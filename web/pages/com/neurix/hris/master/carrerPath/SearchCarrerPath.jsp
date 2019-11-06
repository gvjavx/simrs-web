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

        function checkDec(el){
            var ex = /^[0-9]+\.?[0-9]*$/;
            if(ex.test(el.value)==false){
                el.value = el.value.substring(0,el.value.length - 1);
            }
        }

        function link(){
            window.location.href="<s:url action='search_carrerPath'/>";
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
            Master Carrer Path
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
                    <s:form id="carrerPathForm" method="post"  theme="simple" namespace="/carrerPath" action="search_carrerPath.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Carrer Path Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="carrerPathId" name="carrerPath.carrerPathId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="carrerPath.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Bidang :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="carrerPath.divisiId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Bagian :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                        <s:select list="#comboBagian.comboListOfPositionBagian" id="bagianId" name="carrerPath.bagianId"
                                                  listKey="bagianId" listValue="bagianName" headerKey="" headerValue=""
                                                  cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Jabatan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="carrerPath.jabatanId"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Jurusan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboJurusan" namespace="/studyJurusan" name="searchStudyJurusan_studyJurusan"/>
                                        <s:select list="#comboJurusan.comboListOfStudyJurusan" id="jurusanId" name="carrerPath.jurusanId"
                                                  listKey="jurusanId" listValue="jurusanName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="carrerPath.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="carrerPathForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <a href="add_carrerPath.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Carrer Path</a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_carrerPath"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="95%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="550" width="600" autoOpen="false"
                                                   title="Carrer Path">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfCarrerPath" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfCarrerPath" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_carrerPath.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/carrerPath" action="edit_carrerPath" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.carrerPathId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/carrerPath" action="delete_carrerPath" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.carrerPathId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                </sj:a>

                                            </display:column>
                                            <display:column property="carrerPathId" sortable="true" title="Carrer Id" />
                                            <display:column property="branchName" sortable="true" title="Unit"  />
                                            <display:column property="jabatanName" sortable="true" title="Jabatan"  />
                                            <display:column property="golonganName" sortable="true" title="Golongan"  />
                                            <display:column property="kelompokPositionName" sortable="true" title="Kelompok Posisi"  />
                                            <display:column property="kelompokPositionName" sortable="true" title="Kelompok Detail"  />
                                            <display:column property="thMinKerja" sortable="true" title="Min Kerja (Th)"  />
                                            <display:column property="divisiName" sortable="true" title="Bidang"  />
                                            <display:column property="thMinBidang" sortable="true" title="Min Bidang (Th)"  />
                                            <display:column property="bagianName" sortable="true" title="Bagian"  />
                                            <display:column property="thMinBagian" sortable="true" title="Min Bagian (Th)"  />
                                            <display:column property="pendidikan" sortable="true" title="Pendidikan"  />
                                            <display:column property="jurusanName" sortable="true" title="Jurusan"  />

                                            <%--<display:column property="flag" sortable="true" title="Flag" />
                                            <display:column property="createdWho" sortable="true" title="CreatedWho"/>--%>
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

