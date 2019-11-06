<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
<%@ include file="/pages/common/header.jsp" %>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<%--<div class="container-fluid">--%>
    <%--<div id="main">--%>
        <%--<div class="media">--%>
            <%--<div class="media-body">--%>
                <%--<table width="100%" align="center" align="center">--%>
                    <%--<tr>--%>
                        <%--<td align="center">--%>
                            <%--<div id="box">--%>

                                <%--<label class="control-label" style="font-size: 28;font-family: Calibri, Arial, sans-serif" ><strong>Welcome to Project Manajement System PLN-APB Jatim</strong></label>--%>
                                <%--<br/>--%>
                                <%--<p><label class="control-label"><small>You in Cloud now and simplify your work</small></label></p>--%>

                            <%--</div>--%>

                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<%@ include file="/pages/common/footer.jsp" %>--%>
<%--</div>--%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section  class="content-header text-center">
        <img width="20%"  border="0" src="<s:url value="/pages/images/logo-nmu.png"/>" name="image_cloud">
        <%--<h1>--%>
            <%--PT. Nusantara Medika Utama--%>
            <%--&lt;%&ndash;<small>e-HEALTH</small>&ndash;%&gt;--%>
        <%--</h1>--%>
        <%--<ol class="breadcrumb">--%>
            <%--&lt;%&ndash;<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<li class="active">Here</li>&ndash;%&gt;--%>
        <%--</ol>--%>

    </section>

    <!-- Main content -->
    <section class="content">

        <!-- Your Page Content Here -->
        <div class="callout callout-success" style="background-color: #FFF !important;">
            <%--<h3>Wellcome !</h3>--%>
            <p align="center" style="font-size: 30;">
                <div class="row text-center">
                    <%--<div class="col-md-5 text-right">--%>
                        <%--&lt;%&ndash;<img src="/hris/pages/upload/image/profile/unknown-person2.jpg" class="img-circle" alt="User Image" width="50px">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<img src="/hris/pages/images/logo-nmu.webp" width="10%" alt="User Image" width="125px">&ndash;%&gt;--%>
                    <%--</div>--%>
                <div style="color: #000;">
                    <strong>Welcome to <span style="color: #50d4a3">e-HEALTH PT. Nusantara Medika Utama</span></strong><br/>
                    You in Cloud now and simplify your work
                </div>
                </div>
               <%-- --%>
            </p>
        </div>


    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>


