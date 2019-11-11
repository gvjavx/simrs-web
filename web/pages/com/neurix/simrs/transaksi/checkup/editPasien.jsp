<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <style>
        .kv-avatar .krajee-default.file-preview-frame, .kv-avatar .krajee-default.file-preview-frame:hover {
            margin: 0;
            padding: 0;
            border: none;
            box-shadow: none;
            text-align: center;
        }

        .kv-avatar {
            display: inline-block;
        }

        .kv-avatar .file-input {
            display: table-cell;
            width: 213px;
        }

        .kv-reqd {
            color: red;
            font-family: monospace;
            font-weight: normal;
        }
    </style>
    <style>
        .pagebanner {
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }

        .pagelinks {
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }

        #tahunAwal {
            z-index: 2000 !important
        }

        #tahunAkhir {
            z-index: 2000 !important
        }
    </style>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TipePegawaiAction.js"/>'></script>
    <script type="text/javascript">
        $(document).ready(function () {
            window.close = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href = "<s:url action='search_rekruitmen.action'/>";
            };
            window.close2 = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
            };
            function callSearch2() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.reload(true);
            }

            $.subscribe('beforeProcessSave', function (event, data) {
                var capeg = document.getElementById("calonPegawaiId").value;
                var namacalon = document.getElementById("calonPegawaiName").value;
                var nohp = document.getElementById("noTelp").value;
                var alamat = document.getElementById("alamat").value;
                var provinsi = document.getElementById("provinsi11").value;
                var kabupaten = document.getElementById("kabupaten11").value;
                var kecamatan = document.getElementById("kecamatan11").value;
                var desa = document.getElementById("desa11").value;
                var rtrw = document.getElementById("rtRw").value;
                var tempatlhr = document.getElementById("tempatLahir").value;
                var tgllhr = document.getElementById("tanggalLahir").value;
                var statusklg = document.getElementById("statusKeluarga").value;
                var clnjbtn = document.getElementById("positionId").value;
                var unit = document.getElementById("branchId").value;
                var statuspgw = document.getElementById("statusPegawai").value;
                var tipepgw = document.getElementById("tipePegawai1").value;
                var stsrkrtmn = document.getElementById("statusRekruitmen12").value;
                var keterangan = document.getElementById("keterangan").value;


                if (capeg != '' && namacalon != '' && nohp != '' && alamat != '' && provinsi != '' && kabupaten != '' &&
                        kecamatan != '' && desa != '' && rtrw != '' && tempatlhr != '' && tgllhr != '' && clnjbtn != '' &&
                        unit != '' && statuspgw != '' && tipepgw != '' && statusklg != '' && stsrkrtmn != '' && keterangan != '') {
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

                    if (capeg == '') {
                        msg += 'Field <strong>Calon Pegawai Id</strong> is required.' + '<br/>';
                    }
                    if (namacalon == '') {
                        msg += 'Field <strong>Nama Calon Pegawai</strong> is required.' + '<br/>';
                    }
                    if (nohp == '') {
                        msg += 'Field <strong>No Telepon</strong> is required.' + '<br/>';
                    }
                    if (alamat == '') {
                        msg += 'Field <strong>Alamat</strong> is required.' + '<br/>';
                    }
                    if (provinsi == '') {
                        msg += 'Field <strong>Provinsi</strong> is required.' + '<br/>';
                    }
                    if (kabupaten == '') {
                        msg += 'Field <strong>Kabupaten</strong> is required.' + '<br/>';
                    }
                    if (kecamatan == '') {
                        msg += 'Field <strong>Kecamatan</strong> is required.' + '<br/>';
                    }
                    if (desa == '') {
                        msg += 'Field <strong>Desa</strong> is required.' + '<br/>';
                    }
                    if (rtrw == '') {
                        msg += 'Field <strong>RT RW</strong> is required.' + '<br/>';
                    }
                    if (tempatlhr == '') {
                        msg += 'Field <strong>Tempat Lahir</strong> is required.' + '<br/>';
                    }
                    if (tgllhr == '') {
                        msg += 'Field <strong>tanggal Lahir</strong> is required.' + '<br/>';
                    }
                    if (statusglg == '') {
                        msg += 'Field <strong>Status Giling</strong> is required.' + '<br/>';
                    }
                    if (statusklg == '') {
                        msg += 'Field <strong>Status Keluarga</strong> is required.' + '<br/>';
                    }
                    if (statuspgw == '') {
                        msg += 'Field <strong>Status Pegawai</strong> is required.' + '<br/>';
                    }
                    if (clnjbtn == '') {
                        msg += 'Field <strong>Calon Jabatan</strong> is required.' + '<br/>';
                    }
                    if (unit == '') {
                        msg += 'Field <strong>Unit</strong> is required.' + '<br/>';
                    }
                    if (tipepgw == '') {
                        msg += 'Field <strong>Tipe Pegawai</strong> is required.' + '<br/>';
                    }
                    if (stsrkrtmn == '') {
                        msg += 'Field <strong>Status Rekruitmen</strong> is required.' + '<br/>';
                    }
                    if (keterangan == '') {
                        msg += 'Field <strong>Keterangan</strong> is required.' + '<br/>';
                    }
                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');

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
                $('#view_dialog_menu').dialog('close');
            }
        });
    </script>

