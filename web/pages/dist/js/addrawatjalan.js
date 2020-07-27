
function getJenisResep(){

    strSelect = "";
    var arBodyJenisResep = [];
    if(jenisPeriksaPasien == "ptpn"){
        arBodyJenisResep.push({"nilai":"bpjs", "label":"BPJS"},{"nilai": "ptpn", "label":"PTPN"});
    } else if (jenisPeriksaPasien == "asuransi"){
        arBodyJenisResep.push({"nilai":"asuransi", "label":"ASURANSI"},{"nilai": "umum", "label":"UMUM"});
    } else if (jenisPeriksaPasien == "bpjs") {
        arBodyJenisResep.push({"nilai": "bpjs", "label": "BPJS"});
    } else {
        arBodyJenisResep.push({"nilai": "umum", "label": "UMUM"});
    }

    var strSelect = "";
    $.each(arBodyJenisResep, function (i, item) {
        strSelect += "<option value='" + item.nilai + "'>" + item.label + "</option>";
    });
    $("#select-jenis-resep").html(strSelect);
}

function hitungBmi(){

    var berat = $('#berat').val();
    var tinggi = $('#tinggi').val();
    var persen = "";
    var bmi = "";
    var barClass = "";
    var barLabel = "";

    if (berat != '' && tinggi != '') {
        var tom = (parseInt(tinggi) * 0.01);
        var tes = (parseFloat(tom)) *  parseFloat(tom);
        bmi = (parseInt(berat) / (tom *  tom)).toFixed(2);
    }

    if (parseInt(bmi) < 18.5) {
        barClass = 'progress-bar-primary';
        persen = 25;
    } else if (parseInt(bmi) >= 18.5 && parseInt(bmi) <= 22.9) {
        barClass = 'progress-bar-success';
        persen = 50;
    } else if (parseInt(bmi) >= 23 && parseInt(bmi) <= 29.9) {
        barClass = 'progress-bar-warning';
        persen = 75;
    } else if (parseInt(bmi) > 30) {
        barClass = 'progress-bar-danger';
        persen = 100;
    }

    var barBmi = '<div class="progress-bar ' + barClass + '" style="width: ' + persen + '%" role="progressbar" aria-valuenow="' + persen + '" aria-valuemin="0" aria-valuemax="100">' + bmi +'</div>';

    $('#bar_bmi').html(barBmi);
}

function printGelangPasien() {
    window.open('printGelangPasien_checkupdetail.action?id=' + noCheckup, '_blank');
}

function hitungCoverBiaya() {
    var jenis = $('#jenis_pasien').val();
    if("asuransi" == jenis){
        CheckupDetailAction.getBiayaAsuransi(idDetailCheckup, function (response) {
            if (response.coverBiaya != null && response.coverBiaya != '') {
                $('#status_asuransi').show();
                if (response.coverBiaya != null) {

                    var coverBiaya = response.coverBiaya;
                    var biayaTindakan = response.tarifTindakan;

                    var persen = "";
                    if (coverBiaya != '' && biayaTindakan) {
                        persen = ((parseInt(biayaTindakan) / parseInt(coverBiaya)) * 100).toFixed(2);
                    } else {
                        persen = 0;
                    }

                    var barClass = "";
                    var barLabel = "";

                    if (parseInt(persen) > 70) {
                        barClass = 'progress-bar-danger';
                    } else if (parseInt(persen) > 50) {
                        barClass = 'progress-bar-warning';
                    } else {
                        barClass = 'progress-bar-success';
                    }

                    var barBpjs = '<div class="progress-bar progress-bar-primary" style="width: 100%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">' + "100%" + '</div>';

                    var barTindakan = '<div class="progress-bar ' + barClass + '" style="width: ' + persen + '%" role="progressbar" aria-valuenow="' + persen + '" aria-valuemin="0" aria-valuemax="100">' + persen + "%" + '</div>';

                    if (coverBiaya != '') {
                        $('#sts_cover_biaya_asuransi').html(barBpjs);
                        $('#b_asuransi').html(formatRupiah(coverBiaya) + " (100%)");
                    }

                    if (biayaTindakan != '') {
                        $('#sts_biaya_tindakan_asuransi').html(barTindakan);
                        $('#b_tindakan_asuransi').html(formatRupiah(biayaTindakan) + " (" + persen + "%)");
                    }
                }
            } else {
                $('#status_asuransi').hide();
            }
        });
    }
}

function hitungStatusBiaya() {
    var jenis = $('#jenis_pasien').val();
    if("bpjs" == jenis || "ptpn" == jenis){
        CheckupDetailAction.getStatusBiayaTindakan(idDetailCheckup, "RWJ", function (response) {
            $('#status_bpjs').show();
            if (response.tarifBpjs != null && response.tarifTindakan != null) {

                var coverBiaya = response.tarifBpjs;
                var biayaTindakan = response.tarifTindakan;
                $('#kode_cbg').text(response.kodeCbg);

                var persen = "";
                if (coverBiaya != '' && biayaTindakan) {
                    persen = ((parseInt(biayaTindakan) / parseInt(coverBiaya)) * 100).toFixed(2);
                } else {
                    persen = 0;
                }

                var barClass = "";
                var barLabel = "";

                if (parseInt(persen) > 70) {
                    barClass = 'progress-bar-danger';
                } else if (parseInt(persen) > 50) {
                    barClass = 'progress-bar-warning';
                } else {
                    barClass = 'progress-bar-success';
                }

                var barBpjs = '<div class="progress-bar progress-bar-primary" style="width: 100%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">' + "100.00%" + '</div>';

                var barTindakan = '<div class="progress-bar ' + barClass + '" style="width: ' + persen + '%" role="progressbar" aria-valuenow="' + persen + '" aria-valuemin="0" aria-valuemax="100">' + persen + "%" + '</div>';

                if (coverBiaya != '') {
                    $('#sts_cover_biaya').html(barBpjs);
                    $('#b_bpjs').html(formatRupiah(coverBiaya) + " (100%)");
                }

                if (biayaTindakan != '') {
                    $('#sts_biaya_tindakan').html(barTindakan);
                    $('#b_tindakan').html(formatRupiah(biayaTindakan) + " (" + persen + "%)");
                }
            }
        });
    }else{
        $('#status_bpjs').hide();
    }
}

function saveAlergi(id) {
    var alergi = $('#alergi').val();
    var jenis = $('#jenis_alergi').val();

    if (noCheckup != '' && alergi != '' && jenis != '') {
        $('#save_alergi').hide();
        $('#load_alergi').show();
        if (id != '') {
            dwr.engine.setAsync(true);
            CheckupAction.saveEditAlergi(alergi, id, jenis, function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    listAlergi();
                    $('#modal-alergi').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(10);
                } else {

                }
            })
        } else {
            dwr.engine.setAsync(true);
            CheckupAction.saveAddAlergi(alergi, noCheckup, jenis, idPasien, function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    listAlergi();
                    $('#modal-alergi').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(10);
                } else {

                }
            })
        }
    } else {
        $('#warning_alergi').show().fadeOut(5000);
        if(alergi == ''){
            $('#war_alergi').show();
        }
        if(jenis == ''){
            $('#war_jenis_alergi').show();
        }
    }
}

function listAlergi() {

    var table = "";
    var noCheckup = $("#no_checkup").val();
    CheckupAction.getListAlergi(noCheckup, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                table += "<tr>" +
                    "<td>" + item.alergi + "</td>" +
                    "<td>" + item.jenis + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editAlergi(\'' + item.idAlergi + '\',\'' + item.alergi + '\', \''+item.jenis+'\')" src="'+contextPath+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>";
            });
        }
    });
    $('#body_alergi').html(table);
}

function editAlergi(id, alergi, jenis) {
    $('#load_alergi').hide();
    $('#modal-alergi').modal('show');
    $('#alergi').val(alergi);
    $('#jenis_alergi').val(jenis);
    $('#save_alergi').attr('onclick', 'saveAlergi(\'' + id + '\')').show();
}

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
    $('#dok_id_dokter').html(option);
}

function selectKeterangan(idKtg) {
    var jenisPasien = $('#jenis_pasien').val();

    if(idKtg != ''){
        if (idKtg == "pindah") {
            $('#pembayaran').hide();
            $("#form-poli").attr('style', 'display:block');
            $("#kamar").attr('style', 'display:none');
            $("#form-selesai").hide();
            $("#form-cekup").hide();
            $('#form-dpjp').hide();

            if(jenisPasien == 'umum'){
                $('#form-asuransi').hide();
                $('#pembayaran').show();
            }else if (jenisPasien == 'asuransi'){
                $('#ri_nama_asuransi').val($('#nama_asuransi').val());
                $('#pembayaran').hide();
                $('#form-asuransi').show();
            }else {
                $('#form-asuransi').hide();
                $('#pembayaran').hide();
            }
        }
        if (idKtg == "rujuk") {
            $("#kamar").attr('style', 'display:block');
            $("#form-poli").attr('style', 'display:none');
            $("#form-selesai").hide();
            $("#form-cekup").hide();
            $('#form-dpjp').show();
            getDokterDpjp();

            if(jenisPasien == 'umum'){
                $('#pembayaran').show();
            }else{
                $('#pembayaran').hide();
            }

            if(jenisPasien == 'asuransi'){
                $('#ri_nama_asuransi').val($('#nama_asuransi').val());
                $('#form-asuransi').show();
            }else{
                $('#form-asuransi').hide();
            }
        }
        if (idKtg == "selesai") {
            $('#pembayaran').hide();
            $("#kamar").attr('style', 'display:none');
            $("#form-poli").attr('style', 'display:none');
            $("#form-selesai").show();
            $("#form-cekup").hide();
            $('#form-asuransi').hide();
            $('#form-dpjp').hide();
        }
        if (idKtg == "lanjut_biaya" || idKtg == "rujuk_rs_lain") {
            $('#pembayaran').hide();
            $("#kamar").attr('style', 'display:none');
            $("#form-poli").attr('style', 'display:none');
            $("#form-selesai").hide();
            $("#form-cekup").hide();
            $('#form-asuransi').hide();
            $('#form-dpjp').hide();
        }
        if(idKtg == "0"){
            $('#pembayaran').hide();
            $("#kamar").hide();
            $("#form-poli").hide();
            $("#form-selesai").hide();
            $("#form-cekup").hide();
            $('#form-asuransi').hide();
            $('#form-dpjp').hide();
        }
    }
}

