function getJenisResep(id) {

    var jenisPeriksaPasien = $("#id_jenis_pasien").val();

    strSelect = "";
    var arBodyJenisResep = [];
    if (jenisPeriksaPasien == "rekanan") {
        arBodyJenisResep.push({"nilai": "bpjs", "label": "BPJS"}, {"nilai": "rekanan", "label": "Rekanan"});
    } else if (jenisPeriksaPasien == "asuransi") {
        arBodyJenisResep.push({"nilai": "asuransi", "label": "ASURANSI"}, {"nilai": "umum", "label": "UMUM"});
    } else if (jenisPeriksaPasien == "bpjs") {
        arBodyJenisResep.push({"nilai": "bpjs", "label": "BPJS"});
    } else if (jenisPeriksaPasien == "bpjs_rekanan") {
        arBodyJenisResep.push({"nilai": "bpjs", "label": "BPJS REKANAN"});
    } else  {
        arBodyJenisResep.push({"nilai": "umum", "label": "UMUM"});
    }

    var strSelect = "";
    $.each(arBodyJenisResep, function (i, item) {
        strSelect += "<option value='" + item.nilai + "'>" + item.label + "</option>";
    });
    $("#"+id).html(strSelect);
}

function hitungBmi() {

    var berat = $('#berat').val();
    var tinggi = $('#tinggi').val();
    var persen = "";
    var bmi = "";
    var barClass = "";
    var barLabel = "";

    if (berat != '' && tinggi != '') {
        var tom = (parseInt(tinggi) * 0.01);
        var tes = (parseFloat(tom)) * parseFloat(tom);
        bmi = (parseInt(berat) / (tom * tom)).toFixed(2);
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

    var barBmi = '<div class="progress-bar ' + barClass + '" style="width: ' + persen + '%" role="progressbar" aria-valuenow="' + persen + '" aria-valuemin="0" aria-valuemax="100">' + bmi + '</div>';

    $('#bar_bmi').html(barBmi);
}

function printGelangPasien() {
    window.open('printGelangPasien_checkupdetail.action?id=' + noCheckup, '_blank');
}

function hitungCoverBiaya() {
    var jenis = $('#id_jenis_pasien').val();
    if ("asuransi" == jenis) {
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

function listSelectDokter(isi) {
    var option = "<option value=''>[Select One]</option>";
    CheckupAction.getListDokterByBranchId(isi, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                option += "<option value='" + item.idDokter + '|' + item.idPelayanan + '|' + item.namaPelayanan + "'>" + item.namaDokter + "</option>";
            });
            $('#dok_id_dokter').html(option);
        } else {
            $('#dok_id_dokter').html(option);
        }
    });
}

function setSpesialis(value) {
    if (value != '') {
        $('#pelayanan_dokter').val(value.split("|")[2]);
    }
}

function listSelectRuangan(id) {
    var flag = true;
    var option = "";
    if (id != '' && id != null) {
        CheckupDetailAction.listRuangan(id, flag, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idTempatTidur + "'>[" + item.noRuangan + "]-" + item.namaRuangan + "-[" + item.namaTempatTidur + "]</option>";
                });
            } else {
                option = option;
            }
            // alert(option);
            // console.log(option);

            $('#kamar_detail').html(option);
            $('#ruangan_ruang').html(option);
        });
    }
}

function selectKeterangan(idKtg) {
    var keteranganPindah = $('#keterangan option:selected').text();
    var jenisPasien = $('#id_jenis_pasien').val();
    if (idKtg != '') {
        if (idKtg == "selesai") {
            $('#form-selesai').show();
            $('#form-catatan').show();

            $('#form-dpjp').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pemeriksaan').hide();
            $('#form-kamar').hide();
            $('#form-stay').hide();
            $('#form-kelas_kamar').hide();
            $('#form-asesmen').hide();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else if (idKtg == "rawat_inap") {
            $('#form-ket-rawat_inap').show();
            $('#form-catatan').show();
            $('#form-kelas_kamar').show();
            $('#form-kamar').show();
            getKelasKamar('rawat_inap');
            $('#label_kamar').text("Kamar");
            $('#kelas_kamar').attr('onchange', 'getKamar(this.value, \'rawat_inap\')');
            if (jenisPasien == 'bpjs' || jenisPasien == 'bpjs_rekanan') {
                $('#form-hak_kamar').show();
                $('#hak_kamar_bpjs').text("Hak Kamar " + kelasPasienBpjs);
            } else {
                $('#form-hak_kamar').hide();
                $('#hak_kamar_bpjs').text('');
            }
            $('#title_asesmen').html(keteranganPindah);
            $('#btn_pindah').attr('onclick', 'setRekamMedisPindah(\'pindah_ri\')');

            $('#form-selesai').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-rs-rujukan').hide();
            $('#form-pemeriksaan').hide();
            $('#form-stay').hide();
            $('#form-asesmen').show();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else if (idKtg == "rujuk_rs_lain") {
            $('#form-rs-rujukan').show();
            $('#form-catatan').show();

            $('#form-selesai').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pemeriksaan').hide();
            $('#form-kamar').hide();
            $('#form-stay').hide();
            $('#form-kelas_kamar').hide();
            $('#form-asesmen').hide();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else if (idKtg == "kontrol_ulang") {
            $('#form-tgl-kontrol').show();
            $('#form-catatan').show();

            $('#form-rs-rujukan').hide();
            $('#form-selesai').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-pemeriksaan').hide();
            $('#form-stay').hide();
            $('#form-kelas_kamar').hide();
            $('#form-asesmen').hide();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else if (idKtg == "rawat_intensif") {
            getKamar(null, 'rawat_intensif');
            $('#label_kamar').text('Kamar Intensif');
            $('#form-catatan').show();
            $('#form-kamar').show();
            if (kategoriRuangan == 'rawat_inap') {
                $('#form-stay').show();
            }

            $('#form-selesai').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pemeriksaan').hide();
            $('#form-kelas_kamar').hide();
            $('#form-asesmen').show();
            $('#title_asesmen').html(keteranganPindah);
            $('#btn_pindah').attr('onclick', 'setRekamMedisPindah(\'pindah_ri\',\'asesmen_pindah\')');
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else if (idKtg == "rawat_isolasi") {
            getKamar(null, 'rawat_isolasi');
            $('#label_kamar').text('Kamar Isolasi');
            $('#form-catatan').show();
            $('#form-kamar').show();
            if (kategoriRuangan == 'rawat_inap') {
                $('#form-stay').show();
            }

            $('#form-selesai').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pemeriksaan').hide();
            $('#form-kelas_kamar').hide();
            $('#form-asesmen').show();
            $('#title_asesmen').html(keteranganPindah);
            $('#btn_pindah').attr('onclick', 'setRekamMedisPindah(\'pindah_ri\',\'asesmen_pindah\')');
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else if (idKtg == "kamar_operasi") {
            getKamar(null, 'kamar_operasi');
            $('#label_kamar').text('Kamar Operasi');
            $('#form-catatan').show();
            $('#form-kamar').show();
            if (kategoriRuangan == 'rawat_inap') {
                $('#form-stay').show();
            }

            $('#form-selesai').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pemeriksaan').hide();
            $('#form-kelas_kamar').hide();
            $('#form-asesmen').show();
            $('#title_asesmen').html(keteranganPindah);
            $('#btn_pindah').attr('onclick', 'setRekamMedisPindah(\'pindah_ok\',\'asesmen_pindah\')');
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else if (idKtg == "ruang_bersalin") {
            getKamar(null, 'ruang_bersalin');
            $('#label_kamar').text('Ruang Bersalin');
            $('#form-catatan').show();
            $('#form-kamar').show();
            if (kategoriRuangan == 'rawat_inap') {
                $('#form-stay').show();
            }

            $('#form-selesai').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pemeriksaan').hide();
            $('#form-kelas_kamar').hide();
            $('#form-asesmen').show();
            $('#title_asesmen').html(keteranganPindah);
            $('#btn_pindah').attr('onclick', 'setRekamMedisPindah(\'pindah_ri\',\'asesmen_pindah\')');
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else if (idKtg == "rr") {
            getKamar(null, 'rr');
            $('#label_kamar').text('Ruangan');
            $('#form-catatan').show();
            $('#form-kamar').show();
            if (kategoriRuangan == 'rawat_inap') {
                $('#form-stay').show();
            }

            $('#form-selesai').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pemeriksaan').hide();
            $('#form-kelas_kamar').hide();
            $('#form-asesmen').show();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else {
            $('#form-selesai').hide();
            $('#form-catatan').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pemeriksaan').hide();
            $('#form-kamar').hide();
            $('#form-stay').hide();
            $('#form-kelas_kamar').hide();
            $('#form-asesmen').hide();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');
        }
    } else {
        $('#form-selesai').hide();
        $('#form-catatan').hide();
        $('#form-ket-rawat_inap').hide();
        $('#form-rs-rujukan').hide();
        $('#form-tgl-kontrol').hide();
        $('#form-pemeriksaan').hide();
        $('#form-kamar').hide();
        $('#form-stay').hide();
        $('#form-kelas_kamar').hide();
        $('#form-asesmen').hide();
        $('#form_order_pemeriksaan').hide();
        $('#body_order_pemeriksaan').html('');
    }
}

function confirmSaveKeterangan() {

    var idKtg = $('#keterangan').val();
    var noCheckup = $("#no_checkup").val();
    var poli = "";
    var kelas = "";
    var kamar = "";
    var idDokter = "";
    var ket_selesai = $('#ket_selesai').val();
    var tgl_cekup = $('#tgl_cekup').val();
    var ket_cekup = $('#cekup_ket').val();
    var cara = $('#ket_cara').val();
    var pendamping = $('#ket_pendamping').val();
    var tujuan = $('#ket_tujuan').val();

    var namaAsuransi = $('#nama_asuransi').val();
    var noRujukan = $('#no_rujukan').val();
    var tglRujukan = $('#tgl_rujukan').val();
    var suratRujukan = $('#surat_rujukan').val();
    var isLaka = $('#is_laka').val();

    if (idKtg == "lanjut_biaya" || idKtg == "rujuk_rs_lain") {
        if (isLaka == "Y") {
            if (noRujukan != '' && tglRujukan != '' && suratRujukan != '') {
                $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + idDokter + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + cara + '\' , \'' + pendamping + '\', \'' + tujuan + '\')');
                $('#modal-confirm-dialog').modal('show');
            } else {
                $('#laka_no_polisi').val(noRujukan);
                $('#laka_tgl_kejadian').val(noRujukan);
                $('#laka_surat_rujukan').val(suratRujukan);
                $('#modal-laka').modal({show: true, backdrop: 'static'});
                var data = {
                    'id_ktg': idKtg,
                    'poli': poli,
                    'kelas': kelas,
                    'kamar': kamar,
                    'ket_selesai': ket_selesai,
                    'tgl_cekup': tgl_cekup,
                    'ket_cekup': ket_cekup,
                    'id_dokter': idDokter,
                    'cara': cara,
                    'pendamping': pendamping,
                    'tujuan': tujuan
                }
                var result = JSON.stringify(data);
                $('#save_laka').attr('onclick', 'saveDataAsuransi(\'' + result + '\')');
            }
        } else {
            $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + idDokter + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + cara + '\' , \'' + pendamping + '\', \'' + tujuan + '\')');
            $('#modal-confirm-dialog').modal('show');
        }
    }

    if (idKtg == "selesai") {
        if (ket_selesai != '') {
            if (isLaka == "Y") {
                if (noRujukan != '' && tglRujukan != '' && suratRujukan != '') {
                    $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + idDokter + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + cara + '\' , \'' + pendamping + '\', \'' + tujuan + '\')');
                    $('#modal-confirm-dialog').modal('show');
                } else {
                    $('#laka_no_polisi').val(noRujukan);
                    $('#laka_tgl_kejadian').val(noRujukan);
                    $('#laka_surat_rujukan').val(suratRujukan);
                    $('#modal-laka').modal({show: true, backdrop: 'static'});
                    var data = {
                        'id_ktg': idKtg,
                        'poli': poli,
                        'kelas': kelas,
                        'kamar': kamar,
                        'ket_selesai': ket_selesai,
                        'tgl_cekup': tgl_cekup,
                        'ket_cekup': ket_cekup,
                        'id_dokter': idDokter,
                        'cara': cara,
                        'pendamping': pendamping,
                        'tujuan': tujuan
                    }
                    var result = JSON.stringify(data);
                    $('#save_laka').attr('onclick', 'saveDataAsuransi(\'' + result + '\')');
                }
            } else {
                $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + idDokter + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + cara + '\' , \'' + pendamping + '\', \'' + tujuan + '\')');
                $('#modal-confirm-dialog').modal('show');
            }
        } else {
            $('#warning_ket').show().fadeOut(5000);
            $('#war_catatan').show();
        }
    }
}

function saveDataAsuransi(data) {
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

    if (noPolisi != '' && tglKejadian != '' && dataURL != '') {
        if(!cekSession()){
            $('#save_laka').hide();
            $('#load_laka').show();
            dwr.engine.setAsync(true);
            CheckupDetailAction.saveUpdateDataAsuransi(idDetailCheckup, noPolisi, tglKejadian, dataURL, function (response) {
                if (response.status == "success") {
                    $('#modal-laka').modal('hide');
                    $('#save_laka').show();
                    $('#load_laka').hide();
                    $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + idDokter + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + cara + '\' , \'' + pendamping + '\', \'' + tujuan + '\')');
                    $('#modal-confirm-dialog').modal('show');
                } else {
                    $('#warning_laka').show().fadeOut(5000);
                    $('#msg_laka').text(response.message);
                    $('#save_laka').show();
                    $('#load_laka').hide();
                }
            });
        }
    } else {
        $('#warning_laka').show().fadeOut(5000);
        $('#msg_laka').text("Silahkan cek kembali inputan anda...!");
        if (noPolisi == '') {
            $('#war_laka_no_polisi').show();
        }
        if (tglKejadian == '') {
            $('#war_laka_tgl_kejadian').show();
        }
        if (suratRujukan == '') {
            $('#war_laka_surat_polisi').show();
        }
    }
}

function saveKeterangan(idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, cara, pendamping, tujuan) {
    $('#modal-confirm-dialog').modal('hide');
    var jenisPasien = $('#id_jenis_pasien').val();
    $('#save_ket').hide();
    $('#load_ket').show();
    $('#waiting_dialog').dialog('open');

    var metodBayar = '<s:property value="rawatInap.metodePembayaran"/>';
    dwr.engine.setAsync(true);
    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, cara, pendamping, tujuan, "", metodBayar, "", metodBayar, "", "", function (response) {
        if (response.status == "success") {
            $('#waiting_dialog').dialog('close');
            $('#info_dialog').dialog('open');
            $('#close_pos').val(8);
            $('#save_ket').show();
            $('#load_ket').hide();
        } else {
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
        dwr.engine.setAsync(true);
        CheckupDetailAction.getListComboTindakan(idKtg, idKelasRuangan, null, {
            callback:function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idTindakan + "'>" + item.tindakan + "</option>";
                    });
                    $('#tin_id_tindakan').html(option);
                } else {
                    $('#tin_id_tindakan').html(option);
                }
            }
        });
    } else {
        $('#tin_id_tindakan').html(option);
    }
}

function listSelectTindakanKategori(val) {
    var option = "<option value=''>[Select One]</option>";
    var idDokter = "";
    var idPelayanan = "";
    var def = "";
    var isEdit = $('#is_edit').val();
    if (val != null && val != '') {
        var dataDokter = val.split("|");
        idDokter = dataDokter[0];
        idPelayanan = dataDokter[1];
        CheckupDetailAction.getListComboTindakanKategori(idPelayanan, kategoriRuangan, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    if(i == 0){
                        def = item.idKategoriTindakan;
                    }
                    option += "<option value='" + item.idKategoriTindakan + "'>" + item.kategoriTindakan + "</option>";
                });
                $('#tin_id_ketgori_tindakan').html(option);
                if(isEdit != "Y"){
                    setTimeout(function () {
                        $('#tin_id_ketgori_tindakan').val(def).trigger('change');
                    },100);
                }
            } else {
                $('#tin_id_ketgori_tindakan').html(option);
            }
        });
    }
}

function toContent() {
    var back = $('#close_pos').val();
    var idHref = $('#h_id_href').val();
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
        window.location.href = 'initForm_' + urlPage + '.action';
    } else if (back == 9) {
        desti = '#pos_rssep';
    } else if (back == 10) {
        desti = '#pos_icd9';
    } else if (back == 11) {
        desti = '#pos_selesai';
    } else if (back == 12) {
        window.location.reload(true);
    } else if (back == 13) {
        window.location.href = 'add_' + urlPage + '.action?id=' + idDetailCheckup + '&idx=' + idHref;
    } else if (back == 14) {
        desti = '#pos_rm';
    } else if (back == 15){
        desti = '#pos_pendamping';
    }

    $('html, body').animate({
        scrollTop: $(desti).offset().top
    }, 0);
}

function showModal(select) {

    var id = "";

    if (select == 1) {

        var tabel = $('#tbl_dokter').tableToJSON();
        var awal = "(";
        var akhir = ")";
        var isi = "";
        if (tabel.length > 0) {
            $.each(tabel, function (i, item) {
                if (isi != '') {
                    isi = isi + ', ' + "'" + tabel[i]["ID Dokter"] + "'";
                } else {
                    isi = "'" + tabel[i]["ID Dokter"] + "'";
                }
            });
        }
        if (isi != '') {
            isi = awal + isi + akhir;
        }
        listSelectDokter(isi);
        $('#dok_id_dokter').val('').trigger('change');
        $('#load_dokter, #warning_dokter, #war_dok').hide();
        $('#save_dokter').attr('onclick', 'saveDokter(\'' + id + '\')').show();
        $('#modal-dokter').modal({show: true, backdrop: 'static'});

    } else if (select == 2) {
        $('#h_harga, #h_diskon').val(null);
        $('#is_edit').val("N");
        $('#form_elektif').hide();
        getListNamaDokter('');
        $('#tin_id_ketgori_tindakan, #tin_id_tindakan, #tin_id_perawat').val('').trigger('change');
        $('#tin_qty').val('1');
        $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
        $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
        $('#modal-tindakan').modal({show: true, backdrop: 'static'});

    } else if (select == 3) {
        $('#nosa_id_diagnosa, #nosa_ket_diagnosa').val('');
        $('#nosa_id_diagnosa, #nosa_jenis_diagnosa').val('').trigger('change');
        $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
        $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
        $('#modal-diagnosa').modal({show: true, backdrop: 'static'});

    } else if (select == 4) {
        removePaint('ttd_dokter_pengirim');
        dokterDpjp();
        getJenisResep('select-jenis-pemeriksaan');
        $('#body_pemeriksaan').html('');
        $('#is_luar, #is_pending_lab').prop('checked', false);
        $('#is_luar, #is_pending_lab, #tarif_luar_lab').attr('disabled', false);
        $('#form_pending, #form_lab_luar, #form_tarif_lab_luar').hide();
        $('#form_lab_dalam').show();
        $('#lab_luar, #lab_parameter_luar').val('');
        $('#tarif_luar_lab, #h_total_tarif').val('');
        var par = $('.parameter_luar');
        $.each(par, function (i, item) {
            if (i != 0) {
                $('#par_' + i).remove();
            }
        });
        $('#form_is_luar').show();
        $('#form_is_pending').show();
        $('.jam').timepicker();
        $('.jam').inputmask('hh:mm', {'placeholder': 'hh:mm'});
        $('.tgl').datepicker({
            autoclose: true,
            changeMonth: true,
            changeYear: true,
            dateFormat: 'dd-mm-yy',
            minDate: new Date()
        });
        $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
        $('#form_ttd').show();
        $('#lab_kategori, #lab_lab, #select-jenis-pemeriksaan').attr('disabled', false);
        $('#lab_kategori, #lab_lab').val('').trigger('change');
        $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
        $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
        $('#modal-lab').modal({show: true, backdrop: 'static'});
        $("#btn-add-lab-luar").hide();
    } else if (select == 5) {
        $('.jam').timepicker();
        $('.jam').inputmask('hh:mm', {'placeholder': 'hh:mm'});
        $('.remove_cek').attr('checked', false);
        $('#untuk1').prop('checked', true);
        $('#body_add_diet').html('');
        getListComboJenisDiet();
        listMakanan();
        $('#bentuk_diet, #keterangan_diet').val('').trigger('change');
        $('#bentuk_diet, #keterangan_diet').val('').removeAttr('disabled');
        $('#save_diet').attr('onclick', 'saveDiet(\'' + id + '\')').show();
        $('#load_diet, #warning_diet, #war_bentuk_diet, #war_keterangan_diet').hide();
        $('#modal-diet').modal({show: true, backdrop: 'static'});
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
        $('#modal-obat').modal({show: true, backdrop: 'static'});
    } else if (select == 7) {
        resetAll();
        getJenisResep('select-jenis-resep');
        getApotekRawatInap;
        $('#resep_jenis_obat').val('').trigger('change');
        // $('#resep_apotek').val('').trigger('change').attr('disabled', false);
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
        $('#resep_jenis_obat').attr("onchange", "var warn =$('#war_jenis_pasien').is(':visible'); if (warn){$('#cor_jenis_pasien').show().fadeOut(3000);$('#war_jenis_pasien').hide()}; setObatPoli(this.value)");
        $('#resep_apotek').attr("onchange", "var warn =$('#war_rep_apotek').is(':visible'); if (warn){$('#cor_rep_apotek').show().fadeOut(3000);$('#war_rep_apotek').hide()}; setObatPoli()");
        $('#resep_nama_obat').attr("onchange", "var warn =$('#war_rep_obat').is(':visible'); if (warn){$('#cor_rep_obat').show().fadeOut(3000);$('#war_rep_obat').hide()}; setStokObatApotek(this,\'\')");
        $('#resep_nama_obat_serupa').attr("onchange", "var warn =$('#war_rep_obat_serupa').is(':visible'); if (warn){$('#cor_rep_obat_serupa').show().fadeOut(3000);$('#war_rep_obat_serupa').hide()}; setStokObatApotek(this, \'serupa\')");
        $('#body_detail').html('');
        $('#modal-resep-head').modal({show: true, backdrop: 'static'});

        var option = '<option value=""> - </option>';
        dwr.engine.setAsync(true);
        CheckupAction.getListJenisObat(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idJenisObat + '">' + item.namaJenisObat + '</option>';
                });
            }
            $('#resep_jenis_obat').html(option);
        });
    } else if (select == 8) {
        $('#id_icd9, #ket_icd9').val('');
        $('#load_icd9').hide();
        $('#save_icd9').attr('onclick', 'saveICD9(\'' + id + '\')').show();
        $('#modal-icd9').modal({show: true, backdrop: 'static'});
    }else if (select == 9) {
        $('#body_add_pendamping_makanan').html('');
        $('#nama_makanan').val('');
        $('#qty_makanan').val('1');
        $('#keterangan_makanan').val('');
        $('#load_makanan_pendamping').hide();
        $('#save_makanan_pendamping').attr('onclick', 'saveMakananPendamping()').show();
        $('#modal-makanan_pendamping').modal({show: true, backdrop: 'static'});
    }
}

