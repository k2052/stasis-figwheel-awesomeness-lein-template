(ns {{name}}.pages
  (:require [optimus.assets :as assets]
            [clojure.string :as string]
            [{{name}}.utils :refer [make-page]]
            [{{name}}.shared.components :as components]
            [rum.core :refer [render-html]]))

(def configs {:styles ["styles.css"]
              :scripts ["js/app.js"]
              :append-html "<script>app.main.init();</script>"})

(defn index []
  (let [html-content (render-html (components/index))]
     (make-page html-content configs)))

(defn about []
  (let [html-content (render-html (components/about))]
     (make-page html-content configs)))

(def routes ["" {"/" index "/about" about}])

(defn route->filename [route]
  (let [cleaned-route (string/replace route #"" "")]
    (if (= cleaned-route "/")
      (str cleaned-route "index.html")
      (str cleaned-route ".html"))))

(defn get-production-assets []
  (assets/load-assets "public" ["/css/styles.css" "/js/app.js"]))

(defn get-assets []
  (concat
    (assets/load-assets "public" ["/css/styles.css" "/js/app.js"])))

(defn get-pages []
  (into {} (for [route (second routes)
                 :let [filename (route->filename (str (key route)))
                       contents ((val route))]]
             [filename contents])))
