package com.socrata.scourgify.azure

import java.io.File
import org.jclouds.blobstore.options.PutOptions
import com.socrata.scourgify.{BlobContext, BlobWriterComponent}

trait VerifiedBlobWriter extends BlobWriterComponent[File,Seq[Triple[String,Long,Long]]]{
  this:BlobContext =>
  class BlobWriter() extends BlobWriterLike {
    def createBlob(container:String, files:File*) = {
      val blob = context.getBlobStore
      if(!blob.containerExists(container))
        blob.createContainerInLocation(null,container)

     try{
      val sizes = files.map{ case f =>
        val fileName = f.getName
        val builder = blob.blobBuilder(fileName)
        val thing = builder.payload(f).build
        blob.putBlob(container,thing, new PutOptions(true))
        val metadata = blob.blobMetadata(container,fileName)
        (fileName,f.length, metadata.getContentMetadata.getContentLength.longValue)
      }
      if(verifyUpload(files,sizes))
        Right(sizes)
      else
        Left(new RuntimeException("Possible error in uploading files."))
     }
     catch {
       case e:Exception => Left(e)
     }

    }
    def verifyUpload(files:Seq[File],uploads:Seq[Triple[String,Long,Long]]):Boolean = {
      files.size == uploads.length &&
        uploads.foldLeft(true){case (acc,blob) => acc && blob._2 == blob._3}


    }

  }

  def BlobWriter(): BlobWriter = new BlobWriter()


}
