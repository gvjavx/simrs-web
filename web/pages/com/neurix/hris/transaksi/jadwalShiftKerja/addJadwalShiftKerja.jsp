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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ShiftAction.js"/>'></script>
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
                window.location.href="<s:url action='initForm_jadwalShiftKerja.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                // var namaJadwal  = document.getElementById("jadwalName").value;
                var unit = document.getElementById("branchid").value;
                var tglAwal = document.getElementById("tglAwal").value;
                var tglAkhir = document.getElementById("tglAkhir").value;
                if (unit!=""&&tglAwal!="") {
                    var awal = tglAwal.split("/").reverse().join("-");
                    var akhir = "";
                    if(tglAkhir != ''){
                        akhir = tglAkhir.split("/").reverse().join("-");
                    }
                    dwr.engine.setAsync(false);
                    JadwalShiftKerjaAction.cekTanggal(unit,tglAwal,tglAkhir,function(listdata) {
                        if (listdata=="00"){
                            if (confirm('Do you want to save this record?')) {
                                JadwalShiftKerjaAction.cekLibur(awal,akhir,function(response) {
                                    if (response.status=="error"){
                                        if (confirm("ini adalah hari libur. Ingin melanjutkan?")){
                                            event.originalEvent.options.submit = true;
                                            $.publish('showDialog');
                                            // if (confirm('Do you want to save this record?')) {
                                            //     event.originalEvent.options.submit = true;
                                            //     $.publish('showDialog');
                                            // } else {
                                            //     // Cancel Submit comes with 1.8.0
                                            //     event.originalEvent.options.submit = false;
                                            // }
                                        }else{
                                            event.originalEvent.options.submit = false;
                                        }
                                    } else{
                                        event.originalEvent.options.submit = true;
                                        $.publish('showDialog');
                                        // if (confirm('Do you want to save this record?')) {
                                        //     event.originalEvent.options.submit = true;
                                        //     $.publish('showDialog');
                                        // } else {
                                        //     // Cancel Submit comes with 1.8.0
                                        //     event.originalEvent.options.submit = false;
                                        // }
                                    }
                                });
                            } else {
                                // Cancel Submit comes with 1.8.0
                                event.originalEvent.options.submit = false;
                            }
                        } else{
                            // Cancel Submit comes with 1.8.0
                            event.originalEvent.options.submit = false;
                            var msg = "Tanggal ini sudah ada";
                            document.getElementById('errorValidationMessage').innerHTML = msg;

                            $.publish('showErrorValidationDialog');
                        }
                    });
                } else {
                    event.originalEvent.options.submit = false;
                    var msg = "";
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
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Tambah Jadwal Shift Kerja</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="jadwalShiftKerjaForm" method="post"  theme="simple" namespace="/jadwalShiftKerja" action="save_jadwalShiftKerja.action" cssClass="form-horizontal">

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
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test='jadwalShiftKerja.adminHcm'>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchid" name="jadwalShiftKerja.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="jadwalShiftKerja.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchid" name="jadwalShiftKerja.branchId" />
                                                        </s:else>
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
                                                            <s:textfield id="tglAwal" name="jadwalShiftKerja.stTanggalAwal" cssClass="form-control pull-right" readonly="true"
                                                                         required="false" cssStyle="background-color: white"/>
                                                            <div class="input-group-addon">
                                                                s/d
                                                            </div>
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="tglAkhir" name="jadwalShiftKerja.stTanggalAkhir" cssClass="form-control pull-right" readonly="true"
                                                                         cssStyle="background-color: white"/>
                                                        </div>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Keterangan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textarea rows="4" id="keterangan" name="jadwalShiftKerja.keterangan" cssClass="form-control"/>
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
                                            <table id="showdata" width="100%">
                                                <tr>
                                                    <td align="center">
                                                        <table style="width: 100%;" class="shiftTable table table-bordered" id="shiftTable">
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
                                                                <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                                           closeTopics="closeDialog" modal="true"
                                                                           resizable="false"
                                                                           height="250" width="600" autoOpen="false"
                                                                           title="Saving Data ...">
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

                                                                    <%--<sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                                                    <%--resizable="false"--%>
                                                                    <%--height="350" width="600" autoOpen="false" title="Saving ...">--%>
                                                                    <%--Please don't close this window, server is processing your request ...--%>
                                                                    <%--</br>--%>
                                                                    <%--</br>--%>
                                                                    <%--</br>--%>
                                                                    <%--<center>--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">--%>
                                                                    <%--</center>--%>
                                                                    <%--</sj:dialog>--%>

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
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-offset-3 col-sm-1">
                                <label class="control-label">Grup</label>
                            </div>
                            <div class="col-sm-4">
                                <s:action id="comboSubDiv" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                <s:select list="#comboSubDiv.comboListOfPositionBagian" id="profesiId" name="shift.profesiId"
                                          listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]" cssClass="form-control" onchange="listShift();listPerson()"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-offset-3 col-sm-1">
                                <label class="control-label">Shift</label>
                            </div>
                            <div class="col-sm-4">
                                <s:action id="comboShift" namespace="/groupShift" name="initComboShift_groupShift"/>
                                <s:select cssClass="form-control" list="#comboShift.listOfComboShift" id="ShiftId" name=""
                                          required="true" listKey="shiftId" listValue="shiftName" headerKey="" headerValue="[Select one]" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-offset-3 col-sm-1">
                                <label class="control-label">On Call</label>
                            </div>
                            <div class="col-sm-4">
                                <s:select list="#{'Y':'Y'}" id="onCall"
                                          headerKey="N" headerValue="N" cssClass="form-control"/>
                            </div>
                        </div>
                        <br>
                    </div>
                    <br>
                    <br>
                    <center>
                        <table id="showdata1" width="100%">
                            <tr>
                                <td align="center">
                                    <table style="width: 100%;" class="groupShiftTable table table-bordered" id="groupShiftTable">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <%--<button id="btnSave" type="button" class="btn btn-default btn-success">Pilih</button>--%>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</html>

