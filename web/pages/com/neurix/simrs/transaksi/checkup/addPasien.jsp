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
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
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
            Tambah Pasien
            <small>e-HEALTH</small>
        </h1>
    </section>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Tambah Pasien</h3>
                    </div>
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="addCheckupForm" method="post" theme="simple" namespace="/checkup"
                                        action="saveAdd_checkup.action" cssClass="form-horizontal">
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
                                                                 name="headerCheckup.noBpjs" required="false"
                                                                 readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>ID Pasien</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                                 name="headerCheckup.idPasien" required="false"
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
                                                    <s:textfield id="no_ktp" name="headerCheckup.noKtp"
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
                                                    <s:textfield id="nama_pasien" name="headerCheckup.nama"
                                                                 required="false" readonly="false"
                                                                 cssClass="form-control" cssStyle="margin-top: 7px"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Jenis Kelamin</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'L':'Laki-Laki','P':'Perempuan'}" cssStyle="margin-top: 7px"
                                                              id="jenis_kelamin" name="headerCheckup.jenisKelamin"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Tempat / Tanggal Lahir</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <s:textfield cssStyle="margin-top: 7px" id="tempat_Lahir" name="headerCheckup.tempatLahir"
                                                                         required="true" cssClass="form-control"/>
                                                        </td>
                                                        <td> /</td>
                                                        <td>
                                                            <div class="input-group date" style="margin-top: 7px">
                                                                <div class="input-group-addon" >
                                                                    <i class="fa fa-calendar"></i>
                                                                </div>
                                                                <s:textfield id="tanggal_lahir"
                                                                             name="headerCheckup.tglLahir"
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
                                                    <s:select list="#{'Islam':'Islam','Kristen':'Kristen'}" cssStyle="margin-top: 7px"
                                                              id="agama" name="headerCheckup.agama"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
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
                                                    <s:textfield id="suku" type="text" name="headerCheckup.suku" required="false"
                                                                 readonly="false" cssClass="form-control" cssStyle="margin-top: 7px"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Alamat</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textarea rows="4" id="jalan" name="headerCheckup.jalan"
                                                                required="false" readonly="false"
                                                                cssClass="form-control" cssStyle="margin-top: 7px"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Provinsi</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="provinsi" name="" required="true" disabled="false"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="provinsi11"
                                                                 name="headerCheckup.provinsiId" required="true"
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
                                                    <small>Kabupaten</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="kabupaten" name="" required="true" disabled="false"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="kabupaten11"
                                                                 name="headerCheckup.kotaId" required="true"
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
                                                    <small>Kecamatan</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="kecamatan" name="" required="true" disabled="false"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="kecamatan11"
                                                                 name="headerCheckup.kecamatanId" required="true"
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
                                                    <small>Desa</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="desa" name="" required="true" disabled="false"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="desa11"
                                                                 name="headerCheckup.desaId" required="true"
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
                                                    <small>Rt / Rw</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="rt_rw" name="headerCheckup.rtRw" required="true"
                                                                 disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <br>
                                        <br>
                                        <tr>
                                        <td>
                                            <label class="control-label">
                                                <h4>Data Penanggung Jawab</h4>
                                            </label>
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Nama</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="nama_penanggung" name="headerCheckup.namaPenanggung" required="true"
                                                                 disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>No Telp</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="no_telp" name="headerCheckup.noTelp" required="true"
                                                                 disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Hubungan</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'Ayah':'Ayah','Ibu':'Ibu','Kakak':'Kakak','Adik':'Adik'}" cssStyle="margin-top: 7px"
                                                              id="hubungan" name="headerCheckup.hubungan"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <h4>Data Kunjungan</h4>
                                                </label>
                                            </td>
                                            <td>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Poli</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'01':'Poli Anak','02':'Poli Mata','03':'Poli Ibu','04':'Poli Umum'}" cssStyle="margin-top: 7px"
                                                              id="poli" name="headerCheckup.hubungan"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Dokter</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'01':'Dr. Sudirman','02':'Dr. Istoyo','03':'Dr. Tarmajo','04':'Dr. Sutino'}" cssStyle="margin-top: 7px"
                                                              id="id_dokter" name="headerCheckup.idDokter"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Penjamin</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'01':'BPJS','02':'BANK BRI','03':'BANK BNI','04':'OVO'}" cssStyle="margin-top: 7px"
                                                              id="id_penjamin" name="headerCheckup.idJenisPeriksaPasien"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Kunjungan</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="kunjungan" name="headerCheckup.jenisKunjungan" required="true"
                                                                 disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Perujuk/Asal</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="perujuk" name="headerCheckup.perujuk" required="true"
                                                                 disabled="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small></small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <div style="margin-top: 10px">
                                                    <sj:submit targets="crusd" type="button" cssClass="btn btn-primary"
                                                               formIds="addCheckupForm" id="save" name="save"
                                                               onBeforeTopics="beforeProcessSave"
                                                               onCompleteTopics="closeDialog,successDialog2"
                                                               onSuccessTopics="successDialog2"
                                                               onErrorTopics="errorDialog">
                                                        <i class="fa fa-check"></i>
                                                        Save
                                                    </sj:submit>&nbsp;
                                                    <button type="button" class="btn btn-danger"
                                                            onclick="window.location.href='<s:url
                                                                    action="add_rekruitmen.action"/>'">
                                                        <i class="fa fa-refresh"></i> Reset
                                                    </button>
                                                    </div>
                                                </table>
                                            </td>
                                        </tr>

                                    </table>
                                </s:form>
                            </td>
                        </tr>
                    </table>
                    <!-- Your Page Content Here -->

                    <div id="actions" class="form-actions" style="display: none">
                        <table align="center">
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
</script>