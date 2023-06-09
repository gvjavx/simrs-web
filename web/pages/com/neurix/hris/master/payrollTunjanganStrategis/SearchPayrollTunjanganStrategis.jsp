<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>

    <style>
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_payrollTunjanganStrategis'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Payroll Tunjangan Fungsional
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Tunjangan Fungsional </h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="payrollTunjanganStrategisForm" method="post"  theme="simple" namespace="/payrollTunjanganStrategis" action="search_payrollTunjanganStrategis.action" cssClass="form-horizontal">

                                        <s:hidden name="addOrEdit"/>
                                        <s:hidden name="delete"/>

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
                                                    <label class="control-label"><small>T. Jab Fungsional Id :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="tunjStrategisId" name="payrollTunjanganStrategis.tunjStrategisId" required="false" readonly="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <%--<tr>--%>
                                                <%--<td>--%>
                                                    <%--<label class="control-label"><small>Jabatan :</small></label>--%>
                                                <%--</td>--%>
                                                <%--<td>--%>
                                                    <%--<table>--%>
                                                        <%--<s:action id="comboPosition" namespace="/admin/position"--%>
                                                                  <%--name="searchPosition_position"/>--%>
                                                        <%--<s:select list="#comboPosition.listOfComboPosition" id="positionId"--%>
                                                                  <%--name="payrollTunjanganStrategis.positionId"--%>
                                                                  <%--listKey="positionId" listValue="positionName" headerKey=""--%>
                                                                  <%--headerValue="[Select one]" cssClass="form-control"/>--%>
                                                    <%--</table>--%>
                                                <%--</td>--%>
                                            <%--</tr>--%>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Profesi :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboProfesi" namespace="/profesi" name="searchProfesi_profesi"/>
                                                        <s:select list="#comboProfesi.listComboProfesi" id="profesiId" name="payrollTunjanganStrategis.profesiId"
                                                                  listKey="profesiId" listValue="profesiName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Level :</small></label>
                                                </td>
                                                <td id="golongan1Group">
                                                    <table>
                                                        <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                                                        <s:select list="#initComboTipe.listComboGolongan" id="golonganId" name="payrollTunjanganStrategis.golonganId"
                                                                  listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                                <%--<tr>
                                                    <td>
                                                        <label class="control-label"><small>Branch :</small></label>
                                                    </td>
                                                    <td>
                                                        <table>
                                                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchId" name="payrollTunjanganJabatanStruktural.branchId" required="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                                        </table>
                                                    </td>
                                                </tr>--%>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Flag :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="payrollTunjanganStrategis.flag"
                                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                                    </table>

                                                </td>
                                            </tr>

                                        </table>
                                        <br>

                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="payrollTunjanganStrategisForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <s:url var="urlAdd" namespace="/payrollTunjanganStrategis" action="add_payrollTunjanganStrategis" escapeAmp="false">
                                                        </s:url>
                                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                            <i class="fa fa-plus"></i>
                                                            Add Tunjangan Jabatan Fungsional
                                                        </sj:a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_payrollTunjanganStrategis"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <br>
                                        <br>
                                        <center>
                                            <table id="showdata" width="40%">
                                                <tr>
                                                    <td align="center">
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="400" width="550" autoOpen="false"
                                                                   title="Payroll Tunjangan Fungsional">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <s:set name="listOfPayrollSkalaGaji" value="#session.listOfResult" scope="request" />
                                                        <display:table name="listOfPayrollSkalaGaji" class="table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_payrollTunjanganStrategis.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                            <display:column media="html" title="Edit">
                                                                <s:if test="#attr.row.flagYes">
                                                                    <s:url var="urlEdit" namespace="/payrollTunjanganStrategis" action="edit_payrollTunjanganStrategis" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.tunjStrategisId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                    </sj:a>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                                <s:if test="#attr.row.flagYes">
                                                                    <s:url var="urlViewDelete" namespace="/payrollTunjanganStrategis" action="delete_payrollTunjanganStrategis" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.tunjStrategisId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                    </sj:a>
                                                                </s:if>
                                                            </display:column>
                                                            <display:column property="tunjStrategisId" sortable="true" title="T. Jab. Fungsional ID" />
                                                            <display:column property="profesiName" sortable="true" title="Profesi"  />
                                                            <display:column property="stNilai" sortable="true" title="Nilai"  />
                                                            <display:column property="golonganName" sortable="true" title="Level"  />
                                                            <display:column property="flag" sortable="true" title="flag"  />
                                                            <display:column property="action" sortable="true" title="action"  />
                                                            <display:column property="createdDate" sortable="true" title="Created date"  />
                                                            <display:column property="createdWho" sortable="true" title="Created who"  />
                                                            <display:column property="lastUpdate" sortable="true" title="Last update"  />
                                                            <display:column property="lastUpdateWho" sortable="true" title="Last update who"  />
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

