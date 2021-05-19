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
    <script type='text/javascript' src='<s:url value="/dwr/interface/MasterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript'>
        function confirm() {

            var tipeTransaksi = $('#tipe_transaksi').val();
            var tanggal  = $('#tanggal').val();
            var keterangan  = $('#keterangan').val();
            var branchId = $('#branch_id').val();
            var status ="";
            KasAction.cekBeforeSaveKoreksi(tipeTransaksi,tanggal,keterangan,branchId,function (result) {
                status=result;
            })

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
            Koreksi / Penyesuaian
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user-plus"></i> Input Koreksi / Penyesuaian</h3>
                    </div>
                    <s:form id="addPembayaranUtangPiutangForm" enctype="multipart/form-data" method="post" namespace="/kas"
                            action="saveAddKoreksi_kas.action" theme="simple">
                        <s:hidden name="kas.tipePembayaran" value="KR" />
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
                                            <div class="col-md-8">
                                                <s:if test='kas.branchId == "01"'>
                                                    <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                    <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branch_id"  onchange="isiKeteterangan()" name="kas.branchId" required="true"
                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                                                </s:if>
                                                <s:else>
                                                    <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                    <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branch_id_view" name="kas.branchId" required="true" disabled="true"
                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="" />

                                                    <s:hidden name="kas.branchId" id="branch_id" />
                                                </s:else>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Tipe Transaksi</label>
                                            <div class="col-md-8">
                                                <s:action id="comboTrans" namespace="/trans" name="initComboTransaksi_trans">
                                                    <s:param name="tipe">JKR</s:param>
                                                </s:action>
                                                <s:select list="#comboTrans.listOfComboTrans" id="tipe_transaksi" name="kas.tipeTransaksi"
                                                          cssStyle="margin-top: 7px" onchange="isiKeteterangan(),getTipeMaster(),getCoaLawan()"
                                                          listKey="transId" listValue="transName" headerKey="" headerValue="" cssClass="form-control" />
                                                <s:hidden id="tipeMaster" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Tanggal</label>
                                            <div class="col-md-8">
                                                <div class="input-group date" style="margin-top: 7px" id="st_tgl">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <s:textfield id="tanggal" name="kas.stTanggal"
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
                                            <label class="col-md-4" style="margin-top: 7px">Keterangan</label>
                                            <div class="col-md-8">
                                                <s:textarea id="keterangan" rows="3" cssStyle="margin-top: 7px" onkeypress="$(this).css('border','')"
                                                            name="kas.keterangan" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-offset-3">
                                    <button type="button" class="btn btn-warning" id="btnLampiran"><i
                                            class="fa fa-file"></i> Lampiran
                                    </button>
                                    <script>
                                        $('#btnLampiran').click(function () {
                                            $('#modal-lampiran').modal('show');
                                        })
                                    </script>
                                </div>
                            </div>

                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Form COA Debit</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">COA</label>
                                            <div class="col-md-8">
                                                <select class="form-control" id="coa_debit" onchange="getDisableTransDebit()">
                                                    <option value="" ></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">ID Divisi</label>
                                            <div class="col-md-3">
                                                <s:textfield id="divisi_id_debit" onkeypress="$(this).css('border','')" wajib="Y"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                                <script>
                                                    $(document).ready(function() {
                                                        var functions, mapped;
                                                        $('#divisi_id_debit').typeahead({
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
                                                                $('#nama_divisi_debit').val(selectedObj.nama);
                                                                return selectedObj.id;
                                                            }
                                                        });
                                                    });
                                                </script>
                                            </div>
                                            <div class="col-md-5">
                                                <s:textfield id="nama_divisi_debit" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Kode Vendor</label>
                                            <div class="col-md-3">
                                                <s:textfield id="kode_vendor_debit" onkeypress="$(this).css('border','')" wajib="Y"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                                <script>
                                                    $(document).ready(function() {
                                                        var functions, mapped;
                                                        $('#kode_vendor_debit').typeahead({
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
                                                                $('#nama_vendor_debit').val(selectedObj.nama);
                                                                return selectedObj.id;
                                                            }
                                                        });
                                                    });
                                                </script>
                                            </div>
                                            <div class="col-md-5">
                                                <s:textfield id="nama_vendor_debit" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">No. Nota</label>
                                            <div class="col-md-7">
                                                <s:textfield id="no_nota_debit" wajib="Y"
                                                             cssClass="form-control" readonly="true" cssStyle="margin-top: 7px"/>
                                                <s:hidden id="rekening_id_debit"/>
                                            </div>
                                            <div class="col-md-1">
                                                <a href="javascript:void(0)">
                                                    <img  style="margin-top: 10px" id="btnSearchNotaDebit" border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_view">
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Jumlah Pembayaran</label>
                                            <div class="col-md-8">
                                                <s:textfield id="jumlah_pembayaran_debit" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" onkeyup="formatRupiah2(this)" />
                                            </div>
                                        </div>
                                        <div class="form-group" style="display: inline;">
                                            <div class="col-sm-10 col-md-offset-4" style="margin-top: 7px">
                                                <button type="button" class="btn btn-success" id="btnSaveDetailPembayaranDebit"><i
                                                        class="fa fa-save"></i> Add
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Form COA Kredit</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">COA</label>
                                            <div class="col-md-8">
                                                <select class="form-control" id="coa_kredit" onchange="getDisableTransKredit()">
                                                    <option value="" ></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">ID Divisi</label>
                                            <div class="col-md-3">
                                                <s:textfield id="divisi_id_kredit" onkeypress="$(this).css('border','')" wajib="Y"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                                <script>
                                                    $(document).ready(function() {
                                                        var functions, mapped;
                                                        $('#divisi_id_kredit').typeahead({
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
                                                                $('#nama_divisi_kredit').val(selectedObj.nama);
                                                                return selectedObj.id;
                                                            }
                                                        });
                                                    });
                                                </script>
                                            </div>
                                            <div class="col-md-5">
                                                <s:textfield id="nama_divisi_kredit" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Kode Vendor</label>
                                            <div class="col-md-3">
                                                <s:textfield id="kode_vendor_kredit" onkeypress="$(this).css('border','')" wajib="Y"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                                <script>
                                                    $(document).ready(function() {
                                                        var functions, mapped;
                                                        $('#kode_vendor_kredit').typeahead({
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
                                                                $('#nama_vendor_kredit').val(selectedObj.nama);
                                                                return selectedObj.id;
                                                            }
                                                        });
                                                    });
                                                </script>
                                            </div>
                                            <div class="col-md-5">
                                                <s:textfield id="nama_vendor_kredit" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">No. Nota</label>
                                            <div class="col-md-7">
                                                <s:textfield id="no_nota_kredit" wajib="Y"
                                                             cssClass="form-control" readonly="true" cssStyle="margin-top: 7px"/>
                                                <s:hidden id="rekening_id"/>
                                            </div>
                                            <div class="col-md-1">
                                                <a href="javascript:void(0)">
                                                    <img  style="margin-top: 10px" id="btnSearchNotaKredit" border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_view">
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Jumlah Pembayaran</label>
                                            <div class="col-md-8">
                                                <s:textfield id="jumlah_pembayaran_kredit" onkeypress="$(this).css('border','')" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" onkeyup="formatRupiah2(this)" />
                                            </div>
                                        </div>
                                        <div class="form-group" style="display: inline;">
                                            <div class="col-sm-10 col-md-offset-4" style="margin-top: 7px">
                                                <button type="button" class="btn btn-success" id="btnSaveDetailPembayaranKredit"><i
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
                                <table id="showdata1" width="100%">
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
                                    <a type="button" class="btn btn-warning" href="initFormKoreksi_kas.action">
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
                                                                                         window.location.href = 'initFormKoreksi_kas.action';
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

