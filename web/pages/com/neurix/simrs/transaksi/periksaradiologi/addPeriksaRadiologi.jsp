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
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.desa"></s:label></table>
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
                    </div>
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Catatan Pemeriksaan</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_radiologi">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="isi_eror"></p>
                        </div>
                        <div class="alert alert-success alert-dismissible" style="display: none" id="info_radiologi">
                            <h4><i class="icon fa fa-info"></i> Info!</h4>
                            Data Berhasil Disimpan!
                        </div>
                        <div class="form-group">
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
                            </div>
                        <div class="row">
                            <div class="col-md-7">
                            <div class="form-group">
                                <label style="margin-top: 7px">Kesimpulan Periksa</label>
                               <textarea class="form-control" rows="4" id="kesimpulan"></textarea>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-success" style="margin-top: 15px;" id="save_ket" onclick="saveRadiologi()"><i
                                        class="fa fa-arrow-right"></i> Save
                                </button>
                                <button style="display: none; cursor: no-drop; margin-top: 15px;" type="button"
                                        class="btn btn-success" id="load_ket"><i class="fa fa-spinner fa-spin"></i>
                                    Sedang Menyimpan...
                                </button>
                                <button class="btn btn-primary" onclick=""
                                        style="margin-top: 15px;" id="print_ket"><i
                                        class="fa fa-print"></i> Print
                                </button>
                                <a href="initForm_radiologi.action" class="btn btn-warning" onclick=""
                                        style="margin-top: 15px;" id="back_ket"><i
                                        class="fa fa-arrow-left"></i> Back
                                </a>
                            </div>
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
<div class="mask"></div>
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

    function saveRadiologi() {

        var idDokter    = $('#id_dokter').val();
        var kesimpulan  = $('#kesimpulan').val();
        if (idPeriksaRadiologi != '' && idDokter != '' && kesimpulan != '') {
            $('#save_ket').hide();
            $('#load_ket').show();
            dwr.engine.setAsync(true);
            PeriksaRadiologiAction.saveRadiologi(idPeriksaRadiologi, idDokter, kesimpulan, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#info_radiologi').show().fadeOut(5000);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    } else {
                        $('#warning_radiologi').show().fadeOut(5000);
                        $('#isi_eror').text('Terjadi kesalahan!');
                        $('#save_ket').show();
                        $('#load_ket').hide();

                    }
                }
            })
        } else {
            $('#warning_radiologi').show().fadeOut(5000);
            $('#isi_eror').text('Silahkan cek kembali data inputan!');
            if (idDokter == '') {
//                $('#lab_kategori').css('border', 'red solid 1px');
            }
            if (kesimpulan == '') {
//                $('#lab_lab').css('border', 'red solid 1px');
            }
        }
    }

    function getIdRadiologi() {

        var table       = "";
        var data        = [];
        var id_periksa  = "";
        var id_dokter   = "";
        var kesimpulan  = "";
        var periksa     = "";

        PeriksaRadiologiAction.getIdPemeriksaRadiologi(idPeriksaLab, function (response) {
            data = response;
            console.log(data);
            if (data != null) {
                $.each(data, function (i, item) {
                    if(item.idPeriksaRadiologi != null){
                        id_periksa = item.idPeriksaRadiologi;
                    }
                    if(item.idDokterRadiologi != null){
                        id_dokter = item.idDokterRadiologi;
                    }
                    if(item.kesimpulan != null){
                        kesimpulan = item.kesimpulan;
                    }
                    if(item.pemeriksaan != null){
                        periksa = item.pemeriksaan;
                    }

                });
            }
        });

        idPeriksaRadiologi = id_periksa;
        $('#id_radiologi').val(id_periksa);
        $('#id_dokter').val(id_dokter).trigger('change');
        $('#kesimpulan').val(kesimpulan);
        $('#pemeriksa').html(periksa);
    }
    function editParameter(id){
        $('#save_par').show();
        $('#load_par').hide();
        $('#par_hasil, #par_ket');
        $('#save_par').attr('onclick','saveEditParameter(\''+id+'\')');
        $('#modal-edit-parameter').modal('show');
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>