function formatRupiah(angka) {
    var reverse = angka.toString().split('').reverse().join(''),
        ribuan = reverse.match(/\d{1,3}/g);
    ribuan = ribuan.join('.').split('').reverse().join('');
    return ribuan;
}

function getApotekRawatInap() {
    CheckupAction.getComboApotekList(function (res) {
        if (res.length == 1){
            $("#body-apotek").html("");
            var str = "";
            $.each(res, function (i, item) {
                str += "<input type='text' class='form-control' value='"+item.namaPelayanan+"' disabled/>" +
                    "<input type='hidden' id='resep_apotek' value='"+item.idPelayanan+"' />";
            });
            $("#body-apotek").html(str);
            setObatPoli();
        };
    })
}


function saveDokter(id) {
    var idDokter = $('#dok_id_dokter').val();
    var jenis = $('#dok_jenis_dpjp').val();
    var idDok = "";
    var pelayanan = "";
    if (idDokter != '') {
        var data = idDokter.split("|");
        idDok = data[0];
        pelayanan = data[1];
    }
    if (idDetailCheckup != '' && idDok != '' && pelayanan != '' && jenis != '') {
        if(!cekSession()){
            $('#save_dokter').hide();
            $('#load_dokter').show();
            if (id != '') {
                dwr.engine.setAsync(true);
                TeamDokterAction.editDokter(id, idDok, pelayanan, function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listDokter();
                        $('#modal-dokter').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                    } else {
                        $('#warning_dokter').show().fadeOut(5000);
                        $('#msg_dokter').text(response.msg);
                    }
                });
            } else {
                dwr.engine.setAsync(true);
                TeamDokterAction.saveDokterRequest(idDetailCheckup, idDok, pelayanan, jenis, function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listDokter();
                        $('#modal-dokter').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                        startInterval();
                    } else {
                        $('#warning_dokter').show().fadeOut(5000);
                        $('#msg_dokter').text(response.msg);
                    }
                });
            }
        }
    } else {
        $('#warning_dokter').show().fadeOut(5000);
        $('#msg_dokter').text('Silahkan cek kembali data inputan...!');
        if (idDokter == '') {
            $('#war_dok').show();
        }
        if (jenis == '') {
            $('#war_dok_jenis_dpjp').show();
        }
    }
}

function listDokter() {
    var table = "";
    var dokter = "";
    var cek = false;
    CheckupAction.getListDokterByIdDetailCheckup(idDetailCheckup, null, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                var jenis = "";
                var status = "";
                var btn = "";
                var label = "";

                if (item.jenisDpjp == "dpjp_1") {
                    jenis = "DPJP 1";
                } else if (item.jenisDpjp == "konsultasi") {
                    jenis = "Konsultasi";
                } else if (item.jenisDpjp == "rawat_bersama") {
                    jenis = "Rawat Bersama";
                } else if (item.jenisDpjp == "rawat_alih") {
                    jenis = "Rawat Alih";
                }
                if (item.flagApprove != null) {
                    if (item.flagApprove == "Y") {
                        if (item.jenisDpjp == "konsultasi") {
                            btn = '<img onclick="doneDokter(\'' + item.idTeamDokter + '\', \'' + item.idDokter + '\', \'' + item.namaDokter + '\')" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-power-off-button-25.png">';
                        }
                        status = "disetujui";
                        label = 'class="span-success"';
                    } else if (item.flagApprove == "N") {
                        btn = '<img onclick="detailApprove(\'' + item.idDokter + '\', \'' + item.namaDokter + '\', \'' + item.flagApprove + '\', \'' + item.keterangan + '\')" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-test-passed-25-orange.png">';
                        status = "ditolak";
                        label = 'class="span-danger"';
                    } else if (item.flagApprove == "S") {
                        status = "selesai";
                        label = 'class="span-primary"';
                    }
                } else {
                    status = "menunggu persetujuan";
                    label = 'class="span-warning"';
                    cek = true;
                }
                table += "<tr>" +
                    "<td>" + item.idDokter + '<input value="' + item.idDokter + '" type="hidden" id="dokter' + i + '">' + "</td>" +
                    "<td>" + item.namaDokter + "</td>" +
                    "<td>" + item.namaPelayanan + "</td>" +
                    "<td>" + jenis + "</td>" +
                    "<td align='center'>" + '<span ' + label + '>' + status + '</span>' + "</td>" +
                    "<td align='center'>" + btn + "</td>" +
                    "</tr>";
                dokter = item.idDokter;
            });
            if (cek) {
                startInterval();
            }
            $('#tin_id_dokter').val(dokter);
            $('#body_dokter').html(table);
        }
    });
}

function detailApprove(id, nama, sts, keterangan) {
    $('#det_id_dokter').text(id);
    $('#det_nama_dokter').text(nama);
    var status = '';
    var label = "";
    if (sts == "Y") {
        status = "disetujui";
        label = 'class="span-success"';
    } else if (sts == "N") {
        status = "ditolak";
        label = 'class="span-danger"';
    } else if (sts == "S") {
        status = "selesai";
        label = 'class="span-success"';
    }
    $('#det_status_dokter').text(status);
    $('#det_status_dokter').addClass(label);

    if (keterangan != 'null') {
        $('#det_keterangan_dokter').text(keterangan);
    }
    $('#modal-detail-dokter').modal({show: true, backdrop: 'static'});
}

function doneDokter(id, idDokter, nama) {
    $('#kon_id_dokter').text(idDokter);
    $('#kon_nama_dokter').text(nama);
    $('#save_kon').attr('onclick', 'saveDoneDokter(\'' + id + '\')');
    $('#modal-konsultasi-dokter').modal({show: true, backdrop: 'static'});
}

function saveDoneDokter(id) {
    if(!cekSession()){
        $('#save_kon').hide();
        $('#load_kon').show();
        dwr.engine.setAsync(true);
        TeamDokterAction.doneDokter(id, {
            callback: function (res) {
                if (res.status == "success") {
                    dwr.engine.setAsync(false);
                    $('#save_kon').show();
                    $('#load_kon').hide();
                    listDokter();
                    $('#modal-konsultasi-dokter').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(1);
                } else {
                    $('#save_kon').show();
                    $('#load_kon').hide();
                    $('#warning_kon_dokter').show().fadeOut(5000);
                    $('#msg_kon_dokter').text(response.msg);
                }
            }
        });
    }
}

function listDokterKeterangan(idPelayanan) {
    var idx = idPelayanan.selectedIndex;
    var idPoli = idPelayanan.options[idx].value;
    var option = "";
    CheckupAction.listOfDokter(idPoli, function (response) {
        option = "<option value=''> - </option>";
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
    var idDokter = $('#tin_id_dokter_dpjp').val();
    var qty = $('#tin_qty').val();
    var idJenisPeriksa = $('#id_jenis_pasien').val();
    var isElektif = $('#is_elektif').val();
    var qtyJam = $('#tin_qty_elektif').val();
    var qtyPerkali = 1;
    var idDok = "";
    var idPelayanan = "";
    if(isElektif == "Y"){
        qtyPerkali = qtyJam;
    }

    if (idDetailCheckup != '' && idTindakan != '' && idDokter && qty != '' && parseInt(qty) > 0 && idKategori != '' && qtyPerkali != '' && parseInt(qtyPerkali) > 0) {
        if(!cekSession()){
            qty = qty * qtyPerkali;
            $('#save_tindakan').hide();
            $('#load_tindakan').show();
            idDok = idDokter.split("|")[0];
            idPelayanan = idDokter.split("|")[1];

            if (id != '') {
                dwr.engine.setAsync(true);
                TindakanRawatAction.editTindakanRawat(id, idDetailCheckup, idTindakan, idDok, "RI", qty, idJenisPeriksa, idPelayanan, {
                    callback: function (response) {
                        if (response.status == "success") {
                            dwr.engine.setAsync(false);
                            listTindakan();
                            hitungStatusBiaya();
                            hitungCoverBiaya();
                            $('#modal-tindakan').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(2);
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                        } else {
                            $('#warning_tindakan').show().fadeOut(5000);
                            $('#msg_tindakan').text(response.msg);
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                        }
                    }
                });
            } else {
                dwr.engine.setAsync(true);
                TindakanRawatAction.saveTindakanRawat(idDetailCheckup, idTindakan, idDok, "RI", qty, idJenisPeriksa, idPelayanan, idRuangan, {
                    callback: function (response) {
                        if (response.status == "success") {
                            dwr.engine.setAsync(false);
                            listTindakan();
                            hitungStatusBiaya();
                            hitungCoverBiaya();
                            $('#modal-tindakan').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(2);
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                        } else {
                            $('#warning_tindakan').show().fadeOut(5000);
                            $('#msg_tindakan').text(response.msg);
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                        }
                    }
                });
            }
        }
    } else {
        $('#warning_tindakan').show().fadeOut(5000);
        $('#msg_tindakan').text('Silahkan cek kembali data inputan dan jumlah harus lebih dari 0..!');

        if (idKategori == '') {
            $('#war_kategori').show();
        }
        if (idTindakan == '') {
            $('#war_tindakan').show();
        }
        if (idDokter == '') {
            $('#war_dpjp').show();
        }
        if (qty <= 0 || qty == '') {
            $('#tin_qty').css('border', 'red solid 1px');
        }
        if (qtyJam <= 0 || qtyJam == '') {
            $('#tin_qty_elektif').css('border', 'red solid 1px');
        }
    }
}

function listDokterTindakan() {

    var idPelayanan = $("#id_pelayanan").val();
    var option = "<option value=''> - </option>";

    CheckupAction.listOfDokter(idPelayanan, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
            });
            $('#dokter_tindakan').html(option);
        } else {
            $('#dokter_tindakan').html(option);
        }
    });
}

function listTindakan() {

    var table = "";
    var data = [];
    var trfTtl = 0;
    TindakanRawatAction.listTindakanRawat(idDetailCheckup, function (response) {
        data = response;
        if (data.length > 0) {
            $.each(data, function (i, item) {

                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));
                var tarif = "-";
                var tarifTotal = "-";
                var trfTotal = 0;
                var qtyTotal = 0;
                var btn = '<img border="0" class="hvr-grow" onclick="editTindakan(\'' + item.idTindakanRawat + '\',\'' + item.idTindakan + '\',\'' + item.idKategoriTindakan + '\',\'' + item.idPerawat + '\',\'' + item.qty + '\', \'' + item.idDokter + '\', \'' + item.idPelayanan + '\')" src="' + pathImages + '/pages/images/icons8-create-25.png" style="cursor: pointer;">';

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

                if ("Y" == item.approveFlag) {
                    btn = "";
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + item.namaTindakan + "</td>" +
                    "<td>" + item.namaDokter + "</td>" +
                    "<td align='right'>" + tarif + "</td>" +
                    "<td align='center'>" + item.qty + "</td>" +
                    "<td align='right'>" + tarifTotal + "</td>" +
                    "<td align='center'>" + btn + "</td>" +
                    "</tr>";

            });
            table = table + "<tr>" +
                "<td colspan='5'>Total</td>" +
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

    if (idDetailCheckup != '' && idDiag != '' && jenisDiagnosa != '' && ketDiagnosa != '') {
        if(!cekSession()){
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
        if (data.length > 0) {
            $.each(data, function (i, item) {
                var id = "-";
                var ket = "-";
                var jen = "-";
                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));

                if (item.idDiagnosa != null) {
                    id = item.idDiagnosa;
                }

                var blink = "";
                if("B20" == id){
                    blink = 'class="blink_me_atas" style="color: red"';
                }

                if (item.keteranganDiagnosa != null) {
                    ket = item.keteranganDiagnosa;
                }
                if (item.jenisDiagnosa != null) {
                    if (item.jenisDiagnosa == "diagnosa_awal") {
                        jen = "Diagnosa Awal";
                    } else if (item.jenisDiagnosa == "diagnosa_utama") {
                        jen = "Diagnosa Utama";
                    } else {
                        jen = "Diagnosa Sekunder";
                    }
                }
                table += '<tr '+blink+'>' +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + id + "</td>" +
                    "<td>" + ket + "</td>" +
                    "<td>" + jen + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiagnosa(\'' + item.idDiagnosaRawat + '\',\'' + item.idDiagnosa + '\',\'' + item.jenisDiagnosa + '\', \'' + item.keteranganDiagnosa + '\')" src="' + pathImages + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
            });
        }
        $('#body_diagnosa').html(table);
    });
}

function listSelectLab(idx) {
    var option = "<option value=''> - </option>";
    if (idx != '') {
        LabAction.listLab(idx, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idLab + "'>" + item.namaLab + "</option>";
                });
                $('#lab_lab').html(option);
                $('#ckp_unit').html(option);
            } else {
                $('#lab_lab').html('');
                $('#ckp_unit').html('');
            }
        });
    } else {
        $('#lab_lab').html('');
        $('#ckp_unit').html('');
    }
}

var listPenunjang = [];
function showModalListPenunjang() {
    listPenunjang = [];
    var idKategori = $("#lab_kategori option:selected").val();
    if (idKategori != '') {
        LabAction.listLab(idKategori, function (response) {
            if (response.length > 0) {
                var str = "";
                $.each(response, function (i, item) {
                    str += "<tr>" +
                        "<td width='50px'><input type='checkbox' id='lab-"+item.idLab+"' value='"+item.idLab+"' class='expand' onclick=\"setCheck(\'"+item.idLab+"\',\'head\')\" /></td>" +
                        "<td><span style='font-weight: bold' id='label-lab-"+item.idLab+"'>"+item.namaLab+"</span></td>" +
                        "<td align='right' id='btn-expand-"+item.idLab+"'><span onclick=\"spanRow(\'"+item.idLab+"\')\" class='expand'><i class='fa fa-angle-down'></i> Expand</span></td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td style='display: none;' id='space-detail-"+item.idLab+"' colspan='3'></td>" +
                        "</tr>";
                });
                $('#body-list-penunjang').html(str);
            } else {
                $('#body-list-penunjang').html("<tr><td colspan='3' align='center'>Pemeriksaan Penunjang Belum Ada. / Belum Memilih Jenis Penunjang </td></tr>");
            }
        });
    } else {
        $('#body-list-penunjang').html("<tr><td colspan='3' align='center'>Pemeriksaan Penunjang Belum Ada. / Belum Memilih Jenis Penunjang </td></tr>");
    }
    $("#modal-list-penunjang").modal('show');
}

function listParameterPenunjang(idLab){
    LabDetailAction.listLabDetail(idLab, function (response) {
        var str = "<table class='table' style='font-size: 13px' width='100%'>";
        $.each(response, function(i, item){

            if ($('#lab-'+idLab).is(":checked")){
                str += "<tr>" +
                    "<td width='50px'><input type='checkbox' id='input-"+idLab+"-"+item.idLabDetail+"' value='"+item.idLabDetail+"' class='expand param-lab check-penunjang-"+idLab+"' onclick=\"setCheck(\'"+item.idLabDetail+"\',\'detail\',this.id)\" checked/></td>" +
                    "<td id='label-param-"+item.idLabDetail+"'>"+item.namaDetailPeriksa+"</td>" +
                    "</tr>";
            } else {
                str += "<tr>" +
                    "<td width='50px'><input type='checkbox'  id='input-"+idLab+"-"+item.idLabDetail+"' value='"+item.idLabDetail+"' class='expand param-lab check-penunjang-"+idLab+"' onclick=\"setCheck(\'"+item.idLabDetail+"\',\'detail\',this.id)\" /></td>" +
                    "<td id='label-param-"+item.idLabDetail+"'>"+item.namaDetailPeriksa+"</td>" +
                    "</tr>";
            }
            // str += "<tr>" +
            //     "<td width='50px'><input type='checkbox' value='"+item.idLabDetail+"' class='expand param-lab check-penunjang-"+idLab+"' onclick=\"setCheck(\'"+item.idLabDetail+"\',\'detail\')\" /></td>" +
            //     "<td>"+item.namaDetailPeriksa+"</td>" +
            //     "</tr>";
        });
        str += "</table>";
        $("#space-detail-"+idLab).html(str);
    });
}

function spanRow(idItem) {
    $("#space-detail-"+idItem).show();
    var str = "<span class='expand' onclick=\"unSpanRow(\'"+idItem+"\')\"><i class='fa fa-angle-up'></i> Unspand</span>";
    $("#btn-expand-"+idItem).html(str);
    listParameterPenunjang(idItem);
}

function unSpanRow(idItem) {
    $("#space-detail-"+idItem).hide();
    var str = "<span class='expand' onclick=\"spanRow(\'"+idItem+"\')\"><i class='fa fa-angle-down'></i> Expand</span>";
    $("#btn-expand-"+idItem).html(str);
}

function setCheck(id, tipe, idDetail){
    if (tipe == "head"){
        if ($('#lab-'+id).is(":checked")){
            spanRow(id);
        } else {
            $(".check-penunjang-"+id).removeAttr('checked');
        }
        saveListPenunjang(id);
    } else {
        var splitid = idDetail.split('-');
        var idLab = splitid[1];
        saveListPenunjang(idLab);
    }
}

function saveListPenunjang(id){

    var nama = $("#label-lab-"+id).text();
    if (listPenunjang.length == 0){
        listPenunjang.push({"idlab":id, "namalab":nama});
    } else {
        var found = false;
        $.each(listPenunjang, function(i, item){
            if(item.idlab == id){
                found = true;
            }
        });

        if (!found){
            listPenunjang.push({"idlab":id, "namalab":nama});
        }
    }
}

function saveListParam() {
    var param = $(".param-lab:checked");
    var str = "";
    var listParam = [];
    var listParamValue = [];
    for (var i = 0 ; i < param.length ; i++) {
        //console.log(param[i].value +" "+$("#label-param-"+param[i].value).text());
        var idparam = param[i].id;
        var idSplit = idparam.split('-')[1];
        var namaParam = $("#label-param-" + param[i].value).text();
        listParam.push({"iddetail": param[i].value, "namadetail": namaParam, "idlab": idSplit});
        listParamValue.push(param[i].value);
    }
    console.log(listPenunjang);
    console.log(listParam);
    console.log(listParamValue);
    var listOfPenunjang = [];
    $.each(listPenunjang, function (i, item) {
        var listDetail = listParam.filter(param => param.idlab == item.idlab);
        var idLab = item.idlab;
        var namaLab = item.namalab;
        var listNamaParam = "";
        var listIdParam = "";
        var listLiParam = "";
        if (listDetail.length > 0){
            $.each(listDetail, function (i, item) {
                listNamaParam = listNamaParam == "" ? item.namadetail : listNamaParam + "#" + item.namadetail;
                listIdParam = listIdParam == "" ? item.iddetail : listIdParam + "#" + item.iddetail;
                listLiParam += '<li>'+item.namadetail+'</li>';
            });
        }

        listOfPenunjang.push({
            "tempPemeriksaan" : namaLab,
            "tempIdPemeriksan" : idLab,
            "idParameter" : listParamValue,
            "tempIdParameter" : listIdParam,
            "tempParameter" : listNamaParam,
            "tempParameterLi" : listLiParam
        })
    });

    var count = $('#tabel_pemeriksaan').tableToJSON().length;
    var row = "";
    $.each(listOfPenunjang, function(i,item){
        row +=
            '<tr id="row_'+count+'">' +
            '<td>'+
            item.tempPemeriksaan +
            '<input type="hidden" class="nama_jenis_pemeriksaan" value="'+item.tempPemeriksaan+'">'+
            '<input type="hidden" class="id_jenis_pemeriksaan" value="'+item.tempIdPemeriksan+'">'+
            '<input type="hidden" class="nama_parameter_pemeriksaan" value="'+item.tempParameter+'">'+
            '<input type="hidden" class="id_parameter_pemeriksaan" value="'+item.tempIdParameter+'">'+
            '</td>'+
            '<td><ul style="margin-left: 20px">'+item.tempParameterLi+'</ul></td>'+
            '<td align="center"><img onclick="delPemeriksaan(\'row_'+count+'\')" style="cursor: pointer" src="'+contextPath+'/pages/images/cancel-flat-new.png" class="hvr-row"></td>'+
            '</tr>';
    });
    $('#body_pemeriksaan').append(row);
    $('#lab_kategori').attr('disabled', true);
    $('#lab_lab').val(null).trigger('change');
    $('#lab_parameter').val(null).trigger('change');
    $('#is_pending_lab').attr('disabled', true);
    $('#is_luar').attr('disabled', true);
    $('#tgl_pending').attr('disabled', true);
    $('#jam_pending').attr('disabled', true);
    $('#lab_parameter_luar').val(null);
    $('#lab_luar').val(null);
    $('#select-jenis-pemeriksaan').attr('disabled', true);
    var par = $('.parameter_luar');
    $.each(par, function (i, item) {
        if (i != 0) {
            $('#par_' + i).remove();
        }
    });
    $('#cek_luar').css('cursor', 'no-drop');
    $('#cek_pending').css('cursor', 'no-drop');
    $('#tarif_luar_lab').attr('disabled', true);
    $("#modal-list-penunjang").modal('hide');
}