function listSelectRuangan(id) {
    var idx = id.selectedIndex;
    var idKelas = id.options[idx].value;
    var flag = true;

    var option = "";
    CheckupDetailAction.listRuangan(idKelas, flag, function (response) {
        option = "<option value=''>[Select One]</option>";
        if (response != null) {
            $.each(response, function (i, item) {
                option += "<option value='" + item.idRuangan + "'>" + item.noRuangan + "-" + item.namaRuangan + "</option>";
            });
            $('#kamar_detail').html(option);
        } else {
            option = option;
        }
    });
}

function confirmSaveKeterangan(){
    var idKtg = $("#keterangan").val();
    var noCheckup = $("#no_checkup").val();
    var poli = $("#poli_lain").val();
    var kelas = $("#kelas_kamar").val();
    var kamar = $("#kamar_detail").val();
    var idDokter = $("#list_dokter").val();
    var ket_selesai = $('#ket_selesai').val();
    var tgl_cekup = $('#tgl_cekup').val().split("-").reverse().join("-");
    var ket_cekup = $('#cekup_ket').val();
    var jenisPasien = $('#jenis_pasien').val();
    var metodeBayar = $("#metode_bayar").val();
    var uangMuka = "";
    var uangUmum = $("#uang_muka_val").val();
    var uangAsuransi = $("#rj_cover_biaya_val").val();
    var idPasien = $('#id_pasien').val();
    var namaAsuransi = $('#nama_asuransi').val();
    var noRujukan = $('#no_rujukan').val();
    var tglRujukan = $('#tgl_rujukan').val();
    var suratRujukan = $('#surat_rujukan').val();
    var isLaka = $('#is_laka').val();
    var idDokterDpjp = $('#dokter_dpjp').val();
    var cekPemeriksaan = $('#pemeriksaan_lab').is(':checked');
    var kategoriLab = $('#ckp_kategori').val();
    var unitLab = $('#ckp_unit').val();
    var parameterLab = $('#ckp_parameter').val();
    var dataPemeriksaan = "";

    if(jenisPasien == 'umum'){
        uangMuka = uangUmum;
    }
    if(jenisPasien == 'asuransi'){
        uangMuka = uangAsuransi;
    }

    if (idKtg != '') {

        if (idKtg == "pindah") {
            if (poli != '' && idDokter != '') {
                if(isLaka == "Y"){
                    if(noRujukan != '' && tglRujukan != '' && suratRujukan != ''){
                        $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                        $('#modal-confirm-dialog').modal('show');
                    }else{
                        $('#laka_no_polisi').val(noRujukan);
                        $('#laka_tgl_kejadian').val(noRujukan);
                        $('#laka_surat_rujukan').val(suratRujukan);
                        $('#modal-laka').modal({show:true, backdrop:'static'});
                        var data  = {
                            'id_ktg':idKtg,
                            'poli':poli,
                            'kelas':kelas,
                            'kamar':kamar,
                            'ket_selesai':ket_selesai,
                            'tgl_cekup':tgl_cekup,
                            'ket_cekup':ket_cekup,
                            'jenis':jenisPasien,
                            'id_pasien':idPasien,
                            'metode_bayar':metodeBayar,
                            'uang_muka':uangMuka
                        }
                        var result = JSON.stringify(data);
                        $('#save_laka').attr('onclick','saveDataAsuransi(\''+result+'\')');
                    }
                }else{
                    $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                    $('#modal-confirm-dialog').modal('show');
                }
            } else {
                $('#warning_ket').show().fadeOut(5000);
                if (poli == '') {
                    $('#war_kolom-2').show();
                }
                if (idDokter == '') {
                    $('#war_kolom-3').show();
                }
            }
        }

        if (idKtg == "rujuk") {
            if (kelas != '' && kamar != '') {
                if(isLaka == "Y"){
                    if(noRujukan != '' && tglRujukan != '' && suratRujukan != ''){
                        $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                        $('#modal-confirm-dialog').modal('show');
                    }else{
                        $('#laka_no_polisi').val(noRujukan);
                        $('#laka_tgl_kejadian').val(noRujukan);
                        $('#laka_surat_rujukan').val(suratRujukan);
                        $('#modal-laka').modal({show:true, backdrop:'static'});
                        var data  = {
                            'id_ktg':idKtg,
                            'poli':poli,
                            'kelas':kelas,
                            'kamar':kamar,
                            'ket_selesai':ket_selesai,
                            'tgl_cekup':tgl_cekup,
                            'ket_cekup':ket_cekup,
                            'jenis':jenisPasien,
                            'id_pasien':idPasien,
                            'metode_bayar':metodeBayar,
                            'uang_muka':uangMuka
                        }
                        var result = JSON.stringify(data);
                        $('#save_laka').attr('onclick','saveDataAsuransi(\''+result+'\')');
                    }
                }else{
                    $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                    $('#modal-confirm-dialog').modal('show');
                }
            }else {
                $('#warning_ket').show().fadeOut(5000);
                if (kelas == '') {
                    $('#war_kolom-2').show();
                }
                if (kamar == '') {
                    $('#war_kolom-3').show();
                }
            }
        }

        if (idKtg == "selesai") {

            metodeBayar = $("#jenis_bayar").val();

            if (ket_selesai != '') {
                if(isLaka == "Y"){
                    if(noRujukan != '' && tglRujukan != '' && suratRujukan != ''){
                        $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                        $('#modal-confirm-dialog').modal('show');
                    }else{
                        $('#laka_no_polisi').val(noRujukan);
                        $('#laka_tgl_kejadian').val(noRujukan);
                        $('#laka_surat_rujukan').val(suratRujukan);
                        $('#modal-laka').modal({show:true, backdrop:'static'});
                        var data  = {
                            'id_ktg':idKtg,
                            'poli':poli,
                            'kelas':kelas,
                            'kamar':kamar,
                            'ket_selesai':ket_selesai,
                            'tgl_cekup':tgl_cekup,
                            'ket_cekup':ket_cekup,
                            'jenis':jenisPasien,
                            'id_pasien':idPasien,
                            'metode_bayar':metodeBayar,
                            'uang_muka':uangMuka
                        }
                        var result = JSON.stringify(data);
                        $('#save_laka').attr('onclick','saveDataAsuransi(\''+result+'\')');
                    }
                }else{
                    if(ket_selesai == "Checkup Ulang"){
                        if(tgl_cekup != '' && ket_cekup != ''){
                            if(cekPemeriksaan){
                                if(kategoriLab != '' && unitLab != '' && parameterLab != '' && parameterLab != null){
                                    $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                                    $('#modal-confirm-dialog').modal('show');
                                }else{
                                    $('#warning_ket').show().fadeOut(5000);
                                    $('#war_kolom-2').show();
                                }
                            }else{
                                $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                                $('#modal-confirm-dialog').modal('show');
                            }
                        }else{
                            $('#warning_ket').show().fadeOut(5000);
                            $('#war_kolom-2').show();
                        }
                    }else{
                        $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                        $('#modal-confirm-dialog').modal('show');
                    }
                }
            } else {
                $('#warning_ket').show().fadeOut(5000);
                $('#war_kolom-2').show();
            }
        }

        if(idKtg == "lanjut_biaya" || idKtg == "rujuk_rs_lain"){
            if(isLaka == "Y"){
                if(noRujukan != '' && tglRujukan != '' && suratRujukan != ''){
                    $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                    $('#modal-confirm-dialog').modal('show');
                }else{
                    $('#laka_no_polisi').val(noRujukan);
                    $('#laka_tgl_kejadian').val(noRujukan);
                    $('#laka_surat_rujukan').val(suratRujukan);
                    $('#modal-laka').modal({show:true, backdrop:'static'});
                    var data  = {
                        'id_ktg':idKtg,
                        'poli':poli,
                        'kelas':kelas,
                        'kamar':kamar,
                        'ket_selesai':ket_selesai,
                        'tgl_cekup':tgl_cekup,
                        'ket_cekup':ket_cekup,
                        'jenis':jenisPasien,
                        'id_pasien':idPasien,
                        'metode_bayar':metodeBayar,
                        'uang_muka':uangMuka
                    }
                    var result = JSON.stringify(data);
                    $('#save_laka').attr('onclick','saveDataAsuransi(\''+result+'\')');
                }
            }else{
                $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                $('#modal-confirm-dialog').modal('show');
            }
        }
    } else {
        $('#warning_ket').show().fadeOut(5000);
        $('#war_catatan').show();
    }
}

