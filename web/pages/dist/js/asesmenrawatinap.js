function showModalAsesmenRawatInap(jenis) {
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
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

    if ("ringkasan_rj" == jenis) {
        listRekamMedisRJ();
    }

    if("asuhan_keperawatan" == jenis){
        $('#diagnosa_askep').val('');
        $('#dia').html('');
        $('#has').html('');
        $('#pla').html('');
        $('#imp').html('');
        $('#eva').html('');
    }
    setDataPasien();
    radioEdukasiPasien(jenis);
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
                    if (tr != '') {
                        tr = tr + '=' + item.value + '|' + alamatTcm[i].value;
                    } else {
                        tr = item.value + '|' + alamatTcm[i].value;
                    }
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

    if ("data_ruangan" == jenis) {
        var va1 = $('#dr1').val();
        var va2 = $('#dr2').val();
        var va3 = $('#dr3').val();
        var va4 = $('#dr4').val();
        var va5 = $('#dr5').val();
        var va6 = $('#dr6').val();
        var va7 = $('[name=dr7]');
        var temp7 = "";
        $.each(va7, function (i, item) {
            if (item.checked) {
                if (temp7 != '') {
                    temp7 = temp7 + '|' + item.value;
                } else {
                    temp7 = item.value;
                }
            }
        });
        var va8 = $('[name=dr8]');
        var temp8 = "";
        $.each(va8, function (i, item) {
            if (item.checked) {
                if (temp8 != '') {
                    temp8 = temp8 + '|' + item.value;
                } else {
                    temp8 = item.value;
                }
            }
        });
        var va9 = $('#dr9').val();
        var va10 = $('#dr10').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 && temp7 && temp8 && va9 && va10 != '') {
            data.push({
                'parameter': 'Ruang Asal',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ruang Tujuan',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Petugas yang menghubungi',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Petugas penerima telp',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berangkat Tanggal dan Jam',
                'jawaban': va5 + ' ' + va6,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alasan Mentransfer',
                'jawaban': temp7,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Derajat Transfer',
                'jawaban': temp8,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Medis',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter /petugas yang menunjuk',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("catatan_klinis" == jenis) {
        var va1 = $('#ck1').val();
        var va2 = $('[name=ck2]:checked').val();
        var va3 = "";
        var v3 = $('[name=ck3]:checked').val();
        if (v3 != undefined) {
            var a = $('[name=ket_ck3]');
            if (v3 == "Ada") {
                $.each(a, function (i, item) {
                    if (item.checked) {
                        if (va3 != '') {
                            va3 = va3 + '|' + item.value;
                        } else {
                            va3 = item.value;
                        }
                    }
                });
            } else {
                va3 = v3;
            }
        }
        var va4 = $('#ck4').val();
        var va5 = $('#ck5').val();
        var va6 = $('#ck6').val();
        var va7 = $('[name=ck7]');
        var temp7 = "";
        $.each(va7, function (i, item) {
            if (item.checked) {
                if (temp7 != '') {
                    temp7 = temp7 + '|' + item.value;
                } else {
                    temp7 = item.value;
                }
            }
        });
        var va8 = $('#ck8').val();

        if (va2 != undefined && va1 && va3 && va4 && va5 && va6 && temp7 && va8 != '') {
            data.push({
                'parameter': 'Anamnesis',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alergi',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });

            var tindakan = "";
            var tin = $('.tin-tindakan');
            $.each(tin, function (i, item) {
                if (item.value != '') {
                    if (tindakan != '') {
                        tindakan = tindakan + '|' + item.value;
                    } else {
                        tindakan = item.value;
                    }
                }
            });

            if (tindakan != '') {
                data.push({
                    'parameter': 'Pengobatan yang sudah diberikan/ tindakan yang sudah dilakukan',
                    'jawaban': tindakan,
                    'keterangan': jenis,
                    'jenis': 'transfer_pasien',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            data.push({
                'parameter': 'Luka',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Makan / Minum terakhir jam',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokumen yang disertakan',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Peralatan medis yang digunakan',
                'jawaban': temp7,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Catatan Hal penting',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("kondisi_serah_terima" == jenis) {
        var va1 = $('#kps1').val();
        var va2 = $('#kps2').val();
        var va3 = $('#kps3').val();
        var va4 = $('#kps4').val();
        var va5 = $('#kps5').val();
        var va6 = $('#kps6').val();
        var va7 = $('#kps7').val();
        var va8 = $('#kps8').val();
        var va9 = $('#kps9').val();
        var va10 = $('#kps10').val();
        var va11 = $('#kps11').val();
        var va12 = $('#kps12').val();
        var va13 = $('#kps13').val();
        var va14 = $('#kps14').val();
        var va15 = $('#kps15').val();
        var va16 = $('#kps16').val();
        var va17 = $('#kps17').val();
        var va18 = $('#kps18').val();
        var va19 = $('#kps19').val();
        var va20 = $('#kps20').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va11 && va12 &&
            va13 && va14 && va15 && va16 && va17 && va18 != '') {
            data.push({
                'parameter': 'Kesadaran Umum',
                'jawaban': va1 + '|' + va2,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesadaran / GCS',
                'jawaban': va3 + '|' + va4,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban': va5 + ' mmHg|' + va6 + ' mmHg',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': va7 + ' C|' + va8 + ' C',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': va9 + ' x/mnt|' + va10 + ' x/mnt',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'RR',
                'jawaban': va11 + ' x/mnt|' + va12 + ' x/mnt',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saturasi',
                'jawaban': va13 + ' %|' + va14 + ' %',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skala Nyeri',
                'jawaban': va15 + '|' + va16,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Resiko Jatuh',
                'jawaban': va17 + '|' + va18,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });

            if (va19 && va20 != '') {
                data.push({
                    'parameter': 'Lain-Lain',
                    'jawaban': va19 + '|' + va20,
                    'keterangan': jenis,
                    'jenis': 'transfer_pasien',
                    'id_detail_checkup': idDetailCheckup
                });
            }

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
                            '<td width="40%">' + item.parameter + '</td>' +
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
                                var tabeleTcm = '<label>Dengan ini menyatakan untuk tidak memperbolehkan nama yang tercantum dibawah ini mempunyai akan terhadap pasien tersebut dengan cara apapun.</label>' +
                                    '<table class="table table-bordered table-striped" style="font-size: 12px">' +
                                    '<thead>' +
                                    '<td>Nama</td>' +
                                    '<td>Alamat</td>' +
                                    '</thead>' +
                                    '<tbody>' + temp1 + '</tbody>' +
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
                    } else if ("data_ruangan" == item.keterangan) {
                        if ("Alasan Mentransfer" == item.parameter || "Derajat Transfer" == item.parameter) {
                            var isi = jwb.split("|");
                            var li = "";
                            $.each(isi, function (i, item) {
                                li += '<li>' + item + '</li>';
                            });
                            if (li != '') {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<ul style="margin-left: 15px">' + li + '</ul>' + '</td>' +
                                    '</tr>';
                            }
                        } else {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + jwb + '</td>' +
                                '</tr>';
                        }

                    } else if ("catatan_klinis" == item.keterangan) {
                        if ("Riwayat Penyakit" == item.parameter || "Peralatan medis yang digunakan" == item.parameter) {
                            var isi = jwb.split("|");
                            var li = "";
                            $.each(isi, function (i, item) {
                                li += '<li>' + item + '</li>';
                            });
                            if (li != '') {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<ul style="margin-left: 15px">' + li + '</ul>' + '</td>' +
                                    '</tr>';
                            }
                        } else if ("Pengobatan yang sudah diberikan/ tindakan yang sudah dilakukan" == item.parameter) {
                            var isi = jwb.split("|");
                            var li = "";
                            $.each(isi, function (i, item) {
                                li += '<li>' + item + '</li>';
                            });
                            if (li != '') {
                                body += '<tr>' +
                                    '<td colspan="2">' + '<label>' + item.parameter + '</label><ul style="margin-left: 15px">' + li + '</ul>' + '</td>' +
                                    '</tr>';
                            }
                        } else {
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + jwb + '</td>' +
                                '</tr>';
                        }
                    } else if ("kondisi_serah_terima" == item.keterangan) {
                        var isi = jwb.split("|");
                        var sebelum = isi[0];
                        var sesudah = isi[1];
                        if (li != '') {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + sebelum + '</td>' +
                                '<td>' + sesudah + '</td>' +
                                '</tr>';
                        }
                    } else {
                        body += '<tr>' +
                            '<td width="40%">' + item.parameter + '</td>' +
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

            if ("kondisi_serah_terima" == jenis) {
                if (cekData) {
                    head = '<tr>' +
                        '<td><b>Pemeriksaan</b></td>' +
                        '<td><b>Sebelum ditransfer</b></td>' +
                        '<td><b>Saat diterima</b></td>' +
                        '</tr>';
                }
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
                    '<td>' + converterDateTime(item.waktu) + '</td>' +
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

    var data =[];

    var diagnosis = $('[name=diag]');
    var hasil = $('[name=hasil]');
    var inter = $('[name=inter]');
    var imple = $('[name=imple]');
    var eva = $('[name=eva]');
    var ttdPerawat = document.getElementById("ttd_perawat");
    var tgl = $('.tgl').val();
    var jam = $('.jam').val();

    var tempDiag    = "";
    var tempHasil   = "";
    var tempInter   = "";
    var tempImple   = "";
    var tempEva     = "";

    $.each(diagnosis, function (i, item) {
        var val = "";
        if(item.type == 'checkbox'){
            if(item.checked){
                val = item.value;
            }
        }else{
            if(item.value != ''){
                val = item.value;
            }
        }

        if(val != ''){
            if(tempDiag != ''){
                tempDiag = tempDiag +'|'+val;
            }else{
                tempDiag = val;
            }
        }
    });

    $.each(hasil, function (i, item) {
        var val = "";
        if(item.type == 'checkbox'){
            if(item.checked){
                val = item.value;
            }
        }else{
            if(item.value != ''){
                val = item.value;
            }
        }

        if(val != ''){
            if(tempHasil != ''){
                tempHasil = tempHasil +'|'+val;
            }else{
                tempHasil = val;
            }
        }
    });

    $.each(inter, function (i, item) {
        var val = "";
        if(item.type == 'checkbox'){
            if(item.checked){
                val = item.value;
            }
        }else{
            if(item.value != ''){
                val = item.value;
            }
        }

        if(val != ''){
            if(tempInter != ''){
                tempInter = tempInter +'|'+val;
            }else{
                tempInter = val;
            }
        }
    });

    $.each(imple, function (i, item) {
        var val = "";
        if(item.type == 'checkbox'){
            if(item.checked){
                val = item.value;
            }
        }else{
            if(item.value != ''){
                val = item.value;
            }
        }

        if(val != ''){
            if(tempImple != ''){
                tempImple = tempImple +'|'+val;
            }else{
                tempImple = val;
            }
        }
    });

    $.each(eva, function (i, item) {
        var val = "";
        if(item.type == 'checkbox'){
            if(item.checked){
                val = item.value;
            }
        }else{
            if(item.value != ''){
                val = item.value;
            }
        }

        if(val != ''){
            if(tempEva != ''){
                tempEva = tempEva +'|'+val;
            }else{
                tempEva = val;
            }
        }
    });

    var cekTtd = isCanvasBlank(ttdPerawat);

    if (tgl && jam && tempDiag && tempHasil && tempInter && tempImple && tempEva != '' && !cekTtd) {

        var ttd = ttdPerawat.toDataURL("image/png"),
            ttd = ttd.replace(/^data:image\/(png|jpg);base64,/, "");

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': tgl+' '+jam,
            'diagnosa': tempDiag,
            'hasil': tempHasil,
            'intervensi': tempInter,
            'implementasi': tempImple,
            'evaluasi': tempEva,
            'keterangan': jenis,
            'ttd_perawat': ttd
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
                var hasil = "";
                var intervensi = "";
                var implementasi = "";
                var evaluasi = "";

                var ul1 = "";
                var ul2 = "";
                var ul3 = "";
                var ul4 = "";
                var ul5 = "";

                if (item.diagnosa != null) {
                    var v = item.diagnosa.split("|");
                    $.each(v, function (i, item) {
                        diagnosa += '<li>' + item + '</li>';
                    });
                }

                if (item.hasil != null) {
                    var v = item.hasil.split("|");
                    $.each(v, function (i, item) {
                        hasil += '<li>' + item + '</li>';
                    });
                }

                if (item.intervensi != null) {
                    var v = item.intervensi.split("|");
                    $.each(v, function (i, item) {
                        intervensi += '<li>' + item + '</li>';
                    });
                }

                if (item.implementasi != null) {
                    var v = item.implementasi.split("|");
                    $.each(v, function (i, item) {
                        implementasi += '<li>' + item + '</li>';
                    });
                }

                if (item.evaluasi != null) {
                    var v = item.evaluasi.split("|");
                    $.each(v, function (i, item) {
                        evaluasi += '<li>' + item + '</li>';
                    });
                }

                if(item.ttdPerawat != null){
                    evaluasi += '<li style="list-style-type: none; margin-top: 10px">' + '<label>Perawat</label><img style="width: 50px; height: 20px" src="' + item.ttdPerawat + '">' + '</li>';
                }

                if (diagnosa != '') {
                    ul1 = '<ul style="margin-left: 12px">' + diagnosa + '</ul>'
                }
                if (hasil != '') {
                    ul2 = '<ul style="margin-left: 12px">' + hasil + '</ul>'
                }
                if (intervensi != '') {
                    ul3 = '<ul style="margin-left: 12px">' + intervensi + '</ul>'
                }
                if (implementasi != '') {
                    ul4 = '<ul style="margin-left: 12px">' + implementasi + '</ul>'
                }
                if (evaluasi != '') {
                    ul5 = '<ul style="margin-left: 12px">' + evaluasi + '</ul>'
                }

                table += '<tr>' +
                    '<td>' + item.waktu + '</td>' +
                    '<td>' + ul1 + '</td>' +
                    '<td>' + ul2 + '</td>' +
                    '<td>' + ul3 + '</td>' +
                    '<td>' + ul4 + '</td>' +
                    '<td>' + ul5 + '</td>' +
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

function saveEdukasiPasien(jenis, ket) {
    var data = [];
    var cek = false;
    var tgl = $('.tgl').val();
    var jam = $('.jam').val();
    var ttd1 = document.getElementById("ttd_pasien_"+jenis);
    var ttd2 = document.getElementById("ttd_staf_"+jenis);
    var cek1 = isCanvasBlank(ttd1);
    var cek2 = isCanvasBlank(ttd2);

    var canv1 = ttd1.toDataURL("image/png"),
        canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

    var canv2 = ttd2.toDataURL("image/png"),
        canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");

    var pertanyaan = $('#p_'+jenis).val();
    var jawaban = $('#j_'+jenis).val();

    var table = $('#table_'+jenis).tableToJSON();

    $.each(table, function (i, item) {

        var dw  = $('[name=dw_'+jenis+i+']:checked').val();
        var pa  = $('[name=pa_'+jenis+i+']:checked').val();
        var me  = $('[name=me_'+jenis+i+']:checked').val();
        var med = $('[name=med_'+jenis+i+']:checked').val();
        var ev  = $('[name=ev_'+jenis+i+']:checked').val();
        var edu = $('#edu'+jenis+i).val();
        var edukator = "";

        if(tgl && jam != ''){
            edukator = tgl+' '+jam;
        }

        if(i == 0){
            if(dw && pa && me && med && ev && edu != undefined && edu && tgl && jam != '' ){
                data.push({
                    'edukator': edukator,
                    'durasi': dw+' menit',
                    'edukasi': edu,
                    'pemahaman_awal': pa,
                    'metode_edukasi': me,
                    'media_edukasi': med,
                    'evaluasi': ev,
                    'ttd_pasien': canv1,
                    'ttd_staf': canv2,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
            }else{
                cek = false;
            }
        }else{
            if(dw && pa && me && med && ev && edu != undefined && edu != ''){
                data.push({
                    'durasi': dw+' menit',
                    'edukasi': edu,
                    'pemahaman_awal': pa,
                    'metode_edukasi': me,
                    'media_edukasi': med,
                    'evaluasi': ev,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }
    });

    if (cek) {

        if(pertanyaan && jawaban != ''){
            data.push({
                'edukasi': pertanyaan,
                'pemahaman_awal': jawaban,
                'keterangan': jenis,
                'tipe': 'Q',
                'id_detail_checkup': idDetailCheckup
            });
        }

        var result = JSON.stringify(data);
        $('#save_ina_' + jenis).hide();
        $('#load_ina_' + jenis).show();
        dwr.engine.setAsync(true);
        EdukasiPasienAction.save(result, {
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
        });
    } else {
        $('#save_ina_' + jenis).show();
        $('#load_ina_' + jenis).hide();
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function listEdukasiPasien(jenis) {
    console.log(jenis);
    var head = "";
    var first = "";
    var body = "";
    var last = "";
    EdukasiPasienAction.getListDetail(idDetailCheckup, jenis,  function (res) {
        if (res.length > 0) {
            var rowSpan = res.length;
            $.each(res, function (i, item) {
                var edu = "";
                var ttdPasien = "";
                var ttdStaf = "";
                if(item.ttdPasien != null && item.ttdPasien != ''){
                    ttdPasien = '<img src="'+item.ttdPasien+'" style="width: 50px">';
                }
                if(item.ttdStaf != null && item.ttdStaf != ''){
                    ttdStaf = '<img src="'+item.ttdStaf+'" style="width: 50px">';
                }

                if("Q" == item.tipe){
                    body += '<tr>' + '<td colspan="9">' + '<p>'+cekNull(item.edukasi)+'</p><p>'+cekNull(item.pemahamanAwal)+'</p>' + '</td>' +
                        '</tr>';
                }else{
                    body += '<tr>' + '<td width="14%">' + cekNull(item.edukator) + '</td>' +
                        '<td width="8%" >' + cekNull(item.durasi) + '</td>' +
                        '<td width="20%">' + cekNull(item.edukasi) + '</td>' +
                        '<td>' + cekNull(item.pemahamanAwal) + '</td>' +
                        '<td>' + cekNull(item.metodeEdukasi) + '</td>' +
                        '<td>' + cekNull(item.mediaEdukasi) + '</td>' +
                        '<td>' + cekNull(item.evaluasi) + '</td>' +
                        '<td>' + ttdPasien + '</td>' +
                        '<td>' + ttdStaf + '</td>' +
                        '</tr>';
                }
            });
            first =
                '<tr style="font-weight: bold">' +
                '<td align="center" style="vertical-align: middle">Tanggal/Jam</td>'+
                '<td align="center" style="vertical-align: middle">Durasi</td>'+
                '<td align="center" style="vertical-align: middle" >Pelaksana Edukasi</td>'+
                '<td align="center" style="vertical-align: middle">Pemahaman Awal</td>'+
                '<td align="center" style="vertical-align: middle">Metode Edukasi</td>'+
                '<td align="center" style="vertical-align: middle">Media Edukasi</td>'+
                '<td align="center" style="vertical-align: middle">Evaluasi/ Verifikasi</td>'+
                '<td align="center" style="vertical-align: middle">TTD Pasien</td>'+
                '<td align="center" style="vertical-align: middle">TTD Staf</td>'+
                '</tr>';
        }else{
            body = '<tr><td>Data belum ada</td></tr>';
        }

        var table = '<table style="font-size: 12px" class="table table-bordered">' +
            '<thead>' + head + '</thead>' +
            '<tbody>' + first + body + last + '</tbody>' +
            '</table>';

        var newRow = $('<tr id="del_ina_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
        newRow.insertAfter($('table').find('#row_ina_' + jenis));
        var url = contextPath + '/pages/images/minus-allnew.png';
        $('#btn_ina_' + jenis).attr('src', url);
        $('#btn_ina_' + jenis).attr('onclick', 'delRowlistEdukasiPasien(\'' + jenis + '\')');
    });
}

function delRowlistEdukasiPasien(id) {
    $('#del_ina_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_ina_' + id).attr('src', url);
    $('#btn_ina_' + id).attr('onclick', 'listEdukasiPasien(\'' + id + '\')');
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

function tambahTindakan() {
    var cek = $('.form-tindakan').length;
    var index = parseInt(cek) + 1;
    var id = 'tindakan-' + index;
    var kolom = '<div class="form-group form-tindakan" id="' + id + '">' +
        '<div class="col-md-11">' +
        '<input placeholder="Keterangan Tindakan" class="form-control tin-tindakan" style="margin-top: 7px">' +
        '</div>' +
        '<div class="col-md-1">' +
        '<button onclick="removeTambahNama(\'' + id + '\')" style="margin-top: 9px; margin-left: -20px" class="btn btn-danger"><i class="fa fa-trash"></i></button>' +
        '</div>' +
        '</div>';
    $('#temp_tindakan').append(kolom);
}

function radioEdukasiPasien(jenis){
    var data = [];
    var edukasi = [];
    var body = "";
    var head = '<tr style="font-weight: bold">\n' +
        '<td colspan="3" align="center" style="vertical-align: middle">Durasi Waktu (menit)</td>\n' +
        '<td rowspan="2" align="center" style="vertical-align: middle" width="30%">Pelaksanaan Edukasi</td>\n' +
        '<td colspan="3" align="center" style="vertical-align: middle">Pemahaman Awal</td>\n' +
        '<td colspan="4" align="center" style="vertical-align: middle">Metode Edukasi</td>\n' +
        '<td colspan="4" align="center" style="vertical-align: middle">Media Edukasi</td>\n' +
        '<td colspan="3" align="center" style="vertical-align: middle">Evaluasi/Verifikasi</td>\n' +
        '</tr>\n' +
        '<tr style="font-weight: bold">\n' +
        '<td align="center" title="3 menit">3</td>\n' +
        '<td align="center">5</td>\n' +
        '<td align="center">10</td>\n' +
        '<td align="center">B</td>\n' +
        '<td align="center">C</td>\n' +
        '<td align="center">K</td>\n' +
        '<td align="center">1</td>\n' +
        '<td align="center">2</td>\n' +
        '<td align="center">3</td>\n' +
        '<td align="center">4</td>\n' +
        '<td align="center">1</td>\n' +
        '<td align="center">2</td>\n' +
        '<td align="center">3</td>\n' +
        '<td align="center">4</td>\n' +
        '<td align="center">1</td>\n' +
        '<td align="center">2</td>\n' +
        '<td align="center">3</td>\n' +
        '</tr>';

    var question = '<div class="row">\n' +
        '<div class="form-group">\n' +
        '    <label class="col-md-2">Tanggal</label>\n' +
        '    <div class="col-md-3">\n' +
        '        <div class="input-group">\n' +
        '            <div class="input-group-addon">\n' +
        '                <i class="fa fa-calendar"></i>\n' +
        '            </div>\n' +
        '            <input class="form-control tgl">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <label class="col-md-1">Jam</label>\n' +
        '    <div class="col-md-3">\n' +
        '        <div class="input-group">\n' +
        '            <div class="input-group-addon">\n' +
        '                <i class="fa fa-clock-o"></i>\n' +
        '            </div>\n' +
        '            <input class="form-control jam">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>\n' +
        '</div>' +
        '<div class="row jarak">\n' +
        '<div class="form-group">\n' +
        '    <label class="col-md-2">Pertanyaan</label>\n' +
        '    <div class="col-md-10"><input class="form-control" id="p_'+jenis+'"></div>\n' +
        '</div>\n' +
        '</div>\n' +
        '<div class="row jarak">\n' +
        '<div class="form-group">\n' +
        '    <label class="col-md-2">Pasien/Keluarga</label>\n' +
        '    <div class="col-md-10"><input class="form-control" id="j_'+jenis+'"></div>\n' +
        '</div>\n' +
        '</div>';

    var ttd = '<div class="row">\n' +
        '    <div class="col-md-3">\n' +
        '        <div class="form-group">\n' +
        '            <label style="margin-left: 8px">TTD Pasien</label>\n' +
        '            <canvas class="paint-canvas-ttd" id="ttd_pasien_'+jenis+'" width="190"\n' +
        '               onmouseover="paintTtd(\'ttd_pasien_'+jenis+'\')"></canvas>\n' +
        '            <button style="margin-left: 8px" type="button" class="btn btn-danger"\n' +
        '                   onclick="removePaint(\'ttd_pasien_'+jenis+'\')"><i class="fa fa-trash"></i>\n' +
        '                Clear\n' +
        '            </button>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <div class="col-md-3">\n' +
        '        <div class="form-group">\n' +
        '            <label style="margin-left: 8px">TTD Staf</label>\n' +
        '            <canvas class="paint-canvas-ttd" id="ttd_staf_'+jenis+'" width="190"\n' +
        '               onmouseover="paintTtd(\'ttd_staf_'+jenis+'\')"></canvas>\n' +
        '            <button style="margin-left: 8px" type="button" class="btn btn-danger"\n' +
        '                   onclick="removePaint(\'ttd_staf_'+jenis+'\')"><i class="fa fa-trash"></i>\n' +
        '                Clear\n' +
        '            </button>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <div class="col-md-6">\n' +
        '        <div class="row" style="margin-left: 5px">\n' +
        '            <label class="col-md-12"><b>Keterangan</b></label>\n' +
        '        </div>\n' +
        '        <table class="table" style="font-size: 12px; margin-left: 10px">\n' +
        '            <tbody>\n' +
        '            <tr>\n' +
        '                <td>Tingkat Pemahaman</td>\n' +
        '                <td>Baik (B), Cukup (C), Kurang (K)</td>\n' +
        '            </tr>\n' +
        '            <tr>\n' +
        '                <td>Metode Edukasi</td>\n' +
        '                <td>Wawancara (1), Diskusi (2), Ceramah (3), Demonstrasi (4)</td>\n' +
        '            </tr>\n' +
        '            <tr>\n' +
        '                <td>Media Edukasi</td>\n' +
        '                <td>Lisan (1), Leaflet (2), Brosur (3), Lembar Balik (4)</td>\n' +
        '            </tr>\n' +
        '            <tr>\n' +
        '                <td>Evaluasi/Verifikasi</td>\n' +
        '                <td>Mengerti (1), Re Edukasi (2), Re Demontrasi (3)</td>\n' +
        '            </tr>\n' +
        '            </tbody>\n' +
        '        </table>\n' +
        '    </div>\n' +
        '</div>';

    if("ept_tppri" == jenis){
        edukasi.push({'edukasi': 'Hak dan Kewajiban Pasien dan Keluarga'});
        edukasi.push({'edukasi': 'Peraturan dan Tataterbit RS'});
        edukasi.push({'edukasi': 'Fasilitas kamar, jam kunjungan dan visite Pasien'});
    }
    if("ept_perawat" == jenis){
        edukasi.push({'edukasi': 'Manajemen Nyeri, Hand Hygiene, Informasi Fungsi Identitas Pasien'});
        edukasi.push({'edukasi': 'Pemberi informasi pasang (infus, cateter, NGT), pencegahan resiko jatuh'});
        edukasi.push({'edukasi': 'Fasilitas kamar, Jam kunjung dan visite Pasien'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
    }
    if("ept_dpjp" == jenis){
        edukasi.push({'edukasi': 'plc|Nama Dpjp'});
        edukasi.push({'edukasi': 'plc|Diagnosa Penyakit'});
        edukasi.push({'edukasi': 'plc|Dasar Diagnosa'});
        edukasi.push({'edukasi': 'plc|Kondisi Pasien'});
        edukasi.push({'edukasi': 'Penatalaksaan penyakit dan prosedur'});
        edukasi.push({'edukasi': 'Tata cara dan tujuan tindakan'});
        edukasi.push({'edukasi': 'Manfaat dan resiko tindakan'});
        edukasi.push({'edukasi': 'Kemungkinan alternatif tindakan'});
        edukasi.push({'edukasi': 'Prognosis dan tindakan'});
        edukasi.push({'edukasi': 'plc|Kemungkinan Hasil yang tidak terduga'});
        edukasi.push({'edukasi': 'Pemeriksaan yang ada dilakukan'});
        edukasi.push({'edukasi': 'Menajemen Nyeri'});
        edukasi.push({'edukasi': 'Penggunaan Peralatan Medis yang efektif dan aman'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});

    }
    if("ept_nutrisionis" == jenis){
        edukasi.push({'edukasi': 'plc|Program diet dan nutrisi'});
        edukasi.push({'edukasi': 'Pengelolaan makanan dari luar RS'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
    }
    if("ept_farmasi" == jenis){
        edukasi.push({'edukasi': 'Aturan, tata cara pemakain obat yang efektif dan aman'});
        edukasi.push({'edukasi': 'Potensi efek samping obat yang diberikan'});
        edukasi.push({'edukasi': 'Potensi interaksi obat dengan obat lain dan atau dengan makanan'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
    }
    if("ept_rehabilitasi_medis" == jenis){
        edukasi.push({'edukasi': 'Program dan teknik fisioterapi'});
        edukasi.push({'edukasi': 'Penerapan dan keamanan alat terapi'});
        edukasi.push({'edukasi': 'Terapi akupasi'});
        edukasi.push({'edukasi': 'Terapi wicara'});
        edukasi.push({'edukasi': 'Latihan menelan'});
        edukasi.push({'edukasi': 'Pemeliharaan, pemakaian dan kecemasan orthose protesa'});
    }
    if("ept_dokter" == jenis){
        edukasi.push({'edukasi': 'plc|Rujukan Pasien'});
        edukasi.push({'edukasi': 'Pasien tidak bisa dirujuk'});
        edukasi.push({'edukasi': 'Pasien tidak transportable'});
        edukasi.push({'edukasi': 'RS menerima rujukan tidak tersedia (kamar penuh, DLL)'});
        edukasi.push({'edukasi': 'Keluarga menolak'});
        edukasi.push({'edukasi': 'Alternatif Layanan'});
        edukasi.push({'edukasi': 'Pasien tetap dirawat di ICU'});
        edukasi.push({'edukasi': 'Pasien Dirawat diruang rawat inap'});
        edukasi.push({'edukasi': 'Pasien di bawa pulang'});
    }
    if("ept_radiografer" == jenis){
        edukasi.push({'edukasi': 'Pemeriksaan Radiologi'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': 'Penerimaan dosis radiasi'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': 'Manfaat dan resiko'});
    }
    if("ept_ppa" == jenis){
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
    }

    if(edukasi.length > 0){
        $.each(edukasi, function (i, item) {
            data.push({
                'durasi': '3|5|10',
                'edukasi': item.edukasi,
                'pemahaman':'Baik|Cukup|Kurang',
                'metode':'Wawancara|Diskusi|Ceramah|Demonstrasi',
                'media':'Lisan|Leaflet|Brosur|Lembar Balik',
                'evaluasi':'Mengerti|Re Edukasi|Re Demontrasi'
            });
        });
    }

    if(data.length > 0){
        $.each(data, function (i, item) {
            var durasi = item.durasi.split("|");
            var pemahaman = item.pemahaman.split("|");
            var metode = item.metode.split("|");
            var media = item.media.split("|");
            var evaluasi = item.evaluasi.split("|");
            var edukas = item.edukasi.split("|");

            var tdDurasi = "";
            var tdEdukasi = "";
            var tdPemahaman = "";
            var tdMetode = "";
            var tdMedia = "";
            var tdEvaluasi = "";

            var idEdu = 'edu'+jenis+i;

            if(edukas.length > 1){
                var edu1 = edukas[1];
                tdEdukasi += '<td><input style="font-size: 12px" class="form-control" id="edu'+jenis+i+'" placeholder="'+edu1+'" onchange="$(\'#'+idEdu+'\').val(\''+edu1+ '\'+\''+" "+'\'+this.value)"></td>';
            }else{
                if(item.edukasi != ''){
                    tdEdukasi += '<td><input type="hidden" id="edu'+jenis+i+'" value="'+item.edukasi+'">'+item.edukasi+'</td>';
                }else{
                    tdEdukasi += '<td><input style="font-size: 12px" class="form-control" id="edu'+jenis+i+'"></td>';
                }
            }

            $.each(durasi, function (idx, item) {
                tdDurasi += '<td align="center" style="vertical-align: middle"><div class="custom03">'+
                            '<input type="radio" value="'+item+'" id="dw_'+jenis+i+idx+'" name="dw_'+jenis+i+'" /><label for="dw_'+jenis+i+idx+'"></label>'+
                            '</div></td>';
            });
            $.each(pemahaman, function (idx, item) {
                tdPemahaman += '<td align="center" style="vertical-align: middle"><div class="custom03">'+
                    '<input type="radio" value="'+item+'" id="pa_'+jenis+i+idx+'" name="pa_'+jenis+i+'" /><label for="pa_'+jenis+i+idx+'"></label>'+
                    '</div></td>';
            });
            $.each(metode, function (idx, item) {
                tdMetode += '<td align="center" style="vertical-align: middle"><div class="custom03">'+
                    '<input type="radio" value="'+item+'" id="me_'+jenis+i+idx+'" name="me_'+jenis+i+'" /><label for="me_'+jenis+i+idx+'"></label>'+
                    '</div></td>';
            });
            $.each(media, function (idx, item) {
                tdMedia += '<td align="center" style="vertical-align: middle"><div class="custom03">'+
                    '<input type="radio" value="'+item+'" id="med_'+jenis+i+idx+'" name="med_'+jenis+i+'" /><label for="med_'+jenis+i+idx+'"></label>'+
                    '</div></td>';
            });
            $.each(evaluasi, function (idx, item) {
                tdEvaluasi += '<td align="center" style="vertical-align: middle"><div class="custom03">'+
                    '<input type="radio" value="'+item+'" id="ev_'+jenis+i+idx+'" name="ev_'+jenis+i+'" /><label for="ev_'+jenis+i+idx+'"></label>'+
                    '</div></td>';
            });

            body += '<tr>' +
                tdDurasi +
                tdEdukasi +
                tdPemahaman +
                tdMetode +
                tdMedia +
                tdEvaluasi +
                '</tr>';
        });

        $('#q_'+jenis).html(question);
        $('#temp_'+jenis).html(ttd);
        $('#head_'+jenis).html(head);
        $('#body_'+jenis).html(body);

        $('.jam').timepicker();
        $('.jam').val(converterTime(new Date()));
        $('.tgl').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tgl').val(converterDate(new Date()));
        $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
    }
}

function searchDiagnosaAskep(id){
    var menus, mapped;
    $('#'+id).typeahead({
        minLength: 3,
        source: function (query, process) {
            menus = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            RencanaAsuhanKeperawatanAction.getDiagnosaAskep(query, function (listdata) {
                data = listdata;
            });

            $.each(data, function (i, item) {
                var labelItem = item.diagnosa;
                mapped[labelItem] = {
                    id: item.idDiagnosaAsuhanKeperawatan,
                    diagnosa: item.diagnosa
                };
                menus.push(labelItem);
            });

            process(menus);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            RencanaAsuhanKeperawatanAction.getListDetailDiagnosaAskep(selectedObj.id, function (res) {
                if(res.length > 0){

                    var diagnosis = "";
                    var hasil = "";
                    var intervensi = "";
                    var implementasi = "";
                    var evaluasi = "";

                    $.each(res, function (i, item) {
                        if(item.diagnosis != null && item.diagnosis != ''){
                            if(item.keteranganDiagnosis == "P"){
                                diagnosis += '<p class="jarak">'+item.diagnosis+'</p><input type="hidden" value="'+item.diagnosis+'" name="diag">';
                            }else if(item.keteranganDiagnosis == "I") {
                                diagnosis += '<input style="font-size: 12px" class="form-control jarak" name="diag" id="diag'+i+'" placeholder="'+item.diagnosis+'" onchange="$(\'#diag'+i+'\').val(\''+item.diagnosis+ '\'+\''+" "+'\'+this.value)">';
                            }else{
                                diagnosis +=
                                    '<div class="row">' +
                                    '<div class="form-check02">\n' +
                                    '<input type="checkbox" name="diag" id="diag'+i+'" value="'+item.diagnosis+'">\n' +
                                    '<label for="diag'+i+'"></label> '+item.diagnosis+'\n' +
                                    '</div>' +
                                    '</div>';
                            }
                        }

                        if(item.hasil != null && item.hasil != ''){
                            if(item.keteranganHasil == "P"){
                                hasil += '<p class="jarak">'+item.hasil+'</p><input type="hidden" value="'+item.hasil+'" name="hasil">';
                            }else if(item.keteranganHasil == "I") {
                                hasil += '<input style="font-size: 12px" class="form-control jarak" name="hasil" id="hasil'+i+'" placeholder="'+item.hasil+'" onchange="$(\'#hasil'+i+'\').val(\''+item.hasil+ '\'+\''+" "+'\'+this.value)">';
                            }else{
                                hasil +=
                                    '<div class="row">' +
                                    '<div class="form-check02">\n' +
                                    '<input type="checkbox" name="hasil" id="hasil'+i+'" value="'+item.hasil+'">\n' +
                                    '<label for="hasil'+i+'"></label> '+item.hasil+'\n' +
                                    '</div>' +
                                    '</div>';
                            }
                        }

                        if(item.intervensi != null && item.intervensi != ''){
                            if(item.keteranganIntervensi == "P"){
                                intervensi += '<p class="jarak">'+item.intervensi+'</p><input type="hidden" value="'+item.intervensi+'" name="inter">';
                            }else if(item.keteranganIntervensi == "I") {
                                intervensi += '<input style="font-size: 12px" class="form-control jarak" name="inter" id="inter'+i+'" placeholder="'+item.intervensi+'" onchange="$(\'#inter'+i+'\').val(\''+item.intervensi+ '\'+\''+" "+'\'+this.value)">';
                            }else{
                                intervensi +=
                                    '<div class="row">' +
                                    '<div class="form-check02">\n' +
                                    '<input type="checkbox" name="inter" id="inter'+i+'" value="'+item.intervensi+'">\n' +
                                    '<label for="inter'+i+'"></label> '+item.intervensi+'\n' +
                                    '</div>' +
                                    '</div>';
                            }
                        }

                        if(item.implementasi != null && item.implementasi != ''){
                            if(item.keteranganImplementasi == "P"){
                                implementasi += '<p class="jarak">'+item.implementasi+'</p><input type="hidden" value="'+item.implementasi+'" name="imple">';
                            }else if(item.keteranganImplementasi == "I") {
                                implementasi += '<input style="font-size: 12px" class="form-control jarak" name="imple" id="imple'+i+'" placeholder="'+item.implementasi+'" onchange="$(\'#imple'+i+'\').val(\''+item.implementasi+ '\'+\''+" "+'\'+this.value)">';
                            }else{
                                implementasi +=
                                    '<div class="row">' +
                                    '<div class="form-check02">\n' +
                                    '<input type="checkbox" name="imple" id="imple'+i+'" value="'+item.implementasi+'">\n' +
                                    '<label for="imple'+i+'"></label> '+item.implementasi+'\n' +
                                    '</div>' +
                                    '</div>';
                            }
                        }

                        if(item.evaluasi != null && item.evaluasi != ''){
                            if(item.keteranganEvaluasi == "P"){
                                evaluasi += '<p class="jarak">'+item.evaluasi+'</p><input type="hidden" value="'+item.evaluasi+'" name="eva">';
                            }else if(item.keteranganEvaluasi == "I") {
                                evaluasi += '<input style="font-size: 12px" class="form-control jarak" name="eva" id="eva'+i+'" placeholder="'+item.evaluasi+'" onchange="$(\'#eva'+i+'\').val(\''+item.evaluasi+ '\'+\''+" "+'\'+this.value)">';
                            }else{
                                evaluasi +=
                                    '<div class="row">' +
                                    '<div class="form-check02">\n' +
                                    '<input type="checkbox" name="eva" id="eva'+i+'" value="'+item.evaluasi+'">\n' +
                                    '<label for="eva'+i+'"></label> '+item.evaluasi+'\n' +
                                    '</div>' +
                                    '</div>';
                            }
                        }
                    });

                    evaluasi += '<div class="row" style="margin-top: 20px">\n' +
                        '<div class="form-group">\n' +
                        '        <div class="col-md-12">\n' +
                        '            <label style="margin-left: 7px"><b>Perawat</b></label>\n' +
                        '            <canvas style="margin-left: 7px; margin-top: -7px" width="150" onmouseover="paintTtd(\'ttd_perawat\')" class="paint-canvas-ttd" id="ttd_perawat"></canvas>\n' +
                        '            <button style="margin-top: -5px; margin-left: 7px" type="button" class="btn btn-danger" onclick="removePaint(\'ttd_perawat\')"><i class="fa fa-trash"></i> Clear\n' +
                        '            </button>\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div>';

                    $('#dia').html(diagnosis);
                    $('#has').html(hasil);
                    $('#pla').html(intervensi);
                    $('#imp').html(implementasi);
                    $('#eva').html(evaluasi);

                }else{
                    $('#dia').html('');
                    $('#has').html('');
                    $('#pla').html('');
                    $('#imp').html('');
                    $('#eva').html('');
                }
            });
            return selectedObj.diagnosa;
        }
    });
}
