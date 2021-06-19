<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        .paint-canvas {
            border: 1px black solid;
            display: block;
            margin: 1rem;
            cursor: pointer;
        }

        .color-picker {
            margin: 1rem 1rem 0 1rem;
        }

        .blink_me {
            animation: blinker 3.0s linear infinite;
        }

        @keyframes blinker {
            50% {
                opacity: 0;
            }
        }
        .expand:hover{
            cursor: pointer;
        }
    </style>

    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TeamDokterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DiagnosaRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanResepAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanRawatICD9Action.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PelayananAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/OrderGiziAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RawatInapAction.js"/>'></script>

    <script type='text/javascript'>

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');

            }
        });

        $.subscribe('errorDialog', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function formatRupiah2(angka) {
            var number_string = angka.replace(/[^,\d]/g, '').toString(),
                split = number_string.split(','),
                sisa = split[0].length % 3,
                rupiah = split[0].substr(0, sisa),
                ribuan = split[0].substr(sisa).match(/\d{3}/gi);

            if (ribuan) {
                separator = sisa ? '.' : '';
                rupiah += separator + ribuan.join('.');
            }

            rupiah = split[1] != undefined ? rupiah + ',' + split[1] : rupiah;
            return rupiah;
        }

        function formatRupiah(angka) {
            if(angka != '' && angka != null && angka > 0){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            }else{
                return 0;
            }
        }

        function convertSentenceCase(myString){
            if(myString != null && myString != ''){
                var rg = /(^\w{1}|\ \s*\w{1})/gi;
                myString = myString.replace(rg, function(toReplace) {
                    return toReplace.toUpperCase();
                });
                return myString;
            }else{
                return "";
            }
        }

    </script>
    <style>
        .form-check {
            display: inline-block;
            padding-left: 2px;
        }

        .form-check input {
            padding: 0;
            height: initial;
            width: initial;
            margin-bottom: 0;
            display: none;
            cursor: pointer;
        }

        .form-check label {
            position: relative;
            cursor: pointer;
        }

        .form-check label:before {
            content:'';
            -webkit-appearance: none;
            background-color: transparent;
            border: 2px solid #0079bf;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 10px;
            display: inline-block;
            position: relative;
            vertical-align: middle;
            cursor: pointer;
            margin-right: 5px;
        }

        .form-check input:checked + label:after {
            content: '';
            display: block;
            position: absolute;
            top: 2px;
            left: 9px;
            width: 6px;
            height: 14px;
            border: solid #0079bf;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
    </style>
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
            Rawat Jalan Pasien
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">

                            <div class="col-md-6">
                                <table class="table table-striped" style="margin-top: 20px">
                                    <s:hidden id="no_checkup" name="headerDetailCheckup.noCheckup"></s:hidden>
                                    <s:hidden id="id_palayanan" name="headerDetailCheckup.idPelayanan"></s:hidden>
                                    <s:hidden id="no_detail_checkup" name="headerDetailCheckup.idDetailCheckup"/>
                                    <s:hidden id="id_pasien" name="headerDetailCheckup.idPasien"/>
                                    <s:hidden id="jenis_pasien" name="headerDetailCheckup.idJenisPeriksaPasien"/>
                                    <s:hidden id="jenis_bayar" name="headerDetailCheckup.metodePembayaran"/>
                                    <s:hidden id="id_asuransi" name="headerDetailCheckup.idAsuransi"/>
                                    <s:hidden id="nama_asuransi" name="headerDetailCheckup.namaAsuransi"/>

                                    <s:hidden id="no_rujukan" name="headerDetailCheckup.noRujukan"/>
                                    <s:hidden id="tgl_rujukan" name="headerDetailCheckup.tglRujukan"/>
                                    <s:hidden id="surat_rujukan" name="headerDetailCheckup.suratRujukan"/>
                                    <s:hidden id="is_laka" name="headerDetailCheckup.isLaka"/>

                                    <s:hidden id="h_nama_pasien" name="headerDetailCheckup.namaPasien"/>
                                    <s:hidden id="h_tgl_lahir" name="headerDetailCheckup.tglLahir"/>
                                    <s:hidden id="h_anamnesa" name="headerDetailCheckup.anamnese"/>
                                    <s:hidden id="h_penunjang_medis" name="headerDetailCheckup.penunjangMedis"/>
                                    <s:hidden id="h_keluhan_utama" name="headerDetailCheckup.keluhanUtama"/>
                                    <s:hidden id="h_suhu" name="headerDetailCheckup.suhu"/>
                                    <s:hidden id="h_nadi" name="headerDetailCheckup.nadi"/>
                                    <s:hidden id="h_tensi" name="headerDetailCheckup.tensi"/>
                                    <s:hidden id="h_pernafasan" name="headerDetailCheckup.pernafasan"/>
                                    <s:hidden id="h_alergi" name="headerDetailCheckup.alergi"/>
                                    <s:hidden id="h_berat_badan" name="headerDetailCheckup.berat"/>
                                    <s:hidden id="h_tinggi_badan" name="headerDetailCheckup.tinggi"/>
                                    <s:hidden id="h_diagnosa" name="headerDetailCheckup.namaDiagnosa"/>
                                    <s:hidden id="h_umur" name="headerDetailCheckup.umur"/>
                                    <s:hidden id="h_alamat_lengkap" name="headerDetailCheckup.alamatLengkap"/>
                                    <s:hidden id="h_no_bpjs" name="headerDetailCheckup.noBpjs"/>
                                    <s:hidden id="h_jenis_kelamin" name="headerDetailCheckup.jenisKelamin"/>
                                    <s:hidden id="h_tipe_pelayanan" name="headerDetailCheckup.kategoriPelayanan"/>
                                    <s:hidden id="h_jenis_pelayanan" name="headerDetailCheckup.kategoriPelayanan"/>
                                    <s:hidden id="h_no_sep" name="headerDetailCheckup.noSep"/>
                                    <s:hidden id="h_id_asuransi" name="headerDetailCheckup.idAsuransi"/>
                                    <s:hidden id="h_is_laka" name="headerDetailCheckup.isLaka"/>
                                    <s:hidden id="h_no_rujukan" name="headerDetailCheckup.noRujukan"/>
                                    <s:hidden id="h_tgl_rujukan" name="headerDetailCheckup.tglRujukan"/>
                                    <s:hidden id="h_surat_rujukan" name="headerDetailCheckup.suratRujukan"/>
                                    <s:hidden id="h_nama_ruangan" name="headerDetailCheckup.namaPelayanan"/>
                                    <s:hidden id="h_is_eksekutif" name="headerDetailCheckup.isEksekutif"/>
                                    <s:hidden id="h_flag_vaksin" name="headerDetailCheckup.isVaksin"/>

                                    <s:if test='headerDetailCheckup.noSep != ""'>
                                        <tr>
                                            <td width="45%"><b>No SEP</b></td>
                                            <td style="vertical-align: middle;">
                                                <table>
                                                    <span class="span-success">
                                                    <s:property value="headerDetailCheckup.noSep"></s:property>
                                                    </span>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <tr>
                                        <td width="45%"><b>No RM</b></td>
                                        <td>
                                            <table><s:label
                                                    name="headerDetailCheckup.idPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup</b></td>
                                        <td>
                                            <table>
                                                <s:label name="headerDetailCheckup.noCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td>
                                            <table><s:label
                                                    name="headerDetailCheckup.idDetailCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.nik"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.namaPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.jenisKelamin"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.tempatTglLahir"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Umur</b></td>
                                        <td>
                                            <table>
                                                <script>
                                                    var umur = '<s:property value="headerDetailCheckup.umur"/>';
                                                    var umurLengkap = umur+' Tahun';
                                                    document.write(umurLengkap);
                                                </script>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No HP. Penanggung Jawab</b></td>
                                        <td>
                                            <table>
                                                <script>
                                                    var noHp = '<s:property value="headerDetailCheckup.noTelp"/>';
                                                    var temp = "";
                                                    if(noHp != '' && noHp.length > 0){
                                                        for (var i = 0; i < noHp.length; i++) {
                                                            if(i == 3){
                                                                temp = temp+noHp[i]+'-';
                                                            }else if(i == 7){
                                                                temp = temp+noHp[i]+'-';
                                                            }else{
                                                                temp = temp+noHp[i];
                                                            }
                                                        }
                                                    }
                                                    document.write(temp);
                                                </script>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <s:hidden name="headerDetailCheckup.jenisPeriksaPasien" id="jenis_periksa"></s:hidden>

                            <div class="col-md-6">
                                <script>
                                    document.write(imagesDefault('<s:property value="headerDetailCheckup.urlKtp"/>'));
                                </script>
                                <table class="table table-striped">
                                    <tr id="row_jenis_pasien">
                                        <td width="45%"><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <script>
                                                    document.write(changeJenisPasien('<s:property value="headerDetailCheckup.idJenisPeriksaPasien"/>', '<s:property value="headerDetailCheckup.jenisPeriksaPasien"/>'));
                                                </script>
                                            </table>
                                        </td>
                                    </tr>
                                    <s:if test='headerDetailCheckup.idJenisPeriksaPasien == "paket_perusahaan" || headerDetailCheckup.idJenisPeriksaPasien == "paket_individu"'>
                                        <tr>
                                            <td><b>Tarif Paket</b></td>
                                            <td>
                                                <table>
                                                    <script>
                                                        var tar = '<s:property value="headerDetailCheckup.coverBiaya"/>';
                                                        if(tar != null){
                                                            document.write("Rp. "+formatRupiah(tar));
                                                        }
                                                    </script>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <s:if test='headerDetailCheckup.idJenisPeriksaPasien == "asuransi"'>
                                        <tr>
                                            <td><b>Nama Asuransi</b></td>
                                            <td>
                                                <table>
                                                    <s:label id="nama_asuransi"
                                                             name="headerDetailCheckup.namaAsuransi"></s:label>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <tr>
                                        <td><b>Pelayanan</b></td>
                                        <td>
                                            <table>
                                                <s:property value="headerDetailCheckup.namaPelayanan"/>
                                                <s:if test='headerDetailCheckup.isEksekutif == "Y"'>
                                                    <span style="margin-left: 5px" class="span-success">Eksekutif</span>
                                                </s:if>
                                                <s:if test='headerDetailCheckup.isVaksin == "Y"'>
                                                    <span style="margin-left: 5px" class="span-warning">Vaksin</span>
                                                </s:if>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.desa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                </table>
                            </div>

                            <div class="form-group" style="display: none">
                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                           closeOnEscape="false"
                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                           buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         toContent();
                                                                                     }
                                                                            }"
                                >
                                    <s:hidden id="close_pos"></s:hidden>
                                    <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                         name="icon_success">
                                    Record has been saved successfully.
                                </sj:dialog>

                                <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
                                           closeTopics="closeDialog" modal="true"
                                           resizable="false"
                                           height="250" width="600" autoOpen="false"
                                           title="Saving ...">
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

                                <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                           resizable="false"
                                           height="250" width="600" autoOpen="false" title="Error Dialog"
                                           buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close'); }
                                                                            }"
                                >
                                    <div class="alert alert-danger alert-dismissible">
                                        <label class="control-label" align="left">
                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                 name="icon_error"> System Found : <p id="errorMessage"></p>
                                        </label>
                                    </div>
                                </sj:dialog>
                            </div>
                            <!-- /.col -->
                        </div>
                        <div class="row" id="form_detail_paket" style="display: none">
                            <div class="col-md-12">
                                <label><i class="fa fa-file-o"></i> Detail Pelayanan Paket</label>
                            </div>
                            <div class="col-md-6">
                                <table style="font-size: 12px" class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <td>Pelayanan</td>
                                        <td width="30%" align="center">Status</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body_detail_paket">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="box-header with-border" id="pos_rm"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-stethoscope"></i> Anamnesis & Pemeriksaan Fisik</h3>
                            </div>
                            <div class="box-body">
                                <div class="alert alert-danger alert-dismissible" style="display: none" id="war_anamnese">
                                    <p id="msg_war"></p>
                                </div>
                                <div class="alert alert-success alert-dismissible" style="display: none" id="suc_anamnese">
                                    <p id="msg_suc"></p>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <span>Autoanamnesis</span>
                                        <s:textarea id="fisik_auto" name="headerDetailCheckup.autoanamnesis" cssClass="form-control" rows="3" placeholder="Keterangan Autoanamnesis"></s:textarea>
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-12">
                                        <span>Heteroanamnesis</span>
                                        <s:textarea id="fisik_hetero" name="headerDetailCheckup.heteroanamnesis" cssClass="form-control" rows="3" placeholder="Keterangan HeteroAnamnesis"></s:textarea>
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-3">
                                        <span>Tensi </span> <small>(mmHg)</small>
                                        <s:textfield cssClass="form-control" id="fisik_tensi" name="headerDetailCheckup.tensi" data-inputmask="'mask': ['999/999']" data-mask=""></s:textfield>
                                    </div>
                                    <div class="col-md-3">
                                        <span>Suhu</span> <small>(&#8451)</small>
                                        <s:textfield cssClass="form-control" id="fisik_suhu" name="headerDetailCheckup.suhu" type="number"></s:textfield>
                                    </div>
                                    <div class="col-md-3">
                                        <span>Nadi</span> <small>(x/menit)</small>
                                        <s:textfield cssClass="form-control" id="fisik_nadi" name="headerDetailCheckup.nadi" type="number"></s:textfield>
                                    </div>
                                    <div class="col-md-3">
                                        <span>RR</span> <small>(x/menit)</small>
                                        <s:textfield cssClass="form-control" id="fisik_rr" name="headerDetailCheckup.pernafasan" type="number"></s:textfield>
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-3">
                                        <span>SPO2</span> <small>(%)</small>
                                        <s:textfield cssClass="form-control" id="fisik_spo2" name="headerDetailCheckup.spo2" type="number"></s:textfield>
                                    </div>
                                    <div class="col-md-3">
                                        <span>Tinggi</span> <small>(Cm)</small>
                                        <s:textfield cssClass="form-control" id="fisik_tinggi" name="headerDetailCheckup.tinggi" type="number"></s:textfield>
                                    </div>
                                    <div class="col-md-3">
                                        <span>Berat</span> <small>(Kg)</small>
                                        <s:textfield cssClass="form-control" id="fisik_berat" name="headerDetailCheckup.berat" type="number"></s:textfield>
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-12">
                                        <span>Catatan Klinis</span>
                                        <s:textarea id="kinis" name="headerDetailCheckup.catatanKlinis" cssClass="form-control" rows="4" placeholder="Catatan Klinis"></s:textarea>
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-12">
                                        <button id="save_fisik" class="btn btn-success pull-right" onclick="saveAnamnese()"><i class="fa fa-check"></i> Save</button>
                                        <button style="display: none; cursor: no-drop;" type="button" class="btn btn-success pull-right" id="load_fisik"> <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-medkit"></i> Rekam Medis</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="btn-group dropdown">
                                            <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Asesmen
                                            </button>
                                            <button onclick="setRekamMedis()" type="button" class="btn btn-primary dropdown-toggle"
                                                    data-toggle="dropdown" style="height: 34px">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu" id="asesmen_rj">
                                            </ul>
                                        </div>
                                        <button type="button" onclick="viewHistory()" class="btn btn-info hvr-icon-spin"><i class="fa fa-history hvr-icon"></i> All History</button>
                                        <button class="btn btn-info" onclick="uploadPemeriksaan()"><i class="fa fa-line-chart"></i> Upload Pemeriksaan</button>
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 20px">
                                    <div class="col-md-12">
                                        <table class="table table-hover" style="font-size: 12px">
                                            <tbody id="temp_kesimpulan"></tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-heartbeat"></i> Hasil BMI (Body Mass Index)</h3>
                            </div>
                            <div class="box-body">
                                <div style="padding-top: 15px">
                                    <div class="progress">
                                        <div id="bar_bmi"></div>
                                    </div>
                                </div>
                                <small>
                                    <ul style="list-style-type: none;">
                                        <li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #337ab7"></i> Berat badan kurang
                                        </li>
                                        <li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #5cb85c"></i> Berat badan normal </li>
                                        <li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #f0ad4e"></i> Berat badan berlebih  </li>
                                        <li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #d9534f"></i> Obesitas  </li>
                                    </ul>
                                </small>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="box-header with-border" id="pos_alergi"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Alergi</h3>
                            </div>
                            <div class="box-body">
                                <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                        onclick="showModal(8)"><i class="fa fa-plus"></i> Tambah Alergi
                                </button>
                                <table class="table table-bordered table-striped">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>Jenis</td>
                                        <td>Alergi</td>
                                        <td align="center" width="20%">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body_alergi">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user-md"></i> Dokter</h3>
                            </div>
                            <div class="box-body">
                                <table class="table table-bordered table-striped" id="tabel_dokter" style="margin-top: 50px">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>ID Dokter</td>
                                        <td>Nama</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body_dokter">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="box-header with-border" id="pos_nosa"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Diagnosa</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(3)"><i class="fa fa-plus"></i> Tambah Diagnosa
                        </button>
                        <table class="table table-bordered table-striped table-hover" id="tabel_diagnosa">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="14%">Waktu</td>
                                <td>Kode ICD10</td>
                                <td>Keterangan</td>
                                <td>Jenis Diagnosa</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_diagnosa">

                            </tbody>
                        </table>
                    </div>

                    <div id="status_bpjs" style="display: none">
                        <div class="box-header with-border">
                        </div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-line-chart"></i> Status Biaya Tindakan</h3>
                        </div>
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-offset-2 col-md-8">
                                    <h5>
                                        Cover Biaya Bpjs dengan kode CBG <b id="kode_cbg"></b>
                                        <small class="pull-right" style="margin-top: 7px">Rp. <span id="b_bpjs"></span>
                                        </small>
                                    </h5>
                                    <div class="progress">
                                        <div id="sts_cover_biaya">
                                        </div>
                                    </div>
                                    <h5>
                                        Total Biaya Tindakan
                                        <small class="pull-right" style="margin-top: 7px">Rp. <span
                                                id="b_tindakan"></span></small>
                                    </h5>
                                    <div class="progress">
                                        <div id="sts_biaya_tindakan">
                                        </div>
                                    </div>
                                    <ul style="list-style-type: none">
                                        <li><i class="fa fa-square" style="color: #337ab7"></i> Total biaya cover Bpjs
                                        </li>
                                        <li><i class="fa fa-square" style="color: #5cb85c"></i> Total biaya tindakan <
                                            50% dari cover biaya Bpjs
                                        </li>
                                        <li><i class="fa fa-square" style="color: #f0ad4e"></i> Total biaya tindakan >
                                            50% dan < 70% dari cover biaya Bpjs
                                        </li>
                                        <li><i class="fa fa-square" style="color: #d9534f"></i> Total biaya tindakan >
                                            70% dari cover biaya Bpjs
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-md-2">

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border" id="pos_icd9">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> ICD9</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(9)"><i class="fa fa-plus"></i> Tambah ICD9
                        </button>
                        <table class="table table-bordered table-striped table-hover" id="tabel_icd9">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="14%">Waktu</td>
                                <td>Kode ICD9</td>
                                <td>Keterangan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_icd9">

                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border" id="pos_tin">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Tindakan</h3>
                    </div>
                    <div class="box-body">
                        <input type="hidden" id="tin_id_dokter">
                        <s:if test='headerDetailCheckup.idJenisPeriksaPasien != "paket_individu" && headerDetailCheckup.idJenisPeriksaPasien != "paket_perusahaan"'>
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(2)"><i class="fa fa-plus"></i> Tambah Tindakan
                        </button>
                            <table class="table table-bordered table-striped table-hover" id="tabel_tindakan">
                                <thead>
                                <tr bgcolor="#90ee90">
                                    <td width="14%">Waktu</td>
                                    <td>Tindakan</td>
                                    <td>Dokter</td>
                                    <td align="center">Tarif (Rp.)</td>
                                    <td align="center">Qty</td>
                                    <td align="center">Total (Rp.)</td>
                                    <td align="center">Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_tindakan">
                                </tbody>
                            </table>
                        </s:if>
                        <s:else>
                            <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                    onclick="showModal(2)"><i class="fa fa-plus"></i> Tambah Tindakan
                            </button>
                            <table class="table table-bordered table-striped table-hover" id="tabel_tindakan">
                                <thead>
                                <tr bgcolor="#90ee90">
                                    <td width="14%">Waktu</td>
                                    <td>Tindakan</td>
                                    <td align="center" width="10%">Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_tindakan_paket">
                                </tbody>
                            </table>
                        </s:else>
                    </div>
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Penunjang Medis</h3>
                    </div>
                    <div class="box-body">
                        <%--<s:if test='headerDetailCheckup.idJenisPeriksaPasien != "paket_individu" && headerDetailCheckup.idJenisPeriksaPasien != "paket_perusahaan"'>--%>
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(4)"><i class="fa fa-plus"></i> Penunjang Medis
                        </button>
                            <button class="btn btn-primary" style="margin-bottom: 10px;"
                                    onclick="refreshTable('lab_ref', 'lab')"><i class="fa fa-refresh" id="lab_ref"></i> Refresh
                            </button>
                        <%--</s:if>--%>
                        <table class="table table-bordered" id="tabel_penunjang_medis">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="14%">Waktu</td>
                                <td>No Order</td>
                                <td>Jenis Penunjang</td>
                                <td>Status</td>
                                <td align="center" width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_lab">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border" id="pos_rssep">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Order Resep Obat</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(7)"><i class="fa fa-plus"></i> Tambah Resep
                        </button>
                        <button class="btn btn-success btn-outline" onclick="showModal(11)" style="margin-bottom: 10px; width: 150px"><i class="fa fa-mail-reply"></i> Copy Resep</button>

                        <button class="btn btn-primary" style="margin-bottom: 10px;"
                                onclick="refreshTable('resep_ref', 'resep')"><i class="fa fa-refresh" id="resep_ref"></i> Refresh
                        </button>
                        <table class="table table-bordered table-striped table-hover" >
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="14%">Waktu</td>
                                <td>ID Resep</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_resep">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-navicon"></i> Keterangan (Jika sudah pulang / selesai pemeriksaan)</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ket">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="warning_msg"></p>
                        </div>
                        <div class="alert alert-success alert-dismissible" style="display: none" id="success_ket">
                            <h4><i class="icon fa fa-info"></i> Succes!</h4>
                            <p id="success_msg"></p>
                        </div>
                        <input type="hidden" id="h_id_pelayanan_paket">
                        <input type="hidden" id="h_urutan_paket">
                        <input type="hidden" id="h_lanjut_paket">
                        <input type="hidden" id="h_id_paket">
                        <input type="hidden" id="h_id_pelayanan_paket_pilih">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Instruksi Tindak Lanjut</label>
                                        <div class="col-md-8">
                                            <select class="form-control select2" id="keterangan" style="width: 100%"
                                                    onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()}; selectKeterangan(this.value)">
                                                <option value="">[Select One]</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" id="form-selesai" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Keterangan</label>
                                        <div class="col-md-8">
                                            <s:action id="initComboKet" namespace="/checkupdetail"
                                                      name="getListComboKeteranganKeluar_checkupdetail"/>
                                            <s:select list="#initComboKet.listOfKeterangan" id="ket_selesai"
                                                      listKey="idKeterangan"
                                                      listValue="keterangan" cssStyle="width: 100%"
                                                      onchange="var warn =$('#war_kolom-2').is(':visible'); if (warn){$('#col_kolom-2').show().fadeOut(3000);$('#war_kolom-2').hide()}; showFormCekup(this.value);"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" id="form-ket-rawat_inap" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Keterangan</label>
                                        <div class="col-md-8">
                                            <select class="form-control select2" id="keterangan_rw" style="width: 100%">
                                                <option value="">[Select One]</option>
                                                <option value="Preventif">Preventif</option>
                                                <option value="Kuratif">Kuratif</option>
                                                <option value="Variatif">Variatif</option>
                                                <option value="Paliatif">Paliatif</option>
                                                <option value="Rehabilitatif">Rehabilitatif</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="display: none" id="form-pindah_poli">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Poli</label>
                                    <div class="col-md-8">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select
                                                list="#initComboPoli.listOfPelayanan" id="poli_lain"
                                                name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                listValue="namaPelayanan" cssStyle="width: 100%"
                                                onchange="listDokterKeterangan(this.value);"
                                                headerKey="" headerValue="[Select one]"
                                                cssClass="form-control select2"/>
                                    </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Dokter</label>
                                        <div class="col-md-8">
                                            <div class="input-group">
                                                <input readonly class="form-control" id="nama_dokter"
                                                       style="cursor: pointer; margin-top: 7px" onclick="showJadwalDokter()"
                                                       placeholder="*klik untuk jadwal dokter">
                                                <div class="input-group-btn">
                                                    <a class="btn btn-success" onclick="showJadwalDokter()" style="margin-left: -3px">
                                                        <span id="btn-dokter"><i class="fa fa-search" ></i> Dokter</span></a>
                                                </div>
                                                <input type="hidden" id="list_dokter">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="display: none" id="form-metode_pembayaran">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Metode Pembayaran</label>
                                        <div class="col-md-8">
                                            <select class="form-control select2" id="metode_bayar" style="width: 100%">
                                                <option value="">[Select One]</option>
                                                <option value="tunai">Tunai</option>
                                                <option value="non_tunai">Non Tunai</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Uang Muka</label>
                                        <div class="col-md-8">
                                            <div class="input-group" style="margin-top: 7px">
                                                <div class="input-group-addon">
                                                    Rp.
                                                </div>
                                                <input class="form-control" id="val_uang_muka" oninput="convertRp(this.id, this.value)">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" id="form-rs-rujukan" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Rumah Sakit Rujukan</label>
                                        <div class="col-md-8">
                                            <s:textfield cssClass="form-control jarak" id="rs_rujukan" placeholder=""></s:textfield>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" id="form-tgl-kontrol" style="display: none">
                                    <div class="form-group">
                                        <div class="col-md-3">
                                            <input class="form-control ptr-tgl close_tanggal_kontrol" placeholder="Tanggal" style="margin-top: 7px" id="close_tgl_kontrol_0">
                                        </div>
                                        <div class="col-md-4">
                                            <select class="form-control select2 close_pelayanan_kontrol" id="close_pelayanan_0" onchange="setIntDokter(this.value, 'close_dokter_0')"></select>
                                        </div>
                                        <div class="col-md-4">
                                            <select class="form-control select2 close_dokter_kontrol" id="close_dokter_0"></select>
                                        </div>
                                        <div class="col-md-1">
                                            <button onclick="addKontrolUlang('close')" style="margin-left: -20px; margin-top: 7px" class="btn btn-success"><i class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                    <div id="set_kontrol_close"></div>
                                    <div class="form-group">
                                        <div class="col-md-offset-4 col-md-8">
                                            <div class="form-check jarak">
                                                <input onclick="isPemeriksaan(this.id, 'form-pemeriksaan')" type="checkbox" name="pemeriksaan_lab" id="pemeriksaan_lab" value="yes">
                                                <label for="pemeriksaan_lab"></label> Pemeriksaan Lab/Radiologi?
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row jarak" id="form-pemeriksaan" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 7px">Kategori</label>
                                        <div class="col-md-8">
                                            <s:action id="comboLab2" namespace="/kategorilab"
                                                      name="getListKategoriLab_kategorilab"/>
                                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                                      onchange="listSelectLab(this.value); inputWarning('war_ckp_kategori', 'cor_ckp_kategori')"
                                                      list="#comboLab2.listOfKategoriLab" id="ckp_kategori"
                                                      listKey="idKategoriLab"
                                                      listValue="namaKategori"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                            <span style="color: red; display: none;" id="war_ckp_kategori"><i
                                                    class="fa fa-times"></i> required</span>
                                            <span style="color: green; display: none;" id="cor_ckp_kategori">
                                                <i class="fa fa-check"></i> correct</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 7px">Jenis Pemeriksaan</label>
                                        <div class="col-md-8">
                                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="ckp_unit"
                                                    onchange="listSelectParameter(this.value); inputWarning('war_ckp_unit', 'cor_ckp_unit');">
                                                <option value=''>[Select One]</option>
                                            </select>
                                            <span style="color: red; display: none;" id="war_ckp_unit"><i
                                                    class="fa fa-times"></i> required</span>
                                            <span style="color: green; display: none;" id="cor_ckp_unit">
                                                <i class="fa fa-check"></i> correct</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 7px">Parameter</label>
                                        <div class="col-md-8">
                                            <select class="form-control select2" onchange="inputWarning('war_ckp_parameter', 'cor_ckp_parameter')" multiple style="margin-top: 7px; width: 100%" id="ckp_parameter">
                                                <option value=''>[Select One]</option>
                                            </select>
                                            <span style="color: red; display: none;" id="war_ckp_parameter"><i
                                                    class="fa fa-times"></i> required</span>
                                            <span style="color: green; display: none;" id="cor_ckp_parameter">
                                                <i class="fa fa-check"></i> correct</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-offset-4 col-md-8">
                                            <button onclick="addOrderListPemeriksaan()" class="btn btn-success"><i class="fa fa-plus"></i> Tambah</button>
                                            <button onclick="resetOrderPemeriksaan()" class="btn btn-danger"><i class="fa fa-refresh"></i> Reset</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" id="form-asesmen" style="display: none">
                                    <div class="form-group">
                                        <div class="col-md-offset-4 col-md-8">
                                            <div class="btn-group dropdown">
                                                <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Asesmen <span id="title_asesmen"></span>
                                                </button>
                                                <button id="btn_pindah" type="button" class="btn btn-primary dropdown-toggle"
                                                        data-toggle="dropdown" style="height: 34px">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu" id="asesmen_pindah">
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="display: none" id="form-rujuk_internal">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Poli Rujukan</label>
                                        <div class="col-md-8">
                                            <s:action id="initComboPoli2" namespace="/checkup"
                                                      name="getComboPelayanan_checkup"/>
                                            <s:select
                                                    list="#initComboPoli2.listOfPelayanan" id="rujuk_internal"
                                                    name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                    listValue="namaPelayanan" cssStyle="width: 100%"
                                                    headerKey="" headerValue="[Select one]"
                                                    cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="display: none" id="form_eksekutif">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Eksekutif ?</label>
                                        <div class="col-md-8">
                                            <div class="form-check" style="margin-top: 10px">
                                                <input type="checkbox" id="is_eksekutif" value="yes">
                                                <label for="is_eksekutif"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row" id="form-catatan" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Catatan</label>
                                        <div class="col-md-8">
                                            <s:textarea cssClass="form-control jarak" id="pesan_dokter" rows="3" placeholder="Pesan untuk pasien"></s:textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="display: none" id="form_vaksin">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Vaksin ?</label>
                                        <div class="col-md-8">
                                            <div class="form-check" style="margin-top: 10px">
                                                <input type="checkbox" id="is_vaksin" value="yes">
                                                <label for="is_vaksin"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 30px; display: none" id="form_order_pemeriksaan">
                                    <div class="form-group">
                                        <div class="col-md-12">
                                            <span>Daftar Order Pemeriksaan</span>
                                            <table class="table table-bordered" style="font-size: 13px" id="tabel_order_pemeriksaan">
                                                <thead>
                                                <tr>
                                                    <td>Jenis Pemeriksaan</td>
                                                    <td>Parameter</td>
                                                    <td align="10%">Action</td>
                                                </tr>
                                                </thead>
                                                <tbody id="body_order_pemeriksaan">
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border" id="pos_finis">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-4 text-center">
                                        <a class="btn btn-warning" href="initForm_checkupdetail.action"><i class="fa fa-arrow-left"></i> Back</a>
                                        <%--<a class="btn btn-primary" onclick="printGelangPasien()"><i class="fa fa-print"></i> Print</a>--%>
                                        <a class="btn btn-success" id="save_ket" onclick="confirmPemeriksaanPasien()"><i class="fa fa-check"></i> Selesai</a>
                                        <button style="display: none; cursor: no-drop;" type="button"
                                                class="btn btn-success" id="load_ket"><i class="fa fa-spinner fa-spin"></i>
                                            Sedang Menyimpan...
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-alergi">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Tambah Alergi</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_alergi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis</label>
                        <div class="col-md-7">
                            <select class="form-control" id="jenis_alergi" onchange="var warn =$('#war_jenis_alergi').is(':visible'); if (warn){$('#cor_jenis_alergi').show().fadeOut(3000);$('#war_jenis_alergi').hide()}">
                                <option value="">[Select One]</option>
                                <option value="Obat">Obat</option>
                                <option value="Makanan">Makanan</option>
                                <option value="Lain-Lain">Lain-Lain</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_jenis_alergi">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_jenis_alergi"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">
                            Alergi
                            <i class="fa fa-question-circle box-rm" style="font-size: 18px">
                                    <span class="box-rmtext" style="font-size: 12px; font-family: Calibri">
                                        Pisahkan dengan coma jika alergi lebih dari 1 pada 1 jenis alergi
                                    </span>
                        </i>
                        </label>
                        <div class="col-md-7">
                            <textarea class="form-control" id="alergi" autocomplete="off" rows="5"
                                   oninput="var warn =$('#war_alergi').is(':visible'); if (warn){$('#cor_alergi').show().fadeOut(3000);$('#war_alergi').hide()}"
                            ></textarea>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_alergi">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_alergi"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_alergi"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_alergi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-dokter">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> <span id="t_dokter"></span></h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_dokter">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_dokter"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Dokter</label>
                        <div class="col-md-7">
                            <select id="dok_id_dokter" style="width: 100%" class="form-control select2"
                                    onchange="var warn =$('#war_dok').is(':visible'); if (warn){$('#cor_dok').show().fadeOut(3000);$('#war_dok').hide()}">
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_dok"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_dok"><i
                                    class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_dokter"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_dokter"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-tindakan">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Tambah Tindakan</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_tindakan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_tindakan"></p>
                </div>
                <div class="alert alert-info alert-dismissible" style="display: none" id="warning_konsul">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_konsul"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Dokter</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="tin_id_dokter_dpjp"
                                    onchange="listSelectTindakanKategori(this.value); var warn =$('#war_dpjp').is(':visible'); if (warn){$('#cor_dpjp').show().fadeOut(3000);$('#war_dpjp').hide()}">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_dpjp"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_dpjp"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kategori Tindakan</label>
                        <div class="col-md-7">
                                <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                        id="tin_id_ketgori_tindakan"
                                        onchange="listSelectTindakan(this.value); var warn =$('#war_kategori').is(':visible'); if (warn){$('#cor_kategori').show().fadeOut(3000);$('#war_kategori').hide()}">
                                    <option value=''>[Select One]</option>
                                </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_kategori"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_kategori"><i class="fa fa-check"></i> correct</p>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Tindakan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="tin_id_tindakan"
                                    onchange="var warn =$('#war_tindakan').is(':visible'); if (warn){$('#cor_tindakan').show().fadeOut(3000);$('#war_tindakan').hide()}; setDiskonHarga(this.value)">
                                <option value=''> - </option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_tindakan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon (%)</label>
                        <div class="col-md-7">
                            <input style="margin-top: 7px" class="form-control" readonly id="h_diskon">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Harga (Rp.)</label>
                        <div class="col-md-3">
                            <input style="margin-top: 7px" class="form-control" readonly id="h_harga">
                        </div>
                        <div class="col-md-1">
                            <i class="fa fa-arrow-right" style="margin-top: 15px"></i>
                        </div>
                        <div class="col-md-3">
                            <input style="margin-top: 7px" class="form-control" readonly id="h_harga_after">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <input type="number" min="1" class="form-control" style="margin-top: 7px" id="tin_qty"
                                   oninput="$(this).css('border','')" onchange="$(this).css('border','')" value="1">
                        </div>
                    </div>
                    <input type="hidden" id="is_edit">
                    <input type="hidden" id="is_elektif">
                    <div class="form-group" style="display: none" id="form_elektif">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Jam</label>
                        <div class="col-md-7">
                            <input type="number" min="1" class="form-control" style="margin-top: 7px" id="tin_qty_elektif"
                                   oninput="$(this).css('border','')" onchange="$(this).css('border','')">
                        </div>
                    </div>
                    <div class="form-group" id="form-btn-add" style="display: none">
                        <div class="col-md-offset-3 col-md-9">
                            <button onclick="addToListTindakan()" class="btn btn-success"><i class="fa fa-plus"></i> Tambah</button>
                            <button onclick="resetListTindakan()" class="btn btn-danger"><i class="fa fa-refresh"></i> Reset</button>
                        </div>
                    </div>
                    <div class="form-group" id="form-list" style="display: none">
                        <label class="col-md-12">
                            <table id="table_list_tindakan" class="table table-bordered table-hover" style="font-size: 12px; margin-top: 20px; width: 100%">
                                <thead>
                                <tr>
                                    <td>Dokter</td>
                                    <td>Tindakan</td>
                                    <td align="center">Qty</td>
                                    <td align="right">Tarif (Rp.)</td>
                                    <td align="right">Total (Rp.)</td>
                                    <td align="center">Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_temp_tindakan">

                                </tbody>
                            </table>
                        </label>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_tindakan"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_tindakan">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-diagnosa">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-stethoscope"></i> <span id="t_diagnosa"></span></h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_diagnosa">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                        <div class="form-group">
                        <label class="col-md-3">Diagnosa</label>
                        <div class="col-md-7">
                        <s:textfield id="nosa_id_diagnosa" style="margin-top: 7px"
                                     name="headerCheckup.diagnosa" autocomplete="off"
                                     onkeypress="var warn =$('#war_diagnosa_bpjs').is(':visible'); if (warn){$('#cor_diagnosa_bpjs').show().fadeOut(3000);$('#war_diagnosa_bpjs').hide()}; searchDiagnosa(this.id)"
                                     cssClass="form-control" required="false"/>
                        </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_diagnosa_bpjs"><i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_diagnosa_bpjs"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                </div>
                <div class="row">
                        <input type="hidden" id="val_jenis_diagnosa">
                        <div class="form-group">
                        <div class="col-md-offset-3 col-md-7">
                        <s:textarea rows="4" id="nosa_ket_diagnosa"
                                    cssStyle="margin-top: 7px" readonly="true"
                                    name="headerCheckup.namaDiagnosa"
                                    cssClass="form-control"></s:textarea>
                        </div>
                        </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Jenis Diagnosa</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="nosa_jenis_diagnosa"
                                    onchange="var warn =$('#war_jenis_diagnosa').is(':visible'); if (warn){$('#cor_jenis_diagnosa').show().fadeOut(3000);$('#war_jenis_diagnosa').hide()}">
                                <option value=""> - </option>
                                <option value="diagnosa_primer">Diagnosa Primer</option>
                                <option value="diagnosa_sekunder">Diagnosa Sekunder</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_jenis_diagnosa"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_jenis_diagnosa"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_diagnosa"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_diagnosa">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icd9">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-stethoscope"></i> Tambah ICD9</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icd9">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_icd9"></p>
                </div>
                <input type="hidden" id="id_edit_icd9">
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Kode ICD9</label>
                        <div class="col-md-7">
                            <s:textfield id="id_icd9" style="margin-top: 7px" autocomplete="off"
                                         onkeypress="var warn =$('#war_id_icd9').is(':visible'); if (warn){$('#cor_id_icd9').show().fadeOut(3000);$('#war_id_icd9').hide()}; searchICD9(this.id)"
                                         cssClass="form-control" required="false"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_id_icd9"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_id_icd9"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-7">
                            <s:textarea rows="4" id="ket_icd9"
                                        cssStyle="margin-top: 7px" readonly="true"
                                        cssClass="form-control"></s:textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_icd9"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_icd9">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-lab">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Penunjang Medis</h4>
            </div>
            <div class="modal-body" id="temp_lab">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_lab">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_lab"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kategori</label>
                        <div class="col-md-7">
                            <s:action id="comboLab" namespace="/kategorilab"
                                      name="getListKategoriLab_kategorilab"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn =$('#war_kategori_lab').is(':visible'); if (warn){$('#cor_kategori_lab').show().fadeOut(3000);$('#war_kategori_lab').hide()}; listSelectLab(this.value)"
                                      list="#comboLab.listOfKategoriLab" id="lab_kategori"
                                      listKey="idKategoriLab"
                                      listValue="namaKategori"
                                      headerKey="" headerValue=" - "
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_kategori_lab"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_kategori_lab"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row" id="form_is_luar">
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check jarak" id="cek_luar">
                                <input onclick="isLuar(this.id)" type="checkbox" id="is_luar" value="yes">
                                <label for="is_luar"></label>
                                Centang Jika Pemeriksaan Luar
                                <i class="fa fa-question-circle box-rm" style="font-size: 18px">
                                    <span class="box-rmtext" style="font-size: 12px; font-family: Calibri">
                                        Centang untuk melakukan order penunjang medis diluar
                                    </span></i>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Pemeriksaan</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px;" id="select-jenis-pemeriksaan">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check jarak">
                                <input type="checkbox" id="is_cito" value="yes">
                                <label for="is_cito"></label>
                                Centang Jika Pemeriksaan Darurat (CITO)
                                <i class="fa fa-question-circle box-rm" style="font-size: 18px">
                                    <span class="box-rmtext" style="font-size: 12px; font-family: Calibri">
                                        Centang untuk menandai pemeriksaan dengan CITO
                                    </span></i>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" style="display: none" id="form_tarif_lab_luar">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif Lab Luar</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    Rp.
                                </div>
                                <input class="form-control"  oninput="convertRpAtas(this.id, this.value, 'h_total_tarif'); var warn =$('#war_tarif_luar_lab').is(':visible'); if (warn){$('#cor_tarif_luar_lab').show().fadeOut(3000);$('#war_tarif_luar_lab').hide()}"
                                       id="tarif_luar_lab" placeholder="Tarif">
                                <input type="hidden" id="h_total_tarif">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_tarif_luar_lab"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_tarif_luar_lab"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <hr>
                <div id="form_lab_luar" style="display: none">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jenis Pemeriksaan</label>
                            <div class="col-md-7">
                                <input class="form-control" style="margin-top: 7px; width: 100%" id="lab_luar" placeholder="masukkan jenis pemeriksaan"
                                        oninput="var warn =$('#war_lab_luar').is(':visible'); if (warn){$('#cor_lab_luar').show().fadeOut(3000);$('#war_lab_luar').hide()};">
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_lab_luar"><i
                                        class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_lab_luar"><i
                                        class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Parameter</label>
                            <div class="col-md-7" id="params_luar">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control parameter_luar"
                                           id="lab_parameter_luar" placeholder="masukkan parameter 1"
                                           oninput="var warn =$('#war_lab_parameter_luar').is(':visible'); if (warn){$('#cor_lab_parameter_luar').show().fadeOut(3000);$('#war_lab_parameter_luar').hide()};">
                                    <div onclick="addParameter()" class="input-group-addon" style="background-color: #5cb85c; color: white; cursor: pointer">
                                        <i class="fa fa-plus"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_lab_parameter_luar"><i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_lab_parameter_luar"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" id="form_is_pending">
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check jarak" id="cek_pending">
                                <input onclick="isPemeriksaan(this.id, 'form_pending')" type="checkbox" id="is_pending_lab" value="yes">
                                <label for="is_pending_lab"></label> Input hasil untuk waktu yg ditentukan <i class="fa fa-question-circle box-rm" style="font-size: 18px"><span class="box-rmtext" style="font-size: 12px; font-family: Calibri">Centang pilihan tersebut jika penginputan nilai hasil pemeriksaan lab atau radiologi,  tidak bisa langsung. yang berarti pasien bisa menyelesaikan adminstrasi dahulu</span></i>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group" style="display: none;" id="form_pending">
                        <label class="col-md-3" style="margin-top: 7px">Tanggal & Jam</label>
                        <div class="col-md-4">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon" >
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control tgl" id="tgl_pending" readonly style="cursor: pointer"
                                       placeholder="mm-dd-yyyy"
                                       onchange="var warn =$('#war_pending').is(':visible'); if (warn){$('#cor_pending').show().fadeOut(3000);$('#war_pending').hide()};">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    <i class="fa fa-clock-o"></i>
                                </div>
                                <input class="form-control jam" id="jam_pending"
                                       placeholder="hh:mm"
                                       onchange="var warn =$('#war_pending').is(':visible'); if (warn){$('#cor_pending').show().fadeOut(3000);$('#war_pending').hide()};">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <i class="fa fa-question-circle box-rm" style="font-size: 18px; margin-left: -25px; margin-top: 15px">
                                <span class="box-rmtext" style="font-size: 12px; font-family: Calibri">
                                    Isi waktu perkiraan minimal dokter lab / radiologi dapat menginputkan hasil pemeriksaan. perkiraan waktu selalu default pada saat user akan order lab pada form ini
                                </span></i>
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_pending"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_pending"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <%--<div class="row">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px"></label>--%>
                        <%--<div class="col-md-7">--%>
                            <%----%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <button class="btn btn-success" id="btn-add-lab-dalam" onclick="showModalListPenunjang()">
                                <i class="fa fa-plus"></i> Tambah Pemeriksaan
                            </button>
                            <button onclick="addListPemeriksaan()" id="btn-add-lab-luar" class="btn btn-success"><i class="fa fa-plus"></i> Tambah</button>
                            <button onclick="resetPemeriksaan()" class="btn btn-danger"><i class="fa fa-refresh"></i> Reset</button>
                        </div>
                    </div>
                </div>
                <hr class="garis">
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-12">
                            <table class="table table-bordered" style="font-size: 13px" id="tabel_pemeriksaan">
                                <thead>
                                <tr>
                                    <td>Jenis Pemeriksaan</td>
                                    <td>Parameter</td>
                                    <td>Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_pemeriksaan">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row" id="form_ttd">
                    <div class="col-md-offset-3 col-md-6">
                        <canvas style="cursor: pointer" onmouseover="paintTtd('ttd_dokter_pengirim')" class="paint-canvas" id="ttd_dokter_pengirim" width="250" height="200"></canvas>
                        <select onchange="onChangePengirim(this.value)" class="form-control" id="select_pengirim"></select>
                        <input style="margin-top: 5px" class="form-control" id="sip_pengirim">
                        <buton onclick="removePaint('ttd_dokter_pengirim')" class="btn btn-danger"><i class="fa fa-trash"></i> Clear</buton>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <span onclick="cekScrol('fa_lab', 'temp_lab')" class="pull-left hvr-grow" style="color: black; margin-top: 11px; cursor: pointer">
                    <i id="fa_lab" class="fa fa-unlock"></i>
                </span>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_lab"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_lab">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-cancel-diet">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Cancel Order Gizi </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_cancel">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_cancel"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-2">Keterangan</label>
                        <div class="col-md-8">
                            <textarea oninput="var warn =$('#war_keterangan_cancel').is(':visible'); if (warn){$('#cor_keterangan_cancel').show().fadeOut(3000);$('#war_keterangan_cancel').hide()}" class="form-control" id="keterangan_cancel" rows="3"></textarea>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_keterangan_cancel"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_keterangan_cancel">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_cancel_diet"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_cancel_diet"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<%--<div class="modal fade" id="modal-obat">--%>
    <%--<div class="modal-dialog modal-flat">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header" style="background-color: #00a65a">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                    <%--<span aria-hidden="true">&times;</span></button>--%>
                <%--<h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Obat Penunjang</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">--%>
                    <%--<h4><i class="icon fa fa-ban"></i> Warning!</h4>--%>
                    <%--<p id="obat_error"></p>--%>
                <%--</div>--%>
                <%--<div class="row">--%>
                    <%--<div class="form-group" id="nama_form">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Nama Obat</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:action id="initObatPoli" namespace="/obatpoli"--%>
                                      <%--name="getListObatPoli_obatpoli"/>--%>
                            <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                      <%--list="#initObatPoli.listOfObatPoli" id="ob_id_obat"--%>
                                      <%--listKey="idObat + '|' + namaObat + '|' + qtyBox + '|' + qtyLembar + '|' + qtyBiji + '|' + lembarPerBox + '|' + bijiPerLembar"--%>
                                      <%--listValue="namaObat"--%>
                                      <%--onchange="var warn =$('#war_obat').is(':visible'); if (warn){$('#cor_obat').show().fadeOut(3000);$('#war_obat').hide()}; setStokObat(this);"--%>
                                      <%--headerKey="" headerValue=" - "--%>
                                      <%--cssClass="form-control select2"/>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_obat"><i--%>
                                    <%--class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_obat">--%>
                                <%--<i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group" id="nama_obat_form">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Nama Obat</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:textfield readonly="true" type="text" min="1" cssClass="form-control"--%>
                                         <%--cssStyle="margin-top: 7px" id="nama_obat"></s:textfield>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Stok Obat</label>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<label style="margin-top: 7px">Box</label>--%>
                            <%--<s:textfield readonly="true" type="text" min="1" cssClass="form-control"--%>
                                         <%--id="ob_stok_box"></s:textfield>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<label style="margin-top: 7px">Lembar</label>--%>
                            <%--<s:textfield readonly="true" type="text" min="1" cssClass="form-control"--%>
                                         <%--id="ob_stok_lembar"></s:textfield>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-3">--%>
                            <%--<label style="margin-top: 7px">Biji</label>--%>
                            <%--<s:textfield readonly="true" type="text" min="1" cssClass="form-control"--%>
                                         <%--id="ob_stok_biji"></s:textfield>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:select list="#{'box':'Box','lembar':'Lembar','biji':'Biji'}"--%>
                                      <%--cssStyle="margin-top: 7px; width: 100%"--%>
                                      <%--onchange="var warn = $('#war_ob_jenis_satuan').is(':visible'); if (warn){$('#cor_ob_jenis_satuan').show().fadeOut(3000);$('#war_ob_jenis_satuan').hide()}"--%>
                                      <%--id="ob_jenis_satuan"--%>
                                      <%--headerKey="" headerValue=" - "--%>
                                      <%--cssClass="form-control select2"/>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_ob_jenis_satuan"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_ob_jenis_satuan"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Jumlah</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:textfield type="number" min="1" cssClass="form-control"--%>
                                         <%--cssStyle="margin-top: 7px" id="ob_qty"--%>
                                         <%--onkeypress="var warn =$('#war_qty_obat').is(':visible'); if (warn){$('#cor_qty_obat').show().fadeOut(3000);$('#war_qty_obat').hide()}"></s:textfield>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_qty_obat"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_qty_obat"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<input type="hidden" id="set_id_obat">--%>
            <%--<input type="hidden" id="set_lembar_perbox">--%>
            <%--<input type="hidden" id="set_biji_perlembar">--%>
            <%--<div class="modal-footer" style="background-color: #cacaca">--%>
                <%--<button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close--%>
                <%--</button>--%>
                <%--<button type="button" class="btn btn-success" id="save_obat"><i class="fa fa-check"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<div class="modal fade" id="modal-resep-head">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="title-resep"></span></h4>
            </div>
            <div class="modal-body" id="temp_resep-head">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_resep_head">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_resep"></p>
                </div>
                <input type="hidden" id="tipe-trans-resep"/>
                <div class="row">
                    <label class="col-md-3" style="margin-top: 7px">Obat Racik ?</label>
                    <div class="col-md-9">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_racik" id="racik_racik" value="Y" onclick="var warn = $('#war_rep_racik').is(':visible'); if (warn){$('#cor_rep_racik').show().fadeOut(3000);$('#war_rep_racik').hide()}; cekRacik(this.id)">
                            <label for="racik_racik"></label> Ya
                        </div>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_racik"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_racik"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-3" style="margin-top: 7px">Apotek</label>
                    <div class="col-md-9">
                        <div id="body-apotek">
                            <s:action id="initApotek" namespace="/checkup"
                                      name="getComboApotek_checkup"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initApotek.listOfApotek" id="resep_apotek"
                                      listKey="idPelayanan + '|' + namaPelayanan"
                                      listValue="namaPelayanan"
                                      cssClass="form-control select2"/>
                        </div>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_apotek"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_apotek"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row" id="form-nama-racik" style="display: none">
                    <label class="col-md-3" style="margin-top: 7px;">Nama Racik</label>
                    <div class="col-md-9">
                        <input oninput="var warn =$('#war_nama_racik').is(':visible'); if (warn){$('#cor_nama_racik').show().fadeOut(3000);$('#war_nama_racik').hide()}"
                               class="form-control" type="text"
                               id="nama_racik" style="margin-top: 7px;">
                        <%--<div class="input-group" style="margin-top: 7px;">--%>
                            <%--<input oninput="var warn =$('#war_nama_racik').is(':visible'); if (warn){$('#cor_nama_racik').show().fadeOut(3000);$('#war_nama_racik').hide()}"--%>
                            <%--class="form-control" type="text"--%>
                            <%--id="nama_racik">--%>
                            <%--<div class="input-group-addon">--%>
                                <%--<input type="color" id="color_racik" style="height: 20px;" value="#a4dfab">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_nama_racik"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_nama_racik"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-3" style="margin-top: 7px">Pilih Obat</label>
                    <div class="col-md-9">
                        <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                id="resep_nama_obat">
                            <option value=""> - </option>
                        </select>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_obat"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_obat"><i class="fa fa-check"></i> correct</span>
                        <button class="btn btn-sm btn-primary" style="display: none;" id="btn-reset-combo-obat" onclick="resetComboObat()"><i class="fa fa-edit"></i></button>
                        <input type="hidden" id="val-kronis"/>
                    </div>
                </div>
                <div class="row jarak">
                    <label class="col-md-3">Informasi Obat</label>
                    <div class="col-md-9">
                        <table class="table" style="font-size: 12px; border: solid 1px #ddd">
                            <tr>
                                <td width="30%">- <span id="set_formula"></span></td>
                                <td align="left"></td>
                                <td width="20%"></td>
                                <td width="30%">- <span id="set_teral"></span></td>
                                <td align="left"></td>
                            </tr>
                            <tr>
                                <td width="30%">- Jenis Satuan : </td>
                                <td align="left"><span id="set_js"></span></td>
                                <td width="20%"></td>
                                <td width="30%">
                                    <span style="display: none;" id="label-kronis"><label class="label label-warning" >Obat Kronis</label></span>
                                </td>
                                <td align="left"></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-3" style="margin-top: 7px">Stok Obat</label>
                    <div class="col-md-9">
                        <div class="input-group" style="margin-top: 7px;">
                            <input class="form-control" type="number" min="1" id="resep_stok_biji" readonly>
                            <div class="input-group-addon">
                                Biji
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="h-qty-default"/>
                </div>
                <div id="obat-serupa" style="background-color: #fff4f0; height:100px; padding-top:5px; margin-top:5px">
                    <div class="row">
                        <label class="col-md-12" style="color: black"><b class="blink_me">Obat Kandungan Serupa</b></label>
                        <input type="hidden" value="N" id="flag-obat-serupa">
                        <label class="col-md-3" style="margin-top: 7px">Pilih Obat</label>
                        <div class="col-md-9">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="resep_nama_obat_serupa">
                                <option value=""> - </option>
                            </select>
                            <span style="color: red; margin-top: 12px; display: none;"
                                  id="war_rep_obat_serupa"><i class="fa fa-times"></i> required</span>
                            <span style="color: green; margin-top: 12px; display: none;"
                                  id="cor_rep_obat_serupa"><i class="fa fa-check"></i> correct</span>
                            <span style="margin-top: 17px; display: none;" id="label-kronis-serupa"><label class="label label-warning" >Obat Kronis</label></span>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat</label>
                        <div class="col-md-9">
                            <div class="input-group" style="margin-top: 7px;">
                                <input class="form-control" type="number" min="1" id="resep_stok_biji_serupa" readonly>
                                <div class="input-group-addon">
                                    Biji
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" style="display: none;">
                    <label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>
                    <div class="col-md-9">
                        <s:select list="#{'lembar':'Lembar','box':'Box'}"
                                  cssStyle="margin-top: 7px; width: 100%"
                                  onchange="var warn = $('#war_rep_jenis_satuan').is(':visible'); if (warn){$('#cor_rep_jenis_satuan').show().fadeOut(3000);$('#war_rep_jenis_satuan').hide()};defaultValByJenisSatuan(this.value)"
                                  id="resep_jenis_satuan"
                                  headerKey="biji" headerValue="Biji"
                                  cssClass="form-control select2" disabled="true"/>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_jenis_satuan"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_jenis_satuan"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row" id="sec-jumlah-resep">
                    <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                    <div class="col-md-9">
                        <div class="input-group" style="margin-top: 7px;">
                            <input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_qty').show().fadeOut(3000);$('#war_rep_qty').hide()}"
                                   value="1" class="form-control" type="number" min="1"
                                   id="resep_qty">
                            <div class="input-group-addon">
                                Biji
                            </div>
                        </div>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_qty"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_qty"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row" id="form-hari" style="display: none">
                    <label class="col-md-3" style="margin-top: 7px; font-size:12px">Pengambilan(Hari)</label>
                    <div class="col-md-9">
                        <input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_hari').show().fadeOut(3000);$('#war_rep_hari').hide()}"
                               style="margin-top: 7px; width: 40%;" value="7" class="form-control" type="number" min="1"
                               id="hari-kronis">
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_hari"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_hari"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row" style="display: none">
                    <label class="col-md-3" style="margin-top: 7px">Jenis Resep</label>
                    <div class="col-md-9">
                        <select class="form-control" style="margin-top: 7px;" id="select-jenis-resep">
                        </select>
                    </div>
                </div>
                <hr/>
                <div class="row" style="margin-top: -10px" id="btn-add-resep">
                    <div class="col-md-12">
                        <button class="btn btn-success" onclick="addObatToList()" id="btn-save-resep-normal"><i class="fa fa-plus"></i> Tambah
                        </button>
                        <button class="btn btn-success" onclick="saveObatRacikToSession()" id="btn-save-resep-racik"><i class="fa fa-plus"></i> Tambah Racik
                        </button>
                        <button class="btn btn-danger" onclick="resetAll()"><i
                                class="fa fa-refresh"></i> Reset
                        </button>
                    </div>
                </div>
                <div class="box-header with-border">
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_data_exits">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Data obat sudah tersedia..!
                </div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Resep Obat, tujuan <b><span
                        id="desti_apotek"></span></b>
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_rese_detail" style="font-size: 13px;">
                        <thead>
                        <td>Nama Obat</td>
                        <td align="center">Qty</td>
                        <td>Keterangan / Signa</td>
                        <td align="center">Harga (Rp.)</td>
                        <td align="center" width="5%">Action</td>
                        </thead>
                        <tbody id="body_detail">
                        </tbody>
                    </table>

                    <div id="informasi-racik">
                    </div>
                    <br>
                </div>
                <div class="row" style="margin-top: 10px">
                    <div class="form-group">
                        <div class="col-md-6">
                            <div id="sec-total-harga">
                                <label><b>Total Harga</b></label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        Rp.
                                    </div>
                                    <input class="form-control" id="total_harga_obat" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <span style="margin-left: 10px"><b>TTD Dokter</b></span>
                            <div class="form-group" style="padding-top: 10px; padding-bottom: 10px; display: none">
                                <div class="col-md-1">
                                    <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker  color-picker pull-left">
                                </div>
                                <div class="col-md-9">
                                    <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72" value="1">
                                </div>
                                <div class="col-md-2">
                                    <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                                </div>
                            </div>
                            <canvas onmouseover="paintTtd('ttd_canvas')" style="margin-top: 2px" class="paint-canvas" id="ttd_canvas" width="250" height="200"></canvas>
                            <button onclick="removePaint('ttd_canvas')" style="margin-top: -8px; margin-right: 5px" class="btn btn-danger pull-right"><i class="fa fa-trash"></i> Clear</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                 <span onclick="cekScrol('fa_resep-head', 'temp_resep-head')" class="pull-left hvr-grow" style="color: black; margin-top: 11px; cursor: pointer">
                    <i id="fa_resep-head" class="fa fa-unlock"></i>
                </span>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_resep_head" onclick="saveResepObatTtd()"><i
                        class="fa fa-check"></i> Buat Resep
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_resep_head"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-resep-history">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Copy Resep</h4>
            </div>
            <div class="modal-body">

                <div class="box-header with-border"><i class="fa fa-file-o"></i> Riwayat Resep </div>
                <table class="table table-striped table-bordered" style="font-size: 13px;">
                    <thead>
                    <td>No. Resep</td>
                    <td>Tgl. Resep</td>
                    <td>No. Rawat</td>
                    <td>No. RM</td>
                    <td align="center" width="5%">Action</td>
                    </thead>
                    <tbody id="body-riwayat-resep">
                    </tbody>
                </table>

            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <%--<button type="button" class="btn btn-success" id="save_resep_head" onclick="saveResepObatTtd()"><i--%>
                        <%--class="fa fa-check"></i> Copy Resep--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success"--%>
                        <%--id="load_resep_head"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-resep-detail">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail Resep Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_detail">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan..!
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_detail">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    Data berhasil diupdate..!
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_rep">
                        <thead>
                        <td>Obat</td>
                        <td>Qty</td>
                        <td>Keterangan</td>
                        <td align="center" width="5%">Action</td>
                        </thead>
                        <tbody id="body_detail_rep">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-laka">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Silahkan Isi Data Asuransi</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_laka">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_laka"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">No Polisi</label>
                        <div class="col-md-7">
                            <input class="form-control" id="laka_no_polisi"
                                   oninput="var warn =$('#war_laka_no_polisi').is(':visible'); if (warn){$('#cor_laka_no_polisi').show().fadeOut(3000);$('#war_laka_no_polisi').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_laka_no_polisi"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_laka_no_polisi"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tgl Kejadian</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control datepicker" id="laka_tgl_kejadian" readonly="true" placeholder="yyyy-mm-dd" style="cursor: pointer"
                                       onchange="var warn =$('#war_laka_tgl_kejadian').is(':visible'); if (warn){$('#cor_laka_tgl_kejadian').show().fadeOut(3000);$('#war_laka_tgl_kejadian').hide()}">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_laka_tgl_kejadian"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_laka_tgl_kejadian"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Foto Surat Polisi</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px">
                                  <span class="input-group-btn">
                                  <span class="btn btn-default btn-file" style="margin-top: 0px">
                                  Browse… <s:file id="url_do" accept=".jpg"
                                  onchange="$('#img_file').css('border',''); setCanvasAtasWithText('laka_surat_rujukan', 'temp_surat_rujuk')"></s:file>
                                  </span>
                                  </span>
                                <input type="text" class="form-control" readonly id="laka_surat_rujukan">
                            </div>
                        </div>
                        <div style="display: none;">
                            <canvas id="temp_surat_rujuk"></canvas>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_laka_surat_polisi"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_laka_surat_polisi"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_laka"><i
                class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_laka"><i
                class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-detail_lab">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail Penunjang Medis</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <table class="table table-striped table-bordered" style="font-size: 13px">
                        <thead>
                        <td>Jenis Penunjang</td>
                        <td>Jenis Pemeriksaan</td>
                        <td>Parameter</td>
                        <tbody id="body_detail_lab">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-history">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> All History
                </h4>
            </div>
            <div class="modal-body" style="height: 450px;overflow-y: scroll;">
                <div class="box-body">
                    <table class="table table-bordered" style="font-size: 12px;">
                        <thead>
                        <tr style="font-weight: bold">
                            <td width="30%">Pelayanan</td>
                            <%--<td>No Transaksi</td>--%>
                            <td width="15%">Waktu</td>
                            <td>Keterangan</td>
                            <td width="14%">Catatan</td>
                            <td width="8%">Telemedic</td>
                        </tr>
                        </thead>
                        <tbody id="body_history">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-lab_luar">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="title_lab_luar"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <img id="img_lab_luar" style="width: 100%">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-telemedic">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Telemedic Pasien Pada Tanggal <span id="tanggal_tele"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <video controls width="100%" height="420px" id="body-video-rm"></video>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-jadwal-dokter">
    <div class="modal-dialog" style="width: 57%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Jadwal Dokter <span
                        id="dokter_pelayanan"></span> Hari Ini</h4>
            </div>
            <div class="modal-body" id="temp_jd">
                <div class="box-body">
                    <div class="col-md-12 text-center" style="display:inline; padding-left: 6%">
                        <div class="btn-wrapper">
                            <div id="jadwal_dokter"></div>
                        </div>
                    </div>
                    <div class="col-md-offset-9 col-md-3">
                        <ul style="list-style-type: none">
                            <li>
                                <span style="color: white; background-color: #ec971f; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">Kuota Non BPJS</span>
                            </li>
                            <li>
                                <span style="margin-left: 5px;color: white; background-color: #00a65a; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">Kuota BPJS</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <span onclick="cekScrol('fa_temp_jd', 'temp_jd')" class="pull-left hvr-grow" style="color: black; margin-top: 11px; cursor: pointer">
                    <i id="fa_temp_jd" class="fa fa-unlock"></i>
                </span>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-diet">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Order Gizi</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_diet">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_diet"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 10px">Jenis Diet</label>
                        <div class="col-md-7">
                            <select id="jenis_diet" class="form-control select2" multiple style="width: 100%"
                                    onchange="var warn =$('#war_jenis_diet').is(':visible'); if (warn){$('#cor_jenis_diet').show().fadeOut(3000);$('#war_jenis_diet').hide()}">

                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_jenis_diet"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_jenis_diet">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 10px">Bentuk Diet</label>
                        <div class="col-md-7">
                            <s:action id="comboDiet1" namespace="/rawatinap"
                                      name="getComboBoxDietGizi_rawatinap"/>
                            <s:select list="#comboDiet1.listOfDietGizi" listKey="idDietGizi" listValue="namaDietGizi" id="bentuk_diet"
                                      onchange="var warn =$('#war_bentuk_diet').is(':visible'); if (warn){$('#cor_bentuk_diet').show().fadeOut(3000);$('#war_bentuk_diet').hide()}"
                                      headerKey="" headerValue=" - " cssClass="form-control select2" cssStyle="width: 100%"/>

                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_bentuk_diet"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_bentuk_diet">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak">
                    <div class="form-group">
                        <label class="col-md-3">Keterangan</label>
                        <div class="col-md-7">
                            <div class="form-check">
                                <input type="checkbox" name="ket_diet" id="ket_diet1" value="pagi" checked class="remove_cek"
                                       onclick="var warn =$('#war_ket_diet').is(':visible'); if (warn){$('#cor_ket_diet').show().fadeOut(3000);$('#war_ket_diet').hide()}; setDiet(this.id)">
                                <label for="ket_diet1"></label> Pagi
                            </div>
                            <div class="form-check" style="margin-left: 10px">
                                <input type="checkbox" name="ket_diet" id="ket_diet2" value="siang" class="remove_cek"
                                       onclick="var warn =$('#war_ket_diet').is(':visible'); if (warn){$('#cor_ket_diet').show().fadeOut(3000);$('#war_ket_diet').hide()}; setDiet(this.id)">
                                <label for="ket_diet2"></label> Siang
                            </div>
                            <div class="form-check" style="margin-left: 10px">
                                <input type="checkbox" name="ket_diet" id="ket_diet3" value="malam" class="remove_cek"
                                       onclick="var warn =$('#war_ket_diet').is(':visible'); if (warn){$('#cor_ket_diet').show().fadeOut(3000);$('#war_ket_diet').hide()}; setDiet(this.id)">
                                <label for="ket_diet3"></label> Malam
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 6px; display: none; margin-left: -20px" id="war_ket_diet"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 6px; display: none; margin-left: -20px" id="cor_ket_diet">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <hr class="garis">
                <div class="row">
                    <div class="col-md-12">
                        <table id="table_add_diet" class="table table-bordered" style="font-size: 12px">
                            <thead>
                            <tr>
                                <td>Waktu</td>
                                <td>Jenis</td>
                                <td>Bentuk</td>
                            </tr>
                            </thead>
                            <tbody id="body_add_diet"></tbody>
                        </table>
                    </div>
                </div>
                <hr class="garis">
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Order Gizi Untuk ?</label>
                        <div class="col-md-6">
                            <div class="custom02">
                                <input checked type="radio" value="1" id="untuk1" name="untuk" /><label for="untuk1" >Order Untuk Hari Ini</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_besok_diet"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_besok_diet">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-6">
                            <div class="custom02">
                                <input type="radio" value="2" id="untuk2" name="untuk" /><label for="untuk2" >Order Untuk Besok</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-6">
                            <div class="custom02">
                                <input type="radio" value="12" id="untuk3" name="untuk" /><label for="untuk3" >Order Untuk Hari Ini dan Besok</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_diet"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_diet"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-diet-edit">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Edit Order Gizi</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_edit_diet">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_edit_diet"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 10px">Bentuk Diet</label>
                        <div class="col-md-7">
                            <s:action id="comboDiet0" namespace="/rawatinap"
                                      name="getComboBoxDietGizi_rawatinap"/>
                            <s:select list="#comboDiet0.listOfDietGizi" listKey="idDietGizi" listValue="namaDietGizi" id="edit_bentuk_diet"
                                      onchange="var warn =$('#war_edit_bentuk_diet').is(':visible'); if (warn){$('#cor_edit_bentuk_diet').show().fadeOut(3000);$('#war_edit_bentuk_diet').hide()}"
                                      headerKey="" headerValue=" - " cssClass="form-control select2" cssStyle="width: 100%"/>

                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_edit_bentuk_diet"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_edit_bentuk_diet">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 10px">Jenis Diet</label>
                        <div class="col-md-7">
                            <select id="edit_jenis_diet" class="form-control select2" multiple style="width: 100%"
                                    onchange="var warn =$('#war_edit_jenis_diet').is(':visible'); if (warn){$('#cor_edit_jenis_diet').show().fadeOut(3000);$('#war_edit_jenis_diet').hide()}">

                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_edit_jenis_diet"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_edit_jenis_diet">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak">
                    <div class="form-group">
                        <label class="col-md-3">Keterangan</label>
                        <div class="col-md-7">
                            <input class="form-control" id="edit_keterangan" readonly>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="edit_save_diet"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="edit_load_diet"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hasil_lab">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-image"></i> <span
                        id="title_hasil_lab"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div id="carousel-hasil_lab" class="carousel slide">
                                <ol class="carousel-indicators" id="li_hasil_lab">

                                </ol>
                                <div class="carousel-inner" id="item_hasil_lab">

                                </div>
                                <a class="left carousel-control" href="#carousel-hasil_lab" data-slide="prev">
                                    <span class="fa fa-angle-left"></span>
                                </a>
                                <a class="right carousel-control" href="#carousel-hasil_lab" data-slide="next">
                                    <span class="fa fa-angle-right"></span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-upload_pemeriksaan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-image"></i> Upload Pemeriksaan</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_pemeriksaan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_warning_pemeriksaan"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_pemeriksaan">
                    <h4><i class="icon fa fa-info"></i> Warning!</h4>
                    <p id="msg_success_pemeriksaan"></p>
                </div>
                <div class="box-body">
                    <div class="row" id="hidden_add">
                        <div class="col-md-2">
                            <div id="btn-uploded">
                                <button onclick="doneUplod()" class="btn btn-success"><i class="fa fa-cloud-upload"></i> Upload</button>
                            </div>
                        </div>
                        <div id="form-uploded" style="display: none">
                            <div class="col-md-3">
                                <input class="form-control" style="margin-top: 7px" placeholder="Keterangan" id="ket_upload_pemeriksan_0" oninput="$(this).css('border', '')">
                            </div>
                            <div class="col-md-7">
                                <div class="input-group">
                                <span class="input-group-btn">
                                    <span class="btn btn-default btn-file">
                                        Browse… <input accept="image/*" class="upload_pemeriksan" onchange="parseToByte('upload_pemeriksan_0', 'label_upload_pemeriksan_0', 'ket_upload_pemeriksan_0')" type="file" id="upload_pemeriksan_0">
                                    </span>
                                </span>
                                    <input type="text" class="form-control" readonly id="label_upload_pemeriksan_0" style="margin-top: 7px">
                                </div>
                                <span style="color: red; font-size: 12px">* format file upload (.jpg/.jpeg/.png)</span>
                            </div>
                            <%--<div class="col-md-1">--%>
                                <%--<button onclick="addUpload('upload_pemeriksan', 'set_upload_pemeriksan')" class="btn btn-success" style="margin-left: -20px; margin-top: 9px"><i class="fa fa-plus"></i></button>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <div id="set_upload_pemeriksan">

                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-12">
                            <div id="carousel-pemeriksaan" class="carousel slide">
                                <ol class="carousel-indicators" id="li_pemeriksaan">

                                </ol>
                                <div class="carousel-inner" id="item_pemeriksaan">

                                </div>
                                <a class="left carousel-control" href="#carousel-pemeriksaan" data-slide="prev">
                                    <span class="fa fa-angle-left"></span>
                                </a>
                                <a class="right carousel-control" href="#carousel-pemeriksaan" data-slide="next">
                                    <span class="fa fa-angle-right"></span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-input_resume">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Inputan Data Resume Medis</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_input_resume">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_input_resume"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <span>Penunjang Lab</span>
                            <textarea class="form-control" rows="3" id="p1"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <span>Penunjang Radiologi</span>
                            <textarea class="form-control" rows="3" id="p2"></textarea>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>TTD Keluarga</label>
                                <canvas class="paint-canvas-ttd" id="ttd_keluarga" width="220"
                                        onmouseover="paintTtd('ttd_keluarga')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_keluarga')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6 text-center">
                                <label>TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="ttd_penjaga" width="220"
                                        onmouseover="paintTtd('ttd_penjaga')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_penjaga')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_resume"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_resume"><i
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
                <h4 class="text-center">Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-check"></i> Yes            </button>
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
                <button type="button" class="btn btn-sm btn-default" id="save_con_rm"><i class="fa fa-check"></i> Ya</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-keterangan">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Signa</h4>
            </div>
            <div class="modal-body">
                <div id="body-keterangan-obat"></div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" onclick="saveKeteranganObat()"><i class="fa fa-check"></i> Save
                </button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-list-penunjang">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> List Pemeriksaan</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_list-penunjang">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_list-penunjang"></p>
                </div>
                <table class="table" style="font-size: 13px;" width="100%">
                    <tbody id="body-list-penunjang">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" onclick="saveListParam()"><i class="fa fa-check"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<%@ include file="/pages/modal/modalRingkasanRawatJalan.jsp" %>