function saveDataAsuransi(data){
    var noPolisi = $('#laka_no_polisi').val();
    var tglKejadian = $('#laka_tgl_kejadian').val();
    var suratRujukan = $('#laka_surat_rujukan').val();
    var canvas = document.getElementById('temp_surat_rujuk');
    var dataURL = canvas.toDataURL("image/png"),
        dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
    var obj = JSON.parse(data);
    var idKtg = obj["id_ktg"];
    var poli = obj["poli"];
    var kelas = obj["kelas"];
    var kamar = obj["kamar"];
    var ket_selesai = obj["ket_selesai"];
    var tgl_cekup = obj["tgl_cekup"];
    var ket_cekup = obj["ket_cekup"];
    var jenisPasien = obj["jenis"];
    var idPasien = obj["id_pasien"];
    var metodeBayar = obj["metode_bayar"];
    var uangMuka = obj["uang_muka"];

    if(noPolisi != '' && tglKejadian != '' && dataURL != ''){

        $('#save_laka').hide();
        $('#load_laka').show();
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveUpdateDataAsuransi(idDetailCheckup, noPolisi, tglKejadian, dataURL, function (response) {
            if(response.status == "success"){
                $('#modal-laka').modal('hide');
                $('#save_laka').show();
                $('#load_laka').hide();
                $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                $('#modal-confirm-dialog').modal('show');
            }else {
                $('#warning_laka').show().fadeOut(5000);
                $('#msg_laka').text(response.message);
                $('#save_laka').show();
                $('#load_laka').hide();
            }
        });

    }else{
        $('#warning_laka').show().fadeOut(5000);
        $('#msg_laka').text("Silahkan cek kembali inputan anda...!");
        if(noPolisi == ''){
            $('#war_laka_no_polisi').show();
        }
        if(tglKejadian == ''){
            $('#war_laka_tgl_kejadian').show();
        }
        if(suratRujukan == ''){
            $('#war_laka_surat_polisi').show();
        }
    }
}

function saveKeterangan(idKtg, poli, kelas, kamar, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, idPasien, metodeBayar, uangMuka) {
    $('#modal-confirm-dialog').modal('hide');
    var idDokter = $('#tin_id_dokter').val();
    var jenisBayar = $('#jenis_bayar').val();
    var idDokterDpjp = $('#dokter_dpjp').val();
    var idDokDpjp = "";
    var idPelDpjp = "";
    var cekPemeriksaan = $('#pemeriksaan_lab').is(':checked');
    var kategoriLab = $('#ckp_kategori').val();
    var unitLab = $('#ckp_unit').val();
    var parameterLab = $('#ckp_parameter').val();
    var dataPemeriksaan = "";

    if(cekPemeriksaan){
        dataPemeriksaan = {
            'kategori_lab':kategoriLab,
            'unit_lab':unitLab,
            'parameter':parameterLab
        }
    }

    if(idDokterDpjp != '' && idDokterDpjp != null){
        var dat = idDokterDpjp.split("|");
        idDokDpjp = dat[0];
        idPelDpjp = dat[1];
    }

    if(idKtg == "pindah"){
        $('#save_ket').hide();
        $('#load_ket').show();
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, "", "", function (response) {
            if(response.status == "success"){
                $('#modal-laka').modal('hide');
                $('#waiting_dialog').dialog('close');
                $('#info_dialog').dialog('open');
                $('#close_pos').val(6);
                $('#save_ket').show();
                $('#load_ket').hide();
            }else{
                $('#waiting_dialog').dialog('close');
                $('#error_dialog').dialog('open');
                $('#errorMessage').text(response.msg);
                $('#save_ket').show();
                $('#load_ket').hide();
            }
        });
    }
    if(idKtg == "rujuk"){
        $('#save_ket').hide();
        $('#load_ket').show();
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, idPelDpjp, kelas, kamar, idDokDpjp, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, "", "", function (response) {
            if(response.status == "success"){
                $('#modal-laka').modal('hide');
                $('#waiting_dialog').dialog('close');
                $('#info_dialog').dialog('open');
                $('#close_pos').val(6);
                $('#save_ket').show();
                $('#load_ket').hide();
            }else{
                $('#waiting_dialog').dialog('close');
                $('#error_dialog').dialog('open');
                $('#errorMessage').text(response.msg);
                $('#save_ket').show();
                $('#load_ket').hide();
            }
        });
    }
    if(idKtg == "selesai" || idKtg == "lanjut_biaya" || idKtg == "rujuk_rs_lain"){

        var peme = "";
        if(dataPemeriksaan != ""){
            peme = JSON.stringify(dataPemeriksaan);
        }

        $('#save_ket').hide();
        $('#load_ket').show();
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, "", peme, function (response) {
            if(response.status == "success"){
                $('#modal-laka').modal('hide');
                $('#waiting_dialog').dialog('close');
                $('#info_dialog').dialog('open');
                $('#close_pos').val(6);
                $('#save_ket').show();
                $('#load_ket').hide();
            }else{
                $('#waiting_dialog').dialog('close');
                $('#error_dialog').dialog('open');
                $('#errorMessage').text(response.msg);
                $('#save_ket').show();
                $('#load_ket').hide();
            }
        });
    }
}

function listSelectTindakan(idKategori) {
    // var idx = idKategori.selectedIndex;
    // var idKtg = idKategori.options[idx].value;
    var option = "<option value=''>[Select One]</option>";
    if (idKategori != '') {
        CheckupDetailAction.getListComboTindakan(idKategori, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idTindakan + "'>" + item.tindakan + "</option>";
                });
                $('#tin_id_tindakan').html(option);
            } else {
                $('#tin_id_tindakan').html('');
            }
        });
    } else {
        $('#tin_id_tindakan').html('');
    }
}

function listSelectTindakanKategori() {
    var option = "<option value=''>[Select One]</option>";
    CheckupDetailAction.getListComboTindakanKategori(idPoli, function (response) {
        if (response != null) {
            $.each(response, function (i, item) {
                option += "<option value='" + item.idKategoriTindakan + "'>" + item.kategoriTindakan + "</option>";
            });
            $('#tin_id_ketgori_tindakan').html(option);
        } else {
            $('#tin_id_ketgori_tindakan').html('');
        }
    });
}

function toContent() {
    var back = $('#close_pos').val();
    var desti = "";

    if (back == 1) {
        desti = "#pos_dok";
    } else if (back == 2) {
        desti = "#pos_tin";
    } else if (back == 3) {
        desti = "#pos_nosa";
    } else if (back == 4) {
        desti = "#pos_lab";
    } else if (back == 5) {
        desti = "#pos_obat";
    } else if (back == 6) {
        window.location.href = 'initForm_'+urlPage+'.action';
    } else if (back == 9) {
        desti = '#pos_rssep';
    } else if (back == 10) {
        desti = '#pos_alergi';
    } else if (back == 11) {
        desti = '#pos_icd9';
    } else if(back == 12){
        window.location.reload(true);
    }

    $('html, body').animate({
        scrollTop: $(desti).offset().top
    }, 2000);
}

function showModal(select) {

    var id = "";

    if (select == 1) {
        $('#dok_id_dokter').val('').trigger('change');
        $('#load_dokter, #warning_dokter, #war_dok').hide();
        $('#save_dokter').attr('onclick', 'saveDokter(\'' + id + '\')').show();
        $('#modal-dokter').modal({show:true, backdrop:'static'});

    } else if (select == 2) {
        $('#tin_id_ketgori_tindakan, #tin_id_tindakan, #tin_id_perawat').val('').trigger('change');
        $('#tin_qty').val('1');
        $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
        $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
        $('#modal-tindakan').modal({show:true, backdrop:'static'});

    } else if (select == 3) {
        $('#nosa_id_diagnosa, #nosa_ket_diagnosa').val('');
        $('#nosa_jenis_diagnosa').val('').trigger('change');
        $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
        $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
        $('#modal-diagnosa').modal({show:true, backdrop:'static'});

    } else if (select == 4) {
        $('#lab_kategori, #lab_lab').val('').trigger('change');
        $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
        $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
        $('#modal-lab').modal({show:true, backdrop:'static'});
    } else if (select == 5) {
        $('#ob_id_obat').val('').trigger('change');
        $('#jenis_form').show();
        $('#nama_form').show();
        $('#nama_obat_form').hide();
        $('#ob_stok_box').val('');
        $('#ob_stok_lembar').val('');
        $('#ob_stok_biji').val('');
        $('#ob_jenis_satuan').val('').trigger('change');
        $('#ob_jenis_satuan').attr('disabled', false);
        $('#ob_qty').val('');
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
        $('#load_obat, #warning_obat, #war_ob_jenis_obat, #war_obat, #war_qty_obat').hide();
        $('#modal-obat').modal({show:true, backdrop:'static'});
    } else if (select == 7) {
        $('#resep_apotek').val('').trigger('change').attr('disabled', false);
        $('#resep_nama_obat').val('').trigger('change');
        $('#resep_keterangan').val('');
        $('#resep_qty').val('');
        $('#resep_jenis_satuan').val('biji').trigger('change');
        $('#resep_stok_box, #resep_stok_lembar, #resep_stok_biji').val('');
        $('#body_detail').html('');
        $('#desti_apotek').html('');
        $('#save_resep_head').show();
        $('#load_resep_head').hide();
        $('#desti_apotek').html('');
        $('#resep_apotek').attr("onchange", "var warn =$('#war_rep_apotek').is(':visible'); if (warn){$('#cor_rep_apotek').show().fadeOut(3000);$('#war_rep_apotek').hide()}; setObatPoli(this)");
        $('#resep_nama_obat').attr("onchange", "var warn =$('#war_rep_obat').is(':visible'); if (warn){$('#cor_rep_obat').show().fadeOut(3000);$('#war_rep_obat').hide()}; setStokObatApotek(this)");
        $('#body_detail').html('');
        $('#modal-resep-head').modal({show:true, backdrop:'static'});
        getJenisResep();
    } else if (select == 8) {
        $('#alergi').val('');
        $('#load_alergi').hide();
        $('#save_alergi').attr('onclick', 'saveAlergi(\'' + id + '\')').show();
        $('#modal-alergi').modal({show:true, backdrop:'static'});
    } else if (select == 9) {
        $('#id_icd9, #ket_icd9').val('');
        $('#load_icd9').hide();
        $('#save_icd9').attr('onclick', 'saveICD9(\'' + id + '\')').show();
        $('#modal-icd9').modal({show:true, backdrop:'static'});
    }

}


