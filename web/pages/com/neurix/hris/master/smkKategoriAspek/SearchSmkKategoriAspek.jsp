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
            window.location.href="<s:url action='initForm_smkKategoriAspek'/>";
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
            SMK Kategori Aspek
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="smkKategoriAspekBForm" method="post"  theme="simple" namespace="/smkKategoriAspek" action="search_smkKategoriAspek.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Kategori Aspek Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="smkKategoriAspekId" name="smkKategoriAspek.kategoriAspekId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Kategori Name :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="smkKategoriAspekName" name="smkKategoriAspek.kategoriName" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Bobot :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="smkKategoriAspekBobot" name="smkKategoriAspek.bobot" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tipe Aspek :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboTipeAspek" namespace="/tipeAspek" name="initComboTipeAspek_tipeAspek"/>
                                        <s:select list="#initComboTipeAspek.tipeAspekList" id="tipeAspekId"
                                                  name="smkKategoriAspek.tipeAspekId"
                                                  listKey="tipeAspekId" listValue="tipeAspekName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId"
                                                  name="smkKategoriAspek.branchId"
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
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="smkKategoriAspek.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="smkKategoriAspekBForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>

                                        <s:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="add_smkKategoriAspek.action">
                                            <i class="fa fa-plus"></i>
                                            Add Kategori
                                        </s:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_smkKategoriAspek"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="60%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="500" width="900" autoOpen="false"
                                                   title="Smk Kategori ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfsmkKategoriAspekB" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfsmkKategoriAspekB" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_smkKategoriAspek.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/smkKategoriAspek" action="edit_smkKategoriAspek" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.kategoriAspekId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <%--<sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>--%>
                                                    <s:a href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </s:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlView" namespace="/smkKategoriAspek" action="delete_smkKategoriAspek" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.kategoriAspekId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{urlView}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </s:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Detail" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/smkKategoriAspek" action="detail_smkKategoriAspek" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.kategoriAspekId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_trash">
                                                </sj:a>
                                            </display:column>

                                            <%--<display:column property="kategoriAspekId" sortable="true" title="Kategori Aspek ID" />--%>
                                            <display:column property="kategoriName" sortable="true" title="Nama Kategori"  />
                                            <display:column property="tipeAspekId" sortable="true" title="Tipe Aspek ID"  />
                                            <display:column property="tipeAspekName" sortable="true" title="Tipe Aspek Name"  />
                                            <display:column property="branchName" sortable="true" title="Unit"  />

                                            <display:column property="flag" sortable="true" title="Flag" />
                                            <display:column property="createdWho" sortable="true" title="CreatedWho"/>

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

