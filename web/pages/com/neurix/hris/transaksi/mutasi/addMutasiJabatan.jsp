
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
        $.subscribe('errorDialog', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
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
                        var rowCount = $('.sppdPersonTable tr').length;
                        if (rowCount>1){
                            if (confirm('Do you want to save this record?')) {
                                event.originalEvent.options.submit = true;
                                $.publish('showDialog');
                            } else {
                                event.originalEvent.options.submit = false;
                            }
                        } else{
                            event.originalEvent.options.submit = false;
                            var msg = "";
                            msg += '<strong>Daftar pegawai yang akan di mutasi / nonaktifkan masih kosong</strong>.' + '<br/>';
                            document.getElementById('errorValidationMessage').innerHTML = msg;
                            $.publish('showErrorValidationDialog');
                        }
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
            Add Mutasi / Nonaktif Jabatan
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Add Form</h3>
                    </div>
                    <div class="box-body">
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

                                    <s:form id="mutasiForm" method="post"  theme="simple" namespace="/mutasi" action="saveMutasi_mutasi.action" cssClass="form-horizontal">
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
                                            Daftar Pegawai
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
                                                                <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                                           closeTopics="closeDialog" modal="true"
                                                                           resizable="false"
                                                                           height="250" width="600" autoOpen="false"
                                                                           title="Searching ...">
                                                                    Please don't close this window, server is processing your request ...
                                                                    <br>
                                                                    <center>
                                                                        <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                                             src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                                             name="image_indicator_write">
                                                                        <br>
                                                                        <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                                             src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                                         save    name="image_indicator_write">
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
                        <label class="control-label col-sm-4" >Status : </label>
                        <div class="col-sm-8">
                            <s:action id="comboStatusMutasi" namespace="/statusMutasi" name="initComboStatusMutasi_statusMutasi"/>
                            <s:select list="#comboStatusMutasi.listOfComboStatusMutasi" id="statusMutasi" listKey="statusMutasiId"
                                      listValue="statusMutasiName" headerKey="" headerValue="[Select One]"
                                      cssClass="form-control form-add" onchange="cekStatusMutasi()"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >NIP : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control form-add" id="nip1" name="nip1" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip form-add" id="nip2" name="nip" onchange="changeTipeMutasi()">
                            <input style="display: none" type="text" class="form-control" id="nipOld" name="nip1">
                        </div>
                        <script type='text/javascript'>
                            var functions, mapped;
                            $('#nip2').typeahead({
                                minLength: 1,
                                source: function (query, process) {
                                    $('#nip1').val("");
                                    functions = [];
                                    mapped = {};
                                    var data = [];
                                    dwr.engine.setAsync(false);
                                    MutasiAction.getListPersonilByNameAndBranch(query,'', function (listdata) {
                                        data = listdata;
                                    });
                                    if (data.length!=0){
                                        $.each(data, function (i, item) {
                                            var labelItem = item.namaPegawai +" - "+ item.positionName;
                                            mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem, nama : item.namaPegawai, branchId : item.branch,
                                                divisiId: item.divisi, positionId : item.positionId, pjs : item.pjs, golongan:item.golongan, profesiId:item.profesiId, tipePegawai:item.tipePegawai };
                                            functions.push(labelItem);
                                        });
                                        process(functions);
                                    }else{
                                        alert("Nama tidak ditemukan");
                                        $('#nip1').val("");
                                        $('#nip2').val("");
                                        $('#positionLamaId1').val("").change();
                                        $('#branchLamaId1').val("").change();
                                        $('#divisiLamaId1').val("").change();
                                        $('#profesiLamaId1').val("").change();
                                    }
                                },
                                updater: function (item) {
                                    var selectedObj = mapped[item];
                                    console.log(selectedObj);
//                                    var namaAlat = selectedObj.label;
                                    document.getElementById("nip1").value = selectedObj.id;
                                    $('#branchLamaId1').val(selectedObj.branchId).change();
                                    $('#branchBaruId1').val(selectedObj.branchId).change();
                                    $('#divisiLamaId1').val(selectedObj.divisiId).change();
                                    $('#positionLamaId1').val(selectedObj.positionId).change();
                                    $('#profesiLamaId1').val(selectedObj.profesiId).change();
                                    $('#divisiBaruId2').val(selectedObj.divisiId);
                                    $('#positionBaruId1').val(selectedObj.positionId);
                                    $('#profesiBaruId1').val(selectedObj.profesiId);
                                    checkAndReturnListPositionPengganti();
                                    return selectedObj.nama;
                                }
                            });

                        </script>
                    </div>

                    <div id="panel-awal">
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="branchLamaId1">Unit Lama : </label>
                            <div class="col-sm-8">
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchLamaId1" name="mutasi.branchLamaId" disabled="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control form-add"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="divisiLamaId1">Divisi/Bidang Lama : </label>
                            <div class="col-sm-8">
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiLamaId1" name="mutasi.divisiLamaId" disabled="true"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control form-add" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="positionLamaId1">Posisi Lama : </label>
                            <div class="col-sm-8">
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select list="#comboPosition.listOfComboPosition" id="positionLamaId1" name="mutasi.positionLamaId" disabled="true"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control form-add"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Profesi Lama : </label>
                            <div class="col-sm-8">
                                <s:action id="comboProfesi" namespace="/profesi" name="searchProfesi_profesi"/>
                                <s:select list="#comboProfesi.listComboProfesi" id="profesiLamaId1" name="mutasi.profesiLamaId" disabled="true"
                                          listKey="profesiId" listValue="profesiName" headerKey="" headerValue="" cssClass="form-control form-add" />
                            </div>
                        </div>
                    </div>

                    <div class="form-group" id="panel_tanggal_keluar" style="display: none">
                        <label class="control-label col-sm-4" >Tanggal Keluar : </label>
                        <div class="col-sm-8">
                            <s:textfield id="tanggalKeluar" cssClass="form-control pull-right"
                                         required="false" cssStyle=""/>
                        </div>
                    </div>

                    
                    <div id="panel-target" style="display: none;">
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="branchBaruId1">Unit Baru : </label>
                            <div class="col-sm-8">
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchBaruId1" name="mutasi.branchBaruId" onchange="listDivisi()"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control form-add"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="divisiBaruId2">Divisi/Bidang Baru : </label>
                            <div class="col-sm-8">
                                <s:action id="comboDivisi" namespace="/department"
                                          name="searchDepartment2_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiBaruId2"
                                          name="mutasi.divisiBaruId" onchange="listPosisi()"
                                          listKey="departmentId" listValue="departmentName"
                                          headerKey="" headerValue=""
                                          cssClass="form-control form-add"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Posisi Baru : </label>
                            <div class="col-sm-8">
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select list="#comboPosition" id="positionBaruId1" name="mutasi.positionBaruId" onchange="checkPositionAktif()"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control form-add"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" >Profesi Baru : </label>
                            <div class="col-sm-8">
                                <s:action id="comboProfesi" namespace="/profesi" name="searchProfesi_profesi"/>
                                <s:select list="#comboProfesi.listComboProfesi" id="profesiBaruId1" name="mutasi.profesiBaruId"
                                          listKey="profesiId" listValue="profesiName" headerKey="" headerValue="" cssClass="form-control form-add" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">Pegawai Aktif : </label>
                            <div class="col-sm-8" id="panel-person-aktif">
                                <%--<s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>--%>
                                <%--<select class="form-control form-add" id="penggantiId" name="mutasi.penggantiNip"></select>--%>
                            </div>
                            <input type="hidden" id="flag-person-aktif" value="N">
                        </div>
                    </div>

                    <div id="panel_jenis_jabatan">
                        <div class="form-group">
                            <label class="control-label col-sm-4" > Jenis Jabatan : </label>
                            <div class="col-sm-8">
                                <s:action id="comboJenisPegawai" namespace="/jenisPegawai" name="initComboJenisPegawai_jenisPegawai"/>
                                <s:select list="#comboJenisPegawai.listOfComboJenisPegawai" id="jenisPegawaiId" name="biodata.jenisPegawai"
                                          listKey="jenisPegawaiId" listValue="jenisPegawaiName" headerKey="" headerValue="" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4" > Digaji : </label>
                            <div class="col-sm-8">
                                <s:select list="#{'N':'Tidak'}" id="flagDigaji"
                                          headerKey="Y" headerValue="Ya" cssClass="form-control" />
                            </div>
                        </div>
                    </div>

                    <div id="panel_position_pengganti" style="display: none">
                        <div class="form-group">
                            <label class="control-label col-sm-4" > Pengganti Posisi Utama : </label>
                            <div class="col-sm-8">
                                <select class="form-control" id="sel_position_pengganti">

                                </select>
                            </div>
                        </div>
                    </div>
                    <div id="panel_sk" style="display: none">
                        <div class="form-group">
                            <label class="control-label col-sm-4" > No. SK : </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="no_sk"/>
                            </div>
                        </div>
                    </div>
                    <div id="panel_ket_resign" style="display: none">
                        <div class="form-group">
                            <label class="control-label col-sm-4" > Ket. Pengunduran : </label>
                            <div class="col-sm-8">
                                <select class="form-control" id="sel_ket_resign">
                                    <option value="pengunduran_diri">Pengunduran Diri</option>
                                    <option value="diberhentikan">Diberhentikan</option>
                                    <option value="pensiun_dini">Pensiun Dini</option>
                                    <option value="meninggal">Meninggal</option>
                                </select>
                            </div>
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
    window.changePegawai = function (id) {
        if (id == "TP01") {
            $('#golongan1Group').show();
            $('#golongan2Group').hide();
            $('#golonganBaru1Group').show();
            $('#golonganBaru2Group').hide();
        } else {
            $('#golongan1Group').hide();
            $('#golongan2Group').show();
            $('#golonganBaru1Group').hide();
            $('#golonganBaru2Group').show();
        }
    };

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
        $('.sppdPersonTable').find('tbody').remove();
        $('.sppdPersonTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        MutasiAction.searchMutasiPerson(function(listdata){
            tmp_table = "<thead style='font-size: 10px;' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #90ee90'>No</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>NIP</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Nama</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Status</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Unit Lama</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Divisi/Bidang Lama</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Jabatan Lama</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Profesi Lama</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Unit Baru</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Divisi/Bidang Baru</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Jabatan Baru</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Profesi Baru</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Jenis Jabatan</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Digaji</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Delete</th>"+
                "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 10px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td >' + item.nip + '</td>' +
                        '<td align="center">' + item.nama+ '</td>' +
                        '<td align="center" style="font-weight: bold;">' + item.statusName+ '</td>' +
                        '<td align="center">' + item.branchLamaName+ '</td>' +
                        '<td align="center">' + item.divisiLamaName+ '</td>' +
                        '<td align="center">' + item.positionLamaName+ '</td>' +
                        '<td align="center">' + item.profesiLamaName+ '</td>' +
                     '<td align="center">' + item.branchBaruName+ '</td>' +
                        '<td align="center">' + item.divisiBaruName+ '</td>' +
                        '<td align="center">' + item.positionBaruName+ '</td>' +
                        '<td align="center">' + item.profesiBaruName+ '</td>' +
                        '<td align="center">' + item.jenisPegawaiName+ '</td>' +
                        '<td align="center">' + labeledWhiteFlag(item.flagDigaji)+ '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='"+item.nip+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        "</tr>";
            });
            $('.sppdPersonTable').append(tmp_table);
        });
    }

    function labeledWhiteFlag(flag){
        if (flag == "Y")
            return "<div class='label label-success'><i class='fa fa-check'></i></div>";
        return "";
    }

    $(document).ready(function() {
        loadPerson();


        $('#tanggalEfektif').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#tanggalKeluar').datepicker({
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            changeYear: true
        });

        $('#btnAddMutasi').click(function(){
            cekStatusMutasi();
            $('#nip2').prop("readonly", true);
            $('#statusMutasi').removeAttr("disabled");
            $('#myForm')[0].reset();
            $("#btnSave").html('Save');
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'addPerson');
            $('.form-add').val("");
            $('#modal-edit').find('.modal-title').text('Add Mutasi / Nonaktif');
            setPanelAlert("")
            $("#flag-person-aktif").val("N");
        });
    });


    $('.sppdPersonTable').on('click', '.item-edit', function(){
        $("#nip2").attr("readonly", false);

        var nip = $(this).attr('data');
        MutasiAction.searchMutasiPersonEdit(nip ,function(listdata) {
            $.each(listdata, function (i, item) {

                $('#statusMutasi').val(item.status);
                $('#statusMutasi').prop("disabled", true);
                $('#tipeMutasi').val(item.tipeMutasi);
                $('#tipeMutasi').prop("disabled", true);
                $('#nip1').val(item.nip);
                $('#nip2').val(item.nama);
                $('#nipOld').val(item.nip);

                $('#branchLamaId1').val(item.branchLamaId).change();
                $('#positionLamaId1').val(item.positionLamaId).change();
                $('#divisiLamaId1').val(item.divisiLamaId).change();

                console.log(item.tipePegawai);
                if (item.tipePegawai == 'TP01'){
                    $('#golonganLamaId1').val(item.levelLama).change();
                }else {
                    $('#golonganLamaId2').val(item.levelLama).change();
                }
                $('#golonganBaruId1').val(item.levelBaru).change();

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

                $('#profesiLamaId1').val(item.profesiLamaId).change();
                $('#profesiBaruId1').val(item.profesiBaruId).change();

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
                $('#tipeMutasi').val(item.tipeMutasi);
                $('#tipeMutasi').prop("disabled", true);
                $('#nip1').val(item.nip);
                $('#nip2').val(item.nama);

                $('#branchLamaId1').val(item.branchLamaId).change();
                $('#positionLamaId1').val(item.positionLamaId).change();
                $('#divisiLamaId1').val(item.divisiLamaId).change();
                $('#profesiBaruId1').val(item.profesiId).change();
                $('#golonganBaruId1').val(item.levelBaru).change();

                //listPosisi(item.branchBaruId, item.divisiBaruId);
                $('#branchBaruId1').val(item.branchBaruId).change();
                $('#divisiBaruId2').val(item.divisiBaruId).change();
                $('#positionBaruId1').val(item.positionBaruId).change();
            });
        });

        $("#nip2").prop("readonly", true);
        $("#branchLamaId1").prop("disabled", true);
        $("#golonganLamaId1").prop("disabled", true);
        $("#golonganBaruId1").prop("disabled", true);
        $("#positionLamaId1").prop("disabled", true);
        $("#divisiLamaId1").prop("disabled", true);
        $("#branchBaruId1").prop("disabled", true);
        $("#positionBaruId1").prop("disabled", true);
        $("#divisiBaruId2").prop("disabled", true);
        $("#profesiBaruId1").prop("disabled", true);
        $("#penggantiId").prop("disabled", true);
        $("#pjsBaru").prop("disabled", true);
        $("#btnSave").html('Delete');
        $('#modal-edit').find('.modal-title').text('Delete Data Anggota');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'deletePerson');
        $("#panel_tanggal_keluar").hide();
        $("#panel_sk").hide();
        $("#panel_ket_resign").hide();
        $("#panel_jenis_jabatan").hide();
        $("#panel-target").hide();

    });

    function save(){
        var url = $('#myForm').attr('action');

        var nip         = document.getElementById("nip1").value;
        var personName  = document.getElementById("nip2").value;
        var nipOld      = document.getElementById("nipOld").value;

        var branchLamaId    = document.getElementById("branchLamaId1").value;
        var divisiLamaId    = document.getElementById("divisiLamaId1").value;
        var positionLamaId  = document.getElementById("positionLamaId1").value;

        var branchLamaName      = $('#branchLamaId1 option:selected').text();
        var divisiLamaName      = $('#divisiLamaId1 option:selected').text();
        var positionLamaName    = $('#positionLamaId1 option:selected').text();

        var branchBaruId        = document.getElementById("branchBaruId1").value;
        var divisiBaruId        = document.getElementById("divisiBaruId2").value;
        var status              = document.getElementById("statusMutasi").value;

        var branchBaruName      = $('#branchBaruId1 option:selected').text();
        var divisiBaruName      = $('#divisiBaruId2 option:selected').text();

        var profesiLamaId       = document.getElementById("profesiLamaId1").value;
        var profesiLamaName     = $('#profesiLamaId1 option:selected').text();
        var profesiBaruId       = document.getElementById("profesiBaruId1").value;
        var profesiBaruName     = $('#profesiBaruId1 option:selected').text();
        var jenisPegawaiId      = $("#jenisPegawaiId option:selected").val();
        var jenisPegawaiName    = $("#jenisPegawaiId option:selected").text();
        var flagDigaji          = $("#flagDigaji option:selected").val();
        var flagPersonAktif     = $("#flag-person-aktif").val();
        var tanggalKeluar       = $("#tanggalKeluar").val();
        var noSk                = $("#no_sk").val();
        var idKet               = $("#sel_ket_resign option:selected").val();
        var txtKet              = $("#sel_ket_resign option:selected").text();

        // jika status lepas maka ada pengganti
        var positionBaruId      = "";
        var positionBaruName    = "";
        var positionPengganti   = "";
        if (status == "L" || status == "RS"){
            positionBaruId      = $("#sel_position_pengganti option:selected").val();
            if (positionBaruId == "" || positionBaruId == null)
                positionBaruId  = positionLamaId;

            positionBaruName    = $("#sel_position_pengganti option:selected").text();
            if (positionBaruName == "" || positionBaruName == null)
                positionBaruName = positionLamaName;

            positionPengganti   = $("#sel_position_pengganti option:selected").val();
            if (positionPengganti == "" || positionPengganti == null){
                positionPengganti = positionBaruId;
                flagDigaji = "";
            }

        } else {
            positionBaruId      = document.getElementById("positionBaruId1").value;
            positionBaruName    = $("#positionBaruId1 option:selected").text();
        }
        // END

        if (personName !='' && branchLamaId !='' && status!='') {
            if(url == 'addPerson'){

                var isOk = true;
                if (status == "M" || status == "R" || status == "RA"){
                    if (flagPersonAktif == "Y"){
                        isOk = false;
                    }
                }

                if (isOk){
                    var objadd = {
                        nip : nip,
                        personname : personName,
                        branchlamaid : branchLamaId,
                        branchlamaname : branchLamaName,
                        divisilamaid : divisiLamaId,
                        divisilamaname : divisiLamaName,
                        positionlamaid : positionLamaId,
                        positionlamaname : positionLamaName,
                        branchbaruid : branchBaruId,
                        branchbaruname : branchBaruName,
                        divisibaruid : divisiBaruId,
                        divisibaruname : divisiBaruName,
                        positionbaruid : positionBaruId,
                        poisitionbaruname : positionBaruName,
                        status : status,
                        profesilamaid : profesiLamaId,
                        profesilamaname : profesiLamaName,
                        profesibaruid : profesiBaruId,
                        profesibaruname : profesiBaruName,
                        jenispegawai : jenisPegawaiId,
                        jenispegawainame : jenisPegawaiName,
                        flagdigaji : flagDigaji,
                        positionPengganti : positionPengganti,
                        tanggalKeluar : tanggalKeluar,
                        nosk : noSk,
                        idket : idKet,
                        txtket : txtKet
                    };

                    var stobj = JSON.stringify(objadd);

                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        MutasiAction.saveAnggotaAdd(stobj, function(result) {
                            if(result==""){
                                alert('Data Successfully Added');
                                $('#modal-edit').modal('hide');
                                $('#myForm')[0].reset();
                                loadPerson();
                            }else{
                                alert(result);
                            }
                        });
                    }
                }
            } else{
                if (confirm('Are you sure you want to delete this Record?')) {
                    MutasiAction.saveAnggotaDelete(nip, function (listdata) {
                        $('#modal-edit').modal('hide');
                        $('#myForm')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadPerson();
                    });
                }
            }
        }else{
            var msg="";
            if (personName==""){
                msg+="Nama pegawai masih kosong \n";
            }
            if (branchLamaId=="") {
                msg+="Branch masih kosong \n";
            }
            if (status=="") {
                msg+="Status masih belum dipilih \n";
            }
            alert(msg);
        }
    }

    $('#btnSave').click(function(){
        var nip             = $("#nip1").val();
        var status          = $("#statusMutasi").val();
        var positionIdLama  = $("#positionLamaId1").val();
        var jenisJabatan    = $("#jenisPegawaiId option:selected").val();
        var noSk            = $("#no_sk").val();
        var tanggalKeluar   = $("#tanggalKeluar").val();
        var url             = $('#myForm').attr('action');

        if (url == "addPerson"){
            MutasiAction.checkIsAvailInSession(nip, function(res){
                if (res.status == "error"){
                    // jika tidak ditemukan nip yng sudah terdaftar akan dimutasi
                    alert(res.msg);
                } else {
                    if (status == "L"){
                        if (noSk == null || noSk == "") {
                            alert("No.SK harus diisi");
                        } else {
                            MutasiAction.getListPositionJabatanLain(positionIdLama, nip, function (positions) {
                                if (positions.length == 0){
                                    alert("Tidak Ditemukan Posisi Lain dari nip ini. \n tidak bisa lepas jabatan. \n");
                                } else {
                                    save();
                                }
                            });
                        }
                    } else if (status == "RA"){
                        if (noSk == null || noSk == ""){
                            alert("No.SK harus diisi");
                        } else {
                            MutasiAction.checkIsAvailJabatanUtama(nip, jenisJabatan, function (avail) {
                                if (avail == true){
                                    alert("Sudah Ada Posisi Utama Aktif dari nip ini. \n pilih jenis jabatan yang lain. \n");
                                } else {
                                    save();
                                }
                            });
                        }

                    } else if (status == "M" || status == "R"){
                        if (noSk == null || noSk == ""){
                            alert("No.SK harus diisi");
                        } else {
                            save();
                        }
                    } else if (status == "RS"){
                        console.log("tanggal keluar didalam if : " + tanggalKeluar);
                        console.log("status didalam if : " + status);

                        if (tanggalKeluar == null || tanggalKeluar == ""){
                            alert("Tanggal Keluar harus diisi");
                        } else {

                            var stDateNow               = getStDateNow();
                            var tanggalKeluarSplited    = splitWithoutStripDate(tanggalKeluar);

                            console.log("date now : " + stDateNow);
                            console.log("tanggal keluar : " + tanggalKeluarSplited);

                            if (parseInt(tanggalKeluarSplited) > parseInt(stDateNow)){
                                alert("Tanggal tidak boleh setelah tanggal sekarang !");
                            } else {
                                save();
                            }
                        }
                    } else {
                        console.log("tanggal keluar didalam if : " + tanggalKeluar);
                        console.log("status didalam if : " + status);
                        save();
                    }
                }
            });
        } else {
            save();
        }

    });

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

        if(status == 'M' || status == 'R' || status == "RA"){

            $("#panel_sk").show();
            $("#panel_jenis_jabatan").show();
            $("#panel_ket_resign").hide();
            if (status == "RA"){
                $("#panel-target").show();
                $("#panel-awal").hide();
            } else {
                $("#panel-target").show();
                $("#panel-awal").show();
            }

            $('#nip2').prop("readonly", false);
            if (status=='M'){
                $( "#branchBaruId1" ).prop( "disabled", false);
            } else{
                $( "#branchBaruId1" ).prop( "disabled", true );
            }
            $( "#divisiBaruId2" ).prop( "disabled", false);
            $( "#positionBaruId1" ).prop( "disabled",false);
            $( "#profesiBaruId1" ).prop( "disabled",false);
            $( "#pjsBaru" ).prop( "disabled",false);
            $("#penggantiId").prop("disabled", false);
            $("#tipeMutasi").prop("disabled", false);
            $("#panel_tanggal_keluar").hide();
        }else{

            if (status == "L" || status == "RS"){
                $("#panel_jenis_jabatan").hide();
                if (status == "RS"){
                    $("#panel_tanggal_keluar").show();
                    $("#panel_sk").hide();
                    $("#panel_ket_resign").show();
                } else {
                    $("#panel_tanggal_keluar").hide();
                    $("#panel_sk").show();
                    $("#panel_ket_resign").hide();
                }
            }

            $("#panel-awal").show();
            $("#panel-target").hide();

            if (status==""){
                $('#nip2').prop("readonly", true);
            } else{
                $('#nip2').prop("readonly", false);
            }
            $( "#branchBaruId1" ).prop( "disabled", true );
            $( "#divisiBaruId2" ).prop( "disabled", true );
            $( "#positionBaruId1" ).prop( "disabled", true );
            $( "#pjsBaru" ).prop( "disabled", true );
            $( "#profesiBaruId1" ).prop( "disabled",true);
            $("#penggantiId").prop("disabled", true);
            $("#tipeMutasi").prop("disabled", true);
            $("#golonganBaruId1").prop("disabled", true);
        }
    }

    
    function changeTipeMutasi() {
        var tipe = $('#tipeMutasi').val();
        var status = $('#statusMutasi').val();
        if (tipe=='MT'){
            $('#branchBaruId1').prop("disabled",false);
            if (status!="M"){
                $('#branchBaruId1').prop("disabled",true);
            }
        }else {
            $('#branchBaruId1').prop("disabled",true);
            $('#branchBaruId1').val($('#branchLamaId1').val());
            listDivisi();
        }
    }

    function checkAndReturnListPositionPengganti(){
        var nip         = $("#nip1").val();
        var positionid  = $("#positionLamaId1").val();
        var status      = $("#statusMutasi").val();

        if (status == "L"){
            MutasiAction.getListOtherPosition(positionid, nip, function (res) {
                var str = "";
                if (res.length > 0){
                    $.each(res, function (i, item) {
                        str += "<option value='"+item.positionId+"'>"+item.positionName+"</option>";
                    });

                    $("#panel_position_pengganti").show();
                    $("#sel_position_pengganti").html(str);
                } else {
                    $("#panel_position_pengganti").hide();
                    $("#sel_position_pengganti").html("");
                }
            });
        } else {
            $("#panel_position_pengganti").hide();
            $("#sel_position_pengganti").html("");
        }
    }

    function checkPositionAktif(){
        console.log("checkPositionAktif");
        var branch      = $("#branchBaruId1 option:selected").val();
        var positionId  = $("#positionBaruId1 option:selected").val();
        setPanelAlert("");
        PositionAction.checkAndGetPositionAktif(positionId, branch, function (res) {
            var str = "";
            if (res.status == "error"){
                str += "<div class='alert alert-danger'>"+res.msg+"</div>";
                $("#flag-person-aktif").val("Y");
                setPanelAlert(str);
            } else {
                str += "<div class='alert alert-success' style='text-align: center'>Posisi Tersedia <i class='fa fa-check'></i></div>";
                $("#flag-person-aktif").val("N");
                setPanelAlert(str);
            }
        });
    }

    function setPanelAlert(str){
        console.log(str);
        $("#panel-person-aktif").html(str);
    }

    function getStDateNow(){
        var d = new Date();
        var month = d.getMonth()+1;
        var day = d.getDate();
        var year = d.getFullYear();
        return year+""+fixLengthForDate(month.toString())+""+fixLengthForDate(day.toString());
    }
    function splitWithoutStripDate(str){
        var res = str.split("-");
        var ln  = res.length;
        var result = "";
        for (i = 0 ; i < ln ; i++){
            result += res[i];
        }
        return result;
    }

    function fixLengthForDate(str){
        if(str.length > 1)
            return str;
        else
            return "0" + str;
    }
</script>




