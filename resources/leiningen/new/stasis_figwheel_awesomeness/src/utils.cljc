(ns {{name}}.utils
  (:require [hiccup.page :refer [html5]]))

(defn make-page [html config]
  (html5
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport"
              :content "width=device-width, initial-scale=1.0"}]
      [:title "Test Static"]
      [:link {:rel "stylesheet" :href "/css/styles.css"}]]
    [:body
     [:div#app html]
     [:script {:src "/js/app.js" :type "text/javascript"}]]))
