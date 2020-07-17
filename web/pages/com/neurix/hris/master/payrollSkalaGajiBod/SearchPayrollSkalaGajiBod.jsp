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
            window.location.href="<s:url action='initForm_payrollSkalaGajiBod'/>";
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
            Payroll Skala Gaji BOD
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="payrollSkalaGajiBodForm" method="post"  theme="simple" namespace="/payrollSkalaGajiBod" action="search_payrollSkalaGajiBod.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Skala BOD Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="payrollSkalaGajiBodId" name="payrollSkalaGajiBod.payrollSkalaGajiBodId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Posisi :</small></label>
                                </td>
                                <td>
                                    <s:action id="comboPosition" namespace="/admin/user" name="initComboPositionBod_user"/>
                                    <s:select list="#comboPosition.listOfComboPositions" id="positionId" name="payrollSkalaGajiBod.positionId"
                                              listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"
                                              cssClass="form-control" />
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tahun :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahun"
                                                  name="payrollSkalaGajiBod.tahun" required="true" headerKey=""
                                                  headerValue="[Select one]"/>
                                    </table>
                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="payrollSkalaGajiBod.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="payrollSkalaGajiBodForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/payrollSkalaGajiBod" action="add_payrollSkalaGajiBod" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Skala Gaji BOD
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_payrollSkalaGajiBod"/>'">
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
                                        <%--<sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"--%>
                                                   <%--height="400" width="550" autoOpen="false"--%>
                                                   <%--title="Payroll Skala Gaji Pkwt">--%>
                                            <%--<center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>--%>
                                        <%--</sj:dialog>--%>

                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="500" width="650" autoOpen="false"
                                                   title="Payroll Skala Gaji BOD">
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

                                        <s:set name="listOfsearchPayrollSkalaGajiBod" value="#session.listOfResultBod" scope="request" />
                                        <display:table name="listOfsearchPayrollSkalaGajiBod" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_payrollSkalaGajiBod.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test='#attr.row.flag == "Y"'>
                                                    <s:url var="urlEdit" namespace="/payrollSkalaGajiBod" action="edit_payrollSkalaGajiBod" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.payrollSkalaGajiBodId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:if test='#attr.row.flag == "Y"'>
                                                    <s:url var="urlViewDelete" namespace="/payrollSkalaGajiBod" action="delete_payrollSkalaGajiBod" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.payrollSkalaGajiBodId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>
                                            <display:column property="payrollSkalaGajiBodId" sortable="true" title="Skala Gaji BOD ID" />
                                            <display:column property="positionName" sortable="true" title="Posisi"  />
                                            <display:column property="gajiBod" sortable="true" title="Gaji"  />
                                            <display:column property="tunjRumah" sortable="true" title="Tunj.Rumah"  />
                                            <display:column property="tunjTelekomunikasi" sortable="true" title="Tunj.Telekomunikasi"  />
                                            <display:column property="jumlahPengasilanBulan" sortable="true" title="Jum.Penghasilan"  />
                                            <display:column property="tahun" sortable="true" title="Tahun"  />
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

