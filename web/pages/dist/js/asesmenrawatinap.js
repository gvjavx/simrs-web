function showModalAsesmenRawatInap(jenis, idRM, isSetIdRM, flagHide, flagCheck) {
    if (isSetIdRM == "Y") {
        tempidRm = idRM;
    }
    if (isReadRM || flagHide == "Y") {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }

    if (flagCheck == "Y") {
        $('.btn-check').hide();
    } else {
        $('.btn-check').show();
    }
    if ("s_o" == jenis) {
        loadImgCanvas('area_canvas');
    }

    if ("nyeri" == jenis) {
        setNyeri('set_' + jenis, umur);
    }

    if ("resiko_jatuh" == jenis) {
        setResikoJatuh('set_' + jenis, umur);
    }

    if ("catatan_pemberian" == jenis) {
        listCatatanPemberianObat();
    }

    if ("asuhan" == jenis) {
        listAsuhanKeperawatan(jenis);
    }

    if ("rekonsiliasi" == jenis) {
        listRekonsiliasiObat();
    }

    if ("ringkasan_rj" == jenis) {
        listRekamMedisRJ();
    }

    if ("asuhan_keperawatan_ina" == jenis) {
        $('#diagnosa_askep').val('');
        $('#dia').html('');
        $('#has').html('');
        $('#pla').html('');
        $('#imp').html('');
        $('#eva').html('');
    }

    if ("tindakan_ina" == jenis) {
        selectOptionTM('ina', jenis);
        $('#form-'+jenis).hide();
    }

    if("catatan_integrasi" == jenis){
        var js = "";
        if("rawatbersalin" == urlPage){
            js = "obstetri";
        }
        setEWS(js, umur, 'set_cppt');
        setTimeout(function () {
            var option = "";
            if("gizi" == flagHide){
                option = '<option value="Ahli Gizi">Ahli Gizi</option>';
                $('#btn_ina_catatan_integrasi_pasien_ina').attr('onclick', 'detailCPPT(\'catatan_integrasi_pasien_ina\', \'catatan_terintegrasi_ina\', \'ina\', \'gizi\')');
                $('#cppt5_tensi, #cppt5_suhu, #cppt5_nadi, #cppt5_rr').attr('disabled', true);
                $('#form_ttd_dpjp').hide();
            }else{
                option = '<option value="">[Select One]</option>\n' +
                    '<option value="Dokter">Dokter</option>\n' +
                    '<option value="Perawat">Perawat</option>\n' +
                    '<option value="Apoteker">Apoteker</option>\n' +
                    '<option value="Fisioterapi">Fisioterapi</option>\n' +
                    '<option value="Ahli Gizi">Ahli Gizi</option>\n' +
                    '<option value="Bidan">Bidan</option>';
                $('#btn_ina_catatan_integrasi_pasien_ina').attr('onclick', 'detailCPPT(\'catatan_integrasi_pasien_ina\', \'catatan_terintegrasi_ina\', \'ina\')');
                $('#cppt5_tensi, #cppt5_suhu, #cppt5_nadi, #cppt5_rr').attr('disabled', false);
                $('#form_ttd_dpjp').show();
            }

            $('#cppt3').html(option);
        }, 500);
    }

    radioEdukasiPasien(jenis);
    $('#modal-ina-' + jenis).modal({show: true, backdrop: 'static'});
    setDataPasien();
}

