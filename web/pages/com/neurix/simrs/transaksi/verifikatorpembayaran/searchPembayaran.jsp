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
        .modal { overflow-y: auto}
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/VerifikatorPembayaranAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#bayar_rawat_jalan, #pembayaran_active').addClass('active');
            $('#pembayaran_open').addClass('menu-open');
        });


    </script>
    <style>
        .top-7{
            margin-top: 7px;
        }
    </style>
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
            Verifikator Pembayaran
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Pembayaran</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="searchForm" method="post" namespace="/onlinepaymentverif" action="search_onlinepaymentverif.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Pasien</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="antrianTelemedic.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Antrian</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_antrian" cssStyle="margin-top: 7px"
                                                     name="antrianTelemedic.id" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Transaksi</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_transaksi" cssStyle="margin-top: 7px"
                                                     name="antrianTelemedic.idTransaksi" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Jenis Pasien</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'asuransi':'ASURANSI', 'bpjs':'BPJS'}" cssStyle="margin-top: 7px"
                                                  headerKey="umum" headerValue="UMUM" name="antrianTelemedic.idJenisPeriksaPasien"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status Transaksi</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'finish':'FINISH'}" cssStyle="margin-top: 7px"
                                                  headerKey="exist" headerValue="EXISTING" name="antrianTelemedic.statusTransaksi"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="searchForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_kasirjalan.action">
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
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
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
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>" alt="Loading..."/></center>
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
                        <table id="sortTable" class="table table-bordered table-striped" style="font-size: 13px">
                            <thead >
                                <tr bgcolor="#90ee90">
                                    <td>Id</td>
                                    <td>Id Pasien</td>
                                    <td>Nama</td>
                                    <td>Jenis</td>
                                    <td>Status</td>
                                    <td>Status konsultasi</td>
                                    <td>Approve konsultasi</td>
                                    <td>Status Resep</td>
                                    <td>Approve Resep</td>
                                    <td align="center">Action</td>
                                </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResults" var="row">
                                <tr>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td>
                                        <s:if test='#row.idJenisPeriksaPasien == "asuransi"'>
                                            <s:property value="idJenisPeriksaPasien"/>  <s:property value="namaAsuransi"/>
                                        </s:if>
                                        <s:else>
                                            <s:property value="idJenisPeriksaPasien"/>
                                        </s:else>
                                    </td>
                                    <td><s:property value="ketStatus"/></td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.flagEresep != "Y"'>
                                            <s:if test='#row.idJenisPeriksaPasien == "umum"'>
                                                <s:if test='#row.flagBayarKonsultasi == "Y"'>
                                                    <label class="label label-success"> Sudah Bayar</label>
                                                </s:if>
                                                <s:else>
                                                    <label class="label label-warning"> Belum Bayar</label>
                                                </s:else>
                                            </s:if>
                                            <s:else>
                                                <s:if test='#row.flagBayarKonsultasi == "Y"'>
                                                    <label class="label label-success"> Terverifikasi </label>
                                                </s:if>
                                                <s:else>
                                                    <label class="label label-warning"> Belum Diverifikasi </label>
                                                </s:else>
                                            </s:else>

                                        </s:if>
                                    </td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.approveKonsultasi == "Y"'>
                                            <label class="label label-success"> <i class="fa fa-check"></i></label>
                                        </s:if>
                                    </td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.flagResep == "Y"'>
                                            <s:if test='#row.idJenisPeriksaPasien == "umum"'>
                                                <s:if test='#row.flagBayarResep == "Y"'>
                                                    <label class="label label-success"> Sudah Bayar</label>
                                                </s:if>
                                                <s:else>
                                                    <label class="label label-warning"> Belum Bayar</label>
                                                </s:else>
                                            </s:if>
                                            <s:else>
                                                <s:if test='#row.flagBayarResep == "Y"'>
                                                    <label class="label label-success"> Terverifikasi </label>
                                                </s:if>
                                                <s:else>
                                                    <label class="label label-warning"> Belum Diverifikasi </label>
                                                </s:else>
                                            </s:else>
                                        </s:if>
                                    </td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.approveResep == "Y"'>
                                            <label class="label label-success"> <i class="fa fa-check"></i></label>
                                        </s:if>
                                    </td>
                                    <td align="center">
                                        <s:if test='#row.statusTransaksi == "finish"'>
                                            <button class="btn btn-sm btn-primary" onclick="viewDetail('<s:property value="id"/>','<s:property value="idJenisPeriksaPasien"/>')"><i class="fa fa-search"></i></button>
                                        </s:if>
                                        <s:else>
                                            <button class="btn btn-sm btn-primary" onclick="viewDetail('<s:property value="id"/>','<s:property value="idJenisPeriksaPasien"/>')"><i class="fa fa-edit"></i></button>
                                        </s:else>
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

