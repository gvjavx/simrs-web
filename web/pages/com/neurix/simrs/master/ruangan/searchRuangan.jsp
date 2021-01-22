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
            window.location.href="<s:url action='initForm_ruangan'/>";
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
            Ruangan
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title" > <i class="fa fa-filter"></i>Search Form</h3>
                    </div>
                    <div class="box-body">
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="ruanganForm" method="post"  theme="simple"
                                        namespace="/ruangan" action="search_ruangan.action" cssClass="form-horizontal">

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
                                        <%--<tr >--%>
                                            <%--<td width="18%">--%>
                                                <%--<label class="control-label"><small>ID Ruangan :</small></label>--%>
                                            <%--</td>--%>
                                            <%--<td>--%>
                                                <%--<table >--%>
                                                    <%--<s:textfield cssStyle="margin-top: 7px" id="id_ruangan"--%>
                                                                 <%--name="ruangan.idRuangan" required="false"--%>
                                                                 <%--readonly="false" cssClass="form-control"/>--%>
                                                <%--</table>--%>
                                            <%--</td>--%>
                                        <%--</tr>--%>
                                        <tr>
                                            <td width="18%">
                                                <label class="control-label"><small>Nama Ruangan :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px"  id="nama_ruangan" name="ruangan.namaRuangan" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>No Ruangan :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="no_ruangan" name="ruangan.noRuangan" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Unit :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                        <%--<s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>--%>
                                                        <%--<s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pendapatanDokter.branchId"--%>
                                                        <%--listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                                    <s:if test='pelayanan.branchUser == "01"'>
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="ruangan.branchId"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="ruangan.branchId" disabled="true"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>
                                                        <s:hidden id="branchId" name="ruangan.branchId" />
                                                    </s:else>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Kelas :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="initComboKelas" namespace="/ruangan" name="initComboKelasRuangan_ruangan"/>
                                                    <s:select list="#initComboKelas.listOfComboKelasRuangan" id="idKelasRuangan" name="ruangan.idKelasRuangan"
                                                              listKey="idKelasRuangan" listValue="namaKelasRuangan" headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control select2"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <%--<tr>--%>
                                            <%--<td>--%>
                                                <%--<label class="control-label"><small>Status Ruangan :</small></label>--%>
                                            <%--</td>--%>
                                            <%--<td>--%>
                                                <%--<table>--%>
                                                    <%--<s:select list="#{'N':'Tidak Tersedia'}" id="statusRuangan" name="ruangan.statusRuangan"--%>
                                                              <%--headerKey="Y" headerValue="Tersedia" cssClass="form-control" />--%>
                                                <%--</table>--%>

                                            <%--</td>--%>
                                        <%--</tr>--%>

                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Flag :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'N':'Non-Active'}" id="flag" name="ruangan.flag"
                                                              headerKey="Y" headerValue="Active" cssClass="form-control select2" />
                                                </table>

                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="ruanganForm" id="search" name="search"
                                                               onClickTopics="showDialogLoading" cssStyle="margin-right: 5px" onCompleteTopics="closeDialogLoading" >
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>
                                                    <s:url var="urlAdd" namespace="/ruangan" action="add_ruangan" escapeAmp="false">
                                                    </s:url>
                                                    <sj:a cssClass="btn btn-success" cssStyle="margin-right: 5px" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                        <i class="fa fa-plus"></i>
                                                        Add Pelayanan
                                                    </sj:a>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-danger" cssStyle="margin-right: 5px" onclick="window.location.href='<s:url action="initForm_ruangan"/>'">
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
                                                        <sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading"
                                                                   closeTopics="closeDialogLoading" modal="true"
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
                                                                   height="575" width="600" autoOpen="false"
                                                                   title="Ruangan ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenuView" modal="true"
                                                               height="700" width="1100" autoOpen="false"
                                                               title="Rekruitmen ">
                                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                    </sj:dialog>
                                                    <s:set name="listOfRuangan" value="#session.listOfResultRuangan" scope="request" />
                                                    <display:table name="listOfRuangan" class=" tableRekruitmen table table-condensed table-striped table-hover"
                                                                   requestURI="paging_displaytag_ruangan.action" export="true" id="row" pagesize="14"
                                                                   style="font-size:12">
                                                        <%--<display:column property="calonPegawaiId" sortable="true" title="Cal Peg ID"  />--%>
                                                        <display:column media="html" title="Edit">
<%--                                                            <s:if test="#attr.row.flagYes">--%>
                                                                <s:url var="urlEdit" namespace="/ruangan" action="edit_ruangan" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.idRuangan"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                </sj:a>
<%--                                                            </s:if>--%>
                                                        </display:column>

                                                        <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                            <s:url var="urlViewDelete" namespace="/ruangan" action="delete_ruangan" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.idRuangan" /></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                                <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                            </sj:a>

                                                        </display:column>
                                                        <display:column property="idRuangan" sortable="true" title="Ruangan ID"/>
                                                        <display:column property="namaRuangan" sortable="true" title="Nama"  />
                                                        <display:column property="noRuangan" sortable="true" title="No. Ruangan"  />
                                                        <%--<display:column property="statusRuanganName" sortable="true" title="Status Ruangan" />--%>
                                                        <display:column property="namaKelasRuangan" sortable="true" title="Kelas Ruangan" />
                                                        <display:column property="keterangan" sortable="true" title="Keterangan" />
                                                        <display:column property="branchName" sortable="true" title="Unit" />
                                                        <%--<display:column property="sisaKuota" sortable="true" title="Sisa Kuota" />--%>
                                                        <%--<display:column property="kuota" sortable="true" title="Kuota" />--%>
                                                        <display:column property="stTarif" sortable="true" title="Tarif" />

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
    window.listPosisi = function(branch, divisi){
        var branch = document.getElementById("branchId").value;
        var divisi = document.getElementById("departmentId").value;
        $('#positionId').empty();
        PositionAction.searchPosition2(branch, divisi, function(listdata){
            $.each(listdata, function (i, item) {
                $('#positionId').append($("<option></option>")
                        .attr("value",item.positionId)
                        .text(item.positionName));
            });
        });
    }
</script>

