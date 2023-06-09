<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/PayrollSkalaGajiAction.js"/>'></script>--%>
    <script type="text/javascript">
        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
//            window.location.reload(true);
            document.payrollSkalaGajiBodForm.action = "search_payrollSkalaGajiBod.action";
            document.payrollSkalaGajiBodForm.submit();
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var posisi = document.getElementById("positionId2").value;
            var gaji = document.getElementById("gaji2").value;
            var rumah = document.getElementById("rumah2").value;
            var telekomunikasi = document.getElementById("telekomunisasi2").value;
            var penghasilan = document.getElementById("penghasilan2").value;
            var tahun = document.getElementById("tahun2").value;

            if (posisi != ''&& gaji != '' && rumah != '' && telekomunikasi != '' && penghasilan != '' && tahun != '') {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if (posisi == '') {
                    msg += 'Field <strong>Posisi </strong> is required.' + '<br/>';
                }
                if (gaji == '') {
                    msg += 'Field <strong>Gaji </strong> is required.' + '<br/>';
                }
                if (rumah == '') {
                    msg += 'Field <strong>Tunj.Rumah </strong> is required.' + '<br/>';
                }
                if (telekomunikasi == '') {
                    msg += 'Field <strong>Tunj.Telekomunikasi </strong> is required.' + '<br/>';
                }
                if (penghasilan == '') {
                    msg += 'Field <strong>Jumlah Penghasilan </strong> is required.' + '<br/>';
                }
                if (tahun == '') {
                    msg += 'Field <strong>Tahun </strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage1').innerHTML = msg;

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

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="editPayrollSkalaBodForm" method="post" theme="simple" namespace="/payrollSkalaGajiBod" action="saveEdit_payrollSkalaGajiBod" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Edit Payroll Skala Gaji BOD</legend>


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
                            <label class="control-label"><small>Skala BOD Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="payrollSkalaGajiBodId2" name="payrollSkalaGajiBod.payrollSkalaGajiBodId" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Posisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/user" name="initComboPositionBod_user"/>
                                <s:select list="#comboPosition.listOfComboPositions" id="positionId2" name="payrollSkalaGajiBod.positionId"
                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"
                                          cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Gaji :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="gaji2" name="payrollSkalaGajiBod.gajiBod" required="true" cssClass="form-control" onfocusout="setJumlahGaji()"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tunj.Rumah :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="rumah2" name="payrollSkalaGajiBod.tunjRumah" required="true" cssClass="form-control" onfocusout="setJumlahGaji()"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tunj.Telekomunikasi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="telekomunisasi2" name="payrollSkalaGajiBod.tunjTelekomunikasi" required="true" cssClass="form-control" onfocusout="setJumlahGaji()"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Jum.Penghasilan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="penghasilan2" readonly="true" name="payrollSkalaGajiBod.jumlahPengasilanBulan" required="true" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tahun :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahun2"
                                          name="payrollSkalaGajiBod.tahun" required="true" headerKey=""
                                          headerValue="[Select one]"/>
                            </table>
                        </td>
                    </tr>

                </table>

                <script>
                    function setJumlahGaji(){
                        var gaji1 = document.getElementById("gaji2").value;
                        var rumah1 = document.getElementById("rumah2").value;
                        var telekomunikasi1 = document.getElementById("telekomunisasi2").value;

                        var jumlahGaji = parseFloat(gaji1)+parseFloat(rumah1)+parseFloat(telekomunikasi1);
                        console.log(jumlahGaji);
                        $('#penghasilan2').val(jumlahGaji);
                    }
                </script>


                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editPayrollSkalaBodForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-danger" onclick="cancelBtn();">
                            <i class="fa fa-refresh"/> Cancel
                        </button>
                    </div>
                </div>


                <div id="actions" class="form-actions">
                    <table>
                        <tr>
                            <div id="crud">
                                <td>
                                    <table>
                                        <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Save Data ...">
                                            Please don't close this window, server is processing your request ...
                                            <br>
                                            <center>
                                                <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                     src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                     name="image_indicator_write">
                                                <br>
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      callSearch2();
                                                                   }
                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); window.location.reload(true)}
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
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); window.location.reload(true)}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                    <br/>
                                                    <center><div id="errorValidationMessage2"></div></center>
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
</body>
</html>

