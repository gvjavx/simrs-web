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
        .checkApprove {width: 20px; height: 20px;}
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

        function link(){
            window.location.href="<s:url action='initForm_alat'/>";
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
            Daftar Jubileum
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <div id="modal-addPayroll" class="modal fade" role="dialog">
            <div class="modal-dialog modal-lg">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Add Data</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="myForm">

                            <div class="form-group">
                                <label class="control-label col-sm-3" >Unit : </label>
                                <div class="col-sm-6">
                                    <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                    <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="sppd.branchId"
                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-3" style="text-align: right">
                                    <input type="checkbox" id="checkApprove" name="checkApprove[]" class="checkApprove" >
                                    <label class="control-label ">Payroll : </label>
                                </div>
                                <div class="col-sm-3">
                                    <s:select list="#{'1':'Januari', '2' : 'Februari', '3':'Maret', '4':'April', '5':'Mei', '6':'Juni', '7':'Juli',
                                '8': 'Agustus', '9' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}" id="flag" name="department.flag"
                                              headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                </div>
                                <div class="col-sm-3">
                                    <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}" id="flag" name="department.flag"
                                              headerKey="0" headerValue="Tahun" cssClass="form-control" />
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-3" style="text-align: right">
                                    <input type="checkbox" id="checkApprove" name="checkApprove[]" class="checkApprove" >
                                    <label class="control-label " >Rapel: </label>
                                </div>
                                <div class="col-sm-3">
                                    <s:select list="#{'1':'Januari', '2' : 'Februari', '3':'Maret', '4':'April', '5':'Mei', '6':'Juni', '7':'Juli',
                                '8': 'Agustus', '9' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}" id="flag" name="department.flag"
                                              headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                </div>
                                <div class="col-sm-3">
                                    <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}" id="flag" name="department.flag"
                                              headerKey="0" headerValue="Tahun" cssClass="form-control" />
                                </div>
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="btnSave" type="button" class="btn btn-default btn-success">Add</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="payrollForm" method="post"  theme="simple" namespace="/payroll" action="searchJubileum_payroll.action" cssClass="well form-horizontal">

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
                                        <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}"
                                                  id="tahunPayroll1" name="payroll.tahun"
                                                  headerKey="0" headerValue="Tahun" cssClass="form-control" />
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
                                        <s:select list="#initComboBranch.listOfComboBranch" id="unitId" name="payroll.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
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
                        <center>
                            <table id="showdata" width="50%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="500" width="600" autoOpen="false"
                                                   title="Payroll ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfPayroll" value="#session.listOfResultPayrollJubileum" scope="request" />
                                        <display:table name="listOfPayroll" class=" tablePayroll table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_payroll_jubileum_payroll.action" export="true" id="row" pagesize="30" style="font-size:10">

                                            <display:column property="nip" title="Nip"  />
                                            <display:column property="nama" title="Nama Pegawai"  />
                                            <display:column property="tanggalJubileum" title="Tanggal Jubileum"  />
                                            <display:column property="masaKerja" title="Masa Kerja"  />

                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </s:form>
                </td>
            </tr>
        </table>

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
    <div id="modal-approve" class="modal fade modal2" role="dialog">
        <div class="modal-dialog " style="width:400px;">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body" >
                    <form class="form-horizontal" id="myForm">

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Bulan</label>
                            <div class="col-sm-8">
                                <input readonly type="text" class="form-control nip" id="approveBulan" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tahun</label>
                            <div class="col-sm-8">
                                <input readonly type="text" class="form-control nip" id="approveTahun" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Unit</label>
                            <div class="col-sm-8">
                                <input readonly type="text" class="form-control nip" id="approveBranch" name="nip">
                                <input readonly type="text" class="form-control nip" style="display: none" id="approveBranchId" name="nip">
                            </div>
                        </div>
                        <div class="form-group" style="display: none">
                            <label class="control-label col-sm-4" >Tipe</label>
                            <div class="col-sm-8">
                                <input readonly type="text" class="form-control nip"  id="tipeId2" >
                            </div>
                        </div>

                    </form>
                    <%--<font size="1" face="Courier New" >--%>
                    <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                    </table>
                </div>
                <div class="modal-footer">
                    <a type="button" id="btnApprove" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                    <a type="button" id="btnNotApprove" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                    <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-draft" class="modal fade modal2" role="dialog">
        <div class="modal-dialog " style="width:500px;">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body" >
                    <form class="form-horizontal" id="myForm">

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Bulan</label>
                            <div class="col-sm-7">
                                <input readonly type="text" class="form-control nip" id="draftBulan" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Tahun</label>
                            <div class="col-sm-7">
                                <input readonly type="text" class="form-control nip" id="draftTahun" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Unit</label>
                            <div class="col-sm-7">
                                <input readonly type="text" class="form-control nip" id="draftBranch" name="nip">
                                <input readonly type="text" class="form-control nip" style="display: none" id="draftBranchId" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Tipe</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="draftTipePrint">
                                    <option value="PK">Penghasilan Kotor</option>
                                    <option value="PD">Potongan Dinas</option>
                                    <option value="PL">Potongan Lain</option>
                                    <option value="R">Rekapitulasi Penghasilan Karyawan</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" >Status Peg.</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="draftStatusPegawai">
                                    <option value="KS">Karyawan Staff</option>
                                    <option value="KNS">Karyawan Non Staff</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group" style="display: none">
                            <label class="control-label col-sm-4" >Tipe</label>
                            <div class="col-sm-8">
                                <input readonly type="text" class="form-control nip"  id="draftTipeId2" >
                            </div>
                        </div>

                    </form>
                    <%--<font size="1" face="Courier New" >--%>
                    <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                    </table>
                </div>
                <div class="modal-footer">
                    <a type="button" id="btnPrint" class="btn btn-success"><i class="fa fa-print"></i> Print</a>
                    <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
                </div>
            </div>
        </div>
    </div>
</html>

<script>
    $(document).ready(function() {



    });
</script>
