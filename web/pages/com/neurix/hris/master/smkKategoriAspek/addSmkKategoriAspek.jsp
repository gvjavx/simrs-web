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

        function link(){
            window.location.href="<s:url action='initForm_smkKategoriAspek'/>";
        }

        $.subscribe('beforeProcessSave1', function (event, data) {
            var nameSmkKategori    = document.getElementById("smkKategoriAspekName").value;
            var branch          = document.getElementById("branchId").value;
            var tipeAspek               = document.getElementById("tipeAspekId").value;

            if (nameSmkKategori != '' && tipeAspek != '' && branch != '') {
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

                if (nameSmkKategori == '') {
                    msg += 'Field <strong> SMK Kategori Name</strong> is required.' + '<br/>';
                }

                if (branch == '') {
                    msg += 'Field <strong>Branch</strong> is required.' + '<br/>';
                }

                if (tipeAspek == '') {
                    msg += 'Field <strong>Tipe Aspek</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
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
            <s:if test="isAddOrEdit()">
                <s:if test="isAdd()">
                    Add SMK Kategori Aspek
                </s:if>
                <s:else>
                    Edit SMK Kategori Aspek
                </s:else>
            </s:if>
            <s:elseif test="isDelete()">
                Delete SMK Kategori Aspek
            </s:elseif>
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <s:if test="isAddOrEdit() || isDelete()">
                    <s:url id="urlProcess" namespace="/smkKategoriAspek" action="save_smkKategoriAspek" includeContext="false"/>
                </s:if>
                <s:else>
                    <s:url id="urlProcess" namespace="/smkKategoriAspek" action="search_smkKategoriAspek" includeContext="false"/>
                </s:else>
                <td align="center">
                    <%--<s:form id="smkKategoriAspekBForm" method="post"  theme="simple" namespace="/smkKategoriAspek" action="save_smkKategoriAspek.action" cssClass="well form-horizontal">--%>
                    <s:form id="smkKategoriAspekBForm" method="post"  theme="simple" action="%{urlProcess}" cssClass="well form-horizontal">

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

                        <table >
                            <s:if test="isAddOrEdit()">
                                <s:if test="isAdd()">
                                </s:if>
                                <s:else>
                                    <tr>
                                        <td>
                                            <label class="control-label"><small>Kategori ID :</small></label>
                                        </td>
                                        <td>
                                            <table>
                                                <s:textfield  id="smkKategoriAspekId1" name="smkKategoriAspek.kategoriAspekId" required="false" readonly="true" cssClass="form-control"/>
                                            </table>
                                        </td>
                                    </tr>
                                </s:else>
                            </s:if>
                            <s:elseif test="isDelete()">
                                <tr>
                                    <td>
                                        <label class="control-label"><small>Kategori ID :</small></label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield  id="smkKategoriAspekId1" name="smkKategoriAspek.kategoriAspekId" required="false" readonly="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                            </s:elseif>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Tipe Aspek :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboTipeAspek" namespace="/tipeAspek" name="initComboTipeAspek_tipeAspek"/>
                                        <s:if test="isAddOrEdit()">
                                            <s:select list="#initComboTipeAspek.tipeAspekList" id="tipeAspekId"
                                                      name="smkKategoriAspek.tipeAspekId"
                                                      listKey="tipeAspekId" listValue="tipeAspekName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <s:select list="#initComboTipeAspek.tipeAspekList" id="tipeAspekId"
                                                      name="smkKategoriAspek.tipeAspekId" disabled="true"
                                                      listKey="tipeAspekId" listValue="tipeAspekName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </s:elseif>
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
                                        <s:if test="isAddOrEdit()">
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId"
                                                      name="smkKategoriAspek.branchId"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId"
                                                      name="smkKategoriAspek.branchId" disabled="true"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </s:elseif>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Kategori Name :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:if test="isAddOrEdit()">
                                            <s:textfield id="smkKategoriAspekName" name="smkKategoriAspek.kategoriName" readonly="false" cssClass="form-control"/>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <s:textfield id="smkKategoriAspekName" name="smkKategoriAspek.kategoriName" readonly="true" cssClass="form-control"/>
                                        </s:elseif>
                                    </table>
                                </td>
                            </tr>

                            <%--<tr>
                                <td>
                                    <label class="control-label"><small>Bobot :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:if test="isAddOrEdit()">
                                            <s:textfield  id="smkKategoriAspekBobot" name="smkKategoriAspek.bobot" readonly="false" cssClass="form-control"/>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <s:textfield  id="smkKategoriAspekBobot" name="smkKategoriAspek.bobot" readonly="true" cssClass="form-control"/>
                                        </s:elseif>
                                    </table>
                                </td>
                            </tr>--%>



                        </table>



                        <br>

                        <div id="actions" class="form-actions" style="display: none">
                            <table align="center">
                                <tr>
                                    <td>
                                        <s:if test="isAddOrEdit()">
                                            <s:url var="urlAdd" namespace="/smkKategoriAspek" action="modalAdd_smkKategoriAspek" escapeAmp="false"></s:url>
                                            <sj:a cssClass="btn btn-info" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                <i class="fa fa-plus"></i>
                                                Add Indikator
                                            </sj:a>
                                        </s:if>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="20%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="500" width="900" autoOpen="false"
                                                   title="Smk Kategori ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfsmkKategoriAspekB" value="#session.kategoriAspekIndikator" scope="request" />
                                        <display:table name="listOfsmkKategoriAspekB" class="table table-condensed table-striped table-hover"
                                                       id="row" pagesize="14" style="font-size:10">
                                            <s:if test="isAddOrEdit()">
                                                <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDelete" namespace="/smkKategoriAspek" action="deleteIndikator_smkKategoriAspek" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.indikatorPenilaianAspekId" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>

                                                </display:column>
                                            </s:if>

                                            <display:column property="indikatorPenilaianAspekId" sortable="true" title="Indikator Id" />
                                            <display:column property="indikatorName" sortable="true" title="Indikator Name" />
                                            <display:column property="bobot" sortable="true" title="Bobot"  />
                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>

                        <br>
                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <s:if test="isAddOrEdit()">
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-success" formIds="smkKategoriAspekBForm" id="save1" name="save1"
                                                       onBeforeTopics="beforeProcessSave1" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                <i class="fa fa-save"></i>
                                                Save Kategori
                                            </sj:submit>
                                            <button type="button" class="btn btn-danger"
                                                    onclick="window.location.href='<s:url action="cancelIndikator_smkKategoriAspek"/>'">
                                                <i class="fa fa-refresh"></i> Cancel
                                            </button>
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-success" formIds="smkKategoriAspekBForm" id="save1" name="save1"
                                                       onBeforeTopics="beforeProcessSave1" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                <i class="fa fa-trash"></i>
                                                Delete Kategori
                                            </sj:submit>
                                            <button type="button" class="btn btn-danger"
                                                    onclick="window.location.href='<s:url action="cancelIndikator_smkKategoriAspek"/>'">
                                                <i class="fa fa-refresh"></i> Cancel
                                            </button>
                                        </s:elseif>
                                    </td>
                                </tr>
                            </table>
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

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

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

