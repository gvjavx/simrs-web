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
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#permintaan_obat_poli, #obat_poli_active').addClass('active');
            $('#obat_poli_open').addClass('menu-open');
        });

        function reture() {
            $('#tipePermintaan').val('002').trigger('change');
            $('#flag').val('N').trigger('change');
            document.obatPoliForm.action = 'searchPermintaanObatPoli_obatpoli.action';
            document.obatPoliForm.submit();
        }

        function showModal(select) {
            $('#req_nama_obat').val('').trigger('change');
            $('#req_qty').val('');
            $('#req_stok').val('');
            $('#req_stok_apotek').val('');
            $('#req_qty').val('');
            $('#body_request').html('');
            $('#req_nama_obat').attr("onchange", "var warn =$('#war_req_obat').is(':visible'); if (warn){$('#cor_req_obat').show().fadeOut(3000);$('#war_req_obat').hide()}; setStokObatPoli(this)");
            $('#req_nama_pelayanan').attr("onchange", "var warn =$('#war_req_pelayanan').is(':visible'); if (warn){$('#cor_req_pelayanan').show().fadeOut(3000);$('#war_req_pelayanan').hide()}; setObatPoli(this)");
            $('#modal-request-obat').modal('show');
        }


    </script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanObatPoliAction.js"/>'></script>

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
            Permintaan Obat Poli
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Permintaan Obat Poli</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="obatPoliForm" method="post" namespace="/obatpoli"
                                    action="searchPermintaanObatPoli_obatpoli.action" theme="simple"
                                    cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tipe Permintaan</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'003':'Reture'}"
                                                  cssStyle="margin-top: 7px"
                                                  id="tipePermintaan" name="permintaanObatPoli.tipePermintaan"
                                                  headerKey="002" headerValue="Request"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Obat</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_obat" cssStyle="margin-top: 7px"
                                                     name="permintaanObatPoli.idObat" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Obat</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_obat" name="permintaanObatPoli.namaObat"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Poli</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select cssStyle="border-radius: 4px; width: 100%"
                                                  list="#initComboPoli.listOfPelayanan" id="poli"
                                                  name="permintaanObatPoli.idPelayanan" listKey="idPelayanan"
                                                  listValue="namaPelayanan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2" disabled="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non Active'}"
                                                  cssStyle="margin-top: 7px"
                                                  id="flag" name="permintaanObatPoli.flag"
                                                  headerKey="Y" headerValue="Active"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-8" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="obatPoliForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary">Action</button>
                                            <button type="button" class="btn btn-primary dropdown-toggle"
                                                    data-toggle="dropdown" style="height: 34px">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li onclick="showModal()"><a href="#"><i class="fa fa-plus"></i>
                                                    Request Obat</a></li>
                                                <li onclick="reture()"><a href="#"><i class="fa fa-refresh"></i>
                                                    Reture Obat</a></li>
                                            </ul>
                                        </div>
                                            <%--<a class="btn btn-primary" onclick="showModal(1)"><i class="fa fa-plus"></i>--%>
                                            <%--Request Obat</a>--%>
                                        <a type="button" class="btn btn-danger" href="initForm_obatpoli.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none">
                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                               resizable="false"
                                               closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         toContent();
                                                                                     }
                                                                            }"
                                    >
                                        <s:hidden id="close_pos"></s:hidden>
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Record has been saved successfully.
                                    </sj:dialog>

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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Permintaan Obat Poli</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Permintaan</td>
                                <td>Tanggal</td>
                                <td>Tujuan Pelayanan</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idPermintaanObatPoli"/></td>
                                    <td><s:property value="stCreatedDate"/></td>
                                    <td><s:property value="namaTujuanPelayanan"/></td>
                                    <td><s:if test='#row.keterangan == "Menunggu Konfirmasi"'>
                                        <label class="label label-warning"><s:property value="keterangan"/></label>
                                    </s:if><s:else>
                                        <label class="label label-success"><s:property value="keterangan"/></label>
                                    </s:else></td>
                                    <td align="center">
                                        <s:if test='#row.approvalFlag == "Y" && #row.diterimaFlag == null'>
                                            <button class="btn btn-primary" onclick="confirm('<s:property value="idApprovalObat"/>','<s:property value="idPermintaanObatPoli"/>','<s:property value="stCreatedDate"/>','<s:property value="tujuanPelayanan"/>')"><i class="fa fa-edit"></i>
                                            </button>
                                        </s:if>
                                        <s:if test='#row.approvalFlag == "Y" && #row.diterimaFlag == "Y" && #row.retureFlag == null '>
                                            <button class="btn btn-warning" onclick="showReture('<s:property value="idPermintaanObatPoli"/>','<s:property value="stCreatedDate"/>','<s:property value="idPelayanan"/>','<s:property value="tujuanPelayanan"/>')"><i class="fa fa-refresh"></i>
                                            </button>
                                        </s:if>
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


