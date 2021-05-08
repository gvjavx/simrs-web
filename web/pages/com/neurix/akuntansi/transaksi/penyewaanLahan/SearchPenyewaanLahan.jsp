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
<script type='text/javascript' src='<s:url value="/dwr/interface/PembayaranUtangPiutangAction.js"/>'></script>

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
                        <div class="form-group">
                            <s:form id="penyewaanLahanForm" method="post"  theme="simple" namespace="/penyewaanLahan" action="search_penyewaanLahan.action" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Unit </label>
                                    <div class="col-sm-4">
                                        <s:if test='penyewaanLahan.branchId == "01"'>
                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="penyewaanLahan.branchId"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>
                                        </s:if>
                                        <s:else>
                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="penyewaanLahan.branchId" disabled="true"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>
                                            <s:hidden id="branchId" name="penyewaanLahan.branchId" />
                                        </s:else>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Penyewaan Lahan ID</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="penyewaanLahanId" name="penyewaanLahan.penyewaanLahanId"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Penyewa</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboVendor" namespace="/masterVendor" name="initComboVendor_masterVendor">
                                            <s:param name="tipe">lahan</s:param>
                                        </s:action>
                                        <s:select list="#initComboVendor.listOfComboVendor" id="namaPenyewa" cssStyle="width: 100%;font-size: 10px"
                                                  listKey="nomorMaster" listValue="nama" headerKey="" headerValue="" name="penyewaanLahan.namaPenyewa" cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal</label>
                                    <div class="col-sm-4">
                                        <div class="input-group date"  style="margin-top: 7px">
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
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="penyewaanLahanForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a id="btnAddPenyewaanLahan" href="javascript:void(0)" class="btn btn-success" ><i class="fa fa-plus"></i> Add Penyewaan Lahan</a>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_penyewaanLahan"/>'">
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
                            <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Penyewaan Lahan</h3>
                        </div>
                        <div class="box-body">
                            <table id="tablePenyewaanLahan" class="tablePenyewaanLahan table table-bordered table-striped">
                                <thead >
                                <tr bgcolor="#90ee90">
                                    <td>Penyewaan Lahan ID</td>
                                    <td>Unit</td>
                                    <td>Tanggal Bayar</td>
                                    <td>Nama Penyewa</td>
                                    <td>Netto (RP) </td>
                                    <td align="center">View</td>
                                    <td align="center">Batal</td>
                                    <td align="center">Posting</td>
                                    <td align="center">Cetak Bukti</td>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="#session.listOfResult" var="row">
                                    <tr>
                                        <td style="text-align: center"><s:property value="penyewaanLahanId"/></td>
                                        <td style="text-align: center"><s:property value="branchName"/></td>
                                        <td style="text-align: center"><s:property value="stTanggalBayar"/></td>
                                        <td><s:property value="namaPenyewaName"/></td>
                                        <td style="text-align: right"><s:property value="stNilaiNetto"/></td>
                                        <td align="center">
                                            <a href="javascript:;" data="<s:property value="%{#attr.row.penyewaanLahanId}"/>" class="item-view">
                                                <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                                            </a>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.cancelFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.penyewaanLahanId}"/>" who="batal" class="item-view-approval">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" >
                                                </a>
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
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" >
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.penyewaanLahanId}"/>" who="keu" class="item-view-approval">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" >
                                                </a>
                                            </s:elseif>
                                            <s:else>
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.penyewaanLahanId}"/>" class="item-posting">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" name="icon_posting">
                                                </a>
                                            </s:else>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.cancelFlag == "Y"'>
                                            </s:if>
                                            <s:elseif test='#row.approvalFlag == "Y"'>
                                                <a href="javascript:;" penyewaanLahanId="<s:property value="%{#attr.row.penyewaanLahanId}"/>" class="item-cetak-bukti">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-print-25.png"/>" name="icon_edit" style="width: 30px">
                                                </a>
                                            </s:elseif>
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
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Unit</label>
                                    <div class="col-md-6">
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="mod_add_branch_id"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 14px" >Nama Penyewa</label>
                                    <div class="col-md-6">
                                        <s:action id="initComboVendor" namespace="/masterVendor" name="initComboVendor_masterVendor">
                                            <s:param name="tipe">lahan</s:param>
                                        </s:action>
                                        <s:select list="#initComboVendor.listOfComboVendor" id="mod_add_nama_penyewa" cssStyle="width: 100%" onchange="isiKeterangan()"
                                                  listKey="nomorMaster" listValue="nama" headerKey="" headerValue="" cssClass="form-control select2"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 14px" >Tanggal</label>
                                    <div class="col-md-6">
                                        <s:textfield id="mod_add_tanggal_bayar" cssClass="form-control" cssStyle="margin-top: 10px"  onchange="isiKeterangan()" />
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
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 14px" >Nilai (RP)</label>
                                    <div class="col-md-6">
                                        <s:textfield id="mod_add_nilai" cssClass="form-control" onkeyup="formatRupiah2(this),hitungPpn(this.value)" cssStyle="text-align: right;margin-top: 7px"  onmouseout="isiKeterangan()" />
                                    </div>
                                </div>
                            </div>
                            <script>
                                function hitungPpn(val){
                                    val = val.replace(/[.]/g,"");
                                    var nilai = parseInt(val);
                                    $('#mod_add_nilai_ppn').val(formatRupiahAtas(nilai*10/100));
                                    $('#mod_add_nilai_pph').val(formatRupiahAtas(nilai*2/100));
                                    $('#mod_add_nilai_netto').val(formatRupiahAtas(nilai-(nilai*10/100)-(nilai*2/100)));
                                }
                            </script>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 14px" >PPN (RP)</label>
                                    <div class="col-md-6">
                                        <s:textfield id="mod_add_nilai_ppn" cssClass="form-control" onkeyup="formatRupiah2(this),hitungNilaiNettoPpn(this.value)" placeholder="0" cssStyle="text-align: right;margin-top: 7px" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 14px" >PPH (RP)</label>
                                    <div class="col-md-6">
                                        <s:textfield id="mod_add_nilai_pph" cssClass="form-control" onkeyup="formatRupiah2(this),hitungNilaiNettoPph(this.value)" placeholder="0" cssStyle="text-align: right;margin-top: 7px" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 14px" >Netto ( Nilai - PPN - PPH ) (RP)</label>
                                    <div class="col-md-6">
                                        <s:textfield id="mod_add_nilai_netto" cssClass="form-control" readonly="true" cssStyle="text-align: right;margin-top: 7px" placeholder="0" />
                                    </div>
                                </div>
                            </div>
                            <script>
                                function hitungNilaiNettoPpn(val){
                                    var pph = $('#mod_add_nilai_pph').val();
                                    if (pph==""){
                                        pph="0";
                                    }
                                    pph = pph.replace(/[.]/g,"");
                                    var bruto = $('#mod_add_nilai').val();
                                    bruto = bruto.replace(/[.]/g,"");
                                    val = val.replace(/[.]/g,"");
                                    var nilai = parseInt(val);
                                    var nilaiBruto = parseInt(bruto);
                                    var nilaiPph = parseInt(pph);
                                    $('#mod_add_nilai_netto').val(formatRupiahAtas(nilaiBruto-nilai-nilaiPph));
                                }
                                function hitungNilaiNettoPph(val){
                                    var ppn = $('#mod_add_nilai_ppn').val();
                                    if (ppn==""){
                                        ppn="0";
                                    }
                                    ppn = ppn.replace(/[.]/g,"");
                                    var bruto = $('#mod_add_nilai').val();
                                    bruto = bruto.replace(/[.]/g,"");
                                    val = val.replace(/[.]/g,"");
                                    var nilai = parseInt(val);
                                    var nilaiBruto = parseInt(bruto);
                                    var nilaiPpn = parseInt(ppn);
                                    $('#mod_add_nilai_netto').val(formatRupiahAtas(nilaiBruto-nilai-nilaiPpn));
                                }
                            </script>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 14px" >Metode Bayar</label>
                                    <div class="col-md-2">
                                        <select id="mod_add_metode_bayar" class="form-control select2" style="width: 100%" onchange="pilihMetode(this.value),isiKeterangan()" >
                                            <option value="" ></option>
                                            <option value="tunai">Tunai</option>
                                            <option value="transfer">Transfer</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4" id="pilih_bank">
                                        <select class="form-control select2" style="width: 100%" id="mod_add_bank" onchange="isiKeterangan()">
                                            <option value=""></option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">No. Faktur Pajak</label>
                                    <div class="col-md-6">
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
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Upload Faktur Pajak</label>
                                    <div class="col-md-6">
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
                            </div>

                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 14px">Keterangan</label>
                                    <div class="col-md-6">
                                        <s:textarea id="mod_add_keterangan" rows="3" cssClass="form-control" cssStyle="margin-top: 14px"/>
                                    </div>
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
                                <label class="col-md-4" >Nilai (RP)</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_nilai" cssClass="form-control" readonly="true" cssStyle="text-align: right" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >PPN (RP)</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_nilai_ppn" cssClass="form-control" readonly="true" cssStyle="text-align: right" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >PPH (RP)</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_nilai_pph" cssClass="form-control" readonly="true" cssStyle="text-align: right" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Netto ( Nilai - PPN - PPH ) (RP)</label>
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
                                <br>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">No. Faktur Pajak</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_view_no_faktur" cssClass="form-control modal_pengajuan" cssStyle="margin-top: 7px;" readonly="true" />
                                    <s:hidden id="mod_view_url_faktur_image"/>
                                </div>
                                <div class="col-md-1">
                                    <a href="javascript:void(0)">
                                        <img  style="margin-top: 10px" id="btnViewFaktur" border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view_faktur">
                                    </a>
                                </div>
                                <br>
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
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px;text-align: right" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jumlah PPN</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_jumlah_ppn" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px;text-align: right" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jumlah PPN BM</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_jumlah_ppn_bm" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px;text-align: right" />
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
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">NPWP Penjual</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_npwp_penjual" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nama Penjual</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_nama_penjual" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat Penjual</label>
                            <div class="col-md-8">
                                <s:textarea id="mod_alamat_penjual" onkeypress="$(this).css('border','')" readonly="true" rows="3"
                                            cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">NPWP Perusahaan</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_npwp_perusahaan" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">NPWP Lawan</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_npwp_lawan_transaksi" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nama Lawan</label>
                            <div class="col-md-8">
                                <s:textfield id="mod_nama_lawan_transaksi" onkeypress="$(this).css('border','')" readonly="true"
                                             cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat Lawan</label>
                            <div class="col-md-8">
                                <s:textarea id="mod_alamat_lawan_transaksi" onkeypress="$(this).css('border','')" readonly="true" rows="3"
                                            cssClass="form-control mod_scan_faktur" cssStyle="margin-top: 7px" />
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
                        currentDate.setMonth(currentDate.getMonth()-3);
                        var dateParts = stTanggalFaktur.split("/");
                        var vendorFaktur = $('#mod_nama_penjual').val();
                        var npwpLawan = $('#mod_npwp_lawan_transaksi').val();
                        var npwpPerusahaan = $('#mod_npwp_perusahaan').val();
                        var tanggalFaktur = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
                        if (statusFaktur!=""){
                            if (tanggalFaktur < currentDate){
                                alert("Tanggal Faktur sudah tidak valid , silahkan membuat faktur baru");
                                $('.mod_scan_faktur').val('');
                                $('#modal-scan-faktur').modal('hide');
                            }else{
                                if (npwpLawan==npwpPerusahaan){
                                    alert("Berhasil menambahkan No. Faktur");
                                    $('#mod_no_faktur').val($('#mod_scan_faktur').val());
                                    $('#modal-scan-faktur').modal('hide');
                                    isiKeterangan();
                                } else{
                                    alert("NPWP Lawan yang berada di faktur tidak sama dengan NPWP perusahaan");
                                }
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

<div id="modal-view-approval" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> </h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">By</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_approve_by" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Date</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_approve_date" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-close"></i> Close</button>
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
                $('#mod_view_nama_penyewa').val(data.namaPenyewaName);
                $('#mod_view_tanggal_bayar').val(data.stTanggalBayar);
                $('#mod_view_nilai').val(data.stNilai);
                $('#mod_view_nilai_ppn').val(data.stNilaiPpn);
                $('#mod_view_nilai_pph').val(data.stNilaiPph);
                $('#mod_view_nilai_netto').val(data.stNilaiNetto);
                $('#mod_view_metode_bayar').val(data.metodeBayar);
                $('#mod_view_bank').val(data.bankName);
                $('#mod_view_no_faktur').val(data.noFaktur);
                $('#mod_view_url_faktur_image').val(data.urlFakturImage);
                $('#mod_view_keterangan').val(data.keterangan);
                if (data.noFaktur==""){
                    $('#btnViewFaktur').hide();
                }
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
                $('#mod_view_nama_penyewa').val(data.namaPenyewaName);
                $('#mod_view_tanggal_bayar').val(data.stTanggalBayar);
                $('#mod_view_nilai').val(data.stNilai);
                $('#mod_view_nilai_ppn').val(data.stNilaiPpn);
                $('#mod_view_nilai_pph').val(data.stNilaiPph);
                $('#mod_view_nilai_netto').val(data.stNilaiNetto);
                $('#mod_view_metode_bayar').val(data.metodeBayar);
                $('#mod_view_no_faktur').val(data.noFaktur);
                $('#mod_view_url_faktur_image').val(data.urlFakturImage);
                $('#mod_view_bank').val(data.bankName);
                $('#mod_view_keterangan').val(data.keterangan);
                if (data.noFaktur==""){
                    $('#btnViewFaktur').hide();
                }
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
                $('#mod_view_nama_penyewa').val(data.namaPenyewaName);
                $('#mod_view_tanggal_bayar').val(data.stTanggalBayar);
                $('#mod_view_nilai').val(data.stNilai);
                $('#mod_view_nilai_ppn').val(data.stNilaiPpn);
                $('#mod_view_nilai_pph').val(data.stNilaiPph);
                $('#mod_view_nilai_netto').val(data.stNilaiNetto);
                $('#mod_view_metode_bayar').val(data.metodeBayar);
                $('#mod_view_no_faktur').val(data.noFaktur);
                $('#mod_view_url_faktur_image').val(data.urlFakturImage);
                $('#mod_view_bank').val(data.bankName);
                $('#mod_view_keterangan').val(data.keterangan);
                if (data.noFaktur==""){
                    $('#btnViewFaktur').hide();
                }
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
            if (branchId!="01"){
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
            var nilaiPpn = $('#mod_add_nilai_ppn').val();
            var nilaiPph = $('#mod_add_nilai_pph').val();
            var nilaiNetto = $('#mod_add_nilai_netto').val();
            var metodeBayar = $('#mod_add_metode_bayar').val();
            var bank = $('#mod_add_bank').val();
            var noFakturPajak = $('#mod_no_faktur').val();
            var keterangan = $('#mod_add_keterangan').val();

            if (nilaiPph==""){
                nilaiPph="0";
            }
            if (nilaiPpn==""){
                nilaiPpn="0";
            }
            var canvas = document.getElementById('img_faktur_canvas');
            var cekCanvas = $('#namaFile').val();
            var dataURL = canvas.toDataURL("image/png"),
                dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");

            if (branchId!=""&&namaPenyewa!=""&&tanggal!=""&&nilai!=""&&metodeBayar!=""&&keterangan!=""&&nilaiPpn!=""&&nilaiNetto!=""){
                if (metodeBayar=="transfer"&&bank==""){
                    alert("Pilih bank terlebih dahulu");
                }else if(cekCanvas==""&&noFakturPajak==""){
                    if (confirm("No. nota dan bukti tidak dimasukkan , apakah anda ingin melanjutkan tanpa nota/bukti dan nilai ppn dijadikan 0 ?")){
                        PenyewaanLahanAction.saveAdd(branchId,namaPenyewa,tanggal,nilai,metodeBayar,keterangan,bank,"0",nilaiPph,nilaiNetto,noFakturPajak,dataURL,function () {
                            alert("Berhasil Menambahkan Data");
                            window.location.reload();
                        })
                    }
                } else{
                    if (confirm("Apakah anda ingin menambahkan data ini ?")){
                        PenyewaanLahanAction.saveAdd(branchId,namaPenyewa,tanggal,nilai,metodeBayar,keterangan,bank,nilaiPpn,nilaiPph,nilaiNetto,noFakturPajak,dataURL,function () {
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
                if (nilaiPpn==""){
                    msg+="Nilai PPN masih kosong \n";
                }
                if (nilaiNetto==""){
                    msg+="Nilai Netto Kosong masih kosong \n";
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

    window.isiKeterangan = function(){
        var namaPenyewa = $( "#mod_add_nama_penyewa option:selected" ).text();
        var tanggal = $('#mod_add_tanggal_bayar').val();
        var metodeBayar = $( "#mod_add_metode_bayar option:selected" ).text();
        var metodeBayar2 = $( "#mod_add_bank option:selected" ).text();
        var noNota = $('#mod_no_faktur').val();
        var keterangan = $('#mod_add_keterangan').val();

        keterangan ="Penyewaan lahan ";

        if (namaPenyewa!=""){
            keterangan +=" oleh "+namaPenyewa;
        }
        if (tanggal!=""){
            keterangan+=" pada tanggal "+tanggal;
        }
        if (metodeBayar!=""){
            if (metodeBayar=="Tunai"){
                keterangan += " dengan pembayaran tunai";
            }else{
                keterangan += " dengan pembayaran transfer pada bank "+metodeBayar2;
            }
        }
        if (noNota !=""){
            keterangan +=" dengan nomor nota "+noNota;
        }
        $('#mod_add_keterangan').val(keterangan);
    };

    function generateNoFaktur(val){
        PembayaranUtangPiutangAction.generateQrEfaktur(val,function (result) {
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
        })
    };
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
    function selectPembayaran(){
        var option = '<option value=""></option>';
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
    $(document).ready(function () {
        $('#tablePenyewaanLahan').DataTable({
            "pageLength": 50,
            "order": [[0, "desc"]]
        });

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
                reader.onload = function (event) {
                    var img = new Image();
                    img.onload = function () {
                        canvas.width = img.width;
                        canvas.height = img.height;
                        ctx.clearRect(0, 0, canvas.width, canvas.height);
                        ctx.drawImage(img, 0, 0);
                    }
                    img.src = event.target.result;
                }
                reader.readAsDataURL(event.target.files[0]);
            } else {
                if (log) alert(log);
            }
        });
    });
    $('#btnViewFaktur').click(function () {
        var id = $('#mod_view_url_faktur_image').val();
        var judul =$('#mod_view_no_faktur').val();
        dwr.engine.setAsync(false);
        $("#my-image").attr("src", id);
        $('#modal-view-faktur').find('.modal-title').text(judul);
        $('#modal-view-faktur').modal('show');
    });

    $('#tablePenyewaanLahan').on('click', '.item-cetak-bukti', function() {
        var penyewaanLahanId = $(this).attr('penyewaanLahanId');
        var url = "cetakBuktiPembayaran_penyewaanLahan.action?penyewaanLahan.penyewaanLahanId="+penyewaanLahanId;
        window.open(url,'_blank');
    });

    $('.tablePenyewaanLahan').on('click', '.item-view-approval', function() {
        var id = $(this).attr('data');
        var who = $(this).attr('who');
        var title ="View Keterangan ";

        PenyewaanLahanAction.getViewApproval(id,function (data) {
            if (who=="batal"){
                title += " Pembatalan";
                $('#mod_approve_by').val(data.cancelWho);
                $('#mod_approve_date').val(data.stCancelDate);
            } else {
                title += " Posting Penyewaan Lahan";
                $('#mod_approve_by').val(data.approvalWho);
                $('#mod_approve_date').val(data.stApprovalDate);
            }
        });
        $("#modal-view-approval").find('.modal-title').text(title);
        $("#modal-view-approval").modal('show');
    });
</script>

