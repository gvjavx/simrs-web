<%--
  Created by IntelliJ IDEA.
  User: reza
  Date: 05/12/19
  Time: 10.20
  To change this template use File | Settings | File Templates.
--%>
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
            document.tindakanForm.action = "search_tindakan.action";
            document.tindakanForm.submit();
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var idTindakan = document.getElementById("idTindakan2").value;
            var namaTindakan = document.getElementById("namaTindakan2").value;
            var branchId = document.getElementById("branchId2").value;
            var idKategoriTindakan = document.getElementById("idKategoriTindakan2").value;
            var idKategoriTindakanIna = document.getElementById("idKategoriTindakanIna2").value;
            var tarif = document.getElementById("tarif2").value;
            var tarifBpjs = document.getElementById("tarifbpjs2").value;
            var diskon = document.getElementById("diskon2").value;

            if (idTindakan != '' && namaTindakan != '' && branchId != '' && idKategoriTindakan != '' && idKategoriTindakanIna != ''
                    && tarif != '' && tarifBpjs != '' && diskon != '') {
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
                if (idTindakan == '') {
                    msg += 'Field <strong>ID Tindakan </strong> is required.' + '<br/>';
                }
                if (namaTindakan == '') {
                    msg += 'Field <strong>Nama Tindakan </strong> is required.' + '<br/>';
                }
                if (branchId == '') {
                    msg += 'Field <strong>Unit </strong> is required.' + '<br/>';
                }
                if (idKategoriTindakan == '') {
                    msg += 'Field <strong>Kategori Tindakan </strong> is required.' + '<br/>';
                }
                if (idKategoriTindakanIna == '') {
                    msg += 'Field <strong>Kategori Ina BPJS </strong> is required.' + '<br/>';
                }
                if (tarif == '') {
                    msg += 'Field <strong>Tarif </strong> is required.' + '<br/>';
                }
                if (tarifBpjs == '') {
                    msg += 'Field <strong>Tarif BPJS </strong> is required.' + '<br/>';
                }
                if (diskon == '') {
                    msg += 'Field <strong>Diskon </strong> is required.' + '<br/>';
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

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="editTindakanForm" method="post" theme="simple" namespace="/tindakan" action="saveEdit_tindakan" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Edit Tindakan</legend>


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
                            <label class="control-label"><small>Tindakan ID :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="idTindakan2" name="tindakan.idTindakan" required="true" readonly="true" disabled="false" cssClass="form-control"/>
                                <%--<s:hidden id="idTindakan2" name="tindakan.idTindakan" />--%>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Tindakan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="namaTindakan2" name="tindakan.tindakan" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                    <%--<s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>--%>
                                    <%--<s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pendapatanDokter.branchId"--%>
                                    <%--listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                <s:if test='tindakan.branchUser == "KP"'>
                                    <s:action id="initComboBranch" namespace="/tindakan" name="initComboBranch_tindakan"/>
                                    <s:select list="#initComboBranch.listOfComboBranches" id="branchId2" name="tindakan.branchId"
                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                </s:if>
                                <s:else>
                                    <s:action id="initComboBranch" namespace="/tindakan" name="initComboBranch_tindakan"/>
                                    <s:select list="#initComboBranch.listOfComboBranches" id="branchId2" name="tindakan.branchId" disabled="true"
                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    <s:hidden id="branchId2" name="tindakan.branchId" />
                                </s:else>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Kategori Tindakan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboKategori" namespace="/tindakan" name="initComboKategori_tindakan"/>
                                <s:select list="#initComboKategori.listOfComboKategoriTindakan" id="idKategoriTindakan2" name="tindakan.idKategoriTindakan"
                                          listKey="idKategoriTindakan" listValue="kategoriTindakan" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Kategori Ina :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboKategoriIna" namespace="/tindakan" name="initComboKategoriIna_tindakan"/>
                                <s:select list="#initComboKategoriIna.listOfComboKategoriTindakanIna" id="idKategoriTindakanIna2" name="tindakan.idKategoriTindakanIna"
                                          listKey="id" listValue="nama" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tarif :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tarif2" name="tindakan.tarif" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tarif BPJS :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tarifbpjs2" name="tindakan.tarifBpjs" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Diskon :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="diskon2" name="tindakan.diskon" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editTindakanForm" id="save" name="save"
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
                                                                        'OK':function() { $('#error_dialog').dialog('close');  window.location.reload(true)}
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