function listSelectParameter(idLab) {
    if (idLab != null && idLab != '') {
        var option = "";
        LabDetailAction.listLabDetail(idLab, function (response) {
            if (response.length > 0) {
                var select = false;
                $.each(response, function (i, item) {
                    if("Y" == item.isPaket){
                        select = true;
                    }
                    option += "<option value='" + item.idLabDetail + "'>" + item.namaDetailPeriksa + "</option>";
                });
                $('#lab_parameter').html(option);
                $('#ckp_parameter').html(option);
                if(select){
                    $('#lab_parameter option').prop('selected', true);
                    $('#ckp_parameter option').prop('selected', true);
                }
            } else {
                $('#lab_parameter').html('');
                $('#ckp_parameter').html('');
                $('#lab_parameter option').prop('selected', false);
                $('#ckp_parameter option').prop('selected', false);
            }
        });
    } else {
        $('#lab_parameter').html('');
        $('#ckp_parameter').html('');
        $('#lab_parameter option').prop('selected', false);
        $('#ckp_parameter option').prop('selected', false);
    }
}

function saveLab(id) {
    var table = $('#tabel_pemeriksaan').tableToJSON().length;
    var cekLuar = $('#is_luar').is(':checked');
    var tarifLabLuar = $('#h_total_tarif').val();
    var isLuar = "N";
    var cekTarif = true;
    if(cekLuar){
        isLuar = "Y";
    }

    var canvas = document.getElementById('ttd_dokter_pengirim');
    var ttdPengirim = convertToDataURLAtas(canvas);
    var ttd = isBlank(canvas);
    var idKategori = $('#lab_kategori').val();
    var cekPending = $('#is_pending_lab').is(':checked');
    var tglPending = $('#tgl_pending').val();
    var jamPending = $('#jam_pending').val();
    var idDokter = $('#sip_pengirim').val();
    var namaDokter = $('#select_pengirim option:selected').text();

    var namaPemeriksaan = $('.nama_jenis_pemeriksaan');
    var idPemeriksaan = $('.id_jenis_pemeriksaan');
    var parameterPemeriksaan = $('.nama_parameter_pemeriksaan');
    var idParameter = $('.id_parameter_pemeriksaan');
    var jenisPemeriksaan = $('#select-jenis-pemeriksaan').val();

    if(table > 0){
        var saveCek = false;
        var waktu = "";
        if (cekPending) {
            if (tglPending && jamPending != '') {
                saveCek = true;
                waktu = tglPending.split("-").reverse().join("-") + " " + jamPending + ":00";
            }
        } else {
            saveCek = true;
        }
        if(saveCek){

            if(id != ''){
                ttd = false;
            }

            if(!ttd){
                var pemeriksan = [];
                $.each(namaPemeriksaan, function (i, item) {
                    if(item.value){
                        var tempItem = [];
                        if("N" == isLuar){
                            var tmep = parameterPemeriksaan[i].value;
                            var imep = idParameter[i].value;
                            if(tmep != '' && imep != ''){
                                var pp = tmep.split("#");
                                var mp = imep.split("#");
                                $.each(pp, function (i, item) {
                                    tempItem.push({
                                        'id_parameter': mp[i],
                                        'nama_parameter': item
                                    })
                                });
                            }
                        }else{
                            var tmep = parameterPemeriksaan[i].value;
                            if(tmep != ''){
                                var pp = tmep.split("#");
                                $.each(pp, function (i, item) {
                                    tempItem.push({
                                        'id_parameter': "",
                                        'nama_parameter': item
                                    })
                                });
                            }
                        }

                        pemeriksan.push({
                            'id_pemeriksaan': idPemeriksaan[i].value,
                            'nama_pemeriksaan': item.value,
                            'list_parameter': JSON.stringify(tempItem)
                        });
                    }
                });

                if(id != ''){
                    var data = {
                        'id_header_pemeriksaan': id,
                        'list_pemeriksaan': JSON.stringify(pemeriksan),
                        'is_luar': isLuar,
                        'id_kategori_lab': idKategori,
                        'jenis_pemeriksaan': jenisPemeriksaan,
                        'tarif_lab_luar': tarifLabLuar
                    }
                    var result = JSON.stringify(data);
                    $('#save_lab').hide();
                    $('#load_lab').show();
                    dwr.engine.setAsync(true);
                    PeriksaLabAction.editOrderLab(result, {
                        callback: function (response) {
                            if (response.status == "success") {
                                dwr.engine.setAsync(false);
                                listLab();
                                $('#modal-lab').modal('hide');
                                $('#info_dialog').dialog('open');
                                $('#close_pos').val(4);
                            } else {
                                $('#warning_lab').show().fadeOut(5000);
                                $('#msg_lab').text(response.msg);
                                $('#modal-lab').scrollTop(0);
                            }
                        }
                    });
                }else{
                    var data = {
                        'id_detail_checkup': idDetailCheckup,
                        'list_pemeriksaan': JSON.stringify(pemeriksan),
                        'is_luar': isLuar,
                        'id_dokter_pengirim': idDokter,
                        'nama_dokter_pengirim':namaDokter,
                        'id_kategori_lab': idKategori,
                        'waktu_pending': waktu,
                        'ttd_pengirim': ttdPengirim,
                        'jenis_pemeriksaan': jenisPemeriksaan,
                        'tarif_lab_luar': tarifLabLuar
                    }
                    var result = JSON.stringify(data);
                    $('#save_lab').hide();
                    $('#load_lab').show();
                    dwr.engine.setAsync(true);
                    PeriksaLabAction.saveOrderLab(result, {
                        callback: function (response) {
                            if (response.status == "success") {
                                dwr.engine.setAsync(false);
                                listLab();
                                $('#modal-lab').modal('hide');
                                $('#info_dialog').dialog('open');
                                $('#close_pos').val(4);
                                $('#save_lab').show();
                                $('#load_lab').hide();
                            } else {
                                $('#warning_lab').show().fadeOut(5000);
                                $('#msg_lab').text(response.msg);
                                $('#modal-lab').scrollTop(0);
                                $('#save_lab').show();
                                $('#load_lab').hide();
                            }
                        }
                    });
                }
            }else{
                $('#warning_lab').show().fadeOut(5000);
                $('#msg_lab').text("Silhakan cek tanda tangan dokter pengirim...!");
                $('#modal-lab').scrollTop(0);
            }
        }else{
            $('#warning_lab').show().fadeOut(5000);
            $('#msg_lab').text("Silhakan cek kembali tanggal dan jam inputan...!");
            $('#modal-lab').scrollTop(0);
            if (tglPending == '' || jamPending == '') {
                $('#war_pending').show();
            }
        }
    }else{
        $('#warning_lab').show().fadeOut(5000);
        $('#msg_lab').text("Silhakan cek kembali inputan berikut...!");
        $('#modal-lab').scrollTop(0);
    }
}

function listLab() {
    var table = "";
    var data = [];
    PeriksaLabAction.listOrderLab(idDetailCheckup, function (response) {
        data = response;
        if (data.length > 0) {
            $.each(data, function (i, item) {
                var pemeriksaan = "-";
                var status = "-";
                var lab = "-";
                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));
                var btn = '';
                var crn = '<img border="0" class="hvr-grow" onclick="detailLab(\'' + item.idHeaderPemeriksaan + '\',\'' + item.kategoriLabName + '\')" src="' + contextPath + '/pages/images/icons8-search-25.png" style="cursor: pointer;">';
                var tipe = "";
                var dalam = '';
                var luar = '';
                var hiddeDalam = "";
                var hiddeLuar = "";

                if (item.kategoriLabName == "Radiologi") {
                    tipe = "radiologi";
                } else {
                    tipe = "lab";
                }

                if (item.idLab != null) {
                    pemeriksaan = item.idLab;
                }

                if (item.statusPeriksaName != null) {
                    status = item.statusPeriksaName;
                }

                if (item.uploadDalam.length > 0) {
                    dalam = '<img border="0" class="hvr-grow" onclick="hasilUploadRI(\'' + item.idHeaderPemeriksaan + '\',\'dalam\',\'' + item.kategoriLabName + '\')" src="' + contextPath + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                    hiddeDalam = JSON.stringify(item.uploadDalam);
                }
                if (item.uploadLuar.length > 0) {
                    luar = '<img border="0" class="hvr-grow" onclick="hasilUploadRI(\'' + item.idHeaderPemeriksaan + '\',\'luar\',\'' + item.kategoriLabName + '\')" src="' + contextPath + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                    hiddeLuar = JSON.stringify(item.uploadLuar);
                }

                var periksa = "";
                if ("Y" == item.isPeriksaLuar) {
                    periksa = '<span class="span-warning">Periksa Luar</span>';
                }

                if ("Pending" == item.statusPeriksaName) {
                    btn = '<a target="_blank" href="printLabRadiologi_' + urlPage + '.action?id=' + idDetailCheckup + '&tipe=' + tipe + '&lab=' + item.idHeaderPemeriksaan + '"><img border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-print-25.png" style="cursor: pointer;"></a>';
                } else if ("Selesai" == item.statusPeriksaName) {
                    btn = '<a target="_blank" href="printLabRadiologi_' + urlPage + '.action?id=' + idDetailCheckup + '&tipe=' + tipe + '&lab=' + item.idHeaderPemeriksaan + '"><img border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-print-25.png" style="cursor: pointer;"></a>';
                } else if ("Antrian" == item.statusPeriksaName) {
                    btn = '<img border="0" class="hvr-grow" onclick="editLab(\'' + item.idHeaderPemeriksaan + '\', \'' + item.idKategoriLab + '\', \'' + item.isPeriksaLuar + '\', \'' + item.statusPeriksaName + '\', \'' + tanggal + '\', \''+item.jenisPeriksaPasien+'\', \''+item.tarifLabLuar+'\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">';
                }

                if ("paket_perusahaan" == jenisPeriksaPasien || "paket_individu" == jenisPeriksaPasien) {
                    table += "<tr>" +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + item.kategoriLabName + ' ' +periksa+ "</td>" +
                        // "<td>" + lab + "</td>" +
                        "<td>" + status + "</td>" +
                        "<td align='center'>" + btn + "</td>" +
                        "</tr>";
                } else {
                    table += "<tr>" +
                        "<td>" +
                        dateFormat +
                        '<textarea style="display: none" id="dalam_' + item.idHeaderPemeriksaan + '">' + hiddeDalam + '</textarea>' +
                        '<textarea style="display: none" id="luar_' + item.idHeaderPemeriksaan + '">' + hiddeLuar + '</textarea>' +
                        "</td>" +
                        "<td>" + item.kategoriLabName + ' ' +periksa+ "</td>" +
                        // "<td>" + lab + "</td>" +
                        "<td>" + status + "</td>" +
                        "<td align='center'>" + btn + dalam + luar + crn + "</td>" +
                        "</tr>";
                }
            });
            $('#body_lab').html(table);
        }
    });
}

function labLuar(kategori, url) {
    $('#title_lab_luar').text("Detail Hasil " + kategori + " Luar");
    $('#img_lab_luar').attr('src', url);
    $('#modal-lab_luar').modal({show: true, backdrop: 'static'});
}

function listSelectObat(select) {
    var idx = select.selectedIndex;
    var idJenis = select.options[idx].value;
    var option = "<option value=''> - </option>";
    var stok = "";

    if (idJenis != '') {
        ObatAction.listObat(idJenis, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idObat + "'>" + item.namaObat + "</option>";
                });
            }
        });
    }
    $('#ob_id_obat').html(option);
    $('#resep_nama_obat').html(option);
}

function detailLab(id, kategoriName) {
    var tempPemeriksaan = "";
    var tempPemeriksa = "";
    var tempDetail = "";
    var tempIdPemeriksa = "";
    var tempIdDetail = "";
    PeriksaLabAction.listParameterPemeriksaan(id, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                var namaPemeriksaan = "";
                var idPemeriksaan = "";
                var namaDetailPemeriksaan = "";
                var idDetailPemeriksaan = "";

                if(item.namaPemeriksaan != null){
                    namaPemeriksaan = item.namaPemeriksaan;
                }
                if(item.idPemeriksaan != null){
                    idPemeriksaan = item.idPemeriksaan;
                }
                if(item.namaDetailPemeriksaan != null){
                    namaDetailPemeriksaan = item.namaDetailPemeriksaan;
                }
                if(item.idDetailPemeriksaan != null){
                    idDetailPemeriksaan = item.idDetailPemeriksaan;
                }

                if(namaPemeriksaan.toLowerCase() != tempPemeriksaan){
                    tempPemeriksaan = item.namaPemeriksaan.toLowerCase();
                    if(tempPemeriksa != ''){
                        tempPemeriksa = tempPemeriksa+'='+namaPemeriksaan;
                        tempIdPemeriksa = tempIdPemeriksa+'='+idPemeriksaan;
                    }else{
                        tempPemeriksa = namaPemeriksaan;
                        tempIdPemeriksa = idPemeriksaan;
                    }
                }

                if(i == 0){
                    tempDetail = namaDetailPemeriksaan;
                    tempIdDetail = idDetailPemeriksaan;
                }else{
                    if(response[i - 1]["namaPemeriksaan"].toLowerCase() == tempPemeriksaan){
                        tempDetail = tempDetail+'#'+namaDetailPemeriksaan;
                        tempIdDetail = tempIdDetail+'#'+idDetailPemeriksaan;
                    }else{
                        tempDetail = tempDetail+'='+namaDetailPemeriksaan;
                        tempIdDetail = tempIdDetail+'='+idDetailPemeriksaan;
                    }
                }
            });

            if(tempPemeriksa != '' && tempDetail != '') {
                var templ = tempPemeriksa.split("=");
                var temp2 = tempDetail.split("=");
                var temp3 = tempIdPemeriksa.split("=");
                var temp4 = tempIdDetail.split("=");
                var row = "";
                $.each(templ, function (i, item) {
                    var tempParameter = temp2[i].split("#");
                    var tempParameterLi = "";
                    $.each(tempParameter, function (i, item) {
                        tempParameterLi += '<li>' + item + '</li>';
                    });

                    var pj = "";
                    if(i == 0){
                        pj = kategoriName;
                    }
                    row += '<tr id="row_' + i + '">' +
                        '<td>' + pj + '</td>' +
                        '<td>' + item + '</td>' +
                        '<td><ul style="margin-left: 20px">' + tempParameterLi + '</ul></td>' +
                        '</tr>';
                });
                if (row != '') {
                    $('#body_detail_lab').html(row);
                }
            }
        }else{
            $('#body_pemeriksaan').html('');
        }
    });
    $('#modal-detail_lab').modal({show: true, backdrop: 'static'});
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
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editObat(\'' + item.idObatInap + '\',\'' + id + '\',\'' + qty + '\',\'' + jenis + '\',\'' + obat + '\',\'' + item.qtyBox + '\',\'' + item.qtyLembar + '\',\'' + item.qtyBiji + '\',\'' + item.lembarPerBox + '\',\'' + item.bijiPerLembar + '\')" src="' + pathImages + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
            });
        }
        $('#body_obat').html(table);
    });
}

function saveDiet(id) {
    if (id != null && id != '') {
        if(!cekSession()){
            var bentukDiet = $('#edit_bentuk_diet').val();
            var jenisDiet = $('#edit_jenis_diet').val();
            if (bentukDiet && jenisDiet != '') {
                $('#edit_save_diet').hide();
                $('#edit_load_diet').show();
                dwr.engine.setAsync(true);
                OrderGiziAction.editOrderGizi(id, bentukDiet, jenisDiet, function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listDiet();
                        $('#modal-diet-edit').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                        $('#edit_save_diet').show();
                        $('#edit_load_diet').hide();
                    } else {
                        $('#edit_save_diet').show();
                        $('#edit_load_diet').hide();
                        $('#warning_edit_diet').show().fadeOut(5000);
                        $('#msg_edit_diet').text(response.message);
                    }
                });
            } else {
                $('#warning_edit_diet').show().fadeOut(5000);
                $('#msg_edit_diet').text("Silahkan cek kembali inputan anda...!");
                if (bentukDiet == '') {
                    $('#war_edit_bentuk_diet').show();
                }
                if (jenisDiet == '' || jenisDiet == null) {
                    $('#war_edit_jenis_diet').show();
                }
            }
        }
    } else {
        var data = [];
        var bentukDiet = $('#bentuk_diet').val();
        var jenisDiet = $('#jenis_diet').val();
        var ketData = $('[name=ket_diet]');
        var untukKapan = $('[name=untuk]:checked').val();
        var isSonde = $('#h_is_sonde').val();
        var jam = $('#jam_awal').val();
        var pemberian = $('#jumlah_pemberian').val();
        var satuan = $('#jumlah_satuan').val();

        var keteranganDiet = "";

        $.each(ketData, function (i, item) {
            if (item.checked) {
                keteranganDiet = 'cek';
            }
        });

        var table = $('#table_add_diet').tableToJSON();
        var tempWaktu = $('.waktu_diet');
        $.each(tempWaktu, function (i, item) {
            data.push({
                'id_detail_checkup': idDetailCheckup,
                'waktu': item.value,
                'id_diet_gizi': $('.bentuk_diet')[i].value,
                'id_jenis_diet': $('.jenis_diet')[i].value,
                'id_rawat_inap': idRawatInap
            });
        });

        var dataObj = {
            'is_sonde': isSonde,
            'keterangan': untukKapan
        }

        var cekcek = false;

        if(isSonde == "Y"){
            if(bentukDiet != '' && jenisDiet != '' != '' && table.length > 0){
                cekcek = true;
            }
        }else{
            if(bentukDiet != '' && keteranganDiet != '' && jenisDiet != '' != ''){
                cekcek = true;
            }
        }

        if (cekcek) {
            $('#save_diet').hide();
            $('#load_diet').show();
            dwr.engine.setAsync(true);
            var result = JSON.stringify(data);
            var dataResult = JSON.stringify(dataObj);
            OrderGiziAction.saveOrderGizi(result, dataResult, "RI", function (response) {
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
                    $('#modal-diet').scrollTop(0);
                }
            });
        } else {
            $('#warning_diet').show().fadeOut(5000);
            $('#msg_diet').text("Silahkan cek kembali inputan anda...!");
            $('#modal-diet').scrollTop(0);
            if (bentukDiet == '') {
                $('#war_bentuk_diet').show();
            }
            if (jenisDiet == '' || jenisDiet == null) {
                $('#war_jenis_diet').show();
            }
            if (keteranganDiet == '') {
                $('#war_ket_diet').show();
            }
            if (jam == '') {
                $('#war_jam_awal').show();
            }
            if (pemberian == '') {
                $('#war_jml_pemberian').show();
            }
            if (satuan == '') {
                $('#war_jml_pemberian').show();
            }
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
                var tanggal = item.tglOrder;
                var dateFormat = converterDate(new Date(tanggal));
                var label = "";
                var btn = "";

                if (item.diterimaFlag == "R") {
                    label = '<span class="span-danger"> dibatalkan</span>';
                } else {
                    if (item.approveFlag == "Y") {
                        btn = '<div class="input-group">' +
                            '<input placeholder="Scan Barcode & Konfirm Terima" class="form-control" onchange="cekBarcode(this.value, \'' + item.idOrderGizi + '\')">' +
                            '<div class="input-group-addon">' +
                            '<span id="status' + item.idOrderGizi + '"></span>' +
                            '</div>' +
                            '</div>';
                        label = '<span class="span-success"> telah dikonfirmasi</span>';
                    } else if(item.approveFlag == "N"){
                        label = '<span class="span-danger"> ditolak</span>';
                    } else {
                        btn = '<img border="0" class="hvr-grow" onclick="editDiet(\'' + item.idOrderGizi + '\',\'' + item.idDietGizi + '\',\'' + item.waktu + '\')" src="' + pathImages + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' +
                            '<img border="0" class="hvr-grow" onclick="cancelDiet(\'' + item.idOrderGizi + '\')" src="' + pathImages + '/pages/images/cancel-flat-new.png" style="cursor: pointer;">';
                        label = '<span class="span-warning"> menunggu konfirmasi</span>'
                    }

                    if (item.diterimaFlag == "Y") {
                        btn = '<div class="input-group">' +
                            '<input class="form-control" value="' + item.idOrderGizi + '" disabled>' +
                            '<div class="input-group-addon">' +
                            '<img src="' + pathImages + '/pages/images/icon_success.ico" style="height: 20px; width: 20px;">' +
                            '</div>' +
                            '</div>';
                        label = '<span class="span-success"> telah diterima</span>';
                    }
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + item.jenisDiet + "</td>" +
                    "<td>" + item.bentukDiet + "</td>" +
                    "<td>" + item.waktu + "</td>" +
                    "<td align='center'>" + label + "</td>" +
                    "<td align='center'>" + btn + "</td>" +
                    "</tr>"
            });
            $('#body_diet').html(table);
        }
    });
}

