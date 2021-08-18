(ns ch1.validate
  (:require [clojure.spec.alpha :as s]))

(defrecord Recipe [name description ingredients steps servings])

(defrecord Ingredient [name quantity unit])

(def spaghetti-tacos (map->Recipe {:name        "Spaghetti tacos"
                                   :description "It's spaghetti... in a taco."
                                   :ingredients [(->Ingredient "Spaghetti" 1 :lb)
                                                 (->Ingredient "Spaghetti sauce" 16 :oz)
                                                 (->Ingredient "Taco shell" 12 :shell)]
                                   :steps       ["Cook spaghetti according to box."
                                                 "Heat spaghetti sauce until warm."
                                                 "Mix spaghetti and sauce."
                                                 "Put spaghetti in taco shells and serve."]
                                   :servings    4}))

(s/def ::name string?)
(s/def ::quantity int?)
(s/def ::unit keyword?)
(s/def ::ingredient (s/keys :req-un [::name ::quantity ::unit]))

(s/def ::description string?)
(s/def ::ingredients (s/coll-of ::ingredient))
(s/def ::steps string?)
(s/def ::servings int?)
(s/def ::recipe (s/keys :req-un [::name ::description ::ingredients ::steps ::servings]))
