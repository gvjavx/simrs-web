function getJenisResep() {
    strSelect = "";
    var arBodyJenisResep = [];
    if (jenisPeriksaPasien == "rekanan") {
        if (isBpjsRekanan == "Y") {
            arBodyJenisResep.push({
                "nilai": "bpjs",
                "label": "BPJS"
            });
            arBodyJenisResep.push({
                "nilai": "rekanan",
                "label": "REKANAN"
            });
        } else {
            arBodyJenisResep.push({
                "nilai": "rekanan",
                "label": "REKANAN"
            });
        }
    } else if (jenisPeriksaPasien == "asuransi") {
        arBodyJenisResep.push({"nilai": "asuransi", "label": "ASURANSI"}, {"nilai": "umum", "label": "UMUM"});
    } else if (jenisPeriksaPasien == "bpjs") {
        arBodyJenisResep.push({"nilai": "bpjs", "label": "BPJS"});
    } else if (jenisPeriksaPasien == "paket_perusahaan") {
        arBodyJenisResep.push({"nilai": "paket_perusahaan", "label": "Medical Checkup"});
    } else if (jenisPeriksaPasien == "paket_individu") {
        arBodyJenisResep.push({"nilai": "paket_individu", "label": "Promo"});
    } else if (jenisPeriksaPasien == "umum") {
        arBodyJenisResep.push({"nilai": "umum", "label": "UMUM"});
    }

    var strSelect = "";
    $.each(arBodyJenisResep, function (i, item) {
        strSelect += "<option value='" + item.nilai + "'>" + item.label + "</option>";
    });
    $("#select-jenis-resep").html(strSelect);
}

function cekRekakanops() {
    CheckupAction.cekRekananOps(IdAsuransi, function (res) {
        if (res.isBpjs != null && res.isBpjs != '') {
            isBpjsRekanan = res.isBpjs;
        }
    });
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
    var jenis = $('#jenis_pasien').val();
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
    var jenis = $('#jenis_pasien').val();
    if (NOSEP != '' && NOSEP != null) {
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
    } else {
        $('#status_bpjs').hide();
    }
}

function saveAlergi(id) {
    var alergi = $('#alergi').val();
    var jenis = $('#jenis_alergi').val();

    if (noCheckup != '' && alergi != '' && jenis != '') {
        if(!cekSession()){
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
        }
    } else {
        $('#warning_alergi').show().fadeOut(5000);
        if (alergi == '') {
            $('#war_alergi').show();
        }
        if (jenis == '') {
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
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editAlergi(\'' + item.idAlergi + '\',\'' + item.alergi + '\', \'' + item.jenis + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
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

function listSelectDokter(idDokter) {
    var option = "<option value=''>[Select One]</option>";
    CheckupAction.listOfDokter(idPoli, idDokter, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
            });
            $('#dok_id_dokter').html(option);
        } else {
            $('#dok_id_dokter').html(option);
        }
    });
}

