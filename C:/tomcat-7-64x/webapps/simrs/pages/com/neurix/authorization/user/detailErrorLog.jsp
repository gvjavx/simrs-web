<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">
</head>

<body bgcolor="#FFFFFF">

    <table width="100%" align="center">
        <tr>
            <td align="center">

            <fieldset>

                <legend align="left">View Detail Error Log</legend>

                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

                <table>
                    <tr>
                        <td>
                            <label class="control-label" for="errorLog.errorId">Error Id :</label>
                        </td>

                        <td>
                            <table>
                                <s:textfield id="errorId" name="errorLog.errorId" required="true" disabled="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label" for="errorLog.moduleMethod">Module Method :</label>
                        </td>

                        <td>
                            <table>
                                <s:textfield id="moduleMethod" name="errorLog.moduleMethod" required="true" disabled="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label" for="errorLog.message">Message :</label>
                        </td>

                        <td>
                            <table>
                                <s:textarea cols="8" rows="5" id="message" name="errorLog.message" required="true" disabled="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label" for="errorLog.stErrorTimestamp">Created Date :</label>
                        </td>

                        <td>
                            <table>
                                <s:textfield id="stErrorTimestamp" name="errorLog.stErrorTimestamp" required="true" disabled="true"/>
                            </table>
                        </td>
                    </tr>

                </table>

            </fieldset>


            </td>
        </tr>
    </table>
</body>
</html>

