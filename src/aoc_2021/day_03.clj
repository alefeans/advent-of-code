(ns aoc-2021.day-03
  (:require [clojure.string :as str]))

(def input (str/split-lines (slurp "resources/aoc_2021/day_03.txt")))

(defn transpose [matrix]
  (apply map vector matrix))

(defn bit-frequencies [matrix]
  (map frequencies matrix))

(defn rate [pred bit-freqs]
  (reduce (fn [rate freq]
            (if (pred (get freq \0) (get freq \1))
              (str rate "0")
              (str rate "1")))
          "" bit-freqs))

(def gamma-rate (partial rate >))

(def epsilon-rate (partial rate <))

(defn bin->dec [bin]
  (Integer/parseInt bin 2))

(defn part-1 [input]
  (let [bit-freqs (bit-frequencies (transpose input))]
    (* (bin->dec (gamma-rate bit-freqs)) 
       (bin->dec (epsilon-rate bit-freqs)))))
