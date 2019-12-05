<%-- 
    Document   : adminTools.jsp
    Created on : 20-Nov-2019, 09:44:44
    Author     : markhartop
--%>
<%@page import="org.solent.com504.project.model.dto.Person"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="org.solent.com504.project.impl.web.WebObjectFactory"%>
<%@page import="org.solent.com504.project.model.service.ServiceFacade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String errorMessage = "";
    String message = "";
    
    String actionStr = request.getParameter("action");
    String workerFNameStr = request.getParameter("workerFName"); 
    String workerSNameStr = request.getParameter("workerSName");
    String roleStr = request.getParameter("role");
    String addressStr = request.getParameter("address");
    String nurseStr = request.getParameter("nurse");
    String patientStr = request.getParameter("patient");
    
    ServiceFacade serviceFacade = (ServiceFacade) WebObjectFactory.getServiceFacade();
    
    if (actionStr == null || actionStr.isEmpty()) {
        // do nothing

    } else if ("addWorker".equals(actionStr)) {
        // put your actcreateWorkerions here
        message = "SUCCESS: new worker worked with name " + workerFNameStr + " " + workerSNameStr + " " + roleStr + " " + addressStr;
        serviceFacade.addPerson(workerFNameStr, workerSNameStr, roleStr, addressStr);
    } else if("addAppointment".equals(actionStr)){
        message = "SUCCESS: Appointment with " + nurseStr + " " + patientStr;
    } else {
        errorMessage = "ERROR: has not worked";
    }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div style="color:red;"><%=errorMessage%></div>
        <div style="color:green;"><%=message%></div>
        
        <h2>Create New Person</h2>
        <form action="./adminTools.jsp" method="post">
            <input type="hidden" name="action" value="addWorker">
            Person First Name: <input type="text" name="workerFName"><br>
            Person Surname Name: <input type="text" name="workerSName"><br>
            Role: <input type="radio" name="role" value="Nurse">Nurse
            <input type="radio" name="role" value="Patient">Patient<br>
            Address: <input type="text" name="address"><br>
            <button type="sumbit">Create Worker</button>
        </form>
        <h2>Create New Appointment</h2>
        <form action="./adminTools.jsp">
            <input type="hidden" name="action" value="addAppointment">
            Select Worker:<select name="nurse">
            <% for (Person person : serviceFacade.findNurses()) { %>
                <option  value=<%=person.getId()%>><%=person.getFirstName()%></option>
            <%  }  %>
            </select><br>
            Select Patient <select name="patient">
            <% for (Person person : serviceFacade.findPatients()) {%>
                <option  value=<%=person.getId()%>><%=person.getFirstName()%></option>
            <%  }  %>
            </select><br>
            <button type="sumbit">Create Appointment</button>
        </form>
        <form action="./mainScreen.jsp">
            <button type="submit">HOME</button>
        </form>
    </body>
</html>
