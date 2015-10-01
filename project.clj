(defproject bmabey/hikari-cp "1.3.2-SNAPSHOT"
  :description "A Clojure wrapper to HikariCP JDBC connection pool"
  :url "https://github.com/tomekw/hikari-cp"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/tomekw/hikari-cp"}
  :dependencies [[org.clojure/clojure         "1.7.0"]
                 [org.tobereplaced/lettercase "1.0.0"]
                 [com.zaxxer/HikariCP         "2.4.1"]
                 [prismatic/schema            "1.0.0"]
                 [prismatic/plumbing "0.5.0" :scope "provided"]
                 [com.stuartsierra/component "0.3.0" :scope "provided"]]
  :profiles {:dev {
                   :dependencies [[expectations               "2.1.3"]
                                  [org.slf4j/slf4j-nop        "1.7.12"]
                                  [org.clojure/java.jdbc      "0.4.1"]
                                  [mysql/mysql-connector-java "5.1.36"]
                                  [org.postgresql/postgresql  "9.3-1102-jdbc41"]]
                   :plugins [[lein-expectations "0.0.7"]]}}
  :aliases {"test" "expectations"})
