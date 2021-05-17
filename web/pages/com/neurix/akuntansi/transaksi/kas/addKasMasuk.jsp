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
    <script type='text/javascript' src='<s:url value="/dwr/interface/AkunMataUangAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MasterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript'>


        function confirmAdd() {

            var currencyId = $('#currencyId').val();
            if (currencyId == null || currencyId == "") {
                $('#modal-add').modal('hide');
                var msg = "Belum Pilih Currency";
                if (status != "") {
                    msg += status + "\n";
                }
                $("html, body").animate({scrollTop: 0}, 600);
                $('#warning_pembayaran').show().fadeOut(10000);
                $('#errorText').html(msg);
            }
            else {
                getCoaLawan();
                $('.modal_add').val('');
                $('#modal-add').modal('show');
            }
        }

        function confirm() {

            var tipeTransaksi = $('#tipe_transaksi_add').val();

            var tanggal = $('#tanggal').val();
            var metodeBayar = $('#metodeBayar').val();
            var bayar = $('#bayar').val();
            var keterangan = $('#keterangan').val();
            var noslipBank = $('#no_slip_bank').val();
            var branchId = $('#branch_id').val();

            var currencyId = $('#currencyId option:selected').val();

            var status = "";
            KasAction.cekBeforeSave(currencyId, tipeTransaksi, tanggal, metodeBayar, bayar, keterangan, noslipBank, branchId, function (result) {
                status = result;
            });
            var rowCount = $('.detailPembayaranTable tr').length;
            if (rowCount < 2) {
                status += "\n Detail pemasukan tidak boleh kosong";
            }
            if (status == "") {
                $('#confirm_dialog').dialog('open');
            } else {
                var msg = "";
                if (status != "") {
                    msg += status + "\n";
                }
                $("html, body").animate({scrollTop: 0}, 600);
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
            Pemasukan Kas
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user-plus"></i> Input Pemasukan Kas</h3>
                    </div>
                    <s:form id="addKasForm" enctype="multipart/form-data" method="post"
                            namespace="/kas"
                            action="saveAdd_kas.action" theme="simple">
                    <s:hidden name="kas.tipeKas" value="KM"/>

                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" id="warning_pembayaran"
                             style="display: none">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <span id="errorText"></span>
                        </div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-user"></i> Form Pembayaran</h3>
                        </div>
                            <%--======================Form Pembayaran=============================================--%>
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="row">
                                        <label class="col-md-4" style="margin-top: 7px">Unit</label>
                                        <div class="col-md-8">
                                            <s:if test='kas.branchId == "KP"'>
                                                <s:action id="comboBranch" namespace="/admin/user"
                                                          name="initComboBranch_user"/>
                                                <s:select cssClass="form-control"
                                                          list="#comboBranch.listOfComboBranches" id="branch_id"
                                                          onchange="isiKeteterangan()"
                                                          name="kas.branchId" required="true"
                                                          listKey="branchId" listValue="branchName" headerKey=""
                                                          headerValue=""/>
                                            </s:if>
                                            <s:else>
                                                <s:action id="comboBranch" namespace="/admin/user"
                                                          name="initComboBranch_user"/>
                                                <s:select cssClass="form-control"
                                                          list="#comboBranch.listOfComboBranches"
                                                          id="branch_id_view" name="kas.branchId"
                                                          required="true" disabled="true"
                                                          listKey="branchId" listValue="branchName" headerKey=""
                                                          headerValue=""/>

                                                <s:hidden name="kas.branchId" id="branch_id"/>
                                            </s:else>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-md-4" style="margin-top: 7px">Tipe Transaksi</label>
                                        <div class="col-md-8">
                                            <s:action id="comboTrans" namespace="/trans"
                                                      name="initComboTransaksi_trans">
                                                <s:param name="tipe">JKM</s:param>
                                            </s:action>
                                            <s:select list="#comboTrans.listOfComboTrans" id="tipe_transaksi"
                                                      name="kas.tipeTransaksi"
                                                      cssStyle="margin-top: 7px"
                                                      onchange="isiKeteterangan(),getTipeMaster(),getCoaLawan(),getCurrencyActive()"
                                                      listKey="transId" listValue="transName" headerKey=""
                                                      headerValue="" cssClass="form-control select2"/>
                                            <s:hidden id="tipe_transaksi_add"
                                                      name="kas.tipeTransaksi"/>
                                            <s:hidden id="tipeMaster"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-md-4" style="margin-top: 7px">Tanggal</label>
                                        <div class="col-md-8">
                                            <div class="input-group date" style="margin-top: 7px" id="st_tgl">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <s:textfield id="tanggal" name="kas.stTanggal"
                                                             cssStyle="background-color: #fff" readonly="true"
                                                             cssClass="form-control datemask"
                                                             onchange="$('#st_tgl').css('border','')"/>
                                                <script>
                                                    $("#tanggal").datepicker({
                                                        setDate: new Date(),
                                                        autoclose: true,
                                                        changeMonth: true,
                                                        changeYear: true,
                                                        dateFormat: 'yy-mm-dd'
                                                    });
                                                    $("#tanggal").datepicker("setDate", new Date());
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-md-4" style="margin-top: 7px">Currency </label>
                                        <div class="col-md-8">
                                            <s:action id="comboCurrency" namespace="/akunMataUang"
                                                      name="initComboCurrency_akunMataUang">
                                            </s:action>
                                            <s:select list="#comboCurrency.listOfComboCurrency" id="currencyId"
                                                      cssStyle="margin-top: 7px" listKey="kodeMataUang"
                                                      listValue="namaMataUang" headerKey="" headerValue=""
                                                      cssClass="form-control"/>
                                            <s:hidden id="currencyHide" name="kas.currencyId"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-md-4" style="margin-top: 14px">Metode Bayar</label>
                                        <div class="col-md-3">
                                            <s:select list="#{'Tunai':'Tunai','Transfer':'Transfer'}" id="metodeBayar"
                                                      name="kas.metodePembayaran"
                                                      headerKey="" headerValue="[Select one]" cssClass="form-control"
                                                      cssStyle="margin-top: 7px"
                                                      onchange="pilihMetode(this.value),isiKeteterangan(), getCoaAsal()"
                                            />

                                        </div>
                                        <div class="col-md-5" id="pilih_bank">
                                            <select class="form-control select2" id="coa_asal" style="width: 100%"
                                                    onchange="isiKeteterangan()" style="margin-top: 7px"
                                                    name="kas.coaKas">
                                                <option value=""></option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-md-4" style="margin-top: 7px">Bayar</label>
                                        <div class="col-md-8">
                                            <s:textfield id="bayar" name="kas.stBayar"
                                                         cssClass="form-control" cssStyle="margin-top: 7px"
                                                         readonly="true"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-md-4" style="margin-top: 7px">Keterangan</label>
                                        <div class="col-md-8">
                                            <s:textarea id="keterangan" rows="3" cssStyle="margin-top: 7px"
                                                        onkeypress="$(this).css('border','')"
                                                        name="kas.keterangan"
                                                        cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-md-4" style="margin-top: 7px">No. Referensi</label>
                                        <div class="col-md-8">
                                            <s:textfield id="no_slip_bank" name="kas.noSlipBank"
                                                         onkeypress="$(this).css('border','')"
                                                         onchange="isiKeteterangan()"
                                                         cssClass="form-control" cssStyle="margin-top: 7px"/>
                                        </div>
                                    </div>

                                        <%--button--%>
                                    <div class="form-group" style="display: inline;">
                                        <div class="col-sm-10 col-md-offset-4" style="margin-top: 7px">
                                            <button type="button" class="btn btn-primary" id="btnAddDetailPembayaran"
                                                    onclick="confirmAdd()"><i
                                                    class="fa fa-plus"></i> Tambah
                                            </button>
                                                <%--<script>
                                                    $('#btnAddDetailPembayaran').click(function () {
                                                        $('.modal_add').val('');
                                                        $('#modal-add').modal('show');
                                                        /*var tipeMaster = $('#tipeMaster').val();
                                                        if (tipeMaster=="pengajuan_biaya") {
                                                            $('.modal_pengajuan').val('');
                                                            $('#modal-add-pengajuan').modal('show');
                                                        } else {
                                                            getCoaLawan();
                                                            $('.modal_add').val('');
                                                            $('#modal-add').modal('show');
                                                        }*/
                                                    })
                                                </script>--%>
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
                            <a type="button" class="btn btn-warning"
                               href="initFormPemasukan_kas.action">
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
                                               formIds="addKasForm" id="save" name="save"
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
                                    <img class="spin" border="0"
                                         style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                         src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                         name="image_indicator_write">
                                </center>
                            </sj:dialog>

                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                       resizable="false" closeOnEscape="false"
                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                       buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.href = 'initFormPemasukan_kas.action';
                                                                                     }
                                                                            }"
                            >
                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                     name="icon_success">
                                Record has been saved successfully.
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
                </div>
                </s:form>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<%--modals--%>
