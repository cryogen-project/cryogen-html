(ns cryogen-html.core
  (:require [cryogen-core.markup :refer [rewrite-hrefs markup-registry]]
            [clojure.string :as s])
  (:import java.util.Collections
           cryogen_core.markup.Markup))

(defn html
  "Read in html file and returns as is. In future it may perform some tiding.
This plugin is mainly to support reading contents in html without further processing."
  []
  (reify Markup
    (dir [this] "html")
    (ext [this] ".html")
    (render-fn [this]
      (fn [rdr config]
        (->> (java.io.BufferedReader. rdr)
             (line-seq)
             (s/join "\n")
             (rewrite-hrefs (:blog-prefix config)))))))

(defn init []
  (swap! markup-registry conj (html)))
