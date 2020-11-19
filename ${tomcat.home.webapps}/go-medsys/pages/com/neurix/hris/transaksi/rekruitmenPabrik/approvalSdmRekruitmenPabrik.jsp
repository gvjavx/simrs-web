
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
    <script type='text/javascript' src='<s:url value="/dwr/interface/IndisiplinerAction.js"/>'></script>

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
            window.clos = function() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='search_rekruitmenPabrik.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                        if (confirm('Do you want to save this record?')) {
                            event.originalEvent.options.submit = true;
                            $.publish('showDialog');
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
            Approval SDM Rekruitmen Pabrik
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="rekruitmenPabrikForm" method="post"  theme="simple" namespace="/rekruitmenPabrik" action="saveApprove_rekruitmenPabrik.action" cssClass="well form-horizontal">

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
                                    <s:textfield  id="rekruitmenPabrikId" name="rekruitmenPabrik.rekruitmenPabrikId" required="true" readonly="true" cssClass="form-control"/>
                                </table>
                            </td>
                        </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Branch :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="rekruitmenPabrik.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true"/>
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
                                                  listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="rekruitmenPabrik.bagianId" disabled="true" />
                                        <s:textfield cssStyle="display: none;" id="bagian4" name="rekruitmenPabrik.bagianId" required="true" readonly="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                        </table>



                        <br>
                        <br>
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
                                        <button type="button" id="btnApproveAll" class="btn btn-success">
                                            <i class="fa fa-check"></i> Approve Checked
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="search_rekruitmenPabrik.action"/>'">
                                            <i class="fa fa-refresh"></i> Close
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
<div id="modal-edit-approve" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormApprove">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Rekruitmen Pabrik Detail Id : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="rekPabrikDetailIdApprove" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="nipApprove" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="nipNamaApprove" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan Lama : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="posisiLamaApprove" name=""
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" disabled="true" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan Baru : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="posisiBaruApprove" name=""
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" disabled="true" cssClass="form-control"/>
                        </div>
                    </div>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveApprove" type="button" class="btn btn-default btn-success">Approve</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-edit-view" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormView">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="nipView" name="nip" readonly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="nipNamaView" name="nip" readonly="true">
                        </div>
                    </div>
                    <br>
                    <center>
                        <table id="showdata1" width="96%">
                            <tr>
                                <td align="center">
                                    <table style="width: 96%;" class="IndisiplinerTable table table-bordered">
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
    window.loadPerson =  function(){
        //alert(nip);
        $('.rekruitmenDetailTable').find('tbody').remove();
        $('.rekruitmenDetailTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        RekruitmenPabrikAction.searchPegawaiCalon(function(listdata) {

            tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'><input type='checkbox' id='checkAll'></th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Approval SDM</th>"+
                    // "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>View</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nip</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Bagian</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jabatan Lama</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jabatan Baru</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Status Pegawai</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Status Giling</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tipe Pegawai</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Indisipliner</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>View Indisipliner</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var icon,klas;
                if (item.approvalGmFlag=="N"){
                    icon="<img border='0' src='<s:url value='/pages/images/icon_not_edit.png'/>' name='icon_not_edit'>";
                    klas="not-edit";
                }
                else if (item.approvalSdmFlag=="Y"){
                    icon="<img border='0' src='<s:url value='/pages/images/icon_success.ico'/>' name='icon_approval'>";
                    klas="sukses";
                }
                else {
                    icon="<img border='0' src='<s:url value='/pages/images/icon_approval.ico'/>' name='icon_approval'>";
                    klas="item-approve";
                }
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + '<input type="checkbox" id="check" name="checkApprove[]" value="'+item.rekruitmenPabrikDetailId+'" class="check">' + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='"+klas+"' data ='"+item.nip+"' >" +
                        icon+
                        '</a>' +
                        '</td>' +
                        /*'<td align="center">' +
                        "<a href='javascript:;' class ='item-view' data ='"+item.nip+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>"+
                        '</a>' +
                        '</td>' +*/
                        '<td >' + item.nip + '</td>' +
                        '<td >' + item.namaPegawai + '</td>' +
                        '<td align="center">' + item.bagianName + '</td>' +
                        '<td align="center">' + item.posisiLamaName + '</td>' +
                        '<td align="center">' + item.posisiBaruName + '</td>' +
                        '<td align="center">' + item.statusPegawaiName + '</td>' +
                        '<td align="center">' + item.statusGiling + '</td>' +
                        '<td align="center">' + item.tipePegawaiName + '</td>' +
                        '<td align="center">' + item.jmlIndisipliner + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view' data ='"+item.nip+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>"+
                        '</a>' +
                        '</td>' +
                        "</tr>";
            });
            $('.rekruitmenDetailTable').append(tmp_table);
        });
    };
    $(document).ready(function() {
        loadPerson();

        $('.rekruitmenDetailTable').on('click', '.item-approve', function(){
            var nip = $(this).attr('data');
            dwr.engine.setAsync(false);
            RekruitmenPabrikAction.searchRekruitmenPabrikPerson(nip ,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#rekPabrikDetailIdApprove').val(item.rekruitmenPabrikDetailId);
                    $('#nipApprove').val(item.nip);
                    $('#nipNamaApprove').val(item.namaPegawai);
                    $('#posisiLamaApprove').val(item.posisiLama).change();
                    $('#posisiBaruApprove').val(item.posisiBaru).change();
                });
            });

            $('#modal-edit-approve').find('.modal-title').text('Approve SDM');
            $('#modal-edit-approve').modal('show');
            $('#myFormView').attr('action', 'approvePerson');
        });
        $('.rekruitmenDetailTable').on('click', '.item-view', function(){
            var nip = $(this).attr('data');
            dwr.engine.setAsync(false);
            RekruitmenPabrikAction.searchRekruitmenPabrikPerson(nip ,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#nipView').val(item.nip);
                    $('#nipNamaView').val(item.namaPegawai);
                    $('#posisiLamaView').val(item.posisiLama).change();
                    $('#posisiBaruView').val(item.posisiBaru).change();
                });
            });

            $('.IndisiplinerTable').find('tbody').remove();
            $('.IndisiplinerTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            IndisiplinerAction.searchIndisipliner(nip,function(listdata) {

                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tipe Indisipliner</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Keterangan</th>"+
                    "</tr></thead>";
                var i = i ;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td align="center">' + item.stTanggal + '</td>' +
                        '<td align="center">' + item.tipeIndisipliner + '</td>' +
                        '<td align="center">' + item.keteranganPelanggaran + '</td>' +
                        "</tr>";
                });
                $('.IndisiplinerTable').append(tmp_table);
            });

            $('#modal-edit-view').find('.modal-title').text('View Data');
            $('#modal-edit-view').modal('show');
            $('#myFormView').attr('action', 'viewPerson');
        });

        $("#checkAll").change(function(){
            $('input:checkbox').not(this).prop('checked', this.checked);
        });

        $('#btnSaveApprove').click(function(){
            var rekId = $('#rekPabrikDetailIdApprove').val();
            var divisi = "";
                RekruitmenPabrikAction.approveRekruitmenPabrikPerson(divisi,rekId ,"sdm","Y",function(listdata) {
                    $('#modal-edit-approve').modal('hide');
                    $('#myFormApprove')[0].reset();
                    location.reload()
                });
            alert('Data Successfully Updated');
        });
        $('#btnApproveAll').click(function(){
            var values = new Array();
            var status ;
            var divisi = "";
            $.each($("input[name='checkApprove[]']:checked"), function() {
                values.push($(this).val());
            });
            if(values.length > 0){
                dwr.engine.setAsync(false);
                $.each($("input[name='checkApprove[]']:checked"), function() {
                    RekruitmenPabrikAction.approveRekruitmenPabrikPerson(divisi,$(this).val(),"sdm","Y",function(listdata) {
                        location.reload();
                    });
                });
                alert('Data successfully saved !');
            }else{
                alert('Silahkan Centang Salah Satu Data !');
            }
        })
    });
</script>




