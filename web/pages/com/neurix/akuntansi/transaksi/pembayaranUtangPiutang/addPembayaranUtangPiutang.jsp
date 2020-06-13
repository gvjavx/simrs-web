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
                                                          cssStyle="margin-top: 7px" onchange="isiKeteterangan(),getTipeMaster(),getCoaLawan(),getCoaAsal(),getTipePengajuan()"
                                                          listKey="transId" listValue="transName" headerKey="" headerValue="" cssClass="form-control" />
                                                <s:hidden id="tipeMaster" />
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
                                    </div>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Detail</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">COA Lawan</label>
                                            <div class="col-md-8">
                                                <select class="form-control" id="coa_lawan" onchange="getDisableTrans()">
                                                    <option value="" ></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">ID Divisi</label>
                                            <div class="col-md-3">
                                                <s:textfield id="divisi_id" onkeypress="$(this).css('border','')" wajib="Y"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                                <script>
                                                    $(document).ready(function() {
                                                        var functions, mapped;
                                                        $('#divisi_id').typeahead({
                                                            minLength: 1,
                                                            source: function (query, process) {
                                                                functions = [];
                                                                mapped = {};
                                                                var data = [];
                                                                dwr.engine.setAsync(false);
                                                                PositionAction.typeAheadPosition(query,function (listdata) {
                                                                    data = listdata;
                                                                });
                                                                $.each(data, function (i, item) {
                                                                    var labelItem = item.kodering + " | " + item.positionName;
                                                                    mapped[labelItem] = {
                                                                        id: item.kodering,
                                                                        nama: item.positionName
                                                                    };
                                                                    functions.push(labelItem);
                                                                });
                                                                process(functions);
                                                            },
                                                            updater: function (item) {
                                                                var selectedObj = mapped[item];
                                                                $('#nama_divisi').val(selectedObj.nama);
                                                                return selectedObj.id;
                                                            }
                                                        });
                                                    });
                                                </script>
                                            </div>
                                            <div class="col-md-5">
                                                <s:textfield id="nama_divisi" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group" id="kode_vendor_view">
                                            <label class="col-md-4" style="margin-top: 7px">Kode Vendor</label>
                                            <div class="col-md-3">
                                                <s:textfield id="kode_vendor" onkeypress="$(this).css('border','')" wajib="Y"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                                <script>
                                                    $(document).ready(function() {
                                                        var functions, mapped;
                                                        $('#kode_vendor').typeahead({
                                                            minLength: 1,
                                                            source: function (query, process) {
                                                                functions = [];
                                                                mapped = {};
                                                                var data = [];
                                                                var master = $('#tipeMaster').val();
                                                                if (master!=""){
                                                                    dwr.engine.setAsync(false);
                                                                    MasterAction.initTypeaheadMasterPembayaran(query,master,function (listdata) {
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
                                                                } else{
                                                                    alert("belum memilih tipe pembayaran");
                                                                }

                                                            },
                                                            updater: function (item) {
                                                                var selectedObj = mapped[item];
                                                                $('#nama_vendor').val(selectedObj.nama);
                                                                return selectedObj.id;
                                                            }
                                                        });
                                                    });
                                                </script>
                                            </div>
                                            <div class="col-md-5">
                                                <s:textfield id="nama_vendor" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group" id="no_nota_view">
                                            <label class="col-md-4" style="margin-top: 7px">No. Nota/Pengajuan</label>
                                            <div class="col-md-7">
                                                <s:textfield id="no_nota" wajib="Y"
                                                             cssClass="form-control" readonly="true" cssStyle="margin-top: 7px"/>
                                                <s:hidden id="rekening_id"/>
                                            </div>
                                            <div class="col-md-1">
                                                <a href="javascript:void(0)">
                                                    <img  style="margin-top: 10px" id="btnSearchNota" border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_view">
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group" id="pengajuan_detail_id_view">
                                            <label class="col-md-4" style="margin-top: 7px">Pengajuan ID</label>
                                            <div class="col-md-8">
                                                <s:textfield id="pengajuan_detail_id" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                            <script>
                                                $(document).ready(function() {
                                                    var functions, mapped;
                                                    $('#pengajuan_detail_id').typeahead({
                                                        minLength: 1,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};
                                                            var data = [];
                                                            var divisi = $('#divisi_id').val();
                                                            dwr.engine.setAsync(false);
                                                            PengajuanBiayaAction.cariPengajuanBiayaDetail(query,divisi,function (listdata) {
                                                                data = listdata;
                                                            });
                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.pengajuanBiayaDetailId + " | " + item.keperluan;
                                                                mapped[labelItem] = {
                                                                    id: item.pengajuanBiayaDetailId,
                                                                    keperluan: item.keperluan,
                                                                    tanggalRealisasi: item.stTanggalRealisasi,
                                                                    jumlah: item.stJumlah
                                                                };
                                                                functions.push(labelItem);
                                                            });
                                                            process(functions);
                                                        },
                                                        updater: function (item) {
                                                            var selectedObj = mapped[item];
                                                            $('#tanggal_realisasi').val(selectedObj.tanggalRealisasi);
                                                            $('#jumlah_pengajuan').val(selectedObj.jumlah);
                                                            $('#jumlah_pembayaran').val(selectedObj.jumlah.replace(/[,]/g,"."));
                                                            return selectedObj.id;
                                                        }
                                                    });
                                                });
                                            </script>
                                        </div>
                                        <div class="form-group" id="tanggal_realisasi_view">
                                            <label class="col-md-4" style="margin-top: 7px">Tanggal Realisasi</label>
                                            <div class="col-md-8">
                                                <s:textfield id="tanggal_realisasi" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group" id="jumlah_pengajuan_view">
                                            <label class="col-md-4" style="margin-top: 7px">Jumlah Pengajuan (RP)</label>
                                            <div class="col-md-8">
                                                <s:textfield id="jumlah_pengajuan" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Jumlah Pembayaran (RP)</label>
                                            <div class="col-md-8">
                                                <s:textfield id="jumlah_pembayaran" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" onkeyup="formatRupiah2(this)" />
                                            </div>
                                        </div>
                                        <div class="form-group" style="display: inline;">
                                            <div class="col-sm-10 col-md-offset-4" style="margin-top: 7px">
                                                <button type="button" class="btn btn-success" id="btnSaveDetailPembayaran"><i
                                                        class="fa fa-save"></i> Add
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <br>
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

    $(document).ready(function () {
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

        $('#btnSaveDetailPembayaran').click(function () {
            var kodeVendor=$('#kode_vendor').val();
            var namaVendor=$('#nama_vendor').val();
            var noNota=$('#no_nota').val();
            var rekeningId=$('#rekening_id').val();
            var pengajuanBiayaDetailId=$('#pengajuan_detail_id').val();
            if (rekeningId==""){
                rekeningId=$('#coa_lawan').val();
            }
            var jumlahPembayaran=$('#jumlah_pembayaran').val();
            var divisiId=$('#divisi_id').val();
            var divisiName=$('#nama_divisi').val();

            var statusDivisi=$('#divisi_id').attr('wajib');
            var statusVendor=$('#kode_vendor').attr('wajib');
            var statusNota=$('#no_nota').attr('wajib');

            if (statusDivisi=='Y'&& divisiId==""){
                alert("belum memilih Divisi");
            } else if (statusVendor=='Y'&& kodeVendor==""){
                alert("belum memilih Vendor");
            }else if (statusNota=='Y'&& noNota==""){
                alert("belum memilih No. Nota");
            }else{
                var tipePengajuanBiaya =$('#tipePengajuan').val();
                //jika pengajuan biasa
                if (tipePengajuanBiaya=="N"){
                    PembayaranUtangPiutangAction.saveDetailPembayaran(kodeVendor,namaVendor,noNota,jumlahPembayaran,rekeningId,divisiId,divisiName,tipePengajuanBiaya,pengajuanBiayaDetailId,function (result) {
                        if (result==""){
                            loadDetailPembayaran();
                            //dihitung totalbayarnya
                            var totalBayar = $('#bayar').val();
                            totalBayar=totalBayar.replace(/[.]/g,"");
                            var strBayar=jumlahPembayaran.replace(/[.]/g,"");
                            var intTotalBayar=0;
                            if (totalBayar!=''){
                                intTotalBayar = parseInt(totalBayar);
                            }
                            var intBayar = parseInt(strBayar);
                            totalBayar = intTotalBayar+intBayar;
                            var strTotalBayar = String(totalBayar);
                            $('#bayar').val(formatRupiahAngka(strTotalBayar));
                        } else{
                            alert(result);
                        }
                    });
                    //jika pengajuan biaya
                }else {
                    var tanggalRealisasi = $('#tanggal_realisasi').val();
                    var jumlahPengajuan = $('#jumlah_pengajuan').val();
                    var currentTime = new Date();
                    var dateParts = tanggalRealisasi.split("-");
                    var tglRealisasi = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
                    if (tanggalRealisasi!=""&&jumlahPengajuan!=""&&jumlahPembayaran!="") {
                        jumlahPengajuan=jumlahPengajuan.replace(/[,]/g,"");
                        var nilaijumlahPembayaran=jumlahPembayaran.replace(/[.]/g,"");
                        var nilaiPengajuan = parseInt(jumlahPengajuan);
                        var nilaiPembayaran = parseInt(nilaijumlahPembayaran);
                        if (nilaiPengajuan>=nilaiPembayaran&&tglRealisasi<=currentTime){
                            PembayaranUtangPiutangAction.saveDetailPembayaran(kodeVendor,namaVendor,noNota,jumlahPembayaran,rekeningId,divisiId,divisiName,tipePengajuanBiaya,pengajuanBiayaDetailId,function (result) {
                                if (result==""){
                                    loadDetailPembayaran();
                                    //dihitung totalbayarnya
                                    var totalBayar = $('#bayar').val();
                                    totalBayar=totalBayar.replace(/[.]/g,"");
                                    var strBayar=jumlahPembayaran.replace(/[.]/g,"");
                                    var intTotalBayar=0;
                                    if (totalBayar!=''){
                                        intTotalBayar = parseInt(totalBayar);
                                    }
                                    var intBayar = parseInt(strBayar);
                                    totalBayar = intTotalBayar+intBayar;
                                    var strTotalBayar = String(totalBayar);
                                    $('#bayar').val(formatRupiahAngka(strTotalBayar));
                                } else{
                                    alert(result);
                                }
                            });
                        } else{
                            var msg ="";
                            if (nilaiPengajuan<nilaiPembayaran){
                                msg+="Nilai Pengajuan tidak boleh lebih besar dari nilai pembayaran \n";
                            }
                            if (tglRealisasi>currentTime){
                                msg+="Tanggal realisasi lebih besar dari tanggal sekarang \n";
                            }
                            alert(msg);
                        }
                    }else{
                        var msg="";
                        if (tanggalRealisasi==""||jumlahPengajuan==""){
                            msg+="Masukkan ID pengajuan biaya yang valid \n";
                        }
                        if (jumlahPembayaran==""){
                            msg+="Jumlah pembayaran masih kosong \n";
                        }
                        alert(msg)
                    }
                };
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
        var keterangan ="";
        if (metodeBayar!=""){
            metodeBayar = "dengan metode bayar "+metodeBayar;
        }
        if (noSlipBank!=""){
            noSlipBank="dengan no referensi bank "+noSlipBank;
        }

        keterangan= tipeTransaksi +" "+branchName+" "+metodeBayar+" "+noSlipBank;

        $('#keterangan').val(keterangan);
    }

    function getTipeMaster() {
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        PembayaranUtangPiutangAction.getTipeMaster(tipeTransaksi,function (response) {
            $('#tipeMaster').val(response);
        })
    }

    function getTipePengajuan() {
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        PembayaranUtangPiutangAction.getTipePengajuan(tipeTransaksi,function (response) {
            $('#tipePengajuan').val(response);
            if (response=="Y"){
                $('#kode_vendor_view').hide();
                $('#no_nota_view').hide();
            }else {
                $('#pengajuan_detail_id_view').hide();
                $('#tanggal_realisasi_view').hide();
                $('#jumlah_pengajuan_view').hide();
            }
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

    function getDisableTrans() {
        $('#divisi_id').attr('readonly', true);
        $('#kode_vendor').attr('readonly', true);
        $('#no_nota').attr('readonly', true);
        $('#jumlah_pembayaran').attr('readonly', true);
        $('#btnSearchNota').hide();
        $('#divisi_id').attr('wajib', "N");
        $('#kode_vendor').attr('wajib', "N");
        $('#no_nota').attr('wajib', "N");

        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        var coaLawan = $('#coa_lawan option:selected').val();
        if (tipeTransaksi!=''&&coaLawan!=''){
            PembayaranUtangPiutangAction.getDisableTrans(tipeTransaksi,coaLawan,function (res) {
                if (res.divisiId=="Y"){
                    $('#divisi_id').attr('readonly', false);
                    $('#divisi_id').attr('wajib', "Y");
                }
                if (res.masterId=="Y"){
                    $('#kode_vendor').attr('readonly', false);
                    $('#kode_vendor').attr('wajib', "Y");
                }
                if (res.noNota=="Y"){
                    $('#btnSearchNota').show();
                    $('#no_nota').attr('wajib', "Y");
                }else{
                    $('#no_nota').attr('wajib', "N");
                }

                if (res.biaya=="Y"){
                    $('#jumlah_pembayaran').attr('readonly', false);
                }
            });
        }else{
            var msg="";
            if (tipeTransaksi==""){
                msg+="Tipe transaksi belum dipilih \n";
            }
            if (coaLawan==""){
                msg+="COA lawan belum dipilih \n";
            }
            alert(msg);
        }
    }

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

