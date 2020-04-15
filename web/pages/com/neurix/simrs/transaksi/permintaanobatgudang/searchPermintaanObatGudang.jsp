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
            $('#permintaan_obat_gudang, #obat_poli_active').addClass('active');
            $('#obat_poli_open').addClass('menu-open');
        });

        function reture() {
            $('#tipePermintaan').val('002').trigger('change');
            $('#flag').val('N').trigger('change');
            document.obatGudangForm.action = 'searchPermintaanObatGudang_obatgudang.action';
            document.obatGudangForm.submit();
        }


    </script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanObatPoliAction.js"/>'></script>

</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%--<div class="pulse"></div>--%>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Permintaan Obat Gudang
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Permintaan Obat Gudang</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="obatGudangForm" method="post" namespace="/obatgudang"
                                    action="searchPermintaanObatGudang_obatgudang.action" theme="simple"
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
                                    <label class="control-label col-sm-4">Poli</label>
                                    <div class="col-sm-4">
                                        <s:action id="initApotek" namespace="/checkup"
                                                  name="getComboApotek_checkup"/>
                                        <s:select cssStyle="border-radius: 4px; width: 100%"
                                                  list="#initApotek.listOfApotek" id="poli"
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
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="obatGudangForm"
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
                                                <li onclick="reture()"><a href="#"><i class="fa fa-refresh"></i> Reture
                                                    Obat</a></li>
                                            </ul>
                                        </div>
                                        <a type="button" class="btn btn-danger" href="initForm_obatgudang.action">
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Permintaan Obat Gudang</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Permintaan</td>
                                <td>Tanggal Request</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idPermintaanObatPoli"/></td>
                                    <td><s:property value="stCreatedDate"/></td>
                                    <td><s:if test='#row.keterangan == "Menunggu Konfirmasi"'>
                                        <label class="label label-warning"><s:property value="keterangan"/></label>
                                    </s:if><s:else>
                                        <label class="label label-success"><s:property value="keterangan"/></label>
                                    </s:else></td>
                                    <td align="center">
                                        <s:if test='#row.approvalFlag == "Y" && #row.diterimaFlag == null'>
                                            <img onclick="confirm('<s:property value="idApprovalObat"/>',
                                                    '<s:property value="idPermintaanObatPoli"/>',
                                                    '<s:property value="stCreatedDate"/>',
                                                    '<s:property value="tujuanPelayanan"/>')"
                                                  class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                        </s:if>
                                        <s:if test='#row.approvalFlag == "Y" && #row.diterimaFlag == "Y"'>
                                            <s:if test='#row.retureFlag == "Y"'>
                                                <label class="label label-warning">Telah Diretur</label>
                                            </s:if>
                                            <s:else>
                                                <img onclick="showReture('<s:property value="idPermintaanObatPoli"/>',
                                                        '<s:property value="stCreatedDate"/>',
                                                        '<s:property value="idPelayanan"/>',
                                                        '<s:property value="tujuanPelayanan"/>')"
                                                      class="hvr-grow" src="<s:url value="/pages/images/icons8-return-25.png"/>" style="cursor: pointer;">
                                            </s:else>
                                        </s:if>
                                        <s:if test='#row.request == false'>
                                            <s:url var="print_permintaan" namespace="/obatgudang" action="printReturePermintaanObat_obatgudang" escapeAmp="false">
                                                <s:param name="idPermintaan"><s:property value="idPermintaanObatPoli"/></s:param>
                                            </s:url>
                                            <s:a target="_blank" href="%{print_permintaan}">
                                            <img class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;">
                                            </s:a>
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
    <div class="modal-dialog modal-flat" style="width: 60%">
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
                        <label class="col-md-3" style="margin-top: 7px">Gudang Obat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="req_gudang_obat" style="width: 100%"
                            onchange="$('#war_gudang_obat').is(':visible'); if (warn){$('#cor_gudang_obat').show().fadeOut(3000);$('#war_gudang_obat').hide()}">
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_gudang_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_gudang_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:action id="initObat" namespace="/obat"
                                      name="getListObat_obat"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initObat.listOfObat" id="req_nama_obat"
                                      listKey="idObat + '|' + namaObat + '|' + qtyBox + '|' + qtyLembar + '|' + qtyBiji + '|' + lembarPerBox + '|' + bijiPerLembar + '|' + idPabrik"
                                      onchange="var warn =$('#war_req_obat').is(':visible'); if (warn){$('#cor_req_obat').show().fadeOut(3000);$('#war_req_obat').hide()}; setStokObatPoli(this)"
                                      listValue="namaObat"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
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
                            <s:select list="#{'box':'Box','lembar':'Lembar','biji':'Biji'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn = $('#war_req_jenis_satuan').is(':visible'); if (warn){$('#cor_req_jenis_satuan').show().fadeOut(3000);$('#war_req_jenis_satuan').hide()}"
                                      id="req_jenis_satuan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
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

                            <button class="btn btn-success pull-right" style="margin-top: 7px; margin-left: 4px"
                                    onclick="addObatToList()"><i class="fa fa-plus"></i> Tambah
                            </button>
                            <button class="btn btn-danger pull-right" style="margin-top: 7px" onclick="resetAll()"><i
                                    class="fa fa-refresh"></i> Reset
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
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Request Obat <b><span
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
                <button type="button" class="btn btn-success" id="save_request" onclick="confirmSaveAddRequest()"><i
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
    <div class="modal-dialog modal-lg">
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
                        <label class="col-md-2" style="margin-top: 10px">ID Permintaan</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" readonly="true" id="req_id_permintaan"
                                   style="margin-top: 7px">
                        </div>
                        <div class="col-md-6"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2" style="margin-top: 10px">Tanggal Request</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" readonly="true" id="req_tanggal"
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
                        <td>ID Barang</td>
                        <td>Nama Obat</td>
                        <td align="center">Expired Date</td>
                        <td align="center">Qty Approve</td>
                        <td align="center" width="25%">Scan ID Barang</td>
                        <td >Jenis Satuan</td>

                        </thead>
                        <tbody id="body_request_detail">
                        </tbody>
                    </table>
                    <p id="loading_data" style="color: #00a65a; display: none"><img
                            src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang
                        mengambil data...</p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_req_detail" onclick="saveConfirmDiterima()"><i
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
    <div class="modal-dialog modal-lg">
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
                        <label class="col-md-2" style="margin-top: 7px">ID Permintaan</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" readonly="true" id="ret_id_permintaan"
                                   style="margin-top: 7px">
                        </div>
                        <label class="col-md-2" style="margin-top: 7px">Tanggal Request</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" readonly="true" id="ret_tanggal"
                                   style="margin-top: 7px">
                        </div>
                    </div>
                    <input type="hidden" id="ret_id_approve">
                    <input type="hidden" id="ret_tujuan_pelayanan">
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Detail Request Obat
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_reture_head">
                        <thead>
                        <td>ID Barang</td>
                        <td>Nama Obat</td>
                        <td align="center">Qty Approve</td>
                        <td width="25%" align="center">Scan ID Barang</td>
                        <td width="12%" align="center">Qty Reture</td>
                        <td>Jenis Satuan</td>

                        </thead>
                        <tbody id="body_reture_head">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_ret_detail" onclick="confirmSaveAddReture()"><i
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function toContent() {
        var pos = $('#close_pos').val();
        if(pos == 1){
            $('#tipePermintaan').val('003').trigger('change');
            $('#flag').val('Y').trigger('change');
            document.obatGudangForm.action = 'searchPermintaanObatGudang_obatgudang.action';
            document.obatGudangForm.submit();
        }else if(pos == 2){
            $('#tipePermintaan').val('002').trigger('change');
            $('#flag').val('Y').trigger('change');
            document.obatGudangForm.action = 'searchPermintaanObatGudang_obatgudang.action';
            document.obatGudangForm.submit();
        }else{
            window.location.reload(true);
        }
    }

    function showModal() {
        getListGudangObat();
        $('#req_nama_obat').val('').trigger('change');
        $('#req_qty').val('');
        $('#req_stok').val('');
        $('#req_stok_apotek').val('');
        $('#req_qty').val('');
        $('#body_request').html('');
        $('#modal-request-obat').modal({show:true, backdrop:'static'});
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

    function addObatToList() {

        var obat = $('#req_nama_obat').val();
        var data = $('#tabel_request').tableToJSON();
        var qty = $('#req_qty').val();
        var jenisSatuan = $('#req_jenis_satuan').val();
        var id = "";
        var nama = "";
        var qtyBox = 0;
        var qtyLembar = 0;
        var qtyBiji = 0;
        var lembarPerBox = 0;
        var bijiPerLembar = 0;
        var idPabrik = "";
        var berubahBentuk = false;
        var isTransaksi = false;
        var pesan = "";

        var cek = false;

        if (obat != '' && qty != '' && jenisSatuan != '') {

            if (obat.split('|')[7] != 'null' && obat.split('|')[7] != '') {
                idPabrik = obat.split('|')[7];
            }
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
                            var row = '<tr id=' + id + '>' +
                                    '<td>' + id + '</td>' +
                                    '<td>' + nama + '</td>' +
                                    '<td align="center">' + qty + '</td>' +
                                    '<td align="center">' + jenisSatuan + '</td>' +
                                    '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-trash-can-25.png"/>" style="cursor: pointer;"></td>' +
                                    '</tr>';
                            $('#body_request').append(row);
                        }
                    }
                }
            } else {
                $('#warning_request').show().fadeOut(5000);
                $('#msg_request').text('Jumlah Request tidak boleh melebihi stok obat...!');
            }
        }
        else {
            if (jenisSatuan == '' || jenisSatuan == null) {
                $('#war_req_jenis_satuan').show();
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

    function confirmSaveAddRequest() {

        var data = $('#tabel_request').tableToJSON();
        var gudang = $('#req_gudang_obat').val();
        var stringData = JSON.stringify(data);

        if (stringData != '[]' && gudang != '') {
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick','saveAddRequest()');
        } else {
            if(gudang == ''){
                $('#war_gudang_obat').show();
            }
            $('#warning_request').show().fadeOut(5000);
            $('#msg_request').text('Silahkan buat daftar list obat terlebih dahulu..!');
        }
    }

    function saveAddRequest(){

        var data = $('#tabel_request').tableToJSON();
        var gudang = $('#req_gudang_obat').val();
        var stringData = JSON.stringify(data);

        $('#modal-confirm-dialog').modal('hide');
        $('#save_request').hide();
        $('#load_request').show();
        dwr.engine.setAsync(true);
        ObatPoliAction.saveAddRequest(stringData, gudang, {
            callback: function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    $('#modal-request-obat').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#save_request').show();
                    $('#load_request').hide();
                    $('#close_pos').val(2);
                } else {
                    $('#warning_request').show().fadeOut(5000);
                    $('#msg_request').text('Terjadi kesalahan saat menyimpan data ke database...!');
                    $('#save_request').show();
                    $('#load_request').hide();
                }
            }
        });
    }

    function confirm(idApp, idPermin, tanggal, tujuan) {
        $('#save_req_detail').show();
        $('#load_req_detail').hide();
        $('#judul_req').html('Konfirmasi request diterima');
        $('#req_id_permintaan').val(idPermin);
        $('#req_tanggal').val(tanggal);
        $('#req_id_approve').val(idApp);
        $('#modal-request-detail').modal({show:true, backdrop:'static'});
        var table = "";
        PermintaanObatPoliAction.listDetailObatRequest(idPermin, {
            callback: function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        var expired = $.datepicker.formatDate('dd-mm-yy', new Date(item.expiredDate));

                        var idBar = item.idBarang;
                        var str = idBar.substring(8, 15);
                        var idBarang = idBar.replace(str, '*******');

                        table += "<tr>" +
                                "<td>" + idBarang +
                                '<input type="hidden" id=id_barang' + i + ' value=' + item.idBarang + '>'+
                                '<input type="hidden" id=id_obat' + i + ' value=' + item.idObat + '>'+
                                '<input type="hidden" id=id_transaksi' + i + ' value=' + item.idTransaksiObatDetail + '>' + "</td>" +
                                "<td>" + item.namaObat + "</td>" +
                                "<td align='center'>" + expired + "</td>" +
                                "<td align='center'>" + item.qtyApprove + "</td>" +
                                "<td align='center'>" +
                                '<div class="input-group">'+
                                '<input class="form-control" onchange="cekIdBarang(\''+i+'\',this.value,\''+item.idBatch+'\')" id=cek_id_barang'+i+'>' +
                                '<div class="input-group-addon">'+
                                '<span id=loading'+i+'></span> '+
                                '</div>'+
                                '</div>'+
                                "</td>" +
                                "<td>" + item.jenisSatuan + "</td>" +
                                "</tr>";
                    });
                }
            }
        });
        $('#body_request_detail').html(table);
    }

    function saveConfirmDiterima() {
        var data = $('#tabel_request_detail').tableToJSON();
        var idApp = $('#req_id_approve').val();
        var idPermin = $('#req_id_permintaan').val();
        var result = [];
        var cek = false;

        $.each(data, function (i, item) {
            var idBarang = $('#id_barang'+i).val();
            var idObat = $('#id_obat'+i).val();
            var idTransaksi = $('#id_transaksi'+i).val();
            var jenisSatuan = data[i]["Jenis Satuan"];
            var qtyApp = data[i]["Qty Approve"];
            var scanId = $('#cek_id_barang'+i).val();
            if(scanId == ""){
                cek = true;
            }
            result.push({'ID Barang':idBarang, 'ID Obat':idObat, 'ID Transkasi':idTransaksi, 'Jenis Satuan':jenisSatuan, 'Qty Approve':qtyApp});
        });

        var stringData = JSON.stringify(result);
        if(cek){
            $('#warning_request_detail').show().fadeOut(5000);
            $('#msg_request_detail').text("Silahkan lakukan konfirmasi untuk masing masing id barang..!");
        }else{
            if (stringData != '[]') {
                $('#save_con').attr('onclick','saveApproveDiterima(\''+idApp+'\',\''+idPermin+'\',\''+stringData+'\')');
                $('#modal-confirm-dialog').modal('show');
            } else {
                $('#warning_request_detail').show().fadeOut(5000);
                $('#msg_request_detail').text("Silahkan cek kembali data inputan berikut..!");
            }
        }
    }

    function saveApproveDiterima(idApp, idPermin, stringData){

        $('#modal-confirm-dialog').modal('hide');
        $('#save_req_detail').hide();
        $('#load_req_detail').show();

        dwr.engine.setAsync(true);
        ObatPoliAction.saveKonfirmasiDiterima(idApp, idPermin, stringData, {
            callback: function (response) {
                if (response["status"] == "success") {
                    $('#modal-request-detail').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#save_req_detail').show();
                    $('#load_req_detail').hide();
                } else {
                    $('#save_req_detail').show();
                    $('#load_req_detail').hide();
                    $('#warning_request_detail').show().fadeOut(5000);
                    $('#msg_request_detail').text(response["message"]);
                }
            }
        });
    }

    function cekIdBarang(id, valueIdBarang, idBatch){
        var idBarang = $('#id_barang'+id).val();
        var flag = "";
        var load = "";
        if(valueIdBarang != ''){
            $('#loading'+id).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            setTimeout(function () {

                if(idBarang == valueIdBarang){
                    flag = "Y";
                    load = '<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">';
                }else{
                    flag = "N";
                    load = '<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">';
                }

                ObatPoliAction.updateDiterimaFlagBatch(idBatch, flag, {callback: function (response) {
                    if(response == "success"){
                        $('#loading'+id).html(load);
                    }else{
                        $('#loading'+id).html('<img src="<s:url value="/pages/images/icon_warning.ico"/>" style="height: 20px; width: 20px;">');
                    }
                }});

            },100);
        }else{
            $('#loading' + id).html('');
        }
    }

    function cekIdBarangReture(id, valueIdBarang, qtyApp) {
        var idBarang = $('#id_barang' + id).val();
        if (valueIdBarang != '') {
            $('#loading' + id).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            setTimeout(function () {
                if (idBarang == valueIdBarang) {
                    $('#loading' + id).html('<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">');
                    $('#new_qty' + id).show().val(qtyApp).focus();
                } else {
                    $('#loading' + id).html('<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">');
                    $('#new_qty' + id).hide();
                    $('#new_qty' + id).val('');
                }
            }, 500);
        } else {
            $('#loading' + id).html('');
            $('#new_qty' + id).val('');
            $('#new_qty' + id).hide();
        }
    }

    function printRequest(idApp, idPermin) {
        PermintaanObatPoliAction.printPermintaanObat(idApp, idPermin, {
            callback: function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    $('#modal-reture').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#save_ret').show();
                    $('#load_ret').hide();
                } else {
                    $('#warning_request').show().fadeOut(5000);
                    $('#msg_reture').text(response);
                    $('#save_ret').show();
                    $('#load_ret').hide();
                }
            }
        });
    }

    function showReture(idPermin, tanggal, idPelayanan, tujuanPelayanan) {
        $('#ret_id_permintaan').val(idPermin);
        $('#ret_tanggal').val(tanggal);
        $('#ret_tujuan_pelayanan').val(tujuanPelayanan);
        $('#modal-reture-detail').modal({show:true, backdrop : 'static'});
        $('#save_req_detail').show();
        $('#load_ret_detail').hide();
        var table = "";
        ObatPoliAction.listDetailOldPermintaan(idPermin, {
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

                        var idBar = item.idBarang;
                        var str = idBar.substring(8, 15);
                        var idBarang = idBar.replace(str, '*******');

                        table += "<tr>" +
                                "<td>" + idBarang +
                                '<input type="hidden" id=id_barang'+ i +' value='+item.idBarang+'>'+
                                '<input type="hidden" id=id_obat'+ i +' value='+item.idObat+'>'+ "</td>" +
                                "<td>" + '<span id=nama_obat' + i + '>' + item.namaObat + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qty_approve' + i + '>' + item.qtyApprove + '</span>' + "</td>" +
                                '<td>' +
                                '<div class="input-group">' +
                                '<input class="form-control" onchange="cekIdBarangReture(\'' + i + '\',this.value,\''+item.qtyApprove+'\')">' +
                                '<div class="input-group-addon">' +
                                '<span id=loading' + i + '></span> ' +
                                '</div>' +
                                '</div>' +
                                '</td>' +
                                '<td>' + '<input onchange="validasiInput(this.value, \''+ item.qtyApprove +'\')" type="number" style="display: none" class="form-control" id=new_qty' + i + '>' + '</td>' +
                                "<td>" + '<span id=jenis_satuan' + i + '>' + item.jenisSatuan + '</span>' + "</td>" +
                                "</tr>";
                    });
                }
            }
        });
        $('#body_reture_head').html(table);
    }

    function validasiInput(value, qtyApp){

        if(value!= ''){
            if(parseInt(value) <= parseInt(qtyApp)){

            }else{
                $('#warning_reture_detail').show().fadeOut(5000);
                $('#msg_reture_detail').text('Qty Reture tidak boleh melebihi qty approve...!');
            }
        }
    }

    function addToListReture(id, lembarPerBox, bijiPerLembar) {
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
                        '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-trash-can-25.png"/>" style="cursor: pointer;"></td>' +
                        '</tr>';
                $('#body_reture_detail').append(row);
                $('#btn' + id).hide();
                $('#new_qty' + id).attr('disabled', true);
            } else {
                $('#warning_reture_detail').show().fadeOut(5000);
                $('#msg_reture_detail').text('Qty tidak boleh melebihi qty stok dan approve...!');
            }
        } else {
            $('#warning_reture_detail').show().fadeOut(5000);
            $('#msg_reture_detail').text('Qty reture tidak boleh kosong...!');
        }
    }

    function confirmSaveAddReture() {

        var data = $('#tabel_reture_head').tableToJSON();
        var idPermintaan = $('#ret_id_permintaan').val();
        var result = [];
        var qty = 0;
        var qtyApp = 0;
        var cek = false;

        $.each(data, function (i, item) {
            var idBarang = $('#id_barang'+i).val();
            var idObat = $('#id_obat'+i).val();
            var qtyReture = $('#new_qty'+i).val();
            var qtyApprove = $('#qty_approve'+i).text();

            if(qtyReture == ""){
                qtyReture = 0;
            }

            if(qtyApprove == ""){
                qtyApprove = 0;
            }

            if(parseInt(qtyReture) > parseInt(qtyApprove)){
                cek = true;
            }

            qtyApp = parseInt(qtyApp) + parseInt(qtyApprove);
            qty = parseInt(qty) + parseInt(qtyReture);

            var jenisSatuan = data[i]["Jenis Satuan"];
            result.push({'ID Barang': idBarang, 'ID Obat':idObat, 'Qty Reture':qtyReture, 'Jenis Satuan':jenisSatuan});
        });

        var stringData = JSON.stringify(result);

        if(qty > 0){

            if(cek){
                $('#warning_reture_detail').show().fadeOut(5000);
                $('#msg_reture_detail').text('Qty reture tidak boleh lebih dari qty approve...!');
            }else {
                if (stringData != '[]') {
                    $('#save_con').attr('onclick','saveAddReture()');
                    $('#modal-confirm-dialog').modal('show');
                } else {
                    $('#warning_reture_detail').show().fadeOut(5000);
                    $('#msg_reture_detail').text('Silahkan cek kembali data inputan berikut...!');
                }
            }
        }else{
            $('#warning_reture_detail').show().fadeOut(5000);
            $('#msg_reture_detail').text('Silahkan lakukan konfirmasi untuk masing masing id barang...!');
        }
    }

    function saveAddReture(){
        $('#modal-confirm-dialog').modal('hide');
        var data = $('#tabel_reture_head').tableToJSON();
        var idPermintaan = $('#ret_id_permintaan').val();
        var tujuanPelayanan = $('#ret_tujuan_pelayanan').val();
        var result = [];
        $.each(data, function (i, item) {
            var idBarang = $('#id_barang'+i).val();
            var idObat = $('#id_obat'+i).val();
            var qtyReture = $('#new_qty'+i).val();
            var jenisSatuan = data[i]["Jenis Satuan"];
            result.push({'ID Barang': idBarang, 'ID Obat':idObat, 'Qty Reture':qtyReture, 'Jenis Satuan':jenisSatuan});
        });

        var stringData = JSON.stringify(result);
        if (stringData != '[]') {

            $('#save_ret_detail').hide();
            $('#load_ret_detail').show();

            dwr.engine.setAsync(true);
            ObatPoliAction.saveAddReture(stringData, tujuanPelayanan, idPermintaan, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-reture-detail').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_ret_detail').show();
                        $('#load_ret_detail').hide();
                        $('#close_pos').val(1);
                    } else {
                        $('#warning_reture_detail').show().fadeOut(5000);
                        $('#msg_reture_detail').text(response.msg);
                        $('#save_ret_detail').show();
                        $('#load_ret_detail').hide();
                    }
                }
            });

        } else {
            $('#warning_reture_detail').show().fadeOut(5000);
            $('#msg_reture_detail').text('Silahkan cek kembali data inputan berikut...!');
        }
    }

    function getListGudangObat(){
        var option = "";
        CheckupAction.getListComboGudang(function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.idPelayanan+'">'+item.namaPelayanan+'</option>';
                });
                $('#req_gudang_obat').html(option);
            }else{
                $('#req_gudang_obat').html('');
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>