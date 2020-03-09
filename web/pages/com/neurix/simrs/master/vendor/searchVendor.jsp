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

    <script type='text/javascript' src='<s:url value="/dwr/interface/VendorAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#vendor_obat').addClass('active');
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
            Data Vendor
            <small>e-HEALTH</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data Vendor</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="vendorForm" method="post" namespace="/vendor" action="search_vendor.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="vendor.namaVendor"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="vendorForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_vendor.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="addVendor()" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Add Vendor</a>
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
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped tablePasien">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Vendor</td>
                                <td>Nama</td>
                                <td>Npwp</td>
                                <td>email</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idVendor"/></td>
                                    <td><s:property value="namaVendor"/></td>
                                    <td><s:property value="npwp"/></td>
                                    <td><s:property value="email"/></td>
                                    <td align="center">
                                        <img class="hvr-grow" onclick="editVendor('<s:property value="idVendor"/>','<s:property value="namaVendor"/>','<s:property value="npwp"/>','<s:property value="email"/>','<s:property value="noTelp"/>','<s:property value="alamat"/>')" style="cursor: pointer" src="<s:url value="/pages/images/icons8-create-25.png"/>">
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
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> <span id="set_judul"></span></h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Vendor</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_nama_vendor"
                                   oninput="var warn =$('#war_set_nama_vendor').is(':visible'); if (warn){$('#cor_set_nama_vendor').show().fadeOut(3000);$('#war_set_nama_vendor').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_vendor">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_vendor"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Npwp</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_npwp" style="margin-top: 7px"
                                   oninput="var warn =$('#war_set_npwp').is(':visible'); if (warn){$('#cor_set_npwp').show().fadeOut(3000);$('#war_set_npwp').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_npwp">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_npwp"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Email</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_email" style="margin-top: 7px"
                                   oninput="var warn =$('#war_set_email').is(':visible'); if (warn){$('#cor_set_email').show().fadeOut(3000);$('#war_set_email').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_email">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_email"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">No Telp</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_telp" style="margin-top: 7px"
                                   oninput="var warn =$('#war_set_telp').is(':visible'); if (warn){$('#cor_set_telp').show().fadeOut(3000);$('#war_set_telp').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_telp">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_telp"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Alamat</label>
                        <div class="col-md-7">
                            <textarea rows="3" class="form-control" id="set_alamat" style="margin-top: 7px"
                                      oninput="var warn =$('#war_set_alamat').is(':visible'); if (warn){$('#cor_set_alamat').show().fadeOut(3000);$('#war_set_alamat').hide()}"></textarea>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_alamat">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_alamat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_add"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-edit">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Edit Vendor</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_edit">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_edit"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Vendor</label>
                        <div class="col-md-7">
                            <input class="form-control" id="edit_nama_vendor" readonly
                                   oninput="var warn =$('#war_edit_nama_vendor').is(':visible'); if (warn){$('#cor_edit_nama_vendor').show().fadeOut(3000);$('#war_edit_nama_vendor').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_nama_vendor">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_nama_vendor"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Npwp</label>
                        <div class="col-md-7">
                            <input class="form-control" id="edit_npwp" readonly style="margin-top: 7px"
                                   oninput="var warn =$('#war_edit_npwp').is(':visible'); if (warn){$('#cor_edit_npwp').show().fadeOut(3000);$('#war_edit_npwp').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_npwp">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_npwp"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Email</label>
                        <div class="col-md-7">
                            <input class="form-control" id="edit_email" style="margin-top: 7px"
                                   oninput="var warn =$('#war_edit_email').is(':visible'); if (warn){$('#cor_edit_email').show().fadeOut(3000);$('#war_edit_email').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_email">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_email"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">No Telp</label>
                        <div class="col-md-7">
                            <input class="form-control" id="edit_telp" style="margin-top: 7px"
                                   oninput="var warn =$('#war_edit_telp').is(':visible'); if (warn){$('#cor_edit_telp').show().fadeOut(3000);$('#war_edit_telp').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_telp">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_telp"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Alamat</label>
                        <div class="col-md-7">
                            <textarea class="form-control" id="edit_alamat" style="margin-top: 7px"
                                      oninput="var warn =$('#war_edit_alamat').is(':visible'); if (warn){$('#cor_edit_alamat').show().fadeOut(3000);$('#war_edit_alamat').hide()}"></textarea>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_alamat">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_alamat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_edit"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_edit"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-detail">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Detail Data Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <img id="img_ktp" style="height: 200px; width: 100%">
                            <table class="table table-striped" style="margin-top: 20px">
                                <tr>
                                    <td><b>ID Pasien</b></td>
                                    <td><span id="an_id_pasien"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="an_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="an_nama"></span></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="an_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, Tgl Lahir</b></td>
                                    <td><span id="an_tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Agama</b></td>
                                    <td><span id="an_agama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Suku</b></td>
                                    <td><span id="an_suku"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="an_alamat"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Desa</b></td>
                                    <td><span id="an_desa"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kecamatan</b></td>
                                    <td><span id="an_kecamatan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kabupaten</b></td>
                                    <td><span id="an_kabupaten"></span></td>
                                </tr>

                                <tr>
                                    <td><b>Provinsi</b></td>
                                    <td><span id="an_provinsi"></span></td>
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

