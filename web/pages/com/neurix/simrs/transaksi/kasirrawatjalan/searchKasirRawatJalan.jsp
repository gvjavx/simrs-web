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
        .modal { overflow-y: auto}
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#bayar_rawat_jalan, #pembayaran_active').addClass('active');
            $('#pembayaran_open').addClass('menu-open');
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
            Kasir Rawat Jalan
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Kasir Rawat Jalan</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="kasirjalanForm" method="post" namespace="/kasirjalan" action="search_kasirjalan.action" theme="simple" cssClass="form-horizontal">
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
                                                  id="status"
                                                  headerKey="3" headerValue="Selesai"
                                                  cssClass="form-control select2" disabled="true"/>
                                        <s:hidden name="headerDetailCheckup.statusPeriksa" value="3"></s:hidden>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status Bayar</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'Y':'Sudah Dibayar'}" cssStyle="margin-top: 7px"
                                                  id="statusBayar"
                                                  headerKey="" headerValue="Belum Dibayar" name="headerDetailCheckup.statusBayar"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Jenis Pasien</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'bpjs':'BPJS','asuransi':'ASURANSI'}" cssStyle="margin-top: 7px"
                                                  headerKey="umum" headerValue="UMUM" name="headerDetailCheckup.idJenisPeriksaPasien"
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
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="kasirjalanForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_kasirjalan.action">
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
                                <td>ID Detail Checkup</td>
                                <td>ID Pasien</td>
                                <td>Nama</td>
                                <td>Status Periksa</td>
                                <td >Status</td>
                                <td>Keterangan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfRawatJalan" var="row">
                                <tr>
                                    <td><s:property value="idDetailCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="statusPeriksaName"/></td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.statusBayar == "Y"'>
                                            <label class="label label-success"> sudah bayar</label>
                                        </s:if>
                                        <s:else>
                                            <label class="label label-warning"> belum bayar</label>
                                        </s:else>
                                    </td>
                                    <td><s:property value="keteranganSelesai"/></td>
                                    <td align="center">
                                        <s:if test='#row.statusBayar == "Y"'>
                                            <s:url var="print_invo" namespace="/kasirjalan" action="printInvoice_kasirjalan" escapeAmp="false">
                                                <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                <s:param name="jenis"><s:property value="idJenisPeriksaPasien"/></s:param>
                                            </s:url>
                                            <s:a href="%{print_invo}" target="_blank">
                                            <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                            </s:a>
                                        </s:if>
                                        <s:else>
                                            <img id="t_<s:property value="idDetailCheckup"/>" onclick="showInvoice('<s:property value="noCheckup"/>','<s:property value="idDetailCheckup"/>')" class="hvr-grow" src="<s:url value="/pages/images/icon_payment.ico"/>" style="cursor: pointer;">
                                        </s:else>
                                        <s:if test='#row.idJenisPeriksaPasien == "bpjs"'>
                                            <s:url var="print_invo" namespace="/kasirjalan" action="printInvoice_kasirjalan" escapeAmp="false">
                                                <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                <s:param name="jenis"><s:property value="idJenisPeriksaPasien"/></s:param>
                                            </s:url>
                                            <s:a href="%{print_invo}" target="_blank">
                                                <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                            </s:a>
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

