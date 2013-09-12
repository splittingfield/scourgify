scourgify
=========

Scala project to move files to Azure Blog

Create a file /etc/scourgify.properties with the field secret_key = AZURE KEY

  
run sbt.

    run storage_account containername file1 file2 file3
  

Creates a new container (or adds to an existing one) the files file1,file2,file3
