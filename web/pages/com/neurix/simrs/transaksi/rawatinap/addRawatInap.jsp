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
    <script type='text/javascript' src='<s:url value="/dwr/interface/OrderGiziAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanResepAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RawatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PlanKegiatanRawatAction.js"/>'></script>
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
        .btn{
            margin-top: 7px;
        }
        #line-chart-tooltip{
            z-index: 10000;
        }
        #line-chart{
            width: 100%;
        }
        #line-chart-rm{
            width: 100%;
        }

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

        .number-circle {
            border-radius: 0%;
            width: 50px;
            height: 25px;
            padding: 3px;
            background: #fff;
            border: 2px solid #666;
            color: #666;
            text-align: center;
            display: inline-block;
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
            <span id="title-pages"></span>
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
                                    <s:hidden id="no_checkup" name="rawatInap.noCheckup"/>
                                    <s:hidden id="id_palayanan" name="rawatInap.idPelayanan"/>
                                    <s:hidden id="no_detail_checkup" name="rawatInap.idDetailCheckup"/>
                                    <s:hidden id="id_rawat_inap" name="rawatInap.idRawatInap"/>
                                    <s:hidden id="id_pasien" name="rawatInap.idPasien"/>
                                    <s:hidden id="id_jenis_pasien" name="rawatInap.idJenisPeriksa"/>
                                    <s:hidden id="jenis_bayar" name="rawatInap.metodePembayaran"/>
                                    <s:hidden id="id_asuransi" name="rawatInap.idAsuransi"/>
                                    <s:hidden id="nama_asuransi" name="rawatInap.namaAsuransi"/>

                                    <s:hidden id="no_rujukan" name="rawatInap.noRujukan"/>
                                    <s:hidden id="tgl_rujukan" name="rawatInap.tglRujukan"/>
                                    <s:hidden id="surat_rujukan" name="rawatInap.suratRujukan"/>
                                    <s:hidden id="is_laka" name="rawatInap.isLaka"/>
                                    <s:hidden id="id_ruangan_lama" name="rawatInap.idRuangan"/>

                                    <s:if test='rawatInap.idJenisPeriksa == "bpjs" || rawatInap.idJenisPeriksa == "rekanan"'>
                                        <tr>
                                            <td width="45%"><b>No SEP</b></td>
                                            <td style="vertical-align: middle;">
                                                <table>
                                                    <span class="span-success">
                                                    <s:property value="rawatInap.noSep"></s:property>
                                                    </span>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <tr>
                                        <td width="45%"><b>No RM</b></td>
                                        <td>
                                            <table>
                                                <s:label name="rawatInap.idPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
                                        <td>
                                            <table>
                                                <s:label name="rawatInap.noCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.idDetailCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.nik"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.namaPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.jenisKelamin"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.tempatTglLahir"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Umur</b></td>
                                        <td>
                                            <table>
                                                <script>
                                                    var umur = '<s:property value="rawatInap.umur"/>';
                                                    var umurLengkap = umur+' Tahun';
                                                    document.write(umurLengkap);
                                                </script>
                                            </table>
                                        </td>
                                    </tr>
                                    <s:if test='rawatInap.idJenisPeriksa == "umum"'>
                                        <tr>
                                            <td>
                                                <button onclick="showUangMuka()" class="btn btn-primary"><i class="fa fa-money"></i> Uang Muka</button>
                                            </td>
                                        </tr>
                                    </s:if>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <script>
                                    document.write(imagesDefault('<s:property value="rawatInap.urlKtp"/>'));
                                </script>
                                <table class="table table-striped">
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <script>
                                                    document.write(changeJenisPasien('<s:property value="rawatInap.idJenisPeriksa"/>', '<s:property value="rawatInap.jenisPeriksaPasien"/>'));
                                                </script>
                                            </table>
                                        </td>
                                    </tr>
                                    <s:if test='rawatInap.idJenisPeriksa == "paket_perusahaan" || rawatInap.idJenisPeriksa == "paket_individu"'>
                                        <tr>
                                            <td><b>Tarif Paket</b></td>
                                            <td>
                                                <table>
                                                    <script>
                                                        var tar = '<s:property value="rawatInap.coverBiaya"/>';
                                                        if(tar != null){
                                                            document.write("Rp. "+formatRupiah(tar));
                                                        }
                                                    </script>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <s:if test='rawatInap.idJenisPeriksa == "asuransi"'>
                                        <tr>
                                            <td><b>Nama Asuransi</b></td>
                                            <td>
                                                <table>
                                                    <s:label id="nama_asuransi"
                                                             name="rawatInap.namaAsuransi"></s:label>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.desa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Ruangan</b></td>
                                        <td>
                                            <table>
                                                <span class="span-success">
                                                    <span id="no_ruang"></span> -
                                                    <span id="name_ruang"></span>
                                                </span>
                                            </table>
                                        </td>
                                    </tr>

                                    <%--<tr>--%>
                                        <%--<td></td>--%>
                                        <%--<td>--%>
                                            <%--<button class="btn btn-primary" onclick="viewDetailRekamMedic('<s:property value="rawatInap.noCheckup"></s:property>')"><i class="fa fa-search"></i> View Rekam Medic Saat Ini</button>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                </table>
                            </div>
                            <!-- /.col -->
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
                                    <s:hidden id="h_id_href"></s:hidden>
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
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border" id="pos_rm">
                        <div class="row">
                            <s:if test='rawatInap.kategoriRuangan == "rawat_inap"'>
                                <div class="col-md-6">
                                    <h3 class="box-title"><i class="fa fa-laptop"></i> Monitoring</h3>
                                </div>
                            </s:if>
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-medkit"></i> Rekam Medis</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                       <div class="row">
                           <s:if test='rawatInap.kategoriRuangan == "rawat_inap"'>
                               <div class="col-md-6">
                                   <button class="btn btn-primary" onclick="showModalPlan('<s:property value="rawatInap.idDetailCheckup"/>','','suster')">
                                       <i class="fa fa-calendar"></i> Schedule Rawat
                                   </button>
                                   <div class="btn-group dropdown">
                                       <button type="button" class="btn btn-info"><i class="fa fa-edit"></i> Observasi dan Pemberian
                                       </button>
                                       <button type="button" class="btn btn-info dropdown-toggle"
                                               data-toggle="dropdown" style="height: 34px">
                                           <span class="caret"></span>
                                           <span class="sr-only">Toggle Dropdown</span>
                                       </button>
                                       <ul class="dropdown-menu" role="menu">
                                           <li><a onclick="showModalCairan('<s:property value="rawatInap.idDetailCheckup"/>')" style="cursor: pointer"><i
                                                   class="fa fa-file-o"></i> Observasi Cairan</a></li>
                                           <li><a onclick="showModalMonVitalSign('<s:property value="rawatInap.idDetailCheckup"/>')" style="cursor: pointer"><i
                                                   class="fa fa-file-o"></i> Vital Sign</a></li>
                                           <li><a onclick="showModalPemberianObat('<s:property value="rawatInap.idDetailCheckup"/>','parenteral')" style="cursor: pointer"><i
                                                   class="fa fa-file-o"></i> Pemberian Obat Parenteral</a></li>
                                           <li><a onclick="showModalPemberianObat('<s:property value="rawatInap.idDetailCheckup"/>','nonparenteral')" style="cursor: pointer"><i
                                                   class="fa fa-file-o"></i> Pemberian Obat Non Parenteral</a></li>
                                       </ul>
                                   </div>
                               </div>
                           </s:if>
                           <div class="col-md-6">
                               <div class="btn-group dropdown">
                                   <button onclick="setRekamMedis()" type="button" class="btn btn-primary hvr-icon-down"><i class="fa fa-edit"></i> Asesmen
                                   </button>
                                   <button onclick="setRekamMedis()" type="button" class="btn btn-primary dropdown-toggle"
                                           data-toggle="dropdown" style="height: 34px">
                                       <span class="caret hvr-icon"></span>
                                       <span class="sr-only">Toggle Dropdown</span>
                                   </button>
                                   <ul class="dropdown-menu" role="menu" id="asesmen_ri">
                                   </ul>
                               </div>
                               <button type="button" id="btn_tranfer_pasien" onmouseenter="loadModalRM('transfer_pasien')" class="btn btn-info" onclick="showModalAsesmenRawatInap('transfer_pasien')"><i class="fa fa-file-o"></i> Serah Terima Pasien
                               </button>
                               <button type="button" onclick="viewHistory()" class="btn btn-info hvr-icon-spin"><i class="fa fa-history hvr-icon"></i> All History
                               </button>
                           </div>
                       </div>
                    </div>
                    <div class="box-header with-border" id="pos_dok">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user-md"></i> Dokter</h3>
                    </div>
                    <div class="box-body">
                        <a class="btn btn-success hvr-icon-spin" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(1)"><i class="fa fa-plus hvr-icon"></i> Request Dokter
                        </a>
                        <button class="btn btn-primary notification icon" style="margin-bottom: 10px; margin-left: 5px"
                                onclick="refreshTable('dok_ref', 'dokter')"><i class="fa fa-refresh" id="dok_ref"></i> Refresh
                            <span class="notification-number" style="display: none" id="notif_dok">new</span>
                        </button>
                        <table class="table table-bordered table-striped" id="tbl_dokter">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Dokter</td>
                                <td>Nama</td>
                                <td>Spesialis</td>
                                <td>Jenis DPJP</td>
                                <td align="center">Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_dokter">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border" id="pos_nosa">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Diagnosa</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline hvr-icon-spin" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(3)"><i class="fa fa-plus hvr-icon"></i> Tambah Diagnosa
                        </button>
                        <table class="table table-bordered table-striped">
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

                    <div class="box-header with-border" id="pos_icd9">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> ICD9</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline hvr-icon-spin" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(8)"><i class="fa fa-plus hvr-icon"></i> Tambah ICD9
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

                    <div class="box-header with-border" id="pos_tin">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Tindakan</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline hvr-icon-spin" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(2)"><i class="fa fa-plus hvr-icon"></i> Tambah Tindakan
                        </button>
                        <table class="table table-bordered table-striped" id="tabel_tindakan">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>Tindakan</td>
                                <td>Dokter</td>
                                <td align="center">Tarif (Rp.)</td>
                                <td align="center">Qty</td>
                                <td align="center">Total (Rp.)</td>
                                <td align="center">Action</td>
                                <input type="hidden" id="tin_id_dokter">
                            </tr>
                            </thead>
                            <tbody id="body_tindakan">
                            </tbody>
                        </table>
                    </div>

                    <s:if test='rawatInap.kategoriRuangan == "rawat_inap"'>
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Penunjang Medis</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline hvr-icon-spin" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(4)"><i class="fa fa-plus hvr-icon"></i> Penunjang Medis
                        </button>
                        <button class="btn btn-primary" style="margin-bottom: 10px;"
                                onclick="refreshTable('lab_ref', 'lab')"><i class="fa fa-refresh" id="lab_ref"></i> Refresh
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Order</td>
                                <td>Jenis Penunjang</td>
                                <td>Pemeriksaan</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_lab">

                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border" id="pos_diet">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Order Gizi</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline hvr-icon-spin" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(5)"><i class="fa fa-plus hvr-icon"></i> Order Gizi
                        </button>
                        <button class="btn btn-primary" style="margin-bottom: 10px;"
                                onclick="refreshTable('gizi_ref', 'gizi')"><i class="fa fa-refresh" id="gizi_ref"></i> Refresh
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90" style="height: 20px">
                                <td >Tanggal</td>
                                <td >ID Diet Gizi</td>
                                <td >Bentuk Diet</td>
                                <td >Keterangan</td>
                                <td align="center">Status</td>
                                <td align="center"width="18%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_diet">

                            </tbody>
                        </table>
                    </div>
                    </s:if>
                    <s:if test='rawatInap.kategoriRuangan == "rawat_inap"'>
                    <div class="box-header with-border" id="pos_ruangan">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Ruangan</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_update">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_update"></p>
                        </div>
                        <table class="table table-bordered table-striped" id="tabel_ruangan">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Masuk</td>
                                <td>Tanggal Keluar</td>
                                <td>Ruangan</td>
                                <td>Kelas</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_ruangan">

                            </tbody>
                        </table>
                    </div>
                    </s:if>
                    <div class="box-header with-border" id="pos_rssep">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Order Resep Obat</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline hvr-icon-spin" style="margin-bottom: 10px; width: 150px" onclick="showModal(7)"><i class="fa fa-plus hvr-icon"></i> Tambah Resep</button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
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
                        <div class="row">
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Instruksi Tindak Lanjut</label>
                                        <div class="col-md-8">
                                            <select class="form-control select2" id="keterangan" style="width: 100%"
                                                    onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()}; selectKeterangan(this.value)">
                                                <option value="">[Select One]</option>
                                                <s:if test='rawatInap.kategoriRuangan == "rawat_inap"'>
                                                    <s:if test='rawatInap.idJenisPeriksa == "umum" || rawatInap.idJenisPeriksa == "rekanan"'>
                                                        <option value="selesai">Selesai</option>
                                                        <option value="rawat_intensif">Rawat Intensif</option>
                                                        <option value="rawat_isolasi">Rawat Isolasi</option>
                                                        <option value="kamar_operasi">Kamar Operasi</option>
                                                        <s:if test='rawatInap.jenisKelamin == "Perempuan"'>
                                                            <option value="ruang_bersalin">Ruang Bersalin</option>
                                                        </s:if>
                                                        <option value="rujuk_rs_lain">Dirujuk</option>
                                                        <option value="kontrol_ulang">Kontrol Ulang</option>
                                                    </s:if>
                                                    <s:elseif test='rawatInap.idJenisPeriksa == "bpjs" || rawatInap.idJenisPeriksa == "asuransi"'>
                                                        <option value="selesai">Selesai</option>
                                                        <option value="rawat_intensif">Rawat Intensif</option>
                                                        <option value="rawat_isolasi">Rawat Isolasi</option>
                                                        <option value="kamar_operasi">Kamar Operasi</option>
                                                        <s:if test='rawatInap.jenisKelamin == "Perempuan"'>
                                                            <option value="ruang_bersalin">Ruang Bersalin</option>
                                                        </s:if>
                                                        <option value="rujuk_rs_lain">Dirujuk</option>
                                                        <option value="kontrol_ulang">Kontrol Ulang</option>
                                                        <option value="lanjut_biaya">Lanjut Biaya</option>
                                                    </s:elseif>
                                                    <s:else>
                                                        <option value="selesai">Selesai</option>
                                                        <option value="kontrol_ulang">Kontrol Ulang</option>
                                                    </s:else>
                                                </s:if>
                                                <s:elseif test='rawatInap.kategoriRuangan == "rawat_intensif"'>
                                                    <s:if test='rawatInap.isStay == "Y"'>
                                                        <option value="kembali_ke_inap">Kembali Ke Inap</option>
                                                    </s:if>
                                                    <s:else>
                                                        <option value="selesai">Selesai</option>
                                                        <option value="rawat_inap">Rawat Inap</option>
                                                    </s:else>
                                                    <option value="rawat_isolasi">Rawat Isolasi</option>
                                                    <option value="kamar_operasi">Kamar Operasi</option>
                                                    <s:if test='rawatInap.jenisKelamin == "Perempuan"'>
                                                        <option value="ruang_bersalin">Ruang Bersalin</option>
                                                    </s:if>
                                                    <%--<option value="rujuk_rs_lain">Dirujuk</option>--%>
                                                    <%--<option value="kontrol_ulang">Kontrol Ulang</option>--%>
                                                    <%--<option value="lanjut_biaya">Lanjut Biaya</option>--%>
                                                </s:elseif>
                                                <s:elseif test='rawatInap.kategoriRuangan == "rawat_isolasi"'>
                                                    <s:if test='rawatInap.isStay == "Y"'>
                                                        <option value="kembali_ke_inap">Kembali Ke Inap</option>
                                                    </s:if>
                                                    <s:else>
                                                        <option value="selesai">Selesai</option>
                                                        <option value="rawat_inap">Rawat Inap</option>
                                                    </s:else>
                                                    <option value="rawat_intensif">Rawat Intensif</option>
                                                    <option value="kamar_operasi">Kamar Operasi</option>
                                                    <s:if test='rawatInap.jenisKelamin == "Perempuan"'>
                                                        <option value="ruang_bersalin">Ruang Bersalin</option>
                                                    </s:if>
                                                    <%--<option value="rujuk_rs_lain">Dirujuk</option>--%>
                                                    <%--<option value="kontrol_ulang">Kontrol Ulang</option>--%>
                                                    <%--<option value="lanjut_biaya">Lanjut Biaya</option>--%>
                                                </s:elseif>
                                                <s:elseif test='rawatInap.kategoriRuangan == "kamar_operasi"'>
                                                    <s:if test='rawatInap.isStay == "Y"'>
                                                        <option value="kembali_ke_inap">Kembali Ke Inap</option>
                                                    </s:if>
                                                    <s:else>
                                                        <option value="selesai">Selesai</option>
                                                        <option value="rawat_inap">Rawat Inap</option>
                                                    </s:else>
                                                    <option value="rawat_intensif">Rawat Intensif</option>
                                                    <option value="rawat_isolasi">Rawat Isolasi</option>
                                                    <s:if test='rawatInap.jenisKelamin == "Perempuan"'>
                                                        <option value="ruang_bersalin">Ruang Bersalin</option>
                                                    </s:if>
                                                    <option value="rr">Recovery Room</option>
                                                </s:elseif>
                                                <s:elseif test='rawatInap.kategoriRuangan == "ruang_bersalin"'>
                                                    <s:if test='rawatInap.isStay == "Y"'>
                                                        <option value="kembali_ke_inap">Kembali Ke Inap</option>
                                                    </s:if>
                                                    <s:else>
                                                        <option value="selesai">Selesai</option>
                                                        <option value="rawat_inap">Rawat Inap</option>
                                                    </s:else>
                                                    <option value="rawat_intensif">Rawat Intensif</option>
                                                    <option value="rawat_isolasi">Rawat Isolasi</option>
                                                    <option value="kamar_operasi">Kamar Operasi</option>
                                                </s:elseif>
                                                <s:elseif test='rawatInap.kategoriRuangan == "rr"'>
                                                    <s:if test='rawatInap.isStay == "Y"'>
                                                        <option value="kembali_ke_inap">Kembali Ke Inap</option>
                                                    </s:if>
                                                    <s:else>
                                                        <option value="selesai">Selesai</option>
                                                        <option value="rawat_inap">Rawat Inap</option>
                                                    </s:else>
                                                    <option value="rawat_intensif">Rawat Intensif</option>
                                                    <option value="rawat_isolasi">Rawat Isolasi</option>
                                                    <option value="kamar_operasi">Kamar Operasi</option>
                                                </s:elseif>
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
                                                      listKey="keterangan"
                                                      listValue="keterangan" cssStyle="width: 100%"
                                                      onchange="var warn =$('#war_kolom-2').is(':visible'); if (warn){$('#col_kolom-2').show().fadeOut(3000);$('#war_kolom-2').hide()};"
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
                                <div class="row" style="display: none" id="form-kelas_kamar">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Kelas Kamar</label>
                                        <div class="col-md-8">
                                            <select class="form-control select2" id="kelas_kamar" style="width: 100%">
                                                <option value=''>[Select One]</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group" style="display: none" id="form-kamar">
                                        <label class="col-md-4" style="margin-top: 10px"><span id="label_kamar"></span></label>
                                        <div class="col-md-8">
                                            <select class="form-control select2" id="kamar" style="width: 100%">
                                                <option value=''>[Select One]</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="display: none" id="form-stay">
                                    <div class="form-group">
                                        <div class="col-md-offset-4 col-md-8">
                                            <div class="form-check jarak">
                                                <input type="checkbox" name="id" id="is_stay" value="Y">
                                                <label for="is_stay"></label> Tetap Diruangan ?
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
                                        <label class="col-md-4 jarak">Tanggal Kontrol Ulang</label>
                                        <div class="col-md-8">
                                            <div class="input-group jarak">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <s:textfield cssClass="form-control datepicker2 datemask2" id="tgl_kontrol"></s:textfield>
                                            </div>
                                        </div>
                                    </div>
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
                                                      onchange="listSelectLab(this.value)"
                                                      list="#comboLab2.listOfKategoriLab" id="ckp_kategori"
                                                      listKey="idKategoriLab"
                                                      listValue="namaKategori"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 7px">Unit</label>
                                        <div class="col-md-8">
                                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="ckp_unit"
                                                    onchange="listSelectParameter(this.value);">
                                                <option value=''>[Select One]</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 7px">Parameter</label>
                                        <div class="col-md-8">
                                            <select class="form-control select2" multiple style="margin-top: 7px; width: 100%" id="ckp_parameter">
                                                <option value=''>[Select One]</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" id="form-asesmen" style="display: none">
                                    <div class="form-group">
                                        <div class="col-md-offset-4 col-md-8">
                                            <button onmouseenter="loadModalRM('transfer_pasien')" class="btn btn-primary" onclick="showModalAsesmenRawatInap('transfer_pasien')">
                                                <i class="fa fa-file-o"></i> Asesmen Transfer Pasien
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row" id="form-catatan" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Catatan</label>
                                        <div class="col-md-8">
                                            <s:textarea cssClass="form-control jarak" id="pesan_dokter" rows="4" placeholder="Pesan untuk pasien"></s:textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="display: none" id="form-hak_kamar">
                                    <div class="col-md-12">
                                        <span style="background-color: #ec971f; color: white; border-radius: 5px; border: 1px solid black; padding: 5px;" id="hak_kamar_bpjs"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border" id="pos_selesai">
                    </div>
                    <div class="box-header with-border" >
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-4 text-center">
                                        <s:if test='rawatInap.kategoriRuangan == "rawat_inap"'>
                                            <a href="initForm_rawatinap.action" class="btn btn-warning hvr-icon-wobble-horizontal"
                                               style="margin-top: 15px;">
                                                <i
                                                        class="fa fa-arrow-left hvr-icon"></i> Back
                                            </a>
                                        </s:if>
                                        <s:elseif test='rawatInap.kategoriRuangan == "rawat_intensif"'>
                                            <a href="initForm_rawatintensif.action" class="btn btn-warning hvr-icon-wobble-horizontal"
                                               style="margin-top: 15px;">
                                                <i
                                                        class="fa fa-arrow-left hvr-icon"></i> Back
                                            </a>
                                        </s:elseif>
                                        <s:elseif test='rawatInap.kategoriRuangan == "kamar_operasi"'>
                                            <a href="initForm_rawatoperasi.action" class="btn btn-warning hvr-icon-wobble-horizontal"
                                               style="margin-top: 15px;">
                                                <i
                                                        class="fa fa-arrow-left hvr-icon"></i> Back
                                            </a>
                                        </s:elseif>
                                        <s:elseif test='rawatInap.kategoriRuangan == "rawat_isolasi"'>
                                            <a href="initForm_rawatisolasi.action" class="btn btn-warning hvr-icon-wobble-horizontal"
                                               style="margin-top: 15px;">
                                                <i
                                                        class="fa fa-arrow-left hvr-icon"></i> Back
                                            </a>
                                        </s:elseif>
                                        <s:elseif test='rawatInap.kategoriRuangan == "ruang_bersalin"'>
                                            <a href="initForm_rawatbersalin.action" class="btn btn-warning hvr-icon-wobble-horizontal"
                                               style="margin-top: 15px;">
                                                <i
                                                        class="fa fa-arrow-left hvr-icon"></i> Back
                                            </a>
                                        </s:elseif>
                                        <s:elseif test='rawatInap.kategoriRuangan == "rr"'>
                                            <a href="initForm_recoveryroom.action" class="btn btn-warning hvr-icon-wobble-horizontal"
                                               style="margin-top: 15px;">
                                                <i
                                                        class="fa fa-arrow-left hvr-icon"></i> Back
                                            </a>
                                        </s:elseif>
                                        <button class="btn btn-success hvr-icon-pulse-grow" onclick="confirmPemeriksaanPasien()"
                                                style="margin-top: 15px;" id="save_ket"><i
                                                class="fa fa-check hvr-icon"></i> Save
                                        </button>
                                        <button style="display: none; cursor: no-drop; margin-top: 15px;" type="button"
                                                class="btn btn-success" id="load_ket"><i class="fa fa-spinner fa-spin"></i>
                                            Sedang Menyimpan...
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-dokter">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Request Dokter</h4>
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
                                    onchange="var warn =$('#war_dok').is(':visible'); if (warn){$('#cor_dok').show().fadeOut(3000);$('#war_dok').hide()}; setSpesialis(this.value)">
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
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Spesialis</label>
                        <div class="col-md-7">
                           <input class="form-control" id="pelayanan_dokter" style="margin-top: 7px" disabled>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis DPJP</label>
                        <div class="col-md-7">
                            <select id="dok_jenis_dpjp" style="width: 100%" class="form-control select2"
                                    onchange="var warn =$('#war_dok_jenis_dpjp').is(':visible'); if (warn){$('#cor_dok_jenis_dpjp').show().fadeOut(3000);$('#war_dok_jenis_dpjp').hide()}">
                                <option value="">[Select One]</option>
                                <option value="konsultasi">Konsultasi</option>
                                <option value="rawat_bersama">Rawat Bersama</option>
                                <option value="rawat_ali">Rawat Alih</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_dok_jenis_dpjp"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_dok_jenis_dpjp"><i
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
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="tin_id_tindakan"
                                    onchange="var warn =$('#war_tindakan').is(':visible'); if (warn){$('#cor_tindakan').show().fadeOut(3000);$('#war_tindakan').hide()}; setDiskonHarga(this.value)">
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
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon</label>
                        <div class="col-md-7">
                            <input style="margin-top: 7px" class="form-control" readonly id="h_diskon">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Harga</label>
                        <div class="col-md-7">
                            <input style="margin-top: 7px" class="form-control" readonly id="h_harga">
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-stethoscope"></i> Tambah Diagnosa</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_diagnosa">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_diagnosa"></p>
                </div>
                <div class="row">
                    <%--<s:if test='rawatInap.idJenisPeriksa == "bpjs" || rawatInap.idJenisPeriksa == "rekanan"'>--%>
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-7">
                                <s:textfield id="nosa_id_diagnosa" style="margin-top: 7px"
                                             name="headerCheckup.diagnosa" autocomplete="off"
                                             onkeypress="var warn =$('#war_diagnosa_bpjs').is(':visible'); if (warn){$('#cor_diagnosa_bpjs').show().fadeOut(3000);$('#war_diagnosa_bpjs').hide()}; searchDiagnosa(this.id)"
                                             cssClass="form-control" required="false"/>
                                <s:hidden name="headerCheckup.jenisTransaksi"/>
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_diagnosa_bpjs"><i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_diagnosa_bpjs"><i class="fa fa-check"></i> correct</p>
                            </div>
                            <div class="form-group">
                            <div class="col-md-offset-3 col-md-7">
                                <s:textarea rows="4" id="nosa_ket_diagnosa"
                                            cssStyle="margin-top: 7px" readonly="true"
                                            name="headerCheckup.namaDiagnosa"
                                            cssClass="form-control"></s:textarea>
                            </div>
                            </div>
                        </div>
                    <div class="form-group">
                        <label class="col-md-3">Jenis Diagnosa</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="nosa_jenis_diagnosa"
                                    onchange="var warn =$('#war_jenis_diagnosa').is(':visible'); if (warn){$('#cor_jenis_diagnosa').show().fadeOut(3000);$('#war_jenis_diagnosa').hide()}">
                                <option value="">[select one]</option>
                                <option value="diagnosa_awal">Diagnosa Awal</option>
                                <option value="diagnosa_utama">Diagnosa Utama</option>
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
                        <label class="col-md-3">ICD9</label>
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
                    <p id="msg_lab"></pid>
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
                        <label class="col-md-3" style="margin-top: 7px">Paket</label>
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
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-7">
                            <div class="form-check jarak">
                                <input onclick="isPemeriksaan(this.id, 'form_pending')" type="checkbox" id="is_pending_lab" value="yes">
                                <label for="is_pending_lab"></label> Is Pending?
                            </div>
                        </div>
                    </div>
                    <div class="form-group" style="display: none" id="form_pending">
                        <label class="col-md-3">Tanggal Jam</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control tgl" id="tgl_pending" readonly style="cursor: pointer"
                                       onchange="var warn =$('#war_pending').is(':visible'); if (warn){$('#cor_pending').show().fadeOut(3000);$('#war_pending').hide()};">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-clock-o"></i>
                                </div>
                                <input class="form-control jam" id="jam_pending"
                                       onchange="var warn =$('#war_pending').is(':visible'); if (warn){$('#cor_pending').show().fadeOut(3000);$('#war_pending').hide()};">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_pending"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_pending"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div style="display: none" id="form_ttd">
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <span>Tanda Tangan</span>
                            </div>
                            <div class="col-md-7">
                                <canvas style="margin-left: 0px" class="paint-canvas-ttd" id="ttd_pengirim" width="310"
                                        onmouseover="paintTtd('ttd_pengirim')"></canvas>
                            </div>
                            <div class="col-md-2">
                                <button class="btn btn-danger" style="margin-left: -20px"
                                        onclick="removePaint('ttd_pengirim')"><i
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
                <button type="button" class="btn btn-success" id="save_lab"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_lab">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
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
                        <label class="col-md-3" style="margin-top: 10px">Bentuk Diet</label>
                        <div class="col-md-7">
                            <s:action id="comboDiet1" namespace="/rawatinap"
                                      name="getComboBoxDietGizi_rawatinap"/>
                            <s:select list="#comboDiet1.listOfDietGizi" listKey="idDietGizi" listValue="namaDietGizi" id="bentuk_diet"
                                      onchange="var warn =$('#war_bentuk_diet').is(':visible'); if (warn){$('#cor_bentuk_diet').show().fadeOut(3000);$('#war_bentuk_diet').hide()}"
                                      headerKey="" headerValue="[Select One]" cssClass="form-control select2" cssStyle="width: 100%"/>

                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_bentuk_diet"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_bentuk_diet">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
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
                <div class="row jarak">
                    <div class="form-group">
                        <label class="col-md-3">Keterangan</label>
                        <div class="col-md-7">
                            <div class="form-check">
                                <input type="checkbox" name="ket_diet" id="ket_diet1" value="pagi"
                                       onclick="var warn =$('#war_ket_diet').is(':visible'); if (warn){$('#cor_ket_diet').show().fadeOut(3000);$('#war_ket_diet').hide()}; setDiet(this.id)">
                                <label for="ket_diet1"></label> Pagi
                            </div>
                            <div class="form-check" style="margin-left: 10px">
                                <input type="checkbox" name="ket_diet" id="ket_diet2" value="siang"
                                       onclick="var warn =$('#war_ket_diet').is(':visible'); if (warn){$('#cor_ket_diet').show().fadeOut(3000);$('#war_ket_diet').hide()}; setDiet(this.id)">
                                <label for="ket_diet2"></label> Siang
                            </div>
                            <div class="form-check" style="margin-left: 10px">
                                <input type="checkbox" name="ket_diet" id="ket_diet3" value="malam"
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
                                <td>Bentuk</td>
                                <td>Jenis</td>
                            </tr>
                            </thead>
                            <tbody id="body_add_diet"></tbody>
                        </table>
                    </div>
                </div>
                <hr class="garis">
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4">Order Untuk Besok?</label>
                        <div class="col-md-6">
                            <div class="form-check">
                                <input type="checkbox" name="besok_diet" id="besok_diet" value="Y">
                                <label for="besok_diet"></label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_besok_diet"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_besok_diet">
                                <i class="fa fa-check"></i> correct</p>
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
                                      headerKey="" headerValue="[Select One]" cssClass="form-control select2" cssStyle="width: 100%"/>

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
                                      onchange="var warn = $('#war_ob_jenis_satuan').is(':visible'); if (warn){$('#cor_ob_jenis_satuan').show().fadeOut(3000);$('#war_ob_jenis_satuan').hide()};"
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
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ruangan">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Edit Ruangan</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ruangan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ruangan"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3 jarak">Tanggal & Jam</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control ptr-tgl" id="tanggal_pindah">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-clock-o"></i>
                                </div>
                                <input class="form-control ptr-jam" id="jam_pindah">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kelas Ruangan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="ruangan_kelas"
                                onchange="var warn =$('#war_ruangan_kelas').is(':visible'); if (warn){$('#cor_ruangan_kelas').show().fadeOut(3000);$('#war_ruangan_kelas').hide()}; listSelectRuangan(this.value)">
                                <option value="">[select one]</option>
                            </select>
                            <%--<s:action id="initKelas" namespace="/checkupdetail"--%>
                                      <%--name="getListComboKelasRuangan_checkupdetail"/>--%>
                            <%--<s:select--%>
                                    <%--onchange="var warn =$('#war_ruangan_kelas').is(':visible'); if (warn){$('#cor_ruangan_kelas').show().fadeOut(3000);$('#war_ruangan_kelas').hide()}; listSelectRuangan(this.value)"--%>
                                    <%--list="#initKelas.listOfKelasRuangan" id="ruangan_kelas"--%>
                                    <%--listKey="idKelasRuangan" cssStyle="width: 100%"--%>
                                    <%--listValue="namaKelasRuangan"--%>
                                    <%--headerKey="" headerValue="[Select one]"--%>
                                    <%--cssClass="form-control select2"/>--%>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ruangan_kelas"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ruangan_kelas"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <input type="hidden" id="h_id_rawat_inap">
                    <input type="hidden" id="h_id_kamar">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Ruangan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="ruangan_ruang"
                                    onchange="var warn =$('#war_ruangan_ruang').is(':visible'); if (warn){$('#cor_ruangan_ruang').show().fadeOut(3000);$('#war_ruangan_ruang').hide()}">
                                <option value="">[select one]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ruangan_ruang"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ruangan_ruang"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_ruang" onclick="saveEditRuang()"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_ruang"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-resep-head">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Resep Pasien</h4>
            </div>
            <div class="modal-body" id="temp_resep-head">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_resep_head">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_resep"></p>
                </div>
                <div class="row">
                    <label class="col-md-2" style="margin-top: 7px">Apotek</label>
                    <div class="col-md-4">
                        <s:action id="initApotek" namespace="/checkup"
                                  name="getComboApotekRi_checkup"/>
                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                  list="#initApotek.listOfApotek" id="resep_apotek"
                                  listKey="idPelayanan + '|' + namaPelayanan"
                                  listValue="namaPelayanan"
                                  headerKey="" headerValue="[Select one]"
                                  cssClass="form-control select2"/>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_apotek"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_apotek"><i class="fa fa-check"></i> correct</span>
                    </div>
                    <label class="col-md-2" style="margin-top: 7px">Kategori</label>
                    <div class="col-md-4">
                        <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                id="resep_jenis_obat">
                            <option value="">[select one]</option>
                        </select>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_jenis_obat"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_jenis_obat"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-2" style="margin-top: 7px">Nama Obat</label>
                    <div class="col-md-4">
                        <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                id="resep_nama_obat">
                            <option value="">[select one]</option>
                        </select>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_obat"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_obat"><i class="fa fa-check"></i> correct</span>
                        <span style="margin-top: 17px; display: none;" id="label-kronis"><label class="label label-warning" >Obat Kronis</label></span>
                        <button class="btn btn-sm btn-primary" style="display: none;" id="btn-reset-combo-obat" onclick="resetComboObat()"><i class="fa fa-edit"></i></button>
                        <input type="hidden" id="val-kronis"/>
                    </div>
                    <label class="col-md-2" style="margin-top: 7px">Stok Obat</label>
                    <div class="col-md-4">
                        <div class="input-group" style="margin-top: 7px;">
                            <input class="form-control" type="number" min="1" id="resep_stok_biji" readonly>
                            <div class="input-group-addon">
                                Biji
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="h-qty-default"/>
                </div>
                <div class="row">
                    <div id="obat-serupa" style="background-color: #fff4f0; height:100px; padding-top:5px; margin-top:5px; display: none">
                        <label class="col-md-12" style="color: black"><b class="blink_me">Obat Kandungan Serupa</b></label>
                        <input type="hidden" value="N" id="flag-obat-serupa">
                        <label class="col-md-2" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-4">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="resep_nama_obat_serupa">
                                <option value="">[select one]</option>
                            </select>
                            <span style="color: red; margin-top: 12px; display: none;"
                                  id="war_rep_obat_serupa"><i class="fa fa-times"></i> required</span>
                            <span style="color: green; margin-top: 12px; display: none;"
                                  id="cor_rep_obat_serupa"><i class="fa fa-check"></i> correct</span>
                            <span style="margin-top: 17px; display: none;" id="label-kronis-serupa"><label class="label label-warning" >Obat Kronis</label></span>
                        </div>
                        <label class="col-md-2" style="margin-top: 7px">Stok Obat</label>
                        <div class="col-md-4">
                            <div class="input-group" style="margin-top: 7px;">
                                <input class="form-control" type="number" min="1" id="resep_stok_biji_serupa" readonly>
                                <div class="input-group-addon">
                                    Biji
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <label class="col-md-2" style="margin-top: 7px">Jenis Satuan</label>
                    <div class="col-md-4">
                        <s:select list="#{'lembar':'Lembar','box':'Box'}"
                                  cssStyle="margin-top: 7px; width: 100%"
                                  onchange="var warn = $('#war_rep_jenis_satuan').is(':visible'); if (warn){$('#cor_rep_jenis_satuan').show().fadeOut(3000);$('#war_rep_jenis_satuan').hide()};defaultValByJenisSatuan(this.value)"
                                  id="resep_jenis_satuan"
                                  headerKey="biji" headerValue="Biji"
                                  cssClass="form-control select2"/>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_jenis_satuan"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_jenis_satuan"><i class="fa fa-check"></i> correct</span>
                    </div>
                    <label class="col-md-2" style="margin-top: 7px">Jumlah</label>
                    <div class="col-md-4">
                        <input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_qty').show().fadeOut(3000);$('#war_rep_qty').hide()}"
                               style="margin-top: 7px;" value="1" class="form-control" type="number" min="1"
                               id="resep_qty">
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_qty"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_qty"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-2" style="margin-top: 7px">Obat Racik ?</label>
                    <div class="col-md-4">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_racik" id="racik_racik" value="Y" onclick="var warn = $('#war_rep_racik').is(':visible'); if (warn){$('#cor_rep_racik').show().fadeOut(3000);$('#war_rep_racik').hide()}; cekRacik(this.id)">
                            <label for="racik_racik"></label> Ya
                        </div>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_racik"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_racik"><i class="fa fa-check"></i> correct</span>
                    </div>
                    <label class="col-md-2" style="margin-top: 7px">Jenis Resep</label>
                    <div class="col-md-4">
                        <select class="form-control" style="margin-top: 7px;" id="select-jenis-resep">
                        </select>
                    </div>
                </div>
                <div class="row" id="form-nama-racik" style="display: none">
                    <label class="col-md-2" style="margin-top: 7px;">Nama Racik</label>
                    <div class="col-md-4">
                        <div class="input-group" style="margin-top: 7px;">
                            <input oninput="var warn =$('#war_nama_racik').is(':visible'); if (warn){$('#cor_nama_racik').show().fadeOut(3000);$('#war_nama_racik').hide()}"
                                   class="form-control" type="text"
                                   id="nama_racik">
                            <div class="input-group-addon">
                                <input type="color" id="color_racik" style="height: 20px;">
                            </div>
                        </div>
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_nama_racik"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_nama_racik"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row" id="form-hari" style="display: none">
                    <label class="col-md-2" style="margin-top: 7px; font-size:12px">Pengambilan(Hari)</label>
                    <div class="col-md-4">
                        <input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_hari').show().fadeOut(3000);$('#war_rep_hari').hide()}"
                               style="margin-top: 7px; width: 40%;" value="7" class="form-control" type="number" min="1"
                               id="hari-kronis">
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_hari"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_hari"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <hr/>
                <%--Keterangan Obat Berdasarkan Jenis Obat--%>
                <%--Keterangan Obat Berdasarkan Jenis Obat--%>
                <div class="row" style="margin-top: -7px">
                    <div class="col-md-offset-2 col-md-8">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="w_keterangan">
                            <p id="p_keterangan"></p>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <label style="margin-bottom: -7px">Waktu</label>
                        <select class="form-control select2" style="width: 100%" id="waktu_param">
                            <option value="">[Select One]</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label style="margin-bottom: -7px">Parameter Keterangan</label>
                        <select onchange="getComboKeteranganObat(this.value)" class="form-control select2" style="width: 100%" id="param_ket">
                            <option value="">[Select One]</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label style="margin-bottom: -7px">Keterangan</label>
                        <select class="select2 form-control" multiple style="width: 100%" id="ket_param">
                        </select>
                    </div>
                    <div class="col-md-1">
                        <button style="margin-top: 20px; margin-left: -25px" class="btn btn-warning" onclick="addKeterangan()"><i class="fa fa-plus"></i></button>
                    </div>
                </div>
                <div class="row" style="margin-top: 10px">
                    <div class="col-md-offset-2 col-md-8">
                        <table class="table table-bordered" style="font-size: 14px" id="table_keterangan">
                            <thead>
                            <tr>
                                <td>Waktu</td>
                                <td>Keterangan</td>
                                <td align="center" width="5%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_keterangan">
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-2">
                        <span style="color: red; margin-top: 12px; display: none;"
                              id="war_rep_cek_waktu"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; margin-top: 12px; display: none;"
                              id="cor_rep_cek_waktu"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <hr/>
                <div class="row" style="margin-top: -10px">
                    <div class="col-md-offset-2 col-md-7">
                        <button class="btn btn-success" onclick="addObatToList()"><i class="fa fa-plus"></i> Tambah
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
                        <td>Keterangan</td>
                        <td align="center">Harga (Rp.)</td>
                        <td align="center" width="5%">Action</td>
                        </thead>
                        <tbody id="body_detail">
                        </tbody>
                    </table>
                </div>
                <div class="box-header with-border">
                </div>
                <div class="row" style="margin-top: 10px">
                    <div class="form-group">
                        <div class="col-md-4">
                            <label><b>Total Harga</b></label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    Rp.
                                </div>
                                <input class="form-control" id="total_harga_obat" readonly>
                            </div>
                        </div>
                        <div class="col-md-offset-2 col-md-2">
                            <button onclick="removePaint('ttd_canvas')" style="margin-left: 80px; margin-top: 21px" class="btn btn-danger"><i class="fa fa-trash"></i> Clear</button>
                        </div>
                        <div class="col-md-4">
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

