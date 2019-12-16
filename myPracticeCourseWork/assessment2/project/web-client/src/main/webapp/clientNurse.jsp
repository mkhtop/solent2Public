<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.solent.com504.project.model.dto.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="org.solent.com504.project.impl.webclient.WebClientObjectFactory"%>
<%@page import="org.solent.com504.project.model.service.ServiceFacade"%>


<%
    // used to place error message at top of page 
    String errorMessage = "";
    String message = "";
    // used to set html header autoload time. This automatically refreshes the page
    // Set refresh, autoload time every 20 seconds
    response.setIntHeader("Refresh", 20);
    // accessing service 
    ServiceFacade serviceFacade = (ServiceFacade) WebClientObjectFactory.getServiceFacade();
    // accessing request parameters
    String actionStr = request.getParameter("action");
    String idStr = request.getParameter("id");
    String fNameStr = request.getParameter("fName");
    String sNameStr = request.getParameter("sName");
    Person searchResult = null;
    String resultName = null;
       
    // basic error checking before making a call
    if (actionStr == null || actionStr.isEmpty()) {
        // do nothing
        
    } else if ("search".equals(actionStr)) {
        // put your actions here
        searchResult = serviceFacade.findByName(fNameStr, sNameStr);
        resultName = searchResult.getFirstName() + " " + searchResult.getSecondName();
    } else if ("on".equals(actionStr)) {
        // put your actions here
        long personId = Long.parseLong(idStr);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        serviceFacade.changeStatus("arrived", personId, dateStr);
    } else if ("leaving".equals(actionStr)){
        long personId = Long.parseLong(idStr);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        serviceFacade.changeStatus("leaving", personId, dateStr);
    } else if ("reset".equals(actionStr)) {
        long personId = Long.parseLong(idStr);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        serviceFacade.changeStatus("okay", personId, dateStr);
    } else {
        errorMessage = "ERROR: page called for unknown action";
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Client Page for heart beat</title>
    </head>
    <body>
        <!-- works with http://localhost:8080/basicfacadeweb/testHeartbeat.jsp -->
        <H1>Client JSP Page for heart beat</H1>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
        <div style="color:green;"><%=message%></div>

        <p>The time is: <%= new Date().toString()%> (note page is auto refreshed every 20 seconds)</p>
        
        <h2>Find Nurse:</h2>
        <form method="post">
            First Name: <input name="fName">
            Second Name: <input name="sName">
            <button type="submit" name="action" value="search">Search</button>
        </form>
        <% if (searchResult == null) {
            } else if ("active".equals(resultName)) {
            %>
        <h2>Search Result</h2>
        <table border ="1">
            <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Status</th>
            </tr>
            
            <tr>
                <td><%=searchResult.getId()%></td>
                <td><%=searchResult.getFirstName()%></td>
                <td><%=searchResult.getSecondName()%></td>
                <td><%=searchResult.getStatus()%></td>
            </tr>
            <%}
                //}%>
        </table>
        
   
        <form action="./clientNurse.jsp" method="post">
            <p>Id <input name="id"></p>
            <button type="submit" name="action" value="on">On Site</button>
            <button type="submit" name="action" value="leaving">Leaving Site</button>
            <button type="submit" name="action" value="reset">Reset</button>
        </form>
        
    </body>
</html>