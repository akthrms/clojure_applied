(ns ch1.recipe)

(defrecord Recipe [name author description ingredients steps servings])

(defrecord Person [fname lname])

(def toast (->Recipe "Toast"
                     (->Person "Alex" "Miller")
                     "Crispy bread"
                     ["Slice of board"]
                     ["Toast bread in toaster"]
                     1))

(def people {"p1" (->Person "Alex" "Miller")})

(def recipes {"r1" (->Recipe "Toast"
                             "p1"
                             "Crispy bread"
                             ["Slice of board"]
                             ["Toast bread in toaster"]
                             1)})
