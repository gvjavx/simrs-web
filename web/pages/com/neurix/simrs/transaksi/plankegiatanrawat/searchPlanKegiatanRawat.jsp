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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PlanKegiatanRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RawatInapAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#rencana_kegiatan_rawat').addClass('active');
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
            Rencana Kegiatan Rawat
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Rencana Kegiatan Rawat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <%--<s:form id="permintaanForm" method="post" namespace="/planrawat" action="search_checkup.action"--%>
                            <%--theme="simple" cssClass="form-horizontal">--%>
                            <div class="form-group form-horizontal">
                                <div class="row">
                                    <label class="control-label col-sm-4">Id Detail Chcekup</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="search_iddetailcheckup" cssStyle="margin-top: 7px"
                                                     name="headerCheckup.idDetailCheckup" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group form-horizontal">
                                <div class="row">
                                    <label class="control-label col-sm-4" for="headerCheckup.idPasien">No. RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="idPasien" cssStyle="margin-top: 7px"
                                                     name="headerCheckup.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <%--<div class="form-group form-horizontal">--%>
                                <%--<div class="row">--%>
                                    <%--<label class="control-label col-sm-4">Poli</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:action id="initComboPoli" namespace="/checkup"--%>
                                                  <%--name="getComboPelayanan_checkup"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--list="#initComboPoli.listOfPelayanan" id="idPelayanan"--%>
                                                  <%--name="headerCheckup.idPelayanan" listKey="idPelayanan"--%>
                                                  <%--listValue="namaPelayanan"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <br>
                            <div class="form-group form-horizontal">
                                <label class="control-label col-sm-4"></label>
                                <div class="col-sm-6" style="margin-top: 7px">
                                    <button class="btn btn-primary" onclick="search()"><i class="fa fa-search"></i> Search</button>
                                    <%--<sj:submit type="button" cssClass="btn btn-success" formIds="permintaanForm"--%>
                                    <%--id="search" name="search"--%>
                                    <%--onClickTopics="showDialogLoading"--%>
                                    <%--onCompleteTopics="closeDialogLoading">--%>
                                    <%--<i class="fa fa-search"></i>--%>
                                    <%--Search--%>
                                    <%--</sj:submit>--%>
                                    <a type="button" class="btn btn-danger" href="initForm_planrawat.action">
                                        <i class="fa fa-refresh"></i> Reset
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien dirawat</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>No. Pendaftaran</td>
                                <td>No. RM</td>
                                <td>Nama Pasien</td>
                                <td>Nama Pelayanan</td>
                                <td>Diagnosa Terakhir</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body-list-plan">
                            <%--<s:iterator value="#session.listOfResult" status="listOfUsers">--%>
                            <%--<tr>--%>
                            <%--<td><s:property value="noCheckup"/></td>--%>
                            <%--<td><s:property value="idPasien"/></td>--%>
                            <%--<td><s:property value="nama"/></td>--%>
                            <%--<td><s:property value="namaPelayanan"/></td>--%>
                            <%--<td><s:property value="statusPeriksaName"/></td>--%>
                            <%--<td align="center">--%>
                            <%--<img border="0" class="hvr-grow" id="v_<s:property value="noCheckup"/>" src="<s:url value="/pages/images/search_flat.png"/>"--%>
                            <%--style="cursor: pointer; width: 25px; height: 25px" onclick="detail_pasien('<s:property value="noCheckup"/>')">--%>
                            <%--<s:url var="edit" namespace="/checkup" action="edit_checkup" escapeAmp="false">--%>
                            <%--<s:param name="id"><s:property value="noCheckup"/></s:param>--%>
                            <%--</s:url>--%>
                            <%--<s:a href="%{edit}">--%>
                            <%--<img border="0" class="hvr-grow" src="<s:url value="/pages/images/edit-flat-new.png"/>"--%>
                            <%--style="cursor: pointer; width: 25px; height: 25px">--%>
                            <%--</s:a>--%>
                            <%--</td>--%>
                            <%--</tr>--%>
                            <%--</s:iterator>--%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-view-plan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> View Daftar Rencana Rawat</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <table class="table table-striped table-bordered">
                            <thead id="head-view-plan">
                            <tr>
                                <td>Tgl Mulai</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body-view-plan">
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

