<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <style type="text/css">
        #tgl1,#tgl2{z-index: 2000!important}
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenAction.js"/>'></script>
    <script type="text/javascript">

        function callSearch2() {
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            var statusRekruitmen=$('#statusRekruitmen22').val();
            if (statusRekruitmen!='') {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    event.originalEvent.options.submit = false;
                }
            }
            else {
                event.originalEvent.options.submit = false;

                var msg = "";

                if (statusRekruitmen == '') {
                    msg += 'Field <strong>Status Rekruitmen</strong> is required.' + '<br/>';
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
            $('#view_dialog_menu_ijin_keluar').dialog('close');
        }


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
<s:form id="form1" theme="simple" cssClass="well form-horizontal">
    <s:hidden name="addOrEdit"/>
    <s:hidden name="delete"/>
    <tr>
        <td align="center">
                <legend align="left">Action Rekruitmen</legend>
                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>
                <div style="float: left">
                    <img id="myImage" src="" class="img-thumbnail img-responsive" width="200" height="150">
                </div>
                <table >
                    <tr>
                        <td>
                            <div>
                                <label class="control-label"><small>Calon Pegawai ID :</small></label>
                            </div>
                        </td>
                        <td>
                            <table>
                                <div>
                                    <s:textfield  id="rekruitmenId32" name="rekruitmen.calonPegawaiId" required="true" readonly="true" cssClass="form-control"/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Calon Pegawai :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="nipId" name="rekruitmen.namaCalonPegawai" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="rekruitmen.positionId"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bidang :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="rekruitmen.departmentId" readonly="true"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="rekruitmen.branchId"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <table id="showdata" width="40%">
                            <br>
                            <br>
                            <tr>
                                <s:set name="listOfRekruitmen" value="#session.ListOfResultRekruitmenStatus" scope="request" />
                                <display:table name="listOfRekruitmen" class=" tableRekruitmen table table-condensed table-striped table-hover"
                                               requestURI="paging_displaytag_rekruitmen.action" id="row" pagesize="20" style="font-size:12">
                                    <display:column property="statusRekruitmenName" sortable="true" title="Record Status"  />
                                    <display:column property="tanggalProses" sortable="true" title="Tanggal Proses" />
                                    <display:column property="keterangan" sortable="true" title="Keterangan" />
                                    <display:column media="html" title="Cetak Report" style="text-align:center">
                                        <s:url var="urlCetakKontrak" namespace="/rekruitmen" action="printReportPerStatusRekruitmen_rekruitmen" escapeAmp="false">
                                            <s:param name="id"><s:property value="#attr.row.calonPegawaiId"/></s:param>
                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                            <s:param name="statusId"><s:property value="#attr.row.statusRekruitmen"/></s:param>
                                        </s:url>
                                        <s:a href="%{urlCetakKontrak}">
                                            <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                        </s:a>
                                    </display:column>
                                </display:table>
                            </tr>
                        </table>
                    </tr>
                    </s:form>
                    <tr>
                        <s:form id="FormActionRekruitmenStatus" method="post" theme="simple" namespace="/rekruitmen" action="saveAction_rekruitmen" cssClass="well form-horizontal">
                            <s:hidden name="addOrEdit"/>
                            <s:hidden name="delete"/>
                    <tr>
                        <td>
                            <label class="control-label"><small>Status Rekruitmen :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="rekruitmenStatusId" name="rekruitmenStatus.calonPegawaiId" required="true" readonly="true" cssStyle="display: none" cssClass="form-control"/>
                                <s:action id="initComboStatus" namespace="/rekruitmen" name="searchStatusRekruitmen_rekruitmen"/>
                                <s:select list="#initComboStatus.listComboStatusRekruitmen" id="statusRekruitmen22" name="rekruitmenStatus.statusRekruitmen" listKey="statusRekruitmentId" listValue="statusRekruitmentName"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="3"  id="keterangan" name="rekruitmenStatus.keterangan" required="false" readonly="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>
            <br>
                <table align="center">
                    <tr>
                        <td>
                            <sj:submit targets="crsud" type="button" cssClass="btn btn-primary" formIds="FormActionRekruitmenStatus" id="save" name="save"
                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                <i class="fa fa-check"></i>
                                Save
                            </sj:submit>
                        </td><td>&nbsp;&nbsp;&nbsp;</td>
                        <td>
                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="search_rekruitmen.action"/>'">
                                <i class="fa fa-refresh"></i> Cancel
                            </button>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
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
                                                                      callSearch2();
                                                                     link();
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
                    </tr>
                </table>
            </s:form>
    </tr>
        </td>
    </tr>
</table>
</body>
</html>

<script>
    $(document).ready(function() {
        $('#rekruitmenStatusId').val(document.getElementById('rekruitmenId32').value);
        var id = $('#rekruitmenId32').val();
        dwr.engine.setAsync(false);
        RekruitmenAction.searchProfilPhoto(id, function (listdata) {
            $("#myImage").attr("src",listdata);
        });
    });
</script>

