package com.hypertech.helloboot.pojo;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Model extends HttpServlet
{
public static final String ID;
private static ServletContext servletContext;
private HashMap<String, ServiceModule> serviceModules; // UUID, serviceModule
private HashMap<String,String> serviceModuleKeys; //  '/employee/add', UUID
static
{
ID=UUID.randomUUID().toString();
}
public Model(ServletContext servletContext)
{
this.servletContext=servletContext;
serviceModules=new HashMap<>();
serviceModuleKeys=new HashMap<>();
}
public void addServiceModule(ServiceModule serviceModule)
{
String serviceModuleKey=UUID.randomUUID().toString();
serviceModules.put(serviceModuleKey,serviceModule);
Map<String,Service> services=serviceModule.getServices();
Set<String> serviceKeys=services.keySet();
String serviceModulePath=serviceModule.getPath();
String servicePath;
for(String key:serviceKeys)
{
servicePath=serviceModulePath+key;
serviceModuleKeys.put(servicePath,serviceModuleKey);
}
}
public ServiceModule getServiceModule(String requestPath)
{
String serviceModuleKey=serviceModuleKeys.get(requestPath);
if(serviceModuleKey==null) return null;
return this.serviceModules.get(serviceModuleKey);
}
public ContextDirectory getContextDirectory()
{
return new ContextDirectory(this.servletContext.getRealPath("/"));
}
public boolean containsPath(String path)
{
Object isPathExists=getServiceModule(path);
if(isPathExists==null) return false;
else return true;
}
}