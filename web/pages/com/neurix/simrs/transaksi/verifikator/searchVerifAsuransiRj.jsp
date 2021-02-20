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
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/VerifikatorAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#bayar_rawat_jalan, #verif_asuransi_active').addClass('active');
            $('#verif_asuransi_open').addClass('menu-open');
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
            Verifikasi Transaksi Pasien Asuransi Rawat Jalan
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Verifikasi Transaksi Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="verifForm" method="post" namespace="/verifasrj" action="searchVerifAs_verifasrj.action" theme="simple" cssClass="form-horizontal">
                                <s:hidden name="headerDetailCheckup.tipePelayanan" value="rawat_jalan"></s:hidden>
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
                                        <s:textfield id="nama_pasien" name="headerDetailCheckup.nama"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Pelayanan</label>
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
                                        <s:select list="#{'Y':'Sudah Verifikasi'}" cssStyle="margin-top: 7px; width: 100%"
                                                  id="status" name="headerDetailCheckup.flagCover"
                                                  headerKey="" headerValue="Belum Verifikasi"
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
                                            <s:textfield id="tgl_from" name="headerDetailCheckup.stDateFrom" cssClass="form-control datemask2"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="headerDetailCheckup.stDateTo" cssClass="form-control datemask2"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="verifForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initVerif_verifasrj.action">
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
                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                   resizable="false"
                                                   closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="close_pos"></s:hidden>
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
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
                        <table id="sortTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>No Checkup</td>
                                <td>No RM</td>
                                <td>Nama</td>
                                <td>Jenis Pasien</td>
                                <td>Keterangan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="jenisPeriksaPasien"/></td>
                                    <td><s:property value="keteranganSelesai"/></td>
                                    <td align="center">
                                        <s:if test='#row.flagCover == "Y"'>
                                            <img src="<s:url value="/pages/images/icon_success.ico"/>">
                                        </s:if>
                                        <s:else>
                                            <img id="spin_<s:property value="idDetailCheckup"/>" onclick="detail('<s:property value="noCheckup"/>','<s:property value="idDetailCheckup"/>','<s:property value="flagCloseTraksaksi"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" style="cursor: pointer;">
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

<div class="modal fade" id="modal-detail">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Verifikasi Transaksi Pasien</h4>
            </div>
            <div class="modal-body" style="height: 70%;overflow-y: scroll;" id="top_up">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_war"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_suc"></p>
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title" ><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped" >
                                <tr>
                                    <td><b>No RM</b></td>
                                    <td ><span id="no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No Checkup </b></td>
                                    <td><span id="no_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="nama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="jenis_kelamin"></span></td>
                                </tr>
                            </table>
                        </div>

                        <input type="hidden" id="h_no_checkup">
                        <input type="hidden" id="h_id_pasien">
                        <input type="hidden" id="h_id_detail_pasien">
                        <input type="hidden" id="h_jenis_pasien">
                        <input type="hidden" id="h_id_pelayanan">
                        <input type="hidden" id="h_metode_bayar">

                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%"><b>Tempat, Tanggal Lahir</b></td>
                                    <td><span id="tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="alamat"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Pelayanan</b></td>
                                    <td><span id="poli"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jenis Pasien</b></td>
                                    <td><span id="jenis_pasien"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Diagnosa</b></td>
                                    <td><span id="diagnosa"></span></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div id="form-close" style="display: none">
                    <div class="box-header with-border">
                        <h3 class="box-title" ><i class="fa fa-hospital-o"></i> Tindakan Rawat</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped" id="tabel_tindakan" >
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="20%">Tanggal</td>
                                <td>Nama Tindakan</td>
                                <td>Tarif (Rp.)</td>
                                <td>Qty</td>
                                <td align="center">Total Tarif (Rp.)</td>
                            </tr>
                            </thead>
                            <tbody id="body_tindakan">
                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title" ><i class="fa fa-hospital-o"></i> Penunjang Medis</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped" id="tabel_penunjang_medis" >
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="20%">Tanggal</td>
                                <td>Pemeriksaan</td>
                                <td>Status</td>
                                <td>Jenis Lab</td>
                                <td align="center">Detail</td>
                            </tr>
                            </thead>
                            <tbody id="body_penunjang_medis">
                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Resep</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped" id="tabel_resep" >
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="20%">Tanggal</td>
                                <td>ID Resep</td>
                                <td>Status</td>
                                <td align="center">Detail</td>
                            </tr>
                            </thead>
                            <tbody id="body_resep">
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="form-cover" style="display: none">
                    <div class="box-header with-border">
                        <h3 class="box-title" ><i class="fa fa-hospital-o"></i> All Tindakan Rawat</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped" id="tbl_tindakan">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="20%">Waktu</td>
                                <td>Nama Tindakan</td>
                                <td width="25%">Tarif (Rp.)</td>
                                <td>Keterangan</td>
                            </tr>
                            </thead>
                            <tbody id="body_tindakan_cover">
                            </tbody>
                        </table>
                        <p id="loading_page" style="color: #0F9E5E; display: none"><img style="width: 50px; height: 50px" src="<s:url value="/pages/images/spinner.gif"/>"><b>Sedang mencari data traksaksi...</b></p>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fin" ><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_fin"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4>Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript' src='<s:url value="/dwr/interface/TindakanRawatAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanResepAction.js"/>'></script>

