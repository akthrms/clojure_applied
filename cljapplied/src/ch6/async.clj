(ns ch6.async
  (:require [clojure.core.async :refer [admix
                                        chan
                                        dropping-buffer
                                        mix
                                        mult
                                        pipe
                                        pub
                                        sub
                                        tap
                                        toggle]]))

(defn make-comp-1 [])

(defn make-comp-2 [])

(defn get-output [comp1])

(defn get-input [comp2])

(let [component1 (make-comp-1)
      input-chan (get-output component1)
      component2 (make-comp-2)
      output-chan (get-input component2)]
  (pipe input-chan output-chan))

(defn connect-and-tap
  "Connect input and output and return channel logging data flowing between them."
  [input output]
  (let [m (mult input)
        log (chan (dropping-buffer 100))]
    (tap m output)
    (tap m log)
    log))

(defn assemble-chans []
  (let [in (chan 10)
        p (pub in :topic)
        news-ch (chan 10)
        weather-ch (chan 10)]
    (sub p :new news-ch)
    (sub p :weather weather-ch)
    [in news-ch weather-ch]))
