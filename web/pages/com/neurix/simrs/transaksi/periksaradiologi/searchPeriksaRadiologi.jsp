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
    </style>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#penunjang_active, #periksa_radiologi').addClass('active');
            $('#penunjang_open').addClass('menu-open');
        });

    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Periksa Radiologi Pasien
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Periksa Radiologi Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="radiologiForm" method="post" namespace="/radiologi" action="search_radiologi.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Detail Checkup</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_checkup_detail" cssStyle="margin-top: 7px"
                                                     name="periksaLab.idDetailCheckup" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="periksaLab.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="periksaLab.nama"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Radiologi</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:action id="comboRadiologi" namespace="/lab"--%>
                                                  <%--name="getListRadiologi_lab"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--list="#comboRadiologi.lisOfRadiologi" id="lab"--%>
                                                  <%--name="periksaLab.idLab" listKey="idLab"--%>
                                                  <%--listValue="namaLab"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2" theme="simple"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'1':'Periksa','3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status" name="periksaLab.statusPeriksa"
                                                  headerKey="0" headerValue="Antrian"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="periksaLab.stDateFrom" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="periksaLab.stDateTo" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="radiologiForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_radiologi.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">

                                        <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Searching ...">
                                            Please don't close this window, server is processing your request ...
                                            <br>
                                            <center>
                                                <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                     src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                     name="image_indicator_write">
                                                <br>
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Periksa Radiologi Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Masuk</td>
                                <td>ID Detail Checkup</td>
                                <td>Nama Pasien</td>
                                <td>Pemeriksaan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="stCreatedDate"/></td>
                                    <td><s:property value="idDetailCheckup"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="labName"/></td>
                                    <td align="center">
                                        <s:if test='#row.approveFlag == "Y"'>
                                            <a target="_blank" href="printRadiologi_radiologi.action?id=<s:property value="idDetailCheckup"/>&lab=<s:property value="idPeriksaLab"/>">
                                                <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer; ">
                                            </a>
                                        </s:if>
                                        <s:else>
                                            <s:url var="add_periksa_radiologi" namespace="/radiologi" action="add_radiologi" escapeAmp="false">
                                                <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                <s:param name="lab"><s:property value="idPeriksaLab"/></s:param>
                                                <s:param name="ket"><s:property value="keterangan"/></s:param>
                                            </s:url>
                                            <s:a href="%{add_periksa_radiologi}">
                                                <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                            </s:a>
                                        </s:else>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script type='text/javascript'>
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>