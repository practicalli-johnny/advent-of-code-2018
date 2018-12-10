(ns advent-of-code-2018.day-nine-brian)

(comment
(circle_rules [] 0)
(circle_rules [0] 1)
(circle_rules [1 0] 2)
(circle_rules [2 1 0] 3)
(circle_rules [3 0 2 1] 4) 5
(circle_rules [4 2 1 3 0] 5)
(circle_rules [5 1 3 0 4 2] 6)
(circle_rules [6 3 0 4 2 5 1] 7)
(circle_rules [7 0 4 2 5 1 6 3] 8)
(circle_rules [8 4 2 5 1 6 3 7 0] 9)
(circle_rules [9 2 5 1 6 3 7 0 8 4] 10)
(circle_rules [10 5 1 6 3 7 0 8 4 9 2] 11)
(circle_rules [11 1 6 3 7 0 8 4 9 2 10 5] 12)
(circle_rules [12 6 3 7 0 8 4 9 2 10 5 11 1] 13)
(circle_rules [13 3 7 0 8 4 9 2 10 5 11 1 12 6] 14)
(circle_rules [14 7 0 8 4 9 2 10 5 11 1 12 6 13 3] 15)
(circle_rules [15 0 8 4 9 2 10 5 11 1 12 6 13 3 14 7] 16)
(circle_rules [16 8 4 9 2 10 5 11 1 12 6 13 3 14 7 15 0] 17)
(circle_rules [17 4 9 2 10 5 11 1 12 6 13 3 14 7 15 0 16 8] 18)
(circle_rules [18 9 2 10 5 11 1 12 6 13 3 14 7 15 0 16 8 17 4] 19)
(circle_rules [19 2 10 5 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18 9] 20)
(circle_rules [20 10 5 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18 9 19 2] 21)
(circle_rules [21 5 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18 9 19 2 20 10] 22)
(circle_rules [22 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18 9 19 2 20 10 21 5] 23)
(circle_rules [19 2 20 10 21 5 22 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18] 24)
(circle_rules [24 20 10 21 5 22 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18 19 2] 25)   ;; last play
(circle_rules [25 10 21 5 22 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18 19 2 24 20] 26)   ;; 25 play result
           ;; [26 21 5 22 11 1 12 6 13 3 14 7 15 0 16 8 17 4 18 19 2 24 20 25 10]
)

(def elves {1 0, 2 0, 3 0, 4 0, 5 0, 6 0, 7 0, 8 0, 9 0}) ;; 9 players

(defn circle_rules [circle i]
  (cond
    (= i 0) (conj circle i)
    (= i 1) (vec (concat [i] circle))
    (= i 2) (vec (concat [i] circle))
    (= (mod i 23) 0) (let [circle (vec (concat [i] (drop 2 circle) (take 2 circle)))
                          vec_cnt (count circle)
                          marblepos  (- vec_cnt 9)
                          marbleval (nth circle marblepos)  ;; 7 left + 2 = 9
                          elfpos (rem i 9)
                          elfval (+ 23 marbleval)]

                          (prn elfpos ">" elfval)
                          (update-in elves [elfpos] + elfval)
                          (assoc elves 5 (+ (get elves 5) 32))
                          (prn "elfs >" elves)
                          (vec (concat (drop (inc marblepos) circle)
                                        (drop 1 (take marblepos circle))))
                      ) ;; the multiple of 23 & 7 c/clockwise stone value is removed & scores added up to player
    :else (vec (concat [i] (drop 2 circle) (take 2 circle)))
  )
)



(loop [circle [] i 0]
  (prn ">>>" circle " " i)
  (if (= i 26) ;; 26 is arbitrary
    (prn "elves score >" elves)
    (recur (circle_rules circle i) (inc i))
    ))
;; => nil