function cekBarcode(value, idOrderGizi) {

    if (value != '' && idOrderGizi != '') {

        if (value == idOrderGizi) {
            $('#status' + idOrderGizi).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            setTimeout(function () {
                OrderGiziAction.updateDiterimaFlag(idOrderGizi, function (response) {
                    if (response.status == "success") {
                        listDiet();
                    } else {
                        $('#status' + idOrderGizi).html('<img src="' + pathImages + '/pages/images/icon_failure.ico" style="height: 20px; width: 20px;">');
                    }
                });
            }, 200);
        } else {
            $('#status' + idOrderGizi).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            setTimeout(function () {
                $('#status' + idOrderGizi).html('<img src="' + pathImages + '/pages/images/icon_failure.ico" style="height: 20px; width: 20px;">');
            }, 200);
        }
    } else {
        $('#status' + idOrderGizi).html('');
    }
}

function editDokter(id, idDokter, idPelayanan) {
    listSelectDokter("");
    $('#load_dokter, #war_dok').hide();
    $('#save_dokter').attr('onclick', 'saveDokter(\'' + id + '\')').show();
    $('#dok_id_dokter').val(idDokter + '|' + idPelayanan).trigger('change');
    $('#modal-dokter').modal({show: true, backdrop: 'static'});
}

function editTindakan(id, idTindakan, idKategori, idPerawat, qty, idDokter, idPelayanan) {
    $('#is_edit').val('Y');
    $('#form_elektif').hide();
    getListNamaDokter('edit');
    $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
    $('#tin_id_dokter_dpjp').val(idDokter + '|' + idPelayanan).trigger('change');
    $('#tin_id_ketgori_tindakan').val(idKategori).trigger('change');
    $('#tin_id_tindakan').val(idTindakan).trigger('change');
    $('#tin_qty').val(qty);
    $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
    $('#modal-tindakan').modal({show: true, backdrop: 'static'});
}

function editDiagnosa(id, idDiagnosa, jenis, ket) {
    var jenisPasien = $('#id_jenis_pasien').val();
    $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
    $('#nosa_id_diagnosa').val(idDiagnosa);
    $('#nosa_ket_diagnosa').val(ket);
    $('#nosa_jenis_diagnosa').val(jenis).trigger('change');
    $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
    $('#modal-diagnosa').modal({show: true, backdrop: 'static'});
}

function editLab(id, idKategoriLab, isLuar, statusPeriksa, tanggal, jenisPasien, tarif) {
    getJenisResep('select-jenis-pemeriksaan');
    $('#form_ttd').hide();
    $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
    $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
    $('#lab_kategori').val(idKategoriLab).trigger('change').attr('disabled', true);
    $('#body_pemeriksaan').html('');
    $('#select-jenis-pemeriksaan').val(jenisPasien).attr('disabled', true);

    if ("Pending" == statusPeriksa) {
        $('#form_is_pending').show();
        var tgl = converterDate(new Date(tanggal));
        var time = converterTime(new Date(tanggal));
        $('#tgl_pending').val(tgl);
        $('#jam_pending').val(time);
    } else {
        $('#form_is_pending').hide();
        $('#tgl_pending').val('');
        $('#jam_pending').val('');
    }
    if ("Y" == isLuar) {
        $('#is_luar').attr('disabled', true);
        $('#is_luar').prop('checked', true);
        var par = $('.parameter_luar');
        $.each(par, function (i, item) {
            if (i != 0) {
                $('#par_' + i).remove();
            }
        });
        $('#form_is_luar').show();
        $('#form_lab_luar').show();
        $('#form_lab_dalam').hide();
        $('#form_tarif_lab_luar').show();
        $('#tarif_luar_lab').val(formatRupiahAtas2(tarif));
        $('#tarif_luar_lab').attr('disabled', false);
        $('#h_total_tarif').val(tarif);
    } else {
        $('#is_luar').attr('disabled', true);
        $('#is_luar').attr('checked', false);
        $('#form_is_luar').hide();
        $('#form_lab_luar').hide();
        $('#form_lab_dalam').show();
        $('#form_tarif_lab_luar').hide();
        $('#tarif_luar_lab, #h_total_tarif').val('');
    }

    var tempPemeriksaan = "";
    var tempPemeriksa = "";
    var tempDetail = "";
    var tempIdPemeriksa = "";
    var tempIdDetail = "";
    PeriksaLabAction.listParameterPemeriksaan(id, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                var namaPemeriksaan = "";
                var idPemeriksaan = "";
                var namaDetailPemeriksaan = "";
                var idDetailPemeriksaan = "";

                if(item.namaPemeriksaan != null){
                    namaPemeriksaan = item.namaPemeriksaan;
                }
                if(item.idPemeriksaan != null){
                    idPemeriksaan = item.idPemeriksaan;
                }
                if(item.namaDetailPemeriksaan != null){
                    namaDetailPemeriksaan = item.namaDetailPemeriksaan;
                }
                if(item.idDetailPemeriksaan != null){
                    idDetailPemeriksaan = item.idDetailPemeriksaan;
                }

                if(namaPemeriksaan.toLowerCase() != tempPemeriksaan){
                    tempPemeriksaan = item.namaPemeriksaan.toLowerCase();
                    if(tempPemeriksa != ''){
                        tempPemeriksa = tempPemeriksa+'='+namaPemeriksaan;
                        tempIdPemeriksa = tempIdPemeriksa+'='+idPemeriksaan;
                    }else{
                        tempPemeriksa = namaPemeriksaan;
                        tempIdPemeriksa = idPemeriksaan;
                    }
                }

                if(i == 0){
                    tempDetail = namaDetailPemeriksaan;
                    tempIdDetail = idDetailPemeriksaan;
                }else{
                    if(response[i - 1]["namaPemeriksaan"].toLowerCase() == tempPemeriksaan){
                        tempDetail = tempDetail+'#'+namaDetailPemeriksaan;
                        tempIdDetail = tempIdDetail+'#'+idDetailPemeriksaan;
                    }else{
                        tempDetail = tempDetail+'='+namaDetailPemeriksaan;
                        tempIdDetail = tempIdDetail+'='+idDetailPemeriksaan;
                    }
                }
            });

            if(tempPemeriksa != '' && tempDetail != ''){
                var templ = tempPemeriksa.split("=");
                var temp2 = tempDetail.split("=");
                var temp3 = tempIdPemeriksa.split("=");
                var temp4 = tempIdDetail.split("=");
                var row = "";
                $.each(templ, function (i, item) {
                    var tempParameter = temp2[i].split("#");
                    var tempParameterLi = "";
                    $.each(tempParameter, function (i, item) {
                        tempParameterLi += '<li>'+item+'</li>';
                    });
                    row += '<tr id="row_'+i+'">' +
                        '<td>' +
                        item +
                        '<input type="hidden" class="nama_jenis_pemeriksaan" value="'+item+'">'+
                        '<input type="hidden" class="id_jenis_pemeriksaan" value="'+temp3[i]+'">'+
                        '<input type="hidden" class="nama_parameter_pemeriksaan" value="'+temp2[i]+'">'+
                        '<input type="hidden" class="id_parameter_pemeriksaan" value="'+temp4[i]+'">'+
                        '</td>'+
                        '<td><ul style="margin-left: 20px">'+tempParameterLi+'</ul></td>'+
                        '<td align="center"><img onclick="delPemeriksaan(\'row_'+i+'\')" style="cursor: pointer" src="'+contextPath+'/pages/images/cancel-flat-new.png" class="hvr-row"></td>'+
                        '</tr>';
                });
                if(row != ''){
                    $('#body_pemeriksaan').html(row);
                }
            }
        }else{
            $('#body_pemeriksaan').html('');
        }
    });
    $('#modal-lab').modal({show: true, backdrop: 'static'});
}

function editDiet(id, idDietGizi, keterangan) {
    getListComboJenisDiet();
    var idJenis = [];
    RawatInapAction.getComboBoxOrderJenisGizi(id, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                idJenis.push(item.idJenisDiet);
            });
        }
    });
    $('#edit_jenis_diet').val(idJenis).trigger('change');
    $('#edit_load_diet, #warning_edit_jenis_diet, #war_edit_bentuk_diet, #war_edit_keterangan_diet').hide();
    $('#edit_bentuk_diet').val(idDietGizi).trigger('change');
    $('#edit_keterangan').val(keterangan).attr('disabled', 'true');
    $('#edit_save_diet').attr('onclick', 'saveDiet(\'' + id + '\')').show();
    $('#modal-diet-edit').modal({show: true, backdrop: 'static'});
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
    $('#modal-obat').modal({show: true, backdrop: 'static'});
}

function editRuangan(kelas, ruang, idRawatInap) {
    $('.ptr-tgl').datepicker({
        dateFormat: 'dd-mm-yy'
    });
    $('.ptr-tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
    $('.ptr-jam').timepicker();
    $('.ptr-jam').inputmask('hh:mm', {'placeholder': 'hh:mm'});
    getKelasKamar('rawat_inap');
    $('#load_ruangan, #war_ruangan_kelas, #war_ruangan_ruang').hide();
    $('#ruangan_kelas').val(kelas).trigger('change');
    $('#ruangan_ruang').val(ruang).trigger('change');
    $('#h_id_rawat_inap').val(idRawatInap);
    $('#h_id_kamar').val(ruang);
    $('#modal-ruangan').modal({show: true, backdrop: 'static'});
}

function listSelectObatEdit(select) {
    var idx = select.selectedIndex;
    var idJenis = select.options[idx].value;
    var option = "<option value=''> - </option>";
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
    CheckupDetailAction.getListRuanganByIdDetailCheckup(idDetailCheckup, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                if (idRawatInap == item.idRawatInap) {
                    $('#no_ruang').html(item.noRuangan);
                    $('#name_ruang').html(item.namaRangan + " [" + item.namaTempatTidur + "]");
                }
                var no = "";
                var name = "";
                var kelas = "";
                var status = "";
                var btn = "";
                var masuk = "";
                var keluar = "";
                var tt = "";

                if (item.tglMasuk != null) {
                    masuk = converterDateTime(new Date(item.tglMasuk));
                }
                if (item.tglKeluar != null) {
                    keluar = converterDateTime(new Date(item.tglKeluar));
                }
                if (item.noRuangan != null) {
                    no = item.noRuangan;
                }
                if (item.namaTempatTidur != null) {
                    tt = item.namaTempatTidur;
                }
                if (item.namaRangan != null) {
                    name = "[" + no + "]-" + item.namaRangan + "-[" + tt + "]";
                }
                if (item.kelasRuanganName != null) {
                    kelas = item.kelasRuanganName;
                }

                if (item.status == "1") {
                    status = "Diruangan";
                    if (item.keterangan == "pindah") {
                        btn = '';
                    } else {
                        btn = '<img border="0" class="hvr-grow" onclick="editRuangan(\'' + item.idKelasRuangan + '\',\'' + item.idTempatTidur + '\', \'' + item.idRawatInap + '\')" src="' + pathImages + '/pages/images/icons8-create-25.png" style="cursor: pointer;">';
                    }
                } else if (item.status == "3") {
                    status = "Selesai";
                } else if (item.status = "0") {
                    status = "Menunggu Konfirmasi";
                    if (item.keterangan == "new") {
                        var date1 = converterDate(new Date(item.tglMasuk));
                        var date2 = converterDate(new Date());
                        if (date1 == date2) {
                            btn = '<img border="0" class="hvr-grow" onclick="conUpdateRuangan(\'' + item.idRawatInap + '\')" src="' + pathImages + '/pages/images/icons8-power-off-button-25.png" style="cursor: pointer;">';
                        } else {
                            btn = '';
                        }
                    } else {
                        btn = '';
                    }
                }

                table += "<tr>" +
                    "<td>" + masuk + "</td>" +
                    "<td>" + keluar + "</td>" +
                    "<td>" + name + "</td>" +
                    "<td>" + kelas + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td align='center'>" +
                    '<input type="hidden" value="' + item.idRawatInap + '" id="id_rawat_inap_' + i + '">' +
                    '<input type="hidden" value="' + item.keterangan + '" id="ket_' + i + '">' +
                    '<input type="hidden" value="' + item.status + '" id="status_' + i + '">' +
                    btn + "</td>" +
                    "</tr>"
            });
            $('#body_ruangan').html(table);
        }
    });
}

function saveEditRuang() {

    var tanggalPindah = $('#tanggal_pindah').val();
    var jamPindah = $('#jam_pindah').val();
    var idKelas = $('#ruangan_kelas').val();
    var idRuang = $('#ruangan_ruang').val();
    var idRuangBefore = $('#h_id_kamar').val();
    var idRawatInap = $('#h_id_rawat_inap').val();

    if (idRuangBefore != idRuang) {
        if (idDetailCheckup != '' && idKelas != '' && idRuang != '' && tanggalPindah && jamPindah != '') {
            if(!cekSession()){
                var tgl = tanggalPindah.split("-").reverse().join("-");
                $('#save_ruang').hide();
                $('#load_ruang').show();
                dwr.engine.setAsync(true);
                CheckupDetailAction.saveUpdateRuangan(idRawatInap, idRuang, idDetailCheckup, tgl + " " + jamPindah + ":00", {
                    callback: function (response) {
                        if (response.status == "success") {
                            dwr.engine.setAsync(false);
                            $('#modal-ruangan').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(7);
                            // $('#h_id_href').val(response.msg);
                            $('#save_ruang').show();
                            $('#load_ruang').hide();
                            listRuanganInap();
                        } else {
                            $('#save_ruang').show();
                            $('#load_ruang').hide();
                            $('#warning_ruangan').show().fadeOut(5000);
                            $('#msg_ruangan').text(response.msg);
                        }
                    }
                });
            }
        } else {
            $('#warning_ruangan').show().fadeOut(5000);
            $('#msg_ruangan').text('Silahkan cek kembali data inputan..!');
            if (idKelas == '') {
                $('#war_ruangan_kelas').show();
            }
            if (idRuang == '') {
                $('#war_ruangan_ruang').show();
            }
        }
    } else {
        $('#warning_ruangan').show().fadeOut(5000);
        $('#msg_ruangan').text('Kamar yang anda pilih sama dengan yang sudah ditempatin..!');
    }
}

function conUpdateRuangan(idRawatInapNew) {
    var table = $('#tabel_ruangan').tableToJSON();
    var idRawatInapPending = "";
    var today = converterDate(new Date());
    var dateSet = "";
    $.each(table, function (i, item) {
        var status = $('#status_' + i).val();
        var keterangan = $('#ket_' + i).val();
        var idRawatInap = $('#id_rawat_inap_' + i).val();
        if (status == "1") {
            if (keterangan == "pindah") {
                idRawatInapPending = idRawatInap;
            }
        }
        if (status == "0") {
            if (keterangan == "new") {
                dateSet = table[i]["Tanggal Masuk"].split(" ")[0];
            }
        }

    });
    if (idRawatInapNew && idRawatInapPending != '') {
        if (today == dateSet) {
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'updateRuangan(\'' + idRawatInapNew + '\', \'' + idRawatInapPending + '\')');
        } else {
            $('#warning_update').show().fadeOut(5000);
            $('#msg_update').text("Tanggal Pindah Kamar " + dateSet + "...!");
        }
    } else {
        $('#warning_update').show().fadeOut(5000);
        $('#msg_update').text("Tidak menemukan ID rawat inap...!");
    }
}

function updateRuangan(idRawatInapNew, idRawatInapPending) {
    $('#modal-confirm-dialog').modal('hide');
    $('#waiting_dialog').dialog('open');
    dwr.engine.setAsync(true);
    CheckupDetailAction.savePindahRuangan(idRawatInapNew, idRawatInapPending, {
        callback: function (response) {
            if (response.status == "success") {
                dwr.engine.setAsync(false);
                $('#waiting_dialog').dialog('close');
                $('#info_dialog').dialog('open');
                $('#close_pos').val(13);
                $('#h_id_href').val(idRawatInapNew);
            } else {
                $('#waiting_dialog').dialog('close');
                $('#warning_update').show().fadeOut(5000);
                $('#msg_update').text(response.msg);
            }
        }
    });
}

function showFormCekup(select) {
    var idx = select.selectedIndex;
    var idKet = select.options[idx].value;
    if (idKet == "Checkup Ulang") {
        $('#form-cekup').show();
    } else {
        $('#form-cekup').hide();
    }
}

function addObatToList() {
    var obat = null;
    var flagSerupa = $("#flag-obat-serupa").val();
    if (flagSerupa == "Y") {
        obat = $("#resep_nama_obat_serupa").val();
    } else {
        obat = $('#resep_nama_obat').val();
    }
    var apotek = $('#resep_apotek').val();
    var qty = $('#resep_qty').val();
    var jenisSatuan = $('#resep_jenis_satuan').val();
    var jenisObat = $('#resep_jenis_obat').val();
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

    // var tableKeterangan = $('#table_keterangan').tableToJSON();
    var jenisResep = $("#select-jenis-resep").val();
    var flagKronis = $("#val-kronis").val();
    var hariKronis = "";
    var harga = "";
    var namaRacik = $('#nama_racik').val();
    var isRacik = false;

    if($('#racik_racik').is(':checked')){
        if(namaRacik != ''){
            isRacik = true;
        }
    }else{
        isRacik = true;
    }

    if (flagKronis == "Y") {
        hariKronis = $("#hari-kronis").val();
    }

    // if (obat && qty && apotek && jenisSatuan && isRacik && tableKeterangan.length > 0) {
    if (obat && qty && apotek && jenisSatuan && isRacik) {

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

        // var bodyKet = "";
        // var tempBodyKet = [];
        // var stringTempBodyKet = "";
        // var keteranganTemp = "";
        // var keteranganTempe = "";
        // $.each(tableKeterangan, function (i, item) {
        //     var idWaktu = $('#waktu_'+i).val();
        //     var namaWaktu = $('#nama_waktu_'+i).val();
        //     var idParam = $('#id_param_'+i).val();
        //     var namaParam = $('#nama_param_'+i).val();
        //
        //     bodyKet += '<tr>' +
        //         '<td>'+namaWaktu+'</td>'+
        //         '<td>'+namaParam+'</td>'+
        //         '</tr>';
        //
        //     if(keteranganTemp != ''){
        //         var nwt = "";
        //         if(namaWaktu != ''){
        //             nwt = '. '+namaWaktu+' : '+namaParam;
        //         }else{
        //             nwt = ', '+namaParam;
        //         }
        //         keteranganTemp = keteranganTemp+nwt;
        //     }else{
        //         keteranganTemp = namaWaktu+' : '+namaParam;
        //     }
        //
        //     if(keteranganTempe != ''){
        //         var nwt = "";
        //         if(namaWaktu != ''){
        //             nwt = '|'+idWaktu+'#'+namaWaktu+' : '+namaParam;
        //         }else{
        //             nwt = '. '+namaParam;
        //         }
        //         keteranganTempe = keteranganTempe+nwt;
        //     }else{
        //         keteranganTempe = idWaktu+'#'+namaWaktu+' : '+namaParam;
        //     }
        // });
        // if(keteranganTempe != ''){
        //     var sp = keteranganTempe.split('|');
        //     $.each(sp, function (i, item) {
        //         var data = item.split('#');
        //         tempBodyKet.push({
        //             'id_waktu':data[0],
        //             'keterangan':data[1]
        //         });
        //     });
        //     stringTempBodyKet = JSON.stringify(tempBodyKet);
        // }
        //
        // var ket = "";
        // if(bodyKet != ''){
        //     ket = '<table style="font-size: 10px" class="table table-bordered" id="tbl_keterangan_'+data.length+'">' +
        //         '<tbody>'+bodyKet+'</tbody>'+
        //         '</table>';
        // }

        if (parseInt(qty) <= parseInt(stok)) {
            $.each(data, function (i, item) {
                var idObatTable = $('#id_obat_'+i).val();
                if (idObatTable == id) {
                    cek = true;
                }
            });
            if (cek) {
                $('#warning_data_exits').show().fadeOut(5000);
            } else {
                var kronis = "";
                var hrKronis = "";
                if(flagKronis == 'Y'){
                    kronis = ' '+'<span style="font-size: 10px;\n' +
                        '        padding: 4px;\n' +
                        '        color: black;\n' +
                        '        background-color: #ffff00;\n' +
                        '        border-radius: 5px;">kronis</span>';

                    hrKronis = ' '+'<span style="font-size: 10px;\n' +
                        '        padding: 4px;\n' +
                        '        color: black;\n' +
                        '        background-color: #4d4dff;\n' +
                        '        border-radius: 5px;">'+hariKronis+' hari</span>';
                }
                var cicik = "";
                var namaCicik = "";
                var flagCicik = "";
                var nameRacik = "";
                var idRacik = "";
                if($('#racik_racik').is(':checked')){
                    flagCicik = "Y";
                    nameRacik = namaRacik;
                    idRacik = nameRacik.toLowerCase().replace(/[' ']/g, '_');
                    var warnaRacik = $('#color_racik').val();
                    cicik = ' '+'<span style="font-size: 10px;\n' +
                        '        padding: 4px;\n' +
                        '        color: white;\n' +
                        '        background-color: #f56954;\n' +
                        '        border-radius: 5px;">racik</span>';
                    namaCicik = '<span style="font-size: 10px;\n' +
                        '        padding: 4px;\n' +
                        '        color: white;\n' +
                        '        background-color: '+warnaRacik+';\n' +
                        '        border-radius: 5px;">'+namaRacik+'</span>'+' ';
                }
                var count = data.length;
                var totalHarga = parseInt(qty) * parseInt(harga);
                $('#resep_apotek').attr('disabled', true);
                $('#desti_apotek').html(namaPelayanan);
                var row = '<tr id=' + id + '>' +
                    '<td>'+ namaCicik + nama +cicik+ kronis +
                    '<input type="hidden" value="'+id+'" id="id_obat_'+count+'">'+
                    '<input type="hidden" value="'+qty+'" id="qty_'+count+'">'+
                    '<input type="hidden" value="'+jenisSatuan+'" id="jenis_satuan_'+count+'">'+
                    '<input type="hidden" value="'+jenisResep+'" id="jenis_resep_'+count+'">'+
                    '<input type="hidden" value="'+hariKronis+'" id="hari_kronis_'+count+'">'+
                    '<textarea style="display: none" type="hidden" id="keterangan_'+count+'"></textarea>'+
                    '<input type="hidden" id="keterangan_detail_'+count+'">'+
                    '<input type="hidden" value="'+flagCicik+'" id="is_racik_'+count+'">'+
                    '<input type="hidden" value="'+nameRacik+'" id="nama_racik_'+count+'">'+
                    '<input type="hidden" value="'+idRacik+'" id="id_racik_'+count+'">'+
                    '</td>' +
                    '<td align="center">' + qty +' ' +jenisSatuan+'</td>' +
                    '<td><div id="body_ket_'+ count +'"></div><br>' +
                    '<button class="btn btn-sm btn-warning" onclick="showModalKeterangan(\''+count+'\')">Tambah</button>' +
                    '<button class="btn btn-sm btn-danger" onclick="hapusKeterangan(\''+count+'\')">Hapus</button>' +
                    '</td>' +
                    '<td align="right">' + formatRupiah(totalHarga) + '</td>' +
                    '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\',\'' + totalHarga + '\')" class="hvr-grow" src="' + contextPath + '/pages/images/cancel-flat-new.png" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                    '</tr>';
                $('#body_detail').append(row);
                var total = $('#total_harga_obat').val();
                var tot = 0;
                if (total != "") {
                    tot = total.replace(/[.]/g, '');
                }
                var jumlah = parseInt(totalHarga) + parseInt(tot);
                $('#total_harga_obat').val(formatRupiah(jumlah));
                if(!$('#racik_racik').is(':checked')){
                    $('#body_keterangan').html('');
                }
                $('#waktu_param, #param_ket, #ket_param').val('').trigger('change');
            }
        } else {
            $('#warning_resep_head').show().fadeOut(5000);
            $('#msg_resep').text('Qty tidak boleh melebihi stok obat..!');
            $('#modal-resep-head').scrollTop(0);
        }

    } else {
        if (jenisObat == '' || jenisObat == null) {
            $('#war_jenis_obat').show();
        }
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
        if (tableKeterangan.length == 0) {
            $('#war_rep_cek_waktu').show();
        }
        if (!isRacik) {
            $('#war_nama_racik').show();
        }
        $('#warning_resep_head').show().fadeOut(5000);
        $('#msg_resep').text('Silahkan cek kembali data inputan!');
        $('#modal-resep-head').scrollTop(0);
    }
}

