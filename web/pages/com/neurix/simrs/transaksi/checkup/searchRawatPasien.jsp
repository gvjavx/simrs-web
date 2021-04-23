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
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PasienAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#pendaftaran_active, #bayar_rawat_jalan').addClass('active');
            $('#pendaftaran_open').addClass('menu-open');
        });

    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Pendaftaran Rawat Jalan Pasien
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Rawat Pasien</h3>
                        <%--<div class="row">--%>
                            <%--<div class="col-md-4">--%>
                            <%--</div>--%>
                            <%--<div class="col-md-3 pull-right">--%>
                                <%--<div class="input-group date">--%>
                                    <%--<input class="form-control" id="id_antrian" placeholder="Antrian Online"--%>
                                           <%--onchange="saveAntrian()">--%>
                                    <%--<div class="input-group-btn">--%>
                                        <%--<button class="btn btn-success"onclick="saveAntrian()" id="save_resep"><i class="fa fa-arrow-right"></i> Save</button>--%>
                                        <%--<button class="btn btn-success" id="load_resep" style="cursor: no-drop; display: none"><i class="fa fa-spinner fa-spin"></i> Sedang mencari...</button>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="checkupForm" method="post" namespace="/checkup" action="search_checkup.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="headerCheckup.idPasien">No RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="headerCheckup.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="headerCheckup.noKtp">No KTP</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_ktp" cssStyle="margin-top: 7px"
                                                     name="headerCheckup.noKtp" required="true"
                                                     cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" for="headerCheckup.nama">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="headerCheckup.nama"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Poli</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initComboPoli.listOfPelayanan" id="poli"
                                                  name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                  listValue="namaPelayanan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'0':'Antrian','1':'Periksa','2':'Rujuk','3':'Selesai'}"
                                                  cssStyle="margin-top: 7px; width: 100%"
                                                  id="status" name="headerCheckup.statusPeriksa"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="headerCheckup.stTglFrom"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="headerCheckup.getStTglTo"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="checkupForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a href="add_checkup.action" type="button" class="btn btn-primary"><i class="fa fa-plus"></i> Pendaftaran</a>
                                        <a type="button" class="btn btn-danger" href="initForm_checkup.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">

                                        <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Searching ...">
                                            Please don't close this window, server is processing your request ...
                                            <br>
                                            <center>
                                                <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                     src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                     name="image_indicator_write">
                                                <br>
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>
                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                   resizable="false" closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         $('#id_antrian').val('');
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>
                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close'); }
                                                                            }"
                                        >
                                            <div class="alert alert-danger alert-dismissible">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                         name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Rawat Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped" style="font-size: 12px">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>No Checkup</td>
                                <td>NO RM</td>
                                <td>Nama</td>
                                <td>Umur</td>
                                <td>Penanggung Jawab</td>
                                <td>Tanggal Masuk</td>
                                <td>Poli Terakhir</td>
                                <td>Status Terakhir</td>
                                <td>Desa</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td><s:property value="umur"/></td>
                                    <td><s:property value="namaPenanggung"/></td>
                                    <td><s:property value="formatTglMasuk"/></td>
                                    <td><s:property value="namaPelayanan"/></td>
                                    <td><s:property value="statusPeriksaName"/></td>
                                    <td><s:property value="namaDesa"/></td>
                                    <td align="center" width="12%">
                                        <s:if test='#row.statusPeriksa == "0"'>
                                            <a href="add_checkup.action?id=<s:property value="idDetailCheckup"/>">
                                                <img style="cursor: pointer" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-orange-25.png"/>">
                                            </a>
                                        </s:if>
                                        <img border="0" class="hvr-grow" id="v_<s:property value="noCheckup"/>"
                                             src="<s:url value="/pages/images/icons8-search-25.png"/>"
                                             style="cursor: pointer;" onclick="detail_pasien('<s:property value="noCheckup"/>','<s:property value="idDetailCheckup"/>')">
                                        <s:if test='#row.isKronis == "Y"'>
                                            <a target="_blank" href="printResepKronis_checkup.action?id=<s:property value="idDetailCheckup"/>">
                                                <img src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                            </a>
                                        </s:if>
                                        <a target="_blank" href="printNoAntrian_checkup.action?id=<s:property value="idPasien"/>&tipe=<s:property value="idPelayanan"/>">
                                            <img src="<s:url value="/pages/images/icons8-print-25-yellow.png"/>">
                                        </a>
                                        <s:if test='#row.statusPeriksa == "0"'>
                                            <img onclick="cancelPeriksa('<s:property value="idDetailCheckup"/>')" style="cursor: pointer" class="hvr-grow" src="<s:url value="/pages/images/cancel-flat-new.png"/>">
                                        </s:if>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-detail-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-search"></i> Detail Rawat Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>No RM</b></td>
                                    <td><span id="det_no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No Checkup</b></td>
                                    <td><span id="det_no_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="det_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="det_nama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="det_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, Tgl Lahir</b></td>
                                    <td><span id="det_tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Agama</b></td>
                                    <td><span id="det_agama"></span></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Suku</b></td>
                                    <td><span id="det_suku"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="det_alamat"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Provinsi</b></td>
                                    <td><span id="det_provinsi"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kabupaten</b></td>
                                    <td><span id="det_kabupaten"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kecamatan</b></td>
                                    <td><span id="det_kecamatan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Desa</b></td>
                                    <td><span id="det_desa"></span></td>
                                </tr>
                                <tr id="surat_rujukan">
                                    <td></td>
                                    <td><button class="btn btn-primary" id="show_rujukan"><i class="fa fa-search"></i> Surat Rujukan</button></td>
                                </tr>
                            </table>
                        </div>

                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Riwayat Checkup</h3>
                </div>

                <div class="box-body">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td>Poli</td>
                            <td>Status</td>
                            <td>Keterangan</td>
                            <td>Ruang</td>
                            <td>No</td>
                        </tr>
                        </thead>
                        <tbody id="det_riwayat">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-add-finger">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-search"></i> Tambah Pasien dengan Finger</h4>
            </div>
            <div class="modal-body">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <s:form id="formFinger" theme="simple" cssClass="form-horizontal">
                    <div class="box-body">
                        <div class="row">
                            <div class="form-group">
                                <label class="control-label col-sm-4" style="margin-top: 5px" for="fin_id">No. BPJS</label>
                                <div class="col-sm-6">
                                    <s:hidden id="fin_id_pas" name="fin_id_pas" cssClass="form-control"/>
                                    <s:textfield id="fin_id" name="fin_id" cssClass="form-control"/>
                                </div>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    $('#fin_id').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};

                                            var data = [];
                                            dwr.engine.setAsync(false);
                                            PasienAction.listPasienWithId(query,function (listdata) {
                                                data = listdata;
                                            });

                                            $.each(data, function (i, item) {
                                                var labelItem = item.noBpjs+" "+item.nama;
                                                mapped[labelItem] = {id: item.noBpjs,nama:item.nama, label: labelItem,idPasien:item.idPasien};
                                                functions.push(labelItem);
                                            });
                                            process(functions);
                                        },
                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            $('#fin_id_pas').val(selectedObj.idPasien);
                                            $('#fin_nm_pas').val(selectedObj.nama);
                                            return selectedObj.id;
                                        }
                                    });
                                </script>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4" style="margin-top: 5px" for="fin_id">Nama Pasien</label>
                                <div class="col-sm-6">
                                    <s:textfield id="fin_nm_pas" name="fin_nm_pas" cssClass="form-control" readonly="true"/>
                                </div>
                            </div>
                        </div>
                        <br>
                        <br>
                        <button type="button" id="btnSearchFingerPrint" class="btn btn-success pull-right"><i class="fa fa-arrow-right"></i> Scan Finger</button>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-antrian">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Detail Data Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>No Checkup Online</b></td>
                                    <td><span id="an_no_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="an_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>ID Pasien</b></td>
                                    <td><span id="an_id_pasien"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="an_nama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="an_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, Tgl Lahir</b></td>
                                    <td><span id="an_tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Agama</b></td>
                                    <td><span id="an_agama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Suku</b></td>
                                    <td><span id="an_suku"></span></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="an_alamat"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Desa</b></td>
                                    <td><span id="an_desa"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kecamatan</b></td>
                                    <td><span id="an_kecamatan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kabupaten</b></td>
                                    <td><span id="an_kabupaten"></span></td>
                                </tr>

                                <tr>
                                    <td><b>Provinsi</b></td>
                                    <td><span id="an_provinsi"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tanggal Daftar</b></td>
                                    <td><span id="an_tgl_daftar"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tujuan Poli</b></td>
                                    <td><span id="an_poli"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Dokter</b></td>
                                    <td><span id="an_dokter"></span></td>
                                </tr>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success" id="save_aktif"><i class="fa fa-arrow-right"></i> Aktivasi</button>
                <button class="btn btn-success" style="cursor: no-drop; display: none" id="load_aktif"><i class="fa fa-spinner fa-spin"></i> Sedang menyimpan...</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-validasi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-warning"></i> Warning</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible">
                        <p id="msg_app"></p>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-rujukan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-file-archive-o"></i> Surat Rujukan</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <img id="img_surat_rujuk" style="height: 500px; width: 100%">
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-detail">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Data Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_cancel">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <p id="msg_cancel"></p>
                            </div>
                            <table class="table table-striped" style="font-size: 13px">
                                <tr>
                                    <td width="30%">No Checkup</td>
                                    <td><span id="det_no_checkup_cancel"></span></td>
                                </tr>
                                <tr>
                                    <td>ID Detail Checkup</td>
                                    <td><span id="det_id_detail_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td>NO RM</td>
                                    <td><span id="det_no_rm_cancel"></span></td>
                                </tr>
                                <tr>
                                    <td>Nama Pasien</td>
                                    <td><span id="det_nama_pasien"></span></td>
                                </tr>
                                <tr>
                                    <td>Pelayanan</td>
                                    <td><span id="det_pelayanan"></span></td>
                                </tr>
                                <tr>
                                    <td>Jenis Pasien</td>
                                    <td><span id="det_jenis_pasien"></span></td>
                                </tr>
                                <tr>
                                    <td>Alamat</td>
                                    <td><span id="det_alamat_batal"></span></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-12">
                            <textarea id="set_alasan" class="form-control" rows="2" placeholder="Alasan"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add" ><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_add"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-edit-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Edit Pendaftaran Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>No BPJS</label>
                                <input class="form-control" id="add_no_bpjs" oninput="$(this).css('border','')"
                                       type="number">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">NIK</label>
                                <input class="form-control" id="add_nik"
                                       oninput="cekNik('add_nik', 'war_add_nik_pasien'); $(this).css('border','')" onkeyup="cekNik('add_nik', 'war_add_nik_pasien');"
                                       data-inputmask="'mask': ['9999999999999999']" data-mask="">
                                <span style="color: red; display: none; font-size: 12px" id="war_add_nik_pasien"><i
                                        class="fa fa-times"></i> jika tidak ada nik masukkan angka (0) 6 digit</span>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Nama</label>
                                <input class="form-control" id="add_nama" oninput="$(this).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Jenis Kelamin</label>
                                <select class="form-control" id="add_jk" onchange="$(this).css('border','')">
                                    <option value="">[Select One]</option>
                                    <option value="L">Laki-Laki</option>
                                    <option value="P">Perempuan</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Tempat Lahir</label>
                                <input class="form-control" id="add_tempat_lahir"
                                       oninput="$(this).css('border',''); setKotaKab(this.id)">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Tanggal Lahir</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl_lahir_validasi datemask" id="add_tanggal_lahir"
                                           onchange="$(this).css('border','')" oninput="$(this).css('border','')">
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Status Perkawinan</label>
                                <s:select id="add_status_perkawinan" name="headerCheckup.statusPerkawinan"
                                          list="#{'Kawin':'Kawin','Belum Kawin':'Belum Kawin','Duda':'Duda','Janda':'Janda'}"
                                          onchange="$(this).css('border','')"
                                          headerKey="" headerValue="[Select One]"
                                          cssStyle="width: 100%" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Pendidikan</label>
                                <s:select id="add_pendidikan" name="headerCheckup.pendidikan"
                                          list="#{'Belum Sekolah':'Belum Sekolah','SD/Sederajat':'SD/Sederajat','SMP/Sederajat':'SMP/Sederajat','SMA/Sederajat':'SMA/Sederajat','S1':'S1','S2':'S2','S3':'S3'}"
                                          onchange="$(this).css('border','')"
                                          headerKey="" headerValue="[Select One]"
                                          cssStyle="width: 100%" cssClass="form-control"/>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Agama</label>
                                <select class="form-control" id="add_agama" onchange="$(this).css('border','')">
                                    <option value="">[Select One]</option>
                                    <option value="Islam">Islam</option>
                                    <option value="Kristen">Kristen</option>
                                    <option value="Katolik">Katolik</option>
                                    <option value="Hindu">Hindu</option>
                                    <option value="Konguchu">Konguchu</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Profesi</label>
                                <s:select id="ket_add_profesi"
                                          list="#{'Guru':'Guru','Dokter':'Dokter','Swasta':'Swasta','PNS':'PNS','Lainnya':'Lainnya'}"
                                          onchange="$('#add_profesi').val(this.value); $(this).css('border',''); if(this.value == 'Lainnya'){$('#form_add_profesi').show()}else{$('#form_add_profesi').hide()} "
                                          headerKey="" headerValue="[Select One]"
                                          cssStyle="width: 100%" cssClass="form-control"/>
                                <s:hidden id="add_profesi"></s:hidden>
                            </div>
                            <div class="form-group" style="display: none" id="form_add_profesi">
                                <s:textfield placeholder="Keterangan Profesi" cssClass="form-control"
                                             cssStyle="margin-top: 7px"
                                             oninput="$('#add_profesi').val(this.value);"></s:textfield>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Suku</label>
                                <s:select id="add_ket_suku"
                                          list="#{'Jawa':'Jawa','Batak':'Batak','Dayak':'Dayak','Asmat':'Asmat','Minahasa':'Minahasa','Melayu':'Melayu','Sunda':'Sunda','Madura':'Madura','Betawi':'Betawi','Bugis':'Bugis','Lainnya':'Lainnya'}"
                                          onchange="$('#add_suku').val(this.value); $(this).css('border',''); if(this.value == 'Lainnya'){$('#form_add_suku').show()}else{$('#form_add_suku').hide()} "
                                          headerKey="" headerValue="[Select One]"
                                          cssStyle="width: 100%" cssClass="form-control"/>
                                <s:hidden id="add_suku"></s:hidden>
                            </div>
                            <div class="form-group" style="display: none" id="form_add_suku">
                                <s:textfield placeholder="Keterangan Suku" cssClass="form-control"
                                             cssStyle="margin-top: 7px"
                                             oninput="$('#add_suku').val(this.value);"></s:textfield>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Alamat</label>
                                <input class="form-control" id="add_alamat" oninput="$(this).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">No Telp</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-phone"></i>
                                    </div>
                                    <input class="form-control" id="add_no_telp" oninput="$(this).css('border','')"
                                           data-inputmask="'mask': ['9999-9999-9999']"
                                           data-mask="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Provinsi</label>
                                <input class="form-control" id="add_provinsi"
                                       oninput="$(this).css('border',''); setProvAtas(this.id, 'add_id_provinsi')">
                                <input type="hidden" id="add_id_provinsi">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Kota</label>
                                <input class="form-control" id="add_kota"
                                       oninput="$(this).css('border',''); setKabAtas(this.id, 'add_id_kota', 'add_id_provinsi')">
                                <input type="hidden" id="add_id_kota">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Kecamatan</label>
                                <input class="form-control" id="add_kecamatan"
                                       oninput="$(this).css('border',''); setKecAtas(this.id, 'add_id_kecamatan', 'add_id_kota')">
                                <input type="hidden" id="add_id_kecamatan">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Kelurahan/Desa</label>
                                <input class="form-control" id="add_desa"
                                       oninput="$(this).css('border',''); setDesAtas(this.id, 'add_id_desa', 'add_id_kecamatan')">
                                <input type="hidden" id="add_id_desa">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Foto Identitas</label>
                                <div class="input-group">
                              <span class="input-group-btn">
                              <span class="btn btn-default btn-file">
                               Browse…<input type="file" accept=".jpg" name="fileUpload" id="ktp"
                                             onchange="$('#img_file').css('border',''); setCanvasAtas('img_ktp_canvas')">
                              </span>
                              </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <canvas id="img_ktp_canvas"
                                        style="border: solid 1px #ccc; width: 100%; height: 135px"></canvas>
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
                        <div class="col-md-4">
                            <div class="form-group">
                                <label >Nama Penanggung</label>
                                <s:textfield id="nama_penanggung" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>No Telp.</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-phone"></i>
                                    </div>
                                    <s:textfield id="no_telp" data-inputmask="'mask': ['9999-9999-9999']" data-mask="" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="form-group">
                                    <label style="margin-bottom: -1px">Hubungan</label>
                                    <s:select
                                            list="#{'Ayah':'Ayah','Ibu':'Ibu','Kakak':'Kakak','Adik':'Adik','Sepupu':'Sepupu','Ipar':'Ipar','Anak':'Anak','Suami':'Suami','Istri':'Istri','Lainnya':'Lainnya'}"
                                            onchange="$('#hub_keluarga').val(this.value); var cek = this.value; if(cek == 'Lainnya'){$('#ket_hubungan').show()}else{$('#ket_hubungan').hide()}"
                                            id="hubungan"
                                            headerKey="" headerValue="[Select one]"
                                            cssClass="form-control select2"/>
                                    <s:hidden id="hub_keluarga"></s:hidden>
                                </div>
                                <div class="form-group">
                                    <s:textfield oninput="$('#hub_keluarga').val(this.value)" id="ket_hubungan"
                                                 cssClass="form-control" cssStyle="margin-top: 7px; display: none"
                                                 placeholder="Keterangan Hubungan"/>
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
                        <div class="col-md-4">
                            <div class="form-group">
                                <label style="margin-bottom: -1px">Pelayanan</label>
                                <select style="width: 100%" class="form-control select2" id="poli_edit"
                                        onchange="listDokter(this.value); var warn =$('#war_poli').is(':visible'); if (warn){$('#cor_poli').show().fadeOut(3000);$('#war_poli').hide()}; cekKunjunganPoli(this.value)">
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Dokter</label>
                                <div class="input-group">
                                    <input readonly class="form-control" id="nama_dokter" placeholder="*klik untuk jadwal dokter">
                                    <div class="input-group-btn">
                                        <a class="btn btn-success">
                                            <span id="btn-dokter">
                                                <i class="fa fa-search"></i> Dokter
                                            </span>
                                        </a>
                                    </div>
                                </div>
                                <s:hidden id="dokter"></s:hidden>
                            </div>
                        </div>
                        <div class="col-md-4">

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_edit" onclick="cekSaveNewPasien()"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_edit"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>
    function detail_pasien(idCheckup, idDetail) {
        var table = "";
        var dataRiwayat = [];
        var dataPasien = [];
        var noCheckup = "";
        var nik = "";
        var namaPasien = "";
        var jenisKelamin = "";
        var tglLahir = "";
        var agama = "";
        var suku = "";
        var alamat = "";
        var provinsi = "";
        var kabupaten = "";
        var kecamatan = "";
        var desa = "";
        var pelayanan = "";
        var ket = "";
        var ruangan = "";
        var noRuangan = "";

        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#v_'+idCheckup).attr('src',url).css('width', '30px', 'height', '40px');


        setTimeout(function () {

            var url = '<s:url value="/pages/images/icons8-search-25.png"/>';
            $('#v_'+idCheckup).attr('src',url).css('width', '', 'height', '');

            CheckupAction.listDataPasien(idDetail, function (response) {
                // dataPasien = response;
                console.log(response);
                if (response != null) {
                //     $.each(dataPasien, function (i, item) {
                        var tanggal = response.tglLahir;
                        var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                        noCheckup = response.noCheckup;
                        nik = response.noKtp;
                        namaPasien = response.nama;

                        if (response.jenisKelamin == "L") {
                            jenisKelamin = "Laki-Laki";
                        } else {
                            jenisKelamin = "Perempuan";
                        }

                        tglLahir = response.tempatLahir + ", " + dateFormat;
                        agama = response.agama;
                        suku = response.suku;
                        alamat = response.jalan;
                        provinsi = response.namaProvinsi;
                        kabupaten = response.namaKota;
                        kecamatan = response.namaKecamatan;
                        desa = response.namaDesa;
                        $('#det_no_rm').text(response.idPasien);

                        if(response.urlDocRujuk != null && response.urlDocRujuk != ''){
                            $('#surat_rujukan').show();
                        }else{
                            $('#surat_rujukan').hide();
                        }

                    $('#show_rujukan').attr('onclick','showRujukan(\''+response.urlDocRujuk+'\')');
                    // });
                }
            });

            CheckupAction.listRiwayatPasien(idCheckup, function (response) {
                dataRiwayat = response;
                if (dataRiwayat != null) {
                    $.each(dataRiwayat, function (i, item) {

                        if (item.namaPelayanan) {
                            pelayanan = item.namaPelayanan;
                        }
                        if (item.keteranganSelesai) {
                            ket = item.keteranganSelesai;
                        }
                        if (item.namaRuangan) {
                            ruangan = item.namaRuangan;
                        }
                        if (item.noRuangan) {
                            noRuangan = item.noRuangan;
                        }

                        table += "<tr>" +
                                "<td>" + pelayanan + "</td>" +
                                "<td>" + item.statusPeriksa + "</td>" +
                                "<td>" + ket + "</td>" +
                                "<td>" + ruangan + "</td>" +
                                "<td>" + noRuangan + "</td>" +
                                "</tr>"
                    });
                }
            });

            $('#det_no_checkup').html(noCheckup);
            $('#det_nik').html(nik);
            $('#det_nama').html(namaPasien);
            $('#det_jenis_kelamin').html(jenisKelamin);
            $('#det_tgl').html(tglLahir);
            $('#det_agama').html(agama);
            $('#det_suku').html(suku);
            $('#det_alamat').html(alamat);
            $('#det_provinsi').html(provinsi);
            $('#det_kabupaten').html(kabupaten);
            $('#det_kecamatan').html(kecamatan);
            $('#det_desa').html(desa);
            $('#det_riwayat').html(table);
            $('#modal-detail-pasien').modal('show');
        }, 100)
    }

    $('#btnFingerPrint').on('click',function() {
        $('#modal-add-finger').modal('show');
    });

    $('#btnSearchFingerPrint').on('click',function() {
        var idPasien = $('#fin_id_pas').val();
        if (idPasien==""){
            alert("nomor BPJS masih kosong");
        } else{
            var url=btoa('http://192.168.43.222:8080/simrs/loginFinger.action?userId='+idPasien+'&tipe=bpjs');
            window.location.href = 'finspot:FingerspotVer;'+url;
        }
    });

    function saveAntrian() {
        var noAntrian = $('#id_antrian').val();
        if(noAntrian != ''){
            $('#load_resep').show();
            $('#save_resep').hide();
            dwr.engine.setAsync(true);
            CheckupAction.getDetailAntrianOnline(noAntrian, {callback: function (response) {

                    if (response.noCheckupOnline != null) {
                        var today = new Date();
                        var dd = String(today.getDate()).padStart(2, '0');
                        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                        var yyyy = today.getFullYear();
                        var tglToday = mm + '-' + dd + '-' + yyyy;

                        var daftar = new Date(response.stTglCheckup);
                        var dDaf = String(daftar.getDate()).padStart(2, '0');
                        var mDaf = String(daftar.getMonth() + 1).padStart(2, '0'); //January is 0!
                        var yDaf = daftar.getFullYear();
                        var tglDaftar = mDaf + '-' + dDaf + '-' + yDaf;

                        var tgl1 = new Date(tglToday);
                        var tgl2 = new Date(tglDaftar);

                        var tanggalToday = Math.abs(tgl1);
                        var tanggalCheckup = Math.abs(tgl2);

                        var time = response.jamAwal;
                        var char = time.split(":");
                        var hh2 = char[0];
                        var min2 = char[1];

                        var timeDaftar = new Date();
                        timeDaftar.setHours(hh2, min2, 0);
                        timeDaftar.setMinutes(timeDaftar.getMinutes() - 30);

                        var timeToday = new Date();

                        if (tanggalToday == tanggalCheckup) {
                            if(Math.abs(timeToday) <= Math.abs(timeDaftar)){
                                // var tipe = "";
                                //
                                // if (response.idJenisPeriksaPasien == "bpjs") {
                                //     tipe = "bpjs";
                                // } else {
                                //     tipe = "umum";
                                // }

                                window.location.href = 'add_checkup.action?tipe='+response.idJenisPeriksaPasien+'&noCheckupOnline='+response.noCheckupOnline;
                                $('#load_resep').hide();
                                $('#save_resep').show();
                            }else{
                                $('#error_dialog').dialog('open');
                                $('#errorMessage').text("Verifikasi sudah tidak bisa dilakukan, dikarenakan sudah lewat dari jam awal pelayanan...!, Silahkan lakukan pendaftaran manual.");
                                $('#load_resep').hide();
                                $('#save_resep').show();
                            }

                        } else {
                            $('#id_antrian').val('');
                            $('#load_resep').hide();
                            $('#save_resep').show();
                            $('#error_dialog').dialog('open');
                            $('#errorMessage').text("Verifikasi sudah tidak bisa dilakukan, dikarenakan sudah lewat dari jam awal pelayanan...!, Silahkan lakukan pendaftaran manual.");
                        }
                    } else {
                        $('#load_resep').hide();
                        $('#save_resep').show();
                        $('#error_dialog').dialog('open');
                        $('#errorMessage').text("No Checkup online tidak dapat ditemukan. Silahkan lakukan pendaftaran manual");
                    }
                }
            });
        }
    }

    function saveAktivasi(noCheckupOnline){
        CheckupAction.aktivasiAntrianOnline(noCheckupOnline, function (response) {
            if(response.status == "success"){
                $('#modal-antrian').modal('hide');
                $('#info_dialog').dialog('open');
            }
        })
    }

    function showRujukan(url){
        if(url != null){
            $('#img_surat_rujuk').attr('src',url);
            $('#modal-rujukan').modal('show');
        }else{

        }
    }

    function cancelPeriksa(idDetailCHeckup){
        CheckupAction.listDataPasien(idDetailCHeckup, {
            callback: function (res) {
                $('#det_no_checkup_cancel').text(res.noCheckup);
                $('#det_id_detail_checkup').text(res.idDetailCheckup);
                $('#det_no_rm_cancel').text(res.idPasien);
                $('#det_nama_pasien').text(res.nama);
                $('#det_pelayanan').text(res.namaPelayanan);
                $('#det_jenis_pasien').text(res.statusPeriksaName);
                $('#det_alamat_batal').text(res.namaDesa+", "+res.namaKecamatan+", "+res.namaKota);
            }
        });
        $('#save_add').attr('onclick', 'saveCancel(\''+idDetailCHeckup+'\')');
        $('#modal-detail').modal({show: true, backdrop:'static'});
    }

    function saveCancel(idDetailCHeckup){
        var alsan = $('#set_alasan').val();
        if(alsan != ''){
            $('#save_add').hide();
            $('#load_add').show();
            dwr.engine.setAsync(true);
            CheckupAction.cancelPeriksa(idDetailCHeckup, alsan, {
                callback: function (res) {
                    if(res.status == "success"){
                        $('#save_add').show();
                        $('#load_add').hide();
                        $('#modal-detail').modal('hide');
                        $('#info_dialog').dialog('open');
                    }else{
                        $('#save_add').show();
                        $('#load_add').hide();
                        $('#warning_cancel').show().fadeOut(5000);
                        $('#msg_cancel').text(res.msg);
                    }
                }
            });
        }else{
            $('#warning_cancel').show().fadeOut(5000);
            $('#msg_cancel').text("Silahkan masukkan alasan pasien...!");
        }
    }

    function editPeriksa(id){
        $('#modal-edit-pasien').modal({show: true, backdrop: 'static'});
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
