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
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserLoginAction.js"/>'></script>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>--%>

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

        <%--function link(){--%>
            <%--window.location.href="<s:url action='loadAdd_payroll'/>";--%>
        <%--}--%>



        $(document).ready(function() {
            // window.cekPayroll = function(branch, bulan, tahun){
            //     var isFlag = 'N';
            //     PayrollAction.cekAvailable(branch, bulan, tahun, function(listdata) {
            //         isFlag = listdata;
            //     });
            //     return isFlag;
            // }
            // window.cekBeforePayroll = function(branch, bulan, tahun){
            //     var isFlag = 'N';
            //     PayrollAction.cekBeforePayroll(branch, bulan, tahun, function(listdata) {
            //         isFlag = listdata;
            //     });
            //     return isFlag;
            // }
            // window.cekApprove = function(branch, bulan, tahun, tipe){
            //     var isFlag = 'Y';
            //     PayrollAction.cekApprove(branch, bulan, tahun, tipe, function(listdata) {
            //         isFlag = listdata;
            //     });
            //     return isFlag;
            // }

            window.closedPopup = function() {
                //$('#waiting_dialog').dialog('close');
                // $('#view_dialog_menu').dialog('close');
                $('#info_dialog_add').dialog('close');
                <%--window.location.href="<s:url action='loadAdd_payroll.action'/>";--%>
                window.location.href="<s:url action='add_payroll.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var branch = document.getElementById("branchId").value;
                var bulan = document.getElementById("bulanPayroll").value;
                var tahun = document.getElementById("tahunPayroll").value;

                if (branch != '' && bulan != '0' && tahun != '') {

                    if (confirm('Do you want to save this for periode =' + bulan + '-' + tahun + ' ?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');

                    } else {
                        // Cancel Submit comes with 1.8.0
                        event.originalEvent.options.submit = false;
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

                    if (tahun == '') {
                        msg += 'Field <strong>Tahun</strong> is required.' + '<br/>';
                    }

                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');

                }
            });

            $.subscribe('successDialog', function (event, data) {
                if (event.originalEvent.request.status == 200) {
                    jQuery(".ui-dialog-titlebar-close").hide();
                    $.publish('showInfoDialogAdd');
                }
            });

            $.subscribe('errorDialogSearch', function (event, data) {
                document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
                $.publish('showErrorDialog');
            });

            // $.subscribe('gantiHalaman', function (event, data) {
            //     window.location.href = 'loadAdd_payroll.action';
            // });
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
                                        <s:form id="addPayrollForm" method="post"  theme="simple" namespace="/payroll" action="saveAdd_payroll.action" cssClass="form-horizontal">

                                            <%--<s:hidden name="addOrEdit"/>--%>
                                            <%--<s:hidden name="delete"/>--%>
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
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:if test="%{payrollHeader.kantorPusat}">
                                                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="payrollHeader.branchId"
                                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:select list="#initComboBranch.listOfComboBranch" id="unitTmp" name="payrollHeader.branchId" disabled="true"
                                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                                <s:hidden id="branchId" name="payrollHeader.branchId" />
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
                                                                      id="bulanPayroll" name="payrollHeader.bulan"
                                                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                        </table>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:action id="comboPeriode" namespace="/payroll" name="initComboPeriodeTahunKurang10_payroll"/>
                                                            <%--<s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>--%>
                                                            <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll"
                                                                      name="payrollHeader.tahun" required="true" headerKey=""
                                                                      headerValue="[Select one]"/>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <s:if test="%{!payrollHeader.afterSave}">
                                                    <script>
                                                        var dt = new Date();
                                                        $('#bulanPayroll').val(("0" + (dt.getMonth() + 1)).slice(-2));
                                                        $('#tahunPayroll').val(dt.getFullYear());
                                                    </script>
                                                </s:if>
                                                <s:else>
                                                    <script>
                                                        $('#bulanPayroll').attr('disabled', true);
                                                        $('#tahunPayroll').attr('disabled', true);
                                                        $('#tipe').attr('disabled', true);
                                                    </script>
                                                </s:else>
                                                <td>
                                                    <label class="control-label"><small>Tipe Payroll :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'PY':'Payroll', 'TH':'THR', 'CP':'Cuti Panjang', 'CT':'Cuti Tahunan', 'JP':'Jasa Operasional', 'JB':'PMP',
                                                        'PN':'SHT(Pensiun)', 'IN':'Insentif','RP':'Rapel'}" id="tipe" name="payrollHeader.tipePayroll" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </table>
                                            <br>
                                            <div id="actions" class="form-actions">
                                                <table align="center">
                                                    <tr>
                                                        <td>
                                                            <%--<sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="payrollForm" id="save" name="save"--%>
                                                                       <%--onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog"--%>
                                                                       <%--onSuccessTopics="gantiHalaman" onErrorTopics="errorDialogSearch" >--%>
                                                                <%--<i class="fa fa-search"></i>--%>
                                                                <%--Search--%>
                                                            <%--</sj:submit>--%>

                                                                <s:if test="%{!payrollHeader.afterSave}">
                                                                    <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addPayrollForm" id="save" name="save"
                                                                               onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                               onSuccessTopics="successDialog" onErrorTopics="errorDialogSearch" >
                                                                        <i class="fa fa-save"></i>
                                                                        Save
                                                                    </sj:submit>
                                                                </s:if>
                                                        </td>
                                                        <%--<td>--%>
                                                            <%--<button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="add_payroll.action"/>'">--%>
                                                                <%--<i class="fa fa-refresh"></i> Refresh--%>
                                                            <%--</button>--%>
                                                        <%--</td>--%>
                                                        <td>
                                                            <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_payroll.action"/>'">
                                                                <i class="fa fa-close"></i> Close
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

                                                            <%--<sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"--%>
                                                                       <%--height="500" width="500" autoOpen="false"--%>
                                                                       <%--title="Payroll ">--%>
                                                                <%--<center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>--%>
                                                            <%--</sj:dialog>--%>

                                                            <s:set name="listDataPayroll" value="#session.listDataPayroll" scope="request" />
                                                            <display:table name="listDataPayroll" class="tablePayroll table table-condensed table-striped table-hover"
                                                                           requestURI="paging_displaytag_payroll.action" export="true" id="row" pagesize="1000" style="font-size:9">

                                                                <display:column property="nip" sortable="true" title="NIP"  />
                                                                <display:column property="namaPegawai" sortable="true" title="Nama" />
                                                                <display:column property="divisiName" sortable="true" title="Bidang" />
                                                                <display:column property="posisiName" sortable="true" title="Jabatan" />
                                                                <display:column style="text-align:right;" property="componentA" sortable="true" title="Gaji" />
                                                                <display:column style="text-align:right;" property="componentB" sortable="true" title="Tot.Tunj." />
                                                                <display:column style="text-align:right;" property="componentC" sortable="true" title="Tot.Pot.Tnp.PPh" />
                                                                <display:column style="text-align:right;" property="tunjanganPph" sortable="true" title="PPh" />
                                                                <display:column style="text-align:right;" property="gajiBersih" sortable="true" title="Gaji.Bersih" />
                                                                <display:column style="text-align:center;" property="multifikator" sortable="true" title="Prop.Gaji" />
                                                                <display:column style="color: #00cc00;" property="stTanggalPraPensiun" sortable="true" title="PMP" />
                                                                <display:column style="color: red;" property="stTanggalPensiun" sortable="true" title="Pensiun" />

                                                                <display:setProperty name="paging.banner.item_name">PayrollDetail</display:setProperty>
                                                                <display:setProperty name="paging.banner.items_name">PayrollDetail</display:setProperty>
                                                                <display:setProperty name="export.excel.filename">PayrollDetail.xls</display:setProperty>
                                                                <display:setProperty name="export.csv.filename">PayrollDetail.csv</display:setProperty>
                                                                <display:setProperty name="export.pdf.filename">PayrollDetail.pdf</display:setProperty>



                                                            </display:table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </center>

                                            <br>
                                                <sj:dialog id="waiting_dialog_save" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="350" width="600" autoOpen="false" title="Saving ...">
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

                                                <sj:dialog id="info_dialog_add" openTopics="showInfoDialogAdd" modal="true" resizable="false" closeOnEscape="false"
                                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                           buttons="{
                                                                  'OK':function() {
                                                                          closedPopup();
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
