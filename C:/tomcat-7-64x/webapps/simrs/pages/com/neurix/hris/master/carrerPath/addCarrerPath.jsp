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
    <script type='text/javascript' src='<s:url value="/dwr/interface/CarrerPathAction.js"/>'></script>


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

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {

            window.cekTraining = function(ck){
                var surat = "";
                if(ck.checked){
                    surat = "Y";
                    $('#divIdTraining').show();
                } else {
                    $('#divIdTraining').hide();
                    $('#idTraining').val("");
                    surat = "N";
                }

                $('#flagTraining').val(surat);
            }

            $('#cekIdTraining').click(function(){
                var id = document.getElementById("idTraining").value;
                SppdAction.cekIdTraining(id,function(listdata) {
                    $.each(listdata, function (i, item) {
                        if(item.sppdStatus == "Ketua"){
                            document.getElementById("personName1").value = item.personId;
                            document.getElementById("personName2").value = item.personSppdName;
                            document.getElementById("personName").value = item.personName;

                            $('#tanggalBerangkat').val(item.strTanggalBerangkat);
                            $('#tanggalKembali').val(item.strTanggalKembali);

                            $('#berangkatDari').val(item.sppdBerangkatDariName);//Id
                            $('#berangkatDari1').val(item.sppdBerangkatDari); //Name

                            $('#tujuanKe1').val(item.kota); //id
                            $('#tujuanKe').val(item.sppdTujuanName); //Name

                            $('#berangkatVia').val(item.sppdBerangkatVia);
                            $('#pulangVia').val(item.sppdKembaliVia);
                            $('#tujuanSppd').val(item.sppdKeperluan);

                            $('#positionId').val(item.positionId).change();
                            $('#branchId').val(item.unitId).change();
                            $('#divisiId').val(item.divisiId).change();

                            $('#positionId3').val(item.positionId).change();
                            $('#branchId3').val(item.unitId).change();
                            $('#divisiId3').val(item.divisiId).change();
                        }
                    });
                    if(listdata.length == 0){
                        alert('Training Id tidak sesuai atau masih ada peserta training yang belum di approve');
                        $('#idTraining').val("");
                    }
                    loadPendidikan();
                });
            });

            window.clos = function() {
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='initForm_sppd.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var pemberiTugas    = document.getElementById("pemberiTugas").value;
                var nip             = document.getElementById("personName1").value;
                var keperluan       = document.getElementById("tujuanSppd").value;
                var tanggalBerangkat= document.getElementById("tanggalBerangkat").value;
                var tanggalKembali  = document.getElementById("tanggalKembali").value;
                var berangkatDari   = document.getElementById("berangkatDari").value;
                var tujuanKe        = document.getElementById("tujuanKe1").value;
                var berangkatVia    = document.getElementById("berangkatVia").value;
                var pulangVia       = document.getElementById("pulangVia").value;
                var penginapan      = document.getElementById("penginapan").value;
                var flagTraining = document.getElementById("flagTraining").value;
                var idTraining  = document.getElementById("idTraining").value;

                var tang1 = tanggalBerangkat.split("-");
                var tang2 = tanggalKembali.split("-");

                SppdAction.cekAvailableSppd(nip, tanggalBerangkat, tanggalKembali, function(listdata) {
                    if(listdata == "-"){
                        if (nip != '' && keperluan != '' && tanggalBerangkat != '' && tanggalKembali != '' && berangkatDari != ''
                                && tujuanKe != '' && pemberiTugas != '') {
                            if(Date.parse(tang1[2] + '-' + tang1[1] + '-' + tang1[0]) > Date.parse(tang2[2] + '-' + tang2[1] + '-' + tang2[0])){
                                //alert('error');
                                event.originalEvent.options.submit = false;
                                var msg = "";

                                msg += '<strong>Tanggal Berangkat</strong> tidak boleh <strong>Tanggal Kembali</strong>' + '<br/>';

                                document.getElementById('errorValidationMessage').innerHTML = msg;

                                $.publish('showErrorValidationDialog');
                            }else {
                                if(flagTraining == "Y" && idTraining == ""){
                                    event.originalEvent.options.submit = false;
                                    var msg = "";

                                    msg += '<strong>Id Training tidak boleh Kosong</strong>' + '<br/>';

                                    document.getElementById('errorValidationMessage').innerHTML = msg;

                                    $.publish('showErrorValidationDialog');
                                }else{
                                    if (confirm('Do you want to save this record?')) {
                                        event.originalEvent.options.submit = true;
                                        $.publish('showDialog');
                                    } else {
                                        event.originalEvent.options.submit = false;
                                    }
                                }
                            }
                        } else {

                            event.originalEvent.options.submit = false;

                            var msg = "";

                            if (nip == '') {
                                msg += 'Field <strong>Name</strong> is required.' + '<br/>';
                            }

                            if (keperluan == '') {
                                msg += 'Field <strong>Keperluan</strong> is required.' + '<br/>';
                            }

                            if (tanggalBerangkat == '') {
                                msg += 'Field <strong>Tanggal Berangkat</strong> is required.' + '<br/>';
                            }

                            if (tanggalKembali == '') {
                                msg += 'Field <strong>Tanggal Kembali</strong> is required.' + '<br/>';
                            }

                            if (berangkatDari == '') {
                                msg += 'Field <strong>Brangkat Dari</strong> is required.' + '<br/>';
                            }


                            if (tujuanKe == '') {
                                msg += 'Field <strong>Tujuan Ke</strong> is required.' + '<br/>';
                            }

                            if (pemberiTugas == '') {
                                msg += 'Field <strong>Pemberi Tugas</strong> is required.' + '<br/>';
                            }

                            document.getElementById('errorValidationMessage').innerHTML = msg;

                            $.publish('showErrorValidationDialog');

                        }
                    }else{
                        event.originalEvent.options.submit = false;
                        alert(listdata);
                    }
                });

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
            Add Carrer Path
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="sppdForm" method="post"  theme="simple" namespace="/sppd" action="save_sppd.action" cssClass="well form-horizontal">

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
                            <tr style="display: none">
                                <td>
                                    <label class="control-label"><small>Carrer Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="carrerPathId1" name="carrerPath.carrerPathId" required="true" readonly="true" cssClass="form-control"/>
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
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId1" name="carrerPath.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Jabatan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId1" name="carrerPath.jabatanId"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Min Kerja (Th) :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield onkeyup="checkDec(this);" id="thMinKerja1" name="carrerPath.thMinKerja" required="true" cssClass="form-control"/>
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
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId1" name="carrerPath.divisiId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Min Bidang (Th):</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield onkeyup="checkDec(this);" id="thMinBidang1" name="carrerPath.thMinBidang" required="true"  cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Bagian :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                        <s:select list="#comboBagian.comboListOfPositionBagian" id="bagianId1" name="carrerPath.bagianId"
                                                  listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Min Bagian (Th) :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield onkeyup="checkDec(this);" id="thMinBagian1" name="carrerPath.thMinBagian" required="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Pendidikan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'D1':'D1', 'D2':'D2', 'D3':'D3', 'S1':'S1', 'S2':'S2', 'S3':'S3'}" id="flag" name="carrerPath.pendidikan"
                                                  headerKey="" headerValue="" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Jurusan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboJurusan" namespace="/studyJurusan" name="searchStudyJurusan_studyJurusan"/>
                                        <s:select list="#comboJurusan.comboListOfStudyJurusan" id="jurusanId1" name="carrerPath.jurusanIdAdd"
                                                  listKey="jurusanId" listValue="jurusanName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Kelompok Posisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboKelompok" namespace="/kelompokPosition" name="searchKelompok_kelompokPosition"/>
                                        <s:select list="#comboKelompok.comboListOfKelompokPosition" id="kelompokId1" name="carrerPath.kelompokPositionId"
                                                  listKey="kelompokId" listValue="kelompokName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Golongan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboGolongan" namespace="/golongan" name="initComboGolongan_golongan"/>
                                        <s:select list="#comboGolongan.listComboGolongan" id="golonganId1" name="carrerPath.golonganId"
                                                  listKey="golonganId" listValue="golonganName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Keterangan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textarea rows="2" id="keterangan1" name="carrerPath.keterangan" required="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                        </table>



                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit targets="crsud" type="button" cssClass="btn btn-primary" formIds="sppdForm" id="save" name="save"
                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                            <i class="fa fa-check"></i>
                                            Save
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="add_sppd.action"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <h3>
                            Pendidikan Formal
                            <button
                                    id="btnAddPendidikan" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
                            </button>
                        </h3>
                        <center>
                            <table id="showdata" width="100%">
                                <tr>
                                    <td align="center">
                                        <table style="width: 50%;" class="carrerPathPendidikan table table-bordered">
                                        </table>
                                    </td>
                                </tr>
                            </table>

                        </center>

                        <br>
                        <br>
                        <h3>
                            Jalur Karir
                            <button
                                    id="btnAddKarir" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
                            </button>
                        </h3>
                        <center>
                            <table id="showdataKarir" width="100%">
                                <tr>
                                    <td align="center">
                                        <table style="width: 80%;" class="carrerPathJalurKarir table table-bordered">
                                        </table>
                                    </td>
                                </tr>
                            </table>

                        </center>

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
    <div class="modal-dialog" style="width:400px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormPendidikan">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiId1">Pendidikan :</label>
                        <div class="col-sm-8">
                            <input type="text" id="carrerPathId" class="form-control pull-right"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiId1">Pendidikan :</label>
                        <div class="col-sm-8">
                            <s:select list="#{'D1':'D1', 'D2':'D2', 'D3':'D3', 'S1':'S1', 'S2':'S2', 'S3':'S3'}" id="pendidikanId" name="carrerPath.pendidikan"
                                      cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiId1">Jurusan :</label>
                        <div class="col-sm-8">
                            <s:action id="comboJurusan" namespace="/studyJurusan" name="searchStudyJurusan_studyJurusan"/>
                            <s:select list="#comboJurusan.comboListOfStudyJurusan" id="jurusanId" name="carrerPath.jurusanIdAdd"
                                      listKey="jurusanId" listValue="jurusanName" cssClass="form-control"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSavePendidikan" type="button" class="btn btn-default btn-success">Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</html>

<script>

    window.loadPendidikan =  function(){
        //alert(nip);
        $('.carrerPathPendidikan').find('tbody').remove();
        $('.carrerPathPendidikan').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        CarrerPathAction.searchCarrerPendidikan(function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tingkat Pendidikan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jurusan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + item.pendidikanName + '</td>' +
                        '<td align="center">' + item.jurusanName + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' id ='"+item.carrerPathId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' id ='"+item.carrerPathId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        "</tr>";
            });
            $('.carrerPathPendidikan').append(tmp_table);
        });
    }

    window.loadKarir =  function(){
        //alert(nip);
        $('.carrerPathJalurKarir').find('tbody').remove();
        $('.carrerPathJalurKarir').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        CarrerPathAction.searchCarrerPendidikan(function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Jalur Karir</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Masa Kerja (Th)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bidang</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Lama Bidang (Th)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bagian </th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Lama Bagian (Th)</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + item.pendidikanName + '</td>' +
                        '<td align="center">' + item.pendidikanName + '</td>' +
                        '<td align="center">' + item.pendidikanName + '</td>' +
                        '<td align="center">' + item.pendidikanName + '</td>' +
                        '<td align="center">' + item.pendidikanName + '</td>' +
                        '<td align="center">' + item.pendidikanName + '</td>' +
                        '<td align="center">' + item.jurusanName + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' id ='"+item.carrerPathId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' id ='"+item.carrerPathId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        "</tr>";
            });
            $('.carrerPathJalurKarir').append(tmp_table);
        });
    }

    $(document).ready(function() {

        loadPendidikan();
        loadKarir();

        $('#tanggalBerangkat').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#tanggalKembali').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#tanggalBerangkatAnggota').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('.tanggalKembaliAnggota').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#btnAddPendidikan').click(function(){

            $('#myFormPendidikan')[0].reset();
            $('#modal-edit').modal('show');
            $('#myFormPendidikan').attr('action', 'addPendidikan');
            $('#modal-edit').find('.modal-title').text('Add Pendidikan Formal');
        });

    });

    $('.carrerPathPendidikan').on('click', '.item-edit', function(){
        var id = $(this).attr('id');

        CarrerPathAction.cekSessionPendidikan(id ,function(listdata) {
            $('#carrerPathId').val(id);
            $('#pendidikanId').val(listdata.pendidikan).change();
            $('#jurusanId').val(listdata.jurusanId).change();

        });

        $('#modal-edit').find('.modal-title').text('Edit Data');
        $('#modal-edit').modal('show');
        $("#btnSavePendidikan").html('Save Edit');
        $('#myFormPendidikan').attr('action', 'editPendidikan');
    });

    $('.carrerPathPendidikan').on('click', '.item-delete', function(){
        var id = $(this).attr('id');

        CarrerPathAction.cekSessionPendidikan(id ,function(listdata) {
            $('#carrerPathId').val(id);
            $('#pendidikanId').val(listdata.pendidikan).change();
            $('#jurusanId').val(listdata.jurusanId).change();

        });

        $('#modal-edit').find('.modal-title').text('Hapus Data');
        $('#modal-edit').modal('show');
        $("#btnSavePendidikan").html('Delete');
        $('#myFormPendidikan').attr('action', 'deletePendidikan');

    });

    $('#btnSavePendidikan').click(function(){
        var url = $('#myFormPendidikan').attr('action');
        var data = $('#myFormPendidikan').serialize();

        var pendidikanId = document.getElementById("pendidikanId").value;
        var jurusan = document.getElementById("jurusanId").value;
        var jurusanName = $("#jurusanId option:selected").text();
        var pendidikanName = $("#pendidikanId option:selected").text();

        var id = pendidikanId + "-" + jurusan;

        if(url == 'addPendidikan'){
            CarrerPathAction.cekSessionPendidikan(id, function(listdata) {
                if(listdata == null){
                    if (confirm('Are you sure you want to save this Record?')) {
                        CarrerPathAction.saveAddPendidikan(id, pendidikanId, pendidikanName, jurusan, jurusanName, function(listdata) {
                            alert('Data Successfully Added');
                            $('#modal-edit').modal('hide');
                            $('#myFormPendidikan')[0].reset();
                            loadPendidikan();
                        });
                    }
                }else{
                    alert("Data Sudah Ada");
                }
            });

        }else if(url == 'editPendidikan'){
            id = document.getElementById("carrerPathId").value;
            if (confirm('Are you sure you want to save this Record?')) {
                CarrerPathAction.saveEditPendidikan(id, pendidikanId, pendidikanName, jurusan, jurusanName, function(listdata) {
                    alert('Data Successfully Added');
                    $('#modal-edit').modal('hide');
                    $('#myFormPendidikan')[0].reset();
                    loadPendidikan();
                });
            }
        }else{
            if (confirm('Are you sure you want to delete this Record?')) {
                id = document.getElementById("carrerPathId").value;
                CarrerPathAction.saveDeletePendidikan(id, function(listdata) {
                    alert('Data Successfully Added');
                    $('#modal-edit').modal('hide');
                    $('#myFormPendidikan')[0].reset();
                    loadPendidikan();
                });
            }
        }
    });

    var branc = '' ;
    var dev = '';
</script>
