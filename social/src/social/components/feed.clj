(ns social.components.feed
  (:require [com.stuartsierra.component :as component]))

(defn process-messages
  [status msg-chan])

(defn handle-responses
  [status response-chan])

(defrecord Feed [auth status msg-chan response-chan]
  component/Lifecycle
  (start [component]
    (reset! (:status component) :running)
    (process-messages status msg-chan)
    (handle-responses status response-chan)
    component)
  (stop [component]
    (reset! (:status component) :stopped)
    component))

(defn new-feed
  [auth msg-chan response-chan]
  (->Feed auth (atom :init) msg-chan response-chan))