var indexObat = "";
var indexKet = "";
function showModalKeterangan(count) {
    $("#modal-keterangan").modal('show');
    var idObat = $("#id_obat_"+count).val();
    $("#body-keterangan-obat").html("");

    indexObat = count;

    ObatAction.KeteranganObatByIdObat(idObat, function (res) {

        if (res.length > 0){

            var table = "<table class='table' width='100%'><tbody>";
            var n = 0;
            var str = "";
            indexKet = n;
            var i = parseInt(n) + 1;
            var select = "<tr><td width='100px'><select id='ket-"+n+"' onchange='getChildKeterangan("+ i +")' class='form-control'><option> - </option>";
            $.each(res, function (i, item) {
                if (item.keterangan == "lainnya"){
                    str += "<td><textarea cols='3' rows='2' id='ket-"+n+"'></textarea></td>"
                }else{
                    str += "<option value='"+item.id+"'>"+item.keterangan+"</option>";
                }
            });

            n++;
            var endselect = "</select></td><td id='body-ket-"+i+"'></td></tr>";
            $("#body-keterangan-obat").html(table + select + str + endselect);
        }
    });
}

function getChildKeterangan(count) {
    indexKet = count;
    var n = parseInt(count);
    var nn = parseInt(n) - 1;
    var idParent = $("#ket-"+nn+" option:selected").val();

    ObatAction.getByParentKeteranganObat(idParent, function (res) {
        var str = "";
        var i = parseInt(n) + 1;
        var select = "<td><select id='ket-"+n+"' onchange='getChildKeterangan("+ i +")' class='form-control'>" +
            "<option> - </option>";
        $.each(res, function (i, item) {
            if (item.keterangan == "lainnya"){
                str += "<td><textarea cols='3' rows='2' id='ket-"+n+"'></textarea></td>"
            }else{
                str += "<option value='"+item.id+"'>"+item.keterangan+"</option>";
            }
        });
        // alert(str);
        var endselect = "</select></td><td id='body-ket-"+i+"'></td>";
        $("#body-ket-"+n).html( select + str + endselect);
    });
}

function saveKeteranganObat(){
    var n = parseInt(indexObat);
    var ket = parseInt(indexKet);

    var str = "";
    var id = "";
    for (var i = 0 ; i < ket ; i++){
        if (i == 0){
            id = $("#ket-"+i+" option:selected").val();
        }
        var keterangan = $("#ket-"+i+" option:selected").text();
        str += keterangan +". ";
    }

    var tempBodyKet = [];
    tempBodyKet.push({
        'id_waktu': id,
        'keterangan': str
    });

    var stTemp = JSON.stringify(tempBodyKet);
    $("#keterangan_"+n).text(stTemp);
    $("#keterangan_detail_"+n).val(str);

    str         = "<div>"+str+"</div>";
    var instr   = $("#body_ket_"+n).html();
    instr       = "<div>"+instr+"</div>";
    $("#body_ket_"+n).html(instr+str);
    $("#modal-keterangan").modal('hide');
}

function hapusKeterangan(count){
    $("#body_ket_"+count).text("");
}

function delRowObat(id, harga) {
    $('#' + id).remove();
    var total = $('#total_harga_obat').val();
    var tot = 0;
    if (total != "") {
        tot = total.replace(/[.]/g, '');
    }
    var jumlah = parseInt(tot) - parseInt(harga);
    $('#total_harga_obat').val(formatRupiah(jumlah));
}

function saveResepObatTtd() {
    var idDokter = $('#tin_id_dokter').val();
    var data = $('#tabel_rese_detail').tableToJSON();
    var canvas = document.getElementById('ttd_canvas');
    var dataURL = canvas.toDataURL("image/png"),
        dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
    var ttd = isBlank(canvas);
    if(data.length > 0 && !ttd) {
        $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
        $('#save_con').attr('onclick','saveResepObat()');
    } else {
        var msg = "";
        if(data.length == 0){
            msg = "Data obat, ";
        }
        if(ttd){
            msg = msg+ "TTD dokter";
        }
        $('#warning_resep_head').show().fadeOut(5000);
        $('#msg_resep').text("Silahkan cek "+msg+"...!");
        $('#modal-resep-head').scrollTop(0);
    }
}

function clearConvas() {
    var canvas = document.getElementById('ttd_canvas');
    const context = canvas.getContext('2d');
    context.clearRect(0, 0, canvas.width, canvas.height);
}

function saveResepObat() {
    $('#modal-confirm-dialog').modal('hide');
    var idDokter = $('#tin_id_dokter').val();
    var data = $('#tabel_rese_detail').tableToJSON();
    var apotek = $('#resep_apotek').val();
    var canvas = document.getElementById('ttd_canvas');
    var dataURL = canvas.toDataURL("image/png"),
        dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
    var ttd = isBlank(canvas);
    if (data.length > 0 && !ttd) {
        if(!cekSession()){
            var idPelayanan = apotek.split('|')[0];
            var namaPelayanan = apotek.split('|')[1];
            var dataObat = [];
            $.each(data, function (i, item) {
                var idObat = $('#id_obat_'+i).val();
                var qty = $('#qty_'+i).val();
                var jenisSatuan = $('#jenis_satuan_'+i).val();
                var keterangan = $('#keterangan_'+i).val();
                var ketDetail = $('#keterangan_detail_'+i).val();
                var jenisResep = $('#jenis_resep_'+i).val();
                var hariKronis = $('#hari_kronis_'+i).val();
                var isRacik = $('#is_racik_'+i).val();
                var namaRacik = $('#nama_racik_'+i).val();
                var idRacik = $('#id_racik_'+i).val();
                dataObat.push({
                    'id_obat':idObat,
                    'qty': qty,
                    'jenis_satuan': jenisSatuan,
                    'keterangan': ketDetail,
                    'keterangan_detail':keterangan,
                    'jenis_resep': jenisResep,
                    'hari_kronis': hariKronis,
                    'nama_racik': namaRacik,
                    'id_racik': idRacik,
                    'is_racik': isRacik
                });

            });
            var stringDataObat = JSON.stringify(dataObat);
            var dataObj = {
                'id_detail_checkup':idDetailCheckup,
                'id_pelayanan':idPoli,
                'id_dokter':idDokter,
                'id_pasien':idPasien,
                'id_apotek': idPelayanan,
                'ttd': dataURL,
                'data_obat':stringDataObat

            }
            var stringObj = JSON.stringify(dataObj);
            $('#save_resep_head').hide();
            $('#load_resep_head').show();
            dwr.engine.setAsync(true);
            PermintaanResepAction.saveResepPasien(stringObj, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(9);
                        $('#save_resep_head').show();
                        $('#load_resep_head').hide();
                        $('#modal-resep-head').modal('hide');
                        listResepPasien();
                    } else {
                        $('#warning_resep_head').show().fadeOut(5000);
                        $('#msg_resep').text(response.msg);
                        $('#save_resep_head').show();
                        $('#load_resep_head').hide();
                    }
                }
            });
        }
    } else {
        $('#warning_resep_head').show().fadeOut(5000);
        $('#msg_resep').text("Silahkan cek kembali data inputan anda..!");
        $('#modal-resep-head').scrollTop(0);
    }
}

function isBlank(canvas) {
    const blank = document.createElement("canvas");
    blank.width = canvas.width;
    blank.height = canvas.height;
    return canvas.toDataURL() === blank.toDataURL();
}


function listResepPasien() {

    var table = "";
    PermintaanResepAction.listResepPasien(idDetailCheckup, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                var idResep = "";
                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));
                var status = item.status;

                if (item.idPermintaanResep != null) {
                    idResep = item.idPermintaanResep;
                }
                if (status == "0") {
                    status = "Antrian";
                } else if (status == "1") {
                    status = "Proses";
                } else if (status == "3") {
                    status = "Selesai";
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + idResep + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td align='center'>" +
                    // '<img border="0" class="hvr-grow" onclick="detailResep(\'' + item.idApprovalObat + '\')" src="'+pathImages+'/pages/images/icons8-create-25.png" style="cursor: pointer;">' +
                    '<img onclick="printResep(\'' + idResep + '\')" class="hvr-grow" src="' + pathImages + '/pages/images/icons8-print-25.png" style="cursor: pointer;">' +
                    "</td>" +
                    "</tr>"
            });
        }
        $('#body_resep').html(table);
    });
}

function detailResep(id) {
    $('#modal-resep-detail').modal({show: true, backdrop: 'static'});
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
                    '<option value=""> - </option>' +
                    '<option value="2 x 1 /Hari">2 x 1 /Hari</option>' +
                    '<option value="3 x 1 /Hari">3 x 1 /Hari</option>' +
                    '</select>' + "</td>" +
                    "<td align='center'>" + '<img border="0" id=' + idObat + ' class="hvr-grow" onclick="editObatResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + qty + '\',\'' + ket + '\',\'' + namaObat + '\')" src="' + pathImages + '/pages/images/icons8-create-25.png" style="cursor: pointer; ">' +
                    '<img border="0" id=save' + idObat + ' class="hvr-grow" onclick="saveDetailResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + item.idApprovalObat + '\')" src="' + pathImages + '/pages/images/save_flat.png" style="cursor: pointer; height: 25px; width: 25px; display: none">' + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_detail_rep').html(table);
}

function editObatResep(id, idObat, qty, ket, namaObat) {

    if ($('#' + idObat).attr('src') == pathImages + '/pages/images/icons8-create-25.png') {
        var url = pathImages + '/pages/images/cnacel-flat.png"/>';
        $('#' + idObat).attr('src', url);
        $('#obat' + idObat).hide();
        $('#qty' + idObat).hide();
        $('#ket' + idObat).hide();

        $('#newObat' + idObat).show().val(namaObat);
        $('#newQty' + idObat).show().val(qty);
        $('#newKet' + idObat).show().val(ket);
        $('#save' + idObat).show();

    } else {
        var url = pathImages + '/pages/images/icons8-create-25.png';
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
        if(!cekSession()){
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
        }
    } else {
        $('#warning_detail').show().fadeOut(5000);
    }
}

function confirmSaveAllTindakan() {
    $('#modal-confirm-dialog').modal('show');
    $('#save_con').attr('onclick', 'saveAllTindakan()');
}

function saveAllTindakan() {
    var jenisPasien = $('#id_jenis_pasien').val();
    $('#modal-confirm-dialog').modal('hide');
    if(!cekSession()){
        $('#save_all').hide();
        $('#load_all').show();
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveApproveAllTindakanRawatJalan(idDetailCheckup, jenisPasien, {
            callback: function (response) {
                if (response.status == "success") {
                    $('#success_all').show().fadeOut(5000);
                    $('#msg_all_suc').text("Berhasil menyimpan semua tindakan...!");
                    $('#save_all').show();
                    $('#load_all').hide();
                    hitungStatusBiaya();
                    listTindakan();
                } else {
                    $('#warning_all').show().fadeOut(5000);
                    $('#msg_all_war').text(response.message);
                    $('#save_all').show();
                    $('#load_all').hide();
                }
            }
        });
    }
}

function showOtherInput(id) {
    var nilai = $("#" + id + "").val();
    if (nilai.toLowerCase() == "lain") {
        $("#ot_" + id + "").removeAttr('style');
    } else {
        $("#ot_" + id + "").hide();
        $("#ot_" + id + "").val("");
    }
}

function setObatPoli(jenis) {
    var poli = $('#resep_apotek').val();
    var option = "<option value=''> - </option>";
    var jenisPasien = $('#id_jenis_pasien').val();

    if (poli != '') {
        var idPel = poli.split('|')[0];
        var namePel = poli.split('|')[1];
        ObatPoliAction.getSelectOptionObatByPoli(idPel, jenisPasien, jenis, idDetailCheckup, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idObat + "|" + item.namaObat + "|" + item.qtyBox + "|" + item.qtyLembar + "|" + item.qtyBiji + "|" + item.lembarPerBox + "|" + item.bijiPerLembar + "|" + item.flagKronis + "|" + item.harga + "|" + item.idJenisObat + "'>" + item.namaObat + "</option>";
                });
                $('#resep_nama_obat').html(option);
            }
        });
    } else {
        $('#resep_nama_obat').html(option);
    }
}

function setStokObatApotek(select, tipe) {

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
            if (idObat.split('|')[9] != 'null' && idObat.split('|')[9] != '') {
                $('#resep_jenis_obat').select2('destroy');
                $('#resep_jenis_obat').val(idObat.split('|')[9]).select2();
            }

            var total = parseInt(qtyBiji) + (parseInt(qtyBox) * parseInt(lembarPerBox)) + (parseInt(qtyLembar) * parseInt(bijiPerLembar));

            if (flagKronis == "Y") {
                labelKronis(flagKronis);
                $("#form-hari").show();
            } else {
                labelKronis(flagKronis);
                $("#form-hari").hide();
            }

            if (tipe == "serupa") {
                $('#resep_stok_biji_serupa').val(total);
            } else {
                $('#resep_stok_biji').val(total);
            }

            $("#h-qty-default").val(bijiPerLembar);
            $('#resep_qty').val(parseInt(bijiPerLembar));
            $('#resep_keterangan').val('');
            $('#resep_qty').val(bijiPerLembar);
            $('#resep_jenis_satuan').val('biji').trigger('change');

            if (tipe != "serupa") {
                if (parseInt(total) == 0) {
                    $("#obat-serupa").show();
                    $("#flag-obat-serupa").val("Y");
                    $("#resep_nama_obat").prop("disabled", 'disabled');
                    $("#btn-reset-combo-obat").show();
                    setObatPoliSerupa();
                } else {
                    $("#obat-serupa").hide();
                    $("#flag-obat-serupa").val("N")
                }
            }

            ObatAction.getHeaderObatById(id, function (res) {
                if (res != null) {
                    getComboParameterObat(res.idSubJenis);
                    if (res.flagIsFormularium == "Y") {
                        $('#set_formula').text("Formularium");
                    } else {
                        $('#set_formula').text("Non Formularium");
                    }

                    if (res.flagParenteral == "Y") {
                        $('#set_teral').text("Parenteral");
                        // $('#set_noretal').text("Tidak");
                    } else {
                        $('#set_teral').text("Non Parenteral");
                        // $('#set_noretal').text("Ya");
                    }
                }
            });

            ObatAction.getListKandunganObat(idObat, function (res) {
                var body = "";
                if (res.length > 0){
                    $.each(res, function (i, item) {
                        body += '<li>'+item.satuanSediaan+'</li>';
                    });
                    $('#set_js').html('<ul>'+body+'</ul>');
                }
            });
        }
    }
}

function resetAll() {
    $('#resep_apotek').val('').trigger('change').attr('disabled', false);
    $('#resep_nama_obat, #resep_jenis_satuan, #param_ket, #ket_param, #waktu_param').val('').trigger('change');
    $('#resep_keterangan, #total_harga_obat, #nama_racik').val('');
    $('#resep_qty').val('');
    $('#resep_stok_box, #resep_stok_lembar, #resep_stok_biji').val('');
    $('#body_detail').html('');
    $('#desti_apotek').html('');
    $('#resep_jenis_obat').val('').trigger('change');
    $('#racik_racik').removeAttr('checked');
    $('#label-kronis').hide();
    $('#body_keterangan').html('');
    removePaint('ttd_canvas');
    $('#body_keterangan').html('');

    $('#set_js').html('');
    $('#set_formula').text('');
    $('#set_teral').text('');
    $('#set_noretal').text('');

    resetComboObat();
}

function labelKronis(flag) {
    if (flag == "Y") {
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
    if (name == "biji") {
        nilai = $("#h-qty-default").val();
    }
    $("#resep_qty").val(nilai);

}

function cancelDiet(id) {
    $('#modal-cancel-diet').modal({show: true, backdrop: 'static'});
    $('#save_cancel_diet').attr('onclick', 'saveCancelDiet(\'' + id + '\')');
}

function saveCancelDiet(id) {
    var ket = $('#keterangan_cancel').val();
    if (ket != '') {
        if(!cekSession()){
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
        }
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

    if (idDetailCheckup != '' && idIcd9 != '' && ketIcd9 != '') {
        if(!cekSession()){
            data = {
                'id_detail_checkup': idDetailCheckup,
                'jenis_pasien': jenisPasien,
                'id_icd9': idIcd9,
                'nama_icd9': ketIcd9,
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
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editICD9(\'' + item.idTindakanRawatIcd9 + '\',\'' + item.idIcd9 + '\',\'' + item.namaIcd9 + '\')" src="' + pathImages + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
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
    $('#modal-icd9').modal({show: true, backdrop: 'static'});
}

function searchDiagnosa(id) {
    var menus, mapped;
    $('#' + id).typeahead({
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
                var labelItem = item.idDiagnosa + '-' + item.descOfDiagnosa;
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

function searchICD9(id) {
    var menus, mapped;
    $('#' + id).typeahead({
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
                var labelItem = item.idIcd9 + '-' + item.namaIcd9;
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

function getListNamaDokter(tipe) {
    var option = '<option value=""> - </option>';
    var def = '';
    CheckupAction.getListDokterByIdDetailCheckup(idDetailCheckup, "Y", function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                if(i == 0){
                    def = item.idDokter+'|'+item.idPelayanan;
                }
                option += '<option value="' + item.idDokter + '|' + item.idPelayanan + '">' + item.namaDokter + ' - ' + item.namaPelayanan + '</option>';
            });
            $('#tin_id_dokter_dpjp').html(option);
            if(tipe != 'edit'){
                $('#tin_id_dokter_dpjp').val(def).trigger('change');
            }
        } else {
            $('#tin_id_dokter_dpjp').html(option);
        }
    });
}

function setObatPoliSerupa() {
    var jenisPasien = $('#jenis_pasien').val();
    var selObat = $("#resep_nama_obat").val();
    var selPelayanan = $("#resep_apotek").val();
    var idObat = selObat.split('|')[0];
    var idPelayanan = selPelayanan.split('|')[0];
    var option = "<option value=''> - </option>";
    ObatPoliAction.getSelectOptionObatByPoliKandunganSerupa(idPelayanan, jenisPasien, idObat, function (response) {
        if (response != null) {
            $.each(response, function (i, item) {
                option += "<option value='" + item.idObat + "|" + item.namaObat + "|" + item.qtyBox + "|" + item.qtyLembar + "|" + item.qtyBiji + "|" + item.lembarPerBox + "|" + item.bijiPerLembar + "|" + item.flagKronis + "|" + item.harga + "'>" + item.namaObat + "</option>";
            });
            $('#resep_nama_obat_serupa').html(option);
        }
    });
}

function isPemeriksaan(id, idTujuan) {
    var cek = $('#' + id).is(':checked');
    if('pemeriksaan_lab' == id){
        if (cek) {
            $('#' + idTujuan).show();
            $('#form_order_pemeriksaan').show();
        } else {
            $('#' + idTujuan).hide();
            $('#form_order_pemeriksaan').hide();
        }
    }else{
        if (cek) {
            $('#' + idTujuan).show();
        } else {
            $('#' + idTujuan).hide();
        }
    }
}

function getKamar(idKelas, kategori) {
    var option = '<option value=""> - </option>';
    dwr.engine.setAsync(true);
    CheckupDetailAction.listRuangan(idKelas, true, kategori, {
        callback: function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idTempatTidur + "'>[" + item.noRuangan + "]-" + item.namaRuangan + "-[" + item.namaTempatTidur + "]</option>";
                });
                $('#kamar').html(option);
            } else {
                $('#kamar').html(option);
            }
        }
    });
}

function getKelasKamar(kategori) {
    var option = '<option value=""> - </option>';
    CheckupDetailAction.getListKelasKamar(kategori, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                option += '<option value="' + item.idKelasRuangan + '">' + item.namaKelasRuangan + '</option>';
            });
            $('#kelas_kamar').html(option);
            $('#ruangan_kelas').html(option);
        } else {
            $('#kelas_kamar').html(option);
            $('#ruangan_kelas').html(option);
        }
    });
}


