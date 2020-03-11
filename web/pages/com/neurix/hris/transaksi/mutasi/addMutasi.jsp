
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MutasiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>

    <style>
        .big-checkbox {width: 20px; height: 20px;}
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
                window.location.href="<s:url action='initForm_mutasi.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {

                var tanggalEfektif= document.getElementById("tanggalEfektif").value;
                var q = new Date();
                var m = q.getMonth()+1;
                var d = q.getDay();
                var y = q.getFullYear();
                var date = new Date(y,m,d);

                var tgl = tanggalEfektif.split("-");
                var mydate = new Date(tgl[2], tgl[1], parseInt(tgl[0])+1);

                if (tanggalEfektif != '') {
                    if (isNaN(mydate) == false) {
//                        if(date <= mydate){
                            if(cekDataMutasi() == 'berhasil'){
                                if (confirm('Do you want to save this record?')) {
                                    event.originalEvent.options.submit = true;
                                    $.publish('showDialog');
                                } else {
                                    event.originalEvent.options.submit = false;
                                }
                            }else{
                                alert(cekDataMutasi());
                                event.originalEvent.options.submit = false;
                            }
//                        }else{
//                            event.originalEvent.options.submit = false;
//                            var msg = "";
//                            msg += '<strong>Tanggal Mutasi tidak dapat kurang dari tanggal sekarang </strong>.' + '<br/>';
//                            document.getElementById('errorValidationMessage').innerHTML = msg;
//                            $.publish('showErrorValidationDialog');
//                        }
                    }else{
                        event.originalEvent.options.submit = false;
                        var msg = "";
                        msg += '<strong>Format Tanggal salah, format menggunakan (dd-mm-yyyy)</strong>.' + '<br/>';
                        document.getElementById('errorValidationMessage').innerHTML = msg;
                        $.publish('showErrorValidationDialog');
                    }

                } else {
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (tanggalEfektif == '') {
                        msg += 'Field <strong>Tanggal Efektif</strong> is required.' + '<br/>';
                    }
                    document.getElementById('errorValidationMessage').innerHTML = msg;
                    $.publish('showErrorValidationDialog');
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
            Add Mutasi / Rotasi
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">

                    <s:hidden id="verif" name="mutasi.verif"/>
                    <s:hidden id="erVerif" name="mutasi.erVerif"/>
                    <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <center>
                            <s:property value="mutasi.erVerif"/>
                        </center>
                    </div>

                    <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <center>
                            <s:property value="mutasi.verif"/>
                        </center>
                    </div>
                    
                    <s:form id="mutasiForm" method="post"  theme="simple" namespace="/mutasi" action="saveMutasi_mutasi.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Tanggal Efektif : </small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggalEfektif" name="mutasi.stTanggalEfektif" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </table>
                                </td>
                            </tr>

                        </table>

                        <br>
                        <h3>
                        <h3>
                            Anggota
                            <button
                                    id="btnAddMutasi" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
                            </button>
                        </h3>
                        <center>
                            <table id="showdata" width="100%">
                                <tr>
                                    <td align="center">
                                        <table style="width: 100%;" class="sppdPersonTable table table-bordered">
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
                                        <sj:submit targets="crsud" type="button" cssClass="btn btn-primary" formIds="mutasiForm" id="save" name="save"
                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                            <i class="fa fa-check"></i>
                                            Save
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="add_mutasi.action"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-default" onclick="window.location.href='<s:url action="initForm_mutasi.action"/>'">
                                            <i class="fa fa-close"></i> Cancel
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>


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
    <div class="modal-dialog" style="width: 500px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Status:</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="statusMutasi" onchange="cekStatusMutasi()">
                                <option value="M">Move</option>
                                <option value="R">Resign</option>
                                <option value="P">Pensiun</option>
                                <option value="MH">Move Holding</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tipe:</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="tipeMutasi">
                                <option value="MT">Mutasi</option>
                                <option value="RT">Rotasi</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="nip2" name="nip">
                            <input style="display: none" type="text" class="form-control" id="nip1" name="nip1">
                            <input style="display: none" type="text" class="form-control" id="nipOld" name="nip1">
                        </div>
                        <script type='text/javascript'>
                            var functions, mapped;
                            // var prov = document.getElementById("provinsi1").value;
                            $('#nip2').typeahead({
                                minLength: 1,
                                source: function (query, process) {
                                    functions = [];
                                    mapped = {};

                                    var data = [];
                                    dwr.engine.setAsync(false);
                                    MutasiAction.initComboPersonil(query,'', function (listdata) {
                                        data = listdata;
                                        //alert('aa');
                                    });
                                    //alert(prov);
                                    $.each(data, function (i, item) {
                                        var labelItem =item.namaPegawai;
                                        var labelNip = item.nip;
                                        mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem, branchId : item.branch,
                                            divisiId: item.divisi, positionId : item.positionId, pjs : item.pjs, golongan:item.golongan };
                                        functions.push(labelItem);
                                    });
                                    process(functions);
                                },
                                updater: function (item) {
                                    var selectedObj = mapped[item];
                                    var namaAlat = selectedObj.label;
                                    document.getElementById("nip1").value = selectedObj.id;
                                    $('#positionLamaId1').val(selectedObj.positionId).change();
                                    $('#branchLamaId1').val(selectedObj.branchId).change();
                                    $('#divisiLamaId1').val(selectedObj.divisiId).change();
                                    $('#golonganLamaId1').val(selectedObj.golongan).change();
                                    if(selectedObj.pjs == 'Y'){
                                        document.getElementById("pjsLama").checked = true;
                                        $("#txtPjsLama").val('Y');
                                    }else{
                                        document.getElementById("pjsLama").checked = false;
                                        $("#txtPjsLama").val('N');
                                    }
                                    return namaAlat;
                                }
                            });

                        </script>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="branchLamaId1">Unit Lama:</label>
                        <div class="col-sm-8">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="branchLamaId1" name="mutasi.branchLamaId" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiLamaId1">Divisi/Bidang Lama:</label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="divisiLamaId1" name="mutasi.divisiLamaId" disabled="true"
                                      listKey="departmentId" listValue="departmentName"   cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="positionLamaId1">Posisi Lama:</label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="positionLamaId1" name="mutasi.positionLamaId" disabled="true"
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="positionLamaId1">Level Lama:</label>
                        <div class="col-sm-8">
                            <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                            <s:select list="#initComboTipe.listComboGolongan" id="golonganLamaId1" name="mutasi.levelLama" disabled="true"
                                      listKey="golonganId" listValue="stLevel" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >PJS Lama:</label>
                        <div class="col-sm-8">
                            <input type="checkbox" class="form-check-input big-checkbox" id="pjsLama" disabled onchange="cekPjsLama()">
                            <input style="display: none" type="text" class="form-check-input " id="txtPjsLama" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="branchBaruId1">Unit Baru:</label>
                        <div class="col-sm-8">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="branchBaruId1" name="mutasi.branchBaruId" onchange="listDivisi()"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiBaruId2">Divisi/Bidang Baru:</label>
                        <div class="col-sm-8">
                            <%--<s:action id="comboDivisi" namespace="/department" name="searchDepartment2_department"/>--%>
                            <%--<s:select list="#comboDivisi.listComboDepartment" id="divisiBaruId2" name="mutasi.divisiBaruId" onchange="listPosisi()"--%>
                                      <%--listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control" />--%>

                            <s:action id="comboDivisi" namespace="/department"
                                      name="searchDepartment2_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="divisiBaruId2"
                                      name="mutasi.divisiBaruId" onchange="listPosisi()"
                                      listKey="departmentId" listValue="departmentName"
                                      headerKey="" headerValue=""
                                      cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Posisi Baru:</label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition" id="positionBaruId1" name="mutasi.positionBaruId" onchange="cekJabatan()"
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="positionLamaId1">Level Baru:</label>
                        <div class="col-sm-8">
                            <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                            <s:select list="#initComboTipe.listComboGolongan" id="golonganBaruId1" name="mutasi.levelBaru"
                                      listKey="golonganId" listValue="stLevel" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">Menggantikan:</label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <select class="form-control" id="penggantiId" name="mutasi.penggantiNip"></select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-4" >PJS Baru:</label>
                        <div class="col-sm-8">
                            <input type="checkbox" class="form-check-input big-checkbox" onchange="cekPjsBaru()" id="pjsBaru">
                            <input style="display: none" type="text" class="form-check-input" value="N" id="txtPjsBaru" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="button" class="btn btn-default btn-success">Add Mutasi</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</html>

