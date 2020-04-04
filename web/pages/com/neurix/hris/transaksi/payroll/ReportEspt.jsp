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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PayrollAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
</head>
<body class="hold-transition skin-blue sidebar-mini" >
<%@ include file="/pages/common/headerNav.jsp" %>
<ivelincloud:mainMenu/>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Payroll Report ESPT
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="cutiPegawaiForm" method="post"  theme="simple" namespace="/cutiPegawai" action="search_cutiPegawai.action" cssClass="form-horizontal">
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
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Pilih Unit]" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Periode :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="periode"
                                                                  name="payroll.tahun" required="true" headerKey=""
                                                                  headerValue="[Select one]"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe Tarikan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'1721':'Tarikan 1721', 'pdppph' : 'Tarikan Pendapatan + PPH'}"
                                                                  id="tipeTarikan"
                                                                  headerKey="" headerValue="[Pilih Tipe Tarikan]" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <button type="button" class="btn btn-success" id="btnCetakExcel">
                                                            <i class="fa fa-print"></i> Cetak Excel
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_laporan"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

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

<script>
    $(document).ready(function() {
        $('#btnCetakExcel').click(function(){
            var branch = $('#branchId').val();
            var periode= $('#periode').val();
            var tipeTarikan= $('#tipeTarikan').val();

            if (branch!=''&&periode!=''&&tipeTarikan!=''){
                var tipeTarikanName ="";
                if (tipeTarikan=="1721"){
                    tipeTarikanName="Tarikan Excel 1721";
                } else if (tipeTarikan=="pdppph"){
                    tipeTarikanName="Tarikan Pendapatan + PPH";
                }

                if (confirm('Apakah Anda ingin mendownload '+tipeTarikanName+' dalam format excel?')) {
                    window.location.href = 'payrollReportExcelEspt_payroll.action?branchId='+branch+'&tahun='+periode+'&tipe='+tipeTarikan;
                }
            }else {
                var msg="";
                if (branch==""){
                    msg+="Unit tidak boleh kosong \n";
                }
                if (periode==""){
                    msg+="Periode tidak boleh kosong \n";
                }
                if (tipeTarikan==""){
                    msg+="Tipe Tarikan tidak boleh kosong \n";
                }
                alert(msg);
            }
        });
    });
</script>
