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

        function showModal(select) {
            if (select == 1) {
                $('#req_nama_obat').val('').trigger('change');
                $('#req_qty').val('');
                $('#req_stok').val('');
                $('#req_stok_apotek').val('');
                $('#req_qty').val('');
                $('#body_request').html('');
                $('#req_nama_obat').attr("onchange", "var warn =$('#war_req_obat').is(':visible'); if (warn){$('#cor_req_obat').show().fadeOut(3000);$('#war_req_obat').hide()}; setStokObatPoli(this)");
                $('#req_nama_pelayanan').attr("onchange", "var warn =$('#war_req_pelayanan').is(':visible'); if (warn){$('#cor_req_pelayanan').show().fadeOut(3000);$('#war_req_pelayanan').hide()}; setObatPoli(this)");
                $('#modal-request-obat').modal('show');
            } else if (select == 2) {
                $('#ret_nama_obat, #ret_nama_pelayanan').val('').trigger('change');
                $('#ret_qty').val('');
                $('#ret_stok').val('');
                $('#ret_qty').val('');
                $('#body_reture').html('');
                $('#modal-reture-obat').modal('show');
            }
        }


    </script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanObatPoliAction.js"/>'></script>

</head>

<body class="hold-transition skin-blue fixed sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Permintaan Obat Poli
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
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="permintaanObatPoli.idObat" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Obat</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="permintaanObatPoli.namaObat"
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
                                                <li onclick="showModal(1)"><a href="#"><i class="fa fa-plus"></i>
                                                    Request Obat</a></li>
                                                <li onclick="showModal(2)"><a href="#"><i class="fa fa-refresh"></i>
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
                                                <img border="0" style="width: 150px; height: 150px"
                                                     src="<s:url value="/pages/images/spinner.gif"/>"
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
                                <td>Tanggal</td>
                                <td>ID Permintaan</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="createdDate"/></td>
                                    <td><s:property value="idPermintaanObatPoli"/></td>
                                    <td><s:if test='#row.keterangan == "Menunggu Konfirmasi"'>
                                        <label class="label label-warning"><s:property value="keterangan"/></label>
                                    </s:if><s:else>
                                        <label class="label label-success"><s:property value="keterangan"/></label>
                                    </s:else></td>
                                    <td align="center">
                                        <s:if test='#row.approvalFlag == "Y" '>
                                            <button class="btn btn-primary" onclick="confirm('<s:property value="idApprovalObat"/>','<s:property value="idPermintaanObatPoli"/>','<s:property value="createdDate"/>','<s:property value="tujuanPelayanan"/>')"><i class="fa fa-edit"></i></button>
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
                            <%--<s:action id="initObat" namespace="/obat"--%>
                            <%--name="getListObat_obat"/>--%>
                            <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                            <%--list="#initObat.listOfObat" id="req_nama_obat"--%>
                            <%--listKey="idObat + '|' + namaObat + '|' + qty"--%>
                            <%--onchange="var warn =$('#war_req_obat').is(':visible'); if (warn){$('#cor_req_obat').show().fadeOut(3000);$('#war_req_obat').hide()}; setStokObatPoli(this)"--%>
                            <%--listValue="namaObat"--%>
                            <%--headerKey="" headerValue="[Select one]"--%>
                            <%--cssClass="form-control select2"/>--%>
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
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat Apotek</label>
                        <div class="col-md-7">
                            <input class="form-control" style="margin-top: 7px" readonly id="req_stok_apotek">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat Poli</label>
                        <div class="col-md-7">
                            <input class="form-control" style="margin-top: 7px" readonly id="req_stok">
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
                            <button class="btn btn-danger pull-right" style="margin-top: 7px" onclick="resetAll(1)"><i
                                    class="fa fa-refresh"></i> Reset
                            </button>
                            <button class="btn btn-success pull-right" style="margin-top: 7px; margin-right: 4px"
                                    onclick="addObatToList(1)"><i class="fa fa-plus"></i> Tambah
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
                        <%--<td>Id_Tujuan</td>--%>
                        <%--<td>Tujuan</td>--%>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td>Qty</td>
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
                <button type="button" class="btn btn-success" id="save_request" onclick="saveAddRequest(1)"><i
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


