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
                window.location.href = "<s:url action='search_pasien.action'/>";
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
                var noBpjs = document.getElementById("no_bpjs").value;
                // var idPasien = document.getElementById("id_pasien").value;
                var noKtp = document.getElementById("no_ktp").value;
                var namaPasien = document.getElementById("nama_pasien").value;
                var jenisKelamin = document.getElementById("jenis_kelamin").value;
                var tempatLahir = document.getElementById("tempat_Lahir").value;
                var tglLahir = document.getElementById("tanggal_lahir").value;
                var agama = document.getElementById("agama").value;
                var noTelp = document.getElementById("no_telp").value;
                var suku = document.getElementById("suku").value;
                var alamat = document.getElementById("alamat").value;
                var provinsi = document.getElementById("provinsi11").value;
                var kabupaten = document.getElementById("kabupaten11").value;
                var kecamatan = document.getElementById("kecamatan11").value;
                var desa = document.getElementById("desa11").value;

                if (noBpjs != '' && noKtp != '' && namaPasien != '' && jenisKelamin != '' && tempatLahir != ''
                        && tglLahir != '' && agama != '' && noTelp != '' && suku != '' && alamat != '' && provinsi != '' && kabupaten != ''  && kecamatan != ''
                        && desa != '') {
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

                    if (noBpjs == '') {
                        msg += 'Field <strong>No. BPJS</strong> is required.' + '<br/>';
                    }
                    if (noKtp == '') {
                        msg += 'Field <strong>No. KTP</strong> is required.' + '<br/>';
                    }
                    if (namaPasien == '') {
                        msg += 'Field <strong>Nama Pasien</strong> is required.' + '<br/>';
                    }
                    if (jenisKelamin == '') {
                        msg += 'Field <strong>Jenis Kelamin</strong> is required.' + '<br/>';
                    }
                    if (tempatLahir == '') {
                        msg += 'Field <strong>Tempat Lahir</strong> is required.' + '<br/>';
                    }
                    if (tglLahir == '') {
                        msg += 'Field <strong>Tgl Lahir</strong> is required.' + '<br/>';
                    }
                    if (agama == '') {
                        msg += 'Field <strong>Agama</strong> is required.' + '<br/>';
                    }
                    if (noTelp == '') {
                        msg += 'Field <strong>No. Telp</strong> is required.' + '<br/>';
                    }
                    if (suku == '') {
                        msg += 'Field <strong>Suku</strong> is required.' + '<br/>';
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
                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');

                }
            });



            // $.subscribe('beforeProcessSaveDocument', function (event, data) {
            //     if (confirm('Do you want to save this record?')) {
            //         event.originalEvent.options.submit = true;
            //         $('#modal-edit-document').modal('hide');
            //         $('#myFormDocument12')[0].reset();
            //         alert('Record has been Deleted successfully.');
            //         loadRekruitmen();
            //     } else {
            //         // Cancel Submit comes with 1.8.0
            //         event.originalEvent.options.submit = false;
            //     }
            // });
            // $.subscribe('successDialogDocument', function (event, data) {
            //     loadRekruitmen();
            // });
            // $.subscribe('loadfoto', function (event, data) {
            //     loadFoto();
            // });
            // $.subscribe('beforeProcessDelete', function (event, data) {
            //     if (confirm('Do you want to delete this record ?')) {
            //         event.originalEvent.options.submit = true;
            //         $.publish('showDialog');
            //
            //     } else {
            //         // Cancel Submit comes with 1.8.0
            //         event.originalEvent.options.submit = false;
            //     }
            // });
            //
            //

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
            Add Pasien
            <small>e-HEALTH</small>
        </h1>
    </section>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Add Pasien Form</h3>
                    </div>
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="addPasienForm" method="post" theme="simple" namespace="/pasien"
                                        action="saveAdd_pasien.action" cssClass="form-horizontal">
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
                                                    <h4>Data Pasien</h4>
                                                </label>
                                            </td>
                                            <td>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>No BPJS</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="no_bpjs" cssStyle="margin-top: 7px"
                                                                 name="pasien.noBpjs" required="false"
                                                                 readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>No KTP</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="no_ktp" name="pasien.noKtp"
                                                                 required="false" readonly="false"
                                                                 cssClass="form-control" cssStyle="margin-top: 7px"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Nama Pasien</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="nama_pasien" name="pasien.nama"
                                                                 required="false" readonly="false"
                                                                 cssClass="form-control" cssStyle="margin-top: 7px"/>
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
                                                    <s:select list="#{'L':'Laki-Laki','P':'Perempuan'}" cssStyle="margin-top: 7px"
                                                              id="jenis_kelamin" name="pasien.jenisKelamin"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
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
                                                            <s:textfield cssStyle="margin-top: 7px" id="tempat_Lahir" name="pasien.tempatLahir"
                                                                         required="true" cssClass="form-control"/>
                                                        </td>
                                                        <td> /</td>
                                                        <td>
                                                            <div class="input-group date" style="margin-top: 7px">
                                                                <div class="input-group-addon" >
                                                                    <i class="fa fa-calendar"></i>
                                                                </div>
                                                                <s:textfield id="tanggal_lahir"
                                                                             name="pasien.tglLahir"
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
                                                    <small>Agama</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'Islam':'Islam','Kristen':'Kristen','Hindu':'Hindu','Budha':'Budha'}" cssStyle="margin-top: 7px"
                                                              id="agama" name="pasien.agama"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>No. Telp</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="no_telp" type="text" name="pasien.noTelp" required="false"
                                                                 readonly="false" cssClass="form-control" cssStyle="margin-top: 7px"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Suku</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="suku" type="text" name="pasien.suku" required="false"
                                                                 readonly="false" cssClass="form-control" cssStyle="margin-top: 7px"/>
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
                                                    <s:textarea rows="4" id="alamat" name="pasien.alamat"
                                                                required="false" readonly="false"
                                                                cssClass="form-control" cssStyle="margin-top: 7px"/>
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
                                                    <s:textfield cssStyle="margin-top: 7px" id="provinsi" name="" required="true" disabled="false"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="provinsi11"
                                                                 name="pasien.provinsiId" required="true"
                                                                 disabled="false" cssClass="form-control" />
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
                                                    <s:textfield cssStyle="margin-top: 7px" id="kabupaten" name="" required="true" disabled="false"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="kabupaten11"
                                                                 name="pasien.kotaId" required="true"
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
                                                    <s:textfield cssStyle="margin-top: 7px" id="kecamatan" name="" required="true" disabled="false"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="kecamatan11"
                                                                 name="pasien.kecamatanId" required="true"
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
                                                    <s:textfield cssStyle="margin-top: 7px" id="desa" name="" required="true" disabled="false"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="desa11"
                                                                 name="pasien.desaId" required="true"
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
                                        <br>
                                        <br>
                                    </table>
                                    <br><br>

                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit targets="crud" type="button" cssClass="btn btn-primary"
                                                               formIds="addPasienForm" id="save" name="save"
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
                                                                    action="add_pasien.action"/>'">
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