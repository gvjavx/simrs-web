
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/JadwalShiftKerjaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/GroupShiftAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_jadwalShiftKerja'/>";
        }

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {
            window.close = function() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='search_jadwalShiftKerja.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var namaJadwal  = document.getElementById("jadwalName").value;
                var unit = document.getElementById("branchid").value;
                var tglAwal = document.getElementById("tglAwal").value;
                if (namaJadwal!=""&&unit!=""&&tglAwal!="") {
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');

                    } else {
                        // Cancel Submit comes with 1.8.0
                        event.originalEvent.options.submit = false;
                    }

                } else {
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if ( namaJadwal == '') {
                        msg += 'Field <strong>Nama Jadwal</strong> is required.' + '<br/>';
                    }
                    if ( unit== '') {
                        msg += 'Field <strong>Unit</strong> is required.' + '<br/>';
                    }
                    if ( tglAwal== '') {
                        msg += 'Field <strong>tanggal Awal</strong> is required.' + '<br/>';
                    }
                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');

                }
            });
            $.subscribe('beforeProcessAddPerson', function (event, data) {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            });
        });


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
            Add Jadwal Shift Kerja
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="jadwalShiftKerjaForm" method="post"  theme="simple" namespace="/jadwalShiftKerja" action="save_jadwalShiftKerja.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Nama Jadwal :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="jadwalName" name="jadwalShiftKerja.jadwalShiftKerjaName" required="true" cssClass="form-control"/>
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
                                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="jadwalShiftKerja.branchId" required="true" listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Tanggal :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglAwal" name="jadwalShiftKerja.stTanggalAwal" cssClass="form-control pull-right"
                                                         required="false" cssStyle=""/>
                                            <div class="input-group-addon">
                                                s/d
                                            </div>
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglAkhir" name="jadwalShiftKerja.stTanggalAkhir" cssClass="form-control pull-right"
                                                         required="false" cssStyle=""/>
                                        </div>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Status Giling :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'DMG':'Dalam Masa Giling','LMG':'Luar Masa Giling'}" id="statusGiling" name="jadwalShiftKerja.statusGiling"
                                                  headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Keterangan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textarea rows="4" id="keterangan" name="jadwalShiftKerja.keterangan" required="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br>
                        <br>
                        <h3>
                            Shift
                            <button
                                    id="btnAddShift" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
                            </button>
                        </h3>
                        <br>
                        <center>
                            <table id="showdata" width="80%">
                                <tr>
                                    <td align="center">
                                        <table style="width: 80%;" class="shiftTable table table-bordered">
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                        <br>
                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="jadwalShiftKerjaForm" id="save" name="save"
                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                            <i class="fa fa-check"></i>
                                            Save
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="add_jadwalShiftKerja.action"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="actions" class="form-actions">
                            <table>
                                <tr>
                                    <div id="crud">
                                        <td>
                                            <table>
                                                <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="350" width="600" autoOpen="false" title="Saving ...">
                                                    Please don't close this window, server is processing your request ...
                                                    </br>
                                                    </br>
                                                    </br>
                                                    <center>
                                                        <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
                                                    </center>
                                                </sj:dialog>

                                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                           buttons="{
                                                              'OK':function() {
                                                                      close();
                                                                   }
                                                            }"
                                                >
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                    Record has been saved successfully.
                                                </sj:dialog>

                                                <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                           height="250" width="600" autoOpen="false" title="Error Dialog"
                                                           buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); }
                                                                    }"
                                                >
                                                    <div class="alert alert-error fade in">
                                                        <label class="control-label" align="left">
                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                                        </label>
                                                    </div>
                                                </sj:dialog>

                                                <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                                           height="280" width="500" autoOpen="false" title="Warning"
                                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                                >
                                                    <div class="alert alert-error fade in">
                                                        <label class="control-label" align="left">
                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                            <br/>
                                                            <center><div id="errorValidationMessage"></div></center>
                                                        </label>
                                                    </div>
                                                </sj:dialog>
                                            </table>
                                        </td>
                                    </div>
                                </tr>
                            </table>
                        </div>
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
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <s:action id="comboGroupShift" namespace="/groupShift" name="initComboGroupShift_groupShift"/>
                    <s:select cssClass="form-control" list="#comboGroupShift.listOfComboGroupShift" id="GroupShiftId" name=""
                              required="true" listKey="groupShiftId" listValue="groupShiftName" headerKey="" cssStyle="display: none" headerValue="[Select one]" />
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-offset-3 col-sm-1">
                                <label class="control-label"><small>Shift </small></label>
                            </div>
                            <div class="col-sm-4">
                                <s:action id="comboShift" namespace="/groupShift" name="initComboShift_groupShift"/>
                                <s:select cssClass="form-control" list="#comboShift.listOfComboShift" id="ShiftId" name=""
                                          required="true" listKey="shiftId" listValue="shiftName" headerKey="" headerValue="[Select one]" />
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-sm-offset-3 col-sm-1">
                                <label class="control-label"><small>Group </small></label>
                            </div>
                            <div class="col-sm-4">
                                <s:action id="comboGroup" namespace="/groupShift" name="initComboGroup_groupShift"/>
                                <s:select cssClass="form-control" list="#comboGroup.listOfComboGroup" id="GroupId" name=""
                                          required="true" listKey="groupId" listValue="groupName" headerKey="" headerValue="[Select one]" />
                            </div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <center>
                        <table id="showdata1" width="80%">
                            <tr>
                                <td align="center">
                                    <table style="width: 80%;" class="groupShiftTable table table-bordered" id="groupShiftTable">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="button" class="btn btn-default btn-success">Pilih</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-member" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Group Member</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormGroupMember">
                    <center>
                        <table id="showdata2" width="100%">
                            <tr>
                                <td align="center">
                                    <table style="width: 100%;" class="groupMemberTable table table-bordered" id="groupMemberTable">

                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</html>

