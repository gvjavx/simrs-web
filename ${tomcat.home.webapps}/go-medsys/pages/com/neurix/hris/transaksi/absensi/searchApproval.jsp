<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AbsensiAction.js"/>'></script>
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
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };

        function cancelBtn2() {
            $('#view_dialog_function2').dialog('close');
        };

        $('body').on('hidden.bs.modal', '.modal', function () {
            $(this).removeData('bs.modal');
        });

        function showLoadingDialog(){
            $('#myModalLoading').modal('show');
        }

        function showAlert(){
            var verif = document.getElementById('verif').value;
            var erVerif = document.getElementById('erVerif').value;
            if(verif !=  ""){
                document.getElementById('succesAlert').style.display = 'block';
                var sc = document.getElementById('succesAlert').value;
                if ( sc != ""){
                    sc = "";
                }
                $("#succesAlert").fadeTo(1000, 500).slideUp(500, function(){
                    $("succesAlert").slideUp(500);
                });
            }else if(erVerif != "") {
                document.getElementById('errorAlert').style.display = 'block';
                erVerif = null;
                $("#errorAlert").fadeTo(1000, 500).slideUp(500, function(){
                    $("errorAlert").slideUp(500);
                });
            }
        }

        function link(){
            window.location.href="<s:url action='initForm_absensi'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" onload="showAlert()">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Absensi
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Approval Absensi</h3>
                    </div>

                    <form role="form" method="post" id="searchForm" action="viewNotifikasi_notifikasi.action?tipeNotif=TN33">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

                            <div class="form-group">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Absensi Id </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="AbsensiId" name="Absensi.AbsensiId" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>NIP </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="personId" name="Absensi.nip" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                        </td>
                                    </tr>
                                </table>
                                <br>

                            </div>
                            <div class="box-footer">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"
                                                       onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                                <i class="fa fa-search"></i>
                                                Search
                                            </sj:submit>
                                        </td>
                                        <td>

                                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="http://localhost:8080/hris/notifikasi/viewNotifikasi_notifikasi.action?tipeNotif=TN88"/>'">
                                                <i class="fa fa-repeat"></i> Reset
                                            </button>
                                        </td>

                                    </tr>
                                </table>
                            </div>
                            <center>
                                <table id="showdata" width="50%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu_absensi" openTopics="showDialogMenu" modal="true"
                                                       height="600" width="600" autoOpen="false"
                                                       title="Absensi">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResultABS" value="#session.listOfResultAbsensi" scope="request" />
                                            <display:table name="listOfResultABS" class="tableAbsensi table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_Absensi.action" id="row" pagesize="20" style="font-size:10">
                                                <display:column property="absensiPegawaiId" sortable="true" title="Absensi Id"/>
                                                <display:column property="nip" sortable="true" title="NIP"/>
                                                <display:column property="nama" sortable="true" title="Nama"/>
                                                <display:column property="positionName" sortable="true" title="Jabatan"/>
                                                <display:column property="divisi" sortable="true" title="Bidang"/>
                                                <display:column property="stTanggal" sortable="true" title="Tanggal"/>
                                                <display:column media="html" title="Approve">
                                                    <s:if test="#attr.row.AbsensiApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:elseif test="#attr.row.notApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                    </s:elseif>
                                                    <s:else>
                                                        <a href="javascript:;"  data="<s:property value="%{#attr.row.absensiPegawaiId}"/>" href="javascript:;" class="item-edit">
                                                            <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                        </a>
                                                    </s:else>
                                                </display:column>
                                                <display:setProperty name="paging.banner.item_name">Approval</display:setProperty>
                                                <display:setProperty name="paging.banner.items_name">Approval</display:setProperty>
                                                <display:setProperty name="export.excel.filename">Approval.xls</display:setProperty>
                                                <display:setProperty name="export.csv.filename">Approval.csv</display:setProperty>
                                                <display:setProperty name="export.pdf.filename">Approval.pdf</display:setProperty>
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                        </div>

                    </form>

                </div>
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
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1000px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve Absensi</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-3" >Absensi ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="AbsensiId1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="Nip1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="Nama1" name="nip">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="Unit1" name="Absensi.unitId" readonly="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                         </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Bidang : </label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="divisiId1" name="Absensi.divisiId" disabled="true"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid1" disabled="true" name="Absensi.positionId" required="false" readonly="true"
                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="tanggal1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea class="form-control nip" rows="3" id="Keterangan1" readonly="true" name="nip"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="btnApprove" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
                <a id="btnNotApprove" data="not" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-edit-not-approve" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Keterangan Not Approve Absensi</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormKeterangan">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan Not Approve : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" class="form-control" id="keterangan" name="nip"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="btnNotApprove1" type="button" class="btn btn-default btn-danger"><i class="fa fa-check"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
<script type="application/javascript">
    $('#btnApprove').click(function(){
        var who = $('#myForm').attr('action');
        var ijinId = document.getElementById("AbsensiId1").value;
        var nip=document.getElementById("Nip1").value;
        if (confirm('Are you sure you want to save this Record?')) {
            dwr.engine.setAsync(false);
            AbsensiAction.saveApprove(ijinId, "Y",who,nip, function() {
                alert('Data Successfully Updated');
                $('#modal-edit').modal('hide');
                $('#myForm')[0].reset();
                location.reload();
            });
        }
    });
    $('#btnNotApprove').on('click', function() {
        $('#modal-edit-not-approve').find('.modal-title').text('Keterangan Not Approve Absensi');
        $('#modal-edit-not-approve').modal('show');
    });
    $('#btnNotApprove1').click(function(){
        var who = "atasan";
        var AbsensiId = document.getElementById("AbsensiId1").value;
        var note = document.getElementById("keterangan").value;
        var nip=document.getElementById("Nip1").value;
        if(note != ''){
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);

                AbsensiAction.saveApprove(AbsensiId, note, who,nip, function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    location.reload();
                });
            }
        }else{
            alert('Silahkan isi keterangan Not Approve !');
        }
    });
    $('.tableAbsensi').on('click', '.item-edit', function() {
        var absensiId = $(this).attr('data');
        AbsensiAction.approveAtasan(absensiId, function(listdata) {
            $.each(listdata, function (i, item) {
                $('#AbsensiId1').val(absensiId);
                $('#Nama1').val(item.nama);
                $('#Nip1').val(item.nip);
                $('#tanggal1').val(item.stTanggal);
                $('#divisiId1').val(item.divisiId).change();
                $('#positionid1').val(item.posisiId).change();
                $('#Unit1').val(item.branchId).change();
                $('#Keterangan1').val(item.keterangan).change();
            });
        });
        $('#modal-edit').find('.modal-title').text('Approve Absensi');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
    });
</script>


