<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var kelas = $("#kelas").val()

            //alert(namaAlat.value);
            if (kelas.value != '' ) {

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
                if (kelas.value =='') {
                    msg = 'Field <strong>Kelas</strong> is required.' + '<br/>';
                }


                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('beforeProcessDelete', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
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

<s:form id="addForm" method="post" theme="simple" namespace="/rskelas" action="save_rskelas" cssClass="well form-horizontal">

    <s:hidden name="addOrEdit"/>
    <s:hidden name="delete"/>



    <legend align="left">Add RS Kelas</legend>


    <table>
        <tr>
            <td width="10%" align="center">
                <%@ include file="/pages/common/message.jsp" %>
            </td>
        </tr>
    </table>

    <div class="form-group">
        <label class="control-label col-sm-2">Rs :</label>
        <div class="col-sm-8">
            <s:action id="comboRsKelas" namespace="/rskelas" name="initComboRsKerjasama_rskelas"/>
            <s:select list="#comboRsKelas.listOfComboRsKerjasama" id="rsId" name="rsKelas.rsId"
                      listKey="rsId" listValue="rsName" headerKey="" headerValue="[Select one]"
                      cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Kelompok :</label>
        <div class="col-sm-8">
            <s:action id="comboKelompok" namespace="/rskelas" name="initComboKelompok_rskelas"/>
            <s:select list="#comboKelompok.listOfComboKelompokPosition" id="kelompokId" name="rsKelas.kelompokId"
                      listKey="kelompokId" listValue="kelompokName" headerKey="" headerValue="[Select one]"
                      cssClass="form-control"/>
            <%--<s:select cssClass="form-control" list="#{'KL01':'Kelompok 1', 'KL02':'Kelompok 2'}" id="kelompokId" name="rsKelas.kelompokId"--%>
                      <%--headerKey="" headerValue="[Select one]"/>--%>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Golongan :</label>
        <div class="col-sm-8">
            <s:action id="comboGolongan" namespace="/rskelas" name="initComboGolongan_rskelas"/>
            <s:select list="#comboGolongan.listOfComboGolongan" id="golonganId" name="rsKelas.golonganId"
                      listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]"
                      cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Kelas :</label>
        <div class="col-sm-8">
            <s:textfield id="kelas" cssClass="form-control" name="rsKelas.kelas" required="false" disabled="false"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Branch :</label>
        <div class="col-sm-8">
            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
            <s:select list="#comboBranch.listOfComboBranches" id="branchId" name="rsKelas.branchId"
                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]"
                      cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Flag :</label>
        <div class="col-sm-8" align="left">
            <s:select cssClass="form-control" list="#{'Y':'Active', 'N':'NonActive'}" id="flag" name="tipeLibur.flag"
                      headerKey="" headerValue="[Select one]"/>
        </div>
    </div>

    <br>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addForm" id="save" name="save"
                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                <i class="icon-ok-sign icon-white"></i>
                Save
            </sj:submit>
            <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                <i class="icon-remove-circle"/> Cancel
            </button>
        </div>
    </div>


    <div id="actions" class="form-actions">
        <table>
            <tr>
                <div id="crud">
                    <td>
                        <table>
                            <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                       resizable="false"
                                       height="350" width="600" autoOpen="false" title="Saving ...">
                                Please don't close this window, server is processing your request ...
                                </br>
                                </br>
                                </br>
                                <center>
                                    <img border="0" src="<s:url value="/pages/images/loading4.gif"/>" name="image_indicator_write">
                                </center>
                            </sj:dialog>

                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                       buttons="{
                                                              'OK':function() {
                                                                      callSearch();
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
                </div>
            </tr>
        </table>
    </div>
</s:form>

</body>
</html>

