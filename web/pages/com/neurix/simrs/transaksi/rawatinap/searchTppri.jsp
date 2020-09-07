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
        .garis{
            color: #ddd;
        }
        .blink_me {
            animation: blinker 3.0s linear infinite;
        }

        @keyframes blinker {
            50% {
                opacity: 0;
            }
        }
    </style>
    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#tppri').addClass('active');
        });

    </script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RawatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/spinner.js"/>'></script>

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
            Tempat Pendaftaran Pasien Rawat Inap (TPPRI)
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Pendaftaran Rawat Inap Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="rawatInapForm" method="post" namespace="/rawatinap" action="searchTppri_rawatinap.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No RM</label>
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
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Status</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:select list="#{'0':'Antrian','1':'Periksa','2':'Rujuk','3':'Selesai'}" cssStyle="margin-top: 7px"--%>
                                                  <%--id="status" name="rawatInap.statusPeriksa"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Kelas Ruangan</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:action id="initComboKelas" namespace="/checkupdetail"--%>
                                                  <%--name="getListComboKelasRuangan_checkupdetail"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px" onchange="$(this).css('border',''); listSelectRuangan(this)"--%>
                                                  <%--list="#initComboKelas.listOfKelasRuangan" id="kelas_kamar"--%>
                                                  <%--name="rawatInap.idKelas"--%>
                                                  <%--listKey="idKelasRuangan"--%>
                                                  <%--listValue="namaKelasRuangan"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-sm-3" style="display: none;" id="load_ruang">--%>
                                        <%--<img border="0" src="<s:url value="/pages/images/spinner.gif"/>" style="cursor: pointer; width: 45px; height: 45px"><b style="color: #00a157;">Sedang diproses...</b></div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Ruangan</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<select style="margin-top: 7px" class="form-control select2" id="nama_ruangan" name="rawatInap.idRuang">--%>
                                            <%--<option value=''>[Select One]</option>--%>
                                        <%--</select>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="rawatInap.stTglFrom" cssClass="form-control"
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
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="rawatInapForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initTppri_rawatinap.action">
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
                                        <div class="col-md-1">
                                            <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker  color-picker pull-left">
                                        </div>
                                        <div class="col-md-9">
                                            <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72" value="1">
                                        </div>
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
                                <td>No RM</td>
                                <td>Nama</td>
                                <td>Jenis Pasien</td>
                                <td>Tindak Lanjut</td>
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
                                        <img id="t_<s:property value="idDetailCheckup"/>" onclick="detail('<s:property value="noCheckup"/>','<s:property value="idDetailCheckup"/>','<s:property value="tindakLanjut"/>','<s:property value="keteranganSelesai"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" style="cursor: pointer;">
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
</div>


