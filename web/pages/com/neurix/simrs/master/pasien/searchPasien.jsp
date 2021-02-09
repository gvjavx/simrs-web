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

    <script type='text/javascript' src='<s:url value="/dwr/interface/PasienAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            $('#pasien').addClass('active');
            searchPasien();
        });

        function searchPasien() {
            var url_string = window.location.href;
            var url = new URL(url_string);
            var idPasien = url.searchParams.get("id_pasien");
            if (idPasien != null) {
                $('#modal-success-pasien').modal('show');
                $('#val_id_pasien').val(idPasien);
            }
        }
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
            Data Pasien
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_search">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_search"></p>
                        </div>
                        <div class="form-group">
                            <s:form onsubmit="return cekSearch()" id="pasienForm" method="post" namespace="/pasien"
                                    action="search_pasien.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">NO RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="pasien.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="pasien.nama"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="pasienForm"
                                                   id="search" name="search"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_pasien.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a href="add_pasien.action" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Add Pasien</a>
                                        <button type="button" class="btn btn-primary" onclick="showModalUpload()">
                                            <i class="fa fa-plus"></i> Upload Rekam Medik Lama
                                        </button>
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
                                                <img class="spin" border="0"
                                                     style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>
                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                   resizable="false"
                                                   closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="close_pos"></s:hidden>
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped tablePasien">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>NO RM</td>
                                <td>Nama</td>
                                <td>Jenis Kelamin</td>
                                <td>Tempat, Tgl Lahir</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td>
                                        <s:if test='#row.jenisKelamin == "L"'>
                                            Laki-Laki
                                        </s:if>
                                        <s:else>
                                            Perempuan
                                        </s:else>
                                    </td>
                                    <td><s:property value="tempatLahir"/>, <s:property value="tglLahir"/></td>
                                        <%--<td><s:property value="password"/></td>--%>
                                    <td align="center">
                                        <img id="t<s:property value="idPasien"/>" class="hvr-grow"
                                             onclick="detail('<s:property value="idPasien"/>')" style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <s:if test='#row.password == null || #row.password == ""'>
                                            <img class="hvr-grow"
                                                 onclick="setPassword('<s:property value="idPasien"/>')"
                                                 style="cursor: pointer"
                                                 src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        </s:if>
                                        <s:else>
                                            <img class="hvr-grow"
                                                 onclick="setPassword('<s:property value="idPasien"/>')"
                                                 style="cursor: pointer"
                                                 src="<s:url value="/pages/images/icons8-create-orange-25.png"/>">
                                        </s:else>
                                        <s:url var="print_card" namespace="/pasien" action="printCard_pasien"
                                               escapeAmp="false">
                                            <s:param name="id"><s:property value="idPasien"/></s:param>
                                        </s:url>
                                        <s:a href="%{print_card}" target="_blank">
                                            <img class="hvr-grow" style="cursor: pointer"
                                                 src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                        </s:a>
                                        <s:if test='#row.disabledFingerData == true'>
                                            <img class="hvr-grow" style="cursor: pointer"
                                                 src="<s:url value="/pages/images/icons8-fingerprint-accepted-25.png"/>"
                                                 onclick="registrasiFinger('<s:property value="idPasien"/>')">
                                        </s:if>
                                        <s:else>
                                            <img class="hvr-grow" style="cursor: pointer"
                                                 src="<s:url value="/pages/images/icons8-fingerprint-scan-25.png"/>"
                                                 onclick="registrasiFinger('<s:property value="idPasien"/>')">
                                        </s:else>
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

