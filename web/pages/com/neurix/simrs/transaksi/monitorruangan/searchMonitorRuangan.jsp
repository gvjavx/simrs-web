<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>

    <style>
        .btn-trans {
            background-color: #404040;
            width: 110px;
            height: 153px;
            border-radius: 10px;
            opacity: 0.9;
            /*padding-right: 20px;
            padding-left: 20px;*/
            padding: 6px;
            float: left;
            margin: 5px;
            border: 1px solid #f7f7f7;
            font-size: 12px;
            text-align: center;
            color: #fff;
        }

        .box-blue {
            background-color: #367fa9;
        }

        .box-bluemuda {
            background-color: #00acd6;
        }

        .box-green {
            background-color: #449d44;
        }

        .box-red {
            background-color: #c9302c;
        }

        .box-yellow {
            background-color: #e08e0b;
        }

        .btn-orange {
            background-color: orange;
        }

        .btn-default {
            /*background-color: #e7e7e7;*/
            /*color:#fff;*/
            width: 100%;
            height: 20px;
            margin: 0 auto;
            padding: 0;
            display: inline-block;
            line-height: 0px;
            text-align: center;
        }

        .btn-transparent {
            background-color: transparent;
            color: #fff;
        }

        .btn-white:hover {
            color: #fff;
        }

        .btn-trans:active {
            background-color: #2caaea;
        }

        .btn-trans:visited {
            background-color: #2caaea;
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
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#monitor_ruangan').addClass('active');
        });

        function formatRupiah(angka) {
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        }

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
            Monitor Ruangan
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Monitor Ruangan</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i></button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="monitorruanganForm" method="post" namespace="/monitorruangan"
                                    action="search_monitorruangan.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kelas Ruangan</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboKelas" namespace="/checkupdetail"
                                                  name="getListComboKelasRuangan_checkupdetail"/>
                                        <s:select cssStyle="margin-top: 7px"
                                                  onchange="$(this).css('border',''); listSelectRuangan(this)"
                                                  list="#initComboKelas.listOfKelasRuangan" id="kelas_kamar"
                                                  name="ruangan.idKelasRuangan"
                                                  listKey="idKelasRuangan"
                                                  listValue="namaKelasRuangan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                    <div class="col-sm-3" style="display: none;" id="load_ruang">
                                        <img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                             style="cursor: pointer; width: 45px; height: 45px"><b
                                            style="color: #00a157;">Sedang diproses...</b></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Ruangan</label>
                                    <div class="col-sm-4">
                                        <select id="ruangan_ruang" style="margin-top: 7px" class="form-control select2"
                                                id="nama_ruangan" name="ruangan.idRuangan">
                                            <option value=''>[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="monitorruanganForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_monitorruangan.action">
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
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Status Ruangan</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-12" style="display:inline; padding-left: 5%">
                                <s:iterator value="#session.listOfResult" var="row">
                                <div class="btn-wrapper" onclick="detailTindakan('<s:property value="idDetailCheckup"/>')">
                                    <s:if test='#row.namaPasien != null'>
                                        <s:if test='#row.nilaiPersen > 70'>
                                            <div id="id_box" class="blink_me btn-trans box-red">
                                        </s:if>
                                        <s:elseif test='#row.nilaiPersen > 50'>
                                            <div id="id_box" class="btn-trans box-yellow">
                                        </s:elseif>
                                        <s:else>
                                            <div id="id_box" class="btn-trans box-green">
                                        </s:else>
                                        </s:if>
                                        <s:else>
                                        <div id="id_box" class="btn-trans">
                                            </s:else>
                                            <button class="btn btn-default" style="height: 20px; width: 100%; font-size: 10px; align-content: center">
                                                <s:property value="namaRuangan"/></button>
                                            <div style="text-align:left; cursor:pointer; font-size:11px;">
                                                <table align="center"
                                                       style="width:100%; border-radius:5px; margin-top:2px;">
                                                    <td align="center" colspan="2">
                                                        <img style="background-color:transparent; height:70px; padding-bottom: 2px" src="<s:url value="/pages/images/room.png"/>">
                                                    </td>
                                                    <tr>
                                                        <td align="left" colspan="2" style="color: white; font-size: 9px; padding-top: 3px; border-bottom: white solid 2px"><s:if test='#row.namaPasien != null'><i class="fa fa-user"></i> <s:property value="namaPasien"></s:property></s:if><s:else>&nbsp;</s:else></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="left" colspan="2" style="color: white; font-size: 9px; padding-top: 5px"><s:if test='#row.tarifBpjs'><script>var tar = '<s:property value="tarifBpjs"/>'; if(tar != null){document.write('<i class="fa fa-square" style="font-size:8px"></i> '+"Rp. "+formatRupiah(tar))}</script></s:if><s:else>&nbsp;</s:else></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="left" colspan="2" style="color: white; font-size: 9px; margin-top: 3px"><s:if test='#row.tarifTindakan'><script>var tar = '<s:property value="tarifTindakan"/>'; if(tar != null){document.write('<i class="fa fa-square" style="font-size:8px"></i> '+"Rp. "+formatRupiah(tar))}</script></s:if><s:else>&nbsp;</s:else></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="right" colspan="2" style="color: white; font-size: 9px; margin-top: 3px"><s:if test='#row.tipeTransaksi == "bpjs"'><img src="<s:url value="/pages/images/logo_bpjs.png"/>" style="width: 10px; height: 10px; background-color: white"></s:if><s:else><label class="label label-primary"><s:property value="tipeTransaksi"/></label></s:else></td>
                                                    </tr>
                                                    <%--<label class="label label-info"><s:property value="tipeTransaksi"/></label>--%>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    </s:iterator>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </section>
</div>

<div class="modal fade" id="modal-detail-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_tin">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_tin"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_tin">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_tin2"></p>
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr id="show_sep">
                                    <td><b>No SEP</b></td>
                                    <td style="vertical-align: middle"><span class="label label-success" id="det_no_sep"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No Checkup</b></td>
                                    <td><span id="det_no_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="det_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="det_nama"></span></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="det_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, TGL Lahir</b></td>
                                    <td><span id="det_tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="det_desa"></span>, <span id="det_kecamatan"></span></td>
                                </tr>
                                <%--<tr>--%>
                                <%--<td><b>Provinsi</b></td>--%>
                                <%--<td><span id="det_provinsi"></span></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                <%--<td><b>Kabupaten</b></td>--%>
                                <%--<td></span></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                <%--<td><b>Kecamatan</b></td>--%>
                                <%--<td><span id="det_kecamatan"></span></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                <%--<td><b>Desa</b></td>--%>
                                <%--<td></span></td>--%>
                                <%--</tr>--%>
                            </table>
                        </div>

                    </div>
                </div>
                <input type="hidden" id="tin_id_detail_checkup">
                <div class="box-body">
                    <div id="bar_bpjs">
                    <div class="row">
                        <div class="col-md-8">
                            <h5>
                                Cover Biaya Bpjs
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
                        </div>
                        <div class="col-md-4">
                            <p style="margin-top: 20px">Keterangan</p>
                            <small>
                                <ul style="list-style-type: none">
                                    <li><i class="fa fa-square" style="color: #337ab7"></i> Total biaya cover Bpjs
                                    </li>
                                    <li><i class="fa fa-square" style="color: #5cb85c"></i> Total biaya tindakan <
                                        50%
                                    </li>
                                    <li><i class="fa fa-square" style="color: #f0ad4e"></i> Total biaya tindakan >
                                        50% dan < 70%
                                    </li>
                                    <li><i class="fa fa-square" style="color: #d9534f"></i> Total biaya tindakan >
                                        70%
                                    </li>
                                </ul>
                            </small>
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
<!-- /.content-wrapper -->
<script type='text/javascript'>

    function formatRupiah(angka) {
        if(angka != "" && angka != null){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        }else{
            return "0";
        }

    }

    function hitungStatusBiaya(idDetailCheckup) {
        CheckupDetailAction.getStatusBiayaTindakan(idDetailCheckup, function (response) {
            if (response.idJenisPeriksaPasien == "bpjs") {
                if (response.tarifBpjs != null && response.tarifTindakan != null) {

                    var coverBiaya = response.tarifBpjs;
                    var biayaTindakan = response.tarifTindakan;

                    var persen = "";
                    if (coverBiaya != '' && biayaTindakan) {
                        persen = ((parseInt(biayaTindakan) / parseInt(coverBiaya)) * 100).toFixed(2);
                    } else {
                        persen = 0;
                    }

                    var barClass = "";
                    var barLabel = "";

                    if (parseInt(persen) > 70) {
                        barClass = 'progress-bar-danger';
                    } else if (parseInt(persen) > 50) {
                        barClass = 'progress-bar-warning';
                    } else {
                        barClass = 'progress-bar-success';
                    }

                    var barBpjs = '<div class="progress-bar progress-bar-primary" style="width: 100%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">' + "100.00%" + '</div>';

                    var barTindakan = '<div class="progress-bar ' + barClass + '" style="width: ' + persen + '%" role="progressbar" aria-valuenow="' + persen + '" aria-valuemin="0" aria-valuemax="100">' + persen + "%" + '</div>';

                    if (coverBiaya != '') {
                        $('#sts_cover_biaya').html(barBpjs);
                        $('#b_bpjs').html(formatRupiah(coverBiaya) + " (100%)");
                        $('#fin_sts_cover_biaya').html(barBpjs);
                        $('#fin_b_bpjs').html(formatRupiah(coverBiaya) + " (100%)");
                    }

                    if (biayaTindakan != '') {
                        $('#sts_biaya_tindakan').html(barTindakan);
                        $('#b_tindakan').html(formatRupiah(biayaTindakan) + " (" + persen + "%)");
                        $('#fin_sts_biaya_tindakan').html(barTindakan);
                        $('#fin_b_tindakan').html(formatRupiah(biayaTindakan) + " (" + persen + "%)");
                    }
                }
            } else {
            }
        });
    }

    function detailTindakan(idDetailCheckup) {
        setTimeout(function () {
        if(idDetailCheckup != ''){
            $('#sts_cover_biaya').html('');
            $('#b_bpjs').html('');
            $('#sts_biaya_tindakan').html('');
            $('#b_tindakan').html('');
            hitungStatusBiaya(idDetailCheckup);
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
            var total = 0;
            var cekTindakan = false;

            var url = '<s:url value="/pages/images/spinner.gif"/>';
            // $('#v_'+idCheckup).attr('src',url).css('width', '30px', 'height', '40px');

                var url = '<s:url value="/pages/images/icons8-create-25.png"/>';
                // $('#v_'+idCheckup).attr('src',url).css('width', '', 'height', '');

                CheckupAction.listDataPasien(idDetailCheckup, function (response) {
                    if (response != null) {
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

                        if(response.idJenisPeriksaPasien == "bpjs"){
                            $('#show_sep, #bar_bpjs').show();
                        }else{
                            $('#show_sep, #bar_bpjs').hide();
                        }
                    }
                });


                $('#det_no_sep').html(noSep);
                $('#det_no_checkup').html(noCheckup);
                $('#det_nik').html(nik);
                $('#det_nama').html(namaPasien);
                $('#det_jenis_kelamin').html(jenisKelamin);
                $('#det_tgl').html(tglLahir);
                $('#det_agama').html(agama);
                $('#det_suku').html(suku);
                $('#det_alamat').html(alamat);
                $('#det_provinsi').html(provinsi);
                $('#det_kabupaten').html(kabupaten);
                $('#det_kecamatan').html(kecamatan);
                $('#det_desa').html(desa);
                $('#body_tindakan').html(table);
                $('#tin_id_detail_checkup').val(idDetailCheckup);
                $('#modal-detail-pasien').modal({show:true, backdrop:'static'});
        }else{
        }
        }, 100);
    }




</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>