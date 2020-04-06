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
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/VerifikatorAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#finalisasi_claim').addClass('active');
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
            E-Rekam Medik
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-body">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-print"></i> Cetakan</h3>
                        </div>
                        <div class="box-body">
                            <button class="btn btn-primary"><i class="fa fa-print"></i> General Consent</button>
                            <button class="btn btn-primary"><i class="fa fa-print"></i> Pelepasan Informasi</button>
                            <button class="btn btn-primary"><i class="fa fa-print"></i> Lembar Konsultasi</button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="box-header with-border"></div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-user"></i> Fisioterapi</h3>
                        </div>
                        <div class="box-body">
                            <button class="btn btn-success"><i class="fa fa-edit"></i> Pengkajian</button>
                            <button class="btn btn-success"><i class="fa fa-edit"></i> Kunjungan</button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="box-header with-border"></div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-user"></i> Ringkasan</h3>
                        </div>
                        <div class="box-body">
                            <button class="btn btn-primary"><i class="fa fa-edit"></i> Ringakasan Pulang</button>
                            <button class="btn btn-primary"><i class="fa fa-edit"></i> Rinkasan Masuk Keluar</button>
                            <button class="btn btn-primary"><i class="fa fa-print"></i> Resume Medis</button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="box-header with-border"></div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-user"></i> MPP</h3>
                        </div>
                        <div class="box-body">
                            <button class="btn btn-success"><i class="fa fa-edit"></i> Form-A Evaluasi Awal</button>
                            <button class="btn btn-success"><i class="fa fa-edit"></i> Form-B Catatan Implementasi</button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="box-header with-border"></div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-user"></i> HIV</h3>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="box-header with-border"></div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-user"></i> HD</h3>
                        </div>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Finalisasi & Kirim Online E-Klaim</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_tin">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_tin"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_tin">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_tin2"></p>
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
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
                                    <td><b>Tempat, TGL Lahir</b></td>
                                    <td><span id="det_tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Diagnosa Awal</b></td>
                                    <td><span id="det_diagnosa"></span></td>
                                </tr>
                                <%--<tr>--%>
                                    <%--<td><b>Suku</b></td>--%>
                                    <%--<td><span id="det_suku"></span></td>--%>
                                <%--</tr>--%>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
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
                            </table>
                        </div>

                    </div>
                </div>
                <input type="hidden" id="tin_id_detail_checkup">
                <div class="box-header with-border">
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-line-chart"></i> Status Biaya Tindakan</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-8">
                            <h5>
                                Cover Biaya Bpjs
                                <small class="pull-right" style="margin-top: 7px">Rp. <span id="b_bpjs"></span></small>
                            </h5>
                            <div class="progress">
                                <div id="sts_cover_biaya">
                                </div>
                            </div>
                            <h5>
                                Total Biaya Tindakan
                                <small class="pull-right" style="margin-top: 7px">Rp. <span id="b_tindakan"></span></small>
                            </h5>
                            <div class="progress">
                                <div id="sts_biaya_tindakan">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <h5>Keterangan</h5>
                            <ul style="list-style-type: none">
                                <small><li><i class="fa fa-square" style="color: #337ab7"></i> Total biaya cover Bpjs</li></small>
                                <small><li><i class="fa fa-square" style="color: #5cb85c"></i> Total biaya tindakan < 50% </li></small>
                                <small><li><i class="fa fa-square" style="color: #f0ad4e"></i> Total biaya tindakan > 50% dan < 70%</li></small>
                                <small><li><i class="fa fa-square" style="color: #d9534f"></i> Total biaya tindakan > 70%</li></small>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-medkit"></i> Daftar Tindakan Rawat</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_detail">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td>Nama Tindakan</td>
                            <td>Kategori Bpjs</td>
                            <td align="center">Qty</td>
                            <td align="center">Tarif (Rp.)</td>
                            <td align="center">Total Tarif (Rp.)</td>
                        </tr>
                        </thead>
                        <tbody id="body_tindakan">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_verif"><i class="fa fa-arrow-right"></i> Finalisasi Klaim
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_verif"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-send-klaim">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-check-square"></i> Konfirmasi Kirim Eklaim
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_send">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_send"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_send">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_send2"></p>
                </div>
                <h4 class="text-center">Kirim Final Klaim..!</h4>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_send"><i class="fa fa-arrow-right"></i> Kirim Klaim</button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_send"><i
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

