package com.socrata.scourgify

import java.io.File

object Azureifier {
  def main(args:Array[String]) {

    // to run.
    // run storage-account container-name file1 file2 file3
    val env = args(0)
    val container = args(1)
    val files = args.drop(2).map(s => new File(s))

    object Writer extends AzureBlobWriter with AzureBlobContext with Credentials { val environment = env; val secretKey = "secretKey"}

    val thing = Writer.BlobWriter()
    thing.createBlob(container,files:_*)
    Writer.context.close
  }
}
