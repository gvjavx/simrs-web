<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>e-HEALTH</title>
    <link rel="shortcut icon" href="<s:url value="/pages/images/logo-pln.png"/>"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/pages/css/style.css"/>"/>
    <style type="text/css" media="all">
    </style>

</head>

<body>
<div class="container">
    <%--<header>--%>
        <%--</br></br></br></br></br></br></br></br>--%>
        <%--<center><h1><strong><b>Good Bye...</b></strong></h1></center>--%>

    <%--</header>--%>
    <br><br><br><br>
    <div style="text-align: center; width: 200px; background-color: #edcccc; padding: 20px; border-radius: 10px;color: red;margin: auto;">
        <h3>Good Bye...</h3>
    </div>
</div>


<SCRIPT>

    top.location.href = '<s:url value="/loginUser.action"/>';

</SCRIPT>


</body>
</html>