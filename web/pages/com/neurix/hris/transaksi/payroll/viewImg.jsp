<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        function closeBtnAddLahan() {
            $('#view_dialog_view').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<s:form id="addBiayaForm" method="post" theme="simple" namespace="/medicalrecord" action="addBiaya_medicalrecord" cssClass="well form-horizontal">

    <s:hidden name="addOrEdit"/>
    <s:hidden name="delete"/>



    <%--<legend align="left">View Doc</legend>--%>


    <table>
        <tr>
            <td width="10%" align="center">
                <%@ include file="/pages/common/message.jsp" %>
            </td>
        </tr>
    </table>
    <div class="form-group">
       <div class="col-md-12 col-sm-12">
           <img src="<s:property value='sppdDoc.path'/>" width="100%">
       </div>
    </div>



    <div id="actions" class="form-actions">
        <table>
            <tr>
                <td></td>
                <td>
                    <div id="crud">
                        <table>
                            <tr>
                                <td>
                                    <label class="control-label"></label>
                                </td>
                            </tr>

                            <tr>
                                <%--<td align="center">--%>
                                    <%----%>

                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--<button type="button" id="cancelbtn" class="btn" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="closeBtnAddLahan();">--%>
                                        <%--<i class="icon-remove-circle"/> Cancel--%>
                                    <%--</button>--%>

                                <%--</td>--%>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</s:form>

</body>
</html>

