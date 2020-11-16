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
        .garis {
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

        $(document).ready(function () {
            $('#pendaftaran_active, #bayar_rawat_inap').addClass('active');
            $('#pendaftaran_open').addClass('menu-open');
        });

    </script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RawatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/spinner.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>

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
                            <s:form id="rawatInapForm" method="post" namespace="/rawatinap"
                                    action="searchTppri_rawatinap.action" theme="simple" cssClass="form-horizontal">
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
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag Tppri</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'Y':'Non-Active'}" id="flag" name="rawatInap.flagTppri"
                                                  headerKey="" headerValue="Active" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="rawatInapForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initTppri_rawatinap.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <button onclick="showDaftar()" type="button" class="btn btn-primary">
                                            <i class="fa fa-plus"></i> Daftar Anak RB
                                        </button>
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
                                        <div class="col-md-1">
                                            <input type="color" style="margin-left: -6px; margin-top: -8px"
                                                   class="js-color-picker  color-picker pull-left">
                                        </div>
                                        <div class="col-md-9">
                                            <input type="range" style="margin-top: -8px" class="js-line-range" min="1"
                                                   max="72" value="1">
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
                            <thead>
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
                                        <img id="t_<s:property value="idDetailCheckup"/>"
                                             onclick="detail('<s:property value="noCheckup"/>','<s:property
                                                     value="idDetailCheckup"/>','<s:property value="tindakLanjut"/>','<s:property value="keteranganSelesai"/>')" class="hvr-grow"
                                             src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>"
                                             style="cursor: pointer;">
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
            <div class="modal-body" style="height: 70%;overflow-y: scroll;">
                <div class="alert alert-success alert-dismissible" style="display: none" id="success">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_suc"></p>
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>No RM</b></td>
                                    <td><span id="no_rm"></span></td>
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
                                    <td><b>Rekam Medis</b></td>
                                    <td>
                                        <div class="btn-group dropdown">
                                            <button onclick="getListRekamMedis('tppri', '', $('#h_id_detail_pasien').val())"
                                                    type="button" class="btn btn-primary"><i class="fa fa-edit"></i>
                                                Asesmen
                                            </button>
                                            <button onclick="getListRekamMedis('tppri', '', $('#h_id_detail_pasien').val())"
                                                    type="button" class="btn btn-primary dropdown-toggle"
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
                            <table class="table table-striped">
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
                                    <td><span
                                            style="background-color: #286090; color: white; border-radius: 5px; border: 1px solid black; padding: 5px"
                                            id="jenis_pasien"></span></td>
                                </tr>
                                <tr style="display: none" id="form-id_kelas">
                                    <td><b>Hak Kamar</b></td>
                                    <td><span
                                            style="background-color: #ec971f; color: white; border-radius: 5px; border: 1px solid black; padding: 5px"
                                            id="hak_kamar"></span></td>
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
                        <label class="col-md-12"><b><i class="fa fa-stethoscope"></i> Instruksi Tindak
                            Lanjut</b></label>
                    </div>
                    <div class="modal" id="top_up"></div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-2">
                                <button onclick="tambahDPJP('')" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                                    DPJP
                                </button>
                            </div>
                            <div class="col-md-6">
                                <p style="color: red; display: none" id="msg_dpjp" class="blink_me">*silahkan pilih
                                    dokter terlebih dahulu...</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Dokter DPJP</label>
                            <div class="col-md-4">
                                <select class="form-control select2 id_dpjp" id="dokter_dpjp_1" style="width: 100%"
                                        onchange="$('#msg_dpjp').hide()">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control dpjp" style="margin-top: 7px" id="jenis_dpjp_1" value="dpjp_1"
                                       disabled="disabled">
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
                    <div class="row">
                        <div class="form-group" style="display: none" id="form-is-uangmuka">
                            <label class="col-md-3" style="margin-top: 10px">Ada uang muka?</label>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" id="cek_uang_muka" value="Y" onclick="cekUangMuka(this.id)">
                                    <label for="cek_uang_muka"></label>
                                </div>
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
                                    <input class="form-control" id="val_uang_muka"
                                           oninput="convertRp(this.id, this.value)">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Boleh Dikunjungin ?</label>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" id="cek_kunjungan" value="Y">
                                    <label for="cek_kunjungan"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-3" style="margin-top: 10px">Upload Berkas</label>
                        <div class="col-md-6">
                            <div class="input-group">
                                 <span class="input-group-btn">
                              <span class="btn btn-default btn-file">
                               Browse… <input type="file" id="berkas" accept=".jpg" onchange="setCanvasAtasWithText('img_berkas','url_berkas')">
                              </span>
                              </span>
                                <canvas id="img_berkas" style="display: none"></canvas>
                                <input type="text" id="url_berkas" class="form-control" readonly style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fin" onclick="confirm()"><i
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

