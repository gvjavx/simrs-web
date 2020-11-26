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
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/payrollParamBpjs"
                    action="view_payrollParamBpjs" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">View Payroll Param Bpjs</legend>
                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

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
                            <label class="control-label"><small>ID Param Bpjs:</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="idPtkpDelete" readonly="true"
                                              name="payrollParamBpjs.idPtkp" required="true"
                                              cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Status Keluarga :</small></label>
                        </td>
                        <td>
                            <table>
                                    <%--<s:action id="initComboTipe" namespace="/statusKeluarga" name="initComboStatusKeluarga_statusKeluarga"/>--%>
                                    <%--<s:select list="#initComboTipe.listComboStatusKeluarga" id="statusKeluargaDelete" name="payrollParamBpjs.statusKeluarga" disabled="true"--%>
                                    <%--listKey="statusKeluarga" listValue="statusKeluargaName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                <s:select list="#{'B':'Bujang', 'K':'Keluarga'}" id="statusKeluargaDelete"
                                          disabled="true" name="payrollParamBpjs.statusKeluarga"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Jumlah Tanggungan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="jumlahTanggunganDelete"
                                              name="payrollParamBpjs.jumlahTanggungan"
                                              required="true" cssClass="form-control" disabled="true"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Nilai :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="nilaiDelete" name="payrollParamBpjs.nilai"
                                              required="true" cssClass="form-control" disabled="true"/>
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