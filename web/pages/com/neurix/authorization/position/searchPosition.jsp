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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionBagianAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_position'/>";
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
            Posisi
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
                    <s:form id="posisiForm" method="post"  theme="simple" namespace="/admin/position" action="search_position.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Posisi Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="positionId" name="position.stPositionId" required="false" readonly="false" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Posisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="positionName" name="position.positionName" required="false" readonly="false" cssClass="form-control" />
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
                                        <s:select list="#session.listOfResultDepartment" id="departmentId" name="position.departmentId" onchange="listPosisiHistory(); cekBidangLain()"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <div id="namaBidangLain" class="form-group" style="display: none">
                                <label class="control-label col-sm-4">Nama Bidang Lain:</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="bidangLain">
                                </div>
                            </div>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Bagian :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                        <s:select list="#comboBagian.comboListOfPositionBagian" id="bagianId" name="position.bagianId"
                                                  listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]" onchange="cekPosisiLain()"
                                                  cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <div id="namaJabatanLain" class="form-group" style="display: none">
                                <label class="control-label col-sm-4">Nama Jabatan Lain: </label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="jabatanLain">
                                </div>
                            </div>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Kelompok Jabatan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboKelompok" namespace="/kelompokPosition" name="searchKelompok_kelompokPosition"/>
                                        <s:select list="#comboKelompok.comboListOfKelompokPosition" id="kelompokId" name="position.kelompokId"
                                                  listKey="kelompokId" listValue="kelompokName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="position.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                        </table>



                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="posisiForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/admin/position" action="add_position" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Posisi
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_position"/>'">
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
                                                   height="500" width="900" autoOpen="false"
                                                   title="Posisi ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfPosition" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfPosition" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag.action" export="true" id="row" pagesize="20" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/admin/position" action="edit_position" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.positionId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlViewDelete" namespace="/admin/position" action="delete_position" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.positionId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>
                                            <display:column property="positionId" sortable="true" title="Id"/>
                                            <display:column property="positionName" sortable="true" title="Posisi"/>
                                            <display:column property="departmentName" sortable="true" title="Bidang/Divisi"/>
                                            <display:column property="bagianName" sortable="true" title="Bagian"/>
                                            <display:column property="kelompokName" sortable="true" title="Kelompok Jabatan"/>
                                            <display:column property="kodering" sortable="true" title="Kodering"/>
                                            <display:column property="flag" sortable="true" title="flag"  />
                                            <display:column property="action" sortable="true" title="action"  />
                                            <display:column property="createdDate" sortable="true" title="Created date"  />
                                            <display:column property="createdWho" sortable="true" title="Created who"  />
                                            <display:column property="lastUpdate" sortable="true" title="Last update"  />
                                            <display:column property="lastUpdateWho" sortable="true" title="Last update who"  />
                                            <display:setProperty name="paging.banner.item_name">Position</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">Positions</display:setProperty>
                                            <display:setProperty name="export.excel.filename">Positions.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">Positions.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">Positions.pdf</display:setProperty>
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
    window.listPosisiHistory = function (branch, divisi) {
//        var branch = document.getElementById("branch1").value;
        var divisi = document.getElementById("departmentId").value;
        console.log("Test divisi "+divisi);
        $('#bagianId').empty();
        $('#bagianId').append($("<option></option>")
                .attr("value", '')
                .text(''));
        console.log("Test");
        PositionBagianAction.searchPositionBagian(divisi, function (listdata) {
            $.each(listdata, function (i, item) {
                $('#bagianId').append($("<option></option>")
                        .attr("value", item.bagianId)
                        .text(item.bagianName));
            });
        });
    };
    window.cekBidangLain = function(){
        var divisi = document.getElementById("departmentId").value;
        console.log("Test2");
        if (divisi=='0'){
            $('#bidangLain').val("");
            $('#namaBidangLain').show();
        }
        else{
            $('#bidangLain').val("");
            $('#namaBidangLain').hide();
        }
    };
    window.cekPosisiLain = function(){
        var position = document.getElementById("bagianId").value;
        if (position=='0'){
            $('#jabatanLain').val("");
            $('#namaJabatanLain').show();
        }
        else{
            $('#jabatanLain').val("");
            $('#namaJabatanLain').hide();
        }
    };
</script>

