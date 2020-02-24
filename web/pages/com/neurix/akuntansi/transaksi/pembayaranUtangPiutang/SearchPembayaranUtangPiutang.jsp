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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PembayaranUtangPiutangAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <style>
        .modal-backdrop {
            z-index: -1;
        }
    </style>
    <style>
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <script type='text/javascript'>
        function cekAvailableCoa(nilai){
            var coa = nilai.value;
            var length = nilai.length;
            if (length!=0){
                dwr.engine.setAsync(false);
                KodeRekeningAction.cekAvailableCoa(coa, function(listdata) {
                    if (listdata.length==0){
                        alert("COA tidak ada");
                        $('#kodeRekening').val("");
                        $('#namaKodeRekening').val("");
                    }
                });
            }
        }
        function link(){
            window.location.href="<s:url action='initForm_pembayaranUtangPiutang'/>";
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
            Pembayaran Hutang Piutang
            <small>e-HEALTH</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pembayaran Hutang Piutang</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="pembayaranUtangPiutangForm" method="post"  theme="simple" namespace="/pembayaranUtangPiutang" action="search_pembayaranUtangPiutang.action" cssClass="form-horizontal">
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
                                                    <label class="control-label"><small>Pembayaran Hutang Piutang ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="pembayaranUtangPiutangId" name="pembayaranUtangPiutang.pembayaranUtangPiutangId" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Flag :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="pembayaranUtangPiutang.flag"
                                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                                    </table>

                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="pembayaranUtangPiutangForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <a href="add_pembayaranUtangPiutang.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Pembayaran Hutang Piutang</a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_pembayaranUtangPiutang"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <br>
                                        <br>
                                        <center>
                                            <table id="showdata" width="80%">
                                                <tr>
                                                    <td align="center">
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="500" width="600" autoOpen="false"
                                                                   title="Pembayaran Hutang Piutang ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <s:set name="listOfPembayaranUtangPiutang" value="#session.listOfResult" scope="request" />
                                                        <display:table name="listOfPembayaranUtangPiutang" class="table table-condensed table-striped table-hover tablePembayaranUtangPiutang"
                                                                       requestURI="paging_displaytag_pembayaranUtangPiutang.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                            <display:column media="html" title="View">
                                                                <s:url var="urlView" namespace="/pembayaranUtangPiutang" action="view_pembayaranUtangPiutang" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.pembayaranUtangPiutangId"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenu" href="%{urlView}">
                                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_view">
                                                                </sj:a>
                                                            </display:column>
                                                            <display:column property="pembayaranUtangPiutangId" sortable="true" title="Tipe Jurnal ID" />
                                                            <display:column property="noJurnal" sortable="true" title="No. Jurnal" />
                                                            <display:column property="tipeTransaksi" sortable="true" title="Tipe Transaksi"  />
                                                            <display:column property="tanggal" sortable="true" title="Tanggal"  />
                                                            <display:column property="kodeRekeningKas" sortable="true" title="Kode Rekening Kas"  />
                                                            <display:column property="bayar" sortable="true" title="Total Bayar"  />
                                                            <display:column property="keterangan" sortable="true" title="Keterangan"  />
                                                            <display:column property="noSlipBank" sortable="true" title="No. Slip Bank"  />
                                                            <display:column property="lastUpdate" sortable="true" title="Last Update"/>
                                                            <display:column media="html" title="Posting"  style="text-align:center">
                                                                <s:if test="#attr.row.flagPosting">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.pembayaranUtangPiutangId}"/>" class="item-posting">
                                                                        <img border="0" src="<s:url value="/pages/images/tambah_flat.png"/>" name="icon_edit" style="width: 30px">
                                                                    </a>
                                                                </s:else>
                                                            </display:column>
                                                            <display:column media="html" title="Cetak Bukti"  style="text-align:center">
                                                                <s:if test="#attr.row.flagPosting">
                                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.noJurnal}"/>" unit="<s:property value="%{#attr.row.branchId}"/>" pembayaranId="<s:property value="%{#attr.row.pembayaranUtangPiutangId}"/>" class="item-cetak-bukti">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit" style="width: 30px">
                                                                    </a>
                                                                </s:if>
                                                            </display:column>
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
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
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Pembayaran Hutang Piutang Id</label>
                                <div class="col-md-6">
                                    <s:textfield id="hutang_piutang_id" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" cssStyle="margin-top: 7px" />
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnPostingJurnal" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Posting</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.tablePembayaranUtangPiutang').on('click', '.item-posting', function() {
            var pembayaranId = $(this).attr('data');
            $('#hutang_piutang_id').val(pembayaranId);
            $("#modal-posting-jurnal").modal('show');
        });
        $('.tablePembayaranUtangPiutang').on('click', '.item-cetak-bukti', function() {
            var noJurnal = $(this).attr('data');
            var branchId = $(this).attr('unit');
            var pembayaranId = $(this).attr('pembayaranId');
            var url = "printReportBuktiPosting_pembayaranUtangPiutang.action?pembayaranUtangPiutang.noJurnal="+noJurnal+"&pembayaranUtangPiutang.branchId="+branchId+"&pembayaranUtangPiutang.pembayaranUtangPiutangId="+pembayaranId;
            window.open(url,'_blank');
        });
        $('#btnPostingJurnal').click(function () {
            var pembayaranId =  $('#hutang_piutang_id').val();
            PembayaranUtangPiutangAction.postingJurnal(pembayaranId,function (listdata) {
                alert(listdata);
                window.location.reload();
            })
        })
    });
</script>


