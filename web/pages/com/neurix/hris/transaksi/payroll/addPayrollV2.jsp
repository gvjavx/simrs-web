<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PayrollAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript'>
        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {
            window.clos = function() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='initForm_payroll.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    event.originalEvent.options.submit = false;
                }
            });
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
            Add Payroll
            <small>GO-MEDSYS</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Hasil Payroll</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="payrollForm" method="post"  theme="simple" namespace="/payroll" action="saveAdd_payroll.action" cssClass="form-horizontal">
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
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="payroll.branchId"
                                                                  listKey="branchId" listValue="branchName" headerKey="" disabled="true"
                                                                  headerValue="[Select one]" cssClass="form-control"/>
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
                                                                  id="bulanPayroll" name="payroll.bulan" disabled="true"
                                                                  headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll" headerKey="" disabled="true"
                                                                  headerValue="[Select one]" name="payroll.tahun"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'PR':'Payroll', 'T':'THR', 'PD':'Pendidikan', 'R':'Rapel',
                                        'JP':'Jasprod', 'JB':'Jubileum', 'PN':'Pensiun'}" id="tipe" name="payroll.tipe"
                                                                  cssClass="form-control" disabled="true" />
                                                        <s:hidden name="payroll.tipe" />
                                                    </table>

                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield cssStyle="display: none" id="personName1" name="payroll.nip" readonly="false" cssClass="form-control"/>
                                                        <s:textfield  id="personName2" readonly="false" cssClass="form-control"/>
                                                        <s:textfield cssStyle="display: none" id="personName" name="payroll.name" readonly="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                                <script type='text/javascript'>
                                                    var functions, mapped;
                                                    $('#personName2').typeahead({
                                                        minLength: 1,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};

                                                            var data = [];
                                                            dwr.engine.setAsync(false);
                                                            MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
                                                                data = listdata;
                                                            });
                                                            $.each(data, function (i, item) {
                                                                var labelItem =item.nip+ " || "+ item.namaPegawai;
                                                                var labelNip = item.nip;
                                                                mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem, branchId : item.branch, divisiId: item.divisi, positionId : item.positionId };
                                                                functions.push(labelItem);
                                                            });


                                                            process(functions);
                                                        },

                                                        updater: function (item) {
                                                            var selectedObj = mapped[item];
                                                            var namaAlat = selectedObj.label;
                                                            document.getElementById("personName1").value = selectedObj.id;
                                                            document.getElementById("personName").value = selectedObj.pegawai;

                                                            branc = selectedObj.branchId;
                                                            dev = selectedObj.divisiId ;
                                                            return namaAlat;
                                                        }
                                                    });

                                                </script>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <button type="button" class="btn btn-primary" onclick="searchData();">
                                                            <i class="fa fa-search"></i> Search
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <sj:submit targets="crsud" type="button" cssClass="btn btn-success" formIds="payrollForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog" onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="fa fa-save"></i>
                                                            Save
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="menujuLink('cancelPage_payroll.action', 'membatalkan proses');">
                                                            <i class="fa fa-close"></i> Cancel
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <br>

                                        <center>
                                            <table id="showdata" width="95%">
                                                <tr>
                                                    <td align="center">
                                                        <s:set name="listDataPayroll" value="#session.listDataPayrollSearch" scope="request" />
                                                        <display:table name="listDataPayroll" class="tablePayroll table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_payroll.action" export="true" id="row" pagesize="40" style="font-size:10">
                                                            <s:if test="%{payroll.adaCheckBox}">
                                                            <display:column><s:checkbox theme="simple" name="payroll.checkedValue" fieldValue="%{#attr.row.nip}" cssClass="cekUserPensiun"/></display:column>
                                                            </s:if>
                                                            <display:column property="nip" sortable="true" title="NIP"  />
                                                            <display:column property="nama" sortable="true" title="Nama" />
                                                            <display:column property="departmentName" sortable="true" title="Bidang" />
                                                            <display:column property="positionName" sortable="true" title="Jabatan" />
                                                            <display:column style="text-align:center;" property="golonganName" sortable="true" title="Level" />
                                                            <display:column property="tipePegawaiName" sortable="true" title="Tipe Pegawai" />
                                                            <display:column style="text-align:right;" property="gajiKotor" sortable="true" title="Gaji Kotor" />
                                                            <display:column style="text-align:right;" property="totalB" sortable="true" title="Potongan" />
                                                            <display:column style="text-align:right;" property="pphGaji" sortable="true" title="PPh" />
                                                            <display:column style="text-align:right;" property="totalGajiBersih" sortable="true" title="Gaji Bersih" />
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="350" width="600" autoOpen="false" title="Saving ...">
                                            Please don't close this window, server is processing your request ...
                                            </br>
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
<script>
    $(document).ready(function(){
        $(".cekUserPensiun").change(function() {
            if(this.checked) {
                PayrollAction.addUserCekToSession(this.value, function(){
                });
            }else{
                PayrollAction.deleteUserCekToSession(this.value, function(){
                });
            }
        });
    })

</script>
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>