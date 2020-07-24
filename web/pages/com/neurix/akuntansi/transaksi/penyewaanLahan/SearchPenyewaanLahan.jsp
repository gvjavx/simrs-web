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
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PenyewaanLahanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_penyewaanLahan'/>";
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
            Penyewaan Lahan
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Search Penyewaan Lahan</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="penyewaanLahanForm" method="post"  theme="simple" namespace="/penyewaanLahan" action="search_penyewaanLahan.action" cssClass="form-horizontal">
                                        <s:hidden name="penyewaanLahan.branchId" id="branchIdAwal" disabled="true" />
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
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test='penyewaanLahan.branchId == "KP"'>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="penyewaanLahan.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="penyewaanLahan.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId" name="penyewaanLahan.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Penyewaan Lahan ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="penyewaanLahanId" name="penyewaanLahan.penyewaanLahanId" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama Penyewa :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="nomorJurnal" name="penyewaanLahan.noJurnal" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tanggal :</small></label>
                                                </td>
                                                <td>
                                                    <div class="input-group date">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tgl1" name="penyewaanLahan.stTanggalDari" cssClass="form-control pull-right"
                                                        />
                                                        <div class="input-group-addon">
                                                            s/d
                                                        </div>
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tgl2" name="penyewaanLahan.stTanggalSelesai" cssClass="form-control pull-right"
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
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="penyewaanLahanForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <a id="btnAddPenyewaanLahan" href="javascript:void(0)" class="btn btn-success" ><i class="fa fa-plus"></i> Add Penyewaan Lahan</a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_penyewaanLahan"/>'">
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
                                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Penyewaan Lahan</h3>
                                            </div>
                                            <div class="box-body">
                                                <table id="myTable" class="tablePenyewaanLahan table table-bordered table-striped">
                                                    <thead >
                                                    <tr bgcolor="#90ee90">
                                                        <td>Penyewaan Lahan ID</td>
                                                        <td>Unit</td>
                                                        <td>Tanggal Bayar</td>
                                                        <td>Nama Penyewa</td>
                                                        <td>Jumlah Bayar</td>
                                                        <td align="center">View</td>
                                                        <td align="center">Batal</td>
                                                        <td align="center">Posting</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:iterator value="#session.listOfResult" var="row">
                                                        <tr>
                                                            <td style="text-align: center"><s:property value="penyewaanLahanId"/></td>
                                                            <td style="text-align: center"><s:property value="branchName"/></td>
                                                            <td style="text-align: center"><s:property value="stTanggalBayar"/></td>
                                                            <td><s:property value="namaPenyewa"/></td>
                                                            <td style="text-align: right"><s:property value="stNilai"/></td>
                                                            <td align="center">
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.penyewaanLahanId}"/>" class="item-view">
                                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                                                                </a>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.cancelFlag == "Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_batal">
                                                                </s:if>
                                                                <s:elseif test='#row.approvalFlag == "Y"'>
                                                                </s:elseif>
                                                                <s:else>
                                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.penyewaanLahanId}"/>" class="item-delete">
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
                                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.penyewaanLahanId}"/>" class="item-posting">
                                                                        <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" name="icon_posting">
                                                                    </a>
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