function confirmPemeriksaanPasien() {
    var data = "";
    var tindakLanjut = $('#keterangan').val();
    var catatan = $('#pesan_dokter').val();
    var keterangan = $('#ket_selesai').val();
    var ketRawatInap = $('#keterangan_rw').val();
    var idRuangan = $('#kamar').val();
    var rsRujukan = $('#rs_rujukan').val();
    var tglKontrol = $('#tgl_kontrol').val();
    var cek = false;

    if (tindakLanjut != '') {

        if (tindakLanjut == "rawat_inap") {
            if (ketRawatInap != '') {
                cek = true;
            }
        }

        if (tindakLanjut == "selesai") {
            if (keterangan != '') {
                cek = true;
            }
        }
        if (tindakLanjut == "rawat_intensif" || tindakLanjut == "rawat_isolasi" ||
            tindakLanjut == "kamar_operasi" || tindakLanjut == "ruang_bersalin" || tindakLanjut == "rr") {
            if (idRuangan != '') {
                cek = true;
            }
        }
        if (tindakLanjut == "rujuk_rs_lain") {
            if (rsRujukan != '') {
                cek = true;
            }
        }
        if (tindakLanjut == "kontrol_ulang") {
            if (tglKontrol != '') {
                cek = true;
            }
        }
        if (tindakLanjut == "lanjut_biaya") {
            cek = true;
        }
        if (tindakLanjut == "kembali_ke_inap") {
            cek = true;
        }
    } else {
        cek = false;
    }
    if (cek) {
        $('#save_con').attr('onclick', 'savePemeriksaanPasien()');
        $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
    } else {
        $('#warning_ket').show().fadeOut(5000);
        $('#warning_msg').text("Silahkan cek kembali data inputan anda..!");
    }
}

function savePemeriksaanPasien() {
    $('#modal-confirm-dialog').modal('hide');
    var data = "";
    var tindakLanjut = $('#keterangan').val();
    var catatan = $('#pesan_dokter').val();
    var keterangan = $('#ket_selesai option:selected').text();
    var ketRawatInap = $('#keterangan_rw').val();
    var rsRujukan = $('#rs_rujukan').val();
    var tglKontrol = $('#tgl_kontrol').val().split("-").reverse().join("-");
    var isPemeriksaan = $('#pemeriksaan_lab').is(':checked');
    var kategoriLab = $('#ckp_kategori').val();
    var isStay = $('#is_stay').is(':checked');
    var idRuangan = $('#kamar').val();
    var idRuanganLama = $('#id_ruangan_lama').val();
    var cek = false;
    var cekTindakan = $('#tabel_tindakan').tableToJSON();
    var idKeterangan = $('#ket_selesai').val();

    if (cekTindakan.length > 0) {
        if (tindakLanjut != '') {

            if (tindakLanjut == "rawat_inap") {
                if (ketRawatInap != '') {
                    data = {
                        'id_detail_checkup': idDetailCheckup,
                        'no_checkup': noCheckup,
                        'id_rawat_inap': idRawatInap,
                        'tindak_lanjut': tindakLanjut,
                        'keterangan': 'Rawat Inap, ' + ketRawatInap,
                        'catatan': catatan,
                        'id_ruangan': idRuangan,
                        'jenis_pasien': jenisPeriksaPasien,
                        'id_ruangan_lama': idRuanganLama
                    }
                    cek = true;
                }
            } else if (tindakLanjut == "rujuk_rs_lain") {
                if (rsRujukan != '') {
                    data = {
                        'id_detail_checkup': idDetailCheckup,
                        'no_checkup': noCheckup,
                        'id_rawat_inap': idRawatInap,
                        'tindak_lanjut': tindakLanjut,
                        'keterangan': 'Rujuk Ke RS ' + rsRujukan,
                        'catatan': catatan,
                        'jenis_pasien': jenisPeriksaPasien,
                        'rs_rujukan': rsRujukan,
                        'id_ruangan_lama': idRuanganLama
                    }
                    cek = true;
                }
            } else if (tindakLanjut == "kontrol_ulang") {
                if (tglKontrol != '') {
                    if (isPemeriksaan) {
                        var tabelOrderPemeriksaan = $('#tabel_order_pemeriksaan').tableToJSON();
                        var namaPemeriksaan = $('.nama_order_jenis_pemeriksaan');
                        var idPemeriksaan = $('.id_order_jenis_pemeriksaan');
                        var parameterPemeriksaan = $('.nama_order_parameter_pemeriksaan');
                        var idParameter = $('.id_order_parameter_pemeriksaan');

                        var pemeriksan = [];
                        $.each(namaPemeriksaan, function (i, item) {
                            if(item.value){
                                var tempItem = [];
                                var tmep = parameterPemeriksaan[i].value;
                                var imep = idParameter[i].value;
                                if(tmep != '' && imep != ''){
                                    var pp = tmep.split("#");
                                    var mp = imep.split("#");
                                    $.each(pp, function (i, item) {
                                        tempItem.push({
                                            'id_parameter': mp[i],
                                            'nama_parameter': item
                                        })
                                    });
                                }
                                pemeriksan.push({
                                    'id_pemeriksaan': idPemeriksaan[i].value,
                                    'nama_pemeriksaan': item.value,
                                    'list_parameter': JSON.stringify(tempItem)
                                });
                            }
                        });

                        if (tabelOrderPemeriksaan.length > 0) {
                            data = {
                                'id_detail_checkup': idDetailCheckup,
                                'no_checkup': noCheckup,
                                'id_rawat_inap': idRawatInap,
                                'tindak_lanjut': tindakLanjut,
                                'keterangan': 'Kontrol Ulang dengan Pemeriksaan Penunjang Medis',
                                'catatan': catatan,
                                'jenis_pasien': jenisPeriksaPasien,
                                'tgl_kontrol': tglKontrol,
                                'list_pemeriksaan': JSON.stringify(pemeriksan),
                                'id_kategori_lab': kategoriLab,
                                'is_order_lab': 'Y',
                                'id_ruangan_lama': idRuanganLama
                            }
                            cek = true;
                        }
                    } else {
                        data = {
                            'id_detail_checkup': idDetailCheckup,
                            'no_checkup': noCheckup,
                            'id_rawat_inap': idRawatInap,
                            'tindak_lanjut': tindakLanjut,
                            'keterangan': 'Kontrol Ulang',
                            'catatan': catatan,
                            'jenis_pasien': jenisPeriksaPasien,
                            'tgl_kontrol': tglKontrol,
                            'id_ruangan_lama': idRuanganLama
                        }
                        cek = true;
                    }
                }
            } else {
                var stay = "N";
                if (kategoriRuangan == 'rawat_inap') {
                    if (isStay) {
                        stay = "Y";
                    }
                } else {
                    if (stayRuangan != null && stayRuangan != '') {
                        stay = "R";
                    }
                }
                var ket = tindakLanjut.replace("_", " ");
                var ktr = convertSentenceCaseUp(ket);
                var meninggal = "";

                if("selesai" == tindakLanjut){
                    CheckupDetailAction.initKeteranganKeluar(idKeterangan, function (res) {
                        if("meninggal" == res.kategori){
                            meninggal = "Y";
                        }
                    });
                }

                data = {
                    'id_detail_checkup': idDetailCheckup,
                    'no_checkup': noCheckup,
                    'id_rawat_inap': idRawatInap,
                    'tindak_lanjut': tindakLanjut,
                    'keterangan': ktr,
                    'catatan': catatan,
                    'id_ruangan': idRuangan,
                    'is_stay': stay,
                    'jenis_pasien': jenisPeriksaPasien,
                    'id_ruangan_lama': idRuanganLama,
                    'is_meninggal': meninggal
                }
                cek = true;
            }
        }
    }

    if (cek) {
        if(!cekSession()){
            var result = JSON.stringify(data);
            $('#waiting_dialog').dialog('open');
            dwr.engine.setAsync(true);
            RawatInapAction.saveTindakLanjutRawatInap(result, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#waiting_dialog').dialog('close');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(8);
                        if('kontrol_ulang' == tindakLanjut){
                            window.open('printSuratKeterangan_rawatinap.action?id='+idDetailCheckup+'&tipe=KU', '_blank');
                        }
                        if('rujuk_rs_lain' == tindakLanjut){
                            window.open('printSuratKeterangan_rawatinap.action?id='+idDetailCheckup+'&tipe=RSL', '_blank');
                        }
                    } else {
                        $('#waiting_dialog').dialog('close');
                        $('#error_dialog').dialog('open');
                        $('#errorMessage').text(res.msg);
                    }
                }
            });
        }
    } else {
        $('#warning_ket').show().fadeOut(5000);
        $('#warning_msg').text("Silahkan cek kembali data inputan anda..!");
    }
}

function resetComboObat() {
    $("#resep_nama_obat").removeAttr("disabled");
    $("#btn-reset-combo-obat").hide();
    $("#obat-serupa").hide();
    $("#flag-obat-serupa").val("N");
    $("#resep_stok_biji_serupa").val("");
}

function getListRekamMedis(tipePelayanan, jenis, id) {
    var li = "";
    CheckupAction.getListRekammedisPasien(tipePelayanan, jenis, id, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                var cek = "";
                var tgl = "";
                var icons = '<i class="fa fa-file-o"></i>';
                var icons2 = '<i class="fa fa-print"></i>';
                var tol = "";
                var labelTerisi = "";
                var constan = 0;
                var terIsi = 0;
                var labelPrint = "";
                var terIsiPrint = "";
                var tolText = "";
                var enter = '';

                if (item.jumlahKategori != null) {
                    constan = item.jumlahKategori;
                }
                if (item.terisi != null && item.terisi != '') {
                    terIsi = item.terisi;
                    terIsiPrint = item.terisi;
                }

                if (constan == terIsi || parseInt(terIsi) > parseInt(constan)) {
                    var conver = "";
                    if (item.createdDate != null) {
                        conver = converterDate(new Date(item.createdDate));
                        tgl = '<label class="label label-success">' + conver + '</label>';
                        tol = 'class="box-rm"';
                        tolText = '<span class="box-rmtext">Tanggal mengisi ' + conver + '</span>';
                    }
                    icons = '<i class="fa fa-check" style="color: #449d44"></i>';
                    icons2 = '<i class="fa fa-check" style="color: #449d44"></i>';
                    enter = '<br>';
                }

                labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';
                labelPrint = '<span style="color: #367fa9; font-weight: bold">' + terIsiPrint + '</span>';

                if (item.keterangan == 'form') {
                    li += '<li ' + tol + '><a style="cursor: pointer" onclick="loadModalRM(\'' + item.jenis + '\', \''+item.function +'\', \''+item.parameter+'\', \''+item.idRekamMedisPasien+'\', \'Y\')">' + icons + item.namaRm + ' ' + labelTerisi + tolText + '</a></li>'+enter;
                } else if (item.keterangan == "surat") {
                    li += '<li ' + tol + '><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\',\'' + item.namaRm + '\')' + '">' + icons2 + item.namaRm + ' ' + labelPrint + tolText + '</a></li>'+enter;
                }
            });
            $('#asesmen_ri').html(li);
        }
    });
}

function printPernyataan(kode, idRm, flag, namaRm) {
    $('#tanya').text("Apakah anda yakin print ?");
    $('#print_form').text(namaRm);
    $('#save_con_rm').attr('onclick', 'printPernyataanRM(\'' + kode + '\', \'' + idRm + '\')');
    $('#modal-confirm-rm').modal('show');
}

function printPernyataanRM(kode, idRM) {
    window.open(contextPath + '/rekammedik/printSuratPernyataan_rekammedik?id=' + idDetailCheckup + '&tipe=' + kode + '&ids=' + idRM, '_blank');
    $('#modal-confirm-rm').modal('hide');
    $('#info_dialog').dialog('open');
    $('#close_pos').val(14);
}

function showUangMuka() {
    $('#h_uang_muka_ri, #uang_muka_ri').val('');
    $('#form-tambah-uang-muka').show();
    listUangMuka();
    var cek = $('#btn_uang_muka').hasClass("btn btn-warning");
    if (cek) {
        $('#cek_name').html("Tambah");
        $('#form-cek-uang').hide();
        $('#btn_uang_muka').removeClass("btn btn-warning").addClass("btn btn-success");
        $('#icon_uang_muka').removeClass("fa fa-times").addClass("fa fa-plus");
    }
    $('#modal-uang_muka').modal({show: true, backdrop: 'static'});
}

function listUangMuka() {
    var table = "";
    dwr.engine.setAsync(true);
    RawatInapAction.getListUangMuka(noCheckup, {
        callback: function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    var status = "belum bayar";
                    var color = 'style="padding: 4px; color: white; background-color: #d33724; border-radius: 5px"';
                    if (item.statusBayar != null && item.statusBayar != "N") {
                        status = "sudah bayar"
                        color = 'style="padding: 4px; color: white; background-color: #0F9E5E; border-radius: 5px"';
                    }
                    table += '<tr>' +
                        '<td>' + converterDateTime(item.createdDate) + '</td>' +
                        '<td>' + item.namaPelayanan + '</td>' +
                        '<td>' + item.id + '</td>' +
                        '<td align="right">' + formatRupiahAtas(item.jumlah) + '</td>' +
                        '<td align="center"><span ' + color + '>' + status + '</span></td>' +
                        '</tr>';
                });
                $('#body_uang_muka').html(table);
            }
        }
    });
}

function cekBtnUangMuka(id) {
    var cek = $('#' + id).hasClass("btn btn-success");
    if (cek) {
        $('#cek_name').html("Batal");
        $('#form-cek-uang').show();
        $('#' + id).removeClass("btn btn-success").addClass("btn btn-warning");
        $('#icon_uang_muka').removeClass("fa fa-plus").addClass("fa fa-times");
    } else {
        $('#cek_name').html("Tambah");
        $('#form-cek-uang').hide();
        $('#' + id).removeClass("btn btn-warning").addClass("btn btn-success");
        $('#icon_uang_muka').removeClass("fa fa-times").addClass("fa fa-plus");
    }
}

function conUangMuka() {
    var uangmuka = $('#h_uang_muka_ri').val();
    if (uangmuka != '' && uangmuka != null) {
        $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
        $('#save_con').attr('onclick', 'saveUangMuka()');
    } else {
        $('#war_uang_muka').show().fadeOut(5000);
        $('#msg_war_uang_muka').text("Silahkan cek kembali uang muka anda....!");
    }
}

function saveUangMuka() {
    if(!cekSession()){
        $('#modal-confirm-dialog').modal('hide');
        var uangmuka = $('#h_uang_muka_ri').val();
        $('#form-tambah-uang-muka').hide();
        $('#def_uang_muka').show();
        $('#msg_def_uang_muka').text("Data sedang disimpan........");
        dwr.engine.setAsync(true);
        RawatInapAction.saveUangMuka(idDetailCheckup, uangmuka, {
            callback: function (res) {
                if (res.status = "success") {
                    $('#form-tambah-uang-muka').show();
                    $('#def_uang_muka').hide();
                    $('#suc_uang_muka').show().fadeOut(5000);
                    $('#msg_suc_uang_muka').text("Data berhasil sudah disimpan...");
                    listUangMuka();
                    $('#cek_name').html("Tambah");
                    $('#form-cek-uang').hide();
                    $('#btn_uang_muka').removeClass("btn btn-warning").addClass("btn btn-success");
                    $('#icon_uang_muka').removeClass("fa fa-times").addClass("fa fa-plus");
                } else {
                    $('#form-tambah-uang-muka').show();
                    $('#def_uang_muka').hide();
                    $('#war_uang_muka').show().fadeOut(5000);
                    $('#msg_def_uang_muka').text(res.msg);
                }
            }
        });
    }
}

function refreshTable(id, tipe) {
    $('#' + id).addClass("fa-spin");
    setTimeout(function () {
        if ("dokter" == tipe) {
            listDokter();
            stopInterval();
        }
        if ("gizi" == tipe) {
            listDiet();
        }
        if ("lab" == tipe) {
            listLab();
        }
        if ("resep" == tipe) {
            listResepPasien();
        }
        if ("pendamping" == tipe) {
            listMakananPendamping();
        }
        $('#' + id).removeClass("fa-spin");
    }, 500);
}

function startInterval() {
    setNotif = setInterval(cekDokter, 5000);
}

function stopInterval() {
    clearInterval(setNotif);
    $('#notif_dok').hide();
}

function cekDokter() {
    RawatInapAction.cekDokterApporve(idDetailCheckup, function (res) {
        var cek = true;
        if (res.length > 0) {
            $.each(res, function (i, item) {
                if (item.flagApprove == null) {
                    cek = false;
                }
            });
        }
        if (cek) {
            $('#notif_dok').show();
        }
    });
}

function setDiet(id) {
    var bentuk = $('#bentuk_diet').val();
    var jenis = $('#jenis_diet').val();
    var bentukText = $('#bentuk_diet option:selected').text();
    var jenisText = $('#jenis_diet option:selected');

    var tempText = "";
    var tempTextLi = "";
    var tempJenis = "";

    if(jenisText.length > 0){
        $.each(jenisText, function (i, item) {
            if(tempText != ''){
                tempText = tempText+', '+item.innerText;
            }else{
                tempText = item.innerText;
            }
            tempTextLi += '<li>'+item.innerText+'</li>';
        });
    }

    if(jenis != null){
        $.each(jenis, function (i, item) {
            if(tempJenis != ''){
                tempJenis = tempJenis+'#'+item;
            }else{
                tempJenis = item;
            }
        });
    }

    var ket = $('#' + id).val();
    var keterangan = "";
    if ("pagi" == ket) {
        keterangan = "Pagi";
    }
    if ("siang" == ket) {
        keterangan = "Siang";
    }
    if ("malam" == ket) {
        keterangan = "Malam";
    }
    var table = "";
    var idCount = $('#table_add_diet').tableToJSON().length;
    if($('#'+id).is(':checked')){
        if (bentuk && jenis != '') {
            table = '<tr id="' + ket + '">' +
                '<td>' + keterangan + '</td>' +
                '<td>' + '<ul style="margin-left: 10px">'+tempTextLi+'</ul>' + '</td>' +
                '<td>' + bentukText +
                '<input type="hidden" class="waktu_diet" value="' + ket + '" id="waktu_' + idCount + '">' +
                '<input type="hidden" class="jenis_diet" value="' + tempJenis + '" id="jenis_' + idCount + '">'+
                '<input type="hidden" class="bentuk_diet" value="' + bentuk + '" id="bentuk_' + idCount + '">' +
                '<input type="hidden" class="bentuk_text_diet" value="' + bentukText + '" id="bentuk_text_' + idCount + '">' +
                '</td>' +
                '</tr>';
            $('#body_add_diet').append(table).hide().show('slow');
        } else {
            $('#warning_diet').show().fadeOut(5000);
            $('#msg_diet').text("Silahkan cek kembali inputan anda...!");
            if (bentuk == '') {
                $('#war_bentuk_diet').show();
            }
            if (jenis == '' || jenis == null) {
                $('#war_jenis_diet').show();
            }
            $('#' + id).prop('checked', false);
        }
    }else{
        $('#'+ket).remove();
    }
}

