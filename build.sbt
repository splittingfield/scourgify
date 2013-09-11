import com.socrata.socratasbt.SocrataSbt._
import SocrataSbtKeys._

seq(socrataSettings(): _*)

scalaVersion := "2.10.2"

name := "scourgify"

libraryDependencies <++= (slf4jVersion) { slf4jVersion =>
  Seq(
  "org.apache.jclouds" % "jclouds-blobstore" % "1.6.2-incubating",
   "org.apache.jclouds.provider" % "azureblob" % "1.6.2-incubating",
  "com.google.code.findbugs" % "jsr305" % "1.3.+",
    "org.slf4j" % "slf4j-simple" % slf4jVersion % "test"
  )
}
