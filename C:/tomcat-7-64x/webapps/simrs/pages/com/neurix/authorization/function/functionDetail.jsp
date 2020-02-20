<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">
</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <div>
                <table >
                    <tr>
                        <td>
                            <label class="control-label"><small>Function Id :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.stFuncId"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Function Name :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.funcName"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>URL :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.url"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Menu :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.stMenu"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Parent :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.stParent"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Function Level :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.stFuncLevel"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Created Date :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.stCreatedDate"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Created Who :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.createdWho"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>LastUpdate :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.stLastUpdate"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>LastUpadate Who :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.lastUpdateWho"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Flag :</small></label>
                        </td>
                        <td>
                            <label class="control-label">
                                <small>
                                    <div class="label">
                                        <s:property value="functions.flag"/>
                                    </div>
                                </small>
                            </label>
                        </td>
                    </tr>

                </table>

            </div>
        </td>
    </tr>
</table>

</body>
</html>

