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
                                        <s:select list="#{'asuransi':'ASURANSI'}" cssStyle="margin-top: 7px"
                                                  headerKey="umum" headerValue="UMUM" name="antrianTelemedic.idJenisPeriksaPasien"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status Transaksi</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'confirmation':'KONFIRMASI','finish':'SELESAI', 'canceled':'BATAL'}" cssStyle="margin-top: 7px"
                                                  headerKey="exist" headerValue="PROSES" name="antrianTelemedic.statusTransaksi"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Hari Ini</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:checkbox value="Y" name="antrianTelemedic.flagDateNow" cssStyle="margin-top: 10px;" id="check_hari_ini"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal </label>
                                    <div class="col-sm-4">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <s:textfield name="antrianTelemedic.stDateFrom" cssClass="form-control datepicker"
                                                             required="false"/>
                                            </div>
                                            <div class="col-md-2">
                                                To
                                            </div>
                                            <div class="col-md-5">
                                                <s:textfield id="tgl_to" name="antrianTelemedic.stDateTo" cssClass="form-control datepicker"
                                                             required="false"/>
                                            </div>
                                        </div>
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
                                        <a type="button" class="btn btn-danger" href="initForm_onlinepaymentverif.action">
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
                                    <td>Id Telemedic</td>
                                    <td>No. RM</td>
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
                                            <s:if test='#row.flagBatalDokter == "Y"'>
                                                <label class="label label-danger"> Dibatalkan Dokter </label>
                                            </s:if>
                                            <s:else>
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
                                                        <s:if test='#row.idJenisPeriksaPasien == "asuransi"'>
                                                            <label class="label label-warning"><s:property value="labelStatusAsuransi"/></label>
                                                        </s:if>
                                                        <s:else>
                                                            <label class="label label-warning"> Belum Diverifikasi </label>
                                                        </s:else>
                                                    </s:else>
                                                </s:else>
                                            </s:else>
                                        </s:if>
                                    </td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.approveKonsultasi == "Y"'>
                                            <img src="<s:url value="/pages/images/icon_success.ico" />">
                                            <%--<label class="label label-success"> <i class="fa fa-check"></i></label>--%>
                                        </s:if>
                                    </td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.flagBatalDokter == "Y"'>
                                            <label class="label label-danger"> Dibatalkan Dokter </label>
                                        </s:if>
                                        <s:else>
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
                                                        <s:if test='#row.idJenisPeriksaPasien == "asuransi"'>
                                                            <label class="label label-warning"><s:property value="labelStatusAsuransi"/></label>
                                                        </s:if>
                                                        <s:else>
                                                            <label class="label label-warning"> Belum Diverifikasi </label>
                                                        </s:else>
                                                    </s:else>
                                                </s:else>
                                            </s:if>
                                        </s:else>
                                    </td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.approveResep == "Y"'>
                                            <img src="<s:url value="/pages/images/icon_success.ico" />">
                                        </s:if>
                                    </td>
                                    <td align="center">
                                        <s:if test='#row.flagBatalDokter == "Y"'>
                                            <s:if test='#row.idJenisPeriksaPasien == "umum"'>
                                                <s:if test='#row.flagDanaDikembaLikan == "N"'>
                                                    <button class="btn btn-sm btn-primary" onclick="viewBatalDokter('<s:property value="idBatalDokterTelemedic"/>', '<s:property value="id"/>', '<s:property value="idJenisPeriksaPasien"/>', '<s:property value="alasanBatal"/>')"><i class="fa fa-money"></i></button>
                                                </s:if>
                                                <s:else>
                                                    <s:url var="print_invo" namespace="/onlinepaymentverif" action="printBuktiRefund_onlinepaymentverif" escapeAmp="false">
                                                        <s:param name="id"><s:property value="id"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{print_invo}" target="_blank">
                                                        <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                                    </s:a>
                                                    <%--<img src="<s:url value="/pages/images/icon_success.ico" />">--%>
                                                </s:else>
                                            </s:if>
                                            <s:else>
                                                <img src="<s:url value="/pages/images/icon_success.ico" />">
                                            </s:else>
                                        </s:if>
                                        <s:else>
                                            <s:if test='#row.statusTransaksi == "finish"'>
                                                <button class="btn btn-sm btn-primary" onclick="viewDetail('<s:property value="id"/>','<s:property value="idJenisPeriksaPasien"/>')"><i class="fa fa-search"></i></button>
                                            </s:if>
                                            <s:else>
                                                <button class="btn btn-sm btn-primary" onclick="viewDetail('<s:property value="id"/>','<s:property value="idJenisPeriksaPasien"/>')"><i class="fa fa-edit"></i></button>
                                            </s:else>
                                        </s:else>

                                        <%--<button class="btn btn-sm btn-primary" onclick="showDialog('loading')">loading test</button>--%>
                                        <%--<button class="btn btn-sm btn-primary" onclick="showDialog('success')">success test</button>--%>
                                        <%--<button class="btn btn-sm btn-primary" onclick="showDialog('error')">error test</button>--%>

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
                    <div id="list-button-asuransi"></div>
                    <div id="detail-invoice-bpjs" style="display: none;">
                        <div class="row top-7">
                            <div class="col-md-3" align="right">No. Kartu BPJS :</div>
                            <div class="col-md-6">
                                <span id="dt-no-kartu-bpjs"></span>
                                <button onclick="checkBpjs()" class="btn btn-sm btn-success" id="dt-btn-check-bpjs" style="margin-left: 20px">Check</button>
                                <button class="btn btn-sm btn-success" id="dt-icon-check-bpjs" style="display: none;margin-left: 20px;"><i class="fa fa-check"></i></button>
                                <button class="btn btn-sm btn-danger" id="dt-icon-denied-bpjs" style="display: none;margin-left: 20px;"><i class="fa fa-times"></i></button>
                            </div>
                            <input type="hidden" id="dt-flag-check-bpjs" value="N">
                            <input type="hidden" id="dt-kelas-pasien-bpjs">
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
                                <button onclick="verifikasiBpjs()" class="btn btn-sm btn-success" id="dt-btn-ver-bpjs" style="display: none; float: right"><i class="fa fa-check"></i> Verifikasi</button>
                                <button onclick="searchPage()" class="btn btn-sm btn-success" id="dt-btn-ref-bpjs" style="display:none; float: right"><i class="fa fa-refresh"></i> OK & Refresh</button>
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

