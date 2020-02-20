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
            var note = document.getElementById("noteAppr").value;

            if (note != '' ) {
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

                if (note == '') {
                    msg += 'Field <strong>Note</strong> is required.' + '<br/>';
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

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/sppd" action="saveEditSdm_sppd" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Approve SDM SPPD</legend>


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
                            <label class="control-label"><small>Name :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="approveNip" name="sppd.personName" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Branch :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId1" name="sppd.branchId" disabled="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId1" name="sppd.divisiId" disabled="true"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Position :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select list="#comboPosition.listOfComboPosition" id="positionId1" name="sppd.positionId" disabled="true"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keperluan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4" id="approveName" name="sppd.tugasSppd" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Berangkat :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="approveNip" name="sppd.tanggalSppdBerangkat" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal kembali :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="approveNip" name="sppd.tanggalSppdKembali" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Berangkat Dari :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="approveNip" name="sppd.berangkatDari" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tujuan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="approveNip" name="sppd.tujuanKe" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Berangkat Via :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="approveNip" name="sppd.berangkatVia" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Pulang Via :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="approveNip" name="sppd.pulangVia" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small><b>Note For Not Approve :</b></small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4"  id="noteAppr" name="sppd.notApprovalSdmNote" required="true"  cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>

                        </td>
                        <td>
                            <table>
                                <s:textfield  id="approveNip" name="sppd.sppdId" required="true" readonly="true" cssStyle="visibility: hidden" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <s:set name="listOfSppd" value="#session.listSppdPersonAnggota" scope="request" />
                        <display:table name="listOfSppd" class="table table-condensed table-striped table-hover"
                                       requestURI="paging_displaytag_sppd.action" id="row" pagesize="14" style="font-size:10">

                            <display:column property="personId" sortable="true" title="NIP" />
                            <display:column property="personName" sortable="true" title="Name" />
                            <display:column property="tipePerson" sortable="true" title="Tipe Person" />
                            <display:column property="branchId" sortable="true" title="Branch" />
                            <display:column property="divisiId" sortable="true" title="Divisi" />
                            <display:column property="positionName" sortable="true" title="Position" />

                        </display:table>
                    </tr>



                </table>

                <center>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">

                                <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                            <button type="button" id="btnApprove" class="btn btn-primary">
                                <i class="fa fa-check"></i>
                                Approve
                            </button>
                            <sj:submit targets="crud" type="button" cssClass="btn btn-danger" formIds="modifyRolefuncForm" id="save" name="save"
                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                <i class="fa fa-close"/> Not Approve
                            </sj:submit>
                        </div>
                    </div>
                </center>


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
                                                <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
                                            </center>
                                        </sj:dialog>

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
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
        </td>
    </tr>
</table>

</body>
</html>

<script>
    $('#btnApprove').click(function(){
        alert('ulil');
    });
</script>
