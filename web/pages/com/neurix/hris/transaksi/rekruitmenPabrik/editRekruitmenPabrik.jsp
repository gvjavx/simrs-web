
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenPabrikAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_rekruitmenPabrik'/>";
        }

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {
            window.clos = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href = "<s:url action='search_rekruitmenPabrik.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var kuota = $('#kuota').val();
                var rowCount = $('.rekruitmenDetailTable tr').length;
                rowCount=rowCount-1;
                if (rowCount<=kuota ) {
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
                    if ( rowCount>=kuota) {
                        msg += 'Table <strong>Rekruitmen Pegawai</strong> Melebihi Kuota.' + '<br/>';
                    }
                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');

                }
            });
        });

    </script>
</head>

<body
        class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Edit Rekruitmen Pabrik
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="rekruitmenPabrikForm" method="post"  theme="simple" namespace="/rekruitmenPabrik" action="saveEdit_rekruitmenPabrik.action" cssClass="well form-horizontal">

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
                                <label class="control-label"><small>Rekruitmen Pegawai Id :</small></label>
                            </td>
                            <td>
                                <table>
                                    <s:textfield  id="rekruitmenPabrikId" name="rekruitmenPabrik.rekruitmenPabrikId" required="true" readonly="true" cssClass="form-control"/>                                </table>
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
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true"/>
                                        <s:textfield  id="branchId1" name="rekruitmenPabrik.branchId" required="true" readonly="true" cssStyle="display: none" cssClass="form-control"/>
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
                                        <s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagianId" required="true" disabled="true"
                                                  listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="rekruitmenPabrik.bagianId" />
                                        <s:textfield id="bagianId1" name="rekruitmenPabrik.bagianId" required="false" readonly="true" cssClass="form-control" cssStyle="display: none"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label">
                                        <small>Kuota :</small>
                                    </label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield id="kuota" name="rekruitmenPabrik.kuota" required="false" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                        </table>
                        <br>
                        <br>
                        <%--<h3>
                            <button id="btnAddPegawai" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
                            </button>
                            <span>Tambah Data</span>
                        </h3>
                        <br>--%>
                        <center>
                            <table id="showdata" width="96%">
                                <tr>
                                    <td align="center">
                                        <table style="width: 96%;" class="rekruitmenDetailTable table table-bordered">
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
                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="rekruitmenPabrikForm" id="save" name="save"
                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                            <i class="fa fa-check"></i>
                                            Save
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="search_rekruitmenPabrik.action"/>'">
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
                                                                      clos();
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
                    <center>
                        <table id="showdata1" width="96%">
                            <tr>
                                <td align="center">
                                    <table style="width: 96%;" class="rekruitmenCalonUserTable table table-bordered">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="button" class="btn btn-default btn-success">Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-edit-posisi" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormEdit">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="nipPosisi" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="nipNamaPosisi" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan Lama : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="posisiLamaPosisi" name=""
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" disabled="true" cssClass="form-control"/>                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan Baru : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="posisiBaruPosisi" name=""
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>                        </div>
                    </div>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSavePosisi" type="button" class="btn btn-default btn-success">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-edit-hapus" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormHapus">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="nipHapus" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="nipNamaHapus" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan Lama : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="posisiLamaHapus" name=""
                                      listKey="positionId" listValue="positionName" headerKey="" disabled="true" headerValue="[Select one]" cssClass="form-control"/>                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan Baru : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="posisiBaruHapus" name=""
                                      listKey="positionId" listValue="positionName" headerKey="" disabled="true" headerValue="[Select one]" cssClass="form-control"/>                        </div>
                    </div>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSavehapus" type="button" class="btn btn-default btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</html>