<div class="modal fade" id="modal-invoice">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Total Tarif Rawat Jalan Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr style="display: none;" id="no_sep_show">
                                    <td><b>No SEP</b></td>
                                    <td style="vertical-align: middle"><span class="label label-success" id="fin_no_sep"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No RM</b></td>
                                    <td><span id="fin_no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td><b>ID Detail Checkup</b></td>
                                    <td><span id="fin_no_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="fin_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="fin_nama"></span></td>
                                </tr>
                                <input type="hidden" id="fin_no_nota"/>
                                <input type="hidden" id="fin_id_pasien"/>
                                <input type="hidden" id="fin_is_resep"/>
                                <input type="hidden" id="fin_metode_bayar"/>
                                <input type="hidden" id="fin_bukti"/>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="fin_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, Tgl Lahir</b></td>
                                    <td><span id="fin_tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="fin_desa"></span>, <span id="fin_kecamatan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jenis Pasien</b></td>
                                    <td><span id="fin_jenis_pasien"></span></td>
                                </tr>
                            </table>
                        </div>

                    </div>
                </div>

                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-medkit"></i> Uang Muka</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_uang_muka">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <%--<td width="10%" align="center">Action</td>--%>
                            <td>Tanggal</td>
                            <td>Bukti</td>
                            <td align="center" width="20%">Total Tarif (Rp.)</td>
                        </tr>
                        </thead>
                        <tbody id="body_uang_muka">
                        </tbody>
                    </table>
                </div>

                <input type="hidden" id="fin_id_detail_checkup">
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-medkit"></i> Daftar Tindakan Rawat</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_tindakan_fin">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td width="10%" align="center">Action</td>
                            <td width="20%">Tanggal</td>
                            <td>Nama Tindakan</td>
                            <td align="center" width="20%">Total Tarif (Rp.)</td>
                        </tr>
                        </thead>
                        <tbody id="body_tindakan_fin">
                        </tbody>
                    </table>

                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_fin"></p>
                    </div>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_fin">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_fin2"></p>
                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="row" style="margin-top: 15px">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Metode Bayar</label>
                            <div class="col-md-8">
                                <select id="metode_bayar" class="form-control" onchange="pilihMetode(this.value)">
                                    <option value="" >[Select One]</option>
                                    <option value="tunai">Tunai</option>
                                    <option value="transfer">Transfer</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div style="display: none" id="pilih_bank">
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 7px">Bank</label>
                                <div class="col-md-8">
                                    <select class="form-control select2" id="bank" style="width: 100%">
                                        <option value="">[Select One]</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-3" style="margin-top: 7px">No Kartu</label>
                                    <div class="col-md-8">
                                        <input type="number" id="no_rekening" style="margin-top: 7px" class="form-control">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fin" onclick="confirmSavePembayaranTagihan()"><i class="fa fa-arrow-right"></i> Save
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

