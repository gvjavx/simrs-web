function getJenisResep() {

    var jenisPeriksaPasien = $("#id_jenis_pasien").val();

    strSelect = "";
    var arBodyJenisResep = [];
    if (jenisPeriksaPasien == "ptpn") {
        arBodyJenisResep.push({"nilai": "bpjs", "label": "BPJS"}, {"nilai": "ptpn", "label": "PTPN"});
    } else if (jenisPeriksaPasien == "asuransi") {
        arBodyJenisResep.push({"nilai": "asuransi", "label": "ASURANSI"}, {"nilai": "umum", "label": "UMUM"});
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
        });
    }
    $('#kamar_detail').html(option);
    $('#ruangan_ruang').html(option);
}

function selectKeterangan(idKtg) {
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

        } else if (idKtg == "rawat_inap") {
            $('#form-ket-rawat_inap').show();
            $('#form-catatan').show();
            $('#form-kelas_kamar').show();
            $('#form-kamar').show();
            getKelasKamar('rawat_inap');
            $('#label_kamar').text("Kamar");
            $('#kelas_kamar').attr('onchange', 'getKamar(this.value, \'rawat_inap\')');
            if (jenisPasien == 'bpjs' || jenisPasien == 'ptpn') {
                $('#form-hak_kamar').show();
                $('#hak_kamar_bpjs').text("Hak Kamar " + kelasPasienBpjs);
            } else {
                $('#form-hak_kamar').hide();
                $('#hak_kamar_bpjs').text('');
            }

            $('#form-selesai').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-rs-rujukan').hide();
            $('#form-pemeriksaan').hide();
            $('#form-stay').hide();

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

        } else if (idKtg == "kontrol_ulang") {
            $('#form-tgl-kontrol').show();
            $('#form-catatan').show();

            $('#form-rs-rujukan').hide();
            $('#form-selesai').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-pemeriksaan').hide();
            $('#form-stay').hide();
            $('#form-kelas_kamar').hide();

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
        }else if (idKtg == "rr") {
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

function listSelectTindakanKategori(val) {
    var option = "<option value=''>[Select One]</option>";
    var idDokter = "";
    var idPelayanan = "";

    if (val != null && val != '') {

        var dataDokter = val.split("|");

        idDokter = dataDokter[0];
        idPelayanan = dataDokter[1];

        CheckupDetailAction.getListComboTindakanKategori(idPelayanan, kategoriRuangan, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idKategoriTindakan + "'>" + item.kategoriTindakan + "</option>";
                });
                $('#tin_id_ketgori_tindakan').html(option);
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
        window.location.href = 'initForm_' + urlPages + '.action';
    } else if (back == 9) {
        desti = '#pos_rssep';
    } else if (back == 10) {
        desti = '#pos_icd9';
    } else if (back == 11) {
        desti = '#pos_selesai';
    } else if (back == 12) {
        window.location.reload(true);
    } else if (back == 13) {
        window.location.href = 'add_' + urlPages + '.action?id=' + idDetailCheckup + '&idx=' + idHref;
    } else if (back == 14) {
        desti = '#pos_rm';
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
        getListNamaDokter();
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
        $('#form_ttd').show();
        $('#lab_kategori, #lab_lab').attr('disabled', false);
        $('#lab_kategori, #lab_lab').val('').trigger('change');
        $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
        $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
        $('#modal-lab').modal({show: true, backdrop: 'static'});
    } else if (select == 5) {
        getListComboJenisDiet();
        $('#bentuk_diet, #keterangan_diet').val('');
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
        $('#resep_jenis_obat').val('').trigger('change');
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
        $('#resep_jenis_obat').attr("onchange", "var warn =$('#war_jenis_pasien').is(':visible'); if (warn){$('#cor_jenis_pasien').show().fadeOut(3000);$('#war_jenis_pasien').hide()}; setObatPoli(this.value)");
        $('#resep_apotek').attr("onchange", "var warn =$('#war_rep_apotek').is(':visible'); if (warn){$('#cor_rep_apotek').show().fadeOut(3000);$('#war_rep_apotek').hide()}");
        $('#resep_nama_obat').attr("onchange", "var warn =$('#war_rep_obat').is(':visible'); if (warn){$('#cor_rep_obat').show().fadeOut(3000);$('#war_rep_obat').hide()}; setStokObatApotek(this,\'\')");
        $('#resep_nama_obat_serupa').attr("onchange", "var warn =$('#war_rep_obat_serupa').is(':visible'); if (warn){$('#cor_rep_obat_serupa').show().fadeOut(3000);$('#war_rep_obat_serupa').hide()}; setStokObatApotek(this, \'serupa\')");
        $('#body_detail').html('');
        $('#modal-resep-head').modal({show: true, backdrop: 'static'});

        var option = '<option value="">[Select One]</option>';
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
    var jenis = $('#dok_jenis_dpjp').val();
    var idDok = "";
    var pelayanan = "";
    if (idDokter != '') {
        var data = idDokter.split("|");
        idDok = data[0];
        pelayanan = data[1];
    }

    if (idDetailCheckup != '' && idDok != '' && pelayanan != '' && jenis != '') {
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
            if(cek){
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
    if(sts == "Y"){
        status = "disetujui";
        label = 'class="span-success"';
    }else if(sts == "N"){
        status = "ditolak";
        label = 'class="span-danger"';
    }else if(sts == "S"){
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
            }else{
                $('#save_kon').show();
                $('#load_kon').hide();
                $('#warning_kon_dokter').show().fadeOut(5000);
                $('#msg_kon_dokter').text(response.msg);
            }
        }
    });
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
    var idDokter = $('#tin_id_dokter_dpjp').val();
    var qty = $('#tin_qty').val();
    var idJenisPeriksa = $('#id_jenis_pasien').val();
    var idDok = "";
    var idPelayanan = "";

    if (idDetailCheckup != '' && idTindakan != '' && idDokter != '' && qty > 0 && idKategori != '') {

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
    }
}

