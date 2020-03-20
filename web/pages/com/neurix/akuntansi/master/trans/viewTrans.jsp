<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/trans" action="saveDelete_trans" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">View Trans</legend>
                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

                <table >
                    <tr>
                        <td>
                            <label class="control-label"><small>Trans Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="transIdView" name="trans.transId" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Trans :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="transNameView" name="trans.transName" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Sumber Baru ?</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'N':'Tidak'}" id="flagSumberBaruView" name="trans.flagSumberBaru"
                                          headerKey="Y" headerValue="Iya" cssClass="form-control" disabled="true" />
                                <s:hidden name="trans.flagSumberBaru" />
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" id="cancel" class="btn btn-danger" onclick="cancelBtn();">
                            <i class="fa fa-refresh"/> Close
                        </button>
                    </div>
                </div>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>
