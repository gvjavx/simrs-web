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
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>

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
            window.location.href="<s:url action='loadAdd_payroll'/>";
        }

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {
            window.cekPayroll = function(branch, bulan, tahun){
                var isFlag = 'N';
                PayrollAction.cekAvailable(branch, bulan, tahun, function(listdata) {
                    isFlag = listdata;
                });
                return isFlag;
            }
            window.cekBeforePayroll = function(branch, bulan, tahun){
                var isFlag = 'N';
                PayrollAction.cekBeforePayroll(branch, bulan, tahun, function(listdata) {
                    isFlag = listdata;
                });
                return isFlag;
            }
            window.cekApprove = function(branch, bulan, tahun, tipe){
                var isFlag = 'Y';
                PayrollAction.cekApprove(branch, bulan, tahun, tipe, function(listdata) {
                    isFlag = listdata;
                });
                return isFlag;
            }
            window.clos = function() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='loadAdd_payroll.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var branch = document.getElementById("branchId").value;
                var bulan = document.getElementById("bulanPayroll").value;
                var tahun = document.getElementById("tahunPayroll").value;

                if (branch != '' && bulan != '0' && tahun != '') {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (branch == '') {
                        msg += 'Field <strong>Branch</strong> is required.' + '<br/>';
                    }

                    if (bulan == '0') {
                        msg += 'Field <strong>Bulan</strong> is required.' + '<br/>';
                    }

                    if (tahun == '') {
                        msg += 'Field <strong>Tahun</strong> is required.' + '<br/>';
                    }

                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');

                }
            });

            $.subscribe('errorDialogSearch', function (event, data) {
                document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
                $.publish('showErrorDialog');
            });

            $.subscribe('gantiHalaman', function (event, data) {
                window.location.href = 'loadAdd_payroll.action';
            });
        });


    </script>
</head>
<div id="crud">
    <body class="hold-transition skin-blue sidebar-mini" >

    <%@ include file="/pages/common/headerNav.jsp" %>

    <ivelincloud:mainMenu/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Add Penggajian
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-filter"></i>Add Penggajian</h3>
                        </div>
                        <div class="box-body">
                            <table width="100%" align="center">
                                <tr>
                                    <td align="center">
                                        <s:form id="payrollForm" method="post"  theme="simple" namespace="/payroll" action="save_payroll.action" cssClass="form-horizontal">

                                            <s:hidden name="addOrEdit"/>
                                            <s:hidden name="delete"/>
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
                                                    <td>
                                                        <table>
                                                            <s:if test="%{payroll.kantorPusat}">
                                                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="payroll.branchId"
                                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                                <s:select list="#initComboBranch.listOfComboBranch" id="unitTmp" name="payroll.branchId" disabled="true"
                                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                                <s:hidden id="branchId" name="payroll.branchId" />
                                                            </s:else>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label class="control-label"><small>Periode :</small></label>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                                      id="bulanPayroll" name="payroll.bulan" onchange="changeBulan(this)"
                                                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                        </table>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                            <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll" onchange="changeTahun(this)"
                                                                      name="payroll.tahun" required="true" headerKey=""
                                                                      headerValue="[Select one]"/>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe Penggajian :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'PR':'Payroll', 'T':'THR', 'CP':'Cuti Panjang', 'CT':'Cuti Tahunan',
                                        'JP':'Jasa Operassional', 'JB':'PMP', 'PN':'SHT', 'IN':'Insentif'}" id="tipe" name="payroll.tipe"
                                                                  cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </table>
                                            <br>
                                            <div id="actions" class="form-actions">
                                                <table align="center">
                                                    <tr>
                                                        <td>
                                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="payrollForm" id="save" name="save"
                                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog"
                                                                       onSuccessTopics="gantiHalaman" onErrorTopics="errorDialogSearch" >
                                                                <i class="fa fa-search"></i>
                                                                Search
                                                            </sj:submit>
                                                        </td>
                                                        <td>
                                                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="add_payroll.action"/>'">
                                                                <i class="fa fa-refresh"></i> Reset
                                                            </button>
                                                        </td>
                                                        <td>
                                                            <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_payroll.action"/>'">
                                                                <i class="fa fa-close"></i> Cancel
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>

                                            <br>
                                            <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                       resizable="false"
                                                       height="350" width="600" autoOpen="false" title="Searching ...">
                                                Please don't close this window, server is processing your request ...
                                                </br>
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

                                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                       buttons="{
                                                              'OK':function() {
                                                                      clos();
                                                                   }
                                                            }"
                                            >
                                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                Record has been saved successfully.
                                            </sj:dialog>

                                            <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                       height="250" width="600" autoOpen="false" title="Error Dialog"
                                                       buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); }
                                                                    }"
                                            >
                                                <div class="alert alert-error fade in">
                                                    <label class="control-label" align="left">
                                                        <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                                    </label>
                                                </div>
                                            </sj:dialog>

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
</div>
</html>