<div class="modal fade" id="modal-detail-asuransi">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-document"></i> <span id="label-modal-detail-asuransi"> Approve Asuransi</span>
                </h4>
            </div>
            <div class="modal-body">

                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin_asuransi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_fin_error_asuransi"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_fin_asuransi">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_fin_asuransi">Approve Berhasil</p>
                </div>

                <div class="col-md-offset-3" style="font-size: 13px">
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

                    <div class="row top-7" style="display: none; text-align: center" id="dt-bukti">

                    </div>

                    <div id="dt-list-tarif" style="display: none">
                        <div class="row top-7">
                            <div class="col-md-9">
                                <table class="table table-striped table-bordered" style="font-size: 13px;">
                                    <thead style="background-color: #90ee90;">
                                    <tr>
                                        <td>Keterangan</td>
                                        <td>Jenis</td>
                                        <td>Biaya</td>
                                    </tr>
                                    </thead>
                                    <tbody id="dt-body-list-tarif">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <span style="display: none; color: red" id="dt-msg-belum-bayar">Resep Belum Siap / Belum ada Biaya</span>
                        <input type="hidden" id="dt-belum-bayar">
                        <div class="row top-7">
                            <div class="col-md-3" align="right" id="label-cover">Biaya Dibayar Asuransi : </div>
                            <div class="col-md-6"><input type="number" class="form-control input-sm" id="dt-cover-asuransi"/></div>
                            <input type="hidden" id="h-dt-cover-asuransi">
                        </div>
                        <div id="dt-body-check-bayar">
                            <div class="row top-7">
                                <div class="col-md-3" align="right"></div>
                                <div class="col-md-6">
                                    <input type="checkbox" id="dt-check-bayar-asuransi" oninput="showBayar(this.checked)"/> Biaya Dibayar Pasien
                                </div>
                            </div>
                        </div>
                        <div id="dt-body-dibayar-pasien" style="display: none">
                            <div class="row top-7">
                                <div class="col-md-3" align="right">Dibayar Pasien : </div>
                                <div class="col-md-6"><input type="number" class="form-control input-sm" id="dt-bayar-asuransi" oninput="kurangiCover(this.value)"/></div>
                            </div>
                        </div>
                    </div>
                    <div id="dt-body-struk">
                        <div class="row top-7">
                            <div class="col-md-3" align="right">Upload Struk</div>
                            <div class="col-md-6">
                                <div class="input-group" id="img_file">
                                    <span class="input-group-btn">
                                          <span class="btn btn-default btn-file">
                                              Browse… <s:file id="imgInp" accept=".jpg" name="fileUpload"
                                                       onchange="$('#img_file').css('border','')"></s:file>
                                          </span>
                                    </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                            </div>
                        </div>
                        <canvas id="img_ktp_canvas" style="border: solid 1px #ccc; width: 300px;"></canvas>
                    </div>

                    <input type="hidden" id="dt-id-struk">
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close--%>
                <%--</button>--%>
                <div id="btn-save-asuransi">
                    <%--<button class="btn btn-success" onclick="uploadStrukAsuransi()"><i class="fa fa-check"></i> Save</button>--%>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-batal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> View Batal Dokter
                </h4>
            </div>
            <div class="modal-body">
                <div class="row top-7">
                    <div class="col-md-3" align="right">Dokter : </div>
                    <div class="col-md-6"><span id="btl-nama-dokter"></span></div>
                    <input type="hidden" id="btl-h-id-antrian"/>
                    <input type="hidden" id="btl-h-id-batal-telemedic"/>
                </div>
                <div class="row top-7">
                    <div class="col-md-3" align="right">Alasan : </div>
                    <div class="col-md-6"><textarea class="form-control" id="btl-alasan-batal" cols="3" rows="3" disabled></textarea></div>
                </div>
                <div class="row top-7">
                    <div class="col-md-12" align="center">Data Transaksi Pasien</div>
                </div>
                <div class="row top-7" align="center" id="btl-list-transaksi">
                    <div class="col-md-12" align="center">
                        <table class="table table-striped table-bordered" style="font-size: 13px;" align="center">
                            <thead style="background-color: #90ee90;">
                            <tr>
                                <td>Id</td>
                                <td>Keterangan</td>
                                <td>Biaya</td>
                                <td>Bukti</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody id="btl-body-list-tarif">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-sm btn-primary" id="save_pengembalian"><i class="fa fa-arrow-right"></i> Konfirmasi Pengembalian Dana--%>
                <%--</button>--%>
            <%--</div>--%>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-dialog-batal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Konfirmasi Pengembalian Dana
                </h4>
            </div>
            <div class="modal-body">
                <div class="row top-7" align="center" id="btl-bukti">

                </div>
                <div class="row top-7">
                    <div class="col-md-3" align="right">
                        <label>Nominal Pengembalian</label>
                    </div>
                    <div class="col-md-7" align="right">
                        <input type="number" class="form-control" id="btl-nominal-app-batal"/>
                    </div>
                </div>
                <h4 align="center" class="top-7">Setuju Pengembalian Dana ?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" style="margin-right: 20px;"><i class="fa fa-times"></i> No
                </button>
                <div id="btl-save-con" style="float:right;">

                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-bukti">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> View bukti
                </h4>
            </div>
            <div class="modal-body">
                <div id="body-view-bukti" style="text-align: center">

                </div>
                <input type="hidden" id="id-transaksi-bukti"/>
            </div>
            <div class="modal-footer">
                <button class="btn btn-warning" id="btn-kembali-bukti" style="display: none" onclick="kembalikanBukti()"><i class="fa fa-repeat"></i> Kembalikan Bukti Pembayaran</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
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