<script type='text/javascript'>

    function pilihMetode(val){
        console.log(val);
        if(val != ''){
            if(val == 'transfer'){
                $('#pilih_bank').show();
            }else{
                $('#pilih_bank').hide();
            }
        }
    }

    function formatRupiah(angka) {
        if(angka != "" && angka != null && parseInt(angka) > 0){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        } else {
            return "0";
        }

    }

    var mapBiaya = [];
    var noNota = "";
    function showInvoice(idCheckup, idDetailCheckup) {
        selectPembayaran();
        $('#pilih_bank').hide();
        mapBiaya = [];
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
        var noSep;
        var cekTindakan = false;
        var jenisPasien = "";
        var uangMuka = 0;
        var metode="";
        var bukti = "";

        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#t_'+idDetailCheckup).attr('src',url).css('width', '30px', 'height', '40px');


        setTimeout(function () {

            var url = '<s:url value="/pages/images/icon_payment.ico"/>';
            $('#t_'+idDetailCheckup).attr('src',url).css('width', '', 'height', '');

            CheckupAction.listDataPasien(idDetailCheckup, function (response) {
                // dataPasien = response;
                console.log(response);
                if (response != null) {
                    // $.each(dataPasien, function (i, item) {
                        var tanggal = response.tglLahir;
                        var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                        noCheckup = response.noCheckup;
                        nik = response.noKtp;
                        namaPasien = response.nama;

                        if (response.jenisKelamin == "L") {
                            jenisKelamin = "Laki-Laki";
                        } else {
                            jenisKelamin = "Perempuan";
                        }

                        tglLahir = response.tempatLahir + ", " + dateFormat;
                        agama = response.agama;
                        suku = response.suku;
                        alamat = response.jalan;
                        provinsi = response.namaProvinsi;
                        kabupaten = response.namaKota;
                        kecamatan = response.namaKecamatan;
                        desa = response.namaDesa;
                        noSep = response.noSep;

                        if(response.metodePembayaran == "tunai"){
                            metode = "tunai";
                        }else if(response.metodePembayaran == "non_tunai"){
                            metode = "non_tunai";
                        }else{
                            metode = "bpjs";
                        }

                        $('#fin_no_rm').html(response.idPasien);

                    // });

                    $("#fin_id_pasien").val(response.idPasien);
                }
            });

            KasirRawatJalanAction.getListUangMuka(idDetailCheckup, "Y", function (response) {
                console.log(response);
                var str = "";
                $.each(response, function(i, item){
                    str += "<tr><td>"+item.stCreatedDate+"</td><td>"+item.id+"</td><td align='right' style='padding-right: 20px'>"+formatRupiah(item.dibayar)+"</td></tr>";
                    $("#fin_no_nota").val(item.noNota);
                    uangMuka = parseInt(uangMuka) + parseInt(item.dibayar);
                    bukti = item.id;
                });
                $("#body_uang_muka").html(str);
            });

            KasirRawatJalanAction.getListTindakanRawat(idDetailCheckup, function (response) {
                dataTindakan = response;
                console.log(response);
                if (dataTindakan != null) {
                    var total = 0;
                    var totalObat = 0;
                    var cekResep = false;
                    var ppn = "";
                    var ppnObat= 0;
                    var ppnObatAsuransi= 0;
                    var totalObatUmum = 0;
                    var cover = 0;

                    $.each(dataTindakan, function (i, item) {
                        var tindakan = "";
                        var tarif = "";
                        var kategori = ""
                        var btn = "";
                        var tgl = "";


                        if (item.namaTindakan != null && item.namaTindakan != '') {
                            tindakan = item.namaTindakan;
                        }

                        if (item.kategoriTindakanBpjs != null && item.kategoriTindakanBpjs != '') {
                            kategori = item.kategoriTindakanBpjs;
                        }

                        if (item.totalTarif != null && item.totalTarif != '') {
                            tarif = item.totalTarif;
                            total = (parseInt(total) + parseInt(tarif));
                        }

                        if (item.stTglTindakan != null) {
                            tgl = item.stTglTindakan;
                        }

                        if (item.keterangan == "resep") {

                            // untuk menhandel asuransi
                            btn = '<img id="btn' + item.idRiwayatTindakan + '"  class="hvr-grow" onclick="detailResep(\'' + item.idTindakan + '\',\'' + item.idRiwayatTindakan + '\')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">';
                            $("#fin_is_resep").val("Y");
                            cekResep = true;

                            if (jenisPasien == "asuransi"){
                                if (item.jenisPasien == "umum"){
                                    totalObatUmum = parseInt(totalObatUmum) + parseInt(item.totalTarif);
                                } else {
                                    totalObat = parseInt(totalObat) + parseInt(item.totalTarif);
                                }
                            } else {
                                totalObat = parseInt(totalObat) + parseInt(item.totalTarif);
                            }

                            <%--btn = '<img id="btn' + item.idRiwayatTindakan + '"  class="hvr-grow" onclick="detailResep(\'' + item.idTindakan + '\',\'' + item.idRiwayatTindakan + '\')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">';--%>
                            <%--$("#fin_is_resep").val("Y");--%>
                            <%--totalObat = parseInt(totalObat) + parseInt(item.totalTarif);--%>
                            <%--cekResep = true;--%>
                        }

                        table += '<tr id="row' + item.idRiwayatTindakan + '" >' +
                            "<td align='center'>" + btn + "</td>" +
                            "<td >" + tgl + "</td>" +
                            "<td>" + tindakan + "</td>" +
                            "<td align='right' style='padding-right: 20px'>" + formatRupiah(tarif) + "</td>" +
                            "</tr>";
                        jenisPasien = item.jenisPasien;
                    });

                    if(cekResep){

                        if (jenisPasien == "asuransi"){
                            ppnObat = (totalObatUmum* 0.1);
                            ppnObatAsuransi = (totalObat * 0.1);
                            var totalPpn = ppnObat + ppnObatAsuransi;
                            ppn = '<tr><td colspan="3">PPN Obat</td><td align="right" style="padding-right: 20px">' + formatRupiah(totalPpn) + '</td></tr>';
                        } else {
                            ppn = '<tr><td colspan="3">PPN Obat</td><td align="right" style="padding-right: 20px">' + formatRupiah(totalObat * 0.1) + '</td></tr>';
                            ppnObat = (totalObat * 0.1);
                        }

                    }

                    var strCover = "";
                    if (jenisPasien == "asuransi"){
                        KasirRawatJalanAction.getCoverAsuransi(idDetailCheckup, function (res) {
                            cover = parseInt(res);
                            console.log("Cover Biaya : "+cover);
                            strCover = '<tr><td colspan="3">Cover </td><td align="right" style="padding-right: 20px">' + formatRupiah(cover) + '</td></tr>';
                        });
                    }

                    var strBiaya = "";
                    if (jenisPasien == "asuransi"){
                        strBiaya = '<tr><td colspan="3">Total Biaya</td><td align="right" style="padding-right: 20px">' + formatRupiah((total - cover) + totalObatUmum + ppnObat + ppnObatAsuransi) + '</td></tr>';
                    } else {
                        strBiaya = '<tr><td colspan="3">Total Biaya</td><td align="right" style="padding-right: 20px">' + formatRupiah(total - uangMuka + ppnObat) + '</td></tr>';
                    }

//                    table = table + '<tr><td colspan="3">Total</td><td align="right" style="padding-right: 20px">' + formatRupiah(total) + '</td></tr>' + strCover + ppn +
//                        '<tr><td colspan="3">Total Biaya</td><td align="right" style="padding-right: 20px">' + formatRupiah(total - uangMuka + ppnObat) + '</td></tr>';

                    table = table + '<tr><td colspan="3">Total</td><td align="right" style="padding-right: 20px">' + formatRupiah(total) + '</td></tr>' + strCover + ppn + strBiaya;

                    //tunai
                    if (metode == "tunai") {
                        if (cekResep) {
                            //rawat jalan dengan obat
                            mapBiaya.push({"type": "uang_muka", "nilai": uangMuka});
                            mapBiaya.push({"type": "kas", "nilai": ((total - uangMuka) + ppnObat) });
                            mapBiaya.push({"type": "pendapatan_rawat_jalan_umum", "nilai": total-totalObat});
                            mapBiaya.push({"type": "pendapatan_obat_umum", "nilai": totalObat});
                            mapBiaya.push({"type": "ppn_keluaran", "nilai": ppnObat });
                        } else {
                            //rawat jalan tanpa obat
                            mapBiaya.push({"type": "uang_muka", "nilai": uangMuka});
                            mapBiaya.push({"type": "kas", "nilai": total - uangMuka});
                            mapBiaya.push({"type": "pendapatan_rawat_jalan_umum", "nilai": total});
                        }
                    } else if (jenisPasien == "asuransi"){
                        //non_tunai asuransi
                        if (cekResep){
                            mapBiaya.push({"type": "kas", "nilai": (total - cover) + totalObatUmum + ppnObat + ppnObatAsuransi });
                            mapBiaya.push({"type": "piutang_pasien_asuransi", "nilai": cover });
                            mapBiaya.push({"type": "pendapatan_rawat_jalan_asuransi", "nilai": cover - totalObat });
                            mapBiaya.push({"type": "pendapaatan_rawat_jalan_umum", "nilai": total - cover });
                            mapBiaya.push({"type": "pendapatan_obat_asuransi", "nilai": totalObat});
                            mapBiaya.push({"type": "pendapatan_obat_umum", "nilai": totalObatUmum});
                            mapBiaya.push({"type": "ppn_keluaran", "nilai": ppnObat + ppnObatAsuransi });
                        } else {
                            mapBiaya.push({"type": "kas", "nilai": total - cover});
                            mapBiaya.push({"type": "piutang_pasien_asuransi", "nilai": cover });
                            mapBiaya.push({"type": "pendapatan_rawat_jalan_asuransi", "nilai": cover });
                            mapBiaya.push({"type": "pendapatan_rawat_jalan_umum", "nilai": total- cover });
                        }

                        metode = "asuransi";
                    } else {
                        //non_tunai umum
                        mapBiaya.push({"type": "kas", "nilai": ((total - uangMuka) + ppnObat)  });
                        mapBiaya.push({"type": "piutang_pasien_non_bpjs", "nilai": ((total - uangMuka) + ppnObat) });
                    }
                }
            });

            console.log(mapBiaya);

            if(jenisPasien == "bpjs"){
                $('#no_sep_show').show();
            }else {
                $('#no_sep_show').hide();
            }

            $('#fin_jenis_pasien').html(jenisPasien.toUpperCase());
            $('#fin_no_sep').html(noSep);
            $('#fin_no_checkup').html(idDetailCheckup);
            $('#fin_nik').html(nik);
            $('#fin_nama').html(namaPasien);
            $('#fin_jenis_kelamin').html(jenisKelamin);
            $('#fin_tgl').html(tglLahir);
            $('#fin_agama').html(agama);
            $('#fin_suku').html(suku);
            $('#fin_alamat').html(alamat);
            $('#fin_provinsi').html(provinsi);
            $('#fin_kabupaten').html(kabupaten);
            $('#fin_kecamatan').html(kecamatan);
            $('#fin_desa').html(desa);
            $('#body_tindakan_fin').html(table);
            $('#fin_id_detail_checkup').val(idDetailCheckup);
            $('#fin_metode_bayar').val(metode);
            $('#fin_bukti').val(bukti);
            console.log(metode);
//            $('#save_fin').attr('onclick','confirmSaveFinalClaim(\''+idCheckup+'\')');
            $('#modal-invoice').modal({show:true, backdrop:'static'});
        }, 100);

    }

    function detailResep(idResep, idRiwayat){

        var tbody = "";
        KasirRawatJalanAction.getListDetailResep(idResep, function (response) {
            if(response.length > 0){

                $.each(response, function (i, item) {
                    tbody += '<tr>' +
                        '<td>'+item.namaObat+'</td>' +
                        '<td align="center">'+item.qty+'</td>' +
                        '<td>'+item.jenisSatuan+'</td>' +
                        '<td align="right" width="19%" style="padding-right: 19px"> '+formatRupiah(item.totalHarga)+'</td>' +
                        '</tr>';
                });

            }
        });

        var rowIndex = document.getElementById("row"+idRiwayat).rowIndex;
        var table = '<table class="table table-bordered"><tr bgcolor="#ffebcd">' +
            '<td>Nama Obat</td>' +
            '<td align="center" width="10%">Qty</td>' +
            '<td>Lembar</td>' +
            '<td align="center">Tarif (Rp.)</td></tr>' +
            '<tbody>'+tbody+'</tbody>'+
            '</table>';

        var newRow = $('<tr id="del'+idRiwayat+'"><td colspan="4">'+table+'</td></tr>');
        newRow.insertAfter($('#tabel_tindakan_fin tr:nth('+rowIndex+')'));
        var cancel = '<s:url value="/pages/images/icons8-cancel-25.png"/>';
        $('#btn'+idRiwayat).attr('src',cancel);
        $('#btn'+idRiwayat).attr('onclick', 'deleteRow(\''+idResep+'\',\''+idRiwayat+'\')');

    }

    function deleteRow(idResep, idRiwayat){
        $('#del'+idRiwayat).remove();
        var plus = '<s:url value="/pages/images/icons8-plus-25.png"/>';
        $('#btn'+idRiwayat).attr('src',plus);
        $('#btn'+idRiwayat).attr('onclick', 'detailResep(\''+idResep+'\',\''+idRiwayat+'\')');
    }

    function confirmSavePembayaranTagihan(){
        var metodeBayarDiAkhir = $('#metode_bayar').val();
        var kodeBank = $('#bank').val();

        if(metodeBayarDiAkhir != ''){

            if(metodeBayarDiAkhir == "transfer"){
                if(kodeBank != ''){
                    $('#modal-confirm-dialog').modal('show');
                    $('#save_con').attr('onclick','savePembayaranTagihan()');
                }else{
                    $('#warning_fin').show().fadeOut(5000);
                    $('#msg_fin').text("Silahkan pilih bank terlebih dahulu..!");
                }
            }else{
                $('#modal-confirm-dialog').modal('show');
                $('#save_con').attr('onclick','savePembayaranTagihan()');
            }
        }else{
            $('#warning_fin').show().fadeOut(5000);
            $('#msg_fin').text("Silahkan pilih metode pembayaran terlebih dahulu..!");
        }
        }

    function savePembayaranTagihan() {
        $('#modal-confirm-dialog').modal('hide');
        var noNota = $("#fin_no_nota").val();
        var idPasien = $("#fin_id_pasien").val();
        var idDetailCheckup = $("#fin_id_detail_checkup").val();
        var metodeBayarDiAkhir = $('#metode_bayar').val();
        var kodeBank = $('#bank').val();
        var isResep = $("#fin_is_resep").val();
        var metodeBayarDiAwal = $('#fin_metode_bayar').val();
        var bukti = $('#fin_bukti').val();
        var noRekening = $('#no_rekening').val();

        $('#save_fin').hide();
        $('#load_fin').show();
        dwr.engine.setAsync(true);
        var jsonString =  JSON.stringify(mapBiaya);

        KasirRawatJalanAction.savePembayaranTagihan(jsonString, idPasien, bukti, isResep, idDetailCheckup, metodeBayarDiAkhir, kodeBank, "JRJ", metodeBayarDiAwal, noRekening, {
            callback: function (response) {
                console.log(response.msg);
                if (response.status == "success") {
                    // alert("success");
                    $('#save_fin').show();
                    $('#load_fin').hide();
                    $('#modal-invoice').modal('hide');
                    $('#info_dialog').dialog('open');
                } else {
                    $('#save_fin').show();
                    $('#load_fin').hide();
                    $('#warning_fin').show().fadeOut(5000);
                    $('#msg_fin').text(response.msg);
                }
            }
        });
    }

    function selectPembayaran(){
        var option = '<option value="">[Select One]</option>';
        KasirRawatJalanAction.getListPembayaran(function (res) {
           if(res.length > 0){
               $.each(res, function (i, item) {
                   option += '<option value="'+item.coa+'">'+item.pembayaranName+'</option>';
               });
               $('#bank').html(option);
           }else{
               $('#bank').html(option);
           }
        });
    }

    function getCoverBiaya(idDetail){
        KasirRawatJalanAction.getCoverAsuransi(idDetail, function (res) {
            console.log("Cover Biaya : "+res);
            return res;
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>