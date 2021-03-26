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
        .btn {
            margin-top: 7px;
        }

        .form-check {
            display: inline-block;
            padding-left: 2px;
        }

        .radio-margin {
            margin-top: -7px;
            margin-left: 1px;
        }

        .paint-canvas-ttd {
            border: 1px black solid;
            margin: 1rem;
        }

        .garis {
            border: solid 0.5px #ddd;
        }

        .bungkus {
            width: 100%;
            height: 420px;
            max-width: 100%;
            max-height: 100%;
            margin: auto;
            overflow: hidden;
        }

        .dropdown-menu {
            min-width: 200px;
        }
        .dropdown-menu.columns-2 {
            min-width: 400px;
        }
        .dropdown-menu.columns-3 {
            min-width: 1000px;
        }
        .dropdown-menu li a {
            padding: 5px 15px;
            font-weight: 300;
        }
        .multi-column-dropdown {
            list-style: none;
        }
        .multi-column-dropdown li a {
            display: block;
            clear: both;
            line-height: 1.428571429;
            color: #333;
            white-space: normal;
        }
        .multi-column-dropdown li a:hover {
            text-decoration: none;
            color: #262626;
            background-color: #f5f5f5;
        }

        @media (max-width: 1200px) {
            .dropdown-menu.multi-column {
                min-width: 700px !important;
                overflow-x: hidden;
            }
        }

    </style>
    
    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">

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
            Detail Rekam Medik
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien Periksa Pada Tanggal <s:property value="detailCheckup.stTanggalMasuk"/></h3>
                    </div>
                    <div class="box-body">
                        <script>
                            var cek = cekImages('<s:property value="imgKtp"/>');
                            var url = '';
                            if(cek){
                                url = '<s:property value="imgKtp"/>';
                            }else{
                                url = contextPathHeader+'/pages/images/no-images.png';
                            }
                            var set = '<div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center"\n' +
                                'class="card card-4 pull-right">\n' +
                                '<img border="2" id="img_ktp" src="'+url+'"\n' +
                                'style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">\n' +
                                '</div>';
                            document.write(set);
                        </script>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <table class="table table-striped">
                                    <s:hidden id="no_checkup" name="detailCheckup.noCheckup"></s:hidden>
                                    <s:hidden id="id_palayanan" name="detailCheckup.idPelayanan"></s:hidden>
                                    <s:hidden id="no_detail_checkup" name="detailCheckup.idDetailCheckup"/>
                                    <s:hidden id="id_pasien" name="detailCheckup.idPasien"/>
                                    <s:hidden id="jenis_pasien" name="detailCheckup.idJenisPeriksaPasien"/>
                                    <s:hidden id="jenis_bayar" name="detailCheckup.metodePembayaran"/>

                                    <s:hidden id="h_nama_pasien" name="detailCheckup.namaPasien"/>
                                    <s:hidden id="h_tgl_lahir" name="detailCheckup.tglLahir"/>
                                    <s:hidden id="h_anamnesa" name="detailCheckup.anamnese"/>
                                    <s:hidden id="h_penunjang_medis" name="detailCheckup.penunjangMedis"/>
                                    <s:hidden id="h_keluhan_utama" name="detailCheckup.keluhanUtama"/>
                                    <s:hidden id="h_suhu" name="detailCheckup.suhu"/>
                                    <s:hidden id="h_nadi" name="detailCheckup.nadi"/>
                                    <s:hidden id="h_tensi" name="detailCheckup.tensi"/>
                                    <s:hidden id="h_pernafasan" name="detailCheckup.pernafasan"/>
                                    <s:hidden id="h_alergi" name="detailCheckup.alergi"/>
                                    <s:hidden id="h_berat_badan" name="detailCheckup.berat"/>
                                    <s:hidden id="h_tinggi_badan" name="detailCheckup.tinggi"/>
                                    <s:hidden id="h_diagnosa" name="detailCheckup.namaDiagnosa"/>
                                    <s:hidden id="h_umur" name="detailCheckup.umur"/>
                                    <s:hidden id="h_alamat_lengkap" name="detailCheckup.alamatLengkap"/>
                                    <s:hidden id="h_no_bpjs" name="detailCheckup.noBpjs"/>
                                    <s:hidden id="h_jenis_kelamin" name="detailCheckup.jenisKelamin"/>
                                    <s:hidden id="h_tipe_pelayanan" name="detailCheckup.tipePelayanan"/>
                                    <s:hidden id="h_kategori_pelayanan" name="detailCheckup.kategoriPelayanan"/>

                                    <tr>
                                        <td width="35%"><b>No RM</b></td>
                                        <td>
                                            <table><s:label
                                                    name="detailCheckup.idPasien" id="pos_scrol"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup</b></td>
                                        <td>
                                            <table>
                                                <s:label name="detailCheckup.noCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td>
                                            <table><s:label
                                                    name="detailCheckup.idDetailCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.nik"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.namaPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.jenisKelamin"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.tempatTglLahir"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Umur</b></td>
                                        <td>
                                            <table>
                                                <script>
                                                    var umur = '<s:property value="detailCheckup.umur"/>';
                                                    var umurLengkap = umur+' Tahun';
                                                    document.write(umurLengkap);
                                                </script>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label id="jenis_periksa"
                                                         name="detailCheckup.jenisPeriksaPasien"></s:label>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <table class="table table-striped">
                                    <tr>
                                        <td width="35%"><b>Poli</b></td>
                                        <td>
                                            <table>
                                                <s:label name="detailCheckup.namaPelayanan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.desa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tanggal Periksa</b></td>
                                        <td>
                                            <table><s:property value="detailCheckup.stTanggalMasuk"/></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Diagnosa Medis</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.diagnosa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <table><s:label name="detailCheckup.namaDiagnosa"></s:label></table>
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
                        <div class="box-header with-border"></div>
                        <div class="row" style="padding: 5px">
                            <div class="col-md-12 text-center">
                                <s:if test='detailCheckup.idx != null'>
                                    <a class="btn btn-warning" href="<%= request.getContextPath() %>/<s:property value="detailCheckup.url"/>/add_<s:property value="detailCheckup.url"/>.action?id=<s:property value="detailCheckup.idx"/>"><i class="fa fa-times"></i>
                                        Back</a>
                                </s:if>
                                <s:else>
                                    <a class="btn btn-warning" href="initForm_rekammedis.action"><i class="fa fa-times"></i>
                                        Back</a>
                                </s:else>
                                <%--<a class="btn btn-danger"--%>
                                   <%--href="detail_rekammedis.action?idPasien=<s:property value="detailCheckup.idPasien"/>"><i--%>
                                        <%--class="fa fa-refresh"></i> Reset</a>--%>
                                <%--<a class="btn btn-info" id="btn-vidio-rm2" onclick="viewTelemedic()"><i class="fa fa-film"></i>--%>
                                    <%--Telemedic</a>--%>
                                <%--<a class="btn btn-info" onclick="viewRiwayat()"><i class="fa fa-history"></i> Riwayat Pasien</a>--%>
                                <a class="btn btn-info" onclick="viewHistory()"><i class="fa fa-history"></i> All History</a>
                                <a class="btn bg-aqua" onclick="viewAllRekamMedis()"><i class="fa fa-book"></i> All Rekam Medis</a>
                                <a class="btn btn-primary" onclick="viewAllRekamMedisLama()"><i class="fa fa-book"></i> Rekam Medis Lama</a>
                            </div>
                        </div>
                        <div class="box-header with-border"></div>
                    </div>
                    <div class="box-body">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-user"></i> Data Rekam Medik Periksa Pada Tanggal <s:property value="detailCheckup.stTanggalMasuk"/></h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--<div class="row">--%>
            <%--<div class="col-md-12">--%>
                <%--<div class="btn-group dropup" style="margin-top: -15px">--%>
                    <%--<button type="button" class="btn btn-info"><i class="fa fa-book"></i> All Rekam Medis--%>
                    <%--</button>--%>
                    <%--<button onclick="loadModalRM('operasi')" type="button" class="btn btn-info dropdown-toggle"--%>
                            <%--data-toggle="dropdown" style="height: 34px">--%>
                        <%--<span class="caret"></span>--%>
                        <%--<span class="sr-only">Toggle Dropdown</span>--%>
                    <%--</button>--%>
                    <%--<ul class="dropdown-menu multi-column columns-3 text-center" style="font-size: 12px">--%>
                        <%--<div class="row">--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<ul class="multi-column-dropdown">--%>
                                    <%--<li style="margin-bottom: -6px"><a><b>Spesialis</b></a></li>--%>
                                    <%--<li><a style="cursor: pointer" onclick="showModalRj('ringkasan_rj')"><i class="fa fa-file-o"></i> Profil Rekam Medis Rawat Jalan</a></li>--%>
                                    <%--<s:if test='detailCheckup.kategoriPelayanan != "" && detailCheckup.kategoriPelayanan != "ugd"'>--%>
                                        <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('<s:property value="detailCheckup.kategoriPelayanan"/>')" onclick="showModalSPS('<s:property value="detailCheckup.kategoriPelayanan"/>')"><i class="fa fa-file-o"></i> <s:property value="detailCheckup.asesmenLabel"/></a></li>--%>
                                    <%--</s:if>--%>
                                    <%--<li class="divider"></li>--%>
                                    <%--<li style="margin-bottom: -6px"><a><b>Hemodialisa (HD)</b></a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('hemodialisa')" onclick="showModalHD('monitoring_hd')"><i class="fa fa-file-o"></i> Monitoring HD</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('hemodialisa')" onclick="showModalHD('perencanaan_hemodialisa')"><i class="fa fa-file-o"></i> Perencanaan HD</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('hemodialisa')" onclick="showModalHD('asesmen_hd')"><i class="fa fa-file-o"></i> Asesmen Awal HD</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('hemodialisa')" onclick="showModalHD('tranfusi_hd')"><i class="fa fa-file-o"></i> Tindakan Medis Transfusi Darah</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('hemodialisa')" onclick="showModalHD('catatan_tranfusi_darah')"><i class="fa fa-file-o"></i> Catatan Pemantauan Tranfusi Darah</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('hemodialisa')" onclick="showModalHD('persetujuan_hd')"><i class="fa fa-file-o"></i> Persetujuan HD</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('hemodialisa')" onclick="showModalHD('travelling')"><i class="fa fa-file-o"></i> Travelling Dialysis</a></li>--%>
                                    <%--<li class="divider"></li>--%>
                                    <%--<li style="margin-bottom: -6px"><a><b>Fisioterapi</b></a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('fisioterapi')" onclick="pengkajianFisioterapi('<s:property value="headerDetailCheckup.idDetailCheckup"/>')"><i class="fa fa-file-o"></i> Pengkajian Pasien Fisioterapi</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('fisioterapi')" onclick="addMonitoringFisioterapi('<s:property value="headerDetailCheckup.idDetailCheckup"/>')"><i class="fa fa-file-o"></i> Kunjungan Fisioterapi</a></li>--%>
                                    <%--<li class="divider"></li>--%>
                                    <%--<li style="margin-bottom: -6px"><a><b>Unit Gawat Darurat (UGD)</b></a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('ugd_anak')" onclick="showAsesmenUgd('Asesmen Awal Gawat Darurat Anak')"><i class="fa fa-file-o"></i> Asesmen Awal Gawat Darurat Anak</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('ugd_dewasa')" onclick="showAsesmenUgd('Asesmen Awal Gawat Darurat Dewasa')"><i class="fa fa-file-o"></i> Asesmen Awal Gawat Darurat Dewasa</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('ugd_dewasa')" onclick="showAsesmenUgd('Asesmen Awal Gawat Darurat Geriatri')"><i class="fa fa-file-o"></i> Asesmen Awal Gawat Darurat Geriatri</a></li>--%>
                                    <%--<li class="divider"></li>--%>
                                    <%--<li style="margin-bottom: -6px"><a><b>Rawat Inap</b></a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('asesmen_rawat_inap')"--%>
                                           <%--onclick="showModalAsesmenRawatInap('asesmen')"><i--%>
                                            <%--class="fa fa-file-o"></i> Asesmen Awal Medis Rawat Inap</a></li>--%>

                                <%--</ul>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<ul class="multi-column-dropdown">--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('catatan_integrasi')"--%>
                                           <%--onclick="showModalAsesmenRawatInap('catatan_integrasi')"><i--%>
                                            <%--class="fa fa-file-o"></i> Catatan Perkembangan Pasien Terintegrasi</a>--%>
                                    <%--</li>--%>
                                    <%--<li><a style="cursor: pointer"--%>
                                           <%--onmouseover="loadModalRM('pengkajian_keperawatan')"--%>
                                           <%--onclick="showModalPengkajianKep('pengkajian')"><i--%>
                                            <%--class="fa fa-file-o"></i> Pengkajian Ulang Keperawatan dan Tindakan</a>--%>
                                    <%--</li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('asuhan')"--%>
                                           <%--onclick="showModalAsesmenRawatInap('asuhan')"><i--%>
                                            <%--class="fa fa-file-o"></i> Rencana Asuhan Keperawatan</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('mpp')" onclick="showModalMpp('evaluasi_awal')"><i class="fa fa-file-o"></i> Form-A Evaluasi Awal</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('mpp')" onclick="showModalMpp('impementasi_mpp')"><i class="fa fa-file-o"></i> Form-B Catatan Implementasi</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('edukasi_pasien')" onclick="showModalAsesmenRawatInap('edukasi_pasien')"><i class="fa fa-file-o"></i> Edukasi Pasien dan Keluarga</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('edukasi_pasien_integrasi')" onclick="showModalAsesmenRawatInap('edukasi_pasien_terintegrasi')"><i class="fa fa-file-o"></i> Edukasi Pasien dan Keluarga Terintegrasi</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('discharge_planing')"--%>
                                           <%--onclick="showModalAsesmenRawatInap('discharge_planing')"><i--%>
                                            <%--class="fa fa-file-o"></i> Discharge Planing</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('transfer_pasien')"--%>
                                           <%--onclick="showModalAsesmenRawatInap('transfer_pasien')"><i--%>
                                            <%--class="fa fa-file-o"></i> Transfer Pasien Antar Ruangan</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('privasi')"--%>
                                           <%--onclick="showModalAsesmenRawatInap('privasi')"><i--%>
                                            <%--class="fa fa-file-o"></i> Privasi Pasien</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('early_warning')"--%>
                                           <%--onclick="showModalAsesmenRawatInap('early_warning')"><i--%>
                                            <%--class="fa fa-file-o"></i> Lembar Observasi Pediatric Early Warning--%>
                                        <%--Score</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('pemberian_obat')"--%>
                                           <%--onclick="showModalAsesmenRawatInap('catatan_pemberian')"><i--%>
                                            <%--class="fa fa-file-o"></i> Catatan Pemberian Obat</a></li>--%>
                                    <%--&lt;%&ndash;<li><a style="cursor: pointer" onclick="showModalAsesmenRawatInap('injeksi')"><i&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;class="fa fa-file-o"></i>Injeksi CPO / Jadwal Pemberian Terapi</a>&ndash;%&gt;--%>
                                    <%--</li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('rekonsiliasi')"--%>
                                           <%--onclick="showModalAsesmenRawatInap('rekonsiliasi')"><i--%>
                                            <%--class="fa fa-file-o"></i> Rekonsiliasi Obat</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('tindakan_ri')" onclick="showModalAsesmenRawatInap('tindakan_ina')"><i--%>
                                            <%--class="fa fa-file-o"></i> Persetujuan Tindakan Kedokteran</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('checklist_transfer_external')" onclick="showModalAsesmenRawatInap('checklist_transfer_external')"><i--%>
                                            <%--class="fa fa-file-o"></i> Checklist Persiapan Transfer Pasien Ekternal</a></li>--%>
                                    <%--<li class="divider"></li>--%>
                                    <%--<li style="margin-bottom: -6px"><a><b>Operasi Kamar</b></a></li>--%>
                                    <%--<li><a style="cursor: pointer" onclick="showModalOperasi('ceklist_operasi')"><i class="fa fa-file-o"></i> Ceklist Serah Terima Pasien Pre Operasi</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onclick="showModalOperasi('penandaan_area')"><i class="fa fa-file-o"></i> Penandaan Area Operasi Pasien</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onclick="showModalOperasi('pra_anestesi')"><i class="fa fa-file-o"></i> Asesmen Pra Anestesi</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onclick="showModalOperasi('informasi_dan_persetujuan')"><i class="fa fa-file-o"></i> Pemberian Edukasi dan Persetujuan</a></li>--%>

                                <%--</ul>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<ul class="multi-column-dropdown">--%>
                                    <%--<li><a style="cursor: pointer" onclick="showModalOperasi('pindah_rr')"><i class="fa fa-file-o"></i> Kriteia Pindah dari RR</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onclick="showModalOperasi('laporan_operasi')"><i class="fa fa-file-o"></i> Laporan Operasi</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('catatan_anestesi')" onclick="showModalOperasi('catatan_anestesi')"><i class="fa fa-file-o"></i> Catatan Anestesi</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('checklist_keselamatan_pasien')" onclick="showModalOperasi('checklist_keselamatan_pasien')"><i class="fa fa-file-o"></i> Ceklist Keselamatan Pasien Operasi</a></li>--%>
                                    <%--<li class="divider"></li>--%>
                                    <%--<li style="margin-bottom: -6px"><a><b>Intensive Care Unit (ICU)</b></a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('icu')"  onclick="showModalICU('asesmen_icu')"><i class="fa fa-file-o"></i> Asesmen ICU</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('hemodinamika')"  onclick="showModalICU('hemodinamika')"><i class="fa fa-file-o"></i> Hemodinamika</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('respirasi')"  onclick="showModalICU('respirasi')"><i class="fa fa-file-o"></i> Respirasi</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('keseimbangan')"  onclick="showModalICU('keseimbangan')"><i class="fa fa-file-o"></i> Keseimbangan</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('obat-obatan')"  onclick="showModalICU('obat-obatan')"><i class="fa fa-file-o"></i> Obat Obatan/ Intakea/ Output</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('asuhan_keperawatan')"  onclick="showModalICU('asuhan_keperawatan')"><i class="fa fa-file-o"></i> Asuhan Keperawatan</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('tindakan_icu')"  onclick="showModalICU('tindakan_icu')"><i class="fa fa-file-o"></i> Persetujuan Tindakan Kedokteran</a></li>--%>
                                    <%--<li class="divider"></li>--%>
                                    <%--<li style="margin-bottom: -6px"><a><b>Ruang Bersalin (RB)</b></a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('identifikasi_bayi')"  onclick="showModalRB('identifikasi_bayi')"><i class="fa fa-file-o"></i> Identifikasi Bayi</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('asesmen_ponek')"  onclick="showModalRB('asesmen_ponek')"><i class="fa fa-file-o"></i> Asesmen Ponek</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('asesmen_awal_bayi')"  onclick="showModalRB('asesmen_awal_bayi')"><i class="fa fa-file-o"></i> Asesmen Awal Bayi</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('laporan_pembedahan')"  onclick="showModalRB('laporan_pembedahan')"><i class="fa fa-file-o"></i> Laporan Pembedahan</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('laporan_persalinan')"  onclick="showModalRB('laporan_persalinan')"><i class="fa fa-file-o"></i> Laporan Persalinan</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('tindakan_rb')"  onclick="showModalRB('tindakan_rb')"><i class="fa fa-file-o"></i> Persetujuan Tindakan Kedokteran</a></li>--%>
                                    <%--<li><a style="cursor: pointer" onmouseover="loadModalRM('asuhan_keperawatan_rb')"  onclick="showModalRB('asuhan_keperawatan_rb')"><i class="fa fa-file-o"></i> Asuhan Kebidanan</a></li>--%>
                                <%--</ul>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="row jarak">
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button onclick="setRekamMedis('rawat_jalan', '<s:property value="detailCheckup.kategoriPelayanan"/>', 'ases_sps')" style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right" id="ases_sps">
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">SPESIALIS</span>
                        <span class="info-box-number"><small>(Asesmen Spesialis)</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button onclick="setRekamMedis('rawat_jalan', 'hemodialisa', 'ases_hd')" style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right" id="ases_hd">
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">HD</span>
                        <span class="info-box-number"><small>(Hemodialisa)</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button onclick="setRekamMedis('rawat_jalan', 'fisioterapi', 'ases_fs')" style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right" id="ases_fs">
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">FISIOTERAPI</span>
                        <span class="info-box-number"><small>&nbsp;</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button onclick="setRekamMedis('igd', '', 'ases_igd')" style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right" id="ases_igd">

                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">UGD</span>
                        <span class="info-box-number"><small>(Unit Gawat Darurat)</small></span>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button onclick="setRekamMedis('rawat_inap', '', 'ases_ri')" style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right" id="ases_ri">

                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">RAWAT INAP</span>
                        <span class="info-box-number"><small>&nbsp;</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button onclick="setRekamMedis('kamar_operasi', '', 'ases_ok')" style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right" id="ases_ok">
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">OK</span>
                        <span class="info-box-number"><small>(Operasi Kamar)</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button onclick="setRekamMedis('rawat_intensif', '', 'ases_icu')" style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right" id="ases_icu">
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">ICU</span>
                        <span class="info-box-number"><small>(Intensive Care Unit)</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button onclick="setRekamMedis('ruang_bersalin', '', 'ases_rb')" style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right" id="ases_rb">
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">RB</span>
                        <span class="info-box-number"><small>(Ruang Bersalin)</small></span>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-riwayat">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Riwayat Pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <table id="tableRM" class="table table-bordered table-striped">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td>ID Detail Checkup</td>
                            <td>Tanggal Masuk</td>
                            <td>Tanggal Keluar</td>
                            <td>Keterangan</td>
                            <td>Pelayanan</td>
                            <td align="center">Action</td>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="#session.listOfRekamMedis" var="row">
                            <tr>
                                <td><s:property value="idDetailCheckup"/></td>
                                <td><s:property value="stTanggalMasuk"/></td>
                                <td><s:property value="stTanggalKeluar"/></td>
                                <td><s:property value="keteranganSelesai"/></td>
                                <td><s:property value="namaPelayanan"/></td>
                                <td align="center">
                                    <a href="detail_rekammedis.action?idPasien=<s:property value="detailCheckup.idPasien"/>&id=<s:property value="idDetailCheckup"/>">
                                        <img class="hvr-grow"
                                             src="<s:url value="/pages/images/icons8-search-25.png"/>"
                                             style="cursor: pointer;">
                                    </a>
                                </td>
                            </tr>
                        </s:iterator>
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

