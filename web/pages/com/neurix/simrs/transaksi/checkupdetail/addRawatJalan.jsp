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

<body class="hold-transition skin-blue fixed sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Rawat Jalan
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
                        <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>No Checkup</label>
                                    <s:textfield id="no_bpjs"
                                                 name="headerDetailCheckup.noCheckup" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>
                                <!-- /.form-group -->
                                <div class="form-group">
                                    <label style="margin-top: 7px">No Detail Checkup</label>
                                    <s:textfield id="no_detail_checkup"
                                                 name="headerDetailCheckup.idDetailCheckup" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label style="margin-top: 7px">Nama</label>
                                    <s:textfield id="nama"
                                                 name="headerDetailCheckup.nama" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label style="margin-top: 7px">Jenis Kelamin</label>
                                    <s:textfield id="jenis_kelamin"
                                                 name="headerDetailCheckup.jenisKelamin" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Tempat Lahir</label>
                                    <s:textfield id="tempat_lahir"
                                                 name="headerDetailCheckup.tempatLahir" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>
                                <!-- /.form-group -->
                            </div>
                            <!-- /.col -->
                            <div class="col-md-4">

                                <div class="form-group">
                                    <label>Tanggal Lahir</label>
                                    <s:textfield id="tgl_lahir"
                                                 name="headerDetailCheckup.tglLahir" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Jenis Pasien</label>
                                    <s:textfield id="jenis_pasien"
                                                 name="headerDetailCheckup.idJenisPeriksaPasien" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Poli</label>
                                    <s:textfield id="poli"
                                                 name="headerDetailCheckup.namaPelayanan" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label style="margin-top: 7px">Alamat</label>
                                    <s:textarea id="jalan" rows="4"
                                                name="headerDetailCheckup.jalan" required="false"
                                                readonly="true" cssClass="form-control"/>
                                </div>

                            </div>

                            <div class="col-md-4">

                                <!-- /.form-group -->
                                <div class="form-group">
                                    <label>Provinsi</label>
                                    <s:textfield id="provinsi" name="headerDetailCheckup.provinsi" required="true" readonly="true"
                                                 cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Kota</label>
                                    <s:textfield id="kota" name="headerDetailCheckup.kota" required="true" readonly="true"
                                                 cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Kecamatan</label>
                                    <s:textfield id="kecamatan" name="headerDetailCheckup.kecamatan" required="true" readonly="true"
                                                 cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Keluahan/Desa</label>
                                    <s:textfield id="desa" name="headerDetailCheckup.desa" required="true" readonly="true"
                                                 cssClass="form-control"/>
                                </div>
                                <!-- /.form-group -->
                            </div>
                            <!-- /.col -->
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user-md"></i> Dokter</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(1)"><i class="fa fa-plus"></i> Tambah Dokter</button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>ID Dokter</td>
                                <td>Nama</td>
                                <td>Poli</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfUsers">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td>
                                        <s:url var="detail" namespace="/checkup" action="view_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <sj:a onClickTopics="showDialogUser" href="%{detail}">
                                            <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" style="cursor: pointer">
                                        </sj:a>

                                        <s:url var="edit" namespace="/checkup" action="edit_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <s:a href="%{edit}">
                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">
                                        </s:a>

                                        <s:url var="delete" namespace="/checkup" action="delete_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <sj:a href="%{delete}">
                                            <img border="0" src="<s:url value="/pages/images/if_delete.ico"/>" style="cursor: pointer; height: 25px; width: 25px">
                                        </sj:a>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border">
                    </div>

                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Tindakan</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(2)"><i class="fa fa-plus"></i> Tambah Tindakan</button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tindakan</td>
                                <td>Tanggal</td>
                                <td>Dokter</td>
                                <td>Perawat</td>
                                <td>Tarif</td>
                                <td>Qty</td>
                                <td>Total</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfUsers">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td>
                                        <s:url var="detail" namespace="/checkup" action="view_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <sj:a onClickTopics="showDialogUser" href="%{detail}">
                                            <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" style="cursor: pointer">
                                        </sj:a>

                                        <s:url var="edit" namespace="/checkup" action="edit_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <s:a href="%{edit}">
                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">
                                        </s:a>

                                        <s:url var="delete" namespace="/checkup" action="delete_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <sj:a href="%{delete}">
                                            <img border="0" src="<s:url value="/pages/images/if_delete.ico"/>" style="cursor: pointer; height: 25px; width: 25px">
                                        </sj:a>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Diagnosa</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(3)"><i class="fa fa-plus"></i> Tambah Diagnosa</button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>ID Diagnosa</td>
                                <td>Deskripsi</td>
                                <td>Keterangan</td>
                                <td>Tanggal</td>
                                <td>Status</td>
                                <td>Poli</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfUsers">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td>
                                        <s:url var="detail" namespace="/checkup" action="view_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <sj:a onClickTopics="showDialogUser" href="%{detail}">
                                            <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" style="cursor: pointer">
                                        </sj:a>

                                        <s:url var="edit" namespace="/checkup" action="edit_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <s:a href="%{edit}">
                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">
                                        </s:a>

                                        <s:url var="delete" namespace="/checkup" action="delete_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <sj:a href="%{delete}">
                                            <img border="0" src="<s:url value="/pages/images/if_delete.ico"/>" style="cursor: pointer; height: 25px; width: 25px">
                                        </sj:a>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Order Lab</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(4)"><i class="fa fa-plus"></i> Tambah Lab</button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Order</td>
                                <td>Pemeriksaan</td>
                                <td>Status</td>
                                <td>Jenis Lab</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfUsers">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td><s:property value="noCheckup"/></td>
                                    <td>
                                        <s:url var="detail" namespace="/checkup" action="view_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <sj:a onClickTopics="showDialogUser" href="%{detail}">
                                            <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" style="cursor: pointer">
                                        </sj:a>

                                        <s:url var="edit" namespace="/checkup" action="edit_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <s:a href="%{edit}">
                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">
                                        </s:a>

                                        <s:url var="delete" namespace="/checkup" action="delete_checkup" escapeAmp="false">
                                            <s:param name="id"><s:property value="noCheckup"/></s:param>
                                        </s:url>
                                        <sj:a href="%{delete}">
                                            <img border="0" src="<s:url value="/pages/images/if_delete.ico"/>" style="cursor: pointer; height: 25px; width: 25px">
                                        </sj:a>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-navicon"></i> Keterangan</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Waktu Selesai Periksa</label>
                                <s:textfield id=""
                                             name="headerDetailCheckup.noCheckup" required="false"
                                             cssClass="form-control"/>
                            </div>
                            <!-- /.form-group -->
                            <div class="form-group">
                                <label style="margin-top: 7px">Keterangan Selesai Periksa</label>
                                <s:textfield id="e" name="headerDetailCheckup.idDetailCheckup" required="false"
                                             cssClass="form-control"/>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-success" style="margin-top: 10px"><i class="fa fa-arrow-right"></i> Save</button>
                            </div>
                        </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-dokter">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Tambah Dokter</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Poli</label>
                        <div class="col-md-7">
                            <select class="form-control">
                                <option value="">[select one]</option>
                                <option value="1">Dr. Sutikno</option>
                                <option value="2">Dr. Julio</option>
                                <option value="3">Dr. Turnomo</option>
                            </select>
                        </div>
                    </div>
                <div class="form-group">
                    <label class="col-md-3">Nama Dokter</label>
                    <div class="col-md-7">
                        <select class="form-control" style="margin-top: 7px">
                            <option value="">[select one]</option>
                            <option value="1">Dr. Sutikno</option>
                            <option value="2">Dr. Julio</option>
                            <option value="3">Dr. Turnomo</option>
                        </select>
                    </div>
                </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button type="button" class="btn btn-success"><i class="fa fa-arrow-right"></i> Save</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-tindakan">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Tambah Tindakan</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Tindakan</label>
                        <div class="col-md-7">
                            <select class="form-control">
                                <option value="">[select one]</option>
                                <option value="1">Suntik</option>
                                <option value="2">Pil</option>
                                <option value="3">Obat</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Dokter</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px">
                                <option value="">[select one]</option>
                                <option value="1">Dr. Sutikno</option>
                                <option value="2">Dr. Julio</option>
                                <option value="3">Dr. Turnomo</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Perawat</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px">
                                <option value="">[select one]</option>
                                <option value="1">Angel</option>
                                <option value="2">Anya</option>
                                <option value="3">Ayu</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                           <input type="number" class="form-control" style="margin-top: 7px">
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button type="button" class="btn btn-success" onclick="saveTindakan()"><i class="fa fa-arrow-right"></i> Save</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-diagnosa">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-stethoscope"></i> Tambah Diagnosa</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Poli</label>
                        <div class="col-md-7">
                            <select class="form-control">
                                <option value="">[select one]</option>
                                <option value="1">Dr. Sutikno</option>
                                <option value="2">Dr. Julio</option>
                                <option value="3">Dr. Turnomo</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Nama Dokter</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px">
                                <option value="">[select one]</option>
                                <option value="1">Dr. Sutikno</option>
                                <option value="2">Dr. Julio</option>
                                <option value="3">Dr. Turnomo</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button type="button" class="btn btn-success"><i class="fa fa-arrow-right"></i> Save</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-lab">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Order Lab</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Poli</label>
                        <div class="col-md-7">
                            <select class="form-control">
                                <option value="">[select one]</option>
                                <option value="1">Dr. Sutikno</option>
                                <option value="2">Dr. Julio</option>
                                <option value="3">Dr. Turnomo</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Nama Dokter</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px">
                                <option value="">[select one]</option>
                                <option value="1">Dr. Sutikno</option>
                                <option value="2">Dr. Julio</option>
                                <option value="3">Dr. Turnomo</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button type="button" class="btn btn-success"><i class="fa fa-arrow-right"></i> Save</button>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    function showModal(select){

        if (select == 1){
            $('#modal-dokter').modal('show');
        }else if(select == 2){
            $('#modal-tindakan').modal('show');
        }else if(select == 3){
            $('#modal-diagnosa').modal('show');
        }else if(select == 4){
            $('#modal-lab').modal('show');
        }
    }


    function saveTindakan(){

    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>