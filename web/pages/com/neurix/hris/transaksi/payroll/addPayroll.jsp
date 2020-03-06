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
                var date = new Date();
                var currentMonth = date.getMonth();
                var currentYears = date.getYear();
                var branch = document.getElementById("branchId").value;
//                var insentifTahun = document.getElementById("insentifTahun").value;
                var bulan = document.getElementById("bulanPayroll").value;
                var bulanPayroll = parseInt(bulan, 10);
                var tahun = document.getElementById("tahunPayroll").value;
                var thr = document.getElementById("flagThr").value;
                var insentifTipe = document.getElementById("flagInsentif").value;
                var thrTipe = 'y';
                var hasil = cekApprove(branch, bulan, tahun, thrTipe);
                //alert(hasil);
                if (bulanPayroll==currentMonth+1){
                    if (branch != '' && bulan != '0' && tahun != '0') {
                        if(thr == 'Y'){
                            event.originalEvent.options.submit = true;
                            $.publish('showDialog');
                            if(thrTipe != ''){
                                event.originalEvent.options.submit = true;
                                $.publish('showDialog');
                            }else{
                                event.originalEvent.options.submit = false;
                                var msg = "";
                                msg += 'Tipe THR harus dipilih.' + '<br/>';
                                document.getElementById('errorValidationMessage').innerHTML = msg;

                                $.publish('showErrorValidationDialog');
                            }
                        }else{
                            event.originalEvent.options.submit = true;
                            $.publish('showDialog');
                        }

                    } else {
                        event.originalEvent.options.submit = false;
                        var msg = "";
                        if (branch == '') {
                            msg += 'Field <strong>Branch</strong> is required.' + '<br/>';
                        }

                        if (bulan == '0') {
                            msg += 'Field <strong>Bulan</strong> is required.' + '<br/>';
                        }

                        if (tahun == '0') {
                            msg += 'Field <strong>Tahun</strong> is required.' + '<br/>';
                        }

                        document.getElementById('errorValidationMessage').innerHTML = msg;

                        $.publish('showErrorValidationDialog');

                    }
                }else{
                    event.originalEvent.options.submit = false;
                    document.getElementById('errorValidationMessage').innerHTML = "Payroll Yang bisa Diproses Hanya Bulan Ini";
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
                Add Payroll
                <small>e-HEALTH</small>
            </h1>
        </section>


        <!-- Main content -->
        <section class="content">
            <table width="100%" align="center">
                <tr>
                    <td align="center">
                        <s:form id="payrollForm" method="post"  theme="simple" namespace="/payroll" action="save_payroll.action" cssClass="well form-horizontal">

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
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <label class="control-label"><small>Unit :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="payroll.branchId"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <input type="checkbox" id="checkPayroll" class="checkApprove" value="payroll"
                                               onchange="changePayroll(this)">
                                    </td>
                                    <td></td>
                                    <td>
                                        <label class="control-label"><small>Payroll :</small></label>
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
                                            <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}"
                                                      id="tahunPayroll" name="payroll.tahun" onchange="changeTahun(this)"
                                                      headerKey="0" headerValue="Tahun" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td style="display: none">
                                        <table>
                                            <s:textfield  id="flagPayroll" name="payroll.flagPayroll" required="false"
                                                          cssClass="form-control" value="Y"/>
                                        </table>
                                    </td >
                                </tr>

                                <tr>
                                    <td>
                                        <input type="checkbox" id="checkCutiTahunan" class="checkApprove" value="cutiTahunan"
                                               onchange="changeCutiTahunan(this)">
                                    </td>
                                    <td></td>
                                    <td>
                                        <label class="control-label"><small>Cuti Tahunan :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                      id="bulanCutiTahunan" disabled="true"
                                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}"
                                                      id="tahunCutiTahunan" disabled="true"
                                                      headerKey="0" headerValue="Tahun" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td style="display: none">
                                        <table>
                                            <s:textfield  id="flagCutiTahunan" name="payroll.flagCutiTahunan" required="false"
                                                          cssClass="form-control" value="N"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="checkbox" id="checkCutiPanjang" class="checkApprove" value="cutiPanjang"
                                               onchange="changeCutiPanjang(this)">
                                    </td>
                                    <td></td>
                                    <td>
                                        <label class="control-label"><small>Cuti Panjang :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                      id="bulanCutiPanjang" disabled="true"
                                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}"
                                                      id="tahunCutiPanjang" disabled="true"
                                                      headerKey="0" headerValue="Tahun" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td style="display: none">
                                        <table>
                                            <s:textfield  id="flagCutiPanjang" name="payroll.flagCutiPanjang" required="false"
                                                          cssClass="form-control" value="N"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="checkbox" id="checkThr" class="checkApprove" value="thr"
                                               onchange="changeThr(this)" >
                                    </td>
                                    <td></td>
                                    <td>
                                        <label class="control-label"><small>THR :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                      id="bulanThr" name="department.flag" disabled="true"
                                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}"
                                                      id="tahunThr" name="department.flag" disabled="true"
                                                      headerKey="0" headerValue="Tahun" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td style="display: none">
                                        <table>
                                            <s:textfield  id="flagThr" name="payroll.flagThr" required="false"
                                                          cssClass="form-control" value="N"/>
                                        </table>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <input type="checkbox" id="checkJasprod" class="checkApprove" value="jasprod"
                                               onchange="changeJasprod(this)">
                                    </td>
                                    <td></td>
                                    <td>
                                        <label class="control-label"><small>Jasopr :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                      id="bulanJasprod" disabled="true"
                                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}"
                                                      id="tahunJasprod" disabled="true"
                                                      headerKey="0" headerValue="Tahun" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td style="display: none">
                                        <table>
                                            <s:textfield  id="flagJasprod" name="payroll.flagJasprod" required="false"
                                                          cssClass="form-control" value="N"/>
                                        </table>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <input type="checkbox" id="checkJubileum" class="checkApprove" value="jubileum"
                                               onchange="changeJubileum(this)">
                                    </td>
                                    <td></td>
                                    <td>
                                        <label class="control-label"><small>PMP :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                      id="bulanJubileum" disabled="true"
                                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}"
                                                      id="tahunJubileum" disabled="true"
                                                      headerKey="0" headerValue="Tahun" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td style="display: none">
                                        <table>
                                            <s:textfield  id="flagJubileum" name="payroll.flagJubileum" required="false"
                                                          cssClass="form-control" value="N"/>
                                        </table>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <input type="checkbox" id="checkPesangon" class="checkApprove" value="pesangon"
                                               onchange="changePesangon(this)">
                                    </td>
                                    <td></td>
                                    <td>
                                        <label class="control-label"><small>SHT :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                      id="bulanPesangon" disabled="true"
                                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}"
                                                      id="tahunPesangon" disabled="true"
                                                      headerKey="0" headerValue="Tahun" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td style="display: none">
                                        <table>
                                            <s:textfield  id="flagPensiun" name="payroll.flagPensiun" required="false"
                                                          cssClass="form-control" value="N"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="checkbox" id="checkInsentif" class="checkApprove" value="jasprod"
                                               onchange="changeInsentif(this)">
                                    </td>
                                    <td></td>
                                    <td>
                                        <label class="control-label"><small>Insentif :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                      id="bulanInsentif" disabled="true"
                                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td>
                                        <table>
                                            <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}"
                                                      id="tahunInsentif" disabled="true"
                                                      headerKey="0" headerValue="Tahun" cssClass="form-control" />
                                        </table>
                                    </td>
                                    <td style="display: none">
                                        <table>
                                            <s:textfield  id="flagInsentif" name="payroll.flagInsentif" required="false"
                                                          cssClass="form-control" value="N"/>
                                        </table>
                                    </td>
                                </tr>


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
                                            <%--<sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="killUserSessionForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialogDelete,successDialogDelete"
                                                       onSuccessTopics="successDialogDelete" onErrorTopics="errorDialogDelete" >
                                                <i class="icon-ok-sign icon-white"></i>
                                                Kill Session
                                            </sj:submit>--%>
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

                            <center>
                                <table id="showdata" width="100%">
                                    <tr>
                                        <td align="center">
                                            <table style="width: 100%;" class="sppdPersonTable table table-bordered">
                                            </table>
                                        </td>
                                    </tr>
                                </table>

                            </center>

                            <div id="actions" class="form-actions">
                                <table>
                                    <tr>
                                            <%--<div id="crud">--%>
                                        <td>
                                            <table>
                                                <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="350" width="600" autoOpen="false" title="Searching ...">
                                                    Please don't close this window, server is processing your request ...
                                                    </br>
                                                    </br>
                                                    </br>
                                                    <center>
                                                        <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_write">
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
                                            </table>
                                        </td>
                                            <%--</div>--%>
                                    </tr>
                                </table>
                            </div>
                        </s:form>
                    </td>
                </tr>
            </table>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="/pages/common/footer.jsp" %>


    <%@ include file="/pages/common/lastScript.jsp" %>

    </body>
    <div id="modal-setting-insentif" class="modal fade modal2" role="dialog">
        <div class="modal-dialog " style="width:300px;">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body" >
                    <form class="form-horizontal" id="myFormDetailInsentif">
                        <div class="form-group">
                            <label class="control-label col-sm-5" >Bulan Mulai</label>

                            <div class="col-sm-7">
                                <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                          id="insentifModalBulanMulai"
                                          headerKey="-" headerValue="Bulan" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" >Bulan Sampai</label>

                            <div class="col-sm-7">
                                <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                          id="insentifModalBulanSampai"
                                          headerKey="-" headerValue="Bulan" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" >Tahun</label>

                            <div class="col-sm-7">
                                <s:select list="#{'2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}"
                                          id="insentifModalTahun"
                                          headerKey="-" headerValue="Tahun" cssClass="form-control" />
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button id="btnSaveDetailInsentif" type="button" class="btn btn-default btn-success">Save</button>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-setting-rapel" class="modal fade modal2" role="dialog">
        <div class="modal-dialog " style="width:600px;">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body" >
                    <form class="form-horizontal" id="myForm">
                        <div class="form-group">
                            <label class="control-label col-sm-4" >Gaji Golongan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelGadas" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelGadasTanggal1" onchange="changeRapelTanggal1(this)">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelGadasTanggal2" onchange="changeRapelTanggal2(this)">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tunjangan Struktural</label>
                            <div class="col-sm-1">
                                <input id="checkRapelStruktural" type="checkbox" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelStrukturalTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelStrukturalTanggal2" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tunjangan Umk</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelUmk" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelUmkTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelUmkTanggal2" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tunj. Jab. Struktural</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelJabatanStruktural" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelJabStrukturalTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelJabStrukturalTanggal2" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tunjangan Strategis</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelStrategis" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelStrategisTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelStrategisTanggal2" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tunjangan Air Listrik</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelAirListrik" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelAirListrikTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelAirListrikTanggal2" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tunjangan Perumahan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelPerumahan" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelPerumahanTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelPerumahanTanggal2" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tunjangan Peralihan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelPeralihan" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelPeralihanTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelPeralihanTanggal2" name="nip">
                            </div>
                        </div>

                        <hr>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Tunjangan Lembur</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelLembur" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapelLembur" id="rapelLemburTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapelLembur" id="rapelLemburTanggal2" name="nip">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Thr</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelThr" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelThrTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelThrTanggal2" name="nip">
                            </div>

                            <div class="col-sm-1">
                                <a href="javascript:;" class="detailRapelThr" >
                                    <span style="font-size: 25px; padding-right: 35px" class="glyphicon glyphicon-check"></span>
                                </a>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Pendidikan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelPendidikan" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelPendidikanTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelPendidikanTanggal2" name="nip">
                            </div>
                            <div class="col-sm-1">
                                <a href="javascript:;" class="detailRapelPendidikan" >
                                    <span style="font-size: 25px; padding-right: 35px" class="glyphicon glyphicon-check"></span>
                                </a>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Insentif</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelInsentif" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelInsentifTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelInsentifTanggal2" name="nip">
                            </div>
                            <div class="col-sm-1">
                                <a href="javascript:;" class="detailRapelInsentif" >
                                    <span style="font-size: 25px; padding-right: 35px" class="glyphicon glyphicon-check"></span>
                                </a>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Jubileum</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelJubileum" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelJubileumTanggal1" name="nip">
                            </div>
                            <div class="col-sm-3">
                                <input style="text-align: right" type="text" class="form-control tanggalRapel" id="rapelJubileumTanggal2" name="nip">
                            </div>
                            <div class="col-sm-1">
                                <a href="javascript:;" class="detailRapelJubileum" >
                                    <span style="font-size: 25px; padding-right: 35px" class="glyphicon glyphicon-check"></span>
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="btnSave" type="button" class="btn btn-default btn-success">Save</button>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-setting-rapel-thr" class="modal fade modal2" role="dialog">
        <div class="modal-dialog " style="width:300px;">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body" >
                    <form class="form-horizontal" id="myFormRapelThr">
                        <div class="form-group">
                            <label class="control-label col-sm-10" >Gaji Golongan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelThrGadas" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Struktural</label>
                            <div class="col-sm-1">
                                <input id="checkRapelThrStruktural" type="checkbox" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Umk</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelThrUmk" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunj. Jab. Struktural</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelThrJabStruktural" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Strategis</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelThrStrategis" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Peralihan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelThrPeralihan" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button id="btnSaveRapelThr" type="button" class="btn btn-default btn-success">Save</button>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-setting-rapel-pendidikan" class="modal fade modal2" role="dialog">
        <div class="modal-dialog " style="width:300px;">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body" >
                    <form class="form-horizontal" id="myFormRapelPendidikan">
                        <div class="form-group">
                            <label class="control-label col-sm-10" >Gaji Golongan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelPendidikanGadas" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Struktural</label>
                            <div class="col-sm-1">
                                <input id="checkRapelPendidikanStruktural" type="checkbox" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Umk</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelPendidikanUmk" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunj. Jab. Struktural</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelPendidikanJabStruktural" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Strategis</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelPendidikanStrategis" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Peralihan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelPendidikanPeralihan" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Air Listrik</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelPendidikanAirListrik" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button id="btnSaveRapelPendidikan" type="button" class="btn btn-default btn-success">Save</button>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-setting-rapel-insentif" class="modal fade modal2" role="dialog">
        <div class="modal-dialog " style="width:300px;">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body" >
                    <form class="form-horizontal" id="myFormRapelInsentif">
                        <div class="form-group">
                            <label class="control-label col-sm-10" >Gaji Golongan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelInsentifGadas" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Struktural</label>
                            <div class="col-sm-1">
                                <input id="checkRapelInsentifStruktural" type="checkbox" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Umk</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelInsentifUmk" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunj. Jab. Struktural</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelInsentifJabStruktural" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Strategis</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelInsentifStrategis" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Peralihan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelInsentifPeralihan" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="btnSaveRapelInsentif" type="button" class="btn btn-default btn-success">Save</button>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-setting-rapel-jubileum" class="modal fade modal2" role="dialog">
        <div class="modal-dialog " style="width:300px;">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body" >
                    <form class="form-horizontal" id="myFormRapelJubileum">
                        <div class="form-group">
                            <label class="control-label col-sm-10" >Gaji Golongan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelJubileumGadas" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Struktural</label>
                            <div class="col-sm-1">
                                <input id="checkRapelJubileumStruktural" type="checkbox" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Umk</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelJubileumUmk" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunj. Jab. Struktural</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelJubileumJabStruktural" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>

                        <%--<div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Strategis</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelJubileumStrategis" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>--%>

                        <div class="form-group">
                            <label class="control-label col-sm-10" >Tunjangan Peralihan</label>
                            <div class="col-sm-1">
                                <input type="checkbox" id="checkRapelJubileumPeralihan" class="checkApprove" value="rapel"
                                       onchange="" style="float: right; padding-right: 100px">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="btnSaveRapelJubileum" type="button" class="btn btn-default btn-success">Save</button>
                    <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

