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
    } else if ("deletePerson".equals(actionStr)) {
        try {
            long personId = Long.parseLong(personIdReq);
            if (serviceFacade.deletePerson(personId)) {
                message = "SUCCESS: person deleted";
            };
        } catch (Exception e) {
            errorMessage = "problem deleting person " + e.getMessage();
        }
    } else if ("arrived".equals(actionStr)) {
        long personId = Long.parseLong(personIdReq);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        message = "SUCCESS: arrived " + dateStr;
        serviceFacade.changeStatus("arrived", personId, dateStr);
    } else if ("leaving".equals(actionStr)) {
        long personId = Long.parseLong(personIdReq);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        message = "SUCCESS: leaving " + dateStr;
        serviceFacade.changeStatus("leaving", personId, dateStr);
    } else if ("extend".equals(actionStr)) {
        long personId = Long.parseLong(personIdReq);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        message = "SUCCESS: extended " + dateStr;
        serviceFacade.changeStatus("extended", personId, dateStr);
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
            <p> Create Nurse/Patient</p>
            <button type="submit"> Create </button>
        </form>
        <form action="./adminTools.jsp">
            <p>Create Appointment</p>
            <input type="hidden" name="action" value="createAppointment">
            <button type="submit"> Create </button>
        </form>
        <% if (serviceFacade.findNurses().isEmpty()) {
            } else {%>
        <h2>Nurses Status</h2>
        <table border ="1">    
            <tr>
                <th>Name</th>
                <th>Location</th>
                <th>Status</th>
                <th></th>
                <th></th>
                <th></th>

            </tr>
            <%
                for (Person person : serviceFacade.findNurses()) {
                    if ("active".equals(person.getActive())) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        //"2020-12-31 14:59"
                        Date currentT = new Date();
            %>
            <tr>
                
                <td><%=person.getFirstName()%> <%=person.getSecondName()%></td>
                <td><%=person.getAddress()%></td>
                <td><%=person.getStatus()%></td>
                <%
                    if (person.getClockIn().equals("NA")) {

                    } else {
                        Date checkTime = format.parse(person.getClockIn());
                        List <String> sList = serviceFacade.checkIfLate(currentT, checkTime);
                        String late = sList.get(0);
        if (late.equals("late")) {%>
                <td bgcolor="red"><%=person.getClockIn()%></td>
                <% } else {%>
                <td><%=person.getClockIn()%></td><%}%>
                <%}%>
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
                    <form action="mainScreen.jsp" method="post">
                        <input type="hidden" name="action" value="extend">
                        <input type="hidden" name="personId" value="<%=person.getId()%>">
                        <input type="submit" value="Okay">
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

        <% if (serviceFacade.findAllAppointments().isEmpty()) {

            } else {%>
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
                <td><%=appointment.getPersonA().getFirstName()%></td>
                <td><%=appointment.getPersonB().getFirstName()%></td>
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