<div class="modal fade" id="modal-request-obat">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Request Obat Poli</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_request">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_request"></p>
                </div>
                <div class="alert alert-warning alert-dismissible" style="display: none" id="warning_bentuk">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_bentuk"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tujuan Pelayanan</label>
                        <div class="col-md-7">
                            <s:action id="initTujuan" namespace="/obatpoli"
                                      name="getTujuanPelayanan_obatpoli"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initTujuan.listOfTujuanPelayanan" id="req_nama_pelayanan"
                                      listKey="idPelayanan + '|' + namaPelayanan"
                                      listValue="namaPelayanan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_req_pelayanan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_req_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="width: 100%" id="req_nama_obat"
                                    onchange="setStokObatPoli(this)">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_req_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_req_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Tujuan</label>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Box</label>
                            <input class="form-control" readonly id="req_stok_box">
                        </div>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Lembar</label>
                            <input class="form-control" readonly id="req_stok_lembar">
                        </div>
                        <div class="col-md-3">
                            <label style="margin-top: 7px">Biji</label>
                            <input class="form-control" readonly id="req_stok_biji">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Sendiri</label>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Box</label>
                            <input class="form-control" readonly id="req_stok_box_sendiri">
                        </div>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Lembar</label>
                            <input class="form-control" readonly id="req_stok_lembar_sendiri">
                        </div>
                        <div class="col-md-3">
                            <label style="margin-top: 7px">Biji</label>
                            <input class="form-control" readonly id="req_stok_biji_sendiri">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>
                        <div class="col-md-7">
                            <%--<s:select list="#{'box':'Box','lembar':'Lembar','biji':'Biji'}"--%>
                                      <%--cssStyle="margin-top: 7px; width: 100%"--%>
                                      <%--onchange="var warn = $('#war_req_jenis_satuan').is(':visible'); if (warn){$('#cor_req_jenis_satuan').show().fadeOut(3000);$('#war_req_jenis_satuan').hide()}"--%>
                                      <%--id="req_jenis_satuan"--%>
                                      <%--headerKey="" headerValue="[Select one]"--%>
                                      <%--cssClass="form-control select2"/>--%>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_req_jenis_satuan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_req_jenis_satuan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Request</label>
                        <div class="col-md-7">
                            <input oninput="var warn =$('#war_req_qty').is(':visible'); if (warn){$('#cor_req_qty').show().fadeOut(3000);$('#war_req_qty').hide()}"
                                   style="margin-top: 7px" class="form-control" type="number" min="1"
                                   id="req_qty">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_req_qty"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_req_qty"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px"></label>
                        <div class="col-md-7">
                            <button class="btn btn-danger pull-right" style="margin-top: 7px" onclick="resetAll()"><i
                                    class="fa fa-refresh"></i> Reset
                            </button>
                            <button class="btn btn-success pull-right" style="margin-top: 7px; margin-right: 4px"
                                    onclick="addObatToList()"><i class="fa fa-plus"></i> Tambah
                            </button>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border">
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_data_exits">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg-req"></p>
                </div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Request Obat, Tujuan <b><span
                        id="req_tujuan"></span></b>
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_request">
                        <thead>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td align="center">Qty</td>
                        <td align="center">Jenis Satuan</td>
                        <td align="center" width="5%">Action</td>
                        </thead>
                        <tbody id="body_request">
                        </tbody>
                    </table>
                    <input type="hidden" id="req_id_pelayanan">
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_request" onclick="saveAddRequest()"><i
                        class="fa fa-arrow-right"></i> Request
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_request"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-request-detail">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span
                        id="judul_req"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_request_detail">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_request_detail"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tanggal Request</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="req_tanggal"
                                   style="margin-top: 7px">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">ID Permintaan</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="req_id_permintaan"
                                   style="margin-top: 7px">
                        </div>
                    </div>
                    <input type="hidden" id="req_id_approve">
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Detail Request Obat
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_request_detail">
                        <thead>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td align="center">Qty Request</td>
                        <td align="center">Qty Approve</td>
                        <td align="center">Jenis Satuan</td>
                        </thead>
                        <tbody id="body_request_detail">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_req_detail" onclick="saveConfirm()"><i
                        class="fa fa-arrow-right"></i> Konfirmasi
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_req_detail"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-reture-detail">
    <div class="modal-dialog modal-flat" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Reture Obat Gudang
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_reture_detail">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_reture_detail"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tanggal Request</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="ret_tanggal"
                                   style="margin-top: 7px">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">ID Permintaan</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="ret_id_permintaan"
                                   style="margin-top: 7px">
                        </div>
                    </div>
                    <input type="hidden" id="ret_tujuan_id">
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Detail Request Obat
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_reture_head">
                        <thead>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td align="center">Qty Box</td>
                        <td align="center">Qty Lembar</td>
                        <td align="center">Qty Biji</td>
                        <td align="center">Qty Request</td>
                        <td align="center">Qty Approve</td>
                        <td align="center">Qty Reture</td>
                        <td align="center">Action</td>
                        </thead>
                        <tbody id="body_reture_head">
                        </tbody>
                    </table>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Detail Reture Obat
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_reture_detail">
                        <thead>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td align="center">Qty</td>
                        <td align="center">Jenis Satuan</td>
                        <td align="center">Action</td>
                        </thead>
                        <tbody id="body_reture_detail">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_ret_detail" onclick="saveAddReture()"><i
                        class="fa fa-arrow-right"></i> Reture
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_ret_detail"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function toContent() {
        window.location.reload(true);
    }

    function setStokObatPoli(select) {

        var idx = select.selectedIndex;
        var idObat = select.options[idx].value;
        var id = "";
        var nama = "";
        var qtyBox = "";
        var qtyLembar = "";
        var qtyBiji = "";
        var lembarPerBox = "";
        var bijiPerLembar = "";

        var stokQtyBox = "";
        var stokQtylembar = "";
        var stokQtyBiji = "";

        if (idObat != '') {

            if (idObat.split('|')[0] != 'null' && idObat.split('|')[0] != '') {
                id = idObat.split('|')[0];
            }
            if (idObat.split('|')[1] != 'null' && idObat.split('|')[1] != '') {
                nama = idObat.split('|')[1];
            }
            if (idObat.split('|')[2] != 'null' && idObat.split('|')[2] != '') {
                qtyBox = idObat.split('|')[2];
            }
            if (idObat.split('|')[3] != 'null' && idObat.split('|')[3] != '') {
                qtyLembar = idObat.split('|')[3];
            }
            if (idObat.split('|')[4] != 'null' && idObat.split('|')[4] != '') {
                qtyBiji = idObat.split('|')[4];
            }
            if (idObat.split('|')[5] != 'null' && idObat.split('|')[5] != '') {
                lembarPerBox = idObat.split('|')[5];
            }
            if (idObat.split('|')[6] != 'null' && idObat.split('|')[6] != '') {
                bijiPerLembar = idObat.split('|')[6];
            }
        }

        if (idObat != '') {
            ObatPoliAction.getStokObatPoli(id, function (response) {
                if (response != null) {
                    console.log(response);
                    $.each(response, function (i, item) {
                        if (item.idObat == id) {
                            if (item.qtyBox != null) {
                                stokQtyBox = item.qtyBox;
                            }
                            if (item.qtyLembar != null) {
                                stokQtylembar = item.qtyLembar;
                            }
                            if (item.qtyBiji != null) {
                                stokQtyBiji = item.qtyBiji;
                            }
                        } else {
                            stok = 0;
                        }
                    });
                }
            });
        }

        $('#req_stok_box').val(qtyBox);
        $('#req_stok_lembar').val(qtyLembar);
        $('#req_stok_biji').val(qtyBiji);

        $('#req_stok_box_sendiri').val(stokQtyBox);
        $('#req_stok_lembar_sendiri').val(stokQtylembar);
        $('#req_stok_biji_sendiri').val(stokQtyBiji);

    }

    //    function setStokPoli(select) {
    //        var idx = select.selectedIndex;
    //        var idObat = select.options[idx].value;
    //        var id = idObat.split('|')[0];
    //        var nama = idObat.split('|')[1];
    //        var qtyBox = idObat.split('|')[2];
    //        var qtyLembar = idObat.split('|')[3];
    //        var qtyBiji = idObat.split('|')[4];
    //        var lembarPerBox = idObat.split('|')[5];
    //        var bijiPerLembar = idObat.split('|')[6];
    //
    //        $('#ret_stok_box').val(stok);
    //    }

    function setObatPoli(select) {
        var idx = select.selectedIndex;
        var poli = select.options[idx].value;
        var idPel = poli.split('|')[0];
        var namePel = poli.split('|')[1];
        var option = "<option value=''>[Select One]</option>";

        if (poli != '') {
            ObatPoliAction.getSelectOptionObatByPoli(idPel, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idObat + "|" + item.namaObat + "|" + item.qtyBox + "|" + item.qtyLembar + "|" + item.qtyBiji + "|" + item.lembarPerBox + "|" + item.bijiPerLembar + "|" + item.idPabrik + "'>" + item.namaObat + "</option>";
                    });
                }
            });
        } else {
            option = "";
        }
        $('#req_nama_obat').html(option);
    }

    function addObatToList() {

        var obat = $('#req_nama_obat').val();
        var data = $('#tabel_request').tableToJSON();
        var tujuan = $('#req_nama_pelayanan').val();
        var qty = $('#req_qty').val();
        var jenisSatuan = $('#req_jenis_satuan').val();
        var id = "";
        var nama = "";
        var qtyBox = 0;
        var qtyLembar = 0;
        var qtyBiji = 0;
        var lembarPerBox = 0;
        var bijiPerLembar = 0;
        var idTujuan = "";
        var namaTujuan = "";
        var berubahBentuk = false;
        var pesan = "";
        var isTransaksi = false;
        var idPabrik = "";

        var cek = false;

        if (obat != '' && qty != '' && jenisSatuan != '') {

            if (obat.split('|')[0] != 'null' && obat.split('|')[0] != '') {
                id = obat.split('|')[0];
            }
            if (obat.split('|')[1] != 'null' && obat.split('|')[1] != '') {
                nama = obat.split('|')[1];
            }
            if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
                qtyBox = obat.split('|')[2];
            }
            if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
                qtyLembar = obat.split('|')[3];
            }
            if (obat.split('|')[4] != 'null' && obat.split('|')[4] != '') {
                qtyBiji = obat.split('|')[4];
            }
            if (obat.split('|')[5] != 'null' && obat.split('|')[5] != '') {
                lembarPerBox = obat.split('|')[5];
            }
            if (obat.split('|')[6] != 'null' && obat.split('|')[6] != '') {
                bijiPerLembar = obat.split('|')[6];
            }
            if (obat.split('|')[7] != 'null' && obat.split('|')[7] != '') {
                idPabrik = obat.split('|')[7];
            }
            if (tujuan.split('|')[0] != 'null' && tujuan.split('|')[0] != '') {
                idTujuan = tujuan.split('|')[0];
            }
            if (tujuan.split('|')[1] != 'null' && tujuan.split('|')[1] != '') {
                namaTujuan = tujuan.split('|')[1];

            }

            var stok = 0;

            if ("box" == jenisSatuan) {
                stok = qtyBox;
            }
            if ("lembar" == jenisSatuan) {
                stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
            }
            if ("biji" == jenisSatuan) {
                stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
            }

            if (parseInt(qty) <= parseInt(stok)) {

                $.each(data, function (i, item) {
                    if (item.ID == id) {
                        cek = true;
                    }
                });

                if (cek) {
                    $('#warning_data_exits').show().fadeOut(5000);
                    $('#msg-req').text("Data obat sudah tersedia..!");
                } else {

                    ObatPoliAction.checkStockLamaByIdPabrikan(idPabrik, {
                        callback: function (response) {
                            if (response != null) {
                                if ("error" == response.status) {
                                    berubahBentuk = true;
                                    pesan = response.message;
                                }
                            }
                        }
                    });

                    if (berubahBentuk) {
                        $('#warning_bentuk').show().fadeOut(5000);
                        $('#msg_bentuk').text(pesan);
                    } else {

                        ObatPoliAction.checkTransaksiObat(id, {
                            callback: function (response) {
                                if (response != null) {
                                    if ("error" == response.status) {
                                        isTransaksi = true;
                                        pesan = response.message;
                                    }
                                }
                            }
                        });

                        if (isTransaksi) {
                            $('#warning_bentuk').show().fadeOut(5000);
                            $('#msg_bentuk').text(pesan);
                        } else {
                            $('#req_nama_pelayanan').attr('disabled', true);
//                    $('#req_jenis_satuan').attr('disabled', true);
                            $('#req_tujuan').html(namaTujuan);
                            $('#req_id_pelayanan').val(idTujuan);

                            var row = '<tr id=' + id + '>' +
                                    '<td>' + id + '</td>' +
                                    '<td>' + nama + '</td>' +
                                    '<td align="center">' + qty + '</td>' +
                                    '<td align="center">' + jenisSatuan + '</td>' +
                                    '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                                    '</tr>';
                            $('#body_request').append(row);
                        }
                    }
                }
            } else {
                $('#warning_request').show().fadeOut(5000);
                $('#msg_request').text('Jumlah Request tidak boleh melebihi stok obat...!');
            }
        } else {
            if (jenisSatuan == '') {
                $('#war_req_jenis_satuan').show();
            }
            if (tujuan == '') {
                $('#war_req_pelayanan').show();
            }
            if (obat == '' || obat == null) {
                $('#war_req_obat').show();
            }
            if (qty == '' || qty <= 0) {
                $('#war_req_qty').show();
            }
            $('#warning_request').show().fadeOut(5000);
            $('#msg_request').text('Silahkan cek kembali data inputan...!');
        }
    }

    function delRowObat(id) {
        $('#' + id).remove();
        $('#btn' + id).show();
        $('#new_qty' + id).attr('disabled', false);
    }

    function saveAddRequest() {

        var data = $('#tabel_request').tableToJSON();
        var idTujuan = $('#req_id_pelayanan').val();
        var stringData = JSON.stringify(data);

        if (stringData != '[]') {

            $('#save_request').hide();
            $('#load_request').show();

            dwr.engine.setAsync(true);
            ObatPoliAction.saveAddRequest(stringData, idTujuan, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-request-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_request').show();
                        $('#load_request').hide();
                        $('body').scrollTop(0);
                    } else {
                        $('#warning_request').show().fadeOut(5000);
                        $('#save_request').show();
                        $('#load_request').hide();
                    }
                }
            });

        } else {
            $('#warning_request').show().fadeOut(5000);
        }
    }

    function resetAll() {
        $('#req_nama_obat').val('').trigger('change');
        $('#req_qty').val('');
        $('#req_stok_box').val('');
        $('#req_stok_lembar').val('');
        $('#req_stok_biji').val('');
        $('#req_stok_box_sendiri').val('');
        $('#req_stok_lembar_sendiri').val('');
        $('#req_stok_biji_sendiri').val('');
        $('#req_nama_pelayanan').val('').trigger('change').attr('disabled', false);
        $('#body_request').html('');
        $('#req_id_pelayanan').val('');
        $('#req_jenis_satuan').val('').trigger('change').attr('disabled', false);
        $('#req_tujuan').html('');
    }

    function confirm(idApp, idPermin, tanggal, tujuan) {
        $('#judul_req').html('Konfirmasi request diterima');
        $('#req_id_permintaan').val(idPermin);
        $('#req_tanggal').val(tanggal);
        $('#req_id_approve').val(idApp);
        $('#modal-request-detail').modal('show');
        var table = "";
        PermintaanObatPoliAction.listDetailPermintaan(idPermin, true, tujuan, "Y", {
            callback: function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        table += "<tr>" +
                                "<td>" + item.idObat + "</td>" +
                                "<td>" + item.namaObat + "</td>" +
                                "<td align='center'>" + item.qty + "</td>" +
                                "<td align='center'>" + item.qtyApprove + "</td>" +
                                "<td align='center'>" + item.jenisSatuan + "</td>" +
                                "</tr>";
                    });
                }
            }
        });
        $('#body_request_detail').html(table);
    }

    function saveConfirm() {
        var data = $('#tabel_request_detail').tableToJSON();
        var stringData = JSON.stringify(data);
        var idApp = $('#req_id_approve').val();
        var idPermin = $('#req_id_permintaan').val();

        if (stringData != '[]') {

            $('#save_req_detail').hide();
            $('#load_req_detail').show();

            dwr.engine.setAsync(true);
            ObatPoliAction.saveKonfirmasiDiterima(idApp, idPermin, stringData, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-request-detail').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_req_detail').show();
                        $('#load_req_detail').hide();
                        $('body').scrollTop(0);
                    } else {
                        $('#warning_reture').show().fadeOut(5000);
                        $('#save_reture').show();
                        $('#load_reture').hide();
                    }
                }
            });

        } else {
            $('#warning_reture').show().fadeOut(5000);
        }
    }

    function showReture(idPermin, tanggal, idPelayanan, tujuan) {
        $('#ret_id_permintaan').val(idPermin);
        $('#ret_tanggal').val(tanggal);
        $('#ret_tujuan_id').val(tujuan);
        $('#modal-reture-detail').modal('show');
        var table = "";
        PermintaanObatPoliAction.listDetailPermintaan(idPermin, true, idPelayanan, "N", {
            callback: function (response) {
                console.log(response);
                if (response != null) {
                    $.each(response, function (i, item) {
                        var qtyBox = "";
                        var qtyLembar = "";
                        var qtyBiji = "";

                        if (item.qtyBox != null) {
                            qtyBox = item.qtyBox;
                        }
                        if (item.qtyLembar != null) {
                            qtyLembar = item.qtyLembar;
                        }
                        if (item.qtyBiji != null) {
                            qtyBiji = item.qtyBiji;
                        }

                        table += "<tr>" +
                                "<td>" + '<span id=obat' + item.idObat + '>' + item.idObat + '</span>' + "</td>" +
                                "<td>" + '<span id=nama_obat' + item.idObat + '>' + item.namaObat + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qtyBox' + item.idObat + '>' + qtyBox + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qtyLembar' + item.idObat + '>' + qtyLembar + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qtyBiji' + item.idObat + '>' + qtyBiji + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qtyReq' + item.idObat + '>' + item.qty + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qty_approve' + item.idObat + '>' + item.qtyApprove + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=jenis_satuan' + item.idObat + '>' + item.jenisSatuan + '</span>' + "</td>" +
                                "<td align='center'>" + '<input type="number" id=new_qty' + item.idObat + ' style="width: 80px" class="form-control">' + "</td>" +
                                "<td align='center'>" + '<a type="button" id=btn' + item.idObat + ' onclick="addToListReture(\'' + item.idObat + '\')" class="btn btn-success"><i class="fa fa-plus"></i></a>' + "</td>" +
                                "</tr>";
                    });
                }
            }
        });
        $('#body_reture_head').html(table);
    }

    function addToListReture(id) {
        var idObat = $('#obat' + id).text();
        var namaObat = $('#nama_obat' + id).text();
        var qty = $('#new_qty' + id).val();
        var qtyBox = $('#qtyBox' + id).text();
        var qtyLembar = $('#qtyLembar' + id).text();
        var qtyReq = $('#qtyReq' + id).text();
        var qtyBiji = $('#qtyBiji' + id).text();
        var qtyApprove = $('#qty_approve' + id).text();
        var jenisSatuan = $('#jenis_satuan' + id).text();

        if (qty != '' && parseInt(qty) > 0) {

            var stok = 0;

            if ("box" == jenisSatuan) {
                stok = qtyBox;
            }
            if ("lembar" == jenisSatuan) {
                stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
            }
            if ("biji" == jenisSatuan) {
                stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
            }


            if (parseInt(qty) <= parseInt(stok) && parseInt(qty) <= parseInt(qtyApprove)) {
                var row = '<tr id=' + id + '>' +
                        '<td>' + idObat + '</td>' +
                        '<td>' + namaObat + '</td>' +
                        '<td align="center">' + qty + '</td>' +
                        '<td align="center">' + jenisSatuan + '</td>' +
                        '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                        '</tr>';
                $('#body_reture_detail').append(row);
                $('#btn' + id).hide();
                $('#new_qty' + id).attr('disabled', true);
            } else {
                $('#warning_reture_detail').show().fadeOut(5000);
                $('#msg_reture_detail').text('Qty tidak boleh melebihi qty stok dan approve...!');
            }
        } else {
            $('#warning_returRequest Obat Polie_detail').show().fadeOut(5000);
            $('#msg_reture_detail').text('Qty reture tidak boleh kosong...!');
        }
    }

    function saveAddReture() {
        var data = $('#tabel_reture_detail').tableToJSON();
        var stringData = JSON.stringify(data);
        var tujuan = $('#ret_tujuan_id').val();

        if (stringData != '[]') {

            $('#save_req_detail').hide();
            $('#load_ret_detail').show();

            dwr.engine.setAsync(true);
            ObatPoliAction.saveAddReture(stringData, tujuan, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-reture-detail').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_req_detail').show();
                        $('#load_ret_detail').hide();
                        $('body').scrollTop(0);
                    } else {
                        $('#warning_reture_detail').show().fadeOut(5000);
                        $('#msg_reture_detail').text('Terjadi kesalahan saat menyimpan data...!');
                        $('#save_req_detail').show();
                        $('#load_ret_detail').hide();
                    }
                }
            });

        } else {
            $('#warning_reture_detail').show().fadeOut(5000);
            $('#msg_reture_detail').text('Silahkan cek kembali data inputan berikut...!');
        }
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>