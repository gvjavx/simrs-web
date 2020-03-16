<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/GroupMemberAction.js"/>'></script>
    <script type="text/javascript">

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            var unit = $("#branchId12").val();
            var group = $("#groupId12").val();
            var shift = $("#shiftId12").val();

            //alert(namaAlat.value);
            if (unit != ''&& group!=''&&shift!='' ) {
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
                if (unit =='') {
                    msg += 'Field <strong>Unit</strong> is required.' + '<br/>';
                }if (group =='') {
                    msg += 'Field <strong>Group</strong> is required.' + '<br/>';
                }if (shift =='') {
                    msg += 'Field <strong>Shift</strong> is required.' + '<br/>';
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

<s:form id="addForm" method="post" theme="simple" namespace="/groupShift" action="save_groupShift" cssClass="well form-horizontal">
    <s:hidden name="addOrEdit"/>
    <s:hidden name="delete"/>
    <legend align="left">Add Group Shift</legend>
    <table>
        <tr>
            <td width="10%" align="center">
                <%@ include file="/pages/common/message.jsp" %>
            </td>
        </tr>
    </table>
    <div class="form-group">
        <table align="center">
            <tr>
                <td>
                    <label class="control-label"><small>Unit :</small></label>
                </td>
                <td>
                    <table>
                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid12" name="groupShift.branchId" required="true"
                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label"><small>Group Shift Name :</small></label>
                </td>
                <td>
                    <table>
                        <s:textfield  id="groupShoftName" name="groupShift.groupShiftName" required="true" cssClass="form-control"/>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label"><small>Shift Id :</small></label>
                </td>
                <td>
                    <table>
                        <s:action id="comboShift" namespace="/shift" name="initComboShift_shift"/>
                        <s:select cssClass="form-control" list="#comboShift.listOfComboShift" id="shiftId12" name="groupShift.shiftId" required="true"
                                  listKey="shiftId" listValue="shiftName" headerKey="" headerValue="[Select one]" />
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label"><small>Group Id :</small></label>
                </td>
                <td>
                    <table>
                        <s:action id="comboGroup" namespace="/group" name="initComboGroup_group"/>
                        <s:select cssClass="form-control" list="#comboGroup.listOfComboGroup" id="groupId12" name="groupShift.groupId" required="true"
                                  listKey="groupId" listValue="groupName" headerKey="" headerValue="[Select one]" />
                    </table>
                </td>
            </tr>
        </table>
        <br>
        <center>
            <table id="showdata2" width="100%">
                <tr>
                    <td align="center">
                        <table style="width: 80%;" class="memberTable table table-bordered" id="memberTable">
                        </table>
                    </td>
                </tr>
            </table>
        </center>
    </div>
    <br>
    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-8">
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
                                    <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
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
<script>
    window.loadMember =  function(){
        $('.memberTable tr').remove();
        var groupId = $('#groupId12').val();
        $('.memberTable').find('tbody').remove().find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        if (groupId!=''){
            GroupMemberAction.searchGroupMember(groupId,function(listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>nama</th>"+
                        "</tr></thead>";
                var i = i ;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td >' + (i + 1) + '</td>' +
                            '<td >' + item.nip + '</td>' +
                            '<td >' + item.nama + '</td>' +
                            "</tr>";
                });
                $('.memberTable').append(tmp_table);
            });
        }
    };

    $(document).ready(function(){
        $('#groupId12').change(function(){
            loadMember();
        })
    })
</script>