function listDokterTindakan() {

    var idPelayanan = $("#id_pelayanan").val();
    var option = "<option value=''>[Select One]</option>";

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
                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + id + "</td>" +
                    "<td>" + ket + "</td>" +
                    "<td>" + jen + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiagnosa(\'' + item.idDiagnosaRawat + '\',\'' + item.idDiagnosa + '\',\'' + item.jenisDiagnosa + '\', \'' + item.keteranganDiagnosa + '\')" src="' + pathImages + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_diagnosa').html(table);
}

function listSelectLab(idx) {
    var option = "<option value=''>[Select One]</option>";
    if (idx != '') {
        LabAction.listLab(idx, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idLab + "'>" + item.namaLab + "</option>";
                });
                $('#lab_lab').html(option);
            } else {
                $('#lab_lab').html('');
            }
        });
    } else {
        $('#lab_lab').html('');
    }
    $('#lab_parameter').html('');
}

function listSelectParameter(idLab) {
    if (idLab != null && idLab != '') {
        var option = "";
        LabDetailAction.listLabDetail(idLab, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idLabDetail + "'>" + item.namaDetailPeriksa + "</option>";
                });
                $('#lab_parameter').html(option);
                $('#lab_parameter option').prop('selected', true);
            } else {
                $('#lab_parameter').html('');
            }
        });
    } else {
        $('#lab_parameter').html('');
    }
}

