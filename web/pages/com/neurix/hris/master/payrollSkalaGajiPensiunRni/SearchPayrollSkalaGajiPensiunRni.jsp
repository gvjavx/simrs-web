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
            window.location.href="<s:url action='initForm_payrollSkalaGajiPensiunRni'/>";
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
            Payroll Skala Gaji Pensiun
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="payrollSkalaGajiPensiunRniForm" method="post"  theme="simple" namespace="/payrollSkalaGajiPensiunRni" action="searchPayrollSkalaGajiPensiun_payrollSkalaGajiPensiunRni.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Skala Gaji ID :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="skalaGajiId" name="payrollSkalaGajiPensiunRni.skalaGajiPensiunId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Golongan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboGolongan" namespace="/golongan" name="initComboGolonganDapen_golongan"/>
                                        <s:select cssClass="form-control" list="#comboGolongan.listComboGolonganDapen" id="golonganDapenId" name="payrollSkalaGajiPensiunRni.golonganId" required="true"
                                                  listKey="golonganDapenId" listValue="golonganDapenName" headerKey="" headerValue="" />
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Tipe Dapen :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboGolongan" namespace="/payrollDanaPensiun" name="searchPayrollDanaPensiun_payrollDanaPensiun"/>
                                        <s:select cssClass="form-control" list="#comboGolongan.listComboPayrollDanaPensiun" id="tipeDapenId" name="payrollSkalaGajiPensiunRni.tipeDapenId"
                                                  listKey="danaPensiunId" listValue="danaPensiun" headerKey="" headerValue="" />
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="payrollSkalaGajiPensiunRni.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="payrollSkalaGajiPensiunRniForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/payrollSkalaGajiPensiunRni" action="add_payrollSkalaGajiPensiunRni" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Dana Pensiun
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_payrollSkalaGajiPensiunRni"/>'">
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
                                                   height="400" width="550" autoOpen="false"
                                                   title="Payroll Skala Gaji Pensiun">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listComboPayrollSkalaGajiPensiun" value="#session.listOfResult" scope="request" />
                                        <display:table name="listComboPayrollSkalaGajiPensiun" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_payrollSkalaGajiPensiunRni.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/payrollSkalaGajiPensiunRni" action="edit_payrollSkalaGajiPensiunRni" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.skalaGajiPensiunId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlViewDelete" namespace="/payrollSkalaGajiPensiunRni" action="delete_payrollSkalaGajiPensiunRni" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.skalaGajiPensiunId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>
                                            <display:column property="skalaGajiPensiunId" sortable="true" title="Skala Gaji ID" />
                                            <display:column property="tipeDapenName" sortable="true" title="Tipe Dapen"  />
                                            <display:column property="golonganName" sortable="true" title="Golongan"  />
                                            <display:column property="poin" sortable="true" title="Masa Golongan"  />
                                            <display:column property="stNilai" sortable="true" title="Nilai"  />
                                            <display:column property="flag" sortable="true" title="flag"  />
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

