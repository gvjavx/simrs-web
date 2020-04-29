function showModalAsesmenRawatInap(jenis) {
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();

        if ("privasi" == jenis) {
            $('.nama-pasien').val(namaPasien);
            $('.no-rm').val(idPasien);
            $('.tgl').val(tglLahir);
            $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
        }

        var jam = $('.jam').length;
        var tgl = $('.tgl').length;

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
    }

    if ("catatan_integrasi" == jenis) {
        listCatatanTerintegrasi();
    }

    if ("catatan_pemberian" == jenis) {
        listCatatanPemberianObat();
    }

    if ("asuhan" == jenis) {
        listAsuhanKeperawatan();
    }

    if ("rekonsiliasi" == jenis) {
        listRekonsiliasiObat();
    }

    if("ringkasan_rj" == jenis){
        listRekamMedisRJ();
    }

    $('#modal-ina-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveAsesmenRawatInap(jenis, ket) {
    var data = [];
    var cek = false;
    if ("riwayat_kesehatan" == jenis) {
        var va1 = $('#rk1').val();
        var va2 = $('#rk2').val();
        var va3 = $('#rk3').val();
        var va4 = $('#rk4').val();
        var va5 = $('[name=rk5]:checked').val();

        var va6 = "";
        var val6 = $('[name=rk6]:checked').val();
        if (val6 == "Ya") {
            var a = $('#ket1_rk6').val();
            var b = $('#ket2_rk6').val();
            if (a && b != '') {
                va6 = a + ', ' + b;
            }
        } else {
            if (val6 != undefined) {
                va6 = val6;
            }
        }

        var va7 = "";
        var val7 = $('[name=rk7]:checked').val();
        if (val7 == "Ya") {
            var a = $('[name=ket1_rk7]:checked').val();
            var b = $('#ket2_rk7').val();
            if (a == "Ya" && b != '') {
                va7 = a + ', ' + 'Reaksi, ' + b;
            } else {
                if (a != undefined) {
                    va7 = val7 + ', Reaksi, ' + a;
                }
            }
        } else {
            if (val7 != undefined) {
                va7 = val7;
            }
        }

        var va8 = "";
        var val8 = $('[name=rk8]:checked').val();
        if (val8 == "Ya") {
            var a = $('#ket1_rk8').val();
            var b = $('#ket2_rk8').val();
            if (a && b != '') {
                va8 = a + ' Batang/Hari' + ', ' + b + ' Tahun';
            }
        } else {
            if (val8 != undefined) {
                va8 = val8;
            }
        }

        var va9 = "";
        var val9 = $('[name=rk9]:checked').val();
        if (val9 == "Ya") {
            var a = $('#ket1_rk9').val();
            var b = $('#ket2_rk9').val();
            if (a && b != '') {
                va9 = a + ', ' + 'Jumlah ' + b;
            }
        } else {
            if (val9 != undefined) {
                va9 = val9;
            }
        }

        var va10 = "";
        var val10 = $('[name=rk10]:checked').val();
        if (val10 == "Ya") {
            var a = $('#ket1_rk10').val();
            if (a != '') {
                va10 = a;
            }
        } else {
            if (val10 != undefined) {
                va10 = val10;
            }
        }
        if (va1 && va2 && va3 && va4 && va6 && va7 && va8 && va9 && va10 != '' && va5 != undefined) {

            data.push({
                'parameter': 'Alasan Masuk RS',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Awal',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Kesehatan yang lalu',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Keluarga',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Obat yang sedang dikonsumsi',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Alergi',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Transfusi Darah',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Merokok',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Minum Minuman Keras',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat pergi ke luar negeri',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("keadaan_umum" == jenis) {
        var va1 = $('[name=kd1]:checked').val();
        var va2 = $('#kd2_1').val();
        var va3 = $('#kd2_2').val();
        var va4 = $('#kd2_3').val();
        var va5 = $('#kd2_4').val();
        var va6 = $('#kd3').val();
        var va7 = $('#kd4').val();
        var va8 = $('#kd5').val();
        var va9 = $('[name=ket_kd5]:checked').val();
        var va10 = $('#kd6').val();
        var va11 = $('[name=ket_kd6]:checked').val();
        var va12 = $('#kd7').val();
        var va13 = $('#kd7').val();

        if (va1 && va9 && va11 != undefined &&
            va2 && va3 && va4 && va5 && va6 && va7 && va8 && va10 && va12 && va13 != '') {

            data.push({
                'parameter': 'Kesadaran',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'GCS',
                'jawaban': 'E = ' + va2 + ', V = ' + va3 + ', M = ' + va4 + ', Hasil = ' + va5,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban': va6 + ' mmHg',
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': va7 + ' C',
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': va8 + ' x/menit, ' + va9,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nafas',
                'jawaban': va10 + ' x/menit, ' + va11,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan',
                'jawaban': va12 + ' Kg',
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tinggi Badan',
                'jawaban': va13 + ' cm',
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pemeriksaan_fisik" == jenis) {

        var va1 = "";
        var va2 = "";
        var va3 = "";
        var va4 = "";
        var va5 = "";
        var va6 = "";
        var va7 = "";
        var va8 = "";
        var va9 = "";
        var va10 = "";
        var va11 = "";
        var va12 = "";

        var v1 = $('[name=pfi1]:checked').val();
        if (v1 != undefined) {
            var a = $('#ket_pfi1').val();
            if (v1 == "Ada kelainan") {
                if (a != '') {
                    va1 = v1 + ', ' + a;
                }
            } else {
                va1 = v1;
            }
        }

        var v2 = $('[name=pfi2]:checked').val();
        if (v2 != undefined) {
            var a = $('#ket_pfi2').val();
            if (v2 == "Ada kelainan" || v2 == "Kelainan bawaan" || v2 == "Lain-Lain") {
                if (a != '') {
                    va2 = v2 + ', ' + a;
                }
            } else {
                va2 = v2;
            }
        }

        var v3 = $('[name=pfi3]:checked').val();
        if (v3 != undefined) {
            var a = $('#ket_pfi3').val();
            if (v3 == "Ada kelainan") {
                if (a != '') {
                    va3 = v3 + ', ' + a;
                }
            } else {
                va3 = v3;
            }
        }

        var v4 = $('[name=pfi4]:checked').val();
        if (v4 != undefined) {
            var a = $('#ket_pfi4').val();
            if (v4 == "Ada kelainan") {
                if (a != '') {
                    va4 = v4 + ', ' + a;
                }
            } else {
                va4 = v4;
            }
        }

        var v5 = $('[name=pfi5]:checked').val();
        if (v5 != undefined) {
            var a = $('#ket1_pfi5').val();
            var b = $('#ket2_pfi5').val();
            var c = $('#ket3_pfi5').val();
            var d = $('#ket4_pfi5').val();
            if (v5 == "Lain-Lain") {
                if (d != '') {
                    va5 = d;
                }
            } else if (v5 == "NGT") {
                if (a != '' && b != '') {
                    va5 = v5 + ', Tanggal ' + a + ', NO ' + b;
                }
            } else if (v5 == "O2 Nasal") {
                if (c != '') {
                    va5 = v5 + ', ' + c + ' lt/menit';
                }
            } else {
                va5 = v5;
            }
        }

        var v6 = $('[name=pfi6]:checked').val();
        console.log(v6);
        if (v6 != undefined) {
            var a = $('#ket_pfi6').val();
            if (v6 == "Lain-Lain") {
                if (a != '') {
                    va6 = a;
                }
            } else {
                va6 = v6;
            }
        }

        var v7 = $('[name=pfi7]:checked').val();
        if (v7 != undefined) {
            var a = $('#ket_pfi7').val();
            if (v7 == "Lain-Lain") {
                if (a != '') {
                    va7 = a;
                }
            } else {
                va7 = v7;
            }
        }

        var v8 = $('[name=pfi8]:checked').val();
        if (v8 != undefined) {
            var a = $('#ket_pfi8').val();
            if (v8 == "Lain-Lain") {
                if (a != '') {
                    va8 = a;
                }
            } else {
                va8 = v8;
            }
        }

        var v9 = $('[name=pfi9]:checked').val();
        if (v9 != undefined) {
            var a = $('#ket_pfi9').val();
            if (v9 == "Ada kelainan") {
                if (a != '') {
                    va9 = v9 + ', ' + a;
                }
            } else {
                va9 = v9;
            }
        }

        var v10 = $('[name=pfi10]:checked').val();
        if (v10 != undefined) {
            var a = $('#ket_pfi10').val();
            if (v10 == "Lain-Lain" || v10 == "Cacat bawaan") {
                if (a != '') {
                    va10 = a;
                }
            } else {
                va10 = v10;
            }
        }

        var v11 = $('[name=pfi11]:checked').val();
        if (v10 != undefined) {
            var a = $('#ket_pfi11').val();
            if (v11 == "Lain-Lain" || v11 == "Kelemahan gerak") {
                if (a != '') {
                    va11 = a;
                }
            } else {
                va11 = v11;
            }
        }

        var v12 = $('[name=pfi12]:checked').val();
        if (v12 != undefined) {
            var a = $('#ket_pfi12').val();
            if (v12 == "Ada kelainan") {
                if (a != '') {
                    va12 = v12 + ', ' + a;
                }
            } else {
                va12 = v12;
            }
        }

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va12 != '') {

            data.push({
                'parameter': 'Kepala',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Wajah',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mata',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Telinga',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hidung',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mulut',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Leher',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dada',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perut',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anggota',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Gerak',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kulit',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("resiko_dekubitus" == jenis) {
        var va1 = $('[name=rd1]:checked').val();
        var va2 = $('[name=rd2]:checked').val();
        var va3 = $('[name=rd3]:checked').val();
        var va4 = $('[name=rd4]:checked').val();
        var va5 = $('[name=rd5]:checked').val();

        if (va1 && va2 && va3 && va4 && va5 != undefined) {

            var isi1 = va1.split("|")[0];
            var isi2 = va2.split("|")[0];
            var isi3 = va3.split("|")[0];
            var isi4 = va4.split("|")[0];
            var isi5 = va5.split("|")[0];

            var skor1 = va1.split("|")[1];
            var skor2 = va2.split("|")[1];
            var skor3 = va3.split("|")[1];
            var skor4 = va4.split("|")[1];
            var skor5 = va5.split("|")[1];

            data.push({
                'parameter': 'Kondisi Fisik',
                'jawaban': isi1,
                'skor': skor1,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kondisi Mental',
                'jawaban': isi2,
                'skor': skor2,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rentang Aktivitas',
                'jawaban': isi3,
                'skor': skor3,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mobilitas',
                'jawaban': isi4,
                'skor': skor4,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Inkontinentia',
                'jawaban': isi5,
                'skor': skor5,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("nyeri" == jenis) {
        var va1 = $('[name=ny1]:checked').val();
        var va2 = $('[name=ny2]:checked').val();
        var va3 = $('#ny3').val();
        if (va1 && va2 != undefined && va3 != '') {
            data.push({
                'parameter': 'Nyeri',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Intensitas',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("funsional" == jenis) {

        var va1 = $('[name=fun1]:checked').val();
        var va2 = $('[name=fun2]:checked').val();
        var va3 = $('[name=fun3]:checked').val();
        var va4 = $('[name=fun4]:checked').val();
        var va5 = $('[name=fun5]:checked').val();

        if (va1 && va2 && va3 && va4 && va5 != undefined) {

            var isi1 = va1.split("|")[0];
            var isi2 = va2.split("|")[0];
            var isi3 = va3.split("|")[0];
            var isi4 = va4.split("|")[0];
            var isi5 = va5.split("|")[0];

            var skor1 = va1.split("|")[1];
            var skor2 = va2.split("|")[1];
            var skor3 = va3.split("|")[1];
            var skor4 = va4.split("|")[1];
            var skor5 = va5.split("|")[1];

            data.push({
                'parameter': 'Makan/Minum',
                'jawaban': isi1,
                'skor': skor1,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mandi',
                'jawaban': isi2,
                'skor': skor2,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Memakai Baju',
                'jawaban': isi3,
                'skor': skor3,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'BAB/BAK',
                'jawaban': isi4,
                'skor': skor4,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Naik Turun Tangga',
                'jawaban': isi5,
                'skor': skor5,
                'keterangan': jenis,
                'jenis': 'asesmen',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("kriteria_discharge_planing" == jenis) {

        var va1 = $('[name=kdp1]:checked').val();
        var va2 = $('[name=kdp2]:checked').val();
        var va3 = $('[name=kdp3]:checked').val();
        var va4 = $('[name=kdp4]:checked').val();
        var va5 = $('#kdp5').val();
        var va6 = $('#kdp6').val();
        var va7 = "";
        var va8 = "";

        var v7 = $('[name=kdp7]:checked').val();
        if (v7 != undefined) {
            var a = $('#ket_kdp7').val();
            if (v7 == "Lainnya") {
                if (a != '') {
                    va7 = a;
                }
            } else {
                va7 = v7;
            }
        }

        var v8 = $('[name=kdp8]:checked').val();
        if (v8 != undefined) {
            var a = $('#ket_kdp8').val();
            if (v8 == "Lainnya") {
                if (a != '') {
                    va8 = a;
                }
            } else {
                va8 = v8;
            }
        }

        if (va1 && va2 && va3 && va4 != undefined && va5 && va6 && va7 && va8 != '') {

            data.push({
                'parameter': '1. Pasien usia > 65 thn',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Keterbatasan Mobilitas',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Prawatan atau pengobatan lanjut',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '4. Bantuan untuk melakukan aktivitas sehari hari',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosis Utama',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosis Sekunder',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Transportasi Pulang',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Membutuhkan pendamping selama di rumah',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("kebutuhan_discharge_planing" == jenis) {

        var va1 = "";
        var va2 = "";
        var va3 = "";
        var va4 = "";
        var va5 = "";
        var tin = "";
        var va6 = "";

        var v1 = $('[name=plan1]');
        $.each(v1, function (i, item) {
            if (item.checked) {
                var valu = "";
                var a = $('#ket_plan1').val();

                if (item.value == "Lainnya") {
                    valu = a;
                } else {
                    valu = item.value;
                }

                if (va1 != '') {
                    va1 = va1 + '|' + valu;
                } else {
                    va1 = valu;
                }
            }
        });

        var v2 = $('[name=plan2]');
        $.each(v2, function (i, item) {
            if (item.checked) {
                var valu = "";
                var a = $('#ket_plan2').val();

                if (item.value == "Lainnya") {
                    valu = a;
                } else {
                    valu = item.value;
                }

                if (va2 != '') {
                    va2 = va2 + '|' + valu;
                } else {
                    va2 = valu;
                }
            }
        });

        var v3 = $('[name=plan3]');
        $.each(v3, function (i, item) {
            if (item.checked) {
                var valu = "";
                var a = $('#ket_plan3').val();

                if (item.value == "Lainnya") {
                    valu = a;
                } else {
                    valu = item.value;
                }

                if (va3 != '') {
                    va3 = va3 + '|' + valu;
                } else {
                    va3 = valu;
                }
            }
        });

        var v4 = $('[name=plan5]');
        $.each(v4, function (i, item) {
            if (item.checked) {
                var valu = "";
                var a = $('#ket_plan5').val();

                if (item.value == "Lainnya") {
                    valu = a;
                } else {
                    valu = item.value;
                }

                if (va4 != '') {
                    va4 = va4 + '|' + valu;
                } else {
                    va4 = valu;
                }
            }
        });

        var v5 = $('[name=plan6]');
        $.each(v5, function (i, item) {
            if (item.checked) {
                var valu = "";
                var a = $('#ket1_plan6').val();
                var b = $('#ket2_plan6').val();
                if (item.value == "Lainnya") {
                    valu = b;
                } else if (item.value == "Pendamping terlatih") {
                    valu = item.value + ', yaitu ' + a;
                } else {
                    valu = item.value;
                }
                if (va5 != '') {
                    va5 = va5 + '|' + valu;
                } else {
                    va5 = valu;
                }
            }
        });

        var tn = $('[name=plan4]');
        $.each(tn, function (i, item) {
            if (item.checked) {
                var valu = "";
                var a = $('#ket_plan4').val();
                if (item.value == "Tindakan") {
                    valu = item.value + ', ' + a;
                } else {
                    valu = item.value;
                }
                if (tin != '') {
                    tin = tin + '|' + valu;
                } else {
                    tin = valu;
                }
            }
        });

        var v6 = $('[name=plan7]');
        $.each(v6, function (i, item) {
            if (item.checked) {
                var valu = "";
                var a = $('#ket1_plan7').val();
                var b = $('#ket2_plan7').val();
                if (item.value == "Lainnya") {
                    valu = b;
                } else if (item.value == "Konsultasi kepada") {
                    valu = item.value + ', yaitu ' + a;
                } else {
                    valu = item.value;
                }
                if (va6 != '') {
                    va6 = va6 + '|' + valu;
                } else {
                    va6 = valu;
                }
            }
        });

        if (tin != '') {
            tin = "Tujuan|" + tin;
        }

        if (va1 && va2 && va3 && va4 && va5 != '') {

            data.push({
                'parameter': 'Diperkirakan akan membutuhkan bantuan dalam aktivitas sehari hari, misal: Pasca Stroke, Gangguan penglihatan, Pasca operasi, dll',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Membutuhkan diet dan edukasi gizi yang kompleks terkait penyakitnya',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Membutuhkan penanganan nyeri yang kronis',
                'jawaban': va3 + '|' + tin,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diperkirakan akan membutuhkan pengelolaan penyakit secara berkelanjutan di luar RSG',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Membutuhkan penanganan penyakit berkelanjutan\n' +
                    '<p>a. Perawatan luka: lokasi tidak terjangkau, luas</p>\n' +
                    '<p style="margin-top: -10px">b. Perawatan pasca melahirkan: perawatan bayi BBLR</p>',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kebutuhan lainnya',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("early_warning_score" == jenis) {
        var tgl = $('#ews1').val();
        var jam = $('#ews2').val();
        var va1 = $('[name=ews3]:checked').val();
        var va2 = $('[name=ews4]:checked').val();
        var va3 = $('[name=ews5]:checked').val();
        var va4 = $('[name=ews6]:checked').val();
        var va5 = $('#ews7').val();
        var va6 = $('#ews8').val();
        var va7 = $('#ews9').val();
        var va8 = $('#ews10').val();
        var va9 = $('#ews11').val();
        var ttd = document.getElementById("ews12");
        var ttdCek = isBlank(ttd);

        if (va1 && va2 && va3 && va4 != undefined && va5 && va6 && va7 && va8 && va9 && !ttdCek) {
            var isi1 = va1.split("|")[0];
            var isi2 = va2.split("|")[0];
            var isi3 = va3.split("|")[0];
            var isi4 = va4.split("|")[0];

            var skor1 = va1.split("|")[1];
            var skor2 = va2.split("|")[1];
            var skor3 = va3.split("|")[1];
            var skor4 = va4.split("|")[1];

            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': tgl + ' ' + jam,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perilaku/Keadaan Umum',
                'jawaban': isi1,
                'skor': skor1,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kardiovaskuler',
                'jawaban': isi2,
                'skor': skor2,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Respirasi',
                'jawaban': isi3,
                'skor': skor3,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pasien 1/4 jam nebulisasi (terus menerus) atau muntah persisten setelah operasi',
                'jawaban': isi4,
                'skor': skor4,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });

            var total = parseInt(skor1) + parseInt(skor2) + parseInt(skor3) + parseInt(skor4);
            data.push({
                'parameter': '<b>Total Skor PEWS</b>',
                'skor': "" + total,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pernafasan/RP.',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'SpO2',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': 'early_warning',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanda Tangan Perawat',
                'jawaban': ttd1,
                'keterangan': jenis,
                'jenis': 'ttd_early_warning_score',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("privasi_pasien" == jenis) {
        var va1 = $('#pp1').val();
        var va2 = $('#pp2').val();
        var va3 = "";
        var v = $('#pp3').val();
        if (v != '') {
            var a = $('#ket_pp3').val();
            if (v == "Lainnya") {
                va3 = a;
            } else {
                va3 = v;
            }
        }
        var va4 = $('#pp4').val();
        var va5 = $('#pp5').val();
        var va6 = $('#pp6').val();
        var ttd = document.getElementById("pp7");
        var ttdCek = isBlank(ttd);

        if (va1 && va2 && va3 && va4 && va5 && va6 != '' && !ttdCek) {

            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Nama',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'privasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'privasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hubungan dengan Pasien',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'privasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Pasien',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'privasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Lahir',
                'jawaban': va5.split("-").reverse().join("-"),
                'keterangan': jenis,
                'jenis': 'privasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nomor RM',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'privasi',
                'id_detail_checkup': idDetailCheckup
            });

            var namaTcm = $('.nama-tcm');
            var alamatTcm = $('.alamat-tcm');
            var tr = "";
            $.each(namaTcm, function (i, item) {
                if (item.value != '') {
                    tr += '<tr><td>' + item.value + '</td><td>' + alamatTcm[i].value + '</td></tr>';
                }
            });

            data.push({
                'parameter': 'Yang tidak diperbolehkan',
                'jawaban': tr,
                'keterangan': jenis,
                'jenis': 'nama_tercantum',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Tanda Tangan Pembuat Pernyataan',
                'jawaban': ttd1,
                'keterangan': jenis,
                'jenis': 'ttd_privasi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("asesmen_edukasi_pasien" == jenis) {
        var va1 = "";
        var va2 = "";
        var va3 = "";
        var va4 = "";
        var va5 = "";
        var va6 = $('[name=ep6]:checked').val();
        var va7 = $('[name=ep7]:checked').val();
        var va8 = $('[name=ep8]:checked').val();
        var va9 = "";
        var va10 = "";

        var v1 = $('[name=ep1]:checked').val();
        if (v1 != undefined) {
            var ket = $('#ket_ep1').val();
            if (v1 == "Ya") {
                va1 = ket;
            } else {
                va1 = v1;
            }
        }

        var v2 = $('[name=ep2]:checked').val();
        if (v2 != undefined) {
            var ket = $('#ket_ep2').val();
            if (v2 == "Daerah" || v2 == "Lainnya") {
                va2 = ket;
            } else {
                va2 = v2;
            }
        }

        var v3 = $('[name=ep3]:checked').val();
        if (v3 != undefined) {
            var ket = $('#ket_ep3').val();
            if (v3 == "Lainnya") {
                va3 = ket;
            } else {
                va3 = v3;
            }
        }

        var v4 = $('[name=ep4]:checked').val();
        if (v4 != undefined) {
            var ket = $('#ket_ep4').val();
            if (v4 == "Ya") {
                va4 = ket;
            } else {
                va4 = v4;
            }
        }

        var v5 = $('[name=ep5]:checked').val();
        if (v5 != undefined) {
            var ket = $('#ket_ep5').val();
            if (v5 == "Lainnya") {
                va5 = ket;
            } else {
                va5 = v5;
            }
        }

        var v9 = $('[name=ep9]:checked').val();
        if (v9 != undefined) {
            var ket = $('#ket_ep9').val();
            if (v9 == "Ya") {
                va9 = ket;
            } else {
                va9 = v9;
            }
        }

        var v10 = $('[name=ep10]:checked').val();
        if (v10 != undefined) {
            var ket = $('#ket_ep10').val();
            if (v10 == "Ya") {
                va10 = ket;
            } else {
                va10 = v10;
            }
        }

        if (va1 && va2 && va3 && va4 && va5 && va9 && va10 != '' && va6 && va7 && va8 != undefined) {
            data.push({
                'parameter': 'Keyakinan/Nilai-nilai',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Bahasa',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Agama',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kebutuhan Peterjemah',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pendidikan pasien',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Baca dan Tulis',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pilihan tipe pembelajaran',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hambatan Edukasi',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keterbatasan fisik dan kognitif',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesedian menerima informasi/edukasi',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("perencanaan_edukasi_pasien" == jenis) {
        var va1 = $('[name=per1]');
        var temp = "";
        $.each(va1, function (i, item) {
            if (item.checked) {
                if (temp != '') {
                    temp = temp + '|' + item.value;
                } else {
                    temp = item.value;
                }
            }
        });

        if (temp != '') {
            data.push({
                'parameter': 'Perencanaan',
                'jawaban': temp,
                'keterangan': jenis,
                'jenis': 'edukasi_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("rencana_gigi_pasien" == jenis) {
        var va1 = $('#gg1').val();
        var va2 = $('#gg2').val();
        var va3 = $('[name=gg3]:checked').val();
        var va4 = $('[name=gg4]:checked').val();
        var va5 = $('[name=gg5]:checked').val();
        var va6 = $('[name=gg6]:checked').val();
        var va7 = $('[name=gg7]:checked').val();
        var va8 = $('[name=gg8]:checked').val();
        var va9 = $('[name=gg9]:checked').val();

        var temp1 = "";
        var temp2 = "";
        if (va8 != undefined) {
            var ket = $('#ket_gg8').val();
            if (va8 == "Ada") {
                temp1 = va8 + ', ' + ket;
            } else {
                temp1 = va8;
            }
        }
        if (va9 != undefined) {
            var ket = $('#ket_gg9').val();
            if (va9 == "Ada") {
                temp2 = va9 + ', ' + ket;
            } else {
                temp2 = va9;
            }
        }
        var va10 = $('#gg10').val();
        var va11 = $('#gg011').val();
        var va12 = $('#gg012').val();
        var ttd = document.getElementById("gg013");
        var cekTtd = isBlank(ttd);

        if (va1 && va2 && temp1 && temp2 && va10 && va11 && va12 != '' &&
            va3 && va4 && va5 && va6 && va7 != undefined && !cekTtd) {

            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Golongan Darah',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penyakit Jantung',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diabetes',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Haemopilia',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hepatitis',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Gastritis',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penyakit Lainnya',
                'jawaban': temp1,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alergi Obat',
                'jawaban': temp2,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alergi Makanan',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Obat yang sedang dikonsumsi',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal pencatatan data',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': 'rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanda Tangan',
                'jawaban': ttd1,
                'keterangan': jenis,
                'jenis': 'ttd_rencana_gigi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        $('#save_ina_' + jenis).hide();
        $('#load_ina_' + jenis).show();
        dwr.engine.setAsync(true);
        AsesmenRawatInapAction.saveAsesmenRawat(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#modal-ina-' + jenis).modal('hide');
                    $('#warning_ina_' + ket).show().fadeOut(5000);
                    $('#msg_ina_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-ina-' + jenis).scrollTop(0);
                } else {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#warning_ina_' + jenis).show().fadeOut(5000);
                    $('#msg_ina_' + jenis).text(res.msg);
                    $('#modal-ina-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function detailAsesmenRawatInap(jenis) {
    if (jenis != '') {
        var head = "";
        var body = "";
        var totalSkor = 0;
        var first = "";
        var last = "";
        var tgl = "";
        var cekData = false;
        var tabeleTcm = '<table class="table table-bordered" style="font-size: 12px">' +
            '<thead>' +
            '<td>Nama</td>' +
            '<td>Alamat</td>' +
            '</thead>' +
            '</table>';
        var tercamtum = "";
        var cekTercamtum = false;
        var cekJenis = "";

        AsesmenRawatInapAction.getListAsesmenRawat(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var jwb = "";
                    var skor = "";
                    if (item.jawaban != null) {
                        jwb = item.jawaban;
                    }

                    if ("resiko_dekubitus" == item.keterangan || "funsional" == item.keterangan) {
                        if (item.skor != null) {
                            skor = item.skor;
                            totalSkor = parseInt(totalSkor) + parseInt(item.skor);
                        }
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb + '</td>' +
                            '<td align="center" width="10%">' + skor + '</td>' +
                            '</tr>';
                    } else if ("kebutuhan_discharge_planing" == item.keterangan) {
                        var li = "";
                        var isi = jwb.split("|");
                        $.each(isi, function (i, item) {
                            if (item == "Tujuan") {
                                li += '<br><li style="list-style-type: none; font-weight: bold">' + item + '</li>';
                            } else {
                                li += '<li>' + item + '</li>';
                            }
                        });

                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td width="40%">' + '<ul style="margin-left: 10px">' + li + '</ul>' + '</td>' +
                            '</tr>';
                    } else if ("early_warning_score" == item.keterangan) {
                        if (item.skor != null) {
                            skor = item.skor;
                        }
                        if ("ttd_early_warning_score" == item.jenis) {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + '<img style="height: 50px" src="' + jwb + '">' + '</td>' +
                                '<td width="10%" align="center">' + skor + '</td>' +
                                '</tr>';
                        } else {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td width="30%">' + jwb + '</td>' +
                                '<td width="10%" align="center">' + skor + '</td>' +
                                '</tr>';
                        }
                    } else if ("privasi_pasien" == jenis) {
                        if ("ttd_privasi" == item.jenis) {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + '<img style="height: 50px" src="' + jwb + '">' + '</td>' +
                                '</tr>';
                        } else if ("nama_tercantum" == item.jenis) {
                            var isi = "";
                            if (cekJenis != item.createdDate) {
                                cekJenis = item.jenis;
                                var tabeleTcm = '<label>Yang tidak dibolehkan adalah</label>' +
                                    '<table class="table table-bordered" style="font-size: 12px">' +
                                    '<thead>' +
                                    '<td>Nama</td>' +
                                    '<td>Alamat</td>' +
                                    '</thead>' +
                                    '<tbody>' + jwb + '</tbody>' +
                                    '</table>';
                                body += '<tr>' +
                                    '<td colspan="2">' + tabeleTcm + '</td>' +
                                    '</tr>';
                            }
                        } else {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + jwb + '</td>' +
                                '</tr>';
                        }
                    } else if ("perencanaan_edukasi_pasien" == jenis) {
                        var li = "";
                        var jml = jwb.split("|");
                        $.each(jml, function (i, item) {
                            li += '<li>' + item + '</li>';
                        });
                        body += '<tr>' +
                            '<td>' + '<ul style="margin-left: 15px">' + li + '</ul>' + '</td>' +
                            '</tr>';

                    } else if ("rencana_gigi_pasien" == jenis) {
                        if ("ttd_rencana_gigi" == item.jenis) {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + '<img style="height: 50px" src="' + jwb + '">' + '</td>' +
                                '</tr>';
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

            if ("resiko_dekubitus" == jenis || "funsional" == jenis) {
                var con = "";
                if (totalSkor >= 5 && totalSkor <= 10) {
                    con = "Tinggi";
                } else if (totalSkor >= 11 && totalSkor <= 15) {
                    con = "Sedang"
                } else if (totalSkor >= 16 && totalSkor <= 20) {
                    con = "Rendah"
                }
                if (cekData) {
                    if ("resiko_dekubitus" == jenis) {
                        last = '<tr style="font-weight: bold"><td colspan="2">Total</td><td align="center">' + totalSkor + '</td></tr>' +
                            '<tr style="font-weight: bold" bgcolor="#ffebcd"><td colspan="2">Resiko Dekubitus</td><td align="center">' + con + '</td></tr>';
                    }
                    if ("funsional" == jenis) {
                        last = '<tr style="font-weight: bold"><td colspan="2">Total</td><td align="center">' + totalSkor + '</td></tr>';
                    }
                }
            }

            if ("privasi_pasien" == jenis) {

            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_ina_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_ina_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_ina_' + jenis).attr('src', url);
            $('#btn_ina_' + jenis).attr('onclick', 'delRowAsesmenRawatInap(\'' + jenis + '\')');
        });
    }
}

function delRowAsesmenRawatInap(id) {
    $('#del_ina_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_ina_' + id).attr('src', url);
    $('#btn_ina_' + id).attr('onclick', 'detailAsesmenRawatInap(\'' + id + '\')');
}

function showKetIna(value, ket) {
    if (value == "Ya" || value == "Ada kelainan" || value == "Kelainan bawaan" || value == "Lain-Lain" || value == "Cacat bawaan" || value == "Kelemahan gerak" || value == "Lainnya" || value == "Daerah" || value == "Ada") {
        $('#form-ina-' + ket).show();
    } else {
        $('#form-ina-' + ket).hide();
        $('#form-ina-' + ket + '1').hide();
        $('#form-ina-' + ket + '2').hide();
    }

    if (value == "NGT") {
        $('#form-ina-' + ket + '1').show();
    } else {
        $('#form-ina-' + ket + '1').hide();
    }

    if (value == "O2 Nasal") {
        $('#form-ina-' + ket + '2').show();
    } else {
        $('#form-ina-' + ket + '2').hide();
    }
}

function chekedChoice(id, ket) {
    var cek = $('#' + id).is(':checked');
    if (cek) {
        $('#form-ina-' + ket).show();
    } else {
        $('#form-ina-' + ket).hide();
    }
}

function saveCatatanTerintegrasi(jenis, ket) {
    var data = "";
    var va1 = $('#cp1').val();
    var va2 = $('#cp2').val();
    var va3 = $('#cp3').val();
    var va4 = $('#cp4').val();
    var va5 = $('#cp5').val();
    var v6 = document.getElementById("cp6");
    var v7 = document.getElementById("cp7");
    var va6 = isBlank(v6);
    var va7 = isBlank(v7);

    if (va1 && va2 && va3 && va4 && va5 != '' && !va6 && !va7) {

        var ttd1 = v6.toDataURL("image/png"),
            ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
        var ttd2 = v7.toDataURL("image/png"),
            ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1.split("-").reverse().join("-") + ' ' + va2 + ':00',
            'ppa': va3,
            'jenis': va4,
            'instruksi': va5,
            'keterangan': jenis,
            'ttd_petugas': ttd1,
            'ttd_dpjp': ttd2
        }

        var result = JSON.stringify(data);

        $('#save_ina_' + jenis).hide();
        $('#load_ina_' + jenis).show();
        dwr.engine.setAsync(true);
        CatatanTerintegrasiAction.saveCatatanTerintegrasi(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#modal-ina-' + jenis).modal('hide');
                    $('#warning_ina_' + ket).show().fadeOut(5000);
                    $('#msg_ina_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-ina-' + jenis).scrollTop(0);
                    listCatatanTerintegrasi();
                } else {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#warning_ina_' + jenis).show().fadeOut(5000);
                    $('#msg_ina_' + jenis).text(res.msg);
                    $('#modal-ina-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#save_ina_' + jenis).show();
        $('#load_ina_' + jenis).hide();
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function listCatatanTerintegrasi() {
    var table = "";
    CatatanTerintegrasiAction.getListCatatanTerintegrasi(idDetailCheckup, function (res) {
        if (res.length > 0) {
            console.log(res);
            $.each(res, function (i, item) {
                table += '<tr>' +
                    '<td>' + formaterDateTime(item.waktu) + '</td>' +
                    '<td>' + cekNull(item.ppa) + '</td>' +
                    '<td>' + cekNull(item.jenis) + '</td>' +
                    '<td>' + cekNull(item.intruksi) + '</td>' +
                    '<td>' + '<img style="width: 100%; height: 20px" src="' + item.ttdPetugas + '">' + '</td>' +
                    '<td>' + '<img style="width: 100%; height: 20px" src="' + item.ttdDpjp + '">' + '</td>' +
                    '</tr>';
            });
            $('#body_catatan_integrasi').html(table);
        }
    });
}

function saveCatatanPemberianObat(jenis, ket) {
    var data = "";
    var va1 = $('#cpo1').val();
    var va2 = $('#cpo2').val();
    var va3 = $('#cpo3').val();
    var va4 = $('#cpo4').val();
    var va5 = $('#cpo5').val();
    var va6 = $('#cpo6').val();
    var va7 = $('#cpo7').val();
    var va8 = $('#cpo8').val();
    var v9 = document.getElementById("cpo9");
    var v10 = document.getElementById("cpo10");
    var va9 = isBlank(v9);
    var va10 = isBlank(v10);

    if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 != '' && !va9 && !va10) {

        var ttd1 = v9.toDataURL("image/png"),
            ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
        var ttd2 = v10.toDataURL("image/png"),
            ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1 + ' ' + va2 + ':00',
            'nama_dokter': va3,
            'nama_obat': va4,
            'aturan_pakai': va5,
            'keterangan': jenis,
            'tanggal_mulai': va6.split("-").reverse().join("-"),
            'tanggal_stop': va7.split("-").reverse().join("-"),
            'status': va8,
            'ttd_dokter': ttd1,
            'ttd_apoteker': ttd2
        }

        var result = JSON.stringify(data);

        $('#save_ina_' + jenis).hide();
        $('#load_ina_' + jenis).show();
        dwr.engine.setAsync(true);
        CatatanPemberianObatAction.saveCatatanPemberianObat(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#modal-ina-' + jenis).modal('hide');
                    $('#warning_ina_' + ket).show().fadeOut(5000);
                    $('#msg_ina_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-ina-' + jenis).scrollTop(0);
                    listCatatanPemberianObat();
                } else {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#warning_ina_' + jenis).show().fadeOut(5000);
                    $('#msg_ina_' + jenis).text(res.msg);
                    $('#modal-ina-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#save_ina_' + jenis).show();
        $('#load_ina_' + jenis).hide();
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function listCatatanPemberianObat() {
    var table = "";
    CatatanPemberianObatAction.getListCatatanPemberianObat(idDetailCheckup, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                table += '<tr>' +
                    '<td>' + item.waktu + '</td>' +
                    '<td>' + cekNull(item.namaDokter) + '</td>' +
                    '<td>' + cekNull(item.namaObat) + '</td>' +
                    '<td>' + cekNull(item.aturanPakai) + '</td>' +
                    '<td>' + formaterDate(item.tanggalMulai) + '</td>' +
                    '<td>' + formaterDate(item.tanggalStop) + '</td>' +
                    '<td align="center">' + '<img style="width: 30px; height: 20px" src="' + item.ttdDokter + '">' + '</td>' +
                    '<td align="center">' + '<img style="width: 30px; height: 20px" src="' + item.ttdApoteker + '">' + '</td>' +
                    '<td align="center">' + convertCek(item.status) + '</td>' +
                    '</tr>';
            });
            $('#body_catatan_pemberian').html(table);
        }
    });
}

function saveAsuhanKeperawatan(jenis, ket) {
    var data = "";
    var tgl = $('#ake1').val();
    var jam = $('#ake2').val();
    var temp1 = "";
    var temp2 = "";
    var temp3 = "";
    var temp4 = "";
    var temp5 = "";
    var temp6 = "";
    var temp7 = "";
    var tempKet = "";
    var label1 = "Resiko/Gangguan integritas kulit berhubungan";
    var label2 = "NOC";
    var label3 = "NIC";

    var va1 = $('[name=ake3]');
    $.each(va1, function (i, item) {
        if (item.checked) {
            if (temp1 != '') {
                temp1 = temp1 + '|' + item.value;
            } else {
                temp1 = "|a. Eksternal|" + item.value;
            }
        }
    });

    var va2 = $('[name=ake4]');
    $.each(va2, function (i, item) {
        if (item.checked) {
            if (temp2 != '') {
                temp2 = temp2 + '|' + item.value;
            } else {
                temp2 = "|b. Internal|" + item.value;
            }
        }
    });

    var va3 = $('[name=ake5]');
    $.each(va3, function (i, item) {
        if (item.checked) {
            if (temp3 != '') {
                temp3 = temp3 + '|' + item.value;
            } else {
                temp3 = "|DS|" + item.value;
            }
        }
    });

    var va4 = $('[name=ake6]');
    $.each(va4, function (i, item) {
        if (item.checked) {
            if (temp4 != '') {
                temp4 = temp4 + '|' + item.value;
            } else {
                temp4 = "|DO|" + item.value;
            }
        }
    });

    var ket1 = $('#ake7').val();
    var ket2 = $('#ake8').val();
    if (ket1 && ket2) {
        tempKet = "|Setelah dilakukan tindakan keperawatan " + ket1 + "x" + ket2 + " menit/liter" + "Diharapkan integrasis kulit klien utuh dan tersisa";
    }

    var ket3 = $('#ket_ake10').val();

    var va5 = $('[name=ake9]');
    $.each(va5, function (i, item) {
        if (item.checked) {
            if (temp5 != '') {
                temp5 = temp5 + '|' + item.value;
            } else {
                temp5 = "|Kriteria|" + item.value;
            }
        }
    });

    var va6 = $('[name=ake10]');
    $.each(va6, function (i, item) {
        if (item.checked) {
            if (temp6 != '') {
                temp6 = temp6 + '|' + item.value;
            } else {
                temp6 = "|Mandiri|" + item.value;
            }
        }
    });

    var va7 = $('[name=ake11]');
    $.each(va7, function (i, item) {
        if (item.checked) {
            if (temp6 != '') {
                temp7 = temp7 + '|' + item.value;
            } else {
                temp7 = "|Kolaborasi|" + item.value;
            }
        }
    });

    var ttd = document.getElementById("ake_ttd");
    var va8 = isBlank(ttd);

    if (tgl && jam != '' && !va8) {

        var ttd1 = ttd.toDataURL("image/png"),
            ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': tgl.split("-").reverse().join("-") + ' ' + jam + ':00',
            'diagnosa': label1 + temp1 + temp2 + temp3 + temp4,
            'tujuan': label2 + tempKet + temp5,
            'intervensi': label3 + temp6 + temp7,
            'keterangan': jenis,
            'ttd_perawat': ttd1
        }

        var result = JSON.stringify(data);

        $('#save_ina_' + jenis).hide();
        $('#load_ina_' + jenis).show();
        dwr.engine.setAsync(true);
        RencanaAsuhanKeperawatanAction.save(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#modal-ina-' + jenis).modal('hide');
                    $('#warning_ina_' + ket).show().fadeOut(5000);
                    $('#msg_ina_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-ina-' + jenis).scrollTop(0);
                    listAsuhanKeperawatan();
                } else {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#warning_ina_' + jenis).show().fadeOut(5000);
                    $('#msg_ina_' + jenis).text(res.msg);
                    $('#modal-ina-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#save_ina_' + jenis).show();
        $('#load_ina_' + jenis).hide();
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function listAsuhanKeperawatan() {
    var table = "";
    RencanaAsuhanKeperawatanAction.getListDetail(idDetailCheckup, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                var diagnosa = "";
                var tujuan = "";
                var intervensi = "";
                var ul1 = "";
                var ul2 = "";
                var ul3 = "";
                if (item.diagnosa != null) {
                    var v = item.diagnosa.split("|");
                    $.each(v, function (i, item) {
                        if (item == "Resiko/Gangguan integritas kulit berhubungan") {
                            diagnosa += '<li style="list-style-type: none; font-weight: bold; margin-left: -15px">' + item + '</li>';
                        } else if (item == "a. Eksternal") {
                            diagnosa += '<li style="list-style-type: none; font-weight: bold; margin-left: -15px">' + item + '</li>';
                        } else if (item == "b. Internal") {
                            diagnosa += '<li style="list-style-type: none; font-weight: bold; margin-top: 15px; margin-left: -15px">' + item + '</li>';
                        } else if (item == "DS") {
                            diagnosa += '<li style="list-style-type: none; font-weight: bold; margin-top: 15px; margin-left: -15px">' + item + '</li>';
                        } else if (item == "DO") {
                            diagnosa += '<li style="list-style-type: none; font-weight: bold; margin-top: 15px; margin-left: -15px">' + item + '</li>';
                        } else {
                            diagnosa += '<li>' + item + '</li>';
                        }
                    });
                }

                if (item.tujuan != null) {
                    var v = item.tujuan.split("|");
                    $.each(v, function (i, item) {
                        if (item == "NOC") {
                            tujuan += '<li style="list-style-type: none; font-weight: bold; margin-left: -15px">' + item + '</li>';
                        } else if (item == "Kriteria") {
                            tujuan += '<li style="list-style-type: none; font-weight: bold; margin-top: 15px; margin-left: -15px">' + item + '</li>';
                        } else {
                            tujuan += '<li>' + item + '</li>';
                        }
                    });
                }

                if (item.intervensi != null) {
                    var v = item.intervensi.split("|");
                    $.each(v, function (i, item) {
                        if (item == "NIC") {
                            intervensi += '<li style="list-style-type: none; font-weight: bold; margin-left: -15px">' + item + '</li>';
                        } else if (item == "Mandiri") {
                            intervensi += '<li style="list-style-type: none; font-weight: bold; margin-left: -15px">' + item + '</li>';
                        } else if (item == "Kolaborasi") {
                            intervensi += '<li style="list-style-type: none; font-weight: bold; margin-top: 15px; margin-left: -15px">' + item + '</li>';
                        } else {
                            intervensi += '<li>' + item + '</li>';
                        }
                    });
                }

                if (diagnosa != '') {
                    ul1 = '<ul style="margin-left: 12px">' + diagnosa + '</ul>'
                }
                if (tujuan != '') {
                    ul2 = '<ul style="margin-left: 12px">' + tujuan + '</ul>'
                }
                if (intervensi != '') {
                    ul3 = '<ul style="margin-left: 12px">' + intervensi + '</ul>'
                }

                table += '<tr>' +
                    '<td>' + formaterDateTime(item.waktu) + '</td>' +
                    '<td>' + ul1 + '</td>' +
                    '<td>' + ul2 + '</td>' +
                    '<td>' + ul3 + '</td>' +
                    '<td align="center">' + '<img style="width: 50px; height: 20px" src="' + item.ttdPerawat + '">' + '</td>' +
                    '</tr>';
            });
            $('#body_asuhan').html(table);
        }
    });
}

function saveRekonsiliasiObat(jenis, ket) {
    var data = "";
    var va1 = $('#reo1').val();
    var va2 = $('#reo2').val();
    var va3 = $('#reo3').val();
    var va4 = $('#reo4').val();
    var va5 = $('#reo5').val();
    var va6 = $('#reo6').val();
    var va7 = $('[name=reo7]:checked').val();
    var v9 = document.getElementById("reo_ttd1");
    var v10 = document.getElementById("reo_ttd2");
    var va9 = isBlank(v9);
    var va10 = isBlank(v10);

    if (va1 && va2 && va3 && va4 && va5 && va6 != '' && va7 != undefined && !va9 && !va10) {

        var ttd1 = v9.toDataURL("image/png"),
            ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
        var ttd2 = v10.toDataURL("image/png"),
            ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

        data = {
            'id_detail_checkup': idDetailCheckup,
            'tanggal': va1.split("-").reverse().join("-"),
            'ruangan': va2,
            'nama_obat': va3,
            'dosis': va4,
            'aturan_pakai': va5,
            'indikasi': va6,
            'diteruskan': va7,
            'ttd_pasien': ttd1,
            'ttd_apoteker': ttd2
        }

        var result = JSON.stringify(data);

        $('#save_ina_' + jenis).hide();
        $('#load_ina_' + jenis).show();
        dwr.engine.setAsync(true);
        RekonsiliasiObatAction.save(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#modal-ina-' + jenis).modal('hide');
                    $('#warning_ina_' + ket).show().fadeOut(5000);
                    $('#msg_ina_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-ina-' + jenis).scrollTop(0);
                    listRekonsiliasiObat();
                } else {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#warning_ina_' + jenis).show().fadeOut(5000);
                    $('#msg_ina_' + jenis).text(res.msg);
                    $('#modal-ina-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#save_ina_' + jenis).show();
        $('#load_ina_' + jenis).hide();
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function listRekonsiliasiObat() {
    var table = "";
    RekonsiliasiObatAction.getListDetail(idDetailCheckup, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                var ya = "";
                var tidak = "";

                if (item.diteruskan != null) {
                    if (item.diteruskan == "Ya") {
                        ya = '<i class="fa fa-check" style="color: #449d44"></i>';
                    } else {
                        tidak = '<i class="fa fa-check" style="color: #449d44"></i>';
                    }
                }

                table += '<tr>' +
                    '<td>' + formaterDate(item.tanggal) + '</td>' +
                    '<td>' + cekNull(item.ruangan) + '</td>' +
                    '<td>' + cekNull(item.namaObat) + '</td>' +
                    '<td>' + cekNull(item.dosis) + '</td>' +
                    '<td>' + cekNull(item.aturanPakai) + '</td>' +
                    '<td>' + cekNull(item.indikasi) + '</td>' +
                    '<td align="center">' + ya + '</td>' +
                    '<td align="center">' + tidak + '</td>' +
                    '<td align="center">' + '<img style="width: 30px; height: 20px" src="' + item.ttdPasien + '">' + '</td>' +
                    '<td align="center">' + '<img style="width: 30px; height: 20px" src="' + item.ttdApoteker + '">' + '</td>' +
                    '</tr>';
            });
            $('#body_rekonsiliasi').html(table);
        }
    });
}

function saveRekamMedisRJ(jenis, ket) {
    var data = "";
    var va1 = $('#rj1').val();
    var va2 = $('#rj2').val();
    var va3 = $('#rj3').val();
    var va4 = $('#rj4').val();
    var va5 = $('#rj5').val();
    var va6 = $('#rj6').val();

    if(va1 && va2 && va3 && va4 && va5 && va6 != ''){
        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1.split("-").reverse().join("-")+ ' ' + va2+':00',
            'anamnese': va3,
            'pemeriksaan_fisik': va4,
            'diagnosa': va5,
            'planing': va6,
            'keterangan': jenis
        }

        var result = JSON.stringify(data);

        $('#save_ina_' + jenis).hide();
        $('#load_ina_' + jenis).show();
        dwr.engine.setAsync(true);
        RekamMedisRawatJalanAction.saveRekamMedisRJ(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#modal-ina-' + jenis).modal('hide');
                    $('#warning_ina_' + ket).show().fadeOut(5000);
                    $('#msg_ina_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-ina-' + jenis).scrollTop(0);
                    listRekamMedisRJ();
                } else {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#warning_ina_' + jenis).show().fadeOut(5000);
                    $('#msg_ina_' + jenis).text(res.msg);
                    $('#modal-ina-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#save_ina_' + jenis).show();
        $('#load_ina_' + jenis).hide();
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function listRekamMedisRJ() {
    var table = "";
    RekamMedisRawatJalanAction.getListRekamMedisRJ(idDetailCheckup, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                table += '<tr>' +
                    '<td>' + formaterDateTime(item.waktu) + '</td>' +
                    '<td>' + cekNull(item.anamnese) + '</td>' +
                    '<td>' + cekNull(item.pemeriksaanFisik) + '</td>' +
                    '<td>' + cekNull(item.diagnosa) + '</td>' +
                    '<td>' + cekNull(item.planing) + '</td>' +
                    '</tr>';
            });
            $('#body_ringkasan_rj').html(table);
        }
    });
}

function convertCek(item) {
    var result = ""
    if (item != null) {
        if (item == "Ya") {
            result = '<i style="color: #5cb85c" class="fa fa-check"></i>'
        } else {
            result = item;
        }
    }
    return result;
}

function cekNull(item) {
    var res = "";
    if (item != null) {
        res = item;
    }
    return res;
}

function tambahNama() {

    var cek = $('.tercamtum').length;
    var index = parseInt(cek) + 1;
    var id = 'tercamtum-' + index;
    var className1 = 'nama-tcm';
    var className2 = 'alamat-tcm';

    var kolom = '<div class="form-group tercamtum" id="' + id + '">' +
        '<div class="col-md-5">' +
        '<input style="margin-top: 7px" class="form-control ' + className1 + '" placeholder="nama">' +
        '</div>' +
        '<div class="col-md-5">' +
        '<input style="margin-top: 7px" class="form-control ' + className2 + '" placeholder="Alamat">' +
        '</div>' +
        '<div class="col-md-1">' +
        '<button onclick="removeTambahNama(\'' + id + '\')" style="margin-top: 7px" class="btn btn-danger"><i class="fa fa-trash"></i></button>' +
        '</div>' +
        '</div>';

    $('#temp_nama').append(kolom);
}

function removeTambahNama(id) {
    $('#' + id).remove();
}