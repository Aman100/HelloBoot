package com.hypertech.helloboot.pojo;
import java.lang.reflect.*;
import java.util.*;
import com.hypertech.helloboot.annotations.*;
import com.hypertech.helloboot.interfaces.*;
import com.hypertech.helloboot.pools.*;
import com.hypertech.helloboot.pojo.*;
import com.hypertech.helloboot.utility.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ServiceModule implements java.io.Serializable
{
private String path;
private Class serviceClass;
private Method requestInjectorMethod; //boolean
private Method sessionInjectorMethod; //boolean 
private Method applicationInjectorMethod; //boolean lete to
private Method cookieManagerInjectorMethod; //boolean 
private Method contextDirectoryInjectorMethod; //boolean
private Field requestContainerField;
private Field sessionContainerField;
private Field applicationContainerField;
private Field cookieManagerField;
private Field contextDirectoryField;
private boolean allowGet;
private boolean allowPost;
private boolean isSecured;
private Guard guard;
private Map<String,Service> services;
private List<AutoWiredField> autoWiredFields;
public void setPath(String path)
{
this.path=path;
}
public String getPath()
{
return this.path;
}
public void setServiceClass(Class serviceClass)
{
this.serviceClass=serviceClass;
}
public Class getServiceClass()
{
return this.serviceClass;
}
public void setApplicationInjectorMethod(Method applicationInjectorMethod)
{
this.applicationInjectorMethod=applicationInjectorMethod;
}
public Method getApplicationInjectorMethod()
{
return this.applicationInjectorMethod;
}
public void setSessionInjectorMethod(Method sessionInjectorMethod)
{
this.sessionInjectorMethod=sessionInjectorMethod;
}
public Method getSessionInjectorMethod()
{
return this.sessionInjectorMethod;
}
public void setRequestInjectorMethod(Method requestInjectorMethod)
{
this.requestInjectorMethod=requestInjectorMethod;
}
public Method getRequestInjectorMethod()
{
return this.requestInjectorMethod;
}
public void setCookieManagerInjectorMethod(Method cookieManagerInjectorMethod)
{
this.cookieManagerInjectorMethod=cookieManagerInjectorMethod;
}
public Method getCookieManagerInjectorMethod()
{
return this.cookieManagerInjectorMethod;
}
public void setContextDirectoryInjectorMethod(Method contextDirectoryInjectorMethod)
{
this.contextDirectoryInjectorMethod=contextDirectoryInjectorMethod;
}
public Method getContextDirectoryInjectorMethod()
{
return this.contextDirectoryInjectorMethod;
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
public void setServices(Map<String,Service> services)
{
this.services=services;
}
public Map<String,Service> getServices()
{
return this.services;
}
public void setAutoWiredFields(List<AutoWiredField> autoWiredFields)
{
this.autoWiredFields=autoWiredFields;
}
public List<AutoWiredField> getAutoWiredFields()
{
return this.autoWiredFields;
}
public Service getService(String servicePath)
{
return this.services.get(servicePath);
}
public void setApplicationContainerField(Field applicationContainerField)
{
this.applicationContainerField=applicationContainerField;
}
public Field getApplicationContainerField()
{
return this.applicationContainerField;
}
public void setSessionContainerField(Field sessionContainerField)
{
this.sessionContainerField=sessionContainerField;
}
public Field getSessionContainerField()
{
return this.sessionContainerField;
}
public void setRequestContainerField(Field requestContainerField)
{
this.requestContainerField=requestContainerField;
}
public Field getRequestContainerField()
{
return this.requestContainerField;
}
public void setCookieManagerField(Field cookieManagerField)
{
this.cookieManagerField=cookieManagerField;
}
public Field getCookieManagerField()
{
return this.cookieManagerField;
}
public void setContextDirectoryField(Field contextDirectoryField)
{
this.contextDirectoryField=contextDirectoryField;
}
public Field getContextDirectoryField()
{
return this.contextDirectoryField;
}
/*
1. if block will only executes if the "ServiceClass writer" have write setter Method(s)
   eg. setApplicationContainer(---), setSessionContainer(---), setContextDirectory(---)
2. else block will only executes if the "ServiceClass writer" doesn't write setter method,
   but the writer declared Field of type desiredType
   eg. RequestContainer, ContextDirecotry, CookieManager
*/
public ServiceController getServiceModuleObject(ServletContext servletContext,HttpServletRequest request,HttpServletResponse response,Service service) throws Throwable
{
Model model=(Model)servletContext.getAttribute(Model.ID);
ServiceController serviceObject;
serviceObject=ServiceObjectPool.get(this.serviceClass);
if(requestInjectorMethod!=null) requestInjectorMethod.invoke(serviceObject,new RequestContainer(request));
else if(requestContainerField!=null) requestContainerField.set(serviceObject,new RequestContainer(request));
if(sessionInjectorMethod!=null) sessionInjectorMethod.invoke(serviceObject,new SessionContainer(request.getSession()));
else if(sessionContainerField!=null) sessionContainerField.set(serviceObject, new SessionContainer(request.getSession()));
if(applicationInjectorMethod!=null) applicationInjectorMethod.invoke(serviceObject, new ApplicationContainer(servletContext));
else if(applicationContainerField!=null) applicationContainerField.set(serviceObject, new ApplicationContainer(servletContext));
if(cookieManagerInjectorMethod!=null) cookieManagerInjectorMethod.invoke(serviceObject, new CookieManager(request,response));
else if(cookieManagerField!=null) cookieManagerField.set(serviceObject,new CookieManager(request,response));
if(contextDirectoryInjectorMethod!=null) contextDirectoryInjectorMethod.invoke(serviceObject, model.getContextDirectory());
else if(contextDirectoryField!=null) contextDirectoryField.set(serviceObject,model.getContextDirectory());
for(AutoWiredField awf:autoWiredFields)
{
String name=awf.getName();
Field field=awf.getField();
Object object=null;
if(name.length()>0)
{
String paramValues[]=request.getParameterValues(name);
if(paramValues!=null) object=parseParamValues(paramValues, field.getType());
if(object==null) object=request.getAttribute(name);
if(object==null) object=request.getSession().getAttribute(name);
if(object==null) object=servletContext.getAttribute(name);
}
else
{
/*
if field represents a string then extract the first string type
from parameters and assign it to object variable
if(field.getType().getSimpleName().equals("String"))
{
object=queryStringMap.values().toArray()[0];
}
*/
if(object==null)
{
Enumeration<String> enm=request.getAttributeNames();
while(enm.hasMoreElements())
{
Object value=request.getAttribute(enm.nextElement());
if(field.getType().isInstance(value)) //base k pointer main derived k object ka address store ho sakta hain waali baat
{
object=value;
break;
}
}
}
if(object==null)
{
Enumeration<String> enm=request.getSession().getAttributeNames();
while(enm.hasMoreElements())
{
Object value=request.getSession().getAttribute(enm.nextElement());
if(field.getType().isInstance(value))
{
object=value;
break;
}
}
}
if(object==null)
{
Enumeration<String> enm=servletContext.getAttributeNames();
while(enm.hasMoreElements())
{
Object value=servletContext.getAttribute(enm.nextElement());
if(field.getType().isInstance(value))
{
object=value;
break;
}
}
}
}
if(object!=null) //primitive hain to chhod do, don't set null to primitive
{
field.setAccessible(true);
field.set(serviceObject,object);
}
}//Loop
return serviceObject;
}
public Object parseParamValues(String []paramValues, Class type)
{
try
{
//if name is not in Parameter String or if the name is an empty string
if(paramValues==null || paramValues.length==0) return null;
if(type.isArray()==false)
{
String paramValue=paramValues[0];
return Utility.parseParamValue(paramValue, type);
}
//something for object array
if(type.equals(Object[].class)) 
{
//return paramValues OR
Object object[]=new Object[paramValues.length];
int i=0;
for(String value:paramValues)
{
object[i]=value;
i++;
}
return object;
}
if(type.equals(String[].class)) return paramValues;
if(type.equals(Long[].class))
{
Long ll[]=new Long[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Long.parseLong(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
Long lll[]=new Long[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(long[].class))
{
long ll[]=new long[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Long.parseLong(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
long lll[]=new long[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(Integer[].class))
{
Integer ll[]=new Integer[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Integer.parseInt(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
Integer lll[]=new Integer[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(int[].class))
{
int ll[]=new int[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Integer.parseInt(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
int lll[]=new int[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(Short[].class))
{
Short ll[]=new Short[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Short.parseShort(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
Short lll[]=new Short[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(short[].class))
{
short ll[]=new short[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Short.parseShort(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
short lll[]=new short[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(Byte[].class))
{
Byte ll[]=new Byte[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Byte.parseByte(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
Byte lll[]=new Byte[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(byte[].class))
{
byte ll[]=new byte[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Byte.parseByte(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
byte lll[]=new byte[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(double[].class))
{
double ll[]=new double[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Double.parseDouble(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
double lll[]=new double[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(Double[].class))
{
Double ll[]=new Double[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Double.parseDouble(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
Double lll[]=new Double[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(Float[].class))
{
Float ll[]=new Float[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Float.parseFloat(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
Float lll[]=new Float[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(float[].class))
{
float ll[]=new float[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Float.parseFloat(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
float lll[]=new float[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(Boolean[].class))
{
Boolean ll[]=new Boolean[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Boolean.parseBoolean(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
Boolean lll[]=new Boolean[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(boolean[].class))
{
boolean ll[]=new boolean[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
try
{
ll[i]=Boolean.parseBoolean(paramValue);
i++;
correct++;
}catch(NumberFormatException nfe) {}
}
boolean lll[]=new boolean[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}
if(type.equals(Character[].class))
{
Character ll[]=new Character[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
if(paramValue!=null || paramValue.length()>0)
ll[i]=paramValue.charAt(0);
i++;
correct++;
}
Character lll[]=new Character[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}//IF
if(type.equals(char[].class))
{
char ll[]=new char[paramValues.length];
int i=0;
int correct=0;
for(String paramValue:paramValues)
{
if(paramValue!=null || paramValue.length()>0)
ll[i]=paramValue.charAt(0);
i++;
correct++;
}
char lll[]=new char[correct];
for(i=0;i<correct;i++) lll[i]=ll[i];
return lll;
}//IF
}catch(Exception exception)
{
return null;
}
return null;
}
}