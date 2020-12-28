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
        .triangle, .triangle:before, .triangle:after {
            box-sizing: border-box;
        }

        .triangle {
            position: absolute;
            top: 6px;
            left: -10px;
            width: 10px;
            height: 20px;
            border-right: solid 10px #f1f1f1;
            border-bottom: solid 10px transparent;
            border-top: solid 10px transparent;
        }

        input[type="radio"] {
            display: none;
        }
        #colorpicker {
            display: block;
            position: relative;
            width: 20px;
            height: 20px;
        }

        #colorpicker2 {
            display: block;
            position: relative;
            width: 20px;
            height: 20px;
        }

        .hidden-menu {
            display: block;
            position: absolute;
            width: 100%;
            min-width: 100px;
            left: 35px;
            top: -5px;
            padding: 0px;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.2), 0 1px 3px 0 rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            z-index: 1;
            background: #fff;
            text-align: left;
        }

        .hidden-menu2 {
            display: block;
            position: absolute;
            width: 100%;
            min-width: 150px;
            left: 35px;
            top: -5px;
            padding: 0px;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.2), 0 1px 3px 0 rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            z-index: 1;
            background: #fff;
            text-align: left;
        }

        #show-hidden-menu {
            display: block;
            position: absolute;
            width: 20px;
            height: 20px;
            padding: 0;
            border-radius: 100%;
            box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.26);
            transition: ease 0.3s;
            background: #fff;
            cursor: pointer;
            /*transition: ease .3s;*/
        }

        .bundar {
            display: block;
            position: absolute;
            width: 20px;
            height: 20px;
            padding: 0;
            border-radius: 100%;
            box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.26);
            transition: ease 0.3s;
            background: #fff;
            cursor: pointer;
            text-align: center;
            /*transition: ease .3s;*/
        }

        #show-hidden-menu2 {
            display: block;
            position: absolute;
            width: 20px;
            height: 20px;
            padding: 0;
            border-radius: 100%;
            box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.26);
            transition: ease 0.3s;
            background: #fff;
            cursor: pointer;
            /*transition: ease .3s;*/
        }

        .button {
            display: inline-block;
            position: relative;
            width: 20px;
            height: 20px;
            margin: 5px;
            cursor: pointer;
        }

        .button span {
            display: block;
            position: absolute;
            width: 20px;
            height: 20px;
            padding: 0;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            border-radius: 100%;
            background: #eeeeee;
            box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.26);
            transition: ease 0.3s;
        }

        .button span:hover {
            padding: 2px;
        }

        .col1 .button span {
            background: white;
        }

        .col2 .button span {
            background: aquamarine;
        }

        .col3 .button span {
            background: darkred;
        }

        .col4 .button span {
            background: springgreen;
        }

        .col5 .button span {
            background: orange;
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
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Keterangan Obat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="checkupDetailForm" method="post" namespace="/keteranganobat" action="searchKeteranganObat_keteranganobat.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Jenis Obat</label>
                                    <div class="col-sm-4">
                                        <select name="keteranganObat.idJenis" id="sel_search_jenis" class="form-control" onchange="listJenisObatSubByJenis('sel_search_jenis_sub', this.value)">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Sub Jenis Obat</label>
                                    <div class="col-sm-4">
                                        <select name="keteranganObat.idSubJenis" id="sel_search_jenis_sub" class="form-control">
                                            <option value="">[Select Ones]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Parameter</label>
                                    <div class="col-sm-4">
                                        <select name="keteranganObat.idParameterKeterangan" id="sel_search_parameter" class="form-control" style="width: 100%">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Keterangan</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssClass="form-control" name="keteranganObat.keterangan" type="text" cssStyle="width: 100%;" id="in_search_keterangan"></s:textfield>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="checkupDetailForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a class="btn btn-primary" onclick="showModal('add','')"><i class="fa fa-plus"></i> Add</a>
                                        <a class="btn btn-danger" href="initForm_keteranganobat.action"><i class="fa fa-refresh"></i> Reset</a>
                                    </div>
                                </div>
                                <div class="row">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">
                                        <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Searching ...">
                                            Please don't close this window, server is processing your request ...
                                            <br>
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
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         link();
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="close_pos"></s:hidden>
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Keterangan Obat</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Jenis</td>
                                <td>Sub Jenis</td>
                                <td>Parameter</td>
                                <td>Keterangan</td>
                                <td align="center" width="10%">Warna</td>
                                <td align="center" width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfKeteranganObat" var="row">
                                <tr>
                                    <td><s:property value="namaJenis"/></td>
                                    <td><s:property value="namaSubJenis"/></td>
                                    <td><s:property value="namaParameterKeterangan"/></td>
                                    <td><s:property value="keterangan"/></td>
                                    <td align="center">
                                        <script>
                                            var label = '<s:property value="warnaLabel"/>';
                                            var back = '<s:property value="warnaBackground"/>';
                                            if(label && back != ''){
                                                var set = '<div class="text-center">' +
                                                    '<div class="bundar" style="background-color: '+label+'"></div>\n' +
                                                    '<div class="bundar" style="background-color: '+back+'; margin-left: 30px"></div>'
                                                    '</div>';
                                                document.write(set);
                                            }
                                        </script>
                                        <%--<s:property value="warnaLabel"/><s:property value="warnaBackground"/></td>--%>
                                    <td align="center">
                                        <img class="hvr-grow"
                                             onclick="showModal('edit', '<s:property value="id"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('delete', '<s:property value="id"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/cancel-flat-new.png"/>">
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
</div>

<%--modal--%>
<div class="modal fade" id="modal-add">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Add Keterangan Budgeting</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-3">
                        <label class="control-label">Jenis Obat</label>
                    </div>
                    <div class="col-md-9">
                        <select id="sel_add_jenis" class="form-control" onchange="listJenisObatSubByJenis('sel_add_jenis_sub', this.value)">
                            <option value="">[Select One]</option>
                        </select>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="col-md-3">
                        <label class="control-label">Sub Jenis</label>
                    </div>
                    <div class="col-md-9">
                        <select id="sel_add_jenis_sub" class="form-control">
                            <option value="">[Select One]</option>
                        </select>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="col-md-3">
                        <label class="control-label">Parameter</label>
                    </div>
                    <div class="col-md-9">
                        <select id="sel_add_parameter" class="form-control" style="width: 100%" onchange="showWarnaIfLabelWaktu('add', this.value)">
                        </select>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="col-md-3">
                        <label class="control-label">Keterangan</label>
                    </div>
                    <div class="col-md-9">
                        <input type="text" class="form-control" style="width: 100%;" id="in_add_keterangan">
                    </div>
                </div>
                <tr>
                    <hr/>
                </tr>
                <div id="group-warna-add" style="display: none;">
                    <div class="row">
                        <div class="col-md-3">
                            <label class="control-label">Warna Background</label>
                        </div>
                        <div class="col-md-9">
                            <div id="colorpicker"><div id="show-hidden-menu"></div>
                                <div class="hidden-menu" style="display: none;"><div class="triangle"></div>
                                    <input type="hidden" value="white" id="sel_add_warna_label">
                                    <label class="col1">
                                        <input type="radio" name="color" value="white" onclick="changeclr(this)">
                                        <div class="button"><span></span></div>
                                    </label>

                                    <label class="col2">
                                        <input type="radio" name="color" value="aquamarine" onclick="changeclr(this)">
                                        <div class="button"><span></span></div>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 7px">
                        <div class="col-md-3">
                            <label class="control-label">Warna Label</label>
                        </div>
                        <div class="col-md-9">
                            <div id="colorpicker2"><div id="show-hidden-menu2"></div>
                                <div class="hidden-menu2" style="display: none;"><div class="triangle"></div>
                                    <input type="hidden" value="white" id="sel_add_warna_background">
                                    <label class="col1">
                                        <input type="radio" name="color" value="white" onclick="changeclr2(this)">
                                        <div class="button"><span></span></div>
                                    </label>
                                    <label class="col3">
                                        <input type="radio" name="color" value="darkred" onclick="changeclr2(this)">
                                        <div class="button"><span></span></div>
                                    </label>
                                    <label class="col4">
                                        <input type="radio" name="color" value="springgreen" onclick="changeclr2(this)">
                                        <div class="button"><span></span></div>
                                    </label>
                                    <label class="col5">
                                        <input type="radio" name="color" value="orange" onclick="changeclr2(this)">
                                        <div class="button"><span></span></div>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" id="save_ket_obat" class="btn btn-success"><i class="fa fa-check"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        listJenisObat("sel_search_jenis");
        listOfParameter("sel_search_parameter");
        $('#show-hidden-menu').click(function() {
            $('.hidden-menu').toggle();
        });
        $('#show-hidden-menu2').click(function() {
            $('.hidden-menu2').toggle();
        });
        var idJenis = '<s:property value="keteranganObat.idJenis"/>';
        var idSubJenis = '<s:property value="keteranganObat.idSubJenis"/>';
        var idParam = '<s:property value="keteranganObat.idParameterKeterangan"/>';
        var keterangan = '<s:property value="keteranganObat.keterangan"/>';
        if(idJenis != '' && idJenis != '%'){
            $('#sel_search_jenis').val(idJenis).trigger('change');
            setTimeout(function () {
                if(idSubJenis != '' && idParam != '%'){
                    $('#sel_search_jenis_sub').val(idSubJenis);
                }else{
                    $('#sel_search_jenis_sub').val('');
                }
            },100);
        }else{
            $('#sel_search_jenis').val('');
        }
        if(idParam != '' && idParam != '%'){
            $('#sel_search_parameter').val(idParam);
        }else{
            $('#sel_search_parameter').val('');
        }
        if(keterangan != '' && keterangan != '%'){
            $('#in_search_keterangan').val(keterangan);
        }else{
            $('#in_search_keterangan').val('');
        }
    });

    $(document).mouseup(function (e){
        var container = $(".hidden-menu");
        if (!container.is(e.target) && container.has(e.target).length === 0){
            container.fadeOut();
        }
        var container = $(".hidden-menu2");
        if (!container.is(e.target) && container.has(e.target).length === 0){
            container.fadeOut();
        }
    });

    $(".button").click(function(){
        $(".hidden-menu").fadeOut();
        $(".hidden-menu2").fadeOut();
    });

    function changeclr(obj) {
        document.getElementById("show-hidden-menu").style.backgroundColor = obj.value;
        $('#sel_add_warna_label').val(obj.value);

    }
    function changeclr2(obj) {
        document.getElementById("show-hidden-menu2").style.backgroundColor = obj.value;
        $('#sel_add_warna_background').val(obj.value);

    }

    function link(){
        window.location.href="<s:url action='initForm_keteranganobat'/>";
    }

    function showModal(jenis, id){
        listJenisObat("sel_add_jenis");
        listOfParameter("sel_add_parameter");
        $('#sel_add_jenis').val('').attr('disabled', false);
        $("#sel_add_jenis_sub").val('').attr('disabled', false);
        $("#sel_add_parameter").val('').attr('disabled', false);
        $("#in_add_keterangan").val('').attr('disabled', false);
        document.getElementById("show-hidden-menu").style.backgroundColor = 'white';
        document.getElementById("show-hidden-menu2").style.backgroundColor = 'white';
        $("#sel_add_warna_label").val('white');
        $("#sel_add_warna_background").val('white');
        var title = "";
        if(jenis == 'add'){
            title = "Add Keterangan Obat";
            $('#save_ket_obat').attr('onclick','saveObat(\'\')');
        }else{
            setData(id);
            if(jenis == 'edit'){
                title = "Edit Keterangan Obat";
                $('#save_ket_obat').attr('onclick','saveObat(\''+id+'\', \'Y\')');
            }
            if(jenis == 'delete'){
                title = "Delete Keterangan Obat";
                $('#sel_add_jenis').attr('disabled', true);
                $("#sel_add_jenis_sub").attr('disabled', true);
                $("#sel_add_parameter").attr('disabled', true);
                $("#in_add_keterangan").attr('disabled', true);
                $('#save_ket_obat').attr('onclick','saveObat(\''+id+'\', \'N\')');
            }
        }
        $("#modal-add").modal({show:true, backdrop:'static'});

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
            $("#"+elid).html(str);
        });
    }

    function setData(id) {
        KeteranganObatAction.getFromSession(id, function (res) {
            listJenisObatSubByJenis('sel_add_jenis_sub', res.idJenis);
            $("#sel_add_jenis").val(res.idJenis);
            $("#sel_add_jenis_sub").val(res.idSubJenis);
            $("#sel_add_parameter").val(res.idParameterKeterangan);
            $("#in_add_keterangan").val(res.keterangan);
            if(res.warnaLabel != null && res.warnaLabel != '' &&
                res.warnaBackground != null && res.warnaBackground != ''){
                $('#group-warna-add').show();
                $("#sel_add_warna_label").val(res.warnaLabel);
                $("#sel_add_warna_background").val(res.warnaBackground);
                document.getElementById("show-hidden-menu").style.backgroundColor = res.warnaLabel;
                document.getElementById("show-hidden-menu2").style.backgroundColor = res.warnaBackground;
            }else{
                $('#group-warna-add').hide();
                $("#sel_add_warna_label").val('white');
                $("#sel_add_warna_background").val('white');
            }
        });
    }

    function saveObat(id, flag) {
        var idJenisSub      = $("#sel_add_jenis_sub").val();
        var parameter       = $("#sel_add_parameter").val();
        var keterangan      = $("#in_add_keterangan").val();
        var warnaLabel      = $("#sel_add_warna_label").val();
        var warnaBackground = $("#sel_add_warna_background").val();
        var arData          = [];

        if(idJenisSub && parameter && keterangan != ''){
            if(id != ''){
                arData.push({
                    "id":id,
                    "id_sub_jenis":idJenisSub,
                    "id_parameter_keterangan":parameter,
                    "keterangan":keterangan,
                    "warna_label":warnaLabel,
                    "warna_background":warnaBackground,
                    "flag":flag
                });

                var stData = JSON.stringify(arData);
                KeteranganObatAction.saveEdit(stData, function (res) {
                    if (res.status == "success"){
                        alert("Berhasil Save");
                    } else {
                        alert(res.msg);
                    }
                    $('#modal-add').hide();
                    link();
                });
            }else{
                arData.push({
                    "id_sub_jenis":idJenisSub,
                    "id_parameter_keterangan":parameter,
                    "keterangan":keterangan,
                    "warna_label":warnaLabel,
                    "warna_background":warnaBackground
                });
                var stData = JSON.stringify(arData);
                KeteranganObatAction.saveAdd(stData, function (res) {
                    if (res.status == "success"){
                        alert("Berhasil Save");
                    } else {
                        alert(res.msg);
                    }
                    $("#modal-add").modal('hide');
                    link();
                });
            }
        }else{
            alert("silahkan cek data inputan...!");
        }
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