function saveDokter(id) {
    var idDokter = $('#dok_id_dokter').val();

    if (idDetailCheckup != '' && idDokter != '') {
        $('#save_dokter').hide();
        $('#load_dokter').show();
        if (id != '') {
            dwr.engine.setAsync(true);
            TeamDokterAction.editDokter(id, idDokter, idPoli, function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    listDokter();
                    $('#modal-dokter').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(1);
                } else {

                }
            })
        } else {
            dwr.engine.setAsync(true);
            TeamDokterAction.saveDokter(idDetailCheckup, idDokter, idPoli, function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    listDokter();
                    $('#modal-dokter').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(1);
                } else {

                }
            })
        }
    } else {
        $('#warning_dokter').show().fadeOut(5000);
        $('#war_dok').show();
    }
}

function listDokter() {
    var table = "";
    var data = [];
    var dokter = "";
    TeamDokterAction.listDokter(idDetailCheckup, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {
                table += "<tr>" +
                    "<td>" + item.idDokter + "</td>" +
                    "<td>" + item.namaDokter + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDokter(\'' + item.idTeamDokter + '\',\'' + item.idDokter + '\')" src="'+contextPath+'/pages/images/icons8-create-25.png" style="cursor: pointer; ">' + "</td>" +
                    "</tr>";
                dokter = item.idDokter;
            });
        }
    });
    $('#tin_id_dokter').val(dokter);
    $('#body_dokter').html(table);
}

function listDokterKeterangan(idPelayanan) {
    var idx = idPelayanan.selectedIndex;
    var idPoli = idPelayanan.options[idx].value;
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
    $('#list_dokter').html(option);
}

function saveTindakan(id) {

    var idKategori = $('#tin_id_ketgori_tindakan').val();
    var idTindakan = $('#tin_id_tindakan').val();
    var idDokter = $('#tin_id_dokter').val();
    var idPerawat = 1;
    var qty = $('#tin_qty').val();
    var idJenisPeriksa = $('#jenis_pasien').val();

    if (idDetailCheckup != '' && idTindakan != '' && idDokter != '' && idPerawat != '' && qty > 0 && idKategori != '') {

        $('#save_tindakan').hide();
        $('#load_tindakan').show();

        if (id != '') {
            dwr.engine.setAsync(true);
            TindakanRawatAction.editTindakanRawat(id, idDetailCheckup, idTindakan, "RJ", idPerawat, qty, idJenisPeriksa, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listTindakan();
                        hitungStatusBiaya();
                        hitungCoverBiaya();
                        $('#modal-tindakan').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(2);
                    } else {
                        $('#eror_dialog').dialog('open');
                        $('#save_tindakan').show();
                        $('#load_tindakan').hide();
                    }
                }
            });
        } else {
            dwr.engine.setAsync(true);
            TindakanRawatAction.saveTindakanRawat(idDetailCheckup, idTindakan, "RJ", idPerawat, qty, idJenisPeriksa, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listTindakan();
                        hitungStatusBiaya();
                        hitungCoverBiaya();
                        $('#modal-tindakan').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(2);
                    } else {
                        $('#eror_dialog').dialog('open');
                        $('#save_tindakan').show();
                        $('#load_tindakan').hide();
                    }
                }
            });
        }
    } else {
        $('#warning_tindakan').show().fadeOut(5000);

        if (idKategori == '') {
            $('#war_kategori').show();
        }
        if (idTindakan == '') {
            $('#war_tindakan').show();
        }
        if (idPerawat == '') {
            $('#war_perawat').show();
        }
        if (qty <= 0 || qty == '') {
            $('#tin_qty').css('border', 'red solid 1px');
        }
    }
}

function listDokterTindakan() {

    var idPelayanan = $("#id_pelayanan").val();

    var option = "";
    CheckupAction.listOfDokter(idPelayanan, function (response) {
        option = "<option value=''>[Select One]</option>";
        if (response != null) {
            $.each(response, function (i, item) {
                option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
            });
        } else {
            option = option;
        }
    });
    $('#dokter_tindakan').html(option);
}

function listTindakan() {

    var table = "";
    var table2 = "";
    var data = [];
    var trfTtl = 0;
    TindakanRawatAction.listTindakanRawat(idDetailCheckup, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {

                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));
                var tarif = "-";
                var tarifTotal = "-";
                var trfTotal = 0;
                var qtyTotal = 0;
                var perawat = "";
                var btn = '<img border="0" class="hvr-grow" onclick="editTindakan(\'' + item.idTindakanRawat + '\',\'' + item.idTindakan + '\',\'' + item.idKategoriTindakan + '\',\'' + item.idPerawat + '\',\'' + item.qty + '\')" src="'+contextPath+'/pages/images/icons8-create-25.png" style="cursor: pointer;">';

                if (item.tarif != null) {
                    tarif = formatRupiah(item.tarif);
                    trfTotal += item.tarif;
                }
                if (item.tarifTotal != null) {
                    tarifTotal = formatRupiah(item.tarifTotal);
                    trfTtl += item.tarifTotal;
                }
                if (item.qty != null) {
                    qtyTotal += item.qty;
                }
                if (item.idPerawat != null) {
                    perawat = item.idPerawat;
                }

                if("Y" == item.approveFlag){
                    btn = "";
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + item.namaTindakan + "</td>" +
                    "<td align='right'>" + tarif + "</td>" +
                    "<td align='center'>" + item.qty + "</td>" +
                    "<td align='right'>" + tarifTotal + "</td>" +
                    "<td align='center'>" + btn + "</td>" +
                    "</tr>";

                table2 += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + item.namaTindakan + "</td>" +
                    "<td align='center'></td>" +
                    "</tr>";

            });

            if("paket_perusahaan" == jenisPeriksaPasien || "paket_individu" == jenisPeriksaPasien){
                $('#body_tindakan_paket').html(table2);
            }else{
                table = table + "<tr>" +
                    "<td colspan='4'>Total</td>" +
                    "<td align='right'>" + formatRupiah(trfTtl) + "</td>" +
                    "<td></td>" +
                    "</tr>";
                $('#body_tindakan').html(table);
            }
        }
    });

}

function saveDiagnosa(id) {

    var idDiag = $('#nosa_id_diagnosa').val();
    var ketDiagnosa = $('#nosa_ket_diagnosa').val();
    var jenisPasien = $('#jenis_pasien').val();
    var panjang = $('#tbl_diagnosa').tableToJSON();
    var jenisDiagnosa = "";
    if(id != ''){
        jenisDiagnosa = $('#val_jenis_diagnosa').val();
    }else{
        jenisDiagnosa = panjang.length + 1;
    }

    if (idDetailCheckup != '' && idDiag != '' && jenisDiagnosa != '') {

        $('#save_diagnosa').hide();
        $('#load_diagnosa').show();

        if (id != '') {
            dwr.engine.setAsync(true);
            DiagnosaRawatAction.editDiagnosa(id, idDiag, jenisDiagnosa, ketDiagnosa, jenisPasien, idDetailCheckup, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listDiagnosa();
                        hitungStatusBiaya();
                        $('#modal-diagnosa').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(3);
                    } else {
                        $('#warning_diagnosa').show().fadeOut(5000);
                        $('#msg_diagnosa').text(response.msg);
                        $('#save_diagnosa').show();
                        $('#load_diagnosa').hide();
                    }
                }
            })
        } else {
            dwr.engine.setAsync(true);
            DiagnosaRawatAction.saveDiagnosa(idDetailCheckup, idDiag, jenisDiagnosa, ketDiagnosa, jenisPasien, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listDiagnosa();
                        hitungStatusBiaya();
                        $('#modal-diagnosa').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(3);
                    } else {
                        $('#warning_diagnosa').show().fadeOut(5000);
                        $('#msg_diagnosa').text(response.msg);
                        $('#save_diagnosa').show();
                        $('#load_diagnosa').hide();
                    }
                }
            })
        }
    } else {
        $('#warning_diagnosa').show().fadeOut(5000);
        $('#msg_diagnosa').text('Silahkan cek kembali data inputan...!');
        if (idDiag == '') {
            $('#war_diagnosa').show();
        }
        if (jenisDiagnosa == '') {
            $('#war_jenis_diagnosa').show();
        }
    }
}

function listDiagnosa() {

    var table = "";
    var data = [];

    DiagnosaRawatAction.listDiagnosa(idDetailCheckup, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {
                var id = "-";
                var ket = "-";
                var jen = "-";
                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));

                if (item.idDiagnosa != null) {
                    id = item.idDiagnosa;
                }
                if (item.keteranganDiagnosa != null) {
                    ket = item.keteranganDiagnosa;
                }
                if (item.jenisDiagnosa != null) {
                    jen = "Diagnosa Ke "+item.jenisDiagnosa;
                    // if (item.jenisDiagnosa == 0) {
                    //     jen = "Diagnosa Awal";
                    // } else {
                    //     jen = "Diagnosa Akhir";
                    // }
                }
                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + id + "</td>" +
                    "<td>" + ket + "</td>" +
                    "<td>" + jen + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiagnosa(\'' + item.idDiagnosaRawat + '\',\'' + item.idDiagnosa + '\',\'' + item.jenisDiagnosa + '\', \''+item.keteranganDiagnosa+'\')" src="'+contextPath+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_diagnosa').html(table);
}

function listSelectLab(idKategori) {
    var option = "<option value=''>[Select One]</option>";
    if (idKategori != '') {
        LabAction.listLab(idKategori, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idLab + "'>" + item.namaLab + "</option>";
                });
                $('#lab_lab').html(option);
                $('#ckp_unit').html(option);
            } else {
                $('#lab_lab').html(option);
                $('#ckp_unit').html(option);
            }
        });
    } else {
        $('#lab_lab').html(option);
        $('#ckp_unit').html(option);
    }
}

function listSelectParameter(idLab) {
    var option = "";
    if (idLab != '') {
        LabDetailAction.listLabDetail(idLab, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idLabDetail + "'>" + item.namaDetailPeriksa + "</option>";
                });
                $('#lab_parameter').html(option);
                $('#ckp_parameter').html(option);
            } else {
                $('#lab_parameter').html(option);
                $('#ckp_parameter').html(option);
            }
        });
    } else {
        $('#lab_parameter').html(option);
        $('#ckp_parameter').html(option);
    }
}