</div>
</html>

<script>
    $(document).ready(function() {
        window.changeRapelTanggal1 = function (id) {
            $('#rapelStrukturalTanggal1').val(id.value);
            $('#rapelUmkTanggal1').val(id.value);
            $('#rapelJabStrukturalTanggal1').val(id.value);
            $('#rapelStrategisTanggal1').val(id.value);
            $('#rapelAirListrikTanggal1').val(id.value);
            $('#rapelPerumahanTanggal1').val(id.value);
            $('#rapelLemburTanggal1').val(id.value);
            $('#rapelThrTanggal1').val(id.value);
            $('#rapelPendidikanTanggal1').val(id.value);
            $('#rapelInsentifTanggal1').val(id.value);
            $('#rapelJubileumTanggal1').val(id.value);
        }

        window.changeRapelTanggal2 = function (id) {
            $('#rapelStrukturalTanggal2').val(id.value);
            $('#rapelUmkTanggal2').val(id.value);
            $('#rapelJabStrukturalTanggal2').val(id.value);
            $('#rapelStrategisTanggal2').val(id.value);
            $('#rapelAirListrikTanggal2').val(id.value);
            $('#rapelPerumahanTanggal2').val(id.value);
            $('#rapelLemburTanggal2').val(id.value);
            $('#rapelThrTanggal2').val(id.value);
            $('#rapelPendidikanTanggal2').val(id.value);
            $('#rapelInsentifTanggal2').val(id.value);
            $('#rapelJubileumTanggal2').val(id.value);
        }

        $('.tanggalRapel').datepicker({
            dateFormat: 'mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('.tanggalRapelLembur').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        document.getElementById("checkPayroll").checked = true;
        $('.detailRapel').on('click', function(){
            if(document.getElementById("flagRapelGadas").value == "Y"){
                $('#checkRapelGadas').prop('checked', true);
            }else{
                $('#checkRapelGadas').prop('checked', false);
            }

            if(document.getElementById("flagRapelStruktural").value == "Y" ){
                $('#checkRapelStruktural').prop('checked', true);
            }else{
                $('#checkRapelStruktural').prop('checked', false);
            }

            if(document.getElementById("flagRapelUmk").value == "Y"){
                $('#checkRapelUmk').prop('checked', true);
            }else{
                $('#checkRapelUmk').prop('checked', false);
            }

            if(document.getElementById("flagRapelJabatanStruktural").value == "Y"){
                $('#checkRapelJabatanStruktural').prop('checked', true);
            }else{
                $('#checkRapelJabatanStruktural').prop('checked', false);
            }

            if(document.getElementById("flagRapelStrategis").value == "Y"){
                $('#checkRapelStrategis').prop('checked', true);
            }else{
                $('#checkRapelStrategis').prop('checked', false);
            }

            if(document.getElementById("flagRapelAirListrik").value == "Y"){
                $('#checkRapelAirListrik').prop('checked', true);
            }else{
                $('#checkRapelAirListrik').prop('checked', false);
            }

            if(document.getElementById("flagRapelLembur").value == "Y"){
                $('#checkRapelLembur').prop('checked', true);
            }else{
                $('#checkRapelLembur').prop('checked', false);
            }

            if(document.getElementById("flagRapelThr").value == "Y"){
                $('#checkRapelThr').prop('checked', true);
            }else{
                $('#checkRapelThr').prop('checked', false);
            }

            if(document.getElementById("flagRapelPendidikan").value == "Y"){
                $('#checkRapelPendidikan').prop('checked', true);
            }else{
                $('#checkRapelPendidikan').prop('checked', false);
            }

            if(document.getElementById("flagRapelInsentif").value == "Y"){
                $('#checkRapelInsentif').prop('checked', true);
            }else{
                $('#checkRapelInsentif').prop('checked', false);
            }

            if(document.getElementById("flagRapelJubileum").value == "Y"){
                $('#checkRapelJubileum').prop('checked', true);
            }else{
                $('#checkRapelJubileum').prop('checked', false);
            }

            if(document.getElementById("flagRapelPerumahan").value == "Y"){
                $('#checkRapelPerumahan').prop('checked', true);
            }else{
                $('#checkRapelPerumahan').prop('checked', false);
            }

            if(document.getElementById("flagRapelPeralihan").value == "Y"){
                $('#checkRapelPeralihan').prop('checked', true);
            }else{
                $('#checkRapelPeralihan').prop('checked', false);
            }


            // THR
            if(document.getElementById("flagRapelThrGadas").value == "Y"){
                $('#checkRapelThrGadas').prop('checked', true);
            }else{
                $('#checkRapelThrGadas').prop('checked', false);
            }

            if(document.getElementById("flagRapelThrStruktural").value == "Y"){
                $('#checkRapelThrStruktural').prop('checked', true);
            }else{
                $('#checkRapelThrStruktural').prop('checked', false);
            }

            if(document.getElementById("flagRapelThrUmk").value == "Y"){
                $('#checkRapelThrUmk').prop('checked', true);
            }else{
                $('#checkRapelThrUmk').prop('checked', false);
            }

            if(document.getElementById("flagRapelThrJabStruktural").value == "Y"){
                $('#checkRapelThrJabStruktural').prop('checked', true);
            }else{
                $('#checkRapelThrJabStruktural').prop('checked', false);
            }

            if(document.getElementById("flagRapelThrStrategis").value == "Y" ){
                $('#checkRapelThrStrategis').prop('checked', true);
            }else{
                $('#checkRapelThrStrategis').prop('checked', false);
            }

            if(document.getElementById("flagRapelThrPeralihan").value == "Y"){
                $('#checkRapelThrPeralihan').prop('checked', true);
            }else{
                $('#checkRapelThrPeralihan').prop('checked', false);
            }

            // Pendidikan
            if(document.getElementById("flagRapelPendidikanGadas").value == "Y"){
                $('#checkRapelPendidikanGadas').prop('checked', true);
            }else{
                $('#checkRapelPendidikanGadas').prop('checked', false);
            }

            if(document.getElementById("flagRapelPendidikanStruktural").value == "Y" ){
                $('#checkRapelPendidikanStruktural').prop('checked', true);
            }else{
                $('#checkRapelPendidikanStruktural').prop('checked', false);
            }

            if(document.getElementById("flagRapelPendidikanUmk").value == "Y"){
                $('#checkRapelPendidikanUmk').prop('checked', true);
            }else{
                $('#checkRapelPendidikanUmk').prop('checked', false);
            }

            if(document.getElementById("flagRapelPendidikanJabStruktural").value == "Y"){
                $('#checkRapelPendidikanJabStruktural').prop('checked', true);
            }else{
                $('#checkRapelPendidikanJabStruktural').prop('checked', false);
            }

            if(document.getElementById("flagRapelPendidikanStrategis").value == "Y" ){
                $('#checkRapelPendidikanStrategis').prop('checked', true);
            }else{
                $('#checkRapelPendidikanStrategis').prop('checked', false);
            }

            if(document.getElementById("flagRapelPendidikanPeralihan").value == "Y"){
                $('#checkRapelPendidikanPeralihan').prop('checked', true);
            }else{
                $('#checkRapelPendidikanPeralihan').prop('checked', false);
            }

            if(document.getElementById("flagRapelPendidikanAirListrik").value == "Y"){
                $('#checkRapelPendidikanAirListrik').prop('checked', true);
            }else{
                $('#checkRapelPendidikanAirListrik').prop('checked', false);
            }

            // Insentif
            if(document.getElementById("flagRapelInsentifGadas").value == "Y"){
                $('#checkRapelInsentifGadas').prop('checked', true);
            }else{
                $('#checkRapelInsentifGadas').prop('checked', false);
            }

            if(document.getElementById("flagRapelInsentifStruktural").value == "Y" ){
                $('#checkRapelInsentifStruktural').prop('checked', true);
            }else{
                $('#checkRapelInsentifStruktural').prop('checked', false);
            }

            if(document.getElementById("flagRapelInsentifUmk").value == "Y"){
                $('#checkRapelInsentifUmk').prop('checked', true);
            }else{
                $('#checkRapelInsentifUmk').prop('checked', false);
            }

            if(document.getElementById("flagRapelInsentifJabStruktural").value == "Y"){
                $('#checkRapelInsentifJabStruktural') .prop('checked', true);
            }else{
                $('#checkRapelInsentifJabStruktural') .prop('checked', false);
            }

            if(document.getElementById("flagRapelInsentifStrategis").value == "Y"){
                $('#checkRapelInsentifStrategis').prop('checked', true);
            }else{
                $('#checkRapelInsentifStrategis').prop('checked', false);
            }

            if(document.getElementById("flagRapelInsentifPeralihan").value == "Y"){
                $('#checkRapelInsentifPeralihan').prop('checked', true);
            }else{
                $('#checkRapelInsentifPeralihan').prop('checked', false);
            }

            // Jubileum
            if(document.getElementById("flagRapelJubileumGadas").value == "Y"){
                $('#checkRapelJubileumGadas').prop('checked', true);
            }else{
                $('#checkRapelJubileumGadas').prop('checked', false);
            }

            if(document.getElementById("flagRapelJubileumStruktural").value == "Y"){
                $('#checkRapelJubileumStruktural').prop('checked', true);
            }else{
                $('#checkRapelJubileumStruktural').prop('checked', false);
            }

            if(document.getElementById("flagRapelJubileumUmk").value == "Y"){
                $('#checkRapelJubileumUmk').prop('checked', true);
            }else{
                $('#checkRapelJubileumUmk').prop('checked', false);
            }

            if(document.getElementById("flagRapelJubileumJabStruktural").value == "Y"){
                $('#checkRapelJubileumJabStruktural').prop('checked', true);
            }else{
                $('#checkRapelJubileumJabStruktural').prop('checked', false);
            }

            /*if(document.getElementById("flagRapelJubileumStrategis").value == "Y"){
             $('#checkRapelJubileumStrategis').prop('checked', true);
             }else{
             $('#checkRapelJubileumStrategis').prop('checked', false);
             }*/

            if(document.getElementById("flagRapelJubileumPeralihan").value == "Y"){
                $('#checkRapelJubileumPeralihan').prop('checked', true);
            }else{
                $('#checkRapelJubileumPeralihan').prop('checked', false);
            }

            $('#modal-setting-rapel').find('.modal-title').text('Setting Rapel');
            $('#modal-setting-rapel').modal('show');
        });

        $('.detailRapelThr').on('click', function(){
            $('#modal-setting-rapel-thr').find('.modal-title').text('Setting Rapel THR');
            $('#modal-setting-rapel-thr').modal('show');
        });

        $('.detailRapelPendidikan').on('click', function(){
            $('#modal-setting-rapel-pendidikan').find('.modal-title').text('Setting Rapel Pendidikan');
            $('#modal-setting-rapel-pendidikan').modal('show');
        });

        $('.detailRapelInsentif').on('click', function(){
            $('#modal-setting-rapel-insentif').find('.modal-title').text('Setting Rapel Insentif');
            $('#modal-setting-rapel-insentif').modal('show');
        });

        $('.detailRapelJubileum').on('click', function(){
            $('#modal-setting-rapel-jubileum').find('.modal-title').text('Setting Rapel Jubileum');
            $('#modal-setting-rapel-jubileum').modal('show');
        });

        /*$('.detailInsentif').on('click', function(){
            var bulanMulai = document.getElementById("insentifBulanMulai").value ;
            var bulanSampai = document.getElementById("insentifBulanSampai").value ;
            var tahunInsentif = document.getElementById("insentifTahun").value ;

            $('#insentifModalBulanMulai').val(bulanMulai).change();
            $('#insentifModalBulanSampai').val(bulanSampai).change();
            $('#insentifModalTahun').val(tahunInsentif).change();

            $('#modal-setting-insentif').find('.modal-title').text('Detail Insentif');
            $('#modal-setting-insentif').modal('show');
        });*/

        $('#btnSave').click(function() {
            if (confirm('Are you sure you want to save this Record?')) {
                if($('#checkRapelGadas').is(":checked")){
                    document.getElementById("flagRapelGadas").value = "Y";
                }else{
                    document.getElementById("flagRapelGadas").value = "N";
                }

                if($('#checkRapelStruktural').is(":checked")){
                    document.getElementById("flagRapelStruktural").value = "Y";
                }else{
                    document.getElementById("flagRapelStruktural").value = "N";
                }

                if($('#checkRapelUmk').is(":checked")){
                    document.getElementById("flagRapelUmk").value = "Y";
                }else{
                    document.getElementById("flagRapelUmk").value = "N";
                }

                if($('#checkRapelJabatanStruktural').is(":checked")){
                    document.getElementById("flagRapelJabatanStruktural").value = "Y";
                }else{
                    document.getElementById("flagRapelJabatanStruktural").value = "N";
                }

                if($('#checkRapelStrategis').is(":checked")){
                    document.getElementById("flagRapelStrategis").value = "Y";
                }else{
                    document.getElementById("flagRapelStrategis").value = "N";
                }

                if($('#checkRapelAirListrik').is(":checked")){
                    document.getElementById("flagRapelAirListrik").value = "Y";
                }else{
                    document.getElementById("flagRapelAirListrik").value = "N";
                }

                if($('#checkRapelLembur').is(":checked")){
                    document.getElementById("flagRapelLembur").value = "Y";
                }else{
                    document.getElementById("flagRapelLembur").value = "N";
                }

                if($('#checkRapelThr').is(":checked")){
                    document.getElementById("flagRapelThr").value = "Y";
                }else{
                    document.getElementById("flagRapelThr").value = "N";
                }

                if($('#checkRapelPendidikan').is(":checked")){
                    document.getElementById("flagRapelPendidikan").value = "Y";
                }else{
                    document.getElementById("flagRapelPendidikan").value = "N";
                }

                if($('#checkRapelInsentif').is(":checked")){
                    document.getElementById("flagRapelInsentif").value = "Y";
                }else{
                    document.getElementById("flagRapelInsentif").value = "N";
                }

                if($('#checkRapelJubileum').is(":checked")){
                    document.getElementById("flagRapelJubileum").value = "Y";
                }else{
                    document.getElementById("flagRapelJubileum").value = "N";
                }

                if($('#checkRapelPerumahan').is(":checked")){
                    document.getElementById("flagRapelPerumahan").value = "Y";
                }else{
                    document.getElementById("flagRapelPerumahan").value = "N";
                }


                // THR
                if($('#checkRapelThrGadas').is(":checked")){
                    document.getElementById("flagRapelThrGadas").value = "Y";
                }else{
                    document.getElementById("flagRapelThrGadas").value = "N";
                }

                if($('#checkRapelThrStruktural').is(":checked")){
                    document.getElementById("flagRapelThrStruktural").value = "Y";
                }else{
                    document.getElementById("flagRapelThrStruktural").value = "N";
                }

                if($('#checkRapelThrUmk').is(":checked")){
                    document.getElementById("flagRapelThrUmk").value = "Y";
                }else{
                    document.getElementById("flagRapelThrUmk").value = "N";
                }

                if($('#checkRapelThrJabStruktural').is(":checked")){
                    document.getElementById("flagRapelThrJabStruktural").value = "Y";
                }else{
                    document.getElementById("flagRapelThrJabStruktural").value = "N";
                }

                if($('#checkRapelThrStrategis').is(":checked")){
                    document.getElementById("flagRapelThrStrategis").value = "Y";
                }else{
                    document.getElementById("flagRapelThrStrategis").value = "N";
                }
                if($('#checkRapelThrPeralihan').is(":checked")){
                    document.getElementById("flagRapelThrPeralihan").value = "Y";
                }else{
                    document.getElementById("flagRapelThrPeralihan").value = "N";
                }

                // Pendidikan
                if($('#checkRapelPendidikanGadas').is(":checked")){
                    document.getElementById("flagRapelPendidikanGadas").value = "Y";
                }else{
                    document.getElementById("flagRapelPendidikanGadas").value = "N";
                }

                if($('#checkRapelPendidikanStruktural').is(":checked")){
                    document.getElementById("flagRapelPendidikanStruktural").value = "Y";
                }else{
                    document.getElementById("flagRapelPendidikanStruktural").value = "N";
                }

                if($('#checkRapelPendidikanUmk').is(":checked")){
                    document.getElementById("flagRapelPendidikanUmk").value = "Y";
                }else{
                    document.getElementById("flagRapelPendidikanUmk").value = "N";
                }

                if($('#checkRapelPendidikanJabStruktural').is(":checked")){
                    document.getElementById("flagRapelPendidikanJabStruktural").value = "Y";
                }else{
                    document.getElementById("flagRapelPendidikanJabStruktural").value = "N";
                }

                if($('#checkRapelPendidikanStrategis').is(":checked")){
                    document.getElementById("flagRapelPendidikanStrategis").value = "Y";
                }else{
                    document.getElementById("flagRapelPendidikanStrategis").value = "N";
                }

                if($('#checkRapelPendidikanPeralihan').is(":checked")){
                    document.getElementById("flagRapelPendidikanPeralihan").value = "Y";
                }else{
                    document.getElementById("flagRapelPendidikanPeralihan").value = "N";
                }

                if($('#checkRapelPendidikanAirListrik').is(":checked")){
                    document.getElementById("flagRapelPendidikanAirListrik").value = "Y";
                }else{
                    document.getElementById("flagRapelPendidikanAirListrik").value = "N";
                }

                // Insentif
                if($('#checkRapelInsentifGadas').is(":checked")){
                    document.getElementById("flagRapelInsentifGadas").value = "Y";
                }else{
                    document.getElementById("flagRapelInsentifGadas").value = "N";
                }

                if($('#checkRapelInsentifStruktural').is(":checked")){
                    document.getElementById("flagRapelInsentifStruktural").value = "Y";
                }else{
                    document.getElementById("flagRapelInsentifStruktural").value = "N";
                }

                if($('#checkRapelInsentifUmk').is(":checked")){
                    document.getElementById("flagRapelInsentifUmk").value = "Y";
                }else{
                    document.getElementById("flagRapelInsentifUmk").value = "N";
                }

                if($('#checkRapelInsentifJabStruktural').is(":checked")){
                    document.getElementById("flagRapelInsentifJabStruktural").value = "Y";
                }else{
                    document.getElementById("flagRapelInsentifJabStruktural").value = "N";
                }

                if($('#checkRapelInsentifStrategis').is(":checked")){
                    document.getElementById("flagRapelInsentifStrategis").value = "Y";
                }else{
                    document.getElementById("flagRapelInsentifStrategis").value = "N";
                }

                if($('#checkRapelInsentifPeralihan').is(":checked")){
                    document.getElementById("flagRapelInsentifPeralihan").value = "Y";
                }else{
                    document.getElementById("flagRapelInsentifPeralihan").value = "N";
                }

                // Jubileum
                if($('#checkRapelJubileumGadas').is(":checked")){
                    document.getElementById("flagRapelJubileumGadas").value = "Y";
                }else{
                    document.getElementById("flagRapelJubileumGadas").value = "N";
                }

                if($('#checkRapelJubileumStruktural').is(":checked")){
                    document.getElementById("flagRapelJubileumStruktural").value = "Y";
                }else{
                    document.getElementById("flagRapelJubileumStruktural").value = "N";
                }

                if($('#checkRapelJubileumUmk').is(":checked")){
                    document.getElementById("flagRapelJubileumUmk").value = "Y";
                }else{
                    document.getElementById("flagRapelJubileumUmk").value = "N";
                }

                if($('#checkRapelJubileumJabStruktural').is(":checked")){
                    document.getElementById("flagRapelJubileumJabStruktural").value = "Y";
                }else{
                    document.getElementById("flagRapelJubileumJabStruktural").value = "N";
                }

                /*if($('#checkRapelJubileumStrategis').is(":checked")){
                 document.getElementById("flagRapelJubileumStrategis").value = "Y";
                 }else{
                 document.getElementById("flagRapelJubileumStrategis").value = "N";
                 }*/

                if($('#checkRapelJubileumPeralihan').is(":checked")){
                    document.getElementById("flagRapelJubileumPeralihan").value = "Y";
                }else{
                    document.getElementById("flagRapelJubileumPeralihan").value = "N";
                }


                $("#flagRapelGadasTanggal1").val($("#rapelGadasTanggal1").val());
                $("#flagRapelStrukturalTanggal1").val($("#rapelStrukturalTanggal1").val());
                $("#flagRapelUmkTanggal1").val($("#rapelUmkTanggal1").val());
                $("#flagRapelJabatanStrukturalTanggal1").val($("#rapelJabStrukturalTanggal1").val());
                $("#flagRapelStrategisTanggal1").val($("#rapelStrategisTanggal1").val());
                $("#flagRapelAirListrikTanggal1").val($("#rapelAirListrikTanggal1").val());
                $("#flagRapelPerumahanTanggal1").val($("#rapelPerumahanTanggal1").val());
                $("#flagRapelLemburTanggal1").val($("#rapelLemburTanggal1").val());
                $("#flagRapelThrTanggal1").val($("#rapelThrTanggal1").val());
                $("#flagRapelPendidikanTanggal1").val($("#rapelPendidikanTanggal1").val());
                $("#flagRapelInsentifTanggal1").val($("#rapelInsentifTanggal1").val());
                $("#flagRapelJubileumTanggal1").val($("#rapelJubileumTanggal1").val());

                $("#flagRapelGadasTanggal2").val($("#rapelGadasTanggal2").val());
                $("#flagRapelStrukturalTanggal2").val($("#rapelStrukturalTanggal2").val());
                $("#flagRapelUmkTanggal2").val($("#rapelUmkTanggal2").val());
                $("#flagRapelJabatanStrukturalTanggal2").val($("#rapelJabStrukturalTanggal2").val());
                $("#flagRapelStrategisTanggal2").val($("#rapelStrategisTanggal2").val());
                $("#flagRapelAirListrikTanggal2").val($("#rapelAirListrikTanggal2").val());
                $("#flagRapelPerumahanTanggal2").val($("#rapelPerumahanTanggal2").val());
                $("#flagRapelLemburTanggal2").val($("#rapelLemburTanggal2").val());
                $("#flagRapelThrTanggal2").val($("#rapelThrTanggal2").val());
                $("#flagRapelPendidikanTanggal2").val($("#rapelPendidikanTanggal2").val());
                $("#flagRapelInsentifTanggal2").val($("#rapelInsentifTanggal2").val());
                $("#flagRapelJubileumTanggal2").val($("#rapelJubileumTanggal2").val());

                alert('Data tersimpan');
                $('#modal-setting-rapel').modal('hide');
            }
        });

        $('#btnSaveRapelThr').click(function() {
            if (confirm('Are you sure you want to save this Record?')) {

                alert('Data tersimpan');
                $('#modal-setting-rapel-thr').modal('hide');
            }
        });

        $('#btnSaveRapelPendidikan').click(function() {
            if (confirm('Are you sure you want to save this Record?')) {

                alert('Data tersimpan');
                $('#modal-setting-rapel-pendidikan').modal('hide');
            }
        });

        $('#btnSaveRapelInsentif').click(function() {
            if (confirm('Are you sure you want to save this Record?')) {
                alert('Data tersimpan');
                $('#modal-setting-rapel-insentif').modal('hide');
            }
        });
        $('#btnSaveRapelJubileum').click(function() {
            if (confirm('Are you sure you want to save this Record?')) {
                alert('Data tersimpan');
                $('#modal-setting-rapel-jubileum').modal('hide');
            }
        });
    });

    window.changePayroll = function (id) {
        if($('#checkPayroll').is(":checked")){
            document.getElementById("flagPayroll").value = "Y";
        }else{
            document.getElementById("flagPayroll").value = "N";
        }
    }

    window.changeRapel = function (id) {
        if($('#checkRapel').is(":checked")){
            document.getElementById("flagRapel").value = "Y";
            $('#tempatPilihanRapelGadas').show();
            $('#tempatPilihanRapelStruktural').show();
            $('#tempatPilihanRapelUmk').show();
            $('#tempatPilihanRapelJabatanStruktural').show();
            $('#tempatPilihanRapelStrategis').show();
            $('#tempatPilihanRapelAirListrik').show();
            $('#tempatPilihanRapelPerumahan').show();
        }else{
            $('#tempatPilihanRapelGadas').hide();
            $('#tempatPilihanRapelStruktural').hide();
            $('#tempatPilihanRapelUmk').hide();
            $('#tempatPilihanRapelJabatanStruktural').hide();
            $('#tempatPilihanRapelStrategis').hide();
            $('#tempatPilihanRapelAirListrik').hide();
            $('#tempatPilihanRapelPerumahan').hide();
            document.getElementById("flagRapel").value = "N";
        }
    }

    window.changeThr = function (id) {
        document.getElementById("flagThr").value = "Y";
        /*if($('#checkThr').is(":checked")){
            document.getElementById("flagThr").value = "Y";
            $('#tempatTipeThr').show();
        }else{
            $('#tempatTipeThr').hide();
            document.getElementById("flagThr").value = "N";
        }*/
    }

    window.changeCutiTahunan = function (id) {
        if($('#checkCutiTahunan').is(":checked")){
            document.getElementById("flagCutiTahunan").value = "Y";
        }else{
            document.getElementById("flagCutiTahunan").value = "N";
        }
    }
    window.changeCutiPanjang = function (id) {
        if($('#checkCutiPanjang').is(":checked")){
            document.getElementById("flagCutiPanjang").value = "Y";
        }else{
            document.getElementById("flagCutiPanjang").value = "N";
        }
    }

    window.changeJasprod = function (id) {
        if($('#checkJasprod').is(":checked")){
            document.getElementById("flagJasprod").value = "Y";
        }else{
            document.getElementById("flagJasprod").value = "N";
        }
    }

    window.changeJubileum = function (id) {
        if($('#checkJubileum').is(":checked")){
            document.getElementById("flagJubileum").value = "Y";
        }else{
            document.getElementById("flagJubileum").value = "N";
        }
    }

    window.changePesangon = function (id) {
        if($('#checkPesangon').is(":checked")){
            document.getElementById("flagPensiun").value = "Y";
        }else{
            document.getElementById("flagPensiun").value = "N";
        }
    }

    window.changeInsentif = function (id) {
        if($('#checkInsentif').is(":checked")){
            document.getElementById("flagInsentif").value = "Y";
        }else{
            document.getElementById("flagInsentif").value = "N";
        }
    }

    window.changeBulan = function (id) {
        $('#bulanCutiTahunan').val(id.value).change();
        $('#bulanThr').val(id.value).change();
        $('#bulanCutiPanjang').val(id.value).change();
        $('#bulanJasprod').val(id.value).change();
        $('#bulanJubileum').val(id.value).change();
        $('#bulanPesangon').val(id.value).change();
        $('#bulanInsentif').val(id.value).change();
    }

    window.changeTahun = function (id) {
        $('#tahunCutiTahunan').val(id.value).change();
        $('#tahunCutiPanjang').val(id.value).change();
        $('#tahunThr').val(id.value).change();
        $('#tahunJasprod').val(id.value).change();
        $('#tahunJubileum').val(id.value).change();
        $('#tahunPesangon').val(id.value).change();
        $('#tahunInsentif').val(id.value).change();
    }

    /*$('#btnSaveDetailInsentif').click(function() {
        if (confirm('Are you sure you want to save this Record?')) {
            $('#insentifBulanMulai').val($('#insentifModalBulanMulai').val());
            $('#insentifBulanSampai').val($('#insentifModalBulanSampai').val());
            $('#insentifTahun').val($('#insentifModalTahun').val());

            alert('Data tersimpan');
            $('#modal-setting-insentif').modal('hide');
        }
    });*/



</script>