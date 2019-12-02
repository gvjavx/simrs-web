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
                                <img border="0" src="<s:url value="/pages/images/ktp-tes.jpg"/>" style="cursor: pointer; margin-bottom: 20px; height: 100px; width: 200px;">
                                <table class="table table-striped">
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
                                        <td>
                                            <s:hidden id="id_periksa_lab" name="periksaLab.idPeriksaLab"></s:hidden>
                                            <s:hidden id="no_checkup" name="periksaLab.noCheckup"></s:hidden>
                                            <s:hidden id="no_detail_checkup" name="periksaLab.idDetailCheckup"></s:hidden>
                                            <s:hidden id="id_palayanan" name="periksaLab.idPelayanan"></s:hidden>
                                            <s:hidden id="id_periksa_radiologi"></s:hidden>
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
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <table class="table table-striped">
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
                                    <tr>
                                        <td><b>Poli</b></td>
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

                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Catatan Pemeriksaan</h3>
                    </div>
                    <div class="box-body">
                        <div class="col-md-7">
                            <div class="form-group">
                                <label style="margin-bottom: -2px">Dokter Radiologi</label>
                                <select class="form-control select2" id="id_dokter" style="width: 100%"
                                        onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()};">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
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
                    <div class="box-header with-border">
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli          = $('#id_palayanan').val();
    var idPeriksaLab    = $('#id_periksa_lab').val();

    $(document).ready(function () {
        $('#periksa_radiologi').addClass('active');
        listSelectDokter();
        getIdRadiologi();
    });

    function listSelectDokter() {
        var option = "";
        CheckupAction.listOfDokter(idPoli, function (response) {
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

        if (idPeriksaLab != '' && idDokter != '' && kesimpulan != '') {
            $('#save_ket').hide();
            $('#load_ket').show();
            dwr.engine.setAsync(true);
            PeriksaRadiologiAction.saveRadiologi(idPeriksaLab, idDokter, kesimpulan, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    } else {

                    }
                }
            })
        } else {
//            $('#warning_lab').show().fadeOut(5000);
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

                });
            }
        });

        $('#id_periksa_radiologi').val(id_periksa);
        $('#id_dokter').val().trigger('change');
        $('#kesimpulan').val(kesimpulan);
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