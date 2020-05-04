function showModalRingkasanPasien(jenis) {
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }

    var jam = $('.jam').length;
    var tgl = $('.tgl').length;
    var tglPattern = $('.tgl-pattern').length;
    var jamPattern = $('.jam-pattern').length;

    if (jam > 0) {
        $('.jam').timepicker();
        $('.jam').val(formaterTime(new Date()));
    }
    if (tgl > 0) {
        $('.tgl').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tgl').val(formaterDate(new Date()));
        $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
    }
    if (tglPattern > 0) {
        $('.tgl-pattern').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tgl-pattern').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
    }
    if (jamPattern > 0) {
        $('.jam-pattern').timepicker();
        $('.jam-pattern').inputmask('hh:mm', {'placeholder': 'hh:mm'});
    }

    $('#modal-ring-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveRingkasanPasien(jenis, ket) {
    var data = [];
    var cek = false;

    if ("ringkasan_pulang_pasien" == jenis) {
        var pe1 = $('#rps1').val();
        var pe2 = $('#rps2').val();
        var pe3 = $('#rps3').val();
        var pe4 = $('#rps4').val();
        var pe5 = $('#rps5').val();
        var pe6 = $('#rps6').val();
        var pe7 = $('#rps7').val();
        var pe8 = $('#rps8').val();
        var pe9 = $('#rps9').val();
        var pe10 = $('#rps10').val();
        var pe11 = $('#rps11').val();
        var pe12 = $('#rps12').val();
        var pe13 = $('#rps13').val();
        var pe14 = $('#rps14').val();
        var pe15 = $('#rps15').val();
        var pe16 = $('#rps16').val();
        var pe17 = $('#rps17').val();
        var pe18 = $('#rps18').val();
        var pe19 = $('#rps19').val();
        var pe20 = $('#rps20').val();
        var pe21 = $('[name=rps21]').val();
        var pe22 = $('[name=rps22]').val();
        var pe23 = $('#rps23').val();


        if (pe1 && pe2 && pe3 && pe4 && pe5 && pe6 && pe7 && pe8 && pe9 && pe10 &&
            pe11 && pe12 && pe13 && pe14 && pe15 && pe16 && pe17 && pe18 && pe19 && pe20 && pe23 != '' && pe21 && pe22 != undefined) {

            data.push({
                'parameter': 'Tanggal Masuk',
                'jawaban': pe1,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Keluar',
                'jawaban': pe2,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alasan Masuk RS',
                'jawaban': pe3,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ringkasan Penyakit',
                'jawaban': pe4,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hasil Pemeriksaan Fisik',
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': pe5 + ' C',
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': pe6 + ' x/mnt',
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'RR',
                'jawaban': pe7 + ' x/mnt',
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tensi',
                'jawaban': pe8 + ' mmHg',
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'CGS',
                'jawaban': pe9,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Penunjang/Diagnotik Terpeing',
                'jawaban': pe10,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi atau pengobatan selama di Rumah Sakit',
                'jawaban': pe11,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hasil Konsultasi',
                'jawaban': pe12,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Utama',
                'jawaban': pe13,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Sekunder',
                'jawaban': pe14,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan/Prosedur',
                'jawaban': pe15,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keadaan Waktu Keluar RS',
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': pe16 + ' C',
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': pe17 + ' x/mnt',
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'RR',
                'jawaban': pe18 + ' x/mnt',
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tensi',
                'jawaban': pe19 + ' mmHg',
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'CGS',
                'jawaban': pe20,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keterangan Keluar',
                'jawaban': pe21,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penyebab Kematian',
                'jawaban': pe22,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Instruksi tindak lanjut',
                'jawaban': pe23,
                'keterangan': jenis,
                'jenis': 'ringkasan_pulang',
                'id_detail_checkup': idDetailCheckup
            });

            var obatTerapi = $('.obat-terapi');
            var jmlTerapi = $('.jml-terapi');
            var dosisTerapi = $('.dosis-terapi');
            var caraTerapi = $('.cara-terapi');
            var terapi = "";
            $.each(obatTerapi, function (i, item) {
                if (item.value != '') {
                    if (terapi != '') {
                        terapi = terapi + '=' + item.value + '|' + jmlTerapi[i].value + '|' + dosisTerapi[i].value + '|' + caraTerapi[i].value;
                    } else {
                        terapi = item.value + '|' + jmlTerapi[i].value + '|' + dosisTerapi[i].value + '|' + caraTerapi[i].value;
                    }
                }
            });
            if (terapi != '') {
                data.push({
                    'parameter': 'Terapi Pulang',
                    'jawaban': terapi,
                    'keterangan': jenis,
                    'jenis': 'ringkasan_pulang',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            cek = true;
        }
    }

    if ("resume_medis_pasien" == jenis) {
        var pe1 = $('#rem1').val();
        var pe2 = $('#rem2').val();
        var pe3 = $('#rem3').val();
        var pe4 = $('#rem4').val();
        var pe5 = $('#rem5').val();
        var pe6 = $('#rem6').val();
        var pe7 = $('#rem7').val();
        var pe8 = $('#rem8').val();
        var pe9 = $('#rem9').val();
        var pe10 = $('#rem10').val();
        var pe11 = $('#rem11').val();
        var pe12 = $('#rem12').val();
        var pe13 = $('#rem13').val();
        var pe14 = $('#rem14').val();
        var pe15 = $('#rem15').val();
        var pe16 = $('#rem16').val();
        var pe18 = $('#rem18').val();

        if (pe1 && pe2 && pe3 && pe4 && pe5 && pe6 && pe7 && pe8 && pe9 && pe10 &&
            pe11 && pe12 && pe13 && pe14 && pe15 && pe16 != '') {
            data.push({
                'parameter': 'Anamnese (Riwayat Penyakit)',
                'jawaban': pe1,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Dahulu',
                'jawaban': pe2,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Laboratorium',
                'jawaban': pe3,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Radiologi',
                'jawaban': pe4,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Lain-Lain',
                'jawaban': pe5,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jalannya penyakit (Konsultasi)',
                'jawaban': pe6,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diganosa Primer',
                'jawaban': pe7,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Sekunder',
                'jawaban': pe8,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban': pe9,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban': pe10,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi Pulang',
                'jawaban': pe11,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kondisi Saat Pulang',
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': pe12 + ' C',
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': pe13 + ' x/mnt',
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'RR',
                'jawaban': pe14 + 'x/mnt',
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tensi',
                'jawaban': pe15 + ' mmHg',
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'GCS',
                'jawaban': pe16,
                'keterangan': jenis,
                'jenis': 'resume_medis',
                'id_detail_checkup': idDetailCheckup
            });

            var keadaan = $('[name=rem17]:checked').val();
            var kead = "";
            if (keadaan != undefined) {
                var a = $('#ket_rem17').val();
                if (keadaan == "Meninggal dunia, sebab kematian") {
                    kead = keadaan + ', ' + a;
                } else if (keadaan == "Rujuk") {
                    kead = keadaan + ', ' + a;
                } else {
                    kead = keadaan;
                }
                data.push({
                    'parameter': 'Keadaan saat keluar RS',
                    'jawaban': kead,
                    'keterangan': jenis,
                    'jenis': 'resume_medis',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            var kete = $('.ket-prognosis');
            var hari = $('.hari-prognosis');
            var tgl = $('.tgl-prognosis');
            var jam = $('.jam-prognosis');
            var prognosis = "";
            var temp = "";
            var label = "Prognosis|Usulan Tindak Lanjut Kontrol Poli"
            var v3 = "|Bila ada Keluhan sebelum tanggal kontrol, pasien dapat berobat ke Fasilitas Kesehatan Tingkat I terdekat. " +
                "Kontrol membawa : FC kartu BPJS/Asurans, FC KTP/KK, REsume Medis/Surat Kontrol"
            $.each(kete, function (i, item) {
                if (item.value != '') {
                    if (prognosis != '') {
                        prognosis = prognosis + '|' + item.value + ', ' + 'Hari ' + hari[i].value + ', ' + 'Tanggal ' + tgl[i].value + ', ' + 'Jam ' + jam[i].value;
                    } else {
                        prognosis = '|' + item.value + ', ' + 'Hari ' + hari[i].value + ', ' + 'Tanggal ' + tgl[i].value + ', ' + 'Jam ' + jam[i].value;
                    }
                }
            });

            var khusus = "";
            if (pe18 != '') {
                khusus = '|Pesan Khusus ' + pe18;
            }

            if (prognosis != '' || pe18 != '') {
                temp = label + prognosis + v3 + khusus;
            }

            if (temp != '') {
                data.push({
                    'parameter': 'Prognosis',
                    'jawaban': temp,
                    'keterangan': jenis,
                    'jenis': 'resume_medis',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            cek = true;
        }
    }

    if ("ringkasan_keluar_pasien" == jenis) {
        var pe1 = $('[name=rkp1]:checked').val();
        var pe2 = "";
        var p2 = $('[name=rkp2]:checked').val();
        if (p2 != undefined) {
            var a = $('#ket_rkp2').val();
            if (p2 == "Puskesmas" || p2 == "Polisi") {
                pe2 = p2 + ', ' + a;
            } else {
                pe2 = p2;
            }
        }
        var pe3 = $('#rkp3').val();
        var pe4 = $('#rkp4').val();

        var pe5 = $('#rkp5').val();
        var pe6 = $('#rkp6').val();
        var pe7 = $('#rkp7').val();
        var pe8 = $('#rkp8').val();
        var pe9 = $('#rkp9').val();
        var pe10 = $('#rkp10').val();

        var krs1 = $('#krs1').val();
        var krs2 = $('#krs2').val();
        var krs3 = $('#krs3').val();

        var pe11 = $('[name=rkp11]:checked').val();
        var pe12 = $('[name=rkp12]:checked').val();

        var pe13 = "";
        var p13 = $('[name=rkp13]:checked').val();

        if (p13 != undefined) {
            var a = $('#ket_rkp13').val();
            if (p13 == "Dirujuk" || p13 == "Permintaan Sendiri" || p13 == "Lain-Lain") {
                pe13 = p13 + ', ' + a;
            } else {
                pe13 = p13;
            }
        }

        if (pe1 && pe11 && pe12 != undefined && pe3 && pe4 && pe5 && pe6 && pe7 && pe8 && pe9 && pe10 && pe2 && pe13 != '') {

            data.push({
                'parameter': 'MRS Melalui',
                'jawaban': pe1,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rujukan dari',
                'jawaban': pe2,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Utama',
                'jawaban': pe3,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Sekunder',
                'jawaban': pe4,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });

            //tindakan operasi

            var tglOp = $('.tgl-op');
            var tinOp = $('.tindakan-op');
            var icdOp = $('.icd-op');
            var bOp = $('.b-op');
            var sOp = $('.s-op');
            var kOp = $('.k-op');
            var anOp = $('.anastesi-op');
            var ope = "";
            $.each(tglOp, function (i, item) {
                if (item.value != '') {
                    if (ope != '') {
                        ope = ope + '=' + item.value + '|' + tinOp[i].value + '|' + icdOp[i].value + '|' + bOp[i].value + '|' + sOp[i].value + '|' + kOp[i].value + '|' + anOp[i].value;
                    } else {
                        ope = item.value + '|' + tinOp[i].value + '|' + icdOp[i].value + '|' + bOp[i].value + '|' + sOp[i].value + '|' + kOp[i].value + '|' + anOp[i].value;
                    }
                }
            });
            if(ope != ''){
                data.push({
                    'parameter': 'Operasi/Tindakan',
                    'jawaban': ope,
                    'keterangan': jenis,
                    'jenis': 'ringkasan_keluar',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            //end

            data.push({
                'parameter': 'Whole Blood',
                'jawaban': pe5 + ' CC ' + ', Tanggal ' + pe6,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Packet Red Cells',
                'jawaban': pe7 + ' CC ' + ', Tanggal ' + pe8,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Liquid Plasma',
                'jawaban': pe9 + ' CC ' + ', Tanggal ' + pe10,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lahir Hidup',
                'jawaban': pe11,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluar RS',
                'jawaban': 'Tanggal ' + krs1 + ', Jam ' + krs2 + ', Lama dirawat ' + krs3,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keadaan Keluar',
                'jawaban': pe12,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Cara Keluar RS',
                'jawaban': pe13,
                'keterangan': jenis,
                'jenis': 'ringkasan_keluar',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        $('#save_ring_' + jenis).hide();
        $('#load_ring_' + jenis).show();
        dwr.engine.setAsync(true);
        RingkasanPasienAction.save(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_ring_' + jenis).show();
                    $('#load_ring_' + jenis).hide();
                    $('#modal-ring-' + jenis).modal('hide');
                    $('#warning_ring_' + ket).show().fadeOut(5000);
                    $('#msg_ring_' + ket).text("Berhasil menambahkan data ringkasan pasien...");
                    $('#modal-ring-' + jenis).scrollTop(0);
                } else {
                    $('#save_ring_' + jenis).show();
                    $('#load_ring_' + jenis).hide();
                    $('#warning_ring_' + jenis).show().fadeOut(5000);
                    $('#msg_ring_' + jenis).text(res.msg);
                    $('#modal-ring-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_ring_' + jenis).show().fadeOut(5000);
        $('#msg_ring_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-ring-' + jenis).scrollTop(0);
    }
}

function detailRingkasanPasien(jenis) {
    if (jenis != '') {
        var head = "";
        var body = "";
        var totalSkor = 0;
        var first = "";
        var last = "";
        var tgl = "";
        var cekData = false;

        RingkasanPasienAction.getListDetail(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var jwb = "";
                    if (item.jawaban != null) {
                        jwb = item.jawaban;
                    }

                    if ("ringkasan_pulang_pasien" == item.keterangan) {
                        if ("Terapi Pulang" == item.parameter) {
                            var table = "";
                            if (jwb != '') {
                                var temp1 = "";
                                var tr = jwb.split("=");
                                $.each(tr, function (i, item) {
                                    var td = item.split("|");
                                    var temp2 = "";
                                    $.each(td, function (i, item) {
                                        if (temp2 != '') {
                                            temp2 = temp2 + '<td>' + item + '</td>';
                                        } else {
                                            temp2 = '<td>' + item + '</td>';
                                        }
                                    });
                                    if (temp1 != '') {
                                        temp1 = temp1 + '<tr>' + temp2 + '</tr>';
                                    } else {
                                        temp1 = '<tr>' + temp2 + '</tr>';
                                    }
                                });
                                table = '<label>Terapi Pulang</label>' +
                                    '<table class="table table-bordered table-striped" style="font-size: 12px">' +
                                    '<thead>' +
                                    '<tr>' +
                                    '<td>Nama Obat</td>' +
                                    '<td>Jumlah</td>' +
                                    '<td>Dosis</td>' +
                                    '<td>Cara Pemberian</td>' +
                                    '</tr>' +
                                    '</thead>' +
                                    '<tbody>' +
                                    temp1 +
                                    '</tbody>' +
                                    '</table>';

                                body += '<tr>' +
                                    '<td colspan="2">' + table + '</td>' +
                                    '</tr>';
                            }
                        } else {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + jwb + '</td>' +
                                '</tr>';
                        }
                    } else if ("resume_medis_pasien" == item.keterangan) {
                        console.log(jwb);
                        if ("Prognosis" == item.parameter) {
                            var isi = jwb.split("|");
                            var li = "";
                            $.each(isi, function (i, item) {
                                if ("Prognosis" == item) {
                                    li += '<li style="list-style-type: none; margin-left: -15px">' + item + '</li>';
                                } else if ("Usulan Tindak Lanjut Kontrol Poli" == item) {
                                    li += '<li style="list-style-type: none; margin-left: -15px">' + item + '</li>';
                                } else {
                                    li += '<li>' + item + '</li>';
                                }
                            });

                            if (li != '') {
                                body += '<tr>' +
                                    '<td colspan="2">' + '<ul style="margin-left: 15px">' + li + '</ul>' + '</td>' +
                                    '</tr>';
                            }
                        } else {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + jwb + '</td>' +
                                '</tr>';
                        }
                    } else if ("ringkasan_keluar_pasien" == item.keterangan){
                        if ("Operasi/Tindakan" == item.parameter) {
                            var table = "";
                            if (jwb != '') {
                                var temp1 = "";
                                var tr = jwb.split("=");
                                $.each(tr, function (i, item) {
                                    var td = item.split("|");
                                    var temp2 = "";
                                    $.each(td, function (i, item) {
                                        if (temp2 != '') {
                                            temp2 = temp2 + '<td>' + item + '</td>';
                                        } else {
                                            temp2 = '<td>' + item + '</td>';
                                        }
                                    });
                                    if (temp1 != '') {
                                        temp1 = temp1 + '<tr>' + temp2 + '</tr>';
                                    } else {
                                        temp1 = '<tr>' + temp2 + '</tr>';
                                    }
                                });
                                table = '<label>'+item.parameter+'</label>' +
                                    '<table class="table table-bordered table-striped" style="font-size: 12px">' +
                                    '<thead>' +
                                    '<tr>' +
                                    '<td>Tanggal</td>' +
                                    '<td>Tindakan</td>' +
                                    '<td>ICD9CM</td>' +
                                    '<td>B</td>' +
                                    '<td>S</td>' +
                                    '<td>K</td>' +
                                    '<td>Anastesi</td>' +
                                    '</tr>' +
                                    '</thead>' +
                                    '<tbody>' +
                                    temp1 +
                                    '</tbody>' +
                                    '</table>';

                                body += '<tr>' +
                                    '<td colspan="2">' + table + '</td>' +
                                    '</tr>';
                            }
                        } else {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + jwb + '</td>' +
                                '</tr>';
                        }
                    } else {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb + '</td>' +
                            '</tr>';
                    }
                    cekData = true;
                    tgl = item.createdDate;
                });
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_ring_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_ring_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_ring_' + jenis).attr('src', url);
            $('#btn_ring_' + jenis).attr('onclick', 'delRowRingkasanPasien(\'' + jenis + '\')');
        });
    }
}

function delRowRingkasanPasien(id) {
    $('#del_ring_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_ring_' + id).attr('src', url);
    $('#btn_ring_' + id).attr('onclick', 'detailRingkasanPasien(\'' + id + '\')');
}

function tambahTerapi() {
    var cek = $('.form-terapi').length;
    var count = +parseInt(cek) + 1;
    var id = "terapi" + count;
    var conten = '<div class="form-group form-terapi" id="' + id + '">\n' +
        '<div class="col-md-3">\n' +
        '<input class="form-control obat-terapi" placeholder="Nama Obat" style="margin-top: 7px">\n' +
        '</div>\n' +
        '<div class="col-md-2">\n' +
        '<input class="form-control jml-terapi" placeholder="Jml" style="margin-top: 7px">\n' +
        '</div>\n' +
        '<div class="col-md-3">\n' +
        '<input class="form-control dosis-terapi" placeholder="Dosis" style="margin-top: 7px">\n' +
        '</div>\n' +
        '<div class="col-md-3">\n' +
        '<input class="form-control cara-terapi" placeholder="Cara Pemberian" style="margin-top: 7px">\n' +
        '</div>\n' +
        '<div class="col-md-1">\n' +
        '<button onclick="delTerapi(\'' + id + '\')" class="btn btn-danger" style="margin-top: 10px; margin-left: -10px"><i class="fa fa-trash"></i></button>\n' +
        '</div>\n' +
        '</div>';
    $('#temp-terapi').append(conten);
}

function tambahPrognosis() {
    var cek = $('.form-prognosis').length;
    var count = +parseInt(cek) + 1;
    var id = "prognosis" + count;
    var conten = '<div class="form-group form-prognosis" id="' + id + '">\n' +
        '<div class="col-md-3">\n' +
        '<input class="form-control ket-prognosis" style="margin-top: 7px" placeholder="Keterangan">\n' +
        '</div>\n' +
        '<div class="col-md-3">\n' +
        '<select class="form-control hari-prognosis" style="margin-top: 7px">\n' +
        '<option value="">[Select One]</option>\n' +
        '<option value="Senin">Senin</option>\n' +
        '<option value="Selasa">Selasa</option>\n' +
        '<option value="Rabu">Rabu</option>\n' +
        '<option value="Kamis">Kamis</option>\n' +
        '<option value="Jumat">Jumat</option>\n' +
        '<option value="Sabtu">Sabtu</option>\n' +
        '<option value="Minggu">Minggu</option>\n' +
        '</select>\n' +
        '</div>\n' +
        '<div class="col-md-3">\n' +
        '<input class="form-control tgl-pattern tgl-prognosis" style="margin-top: 7px" placeholder="Tanggal">\n' +
        '</div>\n' +
        '<div class="col-md-2">\n' +
        '<input class="form-control jam-pattern jam-prognosis" style="margin-top: 7px" placeholder="Jam">\n' +
        '</div>\n' +
        '<div class="col-md-1">\n' +
        '<button onclick="delTerapi(\'' + id + '\')" class="btn btn-danger" style="margin-top: 10px; margin-left: -10px"><i class="fa fa-trash"></i></button>\n' +
        '</div>\n' +
        '</div>';
    $('#temp-prognosis').append(conten);

    var tglPattern = $('.tgl-pattern').length;
    var jamPattern = $('.jam-pattern').length;

    if (tglPattern > 0) {
        $('.tgl-pattern').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tgl-pattern').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
    }
    if (jamPattern > 0) {
        $('.jam-pattern').timepicker();
        $('.jam-pattern').inputmask('hh:mm', {'placeholder': 'hh:mm'});
    }
}

function tambahOp() {
    var cek = $('.form-op').length;
    var count = +parseInt(cek) + 1;
    var id = "op" + count;
    var content = '<hr class="garis" id="row' + count + '"><div class="row form-op" id="' + id + '">\n' +
        '<div class="form-group">\n' +
        '    <div class="col-md-3">\n' +
        '        <input class="form-control tgl-ptn tgl-op" style="font-size: 12px">\n' +
        '    </div>\n' +
        '    <div class="col-md-5">\n' +
        '        <textarea class="form-control tindakan-op" placeholder="Tindakan" style="font-size: 12px" rows="3"></textarea>\n' +
        '    </div>\n' +
        '    <div class="col-md-4">\n' +
        '        <textarea class="form-control icd-op" placeholder="ICD9 CM" style="font-size: 12px" rows="3"></textarea>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="form-group">\n' +
        '    <div class="col-md-2">\n' +
        '        <input class="form-control b-op" placeholder="B" style="font-size: 12px; margin-top: 7px">\n' +
        '    </div>\n' +
        '    <div class="col-md-2">\n' +
        '        <input class="form-control s-op" placeholder="S" style="font-size: 12px; margin-top: 7px">\n' +
        '    </div>\n' +
        '    <div class="col-md-2">\n' +
        '        <input class="form-control k-op" placeholder="K" style="font-size: 12px; margin-top: 7px">\n' +
        '    </div>\n' +
        '    <div class="col-md-5">\n' +
        '        <input class="form-control anastesi-op" placeholder="Anastesi" style="font-size: 12px; margin-top: 7px">\n' +
        '    </div>\n' +
        '    <div class="col-md-1">\n' +
        '        <button onclick="deleteOpp(\'' + count + '\')" class="btn btn-danger"><i class="fa fa-trash"></i></button>\n' +
        '    </div>\n' +
        '</div>\n' +
        '</div>';

    $('#temp-op').append(content);

    var tglPattern = $('.tgl-ptn').length;

    if (tglPattern > 0) {
        $('.tgl-ptn').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tgl-ptn').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
    }
}

function delTerapi(id) {
    $('#' + id).remove();
}

function deleteOpp(id) {
    $("#op" + id).remove();
    $('#row' + id).remove();
}

function showKetRing(value, ket) {
    if (value == "Meninggal dunia, sebab kematian" || value == "Rujuk" ||
        value == "Puskesmas" || value == "Polisi" || value == "Dirujuk" ||
        value == "Permintaan Sendiri" || value == "Lain-Lain") {
        $('#form-ring-' + ket).show();
    } else {
        $('#form-ring-' + ket).hide();
    }
}