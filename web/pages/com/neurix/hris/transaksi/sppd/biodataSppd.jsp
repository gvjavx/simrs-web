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
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="" action="" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">View Sppd</legend>


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
                                <s:set name="listOfSppd" value="#session.listOfResultSppd" scope="request" />
                                <display:table name="listOfSppd" class="tableSppd table table-condensed table-striped table-hover"
                                               requestURI="paging_displaytag_sppd.action" export="false" id="row" pagesize="14" style="font-size:10">
                                    <display:column property="personId" sortable="true" title="NIP" />
                                    <display:column property="personName" sortable="true" title="Nama"  />
                                    <display:column property="tipePerson" sortable="true" title="Tipe Person"/>
                                    <display:column property="tanggalBerangkat" sortable="true" title="Tanggal Berangkat"/>
                                    <display:column property="berangkatDari" sortable="true" title="Berangkat Dari"/>
                                    <display:column property="tujuanKe" sortable="true" title="Tujuan Ke"/>
                                    <display:column property="berangkatVia" sortable="true" title="Berangkat Via"/>
                                    <display:column property="kembaliVia" sortable="true" title="Kembali Via"/>
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
