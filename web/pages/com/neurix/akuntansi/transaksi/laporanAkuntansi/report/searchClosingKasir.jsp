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
    <script type='text/javascript' src='<s:url value="/dwr/interface/LaporanAkuntansiAction.js"/>'></script>
    <script type='text/javascript'>

        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        function link(){
            window.location.href="<s:url action='initForm_laporan'/>";
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            var unit    = document.getElementById("branchId").value;
            var tglFrom = document.getElementById("tglFrom").value;
            var nipKasir = document.getElementById("kasir").value;
            var tipeKasir = document.getElementById("tipe_kasir").value;
            var reportId = document.getElementById("reportId").value;

            if ( unit != '' && tglFrom != ''&&nipKasir!=''&&tipeKasir!=''&&reportId!='') {
                event.originalEvent.options.submit = false;
                var url = "printReportClosingKasir_laporanAkuntansi.action?laporanAkuntansi.unit="+unit+"&laporanAkuntansi.stTanggalAwal="+tglFrom+"&laporanAkuntansi.nip="+nipKasir+"&laporanAkuntansi.tipeKasir="+tipeKasir+"&laporanAkuntansi.reportId="+reportId;
                window.open(url,'_blank');
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if ( unit == '') {
                    msg += 'Field <strong>Unit </strong> masih belum dipilih' + '<br/>';
                }
                if ( tipeKasir == '') {
                    msg += 'Field <strong>Tipe Kasir </strong> masih belum dipilih' + '<br/>';
                }
                if ( tglFrom == '') {
                    msg += 'Field <strong>Tanggal </strong> masih belum dipilih' + '<br/>';
                }
                if ( nipKasir == '') {
                    msg += 'Field <strong>Kasir </strong> masih belum dipilih' + '<br/>';
                }
                if ( reportId == '') {
                    msg += 'Field <strong>Error </strong> hubungi admin' + '<br/>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');
            }
        });
        $.subscribe('beforeProcessSaveRekap', function (event, data) {
            var unit    = document.getElementById("branchId").value;
            var tglFrom = document.getElementById("tglFrom").value;
            var nipKasir = document.getElementById("kasir").value;
            var tipeKasir = document.getElementById("tipe_kasir").value;
            var reportId = document.getElementById("reportId").value;

            if ( unit != '' && tglFrom != ''&&nipKasir!=''&&tipeKasir!=''&&reportId!='') {
                event.originalEvent.options.submit = false;
                var url = "printReportRekapClosingKasir_laporanAkuntansi.action?laporanAkuntansi.unit="+unit+"&laporanAkuntansi.stTanggalAwal="+tglFrom+"&laporanAkuntansi.nip="+nipKasir+"&laporanAkuntansi.tipeKasir="+tipeKasir+"&laporanAkuntansi.reportId="+reportId;
                window.open(url,'_blank');
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if ( unit == '') {
                    msg += 'Field <strong>Unit </strong> masih belum dipilih' + '<br/>';
                }
                if ( tipeKasir == '') {
                    msg += 'Field <strong>Tipe Kasir </strong> masih belum dipilih' + '<br/>';
                }
                if ( tglFrom == '') {
                    msg += 'Field <strong>Tanggal </strong> masih belum dipilih' + '<br/>';
                }
                if ( nipKasir == '') {
                    msg += 'Field <strong>Kasir </strong> masih belum dipilih' + '<br/>';
                }
                if ( reportId == '') {
                    msg += 'Field <strong>Error </strong> hubungi admin' + '<br/>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');
            }
        });
        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {
//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });
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
            Closing Kasir Tunai
            <%--<small>e-HEALTH</small>--%>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Closing Kasir Tunai</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="laporanAkuntansiForm" method="post"  theme="simple" cssClass="form-horizontal">
                                        <s:hidden name="tipeLaporan" value="neraca_saldo"/>
                                        <table>
                                            <tr>
                                                <td width="10%" align="center">
                                                    <%@ include file="/pages/common/message.jsp" %>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td width="300px">
                                                    <table>
                                                        <s:if test='laporanAkuntansi.unit == "01"'>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="laporanAkuntansi.unit"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]"  cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="laporanAkuntansi.unit" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId" name="laporanAkuntansi.unit" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tanggal :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <div class="input-group date">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="tglFrom" cssClass="form-control pull-right"
                                                                         required="false" size="7"  cssStyle="background-color: white" readonly="true"/>
                                                            <script>
                                                                var today = new Date();
                                                                var dd = String(today.getDate()).padStart(2, '0');
                                                                var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                                                                var yyyy = today.getFullYear();

                                                                today = dd + '-' + mm + '-' + yyyy;
                                                                $('#tglFrom').val(today);
                                                            </script>
                                                        </div>
                                                        <script>
                                                            $('#tglFrom').datepicker({
                                                                dateFormat: 'dd-mm-yy'
                                                            });
                                                        </script>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe Kasir :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'kasir_ri':'Kasir Rawat Inap', 'kasir_rj' : 'Kasir Rawat Jalan'}"
                                                                  id="tipe_kasir"  onchange="listKasir()"
                                                                  headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                                        <s:hidden name="laporanAkuntansi.reportId" id="reportId" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Kasir :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <select id="kasir" class="form-control">
                                                            <option value=''>[Select One]</option>
                                                        </select>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="laporanAkuntansiForm" id="printRekap" name="printRekap"
                                                                   onBeforeTopics="beforeProcessSaveRekap">
                                                            <i class="fa fa-print"></i>
                                                            Print Rekap
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="laporanAkuntansiForm" id="print" name="print"
                                                                   onBeforeTopics="beforeProcessSave">
                                                            <i class="fa fa-print"></i>
                                                            Print
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="searchReportNeracaSaldo_laporanAkuntansi.action"/>'">
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
                                                        <sj:dialog id="waiting_dialog_loading" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="350" width="600" autoOpen="false" title="Loading ...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <center>
                                                                <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                                            </center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="620" width="900" autoOpen="false"
                                                                   title="Laporan Akuntansi">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                        <div id="actions" class="form-actions">
                                            <table>
                                                <tr>
                                                    <div id="crud">
                                                        <td>
                                                            <table>
                                                                <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                                                           height="280" width="500" autoOpen="false" title="Warning"
                                                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                                                >
                                                                    <div class="alert alert-error fade in">
                                                                        <label class="control-label" align="left">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                                            <br/>
                                                                            <center><div id="errorValidationMessage"></div></center>
                                                                        </label>
                                                                    </div>
                                                                </sj:dialog>
                                                            </table>
                                                        </td>
                                                    </div>
                                                </tr>
                                            </table>
                                        </div>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Your Page Content Here -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>
<script>
    function listKasir(){
        var branchId =$('#branchId').val();
        var tglFrom =$('#tglFrom').val();
        var tipeKasir =$('#tipe_kasir').val();
        var option = "";
        option = "<option value=''>[Select One]</option>";
        if (branchId!=''&&tglFrom!=''&&tipeKasir!=''){
            LaporanAkuntansiAction.listKasirByBranch(branchId,tglFrom,tipeKasir, function(response){
                if (response != null){
                    $.each(response, function (i, item) {
                        option += "<option value='"+item.nip+"'>"+item.shiftName+" | "+item.namaPegawai+ "</option>";
                    });
                }else{
                    option = option;
                }
            });
            $('#kasir').html(option);
        }else { $('#kasir').html(option);}
    }
    /*$(document).ready(function () {
        listKasir();
    })*/
</script>