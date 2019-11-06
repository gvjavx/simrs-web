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
    <script type='text/javascript' src='<s:url value="/dwr/interface/UpdateGolonganAction.js"/>'></script>

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
        $(document).ready(function() {
            $('#btnApprove').click(function(){
                var id = document.getElementById("updateId").value;

                UpdateGolonganAction.saveApprove(id, function(listdata) {
                    alert('Update Golongan Berhasil');
                    location.reload();
                    $('#modal-approve').modal('hide');
                });
            });
        });

        function link(){
            window.location.href="<s:url action='initForm_updateGolongan'/>";
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
            Update Golongan
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
                    <s:form id="golonganForm" method="post"  theme="simple" namespace="/updateGolongan" action="search_updateGolongan.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Update Golongan Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="golonganId" name="updateGolongan.updateGolonganId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tahun :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}" id="flag"
                                                  name="updateGolongan.periode" headerKey="" headerValue="" cssClass="form-control" />
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
                                        <s:select list="#initComboBranch.listOfComboBranch" id="unitId" name="updateGolongan.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>


                            <tr>
                                <td>
                                    <label class="control-label"><small>Approved :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Belum Approve', 'Y':'Sudah Approve'}" id="approve" name="updateGolongan.approvalFlag"
                                                  headerKey="" headerValue="" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>

                        </table>



                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="golonganForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/updateGolongan" action="add_updateGolongan" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Master Update
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_updateGolongan"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="30%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="300" width="500" autoOpen="false"
                                                   title="Update Golongan">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfGolongan" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfGolongan" class="tableUpdateGolongan table tableData table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_golongan.action" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlEdit" namespace="/updateGolongan" action="edit_updateGolongan" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.updateGolonganId"/></s:param>
                                                        <s:param name="periode"><s:property value="#attr.row.periode"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{urlEdit}" id="hrefEdit">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </s:a>
                                                </s:if>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/golongan" action="delete_golongan" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.updateGolonganId" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                </sj:a>
                                            </display:column>

                                            <display:column media="html" title="Approve" style="text-align:center;font-size:9">
                                                <s:if test="#attr.row.approve">
                                                    <a href="javascript:;"
                                                       data="<s:property value="%{#attr.row.updateGolonganId}"/>"
                                                       namaBranch="<s:property value="%{#attr.row.branchName}"/>"
                                                       href="javascript:;" class="item-approve">
                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                    </a>
                                                </s:if>
                                                <s:else>
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                </s:else>
                                            </display:column>

                                            <display:column media="html" title="Print" style="text-align:center;font-size:9">
                                                <a href="javascript:;" draftBulan="<s:property value="%{#attr.row.bulan}"/>"
                                                   id="<s:property value="%{#attr.row.updateGolonganId}"/>"
                                                   periode="<s:property value="%{#attr.row.periode}"/>"
                                                   class="item-print">
                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_lama.ico"/>" name="icon_edit">
                                                </a
                                            </display:column>

                                            <display:column property="updateGolonganId" sortable="true" title="ID" />
                                            <display:column property="periode" sortable="true" title="Periode"  />
                                            <display:column property="branchName" sortable="true" title="Unit"  />

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

<script>
    $('.tableUpdateGolongan').on('click', '.item-approve', function(){

        var id = $(this).attr('data');
        var branchName = $(this).attr('namaBranch');

        $('#updateId').val(id);
        $('#labelApprove').text("Apakah anda ingin mengupdate seluruh golongan karyawan di " + branchName + "?");
        $('#modal-approve').find('.modal-title').text('');
        $('#modal-approve').modal('show');
    });

    $('.tableUpdateGolongan').on('click', '.item-print', function(){

        var id = $(this).attr('id');
        var periode = $(this).attr('periode');

        window.location.href = 'printGolongan_updateGolongan?id='+id+'&periode='+periode;
    });
</script>

<div id="modal-approve" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width:650px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve SPPD</h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" id="formApproveAtasan">
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-2" >Id : </label>
                        <div class="col-sm-4">
                            <input readonly type="text" class="form-control nip" id="updateId">
                        </div>
                    </div>
                </form>
                <label style="font-size: 17px" id="labelApprove"></label>
            </div>

            <div class="modal-footer">
                <a id="btnApprove" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>

