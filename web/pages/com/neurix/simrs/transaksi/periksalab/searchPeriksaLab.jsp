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

        function resetField(){
            $('#no_bpjs, #id_pasien, #no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #tanggal_lahir, #jalan, #suku, #agama, #poli, #dokter, #penjamin, #provinsi11, #kabupaten11, #kecamatan11, #desa11, #provinsi, #kabupaten, #kecamatan, #desa').val('');
        }


    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Periksa Lab Pasien
            <small>e-HEALTH</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Periksa Lab Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="checkupDetailForm" method="post" namespace="/checkup" action="search_checkup.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Pasien</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="headerDetailCheckup.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="headerDetailCheckup.nama"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Jenis Kelamin</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'L':'Laki-laki','P':'Perempuan'}" cssStyle="margin-top: 7px"
                                                  id="jenis_kelamin" name="headerDetailCheckup.jenisKelamin"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Poli</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'01':'Poli Anak','02':'Poli Mata','03':'Poli Ibu','04':'Poli Umum'}" cssStyle="margin-top: 7px"
                                                  id="poli" name="headerDetailCheckup.IdPelayanan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'0':'Antrian','1':'Periksa','2':'Rujuk','3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status" name="headerDetailCheckup.statusPeriksa"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="headerDetailCheckup.stTglFrom" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="headerDetailCheckup.stTglTo" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="checkupDetailForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_checkupdetail.action">
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
                                                <img border="0" style="width: 150px; height: 150px" src="<s:url value="/pages/images/spinner.gif"/>" name="image_indicator_write">
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Periksa Lab Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Masuk</td>
                                <td>Nama</td>
                                <td>Jenis Rawat</td>
                                <td>Pemeriksaan</td>
                                <td>Process</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfUsers">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td><s:property value="namaPelayanan"/></td>
                                    <td>
                                        <s:url var="detail" namespace="/checkup" action="view_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <sj:a onClickTopics="showDialogUser" href="%{detail}">
                                            <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" style="cursor: pointer">
                                        </sj:a>

                                        <s:url var="edit" namespace="/checkup" action="edit_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <s:a href="%{edit}">
                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">
                                        </s:a>

                                        <s:url var="delete" namespace="/checkup" action="delete_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <sj:a href="%{delete}">
                                            <img border="0" src="<s:url value="/pages/images/if_delete.ico"/>" style="cursor: pointer; height: 25px; width: 25px">
                                        </sj:a>
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