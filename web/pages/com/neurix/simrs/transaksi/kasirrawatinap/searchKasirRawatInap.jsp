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
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#bayar_rawat_inap, #pembayaran_active').addClass('active');
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
            Kasir Rawat Inap
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Kasir Rawat Inap</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="kasirinapForm" method="post" namespace="/kasirinap"
                                    action="search_kasirinap.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Pasien</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="rawatInap.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="rawatInap.namaPasien"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'2':'Rujuk','3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status"
                                                  headerKey="3" headerValue="Selesai"
                                                  cssClass="form-control select2" disabled="true"/>
                                        <s:hidden name="rawatInap.statusPeriksa" value="3"></s:hidden>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status Bayar</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'Y':'Sudah Dibayar'}" cssStyle="margin-top: 7px"
                                                  id="statusBayar"
                                                  headerKey="" headerValue="Belum Dibayar" name="rawatInap.statusBayar"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Jenis Pasien</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'asuransi':'SISA ASURANSI', 'bpjs':'SISA BPJS'}" cssStyle="margin-top: 7px"
                                                  headerKey="umum" headerValue="UMUM" name="rawatInap.idJenisPeriksa"
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
                                            <s:textfield id="tgl_from" name="rawatInap.stTglFrom"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="rawatInap.stTglTo" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="kasirinapForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_kasirinap.action">
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
                                                <img class="spin" border="0"
                                                     style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
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
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
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
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>No Checkup</td>
                                <td>ID Pasien</td>
                                <td>Nama</td>
                                <td>Status Periksa</td>
                                <td>Status</td>
                                <td>Keterangan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfRawatJalan" var="row">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="statusPeriksaName"/></td>
                                    <td style="vertical-align: middle">
                                        <s:if test='#row.statusBayar == "Y"'>
                                            <span class="span-success"> sudah bayar</span>
                                        </s:if>
                                        <s:else>
                                            <span class="span-warning"> belum bayar</span>
                                        </s:else>
                                    </td>
                                    <td><s:property value="keteranganSelesai"/></td>
                                    <td align="center">
                                        <s:if test='#row.statusBayar == "Y"'>
                                            <s:url var="print_invo" namespace="/kasirinap"
                                                   action="printInvoice_kasirinap" escapeAmp="false">
                                                <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                <s:param name="jenis"><s:property value="idJenisPeriksa"/></s:param>
                                            </s:url>
                                            <s:a href="%{print_invo}" target="_blank">
                                                <img class="hvr-grow" style="cursor: pointer"
                                                     src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                            </s:a>
                                        </s:if>
                                        <s:else>
                                            <img id="t_<s:property value="idDetailCheckup"/>"
                                                 onclick="showInvoice('<s:property value="noCheckup"/>','<s:property
                                                         value="idDetailCheckup"/>')" class="hvr-grow"
                                                 src="<s:url value="/pages/images/icon_payment.ico"/>"
                                                 style="cursor: pointer;">
                                        </s:else>
                                        <s:if test='#row.idJenisPeriksa == "bpjs"'>
                                            <s:url var="print_invo_bpjs" namespace="/kasirinap"
                                                   action="printInvoice_kasirinap" escapeAmp="false">
                                                <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                <s:param name="jenis"><s:property value="idJenisPeriksa"/></s:param>
                                            </s:url>
                                            <s:a href="%{print_invo_bpjs}" target="_blank">
                                                <img class="hvr-grow" style="cursor: pointer"
                                                     src="<s:url value="/pages/images/icons8-print-25.png"/>">
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Total Tarif Rawat Inap
                    Pasien</h4>
            </div>
            <div class="modal-body" style="height: 70%; overflow-y: scroll">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr style="display: none;" id="no_sep_show">
                                    <td><b>No SEP</b></td>
                                    <td style="vertical-align: middle"><span class="label label-success"
                                                                             id="fin_no_sep"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No RM</b></td>
                                    <td><span id="fin_no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No Checkup</b></td>
                                    <td><span id="fin_no_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>ID Detail Checkup</b></td>
                                    <td><span id="fin_id_detail_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="fin_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="fin_nama"></span></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.col -->

                        <input type="hidden" id="fin_id_pasien"/>
                        <input type="hidden" id="fin_is_resep"/>
                        <input type="hidden" id="fin_metode_bayar"/>
                        <input type="hidden" id="fin_bukti"/>
                        <input type="hidden" id="h_no_checkup"/>
                        <input type="hidden" id="h_jenis_pasien"/>

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
                                <tr style="display: none;" id="label-asuransi">
                                    <td><b>Asuransi</b></td>
                                    <td><span id="fin_asuransi"></span></td>
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
                            <td>Tanggal</td>
                            <td>No Nota</td>
                            <td align="center" width="20%">Total Tarif (Rp.)</td>
                        </tr>
                        </thead>
                        <tbody id="body_uang_muka">
                        </tbody>
                    </table>
                </div>

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

                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_fin">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_fin2"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_fin"></p>
                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="row" style="margin-top: 15px" id="form_lebih_biaya">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>Metode Bayar</label>
                            <select style="margin-top: 7px" id="metode_bayar" class="form-control"
                                    onchange="pilihMetode(this.value)">
                                <option value="">[Select One]</option>
                                <option value="tunai">Tunai</option>
                                <option value="transfer">Transfer</option>
                            </select>
                        </div>
                    </div>
                    <div id="pilih_bank" style="display:none;">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Bank</label>
                                <select class="form-control select2" id="bank" style="width: 100%;">
                                    <option value="">[Select One]</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>No Kartu</label>
                                <input style="margin-top: 7px" type="number" id="no_rekening" class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fin" onclick="confirmSavePembayaranTagihan()"><i
                        class="fa fa-check"></i> Save
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-check"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function pilihMetode(val) {
        if (val != '') {
            if (val == 'transfer') {
                $('#pilih_bank').show();
            } else {
                $('#pilih_bank').hide();
            }
        }
    }

    function formatRupiah(angka) {
        if (angka != "" && angka != null && parseInt(angka) > 0) {
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        } else {
            return "0";
        }

    }

    var mapBiaya = [];
    function showInvoice(idCheckup, idDetailCheckup) {
        if(!cekSession()){
            selectPembayaran();
            $('#pilih_bank').hide();
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
            var metode = "";
            var bukti = "";
            var cekResep = false;
            var namaAsuransi = "";
            var idJenisPasien = "";
            var namaJenisPasien = "";

            var url = '<s:url value="/pages/images/spinner.gif"/>';
            $('#t_' + idDetailCheckup).attr('src', url).css('width', '30px', 'height', '40px');
            setTimeout(function () {
                var url = '<s:url value="/pages/images/icon_payment.ico"/>';
                $('#t_' + idDetailCheckup).attr('src', url).css('width', '', 'height', '');
                CheckupAction.listDataPasien(idDetailCheckup, function (response) {
                    if (response != null) {
                        jenisPasien = response.idJenisPeriksaPasien;
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
                        namaAsuransi = response.namaAsuransi;
                        idJenisPasien = response.idJenisPeriksaPasien;
                        namaJenisPasien = response.statusPeriksaName;

                        if (response.metodePembayaran == "tunai") {
                            metode = "tunai";
                        } else if (response.metodePembayaran == "non_tunai") {
                            metode = "non_tunai";
                        } else {
                            metode = "bpjs";
                        }

                        $('#fin_no_rm').html(response.idPasien);
                        $("#fin_id_pasien").val(response.idPasien);
                        $("#h_jenis_pasien").val(response.idJenisPeriksaPasien);
                    }
                });

                KasirRawatJalanAction.getListUangMuka(idCheckup, "Y", function (response) {
                    var str = "";
                    $.each(response, function (i, item) {
                        str += "<tr><td>" + item.stDate + "</td><td>" + item.id + "</td><td align='right' style='padding-right: 20px'>" + formatRupiah(item.dibayar) + "</td></tr>"
                        $("#fin_no_nota").val(item.noNota);
                        uangMuka = parseInt(uangMuka) + parseInt(item.dibayar);
                        bukti = item.id;
                    });
                    $("#body_uang_muka").html(str);
                });

                KasirRawatJalanAction.getListTindakanRawat(idCheckup, idJenisPasien, function (response) {
                    dataTindakan = response;
                    if (dataTindakan.length > 0) {
                        var total = 0;
                        var totalObat = 0;
                        var ppn = "";
                        var ppnObat = 0;
                        var ppnObatAsuransi = 0;
                        var totalObatUmum = 0;
                        var cover = 0;
                        var tindakanAsuransi = 0;
                        var tindakanUmum = 0;

                        $.each(dataTindakan, function (i, item) {
                            var tindakan = "";
                            var tarif = "";
                            var kategori = "";
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
                                if (item.keterangan != "resep") {
                                    if (item.jenisPasien == "asuransi") {
                                        tindakanAsuransi = parseInt(tindakanAsuransi) + parseInt(tarif);
                                    } else {
                                        tindakanUmum = parseInt(tindakanUmum) + parseInt(tarif);
                                    }
                                }
                            }

                            if (item.stTglTindakan != null) {
                                tgl = item.stTglTindakan;
                            }

                            if (item.keterangan == "resep") {

                                // untuk menhandel asuransi
                                btn = '<img id="btn' + item.idRiwayatTindakan + '"  class="hvr-grow" onclick="detailResep(\'' + item.idTindakan + '\',\'' + item.idRiwayatTindakan + '\')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">';
                                $("#fin_is_resep").val("Y");
                                cekResep = true;

                                if (jenisPasien == "asuransi") {
                                    if (item.jenisPasien == "umum") {
                                        totalObatUmum = parseInt(totalObatUmum) + parseInt(item.totalTarif);
                                    } else {
                                        totalObat = parseInt(totalObat) + parseInt(item.totalTarif);
                                    }
                                } else {
                                    totalObat = parseInt(totalObat) + parseInt(item.totalTarif);
                                }
                            }

                            table += '<tr id="row' + item.idRiwayatTindakan + '" >' +
                                "<td align='center'>" + btn + "</td>" +
                                "<td >" + tgl + "</td>" +
                                "<td>" + tindakan + "</td>" +
                                "<td align='right' style='padding-right: 20px'>" + formatRupiah(tarif) + "</td>" +
                                "</tr>";
                        });

                        if (cekResep) {

                            if (jenisPasien == "asuransi") {
                                ppnObat = (totalObatUmum * 0.1);
                                ppnObatAsuransi = (totalObat * 0.1);
                                var totalPpn = ppnObat + ppnObatAsuransi;
                                ppn = '<tr><td colspan="3">PPN Obat</td><td align="right" style="padding-right: 20px">' + formatRupiah(totalPpn) + '</td></tr>';
                            } else {
                                ppn = '<tr><td colspan="3">PPN Obat</td><td align="right" style="padding-right: 20px">' + formatRupiah(totalObat * 0.1) + '</td></tr>';
                                ppnObat = (totalObat * 0.1);
                            }

                        }
                        table = table + '<tr><td colspan="3">Total Jasa</td><td align="right" style="padding-right: 20px">' + formatRupiah(total) + '</td></tr>';
                        if (parseInt(uangMuka) > 0) {
                            table = table + '<tr><td colspan="3">Total Uang Muka</td><td align="right" style="padding-right: 20px">' + formatRupiah(uangMuka) + '</td>';
                        }
                        table = table + '<tr><td colspan="3">Total Biaya yang Dibayar</td><td align="right" style="padding-right: 20px">' + formatRupiah(total - uangMuka) + '</td></tr>';
                        if(parseInt(uangMuka) > parseInt(total)){
                            $('#form_lebih_biaya').hide();
                            var lebih = uangMuka - (total);
                            table = table +
                                '<tr style="background-color: yellow; color: black">' +
                                '<input type="hidden" id="kelebihan_biaya" value="'+lebih+'">'+
                                '<td colspan="3">Kelebihan Biaya</td>' +
                                '<td align="right" style="padding-right: 20px">' + formatRupiah(lebih) +
                                '</td>' +
                                '</tr>';
                        }else{
                            $('#form_lebih_biaya').show();
                        }
                    }
                });

                if (jenisPasien == "bpjs") {
                    $('#no_sep_show').show();
                } else if (jenisPasien == "asuransi") {
                    $('#label-asuransi').show();
                    $('#fin_asuransi').text(namaAsuransi);
                } else {
                    $('#no_sep_show').hide();
                }

                $('#fin_jenis_pasien').html(changeJenisPasien(jenisPasien, namaJenisPasien));
                $('#fin_no_sep').html(noSep);
                $('#fin_id_detail_checkup').html(idDetailCheckup);
                $('#fin_no_checkup').html(idCheckup);
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
                $('#fin_bukti').val(bukti);
                $('#fin_metode_bayar').val(metode);
                $('#h_no_checkup').val(idCheckup);
                $('#body_tindakan_fin').html(table);
                $('#fin_id_detail_checkup').val(idDetailCheckup);
                $('#modal-invoice').modal({show: true, backdrop: 'static'});
            }, 100);
        }
    }

    function detailResep(idResep, idRiwayat) {
        if(!cekSession()){
            var tbody = "";
            KasirRawatInapAction.getListDetailResep(idResep, function (response) {
                if (response.length > 0) {

                    $.each(response, function (i, item) {
                        tbody += '<tr>' +
                            '<td>' + item.namaObat + '</td>' +
                            '<td align="center">' + item.qty + '</td>' +
                            '<td>' + item.jenisSatuan + '</td>' +
                            '<td align="right" width="19%" style="padding-right: 19px"> ' + formatRupiah(item.totalHarga) + '</td>' +
                            '</tr>';
                    });
                }
            });

            var rowIndex = document.getElementById("row" + idRiwayat).rowIndex;
            var table = '<table class="table table-bordered"><tr bgcolor="#ffebcd">' +
                '<td>Nama Obat</td>' +
                '<td align="center" width="10%">Qty</td>' +
                '<td>Lembar</td>' +
                '<td align="center">Tarif (Rp.)</td></tr>' +
                '<tbody>' + tbody + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del' + idRiwayat + '"><td colspan="4">' + table + '</td></tr>');
            newRow.insertAfter($('#tabel_tindakan_fin tr:nth(' + rowIndex + ')'));
            var cancel = '<s:url value="/pages/images/icons8-cancel-25.png"/>';
            $('#btn' + idRiwayat).attr('src', cancel);
            $('#btn' + idRiwayat).attr('onclick', 'deleteRow(\'' + idResep + '\',\'' + idRiwayat + '\')');
        }
    }

    function deleteRow(idResep, idRiwayat) {
        $('#del' + idRiwayat).remove();
        var plus = '<s:url value="/pages/images/icons8-plus-25.png"/>';
        $('#btn' + idRiwayat).attr('src', plus);
        $('#btn' + idRiwayat).attr('onclick', 'detailResep(\'' + idResep + '\',\'' + idRiwayat + '\')');
    }

    function confirmSavePembayaranTagihan() {
        var metodeBayarDiAkhir = $('#metode_bayar').val();
        var kodeBank = $('#bank').val();

        if($('#kelebihan_biaya').val() != undefined){
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick','savePembayaranTagihan()');
        }else{
            if (metodeBayarDiAkhir != '') {
                if (metodeBayarDiAkhir == "transfer") {
                    if (kodeBank != '') {
                        $('#modal-confirm-dialog').modal('show');
                        $('#save_con').attr('onclick', 'savePembayaranTagihan()');
                    } else {
                        $('#warning_fin').show().fadeOut(5000);
                        $('#msg_fin').text("Silahkan pilih bank terlebih dahulu..!");
                    }
                } else {
                    $('#modal-confirm-dialog').modal('show');
                    $('#save_con').attr('onclick', 'savePembayaranTagihan()');
                }
            } else {
                $('#warning_fin').show().fadeOut(5000);
                $('#msg_fin').text("Silahkan pilih metode pembayaran terlebih dahulu..!");
            }
        }
    }

    function savePembayaranTagihan() {
        $('#modal-confirm-dialog').modal('hide');
        if(!cekSession()){
            var idPasien = $("#fin_id_pasien").val();
            var idDetailCheckup = $("#fin_id_detail_checkup").val();
            var metodeBayarDiAkhir = $('#metode_bayar').val();
            var kodeBank = $('#bank').val();
            var isResep = $("#fin_is_resep").val();
            var metodeBayarDiAwal = $('#fin_metode_bayar').val();
            var bukti = $('#fin_bukti').val();
            var noRekening = $('#no_rekening').val();
            var noCheckup = $('#h_no_checkup').val();
            var jenisPasien = $('#h_jenis_pasien').val();
            var lebihBiaya = "";
            if($('#kelebihan_biaya').val() != ''){
                lebihBiaya = $('#kelebihan_biaya').val();
            }
            $('#save_fin').hide();
            $('#load_fin').show();
            var dataObj = {
                'id_pasien': idPasien,
                'with_obat': isResep,
                'metode_bayar': metodeBayarDiAkhir,
                'kode_bank': kodeBank,
                'type': "JRI",
                'jenis': metodeBayarDiAwal,
                'no_rekening': noRekening,
                'no_checkup': noCheckup,
                'lebih_biaya': lebihBiaya,
                'jenis_pasien': jenisPasien
            }
            dwr.engine.setAsync(true);
            var jsonString = JSON.stringify(dataObj);
            KasirRawatJalanAction.savePembayaranTagihan(jsonString, {
                callback: function (response) {
                    if (response.status == "success") {
                        $('#save_fin').show();
                        $('#load_fin').hide();
                        $('#modal-invoice').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('body').scrollTop(0);
                        printInvoice(idDetailCheckup, jenisPasien);
                    } else {
                        $('#save_fin').show();
                        $('#load_fin').hide();
                        $('#warning_fin').show().fadeOut(5000);
                        $('#msg_fin').text(response.msg);
                    }
                }
            });
        }
    }

    function selectPembayaran() {
        if(!cekSession()){
            var option = '<option value="">[Select One]</option>';
            KasirRawatJalanAction.getListPembayaran(function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        option += '<option value="' + item.coa + '">' + item.pembayaranName + '</option>';
                    });
                    $('#bank').html(option);
                } else {
                    $('#bank').html(option);
                }
            });
        }
    }

    function getCoverBiaya(idDetail) {
        KasirRawatJalanAction.getCoverAsuransi(idDetail, function (res) {
            return res;
        });
    }

    function printInvoice(id, jenis){
        window.open('printInvoice_kasirinap?id='+id+'&jenis='+jenis,'_blank');
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>