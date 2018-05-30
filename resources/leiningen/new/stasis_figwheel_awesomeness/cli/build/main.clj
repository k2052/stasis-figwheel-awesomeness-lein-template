(ns build.main
  (:require [stasis.core :as stasis]
            [optimus.optimizations :as optimizations]
            [optimus.export :refer [save-assets]]
            [optimus.prime :as optimus]
            [clojure.java.shell :refer [sh]]
            [{{name}}.pages :refer [get-production-assets get-pages]]))

(def export-dir "dist")

(defn sh! [command]
  (println command)
  (println (sh "bash" "-c" command)))

(defn watch []
  (sh! "boot dev"))

(defn export []
  (let [assets (optimizations/all (get-production-assets) {})
        pages (get-pages)]
    (stasis/empty-directory! export-dir)
    (save-assets assets export-dir)
    (stasis/export-pages pages export-dir {:optimus-assets assets})))

(defn build []
  (sh! "boot build")
  (sh! "lein cljsbuild once release")
  (export))