function saveLab(id) {

    var idKategori = $('#lab_kategori').val();
    var idLab = $('#lab_lab').val();
    var idParameter = $('#lab_parameter').val();

    if (idDetailCheckup != '' && idKategori != '' && idLab != '' && idParameter != null) {

        $('#save_lab').hide();
        $('#load_lab').show();

        if (id != '') {
            dwr.engine.setAsync(true);
            PeriksaLabAction.editOrderLab(id, idLab, idParameter, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listLab();
                        $('#modal-lab').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(4);
                    } else {

                    }
                }
            })
        } else {
            dwr.engine.setAsync(true);
            PeriksaLabAction.saveOrderLab(idDetailCheckup, idLab, idParameter, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listLab();
                        $('#modal-lab').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(4);
                    } else {

                    }
                }
            })
        }
    } else {
        $('#warning_lab').show().fadeOut(5000);
        if (idKategori == '') {
            $('#war_kategori_lab').show();
        }
        if (idLab == '') {
            $('#war_lab').show();
        }
        if (idParameter == '' || idParameter == null) {
            $('#war_parameter').show();
        }
    }
}

function listLab() {

    var table = "";
    var data = [];

    PeriksaLabAction.listOrderLab(idDetailCheckup, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {
                var pemeriksaan = "-";
                var status = "-";
                var lab = "-";
                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));
                var btn = '<img border="0" class="hvr-grow" onclick="editLab(\'' + item.idPeriksaLab + '\',\'' + item.idLab + '\',\'' + item.idKategoriLab + '\',\''+item.kategoriLabName+'\')" src="'+contextPath+'/pages/images/icons8-create-25.png" style="cursor: pointer;">';
                var tipe = "";

                if(item.kategoriLabName == "Radiologi"){
                    tipe = "radiologi";
                }else{
                    tipe = "lab";
                }

                if (item.idLab != null) {
                    pemeriksaan = item.idLab;
                }
                if (item.statusPeriksaName != null) {
                    status = item.statusPeriksaName;
                }
                if (item.labName != null) {
                    lab = item.labName;
                }
                if(item.approveFlag == "Y"){
                    btn = '<a target="_blank" href="printLabRadiologi_checkupdetail.action?id='+idDetailCheckup+'&tipe='+tipe+'&lab='+item.idPeriksaLab+'"><img border="0" class="hvr-grow" src="'+contextPath+'/pages/images/icons8-print-25.png" style="cursor: pointer;"></a>';
                }

                if("paket_perusahaan" == jenisPeriksaPasien || "paket_individu" == jenisPeriksaPasien){
                    table += "<tr>" +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + lab + "</td>" +
                        "<td>" + status + "</td>" +
                        "<td>" + item.kategoriLabName + "</td>" +
                        "<td align='center'></td>" +
                        "</tr>";
                }else{
                    table += "<tr>" +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + lab + "</td>" +
                        "<td>" + status + "</td>" +
                        "<td>" + item.kategoriLabName + "</td>" +
                        "<td align='center'>" + btn + "</td>" +
                        "</tr>";
                }

            });
        }
    });

    $('#body_lab').html(table);
}

function saveObat(idInap) {

    var jenisSatuan = $('#ob_jenis_satuan').val();
    var obat = $('#ob_id_obat').val();
    var qty = $('#ob_qty').val();
    var id = "";
    var nama = "";
    var qtyBox = 0;
    var qtyLembar = 0;
    var qtyBiji = 0;
    var lembarPerBox = 0;
    var bijiPerLembar = 0;
    var stok = 0;

    if (obat != '') {
        if (obat.split('|')[0] != 'null' && obat.split('|')[0] != '') {
            id = obat.split('|')[0];
        }
        if (obat.split('|')[1] != 'null' && obat.split('|')[1] != '') {
            nama = obat.split('|')[1];
        }
        if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
            qtyBox = obat.split('|')[2];
        }
        if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
            qtyLembar = obat.split('|')[3];
        }
        if (obat.split('|')[4] != 'null' && obat.split('|')[4] != '') {
            qtyBiji = obat.split('|')[4];
        }
        if (obat.split('|')[5] != 'null' && obat.split('|')[5] != '') {
            lembarPerBox = obat.split('|')[5];
        }
        if (obat.split('|')[6] != 'null' && obat.split('|')[6] != '') {
            bijiPerLembar = obat.split('|')[6];
        }
    }

    if (idInap != '') {

        var idObat = $('#set_id_obat').val();
        qtyBox = $('#ob_stok_box').val();
        qtyLembar = $('#ob_stok_lembar').val();
        qtyBiji = $('#ob_stok_biji').val();
        lembarPerBox = $('#set_lembar_perbox').val();
        bijiPerLembar = $('#set_biji_perlembar').val();

        if ("box" == jenisSatuan) {
            stok = qtyBox;
        }
        if ("lembar" == jenisSatuan) {
            stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
        }
        if ("biji" == jenisSatuan) {
            stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
        }

        if (parseInt(qty) <= parseInt(stok)) {

            $('#save_obat').hide();
            $('#load_obat').show();

            dwr.engine.setAsync(true);
            ObatInapAction.editObatInap(idInap, idDetailCheckup, idObat, qty, jenisSatuan, function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    listObat();
                    $('#modal-obat').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(5);
                } else {

                }
            })
        } else {
            $('#warning_obat').show().fadeOut(5000);
            $('#obat_error').text("Jumlah obat tidak boleh melebihi stok..!");
        }
    } else {
        if (idDetailCheckup != '' && obat != '' && parseInt(qty) > 0 && jenisSatuan != '') {

            if ("box" == jenisSatuan) {
                stok = qtyBox;
            }
            if ("lembar" == jenisSatuan) {
                stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
            }
            if ("biji" == jenisSatuan) {
                stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
            }

            if (parseInt(qty) <= parseInt(stok)) {

                $('#save_obat').hide();
                $('#load_obat').show();

                dwr.engine.setAsync(true);
                ObatInapAction.saveObatInap(idDetailCheckup, id, qty, jenisSatuan, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listObat();
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                    } else {

                    }
                })
            } else {
                $('#warning_obat').show().fadeOut(5000);
                $('#obat_error').text("Jumlah obat tidak boleh melebihi stok..!");
            }
        } else {
            $('#warning_obat').show().fadeOut(5000);
            $('#obat_error').text("Silahkan cek kembali data inputan..!");
            if (jenisSatuan == '' || jenisSatuan == null) {
                $('#war_ob_jenis_satuan').show();
            }
            if (obat == '' || obat == null) {
                $('#war_obat').show();
            }
            if (qty == '' || qty < 1) {
                $('#war_qty_obat').show();
            }
        }
    }
}

function listObat() {
    var table = "";
    var data = [];

    ObatInapAction.listObatInap(idDetailCheckup, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {
                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));
                var id = "-";
                var obat = "-";
                var qty = "-";
                var jenis = "-";
                if (item.idObat != null) {
                    id = item.idObat;
                }
                if (item.namaObat != null) {
                    obat = item.namaObat;
                }
                if (item.qty != null) {
                    qty = item.qty;
                }
                if (item.jenisSatuan != null) {
                    jenis = item.jenisSatuan;
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + id + "</td>" +
                    "<td>" + obat + "</td>" +
                    "<td align='center'>" + qty + "</td>" +
                    "<td>" + jenis + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editObat(\'' + item.idObatInap + '\',\'' + id + '\',\'' + qty + '\',\'' + jenis + '\',\'' + obat + '\',\'' + item.qtyBox + '\',\'' + item.qtyLembar + '\',\'' + item.qtyBiji + '\',\'' + item.lembarPerBox + '\',\'' + item.bijiPerLembar + '\')" src="'+contextPath+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_obat').html(table);
}

function setStokObat(select) {

    var idx = select.selectedIndex;
    var id = "";
    var nama = "";
    var qtyBox = "";
    var qtyLembar = "";
    var qtyBiji = "";
    var lembarPerBox = "";
    var bijiPerLembar = "";

    if (idx > 0) {

        var obat = select.options[idx].value;

        if (obat.split('|')[0] != 'null' && obat.split('|')[0] != '') {
            id = obat.split('|')[0];
        }
        if (obat.split('|')[1] != 'null' && obat.split('|')[1] != '') {
            nama = obat.split('|')[1];
        }
        if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
            qtyBox = obat.split('|')[2];
        }
        if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
            qtyLembar = obat.split('|')[3];
        }
        if (obat.split('|')[4] != 'null' && obat.split('|')[4] != '') {
            qtyBiji = obat.split('|')[4];
        }

        $('#ob_stok_box').val(qtyBox);
        $('#ob_stok_lembar').val(qtyLembar);
        $('#ob_stok_biji').val(qtyBiji);

    }
}

function editDokter(id, idDokter) {
    $('#load_dokter, #war_dok').hide();
    $('#save_dokter').attr('onclick', 'saveDokter(\'' + id + '\')').show();
    $('#dok_id_dokter').val(idDokter).trigger('change');
    $('#modal-dokter').modal({show:true, backdrop:'static'});
}

function editTindakan(id, idTindakan, idKategori, idPerawat, qty) {
    $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
    $('#tin_id_ketgori_tindakan').val(idKategori).trigger('change');
    $('#tin_id_tindakan').val(idTindakan).trigger('change');
    $('#tin_id_perawat').val(idPerawat).trigger('change');
    $('#tin_qty').val(qty);
    $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
    $('#modal-tindakan').modal({show:true, backdrop:'static'});
}

function editDiagnosa(id, idDiagnosa, jenis, ket) {
    var jenisPasien = $('#jenis_pasien').val();
    $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
    $('#nosa_id_diagnosa').val(idDiagnosa);
    $('#nosa_ket_diagnosa').val(ket);
    $('#val_jenis_diagnosa').val(jenis);
    $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
    $('#modal-diagnosa').modal({show:true, backdrop:'static'});
}

