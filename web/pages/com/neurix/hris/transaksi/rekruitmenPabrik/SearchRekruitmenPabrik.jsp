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
            window.location.href="<s:url action='initForm_alat'/>";
        }


    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenPabrikAction.js"/>'></script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Rekruitmen Pabrik
            <small>e-HEALTH</small>
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
                                <s:form id="rekruitmenPabrikForm" method="post"  theme="simple" namespace="/rekruitmenPabrik" action="search_rekruitmenPabrik.action" cssClass="form-horizontal">

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
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Rekruitmen Pabrik Id :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield  id="rekruitmenPabrikId" name="rekruitmenPabrik.rekruitmenPabrikId" required="false" readonly="false" cssClass="form-control"/>
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
                                                    <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="rekruitmenPabrik.branchId"
                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                </table>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Bagian :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagianUnit_strukturJabatan"/>
                                                    <s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagian" required="true"
                                                              listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="rekruitmenPabrik.bagianId" />
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>MT :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="comboMasaTanam" namespace="/masaTanam" name="searchMasaTanam_masaTanam"/>
                                                    <s:select list="#comboMasaTanam.listComboMasaTanam" id="masaTanam" name="rekruitmenPabrik.mt"
                                                              listKey="masaTanamId" listValue="masaTanamName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="rekruitmenPabrikForm" id="search" name="search"
                                                               onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <s:if test="isAdmin()">
                                                <td>
                                                    <button type="button" class="btn btn-success" id="btn_add_kuota">
                                                        <i class="fa fa-plus"></i> Add Kuota
                                                    </button>
                                                </td>
                                                </s:if>
                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_rekruitmenPabrik"/>'">
                                                        <i class="fa fa-refresh"></i> Reset
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>

                                    <br>
                                    <br>
                                    <center>
                                        <table id="showdata" width="70%">
                                            <tr>
                                                <td align="center">
                                                    <style>
                                                        th {text-align: center}
                                                    </style>
                                                    <sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading" closeTopics="closeDialogLoading" modal="true"
                                                               resizable="false"
                                                               height="350" width="600" autoOpen="false" title="Loading ...">
                                                        Please don't close this window, server is processing your request ...
                                                        </br>
                                                        </br>
                                                        </br>
                                                        <center>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                                        </center>
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu_view" openTopics="showDialogMenuView1" modal="true"
                                                               height="600" width="1000" autoOpen="false"
                                                               title="Rekruitmen ">
                                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                               height="800" width="1000" autoOpen="false"
                                                               title="Rekruitmen ">
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenuView" modal="true"
                                                               height="700" width="800" autoOpen="false"
                                                               title="Rekruitmen ">
                                                    </sj:dialog>
                                                    <s:set name="listOfRekruitmenPabrik" value="#session.listOfResult" scope="request" />
                                                    <display:table name="listOfRekruitmenPabrik" class=" tableRekruitmenPabrik table table-condensed table-striped table-hover"
                                                                   requestURI="paging_displaytag_rekruitmenPabrik.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                        <display:column media="html" title="Cetak Kontrak" style="text-align:center">
                                                            <s:url var="urlCetakKontrak" namespace="/rekruitmenPabrik" action="cetakKontrak_rekruitmenPabrik" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.rekruitmenPabrikId" /></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogMenuView1" href="%{urlCetakKontrak}">
                                                                <img border="0" src="<s:url value="/pages/images/icon_printer_lama.ico"/>" name="icon_trash">
                                                            </sj:a>
                                                        </display:column>
                                                        <display:column media="html" title="Approval SDM" style="text-align:center">
                                                            <s:if test="#attr.row.cekAdmin">
                                                                <s:if test="#attr.row.approvalSdm">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:else>
                                                                    <s:url var="urlApproveSdm" namespace="/rekruitmenPabrik" action="approveSdm_rekruitmenPabrik" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.rekruitmenPabrikId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlApproveSdm}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon approval">
                                                                    </s:a>
                                                                </s:else>
                                                            </s:if>
                                                            <s:elseif test="#attr.row.approvalSdm">
                                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                            </s:elseif>
                                                        </display:column>
                                                        <display:column media="html" title="Approval GM" style="text-align:center">
                                                            <s:if test="#attr.row.approvalGm">
                                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                            </s:if>
                                                        </display:column>
                                                        <display:column media="html" title="Edit" style="text-align:center">
                                                            <s:if test="#attr.row.close">
                                                            </s:if>
                                                            <s:else>
                                                                <s:url var="urlEdit" namespace="/rekruitmenPabrik" action="edit_rekruitmenPabrik" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.rekruitmenPabrikId"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <s:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                </s:a>
                                                            </s:else>
                                                        </display:column>
                                                        <display:column media="html" title="Close" style="text-align:center">
                                                            <s:if test="#attr.row.cekAdmin">
                                                                <s:if test="#attr.row.close">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:else>
                                                                    <s:url var="urlViewDelete" namespace="/rekruitmenPabrik" action="closed_rekruitmenPabrik" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.rekruitmenPabrikId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenuView1" href="%{urlViewDelete}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_view">
                                                                    </sj:a>
                                                                </s:else>
                                                            </s:if>
                                                        </display:column>
                                                        <display:column media="html" title="View" style="text-align:center">
                                                            <s:url var="urlViewDelete" namespace="/rekruitmenPabrik" action="view_rekruitmenPabrik" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.rekruitmenPabrikId" /></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogMenuView1" href="%{urlViewDelete}">
                                                                <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_view">
                                                            </sj:a>
                                                        </display:column>
                                                        <display:column property="rekruitmenPabrikId" sortable="true" title="rekruitmen Pabrik Id" />
                                                        <display:column property="unitName" sortable="true" title="Unit"  style="text-align:center"/>
                                                        <display:column property="bagianName" sortable="true" title="Bagian"  style="text-align:center"/>
                                                        <display:column property="kuota" sortable="true" title="Kuota"  style="text-align:center"/>
                                                        <display:column property="sisaKuota" sortable="true" title="Sisa Kuota"  style="text-align:center"/>
                                                        <display:column property="mt" sortable="true" title="Masa Tanam"  style="text-align:center"/>
                                                        <display:column media="html" title="Add Pegawai" style="text-align:center">
                                                            <s:if test="#attr.row.close">
                                                            </s:if>
                                                            <s:else>
                                                                <s:url var="urlEdit" namespace="/rekruitmenPabrik" action="add_rekruitmenPabrik" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.rekruitmenPabrikId"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <s:a action="add_rekruitmenPabrik.action">
                                                                    <s:param name="id"><s:property value="#attr.row.rekruitmenPabrikId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    <img border="0" src="<s:url value="/pages/images/edit_member.png"/>" name="icon_edit">
                                                                </s:a>
                                                            </s:else>
                                                        </display:column>
                                                        <display:setProperty name="paging.banner.item_name">RekruitmenPabrik</display:setProperty>
                                                        <display:setProperty name="paging.banner.items_name">RekruitmenPabrik</display:setProperty>
                                                        <display:setProperty name="export.excel.filename">RekruitmenPabrik.xls</display:setProperty>
                                                        <display:setProperty name="export.csv.filename">RekruitmenPabrik.csv</display:setProperty>
                                                        <display:setProperty name="export.pdf.filename">RekruitmenPabrik.pdf</display:setProperty>
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
    <div id="modal-add-kuota" class="modal fade" role="dialog">
        <div class="modal-dialog modal-md">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Add Kuota</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="myFormKuota">
                        <div class="form-group">
                            <label class="control-label col-sm-3" >Masa Tanam : </label>
                            <div class="col-sm-6">
                                <s:action id="comboMasaTanam" namespace="/masaTanam" name="searchMasaTanam_masaTanam"/>
                                <s:select list="#comboMasaTanam.listComboMasaTanam" id="masaTanam4"
                                          listKey="masaTanamId" listValue="masaTanamName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" >Unit : </label>
                            <div class="col-sm-6">
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId4"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" >Bagian : </label>
                            <div class="col-sm-6">
                                <s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagianUnit_strukturJabatan"/>
                                <s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagianId4" required="true"
                                          listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="rekruitmenPabrik.bagianId" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" >Kuota : </label>
                            <div class="col-sm-6">
                                <s:textfield  id="kuota4" required="false" readonly="false" cssClass="form-control"/>
                            </div>
                        </div>
                        <br>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="btnSaveKuota" type="button" class="btn btn-default btn-success">Save</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
<script>
    $(document).ready(function(){
        $('#btn_add_kuota').click(function(){
            $('#modal-add-kuota').find('.modal-title').text('Add Kuota');
            $('#modal-add-kuota').modal('show');
            $('#myFormKuota').attr('action', 'addKuota');
        });
        $('#btnSaveKuota').click(function(){
            var masatanam=$('#masaTanam4').val();
            var unit =$('#branchId4').val();
            var bagian=$('#bagianId4').val();
            var kuota =$('#kuota4').val();
            if(masatanam!=""&&unit!=""&&bagian!=""&&kuota!="") {
                RekruitmenPabrikAction.saveKuota(masatanam, unit, bagian, kuota, function (listdata) {
                    alert('Data Successfully Saved');
                    $('#modal-add-kuota').modal('hide');
                    location.reload();
                });
            }else if (masatanam==""){
                alert("Masa Tanam Belum Dipilih.");
            }else if (unit==""){
                alert("Unit Belum Dipilih.");
            }else if (bagian==""){
                alert("Bagian Belum Dipilih.");
            }else if (kuota==""){
                alert("Kuota Belum Dipilih.");
            }
        })
    })
</script>
