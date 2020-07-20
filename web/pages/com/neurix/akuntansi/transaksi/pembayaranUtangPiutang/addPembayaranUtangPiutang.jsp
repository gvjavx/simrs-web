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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PembayaranUtangPiutangAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PengajuanBiayaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MasterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript'>
        function confirm() {
            var tipeTransaksi = $('#tipe_transaksi').val();
            var tanggal = $('#tanggal').val();
            var metodeBayar = $('#coa_asal').val();
            var bayar      = $('#bayar').val();
            var keterangan    = $('#keterangan').val();
            var noslipBank     = $('#no_slip_bank').val();
            var branchId     = $('#branch_id').val();
            var status ="";
            PembayaranUtangPiutangAction.cekBeforeSave(tipeTransaksi,tanggal,metodeBayar,bayar,keterangan,noslipBank,branchId,function (result) {
                status=result;
            });
            if (status=="") {
                $('#confirm_dialog').dialog('open');
            } else {
                var msg="";
                if (status!=""){
                    msg+= status +"\n";
                }
                $("html, body").animate({ scrollTop: 0 }, 600);
                $('#warning_pembayaran').show().fadeOut(10000);
                $('#errorText').html(msg);
            }
        }
        $.subscribe('beforeProcessSave', function (event, data) {
            event.originalEvent.options.submit = true;
            $('#confirm_dialog').dialog('close');
            $.publish('showDialog');
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function resetField() {
            window.location.reload()
        }
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
            Pengeluaran Kas/Bank
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user-plus"></i> Input Pengeluaran Kas/Bank</h3>
                    </div>
                    <s:form id="addPembayaranUtangPiutangForm" enctype="multipart/form-data" method="post" namespace="/pembayaranUtangPiutang"
                            action="saveAdd_pembayaranUtangPiutang.action" theme="simple">
                        <s:hidden name="pembayaranUtangPiutang.tipePembayaran" value="KK" />
                        <div class="box-body">
                            <div class="alert alert-danger alert-dismissible" id="warning_pembayaran" style="display: none">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <span id="errorText"></span>
                            </div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Form Pembayaran</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Unit</label>
                                            <div class="col-md-7">
                                                <s:if test='pembayaranUtangPiutang.branchId == "KP"'>
                                                    <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                    <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branch_id"  onchange="isiKeteterangan()" name="pembayaranUtangPiutang.branchId" required="true"
                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                                                </s:if>
                                                <s:else>
                                                    <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                    <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branch_id_view" name="pembayaranUtangPiutang.branchId" required="true" disabled="true"
                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="" />

                                                    <s:hidden name="pembayaranUtangPiutang.branchId" id="branch_id" />
                                                </s:else>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Tipe Transaksi</label>
                                            <div class="col-md-7">
                                                <s:action id="comboTrans" namespace="/trans" name="initComboTransPembayaran_trans">
                                                    <s:param name="tipe">KK</s:param>
                                                </s:action>
                                                <s:select list="#comboTrans.listOfComboTrans" id="tipe_transaksi" name="pembayaranUtangPiutang.tipeTransaksi"
                                                          cssStyle="margin-top: 7px" onchange="isiKeteterangan(),getTipeMaster(),getCoaLawan(),getCoaAsal()"
                                                          listKey="transId" listValue="transName" headerKey="" headerValue="" cssClass="form-control" />
                                                <s:hidden id="tipeMaster" />
                                                <s:hidden id="keperluan" />
                                                <s:hidden id="tipePengajuan" name="pembayaranUtangPiutang.tipePengajuanBiaya" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Tanggal</label>
                                            <div class="col-md-7">
                                                <div class="input-group date" style="margin-top: 7px" id="st_tgl">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <s:textfield id="tanggal" name="pembayaranUtangPiutang.stTanggal"
                                                                 cssClass="form-control datemask" onchange="$('#st_tgl').css('border','')"/>
                                                    <script>
                                                        $("#tanggal").datepicker({
                                                            setDate: new Date(),
                                                            autoclose: true,
                                                            changeMonth: true,
                                                            changeYear:true,
                                                            dateFormat:'yy-mm-dd'
                                                        });
                                                        $("#tanggal").datepicker("setDate", new Date());
                                                    </script>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">COA Kas </label>
                                            <div class="col-md-7">
                                                <select class="form-control" id="coa_asal" onchange="isiKeteterangan()" style="margin-top: 7px" name="pembayaranUtangPiutang.metodePembayaran">
                                                    <option value="" ></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Bayar</label>
                                            <div class="col-md-7">
                                                <s:textfield id="bayar" name="pembayaranUtangPiutang.stBayar"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" readonly="true" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Keterangan</label>
                                            <div class="col-md-7">
                                                <s:textarea id="keterangan" rows="3" cssStyle="margin-top: 7px" onkeypress="$(this).css('border','')"
                                                            name="pembayaranUtangPiutang.keterangan" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">No. Referensi</label>
                                            <div class="col-md-7">
                                                <s:textfield id="no_slip_bank" name="pembayaranUtangPiutang.noSlipBank" onkeypress="$(this).css('border','')" onchange="isiKeteterangan()"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group" style="display: inline;">
                                            <div class="col-sm-10 col-md-offset-4" style="margin-top: 7px">
                                                <button type="button" class="btn btn-primary" id="btnAddDetailPembayaran"><i
                                                        class="fa fa-plus"></i> Tambah
                                                </button>
                                                <script>
                                                    $('#btnAddDetailPembayaran').click(function () {
                                                        var tipeMaster = $('#tipeMaster').val();
                                                        if (tipeMaster=="pengajuan_biaya") {
                                                            $('.modal_pengajuan').val('');
                                                            $('#modal-add-pengajuan').modal('show');
                                                        }else{
                                                            alert("tipe transaksi masih kosong atau belum ada tipe master pada transaksi")
                                                        }
                                                    })
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> List Pembayaran</h3>
                            </div>
                            <br>
                            <center>
                                <table id="showdata1" width="80%">
                                    <tr>
                                        <td align="center">
                                            <table style="width: 100%;"
                                                   class="detailPembayaranTable table table-bordered">
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                            <br>
                            <br>
                            <div class="box-header with-border"></div>
                            <div class="box-body">
                                    <%--<div class="row">--%>
                                    <%--<div class="col-md-6">--%>
                                <div class="form-group" style="display: inline;">
                                        <%--<div class="col-sm-10 col-md-offset-4" style="margin-top: 7px">--%>
                                    <button type="button" class="btn btn-success" onclick="confirm()"><i
                                            class="fa fa-arrow-right"></i> Save
                                    </button>
                                    <button type="button" class="btn btn-danger" onclick="resetField()">
                                        <i class="fa fa-refresh"></i> Reset
                                    </button>
                                    <a type="button" class="btn btn-warning" href="initForm_pembayaranUtangPiutang.action">
                                        <i class="fa fa-arrow-left"></i> Back
                                    </a>
                                </div>
                                <div style="display: none">
                                    <sj:dialog id="confirm_dialog" modal="true" resizable="false" closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Confirmation Dialog">
                                        <center><img border="0" style="height: 40px; width: 40px"
                                                     src="<s:url value="/pages/images/icon_warning.ico"/>"
                                                     name="icon_success">
                                            Do you want to save this record?
                                        </center>
                                        <br>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-warning"
                                                    onclick="$('#confirm_dialog').dialog('close')"><i
                                                    class="fa fa-times"></i> No
                                            </button>
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                                                       formIds="addPembayaranUtangPiutangForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave"
                                                       onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                                <i class="fa fa-arrow-right"></i>
                                                yes
                                            </sj:submit>
                                        </div>
                                    </sj:dialog>
                                    <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog"
                                               modal="true"
                                               resizable="false"
                                               height="250" width="600" autoOpen="false" title="Saving ...">
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

                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.href = 'initForm_pembayaranUtangPiutang.action';
                                                                                     }
                                                                            }"
                                    >
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                        Record has been saved successfully.
                                    </sj:dialog>

                                    <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                               height="250" width="600" autoOpen="false" title="Error Dialog"
                                               buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close'); }
                                                                            }"
                                    >
                                        <div class="alert alert-danger alert-dismissible">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                            </label>
                                        </div>
                                    </sj:dialog>
                                </div>
                            </div>
                        </div>
                    </s:form>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-search-nota">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Daftar Nota</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabelDaftarNota">
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnAddCheckedNota" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Add Checked
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
                            <div class="form-group" id="pengajuan_detail_id_view">
                                <label class="col-md-4" style="margin-top: 7px">Pengajuan ID</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_pengajuan_detail_id" onkeypress="$(this).css('border','')"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" />
                                </div>
                                <script>
                                    $(document).ready(function() {
                                        var functions, mapped;
                                        $('#mod_pengajuan_detail_id').typeahead({
                                            minLength: 1,
                                            source: function (query, process) {
                                                functions = [];
                                                mapped = {};
                                                var data = [];
                                                dwr.engine.setAsync(false);
                                                PengajuanBiayaAction.cariPengajuanBiayaDetail(query,function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    var labelItem = item.pengajuanBiayaDetailId + " | " + item.keperluan;
                                                    mapped[labelItem] = {
                                                        id: item.pengajuanBiayaDetailId,
                                                        keperluan: item.keperluan,
                                                        tanggalRealisasi: item.stTanggalRealisasi,
                                                        noBudgeting: item.noBudgeting,
                                                        divisiId: item.divisiId,
                                                        divisiName: item.divisiName,
                                                        coaDivisi: item.coaDivisi,
                                                        coaLawan: item.coa,
                                                        coaLawanName: item.coaName,
                                                        noKontrak: item.noKontrak,
                                                        namaKontrak: item.namaKontrak,
                                                        jumlah: item.stJumlah
                                                    };
                                                    functions.push(labelItem);
                                                });
                                                process(functions);
                                            },
                                            updater: function (item) {
                                                var selectedObj = mapped[item];
                                                $('#mod_tanggal_realisasi').val(selectedObj.tanggalRealisasi);
                                                $('#mod_jumlah_pengajuan').val(selectedObj.jumlah);
                                                $('#mod_no_budgetting').val(selectedObj.noBudgeting);
                                                $('#mod_keperluan').val(selectedObj.keperluan);
                                                $('#mod_no_kontrak').val(selectedObj.noKontrak);
                                                $('#mod_nama_kontrak').val(selectedObj.namaKontrak);
                                                $('#mod_divisi_id').val(selectedObj.coaDivisi);
                                                $('#mod_nama_divisi').val(selectedObj.divisiName);
                                                var option = '<option value="'+selectedObj.coaLawan+'">'+selectedObj.coaLawanName+'</option>';
                                                $('#mod_coa_lawan').html(option);
                                                $('#mod_jumlah_pembayaran').val(selectedObj.jumlah.replace(/[,]/g,"."));
                                                isiKeteterangan();
                                                return selectedObj.id;
                                            }
                                        });
                                    });
                                </script>
                            </div>
                            <s:hidden id="mod_keperluan" />
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">COA Lawan</label>
                                <div class="col-md-8">
                                    <select class="form-control modal_pengajuan" id="mod_coa_lawan" onchange="getDisableTrans()" style="margin-top: 7px">
                                        <option value="" ></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">ID Divisi</label>
                                <div class="col-md-3">
                                    <s:textfield id="mod_divisi_id" onkeypress="$(this).css('border','')" wajib="Y"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" readonly="true" />
                                </div>
                                <div class="col-md-5">
                                    <s:textfield id="mod_nama_divisi" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" />
                                </div>
                            </div>
                            <div class="form-group" id="tanggal_realisasi_view">
                                <label class="col-md-4" style="margin-top: 7px">Tanggal Realisasi</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_tanggal_realisasi" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" />
                                </div>
                            </div>
                            <div class="form-group" id="no_budgetting_view">
                                <label class="col-md-4" style="margin-top: 7px">No. Budgeting</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_no_budgetting" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" />
                                </div>
                            </div>
                            <div class="form-group" id="no_kontrak_view">
                                <label class="col-md-4" style="margin-top: 7px">No. Kontrak</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_no_kontrak" readonly="true" cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" />
                                </div>
                            </div>
                            <div class="form-group" id="nama_kontrak_view">
                                <label class="col-md-4" style="margin-top: 7px">Nama Kontrak</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_nama_kontrak" readonly="true" cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" />
                                </div>
                            </div>
                            <div class="form-group" id="jumlah_pengajuan_view">
                                <label class="col-md-4" style="margin-top: 7px">Jumlah Pengajuan (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_jumlah_pengajuan" readonly="true" cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Jumlah Pembayaran (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_jumlah_pembayaran" onkeypress="$(this).css('border','')"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" onkeyup="formatRupiah2(this)" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">PPH (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_total_pph" cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px;" onkeyup="formatRupiah2(this)" placeholder="0"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">PPN (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_total_ppn" cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" placeholder="0" onkeyup="formatRupiah2(this)" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">No. Faktur Pajak</label>
                                <div class="col-md-7">
                                    <s:textfield id="mod_no_faktur" cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px;" readonly="true" />
                                </div>
                                <div class="col-md-1">
                                    <a href="javascript:void(0)">
                                        <img  style="margin-top: 10px" id="btnScanFaktur" border="0" src="<s:url value="/pages/images/icons8-qr-code-25.png"/>" name="icon_scan_faktur">
                                    </a>
                                </div>
                                <script>
                                    $('#btnScanFaktur').click(function () {
                                        $('.mod_scan_faktur').val('');
                                        $('#no_faktur_view').text("Scan QR disini");
                                        $("#mod_scan_faktur").prop('readonly', false);
                                        $('#modal-scan-faktur').modal('show');
                                    })
                                </script>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Upload Faktur Pajak</label>
                                <div class="col-md-8">
                                    <div class="input-group" id="img_file"  style="margin-top: 7px">
                              <span class="input-group-btn">
                              <span class="btn btn-default btn-file">
                                   Browse… <s:file id="imgInp" accept=".jpg" name="fileUpload"
                                                   onchange="$('#img_file').css('border','')"></s:file>
                                                        </span>
                                                        </span>
                                            <input type="text" class="form-control" readonly id="namaFile">
                                        </div>
                                    <canvas id="img_faktur_canvas" style="display: none"></canvas>
                                </div>
                            </div>
                            <br>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="mod_btnSaveDetailPembayaran" type="button" class="btn btn-default btn-success"><i class="fa fa-plus"></i> Add</a>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
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
                                <s:textfield id="mod_scan_faktur" onkeypress="$(this).css('border','')" onchange="generateNoFaktur(this.value)"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tanggal Faktur</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_tgl_faktur" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jumlah DPP</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_jumlah_dpp" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jumlah PPN</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_jumlah_ppn" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Status Approval</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_status_approval" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Status Faktur</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_status_faktur" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Referensi</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_referensi" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="btnAddNoFaktur" type="button" class="btn btn-default btn-success"><i class="fa fa-plus"></i> Add</a>
                <script>
                    $('#btnAddNoFaktur').click(function () {
                        var stTanggalFaktur = $('#mod_tgl_faktur').val();
                        var statusFaktur = $('#mod_status_faktur').val();
                        var currentDate = new Date();
                        currentDate.setMonth(currentDate.getMonth()-3);
                        var dateParts = stTanggalFaktur.split("/");
                        var tanggalFaktur = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
                        if (statusFaktur!=""){
                            if (tanggalFaktur < currentDate){
                                alert("Tanggal Faktur sudah tidak valid , silahkan membuat faktur baru");
                                $('.mod_scan_faktur').val('');
                                $('#no_faktur_view').text("Scan QR disini");
                                $("#mod_scan_faktur").prop('readonly', false);
                            }{
                                alert("Berhasil menambahkan No. Faktur");
                                $('#mod_no_faktur').val($('#mod_scan_faktur').val());
                                $('#modal-scan-faktur').modal('hide');
                            }
                        } else{
                            alert("QR atau faktur tidak valid ");
                            $('.mod_scan_faktur').val('');
                            $('#no_faktur_view').text("Scan QR disini");
                            $("#mod_scan_faktur").prop('readonly', false);
                        }
                    })
                </script>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->
<script type='text/javascript'>
    function selectPembayaran(){
        var option = '<option value=""></option>';
        KasirRawatJalanAction.getListPembayaran(function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.coa+'">'+item.pembayaranName+'</option>';
                });
                $('#bank').html(option);
            }else{
                $('#bank').html(option);
            }
        });
    }

    function generateNoFaktur(val){
        PembayaranUtangPiutangAction.generateQrEfaktur(val,function (result) {
            $('#no_faktur_view').text("No. Faktur");
            $("#mod_scan_faktur").prop('readonly', true);
            $('#mod_tgl_faktur').val(result.tanggalFaktur);
            $('#mod_scan_faktur').val(result.nomorFaktur);
            $('#mod_jumlah_dpp').val(result.jumlahDpp);
            $('#mod_jumlah_ppn').val(result.jumlahPpn);
            $('#mod_status_approval').val(result.statusApproval);
            $('#mod_status_faktur').val(result.statusFaktur);
            $('#mod_referensi').val(result.referensi);
        })
    };

    $(document).ready(function () {

        var canvas = document.getElementById('img_faktur_canvas');
        var ctx = canvas.getContext('2d');

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


        $('#divisi_id').attr('readonly', true);
        $('#kode_vendor').attr('readonly', true);
        $('#no_nota').attr('readonly', true);
        $('#jumlah_pembayaran').attr('readonly', true);
        $('#btnSearchNota').hide();
        $('#divisi_id').attr('wajib', "N");
        $('#kode_vendor').attr('wajib', "N");
        $('#no_nota').attr('wajib', "N");

        selectPembayaran();
        $('#btnSearchNota').click(function () {
            var masterId = $('#kode_vendor').val();
            var transaksiId = $('#tipe_transaksi').val();
            var branchId = $('#branch_id').val();
            var divisiId = $('#divisi_id').val();
            var coaLawan = $('#coa_lawan').val();

            $('#tabelDaftarNota').find('tbody').remove();
            $('#tabelDaftarNota').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";

            var add=true;
            if (coaLawan==""||branchId==""||transaksiId==""){
                add=false;
            }
            var statusDivisi=$('#divisi_id').attr('wajib');
            var statusVendor=$('#kode_vendor').attr('wajib');

            if (statusDivisi=='Y'&& divisiId==""){
                alert("belum memilih Divisi");
            } else if (statusVendor=='Y'&& masterId==""){
                alert("belum memilih Vendor");
            } else {
                if (add){
                    PembayaranUtangPiutangAction.searchNotaPembayaran(masterId,transaksiId,branchId,divisiId,coaLawan,function (listdata) {
                        tmp_table = "<thead style='font-size: 14px' ><tr class='active'>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>No</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196'><input type='checkbox' id='checkAll'></th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Kode Vendor</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Rekening ID</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>No. Nota</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Debit</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Add</th>" +
                            "</tr></thead>";
                        var i = i;
                        $.each(listdata, function (i, item) {
                            var combo = '<input type="checkbox" checked id="check_'+i+'">';
                            tmp_table += '<tr style="font-size: 12px;" ">' +
                                '<td align="center">' + (i + 1) + '</td>' +
                                '<td align="center">' + combo + '</td>' +
                                '<td align="center">' + item.masterId + '</td>' +
                                '<td align="center">' + item.rekeningId + '</td>' +
                                '<td align="center">' + item.noNota + '</td>' +
                                '<td align="center">' + item.stJumlahPembayaran + '</td>' +
                                '<td align="center">' +
                                "<a href='javascript:;' class ='item-add-data' data ='" + item.noNota + "' bayar ='" + item.stJumlahPembayaran + "' rekeningId ='" + item.rekeningId + "'>" +
                                "<img border='0' src='<s:url value='/pages/images/add_task1.png'/>' name='icon_add'>" +
                                '</a>' +
                                '</td>' +
                                "</tr>";
                        });
                        $('#tabelDaftarNota').append(tmp_table);
                        $("#checkAll").change(function(){
                            $('input:checkbox').not(this).prop('checked', this.checked);
                        });
                    });
                    $("#modal-search-nota").modal('show');
                } else{
                    var msg="";
                    if (transaksiId==""){
                        msg+="Tipe Transaksi belum dipilih \n";
                    }
                    if (branchId==""){
                        msg+="Unit belum dipilih \n";
                    }
                    if (coaLawan==""){
                        msg+="Coa lawan belum dipilih \n";
                    }
                    alert(msg);
                }
            }
        });
        $('#tabelDaftarNota').on('click', '.item-add-data', function () {
            var noNota = $(this).attr('data');
            var bayar = $(this).attr('bayar');
            var rekeningId = $(this).attr('rekeningId');
            $('#no_nota').val(noNota);
            $('#jumlah_pembayaran').val(bayar);
            $('#rekening_id').val(rekeningId);

            $("#modal-search-nota").modal('hide');

        });

        $('#btnAddCheckedNota').click(function () {
            var data = $('#tabelDaftarNota').tableToJSON();
            var kodeVendor=$('#kode_vendor').val();
            var namaVendor=$('#nama_vendor').val();
            var divisiId=$('#divisi_id').val();
            var divisiName=$('#nama_divisi').val();
            var error="";
            $.each(data, function (i, item) {
                var noNota = data[i]["No. Nota"];
                var bayar = data[i]["Debit"];
                var rekeningId = data[i]["Rekening ID"];
                if ($('#check_' + i).prop("checked") == true) {
                    if (kodeVendor!=''&&noNota!=''&&namaVendor!=''){
                        PembayaranUtangPiutangAction.saveDetailPembayaran(kodeVendor,namaVendor,noNota,bayar,rekeningId,divisiId,divisiName,function (result) {
                            if (result==""){
                                loadDetailPembayaran();
                                //dihitung totalbayarnya
                                var totalBayar = $('#bayar').val();
                                totalBayar=totalBayar.replace(/[.]/g,"");
                                var strBayar=bayar.replace(/[.]/g,"");
                                var intTotalBayar=0;
                                if (totalBayar!=''){
                                    intTotalBayar = parseInt(totalBayar);
                                }
                                var intBayar = parseInt(strBayar);
                                totalBayar = intTotalBayar+intBayar;
                                var strTotalBayar = String(totalBayar);
                                $('#bayar').val(formatRupiahAngka(strTotalBayar));
                            } else{
                                error=result;
                            }
                        });
                    } else{
                        var msg="";
                        if (kodeVendor==""){
                            msg+="Kode vendor tidak boleh kosong \n";
                        }if (noNota==""){
                            msg+="No nota tidak boleh kosong \n";
                        }if (jumlahPembayaran==""){
                            msg+="Jumlah pembayaran tidak boleh kosong \n";
                        }if (namaVendor==""){
                            msg+="Nama vendor tidak ditemukan, coba lagi \n";
                        }
                        error=msg;
                    }
                }
            });
            if (error!=""){
                alert(error);
            }
        });

        $('#mod_btnSaveDetailPembayaran').click(function () {
            $('#tipePengajuan').val("Y");
            var tanggalRealisasi = $('#mod_tanggal_realisasi').val();
            var jumlahPengajuan = $('#mod_jumlah_pengajuan').val();
            var jumlahPembayaran = $('#mod_jumlah_pembayaran').val();
            var jumlah_ppn = $('#mod_total_ppn').val();
            var jumlah_pph = $('#mod_total_pph').val();
            var rekeningId = $('#mod_coa_lawan').val();
            var divisiId = $('#mod_divisi_id').val();
            var divisiName = $('#mod_nama_divisi').val();
            var tipePengajuanBiaya = $('#tipePengajuan').val();
            var noFakturPajak = $('#mod_no_faktur').val();
            var pengajuanBiayaDetailId = $('#mod_pengajuan_detail_id').val();
            var noBudgeting = $('#mod_no_budgetting').val();
            var currentTime = new Date();
            var dateParts = tanggalRealisasi.split("-");
            var tglRealisasi = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
            if (jumlah_pph==""){jumlah_pph="0";}
            if (jumlah_ppn==""){jumlah_ppn="0";}
            var canvas = document.getElementById('img_faktur_canvas');
            var cekCanvas = $('#namaFile').val();
            var dataURL = canvas.toDataURL("image/png"),
                dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
            console.log(cekCanvas);

            if (tanggalRealisasi!=""&&jumlahPengajuan!=""&&jumlahPembayaran!=""&&cekCanvas!=""&&noFakturPajak!="") {
                jumlahPengajuan = jumlahPengajuan.replace(/[,]/g, "");
                var nilaijumlahPembayaran = jumlahPembayaran.replace(/[.]/g, "");
                var nilaiPengajuan = parseInt(jumlahPengajuan);
                var nilaiPembayaran = parseInt(nilaijumlahPembayaran);
                var nilaiPpn = parseInt(jumlah_ppn.replace(/[,]/g, ""));
                var nilaiPph = parseInt(jumlah_pph.replace(/[,]/g, ""));
                if (nilaiPengajuan-(nilaiPembayaran+nilaiPph+nilaiPpn)<0){
                    alert("jumlah Pembayaran + PPN + PPH lebih dari jumlah pengajuan");
                }else if (nilaiPengajuan >= nilaiPembayaran && tglRealisasi <= currentTime) {
                    PembayaranUtangPiutangAction.saveDetailPembayaran("", "", "", jumlahPembayaran, rekeningId, divisiId,
                        divisiName, tipePengajuanBiaya, pengajuanBiayaDetailId, noBudgeting,jumlah_ppn,
                        jumlah_pph,noFakturPajak,dataURL, function (result) {
                        if (result == "") {
                            loadDetailPembayaran();
                            //dihitung totalbayarnya
                            var totalBayar = $('#bayar').val();
                            totalBayar = totalBayar.replace(/[.]/g, "");
                            var strBayar = jumlahPembayaran.replace(/[.]/g, "");
                            var intTotalBayar = 0;
                            if (totalBayar != '') {
                                intTotalBayar = parseInt(totalBayar);
                            }
                            var intBayar = parseInt(strBayar);
                            totalBayar = intTotalBayar + intBayar;
                            var strTotalBayar = String(totalBayar);
                            $('#bayar').val(formatRupiahAngka(strTotalBayar));
                            $('#keperluan').val($('#mod_nama_kontrak').val());
                            isiKeteterangan();
                            $('#modal-add-pengajuan').modal('hide');
                        } else {
                            alert(result);
                        }
                    });
                } else {
                    var msg = "";
                    if (nilaiPengajuan < nilaiPembayaran) {
                        msg += "Nilai Pengajuan tidak boleh lebih besar dari nilai pembayaran \n";
                    }
                    if (tglRealisasi > currentTime) {
                        msg += "Tanggal realisasi lebih besar dari tanggal sekarang \n";
                    }
                    if (tglRealisasi > currentTime) {
                        msg += "Tanggal realisasi lebih besar dari tanggal sekarang \n";
                    }
                    alert(msg);
                }
            }else{
                var msg = "";
                if (tanggalRealisasi=="") {
                    msg += "Tanggal Realisasi Masih Kosong \n";
                }
                if (jumlahPengajuan=="") {
                    msg += "Jumlah Pengajuan Masih Kosong \n";
                }
                if (jumlahPembayaran=="") {
                    msg += "Jumlah Pembayaran Masih Kosong \n";
                }
                if (noFakturPajak=="") {
                    msg += "No. Faktur Pajak Masih Kosong \n";
                }
                if (cekCanvas=="") {
                    msg += "Belum Upload Faktur Pajak \n";
                }
                alert(msg);
            }
        });
        $('.detailPembayaranTable').on('click', '.item-delete-data', function () {
            var id = $(this).attr('data');
            var biaya = $(this).attr('biaya');
            if (id!=''){
                PembayaranUtangPiutangAction.deleteDetailPembayaran(id,function (result) {
                    alert("data berhasil dihapus");
                    loadDetailPembayaran();
                    var totalBayar = $('#bayar').val();
                    totalBayar=totalBayar.replace(/[.]/g,"");
                    var strBayar=biaya.replace(/[.]/g,"");
                    var intTotalBayar=0;
                    if (totalBayar!=''){
                        intTotalBayar = parseInt(totalBayar);
                    }
                    var intBayar = parseInt(strBayar);
                    totalBayar = intTotalBayar-intBayar;
                    var strTotalBayar = String(totalBayar);
                    $('#bayar').val(formatRupiahAngka(strTotalBayar));
                });
            } else{
                var msg="";
                if (id==""){
                    msg+="Kode vendor tidak ditemukan \n";
                }
                alert(msg);
            }
        });
        window.loadDetailPembayaran = function () {
            $('.detailPembayaranTable').find('tbody').remove();
            $('.detailPembayaranTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            PembayaranUtangPiutangAction.searchDetailPembayaran(function (listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>No</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Rekening ID</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Divisi ID</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Nama Divisi</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Kode Vendor</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Nama Vendor</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>No. Nota</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Jumlah Pembayaran</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Delete</th>" +
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.rekeningId + '</td>' +
                        '<td align="center">' + item.divisiId + '</td>' +
                        '<td align="center">' + item.divisiName + '</td>' +
                        '<td align="center">' + item.masterId + '</td>' +
                        '<td align="center">' + item.masterName + '</td>' +
                        '<td align="center">' + item.noNota + '</td>' +
                        '<td align="center">' + item.stJumlahPembayaran+ '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete-data' data ='" + item.noNota + "' biaya ='" + item.stJumlahPembayaran + "'>" +
                        "<img border='0' src='<s:url value='/pages/images/delete_task.png'/>' name='icon_delete'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                $('.detailPembayaranTable').append(tmp_table);
            });
        };
    });

    function isiKeteterangan() {
        var tipeTransaksi = $('#tipe_transaksi option:selected').text();
        var metodeBayar = $('#coa_asal option:selected').text();
        var branchName = $('#branch_id option:selected').text();
        var noSlipBank = $('#no_slip_bank').val();
        var keperluan = $('#keperluan').val();
        var keterangan ="";

        if (keperluan!=""){
            keperluan = "untuk keperluan "+keperluan;
        }
        if (metodeBayar!=""){
            metodeBayar = "dengan metode bayar "+metodeBayar;
        }
        if (noSlipBank!=""){
            noSlipBank="dengan no referensi bank "+noSlipBank;
        }

        keterangan= tipeTransaksi +" "+branchName+" "+metodeBayar+" "+noSlipBank+" "+keperluan;

        $('#keterangan').val(keterangan);
    }

    function getTipeMaster() {
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        PembayaranUtangPiutangAction.getTipeMaster(tipeTransaksi,function (response) {
            $('#tipeMaster').val(response);
        })
    }

    function getCoaAsal() {
        var option = '<option value=""></option>';
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        KodeRekeningAction.getKodeRekeningLawanByTransId(tipeTransaksi,"K",function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.kodeRekening+'">'+item.namaKodeRekening+'</option>';
                });
                $('#coa_asal').html(option);
            }else{
                $('#coa_asal').html(option);
            }
        });
    }

    function getCoaLawan() {
        var option = '<option value=""></option>';
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        KodeRekeningAction.getKodeRekeningLawanByTransId(tipeTransaksi,"D",function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.kodeRekening+'">'+item.tampilanCoa+'</option>';
                });
                $('#coa_lawan').html(option);
            }else{
                $('#coa_lawan').html(option);
            }
        });
    }

    // function getDisableTrans() {
    //     $('#divisi_id').attr('readonly', true);
    //     $('#kode_vendor').attr('readonly', true);
    //     $('#no_nota').attr('readonly', true);
    //     $('#jumlah_pembayaran').attr('readonly', true);
    //     $('#btnSearchNota').hide();
    //     $('#divisi_id').attr('wajib', "N");
    //     $('#kode_vendor').attr('wajib', "N");
    //     $('#no_nota').attr('wajib', "N");
    //
    //     var tipeTransaksi = $('#tipe_transaksi option:selected').val();
    //     var coaLawan = $('#coa_lawan option:selected').val();
    //     if (tipeTransaksi!=''&&coaLawan!=''){
    //         PembayaranUtangPiutangAction.getDisableTrans(tipeTransaksi,coaLawan,function (res) {
    //             if (res.divisiId=="Y"){
    //                 $('#divisi_id').attr('readonly', false);
    //                 $('#divisi_id').attr('wajib', "Y");
    //             }
    //             if (res.masterId=="Y"){
    //                 $('#kode_vendor').attr('readonly', false);
    //                 $('#kode_vendor').attr('wajib', "Y");
    //             }
    //             if (res.noNota=="Y"){
    //                 $('#btnSearchNota').show();
    //                 $('#no_nota').attr('wajib', "Y");
    //             }else{
    //                 $('#no_nota').attr('wajib', "N");
    //             }
    //
    //             if (res.biaya=="Y"){
    //                 $('#jumlah_pembayaran').attr('readonly', false);
    //             }
    //         });
    //     }else{
    //         var msg="";
    //         if (tipeTransaksi==""){
    //             msg+="Tipe transaksi belum dipilih \n";
    //         }
    //         if (coaLawan==""){
    //             msg+="COA lawan belum dipilih \n";
    //         }
    //         alert(msg);
    //     }
    // }

    function formatRupiahAngka(angka) {
        var number_string = angka.replace(/[^,\d]/g, '').toString(),
            split = number_string.split(','),
            sisa = split[0].length % 3,
            rupiah = split[0].substr(0, sisa),
            ribuan = split[0].substr(sisa).match(/\d{3}/gi);

        if (ribuan) {
            separator = sisa ? '.' : '';
            rupiah += separator + ribuan.join('.');
        }

        rupiah = split[1] != undefined ? rupiah + ',' + split[1] : rupiah;
        return rupiah;
    }


</script>
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

