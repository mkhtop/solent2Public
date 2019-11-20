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
    String usernameStr = request.getParameter("username");
    String locationStr = request.getParameter("location");

    // basic error checking before making a call
    if (actionStr == null || actionStr.isEmpty()) {
        // do nothing

    } else if ("on".equals(actionStr)) {
        // put your actions here
        serviceFacade.arrived(usernameStr, locationStr);
    } else if ("leaving".equals(actionStr)){
        //action here
    } else if ("reset".equals(actionStr)) {
        // put your actions here
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
        <h2>Controls</h2>
        <form>
            <p>Username:<input name="username"></p>
            <p>Location:<input name="location"></p>
            <button type="submit" name="action" value="on">On Site</button>
            <button type="submit" name="action" value="leaving">Leaving Site</button>
            <button type="submit" name="action" value="reset">Reset</button>
        </form>
        
    </body>
</html>
