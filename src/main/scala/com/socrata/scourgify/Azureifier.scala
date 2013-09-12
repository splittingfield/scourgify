package com.socrata.scourgify

import java.io.File
import com.socrata.scourgify.azure.{VerifiedBlobWriter, AzureBlobContext}

object Azureifier {
  def main(args:Array[String]) {

    // to run.
    // run storage-account container-name file1 file2 file3
    val env = args(0)
    val container = args(1)
    val files = args.drop(2).map(s => new File(s))
    val conf = PropertiesConfiguration
    val key = conf.getProperty("secret_key")
    object Writer extends VerifiedBlobWriter with AzureBlobContext with Credentials { val environment = env; val secretKey = key}

    val thing = Writer.BlobWriter()
    val upload = thing.createBlob(container,files:_*)
    Writer.context.close
    upload match {
      case Left(e) =>
        println(e.getMessage)
        sys.exit(1)
      case Right(e) => println("Upload successful")
    }
    sys.exit(0)

  }
}
