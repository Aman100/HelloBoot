/*
we don't want to access this class outside the package that's why we didn't give Modifiers to 
setApplication,setHttpServletRequest,setSession (for securit purpose)
when request arrives to RequestHandlingServlet then the servlet can't access this class
that's why we put this class outside the pojo
*/
package com.hypertech.helloboot.pojo;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
public class ApplicationContainer extends HttpServlet implements java.io.Serializable
{
private String name;
private Object value;
private ServletContext servletContext;

ApplicationContainer(ServletContext servletContext)	//no-modifiers
{
this.servletContext=servletContext;
}
public ApplicationContainer getApplicationContainer() 	//it should be static
{
ServletContext sc=getServletContext();
ApplicationContainer applicationContainer=new ApplicationContainer(sc);
return applicationContainer;
}

void setApplication(ServletContext servletContext) //can be accessed within same package
{
this.servletContext=servletContext;
}

public void setAttribute(String name, Object value)
{
this.servletContext.setAttribute(name,value);
//this.name=name;
//this.value=value;
}

public Object getAttribute(String name)
{
return servletContext.getAttribute(name);
}

public void removeAttributes()
{
Enumeration<String> e=servletContext.getAttributeNames();
while(e.hasMoreElements())
{
servletContext.removeAttribute(e.nextElement());
}
}

}