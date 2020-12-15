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
            window.location.href="<s:url action='initForm_rekananops'/>";
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
            RekananOps
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i>RekananOps</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="SearchRekananOpsForm" method="post"  theme="simple"
                                            namespace="/rekananops" action="search_rekananops.action" cssClass="form-horizontal">
                                        <table>
                                            <tr>
                                                <td width="10%" align="center">
                                                    <%@ include file="/pages/common/message.jsp" %>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama rekanan :</small></label>
                                                </td>
                                                <td width="70%">
                                                    <table>
                                                        <s:action id="initComboRekanan" namespace="/rekananops" name="initComboRekanan_rekananops"/>
                                                        <s:select list="#initComboRekanan.listOfComboRekananOps" id="positionId1" name="rekananOps.idRekananOps"
                                                        listKey="idRekananOps" listValue="namaRekanan" headerKey="" headerValue="[Select one]"
                                                        cssClass="form-control" cssStyle="margin-top: 5px"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Flag :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="rekananOps.flag"
                                                                  headerKey="Y" headerValue="Active" cssClass="form-control select2" />
                                                    </table>

                                                </td>
                                            </tr>

                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td >
                                                        <sj:submit type="button" cssStyle="margin-right: 5px" cssClass="btn btn-primary"
                                                                   formIds="SearchRekananOpsForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <s:url var="urlAdd" namespace="/rekananops" action="add_rekananops" escapeAmp="false">
                                                        </s:url>
                                                        <sj:a cssClass="btn btn-success" cssStyle="margin-right: 5px" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                            <i class="fa fa-plus"></i>
                                                            Add RekananOps
                                                        </sj:a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" cssStyle="margin-right: 5px"
                                                                onclick="window.location.href='<s:url
                                                                action="initForm_rekananops"/>'">
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
                                                        <sj:dialog id="waiting_dialog_loading" openTopics="showDialog"
                                                                   closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false"
                                                                   title="Search Data ...">
                                                            Please don't close this window, server is processing your request ...
                                                            <br>
                                                            <center>
                                                                <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                                     src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                                     name="image_indicator_write">
                                                                <br>
                                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                                     name="image_indicator_write">
                                                            </center>
                                                        </sj:dialog>

                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="440" width="600" autoOpen="false"
                                                                   title="RekananOps ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <sj:dialog id="view_dialog_menu_pendapatan" openTopics="showDialogMenuView" modal="true"
                                                                   height="570" width="700" autoOpen="false"
                                                                   title="Pendapatan Dokter">
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_keterangan" openTopics="showDialogMenuKeterangan" modal="true"
                                                                   height="680" width="700" autoOpen="false"
                                                                   title="Pendapatan Dokter">
                                                        </sj:dialog>

                                                        <s:set name="listOfsearchRekananOps" value="#session.listOfResultRekananOps" scope="request" />
                                                        <display:table name="listOfsearchRekananOps" class="table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_rekananOps.action" export="true" id="row"
                                                                       pagesize="14" style="font-size:12">

                                                            <display:column media="html" title="Edit">
                                                                <s:if test='#attr.row.flag == "Y"'>
                                                                    <s:url var="urlEdit" namespace="/rekananops" action="edit_rekananops" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.idRekananOps"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                    </sj:a>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                                <s:if test='#attr.row.flag == "Y"'>
                                                                    <s:url var="urlViewDelete" namespace="/rekananops" action="delete_rekananops" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.idRekananOps" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                    </sj:a>
                                                                </s:if>
                                                            </display:column>
                                                            <%--<display:column property="idRekananOps" sortable="true" title="ID RekananOps" />--%>
                                                            <display:column property="idRekananOps" sortable="true" title="id RekananOps" />
                                                            <display:column property="namaRekanan" sortable="true" title="nama rekanan Ops" />

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
                                        <div id="actions" class="form-actions">
                                            <table>
                                                <tr>
                                                    <div id="crud">
                                                        <td>
                                                            <table>
                                                                <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog"
                                                                           modal="true" resizable="false"
                                                                           height="280" width="500" autoOpen="false" title="Warning"
                                                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                                                >
                                                                    <div class="alert alert-error fade in">
                                                                        <label class="control-label" align="left">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                                                 name="icon_error"> Please check this field :
                                                                            <br/>
                                                                            <center><div id="errorValidationMessage"></div></center>
                                                                        </label>
                                                                    </div>
                                                                </sj:dialog>
                                                            </table>
                                                        </td>
                                                    </div>
                                                </tr>
                                            </table>
                                        </div>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Your Page Content Here -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>