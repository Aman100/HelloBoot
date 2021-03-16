package com.hypertech.helloboot;
import java.io.*;
import javax.servlet.*;
import com.google.gson.*;
import java.lang.reflect.*;
import javax.servlet.http.*;
import com.hypertech.helloboot.pojo.*;
import com.hypertech.helloboot.annotations.*;
import javax.servlet.annotation.*;
import com.hypertech.helloboot.interfaces.*;
import com.hypertech.helloboot.*;
public class TMRestServiceHandler extends HttpServlet
{
public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
{
doGet(httpServletRequest, httpServletResponse);
}
public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
{
try
{
String path=httpServletRequest.getPathInfo();
System.out.println("GET PATH INFO : " + path);
System.out.println("**************" + path + "**************");
ServletContext servletContext=getServletContext();
Model model=(Model)servletContext.getAttribute(Model.ID);
ServiceModule serviceModule;
serviceModule=model.getServiceModule(path); // trim "/employee/add" and get "employee"
if(serviceModule==null)
{
System.out.println("*************NOT_FOUND****************");
httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
return;
}
path=path.substring(path.indexOf('/',1));
Service service=serviceModule.getService(path);
System.out.println("SERVICE_PATH : " + service.getPath() + ", SERVICE_MODULE : " + serviceModule.getPath());
String httpServletRequestMethodType=httpServletRequest.getMethod();
if(httpServletRequestMethodType.equalsIgnoreCase("GET"))
{
if(service.isGetAllowed()==false)
{
httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(httpServletRequestMethodType.equalsIgnoreCase("POST") )
{
if(service.isPostAllowed()==false)
{
httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
//some more if conditions for other method types
//For getting serviceModuleObject and after applying necessary injections to it.
ServiceController serviceModuleObject=null;
try
{
serviceModuleObject=serviceModule.getServiceModuleObject(servletContext,httpServletRequest,httpServletResponse,service);
}catch(Throwable throwable)
{
throwable.printStackTrace();
}
if(serviceModuleObject==null)
{
System.out.println("**********SOME_ERROR****************");
httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
return;
}


Object arguments[]=service.getArguments(servletContext,httpServletRequest,httpServletResponse);
Object result=null;
try
{
Method method=service.getMethod();
result=method.invoke(serviceModuleObject,arguments);
}catch(InvocationTargetException invocationTargetException)
{
invocationTargetException.printStackTrace();
}
catch(IllegalAccessException illegalAccessException)
{
System.out.println("IllegalAccessException : "+illegalAccessException);
httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
return;
}


// Exception was not raised
// letus resolve forwardTo scenario
System.out.println("IS_FORWARDING : " + service.isForwarding());
if(service.isForwarding())
{
String forwardTo=service.getForwardTo();
System.out.println("FORWARD TO : " + forwardTo);
System.out.println("SERVICE MODULE PATH : " + serviceModule.getPath());
if(forwardTo!=null) forwardTo=serviceModule.getPath()+forwardTo;
if(forwardTo==null)
{
if(service.getReturnType().equals(void.class))
{
httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
return;
}
if(result==null)
{
httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
return;
}
forwardTo=result.toString();
if(forwardTo==null)
{
httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
return;
}
} // service.forwardTo is null
if(model.containsPath(forwardTo))
{
// get url pattern in a variable named as prefix
String prefix=httpServletRequest.getServletPath();
RequestDispatcher httpServletRequestDispatcher;
System.out.println(prefix + " " + forwardTo);
System.out.println(prefix+forwardTo);
httpServletRequestDispatcher=httpServletRequest.getRequestDispatcher(prefix+forwardTo);
httpServletRequestDispatcher.forward(httpServletRequest,httpServletResponse);
return;
}
else
{
System.out.println("FORWARD TO ELSE : " + forwardTo);
RequestDispatcher httpServletRequestDispatcher;
httpServletRequestDispatcher=httpServletRequest.getRequestDispatcher(forwardTo);
httpServletRequestDispatcher.forward(httpServletRequest,httpServletResponse);
return;
}
} // isForwarding ends

OutputStream outputStream=httpServletResponse.getOutputStream();
if(service.getResponseType()==ResponseType.JSON)
{
httpServletResponse.setContentType("application/json");
outputStream.write(new Gson().toJson(result).getBytes("UTF-8"));
return;
}
if(service.getResponseType()==ResponseType.XML)
{
return;
}
if(service.getResponseType()==ResponseType.NONE)
{
return;
}
if(service.getResponseType()==ResponseType.HTML)
{
httpServletResponse.setContentType("text/html");
if(result==null) 
{
outputStream.write("".getBytes("UTF-8"));
return;
}
if(result instanceof String)
{
outputStream.write(String.valueOf(result).getBytes("UTF-8"));
return;
}
if(result instanceof Long)
{
outputStream.write(String.valueOf(result).getBytes("UTF-8"));
return;
}
if(result instanceof Integer)
{
outputStream.write(String.valueOf(result).getBytes("UTF-8"));
return;
}
if(result instanceof Short)
{
outputStream.write(String.valueOf(result).getBytes("UTF-8"));
return;
}
if(result instanceof Byte)
{
outputStream.write(String.valueOf(result).getBytes("UTF-8"));
return;
}
if(result instanceof Double)
{
outputStream.write(String.valueOf(result).getBytes("UTF-8"));
return;
}
if(result instanceof Float)
{
outputStream.write(String.valueOf(result).getBytes("UTF-8"));
return;
}
if(result instanceof Character)
{
outputStream.write(String.valueOf(result).getBytes("UTF-8"));
return;
}
if(result instanceof Boolean)
{
outputStream.write(String.valueOf(result).getBytes("UTF-8"));
return;
}
if(result instanceof File)
{
if(result==null)
{
outputStream.write(String.valueOf(result).getBytes("UTF-8"));
return;
}
return;
}


}
System.out.println("DONE DONE");
if(service.getResponseType()==ResponseType.FILE)
{
System.out.println("ResponseType.FILE");
if(service.getReturnType().equals(ResponseFile.class))
{
System.out.println("ResponseFile.class");
if(result instanceof ResponseFile)
{
if(result==null)
{
httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
return;
}
ResponseFile responseFile=(ResponseFile)result;
File file=responseFile.getFile();
if(file.exists()==false)
{
httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
return;
}
String name=responseFile.getName();
String mimeType=responseFile.getMimeType();
Boolean shouldSave=responseFile.getShouldSave();

//Code to send file to Client
System.out.println(name);
if(shouldSave) httpServletResponse.setHeader("Content-disposition", "attachment; filename="+ name);
httpServletResponse.setContentType(mimeType);
FileInputStream fileInputStream;
fileInputStream=new FileInputStream(file);
BufferedInputStream bis=new BufferedInputStream(fileInputStream);
byte contents[]=new byte[1024];
int bytesRead;
long lengthOfFile=file.length();
int i=0;
while(i<lengthOfFile)
{
bytesRead=bis.read(contents);
if(bytesRead<0) break;
outputStream.write(contents,0,bytesRead);
outputStream.flush();
i=i+bytesRead;
}
fileInputStream.close();
System.out.println("bytes of file sent : "+i);
}
}
}
}catch(Exception exception)
{
exception.printStackTrace();
}
}
}