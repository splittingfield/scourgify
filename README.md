scourgify
=========

Scala project to move files to Azure Blob

Create a file /etc/scourgify.properties with the field secret_key = AZURE KEY

  
run sbt.

    run storage_account containername file1 file2 file3
  

Creates a new container (or adds to an existing one) the files file1,file2,file3.

There is nothing really special about this going to Azure. Adding imports
will allow writing files to about any blob store supported by jclouds.
