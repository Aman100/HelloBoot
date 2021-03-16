package com.hypertech.helloboot.pojo;
import java.lang.reflect.*;
import java.util.*;
import com.hypertech.helloboot.annotations.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.hypertech.helloboot.utility.*;
import java.io.*;
public class Service implements java.io.Serializable
{
private String path;
private Method method;
private boolean injectApplicationContainer;
private boolean injectSessionContainer;
private boolean injectRequestContainer;
private boolean injectCookieManager;
private boolean injectContextDirectory;
private boolean fileConfiguration;
private FileConfiguration fileConfigurationAnnotation;
private boolean allowGet;
private boolean allowPost;
private boolean isSecured;
private Guard guard;
private Class [] parameters;
private int applicationParameterIndexes[];
private int sessionParameterIndexes[];
private int requestParameterIndexes[];
private int cookieManagerParameterIndexes[];
private int contextDirectoryParameterIndexes[];
private int propertyParameterIndexes[];
private Property[] propertyParameters;
private int autoWiredParameterIndexes[];
private AutoWired[] autoWiredParameters;
private Class returnType;
private ResponseType responseType;
private boolean isForwarding;
private String forwardTo;
private boolean exceptionHandler;
private boolean exceptionHandlerAService;
private String exceptionHandlerService;
public void setExceptionHandlerService(String exceptionHandlerService)
{
this.exceptionHandlerService=exceptionHandlerService;
}
public String getExceptionHandler()
{
return this.exceptionHandlerService;
}
public void setExceptionHandler(boolean exceptionHandler)
{
this.exceptionHandler=exceptionHandler;
}
public boolean hasExceptionHandler()
{
return this.exceptionHandler;
}
public void setExceptionHandlerAService(boolean exceptionHandlerAService)
{
this.exceptionHandlerAService=exceptionHandlerAService;
}
public boolean isExceptionHandlerAService()
{
return this.exceptionHandlerAService;
}
public void setPath(String path)
{
this.path=path;
}
public String getPath()
{
return this.path;
}
public void setMethod(Method method)
{
this.method=method;
}
public Method getMethod()
{
return this.method;
}
public void setFileConfigurationAnnotation(FileConfiguration fileConfigurationAnnotation)
{
this.fileConfigurationAnnotation=fileConfigurationAnnotation;
}
public FileConfiguration getFileConfigurationAnnotation()
{
return this.fileConfigurationAnnotation;
}
public void setFileConfiguration(boolean fileConfiguration)
{
this.fileConfiguration=fileConfiguration;
}
public boolean getFileConfiguration()
{
return this.fileConfiguration;
}
public void setInjectApplicationContainer(boolean injectApplicationContainer)
{
this.injectApplicationContainer=injectApplicationContainer;
}
public boolean getInjectApplicationContainer()
{
return this.injectApplicationContainer;
}
public void setInjectSessionContainer(boolean injectSessionContainer)
{
this.injectSessionContainer=injectSessionContainer;
}
public boolean getInjectSessionContainer()
{
return this.injectSessionContainer;
}
public void setInjectRequestContainer(boolean injectRequestContainer)
{
this.injectRequestContainer=injectRequestContainer;
}
public boolean getInjectRequestContainer()
{
return this.injectRequestContainer;
}
public void setInjectCookieManager(boolean injectCookieManager)
{
this.injectCookieManager=injectCookieManager;
}
public boolean getInjectCookieManager()
{
return this.injectCookieManager;
}
public void setInjectContextDirectory(boolean injectContextDirectory)
{
this.injectContextDirectory=injectContextDirectory;
}
public boolean getInjectContextDirectory()
{
return this.injectContextDirectory;
}
public void setAllowGet(boolean allowGet)
{
this.allowGet=allowGet;
}
public boolean isGetAllowed()
{
return this.allowGet;
}
public void setAllowPost(boolean allowPost)
{
this.allowPost=allowPost;
}
public boolean isPostAllowed()
{
return this.allowPost;
}
public void setIsSecured(boolean isSecured)
{
this.isSecured=isSecured;
}
public boolean getIsSecured()
{
return this.isSecured;
}
public void setGuard(Guard guard)
{
this.guard=guard;
}
public Guard getGuard()
{
return this.guard;
}
public void setParameters(Class[] parameters)
{
this.parameters=parameters;
}
public Class[] getParameters()
{
return this.parameters;
}
public void setApplicationParameterIndexes(int[] applicationParameterIndexes)
{
this.applicationParameterIndexes=applicationParameterIndexes;
}
public int[] getApplicationParameterIndexes()
{
return this.applicationParameterIndexes;
}
public void setSessionParameterIndexes(int[] sessionParameterIndexes)
{
this.sessionParameterIndexes=sessionParameterIndexes;
}
public int[] getSessionParameterIndexes()
{
return this.sessionParameterIndexes;
}
public void setRequestParameterIndexes(int[] requestParameterIndexes)
{
this.requestParameterIndexes=requestParameterIndexes;
}
public int[] getRequestParameterIndexes()
{
return this.requestParameterIndexes;
}
public void setCookieManagerParameterIndexes(int[] cookieManagerParameterIndexes)
{
this.cookieManagerParameterIndexes=cookieManagerParameterIndexes;
}
public int[] getCookieManagerParameterIndexes()
{
return this.cookieManagerParameterIndexes;
}
public void setContextDirectoryParameterIndexes(int[] contextDirectoryParameterIndexes)
{
this.contextDirectoryParameterIndexes=contextDirectoryParameterIndexes;
}
public int[] getContextDirectoryParameterIndexes()
{
return this.contextDirectoryParameterIndexes;
}
public void setPropertyParameterIndexes(int[] propertyParameterIndexes)
{
this.propertyParameterIndexes=propertyParameterIndexes;
}
public int[] getPropertyParameterIndexes()
{
return this.propertyParameterIndexes;
}
public void setPropertyParameters(Property[] propertyParameters)
{
this.propertyParameters=propertyParameters;
}
public Property[] getPropertyParameters()
{
return this.propertyParameters;
}
public void setAutoWiredParameters(AutoWired[] autoWiredParameters)
{
this.autoWiredParameters=autoWiredParameters;
}
public AutoWired[] getAutoWiredParameters()
{
return this.autoWiredParameters;
}
public void setAutoWiredParameterIndexes(int[] autoWiredParameterIndexes)
{
this.autoWiredParameterIndexes=autoWiredParameterIndexes;
}
public int[] getAutoWiredParameterIndexes()
{
return this.autoWiredParameterIndexes;
}
public void setReturnType(Class returnType)
{
this.returnType=returnType;
}
public Class getReturnType()
{
return this.returnType;
}
public void setResponseType(ResponseType responseType)
{
this.responseType=responseType;
}
public ResponseType getResponseType()
{
return this.responseType;
}
public void setIsForwarding(boolean isForwarding)
{
this.isForwarding=isForwarding;
}
public boolean isForwarding()
{
return this.isForwarding;
}
public void setForwardTo(String forwardTo)
{
this.forwardTo=forwardTo;
}
public String getForwardTo()
{
return this.forwardTo;
}
public Object[] getArguments(ServletContext servletContext, HttpServletRequest request,HttpServletResponse response)
{
if(parameters==null) return new Object[0];
Object arguments[]=new Object[parameters.length];
int i=0;
for(Class parameter:parameters)
{
if(parameter.equals(long.class)) arguments[i]=0;
else if(parameter.equals(int.class))  arguments[i]=0;
else if(parameter.equals(short.class))  arguments[i]=0;
else if(parameter.equals(byte.class))  arguments[i]=0;
else if(parameter.equals(double.class))  arguments[i]=0.0;
else if(parameter.equals(float.class))  arguments[i]=0.0;
else if(parameter.equals(char.class))  arguments[i]='\u0000';
else if(parameter.equals(boolean.class))  arguments[i]=false;
i++;
}
if(injectApplicationContainer)
{
ApplicationContainer applicationContainer=new ApplicationContainer(servletContext);
for(int api:applicationParameterIndexes) arguments[api]=applicationContainer;
}
if(injectSessionContainer)
{
HttpSession httpSession=request.getSession();
SessionContainer sessionContainer=new SessionContainer(httpSession);
for(int spi:sessionParameterIndexes) arguments[spi]=sessionContainer;
}
if(injectRequestContainer)
{
RequestContainer requestContainer=new RequestContainer(request);
for(int rpi:requestParameterIndexes) arguments[rpi]=requestContainer;
}
if(injectCookieManager)
{
CookieManager cookieManager=new CookieManager(request,response);
for(int cm:cookieManagerParameterIndexes) arguments[cm]=cookieManager;
}
if(injectContextDirectory)
{
Model model=(Model)servletContext.getAttribute(Model.ID);
ContextDirectory contextDirectory=model.getContextDirectory();
for(int cd:contextDirectoryParameterIndexes) arguments[cd]=contextDirectory;
}
if(fileConfiguration)
{
System.out.println("CHAMACHAM : " + fileConfigurationAnnotation.inputName());
FileUpload fileUpload=new FileUpload(request,fileConfigurationAnnotation.inputName());
try
{
String filePath=fileUpload.upload();
System.out.println("FilePath : " + filePath);
UploadedFile uploadedFile=new UploadedFile(new File(filePath),false);
arguments[0]=uploadedFile;
}catch(Exception e)
{
e.printStackTrace();
}
}
//Property
i=0;
if(propertyParameterIndexes!=null)
{
for(int ppi:propertyParameterIndexes)
{
String paramValue=propertyParameters[i].value();
paramValue=request.getParameter(paramValue);
try
{
arguments[ppi]=Utility.parseParamValue(paramValue,parameters[ppi]);
}catch(Exception e) { }
i++;
}
}
i=0;
if(autoWiredParameterIndexes!=null)
{
Object object=null;
for(int api:autoWiredParameterIndexes)
{
String name=autoWiredParameters[i].name();
if(object==null) object=request.getAttribute(name);
if(object==null) object=request.getSession().getAttribute(name);
if(object==null) object=servletContext.getAttribute(name);
if(object!=null) if(parameters[api].equals(object.getClass())) arguments[api]=object;
i++;
}
}
return arguments;
}
}