<script>
    window.loadShift =  function(){
        $('.shiftTable').find('tbody').remove();
        $('.shiftTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        JadwalShiftKerjaAction.searchGroupShift(function(listdata) {
            tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Delete</th>"+
                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Shift</th>"+
                "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Group</th>"+
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="center">' +
                    "<a href='javascript:;' class ='item-delete-shift' data ='"+item.groupShiftId+"' >" +
                    "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_trash'>"+
                    '</a>' +
                    '</td>' +
                    '<td align="center">' + item.shiftName + '</td>' +
                    '<td align="center">' + item.groupName + '</td>' +
                    "</tr>";
            });
            $('.shiftTable').append(tmp_table);
        });
    };
    window.loadGroup =  function(){
        dwr.engine.setAsync(false);
        var shiftId=$('#ShiftId').val();
        var groupId=$('#GroupId').val();
        GroupShiftAction.searchGroupShiftId(groupId,shiftId,function(data) {
            $('#GroupShiftId').val(data);
        });
        var groupShiftId=$('#GroupShiftId').val();
        $('.groupShiftTable').find('tbody').remove();
        $('.groupShiftTable').find('thead').remove();
        if (groupShiftId!=""){
            dwr.engine.setAsync(false);
            var tmp_table = "";
            JadwalShiftKerjaAction.searchGroup(groupShiftId,function(listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc ; display:none'>Id</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>View</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Group Id</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Group Name</th>"+
                    "</tr></thead>";
                var i = i ;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td style="display:none">' + item.groupShiftId + '</td>' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-member' data ='"+item.groupId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>"+
                        '</a>' +
                        '</td>' +
                        '<td>' + item.groupId + '</td>' +
                        '<td>' + item.groupName + '</td>' +
                        "</tr>";
                });
                $('.groupShiftTable').append(tmp_table);
            });
        }
    };
    $(document).ready(function() {

        $('#tglAwal').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#tglAkhir').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        loadShift();

        $('.groupShiftTable').on('click', '.item-view-member', function () {
            var id = $(this).attr('data');
            $('.groupMemberTable').find('tbody').remove();
            $('.groupMemberTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            JadwalShiftKerjaAction.searchGroupMember(id,function(listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Pegawai</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jabatan</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Unit</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Bidang</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Status Giling</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Golongan</th>"+
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.nip + '</td>' +
                        '<td >' + item.nama + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.branchName + '</td>' +
                        '<td align="center">' + item.divisiName + '</td>' +
                        '<td align="center">' + item.statusGilingName + '</td>' +
                        '<td align="center">' + item.golonganName + '</td>' +
                        "</tr>";
                });
                $('.groupMemberTable').append(tmp_table);
                $("#groupMemberTable td:contains('null')").html("-");
            });

            $('#modal-member').find('.modal-title').text('View Member');
            $('#modal-member').modal('show');
        });
        $('.shiftTable').on('click', '.item-delete-shift', function () {
            var id = $(this).attr('data');
            dwr.engine.setAsync(false);
            if (confirm('Are you sure you want to delete this Record?')) {
                JadwalShiftKerjaAction.deleteJadwalShiftKerja(id,function(listdata) {
                    loadShift();
                });
                alert('Data Successfully Updated');
            }
        });
        $('#btnAddShift').click(function(){
            $('#myForm')[0].reset();
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'addShift');
            $('#modal-edit').find('.modal-title').text('Add Shift');

        });

        $('.groupMemberTable').on('click', '.item-view', function () {
            id = $(this).attr('data');
            dwr.engine.setAsync(false);
            loadMember();
            $('#modal-view-study').find('.modal-title').text('View Study');
            $('#modal-view-study').modal('show');
            $('#ViewStudy').attr('action', 'editPerson');
        });

        $('#GroupId').change(function(){
            var groupId = $('#groupId').val();
            var shiftId = $('#shiftId').val();
            if (groupId!=""&&shiftId!=""){
                loadGroup();
            }
        });
        $('#ShiftId').change(function(){
            var groupId = $('#groupId').val();
            var shiftId = $('#shiftId').val();
            if (groupId!=""&&shiftId!=""){
                loadGroup();
            }
        });
        $('#btnSave').click(function(){
            var values = new Array();
            var val1;
            var table = $("#groupShiftTable");
            table.find('tr').each(function (i) {
                var $tds = $(this).find('td');
                val1= $tds.eq(0).text();
                if (val1!=''){
                    values.push(val1);
                }
            });
            if(values.length > 0){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    table.find('tr').each(function (i) {
                        var $tds = $(this).find('td');
                        val1 = $tds.eq(0).text();
                        if (val1 != '') {
                            JadwalShiftKerjaAction.saveTmpShift(val1, function (listdata) {
                                $('#modal-edit').modal('hide');
                                $('#myForm')[0].reset();
                                loadShift();
                            });
                        }
                    });
                    alert('Data Successfully Updated');
                }
            }else{
                alert('Belum memilih Shift !');
            }
        });
        $('.rekruitmenDetailTable').on('click', '.item-edit', function(){
            var nip = $(this).attr('data');
            dwr.engine.setAsync(false);
            JadwalShiftKerjaAction.searchJadwalShiftKerjaPerson(nip ,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#nipPosisi').val(item.nip);
                    $('#nipNamaPosisi').val(item.namaPegawai);
                    $('#posisiLamaPosisi').val(item.posisiLama).change();
                    $('#posisiBaruPosisi').val(item.posisiBaru).change();
                });
            });

            $('#modal-edit-posisi').find('.modal-title').text('Edit Posisi Baru');
            $('#modal-edit-posisi').modal('show');
            $('#myFormEdit').attr('action', 'editPerson');
        });
        $('.rekruitmenDetailTable').on('click', '.item-delete', function(){
            var nip = $(this).attr('data');
            dwr.engine.setAsync(false);
            JadwalShiftKerjaAction.searchJadwalShiftKerjaPerson(nip ,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#nipHapus').val(item.nip);
                    $('#nipNamaHapus').val(item.namaPegawai);
                    $('#posisiLamaHapus').val(item.posisiLama).change();
                    $('#posisiBaruHapus').val(item.posisiBaru).change();
                });
            });

            $('#modal-edit-hapus').find('.modal-title').text('Hapus Data');
            $('#modal-edit-hapus').modal('show');
            $('#myFormHapus').attr('action', 'deletePerson');
        });

        $('#btnSavePosisi').click(function(){
            var nip = $('#nipPosisi').val();
            var posisiBaru = $('#posisiBaruPosisi').val();
            if (posisiBaru!=''){
                if (confirm('Are you sure you want to edit this Record?')) {
                    JadwalShiftKerjaAction.editJadwalShiftKerjaPerson(nip,posisiBaru ,function(listdata) {
                        $('#modal-edit-posisi').modal('hide');
                        $('#myFormEdit')[0].reset();
                        location.reload()
                    });
                }
                alert('Data Successfully Updated');
            }
            else {
                alert ("masukkan posisi baru");
            }

        });
        $('#btnSavehapus').click(function(){
            if (confirm('Are you sure you want to delete this Record?')) {
                var nip = $('#nipHapus').val();
                JadwalShiftKerjaAction.deleteJadwalShiftKerjaPerson(nip,function(listdata) {
                    $('#modal-edit-hapus').modal('hide');
                    $('#myFormHapus')[0].reset();
                    location.reload()
                });
                alert('Data Successfully Updated');
            }
        });
    });
</script>