<div class="modal fade" id="modal-all_rekam_medis">
    <div class="modal-dialog" style="width: 75%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> All Rekam Medis Pasien
                </h4>
            </div>
            <div class="modal-body">
                <span class="text-center">
                     <p id="loading_page" style="color: #0F9E5E; display: none"><img style="width: 50px; height: 50px" src="<s:url value="/pages/images/spinner.gif"/>"><b>Sedang mencari data traksaksi...</b></p>
                </span>
                <div class="box-body" style="font-size: 13px">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="row">
                                <ul class="fa-ul" id="asesmen_sps">

                                </ul>
                            </div>
                            <div class="row">
                                <ul class="fa-ul" id="asesmen_fs">
                                </ul>
                            </div>
                            <div class="row">
                                <ul class="fa-ul" id="asesmen_hd">
                                </ul>
                            </div>
                            <div class="row">
                                <ul class="fa-ul" id="asesmen_igd">
                                </ul>
                            </div>
                            <div class="row">
                                <ul class="fa-ul" id="asesmen_tppri">
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="row">
                                <ul class="fa-ul" id="asesmen_ri">
                                </ul>
                            </div>
                            <div class="row">
                                <ul class="fa-ul" id="asesmen_icu">
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="row">
                                <ul class="fa-ul" id="asesmen_ko">
                                </ul>
                            </div>
                            <div class="row">
                                <ul class="fa-ul" id="asesmen_rb">
                                </ul>
                            </div>
                            <div class="row">
                                <ul class="fa-ul" id="asesmen_surat">
                                </ul>
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

