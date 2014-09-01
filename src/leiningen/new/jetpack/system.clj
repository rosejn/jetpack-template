(ns {{name}}.system
  (:require [com.stuartsierra.component :as component]
            [taoensso.timbre :as log]
            [{{name}}.components.page-loader :refer [page-loader-component]]
            [{{name}}.components.router :refer [router-component]]
            [{{name}}.components.http-server :refer [http-server-component]]))

(defn {{name}}-system
  [{:keys [loader router http] :as config}]
  (log/info "Creating the {{name}} system...")
  (component/system-map
    :page-loader (page-loader-component loader)
    :router  (component/using (router-component router) [:page-loader])
    :server  (component/using (http-server-component http) [:router])))

