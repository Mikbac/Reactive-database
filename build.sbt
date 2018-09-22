name := "Reactive-database"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
  "com.h2database" % "h2" % "1.4.197",
  "org.slf4j" % "slf4j-nop" % "1.7.25",
  "org.junit.jupiter" % "junit-jupiter-api" % "5.3.1" % Test
)