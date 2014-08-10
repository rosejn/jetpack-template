(ns leiningen.new.jetpack
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files project-name]]
            [leiningen.core.main :as main]))

(defn jetpack
  "A lein template for quickly creating jetpack accelerated web projects."
  [name]
  (let [data {:raw-name name
              :name (project-name name)
              :sanitized (name-to-path name)}
        render (renderer "jetpack")]
    (main/info "Generating a project called " name "based on the 'jetpack' template")
    (->files data
             [".gitignore" (render "gitignore")]
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             ["resources/config.edn" (render "config.edn")]
             "log"
             "public/js"
             "public/img"
             ["src/{{sanitized}}/system.clj" (render "system.clj" data)]
             ["src/{{sanitized}}/config.clj" (render "config.clj" data)]
             ["src/{{sanitized}}/main.clj" (render "main.clj" data)]
             ["src/{{sanitized}}/template.clj" (render "template.clj" data)]
             )))
