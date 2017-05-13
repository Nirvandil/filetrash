# filetrash
Simple and stupid file exchanger with direct links using Spring MVC.

Directory, where uploaded files will be saved on server, defined in **web.xml**
 file as parameter **uploadPath**. You need to serve it by your 
 frontend web-server (e.g. nginx) in order to get access for uploaded file.
 
 Max file size for uploading defined as property **maxUploadSize** in **filetrash-servlet.xml**. 
 The value is presented *in bytes*.