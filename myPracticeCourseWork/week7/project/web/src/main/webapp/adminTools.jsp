<%-- 
    Document   : adminTools.jsp
    Created on : 20-Nov-2019, 09:44:44
    Author     : markhartop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String errorMessage = "";
    String message = "";
    
    String actionStr = request.getParameter("action");
    String workerNameStr = request.getParameter("workerName"); 
    String workerIdStr = request.getParameter("workerId");
    
    
    if (actionStr == null || actionStr.isEmpty()) {
        // do nothing

    } else if ("addWorker".equals(actionStr)) {
        // put your actcreateWorkerions here
        message = "SUCCESS: new worker worked with name " + workerNameStr + " " + workerIdStr;
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
        
        <h2>Create New Worker</h2>
        <form action="./adminTools.jsp" method="post">
            <input type="hidden" name="action" value="addWorker">
            Enter New Worker Name: <input type="text" name="workerName">
            Enter New Worker Id: <input type="text" name="workerId">
            <button type="sumbit">Create Worker</button>
        </form>
        <form action="./testHeartbeat.jsp">
            <button type="submit">HOME</button>
        </form>
    </body>
</html>