function saveLab(id) {
    var data = $('#tbl_dokter').tableToJSON();
    var idDokter = "";
    $.each(data, function (i, item) {
        if (i == 0) {
            idDokter = data[i]["ID Dokter"];
        }
    });
    var idKategori = $('#lab_kategori').val();
    var idLab = $('#lab_lab').val();
    var idParameter = $('#lab_parameter').val();
    var pengirim = document.getElementById('ttd_pengirim');
    var cekTtd = isCanvasBlank(pengirim);

    if (idDetailCheckup != '' && idKategori != '' && idLab != '' && idParameter != null) {
        if (id != '') {
            $('#save_lab').hide();
            $('#load_lab').show();
            dwr.engine.setAsync(true);
            PeriksaLabAction.editOrderLab(id, idLab, idParameter, {
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
                    }
                }
            });
        } else {
            if (!cekTtd) {
                $('#save_lab').hide();
                $('#load_lab').show();
                dwr.engine.setAsync(true);
                var ttd = convertToDataURL(pengirim);
                PeriksaLabAction.saveOrderLab(idDetailCheckup, idLab, idParameter, ttd, idDokter, idKategori, {
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
                        }
                    }
                });
            } else {
                $('#warning_lab').show().fadeOut(5000);
                $('#msg_lab').text("Silhakan lakukan TTD dulu...!");
            }
        }
    } else {
        $('#warning_lab').show().fadeOut(5000);
        $('#msg_lab').text("Silahkan cek kembali data inputan!");
        if (idKategori == '') {
            $('#war_kategori_lab').show();
        }
        if (idLab == '' || idLab == null) {
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
        if (data.length > 0) {
            $.each(data, function (i, item) {
                var pemeriksaan = "-";
                var status = "-";
                var lab = "-";
                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));
                var btn = '<img border="0" class="hvr-grow" onclick="editLab(\'' + item.idPeriksaLab + '\',\'' + item.idLab + '\',\'' + item.idKategoriLab + '\',\'' + item.kategoriLabName + '\')" src="' + pathImages + '/pages/images/icons8-create-25.png" style="cursor: pointer;">';
                var crn = '<img border="0" class="hvr-grow" onclick="detailLab(\'' + item.idPeriksaLab + '\',\'' + item.kategoriLabName + '\')" src="' + contextPath + '/pages/images/icons8-search-25.png" style="cursor: pointer;">';
                var tipe = "";

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
                if (item.labName != null) {
                    lab = item.labName;
                }
                if (item.approveFlag == "Y") {
                    if (item.urlImg != null) {
                        btn = '<img onclick="labLuar(\'' + lab + '\', \'' + item.urlImg + '\')" border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                    } else {
                        btn = '<a target="_blank" href="printLabRadiologi_rawatinap.action?id=' + idDetailCheckup + '&tipe=' + tipe + '&lab=' + item.idPeriksaLab + '"><img border="0" class="hvr-grow" src="' + pathImages + '/pages/images/icons8-print-25.png" style="cursor: pointer;"></a>';
                    }
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + item.kategoriLabName + "</td>" +
                    "<td>" + lab + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td align='center'>" + btn + crn + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_lab').html(table);
}

function labLuar(kategori, url) {
    $('#title_lab_luar').text("Detail Hasil " + kategori + " Luar");
    $('#img_lab_luar').attr('src', url);
    $('#modal-lab_luar').modal({show: true, backdrop: 'static'});
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

function detailLab(id, kategoriName) {
    var idParameter = [];
    var body = [];
    PeriksaLabAction.listParameterPemeriksaan(id, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                body += '<tr>' +
                    '<td>' + item.namaDetailPeriksa + '</td>' +
                    '<td>' + kategoriName + '</td>' +
                    '</tr>';
            });
            $('#body_detail_lab').html(body);
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
    });

    $('#body_obat').html(table);
}

function saveDiet(id) {
    if (id != null && id != '') {
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
    } else {
        var data = [];
        var bentukDiet = $('#bentuk_diet').val();
        var jenisDiet = $('#jenis_diet').val();
        var ketData = $('[name=ket_diet]');
        var isBesok = $('#besok_diet').is(':checked');
        var besok = "N";
        if (isBesok) {
            besok = "Y";
        }
        var keteranganDiet = "";
        $.each(ketData, function (i, item) {
            if (item.checked) {
                keteranganDiet = 'cek';
            }
        });
        var table = $('#table_add_diet').tableToJSON();
        $.each(table, function (i, item) {
            var waktu = $('#waktu_' + i).val();
            var bentuk = $('#bentuk_' + i).val();
            var jenis = $('#jenis_' + i).val();
            var bentukText = $('#bentuk_text_' + i).val();
            data.push({
                'id_rawat_inap': idRawatInap,
                'waktu': waktu,
                'id_diet_gizi': bentuk,
                'id_jenis_diet': jenis
            });
        });
        if (bentukDiet != '' && keteranganDiet != '' && jenisDiet != '') {
            $('#save_diet').hide();
            $('#load_diet').show();
            dwr.engine.setAsync(true);
            var result = JSON.stringify(data);
            OrderGiziAction.saveOrderGizi(result, besok, function (response) {
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
        } else {
            $('#warning_diet').show().fadeOut(5000);
            $('#msg_diet').text("Silahkan cek kembali inputan anda...!");
            if (bentukDiet == '') {
                $('#war_bentuk_diet').show();
            }
            if (jenisDiet == '' || jenisDiet == null) {
                $('#war_jenis_diet').show();
            }
            if (keteranganDiet == '') {
                $('#war_ket_diet').show();
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
                            '<input class="form-control" onchange="cekBarcode(this.value, \'' + item.idOrderGizi + '\')">' +
                            '<div class="input-group-addon">' +
                            '<span id="status' + item.idOrderGizi + '"></span>' +
                            '</div>' +
                            '</div>';
                        label = '<span class="span-success"> telah dikonfirmasi</span>';
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
                    "<td>" + item.idDietGizi + "</td>" +
                    "<td>" + item.bentukDiet + "</td>" +
                    "<td>" + item.waktu + "</td>" +
                    "<td align='center'>" + label + "</td>" +
                    "<td align='center'>" + btn + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_diet').html(table);
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
    getListNamaDokter();
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

function editLab(id, idLab, idKategoriLab, kategoriName) {
    $('#form_ttd').hide();
    $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
    $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
    $('#lab_kategori').val(idKategoriLab).trigger('change').attr('disabled', true);
    var idParameter = [];
    PeriksaLabAction.listParameterPemeriksaan(id, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                idParameter.push(item.idLabDetail);
            });
        }
    });
    $('#lab_lab').val(idLab).trigger('change').attr('disabled', true);
    $('#lab_parameter').val(idParameter).trigger('change');
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
            var tgl = tanggalPindah.split("-").reverse().join("-");
            $('#save_ruang').hide();
            $('#load_ruang').show();
            console.log(jamPindah);
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
    //var obat = $('#resep_nama_obat').val();
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

    if (flagKronis == "Y") {
        hariKronis = $("#hari-kronis").val();
    }

    var i = 0;
    var waktu = [];
    $.each(listObat, function (idx, item) {
        if (item.checked) {
            waktu.push($(this).val());
            i = i + 1;
        }
    });

    var ket = pemberian + " Makan. " + i + "x1. " + waktu.join(", ");

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
                    '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\',\'' + totalHarga + '\')" class="hvr-grow" src="' + contextPath + '/pages/images/delete-flat.png" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                    '</tr>';
                $('#body_detail').append(row);
                var total = $('#total_harga_obat').val();
                var tot = 0;
                if (total != "") {
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
    if (total != "") {
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
        $('#modal-ttd').modal({show: true, backdrop: 'static'});
    } else {
        $('#warning_resep_head').show().fadeOut(5000);
        $('#msg_resep').text("Silahkan cek kembali data inputan anda..!");
        $('#modal-resep-head').scrollTop(0);
    }
}

function clearConvas() {
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
    var ttd = isBlank(canvas);
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
    });

    $('#body_resep').html(table);
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
                    '<option value="">[Select One]</option>' +
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

function confirmSaveAllTindakan() {
    $('#modal-confirm-dialog').modal('show');
    $('#save_con').attr('onclick', 'saveAllTindakan()');
}

function saveAllTindakan() {
    var jenisPasien = $('#id_jenis_pasien').val();
    $('#modal-confirm-dialog').modal('hide');
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
    var option = "<option value=''>[Select One]</option>";
    var jenisPasien = $('#id_jenis_pasien').val();

    if (poli != '') {
        var idPel = poli.split('|')[0];
        var namePel = poli.split('|')[1];
        ObatPoliAction.getSelectOptionObatByPoli(idPel, jenisPasien, jenis, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idObat + "|" + item.namaObat + "|" + item.qtyBox + "|" + item.qtyLembar + "|" + item.qtyBiji + "|" + item.lembarPerBox + "|" + item.bijiPerLembar + "|" + item.flagKronis + "|" + item.harga + "'>" + item.namaObat + "</option>";
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

            //$('#resep_stok_box').val(qtyBox);
            //$('#resep_stok_lembar').val(qtyLembar);
            //$('#resep_stok_biji').val(total);
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
        }
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

    if (idDetailCheckup != '' && idIcd9 != '' && ketIcd9 != '') {

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

function getListNamaDokter() {
    var option = '<option value="">[Select One]</option>';
    CheckupAction.getListDokterByIdDetailCheckup(idDetailCheckup, "Y", function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                option += '<option value="' + item.idDokter + '|' + item.idPelayanan + '">' + item.namaDokter + ' - ' + item.namaPelayanan + '</option>';
            });
            $('#tin_id_dokter_dpjp').html(option);
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
    var option = "<option value=''>[Select One]</option>";
    ObatPoliAction.getSelectOptionObatByPoliKandunganSerupa(idPelayanan, jenisPasien, idObat, function (response) {
        if (response != null) {
            $.each(response, function (i, item) {
                option += "<option value='" + item.idObat + "|" + item.namaObat + "|" + item.qtyBox + "|" + item.qtyLembar + "|" + item.qtyBiji + "|" + item.lembarPerBox + "|" + item.bijiPerLembar + "|" + item.flagKronis + "|" + item.harga + "'>" + item.namaObat + "</option>";
            });
            $('#resep_nama_obat_serupa').html(option);
        }
    });
}

function isPemeriksaan(id) {
    var cek = $('#' + id).is(':checked');
    if (cek) {
        $('#form-pemeriksaan').show();
    } else {
        $('#form-pemeriksaan').hide();
    }
}

function getKamar(idKelas, kategori) {
    var option = '<option value="">[Select One]</option>';
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
    var option = '<option value="">[Select One]</option>';
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

    console.log(option);
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
    var keterangan = $('#ket_selesai').val();
    var ketRawatInap = $('#keterangan_rw').val();
    var rsRujukan = $('#rs_rujukan').val();
    var tglKontrol = $('#tgl_kontrol').val().split("-").reverse().join("-");
    var isPemeriksaan = $('#pemeriksaan_lab').is(':checked');
    var kategoriLab = $('#ckp_kategori').val();
    var unitLab = $('#ckp_unit').val();
    var parameterLab = $('#ckp_parameter').val();
    var isStay = $('#is_stay').is(':checked');
    var idRuangan = $('#kamar').val();
    var idRuanganLama = $('#id_ruangan_lama').val();
    var cek = false;
    var cekTindakan = $('#tabel_tindakan').tableToJSON();

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
                        if (kategoriLab && unitLab && parameterLab != '') {
                            data = {
                                'id_detail_checkup': idDetailCheckup,
                                'no_checkup': noCheckup,
                                'id_rawat_inap': idRawatInap,
                                'tindak_lanjut': tindakLanjut,
                                'keterangan': 'Kontrol Ulang dengan Pemeriksaan Penunjang Medis',
                                'catatan': catatan,
                                'jenis_pasien': jenisPeriksaPasien,
                                'tgl_kontrol': tglKontrol,
                                'kategori_lab': kategoriLab,
                                'unit_lab': unitLab,
                                'parameter': parameterLab,
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
                console.log(tindakLanjut);
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
                    'id_ruangan_lama': idRuanganLama
                }
                cek = true;
            }
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        RawatInapAction.saveTindakLanjutRawatInap(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#waiting_dialog').dialog('close');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(8);
                } else {
                    $('#waiting_dialog').dialog('close');
                    $('#error_dialog').dialog('open');
                    $('#errorMessage').text(res.msg);
                }
            }
        });
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
                }

                labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';
                labelPrint = '<span style="color: #367fa9; font-weight: bold">' + terIsiPrint + '</span>';

                if (item.keterangan == 'form') {
                    li += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')"><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '">' + icons + item.namaRm + ' ' + labelTerisi + tolText + '</a></li>'
                } else if (item.keterangan == "surat") {
                    li += '<li ' + tol + '><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\',\'' + item.namaRm + '\')' + '">' + icons2 + item.namaRm + ' ' + labelPrint + tolText + '</a></li>'
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
                if(item.flagApprove == null){
                    cek = false;
                }
            });
        }
        if(cek){
            $('#notif_dok').show();
        }
    });
}

