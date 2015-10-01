(ns hikari-cp.component
  (:require [hikari-cp.core :as core]
            [com.stuartsierra.component :as component]
            [schema.core :as s]
            [plumbing.core :refer [defnk map-keys]]))


(s/defrecord DataSource [db-config :- core/ConfigurationOptions datasource]
  component/Lifecycle
  (start [this]
         (if datasource
           this
           (assoc this :datasource (core/make-datasource db-config))))

  (stop [this]
        (when datasource
          (core/close-datasource datasource))
        (assoc this :datasource nil)))

;; have to create new shcemas here that make the defaulted values optional
(let [default? (-> core/default-datasource-options keys set)]
  (def BaseConfigurationOptions
    (map-keys (fn [k]
                (if (default? k)
                  (s/optional-key k)
                  k))
              core/BaseConfigurationOptions)))

(def AdapterConfigurationOptions
  (assoc BaseConfigurationOptions
         :adapter core/AdaptersList))

(def JDBCUrlConfigurationOptions
  (assoc BaseConfigurationOptions
         :jdbc-url s/Str
         :driver-class-name s/Str))

(def ConfigurationOptions (s/conditional
                             :adapter AdapterConfigurationOptions
                             :jdbc-url JDBCUrlConfigurationOptions))

(defnk datasource
  "Component constructor for use in system-graph: https://github.com/RedBrainLabs/system-graph"
  [[:config db :- ConfigurationOptions]]
  (->DataSource db nil))
