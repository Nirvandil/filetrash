# filetrash
Simple and stupid file exchanger with direct links using Spring MVC.

### Settings
Directory, where uploaded files will be saved on server, defined in **web.xml**
 file as parameter **uploadPath**. You need to serve it by your 
 frontend web-server (e.g. nginx) in order to get access for uploaded files
 or create <Context path="context/uploadPath" docBase="realPathOnServer"/> to 
 serve it by Tomcat.
 
  Public key for Google reCAPTCHA must be set in **web.xml** as init-param named
  **dataSiteKey**. Secret key - as init-param **siteKeySecret**
 
 Max file size for uploading defined as init-param **maxUploadSize** in 
 **web.xml**. 
 The value is presented *in bytes*.
 
### Tips
If you encounter "Connection refused" instead MaxUploadSizeExceededException
 on Apache Tomcat when uploaded 
file size is greater than limitation, it's because default request body in 
Tomcat is 2Mb. You can fix it by setting parameter maxSwallowSize="-1" for  
connector (in file conf/server.xml of CATALINA_BASE). 