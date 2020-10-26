<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <style>
        .box_shadow {
            box-shadow: 4px 8px 12px grey;
        }
    </style>
    <script type='text/javascript'>
        $(document).ready(function () {
        });
    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/InitDashboardAction.js"/>'></script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<div class="content-wrapper">
    <section class="content-header">
        <h1>
            Dashboard
        </h1>
    </section>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-3">
                                <select class="form-control select2" onchange="setAllCount()">
                                    <option value="">[Select One]</option>
                                    <option value="2020">2020</option>
                                    <option value="2021">2021</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 50px; height: 50px; margin-top: 18px"
                                            src="<s:url value="/pages/images/logo-dokter.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Rawat Jalan</span>
                                        <span class="info-box-number"><span id="sum_rj"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 30px; height: 50px; margin-top: 18px"
                                            src="<s:url value="/pages/images/logo-perawat.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">IGD</span>
                                        <span class="info-box-number"><span id="sum_igd"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix visible-sm-block"></div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 50px; height: 50px; margin-top: 18px"
                                            src="<s:url value="/pages/images/logo-poli.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Rawat Inap</span>
                                        <span class="info-box-number"><span id="sum_ri"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 50px; height: 50px; margin-top: 18px"
                                            src="<s:url value="/pages/images/logo-pasien.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Telemedic</span>
                                        <span class="info-box-number"><span id="sum_telemedic"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<script type='text/javascript'>
    function setAllCount() {
        dwr.engine.setAsync(true);
        InitDashboardAction.getCountAll({
            callback: function (response) {
                $('#sum_rj').text(response.jmlRJ);
                $('#sum_ri').text(response.jmlRI);
                $('#sum_igd').text(response.jmlIGD);
                $('#sum_telemedic').text(response.jmlTelemedic);
            }
        });
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>