<script type='text/javascript'>

    var contextPath = '<%= request.getContextPath() %>';
    var jenisKelamin = "";

    function formatRupiah(angka) {
        if(angka != "" && angka > 0){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        }else{
            return 0;
        }

    }

    function detail(noCheckup, idDetailCheckup, flagClose) {
        if(!cekSession()){
            $('#body_tindakan').html('');
            $('#body_lab').html('');
            $('#body_resep').html('');
            startSpinner('spin_', idDetailCheckup);
            dwr.engine.setAsync(true);
            CheckupAction.listDataPasien(idDetailCheckup, {callback: function (res) {
                    if (res.idPasien != null) {
                        stopSpinner('spin_', idDetailCheckup);
                        dwr.engine.setAsync(false);
                        jenisKelamin = res.jenisKelamin;
                        listTindakan(noCheckup, res.idJenisPeriksaPasien);
                        listResepPasien(noCheckup, res.idJenisPeriksaPasien);
                        listLab(noCheckup, res.idJenisPeriksaPasien);
                        var jk = "";
                        var alamat = res.namaDesa + ", " + res.namaKecamatan + ", " + res.namaKota;
                        var diagnosa = res.diagnosa + ", " + res.namaDiagnosa;
                        if (res.jenisKelamin == "L") {
                            jk = "Laki-Laki";
                        } else {
                            jk = "Perempuan";
                        }

                        $('#no_rm').html(res.idPasien);
                        $('#no_checkup').html(noCheckup);
                        $('#nik').html(res.noKtp);
                        $('#nama').html(res.nama);
                        $('#jenis_kelamin').html(jk);
                        $('#tgl').html(res.tempatLahir + ", " + converterDate(new Date(res.tglLahir)));
                        $('#alamat').html(alamat);
                        $('#poli').html(res.namaPelayanan);
                        $('#diagnosa').html(diagnosa);
                        $('#h_id_pasien').val(res.idPasien);
                        $('#h_id_detail_pasien').val(res.idDetailCheckup);
                        $('#h_id_pelayanan').val(res.idPelayanan);
                        $('#h_metode_bayar').val(res.metodePembayaran);
                        $('#h_jenis_pasien').val(res.idJenisPeriksaPasien);
                        $('#h_no_checkup').val(noCheckup);
                        $('#jenis_pasien').html(changeJenisPasien(res.idJenisPeriksaPasien, res.statusPeriksaName));
                        $('#save_fin').show();
                        $('#load_fin').hide();
                        if(res.flagCloseTransaksi == "Y"){
                            $('#form-close').hide();
                            $('#form-cover').show();
                            $('#save_fin').html("<i class='fa fa-check'></i> Update Cover");
                            $('#save_fin').attr('onclick', 'confirm()');
                            listTindakanApprove(noCheckup, idDetailCheckup, res.idJenisPeriksaPasien);
                        }else{
                            $('#form-close').show();
                            $('#form-cover').hide();
                            $('#save_fin').html("<i class='fa fa-check'></i> Verifikasi");
                            $('#save_fin').attr('onclick', 'confirmClose()');
                        }
                        $('#modal-detail').modal({show: true, backdrop: 'static'});
                    }
                }
            });
        }
    }

    function listTindakan(noCheckup, jenis) {
        if(!cekSession()){
            var table = "";
            var data = [];
            var trfTtl = 0;
            TindakanRawatAction.getListTindakanRawat(noCheckup, jenis, function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        var tanggal = item.createdDate;
                        var dateFormat = converterDate(new Date(tanggal));
                        var tarif = "-";
                        var tarifTotal = "-";
                        var trfTotal = 0;
                        var qtyTotal = 0;
                        var perawat = "";
                        var btn = '';

                        if (item.tarif != null) {
                            tarif = formatRupiah(item.tarif);
                            trfTotal += item.tarif;
                        }
                        if (item.tarifTotal != null) {
                            tarifTotal = formatRupiah(item.tarifTotal);
                            trfTtl += item.tarifTotal;
                        }
                        if (item.qty != null) {
                            qtyTotal += item.qty;
                        }
                        if (item.idPerawat != null) {
                            perawat = item.idPerawat;
                        }

                        if ("Y" == item.approveFlag) {
                            btn = "";
                        }

                        table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + item.namaTindakan + "</td>" +
                            "<td align='right'>" + tarif + "</td>" +
                            "<td align='center'>" + item.qty + "</td>" +
                            "<td align='right'>" + tarifTotal + "</td>" +
                            "</tr>";

                    });

                    if(table != ''){
                        table = table + "<tr>" +
                            "<td colspan='4'>Total</td>" +
                            "<td align='right'>" + formatRupiah(trfTtl) + "</td>" +
                            "</tr>";
                        $('#body_tindakan').html(table);
                    }
                }
            });
        }
    }

    function listResepPasien(noCheckup, jenis) {
        if(!cekSession()){
            var table = "";
            var data = [];
            PermintaanResepAction.getListRespPasien(noCheckup, jenis, function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        var idResep = "";
                        var tanggal = item.createdDate;
                        var dateFormat = converterDate(new Date(tanggal));
                        var status = "";

                        if(item.status == "0"){
                            status = "Antrian";
                        }else if(item.status == "1"){
                            status = "Proses";
                        }else if(item.status == "3"){
                            status = "Selesai";
                        }else{
                            status = "Tidak Diketahui";
                        }
                        if (item.idPermintaanResep != null) {
                            idResep = item.idPermintaanResep;
                        }


                        var url = contextPath + '/pages/images/icons8-plus-25.png';
                        table += '<tr id="row_'+idResep+'">' +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + idResep + "</td>" +
                            "<td>" + status + "</td>" +
                            "<td align='center' width='10%'>" +
                            '<img src="'+url+'" onclick="detailTindakan(\''+idResep+'\', \'resep\')" class="hvr-grow" id="btn_'+idResep+'" style="cursor: pointer">' +
                            "</td>" +
                            "</tr>"
                    });
                    $('#body_resep').html(table);
                }
            });
        }
    }

    function listLab(noCheckup, jenis) {
        if(!cekSession()){
            var table = "";
            var data = [];
            PeriksaLabAction.getListLab(noCheckup, jenis, function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        var pemeriksaan = "-";
                        var status = "-";
                        var lab = "-";
                        var tanggal = item.createdDate;
                        var dateFormat = converterDate(new Date(tanggal));
                        var btn = '';
                        var tipe = "";

                        if (item.kategori == "radiologi") {
                            tipe = "radiologi";
                        } else {
                            tipe = "laboratorium";
                        }

                        if (item.idLab != null) {
                            pemeriksaan = item.idLab;
                        }
                        if (item.statusPeriksa != null) {
                            if(item.statusPeriksa == "0"){
                                status = "Antrian";
                            }else if(item.statusPeriksa == "1"){
                                status = "Proses";
                            }else{
                                status = "Selesai";
                            }
                        }
                        if (item.labName != null) {
                            lab = item.labName;
                        }

                        var url = contextPath + '/pages/images/icons8-plus-25.png';

                        table += '<tr id="row_'+item.idPeriksaLab+'">' +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + lab + "</td>" +
                            "<td>" + status + "</td>" +
                            "<td>" + item.kategoriLabName + "</td>" +
                            "<td align='center' width='10%'>" +
                            '<img src="'+url+'" onclick="detailTindakan(\''+item.idPeriksaLab+'\', \''+tipe+'\')" class="hvr-grow" id="btn_'+item.idPeriksaLab+'" style="cursor: pointer">'
                            +"</td>" +
                            "</tr>";

                    });
                    $('#body_penunjang_medis').html(table);
                }
            });
        }
    }

    function detailTindakan(idTindakan, keterangan){
        if(!cekSession()){
            if (idTindakan && keterangan != '') {
                var head = "";
                var body = "";
                CheckupAction.getListDetailHistoryPasien(idTindakan, keterangan, function (res) {
                    if (res.length > 0) {
                        $.each(res, function (i, item) {
                            if(keterangan == "radiologi"){
                                var acuan = cekDataNull(item.ketAcuanL);
                                if(jenisKelamin == "P"){
                                    acuan = cekDataNull(item.ketAcuanP);
                                }
                                body += '<tr>' +
                                    '<td>'+cekDataNull(item.namaDetailLab)+'</td>' +
                                    '<td>'+cekDataNull(item.satuan)+'</td>' +
                                    '<td>'+cekDataNull(acuan)+'</td>' +
                                    '<td>'+cekDataNull(item.kesimpulan)+'</td>' +
                                    '</tr>';
                            }
                            if(keterangan == "laboratorium"){
                                var acuan = cekDataNull(item.ketAcuanL);
                                if(jenisKelamin == "P"){
                                    acuan = cekDataNull(item.ketAcuanP);
                                }
                                body += '<tr>' +
                                    '<td>'+cekDataNull(item.namaDetailLab)+'</td>' +
                                    '<td>'+cekDataNull(item.satuan)+'</td>' +
                                    '<td>'+cekDataNull(acuan)+'</td>' +
                                    '<td>'+cekDataNull(item.kesimpulan)+'</td>' +
                                    '<td>'+cekDataNull(item.keterangan)+'</td>' +
                                    '</tr>';
                            }
                            if(keterangan == "resep"){
                                body += '<tr>' +
                                    '<td>'+cekDataNull(item.namaObat)+'</td>' +
                                    '<td>'+cekDataNull(item.qty)+' '+cekDataNull(item.satuan)+'</td>' +
                                    '<td>'+cekDataNull(item.keterangan)+'</td>' +
                                    '</tr>';
                            }
                        });
                    }

                    if(keterangan == "radiologi"){
                        head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                            '<td>Pemeriksaan</td>' +
                            '<td>Satuan</td>' +
                            '<td>Keterangan Acuan</td>' +
                            '<td>Hasil</td>' +
                            '</tr>';
                    }
                    if(keterangan == "laboratorium"){
                        head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                            '<td>Pemeriksaan</td>' +
                            '<td>Satuan</td>' +
                            '<td>Keterangan Acuan</td>' +
                            '<td>Hasil</td>' +
                            '<td>Keterangan</td>' +
                            '</tr>';
                    }
                    if(keterangan == "resep"){
                        head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                            '<td>Nama Obat</td>' +
                            '<td>Qty</td>' +
                            '<td>Keterangan</td>' +
                            '</tr>';
                    }

                    var table = '<table style="font-size: 12px" class="table table-bordered">' +
                        '<thead>' + head + '</thead>' +
                        '<tbody>' + body + '</tbody>' +
                        '</table>';

                    var newRow = $('<tr id="del_' + idTindakan + '"><td colspan="6">' + table + '</td></tr>');
                    newRow.insertAfter($('table').find('#row_' + idTindakan));
                    var url = contextPath+'/pages/images/minus-allnew.png';
                    $('#btn_' + idTindakan).attr('src', url);
                    $('#btn_' + idTindakan).attr('onclick', 'delDetail(\''+idTindakan+'\', \''+keterangan+'\')');
                });
            }
        }
    }

    function delDetail(idTindakan, keterangan){
        $('#del_' + idTindakan).remove();
        var url = contextPath+'/pages/images/icons8-plus-25.png';
        $('#btn_' + idTindakan).attr('src', url);
        $('#btn_' + idTindakan).attr('onclick', 'detailTindakan(\''+idTindakan+'\', \''+keterangan+'\')');
    }

    function cekDataNull(item){
        var data = "";
        if(item != null && item != ''){
            data = item;
        }
        return data;
    }

    function confirmClose(){
        $('#modal-confirm-dialog').modal({show:true, backdrop:'static'});
        $('#save_con').attr('onclick', 'closeTransaksi()');
    }

    function closeTransaksi(){
        $('#modal-confirm-dialog').modal('hide');
        if(!cekSession()){
            var data = "";
            var noCheckup = $('#h_no_checkup').val();
            var idPasien = $('#h_id_pasien').val();
            var idPelayanan = $('#h_id_pelayanan').val();
            var metodePembayaran = $('#h_metode_bayar').val();
            var jenisPasien = $('#h_jenis_pasien').val();
            var idDetailCheckup = $('#h_id_detail_pasien').val();
            var cekResep = $('#tabel_resep').tableToJSON();
            var isResep = "N";
            if(cekResep.length > 0){
                isResep = "Y";
            }

            data = {
                'no_checkup':noCheckup,
                'id_pasien':idPasien,
                'id_detail_checkup': idDetailCheckup,
                'jenis_pasien': jenisPasien,
                'id_pelayanan': idPelayanan,
                'metode_bayar': metodePembayaran,
                'is_resep':isResep
            }
            var result = JSON.stringify(data);
            $('#save_fin').hide();
            $('#load_fin').show();
            dwr.engine.setAsync(true);
            CheckupDetailAction.closeTraksaksiPasien(result,
                {callback: function (res) {
                        if (res.status == "success") {
                            $('#form-close').hide();
                            $('#form-cover').show();
                            $('#save_fin').show();
                            $('#load_fin').hide();
                            $('#save_fin').html("<i class='fa fa-check'></i> Update Cover");
                            $('#save_fin').attr('onclick', 'confirm()');
                            $('#top_up').scrollTop(0);
                            $('#success').show().fadeOut(5000);
                            $('#msg_suc').text("Traksaksi berhasil di close, silahkan update cover asuransi dibawah...!");
                            listTindakanApprove(noCheckup, idDetailCheckup, jenisPasien);
                        } else {
                            $('#save_fin').show();
                            $('#load_fin').hide();
                            $('#warning').show().fadeOut(5000);
                            $('#msg_war').text(res.msg);
                            $('#top_up').scrollTop(0);
                        }
                    }
                });
        }
    }

    function listTindakanApprove(noCheckup, idDetailCheckup, jenis) {
        if(!cekSession()){
            var table = "";
            var data = [];
            var trfTtl = 0;
            $('#body_tindakan_cover').html('');
            $('#loading_page').show();
            dwr.engine.setAsync(true);
            VerifikatorAction.getListTindakanRawat(noCheckup, idDetailCheckup, jenis,
                {callback: function (response) {
                        if (response.length > 0) {
                            $('#loading_page').hide();
                            $.each(response, function (i, item) {
                                var tanggal = item.createdDate;
                                var dateFormat = converterDate(new Date(tanggal));
                                var tarif = "-";
                                var tarifTotal = "-";
                                var trfTotal = 0;
                                var qtyTotal = 0;
                                var perawat = "";
                                var btn = '';

                                if (item.totalTarif != null) {
                                    tarif = formatRupiah(item.totalTarif);
                                    trfTtl += item.totalTarif;
                                }

                                var select = '<select style="width: 100%" onchange="setCoverBiaya(this.value,\''+item.totalTarif+'\')" class="form-control select2" id="cover_'+i+'">' +
                                    '<option value="asuransi">Ditanggung</option>'+
                                    '<option value="umum">Tidak Ditanggung</option>'+
                                    '</select>';

                                table += "<tr>" +
                                    "<td>" +'<input type="hidden" value="'+item.idRiwayatTindakan+'" id="h_id_riwayat_tindakan_'+i+'">' +item.stTglTindakan + "</td>" +
                                    "<td>" + item.namaTindakan + "</td>" +
                                    "<td align='right'>" + tarif + "</td>" +
                                    "<td>" + select + "</td>" +
                                    "</tr>";

                            });

                            if(table != ''){
                                table = table + "<tr style='font-weight: bold'>" +
                                    "<td colspan='2'>Total Jasa</td>" +
                                    "<td align='right'>" + formatRupiah(trfTtl) + "</td>" +
                                    "<td></td>" +
                                    "</tr>" +
                                    "<tr style='font-weight: bold'>" +
                                    "<td colspan='2'>Total Cover Biaya</td>" +
                                    "<td>" +
                                    '<input type="hidden" value="'+trfTtl+'" id="h_cover_biaya">' +
                                    '<input style="text-align: right" class="form-control" id="cover_biaya" value="'+formatRupiah(trfTtl)+'">' +
                                    "</td>" +
                                    "<td></td>" +
                                    "</tr>"+
                                    "<tr style='font-weight: bold'>" +
                                    "<td colspan='2'>Total Biaya Yang Dibayar Pasien</td>" +
                                    "<td>" +
                                    '<input type="hidden" id="h_pasien_bayar" value="0">' +
                                    '<input style="text-align: right" class="form-control" id="pasien_bayar" value="0">' +
                                    "</td>" +
                                    "<td></td>" +
                                    "</tr>";
                                $('#body_tindakan_cover').html(table);
                            }
                        }
                        var cek = $('.select2').length;
                        if(cek > 0){
                            $('.select2').select2();
                        }
                    }
                });
        }
    }

    function setCoverBiaya(value, tarif) {
        var jumlahCover = $('#h_cover_biaya').val();
        var jumlahPasienBayar = $('#h_pasien_bayar').val();
        var cover = 0;
        var pasien = 0;
        var totalCover = 0;
        var totalPasien = 0;

        if(jumlahCover != null && jumlahCover != ''){
            cover = jumlahCover;
        }
        if(jumlahPasienBayar != null && jumlahPasienBayar != ''){
            pasien = jumlahPasienBayar;
        }

        if(value == "asuransi"){
            totalCover = parseInt(cover) + parseInt(tarif);
            totalPasien = parseInt(pasien) - parseInt(tarif);
        }else{
            totalCover = parseInt(cover) - parseInt(tarif);
            totalPasien = parseInt(pasien) + parseInt(tarif);
        }

        $('#cover_biaya').val(formatRupiah(totalCover));
        $('#pasien_bayar').val(formatRupiah(totalPasien));
        $('#h_cover_biaya').val(totalCover);
        $('#h_pasien_bayar').val(totalPasien);

    }

    function cekDataNull(item){
        var data = "";
        if(item != null && item != ''){
            data = item;
        }
        return data;
    }

    function confirm(){
        $('#modal-confirm-dialog').modal({show:true, backdrop:'static'});
        $('#save_con').attr('onclick', 'closeTransaksiCover()');
    }

    function closeTransaksiCover(){
        $('#modal-confirm-dialog').modal('hide');
        if(!cekSession()){
            var data = [];
            var dataDetail = "";
            var noCheckup = $('#h_no_checkup').val();
            var idPasien = $('#h_id_pasien').val();
            var idPelayanan = $('#h_id_pelayanan').val();
            var idDetailCheckup = $('#h_id_detail_pasien').val();
            var coverBiaya = $('#h_cover_biaya').val();
            var pasienBayar = $('#h_pasien_bayar').val();
            var dataTable = $('#tbl_tindakan').tableToJSON();
            var cekResep = $('#tabel_resep').tableToJSON();
            var isResep = "N";
            if(cekResep.length > 0){
                isResep = "Y";
            }

            for (var i = 0; i < dataTable.length - 3; i++){
                var idRiwayat = $('#h_id_riwayat_tindakan_'+i).val();
                var jenis = $('#cover_'+i).val();
                data.push({
                    'id_riwayat_tindakan': idRiwayat,
                    'jenis_pasien':jenis
                });
            }

            dataDetail = {
                'no_checkup':noCheckup,
                'id_pasien':idPasien,
                'id_detail_checkup': idDetailCheckup,
                'id_pelayanan': idPelayanan,
                'ditanggung_pasien': pasienBayar,
                'cover_biaya': coverBiaya,
                'is_resep':isResep
            }
            var result1 = JSON.stringify(data);
            var result2 = JSON.stringify(dataDetail);
            $('#save_fin').hide();
            $('#load_fin').show();
            dwr.engine.setAsync(true);
            VerifikatorAction.updateCoverAsuransi(result1, result2,
                {callback: function (res) {
                        if (res.status == "success") {
                            $('#save_fin').show();
                            $('#load_fin').hide();
                            $('#modal-detail').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('body').scrollTop(0);
                        } else {
                            $('#save_fin').show();
                            $('#load_fin').hide();
                            $('#warning').show().fadeOut(5000);
                            $('#msg_war').text(res.msg);
                            $('#top_up').scrollTop(0);
                        }
                    }
                });
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>