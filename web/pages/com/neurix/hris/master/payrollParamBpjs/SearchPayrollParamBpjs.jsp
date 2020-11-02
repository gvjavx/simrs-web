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
                                        <s:textfield  id="idsearch" name="payrollParamBpjs.payrollParamBpjsId"
                                                      required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>


                            <%--<tr>--%>
                                <%--<td>--%>
                                    <%--<label class="control-label"><small>Flag Gajipokok  :</small></label>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--<table>--%>
                                        <%--<s:select list="#{'N':'Non-Active'}" id="gapoksearch"--%>
                                                  <%--name="payrollParamBpjs.flagGapok"--%>
                                                  <%--headerKey="Y" headerValue="Active"--%>
                                                  <%--cssClass="form-control" />--%>
                                    <%--</table>--%>

                                <%--</td>--%>
                            <%--</tr>--%>

                            <%--<tr>--%>
                                <%--<td>--%>
                                    <%--<label class="control-label"><small>Flag sankhus :</small></label>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--<table>--%>
                                        <%--<s:select list="#{'N':'Non-Active'}" id="flagsankhus"--%>
                                                  <%--name="payrollParamBpjs.flagSankhus"--%>
                                                  <%--headerKey="Y" headerValue="Active" cssClass="form-control" />--%>
                                    <%--</table>--%>

                                <%--</td>--%>
                            <%--</tr>--%>

                            <%--<tr>--%>
                                <%--<td>--%>
                                    <%--<label class="control-label"><small>Flag Peralihan Gajipokok  :</small></label>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--<table>--%>
                                        <%--<s:select list="#{'N':'Non-Active'}" id="pgapoksearch"--%>
                                                  <%--name="payrollParamBpjs.flagPeralihanGapok"--%>
                                                  <%--headerKey="Y" headerValue="Active"--%>
                                                  <%--cssClass="form-control" />--%>
                                    <%--</table>--%>

                                <%--</td>--%>
                            <%--</tr>--%>

                            <%--<tr>--%>
                                <%--<td>--%>
                                    <%--<label class="control-label"><small>Flag Peralihan Sankhus  :</small></label>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--<table>--%>
                                        <%--<s:select list="#{'N':'Non-Active'}" id="psankussearch"--%>
                                                  <%--name="payrollParamBpjs.flagPeralihanSankhus"--%>
                                                  <%--headerKey="Y" headerValue="Active"--%>
                                                  <%--cssClass="form-control" />--%>
                                    <%--</table>--%>

                                <%--</td>--%>
                            <%--</tr>--%>

                            <%--<tr>--%>
                                <%--<td>--%>
                                    <%--<label class="control-label"><small>Flag Peralihan Tunjangan  :</small></label>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--<table>--%>
                                        <%--<s:select list="#{'N':'Non-Active'}" id="ptunjangansearch"--%>
                                                  <%--name="payrollParamBpjs.flagPeralihanTunjangan"--%>
                                                  <%--headerKey="Y" headerValue="Active"--%>
                                                  <%--cssClass="form-control" />--%>
                                    <%--</table>--%>

                                <%--</td>--%>
                            <%--</tr>--%>

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
                                        <sj:submit type="button" cssClass="btn btn-primary"
                                                   formIds="payrollParamBpjsForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>

                                    <td>
                                        <button type="button" class="btn btn-danger"
                                                onclick="window.location.href='<s:url
                                                        action="initForm_payrollParamBpjs"/>'">
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
                                            <center><img border="0" src="<s:url
                                            value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfsearchPayrollParamBpjs" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfsearchPayrollParamBpjs" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_payrollParamBpjs.action" export="true"
                                                       id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="View">
                                                <s:url var="urlView" namespace="/payrollParamBpjs" action="view_payrollParamBpjs"
                                                       escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.idPtkp"/></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlView}">
                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_view">
                                                </sj:a>
                                            </display:column>
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/payrollParamBpjs" action="edit_payrollParamBpjs"
                                                           escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.payrollParamBpjsId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>


                                            <display:column property="payrollParamBpjsId" sortable="true" title="ID Param Bpjs" />
                                            <display:column property="flagGapok" sortable="true" title="Gaji Pokok" />
                                            <display:column property="flagSankhus" sortable="true" title="Sankus" />
                                            <display:column property="flagPeralihanGapok" sortable="true" title="Peralihan Gaji Pokok" />
                                            <display:column property="flagPeralihanSankhus" sortable="true" title="Peralihan Sankus" />
                                            <display:column property="flagPeralihanTunjangan" sortable="true" title="Peralihan Tunjangan" />

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

