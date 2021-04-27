<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanGiziAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KeperawatanRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenGiziAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenGiziAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenRawatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CatatanTerintegrasiAction.js"/>'></script>

    <script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenrawatjalan.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/datapasien.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/nyeri.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/resikojatuh.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/gizi.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenrawatinap.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/cppt.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            $('#exampleGizi').dataTable({
                "columnDefs": [
                    {"orderable": false, "targets": 9}
                ]
            });
            $('#permintaan_gizi').addClass('active');
            setSonde('is_sonde', '<s:property value="rawatInap.isSonde"/>');
            <%--setTipe('<s:property value="rawatInap.tipePelayanan"/>');--%>
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
            Permintaan Gizi
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Permintaan Gizi</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="giziForm" method="post" namespace="/ordergizi" action="search_ordergizi.action"
                                    theme="simple" cssClass="form-horizontal">
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Tipe Pelayanan</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:select list="#{'RI':'Rawat Inap'}" onchange="setTipe(this.value)"--%>
                                                  <%--id="tipe_pelayanan" name="rawatInap.tipePelayanan"--%>
                                                  <%--headerKey="RJ" headerValue="Rawat Jalan"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <s:hidden name="rawatInap.tipePelayanan" value="RI"></s:hidden>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="rawatInap.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="rawatInap.namaPasien"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" style="margin-top: 5px">Diet Sonde ?</label>
                                    <div class="col-sm-4">
                                        <div class="form-check" style="margin-top: 10px">
                                            <input type="checkbox" onclick="setSonde('is_sonde', 'C')"
                                                   id="is_sonde"
                                                   value="Y">
                                            <label for="is_sonde"></label>
                                        </div>
                                    </div>
                                </div>
                                <s:hidden name="rawatInap.isSonde" id="val_sonde"></s:hidden>
                                <div class="form-group" id="form_waktu">
                                    <label class="control-label col-sm-4">Waktu</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'siang':'Siang','malam':'Malam'}" cssStyle="margin-top: 7px"
                                                  id="status" name="rawatInap.waktu"
                                                  headerKey="pagi" headerValue="Pagi"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div style="display: block" id="form_ri">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Kelas Ruangan</label>
                                        <div class="col-sm-4">
                                            <s:action id="initComboKelas" namespace="/checkupdetail"
                                                      name="getListComboKelasRuangan_checkupdetail"/>
                                            <s:select cssStyle="margin-top: 7px"
                                                      onchange="$(this).css('border',''); listSelectRuangan(this.value)"
                                                      list="#initComboKelas.listOfKelasRuangan" id="kelas_kamar"
                                                      name="rawatInap.idKelas"
                                                      listKey="idKelasRuangan"
                                                      listValue="namaKelasRuangan"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                        </div>
                                        <div class="col-sm-3" style="display: none;" id="load_ruang">
                                            <img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                 style="cursor: pointer; width: 45px; height: 45px"><b
                                                style="color: #00a157;">Sedang diproses...</b></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Ruangan</label>
                                        <div class="col-sm-4">
                                            <select id="ruangan_ruang" style="margin-top: 7px" class="form-control select2"
                                                    id="nama_ruangan" name="rawatInap.idRuang">
                                                <option value=''>[Select One]</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Order</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="rawatInap.stTglFrom"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="rawatInap.stTglTo" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="giziForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_ordergizi.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
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
                                                                                         closePoz();
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="close_pos"></s:hidden>
                                            <s:hidden id="data_pos"></s:hidden>
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Permintaan Gizi</h3>
                        <a target="_blank" href="printListGizi_ordergizi.action">
                            <button class="btn btn-primary pull-right"><i class="fa fa-print"></i> Print</button>
                        </a>
                    </div>
                    <div class="box-body">
                        <table id="exampleGizi" class="table table-bordered table-striped" style="font-size: 12px">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>No RM</td>
                                <td>Nama</td>
                                <td>Ruangan</td>
                                <td>Jenis Diet</td>
                                <td>Bentuk Diet</td>
                                <td>Alergi</td>
                                <td>Diagnosa</td>
                                <td>Waktu</td>
                                <td width="15%">Status</td>
                                <td align="center" width="9%">
                                    <div class="form-check">
                                        <input type="checkbox" id="select_all" value="all"
                                               onclick="setAll(this.id, 'id_order_gizi'); setSave('id_order_gizi')">
                                        <label for="select_all"></label>
                                    </div>
                                </td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td>
                                        <s:if test='#row.noRuangan != "" && #row.noRuangan != null'>
                                            [<s:property value="noRuangan"/>] <s:property value="namaRangan"/>
                                        </s:if>
                                        <s:else>
                                            <s:property value="namaRangan"/>
                                        </s:else>
                                    </td>
                                    <td><s:property value="jenisDiet"/></td>
                                    <td><s:property value="bentukGizi"/></td>
                                    <td>
                                        <s:property value="alergi"/>
                                        <input type="hidden" id="no_checkup_<s:property value="idOrderGizi"/>" value="<s:property value="noCheckup"/>">
                                    </td>
                                    <td><s:property value="namaDiagnosa"/></td>
                                    <td><s:property value="waktu"/></td>
                                    <td>
                                        <s:if test='#row.diterimaFlag == "R"'>
                                            <span class="span-danger">dibatalkan</span>
                                        </s:if>
                                        <s:else>
                                            <s:if test='#row.approveFlag == "Y"'>
                                                <span class="span-success">telah dikonfirmasi</span>
                                            </s:if>
                                            <s:elseif test='#row.approveFlag == "N"'>
                                                <span class="span-danger">ditolak</span>
                                            </s:elseif>
                                            <s:else>
                                                <span class="span-warning">menunggu konfirmasi</span>
                                            </s:else>
                                        </s:else>
                                    </td>
                                    <td align="center">
                                        <s:if test='#row.diterimaFlag == "R"'>
                                            <img border="0" class="hvr-grow" id="v_<s:property value="noCheckup"/>"
                                                 src="<s:url value="/pages/images/icons8-search-25.png"/>"
                                                 style="cursor: pointer;"
                                                 onclick="viewHistory('<s:property value="idPasien"/>',
                                                         '<s:property value="namaPasien"/>',
                                                         '<s:property value="jenisKelamin"/>',
                                                         '<s:property value="umur"/>',
                                                         '<s:property value="namaRangan"/>',
                                                         '<s:property value="jenisDiet"/>',
                                                         '<s:property value="bentukGizi"/>',
                                                         '<s:property value="alergi"/>',
                                                         '<s:property value="namaDiagnosa"/>',
                                                         '<s:property value="idDetailCheckup"/>',
                                                         '<s:property value="noCheckup"/>'
                                                         )">
                                        </s:if>
                                        <s:else>
                                            <s:if test='#row.approveFlag == "Y"'>
                                                <img border="0" class="hvr-grow" id="v_<s:property value="noCheckup"/>"
                                                     src="<s:url value="/pages/images/icons8-search-25.png"/>"
                                                     style="cursor: pointer;"
                                                     onclick="viewHistory('<s:property value="idPasien"/>',
                                                             '<s:property value="namaPasien"/>',
                                                             '<s:property value="jenisKelamin"/>',
                                                             '<s:property value="umur"/>',
                                                             '<s:property value="namaRangan"/>',
                                                             '<s:property value="jenisDiet"/>',
                                                             '<s:property value="bentukGizi"/>',
                                                             '<s:property value="alergi"/>',
                                                             '<s:property value="namaDiagnosa"/>',
                                                             '<s:property value="idDetailCheckup"/>',
                                                             '<s:property value="noCheckup"/>'
                                                             )">
                                                <img class="hvr-grow" onclick="printBarcodeGizi('<s:property value="noCheckup"/>', '<s:property value="idOrderGizi"/>')" src="<s:url value="/pages/images/icons8-barcode-scanner-25.png"/>">
                                            </s:if>
                                            <s:elseif test='#row.approveFlag == "N"'>
                                                <img border="0" class="hvr-grow" id="v_<s:property value="noCheckup"/>"
                                                     src="<s:url value="/pages/images/icons8-search-25.png"/>"
                                                     style="cursor: pointer;"
                                                     onclick="viewHistory('<s:property value="idPasien"/>',
                                                             '<s:property value="namaPasien"/>',
                                                             '<s:property value="jenisKelamin"/>',
                                                             '<s:property value="umur"/>',
                                                             '<s:property value="namaRangan"/>',
                                                             '<s:property value="jenisDiet"/>',
                                                             '<s:property value="bentukGizi"/>',
                                                             '<s:property value="alergi"/>',
                                                             '<s:property value="namaDiagnosa"/>',
                                                             '<s:property value="idDetailCheckup"/>',
                                                             '<s:property value="noCheckup"/>'
                                                             )">
                                            </s:elseif>
                                            <s:else>
                                                <img border="0" class="hvr-grow" id="v_<s:property value="noCheckup"/>"
                                                     src="<s:url value="/pages/images/icons8-search-25.png"/>"
                                                     style="cursor: pointer;"
                                                     onclick="viewHistory('<s:property value="idPasien"/>',
                                                             '<s:property value="namaPasien"/>',
                                                             '<s:property value="jenisKelamin"/>',
                                                             '<s:property value="umur"/>',
                                                             '<s:property value="namaRangan"/>',
                                                             '<s:property value="jenisDiet"/>',
                                                             '<s:property value="bentukGizi"/>',
                                                             '<s:property value="alergi"/>',
                                                             '<s:property value="namaDiagnosa"/>',
                                                             '<s:property value="idDetailCheckup"/>',
                                                             '<s:property value="noCheckup"/>'
                                                             )">
                                                <div class="form-check">
                                                    <input onclick="setSave('id_order_gizi')" type="checkbox"
                                                           name="id_order_gizi"
                                                           id="id_order_gizi_<s:property value="idOrderGizi"/>"
                                                           value="<s:property value="idOrderGizi"/>">
                                                    <label for="id_order_gizi_<s:property value="idOrderGizi"/>"></label>
                                                </div>
                                            </s:else>
                                        </s:else>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-4 text-center">
                                <a style="display: none" id="btn-not-approve" class="btn btn-danger"
                                   onclick="saveNotApproveGizi()"><i class="fa fa-times"></i> Not Approve</a>
                                <a style="display: none" id="btn-approve" class="btn btn-success"
                                   onclick="saveApproveGizi()"><i class="fa fa-check"></i> Approve</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-detail-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Order Gizi Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_gizi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_gizi"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_gizi">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_gizi2"></p>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_gizi">
                        <thead>
                        <tr>
                            <td>Tanggal Order</td>
                            <td>ID Diet Gizi</td>
                            <td>Bentuk Diet</td>
                            <td>Keterangan</td>
                            <td align="center" rowspan="2">Status</td>
                            <td align="center" rowspan="2">Action</td>
                        </tr>
                        </thead>
                        <tbody id="body_gizi">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_gizi" onclick="saveVerif()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_gizi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-not-approve">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Not Approve Gizi</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_not-approve">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_not-approve"></p>
                </div>
                <div class="box-body">
                    <div class="col-md-12">
                        <textarea rows="4" class="form-control" placeholder="Keterangan di tolak"
                                  id="keterangan_ditolak"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_not_approve" onclick="saveVerif()"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_not_approve"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-history">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> All History Penunjang Medis</span>
                </h4>
            </div>
            <div class="modal-body" style="height: 450px;overflow-y: scroll;">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="30%">NO RM</td>
                                    <td><span id="det_no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td>Nama</td>
                                    <td><span id="det_nama"></span></td>
                                </tr>
                                <tr>
                                    <td>Jenis Kelamin</td>
                                    <td><span id="det_jk"></span></td>
                                </tr>
                                <tr>
                                    <td>Umur</td>
                                    <td><span id="det_umur"></span></td>
                                </tr>
                                <tr>
                                    <td >Ruangan</td>
                                    <td><span id="det_ruangan"></span></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="30%">Jenis Diet</td>
                                    <td><span id="det_jenis_diet"></span></td>
                                </tr>
                                <tr>
                                    <td>Bentuk Diet</td>
                                    <td><span id="det_bentuk_diet"></span></td>
                                </tr>
                                <tr>
                                    <td>Alergi</td>
                                    <td><span id="det_alergi"></span></td>
                                </tr>
                                <tr>
                                    <td>Diagnosa</td>
                                    <td><span id="det_diagnosa"></span></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="btn-group dropdown" style="margin-top: -20px">
                                <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Asesmen
                                </button>
                                <button id="btn_ases" type="button" class="btn btn-primary dropdown-toggle"
                                        data-toggle="dropdown" style="height: 34px">
                                    <span class="caret"></span>
                                    <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu" role="menu" id="asesmen_rj">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <table class="table table-bordered" style="font-size: 12px; margin-top: 20px">
                        <thead>
                        <tr style="font-weight: bold">
                            <td width="30%">Pelayanan</td>
                            <td width="15%">Waktu</td>
                            <td>Keterangan</td>
                        </tr>
                        </thead>
                        <tbody id="body_history">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-lab_luar">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="title_lab_luar"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <img id="img_lab_luar" style="width: 100%">
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

