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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaRadiologiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>

    <script type='text/javascript'>

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
            Periksa Radiologi Pasien
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

                            <div class="col-md-6">
                                <table class="table table-striped" style="margin-top: 20px">
                                    <tr>
                                        <td><b>No RM</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.idPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
                                        <td>
                                            <s:hidden id="id_periksa_lab" name="periksaLab.idPeriksaLab"></s:hidden>
                                            <s:hidden id="no_checkup" name="periksaLab.noCheckup"></s:hidden>
                                            <s:hidden id="no_detail_checkup" name="periksaLab.idDetailCheckup"></s:hidden>
                                            <s:hidden id="id_palayanan" name="periksaLab.idPelayanan"></s:hidden>
                                            <table><s:label name="periksaLab.noCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.idDetailCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td>
                                            <table><s:label id="nik" name="periksaLab.nik"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.namaPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.jenisKelamin"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.tempatTglLahir"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label name="periksaLab.jenisPeriksaPasien"></s:label>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center" class="card card-4 pull-right">
                                    <img border="2" id="img_ktp" src="<s:property value="periksaLab.urlKtp"/>" style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">
                                </div>
                                <table class="table table-striped">
                                    <tr>
                                        <td ><b>Poli</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.namaPelayanan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.desa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Permeriksaan</b></td>
                                        <td>
                                            <table><label class="label label-success"><span id="pemeriksa"></span> </label></table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="form-group" style="display: none">
                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                           closeOnEscape="false"
                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                           buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         toContent();
                                                                                     }
                                                                            }"
                                >
                                    <s:hidden id="close_pos"></s:hidden>
                                    <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                         name="icon_success">
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
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_rad">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_rad"></p>
                        </div>
                    </div>
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Catatan Pemeriksaan</h3>
                    </div>
                    <div class="box-body">
                        <%--<div class="alert alert-danger alert-dismissible" style="display: none" id="warning_radiologi">--%>
                        <%--<h4><i class="icon fa fa-ban"></i> Warning!</h4>--%>
                        <%--<p id="isi_eror"></p>--%>
                        <%--</div>--%>
                        <%--<div class="alert alert-success alert-dismissible" style="display: none" id="info_radiologi">--%>
                            <%--<h4><i class="icon fa fa-info"></i> Info!</h4>--%>
                            <%--Data Berhasil Disimpan!--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-md-4">--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label style="margin-bottom: -2px">Dokter Radiologi</label>--%>
                                        <%--<select class="form-control select2" id="id_dokter" style="width: 100%"--%>
                                                <%--onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()};">--%>
                                            <%--<option value=''>[Select One]</option>--%>
                                        <%--</select>--%>
                                    <%--</div>--%>
                                    <%--<input type="hidden" id="id_radiologi">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-md-7">--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label style="margin-top: 7px">Kesimpulan Periksa</label>--%>
                                        <%--<textarea class="form-control" rows="4" id="kesimpulan"></textarea>--%>
                                    <%--</div>--%>
                                    <%--<div class="form-group">--%>
                                        <%--<button class="btn btn-success" style="margin-top: 15px;" id="save_ket" onclick="saveRadiologi()"><i--%>
                                                <%--class="fa fa-arrow-right"></i> Save--%>
                                        <%--</button>--%>
                                        <%--<button style="display: none; cursor: no-drop; margin-top: 15px;" type="button"--%>
                                                <%--class="btn btn-success" id="load_ket"><i class="fa fa-spinner fa-spin"></i>--%>
                                            <%--Sedang Menyimpan...--%>
                                        <%--</button>--%>
                                        <%--<a href="initForm_radiologi.action" class="btn btn-warning" onclick=""--%>
                                           <%--style="margin-top: 15px;" id="back_ket"><i--%>
                                                <%--class="fa fa-arrow-left"></i> Back--%>
                                        <%--</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                            <table class="table table-bordered table-striped" id="tabel_radiologi">
                                <thead>
                                <tr bgcolor="#90ee90">
                                    <td>Pemeriksaan</td>
                                    <td>Hasil</td>
                                    <td align="center" width="10%">Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_parameter">

                                </tbody>
                            </table>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label style="margin-bottom: -2px">Dokter Radiologi</label>
                                    <select class="form-control select2" id="id_dokter" style="width: 100%"
                                            onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()};">
                                        <option value=''>[Select One]</option>
                                    </select>
                                </div>
                                <input type="hidden" id="id_radiologi">
                            </div>
                            <div class="col-md-4">
                                <a href="initForm_radiologi.action" style="margin-top: 25px" class="btn btn-warning"><i class="fa fa-times"></i> Cancel</a>
                                <button onclick="conSavePeriksaLab()" style="margin-top: 25px" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
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
<div class="mask"></div>


