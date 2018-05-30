(set-env!
 :source-paths #{"src" "cli"}
 :resource-paths #{"resources"}
 :dependencies '[[org.clojure/clojure "1.9.0"]
                 [bidi "2.1.3"]
                 [ring/ring-defaults "0.3.1"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]
                 [optimus "0.20.1"]
                 [rum "0.11.2"]
                 [stasis "2.3.0"]
                 [garden "1.3.5"]
                 [hawk "0.2.11"]
                 [org.martinklepsch/boot-garden "1.3.2-0"]
                 [org.clojure/tools.nrepl "0.2.12" :scope "test"]
                 [com.cemerick/piggieback "0.2.1"]
                 [adzerk/boot-cljs "2.1.4"]
                 [powerlaces/boot-figreload "0.5.14"]
                 [adzerk/boot-cljs-repl "0.3.3"]
                 [binaryage/devtools "0.9.10"]
                 [binaryage/dirac "1.2.35"]
                 [powerlaces/boot-cljs-devtools "0.2.0"]
                 [pandeiro/boot-http "0.8.3"]
                 [weasel "0.7.0" :scope "test"]
                 [org.clojure/clojurescript "1.10.238"]])

(require '[adzerk.boot-cljs              :refer [cljs]]
         '[adzerk.boot-cljs-repl         :refer [cljs-repl]]
         '[powerlaces.boot-figreload     :refer [reload]]
         '[powerlaces.boot-cljs-devtools :refer [dirac cljs-devtools]]
         '[pandeiro.boot-http            :refer [serve]]
         '[org.martinklepsch.boot-garden :refer [garden]])

(task-options! garden {:styles-var   '{{name}}.styles/main
                       :output-to    "public/css/styles.css"
                       :pretty-print true}
               target {:dir #{"resources"}})

(def figwheel-options {:ring-handler '{{name}}.backend/dev-app
                       :css-dirs ["resources/public/css"]
                       :target-path "resources"})

(deftask build
  "Build blog prod version."
  []
  (comp (garden) (target)))

(deftask dev
  [D with-dirac bool "Enable Dirac Devtools."
   p port PORT int "The nRepl port"]
  (comp
    (serve :handler '{{name}}.backend/app :reload true)
    (watch)
    (garden)
    (reload)
    (cljs-repl)
    (cljs :optimizations :none :source-map true)
    (target)))
