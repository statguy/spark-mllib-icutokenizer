name := "spark-mllib-icutokenizer"
organization := "org.apache.spark"

version := "0.1"

scalaVersion := "2.10.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-mllib" % "1.6.1" % "provided",
  "com.ibm.icu" % "icu4j" % "57.1",
  "org.scalatest" %% "scalatest" % "3.0.0-M16-SNAP1" % "test"
)

publishTo := Some(Resolver.file("file", new File(Path.userHome.absolutePath + "/.m2/repository")))
