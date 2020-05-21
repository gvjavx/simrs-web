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
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#pasien').addClass('active');
            searchPasien();
        });

        function searchPasien(){
            var url_string = window.location.href;
            var url = new URL(url_string);
            var idPasien = url.searchParams.get("id_pasien");

            if(idPasien != null){
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
                        <div class="form-group">
                            <s:form id="pasienForm" method="post" namespace="/pasien" action="search_pasien.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Pasien</label>
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
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="pasienForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_pasien.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a href="add_pasien.action" class="btn btn-primary" ><i class="fa fa-plus"></i> Add Pasien</a>
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
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
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
                                        <%--<sj:dialog id="success_dialog" openTopics="showInfoDialog" modal="true"--%>
                                                   <%--resizable="false"--%>
                                                   <%--closeOnEscape="false"--%>
                                                   <%--height="200" width="400" autoOpen="false" title="Infomation Dialog"--%>
                                                   <%--buttons="{--%>
                                                                                <%--'OK':function() {--%>
                                                                                         <%--$('#success_dialog').dialog('close');--%>
                                                                                         <%--pasienSuccess();--%>
                                                                                     <%--}--%>
                                                                            <%--}"--%>
                                        <%-->--%>
                                            <%--<s:hidden id="val_id_pasien"></s:hidden>--%>
                                            <%--<img border="0" src="<s:url value="/pages/images/icon_success.png"/>"--%>
                                                 <%--name="icon_success">--%>
                                            <%--Record has been saved successfully.--%>
                                        <%--</sj:dialog>--%>
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>" alt="Loading..."/></center>
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
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>ID Pasien</td>
                                <td>Nama</td>
                                <td>Jenis Kelamin</td>
                                <td>Tempat, Tgl Lahir</td>
                                <%--<td>Password</td>--%>
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
                                        <img id="t<s:property value="idPasien"/>" class="hvr-grow" onclick="detail('<s:property value="idPasien"/>')" style="cursor: pointer" src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <s:if test='#row.password == null || #row.password == ""'>
                                            <img class="hvr-grow" onclick="setPassword('<s:property value="idPasien"/>')" style="cursor: pointer" src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        </s:if>
                                        <s:else>
                                            <img class="hvr-grow" onclick="setPassword('<s:property value="idPasien"/>')" style="cursor: pointer" src="<s:url value="/pages/images/icons8-create-orange-25.png"/>">
                                        </s:else>
                                        <s:url var="print_card" namespace="/pasien" action="printCard_pasien" escapeAmp="false">
                                            <s:param name="id"><s:property value="idPasien"/></s:param>
                                        </s:url>
                                        <s:a href="%{print_card}" target="_blank">
                                            <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                        </s:a>
                                        <s:if test='#row.disabledFingerData == true'>
                                            <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-fingerprint-accepted-25.png"/>" onclick="registrasiFinger('<s:property value="idPasien"/>')">
                                        </s:if>
                                        <s:else>
                                            <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-fingerprint-scan-25.png"/>" onclick="registrasiFinger('<s:property value="idPasien"/>')">
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekam Medik Pasien <span id="nama_medik"></span></h4>
            </div>
            <div class="modal-body">
                <s:form id="uploadForm" method="post" enctype="multipart/form-data" theme="simple" namespace="/pasien" action="saveUploadRmLama_pasien.action" cssClass="form-horizontal">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">ID Pasien</label>
                        <div class="col-md-7">
                            <div class="input-group">
                                <s:textfield id="upload_pasien" name="pasien.idPasien"
                                             onkeypress="$(this).css('border','');"
                                             cssClass="form-control"/>
                                <div class="input-group-btn">
                                    <a href="#" class="btn btn-primary pull-right" onclick="addInputUpload()"><i class="fa fa-plus"></i> Add Upload</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Pasien</label>
                        <div class="col-md-7">
                            <s:textfield id="upload_nama_pasien" name="pasien.idPasien"
                                         cssStyle="margin-top: 7px"
                                         onkeypress="$(this).css('border','');"
                                         cssClass="form-control" readonly="true"/>
                        </div>
                    </div>
                    <script>
                        function tesPasien(val) {
                            $('#isi').html('<a href="#">Link 1</a><a href="#">Link 2</a><a href="#">Link 3</a>');
                        }
                    </script>
                    <script type="application/javascript">
                        var functions, mapped;
                        $('#upload_pasien').typeahead({
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
                                    var labelItem = "";

                                    if (item.noBpjs != '' && item.noBpjs != null) {
                                        labelItem = item.noKtp + "-" + item.noBpjs + "-" + item.nama;
                                    } else {
                                        labelItem = item.noKtp + "-" + item.nama;
                                    }
                                    mapped[labelItem] = {
                                        id: item.idPasien,
                                        nama: item.nama
                                    };
                                    functions.push(labelItem);
                                });
                                process(functions);

                            },
                            updater: function (item) {
                                var selectedObj = mapped[item];
                                $('#upload_nama_pasien').val(selectedObj.nama);
                                return selectedObj.id;
                            }
                        });
                    </script>
                    <br/>
                    <div id="body-rm">
                    </div>
                </s:form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <sj:submit type="button" cssClass="btn btn-success" formIds="uploadForm" id="save" name="save"
                           onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                    <i class="fa fa-arrow-right"></i>
                    Save
                </sj:submit>
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
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_set_id_pasien">
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
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_set_nama">
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
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_set_password">
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
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_password"><i
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Detail Data Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <img id="img_ktp" style="height: 200px; width: 100%">
                            <table class="table table-striped" style="margin-top: 20px">
                                <tr>
                                    <td><b>ID Pasien</b></td>
                                    <td><span id="an_id_pasien"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="an_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="an_nama"></span></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
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
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes            </button>
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

    function pasienSuccess(){
        var idPasien = $('#val_id_pasien').val();
        $('#id_pasien').val(idPasien);
        document.pasienForm.action = 'search_pasien.action';
        document.pasienForm.submit();
    }

    function detail(pasiendId) {

        var table = "";
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
        var idPasien = "";

        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#t'+pasiendId).attr('src',url).css('width', '30px', 'height', '40px');


        setTimeout(function () {

            var url = '<s:url value="/pages/images/icons8-view-25.png"/>';
            $('#t'+pasiendId).attr('src',url).css('width', '', 'height', '');

            PasienAction.getDataPasien(pasiendId, function (response) {
                console.log(response);
                if (response != null) {

                    nik = response.noKtp;
                    namaPasien = response.nama;

                    if (response.jenisKelamin == "L") {
                        jenisKelamin = "Laki-Laki";
                    } else {
                        jenisKelamin = "Perempuan";
                    }

                    tglLahir = response.tempatLahir + ", " + formateDate(response.tglLahir);
                    agama = response.agama;
                    suku = response.suku;
                    alamat = response.jalan;
                    provinsi = response.provinsi;
                    kabupaten = response.kota;
                    kecamatan = response.kecamatan;
                    desa = response.desa;
                    idPasien = response.idPasien;
                    $('#img_ktp').attr('src',response.urlKtp);
                }
            });

            $('#an_id_pasien').html(idPasien);
            $('#an_nik').html(nik);
            $('#an_nama').html(namaPasien);
            $('#an_jenis_kelamin').html(jenisKelamin);
            $('#an_tgl').html(tglLahir);
            $('#an_agama').html(agama);
            $('#an_suku').html(suku);
            $('#an_alamat').html(alamat);
            $('#an_provinsi').html(provinsi);
            $('#an_kabupaten').html(kabupaten);
            $('#an_kecamatan').html(kecamatan);
            $('#an_desa').html(desa);
            $('#modal-detail').modal({show:true, backdrop:'static'});
        }, 100);
    }

    function formateDate(tanggal){

        var tgl = "";
        if(tanggal != null && tanggal != ''){
            tgl = $.datepicker.formatDate("dd-mm-yy", new Date(tanggal));
        }
        return tgl;
    }

    function setPassword(idPasien){
        if(idPasien != ''){
            $('#modal-password').modal({show:true, backdrop:'static'});
            PasienAction.getDataPasien(idPasien, function (response) {
                if(response.idPasien != null){
                    $('#set_id_pasien').val(response.idPasien);
                    $('#set_nama').val(response.nama);
                    $('#set_password').val(response.password);
                }else{

                }
            });

            $('#save_password').attr('onclick','savePassword(\''+idPasien+'\')');
        }
    }

    function seePassword(){
        var type = $('#set_password').attr('type');
        if(type == 'password'){
            $('#set_password').removeAttr('type');
            $('#set_password').attr('type','text');
            $('#btn_see').removeClass('btn btn-success').addClass('btn btn-warning');
            $('#fa_see').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
        }else{
            $('#set_password').removeAttr('type');
            $('#set_password').attr('type','password');
            $('#btn_see').removeClass('btn btn-warning').addClass('btn btn-success');
            $('#fa_see').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
        }
    }

    function savePassword(idPasien){
        var password = $('#set_password').val();
        if(password != ''){

            $('#save_password').hide();
            $('#load_password').show();
            dwr.engine.setAsync(true);
            PasienAction.setPasswordPasien(idPasien, password, function (response) {
                if(response.status == "success"){
                    $('#modal-password').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#save_password').show();
                    $('#load_password').hide();
                }else{
                    $('#save_password').show();
                    $('#load_password').hide();
                    $('#warning_password').show().fadeOut(5000);
                    $('#msg_password').text(response.message);
                }
            });
        }else{
            $('#warning_password').show().fadeOut(5000);
            $('#msg_password').text('Silahkan cek kembali data inputan..!');
            if(password == ''){
                $('#war_set_password').show();
            }
        }
    }

    $('.tablePasien').on('click', '.item-register-finger', function() {
        var idPasien = $(this).attr('data');
        var location = window.location.hostname;
        console.log(location);
        var url=btoa('http://192.168.43.222:8080/simrs/registerFinger.action?userId='+idPasien);
        console.log(url);
        var href ='finspot:FingerspotReg;'+url;
        window.location.href =href ;
    });

    function registrasiFinger(idPasien){
        if(idPasien != ''){
            console.log(idPasien);
            var location = window.location.href;
            console.log(location);
            var url=btoa('http://192.168.43.222:8080/simrs/registerFinger.action?userId='+idPasien);
            console.log(url);
            var href ='finspot:FingerspotReg;'+url;
            console.log(href);
            window.location.href =href ;
        }
    }

    function showModalUpload(){
        $("#modal-upload").modal("show");
    }

    var n = 0;
    var i = 1;
    function addInputUpload() {

        var str = "";
        str += '<div class="form-group">'+
            '<label class="col-md-3" style="margin-top: 8px">Upload Foto Rekam Medik '+i+'</label>'+
            '<div class="col-md-7">'+
            '<input type="file" name="fileUploadImage" class="form form-control" style="margin-top: 7px">'+
            <%--'<s:file id="upload-img" name="fileUploadImage" cssClass="form form-control"/>'+--%>
            '</div>'+
            '</div>';
        if (n > 0){
            $("#body-rekam-medic-"+n+"").html(str+'<div id="body-rekam-medic-'+i+'"></div>');
        } else {
            $("#body-rm").html('<div id="body-rekam-medic-'+n+'">'+str+'</div><div id="body-rekam-medic-'+i+'"></div>');
        }
        n++;
        i++;
    }

    /*function user_register(user_id, user_name) {
        regStats = 0;
        regCt = -1;
        try
        {
            timer_register.stop();
        }
        catch(err)
        {
            console.log('Registration timer has been init');
        }
        var limit = 4;
        var ct = 1;
        var timeout = 5000;

        timer_register = $.timer(timeout, function() {
            console.log("'"+user_name+"' registration checking...");
            user_checkregister(user_id,$("#user_finger_"+user_id).html());
            if (ct>=limit || regStats==1)
            {
                timer_register.stop();
                console.log("'"+user_name+"' registration checking end");
                if (ct>=limit && regStats==0)
                {
                    alert("'"+user_name+"' registration fail!");
                }
                if (regStats==1)
                {
                    $("#user_finger_"+user_id).html(regCt);
                    alert("'"+user_name+"' registration success!");
                    load('http://localhost:8080/simrs/');
                }
            }
            ct++;
        });
    }

    function user_checkregister(user_id, current) {
        $.ajax({
            url			:	"user.php?action=checkreg&user_id="+user_id+"&current="+current,
            type		:	"GET",
            success		:	function(data)
            {
                try
                {
                    var res = jQuery.parseJSON(data);
                    if (res.result)
                    {
                        regStats = 1;
                        $.each(res, function(key, value){
                            if (key=='current')
                            {
                                regCt = value;
                            }
                        });
                    }
                }
                catch(err)
                {
                    alert(err.message);
                }
            }
        });
    }*/

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>