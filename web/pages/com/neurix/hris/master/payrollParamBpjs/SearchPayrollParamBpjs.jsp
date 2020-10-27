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
            window.location.href="<s:url action='initForm_payrollParamBpjs'/>";
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
            Payroll Param Bpjs
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Payroll Param Bpjs</h3>
                    </div>
                    <div class="box-body">
        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="payrollParamBpjsForm" method="post"  theme="simple"
                            namespace="/payrollParamBpjs" action="search_payrollParamBpjs.action"
                            cssClass="form-horizontal">

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
                                    <label class="control-label"><small>ID Param Bpjs :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="idPtkpSearch" name="payrollParamBpjs.idPtkp"
                                                      required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Gaji Pokok :</small></label>
                                </td>
                                <td>
                                    <table>

                                            <s:select list="#{'Y':'Yes', 'N':'No'}"
                                                      id="statusKeluargaSearch" name="payrollParamBpjs.statusKeluarga"
                                                      headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Jumlah Tanggungan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield type="number" min="0" id="jumlahTanggunganSearch"
                                                     name="payrollParamBpjs.jumlahTanggungan" required="true"
                                                     cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="payrollParamBpjs.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="payrollParamBpjsForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/payrollParamBpjs" action="add_payrollParamBpjs"
                                               escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Payroll PTKP
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger"
                                                onclick="window.location.href='<s:url action="initForm_payrollParamBpjs"/>'">
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
                                                   height="400" width="550" autoOpen="false"
                                                   title="Payroll PTKP ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfsearchPayrollParamBpjs" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfsearchPayrollParamBpjs" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_payrollParamBpjs.action" export="true"
                                                       id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="View">
                                                <s:url var="urlView" namespace="/payrollParamBpjs" action="view_payrollParamBpjs" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.idPtkp"/></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlView}">
                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_view">
                                                </sj:a>
                                            </display:column>
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/payrollParamBpjs" action="edit_payrollParamBpjs" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.idPtkp"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <%--<display:column media="html" title="Delete" style="text-align:center;font-size:9">--%>
                                                <%--<s:url var="urlViewDelete" namespace="/payrollParamBpjs" action="delete_payrollParamBpjs" escapeAmp="false">--%>
                                                    <%--<s:param name="id"><s:property value="#attr.row.idPtkp" /></s:param>--%>
                                                    <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                <%--</s:url>--%>
                                                <%--<sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">--%>
                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">--%>
                                                <%--</sj:a>--%>

                                            <%--</display:column>--%>
                                            <display:column property="idPtkp" sortable="true" title="ID PTKP" />
                                            <display:column property="statusKeluarga" sortable="true" title="Status Keluarga"  />
                                            <display:column property="jumlahTanggungan" sortable="true" title="Jumlah Tanggungan"  />
                                            <%--<display:column property="nilai" sortable="true" title="Nilai"  />--%>
                                            <display:column title="Nilai">
                                                <script>
                                                    var nilai = '<s:property value="#attr.row.nilai" />';
                                                    document.write(formatRupiahAtas(nilai));
                                                </script>
                                            </display:column>
                                            <display:column property="createdWho" sortable="true" title="Created Who"/>
                                            <display:column property="lastUpdate" sortable="true" title="Last Update"/>
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

