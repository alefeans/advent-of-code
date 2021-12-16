(ns aoc-2021.day-01
  (:require [clojure.string :as str]))

(def input
  (->> (str/split (slurp "resources/aoc_2021/day_01.txt") #"\n")
       (map #(Integer/parseInt %))))

;; --- Part One  ---

;; Solution using partiton + reduce
(defn part-1 [input]
  (->> (partition 2 1 input)
       (reduce (fn [result [right left]] (if (> left right) (inc result) result)) 0)))


;; Solution using a map to control the increment (reusable and fastest one!)
(defn part-1 [input]
  (:result (reduce (fn [{:keys [result previous]} number]
                     (if (> number previous)
                       {:previous number :result (inc result)}
                       {:previous number :result result}))
                   {:result 0 :previous (first input)} (rest input))))

;; --- Part Two ---

(defn part-2 [input]
  (->> (partition 3 1 input)
       (map #(apply + %))
       part-1))
