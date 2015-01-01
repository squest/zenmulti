(ns zenmulti.alfa.routes
	(:gen-class)
	(:require [compojure.core :refer :all]
						[compojure.route :refer [resources not-found]]
						[noir.session :as sess]
						[noir.response :as resp]))

(def backoffice
	(context "/backoffice" req
					 (GET "/" req
								"Hello this is backoffice")))


(defroutes
	main-site
	(GET "/" [req]
			 "Hello this is the main site")
	(GET "/session-test" [req]
			 (do (sess/put! :username "dodol")
					 "Hellow"))
	(GET "/session-get" [req]
			 (str "Hello " (sess/get :username)))
	(not-found "Kagak nemu nyet"))

(def all-routes
	(routes backoffice main-site))

