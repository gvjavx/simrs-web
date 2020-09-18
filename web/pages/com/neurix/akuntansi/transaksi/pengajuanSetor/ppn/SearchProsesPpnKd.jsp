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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PengajuanSetorAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <style>
        .modal-backdrop {
            z-index: -1;
        }
    </style>
    <style>
        .pagebanner{
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <script type='text/javascript'>
        function link(){
            window.location.href="<s:url action='initFormProsesPpnKd_pengajuanSetor'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Proses PPN Kantor Direksi
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Search Proses PPN Kantor Direksi </h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="pengajuanSetorForm" method="post"  theme="simple" namespace="/pengajuanSetor" action="searchProsesPpnKd_pengajuanSetor.action" cssClass="form-horizontal">
                                        <table>
                                            <tr>
                                                <td width="10%" align="center">
                                                    <%@ include file="/pages/common/message.jsp" %>
                                                </td>
                                            </tr>
                                        </table>
                                        <table >
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Periode :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                                  id="bulan1" name="perhitunganPpnKd.bulan"
                                                                  headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahun1"
                                                                  name="perhitunganPpnKd.tahun" required="true" headerKey=""
                                                                  headerValue="[Select one]"/>
                                                    </table>
                                                </td>

                                                <td>
                                                    <table>
                                                        <h4>s/d </h4>
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                                  id="bulan2" name="perhitunganPpnKd.bulan1"
                                                                  headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahun2"
                                                                  name="perhitunganPpnKd.tahun1" required="true" headerKey=""
                                                                  headerValue="[Select one]"/>
                                                    </table>
                                                </td>
                                                <script>
                                                    var dt = new Date();
                                                    $('#bulan1').val("01");
                                                    $('#tahun1').val(dt.getFullYear());
                                                    $('#bulan2').val(("0" + (dt.getMonth() + 1)).slice(-2));
                                                    $('#tahun2').val(dt.getFullYear());
                                                </script>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <s:if test='pengajuanSetor.branchId == "KP"'>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="pengajuanSetorForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <a href="addProsesPpnKantorPusat_pengajuanSetor.action" class="btn btn-success" ><i class="fa fa-plus"></i> Proses PPN Kanpus</a>
                                                    </td>
                                                    </s:if>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initFormProsesPpnKd_pengajuanSetor"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <div style="text-align: left !important;">
                                            <div class="box-header with-border"></div>
                                            <div class="box-header with-border">
                                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pengajuan Setor PPN</h3>
                                            </div>
                                            <div class="box-body">
                                                <table id="myTable" class="tableProsesPpnKd table table-bordered table-striped">
                                                    <thead >
                                                    <tr bgcolor="#90ee90">
                                                        <td>Bulan</td>
                                                        <td>Tahun</td>
                                                        <td align="center">View</td>
                                                        <td align="center">Batal</td>
                                                        <td align="center">Posting</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:iterator value="#session.listOfResult" var="row">
                                                        <tr>
                                                            <td style="text-align: center"><s:property value="namaBulan"/></td>
                                                            <td style="text-align: center"><s:property value="tahun"/></td>
                                                            <td align="center">
                                                                <s:a action="view_pengajuanSetor.action">
                                                                    <s:param name="bulan"><s:property value="#attr.row.bulan"/> </s:param>
                                                                    <s:param name="tahun"><s:property value="#attr.row.tahun"/> </s:param>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                                </s:a>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.cancelFlag == "Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_batal">
                                                                </s:if>
                                                                <s:elseif test='#row.approvalFlag == "Y"'>
                                                                </s:elseif>
                                                                <s:else>
                                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.bulan}"/>" class="item-delete">
                                                                        <img border="0" src="<s:url value="/pages/images/icons8-trash-can-25.png"/>" name="icon_delete">
                                                                    </a>
                                                                </s:else>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.cancelFlag == "Y"'>
                                                                </s:if>
                                                                <s:elseif test='#row.approvalFlag == "Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_batal">
                                                                </s:elseif>
                                                                <s:else>
                                                                    <s:a action="view_pengajuanSetor.action">
                                                                        <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>" tahun="<s:property value="%{#attr.row.tahun}"/>" class="item-posting">
                                                                            <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" name="icon_posting">
                                                                        </a>
                                                                    </s:a>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                    </s:iterator>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
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
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Posting Jurnal</h4>
            </div>
            <div class="modal-body">
                <s:hidden id="mod_bulan" />
                <s:hidden id="mod_tahun" />
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">Jumlah PPN Keluaran </label>
                        <div class="col-md-7">
                            <s:textfield id="mod_jumlah_ppn_keluaran" cssClass="form-control" readonly="true" cssStyle="margin-top: 7px ; text-align: right" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">Jumlah PPN Masukan B2</label>
                        <div class="col-md-7">
                            <s:textfield id="mod_jumlah_ppn_masukan_b2" cssClass="form-control" readonly="true" cssStyle="margin-top: 7px ; text-align: right" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">Jumlah Perhitungan Kembali</label>
                        <div class="col-md-7">
                            <s:textfield id="mod_jumlah_perhitungan_kembali" cssClass="form-control" readonly="true" cssStyle="margin-top: 7px ; text-align: right" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">Jumlah Kurang Bayar</label>
                        <div class="col-md-7">
                            <s:textfield id="mod_jumlah_kurang_bayar" cssClass="form-control" readonly="true" cssStyle="margin-top: 7px ; text-align: right" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">Kas </label>
                        <div class="col-md-7">
                            <s:select list="#{'':''}" cssStyle="margin-top: 7px" id="mod_bank" cssClass="form-control" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4" style="margin-top: 14px">Keterangan </label>
                        <div class="col-md-7">
                            <s:textarea id="mod_keterangan" rows="3" cssStyle="margin-top: 7px" onkeypress="$(this).css('border','')" cssClass="form-control"/>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnPostingJurnal"><i class="fa fa-arrow-right"></i> Posting</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
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
                <%--<button type="button" class="btn btn-sm btn-success" id="ok_con"><i class="fa fa-check"></i> Ok--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<script>
    function showDialog(tipe) {
        if (tipe == "loading"){
            $("#modal-loading-dialog").modal('show');
        }
        if (tipe == "error"){
            $("#modal-loading-dialog").modal('show');
            $("#waiting-content").hide();
            $("#warning_fin_waiting").show();
//            $("#msg_fin_error_waiting").text("Error. perbaikan");
        }
        if (tipe == "success"){
            $("#modal-loading-dialog").modal('hide');
            $("#modal-success-dialog").modal('show');
            window.location.reload();
        }
    };

    $('.tableProsesPpnKd').on('click', '.item-posting', function() {
        var bulan = $(this).attr('bulan');
        var tahun = $(this).attr('tahun');

        $('#mod_bulan').val(bulan);
        $('#mod_tahun').val(tahun);
        PengajuanSetorAction.getModalPostingPpn(bulan,tahun,function (data) {
            $('#mod_jumlah_ppn_keluaran').val(data.stPpnKeluaran);
            $('#mod_jumlah_ppn_masukan_b2').val(data.stTotalPpnMasukan);
            $('#mod_jumlah_kurang_bayar').val(data.stTotalKurangBayar);
            $('#mod_jumlah_perhitungan_kembali').val(data.stPerhitunganKembali);
        });
        selectPembayaran();
        $("#modal-posting-jurnal").modal('show');
    });

    $('#btnPostingJurnal').click(function () {
        var bulan =  $('#mod_bulan').val();
        var tahun =  $('#mod_tahun').val();
        var kas = $('#mod_bank').val();
        var keterangan = $('#mod_keterangan').val();

        if (bulan!=''&&tahun!=''&&kas!=''&&keterangan!=''){
            if (confirm("apakah anda ingin memposting PPN ini ?")){
                showDialog("loading");
                dwr.engine.setAsync(true);

                PengajuanSetorAction.postingJurnalProsesPpn(bulan,tahun,kas,keterangan,function (data) {
                    dwr.engine.setAsync(false);
                    showDialog("success");
                })
            }
        } else{
            var msg="";
            if (bulan==""){
                msg+="Bulan tidak boleh kosong \n";
            }
            if (tahun==""){
                msg+="Tahun tidak boleh kosong \n";
            }
            if (kas==""){
                msg+="Kas tidak boleh kosong \n";
            }
            if (keterangan==""){
                msg+="Keterangan tidak boleh kosong \n";
            }
            alert(msg);
        }
    });
    $('#btnBatalPengajuan').click(function () {
        var pengajuanSetorId =  $('#mod_pengajuan_setor_id').val();
        if (confirm("apakah anda ingin membatalkan pengajuan setor PPN dengan ID Pengajuan Setor "+pengajuanSetorId +" ?")){
            PengajuanSetorAction.batalkanPengajuan(pengajuanSetorId,function (listdata) {
                alert(listdata);
                window.location.reload();
            })
        }
    });

    $('.tableProsesPpnKd').on('click', '.item-delete', function() {
        var bulan = $(this).attr('bulan');
        var tahun = $(this).attr('tahun');
        PengajuanSetorAction.cancelProsesPpn(bulan,tahun,function (data) {
            alert(data);
            window.location.reload();
        });
    });

    function selectPembayaran(){
        var option = '<option value="">[Select One]</option>';
        KasirRawatJalanAction.getListPembayaran(function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.coa+'">'+item.pembayaranName+'</option>';
                });
                $('#mod_bank').html(option);
            }else{
                $('#mod_bank').html(option);
            }
        });
    }
</script>