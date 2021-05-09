function getJenisResep(id) {
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
    } else if (jenisPeriksaPasien == "bpjs_rekanan") {
        arBodyJenisResep.push({"nilai": "bpjs", "label": "BPJS REKANAN"});
    }

    var strSelect = "";
    $.each(arBodyJenisResep, function (i, item) {
        strSelect += "<option value='" + item.nilai + "'>" + item.label + "</option>";
    });
    $("#"+id).html(strSelect);
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
            if (response.coverBiaya != null) {
                $('#status_asuransi').show();
                if (response.coverBiaya != null) {
                    var coverBiaya = response.coverBiaya;
                    var biayaTindakan = response.tarifTindakan;

                    var persen = "";
                    if (coverBiaya != null) {
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

                    if (coverBiaya != null) {
                        $('#sts_cover_biaya_asuransi').html(barBpjs);
                        $('#b_asuransi').html(formatRupiah(coverBiaya) + " (100%)");
                    }

                    if (biayaTindakan != null) {
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
            if (response.tarifBpjs != null) {
                var coverBiaya = response.tarifBpjs;
                var biayaTindakan = response.tarifTindakan;
                $('#kode_cbg').text(response.kodeCbg);

                var persen = "";
                if (coverBiaya != null && biayaTindakan != null) {
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

                if (coverBiaya != null) {
                    $('#sts_cover_biaya').html(barBpjs);
                    $('#b_bpjs').html(formatRupiah(coverBiaya) + " (100%)");
                }

                if (biayaTindakan != null) {
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
        if (!cekSession()) {
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
                    "<td>" + item.jenis + "</td>" +
                    "<td>" + item.alergi + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editAlergi(\'' + item.idAlergi + '\',\'' + item.alergi + '\', \'' + item.jenis + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>";
            });
        }
        $('#body_alergi').html(table);
    });
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
    var keteranganPindah = $('#keterangan option:selected').text();
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
            $('#form_eksekutif').hide();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else if (idKtg == "pindah_poli") {
            $('#poli_lain').attr('disabled', false);
            $('#form-pindah_poli').show();
            $('#form-catatan').show();
            if (jenisPasien == 'umum') {
                $('#form_eksekutif').show();
            } else {
                $('#form_eksekutif').hide();
            }
            $('#form-metode_pembayaran').hide();
            $('#form-selesai').hide();
            $('#form-dpjp').hide();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-asesmen').hide();
            $('#form-rujuk_internal').hide();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

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
            $('#form_eksekutif').hide();
            $('#title_asesmen').html(keteranganPindah);
            $('#btn_pindah').attr('onclick', 'setRekamMedisPindah(\'pindah_ri\',\'asesmen_pindah\')');
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

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
            $('#form_eksekutif').hide();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

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
            $('#form_eksekutif').hide();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

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
            $('#form_eksekutif').hide();
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');

        } else {
            $('#form-selesai').hide();
            $('#form-catatan').show();
            $('#form-ket-rawat_inap').hide();
            $('#form-rs-rujukan').hide();
            $('#form-tgl-kontrol').hide();
            $('#form-pindah_poli').hide();
            $('#form-metode_pembayaran').hide();
            $('#form_eksekutif').hide();
            if ("rujuk_internal" == idKtg) {
                $('#form-rujuk_internal').show();
                $('#form-asesmen').hide();
            } else if ("lanjut_biaya" == idKtg) {
                $('#form-asesmen').hide();
                $('#form-rujuk_internal').hide();
            } else {
                $('#form-asesmen').show();
                $('#form-rujuk_internal').hide();
                if ("kamar_operasi" == idKtg) {
                    $('#title_asesmen').html(keteranganPindah);
                    $('#btn_pindah').attr('onclick', 'setRekamMedisPindah(\'pindah_ok\',\'asesmen_pindah\')');
                } else {
                    $('#title_asesmen').html(keteranganPindah);
                    $('#btn_pindah').attr('onclick', 'setRekamMedisPindah(\'pindah_ri\',\'asesmen_pindah\')');
                }
            }
            $('#form_order_pemeriksaan').hide();
            $('#body_order_pemeriksaan').html('');
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
        $('#form_eksekutif').hide();
        $('#form_order_pemeriksaan').hide();
        $('#body_order_pemeriksaan').html('');
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
        if (!cekSession()) {
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
    var option = "<option value=''> - </option>";
    if (idKategori != '') {
        dwr.engine.setAsync(true);
        CheckupDetailAction.getListComboTindakan(idKategori, idKelasRuangan, flagVaksin, {
            callback:function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idTindakan + "'>" + item.tindakan + "</option>";
                    });
                    $('#tin_id_tindakan').html(option);
                } else {
                    $('#tin_id_tindakan').html('');
                }
            }
        });
    } else {
        $('#tin_id_tindakan').html('');
    }
}

function listSelectTindakanKategori(val) {
    var option = '<option value=""> - </option>';
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
    var option = '<option value=""> - </option>';
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
        desti = "#pos_diet";
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
            temp = idDokter;
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
        $('.remove_cek').attr('checked', false);
        $('#untuk1').prop('checked', true);
        $('#body_add_diet').html('');
        getListComboJenisDiet();
        $('#bentuk_diet, #keterangan_diet').val('').trigger('change');
        $('#bentuk_diet, #keterangan_diet').val('').removeAttr('disabled');
        $('#save_diet').attr('onclick', 'saveDiet(\'' + id + '\')').show();
        $('#load_diet, #warning_diet, #war_bentuk_diet, #war_keterangan_diet').hide();
        $('#modal-diet').modal({show: true, backdrop: 'static'});
    } else if (select == 7) {
        resetAll();
        cekRekakanops();
        getApotekRawatJalan();
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
        $('#resep_jenis_obat').attr("onchange", "var warn =$('#war_jenis_obat').is(':visible'); if (warn){$('#cor_jenis_obat').show().fadeOut(3000);$('#war_jenis_obat').hide()}; setObatPoli(this.value)");
        $('#resep_apotek').attr("onchange", "var warn =$('#war_rep_apotek').is(':visible'); if (warn){$('#cor_rep_apotek').show().fadeOut(3000);$('#war_rep_apotek').hide()}; setObatPoli()");
        $('#resep_nama_obat').attr("onchange", "var warn =$('#war_rep_obat').is(':visible'); if (warn){$('#cor_rep_obat').show().fadeOut(3000);$('#war_rep_obat').hide()}; setStokObatApotek(this, \'\')");
        $('#resep_nama_obat_serupa').attr("onchange", "var warn =$('#war_rep_obat_serupa').is(':visible'); if (warn){$('#cor_rep_obat_serupa').show().fadeOut(3000);$('#war_rep_obat_serupa').hide()}; setStokObatApotek(this, \'serupa\')");
        $('#body_detail').html('');
        $('#modal-resep-head').modal({show: true, backdrop: 'static'});
        getJenisResep('select-jenis-resep');
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

function getApotekRawatJalan() {
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
    if (idDetailCheckup != '' && idDokter != '') {
        if (!cekSession()) {
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
                    // "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDokter(\'' + item.idTeamDokter + '\',\'' + item.idDokter + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer; ">' + "</td>" +
                    "</tr>";
                dokter = item.idDokter;
            });
        }
        $('#tin_id_dokter').val(dokter);
        $('#body_dokter').html(table);
    });
}

