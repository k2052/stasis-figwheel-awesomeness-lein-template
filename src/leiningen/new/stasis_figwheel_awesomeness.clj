(ns leiningen.new.stasis-figwheel-awesomeness
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "stasis-figwheel-awesomeness"))

(defn stasis-figwheel-awesomeness
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' stasis-figwheel-awesomeness project.")
    (->files data
             ;; cli
             ["cli/build/main.clj" (render "cli/build/main.clj" data)]

             ;; Lein configs
             ["project.clj" (render "lein/project.clj" data)]

             ;; Boot Configs. Yes, I combine lein and boot. Don't be jealous of the awesomee
             ["boot.properties" (render "boot/boot.properties" data)]
             ["build.boot" (render "boot/build.boot" data)]
             ["src/main.cljs.edn" (render "src/main.cljs.edn" data)]
             ["resources/main.cljs.edn" (render "src/main.cljs.edn" data)]

             ;; npm configs
             ["package-lock.json" (render "npm/package-lock.json" data)]
             ["package.json" (render "npm/package.json" data)]

             ;; frontend
             ["src/{{sanitized}}/app/main.cljs" (render "src/app/main.cljs" data)]

             ;; backend
             ["src/{{sanitized}}/backend.clj" (render "src/backend.clj" data)]

             ;; shared
             ["src/{{sanitized}}/pages.cljc" (render "src/pages.cljc" data)]
             ["src/{{sanitized}}/styles.clj" (render "src/styles.clj" data)]
             ["src/{{sanitized}}/shared/components.cljc" (render "src/shared/components.cljc" data)]

             ;; rest. all the remaining things! which is a lot of things probably
             ["src/{{sanitized}}/utils.cljc" (render "src/utils.cljc" data)])))