<div class="modal fade" id="modal-reture-obat">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Reture Obat Poli</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_reture">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_reture"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tujuan Pelayanan</label>
                        <div class="col-md-7">
                            <s:action id="initTujuan" namespace="/obatpoli"
                                      name="getTujuanPelayanan_obatpoli"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initTujuan.listOfTujuanPelayanan" id="ret_nama_pelayanan"
                                      listKey="idPelayanan + '|' + namaPelayanan"
                                      onchange="var warn =$('#war_ret_pelayanan').is(':visible'); if (warn){$('#cor_ret_pelayanan').show().fadeOut(3000);$('#war_ret_pelayanan').hide()}"
                                      listValue="namaPelayanan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ret_pelayanan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ret_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:action id="initObatPoli" namespace="/obatpoli"
                                      name="getListObatPoli_obatpoli"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initObatPoli.listOfObatPoli" id="ret_nama_obat"
                                      listKey="idObat + '|' + namaObat + '|' + qty"
                                      onchange="var warn =$('#war_ret_obat').is(':visible'); if (warn){$('#cor_ret_obat').show().fadeOut(3000);$('#war_ret_obat').hide()}; setStokPoli(this)"
                                      listValue="namaObat"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ret_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ret_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat Poli</label>
                        <div class="col-md-7">
                            <input class="form-control" style="margin-top: 7px" readonly id="ret_stok">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Reture</label>
                        <div class="col-md-7">
                            <input oninput="var warn =$('#war_ret_qty').is(':visible'); if (warn){$('#cor_ret_qty').show().fadeOut(3000);$('#war_ret_qty').hide()}"
                                   style="margin-top: 7px" class="form-control" type="number" min="1"
                                   id="ret_qty">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ret_qty"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ret_qty"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px"></label>
                        <div class="col-md-7">
                            <button class="btn btn-danger pull-right" style="margin-top: 7px" onclick="resetAll(2)"><i
                                    class="fa fa-refresh"></i> Reset
                            </button>
                            <button class="btn btn-success pull-right" style="margin-top: 7px; margin-right: 4px"
                                    onclick="addObatToList(2)"><i class="fa fa-plus"></i> Tambah
                            </button>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border">
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_data_exits_ret">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg-ret"></p>
                </div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Reture Obat, Tujuan <b><span
                        id="ret_tujuan"></span></b>
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_reture">
                        <thead>
                        <%--<td>Id_Tujuan</td>--%>
                        <%--<td>Tujuan</td>--%>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td>Qty</td>
                        <td align="center" width="5%">Action</td>
                        </thead>
                        <tbody id="body_reture">
                        </tbody>
                    </table>
                    <input type="hidden" id="ret_id_pelayanan">
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_reture" onclick="saveAddRequest(2)"><i
                        class="fa fa-arrow-right"></i> Request
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_reture"><i
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="judul_req"></span>
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
                            <input type="text" class="form-control" readonly="true" id="req_tanggal" style="margin-top: 7px">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">ID Permintaan</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="req_id_permintaan" style="margin-top: 7px">
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
                        <td >ID</td>
                        <td >Nama Obat</td>
                        <td align="center">Request</td>
                        <td align="center">Approve</td>
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
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_req_detail"><i
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
        console.log(idObat);
        var stok = 0;
        var id = idObat.split('|')[0];
        var nama = idObat.split('|')[1];
        var stokApotek = idObat.split('|')[2];

        if (idObat != '') {
            ObatPoliAction.getStokObatPoli(id, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        if (item.qty != null) {
                            if (item.idObat == id) {
                                stok = item.qty;
                            }
                        } else {
                            stok = 0;
                        }
                    });
                }
            });
        }

        $('#req_stok').val(stok);
        $('#req_stok_apotek').val(stokApotek);
    }

    function setStokPoli(select) {
        var idx = select.selectedIndex;
        var idObat = select.options[idx].value;
        var id = idObat.split('|')[0];
        var nama = idObat.split('|')[1];
        var stok = idObat.split('|')[2];

        $('#ret_stok').val(stok);
    }

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
                        option += "<option value='" + item.idObat + "|" + item.namaObat + "|" + item.qty + "'>" + item.namaObat + "</option>";
                    });
                }
            });
        } else {
            option = "";
        }
        $('#req_nama_obat').html(option);
    }

    function addObatToList(select) {

        if (select == 1) {
            var obat = $('#req_nama_obat').val();
            var qty = $('#req_qty').val();
            var stok = $('#req_stok_apotek').val();
            var data = $('#tabel_request').tableToJSON();
            var tujuan = $('#req_nama_pelayanan').val();
            var id  = "";
            var nama  = "";
            var idTujuan  = "";
            var namaTujuan  = "";

            var cek = false;

            if (obat != '' && qty != '') {

               id = obat.split('|')[0];
               nama = obat.split('|')[1];
               idTujuan = tujuan.split('|')[0];
               namaTujuan = tujuan.split('|')[1];

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
                        $('#req_nama_pelayanan').attr('disabled', true);
                        $('#req_tujuan').html(namaTujuan);
                        $('#req_id_pelayanan').val(idTujuan);
                        var row = '<tr id=' + id + '>' +
                                '<td>' + id + '</td>' +
                                '<td>' + nama + '</td>' +
                                '<td>' + qty + '</td>' +
                                '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                                '</tr>';
                        $('#body_request').append(row);
                    }
                } else {
                    $('#warning_request').show().fadeOut(5000);
                    $('#msg_request').text('Jumlah Request tidak boleh melebihi stok obat...!');
                }
            } else {
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
        } else if (select == 2) {

            var obat = $('#ret_nama_obat').val();
            var qty = $('#ret_qty').val();
            var stok = $('#ret_stok').val();
            var data = $('#tabel_reture').tableToJSON();
            var tujuan = $('#ret_nama_pelayanan').val();
            var id = "";
            var nama = "";
            var idTujuan  = "";
            var namaTujuan  = "";

            var cek = false;

            if (obat != '' && qty != '') {

                id = obat.split('|')[0];
                nama = obat.split('|')[1];
                idTujuan = tujuan.split('|')[0];
                namaTujuan = tujuan.split('|')[1];

                if (parseInt(qty) <= parseInt(stok)) {

                    $.each(data, function (i, item) {
                        if (item.ID == id) {
                            cek = true;
                        }
                    });

                    if (cek) {
                        $('#warning_data_exits_ret').show().fadeOut(5000);
                        $('#msg-ret').text("Data obat sudah tersedia..!");
                    } else {
                        $('#ret_nama_pelayanan').attr('disabled', true);
                        $('#ret_tujuan').html(namaTujuan);
                        $('#ret_id_pelayanan').val(idTujuan);
                        var row = '<tr id=' + id + '>' +
                                '<td>' + id + '</td>' +
                                '<td>' + nama + '</td>' +
                                '<td>' + qty + '</td>' +
                                '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                                '</tr>';
                        $('#body_reture').append(row);
                    }
                } else {
                    $('#warning_reture').show().fadeOut(5000);
                    $('#msg_reture').text('Jumlah reture tidak boleh melebihi stok...!');
                }
            } else {
                if (tujuan == '') {
                    $('#war_ret_pelayanan').show();
                }
                if (obat == '' || obat == null) {
                    $('#war_ret_obat').show();
                }
                if (qty == '' || qty <= 0) {
                    $('#war_ret_qty').show();
                }
                $('#warning_reture').show().fadeOut(5000);
                $('#msg_reture').text('Silahkan cek kembali data inputan...!');
            }
        }

    }

    function delRowObat(id) {
        $('#' + id).remove();
    }

    function saveAddRequest(select) {

        if (select == 1) {
            var data = $('#tabel_request').tableToJSON();
            var idTujuan = $('#req_id_pelayanan').val();
            var stringData = JSON.stringify(data);

            if (stringData != '[]') {

                $('#save_request').hide();
                $('#load_request').show();

                dwr.engine.setAsync(true);
                ObatPoliAction.saveAddRequest(stringData, idTujuan,{
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            $('#modal-request-obat').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#save_request').show();
                            $('#load_request').hide();
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
        } else if (select == 2) {

            var data = $('#tabel_reture').tableToJSON();
            var idTujuan = $('#ret_id_pelayanan').val();
            var stringData = JSON.stringify(data);

            if (stringData != '[]') {

                $('#save_reture').hide();
                $('#load_reture').show();

                dwr.engine.setAsync(true);
                ObatPoliAction.saveAddReture(stringData, idTujuan,{
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            $('#modal-reture-obat').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#save_reture').show();
                            $('#load_reture').hide();
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

    }

    function resetAll(select){
        if(select == 1){
            $('#req_nama_obat').val('').trigger('change');
            $('#req_qty').val('');
            $('#req_stok_apotek').val('');
            $('#req_stok').val('');
            $('#req_nama_pelayanan').val('').trigger('change').attr('disabled',false);
            $('#body_request').html('');
            $('#req_id_pelayanan').val('');
            $('#req_tujuan').html('');
        }else if (select == 2){
            $('#ret_nama_obat').val('').trigger('change');
            $('#ret_qty').val('');
            $('#ret_stok').val('');
            $('#ret_nama_pelayanan').val('').trigger('change').attr('disabled', false);
            $('#body_reture').html('');
            $('#ret_id_pelayanan').val('');
            $('#ret_tujuan').html('');
        }
    }

    function confirm(idApp, idPermin, tanggal, tujuan){
        $('#judul_req').html('Konfirmasi request diterima');
        $('#req_id_permintaan').val(idPermin);
        $('#req_tanggal').val(tanggal);
        $('#req_id_approve').val(idApp);
        $('#modal-request-detail').modal('show');
        var table = "";
        PermintaanObatPoliAction.listDetailPermintaan(idPermin, true, tujuan, {
            callback: function (response) {
                if(response != null){
                    $.each(response, function (i, item) {
                        table += "<tr>" +
                                "<td>" + item.idObat + "</td>" +
                                "<td>" + item.namaObat + "</td>" +
                                "<td align='center'>" + item.qty + "</td>" +
                                "<td align='center'>" + item.qtyApprove + "</td>" +
                                "</tr>";
                    });
                }
            }
        });
        $('#body_request_detail').html(table);
    }

    function saveConfirm(){
        var data        = $('#tabel_request_detail').tableToJSON();
        var stringData  = JSON.stringify(data);
        var idApp       = $('#req_id_approve').val();
        var idPermin    = $('#req_id_permintaan').val();

        if (stringData != '[]') {

            $('#save_req_detail').hide();
            $('#load_req_detail').show();

            dwr.engine.setAsync(true);
            ObatPoliAction.saveKonfirmasiDiterima(idApp, idPermin, stringData,{
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-request-detail').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_req_detail').show();
                        $('#load_req_detail').hide();
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


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>