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

        .treegrid-collapsed {
            background-color: #bfbfbf;
        }
        .treegrid-expanded {
            background-color: #e6e6e6;
        }

        .treegrid-indent {width:16px; height: 16px; display: inline-block; position: relative;}

        .treegrid-expander { width:16px; height: 16px; display: inline-block; position: relative; cursor: pointer;}
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BudgetingAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MasterAction.js"/>'></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>
    <script type='text/javascript'>

        function formatRupiah(angka) {
            if(angka != null && angka != ''){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                if (angka < 0){
                    return "-"+ribuan;
                } else {
                    return ribuan;
                }
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
                                    <td>Tahun</td>
                                    <td>:</td>
                                    <td> <s:property value="budgeting.tahun"></s:property></td>
                                </tr>
                                <tr>
                                    <td>Unit</td>
                                    <td>:</td>
                                    <td> <s:property value="budgeting.branchName"></s:property></td>
                                </tr>
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
                                <%--<tr>--%>
                                    <%--<td>Data lain (tipe coa - master id - divisi id) </td>--%>
                                    <%--<td>:</td>--%>
                                    <%--<td><s:property value="budgeting.tipeCoa"></s:property> - <s:property value="budgeting.flagMaster"></s:property> - <s:property value="budgeting.flagDivisi"></s:property></td>--%>
                                <%--</tr>--%>
                            </tbody>
                        </table>

                        <%--table informasi all coa--%>
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <table class="table table-bordered table-striped tree" style="font-size: 15px;">
                                    <thead id="head-budgeting">
                                    <tr bgcolor="#90ee90">
                                        <td style="width: 20%">COA</td>
                                        <td align="center">Keterangan</td>
                                        <td align="center">Total</td>
                                        <td align="center">Selisih</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body-budgeting">
                                    <%--<s:iterator value="#session.listOfCoa" status="listOfCoa" var="row">--%>
                                        <%--<tr>--%>
                                            <%--<td><s:property value="kodeRekening"/></td>--%>
                                            <%--<td><s:property value="namaKodeRekening"/></td>--%>
                                            <%--<td align="center"><script>document.write(formatRupiah('<s:property value="nilaiTotal"/>'))</script></td>--%>
                                            <%--<td align="center"><s:property value="selisih"/></td>--%>
                                        <%--</tr>--%>
                                    <%--</s:iterator>--%>
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

                                    <div id="form-master">
                                        <strong>Master</strong> <hr style="width: 50%;">
                                        <div class="row">
                                            <label class="control-label col-sm-2">Master Id</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" id="masterid"/>

                                                <script>
                                                    $(document).ready(function() {
                                                        var functions, mapped;
                                                        $('#masterid').typeahead({
                                                            minLength: 1,
                                                            source: function (query, process) {
                                                                functions = [];
                                                                mapped = {};
                                                                var data = [];
                                                                dwr.engine.setAsync(false);
                                                                MasterAction.initTypeaheadMaster(query,function (listdata) {
                                                                    data = listdata;
                                                                });
                                                                $.each(data, function (i, item) {
                                                                    var labelItem = item.nomorVendor + " | " + item.nama;
                                                                    mapped[labelItem] = {
                                                                        name :item.nama,
                                                                        id : item.nomorVendor
                                                                    };
                                                                    functions.push(labelItem);
                                                                });
                                                                process(functions);
                                                            },
                                                            updater: function (item) {
                                                                var selectedObj = mapped[item];
                                                                $("#namamaster").val(selectedObj.name);
                                                                return selectedObj.id;
                                                            }
                                                        });
                                                    });
                                                </script>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="control-label col-sm-2">Nama Master</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" id="namamaster" readonly/>
                                            </div>
                                        </div>
                                    </div>

                                    <br>
                                    <div id="form-divisi">
                                        <strong>Divisi</strong> <hr style="width: 50%;">
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
                                                                PositionAction.typeAheadPosition(query,function (listdata) {
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
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="control-label col-sm-2">Nama Divisi</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" id="namadivisi" readonly/>
                                                <input type="hidden" class="form-control" id="positionid" readonly/>
                                            </div>
                                        </div>
                                    </div>

                                    <br>
                                    <div id="form-periode">
                                        <strong>Nilai Per Periode</strong><hr style="width: 50%;">

                                        <div class="row">
                                            <label class="control-label col-sm-2">QTY</label>
                                            <div class="col-sm-4">
                                                <input type="number" value="0" class="form-control" id="qty" onchange="hitungSubTotal('divisi')"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="control-label col-sm-2">Tarif (Satuan)</label>
                                            <div class="col-sm-4">
                                                <input type="number" value="0" class="form-control" id="nilai" onchange="hitungSubTotal('divisi')"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="control-label col-sm-2">Sub Total</label>
                                            <div class="col-sm-4">
                                                <input type="number" class="form-control" id="total-divisi" disabled/>
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
                            </div>

                            <div class="row">
                                <div class="col-md-6 col-md-offset-5" style="margin-top: 10px">
                                    <button class="btn btn-primary" onclick="saveAdd()" id="btn-save-add"><i class="fa fa-plus"></i> Add</button>
                                </div>
                            </div>
                        </div>

                        <%--</s:form>--%>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i>
                           <strong><span id="label-tipe-list"></span></strong>
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
                            <strong>Warning!</strong><span id="error-msg"></span>
                        </div>

                        <%--table informasi quartal--%>
                        <s:if test='budgeting.tipe == "quartal"'>
                            <div class="row">
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Kuartal 1</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
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
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>


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
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
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
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
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
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
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
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
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
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
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
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
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
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
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
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
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
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
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
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
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
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
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
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </s:if>

                        <%--TIPE BULANAN START--%>
                        <s:if test='budgeting.tipe == "bulanan"'>
                            <div class="row">
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Januari</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "januari"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Februari</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "februari"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Maret</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "maret"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>April</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "april"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Mei</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "mei"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Juni</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "juni"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Juli</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "juli"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Agustus</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "agustus"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>September</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "september"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Oktober</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "oktober"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>November</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "november"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <label>Desember</label>
                                    <table class="table table-bordered table-striped" style="font-size: 15px;">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td class="list-label-master-id">Master Id</td>
                                            <td class="list-label-master-name">Master Name</td>
                                            <td style="width: 20%" class="list-label-divisi-id">Divisi Id</td>
                                            <td align="center" class="list-label-divisi-name">Nama Divisi</td>
                                            <td align="center">Quantity</td>
                                            <td align="center">Nilai</td>
                                            <td align="center">Sub Total</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="#session.listOfDetailEdit" var="row">
                                            <s:if test='#row.tipe == "desember"'>
                                                <tr>
                                                    <td><s:property value="masterId"/></td>
                                                    <td><s:property value="masterName"/></td>
                                                    <td><s:property value="divisiId"/></td>
                                                    <td><s:property value="divisiName"/></td>
                                                    <td align="center"><s:property value="qty"/></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="nilai"/>'))</script></td>
                                                    <td align="center"><script>document.write(formatRupiah('<s:property value="subTotal"/>'))</script></td>
                                                    <td align="center">
                                                        <s:if test='#row.flagEdit != "N"'>
                                                            <button class="btn btn-sm btn-primary" onclick="edit('<s:property value="idBudgetingDetail"/>')" ><i class="fa fa-edit"></i></button>
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </s:if>

                        <%--TIPE BULANAN END--%>

                        <div class="form-group" style="margin-top: 10px">
                            <div class="col-md-4 col-md-offset-5">
                                <button class="btn btn-success" onclick="back()" id="btn-save-all"><i class="fa fa-check"></i> Save</button>
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
                        <label class="col-md-2 col-md-offset-1">Master Id</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="edit-master" disabled>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-2 col-md-offset-1">Master Name</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="edit-master-name" disabled>
                        </div>
                    </div>
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
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-plus"></i> Investasi
                </h4>
            </div>
            <div class="modal-body">
                <button class="btn btn-sm btn-success" onclick="addInputUpload()"><i class="fa fa-plus"></i> Tambah</button>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <td style="width: 50% ;">Nama Rincian</td>
                        <td style="width: 20% ;">Qty</td>
                        <td>Nilai</td>
                    </tr>
                    </thead>
                    <tbody id="body-add-pengadaan">

                    </tbody>
                    <input type="hidden" id="id-detail"/>
                </table>
                <div class="row">
                    <%--<label class="control-label col-sm-2">Nama </label>--%>
                    <div class="col-sm-4">
                       <input type="hidden" class="form form-control" id="nama-head-pengadaan"/>
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
    var flagDivisi  = '<s:property value="budgeting.flagDivisi"/>';
    var flagMaster  = '<s:property value="budgeting.flagMaster"/>';
    var tipeCoa     = '<s:property value="budgeting.tipeCoa"/>';

    var form = { "budgeting.tahun":tahun, "budgeting.branchId":unit, "budgeting.tipe":tipe };

    $( document ).ready(function() {
        comboTipe();
        search();
        enableDisable();
    });

    function hitungSubTotal(var1) {
        var total = 0;
        if ("divisi" == var1){

            var qty = $("#qty").val();
            var nilai = $("#nilai").val();

            total = qty*nilai;
            $("#total-divisi").val(total);
        }
    }

    function enableDisable() {
        if ((flagDivisi == "N" || flagDivisi == null || flagDivisi == "") && (flagMaster == "N" || flagMaster == null || flagMaster == "") && tipeCoa == "04"){
            $("#form-divisi").hide();
            $("#form-master").hide();
            $("#form-periode").hide();
            $("#btn-save-add").html("<i class='fa fa-plus'></i> Add Investasi");
            $(".list-label-divisi-id").text("Investasi Id");
            $(".list-label-divisi-name").text("Nama Investasi");
        } else {
            if ((flagDivisi == "N" || flagDivisi == "" || flagDivisi == null) && (flagMaster == "N" || flagMaster == "" || flagMaster == null)){
                $("#alert-error").show();
                $("#error-msg").text(" Tidak Bisa Edit Data Budgeting. Lengkapi Data Kode Rekening Terlebih Dahulu.");
                $("#btn-save-add").hide();
                $("#form-divisi").hide();
                $("#form-master").hide();
                $("#form-periode").hide();
                $("#btn-save-all").text("Back");
            } else {
                if (flagDivisi == "N"){
                    $("#form-divisi").hide();
                }
                if (flagMaster == "N"){
                    $("#form-master").hide();
                }
            }

        }

    }

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

        if (tipe == "bulanan"){

            label = "Bulanan";
            opt = "<option value='januari'>Januari</option>" +
                "<option value='februari'>Februari</option>" +
                "<option value='maret'>Maret</option>" +
                "<option value='april'>April</option>" +
                "<option value='mei'>Mei</option>" +
                "<option value='juni'>Juni</option>" +
                "<option value='juli'>Juli</option>" +
                "<option value='agustus'>Agustus</option>" +
                "<option value='september'>September</option>" +
                "<option value='oktober'>Oktober</option>" +
                "<option value='november'>November</option>" +
                "<option value='desember'>Desember</option>";
        }

        $("#label-tipe").text(label);
        $("#label-tipe-list").text("List "+label);
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
        var masterId    = $("#masterid").val();
        var mastername  = $("#namamaster").val();

        if (divisi == ""){
            // jika inputan divisi kosong makan muncul modal input pengadaan
            n = 0;
            strPengadaan = "";
            $("#label-tipe-pengadaan").show();
            $("#sel-tipe-pengadaan").show();
            $("#id-detail").val("");
            $("#body-add-pengadaan").html("");
            $("#nama-head-pengadaan").val("Investasi");
            $("#modal-pengadaan").modal('show');
        } else {

            // jika ada divisi check apakah divisi tersebut sudah ditambahkan
            BudgetingAction.checkBudgetingDivisi(tipe, divisi, rekeningid, function (response) {
                if (response != null){
                    $("#alert-error").show().fadeOut(5000);
                    $("#error-msg").text(" Data Sudah Ada. Divisi : "+namadivisi+" Pada : "+tipe);
                } else {

                    // jika tidak ditambhakan
                    // cek apakah inputan tidak null / 0 / < 0
                    if (qty == "" || qty <= 0 || nilai == "" || nilai <= 0){
                        $("#alert-error").show().fadeOut(5000);
                        $("#error-msg").text(" Inputan Tidak Boleh Kosong / Tidak Boleh 0");
                    } else {


                        // jika ada inputan divisi maka save to session detail
                        if (rekeningid != ""){
                            var arrCoa = [];
                            arrCoa.push({
                                "divisi":divisi == null ? "" : divisi,
                                "divisiname":namadivisi == null ? "" : namadivisi,
                                "qty":qty,
                                "nilai":nilai,
                                "tipe":tipe,
                                "rekeningid":rekeningid,
                                "positionid":positionid,
                                "masterid":masterId == null ? "" : masterId,
                                "mastername":mastername == null ? "": mastername
                            });
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
                };
            });
        }
    }

    function refresh() {
        var host = firstpath()+"/budgeting/edit_budgeting.action?status="+status+"&id="+rekeningid+"&tipe=detail&trans="+trans;
        post(host, form);
    }

    function edit(id) {
        BudgetingAction.getBudgetinDetailById(id, function(response){

            if (response.divisiId == "INVS"){
                strPengadaan = "";
                n = 0;
                BudgetingAction.getListPengadaan(id, function(list){
                    if (list != null){
                        $.each(list, function (i, item) {
                            strPengadaan += '<tr>' +
                                '<td><input type="hidden" id="id-add-'+i+'" value="'+item.idPengadaan+'"><input type="text" class="form form-control" id="nama-add-'+i+'" value="'+item.namPengadaan+'"/></td>' +
                                '<td><input type="number" class="form form-control" id="qty-add-'+i+'" value="'+item.qty+'"/></td>' +
                                '<td><input type="number" class="form form-control" id="nilai-add-'+i+'" value="'+item.nilai+'"/></td>' +
                                '</tr>';
                        });
                        n = list.length;
                        $("#id-detail").val(id);
                        $("#label-tipe-pengadaan").hide();
                        $("#sel-tipe-pengadaan").hide();
                        $("#body-add-pengadaan").html(strPengadaan);
                        $("#nama-head-pengadaan").val("Investasi");
                        $("#modal-pengadaan").modal('show');
                    }
                }) ;
            } else {
                $("#modal-edit").modal('show');
                $("#edit-id").val(response.idBudgetingDetail);
                $("#edit-divisi").val(response.divisiId);
                $("#edit-divisi-name").val(response.divisiName);
                $("#edit-qty").val(response.qty);
                $("#edit-nilai").val(response.nilai);
            }
        });

    }

    function saveEdit() {

        var id      = $("#edit-id").val();
        var qty     = $("#edit-qty").val();
        var nilai   = $("#edit-nilai").val();

        var arrData = [];
        arrData.push({"id":id, "qty":qty, "nilai":nilai, "rekeningid":rekeningid});
        var jsonStr = JSON.stringify(arrData);
//        console.log(arrData);
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
            '<td><input type="hidden" id="id-add-'+n+'"><input type="text" class="form form-control" id="nama-add-'+n+'"/></td>' +
            '<td><input type="number" class="form form-control" id="qty-add-'+n+'"/></td>' +
            '<td><input type="number" class="form form-control" id="nilai-add-'+n+'"/></td>' +
            '</tr>';
        $("#body-add-pengadaan").html(strPengadaan);
        n++;
    }
    
    function saveAddPengadaan() {

        var namainvestasi   = $("#nama-head-pengadaan").val();
        var tipepengadaan   = $("#sel-tipe-pengadaan").val();
        var iddetail        = $("#id-detail").val();

        var arrData = [];
        for (i = 0; i < n; i++){
            var name = $("#nama-add-"+i).val();
            var qty = $("#qty-add-"+i).val();
            var nilai = $("#nilai-add-"+i).val();

            if (iddetail == "") {
                arrData.push({ "name":name, "qty":qty, "nilai":nilai, "id":"" });
            } else {

                var id = $("#id-add-"+i).val();
                arrData.push({ "name":name, "qty":qty, "nilai":nilai, "id":id });
            }
        }

        var strJson = JSON.stringify(arrData);

        if (iddetail != "") {
            // jika id detail tidak kosong = save edit
            BudgetingAction.saveEditPengadaan(strJson, namainvestasi, rekeningid, iddetail, function(response){
                refresh();
            });
            $("#modal-pengadaan").modal('hide');
        } else {
            BudgetingAction.checkBudgetingDivisi(tipepengadaan, "", rekeningid, function (response) {
                if (response != null){
                    $("#alert-error-add-pengadaan").show().fadeOut(5000);
                    $("#error-msg-add-pengadaan").text(" Data Investasi Sudah Ada. Pada : "+tipepengadaan);
                } else {
                    // jika id detail kosong = save add
                    BudgetingAction.saveAddPengadaan(strJson, namainvestasi, rekeningid, tipepengadaan, function(response){
                        refresh();
                    });
                    $("#modal-pengadaan").modal('hide');
                }
            });
        }


    }

    function search() {
        var data = [];
        var data2 = [];
        dwr.engine.setAsync(true);
        BudgetingAction.getListOfCoaBudgetingSession(function (response) {

//            console.log(response);
            data = response;
            data2 = new Array();
            $.each(data, function(i,item){
//                console.log(item.rekeningId);
                data2.push({_id : item.rekeningId, level : item.level,  nama : item.namaKodeRekening, parent : item.parentId, coa : item.kodeRekening,
                    nilaiTotal : item.nilaiTotal, quartal1 : item.quartal1, quartal2: item.quartal2, quartal3 : item.quartal3, quartal4 : item.quartal4,
                    semester1 : item.semester1, semester2:item.semester2, stLevel: item.stLevel, selisih : item.selisih});

            });
            function hierarhySort(hashArr, key, result) {
                if (hashArr[key] == undefined){
                    //level--;
                    return;
                }else{
                    var arr = [] ;
                    arr  = hashArr[key];
                }
                for (var i=0; i<arr.length; i++) {
                    result.push(arr[i]);
                    hierarhySort(hashArr, arr[i]._id, result);
                }
                return result;
            }
            var hashArr = {};
            for (var i=0; i<data2.length; i++) {
                if (hashArr[data2[i].parent] == undefined) {
                    hashArr[data2[i].parent] = [];
                }
                hashArr[data2[i].parent].push(data2[i]);
            }

            var strList = "";
            for(i = 0 ; i < data2.length ; i++){
                if(data2[i].parent == "-"){
                    strList += '<tr style="font-size: 12px;" class=" treegrid-' + data2[i]._id+ '">' +
                        '<td >' + data2[i].coa + '</td>' +
                        '<td >' + data2[i].nama + '</td>' +
                        "<td align='right'>"+formatRupiah(data2[i].nilaiTotal)+"</td>"+
                        "<td align='right'>"+formatRupiah(data2[i].selisih)+"</td>"+
                        "</tr>";
                } else {
                    strList += '<tr style="font-size: 12px" class=" treegrid-' + data2[i]._id + ' treegrid-parent-' + data2[i].parent + '">' +
                        + '<td style="border: 2px solid black;">' +
                        '<td >' + data2[i].coa + '</td>' +
                        '<td >' + data2[i].nama + '</td>' +
                        "<td align='right'>"+formatRupiah(data2[i].nilaiTotal)+"</td>"+
                        "<td align='right'>"+formatRupiah(data2[i].selisih)+"</td>"+
                        "</tr>";
                }
            }
            $("#body-budgeting").html(strList);
            $('.tree').treegrid({
                expanderExpandedClass: 'glyphicon glyphicon-minus',
                expanderCollapsedClass: 'glyphicon glyphicon-plus'
            });
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>