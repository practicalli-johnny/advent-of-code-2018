(ns advent-of-code-2018.day-nine)

;; infinite sequence of marbles starting at zero

;; place a marble in the circle - curent marble

;; each Elf takes a turn placing the lowest-numbered remaining marble into the circle between the marbles that are 1 and 2 marbles clockwise of the current marble

;; if only one marble, place it 1 clockwise
;; when more than one marble, place new marble clockwise between first and second marble

;; placed marble becomes current marble

;; if marble multiple of 23, keep marble and add the marble value to your score
;; and remove the marble that is 7 marbles counterclockwise and its value added to the current players score
;; the marble one clockwise from the removed marble is the current marble

;; 476 players; last marble is worth 71657 points

;; first test answer
;; 0 2 1

;; [-] (0)
;; [1]  0 (1)
;; [2]  0 (2) 1
;; [3]  0  2  1 (3)
;; [4]  0 (4) 2  1  3
;; [5]  0  4  2 (5) 1  3
;; [6]  0  4  2  5  1 (6) 3

(def test-data [3 0 2 1])

(let [marbles test-data
      sequence (cycle marbles)]
  (conj (take (count marbles) (rest (rest sequence))) (count marbles)) )
;; => (4 3 0 2 1)



(def marbles
  (range 1 (inc 25)))

(def circle 0)

(defn add-marble-to-circle
  "Takes a circle where the head of the collection is the current marble"
  [circle marble]
  (conj [] (first circle) marble (rest circle) ))

;; trying to get 0 2 1
(add-marble-to-circle [0 1] 2)


;; trying to get: 0  2  1 (3)
(add-marble-to-circle [0  2  1] 3)

(defn set-current-marble-to-head [circle]
  ,,,)


(loop [circle [0]
       remaining-marbles marbles]
  (if (empty? remaining-marbles)
    circle
    (recur (conj circle (first remaining-marbles))  (rest remaining-marbles))))
;; => [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25]
