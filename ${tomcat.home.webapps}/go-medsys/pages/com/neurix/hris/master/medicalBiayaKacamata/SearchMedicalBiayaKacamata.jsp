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
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalBiayaKacamataAction.js"/>'></script>

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

                MedicalBiayaKacamataAction.saveApprove(id, function(listdata) {
                    alert('Update Golongan Berhasil');
                    $('#modal-approve').modal('hide');
                });
            });
        });

        function link(){
            window.location.href="<s:url action='initForm_medicalBiayaKacamata'/>";
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
            Medical Biaya Kacamata
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
                    <s:form id="golonganForm" method="post"  theme="simple" namespace="/medicalBiayaKacamata" action="search_medicalBiayaKacamata.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Biaya Kacamata Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="medicalBiayaKacamataId" name="medicalBiayaKacamata.biayaKacamataId" required="false" readonly="false" cssClass="form-control"/>
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
                                        <s:select list="#initComboBranch.listOfComboBranch" id="unitId" name="medicalBiayaKacamata.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Golongan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                                        <s:select list="#initComboTipe.listComboGolongan" id="golonganId" name="medicalBiayaKacamata.golonganId"
                                                  listKey="golonganId" listValue="golonganName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="medicalBiayaKacamata.flag"
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
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="golonganForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <s:url var="urlAdd" namespace="/medicalBiayaKacamata" action="add_medicalBiayaKacamata" escapeAmp="false">
                                        </s:url>
                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                            <i class="fa fa-plus"></i>
                                            Add Biaya Kacamata
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_medicalBiayaKacamata"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="60%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="400" width="500" autoOpen="false"
                                                   title="Biaya Kacamata">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfGolongan" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfGolongan" class="tableMedicalBiayaKacamata table tableData table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_medicalBiayaKacamata.action" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <s:url var="urlViewEdit" namespace="/medicalBiayaKacamata" action="edit_medicalBiayaKacamata" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.biayaKacamataId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewEdit}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_trash">
                                                </sj:a>
                                            </display:column>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/medicalBiayaKacamata" action="delete_medicalBiayaKacamata" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.biayaKacamataId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                </sj:a>
                                            </display:column>

                                            <display:column property="biayaKacamataId" sortable="true" title="ID" />
                                            <display:column property="branchName" sortable="true" title="Unit"  />
                                            <display:column property="golonganName" sortable="true" title="Golongan Name"  />
                                            <display:column property="strBiayaLensa" sortable="true" title="Biaya Lensa"  />
                                            <display:column property="strBiayaGagang" sortable="true" title="Biaya Gagang"  />

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
    $('.tableMedicalBiayaKacamata').on('click', '.item-approve', function(){

        var id = $(this).attr('data');
        var branchName = $(this).attr('namaBranch');

        $('#updateId').val(id);
        $('#labelApprove').text("Apakah anda ingin mengupdate seluruh golongan karyawan di " + branchName + "?");
        $('#modal-approve').find('.modal-title').text('');
        $('#modal-approve').modal('show');
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

