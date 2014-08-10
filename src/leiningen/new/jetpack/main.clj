(ns {{name}}.main
  (:gen-class)
  (:require
    [com.stuartsierra.component :as component]
    [{{name}}.config :refer [CONFIG]]
    [jetpack.system :refer [jetpack-system]]))


(defn -main [& args]
  (component/start
    (jetpack-system (CONFIG))))
