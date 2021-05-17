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
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PengajuanBiayaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MasterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <style>
        .modal-backdrop {
            z-index: -1;
        }
    </style>
    <style>
        .pagebanner {
            width: 100%;
            font-size: 14px;
        }

        .pagelinks {
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <script type='text/javascript'>
        function cekAvailableCoa(nilai) {
            var coa = nilai.value;
            var length = nilai.length;
            if (length != 0) {
                dwr.engine.setAsync(false);
                KodeRekeningAction.cekAvailableCoa(coa, function (listdata) {
                    if (listdata.length == 0) {
                        alert("COA tidak ada");
                        $('#kodeRekening').val("");
                        $('#namaKodeRekening').val("");
                    }
                });
            }
        }

        function link() {
            window.location.href = "<s:url action='initFormKoreksi_kas'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>


<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Koreksi Pengajuan Biaya ( Dengan Uang Muka )
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Search Koreksi Pengajuan Biaya ( Dengan Uang
                            Muka )</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="kasForm" method="post" theme="simple"
                                    namespace="/kas"
                                    action="searchKoreksiPengajuan_kas.action"
                                    cssClass="form-horizontal">
                                <s:hidden name="kas.tipeKas" value="KR" />
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Unit </label>
                                    <div class="col-sm-4">
                                        <s:if test='kas.branchIdUser == "KP"'>
                                            <s:action id="initComboBranch" namespace="/admin/branch"
                                                      name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId"
                                                      name="kas.branchId"
                                                      listKey="branchId" listValue="branchName" headerKey=""
                                                      headerValue="[Select one]" cssClass="form-control select2"/>
                                        </s:if>
                                        <s:else>
                                            <s:action id="initComboBranch" namespace="/admin/branch"
                                                      name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView"
                                                      name="kas.branchId" disabled="true"
                                                      listKey="branchId" listValue="branchName" headerKey=""
                                                      headerValue="[Select one]" cssClass="form-control select2"/>
                                            <s:hidden id="branchId" name="kas.branchId"/>
                                        </s:else>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Koreksi/Penyesuaian Kas/Bank ID</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="kasId"
                                                     name="kas.kasId"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No. Jurnal</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="noJurnal" name="kas.noJurnal"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal</label>
                                    <div class="col-sm-4">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl1" name="kas.stTanggalDari"
                                                         cssClass="form-control pull-right"
                                            />
                                            <div class="input-group-addon">
                                                s/d
                                            </div>
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl2" name="kas.stTanggalSelesai"
                                                         cssClass="form-control pull-right"
                                            />
                                        </div>
                                        <script>
                                            $('#tgl1').datepicker({
                                                dateFormat: 'dd-mm-yy'
                                            });
                                            $('#tgl2').datepicker({
                                                dateFormat: 'dd-mm-yy'
                                            });
                                        </script>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-primary"
                                                   formIds="kasForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>

                                        <a href="#" id="btnAddDetailPembayaran" class="btn btn-success"><i
                                                class="fa fa-plus"></i> Add Koreksi Pengajuan</a>

                                        <button type="button" class="btn btn-danger"
                                                onclick="window.location.href='<s:url
                                                        action="initFormKoreksi_kas"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
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
                    <div style="text-align: left !important;">
                        <div class="box-header with-border"></div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Koreksi Pengajuan Biaya ( Dengan
                                Uang Muka )</h3>
                        </div>
                        <div class="box-body">
                            <table id="tableKas"
                                   class="tableKas table table-bordered table-striped"
                                   style="font-size: 11px">
                                <thead>
                                <tr bgcolor="#90ee90" style="text-align: center">
                                    <td>ID</td>
                                    <td>Unit</td>
                                    <td>No. Jurnal</td>
                                    <td>Transaksi</td>
                                    <td>Tanggal</td>
                                    <td align="center">View</td>
                                    <td align="center">Approval Keu.</td>
                                    <td align="center">Approval Kasub.</td>
                                    <td align="center">Posting</td>
                                    <td align="center">Bukti</td>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="#session.listOfResult" var="row">
                                    <tr>
                                        <td style="text-align: center"><s:property
                                                value="kasId"/></td>
                                        <td><s:property value="branchName"/></td>
                                        <td style="text-align: center"><s:property value="noJurnal"/></td>
                                        <td><s:property value="stTipeTransaksi"/></td>
                                        <td><s:property value="stTanggal"/></td>
                                        <td align="center">
                                            <a href="javascript:;"
                                               data="<s:property value="%{#attr.row.kasId}"/>"
                                               class="item-view">
                                                <img border="0"
                                                     src="<s:url value="/pages/images/icons8-search-25.png"/>">
                                            </a>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.approvalKeuanganFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:if>
                                            <s:elseif test='#row.approvalKasubKeuanganFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='#row.registeredFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='#row.approvalKeuanganFlag == "N"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='#row.jabatan == "keu"'>
                                                <a href="javascript:;"
                                                   data="<s:property value="%{#attr.row.kasId}"/>"
                                                   class="item-approve-keu">
                                                    <img border="0"
                                                         src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>">
                                                </a>
                                            </s:elseif>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.approvalKasubKeuanganFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:if>
                                            <s:elseif test='#row.registeredFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='#row.approvalKasubKeuanganFlag == "N"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='#row.jabatan == "kasub"'>
                                                <a href="javascript:;"
                                                   data="<s:property value="%{#attr.row.kasId}"/>"
                                                   class="item-approve-kasub-keu">
                                                    <img border="0"
                                                         src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>">
                                                </a>
                                            </s:elseif>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.registeredFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:if>
                                            <s:elseif test='#row.jabatan == "ka"'>
                                                <a href="javascript:;"
                                                   data="<s:property value="%{#attr.row.kasId}"/>"
                                                   class="item-posting">
                                                    <img border="0"
                                                         src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>">
                                                </a>
                                            </s:elseif>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.registeredFlag == "Y"'>
                                                <a href="javascript:;"
                                                   data="<s:property value="%{#attr.row.noJurnal}"/>"
                                                   unit="<s:property value="%{#attr.row.branchId}"/>"
                                                   pembayaranId="<s:property value="%{#attr.row.kasId}"/>"
                                                   class="item-cetak-bukti">
                                                    <img border="0"
                                                         src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                                </a>
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
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>

<div class="modal fade" id="modal-posting-jurnal">
    <div class="modal-dialog modal-flat" style="width: 1300px">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Posting Jurnal</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Koreksi/Penyesuaian ID</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_pembayaran_id" onkeypress="$(this).css('border','')"
                                                 readonly="true" cssStyle="margin-top: 7px"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">No. Jurnal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_no_jurnal" onkeypress="$(this).css('border','')"
                                                 readonly="true"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Tipe Transaksi</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tipe_transaksi" onkeypress="$(this).css('border','')"
                                                 readonly="true"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Tanggal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tanggal" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">No. Referensi</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_no_slip_bank" onkeypress="$(this).css('border','')"
                                                 readonly="true"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Keterangan</label>
                                <div class="col-md-6">
                                    <s:textarea id="mod_keterangan" onkeypress="$(this).css('border','')"
                                                readonly="true"
                                                cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <div class="col-md-offset-1 col-md-10">
                                    <table style="width: 100%;"
                                           class="pembayaranTable table table-bordered">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-primary" id="btnLampiran"><i class="fa fa-file"></i> Lampiran
                </button>
                <script>
                    $('#btnLampiran').click(function () {
                        $('#modal-lampiran').modal('show');
                    })
                </script>
                <button type="button" class="btn btn-success" id="btnApproveKeu" data-dismiss="modal"><i
                        class="fa fa-arrow-right"></i> Approve Keu.
                </button>
                <button type="button" class="btn btn-danger" id="btnNotApproveKeu" data-dismiss="modal"><i
                        class="fa fa-close"></i> Not Approve Keu.
                </button>
                <button type="button" class="btn btn-success" id="btnApproveKasub" data-dismiss="modal"><i
                        class="fa fa-arrow-right"></i> Approve Kasub.
                </button>
                <button type="button" class="btn btn-danger" id="btnNotApproveKasub" data-dismiss="modal"><i
                        class="fa fa-close"></i> Not Approve Kasub.
                </button>
                <button type="button" class="btn btn-success" id="btnPostingJurnal" data-dismiss="modal"><i
                        class="fa fa-arrow-right"></i> Posting
                </button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-add-pengajuan">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Add Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <s:hidden id="mod_branch_id"/>
                            <s:hidden id="mod_nip_uang_muka"/>
                            <s:hidden id="mod_bukti_uang_muka"/>

                            <div class="form-group" id="pengajuan_detail_id_view">
                                <label class="col-md-4" style="margin-top: 7px">Pengajuan ID</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_pengajuan_detail_id" onkeypress="$(this).css('border','')"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"/>
                                </div>
                                <script>
                                    $(document).ready(function () {
                                        var functions, mapped;
                                        $('#mod_pengajuan_detail_id').typeahead({
                                            minLength: 1,
                                            source: function (query, process) {
                                                functions = [];
                                                mapped = {};
                                                var data = [];
                                                dwr.engine.setAsync(false);
                                                console.log("cek");
                                                PengajuanBiayaAction.cariPengajuanBiayaDetailUangMuka(query, function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    var labelItem = item.pengajuanBiayaDetailId + " | " + item.keperluan;
                                                    mapped[labelItem] = {
                                                        id: item.pengajuanBiayaDetailId,
                                                        keperluan: item.keperluan,
                                                        tanggalRealisasi: item.stTanggalRealisasi,
                                                        tipePengajuan: item.transaksi,
                                                        noBudgeting: item.noBudgeting,
                                                        divisiId: item.divisiId,
                                                        divisiName: item.divisiName,
                                                        coaDivisi: item.coaDivisi,
                                                        coaLawan: item.coa,
                                                        coaLawanName: item.coaName,
                                                        noKontrak: item.noKontrak,
                                                        uangMuka: item.stUangMuka,
                                                        nipUangMuka: item.nipUangMuka,
                                                        buktiUangMuka: item.buktiUangMuka,
                                                        namaKontrak: item.namaKontrak,
                                                        jumlah: item.stJumlah
                                                    };
                                                    functions.push(labelItem);
                                                });
                                                process(functions);
                                            },
                                            updater: function (item) {
                                                var selectedObj = mapped[item];
                                                var uangMuka = selectedObj.uangMuka.replace(/[,]/g, ".");
                                                var jumlahPembayaran = selectedObj.jumlah.replace(/[,]/g, ".");
                                                $('#mod_tanggal_realisasi').val(selectedObj.tanggalRealisasi);
                                                $('#mod_tipe_pengajuan').val(selectedObj.tipePengajuan);
                                                $('#mod_jumlah_pengajuan').val(selectedObj.jumlah.replace(/[,]/g, "."));
                                                $('#mod_no_budgetting').val(selectedObj.noBudgeting);
                                                $('#mod_keperluan').val(selectedObj.keperluan);
                                                $('#mod_no_kontrak').val(selectedObj.noKontrak);
                                                $('#mod_nama_kontrak').val(selectedObj.namaKontrak);
                                                $('#mod_divisi_id').val(selectedObj.coaDivisi);
                                                $('#mod_nama_divisi').val(selectedObj.divisiName);
                                                $('#mod_nip_uang_muka').val(selectedObj.nipUangMuka);
                                                $('#mod_bukti_uang_muka').val(selectedObj.buktiUangMuka);
                                                $('#mod_jumlah_uang_muka').val(uangMuka);
                                                var option = '<option value="' + selectedObj.coaLawan + '">' + selectedObj.coaLawanName + '</option>';
                                                $('#mod_coa_lawan').html(option);
                                                $('#mod_jumlah_pembayaran').val(jumlahPembayaran);

                                                var tmpUangMuka = uangMuka.replace(/[.]/g, "");
                                                var tmpBayar = jumlahPembayaran.replace(/[.]/g, "");
                                                var nilaiUangMuka = parseInt(tmpUangMuka);
                                                var nilaiBayar = parseInt(tmpBayar);
                                                var hasilAkhir = Math.abs((nilaiBayar + (nilaiBayar * 10 / 100)) - ((nilaiBayar * 2 / 100) + nilaiUangMuka));
                                                $('#mod_netto').val(formatRupiahAtas(hasilAkhir));
                                                $('#mod_total_ppn').val(formatRupiahAtas(nilaiBayar * 10 / 100));
                                                $('#mod_total_pph').val(formatRupiahAtas(nilaiBayar * 2 / 100));
                                                getCoaBayar();

                                                return selectedObj.id;
                                            }
                                        });
                                    });

                                    function getCoaBayar() {
                                        var option = '<option value=""></option>';
                                        var tipeTransaksi = "47";
                                        KodeRekeningAction.getKodeRekeningLawanByTransId(tipeTransaksi, "K", function (res) {
                                            if (res.length > 0) {
                                                $.each(res, function (i, item) {
                                                    option += '<option value="' + item.kodeRekening + '">' + item.namaKodeRekening + '</option>';
                                                });
                                                $('#mod_metode_bayar').html(option);
                                            } else {
                                                $('#mod_metode_bayar').html(option);
                                            }
                                        });
                                    }
                                </script>
                            </div>
                            <s:hidden id="mod_keperluan"/>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">COA Lawan</label>
                                <div class="col-md-8">
                                    <select class="form-control modal_pengajuan" id="mod_coa_lawan"
                                            onchange="getDisableTrans()" style="margin-top: 7px">
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Metode Bayar</label>
                                <div class="col-md-8">
                                    <select class="form-control modal_pengajuan" id="mod_metode_bayar"
                                            style="margin-top: 7px">
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">ID Divisi</label>
                                <div class="col-md-3">
                                    <s:textfield id="mod_divisi_id" onkeypress="$(this).css('border','')" wajib="Y"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"
                                                 readonly="true"/>
                                </div>
                                <div class="col-md-5">
                                    <s:textfield id="mod_nama_divisi" onkeypress="$(this).css('border','')"
                                                 readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                            <div class="form-group" id="tanggal_realisasi_view">
                                <label class="col-md-4" style="margin-top: 7px">Tanggal Realisasi</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_tanggal_realisasi" onkeypress="$(this).css('border','')"
                                                 readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                            <div class="form-group" id="tipe_pengajuan_view">
                                <label class="col-md-4" style="margin-top: 7px">Tipe Pengajuan</label>
                                <div class="col-md-8">
                                    <s:select list="#{'R':'Rutin','I':'Investasi'}" cssStyle="margin-top: 7px"
                                              disabled="true"
                                              id="mod_tipe_pengajuan" headerKey="" headerValue=""
                                              cssClass="form-control modal_pengajuan"/>
                                </div>
                            </div>
                            <div class="form-group" id="no_budgetting_view">
                                <label class="col-md-4" style="margin-top: 7px">No. Budgeting</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_no_budgetting" onkeypress="$(this).css('border','')"
                                                 readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                            <div class="form-group" id="no_kontrak_view">
                                <label class="col-md-4" style="margin-top: 7px">No. Kontrak</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_no_kontrak" readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                            <div class="form-group" id="nama_kontrak_view">
                                <label class="col-md-4" style="margin-top: 7px">Nama Kontrak</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_nama_kontrak" readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                            <div class="form-group" id="jumlah_pengajuan_view">
                                <label class="col-md-4" style="margin-top: 7px">Jumlah Pengajuan (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_jumlah_pengajuan" readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Uang Muka (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_jumlah_uang_muka" cssClass="form-control modal_pengajuan"
                                                 cssStyle="margin-top: 7px" placeholder="0" readonly="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Jumlah Pembayaran (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_jumlah_pembayaran" onkeypress="$(this).css('border','')"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"
                                                 onkeyup="formatRupiah2(this),hitungNilaiNettoBruto(this.value)"/>
                                </div>
                            </div>
                            <script>
                                function hitungNilaiNettoBruto(val) {
                                    var ppn = $('#mod_total_ppn').val();
                                    var pph = $('#mod_total_pph').val();
                                    if (ppn == "") {
                                        ppn = "0";
                                    }
                                    if (pph == "") {
                                        pph = "0";
                                    }
                                    ppn = ppn.replace(/[.]/g, "");
                                    pph = pph.replace(/[.]/g, "");
                                    var uangMuka = $('#mod_jumlah_uang_muka').val();
                                    uangMuka = uangMuka.replace(/[.]/g, "");
                                    val = val.replace(/[.]/g, "");
                                    var nilai = parseInt(val);
                                    var nilaiUangMuka = parseInt(uangMuka);
                                    var nilaiPph = parseInt(pph);
                                    var nilaiPpn = parseInt(ppn);
                                    var hasilAkhir = Math.abs((nilai + nilaiPpn) - (nilaiPph + nilaiUangMuka));
                                    $('#mod_netto').val(formatRupiahAtas(hasilAkhir));
                                }
                            </script>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">PPN (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_total_ppn" cssClass="form-control modal_pengajuan"
                                                 onkeyup="formatRupiah2(this),hitungNilaiNettoPpn(this.value)"
                                                 cssStyle="margin-top: 7px" placeholder="0"/>
                                </div>
                            </div>
                            <script>
                                function hitungNilaiNettoPpn(val) {
                                    var pph = $('#mod_total_pph').val();
                                    if (pph == "") {
                                        pph = "0";
                                    }
                                    pph = pph.replace(/[.]/g, "");
                                    var bruto = $('#mod_jumlah_pembayaran').val();
                                    var uangMuka = $('#mod_jumlah_uang_muka').val();
                                    bruto = bruto.replace(/[.]/g, "");
                                    uangMuka = uangMuka.replace(/[.]/g, "");
                                    val = val.replace(/[.]/g, "");
                                    var nilai = parseInt(val);
                                    var nilaiBruto = parseInt(bruto);
                                    var nilaiUangMuka = parseInt(uangMuka);
                                    var nilaiPph = parseInt(pph);
                                    var hasilAkhir = Math.abs((nilaiBruto + nilai) - (nilaiPph + nilaiUangMuka));
                                    $('#mod_netto').val(formatRupiahAtas(hasilAkhir));
                                }
                            </script>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">PPH (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_total_pph" cssClass="form-control modal_pengajuan"
                                                 cssStyle="margin-top: 7px;"
                                                 onkeyup="formatRupiah2(this),hitungNilaiNettoPph(this.value)"
                                                 placeholder="0"/>
                                </div>
                            </div>
                            <script>
                                function hitungNilaiNettoPph(val) {
                                    var ppn = $('#mod_total_ppn').val();
                                    if (ppn == "") {
                                        ppn = "0";
                                    }
                                    ppn = ppn.replace(/[.]/g, "");
                                    var bruto = $('#mod_jumlah_pembayaran').val();
                                    var uangMuka = $('#mod_jumlah_uang_muka').val();
                                    bruto = bruto.replace(/[.]/g, "");
                                    uangMuka = uangMuka.replace(/[.]/g, "");
                                    val = val.replace(/[.]/g, "");
                                    var nilai = parseInt(val);
                                    var nilaiBruto = parseInt(bruto);
                                    var nilaiUangMuka = parseInt(uangMuka);
                                    var nilaiPpn = parseInt(ppn);
                                    var hasilAkhir = Math.abs((nilaiBruto + nilaiPpn) - (nilai + nilaiUangMuka));
                                    $('#mod_netto').val(formatRupiahAtas(hasilAkhir));
                                }
                            </script>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Netto (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_netto" cssClass="form-control modal_pengajuan"
                                                 cssStyle="margin-top: 7px;" readonly="true" placeholder="0"/>
                                </div>
                            </div>
                            <div class="form-group" id="kode_vendor_view_pengajuan">
                                <label class="col-md-4" style="margin-top: 7px">Kode Vendor</label>
                                <div class="col-md-3">
                                    <s:textfield id="mod_kode_vendor_pengajuan" onkeypress="$(this).css('border','')"
                                                 wajib="Y"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"/>
                                    <script>
                                        $(document).ready(function () {
                                            var functions, mapped;
                                            $('#mod_kode_vendor_pengajuan').typeahead({
                                                minLength: 1,
                                                source: function (query, process) {
                                                    functions = [];
                                                    mapped = {};
                                                    var data = [];
                                                    var master = $('#tipeMaster').val();
                                                    if (master != "") {
                                                        dwr.engine.setAsync(false);
                                                        MasterAction.initTypeaheadMasterPembayaran(query, master, function (listdata) {
                                                            data = listdata;
                                                        });
                                                        $.each(data, function (i, item) {
                                                            var labelItem = item.nomorVendor + " | " + item.nama;
                                                            mapped[labelItem] = {
                                                                id: item.nomorVendor,
                                                                nama: item.nama
                                                            };
                                                            functions.push(labelItem);
                                                        });
                                                        process(functions);
                                                    } else {
                                                        alert("belum memilih tipe pembayaran");
                                                    }

                                                },
                                                updater: function (item) {
                                                    var selectedObj = mapped[item];
                                                    $('#mod_nama_vendor_pengajuan').val(selectedObj.nama);
                                                    return selectedObj.id;
                                                }
                                            });
                                        });
                                    </script>
                                </div>
                                <div class="col-md-5">
                                    <s:textfield id="mod_nama_vendor_pengajuan" onkeypress="$(this).css('border','')"
                                                 readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">No. Faktur Pajak</label>
                                <div class="col-md-7">
                                    <s:textfield id="mod_no_faktur" cssClass="form-control modal_pengajuan"
                                                 cssStyle="margin-top: 7px;" readonly="true"/>
                                </div>
                                <div class="col-md-1">
                                    <a href="javascript:void(0)">
                                        <img style="margin-top: 10px" id="btnScanFaktur" border="0"
                                             src="<s:url value="/pages/images/icons8-qr-code-25.png"/>"
                                             name="icon_scan_faktur">
                                    </a>
                                </div>
                                <script>
                                    $('#btnScanFaktur').click(function () {
                                        var namaVendor = $('#mod_nama_vendor_pengajuan').val();
                                        if (namaVendor == "") {
                                            alert("Masukkan vendor terlebih dahulu");
                                        } else {
                                            $('.mod_scan_faktur').val('');
                                            $('#no_faktur_view').text("Scan QR disini");
                                            $("#mod_scan_faktur").prop('readonly', false);
                                            $('#modal-scan-faktur').modal('show');
                                        }
                                    })
                                </script>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Upload Faktur Pajak</label>
                                <div class="col-md-8">
                                    <div class="input-group" id="img_file" style="margin-top: 7px">
                                      <span class="input-group-btn">
                                      <span class="btn btn-default btn-file btn-file-1">
                                           Browse… <s:file id="imgInp" accept=".jpg" name="fileUpload"
                                                           onchange="$('#img_file').css('border','')"></s:file>
                                        </span>
                                        </span>
                                        <input type="text" class="form-control" readonly id="namaFile">
                                    </div>
                                    <canvas id="img_faktur_canvas" style="display: none"></canvas>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Keterangan</label>
                                <div class="col-md-8">
                                    <s:textarea id="mod_keterangan_pengajuan" onkeypress="$(this).css('border','')"
                                                cssStyle="margin-top: 7px" cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <br>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="mod_btnSaveDetailPembayaran" type="button" class="btn btn-default btn-success"><i
                        class="fa fa-plus"></i> Add</a>
                <script>
                    $('#mod_btnSaveDetailPembayaran').click(function () {
                        var pengajuanId = $('#mod_pengajuan_detail_id').val();
                        var kodeRekeningLawan = $('#mod_coa_lawan').val();
                        var metodeBayar = $('#mod_metode_bayar').val();
                        var stJumlahPembayaran = $('#mod_jumlah_pembayaran').val();
                        var stNetto = $('#mod_netto').val();
                        var stPpn = $('#mod_total_ppn').val();
                        var stPph = $('#mod_total_pph').val();
                        var kodeVendor = $('#mod_kode_vendor_pengajuan').val();
                        var stUangMuka = $('#mod_jumlah_uang_muka').val();
                        var nip = $('#mod_nip_uang_muka').val();
                        var buktiUangMuka = $('#mod_bukti_uang_muka').val();
                        var noFakturPajak = $('#mod_no_faktur').val();
                        var uploadFakturPajak = $('#namaFile').val();
                        var keterangan = $('#mod_keterangan_pengajuan').val();
                        var branchId = $('#mod_branch_id').val();
                        var divisiId = $('#mod_divisi_id').val();
                        var sumberDana = $('#mod_no_budgetting').val();

                        if (pengajuanId != "" && kodeRekeningLawan != "" && metodeBayar != "" && stJumlahPembayaran != "" && stNetto != "" && kodeVendor != "" &&
                            stUangMuka != "" && nip != "" && buktiUangMuka != "" && keterangan != "" && branchId != "" && divisiId != "" && sumberDana != "") {
                            if (noFakturPajak == "") {
                                if (confirm("Tidak ada faktur pajak, apakah anda ingin melanjutkan ?")) {
                                    if (confirm("Do you want to save this record ?")) {
                                        KasAction.saveAddKoreksiPengajuan(pengajuanId, kodeRekeningLawan, metodeBayar, stJumlahPembayaran, stNetto, stPpn, stPph,
                                            kodeVendor, stUangMuka, nip, buktiUangMuka, noFakturPajak, uploadFakturPajak, keterangan, branchId, divisiId, sumberDana, function (data) {
                                                alert(data);
                                                window.location.reload();
                                            });
                                    }
                                }
                            } else {
                                if (uploadFakturPajak != "" && stPpn != "") {
                                    if (confirm("Do you want to save this record ?")) {
                                        KasAction.saveAddKoreksiPengajuan(pengajuanId, kodeRekeningLawan, metodeBayar, stJumlahPembayaran, stNetto, stPpn, stPph,
                                            kodeVendor, stUangMuka, nip, buktiUangMuka, noFakturPajak, uploadFakturPajak, keterangan, branchId, divisiId, sumberDana, function (data) {
                                                alert(data);
                                                window.location.reload();
                                            });
                                    }
                                } else {
                                    var msg = "";
                                    if (uploadFakturPajak == "") {
                                        msg += "Faktur pajak belum di upload \n";
                                    }
                                    if (stPpn == "") {
                                        msg += "Nilai PPN masih kosong";
                                    }
                                    alert(msg);
                                }
                            }
                        } else {
                            var msg = "";
                            if (pengajuanId == "") {
                                msg += "Pengajuan ID kosong \n";
                            }
                            if (kodeRekeningLawan == "") {
                                msg += "Kode Rekening Lawan Kosong \n";
                            }
                            if (metodeBayar == "") {
                                msg += "Metode Bayar Kosong \n";
                            }
                            if (stJumlahPembayaran == "") {
                                msg += "Jumlah Pembayaran Kosong \n";
                            }
                            if (stNetto == "") {
                                msg += "Netto Kosong \n";
                            }
                            if (kodeVendor == "") {
                                msg += "Kode Vendor Kosong \n";
                            }
                            if (stUangMuka == "") {
                                msg += "Uang Muka Tidak Ditemukan \n";
                            }
                            if (nip == "") {
                                msg += "NIP Uang Muka Tidak Ditemukan \n";
                            }
                            if (buktiUangMuka == "") {
                                msg += "No. Nota Uang Muka Tidak Ditemukan \n";
                            }
                            if (keterangan == "") {
                                msg += "Keterangan Masih Kosong \n";
                            }
                            if (branchId == "") {
                                msg += "Unit kosong";
                            }
                            if (divisiId == "") {
                                msg += "Divisi ID kosong";
                            }
                            if (sumberDana == "") {
                                msg += "No. Budgetting kosong";
                            }
                            alert(msg);
                        }

                    })
                </script>

                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-scan-faktur">
    <div class="modal-dialog modal-flat modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> SCAN FAKTUR</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px" id="no_faktur_view">Scan QR disini</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_scan_faktur" onkeypress="$(this).css('border','')"
                                             onchange="generateNoFaktur(this.value)"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tanggal Faktur</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_tgl_faktur" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jumlah DPP</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_jumlah_dpp" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur"
                                             cssStyle="margin-top: 7px;text-align: right"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jumlah PPN</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_jumlah_ppn" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur"
                                             cssStyle="margin-top: 7px;text-align: right"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jumlah PPN BM</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_jumlah_ppn_bm" onkeypress="$(this).css('border','')"
                                             readonly="true"
                                             cssClass="form-control mod_scan_faktur"
                                             cssStyle="margin-top: 7px;text-align: right"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Status Approval</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_status_approval" onkeypress="$(this).css('border','')"
                                             readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Status Faktur</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_status_faktur" onkeypress="$(this).css('border','')"
                                             readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Referensi</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_referensi" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">NPWP Penjual</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_npwp_penjual" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nama Penjual</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_nama_penjual" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat Penjual</label>
                            <div class="col-md-8">
                                <s:textarea id="mod_alamat_penjual" onkeypress="$(this).css('border','')"
                                            readonly="true" rows="3"
                                            cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">NPWP Perusahaan</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_npwp_perusahaan" onkeypress="$(this).css('border','')"
                                             readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">NPWP Lawan</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_npwp_lawan_transaksi" onkeypress="$(this).css('border','')"
                                             readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nama Lawan</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_nama_lawan_transaksi" onkeypress="$(this).css('border','')"
                                             readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat Lawan</label>
                            <div class="col-md-8">
                                <s:textarea id="mod_alamat_lawan_transaksi" onkeypress="$(this).css('border','')"
                                            readonly="true" rows="3"
                                            cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px"/>
                            </div>
                        </div>
                    </div>
                </div>
                <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="btnAddNoFaktur" type="button" class="btn btn-default btn-success"><i class="fa fa-plus"></i> Add</a>
                <script>
                    $('#btnAddNoFaktur').click(function () {
                        var stTanggalFaktur = $('#mod_tgl_faktur').val();
                        var statusFaktur = $('#mod_status_faktur').val();
                        var currentDate = new Date();
                        currentDate.setMonth(currentDate.getMonth() - 3);
                        var dateParts = stTanggalFaktur.split("/");
                        var vendorFaktur = $('#mod_nama_penjual').val();
                        var vendor = $('#mod_nama_vendor_pengajuan').val();
                        var npwpLawan = $('#mod_npwp_lawan_transaksi').val();
                        var npwpPerusahaan = $('#mod_npwp_perusahaan').val();
                        var tanggalFaktur = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
                        if (statusFaktur != "") {
                            if (tanggalFaktur < currentDate) {
                                alert("Tanggal Faktur sudah tidak valid , silahkan membuat faktur baru");
                                $('.mod_scan_faktur').val('');
                                $('#modal-scan-faktur').modal('hide');
                            } else {
                                if (vendor == vendorFaktur) {
                                    alert("Berhasil menambahkan No. Faktur");
                                    $('#mod_no_faktur').val($('#mod_scan_faktur').val());
                                    $('#modal-scan-faktur').modal('hide');
                                } else {
                                    if (confirm("Nama vendor tidak sama , apakah anda tetap ingin menambahkan ? ")) {
                                        if (npwpLawan == npwpPerusahaan) {
                                            alert("Berhasil menambahkan No. Faktur");
                                            $('#mod_no_faktur').val($('#mod_scan_faktur').val());
                                            $('#modal-scan-faktur').modal('hide');
                                        } else {
                                            alert("NPWP Lawan yang berada di faktur tidak sama dengan NPWP perusahaan");
                                        }
                                    }
                                }
                            }
                        } else {
                            alert("QR atau faktur tidak valid ");
                            $('.mod_scan_faktur').val('');
                            $('#no_faktur_view').text("Scan QR disini");
                            $("#mod_scan_faktur").prop('readonly', false);
                        }
                    })
                </script>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-view-faktur" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">View Faktur</h4>
            </div>
            <div class="modal-body">
                <img src="" class="img-responsive" id="my-image">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-lampiran">
    <div class="modal-dialog modal-flat modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Daftar Lampiran</h4>
            </div>
            <div class="modal-body">
                <center class="box">
                    <br>
                    <br>
                    <%--<div class="row">--%>
                    <%--<label class="control-label col-sm-4">Nama Lampiran </label>--%>
                    <%--<div class="col-sm-8">--%>
                    <%--<s:textfield id="mod_nama_lampiran" onkeypress="$(this).css('border','')" cssClass="form-control modal_lampiran"/>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row" style="margin-top: 7px">--%>
                    <%--<label class="control-label col-sm-4">Lampiran (PDF/JPEG/PNG) </label>--%>
                    <%--<div class="col-sm-8">--%>
                    <%--<div class="input-group" id="img_file2"  style="margin-top: 7px">--%>
                    <%--<span class="input-group-btn">--%>
                    <%--<span class="btn btn-default btn-file btn-file-2">--%>
                    <%--Browse… <s:file id="imgInp2" accept=".jpg" name="fileUpload2"--%>
                    <%--onchange="$('#img_file2').css('border','')"></s:file>--%>
                    <%--</span>--%>
                    <%--</span>--%>
                    <%--<input type="text" class="form-control" readonly id="namaFile2">--%>
                    <%--</div>--%>
                    <%--<canvas id="img_faktur_canvas2" style="display: none"></canvas>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<br>--%>
                    <%--<div class="row" style="margin-top: 7px">--%>
                    <%--<center>--%>
                    <%--<a id="btnAddLampiran" type="button" class="btn btn-default btn-success"><i class="fa fa-plus"></i> Tambah</a>--%>
                    <%--</center>--%>
                    <%--</div>--%>
                    <%--<br>--%>
                    <div class="row">
                        <div class="col-md-12">
                            <table style="width: 100%;" class="tabelLampiran table table-bordered">
                            </table>
                            <br>
                        </div>
                    </div>
                </center>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>
</div>
<div id="modal-view-lampiran" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">View Lampiran</h4>
            </div>
            <div class="modal-body">
                <img src="" class="img-responsive" id="my-image2">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    $('#btnAddDetailPembayaran').click(function () {
        $('.modal_pengajuan').val('');
        $('#mod_branch_id').val($('#branchId').val());
        $('#modal-add-pengajuan').modal('show');
    });

    $(document).ready(function () {
        $('#tableKas').DataTable({
            "order": [[0, "desc"]],
            "pageLength": 50,
        });

        $('.tableKas').on('click', '.item-view', function () {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KasAction.getForModalPopUp(pembayaranId, function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            $("#btnPostingJurnal").hide();
            $("#btnApproveKeu").hide();
            $("#btnNotApproveKeu").hide();
            $("#btnApproveKasub").hide();
            $("#btnNotApproveKasub").hide();
            $("#modal-posting-jurnal").find('.modal-title').text('View Koreksi Kas/Bank');
            $("#modal-posting-jurnal").modal('show');
        });

        $('.tableKas').on('click', '.item-posting', function () {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KasAction.getForModalPopUp(pembayaranId, function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            $("#modal-posting-jurnal").find('.modal-title').text('Posting Jurnal');
            $("#modal-posting-jurnal").modal('show');
            $("#btnPostingJurnal").show();
            $("#btnApproveKeu").hide();
            $("#btnNotApproveKeu").hide();
            $("#btnApproveKasub").hide();
            $("#btnNotApproveKasub").hide();
        });

        $('.tableKas').on('click', '.item-approve-keu', function () {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KasAction.getForModalPopUp(pembayaranId, function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            $("#modal-posting-jurnal").find('.modal-title').text('Approval Admin Keuangan');
            $("#modal-posting-jurnal").modal('show');
            $("#btnPostingJurnal").hide();
            $("#btnApproveKeu").show();
            $("#btnNotApproveKeu").show();
            $("#btnApproveKasub").hide();
            $("#btnNotApproveKasub").hide();
        });

        $('.tableKas').on('click', '.item-approve-kasub-keu', function () {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KasAction.getForModalPopUp(pembayaranId, function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            $("#modal-posting-jurnal").find('.modal-title').text('Approval Kasub. Keuangan');
            $("#modal-posting-jurnal").modal('show');
            $("#btnPostingJurnal").hide();
            $("#btnApproveKeu").hide();
            $("#btnNotApproveKeu").hide();
            $("#btnApproveKasub").show();
            $("#btnNotApproveKasub").show();
        });

        $('.tableKas').on('click', '.item-cetak-bukti', function () {
            var noJurnal = $(this).attr('data');
            var branchId = $(this).attr('unit');
            var pembayaranId = $(this).attr('pembayaranId');
            var url = "printReportBuktiPosting_kas.action?kas.noJurnal=" + noJurnal + "&kas.branchId=" + branchId + "&kas.kasId=" + pembayaranId;
            window.open(url, '_blank');
        });

        $('#btnApproveKeu').click(function () {
            var pembayaranId = $('#mod_pembayaran_id').val();
            var who = "keu";
            var flag = "Y";
            if (confirm("apakah anda ingin melakukan approve Koreksi/Penyesuaian dengan pembayaran id " + pembayaranId + " ?")) {
                KasAction.approvePembayaran(pembayaranId, who, flag, function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        });

        $('#btnNotApproveKeu').click(function () {
            var pembayaranId = $('#mod_pembayaran_id').val();
            var who = "keu";
            var flag = "N";
            if (confirm("apakah anda ingin melakukan not approve Koreksi/Penyesuaian dengan pembayaran id " + pembayaranId + " ?")) {
                KasAction.approvePembayaran(pembayaranId, who, flag, function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        });

        $('#btnApproveKasub').click(function () {
            var pembayaranId = $('#mod_pembayaran_id').val();
            var who = "kasub";
            var flag = "Y";
            if (confirm("apakah anda ingin melakukan approve Koreksi/Penyesuaian dengan pembayaran id " + pembayaranId + " ?")) {
                KasAction.approvePembayaran(pembayaranId, who, flag, function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        });

        $('#btnNotApproveKasub').click(function () {
            var pembayaranId = $('#mod_pembayaran_id').val();
            var who = "kasub";
            var flag = "N";
            if (confirm("apakah anda ingin melakukan not approve Koreksi/Penyesuaian dengan pembayaran id " + pembayaranId + " ?")) {
                KasAction.approvePembayaran(pembayaranId, who, flag, function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        });

        $('#btnPostingJurnal').click(function () {
            var pembayaranId = $('#mod_pembayaran_id').val();
            if (confirm("apakah anda ingin memposting Koreksi/Penyesuaian dengan pembayaran id " + pembayaranId + " ?")) {
                KasAction.postingJurnal(pembayaranId, function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        })
    });
    window.loadPembayaran = function () {
        loadLampiran();
        $('.pembayaranTable').find('tbody').remove();
        $('.pembayaranTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        KasAction.searchPembayaranDetail(function (listdata) {
            tmp_table = "<thead style='font-size: 14px' ><tr class='active'>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196'>No</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Detail ID</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>No. Vendor</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Divisi ID</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>No. Nota</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Jml Pembayaran</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>No. Faktur Pajak</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>View Faktur Pajak</th>" +
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                var view = '<td align="center"></td>';
                if (item.noFakturPajak != "") {
                    view = '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-faktur' data ='" + item.urlFakturImage + "' judul ='" + item.noFakturPajak + "' >" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>" +
                        '</a>' +
                        '</td>';
                }
                tmp_table += '<tr style="font-size: 12px;">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="center">' + item.kasDetailId + '</td>' +
                    '<td align="center">' + item.masterId + '</td>' +
                    '<td align="center">' + item.divisiName + '</td>' +
                    '<td align="center">' + item.noNota + '</td>' +
                    '<td align="right">' + item.stJumlahPembayaran + '</td>' +
                    '<td align="center">' + item.noFakturPajak + '</td>' +
                    view +
                    "</tr>";
            });
            $('.pembayaranTable').append(tmp_table);
        });
    };

    $('.pembayaranTable').on('click', '.item-view-faktur', function () {
        var id = $(this).attr('data');
        var judul = $(this).attr('judul');
        dwr.engine.setAsync(false);
        $("#my-image").attr("src", id);
        $('#modal-view-faktur').find('.modal-title').text(judul);
        $('#modal-view-faktur').modal('show');
    });
    window.loadLampiran = function () {
        $('.tabelLampiran').find('tbody').remove();
        $('.tabelLampiran').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        KasAction.loadSessionLampiran(function (listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #30d196'>No</th>" +
                "<th style='text-align: center; background-color:  #30d196'>Nama Lampiran</th>" +
                "<th style='text-align: center; background-color:  #30d196'>View</th>" +
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center" >' + (i + 1) + '</td>' +
                    '<td align="center">' + item.namaLampiran + '</td>' +
                    '<td align="center">' +
                    "<a href='javascript:;' class ='item-view-lampiran' nama ='" + item.namaLampiran + "' url ='" + item.uploadFile + "'>" +
                    "<img border='0' src='<s:url value='/pages/images/icons8-search-25.png'/>'>" +
                    '</a>' +
                    '</td>' +
                    "</tr>";
            });
            $('.tabelLampiran').append(tmp_table);
        });
    };

    $('.tabelLampiran').on('click', '.item-view-lampiran', function () {
        var judul = $(this).attr('nama');
        var url = $(this).attr('url');
        $('#modal-view-lampiran').find('.modal-title').text(judul);
        $("#my-image2").attr("src", url);
        $('#modal-view-lampiran').modal('show');
    });

    function generateNoFaktur(val) {
        KasAction.generateQrEfaktur(val, function (result) {
            if (result != null) {
                $('#no_faktur_view').text("No. Faktur");
                $("#mod_scan_faktur").prop('readonly', true);
                $('#mod_tgl_faktur').val(result.tanggalFaktur);
                $('#mod_scan_faktur').val(result.nomorFaktur);
                $('#mod_jumlah_dpp').val(formatRupiahAngka(result.jumlahDpp));
                $('#mod_jumlah_ppn').val(formatRupiahAngka(result.jumlahPpn));
                $('#mod_jumlah_ppn_bm').val(formatRupiahAngka(result.jumlahPpnBm));
                $('#mod_status_approval').val(result.statusApproval);
                $('#mod_status_faktur').val(result.statusFaktur);
                $('#mod_referensi').val(result.referensi);
                $('#mod_npwp_penjual').val(result.npwpPenjual);
                $('#mod_nama_penjual').val(result.namaPenjual);
                $('#mod_alamat_penjual').val(result.alamatPenjual);
                $('#mod_npwp_lawan_transaksi').val(result.npwpLawanTransaksi);
                $('#mod_npwp_perusahaan').val(result.npwpPerusahaan);
                $('#mod_nama_lawan_transaksi').val(result.namaLawanTransaksi);
                $('#mod_alamat_lawan_transaksi').val(result.alamatLawanTransaksi);
            }
            else {
                alert("Faktur Tidak Ditemukan");
                $('#mod_scan_faktur').val('');

            }
        })

    };
</script>

