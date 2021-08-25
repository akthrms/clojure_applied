(ns ch6.async
  (:require [clojure.core.async :as async]))

(defn make-component-1 [])

(defn make-component-2 [])

(defn get-output [component])

(defn get-input [component])

(let [component-1 (make-component-1)
      input (get-input component-1)
      component-2 (make-component-2)
      output (get-output component-2)]
  (async/pipe input output))

(defn connect-and-tap
  "Connect input and output and return channel logging data flowing between them."
  [input output]
  (let [m (async/mult input)
        log (async/chan (async/dropping-buffer 100))]
    (async/tap m output)
    (async/tap m log)
    log))

(defn assemble-channels []
  (let [in (async/chan 10)
        p (async/pub in :topic)
        news-channel (async/chan 10)
        weather-channel (async/chan 10)]
    (async/sub p :new news-channel)
    (async/sub p :weather weather-channel)
    [in news-channel weather-channel]))

(defn combine-channels
  [twitter-channel facebook-channel]
  (async/merge [twitter-channel facebook-channel] 100))

(defn mix-channels
  [twitter-channel facebook-channel out]
  (let [m (async/mix out)]
    (async/admix m twitter-channel)
    (async/admix m facebook-channel)
    (async/toggle m {twitter-channel  {:mute true}
                     facebook-channel {:mute true}})
    m))