function getListComboJenisDiet() {
    var option = '';
    RawatInapAction.getComboBoxJenisGizi(function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                option += '<option value="' + item.idJenisDiet + '">' + item.namaJenisDiet + '</option>'
            });
            $('#jenis_diet').html(option);
            $('#edit_jenis_diet').html(option);
        }
    });
}

function setDiskonHarga(id) {
    if (id != '') {
        TindakanAction.initTindakan(id, function (res) {
            if (res.idTindakan != '') {
                var disk = 0;
                if (res.diskon != '' && res.diskon != null) {
                    disk = res.diskon;
                }
                if (jenisPeriksaPasien == "bpjs") {
                    $('#h_harga').val("Rp. " + formatRupiahAtas(res.tarifBpjs));
                } else if (jenisPeriksaPasien == "bpjs_rekanan" || jenisPeriksaPasien == "rekanan"){

                    TindakanRawatAction.getTarifDetailRekanaOps(idDetailCheckup, id, function (res2) {

                        if (jenisPeriksaPasien == "bpjs_rekanan"){
                            disk = res2.diskonBpjs;
                            $('#h_harga').val("Rp. " + formatRupiahAtas(res2.tarifBpjs));
                        }

                        if (jenisPeriksaPasien == "rekanan"){
                            disk = res2.diskonNonBpjs;
                            $('#h_harga').val("Rp. " + formatRupiahAtas(res2.tarif));
                        }
                    });

                } else {
                    $('#h_harga').val("Rp. " + formatRupiahAtas(res.tarif));
                }
                $('#h_diskon').val(disk);
                if("Y" == res.isElektif){
                    $('#form_elektif').show();
                    $('#is_elektif').val("Y");
                }else{
                    $('#form_elektif').hide();
                    $('#is_elektif').val("N");
                }
            }
        });
    }
}

function cekRacik(id){
    if($('#'+id).is(':checked')){
        $('#form-nama-racik').show();
    }else{
        $('#form-nama-racik').hide();
        $('#nama_racik').val('');
    }
}

function addKeterangan() {
    var table = $('#table_keterangan').tableToJSON();
    var id = table.length;
    var last = id - 1;
    var namaWaktu = $('#waktu_param option:selected').text();
    var namaParam = $('#param_ket option:selected').text();
    var waktu = $('#waktu_param').val();
    var param = $('#param_ket').val();
    var ket = $('#ket_param').val();
    var cek = false;
    if(namaWaktu || param != '' || ket != null){
        var disKet = "";
        $.each(ket, function (i, item) {
            var id = item.split('|')[0];
            var nam = item.split('|')[1];
            if(disKet != ''){
                disKet = disKet+', '+nam;
            }else{
                disKet = nam;
            }
        });
        if(id > 0){
            $.each(table, function (i, item) {
                var idP = $('#id_param_'+i).val();
                var wkt = $('#waktu_'+i).val();
                if(idP != ''){
                    if(idP == param && wkt == waktu){
                        cek = true;
                    }
                }
            });
        }
        var set = namaParam +' : '+disKet;
        if(cek){
            $('#w_keterangan').show().fadeOut(5000);
            $('#p_keterangan').text("Keterangan "+namaParam+" sudah ada dalam list...!");
        }else{
            var cekNama = namaWaktu;
            if(id > 0){
                if($('#waktu_'+last).val() == waktu){
                    cekNama = "";
                }
            }
            var body = '<tr id="'+param+'" style="height: 2px">' +
                '<td>' + cekNama +
                '<input type="hidden" id="waktu_'+id+'" value="'+waktu+'">'+
                '<input type="hidden" id="nama_waktu_'+id+'" value="'+cekNama+'">'+
                '<input type="hidden" id="id_param_'+id+'" value="'+param+'">'+
                '<input type="hidden" id="nama_param_'+id+'" value="'+set+'">'+
                '</td>'+
                '<td>'+set+'</td>'+
                '<td align="center"><img onclick="delKet(\'' + param + '\')" class="hvr-grow" src="' + contextPathHeader + '/pages/images/cancel-flat-new.png" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                '</tr>';
            $('#body_keterangan').append(body);
            inputWarning('war_rep_cek_waktu','cor_rep_cek_waktu');
        }
    }else{
        $('#w_keterangan').show().fadeOut(5000);
        $('#p_keterangan').text("Silahkan cek inputan parameter dan keterangan...!");
    }
}

function getComboParameterObat(idJenis){
    ObatAction.getComboParameterObat(idJenis, function (res) {
        var option = '<option value=""> - </option>';
        if(res.length > 0){
            $.each(res, function (i, item) {
                option += '<option value="'+item.id+'">'+item.nama+'</option>';
            });
        }
        $('#param_ket').html(option);
    });
    getComboWaktuObat(idJenis);
}

function getComboWaktuObat(idJenis){
    ObatAction.getComboParameterWaktuObat(idJenis, function (res) {
        var option = '<option value=""> - </option>';
        if(res.length > 0){
            $.each(res, function (i, item) {
                option += '<option value="'+item.id+'">'+item.keterangan+'</option>';
            });
            $('#waktu_param').html(option);
        }
    });
}

function getComboKeteranganObat(idParam){
    ObatAction.getComboKeteranganObat(idParam, function (res) {
        var option = '';
        if(res.length > 0){
            $.each(res, function (i, item) {
                option += '<option value="'+item.id+'|'+item.keterangan+'">'+item.keterangan+'</option>';
            });
        }
        $('#ket_param').html(option);
    });
}

function refreshKeterangan(param, set){
    var temp = "";
    var cek = $('#text_area_keterangan option');
    if(cek.length > 0){
        $.each(cek, function (i, item) {
            if(item.selected){
                var id = item.value.split('|')[0];
                var nama = item.value.split('|')[1];
                temp += '<option value="'+id+'|'+nama+'">'+nama+'</option>';
            }
        });
        if(param && set != ''){
            temp = temp + '<option value="'+param+'|'+set+'">'+set+'</option>';
        }
    }else{
        if(param && set != ''){
            temp = '<option value="'+param+'|'+set+'">'+set+'</option>';
        }
    }
    $('#text_area_keterangan').html(temp);
    if(temp != ''){
        $('#text_area_keterangan option').prop('selected', true);
    }
    $('#param_ket').val('').trigger('change');
}

function isLuar(id) {
    var cek = $('#' + id).is(':checked');
    if (cek) {
        $('#form_lab_luar, #form_tarif_lab_luar').show();
        $('#form_lab_dalam').hide();
        $('#tarif_luar_lab, #h_total_tarif').val('');
    } else {
        $('#form_lab_luar, #form_tarif_lab_luar').hide();
        $('#form_lab_dalam').show();
        $('#tarif_luar_lab, #h_total_tarif').val('');
    }
}

function addParameter(){
    var par = $('.parameter_luar');
    var count = par.length;
    var idROw = 'par_'+count;
    var set = '<div class="input-group" style="margin-top: 7px;" id="'+idROw+'">\n' +
        '<input id="lab_parameter_luar_'+count+'" class="form-control parameter_luar"\n' +
        '       placeholder="masukkan parameter '+(parseInt(count)+parseInt(1))+'">\n' +
        '<div onclick="delParamrs(\''+idROw+'\')" class="input-group-addon" style="background-color: #a94442; color: white; cursor: pointer">\n' +
        '    <i class="fa fa-trash"></i>\n' +
        '</div>\n' +
        '</div>';
    $('#params_luar').append(set);
    $('#lab_parameter_luar_'+count).focus();
}

function delParamrs(id){
    $('#'+id).remove();
}


function hasilUploadRI(id, tipe, kategori){
    var data = $('#'+tipe+'_'+id).val();
    $('#item_hasil_lab').html('');
    $('#li_hasil_lab').html('');
    if(data != null && data != ''){
        var result = JSON.parse(data);
        if(tipe == 'dalam'){
            $('#title_hasil_lab').html("Hasil Pemeriksaan "+kategori);
        }else{
            $('#title_hasil_lab').html("Hasil Pemeriksaan "+kategori+" Luar");
        }
        if(result.length > 0){
            var set = '';
            var li = '';
            $.each(result, function (i, item) {
                var cla = 'class="item"';
                var claLi = '';
                if(i == 0){
                    cla = 'class="item active"';
                    claLi = 'class="active"';
                }
                var x = item.urlImg;
                var tipe = x.split('.').pop();
                if("pdf" == tipe){
                    set += '<div ' + cla + '>\n' +
                        '<embed src="'+item.urlImg+'" style="width: 100%; height: 70%"/>'+
                        '</div>';
                }else{
                    set += '<div ' + cla + '>\n' +
                        '<img src="' + item.urlImg + '" style="width: 100%; height: 70%">\n' +
                        '</div>';
                }
                li += '<li data-target="#carousel-hasil_lab" data-slide-to="'+i+'" '+claLi+'></li>';
            });
            $('#item_hasil_lab').html(set);
            $('#li_hasil_lab').html(li);
        }
        $('#modal-hasil_lab').modal({show: true, backdrop:'static'});
    }
}

function dataDiet(id){
    RawatInapAction.getDataDietGizi(id, function (res) {
        if("Y" == res.isSonde){
            $('#h_is_sonde').val("Y");
            $('#form_keterangan_diet, #form_order, #form_snack').hide();
            $('#form_pemberian_diet').show();
            $('#info_diet').fadeIn(1000);
            $('#msg_info').text("Anda memilih bentuk diet Sonde. Silahkan masukkan waktu awal dan jumlah pemberian sonde...!");
        }else{
            $('#h_is_sonde').val("N");
            $('#form_keterangan_diet, #form_order, #form_snack').show();
            $('#form_pemberian_diet').hide();
            $('#info_diet').fadeOut(1000);
        }
    });
}

function generateSonde(){
    $('#body_add_diet').html('');
    var bentuk = $('#bentuk_diet').val();
    var jenis = $('#jenis_diet').val();
    var bentukText = $('#bentuk_diet option:selected').text();
    var jenisText = $('#jenis_diet option:selected');
    var jam = $('#jam_awal').val();
    var pemberian = $('#jumlah_pemberian').val();
    var satuan = $('#jumlah_satuan').val();
    var tempTextLi = "";

    var tempText = "";
    if(jenisText.length > 0){
        $.each(jenisText, function (i, item) {
            if(tempText != ''){
                tempText = tempText+', '+item.innerText;
            }else{
                tempText = item.innerText;
            }
            tempTextLi += '<li>'+item.innerText+'</li>';
        });
    }
    var table = "";
    if (bentuk && jenis && jam && pemberian && satuan != '') {
        var time = $('#jam_awal').val();
        var char = time.split(":");
        var hh = char[0];
        var min = char[1];
        var timeSonde = new Date();
        timeSonde.setHours(hh, min, 0);
        var tempDate = "";
        for (var i = 0; i < pemberian; i++){

            var cek = false;
            if(i != 0){
                timeSonde.setHours(timeSonde.getHours() + 3, timeSonde.getMinutes(), 0);
            }

            if(tempDate != ""){
                if(tempDate != timeSonde.getDate()){
                    cek = true;
                }
            }

            if(!cek){
                tempDate = timeSonde.getDate();
                var tgl = converterTime(timeSonde);
                table = '<tr id="' + i + '">' +
                    '<td>' + tgl + '</td>' +
                    '<td>' + bentukText +
                    '<input class="waktu_diet" type="hidden" value="' + tgl + '" id="waktu_' + i + '">'+
                    '<input class="bentuk_diet" type="hidden" value="' + bentuk + '" id="bentuk_' + i + '">' +
                    '<input class="bentuk_text_diet" type="hidden" value="' + bentukText + '" id="bentuk_text_' + i + '">' +
                    '<input class="jenis_diet" type="hidden" value="' + jenis + '" id="jenis_' + i + '">'+
                    '</td>' +
                    '<td>' + '<ul style="margin-left: 10px">'+tempTextLi+'</ul>' + '</td>' +
                    '</tr>';
                $('#body_add_diet').append(table);
            }else{
                $('#warning_diet').show().fadeOut(5000);
                $('#msg_diet').text("Pemberian hanya untuk hari ini saja...!");
            }
        }
    } else {
        $('#warning_diet').show().fadeOut(5000);
        $('#msg_diet').text("Silahkan cek kembali inputan anda...!");
        if (bentuk == '') {
            $('#war_bentuk_diet').show();
        }
        if (jenis == '' || jenis == null) {
            $('#war_jenis_diet').show();
        }
        if (jam == '') {
            $('#war_jam_awal').show();
        }
        if (pemberian == '') {
            $('#war_jml_pemberian').show();
        }
        if (satuan == '') {
            $('#war_jml_pemberian').show();
        }
    }
}

function addMakanLuar(){
    var clas = $('.makanan_luar').length;
    var row = 'mk_'+clas;
    var set = '<div id="'+row+'" class="input-group" style="margin-top: 7px">\n' +
        '<input class="form-control makanan_luar" placeholder="Makanan Luar '+(parseInt(clas)+1)+'">\n' +
        '<div class="input-group-btn">\n' +
        '    <a onclick="delMakanLuar(\''+row+'\')" class="btn btn-danger" style="height: 34px; margin-top: 0px">\n' +
        '        <i class="fa fa-trash" style="margin-top: 3px"></i>\n' +
        '    </a>\n' +
        '</div>\n' +
        '</div>';
    $('#temp_luar').append(set);
}

function delMakanLuar(id){
    $('#'+id).remove();
}

function listMakanan(){
    var option = "";
    OrderGiziAction.listPendampingGizi(function (list) {
        if(list.length > 0){
            $.each(list, function (i, item) {
                option += '<option value="' + item.idPendampingGizi +'|'+item.nama+ '">' + item.nama + '</option>'
            });
            $('#snack').html(option);
        }else{
            $('#snack').html(option);
        }
    });
}

function setRekamMedisPindah(tipePelayanan, id) {
    var temp = "";
    dwr.engine.setAsync(true);
    CheckupAction.getListRekammedisPasien(tipePelayanan, null, idDetailCheckup, {
        callback: function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var cek = "";
                    var tgl = "";
                    var icons = '<i class="fa fa-file-o"></i>';
                    var icons2 = '<i class="fa fa-print"></i>';
                    var tol = "";
                    var tolText = "";
                    var labelTerisi = "";
                    var constan = 0;
                    var terIsi = 0;
                    var terIsiPrint = "";

                    if (item.jumlahKategori != null) {
                        constan = item.jumlahKategori;
                    }
                    if (item.terisi != null && item.terisi != '') {
                        terIsi = item.terisi;
                        terIsiPrint = item.terisi;
                    }

                    if (constan == terIsi || parseInt(terIsi) > parseInt(constan)) {
                        var conver = "";
                        if (item.createdDate != null) {
                            conver = converterDate(new Date(item.createdDate));
                            tgl = '<label class="label label-success">' + conver + '</label>';
                            tol = 'class="box-rm"';
                            tolText = '<span class="box-rmtext">Tanggal mengisi ' + conver + '</span>';
                        }
                        icons = '<i class="fa fa-check" style="color: #449d44"></i>';
                    }

                    labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';

                    if (item.keterangan == 'form') {
                        if (item.jenis != "ugd_anak" && item.jenis != "ugd_dewasa" && item.jenis != "ugd_geriatri" && item.jenis != "mpp" && item.jenis != "asesmen_triase") {
                            temp += '<li ' + tol + '><a style="cursor: pointer" onclick="loadModalRM(\'' + item.jenis + '\', \'' + item.function + '\', \'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\', \'N\', \'Y\')">' + icons + item.namaRm +' '+ labelTerisi + tolText + '</a></li>';
                        }
                    }
                });
                $('#' + id).html(temp);
            }
        }
    });
}

function setRekamMedisHasilPindah(tipePelayanan, id) {
    var temp = "";
    var show = false;
    dwr.engine.setAsync(true);
    CheckupAction.getListRekammedisPasienByNoCheckup(tipePelayanan, null, noCheckup, {
        callback: function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var cek = "";
                    var tgl = "";
                    var icons = '<i class="fa fa-file-o"></i>';
                    var icons2 = '<i class="fa fa-print"></i>';
                    var tol = "";
                    var tolText = "";
                    var labelTerisi = "";
                    var constan = 0;
                    var terIsi = 0;
                    var labelPrint = "";
                    var terIsiPrint = "";

                    if (item.jumlahKategori != null) {
                        constan = item.jumlahKategori;
                    }
                    if (item.terisi != null && item.terisi != '') {
                        terIsi = item.terisi;
                        terIsiPrint = item.terisi;
                    }

                    if (constan == terIsi || parseInt(terIsi) > parseInt(constan)) {
                        var conver = "";
                        if (item.createdDate != null) {
                            conver = converterDate(new Date(item.createdDate));
                            tgl = '<label class="label label-success">' + conver + '</label>';
                            tol = 'class="box-rm"';
                            tolText = '<span class="box-rmtext">Tanggal mengisi ' + conver + '</span>';
                        }
                        icons = '<i class="fa fa-check" style="color: #449d44"></i>';
                    }

                    labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';

                    if(parseInt(terIsi) > 0){
                        temp += '<li ' + tol + '><a style="cursor: pointer" onclick="loadModalRM(\'' + item.jenis + '\', \'' + item.function + '\', \'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\', \'Y\', \'N\')">' + icons + item.namaRm +' '+ labelTerisi + tolText + '</a></li>';
                        show = true;
                    }

                });
                $('#' + id).html(temp);
            }

            if(show){
                $('#form_pindahan').show();
            }else{
                $('#form_pindahan').hide();
            }
        }
    });
}

function dokterDpjp(){
    var data = $('#tbl_dokter').tableToJSON();
    var option = "";
    $.each(data, function (i, item) {
        var idDokter = data[i]["ID Dokter"];
        var namaDokter = data[i]["Nama"];
        option += '<option value="'+idDokter+'">'+namaDokter+'</option>';
        if(i == 0){
            $('#sip_pengirim').val(idDokter);
        }
    });
    $('#select_pengirim').html(option);
}

function onChangePengirim(dokter){
    if(dokter != ''){
        $('#sip_pengirim').val(dokter);
    }
}

function addListMakananPendamping(){
    var table = $('#table_add_catering').tableToJSON();
    var count = table.length;
    var row = "";
    var makanan = $('#nama_makanan').val();
    var qty = $('#qty_makanan').val();
    var keterangan = $('#keterangan_makanan').val();
    if(makanan && qty != ''){
        if(keterangan == ''){
            keterangan = '-';
        }
        var cek = false;
        var temp = $('.makanan_pendamping');
        $.each(temp, function (i, item) {
            if(item.value.toUpperCase() == makanan.toUpperCase()){
                cek = true;
            }
        });

        if(!cek){
            makanan = convertSentenceCase(makanan);
            row = '<tr id="makanan_'+count+'">' +
                '<td>'+makanan+
                '<input type="hidden" value="'+makanan+'" class="makanan_pendamping" id="nama_makanan_'+count+'">'+
                '<input type="hidden" value="'+qty+'" class="qty_makanan_pendamping" id="qty_makanan_'+count+'">'+
                '<input type="hidden" value="'+keterangan+'" class="keterangan_makanan_pendamping" id="keterangan_makanan_'+count+'">'+
                '</td>'+
                '<td align="center">'+qty+'</td>'+
                '<td>'+keterangan+'</td>'+
                '<td align="center"><img onclick="delPendamping(\'makanan_' + count + '\')" class="hvr-grow" src="' + contextPath + '/pages/images/cancel-flat-new.png" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                '</tr>';
            $('#body_add_pendamping_makanan').append(row).hide().show('slow');
        }else{
            $('#warning_catering').show().fadeOut(5000);
            $('#msg_catering').text("Makanan "+makanan+" Sudah ada dalam list");
        }
    }else{
        if(makanan == ''){
            $('#war_nama_makanan').show();
        }
        if(qty == '' || parseInt(qty) <= 0){
            $('#war_qty_makanan').show();
        }
        $('#warning_catering').show().fadeOut(5000);
        $('#msg_catering').text("Silahkan cek kembali inputan berikut");
    }
}

function delPendamping(id){
    $('#'+id).remove();
}

function saveMakananPendamping(){
    var table = $('#table_add_catering').tableToJSON();
    if(table.length > 0){
        var makanan = $('.makanan_pendamping');
        var tempData = [];
        $.each(makanan, function (i, item) {
            if(item.value != ''){
                tempData.push({
                    'nama': item.value,
                    'qty': $('.qty_makanan_pendamping')[i].value,
                    'keterangan': $('.keterangan_makanan_pendamping')[i].value
                });
            }
        });
        var data = {
            'id_detail_checkup': idDetailCheckup,
            'id_ruangan': idRuangan,
            'detail': JSON.stringify(tempData)
        }

        var result = JSON.stringify(data);
        $('#load_makanan_pendamping').show();
        $('#save_makanan_pendamping').hide();
        dwr.engine.setAsync(true);
        PendampingMakananAction.savePendampingMakanan(result, {
            callback: function (res) {
                if(res.status == "success"){
                    $('#modal-makanan_pendamping').modal('hide');
                    $('#load_makanan_pendamping').hide();
                    $('#save_makanan_pendamping').show();
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(15);
                    listMakananPendamping();
                }else{
                    $('#load_makanan_pendamping').hide();
                    $('#save_makanan_pendamping').show();
                    $('#warning_catering').show().fadeOut(5000);
                    $('#msg_catering').text(res.msg);
                }
            }
        });
    }else{
        $('#warning_catering').show().fadeOut(5000);
        $('#msg_catering').text("Silahkan cek kembali inputan anda...!");
    }
}

