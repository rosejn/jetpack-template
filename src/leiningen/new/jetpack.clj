(ns leiningen.new.jetpack
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files project-name]]
            [leiningen.core.main :as main]))

(defn in?
  "true if seq contains elm"
  [seq elm]
  (true? (some #(= elm %) seq)))

(defn jetpack
  "A lein template for quickly creating jetpack accelerated web projects."
  ([name]
   (jetpack name []))
  ([name & opts]
   (let [data {:raw-name name
               :name (project-name name)
               :sanitized (name-to-path name)
               :websocket (in? opts :websocket)
               :om (in? opts :om)
               :friend (in? opts :friend)}
         render (renderer "jetpack")
         base [["project.clj" (render "project.clj" data)]
               [".gitignore" (render "gitignore")]
               ["README.md" (render "README.md" data)]
               "log"
               "public/js"
               "public/img"
               ["pages/index.md" (render "pages/index.md" data)]
               ["pages/recipes.edn" (render "pages/recipes.edn" data)]

               ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
               ["src/{{sanitized}}/pages.clj" (render "pages.clj" data)]
               ["src/{{sanitized}}/template.clj" (render "template.clj" data)]
               ["src/{{sanitized}}/users.clj" (render "users.clj" data)]
               ["src/{{sanitized}}/config.clj" (render "config.clj" data)]
               ["resources/config.edn" (render "config.edn")]

               ["src/{{sanitized}}/components/http_server.clj" (render "components/http_server.clj" data)]
               ["src/{{sanitized}}/components/router.clj" (render "components/router.clj" data)]
               ["src/{{sanitized}}/components/page_loader.clj" (render "components/page_loader.clj" data)]
               ["src/{{sanitized}}/system.clj" (render "system.clj" data)]

               ["src/{{sanitized}}/main.clj" (render "main.clj" data)]
               ["dev/user.clj" (render "dev/user.clj" data)]
               ["dev/user/once.clj" (render "dev/user/once.clj" data)]]
         ws [["src/{{sanitized}}/websocket.clj" (render "websocket.clj" data)]]
         om []
         friend []
         rendered (reduce (fn [m [opt files]]
                            (if (in? opts opt) (concat m files) m))
                          base
                          {:websocket ws
                           :om om
                           :friend friend})]
     (main/info "Generating a project called " name "based on the 'jetpack' template")
     (apply ->files data rendered))))
