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
        .checkApprove {width: 20px; height: 20px;}
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
            window.location.href="<s:url action='initForm_fingerPrint'/>";
        }

    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Finger Print
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="fingerPrintForm" method="post"  theme="simple" namespace="/fingerPrint" action="search_fingerPrint.action" cssClass="form-horizontal">

                                    <s:hidden name="addOrEdit"/>
                                    <s:hidden name="delete"/>

                                    <table>
                                        <tr>
                                            <td width="10%" align="center">
                                                <%@ include file="/pages/common/message.jsp" %>
                                            </td>
                                        </tr>
                                    </table>
                                    <table>
                                        <tr >
                                            <td>
                                                <label class="control-label"><small>ID Finger Print</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="id_fingerPrint" name="fingerPrint.idFingerPrint" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Nama Finger Print</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px"  id="nama_fingerPrint" name="fingerPrint.namaFingerPrint" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>No Finger Print</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="no_fingerPrint" name="fingerPrint.noFingerPrint" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="fingerPrintForm" id="search" name="search"
                                                               onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>
                                                    <s:url var="urlAdd" namespace="/fingerPrint" action="add_fingerPrint" escapeAmp="false">
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                        <button class="btn btn-success" ><i class="fa fa-plus"></i> Add Finger Print</button>
                                                    </sj:a>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_fingerPrint"/>'">
                                                        <i class="fa fa-refresh"></i> Reset
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>

                                    <br>
                                    <br>
                                    <center>
                                        <table id="showdata" width="90%">
                                            <tr>
                                                <td align="center">
                                                    <sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading" closeTopics="closeDialogLoading" modal="true"
                                                               resizable="false"
                                                               height="350" width="600" autoOpen="false" title="Loading ...">
                                                        Please don't close this window, server is processing your request ...
                                                        </br>
                                                        </br>
                                                        </br>
                                                        <center>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                                        </center>
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                               height="800" width="1100" autoOpen="false"
                                                               title="FingerPrint ">
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenuView" modal="true"
                                                               height="700" width="1100" autoOpen="false"
                                                               title="Rekruitmen ">
                                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                    </sj:dialog>
                                                    <s:set name="listOfRekruitmen" value="#session.listOfResult" scope="request" />
                                                    <display:table name="listOfRekruitmen" class=" tableRekruitmen table table-condensed table-striped table-hover"
                                                                   requestURI="paging_displaytag_fingerPrint.action" export="true" id="row" pagesize="14" style="font-size:12">
                                                        <%--<display:column property="calonPegawaiId" sortable="true" title="Cal Peg ID"  />--%>
                                                        <display:column media="html" title="Edit">
<%--                                                            <s:if test="#attr.row.flagYes">--%>
                                                                <s:url var="urlEdit" namespace="/fingerPrint" action="edit_fingerPrint" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.idFingerPrint"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                </sj:a>
<%--                                                            </s:if>--%>
                                                        </display:column>

                                                        <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                            <s:url var="urlViewDelete" namespace="/fingerPrint" action="delete_fingerPrint" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.idFingerPrint" /></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                                <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                            </sj:a>

                                                        </display:column>
                                                        <display:column property="idFingerPrint" sortable="true" title="FingerPrint ID"/>
                                                        <display:column property="namaFingerPrint" sortable="true" title="Nama"  />
                                                        <display:column property="noFingerPrint" sortable="true" title="No. FingerPrint"  />
                                                        <display:column property="statusFingerPrint" sortable="true" title="Status FingerPrint" />
                                                        <display:column property="namaKelasFingerPrint" sortable="true" title="Kelas FingerPrint" />
                                                        <display:column property="tarif" sortable="true" title="Tarif" />

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
            </div>
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

