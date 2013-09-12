package com.socrata.scourgify

import org.jclouds.blobstore.BlobStoreContext


trait BlobContext {
  def context:BlobStoreContext
}
