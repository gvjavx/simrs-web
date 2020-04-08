<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<!-- Content Wrapper. Contains page content -->
    <!-- Content Header (Page header) -->
    <!-- Main content -->
<html>
<body bgcolor="#8fbc8f">
<section class="content">
    <!-- Your Page Content Here -->
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="form-group">
                        <s:form id="addCheckupForm" enctype="multipart/form-data" method="post" namespace="/checkup" action="saveAdd_checkup.action" theme="simple" cssClass="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-2">No Checkup</label>
                                <div class="col-sm-4">
                                    <s:textfield id="no_bpjs" cssStyle="margin-top: 7px"
                                                 name="headerCheckup.noBpjs" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>

                                <label class="control-label col-sm-2" for="headerCheckup.jalan">Alamat</label>
                                <div class="col-sm-4">
                                    <s:textarea id="jalan" rows="3" cssStyle="margin-top: 7px"
                                                name="headerCheckup.jalan" required="false"
                                                readonly="true" cssClass="form-control"/>
                                </div>

                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="headerCheckup.idPasien">ID Pasien</label>
                                <div class="col-sm-4">
                                    <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                 name="headerCheckup.idPasien" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>
                                <label class="control-label col-sm-2" for="headerCheckup.provinsiId">Provinsi</label>
                                <div class="col-sm-4">
                                    <s:textfield cssStyle="margin-top: 7px" id="provinsi" name="" required="true" disabled="false"
                                                 cssClass="form-control" readonly="true"/>
                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="provinsi11"
                                                 name="headerCheckup.provinsiId" required="true"
                                                 readonly="true" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="headerCheckup.noKtp">No KTP</label>
                                <div class="col-sm-4">
                                    <s:textfield id="no_ktp" cssStyle="margin-top: 7px"
                                                 name="headerCheckup.noKtp" required="true" cssClass="form-control" readonly="true"/>
                                </div>
                                <label class="control-label col-sm-2" for="headerCheckup.kotaId">Kota</label>
                                <div class="col-sm-4">
                                    <s:textfield cssStyle="margin-top: 7px" id="kabupaten" name="" required="true" disabled="false"
                                                 cssClass="form-control" readonly="true"/>
                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="kabupaten11"
                                                 name="headerCheckup.kotaId" required="true"
                                                 disabled="false" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="headerCheckup.nama">Nama</label>
                                <div class="col-sm-4">
                                    <s:textfield id="nama_pasien" name="headerCheckup.nama"
                                                 required="false" readonly="true"
                                                 cssClass="form-control" cssStyle="margin-top: 7px"/>
                                </div>
                                <label class="control-label col-sm-2" for="headerCheckup.kecamatanId">Kecamatan</label>
                                <div class="col-sm-4">
                                    <s:textfield cssStyle="margin-top: 7px" id="kecamatan" name="" required="true" disabled="false"
                                                 cssClass="form-control" readonly="true"/>
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
                                              cssClass="form-control" readonly="true"/>
                                </div>
                                <label class="control-label col-sm-2" for="headerCheckup.desaId">Desa</label>
                                <div class="col-sm-4">
                                    <s:textfield cssStyle="margin-top: 7px" id="desa" name="" required="true" disabled="false"
                                                 cssClass="form-control" readonly="true"/>
                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="desa11"
                                                 name="headerCheckup.desaId" required="true"
                                                 disabled="false" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="headerCheckup.tempatLahir">Tempat Lahir</label>
                                <div class="col-sm-4">
                                    <s:textfield cssStyle="margin-top: 7px" id="tempat_Lahir" name="headerCheckup.tempatLahir"
                                                 required="true" cssClass="form-control" readonly="true"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" >Tanggal Lahir</label>
                                <div class="col-sm-4">
                                    <div class="input-group date" style="margin-top: 7px">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <s:textfield id="tanggal_lahir" name="headerCheckup.tglLahir" cssClass="form-control"
                                                     required="false" readonly="true"/>
                                    </div>

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="headerCheckup.agama">Agama</label>
                                <div class="col-sm-4">
                                    <s:select list="#{'Islam':'Islam','Kristen':'Kristen'}" cssStyle="margin-top: 7px"
                                              id="agama" name="headerCheckup.agama"
                                              headerKey="" headerValue="[Select one]"
                                              cssClass="form-control" readonly="true"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="headerCheckup.suku">Suku</label>
                                <div class="col-sm-4">
                                    <s:textfield id="suku" type="text" name="headerCheckup.suku" required="false"
                                                 readonly="true" cssClass="form-control" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                        </s:form>
                    </div>
                </div>

                <div class="box-header">
                    <h3 class="box-title">Data Riwayat Checkup</h3>
                </div>

                <div class="box-body">
                    <table id="myTable" class="table table-bordered table-striped">
                        <thead >
                        <tr bgcolor="#90ee90">
                            <td>Poli</td>
                            <td>Status</td>
                            <td>Keterangan</td>
                            <td>Ruang</td>
                            <td>No</td>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="#session.listOfRiwayat" status="listOfRiwayat">
                            <tr>
                                <td><s:property value="idPelayanan"/></td>
                                <td><s:property value="statusPeriksa"/></td>
                                <td><s:property value="keterangan"/></td>
                                <td><s:property value="namaRuangan"/></td>
                                <td><s:property value="noRuangan"/></td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                    <br>
                    <div class="form-group">
                            <button style="margin-top: 7px" type="button" class="btn btn-warning" onclick="$('#view_dialog_user').dialog('close')">
                                <i class="fa fa-times"></i> Close
                            </button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
<!-- /.content -->
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
</body>
</html>