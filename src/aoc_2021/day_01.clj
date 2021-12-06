(ns aoc-2021.day-01
  (:require [clojure.string :as str]))

(def input
  (->> (str/split (slurp "resources/aoc_2021/day_01.txt") #"\n")
       (map #(Integer/parseInt %))))

;; --- Part One  ---

(defn part-1 [input]
  (:total (reduce (fn [{:keys [total previous]} number]
                    (if (> number previous)
                      {:previous number :total (inc total)}
                      {:previous number :total total}))
                  {:total 0 :previous (first input)} (rest input))))

;; --- Part Two ---

(defn part-2 [input]
  (->> (partition 3 1 input)
       (map #(apply + %))
       part-1))
