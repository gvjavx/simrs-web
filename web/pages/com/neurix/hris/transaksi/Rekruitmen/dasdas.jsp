
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <%--     <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
        <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenAction.js"/>'></script>
        <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>--%>

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
        #tahunAwal{z-index: 2000!important}
        #tahunAkhir{z-index: 2000!important}
    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_rekruitmen'/>";
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
                window.location.href="<s:url action='add_rekruitmen.action'/>";
            };

            $.subscribe('beforeProcessSave154', function (event, data) {
                if (1==1) {
                    if(1>5){
                        //alert('error');
                        event.originalEvent.options.submit = false;
                        var msg = "";

                        document.getElementById('errorValidationMessage').innerHTML = msg;

                        $.publish('showErrorValidationDialog');
                    }else {
                        if (confirm('Do you want to save this record?')) {
                            event.originalEvent.options.submit = true;
                            $.publish('showDialog');
                        } else {
                            event.originalEvent.options.submit = false;
                        }
                    }
                } else {

                    event.originalEvent.options.submit = false;

                    var msg = "";

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
            Add Rekruitmen
            <small>e-HEALTH</small>
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
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="rekruitmenForm" method="post"  theme="simple" namespace="/rekruitmen" action="save_rekruitmen.action" cssClass="form-horizontal">

                                    <s:hidden name="addOrEdit"/>
                                    <s:hidden name="delete"/>

                                    <table>
                                        <tr>
                                            <td width="10%" align="center">
                                                <%@ include file="/pages/common/message.jsp" %>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <table>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Calon Pegawai Id :</small></label>
                                            </td>
                                            <td>
                                                <div class="input-group">
                                                    <div class="input-group-addon">
                                                        <div id="CPID">
                                                            CAPEG18
                                                        </div>
                                                    </div>
                                                    <s:textfield id="calonPegawaiId" name="rekruitmen.calonPegawaiId" required="false" readonly="false" cssClass="form-control"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Nama Calon Pegawai :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="calonPegawaiName" name="rekruitmen.namaCalonPegawai" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Gelar Depan :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="gelarDepan" name="rekruitmen.gelarDepan" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Gelar Belakang :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="gelarBelakang" name="rekruitmen.gelarBelakang" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>No. HP :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <div class="input-group">
                                                        <div class="input-group-addon">
                                                            <div id="notelpawal">
                                                                +62
                                                            </div>
                                                        </div>
                                                        <s:textfield id="noTelp" name="rekruitmen.noTelp" required="false" readonly="false" cssClass="form-control"/>
                                                    </div>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Alamat :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textarea rows="4"  id="tujuanRekruitmen" name="rekruitmen.tugasRekruitmen" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Provinsi :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="provinsi" name="" required="true" disabled="false" cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none" id="provinsi11" name="rekruitmen.provinsi" required="true" disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <script type='text/javascript'>
                                            var functions, mapped;
                                            $('#provinsi').typeahead({
                                                minLength: 1,
                                                source: function (query, process) {
                                                    functions = [];
                                                    mapped = {};

                                                    var data = [];
                                                    dwr.engine.setAsync(false);
                                                    ProvinsiAction.initComboProvinsi(query, function (listdata) {
                                                        data = listdata;
                                                    });

                                                    $.each(data, function (i, item) {
                                                        var labelItem = item.provinsiName;
                                                        mapped[labelItem] = { id: item.provinsiId, label: labelItem };
                                                        functions.push(labelItem);
                                                    });

                                                    process(functions);
                                                },
                                                updater: function (item) {
                                                    var selectedObj = mapped[item];
                                                    var namaAlat = selectedObj.label;
                                                    document.getElementById("provinsi11").value = selectedObj.id;
                                                    prov = selectedObj.id ;
                                                    return namaAlat;
                                                }
                                            });
                                        </script>


                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Kabupaten :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="kabupaten" name="" required="true" disabled="false" cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none" id="kabupaten11" name="rekruitmen.kabupaten" required="true" disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <script type='text/javascript'>
                                            var functions, mapped;
                                            // var prov = document.getElementById("provinsi1").value;
                                            $('#kabupaten').typeahead({
                                                minLength: 1,
                                                source: function (query, process) {
                                                    functions = [];
                                                    mapped = {};

                                                    var data = [];
                                                    dwr.engine.setAsync(false);
                                                    ProvinsiAction.initComboKota(query, prov, function (listdata) {
                                                        data = listdata;
                                                    });
                                                    //alert(prov);
                                                    $.each(data, function (i, item) {
                                                        //alert(item.kotaName);
                                                        var labelItem = item.kotaName;
                                                        mapped[labelItem] = { id: item.kotaId, label: labelItem };
                                                        functions.push(labelItem);
                                                    });

                                                    process(functions);
                                                },
                                                updater: function (item) {
                                                    var selectedObj = mapped[item];
                                                    var namaAlat = selectedObj.label;
                                                    document.getElementById("kabupaten11").value = selectedObj.id;

                                                    kab = selectedObj.id ;
                                                    return namaAlat;
                                                }
                                            });

                                            //
                                            //
                                        </script>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Kecamatan :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="kecamatan" name="" required="true" disabled="false" cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none" id="kecamatan11" name="rekruitmen.kecamatan" required="true" disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <script type='text/javascript'>
                                            var functions, mapped;
                                            var kab = document.getElementById("kabupaten").value;
                                            $('#kecamatan').typeahead({
                                                minLength: 1,
                                                source: function (query, process) {
                                                    functions = [];
                                                    mapped = {};

                                                    var data = [];
                                                    dwr.engine.setAsync(false);
                                                    ProvinsiAction.initComboKecamatan(query, kab, function (listdata) {
                                                        data = listdata;
                                                    });
                                                    //alert(prov);
                                                    $.each(data, function (i, item) {
                                                        //alert(item.kotaName);
                                                        var labelItem = item.kecamatanName;
                                                        mapped[labelItem] = { id: item.kecamatanId, label: labelItem };
                                                        functions.push(labelItem);
                                                    });

                                                    process(functions);
                                                },
                                                updater: function (item) {
                                                    var selectedObj = mapped[item];
                                                    var namaAlat = selectedObj.label;
                                                    document.getElementById("kecamatan11").value = selectedObj.id;

                                                    kec = selectedObj.id;
                                                    return namaAlat;
                                                }
                                            });
                                        </script>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Desa :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="desa" name="" required="true" disabled="false" cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none" id="desa11" name="rekruitmen.desa" required="true" disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <script type='text/javascript'>
                                            var functions, mapped;
                                            $('#desa').typeahead({
                                                minLength: 1,
                                                source: function (query, process) {
                                                    functions = [];
                                                    mapped = {};

                                                    var data = [];
                                                    dwr.engine.setAsync(false);
                                                    ProvinsiAction.initComboDesa(query, kec, function (listdata) {
                                                        data = listdata;
                                                    });
                                                    //alert(prov);
                                                    $.each(data, function (i, item) {
                                                        //alert(item.kotaName);
                                                        var labelItem = item.desaName;
                                                        mapped[labelItem] = { id: item.desaId, label: labelItem };
                                                        functions.push(labelItem);
                                                    });

                                                    process(functions);
                                                },
                                                updater: function (item) {
                                                    var selectedObj = mapped[item];
                                                    var namaAlat = selectedObj.label;
                                                    document.getElementById("desa11").value = selectedObj.id;

                                                    desa = selectedObj.id;
                                                    return namaAlat;
                                                }
                                            });
                                        </script>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Rt / Rw :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="rtRw" name="rekruitmen.rtRw" required="true" disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Tempat / Tanggal Lahir :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <s:textfield id="tempatLahir" name="rekruitmen.tempatLahir" required="true" cssClass="form-control"/>
                                                        </td>
                                                        <td> / </td>
                                                        <td>
                                                            <div class="input-group date">
                                                                <div class="input-group-addon">
                                                                    <i class="fa fa-calendar"></i>
                                                                </div>
                                                                <s:textfield id="tanggalLahir" name="rekruitmen.stTanggalLahir" cssClass="form-control pull-right" required="false" />
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Status Keluarga :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'K':'Keluarga','B':'Bujang'}" id="statusKeluarga" name="rekruitmen.statusKeluarga"
                                                              headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Jumlah Anak :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="jumlahAnak" name="rekruitmen.jumlahAnak" required="true" disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Calon Jabatan :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                                    <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="rekruitmen.positionId"
                                                              listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Divisi :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                                    <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="rekruitmen.divisiId" readonly="true"
                                                              listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
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
                                                    <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="rekruitmen.branchId"
                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                </table>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Status Pegawai :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'KNS' : 'Karyawan Non Struktural', 'KS':'Karyawan Struktural'}"
                                                              id="statusPegawai" name="rekruitmen.statusPegawai"
                                                              headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Tipe Pegawai :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="initComboTipe" namespace="/tipepegawai" name="searchTipePegawai_tipepegawai"/>
                                                    <s:select list="#initComboTipe.listComboTipePegawai" id="tipePegawai1" name="rekruitmen.tipePegawai"
                                                              listKey="tipePegawaiId" listValue="tipePegawaiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Status Giling :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'DMG':'Dalam Masa Giling','LMG':'Luar Masa Giling'}" id="statusGiling" name="rekruitmen.statusGiling"
                                                              headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <h4>
                                        List Study
                                        <button
                                                id="btnAddStudy" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah-study"><i class="fa fa-plus"></i>
                                        </button>
                                    </h4>
                                    <center>
                                        <table id="showdata" width="80%">
                                            <tr>
                                                <td align="center">
                                                    <table style="width: 80%;" class="rekruitmenStudyTable table table-bordered">
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>
                                    <br>
                                    <h4>
                                        List Document ( Upload Document )
                                        <button
                                                id="btnAddDocument" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah-document"><i class="fa fa-plus"></i>
                                        </button>
                                    </h4>
                                    <center>
                                        <table id="showdata1" width="80%">
                                            <tr>
                                                <td align="center">
                                                    <table style="width: 80%;" class="rekruitmenDocumentTable table table-bordered">
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>
                                    <br>
                                    <table>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Status Rekruitmen :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="initComboStatus" namespace="/rekruitmen" name="searchStatusRekruitmen_rekruitmen"/>
                                                    <s:select list="#initComboStatus.listComboStatusRekruitmen" id="statusRekruitmen12" name="rekruitmenStatus.statusRekruitmen" listKey="statusRekruitmentId" listValue="statusRekruitmentName"
                                                              headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <table>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Keterangan :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textarea rows="4" cols="70"  id="keterangan" name="rekruitmen.keterangan" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br><br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="rekruitmenForm" id="save" name="save"
                                                               onBeforeTopics="beforeProcessSave154" onCompleteTopics="closeDialog,successDialog"
                                                               onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                        <i class="fa fa-check"></i>
                                                        Save
                                                    </sj:submit>
                                                </td><td>&nbsp;&nbsp;&nbsp;</td>
                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="add_rekruitmen.action"/>'">
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
<div id="modal-edit-study" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data Study</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormStudy">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jenjang : </label>
                        <div class="col-sm-6">
                            <s:select list="#{'SD':'SD/Sederajat','SMP':'SMP/Sederajat','SMA':'SMA/Sederajat','S1':'S1','S2':'S2','S3':'S3'}" id="jenjang" name="" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama Sekolah : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control namaSekolah" id="namaSekolah" name="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tahun Awal : </label>
                        <div class="col-sm-6">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <s:textfield id="tahunAwal" name="" cssClass="form-control pull-right" required="false" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tahun Akhir : </label>
                        <div class="col-sm-6">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <s:textfield id="tahunAkhir" name="" cssClass="form-control pull-right" required="false" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nilai : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nilai" id="nilai" name="nilai">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveStudy" type="button" class="btn btn-default btn-success">Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-edit-document" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Document</h4>
            </div>
            <div class="modal-body">
                <s:url id="urlProcess" namespace="/rekruitmen" action="addRekruitmenDoc_rekruitmen" includeContext="false"/>
                <s:form id="myFormDocument" enctype="multipart/form-data" method="post" action="%{urlProcess}" theme="simple" cssClass="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >File : </label>
                        <div class="col-sm-8">
                            <input type="file" id="file" class="form-control" name="fileUpload" />
                            <input type="text" id="cpiddoc" class="form-control" name="rekruitmenUploadDoc.calonPegawaiId" readonly style="display: none;" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" class="form-control" id="docNote" name="rekruitmenUploadDoc.note"></textarea>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addUserForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                        <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
                    </div>
                    <%--</form>--%>
                </s:form>
            </div>
        </div>
    </div>
</div>
</html>
<script>
    $(document).ready(function() {
        $('#btnAddStudy').click(function(){
            $('#myFormStudy')[0].reset();
            console.log($('#myFormStudy')[0]);
            $('#modal-edit-study').modal('show');
            $('#myFormStudy').attr('action','addStudy');
            $('#modal-edit-study').find('.modal-title').text('Add Study');
        });
        $('#btnAddDocument').click(function(){
            $('#modal-edit-document').modal('show');
            $('#myFormDocument').attr('id',document.getElementById('calonPegawaiId').value);
            $('#modal-edit-document').find('.modal-title').text('Upload Document');
        });
        $('#tanggalLahir').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange:"1980:2018"
        });
        $('#tahunAwal').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange:"1980:2018"
        });
        $('#tahunAkhir').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange:"1980:2018"
        });
        window.loadRekruitmen =  function(){
            //alert(nip);
            $('.rekruitmenStudyTable').find('tbody').remove();
            $('.rekruitmenStudyTable').find('thead').remove();
            $('.rekruitmenDocumentTable').find('tbody').remove();
            $('.rekruitmenDocumentTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table_study = "";
            RekruitmenAction.searchRekruitmenStudy(function(listdata) {
                tmp_table_study = "<thead style='font-size: 14px' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>No</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>View</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef''>Jenjang</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef''>Nama Sekolah</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef''>Tahun Awal</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>Tahun Akhir</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>Nilai</th>"+
                        "</tr></thead>";
                var i = i ;
                $.each(listdata, function (i, item) {
                    tmp_table_study += '<tr style="font-size: 12px;" ">' +
                            '<td >' + (i + 1) + '</td>' +
                            '<td align="center">' +
                            "<a href='javascript:;' class ='item-view' data ='"+item.studyCalonPegawaiId+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>"+
                            '</a>' +
                            '</td>' +
                            '<td >' + item.tipeStudy + '</td>' +
                            '<td align="center">' + item.studyName + '</td>' +
                            '<td align="center">' + item.stTahunAwal + '</td>' +
                            '<td align="center">' + item.stTahunAkhir + '</td>' +
                            '<td align="center">' + item.nilai + '</td>' +
                            "</tr>";
                });
                $('.rekruitmenStudyTable').append(tmp_table_study);
            });
            dwr.engine.setAsync(false);
            var tmp_table_document = "";
            RekruitmenAction.searchRekruitmenDocument(function(listdata) {
                tmp_table_document = "<thead style='font-size: 14px' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>No</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>View</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>Keterangan</th>"+
                        "</tr></thead>";
                var i = i ;
                $.each(listdata, function (i, item) {
                    tmp_table_document += '<tr style="font-size: 12px;" ">' +
                            '<td >' + (i + 1) + '</td>' +
                            '<td align="center">' +
                            "<a href='javascript:;' class ='item-view' data ='"+item.uploadDocRekId+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>"+
                            '</a>' +
                            '</td>' +
                            '<td >' + item.note+ '</td>' +
                            "</tr>";
                });
                $('.rekruitmenDocumentTable').append(tmp_table_document);
            });
        };
        $('#btnSaveStudy').click(function(){
            var url = $('#myFormStudy').attr('action');
            var data = $('#myFormStudy').serialize();

            var Cpid        = document.getElementById("calonPegawaiId").value;
            var tipeStudy   = document.getElementById("jenjang").value;
            var studyName   = document.getElementById("namaSekolah").value;
            var tahunAwal   = document.getElementById("tahunAwal").value;
            var tahunAkhir  = document.getElementById("tahunAkhir").value;
            var nilai       = document.getElementById("nilai").value;

            //alert(personName + branchName + positionName + divisiName);
            if(url == 'addStudy'){
                if(Cpid != ''){
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        RekruitmenAction.saveAddStudy(Cpid, tipeStudy, studyName, tahunAwal, tahunAkhir, nilai,function(listdata) {
                            alert('Data Successfully Added');
                            $('#modal-edit-study').modal('hide');
                            $('#myFormStudy')[0].reset();
                            loadRekruitmen();
                        });
                    }
                }else{
                    alert('Calon Pegawai Id must be Entry');
                }
            }else if(url == 'editStudy'){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    RekruitmenAction.saveEditStudy(Cpid,tipeStudy,studyName,tahunAwal,tahunAkhir,nilai, function(listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-edit-study').modal('hide');
                        $('#myFormStudy')[0].reset();
                        loadRekruitmen();
                    });
                }
            }else{
                if (confirm('Are you sure you want to delete this Record?')) {
                    RekruitmenAction.saveRekruitmenStudyDelete(Cpid, function (listdata) {
                        $('#modal-edit').modal('hide');
                        $('#myFormStudy')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadRekruitmen();
                    });
                }
            }
        });
        $('#file').change(function(){
            var cpid = document.getElementById('calonPegawaiId').value;
            if (cpid!=null&&cpid!=""){
                $('#cpiddoc').val(cpid);
            }
            else {
                alert("Calon Pegawai ID is Empty");
                $('#file').val("");
            }
        });
        loadRekruitmen();
    });
</script>