function resetMakanan(){
    $('#body_add_pendamping_makanan').html('');
    $('#nama_makanan').val('');
    $('#qty_makanan').val('1');
    $('#keterangan_makanan').val('');
}

function listMakananPendamping() {
    var table = "";
    PendampingMakananAction.listHeaderPendampingMakanan(idDetailCheckup, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                var status = "-";
                var dateFormat = converterDate(item.createdDate);
                var btn = '<img border="0" class="hvr-grow" onclick="detailMakananPendamping(\'' + item.idHeaderPendampingMakanan + '\', \''+item.status+'\')" src="' + contextPath + '/pages/images/icons8-search-25.png" style="cursor: pointer;">';
                if("0" == item.status){
                    status = '<span class="span-warning">Proses</span>';
                }else{
                    status = '<span class="span-success">Selesai</span>';
                }
                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + item.idHeaderPendampingMakanan + "</td>" +
                    "<td align='center'>" + status + "</td>" +
                    "<td align='center'>" + btn + "</td>" +
                    "</tr>";
            });
            $('#body_pendamping').html(table);
        }
    });
}

function detailMakananPendamping(id, status){
    var table = "";
    PendampingMakananAction.listDetailPendampingMakanan(id, function (json) {
        $.each(json, function (i, item) {
            var btn = "";
            if(status == "0"){
                btn = '<img id="btn_edit_'+item.idDetailPendampingMakanan+'" onclick="edtiDetailMP(\''+item.idDetailPendampingMakanan+'\')" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer; height: 25px; width: 25px;">';
            }
            var nomor = i+1;
            table += '<tr id="'+item.idDetailPendampingMakanan+'">' +
                '<td>'+nomor+'</td>'+
                '<td><span id="l_nama_'+item.idDetailPendampingMakanan+'">'+item.nama+'</span>'+
                '<input type="hidden" value="'+item.nama+'" class="form-control" id="nama_makanan_'+item.idDetailPendampingMakanan+'">'+
                '</td>'+
                '<td align="center"><span id="l_qty_'+item.idDetailPendampingMakanan+'">'+item.qty+'</span>'+
                '<input type="hidden" value="'+item.qty+'" class="form-control" id="qty_makanan_'+item.idDetailPendampingMakanan+'">'+
                '</td>'+
                '<td><span id="l_keterangan_'+item.idDetailPendampingMakanan+'">'+item.keterangan+'</span>'+
                '<input type="hidden" value="'+item.keterangan+'" class="form-control" id="keterangan_makanan_'+item.idDetailPendampingMakanan+'">'+
                '</td>'+
                '<td align="center">'+btn+'</td>' +
                '</tr>';
        });
        $('#body_pendamping_makanan').html(table);
    });
    $('#modal-detail_makanan').modal({show: true, backdrop: 'static'});
}

function edtiDetailMP(id){
    $('#l_nama_'+id).hide();
    $('#l_qty_'+id).hide();
    $('#l_keterangan_'+id).hide();
    $('#nama_makanan_'+id).removeAttr('type').attr('type','text');
    $('#qty_makanan_'+id).removeAttr('type').attr('type','number');
    $('#keterangan_makanan_'+id).removeAttr('type').attr('type','text');
    var url = contextPath + '/pages/images/icons8-save-25.png';
    $('#btn_edit_' + id).attr('src', url);
    $('#btn_edit_' + id).attr('onclick', 'saveEditMP(\'' + id + '\')');
}

function saveEditMP(id){
    $('#l_nama_'+id).show();
    $('#l_qty_'+id).show();
    $('#l_keterangan_'+id).show();
    $('#nama_makanan_'+id).removeAttr('type').attr('type','hidden');
    $('#qty_makanan_'+id).removeAttr('type').attr('type','hidden');
    $('#keterangan_makanan_'+id).removeAttr('type').attr('type','hidden');
    var nama = $('#nama_makanan_'+id).val();
    var qty = $('#qty_makanan_'+id).val();
    var keterangan = $('#keterangan_makanan_'+id).val();
    $('#l_nama_'+id).text(nama);
    $('#l_qty_'+id).text(qty);
    $('#l_keterangan_'+id).text(keterangan);
    var url = contextPath + '/pages/images/spinner.gif';
    $('#btn_edit_' + id).attr('src', url);
    dwr.engine.setAsync(true);
    PendampingMakananAction.editDetailPendampingMakanan(id, nama, qty, keterangan, {
        callback:function (res) {
            if(res.status == "success"){
                $('#warning_suc_detail_makanan').show().fadeOut(5000);
                $('#msg_suc_detail_makanan').text("Berhasil menyimpan data...!");
                var url = contextPath + '/pages/images/icons8-create-25.png';
                $('#btn_edit_' + id).attr('src', url);
                $('#btn_edit_' + id).attr('onclick', 'edtiDetailMP(\'' + id + '\')');
            }else{
                $('#warning_detail_makanan').show().fadeOut(5000);
                $('#msg_detail_makanan').text(res.msg);
            }
        }
    });
}

function addListPemeriksaan(){
    var count = $('#tabel_pemeriksaan').tableToJSON().length;
    var idKategori = $('#lab_kategori').val();
    var namaPemeriksaan = $('#lab_lab option:selected').text();
    var idPemeriksaan = $('#lab_lab').val();

    var namaParameter = $('#lab_parameter option:selected');
    var idParameter = $('#lab_parameter').val();

    var cekPending = $('#is_pending_lab').is(':checked');
    var tglPending = $('#tgl_pending').val();
    var jamPending = $('#jam_pending').val();

    var namaPemeriksaanLuar = $('#lab_luar').val();
    var idParameterLuar = $('.parameter_luar');

    var idLabLuar = $('#lab_luar').val();
    var idParamLuar = $('#lab_parameter_luar').val();

    var tempPemeriksaan = "";
    var tempIdPemeriksan= "";
    var tempPemeriksanLi = "";

    var tempParameter = "";
    var tempIdParameter = "";
    var tempParameterLi = "";
    var cekLuar = $('#is_luar').is(':checked');
    var tarifLabLuar = $('#h_total_tarif').val();
    var cekTarif = true;

    if (cekLuar) {
        if(namaPemeriksaanLuar != ''){
            tempPemeriksaan = namaPemeriksaanLuar;
        }
        if(idParameterLuar[0].value != ''){
            $.each(idParameterLuar, function (i, item) {
                if(item.value != ''){
                    if(tempParameter != ''){
                        tempParameter = tempParameter+'#'+item.value;
                    }else{
                        tempParameter = item.value;
                    }
                    tempParameterLi += '<li>'+item.value+'</li>';
                }
            });
        }
        if(tarifLabLuar != '' && parseInt(tarifLabLuar) > 0){
            cekTarif = true;
        }else{
            cekTarif = false;
        }
    }else{
        if(idPemeriksaan != ''){
            tempPemeriksaan = namaPemeriksaan;
            tempIdPemeriksan = idPemeriksaan;
        }
        if(idParameter != '' && idParameter != null){
            $.each(namaParameter, function (i, item) {
                if(tempIdParameter != ''){
                    tempIdParameter = tempIdParameter+'#'+item.value;
                }else{
                    tempIdParameter = item.value;
                }

                if(tempParameter != ''){
                    tempParameter = tempParameter+'#'+item.innerHTML;
                }else{
                    tempParameter = item.innerHTML;
                }
                tempParameterLi += '<li>'+item.innerHTML+'</li>';
            });
        }
    }



    if(tempPemeriksaan && tempParameter != '' && cekTarif){
        var cek = false;
        $.each($('.nama_jenis_pemeriksaan'), function (i, item) {
            if(item.value != ''){
                if(item.value.toLowerCase() == tempPemeriksaan.toLowerCase()){
                    cek = true;
                }
            }
        });
        if(!cek){
            var row =
                '<tr id="row_'+count+'">' +
                '<td>'+
                tempPemeriksaan +
                '<input type="hidden" class="nama_jenis_pemeriksaan" value="'+tempPemeriksaan+'">'+
                '<input type="hidden" class="id_jenis_pemeriksaan" value="'+tempIdPemeriksan+'">'+
                '<input type="hidden" class="nama_parameter_pemeriksaan" value="'+tempParameter+'">'+
                '<input type="hidden" class="id_parameter_pemeriksaan" value="'+tempIdParameter+'">'+
                '</td>'+
                '<td><ul style="margin-left: 20px">'+tempParameterLi+'</ul></td>'+
                '<td align="center"><img onclick="delPemeriksaan(\'row_'+count+'\')" style="cursor: pointer" src="'+contextPath+'/pages/images/cancel-flat-new.png" class="hvr-row"></td>'+
                '</tr>';
            $('#body_pemeriksaan').append(row);
            $('#lab_kategori').attr('disabled', true);
            $('#lab_lab').val(null).trigger('change');
            $('#lab_parameter').val(null).trigger('change');
            $('#is_pending_lab').attr('disabled', true);
            $('#is_luar').attr('disabled', true);
            $('#tgl_pending').attr('disabled', true);
            $('#jam_pending').attr('disabled', true);
            $('#lab_parameter_luar').val(null);
            $('#lab_luar').val(null);
            $('#select-jenis-pemeriksaan').attr('disabled', true);
            var par = $('.parameter_luar');
            $.each(par, function (i, item) {
                if (i != 0) {
                    $('#par_' + i).remove();
                }
            });
            $('#cek_luar').css('cursor', 'no-drop');
            $('#cek_pending').css('cursor', 'no-drop');
        }else{
            $('#modal-lab').scrollTop(0);
            $('#warning_lab').show().fadeOut(5000);
            $('#msg_lab').text("Pemeriksaan "+tempPemeriksaan+" sudah ada dalam ist");
        }
    }else{
        $('#modal-lab').scrollTop(0);
        $('#warning_lab').show().fadeOut(5000);
        $('#msg_lab').text("Silahkan cek kembali inputan berikut...!");
        if (idKategori == '') {
            $('#war_kategori_lab').show();
        }
        if (idPemeriksaan == '') {
            $('#war_lab').show();
        }
        if (idParameter == '' || idParameter == null) {
            $('#war_parameter').show();
        }
        if (idLabLuar == '') {
            $('#war_lab_luar').show();
        }
        if (idParamLuar == '') {
            $('#war_lab_parameter_luar').show();
        }
        if (tarifLabLuar == '') {
            $('#war_tarif_luar_lab').show();
        }
    }
}

function delPemeriksaan(id){
    $('#'+id).remove();
    var table = $('#tabel_pemeriksaan').tableToJSON().length;
    if(table == 0){
        $('#lab_kategori').attr('disabled', false);
        $('#is_pending_lab').attr('disabled', false);
        $('#is_luar').attr('disabled', false);
        $('#tgl_pending').attr('disabled', false);
        $('#jam_pending').attr('disabled', false);
        $('#cek_luar').css('cursor', 'pointer');
        $('#cek_pending').css('cursor', 'pointer');
        $('#select-jenis-pemeriksaan').attr('disabled', false);
    }
}

function resetPemeriksaan(){
    $('#body_pemeriksaan').html('');
    $('#lab_kategori').attr('disabled', false);
    $('#lab_lab').val(null).trigger('change');
    $('#lab_parameter').val(null).trigger('change');
    $('#is_pending_lab').attr('disabled', false);
    $('#is_luar').attr('disabled', false);
    $('#tgl_pending').attr('disabled', false);
    $('#jam_pending').attr('disabled', false);
    $('#lab_parameter_luar').val(null);
    $('#lab_luar').val(null);
    $('#select-jenis-pemeriksaan').attr('disabled', false);
    var par = $('.parameter_luar');
    $.each(par, function (i, item) {
        if (i != 0) {
            $('#par_' + i).remove();
        }
    });
    $('#cek_luar').css('cursor', 'pointer');
    $('#cek_pending').css('cursor', 'pointer');
}

function addOrderListPemeriksaan(){
    var count = $('#tabel_order_pemeriksaan').tableToJSON().length;
    var idKategori = $('#ckp_kategori').val();
    var namaPemeriksaan = $('#ckp_unit option:selected').text();
    var idPemeriksaan = $('#ckp_unit').val();

    var namaParameter = $('#ckp_parameter option:selected');
    var idParameter = $('#ckp_parameter').val();

    var tempPemeriksaan = "";
    var tempIdPemeriksan= "";

    var tempParameter = "";
    var tempIdParameter = "";
    var tempParameterLi = "";

    if(idPemeriksaan != ''){
        tempPemeriksaan = namaPemeriksaan;
        tempIdPemeriksan = idPemeriksaan;
    }
    if(idParameter != '' && idParameter != null){
        $.each(namaParameter, function (i, item) {
            if(tempIdParameter != ''){
                tempIdParameter = tempIdParameter+'#'+item.value;
            }else{
                tempIdParameter = item.value;
            }

            if(tempParameter != ''){
                tempParameter = tempParameter+'#'+item.innerHTML;
            }else{
                tempParameter = item.innerHTML;
            }
            tempParameterLi += '<li>'+item.innerHTML+'</li>';
        });
    }

    if(tempPemeriksaan && tempParameter != ''){
        var cek = false;
        $.each($('.nama_order_jenis_pemeriksaan'), function (i, item) {
            if(item.value != ''){
                if(item.value.toLowerCase() == tempPemeriksaan.toLowerCase()){
                    cek = true;
                }
            }
        });
        if(!cek){
            var row =
                '<tr id="row_'+count+'">' +
                '<td>'+
                tempPemeriksaan +
                '<input type="hidden" class="nama_order_jenis_pemeriksaan" value="'+tempPemeriksaan+'">'+
                '<input type="hidden" class="id_order_jenis_pemeriksaan" value="'+tempIdPemeriksan+'">'+
                '<input type="hidden" class="nama_order_parameter_pemeriksaan" value="'+tempParameter+'">'+
                '<input type="hidden" class="id_order_parameter_pemeriksaan" value="'+tempIdParameter+'">'+
                '</td>'+
                '<td><ul style="margin-left: 20px">'+tempParameterLi+'</ul></td>'+
                '<td align="center"><img onclick="delOrderPemeriksaan(\'row_'+count+'\')" style="cursor: pointer" src="'+contextPath+'/pages/images/cancel-flat-new.png" class="hvr-row"></td>'+
                '</tr>';
            $('#body_order_pemeriksaan').append(row);
            $('#ckp_kategori').attr('disabled', true);
            $('#ckp_unit').val(null).trigger('change');
            $('#ckp_parameter').val(null).trigger('change');
        }else{
            $('#warning_ket').show().fadeOut(5000);
            $('#warning_msg').text("Pemeriksaan "+tempPemeriksaan+" sudah ada dalam ist");
        }
    }else{
        $('#warning_ket').show().fadeOut(5000);
        $('#warning_msg').text("Silahkan cek kembali inputan berikut...!");
        if (idKategori == '') {
            $('#war_ckp_kategori').show();
        }
        if (idPemeriksaan == '') {
            $('#war_ckp_unit').show();
        }
        if (idParameter == '' || idParameter == null) {
            $('#war_ckp_parameter').show();
        }
    }
}

function delOrderPemeriksaan(id){
    $('#'+id).remove();
    var table = $('#tabel_order_pemeriksaan').tableToJSON().length;
    if(table == 0){
        $('#ckp_kategori').attr('disabled', false);
    }
}

function resetOrderPemeriksaan(){
    $('#body_order_pemeriksaan').html('');
    $('#ckp_kategori').attr('disabled', false);
    $('#ckp_kategori').val(null).trigger('change');
    $('#ckp_unit').val(null).trigger('change');
    $('#ckp_parameter').val(null).trigger('change');
}

function uploadPemeriksaan(){
    $('#btn-uploded').html('<button onclick="doneUplod()" class="btn btn-success"><i class="fa fa-cloud-upload"></i> Upload</button>');
    $('#form-uploded').hide();
    $('#ket_upload_pemeriksan_0, #upload_pemeriksan_0, #label_upload_pemeriksan_0').val('');
    $('#set_upload_pemeriksan').html('');
    $('#label_upload_pemeriksan_0').css('border-bottom','');
    listUploadPemeriksaan();
    $('#modal-upload_pemeriksaan').modal({show: true, backdrop:'static'});
}

function parseToByte(id, label, ket, idRow) {
    if(!cekSession()){
        var files = document.getElementById(id).files;
        var keterangan = $('#'+ket).val();
        if (files.length > 0 && keterangan != '') {
            var fileToLoad = files[0];
            var fileReader = new FileReader();
            var base64File;
            fileReader.onload = function(event) {
                base64File = event.target.result;
                var eks = cekEks(base64File);
                var base = replaceFile(base64File);
                var data = {
                    'id_detail_checkup': idDetailCheckup,
                    'byte': base,
                    'keterangan': keterangan,
                    'eks': eks,
                    'file_name': id
                }
                if(eks == "pdf" || eks == "jpg" || eks == "png"){
                    var result = JSON.stringify(data);
                    $('#'+label).val("Sedang menyimpan...");
                    dwr.engine.setAsync(true);
                    CheckupDetailAction.uploadFilePemeriksaan(result, {
                        callback: function (res) {
                            if(res.status == "success"){
                                $('#'+label).val(files[0].name);
                                $('#'+label).css('border-bottom','solid 5px #5cb85c');
                                dwr.engine.setAsync(false);
                                listUploadPemeriksaan();
                            }else{
                                $('#'+label).val(res.msg);
                                $('#'+label).css('border-bottom','solid 5px #c9302c');
                            }
                        }
                    });
                }else{
                    $('#'+label).val("File harus .jpg, .png, .pdf");
                    $('#'+label).css('border-bottom','solid 5px #c9302c');
                }
            };
            fileReader.readAsDataURL(fileToLoad);
        }else{
            $('#'+ket).css('border', 'red solid 1px');
            $('#'+ket).focus();
            $('#'+id).val('');
        }
    }
}

function replaceFile(byte){
    var conditon = byte.split(",")[0]+',';
    var res = byte.replace(conditon, "");
    return res;
}

function cekEks(byte){
    var res = "";
    var conditon = byte.split(",")[0];
    if(conditon == "data:image/jpeg;base64"){
        res = "jpg";
    }else if(conditon == "data:image/png;base64"){
        res = "png";
    }else if(conditon == "data:application/pdf;base64"){
        res = "pdf";
    }
    return res;
}

function delUpload(idDetail) {
    $('#modal-confirm-rm').modal('show');
    $('#tanya').text("Apakah anda yakin untuk menghapus data?");
    if(idDetail != ''){
        $('#save_con_rm').attr('onclick', 'executeDel(\''+idDetail+'\')');
    }
}

function executeDel(idDetail){
    $('#modal-confirm-rm').modal('hide');
    if(!cekSession()){
        dwr.engine.setAsync(true);
        CheckupDetailAction.deleteUploadFilePemeriksaan(idDetail, {
            callback: function (res) {
                if(res.status == "success"){
                    dwr.engine.setAsync(false);
                    $('#success_pemeriksaan').show().fadeOut(5000);
                    $('#msg_success_pemeriksaan').text("Berhasil menghapus data...!");
                    listUploadPemeriksaan();
                }else{
                    $('#warning_pemeriksaan').show().fadeOut(5000);
                    $('#msg_warning_pemeriksaan').text(res.msg);
                }
            }
        });
    }
}

function listUploadPemeriksaan(){
    $('#item_pemeriksaan').html('');
    $('#li_pemeriksaan').html('');
    CheckupDetailAction.getListUploadPendukungPemeriksaan(idDetailCheckup, function (res) {
        if(res.length > 0){
            var set = '';
            var li = '';
            $.each(res, function (i, item) {
                var cla = 'class="item"';
                var claLi = '';
                if (i == 0) {
                    cla = 'class="item active"';
                    claLi = 'class="active"';
                }
                var x = item.urlImg;
                var tipe = x.split('.').pop();
                if("pdf" == tipe){
                    set += '<div ' + cla + '>\n' +
                        '<embed src="'+item.urlImg+'" style="width: 100%; height: 400px"/>'+
                        '</div>';
                }else{
                    set += '<div ' + cla + '>\n' +
                        '<div class="text-center">' +
                        '<span><b>'+item.keterangan+'</b></span>'+
                        '</div>'+
                        '<img src="' + item.urlImg + '" style="width: 100%; height: 400px; cursor: pointer" onclick="delUpload(\''+item.idUpload+'\')">\n' +
                        '</div>';
                }
                li += '<li data-target="#carousel-pemeriksaan" data-slide-to="' + i + '" ' + claLi + '></li>';
            });
            $('#item_pemeriksaan').html(set);
            $('#li_pemeriksaan').html(li);
        }
    });
}

function setUpload(){
    $('#btn-uploded').html('<button onclick="doneUplod()" class="btn btn-success"><i class="fa fa-cloud-upload"></i> Upload</button>');
    $('#form-uploded').hide();
    $('#ket_upload_pemeriksan_0, #upload_pemeriksan_0, #label_upload_pemeriksan_0').val('');
    $('#set_upload_pemeriksan').html('');
    $('#label_upload_pemeriksan_0').css('border-bottom','');
}

function doneUplod(){
    $('#btn-uploded').html('<button onclick="setUpload()" class="btn btn-warning"><i class="fa fa-check"></i> Selesai Upload</button>');
    $('#form-uploded').show();
    $('#ket_upload_pemeriksan_0, #upload_pemeriksan_0, #label_upload_pemeriksan_0').val('');
    $('#set_upload_pemeriksan').html('');
    $('#label_upload_pemeriksan_0').css('border-bottom','');
}