<div class="modal fade" id="modal-success-pasien">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"></h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success alert-dismissible">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    Berhasil melakukan registrasi fingerprint
                </div>
            </div>
            <input id="val_id_pasien" type="hidden">
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" onclick="pasienSuccess()"><i class="fa fa-check"></i> OK
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function addVendor() {
        $('#modal-add').modal({show: true, backdrop: 'static'});
        $('#save_add').attr('onclick','saveVendor("")');
        $('#set_judul').text("Tambah Vendor");
    }

    function saveVendor(idVendor) {

        var data = "";
        var nama = $('#set_nama_vendor').val();
        var npwp = $('#set_npwp').val();
        var email = $('#set_email').val();
        var noTelp = $('#set_telp').val();
        var alamat = $('#set_alamat').val();

        if(nama != '' && npwp != '' && email != '' && noTelp != '' && alamat != ''){

            if(idVendor != ''){
                $('#save_add').hide();
                $('#load_add').show();
                data = {'id_vendor':idVendor,'nama_vendor':nama,'npwp':npwp,'email':email,'no_telp':noTelp,'alamat':alamat};
                var dataString = JSON.stringify(data);
                dwr.engine.setAsync(true);
                VendorAction.saveEditVendor(dataString, {
                    callback: function (response) {
                        if (response.status == "success") {
                            $('#modal-add').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#save_add').show();
                            $('#load_add').hide();

                        } else {
                            $('#warning_add').show().fadeOut(5000);
                            $('#msg_add').text(response.message);
                            $('#save_add').show();
                            $('#load_add').hide();
                        }
                    }
                });
            }else{
                $('#save_add').hide();
                $('#load_add').show();
                data = {'nama_vendor':nama,'npwp':npwp,'email':email,'no_telp':noTelp,'alamat':alamat};
                var dataString = JSON.stringify(data);
                dwr.engine.setAsync(true);
                VendorAction.saveVendor(dataString, {
                    callback: function (response) {
                        if (response.status == "success") {
                            $('#modal-add').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#save_add').show();
                            $('#load_add').hide();

                        } else {
                            $('#warning_add').show().fadeOut(5000);
                            $('#msg_add').text(response.message);
                            $('#save_add').show();
                            $('#load_add').hide();
                        }
                    }
                });
            }
        }else{
            $('#warning_add').show().fadeOut(5000);
            $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");

            if(nama == ''){
                $('#war_set_nama_vendor').show();
            }
            if(npwp == ''){
                $('#war_set_npwp').show();
            }
            if(email == ''){
                $('#war_set_email').show();
            }
            if(noTelp == ''){
                $('#war_set_telp').show();
            }
            if(alamat == ''){
                $('#war_set_alamat').show();
            }
        }
    }

    function editVendor(idVendor, nama, npwp, email, noTelp, alamat) {

        $('#set_nama_vendor').val(nama);
        $('#set_npwp').val(npwp);
        $('#set_email').val(email);
        $('#set_telp').val(noTelp);
        $('#set_alamat').val(alamat);
        $('#modal-add').modal({show:true, backdrop:'static'});
        $('#save_add').attr('onclick','saveVendor(\''+idVendor+'\')');
        $('#set_judul').text("Edit Vendor");
    }



</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>