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
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PasienAction.js"/>'></script>
    <script type='text/javascript'>

        function confirm() {

            var noBpjs          = $('#no_bpjs').val();
            var idPasien        = $('#id_pasien').val();
            var noKtp           = $('#no_ktp').val();
            var namaPasien      = $('#nama_pasien').val();
            var jenisKelamin    = $('#jenis_kelamin').val();
            var tempatLahir     = $('#tempat_lahir').val();
            var tglLahir        = $('#tanggal_lahir').val();
            var jalan           = $('#jalan').val();
            var suku            = $('#suku').val();
            var profesi         = $('#profesi').val();
            var agama           = $('#agama').val();
            var poli            = $('#poli').val();
            var dokter          = $('#dokter').val();
            var penjamin        = $('#penjamin').val();
            var provinsi        = $('#provinsi11').val();
            var kota            = $('#kabupaten11').val();
            var kecamatan       = $('#kecamatan11').val();
            var desa            = $('#desa11').val();
            var imgInp          = $('#imgInp').val();

            if (idPasien != '' && noKtp != '' && namaPasien != ''
                    && jenisKelamin != '' && tempatLahir != '' && tglLahir != ''
                    && profesi != '' && agama != ''
                    && poli != '' && dokter != '' && penjamin != ''
                    && provinsi != '' && kota != '' && kecamatan != '' && desa != '' && imgInp != '') {

                $('#confirm_dialog').dialog('open');

            } else {

                $("html, body").animate({ scrollTop: 0 }, 600);
                $('#warning_pasien').show().fadeOut(10000);

//                if (noBpjs == '') {
//                    $('#no_bpjs').css('border','red solid 1px');
//                }
                if (idPasien == '') {
                    $('#id_pasien').css('border','red solid 1px');
                }
                if (noKtp == '') {
                    $('#no_ktp').css('border','red solid 1px');
                }
                if (namaPasien == '') {
                    $('#nama_pasien').css('border','red solid 1px');
                }
                if (jenisKelamin == '') {
                    $('#jenis_kelamin').css('border','red solid 1px');
                }
                if (tempatLahir == '') {
                    $('#tempat_lahir').css('border','red solid 1px');
                }
                if (tglLahir == '') {
                    $('#st_tgl_lahir').css('border','red solid 1px');
                }
//                if (jalan == '') {
//                    $('#jalan').css('border','red solid 1px');
//                }
//                if (suku == '') {
//                    $('#suku').css('border','red solid 1px');
//                }
//                if (profesi == '') {
//                    $('#profesi').css('border','red solid 1px');
//                }
                if (agama == '') {
                    $('#agama').css('border','red solid 1px');
                }
                if (poli == '') {
                    $('#poli').css('border','red solid 1px');
                }
                if (dokter == '' || dokter == null) {
                    $('#dokter').css('border','red solid 1px');
                }
                if (penjamin == '') {
                    $('#penjamin').css('border','red solid 1px');
                }
                if (provinsi == '') {
                    $('#provinsi').css('border','red solid 1px');
                }
                if (kota == '') {
                    $('#kabupaten').css('border','red solid 1px');
                }
                if (kecamatan == '') {
                    $('#kecamatan').css('border','red solid 1px');
                }
                if (desa == '') {
                    $('#desa').css('border','red solid 1px');
                }
                if(imgInp == ''){
                    $('#img_file').css('border','red solid 1px');
                }
            }


        }
        $.subscribe('beforeProcessSave', function (event, data) {
            event.originalEvent.options.submit = true;
            $('#confirm_dialog').dialog('close');
            $.publish('showDialog');
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
                $('#no_bpjs, #id_pasien, #no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #st_tgl_lahir, #jalan, #suku, #profesi, #agama, #poli, #dokter, #penjamin, #img_file, #provinsi, #kabupaten, #kecamatan, #desa').css('border','');
                resetField();

            }
        });

        $.subscribe('errorDialog', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function resetField() {
            $('#no_bpjs, #id_pasien, #no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #tanggal_lahir, #jalan, #suku, #profesi, #agama, #poli, #dokter, #penjamin, #provinsi11, #kabupaten11, #kecamatan11, #desa11, #provinsi, #kabupaten, #kecamatan, #desa, #nama_penanggung, #no_telp, #hubungan, #kunjungan, #perujuk').val('');
            var img = '<s:url value="/pages/images/ktp-default.jpg"/>';
            $('#img-upload').attr('src',img);
            $('#imgInp').attr('value','');

        }


    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Tambah Rawat Pasien
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
                        <h3 class="box-title"><i class="fa fa-user-plus"></i> Inputan Data Pasien</h3>
                    </div>
                    <s:form id="addCheckupForm" enctype="multipart/form-data" method="post" namespace="/checkup"
                            action="saveAdd_checkup.action" theme="simple">
                        <div class="box-body">
                            <div class="alert alert-danger alert-dismissible" id="warning_pasien" style="display: none">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                Silahkan cek kembali inputan data pasien!
                            </div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                                <%--<button class="btn btn-success pull-right"><i class="fa fa-plus"></i> Pasien Baru</button>--%>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4">No BPJS</label>
                                            <div class="col-md-8">
                                                <div class="input-group date">
                                                    <s:textfield id="no_bpjs" name="headerCheckup.noBpjs" cssClass="form-control"/>
                                                    <div class="input-group-addon btn btn-success" id="btnSearchBpjsPerson">
                                                        <i class="fa fa-search" style="cursor: pointer"></i> Search
                                                    </div>
                                                </div>
                                                <%--<s:textfield id="no_bpjs" name="headerCheckup.noBpjs"--%>
                                                             <%--cssClass="form-control" onkeypress="$(this).css('border','')"/>--%>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">ID Pasien</label>
                                            <div class="col-md-8">
                                                <s:textfield id="id_pasien" name="headerCheckup.idPasien" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />

                                                    <%--<s:action id="initSelectPasien" namespace="/pasien"--%>
                                                              <%--name="getListComboSelectPasien_pasien"/>--%>
                                                    <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                              <%--list="#initSelectPasien.listOfpasien" id="id_pasien"--%>
                                                              <%--name="headerCheckup.idPasien" listKey="idPasien+,+nama"--%>
                                                              <%--listValue="idPasien" onchange="$(this).css('border','')"--%>
                                                              <%--headerKey="" headerValue="[Select one]"--%>
                                                              <%--cssClass="form-control select2"/>--%>

                                            </div>

                                            <script type="application/javascript">
                                                var functions, mapped;
                                                $('#id_pasien').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};

                                                        var data = [];
                                                        dwr.engine.setAsync(false);

                                                        PasienAction.getListComboPasien(query, function (listdata) {
                                                            data = listdata;
                                                        });

                                                        $.each(data, function (i, item) {
                                                            var labelItem = item.noKtp+" "+item.noBpjs+" "+item.nama;
                                                            mapped[labelItem] = {
                                                                id: item.idPasien,
                                                                nama:item.nama,
                                                                ktp: item.noKtp,
                                                                bpjs:item.noBpjs,
                                                                tempatlahir:item.tempatLahir,
                                                                tgllahir:item.tglLahir,
                                                                alamat:item.jalan,
                                                                suku:item.suku,
                                                                profesi:item.profesi,
                                                                notelp:item.noTelp,
                                                                urlktp:item.urlKtp,
                                                                sex:item.jenisKelamin,
                                                                agama:item.agama
                                                            };
                                                            functions.push(labelItem);
                                                        });
                                                        process(functions);

                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];
                                                        $('#no_ktp').val(selectedObj.ktp);
                                                        $('#nama_pasien').val(selectedObj.nama);
                                                        $('#jenis_kelamin').val(selectedObj.sex);
                                                        $('#tempat_lahir').val(selectedObj.tempatlahir);
                                                        $('#tanggal_lahir').val(selectedObj.tgllahir);
                                                        $('#agama').val(selectedObj.agama);
                                                        $('#profesi').val(selectedObj.profesi);
                                                        $('#jalan').val(selectedObj.alamat);
                                                        $('#suku').val(selectedObj.urlktp);
                                                        $('#url').val(selectedObj.suku);
                                                        return selectedObj.id;
                                                    }
                                                });
                                            </script>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">NIK Pasien</label>
                                            <div class="col-md-8">
                                                <s:textfield id="no_ktp" name="headerCheckup.noKtp" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"
                                                             data-inputmask="'mask': ['9999999999999999']"
                                                             data-mask=""/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Nama Pasien</label>
                                            <div class="col-md-8">
                                                <s:textfield id="nama_pasien" name="headerCheckup.nama" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                                            <div class="col-md-8">
                                                <s:select list="#{'L':'Laki-Laki','P':'Perempuan'}"
                                                          cssStyle="margin-top: 7px" onchange="$(this).css('border','')"
                                                          id="jenis_kelamin" name="headerCheckup.jenisKelamin"
                                                          headerKey="" headerValue="[Select one]"
                                                          cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Tempat Lahir</label>
                                            <div class="col-md-8">
                                                <s:textfield id="tempat_lahir" name="headerCheckup.tempatLahir" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Tanggal Lahir</label>
                                            <div class="col-md-8">
                                                <div class="input-group date" style="margin-top: 7px" id="st_tgl_lahir">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <s:textfield id="tanggal_lahir" name="headerCheckup.stTglLahir"
                                                                 cssClass="form-control datemask" onchange="$('#st_tgl_lahir').css('border','')"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Agama</label>
                                            <div class="col-md-8">
                                               <s:select id="agama" name="headerCheckup.agama" list="#{'Islam':'Islam','Kristen':'Kristen','Katolik':'Katolik','Hindu':'Hindu','Buddha':'Buddha','Konghucu':'Konghucu'}"
                                                         onchange="$(this).css('border','')"
                                                         headerKey="" headerValue="[Select One]" cssStyle="margin-top: 7px" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Profesi</label>
                                            <div class="col-md-8">
                                                <s:textfield id="profesi" name="headerCheckup.profesi" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Suku</label>
                                            <div class="col-md-8">
                                                <s:textfield id="suku" name="headerCheckup.suku" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                                            <div class="col-md-8">
                                                <s:textarea id="jalan" rows="3" cssStyle="margin-top: 7px" onkeypress="$(this).css('border','')"
                                                            name="headerCheckup.jalan" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4">Provinsi</label>
                                            <div class="col-md-8">
                                                <s:textfield cssStyle="margin-top: 7px" id="provinsi" name=""
                                                             required="true" disabled="false" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control"/>
                                                <s:textfield cssStyle="display: none; margin-top: 7px" id="provinsi11"
                                                             name="headerCheckup.provinsiId" required="true"
                                                             disabled="false" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Kota</label>
                                            <div class="col-md-8">
                                                <s:textfield cssStyle="margin-top: 7px" id="kabupaten" name=""
                                                             required="true" disabled="false" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control"/>
                                                <s:textfield cssStyle="display: none; margin-top: 7px" id="kabupaten11"
                                                             name="headerCheckup.kotaId" required="true"
                                                             disabled="false" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Kecamatan</label>
                                            <div class="col-md-8">
                                                <s:textfield cssStyle="margin-top: 7px" id="kecamatan" name=""
                                                             required="true" disabled="false" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control"/>
                                                <s:textfield cssStyle="display: none; margin-top: 7px" id="kecamatan11"
                                                             name="headerCheckup.kecamatanId" required="true"
                                                             disabled="false" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Kelurahan/Desa</label>
                                            <div class="col-md-8">
                                                <s:textfield cssStyle="margin-top: 7px" id="desa" name=""
                                                             required="true" disabled="false" onkeypress="$(this).css('border','')"
                                                             cssClass="form-control"/>
                                                <s:textfield cssStyle="display: none; margin-top: 7px" id="desa11"
                                                             name="headerCheckup.desaId" required="true"
                                                             disabled="false" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Foto KTP</label>
                                            <div class="col-md-8">
                                                <div class="input-group" style="margin-top: 7px" id="img_file">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn-file">
                                                            <%--<input type="file" id="imgInp" accept=".jpg" name="fileUploadKtpPasien" onchange="$('#img_file').css('border','')">--%>
                                                            Browse… <s:file id="imgInp" accept=".jpg" name="fileUpload" onchange="$('#img_file').css('border','')"></s:file>
                                                        </span>
                                                    </span>
                                                    <input type="text" class="form-control" readonly>
                                                </div>
                                                <img id="img-upload" width="100%" src="<s:url value="/pages/images/ktp-default.jpg"/>"
                                                     style="border: darkgray solid 1px; height: 170px"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Penanggung Jawab</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4">Nama Penanggung</label>
                                            <div class="col-md-8">
                                                <s:textfield id="nama_penanggung" name="headerCheckup.namaPenanggung"
                                                             cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">No Telp.</label>
                                            <div class="col-md-8">
                                                <s:textfield id="no_telp" name="headerCheckup.noTelp"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"
                                                             data-inputmask="'mask': ['+62 999-9999-9999']"
                                                             data-mask=""/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Hubungan</label>
                                            <div class="col-md-8">
                                                <s:select
                                                        list="#{'Ayah':'Ayah','Ibu':'Ibu','Kakak':'Kakak','Adik':'Adik','Sepupu':'Sepupu','Ipar':'Ipar'}"
                                                        cssStyle="margin-top: 7px"
                                                        id="hubungan" name="headerCheckup.hubunganKeluarga"
                                                        headerKey="" headerValue="[Select one]"
                                                        cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Kunjungan</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4">Poli</label>
                                            <div class="col-md-8">
                                                <s:action id="initComboPoli" namespace="/checkup"
                                                          name="getComboPelayanan_checkup"/>
                                                <s:select cssStyle="margin-top: 7px; width: 100%"
                                                          list="#initComboPoli.listOfPelayanan" id="poli"
                                                          name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                          listValue="namaPelayanan" onchange="$(this).css('border',''); listDokter(this)"
                                                          headerKey="" headerValue="[Select one]"
                                                          cssClass="form-control select2"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Dokter</label>
                                            <div class="col-md-8">
                                                    <select id="dokter" class="form-control select2" name="headerCheckup.idDokter" style="margin-top: 7px; width: 100%" onchange="$(this).css('border','')">
                                                        <option value=''>[Select One]</option>
                                                    </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Penjamin</label>
                                            <div class="col-md-8">
                                                <s:action id="initComboPenjamin" namespace="/checkup"
                                                          name="getComboJenisPeriksaPasien_checkup"/>
                                                <s:select cssStyle="margin-top: 7px"
                                                          list="#initComboPenjamin.listOfJenisPriksaPasien"
                                                          id="penjamin" name="headerCheckup.idJenisPeriksaPasien"
                                                          listKey="idJenisPeriksaPasien" listValue="keterangan"
                                                          headerKey="" headerValue="[Select one]" onchange="$(this).css('border','')"
                                                          cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4">Kunjungan</label>
                                            <div class="col-md-8">
                                                <s:select list="#{'Lama':'Lama','Baru':'Baru'}"
                                                          cssStyle="margin-top: 7px" onchange="$(this).css('border','')"
                                                          id="kunjungan" name="headerCheckup.jenisKunjungan"
                                                          headerKey="" headerValue="[Select one]"
                                                          cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Perujuk/Asal</label>
                                            <div class="col-md-8">
                                                <s:textfield id="perujuk" name="headerCheckup.rujuk"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <div class="col-sm-10 col-md-offset-4" style="margin-top: 7px">
                                                <button type="button" class="btn btn-success" onclick="confirm()"><i
                                                        class="fa fa-arrow-right"></i> Save
                                                </button>
                                                <button type="button" class="btn btn-danger" onclick="resetField()">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </button>
                                                <a type="button" class="btn btn-warning" href="initForm_checkup.action">
                                                    <i class="fa fa-arrow-left"></i> Back
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none">
                                    <sj:dialog id="confirm_dialog" modal="true" resizable="false" closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Confirmation Dialog">
                                        <center><img border="0" style="height: 40px; width: 40px"
                                                     src="<s:url value="/pages/images/icon_warning.ico"/>"
                                                     name="icon_success">
                                            Do you want to save this record?
                                        </center>
                                        <br>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-warning"
                                                    onclick="$('#confirm_dialog').dialog('close')"><i
                                                    class="fa fa-times"></i> No
                                            </button>
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                                                       formIds="addCheckupForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave"
                                                       onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                                <i class="fa fa-arrow-right"></i>
                                                yes
                                            </sj:submit>
                                        </div>
                                    </sj:dialog>

                                    <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog"
                                               modal="true"
                                               resizable="false"
                                               height="250" width="600" autoOpen="false" title="Saving ...">
                                        Please don't close this window, server is processing your request ...
                                        <br>
                                        <center>
                                            <img border="0" style="width: 150px; height: 150px"
                                                 src="<s:url value="/pages/images/spinner.gif"/>"
                                                 name="image_indicator_write">
                                        </center>
                                    </sj:dialog>

                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
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
                                        <div class="alert alert-danger alert-dismissible">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                            </label>
                                        </div>
                                    </sj:dialog>

                                </div>
                            </div>

                        </div>
                    </s:form>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script type='text/javascript'>
    $(document).ready(function () {
        $('#btnSearchBpjsPerson').click(function () {
            var nobpjs=$('#no_bpjs').val();
            if (nobpjs!=''){
                CheckupAction.completeBpjs(nobpjs, function(response){
                    if (response!=null){
                        $('#no_ktp').val(response.noKtp);
                        $('#nama_pasien').val(response.nama);
                        $('#jenis_kelamin').val(response.jenisKelamin);
                        $('#tanggal_lahir').val(response.stTglLahir);
                    }else{
                        alert("Data BPJS tidak ditemukan");
                    }

                });
            } else{
                alert("Nomor BPJS masih kosong");
            }
        });

        $('#pendaftaran').addClass('active');

        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        $('.btn-file :file').on('fileselect', function (event, label) {

            var input = $(this).parents('.input-group').find(':text'),
                    log = label;

            if (input.length) {
                input.val(log);
            } else {
                if (log) alert(log);
            }

        });
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#img-upload').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#imgInp").change(function () {
            readURL(this);
        });
    });

    function listDokter(idPelayanan){
        var idx = idPelayanan.selectedIndex;
        var idPoli = idPelayanan.options[idx].value;
        var option = "";
        CheckupAction.listOfDokter(idPoli, function(response){
            option = "<option value=''>[Select One]</option>";
            if (response != null){
                $.each(response, function (i, item) {
                    option += "<option value='"+item.idDokter+"'>" +item.namaDokter+ "</option>";
                });
            }else{
               option = option;
            }
        });
        $('#dokter').html(option);
    }

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