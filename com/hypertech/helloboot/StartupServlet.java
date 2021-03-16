package com.hypertech.helloboot;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.net.*;
import java.lang.reflect.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.annotation.*;
import com.hypertech.helloboot.*;
import com.hypertech.helloboot.pojo.*;
import com.hypertech.helloboot.annotations.*;
public class StartupServlet extends HttpServlet
{
private static URLClassLoader urlClassLoader;
private List<String> list=new LinkedList<>();

//init
public void init()
{
String base=System.getProperty("user.dir")+"\\webapps";
ServletContext servletContext=(ServletContext)getServletContext();
File file=(File)servletContext.getAttribute(ServletContext.TEMPDIR);
String path=file.getAbsolutePath();
String contextDirectory=path.substring(path.lastIndexOf('\\')+1);
contextDirectory=base+"\\"+contextDirectory;
System.out.println("CONTEXT_DIR : " + contextDirectory);
try
{
//Absolute path of file is added to the jarFiles[]
File [] jarFiles=new File(contextDirectory+"\\WEB-INF\\lib").listFiles(new FileFilter(){
public boolean accept(File f)
{
return f.getName().endsWith(".jar");
}
});
//Dynamic loading jars into classpath
URL [] jarURLS=new URL[jarFiles.length+1];
int ii=0;
for(File jarFile:jarFiles)
{
jarURLS[ii]=jarFile.toURL();
ii++;
}
jarURLS[ii]=new File(contextDirectory+"\\WEB-INF\\classes").toURL();
urlClassLoader=new URLClassLoader(jarURLS);
getFileNames(list, new File(contextDirectory+"\\WEB-INF\\classes").toPath());
}catch(Exception e)
{
e.printStackTrace();
}
list.forEach(System.out::println);
analyzeServices();
}

//getFileNames
static public List<String> getFileNames(List<String> fileNames, java.nio.file.Path dir)
{
try(DirectoryStream<java.nio.file.Path> stream=Files.newDirectoryStream(dir))
{
for(java.nio.file.Path path:stream)
{
if(path.toFile().isDirectory()) getFileNames(fileNames, path);
else
{
String p=path.toAbsolutePath().toString();
System.out.println("PATH :" + p);
p=p.substring(p.indexOf("\\com\\")+1).replace('\\','.');
if(p.endsWith(".class") && p.startsWith("com"))
{
try
{
String s=p.substring(0,p.length()-6);
Class c=Class.forName(s,true,urlClassLoader);
fileNames.add(s);
}catch(Exception e)
{
e.printStackTrace();
}
}
}
}
}catch(IOException ioException)
{
ioException.printStackTrace();
}
return fileNames;
}

//analyzeServices
private void analyzeServices()
{
try
{
//one loop should run no.of.serviceClass times (Size of list times)
//in it there will be a nested loop which run no.of.method(s) times in serviceClass. 
//size of methodArray which getMethods() return 
Class serviceClass=null;
Method method[]=null;
String fullPath=null;
Service service=null;
ServiceModule serviceModule=null;
Map<String, Service> services=new HashMap<>();
ServletContext servletContext=(ServletContext)getServletContext();
Model model=new Model(servletContext);
List<AutoWiredField> autoWiredFields=null;
AutoWiredField autoWiredField=null;
for(int i=0;i<list.size();i++)
{
System.out.println("I : " + i + " " + list.size());
serviceModule=new ServiceModule();
serviceClass=Class.forName(list.get(i));
method=serviceClass.getMethods();
Field fields[]=serviceClass.getDeclaredFields();
autoWiredFields=new LinkedList<>();
for(Field field:fields)
{
if(field.isAnnotationPresent(AutoWired.class))
{
AutoWired autoWiredAnnotation=(AutoWired)field.getAnnotation(AutoWired.class);
autoWiredField=new AutoWiredField();
if(autoWiredAnnotation.name().equals("")==false) autoWiredField.setName(autoWiredAnnotation.name());
else autoWiredField.setName(autoWiredAnnotation.name());
autoWiredField.setField(field);
autoWiredFields.add(autoWiredField);
}
}

if(serviceClass.isAnnotationPresent(com.hypertech.helloboot.annotations.Path.class))
{
com.hypertech.helloboot.annotations.Path pathAnnotation=(com.hypertech.helloboot.annotations.Path)serviceClass.getAnnotation(com.hypertech.helloboot.annotations.Path.class);
serviceModule.setPath(pathAnnotation.value());
}
else continue;
if(serviceClass.isAnnotationPresent(Get.class)) serviceModule.setAllowGet(true);
else serviceModule.setAllowGet(false);
if(serviceClass.isAnnotationPresent(Post.class)) serviceModule.setAllowPost(true);
else serviceModule.setAllowPost(false);
fields=serviceClass.getDeclaredFields();
for(Field field:fields)
{
if(field.getType().equals(RequestContainer.class))
{
field.setAccessible(true);
serviceModule.setRequestContainerField(field);
}
else if(field.getType().equals(SessionContainer.class))
{
field.setAccessible(true);
serviceModule.setSessionContainerField(field);
}
else if(field.getType().equals(ApplicationContainer.class))
{
field.setAccessible(true);
serviceModule.setApplicationContainerField(field);
}
}

for(int j=0;j<method.length;j++) //loop for performing operation on method array's element
{
service=new Service();
//Lots of if conditions
if(method[j].getName().equals("setRequestContainer")) serviceModule.setRequestInjectorMethod(method[j]);
if(method[j].getName().equals("setSessionContainer")) serviceModule.setSessionInjectorMethod(method[j]);
if(method[j].getName().equals("setApplicationContainer")) serviceModule.setApplicationInjectorMethod(method[j]);
if(method[j].isAnnotationPresent(com.hypertech.helloboot.annotations.FileConfiguration.class))
{
service.setFileConfiguration(true);
service.setFileConfigurationAnnotation(method[j].getAnnotation(com.hypertech.helloboot.annotations.FileConfiguration.class));
}
else service.setFileConfiguration(false);
if(method[j].isAnnotationPresent(com.hypertech.helloboot.annotations.Path.class))
{
com.hypertech.helloboot.annotations.Path pathAnnotation=method[j].getAnnotation(com.hypertech.helloboot.annotations.Path.class);
service.setPath(pathAnnotation.value());
}
else 
{ 
//i think i need to do something here
}

if(method[j].isAnnotationPresent(Get.class)) service.setAllowGet(true);
else service.setAllowGet(false);
if(method[j].isAnnotationPresent(Post.class)) service.setAllowPost(true);
else service.setAllowPost(false);


Annotation annotations[][]=method[j].getParameterAnnotations();
int length=0;
for(Annotation a[]:annotations) if(a.length>0) length++;
if(length>0)
{
int propertyParameterIndexes[]=new int[annotations.length];
Property []propertyParameters=new Property[annotations.length];
int autoWiredParameterIndexes[]=new int[annotations.length];
AutoWired []autoWiredParameters=new AutoWired[annotations.length];
int ii=0, jj=0, kk=0;
for(Annotation annotation[]:annotations)
{
if(annotation.length>0)
{
if(annotation[0].annotationType().equals(Property.class))
{
propertyParameterIndexes[ii]=jj;
propertyParameters[ii]=(Property)annotation[0];
ii++;
}
if(annotation[0].annotationType().equals(AutoWired.class))
{
autoWiredParameterIndexes[kk]=jj;
autoWiredParameters[kk]=(AutoWired)annotation[0];
kk++;
}
}
jj++;
}
propertyParameterIndexes=Arrays.copyOf(propertyParameterIndexes,ii);
propertyParameters=Arrays.copyOf(propertyParameters,ii);
autoWiredParameterIndexes=Arrays.copyOf(autoWiredParameterIndexes,kk);
autoWiredParameters=Arrays.copyOf(autoWiredParameters,kk);
service.setPropertyParameterIndexes(propertyParameterIndexes);
service.setPropertyParameters(propertyParameters);
service.setAutoWiredParameterIndexes(autoWiredParameterIndexes);
service.setAutoWiredParameters(autoWiredParameters);
}

if(method[j].isAnnotationPresent(InjectRequestContainer.class))
{
Parameter parameters[]=method[j].getParameters();
int requestParameterIndexes[]=new int[parameters.length];
length=0;
int ii=0, jj=0;
for(Parameter p:parameters)
{
if(p.getType().equals(RequestContainer.class))
{
requestParameterIndexes[ii]=jj;
ii++;
length++;
}
jj++;
}
service.setRequestParameterIndexes(requestParameterIndexes);
service.setInjectRequestContainer(true);
}
else service.setInjectRequestContainer(false);

if(method[j].isAnnotationPresent(InjectSessionContainer.class))
{
Parameter parameters[]=method[j].getParameters();
int sessionParameterIndexes[]=new int[parameters.length];
length=0;
int ii=0, jj=0;
for(Parameter p:parameters)
{
if(p.getType().equals(SessionContainer.class))
{
sessionParameterIndexes[ii]=jj;
ii++;
length++;
}
jj++;
}
service.setSessionParameterIndexes(sessionParameterIndexes);
service.setInjectSessionContainer(true);
}
else service.setInjectSessionContainer(false);

if(method[j].isAnnotationPresent(InjectApplicationContainer.class))
{
Parameter parameters[]=method[j].getParameters();
int applicationParameterIndexes[]=new int[parameters.length];
length=0;
int ii=0, jj=0;
for(Parameter p:parameters)
{
if(p.getType().equals(ApplicationContainer.class))
{
applicationParameterIndexes[ii]=jj;
ii++;
length++;
}
jj++;
}
service.setApplicationParameterIndexes(applicationParameterIndexes);
service.setInjectApplicationContainer(true);
}
else service.setInjectApplicationContainer(false);

if(method[j].isAnnotationPresent(InjectCookieManager.class))
{
Parameter parameters[]=method[j].getParameters();
int cookieManagerParameterIndexes[]=new int[parameters.length];
length=0;
int ii=0, jj=0;
for(Parameter p:parameters)
{
if(p.getType().equals(com.hypertech.helloboot.pojo.CookieManager.class))
{
cookieManagerParameterIndexes[ii]=jj;
ii++;
length++;
}
jj++;
}
service.setCookieManagerParameterIndexes(cookieManagerParameterIndexes);
service.setInjectCookieManager(true);
}
else service.setInjectCookieManager(false);

/*
if(method[j].isAnnotationPresent(InjectContextDirectory.class)) service.setInjectContextDirectory(true);
else service.setInjectContextDirectory(false);
*/

if(method[j].isAnnotationPresent(Forward.class))
{
Forward forwardAnnotation=method[j].getAnnotation(Forward.class);
String resource=forwardAnnotation.resource();
if(resource.equals("")) service.setForwardTo(null);
else service.setForwardTo(resource);
service.setIsForwarding(true);
}
else
{
service.setIsForwarding(false);
}
if(method[j].isAnnotationPresent(Produces.class))
{
Produces producesAnnotation=method[j].getAnnotation(Produces.class);
service.setResponseType(producesAnnotation.responseType());
}
else
{
service.setResponseType(null);
}
service.setParameters(method[j].getParameterTypes());
service.setReturnType(method[j].getReturnType());
service.setMethod(method[j]);
serviceModule.setAutoWiredFields(autoWiredFields);
serviceModule.setServiceClass(serviceClass);
fullPath=service.getPath();
services.put(fullPath, service); //add service to service module map
}//inner loop
//pass model to setServiceModule() of Model
serviceModule.setServices(services);
model.addServiceModule(serviceModule);
//System.out.println(model.getServiceModule(fullPath).getService(fullPath).isGetAllowed());
}//Outer loop
servletContext.setAttribute(Model.ID, model);
}catch(Exception e)
{
e.printStackTrace();
}
}//analyzeService

}