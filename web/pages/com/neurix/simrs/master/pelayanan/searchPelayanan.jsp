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
        .jarak_atas {
            margin-top: 7px
        }
    </style>

    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/HeaderTindakanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PelayananAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            getBranch();
            $('#tindakan').addClass('active');
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
            Data Pelayanan
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data Pelayanan</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="pelayananForm" method="post" namespace="/pelayanan"
                                    action="search_pelayanan.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Pelayanan</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_tindakan" name="pelayanan.idPelayanan"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Pelayanan</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_tindakan" name="pelayanan.namaPelayanan"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Unit</label>
                                    <div class="col-sm-4">
                                        <select class="form-control select2" id="branch">
                                            <option value="">[Select One]</option>
                                        </select>
                                        <s:hidden id="h_branch_id" name="pelayanan.branchId"></s:hidden>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="pelayanan.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="pelayananForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_pelayanan.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="showModal('add')" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Tambah Pelayanan</a>
                                    </div>
                                </div>
                                <div class="form-group">
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
                                                <img class="spin" border="0"
                                                     style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>
                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                   resizable="false"
                                                   closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="close_pos"></s:hidden>
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
                                        </sj:dialog>
                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close'); }
                                                                            }"
                                        >
                                            <div class="alert alert-danger alert-dismissible">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                         name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pelayanan</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="15%">ID Pelayanan</td>
                                <td>Pelayanan</td>
                                <td width="15%">Tipe pelayanan</td>
                                <td>Divisi</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResultPelayanan" var="row">
                                <tr>
                                    <td><s:property value="idPelayanan"/></td>
                                    <td><s:property value="namaPelayanan"/></td>
                                    <td><s:property value="tipePelayanan"/></td>
                                    <td><s:property value="divisiName"/></td>
                                    <td align="center" width="10%">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="idPelayanan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('edit', '<s:property value="idPelayanan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('delete', '<s:property value="idPelayanan"/>')"
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
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-add">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> <span id="set_judul"></span>
                </h4>
            </div>
            <div class="modal-body" id="temp_scrol">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Unit</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="set_nama_unit" style="width: 100%"
                                    onchange="var warn =$('#war_set_nama_unit').is(':visible'); if (warn){$('#cor_set_nama_unit').show().fadeOut(3000);$('#war_set_nama_unit').hide()};"></select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_unit">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_unit"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Pelayanan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="set_nama_pelayanan" style="width: 100%"
                                    onchange="var warn =$('#war_set_nama_pelayanan').is(':visible'); if (warn){$('#cor_set_nama_pelayanan').show().fadeOut(3000);$('#war_set_nama_pelayanan').hide()}">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_pelayanan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_add"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Data Pelayanan</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="30%"><b>Unit</b></td>
                                    <td><span id="v_unit"></span></td>
                                </tr>
                                <tr>
                                    <td><b>ID Pelayanan</b></td>
                                    <td><span id="v_id_pelayanan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama pelayanan</b></td>
                                    <td><span id="v_nama_pelayanan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tipe Pelayanan</b></td>
                                    <td><span id="v_tipe_pelayanan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Divisi</b></td>
                                    <td><span id="v_divisi"></span></td>
                                </tr>
                            </table>
                        </div>
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

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4 id="pesan"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-check"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function showModal(tipe, id) {
        if ('add' == tipe) {
            $('#set_nama_pelayanan').val('').trigger('change');
            $('#set_nama_unit').attr('disabled', false);
            $('#set_nama_pelayanan').attr('disabled', false);
            $('#save_add').attr('onclick', 'savePelayanan(\''+tipe+'\')');
            $('#set_judul').text("Tambah Pelayanan");
            $('#modal-add').modal({show: true, backdrop: 'static'});
            getHeaderPelayanan();
        }
        if ('detail' == tipe) {
            getDataPelayanan(id);
            $('#modal-view').modal({show: true, static: 'backdrop'});
        }
        if ('edit' == tipe) {
            getHeaderPelayanan();
            $('#set_judul').text("Edit Pelayanan");
            $('#save_add').attr('onclick', 'savePelayanan(\''+tipe+'\')');
            $('#modal-add').modal({show: true, static: 'backdrop'});
            getDataPelayanan(id);
        }
        if ('delete' == tipe) {
            getHeaderPelayanan();
            $('#set_judul').text("Delete Pelayanan");
            $('#save_add').attr('onclick', 'savePelayanan(\''+tipe+'\')');
            $('#modal-add').modal({show: true, static: 'backdrop'});
            getDataPelayanan(id);
        }
    }

    function savePelayanan(tipe) {
        if(!cekSession()){
            var namaUnit = $('#set_nama_unit').val();
            var idHeadPelayanan = $('#set_nama_pelayanan').val();
            var namaPelayanan = $('#set_nama_pelayanan option:selected').text();
            var idPelayanan = $('#v_id_pelayanan').text();
            if (namaUnit && idHeadPelayanan != '') {
                $('#save_add').hide();
                $('#load_add').show();
                var data = {
                    'tipe': tipe,
                    'id_pelayanan': idPelayanan,
                    'branch_id': namaUnit,
                    'id_header_pelayanan': idHeadPelayanan,
                    'nama_pelayanan': namaPelayanan
                };
                var dataString = JSON.stringify(data);
                dwr.engine.setAsync(true);
                PelayananAction.save(dataString, {
                    callback: function (response) {
                        if (response.status == "success") {
                            $('#modal-add').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#save_add').show();
                            $('#load_add').hide();
                            $('body').scrollTop(0);

                        } else {
                            $('#warning_add').show().fadeOut(5000);
                            $('#msg_add').text(response.msg);
                            $('#save_add').show();
                            $('#load_add').hide();
                        }
                    }
                });
            } else {
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");

                if (namaUnit == '') {
                    $('#war_set_nama_unit').show();
                }
                if (namaPelayanan == '' || namaPelayanan == null) {
                    $('#war_set_nama_pelayanan').show();
                }
            }
        }
    }

    function getBranch() {
        var selectOne = '<option value="">[Select One]</option>';
        var option = '';
        var idDef = "";
        var isDis = "";
        TindakanAction.getComboBranch(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    if(i == 0){
                        idDef = item.branchId;
                    }
                    if(item.isDisabled == "Y"){
                        isDis = "Y";
                    }
                    option += '<option value="' + item.branchId + '">' + item.branchName + '</option>';
                });
            }
            console.log(idDef);
            console.log(isDis);
            if(isDis == "Y"){
                $('#set_nama_unit').html(option);
                $('#branch').html(option);
                $('#set_nama_unit').val(idDef).trigger('change');
                $('#branch').val(idDef).trigger('change');
                $('#set_nama_unit, #branch').attr('disabled', true);
                $('#h_branch_id').val(idDef);
            }else{
                $('#set_nama_unit').html(selectOne+option);
                $('#branch').html(selectOne+option);
            }
        });
    }

    function getHeaderPelayanan() {
        var option = '<option value="">[Select One]</option>';
        PelayananAction.getHeaderPelayanan(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idHeaderPelayanan + '">' + item.namaPelayanan + '</option>'
                });
            }
            $('#set_nama_pelayanan').html(option);
        });
    }

    function getDataPelayanan(id) {
        if(!cekSession()){
            PelayananAction.init(id, function (res) {
                if (res.idPelayanan != null) {
                    $('#v_unit').text(res.branchName);
                    $('#v_id_pelayanan').text(res.idPelayanan);
                    $('#v_nama_pelayanan').text(res.namaPelayanan);
                    $('#v_tipe_pelayanan').text(res.tipePelayanan);
                    $('#v_kategori_pelayanan').text(res.kategoriPelayanan);
                    $('#v_divisi').text(res.divisiName);

                    $('#set_nama_unit').val(res.branchId).trigger('change');
                    $('#set_nama_pelayanan').val(res.idHeaderPelayanan).trigger('change');
                }else{
                    $('#v_unit, #v_id_pelayanan, #v_nama_pelayanan').text('');
                    $('#v_tipe_pelayanan, #v_kategori_pelayanan, #v_divisi').text('');
                    $('#set_nama_unit').val('').trigger('change');
                    $('#set_nama_pelayanan').val('').trigger('change');
                }
            });
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>