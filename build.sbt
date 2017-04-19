name := """play-java-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

// https://mvnrepository.com/artifact/org.postgresql/postgresql
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.12"

// https://mvnrepository.com/artifact/javax/javaee-web-api
libraryDependencies += "commons-codec" % "commons-codec" % "1.6"

//"org.postgresql" % "postgresql" % "9.4.1211.jre7"

libraryDependencies ++= Seq(
  // If you enable PlayEbean plugin you must remove these
  // JPA dependencies to avoid conflicts.
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final"
  
)





fork in run := true