<script type='text/javascript'>

    function formatRupiah(angka) {
        var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
        ribuan = ribuan.join('.').split('').reverse().join('');
        return ribuan;
    }

    function detailTindakan(idCheckup, idDetailCheckup) {

        var table = "";
        var dataTindakan = [];
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
        var idDignosa = "";
        var namaDiagnosa = "";

        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#v_'+idCheckup).attr('src',url).css('width', '30px', 'height', '40px');


        setTimeout(function () {

            var url = '<s:url value="/pages/images/icons8-create-25.png"/>';
            $('#v_'+idCheckup).attr('src',url).css('width', '', 'height', '');

            CheckupAction.listDataPasien(idCheckup, function (response) {
                dataPasien = response;
                if (dataPasien != null) {
                    $.each(dataPasien, function (i, item) {
                        var tanggal = item.tglLahir;
                        var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                        noCheckup = item.noCheckup;
                        nik = item.noKtp;
                        namaPasien = item.nama;

                        if (item.jenisKelamin == "L") {
                            jenisKelamin = "Laki-Laki";
                        } else {
                            jenisKelamin = "Perempuan";
                        }

                        tglLahir = item.tempatLahir + ", " + dateFormat;
                        agama = item.agama;
                        suku = item.suku;
                        alamat = item.jalan;
                        provinsi = item.namaProvinsi;
                        kabupaten = item.namaKota;
                        kecamatan = item.namaKecamatan;
                        desa = item.namaDesa;
                    });
                }
            });

            VerifikatorAction.getListTindakanRawat(idDetailCheckup, function (response) {
                dataTindakan = response;
                console.log(dataTindakan);
                if (dataTindakan != null) {
                    $.each(dataTindakan, function (i, item) {

                        var tindakan = "";
                        var qty = "";
                        var tarif = "";
                        var totalTarif = "";
                        var kategori = "";

                        if (item.namaTindakan != null ) {
                            tindakan = item.namaTindakan;
                        }
                        if (item.qty != null) {
                            qty = item.qty;
                        }
                        if (item.tarif != null) {
                            tarif = item.tarif;
                        }
                        if (item.tarifTotal != null) {
                            totalTarif = item.tarifTotal;
                        }
                        if (item.kategoriTindakanBpjs != null) {
                            kategori = item.kategoriTindakanBpjs;
                        }

                        table += "<tr>" +
                                "<td>" + tindakan + "</td>" +
                                "<td>" + kategori +"</td>" +
                                "<td align='right'>" + qty +"</td>" +
                                "<td align='right'>" + formatRupiah(tarif) +"</td>" +
                                "<td align='right'>" + formatRupiah(totalTarif) +"</td>" +
                                "</tr>";
                    });
                }
            });

            CheckupAction.getDiagnosaRawatPasien(idDetailCheckup, function (response) {
                if(response != null){
                    idDignosa = response.idDignosa;
                    namaDiagnosa = response.keteranganDiagnosa;
                }
            });

            CheckupDetailAction.getStatusBiayaTindakan(idCheckup, function (response) {
                    if(response.tarifBpjs != null && response.tarifTindakan != null){

                        var coverBiaya = response.tarifBpjs;
                        var biayaTindakan = response.tarifTindakan;

                        var persen = "";
                        if(coverBiaya != '' && biayaTindakan){
                            persen = ((parseInt(biayaTindakan)/parseInt(coverBiaya))*100).toFixed(2);
                        }else{
                            persen = 0;
                        }

                        var barClass = "";
                        var barLabel = "";

                        if(parseInt(persen) > 70){
                            barClass = 'progress-bar-danger';
                        }else if (parseInt(persen) > 50){
                            barClass = 'progress-bar-warning';
                        }else{
                            barClass = 'progress-bar-success';
                        }

                        var barBpjs = '<div class="progress-bar progress-bar-primary" style="width: 100%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">'+"100.00%"+'</div>';

                        var barTindakan = '<div class="progress-bar '+barClass+'" style="width: '+persen+'%" role="progressbar" aria-valuenow="'+persen+'" aria-valuemin="0" aria-valuemax="100">'+persen+"%"+ '</div>';

                        if(coverBiaya != ''){
                            $('#sts_cover_biaya').html(barBpjs);
                            $('#b_bpjs').html(formatRupiah(coverBiaya)+" (100%)");
                        }

                        if(biayaTindakan != ''){
                            $('#sts_biaya_tindakan').html(barTindakan);
                            $('#b_tindakan').html(formatRupiah(biayaTindakan)+" ("+persen+"%)");
                        }
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
            $('#det_diagnosa').html(namaDiagnosa);
            $('#body_tindakan').html(table);
            $('#tin_id_detail_checkup').val(idDetailCheckup);
            $('#save_verif').attr('onclick','confirmSaveApproveKlamin(\''+idCheckup+'\')');
            $('#modal-detail-pasien').modal({show:true, backdrop:'static'});
        }, 100);
    }

    function confirmSaveApproveKlamin(idCheckup){
        var data = $('#tabel_tindakan_ts').tableToJSON();
        var cek = false;

        if (cek) {
            $('#msg_tin').text("Silahkan pilih kategori tindakan BPJS terlebih kemudian klik icon di pinggir untuk konfirmasi...!");
            $('#warning_tin').show().fadeOut(5000);
        } else {
            $('#save_con').attr('onclick','saveApproveKlaim(\''+idCheckup+'\')');
            $('#modal-confirm-dialog').modal('show');
        }

    }

    function saveApproveKlaim(idCheckup) {
        $('#modal-confirm-dialog').modal('hide');
        $('#load_verif').show();
        $('#save_verif').hide();
        dwr.engine.setAsync(true);
        VerifikatorAction.finalClaim(idCheckup, {
            callback:function (response) {
                console.log(response);
                if(response.status == "200"){
                    $('#load_verif').hide();
                    $('#save_verif').show();
                    $('#msg_tin2').text(response.message);
                    $('#warning_tin').show().fadeOut(5000);
                    $('#modal-send-klaim').modal({show:true, backdrop:'static'});
                    $('#save_send').attr('onclick','confirmSendKlaim(\''+idCheckup+'\')');
//                    $('#modal-detail-pasien').modal('hide');
//                    $('#info_dialog').dialog('open');
                }else{
                    $('#load_verif').hide();
                    $('#save_verif').show();
                    $('#msg_tin').text(response.message);
                    $('#warning_tin').show().fadeOut(5000);
                }
            }
        });
    }

    function confirmSendKlaim(idCheckup){
        $('#modal-confirm-dialog').modal('show');
        $('#save_con').attr('onclick','sendKlaim(\''+idCheckup+'\')');
    }

    function sendKlaim(idCheckup){
        $('#modal-confirm-dialog').modal('hide');
        $('#load_send').show();
        $('#save_send').hide();
        dwr.engine.setAsync(true);
        VerifikatorAction.sendClaimOnline(idCheckup, {
            callback:function (response) {
                console.log(response);
                if(response.status == "200"){
                    $('#load_verif').hide();
                    $('#save_verif').show();
                    $('#modal-send-klaim').modal('hide');
                    $('#modal-detail-pasien').modal('hide');
                    $('#info_dialog').dialog('open');
                }else{
                    $('#load_send').hide();
                    $('#save_send').show();
                    $('#msg_send').text("Terjadi kesalahan pada saat mengirim klaim, Found Eror: ["+response.message+"]");
                    $('#warning_send').show().fadeOut(5000);
                }
            }
        });
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>