<div class="modal fade" id="modal-upload">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekam Medik Pasien <span
                        id="nama_medik"></span></h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_upload">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_upload"></p>
                </div>
                <%--<s:form id="uploadForm" method="post" enctype="multipart/form-data" theme="simple" namespace="/pasien" action="saveUploadRmLama_pasien.action" cssClass="form-horizontal">--%>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">No RM</label>
                        <div class="col-md-7">
                            <div class="input-group">
                                <s:textfield id="upload_pasien"
                                             onkeypress="$(this).css('border','');"
                                             cssClass="form-control"
                                             placeholder="ketik nama atau rm baru atau rm lama"/>
                                <div class="input-group-btn">
                                    <a href="#" class="btn btn-primary pull-right" onclick="addInputUpload()"><i
                                            class="fa fa-plus"></i> Add Upload</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-warning" onclick="removeAll()"><i class="fa fa-refresh"></i></button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">No RM Lama</label>
                        <div class="col-md-7">
                            <s:textfield id="upload_no_rm_lama"
                                         cssStyle="margin-top: 7px"
                                         onkeypress="$(this).css('border','');"
                                         cssClass="form-control" readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Pasien</label>
                        <div class="col-md-7">
                            <s:textfield id="upload_nama_pasien"
                                         cssStyle="margin-top: 7px"
                                         onkeypress="$(this).css('border','');"
                                         cssClass="form-control" readonly="true"/>
                        </div>
                    </div>
                    <script type="application/javascript">
                        var functions, mapped;
                        $('#upload_pasien').typeahead({
                            minLength: 1,
                            source: function (query, process) {
                                functions = [];
                                mapped = {};

                                var data = [];
                                dwr.engine.setAsync(false);

                                PasienAction.getListComboPasienByRmLama(query, function (listdata) {
                                    data = listdata;
                                });

                                $.each(data, function (i, item) {
                                    var labelItem = item.idPasien + "-" + item.noRmLama + "-" + item.nama;
                                    mapped[labelItem] = {
                                        id: item.idPasien,
                                        nama: item.nama,
                                        noRmLama: item.noRmLama
                                    };
                                    functions.push(labelItem);
                                });
                                process(functions);

                            },
                            updater: function (item) {
                                var selectedObj = mapped[item];
                                $('#upload_nama_pasien').val(selectedObj.nama);
                                $('#upload_no_rm_lama').val(selectedObj.noRmLama);
                                $('#upload_pasien').attr('disabled', true);
                                return selectedObj.id;
                            }
                        });
                    </script>
                </div>
                <div class="row">
                    <div id="body-rm">
                    </div>
                </div>
                <%--</s:form>--%>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success pull-right" id="save_upload"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success pull-right"
                        id="load_upload"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-password">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Edit Password</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_password">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_password"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">ID Pasien</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_id_pasien" readonly
                                   oninput="var warn =$('#war_set_id_pasien').is(':visible'); if (warn){$('#cor_set_id_pasien').show().fadeOut(3000);$('#war_set_id_pasien').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_id_pasien">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_id_pasien"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_nama" readonly style="margin-top: 7px"
                                   oninput="var warn =$('#war_set_nama').is(':visible'); if (warn){$('#cor_set_nama').show().fadeOut(3000);$('#war_set_nama').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Password</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px">
                                <input type="password" class="form-control" id="set_password"
                                       oninput="var warn =$('#war_set_password').is(':visible'); if (warn){$('#cor_set_password').show().fadeOut(3000);$('#war_set_password').hide()}">
                                <div class="input-group-btn" onclick="seePassword()">
                                    <a class="btn btn-success" id="btn_see"><i id="fa_see" class="fa fa-eye"></i>&nbsp;</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_password">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_password"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_password"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_password">
                    <i
                            class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-detail">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> <span id="title_modal">Detail Data Pasien</span></h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_edit">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_war_edit"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_edit">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_suc_edit"></p>
                </div>
                <input type="hidden" id="h_id_pasien">
                <div class="box-body" style="display:none;" id="form-detail">
                    <div class="row">
                        <div class="col-md-6">
                            <img id="img_ktp" style="height: 200px; width: 100%">
                            <table class="table table-striped" style="margin-top: 20px">
                                <tr>
                                    <td width="40%"><b>NO RM</b></td>
                                    <td><span id="an_id_pasien"></span></td>
                                </tr>
                                <tr id="form_no_bpjs">
                                    <td><b>NO BPJS</b></td>
                                    <td><span id="an_no_bpjs"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="an_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="an_nama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, Tgl Lahir</b></td>
                                    <td><span id="an_tgl"></span></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%"><b>Jenis Kelamin</b></td>
                                    <td><span id="an_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Agama</b></td>
                                    <td><span id="an_agama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Suku</b></td>
                                    <td><span id="an_suku"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Profesi</b></td>
                                    <td><span id="an_profesi"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Status</b></td>
                                    <td><span id="an_status"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Pendidikan</b></td>
                                    <td><span id="an_pendidikan"></span></td>
                                </tr>
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
                            </table>
                        </div>
                    </div>
                </div>
                <div class="box-body" style="display:none;" id="form-edit">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>NO RM</label>
                                <input class="form-control" readonly id="add_no_rm" oninput="$(this).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">NO BPJS</label>
                                <input class="form-control" id="add_no_bpjs" oninput="$(this).css('border','')"
                                       type="number">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">NIK</label>
                                <input class="form-control" id="add_nik" oninput="$(this).css('border','')"
                                       type="number">
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
                                <input class="form-control" id="add_tempat_lahir" oninput="$(this).css('border',''); setKotaKab(this.id)">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Tanggal Lahir</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input style="cursor: pointer;" class="form-control datepicker datemask" id="add_tanggal_lahir"
                                           onchange="$(this).css('border','')" readonly placeholder="yyyy-mm-dd">
                                </div>
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
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Profesi</label>
                                <s:select id="ket_add_profesi"
                                          list="#{'Guru':'Guru','Dokter':'Dokter','Swasta':'Swasta','PNS':'PNS','Lainnya':'Lainnya'}"
                                          onchange="$('#add_profesi').val(this.value); $(this).css('border',''); if(this.value == 'Lainnya'){$('#form_add_profesi').show()}else{$('#form_add_profesi').hide()} "
                                          headerKey="" headerValue="[Select One]"
                                          cssStyle="width: 100%" cssClass="form-control"/>
                                <s:hidden id="add_profesi"></s:hidden>
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
                                <textarea rows="4" class="form-control" id="add_alamat"></textarea>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 15px">No Telp</label>
                                <input oninput="$(this).css('border','')" class="form-control" id="add_no_telp" data-inputmask="'mask': ['9999-9999-9999']"
                                       data-mask="">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Pendidikan</label>
                                <s:select id="add_pendidikan"
                                          list="#{'SD/Sederajat':'SD/Sederajat','SMP/Sederajat':'SMP/Sederajat','SMA/Sederajat':'SMA/Sederajat','S1':'S1','S2':'S3','S3':'S3'}"
                                          onchange="$(this).css('border','')"
                                          headerKey="" headerValue="[Select One]"
                                          cssClass="form-control"/>
                            </div>
                            <div class="form-group" style="display: none" id="form_add_profesi">
                                <s:textfield placeholder="Keterangan Profesi" cssClass="form-control"
                                             cssStyle="margin-top: 7px"
                                             oninput="$('#add_profesi').val(this.value);"></s:textfield>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Status Perkawinan</label>
                                <s:select id="add_status_perkawinan"
                                          list="#{'Kawin':'Kawin','Belum Kawin':'Belum Kawin'}"
                                          onchange="$(this).css('border','')"
                                          headerKey="" headerValue="[Select One]"
                                          cssClass="form-control"/>
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
                                <label style="margin-top: 7px">Flag</label>
                                <select class="form-control" id="add_flag" onchange="$(this).css('border','')">
                                    <option value="">[Select One]</option>
                                    <option value="Y">Active</option>
                                    <option value="N">Non-Active</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Password</label>
                                <div class="input-group">
                                    <input oninput="$(this).css('border','')" class="form-control" id="add_password" type="password">
                                    <div onclick="seePassw()" class="input-group-addon" style="cursor: pointer">
                                        <i id="icon-password" class="fa fa-eye"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Foto Identitas</label>
                                <div class="input-group" id="img_file">
                              <span class="input-group-btn">
                              <span class="btn btn-default btn-file">
                               Browse… <s:file id="imgInp" accept=".jpg" name="fileUpload"
                                               onchange="$('#img_ktp_canvas').css('border','solid 1px #ccc'); setCanvas('img_ktp_canvas')"></s:file>
                                                    </span>
                                                    </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <canvas id="img_ktp_canvas" style="border: solid 1px #ccc; width: 100%; height: 150px"></canvas>
                                <input type="hidden" id="add_img_ktp">
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button id="btn_edit" value="edit" type="button" onclick="setEdit()" class="btn btn-primary pull-left">
                    <i class="fa fa-edit"></i> <span id="label_btn">Edit</span>
                </button>
                <button style="display: none" id="btn_save" type="button" onclick="conEdit()"
                        class="btn btn-success pull-left"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success pull-left"
                        id="load_save"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4>Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-success-pasien">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"></h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success alert-dismissible">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    Berhasil melakukan registrasi fingerprint
                </div>
            </div>
            <input id="val_id_pasien" type="hidden">
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" onclick="pasienSuccess()"><i class="fa fa-check"></i> OK
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function pasienSuccess() {
        var idPasien = $('#val_id_pasien').val();
        $('#id_pasien').val(idPasien);
        document.pasienForm.action = 'search_pasien.action';
        document.pasienForm.submit();
    }

    function detail(pasiendId) {
        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#t' + pasiendId).attr('src', url).css('width', '30px', 'height', '40px');
        $('#btn_edit').val("edit");
        $('#label_btn').text('Edit');
        $('#btn_edit').removeClass("btn btn-warning pull-left");
        $('#btn_edit').addClass("btn btn-primary pull-left");
        $('#btn_save').hide();
        $('#form-detail').show();
        $('#form-edit').hide();
        $('#load_save').hide();
        setTimeout(function () {
            $('#title_modal').text("Detail Data Pasien");
            dataPasienSet(pasiendId);
            var url = '<s:url value="/pages/images/icons8-view-25.png"/>';
            $('#t' + pasiendId).attr('src', url).css('width', '', 'height', '');
            $('#modal-detail').modal({show: true, backdrop: 'static'});
        }, 100);
    }

    function dataPasienSet(pasiendId) {
        PasienAction.getDataPasien(pasiendId, function (response) {
            if (response != null) {
                var jenisKelamin = "";
                var tglLahir = "";

                if (response.jenisKelamin == "L") {
                    jenisKelamin = "Laki-Laki";
                } else {
                    jenisKelamin = "Perempuan";
                }
                tglLahir = response.tempatLahir + ", " + formateDate(response.tglLahir);

                $('#h_id_pasien').val(response.idPasien);
                $('#an_id_pasien').html(response.idPasien);
                if(response.noBpjs != null && response.noBpjs != ''){
                    $('#form_no_bpjs').show();
                }else{
                    $('#form_no_bpjs').hide();
                }
                $('#an_no_bpjs').html(response.noBpjs);
                $('#an_nik').html(response.noKtp);
                $('#an_nama').html(response.nama);
                $('#an_jenis_kelamin').html(jenisKelamin);
                $('#an_tgl').html(tglLahir);
                $('#an_agama').html(response.agama);
                $('#an_status').html(response.statusPerkawinan);
                $('#an_profesi').html(response.profesi);
                $('#an_pendidikan').html(response.pendidikan);
                $('#an_suku').html(response.suku);
                $('#an_alamat').html(response.jalan);
                $('#an_provinsi').html(response.provinsi);
                $('#an_kabupaten').html(response.kota);
                $('#an_kecamatan').html(response.kecamatan);
                $('#an_desa').html(response.desa);
                var img = contextPathHeader+'/pages/images/no-images.png';
                if(cekImages(response.urlKtp)){
                    $('#img_ktp').attr('src', response.urlKtp);
                }else{
                    $('#img_ktp').attr('src', img);
                }
                $('#add_no_rm').val(response.idPasien);
                $('#add_no_bpjs').val(response.noBpjs);
                $('#add_flag').val(response.flag);
                $('#add_nik').val(response.noKtp);
                $('#add_nama').val(response.nama);
                $('#add_jk').val(response.jenisKelamin);
                $('#add_tempat_lahir').val(response.tempatLahir);
                $('#add_tanggal_lahir').val(response.tglLahir);
                $('#add_agama').val(response.agama);
                $('#add_profesi').val(response.profesi);
                $('#ket_add_profesi').val(response.profesi);
                $('#add_suku').val(response.suku);
                $('#add_ket_suku').val(response.suku);
                $('#add_alamat').val(response.jalan);
                $('#add_no_telp').val(response.noTelp);
                $('#add_provinsi').val(response.provinsi);
                $('#add_id_provinsi').val(response.provinsiId);
                $('#add_kota').val(response.kota);
                $('#add_id_kota').val(response.kotaId);
                $('#add_kecamatan').val(response.kecamatan);
                $('#add_id_kecamatan').val(response.kecamatanId);
                $('#add_desa').val(response.desa);
                $('#add_id_desa').val(response.desaId);
                $('#add_img_ktp').val(response.imgKtp);
                $('#add_password').val(response.password);
                $('#add_status_perkawinan').val(response.statusPerkawinan);
                $('#add_pendidikan').val(response.pendidikan);
                if (response.urlKtp != null) {
                    var canvas = document.getElementById('img_ktp_canvas');
                    var ctx = canvas.getContext('2d');
                    var img = new Image();
                    img.src = response.urlKtp;
                    img.onload = function (ev) {
                        canvas.width = img.width;
                        canvas.height = img.height;
                        ctx.clearRect(0, 0, canvas.width, canvas.height);
                        ctx.drawImage(img, 0, 0);
                    }
                }
            }
        });
    }

    function formateDate(tanggal) {

        var tgl = "";
        if (tanggal != null && tanggal != '') {
            tgl = $.datepicker.formatDate("dd-mm-yy", new Date(tanggal));
        }
        return tgl;
    }

    function setPassword(idPasien) {
        if (idPasien != '') {
            $('#modal-password').modal({show: true, backdrop: 'static'});
            PasienAction.getDataPasien(idPasien, function (response) {
                if (response.idPasien != null) {
                    $('#set_id_pasien').val(response.idPasien);
                    $('#set_nama').val(response.nama);
                    $('#set_password').val(response.password);
                } else {

                }
            });

            $('#save_password').attr('onclick', 'savePassword(\'' + idPasien + '\')');
        }
    }

    function seePassword() {
        var type = $('#set_password').attr('type');
        if (type == 'password') {
            $('#set_password').removeAttr('type');
            $('#set_password').attr('type', 'text');
            $('#btn_see').removeClass('btn btn-success').addClass('btn btn-warning');
            $('#fa_see').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
        } else {
            $('#set_password').removeAttr('type');
            $('#set_password').attr('type', 'password');
            $('#btn_see').removeClass('btn btn-warning').addClass('btn btn-success');
            $('#fa_see').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
        }
    }

    function savePassword(idPasien) {
        var password = $('#set_password').val();
        if (password != '') {

            $('#save_password').hide();
            $('#load_password').show();
            dwr.engine.setAsync(true);
            PasienAction.setPasswordPasien(idPasien, password, function (response) {
                if (response.status == "success") {
                    $('#modal-password').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#save_password').show();
                    $('#load_password').hide();
                } else {
                    $('#save_password').show();
                    $('#load_password').hide();
                    $('#warning_password').show().fadeOut(5000);
                    $('#msg_password').text(response.message);
                }
            });
        } else {
            $('#warning_password').show().fadeOut(5000);
            $('#msg_password').text('Silahkan cek kembali data inputan..!');
            if (password == '') {
                $('#war_set_password').show();
            }
        }
    }

    function registrasiFinger(idPasien) {
        var hostname = window.location.origin + contextPathHeader;
        if (idPasien != '') {
            var url = btoa(hostname + '/registerFinger.action?userId=' + idPasien + '&hostname=' + hostname);
            var href = 'finspot:FingerspotReg;' + url;
            window.location.href = href;
        }
    }

    function showModalUpload() {
        $('#upload_pasien').attr('disabled', false);
        $('#save_upload').show();
        $('#load_upload').hide();
        $('#save_upload').attr('onclick', 'cekUpload()');
        $('#upload_pasien, #upload_nama_pasien, #upload_no_rm_lama').val('');
        $('#body-rm').html('');
        $("#modal-upload").modal({show: true, backdrop: 'static'});
    }

    function cekUpload() {
        var idPasien = $('#upload_pasien').val();
        var img = $('.form-img-rm');
        var cek = false;
        $.each(img, function (i, item) {
            if (item.value == '') {
                cek = true;
            }
        });
        if (idPasien != '' && !cek && img.length > 0) {
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'saveUpload()');
        } else {
            $('#warning_upload').show().fadeOut(5000);
            $('#msg_upload').text("Silahkan cek kembali inputan anda...!");
        }
    }

    function saveUpload() {
        $('#modal-confirm-dialog').modal('hide');
        var idPasien = $('#upload_pasien').val();
        var noRmLama = $('#upload_no_rm_lama').val();
        var img = $('.form-img-rm');
        var data = [];
        $.each(img, function (i, item) {
            var canvas = document.getElementById('cav_' + i);
            if (item.value != '') {
                var url = convertToDataURLAtas(canvas);
                data.push({
                    'gambar': url
                })
            }
        });
        var result = JSON.stringify(data);
        $('#save_upload').hide();
        $('#load_upload').show();
        dwr.engine.setAsync(true);
        PasienAction.saveUploadRmLama(result, idPasien, noRmLama, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_upload').show();
                    $('#load_upload').hide();
                    $('#info_dialog').dialog('open');
                    $('#modal-upload').modal('hide');
                } else {
                    $('#save_upload').show();
                    $('#load_upload').hide();
                    $('#warning_upload').show().fadeOut(5000);
                    $('#msg_upload').text(res.msg);
                }
            }
        });
    }

    function addInputUpload() {
        var i = $('.img-upload').length;
        var number = i + 1;
        var str = '<div class="form-group img-upload" id="row_' + i + '">' +
            '<label class="col-md-3" style="margin-top: 8px">Upload Foto Rekam Medik ' + number + '</label>' +
            '<div class="col-md-7">' +
            '<canvas id="cav_' + i + '" style="display: none"></canvas>' +
            '<input id="text_' + i + '" onchange="setCanvasAtasWithText(\'cav_' + i + '\', \'text_' + i + '\')" type="file" name="fileUploadImage" class="form-control form-img-rm" style="margin-top: 7px">' +
            <%--'<s:file id="upload-img" name="fileUploadImage" cssClass="form form-control"/>'+--%>
            '</div>' +
            '<div class="col-md-1" style="margin-left: -20px; margin-top: 10px">' +
            '<a class="btn btn-danger" onclick="delUpload(\'row_' + i + '\')"><i class="fa fa-trash"></i></a>';
        '</div>' +
        '</div>';
        $('#body-rm').append(str);
    }

    function delUpload(id) {
        $('#' + id).remove();
    }

    function setEdit() {
        var text = $('#btn_edit').val();
        var idPasien = $('#h_id_pasien').val();
        if (text == "edit") {
            dataPasienSet(idPasien);
            $('#btn_edit').val("batal_edit");
            $('#label_btn').text('Batal Edit');
            $('#btn_edit').removeClass("btn btn-primary pull-left");
            $('#btn_edit').addClass("btn btn-warning pull-left");
            $('#btn_save').show();
            $('#form-detail').hide();
            $('#form-edit').show();
            $('#title_modal').text("Edit Data Pasien");
        } else {
            $('#btn_edit').val("edit");
            $('#label_btn').text('Edit');
            $('#btn_edit').removeClass("btn btn-warning pull-left");
            $('#btn_edit').addClass("btn btn-primary pull-left");
            $('#btn_save').hide();
            $('#form-detail').show();
            $('#form-edit').hide();
            $('#load_save').hide();
            $('#title_modal').text("Detail Data Pasien");
        }
    }

    function setCanvas(canvas) {
        var canvas = document.getElementById(canvas);
        var ctx = canvas.getContext('2d');
        var reader = new FileReader();
        reader.onload = function (event) {
            var img = new Image();
            img.onload = function () {
                canvas.width = img.width;
                canvas.height = img.height;
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.drawImage(img, 0, 0);
            }
            img.src = event.target.result;
        }
        reader.readAsDataURL(event.target.files[0]);
        $('#add_img_ktp').val('ganti');
    }

    function conEdit() {
        var idPasien = $('#add_no_rm').val();
        var noTelp = $('#add_no_telp').val();
        var noBpjs = $('#add_no_bpjs').val();
        var nik = $('#add_nik').val();
        var nama = $('#add_nama').val();
        var jk = $('#add_jk').val();
        var tempatLahir = $('#add_tempat_lahir').val();
        var tanggalLahir = $('#add_tanggal_lahir').val();
        var agama = $('#add_agama').val();
        var profesi = $('#add_profesi').val();
        var suku = $('#add_suku').val();
        var alamat = $('#add_alamat').val();
        var provinsi = $('#add_id_provinsi').val();
        var kota = $('#add_id_kota').val();
        var kecamatan = $('#add_id_kecamatan').val();
        var desa = $('#add_id_desa').val();
        var flag = $('#add_flag').val();
        var password = $('#add_password').val();
        var statusPerkawinan = $('#add_status_perkawinan').val();
        var pendidikan = $('#add_pendidikan').val();

        var canvas = document.getElementById('img_ktp_canvas');
        var dataURL = canvas.toDataURL("image/png"),
            dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");

        var cekGanti = $('#add_img_ktp').val();
        var images = "";
        if (cekGanti == "ganti") {
            images = dataURL;
        } else {
            images = '';
        }

        console.log(profesi);
        console.log(suku);

        var foto = isCanvasBlank(canvas);
        if (nik != '' && nama != '' && jk != '' && tempatLahir != '' && tanggalLahir != '' &&
            agama != '' && provinsi != '' && kota != '' && kecamatan != '' && desa != '' &&
            password != '' && statusPerkawinan != '' && pendidikan != '' && !foto && noTelp != '' && profesi != '' && suku != '') {
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'saveEditPasien()');
        }else {
            $('#modal-detail').scrollTop(0);
            $('#warning_edit').show().fadeOut(5000);
            $('#msg_war_edit').text("Silahkan cek kembali data inputan anda...!");
            if (nik == '') {
                $('#add_nik').css('border', 'solid 1px red');
            }
            if (nama == '') {
                $('#add_nama').css('border', 'solid 1px red');
            }
            if (jk == '') {
                $('#add_jk').css('border', 'solid 1px red');
            }
            if (tempatLahir == '') {
                $('#add_tempat_lahir').css('border', 'solid 1px red');
            }
            if (tanggalLahir == '') {
                $('#add_tanggal_lahir').css('border', 'solid 1px red');
            }
            if (agama == '') {
                $('#add_agama').css('border', 'solid 1px red');
            }
            if (provinsi == '') {
                $('#add_provinsi').css('border', 'solid 1px red');
            }
            if (kota == '') {
                $('#add_kota').css('border', 'solid 1px red');
            }
            if (kecamatan == '') {
                $('#add_kecamatan').css('border', 'solid 1px red');
            }
            if (desa == '') {
                $('#add_desa').css('border', 'solid 1px red');
            }
            if (flag == '') {
                $('#flag').css('border', 'solid 1px red');
            }
            if (statusPerkawinan == '') {
                $('#add_status_perkawinan').css('border', 'solid 1px red');
            }
            if (pendidikan == '') {
                $('#add_pendidikan').css('border', 'solid 1px red');
            }
            if (password == '') {
                $('#add_password').css('border', 'solid 1px red');
            }
            if(foto){
                $('#img_ktp_canvas').css('border', 'solid 1px red');
            }
            if(noTelp == ''){
                $('#add_no_telp').css('border', 'solid 1px red');
            }
            if (profesi == '') {
                $('#ket_add_profesi').css('border', 'solid 1px red');
            }
            if (suku == '') {
                $('#add_ket_suku').css('border', 'solid 1px red');
            }
        }
    }

    function saveEditPasien() {
        $('#modal-confirm-dialog').modal('hide');
        var data = "";
        var idPasien = $('#add_no_rm').val();
        var noTelp = $('#add_no_telp').val();
        var noBpjs = $('#add_no_bpjs').val();
        var nik = $('#add_nik').val();
        var nama = $('#add_nama').val();
        var jk = $('#add_jk').val();
        var tempatLahir = $('#add_tempat_lahir').val();
        var tanggalLahir = $('#add_tanggal_lahir').val();
        var agama = $('#add_agama').val();
        var profesi = $('#add_profesi').val();
        var suku = $('#add_suku').val();
        var alamat = $('#add_alamat').val();
        var provinsi = $('#add_id_provinsi').val();
        var kota = $('#add_id_kota').val();
        var kecamatan = $('#add_id_kecamatan').val();
        var desa = $('#add_id_desa').val();
        var flag = $('#add_flag').val();
        var password = $('#add_password').val();
        var statusPerkawinan = $('#add_status_perkawinan').val();
        var pendidikan = $('#add_pendidikan').val();

        var canvas = document.getElementById('img_ktp_canvas');
        var dataURL = canvas.toDataURL("image/png"),
            dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");

        var cekGanti = $('#add_img_ktp').val();
        var images = "";
        if (cekGanti == "ganti") {
            images = dataURL;
        } else {
            images = '';
        }

        if (nik != '' && nama != '' && jk != '' && tempatLahir != '' && tanggalLahir != '' &&
            agama != '' && provinsi != '' && kota != '' && kecamatan != '' && desa != '' && password != '' && statusPerkawinan != '' && pendidikan != '') {

            data = {
                'id_pasien': idPasien,
                'nik': nik,
                'no_bpjs': noBpjs,
                'nama': nama,
                'jk': jk,
                'tempat_lahir': tempatLahir,
                'tanggal_lahir': tanggalLahir,
                'agama': agama,
                'profesi': profesi,
                'suku': suku,
                'alamat': alamat,
                'desa_id': desa,
                'no_telp': noTelp,
                'flag': flag,
                'status': statusPerkawinan,
                'pendidikan': pendidikan,
                'password':password,
                'img_ktp': images
            };

            var objectString = JSON.stringify(data);
            $('#btn_save').hide();
            $('#load_save').show();
            dwr.engine.setAsync(true);
            PasienAction.saveEditPasien(objectString, {
                callback: function (response) {
                    if (response.status == "success") {
                        $('#modal-detail').scrollTop(0);
                        dataPasienSet(idPasien);
                        $('#success_edit').show().fadeOut(5000);
                        $('#msg_suc_edit').text("Berhasil mengupdate data pasien....");
                        $('#btn_edit').val("edit");
                        $('#label_btn').text('Edit');
                        $('#btn_edit').removeClass("btn btn-warning pull-left");
                        $('#btn_edit').addClass("btn btn-primary pull-left");
                        $('#btn_save').hide();
                        $('#load_save').hide();
                        $('#form-detail').show();
                        $('#form-edit').hide();
                        $('#title_modal').text("Detail Data Pasien");
                    } else {
                        $('#modal-detail').scrollTop(0);
                        $('#btn_save').show();
                        $('#load_save').hide();
                        $('#warning_edit').show().fadeOut(5000);
                        $('#msg_war_edit').text(response.msg);
                    }
                }
            });
        } else {
            $('#warning_edit').show().fadeOut(5000);
            $('#msg_war_edit').text("Silahkan cek kembali data inputan anda...!");
            if (nik == '') {
                $('#add_nik').css('border', 'solid 1px red');
            }
            if (nama == '') {
                $('#add_nama').css('border', 'solid 1px red');
            }
            if (jk == '') {
                $('#add_jk').css('border', 'solid 1px red');
            }
            if (tempatLahir == '') {
                $('#add_tempat_lahir').css('border', 'solid 1px red');
            }
            if (tanggalLahir == '') {
                $('#add_tanggal_lahir').css('border', 'solid 1px red');
            }
            if (agama == '') {
                $('#add_agama').css('border', 'solid 1px red');
            }
            if (provinsi == '') {
                $('#add_provinsi').css('border', 'solid 1px red');
            }
            if (kota == '') {
                $('#add_kota').css('border', 'solid 1px red');
            }
            if (kecamatan == '') {
                $('#add_kecamatan').css('border', 'solid 1px red');
            }
            if (desa == '') {
                $('#add_desa').css('border', 'solid 1px red');
            }
            if (flag == '') {
                $('#flag').css('border', 'solid 1px red');
            }
            if (statusPerkawinan == '') {
                $('#add_status_perkawinan').css('border', 'solid 1px red');
            }
            if (pendidikan == '') {
                $('#add_pendidikan').css('border', 'solid 1px red');
            }
            if (password == '') {
                $('#add_password').css('border', 'solid 1px red');
            }
        }

    }

    function removeAll() {
        $('#upload_pasien').attr('disabled', false);
        $('#upload_pasien, #upload_nama_pasien, #upload_no_rm_lama').val('');
        $('#body-rm').html('');
    }

    function cekSearch() {
        var id = $('#id_pasien').val();
        var nama = $('#nama_pasien').val();
        var count = $("#nama_pasien").val().replace(/ /g,'').length;
        var countId = $("#id_pasien").val().replace(/ /g,'').length;
        if(id == '' && nama == '' || count < 3 || countId < 3){
            $('#warning_search').show().fadeOut(5000);
            $('#msg_search').text("Inputan data berikut minimal 3 Karakter...!");
            return false;
        } else {
            $('#waiting_dialog').dialog('open');
            return true;
        }
    }

    function seePassw() {
        var type = $('#add_password').attr('type');
        if (type == 'password') {
            $('#add_password').removeAttr('type');
            $('#add_password').attr('type', 'text');
            $('#icon-password').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
        } else {
            $('#add_password').removeAttr('type');
            $('#add_password').attr('type', 'password');
            $('#icon-password').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>