<script>
    $(document).ready(function() {
        $('#ShiftId').empty();
        $('#ShiftId').append($("<option></option>")
            .attr("value", '')
            .text(''));
        $('#tglAwal').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#tglAkhir').datepicker({
            dateFormat: 'dd/mm/yy'
        });
        $('#btnAddShift').click(function(){
            var unit = $('#branchid').val();
            if (unit!=''){
                $('#ShiftId').empty();
                $('#myForm')[0].reset();
                $('#modal-edit').modal('show');
                $('#myForm').attr('action', 'addShift');
                $('#modal-edit').find('.modal-title').text('Add Shift');
                dwr.engine.setAsync(false);
                JadwalShiftKerjaAction.searchUserBagian(function(data) {
                    if (!data.adminHcm){
                        $('#profesiId').attr("disabled", true);
                        $('#profesiId').val(data.groupId);
                        listShift();
                        listPerson();
                    }
                });
            }else{
                alert("Isikan unit terlebih dahulu");
            }
        });
    });

    window.listShift = function () {
        var grup = $('#profesiId').val();
        $('#ShiftId').empty();
        if (grup!=''){
            dwr.engine.setAsync(false);
            ShiftAction.searchShiftByGrup(grup, function (listdata) {
                $.each(listdata, function (i, item) {
                    $('#ShiftId').append($("<option></option>")
                        .attr("value", item.shiftId)
                        .text(item.shiftName));
                });
            });
        } else{
            $('#ShiftId').append($("<option></option>")
                .attr("value", '')
                .text(''));
        }
    };
    window.listPerson= function () {
        $('.groupShiftTable').empty();
        var grup = $('#profesiId').val();
        var unit = $('#branchid').val();
        if (grup!=''&& unit!=''){
            dwr.engine.setAsync(false);
            var tmp_table = "";
            JadwalShiftKerjaAction.searchPegawaiByGrup(grup,unit,function(listdata) {
                tmp_table = "<thead><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #90ee90'>No</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>NIP</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Nama Pegawai</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Posisi</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Sub Divisi</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Tambahkan</th>"+
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.nip + '</td>' +
                        '<td align="center">' + item.namaPegawai + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.profesiName + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-add-shift' data ='"+item.nip+"' nama ='"+item.namaPegawai+"' posisi ='"+item.positionName+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/add_task1.png'/>'>"+
                        '</a>' +
                        '</td>' +
                        "</tr>";
                });
                $('.groupShiftTable').append(tmp_table);
                $('#groupShiftTable').DataTable({
                    "pageLength": 20,
                    "bDestroy":true
                });
            });
        }
    };
    window.resultPerson= function () {
        $('.shiftTable').empty();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        JadwalShiftKerjaAction.searchResultPegawai(function(listdata) {
            tmp_table = "<thead><tr class='active'>"+
                "<th style='text-align: center; background-color:  #90ee90'>No</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>NIP</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Nama Pegawai</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Posisi</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Grup</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Shift</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>On Call</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Hapus</th>"+
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="center">' + item.nip + '</td>' +
                    '<td align="center">' + item.namaPegawai + '</td>' +
                    '<td align="center">' + item.positionName + '</td>' +
                    '<td align="center">' + item.profesiName + '</td>' +
                    '<td align="center">' + item.shiftName + '</td>' +
                    '<td align="center">' + item.onCall + '</td>' +
                    '<td align="center">' +
                    "<a href='javascript:;' class ='item-delete-shift' data ='"+item.nip+"' nama ='"+item.namaPegawai+"' shift ='"+item.shiftId+"' posisi ='"+item.positionName+"' >" +
                    "<img border='0' src='<s:url value='/pages/images/delete_task.png'/>'>"+
                    '</a>' +
                    '</td>' +
                    "</tr>";
            });
            $('.shiftTable').append(tmp_table);
            $('#shiftTable').DataTable({
                "pageLength": 20,
                "bDestroy":true
            });
        });
    };
    $('.groupShiftTable').on('click', '.item-add-shift', function () {
        var nip = $(this).attr('data');
        var nama = $(this).attr('nama');
        var posisi = $(this).attr('posisi');
        var grup = $('#profesiId').find('option:selected').text();
        var shift = $('#ShiftId').find('option:selected').text();
        var shiftId = $('#ShiftId').find('option:selected').val();
        var shiftIdKosong = $('#ShiftId').val();
        var grupId = $('#profesiId').find('option:selected').val();
        var onCall = $('#onCall').val();
        var pesan = "Shift is required";
        if (shiftIdKosong == null) {
            alert(pesan);
        }else{
            dwr.engine.setAsync(false);
            JadwalShiftKerjaAction.cekJadwalKerja(nip,shiftId,function (data) {
                if (data.statusSave==""){
                    if (data.jumlahJadwal===1){
                        if (confirm("Sudah ada jadwal sebelumnya apakah anda ingin menambahkan shift gantung ?")) {
                            JadwalShiftKerjaAction.savePegawaiShift(nip,nama,posisi,grup,grupId,shift,shiftId,onCall,function() {
                                resultPerson();
                                listPerson();
                            });
                        }
                    }else if (data.jumlahJadwal===0){
                        JadwalShiftKerjaAction.savePegawaiShift(nip,nama,posisi,grup,grupId,shift,shiftId,onCall,function() {
                            resultPerson();
                            listPerson();
                        });
                    }
                } else{
                    alert(data.statusSave);
                }
            })
        }
    });
    $('.shiftTable').on('click', '.item-delete-shift', function () {
        if (confirm("Apakah data ini ingin dihapus ?")){
            var nip = $(this).attr('data');
            var shift = $(this).attr('shift');
            dwr.engine.setAsync(false);
            JadwalShiftKerjaAction.deletePegawaiShift(nip,shift,function() {
                resultPerson();
            });
        }
    });
</script>
