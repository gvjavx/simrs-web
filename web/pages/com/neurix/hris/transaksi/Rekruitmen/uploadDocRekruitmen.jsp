<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <style type="text/css">
        #tgl1, #tgl2 {
            z-index: 2000 !important
        }

    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TipePegawaiAction.js"/>'></script>
    <script type="text/javascript">

        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };
        window.close = function () {
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.href = "<s:url action='search_rekruitmen.action'/>";
        };
        $.subscribe('beforeProcessSave', function (event, data) {
            if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
        });
        $.subscribe('beforeProcessSaveDocument', function (event, data) {
            if (confirm('Do you want to save this record?')) {
                event.originalEvent.options.submit = true;
                $('#modal-edit-document').modal('hide');
                $('#myFormDocument12')[0].reset();
                alert('Record has been Deleted successfully.');
                loadRekruitmen();
            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });
        $.subscribe('successDialogDocument', function (event, data) {
            loadRekruitmen();
        });
        $.subscribe('loadfoto', function (event, data) {
            loadFoto();
        });
        $.subscribe('beforeProcessDelete', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });


        $.subscribe('successDialog2', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog2');
            }
        });

        $.subscribe('errorDialog', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });
        function cancelBtn() {
            $('#view_dialog_menu_ijin_keluar').dialog('close');
        }
        ;


    </script>

</head>

