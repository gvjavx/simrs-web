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
    <script type='text/javascript' src='<s:url value="/dwr/interface/SppdAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>

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
                    loadPerson();
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
            Add SPPD
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
                            <tr>
                                <td>
                                    <label>Training : </label>
                                </td>
                                <td>
                                    <input type="checkbox" onchange="cekTraining(this)" id="checkApprove" name="checkAll" class="checkApprove">
                                    <s:hidden id="flagTraining" value="N" name="sppd.flagTraining"></s:hidden>
                                </td>
                            </tr>
                            <tr id="divIdTraining" style="display:none;">
                                <td>
                                    <label>ID Training: </label>
                                </td>
                                <td>
                                    <s:textfield cssStyle="background-color:#99ebff; " id="idTraining" name="sppd.idTraining" required="false" readonly="false" cssClass="form-control"/>
                                </td>
                                <td>
                                    <a id="cekIdTraining" class="btn btn-info">cek</a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Pemberi Tugas :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="pemberiTugas2" required="false" readonly="false" cssClass="form-control"/>
                                        <s:textfield cssStyle="display: none" id="pemberiTugas" name="sppd.pemberiTugas" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    // var prov = document.getElementById("provinsi1").value;
                                    $('#pemberiTugas2').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};

                                            var data = [];
                                            dwr.engine.setAsync(false);
                                            MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
                                                data = listdata;
                                                //alert('aa');
                                            });
                                            //alert(prov);
                                            $.each(data, function (i, item) {
                                                var labelItem =item.nip+ " || "+ item.namaPegawai;
                                                var labelNip = item.nip;
                                                mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem, branchId : item.branch, divisiId: item.divisi, positionId : item.positionId };
                                                functions.push(labelItem);
                                            });


                                            process(functions);
                                        },

                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            var namaAlat = selectedObj.label;
                                            document.getElementById("pemberiTugas").value = selectedObj.id;

                                            return namaAlat;
                                        }
                                    });

                                </script>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Nama :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield cssStyle="display: none" id="personName1" name="sppdPerson.personId" required="false" readonly="false" cssClass="form-control"/>
                                        <s:textfield  id="personName2" required="false" readonly="false" cssClass="form-control"/>
                                        <s:textfield cssStyle="display: none" id="personName" name="sppdPerson.personName" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    // var prov = document.getElementById("provinsi1").value;
                                    $('#personName2').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};

                                            var data = [];
                                            dwr.engine.setAsync(false);
                                            MedicalRecordAction.initComboPersonil(query, function (listdata) {
                                                data = listdata;
                                                //alert('aa');
                                            });
                                            //alert(prov);
                                            $.each(data, function (i, item) {
                                                var labelItem =item.nip+ " || "+ item.namaPegawai;
                                                var labelNip = item.nip;
                                                mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem, branchId : item.branch, divisiId: item.divisi, positionId : item.positionId };
                                                functions.push(labelItem);
                                            });


                                            process(functions);
                                        },

                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            var namaAlat = selectedObj.label;
                                            document.getElementById("personName1").value = selectedObj.id;
                                            document.getElementById("personName").value = selectedObj.pegawai;

                                            $('#positionId').val(selectedObj.positionId).change();
                                            $('#branchId').val(selectedObj.branchId).change();
                                            $('#divisiId').val(selectedObj.divisiId).change();

                                            $('#positionId3').val(selectedObj.positionId).change();
                                            $('#branchId3').val(selectedObj.branchId).change();
                                            $('#divisiId3').val(selectedObj.divisiId).change();

                                            branc = selectedObj.branchId;
                                            dev = selectedObj.divisiId ;
                                            return namaAlat;
                                        }
                                    });

                                </script>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="sppd.branchId" readonly="true" cssStyle="display: none"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId3" name="sppd.branchId" disabled="true"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Bagian :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="sppd.divisiId" readonly="true" cssStyle="display: none"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId3" name="sppd.divisiId" readonly="true" disabled="true"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
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
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="sppdPerson.positionId" readonly="true" cssStyle="display: none"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId3" name="sppdPerson.positionId" readonly="true" disabled="true"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Dinas :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'LN':'Luar Negeri'}" id="flag" name="sppd.dinas"
                                                  headerKey="DN" headerValue="Dalam Negeri" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Keperluan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textarea rows="4"  id="tujuanSppd" name="sppd.tugasSppd" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tanggal Berangkat :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggalBerangkat" name="sppd.stTanggalSppdBerangkat" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tanggal Kembali :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggalKembali" name="sppd.stTanggalSppdKembali" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Dari :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield cssStyle="display: none" id="berangkatDari1" name="sppdPerson.berangkatDari" required="false" readonly="false" cssClass="form-control"/>
                                        <s:textfield onchange="" id="berangkatDari" name="sppdPerson.berangkatDar" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                    <script type='text/javascript'>
                                        var functions, mapped;
                                        $('#berangkatDari').typeahead({
                                            minLength: 1,
                                            source: function (query, process) {
                                                functions = [];
                                                mapped = {};

                                                var data = [];
                                                dwr.engine.setAsync(false);
                                                ProvinsiAction.initComboKota(query, "", function (listdata) {
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
                                                document.getElementById("berangkatDari1").value = selectedObj.id;
                                                document.getElementById("kotaDari").value = selectedObj.label;

                                                //kab = selectedObj.id ;
                                                return namaAlat;
                                            }
                                        });
                                    </script>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Tempat Tujuan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield cssStyle="display: none" id="tujuanKe1" name="sppdPerson.tujuanKe" required="false" readonly="false" cssClass="form-control"/>
                                        <s:textfield onchange="" id="tujuanKe" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                    <script type='text/javascript'>
                                        var functions, mapped;
                                        $('#tujuanKe').typeahead({
                                            minLength: 1,
                                            source: function (query, process) {
                                                functions = [];
                                                mapped = {};

                                                var data = [];
                                                dwr.engine.setAsync(false);
                                                ProvinsiAction.initComboKota(query, "", function (listdata) {
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
                                                document.getElementById("tujuanKe1").value = selectedObj.id;
                                                document.getElementById("kotaTujuan").value = selectedObj.label;

                                                //kab = selectedObj.id ;
                                                return namaAlat;
                                            }
                                        });
                                    </script>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Berangkat Via :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'Kereta Api':'Kereta Api' , 'Bis' : 'Bis', 'Mobil' : 'Mobil'}" cssClass="form-control" id="berangkatVia" name="sppdPerson.berangkatVia"
                                                  headerKey="Pesawat" headerValue="Pesawat" />
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Pulang Via :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'Kereta Api':'Kereta Api' , 'Bis' : 'Bis', 'Mobil' : 'Mobil'}" cssClass="form-control" id="pulangVia" name="sppdPerson.kembaliVia"
                                                  headerKey="Pesawat" headerValue="Pesawat" />
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Penginapan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'Hotel':'Hotel' , 'Mess' : 'Mess'}" cssClass="form-control" id="penginapan" name="sppdPerson.penginapan"
                                                  headerKey="" headerValue="" />
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
                            Anggota
                            <button
                                    id="btnAddAnggota" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
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
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="nip" name="nip">
                            <input style="display: none" type="text" class="form-control nip" id="nipNama" name="nip">
                            <input style="display: none" type="text" class="form-control" id="nip1" name="nip1">
                            <input style="display: none" type="text" class="form-control" id="nipOld" name="nip1">
                        </div>
                        <script type='text/javascript'>
                            var functions, mapped;
                            // var prov = document.getElementById("provinsi1").value;
                            $('#nip').typeahead({
                                minLength: 1,
                                source: function (query, process) {
                                    functions = [];
                                    mapped = {};

                                    var data = [];
                                    dwr.engine.setAsync(false);
                                    //UserAction.initComboUser(query, branc, dev, function (listdata) {
                                    /*BiodataAction.initComboUser(query, function (listdata) {
                                        data = listdata;
                                        //alert('aa');
                                    });*/

                                    MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
                                        data = listdata;
                                        //alert('aa');
                                    });
                                    //alert(prov);
                                    $.each(data, function (i, item) {
                                        var labelItem =item.nip+ " || "+ item.namaPegawai;
                                        var labelNip = item.nip;
                                        mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem, branchId : item.branch, divisiId: item.divisi, positionId : item.positionId };
                                        functions.push(labelItem);
                                    });

                                    process(functions);
                                },

                                updater: function (item) {
                                    var selectedObj = mapped[item];
                                    var namaAlat = selectedObj.label;
                                    document.getElementById("nip1").value = selectedObj.id;
                                    document.getElementById("nipNama").value = selectedObj.pegawai;

                                    $('#positionId1').val(selectedObj.positionId).change();
                                    $('#branchId1').val(selectedObj.branchId).change();
                                    $('#divisiId1').val(selectedObj.divisiId).change();
                                    return namaAlat;
                                }
                            });

                        </script>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiId1">Unit :</label>
                        <div class="col-sm-6">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId1" name="sppd.branchId" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiId1">Jabatan :</label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="positionId1" name="sppdPerson.positionId" disabled="true"
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiId1">Divisi :</label>
                        <div class="col-sm-6">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="divisiId1" name="sppd.divisiId" disabled="true"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Berangkat:</label>
                        <div class="col-sm-6">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" id="tanggalBerangkatAnggota" class="form-control pull-right"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Kembali : </label>
                        <div class="col-sm-6">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <s:textfield id="tanggalKembaliAnggota"  cssClass="tanggalKembaliAnggota form-control pull-right"
                                             required="false"  cssStyle=""/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Dari : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="kotaDari" name="nip" readonly>
                            <input type="text" class="form-control" style="display: none" id="kotaDari1" name="nip" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Kota Tujuan : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control nip" id="kotaTujuan" name="nip" readonly>
                            <input type="text" class="form-control" style="display: none" id="kotaTujuan1" name="nip" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Berangkat Via : </label>
                        <div class="col-sm-6">
                            <s:select list="#{'Kereta Api':'Kereta Api' , 'Bis' : 'Bis', 'Pesawat' : 'Pesawat', 'Mobil' : 'Mobil'}" cssClass="form-control" id="berangkatViaAnggota" name="sppdPerson.berangkatVia"
                                      headerKey="" headerValue="[Pilih Transportasi]" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Pulang Via : </label>
                        <div class="col-sm-6">
                            <s:select list="#{'Kereta Api':'Kereta Api' , 'Bis' : 'Bis', 'Pesawat' : 'Pesawat', 'Mobil' : 'Mobil'}" cssClass="form-control" id="pulangViaAnggota" name="sppdPerson.berangkatVia"
                                      headerKey="" headerValue="[Pilih Transportasi]" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Penginapan : </label>
                        <div class="col-sm-6">
                            <s:select list="#{'Hotel':'Hotel' , 'Mess' : 'Mess'}" cssClass="form-control" id="penginapanAnggota" name="sppdPerson.kembaliVia"
                                      headerKey="" headerValue="" />
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="button" class="btn btn-default btn-success">Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</html>

