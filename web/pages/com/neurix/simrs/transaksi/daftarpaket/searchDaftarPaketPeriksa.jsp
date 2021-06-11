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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PaketPeriksaAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#daftar_paket').addClass('active');
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
            Pendaftaran Paket Periksa Pasien
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Paket Periksa</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="daftarpaketperiksaForm" method="post" namespace="/daftarpaket" action="searchDaftarPaket_daftarpaket.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Perusahaan</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_detail_checkup" cssStyle="margin-top: 7px"
                                                     name="paketPeriksa.namaPerusahaan" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Paket</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="paketPeriksa.namaPaket"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>

                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="daftarpaketperiksaForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_daftarpaket.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a type="button" class="btn btn-primary" href="add_daftarpaket.action">
                                            <i class="fa fa-plus"></i> Tambah Paket
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Paket Periksa</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Nama Perusahaan</td>
                                <td>Nama Paket</td>
                                <td align="center" width="20%">Jumlah Pasien</td>
                                <td align="center" width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult">
                                <tr>
                                    <td><s:property value="namaPerusahaan"/></td>
                                    <td><s:property value="namaPaket"/></td>
                                    <td align="center"><span style="padding: 4px; background-color: #fbec88"><s:property value="jumlah"/></span></td>
                                    <td align="center">
                                        <img class="hvr-grow" onclick="detailPaket('<s:property value="idPerusahaan"/>','<s:property value="idPaket"/>','<s:property value="namaPerusahaan"/>','<s:property value="namaPaket"/>')" src="<s:url value="/pages/images/icons8-search-25.png"/>" style="cursor: pointer; ">
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

<div class="modal fade" id="modal-detail-pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Daftar Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                    <table class="table table-striped">
                        <tr>
                            <td width="35%">Nama Perusahaan</td>
                            <td><p id="det_nama_perusahaan"></p></td>
                        </tr>
                        <tr>
                            <td>Nama Paket</td>
                            <td><p id="det_nama_paket"></p></td>
                        </tr>
                    </table>
                <table class="table table-striped table-bordered">
                    <thead>
                        <td>No RM</td>
                        <td>Nama</td>
                        <td align="center">Status</td>
                    </thead>
                    <tbody id="body_detail_pasien">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <%--<button type="button" class="btn btn-success" id="save_add" onclick="saveNewPasien()"><i class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success"--%>
                        <%--id="load_add"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>
    function detailPaket(idPerusahaan, idPaket, namaPerusahaan, namaPaket){
        $('#det_nama_perusahaan').text(namaPerusahaan);
        $('#det_nama_paket').text(namaPaket);
        $('#modal-detail-pasien').modal({show:true, backdrop:'static'});
        PaketPeriksaAction.detailDaftarPaketPasien(idPaket, idPerusahaan, function (response) {
            if(response.length > 0){
              var table = "";
              $.each(response, function (i, item) {
                  var label = ""
                  if(item.flag == "Y"){
                      label = '<label class="label label-warning">belum periksa</label>';
                  }else{
                      label = '<label class="label label-success">sudah periksa</label>';
                  }
                  table +=
                      '<tr>' +
                      '<td>'+item.idPasien+'</td>' +
                      '<td>'+item.namaPasien+'</td>' +
                      '<td align="center" style="vertical-align: middle">'+label+'</td>' +
                      '</tr>'
              });
              $('#body_detail_pasien').html(table);
            }
        })

    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>