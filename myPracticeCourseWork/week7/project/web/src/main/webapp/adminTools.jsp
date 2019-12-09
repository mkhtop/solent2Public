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
    String dayInt = request.getParameter("day");
    String mnthInt = request.getParameter("month");
    String yearInt = request.getParameter("year");
    String descStr = request.getParameter("description");
    String durationInt = request.getParameter("duration");
    String hrInt = request.getParameter("hr");
    
    ServiceFacade serviceFacade = (ServiceFacade) WebObjectFactory.getServiceFacade();
    
    if (actionStr == null || actionStr.isEmpty()) {
        // do nothing

    } else if ("addWorker".equals(actionStr)) {
        // put your actcreateWorkerions here
        message = "SUCCESS: new worker worked with name " + workerFNameStr + " " + workerSNameStr + " " + roleStr + " " + addressStr;
        serviceFacade.addPerson(workerFNameStr, workerSNameStr, roleStr, addressStr);
    } else if("addAppointment".equals(actionStr)){
        long nurseId = Long.parseLong(nurseStr);
        Person nurse = serviceFacade.findById(nurseId);
        long patientId = Long.parseLong(patientStr);
        Person patient = serviceFacade.findById(patientId);
        int hr = Integer.parseInt(hrInt);
        int day = Integer.parseInt(dayInt);
        int mnth = Integer.parseInt(mnthInt);
        int year = Integer.parseInt(yearInt);
        int duration = Integer.parseInt(durationInt);
        serviceFacade.addAppointment(nurse, patient, hr, day, mnth, year, descStr, duration);
        message = "SUCCESS: Appointment with " + nurseStr + " " + patientStr + " " + dayInt + " " + mnthInt + " " + yearInt + " " + descStr + " " + durationInt;
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
            Select Nurse<select name="nurse">
            <% for (Person person : serviceFacade.findNurses()) { %>
                <option  value=<%=person.getId()%>><%=person.getFirstName()%></option>
            <%  }  %>
            </select><br>
            Select Patient: <select name="patient">
            <% for (Person person : serviceFacade.findPatients()) {%>
                <option  value=<%=person.getId()%>><%=person.getFirstName()%></option>
            <%  }  %>
            </select><br>
            Hour:<select name="hr">
                <% for (int i=8; i<27; i++){%>
                <option value=<%=i%>><%=i%></option>;
                <%}%>  
            </select>
            Day:<select name="day">
                <% for (int i=1; i<32; i++){%>
                <option value=<%=i%>><%=i%></option>;
                <%}%>  
            </select>
            Month:<select name="month">
                <% for (int i=1; i<13; i++){%>
                <option value=<%=i%>><%=i%></option>;
                <%}%>  
            </select>
            Year:<select name="year">
                <% for (int i=2019; i<2022; i++){%>
                <option value=<%=i%>><%=i%></option>;
                <%}%>  
            </select><br>
            Duration (minutes):<select name="duration">
                <% for (int i=15; i<121; i = i+15){%>
                <option value=<%=i%>><%=i%></option>;
                <%}%>  
            </select><br>
            Description: <input type="text" name="description"><br>
            <button type="sumbit">Create Appointment</button>
        </form>
        <form action="./mainScreen.jsp">
            <button type="submit">HOME</button>
        </form>
    </body>
</html>