<script>

    window.loadPerson =  function(){
        //alert(nip);
        $('.sppdPersonTable').find('tbody').remove();
        $('.sppdPersonTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        SppdAction.searchSppdPerson(function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Unit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Bagian</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Dari</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Kota Tujuan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Berangkat</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Kembali</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Berangkat Via</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Pulang Via</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Penginapan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Edit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Delete</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + item.personName + '</td>' +
                        '<td align="center">' + item.branchName + '</td>' +
                        '<td align="center">' + item.divisiName + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.berangkatDariNama + '</td>' +
                        '<td align="center">' + item.tujuanKeNama + '</td>' +
                        '<td align="center">' + item.stTanggalBerangkat+ '</td>' +
                        '<td align="center">' + item.stTanggalKembali + '</td>' +
                        '<td align="center">' + item.berangkatVia + '</td>' +
                        '<td align="center">' + item.kembaliVia + '</td>' +
                        '<td align="center">' + item.penginapan + '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-edit' data ='"+item.personId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-delete' data ='"+item.personId+"' >" +
                        "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                        '</a>' +
                        '</td>' +
                        "</tr>";
            });
            $('.sppdPersonTable').append(tmp_table);
        });
    }

    /*window.cekBiayaLokal = function(){
        var berangkatDari   = document.getElementById("berangkatDari1").value;
        SppdAction.cekBiayaLokal(berangkatDari ,function(listdata) {
            if(listdata == false){
                $('#berangkatDari1').val('');
                $('#berangkatDari').val('');
                alert('Data master berangkat belum ada');
            }
        });
    }

    window.cekBiayaLokal2 = function(){
        var tujuanKe   = document.getElementById("tujuanKe1").value;
        SppdAction.cekBiayaLokal(tujuanKe ,function(listdata) {
            if(listdata == false){
                $('#tujuanKe1').val('');
                $('#tujuanKe').val('');
                alert('Data master tujuan belum ada');
            }
        });
    }*/

    $(document).ready(function() {

        loadPerson();

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

        $('#btnAddAnggota').click(function(){
            var tujuanKe        = document.getElementById("tujuanKe").value;
            var tujuanKe1        = document.getElementById("tujuanKe1").value;
            var berangkatDari   = document.getElementById("berangkatDari").value;
            var berangkatDari1   = document.getElementById("berangkatDari1").value;

            $("#nip").prop("readonly", false);
            $('#myForm')[0].reset();
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'addPerson');
            $('#modal-edit').find('.modal-title').text('Add Anggota');
            $('#kotaDari').val(berangkatDari);
            $('#kotaTujuan').val(tujuanKe);
            $('#kotaDari1').val(berangkatDari1);
            $('#kotaTujuan1').val(tujuanKe1);
        });

    });

    $('.sppdPersonTable').on('click', '.item-edit', function(){
        var nip = $(this).attr('data');
        $("#nip").prop("readonly", false);
        SppdAction.searchSppdPersonEdit(nip ,function(listdata) {
            $.each(listdata, function (i, item) {
                $('#nip1').val(item.personId);
                $('#nipOld').val(item.personId);
                $('#nip').val(item.personName);
                $('#nipNama').val(item.personName);
                $('#kotaDari').val(item.berangkatDariNama);
                $('#kotaDari1').val(item.berangkatDari);
                $('#kotaTujuan').val(item.tujuanKeNama);
                $('#kotaTujuan1').val(item.tujuanKe);
                $('#tanggalBerangkatAnggota').val(item.stTanggalBerangkat);
                $('#tanggalKembaliAnggota').val(item.stTanggalKembali);

                $('#branchId1').val(item.branchId).change();
                $('#positionId1').val(item.positionId).change();
                $('#divisiId1').val(item.divisiId).change();
                $('#berangkatViaAnggota').val(item.berangkatVia).change();
                $('#pulangViaAnggota').val(item.kembaliVia).change();
                $('#penginapanAnggota').val(item.penginapan).change();
            });
        });

        $('#modal-edit').find('.modal-title').text('Edit Data');
        $('#modal-edit').modal('show');
        $("#btnSave").html('Save Edit');
        $('#myForm').attr('action', 'editPerson');
    });

    $('.sppdPersonTable').on('click', '.item-delete', function(){
        var id = $(this).attr('data');

        SppdAction.searchSppdPersonEdit(id,function(listdata) {
            $.each(listdata, function (i, item) {
                $('#nip1').val(item.personId);
                $('#nipOld').val(item.personId);
                $('#nip').val(item.personName);
                $('#kotaDari').val(item.berangkatDariNama);
                $('#kotaDari1').val(item.berangkatDari);
                $('#kotaTujuan').val(item.tujuanKeNama);
                $('#kotaTujuan1').val(item.tujuanKe);
                $('#tanggalBerangkatAnggota').val(item.stTanggalBerangkat);
                $('#tanggalKembaliAnggota').val(item.stTanggalKembali);

                $('#branchId1').val(item.branchId).change();
                $('#positionId1').val(item.positionId).change();
                $('#divisiId1').val(item.divisiId).change();
                $('#berangkatViaAnggota').val(item.berangkatVia).change();
                $('#pulangViaAnggota').val(item.kembaliVia).change();
                $('#penginapanAnggota').val(item.penginapan).change();
            });
        });

        $("#nip").prop("readonly", true);
        $("#btnSave").html('Delete');
        $('#modal-edit').find('.modal-title').text('Delete Data Anggota');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'deletePerson');

    });

    $('#btnSave').click(function(){
        var url = $('#myForm').attr('action');
        var data = $('#myForm').serialize();

        var kotaDariNama    = document.getElementById("kotaDari").value;
        var kotaDari    = document.getElementById("kotaDari1").value;
        var kotaTujuanNama  = document.getElementById("kotaTujuan").value;
        var kotaTujuan  = document.getElementById("kotaTujuan1").value;
        var berangkatViaAnggota  = document.getElementById("berangkatViaAnggota").value;
        var pulangViaAnggota  = document.getElementById("pulangViaAnggota").value;
        var penginapanAnggota  = document.getElementById("penginapanAnggota").value;
        var tanggalBerangkat  = document.getElementById("tanggalBerangkat").value;
        var tanggalKembali  = document.getElementById("tanggalKembali").value;
        var tanggalBerangkatAnggota  = document.getElementById("tanggalBerangkatAnggota").value;
        var tanggalKembaliAnggota  = document.getElementById("tanggalKembaliAnggota").value;

        var nip         = document.getElementById("nip1").value;
        var nipOld         = document.getElementById("nipOld").value;
        /*var personName  = document.getElementById("nip").value;*/
        var personName  = document.getElementById("nipNama").value;
        var branchId    = document.getElementById("branchId1").value;
        var branchName1 = document.getElementById("branchId1");
        var branchName  = branchName1.options[branchName1.selectedIndex].text;
        var positionId  = document.getElementById("positionId1").value;
        var positionName1= document.getElementById("positionId1");
        var positionName= positionName1.options[positionName1.selectedIndex].text;
        var divisiId    = document.getElementById("divisiId1").value;
        var divisiName1 = document.getElementById("divisiId1");
        var divisiName  = divisiName1.options[divisiName1.selectedIndex].text;

        var tangAnggota1 = tanggalBerangkatAnggota.split("-");
        var tangAnggota2 = tanggalKembaliAnggota.split("-");
        var tang1 = tanggalBerangkat.split("-");
        var tang2 = tanggalKembali.split("-");
        //alert(personName + branchName + positionName + divisiName);
        if(url == 'addPerson'){
            if(Date.parse(tangAnggota1[2] + '-' + tangAnggota1[1] + '-' + tangAnggota1[0]) > Date.parse(tangAnggota2[2] + '-' + tangAnggota2[1] + '-' + tangAnggota2[0])){
                alert('Tanggal Berangkat Tidak boleh lebih dari tanggal kembali');
            }else if(nip != '' && kotaDari != '' && kotaTujuan != '' && berangkatViaAnggota != '' && pulangViaAnggota != '' ){
                SppdAction.cekAvailableSppd(nip, tanggalBerangkatAnggota, tanggalKembaliAnggota, function(listdata) {
                    if (listdata == "-") {
                        if (confirm('Are you sure you want to save this Record?')) {
                            dwr.engine.setAsync(false);
                            SppdAction.saveAdd(nip, personName, branchId, branchName, positionId, positionName,  divisiId, divisiName,
                                    kotaDari, kotaTujuan, berangkatViaAnggota, pulangViaAnggota, penginapanAnggota, kotaDariNama,
                                    kotaTujuanNama, tanggalBerangkatAnggota, tanggalKembaliAnggota, function(listdata) {
                                        alert('Data Successfully Added');
                                        $('#modal-edit').modal('hide');
                                        $('#myForm')[0].reset();
                                        loadPerson();
                                    });
                        }
                    } else {
                        alert(listdata);
                    }
                });

            }else{
                var msg = '';
                if(nip == ''){
                    msg += 'Nama, ';
                    //alert('');
                }
                if(kotaDari == ''){
                    msg += 'Berangkat dari, ';
                }
                if(berangkatViaAnggota == ''){
                    msg += 'Berangkat Via, ';
                }

                if(pulangViaAnggota == ''){
                    msg += 'Pulang Via, ';
                }


                msg += 'Harus Diisi!';
                alert(msg);
            }
        }else if(url == 'editPerson'){
            if(Date.parse(tangAnggota1[2] + '-' + tangAnggota1[1] + '-' + tangAnggota1[0]) > Date.parse(tangAnggota2[2] + '-' + tangAnggota2[1] + '-' + tangAnggota2[0])){
                alert('Tanggal Berangkat Tidak boleh lebih dari tanggal kembali');
            }else if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);
                SppdAction.saveEditPerson(nipOld, nip, personName, branchId, branchName, positionId, positionName,  divisiId, divisiName,
                        kotaDari, kotaTujuan, berangkatViaAnggota, pulangViaAnggota, penginapanAnggota, kotaDariNama,
                        kotaTujuanNama, tanggalBerangkatAnggota, tanggalKembaliAnggota, function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    loadPerson();
                });
            }
        }else{
            if (confirm('Are you sure you want to delete this Record?')) {
                SppdAction.saveSppdPersonDelete(nip, function (listdata) {
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    alert('Record has been Deleted successfully.');
                    loadPerson();
                });
            }
        }
    });

    var branc = '' ;
    var dev = '';
</script>
