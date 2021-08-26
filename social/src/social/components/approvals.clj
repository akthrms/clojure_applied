(ns social.components.approvals
  (:require [com.stuartsierra.component :as component]))

(defn process-alerts
  [knowledge-engine response-chan])

(defrecord Approvals [approval-config alert-chan knowledge-engine response-chan]
  component/Lifecycle
  (start [component]
    (process-alerts knowledge-engine response-chan)
    component)
  (stop [component]
    component))

(defn new-approvals
  [approval-config alert-chan response-chan]
  (map->Approvals {:approval-config approval-config
                   :alert-chan      alert-chan
                   :response-chan   response-chan}))
