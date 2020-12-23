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
    <script type='text/javascript' src='<s:url value="/dwr/interface/KeteranganObatAction.js"/>'></script>
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
            Keterangan Obat
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Keterangan Obat</h3>
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
                                                <label class="control-label"><small>Jenis Obat :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <select id="sel_search_jenis" class="form-control" onchange="listJenisObatSubByJenis('sel_search_jenis_sub', this.value)">

                                                    </select>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Sub Jenis Obat :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <select id="sel_search_jenis_sub" class="form-control">
                                                        <option value="">[Select Ones]</option>
                                                    </select>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Parameter  :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <select id="sel_search_parameter" class="form-control" style="width: 100%">

                                                    </select>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Keterangan :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                   <input type="text" class="form-control" style="width: 100%;" id="in_search_keterangan">
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
                                                            <td>Sub Jenis</td>
                                                            <td>Parameter</td>
                                                            <td>Keterangan</td>
                                                            <td>Warna</td>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Edit Keterangan Obat</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="id_edit"/>
                <input type="hidden" id="flag_edit"/>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Jenis Obat :</small></label>
                    </div>
                    <div class="col-md-5">
                        <select id="sel_edit_jenis" class="form-control" onchange="listJenisObatSubByJenis('sel_edit_jenis_sub', this.value)">

                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Sub Jenis :</small></label>
                    </div>
                    <div class="col-md-5">
                        <select id="sel_edit_jenis_sub" class="form-control">
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Parameter :</small></label>
                    </div>
                    <div class="col-md-5">
                        <select id="sel_edit_parameter" class="form-control" style="width: 100%" onchange="showWarnaIfLabelWaktu('edit', this.value)">
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Keterangan :</small></label>
                    </div>
                    <div class="col-md-5">
                        <input type="text" class="form-control" style="width: 100%;" id="in_edit_keterangan">
                    </div>
                </div>
                <tr>
                    <hr/>
                </tr>
                <div id="group-warna-edit" style="display: none;">
                    <div class="row">
                        <div class="col-md-3">
                            <label class="control-label"><small>Warna Background :</small></label>
                        </div>
                        <div class="col-md-5">
                            <select id="sel_edit_warna_background" class="form-control" style="width: 100%">
                                <option value="putih"><span class="badge">Putih</span></option>
                                <option value="aquamarine"><span class="badge" style="background-color: aquamarine">Biru Mudah</span></option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label class="control-label"><small>Warna Label :</small></label>
                        </div>
                        <div class="col-md-5">
                            <select id="sel_edit_warna_label" class="form-control" style="width: 100%">
                                <option value="putih"><span class="badge">Putih</span></option>
                                <option value="darkred"><span class="badge" style="background-color: darkred">Merah</span></option>
                                <option value="springgreen"><span class="badge" style="background-color: springgreen">Hijau</span></option>
                                <option value="orange"><span class="badge" style="background-color: orange">Orange</span></option>
                            </select>
                        </div>
                    </div>
                </div>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Delete Keterangan Obat</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="id_delete"/>
                <input type="hidden" id="flag_delete"/>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Jenis Obat :</small></label>
                    </div>
                    <div class="col-md-5">
                        <select id="sel_delete_jenis" class="form-control" onchange="listJenisObatSubByJenis('sel_delete_jenis_sub', this.value)" disabled>

                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Sub Jenis :</small></label>
                    </div>
                    <div class="col-md-5">
                        <select id="sel_delete_jenis_sub" class="form-control" disabled>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Parameter :</small></label>
                    </div>
                    <div class="col-md-5">
                        <select id="sel_delete_parameter" class="form-control" style="width: 100%" onchange="showWarnaIfLabelWaktu('delete', this.value)" disabled>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Keterangan :</small></label>
                    </div>
                    <div class="col-md-5">
                        <input type="text" class="form-control" style="width: 100%;" id="in_delete_keterangan" disabled>
                    </div>
                </div>
                <tr>
                    <hr/>
                </tr>
                <div id="group-warna-delete" style="display: none;">
                    <div class="row">
                        <div class="col-md-3">
                            <label class="control-label"><small>Warna Background :</small></label>
                        </div>
                        <div class="col-md-5">
                            <select id="sel_delete_warna_background" class="form-control" style="width: 100%" disabled>
                                <option value="putih"><span class="badge">Putih</span></option>
                                <option value="aquamarine"><span class="badge" style="background-color: aquamarine">Biru Mudah</span></option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label class="control-label"><small>Warna Label :</small></label>
                        </div>
                        <div class="col-md-5">
                            <select id="sel_delete_warna_label" class="form-control" style="width: 100%" disabled>
                                <option value="putih"><span class="badge">Putih</span></option>
                                <option value="darkred"><span class="badge" style="background-color: darkred">Merah</span></option>
                                <option value="springgreen"><span class="badge" style="background-color: springgreen">Hijau</span></option>
                                <option value="orange"><span class="badge" style="background-color: orange">Orange</span></option>
                            </select>
                        </div>
                    </div>
                </div>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Add Keterangan Budgeting</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Jenis Obat :</small></label>
                    </div>
                    <div class="col-md-5">
                        <select id="sel_add_jenis" class="form-control" onchange="listJenisObatSubByJenis('sel_add_jenis_sub', this.value)">

                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Sub Jenis :</small></label>
                    </div>
                    <div class="col-md-5">
                        <select id="sel_add_jenis_sub" class="form-control">
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Parameter :</small></label>
                    </div>
                    <div class="col-md-5">
                        <select id="sel_add_parameter" class="form-control" style="width: 100%" onchange="showWarnaIfLabelWaktu('add', this.value)">
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label"><small>Keterangan :</small></label>
                    </div>
                    <div class="col-md-5">
                        <input type="text" class="form-control" style="width: 100%;" id="in_add_keterangan">
                    </div>
                </div>
                <tr>
                    <hr/>
                </tr>
                <div id="group-warna-add" style="display: none;">
                    <div class="row">
                        <div class="col-md-3">
                            <label class="control-label"><small>Warna Background :</small></label>
                        </div>
                        <div class="col-md-5">
                            <select id="sel_add_warna_background" class="form-control" style="width: 100%">
                                <option value="white"><span class="badge">Putih</span></option>
                                <option value="aquamarine"><span class="badge" style="background-color: aquamarine">Biru Mudah</span></option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label class="control-label"><small>Warna Label :</small></label>
                        </div>
                        <div class="col-md-5">
                            <select id="sel_add_warna_label" class="form-control" style="width: 100%">
                                <option value="white"><span class="badge">Putih</span></option>
                                <option value="darkred"><span class="badge" style="background-color: darkred">Merah</span></option>
                                <option value="springgreen"><span class="badge" style="background-color: springgreen">Hijau</span></option>
                                <option value="orange"><span class="badge" style="background-color: orange">Orange</span></option>
                            </select>
                        </div>
                    </div>
                </div>
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
        listJenisObat("sel_search_jenis");
        listOfParameter("sel_search_parameter");
    });

    function link(){
        window.location.href="<s:url action='initForm_keteranganobat'/>";
    }

    function listJenisObat(elid) {
        KeteranganObatAction.getAllJenisPersediaanObat(function (res) {
            var str = "<option value=''>[Select Ones]</option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.nama+'</option>';
            });
            $("#"+elid).html(str);
        });
    }
    function listJenisObatSubByJenis(elid, jenis) {
        KeteranganObatAction.getAllJenisPersediaanSubByIdJenis(jenis, function (res) {
            var str = "<option value=''>[Select Ones]</option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.nama+'</option>';
            });
            $("#"+elid).html(str);
        });
    }
    function listOfParameter(elid) {
        KeteranganObatAction.getAllParameterKeterangan(function (res) {
            var str = "<option value=''>[Select Ones]</option>";
            $.each(res, function (i, item) {
                str += '<option value="'+item.id+'">'+item.nama+'</option>';
            });
            console.log(res);
            $("#"+elid).html(str);
        });
    }

    function search() {
        var idjenis         = $("#sel_search_jenis").val() == null ? "" : $("#sel_search_jenis").val();
        var idSubJenis      = $("#sel_search_jenis_sub").val() == null ? "" : $("#sel_search_jenis_sub").val();
        var parameter       = $("#sel_search_parameter").val() == null ? "" : $("#sel_search_parameter").val();
        var keterangan      = $("#in_search_keterangan").val() == null ? "" : $("#in_search_keterangan").val();
        var ardata          = [];

        ardata.push({"id_jenis":idjenis, "id_sub_jenis":idSubJenis, "id_parameter":parameter, "keterangan":keterangan});
        var stdata = JSON.stringify(ardata);

        KeteranganObatAction.search(stdata, function (res) {
            var str = "";
            $.each(res, function (i, item) {
                str += '<tr>'+
                    '<td>'+item.namaJenis+'</td>'+
                    '<td>'+item.namaSubJenis+'</td>'+
                    '<td>'+item.namaParameterKeterangan+'</td>'+
                    '<td>'+item.keterangan+'</td>'+
                    '<td>' +
                    '<span class="badge" style="background-color: '+item.warnaLabel+'; color: black;">'+item.warnaLabel+'</span>' +
                    '<span class="badge" style="background-color: '+item.warnaBackground+'; color: black;">'+item.warnaBackground+'</span>' +
                    '</td>'+
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
        KeteranganObatAction.getFromSession(id, function (res) {
            listJenisObat("sel_edit_jenis");
            listJenisObatSubByJenis("sel_edit_jenis_sub", res.idJenisObat);
            listOfParameter("sel_edit_parameter");
            showWarnaIfLabelWaktu("edit",res.idParameterKeterangan);

            $("#sel_edit_jenis").val(res.idJenis);
            $("#sel_edit_jenis_sub").val(res.idSubJenis);
            $("#sel_edit_parameter").val(res.idParameterKeterangan);
            $("#in_edit_keterangan").val(res.keterangan);
            $("#sel_edit_warna_label").val(res.warnaLabel);
            $("#sel_edit_warna_background").val(res.warnaBackground);
            $("#id_edit").val(res.id);
            $("#flag_edit").val("Y");
        });
    }

    function showDelete(id){
        $("#modal-delete").modal('show');
        KeteranganObatAction.getFromSession(id, function (res) {
            listJenisObat("sel_delete_jenis");
            listJenisObatSubByJenis("sel_delete_jenis_sub", res.idJenisObat);
            listOfParameter("sel_delete_parameter");
            showWarnaIfLabelWaktu("delete",res.idParameterKeterangan);

            $("#sel_delete_jenis").val(res.idJenis);
            $("#sel_delete_jenis_sub").val(res.idSubJenis);
            $("#sel_delete_parameter").val(res.idParameterKeterangan);
            $("#in_delete_keterangan").val(res.keterangan);
            $("#sel_delete_warna_label").val(res.warnaLabel);
            $("#sel_delete_warna_background").val(res.warnaBackground);
            $("#id_delete").val(res.id);
            $("#flag_delete").val("N");
        });
    }

    function add(){
        $("#modal-add").modal('show');
        listJenisObat("sel_add_jenis");
        listOfParameter("sel_add_parameter");
    }

    function saveEdit() {

        var id              = $("#id_edit").val();
        var idJenisSub      = $("#sel_edit_jenis_sub").val();
        var parameter       = $("#sel_edit_parameter").val();
        var keterangan      = $("#in_edit_keterangan").val();
        var warnaLabel      = $("#sel_edit_warna_label").val();
        var warnaBackground = $("#sel_edit_warna_background").val();
        var flag            = $("#flag_edit").val();
        var arData          = [];

        arData.push({
            "id":id,
            "id_sub_jenis":idJenisSub,
            "id_parameter_keterangan":parameter,
            "keterangan":keterangan,
            "warna_label":warnaLabel,
            "warna_background":warnaBackground,
            "flag":flag
        });

        var stData          = JSON.stringify(arData);
        KeteranganObatAction.saveEdit(stData, function (res) {
            if (res.status == "success"){
                alert("Berhasil Save");
            } else {
                alert(res.status);
            }

            if (flag == "Y"){
                $("#modal-edit").modal('hide');
            } else {
                $("#modal-delete").modal('hide');
            }
            link();
        });
    }

    function saveAdd() {

        var idJenisSub      = $("#sel_add_jenis_sub").val();
        var parameter       = $("#sel_add_parameter").val();
        var keterangan      = $("#in_add_keterangan").val();
        var warnaLabel      = $("#sel_add_warna_label").val();
        var warnaBackground = $("#sel_add_warna_background").val();
        var arData          = [];

        if (idJenisSub != "" || parameter != "" || keterangan != ""){
            alert("Lengkapi data terlebih dahulu");
            return false;
        }

        arData.push({
            "id_sub_jenis":idJenisSub,
            "id_parameter_keterangan":parameter,
            "keterangan":keterangan,
            "warna_label":warnaLabel,
            "warna_background":warnaBackground
        });

        var stData          = JSON.stringify(arData);
        KeteranganObatAction.saveAdd(stData, function (res) {
            if (res.status == "success"){
                alert("Berhasil Save");
            } else {
                alert(res.status);
            }
            $("#modal-add").modal('hide');
            link();
        });
    }

    function showWarnaIfLabelWaktu(elid, id) {
        KeteranganObatAction.getParameterKeteranganObatById(id, function (res) {
            if (res.id != "" && res.flagLabelWaktu == "Y"){
                console.log("flag label waktu == Y");
                $("#group-warna-"+elid).show();
            } else {
                $("#group-warna-"+elid).hide();
            }
        });
    }

</script>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>

