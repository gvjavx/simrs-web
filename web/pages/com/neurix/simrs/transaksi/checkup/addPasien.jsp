<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript'>

        $.subscribe('beforeProcessSave', function (event, data) {

            var noBpjs         = $('#no_bpjs').val();
            var idPasien        = $('#id_pasien').val();
            var noKtp           = $('#no_ktp').val();
            var namaPasien      = $('#nama_pasien').val();
            var jenisKelamin    = $('#jenis_kelamin').val();
            var tempatLahir     = $('#tempat_lahir').val();
            var tglLahir        = $('#tangggal_lahir').val();
            var jalan           = $('#jalan').val();
            var suku            = $('#suku').val();
            var agama           = $('#agama').val();
            var poli            = $('#poli').val();
            var dokter          = $('#dokter').val();
            var penjamin        = $('#penjamin').val();
            var provinsi        = $('#provinsi11').val();
            var kota            = $('#kabupaten11').val();
            var kecamatan       = $('#kecamatan11').val();
            var desa            = $('#desa11').val();

            if(noBpjs != '' && idPasien != '' && noKtp != ''&&namaPasien != ''
               && jenisKelamin != ''&& tempatLahir != '' && tglLahir != ''
               && jalan != ''&& suku != '' && agama != ''
               && poli != ''&& dokter != '' && penjamin != ''
               && provinsi != ''&& kota != '' && kecamatan != '' && desa != '') {

                if (document.getElementById("id_pasien").value != '') {
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');

                    } else {
                        event.originalEvent.options.submit = false;

                    }
                }
            }else {

                event.originalEvent.options.submit = false;

                var msg = "";
                if (noBpjs == '') {
                    msg = '<strong>No BPJS</strong> harus diisi'+'<br>';
                }
                if (idPasien == '') {
                    msg = msg + '<strong>ID Pasien</strong> harus diisi'+'<br>';
                }
                if (noKtp == '') {
                    msg = msg + '<strong>No KTP</strong> harus diisi'+'<br>';
                }
                if (namaPasien == '') {
                    msg = msg + '<strong>Nama</strong> harus diisi'+'<br>';
                }
                if (jenisKelamin == '') {
                    msg = msg + '<strong>Jenis Kelamin</strong> harus diisi'+'<br>';
                }
                if (tempatLahir == '') {
                    msg = msg + '<strong>Tempat Lahir</strong> harus diisi'+'<br>';
                }
                if (tglLahir == '') {
                    msg = msg + '<strong>Tanggal Lahir</strong> harus diisi'+'<br>';
                }
                if (jalan == '') {
                    msg = msg + '<strong>Jalan</strong> harus diisi'+'<br>';
                }
                if (suku == '') {
                    msg = msg + '<strong>Suku</strong> harus diisi'+'<br>';
                }
                if (agama == '') {
                    msg = msg + '<strong>Agama</strong> harus diisi'+'<br>';
                }
                if (poli == '') {
                    msg = msg + '<strong>Poli</strong> harus diisi'+'<br>';
                }
                if (dokter == '') {
                    msg = msg + '<strong>DokterP</strong> harus diisi'+'<br>';
                }
                if (penjamin == '') {
                    msg = msg + '<strong>Penjamin</strong> harus diisi'+'<br>';
                }
                if (provinsi == '') {
                    msg = msg + '<strong>Provinsi</strong> harus diisi'+'<br>';
                }
                if (kota == '') {
                    msg = msg + '<strong>Kota</strong> harus diisi'+'<br>';
                }
                if (kecamatan == '') {
                    msg = msg + '<strong>Kecamatan</strong> harus diisi'+'<br>';
                }
                if (desa == '') {
                    msg = msg + '<strong>Desa</strong> harus diisi'+'<br>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

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

        function resetField(){
            $('#no_bpjs, #id_pasien, #no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #tanggal_lahir, #jalan, #suku, #agama, #poli, #dokter, #penjamin, #provinsi11, #kabupaten11, #kecamatan11, #desa11, #provinsi, #kabupaten, #kecamatan, #desa').val('');
        }


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

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <%--<h3 class="box-title">Search Form</h3>--%>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="addCheckupForm" enctype="multipart/form-data" method="post" namespace="/checkup" action="saveAdd_checkup.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-2"></label>
                                    <div class="col-sm-3"><h4>Data Pasien</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2">No BPJS</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_bpjs" cssStyle="margin-top: 7px"
                                                     name="headerCheckup.noBpjs" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>

                                    <label class="control-label col-sm-2" for="headerCheckup.jalan">Alamat</label>
                                    <div class="col-sm-4">
                                        <s:textarea id="jalan" rows="3" cssStyle="margin-top: 7px"
                                                     name="headerCheckup.jalan" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>

                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.idPasien">ID Pasien</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="headerCheckup.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                    <label class="control-label col-sm-2" for="headerCheckup.provinsiId">Provinsi</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssStyle="margin-top: 7px" id="provinsi" name="" required="true" disabled="false"
                                                     cssClass="form-control"/>
                                        <s:textfield cssStyle="display: none; margin-top: 7px" id="provinsi11"
                                                     name="headerCheckup.provinsiId" required="true"
                                                     disabled="false" cssClass="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.noKtp">No KTP</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_ktp" cssStyle="margin-top: 7px"
                                                     name="headerCheckup.noKtp" required="true" cssClass="form-control"/>
                                    </div>
                                    <label class="control-label col-sm-2" for="headerCheckup.kotaId">Kota</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssStyle="margin-top: 7px" id="kabupaten" name="" required="true" disabled="false"
                                                     cssClass="form-control"/>
                                        <s:textfield cssStyle="display: none; margin-top: 7px" id="kabupaten11"
                                                     name="headerCheckup.kotaId" required="true"
                                                     disabled="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.nama">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="headerCheckup.nama"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                    <label class="control-label col-sm-2" for="headerCheckup.kecamatanId">Kecamatan</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssStyle="margin-top: 7px" id="kecamatan" name="" required="true" disabled="false"
                                                     cssClass="form-control"/>
                                        <s:textfield cssStyle="display: none; margin-top: 7px" id="kecamatan11"
                                                     name="headerCheckup.kecamatanId" required="true"
                                                     disabled="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.jenisKelamin">Jenis Kelamin</label>
                                    <div class="col-sm-4">
                                        <%--<s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>--%>
                                        <s:select list="#{'L':'Laki-Laki','P':'Perempuan'}" cssStyle="margin-top: 7px"
                                                  id="jenis_kelamin" name="headerCheckup.jenisKelamin"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>
                                    <label class="control-label col-sm-2" for="headerCheckup.desaId">Desa</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssStyle="margin-top: 7px" id="desa" name="" required="true" disabled="false"
                                                     cssClass="form-control"/>
                                        <s:textfield cssStyle="display: none; margin-top: 7px" id="desa11"
                                                     name="headerCheckup.desaId" required="true"
                                                     disabled="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.tempatLahir">Tempat Lahir</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssStyle="margin-top: 7px" id="tempat_Lahir" name="headerCheckup.tempatLahir"
                                                     required="true" cssClass="form-control"/>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" >Tanggal Lahir</label>
                                    <div class="col-sm-4">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                                <s:textfield id="tanggal_lahir" name="headerCheckup.stTglLahir" cssClass="form-control"
                                                required="false"/>
                                        </div>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.agama">Agama</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'Islam':'Islam','Kristen':'Kristen'}" cssStyle="margin-top: 7px"
                                                  id="agama" name="headerCheckup.agama"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.suku">Suku</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="suku" type="text" name="headerCheckup.suku" required="false"
                                                     readonly="false" cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2"></label>
                                    <div class="col-sm-3"><h4>Data Penanggung Jawab</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.namaPenanggung">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssStyle="margin-top: 7px" id="nama_penanggung" name="headerCheckup.namaPenanggung" required="true"
                                                     disabled="false" cssClass="form-control"/>
                                    </div>
                                    <label class="control-label col-sm-2" for="headerCheckup.hubungan">Hubungan</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'Ayah':'Ayah','Ibu':'Ibu','Kakak':'Kakak','Adik':'Adik'}" cssStyle="margin-top: 7px"
                                                  id="hubungan" name="headerCheckup.hubungan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.suku">No Telp</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssStyle="margin-top: 7px" id="no_telp" name="headerCheckup.noTelp" required="true"
                                                     disabled="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2"></label>
                                    <div class="col-sm-3"><h4>Data Kunjungan</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.IdPelayanan">Poli</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'01':'Poli Anak','02':'Poli Mata','03':'Poli Ibu','04':'Poli Umum'}" cssStyle="margin-top: 7px"
                                                  id="poli" name="headerCheckup.IdPelayanan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>
                                    <label class="control-label col-sm-2" for="headerCheckup.jenisKunjungan">Kunjungan</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssStyle="margin-top: 7px" id="kunjungan" name="headerCheckup.jenisKunjungan" required="true"
                                                     disabled="false" cssClass="form-control"/>
                                        </table>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.idDokter">Dokter</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'01':'Dr. Sudirman','02':'Dr. Istoyo','03':'Dr. Tarmajo','04':'Dr. Sutino'}" cssStyle="margin-top: 7px"
                                                  id="dokter" name="headerCheckup.idDokter"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>
                                    <label class="control-label col-sm-2" for="headerCheckup.perujuk">Perujuk/Asal</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssStyle="margin-top: 7px" id="perujuk" name="headerCheckup.perujuk" required="true"
                                                     disabled="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="headerCheckup.idJenisPeriksaPasien">Penjamin</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'1':'BPJS','2':'BANK BRI','3':'BANK BNI','4':'OVO'}" cssStyle="margin-top: 7px"
                                                  id="penjamin" name="headerCheckup.idJenisPeriksaPasien"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-2"></label>
                                    <div class="col-sm-3" style="margin-top: 7px">
                                        <sj:submit targets="crud" type="button" cssClass="btn btn-success" formIds="addCheckupForm" id="save" name="save"
                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                            <i class="fa fa-check"></i>
                                            Save
                                        </sj:submit>
                                        <button type="button" class="btn btn-danger" onclick="resetField()">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                        <a type="button" class="btn btn-warning" href="initForm_checkup.action">
                                            <i class="fa fa-arrow-left"></i> Back
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">
                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Saving ...">
                                            Please don't close this window, server is processing your request ...
                                            <br>
                                            <center>
                                                <img border="0" style="width: 150px; height: 150px" src="<s:url value="/pages/images/spinner.gif"/>" name="image_indicator_write">
                                            </center>
                                        </sj:dialog>

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         resetField();
                                                                                     }
                                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>

                                        <sj:dialog id="confirm_dialog" modal="true" resizable="false" closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Confirmation Dialog">
                                            <center><img border="0" style="height: 40px; width: 40px" src="<s:url value="/pages/images/icon_warning.ico"/>" name="icon_success">
                                                Do you want to save this record?
                                                </center>
                                            <br>
                                            <s:hidden id="con"></s:hidden>
                                            <center>
                                                <button class="btn btn-success" onclick="$('#con').val(true)">Yes</button>&nbsp&nbsp&nbsp&nbsp
                                                <button class="btn btn-danger">No</button></center>
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
                                            <div class="alert bg-danger">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Silahkan Periksa Inputan Berikut :
                                                    <br/>
                                                        <div id="errorValidationMessage"></div>
                                                </label>
                                            </div>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
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

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>