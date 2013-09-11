package com.socrata.scourgify

trait BlobWriterComponent[T,R] {
    type BlobWriter <: BlobWriterLike
    trait BlobWriterLike {
      def createBlob(container:String,files:T*):Either[Exception,R]

    }

    def BlobWriter():BlobWriter

}

import org.jclouds.blobstore.BlobStoreContext
import java.io.File
import org.jclouds.blobstore.options.PutOptions

trait BlobContext {
  def context:BlobStoreContext
}



import org.jclouds.ContextBuilder
trait AzureBlobContext extends BlobContext {
  this:Credentials =>
  def context = ContextBuilder.newBuilder("azureblob").credentials(environment,secretKey).build(classOf[BlobStoreContext])

}


trait Credentials {
  def environment:String
  def secretKey:String
}



trait AzureBlobWriter extends BlobWriterComponent[File,String]{
  this:AzureBlobContext =>
  class BlobWriter() extends BlobWriterLike {
    def createBlob(container:String, files:File*) = {
      val blob = context.getBlobStore
      if(!blob.containerExists(container))
        blob.createContainerInLocation(null,container)


      val etags = files.map{ case f =>
        val builder = blob.blobBuilder(f.getName)
        val thing = builder.payload(f).build
        blob.putBlob(container,thing, new PutOptions(true))
      }


     Right("")


    }
  }

  def BlobWriter(): BlobWriter = new BlobWriter()

}






