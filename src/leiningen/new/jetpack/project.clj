(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "A JetPack accelerated website."
  :url "http://github.com/url/here"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [jarohen/nomad "0.6.3"]              ; environment & config files
                 [com.taoensso/timbre "3.2.0"]        ; logging
                 [com.stuartsierra/component "0.2.1"] ; managing stateful components
                 [org.clojure/tools.namespace "0.2.5"] ; ns helpers
                 [org.clojure/tools.trace "0.7.8"]    ; quick debug tool
                 [compojure "1.1.6"]
                 [com.cemerick/friend "0.2.1"]
                 [hiccup "1.0.4"]
                 [jarohen/chord "0.4.2"]  ; core.async channels over http
                 ;[org.clojure/core.async "0.1.256.0-1bf8cf-alpha"]
                 ]
  :main {{name}}.main

  :profiles
  {:dev  {:dependencies  [[javax.servlet/servlet-api "2.5"]
                          [ring-mock "0.1.5"]]
          :source-paths  ["dev"]
          }
   }

  :resources-path "public"

  :plugins [[lein-daemon "0.5.4"]
            [lein-ring "0.8.8"]]

  :daemon {:site {:ns jetpack.main
                  :pidfile "{{name}}.pid"}}

  :ring {:handler {{name}}.site/app
         :auto-reload? true}

  :repl-options {:init-ns user}

  :min-lein-version "2.0.0"

  :jvm-opts  ["-Xmx400m"
             "-XX:+UseConcMarkSweepGC"]
  )
