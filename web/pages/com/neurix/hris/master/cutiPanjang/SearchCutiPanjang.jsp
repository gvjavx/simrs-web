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
            window.location.href="<s:url action='initForm_cutiPanjang'/>";
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
            Cuti Panjang
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="cutiPanjangForm" method="post"  theme="simple" namespace="/cutiPanjang" action="search_cutiPanjang.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Cuti Panjang Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="cutiPanjangId" name="cutiPanjang.cutiPanjangId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Golongan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboGolongan" namespace="/golongan" name="initComboGolongan_golongan"/>
                                        <s:select list="#comboGolongan.listComboGolongan" id="golongan" name="cutiPanjang.golonganId"
                                                  listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="unitId12" name="cutiPanjang.branchId" required="true"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Tipe Hari :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'kerja':'kerja','kalender':'kalender'}" id="tipeHari" name="cutiPanjang.tipeHari"
                                                  headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="cutiPanjang.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="cutiPanjangForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/cutiPanjang" action="add_cutiPanjang" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Cuti Panjang
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_cutiPanjang"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="40%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="400" width="500" autoOpen="false"
                                                   title="CutiPanjang ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfCutiPanjang" value="#session.listOfResultCutiPanjang" scope="request" />
                                        <display:table name="listOfCutiPanjang" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_cutiPanjang.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/cutiPanjang" action="edit_cutiPanjang" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.cutiPanjangId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/cutiPanjang" action="delete_cutiPanjang" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.cutiPanjangId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                </sj:a>

                                            </display:column>
                                            <display:column property="cutiPanjangId" sortable="true" title="Cuti Panjang ID" />
                                            <display:column property="golonganId" sortable="true" title="Golongan"  />
                                            <display:column property="branchId" sortable="true" title="Branch"  />
                                            <display:column property="jumlahCuti" sortable="true" title="Jumlah Cuti"  />
                                            <display:column property="tipeHari" sortable="true" title="Tipe Kalender"  />

                                            <display:column property="flag" sortable="true" title="Flag" />
                                            <display:column property="createdWho" sortable="true" title="CreatedWho"/>
                                            <display:setProperty name="export.excel.filename">CutiPanjang.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">CutiPanjang.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">CutiPanjang.pdf</display:setProperty>
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

