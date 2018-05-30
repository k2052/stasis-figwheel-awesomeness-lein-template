(ns {{name}}.backend
  (:require
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.content-type :refer [wrap-content-type]]
    [ring.middleware.reload :refer [wrap-reload]]
    [ring.middleware.resource :refer [wrap-resource]]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [ring.util.response :as response]
    [stasis.core :as stasis]
    [optimus.optimizations :as optimizations]
    [optimus.prime :as optimus]
    [optimus.strategies :refer [serve-live-assets]]
    [{{name}}.pages :refer [get-pages get-assets]]))

(def app (-> (stasis/serve-pages get-pages)
             (optimus/wrap get-assets optimizations/all serve-live-assets)
             (wrap-resource ".")
             wrap-content-type))

(println "ring ready! prepare your butt for hyperspace jumps" app)

;; NOTE: wrap reload isn't needed when the clj sources are watched by figwheel
;; but it's very good to know about
(def dev-app (wrap-reload (wrap-defaults #'app site-defaults)))
