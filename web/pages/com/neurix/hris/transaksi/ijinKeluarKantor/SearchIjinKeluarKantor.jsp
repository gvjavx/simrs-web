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
<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinKeluarAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinAction.js"/>'></script>
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
            window.location.href="<s:url action='initFormKantor_ijinKeluar'/>";
        }
        var unit = '<s:property value="CutiPegawai.unitId"/>'
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
            Ijin Keluar Kantor
            <small>HRIS</small>
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
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="ijinKeluarForm" method="post"  theme="simple" namespace="/ijinKeluar" action="searchKantor_ijinKeluar.action" cssClass=" form-horizontal">
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
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="ijinKeluar.unitId" required="true" listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Ijin Keluar Kantor Id :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="ijinKeluarId" name="ijinKeluar.ijinKeluarId" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>NIP :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="nip" name="ijinKeluar.nip" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <script type='text/javascript'>
                                                var functions, mapped;
                                                $('#nip').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};

                                                        var data = [];
                                                        var unit = $('#branchid').val();
                                                        if (unit!=""){
                                                            dwr.engine.setAsync(false);
                                                            IjinKeluarAction.initComboPersonil(query, unit, function (listdata) {
                                                                data = listdata;
                                                            });

                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.nip+" "+item.namaPegawai;
                                                                mapped[labelItem] = {id: item.nip, label: labelItem,nama:item.namaPegawai};
                                                                functions.push(labelItem);
                                                            });

                                                            process(functions);
                                                        } else {
                                                            alert("Unit is empty");
                                                            $('#nip').val("");
                                                        }
                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];
                                                        var namaMember = selectedObj.label;
                                                        document.getElementById("namaId").value = selectedObj.nama;
                                                        return selectedObj.id;
                                                    }
                                                });
                                            </script>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaId" name="ijinKeluar.namaPegawai" required="true" disabled="true" cssClass="form-control"/>
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
                                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="ijinKeluar.divisiId"
                                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tanggal Ijin :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <div class="input-group date">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampFrom" name="ijinKeluar.stTanggalAwal" size="7" cssClass="form-control pull-right"
                                                                         required="false" cssStyle=""/>
                                                            <div class="input-group-addon">
                                                                s/d
                                                            </div>
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampTo" name="ijinKeluar.stTanggalAkhir" size="7" cssClass="form-control pull-right"
                                                                         required="false" cssStyle=""/>
                                                        </div>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="ijinKeluarForm" id="search" name="search"
                                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <s:url var="urlAdd" namespace="/ijinKeluar" action="addKantor_ijinKeluar" escapeAmp="false">
                                                        </s:url>
                                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                            <i class="fa fa-plus"></i>
                                                            Add Ijin Keluar kantor
                                                        </sj:a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initFormKantor_ijinKeluar"/>'">
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
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="640" width="700" autoOpen="false"
                                                                   title="Ijin Keluar Kantor ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu_ijin_keluar" openTopics="showDialogMenuView" modal="true"
                                                                   height="570" width="600" autoOpen="false"
                                                                   title="Ijin Keluar Kantor">
                                                        </sj:dialog>
                                                        <s:set name="listOfIjinKeluarKantor" value="#session.listOfResultIjinKeluarKantor" scope="request" />
                                                        <display:table name="listOfIjinKeluarKantor" class="tableIjinKeluarKantor table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_kantor_ijinKeluar.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                            <display:column media="html" title="Batal">
                                                                <s:if test="#attr.row.cancel">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.notApprove">
                                                                </s:elseif>
                                                                <s:elseif test="#attr.row.notApproveSdm">
                                                                </s:elseif>
                                                                <s:elseif test="#attr.row.canCancel">
                                                                    <s:url var="urlCancel" namespace="/ijinKeluar" action="cancelIjinKeluarKantor_ijinKeluar" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                    </s:url>
                                                                    <sj:a onClickTopics="showDialogMenuView" href="%{urlCancel}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                    </sj:a>
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Cetak Surat Kembali" style="text-align:center">
                                                                <s:if test="#attr.row.cancel">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.ijinKeluarApprove">
                                                                    <s:url var="urlCetakSurat" namespace="/ijinKeluar" action="cetakSuratKembali_ijinKeluar" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.ijinKeluarId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlCetakSurat}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                                    </s:a>
                                                                </s:elseif>
                                                                <s:else>
                                                                </s:else>
                                                            </display:column>
                                                            <display:column media="html" title="Cetak Surat" style="text-align:center">
                                                                <s:if test="#attr.row.cancel">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.ijinKeluarApprove">
                                                                    <s:url var="urlCetakSurat" namespace="/ijinKeluar" action="cetakSurat_ijinKeluar" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.ijinKeluarId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlCetakSurat}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                                    </s:a>
                                                                </s:elseif>
                                                                <s:else>
                                                                </s:else>
                                                            </display:column>
                                                            <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                                <s:url var="urlViewDelete" namespace="/ijinKeluar" action="deleteKantor_ijinKeluar" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenuView" href="%{urlViewDelete}">
                                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                                </sj:a>
                                                            </display:column>
                                                            <display:column property="ijinKeluarId" sortable="true" title="Keluar Kantor Id"/>
                                                            <display:column property="nip" sortable="true" title="NIP"/>
                                                            <display:column property="namaPegawai" sortable="true" title="Nama"/>
                                                            <display:column property="divisiName" sortable="true" title="Bidang"/>
                                                            <display:column property="positionName" sortable="true" title="Posisi"/>
                                                            <display:column property="stTanggalAwal" sortable="true" title="Tanggal"/>
                                                            <display:column property="jamKeluar" sortable="true" title="Jam Keluar"/>
                                                            <display:column property="jamKembali" sortable="true" title="Jam Kembali"/>
                                                            <display:column media="html" title="Approval Atasan">
                                                                <s:if test="#attr.row.cancel">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.ijinKeluarApprove">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                                <s:else>
                                                                </s:else>
                                                            </display:column>
                                                            <display:setProperty name="paging.banner.item_name">IjinKeluarKantor</display:setProperty>
                                                            <display:setProperty name="paging.banner.items_name">IjinKeluarKantor</display:setProperty>
                                                            <display:setProperty name="export.excel.filename">IjinKeluarKantor.xls</display:setProperty>
                                                            <display:setProperty name="export.csv.filename">IjinKeluarKantor.csv</display:setProperty>
                                                            <display:setProperty name="export.pdf.filename">IjinKeluarKantor.pdf</display:setProperty>
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
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1000px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve Ijin Keluar Kantor</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-3" >Ijin Keluar Kantor ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="IjinKeluarId1" name="nip">
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
                            <input type="text" readonly class="form-control" id="Name321" name="nip">

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="Unit1" name="ijinKeluar.unitId" readonly="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jam Keluar : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="jamKeluar1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jam Kembali : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="jamKembali1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <s:textarea rows="4" id="keterangan1" name="ijinKeluar.keterangan" readonly="true" required="false" cssClass="form-control"/>
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
                <h4 class="modal-title">Keterangan Not Approve Ijin Keluar Kantor</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormKeterangan">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" class="form-control" id="keterangan" name="nip"></textarea>
                        </div>
                    </div>
                </form>
                <table style="width: 100%;" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a id="btnNotApprove1" type="button" class="btn btn-default btn-danger"><i class="fa fa-check"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<script>

    $('#btnApprove').click(function(){
        var who = $('#myForm').attr('action');
        var ijinId = document.getElementById("IjinKeluarId1").value;
        var nip=document.getElementById("Nip1").value;
        if (confirm('Are you sure you want to save this Record?')) {
            dwr.engine.setAsync(false);
            IjinKeluarAction.saveApprove(ijinId, "Y",who,nip, function(listdata) {
                alert('Data Successfully Updated');
                $('#modal-edit').modal('hide');
                $('#myForm')[0].reset();
                location.reload();
            });
        }
    });
    $('.tableIjinKeluarKantor').on('click', '.item-edit', function() {
        $('#modal-edit').find('.modal-title').text('Approve Ijin Atasan');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
        var IjinKeluarId = $(this).attr('data');
        var nama = $(this).attr('nama');
        var ijinid = $(this).attr('ijin');
        $('#IjinKeluarId1').val(IjinKeluarId);
        IjinKeluarAction.approveAtasan(IjinKeluarId, function (listdata) {
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalAwal);
                var myDate1 = new Date(item.tanggalAkhir);
                $('#Nip1').val(item.nip);
                $('#Name321').val(item.namaPegawai);
                $('#jamKeluar1').val(item.jamKeluar);
                $('#jamKembali1').val(item.jamKembali);
                $('#Unit1').val(item.unitId).change();
                $('#keterangan1').val(item.keterangan).change();
                if (item.ijinKeluarApproveStatus == true) {
                    $('#btnNotApprove').hide();
                    $('#btnApprove').hide();
                } else {
                    $('#btnNotApprove').show();
                    $('#btnApprove').show();
                }
            });
        });
    });
        $('#btnNotApprove').on('click', function() {

        });
        $('#btnNotApprove1').click(function(){
            $('#modal-edit-not-approve').find('.modal-title').text('Keterangan Not Approve Ijin Tidak Masuk').modal('show');
            var who = $('#myForm').attr('action');
            var IjinKeluarId = document.getElementById("IjinKeluarId1").value;
            var note = document.getElementById("keterangan").value;
            var nip=document.getElementById("Nip1").value;
            //alert(note);
            if(note != ''){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);

                    IjinKeluarAction.saveApprove(IjinKeluarId, note, who,nip, function(listdata) {
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
</script>
<script>
    $(document).ready(function() {
        $('#loginTimestampTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#loginTimestampFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    });
</script>