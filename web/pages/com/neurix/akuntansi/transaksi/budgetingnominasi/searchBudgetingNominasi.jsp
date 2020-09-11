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

        /*--body { background-color:#fafafa; font-family:'Open Sans';}*/
        /*.container { margin:150px auto;}*/
        .treegrid-collapsed {
            background-color: #bfbfbf;
        }
        .treegrid-expanded {
            background-color: #e6e6e6;
        }

        .treegrid-indent {width:16px; height: 16px; display: inline-block; position: relative;}

        .treegrid-expander { width:16px; height: 16px; display: inline-block; position: relative; cursor: pointer;}

        /*.treegrid-expander-expanded{background-image: url(collapse.png); }
        .treegrid-expander-collapsed{background-image: url(expand.png);}*/
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SettingTutupPeriodAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TutuPeriodAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BudgetingAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BgNominasiAction.js"/>'></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/lodash.js"/>"></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#bayar_rawat_jalan, #pembayaran_active').addClass('active');
            $('#pembayaran_open').addClass('menu-open');
            changeAction('');
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Budgeting Nominasi</h3>
                    </div>
                    <div class="box-body">
                        <%--<s:form id="kasirjalanForm" method="post" namespace="/kasirjalan" action="search_kasirjalan.action" theme="simple" cssClass="form-horizontal">--%>
                            <div class="form-group form-horizontal">
                                <div class="row">
                                    <div class="col-md-12 col-md-offset-3">
                                        <div class="row">
                                            <label class="control-label col-sm-2">Tahun</label>
                                            <div class="col-sm-2">
                                                <select class="form-control" id="sel-tahun">
                                                    <option value="2020">2020</option>
                                                    <option value="2021">2021</option>
                                                    <option value="2022">2022</option>
                                                    <option value="2023">2023</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="control-label col-sm-2">Unit</label>
                                            <div class="col-sm-2">
                                                <s:if test='budgeting.flagKp == "Y"'>
                                                    <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                    <s:select list="#initComboBranch.listOfComboBranch" id="sel-unit" name="budgeting.branchId"
                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select Value]" cssClass="form-control" onchange="changeAction(this.value)"/>

                                                </s:if>
                                                <s:else>
                                                    <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                    <s:select list="#initComboBranch.listOfComboBranch" name="budgeting.branchId"
                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select Value]" cssClass="form-control" onchange="changeAction(this.value)" disabled="true"/>
                                                    <%--<input type="hidden" id="sel-unit" name="budgeting.branchId"/>--%>
                                                    <s:hidden id="sel-unit" name="budgeting.branchId"/>
                                                </s:else>
                                            </div>
                                        </div>
                                        <%--<div class="row">--%>
                                            <%--<label class="control-label col-sm-2">Status</label>--%>
                                            <%--<div class="col-sm-2">--%>
                                                <%--<select class="form-control" id="sel-status">--%>
                                                    <%--<option value="#">[Select One]</option>--%>
                                                    <%--<option value="DRAFT">DRAFT</option>--%>
                                                    <%--<option value="FINAL">FINAL</option>--%>
                                                    <%--<option value="REVISI">REVISI</option>--%>
                                                <%--</select>--%>  <%--<div class="row">--%>
                                        <%--<label class="control-label col-sm-2">Coa</label>--%>
                                        <%--<div class="col-sm-2">--%>
                                        <%--<input type="text" class="form-control" id="coa">--%>
                                        <%--<script>--%>
                                        <%--$(document).ready(function() {--%>
                                        <%--var functions, mapped;--%>
                                        <%--$('#coa').typeahead({--%>
                                        <%--minLength: 1,--%>
                                        <%--source: function (query, process) {--%>
                                        <%--functions = [];--%>
                                        <%--mapped = {};--%>
                                        <%--var data = [];--%>
                                        <%--dwr.engine.setAsync(false);--%>
                                        <%--KodeRekeningAction.initTypeaheadKodeRekening(query,function (listdata) {--%>
                                        <%--data = listdata;--%>
                                        <%--});--%>
                                        <%--$.each(data, function (i, item) {--%>
                                        <%--var labelItem = item.kodeRekening + " | " + item.namaKodeRekening;--%>
                                        <%--mapped[labelItem] = {--%>
                                        <%--id: item.rekeningId,--%>
                                        <%--nama: item.namaKodeRekening,--%>
                                        <%--kode : item.kodeRekening,--%>
                                        <%--parent :item.parentId--%>
                                        <%--};--%>
                                        <%--functions.push(labelItem);--%>
                                        <%--});--%>
                                        <%--process(functions);--%>
                                        <%--},--%>
                                        <%--updater: function (item) {--%>
                                        <%--var selectedObj = mapped[item];--%>
                                        <%--$('#rekeningid').val(selectedObj.id);--%>
                                        <%--return selectedObj.kode;--%>
                                        <%--}--%>
                                        <%--});--%>
                                        <%--});--%>
                                        <%--</script>--%>
                                        <%--</div>--%>
                                        <%--</div>
                                            <%--</div>--%>
                                        <%--</div>--%>

                                        <input type="hidden" id="rekeningid">

                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-md-offset-4" style="margin-top: 10px">
                                        <button class="btn btn-success" onclick="search()"><i class="fa fa-search"></i> Search</button>
                                        <s:if test='budgeting.flagKp == "Y"'>
                                            <button class="btn btn-primary" onclick="add()" id="btn-add"><i class="fa fa-plus"></i> Add</button>
                                        </s:if>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary"><i class="fa fa-plus"></i> Action</button>
                                            <button type="button" class="btn btn-primary dropdown-toggle"
                                                    data-toggle="dropdown" style="height: 34px">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu" id="action-menu">
                                            </ul>
                                        </div>
                                        <button class="btn btn-danger" onclick="reset()"><i class="fa fa-refresh"></i> Reset</button>
                                    </div>
                                </div>
                            </div>

                        <%--</s:form>--%>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> List Data Budgeting :
                        <strong><span id="label-tahun"></span> - <span id="label-unit"></span></strong>
                        </h3>
                    </div>
                    <div class="box-body">

                        <%--<div class="alert alert-info alert-dismissable" id="alert-info">--%>
                        <%--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>--%>
                        <%--<strong>Info!</strong> Pilih Priode Kemudian Choose--%>
                        <%--</div>--%>

                        <div class="alert alert-success alert-dismissable" id="alert-success" style="display: none">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <center><h5><strong>Loading ...</strong> Mencari data </h5></center>
                        </div>

                        <div class="alert alert-warning alert-dismissable" id="alert-error" style="display: none">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>Error!</strong><span id="error-msg"></span>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <table class="tree table table-bordered table-striped">
                                    <thead id="head-budgeting">
                                        <tr bgcolor="#90ee90">
                                            <td style="width: 20%">COA</td>
                                            <td align="">Keterangan</td>
                                            <td align="center">Nilai Draf</td>
                                            <td align="center">Nilai Final</td>
                                            <td align="center">Nilai Revisi</td>
                                            <td align="">No. Budgeting</td>
                                            <td>Action</td>
                                        </tr>
                                    </thead>
                                    <tbody id="body-budgeting" style="font-size: 13px">
                                    </tbody>
                                    <%--<input type="hidden" id="index-period"/>--%>
                                    <%--<input type="hidden" id="index-branch"/>--%>
                                    <%--<input type="hidden" id="bulan"/>--%>
                                    <%--<input type="hidden" id="tahun"/>--%>
                                </table>
                            </div>
                        </div>

                        <%--<div class="form-group">--%>
                            <%--<div class="col-md-4 col-md-offset-5">--%>
                                <%--<button class="btn btn-success" style="display: none" id="btn-save" onclick="saveBatasPeriod()"><i class="fa fa-check"></i> Save </button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-view">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-file"></i> View Detail Budgeting
                </h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="view-id">
                <table style="font-size: 15px; margin-bottom: 10px;" class="table">
                    <tbody>
                        <tr>
                            <td width="20%">Tahun</td>
                            <td>:</td>
                            <td id="tahun-view"></td>
                        </tr>
                        <tr>
                            <td>Unit</td>
                            <td>:</td>
                            <td id="unit-view"></td>
                        </tr>
                        <tr>
                            <td>COA </td>
                            <td>:</td>
                            <td id="coa-view"></td>
                        </tr>
                    </tbody>
                </table>
                <table class="table table-bordered table-striped">
                    <thead id="head-budgeting-view" style="font-size: 13px">
                    <tr bgcolor="#90ee90">
                        <td>Periode</td>
                        <td id="label-master-id">Master Id</td>
                        <td align="" id="label-master-name">Master Name</td>
                        <td align="" id="label-divisi-id">Divisi Id</td>
                        <td align="" id="label-divisi-name">Divisi Name</td>
                        <td align="center">Qty</td>
                        <td align="center">Nilai</td>
                        <td align="center">Sub Total</td>
                        <td align="center">Realisasi</td>
                        <td align="center">Selisih</td>
                        <td align="center">Action</td>
                    </tr>
                    </thead>
                    <tbody id="body-budgeting-view" style="font-size: 11px">
                    </tbody>
                    <%--<input type="hidden" id="index-period"/>--%>
                    <%--<input type="hidden" id="index-branch"/>--%>
                    <%--<input type="hidden" id="bulan"/>--%>
                    <%--<input type="hidden" id="tahun"/>--%>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-pengadaan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-file"></i> View Investasi
                </h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="view-id-pengadaan">
                <table style="font-size: 15px; margin-bottom: 10px;" class="table">
                    <tbody>
                    <tr>
                        <td width="20%">Tahun</td>
                        <td>:</td>
                        <td id="tahun-view-pengadaan"></td>
                    </tr>
                    <tr>
                        <td>Unit</td>
                        <td>:</td>
                        <td id="unit-view-pengadaan"></td>
                    </tr>
                    <tr>
                        <td>COA </td>
                        <td>:</td>
                        <td id="coa-view-pengadaan"></td>
                    </tr>
                    <tr>
                        <td>Periode </td>
                        <td>:</td>
                        <td id="periode-view-pengadaan"></td>
                    </tr>
                    </tbody>
                </table>
                <table class="table table-bordered table-striped">
                    <thead id="head-budgeting-view-pengadaan" style="font-size: 13px">
                    <tr bgcolor="#90ee90">
                        <td>Nama Investasi</td>
                        <td>No Kontrak</td>
                        <td align="center">Qty</td>
                        <td align="center">Nilai</td>
                        <td align="center">Sub Total</td>
                        <td align="center">Nilai Kontrak</td>
                        <td align="center">Realisasi</td>
                        <td align="center">Selisih</td>
                    </tr>
                    </thead>
                    <tbody id="body-budgeting-view-pengadaan" style="font-size: 13px">
                    </tbody>
                    <%--<input type="hidden" id="index-period"/>--%>
                    <%--<input type="hidden" id="index-branch"/>--%>
                    <%--<input type="hidden" id="bulan"/>--%>
                    <%--<input type="hidden" id="tahun"/>--%>
                </table>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function changeAction(var1){

        var tahun = $("#sel-tahun").val();
        var unit = "";
        if (var1 == null || var1 == "")
            unit = $("#sel-unit").val();
        else
            unit = var1;
        console.log(unit);

        BudgetingAction.findLastStatus(unit, tahun, "nominasi", function(response){
            var str = "";
            if (response.status != ""){
                if ("ADJUST_REVISI" == response.status){
                    str += "<li>"+
                        "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                        "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                        "</li><hr>";

                } else if ("APPROVE_REVISI" == response.status){

                    str += "<li>"+
                        "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                        "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                        "</li><hr>";

                } else if ("EDIT_REVISI" == response.status){
                    str += "<li>"+
                        "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                        "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                        "</li>";

                    str += "<li>"+
                        "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                        "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                        "</li>";

                    str += "<li>"+
                        "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                        "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                        "</li><hr>";
                } else {
                    if ("ADJUST_FINAL" ==  response.status){
                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                            "</li><hr>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                            "</li><hr>";

                    } else if ("APPROVE_FINAL" ==  response.status){
                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                            "</li><hr>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                            "</li><hr>";

                    } else if ("EDIT_FINAL" ==  response.status){
                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
                            "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
                            "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                            "</li><hr>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                            "</li>";

                        str += "<li>"+
                            "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                            "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                            "</li><hr>";
                    } else {
                        if ("ADJUST_DRAFT" == response.status){
                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_DRAFT')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Draft</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                                "</li><hr>";
                        } else if("APPROVE_DRAFT" == response.status){

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_DRAFT')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Draft</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                                "</li><hr>";
                        } else {
                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_DRAFT')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Draft</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_DRAFT')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Draft</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_DRAFT')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Draft</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Final</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_FINAL')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Final</a>"+
                                "</li><hr>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('EDIT_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Edit Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('APPROVE_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Approve Revisi</a>"+
                                "</li>";

                            str += "<li>"+
                                "<a href=\"#\" onclick=\"action('ADJUST_REVISI')\">"+
                                "<i class=\"fa fa-edit\"></i>Adjust Revisi</a>"+
                                "</li><hr>";
                        }
                    }
                }
            } else {
                str = "";
                str += "<li>"+
                    "<span>"+
                    "<i class=\"fa fa-info\"></i>Please Add</span>"+
                    "</li><hr>";
            }

            $("#action-menu").html(str);
        });
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

    function formatRupiah(angka) {
        if(angka != null && angka != '' && angka > 0){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        }else{
            return 0;
        }
    }

    function formatSelisih(angka) {
        if(angka != null && angka != ''){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');

            if (angka < 0){
                return "<span style='color: indianred'> - "+ribuan+"</span>";
            } else {
                return "<span style='color: darkgreen'> + "+ribuan+"</span>";
            }

        }else{
            return 0;
        }
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

    function add() {
        var host = firstpath()+"/bgnominasi/add_bgnominasi.action";
        post(host);
    }

    function reset() {
        var host = firstpath()+"/bgnominasi/initForm_bgnominasi.action";
        post(host);
    }

    $('.tree').treegrid({
        expanderExpandedClass: 'glyphicon glyphicon-minus',
        expanderCollapsedClass: 'glyphicon glyphicon-plus'
    });

    function search() {
        var tahun = $("#sel-tahun").val();
        var unit = $("#sel-unit").val();
        var unitName = $("#sel-unit :selected").text();
        var status = $("#sel-status").val();
        var rekeningid = $("#rekeningid").val();
        var data = [];
        var data2 = [];

        $("#alert-success").show();
        $("#label-unit").text(unitName);
        $("#label-tahun").text(tahun);

        if (unit != ""){
            var arr = [];
//            arr.push({
//                "tahun":tahun,
//                "unit":unit,
//                "status":"",
//                "coa":rekeningid
//            });

//            var strJson = JSON.stringify(arr);
            dwr.engine.setAsync(true);
            BgNominasiAction.getSearchListBudgeting(tahun, unit, function (response) {
                dwr.engine.setAsync(false);
                $("#alert-success").hide();
                if (response.status == "error"){
                    $("#alert-error").show().fadeOut(5000);
                    $("#error-msg").text(response.msg);
                } else {

                    data = response.list;
                    data2 = new Array();
                    $.each(data, function(i,item){
                        console.log(item.rekeningId);
                        data2.push({_id : item.rekeningId, level : item.level,  nama : item.namaKodeRekening, parent : item.parentId, coa : item.kodeRekening,
                            nilaiDraf : item.nilaiDraf, nilaiFinal : item.nilaiFinal, nilaiRevisi : item.nilaiRevisi, noBudgeting : item.noBudgeting,
                            idBudgeting : item.idBudgeting});

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

                    console.log("loop")
                    var strList = "";
                    for(i = 0 ; i < data2.length ; i++){
                        if(data2[i].parent == "-"){
                            strList += '<tr style="font-size: 12px;" class=" treegrid-' + data2[i]._id+ '">' +
                                '<td >' + data2[i].coa + '</td>' +
                                '<td >' + data2[i].nama + '</td>' +
                                "<td align='right'>"+formatRupiah(data2[i].nilaiDraf)+"</td>"+
                                "<td align='right'>"+formatRupiah(data2[i].nilaiFinal)+"</td>"+
                                "<td align='right'>"+formatRupiah(data2[i].nilaiRevisi)+"</td>"+
                                "<td>"+data2[i].noBudgeting+"</td>";
                                "<td align='center'>"+actionView(data2[i].idBudgeting, data2[i].level)+"</td>"+
                                "</tr>";
                        } else {
                            strList += '<tr style="font-size: 12px" class=" treegrid-' + data2[i]._id + ' treegrid-parent-' + data2[i].parent + '">' +
                                + '<td style="border: 2px solid black;">' +
                                '<td >' + data2[i].coa + '</td>' +
                                '<td >' + data2[i].nama + '</td>' +
                                "<td align='right'>"+formatRupiah(data2[i].nilaiDraf)+"</td>"+
                                "<td align='right'>"+formatRupiah(data2[i].nilaiFinal)+"</td>"+
                                "<td align='right'>"+formatRupiah(data2[i].nilaiRevisi)+"</td>"+
                                "<td>"+data2[i].noBudgeting+"</td>"+
                                "<td align='center'>"+actionView(data2[i].idBudgeting, data2[i].level)+"</td>"+
                                "</tr>";
                        }
                    }
                }
//                $('.tree').append(strList);
                $(".tree .ceknull:contains('null')").html("-");
                $("#body-budgeting").html(strList);
                $('.tree').treegrid({
                    expanderExpandedClass: 'glyphicon glyphicon-minus',
                    expanderCollapsedClass: 'glyphicon glyphicon-plus'
                });
            });
        } else {
            $("#alert-success").hide();
            $("#body-budgeting").html("");
            alert("Pilih Unit Terlebih Dahulu");
        }
    }

    function actionView(var1, var2) {
        var str = "";
        if (var2 == "5"){
        str = "<button class='item-view btn btn-sm btn-success' data='"+var1+"'><i class='fa fa-search'></i></button>";
        }
        return str;
    }

    // view Budgeting
    $(".tree").on('click', ".item-view", function () {
        var id = $(this).attr('data');

        BudgetingAction.view(id, function(response){
            console.log(response);

            $("#tahun-view").text(response.tahun);
            $("#unit-view").text(response.branchName);
            $("#coa-view").text(response.kodeRekening + " - " + response.namaKodeRekening);

            var str = "";
            if (response.budgetingDetailList.length > 0){
                var investasi = false;
                $.each(response.budgetingDetailList, function (i, item) {
                    str += "<tr>"+
                            "<td>"+item.tipe+"</td>";
                        if(item.divisiId == "INVS"){
                            investasi = true;
                            str += "<td class='val-master-id' style='display: none'>"+item.masterId+"</td>"+
                                "<td class='val-master-name' style='display: none'>"+item.masterName+"</td>" +
                                "<td style='display: none'>"+item.divisiId+"</td>";
                        } else {
                            str += "<td class='val-master-id' >"+item.masterId+"</td>"+
                                    "<td class='val-master-name' >"+item.masterName+"</td>"+
                                    "<td>"+item.divisiId+"</td>";
                        }
//                            "<td class='val-master-id' >"+item.masterId+"</td>"+
//                            "<td class='val-master-name' >"+item.masterName+"</td>"+

                    str +=
                            "<td>"+item.divisiName+"</td>"+
                            "<td align='center'>"+item.qty+"</td>"+
                            "<td align='right'>"+ formatRupiah(item.nilai)+"</td>"+
                            "<td align='right'>"+ formatRupiah(item.subTotal)+"</td>"+
                            "<td align='right'>"+ formatRupiah(item.saldoAkhir) +"</td>"+
                            "<td align='right'>"+ formatSelisih(item.selisihSaldoAkhir) +"</td>"+
                            "<td align='center'>" + actionViewPengadaan(investasi, item.idBudgetingDetail, item.tipe) + "</td>"+
                            "</tr>";
                });

                if (investasi){
                    str +=  "<td colspan='4'>Nilai Total</td>"+
                        "<td align='right'>"+ formatRupiah(response.nilaiTotal) +"</td>"+
                        "<td align='right'>"+ formatRupiah(response.saldoAkhir) +"</td>"+
                        "<td align='right'>"+ formatSelisih(response.selisihSaldoAkhir) +"</td>"+
                        "<td></td>"+
                        "</tr>";

                    $("#label-master-id").hide();
                    $("#label-master-name").hide();
                    $("#label-divisi-id").hide();
                    $("#label-divisi-id").text("Id");
                    $("#label-divisi-name").text("Name");
                } else {

                    str +=  "<td colspan='7'>Nilai Total</td>"+
                        "<td align='right'>"+ formatRupiah(response.nilaiTotal) +"</td>"+
                        "<td align='right'>"+ formatRupiah(response.saldoAkhir) +"</td>"+
                        "<td align='right'>"+ formatSelisih(response.selisihSaldoAkhir) +"</td>"+
                        "<td></td>"+
                        "</tr>";

                    $("#label-master-id").show();
                    $("#label-master-name").show();
                    $("#label-divisi-id").show();
                    $("#label-divisi-id").text("Divisi Id");
                    $("#label-divisi-name").text("Divisi Name");
                }
            }

            $("#body-budgeting-view").html(str);
        });

        $("#modal-view").modal('show');
        $("#view-id").val(id);
    });

    function actionViewPengadaan(var1, var2, var3) {
        if (var1){
            return "<button class='item-view btn btn-sm btn-success' onclick=\"viewPengadaan('"+var2+"','"+var3+"')\"><i class='fa fa-bars'></i></button>";
        } else {
            return "";
        }
    }

    function viewPengadaan(var1, var2) {

        $("#modal-view-pengadaan").modal('show');

        var tahun = $("#tahun-view").text();
        var unit = $("#unit-view").text();
        var coa = $("#coa-view").text();

        $("#tahun-view-pengadaan").text(tahun);
        $("#unit-view-pengadaan").text(unit);
        $("#coa-view-pengadaan").text(coa);
        $("#periode-view-pengadaan").text(var2.toUpperCase());

        BudgetingAction.viewPengadaan(var1, function (response) {

            if (response.length > 0){

                var str = "";
                $.each(response, function(i, item){
                    str += "<tr>" +
                        "<td>"+ item.namPengadaan+"</td>" +
                        "<td>"+item.noKontrak+"</td>" +
                        "<td align='right'>"+ item.qty+"</td>" +
                        "<td align='right'>"+ formatRupiah( item.nilai ) +"</td>" +
                        "<td align='right'>"+ formatRupiah( item.subTotal )+"</td>" +
                        "<td align='right'>"+ formatRupiah( item.nilaiKontrak ) +"</td>" +
                        "<td align='right'>"+ formatRupiah( item.realisasi ) +"</td>" +
                        "<td align='right'>"+ formatSelisih( item.selisih ) +"</td>" +
                        "</tr>";
                });

                $("#body-budgeting-view-pengadaan").html(str);
            }

        });


    }

    function saveTutup(unit, tahun, bulan) {

        dwr.engine.setAsync(true);

        $("#btn-tutup-"+unit).hide();
        $("#btn-lock-"+unit).hide();
        $("#load-save-"+unit).text("Processing Tutup Period ... ");

        TutuPeriodAction.saveTutupPeriod(unit, tahun, bulan, function(response){
            dwr.engine.setAsync(false);
            if (response.status == "error"){
               searchPeriod();
               $("#alert-error").show().fadeOut(5000);
               $("#error-msg").text(response.msg);
           } else {
               searchPeriod();
               $("#btn-tutup-"+unit).show();
               $("#btn-lock-"+unit).show();
               $("#alert-error").hide();
               $("#alert-success").show().fadeOut(5000);

           }
       });
    }

    function saveLock(unit, tahun, bulan){
        TutuPeriodAction.saveLockPeriod(unit, tahun, bulan, function(response){
            if (response.status == "error"){
                $("#alert-error").show().fadeOut(5000);
                $("#error-msg").text(response.msg);
            } else {
                $("#alert-error").hide();
                $("#alert-success").show().fadeOut(5000);

                searchPeriod();
            }
        });
    }

    function setNullToString(params){
        if (params == null){
            return " - ";
        } else {
            return params;
        }
    }

    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }

    function action(var1){

        var unit    = $("#sel-unit").val();
        var tahun   = $("#sel-tahun").val();

        if ((unit && tahun) != ""){

            var form = {"budgeting.branchId":unit, "budgeting.tahun":tahun};
            var host = firstpath()+"/bgnominasi/edit_bgnominasi.action?status=edit&trans="+var1;
            post(host, form);
        } else {
            alert("Pilih Unit dan Tahun Dulu.")
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>