<body bgcolor="#FFFFFF">
<%--
<table width="100%" align="center">--%>
<div align="center" class="well">
    <s:form id="rekruitmenEditForm" method="post" theme="simple" namespace="/rekruitmen"
            action="saveEdit_rekruitmen.action" cssClass="form-horizontal">
        <s:hidden name="addOrEdit"/>
        <s:hidden name="delete"/>
        <tr>
            <td align="center">
                <legend align="left">Edit Data Rekruitmen</legend>
                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>
                <div style="float: left">
                    <img id="myImage" src="" class="img-thumbnail img-responsive" width="200" height="150">
                </div>
                <table>
                    <tr>
                        <td>
                            <label class="control-label"><small>Calon Pegawai Id :</small></label>
                        </td>
                        <td>
                            <s:textfield id="calonPegawaiId32" name="rekruitmen.calonPegawaiId" required="false" readonly="true" cssClass="form-control"/>
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
                            <label class="control-label"><small>No. KTP :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="noKtp" name="rekruitmen.noKtp" type="number" required="false" readonly="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>No. HP :</small></label>
                        </td>
                        <td>
                            <s:textfield id="noTelp" name="rekruitmen.noTelp" type="number" required="false" readonly="false" cssClass="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Alamat :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4"  id="tujuanRekruitmen" name="rekruitmen.alamat" required="false" readonly="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Provinsi :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="provinsi" name="rekruitmen.provinsiName" required="true" disabled="false"
                                             cssClass="form-control"/>
                                <s:textfield cssStyle="display: none" id="provinsi11"
                                             name="rekruitmen.provinsiId" required="true"
                                             disabled="false" cssClass="form-control"/>
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
                                    mapped[labelItem] = {id: item.provinsiId, label: labelItem};
                                    functions.push(labelItem);
                                });

                                process(functions);
                            },
                            updater: function (item) {
                                var selectedObj = mapped[item];
                                var namaAlat = selectedObj.label;
                                document.getElementById("provinsi11").value = selectedObj.id;
                                prov = selectedObj.id;
                                return namaAlat;
                            }
                        });
                    </script>


                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Kabupaten :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="kabupaten" name="rekruitmen.kotaName" required="true" disabled="false"
                                             cssClass="form-control"/>
                                <s:textfield cssStyle="display: none" id="kabupaten11"
                                             name="rekruitmen.kotaId" required="true"
                                             disabled="false" cssClass="form-control"/>
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
                                    mapped[labelItem] = {id: item.kotaId, label: labelItem};
                                    functions.push(labelItem);
                                });

                                process(functions);
                            },
                            updater: function (item) {
                                var selectedObj = mapped[item];
                                var namaAlat = selectedObj.label;
                                document.getElementById("kabupaten11").value = selectedObj.id;

                                kab = selectedObj.id;
                                return namaAlat;
                            }
                        });

                        //
                        //
                    </script>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Kecamatan :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="kecamatan" name="rekruitmen.kecamatanName" required="true" disabled="false"
                                             cssClass="form-control"/>
                                <s:textfield cssStyle="display: none" id="kecamatan11"
                                             name="rekruitmen.kecamatanId" required="true"
                                             disabled="false" cssClass="form-control"/>
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
                                    mapped[labelItem] = {id: item.kecamatanId, label: labelItem};
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
                            <label class="control-label">
                                <small>Desa :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="desa" name="rekruitmen.desaName" required="true" disabled="false"
                                             cssClass="form-control"/>
                                <s:textfield cssStyle="display: none" id="desa11"
                                             name="rekruitmen.desaId" required="true"
                                             disabled="false" cssClass="form-control"/>
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
                                    mapped[labelItem] = {id: item.desaId, label: labelItem};
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
                                <s:textfield id="rtRw" name="rekruitmen.rtRw" required="true" disabled="false" readonly="false" cssClass="form-control"/>
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
                                        <s:textfield id="tempatLahir" name="rekruitmen.tempatLahir" readonly="false" required="true" cssClass="form-control"/>
                                    </td>
                                    <td> / </td>
                                    <td>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggalLahir" name="rekruitmen.stTanggalLahir" readonly="false" cssClass="form-control pull-right" required="false" />
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis Kelamin :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'L':'Laki-Laki','P':'Perempuan'}" id="jenisKelamin" name="rekruitmen.jenisKelamin"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Status Keluarga :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'K':'Keluarga','B':'Single'}" id="statusKeluarga"
                                          name="rekruitmen.statusKeluarga"
                                          headerKey="" headerValue="[Select one]"
                                          cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Jumlah Anak :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="jumlahAnak" name="rekruitmen.jumlahAnak" type="number"
                                             required="true" disabled="false"
                                             cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Unit :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboBranch" namespace="/admin/branch"
                                          name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId5"
                                          name="rekruitmen.branchId" onchange="listPosisi()"
                                          listKey="branchId" listValue="branchName" headerKey=""
                                          headerValue="[Select one]" cssClass="form-control"/>
                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Bidang :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department"
                                          name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="departmentId5"
                                          name="rekruitmen.departmentId" onchange="listPosisi()"
                                          listKey="departmentId" listValue="departmentName"
                                          headerKey="" headerValue="[Select one]"
                                          cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Calon Jabatan :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/position"
                                          name="searchPosition_position"/>
                                <s:select list="#comboPosition.listOfComboPosition" id="positionId5"
                                          name="rekruitmen.positionId"
                                          listKey="positionId" listValue="positionName" headerKey=""
                                          headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Status Pegawai :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                <s:select
                                        list="#{'KNS' : 'Karyawan Non Staff', 'KS':'Karyawan Staff'}"
                                        id="statusPegawai" name="rekruitmen.statusPegawai"
                                        headerKey="" headerValue="[Select one]"
                                        cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label">
                                <small>Tipe Pegawai :</small>
                            </label>
                        </td>
                        <td>
                            <table>
                                    <s:action id="initComboTipe" namespace="/tipepegawai"
                                    name="searchTipePegawai_tipepegawai"/>
                                    <s:select list="#initComboTipe.listComboTipePegawai"
                                    id="tipePegawai1" name="rekruitmen.tipePegawai"
                                    listKey="tipePegawaiId" listValue="tipePegawaiName"
                                    headerKey="" headerValue="[Select one]"
                                    cssClass="form-control"/>
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
            </td>
        </tr>

        <br>
        <br>
        <div id="actions" class="form-actions">
            <table align="center">
                <tr>
                    <td>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary"
                                   formIds="rekruitmenEditForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSave"
                                   onCompleteTopics="closeDialog,successDialog2"
                                   onSuccessTopics="successDialog2"
                                   onErrorTopics="errorDialog">
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;</td>
                    <td>
                        <button type="button" class="btn btn-danger"
                                onclick="window.location.href='<s:url
                                        action="search_rekruitmen.action"/>'">
                            <i class="fa fa-close"></i> Cancel
                        </button>
                    </td>
                </tr>
            </table>
        </div>
        <div id="actions" class="form-actions">
            <table>
                <tr>
                    <div id="crusd">
                        <td>
                            <table>
                                <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                           closeTopics="closeDialog" modal="true"
                                           resizable="false"
                                           height="350" width="600" autoOpen="false"
                                           title="Saving ...">
                                    Please don't close this window, server is processing your request ...
                                    </br>
                                    </br>
                                    </br>
                                    <center>
                                        <img border="0"
                                             src="<s:url value="/pages/images/indicator-write.gif"/>"
                                             name="image_indicator_write">
                                    </center>
                                </sj:dialog>

                                <sj:dialog id="info_dialog" openTopics="showInfoDialog"
                                           modal="true" resizable="false"
                                           height="200" width="400" autoOpen="false"
                                           title="Infomation Dialog"
                                           buttons="{
                                                              'OK':function() {
                                                                      close();
                                                                   }
                                                            }"
                                >
                                    <img border="0"
                                         src="<s:url value="/pages/images/icon_success.png"/>"
                                         name="icon_success">
                                    Record has been saved successfully.
                                </sj:dialog>
                                <sj:dialog id="waiting_dialog" openTopics="showDialog2"
                                           closeTopics="closeDialog" modal="true"
                                           resizable="false"
                                           height="350" width="600" autoOpen="false"
                                           title="Saving ...">
                                    Please don't close this window, server is processing your request ...
                                    </br>
                                    </br>
                                    </br>
                                    <center>
                                        <img border="0"
                                             src="<s:url value="/pages/images/indicator-write.gif"/>"
                                             name="image_indicator_write">
                                    </center>
                                </sj:dialog>

                                <sj:dialog id="info_dialog" openTopics="showInfoDialog2"
                                           modal="true" resizable="false"
                                           height="200" width="400" autoOpen="false"
                                           title="Infomation Dialog"
                                           buttons="{
                                                              'OK':function() {
                                                                      close();
                                                                   }
                                                            }"
                                >
                                    <img border="0"
                                         src="<s:url value="/pages/images/icon_success.png"/>"
                                         name="icon_success">
                                    Record has been saved successfully.
                                </sj:dialog>
                                <sj:dialog id="error_dialog" openTopics="showErrorDialog"
                                           modal="true" resizable="false"
                                           height="250" width="600" autoOpen="false"
                                           title="Error Dialog"
                                           buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); }
                                                                    }"
                                >
                                    <div class="alert alert-error fade in">
                                        <label class="control-label" align="left">
                                            <img border="0"
                                                 src="<s:url value="/pages/images/icon_error.png"/>"
                                                 name="icon_error"> System Found : <p
                                                id="errorMessage"></p>
                                        </label>
                                    </div>
                                </sj:dialog>

                                <sj:dialog id="error_validation_dialog"
                                           openTopics="showErrorValidationDialog"
                                           modal="true" resizable="false"
                                           height="280" width="500" autoOpen="false"
                                           title="Warning"
                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                >
                                    <div class="alert alert-error fade in">
                                        <label class="control-label" align="left">
                                            <img border="0"
                                                 src="<s:url value="/pages/images/icon_error.png"/>"
                                                 name="icon_error"> Please check this field
                                            :
                                            <br/>
                                            <center>
                                                <div id="errorValidationMessage"></div>
                                            </center>
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
</div>
<%--</table>--%>
</body>
</html>

