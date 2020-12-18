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
            Vendor
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
                                                <label class="control-label"><small>Jenis Budgeting :</small></label>
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
                                                <label class="control-label"><small>Kategori Item :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <select id="sel_search_kategori" class="form-control">

                                                    </select>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Master :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <select id="sel_search_master" class="form-control select2" style="width: 100%">

                                                    </select>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Divisi :</small></label>
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
                                                <label class="control-label"><small>Item Rekening :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <select id="sel_search_item_rekening" class="form-control select2" style="width: 100%">

                                                    </select>
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
                                    <center>
                                        <table id="showdata" width="90%">
                                            <tr>
                                                <td align="center">
                                                    <table class="table table-bordered" style="font-size: 13px;">
                                                        <thead style="background-color: springgreen;">
                                                        <tr>
                                                            <td>Jenis</td>
                                                            <td>Ketegori</td>
                                                            <td>Master</td>
                                                            <td>Divisi</td>
                                                            <td>Item</td>
                                                            <td>Action</td>
                                                        </tr>
                                                        </thead>
                                                        <tbody id="body_search">

                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>
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
                                <select id="sel_edit_jenis" class="form-control" onchange="listKetegoriByJenis('sel_edit_kategori', this.value)">

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
                                <select id="sel_edit_kategori" class="form-control">

                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr>
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
                    <tr>
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_edit_divisi" class="form-control select2" style="width: 100%">

                                </select>
                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Item Rekening :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_edit_item_rekening" class="form-control select2" style="width: 100%">

                                </select>
                            </table>

                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" onclick="saveEdit()"><i class="fa fa-check"></i> Save
                </button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
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
                                <select id="sel_delete_jenis" class="form-control" onchange="listKetegoriByJenis('sel_delete_kategori', this.value)" disabled>

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
                                <select id="sel_delete_kategori" class="form-control" disabled>

                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr>
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
                    <tr>
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_delete_divisi" class="form-control select2" style="width: 100%" disabled>

                                </select>
                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Item Rekening :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_delete_item_rekening" class="form-control select2" style="width: 100%" disabled>

                                </select>
                            </table>

                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" onclick="saveEdit()"><i class="fa fa-check"></i> Save
                </button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
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
                                <select id="sel_add_jenis" class="form-control" onchange="listKetegoriByJenis('sel_add_kategori', this.value)">

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
                                <select id="sel_add_kategori" class="form-control">

                                </select>
                            </table>
                        </td>
                    </tr>
                    <tr>
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
                    <tr>
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_add_divisi" class="form-control select2" style="width: 100%">

                                </select>
                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Item Rekening :</small></label>
                        </td>
                        <td>
                            <table>
                                <select id="sel_add_item_rekening" class="form-control select2" style="width: 100%">

                                </select>
                            </table>

                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" onclick="saveAdd()"><i class="fa fa-check"></i> Save
                </button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        listJenisBudgeting("sel_search_jenis");
        listMaster("sel_search_master");
        listPosition("sel_search_divisi");
        listRekening("sel_search_item_rekening");
    });

    function link(){
        window.location.href="<s:url action='initForm_parameterbudgeting'/>";
    }

    function listJenisBudgeting(elid) {
        ParameterBudgetingAction.getAllJenisBudgeting(function (res) {
            var str = "<option value=''>[Select Ones]</option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.namaJenis+'</option>';
            });
            $("#"+elid).html(str);
        });
    }
    function listKetegori(elid) {
        ParameterBudgetingAction.getAllKategoriBudgeting(function (res) {
            var str = "<option value=''>[Select Ones]</option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.nama+'</option>';
            });
            $("#"+elid).html(str);
        });
    }
    function listKetegoriByJenis(elid, jenis) {
        ParameterBudgetingAction.getAllKatagoriByIdJenis(jenis, function (res) {
            var str = "<option value=''>[Select Ones]</option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.nama+'</option>';
            });
            $("#"+elid).html(str);
        });
    }
    function listPosition(elid) {
        ParameterBudgetingAction.getAllPosition(function (res) {
            var str = "<option value=''>[Select Ones]</option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.kodering+'">'+item.positionName+'</option>';
            });
            $("#"+elid).html(str);
        });
    }
    function listMaster(elid) {
        ParameterBudgetingAction.getAllMaster(function (res) {
            var str = "<option value=''>[Select Ones]</option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.primaryKey.nomorMaster+'">'+item.nama+'</option>';
            });
            console.log(res);
            $("#"+elid).html(str);
        });
    }
    function listRekening(elid) {
        ParameterBudgetingAction.getAllParamRekening(function (res) {
            var str = "<option value=''>[Select Ones]</option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.nama+'</option>';
            });
            $("#"+elid).html(str);
        });
    }

    function search() {
        var idjenis         = $("#sel_search_jenis").val() == null ? "" : $("#sel_search_jenis").val();
        var idkategori      = $("#sel_search_kategori").val() == null ? "" : $("#sel_search_kategori").val();
        var masterid        = $("#sel_search_master").val() == null ? "" : $("#sel_search_master").val();
        var divisiid        = $("#sel_search_divisi").val() == null ? "" : $("#sel_search_divisi").val();
        var itemrekening    = $("#sel_search_item_rekening").val() == null ? "" : $("#sel_search_item_rekening").val();
        var ardata          = [];

        ardata.push({"id_jenis_budgeting":idjenis, "id_kategori_budgeting":idkategori, "master_id":masterid, "divisi_id":divisiid, "id_item":itemrekening});
        var stdata = JSON.stringify(ardata);

        ParameterBudgetingAction.search(stdata, function (res) {
            var str = "";
            $.each(res, function (i, item) {
                str += '<tr>'+
                        '<td>'+item.namaJenisBudgeting+'</td>'+
                        '<td>'+item.namaKategoriBudgeting+'</td>'+
                        '<td>'+item.namaMaster+'</td>'+
                        '<td>'+item.namaDivisi+'</td>'+
                        '<td>'+item.namaParamRekening+'</td>'+
                        '<td align="center">' +
                        '<button class="btn btn-primary btn-sm" onclick="edit(\''+item.id+'\')"><i class="fa fa-edit"></i> Edit</button>'+
                        '<button class="btn btn-danger btn-sm" onclick="showDelete(\''+item.id+'\')"><i class="fa fa-time"></i> Delete</button>'+
                        '</td>'+
                        '</tr>';
            });

            $("#body_search").html(str);
        })
    }

    function edit(id) {
        $("#modal-edit").modal('show');
        ParameterBudgetingAction.getFromSession(id, function (res) {
            listJenisBudgeting("sel_edit_jenis");
            listKetegoriByJenis("sel_edit_kategori", res.idJenisBudgeting);
            listMaster("sel_edit_master");
            listPosition("sel_edit_divisi");
            listRekening("sel_edit_item_rekening");

            $("#sel_edit_jenis").val(res.idJenisBudgeting);
            $("#sel_edit_master").val(res.masterId);
            $("#sel_edit_divisi").val(res.divisiId);
            $("#sel_edit_item_rekening").val(res.idParamRekening);
            $("#sel_edit_kategori").val(res.idKategoriBudgeting);
            $("#id_edit").val(res.id);
            $("#flag_edit").val("Y");
        });
    }

    function showDelete(id){
        $("#modal-delete").modal('show');
        ParameterBudgetingAction.getFromSession(id, function (res) {
            listJenisBudgeting("sel_delete_jenis");
            listKetegoriByJenis("sel_delete_kategori", res.idJenisBudgeting);
            listMaster("sel_delete_master");
            listPosition("sel_delete_divisi");
            listRekening("sel_delete_item_rekening");

            $("#sel_delete_jenis").val(res.idJenisBudgeting);
            $("#sel_delete_master").val(res.masterId);
            $("#sel_delete_divisi").val(res.divisiId);
            $("#sel_delete_item_rekening").val(res.idParamRekening);
            $("#sel_delete_kategori").val(res.idKategoriBudgeting);
            $("#id_delete").val(res.id);
            $("#flag_delete").val("N");
        });
    }

    function add(){
        $("#modal-add").modal('show');
        listJenisBudgeting("sel_add_jenis");
        listMaster("sel_add_master");
        listPosition("sel_add_divisi");
        listRekening("sel_add_item_rekening");
    }

</script>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>

