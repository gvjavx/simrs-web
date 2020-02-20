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
            window.location.href="<s:url action='initForm_mesinAbsensiDetail'/>";
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
            Mesin Absensi Detail
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
                    <s:form id="mesinAbsensiDetailForm" method="post"  theme="simple" namespace="/mesinAbsensiDetail" action="search_mesinAbsensiDetail.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Mesin Abs. Detail Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="mesinAbsensiDetailId" name="mesinAbsensiDetail.mesinAbsensiDetailId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Pin :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="pin" name="mesinAbsensiDetail.pin" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Status :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="status" name="mesinAbsensiDetail.status" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Scan Date :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="loginTimestampFrom" name="mesinAbsensiDetail.stTanggalDari" cssClass="form-control pull-right"
                                                         required="false" size="7"  cssStyle=""/>
                                            <div class="input-group-addon">
                                                s/d
                                            </div>
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="loginTimestampTo" name="mesinAbsensiDetail.stTanggalSelesai" cssClass="form-control pull-right"
                                                         required="false" size="7"  cssStyle=""/>
                                        </div>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Verify Mode :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="verifyMode" name="mesinAbsensiDetail.verifyMode" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Work Kode :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="workCode" name="mesinAbsensiDetail.workCode" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br>
                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="mesinAbsensiDetailForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <%--<td>--%>
                                        <%--<s:url var="urlAdd" namespace="/mesinAbsensiDetail" action="add_mesinAbsensiDetail" escapeAmp="false">--%>
                                        <%--</s:url>--%>
                                        <%--<sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">--%>
                                            <%--<i class="fa fa-plus"></i>--%>
                                            <%--Add Mesin Abs. Detail--%>
                                        <%--</sj:a>--%>
                                    <%--</td>--%>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_mesinAbsensiDetail"/>'">
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
                                                   height="500" width="550" autoOpen="false"
                                                   title="Mesin Abs. Detail ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfMesinAbsensiDetail" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfMesinAbsensiDetail" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_mesinAbsensiDetail.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <%--<display:column media="html" title="Edit">--%>
                                                <%--<s:if test="#attr.row.flagYes">--%>
                                                    <%--<s:url var="urlEdit" namespace="/mesinAbsensiDetail" action="edit_mesinAbsensiDetail" escapeAmp="false">--%>
                                                        <%--<s:param name="id"><s:property value="#attr.row.mesinAbsensiDetailId"/></s:param>--%>
                                                        <%--<s:param name="flag"><s:property value="#attr.row.flag"/></s:param>--%>
                                                    <%--</s:url>--%>
                                                    <%--<sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">--%>
                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">--%>
                                                    <%--</sj:a>--%>
                                                <%--</s:if>--%>
                                            <%--</display:column>--%>

                                            <%--<display:column media="html" title="Delete" style="text-align:center;font-size:9">--%>
                                                <%--<s:url var="urlViewDelete" namespace="/mesinAbsensiDetail" action="delete_mesinAbsensiDetail" escapeAmp="false">--%>
                                                    <%--<s:param name="id"><s:property value="#attr.row.mesinAbsensiDetailId" /></s:param>--%>
                                                    <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                <%--</s:url>--%>
                                                <%--<sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">--%>
                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">--%>
                                                <%--</sj:a>--%>

                                            <%--</display:column>--%>
                                            <display:column property="mesinAbsensiDetailId" sortable="true" title="ID" />
                                            <display:column property="pin" sortable="true" title="Pin"  />
                                            <display:column property="status" sortable="true" title="Status"  />
                                            <display:column property="scanDate" sortable="true" title="Scan Date"  />
                                            <display:column property="verifyMode" sortable="true" title="Verify Mode"  />
                                            <display:column property="workCode" sortable="true" title="Work Kode"  />

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

<script>
    $(document).ready(function(){
        $('#loginTimestampTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#loginTimestampFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    })
</script>



