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
            window.location.href="<s:url action='initForm_smkPersenSmkNilai'/>";
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
            Smk Persen Nilai
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="smkPersenSmkNilaiForm" method="post"  theme="simple" namespace="/smkPersenSmkNilai" action="search_smkPersenSmkNilai.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="smkNilaiId" name="smkPersenSmkNilai.smkNilaiId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Nama :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="nama" name="smkPersenSmkNilai.nama" required="false" readonly="false" cssClass="form-control"/>
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
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="smkPersenSmkNilai.branchId"
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
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="smkPersenSmkNilai.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="smkPersenSmkNilaiForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/smkPersenSmkNilai" action="add_smkPersenSmkNilai" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Persen Nilai
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_smkPersenSmkNilai"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="35%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="400" width="550" autoOpen="false"
                                                   title="Smk Persen Nilai">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfPayrollSkalaGaji" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfPayrollSkalaGaji" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_smkPersenSmkNilai.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/smkPersenSmkNilai" action="edit_smkPersenSmkNilai" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.smkNilaiId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/smkPersenSmkNilai" action="delete_smkPersenSmkNilai" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.smkNilaiId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                </sj:a>

                                            </display:column>
                                            <display:column property="smkNilaiId" sortable="true" title="Persen Id" />
                                            <display:column property="branchName" sortable="true" title="Unit"  />
                                            <display:column property="nilaiBawah" sortable="true" title="Nilai Bawah"  />
                                            <display:column property="nilaiAtas" sortable="true" title="Nilai Atas"  />
                                            <display:column property="nilai" sortable="true" title="Nilai"  />
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

