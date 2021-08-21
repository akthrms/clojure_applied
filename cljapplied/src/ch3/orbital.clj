(ns ch3.orbital)

(defn semi-major-axis
  "The planet's average distance from the star"
  [p]
  (/ (+ (:aphelion p) (:perihelion p)) 2))

(def G)

(defn mu
  [mass]
  (* G mass))

(defn orbital-period
  "The time it takes for a planet to make a complete orbit around a mass, in seconds"
  [p mass]
  (* Math/PI
     2
     (Math/sqrt (/ (Math/pow (semi-major-axis p) 3)
                   (mu mass)))))

(defn orbital-periods
  "Given a collection of planets, and a star, return the orbital periods of every planet."
  [planets star]
  (let [solar-mass (:mass star)]
    (map (fn [planet] (orbital-period planet solar-mass)) planets)))

(defn orbital-period-transformation
  "Create a map transformation for planet->orbital-period."
  [star]
  (map #(orbital-period % (:mass star))))

(defn orbital-periods
  [planets star]
  (sequence (orbital-period-transformation star) planets))

(defn orbital-periods
  [planets star]
  (into [] (orbital-period-transformation star) planets))

(defn orbital-periods
  [planets star]
  (into () (orbital-period-transformation star) planets))
