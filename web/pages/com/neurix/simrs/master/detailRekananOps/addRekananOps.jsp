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
            window.location.reload(true);
        };

    $.subscribe('beforeProcessSaveAdd', function (event, data) {
        var bpjs = document.getElementById("bpjs").value;
        var namarekananadd = document.getElementById("namarekananadd").value;
        var branchIdadd = document.getElementById("branchIdadd").value;
        var diskonadd = document.getElementById("diskonadd").value;
     
        if (bpjs != '' && namarekananadd != '' && branchIdadd != '' && diskonadd != '' ) {
            if (confirm('Do you want to save this record?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialogAdd');
            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        } else {
            event.originalEvent.options.submit = false;
            var msg = "";
            if (bpjs == '') {
                msg += 'Field <strong>Bpjs  </strong> is required.' + '<br/>';
            }
            if (namarekananadd == '') {
                msg += 'Field <strong>Nama rekanan Ops   </strong> is required.' + '<br/>';
            }
            if (branchIdadd == '') {
                msg += 'Field <strong>Branch   </strong> is required.' + '<br/>';
            }
            if (diskonadd == '') {
                msg += 'Field <strong>Diskon  </strong> is required.' + '<br/>';
            }

            document.getElementById('errorValidationMessageAdd').innerHTML = msg;

            $.publish('showErrorValidationDialogAdd');
        }
    });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
            }
        );

        $.subscribe('errorDialogAdd', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessageAdd').innerHTML = "Status = "
                + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialogAdd');
        }

        );

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center" >
            <s:form id="addRekananOpsForm" method="post" theme="simple"
                    namespace="/detailrekananops" action="saveAdd_detailrekananops" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Add Detail Rekanan Ops</legend>
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
                            <label class="control-label"><small>Nama Rekanan :</small></label>
                        </td>
                        <td width="70%">
                            <table>
                                <s:action id="initComboRekanan" namespace="/detailrekananops" name="initComboRekanan_detailrekananops"/>
                                <s:select list="#initComboRekanan.listOfComboRekananOps" id="namarekananadd" name="detailRekananOps.idRekananOps" 
                                          listKey="idRekananOps" listValue="namaRekanan" headerKey="" headerValue="[Select one]"
                                          cssClass="form-control" cssStyle="margin-top: 5px" />

                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Unit:</small></label>
                        </td>
                        <td width="70%">
                            <table>
                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                <s:select list="#comboBranch.listOfComboBranches" id="branchIdadd" name="detailRekananOps.branchId"
                                          cssStyle="margin-top: 5px"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]"
                                          cssClass="form-control" disabled="true" />
                                <s:hidden name="detailRekananOps.idRekananOps"></s:hidden>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td width="18%">
                            <label class="control-label"><small> Diskon :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield cssStyle="margin-top: 7px"
                                             id="diskonadd" type="number"
                                             name="detailRekananOps.diskon"
                                             required="false"
                                             readonly="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td width="20%">
                            <label class="control-label"><small>Cover BPJS :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'Y':'Ya'}" id="bpjs" name="detailRekananOps.isBpjs"
                                          headerKey="N" headerValue="Tidak" cssClass="form-control select2"
                                          cssStyle="margin-top: 5px"/>
                            </table>
                        </td>
                    </tr>

                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addRekananOpsForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSaveAdd" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialogAdd" >
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
                                        <sj:dialog id="waiting_dialog" openTopics="showDialogAdd"
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
                                                   height="190" width="400" autoOpen="false" title="Infomation Dialog"
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

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialogAdd" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close');}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessageAdd"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>
                                        <sj:dialog id="error_validation_dialog_add" openTopics="showErrorValidationDialogAdd" modal="true" resizable="false"
                                                   height="280" width="500" autoOpen="false" title="Warning"
                                                   buttons="{
                                                                        'OK':function() { $('#error_validation_dialog_add').dialog('close');}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                    <br/>
                                                    <center><div id="errorValidationMessageAdd"></div></center>
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
<script>
    window.cekEksekutif1 = function () {
        if (document.getElementById("isEksekutifAdd").checked == true) {
            $("#eksekutif").val("Yes");
        } else {
            $("#eksekutif").val("No");
        }
    }
    function showKategoriRekananOps(valueTipe){
        // console.log(valueTipe);
        if(valueTipe=='rawat_jalan'){
            $('#form_kategori').show();
        }else {
            $('#form_kategori').hide();
            $('#kategoriRekananOpsAdd').val('');

        }
    }

</script>