<div class="modal fade" id="modal-add-sewa-lahan">
    <div class="modal-dialog modal-flat" style="width: 1000px">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Add Penyewaan Lahan</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Unit</label>
                                <div class="col-md-6">
                                    <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                    <s:select list="#initComboBranch.listOfComboBranch" id="mod_add_branch_id"
                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Nama Penyewa</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_add_nama_penyewa" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Tanggal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_add_tanggal_bayar" cssClass="form-control"  />
                                    <script>
                                        $('#mod_add_tanggal_bayar').datepicker({
                                            setDate: new Date(),
                                            autoclose: true,
                                            changeMonth: true,
                                            changeYear:true,
                                            dateFormat:'dd-mm-yy'
                                        });
                                        $("#mod_add_tanggal_bayar").datepicker("setDate", new Date());

                                    </script>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Nilai</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_add_nilai" cssClass="form-control" onkeyup="formatRupiah2(this)" cssStyle="text-align: right" />
                                    <br>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" >Metode Bayar</label>
                                    <div class="col-md-2">
                                        <select id="mod_add_metode_bayar" class="form-control" onchange="pilihMetode(this.value)">
                                            <option value="" >[Select One]</option>
                                            <option value="tunai">Tunai</option>
                                            <option value="transfer">Transfer</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4" id="pilih_bank">
                                        <select class="form-control" id="mod_add_bank">
                                            <option value="">[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px">Keterangan</label>
                                <div class="col-md-6">
                                    <s:textarea id="mod_add_keterangan" rows="3" cssClass="form-control" cssStyle="margin-top: 14px"/>
                                    <br>
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnSaveAddPenyewaanLahan"><i class="fa fa-plus"></i> Add</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-posting-jurnal">
    <div class="modal-dialog modal-flat" style="width: 1000px">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> </h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" >Penyewaan Lahan ID</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_penyewaan_lahan_id" cssClass="form-control" readonly="true" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Unit</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_branch_id" cssClass="form-control" readonly="true" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Nama Penyewa</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_nama_penyewa" cssClass="form-control" readonly="true" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Tanggal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_tanggal_bayar" cssClass="form-control" readonly="true"  />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Nilai</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_nilai" cssClass="form-control" readonly="true" cssStyle="text-align: right" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Nilai PPN</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_nilai_ppn" cssClass="form-control" readonly="true" cssStyle="text-align: right" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Nilai Netto</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_nilai_netto" cssClass="form-control" readonly="true" cssStyle="text-align: right" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Metode Bayar</label>
                                <div class="col-md-2">
                                    <s:textfield id="mod_view_metode_bayar" cssClass="form-control" readonly="true" />
                                </div>
                                <div class="col-md-4">
                                    <s:textfield id="mod_view_bank" cssClass="form-control" readonly="true" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px">Keterangan</label>
                                <div class="col-md-6">
                                    <s:textarea id="mod_view_keterangan" rows="3" cssClass="form-control" cssStyle="margin-top: 14px"  readonly="true"/>
                                    <br>
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnPostingJurnal"><i class="fa fa-arrow-right"></i> Posting</button>
                <button type="button" class="btn btn-danger" id="btnCancelPenyewaan"><i class="fa fa-close"></i> Batalkan</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#pilih_bank').hide();
        $('.tablePenyewaanLahan').on('click', '.item-view', function() {
            var penyewaanLahanId = $(this).attr('data');
            $('#mod_view_penyewaan_lahan_id').val(penyewaanLahanId);
            PenyewaanLahanAction.getForModalPopUp(penyewaanLahanId,function (data) {
                $('#mod_view_branch_id').val(data.branchName);
                $('#mod_view_nama_penyewa').val(data.namaPenyewa);
                $('#mod_view_tanggal_bayar').val(data.stTanggalBayar);
                $('#mod_view_nilai').val(data.stNilai);
                $('#mod_view_nilai_ppn').val(data.stNilaiPpn);
                $('#mod_view_nilai_netto').val(data.stNilaiNetto);
                $('#mod_view_metode_bayar').val(data.metodeBayar);
                $('#mod_view_bank').val(data.bankName);
                $('#mod_view_keterangan').val(data.keterangan);
            });
            $("#btnPostingJurnal").hide();
            $("#btnCancelPenyewaan").hide();
            $("#modal-posting-jurnal").find('.modal-title').text('View Penyewaan Lahan');
            $("#modal-posting-jurnal").modal('show');
        });

        $('.tablePenyewaanLahan').on('click', '.item-posting', function() {
            var penyewaanLahanId = $(this).attr('data');
            $('#mod_view_penyewaan_lahan_id').val(penyewaanLahanId);
            PenyewaanLahanAction.getForModalPopUp(penyewaanLahanId,function (data) {
                $('#mod_view_branch_id').val(data.branchName);
                $('#mod_view_nama_penyewa').val(data.namaPenyewa);
                $('#mod_view_tanggal_bayar').val(data.stTanggalBayar);
                $('#mod_view_nilai').val(data.stNilai);
                $('#mod_view_nilai_ppn').val(data.stNilaiPpn);
                $('#mod_view_nilai_netto').val(data.stNilaiNetto);
                $('#mod_view_metode_bayar').val(data.metodeBayar);
                $('#mod_view_bank').val(data.bankName);
                $('#mod_view_keterangan').val(data.keterangan);
            });
            $("#btnPostingJurnal").show();
            $("#btnCancelPenyewaan").hide();
            $("#modal-posting-jurnal").find('.modal-title').text('Posting Penyewaan Lahan');
            $("#modal-posting-jurnal").modal('show');
        });

        $('.tablePenyewaanLahan').on('click', '.item-delete', function() {
            var penyewaanLahanId = $(this).attr('data');
            $('#mod_view_penyewaan_lahan_id').val(penyewaanLahanId);
            PenyewaanLahanAction.getForModalPopUp(penyewaanLahanId,function (data) {
                $('#mod_view_branch_id').val(data.branchName);
                $('#mod_view_nama_penyewa').val(data.namaPenyewa);
                $('#mod_view_tanggal_bayar').val(data.stTanggalBayar);
                $('#mod_view_nilai').val(data.stNilai);
                $('#mod_view_nilai_ppn').val(data.stNilaiPpn);
                $('#mod_view_nilai_netto').val(data.stNilaiNetto);
                $('#mod_view_metode_bayar').val(data.metodeBayar);
                $('#mod_view_bank').val(data.bankName);
                $('#mod_view_keterangan').val(data.keterangan);
            });
            $("#btnPostingJurnal").hide();
            $("#btnCancelPenyewaan").show();
            $("#modal-posting-jurnal").find('.modal-title').text('Cancel Penyewaan Lahan');
            $("#modal-posting-jurnal").modal('show');
        });

        $('#btnPostingJurnal').click(function () {
            var penyewaanLahanId =  $('#mod_view_penyewaan_lahan_id').val();
            if (confirm("apakah anda ingin memposting transaksi dengan Penyewaan Lahan ID "+penyewaanLahanId +" ?")){
                PenyewaanLahanAction.postingJurnal(penyewaanLahanId,function (data) {
                    alert(data);
                    window.location.reload();
                })
            }
        });

        $('#btnCancelPenyewaan').click(function () {
            var penyewaanLahanId =  $('#mod_view_penyewaan_lahan_id').val();
            if (confirm("apakah anda ingin membatalkan transaksi dengan Penyewaan Lahan ID "+penyewaanLahanId +" ?")){
                PenyewaanLahanAction.cancelPenyewaanLahan(penyewaanLahanId,function (data) {
                    alert(data);
                    window.location.reload();
                })
            }
        });

        $('#btnAddPenyewaanLahan').click(function () {
            selectPembayaran();
            var branchId= $('#branchId').val();
            if (branchIdAwal!="KP"){
                $('#mod_add_branch_id').attr("disabled", true);
                $('#mod_add_branch_id').val(branchId);
            }else{
                $('#mod_add_branch_id').attr("disabled", false);
            }
            $('#modal-add-sewa-lahan').modal("show");
        });

        $('#btnSaveAddPenyewaanLahan').click(function () {
            var branchId = $('#mod_add_branch_id').val();
            var namaPenyewa = $('#mod_add_nama_penyewa').val();
            var tanggal = $('#mod_add_tanggal_bayar').val();
            var nilai = $('#mod_add_nilai').val();
            var metodeBayar = $('#mod_add_metode_bayar').val();
            var bank = $('#mod_add_bank').val();
            var keterangan = $('#mod_add_keterangan').val();

            if (branchId!=""&&namaPenyewa!=""&&tanggal!=""&&nilai!=""&&metodeBayar!=""&&keterangan!=""){
                if (metodeBayar=="transfer"&&bank==""){
                    alert("Pilih bank terlebih dahulu");
                } else{
                    if (confirm("Apakah anda ingin menambahkan data ini ?")){
                        PenyewaanLahanAction.saveAdd(branchId,namaPenyewa,tanggal,nilai,metodeBayar,keterangan,bank,function () {
                            alert("Berhasil Menambahkan Data");
                            window.location.reload();
                        })
                    }
                }
            } else{
                var msg="";
                if (branchId==""){
                    msg+="Unit masih belum dipilih \n";
                }
                if (namaPenyewa==""){
                    msg+="Nama penyewa masih kosong \n";
                }
                if (tanggal==""){
                    msg+="Tanggal masih kosong \n";
                }
                if (nilai==""){
                    msg+="Nilai masih kosong \n";
                }
                if (metodeBayar==""){
                    msg+="Metode Bayar masih kosong \n";
                }
                if (keterangan==""){
                    msg+="Keterangan masih kosong \n";
                }
                alert(msg);
            }
        })
    });
    function pilihMetode(val){
        if(val != ''){
            if(val == 'transfer'){
                $('#pilih_bank').show();
            }else{
                $('#pilih_bank').hide();
            }
        }
    }
    function selectPembayaran(){
        var option = '<option value="">[Select One]</option>';
        KasirRawatJalanAction.getListPembayaran(function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.coa+'">'+item.pembayaranName+'</option>';
                });
                $('#mod_add_bank').html(option);
            }else{
                $('#mod_add_bank').html(option);
            }
        });
    }
</script>

