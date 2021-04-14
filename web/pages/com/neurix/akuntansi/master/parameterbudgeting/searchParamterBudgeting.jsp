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
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ParameterBudgetingAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <style>
        .modal-backdrop {
            z-index: -1;
        }
    </style>
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
        #view_add_item_rekening:hover {
            cursor: pointer;
        }
        #view_edit_item_rekening:hover {
            cursor: pointer;
        }
        /*#view_delete_item_rekening:hover {*/
            /*cursor: pointer;*/
        /*}*/
    </style>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Item Budgeting
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Item Budgeting</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
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
                                                <label class="control-label"><small>Jenis Budgeting </small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <select id="sel_search_jenis" class="form-control" onchange="listKetegoriByJenis('sel_search_kategori', this.value)">
                                                    </select>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Kategori Item</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <select id="sel_search_kategori" class="form-control" style="margin-top: 7px;">
                                                        <option value=""> - </option>
                                                    </select>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Master</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <select id="sel_search_master" class="form-control select2" style="width: 100%;margin-top: 7px;">
                                                    </select>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Divisi</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <select id="sel_search_divisi" class="form-control select2" style="width: 100%">
                                                    </select>
                                                </table>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Item Rekening</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <input type="text" class="form-control" id="sel_search_item_rekening" style="margin-top:7px;"/>
                                                    <%--<select id="sel_search_item_rekening" class="form-control select2" style="width: 100%">--%>
                                                    <%--</select>--%>
                                                </table>

                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <button class="btn btn-success" onclick="search()"><i class="fa fa-search"></i> Search</button>
                                                </td>
                                                <td>
                                                    <button class="btn btn-primary" onclick="add()"><i class="fa fa-plus"></i> Add</button>
                                                </td>
                                                <td>
                                                    <button class="btn btn-danger" onclick="link()"><i class="fa fa-refresh"></i> Reset</button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>

                                    <br>
                                    <br>
                                    <table class="table table-bordered" style="font-size: 13px;" width="90%">
                                        <thead style="background-color: springgreen;">
                                        <tr>
                                            <td>Jenis</td>
                                            <td>Ketegori</td>
                                            <td>Master</td>
                                            <td>Divisi</td>
                                            <td>Item</td>
                                            <td align="center" width="150px">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody id="body_search">
                                            <%--<s:iterator value="#session.listOfParameterBudgeting" var="row">--%>
                                                <%--<tr>--%>
                                                    <%--<td><s:property value="namaJenisBudgeting"/></td>--%>
                                                    <%--<td><s:property value="namaKategoriBudgeting"/></td>--%>
                                                    <%--<td><s:property value="namaMaster"/></td>--%>
                                                    <%--<td><s:property value="namaDivisi"/></td>--%>
                                                    <%--<td><s:property value="namaParamRekening"/></td>--%>
                                                    <%--<td align="center">--%>
                                                    <%--<button class="btn btn-primary btn-sm" onclick="edit('<s:property value="id"/>')"><i class="fa fa-edit"></i> Edit</button>--%>
                                                    <%--<button class="btn btn-danger btn-sm" onclick="showDelete('<s:property value="id"/>')"><i class="fa fa-time"></i> Delete</button>--%>
                                                    <%--</td>--%>
                                                <%--</tr>--%>
                                            <%--</s:iterator>--%>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<%--modal--%>
