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
            window.location.href="<s:url action='initForm_notifikasi'/>";
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
            Notifikasi
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="notifikasiForm" method="post"  theme="simple" namespace="/notifikasi" action="search_notifikasi.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Notif Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="notifId" name="notifikasi.notifId" required="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Nip :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="nip" name="notifikasi.nip" required="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tipe Notif:</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboTipeNotif" namespace="/tipeNotif" name="initComboTipeNotif_tipeNotif"/>
                                        <s:select list="#initComboTipeNotif.listOfComboTipeNotif" id="tipeNotifId" name="notifikasi.tipeNotifId"
                                                  listKey="tipeNotifId" listValue="tipeNotifName" headerKey="" headerValue="" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Note :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="note" name="notifikasi.note" required="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Read :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="read" name="notifikasi.read" required="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>From Person :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="fromPerson" name="notifikasi.fromPerson" required="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>No Request :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="noRequest" name="notifikasi.noRequest" required="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                        </table>

                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="notifikasiForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/notifikasi" action="add_notifikasi" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Notifikasi
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_notifikasi"/>'">
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
                                                   height="500" width="550" autoOpen="false"
                                                   title="Notifikasi">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfNotifikasi" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfNotifikasi" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_master_notifikasi.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/notifikasi" action="edit_notifikasi" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.notifId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/notifikasi" action="delete_notifikasi" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.notifId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                </sj:a>

                                            </display:column>
                                            <display:column property="notifId" sortable="true" title="Notif Id"  />
                                            <display:column property="nip" sortable="true" title="Nip"  />
                                            <display:column property="tipeNotifId" sortable="true" title="Tipe Notif id"  />
                                            <display:column property="tipeNotifName" sortable="true" title="Tipe Notif Name"  />
                                            <display:column property="note" sortable="true" title="Note" />
                                            <display:column property="read" sortable="true" title="Read"  />
                                            <display:column property="fromPerson" sortable="true" title="From Person"  />
                                            <display:column property="noRequest" sortable="true" title="No Request"  />
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