function selectKeterangan(idKtg) {
    var jenisPasien = $('#jenis_pasien').val();
    if (idKtg != '') {
        if (idKtg == "selesai") {
            $('#form-selesai').show();
            $('#form-catatan').show();

            $('#form-dpjp').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pindah_poli').hide();
            $('#form-metode_pembayaran').hide();
            $('#form-asesmen').hide();
            $('#form-rujuk_internal').hide();

        } else if (idKtg == "pindah_poli") {
            $('#poli_lain').attr('disabled', false);
            $('#form-pindah_poli').show();
            $('#form-catatan').show();
            if (jenisPasien == 'umum') {
                $('#form-metode_pembayaran').show();
            } else {
                $('#form-metode_pembayaran').hide();
            }
            $('#form-selesai').hide();
            $('#form-dpjp').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-asesmen').hide();
            $('#form-rujuk_internal').hide();

        } else if (idKtg == "rawat_inap") {
            $('#form-ket-rawat_inap').show();
            $('#form-catatan').show();
            $('#form-selesai').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-rs-rujukan').hide();
            $('#form-pindah_poli').hide();
            $('#form-metode_pembayaran').hide();
            $('#form-asesmen').show();
            $('#form-rujuk_internal').hide();

        } else if (idKtg == "rujuk_rs_lain") {
            $('#form-rs-rujukan').show();
            $('#form-catatan').show();

            $('#form-selesai').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pindah_poli').hide();
            $('#form-metode_pembayaran').hide();
            $('#form-asesmen').hide();
            $('#form-rujuk_internal').hide();

        } else if (idKtg == "kontrol_ulang") {
            $('#form-tgl-kontrol').show();
            $('#form-catatan').show();

            $('#form-rs-rujukan').hide();
            $('#form-selesai').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-pindah_poli').hide();
            $('#form-metode_pembayaran').hide();
            $('#form-asesmen').hide();
            $('#form-rujuk_internal').hide();

        } else if (idKtg == "lanjut_paket") {
            var idpel = $('#h_id_pelayanan_paket_pilih').val();
            $('#poli_lain').val(idpel).trigger('change');
            $('#poli_lain').attr('disabled', true);
            $('#form-pindah_poli').show();
            $('#form-catatan').show();
            $('#form-metode_pembayaran').hide();
            $('#form-selesai').hide();
            $('#form-dpjp').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-asesmen').hide();
            $('#form-rujuk_internal').hide();

        } else {
            $('#form-selesai').hide();
            $('#form-catatan').show();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pindah_poli').hide();
            $('#form-metode_pembayaran').hide();
            if ("rujuk_internal" == idKtg) {
                $('#form-rujuk_internal').show();
                $('#form-asesmen').hide();
            } else if ("lanjut_biaya" == idKtg) {
                $('#form-asesmen').hide();
                $('#form-rujuk_internal').hide();
            } else {
                $('#form-asesmen').show();
                $('#form-rujuk_internal').hide();
            }
        }
    } else {
        $('#form-selesai').hide();
        $('#form-catatan').hide();
        $('#form-ket-rawat_inap').hide();
        $('#form-rs-rujukan').hide();
        $('#form-tgl-kontrol').hide();
        $('#form-pindah_poli').hide();
        $('#form-metode_pembayaran').hide();
        $('#form-asesmen').hide();
        $('#form-rujuk_internal').hide();
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

function confirmSaveKeterangan() {
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

    if (jenisPasien == 'umum') {
        uangMuka = uangUmum;
    }
    if (jenisPasien == 'asuransi') {
        uangMuka = uangAsuransi;
    }

    if (idKtg != '') {

        if (idKtg == "pindah") {
            if (poli != '' && idDokter != '') {
                if (isLaka == "Y") {
                    if (noRujukan != '' && tglRujukan != '' && suratRujukan != '') {
                        $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
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
                            'jenis': jenisPasien,
                            'id_pasien': idPasien,
                            'metode_bayar': metodeBayar,
                            'uang_muka': uangMuka
                        }
                        var result = JSON.stringify(data);
                        $('#save_laka').attr('onclick', 'saveDataAsuransi(\'' + result + '\')');
                    }
                } else {
                    $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
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
                if (isLaka == "Y") {
                    if (noRujukan != '' && tglRujukan != '' && suratRujukan != '') {
                        $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
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
                            'jenis': jenisPasien,
                            'id_pasien': idPasien,
                            'metode_bayar': metodeBayar,
                            'uang_muka': uangMuka
                        }
                        var result = JSON.stringify(data);
                        $('#save_laka').attr('onclick', 'saveDataAsuransi(\'' + result + '\')');
                    }
                } else {
                    $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
                    $('#modal-confirm-dialog').modal('show');
                }
            } else {
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
                if (isLaka == "Y") {
                    if (noRujukan != '' && tglRujukan != '' && suratRujukan != '') {
                        $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
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
                            'jenis': jenisPasien,
                            'id_pasien': idPasien,
                            'metode_bayar': metodeBayar,
                            'uang_muka': uangMuka
                        }
                        var result = JSON.stringify(data);
                        $('#save_laka').attr('onclick', 'saveDataAsuransi(\'' + result + '\')');
                    }
                } else {
                    if (ket_selesai == "Checkup Ulang") {
                        if (tgl_cekup != '' && ket_cekup != '') {
                            if (cekPemeriksaan) {
                                if (kategoriLab != '' && unitLab != '' && parameterLab != '' && parameterLab != null) {
                                    $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
                                    $('#modal-confirm-dialog').modal('show');
                                } else {
                                    $('#warning_ket').show().fadeOut(5000);
                                    $('#war_kolom-2').show();
                                }
                            } else {
                                $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
                                $('#modal-confirm-dialog').modal('show');
                            }
                        } else {
                            $('#warning_ket').show().fadeOut(5000);
                            $('#war_kolom-2').show();
                        }
                    } else {
                        $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
                        $('#modal-confirm-dialog').modal('show');
                    }
                }
            } else {
                $('#warning_ket').show().fadeOut(5000);
                $('#war_kolom-2').show();
            }
        }

        if (idKtg == "lanjut_biaya" || idKtg == "rujuk_rs_lain") {
            if (isLaka == "Y") {
                if (noRujukan != '' && tglRujukan != '' && suratRujukan != '') {
                    $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
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
                        'jenis': jenisPasien,
                        'id_pasien': idPasien,
                        'metode_bayar': metodeBayar,
                        'uang_muka': uangMuka
                    }
                    var result = JSON.stringify(data);
                    $('#save_laka').attr('onclick', 'saveDataAsuransi(\'' + result + '\')');
                }
            } else {
                $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
                $('#modal-confirm-dialog').modal('show');
            }
        }
    } else {
        $('#warning_ket').show().fadeOut(5000);
        $('#war_catatan').show();
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
    var jenisPasien = obj["jenis"];
    var idPasien = obj["id_pasien"];
    var metodeBayar = obj["metode_bayar"];
    var uangMuka = obj["uang_muka"];

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
                    $('#save_con').attr('onclick', 'saveKeterangan(\'' + idKtg + '\', \'' + poli + '\', \'' + kelas + '\', \'' + kamar + '\', \'' + ket_selesai + '\', \'' + tgl_cekup + '\', \'' + ket_cekup + '\', \'' + jenisPasien + '\',\'' + idPasien + '\',\'' + metodeBayar + '\', \'' + uangMuka + '\')');
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

    if (cekPemeriksaan) {
        dataPemeriksaan = {
            'kategori_lab': kategoriLab,
            'unit_lab': unitLab,
            'parameter': parameterLab
        }
    }

    if (idDokterDpjp != '' && idDokterDpjp != null) {
        var dat = idDokterDpjp.split("|");
        idDokDpjp = dat[0];
        idPelDpjp = dat[1];
    }

    if (idKtg == "pindah") {
        $('#save_ket').hide();
        $('#load_ket').show();
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, "", "", function (response) {
            if (response.status == "success") {
                $('#modal-laka').modal('hide');
                $('#waiting_dialog').dialog('close');
                $('#info_dialog').dialog('open');
                $('#close_pos').val(6);
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
    if (idKtg == "rujuk") {
        $('#save_ket').hide();
        $('#load_ket').show();
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, idPelDpjp, kelas, kamar, idDokDpjp, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, "", "", function (response) {
            if (response.status == "success") {
                $('#modal-laka').modal('hide');
                $('#waiting_dialog').dialog('close');
                $('#info_dialog').dialog('open');
                $('#close_pos').val(6);
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
    if (idKtg == "selesai" || idKtg == "lanjut_biaya" || idKtg == "rujuk_rs_lain") {

        var peme = "";
        if (dataPemeriksaan != "") {
            peme = JSON.stringify(dataPemeriksaan);
        }

        $('#save_ket').hide();
        $('#load_ket').show();
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, "", peme, function (response) {
            if (response.status == "success") {
                $('#modal-laka').modal('hide');
                $('#waiting_dialog').dialog('close');
                $('#info_dialog').dialog('open');
                $('#close_pos').val(6);
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
}

function listSelectTindakan(idKategori) {
    var option = "<option value=''>[Select One]</option>";
    if (idKategori != '') {
        CheckupDetailAction.getListComboTindakan(idKategori,idKelasRuangan, function (response) {
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

function listSelectTindakanKategori(val) {
    var option = '<option value="">[Select One]</option>';
    var idDokter = "";
    var idPelayanan = "";
    var def = '';
    var isEdit = $('#is_edit').val();
    if (val != null && val != '') {
        var dataDokter = val.split("|");
        idDokter = dataDokter[0];
        idPelayanan = dataDokter[1];
        CheckupDetailAction.getListComboTindakanKategori(idPelayanan, null, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    if (i == 0) {
                        def = item.idKategoriTindakan;
                    }
                    option += '<option value="' + item.idKategoriTindakan + '">' + item.kategoriTindakan + '</option>';
                });
                $('#tin_id_ketgori_tindakan').html(option);
                if (isEdit != "Y") {
                    setTimeout(function () {
                        $('#tin_id_ketgori_tindakan').val(def).trigger('change');
                    }, 100);
                }
            } else {
                $('#tin_id_ketgori_tindakan').html('');
            }
        });
    }

}

function getListNamaDokter(tipe) {
    var option = '<option value="">[Select One]</option>';
    var def = '';
    CheckupAction.getListDokterByIdDetailCheckup(idDetailCheckup, null, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                if (i == 0) {
                    def = item.idDokter + '|' + item.idPelayanan;
                }
                option += '<option value="' + item.idDokter + '|' + item.idPelayanan + '">' + item.namaDokter + '</option>';
            });
            $('#tin_id_dokter_dpjp').html(option);
            if (tipe != 'edit') {
                $('#tin_id_dokter_dpjp').val(def).trigger('change');
            }
        } else {
            $('#tin_id_dokter_dpjp').html(option);
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
        window.location.href = 'initForm_' + urlPage + '.action';
    } else if (back == 9) {
        desti = "#pos_rssep";
    } else if (back == 10) {
        desti = "#pos_alergi";
    } else if (back == 11) {
        desti = "#pos_icd9";
    } else if (back == 12) {
        window.location.reload(true);
    } else if (back == 13) {
        desti = "#pos_finis";
    } else if (back == 14) {
        desti = "#pos_rm";
    }
    $('html, body').animate({
        scrollTop: $(desti).offset().top
    }, 0);
}

function showModal(select) {

    var id = "";

    if (select == 1) {
        $('#dok_id_dokter').val('').trigger('change');
        var data = $('#tabel_dokter').tableToJSON();
        var aw = "(";
        var ah = ")";
        var idDokter = "";
        var temp = "";
        $.each(data, function (i, item) {
            if (idDokter != '') {
                idDokter = idDokter + ', ' + "'" + data[i]["ID Dokter"] + "'";
            } else {
                idDokter = "'" + data[i]["ID Dokter"] + "'";
            }
        });

        if (idDokter != '') {
            temp = aw + idDokter + ah;
        }
        $('#t_dokter').html("Tambah Dokter");
        listSelectDokter(temp);
        $('#load_dokter, #warning_dokter, #war_dok, #war_dpjp').hide();
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
        $('#t_diagnosa').text("Tambah Diagnosa");
        $('#nosa_id_diagnosa, #nosa_ket_diagnosa').val('');
        $('#nosa_jenis_diagnosa').val('').trigger('change');
        $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
        $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
        $('#modal-diagnosa').modal({show: true, backdrop: 'static'});

    } else if (select == 4) {
        $('.jam').timepicker();
        $('.jam').inputmask('hh:mm', {'placeholder': 'hh:mm'});
        $('.tgl').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
        $('#form_ttd').show();
        $('#lab_kategori, #lab_lab').attr('disabled', false);
        $('#lab_kategori, #lab_lab').val('').trigger('change');
        $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
        $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
        $('#modal-lab').modal({show: true, backdrop: 'static'});
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
        $('#modal-obat').modal({show: true, backdrop: 'static'});
    } else if (select == 7) {
        resetAll();
        cekRekakanops();
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
        $('#resep_jenis_obat').attr("onchange", "var warn =$('#war_jenis_obat').is(':visible'); if (warn){$('#cor_jenis_obat').show().fadeOut(3000);$('#war_jenis_obat').hide()}; setObatPoli(this.value)");
        $('#resep_apotek').attr("onchange", "var warn =$('#war_rep_apotek').is(':visible'); if (warn){$('#cor_rep_apotek').show().fadeOut(3000);$('#war_rep_apotek').hide()}");
        $('#resep_nama_obat').attr("onchange", "var warn =$('#war_rep_obat').is(':visible'); if (warn){$('#cor_rep_obat').show().fadeOut(3000);$('#war_rep_obat').hide()}; setStokObatApotek(this, \'\')");
        $('#resep_nama_obat_serupa').attr("onchange", "var warn =$('#war_rep_obat_serupa').is(':visible'); if (warn){$('#cor_rep_obat_serupa').show().fadeOut(3000);$('#war_rep_obat_serupa').hide()}; setStokObatApotek(this, \'serupa\')");
        $('#body_detail').html('');
        $('#modal-resep-head').modal({show: true, backdrop: 'static'});
        getJenisResep();
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
        $('#alergi').val('');
        $('#load_alergi').hide();
        $('#save_alergi').attr('onclick', 'saveAlergi(\'' + id + '\')').show();
        $('#modal-alergi').modal({show: true, backdrop: 'static'});
    } else if (select == 9) {
        $('#id_icd9, #ket_icd9').val('');
        $('#load_icd9').hide();
        $('#save_icd9').attr('onclick', 'saveICD9(\'' + id + '\')').show();
        $('#modal-icd9').modal({show: true, backdrop: 'static'});
    }
}


function saveDokter(id) {
    var idDokter = $('#dok_id_dokter').val();
    if (idDetailCheckup != '' && idDokter != '') {
        if(!cekSession()){
            $('#save_dokter').hide();
            $('#load_dokter').show();
            if (id != '') {
                dwr.engine.setAsync(true);
                TeamDokterAction.editDokter(id, idDokter, idPoli, function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listDokter();
                        $('#modal-dokter').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                        $('#save_dokter').show();
                        $('#load_dokter').hide();
                    } else {
                        $('#warning_dokter').show().fadeOut(5000);
                        $('#msg_dokter').text(response.msg);
                        $('#save_dokter').show();
                        $('#load_dokter').hide();
                    }
                })
            } else {
                dwr.engine.setAsync(true);
                TeamDokterAction.saveDokter(idDetailCheckup, idDokter, idPoli, function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listDokter();
                        $('#modal-dokter').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                        $('#save_dokter').show();
                        $('#load_dokter').hide();
                    } else {
                        $('#warning_dokter').show().fadeOut(5000);
                        $('#msg_dokter').text(response.msg);
                        $('#save_dokter').show();
                        $('#load_dokter').hide();
                    }
                })
            }
        }
    } else {
        $('#warning_dokter').show().fadeOut(5000);
        $('#msg_dokter').text("Silahkan cek kembali data inputan...!");
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
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDokter(\'' + item.idTeamDokter + '\',\'' + item.idDokter + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer; ">' + "</td>" +
                    "</tr>";
                dokter = item.idDokter;
            });
        }
    });
    $('#tin_id_dokter').val(dokter);
    $('#body_dokter').html(table);
}

