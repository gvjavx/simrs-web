function showModalAsesmenRawatInap(jenis) {
    if(isReadRM){
        $('.btn-hide').hide();
    }else{
        $('.btn-hide').show();
    }
    if ("catatan_integrasi" == jenis) {
        $('.tgl').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tgl').val(formaterDate(new Date()));
        $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
        $('.jam').timepicker();
        $('.jam').val(formaterTime(new Date()));
        listCatatanTerintegrasi();
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
    if (value == "Ya" || value == "Ada kelainan" || value == "Kelainan bawaan" || value == "Lain-Lain" || value == "Cacat bawaan" || value == "Kelemahan gerak" || value == "Lainnya") {
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
    console.log(v6);
    var va6 = isBlank(v6);
    var va7 = isBlank(v7);

    if (va1 && va2 && va3 && va4 && va5 != '' && !va6 && !va7) {

        var ttd1 = v6.toDataURL("image/png"),
            ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
        var ttd2 = v7.toDataURL("image/png"),
            ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1.split("-").reverse().join("-") + ' ' + va2+':00',
            'ppa':va3,
            'jenis':va4,
            'instruksi':va5,
            'keterangan':jenis,
            'ttd_petugas': ttd1,
            'ttd_dpjp':ttd2
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
        if(res.length > 0){
            console.log(res);
            $.each(res, function (i, item) {
                table += '<tr>' +
                    '<td>'+formaterDateTime(item.waktu)+'</td>'+
                    '<td>'+cekNull(item.ppa)+'</td>'+
                    '<td>'+cekNull(item.jenis)+'</td>'+
                    '<td>'+cekNull(item.intruksi)+'</td>'+
                    '<td>'+ '<img style="width: 100%; height: 20px" src="'+item.ttdPetugas+'">'+'</td>'+
                    '<td>'+ '<img style="width: 100%; height: 20px" src="'+item.ttdDpjp+'">'+'</td>'+
                    '</tr>';
            });
            $('#body_catatan_integrasi').html(table);
        }
    });
}

function cekNull(item) {
    var res = "";
    if(item != null){
        res = item;
    }
    return res;
}