function saveAsesmenRawatInap(jenis, ket) {
    var data = [];
    var cek = false;
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }

    if ("riwayat_kesehatan" == jenis) {
        var va1 = $('#rk1').val();
        var va2 = $('#rk2').val();
        var va3 = $('#rk3').val();
        var va4 = $('#rk4').val();
        var va5 = $('[name=rk5]:checked').val();

        var va6 = $('#rk6').val();
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
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Awal',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Kesehatan yang lalu',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Keluarga',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Obat yang sedang dikonsumsi',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Alergi',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Transfusi Darah',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Merokok',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Minum Minuman Keras',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat pergi ke luar negeri',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
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
        var va13 = $('#kd8').val();
        var va14 = $('#kd9').val();

        if (va1 && va9 && va11 != undefined &&
            va2 && va3 && va4 && va6 && va7 && va8 && va10 && va12 && va13 != '') {

            data.push({
                'parameter': 'Kesadaran',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'GCS',
                'jawaban': 'E = ' + va2 + ', V = ' + va3 + ', M = ' + va4 + ', Hasil = ' + (parseInt(va2)+parseInt(va3)+parseInt(va4)),
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban': va6 + ' mmHg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': va7 + ' ˚C',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': va8 + ' x/menit, ' + va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nafas',
                'jawaban': va10 + ' x/menit, ' + va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan',
                'jawaban': va12 + ' Kg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tinggi Badan',
                'jawaban': va13 + ' cm',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'SPO2',
                'jawaban': va14,
                'keterangan': jenis,
                'jenis': ket,
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
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Wajah',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mata',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Telinga',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hidung',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mulut',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Leher',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dada',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perut',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anggota',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Gerak',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kulit',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
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
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kondisi Mental',
                'jawaban': isi2,
                'skor': skor2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rentang Aktivitas',
                'jawaban': isi3,
                'skor': skor3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mobilitas',
                'jawaban': isi4,
                'skor': skor4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Inkontinentia',
                'jawaban': isi5,
                'skor': skor5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("nyeri" == jenis) {
        var va1 = $('[name=ny1]:checked').val();
        var va2 = $('[name=ny2]:checked').val();
        var va3 = $('#ny3').val();
        var lokasi = $('#y_lokasi').val();
        var jen = $('#temp_jenis').val();
        var nyeri = "";
        var tipe = "";
        if (jen == "emoji") {
            nyeri = document.getElementById('choice_emoji');
            tipe = "Wong Baker Pain Scale";
        } else {
            nyeri = document.getElementById('area_nyeri');
            tipe = "Nomeric Rating Scale";
        }
        var cvs1 = isCanvasBlank(nyeri);

        var yeye = false;
        if(va1 == "Ya"){
            if(va2 != undefined && va3 && lokasi != '' && !cvs1){
                yeye = true;
            }
        }else{
            yeye = true;
        }

        if (va1 && yeye) {
            data.push({
                'parameter': 'Nyeri',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban': lokasi != '' ? lokasi : '-',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis',
                'jawaban': va2 != undefined ? va2 : '-',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Intensitas',
                'jawaban': va3 != '' ? va3 : '-',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            if(!cvs1){
                var canv1 = convertToDataURL(nyeri);
                data.push({
                    'parameter': tipe,
                    'jawaban': canv1,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'gambar',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            cek = true;
        }
    }

    if ("funsional" == jenis) {

        var va1 = $('[name=fun1]:checked').val();
        var va2 = $('[name=fun2]:checked').val();
        var va3 = $('[name=fun3]:checked').val();
        var va4 = $('[name=fun4]:checked').val();
        var va5 = $('[name=fun5]:checked').val();
        var va6 = $('[name=fun6]:checked').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 != undefined) {

            var isi1 = va1.split("|")[0];
            var isi2 = va2.split("|")[0];
            var isi3 = va3.split("|")[0];
            var isi4 = va4.split("|")[0];
            var isi5 = va5.split("|")[0];
            var isi6 = va6.split("|")[0];

            var skor1 = va1.split("|")[1];
            var skor2 = va2.split("|")[1];
            var skor3 = va3.split("|")[1];
            var skor4 = va4.split("|")[1];
            var skor5 = va5.split("|")[1];
            var skor6 = va6.split("|")[1];

            data.push({
                'parameter': 'Makan/Minum',
                'jawaban': isi1,
                'skor': skor1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mandi',
                'jawaban': isi2,
                'skor': skor2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Memakai Baju',
                'jawaban': isi3,
                'skor': skor3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'BAB/BAK',
                'jawaban': isi4,
                'skor': skor4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Naik Turun Tangga',
                'jawaban': isi5,
                'skor': skor5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mengontrol BAB/BAK',
                'jawaban': isi6,
                'skor': skor6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var jumlah =
                parseInt(skor1) +
                parseInt(skor2) +
                parseInt(skor3) +
                parseInt(skor4) +
                parseInt(skor5) +
                parseInt(skor6);

            data.push({
                'parameter': 'Total Skor',
                'jawaban': '',
                'skor': ""+jumlah,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'total',
                'id_detail_checkup': idDetailCheckup
            });

            var kesimpulan = "";
            if (parseInt(jumlah) > 50 && parseInt(jumlah) <= 60) {
                kesimpulan = "Tidak Ada Ketergantungan";
            } else if (parseInt(jumlah) >= 30 && parseInt(jumlah) <= 50) {
                kesimpulan = "Ketergantungan Sebagian";
            } else {
                kesimpulan = "Tergantungan Penuh";
            }
            data.push({
                'parameter': 'Kesimpulan',
                'jawaban': kesimpulan,
                'keterangan': jenis,
                'tipe': 'kesimpulan',
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("resiko_jatuh" == jenis) {
        var tgl = $('#ps01').val();
        var jam = $('#ps02').val();
        data.push({
            'parameter': 'Tanggal Jam',
            'jawaban': tgl + ' ' + jam,
            'keterangan': jenis,
            'jenis': ket,
            'id_detail_checkup': idDetailCheckup
        });
        var resikoJatuh = $('.resiko_jatuh');
        var jenisResiko = $('#jenis_resiko').val();
        var totalSkor = "";
        if (resikoJatuh.length > 0) {
            $.each(resikoJatuh, function (i, item) {
                var label = $('#label_resiko_jatuh' + i).text();
                var resiko = $('[name=resiko_jatuh' + i + ']:checked').val();
                if (resiko != undefined) {
                    var isi = resiko.split("|")[0];
                    var skor = resiko.split("|")[1];
                    data.push({
                        'parameter': label,
                        'jawaban': isi,
                        'skor': skor,
                        'keterangan': jenis,
                        'jenis': ket,
                        'id_detail_checkup': idDetailCheckup
                    });
                    if (totalSkor != '') {
                        totalSkor = parseInt(totalSkor) + parseInt(skor);
                    } else {
                        totalSkor = parseInt(skor);
                    }
                }
            });

            if (totalSkor != '') {
                data.push({
                    'parameter': 'Total Skor',
                    'jawaban': '' + totalSkor,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'total',
                    'id_detail_checkup': idDetailCheckup
                });

                var kesimpulan = "";

                if (jenisResiko == "humpty_dumpty") {
                    if (parseInt(totalSkor) >= 7 && parseInt(totalSkor) <= 11) {
                        kesimpulan = "Rendah";
                    } else if (parseInt(totalSkor) >= 12) {
                        kesimpulan = "Tinggi";
                    }
                } else if (jenisResiko == "skala_morse") {
                    if (parseInt(totalSkor) >= 0 && parseInt(totalSkor) <= 24) {
                        kesimpulan = "Rendah";
                    } else if (parseInt(totalSkor) >= 25 && parseInt(totalSkor) <= 44) {
                        kesimpulan = "Sedang";
                    } else if (parseInt(totalSkor) >= 45) {
                        kesimpulan = "Tinggi";
                    }
                } else if (jenisResiko == "geriatri") {
                    if (parseInt(totalSkor) >= 0 && parseInt(totalSkor) <= 5) {
                        kesimpulan = "Rendah";
                    } else if (parseInt(totalSkor) >= 6 && parseInt(totalSkor) <= 16) {
                        kesimpulan = "Sedang";
                    } else if (parseInt(totalSkor) >= 17) {
                        kesimpulan = "Tinggi";
                    }
                }
                data.push({
                    'parameter': 'Resiko Jatuh',
                    'jawaban': kesimpulan,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'kesimpulan',
                    'id_detail_checkup': idDetailCheckup
                });
            }
        }
        if (resikoJatuh.length == data.length - 3) {
            cek = true;
        }
    }

    if ("psiko_social" == jenis) {
        var va1 = "";
        var va2 = $('#psko2').val();
        var va3 = "";
        var va4 = $('#psko4').val();
        var va5 = $('#psko5').val();
        var va6 = $('#psko6').val();
        var va7 = $('#psko7').val();

        var tempat = $('#psko1').val();
        if (tempat == "Lainnya") {
            var t = $('#ket_tempat_tinggal').val();
            if (t != '') {
                va1 = t;
            }
        } else {
            va1 = tempat;
        }

        var perilaku = $('#psko3').val();
        if (perilaku == "Ada") {
            var p = $('#ket_perilaku').val();
            if (p != '') {
                va3 = p;
            }
        } else {
            va3 = perilaku;
        }
        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 != '') {
            data.push({
                'parameter': 'Tempat Tinggal',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status Emosional',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Masalah Perilaku',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status Pernikahan',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hubungan pasien dengan keluarga',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Agama',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perlu dibantu dalam ibadah',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("skrining_nutrisi" == jenis) {
        var nutrisi1 = $('[name=radio_aud_nutrisional]:checked').val();
        var nutrisi2 = $('[name=radio_aud_nafsu1]:checked').val();
        var nutrisi3 = $('[name=radio_diagnosis1]:checked').val();

        var tNutrisi1 = "";
        if (nutrisi1 == "Ya") {
            var penurunan = $('[name=radio_aud_penurunan]:checked').val();
            if (penurunan != undefined) {
                tNutrisi1 = nutrisi1 + ', ' + penurunan;
            }
        } else {
            tNutrisi1 = nutrisi1
        }

        if (tNutrisi1 && nutrisi2 && nutrisi3 != undefined) {
            var skor1 = tNutrisi1.split("|")[1];
            var skor2 = nutrisi2.split("|")[1];
            var skor3 = nutrisi3.split("|")[1];

            var isi1 = tNutrisi1.split("|")[0];
            var isi2 = nutrisi2.split("|")[0];
            var isi3 = nutrisi3.split("|")[0];

            data.push({
                'parameter': 'Apakah pasien mengalami penurunan berat badan yang tidak direncanakan /tidak diinginkan dalam 6 bulan terakhir?',
                'jawaban': isi1,
                'keterangan': jenis,
                'jenis': ket,
                'skor': skor1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Apakah asupan makan pasien berkurang karena penurunan nafsu makan/kesulitan menerima makanan?',
                'jawaban': isi2,
                'keterangan': jenis,
                'jenis': ket,
                'skor': skor2,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pasien dengan diagnosis khusus?',
                'jawaban': isi3,
                'keterangan': jenis,
                'jenis': ket,
                'skor': skor3,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("neurologi" == jenis) {
        var va1 = $('[name=neu]');
        var temp = "";
        $.each(va1, function (i, item) {
            if (item.checked) {
                if (item.value != '') {
                    if (temp != '') {
                        temp = temp + '|' + item.value;
                    } else {
                        temp = item.value;
                    }
                }
            }
        });
        if (temp != '') {
            data.push({
                'parameter': 'Neurologi',
                'jawaban': temp,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("genitourinaria" == jenis) {
        var va1 = $('[name=gen1]');
        var va2 = $('#catatan_gen').val();
        var temp = "";

        $.each(va1, function (i, item) {
            if (item.checked) {
                if (item.value != '') {
                    if (temp != '') {
                        temp = temp + '|' + item.value;
                    } else {
                        temp = item.value;
                    }
                }
            }
        });
        if (temp != '' && !cek1 && !cek2) {
            data.push({
                'parameter': 'Genitourinaria',
                'jawaban': temp,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            if (va2 != '') {
                data.push({
                    'parameter': 'Catatan',
                    'jawaban': va2,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            cek = true;
        }
    }

    if("diagnosa_keperawatan" == jenis){
        var ttd1 = document.getElementById('gen2');
        var ttd2 = document.getElementById('gen3');
        var nama1 = $('#nama_terang_gen2').val();
        var nama2 = $('#nama_terang_gen3').val();
        var sip1 = $('#sip_gen2').val();
        var sip2 = $('#sip_gen3').val();

        var cek1 = isCanvasBlank(ttd1);
        var cek2 = isCanvasBlank(ttd2);


        var va1 = $('[name=diagnosa_keperawatan]');
        var jamngisi = $('#jam_ngisi').val();
        var temp = "";

        $.each(va1, function (i, item) {
            if (item.checked) {
                if (item.value != '') {
                    if (temp != '') {
                        temp = temp + '|' + item.value;
                    } else {
                        temp = item.value;
                    }
                }
            }
        });

        if(temp != '' && jamngisi != '' && nama1 && nama2 && sip1 && sip2 != '' && !cek1 && !cek2){
            data.push({
                'parameter': 'Diagnosa Keperawatan',
                'jawaban': temp,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jam Selesai Asesmen',
                'jawaban': jamngisi,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var cvs1 = convertToDataURL(ttd1);
            var cvs2 = convertToDataURL(ttd2);
            data.push({
                'parameter': 'TTD Perawat',
                'jawaban': cvs1,
                'keterangan': jenis,
                'jenis': ket,
                'nama_terang': nama1,
                'sip': sip1,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD DPJP',
                'jawaban': cvs2,
                'keterangan': jenis,
                'jenis': ket,
                'nama_terang': nama2,
                'sip': sip2,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if("skrining_farmasi" == jenis){
        var va1 = $('[name=farmasi]');
        var temp = "";
        $.each(va1, function (i, item) {
            if (item.checked) {
                if (item.value != '') {
                    if (temp != '') {
                        temp = temp + '|' + item.value;
                    } else {
                        temp = item.value;
                    }
                }
            }
        });

        if(temp != '') {
            data.push({
                'parameter': 'Skrining Farmasi',
                'jawaban': temp,
                'keterangan': jenis,
                'jenis': ket,
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

        var ttd1 = document.getElementById("ttd_dpjp");
        var ttd2 = document.getElementById("ttd1_keluarga");
        var ttd3 = document.getElementById("ttd_perawat");
        var ttdCek1 = isCanvasBlank(ttd1);
        var ttdCek2 = isCanvasBlank(ttd2);
        var ttdCek3 = isCanvasBlank(ttd3);

        var nama1 = $('#nama_dpjp').val();
        var sip1 = $('#sip_dpjp').val();
        var nama2 = $('#nama_keluarga').val();
        var nama3 = $('#nama_perawat').val();
        var sip3 = $('#sip_perawat').val();

        if (va1 && va2 && va3 && va4 && va5 && nama1 && sip1 && nama2 && nama3 && sip3 != '') {

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

            var cvs1 = ttd1.toDataURL("image/png"),
                cvs1 = cvs1.replace(/^data:image\/(png|jpg);base64,/, "");
            var cvs2 = ttd1.toDataURL("image/png"),
                cvs2 = cvs2.replace(/^data:image\/(png|jpg);base64,/, "");
            var cvs3 = ttd1.toDataURL("image/png"),
                cvs3 = cvs3.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD DPJP',
                'jawaban': cvs1,
                'keterangan': jenis,
                'nama_terang': nama1,
                'sip': sip1,
                'tipe': 'ttd',
                'jenis': 'discharge_planing',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Keluarga',
                'jawaban': cvs2,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'nama_terang': nama2,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Perawat',
                'jawaban': cvs3,
                'keterangan': jenis,
                'jenis': 'discharge_planing',
                'nama_terang': nama3,
                'sip': sip3,
                'tipe': 'ttd',
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
        var ttdCek = isCanvasBlank(ttd);
        var namaTerang = $('#nama_terang_ews12').val();
        var sip = $('#sip_terang_ews12').val();

        if (va1 && va2 && va3 && va4 != undefined && va5 && va6 && va7 && va8 && va9 && namaTerang && sip != '' && !ttdCek) {
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
                'jenis': 'early_warning',
                'tipe': 'ttd',
                'nama_terang': namaTerang,
                'sip': sip,
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
        var ttdCek = isCanvasBlank(ttd);
        var nama = $('#nama_pembuat').val();

        if (nama != '' && va1 && va2 && va3 && va4 && va5 && va6 != '' && !ttdCek) {

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
                'jenis': 'privasi',
                'tipe': 'nama_tercantum',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Tanda Tangan Pembuat Pernyataan',
                'jawaban': ttd1,
                'keterangan': jenis,
                'jenis': 'privasi',
                'tipe': 'ttd',
                'nama_terang': nama,
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

    if("add_transfer_pasien" == jenis){
        var cekData1 = false;
        var cekData2 = false;
        var cekData3 = false;

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
                'parameter': 'Data Ruangan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'first',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ruang Asal',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'action',
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
                'tipe': 'split',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Derajat Transfer',
                'jawaban': temp8,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'split',
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
            cekData1 = true;
        }

        var va1 = $('#ck1').val();
        var va2 = $('#ck2').val();
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

        if (va2 != '' && va1 && va3 && va4 && va5 && va6 && temp7 && va8 != '') {
            data.push({
                'parameter': 'Catatan Klinis',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'bold',
                'id_detail_checkup': idDetailCheckup
            });
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
                'tipe': 'split',
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
                    'tipe': 'split',
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
                'tipe': 'split',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Catatan Hal penting',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'id_detail_checkup': idDetailCheckup
            });
            cekData2 = true;
        }

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

        var ttd1 = document.getElementById("perawat_pengirim");
        var nama1 = $('#nama_terang_pengirim').val();
        var sip1 = $('#nip_perawat_pengirim').val();
        var cekTtd1 = isCanvasBlank(ttd1);

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va11 && va12 &&
            va13 && va14 && va15 && va16 && va17 && va18 != '' && !cekTtd1) {
            data.push({
                'parameter': 'Kondisi Pasien Saat Serah Terima',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'bold',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan',
                'jawaban': 'Sebelum ditransfer|Saat perjalanan|Saat diterima',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'serah_terima',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesadaran Umum',
                'jawaban': va1 + '|' + va2,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'serah_terima',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesadaran / GCS',
                'jawaban': va3 + '|' + va4,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'serah_terima',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban': replaceUnderLine(va5) + ' mmHg|' + replaceUnderLine(va6) + ' mmHg',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'serah_terima',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': va7 + ' ˚C|' + va8 + ' ˚C',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'serah_terima',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': va9 + ' x/mnt|' + va10 + ' x/mnt',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'serah_terima',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'RR',
                'jawaban': va11 + ' x/mnt|' + va12 + ' x/mnt',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'serah_terima',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saturasi',
                'jawaban': va13 + ' %|' + va14 + ' %',
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'serah_terima',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skala Nyeri',
                'jawaban': va15 + '|' + va16,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'serah_terima',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Resiko Jatuh',
                'jawaban': va17 + '|' + va18,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'serah_terima',
                'id_detail_checkup': idDetailCheckup
            });

            if (va19 && va20 != '') {
                data.push({
                    'parameter': 'Lain-Lain',
                    'jawaban': va19 + '|' + va20,
                    'keterangan': jenis,
                    'jenis': 'transfer_pasien',
                    'tipe': 'serah_terima',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Perawat Pengirim',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'ttd',
                'nama_terang':nama1,
                'sip':sip1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perawat Penerima',
                'jawaban': "",
                'keterangan': jenis,
                'jenis': 'transfer_pasien',
                'tipe': 'ttd',
                'nama_terang':nama2,
                'sip':sip2,
                'id_detail_checkup': idDetailCheckup
            });
            cekData3 = true;
        }

        if(cekData1 && cekData2 && cekData3){
            cek = true;
        }
    }

    if ("add_tindakan_ina" == jenis) {
        var va1 = $('#op1').val();
        var va2 = $('#op2').val();
        var va3 = $('#op3').val();
        var va4 = $('#op4').val();
        var va5 = $('#op5').val();
        var va6 = $('#op6').val();
        var va7 = $('#op7').val();
        var va8 = $('#op8').val();
        var va9 = $('#op9').val();
        var va10 = $('#op10').val();
        var va11 = $('#op11').val();

        var persetujuan = $('#nama_tindakan_medis').val();
        var parameter = $('[name=parameter]');
        var tanda = $('[name=tanda]');

        var ttd1 = document.getElementById("ttd1");
        var ttd2 = document.getElementById("ttd2");
        var ttd3 = document.getElementById("ttd3");
        var ttd4 = document.getElementById("ttd4");
        var ttd5 = document.getElementById("ttd5");

        var nama1 = $('#nama_terang_ttd1').val();
        var sip1 = $('#sip_ttd1').val();
        var nama2 = $('#nama_terang_ttd2').val();
        var nama3 = $('#nama_terang_ttd3').val();
        var sip3 = $('#sip_ttd3').val();
        var nama4 = $('#nama_terang_ttd4').val();
        var nama5 = $('#nama_terang_ttd5').val();
        var sip5 = $('#sip_ttd5').val();

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);
        var cekTtd4 = isCanvasBlank(ttd4);
        var cekTtd5 = isCanvasBlank(ttd5);

        if (nama1 && nama2 && nama3 && nama4 && nama5 && sip1 && va1 && va2 && va3 && va4 && persetujuan != '' && !cekTtd1 && !cekTtd2 && !cekTtd3 && !cekTtd4 && !cekTtd5) {

            data.push({
                'parameter': 'pernyataan',
                'jawaban': 'Pemberian Informasi dan Persetujuan ' + persetujuan,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter Penanggung Jawab',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemberi Informasi',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penerima Informasi',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Jenis Informasi',
                'jawaban': 'Isi Informasi',
                'informasi': 'Check Informasi',
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'bold',
                'id_detail_checkup': idDetailCheckup
            });

            $.each(parameter, function (i, item) {
                var informasi = $('[name=informasi' + i + ']');
                var info = "";
                $.each(informasi, function (idx, itemx) {
                    if (itemx.type == 'checkbox') {
                        if (itemx.checked) {
                            if (info != '') {
                                info = info + ', ' + itemx.value;
                            } else {
                                info = itemx.value;
                            }
                        }
                    }
                    if (itemx.type == 'radio') {
                        if (itemx.checked) {
                            if (info != '') {
                                info = info + ', ' + itemx.value;
                            } else {
                                info = itemx.value;
                            }
                        }
                    }
                    if (itemx.type == 'text') {
                        if (itemx.value != '') {
                            if (info != '') {
                                info = info + ', ' + itemx.value;
                            } else {
                                info = itemx.value;
                            }
                        }
                    }
                    if (itemx.type == 'hidden') {
                        if (itemx.value != '') {
                            if (info != '') {
                                info = info + ', ' + itemx.value;
                            } else {
                                info = itemx.value;
                            }
                        }
                    }
                });

                var tdn = "";
                if (tanda[i].checked) {
                    tdn = tanda[i].value;
                }

                data.push({
                    'parameter': item.value,
                    'informasi': tdn,
                    'jawaban': info,
                    'keterangan': jenis,
                    'jenis': persetujuan,
                    'tipe': 'info',
                    'id_detail_checkup': idDetailCheckup
                });
            });


            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv2 = ttd2.toDataURL("image/png"),
                canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv3 = ttd3.toDataURL("image/png"),
                canv3 = canv3.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv4 = ttd4.toDataURL("image/png"),
                canv4 = canv4.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv5 = ttd5.toDataURL("image/png"),
                canv5 = canv5.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerangkan hal-hal di atas secara benar dan jelas dengan memberikan kesempatakan bertanya dan atau diskusi kepada pasien dan/atau keluarganya sedemikian rupa sehingga telah memahaminya',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama1,
                'sip':sip1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerima informasi sebagaimana di atas dan telah memahaminya',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama2,
                'id_detail_checkup': idDetailCheckup
            });
            if($('#h_is_biaya').val() == "Y"){
                data.push({
                    'parameter': 'pernyataan',
                    'jawaban': 'Biaya adalah perkiraan biaya yang harus dibayarkan oleh pihak pasien berdasarkan perkiraan dalam kasus-kasus sewajarnya dan tidak mengikat kedua belah pihak apabila ada perluasan',
                    'keterangan': jenis,
                    'jenis': persetujuan,
                    'tipe': 'colspan',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            data.push({
                'parameter': 'pernyataan',
                'jawaban': 'Persetujuan Tindakan Medis',
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'pernyataan',
                'jawaban': 'Yang bertanda tangan dibawah ini, Saya ' + va5 + ' ' +
                    'tanggal lahir ' + va6 + ', ' + va7 + ' dengan ini menyatakan persetujuan untuk dilakukan tindakan ' + persetujuan + ' ' +
                    'terhadap pasien Bernama ' + va9 + ' tanggal lahir ' + va10 + ', Alamat ' + va11 + '.' +
                    'Saya memahami perlunya dan manfaat tindakan tersebut sebagaimana telah dijelaskan seperti diatas ' +
                    'kepada saya termasuk resiko dan komplikasi yang timbul ' +
                    'Saya juga menyadari bahwa oleh karena itu ilmu kedokteran bukan ilmu pasti, maka keberhasilan tindakan ' +
                    'kedokteran bukan keniscayaan, tetapi tergantung kepada izin Tuhan Yang maha Esa. Tanggal ' + converterDate(new Date) + ', Jam ' + converterTime(new Date()) +' WIB',
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD yang menyatakan',
                'jawaban': canv3,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama3,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saksi Keluarga',
                'jawaban': canv4,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama4,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perawat Pendamping',
                'jawaban': canv5,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama5,
                'sip': sip5,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("transfer_external" == jenis) {
        var va1 = $('#ina1').val();
        var va2 = $('#ina2').val();
        var va3 = $('#ina3').val();
        var va4 = $('#ina4').val();
        var va5 = $('#ina5').val();
        var va6 = $('#ina6').val();
        var va7 = $('#ina7').val();
        var va8 = $('#ina8').val();
        var va9 = $('#ina9').val();
        var va10 = $('#ina10').val();

        var va11 = $('[name=ina11]:checked').val();
        var va12 = $('[name=ina12]');
        var va13 = $('[name=ina13]:checked').val();
        var va14 = $('[name=ina14]');
        var va15 = $('[name=ina15]:checked').val();
        var va16 = $('[name=ina16]');
        var va17 = $('[name=ina17]:checked').val();
        var va18 = $('[name=ina18]');
        var va19 = $('[name=ina19]:checked').val();
        var va20 = $('[name=ina20]');

        var va21 = $('[name=ina21]:checked').val();
        var va22 = $('[name=ina22]:checked').val();
        var va23 = $('[name=ina23]:checked').val();
        var va24 = $('[name=ina24]:checked').val();
        var va25 = $('[name=ina25]:checked').val();

        var ttd1 = document.getElementById("ttd1_dokter");
        var ttd2 = document.getElementById("ttd1_pasien");
        var nama1 = $('#nama_terang_dokter').val();
        var nama2 = $('#nama_terang_pasien').val();
        var sip1 = $('#sip_dokter').val();

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && nama1 && nama2 && sip1 != '' && !cekTtd1 && !cekTtd2) {

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv2 = ttd2.toDataURL("image/png"),
                canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Nama Pasien',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tgl Lahir',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Masuk',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'DPJP',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal dan Jam Transfers',
                'jawaban': va5 + ' ' + va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter konsulen',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Unit yang dituju',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa masuk',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alasan transfer',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            if (va11 != undefined) {
                var ketIna = "";
                $.each(va12, function (i, item) {
                    if (item.checked) {
                        if ("Pemeriksaan tanda vital" == item.value) {

                            var ket1 = $('#ket_ina1241').val();
                            var ket2 = $('#ket_ina1242').val();
                            var ket3 = $('#ket_ina1243').val();
                            var ket4 = $('#ket_ina1244').val();

                            if (ketIna != '') {
                                ketIna = ketIna + '| TD : ' + ket1 + ' mmHg, Suhu : ' + ket2 + ' ˚C , Nadi : ' + ket3 + ', RR : ' + ket4 + ' x/menit';
                            } else {
                                ketIna = "TD : " + ket1 + ' mmHg, Suhu : ' + ket2 + ' ˚C , Nadi : ' + ket3 + ', RR : ' + ket4 + ' x/menit';
                            }

                        } else {
                            if (ketIna != '') {
                                ketIna = ketIna + '|' + item.value;
                            } else {
                                ketIna = item.value;
                            }
                        }
                    }
                });
                data.push({
                    'parameter': va11,
                    'jawaban': ketIna,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'li',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if (va13 != undefined) {
                var ketIna = "";
                $.each(va14, function (i, item) {
                    if (item.checked) {
                        if (ketIna != '') {
                            ketIna = ketIna + '|' + item.value;
                        } else {
                            ketIna = item.value;
                        }
                    }
                });
                data.push({
                    'parameter': va13,
                    'jawaban': ketIna,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'li',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if (va15 != undefined) {
                var ketIna = "";
                $.each(va16, function (i, item) {
                    if (item.checked) {
                        if (ketIna != '') {
                            ketIna = ketIna + '|' + item.value;
                        } else {
                            ketIna = item.value;
                        }
                    }
                });
                data.push({
                    'parameter': va15,
                    'jawaban': ketIna,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'li',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if (va17 != undefined) {
                var ketIna = "";
                $.each(va18, function (i, item) {
                    if (item.checked) {
                        if (ketIna != '') {
                            ketIna = ketIna + '|' + item.value;
                        } else {
                            ketIna = item.value;
                        }
                    }
                });
                data.push({
                    'parameter': va17,
                    'jawaban': ketIna,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'li',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if (va19 != undefined) {
                var ketIna = "";
                $.each(va20, function (i, item) {
                    if (item.checked) {
                        if (ketIna != '') {
                            ketIna = ketIna + '|' + item.value;
                        } else {
                            ketIna = item.value;
                        }
                    }
                });
                data.push({
                    'parameter': va19,
                    'jawaban': ketIna,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'li',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            if (va21 != undefined) {
                data.push({
                    'parameter': va21,
                    'jawaban': '',
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'colspan',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            if (va22 != undefined) {
                data.push({
                    'parameter': va22,
                    'jawaban': '',
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'colspan',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            if (va23 != undefined) {
                data.push({
                    'parameter': va23,
                    'jawaban': '',
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'colspan',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            if (va24 != undefined) {
                data.push({
                    'parameter': va24,
                    'jawaban': '',
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'colspan',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            if (va25 != undefined) {
                data.push({
                    'parameter': va25,
                    'jawaban': '',
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'colspan',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            data.push({
                'parameter': 'TTD Dokter',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama1,
                'sip': sip1,
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'TTD Pasien',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama2,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("s_o" == jenis) {
        var va1 = $('#so1').val();
        var va2 = $('#so2').val();
        var va3 = $('#so3').val();
        var va4 = $('#so4').val();
        var va5 = $('#so5').val();
        var va6 = $('#so6').val();
        var va7 = $('#so7').val();
        var va8 = $('#so8').val();
        var ket = $('#ket_area_canvas').val();
        var fisik = document.getElementById("area_canvas");

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 != '') {

            data.push({
                'parameter': 'Anamnesa',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Dahulu',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Keluarga',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Alergi',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status Gizi',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var cvs = convertToDataURL(fisik);
            data.push({
                'parameter': 'Pemeriksaan Fisik',
                'jawaban': cvs,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'gambar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keterangan',
                'jawaban': ket,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Laborat',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Radiologi',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'ECG',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("a_p" == jenis) {
        var va1 = $('#ap1').val();
        var va2 = $('#ap2').val();
        var va3 = $('#ap3').val();
        var va4 = $('#ap4').val();
        var va5 = $('#ap5').val();
        var va6 = $('#ap6').val();
        var va7 = $('#ap7').val();
        var va8 = $('#ap8').val();

        var ttd = document.getElementById("ap9");
        var nama = $('#nama_terang_ap9').val();
        var sip = $('#sip_ap9').val();
        var cekTTD = isCanvasBlank(ttd);

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && nama && sip != '' && !cekTTD) {

            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perencanaan Pelayanan',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Penunjang',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pengobatan / Tindakan',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hasil yang diharapkan (Prognosa)',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Tanggal & jam',
                'jawaban': va7 + ' ' + va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var cvs = convertToDataURL(ttd);
            data.push({
                'parameter': 'TTD DPJP',
                'jawaban': cvs,
                'keterangan': jenis,
                'jenis': ket,
                'nama_terang': nama,
                'sip': sip,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("hand_over_jaga" == jenis) {
        var va1 = $('#ho1').val();
        var va2 = $('#ho2').val();
        var va3 = $('#ho3').val();
        var va4 = $('#ho4').val();
        var va5 = $('#ho5').val();
        var va6 = $('#ho6').val();
        var va7 = $('#ho7').val();
        var va8 = $('#ho8').val();
        var va9 = $('#ho9').val();
        var va10 = $('#ho10').val();
        var va11 = $('#ho11').val();
        var va12 = $('#ho12').val();
        var va13 = $('#ho13').val();
        var va14 = $('#ho14').val();
        var va15 = $('#ho15').val();
        var va16 = $('#ho16').val();
        var va17 = $('#ho17').val();
        var va18 = $('#ho18').val();
        var va19 = $('#ho19').val();
        var va20 = $('#ho20').val();
        var va21 = $('#ho21').val();
        var va22 = $('#ho22').val();
        var va23 = $('#ho23').val();
        var va24 = $('#ho24').val();
        var va25 = $('#ho25').val();
        var va26 = $('#ho26').val();
        var va27 = $('#ho27').val();
        var waktu = $('#waktu').val();
        var ttd1 = document.getElementById('ho28');
        var ttd2 = document.getElementById('ho29');
        var nama1 = $('#nama_terang_ho28').val();
        var nama2 = $('#nama_terang_ho29').val();
        var nip1 = $('#nip_ho28').val();
        var nip2 = $('#nip_ho29').val();
        var cek1 = isCanvasBlank(ttd1);
        var cek2 = isCanvasBlank(ttd2);

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 &&
            va11 && va12 && va13 && va14 && va15 && va16 && va17 && va18 && va19 && va20 &&
            va21 && va22 && va23 && va24 && va25 && va26 && va27 && nama1 && waktu && nip1 && !cek1) {

            data.push({
                'parameter': 'Waktu',
                'jawaban': waktu,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ruangan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hari/Tanggal',
                'jawaban': va2 + ', ' + va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'DPJP',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnoses',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesadaran',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'GCS (EVM)',
                'jawaban': 'E : ' + va8 + ', V : ' + va9 + ', M : ' + va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tensi',
                'jawaban': va11 + ' mmHg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': va12 + ' x/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': va13 + ' ˚C',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'RR',
                'jawaban': va14 + ' x/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nyeri',
                'jawaban': va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Oksigen',
                'jawaban': va16 + ' l/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Infus',
                'jawaban': va17 + ' tts/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Transfusi',
                'jawaban': va18 + ' tts/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kateter',
                'jawaban': va19,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'NGT',
                'jawaban': va20,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Makan/Minum',
                'jawaban': va21,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Toileting',
                'jawaban': va22,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Aktivitas Gerak',
                'jawaban': va23,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Keperawatan',
                'jawaban': va24,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Intervensi yang sudah dilakukan',
                'jawaban': va25,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan Kolaborasi',
                'jawaban': va26,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rencana Umum',
                'jawaban': va27,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var cvs1 = convertToDataURL(ttd1);
            var cvs2 = convertToDataURL(ttd2);

            data.push({
                'parameter': 'Pemberi Operan',
                'jawaban': cvs1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama1,
                'sip': nip1,
                'id_detail_checkup': idDetailCheckup
            });

            var ttp = "";
            if(!cek2){
                ttp = cvs2;
            }
            data.push({
                'parameter': 'Penerima Operan',
                'jawaban': ttp,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama2,
                'sip': nip2,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            $('#save_ina_' + jenis).hide();
            $('#load_ina_' + jenis).show();
            dwr.engine.setAsync(true);
            AsesmenRawatInapAction.saveAsesmenRawat(result, pasienData, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_ina_' + jenis).show();
                        $('#load_ina_' + jenis).hide();
                        $('#modal-ina-' + jenis).modal('hide');
                        $('#warning_ina_' + ket).show().fadeOut(5000);
                        $('#msg_ina_' + ket).text("Berhasil menambahkan data ....");
                        $('#modal-ina-' + jenis).scrollTop(0);
                        delRowAsesmenRawatInap(jenis);
                        detailAsesmenRawatInap(jenis);
                    } else {
                        $('#save_ina_' + jenis).show();
                        $('#load_ina_' + jenis).hide();
                        $('#warning_ina_' + jenis).show().fadeOut(5000);
                        $('#msg_ina_' + jenis).text(res.msg);
                        $('#modal-ina-' + jenis).scrollTop(0);
                    }
                }
            })
        }
    } else {
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function detailAsesmenRawatInap(jenis) {
    if(!cekSession()){
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
            var cekSerahTerima = 0;

            AsesmenRawatInapAction.getListAsesmenRawat(noCheckup, jenis, function (res) {
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
                        } else if ("kebutuhan_discharge_planing" == item.keterangan ||
                            "neurologi" == item.keterangan ||
                            "genitourinaria" == item.keterangan ||
                            "diagnosa_keperawatan" == item.keterangan ||
                            "skrining_farmasi" == item.keterangan) {

                            var li = "";
                            var isi = jwb.split("|");
                            $.each(isi, function (i, item) {
                                if (item == "Tujuan") {
                                    li += '<br><li style="list-style-type: none; font-weight: bold">' + item + '</li>';
                                } else {
                                    li += '<li>' + item + '</li>';
                                }
                            });

                            if(item.tipe == 'ttd'){
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban + '" style="height: 100px">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerang)+'</p>' +
                                    '<p style="margin-top: -10px">'+cekItemIsNull(item.sip)+'</p>' +
                                    '</td>' +
                                    '</tr>';
                            }else{
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td width="40%">' + '<ul style="margin-left: 10px">' + li + '</ul>' + '</td>' +
                                    '</tr>';
                            }
                        } else if ("early_warning_score" == item.keterangan) {
                            if (item.skor != null) {
                                skor = item.skor;
                            }
                            if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<img style="height: 50px" src="' + jwb + '">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerang)+'</p>' +
                                    '<p style="margin-top: -10px">'+cekItemIsNull(item.sip)+'</p>' +
                                    '</td>' +
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
                            if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<img style="height: 50px" src="' + jwb + '">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerang)+'</p>' +
                                    '<p style="margin-top: -10px">'+cekItemIsNull(item.sip)+'</p>' +
                                    '</td>' +
                                    '</tr>';
                            } else if ("nama_tercantum" == item.tipe) {

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

                        } else if ("add_transfer_pasien" == item.keterangan) {
                            if ("split" == item.tipe) {
                                var isi = jwb.split("|");
                                var li = "";
                                $.each(isi, function (i, item) {
                                    li += '<li>' + item + '</li>';
                                });
                                if (li != '') {
                                    body += '<tr>' +
                                        '<td>' + item.parameter + '</td>' +
                                        '<td colspan="3">' + '<ul style="margin-left: 15px">' + li + '</ul>' + '</td>' +
                                        '</tr>';
                                }
                            }else if ("serah_terima" == item.tipe) {
                                var isi = jwb.split("|");
                                var sebelum = isi[0];
                                var saat = isi[1];
                                var sesudah = '';
                                if (isi[2] != undefined) {
                                    sesudah = isi[2];
                                } else {
                                    if ("Kesadaran Umum" == item.parameter) {
                                        sesudah = '<div id="set_serah_'+i+'">' +
                                            '<div class="input-group" style="width: 100%">' +
                                            '<select id="serah_'+i+'" class="form-control">\n' +
                                            '<option value="">[Select One]</option>\n' +
                                            '<option value="Baik">Baik</option>\n' +
                                            '<option value="Cukup">Cukup</option>\n' +
                                            '<option value="Lemah">Lemah</option>\n' +
                                            '<option value="Jelek">Jelek</option>\n' +
                                            '</select>' +
                                            '<div onclick="saveTransferPasien(\''+item.idAsesmenKeperawatanRawatInap+'\', \''+i+'\',\''+item.parameter+'\')" class="input-group-addon" style="cursor: pointer; background-color: #449d44">' +
                                            '<i class="fa fa-check" style="color: white"></i>' +
                                            '</div>' +
                                            '</div></div>';
                                    } else if ("Resiko Jatuh" == item.parameter) {
                                        sesudah = '<div id="set_serah_'+i+'">' +
                                            '<div class="input-group" style="width: 100%">' +
                                            '<select id="serah_'+i+'" class="form-control">\n' +
                                            '<option value="">[Select One]</option>\n' +
                                            '<option value="Ringan">Ringan</option>\n' +
                                            '<option value="Sedang">Sedang</option>\n' +
                                            '<option value="Berat">Berat</option>\n' +
                                            '</select>' +
                                            '<div onclick="saveTransferPasien(\''+item.idAsesmenKeperawatanRawatInap+'\', \''+i+'\',\''+item.parameter+'\')" class="input-group-addon" style="cursor: pointer; background-color: #449d44">' +
                                            '<i class="fa fa-check" style="color: white"></i>' +
                                            '</div>' +
                                            '</div></div>';
                                    } else if ("Tekanan Darah" == item.parameter) {
                                        sesudah = '<div id="set_serah_'+i+'">' +
                                            '<div class="input-group">' +
                                            '<input id="serah_'+i+'" class="form-control" data-inputmask="\'mask\': [\'999/999\']" data-mask="">' +
                                            '<div onclick="saveTransferPasien(\''+item.idAsesmenKeperawatanRawatInap+'\', \''+i+'\', \''+item.parameter+'\')" class="input-group-addon" style="cursor: pointer; background-color: #449d44">' +
                                            '<i class="fa fa-check" style="color: white"></i>' +
                                            '</div>' +
                                            '</div></div>';
                                    } else {
                                        var tip = 'type="number"';
                                        if("Lain-Lain" == item.parameter){
                                            tip = "";
                                        }
                                        sesudah = '<div id="set_serah_'+i+'">' +
                                            '<div class="input-group">' +
                                            '<input id="serah_'+i+'" class="form-control" '+tip+'>' +
                                            '<div onclick="saveTransferPasien(\''+item.idAsesmenKeperawatanRawatInap+'\', \''+i+'\', \''+item.parameter+'\')" class="input-group-addon" style="cursor: pointer; background-color: #449d44">' +
                                            '<i class="fa fa-check" style="color: white"></i>' +
                                            '</div>' +
                                            '</div></div>';
                                    }
                                }
                                if (li != '') {
                                    body += '<tr>' +
                                        '<td width="40%">' + item.parameter + '</td>' +
                                        '<td width="20%">' + sebelum + '</td>' +
                                        '<td width="20%">' + saat + '</td>' +
                                        '<td width="20%">' + sesudah + '</td>' +
                                        '</tr>';
                                }
                            } else if ("ttd" == item.tipe) {
                                if(item.jawaban != null && item.jawaban != ''){
                                    body += '<tr>' +
                                        '<td colspan="3">' + item.parameter + '</td>' +
                                        '<td>' + '<img src="' + item.jawaban + '" style="height: 80px">' +
                                        '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerang)+'</p>' +
                                        '<p style="margin-top: -10px">'+cekItemIsNull(item.sip)+'</p>' +
                                        '</td>' +
                                        '</tr>';
                                }else{
                                    body += '<tr>' +
                                        '<td colspan="3">' + item.parameter + '</td>' +
                                        '<td>' + '<i onclick="setTtd(\''+item.idAsesmenKeperawatanRawatInap+'\', \''+jenis+'\', \'kondisi_serah_terima\')" class="fa fa-edit" style="font-size: 20px; cursor: pointer"> *klik untuk ttd</i>'+
                                        '</td>' +
                                        '</tr>';
                                }
                            }else if ("bold" == item.tipe || "first" == item.tipe) {
                                if("first" == item.tipe){
                                    if(cekSerahTerima > 0){
                                        body += '<tr bgcolor="#ffe4b5"><td colspan="4"></td></tr>' +
                                            '<tr style="font-weight: bold">' +
                                            '<td colspan="4">' + item.parameter + '</td>' +
                                            '</tr>';
                                    }else{
                                        body += '<tr style="font-weight: bold">' +
                                            '<td colspan="4">' + item.parameter + '</td>' +
                                            '</tr>';
                                    }
                                    cekSerahTerima = cekSerahTerima + 1;
                                }else{
                                    body += '<tr style="font-weight: bold">' +
                                        '<td colspan="4">' + item.parameter + '</td>' +
                                        '</tr>';
                                }
                            }else if ("action" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td colspan="3">' + jwb + '<div class="pull-right"><i style="cursor: pointer; color: red" onclick="conRI(\'' + jenis + '\',\''+item.keterangan+'\', \'' + item.idAsesmenKeperawatanRawatInap + '\')" class="fa fa-trash hvr-grow"></i></div>' +
                                    '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td colspan="3">' + jwb + '</td>' +
                                    '</tr>';
                            }
                        } else if ("add_tindakan_ina" == jenis) {
                            var del = '';
                            if("Tanggal" == item.parameter){
                                del = '<span style="margin-right: 60px;" onclick="conRI(\'' + jenis + '\',\''+item.keterangan+'\', \'' + item.idAsesmenKeperawatanRawatInap + '\')" class="pull-right"><i id="delete_' + item.idAsesmenKeperawatanRawatInap + '" class="fa fa-trash hvr-grow" style="color: red; font-size: 20px"></i></span>' +
                                    '<a style="margin-right: 7px" target="_blank" href="'+contextPath+'/rekammedik/printSuratPernyataan_rekammedik?id='+idDetailCheckup+'&tipe=INA&keterangan='+item.keterangan+'&createdDate='+converterDateTimeComplex(item.createdDate)+'" class="pull-right"><i class="fa fa-print hvr-grow" style="color: deepskyblue; font-size: 20px"></i></a>';
                            }

                            if ("colspan" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="3">' + cekNull(jwb) + '</td>' +
                                    '</tr>';
                            } else if ("info" == item.tipe) {
                                body += '<tr>' +
                                    '<td width="30%">' + cekNull(item.parameter) + '</td>' +
                                    '<td >' + cekNull(jwb) + '</td>' +
                                    '<td width="20%" align="center">' + cekIconsIsNotNull(item.informasi) + '</td>' +
                                    '</tr>';
                            } else if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="2">' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban + '" style="height: 80px">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerang)+'</p>' +
                                    '<p style="margin-top: -10px">'+cekItemIsNull(item.sip)+'</p>' +
                                    '</td>' +
                                    '</tr>';
                            } else if ("bold" == item.tipe) {
                                body += '<tr style="font-weight: bold">' +
                                    '<td width="25%">' + item.parameter + '</td>' +
                                    '<td >' + cekNull(jwb) + '</td>' +
                                    '<td width="20%" align="center">' + cekNull(item.informasi) + '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="30%">' + item.parameter + '</td>' +
                                    '<td colspan="2">' + jwb + del + '</td>' +
                                    '</tr>';
                            }
                        } else if ("transfer_external" == jenis) {
                            if ("li" == item.tipe) {
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
                            } else if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban + '" style="height: 80px">' + '</td>' +
                                    '</tr>';
                            } else if ("colspan" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="2">' + item.parameter + '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '</td>' +
                                    '</tr>';
                            }
                        } else if ("s_o" == jenis) {
                            if ("pernyataan" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="2">' + item.parameter + '</td>' +
                                    '</tr>';
                            } else if ("gambar" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban + '" style="width: 100%">' + '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '</td>' +
                                    '</tr>';
                            }
                        } else if ("a_p" == jenis) {
                            if ("pernyataan" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="2">' + item.parameter + '</td>' +
                                    '</tr>';
                            } else if ("gambar" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban + '" style="width: 100%">' + '</td>' +
                                    '</tr>';
                            } else if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban + '" style="width: 100px">' +
                                    '<p style="margin-top: -3px">' + cekItemIsNull(item.namaTerang) + '</p>' +
                                    '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                    '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '</td>' +
                                    '</tr>';
                            }
                        } else if ("hand_over_jaga" == jenis) {
                            if ("Waktu" == item.parameter) {
                                body += '<tr style="font-weight: bold">' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '<span onclick="conRI(\'' + jenis + '\',\'hand_over\', \'' + item.idAsesmenKeperawatanRawatInap + '\')" class="pull-right"><i id="delete_' + item.idAsesmenKeperawatanRawatInap + '" class="fa fa-trash hvr-grow" style="color: red"></i></span>' + '</td>' +
                                    '</tr>';
                            } else {
                                if ("bold" == item.tipe) {
                                    body += '<tr style="font-weight: bold">' +
                                        '<td width="40%">' + item.parameter + '</td>' +
                                        '<td>' + jwb + '</td>' +
                                        '</tr>';
                                } else if ("ttd" == item.tipe) {
                                    var temp = '';
                                    if(item.jawaban != '' && item.jawaban != null){
                                        temp = '<img style="width: 50%; height: 200px" src="' + item.jawaban + '">';
                                    }else{
                                        temp = '<i onclick="setTtd(\''+item.idAsesmenKeperawatanRawatInap+'\', \''+jenis+'\', \'hand_over\')" class="fa fa-pencil hvr-grow" style="cursor: pointer; color: #1ab7ea; margin-bottom: 10px"></i><br>';
                                    }
                                    body += '<tr>' +
                                        '<td>' + item.parameter + '</td>' +
                                        '<td>' + temp +
                                        '<p style="margin-top: -3px">' + cekItemIsNull(item.namaTerang) + '</p>' +
                                        '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                        '</td>' +
                                        '</tr>';
                                } else {
                                    body += '<tr>' +
                                        '<td width="40%">' + item.parameter + '</td>' +
                                        '<td>' + jwb + '</td>' +
                                        '</tr>';
                                }
                            }
                        } else if ("resiko_jatuh" == jenis || "skrining_nutrisi" == jenis) {
                            if ("total" == item.tipe) {
                                body += '<tr>' +
                                    '<td width="40%" colspan="2">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '</td>' +
                                    '</tr>';
                            } else if ("kesimpulan" == item.tipe) {
                                body += '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                                    '<td colspan="2">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '</td>' +
                                    '<td>' + cekItemIsNull(item.skor) + '</td>' +
                                    '</tr>';
                            }
                        } else {
                            if ("pernyataan" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="2">' + item.parameter + '</td>' +
                                    '</tr>';
                            } else if ("gambar" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban + '" style="width: 100%">' + '</td>' +
                                    '</tr>';
                            } else if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban + '" style="width: 100px">' +
                                    '<p style="margin-top: -3px">' + cekItemIsNull(item.namaTerang) + '</p>' +
                                    '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                    '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '</td>' +
                                    '</tr>';
                            }
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
                            '<td><b>Saat perjalanan</b></td>' +
                            '<td width="25%"><b>Saat diterima</b></td>' +
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
                $('[data-mask]').inputmask();
            });
        }
    }
}

function delRowAsesmenRawatInap(id) {
    $('#del_ina_' + id).remove();
    var url = "";
    if(id == 'hand_over_jaga' || id == 'add_transfer_pasien'){
        url = contextPath + '/pages/images/icons8-add-list-25.png';
    }else{
        url = contextPath + '/pages/images/icons8-plus-25.png';
    }
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
    if(!cekSession()){
        var data = "";
        var va1 = $('#cp1').val();
        var va2 = $('#cp2').val();
        var va3 = $('#cp3').val();
        var va5 = $('#cp5').val();
        var v6 = document.getElementById("cp6");
        var v7 = document.getElementById("cp7");
        var va6 = isCanvasBlank(v6);
        var va7 = isCanvasBlank(v7);

        if (va1 && va2 && va3 && va5 != '' && !va6 && !va7) {

            var ttd1 = v6.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            var ttd2 = v7.toDataURL("image/png"),
                ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

            data = {
                'id_detail_checkup': idDetailCheckup,
                'waktu': va1.split("-").reverse().join("-") + ' ' + va2 + ':00',
                'ppa': va3,
                'jenis': '',
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
}

function listCatatanTerintegrasi() {
    if(!cekSession()){
        var table = "";
        CatatanTerintegrasiAction.getListCatatanTerintegrasi(idDetailCheckup, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    table += '<tr>' +
                        '<td>' + converterDateTime(item.waktu) + '</td>' +
                        '<td>' + cekNull(item.ppa) + '</td>' +
                        '<td>' + cekNull(item.intruksi) + '</td>' +
                        '<td>' + '<img style="width: 100%; height: 20px" src="' + item.ttdPetugas + '">' + '</td>' +
                        '<td>' + '<img style="width: 100%; height: 20px" src="' + item.ttdDpjp + '">' + '</td>' +
                        '</tr>';
                });
                $('#body_catatan_integrasi').html(table);
            }
        });
    }
}

function saveCatatanPemberianObat(jenis, ket) {
    if(!cekSession()){
        var dataPasien = "";
        dataPasien = {
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'id_pasien': idPasien,
            'id_rm': tempidRm
        }
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
        var va9 = isCanvasBlank(v9);
        var va10 = isCanvasBlank(v10);
        var nama1 = $('#nama_terang_cpo9').val();
        var nama2 = $('#nama_terang_cpo10').val();
        var sip1 = $('#sip_cpo9').val();
        var nama1 = $('#nama_terang_cpo9').val();
        var nama2 = $('#nama_terang_cpo10').val();
        var sip1 = $('#sip_cpo9').val();

        if (nama1 && nama2 && sip1 && va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && nama1 && nama2 && sip1 != '' && !va9 && !va10) {

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
                'ttd_apoteker': ttd2,
                'nama_terang_dokter':nama1,
                'nama_terang_perawat':nama2,
                'sip_dokter':sip1
            }

            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);

            $('#save_ina_' + jenis).hide();
            $('#load_ina_' + jenis).show();
            dwr.engine.setAsync(true);
            CatatanPemberianObatAction.saveCatatanPemberianObat(result, pasienData, {
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
}

function listCatatanPemberianObat() {
    var jeniscpo = "";
    if(jenisCPO != undefined){
        jeniscpo = jenisCPO;
    }
    if(!cekSession()){
        var table = "";
        CatatanPemberianObatAction.getListCatatanPemberianObat(idDetailCheckup, jeniscpo, function (res) {
            if (res.length > 0) {
                var wawak = "";
                $.each(res, function (i, item) {
                    var action = '';
                    var ttd = '';
                    if(wawak != item.waktu){
                        wawak = item.waktu;
                        action = '<i onclick="actionCPO(\''+item.idCatatanPemberianObat+'\', \''+item.waktu+'\')" class="fa fa-edit" style="font-size: 20px; cursor: pointer"></i>';
                        if("perawat" == item.jenis){
                            if(item.ttdApoteker != null && item.ttdDokter){
                                ttd = '<span>Keluarga</span><br>'+
                                    '<img style="width: 70%; height: 50px" src="' + item.ttdDokter + '">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerangDokter)+'</p>' +
                                    '<p>-----------------------------------------</p>'+
                                    '<span>Perawat</span><br>'+
                                    '<img style="width: 70%; height: 50px" src="' + item.ttdApoteker + '">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerangPerawat)+'</p>' +
                                    '<p style="margin-top: -9px;">'+cekItemIsNull(item.sipPerawat)+'</p>';
                            }
                        }else{
                            if(item.ttdApoteker != null && item.ttdDokter){
                                ttd = '<span>Petugas Farmasi</span><br>'+
                                    '<img style="width: 70%; height: 50px" src="' + item.ttdDokter + '">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerangDokter)+'</p>' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.sipDokter)+'</p>' +
                                    '<p>-----------------------------------------</p>'+
                                    '<span>Perawat</span><br>'+
                                    '<img style="width: 70%; height: 50px" src="' + item.ttdApoteker + '">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerangPerawat)+'</p>' +
                                    '<p style="margin-top: -9px;">'+cekItemIsNull(item.sipPerawat)+'</p>';
                            }
                        }
                    }

                    var ket = "";
                    if(item.keterangan != null && item.keterangan != ''){
                        var li = "";
                        var plit = item.keterangan.split("|");
                        $.each(plit, function (i, item) {
                           li += '<li>'+item+' <i class="fa fa-check" style="color: green"></i></li>';
                        });
                        if(li != ''){
                            ket = '<ul style="margin-left: 20px">'+li+'</ul>';
                        }
                    }
                    table += '<tr>' +
                        '<td>' + item.waktu + '</td>' +
                        '<td>' + cekNull(item.namaObat) + '</td>' +
                        '<td>' + cekNull(item.aturanPakai) + '</td>' +
                        '<td>' + ket + '</td>' +
                        '<td align="center">' + ttd +'</td>' +
                        '<td align="center">' + action + '</td>' +
                        '</tr>';
                });
                $('#body_catatan_pemberian').html(table);
            }
        });
    }
}

function actionCPO(id, waktu){
    $('#jam').val(waktu);
    $('#tipe_cpo').val(jenisCPO);
    if(jenisCPO == "apoteker"){
        $('#form_apoteker').show();
        $('#form_perawat').hide();
        $('#title_ttd').text("Petugas Farmasi");
    }else{
        $('#form_apoteker').hide();
        $('#form_perawat').show();
        $('#title_ttd').text("TTD Keluarga");
    }
    $('#modal-ina-action_pemberian_obat').modal({show:true, backdrop:'static'});
    $('#save_ina_action_pemberian_obat').attr('onclick', 'saveCOP(\''+id+'\')');
}

function saveCOP(id){
    var centeng = $('[name=centangan]');
    var ttdPasien = document.getElementById("cpo9");
    var ttdPerawat = document.getElementById("cpo10");
    var nama1 = $('#nama_terang_cpo9').val();
    var nama2 = $('#nama_terang_cpo10').val();
    var sip2 = $('#nip_cpo10').val();
    var cek1 = isCanvasBlank(ttdPasien);
    var cek2 = isCanvasBlank(ttdPerawat);
    var waktu = $('#jam').val();

    var apoteker = $('#nama_terang_apoteker').val();
    var sipApoteker = $('#sip_apoteker').val();

    var temp = "";
    $.each(centeng, function (i, item) {
        if(item.checked){
            if(temp != ''){
                temp = temp +'|'+item.value;
            }else{
                temp = item.value;
            }
        }
    });

    var ttd1 = ttdPasien.toDataURL("image/png"),
        ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
    var ttd2 = ttdPerawat.toDataURL("image/png"),
        ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

    var namanama = nama1;
    var jenCPO = $('#tipe_cpo').val();
    if("apoteker" == jenCPO){
        namanama = apoteker;
    }
    var data = {
        'id': id,
        'ttd_keluarga': ttd1,
        'ttd_perawat': ttd2,
        'nama1': nama1,
        'nama2': nama2,
        'sip1': sipApoteker,
        'sip2': sip2,
        'keterangan': temp,
        'waktu': waktu
    }

    if(id != '' && !cek1 && !cek2 && namanama && nama2 && sip2 && waktu && temp != ''){
        $('#save_ina_action_pemberian_obat').hide();
        $('#load_ina_action_pemberian_obat').show();
        var result = JSON.stringify(data);
        dwr.engine.setAsync(true);
        CatatanPemberianObatAction.saveUpdate(result, {
            callback:function (res) {
                if(res.status = 'success'){
                    $('#modal-ina-action_pemberian_obat').modal('hide');
                    $('#save_ina_action_pemberian_obat').show();
                    $('#load_ina_action_pemberian_obat').hide();
                    $('#success_ina_action_pemberian_obat').show().fadeOut(5000);
                    $('#msg_success_ina_action_pemberian_obat').text("Data berhasil tersimpan...!");
                    listCatatanPemberianObat();
                }else{
                    $('#warning_ina_action_pemberian_obat').show().fadeOut(5000);
                    $('#msg_ina_action_pemberian_obat').text(res.msg);
                }
            }
        });
    }else{
        $('#warning_ina_action_pemberian_obat').show().fadeOut(5000);
        $('#msg_ina_action_pemberian_obat').text("Silahkan cek kembali inputan anda...!");
    }
}

function saveAsuhanKeperawatan(jenis, ket) {

    var data = [];
    var dataPasien = "";

    dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var diagnosis = $('[name=diag]');
    var hasil = $('[name=hasil]');
    var inter = $('[name=inter]');
    var imple = $('[name=imple]');
    var eva = $('[name=eva]');
    var ttdPerawat = document.getElementById("ttd_perawat");
    var tgl = $('.tgl').val();
    var jam = $('.jam').val();
    var namaTerang = $('#nama_terang_perawat').val();

    var tempDiag = "";
    var tempHasil = "";
    var tempInter = "";
    var tempImple = "";
    var tempEva = "";

    $.each(diagnosis, function (i, item) {
        var val = "";
        if (item.type == 'checkbox') {
            if (item.checked) {
                val = item.value;
            }
        } else {
            if (item.value != '') {
                val = item.value;
            }
        }

        if (val != '') {
            if (tempDiag != '') {
                tempDiag = tempDiag + '|' + val;
            } else {
                tempDiag = val;
            }
        }
    });

    $.each(hasil, function (i, item) {
        var val = "";
        if (item.type == 'checkbox') {
            if (item.checked) {
                val = item.value;
            }
        } else {
            if (item.value != '') {
                val = item.value;
            }
        }

        if (val != '') {
            if (tempHasil != '') {
                tempHasil = tempHasil + '|' + val;
            } else {
                tempHasil = val;
            }
        }
    });

    $.each(inter, function (i, item) {
        var val = "";
        if (item.type == 'checkbox') {
            if (item.checked) {
                val = item.value;
            }
        } else {
            if (item.value != '') {
                val = item.value;
            }
        }

        if (val != '') {
            if (tempInter != '') {
                tempInter = tempInter + '|' + val;
            } else {
                tempInter = val;
            }
        }
    });

    $.each(imple, function (i, item) {
        var val = "";
        if (item.type == 'checkbox') {
            if (item.checked) {
                val = item.value;
            }
        } else {
            if (item.value != '') {
                val = item.value;
            }
        }

        if (val != '') {
            if (tempImple != '') {
                tempImple = tempImple + '|' + val;
            } else {
                tempImple = val;
            }
        }
    });

    $.each(eva, function (i, item) {
        var val = "";
        if (item.type == 'checkbox') {
            if (item.checked) {
                val = item.value;
            }
        } else {
            if (item.value != '') {
                val = item.value;
            }
        }

        if (val != '') {
            if (tempEva != '') {
                tempEva = tempEva + '|' + val;
            } else {
                tempEva = val;
            }
        }
    });

    var cekTtd = isCanvasBlank(ttdPerawat);

    if (tgl && jam && tempDiag && tempHasil && tempInter && tempImple && tempEva && namaTerang != '' && !cekTtd) {

        var ttd = ttdPerawat.toDataURL("image/png"),
            ttd = ttd.replace(/^data:image\/(png|jpg);base64,/, "");

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': tgl + ' ' + jam,
            'diagnosa': tempDiag,
            'hasil': tempHasil,
            'intervensi': tempInter,
            'implementasi': tempImple,
            'evaluasi': tempEva,
            'keterangan': jenis,
            'nama_terang': namaTerang,
            'ttd_perawat': ttd
        }

        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            $('#save_ina_' + jenis).hide();
            $('#load_ina_' + jenis).show();
            dwr.engine.setAsync(true);
            RencanaAsuhanKeperawatanAction.save(result, pasienData, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_ina_' + jenis).show();
                        $('#load_ina_' + jenis).hide();
                        $('#modal-ina-' + jenis).modal('hide');
                        $('#warning_ina_' + ket).show().fadeOut(5000);
                        $('#msg_ina_' + ket).text("Berhasil menambahkan data ....");
                        $('#modal-ina-' + jenis).scrollTop(0);
                        listAsuhanKeperawatan(ket);
                    } else {
                        $('#save_ina_' + jenis).show();
                        $('#load_ina_' + jenis).hide();
                        $('#warning_ina_' + jenis).show().fadeOut(5000);
                        $('#msg_ina_' + jenis).text(res.msg);
                        $('#modal-ina-' + jenis).scrollTop(0);
                    }
                }
            });
        }
    } else {
        $('#save_ina_' + jenis).show();
        $('#load_ina_' + jenis).hide();
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function listAsuhanKeperawatan(jenis) {
    if(!cekSession()){
        var table = "";
        RencanaAsuhanKeperawatanAction.getListDetail(idDetailCheckup, jenis + '_keperawatan_ina', function (res) {
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

                    if (item.ttdPerawat != null) {
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
    var va9 = isCanvasBlank(v9);
    var va10 = isCanvasBlank(v10);

    var keluarga = $('#keluarga').val();
    var apoteker = $('#apoteker').val();
    var sip = $('#sip_apoteker').val();

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
            'ttd_apoteker': ttd2,
            'keluarga': keluarga,
            'apoteker': apoteker,
            'sip': sip
        }

        if(!cekSession()){
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
        }
    } else {
        $('#save_ina_' + jenis).show();
        $('#load_ina_' + jenis).hide();
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function listRekonsiliasiObat() {
    if(!cekSession()){
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
                        '<td>' + converterDate(item.tanggal) + '</td>' +
                        '<td>' + cekNull(item.ruangan) + '</td>' +
                        '<td>' + cekNull(item.namaObat) + '</td>' +
                        '<td>' + cekNull(item.dosis) + '</td>' +
                        '<td>' + cekNull(item.aturanPakai) + '</td>' +
                        '<td>' + cekNull(item.indikasi) + '</td>' +
                        '<td align="center">' + ya + '</td>' +
                        '<td align="center">' + tidak + '</td>' +
                        '<td align="center">' +
                        '<img style="width: 70px; height: 50px" src="' + item.ttdPasien + '">' +
                        '<p>'+item.keluarga+'</p>'+
                        '</td>' +
                        '<td align="center">' +
                        '<img style="width: 70px; height: 50px" src="' + item.ttdApoteker + '">' +
                        '<p>'+item.apoteker+'</p>'+
                        '<p>'+item.sip+'</p>'+
                        '</td>' +
                        '</tr>';
                });
                $('#body_rekonsiliasi').html(table);
            }
        });
    }
}

function saveEdukasiPasien(jenis, ket) {
    var data = [];
    var cek = false;
    var tgl = $('.tgl').val();
    var jam = $('.jam').val();

    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    };

    var nama1 = $('#nama_terang_pasien').val();
    var nama2 = $('#nama_terang_dokter').val();
    var sip2 = $('#sip_dokter').val();

    var ttd1 = document.getElementById("ttd_pasien_" + jenis);
    var ttd2 = document.getElementById("ttd_staf_" + jenis);
    var cek1 = isCanvasBlank(ttd1);
    var cek2 = isCanvasBlank(ttd2);

    var canv1 = ttd1.toDataURL("image/png"),
        canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

    var canv2 = ttd2.toDataURL("image/png"),
        canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");

    var pertanyaan = $('#p_' + jenis).val();
    var jawaban = $('#j_' + jenis).val();

    var table = $('#table_' + jenis).tableToJSON();

    $.each(table, function (i, item) {

        var dw = $('[name=dw_' + jenis + i + ']:checked').val();
        var pa = $('[name=pa_' + jenis + i + ']:checked').val();
        var me = $('[name=me_' + jenis + i + ']:checked').val();
        var med = $('[name=med_' + jenis + i + ']:checked').val();
        var ev = $('[name=ev_' + jenis + i + ']:checked').val();
        var edu = $('#edu' + jenis + i).val();
        var edukator = "";

        if (tgl && jam != '') {
            edukator = tgl + ' ' + jam;
        }

        if (i == 0) {
            if (dw && pa && me && med && ev && edu != undefined && edu && tgl && jam != '') {
                data.push({
                    'edukator': edukator,
                    'durasi': dw + ' menit',
                    'edukasi': edu,
                    'pemahaman_awal': pa,
                    'metode_edukasi': me,
                    'media_edukasi': med,
                    'evaluasi': ev,
                    'ttd_pasien': canv1,
                    'ttd_staf': canv2,
                    'nama_terang': nama1,
                    'nama_staf': nama2,
                    'sip': sip2,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
            } else {
                cek = false;
            }
        } else {
            if (dw && pa && me && med && ev && edu != undefined && edu != '') {
                data.push({
                    'durasi': dw + ' menit',
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

        if (pertanyaan && jawaban != '') {
            data.push({
                'edukasi': pertanyaan,
                'pemahaman_awal': jawaban,
                'keterangan': jenis,
                'tipe': 'Q',
                'id_detail_checkup': idDetailCheckup
            });
        }

        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            $('#save_ina_' + jenis).hide();
            $('#load_ina_' + jenis).show();
            dwr.engine.setAsync(true);
            EdukasiPasienAction.save(result, pasienData, {
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
        }
    } else {
        $('#save_ina_' + jenis).show();
        $('#load_ina_' + jenis).hide();
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function listEdukasiPasien(jenis) {
    if(!cekSession()){
        var head = "";
        var first = "";
        var body = "";
        var last = "";
        EdukasiPasienAction.getListDetail(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                var rowSpan = res.length;
                $.each(res, function (i, item) {
                    var edu = "";
                    var ttdPasien = "";
                    var ttdStaf = "";
                    if (item.ttdPasien != null && item.ttdPasien != '') {
                        ttdPasien = '<img src="' + item.ttdPasien + '" style="width: 50px">' +
                            '<p>' + cekNull(item.namaTerang) + '</p>';
                    }
                    if (item.ttdStaf != null && item.ttdStaf != '') {
                        ttdStaf = '<img src="' + item.ttdStaf + '" style="width: 50px">' +
                            '<p>' + cekNull(item.namaStaf) + '</p>' +
                            '<p>' + cekNull(item.sip) + '</p>';
                    }

                    if ("Q" == item.tipe) {
                        body += '<tr>' + '<td colspan="9">' + '<p>' + cekNull(item.edukasi) + '</p><p>' + cekNull(item.pemahamanAwal) + '</p>' + '</td>' +
                            '</tr>';
                    } else {
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
                    '<td align="center" style="vertical-align: middle">Tanggal/Jam</td>' +
                    '<td align="center" style="vertical-align: middle">Durasi</td>' +
                    '<td align="center" style="vertical-align: middle" >Pelaksana Edukasi</td>' +
                    '<td align="center" style="vertical-align: middle">Pemahaman Awal</td>' +
                    '<td align="center" style="vertical-align: middle">Metode Edukasi</td>' +
                    '<td align="center" style="vertical-align: middle">Media Edukasi</td>' +
                    '<td align="center" style="vertical-align: middle">Evaluasi/ Verifikasi</td>' +
                    '<td align="center" style="vertical-align: middle">TTD Pasien</td>' +
                    '<td align="center" style="vertical-align: middle">TTD Staf</td>' +
                    '</tr>';
            } else {
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

function radioEdukasiPasien(jenis) {
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
        '    <div class="col-md-10"><input class="form-control" id="p_' + jenis + '"></div>\n' +
        '</div>\n' +
        '</div>\n' +
        '<div class="row jarak">\n' +
        '<div class="form-group">\n' +
        '    <label class="col-md-2">Pasien/Keluarga</label>\n' +
        '    <div class="col-md-10"><input class="form-control" id="j_' + jenis + '"></div>\n' +
        '</div>\n' +
        '</div>';

    var ttd = '<div class="row">\n' +
        '    <div class="col-md-3">\n' +
        '        <div class="form-group">\n' +
        '            <label style="margin-left: 8px">TTD Pasien</label>\n' +
        '            <canvas class="paint-canvas-ttd" id="ttd_pasien_' + jenis + '" width="190"\n' +
        '               onmouseover="paintTtd(\'ttd_pasien_' + jenis + '\')"></canvas>\n' +
        '<input class="form-control nama-pasien" id="nama_terang_pasien" placeholder="Nama Terang">' +
        '            <button style="margin-left: 8px" type="button" class="btn btn-danger"\n' +
        '                   onclick="removePaint(\'ttd_pasien_' + jenis + '\')"><i class="fa fa-trash"></i>\n' +
        '                Clear\n' +
        '            </button>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <div class="col-md-3">\n' +
        '        <div class="form-group">\n' +
        '            <label style="margin-left: 8px">TTD Staf</label>\n' +
        '            <canvas class="paint-canvas-ttd" id="ttd_staf_' + jenis + '" width="190"\n' +
        '               onmouseover="paintTtd(\'ttd_staf_' + jenis + '\')"></canvas>\n' +
        '<input class="form-control nama_petugas" id="nama_terang_dokter" placeholder="Nama Terang">\n' +
        '<input style="margin-top: 3px" class="form-control nip_petugas" id="sip_dokter" placeholder="NIP/SIP">' +
        '            <button style="margin-left: 8px" type="button" class="btn btn-danger"\n' +
        '                   onclick="removePaint(\'ttd_staf_' + jenis + '\')"><i class="fa fa-trash"></i>\n' +
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

    if ("ept_tppri" == jenis) {
        edukasi.push({'edukasi': 'Hak dan Kewajiban Pasien dan Keluarga'});
        edukasi.push({'edukasi': 'Peraturan dan Tataterbit RS'});
        edukasi.push({'edukasi': 'Fasilitas kamar, jam kunjungan dan visite Pasien'});
    }
    if ("ept_perawat" == jenis) {
        edukasi.push({'edukasi': 'Manajemen Nyeri, Hand Hygiene, Informasi Fungsi Identitas Pasien'});
        edukasi.push({'edukasi': 'Pemberi informasi pasang (infus, cateter, NGT), pencegahan resiko jatuh'});
        edukasi.push({'edukasi': 'Fasilitas kamar, Jam kunjung dan visite Pasien'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
    }
    if ("ept_igd" == jenis) {
        edukasi.push({'edukasi': 'Penjelasan Pemakaian Gelang Identitas'});
        edukasi.push({'edukasi': 'Resiko Jatuh'});
        edukasi.push({'edukasi': 'Hak Kamar/Selisih/Asuransi'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
    }
    if ("ept_dokter_igd" == jenis) {
        edukasi.push({'edukasi': 'plc|Diagnosa Masuk'});
        edukasi.push({'edukasi': 'plc|Rencana Pelayanan Dan Tindakan'});
        edukasi.push({'edukasi': 'plc|DPJP yang merawat Dr.'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
    }
    if ("ept_dpjp" == jenis) {
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
    if ("ept_nutrisionis" == jenis) {
        edukasi.push({'edukasi': 'plc|Program diet dan nutrisi'});
        edukasi.push({'edukasi': 'Pengelolaan makanan dari luar RS'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
    }
    if ("ept_farmasi" == jenis) {
        edukasi.push({'edukasi': 'Aturan, tata cara pemakain obat yang efektif dan aman'});
        edukasi.push({'edukasi': 'Potensi efek samping obat yang diberikan'});
        edukasi.push({'edukasi': 'Potensi interaksi obat dengan obat lain dan atau dengan makanan'});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
    }
    if ("ept_rehabilitasi_medis" == jenis) {
        edukasi.push({'edukasi': 'Program dan teknik fisioterapi'});
        edukasi.push({'edukasi': 'Penerapan dan keamanan alat terapi'});
        edukasi.push({'edukasi': 'Terapi akupasi'});
        edukasi.push({'edukasi': 'Terapi wicara'});
        edukasi.push({'edukasi': 'Latihan menelan'});
        edukasi.push({'edukasi': 'Pemeliharaan, pemakaian dan kecemasan orthose protesa'});
    }
    if ("ept_dokter" == jenis) {
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
    if ("ept_radiografer" == jenis) {
        edukasi.push({'edukasi': 'plc|Pemeriksaan Radiologi'});
        edukasi.push({'edukasi': 'plc|Penerimaan dosis radiasi'});
        edukasi.push({'edukasi': 'plc|Manfaat dan resiko'});
    }
    if ("ept_ppa" == jenis) {
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
        edukasi.push({'edukasi': ''});
    }

    if (edukasi.length > 0) {
        $.each(edukasi, function (i, item) {
            data.push({
                'durasi': '3|5|10',
                'edukasi': item.edukasi,
                'pemahaman': 'Baik|Cukup|Kurang',
                'metode': 'Wawancara|Diskusi|Ceramah|Demonstrasi',
                'media': 'Lisan|Leaflet|Brosur|Lembar Balik',
                'evaluasi': 'Mengerti|Re Edukasi|Re Demontrasi'
            });
        });
    }

    if (data.length > 0) {
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

            var idEdu = 'edu' + jenis + i;

            if (edukas.length > 1) {
                var edu1 = edukas[1];
                tdEdukasi += '<td><input style="font-size: 12px" class="form-control" id="edu' + jenis + i + '" placeholder="' + edu1 + '" onchange="$(\'#' + idEdu + '\').val(\'' + edu1 + '\'+\'' + " " + '\'+this.value)"></td>';
            } else {
                if (item.edukasi != '') {
                    tdEdukasi += '<td><input type="hidden" id="edu' + jenis + i + '" value="' + item.edukasi + '">' + item.edukasi + '</td>';
                } else {
                    tdEdukasi += '<td><input style="font-size: 12px" class="form-control" id="edu' + jenis + i + '"></td>';
                }
            }

            $.each(durasi, function (idx, item) {
                tdDurasi += '<td align="center" style="vertical-align: middle"><div class="custom03">' +
                    '<input type="radio" value="' + item + '" id="dw_' + jenis + i + idx + '" name="dw_' + jenis + i + '" /><label for="dw_' + jenis + i + idx + '"></label>' +
                    '</div></td>';
            });
            $.each(pemahaman, function (idx, item) {
                tdPemahaman += '<td align="center" style="vertical-align: middle"><div class="custom03">' +
                    '<input type="radio" value="' + item + '" id="pa_' + jenis + i + idx + '" name="pa_' + jenis + i + '" /><label for="pa_' + jenis + i + idx + '"></label>' +
                    '</div></td>';
            });
            $.each(metode, function (idx, item) {
                tdMetode += '<td align="center" style="vertical-align: middle"><div class="custom03">' +
                    '<input type="radio" value="' + item + '" id="me_' + jenis + i + idx + '" name="me_' + jenis + i + '" /><label for="me_' + jenis + i + idx + '"></label>' +
                    '</div></td>';
            });
            $.each(media, function (idx, item) {
                tdMedia += '<td align="center" style="vertical-align: middle"><div class="custom03">' +
                    '<input type="radio" value="' + item + '" id="med_' + jenis + i + idx + '" name="med_' + jenis + i + '" /><label for="med_' + jenis + i + idx + '"></label>' +
                    '</div></td>';
            });
            $.each(evaluasi, function (idx, item) {
                tdEvaluasi += '<td align="center" style="vertical-align: middle"><div class="custom03">' +
                    '<input type="radio" value="' + item + '" id="ev_' + jenis + i + idx + '" name="ev_' + jenis + i + '" /><label for="ev_' + jenis + i + idx + '"></label>' +
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

        $('#q_' + jenis).html(question);
        $('#temp_' + jenis).html(ttd);
        $('#head_' + jenis).html(head);
        $('#body_' + jenis).html(body);

        $('.jam').timepicker();
        $('.jam').val(converterTime(new Date()));
        $('.tgl').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tgl').val(converterDate(new Date()));
        $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
    }
}

function searchDiagnosaAskep(id, tipe) {
    var menus, mapped;
    $('#' + id).typeahead({
        minLength: 3,
        source: function (query, process) {
            menus = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            RencanaAsuhanKeperawatanAction.getDiagnosaAskep(query, tipe, function (listdata) {
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
                if (res.length > 0) {

                    var diagnosis = "";
                    var hasil = "";
                    var intervensi = "";
                    var implementasi = "";
                    var evaluasi = "";

                    $.each(res, function (i, item) {
                        if (item.diagnosis != null && item.diagnosis != '') {
                            if (item.keteranganDiagnosis == "P") {
                                diagnosis += '<p class="jarak">' + item.diagnosis + '</p><input type="hidden" value="' + item.diagnosis + '" name="diag">';
                            } else if (item.keteranganDiagnosis == "I") {
                                diagnosis += '<input autocomplete="off" style="font-size: 12px" class="form-control jarak" name="diag" id="diag' + i + '" placeholder="' + item.diagnosis + '" onchange="$(\'#diag' + i + '\').val(\'' + item.diagnosis + '\'+\'' + " " + '\'+this.value)">';
                            } else if (item.keteranganDiagnosis == "R") {
                                diagnosis += '<div class="row">' +
                                    '<div class="col-md-1">' +
                                    '<div class="custom02">\n' +
                                    '<input type="radio" value="(P) ' + item.diagnosis + '" id="diag' + i + '1" name="diag"/><label for="diag' + i + '1">P</label>\n' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="col-md-1">' +
                                    '<div class="custom02">\n' +
                                    '<input type="radio" value="(S) ' + item.diagnosis + '" id="diag' + i + '2" name="diag"/><label for="diag' + i + '2">S</label>\n' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="col-md-1">' +
                                    '<div class="custom02">\n' +
                                    '<input type="radio" value="(M) ' + item.diagnosis + '" id="diag' + i + '3" name="diag"/><label for="diag' + i + '3">M</label>\n' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="row">' +
                                    '<div class="col-md-12">' +
                                    '<label><b>' + item.diagnosis + '</b></label>' +
                                    '</div>' +
                                    '</div>';
                            } else {
                                diagnosis +=
                                    '<div class="row">' +
                                    '<div class="form-check02">\n' +
                                    '<input type="checkbox" name="diag" id="diag' + i + '" value="' + item.diagnosis + '">\n' +
                                    '<label for="diag' + i + '"></label> ' + item.diagnosis + '\n' +
                                    '</div>' +
                                    '</div>';
                            }
                        }

                        if (item.hasil != null && item.hasil != '') {
                            if (item.keteranganHasil == "P") {
                                hasil += '<p class="jarak">' + item.hasil + '</p><input type="hidden" value="' + item.hasil + '" name="hasil">';
                            } else if (item.keteranganHasil == "I") {
                                hasil += '<input autocomplete="off" style="font-size: 12px" class="form-control jarak" name="hasil" id="hasil' + i + '" placeholder="' + item.hasil + '" onchange="$(\'#hasil' + i + '\').val(\'' + item.hasil + '\'+\'' + " " + '\'+this.value)">';
                            } else {
                                hasil +=
                                    '<div class="row">' +
                                    '<div class="form-check02">\n' +
                                    '<input type="checkbox" name="hasil" id="hasil' + i + '" value="' + item.hasil + '">\n' +
                                    '<label for="hasil' + i + '"></label> ' + item.hasil + '\n' +
                                    '</div>' +
                                    '</div>';
                            }
                        }

                        if (item.intervensi != null && item.intervensi != '') {
                            if (item.keteranganIntervensi == "P") {
                                intervensi += '<p class="jarak">' + item.intervensi + '</p><input type="hidden" value="' + item.intervensi + '" name="inter">';
                            } else if (item.keteranganIntervensi == "I") {
                                intervensi += '<input style="font-size: 12px" class="form-control jarak" name="inter" id="inter' + i + '" placeholder="' + item.intervensi + '" onchange="$(\'#inter' + i + '\').val(\'' + item.intervensi + '\'+\'' + " " + '\'+this.value)">';
                            } else {
                                intervensi +=
                                    '<div class="row">' +
                                    '<div class="form-check02">\n' +
                                    '<input type="checkbox" name="inter" id="inter' + i + '" value="' + item.intervensi + '">\n' +
                                    '<label for="inter' + i + '"></label> ' + item.intervensi + '\n' +
                                    '</div>' +
                                    '</div>';
                            }
                        }

                        if (item.implementasi != null && item.implementasi != '') {
                            if (item.keteranganImplementasi == "P") {
                                implementasi += '<p class="jarak">' + item.implementasi + '</p><input type="hidden" value="' + item.implementasi + '" name="imple">';
                            } else if (item.keteranganImplementasi == "I") {
                                implementasi += '<input style="font-size: 12px" class="form-control jarak" name="imple" id="imple' + i + '" placeholder="' + item.implementasi + '" onchange="$(\'#imple' + i + '\').val(\'' + item.implementasi + '\'+\'' + " " + '\'+this.value)">';
                            } else {
                                implementasi +=
                                    '<div class="row">' +
                                    '<div class="form-check02">\n' +
                                    '<input type="checkbox" name="imple" id="imple' + i + '" value="' + item.implementasi + '">\n' +
                                    '<label for="imple' + i + '"></label> ' + item.implementasi + '\n' +
                                    '</div>' +
                                    '</div>';
                            }
                        }

                        if (item.evaluasi != null && item.evaluasi != '') {
                            if (item.keteranganEvaluasi == "P") {
                                evaluasi += '<p class="jarak">' + item.evaluasi + '</p><input type="hidden" value="' + item.evaluasi + '" name="eva">';
                            } else if (item.keteranganEvaluasi == "I") {
                                evaluasi += '<input autocomplete="off" style="font-size: 12px" class="form-control jarak" name="eva" id="eva' + i + '" placeholder="' + item.evaluasi + '" onchange="$(\'#eva' + i + '\').val(\'' + item.evaluasi + '\'+\'' + " " + '\'+this.value)">';
                            } else {
                                evaluasi +=
                                    '<div class="row">' +
                                    '<div class="form-check02">\n' +
                                    '<input type="checkbox" name="eva" id="eva' + i + '" value="' + item.evaluasi + '">\n' +
                                    '<label for="eva' + i + '"></label> ' + item.evaluasi + '\n' +
                                    '</div>' +
                                    '</div>';
                            }
                        }
                    });

                    var labelTtd = "Perawat";
                    if ("rb" == tipe) {
                        labelTtd = "Bidan";
                    }

                    evaluasi += '<div class="row" style="margin-top: 20px">\n' +
                        '<div class="form-group">\n' +
                        '        <div class="col-md-12">\n' +
                        '            <label style="margin-left: 7px"><b>' + labelTtd + '</b></label>\n' +
                        '            <canvas style="margin-left: 7px; margin-top: -7px" width="150" onmouseover="paintTtd(\'ttd_perawat\')" class="paint-canvas-ttd" id="ttd_perawat"></canvas>' +
                        '            <input class="form-control" id="nama_terang_perawat" placeholder="Nama Terang">' +
                        '            <input autocomplete="off" class="form-control" id="nip_perawat" placeholder="NIP">' +
                        '            <button style="margin-left: 7px" type="button" class="btn btn-danger" onclick="removePaint(\'ttd_perawat\')"><i class="fa fa-trash"></i> Clear\n' +
                        '            </button>\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div>';

                    if ("all" == tipe || "rb" == tipe) {
                        $('#dia').html(diagnosis);
                        $('#has').html(hasil);
                        $('#pla').html(intervensi);
                        $('#imp').html(implementasi);
                        $('#eva').html(evaluasi);
                    }

                    if ("icu" == tipe) {

                        var option1 = "";
                        var option2 = "<option value=''>[Select One]</option>";

                        $.each(dataTindakan(), function (i, item) {
                            option1 += '<option value="' + item.tindakan + '">' + item.tindakan + '</option>';
                        });

                        $.each(dataHeadSubjektif(), function (i, item) {
                            option2 += '<option value="' + item.id + '">' + item.isi + '</option>';
                        });

                        $('#dia').html(diagnosis);
                        $('#askep_subjek').html(option2);
                        $('#rec').html(intervensi);
                        $('#tin').html(option1);
                    }

                } else {
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

    var sel = $('.select2').length;
    if (sel > 0) {
        $('.select2').select2();
    }
}

function setSubjektif(val) {
    var temp = "";
    $.each(dataSubjektif(val), function (i, item) {

        var con =
            '<div class="col-md-3">\n' +
            '<label>' + item.subjektif + '</label><input type="hidden" value="' + item.subjektif + '" name="params">' +
            '</div>';

        var con2 = "";
        var content = item.content;
        var ctn = content.split("|");

        $.each(ctn, function (idx, it) {

            if (item.keterangan == "c") {

                if (idx <= 3) {
                    con2 += '<div class="col-md-2">\n' +
                        '<div class="form-check02">\n' +
                        '<input type="checkbox" name="subjek' + i + '" id="subjek' + idx + i + '" value="' + it + '">\n' +
                        '<label for="subjek' + idx + i + '"></label> ' + it + '\n' +
                        '</div>' +
                        '</div>';
                } else if (idx > 3) {
                    con2 += '<div class="col-md-offset-3 col-md-3">\n' +
                        '<div class="form-check02">\n' +
                        '<input type="checkbox" name="subjek' + i + '" id="subjek' + idx + i + '" value="' + it + '">\n' +
                        '<label for="subjek' + idx + i + '"></label> ' + it + '\n' +
                        '</div>' +
                        '</div>';
                } else if (idx > 4) {
                    con2 += '<div class="col-md-offset-3 col-md-3">\n' +
                        '<div class="form-check02">\n' +
                        '<input type="checkbox" name="subjek' + i + '" id="subjek' + idx + i + '" value="' + it + '">\n' +
                        '<label for="subjek' + idx + i + '"></label> ' + it + '\n' +
                        '</div>' +
                        '</div>';
                }
            }

            if (item.keterangan == "i") {

                if (idx <= 3) {
                    con2 += '<div class="col-md-2">\n' +
                        '<input style="margin-left: 15px" name="subjek' + i + '" class="form-control" id="subjek' + idx + i + '" placeholder="' + it + '" onchange="$(\'#subjek' + idx + i + '\').val(\'' + it + ' ' + '\'+this.value)">\n' +
                        '</div>';
                } else if (idx > 3) {
                    con2 += '<div class="col-md-offset-3 col-md-3">\n' +
                        '<input style="margin-left: 15px" name="subjek' + i + '" class="form-control" id="subjek' + idx + i + '" placeholder="' + it + '" onchange="$(\'#subjek' + idx + i + '\').val(\'' + it + ' ' + '\'+this.value)">\n' +
                        '</div>';
                } else if (idx > 4) {
                    con2 += '<div class="col-md-offset-3 col-md-3">\n' +
                        '<input style="margin-left: 15px" name="subjek' + i + '" class="form-control" id="subjek' + idx + i + '" placeholder="' + it + '" onchange="$(\'#subjek' + idx + i + '\').val(\'' + it + ' ' + '\'+this.value)">\n' +
                        '</div>';
                }
            }
        });
        temp +=
            '<div class="row">' +
            '<div class="form-group">' +
            con + con2 +
            '</div>' +
            '</div>';
    });
    $('#temp_subjektif').html(temp);
}

function dataTindakan() {
    var data = [];

    data.push({'tindakan': 'Memberikan askep'});
    data.push({'tindakan': 'Asintesi CVC'});
    data.push({'tindakan': 'Seting Ventilator'});
    data.push({'tindakan': 'Sucsen/Washing'});
    data.push({'tindakan': 'Monitoring EKG/TD/SpO2'});
    data.push({'tindakan': 'Monitoring CVP/IBP'});
    data.push({'tindakan': 'Injeksi M/IV/SC'});
    data.push({'tindakan': 'Obat per NGT/Oral/Teles/Supp'});
    data.push({'tindakan': 'Perawatan ETT/Gudel'});
    data.push({'tindakan': 'Perawatan Trakeostomi'});
    data.push({'tindakan': 'Pemasangan IV/Cateter'});
    data.push({'tindakan': 'Pasang dower cateter'});
    data.push({'tindakan': 'Lepas dower cateter'});
    data.push({'tindakan': 'Masang NGT'});
    data.push({'tindakan': 'Pelepasan NGT'});
    data.push({'tindakan': 'Personal/Oral Higinis'});
    data.push({'tindakan': 'Titrasi Obat'});
    data.push({'tindakan': 'Mengambil BGA'});
    data.push({'tindakan': 'Balanca cairan'});
    data.push({'tindakan': 'Membantu BAB/BAC'});
    data.push({'tindakan': 'Fisioterapi dada'});
    data.push({'tindakan': 'Membantu ROM'});
    data.push({'tindakan': 'Pemberian O2'});
    data.push({'tindakan': 'Rawat luka'});
    data.push({'tindakan': 'Ganti Kolestomi Bag'});
    data.push({'tindakan': 'Memberikan diet NGT'});
    data.push({'tindakan': 'Bantu makan/minum eskterma'});
    data.push({'tindakan': 'Menyeka PX'});
    data.push({'tindakan': 'Head up'});
    data.push({'tindakan': 'Evaluasi GCS'});
    data.push({'tindakan': 'Evaluasi nyeri'});
    data.push({'tindakan': 'Alfdrain'});
    data.push({'tindakan': 'GDA/jam'});
    data.push({'tindakan': 'Memasang fiksasi'});
    data.push({'tindakan': 'Resusitasi jantung paru'});
    data.push({'tindakan': 'Skin tes'});
    data.push({'tindakan': 'EKG manual'});
    data.push({'tindakan': 'Perawatan jenasah'});
    data.push({'tindakan': 'DC shoc'});
    data.push({'tindakan': 'Kumbah lambung'});
    data.push({'tindakan': 'Trasfusi darah'});
    data.push({'tindakan': 'Buka jahit dan drain'});
    data.push({'tindakan': 'Perawatan WSD'});

    return data;
}

function dataHeadSubjektif() {
    var data = [];
    data.push({'id': '1', 'isi': 'Breathing'});
    data.push({'id': '2', 'isi': 'Blood'});
    data.push({'id': '3', 'isi': 'Brain'});
    data.push({'id': '4', 'isi': 'Bladder'});
    data.push({'id': '5', 'isi': 'Bowel'});
    data.push({'id': '6', 'isi': 'Bone'});
    return data;
}

function dataSubjektif(id) {
    var data = [];
    var dataCari = [];

    data.push({
        'id': '1',
        'subjektif': 'Jalan nafas',
        'content': 'bersih|sumbatan/benda|benda asing|darah|lidah',
        'keterangan': 'c'
    });
    data.push({
        'id': '1',
        'subjektif': 'Pernafasan',
        'content': 'sesak|aktivitas|tanpa acktivitas|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '1',
        'subjektif': 'Penggunaan otot bantu pernafasan',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '1',
        'subjektif': 'ETT/Tracheocanule',
        'content': '|cuf',
        'keterangan': 'i'
    });
    data.push({
        'id': '1',
        'subjektif': 'Frekuensi',
        'content': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '1',
        'subjektif': 'Irama',
        'content': 'teratur|tidak teratur',
        'keterangan': 'c'
    });
    data.push({
        'id': '1',
        'subjektif': 'Kedalaman',
        'content': 'dalam|dangkal',
        'keterangan': 'c'
    });
    data.push({
        'id': '1',
        'subjektif': 'Sputum',
        'content': 'putih|kuning/bau',
        'keterangan': 'c'
    });
    data.push({
        'id': '1',
        'subjektif': 'Konsistensi',
        'content': 'kental|encer',
        'keterangan': 'c'
    });
    data.push({
        'id': '1',
        'subjektif': 'Terdapat darah',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '1',
        'subjektif': 'Nafas berbunyi',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '1',
        'subjektif': 'Suara nafas',
        'content': 'ronchi|wheezing|vesikuler',
        'keterangan': 'c'
    });
    data.push({
        'id': '1',
        'subjektif': 'Analisa gas darah',
        'content': 'pH|pCO2|pO2|O2',
        'keterangan': 'i'
    });

    //id = 2
    data.push({
        'id': '2',
        'subjektif': 'Nadi',
        'content': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '2',
        'subjektif': 'Irama',
        'content': 'teratur|tidak teratur',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Denyut Nadi',
        'content': 'lemah|kuat',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'EKG',
        'content': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '2',
        'subjektif': 'Tekanan Darah',
        'content': '|MAP',
        'keterangan': 'i'
    });
    data.push({
        'id': '2',
        'subjektif': 'Distensi vena jugularis',
        'content': 'tidak|ya',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Akral',
        'content': 'hangat|dingin',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Warna Kulit',
        'content': 'pucat|kemerahan|sianosis',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Pengisian kapiler',
        'content': '< 3 detik| > 3 detik',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Edema',
        'content': 'tidak|ya|muka|tungkai atas|tungkai bawah|seluruh tubuh',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Jantung Irama',
        'content': 'teratur|tidak teratur',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Bunyi',
        'content': 'BJ I|BJ II|murmur|gallop',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Keluhan',
        'content': 'lelah|berdebar-debar|kesemutan|keringat dingin|gemeteran',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Sakit Dada',
        'content': 'ya, timbul|saat aktivitas|tanpa aktivitas|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Karakteristik',
        'content': 'seperti ditusuk-tusuk|seperti terbakar|mnyebar|seperti tertimpa benda berat',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'Hb',
        'content': '|Ht|Eritrosit|Leukosit|Trombosit|',
        'keterangan': 'i'
    });
    data.push({
        'id': '2',
        'subjektif': 'Pendarahan',
        'content': 'gusi mudah berdarah|mimisan|petechine|echimsis|terus menerus|kemah|pucat',
        'keterangan': 'c'
    });
    data.push({
        'id': '2',
        'subjektif': 'CT/BT',
        'content': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '2',
        'subjektif': 'PPT/APPT',
        'content': '',
        'keterangan': 'i'
    });

    //id = 3
    data.push({
        'id': '3',
        'subjektif': 'Tinklat kesadaran',
        'content': 'kompos mentis|apatis|sanmonela',
        'keterangan': 'c'
    });
    data.push({
        'id': '3',
        'subjektif': 'Pupil',
        'content': 'Isokor|Unisokor|Miosis|Midrisis',
        'keterangan': 'c'
    });
    data.push({
        'id': '3',
        'subjektif': 'Reaksi pada cahaya',
        'content': 'positif|negatif',
        'keterangan': 'c'
    });
    data.push({
        'id': '3',
        'subjektif': 'GCS',
        'content': 'E|M|V|jumlah',
        'keterangan': 'i'
    });
    data.push({
        'id': '3',
        'subjektif': 'Terjadi',
        'content': 'kejang|kelumpuhan, dibagian|kanan|kiri|pelo|mulut menceng|aphasia',
        'keterangan': 'c'
    });
    data.push({
        'id': '3',
        'subjektif': 'ICP',
        'content': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '3',
        'subjektif': 'CVP',
        'content': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '3',
        'subjektif': 'Psikologi',
        'content': 'sedih/murung|menyendiri|kebersihan diri kurang|gelisah/modar mandir|bicara sendiri|ekspresi wajah datar|ekspresi wajah tegang|mata merah|marah-marah',
        'keterangan': 'c'
    });

    //id = 4
    data.push({
        'id': '4',
        'subjektif': 'BAK',
        'content': 'pola rutin|saat ini',
        'keterangan': 'i'
    });
    data.push({
        'id': '4',
        'subjektif': 'Prodoksi urin',
        'content': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '4',
        'subjektif': 'Warna',
        'content': 'kuning jernih|kuning kental/coklat|merah|bening',
        'keterangan': 'c'
    });
    data.push({
        'id': '4',
        'subjektif': 'Rasa Sakit waktu BAK',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '4',
        'subjektif': 'Distensi/ketegangan kandung kemih kencing',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '4',
        'subjektif': 'Keluhan sakit pinggang',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });

    //id = 5
    data.push({
        'id': '5',
        'subjektif': 'BAB',
        'content': 'pola rutin|saat ini',
        'keterangan': 'i'
    });
    data.push({
        'id': '5',
        'subjektif': 'Konsistensi',
        'content': 'pada|lunak|encer',
        'keterangan': 'c'
    });
    data.push({
        'id': '5',
        'subjektif': 'Warna',
        'content': 'kuning|hitam|merah|depul/pucat',
        'keterangan': 'c'
    });
    data.push({
        'id': '5',
        'subjektif': 'Lendir',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '5',
        'subjektif': 'Mual/Muntah',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '5',
        'subjektif': 'Kembung',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '5',
        'subjektif': 'Nyeri tekan',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '5',
        'subjektif': 'Distensi',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '5',
        'subjektif': 'NGT',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '5',
        'subjektif': 'Intake',
        'content': '',
        'keterangan': 'i'
    });

    //id = 6
    data.push({
        'id': '6',
        'subjektif': 'Turgor Kulit',
        'content': 'baik atau eslatic|buruk',
        'keterangan': 'c'
    });
    data.push({
        'id': '6',
        'subjektif': 'Keadaan Kulit',
        'content': 'baik|terdapat luka',
        'keterangan': 'c'
    });
    data.push({
        'id': '6',
        'subjektif': 'Keadaan Luka',
        'content': 'baru|infeksi/gangren',
        'keterangan': 'c'
    });
    data.push({
        'id': '6',
        'subjektif': 'Kesulitan dalam pergerakan',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '6',
        'subjektif': 'Fraktur',
        'content': 'ya',
        'keterangan': 'c'
    });
    data.push({
        'id': '6',
        'subjektif': 'Odema',
        'content': 'ya|tidak',
        'keterangan': 'c'
    });
    data.push({
        'id': '6',
        'subjektif': 'Kekuatan otot',
        'content': '',
        'keterangan': 'i'
    });

    $.each(data, function (i, item) {
        if (item.id == id) {
            dataCari.push({
                'id': item.id,
                'subjektif': item.subjektif,
                'content': item.content,
                'keterangan': item.keterangan
            });
        }
    });

    return dataCari;
}

function pilihTindakanINA(val) {
    if (val != '') {
        $('#form-tindakan-ina').show();
        $('#ina8').val(val);
        var body = "";
        $.each(tindakanINA(val), function (i, item) {

            var params = "";
            var informasi = "";

            if ("i" == item.keterangan) {
                var info = item.informasi.split("|");
                $.each(info, function (idx, itemx) {
                    if (itemx != '') {
                        informasi += '<input class="form-control" name="informasi' + i + '" id="info' + i + '" placeholder="' + itemx + '" onchange="$(\'#info' + i + '\').val(\'' + itemx + ' ' + '\'+this.value)">';
                    } else {
                        informasi += '<input class="form-control" name="informasi' + i + '" id="info' + i + '" placeholder="' + itemx + '">';
                    }
                });
            }
            if ("r" == item.keterangan) {
                var info = item.informasi.split("|");
                $.each(info, function (idx, itemx) {
                    informasi += '<div class="row">' +
                        '<div class="custom02" style="margin-left: 15px">\n' +
                        '<input type="radio" value="' + itemx + '" id="informasi' + i + idx + '" name="informasi' + i + '"/><label for="informasi' + i + idx + '">' + itemx + '</label>\n' +
                        '</div>' +
                        '</div>';
                });
            }
            if ("c" == item.keterangan) {
                var info = item.informasi.split("|");
                $.each(info, function (idx, itemx) {
                    informasi += '<div class="row">' +
                        '<div class="form-check02">\n' +
                        '<input type="checkbox" name="informasi' + i + '" id="informasi' + i + idx + '" value="' + itemx + '">\n' +
                        '<label for="informasi' + i + idx + '"></label> ' + itemx + '\n' +
                        '</div>' +
                        '</div>';
                });
            }
            if ("l" == item.keterangan) {
                informasi += item.informasi + '<input type="hidden" value="' + item.informasi + '" name="informasi' + i + '">';
            }

            var cekList = '<div class="row">' +
                '<div class="form-check02">\n' +
                '<input type="checkbox" name="tanda" id="tanda' + i + '" value="Ya">\n' +
                '<label for="tanda' + i + '"></label>' +
                '</div>' +
                '</div>'

            body += '<tr>' +
                '<td width="25%">' + item.parameter + '<input name="parameter" type="hidden" value="' + item.parameter + '"></td>' +
                '<td>' + informasi + '</td>' +
                '<td align="center" width="15%">' + cekList + '</td>' +
                '</tr>';
        });

        $('#body_tindakan_ina').html(body);

    } else {
        $('#form-tindakan-ina').hide();
        $('#ina8').val('');
        $('#body_tindakan_ina').html('');
    }
}

function tindakanINA(jenis) {

    var data = [];
    var dataCari = [];

    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Diagnosis (WD dan DD)',
        'informasi': 'Appendicities acute',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Anamnesa, pemeriksaan klinis, laboratorium',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Appendectomy',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Indikasi Tindakan',
        'informasi': 'Appendicities acute',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Tata Cara',
        'informasi': 'Pembiusan, irisan jaringan yang ditindak',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Tujuan',
        'informasi': 'Kuratif',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Resiko',
        'informasi': 'Sedang, Hal-hal lain yang tidak dapat diprediksi sebelumnya',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi, streng ilues dan hal-hal yang tidak dapat diprediksi sebelumnya',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Prognosis',
        'informasi': 'Baik',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'Appendecitomy',
        'parameter': 'Perkiraan Biaya',
        'informasi': '',
        'keterangan': 'i'
    });

    if (jenis != '') {
        $.each(data, function (i, item) {
            if (jenis == item.jenis) {
                dataCari.push({
                    'jenis': item.jenis,
                    'parameter': item.parameter,
                    'informasi': item.informasi,
                    'keterangan': item.keterangan
                });
            }
        });
    }
    return dataCari;
}

function saveImpl(jenis, ket) {
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var data = "";
    var va1 = $('#impl1').val();
    var va2 = $('#impl2').val();
    var va3 = $('#impl3').val();
    var v4 = document.getElementById("impl4");
    var va4 = isCanvasBlank(v4);
    var nama = $('#nama_terang').val();
    var sip = $('#sip').val();

    if (va1 && va2 && va3 && nama && sip != '' && !va4) {

        var ttd = convertToDataURL(v4);

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1 + ' ' + va2,
            'keterangan': va3,
            'nama_terang': nama,
            'sip': sip,
            'ttd': ttd
        }

        var result = JSON.stringify(data);
        var pasienData = JSON.stringify(dataPasien);

        $('#save_ina_' + jenis).hide();
        $('#load_ina_' + jenis).show();
        dwr.engine.setAsync(true);
        AsesmenRawatInapAction.saveImplementasiPerawat(result, pasienData, {
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

function detailImpl(jenis) {
    var body = "";
    var head = "";
    var cekData = false;
    AsesmenRawatInapAction.getListImplementasiPerawat(idDetailCheckup, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                body += '<tr>' +
                    '<td width="13%">' + item.waktu + '</td>' +
                    '<td>' + item.keterangan + '</td>' +
                    '<td>' + '<img style="width: 100px" src="' + item.ttd + '">' +
                    '<p style="margin-top: -3px">' + cekItemIsNull(item.namaTerang) + '</p>' +
                    '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p></td>' +
                    '<td align="center"><i id="delete_' + item.idImplementasiPerawat + '" onclick="conRI(\'' + jenis + '\',\'implementasi_perawat\', \'' + item.idImplementasiPerawat + '\', \'impl\')" class="fa fa-trash hvr-grow" style="color: red"></i></td>'
                '</tr>'
            });
            cekData = true;
        } else {
            body = '<tr><td>Data belum ada</td></tr>';
        }

        if (cekData) {
            head = '<tr>' +
                '<td>Waktu</td>' +
                '<td>Keterangan</td>' +
                '<td width="20%">Nama/TTD</td>' +
                '<td width="10%" align="center">Action</td>' +
                '</tr>';
        }
        var table = '<table style="font-size: 12px" class="table table-bordered">' +
            '<thead>' + head + '</thead>' +
            '<tbody>' + body + '</tbody>' +
            '</table>';

        var newRow = $('<tr id="del_ina_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
        newRow.insertAfter($('table').find('#row_ina_' + jenis));
        var url = contextPath + '/pages/images/minus-allnew.png';
        $('#btn_ina_' + jenis).attr('src', url);
        $('#btn_ina_' + jenis).attr('onclick', 'delRowImplementasi(\'' + jenis + '\')');
    });
}

function delRowImplementasi(id) {
    $('#del_ina_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_ina_' + id).attr('src', url);
    $('#btn_ina_' + id).attr('onclick', 'detailImpl(\'' + id + '\')');
}


function conRI(jenis, ket, idAsesmen, tipe) {
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show: true, backdrop: 'static'});
    if (idAsesmen != undefined && idAsesmen != '') {
        if (tipe == "impl") {
            $('#save_con_rm').attr('onclick', 'delImpl(\'' + jenis + '\', \'' + ket + '\', \'' + idAsesmen + '\')');
        } else if (tipe == "edukasi") {
            $('#save_con_rm').attr('onclick', 'delEdukasi(\'' + jenis + '\', \'' + ket + '\')');
        } else {
            $('#save_con_rm').attr('onclick', 'delRIHand(\'' + jenis + '\', \'' + ket + '\', \'' + idAsesmen + '\')');
        }
    } else {
        $('#save_con_rm').attr('onclick', 'delRI(\'' + jenis + '\', \'' + ket + '\')');
    }
}

function delImpl(jenis, ket, idAsesmen) {
    $('#modal-confirm-rm').modal('hide');
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var result = JSON.stringify(dataPasien);
    startIconSpin('delete_' + idAsesmen);
    dwr.engine.setAsync(true);
    AsesmenRawatInapAction.saveDeleteImplementasiPerawat(idAsesmen, result, {
        callback: function (res) {
            if (res.status == "success") {
                stopIconSpin('delete_' + idAsesmen);
                $('#modal-ina-' + ket).scrollTop(0);
                $('#warning_ina_' + ket).show().fadeOut(5000);
                $('#msg_ina_' + ket).text("Berhasil menghapus data...");
            } else {
                stopIconSpin('delete_' + idAsesmen);
                $('#modal-ina-' + ket).scrollTop(0);
                $('#warn_' + ket).show().fadeOut(5000);
                $('#msg_' + ket).text(res.msg);
            }
        }
    });
}

function delRIHand(jenis, ket, idAsesmen) {
    $('#modal-confirm-rm').modal('hide');
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var result = JSON.stringify(dataPasien);
    startIconSpin('delete_' + idAsesmen);
    dwr.engine.setAsync(true);
    AsesmenRawatInapAction.saveDelete(idDetailCheckup, jenis, idAsesmen, result, {
        callback: function (res) {
            if (res.status == "success") {
                stopIconSpin('delete_' + idAsesmen);
                $('#modal-ina-' + ket).scrollTop(0);
                $('#warning_ina_' + ket).show().fadeOut(5000);
                $('#msg_ina_' + ket).text("Berhasil menghapus data...");
                delRowAsesmenRawatInap(jenis);
                detailAsesmenRawatInap(jenis);
            } else {
                stopIconSpin('delete_' + idAsesmen);
                $('#modal-ina-' + ket).scrollTop(0);
                $('#warn_' + ket).show().fadeOut(5000);
                $('#msg_' + ket).text(res.msg);
            }
        }
    });
}

function delEdukasi(jenis, ket) {
    $('#modal-confirm-rm').modal('hide');
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var result = JSON.stringify(dataPasien);
    startSpin('delete_' + jenis);
    dwr.engine.setAsync(true);
    EdukasiPasienAction.saveDelete(idDetailCheckup, jenis, result, {
        callback: function (res) {
            if (res.status == "success") {
                stopSpin('delete_' + jenis);
                $('#modal-ina-' + ket).scrollTop(0);
                $('#warning_ina_' + ket).show().fadeOut(5000);
                $('#msg_ina_' + ket).text("Berhasil menghapus data...");
            } else {
                stopSpin('delete_' + jenis);
                $('#modal-ina-' + ket).scrollTop(0);
                $('#warn_' + ket).show().fadeOut(5000);
                $('#msg_' + ket).text(res.msg);
            }
        }
    });
}


function delRI(jenis, ket) {
    $('#modal-confirm-rm').modal('hide');
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var result = JSON.stringify(dataPasien);
    startSpin('delete_' + jenis);
    dwr.engine.setAsync(true);
    AsesmenRawatInapAction.saveDelete(idDetailCheckup, jenis, null, result, {
        callback: function (res) {
            if (res.status == "success") {
                stopSpin('delete_' + jenis);
                $('#modal-ina-' + ket).scrollTop(0);
                $('#warning_ina_' + ket).show().fadeOut(5000);
                $('#msg_ina_' + ket).text("Berhasil menghapus data...");
            } else {
                stopSpin('delete_' + jenis);
                $('#modal-ina-' + ket).scrollTop(0);
                $('#warn_' + ket).show().fadeOut(5000);
                $('#msg_' + ket).text(res.msg);
            }
            delRowAsesmenRawatInap(jenis);
            detailAsesmenRawatInap(jenis);
        }
    });
}

function setSideValue(id, value) {
    if (value == '' || value == '___/___') {
        $('#' + id).val('');
    } else {
        $('#' + id).val(value);
    }
}

function saveTransferPasien(id, i, parameter) {
    var nilai = $('#serah_' + i).val();
    if (id != '' && nilai != '') {
        var jawaban = "";
        if ("Tekanan Darah" == parameter) {
            jawaban = replaceUnderLine(nilai) + " mmHg";
        } else if ("Suhu" == parameter) {
            jawaban = nilai + " ˚C";
        } else if ("Nadi" == parameter) {
            jawaban = nilai + " x/mnt";
        } else if ("RR" == parameter) {
            jawaban = nilai + " x/mnt";
        } else if ("Saturasi" == parameter) {
            jawaban = nilai + " %";
        } else {
            jawaban = nilai;
        }
        dwr.engine.setAsync(true);
        AsesmenRawatInapAction.saveAsesmenRI(id, jawaban, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#set_serah_' + i).html('<span>' + jawaban + '</span>');
                    $('#warning_ina_transfer_pasien').show().fadeOut(5000);
                    $('#msg_ina_transfer_pasien').text("Data berhasil disimpan...!");
                } else {
                    $('#warn_transfer_pasien').show().fadeOut(5000);
                    $('#msg_transfer_pasien').text(res.msg);
                }
            }
        });
    }
}

function setTtd(id, jenis, ket){
    setDataPasien();
    $('#modal-ina-ttd').modal({show: true, backdrop: 'static'});
    $('#save_ttd').attr('onclick','saveTtd(\''+id+'\', \''+jenis+'\', \''+ket+'\')');
}

function saveTtd(id, jenis, ket){
    var nama = $('#nama_ttd').val();
    var sip = $('#sip_ttd').val();
    var ttd = document.getElementById("ttd_edit");
    var cek = isCanvasBlank(ttd);
    if(nama && sip != '' && !cek){
        if(!cekSession()){
            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            var dataPasien = {
                'id_asesmen': id,
                'nama': nama,
                'sip': sip,
                'ttd': ttd1
            }
            delRowAsesmenRawatInap(jenis);
            var result = JSON.stringify(dataPasien);
            $('#save_ttd').hide();
            $('#load_ttd').show();
            dwr.engine.setAsync(true);
            AsesmenRawatInapAction.updateTTD(result, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#modal-ina-ttd').modal('hide');
                        $('#save_ttd').show();
                        $('#load_ttd').hide();
                        detailAsesmenRawatInap(jenis);
                    } else {
                        $('#warning_ina_ttd').show().fadeOut(5000);
                        $('#msg_ina_ttd').text(res.msg);
                        $('#save_ttd').show();
                        $('#load_ttd').hide();
                    }
                }
            });
        }
    }else {
        $('#warning_ina_'+ket).show().fadeOut(5000);
        $('#msg_ina_'+ket).text("Silahkan cek kembali inputan anda...!");
    }
}