function editLab(id, idLab, idKategoriLab, kategoriName) {
    $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
    $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
    $('#lab_kategori').val(idKategoriLab).trigger('change');
    var idParameter = [];
    PeriksaLabAction.listParameterPemeriksaan(id, kategoriName, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                idParameter.push(item.idLabDetail);
            });
        }
    });
    $('#lab_lab').val(idLab).trigger('change');
    $('#lab_parameter').val(idParameter).trigger('change');
    $('#modal-lab').modal({show:true, backdrop:'static'});
}

function editObat(id, idobat, qty, jenis, namaObat, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerLembar) {
    var qtyBox1 = "";
    var qtyLembar1 = "";
    var qtyBiji1 = "";

    if (qtyBox != 'null') {
        qtyBox1 = qtyBox;
    }

    if (qtyLembar != 'null') {
        qtyLembar1 = qtyLembar;
    }

    if (qtyBiji != 'null') {
        qtyBiji1 = qtyBiji;
    }
    $('#load_obat, #warning_obat, #war_ob_jenis_satuan, #war_obat, #war_qty_obat').hide();
    $('#jenis_form').hide();
    $('#nama_form').hide();
    $('#nama_obat_form').show();
    $('#nama_obat').val(namaObat);
    $('#ob_qty').val(qty);
    $('#ob_stok_box').val(qtyBox1);
    $('#ob_stok_lembar').val(qtyLembar1);
    $('#ob_stok_biji').val(qtyBiji1);
    $('#set_id_obat').val(idobat);
    $('#set_lembar_perbox').val(lembarPerBox);
    $('#set_biji_perlembar').val(bijiPerLembar);
    $('#ob_jenis_satuan').val(jenis).trigger('change').attr('disabled', true);
    $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
    $('#modal-obat').modal({show:true, backdrop:'static'});
}

function listSelectObatEdit(select) {
    var idx = select.selectedIndex;
    var idJenis = select.options[idx].value;
    var option = "<option value=''>[Select One]</option>";
    if (idJenis != '') {
        ObatAction.listObatByJenis(idJenis, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idObat + "'>" + item.namaObat + "</option>";
                });
            } else {
                option = option;
            }
        });
    } else {
        option = option;
    }

    $('#ob_id_obat').html(option);
}

function listSelectObat(select) {
    var idx = select.selectedIndex;
    var idJenis = select.options[idx].value;
    var option = "<option value=''>[Select One]</option>";
    if (idJenis != '') {
        ObatAction.listObat(idJenis, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idObat + "'>" + item.namaObat + "</option>";
                });
                $('#ob_id_obat').html(option);
                $('#resep_nama_obat').html(option);
            } else {
                option = option;
            }
        });
    } else {
        option = option;
    }
}

function showFormCekup(idKet) {
    if (idKet == "Checkup Ulang") {
        $('#form-cekup').show();
    } else {
        $('#form-cekup').hide();
    }
}

function addObatToList() {

    var apotek = $('#resep_apotek').val();
    var obat = $('#resep_nama_obat').val();
    var qty = $('#resep_qty').val();
    var jenisSatuan = $('#resep_jenis_satuan').val();
    var stokBox = $('#resep_stok_box').val();
    var stokLembar = $('#resep_stok_lembar').val();
    var stokBiji = $('#resep_stok_biji').val();
    var cek = false;
    var data = $('#tabel_rese_detail').tableToJSON();
    var id = "";
    var nama = "";
    var qtyBox = 0;
    var qtyLembar = 0;
    var qtyBiji = 0;
    var lembarPerBox = 0;
    var bijiPerLembar = 0;

    var listObat = $("[name=cek_waktu]:checked");
    var pemberian = $("#resep_waktu").val();
    var jenisResep = $("#jenis_resep").val();
    var flagKronis = $("#val-kronis").val();
    var hariKronis = "";
    var harga = "";

    if (flagKronis == "Y"){
        hariKronis = $("#hari-kronis").val();
    }

    var i = 0;
    var waktu = [];
    $.each(listObat, function (idx, item) {
        if(item.checked){
            waktu.push($(this).val());
            i = i+1;
        }
    });

    var ket = pemberian+" Makan. "+i+"x1. "+waktu.join(", ");

    if (obat != '' && ket != '' && qty != '' && apotek != '' && jenisSatuan != '' && waktu.length > 0) {

        var idPelayanan = apotek.split('|')[0];
        var namaPelayanan = apotek.split('|')[1];

        if (obat.split('|')[0] != 'null' && obat.split('|')[0] != '') {
            id = obat.split('|')[0];
        }
        if (obat.split('|')[1] != 'null' && obat.split('|')[1] != '') {
            nama = obat.split('|')[1];
        }
        if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
            qtyBox = obat.split('|')[2];
        }
        if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
            qtyLembar = obat.split('|')[3];
        }
        if (obat.split('|')[4] != 'null' && obat.split('|')[4] != '') {
            qtyBiji = obat.split('|')[4];
        }
        if (obat.split('|')[5] != 'null' && obat.split('|')[5] != '') {
            lembarPerBox = obat.split('|')[5];
        }
        if (obat.split('|')[6] != 'null' && obat.split('|')[6] != '') {
            bijiPerLembar = obat.split('|')[6];
        }
        if (obat.split('|')[8] != 'null' && obat.split('|')[8] != '') {
            harga = obat.split('|')[8];
        }


        var stok = 0;

        if ("box" == jenisSatuan) {
            stok = qtyBox;
        }
        if ("lembar" == jenisSatuan) {
            stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
        }
        if ("biji" == jenisSatuan) {
            stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
        }

        if (parseInt(qty) <= parseInt(stok)) {

            $.each(data, function (i, item) {
                if (item.ID == id) {
                    cek = true;
                }
            });

            if (cek) {
                $('#warning_data_exits').show().fadeOut(5000);
            } else {
                var totalHarga = parseInt(qty) * parseInt(harga);
                $('#resep_apotek').attr('disabled', true);
                $('#desti_apotek').html(namaPelayanan);
                var row = '<tr id=' + id + '>' +
                    '<td>' + id + '</td>' +
                    '<td>' + nama + '</td>' +
                    '<td align="center">' + qty + '</td>' +
                    '<td align="center">' + jenisSatuan + '</td>' +
                    '<td>' + ket + '</td>' +
                    '<td>' + jenisResep + '</td>' +
                    '<td>' + labelKronis(flagKronis) + '</td>' +
                    '<td aling="center">' + hariKronis + '</td>' +
                    '<td aling="center">' + formatRupiah(totalHarga) + '</td>' +
                    '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\',\''+totalHarga+'\')" class="hvr-grow" src="'+contextPath+'/pages/images/delete-flat.png" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                    '</tr>';
                $('#body_detail').append(row);
                var total = $('#total_harga_obat').val();
                var tot = 0;
                if(total != ""){
                    tot = total.replace(/[.]/g, '');
                }
                var jumlah = parseInt(totalHarga) + parseInt(tot);
                $('#total_harga_obat').val(formatRupiah(jumlah));
            }
        } else {
            $('#warning_resep_head').show().fadeOut(5000);
            $('#msg_resep').text('Qty tidak boleh melebihi stok obat..!');
            $('#modal-resep-head').scrollTop(0);
        }

    } else {
        if (jenisSatuan == '' || jenisSatuan == null) {
            $('#war_rep_jenis_satuan').show();
        }
        if (apotek == '' || apotek == null) {
            $('#war_rep_apotek').show();
        }
        if (obat == '' || obat == null) {
            $('#war_rep_obat').show();
        }
        if (qty == '' || qty <= 0) {
            $('#war_rep_qty').show();
        }
        if (waktu.length == 0) {
            $('#war_rep_cek_waktu').show();
        }
        $('#warning_resep_head').show().fadeOut(5000);
        $('#msg_resep').text('Silahkan cek kembali data inputan!');
        $('#modal-resep-head').scrollTop(0);
    }
}

function delRowObat(id, harga) {
    $('#' + id).remove();
    var total = $('#total_harga_obat').val();
    var tot = 0;
    if(total != ""){
        tot = total.replace(/[.]/g, '');
    }
    var jumlah = parseInt(tot) - parseInt(harga);
    $('#total_harga_obat').val(formatRupiah(jumlah));
}

function saveResepObatTtd() {

    var idDokter = $('#tin_id_dokter').val();
    var data = $('#tabel_rese_detail').tableToJSON();
    var stringData = JSON.stringify(data);
    var idPelayanan = $('#resep_apotek').val();
    var apotek = $('#resep_apotek').val();

    if (stringData != '[]') {
        $('#modal-ttd').modal({show:true, backdrop:'static'});
    } else {
        $('#warning_resep_head').show().fadeOut(5000);
        $('#msg_resep').text("Silahkan cek kembali data inputan anda..!");
        $('#modal-resep-head').scrollTop(0);
    }
}

function clearConvas(){
    var canvas = document.getElementById('ttd_canvas');
    const context = canvas.getContext('2d');
    context.clearRect(0, 0, canvas.width, canvas.height);
}

