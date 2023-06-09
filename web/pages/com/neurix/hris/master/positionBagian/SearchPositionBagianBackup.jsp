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
            window.location.href="<s:url action='initForm_positionBagian'/>";
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
            Sub Bidang/Divisi
        </h1>
        <%--<ol class="breadcrumb">--%>
        <%--<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>--%>
        <%--<li class="active">Here</li>--%>
        <%--</ol>--%>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="positionBagianForm" method="post"  theme="simple" namespace="/positionBagian" action="search_positionBagian.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Sub Bidang/Divisi Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="positionBagianId" name="positionBagian.bagianId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Bidang/Devisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboMasaTanam" namespace="/department" name="initDepartment_department"/>
                                        <s:select list="#session.listOfResultDepartment" id="departmentId" name="positionBagian.divisiId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Sub Bidang/Divisi Name :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="positionBagianName" name="positionBagian.bagianName" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="positionBagian.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="positionBagianForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/positionBagian" action="add_positionBagian" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Bagian
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_positionBagian"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="80%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="350" width="500" autoOpen="false"
                                                   title="Position Bagian ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfPositionBagian" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfPositionBagian" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_positionBagian.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/positionBagian" action="edit_positionBagian" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.bagianId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlViewDelete" namespace="/positionBagian" action="delete_positionBagian" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.bagianId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>
                                            <display:column property="bagianId" sortable="true" title="Sub Bidang/Divisi Id" />
                                            <display:column property="bagianName" sortable="true" title="Sub Bidang/Divisi Name"  />
                                            <display:column property="divisiName" sortable="true" title="Bidang/Divisi"  />
                                            <display:column property="kodering" sortable="true" title="Kodering"  />
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