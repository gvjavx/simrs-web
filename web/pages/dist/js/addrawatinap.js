
function getJenisResep(){

    var jenisPeriksaPasien = $("#id_jenis_pasien").val();

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
    var jenis = $('#id_jenis_pasien').val();
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
    var jenis = $("#id_jenis_pasien").val();
    CheckupDetailAction.getStatusBiayaTindakan(idDetailCheckup, "RI", function (response) {
        if (jenis == "bpjs" || jenis == "ptpn") {
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
        } else {
            $('#status_bpjs').hide();
        }
    });
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
        } else {
            option = option;
        }
    });

    $('#kamar_detail').html(option);
    $('#ruangan_ruang').html(option);
}

function selectKeterangan(val){
    if(val != ''){
        if(val == "selesai"){
            $('#form-selesai').show();
        }
        if(val == "lanjut_biaya" || val == "rujuk_rs_lain"){
            $('#form-selesai').hide();
            $('#form-cekup').hide();
        }
    }
}

function confirmSaveKeterangan() {

    var idKtg       = $('#keterangan').val();
    var noCheckup   = $("#no_checkup").val();
    var poli        = "";
    var kelas       = "";
    var kamar       = "";
    var idDokter    = "";
    var ket_selesai = $('#ket_selesai').val();
    var tgl_cekup   = $('#tgl_cekup').val();
    var ket_cekup   = $('#cekup_ket').val();
    var cara        = $('#ket_cara').val();
    var pendamping  = $('#ket_pendamping').val();
    var tujuan      = $('#ket_tujuan').val();

    var namaAsuransi = $('#nama_asuransi').val();
    var noRujukan = $('#no_rujukan').val();
    var tglRujukan = $('#tgl_rujukan').val();
    var suratRujukan = $('#surat_rujukan').val();
    var isLaka = $('#is_laka').val();

    if(idKtg == "lanjut_biaya" || idKtg == "rujuk_rs_lain"){
        if(isLaka == "Y"){
            if(noRujukan != '' && tglRujukan != '' && suratRujukan != ''){
                $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+idDokter+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+cara+'\' , \''+pendamping+'\', \''+tujuan+'\')');
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
                    'id_dokter':idDokter,
                    'cara':cara,
                    'pendamping':pendamping,
                    'tujuan':tujuan
                }
                var result = JSON.stringify(data);
                $('#save_laka').attr('onclick','saveDataAsuransi(\''+result+'\')');
            }
        }else{
            $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+idDokter+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+cara+'\' , \''+pendamping+'\', \''+tujuan+'\')');
            $('#modal-confirm-dialog').modal('show');
        }
    }

    if(idKtg == "selesai"){
        if(ket_selesai != ''){
            if(isLaka == "Y"){
                if(noRujukan != '' && tglRujukan != '' && suratRujukan != ''){
                    $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+idDokter+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+cara+'\' , \''+pendamping+'\', \''+tujuan+'\')');
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
                        'id_dokter':idDokter,
                        'cara':cara,
                        'pendamping':pendamping,
                        'tujuan':tujuan
                    }
                    var result = JSON.stringify(data);
                    $('#save_laka').attr('onclick','saveDataAsuransi(\''+result+'\')');
                }
            }else{
                $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+idDokter+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+cara+'\' , \''+pendamping+'\', \''+tujuan+'\')');
                $('#modal-confirm-dialog').modal('show');
            }
        }else{
            $('#warning_ket').show().fadeOut(5000);
            $('#war_catatan').show();
        }
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
    var idDokter = obj["id_dokter"];
    var cara = obj["cara"];
    var pendamping = obj["pendamping"];
    var tujuan = obj["tujuan"];

    if(noPolisi != '' && tglKejadian != '' && dataURL != ''){

        $('#save_laka').hide();
        $('#load_laka').show();
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveUpdateDataAsuransi(idDetailCheckup, noPolisi, tglKejadian, dataURL, function (response) {
            if(response.status == "success"){
                $('#modal-laka').modal('hide');
                $('#save_laka').show();
                $('#load_laka').hide();
                $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+idDokter+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+cara+'\' , \''+pendamping+'\', \''+tujuan+'\')');
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

function saveKeterangan(idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, cara, pendamping, tujuan){
    $('#modal-confirm-dialog').modal('hide');
    var jenisPasien = $('#id_jenis_pasien').val();
    $('#save_ket').hide();
    $('#load_ket').show();
    $('#waiting_dialog').dialog('open');

    var metodBayar = '<s:property value="rawatInap.metodePembayaran"/>';
    dwr.engine.setAsync(true);
    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, cara, pendamping, tujuan, "", metodBayar, function (response) {
        if(response.status == "success"){
            $('#waiting_dialog').dialog('close');
            $('#info_dialog').dialog('open');
            $('#close_pos').val(8);
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

function listSelectTindakan(idKtg) {
    var option = "<option value=''>[Select One]</option>";
    if (idKtg != '') {
        CheckupDetailAction.getListComboTindakan(idKtg, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idTindakan + "'>" + item.tindakan + "</option>";
                });
                $('#tin_id_tindakan').html(option);
            } else {
                $('#tin_id_tindakan').html(option);
            }
        });
    } else {
        $('#tin_id_tindakan').html(option);
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
        desti = "#pos_diet";
    } else if (back == 6) {
        desti = "#pos_obat";
    } else if (back == 7) {
        desti = "#pos_ruangan";
    } else if (back == 8) {
        window.location.href = 'initForm_rawatinap.action';
    } else if (back == 9){
        desti = '#pos_rssep';
    }else if (back == 10){
        desti = '#pos_icd9';
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
        $('#modal-dokter').modal({show:true,backdrop:'static'});

    } else if (select == 2) {
        $('#tin_id_ketgori_tindakan, #tin_id_tindakan, #tin_id_perawat').val('').trigger('change');
        $('#tin_qty').val('1');
        $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
        $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
        $('#modal-tindakan').modal({show:true,backdrop:'static'});

    } else if (select == 3) {
        $('#nosa_id_diagnosa, #nosa_ket_diagnosa').val('');
        $('#nosa_id_diagnosa, #nosa_jenis_diagnosa').val('').trigger('change');
        $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
        $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
        $('#modal-diagnosa').modal({show:true,backdrop:'static'});

    } else if (select == 4) {
        $('#lab_kategori, #lab_lab').val('').trigger('change');
        $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
        $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
        $('#modal-lab').modal({show:true,backdrop:'static'});
    } else if (select == 5) {
        $('#bentuk_diet, #keterangan_diet').val('');
        $('#bentuk_diet, #keterangan_diet').val('').removeAttr('disabled');
        $('#save_diet').attr('onclick', 'saveDiet(\'' + id + '\')').show();
        $('#load_diet, #warning_diet, #war_bentuk_diet, #war_keterangan_diet').hide();
        $('#modal-diet').modal({show:true,backdrop:'static'});
    } else if (select == 6) {
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
        $('#modal-obat').modal({show:true,backdrop:'static'});
    }else if (select == 7) {
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
        $('#modal-resep-head').modal({show:true,backdrop:'static'});
    } else if (select == 8){
        $('#id_icd9, #ket_icd9').val('');
        $('#load_icd9').hide();
        $('#save_icd9').attr('onclick', 'saveICD9(\'' + id + '\')').show();
        $('#modal-icd9').modal({show:true, backdrop:'static'});
    }
}

function formatRupiah(angka) {
    var reverse = angka.toString().split('').reverse().join(''),
        ribuan = reverse.match(/\d{1,3}/g);
    ribuan = ribuan.join('.').split('').reverse().join('');
    return ribuan;
}


function saveDokter(id) {
    var idDokter = $('#dok_id_dokter').val();

    if (idDetailCheckup != '' && idDokter != '') {
        $('#save_dokter').hide();
        $('#load_dokter').show();
        if (id != '') {
            dwr.engine.setAsync(true);
            TeamDokterAction.editDokter(id, idDokter, function (response) {
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
            TeamDokterAction.saveDokter(idDetailCheckup, idDokter, function (response) {
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
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDokter(\'' + item.idTeamDokter + '\',\'' + item.idDokter + '\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
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
    var idJenisPeriksa = $('#id_jenis_pasien').val();

    if (idDetailCheckup != '' && idTindakan != '' && idDokter != '' && idPerawat != '' && qty > 0 && idKategori != '') {

        $('#save_tindakan').hide();
        $('#load_tindakan').show();

        if (id != '') {
            dwr.engine.setAsync(true);
            TindakanRawatAction.editTindakanRawat(id, idDetailCheckup, idTindakan, idDokter, idPerawat, qty, idJenisPeriksa, {
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
            TindakanRawatAction.saveTindakanRawat(idDetailCheckup, idTindakan, idDokter, idPerawat, qty, idJenisPeriksa, {
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
                var btn = '<img border="0" class="hvr-grow" onclick="editTindakan(\'' + item.idTindakanRawat + '\',\'' + item.idTindakan + '\',\'' + item.idKategoriTindakan + '\',\'' + item.idPerawat + '\',\'' + item.qty + '\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer;">';

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

                if("Y" == item.approveFlag){
                    btn = "";
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + item.namaTindakan + "</td>" +
                    "<td align='right'>" + tarif +"</td>" +
                    "<td align='center'>" + item.qty + "</td>" +
                    "<td align='right'>" + tarifTotal + "</td>" +
                    "<td align='center'>" + btn + "</td>" +
                    "</tr>";

            });
            table = table + "<tr>" +
                "<td colspan='4'>Total</td>" +
                "<td align='right'>" + formatRupiah(trfTtl) + "</td>" +
                "<td></td>" +
                "</tr>";
            $('#body_tindakan').html(table);
        }
    });



}

function saveDiagnosa(id) {

    var idDiag = $('#nosa_id_diagnosa').val();
    var ketDiagnosa = $('#nosa_ket_diagnosa').val();
    var jenisDiagnosa = $('#nosa_jenis_diagnosa').val();
    var jenisPasien = $('#id_jenis_pasien').val();

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
                        $('#save_diagnosa').show();
                        $('#load_diagnosa').hide();
                        $('#warning_diagnosa').show().fadeOut(5000);
                        $('#msg_diagnosa').text(response.msg);
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
                        $('#save_diagnosa').show();
                        $('#load_diagnosa').hide();
                        $('#warning_diagnosa').show().fadeOut(5000);
                        $('#msg_diagnosa').text(response.msg);
                    }
                }
            })
        }
    } else {
        $('#warning_diagnosa').show().fadeOut(5000);
        $('#msg_diagnosa').text("Silahkan cek kembali data inputan...!");
        if (idDiag == '') {
            $('#war_diagnosa_bpjs').show();
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
                    if (item.jenisDiagnosa == 0) {
                        jen = "Diagnosa Awal";
                    } else {
                        jen = "Diagnosa Akhir";
                    }
                }
                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + id + "</td>" +
                    "<td>" + ket + "</td>" +
                    "<td>" + jen + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiagnosa(\'' + item.idDiagnosaRawat + '\',\'' + item.idDiagnosa + '\',\'' + item.jenisDiagnosa + '\', \''+item.keteranganDiagnosa+'\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_diagnosa').html(table);
}

function listSelectLab(select) {
    var idx = select.selectedIndex;

    var option = "<option value=''>[Select One]</option>";
    if (idx > 0) {
        var idKategori = select.options[idx].value;
        LabAction.listLab(idKategori, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idLab + "'>" + item.namaLab + "</option>";
                });
                $('#lab_lab').html(option);
            } else {
                $('#lab_lab').html('');
            }
        });
    }else{
        $('#lab_lab').html('');
    }
}

function listSelectParameter(select) {
    var idx = select.selectedIndex;
    var option = "";
    if (idx > 0) {
        var idLab = select.options[idx].value;
        LabDetailAction.listLabDetail(idLab, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idLabDetail + "'>" + item.namaDetailPeriksa + "</option>";
                });
                $('#lab_parameter').html(option);
            } else {
                $('#lab_parameter').html('');
            }
        });
    } else {
        $('#lab_parameter').html('');
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
                var btn = '<img border="0" class="hvr-grow" onclick="editLab(\'' + item.idPeriksaLab + '\',\'' + item.idLab + '\',\'' + item.idKategoriLab + '\',\''+item.kategoriLabName+'\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer;">';

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
                    btn = '<a target="_blank" href="printLabRadiologi_rawatinap.action?id='+idDetailCheckup+'&tipe='+tipe+'&lab='+item.idPeriksaLab+'"><img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;"></a>';
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + lab + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td>" + item.kategoriLabName + "</td>" +
                    "<td align='center'>" + btn + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_lab').html(table);
}