function saveResepObat() {
    $('#modal-ttd').modal('hide');
    var idDokter = $('#tin_id_dokter').val();
    var jenisResep = $('#select-jenis-resep').val();
    var data = $('#tabel_rese_detail').tableToJSON();
    var stringData = JSON.stringify(data);
    var idPelayanan = $('#resep_apotek').val();
    var apotek = $('#resep_apotek').val();
    var canvas = document.getElementById('ttd_canvas');
    var dataURL = canvas.toDataURL("image/png"),
        dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
    var ttd  = isBlank(canvas);
    if (stringData != '[]' && !ttd) {
        var idPelayanan = apotek.split('|')[0];
        var namaPelayanan = apotek.split('|')[1];
        $('#save_resep_head').hide();
        $('#load_resep_head').show();
        dwr.engine.setAsync(true);
        PermintaanResepAction.saveResepPasien(idDetailCheckup, idPoli, idDokter, idPasien, stringData, idPelayanan, dataURL, jenisResep, {
            callback: function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(9);
                    $('#save_resep_head').show();
                    $('#load_resep_head').hide();
                    $('#modal-resep-head').modal('hide');
                    listResepPasien();
                } else {
                    $('#warning_resep_head').show().fadeOut(5000);
                    $('#msg_resep').text("Silahkan cek kembali data inputan anda..!");
                    $('#save_resep_head').show();
                    $('#load_resep_head').hide();
                }
            }
        });
    } else {
        $('#warning_resep_head').show().fadeOut(5000);
        $('#msg_resep').text("Silahkan cek kembali data inputan anda..!");
        $('#modal-resep-head').scrollTop(0);
    }
}

function isBlank(canvas){
    const blank = document.createElement("canvas");
    blank.width = canvas.width;
    blank.height = canvas.height;
    return canvas.toDataURL() === blank.toDataURL();
}

function listResepPasien() {

    var table = "";
    var data = [];

    PermintaanResepAction.listResepPasien(idDetailCheckup, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {
                var idResep = "";
                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));

                if (item.idPermintaanResep != null) {
                    idResep = item.idPermintaanResep;
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + idResep + "</td>" +
                    "<td align='center'>" +
            '<img onclick="printResep(\'' + idResep + '\')" class="hvr-grow" src="'+contextPath+'/pages/images/icons8-print-25.png" style="cursor: pointer;">' +
            "</td>" +
            "</tr>"
            });
        }
    });

    $('#body_resep').html(table);
}

function printResep(id) {
    window.open('printResepPasien_'+urlPage+'.action?id=' + idDetailCheckup + '&idResep=' + id, '_blank');
}

function detailResep(id) {
    $('#modal-resep-detail').modal({show:true, backdrop:'static'});
    listDetailResepPasien(id);
}

function listDetailResepPasien(idApprovalObat) {

    var table = "";
    var data = [];

    PermintaanResepAction.listDetail(idApprovalObat, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {

                var qty = "";
                var namaObat = "";
                var ket = "";
                var idObat = "";

                if (item.idObat != null) {
                    idObat = item.idObat;
                }

                if (item.qty != null) {
                    qty = item.qty;
                }

                if (item.namaObat != null) {
                    namaObat = item.namaObat;
                }

                if (item.keterangan != null) {
                    ket = item.keterangan;
                }

                table += "<tr>" +
                    "<td>" + '<span id=obat' + idObat + '>' + namaObat + '</span><input style="display:none; width: 120px;" type="text" id=newObat' + idObat + ' class="form-control"><input type="hidden" id=idObat' + idObat + '>' + "</td>" +
                    "<td>" + '<span id=qty' + idObat + '>' + qty + '</span>' + '<input type="hidden" id=newId' + idObat + ' value=' + idObat + '>' +
                    '<input style="display:none; width: 80px" type="number" id=newQty' + idObat + ' class="form-control">' + "</td>" +
                    "<td>" + '<span id=ket' + idObat + '>' + ket + '</span>' +
                    '<select class="form-control" id=newKet' + idObat + ' style="display:none"' +
                    '<option value="">[Select One]</option>' +
                    '<option value="2 x 1 /Hari">2 x 1 /Hari</option>' +
                    '<option value="3 x 1 /Hari">3 x 1 /Hari</option>' +
                    '</select>' + "</td>" +
                    "<td align='center'>" + '<img border="0" id=' + idObat + ' class="hvr-grow" onclick="editObatResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + qty + '\',\'' + ket + '\',\'' + namaObat + '\')" src="'+contextPath+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' +
                    '<img border="0" id=save' + idObat + ' class="hvr-grow" onclick="saveDetailResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + item.idApprovalObat + '\')" src="'+contextPath+'/pages/images/icons8-save-25.png" style="cursor: pointer; display: none">' + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_detail_rep').html(table);
}

function editObatResep(id, idObat, qty, ket, namaObat) {

    if ($('#' + idObat).attr('src') == '/simrs/pages/images/icons8-create-25.png') {
        var url = contextPath+'/pages/images/cnacel-flat.png"/>';
        $('#' + idObat).attr('src', url);
        $('#obat' + idObat).hide();
        $('#qty' + idObat).hide();
        $('#ket' + idObat).hide();

        $('#newObat' + idObat).show().val(namaObat);
        $('#newQty' + idObat).show().val(qty);
        $('#newKet' + idObat).show().val(ket);
        $('#save' + idObat).show();

    } else {
        var url = contextPath+'/pages/images/icons8-create-25.png"/>';
        $('#' + idObat).attr('src', url);
        $('#obat' + idObat).show();
        $('#qty' + idObat).show();
        $('#ket' + idObat).show();

        $('#newObat' + idObat).hide();
        $('#newQty' + idObat).hide();
        $('#newKet' + idObat).hide();
        $('#save' + idObat).hide();
    }
}

function saveDetailResep(id, idObat, idApp) {

    var obat = $('#newId' + idObat).val();
    var qty = $('#newQty' + idObat).val();
    var ket = $('#newKet' + idObat).val();

    if (obat != '' && qty != '' && ket != '') {
        $('#save_resep_head').hide();
        $('#load_resep_head').show();
        dwr.engine.setAsync(true);
        PermintaanResepAction.saveEditDetail(id, obat, qty, ket, {
            callback: function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    $('#success_detail').show().fadeOut(5000);
                    listDetailResepPasien(idApp);
                } else {
                    $('#warning_resep_head').show().fadeOut(5000);
                    $('#save_resep_head').show();
                    $('#load_resep_head').hide();
                }
            }
        });
    } else {
        $('#warning_detail').show().fadeOut(5000);
    }
}

function setStokObatApotek(select) {

    var id = "";
    var nama = "";
    var qtyBox = "";
    var qtyLembar = "";
    var qtyBiji = "";
    var lembarPerBox = "";
    var bijiPerLembar = "";
    var flagKronis = "";
    var idx = select.selectedIndex;

    if (idx > 0) {
        var idObat = select.options[idx].value;
        if (idObat != null && idObat != '') {

            if (idObat.split('|')[0] != 'null' && idObat.split('|')[0] != '') {
                id = idObat.split('|')[0];
            }
            if (idObat.split('|')[1] != 'null' && idObat.split('|')[1] != '') {
                nama = idObat.split('|')[1];
            }
            if (idObat.split('|')[2] != 'null' && idObat.split('|')[2] != '') {
                qtyBox = idObat.split('|')[2];
            }
            if (idObat.split('|')[3] != 'null' && idObat.split('|')[3] != '') {
                qtyLembar = idObat.split('|')[3];
            }
            if (idObat.split('|')[4] != 'null' && idObat.split('|')[4] != '') {
                qtyBiji = idObat.split('|')[4];
            }
            if (idObat.split('|')[5] != 'null' && idObat.split('|')[5] != '') {
                lembarPerBox = idObat.split('|')[5];
            }
            if (idObat.split('|')[6] != 'null' && idObat.split('|')[6] != '') {
                bijiPerLembar = idObat.split('|')[6];
            }
            if (idObat.split('|')[7] != 'null' && idObat.split('|')[7] != '') {
                flagKronis = idObat.split('|')[7];
            }

            var total = parseInt(qtyBiji)+(parseInt(qtyBox)*parseInt(lembarPerBox))+(parseInt(qtyLembar)*parseInt(bijiPerLembar));

            if (flagKronis == "Y"){
                labelKronis(flagKronis);
                $("#form-hari").show();
            } else {
                labelKronis(flagKronis);
                $("#form-hari").hide();
            }

            $('#resep_stok_biji').val(total);
            $("#h-qty-default").val(bijiPerLembar);

            $('#resep_keterangan').val('');
            $('#resep_qty').val(bijiPerLembar);
            $('#resep_jenis_satuan').val('biji').trigger('change');
        }
    }
}

function savePenunjangPasien() {

    var tinggi = $('#tinggi').val();
    var berat = $('#berat').val();

    if (noCheckup != '' && tinggi != '' && berat != '') {
        $('#save_penunjang').hide();
        $('#load_penunjang').show();
        dwr.engine.setAsync(true);
        CheckupAction.savePenunjangPasien(tinggi, berat, noCheckup, function (response) {
            if (response == "success") {
                dwr.engine.setAsync(false);
                $('#success_penunjang').show().fadeOut(5000);
                $('#save_penunjang').show();
                $('#load_penunjang').hide();
                hitungBmi();
            } else {
                $('#save_penunjang').show();
                $('#load_penunjang').hide();
            }
        })
    } else {
        $('#warning_penunjang').show().fadeOut(5000);
    }
}

function resetAll() {
    $('#resep_apotek').val('').trigger('change').attr('disabled', false);
    $('#resep_nama_obat, #resep_jenis_satuan').val('').trigger('change');
    $('#resep_keterangan').val('');
    $('#resep_qty').val('');
    $('#resep_stok_box, #resep_stok_lembar, #resep_stok_biji').val('');
    $('#body_detail').html('');
    $('#desti_apotek').html('');
}

function setObatPoli(select) {
    var idx = select.selectedIndex;
    var poli = select.options[idx].value;
    var idPel = poli.split('|')[0];
    var namePel = poli.split('|')[1];
    var option = "<option value=''>[Select One]</option>";
    var jenisPasien = $('#jenis_pasien').val();

    if (poli != '') {
        ObatPoliAction.getSelectOptionObatByPoli(idPel, jenisPasien, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idObat + "|" + item.namaObat + "|" + item.qtyBox + "|" + item.qtyLembar + "|" + item.qtyBiji + "|" + item.lembarPerBox + "|" + item.bijiPerLembar + "|" + item.flagKronis + "|" + item.harga + "'>" + item.namaObat + "</option>";
                });
                $('#resep_nama_obat').html(option);
            }
        });
    } else {
        option = "";
    }
}

