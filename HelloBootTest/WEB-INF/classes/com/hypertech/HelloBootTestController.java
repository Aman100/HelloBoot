package com.hypertech;
import com.hypertech.helloboot.annotations.*;
import com.hypertech.helloboot.interfaces.*;
import com.hypertech.helloboot.pojo.*;

class UserDetails
{
public String username;
public String password;
}

@Get()
@Path(value="/employee")
public class HelloBootTestController implements ServiceController
{
RequestContainer requestContainer;
SessionContainer sessionContainer;
ApplicationContainer applicationContainer;
public void init()
{
System.out.println("INSIDE init");
}

@Get()
@Path(value="/add")
@Forward(resource="")
public String index()
{
return "/index.jsp";
}

@Get()
@Path(value="/registrationForm")
@Forward(resource="")
public String registrationForm()
{
return "/registrationForm.jsp";
}

@Get()
@Path(value="/loginForm")
@Forward(resource="")
public String loginForm()
{
return "/login.jsp";
}

@Post()
@Path(value="/submitForm")
@FileConfiguration(inputName="docname")
@Forward(resource="")
public String submitForm(UploadedFile uploadedFile, @Property(value="name")String name, @Property(value="age")int age, @Property(value="gender")String gender, @Property(value="username")String username, @Property(value="password")String password)
{
applicationContainer.setAttribute("uploadedFile",uploadedFile);
Employee employee=new Employee();
System.out.println("SUBMIT NAME : " + name);
employee.name=name;
employee.age=age;
employee.gender=gender;
UserDetails userDetails=new UserDetails();
userDetails.username=username;
userDetails.password=password;
applicationContainer.setAttribute("employee",employee);
applicationContainer.setAttribute("userDetails",userDetails);
System.out.println("NAME : " + name);
System.out.println(age);
System.out.println(gender);
return "/login.jsp";
}

@Get()
@Path(value="/login")
@Forward(resource="")
@InjectCookieManager()
public String login(CookieManager cookieManager, @Property(value="username")String username, @Property(value="password")String password)
{
String sessionId=sessionContainer.getId();
if(cookieManager.getCookie("sessionId")!=null && cookieManager.getCookie("sessionId").equals(sessionId)) return "/homepage.jsp";
UserDetails userDetails=(UserDetails)applicationContainer.getAttribute("userDetails");
if((userDetails.username.equals(username) && userDetails.password.equals(password)))
{
cookieManager.setCookie("sessionId",sessionId);
return "/homepage.jsp";
}
else
{
System.out.println("ACCESS DENIED");
requestContainer.setAttribute("error","Invalid Username or Password");
return "/login.jsp";
}
}

@Get()
@Path(value="/view")
@Produces(responseType=ResponseType.FILE)
public ResponseFile ViewFile(@AutoWired(name="uploadedFile") UploadedFile uploadedFile)
{
ResponseFile responseFile=new ResponseFile();
responseFile.setFile(uploadedFile.getFile());
responseFile.setMimeType("application/pdf");
responseFile.setName("COOL");
responseFile.setShouldSave(false);
return responseFile;
}

@Get()
@Path(value="/update")
@Forward(resource="")
public String update()
{
return "/updateEmployee.jsp";
}

@Post()
@Path(value="/updateEmployee")
@Forward(resource="")
public String UpdateEmployee(@AutoWired(name="employee")Employee employee,@Property(value="name")String name, @Property(value="age")int age, @Property(value="gender")String gender)
{
employee.name=name;
System.out.println("NAME : " + name);
employee.age=age;
employee.gender=gender;
System.out.println("EmployeeNAME : " + employee.name);
applicationContainer.setAttribute("employee",employee);
return "/homepage.jsp";
}

@Get()
@Path(value="/getEmployee")
@Produces(responseType=ResponseType.JSON)
@InjectApplicationContainer()
public Employee getEmployeeAsJSON(ApplicationContainer ac)
{
Employee e=(Employee)ac.getAttribute("employee");
return e;
}


@Get()
@Path(value="/logout")
@Forward(resource="")
public String logout()
{
sessionContainer.endSession();
requestContainer.removeAttribute("error");
return "/index.jsp";
}

@Get()
@Path(value="/verify")
@Forward(resource="/homepage")
public void verify()
{
System.out.println("DONE DONE");
}

@Get()
@Path(value="/homepage")
@Forward(resource="")
public String homepage()
{
return "/homepage.jsp";
}
}