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

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/VerifikatorAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#verifikasi_tindakan').addClass('active');
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
            Verifikasi Tindakan Rawat
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Verifikasi Tindakan Rawat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="verifikatorForm" method="post" namespace="/verifikator" action="search_verifikator.action" theme="simple" cssClass="form-horizontal">
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
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="verifikatorForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_verifikator.action">
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>No Checkup</td>
                                <td>ID Pasien</td>
                                <td>Nama</td>
                                <td>Alamat</td>
                                <td>Status</td>
                                <td>Keterangan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfRawatJalan" id="listRawatjalan">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="alamat"/></td>
                                    <td><s:property value="statusPeriksaName"/></td>
                                    <td><s:property value="keteranganSelesai"/></td>
                                    <td align="center">
                                        <s:if test="#listRawatjalan.statusPeriksa == 0 || #listRawatjalan.statusPeriksa == 1">
                                            <%--<s:url var="add_rawat_jalan" namespace="/checkupdetail" action="add_checkupdetail" escapeAmp="false">--%>
                                                <%--<s:param name="id"><s:property value="noCheckup"/></s:param>--%>
                                            <%--</s:url>--%>
                                            <%--<s:a href="%{add_rawat_jalan}">--%>
                                                <img onclick="detailTindakan('<s:property value="noCheckup"/>','<s:property value="idDetailCheckup"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                            <%--</s:a>--%>
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

<div class="modal fade" id="modal-detail-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Tindakan Rawat Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>No Checkup</b></td>
                                    <td><span id="det_no_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="det_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="det_nama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="det_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, TGL Lahir</b></td>
                                    <td><span id="det_tgl"></span></td>
                                </tr>
                                <%--<tr>--%>
                                    <%--<td><b>Agama</b></td>--%>
                                    <%--<td><span id="det_agama"></span></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                    <%--<td><b>Suku</b></td>--%>
                                    <%--<td><span id="det_suku"></span></td>--%>
                                <%--</tr>--%>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="det_alamat"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Provinsi</b></td>
                                    <td><span id="det_provinsi"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kabupaten</b></td>
                                    <td><span id="det_kabupaten"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kecamatan</b></td>
                                    <td><span id="det_kecamatan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Desa</b></td>
                                    <td><span id="det_desa"></span></td>
                                </tr>
                            </table>
                        </div>

                    </div>
                </div>
                <input type="hidden" id="tin_id_detail_checkup">
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-medkit"></i> Daftar Tindakan Rawat</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_tindakan_ts">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td>Nama Tindakan</td>
                            <td align="center">Kategori</td>
                            <td align="center">Action</td>
                        </tr>
                        </thead>
                        <tbody id="body_tindakan">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_verif"><i class="fa fa-arrow-right"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>
    function detailTindakan(idCheckup, idDetailCheckup) {
        var table = "";
        var dataTindakan = [];
        var dataPasien = [];
        var noCheckup = "";
        var nik = "";
        var namaPasien = "";
        var jenisKelamin = "";
        var tglLahir = "";
        var agama = "";
        var suku = "";
        var alamat = "";
        var provinsi = "";
        var kabupaten = "";
        var kecamatan = "";
        var desa = "";
        var tindakan = "";

        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#v_'+idCheckup).attr('src',url).css('width', '30px', 'height', '40px');


        setTimeout(function () {

            var url = '<s:url value="/pages/images/icons8-search-25.png"/>';
            $('#v_'+idCheckup).attr('src',url).css('width', '', 'height', '');

            CheckupAction.listDataPasien(idCheckup, function (response) {
                dataPasien = response;
                if (dataPasien != null) {
                    $.each(dataPasien, function (i, item) {
                        var tanggal = item.tglLahir;
                        var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                        noCheckup = item.noCheckup;
                        nik = item.noKtp;
                        namaPasien = item.nama;

                        if (item.jenisKelamin == "L") {
                            jenisKelamin = "Laki-Laki";
                        } else {
                            jenisKelamin = "Perempuan";
                        }

                        tglLahir = item.tempatLahir + ", " + dateFormat;
                        agama = item.agama;
                        suku = item.suku;
                        alamat = item.jalan;
                        provinsi = item.namaProvinsi;
                        kabupaten = item.namaKota;
                        kecamatan = item.namaKecamatan;
                        desa = item.namaDesa;
                    });
                }
            });

            VerifikatorAction.getListTindakanRawat(idDetailCheckup, function (response) {
                dataTindakan = response;
                if (dataTindakan != null) {
                    $.each(dataTindakan, function (i, item) {

                        if (item.namaTindakan) {
                            tindakan = item.namaTindakan;
                        }

                        table += "<tr>" +
                                "<td>" + tindakan + "</td>" +
                                "<td>" +
                                '<select onclick="toSelect2(\''+i+'\')" class="form-control" id=ts'+i+'>' +
                                '<option value="">[Select One]</option>'+
                                '<option value="1">Prosedur Non Bedah</option>'+
                                '<option value="2">Tenaga Ahli</option>'+
                                '<option value="3">Radiologi</option>'+
                                '<option value="4">Rehabilitasi</option>'+
                                '<option value="5">Obat</option>'+
                                '<option value="6">Alkes</option>'+
                                '<option value="7">Prosedur Bedah</option>'+
                                '<option value="8">Keperawatan</option>'+
                                '<option value="9">Laboratorium</option>'+
                                '<option value="10">Kamar / Akomodasi</option>'+
                                '<option value="11">Obat Kronis</option>'+
                                '<option value="12">BMHP</option>'+
                                '<option value="13">Konsultasi</option>'+
                                '<option value="14">Penunjang</option>'+
                                '<option value="15">Pelayanan Darah</option>'+
                                '<option value="16">Rawat Intensif</option>'+
                                '<option value="17">Obat Kemoterapi</option>'+
                                '</select>' + "</td>" +
                                "<td align='center'>" + '<img class="hvr-grow" onclick="updateApproveFlag(\''+item.idTindakanRawat+'\')" src="<s:url value="/pages/images/icons8-save-25.png"/>">' + "</td>" +
                                "</tr>";
                    });
                }
            });

            $('#det_no_checkup').html(noCheckup);
            $('#det_nik').html(nik);
            $('#det_nama').html(namaPasien);
            $('#det_jenis_kelamin').html(jenisKelamin);
            $('#det_tgl').html(tglLahir);
            $('#det_agama').html(agama);
            $('#det_suku').html(suku);
            $('#det_alamat').html(alamat);
            $('#det_provinsi').html(provinsi);
            $('#det_kabupaten').html(kabupaten);
            $('#det_kecamatan').html(kecamatan);
            $('#det_desa').html(desa);
            $('#body_tindakan').html(table);
            $('#tin_id_detail_checkup').val(idDetailCheckup);
            $('#save_verif').attr('onclick','saveApproveTindakan(\''+idDetailCheckup+'\')');
            $('#modal-detail-pasien').modal({show:true, backdrop:'static'});
        }, 100);
    }

    function toSelect2(i){
        $('#ts'+i).select2();
    }

    function updateApproveFlag(idTindakan){
        var tes = $('#tabel_tindakan').tableToJSON();
        $.each(tes, function (i, item) {
            $('#ts'+i).select2();
        })
        VerifikatorAction.updateApproveBpjsFlag(idTindakan, function (response) {
            console.log(response);
        });
    }

    function saveApproveTindakan(idDetailCheckup){
        VerifikatorAction.
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>