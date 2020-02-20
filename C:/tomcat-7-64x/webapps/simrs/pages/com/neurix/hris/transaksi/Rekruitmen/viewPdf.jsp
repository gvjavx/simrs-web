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

<s:form id="viewPdfForm" method="post" theme="simple" namespace="/rekruitmen" action="viewDoc_rekruitmen" cssClass="well form-horizontal">

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
            <object data="<s:property value="RekruitmenUploadDoc.filePath"/>" type="application/pdf" width="100%" height="100%">
                    <%--<p>Alternative text - include a link <a href="myfile.pdf">to the PDF!</a></p>--%>
            </object>
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
