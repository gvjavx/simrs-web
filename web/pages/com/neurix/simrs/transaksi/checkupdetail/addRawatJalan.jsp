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

    </style>

    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TeamDokterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DiagnosaRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanResepAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanRawatICD9Action.js"/>'></script>

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

                                    <s:if test='headerDetailCheckup.idJenisPeriksaPasien == "bpjs" || headerDetailCheckup.idJenisPeriksaPasien == "ptpn"'>
                                        <tr>
                                            <td width="45%"><b>No SEP</b></td>
                                            <td style="vertical-align: middle;">
                                                <table>
                                                    <s:label cssClass="label label-success" name="headerDetailCheckup.noSep"></s:label>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <tr>
                                        <td><b>No RM</b></td>
                                        <td>
                                            <table><s:label
                                                    name="headerDetailCheckup.idPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
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
                                    <s:if test='headerDetailCheckup.metodePembayaran != null && headerDetailCheckup.metodePembayaran != ""'>
                                        <tr>
                                            <td><b>Metode Pembayaran</b></td>
                                            <td>
                                                <table>
                                                    <script>
                                                        var metode = '<s:property value="headerDetailCheckup.metodePembayaran"/>';
                                                        var met = metode.replace("_", " ");
                                                        var meto = convertSentenceCase(met);
                                                        document.write(meto);
                                                    </script>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center"
                                     class="card card-4 pull-right">
                                    <img border="2" id="img_ktp" src="<s:property value="headerDetailCheckup.urlKtp"/>"
                                         style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">
                                </div>
                                <%--<img border="2" class="card card-4 pull-right" src="<s:url value="/pages/images/ktp-tes.jpg"/>"--%>
                                <%--style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px;">--%>
                                <table class="table table-striped">
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label id="jenis_periksa"
                                                         name="headerDetailCheckup.jenisPeriksaPasien"></s:label>
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
                                        <td><b>Poli</b></td>
                                        <td>
                                            <table>
                                                <s:label name="headerDetailCheckup.namaPelayanan"></s:label></table>
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
                        <%--<div class="row">--%>
                            <%--<div class="form-group">--%>
                                <%--<div class="col-md-12">--%>
                                    <%--<a href="<%= request.getContextPath() %>/rekammedik/initRekamMedik_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=RJ" class="btn btn-primary pull-left"><i class="fa fa-user-plus"></i> E-Rekam Medik</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-stethoscope"></i> Anamnesa & Pemeriksaan Fisik</h3>
                            </div>
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-medkit"></i> Rekam Medis</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="alert alert-danger alert-dismissible" style="display: none" id="war_anamnese">
                                    <p id="msg_war"></p>
                                </div>
                                <div class="alert alert-success alert-dismissible" style="display: none" id="suc_anamnese">
                                    <p id="msg_suc"></p>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <s:textarea id="fisik_anamnesa" name="headerDetailCheckup.anamnese" cssClass="form-control" rows="4" placeholder="Keterangan Anamnese"></s:textarea>
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
                                    <div class="col-md-12">
                                        <button id="save_fisik" class="btn btn-success pull-right" onclick="saveAnamnese()"><i class="fa fa-check"></i> Save</button>
                                        <button style="display: none; cursor: no-drop; margin-top: 25px" type="button" class="btn btn-success" id="load_fisik"> <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="btn-group dropdown">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Asesmen
                                    </button>
                                    <button onclick="loadModalRM('<s:property value="headerDetailCheckup.kategoriPelayanan"/>');" type="button" class="btn btn-primary dropdown-toggle"
                                    <%--<button onclick="loadModalRM('hemodialisa');" type="button" class="btn btn-primary dropdown-toggle"--%>
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <%--id="asesmen_rj"--%>
                                    <ul class="dropdown-menu" role="menu">
                                        <s:if test='headerDetailCheckup.kategoriPelayanan == "hemodialisa"'>
                                            <li><a style="cursor: pointer" onclick="showModalHD('monitoring_hd')"><i class="fa fa-file-o"></i>Monitoring HD</a></li>
                                            <li><a style="cursor: pointer" onclick="showModalHD('perencanaan_hemodialisa')"><i class="fa fa-file-o"></i>Perencanaan HD</a></li>
                                            <li><a style="cursor: pointer" onclick="showModalHD('asesmen_hd')"><i class="fa fa-file-o"></i>Asesmen Awal HD</a></li>
                                            <li><a style="cursor: pointer" onclick="showModalHD('tranfusi_hd')"><i class="fa fa-file-o"></i>Tindakan Medis Transfusi Darah</a></li>
                                            <li><a style="cursor: pointer" onclick="showModalHD('catatan_tranfusi_darah')"><i class="fa fa-file-o"></i>Catatan Pemantauan Tranfusi Darah</a></li>
                                            <li><a style="cursor: pointer" onclick="showModalHD('persetujuan_hd')"><i class="fa fa-file-o"></i>Persetujuan HD</a></li>
                                            <li><a style="cursor: pointer" onclick="showModalHD('travelling')"><i class="fa fa-file-o"></i>Travelling Dialysis</a></li>
                                        </s:if>
                                        <s:elseif test='headerDetailCheckup.kategoriPelayanan == "fisioterapi"'>
                                            <li><a style="cursor: pointer" onclick="pengkajianFisioterapi('<s:property value="headerDetailCheckup.idDetailCheckup"/>')"><i class="fa fa-file-o"></i>Pengkajian Pasien Fisioterapi</a></li>
                                            <li><a style="cursor: pointer" onclick="addMonitoringFisioterapi('<s:property value="headerDetailCheckup.idDetailCheckup"/>')"><i class="fa fa-file-o"></i>Kunjungan Fisioterapi</a></li>
                                        </s:elseif>
                                        <s:else>
                                            <li><a style="cursor: pointer" onclick="showModalSPS('<s:property value="headerDetailCheckup.kategoriPelayanan"/>')"><i class="fa fa-file-o"></i><s:property value="headerDetailCheckup.asesmenLabel"/></a></li>
                                        </s:else>
                                        <li><a style="cursor: pointer" onclick="showModalRj('ringkasan_rj')"><i class="fa fa-file-o"></i>Profil Rekam Medis Rawat Jalan</a></li>
                                        <li><a style="cursor: pointer" onclick="printPernyataan('SP03')"><i class="fa fa-print"></i>Surat Penolakan Tindakan</a></li>
                                        <li><a style="cursor: pointer" onclick="printPernyataan('SP06')"><i class="fa fa-print"></i>Surat Pernyataan Non Bpjs / Asuransi</a></li>
                                        <li><a style="cursor: pointer" onclick="printPernyataan('SK01')"><i class="fa fa-print"></i>Surat Keterangan Dokter</a></li>
                                        <li><a style="cursor: pointer" onclick="printPernyataan('SK05')"><i class="fa fa-print"></i>Surat Rekomendasi DPJP</a></li>
                                        <li><a style="cursor: pointer" onclick="printPernyataan('SK03')"><i class="fa fa-print"></i>Surat Keterangan Sehat</a></li>
                                        <li><a style="cursor: pointer" onclick="printPernyataan('RI01')"><i class="fa fa-print"></i>Surat Permintaan Rawat Inap</a></li>
                                    </ul>
                                </div>
                                <%----%>
                                    <%--<div class="btn-group">--%>
                                        <%--<button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Hemodialisa--%>
                                        <%--</button>--%>
                                        <%--<button onclick="loadModalRM('hd')" type="button" class="btn btn-primary dropdown-toggle"--%>
                                                <%--data-toggle="dropdown" style="height: 34px">--%>
                                            <%--<span class="caret"></span>--%>
                                            <%--<span class="sr-only">Toggle Dropdown</span>--%>
                                        <%--</button>--%>
                                        <%--<ul class="dropdown-menu" role="menu">--%>
                                            <%--<li><a style="cursor: pointer" onclick="showModalHD('monitoring_hd')"><i class="fa fa-circle-o"></i>Monitoring HD</a></li>--%>
                                            <%--<li><a style="cursor: pointer" onclick="showModalHD('perencanaan_hemodialisa')"><i class="fa fa-circle-o"></i>Perencanaan HD</a></li>--%>
                                            <%--<li><a style="cursor: pointer" onclick="showModalHD('asesmen_hd')"><i class="fa fa-circle-o"></i>Asesmen Awal HD</a></li>--%>
                                            <%--<li><a style="cursor: pointer" onclick="showModalHD('tranfusi_hd')"><i class="fa fa-circle-o"></i>Tindakan Medis Transfusi Darah</a></li>--%>
                                            <%--<li><a style="cursor: pointer" onclick="showModalHD('catatan_tranfusi_darah')"><i class="fa fa-circle-o"></i>Catatan Pemantauan Tranfusi Darah</a></li>--%>
                                            <%--<li><a style="cursor: pointer" onclick="showModalHD('persetujuan_hd')"><i class="fa fa-circle-o"></i>Persetujuan HD</a></li>--%>
                                            <%--<li><a style="cursor: pointer" onclick="showModalHD('travelling')"><i class="fa fa-circle-o"></i>Travelling Dialysis</a></li>--%>
                                        <%--</ul>--%>
                                    <%--</div>--%>
                                <%----%>
                                    <%--<div class="btn-group">--%>
                                        <%--<button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Fisioterapi--%>
                                        <%--</button>--%>
                                        <%--<button onclick="loadModalRM('fisioterapi')" type="button" class="btn btn-primary dropdown-toggle"--%>
                                                <%--data-toggle="dropdown" style="height: 34px">--%>
                                            <%--<span class="caret"></span>--%>
                                            <%--<span class="sr-only">Toggle Dropdown</span>--%>
                                        <%--</button>--%>
                                        <%--<ul class="dropdown-menu" role="menu">--%>
                                            <%--<li><a style="cursor: pointer" onclick="pengkajianFisioterapi('<s:property value="headerDetailCheckup.idDetailCheckup"/>')"><i class="fa fa-circle-o"></i>Pengkajian Pasien Fisioterapi</a></li>--%>
                                            <%--<li><a style="cursor: pointer" onclick="addMonitoringFisioterapi('<s:property value="headerDetailCheckup.idDetailCheckup"/>')"><i class="fa fa-circle-o"></i>Kunjungan Fisioterapi</a></li>--%>
                                        <%--</ul>--%>
                                    <%--</div>--%>
                                    <%--<div class="btn-group">--%>
                                        <%--<button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Asesmen--%>
                                        <%--</button>--%>
                                        <%--<button onclick="loadModalRM('spesialis')" type="button" class="btn btn-primary dropdown-toggle"--%>
                                                <%--data-toggle="dropdown" style="height: 34px">--%>
                                            <%--<span class="caret"></span>--%>
                                            <%--<span class="sr-only">Toggle Dropdown</span>--%>
                                        <%--</button>--%>
                                        <%--<ul class="dropdown-menu" role="menu">--%>
                                            <%--<li><a style="cursor: pointer" onclick="showModalSPS('<s:property value="headerDetailCheckup.kategoriPelayanan"/>')"><i class="fa fa-circle-o"></i><s:property value="headerDetailCheckup.asesmenLabel"/></a></li>--%>
                                        <%--</ul>--%>
                                    <%--</div>--%>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border" id="pos_alergi">
                    </div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-user"></i> Tinggi & Berat Badan</h3>
                            </div>
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-heartbeat"></i> Hasil BMI (Body Mass Index)</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_penunjang">
                                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                    Silahkan cek kembali data inputan!
                                </div>
                                <div class="alert alert-success alert-dismissible" style="display: none" id="success_penunjang">
                                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                                    Data berhasil disimpan!
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Tinggi</label>
                                            <div class="input-group date">
                                                <s:textfield id="tinggi" name="headerDetailCheckup.tinggi"
                                                             cssClass="form-control" type="number"/>
                                                <div class="input-group-addon">
                                                    cm
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Berat</label>
                                            <div class="input-group date">
                                                <s:textfield id="berat" name="headerDetailCheckup.berat"
                                                             cssClass="form-control" type="number"/>
                                                <div class="input-group-addon">
                                                    Kg
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                                <button style="margin-top: 25px" id="save_penunjang" onclick="savePenunjangPasien()"
                                                        class="btn btn-success"><i
                                                        class="fa fa-check"></i>
                                                    Save
                                                </button>
                                                <button style="display: none; cursor: no-drop; margin-top: 25px" type="button"
                                                        class="btn btn-success"
                                                        id="load_penunjang">
                                                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                                                </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div style="padding-top: 15px">
                                    <div class="progress">
                                        <div id="bar_bmi"></div>
                                    </div>
                                </div>
                                <%--<small>--%>
                                    <%--<ul style="list-style-type: none;">--%>
                                        <%--<li style="float: left"><i class="fa fa-square" style="color: #5bc0de"></i> Kurus berat--%>
                                        <%--</li>--%>
                                        <%--<li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #337ab7"></i> Kurus ringan--%>
                                        <%--</li>--%>
                                        <%--<li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #5cb85c"></i> Normal </li>--%>
                                        <%--<li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #f0ad4e"></i> Gemuk ringan  </li>--%>
                                        <%--<li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #d9534f"></i> Gemuk berat    </li>--%>
                                    <%--</ul>--%>
                                <%--</small>--%>
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

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-user"></i> Alergi</h3>
                            </div>
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-user-md"></i> Dokter</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                        onclick="showModal(8)"><i class="fa fa-plus"></i> Tambah Alergi
                                </button>
                                <table class="table table-bordered table-striped">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>Alergi</td>
                                        <td>Jenis</td>
                                        <td align="center" width="20%">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body_alergi">
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-6">
                                <s:if test='headerDetailCheckup.idJenisPeriksaPasien != "paket_individu" && headerDetailCheckup.idJenisPeriksaPasien != "paket_perusahaan"'>
                                    <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                            onclick="showModal(1)"><i class="fa fa-plus"></i> Tambah Dokter
                                    </button>
                                </s:if>
                                <table class="table table-bordered table-striped">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>ID Dokter</td>
                                        <td>Nama</td>
                                        <%--<td>Spesialis</td>--%>
                                        <td align="center">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body_dokter">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <%--<div class="box-header with-border"></div>--%>
                    <%--<div class="box-header with-border">--%>
                    <%----%>
                    <%--</div>--%>
                    <%--<div class="box-body">--%>

                    <%--</div>--%>
                    <div class="box-header with-border" id="pos_nosa">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Diagnosa</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(3)"><i class="fa fa-plus"></i> Tambah Diagnosa
                        </button>
                        <table class="table table-bordered table-striped" id="tbl_diagnosa">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>ID Diagnosa</td>
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

                    <div id="status_asuransi" style="display: none">
                        <div class="box-header with-border">
                        </div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-line-chart"></i> Status Biaya Tindakan</h3>
                        </div>
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-offset-2 col-md-8">
                                    <h5>
                                        Cover Biaya Asuransi
                                        <small class="pull-right" style="margin-top: 7px">Rp. <span id="b_asuransi"></span>
                                        </small>
                                    </h5>
                                    <div class="progress">
                                        <div id="sts_cover_biaya_asuransi">
                                        </div>
                                    </div>
                                    <h5>
                                        Total Biaya Tindakan
                                        <small class="pull-right" style="margin-top: 7px">Rp. <span
                                                id="b_tindakan_asuransi"></span></small>
                                    </h5>
                                    <div class="progress">
                                        <div id="sts_biaya_tindakan_asuransi">
                                        </div>
                                    </div>
                                    <ul style="list-style-type: none">
                                        <li><i class="fa fa-square" style="color: #337ab7"></i> Total biaya cover Asuransi
                                        </li>
                                        <li><i class="fa fa-square" style="color: #5cb85c"></i> Total biaya tindakan <
                                            50% dari cover biaya Asuransi
                                        </li>
                                        <li><i class="fa fa-square" style="color: #f0ad4e"></i> Total biaya tindakan >
                                            50% dan < 70% dari cover biaya Asuransi
                                        </li>
                                        <li><i class="fa fa-square" style="color: #d9534f"></i> Total biaya tindakan >
                                            70% dari cover biaya Asuransi
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
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>ID ICD9</td>
                                <td>Nama ICD9</td>
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
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr bgcolor="#90ee90">
                                    <td>Tanggal</td>
                                    <td>Tindakan</td>
                                        <%--<td>Dokter</td>--%>
                                        <%--<td>Perawat</td>--%>
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
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr bgcolor="#90ee90">
                                    <td>Tanggal</td>
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
                        <%--</s:if>--%>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Order</td>
                                <td>Pemeriksaan</td>
                                <td>Status</td>
                                <td>Jenis Lab</td>
                                <td align="center" width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_lab">

                            </tbody>
                        </table>
                    </div>

                    <%--<div class="box-header with-border" id="pos_obat">--%>
                    <%--</div>--%>
                    <%--<div class="box-header with-border">--%>
                        <%--<h3 class="box-title"><i class="fa fa-plus-square"></i> Obat Penunjang</h3>--%>
                    <%--</div>--%>
                    <%--<div class="box-body">--%>
                        <%--<button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"--%>
                                <%--onclick="showModal(5)"><i class="fa fa-plus"></i> Obat Penunjang--%>
                        <%--</button>--%>
                        <%--<table class="table table-bordered table-striped">--%>
                            <%--<thead>--%>
                            <%--<tr bgcolor="#90ee90">--%>
                                <%--<td>Tanggal</td>--%>
                                <%--<td>ID Obat</td>--%>
                                <%--<td>Obat</td>--%>
                                <%--<td align="center">Qty</td>--%>
                                <%--<td>Jenis Satuan</td>--%>
                                <%--<td align="center">Action</td>--%>
                            <%--</tr>--%>
                            <%--</thead>--%>
                            <%--<tbody id="body_obat">--%>

                            <%--</tbody>--%>
                        <%--</table>--%>
                    <%--</div>--%>

                    <div class="box-header with-border" id="pos_rssep">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Order Resep Obat</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(7)"><i class="fa fa-plus"></i> Tambah Resep
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>ID Resep</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_resep">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_all">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_all_war"></p>
                        </div>
                        <div class="alert alert-success alert-dismissible" style="display: none" id="success_all">
                            <h4><i class="icon fa fa-info"></i> Info!</h4>
                            <p id="msg_all_suc"></p>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-4 text-center">
                                        <a class="btn btn-success" id="save_all" onclick="confirmSaveAllTindakan()"><i class="fa fa-check"></i> Save All Tindakan</a>
                                        <button style="display: none; cursor: no-drop;" type="button"
                                                class="btn btn-success" id="load_all"><i class="fa fa-spinner fa-spin"></i>
                                            Sedang Menyimpan...
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-navicon"></i> Keterangan (Jika sudah pulang / selesai pemeriksaan)</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ket">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            Silahkan cek kembali data inputan!
                        </div>
                        <div class="row">
                            <div class="col-md-offset-1 col-md-5">
                                <div class="form-group">
                                    <label class="col-md-5" style="margin-top: 10px">Keterangan</label>
                                    <div class="col-md-7">
                                        <select class="form-control select2" id="keterangan" style="width: 100%"
                                                onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()}; selectKeterangan(this.value)">
                                            <option value=''>[Select One]</option>
                                            <option value='selesai'>Selesai</option>
                                            <option value='pindah'>Pindah Poli Lain</option>
                                            <%--<option value='rujuk'>Rujuk Rawat Inap</option>--%>
                                            <option value='lanjut_biaya'>Lanjut Biaya</option>
                                            <option value='rujuk_rs_lain'>Rujuk RS Lain</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div id="form-selesai" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Catatan</label>
                                        <div class="col-md-8">
                                        <s:action id="initComboKet" namespace="/checkupdetail"
                                                  name="getListComboKeteranganKeluar_checkupdetail"/>
                                        <s:select list="#initComboKet.listOfKeterangan" id="ket_selesai"
                                                  name="headerCheckup.idPelayanan" listKey="keterangan"
                                                  listValue="keterangan" cssStyle="width: 100%"
                                                  onchange="var warn =$('#war_kolom-2').is(':visible'); if (warn){$('#col_kolom-2').show().fadeOut(3000);$('#war_kolom-2').hide()}; showFormCekup(this.value);"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                </div>
                                <div id="kamar" style="display: none;">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Kelas</label>
                                        <div class="col-md-8">
                                        <s:action id="initComboKelas" namespace="/checkupdetail"
                                                  name="getListComboKelasRuangan_checkupdetail"/>
                                        <s:select
                                                onchange="var warn =$('#war_kolom-2').is(':visible'); if (warn){$('#col_kolom-2').show().fadeOut(3000);$('#war_kolom-2').hide()}; listSelectRuangan(this)"
                                                list="#initComboKelas.listOfKelasRuangan" id="kelas_kamar"
                                                listKey="idKelasRuangan" cssStyle="width: 100%"
                                                listValue="namaKelasRuangan"
                                                headerKey="" headerValue="[Select one]"
                                                cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Kamar</label>
                                        <div class="col-md-8">
                                        <select class="form-control select2" id="kamar_detail" style="width: 100%"
                                                onchange="var warn =$('#war_kolom-3').is(':visible'); if (warn){$('#col_kolom-3').show().fadeOut(3000);$('#war_kolom-3').hide()}">
                                            <option value=''>[Select One]</option>
                                        </select>
                                        </div>
                                    </div>
                                </div>
                                <div id="pembayaran" style="display: none;">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Metode Bayar</label>
                                        <div class="col-md-8">
                                            <s:select
                                                    id="metode_bayar"
                                                    list="#{'tunai':'Tunai','non_tunai':'Non Tunai'}"
                                                    cssStyle="margin-top: 7px"
                                                    headerKey="" headerValue="[Select one]"
                                                    cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Uang Muka</label>
                                        <div class="col-md-8">
                                            <div class="input-group" style="margin-top: 7px">
                                                <div class="input-group-addon">
                                                    Rp.
                                                </div>
                                                <s:hidden id="uang_muka_val"></s:hidden>
                                                <s:textfield type="text" id="uang_muka" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="form-asuransi" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Nama Asuransi</label>
                                        <div class="col-md-8">
                                            <input style="margin-top: 7px" class="form-control" id="ri_nama_asuransi" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 7px">Cover Biaya</label>
                                        <div class="col-md-8">
                                            <div class="input-group" style="margin-top: 7px">
                                                <div class="input-group-addon">
                                                    Rp.
                                                </div>
                                                <s:hidden id="rj_cover_biaya_val"></s:hidden>
                                                <s:textfield type="text" id="rj_cover_biaya" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="form-poli" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Poli</label>
                                        <div class="col-md-8">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select
                                                list="#initComboPoli.listOfPelayanan" id="poli_lain"
                                                name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                listValue="namaPelayanan" cssStyle="width: 100%"
                                                onchange="var warn =$('#war_kolom-2').is(':visible'); if (warn){$('#col_kolom-2').show().fadeOut(3000);$('#war_kolom-2').hide()}; listDokterKeterangan(this)"
                                                headerKey="" headerValue="[Select one]"
                                                cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Dokter</label>
                                        <div class="col-md-8">
                                        <select id="list_dokter" class="form-control select2" style="width: 100%"
                                                onchange="var warn =$('#war_kolom-3').is(':visible'); if (warn){$('#col_kolom-3').show().fadeOut(3000);$('#war_kolom-3').hide()}">
                                            <option value=''>[Select One]</option>
                                        </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="display: none;" id="form-cekup">
                            <div class="col-md-offset-1 col-md-5">
                                <div class="form-group">
                                    <label class="col-md-5" style="margin-top: 10px">Tgl Checkup Ulang</label>
                                    <div class="col-md-7">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_cekup" cssClass="form-control datepicker2 datemask2"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-5 col-md-7">
                                        <div class="form-check jarak">
                                            <input onclick="isPemeriksaan(this.id)" type="checkbox" name="pemeriksaan_lab" id="pemeriksaan_lab" value="yes">
                                            <label for="pemeriksaan_lab"></label> Pemeriksaan Lab/Radiologi?
                                        </div>
                                    </div>
                                </div>
                                <div id="form-pemeriksaan" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-5" style="margin-top: 7px">Kategori</label>
                                        <div class="col-md-7">
                                            <s:action id="comboLab2" namespace="/kategorilab"
                                                      name="getListKategoriLab_kategorilab"/>
                                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                                      onchange="listSelectLab(this.value)"
                                                      list="#comboLab2.listOfKategoriLab" id="ckp_kategori"
                                                      listKey="idKategoriLab"
                                                      listValue="namaKategori"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-5" style="margin-top: 7px">Unit</label>
                                        <div class="col-md-7">
                                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="ckp_unit"
                                                    onchange="listSelectParameter(this.value);">
                                                <option value=''>[Select One]</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-5" style="margin-top: 7px">Parameter</label>
                                        <div class="col-md-7">
                                            <select class="form-control select2" multiple style="margin-top: 7px; width: 100%" id="ckp_parameter">
                                                <option value=''>[Select One]</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 10px">Catatan Checkup</label>
                                    <div class="col-md-8">
                                        <s:textarea cssClass="form-control" rows="9" id="cekup_ket"
                                                    style="margin-top: 7px"></s:textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-4 text-center">
                                        <a class="btn btn-warning" href="initForm_checkupdetail.action"><i class="fa fa-arrow-left"></i> Back</a>
                                        <%--<a class="btn btn-primary" onclick="printGelangPasien()"><i class="fa fa-print"></i> Print</a>--%>
                                        <a class="btn btn-success" id="save_ket" onclick="confirmSaveKeterangan()"><i class="fa fa-check"></i> Close</a>
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
                        <label class="col-md-3" style="margin-top: 7px">Alergi</label>
                        <div class="col-md-7">
                            <input class="form-control" id="alergi"
                                   oninput="var warn =$('#war_alergi').is(':visible'); if (warn){$('#cor_alergi').show().fadeOut(3000);$('#war_alergi').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_alergi">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_alergi"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak">
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
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_alergi"><i
                        class="fa fa-arrow-right"></i> Save
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Tambah Dokter</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_dokter">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
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
                        class="fa fa-arrow-right"></i> Save
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
                    Silahkan cek kembali data inputan dan jumlah harus lebih dari 0
                </div>
                <div class="row">
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
                        <%--<label style="margin-top: 7px"><s:property value="headerDetailCheckup.idJenisPeriksaPasien"/> </label>--%>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="tin_id_tindakan"
                                    onchange="var warn =$('#war_tindakan').is(':visible'); if (warn){$('#cor_tindakan').show().fadeOut(3000);$('#war_tindakan').hide()}">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_tindakan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-md-3" style="margin-top: 7px">Nama Perawat</label>--%>
                    <%--<div class="col-md-7">--%>
                    <%--<select class="form-control select2" style="margin-top: 7px; width: 100%"--%>
                    <%--id="tin_id_perawat"--%>
                    <%--onchange="var warn =$('#war_perawat').is(':visible'); if (warn){$('#cor_perawat').show().fadeOut(3000);$('#war_perawat').hide()}">--%>
                    <%--<option value="">[select one]</option>--%>
                    <%--<option value="1">Angel</option>--%>
                    <%--<option value="2">Anya</option>--%>
                    <%--<option value="3">Ayu</option>--%>
                    <%--</select>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2">--%>
                    <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_perawat">--%>
                    <%--<i class="fa fa-times"></i> required</p>--%>
                    <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="cor_perawat"><i class="fa fa-check"></i> correct</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <input type="number" min="1" class="form-control" style="margin-top: 7px" id="tin_qty"
                                   oninput="$(this).css('border','')" onchange="$(this).css('border','')" value="1">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_tindakan"><i
                        class="fa fa-arrow-right"></i> Save
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-stethoscope"></i> Tambah Diagnosa</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_diagnosa">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <%--<s:if test='headerDetailCheckup.idJenisPeriksaPasien == "bpjs" || headerDetailCheckup.idJenisPeriksaPasien == "ptpn"'>--%>
                        <div class="form-group">
                        <label class="col-md-3">Diagnosa</label>
                        <div class="col-md-7">
                        <s:textfield id="nosa_id_diagnosa" style="margin-top: 7px"
                                     name="headerCheckup.diagnosa"
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
                        <input type="hidden" id="val_jenis_diagnosa">
                        <div class="form-group">
                        <div class="col-md-offset-3 col-md-7">
                        <s:textarea rows="4" id="nosa_ket_diagnosa"
                                    cssStyle="margin-top: 7px" readonly="true"
                                    name="headerCheckup.namaDiagnosa"
                                    cssClass="form-control"></s:textarea>
                        </div>
                        </div>
                    <%--</s:if>--%>
                    <%--<s:else>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-md-3">Diagnosa</label>--%>
                            <%--<div class="col-md-7">--%>
                                <%--<s:action id="initComboDiagnosa" namespace="/checkupdetail"--%>
                                          <%--name="getListComboDiagnosa_checkupdetail"/>--%>
                                <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                          <%--onchange="var warn =$('#war_diagnosa').is(':visible'); if (warn){$('#cor_diagnosa').show().fadeOut(3000);$('#war_diagnosa').hide()}"--%>
                                          <%--list="#initComboDiagnosa.listOfComboDiagnosa" id="nosa_id_diagnosa"--%>
                                          <%--name="headerDetailCheckup.idPelayanan" listKey="idDiagnosa"--%>
                                          <%--listValue="descOfDiagnosa"--%>
                                          <%--headerKey="" headerValue="[Select one]"--%>
                                          <%--cssClass="form-control select2"/>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-2">--%>
                                <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                                   <%--id="war_diagnosa"><i class="fa fa-times"></i> required</p>--%>
                                <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                                   <%--id="cor_diagnosa"><i class="fa fa-check"></i> correct</p>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</s:else>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3">Jenis Diagnosa</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<select class="form-control select2" style="margin-top: 7px; width: 100%"--%>
                                    <%--id="nosa_jenis_diagnosa"--%>
                                    <%--onchange="var warn =$('#war_jenis_diagnosa').is(':visible'); if (warn){$('#cor_jenis_diagnosa').show().fadeOut(3000);$('#war_jenis_diagnosa').hide()}">--%>
                                <%--<option value="">[select one]</option>--%>
                                <%--<option value="0">Diagnosa Awal</option>--%>
                                <%--<option value="1">Diagnosa Akhir</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_jenis_diagnosa"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_jenis_diagnosa"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_diagnosa"><i
                        class="fa fa-arrow-right"></i> Save
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
                        <label class="col-md-3">ICD9</label>
                        <div class="col-md-7">
                            <s:textfield id="id_icd9" style="margin-top: 7px"
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
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_icd9">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-lab">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Penunjang Medis</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_lab">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
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
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_kategori_lab"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_kategori_lab"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Unit</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="lab_lab"
                                    onchange="var warn =$('#war_lab').is(':visible'); if (warn){$('#cor_lab').show().fadeOut(3000);$('#war_lab').hide()}; listSelectParameter(this.value);">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_lab"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_lab"><i
                                    class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <input type="hidden" id="jenis_lab">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Parameter</label>
                        <div class="col-md-7">
                            <select class="form-control select2" multiple style="margin-top: 7px; width: 100%"
                                    id="lab_parameter"
                                    onchange="var warn =$('#war_parameter').is(':visible'); if (warn){$('#cor_parameter').show().fadeOut(3000);$('#war_parameter').hide()}">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_parameter"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_parameter"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_lab"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_lab">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-obat">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Obat Penunjang</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="row">
                    <%--<div class="form-group" id="jenis_form">--%>
                    <%--<label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>--%>
                    <%--<div class="col-md-7">--%>
                    <%--<s:action id="initJenis" namespace="/jenisobat"--%>
                    <%--name="getListJenisObat_jenisobat"/>--%>
                    <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                    <%--list="#initJenis.listOfJenisObat" id="obat_jenis_obat"--%>
                    <%--listKey="idJenisObat"--%>
                    <%--listValue="namaJenisObat"--%>
                    <%--headerKey="" headerValue="[Select one]"--%>
                    <%--cssClass="form-control select2"/>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2">--%>
                    <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="war_jenis_obat"><i class="fa fa-times"></i> required</p>--%>
                    <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="cor_jenis_obat"><i class="fa fa-check"></i> correct</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group" id="nama_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:action id="initObatPoli" namespace="/obatpoli"
                                      name="getListObatPoli_obatpoli"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initObatPoli.listOfObatPoli" id="ob_id_obat"
                                      listKey="idObat + '|' + namaObat + '|' + qtyBox + '|' + qtyLembar + '|' + qtyBiji + '|' + lembarPerBox + '|' + bijiPerLembar"
                                      listValue="namaObat"
                                      onchange="var warn =$('#war_obat').is(':visible'); if (warn){$('#cor_obat').show().fadeOut(3000);$('#war_obat').hide()}; setStokObat(this);"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                            <%--<select class="form-control select2" style="margin-top: 7px; width: 100%" id="ob_id_obat"--%>
                            <%--onchange="var warn =$('#war_obat').is(':visible'); if (warn){$('#cor_obat').show().fadeOut(3000);$('#war_obat').hide()}; setStokObat(this);">--%>
                            <%--<option value="">[select one]</option>--%>
                            <%--</select>--%>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_obat"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_obat">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group" id="nama_obat_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="nama_obat"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat</label>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Box</label>
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         id="ob_stok_box"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Lembar</label>
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         id="ob_stok_lembar"></s:textfield>
                        </div>
                        <div class="col-md-3">
                            <label style="margin-top: 7px">Biji</label>
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         id="ob_stok_biji"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>
                        <div class="col-md-7">
                            <s:select list="#{'box':'Box','lembar':'Lembar','biji':'Biji'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn = $('#war_ob_jenis_satuan').is(':visible'); if (warn){$('#cor_ob_jenis_satuan').show().fadeOut(3000);$('#war_ob_jenis_satuan').hide()}"
                                      id="ob_jenis_satuan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ob_jenis_satuan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ob_jenis_satuan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="ob_qty"
                                         onkeypress="var warn =$('#war_qty_obat').is(':visible'); if (warn){$('#cor_qty_obat').show().fadeOut(3000);$('#war_qty_obat').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_qty_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_qty_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="set_id_obat">
            <input type="hidden" id="set_lembar_perbox">
            <input type="hidden" id="set_biji_perlembar">
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-resep-head">
    <div class="modal-dialog modal-flat" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Resep Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_resep_head">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_resep"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Apotek</label>
                        <div class="col-md-7">
                            <s:action id="initApotek" namespace="/checkup"
                                      name="getComboApotek_checkup"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initApotek.listOfApotek" id="resep_apotek"
                                      listKey="idPelayanan + '|' + namaPelayanan"
                                      listValue="namaPelayanan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_apotek"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_apotek"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="resep_nama_obat">
                                <option value="">[select one]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_obat"><i class="fa fa-check"></i> correct</p>
                            <p style="margin-top: 17px; display: none; margin-left: -20px" id="label-kronis"><label class="label label-warning" >Obat Kronis</label></p>
                            <input type="hidden" id="val-kronis"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat (Biji)</label>
                        <%--<div class="col-md-2">--%>
                        <%--<label style="margin-top: 7px">Box</label>--%>
                        <%--<input class="form-control" type="number" min="1" id="resep_stok_box"--%>
                        <%--readonly>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                        <%--<label style="margin-top: 7px">Lembar</label>--%>
                        <%--<input class="form-control" type="number" min="1" id="resep_stok_lembar"--%>
                        <%--readonly>--%>
                        <%--</div>--%>
                        <div class="col-md-7">
                            <%--<label style="margin-top: 7px">Stok (Biji)</label>--%>
                            <div class="input-group" style="margin-top: 7px; width: 40%">
                                <input class="form-control" type="number" min="1" id="resep_stok_biji" readonly>
                                <div class="input-group-addon">
                                    Biji
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="h-qty-default"/>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>
                        <div class="col-md-7">
                            <s:select list="#{'lembar':'Lembar','box':'Box'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn = $('#war_rep_jenis_satuan').is(':visible'); if (warn){$('#cor_rep_jenis_satuan').show().fadeOut(3000);$('#war_rep_jenis_satuan').hide()};defaultValByJenisSatuan(this.value)"
                                      id="resep_jenis_satuan"
                                      headerKey="biji" headerValue="Biji"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_jenis_satuan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_jenis_satuan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Pemberian</label>
                        <div class="col-md-7">
                            <s:select list="#{'sebelum':'Sebelum'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn = $('#war_rep_jenis_satuan').is(':visible'); if (warn){$('#cor_rep_jenis_satuan').show().fadeOut(3000);$('#war_rep_jenis_satuan').hide()}"
                                      id="resep_waktu"
                                      headerKey="Sesudah" headerValue="Sesudah"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_waktu"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_waktu"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Waktu Minum</label>
                        <div class="col-md-7">
                            <div class="form-check" style="margin-top: 7px;">
                                <input type="checkbox" name="cek_waktu" id="pagi" value="Pagi" onclick="var warn = $('#war_rep_cek_waktu').is(':visible'); if (warn){$('#cor_rep_cek_waktu').show().fadeOut(3000);$('#war_rep_cek_waktu').hide()}">
                                <label for="pagi"></label> Pagi
                            </div>
                            <div class="form-check" style="margin-top: 7px; margin-left: 10px">
                                <input type="checkbox" name="cek_waktu" id="siang" value="Siang" onclick="var warn = $('#war_rep_cek_waktu').is(':visible'); if (warn){$('#cor_rep_cek_waktu').show().fadeOut(3000);$('#war_rep_cek_waktu').hide()}">
                                <label for="siang"></label> Siang
                            </div>
                            <div class="form-check" style="margin-top: 7px; margin-left: 10px">
                                <input type="checkbox" name="cek_waktu" id="malam" value="Malam" onclick="var warn = $('#war_rep_cek_waktu').is(':visible'); if (warn){$('#cor_rep_cek_waktu').show().fadeOut(3000);$('#war_rep_cek_waktu').hide()}">
                                <label for="malam"></label> Malam
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_cek_waktu"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_cek_waktu"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_qty').show().fadeOut(3000);$('#war_rep_qty').hide()}"
                                   style="margin-top: 7px; width: 40%" value="1" class="form-control" type="number" min="1"
                                   id="resep_qty">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_qty"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_qty"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>

                    <div class="form-group" id="form-hari" style="display: none;">
                        <label class="col-md-3" style="margin-top: 7px; font-size:12px">Pengambilan Berikutnya(Hari)</label>
                        <div class="col-md-7">
                            <input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_hari').show().fadeOut(3000);$('#war_rep_hari').hide()}"
                                   style="margin-top: 7px; width: 40%;" value="7" class="form-control" type="number" min="1"
                                   id="hari-kronis">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_hari"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_hari"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Obat Racik</label>
                        <div class="col-md-7">

                            <select id="jenis_resep" class="form form-control" style="margin-top: 7px;width: 40%"
                                    onchange="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_racik').show().fadeOut(3000);$('#war_rep_racik').hide()}">
                                <option value=""> Tidak </option>
                                <option value="Y">Ya</option>
                            </select>
                            <%--<input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_qty').show().fadeOut(3000);$('#war_rep_qty').hide()}"--%>
                            <%--style="margin-top: 7px" value="1" class="form-control" type="number" min="1"--%>
                            <%--id="resep_qty">--%>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_racik"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_racik"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group" id="form-jenis-resep">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Resep</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px;width: 40%" id="select-jenis-resep">

                            </select>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-md-3" style="margin-top: 7px">Keterangan</label>--%>
                    <%--<div class="col-md-7">--%>
                    <%--<textarea id="resep_keterangan" rows="4" class="form-control" style="margin-top: 7px"--%>
                    <%--oninput="var warn =$('#war_rep_ket').is(':visible'); if (warn){$('#cor_rep_ket').show().fadeOut(3000);$('#war_rep_ket').hide()}"></textarea>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2">--%>
                    <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="war_rep_ket"><i class="fa fa-times"></i> required</p>--%>
                    <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="cor_rep_ket"><i class="fa fa-check"></i> correct</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px"></label>
                        <div class="col-md-7">
                            <button class="btn btn-danger pull-right" style="margin-top: 7px" onclick="resetAll()"><i
                                    class="fa fa-refresh"></i> Reset
                            </button>
                            <button class="btn btn-success pull-right" style="margin-top: 7px; margin-right: 4px"
                                    onclick="addObatToList()"><i class="fa fa-plus"></i> Tambah
                            </button>
                        </div>
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
                        <td>ID</td>
                        <td>Obat</td>
                        <td align="center">Qty</td>
                        <td align="center">Jenis Satuan</td>
                        <td>Keterangan</td>
                        <td>Racik</td>
                        <td>Kronis</td>
                        <td>Pengambilan Berikutnya</td>
                        <td>Harga</td>
                        <td align="center" width="5%">Action</td>
                        </thead>
                        <tbody id="body_detail">
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-4">
                            <label>Total Harga</label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    Rp.
                                </div>
                                <input class="form-control" id="total_harga_obat" readonly>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_resep_head" onclick="saveResepObatTtd()"><i
                        class="fa fa-arrow-right"></i> Buat Resep
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_resep_head"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
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
                <%--<button type="button" class="btn btn-success" id="save_resep_detail" onclick="saveResepDetail()"><i--%>
                <%--class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_resep_detail"><i--%>
                <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Data Asuransi</h4>
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
                            <input class="form-control datepicker" style="margin-top: 7px" id="laka_tgl_kejadian"
                                   onchange="var warn =$('#war_laka_tgl_kejadian').is(':visible'); if (warn){$('#cor_laka_tgl_kejadian').show().fadeOut(3000);$('#war_laka_tgl_kejadian').hide()}">
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
                            <div class="input-group" style="margin-top: 7px" id="img_url">
                                  <span class="input-group-btn">
                                  <span class="btn btn-default btn-file">
                                  Browse… <s:file id="url_do" accept=".jpg"
                                  onchange="$('#img_file').css('border','')"></s:file>
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
                class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_laka"><i
                class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ttd">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-pencil"></i> Tanda Tangan Dokter
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
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
                <canvas class="js-paint  paint-canvas" id="ttd_canvas" width="550" height="300"></canvas>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-danger" onclick="clearConvas()"><i class="fa fa-pencil"></i> Clear
                </button>
                <button class="btn btn-success pull-right" onclick="saveResepObat()"><i class="fa fa-check"></i> Save</button>
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-rm">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h5 class="text-center">Do you want print this?</h5>
                <h5 class="text-center" id="print_form"></h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con_rm"><i class="fa fa-arrow-right"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-temp"></div>