<div class="modal fade" id="modal-rekam-medis-lama">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekam Medis Lama</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <span style="font-weight: bold; color: #0F9E5E" class="text-center" id="id_loading"></span>
                            <div class="box box-solid">
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                        <ol class="carousel-indicators" id="button_ol">

                                        </ol>
                                        <div class="carousel-inner" id="isi_carousel">

                                        </div>
                                        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                            <span class="fa fa-angle-left"></span>
                                        </a>
                                        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                            <span class="fa fa-angle-right"></span>
                                        </a>
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
                <button type="button" class="btn btn-sm btn-default" id="save_con_rm"><i class="fa fa-check"></i> Ya
                </button>
            </div>
        </div>
    </div>
</div>

<div class="mask"></div>
<%@ include file="/pages/modal/modalRingkasanRawatJalan.jsp" %>

<script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/FisioterapiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/HemodialisaAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/RekamMedisRawatJalanAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/MonitoringTransfusiDarahAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenSpesialisAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenUgdAction.js"/>'></script>
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
<script type='text/javascript' src='<s:url value="/dwr/interface/KeperawatanRawatJalanAction.js"/>'></script>

<script type='text/javascript' src='<s:url value="/pages/dist/js/datapasien.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/fisioterapi.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/hd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/rj.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/addrawatjalan.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/spesialis.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/nyeri.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/history.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenUgd.js"/>'></script>
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
<script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenrawatjalan.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/cppt.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/rencana_asuahan.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/tindakan_medis.js"/>'></script>

