package com.socrata.scourgify

trait Credentials {
  def environment:String
  def secretKey:String
}
