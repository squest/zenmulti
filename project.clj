(defproject zenmulti "0.1.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.stuartsierra/component "0.2.2"]
                 [zenedu/zenpack "0.1.11"]
                 [enlive "1.1.5"]
                 [org.clojure/core.async "0.1.338.0-5c5012-alpha"]
                 [org.clojure/clojurescript "0.0-2511"]
                 [couchbase-clj "0.2.0"]
                 [reagent "0.4.3"]
                 [cljs-ajax "0.3.3"]
                 [kioo "0.4.0"]]

  :cljsbuild {:builds
              [{:source-paths ["src-cljs/alfa"],
                :compiler {:pretty-print false,
                           :closure-warnings {:non-standard-jsdoc :off},
                           :output-to "resources/public/alfa/app.js",
                           :output-wrapper false,
                           :optimizations :simple}
                :id "alfa-browser"}]}
  
  :url "http://example.com/FIXME"
  :main zenmulti.core
  :jvm-opts ["-server"]
  :plugins [[lein-ring "0.8.13"]
            [codox "0.8.10"]
            [lein-expectations "0.0.8"]
            [lein-environ "1.0.0"]
            [lein-cljsbuild "1.0.4"]
            [lein-autoexpect "1.4.2"]]
  :description "FIXME: write description"
  :min-lein-version "2.0.0")