<%--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
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
                <button type="button" class="btn btn-success" id="btnAddCheckedNota" data-dismiss="modal"><i
                        class="fa fa-arrow-right"></i> Add Checked
                </button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<%--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="modal fade" id="modal-add">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail </h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">COA Lawan</label>
                                <div class="col-md-8">
                                    <select class="form-control" id="coa_lawan"
                                            onchange="getDisableTrans()">
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">ID Divisi</label>
                                <%--bawaan mod--%>
                                <div class="col-md-3">
                                    <s:textfield id="mod_id_divisi_add" onkeypress="$(this).css('border','')" wajib="Y"
                                                 readonly="true"
                                                 cssClass="form-control modal_add" cssStyle="margin-top: 7px"/>
                                    <script>
                                        $(document).ready(function () {
                                            var functions, mapped;
                                            $('#mod_id_divisi_add').typeahead({
                                                minLength: 1,
                                                source: function (query, process) {
                                                    functions = [];
                                                    mapped = {};
                                                    var data = [];
                                                    dwr.engine.setAsync(false);
                                                    PositionAction.typeAheadPosition(query, function (listdata) {
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
                                                    $('#mod_nama_divisi_add').val(selectedObj.nama);
                                                    return selectedObj.id;
                                                }
                                            });
                                        });
                                    </script>
                                </div>
                                <div class="col-md-5">
                                    <s:textfield id="mod_nama_divisi_add" onkeypress="$(this).css('border','')"
                                                 readonly="true"
                                                 cssClass="form-control modal_add" cssStyle="margin-top: 7px"/>
                                </div>


                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">ID Vendor/Kary/Dokter/Pasien</label>
                                <div class="col-md-3">
                                    <s:textfield id="kode_vendor" onkeypress="$(this).css('border','')" wajib="Y"
                                                 readonly="true"
                                                 cssClass="form-control modal_add" cssStyle="margin-top: 7px"/>
                                    <script>
                                        $(document).ready(function () {
                                            var functions, mapped;
                                            $('#kode_vendor').typeahead({
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
                                                        alert("master belum di tentukan");
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
                                                 cssClass="form-control modal_add" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                            <div class="form-group" id="no_nota_view_add">
                                <label class="col-md-4" style="margin-top: 7px">No. Nota</label>
                                <div class="col-md-7">
                                    <s:textfield id="no_nota" wajib="Y"
                                                 cssClass="form-control modal_add" readonly="true"
                                                 cssStyle="margin-top: 7px"/>
                                </div>
                                <s:hidden id="rekening_id"/>
                                <div class="col-md-1">
                                    <a href="javascript:void(0)">
                                        <img style="margin-top: 10px;display: none" id="btnSearchNota" border="0"
                                             src="<s:url value="/pages/images/view.png"/>" name="icon_view">
                                    </a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Jumlah Pembayaran</label>
                                <div class="col-md-8">
                                    <s:textfield id="jumlah_pembayaran" readonly="true"
                                                 cssClass="form-control modal_add"
                                                 cssStyle="margin-top: 7px;margin-bottom: 14px"
                                                 onkeyup="formatRupiah2(this)"/>
                                </div>
                            </div>
                            <br>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="btnSaveDetailPembayaran" type="button" class="btn btn-default btn-success"><i
                        class="fa fa-plus"></i> Add</a>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<%--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
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
                            <s:textfield id="mod_nama_lampiran" onkeypress="$(this).css('border','')"
                                         cssClass="form-control modal_lampiran"/>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 7px">
                        <label class="control-label col-sm-4">Lampiran (PDF/JPEG/PNG) </label>
                        <div class="col-sm-8">
                            <div class="input-group" id="img_file2" style="margin-top: 7px">
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
                            <a id="btnAddLampiran" type="button" class="btn btn-default btn-success"><i
                                    class="fa fa-plus"></i> Tambah</a>
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

<%--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="modal fade" id="modal-view-lampiran" role="dialog">
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

<%--/////////////////////////////////// tidak dipakai belum yakin ////////////////////////////////////////////////////////////////////////--%>
<%--<div class="modal fade" id="modal-add-pengajuan">
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
                                                        tipePengajuan: item.transaksi,
                                                        noBudgeting: item.noBudgeting,
                                                        divisiId: item.divisiId,
                                                        divisiName: item.divisiName,
                                                        coaDivisi: item.coaDivisi,
                                                        coaLawan: item.coa,
                                                        coaLawanName: item.coaName,
                                                        noKontrak: item.noKontrak,
                                                        namaKontrak: item.keperluanName,
                                                        jumlah: item.stJumlah
                                                    };
                                                    functions.push(labelItem);
                                                });
                                                process(functions);
                                            },
                                            updater: function (item) {
                                                var selectedObj = mapped[item];
                                                $('#mod_tanggal_realisasi').val(selectedObj.tanggalRealisasi);
                                                $('#mod_tipe_pengajuan').val(selectedObj.tipePengajuan);
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

                                                var jumlah = selectedObj.jumlah.replace(/[,]/g,"");
                                                var nilaiJumlah = parseInt(jumlah);
                                                $('#mod_total_ppn').val(formatRupiahAtas(nilaiJumlah*10/100));
                                                $('#mod_total_pph').val(formatRupiahAtas(nilaiJumlah*2/100));
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
                            <div class="form-group" id="tipe_pengajuan_view">
                                <label class="col-md-4" style="margin-top: 7px">Tipe Pengajuan</label>
                                <div class="col-md-8">
                                    <s:select list="#{'R':'Rutin','I':'Investasi'}" cssStyle="margin-top: 7px" disabled="true"
                                              id="mod_tipe_pengajuan" headerKey="" headerValue="" cssClass="form-control modal_pengajuan" />
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
                                <label class="col-md-4" style="margin-top: 7px">PPN (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_total_ppn" cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" placeholder="0" onkeyup="formatRupiah2(this)" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">PPH (RP)</label>
                                <div class="col-md-8">
                                    <s:textfield id="mod_total_pph" cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px;" onkeyup="formatRupiah2(this)" placeholder="0"/>
                                </div>
                            </div>
                            <div class="form-group" id="kode_vendor_view_pengajuan">
                                <label class="col-md-4" style="margin-top: 7px">Kode Vendor</label>
                                <div class="col-md-3">
                                    <s:textfield id="mod_kode_vendor_pengajuan" onkeypress="$(this).css('border','')" wajib="Y"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" />
                                    <script>
                                        $(document).ready(function() {
                                            var functions, mapped;
                                            $('#mod_kode_vendor_pengajuan').typeahead({
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
                                                    $('#mod_nama_vendor_pengajuan').val(selectedObj.nama);
                                                    return selectedObj.id;
                                                }
                                            });
                                        });
                                    </script>
                                </div>
                                <div class="col-md-5">
                                    <s:textfield id="mod_nama_vendor_pengajuan" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px" />
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
                                        var namaVendor = $('#mod_nama_vendor_pengajuan').val();
                                        if (namaVendor==""){
                                            alert("Masukkan vendor terlebih dahulu");
                                        } else{
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
                                    <div class="input-group" id="img_file"  style="margin-top: 7px">
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
</div>--%>

<!-- /.content-wrapper -->
<script type='text/javascript'>
    function selectPembayaran() {
        var option = '<option value=""></option>';
        KasirRawatJalanAction.getListPembayaran(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.coa + '">' + item.pembayaranName + '</option>';
                });
                $('#bank').html(option);
            } else {
                $('#bank').html(option);
            }
        });
    }

    $(document).ready(function () {

        $('#currencyId').change(function () {


            var currencyId = $('#currencyId').val();
            if(currencyId != ''){
                $('#currencyHide').val(currencyId);
                console.log($('#currencyHide').val())
            }
        })

        $('#mod_id_divisi_add').attr('readonly', true);
        $('#kode_vendor').attr('readonly', true);
        $('#no_nota').attr('readonly', true);
        $('#jumlah_pembayaran').attr('readonly', true);
        $('#btnSearchNota').hide();
        $('#mod_id_divisi_add').attr('wajib', "N");
        $('#kode_vendor').attr('wajib', "N");
        $('#no_nota').attr('wajib', "N");
        $('#jumlah_pembayaran').attr('wajib', "N");

        selectPembayaran();
        $('#btnSearchNota').click(function () {
            var masterId = $('#kode_vendor').val();
            var transaksiId = $('#tipe_transaksi').val();
            var branchId = $('#branch_id').val();
            var divisiId = $('#mod_id_divisi_add').val();
            var coaLawan = $('#coa_lawan').val();

            $('#tabelDaftarNota').find('tbody').remove();
            $('#tabelDaftarNota').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";

            var add = true;
            if (coaLawan == "" || branchId == "" || transaksiId == "") {
                add = false;
            }
            var statusDivisi = $('#mod_id_divisi_add').attr('wajib');
            var statusVendor = $('#kode_vendor').attr('wajib');

            if (statusDivisi == 'Y' && divisiId == "") {
                alert("belum memilih Divisi");
            } else if (statusVendor == 'Y' && masterId == "") {
                alert("belum memilih Vendor");
            } else {
                if (add) {
                    KasAction.searchNotaPembayaran(masterId, transaksiId, branchId, divisiId, coaLawan, function (listdata) {
                        tmp_table = "<thead style='font-size: 14px' ><tr class='active'>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>No</th>" +
                            // "<th style='text-align: center; color: #fff; background-color:  #30d196'><input type='checkbox' id='checkAll'></th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Kode Vendor</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Rekening ID</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>No. Nota</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Debit</th>" +
                            "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Add</th>" +
                            "</tr></thead>";
                        var i = i;
                        $.each(listdata, function (i, item) {
                            // var combo = '<input type="checkbox" checked id="check_'+i+'">';
                            tmp_table += '<tr style="font-size: 12px;" ">' +
                                '<td align="center">' + (i + 1) + '</td>' +
                                // '<td align="center">' + combo + '</td>' +
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
                        $("#checkAll").change(function () {
                            $('input:checkbox').not(this).prop('checked', this.checked);
                        });
                    });
                    $("#modal-search-nota").appendTo("body").modal('show');
                } else {
                    var msg = "";
                    if (transaksiId == "") {
                        msg += "Tipe Transaksi belum dipilih \n";
                    }
                    if (branchId == "") {
                        msg += "Unit belum dipilih \n";
                    }
                    if (coaLawan == "") {
                        msg += "COA lawan belum dipilih \n";
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
            var kodeVendor = $('#kode_vendor').val();
            var namaVendor = $('#nama_vendor').val();
            var divisiId = $('#mod_id_divisi_add').val();
            var divisiName = $('#nama_divisi').val();
            var error = "";
            $.each(data, function (i, item) {
                var noNota = data[i]["No. Nota"];
                var bayar = data[i]["Debit"];
                var rekeningId = data[i]["Rekening ID"];
                if ($('#check_' + i).prop("checked") == true) {
                    if (kodeVendor != '' && noNota != '' && namaVendor != '') {
                        KasAction.saveKasDetail(kodeVendor, namaVendor, noNota, bayar, rekeningId, divisiId, divisiName, function (result) {
                            if (result == "") {
                                loadDetailPembayaran();
                                //dihitung totalbayarnya
                                var totalBayar = $('#bayar').val();
                                totalBayar = totalBayar.replace(/[.]/g, "");
                                var strBayar = bayar.replace(/[.]/g, "");
                                var intTotalBayar = 0;
                                if (totalBayar != '') {
                                    intTotalBayar = parseInt(totalBayar);
                                }
                                var intBayar = parseInt(strBayar);
                                totalBayar = intTotalBayar + intBayar;
                                var strTotalBayar = String(totalBayar);
                                $('#bayar').val(formatRupiahAngka(strTotalBayar));
                            } else {
                                error = result;
                            }
                        });
                    } else {
                        var msg = "";
                        if (kodeVendor == "") {
                            msg += "Kode vendor tidak boleh kosong \n";
                        }
                        if (noNota == "") {
                            msg += "No nota tidak boleh kosong \n";
                        }
                        if (jumlahPembayaran == "") {
                            msg += "Jumlah pembayaran tidak boleh kosong \n";
                        }
                        if (namaVendor == "") {
                            msg += "Nama vendor tidak ditemukan, coba lagi \n";
                        }
                        error = msg;
                    }
                }
            });
            if (error != "") {
                alert(error);
            }
        });

        $('#btnSaveDetailPembayaran').click(function () {
            var kodeVendor = $('#kode_vendor').val();
            var namaVendor = $('#nama_vendor').val();
            var noNota = $('#no_nota').val();
            var rekeningId = $('#rekening_id').val();
            if (rekeningId == "") {
                rekeningId = $('#coa_lawan').val();
            }
            var jumlahPembayaran = $('#jumlah_pembayaran').val();
            var currency = $('#currencyId').val();

            var divisiId = $('#mod_id_divisi_add').val();
            var divisiName = $('#mod_nama_divisi_add').val();

            var statusDivisi = $('#mod_id_divisi_add').attr('wajib');
            var statusVendor = $('#kode_vendor').attr('wajib');
            var statusNota = $('#no_nota').attr('wajib');
            var statusJumlahPembayaran = $('#jumlah_pembayaran').attr('wajib');

            if (statusDivisi == 'Y' && divisiId == "") {
                alert("belum memilih Divisi");
            } else if (statusVendor == 'Y' && kodeVendor == "") {
                alert("belum memilih Vendor");
            } else if (statusNota == 'Y' && noNota == "") {
                alert("belum memilih/mengisi No. Nota");
            } else if (statusJumlahPembayaran == 'Y' && jumlahPembayaran == "") {
                alert("jumlah pembayaran belum dimasukkan");
            } else {
                KasAction.saveKasDetail(kodeVendor, namaVendor, noNota, jumlahPembayaran, rekeningId, divisiId, divisiName, currency, function (result) {
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
                        $('#tipe_transaksi').prop('disabled', 'true');
                        $('#tipe_transaksi_add').val($('#tipe_transaksi').val());
                        $('#currencyId').prop('disabled', 'true');
                        $('#currencyHide').val($('#currencyId').val());
                        $('#modal-add').modal('hide');
                    } else {
                        alert(result);
                    }
                });
                $('#no_nota').val('');
                $('#jumlah_pembayaran').val('');
            }

        });
        $('.detailPembayaranTable').on('click', '.item-delete-data', function () {
            var rekeningId = $(this).attr('rekening');
            var nonota = $(this).attr('data');
            var vendor = $(this).attr('vendor');
            var divisi = $(this).attr('divisi');
            var biaya = $(this).attr('biaya');
            KasAction.deleteDetailPembayaran(rekeningId, divisi, vendor, nonota, biaya, function () {
                alert("data berhasil dihapus");
                loadDetailPembayaran();
                var totalBayar = $('#bayar').val();
                totalBayar = totalBayar.replace(/[.]/g, "");
                var strBayar = biaya.replace(/[.]/g, "");
                var intTotalBayar = 0;
                if (totalBayar != '') {
                    intTotalBayar = parseInt(totalBayar);
                }
                var intBayar = parseInt(strBayar);
                totalBayar = intTotalBayar - intBayar;
                var strTotalBayar = String(totalBayar);
                $('#bayar').val(formatRupiahAngka(strTotalBayar));

                var rowCount = $('.detailPembayaranTable tr').length;
                if (rowCount === 1) {
                    $('#tipe_transaksi').removeAttr('disabled');
                    $('#tipe_transaksi_add').val();

                    $('#currencyId').removeAttr('disabled');
                    $('#currencyHide').val();
                    $('#modal-add').modal('hide');
                }
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
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Rekening ID</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Divisi ID</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Nama Divisi</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 '>Kode Vendor</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Nama Vendor</th>" +
                    "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>No. Nota</th>" +
                    // "<th style='text-align: center; color: #fff; background-color:  #30d196 ''>Currency</th>" +
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
                        // '<td align="center">' + item.currency + '</td>' +
                        '<td align="center">' + item.stJumlahPembayaran + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete-data' data ='" + item.noNota + "' rekening ='" + item.rekeningId + "' vendor ='" + item.masterId + "' biaya ='" + item.stJumlahPembayaran + "' divisi ='" + item.divisiId + "'>" +
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
        var metodeBayar = $( "#metodeBayar option:selected" ).text();
        var coa = $( "#coa_asal option:selected" ).text();
        var branchName = $('#branch_id option:selected').text();
        var noSlipBank = $('#no_slip_bank').val();
        var metode = "";
        var keterangan = "";
        if (metodeBayar!=""){
            if (metodeBayar=="Tunai"){
                metode = " dengan pembayaran tunai";
            }else{
                metode = " dengan pembayaran transfer pada "+coa;
            }
        }

        keterangan = tipeTransaksi + " " + branchName + " " + metode;

        $('#keterangan').val(keterangan);
    }

    function getTipeMaster() {
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        KasAction.getTipeMaster(tipeTransaksi, function (response) {
            $('#tipeMaster').val(response);
        })
    }

    function getCoaAsal() {
        var option = '<option value=""></option>';
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        KodeRekeningAction.getKodeRekeningLawanByTransId(tipeTransaksi, "D", function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.kodeRekening + '">' + item.tampilanCoa + '</option>';
                });
                $('#coa_asal').html(option);
            } else {
                $('#coa_asal').html(option);
            }
        });
    }

    function getCurrencyActive() {
        var option = '<option value=""></option>';
        $('#currencyId').attr('readonly', false);
        AkunMataUangAction.getCurrencyActive(function (res) {
            if (res.length > 0) {
                $.each(res, function (item) {
                    option += '<option value="' + res[item].kodeMataUang+ '">' + res[item].namaMataUang + '</option>';
                });
                $('#currencyId').html(option);
            } else {
                $('#currencyId').html(option);
            }
        });
    }

    function getCoaLawan() {
        var option = '<option value=""></option>';
        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        KodeRekeningAction.getKodeRekeningLawanByTransId(tipeTransaksi, "K", function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.kodeRekening + '">' + item.tampilanCoa + '</option>';
                });
                $('#coa_lawan').html(option);
            } else {
                $('#coa_lawan').html(option);
            }
        });
    }

    function getDisableTrans() {
        $('#mod_id_divisi_add').attr('readonly', true);
        $('#mod_id_divisi_add').val('');
        $('#kode_vendor').attr('readonly', true);
        $('#kode_vendor').val('');
        $('#nama_vendor').val('');
        $('#no_nota').attr('readonly', true);
        $('#jumlah_pembayaran').attr('readonly', true);


        $('#btnSearchNota').hide();
        $('#mod_id_divisi_add').attr('wajib', "N");
        $('#kode_vendor').attr('wajib', "N");
        $('#no_nota').attr('wajib', "N");
        $('#jumlah_pembayaran').attr('wajib', "N");

        var tipeTransaksi = $('#tipe_transaksi option:selected').val();
        var coaLawan = $('#coa_lawan option:selected').val();
        if (tipeTransaksi != '' && coaLawan != '') {
            KasAction.getDisableTrans(tipeTransaksi, coaLawan, function (res) {
                if (res.divisiId == "Y") {
                    $('#mod_id_divisi_add').attr('readonly', false);
                    $('#mod_id_divisi_add').attr('wajib', "Y");
                }
                if (res.masterId == "Y") {
                    $('#kode_vendor').attr('readonly', false);
                    $('#kode_vendor').attr('wajib', "Y");
                }
                if (res.noNota == "Y") {
                    $('#btnSearchNota').show();
                    $('#no_nota').attr('wajib', "Y");
                } else {
                    $('#no_nota').attr('wajib', "N");
                }

                if (res.biaya == "Y") {
                    $('#jumlah_pembayaran').attr('readonly', false);
                    // $('#currencyId').attr('readonly', false);
                    $('#jumlah_pembayaran').attr('wajib', "Y");
                    $('#btnSearchNota').hide();
                    $('#no_nota').attr('readonly', false);
                } else {
                    $('#jumlah_pembayaran').attr('wajib', "N");
                }
            });
        } else {
            var msg = "";
            if (tipeTransaksi == "") {
                msg += "Tipe transaksi belum dipilih \n";
            }
            if (coaLawan == "") {
                msg += "COA lawan belum dipilih \n";
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

    <%--/////////////////////////////////// tidak dipakai belum yakin ////////////////////////////////////////////////////////////////////////--%>
    /*
        function formatCurrency(value) {
            var currencyId = $('#currencyId option:selected').val();
            if (currencyId == 'RPH') {
                // $('#jumlah_pembayaran').attr("onkeyup", formatRupiah2(value));
                $('#jumlah_pembayaran').attr("onkeyup", getCurrency(value, "id-ID"));

            }
            else {
                $('#jumlah_pembayaran').attr("onkeyup", getCurrency(value, "es-US"));
            }

        }

        function clearJumlahPembayaran() {
            // $('#jumlah_pembayaran').attr('readonly', false);
            $('#jumlah_pembayaran').val('');
        }*/
    <%--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
    function pilihMetode(val){
        if(val != ''){
            if(val == 'Transfer'){
                $('#pilih_bank').show();
            }else{
                $('#pilih_bank').hide();
            }
        }
    }

    $('#btnAddLampiran').click(function () {
        var namaLampiran = $('#mod_nama_lampiran').val();
        var canvas = document.getElementById('img_faktur_canvas2');
        var dataURL = canvas.toDataURL("image/png"),
            dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
        if (namaLampiran != '' && canvas != '') {
            KasAction.saveSessionLampiran(namaLampiran, dataURL, function (result) {
                if (result == "") {
                    $('#mod_nama_lampiran').val("");
                    $('#img_faktur_canvas2').val("");
                } else {
                    alert(result);
                }
            });
            loadLampiran();
        } else {
            var msg = "";
            if (namaLampiran == "") {
                msg += "Nama Lampiran masih kosong. \n"
            }
            if (canvas == "") {
                msg += "Gambar belum diupload \n"
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
        }
        ;
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

    $('.tabelLampiran').on('click', '.item-view-lampiran', function () {
        var judul = $(this).attr('nama');
        dwr.engine.setAsync(false);
        KasAction.loadImageSessionLaporan(judul, function (data) {
            $("#my-image").attr("src", "data:image/png;base64," + data);
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