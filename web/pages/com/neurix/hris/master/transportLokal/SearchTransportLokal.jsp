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
            window.location.href="<s:url action='initForm_transportLokal'/>";
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
            Transport Lokal
            <small>e-HEALTH</small>
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
                    <s:form id="transportLokalForm" method="post"  theme="simple" namespace="/transportLokal" action="search_transportLokal.action" cssClass="well form-horizontal">

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
                            <tr >
                                <td>
                                    <label class="control-label"><small>Transport Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="transportLokalId" name="transportLokal.transportLokalId" required="true" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Transport Name :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="transportLokalName" name="transportLokal.transportLokalName" required="true" disabled="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Transport Ke :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="transportLokalKe" name="transportLokal.transportLokalKe" required="true" disabled="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="transportLokal.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="transportLokalForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/transportLokal" action="add_transportLokal" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Transport Lokal
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_transportLokal"/>'">
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
                                                   height="450" width="550" autoOpen="false"
                                                   title="Transport Lokal ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfTransportLokal" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfTransportLokal" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_transportLokal.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/transportLokal" action="edit_transportLokal" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.transportLokalId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/transportLokal" action="delete_transportLokal" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.transportLokalId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                </sj:a>

                                            </display:column>
                                            <display:column property="transportLokalId" sortable="true" title="Transport ID" />
                                            <display:column property="transportLokalName" sortable="true" title="Nama"  />
                                            <display:column property="transportLokalKe" sortable="true" title="Transport Ke"  />
                                            <display:column property="jumlahBiaya" sortable="true" title="Jumlah Transport"  />

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