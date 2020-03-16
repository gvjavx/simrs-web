<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <style>
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

        $.subscribe('beforeProcessSave', function (event, data) {

            if (document.getElementById("positionName").value != '') {

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
                if (document.getElementById("positionName").value == '') {
                    msg = 'Field <strong>Position Name</strong> is required.' + '<br/>';
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

        function resetField() {
            document.getElementById("positionId").value = '';
            document.getElementById("positionName").value = '';
        }


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
            Posisi
            <small>e-HEALTH</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>

                    <s:if test="isAddOrEdit() || isDelete()">
                        <s:url id="urlProcess" namespace="/admin/position" action="save_position" includeContext="false"/>
                    </s:if>
                    <s:else>
                        <s:url id="urlProcess" namespace="/admin/position" action="search_position" includeContext="false"/>
                    </s:else>


                        <s:hidden name="addOrEdit"/>
                        <s:hidden id="add" name="add"/>
                        <s:hidden name="delete"/>

                        <div class="box-body">
                            <div class="form-group">
                                <s:form id="positionForm" method="post" action="%{urlProcess}" theme="simple"
                                        cssClass="well form-horizontal">

                                    <s:hidden name="addOrEdit"/>
                                    <s:hidden id="add" name="add"/>
                                    <s:hidden name="delete"/>

                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="position.stPositionId">Posisi Id :</label>
                                        <s:if test="isAddOrEdit()">
                                            <div class="col-sm-3">
                                                <s:textfield id="positionId" name="position.stPositionId"
                                                             readonly="true" cssClass="form-control" />
                                            </div>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <div class="col-sm-3">
                                                <s:textfield id="positionId" cssClass="form-control" readonly="true" name="position.stPositionId"/>
                                            </div>
                                        </s:elseif>
                                        <s:else>
                                            <div class="col-sm-3">
                                                <s:textfield id="positionId" name="position.stPositionId" cssClass="form-control" />
                                            </div>
                                        </s:else>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="position.positionName">Nama Posisi :</label>
                                        <s:if test="isAddOrEdit()">
                                            <div class="col-sm-3">
                                                <s:textfield id="positionName" name="position.positionName"
                                                             readonly="false" cssClass="form-control" />
                                            </div>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <div class="col-sm-3">
                                                <s:textfield id="positionName" cssClass="form-control" readonly="true" name="position.positionName"/>
                                            </div>
                                        </s:elseif>
                                        <s:else>
                                            <div class="col-sm-3">
                                                <s:textfield id="positionName" name="position.positionName" cssClass="form-control" />
                                            </div>
                                        </s:else>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="position.positionName">Bidang/Divisi :</label>
                                        <s:if test="isAddOrEdit()">
                                            <div class="col-sm-3">
                                                <s:action id="comboMasaTanam" namespace="/department" name="initDepartment_department"/>
                                                <s:select list="#session.listOfResultDepartment" id="departmentId" name="position.departmentId"
                                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                            </div>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <div class="col-sm-3">
                                                <s:action id="comboMasaTanam" namespace="/department" name="initDepartment_department"/>
                                                <s:select disabled="true" list="#session.listOfResultDepartment" id="departmentId" name="position.departmentId"
                                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                            </div>
                                        </s:elseif>
                                        <s:else>
                                            <div class="col-sm-3">
                                                <s:action id="comboMasaTanam" namespace="/department" name="initDepartment_department"/>
                                                <s:select list="#session.listOfResultDepartment" id="departmentId" name="position.departmentId"
                                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                            </div>
                                        </s:else>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="position.positionName">Bagian :</label>
                                        <s:if test="isAddOrEdit()">
                                            <div class="col-sm-3">
                                                <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                                <s:select list="#comboBagian.comboListOfPositionBagian" id="bagianId" name="position.bagianId"
                                                          listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]"
                                                          cssClass="form-control"/>
                                            </div>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <div class="col-sm-3">
                                                <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                                <s:select disabled="true" list="#comboBagian.comboListOfPositionBagian" id="bagianId" name="position.bagianId"
                                                          listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]"
                                                          cssClass="form-control"/>
                                            </div>
                                        </s:elseif>
                                        <s:else>
                                            <div class="col-sm-3">
                                                <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                                <s:select list="#comboBagian.comboListOfPositionBagian" id="bagianId" name="position.bagianId"
                                                          listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]"
                                                          cssClass="form-control"/>
                                            </div>
                                        </s:else>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="position.positionName">Kelompok Jabatan :</label>
                                        <s:if test="isAddOrEdit()">
                                            <div class="col-sm-3">
                                                <s:action id="comboKelompok" namespace="/kelompokPosition" name="searchKelompok_kelompokPosition"/>
                                                <s:select list="#comboKelompok.comboListOfKelompokPosition" id="kelompokId" name="position.kelompokId"
                                                          listKey="kelompokId" listValue="kelompokName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                            </div>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <div class="col-sm-3">
                                                <s:action id="comboKelompok" namespace="/kelompokPosition" name="searchKelompok_kelompokPosition"/>
                                                <s:select disabled="true" list="#comboKelompok.comboListOfKelompokPosition" id="kelompokId" name="position.kelompokId"
                                                          listKey="kelompokId" listValue="kelompokName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                            </div>
                                        </s:elseif>
                                        <s:else>
                                            <div class="col-sm-3">
                                                <s:action id="comboKelompok" namespace="/kelompokPosition" name="searchKelompok_kelompokPosition"/>
                                                <s:select list="#comboKelompok.comboListOfKelompokPosition" id="kelompokId" name="position.kelompokId"
                                                          listKey="kelompokId" listValue="kelompokName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                            </div>
                                        </s:else>
                                    </div>

                                    <s:if test="!(isAddOrEdit() || isDelete())">
                                        <div class="form-group">
                                            <label class="control-label col-sm-5" for="position.positionName">Flag :</label>
                                            <div class="col-sm-3">
                                                <s:select list="#{'N':'Non-Active'}" id="flag" name="position.flag"
                                                          headerKey="Y" headerValue="Active" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </s:if>

                                    <br>
                                    <div id="actions" class="form-actions">
                                        <div id="cruds">
                                            <div class="form-group">
                                                <label class="control-label col-sm-4" style="visibility: hidden"></label>

                                                <div style="padding-left: 140px" class="col-sm-4">
                                                    <s:if test="isAddOrEdit()">
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="positionForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="fa fa-check"></i>
                                                            Save
                                                        </sj:submit>

                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false" title="Saving ...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <center>
                                                                <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>"
                                                                     name="image_indicator_write">
                                                            </center>
                                                        </sj:dialog>

                                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                   buttons="{
                                                                              'OK':function() {
                                                                                        $('#info_dialog').dialog('close');
                                                                                        if (document.getElementById('add').value == 'true') {
                                                                                            resetField();
                                                                                        }
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
                                                                <label class="control-label" align="left"> <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                                                                                name="icon_error"> System Found : <p id="errorMessage"></p></label>
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

                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_position"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </s:if>

                                                    <s:elseif test="isDelete()">
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="positionForm"
                                                                   id="save" name="save"
                                                                   onBeforeTopics="beforeProcessDelete" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                                            <i class="fa fa-trash"></i>
                                                            Delete
                                                        </sj:submit>


                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false" title="Deleting ...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-trash.gif"/>"
                                                                 name="image_indicator_trash">
                                                        </sj:dialog>

                                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                   buttons="{
                                                                                'OK':function() { $('#info_dialog').dialog('close'); }
                                                                            }"
                                                        >
                                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                            Record has been deleted successfully.
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

                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_position"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </s:elseif>

                                                    <s:else>
                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog1" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false" title="Searching...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <center>
                                                                <img border="0" src="<s:url value="/pages/images/loading11.gif"/>" name="image_indicator_read">
                                                            </center>
                                                        </sj:dialog>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="positionForm" id="search"
                                                                   name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog">
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>

                                                        <div class="btn-group">
                                                            <s:url id="urlAdd" namespace="/admin/position" action="add_position" escapeAmp="false"/>
                                                            <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                                <i class="fa fa-edit"></i>
                                                                Posisi
                                                                <span class="caret"></span>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li><s:a href="%{urlAdd}"><i class="fa fa-plus"></i> Add</s:a></li>
                                                            </ul>
                                                        </div>

                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_position"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </s:else>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <br><br>
                                    <center>
                                        <s:if test="!isAddOrEdit() && !isDelete()">
                                            <table>
                                                <tr>
                                                    <td align="center">

                                                        <s:set name="listOfPosition" value="#session.listOfResult" scope="request"/>
                                                        <display:table name="listOfPosition" class="table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag.action" id="row" export="true" pagesize="20" style="font-size:12">

                                                            <display:column media="html" title="Edit">
                                                                <s:if test="#attr.row.flagYes">
                                                                    <s:url var="urlView" namespace="/admin/position" action="edit_position" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.positionId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlView}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                    </s:a>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="Delete">
                                                                <s:if test="#attr.row.flagYes">
                                                                    <s:url var="urlView" namespace="/admin/position" action="delete_position" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.positionId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlView}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                    </s:a>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column property="positionId" sortable="true" title="Id"/>
                                                            <display:column property="positionName" sortable="true" title="Nama Posisi"/>
                                                            <display:column property="departmentName" sortable="true" title="Bidang/Divisi"/>
                                                            <display:column property="bagianName" sortable="true" title="Bagian"/>
                                                            <display:column property="kelompokName" sortable="true" title="Kelompok Jabatan"/>
                                                            <%--<display:column property="createdDate" sortable="true" title="CreatedDate"
                                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                                            <display:column property="createdWho" sortable="true" title="CreatedWho"/>
                                                            <display:column property="lastUpdate" sortable="true" title="Updated"
                                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                                            <display:column property="lastUpdateWho" sortable="true" title="UpdatedWho"/>
                                                            <display:column property="action" sortable="true" title="Action"/>
                                                            <display:column property="flag" sortable="true" title="Flag"/>--%>
                                                            <display:setProperty name="paging.banner.item_name">Position</display:setProperty>
                                                            <display:setProperty name="paging.banner.items_name">Positions</display:setProperty>
                                                            <display:setProperty name="export.excel.filename">Positions.xls</display:setProperty>
                                                            <display:setProperty name="export.csv.filename">Positions.csv</display:setProperty>
                                                            <display:setProperty name="export.pdf.filename">Positions.pdf</display:setProperty>
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:if>
                                    </center>
                                </s:form>
                            </div>
                        </div>


                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>