<script>

    window.loadPerson =  function(){
        //alert(nip);
        $('.rekruitmenDetailTable').find('tbody').remove();
        $('.rekruitmenDetailTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        RekruitmenPabrikAction.searchPegawaiCalon(function(listdata) {

            tmp_table = "<thead style='font-size: 12px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Delete</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nip</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Status Giling</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Bagian</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jabatan Lama</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jabatan Baru</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Status Pegawai</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tipe Pegawai</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Approval SDM</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Approval GM</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var sukses2 = "<img border='0' src='<s:url value='/pages/images/icon_not_edit.png'/>' name='icon_trash'>";
                var sukses = "<img border='0' src='<s:url value='/pages/images/icon_success.ico'/>' name='icon_trash'>";
                var fail = "<img border='0' src='<s:url value='/pages/images/icon_failure.ico'/>' name='icon_trash'>";
                var icon,icon2,klas1,klas2,hicon,eicon,hklas,eklas;
                if (item.approvalSdmFlag=="Y"){
                    icon=sukses;
                    klas1="<a class ='sukses' data ='"+item.nip+"' >";
                }
                else {
                    icon=fail;
                    klas1="<a href='javascript:;' class ='item-approvalSDM' data ='"+item.nip+"' >";
                }
                if (item.approvalGmFlag=="Y"){
                    icon2=sukses;
                    klas2="<a class ='sukses' data ='"+item.nip+"' >";
                }
                else {
                    icon2=fail;
                    klas2="<a href='javascript:;' class ='item-approvalGM' data ='"+item.nip+"' >";
                }
                if(item.approvalGmFlag=="Y"&&item.approvalSdmFlag=="Y"){
                    hicon=sukses2;
                    hklas="<a href='javascript:;' class ='sukses' data ='"+item.nip+"' >";
                    eicon=sukses2;
                    eklas="<a href='javascript:;' class ='sukses' data ='"+item.nip+"' >";
                }
                else {
                    hicon= "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_delete'>";
                    hklas="<a href='javascript:;' class ='item-delete' data ='"+item.nip+"' >" ;
                    eicon= "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>";
                    eklas="<a href='javascript:;' class ='item-edit' data ='"+item.nip+"' >" ;
                }
                tmp_table += '<tr style="font-size: 11px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td align="center">' +
                        hklas+
                        hicon+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        eklas +
                        eicon+
                        '</a>' +
                        '</td>' +
                        '<td >' + item.nip + '</td>' +
                        '<td >' + item.namaPegawai + '</td>' +
                        '<td >' + item.statusGiling + '</td>' +
                        '<td align="center">' + item.bagianName + '</td>' +
                        '<td align="center">' + item.posisiLamaName + '</td>' +
                        '<td align="center">' + item.posisiBaruName + '</td>' +
                        '<td align="center">' + item.statusPegawaiName + '</td>' +
                        '<td align="center">' + item.tipePegawaiName + '</td>' +
                        '<td align="center">' +
                        klas1 +
                        icon+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        klas2 +
                        icon2+
                        '</a>' +
                        '</td>' +
                        "</tr>";
            });
            $('.rekruitmenDetailTable').append(tmp_table);
        });
    };
    window.loadCalonPerson =  function(){
        var branchid=$('#branchId1').val();
        var nipid1=$('#nip').val();
        var masagiling =$('#statusGiling1').val();
        $('.rekruitmenCalonUserTable').find('tbody').remove();
        $('.rekruitmenCalonUserTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        RekruitmenPabrikAction.searchPegawai(nipid1,branchid,masagiling,function(listdata) {

            tmp_table = "<thead style='font-size: 12px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Check</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>View</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>nip</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Jabatan</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Bagian</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Status Giling</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Status Pegawai</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tipe Pegawai</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var combo = '<input type="checkbox" id="check" name="checkApprove[]" value="'+item.nip+'" class="check" >';
                tmp_table += '<tr style="font-size: 11px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + combo + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view' data ='"+item.nip+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>"+
                        '</a>' +
                        '</td>' +
                        '<td >' + item.nip + '</td>' +
                        '<td >' + item.namaPegawai + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.bagianName + '</td>' +
                        '<td align="center">' + item.masaGiling + '</td>' +
                        '<td align="center">' + item.statusPegawai + '</td>' +
                        '<td align="center">' + item.tipePegawaiName + '</td>' +
                        "</tr>";
            });
            $('.rekruitmenCalonUserTable').append(tmp_table);
            $('#rekruitmenCalonUserTable').DataTable({
                "lengthChange": false,
                "pageLength": 50,
                "bDestroy": true
            });
        });
    };
    $(document).ready(function() {
        loadPerson();
        $('#btnAddPegawai').click(function(){
            loadCalonPerson();
            $("#nip").prop("readonly", false);
            $('#myForm')[0].reset();
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'addPerson');
            $('#modal-edit').find('.modal-title').text('Add Anggota');
        });
        $('#btnSearch').click(function(){
            loadCalonPerson();
        });
        $('#btnReset').click(function(){
            $('#nip').val("");
            $('#nipNama').val("");
            loadCalonPerson();
        });
        $('#btnSave').click(function(){
            var values = new Array();
            $.each($("input[name='checkApprove[]']:checked"), function() {
                values.push($(this).val());
            });
            if(values.length > 0){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    $.each($("input[name='checkApprove[]']:checked"), function() {
                        RekruitmenPabrikAction.saveTmpPegawai($(this).val(),function(listdata) {
                            $('#modal-edit').modal('hide');
                            $('#myForm')[0].reset();
                            loadPerson();
                        });
                    });
                    alert('Data Successfully Updated');
                }
            }else{
                alert('Silahkan Centang Salah Satu Pegawai !');
            }
        });
        $('.rekruitmenDetailTable').on('click', '.item-edit', function(){
            var nip = $(this).attr('data');
            dwr.engine.setAsync(false);
            RekruitmenPabrikAction.searchRekruitmenPabrikPerson(nip ,function(listdata) {
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
            RekruitmenPabrikAction.searchRekruitmenPabrikPerson(nip ,function(listdata) {
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
            if (confirm('Are you sure you want to save this Record?')) {
                var nip = $('#nipPosisi').val();
                var posisiBaru = $('#posisiBaruPosisi').val();
                if (posisiBaru!=null){
                    RekruitmenPabrikAction.editRekruitmenPabrikPerson(nip,posisiBaru ,function(listdata) {
                        $('#modal-edit-posisi').modal('hide');
                        $('#myFormEdit')[0].reset();
                        loadPerson();
                    });
                } else {
                    alert ("masukkan posisi baru");
                }
                alert('Data Successfully Updated');
            }
        });
        $('#btnSavehapus').click(function(){
            if (confirm('Are you sure you want to save this Record?')) {
                var nip = $('#nipHapus').val();
                RekruitmenPabrikAction.deleteRekruitmenPabrikPerson(nip,function(listdata) {
                    $('#modal-edit-hapus').modal('hide');
                    $('#myFormHapus')[0].reset();
                    loadPerson();
                });
                alert('Data Successfully Updated');
            }
        });
    });
</script>




