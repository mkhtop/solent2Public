<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.solent.com504.project.model.dto.Appointment"%>
<%@page import="org.solent.com504.project.model.dto.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="org.solent.com504.project.impl.web.WebObjectFactory"%>
<%@page import="org.solent.com504.project.model.service.ServiceFacade"%>



<%
    // used to place error message at top of page 
    String errorMessage = "";
    String message = "";
    // used to set html header autoload time. This automatically refreshes the page
    // Set refresh, autoload time every 20 seconds
    response.setIntHeader("Refresh", 20);
    // accessing service 
    ServiceFacade serviceFacade = (ServiceFacade) WebObjectFactory.getServiceFacade();
    // accessing request parameters
    String actionStr = request.getParameter("action");
    String personIdReq = request.getParameter("personId");
    
    
    // basic error checking before making a call
    if (actionStr == null || actionStr.isEmpty()) {
        // do nothing
    } else if ("createAppointment".equals(actionStr)) {
        message = "SUCCESS: new appointment";
    } else if ("deletePerson".equals(actionStr)) {
        try {
            long personId = Long.parseLong(personIdReq);
            if (serviceFacade.deletePerson(personId)) {
                message = "SUCCESS: person deleted";
            };
        } catch (Exception e) {
            errorMessage = "problem deleting Book " + e.getMessage();
        }
    } else if("arrived".equals(actionStr)){
        long personId = Long.parseLong(personIdReq);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        message = "SUCCESS: arrived " + dateStr;
        serviceFacade.changeStatus("arrived", personId, dateStr);
    } else if("leaving".equals(actionStr)){
        
        long personId = Long.parseLong(personIdReq);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        Date strBack = format.parse(dateStr);
        message = "SUCCESS: leaving with date:" + strBack;
        serviceFacade.changeStatus("leaving", personId, dateStr);
    } else if("extend".equals(actionStr)){
        message = "SUCCESS: extend";
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        long personId = Long.parseLong(personIdReq);
        serviceFacade.changeStatus("extedned", personId, dateStr);
    } else {
        errorMessage = "ERROR: page called for unknown action";
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Server Page for heart beat</title>
    </head>
    <body>
        <!-- works with http://localhost:8080/basicfacadeweb/mainScreen.jsp -->
        <H1>JSP Server Page for heart beat</H1>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
        <div style="color:green;"><%=message%></div>

        <p>The time is: <%= new Date().toString()%> (note page is auto refreshed every 20 seconds)</p>

        <h2>Admin Tools</h2>
        <form action="./adminTools.jsp">
            <p> Create Worker</p>
            <button type="submit"> Create </button>
        </form>
        <form action="./adminTools.jsp">
            <p>Create Appointment</p>
            <input type="hidden" name="action" value="createAppointment">
            <button type="submit"> Create </button>
        </form>
        <% if (serviceFacade.findNurses().isEmpty()){}
            else {%>
        <h2>Nurses Status</h2>
        <table border ="1">    
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Location</th>
                <th>Role</th>
                <th>Status</th>
                <th></th>
                <th></th>
                <th></th>
                
            </tr>
            <% 
                for (Person person : serviceFacade.findNurses()) {
                    if ("active".equals(person.getActive())) {
            %>
            <tr>
                <td><%=person.getId()%></td>
                <td><%=person.getFirstName()%></td>
                <td><%=person.getAddress()%></td>
                <td><%=person.getRole()%></td>
                <td><%=person.getStatus()%></td>        
                <td><%=person.getClockIn()%></td>

                <td>
                    <form action="mainScreen.jsp" method="post">
                        <input type="hidden" name="action" value="arrived">
                        <input type="hidden" name="personId" value="<%=person.getId()%>">
                        <input type="submit" value="Arrived">
                    </form>
                    <form action="mainScreen.jsp" method="post">
                        <input type="hidden" name="action" value="leaving">
                        <input type="hidden" name="personId" value="<%=person.getId()%>">
                        <input type="submit" value="Leaving">
                    </form>
                    <form action="mainScreen.jsp" methos="post">
                        <input type="hidden" name="action" value="extend">
                        <input type="hidden" name="personId" value="<%=person.getId()%>">
                        <input type="submit" value="Extend Stay">
                    </form>
                </td>
                <td>
                    <form action="adminTools.jsp">
                        <input type="hidden" name="action" value="modifyPerson"> 
                        <input type="hidden" name="personId" value="<%=person.getId()%>"> 
                        <input type="submit" value="Modify Person">
                    </form>
                    <form action="mainScreen.jsp">
                        <input type="hidden" name="action" value="deletePerson"> 
                        <input type="hidden" name="personId" value="<%=person.getId()%>"> 
                        <input type="submit" value="Delete Person">
                    </form>
                </td>
            </tr>
            <% }
                }
            %> 
        </table>
        <% } %>
                       
        <% if (serviceFacade.findAllAppointments().isEmpty()){
            
        }else {%>
        <h2>Appointments</h2>
        <table border ="1">
            <tr>
                <th>Nurse</th>
                <th>Patient</th>
                <th>Location</th>
                <th>Time and Date</th>
            </tr>
            <tr>
            <%for (Appointment appointment : serviceFacade.findAllAppointments()) {
                    if ("active".equals(appointment.getActive())) {
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd-MM-yyyy");
                        //"2020-12-31 14:59"
                        String dateStr = format.format(appointment.getAppDate());
            %>
            <tr>
                <td><%=appointment.getPersonA().getFirstName() %></td>
                <td><%=appointment.getPersonB().getFirstName() %></td>
                <td><%=appointment.getPersonB().getAddress()%></td>
                <td><%=dateStr%></td>
            </tr>
            <%}
                }%>
            </tr>
        </table>
            <% }%>
    </body>
</html>