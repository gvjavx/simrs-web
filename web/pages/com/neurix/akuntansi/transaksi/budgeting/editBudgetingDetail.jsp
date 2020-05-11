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
        .modal { overflow-y: auto}
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BudgetingAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript'>

        function formatRupiah(angka) {
            if(angka != null && angka != ''){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            }else{
                return 0;
            }
        }
    </script>

    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>
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
            Akutansi
            <%--<small>e-HEALTH</small>--%>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Edit Budgeting Detail</h3>
                    </div>
                    <div class="box-body">

                        <table style="font-size: 15px; margin-bottom: 10px;" class="table">
                            <tbody>
                                <tr>
                                    <td>Parent COA </td>
                                    <td>:</td>
                                    <td> <s:property value="budgeting.kodeParent"></s:property> - <s:property value="budgeting.namaParent"></s:property></td>
                                </tr>
                                <tr>
                                    <td>Child COA </td>
                                    <td>:</td>
                                    <td> <s:property value="budgeting.kodeRekening"></s:property> - <s:property value="budgeting.namaKodeRekening"></s:property></td>
                                </tr>
                            </tbody>
                        </table>

                        <%--table informasi all coa--%>
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <table id="sortTable" class="table table-bordered table-striped" style="font-size: 15px;">
                                    <thead id="head-budgeting">
                                    <tr bgcolor="#90ee90">
                                        <td style="width: 20%">COA</td>
                                        <td align="center">Keterangan</td>
                                        <td align="center">Total</td>
                                        <td align="center">Selisih</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body-budgeting">
                                    <s:iterator value="#session.listOfCoa" status="listOfCoa" var="row">
                                        <tr>
                                            <td><s:property value="kodeRekening"/></td>
                                            <td><s:property value="namaKodeRekening"/></td>
                                            <td align="center"><script>document.write(formatRupiah('<s:property value="nilaiTotal"/>'))</script></td>
                                            <td align="center"><s:property value="selisih"/></td>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <%--FORM PENGISIAN--%>
                        <%--<s:form id="kasirjalanForm" method="post" namespace="/kasirjalan" action="search_kasirjalan.action" theme="simple" cssClass="form-horizontal">--%>
                        <hr>
                        <div class="form-group form-horizontal" style="margin-top: 20px;">
                            <div class="row">
                                <div class="col-md-12 col-md-offset-2">
                                    <div class="row">
                                        <label class="control-label col-sm-2">Divisi Id</label>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" id="divisiid"/>

                                            <script>
                                                $(document).ready(function() {
                                                    var functions, mapped;
                                                    $('#divisiid').typeahead({
                                                        minLength: 1,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};
                                                            var data = [];
                                                            dwr.engine.setAsync(false);
                                                            PositionAction.typeHeadPosition(query,function (listdata) {
                                                                data = listdata;
                                                            });
                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.kodering + " | " + item.positionName;
                                                                mapped[labelItem] = {
                                                                    kode : item.kodering,
                                                                    name :item.positionName,
                                                                    id : item.positionId
                                                                };
                                                                functions.push(labelItem);
                                                            });
                                                            process(functions);
                                                        },
                                                        updater: function (item) {
                                                            var selectedObj = mapped[item];
                                                            $("#namadivisi").val(selectedObj.name);
                                                            $("#positionid").val(selectedObj.id);
                                                            return selectedObj.kode;
                                                        }
                                                    });
                                                });
                                            </script>

                                        <%--<select class="form-control" id="sel-tahun" name="budgeting.tahun">--%>
                                                <%--<option value="2020">2020</option>--%>
                                                <%--<option value="2021">2021</option>--%>
                                                <%--<option value="2022">2022</option>--%>
                                                <%--<option value="2023">2023</option>--%>
                                            <%--</select>--%>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="control-label col-sm-2">Nama Divisi</label>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control" id="namadivisi" readonly/>
                                            <input type="hidden" class="form-control" id="positionid" readonly/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="control-label col-sm-2">QTY</label>
                                        <div class="col-sm-4">
                                            <input type="number" class="form-control" id="qty"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="control-label col-sm-2">Tarif (Satuan)</label>
                                        <div class="col-sm-4">
                                            <input type="number" class="form-control" id="nilai"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="control-label col-sm-2" id="label-tipe"></label>
                                        <div class="col-sm-4">
                                            <select class="form-control" id="sel-tipe">

                                            </select>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 col-md-offset-7" style="margin-top: 10px">
                                    <button class="btn btn-success" onclick="saveAdd()"><i class="fa fa-plus"></i> Add</button>
                                </div>
                            </div>
                        </div>

                        <%--</s:form>--%>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i>
                            <%--List Tutup Period <strong><span id="label-tahun"></span> - <span id="label-bulan"></span></strong> --%>
                        </h3>
                    </div>
                    <div class="box-body">

                        <%--<div class="alert alert-info alert-dismissable" id="alert-info">--%>
                        <%--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>--%>
                        <%--<strong>Info!</strong> Pilih Priode Kemudian Choose--%>
                        <%--</div>--%>

                        <div class="alert alert-success alert-dismissable" id="alert-success" style="display: none">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>Success!</strong> Berhasil Menyimpan data
                        </div>

                        <div class="alert alert-warning alert-dismissable" id="alert-error" style="display: none">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>Error!</strong><span id="error-msg"></span>
                        </div>

                        <%--table informasi quartal--%>
                        <s:if test='budgeting.tipe == "quartal"'>
                            <div class="row">
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Kuartal 1</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td style="width: 20%">Divisi Id</td>
                                            <td align="center">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "quartal1"'>
                                                <tr>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center"><button class="btn btn-sm btn-success" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button></td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Kuartal 2</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td style="width: 20%">Divisi Id</td>
                                            <td align="center">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "quartal2"'>
                                                <tr>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><s:property value="nilai"/></td>
                                                    <td align="center"><s:property value="subTotal"/></td>
                                                    <td align="center"><button class="btn btn-sm btn-success" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button></td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Kuartal 3</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td style="width: 20%">Divisi Id</td>
                                            <td align="center">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "quartal3"'>
                                                <tr>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><s:property value="nilai"/></td>
                                                    <td align="center"><s:property value="subTotal"/></td>
                                                    <td align="center"><button class="btn btn-sm btn-success" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button></td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Kuartal 4</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td style="width: 20%">Divisi Id</td>
                                            <td align="center">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "quartal4"'>
                                                <tr>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><s:property value="nilai"/></td>
                                                    <td align="center"><s:property value="subTotal"/></td>
                                                    <td align="center"><button class="btn btn-sm btn-success" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button></td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </s:if>

                        <%--table informasi quartal--%>
                        <s:if test='budgeting.tipe == "semester"'>
                            <div class="row">
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Semester 1</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td style="width: 20%">Divisi Id</td>
                                            <td align="center">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "semester1"'>
                                                <tr>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><s:property value="nilai"/></td>
                                                    <td align="center"><s:property value="subTotal"/></td>
                                                    <td align="center"><button class="btn btn-sm btn-success" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button></td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Semester 2</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td style="width: 20%">Divisi Id</td>
                                            <td align="center">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "semester2"'>
                                                <tr>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><s:property value="nilai"/></td>
                                                    <td align="center"><s:property value="subTotal"/></td>
                                                    <td align="center"><button class="btn btn-sm btn-success" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button></td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </s:if>

                        <s:if test='budgeting.tipe == "tahunan"'>
                            <div class="row">
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Periode Tahun</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td style="width: 20%">Divisi Id</td>
                                            <td align="center">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "tahunan"'>
                                                <tr>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><s:property value="nilai"/></td>
                                                    <td align="center"><s:property value="subTotal"/></td>
                                                    <td align="center"><button class="btn btn-sm btn-success" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button></td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </s:if>

                        <div class="form-group" style="margin-top: 10px">
                            <div class="col-md-4 col-md-offset-5">
                                <button class="btn btn-success" onclick="back()"><i class="fa fa-check"></i> Save</button>
                                <%--<button class="btn btn-success" id="btn-save" onclick="save()"><i class="fa fa-check"></i> Save </button>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-edit">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-plus"></i> Edit Budgeting Detail
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <label class="col-md-2 col-md-offset-1">Divisi Id</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="edit-divisi" disabled>
                            <input type="hidden" class="form-control" id="edit-id">
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-2 col-md-offset-1">Divisi Name</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="edit-divisi-name" disabled>
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-md-2 col-md-offset-1">Qty</label>
                        <div class="col-md-6">
                            <input type="number" class="form-control" id="edit-qty">
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-md-2 col-md-offset-1">Nilai</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="edit-nilai">
                        </div>
                    </div>

                    <div class="alert alert-warning alert-dismissable" id="alert-error-modal" style="display: none">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>Error!</strong><span id="error-msg-modal">Gagal Menambahkan Ke List</span>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="save_con" onclick="saveEdit()"><i class="fa fa-check"></i> Save</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-pengadaan">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-plus"></i> Tambah Rincian
                </h4>
            </div>
            <div class="modal-body">
                <button class="btn btn-sm btn-success" onclick="addInputUpload()"><i class="fa fa-plus"></i> Tambah</button>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <td>Nama Rincian</td>
                        <td>Qty</td>
                        <td>Nilai</td>
                    </tr>
                    </thead>
                    <tbody id="body-add-pengadaan">

                    </tbody>
                </table>
                <div class="row">
                    <label class="control-label col-sm-2">Nama </label>
                    <div class="col-sm-4">
                       <input type="text" class="form form-control" id="nama-head-pengadaan"/>
                    </div>
                </div>
                <div class="row">
                    <label class="control-label col-sm-2" id="label-tipe-pengadaan"></label>
                    <div class="col-sm-4">
                        <select class="form-control" id="sel-tipe-pengadaan">

                        </select>
                    </div>
                </div>

                <div class="alert alert-warning alert-dismissable" id="alert-error-add-pengadaan" style="display: none">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <strong>Error!</strong><span id="error-msg-add-pengadaan">Gagal Menambahkan Ke List</span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="saveAddPengadaan()"><i class="fa fa-check"></i> Save</button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    var unit        = '<s:property value="budgeting.branchId" />';
    var tahun       = '<s:property value="budgeting.tahun" />';
    var tipe        = '<s:property value="budgeting.tipe" />';
    var status      = '<s:property value="status" />';
    var rekeningid  = '<s:property value="id" />';
    var trans       = '<s:property value="trans" />';

    var form = { "budgeting.tahun":tahun, "budgeting.branchId":unit, "budgeting.tipe":tipe };