<div class="modal fade" id="modal-detail">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> TPPRI</h4>
            </div>
            <div class="modal-body" style="height: 450px;overflow-y: scroll;">
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
                                    <td><span id="no_detail_checkup"></span></td>
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
                                <tr>
                                    <td><b>Tempat, Tanggal Lahir</b></td>
                                    <td><span id="tgl"></span></td>
                                </tr>
                                <tr style="display: none" id="kelas_bpjs">
                                    <td><b>Kelas Kamar BPJS</b></td>
                                    <td><span id="id_kelas"></span></td>
                                </tr>
                                <tr>
                                    <td>Rekam Medis</td>
                                    <td>
                                        <div class="btn-group dropdown">
                                            <button onclick="getListRekamMedis('tppri', '', $('#h_id_detail_pasien').val())" type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Asesmen
                                            </button>
                                            <button onclick="getListRekamMedis('tppri', '', $('#h_id_detail_pasien').val())" type="button" class="btn btn-primary dropdown-toggle"
                                                    data-toggle="dropdown" style="height: 34px">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu" id="asesmen_rj">
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <input type="hidden" id="h_id_pasien">
                        <input type="hidden" id="h_id_detail_pasien">
                        <input type="hidden" id="h_no_checkup">
                        <input type="hidden" id="h_jenis_pasien">
                        <input type="hidden" id="h_tindak_lanjut">
                        <input type="hidden" id="h_kategori">

                        <div class="col-md-6">
                            <table class="table table-striped" >
                                <tr>
                                    <td width="30%"><b>Alamat</b></td>
                                    <td><span id="alamat"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Poli</b></td>
                                    <td><span id="poli"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Diagnosa</b></td>
                                    <td><span id="diagnosa"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tindak Lanjut</b></td>
                                    <td><span id="tindak_lanjut"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jenis Pasien</b></td>
                                    <td><span style="background-color: #286090; color: white; border-radius: 5px; border: 1px solid black; padding: 5px" id="jenis_pasien"></span></td>
                                </tr>
                                <tr style="display: none" id="form-id_kelas">
                                    <td><b>Hak Kamar</b></td>
                                    <td><span style="background-color: #ec971f; color: white; border-radius: 5px; border: 1px solid black; padding: 5px" id="hak_kamar"></span></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_war"></p>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b ><i class="fa fa-stethoscope"></i> Instruksi Tindak Lanjut</b></label>
                    </div>
                    <div class="modal" id="top_up"></div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-2">
                                <button onclick="tambahDPJP()" class="btn btn-success"> <i class="fa fa-plus"></i> Tambah DPJP</button>
                            </div>
                            <div class="col-md-6">
                                <p style="color: red; display: none" id="msg_dpjp" class="blink_me">*silahkan pilih dokter terlebih dahulu...</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Dokter DPJP</label>
                            <div class="col-md-4">
                                <select class="form-control select2 id_dpjp" id="dokter_dpjp_1" style="width: 100%" onchange="$('#msg_dpjp').hide()">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control dpjp" style="margin-top: 7px" value="DPJP 1" disabled="disabled">
                            </div>
                        </div>
                    </div>
                    <div id="set_dpjp"></div>
                    <div class="row" style="display: none" id="form-kelas_kamar">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Kelas Kamar</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="kelas_kamar" style="width: 100%">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" style="display: none" id="form-kamar">
                            <label class="col-md-3" style="margin-top: 10px"><span id="label_kamar"></span></label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="kamar" style="width: 100%">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="form-metode_pembayaran">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Metode Pembayaran</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="metode_bayar" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="tunai">Tunai</option>
                                    <option value="non_tunai">Non Tunai</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="form-uang_muka">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Uang Muka</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        Rp.
                                    </div>
                                    <input class="form-control" id="val_uang_muka" oninput="convertRp(this.id, this.value)">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fin" onclick="confirm()"><i class="fa fa-arrow-right"></i> Save
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

<div id="modal-temp"></div>

<div class="modal fade" id="modal-confirm-rm">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Konfirmasi
                </h4>
            </div>
            <div class="modal-body">
                <h4 class="text-center" id="tanya"></h4>
                <h4 class="text-center" id="print_form"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Tidak
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con_rm"><i class="fa fa-check"></i> Ya            </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript' src='<s:url value="/dwr/interface/RingkasanPasienAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>

<script type="text/javascript" src="<s:url value="/pages/dist/js/datapasien.js"/>"></script>
<script type="text/javascript" src="<s:url value="/pages/dist/js/ringkasanpasien.js"/>"></script>
<script type="text/javascript" src="<s:url value="/pages/dist/js/paintTtd.js"/>"></script>

