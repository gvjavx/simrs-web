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
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#permintaan_obat').addClass('active');
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
            Permintaan Obat Poli
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Permintaan Obat Poli</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="permintaanForm" method="post" namespace="/checkup" action="search_checkup.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="headerCheckup.idPasien">ID Pasien</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="headerCheckup.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="headerCheckup.noKtp">No KTP</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_ktp" cssStyle="margin-top: 7px"
                                                     name="headerCheckup.noKtp" required="true"
                                                     cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="headerCheckup.nama">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="headerCheckup.nama"
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
                                                  list="#initComboPoli.listOfPelayanan" id="poli"
                                                  name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                  listValue="namaPelayanan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'0':'Antrian','1':'Periksa','2':'Rujuk','3':'Selesai'}"
                                                  cssStyle="margin-top: 7px"
                                                  id="status" name="headerCheckup.statusPeriksa"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="headerCheckup.jalan">Alamat</label>
                                    <div class="col-sm-4">
                                        <s:textarea cssStyle="margin-top: 7px" id="alamat" name="headerCheckup.jalan"
                                                    required="false" readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="headerCheckup.stTglFrom"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="headerCheckup.stTglTo"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="permintaanForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-primary" href="add_checkup.action"><i
                                                class="fa fa-plus"></i> Tambah Rawat Pasien</a>
                                        <a type="button" class="btn btn-danger" href="initForm_checkup.action">
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
                                            <%--<center>--%>
                                                <%--<img border="0" style="width: 150px; height: 150px"--%>
                                                     <%--src="<s:url value="/pages/images/spinner.gif"/>"--%>
                                                     <%--name="image_indicator_write">--%>
                                            <%--</center>--%>
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Permintaan Obat Poli</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Nama Pelayanan</td>
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td>Qty</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfUsers">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td><s:property value="namaPelayanan"/></td>
                                    <td><s:property value="statusPeriksaName"/></td>
                                    <td align="center">
                                        <img border="0" class="hvr-grow" id="v_<s:property value="noCheckup"/>" src="<s:url value="/pages/images/search_flat.png"/>"
                                             style="cursor: pointer; width: 25px; height: 25px" onclick="detail_pasien('<s:property value="noCheckup"/>')">
                                        <s:url var="edit" namespace="/checkup" action="edit_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <s:a href="%{edit}">
                                            <img border="0" class="hvr-grow" src="<s:url value="/pages/images/edit-flat-new.png"/>"
                                                 style="cursor: pointer; width: 25px; height: 25px">
                                        </s:a>
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

<div class="modal fade" id="modal-detail-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-search"></i> Detail Rawat Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
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
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="det_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, TGL Lahir</b></td>
                                    <td><span id="det_tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Agama</b></td>
                                    <td><span id="det_agama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Suku</b></td>
                                    <td><span id="det_suku"></span></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="det_alamat"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Provinsi</b></td>
                                    <td><span id="det_provinsi"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kabupaten</b></td>
                                    <td><span id="det_kabupaten"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kecamatan</b></td>
                                    <td><span id="det_kecamatan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Desa</b></td>
                                    <td><span id="det_desa"></span></td>
                                </tr>
                            </table>
                        </div>

                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Riwayat Checkup</h3>
                </div>

                <div class="box-body">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td>Poli</td>
                            <td>Status</td>
                            <td>Keterangan</td>
                            <td>Ruang</td>
                            <td>No</td>
                        </tr>
                        </thead>
                        <tbody id="det_riwayat">
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

<script type='text/javascript'>
    function detail_pasien(idCheckup) {
        var table = "";
        var dataRiwayat = [];
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
        var pelayanan = "";
        var ket = "";
        var ruangan = "";
        var noRuangan = "";

        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#v_'+idCheckup).attr('src',url).css('width', '30px', 'height', '40px');


        setTimeout(function () {

            var url = '<s:url value="/pages/images/search_flat.png"/>';
            $('#v_'+idCheckup).attr('src',url).css('width', '25px', 'height', '25px');

            CheckupAction.listDataPasien(idCheckup, function (response) {
                dataPasien = response;
                if (dataPasien != null) {
                    $.each(dataPasien, function (i, item) {
                        var tanggal = item.tglLahir;
                        var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                        noCheckup = item.noCheckup;
                        nik = item.noKtp;
                        namaPasien = item.nama;

                        if (item.jenisKelamin == "L") {
                            jenisKelamin = "Laki-Laki";
                        } else {
                            jenisKelamin = "Perempuan";
                        }

                        tglLahir = item.tempatLahir + ", " + dateFormat;
                        agama = item.agama;
                        suku = item.suku;
                        alamat = item.jalan;
                        provinsi = item.namaProvinsi;
                        kabupaten = item.namaKota;
                        kecamatan = item.namaKecamatan;
                        desa = item.namaDesa;
                    });
                }
            });

            CheckupAction.listRiwayatPasien(idCheckup, function (response) {
                dataRiwayat = response;
                if (dataRiwayat != null) {
                    $.each(dataRiwayat, function (i, item) {

                        if (item.namaPelayanan) {
                            pelayanan = item.namaPelayanan;
                        }
                        if (item.keteranganSelesai) {
                            ket = item.keteranganSelesai;
                        }
                        if (item.namaRuangan) {
                            ruangan = item.namaRuangan;
                        }
                        if (item.noRuangan) {
                            noRuangan = item.noRuangan;
                        }


                        table += "<tr>" +
                                "<td>" + pelayanan + "</td>" +
                                "<td>" + item.statusPeriksa + "</td>" +
                                "<td>" + ket + "</td>" +
                                "<td>" + ruangan + "</td>" +
                                "<td>" + noRuangan + "</td>" +
                                "</tr>"
                    });
                }
            });

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
            $('#det_riwayat').html(table);
            $('#modal-detail-pasien').modal('show');

        }, 100)
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>