<div class="modal fade" id="modal-invoice">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Pembayaran</h4>
            </div>
            <div class="modal-body">
                <%--<div class="box-header with-border">--%>
                    <%--<h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>--%>
                <%--</div>--%>
                <div class="box-body">
                    <div class="row">
                        <!-- /.col -->
                    </div>
                </div>

                <%--<div class="box-header with-border">--%>
                    <%--<h3 class="box-title"><i class="fa fa-medkit"></i> Daftar Tindakan Rawat</h3>--%>
                <%--</div>--%>
                <div class="box-body">

                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_fin_error"></p>
                    </div>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_fin">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_fin">Approve Berhasil</p>
                    </div>

                    <table class="table table-bordered table-striped" id="tabel_tindakan_fin" style="font-size: 12px">
                        <thead>
                        <tr bgcolor="#90ee90" id="head_tindakan_fin">
                            <%--<td>Id</td>--%>
                            <%--<td width="20%">Keterangan</td>--%>
                            <%--<td>Approve Flag</td>--%>
                            <%--<td>Approve Who</td>--%>
                            <%--<td align="center" width="20%">Total Tarif (Rp.)</td>--%>
                            <%--<td align="center" width="20%">View Bukti</td>--%>
                            <%--<td width="10%" align="center">Action</td>--%>
                        </tr>
                        </thead>
                        <tbody id="body_tindakan_fin">
                        </tbody>
                        <input type="hidden" id="fin_id_antrian"/>
                        <input type="hidden" id="fin_id_jenis_periksa_pasien"/>
                    </table>
                    <hr>
                    <br>
                    <div id="detail-invoice-bpjs" style="display: none;">
                        <div class="row top-7">
                            <div class="col-md-3" align="right">No. Kartu BPJS :</div>
                            <div class="col-md-6">
                                <span id="dt-no-kartu-bpjs"></span>
                                <button onclick="checkBpjs()" class="btn btn-sm btn-success" id="dt-btn-check-bpjs" style="margin-left: 20px">Check</button>
                                <button class="btn btn-sm btn-success" id="dt-icon-check-bpjs" style="display: none;margin-left: 20px;"><i class="fa fa-check"></i></button>
                            </div>
                            <input type="hidden" id="dt-flag-check-bpjs" value="N">
                        </div>
                        <div class="row top-7">
                            <div class="col-md-3" align="right">a.n. :</div>
                            <div class="col-md-6"><span id="dt-nama-pasien-bpjs"></span></div>
                        </div>
                        <div class="row top-7">
                            <div class="col-md-3" align="right">Keluhan Pasien :</div>
                            <div class="col-md-6"><textarea class="form-control" cols="4" rows="3" id="dt-keluhan-bpjs" readonly></textarea></div>
                        </div>
                        <div class="row top-7">
                            <div class="col-md-3" align="right">Diagnosa ICD 10 :</div>
                            <div class="col-md-6">
                                <input type="text" id="dt-diagnosa-awal-bpjs" style="margin-top: 7px" onkeypress="$(this).css('border','')" class="form-control" required="false"/>
                                <script>
                                    var menus, mapped;
                                    $('#dt-diagnosa-awal-bpjs').typeahead({
                                        minLength: 3,
                                        source: function (query, process) {
                                            menus = [];
                                            mapped = {};

                                            var data = [];
                                            dwr.engine.setAsync(false);
                                            CheckupAction.getListBpjsDiagnosaAwal(query, function (listdata) {
                                                data = listdata;
                                            });

                                            $.each(data, function (i, item) {
                                                var labelItem = item.namaDiagnosaBpjs;
                                                mapped[labelItem] = {
                                                    id: item.kodeDiagnosaBpjs,
                                                    label: labelItem,
                                                    name: item.namaDiagnosaBpjs
                                                };
                                                menus.push(labelItem);
                                            });

                                            process(menus);
                                        },
                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            // insert to textarea diagnosa_ket
                                            $("#dt-ket-diagnosa-bpjs").val(selectedObj.name);
                                            return selectedObj.id;
                                        }
                                    });
                                </script>
                                <textarea rows="4" id="dt-ket-diagnosa-bpjs"
                                            style="margin-top: 7px" readonly="true"
                                            class="form-control"></textarea>
                            </div>
                        </div>
                        <div class="row top-7">
                            <div class="col-md-3" align="right">Cover BPJS :</div>
                            <div class="col-md-6"><input type="number" class="form-control" id="dt-cover-bpjs" readonly></div>
                        </div>
                        <div class="row top-7">
                            <div class="col-md-3" align="right"></div>
                            <div class="col-md-6">
                                <button onclick="verifikasiBpjs()" class="btn btn-sm btn-success" id="dt-btn-ver-bpjs" style="display: none;">Verifikasi</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border"></div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <%--<button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close--%>
                <%--</button>--%>
                <%--<button type="button" class="btn btn-success" id="save_fin" onclick="confirmSavePembayaranTagihan()"><i class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success"--%>
                        <%--id="load_fin"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
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

