(ns zenmulti.remains.one)

(defn fm
	[& a]
	(reify
		Object
		(toString [_]
			(apply str (interpose ", " (sort a))))
		clojure.lang.Seqable
		(seq [_]
			({nil nil} a (distinct a)))))



