(ns ch3.fn)

(defn nth-page
  "Return up to page-size results for the nth (0-based) page of source."
  [source page-size page]
  (->> source
       (drop (* page page-size))
       (take page-size)))

(defn page-and-rest
  [source page-size]
  (split-at page-size source))

(defn smallest-n
  [planets n]
  (->> planets
       (sort-by :volume)
       (take n)))