<div class="modal fade" id="modal-edit-parameter">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Edit Parameter Pemeriksaan</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_par">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_par"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Hasil</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="par_hasil" oninput="var warn =$('#war_hasil').is(':visible'); if (warn){$('#cor_hasil').show().fadeOut(3000);$('#war_hasil').hide()}">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_hasil"><i
                                class="fa fa-times"></i> required</p>
                        <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_hasil">
                            <i class="fa fa-check"></i> correct</p>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_par"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_par"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
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
                <h4 class="text-center">Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idDetailCheckup     = $('#no_detail_checkup').val();
    var idPoli              = $('#id_palayanan').val();
    var idPeriksaLab        = $('#id_periksa_lab').val();
    var idPeriksaRadiologi  = "";

    $(document).ready(function () {
        $('#periksa_radiologi').addClass('active');
        listSelectDokter();
        getIdRadiologi();

        $('#img_ktp').on('click', function(e){
            e.preventDefault();
            var src = $('#img_ktp').attr('src');
            if(src != null && src != ""){
                $('.mask').html('<div class="img-box"><img src="'+ src +'"><a class="close">&times;</a>');

                $('.mask').addClass('is-visible fadein').on('animationend', function(){
                    $(this).removeClass('fadein is-visible').addClass('is-visible');
                });

                $('.close').on('click', function(){
                    $(this).parents('.mask').addClass('fadeout').on('animationend', function(){
                        $(this).removeClass('fadeout is-visible')
                    });
                });
            }

        });
    });

    function toContent(){
        var ref = $('#close_pos').val();
        if(ref == 1){
            window.location.href = 'initForm_radiologi.action';
        }
    }

    function listSelectDokter() {
        var option = "";
        PeriksaLabAction.getListDokterTeamByNoDetail(idDetailCheckup, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
                });
            } else {
                option = option;
            }
        });
        $('#id_dokter').html(option);
    }

    function saveRadiologi(id) {

        var idDokter    = $('#id_dokter').val();
        var kesimpulan  = $('#par_hasil').val();

        if (id != '' && kesimpulan != '') {
            $('#save_ket').hide();
            $('#load_ket').show();
            dwr.engine.setAsync(true);
            PeriksaRadiologiAction.saveRadiologi(id, kesimpulan, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-edit-parameter').modal('hide');
                        $('#info_radiologi').show().fadeOut(5000);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                        $('#info_dialog').dialog('open');
                        getIdRadiologi();
                    } else {
                        $('#warning_par').show().fadeOut(5000);
                        $('#msg_par').text(response.getMessage);
                        $('#save_ket').show();
                        $('#load_ket').hide();

                    }
                }
            })
        } else {
            $('#warning_par').show().fadeOut(5000);
            $('#msg_par').text("Silahkan cek kembali data inputan!");
            $('#war_hasil').show();
        }
    }

    function getIdRadiologi() {

        PeriksaRadiologiAction.getIdPemeriksaRadiologi(idPeriksaLab, function (response) {
            data = response;
            if (data.length > 0) {
                var table = "";
                $.each(data, function (i, item) {
                    var hasil = "";
                    if(item.kesimpulan != null && item.kesimpulan != ''){
                        hasil = item.kesimpulan;
                    }
                    table += '<tr>' +
                        '<td>'+item.namaDetailPeriksa+'</td>' +
                        '<td>'+hasil+'</td>' +
                        "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editParameter(\''+item.idPeriksaRadiologi+'\',\''+hasil+'\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + "</td>" +
                        '</tr>';
                });
                $('#body_parameter').html(table);
            }
        });
    }

    function editParameter(id, hasil){
        $('#save_par').show();
        $('#load_par').hide();
        $('#par_hasil').val(hasil);
        $('#save_par').attr('onclick','saveRadiologi(\''+id+'\')');
        $('#modal-edit-parameter').modal('show');
    }

    function conSavePeriksaLab(){
        var idDokter = $('#id_dokter').val();
        var data = $('#tabel_radiologi').tableToJSON();
        var cek  = false;
        $.each(data, function (i, item) {
            var hasil = data[i]["Hasil"];
            if(hasil == ""){
                cek = true;
            }
        });
        if(!cek && idDokter != ''){
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick', 'savePeriksaLab(\''+idDokter+'\')');
        }else{
            $('#warning_rad').show().fadeOut(5000);
            $('#msg_rad').text("Silahkan cek kembali data hasil radiologi dan nama dokter...!");
        }
    }

    function savePeriksaLab(idDokter){
        $('#modal-confirm-dialog').modal('hide');
        var idPeriksaLab = '<s:property value="periksaLab.idPeriksaLab"/>';
        dwr.engine.setAsync(true);
        PeriksaRadiologiAction.saveDokterRadiologi(idPeriksaLab, idDokter, {
            callback:function (res) {
                if(res.status == "success"){
                    $('#save_ket').show();
                    $('#load_ket').hide();
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(1);
                }else{
                    $('#warning_rad').show().fadeOut(5000);
                    $('#msg_rad').text(res.message);
                }
        }});
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>