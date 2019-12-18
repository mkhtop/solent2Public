<%@page import="java.util.ArrayList"%>
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

    Person searchResult = new Person();
    List<Person> pList = new ArrayList<>();

    //Person searchResult = serviceFacade.findByName(firstName, secondName).get(0);
    // basic error checking before making a call
    if (actionStr == null || actionStr.isEmpty()) {
        // do nothing

    } else if ("search".equals(actionStr)) {
        if (fNameStr.isEmpty() || sNameStr.isEmpty()) {
            errorMessage = "Please enter Nurse Names";
        } else {
            pList = serviceFacade.findByName(fNameStr, sNameStr);
            if (pList.isEmpty()) {
                errorMessage = "no nurse found";
            } else {
                searchResult = pList.get(0);
            }
        }
    } else if ("arrived".equals(actionStr)) {
        // put your actions here
        long personId = Long.parseLong(idStr);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        serviceFacade.changeStatus("arrived", personId, dateStr);
        pList = serviceFacade.findByName(fNameStr, sNameStr);
        if (pList.isEmpty()) {
            errorMessage = "no nurse found";
        } else {
            searchResult = pList.get(0);
        }
    } else if ("leaving".equals(actionStr)) {
        long personId = Long.parseLong(idStr);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        serviceFacade.changeStatus("leaving", personId, dateStr);
        pList = serviceFacade.findByName(fNameStr, sNameStr);
        if (pList.isEmpty()) {
            errorMessage = "no nurse found";
        } else {
            searchResult = pList.get(0);
        }
    } else if ("extend".equals(actionStr)) {
        long personId = Long.parseLong(idStr);
        Date clocked = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
        String dateStr = format.format(clocked);
        serviceFacade.changeStatus("I'm okay", personId, dateStr);
        pList = serviceFacade.findByName(fNameStr, sNameStr);
        if (pList.isEmpty()) {
            errorMessage = "no nurse found";
        } else {
            searchResult = pList.get(0);
        }
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
        <form method="get">
            First Name: <input name="fName"><br><br>
            Second Name: <input name="sName"><br><br>
            <button type="submit" name="action" value="search">Search</button>
        </form>
        <% if (fNameStr == null) {
            } else {
                for (Person p : serviceFacade.findByName(fNameStr, sNameStr)) {
        %>
        <h2>Search Result</h2>
        <table border ="1">
            <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Status</th>
                <th></th>
            </tr>

            <tr>
                <td><%=p.getId()%></td>
                <td><%=p.getFirstName()%></td>
                <td><%=p.getSecondName()%></td>
                <td><%=p.getStatus()%></td>
                <td>
                    <form action="clientNurse.jsp?fName=<%=p.getFirstName()%>&sName=<%=p.getSecondName()%>" method="post">
                        <input type="hidden" name="action" value="arrived">
                        <input type="hidden" name="id" value="<%=p.getId()%>">
                        <input type="submit" value="Arrived">
                    </form>
                    <form action="clientNurse.jsp?fName=<%=p.getFirstName()%>&sName=<%=p.getSecondName()%>" method="post">
                        <input type="hidden" name="action" value="leaving">
                        <input type="hidden" name="id" value="<%=p.getId()%>">
                        <input type="hidden" name="fName" value="<%=p.getFirstName()%>">
                        <input type="hidden" name="sName" value="<%=p.getSecondName()%>">
                        <input type="submit" value="Leaving">
                    </form>
                    <form action="clientNurse.jsp?fName=<%=p.getFirstName()%>&sName=<%=p.getSecondName()%>" method="post">
                        <input type="hidden" name="action" value="extend">
                        <input type="hidden" name="id" value="<%=p.getId()%>">
                        <input method="get" type="hidden" name="fName" value="<%=p.getFirstName()%>">
                        <input method="get" type="hidden" name="sName" value="<%=p.getSecondName()%>">
                        <input type="submit" value="Okay">
                    </form>
                </td>
            </tr>
        </table>
        <% SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //"2020-12-31 14:59"
            Date currentT = new Date();
            Date checkTime = format.parse(p.getClockIn());
                        List<String> sList = new ArrayList<>();
                        sList = serviceFacade.checkIfLate(currentT, checkTime);
                        String late = sList.get(0);
                if (late.equals("late")) {%>
        <h2>Last Update: <%=p.getClockIn()%> LATE</h2>
        <% } else {%>
        <h2>Last Update: <%=p.getClockIn()%> ON TIME</h2><%
                        }
                    }

                }

           


        %>

        <!--
             <form action="./clientNurse.jsp" method="post">
                 <p>Id <input name="id"></p>
                 <button type="submit" name="action" value="on">On Site</button>
                 <button type="submit" name="action" value="leaving">Leaving Site</button>
                 <button type="submit" name="action" value="reset">Reset</button>
             </form>
        -->
    </body>
</html>