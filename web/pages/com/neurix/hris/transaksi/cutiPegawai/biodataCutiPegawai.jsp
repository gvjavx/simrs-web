<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">
        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        }
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        }
    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/cutiPegawai" action="saveEdit_cutiPegawai" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">View Cuti Pegawai</legend>


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
                            <label class="control-label"><small>NIP :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="nipId" name="biodata.nip" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Nama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="namaId1" name="biodata.namaPegawai" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Posisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                <s:select disabled="true" cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid12" name="biodata.positionId" required="false" readonly="true"
                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select disabled="true" list="#comboDivisi.listComboDepartment" id="divisiId12" name="biodata.divisi"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                <s:select disabled="true" cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="biodata.branch" required="true" readonly="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <center>
                    <table id="showdata" width="80%">
                        <tr>
                            <td align="center">
                                <s:set name="listOfCutiPegawai" value="#session.listOfResultCutiPegawai" scope="request" />
                                <display:table name="listOfCutiPegawai" class="tableCutiPegawai table table-condensed table-striped table-hover"
                                               requestURI="paging_displaytag_cutiPegawai.action" export="false" id="row" pagesize="14" style="font-size:10">
                                    <display:column property="cutiPegawaiId" sortable="true" title="Cuti Pegawai ID" />
                                    <display:column property="nip" sortable="true" title="NIP"  />
                                    <display:column property="namaPegawai" sortable="true" title="Nama Pegawai"  />
                                    <display:column property="pegawaiPenggantiSementara" sortable="true" title="Pegawai Pengganti"/>
                                    <display:column property="cutiName" sortable="true" title="Nama Cuti"/>
                                    <display:column property="posisiName" sortable="true" title="Posisi"/>
                                    <display:column property="divisiName" sortable="true" title="Divisi"/>
                                    <display:column property="unitName" sortable="true" title="Unit"/>
                                    <display:column property="lamaHariCuti" sortable="true" title="Lama Cuti"/>
                                    <display:column property="tanggalDari" sortable="true" title="Tanggal Dari"/>
                                    <display:column property="tanggalSelesai" sortable="true" title="Tanggal Selesai"/>
                                    <display:column media="html" title="Approval Atasan">
                                        <s:if test="#attr.row.cutiPegawaiApprove">
                                            <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                        </s:if>
                                        <s:else>
                                            <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                        </s:else>
                                    </display:column>
                                    <display:column media="html" title="Approval SDM">
                                        <s:if test="#attr.row.cutiPegawaiApproveSdm">
                                            <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                        </s:if>
                                        <s:else>
                                            <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                        </s:else>
                                    </display:column>
                                </display:table>
                            </td>
                        </tr>
                    </table>
                </center>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                            <i class="fa fa-close"/> Close
                        </button>
                    </div>
                </div>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>