<div class="modal fade" id="modal-search-nota-debit">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Daftar Nota</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabelDaftarNotaDebit">
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnAddCheckedNotaDebit" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Add Checked
                </button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-search-nota-kredit">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Daftar Nota</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabelDaftarNotaKredit">
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnAddCheckedNotaKredit" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Add Checked
                </button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
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
                    <div class="row">
                        <label class="control-label col-sm-4">Nama Lampiran </label>
                        <div class="col-sm-8">
                            <s:textfield id="mod_nama_lampiran" onkeypress="$(this).css('border','')" cssClass="form-control modal_lampiran"/>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 7px">
                        <label class="control-label col-sm-4">Lampiran (PDF/JPEG/PNG) </label>
                        <div class="col-sm-8">
                            <div class="input-group" id="img_file2"  style="margin-top: 7px">
                                          <span class="input-group-btn">
                                            <span class="btn btn-default btn-file btn-file-2">
                                               Browse… <s:file id="imgInp2" accept=".jpg" name="fileUpload2"
                                                               onchange="$('#img_file2').css('border','')"></s:file>
                                            </span>
                                            </span>
                                <input type="text" class="form-control" readonly id="namaFile2">
                            </div>
                            <canvas id="img_faktur_canvas2" style="display: none"></canvas>
                        </div>
                    </div>
                    <br>
                    <div class="row" style="margin-top: 7px">
                        <center>
                            <a id="btnAddLampiran" type="button" class="btn btn-default btn-success"><i class="fa fa-plus"></i> Tambah</a>
                        </center>
                    </div>
                    <br>
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