function listDokterKeterangan(idPelayanan) {
    $('#list_dokter').val('');
    PelayananAction.getDataPelayanan(idPelayanan, function (res) {
        var option2 = "<option value=''> - </option>";
        if (res.idPelayanan != null) {
            if (res.isVaksin == "Y") {
                $('#form_vaksin').show();
            } else {
                $('#form_vaksin').hide();
            }
        }
    });
    // var option = "<option value=''> - </option>";
    // CheckupAction.listOfDokter(idPelayanan, function (response) {
    //     if (response.length > 0) {
    //         $.each(response, function (i, item) {
    //             option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
    //         });
    //         $('#list_dokter').html(option);
    //     } else {
    //         $('#list_dokter').html(option);
    //     }
    // });
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
        if (!cekSession()) {
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
        option = "<option value=''> - </option>";
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
        if (!cekSession()) {
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

                var blink = "";
                if("B20" == id){
                    blink = 'class="blink_me_atas" style="color: red"';
                }

                if (item.keteranganDiagnosa != null) {
                    ket = item.keteranganDiagnosa;
                }
                if (item.jenisDiagnosa != null) {
                    jen = "Diagnosa Ke " + item.jenisDiagnosa;
                }
                table += '<tr '+blink+'>' +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + id + "</td>" +
                    "<td>" + ket + "</td>" +
                    "<td>" + jen + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiagnosa(\'' + item.idDiagnosaRawat + '\',\'' + item.idDiagnosa + '\',\'' + item.jenisDiagnosa + '\', \'' + item.keteranganDiagnosa + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
            });
        }
        $('#body_diagnosa').html(table);
    });
}