<div class="modal fade" id="modal-view-bukti">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> View bukti
                </h4>
            </div>
            <div class="modal-body">
                <div id="body-view-bukti" style="text-align: center">

                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-detail-asuransi">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-document"></i> Approve Asuransi</span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="col-md-offset-3">
                    <div class="row">
                        <div class="col-md-6"><h3 id="dt-nama-asuransi"></h3></div>
                    </div>
                    <div class="row top-7">
                        <div class="col-md-3" align="right">No. Kartu :</div>
                        <div class="col-md-6"><span id="dt-no-kartu-asuransi"></span></div>
                    </div>
                    <div class="row top-7">
                        <div class="col-md-3" align="right">a.n. :</div>
                        <div class="col-md-6"><span id="dt-nama-pasien-asuransi"></span></div>
                    </div>
                    <div class="row top-7">
                        <div class="col-md-3" align="right">Input Cover :</div>
                        <div class="col-md-6"><input type="number" class="form-control" id="dt-cover-asuransi"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close--%>
                <%--</button>--%>
                <div id="btn-save-asuransi"></div>
            </div>
        </div>
    </div>
</div>

<%--<div class="modal fade" id="modal-detail-bpjs">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                    <%--<span aria-hidden="true">&times;</span></button>--%>
                <%--<h4 class="modal-title"><i class="fa fa-document"></i> Approve Pasien Bpjs</span>--%>
                <%--</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin_bpjs">--%>
                    <%--<h4><i class="icon fa fa-ban"></i> Warning!</h4>--%>
                    <%--<p id="msg_fin_error_bpjs"></p>--%>
                <%--</div>--%>
                <%--<div class="alert alert-success alert-dismissible" style="display: none" id="success_fin_bpjs">--%>
                    <%--<h4><i class="icon fa fa-info"></i> Info!</h4>--%>
                    <%--<p id="msg_fin_bpjs">Approve Berhasil</p>--%>
                <%--</div>--%>
                <%--<div class="col-md-offset-3">--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-6"><h3 id="dt-nama-pelayanan-bpjs"></h3></div>--%>
                    <%--</div>--%>
                    <%--<div class="row top-7">--%>
                        <%--<div class="col-md-3" align="right">No. Kartu :</div>--%>
                        <%--<div class="col-md-6"><span id="dt-no-kartu-bpjs"></span></div>--%>
                    <%--</div>--%>
                    <%--<div class="row top-7">--%>
                        <%--<div class="col-md-3" align="right">a.n. :</div>--%>
                        <%--<div class="col-md-6"><span id="dt-nama-pasien-bpjs"></span></div>--%>
                    <%--</div>--%>
                    <%--<div class="row top-7">--%>
                        <%--<div class="col-md-3" align="right">Keluhan Pasien :</div>--%>
                        <%--<div class="col-md-6"><textarea class="form-control" cols="4" rows="3"></textarea></div>--%>
                    <%--</div>--%>
                    <%--<div class="row top-7">--%>
                        <%--<div class="col-md-3" align="right">Diagnosa ICD 10 :</div>--%>
                        <%--<div class="col-md-6"></div>--%>
                    <%--</div>--%>
                    <%--<div class="row top-7">--%>
                        <%--<div class="col-md-3" align="right">Cover BPJS :</div>--%>
                        <%--<div class="col-md-6"><input type="number" class="form-control" id="dt-cover-bpjs"></div>--%>
                    <%--</div>--%>
                    <%--<div class="row top-7">--%>
                        <%--<div class="col-md-3" align="right"></div>--%>
                        <%--<div class="col-md-6"><button onclick="checkBpjs()" class="btn btn-success">Check</button></div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--&lt;%&ndash;<button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close&ndash;%&gt;--%>
                <%--&lt;%&ndash;</button>&ndash;%&gt;--%>
                <%--<div id="btn-save-bpjs"></div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>