function listSelectObat(select) {
    var idx = select.selectedIndex;
    var idJenis = select.options[idx].value;
    var option = "<option value=''>[Select One]</option>";
    var stok = "";

    if (idJenis != '') {
        ObatAction.listObat(idJenis, function (response) {
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
    $('#resep_nama_obat').html(option);
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
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editObat(\'' + item.idObatInap + '\',\'' + id + '\',\'' + qty + '\',\'' + jenis + '\',\'' + obat + '\',\'' + item.qtyBox + '\',\'' + item.qtyLembar + '\',\'' + item.qtyBiji + '\',\'' + item.lembarPerBox + '\',\'' + item.bijiPerLembar + '\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_obat').html(table);
}

function saveDiet(id) {

    var bentukDiet = $('#bentuk_diet').val();
    var keteranganDiet = $('#keterangan_diet').val();

    if(bentukDiet != '' && keteranganDiet != ''){
        $('#save_diet').hide();
        $('#load_diet').show();
        dwr.engine.setAsync(true);
        if(id != ''){
            OrderGiziAction.editOrderGizi(id, bentukDiet, function (response) {
                if (response.status == "success") {
                    dwr.engine.setAsync(false);
                    listDiet();
                    $('#modal-diet').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(5);
                    $('#save_diet').show();
                    $('#load_diet').hide();
                } else {
                    $('#save_diet').show();
                    $('#load_diet').hide();
                    $('#warning_diet').show().fadeOut(5000);
                    $('#msg_diet').text(response.message);
                }
            });
        }else{
            OrderGiziAction.saveOrderGizi(idRawatInap, bentukDiet, keteranganDiet, function (response) {
                if (response.status == "success") {
                    dwr.engine.setAsync(false);
                    listDiet();
                    $('#modal-diet').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(5);
                    $('#save_diet').show();
                    $('#load_diet').hide();
                } else {
                    $('#save_diet').show();
                    $('#load_diet').hide();
                    $('#warning_diet').show().fadeOut(5000);
                    $('#msg_diet').text(response.message);
                }
            });
        }
    }else{
        $('#warning_diet').show().fadeOut(5000);
        $('#msg_diet').text("Silahkan cek kembali inputan anda...!");
        if (bentukDiet == '') {
            $('#war_bentuk_diet').show();
        }
        if (keteranganDiet == '') {
            $('#war_keterangan_diet').show();
        }
    }
}

function listDiet() {
    var table = "";
    var data = [];

    OrderGiziAction.listOrderGizi(idRawatInap, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {
                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));
                var label = "";
                var btn = "";

                if(item.diterimaFlag == "R"){
                    label = '<label class="label label-danger"> dibatalakan</label>';
                }else{
                    if(item.approveFlag == "Y"){
                        btn = '<div class="input-group">' +
                            '<input class="form-control" onchange="cekBarcode(this.value, \''+item.idOrderGizi+'\')">' +
                            '<div class="input-group-addon">' +
                            '<span id="status'+item.idOrderGizi+'"></span>' +
                            '</div>' +
                            '</div>';
                        label = '<label class="label label-info"> telah dikonfirmasi</label>';
                    }else{
                        btn = '<img border="0" class="hvr-grow" onclick="editDiet(\'' + item.idOrderGizi + '\',\'' + item.idDietGizi + '\',\'' + item.keterangan + '\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' +
                            '<img border="0" class="hvr-grow" onclick="cancelDiet(\'' + item.idOrderGizi + '\')" src="<s:url value="/pages/images/icons8-cancel-25.png"/>" style="cursor: pointer;">';
                        label = '<label class="label label-warning"> menunggu konfirmasi</label>'
                    }

                    if(item.diterimaFlag == "Y"){
                        btn = '<div class="input-group">' +
                            '<input class="form-control" value="'+item.idOrderGizi+'" disabled>' +
                            '<div class="input-group-addon">' +
                            '<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">'+
                            '</div>' +
                            '</div>';
                        label = '<label class="label label-success"> telah diterima</label>';
                    }
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + item.idDietGizi + "</td>" +
                    "<td>" + item.bentukDiet + "</td>" +
                    "<td>" + item.keterangan + "</td>" +
                    "<td style='vertical-align: middle' >" + label + "</td>" +
                    "<td align='center'>" + btn + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_diet').html(table);
}

function cekBarcode(value, idOrderGizi){

    if(value != '' && idOrderGizi != ''){

        if(value == idOrderGizi){
            $('#status'+idOrderGizi).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            setTimeout(function () {
                OrderGiziAction.updateDiterimaFlag(idOrderGizi, function (response) {
                    if(response.status == "success"){
                        listDiet();
                    }else{
                        $('#status' + idOrderGizi).html('<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">');
                    }
                });
            },200);
        }else{
            $('#status'+idOrderGizi).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            setTimeout(function () {
                $('#status' + idOrderGizi).html('<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">');
            },200);
        }
    }else{
        $('#status' + idOrderGizi).html('');
    }
}

function editDokter(id, idDokter) {
    $('#load_dokter, #war_dok').hide();
    $('#save_dokter').attr('onclick', 'saveDokter(\'' + id + '\')').show();
    $('#dok_id_dokter').val(idDokter).trigger('change');
    $('#modal-dokter').modal({show:true,backdrop:'static'});
}

function editTindakan(id, idTindakan, idKategori, idPerawat, qty) {
    $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
    $('#tin_id_ketgori_tindakan').val(idKategori).trigger('change');
    $('#tin_id_tindakan').val(idTindakan).trigger('change');
    $('#tin_id_perawat').val(idPerawat).trigger('change');
    $('#tin_qty').val(qty);
    $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
    $('#modal-tindakan').modal({show:true,backdrop:'static'});
}

function editDiagnosa(id, idDiagnosa, jenis, ket) {
    var jenisPasien = $('#id_jenis_pasien').val();
    $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
    if(jenisPasien == "bpjs" || jenisPasien == "ptpn"){
        $('#nosa_id_diagnosa_bpjs').val(idDiagnosa);
        $('#nosa_ket_diagnosa').val(ket);
    }else{
        $('#nosa_id_diagnosa').val(idDiagnosa).trigger('change');
    }
    $('#nosa_jenis_diagnosa').val(jenis).trigger('change');
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
    $('#modal-lab').modal({show:true,backdrop:'static'});
}

function editDiet(id, idDietGizi, keterangan) {
    $('#load_diet, #warning_diet, #war_bentuk_diet, #war_keterangan_diet').hide();
    $('#bentuk_diet').val(idDietGizi).trigger('change');
    $('#keterangan_diet').val(keterangan).attr('disabled','true');
    $('#save_diet').attr('onclick', 'saveDiet(\'' + id + '\')').show();
    $('#modal-diet').modal({show:true,backdrop:'static'});
}

function editObat(id, idobat, qty, jenis, namaObat, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerLembar) {
    var qtyBox1 = "";
    var qtyLembar1 = "";
    var qtyBiji1 = "";

    if(qtyBox != 'null'){
        qtyBox1 = qtyBox;
    }

    if(qtyLembar != 'null'){
        qtyLembar1 = qtyLembar;
    }

    if(qtyBiji != 'null'){
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
    $('#modal-obat').modal({show:true,backdrop:'static'});
}

function editRuangan(kelas, ruang) {
    $('#load_ruangan, #war_ruangan_kelas, #war_ruangan_ruang').hide();
    $('#ruangan_kelas').val(kelas).trigger('change');
    $('#ruangan_ruang').val(ruang).trigger('change');
    $('#modal-ruangan').modal({show:true,backdrop:'static'});
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


function listRuanganInap() {

    var table = "";
    var data = [];
    var no = "";
    var name = "";
    var kelas = "";

    CheckupDetailAction.getListRuangInapByIdDetailCheckup(idDetailCheckup, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {
                if (item.namaRangan != null) {
                    name = item.namaRangan;
                }
                if (item.noRuangan != null) {
                    no = item.noRuangan;
                }
                if (item.kelasRuanganName != null) {
                    kelas = item.kelasRuanganName;
                }
                table += "<tr>" +
                    "<td>" + name + "</td>" +
                    "<td>" + no + "</td>" +
                    "<td>" + kelas + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editRuangan(\'' + item.idKelas + '\',\'' + item.idRuangan + '\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#no_ruang').html(no);
    $('#name_ruang').html(name);
    $('#body_ruangan').html(table);
}

function saveEditRuang() {

    var idKelas = $('#ruangan_kelas').val();
    var idRuang = $('#ruangan_ruang').val();

    if (idDetailCheckup != '' && idKelas != '' && idRuang) {
        $('#save_ruang').hide();
        $('#load_ruang').show();

        dwr.engine.setAsync(true);
        CheckupDetailAction.saveUpdateRuangan(idRuang, idDetailCheckup, {
            callback: function (response) {
                if (response == "SUCCESS") {
                    dwr.engine.setAsync(false);
                    listRuanganInap();
                    $('#modal-ruangan').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(7);
                    $('#save_ruang').show();
                    $('#load_ruang').hide();
                } else {

                }
            }
        })

    } else {
        $('#warning_ruangan').show().fadeOut(5000);

        if (idKelas == '') {
            $('#war_ruangan_kelas').show();
        }
        if (idRuang == '') {
            $('#war_ruangan_ruang').show();
        }
    }
}

function showFormCekup(select){
    var idx = select.selectedIndex;
    var idKet = select.options[idx].value;
    if(idKet == "Checkup Ulang"){
        $('#form-cekup').show();
    }else{
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

    var listObat = $("input[name=cek_waktu]:checked");
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
                    '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\',\''+totalHarga+'\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
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
                    '<img border="0" class="hvr-grow" onclick="detailResep(\'' + item.idApprovalObat + '\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' +
            '<img onclick="printResep(\'' + idResep + '\')" class="hvr-grow" src="'+pathImages+'/pages/images/icons8-print-25.png" style="cursor: pointer;">' +
            "</td>" +
            "</tr>"
            });
        }
    });

    $('#body_resep').html(table);
}

function detailResep(id){
    $('#modal-resep-detail').modal({show:true,backdrop:'static'});
    listDetailResepPasien(id);
}

function listDetailResepPasien(idApprovalObat) {

    var table = "";
    var data = [];

    PermintaanResepAction.listDetail(idApprovalObat, function (response) {
        data = response;
        if (data != null) {
            $.each(data, function (i, item) {

                var qty      = "";
                var namaObat = "";
                var ket      = "";
                var idObat   = "";

                if(item.idObat != null){
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
                    "<td>"+ '<span id=obat'+idObat+'>' + namaObat + '</span><input style="display:none; width: 120px;" type="text" id=newObat'+idObat+' class="form-control"><input type="hidden" id=idObat'+idObat+'>' + "</td>" +
                    "<td>"+'<span id=qty'+idObat+'>'+ qty + '</span>'+'<input type="hidden" id=newId'+idObat+' value='+idObat+'>'+
                    '<input style="display:none; width: 80px" type="number" id=newQty'+idObat+' class="form-control">'+ "</td>" +
                    "<td>"+'<span id=ket'+idObat+'>'+ ket + '</span>'+
                    '<select class="form-control" id=newKet'+idObat+' style="display:none"'+
                    '<option value="">[Select One]</option>'+
                    '<option value="2 x 1 /Hari">2 x 1 /Hari</option>'+
                    '<option value="3 x 1 /Hari">3 x 1 /Hari</option>'+
                    '</select>'+ "</td>" +
                    "<td align='center'>" + '<img border="0" id='+idObat+' class="hvr-grow" onclick="editObatResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + qty + '\',\'' + ket + '\',\'' + namaObat + '\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer; ">'+
                    '<img border="0" id=save'+idObat+' class="hvr-grow" onclick="saveDetailResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\''+item.idApprovalObat+'\')" src="<s:url value="/pages/images/save_flat.png"/>" style="cursor: pointer; height: 25px; width: 25px; display: none">'+ "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_detail_rep').html(table);
}

function editObatResep(id, idObat, qty, ket, namaObat){

    if($('#'+idObat).attr('src') == pathImages+'/pages/images/icons8-create-25.png'){
        var url = pathImages+'/pages/images/cnacel-flat.png"/>';
        $('#'+idObat).attr('src',url);
        $('#obat'+idObat).hide();
        $('#qty'+idObat).hide();
        $('#ket'+idObat).hide();

        $('#newObat'+idObat).show().val(namaObat);
        $('#newQty'+idObat).show().val(qty);
        $('#newKet'+idObat).show().val(ket);
        $('#save'+idObat).show();

    }else{
        var url = pathImages+'/pages/images/icons8-create-25.png';
        $('#'+idObat).attr('src',url);
        $('#obat'+idObat).show();
        $('#qty'+idObat).show();
        $('#ket'+idObat).show();

        $('#newObat'+idObat).hide();
        $('#newQty'+idObat).hide();
        $('#newKet'+idObat).hide();
        $('#save'+idObat).hide();
    }
}

function saveDetailResep(id, idObat, idApp){

    var obat    = $('#newId'+idObat).val();
    var qty     = $('#newQty'+idObat).val();
    var ket     = $('#newKet'+idObat).val();

    if(obat != '' && qty != '' && ket != ''){
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
    }else{
        $('#warning_detail').show().fadeOut(5000);
    }
}

function confirmSaveAllTindakan(){
    $('#modal-confirm-dialog').modal('show');
    $('#save_con').attr('onclick','saveAllTindakan()');
}

function saveAllTindakan(){
    var jenisPasien = $('#id_jenis_pasien').val();
    $('#modal-confirm-dialog').modal('hide');
    $('#save_all').hide();
    $('#load_all').show();
    dwr.engine.setAsync(true);
    CheckupDetailAction.saveApproveAllTindakanRawatJalan(idDetailCheckup, jenisPasien, {
        callback : function (response) {
            if(response.status == "success"){
                $('#success_all').show().fadeOut(5000);
                $('#msg_all_suc').text("Berhasil menyimpan semua tindakan...!");
                $('#save_all').show();
                $('#load_all').hide();
                hitungStatusBiaya();
                listTindakan();
            }else{
                $('#warning_all').show().fadeOut(5000);
                $('#msg_all_war').text(response.message);
                $('#save_all').show();
                $('#load_all').hide();
            }
        }});
}

function showOtherInput(id){
    var nilai = $("#"+id+"").val();
    if (nilai.toLowerCase() == "lain") {
        $("#ot_"+id+"").removeAttr('style');
    } else {
        $("#ot_"+id+"").hide();
        $("#ot_"+id+"").val("");
    }
}

function setObatPoli(select) {
    var idx = select.selectedIndex;
    var poli = select.options[idx].value;
    var idPel = poli.split('|')[0];
    var namePel = poli.split('|')[1];
    var option = "<option value=''>[Select One]</option>";
    var jenisPasien = $('#id_jenis_pasien').val();

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
            //$('#resep_stok_box').val(qtyBox);
            //$('#resep_stok_lembar').val(qtyLembar);
            $('#resep_stok_biji').val(total);
            $("#h-qty-default").val(bijiPerLembar);
            $('#resep_qty').val(parseInt(bijiPerLembar));

            $('#resep_keterangan').val('');
            $('#resep_qty').val(bijiPerLembar);
            $('#resep_jenis_satuan').val('biji').trigger('change');
        }
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

function defaultValByJenisSatuan(name) {

    var nilai = "1";
    if (name == "biji"){
        nilai = $("#h-qty-default").val();
    }
    $("#resep_qty").val(nilai);

}

function cancelDiet(id){
    $('#modal-cancel-diet').modal({show:true, backdrop:'static'});
    $('#save_cancel_diet').attr('onclick','saveCancelDiet(\''+id+'\')');
}

function saveCancelDiet(id) {

    var ket = $('#keterangan_cancel').val();
    if (ket != '') {
        $('#save_cancel_diet').hide();
        $('#load_cancel_diet').show();
        dwr.engine.setAsync(true);
        OrderGiziAction.cancelOrderGizi(id, ket, {
            callback: function (response) {
                if (response.status == "success") {
                    dwr.engine.setAsync(false);
                    listDiet();
                    $('#modal-cancel-diet').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(5);
                    $('#save_cancel_diet').show();
                    $('#load_cancel_diet').hide();
                } else {
                    $('#save_cancel_diet').show();
                    $('#load_cancel_diet').hide();
                    $('#warning_cancel').show().fadeOut(5000);
                    $('#msg_cancel').text(response.message);
                }
            }
        });
    } else {
        $('#warning_cancel').show().fadeOut(5000);
        $('#msg_cancel').text('Silahkan cek kembali data inputan...!');
        $('#war_keterangan_cancel').show();
    }
}

function printResep(id) {
    window.open('printResepPasien_rawatinap.action?id=' + idDetailCheckup + '&idResep=' + id, '_blank');
}

function saveICD9(id) {

    var idIcd9 = $('#id_icd9').val();
    var ketIcd9 = $('#ket_icd9').val();
    var idIcd9Edit = $('#id_edit_icd9').val();
    var jenisPasien = $('#id_jenis_pasien').val();
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
                        $('#close_pos').val(10);
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
                        $('#close_pos').val(10);
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
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editICD9(\'' + item.idTindakanRawatIcd9 + '\',\'' + item.idIcd9 + '\',\'' + item.namaIcd9 + '\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
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