<div class="modal fade" id="modal-add-plan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Rencana Rawat </h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <div class="col-md-2" style="margin-top: 7px;">
                        <label>Tanggal Plan :</label>
                    </div>
                    <div class="col-md-4" style="margin-top: 7px;">
                        <input type="date" class="form-control" id="tgl">
                        <input type="hidden" class="form-control" id="idPoli">
                    </div>
                </div>
                <br>

                <div style="margin-bottom:20px">
                    <button type="button" class="btn btn-success" onclick="showModalAdd('vitalsign')">
                        <i class="fa fa-plus"></i> Add Monitoring Vital Sign
                    </button>
                    <%--<button type="button" class="btn btn-info" onclick="showGrafVitalSign('<s:property value="rawatInap.idDetailCheckup"/>')">--%>
                    <%--<i class="fa fa-pie-chart"></i> View Graf--%>
                    <%--</button>--%>
                    <button type="button" class="btn btn-success" onclick="showModalAdd('cairan')">
                        <i class="fa fa-plus"></i> Add Monitoring Cairan
                    </button>
                    <button type="button" class="btn btn-success" onclick="showModalAdd('parenteral')">
                        <i class="fa fa-plus"></i> Add Obat Parenteral
                    </button>
                    <button type="button" class="btn btn-success" onclick="showModalAdd('nonparenteral')">
                        <i class="fa fa-plus"></i> Add Obat Non Parenteral
                    </button>
                </div>
                <br>

                <input type="hidden" id="idDetailCheckup">

                <h4> Vital Sign </h4>
                <table class="table table-bordered" style="width: 50%">
                    <thead>
                    <td>Waktu</td>
                    <td>Jam</td>
                    <td>Catatan Dokter</td>
                    </thead>
                    <tbody id="body-list-vital-sign">

                    </tbody>
                </table>
                <br>

                <h4> Monitoring Cairan </h4>
                <table class="table table-bordered" style="font-size:11px;">
                    <thead>
                    <td>Waktu</td>
                    <td>Macam Cairan</td>
                    <td>Melalui</td>
                    <td>Jumlah</td>
                    <td>Jam Mulai</td>
                    <td>Jam Selesai</td>
                    <td>Cek Tambahan Obat</td>
                    <td>Sisa</td>
                    <td>Jam Ukur Buang</td>
                    <td>Dari</td>
                    <td>Balance Cairan</td>
                    <td>Catatan Dokter</td>
                    </thead>
                    <tbody id="body-list-cairan">

                    </tbody>
                </table>
                <br>

                <h4> Obat Parenteral </h4>
                <table class="table table-bordered">
                    <thead>
                    <td>Waktu</td>
                    <td>Nama Obat</td>
                    <td>Cara Pemberian</td>
                    <td>Dosis</td>
                    <td>Skin Test</td>
                    <td>Waktu Pemberian</td>
                    <td>Catatan Dokter</td>
                    </thead>
                    <tbody id="body-list-perenteral">

                    </tbody>
                </table>
                <br>

                <h4> Obat Non Parenteral </h4>
                <table class="table table-bordered">
                    <thead>
                    <td>Waktu</td>
                    <td>Nama Obat</td>
                    <td>Dosis</td>
                    <td>Waktu Pemberian</td>
                    <td>Catatan Dokter</td>
                    </thead>
                    <tbody id="body-list-nonparenteral">

                    </tbody>
                </table>
                <br>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" onclick="savePlan()"><i class="fa fa-check"></i> Save
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
                    <div class="row">
                        <div class="col-md-3">
                            <label style="margin-top: 7px">Waktu</label>
                        </div>
                        <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" id="mvs_waktu">
                                <option val='pagi'>Pagi</option>
                                <option val='siang'>Siang</option>
                                <option val='malam'>Malam</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label style="margin-top: 7px">Catatan Dokter</label>
                        </div>
                        <div class="col-md-6">
                            <textarea class="form-control" style="margin-top: 7px" id="mvs_ket"></textarea>
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
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveToList('vitalsign')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
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
                            <label style="margin-top: 7px">Waktu</label>
                        </div>
                        <div class="col-md-8">
                            <select style="margin-top: 7px" class="form-control" id="mcr_waktu">
                                <option val='pagi'>Pagi</option>
                                <option val='siang'>Siang</option>
                                <option val='malam'>Malam</option>
                            </select>
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
                            <select style="margin-top: 7px" class="form-control" id="mcr_cek">
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
                            <label style="margin-top: 7px">Catatan Dokter</label>
                        </div>
                        <div class="col-md-8">
                            <textarea style="margin-top: 7px" class="form-control" id="mcr_ket"></textarea>
                        </div>
                    </div>
                </div>

                <br>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" onclick="saveToList('cairan')"><i class="fa fa-arrow-right"></i> Save
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
                            <label style="margin-top: 7px">Waktu</label>
                        </div>
                        <div class="col-md-8">
                            <select style="margin-top: 7px" class="form-control" id="nonpar_waktu">
                                <option val='pagi'>Pagi</option>
                                <option val='siang'>Siang</option>
                                <option val='malam'>Malam</option>
                            </select>
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
                            <label style="margin-top: 7px">Waktu Pemberian</label>
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
                            <label style="margin-top: 7px">Catatan Dokter</label>
                        </div>
                        <div class="col-md-8">
                            <textarea style="margin-top: 7px" class="form-control" name="name" rows="8" cols="80" id="nonpar_keterangan"></textarea>
                        </div>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveToList('nonparenteral')"><i class="fa fa-arrow-right"></i> Save
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
                            <label style="margin-top: 7px">Waktu</label>
                        </div>
                        <div class="col-md-8">
                            <select style="margin-top: 7px" class="form-control" id="par_waktu">
                                <option val='pagi'>Pagi</option>
                                <option val='siang'>Siang</option>
                                <option val='malam'>Malam</option>
                            </select>
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
                            <label style="margin-top: 7px">Waktu Pemberian</label>
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
                            <label style="margin-top: 7px">Catatan Dokter</label>
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
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveToList('parenteral')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript' src='<s:url value="/pages/dist/js/planrawat.js"/>'></script>
<script type='text/javascript'>

    var listOfVitalSign = [];
    var listOfCairan = [];
    var listOfParenteral = [];
    var listOfNonParenteral = [];

    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [year, month, day].join('-');
    }

    function search() {

        var idPasien = $("#idPasien").val();
        var idDetail = $("#search_iddetailcheckup").val();

        var arrJson = [];
        arrJson.push({"id_pasien":idPasien, "id_detail_checkup":idDetail, "id_pelayanan":""});
        var stJson = JSON.stringify(arrJson);

        PlanKegiatanRawatAction.getSearchKegiatanRawat(stJson, function (response) {
            if (response.length > 0){

                var str = "";
                $.each(response, function (i, item) {
                    str += "<tr>" +
                        "<td>" + item.idDetailCheckup + "</td>" +
                        "<td>" + item.idPasien + "</td>" +
                        "<td>" + item.namaPasien + "</td>" +
                        "<td>" + item.namaPelayanan + "</td>" +
                        "<td>" + item.diagnosa + "</td>" +
                        "<td align='center'>" +
                        "<button class='btn btn-primary' onclick=\"viewAddPlan('"+item.idDetailCheckup+"', '"+item.idPelayanan+"')\"><i class='fa fa-plus'></i></button> " +
                        "<button class='btn btn-primary' onclick=\"viewPlan('"+item.idDetailCheckup+"')\"><i class='fa fa-search'></i></button>" +
                        "</td>" +
                        "</tr>";
                });

                $("#body-list-plan").html(str);

            } else {

            }
        })
    }

    function viewPlan(idDetail){
        $("#modal-view-plan").modal('show');

        PlanKegiatanRawatAction.getListPlanKegiatan(idDetail, function (response) {

            var str = "";
            $.each(response, function (i, item) {
                str += "<tr>" +
                    "<td>"+ item.stTglMulai +"</td>" +
                    "<td align='center'><button class='btn btn-primary' onclick=\"showModalPlan('"+item.idDetailCheckup+"','"+item.stTglMulai+"', 'admin')\"><i class='fa fa-search'></i></button></td>" +
                    "</tr>";
            })

            $("#body-view-plan").html(str);

        })
    }

    function viewAddPlan(idDetail, idPoli) {
        setAllListNull();
        $("#idDetailCheckup").val(idDetail);
        $("#tgl").val(formatDate(Date.now()));
        $("#idPoli").val(idPoli);
        $("#modal-add-plan").modal('show');
    }

    function setAllListNull() {
        listOfCairan = [];
        listOfVitalSign = [];
        listOfParenteral = [];
        listOfNonParenteral = [];
        $("#body-list-vital-sign").html("");
        $("#body-list-cairan").html("");
        $("#body-list-perenteral").html("");
        $("#body-list-nonparenteral").html("");
    }

    function showModalAdd(param) {
        if (param == "vitalsign")
            $("#modal-add-vital-sign").modal('show');
        if (param == "cairan"){

            $("#mcr_mulai").timepicker();
            $("#mcr_selesai").timepicker();
            $("#mcr_buang").timepicker();

            $("#modal-add-cairan").modal('show');
        }
        if (param == "parenteral"){

            var idPoli = $("#idPoli").val();

            RawatInapAction.getListObatParenteral(idPoli, function(response){
                var str = "";
                $.each(response, function(i, item) {
                    str += "<option val=\'"+item.namaObat+"\'>"+item.namaObat+"</option>";
                });
                $("#select_obat_par").html(str);
            });

            $("#modal-add-pemberian-parenteral").modal('show');
        }
        if (param == "nonparenteral"){

            var idDetail = $("#idDetailCheckup").val();

            RawatInapAction.getListObatNonParenteral(idDetail, "%",  function(response){
                var str = "";
                $.each(response, function(i, item) {
                    str += "<option val=\'"+item.namaObat+"\'>"+item.namaObat+"</option>";
                });
                $("#select_obat_nonpar").html(str);
            });

            $("#modal-add-pemberian-non-parenteral").modal('show');
        }
    }

    function saveToList(param) {
        if(param == "vitalsign"){

            var jam = $("#mvs_jam").val();
            var waktu = $("#mvs_waktu").val();
            var ket = $("#mvs_ket").val();

            listOfVitalSign.push({"jam":jam, "waktu":waktu, "ket":ket});
            setToListTable(param);
        }
        if (param == "cairan"){

            var waktu = $("#mcr_waktu").val();
            var macam = $("#mcr_macam").val();
            var melalui = $("#mcr_melalui").val();
            var jumlah = $("#mcr_jumlah").val();
            var mulai = $("#mcr_mulai").val();
            var selesai = $("#mcr_selesai").val();
            var cek = $("#mcr_cek").val();
            var sisa = $("#mcr_sisa").val();
            var buang = $("#mcr_buang").val();
            var dari = $("#mcr_dari").val();
            var balance = $("#mcr_balance").val();
            var ket = $("#mcr_ket").val();

            listOfCairan.push({
                "waktu":waktu,
                "macam":macam,
                "melalui":melalui,
                "jumlah":jumlah,
                "mulai":mulai,
                "selesai":selesai,
                "cek":cek,
                "sisa":sisa,
                "buang":buang,
                "dari":dari,
                "balance":balance,
                "ket":ket
            });
            setToListTable(param);
        }
        if (param == "parenteral"){

            var waktu = $("#par_waktu").val();
            var obat = $("#select_obat_par").val();
            var nama = $("#select_obat_par").text();
            var cara = $("#par_cara").val();
            var dosis = $("#par_dosis").val();
            var skintes = $("#par_skintes").val();
            var waktupemberian = $("#select_waktu_par").val();
            var ket = $("#par_keterangan").val();

            listOfParenteral.push({
                "waktu":waktu,
                "obat":obat,
                "nama":nama,
                "cara":cara,
                "dosis":dosis,
                "skintes":skintes,
                "waktupemberian":waktupemberian,
                "ket":ket,
                "kat":param
            });
            setToListTable(param);
        }
        if (param == "nonparenteral"){

            var waktu = $("#nonpar_waktu").val();
            var obat = $("#select_obat_nonpar").val();
            var nama = $("#select_obat_nonpar").text();
            var dosis = $("#nonpar_dosis").val();
            var waktupemberian = $("#select_waktu_nonpar").val();
            var ket = $("#nonpar_keterangan").val();

            listOfNonParenteral.push({
                "waktu":waktu,
                "obat":obat,
                "nama":nama,
                "dosis":dosis,
                "waktupemberian":waktupemberian,
                "ket":ket,
                "kat":param
            });
            setToListTable(param);
        }
    }

    function setToListTable(param) {

        if(param == "vitalsign"){

            console.log(listOfVitalSign);

            $("#body-list-vital-sign").html("");
            if(listOfVitalSign.length > 0) {
                var str = "";
                $.each(listOfVitalSign, function (i, item) {
                    str += "<tr>" +
                        "<td>"+ item.waktu +"</td>" +
                        "<td>"+ item.jam +"</td>" +
                        "<td>"+ item.ket +"</td>" +
                        "</tr>"
                });
                $("#modal-add-vital-sign").modal('hide');
                $("#body-list-vital-sign").html(str);
            }
        }

        if(param == "cairan"){
            $("#body-list-cairan").html("");
            if(listOfCairan.length > 0) {
                var str = "";
                $.each(listOfCairan, function (i, item) {
                    str += "<tr>" +
                        "<td>"+ item.waktu +"</td>" +
                        "<td>"+ item.macam +"</td>" +
                        "<td>"+ item.melalui +"</td>" +
                        "<td>"+ item.jumlah +"</td>" +
                        "<td>"+ item.mulai +"</td>" +
                        "<td>"+ item.selesai +"</td>" +
                        "<td>"+ item.cek +"</td>" +
                        "<td>"+ item.sisa +"</td>" +
                        "<td>"+ item.buang +"</td>" +
                        "<td>"+ item.dari +"</td>" +
                        "<td>"+ item.balance +"</td>" +
                        "<td>"+ item.ket +"</td>" +
                        "</tr>"
                });
                $("#modal-add-cairan").modal('hide');
                $("#body-list-cairan").html(str);
            }
        }
        if(param == "parenteral"){
            $("#body-list-perenteral").html("");
            var str = "";
            $.each(listOfParenteral, function(i, item){
                str += "<tr>" +
                    "<td>"+ item.waktu +"</td>" +
                    "<td>"+ item.nama +"</td>" +
                    "<td>"+ item.cara +"</td>" +
                    "<td>"+ item.dosis +"</td>" +
                    "<td>"+ item.skintest +"</td>" +
                    "<td>"+ item.waktupemberian +"</td>" +
                    "<td>"+ item.ket +"</td>" +
                    "</tr>"
            });
            $("#modal-add-pemberian-parenteral").hide();
            $("#body-list-perenteral").html(str);
        }

        if(param == "nonparenteral"){
            $("#body-list-nonparenteral").html("");
            var str = "";
            $.each(listOfNonParenteral, function(i, item){
                str += "<tr>" +
                    "<td>"+ item.waktu +"</td>" +
                    "<td>"+ item.nama +"</td>" +
                    "<td>"+ item.dosis +"</td>" +
                    "<td>"+ item.waktupemberian +"</td>" +
                    "<td>"+ item.ket +"</td>" +
                    "</tr>"
            });
            $("#modal-add-pemberian-non-parenteral").hide();
            $("#body-list-nonparenteral").html(str);
        }
    }

    function savePlan(){

        var idDetail = $("#idDetailCheckup").val();
        var tglPlan = $("#tgl").val();

//        console.log(listOfVitalSign);

        var strVitalSign = JSON.stringify(listOfVitalSign);
        var strCairan = JSON.stringify(listOfCairan);
        var strParenteral = JSON.stringify(listOfParenteral);
        var strNonParenteral = JSON.stringify(listOfNonParenteral);

        if (idDetail != "" && tglPlan != ""){
            PlanKegiatanRawatAction.savePlanKegiatanRawat(idDetail, tglPlan, strVitalSign, strCairan, strParenteral, strNonParenteral, function(response) {
                if (response.status == "success"){
                    alert("success");
                    $("#modal-add-plan").modal('hide');
                    $("#search_iddetailcheckup").val(idDetail);
                    search();
                } else {
                    alert(response.msg);
                    $("#modal-add-plan").modal('hide');
                    $("#search_iddetailcheckup").val(idDetail);
                    search();
                }
            })
        } else {
            alert("kosong");
        }
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>