</head>

<body class="hold-transition skin-blue sidebar-mini">
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
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Add Rekruitmen Form</h3>
                    </div>
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <div align="center" style="float: left;margin-left: 10%;margin-top: 2%">
                                    <s:url id="urlProcess" namespace="/rekruitmen" action="uploadProfil_rekruitmen" includeContext="false"/>
                                    <s:form id="myFormProfile" enctype="multipart/form-data" method="post" action="%{urlProcess}" theme="simple"
                                            cssClass=" form-horizontal">
                                        <s:textfield id="calonPegawaiId12" name="rekruitmen.calonPegawaiId"
                                                     required="false" readonly="true"
                                                     cssClass="hidden form-control"/>
                                        <div class="well" style="background-color:#dcdcdc !important">
                                            <img width="150px" alt="User Pic" src="/hris/pages/images/nobody_m.original.jpg" id="profile-image1" class="img-circle img-responsive">
                                            <input id="profile-image-upload" class="hidden" type="file" name="fileUpload">
                                            <div style="color:#999;" >Click To Select</div>
                                        </div>
                                        <div id="actions" class="form-actions">
                                            <table align="center" class="hidden">
                                                <tr>
                                                    <td>
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="myFormProfile"
                                                                   id="saveProfil" name="save" onCompleteTopics="loadfoto" onSuccessTopics="loadfoto">
                                                            <i class="fa fa-save"></i>
                                                        </sj:submit>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </s:form>
                                </div>
                                <s:form id="rekruitmenForm" method="post" theme="simple" namespace="/rekruitmen"
                                        action="save_rekruitmen.action" cssClass="form-horizontal">
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
                                                <label class="control-label">
                                                    <small>Calon Pegawai Id :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <div class="input-group">
                                                    <s:textfield id="calonPegawaiId" name="rekruitmen.calonPegawaiId"
                                                                 required="false" readonly="true"
                                                                 cssClass="form-control"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Nama Calon Pegawai :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="calonPegawaiName"
                                                                 name="rekruitmen.namaCalonPegawai" required="false"
                                                                 readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Gelar Depan :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="gelarDepan" name="rekruitmen.gelarDepan"
                                                                 required="false" readonly="false"
                                                                 cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Gelar Belakang :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="gelarBelakang" name="rekruitmen.gelarBelakang"
                                                                 required="false" readonly="false"
                                                                 cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>No. KTP :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="noKtp" type="number" name="rekruitmen.noKtp" required="false"
                                                                 readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>No. HP :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <div class="input-group">
                                                        <div class="input-group-addon">
                                                            <div id="notelpawal">
                                                                +62
                                                            </div>
                                                        </div>
                                                        <s:textfield id="noTelp"  type="number" name="rekruitmen.noTelp"
                                                                     required="false" readonly="false"
                                                                     cssClass="form-control"/>
                                                    </div>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Alamat :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textarea rows="4" id="alamat" name="rekruitmen.alamat"
                                                                required="false" readonly="false"
                                                                cssClass="form-control"/>
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
                                                    <s:textfield id="provinsi" name="" required="true" disabled="false"
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
                                                    <s:textfield id="kabupaten" name="" required="true" disabled="false"
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
                                                    <s:textfield id="kecamatan" name="" required="true" disabled="false"
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
                                                    <s:textfield id="desa" name="" required="true" disabled="false"
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
                                                <label class="control-label">
                                                    <small>Rt / Rw :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="rtRw" name="rekruitmen.rtRw" required="true"
                                                                 disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Tempat / Tanggal Lahir :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <s:textfield id="tempatLahir" name="rekruitmen.tempatLahir"
                                                                         required="true" cssClass="form-control"/>
                                                        </td>
                                                        <td> /</td>
                                                        <td>
                                                            <div class="input-group date">
                                                                <div class="input-group-addon">
                                                                    <i class="fa fa-calendar"></i>
                                                                </div>
                                                                <s:textfield id="tanggalLahir"
                                                                             name="rekruitmen.stTanggalLahir"
                                                                             cssClass="form-control pull-right"
                                                                             required="false"/>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Jenis Kelamin :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'L':'Laki-Laki','P':'Perempuan'}"
                                                              id="jenisKelamin" name="rekruitmen.jenisKelamin"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
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
                                                    <s:select list="#{'K':'Keluarga','B':'Bujang'}" id="statusKeluarga"
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
                                                    <s:textfield id="jumlahAnak" type="number" name="rekruitmen.jumlahAnak"
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
                                                    <s:select list="#initComboBranch.listOfComboBranch" id="branchId"
                                                              name="rekruitmen.branchId" onchange="listDivisi()"
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
                                                    <s:select list="#comboDivisi.listComboDepartment" id="departmentId"
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
                                                    <s:select list="#comboPosition.listOfComboPosition" id="positionId"
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
                                                    <%--<s:action id="initComboTipe" namespace="/tipepegawai"--%>
                                                              <%--name="searchTipePegawai_tipepegawai"/>--%>
                                                    <%--<s:select list="#initComboTipe.listComboTipePegawai"--%>
                                                              <%--id="tipePegawai1" name="rekruitmen.tipePegawai"--%>
                                                              <%--listKey="tipePegawaiId" listValue="tipePegawaiName"--%>
                                                              <%--headerKey="" headerValue="[Select one]"--%>
                                                              <%--cssClass="form-control"/>--%>
                                                        <s:select
                                                                list="#{}"
                                                                id="tipePegawai1" name="rekruitmen.tipePegawai"
                                                                headerKey="" headerValue="[Select one]"
                                                                cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Status Giling :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select
                                                            list="#{'DMG':'Dalam Masa Giling','LMG':'Luar Masa Giling'}"
                                                            id="statusGiling" name="rekruitmen.statusGiling"
                                                            headerKey="" headerValue="[Select one]"
                                                            cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    &nbsp;
                                    &nbsp;
                                    &nbsp;
                                    &nbsp;
                                    <br>
                                    <h4>
                                        List Study
                                        <button
                                                id="btnAddStudy" type="button" class="btn btn-default btn-info"
                                                data-toggle="modal" data-target="#modal-tambah-study"><i
                                                class="fa fa-plus"></i>
                                        </button>
                                    </h4>
                                    <center>
                                        <table id="showdata" width="80%">
                                            <tr>
                                                <td align="center">
                                                    <table style="width: 80%;"
                                                           class="rekruitmenStudyTable table table-bordered">
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>
                                    <br>
                                    <h4>
                                        List Document ( Upload Document )
                                        <button
                                                id="btnAddDocument" type="button" class="btn btn-default btn-info"
                                                data-toggle="modal" data-target="#modal-tambah-document"><i
                                                class="fa fa-plus"></i>
                                        </button>
                                    </h4>
                                    <center>
                                        <table id="showdata1" width="80%">
                                            <tr>
                                                <td align="center">
                                                    <table style="width: 80%;"
                                                           class="rekruitmenDocumentTable table table-bordered">
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>
                                    <br>
                                    <table>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Status Rekruitmen :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="initComboStatus" namespace="/rekruitmen"
                                                              name="searchStatusRekruitmen_rekruitmen"/>
                                                    <s:select list="#initComboStatus.listComboStatusRekruitmen"
                                                              id="statusRekruitmen12"
                                                              name="rekruitmenStatus.statusRekruitmen"
                                                              listKey="statusRekruitmentId"
                                                              listValue="statusRekruitmentName"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <table>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Keterangan :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textarea rows="4" cols="70" id="keterangan"
                                                                name="rekruitmenStatus.keterangan" required="false"
                                                                readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br><br>

                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit targets="crud" type="button" cssClass="btn btn-primary"
                                                               formIds="rekruitmenForm" id="save" name="save"
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
                                                                    action="add_rekruitmen.action"/>'">
                                                        <i class="fa fa-refresh"></i> Reset
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
                        <label class="control-label col-sm-3">Jenjang : </label>

                        <div class="col-sm-6">
                            <s:select
                                    list="#{'SD':'SD/Sederajat','SMP':'SMP/Sederajat','SMA':'SMA/Sederajat','S1':'S1','S2':'S2','S3':'S3'}"
                                    id="jenjang" name="" headerKey="" headerValue="[Select one]"
                                    cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Nama Sekolah : </label>

                        <div class="col-sm-6">
                            <input type="text" class="form-control namaSekolah" id="namaSekolah" name="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Tahun Awal : </label>

                        <div class="col-sm-6">
                            <table>
                                <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriode_rekruitmen"/>
                                <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunAwal"
                                          name="rekruitmen.tahunAwal" required="true" headerKey=""
                                          headerValue="[Select one]"/>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Tahun Akhir : </label>

                        <div class="col-sm-6">
                            <table>
                                <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriode_rekruitmen"/>
                                <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode"
                                          id="tahunAkhir" name="rekruitmen.tahunAkhir" required="true" headerKey=""
                                          headerValue="[Select one]"/>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Nilai : </label>

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
<div id="modal-view-study" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Modal View Study</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="ViewStudy">

                    <div class="form-group">
                        <label class="control-label col-sm-3">Jenjang : </label>

                        <div class="col-sm-6">
                            <s:select
                                    list="#{'SD':'SD/Sederajat','SMP':'SMP/Sederajat','SMA':'SMA/Sederajat','S1':'S1','S2':'S2','S3':'S3'}"
                                    id="jenjangView" name="" readonly="true" headerKey="" headerValue="[Select one]"
                                    disabled="true" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Nama Sekolah : </label>

                        <div class="col-sm-6">
                            <input type="text" class="form-control namaSekolah" readonly="true" id="namaSekolahView"
                                   name="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Tahun Awal : </label>

                        <div class="col-sm-6">
                            <table>
                                <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriode_rekruitmen"/>
                                <s:select cssClass="form-control" readonly="true" disabled="true"
                                          list="#comboPeriode.listOfComboPeriode" id="tahunAwalView"
                                          name="rekruitmen.tahunAwal" required="true" headerKey=""
                                          headerValue="[Select one]"/>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Tahun Akhir : </label>

                        <div class="col-sm-6">
                            <table>
                                <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriode_rekruitmen"/>
                                <s:select cssClass="form-control" readonly="true" disabled="true"
                                          list="#comboPeriode.listOfComboPeriode" id="tahunAkhirView"
                                          name="rekruitmen.tahunAkhir" required="true" headerKey=""
                                          headerValue="[Select one]"/>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Nilai : </label>

                        <div class="col-sm-6">
                            <input type="text" class="form-control nilai" readonly="true" id="nilaiView" name="nilai">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-view-document" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">View Document</h4>
            </div>
            <div class="modal-body">
                <img src="" class="img-responsive" id="my-image">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<sj:dialog id="view_dialog_menu" openTopics="showDialogMenuEdit" modal="true"
           height="500" width="900" autoOpen="false"
           title="View Document">