<div class="modal fade" id="modal-edit">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Edit Item Budgeting</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="id_edit"/>
                <input type="hidden" id="flag_edit"/>
                <table width="80%" align="center">
                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis Budgeting :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_edit_jenis" class="form-control" onchange="listKetegoriByJenis('sel_edit_kategori', this.value);showMasterAndDivision('edit');resetIfChange('edit')">

                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kategori Item :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_edit_kategori" class="form-control" style="margin-top: 7px;">
                                    <option value=""> - </option>
                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr id="edit-master">
                        <td>
                            <label class="control-label"><small>Master :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_edit_master" class="form-control select2" style="width: 100%">

                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr id="edit-divisi">
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_edit_divisi" class="form-control select2" style="width: 100%" onchange="koderingPosition('edit', this.value)">
                                </select>
                                <input type="hidden" id="sel_edit_kodering"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Item Rekening :</small></label>
                        </td>
                        <td>
                            <table>
                                <input type="text" class="form-control" id="view_edit_item_rekening" onclick="showDetailCoa('edit');" placeholder="Klik Untuk Input" style="margin-top:7px;" readonly/>
                                <input type="hidden" id="sel_edit_item_rekening"/>
                                <%--<select id="sel_edit_item_rekening" class="form-control select2" style="width: 100%">--%>
                                <%--</select>--%>
                            </table>

                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" onclick="saveEdit()"><i class="fa fa-check"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-delete">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Delete Item Budgeting</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="id_delete"/>
                <input type="hidden" id="flag_delete"/>
                <table width="80%" align="center">
                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis Budgeting :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_delete_jenis" class="form-control" onchange="listKetegoriByJenis('sel_delete_kategori', this.value);showMasterAndDivision('delete')" disabled>

                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kategori Item :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_delete_kategori" class="form-control" style="margin-top: 7px" disabled>
                                    <option value=""> - </option>
                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr id="delete-master">
                        <td>
                            <label class="control-label"><small>Master :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_delete_master" class="form-control select2" style="width: 100%" disabled>

                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr id="delete-divisi">
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_delete_divisi" class="form-control select2" style="width: 100%" disabled>
                                </select>
                                <input type="hidden" id="sel_delete_kodering"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Item Rekening :</small></label>
                        </td>
                        <td>
                            <table>
                                <input type="text" class="form-control" id="view_delete_item_rekening" style="margin-top:7px;" onclick="" placeholder="Klik Untuk Input" readonly/>
                                <input type="hidden" id="sel_delete_item_rekening"/>
                                <%--<select id="sel_delete_item_rekening" class="form-control select2" style="width: 100%" disabled>--%>
                                <%--</select>--%>
                            </table>

                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-danger" onclick="saveDelete()"><i class="fa fa-trash"></i> Delete
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Add Item Budgeting</h4>
            </div>
            <div class="modal-body">
                <table width="80%" align="center">
                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis Budgeting :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_add_jenis" class="form-control" onchange="listKetegoriByJenis('sel_add_kategori', this.value);showMasterAndDivision('add');resetIfChange('add')">

                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kategori Item :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_add_kategori" class="form-control" style="margin-top: 7px">
                                    <option value=""> - </option>
                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr id="add-master">
                        <td>
                            <label class="control-label"><small>Master :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_add_master" class="form-control select2" style="width: 100%">

                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr id="add-divisi">
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_add_divisi" class="form-control select2" style="width: 100%" onchange="koderingPosition('add', this.value)">

                                </select>
                                <input type="hidden" id="sel_add_kodering"/>
                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Item Rekening :</small></label>
                        </td>
                        <td>
                            <table>
                                <input type="text" class="form-control" id="view_add_item_rekening" style="margin-top:7px;" onclick="showDetailCoa('add')" placeholder="Klik Untuk Input" readonly/>
                                <input type="hidden" id="sel_add_item_rekening">
                                <%--<select id="sel_add_item_rekening" class="form-control select2" style="width: 100%">--%>
                                <%--</select>--%>
                            </table>

                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" onclick="saveAdd()"><i class="fa fa-check"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-coa">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> List COA
                </h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered" style="font-size: 13px">
                    <thead>
                    <tr>
                        <td>COA</td>
                        <td>Nama COA</td>
                        <td>Action</td>
                    </tr>
                    </thead>
                    <tbody id="list-coa">
                    <%--<s:iterator value="#session.listOfCoa" var="row">--%>
                        <%--<tr>--%>
                            <%--<td><s:property value="kodeRekening"/></td>--%>
                            <%--<td><s:property value="namaKodeRekening"/></td>--%>
                            <%--<td align="center">--%>
                            <%--<button class="btn btn-primary btn-sm" onclick="edit('<s:property value="rekeningId"/>')"><i class="fa fa-edit"></i> Pilih</button>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                    <%--</s:iterator>--%>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-success-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Success
                </h4>
            </div>
            <div class="modal-body" style="text-align: center">
                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                     name="icon_success">
                Record has been saved successfully.
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <button type="button" class="btn btn-sm btn-success" onclick="link()"><i class="fa fa-check"></i> Ok
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4>Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        listJenisBudgeting("sel_search_jenis");
        listMaster("sel_search_master");
        listPosition('search');
        listRekening("sel_search_item_rekening");
    });

    function link(){
        window.location.href="<s:url action='initForm_parameterbudgeting'/>";
    }

    function listJenisBudgeting(elid) {
        ParameterBudgetingAction.getAllJenisBudgeting(function (res) {
            var str = "<option value=''> - </option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.namaJenis+'</option>';
            });
            $("#"+elid).html(str);
        });
    }
    function listKetegori(elid) {
        ParameterBudgetingAction.getAllKategoriBudgeting(function (res) {
            var str = "<option value=''> - </option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.nama+'</option>';
            });
            $("#"+elid).html(str);
        });
    }
    function listKetegoriByJenis(elid, jenis) {
        ParameterBudgetingAction.getAllKatagoriByIdJenis(jenis, function (res) {
            var str = "<option value=''> - </option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.nama+'</option>';
            });
            $("#"+elid).html(str);
        });
    }
    function listPosition(elid) {
        ParameterBudgetingAction.getAllPosition(function (res) {
            var str = "<option value=''> - </option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.positionId+'">'+item.positionName+'</option>';
            });
            $("#sel_"+elid+"_divisi").html(str);
        });
    }

    function koderingPosition(elid, positionId) {
        ParameterBudgetingAction.getKoderingByPositionId(positionId, function (res) {
            $("#sel_"+elid+"_kodering").val(res);
        });
    }

    function listMaster(elid) {
        ParameterBudgetingAction.getAllMaster(function (res) {
            var str = "<option value=''> - </option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.primaryKey.nomorMaster+'">'+item.nama+'</option>';
            });
            console.log(res);
            $("#"+elid).html(str);
        });
    }
    function listRekening(elid) {
        ParameterBudgetingAction.getAllParamRekening(function (res) {
            var str = "<option value=''> - </option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.nama+'</option>';
            });
            $("#"+elid).html(str);
        });
    }

    function search() {
        dwr.engine.setAsync(true);
        showDialogModal("loading", 'show');
        var idjenis         = $("#sel_search_jenis").val() == null ? "" : $("#sel_search_jenis").val();
        var idkategori      = $("#sel_search_kategori").val() == null ? "" : $("#sel_search_kategori").val();
        var masterid        = $("#sel_search_master").val() == null ? "" : $("#sel_search_master").val();
        var divisiid        = $("#sel_search_divisi").val() == null ? "" : $("#sel_search_divisi").val();
        var itemrekening    = $("#sel_search_item_rekening").val() == null ? "" : $("#sel_search_item_rekening").val();
        var ardata          = [];

        ardata.push({"id_jenis_budgeting":idjenis, "id_kategori_budgeting":idkategori, "master_id":masterid, "divisi_id":divisiid, "id_item":itemrekening});
        var stdata = JSON.stringify(ardata);

        ParameterBudgetingAction.search(stdata, function (res) {
            showDialogModal("loading", 'hide');
            dwr.engine.setAsync(false);
            var str = "";
            $.each(res, function (i, item) {
                str += '<tr>'+
                        '<td>'+item.namaJenisBudgeting+'</td>'+
                        '<td>'+item.namaKategoriBudgeting+'</td>'+
                        '<td>'+nullEscape(item.namaMaster)+'</td>'+
                        '<td>'+nullEscape(item.namaDivisi)+'</td>'+
                        '<td>'+nullEscape(item.namaKodeRekening)+'</td>'+
                        '<td align="center">' +
                        '<button class="btn btn-primary btn-sm" onclick="edit(\''+item.id+'\')"><i class="fa fa-edit"></i> Edit</button>'+
                        '<button class="btn btn-danger btn-sm" onclick="showDelete(\''+item.id+'\')"><i class="fa fa-trash"></i> Delete</button>'+
                        '</td>'+
                        '</tr>';
            });

            $("#body_search").html(str);
            myTable();
        })
    }

    function nullEscape(id) {
        if (id == null || id == ""){
            return "-";
        }
        return id;
    }

    function myTable(){
        $('#myTable').DataTable();
    }

    function edit(id) {
        $("#modal-edit").modal('show');
        ParameterBudgetingAction.getFromSession(id, function (res) {
            listJenisBudgeting("sel_edit_jenis");
            listKetegoriByJenis("sel_edit_kategori", res.idJenisBudgeting);
            listMaster("sel_edit_master");
            listPosition("edit");

            $("#sel_edit_jenis").val(res.idJenisBudgeting);
            $("#sel_edit_master").val(res.masterId);
            $("#sel_edit_divisi").val(res.divisiId);
            $("#view_edit_item_rekening").val(res.namaKodeRekening);
            $("#sel_edit_item_rekening").val(res.rekeningId);
            $("#sel_edit_kategori").val(res.idKategoriBudgeting);
            $("#id_edit").val(res.id);
            $("#flag_edit").val("Y");
            showMasterAndDivision('edit');

        });
    }

    function showDelete(id){
        $("#modal-delete").modal('show');
        ParameterBudgetingAction.getFromSession(id, function (res) {
            listJenisBudgeting("sel_delete_jenis");
            listKetegoriByJenis("sel_delete_kategori", res.idJenisBudgeting);
            listMaster("sel_delete_master");
            listPosition("delete");

            $("#sel_delete_jenis").val(res.idJenisBudgeting);
            $("#sel_delete_master").val(res.masterId);
            $("#sel_delete_divisi").val(res.divisiId);
            $("#view_delete_item_rekening").val(res.namaKodeRekening);
            $("#sel_delete_item_rekening").val(res.rekeningId);
            $("#sel_delete_kategori").val(res.idKategoriBudgeting);
            $("#id_delete").val(res.id);
            $("#flag_delete").val("N");
            showMasterAndDivision('delete');
        });
    }

    function add(){
        $("#modal-add").modal('show');
        listJenisBudgeting("sel_add_jenis");
        listMaster("sel_add_master");
        listPosition("add");
//        listRekening("sel_add_item_rekening");
    }

    function saveEdit() {

        dwr.engine.setAsync(true);
        showDialogModal("loading");

        var id              = $("#id_edit").val();
        var idJenis         = $("#sel_edit_jenis").val();
        var idKategori      = $("#sel_edit_kategori").val();
        var masterId        = $("#sel_edit_master").val();
        var divisiId        = $("#sel_edit_divisi").val();
        var kodering        = $("#sel_edit_kodering").val();
        var idParam         = $("#sel_edit_item_rekening").val();
        var flag            = $("#flag_edit").val();
        var arData          = [];

        arData.push({"id":id, "id_jenis_budgeting":idJenis, "id_kategori_budgeting":idKategori, "master_id":masterId, "divisi_id":kodering, "position_id" : divisiId, "rekening_id":idParam, "flag":flag});
        var stData          = JSON.stringify(arData);
        ParameterBudgetingAction.saveEdit(stData, function (res) {
            dwr.engine.setAsync(false);
            if (res.status == "success"){
                showDialogModal("success", 'show');
            } else {
                showDialogModal("error", 'show', res.msg);
            }
            if (flag == "Y"){
                $("#modal-edit").modal('hide');
            } else {
                $("#modal-delete").modal('hide');
            }
        });
    }

    function saveDelete() {

        dwr.engine.setAsync(true);
        showDialogModal("loading");

        var id              = $("#id_delete").val();
        var idJenis         = $("#sel_delete_jenis").val();
        var idKategori      = $("#sel_delete_kategori").val();
        var masterId        = $("#sel_delete_master").val();
        var divisiId        = $("#sel_delete_divisi").val();
        var kodering         = $("#sel_delete_kodering").val();
        var idParam         = $("#sel_delete_item_rekening").val();
        var flag            = $("#flag_delete").val();
        var arData          = [];

        arData.push({"id":id, "id_jenis_budgeting":idJenis, "id_kategori_budgeting":idKategori, "master_id":masterId,"divisi_id":kodering, "position_id" : divisiId, "rekening_id":idParam, "flag":flag});
        var stData          = JSON.stringify(arData);
        ParameterBudgetingAction.saveEdit(stData, function (res) {
            dwr.engine.setAsync(false);
            if (res.status == "success"){
                showDialogModal("success",'show');
            } else {
                showDialogModal("error",'show', res.msg);
            }
            if (flag == "Y"){
                $("#modal-edit").modal('hide');
            } else {
                $("#modal-delete").modal('hide');
            }
        });
    }

    function saveAdd() {
        dwr.engine.setAsync(true);
        showDialogModal("loading");
        var idJenis         = $("#sel_add_jenis").val();
        var idKategori      = $("#sel_add_kategori").val();
        var masterId        = $("#sel_add_master").val();
        var divisiId        = $("#sel_add_divisi").val();
        var kodering        = $("#sel_add_kodering").val();
        var idParam         = $("#sel_add_item_rekening").val();
        var arData          = [];

        arData.push({"id_jenis_budgeting":idJenis, "id_kategori_budgeting":idKategori, "master_id":masterId, "divisi_id":kodering, "position_id" : divisiId ,"rekening_id":idParam});
        var stData          = JSON.stringify(arData);
        ParameterBudgetingAction.saveAdd(stData, function (res) {
            dwr.engine.setAsync(false);
            if (res.status == "success"){
                showDialogModal("success", 'show');
            } else {
                showDialogModal("error", 'show', res.msg);
            }
            $("#modal-add").modal('hide');
//            link();
        });
    }

    function showDetailCoa(idelement) {

        var idJenisBudgeting = "";
        if (idelement == "add")
            idJenisBudgeting = $("#sel_add_jenis option:selected").val();
        if (idelement == "edit")
            idJenisBudgeting = $("#sel_edit_jenis option:selected").val();

        if (idJenisBudgeting == ""){
            alert("Pilih Jenis Budgeting Terlebih Dahulu");
        } else {
            $("#modal-view-coa").modal('show');
            ParameterBudgetingAction.getTipeKodeRekening(idJenisBudgeting, function (res_idJenis) {

                ParameterBudgetingAction.getListKodeRekeningByTipeCoa(res_idJenis, function (res) {

                    var str = "";
                    if (res.list.length == 0){
                        str += "<tr><td colspan='3' align='center'> Tipe Coa Belum Di Setting Pada Master Kode Rekening </td></tr>"
                    } else {
                        $.each(res.list, function (i, item) {
                            str += "<tr>" +
                                "<td>"+item.kodeRekening+"</td>" +
                                "<td>"+item.namaKodeRekening+"</td>"+
                                "<td align='center'>";
                            if (item.bLevel == 5){
                                str += "<button class='btn btn-sm btn-default' onclick=\"setToInputRekening(\'"+item.rekeningId+"\',\'"+item.namaKodeRekening+"\', \'"+idelement+"\')\"><i class='fa fa-check'></i> Pilih</button>";
                            }
                            str += "</td>" +
                                "</tr>";
                        });
                    }


                    //console.log(res.list.length);
                    $("#list-coa").html(str);
                });
            });
        }
    }

    function setToInputRekening(rekeningId, namaKodeRekening, idelement) {
        $("#sel_"+idelement+"_item_rekening").val(rekeningId);
        $("#view_"+idelement+"_item_rekening").val(namaKodeRekening);
        $("#modal-view-coa").modal('hide');
    }

    function showMasterAndDivision(idelement) {

        var jenis = $("#sel_"+idelement+"_jenis option:selected").val();

        if(jenis == "INV"){
            $("#"+idelement+"-master").hide();
            $("#"+idelement+"-divisi").hide();
        } else if (jenis == "BYA"){
            $("#"+idelement+"-master").hide();
            $("#"+idelement+"-divisi").show();
        } else {
            $("#"+idelement+"-master").show();
            $("#"+idelement+"-divisi").show();
        }
    }

    function resetIfChange(idelement) {
        if(idelement == "edit"){
            $("#sel_"+idelement+"_item_rekening").val('');
            $("#view_"+idelement+"_item_rekening").val('');
        }
    }


</script>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>

