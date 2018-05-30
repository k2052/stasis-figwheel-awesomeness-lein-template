(ns ^:figwheel-load {{name}}.app.main
  (:require [clojure.string :refer [replace]]
            [bidi.bidi :refer [match-route]]
            [rum.core :as rum]
            [{{name}}.shared.components :refer [index about]]
            [goog.events :as events]
            [goog.history.EventType :as EventType])
  (:import goog.history.Html5History
          goog.Uri))

(def handlers {:index index :about about})

(def routes ["" {"/" :index "/about" :about}])

(def route ["/index.html" :index])
(defn get-current-path []
  (let [path (.-pathname (.-location js/window))]
    (replace path #".html" "")))

(defn get-component [path]
  (let [{:keys [handler]} (match-route routes path)]
    (handler handlers)))

(defn render! [path]
  (rum/hydrate ((get-component path)) (.getElementById js/document "app")))

(let [history (Html5History.)]
  (events/listen history EventType/NAVIGATE #(render! (.-token %)))
  (doto history
    (.setUseFragment false)
    (.setPathPrefix "")
    (.setEnabled true))
  (events/listen js/document "click"
                 (fn [e]
                   (. e preventDefault)
                   (let [path (.getPath (.parse Uri (.-href (.-target e))))
                         title (.-title (.-target e))]
                     (when path
                       (. history (setToken path title)))))))

(defn start []
  (js/console.log "start")
  (render! (get-current-path)))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))

(defn ^:export init []
  (js/console.log "init")
  (start))
