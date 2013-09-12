package com.socrata.scourgify.azure

import org.jclouds.ContextBuilder
import org.jclouds.blobstore.BlobStoreContext
import com.socrata.scourgify.{Credentials, BlobContext}

trait AzureBlobContext extends BlobContext {
  this:Credentials =>
  def context = ContextBuilder.newBuilder("azureblob").credentials(environment,secretKey).build(classOf[BlobStoreContext])

}
