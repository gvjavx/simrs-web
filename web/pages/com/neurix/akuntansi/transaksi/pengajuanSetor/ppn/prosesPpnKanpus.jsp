<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PengajuanSetorAction.js"/>'></script>
    <script type="text/javascript">
        function callSearch2() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }

        function link() {
            window.location.href = "<s:url action='initFormProsesPpnKd_pengajuanSetor.action'/>";
        }

        $(document).ready(function () {
            window.close2 = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
            };
            $.subscribe('beforeProcessSave', function (event, data) {
                if (confirm('Do you want to proses this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    event.originalEvent.options.submit = false;
                }
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

            function cancelBtn() {
                $('#view_dialog_menu').dialog('close');
            }
        });
    </script>

</head>

<body class="hold-transition skin-blue sidebar-mini">
<%@ include file="/pages/common/headerNav.jsp" %>
<ivelincloud:mainMenu/>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Pengajuan Setor
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-save"></i> PAJAK  KELUARAN & MASUKAN </h3>
                    </div>
                    <s:form id="saveAddPengajuanSetor" enctype="multipart/form-data" method="post"
                            namespace="/pengajuanSetor"
                            action="saveProsesPpnKanpus_pengajuanSetor.action" theme="simple">
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-header with-border"></div>
                                    <div class="box-header with-border">
                                        <h3 class="box-title"><i class="fa fa-th-list"></i> Data Summary</h3>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="nav nav-pills" id="tabPane">
                                        <li class="active"><a href="#normal" data-toggle="tab">Cek Pajak ( Normal )</a></li>
                                        <li><a href="#b2" data-toggle="tab">Cek Pajak ( B2 )</a></li>
                                        <li><a href="#b3" data-toggle="tab">Cek Pajak ( B3 )</a></li>
                                    </ul>
                                    <br>
                                    <br>
                                    <div class="tab-content clearfix">
                                        <div id="normal" class="tab-pane active">
                                            <div class="row">
                                                <div style="margin-left: 50px">
                                                    <div class="row">
                                                        <h5><b><u>PERHITUNGAN SETORAN PPN</u></b></h5>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="row" style="display: none">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">LB Bulan Yll (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stLbBulanYll" id="lb_bulan_yll_normal" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">PPN Masukan (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stPpnMasukan" id="ppn_masukan_normal" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row" style="display: none">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">Total PPN Masukan (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stTotalPpnMasukan" id="total_ppn_masukan_normal" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">PPN Keluaran (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stPpnKeluaran" id="ppn_keluaran_normal" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">Lebih Bayar (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stKurangBayar" id="kurang_bayar_normal" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">Perhitungan Kembali (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stPerhitunganKembali" id="perhitungan_kembali_normal" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px"><b>Total Lebih Bayar (RP)</b> </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stTotalKurangBayar" id="total_kurang_bayar_normal" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr>
                                            </div>
                                            <br>
                                            <br>
                                            <table id="table1" class="table table-bordered table-striped sortTable">
                                            </table>
                                        </div>
                                        <div id="b2" class="tab-pane fade">
                                            <div class="row">
                                                <div style="margin-left: 50px">
                                                    <div class="row">
                                                        <h5><b><u>PERHITUNGAN SETORAN PPN</u></b></h5>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="row" style="display: none">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">LB Bulan Yll (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stLbBulanYll" id="lb_bulan_yll_b2" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">PPN Masukan (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stPpnMasukan" id="ppn_masukan_b2" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row" style="display: none">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">Total PPN Masukan (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stTotalPpnMasukan" id="total_ppn_masukan_b2" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">PPN Keluaran (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stPpnKeluaran" id="ppn_keluaran_b2" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4 txt_kurang_bayar_b2" style="margin-top: 14px;margin-left: 20px" >Kurang Bayar (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stKurangBayar" id="kurang_bayar_b2" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">Perhitungan Kembali (RP) </label>
                                                            <div class="col-sm-5">
                                                                <s:textfield name="perhitunganPpnKd.stPerhitunganKembali" id="perhitungan_kembali_b2" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                            <div class="col-sm-1">
                                                                <a href="javascript:;" id="btnViewPerhitunganKembali">
                                                                    <img border="0"  style="margin-top: 12px" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px"><b class="txt_total_kurang_bayar_b2">Total Kurang Bayar (RP)</b> </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stTotalKurangBayar" id="total_kurang_bayar_b2" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr>
                                            </div>
                                            <br>
                                            <br>
                                            <table id="table2" class="table table-bordered table-striped sortTable">
                                            </table>
                                        </div>
                                        <div id="b3" class="tab-pane fade">
                                            <div class="row">
                                                <div style="margin-left: 50px">
                                                    <div class="row">
                                                        <h5><b><u>PERHITUNGAN SETORAN PPN</u></b></h5>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="row" style="display: none">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">LB Bulan Yll (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stLbBulanYll" id="lb_bulan_yll_b3" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">PPN Masukan (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stPpnMasukan" id="ppn_masukan_b3" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row" style="display: none">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">Total PPN Masukan (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stTotalPpnMasukan" id="total_ppn_masukan_b3" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">PPN Keluaran (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stPpnKeluaran" id="ppn_keluaran_b3" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">Kurang Bayar (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stKurangBayar" id="kurang_bayar_b3" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px">Perhitungan Kembali (RP) </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stPerhitunganKembali" id="perhitungan_kembali_b3" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <label class="control-label col-sm-4" style="margin-top: 14px;margin-left: 20px"><b>Total Kurang Bayar (RP)</b> </label>
                                                            <div class="col-sm-6">
                                                                <s:textfield name="perhitunganPpnKd.stTotalKurangBayar" id="total_kurang_bayar_b3" cssStyle="text-align: right;margin-top: 7px"
                                                                             readonly="true" cssClass="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr>
                                            </div>
                                            <br>
                                            <br>
                                            <table id="table3" class="table table-bordered table-striped sortTable">
                                            </table>
                                        </div>
                                    </div>
                                    <br>
                                    <br>
                                    <div class="row">
                                        <div style="margin-left: 80px">
                                            <div class="row">
                                                <h5><b><u>KETERANGAN</u></b></h5>
                                            </div>
                                            <div class="row">
                                                <h6><b>PENYERAHAN BARANG DAN JASA</b></h6>
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="row" style="display: none">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-offset-1 col-sm-4" style="margin-top: 14px">Ekspor (RP) </label>
                                                    <div class="col-sm-7">
                                                        <s:textfield name="perhitunganPpnKd.stPpnEkspor" id="ekspor" cssStyle="text-align: right;margin-top: 7px"
                                                                     readonly="true" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-offset-1 col-sm-4" style="margin-top: 14px">Dipungut Sendiri (RP) </label>
                                                    <div class="col-sm-7">
                                                        <s:textfield name="perhitunganPpnKd.stDipungutSendiri" id="dipungut_sendiri" cssStyle="text-align: right;margin-top: 7px"
                                                                     readonly="true" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row" style="display: none">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-offset-1 col-sm-4" style="margin-top: 14px">Dipungut Oleh Pemungut (RP) </label>
                                                    <div class="col-sm-7">
                                                        <s:textfield name="perhitunganPpnKd.stDipungutSendiri" id="dipungut_oleh_pemungut" cssStyle="text-align: right;margin-top: 7px"
                                                                     readonly="true" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row" style="display: none">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-offset-1 col-sm-4" style="margin-top: 14px">Tidak Dipungut (RP) </label>
                                                    <div class="col-sm-7">
                                                        <s:textfield name="perhitunganPpnKd.stTidakDipungut" id="tidak_dipungut" cssStyle="text-align: right;margin-top: 7px"
                                                                     readonly="true" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row" style="display: none">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-offset-1 col-sm-4" style="margin-top: 14px">Dibebaskan (RP) </label>
                                                    <div class="col-sm-7">
                                                        <s:textfield name="perhitunganPpnKd.dibebaskan" id="dibebaskan" cssStyle="text-align: right;margin-top: 7px"
                                                                     readonly="true" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-offset-1 col-sm-4" style="margin-top: 14px"><b>TOTAL A (RP) </b></label>
                                                    <div class="col-sm-7">
                                                        <s:textfield name="perhitunganPpnKd.stJumlahTerutangPpn" id="total_a" cssStyle="text-align: right;margin-top: 7px"
                                                                     readonly="true" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                        </div>
                                        <div class="col-md-offset-1 col-md-5">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-5" style="margin-top: 14px">Jasa RS (RP) </label>
                                                    <div class="col-sm-7">
                                                        <s:textfield name="perhitunganPpnKd.stJasaRs" id="jasa_rs" cssStyle="text-align: right;margin-top: 7px"
                                                                     readonly="true" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-5" style="margin-top: 14px">Obat Rawat Inap (RP) </label>
                                                    <div class="col-sm-7">
                                                        <s:textfield name="perhitunganPpnKd.stObatRawatInap" id="obat_rawat_inap" cssStyle="text-align: right;margin-top: 7px"
                                                                     readonly="true" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-5" style="margin-top: 14px"><b>Total B (RP) </b></label>
                                                    <div class="col-sm-7">
                                                        <s:textfield name="perhitunganPpnKd.stJumlahTidakTerutang" id="total_b" cssStyle="text-align: right;margin-top: 7px"
                                                                     readonly="true" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-5" style="margin-top: 14px"><b>Total A + B (RP) </b></label>
                                                    <div class="col-sm-7">
                                                        <s:textfield name="perhitunganPpnKd.stPenyerahanBarangDanJasa" id="total_a_b" cssStyle="text-align: right;margin-top: 7px"
                                                                     readonly="true" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <br>
                                    <div class="form-group col-md-offset-5">
                                        <br>
                                        <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                                                   formIds="saveAddPengajuanSetor" id="save" name="save"
                                                   onBeforeTopics="beforeProcessSave"
                                                   onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog"
                                                   onErrorTopics="errorDialog">
                                            <i class="fa fa-save"></i>
                                            Save
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger"
                                           href="addProsesPpnKantorPusat_pengajuanSetor.action">
                                            <i class="fa fa-arrow-left"></i> Go Back
                                        </a>
                                    </div>
                                    <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog"
                                               modal="true"
                                               resizable="false"
                                               height="250" width="600" autoOpen="false" title="Importing ...">
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
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                              'OK':function() {
                                                                        callSearch2();
                                                                      link();
                                                                   }
                                                            }"
                                    >
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Pengajuan Setor has been successfully created.
                                    </sj:dialog>

                                    <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                               resizable="false"
                                               height="250" width="600" autoOpen="false" title="Error Dialog"
                                               buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); }
                                                                    }"
                                    >
                                        <div class="alert alert-error fade in">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                     name="icon_error"> System Found : <p id="errorMessage"></p>
                                            </label>
                                        </div>
                                    </sj:dialog>

                                    <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog"
                                               modal="true" resizable="false"
                                               height="280" width="500" autoOpen="false" title="Warning"
                                               buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                    >
                                        <div class="alert alert-error fade in">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                     name="icon_error"> Please check this field :
                                                <br/>
                                                <center>
                                                    <div id="errorValidationMessage"></div>
                                                </center>
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
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>

<div class="modal fade" id="modal-detail-perhitungan">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail Perhitungan Kembali</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px"></label>
                        <div class="col-md-4">
                            <div class="text-center">DPP</div>
                        </div>
                        <div class="col-md-4">
                            <div class="text-center">PPN</div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">1. Total Pendapatan Apotek Rumah Sakit </label>
                        <div class="col-md-4">
                            <s:textfield id="dpp_pendapatan_rumah_sakit" cssClass="form-control pull-right" readonly="true" cssStyle="margin-top: 7px; text-align: right" />
                        </div>
                        <div class="col-md-4">
                            <s:textfield id="ppn_pendapatan_rumah_sakit" cssClass="form-control pull-right" readonly="true" cssStyle="margin-top: 7px; text-align: right" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">- Penyerahan yang Tidak Terutang PPN </label>
                        <div class="col-md-4">
                            <s:textfield id="dpp_penyerahan_tidak_terutang" cssClass="form-control pull-right" readonly="true" cssStyle="margin-top: 7px; text-align: right" />
                        </div>
                        <div class="col-md-4">
                            <s:textfield id="ppn_penyerahan_tidak_terutang" cssClass="form-control pull-right" readonly="true" cssStyle="margin-top: 7px; text-align: right" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">- Penyerahan yang Terutang PPN </label>
                        <div class="col-md-4">
                            <s:textfield id="dpp_penyerahan_terutang" cssClass="form-control pull-right" readonly="true" cssStyle="margin-top: 7px; text-align: right" />
                        </div>
                        <div class="col-md-4">
                            <s:textfield id="ppn_penyerahan_terutang" cssClass="form-control pull-right" readonly="true" cssStyle="margin-top: 7px; text-align: right" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">2. PPN Masukan Yang Telah Dikreditkan </label>
                        <div class="col-md-4">
                            <s:textfield id="dpp_ppn_masukan_yang_telah_dikreditkan" cssClass="form-control pull-right" readonly="true" cssStyle="margin-top: 7px; text-align: right" />
                        </div>
                        <div class="col-md-4">
                            <s:textfield id="ppn_ppn_masukan_yang_telah_dikreditkan" cssClass="form-control pull-right" readonly="true" cssStyle="margin-top: 7px; text-align: right" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">3. PPN Masukan Yang tdk dpt dikreditkan </label>
                        <div class="col-md-offset-4 col-md-4">
                            <s:textfield id="ppn_masukan_yang_tidak_dapat_dikreditkan" cssClass="form-control pull-right" readonly="true" cssStyle="margin-top: 7px; text-align: right" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">4. Telah Diperhitungkan Kembali PPN Masukan yg tdk dpt dikreditkan sebelumya </label>
                        <div class="col-md-offset-4 col-md-4">
                            <s:textfield id="ppn_telah_diperhitungkan_kembali" cssClass="form-control pull-right" readonly="true" cssStyle="margin-top: 7px; text-align: right" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">5. Hasil Perhitungan Kembali PPN Masukan yang tidak dapat dikreditkan </label>
                        <div class="col-md-offset-4 col-md-4">
                            <s:textfield id="ppn_hasil_perhitungan_kembali" cssClass="form-control" readonly="true" cssStyle="margin-top: 7px ; text-align: right" />
                        </div>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<script>
    $('#btnViewPerhitunganKembali').click(function () {
        loadPerhitunganKembali();
        $("#modal-detail-perhitungan").modal('show');
    });
    window.loadPerhitunganKembali = function () {
        dwr.engine.setAsync(false);
        PengajuanSetorAction.searchDataSessionPerhitunganKembali(function (data) {
            $('#dpp_pendapatan_rumah_sakit').val(data.stDppTotalPendapatanRumahSakit);
            $('#ppn_pendapatan_rumah_sakit').val(data.stPpnTotalPendapatanRumahSakit);
            $('#dpp_penyerahan_tidak_terutang').val(data.stDppPenyerahanTidakTerutangPpn);
            $('#ppn_penyerahan_tidak_terutang').val(data.stPpnPenyerahanTidakTerutangPpn);
            $('#dpp_penyerahan_terutang').val(data.stDppPenyerahanTerutangPpn);
            $('#ppn_penyerahan_terutang').val(data.stPpnPenyerahanTerutangPpn);
            $('#dpp_ppn_masukan_yang_telah_dikreditkan').val(data.stDppPpnMasukanYangTelahDikreditkan);
            $('#ppn_ppn_masukan_yang_telah_dikreditkan').val(data.stPpnPpnMasukanYangTelahDikreditkan);
            $('#ppn_masukan_yang_tidak_dapat_dikreditkan').val(data.stPpnPpnMasukanYangTidakDapatDikreditkan);
            $('#ppn_telah_diperhitungkan_kembali').val(data.stPpnTelahDiperhitungkanKembaliPpnMasukan);
            $('#ppn_hasil_perhitungan_kembali').val(data.stPpnHasilPerhitunganKembaliPpn);
        });
    };

    window.loadSessionProsesPpn = function () {
        $('#table1').find('tbody').remove();
        $('#table1').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionProsesPpn(function (listdata) {

            tmp_table = "<thead style='font-size: 14px;' ><tr>" +
                "<th rowspan='2' style='text-align: center; background-color:  #90ee90'>No.</th>" +
                "<th rowspan='2' style='text-align: center; background-color:  #90ee90'>Unit Kerja</th>" +
                "<th colspan='2' style='text-align: center; background-color:  #90ee90''>Laporan Unit</th>" +
                "<th colspan='2' style='text-align: center; background-color:  #90ee90'>Koreksi</th>" +
                "<th colspan='2' style='text-align: center; background-color:  #90ee90'>Diambil Alih Kantor Pusat</th>" +
                "</tr>";
            tmp_table += "<tr>" +
                "<th style='text-align: center; background-color:  #90ee90''>Keluaran ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Masukan ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keluaran ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Masukan ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keluaran ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Masukan ( RP )</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                '<td align="center">' + item.no + '</td>' +
                '<td >' + item.branchName + '</td>' +
                '<td align="right">' + item.stKeluaranUnit + '</td>' +
                '<td align="right">' + item.stMasukanUnit + '</td>' +
                '<td align="right">' + item.stKeluaranKoreksi + '</td>' +
                '<td align="right">' + item.stMasukanKoreksi + '</td>' +
                '<td align="right">' + item.stKeluaranDiambilKp + '</td>' +
                '<td align="right">' + item.stMasukanDiambilKp + '</td>' +
                "</tr>";
            });
            $('#table1').append(tmp_table);
        });
    };

    window.loadSessionProsesPpnB2 = function () {
        $('#table2').find('tbody').remove();
        $('#table2').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionProsesPpnB2(function (listdata) {

            tmp_table = "<thead style='font-size: 14px;' ><tr>" +
                "<th rowspan='2' style='text-align: center; background-color:  #90ee90'>No.</th>" +
                "<th rowspan='2' style='text-align: center; background-color:  #90ee90'>Unit Kerja</th>" +
                "<th colspan='2' style='text-align: center; background-color:  #90ee90''>Laporan Unit</th>" +
                "<th colspan='2' style='text-align: center; background-color:  #90ee90'>Koreksi</th>" +
                "<th colspan='2' style='text-align: center; background-color:  #90ee90'>Diambil Alih Kantor Pusat</th>" +
                "</tr>";
            tmp_table += "<tr>" +
                "<th style='text-align: center; background-color:  #90ee90''>Keluaran ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Masukan ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keluaran ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Masukan ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keluaran ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Masukan ( RP )</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + item.no + '</td>' +
                    '<td >' + item.branchName + '</td>' +
                    '<td align="right">' + item.stKeluaranUnit + '</td>' +
                    '<td align="right">' + item.stMasukanUnit + '</td>' +
                    '<td align="right">' + item.stKeluaranKoreksi + '</td>' +
                    '<td align="right">' + item.stMasukanKoreksi + '</td>' +
                    '<td align="right">' + item.stKeluaranDiambilKp + '</td>' +
                    '<td align="right">' + item.stMasukanDiambilKp + '</td>' +
                    "</tr>";
            });
            $('#table2').append(tmp_table);
        });
    };
    window.loadSessionProsesPpnB3 = function () {
        $('#table3').find('tbody').remove();
        $('#table3').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionProsesPpnB3(function (listdata) {
            tmp_table = "<thead style='font-size: 14px;' ><tr>" +
                "<th rowspan='2' style='text-align: center; background-color:  #90ee90'>No.</th>" +
                "<th rowspan='2' style='text-align: center; background-color:  #90ee90'>Unit Kerja</th>" +
                "<th colspan='2' style='text-align: center; background-color:  #90ee90''>Laporan Unit</th>" +
                "<th colspan='2' style='text-align: center; background-color:  #90ee90'>Koreksi</th>" +
                "<th colspan='2' style='text-align: center; background-color:  #90ee90'>Diambil Alih Kantor Pusat</th>" +
                "</tr>";
            tmp_table += "<tr>" +
                "<th style='text-align: center; background-color:  #90ee90''>Keluaran ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Masukan ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keluaran ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Masukan ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keluaran ( RP )</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Masukan ( RP )</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + item.no + '</td>' +
                    '<td >' + item.branchName + '</td>' +
                    '<td align="right">' + item.stKeluaranUnit + '</td>' +
                    '<td align="right">' + item.stMasukanUnit + '</td>' +
                    '<td align="right">' + item.stKeluaranKoreksi + '</td>' +
                    '<td align="right">' + item.stMasukanKoreksi + '</td>' +
                    '<td align="right">' + item.stKeluaranDiambilKp + '</td>' +
                    '<td align="right">' + item.stMasukanDiambilKp + '</td>' +
                    "</tr>";
            });
            $('#table3').append(tmp_table);
        });
    };
    window.loadSessionHasilProsesPpn = function () {
        PengajuanSetorAction.searchDataSessionHasilProsesPpnPenyerahanBarangDanJasa(function (data) {
            $('#ekspor').val(data.stPpnEkspor);
            $('#dipungut_sendiri').val(data.stDipungutSendiri);
            $('#dipungut_oleh_pemungut').val(data.stDipungutOlehPemungut);
            $('#tidak_dipungut').val(data.stTidakDipungut);
            $('#dibebaskan').val(data.stDibebaskan);
            $('#total_a').val(data.stJumlahTerutangPpn);
            $('#jasa_rs').val(data.stJasaRs);
            $('#obat_rawat_inap').val(data.stObatRawatInap);
            $('#total_b').val(data.stJumlahTidakTerutang);
            $('#total_a_b').val(data.stPenyerahanBarangDanJasa);
        });
    };

    window.loadSessionHasilProsesPpnNormal = function () {
        PengajuanSetorAction.searchDataSessionHasilProsesPpn(function (data) {
            $('#ppn_masukan_normal').val(data.stPpnMasukan);
            $('#total_ppn_masukan_normal').val(data.stTotalPpnMasukan);
            $('#ppn_keluaran_normal').val(data.stPpnKeluaran);
            $('#kurang_bayar_normal').val(data.stKurangBayar);
            $('#lb_bulan_yll_normal').val(data.stLbBulanYll);
            $('#perhitungan_kembali_normal').val(data.stPerhitunganKembali);
            $('#total_kurang_bayar_normal').val(data.stTotalKurangBayar);
        });
    };

    window.loadSessionHasilProsesPpnB2 = function () {
        PengajuanSetorAction.searchDataSessionHasilProsesPpnB2(function (data) {
            $('#ppn_masukan_b2').val(data.stPpnMasukan);
            $('#total_ppn_masukan_b2').val(data.stTotalPpnMasukan);
            $('#ppn_keluaran_b2').val(data.stPpnKeluaran);
            $('#kurang_bayar_b2').val(data.stKurangBayar);
            $('#lb_bulan_yll_b2').val(data.stLbBulanYll);
            $('#perhitungan_kembali_b2').val(data.stPerhitunganKembali);
            $('#total_kurang_bayar_b2').val(data.stTotalKurangBayar);
            $('.txt_kurang_bayar_b2').html("Lebih Bayar (RP)");
            $('.txt_total_kurang_bayar_b2').html("Total Lebih Bayar (RP)");
            if (data.statusB2=="kurang_bayar"){
                $('.txt_kurang_bayar_b2').html("Kurang Bayar (RP)");
                $('.txt_total_kurang_bayar_b2').html("Total Kurang Bayar (RP)");
            }
        });
    };

    window.loadSessionHasilProsesPpnB3 = function () {
        PengajuanSetorAction.searchDataSessionHasilProsesPpnB3(function (data) {
            $('#ppn_masukan_b3').val(data.stPpnMasukan);
            $('#total_ppn_masukan_b3').val(data.stTotalPpnMasukan);
            $('#ppn_keluaran_b3').val(data.stPpnKeluaran);
            $('#kurang_bayar_b3').val(data.stKurangBayar);
            $('#lb_bulan_yll_b3').val(data.stLbBulanYll);
            $('#perhitungan_kembali_b3').val(data.stPerhitunganKembali);
            $('#total_kurang_bayar_b3').val(data.stTotalKurangBayar);
        });
    };

    $('document').ready(function () {
        loadSessionHasilProsesPpn();
        loadSessionHasilProsesPpnNormal();
        loadSessionHasilProsesPpnB2();
        loadSessionHasilProsesPpnB3();
        loadSessionProsesPpn();
        loadSessionProsesPpnB2();
        loadSessionProsesPpnB3();
        $('#table1').DataTable({
            paging: false,
            searching: false,
            "bDestroy": true,
            "ordering": false,
            "order": [[0, "asc"]]
        });
        $('#table2').DataTable({
            paging: false,
            "bDestroy": true,
            searching: false,
            "ordering": false,
            "order": [[0, "asc"]]
        });
        $('#table3').DataTable({
            paging: false,
            searching: false,
            "bDestroy": true,
            "ordering": false,
            "order": [[0, "asc"]]
        });
    });
</script>