<div id="modal-temp"></div>

<div class="modal fade" id="modal-hasil_lab">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-image"></i> <span
                        id="title_hasil_lab"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div id="carousel-hasil_lab" class="carousel slide">
                                <ol class="carousel-indicators" id="li_hasil_lab">

                                </ol>
                                <div class="carousel-inner" id="item_hasil_lab">

                                </div>
                                <a class="left carousel-control" href="#carousel-hasil_lab" data-slide="prev">
                                    <span class="fa fa-angle-left"></span>
                                </a>
                                <a class="right carousel-control" href="#carousel-hasil_lab" data-slide="next">
                                    <span class="fa fa-angle-right"></span>
                                </a>
                            </div>
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

<div class="modal fade" id="modal-confirm-rm">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Konfirmasi
                </h4>
            </div>
            <div class="modal-body">
                <h4 class="text-center" id="tanya"></h4>
                <h4 class="text-center" id="print_form"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Tidak
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con_rm"><i class="fa fa-check"></i> Ya            </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idDetailCheckup = "";
    var contextPath = '<%= request.getContextPath() %>';
    var isReadRM = false;
    var noCheckup = "";
    var umur = "";
    var namaRuanganPasien = "";
    var tempBerat = "";
    var tempTinggi = "";
    var tempTensi = "";
    var tempSuhu = "";
    var tempNadi = "";
    var tempRr = "";
    var tempBerat = "";
    var tempTinggi = "";
    var tempAnmnesa = "";
    var idPasien = "";
    var tempidRm = "";

    function listOrderGizi(idRawatInap, noCheckup) {
        $('#modal-detail-pasien').modal({show: true, backdrop: 'static'});
        var table = "";
        dwr.engine.setAsync(true);
        PermintaanGiziAction.getListOrderGizi(idRawatInap, {
            callback: function (response) {
                $.each(response, function (i, item) {

                    var tanggal = $.datepicker.formatDate("dd-mm-yy", new Date(item.createdDate));
                    var jenisPagi = "";
                    var bentukPagi = "";
                    var jenisSiang = "";
                    var bentukSiang = "";
                    var jenisMalam = "";
                    var bentukMalam = "";
                    var label = "";
                    var btn = "";
                    var idDietGizi = "";
                    var bentukDiet = "";
                    var keterangan = "";

                    if (item.bentukMakanPagi != null) {
                        bentukPagi = item.bentukMakanPagi;
                    }
                    if (item.dietPagi != null) {
                        jenisPagi = item.dietPagi;
                    }
                    if (item.bentukMakanSiang != null) {
                        bentukSiang = item.bentukMakanSiang;
                    }
                    if (item.dietSiang != null) {
                        jenisSiang = item.dietSiang;
                    }
                    if (item.dietMalam != null) {
                        bentukMalam = item.bentukMakanMalam;
                    }
                    if (item.bentukMakanMalam != null) {
                        jenisMalam = item.dietMalam;
                    }

                    if (item.idDietGizi != null) {
                        idDietGizi = item.idDietGizi;
                    }
                    if (item.bentukDiet != null) {
                        bentukDiet = item.bentukDiet;
                    }
                    if (item.keterangan != null) {
                        keterangan = item.keterangan;
                    }

                    if (item.approveFlag == "Y") {
                        label = '<label class="label label-info"> siap kirim</label>';
                        btn = '<img onclick="printBarcodeGizi(\'' + noCheckup + '\',\'' + item.idOrderGizi + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-barcode-scanner-25.png"/>" style="cursor: pointer;">';
                    } else {
                        label = '<label class="label label-warning"> menunggu</label>';
                        btn = '<img id="bot' + item.idOrderGizi + '" onclick="saveApprove(\'' + item.idOrderGizi + '\',\'' + idRawatInap + '\',\'' + noCheckup + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-edit-25.png"/>" style="cursor: pointer;">';
                    }

                    if (item.diterimaFlag == "Y") {
                        label = '<label class="label label-success"> selesai</label>';
                        btn = '';
                    }
                    table += '<tr>' +
                        '<td>' + tanggal + '</td>' +
                        '<td>' + idDietGizi + '</td>' +
                        '<td>' + bentukDiet + '</td>' +
                        '<td>' + keterangan + '</td>' +
                        '<td style="vertical-align: middle" align="center">' + label + '</td>' +
                        '<td align="center">' + btn + '</td>' +
                        '</tr>'
                });
                $('#body_gizi').html(table);
            }
        });
    }

    function saveApprove(idOrder, idRawatInap, noCheckup) {
        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#bot' + idOrder).attr('src', url).css('width', '30px', 'height', '40px');

        setTimeout(function () {
            PermintaanGiziAction.updateApproveFLag(idOrder, function (response) {
                if (response.status == "success") {
                    $('#bot' + idOrder).removeAttr("src");
                    $('#success_gizi').show().fadeOut(5000);
                    $('#msg_gizi2').text(response.message);
                    listOrderGizi(idRawatInap, noCheckup);
                } else {
                    $('#bot' + idOrder).removeAttr("src");
                    $('#warning_gizi').show().fadeOut(5000);
                    $('#msg_gizi').text(response.message);
                }
            });
        }, 200);
    }

    function printBarcodeGizi(noCheckup, idorderGizi) {
        window.open('printBarcodeGizi_ordergizi.action?id=' + noCheckup + '&order=' + idorderGizi, '_blank');
    }

    function saveVerif() {
        $('#save_gizi').hide();
        $('#load_gizi').show();
        setTimeout(function () {
            $('#save_gizi').show();
            $('#load_gizi').hide();
            $('#modal-detail-pasien').modal('hide');
            $('#info_dialog').dialog('open');
            $('body').scrollTop(0);
        }, 500);
    }

    function listSelectRuangan(id) {
        var option = "";
        if (id != '') {
            CheckupDetailAction.listJustRuangan(id, function (response) {
                option = "<option value=''>[Select One]</option>";
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idRuangan + "'>[" + item.noRuangan + "]-" + item.namaRuangan + "</option>";
                    });
                    $('#ruangan_ruang').html(option);
                } else {
                    $('#ruangan_ruang').html(option);
                }
            });
        }
    }

    function setAll(id, idTujuan) {
        var cek = $('#' + id).is(':checked');
        var idOrder = $('[name=' + idTujuan + ']');
        if (cek) {
            $.each(idOrder, function (i, item) {
                item.checked = true;
            });
        } else {
            $.each(idOrder, function (i, item) {
                item.checked = false;
            });
        }
    }

    function setSave(idTujuan) {
        var idOrder = $('[name=' + idTujuan + ']');
        var cek = false;
        $.each(idOrder, function (i, item) {
            if (item.checked) {
                cek = true;
            }
        });
        if (cek) {
            $('#btn-not-approve').show();
            $('#btn-approve').show();
        } else {
            $('#btn-not-approve').hide();
            $('#btn-approve').hide();
        }
    }

    function saveApproveGizi() {
        var data = [];
        var idOrder = $('[name=id_order_gizi]');
        $.each(idOrder, function (i, item) {
            if (item.checked) {
                var noCheckup = $('#no_checkup_'+item.value).val();
                data.push({
                    'id_order_gizi': item.value,
                    'status': 'Y',
                    'no_checkup': noCheckup
                });
            }
        });
        if(data.length > 0){
            var json = JSON.stringify(data);
            dwr.engine.setAsync(true);
            PermintaanGiziAction.updateGizi(json, {
                callback: function (res) {
                    if(res.status == "success"){
                        $('#info_dialog').dialog('open');
                        $('#data_pos').val(json);
                        $('#close_pos').val(2);
                        $('body').scrollTop(0);
                    }else{
                        $('#error_dialog').dialog('open');
                        $('#errorMessage').text(res.msg);
                        $('body').scrollTop(0);
                    }
                }
            });
        }
    }

    function saveNotApproveGizi() {
        $('#save_not_approve').attr('onclick', 'saveFinalNotApproveGizi()');
        $('#modal-not-approve').modal({show: true, backdrop: 'static'});
    }

    function saveFinalNotApproveGizi() {
        var ket = $('#keterangan_ditolak').val();
        var data = [];
        if (ket != '') {
            var idOrder = $('[name=id_order_gizi]');
            $.each(idOrder, function (i, item) {
                if (item.checked) {
                    data.push({
                        'id_order_gizi': item.value,
                        'keterangan': ket,
                        'status': 'N'
                    });
                }
            });
            if (data.length > 0) {
                var json = JSON.stringify(data);
                $('#load_not_approve').show();
                $('#save_not_approve').hide();
                dwr.engine.setAsync(true);
                PermintaanGiziAction.updateGizi(json, {
                    callback: function (res) {
                        if(res.status == "success"){
                            $('#modal-not-approve').modal('hide');
                            $('#load_not_approve').hide();
                            $('#save_not_approve').show();
                            $('#info_dialog').dialog('open');
                            $('body').scrollTop(0);
                            $('#close_pos').val(1);
                        }else{
                            $('#load_not_approve').hide();
                            $('#save_not_approve').show();
                            $('#warning_not-approve').show().fadeOut(5000);
                            $('#msg_not-approve').text('Silahkan cek kembali inputan anda...!');
                        }
                    }
                });
            }
        } else {
            $('#warning_not-approve').show().fadeOut(5000);
            $('#msg_not-approve').text('Silahkan cek kembali inputan anda...!');
        }
    }

    function closePoz(){
        var pos = $('#close_pos').val();
        var data = $('#data_pos').val();
        if(pos == 1){
            window.location.reload(true);
        }
        if(pos == 2){
            window.location.reload(true);
            var json = JSON.parse(data);
            $.each(json, function (i, item) {
                window.open(contextPathHeader + '/ordergizi/printBarcodeGizi_ordergizi.action?id=' + item.no_checkup + '&order=' + item.id_order_gizi, '_blank');
            });
        }
    }

    function setTipe(val){
        if("RI" == val){
            $('#form_ri').show();
        }else{
            $('#form_ri').hide();
        }
    }

    function viewHistory(idPas, namaPasien, jenisKelamin, um, ruangan, jenisDiet, betukDiet, alergi, diagnosa, iddetail, nocheckup) {
        if(!cekSession()){
            $('#btn_ases').attr('onclick', 'getListRekamMedis(\'gizi\',\'\',\''+iddetail+'\')');
            idDetailCheckup = iddetail;
            umur = um;
            idPasien = idPas;
            noCheckup = nocheckup;
            $('#label_pasien').text(namaPasien);
            $('#modal-history').modal({show: true, backdrop: 'static', keyboard: false});
            var table = "";
            $('#det_no_rm').html(idPas);
            $('#det_nama').html(namaPasien);
            $('#det_jk').html(jenisKelamin);
            $('#det_umur').html(um);
            $('#det_ruangan').html(ruangan);
            $('#det_jenis_diet').html(jenisDiet);
            $('#det_bentuk_diet').html(betukDiet);
            $('#det_alergi').html(alergi);
            $('#det_diagnosa').html(diagnosa);
            PeriksaLabAction.getListHistoryLabRadiologi(idPas, function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        var btn = "";
                        var icon = "";
                        var tele = "";
                        var keteranganTindakan = item.keterangan;
                        if("lab" == item.keterangan){
                            keteranganTindakan = "laboratorium";
                        }

                        var json = "";
                        var btn2 = "";
                        if(item.uploadDalam.length > 0){
                            json = JSON.stringify(item.uploadDalam);
                        }

                        if ("laboratorium" == keteranganTindakan || "radiologi" == keteranganTindakan) {
                            if ("laboratorium" == keteranganTindakan || "radiologi" == keteranganTindakan) {
                                if ("Y" == item.isPeriksaLuar) {
                                    btn = '<img onclick="showHasil(\'' + item.idPeriksaLab + '\', \'' + item.kategoriLabName + '\')" border="0" class="hvr-grow" src="' + contextPathHeader + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                                } else {
                                    btn = '<img class="hvr-grow" id="btn_' + item.idPeriksaLab + '" \n' +
                                        'onclick="detailTindakan(\'' + item.idPeriksaLab + '\',\'' + item.idPeriksaLab + '\',\'' + keteranganTindakan + '\')"\n' +
                                        'src="' + contextPathHeader + '/pages/images/icons8-plus-25.png">';
                                    if(json != ''){
                                        btn2 = '<img onclick="showHasil(\'' + item.idPeriksaLab + '\', \'' + item.kategoriLabName + '\')" border="0" class="hvr-grow" src="' + contextPathHeader + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                                    }
                                }
                            }
                        }

                        var temp = '<b>'+ cekDataNull(item.idDetailCheckup) +
                        '<p>' + cekDataNull(item.namaPelayanan) + '</p></b>';
                        table += '<tr id="row_' + item.idPeriksaLab + '">' +
                            '<td>' + temp +
                            '<textarea style="display: none" id="id_id' + item.idPeriksaLab + '">' + json + '</textarea>' +
                            '</td>' +
                            '<td>' + converterDateTime(item.createdDate) + '</td>' +
                            '<td>' + cekDataNull(item.kategoriLabName) + ' <div class="pull-right">' + btn + btn2 + '</div></td>' +
                            '<tr>';
                    });
                    $('#body_history').html(table);
                }
            });
        }
    }

    function detailTindakan(id, idTindakan, keterangan) {
        if(!cekSession()){
            if (id && idTindakan && keterangan != '') {
                var head = "";
                var body = "";
                CheckupAction.getListDetailHistoryPasien(idTindakan, keterangan, function (res) {
                    if (res.length > 0) {
                        $.each(res, function (i, item) {
                            if (keterangan == "radiologi") {
                                body += '<tr>' +
                                    '<td width="40%">' + cekDataNull(item.namaDetailLab) + '</td>' +
                                    '<td><div style="margin-left: 25px">' + cekDataNull(item.kesimpulan) + '</div></td>' +
                                    '</tr>';
                            }
                            if (keterangan == "laboratorium") {
                                body += '<tr>' +
                                    '<td>' + cekDataNull(item.namaDetailLab) + '</td>' +
                                    '<td>' + cekDataNull(item.kesimpulan) + '</td>' +
                                    '<td>' + cekDataNull(item.acuan) + '</td>' +
                                    '<td>' + cekDataNull(item.satuan) + '</td>' +
                                    '<td>' + cekDataNull(item.keterangan) + '</td>' +
                                    '</tr>';
                            }
                        });
                    }

                    if (keterangan == "radiologi") {
                        head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                            '<td width="40%">Pemeriksaan</td>' +
                            '<td>Hasil</td>' +
                            '</tr>';
                    }
                    if (keterangan == "laboratorium") {
                        head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                            '<td>Pemeriksaan</td>' +
                            '<td>Hasil</td>' +
                            '<td>Nilai Normal</td>' +
                            '<td>Satuan</td>' +
                            '<td>Keterangan</td>' +
                            '</tr>';
                    }

                    var table = '<table style="font-size: 12px" class="table table-bordered">' +
                        '<thead>' + head + '</thead>' +
                        '<tbody>' + body + '</tbody>' +
                        '</table>';

                    var newRow = $('<tr id="del_' + id + '"><td colspan="6">' + table + '</td></tr>');
                    newRow.insertAfter($('table').find('#row_' + id));
                    var url = contextPathHeader + '/pages/images/minus-allnew.png';
                    $('#btn_' + id).attr('src', url);
                    $('#btn_' + id).attr('onclick', 'delDetail(\'' + id + '\',\'' + idTindakan + '\', \'' + keterangan + '\')');
                });
            }
        }
    }

    function delDetail(id, idTindakan, keterangan) {
        $('#del_' + id).remove();
        var url = contextPathHeader + '/pages/images/icons8-plus-25.png';
        $('#btn_' + id).attr('src', url);
        $('#btn_' + id).attr('onclick', 'detailTindakan(\'' + id + '\', \'' + idTindakan + '\', \'' + keterangan + '\')');
    }

    function cekDataNull(item) {
        var data = "";
        if (item != null && item != '') {
            data = item;
        }
        return data;
    }

    function showHasil(id, nama) {
        var data = $('#id_id' +id).val();
        $('#item_hasil_lab').html('');
        $('#li_hasil_lab').html('');
        if (data != null && data != '') {
            var result = JSON.parse(data);
            $('#title_hasil_lab').html(nama);
            if (result.length > 0) {
                var set = '';
                var li = '';
                $.each(result, function (i, item) {
                    var cla = 'class="item"';
                    var claLi = '';
                    if (i == 0) {
                        cla = 'class="item active"';
                        claLi = 'class="active"';
                    }
                    var x = item.urlImg;
                    var tipe = x.split('.').pop();
                    if("pdf" == tipe){
                        set += '<div ' + cla + '>\n' +
                            '<embed src="'+item.urlImg+'" style="width: 100%; height: 70%"/>'+
                            '</div>';
                    }else{
                        set += '<div ' + cla + '>\n' +
                            '<img src="' + item.urlImg + '" style="width: 100%">\n' +
                            '</div>';
                    }
                    li += '<li data-target="#carousel-hasil_lab" data-slide-to="' + i + '" ' + claLi + '></li>';
                });
                $('#item_hasil_lab').html(set);
                $('#li_hasil_lab').html(li);
            }
            $('#modal-hasil_lab').modal({show: true, backdrop: 'static'});
        }
    }

    function loadModalRM(jenis, method, parameter, idRM, flag, flagHide) {
        var context = contextPath + '/pages/modal/modal-default.jsp';
        if (jenis != "") {
            context = contextPath + '/pages/modal/modal-'+jenis+'.jsp';
        }
        $('#modal-temp').load(context, function (res, status, xhr) {
            if(status == "success"){
                var func = new Function(method+'(\''+parameter+'\', \''+idRM+'\', \''+flag+'\', \''+flagHide+'\')');
                func();
            }
        });
    }

    function getListRekamMedis(tipePelayanan, jenis, id) {
        var li = "";
        var jenisRm = "";
        if (jenis == "igd") {
            if (umur >= 0 && umur <= 17) {
                jenisRm = 'ugd_anak';
            } else if (umur >= 18 && umur <= 55) {
                jenisRm = 'ugd_dewasa';
            } else if (umur > 56) {
                jenisRm = 'ugd_geriatri';
            }
        } else {
            jenisRm = jenis;
        }
        CheckupAction.getListRekammedisPasien(tipePelayanan, jenisRm, id, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var cek = "";
                    var tgl = "";
                    var icons = '<i class="fa fa-file-o"></i>';
                    var icons2 = '<i class="fa fa-print"></i>';
                    var tol = "";
                    var tolText = "";
                    var labelTerisi = "";
                    var constan = 0;
                    var terIsi = 0;
                    var labelPrint = "";
                    var terIsiPrint = "";
                    var enter = '';

                    if (item.jumlahKategori != null) {
                        constan = item.jumlahKategori;
                    }
                    if (item.terisi != null && item.terisi != '') {
                        terIsi = item.terisi;
                        terIsiPrint = item.terisi;
                    }

                    if (constan == terIsi || parseInt(terIsi) > parseInt(constan)) {
                        var conver = "";
                        if (item.createdDate != null) {
                            conver = converterDate(new Date(item.createdDate));
                            tgl = '<label class="label label-success">' + conver + '</label>';
                            tol = 'class="box-rm"';
                            tolText = '<span class="box-rmtext">Tanggal mengisi ' + conver + '</span>';
                        }
                        icons = '<i class="fa fa-check" style="color: #449d44"></i>';
                        icons2 = '<i class="fa fa-check" style="color: #449d44"></i>';
                        enter = '<br>';
                    }

                    labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';

                    if (item.keterangan == 'form') {
                        li += '<li ' + tol + '><a style="cursor: pointer" onclick="loadModalRM(\'' + item.jenis + '\', \''+item.function +'\', \''+item.parameter+'\', \''+item.idRekamMedisPasien+'\', \'Y\', \'gizi\')">' + icons + item.namaRm + ' ' + labelTerisi + tolText + '</a></li>'+enter;
                    } else if (item.keterangan == "surat") {
                        li += '<li ' + tol + '><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\',\'' + item.namaRm + '\')' + '">' + icons2 + item.namaRm + ' ' + labelPrint + tolText + '</a></li>'+enter;
                    }
                });
                $('#asesmen_rj').html(li);
            }
        });
    }

    function setSonde(id, isSonde){
        if("C" == isSonde){
            if($('#'+id).is(':checked')){
                $('#status').attr('disabled', true);
                $('#form_waktu').hide();
                $('#val_sonde').val('Y');
            }else{
                $('#status').attr('disabled', false);
                $('#form_waktu').show();
                $('#val_sonde').val('N');
            }
        }else{
            if("Y" == isSonde){
                $('#is_sonde').prop('checked', true);
                $('#status').attr('disabled', true);
                $('#form_waktu').hide();
                $('#val_sonde').val('Y');
            }else{
                $('#is_sonde').prop('checked', false);
                $('#status').attr('disabled', false);
                $('#form_waktu').show();
                $('#val_sonde').val('N');
            }
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>