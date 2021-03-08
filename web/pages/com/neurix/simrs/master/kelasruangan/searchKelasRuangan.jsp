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
            window.location.href="<s:url action='initForm_kelasruangan'/>";
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
            Kelas Ruangan
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
                                <s:form id="kelasruanganForm" method="post"  theme="simple"
                                        namespace="/kelasruangan" action="search_kelasruangan.action" cssClass="form-horizontal">

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
                                            <td  width="40%">
                                                <label class="control-label"><small>ID Kelas Ruangan </small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px"
                                                                 id="id_kelasruangan" name="kelasRuangan.idKelasRuangan"
                                                                 readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Nama Kelas Ruangan </small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="nama_kelasruangan"
                                                                 name="kelasRuangan.namaKelasRuangan"  readonly="false"
                                                                 cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Divisi </small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="initComboPosition" namespace="/kelasruangan"
                                                              name="initComboPosition_kelasruangan"/>
                                                    <s:select list="#initComboPosition.listOfComboPositions"
                                                              id="positionId" name="kelasRuangan.positionId"
                                                              listKey="positionId" listValue="positionName"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control select2"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Flag </small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'N':'Non-Active'}" id="flag" name="kelasRuangan.flag"
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
                                                    <sj:submit cssStyle="margin-right: 5px" type="button"
                                                               cssClass="btn btn-primary" formIds="kelasruanganForm"
                                                               id="search" name="search"
                                                               onClickTopics="showDialogLoading"
                                                               onCompleteTopics="closeDialogLoading" >
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>
                                                    <s:url  var="urlAdd" namespace="/kelasruangan" action="add_kelasruangan"
                                                            escapeAmp="false">
                                                    </s:url>
                                                    <sj:a cssClass="btn btn-success" cssStyle="margin-right: 5px"
                                                          onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                        <i class="fa fa-plus"></i>
                                                    Add Kelas ruangan
                                                    </sj:a>
                                                </td>

                                                <td>
                                                    <button type="button" class="btn btn-danger"
                                                            cssStyle="margin-right: 5px" onclick="window.location.href='<s:url
                                                            action="initForm_kelasruangan"/>'">
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
                                                               height="250" width="600" autoOpen="false" title="Loading ...">
                                                        Please don't close this window, server is processing your request ...
                                                        </br>
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
                                                               height="350" width="600" autoOpen="false"
                                                               title="Ruangan ">
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenuView" modal="true"
                                                               height="570" width="700" autoOpen="false"
                                                               title="Rekruitmen ">
                                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                    </sj:dialog>
                                                    <s:set name="listOfKelas" value="#session.listOfResultRuanganKelas" scope="request" />
                                                    <display:table name="listOfKelas" class="tableRekruitmen table table-condensed table-striped table-hover"
                                                                   requestURI="paging_displaytag_kelasruangan.action" export="true"
                                                                   id="row" pagesize="10" style="font-size:12">
                                                        <%--<display:column property="calonPegawaiId" sortable="true" title="Cal Peg ID"  />--%>
                                                        <display:column media="html" title="Edit">
                                                            <s:if test='#attr.row.flag == "Y"'>
                                                                <s:url var="urlEdit" namespace="/kelasruangan" action="edit_kelasruangan" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.idKelasRuangan"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                </sj:a>
                                                            </s:if>
                                                        </display:column>

                                                        <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                            <s:if test='#attr.row.flag == "Y"'>
                                                                <s:url var="urlViewDelete" namespace="/kelasruangan" action="delete_kelasruangan" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.idKelasRuangan" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                </sj:a>
                                                            </s:if>

                                                        </display:column>
                                                        <display:column property="idKelasRuangan" sortable="true" title="Kelas Ruangan ID"/>
                                                        <display:column property="namaKelasRuangan" sortable="true" title="Nama Kelas"  />
                                                        <display:column property="divisiName" sortable="true" title="Divisi"/>
                                                        <display:column property="kategori" sortable="true" title="Kategori"/>
                                                        <%--<display:column property="kodering" sortable="true" title="kodering"  />--%>
                                                        <%--<display:column property="flag" sortable="true" title="flag"  />--%>
                                                        <%--<display:column property="action" sortable="true" title="action"  />--%>
                                                        <display:column property="stCreatedDate" sortable="true" title="Created date"  />
                                                        <display:column property="createdWho" sortable="true" title="Created who"  />
                                                        <display:column property="stLastUpdate" sortable="true" title="Last update"  />
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

