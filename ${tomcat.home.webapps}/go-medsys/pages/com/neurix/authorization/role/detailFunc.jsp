<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">
    <script type='text/javascript' src='<s:url value="/dwr/interface/RoleFuncAction.js"/>'></script>
    <script type="text/javascript">

        function saveBtn() {

            var arrCheckedFuncId = new Array();
            var listOfFunc=document.getElementsByName('funcIdChecked');
            var j=0;
            for (var i = 0; i < listOfFunc.length; i++) {
                if (document.getElementsByName('funcIdChecked')[i].checked) {
                    arrCheckedFuncId[j]=document.getElementsByName('funcIdChecked')[i].value;
                    j++;
                }
            }

            var addFlag = document.getElementById('addFlag').value;
            var editFlag = document.getElementById('editFlag').value;
            var roleId = document.getElementById('roleId').value;
            if (roleId == '') { //handle anomali when get from popup edit (mis get)
                roleId = '<s:property value="roleId"/>';
            }

            var menuId = document.getElementById('menuId').value;

//            dwr.engine.setAsync(false);
            RoleFuncAction.saveListFunction(arrCheckedFuncId,addFlag,editFlag,roleId,menuId, function (message) {
                if (message=='00') {
                    $('#view_dialog_function').dialog('close');
                } else {
                    document.getElementById('errorMessageFunc').innerHTML = 'Error when saving detail funtion to session. Please inform this to your admin.';
                    $.publish('showErrorDialogFunc');
                }
            });

        };

        function cancelFuncBtn() {
            $('#view_dialog_function').dialog('close');
        };

        function setReadOnlyCheckbox(){
            var listOfMenu=document.getElementsByName('funcIdChecked');
            for (var i = 0; i < listOfMenu.length; i++) {
                document.getElementsByName('funcIdChecked')[i].disabled=true;
            }
        }

    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <div>

                <s:form id="selectedFunctionForm" method="post" namespace="/admin/rolefunction" action="initForm_rolefunc" cssClass="well form-horizontal">

                    <s:hidden id="addFlag" name="add"/>
                    <s:hidden id="editFlag" name="addOrEdit"/>
                    <s:hidden id="menuId" name="id"/>
                    <s:hidden id="roleId" name="roleId"/>

                    <table>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr><td></td></tr>
                                    <tr><td></td></tr>
                                    <tr>
                                        <td align="center">
                                            <s:if test="isAddOrEdit() || isAdd()">
                                                <s:set name="listOfFunction" value="menu.listOfFunctions"/>
                                                <display:table name="listOfFunction" class="table table-condensed table-striped table-hover" requestURI="" id="row" style="font-size:11">
                                                    <display:column property="displayObjectCheck.checkBox" title="Select" media="html" style="text-align:center;"/>
                                                    <display:column property="funcId" title="Id" />
                                                    <display:column property="funcName" title="Function Name"/>
                                                    <display:column property="url" title="URL"/>
                                                    <display:caption style="font-weight:bold;font-size:14px">List Of Function</display:caption>
                                                </display:table>
                                            </s:if>
                                            <s:else>
                                                <s:set name="listOfFunction" value="menu.listOfFunctions"/>
                                                <display:table name="listOfFunction" class="table table-condensed table-striped table-hover" requestURI="" id="row" style="font-size:11">
                                                    <display:column property="displayObjectCheck.checkBox" title="Select" media="html" style="text-align:center;"/>
                                                    <display:column property="funcId" title="Id" />
                                                    <display:column property="funcName" title="Function Name"/>
                                                    <display:column property="url" title="URL"/>
                                                    <display:caption style="font-weight:bold;font-size:14px">List Of Function</display:caption>
                                                </display:table>
                                                <script>setReadOnlyCheckbox();</script>
                                            </s:else>
                                        </td>
                                    </tr>
                                </table>

                            </td>
                        </tr>

                    </table>

                    <div>
                        <table>
                            <tr>
                                <td align="center">
                                    <table>
                                        <s:if test="isAddOrEdit()">
                                            <sj:dialog id="error_dialog_func" openTopics="showErrorDialogFunc" modal="true" resizable="false"
                                                        height="250" width="600" autoOpen="false" title="Error Dialog"
                                                       buttons="{
                                                                        'OK':function() { $('#error_dialog_func').dialog('close'); }
                                                                }"
                                                    >
                                                <div id="errorMsgFunc" class="alert alert-error fade in">
                                                    <label class="control-label" align="left">
                                                        <img id="iconErrorFunc" border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error_func"> System Found : <p id="errorMessageFunc"></p>
                                                    </label>
                                                </div>
                                            </sj:dialog>

                                            <button type="button" id="saveFunc" class="btn btn-primary" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="saveBtn();">
                                                <i class="icon-ok-circle icon-white"></i> Save
                                            </button>
                                        </s:if>
                                    </table>
                                </td>
                                <td>

                                    <button type="button" id="cancelFunc" class="btn" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelFuncBtn();">
                                        <i class="icon-remove-circle"/> Cancel
                                    </button>

                                </td>


                            </tr>
                        </table>
                    </div>

                </s:form>
            </div>
        </td>
    </tr>
</table>

</body>
</html>