</sj:dialog>
<div id="modal-edit-document" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Document</h4>
            </div>
            <div class="modal-body">
                <s:url id="urlProcess" namespace="/rekruitmen" action="addRekruitmenDoc_rekruitmen"
                       includeContext="false"/>
                <s:form id="myFormDocument12" enctype="multipart/form-data" method="post" action="%{urlProcess}"
                        theme="simple" cssClass="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-3">Nama Document : </label>

                        <div class="col-sm-8">
                            <s:textfield id="documentName" name="rekruitmenUploadDoc.documentName" required="true"
                                         disabled="false" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">File : </label>

                        <div class="col-sm-8">
                            <input type="file" id="file" class="form-control" name="fileUpload"/>
                            <input type="text" id="cpiddoc" class="form-control" accept="application/pdf,image/jpeg"
                                   name="rekruitmenUploadDoc.calonPegawaiId" readonly style="display: none;"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="myFormDocument12"
                                   id="saveDocument" name="save" onBeforeTopics="beforeProcessSaveDocument"
                                   onCompleteTopics="closeDialog,successDialogDocument"
                                   onSuccessTopics="successDialogDocument" onErrorTopics="errorDialog">
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
    $(document).ready(function () {
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
                    if (tipeStudy!=''&&studyName!=''&&tahunAwal!=''&&tahunAkhir!=''&&nilai!=''){
                        if (confirm('Are you sure you want to save this Record?')) {
                            dwr.engine.setAsync(false);
                            RekruitmenAction.saveAddStudy(Cpid, tipeStudy, studyName, tahunAwal, tahunAkhir, nilai, function (listdata) {
                                alert('Data Successfully Added');
                                $('#modal-edit-study').modal('hide');
                                $('#myFormStudy')[0].reset();
                                loadRekruitmen();
                            });
                        }
                    } else{
                        alert("masih ada yang kosong , cek kembali ")
                    }

                } else {
                    alert('Calon Pegawai Id masih kosong');
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
    });
    window.listPosisi = function(branch, divisi){
        var branch = document.getElementById("branchId").value;
        var divisi = document.getElementById("departmentId").value;
        $('#positionId').empty();
        PositionAction.searchPosition2(branch, divisi, function(listdata){
            $.each(listdata, function (i, item) {
                $('#positionId').append($("<option></option>")
                    .attr("value",item.positionId)
                    .text(item.positionName));
            });
        });
    };
    window.listDivisi= function(){
        var branch = document.getElementById("branchId").value;
        $('#departmentId').empty();
        PositionAction.searchDivisi(branch, function(listdata){
            $.each(listdata, function (i, item) {
                $('#departmentId').append($("<option></option>")
                    .attr("value",item.departmentId)
                    .text(item.departmentName));
            });
        });
        listPosisi();
    };
    window.listTipePegawai = function(){
        var branch = document.getElementById("branchId").value;
        TipePegawaiAction.searchTipePegawai(branch, function(listdata){
            $.each(listdata, function (i, item) {
                $('#tipePegawai1').append($("<option></option>")
                    .attr("value",item.tipePegawaiId)
                    .text(item.tipePegawaiName));
            });
        });
    }
    $('#branchId').change(function () {
        $('#tipePegawai1').empty();
        listTipePegawai();
    })
</script>