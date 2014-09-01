(ns {{name}}.main
  (:gen-class)
  (:require
    [com.stuartsierra.component :as component]
    [taoensso.timbre :as log]
    [{{name}}.config :refer [CONFIG]]
    [{{name}}.system :refer [{{name}}-system]]))


(defn -main [& args]
  (log/info "Starting {{raw-name}} system...")
  (component/start
    ({{name}}-system (CONFIG))))