<div class="modal fade" id="modal-daftar-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Daftar Pasien Baru Lahir</h4>
            </div>
            <div class="modal-body" style="height: 70%; overflow-y: scroll" id="back_top">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Jenis Pasien</label>
                                <select class="form-control" id="add_jenis"
                                        onchange="$(this).css('border',''); choiceJenisPasien(this.value)">
                                    <option value="umum">Umum</option>
                                    <option value="bpjs">BPJS</option>
                                </select>
                            </div>
                            <div class="form-group" style="display: none" id="form_no_bpjs">
                                <label style="margin-top: 7px">No BPJS</label>
                                <div class="input-group">
                                    <input class="form-control" id="add_no_bpjs" oninput="$(this).css('border','')">
                                    <div class="input-group-addon" style="cursor: pointer"
                                         onclick="cekBpjs(this.value)">
                                        <i class="fa fa-search"></i> Check
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">NIK</label>
                                <input class="form-control" id="add_nik" oninput="$(this).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Nama</label>
                                <input class="form-control" id="add_nama" oninput="$(this).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Jenis Kelamin</label>
                                <select class="form-control" id="add_jk" onchange="$(this).css('border','')">
                                    <option value="">[Select One]</option>
                                    <option value="L">Laki-Laki</option>
                                    <option value="P">Perempuan</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Tempat Lahir</label>
                                <input class="form-control" id="add_tempat_lahir" oninput="$(this).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Tanggal Lahir</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control datepicker datemask" id="add_tanggal_lahir"
                                           onchange="$(this).css('border','')">
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Agama</label>
                                <select class="form-control" id="add_agama" onchange="$(this).css('border','')">
                                    <option value="">[Select One]</option>
                                    <option value="Islam">Islam</option>
                                    <option value="Kristen">Kristen</option>
                                    <option value="Katolik">Katolik</option>
                                    <option value="Hindu">Hindu</option>
                                    <option value="Konguchu">Konguchu</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Profesi</label>
                                <input class="form-control" id="add_profesi">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Suku</label>
                                <input class="form-control" id="add_suku">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Alamat</label>
                                <input class="form-control" id="add_alamat">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">No Telp.</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-phone"></i>
                                    </div>
                                    <input class="form-control" id="add_no_telp" oninput="$(this.id).css('border','')"
                                           data-inputmask="'mask': ['9999-9999-9999']"
                                           data-mask="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Provinsi</label>
                                <input class="form-control" id="add_provinsi" oninput="$(this).css('border',''); setProvAtas(this.id, 'add_id_provinsi')">
                                <input type="hidden" id="add_id_provinsi">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Kota</label>
                                <input class="form-control" id="add_kota" oninput="$(this).css('border',''); setKabAtas(this.id, 'add_id_kota', 'add_id_provinsi')">
                                <input type="hidden" id="add_id_kota">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Kecamatan</label>
                                <input class="form-control" id="add_kecamatan" oninput="$(this).css('border',''); setKecAtas(this.id, 'add_id_kecamatan', 'add_id_kota')">
                                <input type="hidden" id="add_id_kecamatan">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Kelurahan/Desa</label>
                                <input class="form-control" id="add_desa" oninput="$(this).css('border',''); setDesAtas(this.id, 'add_id_desa', 'add_id_kecamatan')">
                                <input type="hidden" id="add_id_desa">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Foto Identitas</label>
                                <div class="input-group" id="img_file" style="margin-top: -7px">
                              <span class="input-group-btn">
                              <span class="btn btn-default btn-file">
                               Browse… <s:file accept=".jpg" name="fileUpload" id="ktp"
                                               onchange="$('#img_file').css('border',''); setCanvas('img_ktp_canvas')"></s:file></span>
                              </span>
                                    <input type="text" class="form-control" readonly style="margin-top: 7px">
                                </div>
                                <canvas id="img_ktp_canvas"
                                        style="border: solid 1px #ccc; width: 100%; height: 200px"></canvas>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Diagnosa</label>
                                <input class="form-control" id="add_id_diagnosa"
                                       oninput="showDiagnosa(this.id); $(this.id).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Keterangan Diagnosa</label>
                                <textarea class="form-control" id="add_keterangan_diagnosa" readonly></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="form_data_rujukan" style="display: none">
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user"></i> Data Rujukan</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Perujuk/Asal</label>
                                    <select class="form-control" id="add_perujuk">
                                        <option value="1">PPK 1 - [Puskesmaa]</option>
                                        <option value="2">PPK 2 - [Rumah Sakit Lain]</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">No Rujukan</label>
                                    <div class="input-group">
                                        <input class="form-control" id="add_no_rujukan">
                                        <div class="input-group-addon" style="cursor: pointer" onclick="">
                                            <i class="fa fa-search"></i> Check
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Keterangan Perujuk</label>
                                    <input class="form-control" id="add_keterangan">
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">No PPK Rujukan</label>
                                    <input class="form-control" id="add_ppk_rujukan">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Tanggal Rujukan</label>
                                    <input class="form-control datepicker datemask" id="add_tgl_rujukan">
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Foto Surat Rujukan</label>
                                    <div class="input-group" style="margin-top: -7px">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn-file">
                                                            Browse… <s:file id="foto_rujukan" accept=".jpg"
                                                                            onchange="setCanvas('add_foto_rujukan')"
                                                                            name="fileUploadDoc"></s:file>
                                                        </span>
                                                    </span>
                                        <input type="text" class="form-control" readonly style="margin-top: 7px">
                                    </div>
                                </div>
                                <canvas id="add_foto_rujukan" style="display: none"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Rawat Inap</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-2">
                                <button onclick="tambahDPJP('add')" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                                    DPJP
                                </button>
                            </div>
                            <div class="col-md-6">
                                <p style="color: red; display: none" id="msg_add_dpjp" class="blink_me">*silahkan pilih
                                    dokter terlebih dahulu...</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Dokter DPJP</label>
                            <div class="col-md-4">
                                <select class="form-control select2 add_id_dpjp" id="dokter_add_dpjp_1" style="width: 100%"
                                        onchange="$('#msg_add_dpjp').hide()">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control add_dpjp" style="margin-top: 7px" value="dpjp_1"
                                       disabled="disabled" placeholder="DPJP 1">
                            </div>
                        </div>
                    </div>
                    <div id="set_add_dpjp"></div>
                    <div class="row" id="form-add-kelas_kamar">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Kelas Kamar</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="add_kelas_kamar" style="width: 100%"
                                        onchange="getKamar(this.value, 'rawat_inap')">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" id="form-add-kamar">
                            <label class="col-md-3" style="margin-top: 10px">Kamar</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="add_kamar" style="width: 100%">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" id="form_is_uang_muka">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Ada uang muka?</label>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" id="cek_add_uang_muka" value="Y"
                                           onclick="cekUangMukaNew(this.id)">
                                    <label for="cek_add_uang_muka"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="form_uang_muka" style="display: none">
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 10px">Metode Pembayaran</label>
                                <div class="col-md-4">
                                    <select class="form-control select2" id="add_metode_bayar" style="width: 100%">
                                        <option value="">[Select One]</option>
                                        <option value="tunai">Tunai</option>
                                        <option value="non_tunai">Non Tunai</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 10px">Uang Muka</label>
                                <div class="col-md-4">
                                    <div class="input-group" style="margin-top: 7px">
                                        <div class="input-group-addon">
                                            Rp.
                                        </div>
                                        <input class="form-control" id="add_uang_muka"
                                               oninput="formatRupiahAtas(this.id, this.value, 'h_uang_muka')">
                                        <input type="hidden" id="h_uang_muka">
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
                <button type="button" class="btn btn-success" id="save_add" onclick="cekSaveRB()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_add"><i
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
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i>
                    Tidak
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con_rm"><i class="fa fa-check"></i> Ya
                </button>
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
        $('#' + id).val(formatRupiahAtas2(val));
    }

    function detail(noCheckup, idDCP, tindakLanjut, keteranganSelesai) {
        idDetailCheckup = idDCP;
        startSpinner('t_', idDCP);
        dwr.engine.setAsync(true);
        CheckupAction.listDataPasien(idDCP,
            {
                callback: function (res) {
                    if (res.idPasien != null) {
                        stopSpinner('t_', idDCP);
                        $('#kelas_kamar').html('');
                        $('#kamar').html('');
                        getDokterDpjp('dokter_dpjp_1', null);
                        if (res.idJenisPeriksaPasien == "umum") {
                            $('#form-is-uangmuka').show();
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
                        setLabelJenisPasien('jenis_pasien', res.idJenisPeriksaPasien);
                        $('#modal-detail').modal({show: true, backdrop: 'static'});
                    }
                }
            });
    }

    function tambahDPJP(tipe) {
        if(tipe == 'add'){
            var cekDpjp = $('.add_dpjp').length;
            var cekValue = $('#dokter_add_dpjp_' + cekDpjp).val();
            var cekJenis = $('#jenis_add_dpjp_' + cekDpjp).val();
            if (cekValue != '' && cekJenis != '') {
                var idDpjp = $('.add_id_dpjp');
                var idIsi = "";
                var aw = "(";
                var ak = ")";
                var valId = null;
                $.each(idDpjp, function (i, item) {
                    if (item.value != '' && item.value) {
                        var sp = item.value.split("|");
                        if (idIsi != '') {
                            idIsi = idIsi + ", " + "'" + sp[0] + "'";
                        } else {
                            idIsi = "'" + sp[0] + "'";
                        }
                    }
                });
                if (idIsi != '') {
                    valId = aw + idIsi + ak;
                }
                var count = cekDpjp + 1;
                $('#dokter_add_dpjp_' + cekDpjp).attr('disabled', 'disabled');
                $('#jenis_add_dpjp_' + cekDpjp).attr('disabled', 'disabled');
                var html = '<div class="row" id="set_dpjp_' + count + '">\n' +
                    '<div class="form-group">\n' +
                    '    <div class="col-md-offset-3 col-md-4">\n' +
                    '        <select class="form-control select2 add_id_dpjp" id="dokter_add_dpjp_' + count + '" style="width: 100%"\n' +
                    '            <option value=\'\'>[Select One]</option>\n' +
                    '        </select>\n' +
                    '    </div>\n' +
                    '    <div class="col-md-4">\n' +
                    '<select class="form-control select2 add_dpjp" id="jenis_add_dpjp_' + count + '">' +
                    '<option value="">[Select One]</option>' +
                    '<option value="konsultasi">Konsultasi</option>' +
                    '<option value="rawat_bersama">Rawat Bersama</option>' +
                    '</select>' +
                    '    </div>\n' +
                    '    <div class="col-md-1">\n' +
                    '        <button onclick="delDpjp(\'set_dpjp_' + count + '\')" style="margin-top: 8px; margin-left: -20px" class="btn btn-danger"><i class="fa fa-trash"></i></button>\n' +
                    '    </div>\n' +
                    '</div>\n' +
                    '</div>';
                $('#set_add_dpjp').append(html);
                $('.select2').select2();
                getDokterDpjp('dokter_add_dpjp_' + count, valId);
            } else {
                $('#msg_add_dpjp').show();
            }
        }else{
            var cekDpjp = $('.dpjp').length;
            var cekValue = $('#dokter_dpjp_' + cekDpjp).val();
            var cekJenis = $('#jenis_dpjp_' + cekDpjp).val();
            if (cekValue != '' && cekJenis != '') {
                var idDpjp = $('.id_dpjp');
                var idIsi = "";
                var aw = "(";
                var ak = ")";
                var valId = null;
                $.each(idDpjp, function (i, item) {
                    if (item.value != '' && item.value) {
                        var sp = item.value.split("|");
                        if (idIsi != '') {
                            idIsi = idIsi + ", " + "'" + sp[0] + "'";
                        } else {
                            idIsi = "'" + sp[0] + "'";
                        }
                    }
                });
                if (idIsi != '') {
                    valId = aw + idIsi + ak;
                }
                var count = cekDpjp + 1;
                $('#dokter_dpjp_' + cekDpjp).attr('disabled', 'disabled');
                $('#jenis_dpjp_' + cekDpjp).attr('disabled', 'disabled');
                var html = '<div class="row" id="set_dpjp_' + count + '">\n' +
                    '<div class="form-group">\n' +
                    '    <div class="col-md-offset-3 col-md-4">\n' +
                    '        <select class="form-control select2 id_dpjp" id="dokter_dpjp_' + count + '" style="width: 100%"\n' +
                    '            <option value=\'\'>[Select One]</option>\n' +
                    '        </select>\n' +
                    '    </div>\n' +
                    '    <div class="col-md-4">\n' +
                    '<select class="form-control select2 dpjp" id="jenis_dpjp_' + count + '">' +
                    '<option value="">[Select One]</option>' +
                    '<option value="konsultasi">Konsultasi</option>' +
                    '<option value="rawat_bersama">Rawat Bersama</option>' +
                    '</select>' +
                    '    </div>\n' +
                    '    <div class="col-md-1">\n' +
                    '        <button onclick="delDpjp(\'set_dpjp_' + count + '\')" style="margin-top: 8px; margin-left: -20px" class="btn btn-danger"><i class="fa fa-trash"></i></button>\n' +
                    '    </div>\n' +
                    '</div>\n' +
                    '</div>';
                $('#set_dpjp').append(html);
                $('.select2').select2();
                getDokterDpjp('dokter_dpjp_' + count, valId);
            } else {
                $('#msg_dpjp').show();
            }
        }
    }

    function delDpjp(id) {
        $('#' + id).remove();
    }

    function getDokterDpjp(id, idDpjp) {
        var option = '<option value="">[Select One]</option>';
        CheckupAction.getListDokterByBranchId(idDpjp, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idDokter + '|' + item.idPelayanan + '|' + item.sip + '|' + item.namaDokter + '">' + item.namaDokter + ' - ' + item.namaPelayanan + '</option>';
                });
                $('#' + id).html(option);
            } else {
                $('#' + id).html(option);
            }
        });
    }

    function getKelasKamar(kategori) {
        var option = '';
        if (kategori == "rawat_inap") {
            option = '<option value="">[Select One]</option>';
        }
        dwr.engine.setAsync(true);
        CheckupDetailAction.getListKelasKamar(kategori, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idKelasRuangan + '">' + item.namaKelasRuangan + '</option>';
                });
                if (kategori == 'rawat_inap') {
                    $('#kelas_kamar').html(option);
                    $('#add_kelas_kamar').html(option);
                } else {
                    $('#kelas_kamar').html(option);
                    $('#kelas_kamar').attr('disabled', 'disabled');
                }
            } else {
                $('#kelas_kamar').html(option);
            }
        });
    }

    function getKamar(idKelas, kategori) {
        var option = '<option value="">[Select One]</option>';
        dwr.engine.setAsync(true);
        CheckupDetailAction.listRuangan(idKelas, true, kategori, {
            callback: function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idTempatTidur + "'>" + '[' + item.noRuangan + "]-" + item.namaRuangan + "-[" + item.namaTempatTidur + "]</option>";
                    });
                    $('#kamar').html(option);
                    $('#add_kamar').html(option);
                } else {
                    $('#kamar').html(option);
                }
            }
        });
    }

    function confirm() {
        var cek = false;
        var jenisPasien = $('#h_jenis_pasien').val();
        var tindakLanjut = $('#h_tindak_lanjut').val();
        var kelasKamar = $('#kelas_kamar').val();
        var kamar = $('#kamar').val();
        var metodeBayar = $('#metode_bayar').val();
        var uangMuka = $('#val_uang_muka').val();
        var dataDpjp = [];
        var dataDokter = $('.id_dpjp');
        var dataPrio = $('.dpjp');
        var isUangMuka = $('#cek_uang_muka').is(':checked');
        $.each(dataDokter, function (i, item) {
            if (item.value != '') {
                var data = item.value.split("|");
                var idDokter = data[0];
                var idPelayanan = data[1];
                dataDpjp.push({
                    'id_dpjp': idDokter,
                    'id_pelayanan': idPelayanan,
                    'prioritas': dataPrio[i].value
                });
            }
        });

        if (tindakLanjut == "rawat_inap") {
            if (dataDpjp.length > 0 && kelasKamar && kamar != '') {
                if (jenisPasien == "umum") {
                    if (isUangMuka) {
                        if (metodeBayar && uangMuka != '') {
                            cek = true;
                        }
                    } else {
                        cek = true;
                    }
                } else {
                    cek = true;
                }
            }
        } else {
            if (dataDpjp.length > 0 && kamar != '') {
                if (jenisPasien == "umum") {
                    if (isUangMuka) {
                        if (metodeBayar && uangMuka != '') {
                            cek = true;
                        }
                    } else {
                        cek = true;
                    }
                } else {
                    cek = true;
                }
            }
        }

        if (cek) {
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'saveTppri()');
        } else {
            // $('#').scrollTop(0);
            $('#warning').show().fadeOut(5000);
            $('#msg_war').text("Silahkan cek kembali data inputan anda...!");
        }

    }

    function saveTppri() {
        $('#modal-confirm-dialog').modal('hide');
        var data = "";
        var dataDpjp = [];
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
        if (valUangMuka != undefined) {
            uangMuka = valUangMuka.replace(/[.]/g, '');
        }
        var kelasKamar = $('#kelas_kamar').val();
        var kamar = $('#kamar').val();
        var cekKunjungan = $('#cek_kunjungan').is(':checked');
        var cekBerkas = $('#berkas').val();
        var finalBerkas = "";
        if(cekBerkas != ''){
            finalBerkas = convertToDataURL(document.getElementById('img_berkas'));
        }

        var bolehKunjungan = "N";
        if(cekKunjungan){
            bolehKunjungan = "Y"
        }
        $.each(dataDokter, function (i, item) {
            if (item.value != '') {
                var data = item.value.split("|");
                var idDokter = data[0];
                var idPelayanan = data[1];
                dataDpjp.push({
                    'id_dpjp': idDokter,
                    'id_pelayanan': idPelayanan,
                    'prioritas': dataPrio[i].value
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
            'uang_muka': uangMuka,
            'kunjungan': bolehKunjungan,
            'img_berkas': finalBerkas
        }

        var result = JSON.stringify(data);
        $('#save_fin').hide();
        $('#load_fin').show();
        dwr.engine.setAsync(true);
        RawatInapAction.saveTppri(result,
            {
                callback: function (response) {
                    if (response.status == "success") {
                        $('#save_fin').show();
                        $('#load_fin').hide();
                        $('#info_dialog').dialog('open');
                        $('#modal-detail').modal('hide');
                        $('body').scrollTop(0);
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
        $('#save_con_rm').attr('onclick', 'printPernyataanRM(\'' + kode + '\', \'' + idRm + '\')');
        $('#modal-confirm-rm').modal('show');
    }

    function printPernyataanRM(kode, idRM) {
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

                    if (item.jumlahKategori != null) {
                        constan = item.jumlahKategori;
                    }
                    if (item.terisi != null && item.terisi != '') {
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

                    labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';
                    labelPrint = '<span style="color: #367fa9; font-weight: bold">' + terIsiPrint + '</span>';

                    if (item.jenis == 'ringkasan_rj') {
                        li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-file-o"></i>' + item.namaRm + '</a></li>'
                    } else {
                        if (item.keterangan == 'form') {
                            li += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')"><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '">' + icons + item.namaRm + ' ' + labelTerisi + tolText + '</a></li>'
                        } else if (item.keterangan == "surat") {
                            li += '<li ' + tol + '><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\',\'' + item.namaRm + '\')' + '">' + icons2 + item.namaRm + ' ' + labelPrint + tolText + '</a></li>'
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
            context = contextPath + '/pages/modal/modal-' + jenis + '.jsp';
        }
        $('#modal-temp').load(context, function (res, status, xhr) {
        });
    }

    function cekUangMuka(id) {
        var cek = $('#' + id).is(':checked');
        if (cek) {
            $('#form-metode_pembayaran').show();
            $('#form-uang_muka').show();
        } else {
            $('#form-metode_pembayaran').hide();
            $('#form-uang_muka').hide();
        }
    }

    function cekUangMukaNew(id) {
        var cek = $('#' + id).is(':checked');
        if (cek) {
            $('#form_uang_muka').show();
        } else {
            $('#form_uang_muka').hide();
        }
    }
</script>
<script>
    function showDaftar() {
        getDokterDpjp('dokter_add_dpjp_1', null);
        getKelasKamar('rawat_inap');
        $('#modal-daftar-pasien').modal({show: true, backdrop: 'static'});
    }

    function setCanvas(id) {
        var canvas = document.getElementById(id);
        var ctx = canvas.getContext('2d');
        var reader = new FileReader();
        reader.onload = function (event) {
            var img = new Image();
            img.onload = function () {
                canvas.width = img.width;
                canvas.height = img.height;
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.drawImage(img, 0, 0);
            }
            img.src = event.target.result;
        }
        reader.readAsDataURL(event.target.files[0]);
    }

    function choiceJenisPasien(jen) {
        if (jen == 'umum') {
            $('#form_no_bpjs').hide();
            $('#form_data_rujukan').hide();
            $('#form_is_uang_muka').show();
        }
        if (jen == 'bpjs') {
            $('#form_no_bpjs').show();
            $('#form_data_rujukan').show();
            $('#form_is_uang_muka').hide();
        }
    }

    function cekSaveRB() {
        var data = "";
        var jenisPasien = $('#add_jenis').val();
        var noBpjs = $('#add_no_bpjs').val();
        var nik = $('#add_nik').val();
        var nama = $('#add_nama').val();
        var jk = $('#add_jk').val();
        var tempatLahir = $('#add_tempat_lahir').val();
        var tanggalLahir = $('#add_tanggal_lahir').val();
        var noTelp = $('#add_no_telp').val();
        var agama = $('#add_agama').val();
        var profesi = $('#add_profesi').val();
        var suku = $('#add_suku').val();
        var alamat = $('#add_alamat').val();
        var provinsi = $('#add_id_provinsi').val();
        var kota = $('#add_id_kota').val();
        var kecamatan = $('#add_id_kecamatan').val();
        var desa = $('#add_id_desa').val();

        var perujuk = $('#add_perujuk').val();
        var noRujukan = $('#add_no_rujukan').val();
        var ketRujukan = $('#add_keterangan').val();
        var ppkRujukan = $('#add_ppk_rujukan').val();
        var tglRujukan = $('#add_tgl_rujukan').val();
        var noHP = noTelp.replace("-", "").replace("_", "");
        var idDignosa = $('#add_id_diagnosa').val();
        var ketDiagnosa = $('#add_keterangan_diagnosa').val();

        var cekUangMuka = $('#cek_add_uang_muka').is(':checked');
        var uangMuka = $('#h_uang_muka').val();
        var metode = $('#add_metode_bayar').val();
        var kamar = $('#add_kamar').val();
        var kelasKamar = $('#add_kelas_kamar').val();
        var dokter = $('#dokter_add_dpjp_1').val();

        var ktp = document.getElementById('img_ktp_canvas');
        var foto = document.getElementById('add_foto_rujukan');
        var cek = false;

        if (nik != '' && nama != '' && jk != '' && tempatLahir != '' && tanggalLahir != '' &&
            agama != '' && provinsi != '' && kota != '' && kecamatan != '' && desa != '' && noTelp != '' && kamar && kelasKamar && dokter != '') {

            if (jenisPasien == 'bpjs') {
                if (noBpjs && idDignosa && ketDiagnosa != '') {
                    cek = true;
                } else {
                    $('#warning_add').show().fadeOut(5000);
                    $('#msg_add').text("Silahkan cek kembali data id diagnosa dan no bpjs...!");
                    $('#back_top').scrollTop(0);
                    if (noBpjs == '') {
                        $('#add_no_bpjs').css('border', 'solid ipx red');
                    }
                    if (idDignosa == '') {
                        $('#add_id_diagnosa').css('border', 'solid ipx red');
                    }
                    if (ketDiagnosa == '') {
                        $('#add_keterangan_diagnosa').css('border', 'solid ipx red');
                    }
                }
            }
            if (jenisPasien == 'umum') {
                if (cekUangMuka) {
                    if (uangMuka && metode != '') {
                        cek = true;
                    } else {
                        $('#warning_add').show().fadeOut(5000);
                        $('#msg_add').text("Silahkan cek kembali data uang muka dan metode bayar...!");
                        $('#back_top').scrollTop(0);
                    }
                } else {
                    cek = true;
                }
            }

        } else {
            $('#warning_add').show().fadeOut(5000);
            $('#msg_add').text("Silahkan cek kembali data inputan anda...!");
            $('#back_top').scrollTop(0);
            if (nik == '') {
                $('#add_nik').css('border', 'solid 1px red');
            }
            if (nama == '') {
                $('#add_nama').css('border', 'solid 1px red');
            }
            if (jk == '') {
                $('#add_jk').css('border', 'solid 1px red');
            }
            if (tempatLahir == '') {
                $('#add_tempat_lahir').css('border', 'solid 1px red');
            }
            if (tanggalLahir == '') {
                $('#add_tanggal_lahir').css('border', 'solid 1px red');
            }
            if (agama == '') {
                $('#add_agama').css('border', 'solid 1px red');
            }
            if (provinsi == '') {
                $('#add_provinsi').css('border', 'solid 1px red');
            }
            if (kota == '') {
                $('#add_kota').css('border', 'solid 1px red');
            }
            if (kecamatan == '') {
                $('#add_kecamatan').css('border', 'solid 1px red');
            }
            if (desa == '') {
                $('#add_desa').css('border', 'solid 1px red');
            }
            if (noTelp == '') {
                $('#add_no_telp').css('border', 'solid 1px red');
            }
        }

        if (cek) {
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'saveNewRB()');
        }
    }

    function saveNewRB() {
        var data = "";
        $('#modal-confirm-dialog').modal('hide');
        var jenisPasien = $('#add_jenis').val();
        var noBpjs = $('#add_no_bpjs').val();
        var nik = $('#add_nik').val();
        var nama = $('#add_nama').val();
        var jk = $('#add_jk').val();
        var tempatLahir = $('#add_tempat_lahir').val();
        var tanggalLahir = $('#add_tanggal_lahir').val();
        var noTelp = $('#add_no_telp').val();
        var agama = $('#add_agama').val();
        var profesi = $('#add_profesi').val();
        var suku = $('#add_suku').val();
        var alamat = $('#add_alamat').val();
        var provinsi = $('#add_id_provinsi').val();
        var kota = $('#add_id_kota').val();
        var kecamatan = $('#add_id_kecamatan').val();
        var desa = $('#add_id_desa').val();

        var perujuk = $('#add_perujuk').val();
        var noRujukan = $('#add_no_rujukan').val();
        var ketRujukan = $('#add_keterangan').val();
        var ppkRujukan = $('#add_ppk_rujukan').val();
        var tglRujukan = $('#add_tgl_rujukan').val();
        var noHP = noTelp.replace("-", "").replace("_", "");
        var idDignosa = $('#add_id_diagnosa').val();
        var ketDiagnosa = $('#add_keterangan_diagnosa').val();

        var cekUangMuka = $('#cek_add_uang_muka').is(':checked');
        var uangMuka = $('#h_uang_muka').val();
        var metode = $('#add_metode_bayar').val();
        var kamar = $('#add_kamar').val();
        var kelasKamar = $('#add_kelas_kamar').val();
        var dokter = $('#dokter_add_dpjp_1').val();

        var ktp = document.getElementById('img_ktp_canvas');
        var foto = document.getElementById('add_foto_rujukan');
        var ktpFinal = "";
        var fotoFinal = "";
        if ($('#ktp').val() != '') {
            ktpFinal = convertToDataURL(ktp);
        }
        if ($('#foto_rujukan').val() != '') {
            ktpFinal = convertToDataURL(ktp);
        }
        var dataDpjp = [];
        var dataDokter = $('.add_id_dpjp');
        var dataPrio = $('.add_dpjp');
        $.each(dataDokter, function (i, item) {
            if (item.value != '') {
                var data = item.value.split("|");
                var idDokter = data[0];
                var idPelayanan = data[1];
                dataDpjp.push({
                    'id_dpjp': idDokter,
                    'id_pelayanan': idPelayanan,
                    'prioritas': dataPrio[i].value
                });
            }
        });
        data = {
            'no_bpjs': noBpjs,
            'nik': nik,
            'nama': nama,
            'jk': jk,
            'tempat_lahir': tempatLahir,
            'tanggal_lahir': tanggalLahir,
            'agama': agama,
            'no_telp': noHP,
            'profesi': profesi,
            'suku': suku,
            'alamat': alamat,
            'desa_id': desa,
            'img_ktp': ktpFinal,
            'jenis_pasien': jenisPasien,
            'kelas_kamar': kelasKamar,
            'kamar': kamar,
            'id_diganosa': idDignosa,
            'ket_diagnosa': ketDiagnosa,
            'img_rujukan': kelasKamar,
            'perujuk': perujuk,
            'no_rujukan': noRujukan,
            'ket_perujuk': ketRujukan,
            'no_ppk': ppkRujukan,
            'tgl_ppk': tglRujukan,
            'img_rujukan': fotoFinal,
            'uang_muka': uangMuka,
            'metode_pembayaran': metode,
            'data_dpjp': dataDpjp
        };
        var objectString = JSON.stringify(data);
        $('#save_add').hide();
        $('#load_add').show();
        dwr.engine.setAsync(true);
        RawatInapAction.saveNewTppri(objectString, {
            callback: function (response) {
                if (response.status == "success") {
                    $('#save_add').show();
                    $('#load_add').hide();
                    $('#info_dialog').dialog('open');
                    $('#modal-daftar-pasien').modal('hide');
                    $('body').scrollTop(0);
                } else {
                    $('#save_add').show();
                    $('#load_add').hide();
                    $('#warning_add').show();
                    $('#msg_add').text(response.msg).fadeOut(5000);
                }
            }
        });
    }

    function showDiagnosa(id) {
        var menus, mapped;
        $('#' + id).typeahead({
            minLength: 3,
            source: function (query, process) {
                menus = [];
                mapped = {};

                var data = [];
                dwr.engine.setAsync(false);
                CheckupAction.getICD10(query, function (listdata) {
                    data = listdata;
                });

                $.each(data, function (i, item) {
                    var labelItem = item.idDiagnosa + '-' + item.descOfDiagnosa;
                    mapped[labelItem] = {
                        id: item.idDiagnosa,
                        label: labelItem,
                        name: item.descOfDiagnosa
                    };
                    menus.push(labelItem);
                });

                process(menus);
            },
            updater: function (item) {
                var selectedObj = mapped[item];
                // insert to textarea diagnosa_ket
                $("#add_keterangan_diagnosa").val(selectedObj.name);
                return selectedObj.id;
            }
        });
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>