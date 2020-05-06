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
            window.location.href="<s:url action='initForm_akunpembayaran'/>";
        }

    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PembayaranAction.js"/>'></script>
</head>
<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Akun Pembayaran
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Form Akun Pembayaran </h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="akunpembayaranForm" method="post"  theme="simple" namespace="/akunpembayaran" action="search_akunpembayaran.action" cssClass="form-horizontal">
                                        <table>
                                            <tr>
                                                <td width="10%" align="center">
                                                    <%@ include file="/pages/common/message.jsp" %>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <div class="form-group">
                                                <label text-align="left" class="control-label col-sm-4">Pembayaran ID</label>
                                                <div class="col-sm-4">
                                                    <s:textfield id="pembayaranId" cssStyle="margin-top: 7px"
                                                                 name="pembayaran.pembayaranId" required="false"
                                                                 readonly="false" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label text-align="left" class="control-label col-sm-4">Nama Pembayaran</label>
                                                <div class="col-sm-4">
                                                    <s:textfield id="pembayaranName" name="pembayaran.pembayaranName"
                                                                 required="false" readonly="false"
                                                                 cssClass="form-control" cssStyle="margin-top: 7px"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label text-align="left" class="control-label col-sm-4">kode Rekening ( COA )</label>
                                                <div class="col-sm-4">
                                                    <s:textfield id="coa" name="pembayaran.coa"
                                                                 required="false" readonly="false"
                                                                 cssClass="form-control" cssStyle="margin-top: 7px"/>
                                                    <script>
                                                        $(document).ready(function() {
                                                            var functions, mapped;
                                                            $('#coa').typeahead({
                                                                minLength: 1,
                                                                source: function (query, process) {
                                                                    functions = [];
                                                                    mapped = {};
                                                                    var data = [];
                                                                    dwr.engine.setAsync(false);
                                                                    PembayaranAction.initTypeaheadKodeRekening(query,function (listdata) {
                                                                        data = listdata;
                                                                    });
                                                                    $.each(data, function (i, item) {
                                                                        var labelItem = item.kodeRekening + " | " + item.namaKodeRekening;
                                                                        mapped[labelItem] = {
                                                                            id: item.kodeRekening,
                                                                            nama: item.namaKodeRekening
                                                                        };
                                                                        functions.push(labelItem);
                                                                    });
                                                                    process(functions);
                                                                },
                                                                updater: function (item) {
                                                                    var selectedObj = mapped[item];
                                                                    return selectedObj.id;
                                                                }
                                                            });
                                                        });
                                                    </script>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label text-align="left" class="control-label col-sm-4">Flag</label>
                                                <div class="col-sm-4">
                                                    <s:select list="#{'N':'Non-Active'}" id="flag" name="pembayaran.flag"
                                                              headerKey="Y" headerValue="Active" cssClass="form-control" />
                                                </div>
                                            </div>

                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="akunpembayaranForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <s:url var="urlAdd" namespace="/akunpembayaran" action="add_akunpembayaran" escapeAmp="false">
                                                        </s:url>
                                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                            <i class="fa fa-plus"></i>
                                                            Add Akun Pembayaran
                                                        </sj:a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_akunpembayaran"/>'">
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
                                                                   height="400" width="600" autoOpen="false"
                                                                   title="Akun Pembayaran ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <sj:dialog id="view_dialog_menu_pendapatan" openTopics="showDialogMenuView" modal="true"
                                                                   height="570" width="700" autoOpen="false"
                                                                   title="Akun Pembayaran">
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_keterangan" openTopics="showDialogMenuKeterangan" modal="true"
                                                                   height="680" width="700" autoOpen="false"
                                                                   title="Akun Pembayaran">
                                                        </sj:dialog>

                                                        <s:set name="listOfsearchPembayaran" value="#session.listOfResultPembayaran" scope="request" />
                                                        <display:table name="listOfsearchPembayaran" class="table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_akunpembayaran.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                            <display:column media="html" title="Edit">
                                                                <s:if test='#attr.row.flag == "Y"'>
                                                                    <s:url var="urlEdit" namespace="/akunpembayaran" action="edit_akunpembayaran" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.pembayaranId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                    </sj:a>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                                <s:if test='#attr.row.flag == "Y"'>
                                                                    <s:url var="urlViewDelete" namespace="/akunpembayaran" action="delete_akunpembayaran" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.pembayaranId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                    </sj:a>
                                                                </s:if>
                                                            </display:column>
                                                            <display:column property="pembayaranId" sortable="true" title="Pembayaran ID" />
                                                            <display:column property="pembayaranName" sortable="true" title="Nama Pembayaran"  />
                                                            <display:column property="coa" sortable="true" title="COA"/>
                                                            <display:column property="flag" sortable="true" title="flag"  />
                                                            <display:column property="action" sortable="true" title="action"  />
                                                            <display:column property="stCreatedDate" sortable="true" title="Created date"  />
                                                            <display:column property="createdWho" sortable="true" title="Created who"  />
                                                            <display:column property="stLastUpdate" sortable="true" title="Last update"  />
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
                                                                <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                                                           height="280" width="500" autoOpen="false" title="Warning"
                                                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                                                >
                                                                    <div class="alert alert-error fade in">
                                                                        <label class="control-label" align="left">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
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