<div id="modal-view-lampiran" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">View Lampiran</h4>
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

<!-- /.content-wrapper -->
<script type='text/javascript'>
    $(document).ready(function () {
        $('#btnSearchNotaDebit').click(function () {
            var masterId = $('#kode_vendor_debit').val();
            var transaksiId = $('#tipe_transaksi').val();
            var branchId = $('#branch_id').val();
            var divisiId = $('#divisi_id_debit').val();
            var coaLawan = $('#coa_debit').val();

            $('#tabelDaftarNotaDebit').find('tbody').remove();
            $('#tabelDaftarNotaDebit').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";

            var add=true;
            if (coaLawan==""||branchId==""||transaksiId==""){
                add=false;
            }
            var statusDivisi=$('#divisi_id_debit').attr('wajib');
            var statusVendor=$('#kode_vendor_debit').attr('wajib');

            if (statusDivisi=='Y'&& divisiId==""){
                alert("belum memilih Divisi");
            } else if (statusVendor=='Y'&& masterId==""){
                alert("belum memilih Vendor");
            } else {
                if (add){
                    KasAction.searchNotaPembayaran(masterId,transaksiId,branchId,divisiId,coaLawan,function (listdata) {
                        tmp_table = "<thead style='font-size: 14px' ><tr class='active'>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>No</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196'><input type='checkbox' id='checkAllDebit'></th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Kode Vendor</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Divisi ID</th>" +
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
                                '<td align="center">' + item.divisiId + '</td>' +
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
                        $('#tabelDaftarNotaDebit').append(tmp_table);
                        $("#checkAllDebit").change(function(){
                            $('input:checkbox').not(this).prop('checked', this.checked);
                        });
                    });
                    $("#modal-search-nota-debit").modal('show');
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

        $('#btnSearchNotaKredit').click(function () {
            var masterId = $('#kode_vendor_kredit').val();
            var transaksiId = $('#tipe_transaksi').val();
            var branchId = $('#branch_id').val();
            var divisiId = $('#divisi_id_kredit').val();
            var coaLawan = $('#coa_kredit').val();

            $('#tabelDaftarNotaKredit').find('tbody').remove();
            $('#tabelDaftarNotaKredit').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";

            var add=true;
            if (coaLawan==""||branchId==""||transaksiId==""){
                add=false;
            }
            var statusDivisi=$('#divisi_id_kredit').attr('wajib');
            var statusVendor=$('#kode_vendor_kredit').attr('wajib');

            if (statusDivisi=='Y'&& divisiId==""){
                alert("belum memilih Divisi");
            } else if (statusVendor=='Y'&& masterId==""){
                alert("belum memilih Vendor");
            } else {
                if (add){
                    KasAction.searchNotaPembayaran(masterId,transaksiId,branchId,divisiId,coaLawan,function (listdata) {
                        tmp_table = "<thead style='font-size: 14px' ><tr class='active'>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>No</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196'><input type='checkbox' id='checkAllKredit'></th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Kode Vendor</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Divisi ID</th>" +
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
                                '<td align="center">' + item.divisiId + '</td>' +
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
                        $('#tabelDaftarNotaKredit').append(tmp_table);
                        $("#checkAllKredit").change(function(){
                            $('input:checkbox').not(this).prop('checked', this.checked);
                        });
                    });
                    $("#modal-search-nota-kredit").modal('show');
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


        $('#tabelDaftarNotaDebit').on('click', '.item-add-data', function () {
            var noNota = $(this).attr('data');
            var bayar = $(this).attr('bayar');
            var rekeningId = $(this).attr('rekeningId');
            $('#no_nota_debit').val(noNota);
            $('#jumlah_pembayaran_debit').val(bayar);
            $('#rekening_id_debit').val(rekeningId);

            $("#modal-search-nota-debit").modal('hide');

        });

        $('#tabelDaftarNotaKredit').on('click', '.item-add-data', function () {
            var noNota = $(this).attr('data');
            var bayar = $(this).attr('bayar');
            var rekeningId = $(this).attr('rekeningId');
            $('#no_nota_kredit').val(noNota);
            $('#jumlah_pembayaran_kredit').val(bayar);
            $('#rekening_id_kredit').val(rekeningId);

            $("#modal-search-nota-kredit").modal('hide');

        });

        $('#btnAddCheckedNotaDebit').click(function () {
            var data = $('#tabelDaftarNotaDebit').tableToJSON();
            var kodeVendor=$('#kode_vendor_debit').val();
            var namaVendor=$('#nama_vendor_debit').val();
            var divisiId=$('#divisi_id_debit').val();
            var divisiName=$('#nama_divisi_debit').val();
            var error="";
            $.each(data, function (i, item) {
                var noNota = data[i]["No. Nota"];
                var bayar = data[i]["Debit"];
                var rekeningId = data[i]["Rekening ID"];
                if ($('#check_' + i).prop("checked") == true) {
                    if (kodeVendor!=''&&noNota!=''&&namaVendor!=''){
                        KasAction.saveKasDetail(kodeVendor,namaVendor,noNota,bayar,rekeningId,divisiId,divisiName,function (result) {
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

        $('#btnAddCheckedNotaKredit').click(function () {
            var data = $('#tabelDaftarNotaKredit').tableToJSON();
            var kodeVendor=$('#kode_vendor_kredit').val();
            var namaVendor=$('#nama_vendor_kredit').val();
            var divisiId=$('#divisi_id_kredit').val();
            var divisiName=$('#nama_divisi_kredit').val();
            var error="";
            $.each(data, function (i, item) {
                var noNota = data[i]["No. Nota"];
                var bayar = data[i]["Debit"];
                var rekeningId = data[i]["Rekening ID"];
                if ($('#check_' + i).prop("checked") == true) {
                    if (kodeVendor!=''&&noNota!=''&&namaVendor!=''){
                        KasAction.saveKasDetail(kodeVendor,namaVendor,noNota,bayar,rekeningId,divisiId,divisiName,function (result) {
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


        $('#btnSaveDetailPembayaranDebit').click(function () {
            var kodeVendor=$('#kode_vendor_debit').val();
            var namaVendor=$('#nama_vendor_debit').val();
            var noNota=$('#no_nota_debit').val();
            var rekeningId=$('#coa_debit').val();
            var jumlahPembayaran=$('#jumlah_pembayaran_debit').val();
            var divisiId=$('#divisi_id_debit').val();
            var divisiName=$('#nama_divisi_debit').val();
            var tipeTransaksi = $('#tipe_transaksi option:selected').val();

            var statusDivisi=$('#divisi_id_debit').attr('wajib');
            var statusVendor=$('#kode_vendor_debit').attr('wajib');

            if (statusDivisi=='Y'&& divisiId==""){
                alert("belum memilih Divisi");
            } else if (statusVendor=='Y'&& kodeVendor==""){
                alert("belum memilih Vendor");
            }else{
                if (jumlahPembayaran!=""){
                    KasAction.saveDetailKoreksi(kodeVendor,namaVendor,noNota,jumlahPembayaran,rekeningId,divisiId,divisiName,tipeTransaksi,function (result) {
                        if (result==""){
                            loadDetailPembayaran();
                        } else{
                            alert(result);
                        }
                    });
                }else {
                    if (jumlahPembayaran==""){
                        alert("Jumlah Pembayaran belum di masukkan")
                    }
                }
            }
        });

        $('#btnSaveDetailPembayaranKredit').click(function () {
            var kodeVendor=$('#kode_vendor_kredit').val();
            var namaVendor=$('#nama_vendor_kredit').val();
            var noNota=$('#no_nota_kredit').val();
            var rekeningId=$('#coa_kredit').val();
            var jumlahPembayaran=$('#jumlah_pembayaran_kredit').val();
            var divisiId=$('#divisi_id_kredit').val();
            var divisiName=$('#nama_divisi_kredit').val();
            var tipeTransaksi = $('#tipe_transaksi option:selected').val();

            var statusDivisi=$('#divisi_id_kredit').attr('wajib');
            var statusVendor=$('#kode_vendor_kredit').attr('wajib');

            if (statusDivisi=='Y'&& divisiId==""){
                alert("belum memilih Divisi");
            } else if (statusVendor=='Y'&& kodeVendor==""){
                alert("belum memilih Vendor");
            }else{
                if (jumlahPembayaran!=""){
                    KasAction.saveDetailKoreksi(kodeVendor,namaVendor,noNota,jumlahPembayaran,rekeningId,divisiId,divisiName,tipeTransaksi,function (result) {
                        if (result==""){
                            loadDetailPembayaran();
                        } else{
                            alert(result);
                        }
                    });
                }else {
                    if (jumlahPembayaran==""){
                        alert("Jumlah Pembayaran belum di masukkan")
                    }
                }
            }
        });

        $('.detailPembayaranTable').on('click', '.item-delete-data', function () {
            var rekeningId = $(this).attr('rekening');
            var nonota = $(this).attr('data');
            var vendor = $(this).attr('vendor');
            var divisi = $(this).attr('divisi');
            var biaya = $(this).attr('biaya');
            KasAction.deleteDetailPembayaran(rekeningId,divisi,vendor,nonota,biaya,function () {
                alert("data berhasil dihapus");
                loadDetailPembayaran();
                var totalBayar = $('#bayar').val();
                    totalBayar = totalBayar;
                    // .replace(/[.]/g, "");
                    var strBayar = biaya;
                    // .replace(/[.]/g, "");
                    var intTotalBayar = 0;
                    if (totalBayar != '') {
                        intTotalBayar = parseInt(totalBayar);
                    }
                    var intBayar = parseInt(strBayar);
                    totalBayar = intTotalBayar - intBayar;
                    var strTotalBayar = String(totalBayar);
                    $('#bayar').val(formatRupiahAngka(strTotalBayar));
            });
        });
        window.loadDetailPembayaran = function () {
            $('.detailPembayaranTable').find('tbody').remove();
            $('.detailPembayaranTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            KasAction.searchDetailPembayaran(function (listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>No</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Kode Rekening</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Divisi ID</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Nama Divisi</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Kode Vendor</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Nama Vendor</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>No. Nota</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Jumlah Debit</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Jumlah Kredit</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Delete</th>" +
                    "</tr></thead>";
                var i = i;
                var totalDebit = 0;
                var totalKredit = 0;
                $.each(listdata, function (i, item) {
                    var stDebit = "0";
                    var stKredit = "0";
                    if (item.posisiCoa=="D"){
                        stDebit = item.stJumlahPembayaran;
                        totalDebit=totalDebit+parseInt(stDebit.replace(/[.]/g,""));
                    }else {
                        stKredit = item.stJumlahPembayaran;
                        totalKredit=totalKredit+parseInt(stKredit.replace(/[.]/g,""));
                    }

                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.rekeningId + '</td>' +
                        '<td align="center">' + item.divisiId + '</td>' +
                        '<td align="center">' + item.divisiName + '</td>' +
                        '<td align="center">' + item.masterId + '</td>' +
                        '<td align="center">' + item.masterName + '</td>' +
                        '<td align="center">' + item.noNota + '</td>' +
                        '<td align="center">' + stDebit+ '</td>' +
                        '<td align="center">' + stKredit+ '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete-data' data ='" + item.noNota + "' rekening ='" + item.rekeningId + "' vendor ='" + item.masterId + "' biaya ='" + item.stJumlahPembayaran + "' divisi ='" + item.divisiId + "'>" +
                        "<img border='0' src='<s:url value='/pages/images/delete_task.png'/>' name='icon_delete'>" +
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center" colspan="7"> Jumlah </td>' +
                    '<td align="center">' + formatRupiahAtas(totalDebit)+ '</td>' +
                    '<td align="center">' + formatRupiahAtas(totalKredit)+ '</td>' +
                    '<td align="center"> </td>' +
                    "</tr>";
                $('.detailPembayaranTable').append(tmp_table);
            });
        };
    });

    function isiKeteterangan() {
        var tipeTransaksi = $('#tipe_transaksi option:selected').text();
        var metodeBayar = $('#metode_bayar option:selected').text();
        var branchName = $('#branch_id option:selected').text();
        var keterangan ="";

        keterangan= tipeTransaksi +" "+branchName;

        $('#keterangan').val(keterangan);
    }

    function getTipeMaster() {
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        KasAction.getTipeMaster(tipeTransaksi,function (response) {
            $('#tipeMaster').val(response);
        })
    }

    function getCoaLawan() {
        var option = '<option value=""></option>';
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        KodeRekeningAction.getKodeRekeningLawanByTransId(tipeTransaksi,"D",function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.kodeRekening+'">'+item.tampilanCoa+'</option>';
                });
                $('#coa_debit').html(option);
            }else{
                $('#coa_debit').html(option);
            }
        });

        var option2 = '<option value=""></option>';
        KodeRekeningAction.getKodeRekeningLawanByTransId(tipeTransaksi,"K",function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option2 += '<option value="'+item.kodeRekening+'">'+item.tampilanCoa+'</option>';
                });
                $('#coa_kredit').html(option2);
            }else{
                $('#coa_kredit').html(option2);
            }
        });
    }

    function getDisableTransDebit() {
        $('#divisi_id_debit').attr('readonly', true);
        $('#kode_vendor_debit').attr('readonly', true);
        $('#no_nota_debit').attr('readonly', true);
        $('#jumlah_pembayaran_debit').attr('readonly', true);
        $('#btnSearchNotaDebit').hide();
        $('#divisi_id_debit').attr('wajib', "N");
        $('#kode_vendor_debit').attr('wajib', "N");
        $('#no_nota_debit').attr('wajib', "N");
        var coaLawanDebit = $('#coa_debit option:selected').val();
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        KasAction.getDisableTrans(tipeTransaksi,coaLawanDebit,function (res) {
            if (res.divisiId=="Y"){
                $('#divisi_id_debit').attr('readonly', false);
                $('#divisi_id_debit').attr('wajib', "Y");
            }
            if (res.masterId=="Y"){
                $('#kode_vendor_debit').attr('readonly', false);
                $('#kode_vendor_debit').attr('wajib', "Y");
            }

            if (res.noNota=="Y"){
                $('#btnSearchNotaDebit').show();
                $('#no_nota_debit').attr('wajib', "Y");
            }else {
                $('#no_nota_debit').attr('wajib', "N");
            }

            if (res.biaya=="Y"){
                $('#jumlah_pembayaran_debit').attr('readonly', false);
            }
        });
    }

    function getDisableTransKredit() {
        $('#divisi_id_kredit').attr('readonly', true);
        $('#kode_vendor_kredit').attr('readonly', true);
        $('#no_nota_kredit').attr('readonly', true);
        $('#jumlah_pembayaran_kredit').attr('readonly', true);
        $('#btnSearchNotaKredit').hide();
        $('#divisi_id_kredit').attr('wajib', "N");
        $('#kode_vendor_kredit').attr('wajib', "N");
        $('#no_nota_debit').attr('wajib', "N");

        var coaLawanKredit = $('#coa_kredit option:selected').val();
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        KasAction.getDisableTrans(tipeTransaksi,coaLawanKredit,function (res) {
            if (res.divisiId=="Y"){
                $('#divisi_id_kredit').attr('readonly', false);
                $('#divisi_id_kredit').attr('wajib', "Y");
            }
            if (res.masterId=="Y"){
                $('#kode_vendor_kredit').attr('readonly', false);
                $('#kode_vendor_kredit').attr('wajib', "Y");
            }

            if (res.noNota=="A"){
                $('#btnSearchNotaKredit').show();
                $('#no_nota_kredit').attr('wajib', "Y");
            }else if (res.noNota=="Y"){
                $('#no_nota_kredit').attr('wajib', "Y");
                $('#no_nota_kredit').attr('readonly', false);
            }

            if (res.biaya=="Y"){
                $('#jumlah_pembayaran_kredit').attr('readonly', false);
            }
        });
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

    $('#btnAddLampiran').click(function () {
        var namaLampiran = $('#mod_nama_lampiran').val();
        var canvas = document.getElementById('img_faktur_canvas2');
        var dataURL = canvas.toDataURL("image/png"),
            dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");

        if (namaLampiran !=''&&canvas!=''){
            KasAction.saveSessionLampiran(namaLampiran, dataURL, function (result) {
                if (result == "") {
                    loadLampiran();
                    $('#mod_nama_lampiran').val("");
                    $('#img_faktur_canvas2').val("");
                } else {
                    alert(result);
                }
            });
        } else{
            var msg="";
            if (namaLampiran==""){
                msg+="Nama Lampiran masih kosong. \n"
            }
            if (canvas==""){
                msg+="Gambar belum diupload \n"
            }
            alert(msg);
        }
    });

    $('.tabelLampiran').on('click', '.item-delete-lampiran', function () {
        var nama = $(this).attr('nama');
        if (nama != '') {
            KasAction.deleteSessionLampiran(nama, function (result) {
                alert("data berhasil dihapus");
                loadLampiran();
            });
        };
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
                "<th style='text-align: center; background-color:  #30d196'>Delete</th>" +
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="center">' + item.namaLampiran + '</td>' +
                    '<td align="center">' +
                    "<a href='javascript:;' class ='item-view-lampiran' nama ='" + item.namaLampiran + "'>" +
                    "<img border='0' src='<s:url value='/pages/images/icons8-search-25.png'/>'>" +
                    '</a>' +
                    '</td>' +
                    '<td align="center">' +
                    "<a href='javascript:;' class ='item-delete-lampiran' nama ='" + item.namaLampiran + "' >" +
                    "<img border='0' src='<s:url value='/pages/images/icons8-trash-can-25.png'/>'>" +
                    '</a>' +
                    '</td>' +
                    "</tr>";
            });
            $('.tabelLampiran').append(tmp_table);
        });
    };

    $('.tabelLampiran').on('click', '.item-view-lampiran', function(){
        var judul = $(this).attr('nama');
        dwr.engine.setAsync(false);
        KasAction.loadImageSessionLaporan(judul,function (data) {
            $("#my-image").attr("src", "data:image/png;base64,"+data);
        });
        $('#modal-view-lampiran').find('.modal-title').text(judul);
        $('#modal-view-lampiran').modal('show');
    });

    $(document).ready(function () {
        var canvas = document.getElementById('img_faktur_canvas2');
        var ctx = canvas.getContext('2d');

        $(document).on('change', '.btn-file-2 :file', function () {
            var input = $(this),
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        $('.btn-file-2 :file').on('fileselect', function (event, label) {
            var input = $(this).parents('.input-group').find(':text'),
                log = label;

            if (input.length) {
                input.val(log);
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = new Image();
                    img.onload = function () {
                        canvas.width = img.width;
                        canvas.height = img.height;
                        ctx.clearRect(0, 0, canvas.width, canvas.height);
                        ctx.drawImage(img, 0, 0);
                    };
                    img.src = event.target.result;
                };
                reader.readAsDataURL(event.target.files[0]);
            } else {
                if (log) alert(log);
            }
        });
    });

</script>
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

