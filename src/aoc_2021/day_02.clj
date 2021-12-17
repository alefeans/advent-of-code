(ns day-02
  (:require [clojure.string :as str]))

(def input (->> (str/split-lines (slurp "resources/aoc_2021/day_02.txt"))
                (map (fn [entry] (let [[c v]    (str/split entry #" ")
                                       command (keyword c)
                                       value   (Integer/parseInt v)]
                                   (if (= command :down)
                                     {:command :up :value (- value)}
                                     {:command command :value value}))))))

(defn part-1 [input]
  (apply * (vals (reduce
                  (fn [result {:keys [command value]}]
                    (if (= command :up)
                      (update result :depth - value)
                      (update result :hp + value)))
                  {:depth 0 :hp 0}
                  input))))

(defn part-2 [input]
  (apply * (vals (select-keys (reduce
                               (fn [{:keys [aim] :as result} {:keys [command value]}]
                                 (if (= command :up)
                                   (update result :aim - value)
                                   (-> (if (= aim 0)
                                         result
                                         (update result :depth + (* value aim)))
                                       (update :hp + value))))
                               {:depth 0 :hp 0 :aim 0}
                               input) [:depth :hp]))))
