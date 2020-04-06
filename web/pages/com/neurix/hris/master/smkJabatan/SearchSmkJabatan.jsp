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
            window.location.href="<s:url action='initForm_smkJabatan'/>";
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
            SMK Jabatan
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="smkJabatanForm" method="post"  theme="simple" namespace="/smkJabatan" action="search_smkJabatan.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Jabatan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="smkJabatan.positionId"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="smkJabatan.divisiId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="smkJabatan.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="smkJabatan.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="smkJabatanForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/smkJabatan" action="add_smkJabatan" escapeAmp="false">
                                        </s:url>
                                        <s:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add SMK Pegawai
                                        </s:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_smkJabatan"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="65%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="400" width="500" autoOpen="false"
                                                   title="Smk Jabatan">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <sj:dialog id="view_dialog_menu2" openTopics="showDialogMenu2" modal="true"
                                                   height="500" width="700" autoOpen="false"
                                                   title="Smk Jabatan Detail">
                                            <%--<center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>--%>
                                        </sj:dialog>

                                        <sj:dialog id="view_dialog_menu3" openTopics="showDialogMenu3" modal="true"
                                                   height="400" width="500" autoOpen="false"
                                                   title="Smk Jabatan Action">
                                            <%--<center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>--%>
                                        </sj:dialog>

                                        <s:set name="listOfSmkPegawai" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfSmkPegawai" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_smkJabatan.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Action" style="text-align:center;font-size:9">
                                                <s:url var="urlViewAction" namespace="/smkJabatan" action="detailAction_smkJabatan" escapeAmp="false">
                                                    <s:param name="position"><s:property value="#attr.row.positionId" /></s:param>
                                                    <s:param name="divisi"><s:property value="#attr.row.divisiId" /></s:param>
                                                    <s:param name="branch"><s:property value="#attr.row.branchId" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewAction}">
                                                    <img border="0" src="<s:url value="/pages/images/view_detail_project.png"/>" name="icon_detail">
                                                </sj:a>
                                            </display:column>
                                            <%--<display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/smkJabatan" action="edit_smkJabatan" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.jabatanSmkId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </s:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/smkJabatan" action="delete_smkJabatan" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.jabatanSmkId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <s:a  href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                </s:a>
                                            </display:column>--%>
                                            <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                <s:url var="urlView" namespace="/smkJabatan" action="detail_smkJabatan" escapeAmp="false">
                                                    <s:param name="position"><s:property value="#attr.row.positionId" /></s:param>
                                                    <s:param name="divisi"><s:property value="#attr.row.divisiId" /></s:param>
                                                    <s:param name="branch"><s:property value="#attr.row.branchId" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlView}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_detail">
                                                </sj:a>
                                            </display:column>
                                            <display:column property="branchName" sortable="true" title="Unit"  />
                                            <display:column property="divisiName" sortable="true" title="Divisi" />
                                            <display:column property="positionName" sortable="true" title="Posisi"  />
                                            <display:column property="bobot" sortable="true" title="Bobot"  />

                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </s:form>
                </td>
            </tr>
        </table>

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

