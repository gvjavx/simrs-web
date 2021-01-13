<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/StudyAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SppdAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AbsensiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>

    <style>
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_biodata'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >
<%@ include file="/pages/common/headerNav.jsp" %>

<div id="modal-menu" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:1200px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Menu Biodata</h4>
            </div>
            <%--MENU-tab--%>
            <input id="menu-nip" readonly="true" style="display: none;">
            <ul class="nav nav-tabs">
                <li class=active"><a href="#menu-payroll">Payroll</a></li>
                <li><a href="#menu-absensi">Absensi</a></li>
                <li><a href="#menu-cuti">Cuti</a></li>
            </ul>

            <h4 class="menu-title"></h4>

            <%--PAYROLL--%>
            <div id="menu-payroll" class="modal-body" >
                <table style="width: 100%;" id="tabelPayroll" class="tabelPayroll table table-bordered"></table>
            </div>

            <%--ABSENSI--%>
            <form class="form-horizontal" id="formGaji">
                <div class="form-group">
                    <label class="control-label col-sm-1" >Bulan</label>
                    <input type="text" id="nipAbsensi" style="display:none;">
                    <div class="col-sm-4">
                        <select class="form-control" id="bulanAbsensi">
                            <option value="01"> Januari </option>
                            <option value="02"> Februari </option>
                            <option value="03"> Maret </option>
                            <option value="04"> April </option>
                            <option value="05"> Mei </option>
                            <option value="06"> Juni </option>
                            <option value="07"> Juli </option>
                            <option value="08"> Agustus </option>
                            <option value="09"> September </option>
                            <option value="10"> Oktober </option>
                            <option value="11"> Nopember </option>
                            <option value="12"> Desember </option>
                        </select>
                    </div>
                    <label class="control-label col-sm-1" >Tahun</label>
                    <div class="col-sm-3">
                        <select id="tahunAbsensi" class="form-control" ></select>
                    </div>
                    <div class="col-sm-3">
                        <a type="button" class="btn btn-primary" id="btnCariAbsensi" ><i class="fa fa-search"></i> Cari</a>
                    </div>
                </div>
            </form>
            <table style="width: 100%;" class="tableAbsensi table table-bordered">
            </table>

            <%--CUTI--%>
            <table style="width: 100%;" class="tableCuti table table-bordered">
            </table>

            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<%--<div id="modal-payroll" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:1200px;">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<table style="width: 100%;" id="tabelPayroll" class="tabelPayroll table table-bordered"></table>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<div id="modal-jabatan" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:800px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <table style="width: 100%;" id="tabelDetailJabatan" class="tabelDetailJabatan table table-bordered"></table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modal-pendidikan" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:700px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <table style="width: 100%;" class="tablePendidikan table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<%--<div id="modal-absensi" class="modal fade" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:500px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<form class="form-horizontal" id="formGaji">--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-1" >Bulan</label>--%>
                        <%--<input type="text" id="nipAbsensi" style="display:none;">--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<select class="form-control" id="bulanAbsensi">--%>
                                <%--<option value="01"> Januari </option>--%>
                                <%--<option value="02"> Februari </option>--%>
                                <%--<option value="03"> Maret </option>--%>
                                <%--<option value="04"> April </option>--%>
                                <%--<option value="05"> Mei </option>--%>
                                <%--<option value="06"> Juni </option>--%>
                                <%--<option value="07"> Juli </option>--%>
                                <%--<option value="08"> Agustus </option>--%>
                                <%--<option value="09"> September </option>--%>
                                <%--<option value="10"> Oktober </option>--%>
                                <%--<option value="11"> Nopember </option>--%>
                                <%--<option value="12"> Desember </option>--%>
                            <%--</select>--%>
                        <%--</div>--%>

                        <%--<label class="control-label col-sm-1" >Tahun</label>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<select id="tahunAbsensi" class="form-control" ></select>--%>
                        <%--</div>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<a type="button" class="btn btn-primary" id="btnCariAbsensi" ><i class="fa fa-search"></i> Cari</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</form>--%>
                <%--<table style="width: 100%;" class="tableAbsensi table table-bordered">--%>
                <%--</table>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<div id="modal-sppd" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1000px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <table style="width: 100%;" class="sppdTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<%--<div id="modal-cuti" class="modal fade" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:400px;">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<table style="width: 100%;" class="tableCuti table table-bordered">--%>
                <%--</table>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal">Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<div id="modal-detail" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:550px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <div class="col-sm-12">
                            <img style="padding-left: 10px" align="center" width="150px" height="170px" id="detailImg"
                                 src="" class="img-rounded" alt="">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailNip" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailNama" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tempat Lahir : </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailTempatLahir" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Lahir : </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailTanggalLahir" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Gender : </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailGender" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Agama : </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailAgama" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Status Keluarga: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailStatusKeluarga" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jumlah Anak : </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailJumlahAnak" >
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Masuk: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailTanggalMasuk" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Kary Tetap: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailTanggalAktif" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" style="padding-left: 0px" >Tanggal Pensiun: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailTanggalPensiun" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Masa Kerja: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailMasaKerja" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailUnit" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Bagian: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailBagian" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailJabatan" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >PJS: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailPjs" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NPWP: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailNPWP" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >No KTP: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailKtp" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Alamat: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailAlamat" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Status Pegawai: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailTipePegawai" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Status Giling: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailStatusGiling" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Golongan: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailGolongan" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Poin: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailPoin" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Status Pegawai: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailStatusPegawai" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Dana Pensiun: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailDanaPensiun" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Zakat Profesi: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailZakat" >
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama Bank : </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailNamaBank" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Cabang Bank: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailCabangBank" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >No Rek.: </label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="detailNoRekBank" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-sppd-detail" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1400px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve SPPD</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Dinas : </label>
                        <div class="col-sm-3">
                            <s:select list="#{'LN':'Luar Negeri'}" id="dinas1" name="sppd.dinas" disabled="true"
                                      headerKey="DN" headerValue="Dalam Negeri" cssClass="form-control" />

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Berangkat : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tanggalBerangkat1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Tanggal Kembali : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tanggalKembali1" name="nip">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-3" >Berangkat Dari: </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="berangkatDari1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Tujuan : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tujuan1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Berangkat Via : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="berangkatVia1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Kembali Via : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="kembaliVia1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keperluan : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" readonly class="form-control" id="keperluan1" name="nip"></textarea>

                        </div>
                    </div>

                </form>
                <center>
                    <h4><b>PERSON</b></h4>
                    <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                    </table>
                    <br>
                    <h4><b>REROUTE</b></h4>
                    <table style="width: 100%;" id="sppdRerouteTable" class="sppdRerouteTable table table-bordered">
                    </table>
                    <br>
                    <h4><b>DOCUMENT</b></h4>
                    <table style="width: 30%;" id="sppdDocTable" class="sppdDocTable table table-bordered">
                    </table>
                </center>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Biodata
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="biodataForm" method="post"  theme="simple" namespace="/biodata" action="search_biodata.action" cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>


                        <table>
                            <tr>
                                <td width="10%" align="center">
                                    <%@ include file="/pages/common/message.jsp" %>
                                </td>
                            </tr>
                        </table>

                        <table >
                            <tr>
                                <td>
                                    <label class="control-label"><small>NIP :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="biodataId" name="biodata.Nip" required="false"  cssClass="form-control"/>
                                    </table>
                                </td>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    // var prov = document.getElementById("provinsi1").value;
                                    $('#biodataId').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};

                                            var data = [];
                                            var unit1 = $('#branchId').val();

                                            if (unit1==""){
//                                                dwr.engine.setAsync(false);
//                                                MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
//                                                    data = listdata;
//                                                });
//                                                $.each(data, function (i, item) {
//                                                    var labelItem =item.nip+ " || "+ item.namaPegawai;
//                                                    var labelNip = item.nip;
//                                                    mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem,
//                                                        branchId : item.branch, divisiId: item.divisi, positionId : item.positionId,
//                                                        golonganId : item.golonganId, point : item.point, tipePegawaiId : item.tipePegawai,
//                                                        statusPegawaiId: item.statusPegawai};
//                                                    functions.push(labelItem);
//                                                });
//                                                process(functions);
                                                alert("unit is empty");
                                                $('#biodataName').val("");
                                                $('#biodataId').val("");
                                            }else {
                                                dwr.engine.setAsync(false);
                                                console.log(unit1);
                                                CutiPegawaiAction.initComboAllPersonil(query, unit1, function (listdata) {
                                                    data = listdata;
                                                });

                                                $.each(data, function (i, item) {
                                                    var labelItem = item.nip+" "+item.namaPegawai;
                                                    mapped[labelItem] = {id: item.nip, label: labelItem,nama:item.namaPegawai};
                                                    functions.push(labelItem);
                                                });

                                                process(functions);
                                            }

                                        },

                                        updater: function (item) {
//                                            var selectedObj = mapped[item];
//                                            var namaAlat = selectedObj.id;
//                                            document.getElementById("biodataName").value = selectedObj.pegawai;
//                                            $('#branchId').val(selectedObj.branchId).change();
//                                            $('#departmentId').val(selectedObj.divisiId).change();
//                                            $('#tipePegawaiId').val(selectedObj.tipePegawai).change();
//                                            return namaAlat;

                                            var selectedObj = mapped[item];
                                            var namaMember = selectedObj.label;
                                            document.getElementById("biodataName").value = selectedObj.nama;
                                            return selectedObj.id;
                                        }
                                    });
                                </script>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Name :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="biodataName" name="biodata.namaPegawai" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:if test='biodata.branch == "KP"'>
                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="biodata.branch"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </s:if>
                                        <s:else>
                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="biodata.branch" disabled="true"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                            <s:hidden id="branchId" name="biodata.branch"/>
                                        </s:else>

                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Bidang :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="golongan" name="biodata.divisi"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Jumlah Anak :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="jmlAnak" name="biodata.jumlahAnak" type="number" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tipe Pegawai :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboTipe" namespace="/tipepegawai" name="searchTipePegawai_tipepegawai"/>
                                        <s:select list="#initComboTipe.listComboTipePegawai" id="tipePegawaiId" name="biodata.tipePegawai"
                                                  listKey="tipePegawaiId" listValue="tipePegawaiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Status :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="biodata.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>

                        </table>



                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="biodataForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-success dropdown-toggle"
                                                    data-toggle="dropdown" style="height: 34px">
                                                <i class="fa fa-plus"></i> Add Biodata
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="add_biodata.action?tipe=karyawan">
                                                    <i class="fa fa-user-plus"></i>Karyawan Kantor</a></li>
                                                <li><a href="add_biodata.action?tipe=dokter">
                                                    <i class="fa fa-user-plus"></i>Dokter Tamu</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_biodata"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="90%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="400" width="600" autoOpen="false"
                                                   title="Biodata">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfbiodata" value="#session.listOfResultBiodata" scope="request" />
                                        <display:table name="listOfbiodata" class="table table-condensed table-striped table-hover listOfbiodata"
                                                       requestURI="paging_displaytag_biodata.action" export="true" id="row" pagesize="40" style="font-size:10">

                                            <s:if test='%{#attr.row.flagCutiDiluarTanggungan == "Y"}'>
                                                <display:column class="bg-danger" media="html" title="Menu">
                                                    <div class="btn-group">
                                                        <button type="button" class="btn btn-info dropdown-toggle"
                                                                data-toggle="dropdown" style="height: 34px">
                                                            <i class="fa fa-id-badge"></i> Action
                                                            <span class="caret"></span>
                                                            <span class="sr-only">Toggle Dropdown</span>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-menu">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"> Menu
                                                                </a>
                                                            </li>
                                                            <s:if test="#attr.row.flagYes">
                                                                <li>
                                                                    <s:a action="edit_biodata.action">
                                                                        <s:param name="id"><s:property value="#attr.row.nip" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit"> Edit
                                                                    </s:a>
                                                                </li>
                                                            </s:if>
                                                            <li>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-payroll">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"> Payroll
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-absensi">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"> Absensi
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-cuti">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"> Cuti
                                                                </a>
                                                            </li>
                                                            <s:if test="#attr.row.flagYes">
                                                                <li>
                                                                    <s:a action="delete_biodata.action">
                                                                        <s:param name="id"><s:property value="#attr.row.nip"/> </s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/> </s:param>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"> Detail
                                                                    </s:a>
                                                                </li>
                                                            </s:if>
                                                            <li>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-print">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_lup"> Print
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </display:column>

                                                <display:column class="bg-danger" property="nip" sortable="true" title="NIP" />
                                                <display:column class="bg-danger" property="nipLama" sortable="true" title="NIP Lama"/>
                                                <display:column class="bg-danger" property="namaPegawai" sortable="true" title="Nama Pegawai" />
                                                <display:column class="bg-danger" property="branchName" sortable="true" title="Unit"/>
                                                <display:column class="bg-danger" property="divisiName" sortable="true" title="Departmen"/>
                                                <display:column class="bg-danger" property="bagianName" sortable="true" title="Bagian"/>
                                                <display:column class="bg-danger" property="positionName" sortable="true" title="Jabatan"/>
                                                <display:column class="bg-danger" property="tipePegawaiName" sortable="true" title="Tipe Pegawai"/>
                                                <display:column class="bg-danger" property="stTanggalLahir" sortable="true" title="Tanggal Lahir"/>
                                                <display:column class="bg-danger" property="stTanggalAktif" sortable="true" title="Tanggal Aktif"/>
                                                <display:column class="bg-danger" property="stTanggalPensiun" sortable="true" title="Tanggal Pensiun"/>
                                                <display:column class="bg-danger" property="jumlahAnak" sortable="true" title="Jumlah Anak"/>
                                                <display:column class="bg-danger" property="pendidikanTerakhir" sortable="true" title="Pendidikan Terakhir"/>
                                            </s:if>
                                            <s:else>
                                                <%--RAKA-try start--%>
                                                <display:column media="html" title="Menu">
                                                    <div class="btn-group">
                                                        <button type="button" class="btn btn-info dropdown-toggle"
                                                                data-toggle="dropdown" style="height: 34px">
                                                            <i class="fa fa-id-badge"></i> Action
                                                            <span class="caret"></span>
                                                            <span class="sr-only">Toggle Dropdown</span>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-menu">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"> Menu
                                                                </a>
                                                            </li>
                                                            <s:if test="#attr.row.flagYes">
                                                                <li>
                                                                    <s:a action="edit_biodata.action">
                                                                        <s:param name="id"><s:property value="#attr.row.nip" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit"> Edit
                                                                    </s:a>
                                                                </li>
                                                            </s:if>
                                                            <li>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-payroll">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"> Payroll
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-absensi">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"> Absensi
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-cuti">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"> Cuti
                                                                </a>
                                                            </li>
                                                            <s:if test="#attr.row.flagYes">
                                                                <li>
                                                                    <s:a action="delete_biodata.action">
                                                                        <s:param name="id"><s:property value="#attr.row.nip"/> </s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/> </s:param>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"> Detail
                                                                    </s:a>
                                                                </li>
                                                            </s:if>
                                                            <li>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-print">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_lup"> Print
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </display:column>
                                                <%--RAKA-try end--%>

                                                <%--RAKA-bu--%>
                                                <%--<display:column media="html" title="Edit">--%>
                                                <%--<s:if test="#attr.row.flagYes">--%>
                                                <%--<s:a action="edit_biodata.action">--%>
                                                <%--<s:param name="id"><s:property value="#attr.row.nip" /></s:param>--%>
                                                <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">--%>
                                                <%--</s:a>--%>
                                                <%--</s:if>--%>
                                                <%--</display:column>--%>

                                                <%--<display:column media="html" title="Delete" style="text-align:center;font-size:9">--%>
                                                <%--<s:if test="#attr.row.flagYes">--%>
                                                <%--<s:a action="delete_biodata.action">--%>
                                                <%--<s:param name="id"><s:property value="#attr.row.nip" /></s:param>--%>
                                                <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">--%>
                                                <%--</s:a>--%>
                                                <%--</s:if>--%>
                                                <%--</display:column>--%>

                                                <%--<display:column style="text-align:center;" media="html" title="Jabatan">--%>
                                                <%--<a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-jabatan">--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">--%>
                                                <%--</a>--%>
                                                <%--</display:column>--%>

                                                <%--RAKA-bu--%>
                                                <%--<display:column style="text-align:center;" media="html" title="Payroll">--%>
                                                <%--<a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-payroll">--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">--%>
                                                <%--</a>--%>
                                                <%--</display:column>--%>

                                                <%--<display:column style="text-align:center;" media="html" title="Pendidikan">--%>
                                                <%--<a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-pendidikan">--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">--%>
                                                <%--</a>--%>
                                                <%--</display:column>--%>

                                                <%--RAKA-bu--%>
                                                <%--<display:column style="text-align:center;" media="html" title="Absensi">--%>
                                                <%--<a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-absensi">--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">--%>
                                                <%--</a>--%>
                                                <%--</display:column>--%>

                                                <%--<display:column style="text-align:center;" media="html" title="SPPD">
                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-sppd">
                                                        <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                    </a>
                                                </display:column>--%>

                                                <%--RAKA-bu--%>
                                                <%--<display:column style="text-align:center;" media="html" title="Cuti">--%>
                                                <%--<a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-cuti">--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">--%>
                                                <%--</a>--%>
                                                <%--</display:column>--%>

                                                <%--RAKA-bu--%>
                                                <%--<display:column style="text-align:center;" media="html" title="Detail">--%>
                                                <%--<s:if test="#attr.row.flagYes">--%>
                                                <%--<s:a action="delete_biodata.action">--%>
                                                <%--<s:param name="id"><s:property value="#attr.row.nip"/> </s:param>--%>
                                                <%--<s:param name="flag"><s:property value="#attr.row.flag"/> </s:param>--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">--%>
                                                <%--</s:a>--%>
                                                <%--</s:if>--%>
                                                <%--</display:column>--%>

                                                <%--<display:column style="text-align:center;" media="html" title="Detail">--%>
                                                <%--<a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-detail">--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">--%>
                                                <%--</a>--%>
                                                <%--</display:column>--%>

                                                <%--RAKA-bu--%>
                                                <%--<display:column style="text-align:center;" media="html" title="Print">--%>
                                                <%--<a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-print">--%>
                                                <%--<img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_lup">--%>
                                                <%--</a>--%>
                                                <%--</display:column>--%>


                                                <display:column property="nip" sortable="true" title="NIP" />
                                                <display:column property="nipLama" sortable="true" title="NIP Lama"/>
                                                <display:column property="namaPegawai" sortable="true" title="Nama Pegawai" />
                                                <display:column property="branchName" sortable="true" title="Unit"/>
                                                <display:column property="divisiName" sortable="true" title="Departmen"/>
                                                <display:column property="bagianName" sortable="true" title="Bagian"/>
                                                <display:column property="positionName" sortable="true" title="Jabatan"/>
                                                <%--<display:column property="gender" sortable="true" title="Jenis Kelamin" />--%>
                                                <%--<display:column property="profesiName" sortable="true" title="Profesi" />--%>
                                                <display:column property="tipePegawaiName" sortable="true" title="Tipe Pegawai"/>
                                                <display:column property="stTanggalLahir" sortable="true" title="Tanggal Lahir"/>
                                                <display:column property="stTanggalAktif" sortable="true" title="Tanggal Aktif"/>
                                                <display:column property="stTanggalPensiun" sortable="true" title="Tanggal Pensiun"/>
                                                <display:column property="jumlahAnak" sortable="true" title="Jumlah Anak"/>
                                                <display:column property="pendidikanTerakhir" sortable="true" title="Pendidikan Terakhir"/>

                                                <%--<display:column property="strLastUpdate" sortable="true" title="Last update"  />--%>
                                                <%--<display:column property="lastUpdateWho" sortable="true" title="Last update who"/>--%>
                                            </s:else>
                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </s:form>
                </td>
            </tr>
        </table>

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

