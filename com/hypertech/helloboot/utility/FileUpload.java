package com.hypertech.helloboot.utility;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@MultipartConfig(maxFileSize = 1024 * 1024 * 2)
public class FileUpload extends HttpServlet
{
private HttpServletRequest request;
private String imageData;
public FileUpload(HttpServletRequest request, String imageData)
{
this.request=request;
this.imageData=imageData;
}
public String upload() throws ServletException, IOException
{
String appPath = request.getServletContext().getRealPath("/");
String imageDestination=appPath+"WEB-INF/uploadedFiles";
File file=new File(imageDestination);
if(file.exists()==false) file.mkdirs();
System.out.println(imageDestination + " " + imageData);
Part part = request.getPart(imageData);
String imageName = part.getSubmittedFileName();
System.out.println(imageName);
try
{
part.write(imageDestination + File.separator + imageName);
}catch (Exception ex)
{
}
return imageDestination+"/"+imageName;
}//method ends
     
//Validates uploaded file is Image or not
}