//    var listOfCoa = [];
    $( document ).ready(function() {
        console.log("hasil >>> "+unit+tahun+tipe);
        comboTipe();
    });

    function comboTipe() {
        var label = "";
        var opt = "";
        if (tipe == "quartal"){

            label = "Kuartal";
            opt = "<option value='quartal1'>Kuartal 1</option>" +
                "<option value='quartal2'>Kuartal 2</option>" +
                "<option value='quartal3'>Kuartal 3</option>" +
                "<option value='quartal4'>Kuartal 4</option>";

        }
        if (tipe == "semester"){

            label = "Semester";
            opt = "<option value='semester1'>Semester 1</option>" +
                "<option value='semester2'>Semester 2</option>";

        }
        if (tipe == "tahunan"){

            label = "Tahunan";
            opt = "<option value='tahunan'>Tahunan</option>";
        }

        $("#label-tipe").text(label);
        $("#label-tipe-pengadaan").text(label);
        $("#sel-tipe").html(opt);
        $("#sel-tipe-pengadaan").html(opt);
    }


    // exemple : post('/contact/', {name: 'Johnny Bravo'});
    function post(path, params) {

        var method='post';
        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        const form = document.createElement('form');
        form.method = method;
        form.action = path;

        for (const key in params) {
            if (params.hasOwnProperty(key)) {
                const hiddenField = document.createElement('input');
                hiddenField.type = 'hidden';
                hiddenField.name = key;
                hiddenField.value = params[key];

                form.appendChild(hiddenField);
            }
        }

        document.body.appendChild(form);
        form.submit();
    }

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

    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }

    function back() {

        if (trans != "" && trans != null){
            var host = firstpath()+"/budgeting/edit_budgeting.action?status="+status+"&trans="+trans;
        } else {
            var host = firstpath()+"/budgeting/add_budgeting.action?status="+status+"&tipe=detail&trans="+trans;
        }

        post(host, form);
    }

    function add() {
        $("#modal-add-coa").modal('show');
        $("#rekeningid").val("");
        $("#coa").val("");
        $("#namacoa").val("");
    }

    function saveAdd() {

        var divisi      = $("#divisiid").val();
        var positionid  = $("#positionid").val();
        var namadivisi  = $("#namadivisi").val();
        var qty         = $("#qty").val();
        var nilai       = $("#nilai").val();
        var tipe        = $("#sel-tipe").val();

        if (divisi == ""){
            // jika inputan divisi kosong makan muncul modal input pengadaan
            n = 0;
            strPengadaan = "";
            $("#body-add-pengadaan").html("");
            $("#modal-pengadaan").modal('show');
        } else {
            // jika ada inputan divisi maka save to session detail
            if (rekeningid != ""){
                var arrCoa = [];
                arrCoa.push({ "divisi":divisi, "divisiname":namadivisi, "qty":qty, "nilai":nilai, "tipe":tipe, "rekeningid":rekeningid, "positionid":positionid});
                var jsonString = JSON.stringify(arrCoa);

                BudgetingAction.setToSessionCoaDetail(jsonString, function (response) {
                    if(response == "01"){
                        refresh();
                    } else {
                        $("#alert-error-modal").show().fadeOut(5000);
                    }
                });
            } else {
                alert("Kode Rekening tidak ditemukan !");
            }
        }
    }

    function refresh() {
        var host = firstpath()+"/budgeting/edit_budgeting.action?status="+status+"&id="+rekeningid+"&tipe=detail&trans="+trans;
        post(host, form);
    }

    function edit(id) {
        $("#modal-edit").modal('show');
        BudgetingAction.getBudgetinDetailById(id, function(response){
            $("#edit-id").val(response.idBudgetingDetail);
            $("#edit-divisi").val(response.divisiId);
            $("#edit-divisi-name").val(response.divisiName);
            $("#edit-qty").val(response.qty);
            $("#edit-nilai").val(response.nilai);
        });

    }

    function saveEdit() {

        var id      = $("#edit-id").val();
        var qty     = $("#edit-qty").val();
        var nilai   = $("#edit-nilai").val();

        var arrData = [];
        arrData.push({"id":id, "qty":qty, "nilai":nilai, "rekeningid":rekeningid});
        var jsonStr = JSON.stringify(arrData);
        console.log(arrData);
        BudgetingAction.saveEditDetail( jsonStr, function(response){

            if (response.status == "success"){
                refresh();
            } else {
                $("#alert-error-modal").show().fadeOut(5000);
                $("#error-msg-modal").text(response.msg);
            }
        });
    }

    var n = 0;
    var strPengadaan = "";
    function addInputUpload() {
        strPengadaan += '<tr>' +
            '<td><input type="text" class="form form-control" id="nama-add-'+n+'"/></td>' +
            '<td><input type="number" class="form form-control" id="qty-add-'+n+'"/></td>' +
            '<td><input type="number" class="form form-control" id="nilai-add-'+n+'"/></td>' +
            '</tr>';
        $("#body-add-pengadaan").html(strPengadaan);
        n++;
    }
    
    function saveAddPengadaan() {

        $("#modal-pengadaan").modal('hide');

//        var tipe = $("#sel-tipe-pengadaan").val();
//        var namapengadaan = $("#nama-head-pengadaan").val();
//        var arrData = [];
//        for (var i = 0 ; i <= n; i++){
//            var nama = $("#ama-add-"+i+"").val();
//            var qty = $("#ama-add-"+i+"").val();
//            var nilai = $("#ama-add-"+i+"").val();
//            arrData.push({"name":nama, "qty":qty, "nilai":nilai});
//        }
//
//        var strJson = JSON.stringify(arrData);
//        BudgetingAction.saveAddPengadaan(strJson, namapengadaan, rekeningid, tipe, function (response) {
//            if (response.status == "success"){
//                refresh();
//            } else {
//                $("#alert-error-add-pengadaan").show().fadeOut(5000);
//            }
//        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>