<script type='text/javascript'>

    var isReadRM = false;
    var contextPath = '<%= request.getContextPath() %>';
    var tempidRm = "";
    var noCheckup = "";
    var tempTensi = "";
    var tempSuhu = "";
    var tempNadi = "";
    var tempRr = "";
    var tempBerat = "";
    var tempTinggi = "";
    var tempAnmnesa = "";
    var idDetailCheckup = "";
    var namaPasien = "";
    var alamatLengkap = "";
    var noBpjs = "";
    var umur = "";
    var jenisKelamin = "";
    var diagnosa = "";
    var idPasien = "";
    var tglLahir = "";

    function convertRp(id, val) {
        $('#'+id).val(formatRupiahAtas2(val));
    }
    function detail(noCheckup, idDCP, tindakLanjut, keteranganSelesai) {
        idDetailCheckup = idDCP;
        startSpinner('t_', idDCP);
        dwr.engine.setAsync(true);
        CheckupAction.listDataPasien(idDCP,
            {callback:function (res) {
                    if (res.idPasien != null) {
                        stopSpinner('t_', idDCP);
                        $('#kelas_kamar').html('');
                        $('#kamar').html('');
                        getDokterDpjp('dokter_dpjp_1', null);
                        if (res.idJenisPeriksaPasien == "umum") {
                            $('#form-metode_pembayaran').show();
                            $('#form-uang_muka').show();
                        } else {
                            $('#form-metode_pembayaran').hide();
                            $('#form-uang_muka').hide();
                        }

                        if (tindakLanjut == "rawat_inap") {
                            getKelasKamar('rawat_inap');
                            $('#kelas_kamar').attr('onchange', 'getKamar(this.value, \'rawat_inap\')');
                            $('#label_kamar').text('Kamar');
                            $('#form-kelas_kamar').show();
                            $('#form-kamar').show();
                            $('#h_kategori').val('rawat_inap');
                        } else if (tindakLanjut == "rawat_intensif") {
                            getKamar(null, 'rawat_intensif');
                            getKelasKamar('rawat_intensif');
                            $('#label_kamar').text('Kamar Intensif');
                            $('#form-kamar').show();
                            $('#form-kelas_kamar').show();
                            $('#h_kategori').val('rawat_intensif');
                        } else if (tindakLanjut == "rawat_isolasi") {
                            getKamar(null, 'rawat_isolasi');
                            getKelasKamar('rawat_isolasi');
                            $('#label_kamar').text('Kamar Isolasi');
                            $('#form-kamar').show();
                            $('#form-kelas_kamar').show();
                            $('#h_kategori').val('rawat_isolasi');
                        } else if (tindakLanjut == "kamar_operasi") {
                            getKamar(null, 'kamar_operasi');
                            getKelasKamar('kamar_operasi');
                            $('#label_kamar').text('Kamar Operasi');
                            $('#form-kamar').show();
                            $('#form-kelas_kamar').show();
                            $('#h_kategori').val('kamar_operasi');
                        } else if (tindakLanjut == "ruang_bersalin") {
                            getKamar(null, 'ruang_bersalin');
                            getKelasKamar('ruang_bersalin');
                            $('#label_kamar').text('Kamar Bersalin');
                            $('#form-kamar').show();
                            $('#form-kelas_kamar').show();
                            $('#h_kategori').val('ruang_bersalin');
                        } else {
                            $('#form-kamar').hide();
                            $('#form-kelas_kamar').hide();
                        }

                        var jk = "";
                        var alamat = res.namaDesa + ", " + res.namaKecamatan + ", " + res.namaKota;
                        var diagnosa = res.diagnosa + "-" + res.namaDiagnosa;
                        if (res.jenisKelamin == "L") {
                            jk = "Laki-Laki";
                        } else {
                            jk = "Perempuan";
                        }

                        if (res.idJenisPeriksaPasien == "ptpn" || res.idJenisPeriksaPasien == "bpjs") {
                            $('#form-id_kelas').show();
                            $('#hak_kamar').text(res.kelasPasien);
                        } else {
                            $('#form-id_kelas').hide();
                        }

                        $('#no_rm').html(res.idPasien);
                        $('#no_detail_checkup').html(noCheckup);
                        $('#nik').html(res.noKtp);
                        $('#nama').html(res.nama);
                        $('#jenis_kelamin').html(jk);
                        $('#tgl').html(res.tempatLahir + ", " + converterDate(new Date(res.tglLahir)));
                        $('#alamat').html(alamat);
                        $('#poli').html(res.namaPelayanan);
                        $('#jenis_pasien').html(res.statusPeriksaName);
                        $('#diagnosa').html(diagnosa);
                        $('#h_id_pasien').val(res.idPasien);
                        $('#h_id_detail_pasien').val(res.idDetailCheckup);
                        $('#h_id_pelayanan').val(res.idPelayanan);
                        $('#h_jenis_pasien').val(res.idJenisPeriksaPasien);
                        $('#h_no_checkup').val(res.noCheckup);
                        $('#h_tindak_lanjut').val(tindakLanjut);
                        $('#tindak_lanjut').html(keteranganSelesai);
                        $('#save_fin').show();
                        $('#load_fin').hide();
                        $('#dokter_dpjp_1').removeAttr('disabled');
                        $('#kelas_kamar').removeAttr('disabled');
                        $('#msg_dpjp').hide();
                        setLabelJenisPasien('jenis_pasien' ,res.idJenisPeriksaPasien);
                        $('#modal-detail').modal({show: true, backdrop: 'static'});
                    }
                }
        });
    }

    function tambahDPJP(){
        var cekDpjp = $('.dpjp').length;
        var cekValue = $('#dokter_dpjp_'+cekDpjp).val();
        if(cekValue != ''){
            var idDpjp = $('.id_dpjp');
            var idIsi = "";
            var aw = "(";
            var ak = ")";
            var valId = null;
            $.each(idDpjp, function (i, item) {
                if(item.value != '' && item.value){
                    var sp = item.value.split("|");
                    if(idIsi != ''){
                        idIsi = idIsi+", "+"'"+sp[0]+"'";
                    }else{
                        idIsi = "'"+sp[0]+"'";
                    }
                }
            });
            if(idIsi != ''){
                valId = aw + idIsi + ak;
            }
            var count = cekDpjp + 1;
            $('#dokter_dpjp_'+cekDpjp).attr('disabled', 'disabled');
            var html = '<div class="row" id="set_dpjp_'+count+'">\n' +
                '<div class="form-group">\n' +
                '    <div class="col-md-offset-3 col-md-4">\n' +
                '        <select class="form-control select2 id_dpjp" id="dokter_dpjp_'+count+'" style="width: 100%"\n' +
                '            <option value=\'\'>[Select One]</option>\n' +
                '        </select>\n' +
                '    </div>\n' +
                '    <div class="col-md-4">\n' +
                '        <input class="form-control dpjp" style="margin-top: 7px" value="DPJP '+count+'" disabled="disabled">\n' +
                '    </div>\n' +
                '    <div class="col-md-1">\n' +
                '        <button onclick="delDpjp(\'set_dpjp_'+count+'\')" style="margin-top: 8px; margin-left: -20px" class="btn btn-danger"><i class="fa fa-trash"></i></button>\n' +
                '    </div>\n' +
                '</div>\n'+
                '</div>';
            $('#set_dpjp').append(html);
            $('.select2').select2();
            getDokterDpjp('dokter_dpjp_'+count, valId);
        }else{
            $('#msg_dpjp').show();
        }
    }

    function delDpjp(id) {
        $('#'+id).remove();
    }

    function getDokterDpjp(id, idDpjp) {
        var option = '<option value="">[Select One]</option>';
        CheckupAction.getListDokterByBranchId(idDpjp, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idDokter + '|' + item.idPelayanan + '|'+item.sip+'|'+item.namaDokter+'">' + item.namaDokter + ' - ' + item.namaPelayanan + '</option>';
                });
                $('#'+id).html(option);
            } else {
                $('#'+id).html(option);
            }
        });
    }

    function getKelasKamar(kategori){
        var option = '';
        if(kategori == "rawat_inap"){
            option = '<option value="">[Select One]</option>';
        }
        dwr.engine.setAsync(true);
        CheckupDetailAction.getListKelasKamar(kategori, function (res) {
           if(res.length > 0){
               $.each(res, function (i, item) {
                   option += '<option value="' + item.idKelasRuangan + '">' + item.namaKelasRuangan + '</option>';
               });
               if(kategori == 'rawat_inap'){
                   $('#kelas_kamar').html(option);
               }else{
                   $('#kelas_kamar').html(option);
                   $('#kelas_kamar').attr('disabled','disabled');
               }
           }else{
               $('#kelas_kamar').html(option);
           }
        });
    }

    function getKamar(idKelas, kategori){
        var option = '<option value="">[Select One]</option>';
        dwr.engine.setAsync(true);
        CheckupDetailAction.listRuangan(idKelas, true,  kategori, {callback:  function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idRuangan + "'>" + item.noRuangan + "-" + item.namaRuangan + "</option>";
                    });
                    $('#kamar').html(option);
                } else {
                    $('#kamar').html(option);
                }
            }
        });
    }

    function confirm(){
        var cek = false;
        var jenisPasien  = $('#h_jenis_pasien').val();
        var tindakLanjut = $('#h_tindak_lanjut').val();
        var kelasKamar = $('#kelas_kamar').val();
        var kamar = $('#kamar').val();
        var metodeBayar = $('#metode_bayar').val();
        var uangMuka = $('#val_uang_muka').val();
        var dataDpjp    = [];
        var dataDokter = $('.id_dpjp');
        var dataPrio = $('.dpjp');
        $.each(dataDokter, function (i, item) {
            if(item.value != ''){
                var data = item.value.split("|");
                var idDokter = data[0];
                var idPelayanan = data[1];
                dataDpjp.push({
                    'id_dpjp':idDokter,
                    'id_pelayanan':idPelayanan,
                    'prioritas':dataPrio[i].value
                })
            }
        });

        if(tindakLanjut == "rawat_inap"){
            if(dataDpjp.length > 0 && kelasKamar && kamar != ''){
                if(jenisPasien == "umum"){
                    if(metodeBayar && uangMuka != ''){
                        cek = true;
                    }
                }else{
                    cek = true;
                }
            }
        }else{
            if(dataDpjp.length > 0 && kamar != ''){
                if(jenisPasien == "umum"){
                    if(metodeBayar && uangMuka != ''){
                        cek = true;
                    }
                }else{
                    cek = true;
                }
            }
        }

        if(cek){
            $('#modal-confirm-dialog').modal({show:true, backdrop:'static'});
            $('#save_con').attr('onclick','saveTppri()');
        }else{
            // $('#').scrollTop(0);
            $('#warning').show().fadeOut(5000);
            $('#msg_war').text("Silahkan cek kembali data inputan anda...!");
        }

    }

    function saveTppri(){
        $('#modal-confirm-dialog').modal('hide');
        var data = "";
        var dataDpjp    = [];
        var dataDokter = $('.id_dpjp');
        var dataPrio = $('.dpjp');
        var idPasien = $('#h_id_pasien').val();
        var noCheckup = $('#h_no_checkup').val();
        var idDetailCheckup = $('#h_id_detail_pasien').val();
        var jenisPasien = $('#h_jenis_pasien').val();
        var tindakLanjut = $('#h_tindak_lanjut').val();
        var kategori = $('#h_kategori').val();
        var metodeBayar = $('#metode_bayar').val();
        var valUangMuka = $('#val_uang_muka').val();
        var uangMuka = "";
        if(valUangMuka != undefined){
            uangMuka = valUangMuka.replace(/[.]/g, '');
        }
        var kelasKamar = $('#kelas_kamar').val();
        var kamar = $('#kamar').val();
        $.each(dataDokter, function (i, item) {
            if(item.value != ''){
                var data = item.value.split("|");
                var idDokter = data[0];
                var idPelayanan = data[1];
                dataDpjp.push({
                    'id_dpjp':idDokter,
                    'id_pelayanan':idPelayanan,
                    'prioritas':dataPrio[i].value
                });
            }
        });

        data = {
            'id_pasien': idPasien,
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'jenis_pasien': jenisPasien,
            'tindak_lanjut': tindakLanjut,
            'data_dpjp': dataDpjp,
            'kelas_kamar': kelasKamar,
            'kamar': kamar,
            'metode_pembayaran': metodeBayar,
            'uang_muka': uangMuka
        }

        var result = JSON.stringify(data);
        $('#save_fin').hide();
        $('#load_fin').show();
        dwr.engine.setAsync(true);
        RawatInapAction.saveTppri(result,
            { callback : function (response) {
                if (response.status == "success") {
                    $('#save_fin').show();
                    $('#load_fin').hide();
                    $('#info_dialog').dialog('open');
                    $('#modal-detail').modal('hide');
                } else {
                    $('#save_fin').show();
                    $('#load_fin').hide();
                    $('#warning').show().fadeOut(5000);
                    $('#msg_war').text(response.msg);
                }
            }
        });
    }

    function printPernyataan(kode, idRm, flag, namaRm) {
        $('#tanya').text("Apakah anda yakin print ?");
        $('#print_form').text(namaRm);
        $('#save_con_rm').attr('onclick','printPernyataanRM(\''+kode+'\', \''+idRm+'\')');
        $('#modal-confirm-rm').modal('show');
    }

    function printPernyataanRM(kode, idRM){
        var idDetailCheckup = $('#h_id_detail_pasien').val();
        window.open(contextPathHeader + '/rekammedik/printSuratPernyataan_rekammedik?id=' + idDetailCheckup + '&tipe=' + kode + '&ids=' + idRM, '_blank');
        $('#modal-confirm-rm').modal('hide');
    }

    function getListRekamMedis(tipePelayanan, jenis, id) {
        var li = "";
        CheckupAction.getListRekammedisPasien(tipePelayanan, jenis, id, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var cek = "";
                    var tgl = "";
                    var icons = '<i class="fa fa-file-o"></i>';
                    var icons2 = '<i class="fa fa-print"></i>';
                    var tol = "";
                    var tolText = "";
                    var labelTerisi = "";
                    var constan = 0;
                    var terIsi = 0;
                    var labelPrint = "";
                    var terIsiPrint = "";

                    if(item.jumlahKategori != null){
                        constan = item.jumlahKategori;
                    }
                    if(item.terisi != null && item.terisi !=''){
                        terIsi = item.terisi;
                        terIsiPrint = item.terisi;
                    }

                    if (constan == terIsi || parseInt(terIsi) > parseInt(constan)) {
                        var conver = "";
                        if (item.createdDate != null) {
                            conver = converterDate(new Date(item.createdDate));
                            tgl = '<label class="label label-success">' + conver + '</label>';
                            tol = 'class="box-rm"';
                            tolText = '<span class="box-rmtext">Tanggal mengisi ' + conver + '</span>';
                        }
                        icons = '<i class="fa fa-check" style="color: #449d44"></i>';
                        icons2 = '<i class="fa fa-check" style="color: #449d44"></i>';
                    }

                    labelTerisi = '<span style="color: #367fa9; font-weight: bold">'+terIsi+'/'+constan+'</span>';
                    labelPrint = '<span style="color: #367fa9; font-weight: bold">'+terIsiPrint+'</span>';

                    if (item.jenis == 'ringkasan_rj') {
                        li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-file-o"></i>' + item.namaRm + '</a></li>'
                    } else {
                        if (item.keterangan == 'form') {
                            li += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')"><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '">' + icons + item.namaRm + ' ' +labelTerisi +tolText+'</a></li>'
                        } else if (item.keterangan == "surat") {
                            li += '<li '+tol+'><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\',\'' + item.namaRm + '\')' + '">' + icons2 + item.namaRm + ' '+ labelPrint + tolText+ '</a></li>'
                        }
                    }
                });
                $('#asesmen_rj').html(li);
            }
        });
    }

    function loadModalRM(jenis) {
        var context = contextPath + '/pages/modal/modal-default.jsp';
        if (jenis != "") {
            context = contextPath + '/pages/modal/modal-'+jenis+'.jsp';
        }
        $('#modal-temp').load(context, function (res, status, xhr) {
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>