function listDokterKeterangan(idPelayanan) {
    var option = "<option value=''>[Select One]</option>";
    CheckupAction.listOfDokter(idPelayanan, function (response) {
        if (response.length > 0) {
            $.each(response, function (i, item) {
                option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
            });
            $('#list_dokter').html(option);
        } else {
            $('#list_dokter').html(option);
        }
    });
}

function saveTindakan(id) {

    var idKategori = $('#tin_id_ketgori_tindakan').val();
    var idTindakan = $('#tin_id_tindakan').val();
    var idDokter = $('#tin_id_dokter_dpjp').val();
    var qty = $('#tin_qty').val();
    var idJenisPeriksa = $('#jenis_pasien').val();
    var idDok = "";
    var idPelayanan = "";

    if (idDetailCheckup != '' && idTindakan != '' && idTindakan != null && idDokter != '' && qty > 0 && idKategori != '' && idKategori != null) {
        if(!cekSession()){
            $('#save_tindakan').hide();
            $('#load_tindakan').show();
            idDok = idDokter.split("|")[0];
            idPelayanan = idDokter.split("|")[1];
            if (id != '') {
                dwr.engine.setAsync(true);
                TindakanRawatAction.editTindakanRawat(id, idDetailCheckup, idTindakan, idDok, "RJ", qty, idJenisPeriksa, idPelayanan, {
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
                TindakanRawatAction.saveTindakanRawat(idDetailCheckup, idTindakan, idDok, "RJ", qty, idJenisPeriksa, idPelayanan, null, {
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

        if (idDokter == '') {
            $('#war_dpjp').show();
        }
        if (idKategori == '' || idKategori == null) {
            $('#war_kategori').show();
        }
        if (idTindakan == '' || idTindakan == null) {
            $('#war_tindakan').show();
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
        if (data.length > 0) {
            $.each(data, function (i, item) {

                var tanggal = item.createdDate;
                var dateFormat = converterDate(new Date(tanggal));
                var tarif = "-";
                var tarifTotal = "-";
                var trfTotal = 0;
                var qtyTotal = 0;
                var perawat = "";
                var btn = '<img border="0" class="hvr-grow" onclick="editTindakan(\'' + item.idTindakanRawat + '\',\'' + item.idTindakan + '\',\'' + item.idKategoriTindakan + '\',\'' + item.idPerawat + '\',\'' + item.qty + '\', \'' + item.idDokter + '\', \'' + item.idPelayanan + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">';

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

                if ("Y" == item.approveFlag) {
                    btn = "";
                }

                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + item.namaTindakan + "</td>" +
                    '<td>' + item.namaDokter + '</td>' +
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

            if ("paket_perusahaan" == jenisPeriksaPasien || "paket_individu" == jenisPeriksaPasien) {
                $('#body_tindakan_paket').html(table2);
            } else {
                table = table + "<tr>" +
                    "<td colspan='5'>Total</td>" +
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
    var panjang = $('#tabel_diagnosa').tableToJSON();
    var jenisDiagnosa = "";
    if (id != '') {
        jenisDiagnosa = $('#val_jenis_diagnosa').val();
    } else {
        jenisDiagnosa = panjang.length + 1;
    }

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
                    jen = "Diagnosa Ke " + item.jenisDiagnosa;
                }
                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + id + "</td>" +
                    "<td>" + ket + "</td>" +
                    "<td>" + jen + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiagnosa(\'' + item.idDiagnosaRawat + '\',\'' + item.idDiagnosa + '\',\'' + item.jenisDiagnosa + '\', \'' + item.keteranganDiagnosa + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
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
            if (response.length > 0) {
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

    $('#lab_parameter').html('');
    $('#ckp_parameter').html('');
}

function listSelectParameter(idLab) {
    var option = "";
    if (idLab != '') {
        LabDetailAction.listLabDetail(idLab, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idLabDetail + "'>" + item.namaDetailPeriksa + "</option>";
                });
                $('#lab_parameter').html(option);
                $('#ckp_parameter').html(option);
                $('#lab_parameter option').prop('selected', true);
                $('#ckp_parameter option').prop('selected', true);
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
    var data = $('#tabel_dokter').tableToJSON();
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
    var cekPending = $('#is_pending_lab').is(':checked');
    var tglPending = $('#tgl_pending').val();
    var jamPending = $('#jam_pending').val();
    var waktu = "";
    if (idDetailCheckup != '' && idKategori != '' && idLab != '' && idParameter != null) {
        if(!cekSession()){
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
                    var saveCek = false;
                    if (cekPending) {
                        if (tglPending && jamPending != '') {
                            saveCek = true;
                            waktu = tglPending.split("-").reverse().join("-") + " " + jamPending + ":00";
                        }
                    } else {
                        saveCek = true;
                    }
                    if (saveCek) {
                        $('#save_lab').hide();
                        $('#load_lab').show();
                        dwr.engine.setAsync(true);
                        var ttd = convertToDataURL(pengirim);
                        PeriksaLabAction.saveOrderLab(idDetailCheckup, idLab, idParameter, ttd, idDokter, idKategori, waktu, {
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
                        })
                    } else {
                        $('#warning_lab').show().fadeOut(5000);
                        $('#msg_lab').text("Silhakan cek kembali tanggal dan jam inputan...!");
                        if (tglPending == '' || jamPending == '') {
                            $('#war_pending').show();
                        }
                    }
                } else {
                    $('#warning_lab').show().fadeOut(5000);
                    $('#msg_lab').text("Silhakan lakukan TTD dulu...!");
                }
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
                var btn = '<img border="0" class="hvr-grow" onclick="editLab(\'' + item.idPeriksaLab + '\',\'' + item.idLab + '\',\'' + item.idKategoriLab + '\',\'' + item.kategoriLabName + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">';
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

                if ("Pending" == item.statusPeriksaName) {
                    btn = '<a target="_blank" href="printLabRadiologi_' + urlPage + '.action?id=' + idDetailCheckup + '&tipe=' + tipe + '&lab=' + item.idPeriksaLab + '"><img border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-print-25.png" style="cursor: pointer;"></a>';
                } else {
                    if (item.approveFlag == "Y") {
                        if (item.urlImg != null) {
                            btn = '<img onclick="labLuar(\'' + lab + '\', \'' + item.urlImg + '\')" border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                        } else {
                            btn = '<a target="_blank" href="printLabRadiologi_' + urlPage + '.action?id=' + idDetailCheckup + '&tipe=' + tipe + '&lab=' + item.idPeriksaLab + '"><img border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-print-25.png" style="cursor: pointer;"></a>';
                        }
                    }
                }

                if ("paket_perusahaan" == jenisPeriksaPasien || "paket_individu" == jenisPeriksaPasien) {
                    table += "<tr>" +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + item.kategoriLabName + "</td>" +
                        "<td>" + lab + "</td>" +
                        "<td>" + status + "</td>" +
                        "<td align='center'>" + btn + "</td>" +
                        "</tr>";
                } else {
                    table += "<tr>" +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + item.kategoriLabName + "</td>" +
                        "<td>" + lab + "</td>" +
                        "<td>" + status + "</td>" +
                        "<td align='center'>" + btn + crn + "</td>" +
                        "</tr>";
                }

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

            if(!cekSession()){
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
            }
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
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editObat(\'' + item.idObatInap + '\',\'' + id + '\',\'' + qty + '\',\'' + jenis + '\',\'' + obat + '\',\'' + item.qtyBox + '\',\'' + item.qtyLembar + '\',\'' + item.qtyBiji + '\',\'' + item.lembarPerBox + '\',\'' + item.bijiPerLembar + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
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
    $('#t_dokter').html("Edit Dokter");
    listSelectDokter(null);
    $('#load_dokter, #war_dok').hide();
    $('#save_dokter').attr('onclick', 'saveDokter(\'' + id + '\')').show();
    $('#dok_id_dokter').val(idDokter).trigger('change');
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
    $('#t_diagnosa').text("Edit Diagnosa");
    var jenisPasien = $('#jenis_pasien').val();
    $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
    $('#nosa_id_diagnosa').val(idDiagnosa);
    $('#nosa_ket_diagnosa').val(ket);
    $('#val_jenis_diagnosa').val(jenis);
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

    var tableKeterangan = $('#table_keterangan').tableToJSON();
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

    if (obat && qty && apotek && jenisSatuan && jenisObat != '' && isRacik && tableKeterangan.length > 0) {

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

        var bodyKet = "";
        var tempBodyKet = [];
        var stringTempBodyKet = "";
        var keteranganTemp = "";
        var keteranganTempe = "";
        $.each(tableKeterangan, function (i, item) {
            var idWaktu = $('#waktu_'+i).val();
            var namaWaktu = $('#nama_waktu_'+i).val();
            var idParam = $('#id_param_'+i).val();
            var namaParam = $('#nama_param_'+i).val();

            bodyKet += '<tr>' +
                '<td>'+namaWaktu+'</td>'+
                '<td>'+namaParam+'</td>'+
                '</tr>';

            if(keteranganTemp != ''){
                var nwt = "";
                if(namaWaktu != ''){
                    nwt = '. '+namaWaktu+' : '+namaParam;
                }else{
                    nwt = ', '+namaParam;
                }
                keteranganTemp = keteranganTemp+nwt;
            }else{
                keteranganTemp = namaWaktu+' : '+namaParam;
            }

            if(keteranganTempe != ''){
                var nwt = "";
                if(namaWaktu != ''){
                    nwt = '|'+idWaktu+'#'+namaWaktu+' : '+namaParam;
                }else{
                    nwt = '. '+namaParam;
                }
                keteranganTempe = keteranganTempe+nwt;
            }else{
                keteranganTempe = idWaktu+'#'+namaWaktu+' : '+namaParam;
            }
        });
        if(keteranganTempe != ''){
            var sp = keteranganTempe.split('|');
            $.each(sp, function (i, item) {
                var data = item.split('#');
                tempBodyKet.push({
                    'id_waktu':data[0],
                    'keterangan':data[1]
                });
            });
            stringTempBodyKet = JSON.stringify(tempBodyKet);
        }

        var ket = "";
        if(bodyKet != ''){
            ket = '<table style="font-size: 10px" class="table table-bordered" id="tbl_keterangan_'+data.length+'">' +
            '<tbody>'+bodyKet+'</tbody>'+
            '</table>';
        }

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
                    '<textarea style="display: none" type="hidden" id="keterangan_'+count+'">'+stringTempBodyKet+'</textarea>'+
                    '<input type="hidden" value="'+keteranganTemp+'" id="keterangan_detail_'+count+'">'+
                    '<input type="hidden" value="'+flagCicik+'" id="is_racik_'+count+'">'+
                    '<input type="hidden" value="'+nameRacik+'" id="nama_racik_'+count+'">'+
                    '<input type="hidden" value="'+idRacik+'" id="id_racik_'+count+'">'+
                    '</td>' +
                    '<td align="center">' + qty +' ' +jenisSatuan+'</td>' +
                    '<td>' + ket + '</td>' +
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
                    '<img onclick="printResep(\'' + idResep + '\')" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-print-25.png" style="cursor: pointer;">' +
                    "</td>" +
                    "</tr>"
            });
            $('#body_resep').html(table);
        }
    });
}

function printResep(id) {
    window.open('printResepPasien_' + urlPage + '.action?id=' + idDetailCheckup + '&idResep=' + id, '_blank');
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
                    "<td align='center'>" + '<img border="0" id=' + idObat + ' class="hvr-grow" onclick="editObatResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + qty + '\',\'' + ket + '\',\'' + namaObat + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' +
                    '<img border="0" id=save' + idObat + ' class="hvr-grow" onclick="saveDetailResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + item.idApprovalObat + '\')" src="' + contextPath + '/pages/images/icons8-save-25.png" style="cursor: pointer; display: none">' + "</td>" +
                    "</tr>"
            });
        }
    });

    $('#body_detail_rep').html(table);
}

function editObatResep(id, idObat, qty, ket, namaObat) {

    if ($('#' + idObat).attr('src') == '/simrs/pages/images/icons8-create-25.png') {
        var url = contextPath + '/pages/images/cnacel-flat.png"/>';
        $('#' + idObat).attr('src', url);
        $('#obat' + idObat).hide();
        $('#qty' + idObat).hide();
        $('#ket' + idObat).hide();

        $('#newObat' + idObat).show().val(namaObat);
        $('#newQty' + idObat).show().val(qty);
        $('#newKet' + idObat).show().val(ket);
        $('#save' + idObat).show();

    } else {
        var url = contextPath + '/pages/images/icons8-create-25.png"/>';
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

            $("#h-qty-default").val(bijiPerLembar);
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
                if(res != null){
                    getComboParameterObat(res.idSubJenis);
                }
            });
        }
    }
}

function savePenunjangPasien() {

    var tinggi = $('#tinggi').val();
    var berat = $('#berat').val();

    if (noCheckup != '' && tinggi != '' && berat != '') {
        if(!cekSession()){
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
        }
    } else {
        $('#warning_penunjang').show().fadeOut(5000);
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
    resetComboObat();
}

function setObatPoli(jenis) {
    var poli = $('#resep_apotek').val();
    var option = "<option value=''>[Select One]</option>";
    var jenisPasien = $('#jenis_pasien').val();

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

function confirmSaveAllTindakan() {
    $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
    $('#save_con').attr('onclick', 'saveAllTindakan()');
}

function saveAllTindakan() {
    $('#modal-confirm-dialog').modal('hide');
    $('#save_all').hide();
    $('#load_all').show();
    var idJenisPeriksa = $('#jenis_pasien').val();
    if(!cekSession()){
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveApproveAllTindakanRawatJalan(idDetailCheckup, idJenisPeriksa, {
            callback: function (response) {
                if (response.status == "success") {
                    $('#success_all').show().fadeOut(5000);
                    $('#msg_all_suc').text(response.message);
                    $('#save_all').show();
                    $('#load_all').hide();
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

function defaultValByJenisSatuan(name) {
    var nilai = "1";
    if (name == "biji") {
        nilai = $("#h-qty-default").val();
        var warn =$('#war_rep_qty').is(':visible');
        if (warn){
            $('#cor_rep_qty').show().fadeOut(3000);
            $('#war_rep_qty').hide();
        }
    }
    $("#resep_qty").val(nilai);

}

function saveAnamnese() {
    var auto = $('#fisik_auto').val();
    var hetero = $('#fisik_hetero').val();
    var tensi = $('#fisik_tensi').val().replace("_", "");
    var suhu = $('#fisik_suhu').val();
    var nadi = $('#fisik_nadi').val();
    var rr = $('#fisik_rr').val();
    if (auto && hetero && tensi && suhu && nadi && rr != '') {
        if(!cekSession()){
            $('#save_fisik').hide();
            $('#load_fisik').show();
            CheckupAction.saveAnamnese(auto, hetero, noCheckup, idDetailCheckup, tensi, suhu, nadi, rr, {
                callback: function (response) {
                    if (response.status == "success") {
                        $('#suc_anamnese').show().fadeOut(5000);
                        $('#save_fisik').show();
                        $('#load_fisik').hide();
                        $('#msg_suc').text("Berhasil menyimpan data pemeriksaan fisik...");
                    } else {
                        $('#war_anamnese').show().fadeOut(5000);
                        $('#save_fisik').show();
                        $('#load_fisik').hide();
                        $('#msg_war').text("Terjadi kesalahan saat penyimpanan data...!");
                    }
                }
            });
        }
    } else {
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
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editICD9(\'' + item.idTindakanRawatIcd9 + '\',\'' + item.idIcd9 + '\',\'' + item.namaIcd9 + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
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

function getListRekamMedis(tipePelayanan, jenis, id) {
    var li = "";
    var jenisRm = "";
    if (jenis == "igd") {
        if (umur >= 0 && umur <= 17) {
            jenisRm = 'ugd_anak';
        } else if (umur >= 18 && umur <= 55) {
            jenisRm = 'ugd_dewasa';
        } else if (umur > 56) {
            jenisRm = 'ugd_geriatri';
        }
    } else {
        jenisRm = jenis;
    }
    CheckupAction.getListRekammedisPasien(tipePelayanan, jenisRm, id, function (res) {
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
                    icons2 = '<i class="fa fa-check" style="color: #449d44"></i>';
                }

                labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';
                labelPrint = '<span style="color: #367fa9; font-weight: bold">' + terIsiPrint + '</span>';

                if (item.jenis == 'ringkasan_rj') {
                    li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-television"></i>' + item.namaRm + '</a></li>'
                } else {
                    if (item.function == 'addMonitoringFisioterapi') {
                        li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-television"></i>' + item.namaRm + '</a></li>'
                    } else {
                        if (item.keterangan == 'form') {
                            li += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')"><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '">' + icons + item.namaRm + ' ' + labelTerisi + tolText + '</a></li>'
                        } else if (item.keterangan == "surat") {
                            li += '<li ' + tol + '><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\',\'' + item.namaRm + '\')' + '">' + icons2 + item.namaRm + ' ' + labelPrint + tolText + '</a></li>'
                        }
                    }
                }
            });
            $('#asesmen_rj').html(li);
        }
    });
}

function getDokterDpjp() {
    var option = '<option value="">[Select One]</option>';
    CheckupAction.getListDokterByBranchId(null, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                option += '<option value="' + item.idDokter + '|' + item.idPelayanan + '">' + item.namaDokter + ' - ' + item.namaPelayanan + '</option>';
            });
            $('#dokter_dpjp').html(option);
        } else {
            $('#dokter_dpjp').html(option);
        }
    });
}

function isPemeriksaan(id, idTujuan) {
    var cek = $('#' + id).is(':checked');
    if (cek) {
        $('#' + idTujuan).show();
    } else {
        $('#' + idTujuan).hide();
    }
}

function confirmPemeriksaanPasien() {
    var data = "";
    var tindakLanjut = $('#keterangan').val();
    var catatan = $('#pesan_dokter').val();
    var keterangan = $('#ket_selesai').val();
    var ketRawatInap = $('#keterangan_rw').val();
    var poliLain = $('#poli_lain').val();
    var listDokter = $('#list_dokter').val();
    var rsRujukan = $('#rs_rujukan').val();
    var tglKontrol = $('#tgl_kontrol').val();
    var jenisPasien = $('#jenis_pasien').val();
    var metodeBayar = $('#metode_bayar').val();
    var valUangMuka = $('#val_uang_muka').val();
    var cek = false;
    var cekTindakan = $('#tabel_tindakan').tableToJSON();
    var cekDiagnosa = $('#tabel_diagnosa').tableToJSON();
    var poliRujukInternal = $('#rujuk_internal').val();
    var message = "Silahkan cek kembali data inputan anda..!";

    if (cekDiagnosa.length > 0) {
        if (cekTindakan.length > 0) {
            if (tindakLanjut != '') {

                if (tindakLanjut == "rawat_inap") {
                    if (ketRawatInap != '') {
                        cek = true;
                    }
                } else if (tindakLanjut == "pindah_poli") {
                    if (poliLain != '' && listDokter != '') {
                        if (jenisPasien == 'umum') {
                            if (metodeBayar != '' && valUangMuka != '') {
                                cek = true;
                            }
                        } else {
                            cek = true;
                        }
                    }
                } else if (tindakLanjut == "kontrol_ulang") {
                    if (tglKontrol != '') {
                        cek = true;
                    }
                } else if (tindakLanjut == "rujuk_rs_lain") {
                    if (rsRujukan != '') {
                        cek = true;
                    }
                } else if (tindakLanjut == "rujuk_internal") {
                    if (poliRujukInternal != '') {
                        cek = true;
                    }
                } else {
                    cek = true;
                }
            }
        } else {
            message = "Tindakan pasien tidak boleh kosong...!";
        }
    } else {
        message = "Diagnosa Pasien tidak boleh kosong...!";
    }

    if (cek) {
        if(jenisPasien == 'asuransi' && isLaka == 'Y'){
            if(noRujukan == '' || noRujukan == null ||
                tglRujukan == '' || tglRujukan == null ||
                suratRujukan == '' || suratRujukan == null){
                $('#laka_no_polisi').val(noRujukan);
                $('#laka_tgl_kejadian').val(tglRujukan);
                $('#laka_surat_rujukan').val(suratRujukan);
                $('#save_laka').attr('onclick', 'cekDataAsuransi()');
                $('#modal-laka').modal({show: true, backdrop: 'static'});
            }else{
                $('#save_con').attr('onclick', 'savePemeriksaanPasien()');
                $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            }
        }else{
            $('#save_con').attr('onclick', 'savePemeriksaanPasien()');
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
        }
    } else {
        $('#warning_ket').show().fadeOut(5000);
        $('#warning_msg').text(message);
    }
}

function savePemeriksaanPasien() {
    $('#modal-confirm-dialog').modal('hide');
    var data = "";
    var dataAsuransi = "";
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
    var poliLain = $('#poli_lain').val();
    var listDokter = $('#list_dokter').val();
    var metodeBayar = $('#metode_bayar').val();
    var valUangMuka = $('#val_uang_muka').val();
    var idPelayananPaket = $('#h_id_pelayanan_paket').val();
    var poliRujukInternal = $('#rujuk_internal').val();
    var urutanPaket = $('#h_urutan_paket').val();
    var idPaket = $('#h_id_paket').val();
    var uangMuka = "";
    if (valUangMuka != undefined) {
        uangMuka = valUangMuka.replace(/[.]/g, '');
    }
    var noPolisi = $('#laka_no_polisi').val();
    var tglKejadian = $('#laka_tgl_kejadian').val();
    var canvas = document.getElementById('temp_surat_rujuk');
    var dataURL = canvas.toDataURL("image/png"),
        dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
    var cek = false;

    if (tindakLanjut != '') {

        if(jenisPeriksaPasien == 'asuransi' && isLaka == 'Y'){
            if(noRujukan == '' || noRujukan == null ||
                tglRujukan == '' || tglRujukan == null ||
                suratRujukan == '' || suratRujukan == null){
                dataAsuransi = {
                    'no_polisi': noPolisi,
                    'tgl_kejadian': tglKejadian,
                    'surat_rujukan': dataURL
                }
            }
        }

        if (tindakLanjut == "rawat_inap") {
            if (ketRawatInap != '') {
                data = {
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup,
                    'tindak_lanjut': tindakLanjut,
                    'keterangan': 'Rawat Inap, ' + ketRawatInap,
                    'catatan': catatan,
                    'jenis_pasien': jenisPeriksaPasien
                }
                cek = true;
            }
        } else if (tindakLanjut == "pindah_poli") {
            if (poliLain != '' && listDokter != '') {
                data = {
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup,
                    'tindak_lanjut': tindakLanjut,
                    'keterangan': 'Pindah Poli',
                    'catatan': catatan,
                    'jenis_pasien': jenisPeriksaPasien,
                    'poli_lain': poliLain,
                    'id_dokter': listDokter,
                    'metode_bayar': metodeBayar,
                    'uang_muka': uangMuka
                }
                cek = true;
            }
        } else if (tindakLanjut == "rujuk_rs_lain") {
            if (rsRujukan != '') {
                data = {
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup,
                    'tindak_lanjut': tindakLanjut,
                    'keterangan': 'Rujuk Ke RS ' + rsRujukan,
                    'catatan': catatan,
                    'jenis_pasien': jenisPeriksaPasien,
                    'rs_rujukan': rsRujukan
                }
                cek = true;
            }
        } else if (tindakLanjut == "kontrol_ulang") {
            if (tglKontrol != '') {
                if (isPemeriksaan) {
                    if (kategoriLab && unitLab && parameterLab != '') {
                        data = {
                            'no_checkup': noCheckup,
                            'id_detail_checkup': idDetailCheckup,
                            'tindak_lanjut': tindakLanjut,
                            'keterangan': 'Kontrol Ulang dengan Pemeriksaan Penunjang Medis',
                            'catatan': catatan,
                            'jenis_pasien': jenisPeriksaPasien,
                            'tgl_kontrol': tglKontrol,
                            'kategori_lab': kategoriLab,
                            'unit_lab': unitLab,
                            'parameter': parameterLab,
                            'is_order_lab': 'Y'
                        }
                        cek = true;
                    }
                } else {
                    data = {
                        'no_checkup': noCheckup,
                        'id_detail_checkup': idDetailCheckup,
                        'tindak_lanjut': tindakLanjut,
                        'keterangan': 'Kontrol Ulang',
                        'catatan': catatan,
                        'jenis_pasien': jenisPeriksaPasien,
                        'tgl_kontrol': tglKontrol
                    }
                    cek = true;
                }
            }
        } else if (tindakLanjut == 'lanjut_paket') {
            data = {
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup,
                'tindak_lanjut': tindakLanjut,
                'poli_lain': poliLain,
                'keterangan': 'Lanjut Paket',
                'catatan': catatan,
                'id_paket': idPaket,
                'id_dokter': listDokter,
                'id_paket_pelayanan': idPelayananPaket,
                'urutan_paket': urutanPaket,
                'jenis_pasien': jenisPeriksaPasien
            }
            cek = true;
        } else if (tindakLanjut == 'rujuk_internal') {
            data = {
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup,
                'tindak_lanjut': tindakLanjut,
                'id_poli_internal': poliRujukInternal,
                'keterangan': 'Rujuk Internal',
                'catatan': catatan,
                'jenis_pasien': jenisPeriksaPasien
            }
            cek = true;
        } else {
            var ket = tindakLanjut.replace("_", " ");
            var ktr = convertSentenceCaseUp(ket);

            data = {
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup,
                'tindak_lanjut': tindakLanjut,
                'keterangan': ktr,
                'catatan': catatan,
                'jenis_pasien': jenisPeriksaPasien
            }
            cek = true;
        }
    }

    if (cek) {
        if(!cekSession()){
            var result = JSON.stringify(data);
            var tempAsuransi = JSON.stringify(dataAsuransi);
            $('#modal-laka').modal('hide');
            $('#waiting_dialog').dialog('open');
            dwr.engine.setAsync(true);
            CheckupDetailAction.savePeriksaPasien(result, tempAsuransi, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#waiting_dialog').dialog('close');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(6);
                        if('rujuk_internal' == tindakLanjut){
                            window.open('printNoRujukan_checkupdetail.action?id='+idDetailCheckup, '_blank');
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

function cekDataAsuransi() {
    var noPolisi = $('#laka_no_polisi').val();
    var tglKejadian = $('#laka_tgl_kejadian').val();
    var suratRujukan = $('#laka_surat_rujukan').val();
    var canvas = document.getElementById('temp_surat_rujuk');
    var dataURL = canvas.toDataURL("image/png"),
        dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
    if(noPolisi && tglKejadian && suratRujukan != ''){
        $('#save_con').attr('onclick', 'savePemeriksaanPasien()');
        $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
    }else{
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

function resetComboObat() {
    $("#resep_nama_obat").removeAttr("disabled");
    $("#btn-reset-combo-obat").hide();
    $("#obat-serupa").hide();
    $("#flag-obat-serupa").val("N");
    $("#resep_stok_biji_serupa").val("");
}

function setRekamMedis() {
    getListRekamMedis(jenisTrans, jenisPelayanan, idDetailCheckup);
}

function cekParameter(val) {
    $('.parameter').on('select2:select', function (e) {
        var data = e.params.data;
        if (data.id == "LDB00000001") {
            $('#lab_parameter option').prop('selected', true);
        }
    });

    $('.parameter').on('select2:unselect', function (e) {
        var data = e.params.data;
        if (data.id == "LDB00000001") {
            $('#lab_parameter').find("option").prop("selected", false);
        }
    });
}

function showDetailPaket() {
    CheckupAction.cekPelayananPaket(noCheckup, function (res) {
        if (res.length > 0) {
            $('#form_detail_paket').show();
            var table = "";
            var cek = false;
            var cekUrutan = "";
            $.each(res, function (i, item) {
                var periksa = "Belum Periksa";
                var color = "";
                if (item.isPeriksa != null && item.isPeriksa != '') {
                    if (item.idDetailCheckup != null && item.idDetailCheckup != '') {
                        periksa = "Sedang Periksa";
                    } else {
                        periksa = "Belum Periksa";
                    }
                } else {
                    cek = true;
                }
                if (idPoli == item.idPelayanan) {
                    color = 'bgcolor="#30d196"';
                    cekUrutan = item.urutan + 1;
                    $('#h_id_paket').val(item.idPaket);
                }
                if (parseInt(cekUrutan) == item.urutan) {
                    $('#h_id_pelayanan_paket').val(item.idPelayananPaket);
                    $('#h_urutan_paket').val(item.urutan);
                    $('#h_id_pelayanan_paket_pilih').val(item.idPelayanan);
                }
                table += '<tr ' + color + '>' +
                    '<td>' + item.namaPelayanan + '</td>' +
                    '<td align="center">' + periksa + '</td>' +
                    '</tr>'
            });
            if (cek) {
                $('#h_lanjut_paket').val("Y");
                isLanjutPaket = true;
            }
            $('#body_detail_paket').html(table);
        }
    });
}

function setKeteranganPeriksa() {
    var option = '<option value="">[Select One]</option>';
    if (jenisPeriksaPasien == 'umum' || jenisPeriksaPasien == 'rekanan') {
        option = option + ' <option value="selesai">Selesai</option>';
        if (urlPage == "checkupdetail") {
            option = option + '<option value="pindah_poli">Pindah Poli</option>\n';
        }
        option = option + '<option value="rawat_inap">Rawat Inap</option>\n' +
            '<option value="rawat_intensif">Rawat Intensif</option>\n' +
            '<option value="rawat_isolasi">Rawat Isolasi</option>\n' +
            '<option value="kamar_operasi">Kamar Operasi</option>\n';
        if (jenisKelamin == "Perempuan") {
            option = option + '<option value="ruang_bersalin">Ruang Bersalin</option>';
        }
        option = option + '<option value="rujuk_rs_lain">Dirujuk</option>\n' +
            '<option value="kontrol_ulang">Kontrol Ulang</option>';
    } else if (jenisPeriksaPasien == 'bpjs' || jenisPeriksaPasien == 'asuransi') {
        option = option + ' <option value="selesai">Selesai</option>\n';
        if (urlPage == "checkupdetail") {
            if (jenisPeriksaPasien == "bpjs") {
                option = option + '<option value="rujuk_internal">Rujuk Internal</option>\n';
            } else {
                option = option + '<option value="pindah_poli">Pindah Poli</option>\n';
            }
        }
        option = option + '<option value="rawat_inap">Rawat Inap</option>\n' +
            '<option value="rawat_intensif">Rawat Intensif</option>\n' +
            '<option value="rawat_isolasi">Rawat Isolasi</option>\n' +
            '<option value="kamar_operasi">Kamar Operasi</option>';
        if (jenisKelamin == "Perempuan") {
            option = option + '<option value="ruang_bersalin">Ruang Bersalin</option>';
        }
        option = option + '<option value="rujuk_rs_lain">Dirujuk</option>\n' +
            '<option value="kontrol_ulang">Kontrol Ulang</option>' +
            '<option value="lanjut_biaya">Lanjut Biaya</option>';
    } else {
        if (isLanjutPaket) {
            option = option + '<option value="lanjut_paket">Lanjut Paket</option>';
        } else {
            option = option + '<option value="selesai">Selesai</option>' +
                '<option value="kontrol_ulang">Kontrol Ulang</option>';
        }
    }
    $('#keterangan').html(option);
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
                } else {
                    $('#h_harga').val("Rp. " + formatRupiahAtas(res.tarif));
                }
                $('#h_diskon').val(disk);
                if ("Y" == res.isElektif) {
                    $('#form_elektif').show();
                    $('#is_elektif').val("Y");
                } else {
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
    if(namaWaktu && param != '' && ket != null){
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
        var option = '<option value="">[Select One]</option>';
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
        var option = '<option value="">[Select One]</option>';
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

function delKet(param){
    $('#'+param).remove();
}