<div class="modal fade" id="modal-resiko">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i>  <span id="label-skor"> </span></h4>
            </div>
            <div class="modal-body">
                <button type="button" style="margin-bottom: 10px" class="btn btn-success" id="add_resiko" onclick="addResiko('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')">
                    <i class="fa fa-plus"></i> Add
                </button>
                <table class="table table-bordered ">
                    <thead>
                    <td>Asesmen</td>
                    <td>Skor</td>
                    <td>Created Who</td>
                    <td>Created Date</td>
                    <td>Action</td>
                    </thead>
                    <tbody id="body-list-resiko">

                    </tbody>
                </table>
                <input type="hidden" id="kat_skor"/>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-resiko">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-add-resiko"> </span></h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <br>
                    <div id="body_resiko">
                    </div>
                    <input type="hidden" id="ind_resiko" class="form form-control"/>
                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_resiko">
                        <h4><i class="icon fa fa-info"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_resiko">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_resiko"></p>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_resiko" onclick="saveResiko('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_resiko"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-asesmen">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-asesmen"></span> </h4>
            </div>
            <div class="modal-body">
                <button type="button" style="margin-bottom: 10px" class="btn btn-success" id="add_asesmen" onclick="addAsesmen('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')">
                    <i class="fa fa-plus"></i> Add
                </button>
                <table class="table table-bordered">
                    <thead>
                    <td>Asesmen</td>
                    <td>Created Who</td>
                    <td>Created Date</td>
                    <td align="center" width="10%">Action</td>
                    </thead>
                    <tbody id="body-list-asesmen">

                    </tbody>
                </table>
                <input type="hidden" id="kat_asesmen"/>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-asesmen">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-add-asesmen"> </span></h4>
            </div>
            <div class="modal-body">
                <div id="body_asesmen">
                </div>
                <div style="color:#fc0303">note : kosongkan jika tidak perlu</div>
                <input type="hidden" id="ind_asesmen" class="form form-control"/>
                <br>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_asesmen">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p>Data Berhasil Tersimpan</p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_asesmen">
                    <h4><i class="icon fa fa-ban"></i> Error !</h4>
                    <p id="error_ket_asesmen"></p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveAsesmen('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-asesmen">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-view-asesmen"> </span></h4>
            </div>
            <div class="modal-body">
                <div id="head-view-asesmen"></div>
                <br>
                <table class="table table-bordered">
                    <tbody id="body-view-asesmen">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-skor">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-view-skor"> </span></h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div id="head-view-skor"></div>
                    <br>
                    <table class="table table-bordered">
                        <tbody id="body-view-skor">
                        </tbody>
                    </table>
                    <br>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-vital-sign">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-line-chart"></i> Chart Vital Sign </h4>
            </div>
            <div class="modal-body">
                <div class="box-body chart-responsive">
                    <div class="chart" id="line-chart_vital_sign" style="height: 300px; width: 100%"></div>
                    <hr class="garis">
                    <div class="row" style="font-size: 12px">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-1">
                                <i class="fa fa-circle" style="color: #ff0000"></i> RR
                            </div>
                            <div class="col-md-1">
                                <i class="fa fa-circle" style="color: #0000ff"></i> Nadi
                            </div>
                            <div class="col-md-2">
                                <i class="fa fa-circle" style="color: #00cc00"></i> Sistole
                            </div>
                            <div class="col-md-2">
                                <i class="fa fa-circle" style="color: #cc6699; margin-left: -70px"></i> Diastole
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

<div class="modal fade" id="modal-add-vital-sign">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi Vital Sign</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label>Created Date</label>
                        </div>
                        <div class="col-md-4">
                            <input type="date" name="" value="" class="date form-control" id="mvs_date"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label style="margin-top: 7px">Jam</label>
                        </div>
                        <div class="col-md-2">
                            <select style="margin-top: 7px" class="form-control" id="mvs_jam">
                                <option val='8'>8</option>
                                <option val='12'>12</option>
                                <option val='16'>16</option>
                                <option val='20'>20</option>
                            </select>
                        </div>
                    </div>
                </div>
                <hr style="color:#b0b0b0"/>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label>Nafas</label>
                            <select class="form-control" id="mvs_nafas">
                                <option val='10'>10</option>
                                <option val='20'>20</option>
                                <option val='30'>30</option>
                                <option val='40'>40</option>
                                <option val='50'>50</option>
                                <option val='60'>60</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label>Nadi</label>
                            <select class="form-control" id="mvs_nadi">
                                <option val='40'>40</option>
                                <option val='60'>60</option>
                                <option val='80'>80</option>
                                <option val='100'>100</option>
                                <option val='120'>120</option>
                                <option val='140'>140</option>
                                <option val='160'>160</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label>Suhu</label>
                            <select class="form-control" id="mvs_suhu">
                                <option val='36'>36</option>
                                <option val='37'>37</option>
                                <option val='38'>38</option>
                                <option val='39'>39</option>
                                <option val='40'>40</option>
                                <option val='41'>41</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label>Tensi</label>
                            <input type="number" name="" value="" class="form-control" id="mvs_tensi">
                        </div>
                    </div>
                </div>
                <hr style="color:#b0b0b0"/>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label>Tinggi badan</label>
                            <input type="number" name="" value="" class="form-control" placeholder="cm" id="mvs_tb">
                        </div>
                        <div class="col-md-3">
                            <label>Berat badan</label>
                            <input type="number" name="" value="" class="form-control" placeholder="Kg" id="mvs_bb">
                        </div>
                    </div>
                </div>
                <input type="hidden" id="ind_asesmen" class="form form-control"/>
                <br>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_asesmen">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p>Data Berhasil Tersimpan</p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_asesmen">
                    <h4><i class="icon fa fa-ban"></i> Error !</h4>
                    <p id="error_ket_asesmen"></p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveVitalSign('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-graf-vital-sign">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-pie-chart"></i> Graf Observasi Vital Sign</h4>
            </div>
            <div class="modal-body">
                <div class="box-body" style="padding:50px;">
                    <div id="line-chart" style="height: 300px"></div>
                    <br/>
                    <p style="margin-top:100px;"><i class="fa fa-circle" style="color:#3a4dc9"></i> Suhu  <i class="fa fa-circle" style="color:#eb4034"></i> Nadi  <i class="fa fa-circle" style="color:#6b6b6b"></i> Nafas</p>

                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-cairan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi Cairan </h4>
            </div>
            <div class="modal-body">
                <div style="margin-bottom:7px">
                    <button type="button" class="btn btn-success" onclick="addObCairan()">
                        <i class="fa fa-plus"></i> Add
                    </button>
                </div>
                <table class="table table-bordered" style="font-size:11px;">
                    <thead>
                    <td align="center" width="10%" style="vertical-align: middle">Tgl</td>
                    <td align="center" style="vertical-align: middle">Macam Cairan</td>
                    <td align="center" style="vertical-align: middle">Melalui</td>
                    <td align="center" style="vertical-align: middle"> Jumlah</td>
                    <td align="center" style="vertical-align: middle">Jam Mulai</td>
                    <td align="center" style="vertical-align: middle">Jam Selesai</td>
                    <td align="center" style="vertical-align: middle">Cek Tambahan Obat</td>
                    <td align="center" style="vertical-align: middle">Sisa</td>
                    <td align="center" style="vertical-align: middle">Jam Ukur Buang</td>
                    <td align="center" style="vertical-align: middle">Dari</td>
                    <td align="center" style="vertical-align: middle">Balance Cairan</td>
                    <td align="center" style="vertical-align: middle">Keterangan</td>
                    <td align="center" style="vertical-align: middle">Created Who</td>
                    </thead>
                    <tbody id="body-list-cairan">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-cairan">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi Cairan</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label>Created Date</label>
                        </div>
                        <div class="col-md-4">
                            <input type="date" name="" value="" class="date form-control" id="mcr_date">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Macam Cairan</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" style="margin-top: 7px" name="" value="" class="form-control" id="mcr_macam">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Melalui</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" style="margin-top: 7px" name="" value="" class="form-control" id="mcr_melalui">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Jumlah (dalam botol)</label>
                        </div>
                        <div class="col-md-4">
                            <input type="number" style="margin-top: 7px" name="" value="" class="form-control" id="mcr_jumlah">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Jam mulai</label>
                            <input type="text" name="" value="" class="time form-control" id="mcr_mulai">
                        </div>
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Jam selesai</label>
                            <input type="text" name="" value="" class="time form-control" id="mcr_selesai">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Cek tambahan obat</label>
                        </div>
                        <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" name="" id="mcr_cek">
                                <option value="Ya">Ya</option>
                                <option value="Tidak">Tidak</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Sisa</label>
                        </div>
                        <div class="col-md-4">
                            <input style="margin-top: 7px" type="number" name="" value="" class="form-control" id="mcr_sisa">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Jam ukur buang</label>
                        </div>
                        <div class="col-md-4">
                            <input style="margin-top: 7px" type="text" name="" value="" class="time form-control" id="mcr_buang">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Dari</label>
                        </div>
                        <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" id="mcr_dari">
                                <option val='Selang lambung'>Selang lambung</option>
                                <option val='Kandung kencing'>Kandung kencing</option>
                                <option val='Air seni biasa'>Air seni biasa</option>
                                <option val='Drainage'>Drainage</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Balance Cairan</label>
                        </div>
                        <div class="col-md-4">
                            <input style="margin-top: 7px" type="number" name="" value="" class="form-control" id="mcr_balance">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Keterangan</label>
                        </div>
                        <div class="col-md-8">
                            <textarea style="margin-top: 7px" class="form-control" id="mcr_ket"></textarea>
                        </div>
                    </div>
                </div>

                <br>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_asesmen">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p>Data Berhasil Tersimpan</p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_asesmen">
                    <h4><i class="icon fa fa-ban"></i> Error !</h4>
                    <p id="error_ket_asesmen"></p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveObCairan('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-pemberian">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi pemberian obat <span id="label_kat_pemberian"></span> </h4>
            </div>
            <div class="modal-body">
                <div style="margin-bottom:7px">
                    <button type="button" class="btn btn-success" onclick="addPemberianObat()">
                        <i class="fa fa-plus"></i> Add
                    </button>
                </div>
                <table class="table table-bordered" style="font-size: 12px">
                    <thead id="thead_pemberian">
                    </thead>
                    <tbody id="body-list-pemberian">

                    </tbody>
                </table>
                <input type="hidden" id="kat_pemberian">
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-pemberian-non-parenteral">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi pemberian obat non parenteral</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label>Created Date</label>
                        </div>
                        <div class="col-md-4">
                            <input type="date" name="" value="" class="date form-control" id="nonpar_date"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Obat</label>
                        </div>
                        <div class="col-md-8">
                            <select style="margin-top: 7px" class="form-control" name="" id="select_obat_nonpar">

                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Dosis</label>
                        </div>
                        <div class="col-md-4">
                            <input type="text" style="margin-top: 7px" name="" value="" class="form-control" id="nonpar_dosis">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Waktu</label>
                        </div>
                        <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" name="" id="select_waktu_nonpar">
                                <option value='pagi'>Pagi</option>
                                <option value='siang'>Siang</option>
                                <option value='sore'>Sore</option>
                                <option value='malam'>Malam</option>
                                <option value='bila perlu'>Bila Perlu</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Keterangan</label>
                        </div>
                        <div class="col-md-8">
                            <textarea style="margin-top: 7px" class="form-control" name="name" rows="8" cols="80" id="nonpar_keterangan"></textarea>
                        </div>
                    </div>
                </div>
                <br>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_asesmen">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p>Data Berhasil Tersimpan</p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_asesmen">
                    <h4><i class="icon fa fa-ban"></i> Error !</h4>
                    <p id="error_ket_asesmen"></p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="savePemberianObat('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-pemberian-parenteral">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi pemberian obat parenteral</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label>Created Date</label>
                        </div>
                        <div class="col-md-4">
                            <input type="date" name="" value="" class="date form-control" id="par_date"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Obat</label>
                        </div>
                        <div class="col-md-8">
                            <select style="margin-top: 7px" class="form-control" name="" id="select_obat_par">

                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Cara Pemberian</label>
                        </div>
                        <div class="col-md-8">
                            <input style="margin-top: 7px" type="text" name="" value="" class="form-control" id="par_cara">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Dosis</label>
                        </div>
                        <div class="col-md-4">
                            <input style="margin-top: 7px" type="text" name="" value="" class="form-control" id="par_dosis">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Skin Tes</label>
                        </div>
                        <div class="col-md-4">
                            <input style="margin-top: 7px" type="text" name="" value="" class="form-control" id="par_skintes">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Waktu</label>
                        </div>
                        <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" name="" id="select_waktu_par">
                                <option value='pagi'>Pagi</option>
                                <option value='siang'>Siang</option>
                                <option value='sore'>Sore</option>
                                <option value='malam'>Malam</option>
                                <option value='bila perlu'>Bila Perlu</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Keterangan</label>
                        </div>
                        <div class="col-md-8">
                            <textarea style="margin-top: 7px" class="form-control" name="name" rows="8" cols="80" id="par_keterangan"></textarea>
                        </div>
                    </div>
                </div>
                <br>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_asesmen">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p>Data Berhasil Tersimpan</p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_asesmen">
                    <h4><i class="icon fa fa-ban"></i> Error !</h4>
                    <p id="error_ket_asesmen"></p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="savePemberianObat('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-edukasi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Form Edukasi Pasien Dan Keluarga Terintregasi </h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <button type="button" class="btn btn-success" id="" onclick="addFormEdukasi('inap14')">
                        <i class="fa fa-plus"></i> Add Header Edukasi
                    </button>
                    <button type="button" class="btn btn-success" id="" onclick="addFormEdukasi('inap15')">
                        <i class="fa fa-plus"></i> Add Parameter Edukasi
                    </button>

                    <table class="table table-bordered" style="font-size: 12px">
                        <thead>
                        <td>Asesmen</td>
                        <td>Created Who</td>
                        <td>Created Date</td>
                        <td>Action</td>
                        </thead>
                        <tbody id="list-body-header-edukasi">

                        </tbody>
                    </table>

                    <table class="table table-bordered">
                        <thead>
                        <td>Asesmen</td>
                        <td>Created Who</td>
                        <td>Created Date</td>
                        <td>Action</td>
                        </thead>
                        <tbody id="list-body-edukasi">

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

<div class="modal fade" id="modal-detail-rekam-medic">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekam Medic Pasien</h4>
            </div>
            <div class="modal-body">
                <div id="head-detail-rm"></div>
                <hr/>
                <!-- Custom Tabs -->
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li id="tab_1"><a href="#" data-toggle="tab" onclick="viewDetailRekamMedicByKategori('tppri')">TPPRI</a></li>
                        <li><a href="#" data-toggle="tab" onclick="viewDetailRekamMedicByKategori('igd')">IGD</a></li>
                        <li><a href="#" data-toggle="tab" onclick="viewDetailRekamMedicByKategori('ri')">RAWAT INAP</a></li>
                        <li><a href="#" data-toggle="tab" onclick="viewDetailRekamMedicByKategori('mon')">MONITORING</a></li>
                    </ul>
                    <div class="tab-content" id="list-body-rekam-medic">
                    </div>
                    <input type="hidden" name="" id="rm-no-checkup">
                </div>

            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
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
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_laka"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<%--Modal Plan Kegiatan Rawat--%>
<div class="modal fade" id="modal-view-plan-detail">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> View Detail Rencana Rawat</h4>
            </div>
            <div class="modal-body" id="body-view-plan-detail">
                <div class="text-center">
                    <h4 id="empty_plan"></h4>
                </div>
                <div id="body-list-plan-pagi"></div>
                <br>
                <div id="body-list-plan-siang"></div>
                <br>
                <div id="body-list-plan-malam"></div>
            </div>
            <input type="hidden" id="id-detail">
            <input type="hidden" id="tgl-plan">
            <input type="hidden" id="jenis-plan">
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-edit-vital-sign">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi Vital Sign</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label style="margin-top: 7px">Jam</label>
                        </div>
                        <div class="col-md-2">
                            <select style="margin-top: 7px" class="form-control" id="edit_mvs_jam">
                                <option val='8'>8</option>
                                <option val='12'>12</option>
                                <option val='16'>16</option>
                                <option val='20'>20</option>
                            </select>
                        </div>
                    </div>
                </div>
                <hr style="color:#b0b0b0"/>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label>Nafas</label>
                            <select class="form-control" id="edit_mvs_nafas">
                                <option val='10'>10</option>
                                <option val='20'>20</option>
                                <option val='30'>30</option>
                                <option val='40'>40</option>
                                <option val='50'>50</option>
                                <option val='60'>60</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label>Nadi</label>
                            <select class="form-control" id="edit_mvs_nadi">
                                <option val='40'>40</option>
                                <option val='60'>60</option>
                                <option val='80'>80</option>
                                <option val='100'>100</option>
                                <option val='120'>120</option>
                                <option val='140'>140</option>
                                <option val='160'>160</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label>Suhu</label>
                            <select class="form-control" id="edit_mvs_suhu">
                                <option val='36'>36</option>
                                <option val='37'>37</option>
                                <option val='38'>38</option>
                                <option val='39'>39</option>
                                <option val='40'>40</option>
                                <option val='41'>41</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label>Tensi</label>
                            <input type="number" name="" value="" class="form-control" id="edit_mvs_tensi">
                        </div>
                    </div>
                </div>
                <hr style="color:#b0b0b0"/>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label>Tinggi badan</label>
                            <input type="number" name="" value="" class="form-control" placeholder="cm" id="edit_mvs_tb">
                        </div>
                        <div class="col-md-3">
                            <label>Berat badan</label>
                            <input type="number" name="" value="" class="form-control" placeholder="Kg" id="edit_mvs_bb">
                        </div>
                    </div>
                </div>
                <input type="hidden" id="edit_mvs_id"/>
                <br>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_vitalsign">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p>Data Berhasil Tersimpan</p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_vitalsign">
                    <h4><i class="icon fa fa-ban"></i> Error !</h4>
                    <p id="error_ket_vitalsign"></p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_vitalsign" onclick="saveUpdatePlan('vitalsign')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_vitalsign"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-edit-cairan">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi Cairan</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Macam Cairan</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" style="margin-top: 7px" name="" value="" class="form-control" id="edit_mcr_macam">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Melalui</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" style="margin-top: 7px" name="" value="" class="form-control" id="edit_mcr_melalui">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Jumlah (dalam botol)</label>
                        </div>
                        <div class="col-md-4">
                            <input type="number" style="margin-top: 7px" name="" value="" class="form-control" id="edit_mcr_jumlah">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Jam mulai</label>
                            <input type="text" name="" value="" class="time form-control" id="edit_mcr_mulai">
                        </div>
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Jam selesai</label>
                            <input type="text" name="" value="" class="time form-control" id="edit_mcr_selesai">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Cek tambahan obat</label>
                        </div>
                        <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" name="" id="edit_mcr_cek">
                                <option value="Ya">Ya</option>
                                <option value="Tidak">Tidak</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Sisa</label>
                        </div>
                        <div class="col-md-4">
                            <input style="margin-top: 7px" type="number" name="" value="" class="form-control" id="edit_mcr_sisa">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Jam ukur buang</label>
                        </div>
                        <div class="col-md-4">
                            <input style="margin-top: 7px" type="text" name="" value="" class="time form-control" id="edit_mcr_buang">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Dari</label>
                        </div>
                        <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" id="edit_mcr_dari">
                                <option val='Selang lambung'>Selang lambung</option>
                                <option val='Kandung kencing'>Kandung kencing</option>
                                <option val='Air seni biasa'>Air seni biasa</option>
                                <option val='Drainage'>Drainage</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Balance Cairan</label>
                        </div>
                        <div class="col-md-4">
                            <input style="margin-top: 7px" type="number" name="" value="" class="form-control" id="edit_mcr_balance">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Keterangan</label>
                        </div>
                        <div class="col-md-8">
                            <textarea style="margin-top: 7px" class="form-control" id="edit_mcr_ket"></textarea>
                        </div>
                    </div>
                </div>

                <input type="hidden" id="edit_mcr_id"/>

                <br>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_cairan">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p>Data Berhasil Tersimpan</p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_cairan">
                    <h4><i class="icon fa fa-ban"></i> Error !</h4>
                    <p id="error_ket_cairan"></p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_cairan" onclick="saveUpdatePlan('cairan')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_cairan"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-edit-pemberian-non-parenteral">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-pemberian"></span></h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Obat</label>
                        </div>
                        <div class="col-md-8">
                            <input style="margin-top: 7px" type="text" name="" value="" class="form-control" id="select_obat_edit_nonpar" readonly>
                        </div>
                    </div>
                </div>

                <div class="form-group" id="input_edit_par_cara">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Cara Pemberian</label>
                        </div>
                        <div class="col-md-8">
                            <input style="margin-top: 7px" type="text" name="" value="" class="form-control" id="edit_par_cara">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Dosis</label>
                        </div>
                        <div class="col-md-4">
                            <input type="text" style="margin-top: 7px" name="" value="" class="form-control" id="edit_nonpar_dosis">
                        </div>
                    </div>
                </div>

                <div class="form-group" id="input_edit_par_skintes">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Skin Tes</label>
                        </div>
                        <div class="col-md-4">
                            <input style="margin-top: 7px" type="text" name="" value="" class="form-control" id="edit_par_skintes">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Waktu Pemberian</label>
                        </div>
                        <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" id="select_waktu_edit_nonpar">
                                <option value='pagi'>Pagi</option>
                                <option value='siang'>Siang</option>
                                <option value='sore'>Sore</option>
                                <option value='malam'>Malam</option>
                                <option value='bila perlu'>Bila Perlu</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label style="margin-top: 7px">Keterangan</label>
                        </div>
                        <div class="col-md-8">
                            <textarea style="margin-top: 7px" class="form-control" name="name" rows="8" cols="80" id="edit_nonpar_keterangan"></textarea>
                        </div>
                    </div>
                </div>
                <br>
                <input type="hidden" id="edit_nonpar_id"/>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_nonpar">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p>Data Berhasil Tersimpan</p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_nonpar">
                    <h4><i class="icon fa fa-ban"></i> Error !</h4>
                    <p id="error_ket_nonpar"></p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_nonpar"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_nonpar"><i
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
                    <table class="table table-striped table-bordered">
                        <thead>
                        <td>Pemeriksaan</td>
                        <td>Jenis Penunjang</td>
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

<div class="modal fade" id="modal-detail-dokter">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Persetujuan Dokter</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <table class="table table-striped">
                            <tbody>
                            <tr>
                                <td width="30%">ID Dokter</td>
                                <td><span id="det_id_dokter"></span></td>
                            </tr>
                            <tr>
                                <td width="40%">Nama Dokter</td>
                                <td><span id="det_nama_dokter"></span></td>
                            </tr>
                            <tr>
                                <td width="40%">Status Request</td>
                                <td ><span id="det_status_dokter"></span></td>
                            </tr>
                            <tr>
                                <td width="40%">Keterangan</td>
                                <td><span id="det_keterangan_dokter"></span></td>
                            </tr>
                            </tbody>
                        </table>
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

<div class="modal fade" id="modal-konsultasi-dokter">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Konsultasi Selesai</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_kon_dokter">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_kon_dokter"></p>
                    </div>
                    <div class="row">
                        <table class="table table-striped">
                            <tbody>
                            <tr>
                                <td width="30%">ID Dokter</td>
                                <td><span id="kon_id_dokter"></span></td>
                            </tr>
                            <tr>
                                <td width="40%">Nama Dokter</td>
                                <td><span id="kon_nama_dokter"></span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="save_kon"><i class="fa fa-check"></i> Selesai
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_kon"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-uang_muka">
    <div class="modal-dialog" style="width: 55%;">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Daftar Uang Muka Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert-default alert-dismissible" style="display: none" id="def_uang_muka">
                        <p id="msg_def_uang_muka"></p>
                    </div>
                    <div class="alert alert-success alebody_uang_mukart-dismissible" style="display: none" id="suc_uang_muka">
                        <p id="msg_suc_uang_muka"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="war_uang_muka">
                        <p id="msg_war_uang_muka"></p>
                    </div>
                    <div class="row" style="margin-top: -10px" id="form-tambah-uang-muka">
                        <div class="col-md-3">
                            <button class="btn btn-success" id="btn_uang_muka" onclick="cekBtnUangMuka(this.id)"><i id="icon_uang_muka" class="fa fa-plus"></i> <span id="cek_name"> Tambah</span></button>
                        </div>
                        <div id="form-cek-uang" style="display: none">
                            <div class="col-md-5">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        Rp.
                                    </div>
                                    <input class="form-control" id="uang_muka_ri" oninput="convertRpAtas(this.id, this.value, 'h_uang_muka_ri')">
                                    <input type="hidden" id="h_uang_muka_ri">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-success" onclick="conUangMuka()"><i class="fa fa-check"></i> Save</button>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-bordered table-striped" style="font-size: 13px">
                                <thead>
                                <tr>
                                    <td width="20%">Tanggal</td>
                                    <td>Pelayanan</td>
                                    <td>Bukti</td>
                                    <td align="center" width="15%">Jumlah (Rp.)</td>
                                    <td align="center" width="15%">Status</td>
                                </tr>
                                </thead>
                                <tbody id="body_uang_muka">
                                </tbody>
                            </table>
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

<div class="mask"></div>

<div id="modal-temp"></div>


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
                            <td width="16%">Catatan</td>
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

<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenOperasiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/MppAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/MonitoringTransfusiDarahAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AppendecitomyAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenRawatInapAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/PengkajianUlangKeperawatanAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/CatatanTerintegrasiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/CatatanPemberianObatAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/RencanaAsuhanKeperawatanAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/RekonsiliasiObatAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/RingkasanPasienAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/EdukasiPasienAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenIcuAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/HemodinamikaAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/RespirasiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/IcuAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/KandunganAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/TindakanAction.js"/>'></script>

<script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/operasi.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/mpp.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/appendecitomy.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenrawatinap.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/pengkajiankeperawatan.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/ringkasanpasien.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/nyeri.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/datapasien.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/icu.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/kandungan.js"/>'></script>

<script type='text/javascript' src='<s:url value="/pages/dist/js/rekammedic.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/planrawat.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/rmrawatinap.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/addrawatinap.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/cppt.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/resikojatuh.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/tindakan_medis.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/custome_form.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/rencana_asuahan.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/allhistory.js"/>'></script>


<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli          = $('#id_palayanan').val();
    var idRawatInap     = $('#id_rawat_inap').val();
    var idPasien        = $('#id_pasien').val();
    var noCheckup       = $('#no_checkup').val();
    var jenisPeriksaPasien = $('#id_jenis_pasien').val();
    var today           = new Date();
    var month           = ""+(today.getMonth()+1);
    var day             = ""+today.getDate();

    var pathImages = '<%= request.getContextPath() %>';
    var contextPath = '<%= request.getContextPath() %>';
    var isReadRM = false;
    var tglLhr = '<s:property value="rawatInap.tglLahir"/>';
    var tglLahir = tglLhr.split("-").reverse().join("-");
    var namaPasien = '<s:property value="rawatInap.namaPasien"/>';
    var anamnese = '<s:property value="rawatInap.anamnese"/>';
    var penunjangMedis = '<s:property value="rawatInap.penunjangMedis"/>';
    var keluhanUtama = '<s:property value="rawatInap.keluhanUtama"/>';
    var suhu = '<s:property value="rawatInap.suhu"/>';
    var nadi = '<s:property value="rawatInap.nadi"/>';
    var tensi = '<s:property value="rawatInap.tensi"/>';
    var pernafasan = '<s:property value="rawatInap.pernafasan"/>';
    var alergi = '<s:property value="rawatInap.alergi"/>';
    var beratBadan = '<s:property value="rawatInap.berat"/>';
    var tinggiBadan = '<s:property value="rawatInap.tinggi"/>';
    var diagnosa = '<s:property value="rawatInap.namaDiagnosa"/>';
    var umur = '<s:property value="rawatInap.umur"/>';
    var alamatLengkap = '<s:property value="rawatInap.alamatLengkap"/>';
    var noBpjs = '<s:property value="rawatInap.noBpjs"/>';
    var jenisKelamin = '<s:property value="rawatInap.jenisKelamin"/>';
    var tempTensi = "";
    var tempSuhu = "";
    var tempNadi = "";
    var tempRr = "";
    var tempBerat = "";
    var tempTinggi = "";
    var tempAnmnesa = "";
    var kategoriRuangan = '<s:property value="rawatInap.kategoriRuangan"/>';
    var kelasPasienBpjs = '<s:property value="rawatInap.idKelas"/>';
    var tempidRm = "";
    var urlPage = "";
    var idRuangan = '<s:property value="rawatInap.idRuangan"/>';
    var namaRuangan = '<s:property value="rawatInap.namaRangan"/>';
    var stayRuangan = '<s:property value="rawatInap.isStay"/>';
    var NOSEP = '<s:property value="rawatInap.noSep"/>';
    var IdAsuransi = '<s:property value="rawatInap.idAsuransi"/>';
    var isBpjsRekanan = "";
    var setNotif = "";
    var idKelasRuangan = '<s:property value="rawatInap.idKelasRuangan"/>';
    var namaRuanganPasien = '<s:property value="rawatInap.namaRangan"/>';

    if (month.length < 2) {
        month = "0"+month;
    }
    if (day.length < 2) {
        day = "0"+day;
    }
    var date = today.getFullYear()+"-"+month+"-"+day;

    function titleCase(string) {
        var sentence = string.toLowerCase().split(" ");
        for(var i = 0; i< sentence.length; i++){
            sentence[i] = sentence[i][0].toUpperCase() + sentence[i].slice(1);
        }
        return sentence;
    }

    $(document).ready(function () {
        $("#mcr_mulai").timepicker();
        $("#mcr_selesai").timepicker();
        $("#mcr_buang").timepicker();
        $(".date").val(date);
        listDokter();
        listTindakan();
        listDiagnosa();
        listLab();
        listObat();
        listDiet();
        listRuanganInap();
        listResepPasien();
        hitungStatusBiaya();
        getJenisResep();
        listICD9();
        cekTranfersPasien('transfer_pasien');

        if(kategoriRuangan == 'rawat_inap'){
            $('#title-pages').text("Rawat Inap Pasien");
            $('#rawat_inap').addClass('active');
            urlPage = 'rawatinap';
            $('#pel_ri_active, #bayar_rawat_inap').addClass('active');
            $('#pel_ri_open').addClass('menu-open');
        }
        if(kategoriRuangan == 'rawat_intensif'){
            $('#title-pages').text("Rawat Intensif Pasien");
            $('#rawat_intensif').addClass('active');
            urlPage = 'rawatintensif';
            $('#pel_ri_active, #rawat_intensif').addClass('active');
            $('#pel_ri_open').addClass('menu-open');
        }
        if(kategoriRuangan == 'rawat_isolasi'){
            $('#title-pages').text("Rawat Isolasi Pasien");
            $('#rawat_isolasi').addClass('active');
            urlPage = 'rawatisolasi';
            $('#pel_ri_active, #rawat_isolasi').addClass('active');
            $('#pel_ri_open').addClass('menu-open');
        }
        if(kategoriRuangan == 'kamar_operasi'){
            $('#title-pages').text("Rawat Operasi Pasien");
            $('#rawat_operasi').addClass('active');
            urlPage = 'rawatoperasi';
            $('#pel_ri_active, #rawat_operasi').addClass('active');
            $('#pel_ri_open').addClass('menu-open');
        }
        if(kategoriRuangan == 'ruang_bersalin'){
            $('#title-pages').text("Rawat Bersalin Pasien");
            $('#rawat_bersalin').addClass('active');
            urlPage = 'rawatbersalin';
            $('#pel_ri_active, #rawat_bersalin').addClass('active');
            $('#pel_ri_open').addClass('menu-open');
        }
        if(kategoriRuangan == 'rr'){
            $('#title-pages').text("Recovery Room");
            $('#rr').addClass('active');
            urlPage = 'recoveryroom';
            $('#pel_ri_active, #rr').addClass('active');
            $('#pel_ri_open').addClass('menu-open');
        }

        $('#img_ktp').on('click', function(e){
            e.preventDefault();
            var src = $('#img_ktp').attr('src');
            if(src != null && src != ""){
                $('.mask').html('<div class="img-box"><img src="'+ src +'"><a class="close">&times;</a>');

                $('.mask').addClass('is-visible fadein').on('animationend', function(){
                    $(this).removeClass('fadein is-visible').addClass('is-visible');
                });

                $('.close').on('click', function(){
                    $(this).parents('.mask').addClass('fadeout').on('animationend', function(){
                        $(this).removeClass('fadeout is-visible')
                    });
                });
            }
        });
    });

    function loadModalRM(jenis) {
        var context = contextPath+'/pages/modal/modal-default.jsp';
        if(jenis != ""){
            context = contextPath + '/pages/modal/modal-'+jenis+'.jsp';
        }
        $('#modal-temp').load(context, function (res) {
        });
    }

    function setRekamMedis() {
        getListRekamMedis(kategoriRuangan, '', idDetailCheckup);
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
