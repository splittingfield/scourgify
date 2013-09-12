package com.socrata.scourgify

trait BlobWriterComponent[T,R] {
  type BlobWriter <: BlobWriterLike
  trait BlobWriterLike {
    def createBlob(container:String,files:T*):Either[Exception,R]

  }

  def BlobWriter():BlobWriter

}

















