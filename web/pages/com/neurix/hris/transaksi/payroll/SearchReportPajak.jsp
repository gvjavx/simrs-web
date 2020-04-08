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
</head>
<body class="hold-transition skin-blue sidebar-mini" >
<%@ include file="/pages/common/headerNav.jsp" %>
<ivelincloud:mainMenu/>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Laporan Payroll
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i>Laporan Payroll</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="payrollForm" method="post"  theme="simple" namespace="/payroll" action="searchReport_payroll.action" cssClass="form-horizontal">

                                        <s:hidden name="addOrEdit"/>
                                        <s:hidden name="delete"/>

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
                                                                  id="bulanPayroll" name="payroll.bulan"
                                                                  headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll"
                                                                  name="payroll.tahun" required="true" headerKey=""
                                                                  headerValue="[Select one]"/>
                                                    </table>
                                                </td>

                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchIdPayroll" name="payroll.branchId"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    </table>

                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                            <%--<s:select list="#{'PK':'Penghasilan Kotor', 'PD':'Potongan Dinas', 'PL':'Potongan Lain',--%>
                                                            <%--'R':'Rekapitulasi Penghasilan Karyawan', 'pendidikan':'Pendidikan', 'thr':'THR',--%>
                                                            <%--'jasprod':'Jasprod', 'insentif':'Insentif', 'rapelBulan':'Rapel Bulan', 'rapelBulanDetail':'Rapel Bulan Detail', 'rapelThr':'Rapel THR',--%>
                                                            <%--'rapelPendidikan':'Rapel Pendidikan','rapelLembur':'Rapel Lembur','rapelInsentif':'Rapel Insentif',--%>
                                                            <%--'rapelJubileum':'Rapel Jubileum'}"--%>
                                                            <%--id="tipe" name="payroll.tipe" cssClass="form-control" />--%>
                                                        <s:select list="#{'PK':'Penghasilan Kotor', 'PD':'Potongan Dinas', 'PL':'Potongan Lain',
                                        'R':'Rekapitulasi Penghasilan Karyawan', 'pendidikan':'Pendidikan', 'thr':'THR',
                                         'jasprod':'Jasprod', 'insentif':'Insentif', 'rapelBulan':'Rapel Bulan', 'rapelBulanDetail':'Rapel Bulan Detail', 'rapelThr':'Rapel THR',
                                         'rapelPendidikan':'Rapel Pendidikan','rapelLembur':'Rapel Lembur','rapelInsentif':'Rapel Insentif',
                                         'rapelJubileum':'Rapel Jubileum'}"
                                                                  id="tipe" name="payroll.tipe" cssClass="form-control" />
                                                    </table>

                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Status :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'KS':'Karyawan Staff', 'KNS':'Karyawan Non Staff'}" id="status" name="payroll.statusPegawai"
                                                                  cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="payrollForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-print"></i>
                                                            Print Pdf
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-success" id="btnExcell">
                                                            <i class="fa fa-download"></i> Download Excel
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_payroll"/>'">
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
        $('#btnExcell').click(function(){
            if (confirm('Apakah Anda ingin mendownload Payroll dalam format excel?')) {
                var branchId = document.getElementById("branchIdPayroll").value;
                var bulan = document.getElementById("bulanPayroll").value;
                var tahun = document.getElementById("tahunPayroll").value;
                var tipePrint = document.getElementById("tipe").value;
                var statusPegawai = document.getElementById("status").value;
                window.location.href = 'downloadXlsRekaptulasiPenghasilan_payroll?branchId='+branchId+'&bulan='+bulan+'&tahun='+tahun+'&statusPegawai='+statusPegawai
                    +'&tipe='+tipePrint;
            }
        });
    });
</script>