<script>

    window.listDivisi= function(){
        var branch = document.getElementById("branchBaruId1").value;
        $('#divisiBaruId2').empty();
        PositionAction.searchDivisi2(branch, function(listdata){
            $.each(listdata, function (i, item) {
                $('#divisiBaruId2').append($("<option></option>")
                        .attr("value",item.departmentId)
                        .text(item.departmentName));
            });
        });
        listPosisi();

    };

    window.loadPerson =  function(){
        //alert(nip);
        $('.sppdPersonTable').find('tbody').remove();
        $('.sppdPersonTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        MutasiAction.searchMutasiPerson(function(listdata){
            tmp_table = "<thead style='font-size: 10px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>NIP</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Unit Lama</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bagian Lama</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Jabatan Lama</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Level Lama</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>PJS Lama</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Menggantikan (NIP)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Menggantikan (Nama)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Unit Baru</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bagian Baru</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Jabatan Baru</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Level Baru</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>PJS Baru</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tipe</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 10px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='"+item.nip+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='"+item.nip+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td >' + item.nip + '</td>' +
                        '<td align="center">' + item.nama+ '</td>' +
                        '<td align="center">' + item.branchLamaName+ '</td>' +
                        '<td align="center">' + item.divisiLamaName+ '</td>' +
                        '<td align="center">' + item.positionLamaName+ '</td>' +
                        '<td align="center">' + item.levelLamaName+ '</td>' +
                        '<td align="center">' + item.pjsLama+ '</td>' +

                        '<td align="center">' + item.penggantiNip+ '</td>' +
                        '<td align="center">' + item.penggantiNama+ '</td>' +
                        '<td align="center">' + item.branchBaruName+ '</td>' +
                        '<td align="center">' + item.divisiBaruName+ '</td>' +
                        '<td align="center">' + item.positionBaruName+ '</td>' +
                        '<td align="center">' + item.levelBaruName+ '</td>' +
                        '<td align="center">' + item.pjs+ '</td>' +
                        '<td align="center">' + item.statusName+ '</td>' +
                        '<td align="center">' + item.tipeMutasiName+ '</td>' +
                        "</tr>";
            });
            $('.sppdPersonTable').append(tmp_table);
        });
    }
    $(document).ready(function() {
        loadPerson();


        $('#tanggalEfektif').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
//            minDate: 0,
        });


        $('#btnAddMutasi').click(function(){


            $('#positionBaruId').empty();
            $('#positionBaruId1').empty();
            $('#penggantiId').empty();
            $("#nip2").prop("readonly", false);
            $("#statusMutasi").prop("disabled", false);
            $("#tipeMutasi").prop("disabled", false);
            cekStatusMutasi();
            $("#branchBaruId1").prop("readonly", false);
            $("#positionBaruId1").prop("readonly", false);
            $("#divisiBaruId2").prop("readonly", false);
            $('#myForm')[0].reset();
            $("#btnSave").html('Save');
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'addPerson');
            $('#modal-edit').find('.modal-title').text('Add Mutasi');
        });

    });

    $('.sppdPersonTable').on('click', '.item-edit', function(){
        $("#nip2").attr("readonly", false);
        /*$("#branchLamaId1").attr("disabled", false);
        $("#positionLamaId1").attr("disabled", false);
        $("#divisiLamaId1").attr("disabled", false);*/
        $("#branchBaruId1").prop("disabled", false);
        $("#divisiBaruId2").prop("disabled", false);
        $("#positionBaruId1").prop("disabled", false);
        $("#penggantiId").prop("disabled", false);
        $("#pjsBaru").prop("disabled", false);

        $('#branchBaruId1').val('').change();
        $('#divisiBaruId2').val('').change();
        $('#positionBaruId1').val('').change();
        $('#penggantiId').val('').change();

        var nip = $(this).attr('data');
        MutasiAction.searchMutasiPersonEdit(nip ,function(listdata) {
            $.each(listdata, function (i, item) {
                $('#statusMutasi').val(item.status);
                $('#statusMutasi').prop("disabled", false);
                $('#nip1').val(item.nip);
                $('#nipOld').val(item.nip);
                $('#nip').val(item.nama);

                $('#branchLamaId1').val(item.branchLamaId).change();
                $('#positionLamaId1').val(item.positionLamaId).change();
                $('#divisiLamaId1').val(item.divisiLamaId).change();

                if(item.status == "R" || item.status == "P"){
                    $("#branchBaruId1").prop("disabled", true);
                    $("#positionBaruId1").prop("disabled", true);
                    $("#divisiBaruId2").prop("disabled", true);
                    $("#penggantiId").prop("disabled", true);
                    $("#pjsBaru").prop("disabled", true);
                }else{
                    //alert(item.positionBaruId);
                    listPosisi(item.branchBaruId, item.divisiBaruId);
                    $('#branchBaruId1').val(item.branchBaruId).change();
                    $('#divisiBaruId2').val(item.divisiBaruId).change();
                    $('#positionBaruId1').val(item.positionBaruId).change();
                    $('#penggantiId').val(item.penggantiNip).change();
                }

                if(item.pjsLama == 'Y'){
                    document.getElementById("pjsLama").checked = true;
                    $("#txtPjsLama").val('Y');
                }else{
                    document.getElementById("pjsLama").checked = false;
                    $("#txtPjsLama").val('N');
                }

                if(item.pjs == 'Y'){
                    document.getElementById("pjsBaru").checked = true;
                    $("#txtPjsBaru").val('Y');
                }else{
                    document.getElementById("pjsBaru").checked = false;
                    $("#txtPjsBaru").val('N');
                }

                //document.getElementById("Check1").checked = item.pjs;
            });
        });

        $("#btnSave").html('Save');
        $('#modal-edit').find('.modal-title').text('Edit Data');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'editPerson');
    });

    $('.sppdPersonTable').on('click', '.item-delete', function(){
        var id = $(this).attr('data');

        MutasiAction.searchMutasiPersonEdit(id ,function(listdata) {
            $.each(listdata, function (i, item) {
                $('#statusMutasi').val(item.status);
                $('#statusMutasi').prop("disabled", true);
                $('#nip1').val(item.nip);
                $('#nipOld').val(item.nip);
                $('#nip').val(item.nama);

                $('#branchLamaId1').val(item.branchLamaId).change();
                $('#positionLamaId1').val(item.positionLamaId).change();
                $('#divisiLamaId1').val(item.divisiLamaId).change();

                //listPosisi(item.branchBaruId, item.divisiBaruId);
                $('#branchBaruId1').val(item.branchBaruId).change();
                $('#divisiBaruId2').val(item.divisiBaruId).change();
                $('#positionBaruId1').val(item.positionBaruId).change();


                if(item.pjsLama == 'Y'){
                    document.getElementById("pjsLama").checked = true;
                    $("#txtPjsLama").val('Y');
                }else{
                    document.getElementById("pjsLama").checked = false;
                    $("#txtPjsLama").val('N');
                }

                if(item.pjs == 'Y'){
                    document.getElementById("pjsBaru").checked = true;
                    $("#txtPjsBaru").val('Y');
                }else{
                    document.getElementById("pjsBaru").checked = false;
                    $("#txtPjsBaru").val('N');
                }

                //document.getElementById("Check1").checked = item.pjs;
            });
        });

        $("#nip2").prop("readonly", true);
        $("#branchLamaId1").prop("disabled", true);
        $("#positionLamaId1").prop("disabled", true);
        $("#divisiLamaId1").prop("disabled", true);
        $("#branchBaruId1").prop("disabled", true);
        $("#positionBaruId1").prop("disabled", true);
        $("#divisiBaruId2").prop("disabled", true);
        $("#penggantiId").prop("disabled", true);
        $("#btnSave").html('Delete');
        $('#modal-edit').find('.modal-title').text('Delete Data Anggota');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'deletePerson');

    });

    $('#btnSave').click(function(){
        var url = $('#myForm').attr('action');
        var data = $('#myForm').serialize();

        var nip         = document.getElementById("nip1").value;
        var personName  = document.getElementById("nip2").value;
        var nipOld      = document.getElementById("nipOld").value;

        var branchLamaId  = document.getElementById("branchLamaId1").value;
        var divisiLamaId    = document.getElementById("divisiLamaId1").value;
        var positionLamaId = document.getElementById("positionLamaId1").value;
        var levelLamaId = document.getElementById("golonganLamaId1").value;
        var txtPjsLama = document.getElementById("txtPjsLama").value;

        var branchLamaName      = $('#branchLamaId1 option:selected').text();
        var divisiLamaName      = $('#divisiLamaId1 option:selected').text();
        var positionLamaName    = $('#positionLamaId1 option:selected').text();
        var levelLamaName    = $('#golonganLamaId1 option:selected').text();

        var menggantikanId      = document.getElementById("penggantiId").value;
        var menggantikanNama    = $('#penggantiId option:selected').text();
        var branchBaruId        = document.getElementById("branchBaruId1").value;
        var divisiBaruId        = document.getElementById("divisiBaruId2").value;
        var positionBaruId      = document.getElementById("positionBaruId1").value;
        var levelBaruId      = document.getElementById("golonganBaruId1").value;
        var txtPjsBaru          = document.getElementById("txtPjsBaru").value;
        var status              = document.getElementById("statusMutasi").value;
        var tipe              = document.getElementById("tipeMutasi").value;

        var branchBaruName      = $('#branchBaruId1 option:selected').text();
        var divisiBaruName      = $('#divisiBaruId2 option:selected').text();
        var positionBaruName    = $('#positionBaruId1 option:selected').text();
        var levelBaruName    = $('#golonganBaruId1 option:selected').text();

        if(url == 'addPerson'){
            if(nip != ''){
                if(status == 'M' && branchBaruId != '' ||status == 'MH' && branchBaruId != '' ){
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        MutasiAction.saveAnggotaAdd(nip, personName, branchLamaId, branchLamaName, divisiLamaId, divisiLamaName, positionLamaId, positionLamaName, txtPjsLama,
                                menggantikanId, menggantikanNama, branchBaruId, branchBaruName, divisiBaruId, divisiBaruName, positionBaruId, positionBaruName, txtPjsBaru,  status ,
                                tipe,levelLamaId,levelBaruId,levelLamaName,levelBaruName, function(listdata) {

                                    if(listdata){
                                        alert('Data Successfully Added');
                                        $('#modal-edit').modal('hide');
                                        $('#myForm')[0].reset();
                                        loadPerson();
                                    }else{
                                        alert('User sudah ada pada list data !');
                                    }
                                });
                    }
                }else if(status == 'R' || status == 'P'){
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        MutasiAction.saveAnggotaAdd(nip, personName, branchLamaId, branchLamaName, divisiLamaId, divisiLamaName, positionLamaId, positionLamaName, txtPjsLama,
                                '', '', '', '', '', '', '', '', '',  status ,'','','','','', function(listdata) {

                                    if(listdata){
                                        alert('Data Successfully Added');
                                        $('#modal-edit').modal('hide');
                                        $('#myForm')[0].reset();
                                        loadPerson();
                                    }else{
                                        alert('User sudah ada pada list data !');
                                    }
                                });
                    }
                } else{
                    alert('Status mutasi dan unit harus dipilih');
                }
            }else{
                alert('Name must be Entry');
            }
        }else if(url == 'editPerson'){
            if(status == 'M'){
                if(nip == '' || branchLamaId == '' || divisiLamaId == '' || positionLamaId == '' ||  branchBaruId == '' || divisiBaruId == '' ||positionBaruId == '' || menggantikanId == ''){
                    alert('Unit, bagian, posisi dan pengganti harus diisi');
                }else{
                    if (confirm('Are you sure you want to save this Record?')) {
                        if (confirm('Are you sure you want to save this Record?')) {
                            dwr.engine.setAsync(false);
                            MutasiAction.saveAnggotaEdit(nipOld,nip, personName, branchLamaId, branchLamaName, divisiLamaId, divisiLamaName, positionLamaId, positionLamaName,
                                    txtPjsLama, menggantikanId, menggantikanNama, branchBaruId, branchBaruName, divisiBaruId, divisiBaruName, positionBaruId, positionBaruName,
                                    txtPjsBaru,  status ,function(listdata) {
                                        if(listdata){
                                            alert('Data Successfully Updated');
                                            $('#modal-edit').modal('hide');
                                            $('#myForm')[0].reset();
                                            loadPerson();
                                        }else{
                                            alert('User sudah ada pada list data !');
                                        }
                                    });
                        }
                    }
                }
            }else if(status == 'R'){
                if(nip == '' || branchLamaId == '' || divisiLamaId == '' || positionLamaId == ''){
                    alert('Unit, bagian lama dan posisi lama');
                }else{
                    if (confirm('Are you sure you want to save this Record?')) {
                        if (confirm('Are you sure you want to save this Record?')) {
                            dwr.engine.setAsync(false);
                            MutasiAction.saveAnggotaEdit(nipOld,nip, personName, branchLamaId, branchLamaName, divisiLamaId, divisiLamaName, positionLamaId, positionLamaName, txtPjsLama,
                                    branchBaruId, branchBaruName, divisiBaruId, '', '', '', txtPjsBaru,  status ,function(listdata) {
                                        if(listdata){
                                            alert('Data Successfully Updated');
                                            $('#modal-edit').modal('hide');
                                            $('#myForm')[0].reset();
                                            loadPerson();
                                        }else{
                                            alert('User sudah ada pada list data !');
                                        }
                                    });
                        }
                    }
                }
            } else{
                alert('status mutasi dan unit harus dipilih');
            }
        }else{
            if (confirm('Are you sure you want to delete this Record?')) {
                MutasiAction.saveAnggotaDelete(nip, function (listdata) {
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    alert('Record has been Deleted successfully.');
                    loadPerson();
                });
            }
        }
    });

    function cekJabatan(){
        var branchBaruId  = document.getElementById("branchBaruId1").value;
        var divisiBaruId  = document.getElementById("divisiBaruId2").value;
        var positionBaruId= document.getElementById("positionBaruId1").value;
        if(branchBaruId != ''  && positionBaruId !=''){
            var ada = "N" ;
            MutasiAction.getAvailableJabatan(branchBaruId, divisiBaruId, positionBaruId, function (listdata) {
                $.each(listdata, function (i, item) {
                    /*alert("Jabatan Pada Unit : " + item.branchName + ", \nBagian : " + item.divisiName
                            + ", \nPosition " + item.positionName + ", masih dijabat oleh : " + item.personName);*/
                    ada = "Y";
                });
                if(ada == "N"){
                    alert("Jabatan Masih Kosong");
                }

                $('#penggantiId').empty();
                $('#penggantiId').append($("<option></option>")
                        .attr("value","-")
                        .text("-"));
                $.each(listdata, function (i, item) {
                    $('#penggantiId').append($("<option></option>")
                            .attr("value",item.nip)
                            .text(item.personName));
                });
            });
        }
    }

    window.listPosisi = function(branch, divisi){
        var branch = document.getElementById("branchBaruId1").value;
        var divisi = document.getElementById("divisiBaruId2").value;

        $('#positionBaruId1').empty();
        PositionAction.searchPositionMutasi(branch, divisi, function(listdata){
            $.each(listdata, function (i, item) {
                $('#positionBaruId1').append($("<option></option>")
                        .attr("value",item.positionId)
                        .text(item.positionName));
            });
        });
    }

    window.cekStatusMutasi = function(){
        var status = document.getElementById("statusMutasi").value;

        var branch = document.getElementById("branchBaruId1").value;
        var divisi = document.getElementById("divisiBaruId2").value;
        var posisi = document.getElementById("positionBaruId1").value;

        if(status == 'R' || status == 'P' || status == 'MH'){
            $( "#branchBaruId1" ).prop( "disabled", true );
            $( "#divisiBaruId2" ).prop( "disabled", true );
            $( "#positionBaruId1" ).prop( "disabled", true );
            $( "#pjsBaru" ).prop( "disabled", true );
            $("#penggantiId").prop("disabled", true);
            $("#tipeMutasi").prop("disabled", true);
            $("#golonganBaruId1").prop("disabled", true);
        }else{
            $( "#branchBaruId1" ).prop( "disabled", false);
            $( "#divisiBaruId2" ).prop( "disabled", false);
            $( "#positionBaruId1" ).prop( "disabled",false);
            $( "#golonganBaruId1" ).prop( "disabled",false);
            $( "#pjsBaru" ).prop( "disabled",false);
            $("#penggantiId").prop("disabled", false);
            $("#tipeMutasi").prop("disabled", false);
        }
    }

    window.cekPjsLama = function(){
        if($('#pjsLama').is(":checked")){
            $("#txtPjsLama").val("Y");
        }else{
            $("#txtPjsLama").val("N");
        }
    }

    window.cekPjsBaru = function(){
        if($('#pjsBaru').is(":checked")){
            $("#txtPjsBaru").val("Y");
        }else{
            $("#txtPjsBaru").val("N");
        }
    }

    window.cekDataMutasi = function(){
        var hasil = "";
        MutasiAction.cekDataMutasi(function(listdata){
            hasil = listdata;
        });
        return hasil;
    }

    var branc = '' ;
    var dev = '';
</script>