function setDiet(id) {
    var bentuk = $('#bentuk_diet').val();
    var jenis = $('#jenis_diet').val();
    var bentukText = $('#bentuk_diet option:selected').text();
    var jenisText = $('#jenis_diet option:selected').text();
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
    if ($('#' + id).is(':checked')) {
        if (bentuk && jenis != '') {
            table = '<tr id="' + ket + '">' +
                '<td>' + keterangan + '<input type="hidden" value="' + ket + '" id="waktu_' + idCount + '"></td>' +
                '<td>' + bentukText +
                '<input type="hidden" value="' + bentuk + '" id="bentuk_' + idCount + '">' +
                '<input type="hidden" value="' + bentukText + '" id="bentuk_text_' + idCount + '">' +
                '</td>' +
                '<td>' + jenisText + '<input type="hidden" value="' + jenis + '" id="jenis_' + idCount + '"></td>' +
                '</tr>';
            $('#body_add_diet').append(table);
        } else {
            $('#warning_diet').show().fadeOut(5000);
            $('#msg_diet').text("Silahkan cek kembali inputan anda...!");
            if (bentuk == '') {
                $('#war_bentuk_diet').show();
            }
            if (jenis == '' || jenisDiet == null) {
                $('#war_jenis_diet').show();
            }
            $('#' + id).prop('checked', false);
        }
    } else {
        $('#' + ket).remove();
    }
}

function getListComboJenisDiet(){
    var option = '';
    RawatInapAction.getComboBoxJenisGizi(function (res) {
        if(res.length > 0){
            $.each(res, function (i, item) {
                option += '<option value="'+item.idJenisDiet+'">'+item.namaJenisDiet+'</option>'
            });
            $('#jenis_diet').html(option);
            $('#edit_jenis_diet').html(option);
        }
    });
}