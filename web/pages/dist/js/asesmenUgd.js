$('[name=radio_aud_nutrisional]').click(function () {
    var check1 = $('[name=radio_aud_nutrisional]:checked').val();
    if (check1 == "Ya") {
        $('#aud_penurunan').show();
    } else {
        $('#aud_penurunan').hide();
    }
});

$('[name=radio_aud_kebutuhan1]').click(function () {
    var check1 = $('[name=radio_aud_kebutuhan1]:checked').val();
    if (check1 == "Gangguan") {
        $('#form-kebutuhan1').show();
    } else {
        $('#form-kebutuhan1').hide();
    }
});

$('[name=radio_aud_kebutuhan2]').click(function () {
    var check1 = $('[name=radio_aud_kebutuhan2]:checked').val();
    if (check1 == "Ya") {
        $('#form-kebutuhan2').show();
    } else {
        $('#form-kebutuhan2').hide();
    }
});

$('[name=radio_aud_kebutuhan3]').click(function () {
    var check1 = $('[name=radio_aud_kebutuhan3]:checked').val();
    if (check1 == "Ya") {
        $('#form-ket-kebutuhan3').show();
    } else {
        $('#form-ket-kebutuhan3').hide();
    }
});

$('[name=radio_ket_kebutuhan3]').click(function () {
    var check1 = $('[name=radio_ket_kebutuhan3]:checked').val();
    if (check1 == "lainnya") {
        $('#form-kebutuhan3').show();
    } else {
        $('#form-kebutuhan3').hide();
    }
});

$('[name=radio_aud_kebutuhan4]').click(function () {
    var check1 = $('[name=radio_aud_kebutuhan4]:checked').val();
    if (check1 == "lainnya") {
        $('#form-kebutuhan4').show();
    } else {
        $('#form-kebutuhan4').hide();
    }
});

function showAsesmenUgd() {
    if(isReadRM){
        $('.btn-hide').hide();
    }else{
        $('.btn-hide').show();
    }
    $('#modal-asesmen-ugd-dewasa').modal({show: true, backdrop: 'static'});
}

function addAsesmenUgd(jenis) {
    if (jenis != '') {
        $('#modal-aud-' + jenis).modal({show: true, backdrop: 'static'});
    }
}

