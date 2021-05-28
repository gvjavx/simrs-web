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
            $('#igd').addClass('active');
            $('#igdTable').DataTable({
                "order": [[ 1, "asc" ]],
                "columnDefs": [
                    {"orderable": false, "targets": 0}
                ]
            });
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
            Rawat IGD Pasien
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Rawat IGD Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="igdForm" method="post" namespace="/igd" action="search_igd.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="headerDetailCheckup.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="headerDetailCheckup.namaPasien"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <s:if test="isEnabledPoli()">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Poli</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initComboPoli.listOfPelayanan"
                                                  name="headerDetailCheckup.idPelayanan" listKey="idPelayanan"
                                                  listValue="namaPelayanan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2" theme="simple"/>
                                    </div>
                                </div>
                                </s:if>
                                <s:else>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Poli</label>
                                        <div class="col-sm-4">
                                            <s:action id="initComboPoli" namespace="/checkup"
                                                      name="getComboPelayanan_checkup"/>
                                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                                      list="#initComboPoli.listOfPelayanan" id="poli"
                                                      listKey="idPelayanan"
                                                      name="headerDetailCheckup.idPelayanan"
                                                      listValue="namaPelayanan"
                                                      headerKey="007" headerValue="IGD"
                                                      cssClass="form-control select2" theme="simple" disabled="true"/>
                                        </div>
                                        <s:hidden name="headerDetailCheckup.idPelayanan"></s:hidden>
                                    </div>
                                </s:else>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'2':'Rujuk','3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status" name="headerDetailCheckup.statusPeriksa"
                                                  headerKey="1" headerValue="Periksa"
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
                                            <s:textfield id="tgl_from" name="headerDetailCheckup.stDateFrom" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="headerDetailCheckup.stDateTo" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="igdForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <%--<a href="addRawatIgd_igd.action" type="button" class="btn btn-primary"><i class="fa fa-plus"></i> Pendaftaran</a>--%>
                                        <a type="button" class="btn btn-danger" href="initForm_igd.action">
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Rawat IGD Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="igdTable" class="table table-bordered table-striped" style="font-size: 13px">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td align="center">Triase</td>
                                <td>ID Detail Checkup</td>
                                <td>No RM</td>
                                <td>Nama</td>
                                <td>Umur</td>
                                <td>Tanggal Masuk</td>
                                <td>Desa</td>
                                <td>Status</td>
                                <td align="center">Jenis Pasien</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td align="center">
                                        <script>
                                            var warna = '<s:property value="triase"/>';
                                            if(warna != ''){
                                                warna = warna.split("|")[1];
                                                document.write('<i class="fa fa-square fa-2x" style="color: '+warna+'"></i>');
                                            }
                                        </script>
                                    </td>
                                    <td><s:property value="idDetailCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="umur"/></td>
                                    <td><s:property value="formatTglMasuk"/></td>
                                    <td><s:property value="desa"/></td>
                                    <td><s:property value="statusPeriksaName"/></td>
                                    <td align="center">
                                        <script>
                                            document.write(changeJenisPasien('<s:property value="idJenisPeriksaPasien"/>', '<s:property value="jenisPeriksaPasien"/>'));
                                        </script>
                                    </td>
                                    <td align="center">
                                        <s:if test='#row.statusPeriksa != "3"'>
                                            <s:if test='#row.idJenisPeriksaPasien == "umum"'>
                                                <s:if test='#row.isBayar == "Y"'>
                                                    <s:url var="add_rawat_jalan" namespace="/igd" action="add_igd" escapeAmp="false">
                                                        <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{add_rawat_jalan}">
                                                        <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                                    </s:a>
                                                </s:if>
                                                <s:else>
                                                    <span class="span-warning">Uang muka belum bayar</span>
                                                </s:else>
                                            </s:if>
                                            <s:else>
                                                <s:url var="add_rawat_jalan" namespace="/igd" action="add_igd" escapeAmp="false">
                                                    <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                </s:url>
                                                <s:a href="%{add_rawat_jalan}">
                                                    <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                                </s:a>
                                            </s:else>
                                        </s:if>
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

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>