function labelKronis(flag){
    if (flag == "Y"){
        $("#label-kronis").show();
        $("#val-kronis").val(flag);
        return 'Obat Kronis';
    } else {
        $("#label-kronis").hide();
        $("#val-kronis").val("");
        return "";
    }
}

function confirmSaveAllTindakan(){
    $('#modal-confirm-dialog').modal({show:true, backdrop:'static'});
    $('#save_con').attr('onclick','saveAllTindakan()');
}

function saveAllTindakan(){
    $('#modal-confirm-dialog').modal('hide');
    $('#save_all').hide();
    $('#load_all').show();
    var idJenisPeriksa = $('#jenis_pasien').val();
    dwr.engine.setAsync(true);
    CheckupDetailAction.saveApproveAllTindakanRawatJalan(idDetailCheckup, idJenisPeriksa, {
        callback : function (response) {
            if(response.status == "success"){
                $('#success_all').show().fadeOut(5000);
                $('#msg_all_suc').text(response.message);
                $('#save_all').show();
                $('#load_all').hide();
                listTindakan();
            }else{
                $('#warning_all').show().fadeOut(5000);
                $('#msg_all_war').text(response.message);
                $('#save_all').show();
                $('#load_all').hide();
            }
        }});
}

function defaultValByJenisSatuan(name) {
    var nilai = "1";
    if (name == "biji"){
        nilai = $("#h-qty-default").val();
    }
    $("#resep_qty").val(nilai);

}

function saveAnamnese(){
    var anamnesa = $('#fisik_anamnesa').val();
    var tensi = $('#fisik_tensi').val().replace("_","");
    var suhu = $('#fisik_suhu').val();
    var nadi = $('#fisik_nadi').val();
    var rr = $('#fisik_rr').val();
    if(anamnesa && tensi && suhu && nadi && rr != ''){
        $('#save_fisik').hide();
        $('#load_fisik').show();
        CheckupAction.saveAnamnese(anamnesa, noCheckup, idDetailCheckup, tensi, suhu, nadi, rr, {callback: function (response) {
            if (response.status == "success") {
                $('#suc_anamnese').show().fadeOut(5000);
                $('#save_fisik').show();
                $('#load_fisik').hide();
                $('#msg_suc').text("Berhasil menyimpan data pemeriksaan fisik...");
            }else{
                $('#war_anamnese').show().fadeOut(5000);
                $('#save_fisik').show();
                $('#load_fisik').hide();
                $('#msg_war').text("Terjadi kesalahan saat penyimpanan data...!");
            }
        }});
    }else{
        $('#war_anamnese').show().fadeOut(5000);
        $('#msg_war').text("Silahkan cek kembali data inputan anda...!");
    }
}

function saveICD9(id) {

    var idIcd9 = $('#id_icd9').val();
    var ketIcd9 = $('#ket_icd9').val();
    var idIcd9Edit = $('#id_edit_icd9').val();
    var jenisPasien = $('#jenis_pasien').val();
    var data = "";

    if (idDetailCheckup != '' && idIcd9 != '') {

        data = {
            'id_detail_checkup' :idDetailCheckup,
            'jenis_pasien': jenisPasien,
            'id_icd9' : idIcd9,
            'nama_icd9' : ketIcd9,
            'id_tindakan_rawat_icd9': id,
            'id_edit_icd9': idIcd9Edit
        }

        $('#save_icd9').hide();
        $('#load_icd9').show();
        var result = JSON.stringify(data);

        if (id != '') {
            dwr.engine.setAsync(true);
            TindakanRawatICD9Action.edit(result, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listICD9();
                        hitungStatusBiaya();
                        $('#modal-icd9').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(11);
                    } else {
                        $('#save_icd9').show();
                        $('#load_icd9').hide();
                        $('#warning_icd9').show().fadeOut(5000);
                        $('#msg_icd9').text(response.msg);
                    }
                }
            })
        } else {
            dwr.engine.setAsync(true);
            TindakanRawatICD9Action.save(result, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listICD9();
                        hitungStatusBiaya();
                        $('#modal-icd9').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(11);
                    } else {
                        $('#save_icd9').show();
                        $('#load_icd9').hide();
                        $('#warning_icd9').show().fadeOut(5000);
                        $('#msg_icd9').text(response.msg);
                    }
                }
            })
        }
    } else {
        $('#warning_icd9').show().fadeOut(5000);
        $('#msg_icd9').text("Silahkan cek kembali inputan anda...!");
        if (id == '') {
            $('#war_id_icd9').show();
        }
    }
}

function listICD9() {

    var table = "";
    var data = [];

    TindakanRawatICD9Action.getListICD9(idDetailCheckup, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {
                var id = "-";
                var ket = "-";
                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));

                if (item.idIcd9 != null) {
                    id = item.idIcd9;
                }
                if (item.namaIcd9 != null) {
                    ket = item.namaIcd9;
                }
                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + id + "</td>" +
                    "<td>" + ket + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editICD9(\'' + item.idTindakanRawatIcd9 + '\',\'' + item.idIcd9 + '\',\'' + item.namaIcd9 + '\')" src="'+contextPath+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
            });
            $('#body_icd9').html(table);
        }
    });
}

function editICD9(id, idIcd9, ketIcd9) {
    $('#load_icd9, #warning_icd9, #war_id_icd9').hide();
    $('#id_icd9').val(idIcd9);
    $('#id_edit_icd9').val(idIcd9);
    $('#ket_icd9').val(ketIcd9);
    $('#save_icd9').attr('onclick', 'saveICD9(\'' + id + '\')').show();
    $('#modal-icd9').modal({show:true, backdrop:'static'});
}

function searchDiagnosa(id){
    var menus, mapped;
    $('#'+id).typeahead({
        minLength: 3,
        source: function (query, process) {
            menus = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            CheckupAction.getICD10(query, function (listdata) {
                data = listdata;
            });

            $.each(data, function (i, item) {
                var labelItem = item.idDiagnosa +'-'+item.descOfDiagnosa;
                mapped[labelItem] = {
                    id: item.idDiagnosa,
                    label: labelItem,
                    name: item.descOfDiagnosa
                };
                menus.push(labelItem);
            });

            process(menus);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            // insert to textarea diagnosa_ket
            $("#nosa_ket_diagnosa").val(selectedObj.name);
            return selectedObj.id;
        }
    });
}

function searchICD9(id){
    var menus, mapped;
    $('#'+id).typeahead({
        minLength: 3,
        source: function (query, process) {
            menus = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            CheckupAction.getICD9(query, function (listdata) {
                data = listdata;
            });

            $.each(data, function (i, item) {
                var labelItem = item.idIcd9 +'-'+item.namaIcd9;
                mapped[labelItem] = {
                    id: item.idIcd9,
                    label: labelItem,
                    name: item.namaIcd9
                };
                menus.push(labelItem);
            });

            process(menus);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            // insert to textarea diagnosa_ket
            $("#ket_icd9").val(selectedObj.name);
            return selectedObj.id;
        }
    });
}

function getListRekamMedis(tipePelayanan, jenis, id){
    var li = "";
    CheckupAction.getListRekammedisPasien(tipePelayanan, jenis, id, function (res) {
        if(res.length > 0){
            $.each(res, function (i, item) {
                var cek = "";
                var tgl = "";
                var icons = '<i class="fa fa-file-o"></i>';
                var icons2 = '<i class="fa fa-print"></i>';
                var tol = "";

                if(item.isPengisian == "Y"){
                    var conver = "";
                    if(item.createdDate != null){
                        conver = converterDate(new Date(item.createdDate));
                        tgl = '<label class="label label-success">'+conver+'</label>';
                        tol = 'title="Mengisi tanggal '+conver+'"';
                    }
                    icons = '<i class="fa fa-file-o" style="color: #449d44"></i>';
                    cek = '<i class="fa fa-check" style="color: #449d44"></i>';
                    icons2 = '<i class="fa fa-print" style="color: #449d44"></i>';
                }
                if(item.jenis == 'ringkasan_rj'){
                    li += '<li><a style="cursor: pointer" onclick="'+item.function+'(\''+item.jenis+'\', \''+item.idRekamMedisPasien+'\', \'Y\')'+'"><i class="fa fa-file-o"></i>'+item.namaRm+'</a></li>'
                }else{
                    if(item.keterangan == 'form'){
                        li += '<li '+tol+' onmouseover="loadModalRM(\''+item.jenis+'\')"><a style="cursor: pointer" onclick="'+item.function+'(\''+item.jenis+'\', \''+item.idRekamMedisPasien+'\', \'Y\')'+'">'+icons+item.namaRm+' '+cek+'</a></li>'
                    }else if(item.keterangan == "surat"){
                        li += '<li><a style="cursor: pointer" onclick="'+item.function+'(\''+item.jenis+'\', \''+item.idRekamMedisPasien+'\', \'Y\',\''+item.namaRm+'\')'+'">'+icons2+item.namaRm+' '+cek+'</a></li>'
                    }
                }
            });
            $('#asesmen_rj').html(li);
        }
    });
}

function getDokterDpjp(){
    var option = '<option value="">[Select One]</option>';
    CheckupAction.getListDokterByBranchId(null, function (res) {
        if(res.length > 0){
            $.each(res, function (i, item) {
                option += '<option value="'+item.idDokter+'|'+item.idPelayanan+'">'+item.namaDokter+' - '+item.namaPelayanan+'</option>';
            });
            $('#dokter_dpjp').html(option);
        }else{
            $('#dokter_dpjp').html(option);
        }
    });
}

function isPemeriksaan(id){
    var cek = $('#'+id).is(':checked');
    if(cek){
        $('#form-pemeriksaan').show();
    }else{
        $('#form-pemeriksaan').hide();
    }
}