function saveAsesmenUgd(jenis) {
    if (jenis != '') {

        var data = [];
        var cek = false;

        if ("keluhan_utama" == jenis) {
            var triase = $('[name=radio_triase]:checked').val();
            var keadaanUmum = $('[name=radio_keadaan]:checked').val();
            var napas = $('[name=radio_napas]:checked').val();
            var nadi = $('[name=radio_nadi]:checked').val();
            var akral = $('[name=radio_akral]:checked').val();
            var crt = $('[name=radio_crt]:checked').val();

            if (triase && keadaanUmum && napas && nadi && akral && crt != undefined) {
                data.push({
                    'parameter': 'Triase',
                    'jawaban': triase,
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Keadaan Umum',
                    'jawaban': keadaanUmum,
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Pernafasan',
                    'jawaban': napas,
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Nadi',
                    'jawaban': nadi,
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Akral',
                    'jawaban': akral,
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'CRT',
                    'jawaban': crt,
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if ("pre_hospital" == jenis) {
            var tkn = $('#pre_tkn').val();
            var nadi1 = $('#pre_nadi').val();
            var rr1 = $('#pre_rr').val();
            var suhu = $('#pre_suhu').val();
            var airway = $('[name=radio_airway]:checked').val();
            var warnaKulit1 = $('[name=radio_kulit]:checked').val();
            var polaNafas = $('[name=radio_pola]:checked').val();
            var kerjaNafas = $('[name=radio_kerja_napas]:checked').val();
            var suaraNafas = $('[name=radio_suara_napas]:checked').val();
            var jejas = $('[name=radio_jejas]:checked').val();
            var trakea = $('[name=radio_dt]:checked').val();
            var dada = $('[name=radio_pd]:checked').val();
            var distensi = $('[name=radio_dvj]:checked').val();
            var otot = $('#breathing_bantu').val();
            var rr2 = $('#breathing_rr').val();
            var spo2 = $('#breathing_sp').val();
            var kwalNadi = $('[name=radio_kwn]:checked').val();
            var irama = $('[name=radio_irama]:checked').val();
            var crt = $('[name=radio_cir_crt]:checked').val();
            var warnaKulit2 = $('[name=radio_cir_warna_kulit]:checked').val();
            var akral = $('[name=radio_cri_akral]:checked').val();
            var nadi2 = $('#circulation_nadi').val();

            if (tkn && nadi1 && rr1 && suhu != ''
                && airway && warnaKulit1 && polaNafas && kerjaNafas && suaraNafas && jejas && trakea && dada && distensi != undefined
                && otot && rr2 && spo2 && nadi2 != ''
                && kwalNadi && irama && crt && warnaKulit2 && akral != undefined) {
                data.push({
                    'parameter': 'Tekanan Darah',
                    'jawaban': tkn + ' mmHg',
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Nadi',
                    'jawaban': nadi1 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'RR',
                    'jawaban': rr1 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Suhu',
                    'jawaban': suhu + ' C',
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Airway',
                    'jawaban': airway,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Warna Kulit',
                    'jawaban': warnaKulit1,
                    'keterangan': jenis,
                    'jenis': 'airway',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Pola Nafas',
                    'jawaban': polaNafas,
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Kerja Nafas',
                    'jawaban': kerjaNafas,
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Suara Nafas',
                    'jawaban': suaraNafas,
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Jejas',
                    'jawaban': jejas,
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Deviasi Trakhea',
                    'jawaban': trakea,
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Pengembangan Dada',
                    'jawaban': dada,
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Distensi vena jugularis',
                    'jawaban': distensi,
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Otot bantu nafas',
                    'jawaban': otot,
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'RR',
                    'jawaban': rr2 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'SpO2',
                    'jawaban': spo2 + ' %',
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Kwalitas nadi',
                    'jawaban': kwalNadi,
                    'keterangan': jenis,
                    'jenis': 'circulation',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Irama',
                    'jawaban': irama,
                    'keterangan': jenis,
                    'jenis': 'circulation',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'CRT',
                    'jawaban': crt,
                    'keterangan': jenis,
                    'jenis': 'circulation',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Warna Kulit',
                    'jawaban': warnaKulit2,
                    'keterangan': jenis,
                    'jenis': 'circulation',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Akral',
                    'jawaban': akral,
                    'keterangan': jenis,
                    'jenis': 'circulation',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Nadi',
                    'jawaban': nadi2 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': 'circulation',
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if ("keperawatan" == jenis) {
            var tkn = $('#kep_tekanan_darah').val();
            var nadi1 = $('#kep_nadi').val();
            var rr1 = $('#kep_rr').val();
            var suhu = $('#kep_suhu').val();
            var bb = $('#kep_bb').val();
            var tempat = $('#kep_tempat_tinggal').val();
            var tTempat = "";
            if (tempat == "Lainnya") {
                var t = $('#kep_ada_tempat_tinggal').val();
                if (t != '') {
                    tTempat = t;
                }
            } else {
                tTempat = tempat;
            }
            var emosi = $('#kep_emosional').val();
            var perilaku = $('#kep_perilaku').val();
            var tPerilaku = "";
            if (perilaku == "Ada") {
                var p = $('#kep_ada_perilaku').val();
                if (p != '') {
                    tPerilaku = p;
                }
            } else {
                tPerilaku = perilaku;
            }
            var status = $('#kep_pernikahan').val();
            var hubungan = $('#kep_hubungan').val();
            var agama = $('#kep_agama').val();
            var dibantu = $('#kep_dibantu').val();
            var pekerjaan = $('#kep_pekerjaan').val();
            var penanggung = $('#kep_penanggung').val();
            var tPenanggung = "";
            if (penanggung == "Lainnya") {
                var pn = $('#kep_ada_penanggung').val();
                if (pn != '') {
                    tPenanggung = pn;
                }
            } else {
                tPenanggung = penanggung;
            }
            var sekarang = $('#kep_sekarang').val();
            var dahulu = $('#kep_dahulu').val();
            var keluarga = $('#kep_keluarga').val();
            var riwayat = $('#kep_alergi').val();
            var tRiwayat = "";
            if (riwayat == "Obat" || riwayat == "Makanan") {
                var r = $('#kep_ket_alergi').val();
                if (r != '') {
                    tRiwayat = riwayat + ', ' + r;
                }
            } else {
                tRiwayat = riwayat;
            }

            if (tkn && nadi1 && rr1 && suhu && bb && tTempat && emosi && tPerilaku && status && hubungan && agama
                && dibantu && pekerjaan && tPenanggung && sekarang && dahulu && keluarga && tRiwayat != '') {
                data.push({
                    'parameter': 'Tekanan Darah',
                    'jawaban': tkn + ' mmHg',
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Nadi',
                    'jawaban': nadi1 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'RR',
                    'jawaban': rr1 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Suhu',
                    'jawaban': suhu + ' C',
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Berat Badan',
                    'jawaban': bb + ' Kg',
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Tempat Tinggal',
                    'jawaban': tTempat,
                    'keterangan': jenis,
                    'jenis': 'Psiko-sosio-Spiritual',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Status Emosional',
                    'jawaban': emosi,
                    'keterangan': jenis,
                    'jenis': 'Psiko-sosio-Spiritual',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Masalah Perilaku',
                    'jawaban': tPerilaku,
                    'keterangan': jenis,
                    'jenis': 'Psiko-sosio-Spiritual',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Status Pernikahan',
                    'jawaban': status,
                    'keterangan': jenis,
                    'jenis': 'Psiko-sosio-Spiritual',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Hubungan pasien dengan keluarga',
                    'jawaban': hubungan,
                    'keterangan': jenis,
                    'jenis': 'Psiko-sosio-Spiritual',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Agama',
                    'jawaban': agama,
                    'keterangan': jenis,
                    'jenis': 'Psiko-sosio-Spiritual',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Perlu dibantu dalam ibadah',
                    'jawaban': dibantu,
                    'keterangan': jenis,
                    'jenis': 'Psiko-sosio-Spiritual',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Pekerjaan',
                    'jawaban': pekerjaan,
                    'keterangan': jenis,
                    'jenis': 'ekonomi',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Penanggung Jawab',
                    'jawaban': tPenanggung,
                    'keterangan': jenis,
                    'jenis': 'ekonomi',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Riwayat Penyakit Sekarang',
                    'jawaban': sekarang,
                    'keterangan': jenis,
                    'jenis': 'riwayat_kesehatan_pasien',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Riwayat Penyakit Dahulu',
                    'jawaban': dahulu,
                    'keterangan': jenis,
                    'jenis': 'riwayat_kesehatan_pasien',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Riwayat Penyakit keluarga',
                    'jawaban': keluarga,
                    'keterangan': jenis,
                    'jenis': 'riwayat_kesehatan_pasien',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Riwayat Alergi',
                    'jawaban': tRiwayat,
                    'keterangan': jenis,
                    'jenis': 'riwayat_alergi',
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }

        }

        if ("nyeri" == jenis) {
            var nyeri = $('[name=radio_aud_nyeri]:checked').val();
            var skala = $('[name=radio_aud_skala]:checked').val();
            var lokasi = $('#yer_lokasi').val();
            if (nyeri && skala != undefined && lokasi != '') {
                data.push({
                    'parameter': 'Apakah terdapat keluhan nyeri',
                    'jawaban': nyeri,
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Skala',
                    'jawaban': skala,
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Lokasi',
                    'jawaban': lokasi,
                    'keterangan': jenis,
                    'jenis': '',
                    'id_detail_checkup': idDetailCheckup
                });
                console.log(data);
                cek = true;
            }
        }

        if ("nutrisional" == jenis) {
            var nutrisi1 = $('[name=radio_aud_nutrisional]:checked').val();
            var nutrisi2 = $('[name=radio_aud_nafsu1]:checked').val();
            var tNutrisi1 = "";
            if (nutrisi1 == "Ya") {
                var penurunan = $('[name=radio_aud_penurunan]:checked').val();
                if (penurunan != undefined) {
                    tNutrisi1 = nutrisi1 + ', ' + penurunan;
                }
            } else {
                tNutrisi1 = nutrisi1
            }

            if (tNutrisi1 && nutrisi2 != undefined) {
                var skor1 = tNutrisi1.split("|")[1];
                var skor2 = nutrisi2.split("|")[1];
                var isi1 = tNutrisi1.split("|")[0];
                var isi2 = nutrisi2.split("|")[0];
                data.push({
                    'parameter': 'Apakah pasien mengalami penurunan berat badan yang tidak direncanakan /tidak diinginkan dalam 6 bulan terakhir?',
                    'jawaban': isi1,
                    'keterangan': jenis,
                    'skor': skor1,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Apakah asupan makan pasien berkurang karena penurunan nafsu makan/kesulitan menerima makanan?',
                    'jawaban': isi2,
                    'keterangan': jenis,
                    'skor': skor2,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if ("jatuh" == jenis) {

            var jatuh1 = $('[name=radio_aud_jatuh1]:checked').val();
            var jatuh2 = $('[name=radio_aud_jatuh2]:checked').val();
            var jatuh3 = $('[name=radio_aud_jatuh3]:checked').val();
            var jatuh4 = $('[name=radio_aud_jatuh4]:checked').val();
            var jatuh5 = $('[name=radio_aud_jatuh5]:checked').val();
            var jatuh6 = $('[name=radio_aud_jatuh6]:checked').val();

            if (jatuh1 && jatuh2 && jatuh3 && jatuh4 && jatuh5 != undefined) {

                var isi1 = jatuh1.split("|")[0];
                var isi2 = jatuh2.split("|")[0];
                var isi3 = jatuh3.split("|")[0];
                var isi4 = jatuh4.split("|")[0];
                var isi5 = jatuh5.split("|")[0];
                var isi6 = jatuh6.split("|")[0];

                var skor1 = jatuh1.split("|")[1];
                var skor2 = jatuh2.split("|")[1];
                var skor3 = jatuh3.split("|")[1];
                var skor4 = jatuh4.split("|")[1];
                var skor5 = jatuh5.split("|")[1];
                var skor6 = jatuh6.split("|")[1];

                data.push({
                    'parameter': 'Riwayat Jatuh',
                    'jawaban': isi1,
                    'keterangan': jenis,
                    'skor': skor1,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Diagnosis Sekunder',
                    'jawaban': isi2,
                    'keterangan': jenis,
                    'skor': skor2,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Alat Bantu',
                    'jawaban': isi3,
                    'keterangan': jenis,
                    'skor': skor3,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Terapi Intravena',
                    'jawaban': isi4,
                    'keterangan': jenis,
                    'skor': skor4,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Gaya Berjalan',
                    'jawaban': isi5,
                    'keterangan': jenis,
                    'skor': skor5,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Status Normal',
                    'jawaban': isi6,
                    'keterangan': jenis,
                    'skor': skor6,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if ("status" == jenis) {
            var status1 = $('#status1').val();
            var status2 = $('#status2').val();
            var status3 = $('#status3').val();
            var status4 = $('#status4').val();
            var status5 = $('#status5').val();
            var status6 = $('#status6').val();
            var status7 = $('#status7').val();
            var status8 = $('#status8').val();
            var status9 = $('#status9').val();
            var status10 = $('#status10').val();

            if (status1 && status2 && status3 && status4 && status5 && status6 && status7 && status8 && status9 && status10 != '') {
                var isi1 = status1.split("|")[0];
                var isi2 = status2.split("|")[0];
                var isi3 = status3.split("|")[0];
                var isi4 = status4.split("|")[0];
                var isi5 = status5.split("|")[0];
                var isi6 = status6.split("|")[0];
                var isi7 = status7.split("|")[0];
                var isi8 = status8.split("|")[0];
                var isi9 = status9.split("|")[0];
                var isi10 = status10.split("|")[0];

                var skor1 = status1.split("|")[1];
                var skor2 = status2.split("|")[1];
                var skor3 = status3.split("|")[1];
                var skor4 = status4.split("|")[1];
                var skor5 = status5.split("|")[1];
                var skor6 = status6.split("|")[1];
                var skor7 = status7.split("|")[1];
                var skor8 = status8.split("|")[1];
                var skor9 = status9.split("|")[1];
                var skor10 = status10.split("|")[1];

                data.push({
                    'parameter': 'Mengendalikan rangsang defekasi',
                    'jawaban': isi1,
                    'keterangan': jenis,
                    'skor': skor1,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Mengendalikan rangsang berkemih',
                    'jawaban': isi2,
                    'keterangan': jenis,
                    'skor': skor2,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Membersihkan diri (cuci muka, sisir rambut, sikat gigi)',
                    'jawaban': isi3,
                    'keterangan': jenis,
                    'skor': skor3,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Penggunaan jamban masuk dan keluar (melepaskan, memakai celana, membersihkan, menyiram)',
                    'jawaban': isi4,
                    'keterangan': jenis,
                    'skor': skor4,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Makan',
                    'jawaban': isi5,
                    'keterangan': jenis,
                    'skor': skor5,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Berubah sikap dari berbaring ke duduk',
                    'jawaban': isi6,
                    'keterangan': jenis,
                    'skor': skor6,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Berpindah / Berjalan',
                    'jawaban': isi7,
                    'keterangan': jenis,
                    'skor': skor7,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Memakai baju',
                    'jawaban': isi8,
                    'keterangan': jenis,
                    'skor': skor8,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Naik turun tangga',
                    'jawaban': isi9,
                    'keterangan': jenis,
                    'skor': skor9,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Mandi',
                    'jawaban': isi10,
                    'keterangan': jenis,
                    'skor': skor10,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if ("kebutuhan" == jenis) {
            var kebutuhan1 = $('[name=radio_aud_kebutuhan1]:checked').val();
            var kebutuhan2 = $('[name=radio_aud_kebutuhan2]:checked').val();
            var kebutuhan3 = $('[name=radio_aud_kebutuhan3]:checked').val();
            var kebutuhan4 = $('[name=radio_aud_kebutuhan4]:checked').val();
            var tKebutuhan1 = "";
            var tKebutuhan2 = "";
            var tKebutuhan3 = "";
            var tKebutuhan4 = "";

            if (kebutuhan1 == "Gangguan") {
                var p = $('#aud_ket_kebutuhan1').val();
                if (p != '') {
                    tKebutuhan1 = p;
                }
            } else {
                tKebutuhan1 = kebutuhan1;
            }

            if (kebutuhan2 == "Ya") {
                var p = $('#aud_ket_kebutuhan2').val();
                if (p != '') {
                    tKebutuhan2 = p;
                }
            } else {
                tKebutuhan2 = kebutuhan2;
            }

            if (kebutuhan3 == "Ya") {
                var p = $('#aud_ket_kebutuhan310').val();
                var ket = $('[name=radio_ket_kebutuhan3]:checked').val();
                if (ket != undefined) {
                    if (ket == "lainnya") {
                        if (p != '') {
                            tKebutuhan3 = p;
                        }
                    } else {
                        tKebutuhan3 = ket;
                    }
                }
            } else {
                tKebutuhan3 = kebutuhan1;
            }

            if (kebutuhan4 == "lainnya") {
                var p = $('#aud_ket_kebutuhan1').val();
                if (p != '') {
                    tKebutuhan4 = p;
                }
            } else {
                tKebutuhan4 = kebutuhan4;
            }

            if (tKebutuhan1 && tKebutuhan2 && tKebutuhan3 && tKebutuhan4 != '') {
                data.push({
                    'parameter': 'Bicara',
                    'jawaban': tKebutuhan1,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Perlu Penerjemah',
                    'jawaban': tKebutuhan2,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Hambatan belajar',
                    'jawaban': tKebutuhan3,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Kebutuhan Pembelajaran',
                    'jawaban': tKebutuhan4,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }

        }

        if ("diagnosis" == jenis) {
            var checkbox = document.getElementsByName('cek_diagnosis');
            var diagnosis = "";
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked) {
                    if (diagnosis != '') {
                        diagnosis = diagnosis + '|' + checkbox[i].value;
                    } else {
                        diagnosis = checkbox[i].value;
                    }
                }
            }

            if (diagnosis != '') {
                data.push({
                    'parameter': 'Diagnosis Keperawatan',
                    'jawaban': diagnosis,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if ("asuhan" == jenis) {
            var checkbox = document.getElementsByName('cek_asuhan');
            var asuhan = "";
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked) {
                    if (asuhan != '') {
                        asuhan = asuhan + '|' + checkbox[i].value;
                    } else {
                        asuhan = checkbox[i].value;
                    }
                }
            }

            if (asuhan != '') {
                data.push({
                    'parameter': 'Rencana Asuhan Keperawatan',
                    'jawaban': asuhan,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        var result = JSON.stringify(data);
        if (cek) {
            $('#save_aud_'+jenis).hide();
            $('#load_aud_'+jenis).show();
            dwr.engine.setAsync(true);
            AsesmenUgdAction.saveAsesmenUgd(result, {callback: function (res) {
                if(res.status == "success"){
                    $('#save_aud_'+jenis).show();
                    $('#load_aud_'+jenis).hide();
                    $('#modal-aud-' + jenis).modal('hide');
                    $('#warning_asesmen_ugd-dewasa').show().fadeOut(5000);
                    $('#msg_asesmen_ugd-dewasa').text("Berhasil menambahkan data asesmen UGD...");
                    $("#modal-asesmen-ugd-dewasa").scrollTop(0);
                }else{
                    $('#save_aud_'+jenis).show();
                    $('#load_aud_'+jenis).hide();
                    $("#warning_aud_" + jenis).show().fadeOut(5000);
                    $('#msg_aud_' + jenis).text(res.msg);
                    $("#modal-aud-"+jenis).scrollTop(0);
                }
            }});

        } else {
            $("#warning_aud_" + jenis).show().fadeOut(5000);
            $('#msg_aud_' + jenis).text("Silahkan cek kembali data inputan anda...!");
            $("#modal-aud-"+jenis).scrollTop(0);
        }
    }

}

function detailAud(jenis) {
    if (jenis != '') {
        var body = "";
        var totalSkor = 0;
        var cekSkor = false;
        var rowTotal = "";
        var kesimpulan = "";
        var isKesimpulan = false;
        var list = "";
        AsesmenUgdAction.getListAsesmenUgd(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {

                    if("diagnosis" == item.keterangan || "asuhan" == item.keterangan){
                        var li = "";
                        var isi = item.jawaban.split("|");
                        $.each(isi, function (i ,item) {
                            li +='<li>'+item+'</li>'
                        });
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + li + '</td>' +
                            '</tr>';
                    }else{
                        if(item.skor != null){
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + item.jawaban + '</td>' +
                                '<td>' + item.skor + '</td>' +
                                '</tr>';
                            totalSkor = parseInt(totalSkor) + parseInt(item.skor);
                            cekSkor = true;
                        }else{
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + item.jawaban + '</td>' +
                                '</tr>';
                        }
                    }
                });

                isKesimpulan = true;
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }

            if(cekSkor){
                rowTotal = '<tr><td colspan="2">Total</td><td>'+totalSkor+'</td></tr>'
            }

            if("nutrisional" == jenis){
                var jwb = "";
                if(totalSkor < 2){
                    jwb = "Tidak";
                }else{
                    jwb = "Ya";
                }

                if(isKesimpulan){
                    kesimpulan = '<tr style="font-weight: bold" bgcolor="#ffebcd"><td>Resiko Nutrisi</td><td colspan="2">'+jwb+'</td></tr>';
                }
            }

            if("jatuh" == jenis){
                var jwb = "";
                if(totalSkor >= 0 && totalSkor <= 24){
                    jwb = "Rendah";
                }else if (totalSkor >= 25 && totalSkor <= 44) {
                    jwb = "Sedang";
                }else if (totalSkor >= 45) {
                    jwb = "Tinggi";
                }

                if(isKesimpulan){
                    kesimpulan = '<tr style="font-weight: bold" bgcolor="#ffebcd"><td>Resiko Jatuh</td><td colspan="2">'+jwb+'</td></tr>';
                }
            }

            var table = '<table style="font-size: 12px" class="table table-bordered"><tr bgcolor="#ffebcd">' +
                '<tbody>' + body + rowTotal + kesimpulan + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_aud_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_aud_' + jenis));
            var url = contextPath+'/pages/images/minus-allnew.png';
            $('#btn_aud_' + jenis).attr('src', url);
            $('#btn_aud_' + jenis).attr('onclick', 'delRowAud(\'' + jenis + '\')');
        });
    }
}

function delRowAud(id) {
    $('#del_aud_' + id).remove();
    var url = contextPath+'/pages/images/icons8-plus-25.png';
    $('#btn_aud_' + id).attr('src', url);
    $('#btn_aud_' + id).attr('onclick', 'detailAud(\'' + id + '\')');
}

function audKeterangan(val, jenis) {
    if (val == "Lainnya" || val == "Ada" || val == "Obat" || val == "Makanan") {
        $('#form_aud_' + jenis).show();
    } else {
        $('#form_aud_' + jenis).hide();
    }
}