<script type='text/javascript'>

    function formatRupiah(angka) {
        if(angka != "" && angka != null && parseInt(angka) > 0){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        } else {
            return "0";
        }
    }

    function searchPage(idAntrian) {
        $("#id_antrian").val(idAntrian);
        $("#searchForm").submit();
    }

    function viewDetail(idAntrian, idJenisPeriksaPasien) {
        $("#modal-invoice").modal('show');
        showDetail(idAntrian, idJenisPeriksaPasien)
    }
    function showDetail(idAntrian, idJenisPeriksaPasien){

        $("#fin_id_jenis_periksa_pasien").val(idJenisPeriksaPasien);

        var head = "";
        if (idJenisPeriksaPasien == "asuransi"){
            head = "<td>Id</td>" +
                "<td width=\"20%\">Keterangan</td>" +
                "<td>Approve Flag</td>" +
                "<td>Approve Who</td>" +
                "<td>No. Kartu</td>" +
                "<td>Cover</td>" +
                "<td align=\"center\" width=\"20%\">Total Tarif (Rp.)</td>" +
                "<td align=\"center\" width=\"20%\">View Bukti</td>" +
                "<td>Action</td>";
        } else if (idJenisPeriksaPasien == "bpjs") {

            $("#detail-invoice-bpjs").show();

            head = "<td>Id</td>" +
                "<td width=\"20%\">Keterangan</td>" +
                "<td>Approve Flag</td>" +
                "<td>Approve Who</td>" +
                "<td>No. Kartu</td>" +
                "<td>SEP</td>" +
                "<td align=\"center\" width=\"20%\">Total Tarif (Rp.)</td>" +
                "<td align=\"center\" width=\"20%\">View Bukti</td>" +
                "<td>Action</td>";
        } else {
            head = "<td>Id</td>" +
                "<td width=\"20%\">Keterangan</td>" +
                "<td>Approve Flag</td>" +
                "<td>Approve Who</td>" +
                "<td align=\"center\" width=\"20%\">Total Tarif (Rp.)</td>" +
                "<td align=\"center\" width=\"20%\">View Bukti</td>" +
                "<td>Action</td>";
        }

        VerifikatorPembayaranAction.getSessionAntrianTelemedic(idAntrian, function (telemedicEntity) {

            var data = telemedicEntity;
            if (data != null){

                if (idJenisPeriksaPasien == "bpjs"){
                    $("#dt-no-kartu-bpjs").text(data.noKartu);
                    $("#dt-nama-pasien-bpjs").text(data.namaPasien);
                    $("#dt-keluhan-bpjs").val(data.keluhan);
                    $("#dt-cover-bpjs").val(data.jumlahCover);
                    $("#dt-diagnosa-awal-bpjs").val(data.idDiagnosa);
                    $("#dt-ket-diagnosa-bpjs").text(data.ketDiagnosa);

                    if (data.noSep != null){
                        $("#dt-btn-check-bpjs").hide();
                        $("#dt-diagnosa-awal-bpjs").prop('readOnly', 'true');
                    }
                }

            }

            VerifikatorPembayaranAction.listDetailPembayaran(idAntrian, function (response) {

                var str = "";
                $.each(response, function (i, item) {
                    str += "<tr>" +
                        "<td>"+item.id+"</td>"+
                        "<td>"+item.keterangan+"</td>"+
                        "<td align='center'>"+ iconFlag(item.approvedFlag) +"</td>"+
                        "<td>"+ nullEscape(item.approvedWho) +"</td>";

                    if (idJenisPeriksaPasien == "asuransi"){
                        str += "<td>"+item.noKartu+"</td>"+
                            "<td align='right'>"+ formatRupiah ( item.jumlahCover )+"</td>";
                    } if (idJenisPeriksaPasien == "bpjs"){
                        str += "<td>"+item.noKartu+"</td>"+
                            "<td>"+ item.noSep+"</td>";
                    }

                    str += "<td align='right'>"+ formatRupiah( item.nominal )+"</td>";

                    if (item.flagBayar == "Y"){

                        if (item.approvedFlag == "Y" || item.noSep == null){
                            str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\')\"><i class='fa fa-search'></i></button></td>"+
                                "<td align='center'></td>";
                        } else {

                            if (item.flagEresep == "Y"){
                                str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\')\"><i class='fa fa-search'></i></button></td>"+
                                    "<td align='center'><button class='btn btn-sm btn-success' onclick=\"saveApproveEresep(\'"+item.id+"\')\"><i class='fa fa-check'></i> Approve E-Resep</button></td>";
                            } else {
                                str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\')\"><i class='fa fa-search'></i></button></td>"+
                                    "<td align='center'><button class='btn btn-sm btn-success' onclick=\"saveApprove(\'"+item.id+"\')\"><i class='fa fa-check'></i> Approve</button></td>";
                            }
                        }

                    } else {
//                        console.log("Id Item : "+item.idItem);
                        if (idJenisPeriksaPasien == "asuransi" && item.nominal != null) {
                            str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\')\"><i class='fa fa-search'></i></button></td>"+
                                "<td align='center'><button class='btn btn-sm btn-success' onclick=\"actionApprove(\'"+item.id+"\')\"><i class='fa fa-edit'></i> VerifiKasi</button></td>";
                        } else {
                            str += "<td></td>" +
                                "<td></td>";
                        }
                    }
                    str += "</tr>";
                });
                $("#head_tindakan_fin").html(head);
                $("#fin_id_antrian").val(idAntrian);
                $("#body_tindakan_fin").html(str);
            });

        });



    }

    function iconFlag(var1) {
        if (var1 == "Y")
            return "<label class=\"label label-success\"> <i class=\"fa fa-check\"></i></label>";
        else if (var1 == "N")
            return "<label class=\"label label-danger\"> <i class=\"fa fa-cross\"></i></label>";
        else
            return "";
    }

    function nullEscape(var1) {
        if (var1 == null)
            return "";
        else
            return var1;
    }

    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }

    function viewBukti(var1){
        $("#modal-view-bukti").modal('show');
        var urlImg = firstpath()+"/images/upload/bukti_transfer/"+var1;
        $("#body-view-bukti").html("<img src='"+urlImg+"' style='max-width: 500px;'></img>");
    }

    function saveApprove(idTransaksi) {
        var idAntrian = $("#fin_id_antrian").val();
        $("#msg_fin").text("Loading . . .");
        $("#success_fin").show();

        dwr.engine.setAsync(true);
        VerifikatorPembayaranAction.approveTransaksi(idTransaksi, function (response) {
            dwr.engine.setAsync(false);
            if (response.status == "error"){
                $("#warning_fin").show().fadeOut(5000);
                $("#success_fin").hide();
                $("#msg_fin_error").text(response.message);
            } else {
                $("#success_fin").show().fadeOut(5000);
                searchPage(idAntrian)
            }
        });
    }

    function saveApproveEresep(idTransaksi){
        $("#success_fin").show().fadeOut(5000);
        var idAntrian = $("#fin_id_antrian").val();

        VerifikatorPembayaranAction.approveEresep(idTransaksi, function (response) {
            dwr.engine.setAsync(false);
            if (response.status == "error"){
                $("#warning_fin").show().fadeOut(5000);
                $("#success_fin").hide();
                $("#msg_fin_error").text(response.message);
            } else {
                $("#success_fin").show().fadeOut(5000);
                searchPage(idAntrian)
            }
        });
    }

    function actionApprove(id) {
        var jenisPasien = $("#fin_id_jenis_periksa_pasien").val();
        $("#dt-id-transaksi-asuransi").val(id);
        if (jenisPasien == "asuransi"){
            var idAntrian = $("#fin_id_antrian").val();
            VerifikatorPembayaranAction.getSessionAntrianTelemedic(idAntrian, function (telemedicEntity) {
                if (telemedicEntity != null){
                    var item = telemedicEntity;

                    if (jenisPasien == "asuransi"){
                        $("#modal-detail-asuransi").modal('show');
                        $("#dt-nama-pasien-asuransi").text(item.namaPasien);
                        $("#dt-nama-asuransi").html(item.namaAsuransi);
                        $("#dt-no-kartu-asuransi").text(item.noKartu);
                        $("#dt-cover-asuransi").val(item.jumlahCover);
                        if (item.jumlahCover != null){
                            $("#dt-cover-asuransi").prop('readonly', true);
                        } else {
                            $("#dt-cover-asuransi").prop('readonly', false);
                        }
                    }
                    //console.log("Jumlah Cover : " + item.jumlahCover)
                }
            });

            $("#btn-save-asuransi").html("<button type=\"button\" class=\"btn btn-success\" id=\"save-detail-save\" onclick=\"approveAsuransi(\'"+idAntrian+"\', \'"+id+"\')\"><i class=\"fa fa-arrow-right\"></i> Save</button>");
        } else {
            saveApprove(id)
        }
    }

    function approveAsuransi(idAntrian, idTransaksi) {
        var cover = $("#dt-cover-asuransi").val();
        VerifikatorPembayaranAction.saveCoverAsuransi(idAntrian, cover, idTransaksi, function (response) {
            if (response.status == "error"){
                $("#warning_fin").show();
                $("#success_fin").hide();
                $("#msg_fin_error").text(response.message);
            } else {
                $("#modal-detail-asuransi").modal('hide');
                searchPage(idAntrian);
            }
        });
    }

    function checkBpjs(){
        var nokartu = $("#dt-no-kartu-bpjs").text();
        $("#dt-flag-check-bpjs").val("Y");
        $("#dt-btn-check-bpjs").hide();
        $("#dt-icon-check-bpjs").show();
        $("#dt-btn-ver-bpjs").show();
    }

    function verifikasiBpjs() {

        var idAntrian = $("#fin_id_antrian").val();
        var noKartu = $("#dt-no-kartu-bpjs").text();
        var idDiagnosa = $("#dt-diagnosa-awal-bpjs").val();
        var ketDiagnosa = $("#dt-ket-diagnosa-bpjs").val();

        VerifikatorPembayaranAction.saveVerifikasiBpjs(idAntrian, noKartu, idDiagnosa, ketDiagnosa, function (response) {
            if (response.status == "error"){
                $("#warning_fin").show();
                $("#success_fin").hide();
                $("#msg_fin_error").text(response.message);
            } else {
                $("#success_fin").show().fadeOut(5000);
                searchPage(idAntrian)
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>