<%@ include file="/pages/modal/modalRingkasanRawatJalan.jsp" %>

<div class="mask"></div>
<!-- /.content-wrapper -->

<script type='text/javascript' src='<s:url value="/dwr/interface/FisioterapiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/HemodialisaAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/RekamMedisRawatJalanAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/MonitoringTransfusiDarahAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenSpesialisAction.js"/>'></script>

<script type='text/javascript' src='<s:url value="/pages/dist/js/datapasien.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/fisioterapi.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/hd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/rj.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/addrawatjalan.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/spesialis.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/nyeri.js"/>'></script>

<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli = $('#id_palayanan').val();
    var idPasien = $('#id_pasien').val();
    var noCheckup = $('#no_checkup').val();
    var jenisPeriksa = $('#jenis_periksa').text();
    var jenisPeriksaPasien = $('#jenis_pasien').val();

    var isReadRM = false;
    var contextPath = '<%= request.getContextPath() %>';
    var tglLhr = '<s:property value="headerDetailCheckup.tglLahir"/>';
    var tglLahir = tglLhr.split("-").reverse().join("-");
    var namaPasien = '<s:property value="headerDetailCheckup.namaPasien"/>';
    var anamnese = '<s:property value="headerDetailCheckup.anamnese"/>';
    var penunjangMedis = '<s:property value="headerDetailCheckup.penunjangMedis"/>';
    var keluhanUtama = '<s:property value="headerDetailCheckup.keluhanUtama"/>';
    var suhu = '<s:property value="headerDetailCheckup.suhu"/>';
    var nadi = '<s:property value="headerDetailCheckup.nadi"/>';
    var tensi = '<s:property value="headerDetailCheckup.tensi"/>';
    var pernafasan = '<s:property value="headerDetailCheckup.pernafasan"/>';
    var alergi = '<s:property value="headerDetailCheckup.alergi"/>';
    var beratBadan = '<s:property value="headerDetailCheckup.berat"/>';
    var tinggiBadan = '<s:property value="headerDetailCheckup.tinggi"/>';
    var diagnosa = '<s:property value="headerDetailCheckup.namaDiagnosa"/>';
    var umur = '<s:property value="headerDetailCheckup.umur"/>';
    var alamatLengkap = '<s:property value="headerDetailCheckup.alamatLengkap"/>';
    var noBpjs = '<s:property value="headerDetailCheckup.noBpjs"/>';
    var jenisKelamin = '<s:property value="headerDetailCheckup.jenisKelamin"/>';
    var tipePelayanan = '<s:property value="headerDetailCheckup.kategoriPelayanan"/>';
    var urlPage = 'checkupdetail';
    var tempTensi = "";
    var tempSuhu = "";
    var tempNadi = "";
    var tempRr = "";
    var tempBerat = "";
    var tempTinggi = "";
    var tempAnmnesa = "";
    var tempidRm = "";

    $(document).ready(function () {
        $('#rawat_jalan').addClass('active');
        listDokter();
        listTindakan();
        listDiagnosa();
        listSelectDokter();
        listLab();
        listObat();
        listResepPasien();
        listAlergi();
        hitungStatusBiaya();
        hitungBmi();
        listSelectTindakanKategori();
        hitungCoverBiaya();
        listICD9();
        getListRekamMedis('rawat_jalan', tipePelayanan, idDetailCheckup);

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

        const paintCanvas = document.querySelector(".js-paint");
        const context = paintCanvas.getContext("2d");
        context.lineCap = "round";

        const colorPicker = document.querySelector(".js-color-picker");

        colorPicker.addEventListener("change", function (evt) {
            context.strokeStyle = evt.target.value;
        });

        const lineWidthRange = document.querySelector(".js-line-range");
        const lineWidthLabel = document.querySelector(".js-range-value");

        lineWidthRange.addEventListener("input", function (evt) {
            const width = evt.target.value;
            lineWidthLabel.innerHTML = width+" px";
            context.lineWidth = width;
        });

        let x = 0,
            y = 0;
        let isMouseDown = false;

        const stopDrawing = function () {
            isMouseDown = false;
        };

        const startDrawing = function (evt) {
            isMouseDown = true;
            [x, y] = [evt.offsetX, evt.offsetY];
        };

        const drawLine = function (evt) {
            if (isMouseDown) {
                const newX = evt.offsetX;
                const newY = evt.offsetY;
                context.beginPath();
                context.moveTo(x, y);
                context.lineTo(newX, newY);
                context.stroke();
                x = newX;
                y = newY;
            }
        };

        paintCanvas.addEventListener("mousedown", startDrawing);
        paintCanvas.addEventListener("mousemove", drawLine);
        paintCanvas.addEventListener("mouseup", stopDrawing);
        paintCanvas.addEventListener("mouseout", stopDrawing);

        var nominal = document.getElementById('uang_muka');
        if(nominal != ''){
            nominal.addEventListener('keyup', function (e) {
                nominal.value = formatRupiah2(this.value);
                var valBayar = nominal.value.replace(/[.]/g, '');

                if(valBayar != ''){
                    $('#uang_muka_val').val(valBayar);
                }else{
                    $('#uang_muka_val').val('');
                }
            });
        }

        var cover = document.getElementById('rj_cover_biaya');
        if(cover != ''){
            cover.addEventListener('keyup', function (e) {
                cover.value = formatRupiah2(this.value);
                var valCover = cover.value.replace(/[.]/g, '');

                if(valCover != ''){
                    $('#rj_cover_biaya_val').val(valCover);
                }else{
                    $('#rj_cover_biaya_val').val('');
                }
            });
        }

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
    });

    function loadModalRM(jenis) {
        var context = "";
        if (jenis != "") {
            context = contextPath + '/pages/modal/modal-'+jenis+'.jsp';
        }
        $('#modal-temp').load(context, function (res) {
        });
    }

    function printPernyataan(kode, idRm, flag, namaRm) {
        $('#print_form').text(namaRm);
        $('#save_con_rm').attr('onclick','printPernyataanRM(\''+kode+'\', \''+idRm+'\')');
        $('#modal-confirm-rm').modal('show');
    }

    function printPernyataanRM(kode, idRM){
        window.open(contextPath+'/rekammedik/printSuratPernyataan_rekammedik?id=' + idDetailCheckup + '&tipe=' + kode+'&ids='+idRM, '_blank');
        $('#modal-confirm-rm').modal('hide');
        $('#info_dialog').dialog('open');
        $('#close_pos').val(12);
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>