function listSelectLab(idKategori) {
    var option = "<option value=''> - </option>";
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

var listPenunjang = [];
function showModalListPenunjang() {
    listPenunjang = [];
    var idKategori = $("#lab_kategori option:selected").val();
    if (idKategori != '') {
        LabAction.listLab(idKategori, function (response) {
            if (response.length > 0) {
                var str = "";
                $.each(response, function (i, item) {
                    // str += "<option value='" + item.idLab + "'>" + item.namaLab + "</option>";
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
    var option = "";
    if (idLab != '') {
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
                $('#lab_parameter').html(option);
                $('#ckp_parameter').html(option);
                $('#lab_parameter option').prop('selected', false);
                $('#ckp_parameter option').prop('selected', false);
            }
        });
    } else {
        $('#lab_parameter').html(option);
        $('#ckp_parameter').html(option);
        $('#lab_parameter option').prop('selected', false);
        $('#ckp_parameter option').prop('selected', false);
    }
}

function saveLab(id) {
    var table = $('#tabel_pemeriksaan').tableToJSON().length;
    var cekLuar = $('#is_luar').is(':checked');
    var isLuar = "N";
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
    var tarifLabLuar = $('#h_total_tarif').val();

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
                    dalam = '<img border="0" class="hvr-grow" onclick="hasilUploadRJ(\'' + item.idHeaderPemeriksaan + '\',\'dalam\',\'' + item.kategoriLabName + '\')" src="' + contextPath + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                    hiddeDalam = JSON.stringify(item.uploadDalam);
                }
                if (item.uploadLuar.length > 0) {
                    luar = '<img border="0" class="hvr-grow" onclick="hasilUploadRJ(\'' + item.idHeaderPemeriksaan + '\',\'luar\',\'' + item.kategoriLabName + '\')" src="' + contextPath + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                    hiddeLuar = JSON.stringify(item.uploadLuar);
                }

                var periksa = "";
                if ("Y" == item.isPeriksaLuar) {
                    periksa = '<span class="span-warning">Periksa Luar</span>';
                }

                var print = "";

                if ("Pending" == item.statusPeriksaName) {
                    btn = '<a target="_blank" href="printLabRadiologi_' + urlPage + '.action?id=' + idDetailCheckup + '&tipe=' + tipe + '&lab=' + item.idHeaderPemeriksaan + '"><img border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-print-25.png" style="cursor: pointer;"></a>';
                } else if ("Selesai" == item.statusPeriksaName) {
                    btn = '<a target="_blank" href="printLabRadiologi_' + urlPage + '.action?id=' + idDetailCheckup + '&tipe=' + tipe + '&lab=' + item.idHeaderPemeriksaan + '"><img border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-print-25.png" style="cursor: pointer;"></a>';
                    print = '<a target="_blank" href="printLabRadiologi_' + urlPage + '.action?id=' + idDetailCheckup + '&tipe=' + tipe + '&lab=' + item.idHeaderPemeriksaan + '"><img border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-print-25.png" style="cursor: pointer;"></a>';
                } else if ("Antrian" == item.statusPeriksaName) {
                    btn = '<img border="0" class="hvr-grow" onclick="editLab(\'' + item.idHeaderPemeriksaan + '\', \'' + item.idKategoriLab + '\', \'' + item.isPeriksaLuar + '\', \'' + item.statusPeriksaName + '\', \'' + tanggal + '\', \''+item.jenisPeriksaPasien+'\', \''+item.tarifLabLuar+'\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">';
                }

                if ("paket_perusahaan" == jenisPeriksaPasien || "paket_individu" == jenisPeriksaPasien) {
                    table += "<tr>" +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + item.kategoriLabName + ' ' +periksa+ "</td>" +
                        "<td>" + status + "</td>" +
                        "<td align='center'>" + print + crn + "</td>" +
                        "</tr>";
                } else {
                    table += "<tr>" +
                        "<td>" +
                        dateFormat +
                        '<textarea style="display: none" id="dalam_' + item.idHeaderPemeriksaan + '">' + hiddeDalam + '</textarea>' +
                        '<textarea style="display: none" id="luar_' + item.idHeaderPemeriksaan + '">' + hiddeLuar + '</textarea>' +
                        "</td>" +
                        "<td>" + item.kategoriLabName+ ' ' +periksa+ "</td>" +
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

            if (!cekSession()) {
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
    var data = $('#tabel_dokter').tableToJSON();
    var tempDokter = "";
    var temp = "";
    $.each(data, function (i, item) {
        if (tempDokter != '') {
            if (data[i]["ID Dokter"] != idDokter) {
                tempDokter = tempDokter + ', ' + "'" + data[i]["ID Dokter"] + "'";
            }
        } else {
            if (data[i]["ID Dokter"] != idDokter) {
                tempDokter = "'" + data[i]["ID Dokter"] + "'";
            }
        }
    });

    if (tempDokter != '') {
        temp = tempDokter;
    }
    $('#t_dokter').html("Edit Dokter");
    listSelectDokter(temp);
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

function listSelectObat(select) {
    var idx = select.selectedIndex;
    var idJenis = select.options[idx].value;
    var option = "<option value=''> - </option>";
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
    // alert(apotek);
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

    var waktuResep = $('[name=waktu_resep]');

    var tempWak = "";
    var liWak = "";
    var ulWak = "";

    $.each(waktuResep, function (i, item) {
        if(item.checked){
            if(tempWak != ''){
                tempWak = tempWak+'#'+item.value;
            }else{
                tempWak = item.value;
            }
            liWak += '<li>'+item.value+'</li>';
        }
    });

    if(liWak != ''){
        ulWak = '<ul style="margin-left: 20px">'+liWak+'</ul>';
    }

    if ($('#racik_racik').is(':checked')) {
        if (namaRacik != '') {
            isRacik = true;
        }
    } else {
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
        //     var idWaktu = $('#waktu_' + i).val();
        //     var namaWaktu = $('#nama_waktu_' + i).val();
        //     var idParam = $('#id_param_' + i).val();
        //     var namaParam = $('#nama_param_' + i).val();
        //
        //     bodyKet += '<tr>' +
        //         '<td>' + namaWaktu + '</td>' +
        //         '<td>' + namaParam + '</td>' +
        //         '</tr>';
        //
        //     if (keteranganTemp != '') {
        //         var nwt = "";
        //         if (namaWaktu != '') {
        //             nwt = '. ' + namaWaktu + ' : ' + namaParam;
        //         } else {
        //             nwt = ', ' + namaParam;
        //         }
        //         keteranganTemp = keteranganTemp + nwt;
        //     } else {
        //         keteranganTemp = namaWaktu + ' : ' + namaParam;
        //     }
        //
        //     if (keteranganTempe != '') {
        //         var nwt = "";
        //         if (namaWaktu != '') {
        //             nwt = '|' + idWaktu + '#' + namaWaktu + ' : ' + namaParam;
        //         } else {
        //             nwt = '. ' + namaParam;
        //         }
        //         keteranganTempe = keteranganTempe + nwt;
        //     } else {
        //         keteranganTempe = idWaktu + '#' + namaWaktu + ' : ' + namaParam;
        //     }
        // });
        // if (keteranganTempe != '') {
        //     var sp = keteranganTempe.split('|');
        //     $.each(sp, function (i, item) {
        //         var data = item.split('#');
        //         tempBodyKet.push({
        //             'id_waktu': data[0],
        //             'keterangan': data[1]
        //         });
        //     });
        //     stringTempBodyKet = JSON.stringify(tempBodyKet);
        // }
        //
        // var ket = "";
        // if (bodyKet != '') {
        //     ket = '<table style="font-size: 10px" class="table table-bordered" id="tbl_keterangan_' + data.length + '">' +
        //         '<tbody>' + bodyKet + '</tbody>' +
        //         '</table>';
        // }

        if (parseInt(qty) <= parseInt(stok)) {
            $.each(data, function (i, item) {
                var idObatTable = $('#id_obat_' + i).val();
                if (idObatTable == id) {
                    cek = true;
                }
            });
            if (cek) {
                $('#warning_data_exits').show().fadeOut(5000);
            } else {
                var kronis = "";
                var hrKronis = "";
                if (flagKronis == 'Y') {
                    kronis = ' ' + '<span style="font-size: 10px;\n' +
                        '        padding: 4px;\n' +
                        '        color: black;\n' +
                        '        background-color: #ffff00;\n' +
                        '        border-radius: 5px;">kronis</span>';

                    hrKronis = ' ' + '<span style="font-size: 10px;\n' +
                        '        padding: 4px;\n' +
                        '        color: black;\n' +
                        '        background-color: #4d4dff;\n' +
                        '        border-radius: 5px;">' + hariKronis + ' hari</span>';
                }
                var cicik = "";
                var namaCicik = "";
                var flagCicik = "";
                var nameRacik = "";
                var idRacik = "";
                if ($('#racik_racik').is(':checked')) {
                    flagCicik = "Y";
                    nameRacik = namaRacik;
                    idRacik = nameRacik.toLowerCase().replace(/[' ']/g, '_');
                    var warnaRacik = $('#color_racik').val();
                    cicik = ' ' + '<span style="font-size: 10px;\n' +
                        '        padding: 4px;\n' +
                        '        color: white;\n' +
                        '        background-color: #f56954;\n' +
                        '        border-radius: 5px;">racik</span>';
                    namaCicik = '<span style="font-size: 10px;\n' +
                        '        padding: 4px;\n' +
                        '        color: white;\n' +
                        '        background-color: ' + warnaRacik + ';\n' +
                        '        border-radius: 5px;">' + namaRacik + '</span>' + ' ';
                }
                var count = data.length;
                var totalHarga = parseInt(qty) * parseInt(harga);
                $('#resep_apotek').attr('disabled', true);
                $('#desti_apotek').html(namaPelayanan);
                var row = '<tr id=' + id + '>' +
                    '<td>' + namaCicik + nama + cicik + kronis +
                    '<input type="hidden" value="' + id + '" id="id_obat_' + count + '">' +
                    '<input type="hidden" value="' + qty + '" id="qty_' + count + '">' +
                    '<input type="hidden" value="' + jenisSatuan + '" id="jenis_satuan_' + count + '">' +
                    '<input type="hidden" value="' + jenisResep + '" id="jenis_resep_' + count + '">' +
                    '<input type="hidden" value="' + hariKronis + '" id="hari_kronis_' + count + '">' +
                    '<textarea style="display: none" type="hidden" id="keterangan_' + count + '"></textarea>' +
                    '<input type="hidden" id="keterangan_detail_' + count + '">' +
                    '<input type="hidden" value="' + flagCicik + '" id="is_racik_' + count + '">' +
                    '<input type="hidden" value="' + nameRacik + '" id="nama_racik_' + count + '">' +
                    '<input type="hidden" value="' + idRacik + '" id="id_racik_' + count + '">' +
                    '<input type="hidden" value="'+tempWak+'" id="temp_waktu_'+count+'">'+
                    '</td>' +
                    '<td align="center">' + qty + ' ' + jenisSatuan + '</td>' +
                    '<td>' + ulWak+ '<br>'+
                    '<div id="body_ket_'+ count +'"></div><br>' +
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
                if (!$('#racik_racik').is(':checked')) {
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
        // if (jenisObat == '' || jenisObat == null) {
        //     $('#war_jenis_obat').show();
        // }
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
    var tempBodyKet = [];
    for (var i = 0 ; i < ket ; i++){
        if (i == 0){
            id = $("#ket-"+i+" option:selected").val();
        }
        var keterangan = $("#ket-"+i+" option:selected").text();
        str += keterangan +". ";
    }

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
    if (data.length > 0 && !ttd) {
        $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
        $('#save_con').attr('onclick', 'saveResepObat()');
    } else {
        var msg = "";
        if (data.length == 0) {
            msg = "Data obat, ";
        }
        if (ttd) {
            msg = msg + "TTD dokter";
        }
        $('#warning_resep_head').show().fadeOut(5000);
        $('#msg_resep').text("Silahkan cek " + msg + "...!");
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
        if (!cekSession()) {
            var idPelayanan = apotek.split('|')[0];
            var namaPelayanan = apotek.split('|')[1];
            var dataObat = [];
            $.each(data, function (i, item) {
                var idObat = $('#id_obat_' + i).val();
                var qty = $('#qty_' + i).val();
                var jenisSatuan = $('#jenis_satuan_' + i).val();
                var keterangan = $('#keterangan_' + i).val();
                var ketDetail = $('#keterangan_detail_' + i).val();
                var jenisResep = $('#jenis_resep_' + i).val();
                var hariKronis = $('#hari_kronis_' + i).val();
                var isRacik = $('#is_racik_' + i).val();
                var namaRacik = $('#nama_racik_' + i).val();
                var idRacik = $('#id_racik_' + i).val();
                var waktu = $('#temp_waktu_'+i).val();
                dataObat.push({
                    'id_obat': idObat,
                    'qty': qty,
                    'jenis_satuan': jenisSatuan,
                    'keterangan': ketDetail,
                    'keterangan_detail': keterangan,
                    'jenis_resep': jenisResep,
                    'hari_kronis': hariKronis,
                    'nama_racik': namaRacik,
                    'id_racik': idRacik,
                    'is_racik': isRacik,
                    'waktu_resep': waktu
                });

            });
            var stringDataObat = JSON.stringify(dataObat);
            var dataObj = {
                'id_detail_checkup': idDetailCheckup,
                'id_pelayanan': idPoli,
                'id_dokter': idDokter,
                'id_pasien': idPasien,
                'id_apotek': idPelayanan,
                'ttd': dataURL,
                'data_obat': stringDataObat

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
                    '<option value=""> - </option>' +
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
        if (!cekSession()) {
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
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        body += '<li>' + item.satuanSediaan + '</li>';
                    });
                    $('#set_js').html('<ul>' + body + '</ul>');
                }
            });
        }
    }
}

function savePenunjangPasien() {

    var tinggi = $('#tinggi').val();
    var berat = $('#berat').val();

    if (noCheckup != '' && tinggi != '' && berat != '') {
        if (!cekSession()) {
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

    $('#set_js').html('');
    $('#set_formula').text('');
    $('#set_teral').text('');
    $('#set_noretal').text('');

    resetComboObat();
}

function setObatPoli(jenis) {
    var poli = $('#resep_apotek').val();
    var option = "<option value=''> - </option>";
    var jenisPasien = $('#jenis_pasien').val();
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
    if (!cekSession()) {
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
        var warn = $('#war_rep_qty').is(':visible');
        if (warn) {
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
    var klinis = $('#kinis').val();
    if (auto && hetero && tensi && suhu && nadi && rr && klinis != '') {
        if (!cekSession()) {
            $('#save_fisik').hide();
            $('#load_fisik').show();
            CheckupAction.saveAnamnese(auto, hetero, noCheckup, idDetailCheckup, tensi, suhu, nadi, rr, klinis, {
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
        if (!cekSession()) {
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
            $("#ket_icd9").val(selectedObj.name);
            return selectedObj.id;
        }
    });
}

function getListRekamMedis(tipePelayanan, jenis, id) {
    var li = "";
    var jenisRm = "";
    if (jenis == "igd") {
        if (parseInt(umur) >= 0 && parseInt(umur) <= 16) {
            jenisRm = 'ugd_anak';
        } else if (parseInt(umur) >= 17 && parseInt(umur) <= 55) {
            jenisRm = 'ugd_dewasa';
        } else if (parseInt(umur) > 56) {
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

                if (item.jenis == 'ringkasan_rj') {
                    li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-television"></i>' + item.namaRm + '</a></li>'
                } else {
                    if (item.function == 'addMonitoringFisioterapi') {
                        li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-television"></i>' + item.namaRm + '</a></li>'
                    } else {
                        if (item.keterangan == 'form') {
                            li += '<li ' + tol + '><a style="cursor: pointer" onclick="loadModalRM(\'' + item.jenis + '\', \'' + item.function + '\', \'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')">' + icons + item.namaRm + ' ' + labelTerisi + tolText + '</a></li>' + enter;
                        } else if (item.keterangan == "surat") {
                            li += '<li ' + tol + '><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\',\'' + item.namaRm + '\')' + '">' + icons2 + item.namaRm + ' ' + labelPrint + tolText + '</a></li>' + enter;
                        }
                    }
                }
            });
            $('#asesmen_rj').html(li);
        }
    });
}

function getDokterDpjp() {
    var option = '<option value=""> - </option>';
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
                        cek = true;
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
        if (jenisPasien == 'asuransi' && isLaka == 'Y') {
            if (noRujukan == '' || noRujukan == null ||
                tglRujukan == '' || tglRujukan == null ||
                suratRujukan == '' || suratRujukan == null) {
                $('#laka_no_polisi').val(noRujukan);
                $('#laka_tgl_kejadian').val(tglRujukan);
                $('#laka_surat_rujukan').val(suratRujukan);
                $('#save_laka').attr('onclick', 'cekDataAsuransi()');
                $('#modal-laka').modal({show: true, backdrop: 'static'});
            } else {
                $('#save_con').attr('onclick', 'savePemeriksaanPasien()');
                $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            }
        } else {
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
    var keterangan = $('#ket_selesai option:selected').text();
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
    var idKeterangan = $('#ket_selesai').val();

    var eksekutif = "N";
    if ($('#is_eksekutif').is(':checked')) {
        eksekutif = "Y";
    }

    var vaksin = "N";
    if ($('#is_vaksin').is(':checked')) {
        vaksin = "Y";
    }

    if (tindakLanjut != '') {

        if (jenisPeriksaPasien == 'asuransi' && isLaka == 'Y') {
            if (noRujukan == '' || noRujukan == null ||
                tglRujukan == '' || tglRujukan == null ||
                suratRujukan == '' || suratRujukan == null) {
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
                    'uang_muka': uangMuka,
                    'is_eksekutif': eksekutif,
                    'is_vaksin': vaksin
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
                            'no_checkup': noCheckup,
                            'id_detail_checkup': idDetailCheckup,
                            'tindak_lanjut': tindakLanjut,
                            'keterangan': 'Kontrol Ulang dengan Pemeriksaan Penunjang Medis',
                            'catatan': catatan,
                            'jenis_pasien': jenisPeriksaPasien,
                            'tgl_kontrol': tglKontrol,
                            'list_pemeriksaan': JSON.stringify(pemeriksan),
                            'id_kategori_lab': kategoriLab,
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
            var meninggal = "";

            if("selesai" == tindakLanjut){
                CheckupDetailAction.initKeteranganKeluar(idKeterangan, function (res) {
                    if("meninggal" == res.kategori){
                        meninggal = "Y";
                    }
                });
            }

            data = {
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup,
                'tindak_lanjut': tindakLanjut,
                'keterangan': ktr,
                'catatan': catatan,
                'jenis_pasien': jenisPeriksaPasien,
                'is_meninggal': meninggal
            }
            cek = true;
        }
    }

    if (cek) {
        if (!cekSession()) {
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
                        if ('rujuk_internal' == tindakLanjut) {
                            window.open('printNoRujukan_checkupdetail.action?id=' + idDetailCheckup, '_blank');
                        }
                        if ('pindah_poli' == tindakLanjut) {
                            PelayananAction.getDataPelayanan(poliLain, function (res) {
                                if(res.tipePelayanan != "igd"){
                                    window.open('printNoAntrian_checkupdetail.action?id=' + idPasien + '&tipe=' + poliLain, '_blank');
                                }
                            });
                        }
                        if('kontrol_ulang' == tindakLanjut){
                            window.open('printSuratKeterangan_checkupdetail.action?id='+idDetailCheckup+'&tipe=KU', '_blank');
                        }
                        if('rujuk_rs_lain' == tindakLanjut){
                            window.open('printSuratKeterangan_checkupdetail.action?id='+idDetailCheckup+'&tipe=RSL', '_blank');
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
    if (noPolisi && tglKejadian && suratRujukan != '') {
        $('#save_con').attr('onclick', 'savePemeriksaanPasien()');
        $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
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

function setKeteranganPeriksa(id) {
    var option = '<option value=""> - </option>';
    if (jenisPeriksaPasien == 'umum' || jenisPeriksaPasien == 'rekanan' || jenisPeriksaPasien == 'bpjs_rekanan') {
        option = option + ' <option value="selesai">Selesai</option>';
        if (jenisPeriksaPasien != "bpjs_rekanan") {
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
        if (jenisPeriksaPasien == "bpjs") {
            option = option + '<option value="rujuk_internal">Rujuk Internal</option>\n';
        } else {
            option = option + '<option value="pindah_poli">Pindah Poli</option>\n';
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
    $('#'+id).html(option);
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
                if ("Y" == res.isElektif) {
                    $('#form_elektif').show();
                    $('#is_elektif').val("Y");
                } else {
                    $('#form_elektif').hide();
                    $('#is_elektif').val("N");
                }

                if ("Y" == res.flagKonsulGizi) {
                    $('#warning_konsul').fadeIn(1000);
                    $('#msg_konsul').text("Anda memilih tindakan Konsultasi Gizi. Setelah menambahkan tindakan ini, infokan kepada Pasien untuk melakukan Konsultasi Gizi ke Unit Gizi...!");
                } else {
                    $('#warning_konsul').hide();
                }
            }
        });
    }
}

function cekRacik(id) {
    if ($('#' + id).is(':checked')) {
        $('#form-nama-racik').show();
    } else {
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
    // if (namaWaktu && param != '' && ket != null) {
    if (namaWaktu || param != '' || ket != null) {
        var disKet = "";
        $.each(ket, function (i, item) {
            var id = item.split('|')[0];
            var nam = item.split('|')[1];
            if (disKet != '') {
                disKet = disKet + ', ' + nam;
            } else {
                disKet = nam;
            }
        });
        if (id > 0) {
            $.each(table, function (i, item) {
                var idP = $('#id_param_' + i).val();
                var wkt = $('#waktu_' + i).val();
                if (idP != '') {
                    if (idP == param && wkt == waktu) {
                        cek = true;
                    }
                }
            });
        }
        var set = namaParam + ' : ' + disKet;
        if (cek) {
            $('#w_keterangan').show().fadeOut(5000);
            $('#p_keterangan').text("Keterangan " + namaParam + " sudah ada dalam list...!");
        } else {
            var cekNama = namaWaktu;
            if (id > 0) {
                if ($('#waktu_' + last).val() == waktu) {
                    cekNama = "";
                }
            }
            var body = '<tr id="' + param + '" style="height: 2px">' +
                '<td>' + cekNama +
                '<input type="hidden" id="waktu_' + id + '" value="' + waktu + '">' +
                '<input type="hidden" id="nama_waktu_' + id + '" value="' + cekNama + '">' +
                '<input type="hidden" id="id_param_' + id + '" value="' + param + '">' +
                '<input type="hidden" id="nama_param_' + id + '" value="' + set + '">' +
                '</td>' +
                '<td>' + set + '</td>' +
                '<td align="center"><img onclick="delKet(\'' + param + '\')" class="hvr-grow" src="' + contextPathHeader + '/pages/images/cancel-flat-new.png" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                '</tr>';
            $('#body_keterangan').append(body);
            inputWarning('war_rep_cek_waktu', 'cor_rep_cek_waktu');
        }
    } else {
        $('#w_keterangan').show().fadeOut(5000);
        $('#p_keterangan').text("Silahkan cek inputan parameter dan keterangan...!");
    }
}

function getComboParameterObat(idJenis) {
    ObatAction.getComboParameterObat(idJenis, function (res) {
        var option = '<option value=""> - </option>';
        if (res.length > 0) {
            $.each(res, function (i, item) {
                option += '<option value="' + item.id + '">' + item.nama + '</option>';
            });
        }
        $('#param_ket').html(option);
    });
    getComboWaktuObat(idJenis);
}

function getComboWaktuObat(idJenis) {
    ObatAction.getComboParameterWaktuObat(idJenis, function (res) {
        var option = '<option value=""> - </option>';
        if (res.length > 0) {
            $.each(res, function (i, item) {
                option += '<option value="' + item.id + '">' + item.keterangan + '</option>';
            });
            $('#waktu_param').html(option);
        }
    });
}

function getComboKeteranganObat(idParam) {
    ObatAction.getComboKeteranganObat(idParam, function (res) {
        var option = '';
        if (res.length > 0) {
            $.each(res, function (i, item) {
                option += '<option value="' + item.id + '|' + item.keterangan + '">' + item.keterangan + '</option>';
            });
        }
        $('#ket_param').html(option);
    });
}

function delKet(param) {
    $('#' + param).remove();
}

function showJadwalDokter() {
    var pel = $('#poli_lain option:selected').text();
    var jenisPasien = jenisPeriksaPasien;
    var id = $('#poli_lain').val();
    var table = "";
    if (id != null && id != '' && jenisPasien != null && jenisPasien != '') {
        CheckupAction.listOfDokter(id, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var kuota = 0;
                    var sisa = 0;
                    var kuotaBpjs = 0;
                    var sisKuotaBpjs = 0;
                    var namaDokter = "";
                    var sip = "";
                    var label = "";
                    var label2 = "";
                    var jamkerja = "";
                    if (item.jamAwal != null && item.jamAkhir != null) {
                        jamkerja = item.jamAwal + " s/d " + item.jamAkhir;
                    }
                    if (item.kuotaOnSite != null && item.kuotaOnSite != '') {
                        kuota = item.kuotaOnSite;
                    }
                    if (item.kuotaBpjs != null && item.kuotaBpjs != '') {
                        kuotaBpjs = item.kuotaBpjs;
                    }
                    if (item.kuotaTerpakaiNonBpjs != null && item.kuotaTerpakaiNonBpjs != '') {
                        sisa = item.kuotaTerpakaiNonBpjs;
                    }
                    if (item.kuotaTerpakaiBpjs != null && item.kuotaTerpakaiBpjs != '') {
                        sisKuotaBpjs = item.kuotaTerpakaiBpjs;
                    }

                    label = sisa + "/" + kuota;
                    label2 = sisKuotaBpjs + "/" + kuotaBpjs;

                    if (item.namaDokter != '') {
                        namaDokter = item.namaDokter;
                    }
                    if (item.idDokter != '') {
                        sip = item.idDokter;
                    }

                    var foto = contextPathHeader + '/pages/images/unknown-person2.jpg';
                    if (item.urlImg != null && item.urlImg != '') {
                        foto = contextPathHeader + item.urlImg;
                    }

                    var clasBox = 'btn-trans';
                    var btnSet = 'onclick="setDokter(\'' + item.idDokter + '\', \'' + item.namaDokter + '\')"';
                    var noDrop = 'cursor: pointer';

                    if (item.flagLibur == "Y") {
                        clasBox = 'btn-trans-02';
                        btnSet = 'style="cursor: no-drop"';
                        noDrop = 'cursor: no-drop';
                    } else {
                        var cekSisa = 0;
                        var cekKuota = 0;
                        if (jenisPasien == 'bpjs') {
                            cekSisa = sisKuotaBpjs;
                            cekKuota = kuotaBpjs;
                        } else {
                            cekSisa = sisa;
                            cekKuota = kuota;
                        }

                        if (cekSisa == cekKuota) {
                            clasBox = 'btn-trans-02';
                            btnSet = 'style="cursor: no-drop"';
                            noDrop = 'cursor: no-drop';
                        }
                    }
                    table += '<div id="id_box_' + i + '" class="' + clasBox + '" ' + btnSet + '>\n' +
                        '<div style="text-align:left; font-size:11px;">\n' +
                        '    <table align="center" style="width:100%; border-radius:5px; margin-top:2px; ' + noDrop + '">\n' +
                        '        <tr>\n' +
                        '            <td align="left" colspan="2">\n' +
                        '                <span style="color: white; background-color: #337ab7; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">' + jamkerja + '</span>\n' +
                        '                <span class="pull-right" style="margin-top: -6px; color: white; background-color: #00a65a; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">' + label2 + '</span>\n' +
                        '                <span class="pull-right" style="margin-top: -6px; margin-left: 5px; color: white; background-color: #ec971f; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">' + label + '</span>\n' +
                        '            </td>\n' +
                        '        </tr>\n' +
                        '        <tr>\n' +
                        '            <td align="center" colspan="2">\n' +
                        '                <img class="img-circle" style="background-color:transparent; height:100px; padding-bottom: 2px; padding-top: 8px; width: 55%;" src="' + foto + '">\n' +
                        '            </td>\n' +
                        '        </tr>\n' +
                        '        <tr>\n' +
                        '            <td align="left" colspan="2" style="color: black; font-size: 11px; padding-top: 3px; border-bottom: black solid 1px; padding-bottom: 3px">\n' +
                        '                <i class="fa fa-user"></i> ' + namaDokter + '\n' +
                        '            </td>\n' +
                        '        </tr>\n' +
                        '        <tr>\n' +
                        '            <td align="left" colspan="2" style="color: black; font-size: 11px; padding-top: 5px">\n' +
                        '                <i class="fa fa-square" style="font-size: 10px"></i> ' + sip + '\n' +
                        '            </td>\n' +
                        '        </tr>\n' +
                        '    </table>\n' +
                        '</div>\n' +
                        '</div>';
                });
            } else {
                table = '<span class="text-center">Jadwal Dokter Tidak Ditemukan...!</span>'
            }
            $('#dokter_pelayanan').text(pel);
            $('#jadwal_dokter').html(table);
            $('#modal-jadwal-dokter').modal({show: true, backdrop: 'static'});
        });
    }
}

function setDokter(idDokter, namaDokter) {
    $('#nama_dokter').val(namaDokter);
    $('#list_dokter').val(idDokter);
    $('#modal-jadwal-dokter').modal('hide');
}

function saveDiet(id) {
    if (id != null && id != '') {
        if (!cekSession()) {
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
                'id_detail_checkup': idDetailCheckup,
                'waktu': waktu,
                'id_diet_gizi': bentuk,
                'id_jenis_diet': jenis,
                'id_pelayanan': idPoli
            });
        });
        if (bentukDiet != '' && keteranganDiet != '' && jenisDiet != '') {
            $('#save_diet').hide();
            $('#load_diet').show();
            dwr.engine.setAsync(true);
            var result = JSON.stringify(data);
            OrderGiziAction.saveOrderGizi(result, untukKapan, "RJ", function (response) {
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
    OrderGiziAction.listOrderGiziRJ(idDetailCheckup, function (response) {
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
                    } else if (item.approveFlag == "N") {
                        label = '<span class="span-danger"> ditolak</span>';
                    } else {
                        btn = '<img border="0" class="hvr-grow" onclick="editDiet(\'' + item.idOrderGizi + '\',\'' + item.idDietGizi + '\',\'' + item.waktu + '\')" src="' + contextPathHeader + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' +
                            '<img border="0" class="hvr-grow" onclick="cancelDiet(\'' + item.idOrderGizi + '\')" src="' + contextPathHeader + '/pages/images/cancel-flat-new.png" style="cursor: pointer;">';
                        label = '<span class="span-warning"> menunggu konfirmasi</span>'
                    }

                    if (item.diterimaFlag == "Y") {
                        btn = '<div class="input-group">' +
                            '<input class="form-control" value="' + item.idOrderGizi + '" disabled>' +
                            '<div class="input-group-addon">' +
                            '<img src="' + contextPathHeader + '/pages/images/icon_success.ico" style="height: 20px; width: 20px;">' +
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

function setDiet(id) {
    var bentuk = $('#bentuk_diet').val();
    var jenis = $('#jenis_diet').val();
    var bentukText = $('#bentuk_diet option:selected').text();
    var jenisText = $('#jenis_diet option:selected');
    var tempText = "";
    if (jenisText.length > 0) {
        $.each(jenisText, function (i, item) {
            if (tempText != '') {
                tempText = tempText + ', ' + item.innerText;
            } else {
                tempText = item.innerText;
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
    if ($('#' + id).is(':checked')) {
        if (bentuk && jenis != '' && jenis && bentuk != null) {
            table = '<tr id="' + ket + '">' +
                '<td>' + keterangan + '<input type="hidden" value="' + ket + '" id="waktu_' + idCount + '"></td>' +
                '<td>' + tempText + '<input type="hidden" value="' + jenis + '" id="jenis_' + idCount + '"></td>' +
                '<td>' + bentukText +
                '<input type="hidden" value="' + bentuk + '" id="bentuk_' + idCount + '">' +
                '<input type="hidden" value="' + bentukText + '" id="bentuk_text_' + idCount + '">' +
                '</td>' +
                '</tr>';
            $('#body_add_diet').append(table);
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
    } else {
        $('#' + ket).remove();
    }
}

function cancelDiet(id) {
    $('#modal-cancel-diet').modal({show: true, backdrop: 'static'});
    $('#save_cancel_diet').attr('onclick', 'saveCancelDiet(\'' + id + '\')');
}

function saveCancelDiet(id) {
    var ket = $('#keterangan_cancel').val();
    if (ket != '') {
        if (!cekSession()) {
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

function cekBarcode(value, idOrderGizi) {

    if (value != '' && idOrderGizi != '') {

        if (value == idOrderGizi) {
            $('#status' + idOrderGizi).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            setTimeout(function () {
                OrderGiziAction.updateDiterimaFlag(idOrderGizi, function (response) {
                    if (response.status == "success") {
                        listDiet();
                    } else {
                        $('#status' + idOrderGizi).html('<img src="' + contextPathHeader + '/pages/images/icon_failure.ico" style="height: 20px; width: 20px;">');
                    }
                });
            }, 200);
        } else {
            $('#status' + idOrderGizi).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            setTimeout(function () {
                $('#status' + idOrderGizi).html('<img src="' + contextPathHeader + '/pages/images/icon_failure.ico" style="height: 20px; width: 20px;">');
            }, 200);
        }
    } else {
        $('#status' + idOrderGizi).html('');
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
        $('#' + id).removeClass("fa-spin");
    }, 500);
}

function isLuar(id) {
    var cek = $('#' + id).is(':checked');
    if (cek) {
        $('#form_lab_luar, #form_tarif_lab_luar, #btn-add-lab-luar').show();
        $('##btn-add-lab-dalam').hide();
        $('#form_lab_dalam').hide();
        $('#tarif_luar_lab, #h_total_tarif').val('');
    } else {
        $('#btn-add-lab-luar').hide();
        $('#btn-add-lab-dalam').show();
        $('#form_lab_luar, #form_tarif_lab_luar').hide();
        $('#form_lab_dalam').show();
        $('#tarif_luar_lab, #h_total_tarif').val('');
    }
}

function addParameter() {
    var par = $('.parameter_luar');
    var count = par.length;
    var idROw = 'par_' + count;
    var set = '<div class="input-group" style="margin-top: 7px;" id="' + idROw + '">\n' +
        '<input id="lab_parameter_luar_' + count + '" class="form-control parameter_luar"\n' +
        '       placeholder="masukkan parameter ' + (parseInt(count) + parseInt(1)) + '">\n' +
        '<div onclick="delParamrs(\'' + idROw + '\')" class="input-group-addon" style="background-color: #a94442; color: white; cursor: pointer">\n' +
        '    <i class="fa fa-trash"></i>\n' +
        '</div>\n' +
        '</div>';
    $('#params_luar').append(set);
    $('#lab_parameter_luar_' + count).focus();
    var place = $('.parameter_luar');
    $.each(place, function (i, item) {
        var nomor = i+1;
        $('#'+item.id).attr('placeholder', 'masukkan parameter '+nomor);
    });
}

function delParamrs(id) {
    $('#' + id).remove();
    var place = $('.parameter_luar');
    $.each(place, function (i, item) {
        var nomor = i+1;
        $('#'+item.id).attr('placeholder', 'masukkan parameter '+nomor);
    });
}

function hasilUploadRJ(id, tipe, kategori) {
    var data = $('#' + tipe + '_' + id).val();
    $('#item_hasil_lab').html('');
    $('#li_hasil_lab').html('');
    if (data != null && data != '') {
        var result = JSON.parse(data);
        if (tipe == 'dalam') {
            $('#title_hasil_lab').html("Hasil Pemeriksaan " + kategori);
        } else {
            $('#title_hasil_lab').html("Hasil Pemeriksaan " + kategori + " Luar");
        }
        if (result.length > 0) {
            var set = '';
            var li = '';
            $.each(result, function (i, item) {
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
                        '<embed src="'+item.urlImg+'" style="width: 100%; height: 70%"/>'+
                        '</div>';
                }else{
                    set += '<div ' + cla + '>\n' +
                        '<img src="' + item.urlImg + '" style="width: 100%; height: 70%">\n' +
                        '</div>';
                }
                li += '<li data-target="#carousel-hasil_lab" data-slide-to="' + i + '" ' + claLi + '></li>';
            });
            $('#item_hasil_lab').html(set);
            $('#li_hasil_lab').html(li);
        }
        $('#modal-hasil_lab').modal({show: true, backdrop: 'static'});
    }
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
                        icons2 = '<i class="fa fa-check" style="color: #449d44"></i>';
                    }

                    labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';

                    if (item.keterangan == 'form') {
                        var jenisRm = "";
                        if (parseInt(umur) >= 0 && parseInt(umur) <= 17) {
                            jenisRm = 'ugd_anak';
                        } else if (parseInt(umur) >= 18 && parseInt(umur) <= 55) {
                            jenisRm = 'ugd_dewasa';
                        } else if (parseInt(umur) > 56) {
                            jenisRm = 'ugd_geriatri';
                        }
                        if (item.jenis == "ugd_anak" || item.jenis == "ugd_dewasa" || item.jenis == "ugd_geriatri") {
                            if (jenisRm == item.jenis) {
                                temp += '<li ' + tol + '><a style="cursor: pointer" onclick="loadModalRM(\'' + item.jenis + '\', \'' + item.function + '\', \'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\', \'N\',\'Y\')">' + icons + item.namaRm +' '+ labelTerisi + tolText + '</a></li>';
                            }
                        } else {
                            temp += '<li ' + tol + '><a style="cursor: pointer" onclick="loadModalRM(\'' + item.jenis + '\', \'' + item.function + '\', \'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\', \'N\',\'Y\')">' + icons + item.namaRm +' '+ labelTerisi + tolText + '</a></li>';
                        }
                    }
                });
                $('#' + id).html(temp);
            }
        }
    });
}

function dokterDpjp(){
    var data = $('#tabel_dokter').tableToJSON();
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

        console.log(idPemeriksaan);
        console.log(idParameter);
        console.log(tempPemeriksaan);
        console.log(tempIdPemeriksan);
        console.log(tempIdParameter);
        console.log(tempParameter);
        console.log(tempParameterLi);
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
            $('#tarif_luar_lab').attr('disabled', true);
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
        $('#tarif_luar_lab').attr('disabled', false);
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
    var par = $('.parameter_luar');
    $.each(par, function (i, item) {
        if (i != 0) {
            $('#par_' + i).remove();
        }
    });
    $('#cek_luar').css('cursor', 'pointer');
    $('#cek_pending').css('cursor', 'pointer');
    $('#select-jenis-pemeriksaan').attr('disabled', false);
    $('#tarif_luar_lab').attr('disabled', false);
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

function setTindakLanjut(){
    dwr.engine.setAsync(true);
    CheckupDetailAction.getDetailCheckup(idDetailCheckup, function (res) {
        console.log(res);
        if(res.tindakLanjut != null && res.tindakLanjut != ''){
            $('#keterangan').val(res.tindakLanjut).trigger('change');
            $('#keterangan').attr('disabled', true);
            if(res.tindakLanjut == 'selesai'){
                var kete = $('#ket_selesai option');
                var ketse = "";
                $.each(kete, function (i, item) {
                    if(item.innerHTML == res.keteranganSelesai){
                        ketse = item.value;
                    }
                });
                $('#ket_selesai').val(ketse).trigger('change');
                $('#ket_selesai').attr('disabled', true);
            }else if(res.tindakLanjut == 'rawat_inap' || res.tindakLanjut == 'rawat_isolasi' || res.tindakLanjut == 'rawat_intensif'){
                $('#keterangan_rw').val(res.indikasi).trigger('change');
                $('#keterangan_rw').attr('disabled', true);
            }else if(res.tindakLanjut == 'rujuk_rs_lain'){
                $('#rs_rujukan').val(res.rsRujukan);
                $('#rs_rujukan').attr('disabled', true);
            }
        }
    });
}