name := "kamon-test"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.19",
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",

  "com.typesafe.akka" %% "akka-actor-typed" % "2.5.22",
  "com.typesafe.akka" %% "akka-http" % "10.1.8",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.8",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.8",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.22" % Test,

  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,

  "io.kamon" %% "kamon-core" % "1.1.2",
  "io.kamon" %% "kamon-scala-future" % "1.0.0",
  "io.kamon" %% "kamon-influxdb" % "1.0.2",
  "io.kamon" %% "kamon-prometheus" % "1.1.1",
  "io.kamon" %% "kamon-system-metrics" % "1.0.1",
  "org.aspectj" % "aspectjweaver" % "1.8.13"
)