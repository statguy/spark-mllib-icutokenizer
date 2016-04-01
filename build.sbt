name := "spark-mllib-icutokenizer"
organization := "org.apache.spark"

version := "0.1"

scalaVersion := "2.10.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-mllib" % "1.6.1" % "provided",
  "com.ibm.icu" % "icu4j" % "57.1"
)
