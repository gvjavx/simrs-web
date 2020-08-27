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
    <script type='text/javascript' src='<s:url value="/dwr/interface/BudgetingNilaiDasarAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BgPendapatanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MasterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Budgeting Pendapatan</h3>
                    </div>
                    <div class="box-body">
                        <%--<s:form id="kasirjalanForm" method="post" namespace="/kasirjalan" action="search_kasirjalan.action" theme="simple" cssClass="form-horizontal">--%>
                        <div class="form-group form-horizontal">
                            <div class="row">
                                <div class="col-md-12">
                                    <h4>
                                        <s:property value="budgeting.namaKategori"/> - <s:property value="budgeting.branchName"/> <s:property value="budgeting.tahun"/>
                                    </h4>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 col-md-offset-3">
                                    <h4>Nilai Dasar : </h4>
                                    <table class="table table-bordered table-striped">
                                        <tbody id="body-nilai-dasar">
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <%--<div class="row">--%>
                                <%--<div class="col-md-6 col-md-offset-6" style="margin-top: 10px">--%>
                                    <%--<button class="btn btn-primary" onclick="add()"><i class="fa fa-plus"></i> Add</button>--%>
                                <%--</div>--%>
                            <%--</div>--%>
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

                            <div class="row">
                                <div class="col-md-8 col-md-offset-2">
                                    <table class="table table-bordered table-striped">
                                        <thead bgcolor="#90ee90">
                                            <tr>
                                                <td>Master</td>
                                                <td align="center">Nilai</td>
                                                <td align="center">Action</td>
                                            </tr>
                                        </thead>
                                        <tbody id="list-body-budgeting">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        <%--<div class="row">--%>
                            <%--<div class="col-md-8 col-md-offset-2">--%>
                                <%--<h4>Nama Parameter</h4>--%>
                                <%--<table class="table table-bordered table-striped tree">--%>
                                    <%--<thead id="head-budgeting">--%>
                                    <%--<tr bgcolor="#90ee90">--%>
                                        <%--<td>Master</td>--%>
                                        <%--<td>Divisi</td>--%>
                                        <%--<td>Nilai</td>--%>
                                        <%--<td align="center">Action</td>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody id="body-budgeting">--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group" style="margin-top: 10px">
                            <div class="col-md-4 col-md-offset-5">
                                <button class="btn btn-warning" onclick="back()"><i class="fa fa-refresh"></i> Close</button>
                                <%--<button class="btn btn-success" id="btn-save" onclick="saveAdd()"><i class="fa fa-arrow-right"></i> Save </button>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-add-coa">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-plus"></i> Add COA
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                    <label class="col-md-2 col-md-offset-1">Jenis Investasi</label>
                    <div class="col-md-6">
                    <input type="text" class="form-control" id="nama-coa">
                        <script>
                            $(document).ready(function() {
                                var tipe = $("#add-coa-tipe").val();
                                var functions, mapped;
                                $('#nama-coa').typeahead({
                                    minLength: 1,
                                    source: function (query, process) {
                                        functions = [];
                                        mapped = {};
                                        var data = [];
                                        dwr.engine.setAsync(false);
                                        BudgetingAction.getListKodeRekeningByLevel(query, tipe, function (listdata) {
                                            data = listdata;
                                        });
                                        $.each(data, function (i, item) {
                                            var labelItem = item.namaKodeRekening;
                                            mapped[labelItem] = {
                                                id: item.rekeningId,
                                                nama: item.namaKodeRekening,
                                                kode : item.kodeRekening,
                                                parent :item.parentId
                                            };
                                            functions.push(labelItem);
                                        });
                                        process(functions);
                                    },
                                    updater: function (item) {
                                        var selectedObj = mapped[item];
                                        $('#rekeningid').val(selectedObj.id);
                                        $('#namacoa').val(selectedObj.nama);
                                        $('#parentid').val(selectedObj.parent);
                                        $('#coa').val(selectedObj.kode);
                                        return selectedObj.nama;
                                    }
                                });
                            });
                        </script>

                    <input type="hidden" id="rekeningid">
                    <input type="hidden" id="namacoa">
                    <input type="hidden" id="parentid">
                    <input type="hidden" id="coa">
                    </div>
                    </div>


                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-add">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-plus"></i> <span id="label-edit"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-8 col-md-offset-3">
                            <div id="form-master">
                                <strong>Master</strong> <hr style="width: 80%;">
                                <div class="row">
                                    <label class="control-label col-sm-4">Master Id</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="masterid" readonly/>
                                        <%--<script>--%>
                                            <%--$(document).ready(function() {--%>
                                                <%--var functions, mapped;--%>
                                                <%--$('#masterid').typeahead({--%>
                                                    <%--minLength: 1,--%>
                                                    <%--source: function (query, process) {--%>
                                                        <%--functions = [];--%>
                                                        <%--mapped = {};--%>
                                                        <%--var data = [];--%>
                                                        <%--dwr.engine.setAsync(false);--%>
                                                        <%--MasterAction.initTypeaheadMaster(query,function (listdata) {--%>
                                                            <%--data = listdata;--%>
                                                        <%--});--%>
                                                        <%--$.each(data, function (i, item) {--%>
                                                            <%--var labelItem = item.nomorVendor + " | " + item.nama;--%>
                                                            <%--mapped[labelItem] = {--%>
                                                                <%--name :item.nama,--%>
                                                                <%--id : item.nomorVendor--%>
                                                            <%--};--%>
                                                            <%--functions.push(labelItem);--%>
                                                        <%--});--%>
                                                        <%--process(functions);--%>
                                                    <%--},--%>
                                                    <%--updater: function (item) {--%>
                                                        <%--var selectedObj = mapped[item];--%>
                                                        <%--$("#namamaster").val(selectedObj.name);--%>
                                                        <%--return selectedObj.id;--%>
                                                    <%--}--%>
                                                <%--});--%>
                                            <%--});--%>
                                        <%--</script>--%>
                                    </div>
                                </div>
                                <%--<div class="row">--%>
                                    <%--<label class="control-label col-sm-4">Nama Master</label>--%>
                                    <%--<div class="col-sm-6">--%>
                                        <%--<input type="text" class="form-control" id="namamaster" readonly/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>

                            <br>
                            <div id="form-divisi">
                                <strong>Divisi</strong> <hr style="width: 80%;">
                                <div class="row">
                                    <label class="control-label col-sm-4">Divisi Id</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="divisiid" readonly/>

                                        <%--<script>--%>
                                            <%--$(document).ready(function() {--%>
                                                <%--var functions, mapped;--%>
                                                <%--$('#divisiid').typeahead({--%>
                                                    <%--minLength: 1,--%>
                                                    <%--source: function (query, process) {--%>
                                                        <%--functions = [];--%>
                                                        <%--mapped = {};--%>
                                                        <%--var data = [];--%>
                                                        <%--dwr.engine.setAsync(false);--%>
                                                        <%--PositionAction.typeAheadPosition(query,function (listdata) {--%>
                                                            <%--data = listdata;--%>
                                                        <%--});--%>
                                                        <%--$.each(data, function (i, item) {--%>
                                                            <%--var labelItem = item.kodering + " | " + item.positionName;--%>
                                                            <%--mapped[labelItem] = {--%>
                                                                <%--kode : item.kodering,--%>
                                                                <%--name :item.positionName,--%>
                                                                <%--id : item.positionId--%>
                                                            <%--};--%>
                                                            <%--functions.push(labelItem);--%>
                                                        <%--});--%>
                                                        <%--process(functions);--%>
                                                    <%--},--%>
                                                    <%--updater: function (item) {--%>
                                                        <%--var selectedObj = mapped[item];--%>
                                                        <%--$("#namadivisi").val(selectedObj.name);--%>
                                                        <%--$("#positionid").val(selectedObj.id);--%>
                                                        <%--return selectedObj.kode;--%>
                                                    <%--}--%>
                                                <%--});--%>
                                            <%--});--%>
                                        <%--</script>--%>
                                    </div>
                                </div>
                                <%--<div class="row">--%>
                                    <%--<label class="control-label col-sm-4">Nama Divisi</label>--%>
                                    <%--<div class="col-sm-6">--%>
                                        <%--<input type="text" class="form-control" id="namadivisi" readonly/>--%>
                                        <%--<input type="hidden" class="form-control" id="positionid" readonly/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>

                            <br>
                            <div id="form-periode">
                                <strong>Periode</strong> <hr style="width: 80%;">
                                <div class="row">
                                    <label class="control-label col-sm-4">Periode</label>
                                    <div class="col-sm-6">
                                        <select class="form-control" id="sel-periode">
                                            <option value="januari">Januari</option>
                                            <option value="februari">Februari</option>
                                            <option value="maret">Maret</option>
                                            <option value="april">April</option>
                                            <option value="mei">Mei</option>
                                            <option value="juni">Juni</option>
                                            <option value="juli">Juli</option>
                                            <option value="agustus">Agustus</option>
                                            <option value="september">September</option>
                                            <option value="oktober">Oktober</option>
                                            <option value="november">November</option>
                                            <option value="desember">Desember</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <br>
                            <strong>Nilai</strong><hr style="width: 80%;">
                            <div class="row">
                                <div class="col-md-12" align="right">
                                    <button class="btn btn-sm btn-info" onclick="showDetail()"><i class="fa fa-info"></i></button>
                                    <button class="btn btn-sm btn-warning" onclick="addPerhitungan()"><i class="fa fa-plus"></i></button>
                                </div>
                            </div>
                            <br>
                            <div id="body-nilai">
                                <%--<div class="row">--%>
                                <%--<label class="control-label col-sm-4">Nilai Pendapatan</label>--%>
                                <%--<div class="col-sm-6">--%>
                                <%--<input type="number" value="0" class="form-control" id="total-pendapatan" onchange="hitungSubTotal('divisi')"/>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                        <input type="hidden" id="id-param"/>
                        <input type="hidden" id="ed-unit"/>
                        <input type="hidden" id="ed-tahun"/>

                        <%--<label class="col-md-2 col-md-offset-1">Jenis Investasi</label>--%>
                        <%--<div class="col-md-6">--%>
                        <%--</div>--%>
                    </div>

                    <div class="alert alert-warning alert-dismissable" id="alert-error-modal" style="display: none">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>Error!</strong><span id="error-msg-modal">Gagal Menambahkan No. Rekening : No. Rekening Sudah Ada</span>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="save_con" onclick="addCoa()"><i class="fa fa-check"></i> Save</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-pendapatan">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Data Pendapatan </h4>
            </div>
            <div class="modal-body">
                <span id="label-tipe"></span> <span id="label-periode"></span>
                <br>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-bordered table-striped tree" style="font-size: 13px; margin-top: 7px;">
                                <thead>
                                <tr bgcolor="#90ee90" id="head-list-pendapatan">

                                </tr>
                                </thead>
                                <tbody id="body-list-pendapatan">
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-hitung">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Add Perhitungan </h4>
            </div>
            <div class="modal-body">
                <br>
                <div class="form-group">
                    <div class="row" id="body-hitung">
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="save-hotung" onclick="addHitung()"><i class="fa fa-check"></i> Save</button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    var listOfCoa = [];
    $( document ).ready(function() {
        chekTipe();
        nilaiDasar();
//        showListParameter();
        showListMaster();
    });

    var listOfParam     = [];
    var n               = 0;
    var tipe            = '<s:property value="budgeting.tipe"/>';
    var flagDisable     = '<s:property value="budgeting.flagDisable"/>';
    var idKategori      = '<s:property value="budgeting.idKategoriBudgeting"/>';
    var namaKategori    = '<s:property value="budgeting.namaKategori"/>';
    var tahun           = '<s:property value="budgeting.tahun"/>';
    var unit            = '<s:property value="budgeting.branchId"/>';

    function chekTipe() {
        if ("Y" == flagDisable){
            $("#sel-tipe").attr('readonly',true);
            $("#sel-tipe").val(tipe);
        }
    }

    function nilaiDasar() {
        BudgetingNilaiDasarAction.getListNilaiDasarEdit(tahun, function (list) {

            var str = "";
            $.each(list, function (i, item) {
                str += "<tr>" +
                        "<td>" + item.keterangan + "</td>" +
                        "<td align='right'>" + item.nilai + "</td>" +
                    "</tr>";
            });

            $("#body-nilai-dasar").html(str);
            console.log(str);
        });
    }

    function showListParameter() {
        BgPendapatanAction.getListParameterByIdKategori(idKategori, function (list) {
            var str = "";
            $.each(list, function (i, item) {
                str += '<div class="row">' +
                    '<div class="col-md-8 col-md-offset-2">' +
                    '<h4 id="label-head-'+item.id+'">' + item.nama +'</h4>' +
                    '<button class="btn btn-sm btn-primary" style="float: right;" onclick="showAdd(\''+item.id+'\')"><i class="fa fa-plus"></i> Tambah</button>' +
                    '<table class="table table-bordered table-striped tree">' +
                    '<thead id="head-budgeting">' +
                    '<tr bgcolor="#90ee90">' +
                    '<td>Periode</td>' +
                    '<td>Master</td>' +
                    '<td>Divisi</td>' +
                    '<td>Nilai</td>' +
//                    '<td align="center">Action</td>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody id="body-budgeting-'+ item.id +'">';

                BgPendapatanAction.getListDataParam(item.id, function (listDatas) {

                    $.each(listDatas, function (n, data) {
                        str += '<tr>' +
                            '<td>'+data.periode+'</td>' +
                            '<td>'+data.namaMaster+'</td>' +
                            '<td>'+data.namaDivisi+'</td>' +
                            '<td align="right">'+ formatRupiah( data.nilaiTotal )+'</td>' +
                            '</tr>';
                    });
                });


                str += '</tbody>' +
                    '</table>' +
                    '</div>' +
                    '</div>' +
                    '<br/>' ;
            });
            $("#list-body-budgeting").html(str);

        });
    }

    function showListMaster() {
        BgPendapatanAction.getListMasterBudgeting(idKategori, function (list) {
            var str = "";
            $.each(list, function (i, item) {
                str += '<tr>' +
                    '<td>'+item.namaMaster+'</td>' +
                    '<td align="right">'+ formatRupiah( item.nilaiTotal )+'</td>' +
                    '<td align="center" id="btn-span-'+i+'"><button class="btn btn-sm btn-primary" onclick="spanRow(\''+i+'\', \''+item.masterId+'\')"><i class="fa fa-plus"></i></button></td>' +
                    '</tr>' +
                    '<tr style="display: none" id="row-master-'+i+'">' +
                    '<td colspan="3" id="body-divisi-'+i+'">' +
                    '</td>' +
                    '</tr>';
            });

            $("#list-body-budgeting").html(str);
        });
    }

    function spanRow(i, master) {
        $("#row-master-"+i).show();
        var btn = '<button class="btn btn-sm btn-primary" onclick="unSpanRow(\''+i+'\', \''+master+'\')"><i class="fa fa-minus"></i></button>';
        $("#btn-span-"+i).html(btn);
        listDivisi(i, master);
    }

    function unSpanRow(i, master) {
        $("#row-master-"+i).hide();
        var btn = '<button class="btn btn-sm btn-primary" onclick="spanRow(\''+i+'\', \''+master+'\')"><i class="fa fa-plus"></i></button>';
        $("#btn-span-"+i).html(btn);
    }

    function listDivisi(i, masterid) {

        BgPendapatanAction.getListDivisiBudgeting(idKategori, masterid, function (list) {

            var str = '';
            $.each(list, function (i, item) {

                str += '<div class="row">' +
                    '<div class="col-md-8 col-md-offset-2">' +
                    '<h4 id="label-head-'+item.id+'">' + item.namaDivisi +'</h4>' +
                    '<button class="btn btn-sm btn-primary" style="float: right;" onclick="showAdd(\''+item.id+'\', \''+item.divisiId+'\', \''+masterid+'\')"><i class="fa fa-plus"></i> Tambah</button>' +
                    '<table class="table table-bordered table-striped">' +
                    '<thead id="head-budgeting">' +
                    '<tr bgcolor="#90ee90">' +
                    '<td>Periode</td>' +
                    '<td>Nilai</td>' +
                    //                    '<td align="center">Action</td>' +
                    '</tr>' +
                    '</thead>' +
                    '<tbody id="body-budgeting-data-'+ item.id +'">';

                BgPendapatanAction.getListDataParam(item.id, function (listDatas) {

                    $.each(listDatas, function (n, data) {
                        str += '<tr>' +
                            '<td>'+data.periode+'</td>' +
                            '<td align="right">'+ formatRupiah( data.nilaiTotal )+'</td>' +
                            '</tr>';
                    });
                });

                str += '</tbody>' +
                    '</table>' +
                    '</div>' +
                    '</div>' +
                    '<br/>' ;
            });
            $("#body-divisi-"+i).html(str);
        })
    }

    function addPerhitungan() {
//        $("#modal-add-hitung").modal('show');
        var idParam = $("#id-param").val();
        var str = "<div class='row'>" +
                "<div class='col-md-4'></div>" +
                "<div class=\"col-md-6\">" +
                "<div id='body-total-"+ n + "-"+ idParam +"'>" +
//                "<select class='form-control' id='total-" + n + "-"+ idParam +"'>";
                "<select class='form-control' id='total-" + n + "-"+ idParam +"' onchange=\"changeInput(\'total-" + n + "-" + idParam + "\', this.value)\">";

        str += "<option value=''>[Select One]</option>" +
            "<option value='combo'>Combo Nilai Dasar</option>"+
            "<option value='input'>Input Manual</option>";

//            BudgetingNilaiDasarAction.getListNilaiDasarEdit(tahun, function (list) {
//                $.each(list, function (i, item) {
//                    str += "<option value='"+item.nilai+"' >"+item.keterangan+"</option>";
//                });
//            });

        str += "</select>" +
            "</div>" +
            "</div>" +
            "<div class=\"col-md-2\">"+
                "<select id='opr-"+ n +"-"+ idParam +"' class='form-control'>" +
                    "<option value='='>(=) Sama Dengan</option>" +
                    "<option value='*'>(X) Kali</option>" +
                    "<option value='+'>(+) Tambah</option>" +
                    "<option value='-'>(-) Kurangi</option>" +
                    "<option value='/'>(/) Bagi</option>" +
                "</select>" +
            "</div>" +
            "</div>";

        n = n + 1;
        var i = n - 1;
        str += "<div id='hitung-"+ n +"'></div>";
        $("#hitung-" + i ).html(str);
        console.log("n = " + i);
    }

    function changeInput(id, value) {
        var str = "";
        if (value == "combo"){
            BudgetingNilaiDasarAction.getListNilaiDasarEdit(tahun, function (list) {
                $.each(list, function (i, item) {
                    str += "<option value='"+item.nilai+"' >"+item.keterangan+"</option>";
                });
                $("#"+id).html(str);
                $("#"+id).removeAttr('onchange');
            });
        } else {
            str += "<input type=\"number\" class=\"form-control\" id=\""+id+"\" />";
            $("#body-"+id).html(str);
        }
    }

    function showAdd(idParam, divisi, master) {
        $("#modal-add").modal('show');
        $("#id-param").val(idParam);
        listOfParam = [];
        n = 0;


        var label = $("#label-head-"+idParam).text();

        var str = "<div class=\"row\">" +
            "<label class=\"col-md-4\">Nilai Pendapatan</label>" +
            "<div class=\"col-md-6\">" +
            "<input type=\"number\" class=\"form-control\" id=\"total-"+n+"-"+idParam+"\" />" +
            "</div>" +
            "<div class=\"col-md-2\">"+
//            "<div class=\"col-md-4\">"+
            "<select id='opr-"+ n +"-"+ idParam +"' class='form-control'>" +
            "<option value='='>(=) Sama Dengan</option>" +
            "<option value='*'>(X) Kali</option>" +
            "<option value='+'>(+) Tambah</option>" +
            "<option value='-'>(-) Kurangi</option>" +
            "<option value='/'>(/) Bagi</option>" +
            "<option value='='>(=) Sama Dengan</option>" +
            "</select>" +
            "</div>" +
            "</div>";

            n = n + 1;

            str += "<div id='hitung-"+ n +"'></div>";

        listOfParam.push({"id":"total-"+idParam, "opr":"="});

//        listOfParam.push(
//            {"id":"total-"+idParam, "opr":"*"},
//            {"id":"tt-"+idParam, "opr":"*"},
//            {"id":"bor-"+idParam, "opr":"="}
//        );


        $("#id-param").val(idParam);
        $("#masterid").val(master);
        $("#divisiid").val(divisi);
        $("#label-edit").text(label);
        $("#body-nilai").html(str);
    }

    function showDetail() {

        var idParam = $("#id-param").val();
        var label = $("#label-edit").text();
        $("#label-tipe").text(label);
        $("#label-periode").text("Periode Januari Hingga " + stBulan(getDateParted('MONTH') - 1) + " " + tahun);

        $("#modal-view-pendapatan").modal('show');
        var head = "";
        var str = "";
        var total = "";
        var totalDiskon = "";
        if (idParam == "PDTRJTDN"){
            head = "<td>Nama</td>" +
                "<td>Harga</td>"+
                "<td>Harga Diskon</td>"+
                "<td>Qty</td>" +
                "<td>Total</td>"+
                "<td>Total Diskon</td>";

            BgPendapatanAction.getListPendapatanTindakan(unit, tahun, "RJ", function (list) {
                $.each(list, function (i, item) {
                    str += "<tr>" +
                        "<td>"+item.namaTindakan+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.harga)+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.hargaDiskon)+"</td>" +
                        "<td>"+item.qty+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.totalHarga)+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.totalHargaDiskon)+"</td>" +
                        "</tr>";

                    total += parseInt(item.totalHarga);
                    totalDiskon += parseInt(nullEscape(item.totalHargaDiskon));
                });
                str += "<tr>" +
                    "<td align='right' colspan='4'>Total : </td>" +
                    "<td align='right'>"+ total +"</td>" +
                    "<td align='right'>"+ totalDiskon +"</td>" +
                    "</tr>";

                $("#head-list-pendapatan").html(head);
                $("#body-list-pendapatan").html(str);
            });
        }
        if (idParam == "PDTRITDN"){
            head = "<td>Nama</td>" +
                "<td>Harga</td>"+
                "<td>Harga Diskon</td>"+
                "<td>Qty</td>" +
                "<td>Total</td>"+
                "<td>Total Diskon</td>";

            BgPendapatanAction.getListPendapatanTindakan(unit, tahun, "RI", function (list) {
                $.each(list, function (i, item) {
                    str += "<tr>" +
                        "<td>"+item.namaTindakan+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.harga)+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.hargaDiskon)+"</td>" +
                        "<td>"+item.qty+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.totalHarga)+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.totalHargaDiskon)+"</td>" +
                        "</tr>";

                    total += parseInt(item.totalHarga);
                    totalDiskon += parseInt(nullEscape(item.totalHargaDiskon));
                });
                str += "<tr>" +
                    "<td align='right' colspan='4'>Total : </td>" +
                    "<td align='right'>"+ total +"</td>" +
                    "<td align='right'>"+ totalDiskon +"</td>" +
                    "</tr>";

                $("#head-list-pendapatan").html(head);
                $("#body-list-pendapatan").html(str);
            });
        }
        if (idParam == "PDTRIKMR"){

        }
        if (idParam == "PDTRJOBT"){
            head = "<td>Nama</td>" +
                "<td>Harga</td>"+
                "<td>Harga Diskon</td>"+
                "<td>Qty</td>" +
                "<td>Total</td>"+
                "<td>Total Diskon</td>";

            BgPendapatanAction.getListPendapatanObat(unit, tahun, "RJ", function (list) {
                $.each(list, function (i, item) {
                    str += "<tr>" +
                        "<td>"+item.namaTindakan+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.harga)+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.hargaDiskon)+"</td>" +
                        "<td>"+item.qty+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.totalHarga)+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.totalHargaDiskon)+"</td>" +
                        "</tr>";

                    total += parseInt(item.totalHarga);
                    totalDiskon += parseInt(nullEscape(item.totalHargaDiskon));
                });
                str += "<tr>" +
                    "<td align='right' colspan='4'>Total : </td>" +
                    "<td align='right'>"+ total +"</td>" +
                    "<td align='right'>"+ totalDiskon +"</td>" +
                    "</tr>";

                $("#head-list-pendapatan").html(head);
                $("#body-list-pendapatan").html(str);
            });
        }
        if (idParam == "PDTRIOBT"){
            head = "<td>Nama</td>" +
                "<td>Harga</td>"+
                "<td>Harga Diskon</td>"+
                "<td>Qty</td>" +
                "<td>Total</td>"+
                "<td>Total Diskon</td>";

            BgPendapatanAction.getListPendapatanObat(unit, tahun, "RI", function (list) {
                $.each(list, function (i, item) {
                    str += "<tr>" +
                        "<td>"+item.namaTindakan+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.harga)+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.hargaDiskon)+"</td>" +
                        "<td>"+item.qty+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.totalHarga)+"</td>" +
                        "<td align='right'>"+ formatRupiah(item.totalHargaDiskon)+"</td>" +
                        "</tr>";

                    total += parseInt(item.totalHarga);
                    totalDiskon += parseInt(nullEscape(item.totalHargaDiskon));
                });
                str += "<tr>" +
                    "<td align='right' colspan='4'>Total : </td>" +
                    "<td align='right'>"+ total +"</td>" +
                    "<td align='right'>"+ totalDiskon +"</td>" +
                    "</tr>";

                $("#head-list-pendapatan").html(head);
                $("#body-list-pendapatan").html(str);
            });

        }
        if (idParam == "PDTRJLAB"){

        }
        if (idParam == "PDTRJRGI"){

        }
        if (idParam == "PDTRILAB"){

        }
        if (idParam == "PDTRIRGI"){

        }
    }

    function stBulan(bulan) {
        if (bulan == "1")
            return "Januari";
        if (bulan == "2")
            return "Februari";
        if (bulan == "3")
            return "Maret";
        if (bulan == "4")
            return "April";
        if (bulan == "5")
            return "Mei";
        if (bulan == "6")
            return "Juni";
        if (bulan == "7")
            return "Juli";
        if (bulan == "8")
            return "Agustus";
        if (bulan == "9")
            return "September";
        if (bulan == "10")
            return "Oktober";
        if (bulan == "11")
            return "November";
        if (bulan == "12")
            return "Desember";
    }

    function getDateParted(tipe) {
        var d = new Date();
        if (tipe == "YEAR")
            return d.getFullYear();
        if (tipe == "MONTH")
            return d.getMonth() + 1;
        if (tipe == "DATE")
            return d.getDate();
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

    function initForm() {
        var host = firstpath()+"/bginvestasi/initForm_bginvestasi.action";
        post(host);
    }

    function add() {
        $("#modal-add-coa").modal('show');
        $("#rekeningid").val("");
        $("#coa").val("");
        $("#namacoa").val("");
    }

    function addCoa() {
        var listData    = [];
        var idParam     = $("#id-param").val();
        var divisiId    = $("#divisiid").val();
        var masterId    = $("#masterid").val();
        var periode     = $("#sel-periode").val();

        for (i=0; i<n;i++){
            var nilai   = $("#total-" + i + "-" + idParam).val();
            var opr     = $("#opr-" + i + "-" + idParam).val();
            listData.push({"nilai":nilai, "opr":opr});
        }

        var stJson = JSON.stringify(listData);
//        dwr.engine.setAsync(true);
        BgPendapatanAction.setPerhitunganToSession(idParam, stJson, masterId, divisiId, tahun, unit, idKategori, periode, function (res) {
//            dwr.engine.setAsync(false);
            if (res.status == "success"){
                refreshAdd();
            } else {

            }
        });

    }

    function refreshAdd() {
        var form = { "budgeting.tahun":tahun, "budgeting.branchId":unit, "budgeting.idKategoriBudgeting":idKategori, "budgeting.namaKategori":namaKategori};
        var host = firstpath()+"/bgpendapatan/add_bgpendapatan.action";
        post(host, form);
    }

    function saveAdd() {
        var tahun = $("#sel-tahun").val();
        var unit = $("#sel-unit").val();
        var tipe = $("#sel-tipe").val();

        BudgetingAction.checkTransaksiBudgeting(unit, tahun, function(response){

            if (response.branchId == null && response.tahun == null){

                BudgetingAction.checkNilaiDasarByTahun(tahun, function(flag){

                    if (flag == "Y"){
                        var form = { "budgeting.tahun":tahun, "budgeting.branchId":unit, "budgeting.tipe":tipe };
                        var host = firstpath()+"/bginvestasi/add_bginvestasi.action?status=add&tipe=detail&trans=ADD_DRAFT";
                        post(host, form);
                    } else {
                        alert("Belum Ada Nilai Dasar untuk Tahun "+ tahun);
                    }
                });
            } else {
                alert("Data Sudah Ada. di tahun "+response.tahun);
            }
        });

//        var form = { "budgeting.tahun":tahun, "budgeting.branchId":unit, "budgeting.tipe":tipe };
//        var host = firstpath()+"/budgeting/add_budgeting.action?status=add&tipe=detail&trans=ADD_DRAFT";
//        post(host, form);
    }

    function hitungSubTotal(var1) {
        var total = 0;
        if ("divisi" == var1){

            var qty = $("#qty").val();
            var nilai = $("#nilai").val();

            total = qty*nilai;
            $("#total-divisi").val(total);
        }
    }

    function nullEscape(n) {
        if (n == null)
            return 0;
        return n;
    }

    function back(){
        var form = { "budgeting.tahun":tahun, "budgeting.branchId":unit, "budgeting.jenis":"add" };
        var host = firstpath()+"/bgpendapatan/add_bgpendapatan.action";
        post(host, form);;
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>