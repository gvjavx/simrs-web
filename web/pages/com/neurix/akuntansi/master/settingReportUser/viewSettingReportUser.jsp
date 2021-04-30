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
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/settingReportUser" action="saveDelete_settingReportUser" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">View setting Report User</legend>
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
                            <label class="control-label"><small>Report User Id:</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="settingReportUserIdDelete" name="settingReportUser.settingReportUserId" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Report Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboLaporan" namespace="/laporanAkuntansi" name="searchLaporanAkuntansi_laporanAkuntansi"/>
                                <s:select list="#comboLaporan.listComboLaporanAkuntansi" id="reportIdAdd" name="settingReportUser.reportId" disabled="true"
                                          listKey="laporanAkuntansiId" listValue="laporanAkuntansiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                            <s:hidden name="name=settingReportUser.reportId"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>User Id ( NIP ) :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="userIdDelete" name="settingReportUser.userId" cssClass="form-control" readonly="true"/>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
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