<script type='text/javascript'>

    var isReadRM = true;
    var idDetailCheckup = $('#no_detail_checkup').val();
    var contextPath = '<%= request.getContextPath() %>';
    var idPasien = $('#id_pasien').val();

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
    var noCheckup = $('#no_checkup').val();
    var tipePelayanan = $('#h_tipe_pelayanan').val();
    var kategoriPelayanan = "";
    var tempTensi = "";
    var tempSuhu = "";
    var tempNadi = "";
    var tempRr = "";
    var tempBerat = "";
    var tempTinggi = "";
    var tempAnmnesa = "";
    var namaRuanganPasien = "";
    var isEksekutif = "";
    var flagVaksin = "";
    var tanggalMasuk = new Date();

    function loadModalRM(jenis, method, parameter, idRM, flag) {
        var context = contextPath + '/pages/modal/modal-default.jsp';
        if (jenis != "") {
            context = contextPath + '/pages/modal/modal-'+jenis+'.jsp';
        }
        $('#modal-temp').load(context, function (res, status, xhr) {
            if(status == "success"){
                var func = new Function(method+'(\''+parameter+'\', \''+idRM+'\', \''+flag+'\')');
                func();
            }
        });
    }

    $(document).ready(function () {
        $('#tableRM').DataTable({
            "order": [[0, "desc"]],
            "pageLength": 5
        });

        $('#rekam_medik').addClass('active');

        var option = '<option value="5">5</option>' +
            '<option value="10">10</option>' +
            '<option value="25">25</option>' +
            '<option value="50">50</option>' +
            '<option value="75">75</option>' +
            '<option value="100">100</option>';

        $('[name=tableRM_length]').html(option);
        $('.dropup').on('show.bs.dropdown', function(e){
            $(this).find('.dropdown-menu').first().stop(true, true).slideDown(350);
        });

        $('.dropup').on('hide.bs.dropdown', function(e){
            $(this).find('.dropdown-menu').first().stop(true, true).slideUp(350);
        });


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

        $('.carousel').carousel({
            interval: false,
            ride: false,
            pause: false
        });

    });

    function printPernyataan(kode, idRm, flag, namaRm) {
        $('#tanya').text("Apakah anda yakin print ?");
        $('#print_form').text(namaRm);
        $('#save_con_rm').attr('onclick', 'printPernyataanRM(\'' + kode + '\', \'' + idRm + '\')');
        $('#modal-confirm-rm').modal('show');
    }

    function printPernyataanRM(kode, idRM) {
        window.open(contextPath + '/rekammedik/printSuratPernyataan_rekammedik?id=' + idDetailCheckup + '&tipe=' + kode + '&ids=' + idRM, '_blank');
        $('#modal-confirm-rm').modal('hide');
        $('#info_dialog').dialog('open');
        $('#close_pos').val(14);
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>