<div class="mask"></div>

<script type='text/javascript' src='<s:url value="/dwr/interface/FisioterapiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/HemodialisaAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/RekamMedisRawatJalanAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/MonitoringTransfusiDarahAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenSpesialisAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/KeperawatanRawatJalanAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/CatatanTerintegrasiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/TindakanAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenRawatInapAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenUgdAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenOperasiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/MppAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenIcuAction.js"/>'></script>

<script type='text/javascript' src='<s:url value="/pages/dist/js/datapasien.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/fisioterapi.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/hd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/rj.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/addrawatjalan.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/spesialis.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/nyeri.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenrawatjalan.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/resikojatuh.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/cppt.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/tindakan_medis.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenrawatinap.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/allhistory.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenUgd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/operasi.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/mpp.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/icu.js"/>'></script>

<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli = $('#id_palayanan').val();
    var idPasien = $('#id_pasien').val();
    var noCheckup = $('#no_checkup').val();
    var jenisPeriksa = $('#jenis_periksa').val();
    var jenisPeriksaPasien = $('#jenis_pasien').val();

    var isReadRM = false;
    var contextPath = '<%= request.getContextPath() %>';
    var tglLhr = $('#h_tgl_lahir').val();
    var tglLahir = tglLhr.split("-").reverse().join("-");
    var namaPasien = $('#h_nama_pasien').val();
    var anamnese = $('#h_anamnesa').val();
    var penunjangMedis = $('#h_penunjang_medis').val();
    var keluhanUtama = $('#h_keluhan_utama').val();
    var suhu = $('#h_suhu').val();
    var nadi = $('#h_nadi').val();
    var tensi = $('#h_tensi').val();
    var pernafasan = $('#h_pernafasan').val();
    var alergi = $('#h_alergi').val();
    var beratBadan = $('#h_berat_badan').val();
    var tinggiBadan = $('#h_tinggi_badan').val();
    var diagnosa = $('#h_diagnosa').val();
    var umur = $('#h_umur').val();
    var alamatLengkap = $('#h_alamat_lengkap').val();
    var noBpjs = $('#h_no_bpjs').val();
    var jenisKelamin = $('#h_jenis_kelamin').val();
    var tipePelayanan = $('#h_tipe_pelayanan').val();
    var urlPage = 'checkupdetail';
    var tempTensi = "";
    var tempSuhu = "";
    var tempNadi = "";
    var tempRr = "";
    var tempBerat = "";
    var tempTinggi = "";
    var tempAnmnesa = "";
    var tempidRm = "";
    var jenisTrans = 'rawat_jalan';
    var jenisPelayanan = $('#h_jenis_pelayanan').val();
    var NOSEP = $('#h_no_sep').val();
    var IdAsuransi = $('#h_id_asuransi').val();
    var isBpjsRekanan = "";
    var isLanjutPaket = false;
    var isLaka = $('#h_is_laka').val();
    var noRujukan = $('#h_no_rujukan').val();
    var tglRujukan = $('#h_tgl_rujukan').val();
    var suratRujukan = $('#h_surat_rujukan').val();
    var idKelasRuangan = "";
    var namaRuanganPasien = $('#h_nama_ruangan').val();
    var isEksekutif = $('#h_is_eksekutif').val();
    var flagVaksin = $('#h_flag_vaksin').val();
    var tanggalMasuk = new Date();
    var idRawatInap = "";
    var namaJenisPasien = $('#nama_jenis_pasien').val();
    var tempSpo2 = "";
    var tempNyeri = "";
    var tempJatuh = "";

    $(document).ready(function () {
        $('#rawat_jalan').addClass('active');
        listDokter();
        listTindakan();
        listDiagnosa();
        listLab();
        listResepPasien();
        listAlergi();
        hitungStatusBiaya();
        hitungBmi();
        listSelectTindakanKategori();
        listICD9();
        listDiet();

        $('#img_ktp').on('click', function (e) {
            e.preventDefault();
            var src = $('#img_ktp').attr('src');

            if (src != null && src != "") {
                $('.mask').html('<div class="img-box"><img src="' + src + '"><a class="close">&times;</a>');

                $('.mask').addClass('is-visible fadein').on('animationend', function () {
                    $(this).removeClass('fadein is-visible').addClass('is-visible');
                });

                $('.close').on('click', function () {
                    $(this).parents('.mask').addClass('fadeout').on('animationend', function () {
                        $(this).removeClass('fadeout is-visible')
                    });
                });
            }

        });

        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        var canvas = document.getElementById('temp_surat_rujuk');
        var ctx = canvas.getContext('2d');

        $('.btn-file :file').on('fileselect', function (event, label) {

            var input = $(this).parents('.input-group').find(':text'),
                log = label;

            if (input.length) {
                var warn =$('#war_laka_surat_polisi').is(':visible');
                if (warn){
                    $('#cor_laka_surat_polisi').show().fadeOut(3000);
                    $('#war_laka_surat_polisi').hide()
                }
                input.val(log);
                var reader = new FileReader();
                reader.onload = function(event){
                    var img = new Image();
                    img.onload = function(){
                        canvas.width = img.width;
                        canvas.height = img.height;
                        ctx.clearRect(0,0,canvas.width,canvas.height);
                        ctx.drawImage(img,0,0);
                    }
                    img.src = event.target.result;
                }
                reader.readAsDataURL(event.target.files[0]);
            } else {
                if (log) alert(log);
            }

        });

        if(jenisPeriksaPasien == 'paket_perusahaan' || jenisPeriksaPasien == 'paket_individu'){
            showDetailPaket();
        }

        setKeteranganPeriksa('keterangan');
        setTindakLanjut();

        $('.carousel').carousel({
            interval: false,
            ride: false,
            pause: false
        });
    });

    function loadModalRM(jenis, method, parameter, idRM, flag, flagHide, flagCheck) {
        var context = contextPath + '/pages/modal/modal-default.jsp';
        if (jenis != "") {
            context = contextPath + '/pages/modal/modal-'+jenis+'.jsp';
        }
        $('#modal-temp').load(context, function (res, status, xhr) {
            if(status == "success"){
                var func = new Function(method+'(\''+parameter+'\', \''+idRM+'\', \''+flag+'\', \''+flagHide+'\', \''+flagCheck+'\')');
                func();
            }
        });
    }

    function convertRp(id, val) {
        $('#'+id).val(formatRupiahAtas2(val));
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
