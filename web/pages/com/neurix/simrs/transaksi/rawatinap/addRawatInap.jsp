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
            Rawat Inap Pasien
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

                                    <s:if test='rawatInap.idJenisPeriksa == "bpjs" || rawatInap.idJenisPeriksa == "ptpn"'>
                                        <tr>
                                            <td width="45%"><b>No SEP</b></td>
                                            <td style="vertical-align: middle;">
                                                <table>
                                                    <s:label cssClass="label label-success" name="rawatInap.noSep"></s:label></table>
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
                                    <s:if test='rawatInap.metodePembayaran != null && rawatInap.metodePembayaran != ""'>
                                        <tr>
                                            <td><b>Metode Pembayaran</b></td>
                                            <td>
                                                <table>
                                                    <script>
                                                        var metode = '<s:property value="rawatInap.metodePembayaran"/>';
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
                                <div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center" class="card card-4 pull-right">
                                    <img border="2" id="img_ktp" src="<s:property value="rawatInap.urlKtp"/>" style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">
                                </div>
                                <table class="table table-striped">
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label name="rawatInap.jenisPeriksaPasien"></s:label>
                                            </table>
                                        </td>
                                    </tr>
                                    <s:if test='rawatInap.idJenisPeriksaPasien == "paket_perusahaan" || rawatInap.idJenisPeriksaPasien == "paket_individu"'>
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
                                            <table><label class="label label-success"> <span id="no_ruang"></span> -
                                                <span id="name_ruang"></span></label></table>
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
                        <%--<div class="row">--%>
                            <%--<div class="form-group">--%>
                                <%--<div class="col-md-12">--%>
                                    <%--<a href="<%= request.getContextPath() %>/rekammedik/initRekamMedik_rekammedik.action?id=<s:property value="rawatInap.idDetailCheckup"/>&tipe=RI" class="btn btn-primary pull-left"><i class="fa fa-user-plus"></i> E-Rekam Medik</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <%--<div class="box-header with-border"></div>--%>
                    <%--<div class="box-header with-border">--%>
                        <%--<h3 class="box-title"><i class="fa fa-hospital-o"></i> Asesmen</h3>--%>
                    <%--</div>--%>
                    <%--<div class="box-body">--%>
                        <%--<button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap10')">--%>
                            <%--<i class="fa fa-edit"></i> Form Riwayat Kesehatan--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap11')">--%>
                            <%--<i class="fa fa-edit"></i> Form Keadaan Umum--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap12')">--%>
                            <%--<i class="fa fa-edit"></i> Form Pemeriksaan Fisik--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap13')">--%>
                            <%--<i class="fa fa-edit"></i> Form Asesmen Nyeri--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalResiko('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap1')">--%>
                            <%--<i class="fa fa-edit"></i> Form Resiko Dekubitus--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalResiko('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap2')">--%>
                            <%--<i class="fa fa-edit"></i> Form Fungsional--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalResiko('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap3')">--%>
                            <%--<i class="fa fa-edit"></i> Form Skrining Gizi Pasien Dewasa--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalResiko('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap4')">--%>
                            <%--<i class="fa fa-edit"></i> Form Skrining Gizi Pasien Anak--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap5')">--%>
                            <%--<i class="fa fa-edit"></i> Form Seksual dan Reproduksi--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap9')">--%>
                            <%--<i class="fa fa-edit"></i> Form Kebutuhan Komunikasi. Kognisi dan Edukasi--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap6')">--%>
                            <%--<i class="fa fa-edit"></i> Form Psikososial dan Ekonomi--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap7')">--%>
                            <%--<i class="fa fa-edit"></i> Form Spritual--%>
                        <%--</button>--%>
                        <%--<button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap8')">--%>
                            <%--<i class="fa fa-edit"></i> Form Diagnose Keperawatan yang muncul--%>
                        <%--</button>--%>
                    <%--</div>--%>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-laptop"></i> Monitoring</h3>
                            </div>
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-medkit"></i> Rekam Medis</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <button class="btn btn-primary" onclick="showModalPlan('<s:property value="rawatInap.idDetailCheckup"/>','','suster')">
                                    <i class="fa fa-calendar"></i> Schedule Rawat
                                </button>
                                <div class="btn-group btn-hide">
                                    <button type="button" class="btn btn-info"><i class="fa fa-edit"></i> Observasi dan Pemberian
                                    </button>
                                    <button type="button" class="btn btn-info dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a onclick="showModalCairan('<s:property value="rawatInap.idDetailCheckup"/>')" style="cursor: pointer"><i
                                                class="fa fa-circle-o"></i> Form Observasi Cairan</a></li>
                                        <li><a onclick="showModalMonVitalSign('<s:property value="rawatInap.idDetailCheckup"/>')" style="cursor: pointer"><i
                                                class="fa fa-circle-o"></i> Form Observasi Vital Sign</a></li>
                                        <li><a onclick="showModalPemberianObat('<s:property value="rawatInap.idDetailCheckup"/>','parenteral')" style="cursor: pointer"><i
                                                class="fa fa-circle-o"></i> Form Pemberian Obat Parenteral</a></li>
                                        <li><a onclick="showModalPemberianObat('<s:property value="rawatInap.idDetailCheckup"/>','nonparenteral')" style="cursor: pointer"><i
                                                class="fa fa-circle-o"></i> Form Pemberian Obat Non Parenteral</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="btn-group btn-hide">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Asesmen
                                    </button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('asesmen_rawat_inap')"
                                               onclick="showModalAsesmenRawatInap('asesmen')"><i
                                                class="fa fa-circle-o"></i>Asesmen Awal Medis Rawat Inap</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('catatan_integrasi')"
                                               onclick="showModalAsesmenRawatInap('catatan_integrasi')"><i
                                                class="fa fa-circle-o"></i>Catatan Perkembangan Pasien Terintegrasi</a>
                                        </li>
                                        <li><a style="cursor: pointer"
                                               onmouseover="loadModalRM('pengkajian_keperawatan')"
                                               onclick="showModalPengkajianKep('pengkajian')"><i
                                                class="fa fa-circle-o"></i>Pengkajian Ulang Keperawatan dan Tindakan</a>
                                        </li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('asuhan')"
                                               onclick="showModalAsesmenRawatInap('asuhan')"><i
                                                class="fa fa-circle-o"></i>Rencana Asuhan Keperawatan</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('mpp')" onclick="showModalMpp('evaluasi_awal')"><i class="fa fa-circle-o"></i>Form-A Evaluasi Awal</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('mpp')" onclick="showModalMpp('impementasi_mpp')"><i class="fa fa-circle-o"></i>Form-B Catatan Implementasi</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('edukasi_pasien')" onclick="showModalAsesmenRawatInap('edukasi_pasien')"><i class="fa fa-circle-o"></i>Edukasi Pasien dan Keluarga</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('edukasi_pasien_integrasi')" onclick="showModalAsesmenRawatInap('edukasi_pasien_terintegrasi')"><i class="fa fa-circle-o"></i>Edukasi Pasien dan Keluarga Terintegrasi</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('discharge_planing')"
                                               onclick="showModalAsesmenRawatInap('discharge_planing')"><i
                                                class="fa fa-circle-o"></i>Discharge Planing</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('transfer_pasien')"
                                               onclick="showModalAsesmenRawatInap('transfer_pasien')"><i
                                                class="fa fa-circle-o"></i>Transfer Pasien Antar Ruangan</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('privasi')"
                                               onclick="showModalAsesmenRawatInap('privasi')"><i
                                                class="fa fa-circle-o"></i>Privasi Pasien</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('early_warning')"
                                               onclick="showModalAsesmenRawatInap('early_warning')"><i
                                                class="fa fa-circle-o"></i>Lembar Observasi Pediatric Early Warning
                                            Score</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('pemberian_obat')"
                                               onclick="showModalAsesmenRawatInap('catatan_pemberian')"><i
                                                class="fa fa-circle-o"></i>Catatan Pemberian Obat</a></li>
                                        <%--<li><a style="cursor: pointer" onclick="showModalAsesmenRawatInap('injeksi')"><i--%>
                                                <%--class="fa fa-circle-o"></i>Injeksi CPO / Jadwal Pemberian Terapi</a>--%>
                                        </li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('rekonsiliasi')"
                                               onclick="showModalAsesmenRawatInap('rekonsiliasi')"><i
                                                class="fa fa-circle-o"></i>Rekonsiliasi Obat</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('tindakan_ri')" onclick="showModalAsesmenRawatInap('tindakan_ina')"><i
                                                class="fa fa-circle-o"></i>Persetujuan Tindakan Kedokteran</a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Kamar Operasi
                                    </button>
                                    <button onclick="loadModalRM('operasi')" type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('ceklist_operasi')"><i class="fa fa-circle-o"></i>Ceklist Serah Terima Pasien Pre Operasi</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('penandaan_area')"><i class="fa fa-circle-o"></i>Penandaan Area Operasi Pasien</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('pra_anestesi')"><i class="fa fa-circle-o"></i>Asesmen Pra Anestesi</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('general_anestesi')"><i class="fa fa-circle-o"></i>Edukasi dan Persetujuan General Anestesi</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('regional_anestesi')"><i class="fa fa-circle-o"></i>Edukasi dan Persetujuan Regional Anestesi</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('pindah_rr')"><i class="fa fa-circle-o"></i>Kriteia Pindah dari RR</a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> ICU
                                    </button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('icu')"  onclick="showModalICU('asesmen_icu')"><i class="fa fa-circle-o"></i>Asesmen ICU</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('hemodinamika')"  onclick="showModalICU('hemodinamika')"><i class="fa fa-circle-o"></i>Hemodinamika</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('respirasi')"  onclick="showModalICU('respirasi')"><i class="fa fa-circle-o"></i>Respirasi</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('keseimbangan')"  onclick="showModalICU('keseimbangan')"><i class="fa fa-circle-o"></i>Keseimbangan</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('obat-obatan')"  onclick="showModalICU('obat-obatan')"><i class="fa fa-circle-o"></i>Obat Obatan/ Intakea/ Output</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('asuhan_keperawatan')"  onclick="showModalICU('asuhan_keperawatan')"><i class="fa fa-circle-o"></i>Asuhan Keperawatan</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('tindakan_icu')"  onclick="showModalICU('tindakan_icu')"><i class="fa fa-circle-o"></i>Persetujuan Tindakan Kedokteran</a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Kebidanan</small>
                                    </button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('identifikasi_bayi')"  onclick="showModalRB('identifikasi_bayi')"><i class="fa fa-circle-o"></i>Identifikasi Bayi</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('asesmen_ponek')"  onclick="showModalRB('asesmen_ponek')"><i class="fa fa-circle-o"></i>Asesmen Ponek</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('asesmen_awal_bayi')"  onclick="showModalRB('asesmen_awal_bayi')"><i class="fa fa-circle-o"></i>Asesmen Awal Bayi</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('laporan_pembedahan')"  onclick="showModalRB('laporan_pembedahan')"><i class="fa fa-circle-o"></i>Laporan Pembedahan</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('laporan_persalinan')"  onclick="showModalRB('laporan_persalinan')"><i class="fa fa-circle-o"></i>Laporan Persalinan</a></li>
                                        <li><a style="cursor: pointer" onmouseover="loadModalRM('tindakan_rb')"  onclick="showModalRB('tindakan_rb')"><i class="fa fa-circle-o"></i>Persetujuan Tindakan Kedokteran</a></li>
                                        <li><a style="cursor: pointer" onclick="printPernyataan('SK04')"><i class="fa fa-print"></i>Surat Keterangan Lahir</a></li>
                                        <li><a style="cursor: pointer" onclick="printPernyataan('SP08')"><i class="fa fa-print"></i>Surat Pernyataan Rujuk RSDS</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="box-header with-border" id="pos_dok">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user-md"></i> Dokter</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(1)"><i class="fa fa-plus"></i> Tambah Dokter
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Dokter</td>
                                <td>Nama</td>
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
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(3)"><i class="fa fa-plus"></i> Tambah Diagnosa
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
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(8)"><i class="fa fa-plus"></i> Tambah ICD9
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
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(2)"><i class="fa fa-plus"></i> Tambah Tindakan
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>Tindakan</td>
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
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Penunjang Medis</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(4)"><i class="fa fa-plus"></i> Penunjang Medis
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Order</td>
                                <td>Pemeriksaan</td>
                                <td>Status</td>
                                <td>Jenis Lab</td>
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
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Order Diet</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(5)"><i class="fa fa-plus"></i> Order Diet
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90" style="height: 20px">
                                <td >Tanggal</td>
                                <td >ID Diet Gizi</td>
                                <td >Bentuk Diet</td>
                                <td >Keterangan</td>
                                <td align="center"width="10%">Status</td>
                                <td align="center" rowspan="2" width="18%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_diet">

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
                    <%--onclick="showModal(6)"><i class="fa fa-plus"></i> Obat Penunjang--%>
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

                    <div class="box-header with-border" id="pos_ruangan">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Ruangan</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Nama Ruangan</td>
                                <td>No Ruangan</td>
                                <td>Kelas</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_ruangan">

                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border" id="pos_rssep">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Order Resep Obat</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(7)"><i class="fa fa-plus"></i> Tambah Resep</button>
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
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-navicon"></i> Keterangan (Jika sudah pulang / selesai rawat inap)</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ket">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            Silahkan cek kembali data inputan!
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 10px">Keterangan</label>
                                    <div class="col-md-8">
                                        <select class="form-control select2" id="keterangan" style="width: 100%"
                                                onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()}; selectKeterangan(this.value)">
                                            <option value=''>[Select One]</option>
                                            <option value='selesai'>Selesai</option>
                                            <option value='lanjut_biaya'>Lanjut Biaya</option>
                                            <option value='rujuk_rs_lain'>Rujuk RS Lain</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div id="form-selesai" style="display: none">
                                <div class="form-group">
                                    <label class="col-md-5" style="margin-top: 7px; margin-bottom: -2px;">Catatan Selesai</label>
                                    <div class="col-md-7">
                                        <s:action id="initComboKet" namespace="/checkupdetail"
                                                  name="getListComboKeteranganKeluar_checkupdetail"/>
                                        <s:select list="#initComboKet.listOfKeterangan" id="ket_selesai"
                                                  name="headerCheckup.idPelayanan" listKey="keterangan"
                                                  listValue="keterangan" cssStyle="width: 100%"
                                                  onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()}; showFormCekup(this)"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-5" style="margin-top: 7px; margin-bottom: -2px;">Cara Pasien </label>
                                    <div class="col-md-7">
                                        <s:select list="#{'Jalan':'Jalan','Pakai kursi Roda':'Pakai kursi Roda','Pakai stretcher':'Pakai stretcher'}" cssStyle="margin-top: 7px; width: 100%"
                                                  id="ket_cara"
                                                  headerKey="" headerValue="[Select One]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-5" style="margin-top: 7px; margin-bottom: -2px;">Didampingi Oleh</label>
                                    <div class="col-md-7">
                                        <s:select list="#{'Petugas':'Petugas','Keluarga/teman':'Keluarga/teman'}" cssStyle="margin-top: 7px; width: 100%"
                                                  id="ket_pendamping"
                                                  headerKey="" headerValue="[Select One]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-5" style="margin-top: 7px; margin-bottom: -2px;">Tempat Tujuan</label>
                                    <div class="col-md-7">
                                        <s:select list="#{'Rumah':'Rumah','Dirujuk Ke':'Dirujuk Ke','Rehabilitas':'Rehabilitas'}" cssStyle="margin-top: 7px; width: 100%"
                                                  id="ket_tujuan"
                                                  headerKey="" headerValue="[Select One]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                            </div>
                            </div>

                            <div class="col-md-5">
                                <div class="form-group" id="form-cekup" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-5" style="margin-top: 7px">Tanggal Chekup Ulang</label>
                                        <div class="col-md-7">
                                            <div class="input-group date">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <s:textfield id="tgl_cekup" cssClass="form-control datepicker"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-5" style="margin-top: 7px">Catatan</label>
                                        <div class="col-md-7">
                                            <s:textarea cssStyle="margin-top: 7px" cssClass="form-control" rows="5" id="cekup_ket"></s:textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-4 text-center">
                                        <a href="initForm_rawatinap.action" class="btn btn-warning"
                                           style="margin-top: 15px;"><i
                                                class="fa fa-arrow-left"></i> Back
                                        </a>
                                        <button class="btn btn-success" onclick="confirmSaveKeterangan()"
                                                style="margin-top: 15px;" id="save_ket"><i
                                                class="fa fa-check"></i> Close
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
                    <p id="msg_diagnosa"></p>
                </div>
                <div class="row">
                    <%--<s:if test='rawatInap.idJenisPeriksa == "bpjs" || rawatInap.idJenisPeriksa == "ptpn"'>--%>
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-7">
                                <s:textfield id="nosa_id_diagnosa" style="margin-top: 7px"
                                             name="headerCheckup.diagnosa"
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
                                <option value="0">Diagnosa Awal</option>
                                <option value="1">Diagnosa Akhir</option>
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
                                      onchange="var warn =$('#war_kategori_lab').is(':visible'); if (warn){$('#cor_kategori_lab').show().fadeOut(3000);$('#war_kategori_lab').hide()}; listSelectLab(this)"
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
                                    onchange="var warn =$('#war_lab').is(':visible'); if (warn){$('#cor_lab').show().fadeOut(3000);$('#war_lab').hide()}; listSelectParameter(this);">
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