<script>
    $(document).ready(function() {
        var d = new Date();
        var n = d.getFullYear();

        $('#tahunAbsensi').empty();
        for(a = n-5 ; a <= (n + 5) ; a++){
            $('#tahunAbsensi').append($("<option></option>")
                    .attr("value", a)
                    .text(a));
        }

        $('#btnCariAbsensi').click(function() {
            var bulan = document.getElementById("bulanAbsensi").value;
            var tahun = document.getElementById("tahunAbsensi").value;
            var nip = document.getElementById("nipAbsensi").value;

            $('.tableAbsensi').find('tbody').remove();
            $('.tableAbsensi').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";

            var blnThn = tahun +"-"+bulan;
            AbsensiAction.cariAbseni(nip, blnThn, function(listdata) {
                tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
             "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>"+
             "<th style='text-align: center; background-color:  #3c8dbc''>In</th>"+
             "<th style='text-align: center; background-color:  #3c8dbc''>Out</th>"+
             "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
             "</tr></thead>";
             var i = i ;
                $.each(listdata, function (i, item) {
                    var jamMasuk = "-";
                    var jamKeluar = "-";
                    var statusName = "-";

                    if(item.jamMasuk != null){
                        jamMasuk = item.jamMasuk;
                    }
                    if(item.jamKeluar != null){
                        jamKeluar = item.jamKeluar;
                    }
                    if(item.statusName != null){
                        statusName = item.statusName;
                    }

                    tmp_table += '<tr  style="font-size: 12px;" ">' +
                                    '<td >' + item.stTanggal+ '</td>' +
                                    '<td align="center">' + jamMasuk+ '</td>' +
                                    '<td align="center">' + jamKeluar  + '</td>' +
                                    '<td align="center">' + statusName + '</td>' +
                                    "</tr>";
                });
                $('.tableAbsensi').append(tmp_table);
             });
        });
    });

    $('.listOfbiodata').on('click', '.item-jabatan', function(){
        var nip = $(this).attr('data');
        
        $('.tabelDetailJabatan').find('tbody').remove();
        $('.tabelDetailJabatan').find('thead').remove();
        $('.tabelDetailJabatan').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        BiodataAction.historyJabatan(nip, function(listdata) {
            tmp_table = "<thead style='color: white; font-size: 15px'><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bidang/Divisi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Unit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>PJS</th>"+
//                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Masuk</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Keluar</th>"+
                    "</tr></thead>";
            var i = i ;
            var totalPoin = 0;
            $.each(listdata, function (i, item) {
                var bidang = "-";
                var branchName = "-";
                var positionName = "-";
                var status = "-";
                var pjs = "-";
//                var tahun = "-";
                var tglMasuk = "-";
                var tglKeluar = "-";

                if(item.bidangName!= null){
                    bidang = item.bidangName;
                }

//                if(item.tahun != null){
//                    tahun = item.tahun;
//                }
                if(item.tanggal != null){
                    tglMasuk = item.tanggal;
                }
                if (item.tanggalKeluar != null){
                    tglKeluar = item.tanggalKeluar;
                }

                if(item.branchName!= null){
                    branchName = item.branchName;
                }
                if(item.positionName!= null){
                    positionName = item.positionName;
                }
                if(item.status!= null){
                    status = item.status;
                }
                if(item.pjsFlag!= null){
                    if(item.pjsFlag == "Y"){
                        pjs = "Iya";
                    }else{
                        pjs = 'Tidak';
                    }
                }
                tmp_table += '<tr  style="font-size: 12px">' +
                        '<td >' + bidang + '</td>' +
                        '<td >' + branchName+ '</td>' +
                        '<td >' + positionName+ '</td>' +
                        '<td >' + status+ '</td>' +
                        '<td >' + pjs+ '</td>' +
//                        '<td >' + tahun+ '</td>' +
                        '<td >' + tglMasuk+ '</td>' +
                        '<td >' + tglKeluar+ '</td>' +
                        "</tr>";
            });
            $('.tabelDetailJabatan').append(tmp_table);
        });

        $('#modal-jabatan').find('.modal-title').text('Jenjang Karir');
        $('#modal-jabatan').modal('show');
    });

    $('.listOfbiodata').on('click', '.item-payroll', function(){
        var nip = $(this).attr('data');

        $('.tabelPayroll').find('tbody').remove();
        $('.tabelPayroll').find('thead').remove();
        $('.tabelPayroll').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        BiodataAction.searchPayroll(nip, function(listdata) {
            tmp_table = "<thead style='color: white; font-size: 15px'><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Download</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Gaji Kotor</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Potongan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Pph</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var link = "<s:property value="appname" />payroll/printReportPayroll_payroll.action?id=" + item.payrollId + "&tipe=PR";
                tmp_table += '<tr  style="font-size: 12px">' +
                        '<td ><a href="'+link+'" >Download</a></td>' +
                        '<td >' + item.bulan+ '</td>' +
                        '<td >' + item.tahun+ '</td>' +
                        '<td >' + item.totalA+ '</td>' +
                        '<td >' + item.totalB+ '</td>' +
                        '<td >' + item.pphGaji+ '</td>' +
                        "</tr>";
            });
            $('.tabelPayroll').append(tmp_table);
        });

        $('#modal-payroll').find('.modal-title').text('Payroll');
        $('#modal-payroll').modal('show');
    });


    $('.listOfbiodata').on('click', '.item-pendidikan', function(){
        var nip = $(this).attr('data');

        $('.tablePendidikan').find('tbody').remove();
        $('.tablePendidikan').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        StudyAction.searchData(nip, function(listdata) {

            tmp_table = "<thead style='font-size: 15px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Akhir</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var typeStudy = "-";
                var studyName = "-";
                var tahunAwal = "-";
                var tahunAkhir = "-";

                if(item.typeStudy != null){
                    typeStudy = item.typeStudy;
                }
                if(item.studyName != null){
                    studyName = item.studyName;
                }
                if(item.tahunAwal != null){
                    tahunAwal = item.tahunAwal;
                }
                if(item.tahunAkhir != null){
                    tahunAkhir = item.tahunAkhir;
                }

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td >' + typeStudy + '</td>' +
                        '<td >' + studyName + '</td>' +
                        '<td align="center">' + tahunAwal + '</td>' +
                        '<td align="center">' + tahunAkhir + '</td>' +
                        "</tr>";
            });
            $('.tablePendidikan').append(tmp_table);
        });
        
        $('#modal-pendidikan').find('.modal-title').text('Riwayat Pendidikan');
        $('#modal-pendidikan').modal('show');
    });

    $('.listOfbiodata').on('click', '.item-detail', function(){
        var nip = $(this).attr('data');

        BiodataAction.detailBiodata(nip, function(listdata) {
            $('#detailImg').attr('src', listdata.pathFoto);
            $('#detailImg').attr('alt', listdata.namaPegawai);

            $('#detailNip').val(listdata.nip);
            $('#detailNama').val(listdata.namaPegawai);
            $('#detailTempatLahir').val(listdata.tempatLahir);
            $('#detailTanggalLahir').val(listdata.stTanggalLahir);
            $('#detailGender').val(listdata.genderName);
            $('#detailAgama').val(listdata.agama);
            $('#detailStatusKeluarga').val(listdata.statusKeluargaName);
            $('#detailJumlahAnak').val(listdata.jumlahAnak);

            $('#detailTanggalMasuk').val(listdata.stTanggalMasuk);
            $('#detailTanggalAktif').val(listdata.stTanggalAktif);
            $('#detailTanggalPensiun').val(listdata.stTanggalPensiun);
            $('#detailMasaKerja').val(listdata.masaKerja);

            $('#detailUnit').val(listdata.branchName);
            $('#detailBagian').val(listdata.divisiName);
            $('#detailJabatan').val(listdata.positionName);
            $('#detailPjs').val(listdata.pjs);
            $('#detailNPWP').val(listdata.npwp);
            $('#detailKtp').val(listdata.noKtp);

            $('#detailAlamat').val(listdata.alamat );
            $('#detailTipePegawai').val(listdata.tipePegawaiName);

            $('#detailStatusGiling').val(listdata.statusGilingName);
            $('#detailGolongan').val(listdata.golonganName);

            $('#detailStatusPegawai').val(listdata.statusPegawaiName);
            $('#detailDanaPensiun').val(listdata.danaPensiun);

            $('#detailNamaBank').val(listdata.namaBank);
            $('#detailCabangBank').val(listdata.cabangBank);
            $('#detailNoRekBank').val(listdata.noRekBank);
        });

        $('#modal-detail').find('.modal-title').text('Biodata Detail');
        $('#modal-detail').modal('show');
    });

    $('.listOfbiodata').on('click', '.item-print', function(){
        var nip = $(this).attr('data');

        if (confirm('Print CV ?')) {
            window.location.href = 'printReportBiodata_biodata?id='+nip;
        }
    });

    //RAKA-start
    $('.listOfBiodata').on('click', '.item-menu', function(){
        var nip = $(this).attr('data');

        $("#model-menu").find("#menu-nip").val(nip);
        $("#modal-menu").modal("show");

    });


    $("#modal-menu nav-tabs a").click(function() {
        var nip = $("#menu-nip").val();
        var target = $(this).attr('href');

        if(target == "#menu-payroll") {
            menuPayroll(nip);
        }else if (target == "#menu-absensi") {
            menuAbsensi(nip);
        }else if(target == "#menu-cuti") {
            menuCuti(nip);
        }
        $(this).tab('show');
    });

    function menuPayroll(nip){
        $('.tabelPayroll').find('tbody').remove();
        $('.tabelPayroll').find('thead').remove();
        $('.tabelPayroll').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        BiodataAction.searchPayroll(nip, function(listdata) {
            tmp_table = "<thead style='color: white; font-size: 15px'><tr class='active'>"+
                "<th style='text-align: center; background-color:  #3c8dbc'>Download</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Gaji Kotor</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Potongan</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Pph</th>"+
                "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var link = "<s:property value="appname" />payroll/printReportPayroll_payroll.action?id=" + item.payrollId + "&tipe=PR";
                tmp_table += '<tr  style="font-size: 12px">' +
                    '<td ><a href="'+link+'" >Download</a></td>' +
                    '<td >' + item.bulan+ '</td>' +
                    '<td >' + item.tahun+ '</td>' +
                    '<td >' + item.totalA+ '</td>' +
                    '<td >' + item.totalB+ '</td>' +
                    '<td >' + item.pphGaji+ '</td>' +
                    "</tr>";
            });
            $('.tabelPayroll').append(tmp_table);
        });

        $(".menu-title").text("Payroll");
    }

    function menuAbsensi(nip){
        $('#nipAbsensi').val(nip);
        $('.tableAbsensi').find('tbody').remove();
        $('.tableAbsensi').find('thead').remove();


        $('.menu-title').text('Absensi');
    }

    function menuCuti(nip){
        $('.tableCuti').find('tbody').remove();
        $('.tableCuti').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        CutiPegawaiAction.sisaCuti(nip, function(listdata) {
            tmp_table = "<thead style='font-size: 15px; color: white' ><tr class='active'>"+
                "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc'>Nama Cuti</th>"+
                "<th style='text-align: center; background-color:  #3c8dbc''>Sisa Cuti(Hari)</th>"+
                "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td>' + item.cutiName + '</td>' +
                    '<td align="center">' + item.sisaCutiHari + '</td>' +
                    "</tr>";
            });
            $('.tableCuti').append(tmp_table);
        });

        $('#modal-menu').find('.menu-title').text('Cuti');
    }
    // RAKA-end

    $('.listOfbiodata').on('click', '.item-cuti', function(){
        var nip = $(this).attr('data');
        $('.tableCuti').find('tbody').remove();
        $('.tableCuti').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        CutiPegawaiAction.sisaCuti(nip, function(listdata) {
            tmp_table = "<thead style='font-size: 15px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Cuti</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Sisa Cuti(Hari)</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td>' + item.cutiName + '</td>' +
                        '<td align="center">' + item.sisaCutiHari + '</td>' +
                        "</tr>";
            });
            $('.tableCuti').append(tmp_table);
        });

        $('#modal-cuti').find('.modal-title').text('Cuti');
        $('#modal-cuti').modal('show');
    });

    $('.listOfbiodata').on('click', '.item-absensi', function(){
        var nip = $(this).attr('data');
        $('#nipAbsensi').val(nip);
        $('.tableAbsensi').find('tbody').remove();
        $('.tableAbsensi').find('thead').remove();


        $('#modal-absensi').find('.modal-title').text('Absensi');
        $('#modal-absensi').modal('show');
    });

    $('.listOfbiodata').on('click', '.item-sppd', function(){
        var nip = $(this).attr('data');
        $('.sppdTable').find('tbody').remove();
        $('.sppdTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        SppdAction.searchSppdPerson(nip, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Sppd ID</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Dinas</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Berangkat Dari</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tujuan Ke</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Berangkat</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Kembali</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Detail</th>"+
                    "</tr></thead>";

            $.each(listdata, function (i, item) {
                var atasan = '';
                var sdm = '';
                var closed = '';
                var tiket = '';

                if(item.sppdClosed){
                    var myDate = new Date(item.tanggalBerangkat);
                    var myDateKembali = new Date(item.tanggalKembali);
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i +1) + '</td>' +
                            '<td >' + item.sppdId + '</td>' +
                            '<td >' + item.dinas + '</td>' +
                            '<td align="center">' + item.berangkatDari+ '</td>' +
                            '<td align="center">' + item.tujuanKe+ '</td>' +
                            '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                            '<td align="center">' + (myDateKembali.getDate()) +' - '+ ("0" + (myDateKembali.getMonth() + 1)).slice(-2) +' - '+myDateKembali.getFullYear() + '</td>' +
                            '<td align="center"> <a class="item-sppd-detail" href="javascript:;"  data="'+item.sppdId+'"><i class="fa fa-book" style="font-size:19px"></i></a> </td>' +
                            "</tr>";
                }


            });
            $('.sppdTable').append(tmp_table);
        });

        $('#modal-sppd').find('.modal-title').text('SPPD');
        $('#modal-sppd').modal('show');
    });
    $('.sppdTable').on('click', '.item-sppd-detail', function(){
        var sppdId = $(this).attr('data');
        $('#sppdId1').val(sppdId);
        //alert(sppdId);
        SppdAction.searchSppd(sppdId, function(listdata) {
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalSppdBerangkat);
                var myDate1 = new Date(item.tanggalSppdKembali);
                $('#tanggalBerangkat1').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                $('#tanggalKembali1').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());

                $('#nama1').val(item.personName);
                $('#branchId1').val(item.branchId).change();
                $('#positionId1').val(item.positionId).change();
                $('#dinas1').val(item.dinas).change();
                $('#divisiId1').val(item.divisiId).change();
                $('#keperluan1').val(item.tugasSppd);
                $('#berangkatDari1').val(item.berangkatDari);
                $('#tujuan1').val(item.tujuanKe);
                $('#berangkatVia1').val(item.berangkatVia);
                $('#kembaliVia1').val(item.pulangVia);
                $('#notApprove').val(item.notApprovalNote);
                $('#approveAtasan').val(item.approvalName);
                $('#approveAtasanId').val(item.approvalId);
                loadPerson(sppdId, 'N');
                loadReroute(sppdId);
                loadDocSppd(sppdId);
            });
        });
        //alert( $('#branchId1').text);
        $('#modal-sppd-detail').find('.modal-title').text('Detail SPPD Person');
        $('#modal-sppd-detail').modal('show');
        $('#myForm').attr('action', 'atasan');
    });


    window.loadPerson =  function(id, status){
        //alert(nip);
        var branch         = $('select[name=branchText] option:selected').text();
        var divisi         = $('select[name=divisiText] option:selected').text();
        //alert(branch);
        $('.sppdPersonTable').find('tbody').remove();
        $('.sppdPersonTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchAnggota(id, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Branch</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Divisi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Position</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note For Not Approval</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval SDM Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note For Not Approval SDM</th>"+
                        /*"<th style='text-align: center; background-color:  #3c8dbc''>Nip Pengganti</th>"+*/
                    "<th style='text-align: center; background-color:  #3c8dbc''>Nama Pengganti</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var atasan = '';
                var sdm = '';

                if(item.sppdApproveSdm == true){
                    sdm = item.linkSdm ;
                }
                if(item.sppdApprove == true){
                    atasan = item.linkAtasan ;
                }

                tmp_table += '<tr  style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.personName + '</td>' +
                        '<td align="center">' + item.branchName + '</td>' +
                        '<td align="center">' + item.divisiName  + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + item.approvalName + '</td>' +
                        '<td align="center"><img src="' + atasan + '"</td>' +
                        '<td align="center">' + item.notApprovalNote + '</td>' +
                        '<td align="center">' + item.approvalSdmName+ '</td>' +
                        '<td align="center"><img src="' + sdm + '"</td>' +
                        '<td align="center">' + item.notApprovalSdmNote + '</td>' +
                        '<td align="center">' + item.pejabatSementaraNama + '</td>' +

                        "</tr>";
            });
            $('.sppdPersonTable').append(tmp_table);

        });
    }
    window.loadReroute =  function(sppdId){
        //alert(branch);
        $('.sppdRerouteTable').find('tbody').remove();
        $('.sppdRerouteTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchReroute(sppdId, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Reroute Dari</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Reroute Ke</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Dari Tanggal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Sampai Tanggal</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalRerouteDari);
                var myDate1 = new Date(item.tanggalRerouteKe);

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td >' + item.sppdPersonName + '</td>' +
                        '<td align="center">' + item.rerouteDari + '</td>' +
                        '<td align="center">' + item.rerouteKe  + '</td>' +
                        '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                        '<td align="center">' + (myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear() + '</td>' +
                        "</tr>";
            });
            $('.sppdRerouteTable').append(tmp_table);

        });
    }
    window.loadDocSppd =  function(sppdId){
        //alert(branch);
        $('.sppdDocTable').find('tbody').remove();
        $('.sppdDocTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchDoc(sppdId, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>View</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Keterangan</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center"> <a class="item-download" href="javascript:;"  data="'+item.sppdDocId+'"><i class="fa fa-download" style="font-size:19px"></i></a> </td>' +
                        '<td align="center">' + item.note + '</td>' +
                        "</tr>";
            });
            $('.sppdDocTable').append(tmp_table);
        });
    }
</script>