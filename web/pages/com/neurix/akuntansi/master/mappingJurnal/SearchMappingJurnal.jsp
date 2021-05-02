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
            window.location.href="<s:url action='initForm_mappingJurnal'/>";
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
            Mapping Jurnal
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Mapping Jurnal</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="mappingJurnalForm" method="post"  theme="simple" namespace="/mappingJurnal" action="search_mappingJurnal.action" cssClass="form-horizontal">
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
                                                    <label class="control-label"><small>Tipe Jurnal :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboTipeJurnal" namespace="/tipeJurnal" name="initComboTipeJurnal_tipeJurnal"/>
                                                        <s:select list="#initComboTipeJurnal.listOfComboTipeJurnal" id="tipeJurnalId" name="mappingJurnal.tipeJurnalId"
                                                                  listKey="tipeJurnalId" listValue="tipeJurnalName" headerKey="" headerValue="[ Select One ]" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe Transaksi :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboTrans" namespace="/trans" name="initComboTrans_trans"/>
                                                        <s:select list="#comboTrans.listOfComboTrans" id="transId" name="mappingJurnal.transId"
                                                                  onchange="$(this).css('border','')"
                                                                  listKey="transId" listValue="transName" headerKey="" headerValue="[ Select One ]" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Flag :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="mappingJurnal.flag"
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
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="mappingJurnalForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <a href="add_mappingJurnal.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Mapping Jurnal</a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_mappingJurnal"/>'">
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
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="300" width="550" autoOpen="false"
                                                                   title="MappingJurnalaksi Billing">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <s:set name="listOfMappingJurnal" value="#session.listOfResult" scope="request" />
                                                        <display:table name="listOfMappingJurnal" class="table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_mappingJurnal.action" export="true" id="row" pagesize="30" style="font-size:10">
                                                            <s:if test='#attr.row.flag == "Y"'>
                                                                <display:column media="html" title="View">
                                                                    <s:if test='#attr.row.tipeJurnalName != ""'>
                                                                        <s:url var="urlView" namespace="/mappingJurnal" action="view_mappingJurnal" escapeAmp="false">
                                                                            <s:param name="transId"><s:property value="#attr.row.transId"/></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                        </s:url>
                                                                        <s:a href="%{urlView}">
                                                                            <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_view">
                                                                        </s:a>
                                                                    </s:if>
                                                                </display:column>

                                                                <display:column media="html" title="Edit">
                                                                    <s:if test='#attr.row.tipeJurnalName != ""'>
                                                                        <s:url var="urlEdit" namespace="/mappingJurnal" action="edit_mappingJurnal" escapeAmp="false">
                                                                            <s:param name="transId"><s:property value="#attr.row.transId"/></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                        </s:url>
                                                                        <s:a href="%{urlEdit}">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                        </s:a>
                                                                    </s:if>
                                                                </display:column>
                                                                <display:column media="html" title="Delete">
                                                                    <s:if test='#attr.row.tipeJurnalName != ""'>
                                                                        <s:url var="urlDelete" namespace="/mappingJurnal" action="delete_mappingJurnal" escapeAmp="false">
                                                                            <s:param name="transId"><s:property value="#attr.row.transId"/></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                        </s:url>
                                                                        <s:a href="%{urlDelete}">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_delete">
                                                                        </s:a>
                                                                    </s:if>
                                                                </display:column>
                                                            </s:if>
                                                            <%--<display:column property="mappingJurnalId" sortable="true" title="Mapping Jurnal ID" />--%>
                                                            <display:column property="tipeJurnalName" sortable="true" title="Tipe Jurnal"  />
                                                            <display:column property="transName" sortable="true" title="Nama Trans"  />
                                                            <display:column property="kodeRekening" sortable="true" title="Kode Rekening"  />
                                                            <display:column property="kodeRekeningName" sortable="true" title="Nama Kode Rekening"  />
                                                            <display:column property="posisi" title="Posisi"  />
                                                            <display:column property="masterId" title="Master"  />
                                                            <display:column property="bukti" title="Bukti"  />
                                                            <display:column property="kodeBarang" title="Kode Barang"  />
                                                            <display:column property="divisiId" title="Divisi ID"  />
                                                            <display:column property="kirimList" title="List Kirim"  />
                                                            <display:column property="keterangan" sortable="true" title="Parameter"  />
                                                            <%--<display:column property="lastUpdate" sortable="true" title="Last Update"/>--%>
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
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>