<div class="modal fade" id="modal-loading-dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Saving ...
                </h4>
            </div>
            <div class="modal-body">
                <div id="waiting-content" style="text-align: center">
                    <h4>Please don't close this window, server is processing your request ...</h4>
                    <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                         src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                         name="image_indicator_write">
                    <br>
                    <img class="spin" border="0"
                         style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                         src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                         name="image_indicator_write">
                </div>

                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin_waiting">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_fin_error_waiting"></p>
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <%--<button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-success-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Success
                </h4>
            </div>
            <div class="modal-body" style="text-align: center">
                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                     name="icon_success">
                Record has been saved successfully.
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <button type="button" class="btn btn-sm btn-success" id="ok_con"><i class="fa fa-check"></i> Ok
                </button>
            </div>
        </div>
    </div>
</div>



<script type='text/javascript'>

    $( document ).ready(function() {
        var canvas = document.getElementById('img_ktp_canvas');
        var ctx = canvas.getContext('2d');
        $("#check_hari_ini").prop( "checked", true );

        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        $('.btn-file :file').on('fileselect', function (event, label) {

            var input = $(this).parents('.input-group').find(':text'),
                log = label;

            if (input.length) {
                input.val(log);
                var reader = new FileReader();
                reader.onload = function(event){
                    var img = new Image();
                    img.onload = function(){
                        canvas.width = img.width;
                        canvas.height = img.height;
                        ctx.clearRect(0,0,canvas.width,canvas.height);
                        ctx.drawImage(img,0,0);
                    }
                    img.src = event.target.result;
                }
                reader.readAsDataURL(event.target.files[0]);
            } else {
                if (log) alert(log);
            }

        });
    });

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

    function showDialog(tipe) {
        if (tipe == "loading"){
            $("#modal-loading-dialog").modal('show');
        }
        if (tipe == "error"){
            $("#modal-loading-dialog").modal('show');
            $("#waiting-content").hide();
            $("#warning_fin_waiting").show();
        }
        if (tipe == "success"){
            $("#modal-loading-dialog").modal('hide');
            $("#modal-success-dialog").modal('show');
        }
    }

    function searchPage(idAntrian) {
        if (idAntrian == null || idAntrian == ""){
            var idAntrian_ = $("#fin_id_antrian").val();
            $("#id_antrian").val(idAntrian_);
            $("#searchForm").submit();
        } else {
            $("#id_antrian").val(idAntrian);
            $("#searchForm").submit();
        }
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
//                "<td>Cover</td>" +
                "<td align=\"center\" width=\"20%\">Total Tarif (Rp.)</td>" +
//                "<td align=\"center\" width=\"20%\">View Bukti</td>" +
                "<td>Action</td>";
        } else if (idJenisPeriksaPasien == "bpjs") {

            $("#detail-invoice-bpjs").show();
            $("#dt-diagnosa-awal-bpjs").val("");
            $("#dt-ket-diagnosa-bpjs").val("");
            $("#dt-cover-bpjs").val(0);
            $("#dt-btn-check-bpjs").show();
            $("#dt-icon-check-bpjs").hide();
            $("#dt-icon-denied-bpjs").hide();
            $("#dt-btn-ver-bpjs").hide();

            head = "<td>Id</td>" +
                "<td width=\"20%\">Keterangan</td>" +
                "<td>Approve Flag</td>" +
                "<td>Approve Who</td>" +
                "<td>No. Kartu</td>" +
                "<td>SEP</td>" +
                "<td align=\"center\" width=\"20%\">Total Tarif (Rp.)</td>" +
//                "<td align=\"center\" width=\"20%\">View Bukti</td>" +
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
                    $("#dt-cover-bpjs").val( formatRupiah(data.jumlahCover));
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
                        str += "<td>"+item.noKartu+"</td>";
                    } if (idJenisPeriksaPasien == "bpjs"){
                        str += "<td>"+item.noKartu+"</td>"+
                            "<td>" + nullEscape(item.noSep)+"</td>";
                    }

                    str += "<td align='right'>"+ formatRupiah( item.nominal )+"</td>";

                    if (item.flagBayar == "Y"){

                        if (idJenisPeriksaPasien == "bpjs"){
                            if (item.noSep == null || item.nominal == 0 || item.approvedFlag == "Y"){
                                str += "<td align='center'></td>";
                            } else {
                                str += "<td align='center'><button class='btn btn-sm btn-success' onclick=\"saveApprove(\'"+item.id+"\')\"><i class='fa fa-check'></i> Approve</button></td>";
                            }
                        } else if (idJenisPeriksaPasien == "asuransi"){
                            if (item.nominal == 0 || item.approvedFlag == "Y"){
                                str += "<td align='center'></td>";
                            } else {
                                str += "<td align='center'><button class='btn btn-sm btn-success' onclick=\"saveApprove(\'"+item.id+"\')\"><i class='fa fa-check'></i> Approve</button></td>";
                            }
                        } else {

                            if (item.approvedFlag == "Y"){
                                str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\',\'"+idJenisPeriksaPasien+"\',\'"+item.id+"\',\'"+item.approvedFlag+"\')\"><i class='fa fa-search'></i></button></td>"+
                                    "<td align='center'></td>";
                            } else {

                                if (item.flagEresep == "Y"){
                                    str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\',\'"+idJenisPeriksaPasien+"\',\'"+item.id+"\',\'"+item.approvedFlag+"\')\"><i class='fa fa-search'></i></button></td>"+
                                        "<td align='center'><button class='btn btn-sm btn-success' onclick=\"saveApproveEresep(\'"+item.id+"\')\"><i class='fa fa-check'></i> Approve E-Resep</button></td>";
                                } else {
                                    str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\',\'"+idJenisPeriksaPasien+"\',\'"+item.id+"\',\'"+item.approvedFlag+"\')\"><i class='fa fa-search'></i></button></td>"+
                                        "<td align='center'><button class='btn btn-sm btn-success' onclick=\"saveApprove(\'"+item.id+"\')\"><i class='fa fa-check'></i> Approve</button></td>";
                                }
                            }

                        }
                    } else {
                        if (idJenisPeriksaPasien == "asuransi"){
                            str += "<td></td>";
                        } else {
                            str += "<td></td>" +
                                "<td></td>";
                        }

//                        console.log("Id Item : "+item.idItem);
//                        if (idJenisPeriksaPasien == "asuransi" && item.nominal != 0) {
//                            str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\')\"><i class='fa fa-search'></i></button></td>"+
//                                "<td align='center'><button class='btn btn-sm btn-success' onclick=\"actionApprove(\'"+item.id+"\')\"><i class='fa fa-edit'></i> VerifiKasi</button></td>";
//                        } else {
//                            str += "<td></td>" +
//                                "<td></td>";
//                        }
                    }
                    str += "</tr>";
                });

                $("#head_tindakan_fin").html(head);
                $("#fin_id_antrian").val(idAntrian);
                $("#body_tindakan_fin").html(str);

                if (idJenisPeriksaPasien == "asuransi"){
                    var flagApprove = data.flagApproveConfirm;
                    var flagViewApprove = data.flagViewApproveConfirm;
                    if (flagApprove == "Y"){
                        getDataStrukAsuransi(idAntrian);
                        var btn = "<button class='btn btn-primary' onclick=\"viewModalDetailAsuransi(\'"+idAntrian+"\', \'approve\')\"><i class='fa fa-edit'></i> Approve Confirmation</button>";
                        var btnListAsuransi = $("#list-button-asuransi").html();
                        btnListAsuransi = btnListAsuransi + btn;
                        $("#list-button-asuransi").html(btnListAsuransi);
                    } else if (flagViewApprove == "Y") {
                        getDataStrukAsuransi(idAntrian);
                        var btn = "<button class='btn btn-success' onclick=\"viewModalDetailAsuransi(\'"+idAntrian+"\', \'view_approve\')\"><i class='fa fa-search'></i> View Approve Confirmation</button>";
                        var btnListAsuransi = $("#list-button-asuransi").html();
                        btnListAsuransi = btnListAsuransi + btn;
                        $("#list-button-asuransi").html(btnListAsuransi);
                    } else {
                        getDataStrukAsuransi(idAntrian);
                    }
                }
            });

        });
    }

    function iconFlag(var1) {
        if (var1 == "Y")
            return "<img src='<s:url value="/pages/images/icon_success.ico" />'>";
        else if (var1 == "N")
            return "<img src='<s:url value="/pages/images/icon_failure.ico" />'>";
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

    function viewBukti(var1, idJenisPerikasPasien, idTransaksi, flag){
        $("#modal-view-bukti").modal('show');
        var urlImg = firstpath()+"/images/upload/bukti_transfer/"+var1;
        if (var1 == null || var1 == "" || var1 == "null"){
            urlImg = "<s:url value="/pages/images/ktp-default.jpg" />";
        }
        $("#body-view-bukti").html(
            "<img src='"+urlImg+"' style='max-width: 500px;'/>"
        );

        console.log(idJenisPerikasPasien +" - "+ idTransaksi );
        if ( idJenisPerikasPasien ==  "umum" && flag != "Y"){
            $("#btn-kembali-bukti").show();
            $("#id-transaksi-bukti").val(idTransaksi);
        }
    }

    function kembalikanBukti() {
        var idTransaksi = $("#id-transaksi-bukti").val();
        var idAntrian = $("#fin_id_antrian").val();
        showDialog("loading");
        dwr.engine.setAsync(true);
        VerifikatorPembayaranAction.kembalikanBukti(idTransaksi, function (response) {
            dwr.engine.setAsync(false);
            if (response.status == "error"){
                showDialog("error");
                $("#msg_fin_error_waiting").text(response.message);
            } else {
                showDialog("success");
                $('#ok_con').on('click', function () {
                    searchPage(idAntrian)
                });
            }
        });

    }

    function saveApprove(idTransaksi) {
        var idAntrian = $("#fin_id_antrian").val();

        showDialog("loading");
        dwr.engine.setAsync(true);
        VerifikatorPembayaranAction.approveTransaksi(idTransaksi, function (response) {
            dwr.engine.setAsync(false);
            if (response.status == "error"){
                showDialog("error");
                $("#msg_fin_error_waiting").text(response.message);
            } else {
                showDialog("success");
                $('#ok_con').on('click', function () {
                    searchPage(idAntrian)
                });
            }
        });
    }

    function saveApproveEresep(idTransaksi){
        var idAntrian = $("#fin_id_antrian").val();

        showDialog("loading");
        dwr.engine.setAsync(true);
        VerifikatorPembayaranAction.approveEresep(idTransaksi, function (response) {
            dwr.engine.setAsync(false);
            if (response.status == "error"){
                showDialog("error");
                $("#msg_fin_error_waiting").text(response.message);
            } else {
                showDialog("success");
                $('#ok_con').on('click', function () {
                    searchPage(idAntrian)
                });
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
        if (cover == "" || parseInt(cover) == 0){
            $("#warning_fin_asuransi").show().fadeOut(5000);
            $("#msg_fin_error_asuransi").text("Nilai Cover Tidak Boleh Kosong.");
        } else {

            showDialog("loading");
            dwr.engine.setAsync(true);
            VerifikatorPembayaranAction.saveCoverAsuransi(idAntrian, cover, idTransaksi, function (response) {
                dwr.engine.setAsync(false);
                if (response.status == "error"){

                    showDialog("error");
                    $("#msg_fin_error_waiting").text(response.message);
                } else {

                    showDialog("success");
                    $('#ok_con').on('click', function (e) {
                        searchPage(idAntrian)
                    });
                }
            });
        }
    }

    function checkBpjs(){
        var nokartu = $("#dt-no-kartu-bpjs").text();
        CheckupAction.checkStatusBpjs(nokartu, function (response) {
            if (response.keteranganStatusPeserta == "AKTIF") {
                $("#dt-flag-check-bpjs").val("Y");
                $("#dt-btn-check-bpjs").hide();
                $("#dt-icon-check-bpjs").show();
                $("#dt-btn-ver-bpjs").show();
                $("#dt-kelas-pasien-bpjs").val(response.kodeKelas)
            } else {
                $("#dt-btn-check-bpjs").hide();
                $("#dt-icon-denied-bpjs").show();
            }
        });
    }

    function verifikasiBpjs() {

        var idAntrian = $("#fin_id_antrian").val();
        var noKartu = $("#dt-no-kartu-bpjs").text();
        var idDiagnosa = $("#dt-diagnosa-awal-bpjs").val();
        var ketDiagnosa = $("#dt-ket-diagnosa-bpjs").val();
        var kelasPasien = $("#dt-kelas-pasien-bpjs").val();

        VerifikatorPembayaranAction.saveVerifikasiBpjs(idAntrian, noKartu, idDiagnosa, ketDiagnosa, kelasPasien, function (response) {
            if (response.status == "error"){
                $("#warning_fin").show().fadeOut(5000);
                $("#success_fin").hide();
                $("#msg_fin_error").text(response.msg);
                $("#dt-diagnosa-awal-bpjs").prop("readOnly",'true');
            } else {
                $("#success_fin").show();
                $("#dt-btn-ver-bpjs").hide();
                $("#dt-btn-ref-bpjs").show();
            }
        });
    }

    function getDataStrukAsuransi(idAntrian) {
        VerifikatorPembayaranAction.getListStrukAsuransi(idAntrian, function (res) {
            var str = "";
            $.each(res, function (i, item) {
                if (item.urlFotoStruk == null && item.approveFlag == null){
                    if (item.jenis == "authorization")
                        str += "<button class='btn btn-primary' onclick=\"viewModalDetailAsuransi(\'"+idAntrian+"\', \'"+item.jenis+"\')\"><i class='fa fa-edit'></i> Authorization</button>";
                    if (item.approveFlag == null && item.urlFotoStruk == null && item.jenis == "confirmation")
                        str += "<button class='btn btn-primary' onclick=\"viewModalDetailAsuransi(\'"+idAntrian+"\', \'"+item.jenis+"\')\"><i class='fa fa-edit'></i> Confirmation</button>";
                } else {
                    if (item.jenis == "authorization")
                        str += "<button class='btn btn-success' onclick=\"viewUploadStrukAsuransi(\'"+idAntrian+"\', \'"+item.jenis+"\')\"><i class='fa fa-search'></i> View Authorization</button>";
                    if (item.jenis == "confirmation")
                        str += "<button class='btn btn-success' onclick=\"viewUploadStrukAsuransi(\'"+idAntrian+"\', \'"+item.jenis+"\')\"><i class='fa fa-search'></i> View Confrimation</button>";
                }
            })

            $("#list-button-asuransi").html(str);
        });
    }

    function viewUploadStrukAsuransi(idAntrian, jenis) {
        VerifikatorPembayaranAction.getSessionAntrianTelemedic(idAntrian, function (telemedicEntity) {
            if (telemedicEntity != null){
                var item = telemedicEntity;
                $("#modal-detail-asuransi").modal('show');
                $("#dt-nama-pasien-asuransi").text(item.namaPasien);
                $("#dt-nama-asuransi").html(item.namaAsuransi);
                $("#dt-no-kartu-asuransi").text(item.noKartu);
                $("#dt-cover-asuransi").val(item.jumlahCover);
                $("#dt-body-struk").hide();
                $("#btn-save-asuransi").html("");

                VerifikatorPembayaranAction.getStrukAsuransiByIdAntrianAndJenis(idAntrian, jenis, function (res) {

                    if (res.urlFotoStruk != null){
                        var pathFoto = firstpath()+"/images/upload/bukti_transfer/"+res.urlFotoStruk;
                        var imghtml = "<div class='col-md-9'><img src='"+pathFoto+"' style='max-width: 300px;'/></div>";
                        $("#dt-bukti").show();
                        $("#dt-bukti").html(imghtml);
                    }

//                        console.log(res);
                    if (res.jenis == "authorization"){
                        $("#label-modal-detail-asuransi").text("View Authorization");
                        $("#dt-id-struk").val(res.id);
                        $("#dt-list-tarif").hide();
                    }

                    if (res.jenis == "confirmation"){
                        $("#label-modal-detail-asuransi").text("View Confirmation");
                        if (item.flagBelumBayar == "Y"){
                            $("#dt-msg-belum-bayar").show();
                            $("#dt-belum-bayar").val(item.flagBelumBayar);
                        }
                        $("#dt-id-struk").val(res.id);
                        showListBiaya(idAntrian);
//                        $("#dt-cover-asuransi").val(item.jumlahCover);
//                        $("#dt-bayar-asuransi").val(item.dibayarPasien);
                        $("#dt-cover-asuransi").hide();
                        $("#dt-body-check-bayar").hide();
                        $("#label-cover").hide();
                    }
                });
            }
        });
    }

    function viewModalDetailAsuransi(idAntrian, jenis){

        VerifikatorPembayaranAction.getSessionAntrianTelemedic(idAntrian, function (telemedicEntity) {
            if (telemedicEntity != null){
                var item = telemedicEntity;
                $("#modal-detail-asuransi").modal('show');
                $("#dt-nama-pasien-asuransi").text(item.namaPasien);
                $("#dt-nama-asuransi").html(item.namaAsuransi);
                $("#dt-no-kartu-asuransi").text(item.noKartu);
                $("#dt-cover-asuransi").val(item.jumlahCover);

                var btn = "";
                if (jenis == "approve" || jenis == "view_approve"){
                    $("#label-modal-detail-asuransi").text("Approve Pembayaran");

                    var pathFoto = firstpath()+"/images/upload/bukti_transfer/"+item.urlFotoStruk;
                    var imghtml = "<div class='col-md-9'><img src='"+pathFoto+"' style='max-width: 300px;'/></div>";
                    $("#dt-bukti").show();
                    $("#dt-bukti").html(imghtml);

                    showListBiaya(idAntrian);
                    showBayar(true);
                    $("#dt-cover-asuransi").val(item.jumlahCover);
                    $("#dt-bayar-asuransi").val(item.dibayarPasien);
                    $("#dt-cover-asuransi").prop("disabled", 'disabled');
                    $("#dt-bayar-asuransi").prop("disabled", 'disabled');
                    $("#dt-body-struk").hide();
                    $("#dt-body-check-bayar").hide();

                    if (jenis == "approve"){
                        btn = "<button type=\"button\" class=\"btn btn-success\" id=\"save-detail-save\" onclick=\"uploadStrukAsuransi(\'"+jenis+"\')\"><i class=\"fa fa-arrow-check\"></i> Approve</button>";
                        $("#btn-save-asuransi").html(btn);
                    }

                } else {
                    VerifikatorPembayaranAction.getStrukAsuransiByIdAntrianAndJenis(idAntrian, jenis, function (res) {
//                        console.log(res);
                        if (jenis == "authorization"){
                            $("#label-modal-detail-asuransi").text("Upload Authorization");
                            $("#dt-bukti").hide();
                            $("#dt-bukti").html("");
                            $("#dt-body-struk").show();
                            $("#dt-list-tarif").hide();

                            $("#dt-id-struk").val(res.id);
                            btn = "<button type=\"button\" class=\"btn btn-success\" id=\"save-detail-save\" onclick=\"uploadStrukAsuransi(\'"+res.jenis+"\')\"><i class=\"fa fa-arrow-right\"></i> Save</button>";
                        }
                        if (jenis == "confirmation"){
                            $("#label-modal-detail-asuransi").text("Upload Confirmation");
                            $("#dt-bukti").hide();
                            $("#dt-bukti").html("");
                            $("#dt-body-struk").show();
                            $("#dt-check-bayar-asuransi").prop("checked", false);
                            //$("#dt-body-dibayar-pasien").hide();
                            //$("#dt-bayar-asuransi").val(0);
                            showBayar(false);

                            if (item.flagBelumBayar == "Y"){
                                $("#dt-msg-belum-bayar").show();
                                $("#dt-belum-bayar").val(item.flagBelumBayar);
                            }
                            $("#dt-id-struk").val(res.id);
                            btn = "<button type=\"button\" class=\"btn btn-success\" id=\"save-detail-save\" onclick=\"uploadStrukAsuransi(\'"+res.jenis+"\')\"><i class=\"fa fa-arrow-right\"></i> Save</button>";
                            showListBiaya(idAntrian);
                        }
                        $("#btn-save-asuransi").html(btn);
                    });
                }
            }
        });
    }

    function showListBiaya(idAntrian) {

        VerifikatorPembayaranAction.getListRiwayatTindakanByIdAntrian(idAntrian, function (res) {
//            console.log("showListPembayaran" + res);
           var str = "";
           var totalTarif = 0;
           $.each(res, function (i, item) {
              str += "<tr>" +
                      "<td>"+ item.namaTindakan + "</td>"+
                      "<td>"+ item.jenisPasien +"</td>"+
                      "<td align='right'>"+ formatRupiah(item.totalTarif) +"</td>"+
                  "</tr>";

              totalTarif = totalTarif + parseInt(item.totalTarif);
           });
           str += "<tr>" +
               "<td align='right' colspan='2'>Total : </td>"+
               "<td align='right'>"+ formatRupiah(totalTarif) +"</td>"+
               "</tr>";

            $("#dt-cover-asuransi").val(totalTarif);
            $("#h-dt-cover-asuransi").val(totalTarif);
            $("#dt-list-tarif").show();
            $("#dt-body-list-tarif").html(str);
        });
    }

    function uploadStrukAsuransi(jenis) {
        //var uploadFile = $("#dt-struk-asuransi");
        //var uploadFile = dwr.util.getValue('dt-struk-asuransi');
        //var filenames = uploadFile.val().split("/");
        //var filename = filenames[filenames.length-1];
        //console.log(uploadFile);
        var idAntrian = $("#fin_id_antrian").val();
        var idStruk = $("#dt-id-struk").val();
        var canvas = document.getElementById('img_ktp_canvas');
        var dataURL = canvas.toDataURL("image/png"),
            dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");

        showDialog("loading");
        dwr.engine.setAsync(true);
        if (jenis == "authorization") {
            VerifikatorPembayaranAction.uploadStruk(dataURL, jenis, idStruk, "", "", function (res) {
                dwr.engine.setAsync(false);
                if (res.status == "success"){
                    showDialog("success");
                    $('#ok_con').on('click', function (e) {
                        searchPage(idAntrian);
                    });
                } else {
                    showDialog("error");
                    $("#msg_fin_error_waiting").text(res.msg);
                }
            });
        } else if (jenis == "confirmation") {
            var flagBelumBayar = $("#dt-belum-bayar").val();
            if (flagBelumBayar == "Y"){
                showDialog("error");
                $("#msg_fin_error_waiting").text("Tidak Bisa Lanjut. Ada Transaksi yang Belum Selesai");
            } else {
                var coverAsuransi = $("#dt-cover-asuransi").val();
                var bayarAsuransi = $("#dt-bayar-asuransi").val();
                VerifikatorPembayaranAction.uploadStruk(dataURL, jenis, idStruk, coverAsuransi, bayarAsuransi, function (res) {
                    dwr.engine.setAsync(false);
                    if (res.status == "success"){
                        showDialog("success");
                        $('#ok_con').on('click', function (e) {
                            searchPage(idAntrian);
                        });
                    } else {
                        showDialog("error");
                        $("#msg_fin_error_waiting").text(res.msg);
                    }
                });
            }
        } else {
            VerifikatorPembayaranAction.approveConfirmAsuransi(idAntrian, function (res) {
                dwr.engine.setAsync(false);
                if (res.status == "success"){
                    showDialog("success");
                    $('#ok_con').on('click', function (e) {
                        searchPage(idAntrian);
                    });
                } else {
                    showDialog("error");
                    $("#msg_fin_error_waiting").text(res.msg);
                }
            });
        }

    }

    function kurangiCover(tarif) {
        if (tarif == 0){
            $("#dt-cover-asuransi").val($("#h-dt-cover-asuransi").val());
        } else {
            var cover = $("#h-dt-cover-asuransi").val();
            $("#dt-cover-asuransi").val(parseInt(cover) - parseInt(tarif));
        }
    }

    function showBayar(checked){
        console.log("showBayar >>> "+checked);
        if (checked){
            $("#dt-body-dibayar-pasien").show();
        } else {
            var cover = $("#h-dt-cover-asuransi").val();
            $("#dt-cover-asuransi").val(cover);
            $("#dt-bayar-asuransi").val(0);
            $("#dt-body-dibayar-pasien").hide();
        }
    }

    function viewBatalDokter(idBatalDokter, idAntrian, idJenisPeriksaPasien, alasan) {
        $("#modal-view-batal").modal('show');
        $("#btl-nama-dokter").val("");
        $("#btl-h-id-antrian").val(idAntrian);
        $("#btl-alasan-batal").val(alasan);
        $("#btl-h-id-batal-telemedic").val(idBatalDokter);

        VerifikatorPembayaranAction.getSessionAntrianTelemedic(idAntrian, function (telemedicEntity) {
            $("#btl-nama-dokter").text(telemedicEntity.namaDokter);

            VerifikatorPembayaranAction.listDetailPembayaran(idAntrian, function (response) {

                var str = "";
                $.each(response, function(i, item){
                    if (item.nominal != null && item.nominal != 0){
                        str += "<tr>" +
                            "<td>"+item.id+"</td>"+
                            "<td>"+item.keterangan+"</td>"+
                            "<td align='right'>"+ formatRupiah( item.nominal ) +"</td>";

                        if (item.keterangan == "konsultasi"){
                            if (telemedicEntity.flagBayarKonsultasi == "Y"){
                                str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\')\"><i class='fa fa-search'></i></button></td>";
                                if (item.nominal == 0){
                                    str += "<td></td>";
                                } else {
                                    str += "<td align='center'><button class='btn btn-sm btn-primary'  onclick=\"showConfirmation(\'"+item.nominal+"\',\'"+item.keterangan+"\',\'"+item.urlFotoBukti+"\')\"><i class='fa fa-arrow-right'></i> Konfirmasi Pengembalian Dana</button></td>";
                                }
                            } else {
                                str += "<td></td>"+
                                    "<td></td>";
                            }

                        } else if (item.keterangan == "resep") {
                            if (telemedicEntity.flagBayarResep == "Y"){
                                str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\')\"><i class='fa fa-search'></i></button></td>";
                                if (item.nominal == 0){
                                    str += "<td></td>";
                                } else {
                                    str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"showConfirmation(\'"+item.nominal+"\',\'"+item.keterangan+"\',\'"+item.urlFotoBukti+"\')\"><i class='fa fa-arrow-right'></i> Konfirmasi Pengembalian Dana</button></td>";
                                }
                            } else {
                                str += "<td></td>"+
                                    "<td></td>";
                            }
                        }
                        str += "</tr>";
                    }
                });

                $("#btl-body-list-tarif").html(str);
            });
        });
    }

    function showConfirmation(nominal, jenis, urlFoto){
        $("#modal-confirm-dialog-batal").modal('show');
        $("#btl-nominal-app-batal").val(nominal);

        var idBatalTele = $("#btl-h-id-batal-telemedic").val();
        var pathFoto = firstpath()+"/images/upload/bukti_transfer/"+urlFoto;
        var imghtml = "<div class='col-md-12'><img src='"+pathFoto+"' style='max-width: 300px;'/></div>";
        var btn = "<button type=\"button\" class=\"btn btn-success\" onclick=\"approveConfirmBatal(\'"+idBatalTele+"\',\'"+jenis+"\')\"><i class=\"fa fa-check\"></i> Yes </button>";
        $("#btl-save-con").html(btn);
        $("#btl-bukti").html(imghtml);
    }

    function approveConfirmBatal(idBatalTele, jenis){
       var idAntrian = $("#btl-h-id-antrian").val();
       var nominal = $("#btl-nominal-app-batal").val();
       showDialog("loading");
       dwr.engine.setAsync(true);
       VerifikatorPembayaranAction.approveConfirmKembaliDana(idBatalTele, jenis, nominal, function (res) {
           dwr.engine.setAsync(false);
           if (res.status == "success"){
               showDialog("success");
               $('#ok_con').on('click', function (e) {
                   searchPage(idAntrian);
               });
           } else {
               showDialog("error");
               $("#msg_fin_error_waiting").text(res.msg);
           }
       });
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>