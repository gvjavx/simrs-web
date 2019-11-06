<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
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

            if (document.getElementById("branchName").value != '') {

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
                if (document.getElementById("branchName").value == '') {
                    msg = 'Field <strong>Branch Name</strong> is required.' + '<br/>';
                }

                if (document.getElementById("branchAddress").value == '') {
                    msg = msg + 'Field <strong>Branch Address</strong> is required.' + '<br/>';
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
            document.getElementById("branchId").value = '';
            document.getElementById("branchName").value = '';
            document.getElementById("branchAddress").value = '';
        }


    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >
<%@ include file="/pages/common/headerNav.jsp" %>


<ivelincloud:mainMenu/>

<div class="content-wrapper">

    <section class="content-header">
        <h1>
            Branch Information
            <small>e-HEALTH</small>
        </h1>
    </section>

    <div class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">

                    </div>

                    <s:if test="isAddOrEdit() || isDelete()">
                        <s:url id="urlProcess" namespace="/admin/branch" action="save_branch" includeContext="false"/>
                    </s:if>
                    <s:else>
                        <s:url id="urlProcess" namespace="/admin/branch" action="search_branch" includeContext="false"/>
                    </s:else>

                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="branchForm" method="post" action="%{urlProcess}" theme="simple"
                                    cssClass="well form-horizontal">
                                <s:hidden name="addOrEdit"/>
                                <s:hidden id="add" name="add"/>
                                <s:hidden name="delete"/>

                                <table>
                                    <tr>
                                        <td width="10%" align="center">
                                            <%@ include file="/pages/common/message.jsp" %>
                                        </td>
                                    </tr>
                                </table>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="branch.branchId">Branch Id :</label>
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <div class="col-sm-3">
                                                <s:textfield id="branchId" name="branch.branchId" required="true" cssClass="form-control"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <div class="col-sm-3">
                                                <s:textfield readonly="true" id="branchId" name="branch.branchId" required="true" cssClass="form-control"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <div class="col-sm-3">
                                            <s:textfield id="branchId" name="branch.branchId" required="true" cssClass="form-control" readonly="true"/>
                                        </div>
                                    </s:elseif>
                                    <s:else>
                                        <div class="col-sm-3">
                                            <s:textfield id="branchId" name="branch.branchId" required="true" cssClass="form-control" readonly="false"/>
                                        </div>
                                    </s:else>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="branch.branchName">Branch Name :</label>
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <div class="col-sm-3">
                                                <s:textfield id="branchName" name="branch.branchName" required="true" cssClass="form-control"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <div class="col-sm-3">
                                                <s:textfield id="branchName" name="branch.branchName" required="true" cssClass="form-control"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <div class="col-sm-3">
                                            <s:textfield id="branchName" name="branch.branchName" readonly="true" required="true" cssClass="form-control" />
                                        </div>
                                    </s:elseif>
                                    <s:else>
                                        <div class="col-sm-3">
                                            <s:textfield id="branchName" name="branch.branchName" required="true" cssClass="form-control"/>
                                        </div>
                                    </s:else>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="branch.branchAddress">Branch Address :</label>
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <div class="col-sm-3">
                                                <s:textfield id="branchAddress" name="branch.branchAddress" cssClass="form-control" required="true"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <div class="col-sm-3">
                                                <s:textfield id="branchAddress" cssClass="form-control" name="branch.branchAddress" required="true"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <div class="col-sm-3">
                                            <s:textfield id="branchAddress" readonly="true" cssClass="form-control" name="branch.branchAddress" required="true"/>
                                        </div>
                                    </s:elseif>
                                    <s:else>
                                        <div class="col-sm-3">
                                            <s:textfield id="branchAddress" name="branch.branchAddress" cssClass="form-control" required="true"  readonly="false"/>
                                        </div>
                                    </s:else>
                                </div>

                                <div class="form-group">
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Status Pabrik:</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="statusPabrik" name="branch.statusPabrik" cssClass="form-control" required="true"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Status Pabrik:</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="statusPabrik" cssClass="form-control" name="branch.statusPabrik" required="true"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <label class="control-label col-sm-5" for="branch.branchAddress">Status Pabrik:</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="statusPabrik" readonly="true" cssClass="form-control" name="branch.statusPabrik" required="true"/>
                                        </div>
                                    </s:elseif>
                                </div>

                                <div class="form-group">
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Multifikator Payroll:</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="multifikator" name="branch.multifikator" cssClass="form-control" required="true"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Multifikator Payroll:</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="multifikator" cssClass="form-control" name="branch.multifikator" required="true"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <label class="control-label col-sm-5" for="branch.branchAddress">Multifikator Payroll:</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="multifikator" readonly="true" cssClass="form-control" name="branch.multifikator" required="true"/>
                                        </div>
                                    </s:elseif>
                                </div>

                                <div class="form-group">
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Max Ijin Keluar :</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="maxJamIjinKeluar" name="branch.maxJamIjinKeluar" cssClass="form-control" required="true"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Max Ijin Keluar :</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="maxJamIjinKeluar" cssClass="form-control" name="branch.maxJamIjinKeluar" required="true"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <label class="control-label col-sm-5" for="branch.branchAddress">Max Ijin Keluar :</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="maxJamIjinKeluar" readonly="true" cssClass="form-control" name="branch.maxJamIjinKeluar" required="true"/>
                                        </div>
                                    </s:elseif>
                                </div>

                                <div class="form-group">
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Jasprod :</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="jasprod" name="branch.faktorJasprod" cssClass="form-control" required="true"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Jasprod :</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="jasprod" cssClass="form-control" name="branch.faktorJasprod" required="true"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <label class="control-label col-sm-5" for="branch.branchAddress">Jasprod :</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="jasprod" readonly="true" cssClass="form-control" name="branch.faktorJasprod" required="true"/>
                                        </div>
                                    </s:elseif>
                                </div>

                                <div class="form-group">
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Jubileum :</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="jubileum" name="branch.faktorJubileum" cssClass="form-control" required="true"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Jubileum :</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="jubileum" cssClass="form-control" name="branch.faktorJubileum" required="true"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <label class="control-label col-sm-5" for="branch.branchAddress">Jubileum :</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="jubileum" readonly="true" cssClass="form-control" name="branch.faktorJubileum" required="true"/>
                                        </div>
                                    </s:elseif>
                                </div>

                                <div class="form-group">
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Umr :</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="umr" name="branch.umr" cssClass="form-control" required="true"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <label class="control-label col-sm-5" for="branch.branchAddress">Umr :</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="umr" cssClass="form-control" name="branch.umr" required="true"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <label class="control-label col-sm-5" for="branch.branchAddress">Umr :</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="umr" readonly="true" cssClass="form-control" name="branch.umr" required="true"/>
                                        </div>
                                    </s:elseif>
                                </div>

                                <div class="form-group">
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <label class="control-label col-sm-5" >Biaya Jasprod :</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="biayaJasprod" name="branch.biayaJasprod" cssClass="form-control" required="true"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <label class="control-label col-sm-5" >Biaya Jasprod :</label>
                                            <div class="col-sm-3">
                                                <s:textfield id="biayaJasprod" cssClass="form-control" name="branch.biayaJasprod" required="true"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <label class="control-label col-sm-5">Biaya Jasprod :</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="biayaJasprod" readonly="true" cssClass="form-control" name="branch.biayaJasprod" required="true"/>
                                        </div>
                                    </s:elseif>
                                </div>

                                <s:if test="!(isAddOrEdit() || isDelete())">
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="branch.flag">Flag :</label>
                                        <div class="col-sm-3">
                                            <s:select list="#{'Y':'Active', 'N':'NonActive'}" id="flag" name="branch.flag"
                                                      headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </div>
                                    </div>
                                </s:if>

                                <div id="crud"></div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" style="visibility: hidden"></label>

                                    <div style="padding-left: 140px" class="col-sm-4">
                                        <s:if test="isAddOrEdit()">
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="branchForm" id="save" name="save"
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
                                                <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>"
                                                     name="image_indicator_write">
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

                                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_branch"/>'">
                                                <i class="fa fa-refresh"></i> Reset
                                            </button>
                                        </s:if>

                                        <s:elseif test="isDelete()">
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="branchForm"
                                                       id="delete" name="delete"
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

                                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_branch"/>'">
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
                                            <sj:submit type="button" cssClass="btn btn-primary" formIds="branchForm" id="search"
                                                       name="search"
                                                       onClickTopics="showDialog" onCompleteTopics="closeDialog">
                                                <i class="fa fa-search"></i>
                                                Search
                                            </sj:submit>

                                            <div class="btn-group">
                                                <s:url id="urlAdd" namespace="/admin/branch" action="add_branch" escapeAmp="false"/>
                                                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                    <i class="fa fa-edit"></i>
                                                    Branch
                                                    <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><s:a href="%{urlAdd}"><i class="fa fa-plus"></i> Add</s:a></li>
                                                </ul>
                                            </div>

                                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_branch"/>'">
                                                <i class="fa fa-refresh"></i> Reset
                                            </button>
                                        </s:else>
                                    </div>

                                </div>
                                <br>
                                <s:if test="!isAddOrEdit() && !isDelete()">
                                    <table align="center">
                                        <tr>
                                    <td align="center">

                                        <s:set name="listOfBranches" value="#session.listOfResult" scope="request"/>
                                        <display:table name="listOfBranches" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag.action" id="row" export="true" pagesize="20" style="font-size:12">

                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlView" namespace="/admin/branch" action="edit_branch" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.branchId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{urlView}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </s:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlView" namespace="/admin/branch" action="delete_branch" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.branchId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{urlView}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </s:a>
                                                </s:if>
                                            </display:column>

                                            <display:column property="branchId" sortable="true" title="Id"/>
                                            <display:column property="branchName" sortable="true" title="Branch.Name"/>
                                            <display:column property="branchAddress" sortable="true" title="Address"/>
                                            <display:column property="statusPabrik" sortable="true" title="Status Pabrik"/>
                                            <display:column property="multifikator" sortable="true" title="Multifikator Payroll"/>
                                            <display:column property="maxJamIjinKeluar" sortable="true" title="Max jam ijin keluar"/>
                                            <display:column property="faktorJasprod" sortable="true" title="Jasprod"/>
                                            <display:column property="faktorJubileum" sortable="true" title="Jubileum"/>
                                            <display:column property="umr" sortable="true" title="Umr"/>
                                            <display:column property="strBiayaJasprod" sortable="true" title="Biaya Jasprod"/>
                                            <%--<display:column property="createdDate" sortable="true" title="CreatedDate"
                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                            <display:column property="createdWho" sortable="true" title="CreatedWho"/>
                                            <display:column property="lastUpdate" sortable="true" title="Updated"
                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                            <display:column property="lastUpdateWho" sortable="true" title="UpdatedWho"/>
                                            <display:column property="action" sortable="true" title="Action"/>
                                            <display:column property="flag" sortable="true" title="Flag"/>--%>


                                            <display:setProperty name="paging.banner.item_name">Branch</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">Branches</display:setProperty>
                                            <display:setProperty name="export.excel.filename">Branches.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">Branches.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">Branches.pdf</display:setProperty>
                                        </display:table>
                                    </td>
                                </tr>
                                    </table>
                                </s:if>
                            </s:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>


</body>
</html>

<script>
    $(document).ready(function() {
        $('#errorTimestampTo').datepicker({
            format: 'dd-mm-yyyy'
        });
        $('#errorTimestampFrom').datepicker({
            format: 'dd-mm-yyyy'
        });
    });
</script>