<script>
        $('#rekruitmenStatusId').val(document.getElementById('calonPegawaiId32').value);
        function callSearch2() {
            //alert('okok');
            window.location.reload(true);
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }
        $('#cpiddoc').val(document.getElementById('calonPegawaiId32').value);
        var id = $('#calonPegawaiId32').val();
        dwr.engine.setAsync(false);
        RekruitmenAction.searchProfilPhoto(id, function (listdata) {
            $("#myImage").attr("src",listdata);
        });
            $('#statusKeluarga').change(function(){
                $('#jumlahAnak').val("0");
            });
            $('#btnAddStudy').click(function () {
                $('#myFormStudy')[0].reset();
                console.log($('#myFormStudy')[0]);
                $('#modal-edit-study').modal('show');
                $('#myFormStudy').attr('action', 'addStudy');
                $('#modal-edit-study').find('.modal-title').text('Add Study');
            });
            $('#btnAddDocument').click(function () {
                $('#modal-edit-document').modal('show');
                $('#myFormDocument').attr('id', document.getElementById('calonPegawaiId').value);
                $('#modal-edit-document').find('.modal-title').text('Upload Document');
            });
            $('#tanggalLahir').datepicker({
                dateFormat: 'dd-mm-yy',
                changeMonth: true,
                changeYear: true,
                yearRange: "1980:2018"
            });
            window.loadFoto=function(){
                dwr.engine.setAsync(false);
                var id = $('#calonPegawaiId').val();
                RekruitmenAction.searchProfilPhotoBySession(function (listdata) {
                    $("#profile-image1").attr("src",listdata.fotoUpload);
                })
            };
            window.loadRekruitmen = function () {
                //alert(nip);
                $('.rekruitmenStudyTable').find('tbody').remove();
                $('.rekruitmenStudyTable').find('thead').remove();
                $('.rekruitmenDocumentTable').find('tbody').remove();
                $('.rekruitmenDocumentTable').find('thead').remove();
                dwr.engine.setAsync(false);
                var tmp_table_study = "";
                RekruitmenAction.searchRekruitmenStudy(function (listdata) {
                    tmp_table_study = "<thead style='font-size: 14px' ><tr class='active'>" +
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>" +
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>View</th>" +
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Jenjang</th>" +
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Nama Sekolah</th>" +
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Tahun Awal</th>" +
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tahun Akhir</th>" +
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nilai</th>" +
                        "</tr></thead>";
                    var i = i;
                    $.each(listdata, function (i, item) {
                        tmp_table_study += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' +
                            "<a href='javascript:;' class ='item-view-study' data ='" + item.studyName + "' >" +
                            "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>" +
                            '</a>' +
                            '</td>' +
                            '<td align="center">' + item.tipeStudy + '</td>' +
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
                var calonPegawaiId = document.getElementById("calonPegawaiId").value;
                RekruitmenAction.searchRekruitmenDocument(calonPegawaiId, function (listdata) {
                    tmp_table_document = "<thead style='font-size: 14px' ><tr class='active'>" +
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>" +
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>View</th>" +
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Document</th>" +
                        "</tr></thead>";
                    var i = i;
                    $.each(listdata, function (i, item) {
                        tmp_table_document += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' +
                            "<a href='javascript:;' class ='item-view-document' data ='" + item.uploadFile + "' judul ='" + item.documentName + "' >" +
                            "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>" +
                            '</a>' +
                            '</td>' +
                            '<td align="center">' + item.documentName + '</td>' +
                            "</tr>";
                    });
                    $('.rekruitmenDocumentTable').append(tmp_table_document);
                });
            };
            $('#btnSaveStudy').click(function () {
                var url = $('#myFormStudy').attr('action');
                var data = $('#myFormStudy').serialize();

                var Cpid = document.getElementById("calonPegawaiId").value;
                var tipeStudy = document.getElementById("jenjang").value;
                var studyName = document.getElementById("namaSekolah").value;
                var tahunAwal = document.getElementById("tahunAwal").value;
                var tahunAkhir = document.getElementById("tahunAkhir").value;
                var nilai = document.getElementById("nilai").value;

                //alert(personName + branchName + positionName + divisiName);
                if (url == 'addStudy') {
                    if (Cpid != '') {
                        if (confirm('Are you sure you want to save this Record?')) {
                            dwr.engine.setAsync(false);
                            RekruitmenAction.saveAddStudy(Cpid, tipeStudy, studyName, tahunAwal, tahunAkhir, nilai, function (listdata) {
                                alert('Data Successfully Added');
                                $('#modal-edit-study').modal('hide');
                                $('#myFormStudy')[0].reset();
                                loadRekruitmen();
                            });
                        }
                    } else {
                        alert('Calon Pegawai Id must be Entry');
                    }
                } else if (url == 'editStudy') {
                    if (confirm('Are you sure you want to save this Record?')) {
                        dwr.engine.setAsync(false);
                        RekruitmenAction.saveEditStudy(Cpid, tipeStudy, studyName, tahunAwal, tahunAkhir, nilai, function (listdata) {
                            alert('Data Successfully Updated');
                            $('#modal-edit-study').modal('hide');
                            $('#myFormStudy')[0].reset();
                            loadRekruitmen();
                        });
                    }
                } else {
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
            $('#file').change(function () {
                var cpid = document.getElementById('calonPegawaiId').value;
                if (cpid != null && cpid != "") {
                    $('#cpiddoc').val(cpid);
                }
                else {
                    alert("Calon Pegawai ID is Empty");
                    $('#file').val("");
                }
            });

            $('.rekruitmenStudyTable').on('click', '.item-view-study', function () {
                var id = $(this).attr('data');
                dwr.engine.setAsync(false);
                RekruitmenAction.searchRekruitmenStudyPerson(id, function (listdata) {
                    $.each(listdata, function (i, item) {
                        $('#namaSekolahView').val(item.studyName);
                        $('#nilaiView').val(item.nilai);
                        $('#jenjangView').val(item.tipeStudy).change();
                        $('#tahunAwalView').val(item.stTahunAwal).change();
                        $('#tahunAkhirView').val(item.stTahunAkhir).change();
                    });
                });

                $('#modal-view-study').find('.modal-title').text('View Study');
                $('#modal-view-study').modal('show');
                $('#ViewStudy').attr('action', 'editPerson');
            });
            $('.rekruitmenDocumentTable').on('click', '.item-view-document', function () {
                var id = $(this).attr('data');
                var judul = $(this).attr('judul');
                dwr.engine.setAsync(false);
                $("#my-image").attr("src", "/hris/pages/upload/file/rekruitmen/" + id);
                $('#modal-view-document').find('.modal-title').text(judul);
                $('#modal-view-document').modal('show');
                $('#ViewDocument').attr('action', 'editPerson');
            });
            $('#profile-image1').on('click', function () {
                $('#profile-image-upload').click();
            });

            $('[data-toggle="popover"]').popover();

            $("#profile-image-upload").on('change',function(){
                $('#saveProfil').click();
            });
            loadRekruitmen();
        window.listPosisi = function(branch, divisi){
            var branch = document.getElementById("branchId5").value;
            var divisi = document.getElementById("departmentId5").value;
            $('#positionId5').empty();
            PositionAction.searchPosition2(branch, divisi, function(listdata){
                $.each(listdata, function (i, item) {
                    $('#positionId5').append($("<option></option>")
                        .attr("value",item.positionId)
                        .text(item.positionName));
                });
            });
        };
        window.listTipePegawai = function(){
            var branch = document.getElementById("branchId5").value;
            TipePegawaiAction.searchTipePegawai(branch, function(listdata){
                $.each(listdata, function (i, item) {
                    $('#tipePegawai1').append($("<option></option>")
                        .attr("value",item.tipePegawaiId)
                        .text(item.tipePegawaiName));
                });
            });
        };
        $('#tipePegawai1').empty();
        listTipePegawai();
        $('#branchId5').change(function () {
            $('#tipePegawai1').empty();
            listTipePegawai();
        })
</script>