<div class="modal fade" id="modal-diet">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Order Diet</h4>
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
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 10px">Keterangan</label>
                        <div class="col-md-7">
                            <select style="margin-top: 7px" class="form-control" id="keterangan_diet" onchange="var warn =$('#war_keterangan_diet').is(':visible'); if (warn){$('#cor_keterangan_diet').show().fadeOut(3000);$('#war_keterangan_diet').hide()}">
                                <option value="">[Select One]</option>
                                <option value="Pagi">Pagi</option>
                                <option value="Siang">Siang</option>
                                <option value="Malam">Malam</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_keterangan_diet"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_keterangan_diet">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_diet"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_diet"><i
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
                <button type="button" class="btn btn-success" id="save_obat"><i class="fa fa-arrow-right"></i> Save
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
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kelas Ruangan</label>
                        <div class="col-md-7">
                            <s:action id="initKelas" namespace="/checkupdetail"
                                      name="getListComboKelasRuangan_checkupdetail"/>
                            <s:select
                                    onchange="var warn =$('#war_ruangan_kelas').is(':visible'); if (warn){$('#cor_ruangan_kelas').show().fadeOut(3000);$('#war_ruangan_kelas').hide()}; listSelectRuangan(this)"
                                    list="#initKelas.listOfKelasRuangan" id="ruangan_kelas"
                                    listKey="idKelasRuangan" cssStyle="width: 100%"
                                    listValue="namaKelasRuangan"
                                    headerKey="" headerValue="[Select one]"
                                    cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ruangan_kelas"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ruangan_kelas"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
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
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_ruang"><i
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
                                      name="getComboApotekRi_checkup"/>
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
                        <div class="col-md-7">
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
                                <input type="checkbox" name="cek_waktu" id="pagi" value="Pagi" onclick="var warn =$('#war_rep_cek_waktu').is(':visible'); if (warn){$('#cor_rep_cek_waktu').show().fadeOut(3000);$('#war_rep_cek_waktu').hide()}">
                                <label for="pagi"></label> Pagi
                            </div>
                            <div class="form-check" style="margin-top: 7px; margin-left: 10px">
                                <input type="checkbox" name="cek_waktu" id="siang" value="Siang" onclick="var warn =$('#war_rep_cek_waktu').is(':visible'); if (warn){$('#cor_rep_cek_waktu').show().fadeOut(3000);$('#war_rep_cek_waktu').hide()}">
                                <label for="siang"></label> Siang
                            </div>
                            <div class="form-check" style="margin-top: 7px; margin-left: 10px">
                                <input type="checkbox" name="cek_waktu" id="malam" value="Malam" onclick="var warn =$('#war_rep_cek_waktu').is(':visible'); if (warn){$('#cor_rep_cek_waktu').show().fadeOut(3000);$('#war_rep_cek_waktu').hide()}">
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
                <button type="button" class="btn btn-success" id="save_resiko" onclick="saveResiko('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
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
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveAsesmen('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi Vital Sign </h4>
            </div>
            <div class="modal-body">
                <div style="margin-bottom:7px">
                    <button type="button" class="btn btn-success" onclick="addMonVitalSign()">
                        <i class="fa fa-plus"></i> Add
                    </button>
                    <button type="button" class="btn btn-info" onclick="showGrafVitalSign('<s:property value="rawatInap.idDetailCheckup"/>')">
                        <i class="fa fa-pie-chart"></i> View Graf
                    </button>
                </div>
                <table class="table table-bordered" style="font-size: 12px">
                    <thead>
                    <td>Jam</td>
                    <td>Nafas</td>
                    <td>Nadi</td>
                    <td>Suhu</td>
                    <td>Tensi</td>
                    <td>Berat Badan</td>
                    <td>Tinggi Badan</td>
                    <td>Created Who</td>
                    <td>Created Date</td>
                    </thead>
                    <tbody id="body-list-vital-sign">

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
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveVitalSign('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
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
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveObCairan('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
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
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="savePemberianObat('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
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
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="savePemberianObat('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
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
                <button type="button" class="btn btn-success" id="save_cancel_diet"><i class="fa fa-arrow-right"></i> Save
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
                <br>
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
                <button type="button" class="btn btn-success" id="save_vitalsign" onclick="saveUpdatePlan('vitalsign')"><i class="fa fa-arrow-right"></i> Save
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
                <button type="button" class="btn btn-success" id="save_cairan" onclick="saveUpdatePlan('cairan')"><i class="fa fa-arrow-right"></i> Save
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
                <button type="button" class="btn btn-success" id="save_nonpar"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_nonpar"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="mask"></div>

<div id="modal-temp"></div>

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

<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli          = $('#id_palayanan').val();
    var idRawatInap     = $('#id_rawat_inap').val();
    var idPasien        = $('#id_pasien').val();
    var noCheckup       = $('#no_checkup').val();
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
        $('#rawat_inap').addClass('active');
        $("#mcr_mulai").timepicker();
        $("#mcr_selesai").timepicker();
        $("#mcr_buang").timepicker();
        $(".date").val(date);
        listDokter();
        listTindakan();
        listDiagnosa();
        listSelectDokter();
        listLab();
        listObat();
        listDiet();
        listRuanganInap();
        listResepPasien();
        listSelectTindakanKategori();
        hitungStatusBiaya();
        hitungCoverBiaya();
        getJenisResep();
        listICD9();

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

    });

    function loadModalRM(jenis) {
        var context = "";
        if(jenis != ""){
            context = contextPath + '/pages/modal/modal-'+jenis+'.jsp';
        }
        $('#modal-temp').load(context, function (res) {
        });
    }

    function printPernyataan(kode) {
        window.open(contextPath+'/rekammedik/printSuratPernyataan_rekammedik?id=' + idDetailCheckup + '&tipe=' + kode, '_blank');
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
