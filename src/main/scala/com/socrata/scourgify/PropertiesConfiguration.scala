package com.socrata.scourgify

import java.io.{FileInputStream, File}
import java.util.Properties


class PropertiesConfiguration
object PropertiesConfiguration extends Properties {
  val f = new File("/etc/scourgify.properties")
  if(f.exists()) {
    load(new FileInputStream(f))
  }
  else {
    sys.error("Please create a file /etc/scourgify.properties with value secret_key = key")
    sys.exit()
  }



}
