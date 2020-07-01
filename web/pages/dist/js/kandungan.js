function showModalRB(jenis) {
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }

    setDataPasien();

    $('#modal-rb-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveRB(jenis, ket) {

    var data = [];
    var cek = false;

    if ("identifikasi_bayi_lahir" == jenis) {

        var va1 = $('#bl1').val();
        var va2 = $('#bl2').val();
        var va3 = $('#bl3').val();
        var va4 = $('#bl4').val();
        var va5 = $('#bl5').val();
        var va6 = $('#bl6').val();
        var va7 = $('#bl7').val();
        var va8 = $('#bl8').val();
        var va9 = $('#bl9').val();
        var va10 = $('#bl10').val();
        var va11 = $('#bl11').val();
        var va12 = $('#bl12').val();
        var va13 = $('#bl13').val();
        var va14 = $('#bl14').val();
        var va15 = $('#bl15').val();

        var ttd1 = document.getElementById("ttd1");
        var ttd2 = document.getElementById("ttd2");
        var ttd3 = document.getElementById("ttd3");
        var ttd4 = document.getElementById("ttd4");

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);
        var cekTtd4 = isCanvasBlank(ttd4);

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 &&
            va11 && va12 && va13 && va14 && va15 != '' && !cekTtd1 && !cekTtd2 && !cekTtd3 && !cekTtd4) {

            data.push({
                'parameter': 'No Rekam Medik',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Ibu',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Ayah',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Lahir Bayi',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jam Lahir',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Bayi',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter/Bidan Penolong',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Warna Kulit',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Apgar Skor',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan',
                'jawaban': va11 + ', Panjang Badan ' + va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lingkar Kepala',
                'jawaban': va13 + ', Lingkar Lengan ' + va14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keterangan',
                'jawaban': va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });


            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv2 = ttd2.toDataURL("image/png"),
                canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv3 = ttd3.toDataURL("image/png"),
                canv3 = canv3.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv4 = ttd4.toDataURL("image/png"),
                canv4 = canv4.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD Penentu Jenis Kelamin',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Dokter/Bidan',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Kamar Bersalin',
                'jawaban': canv3,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Perawatan Ruang Bayi',
                'jawaban': canv4,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pernyataan_bayi_lahir" == jenis) {

        var va1 = $('#p1').val();
        var ttd1 = document.getElementById("ttd1_" + jenis);
        var ttd2 = document.getElementById("ttd2_" + jenis);
        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);

        if (va1 != '' && !cekTtd1 && !cekTtd2) {

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv2 = ttd2.toDataURL("image/png"),
                canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Tanggal',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'colspan',
                'jawaban': 'Saya menyatakan pada saat saya pulang, telah menerima bayi saya, memeriksa dan menyakinkan bahwa bayi tersebut adalah betul-betul anak saya. Saya mengecek pengenalnya adalah gelang yang berisikan keterangan pengenal nama dan rekam medik yang sesuai',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Bidan/Perawat',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Ibu',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("asesmen_ponek_rb" == jenis) {

        var va1 = $('#po1').val();
        var va2 = $('#po2').val();
        var va3 = $('[name=po3]:checked').val();
        var va4 = $('#po4').val();
        var va5 = $('#po5').val();
        var va6 = $('#po6').val();
        var va7 = $('#po7').val();
        var va8 = $('#po8').val();
        var va9 = $('#po9').val();
        var va10 = $('#po10').val();
        var va11 = $('#po11').val();
        var va12 = $('#po12').val();
        var va13 = $('#po13').val();
        var va14 = $('#po14').val();
        var va15 = $('#po15').val();
        var va16 = $('#po16').val();
        var va17 = $('#po17').val();
        var va18 = $('#po18').val();
        var va19 = $('#po19').val();
        var va20 = $('#po20').val();
        var va21 = $('#po21').val();
        var va22 = $('#po22').val();
        var va23 = $('#po23').val();
        var va24 = $('#po24').val();
        var va25 = $('#po25').val();
        var va26 = $('#po26').val();
        var va27 = $('#po27').val();
        var va28 = $('#po28').val();
        var va29 = $('#po29').val();
        var va30 = $('#po30').val();
        var va31 = $('#po31').val();
        var va32 = $('#po32').val();
        var va33 = $('#po33').val();
        var va34 = $('#po34').val();
        var va35 = $('#po35').val();
        var va36 = $('#po36').val();
        var va37 = $('#po37').val();
        var va38 = $('#po38').val();

        var ttd1 = document.getElementById("ttd1_" + jenis);
        var ttd2 = document.getElementById("ttd2_" + jenis);
        var ttd3 = document.getElementById("ttd3_" + jenis);

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);

        if (!cekTtd1 && !cekTtd2 && !cekTtd3) {

            data.push({
                'parameter': 'Tanggal Jam',
                'jawaban': va1 + ' ' + va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Triase',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnesa',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Fisik',
                'jawaban': 'GCS ' + va5 + ', TD ' + va6 + ', Nadi ' + va7 + ', RR ' + va8 + ', Suhu ' + va9 + ', TB ' + va10 + ', BB ' + va11 + ', GDA ' + va12 + ', SPO2 ' + va13,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit dahulu dan keluarga',
                'jawaban': va14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Obsetetri dan Gynekologi',
                'jawaban': va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status Perkawinan',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kawin',
                'jawaban': va16 + ' kali, dengan suami sekarang ' + va17,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kawin pertama usia',
                'jawaban': va18 + ' tahun',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pendidikan Terakhir',
                'jawaban': va19,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Manarce',
                'jawaban': va20,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dysmenorhea/tidak',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Siklus menstruasi',
                'jawaban': va21 + ', Siklus menstruasi ' + va22,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'HPHT',
                'jawaban': va23 + ', HPL ' + va24,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'KB Terakhir',
                'jawaban': va25,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alergi',
                'jawaban': va26,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemakaian obat saat ini',
                'jawaban': va27,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Anternal Care',
                'jawaban': va28,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Obstetri',
                'jawaban': va29,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Penunjang',
                'jawaban': va30,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja/Diagnosa Banding',
                'jawaban': va31,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan Terapi',
                'jawaban': va32,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindak lanjut',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rawat inap di',
                'jawaban': va33,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi rawat inap',
                'jawaban': va34,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dipulangkan / kontrol ke poli',
                'jawaban': va35,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dirujuk ke',
                'jawaban': va36,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pulang paksa / menolak tindakan',
                'jawaban': va37,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Meninggal',
                'jawaban': va38,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv2 = ttd2.toDataURL("image/png"),
                canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv3 = ttd3.toDataURL("image/png"),
                canv3 = canv3.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD Bidang jaga',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Pasien dan Keluarga',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Dokter',
                'jawaban': canv3,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("asesmen_awal_bayi_rb" == jenis) {

        var va1 = $('#rb1').val();
        var va2 = $('#rb2').val();
        var va3 = $('#rb3').val();
        var va4 = $('#rb4').val();

        var va5 = "";
        var va6 = "";
        var va7 = "";
        var va9 = "";

        var v5 = $('[name=rb5]');
        var v7 = $('[name=rb7]');
        var v8 = $('[name=rb8]');
        var v9 = $('[name=rb9]');

        $.each(v5, function (i, item) {
            if (item.type = "checkbox") {
                if (item.checked) {
                    if (va5 != '') {
                        va5 = va5 + ', ' + item.value;
                    } else {
                        va5 = item.value;
                    }
                }
            } else {
                if (item.value != '') {
                    if (item.checked) {
                        if (va5 != '') {
                            va5 = va5 + ', ' + item.value;
                        } else {
                            va5 = item.value;
                        }
                    }
                }
            }
        });

        $.each(v7, function (i, item) {
            if (item.checked) {
                if (va6 != '') {
                    va6 = va6 + ', ' + item.value;
                } else {
                    va6 = item.value;
                }
            }
        });

        $.each(v8, function (i, item) {
            if (item.checked) {
                if (va7 != '') {
                    va7 = va7 + ', ' + item.value;
                } else {
                    va7 = item.value;
                }
            }
        });

        $.each(v9, function (i, item) {
            if (item.type = "checkbox") {
                if (item.checked) {
                    if (va9 != '') {
                        va9 = va9 + ', ' + item.value;
                    } else {
                        va9 = item.value;
                    }
                }
            } else {
                if (item.value != '') {
                    if (item.checked) {
                        if (va9 != '') {
                            va9 = va9 + ', ' + item.value;
                        } else {
                            va9 = item.value;
                        }
                    }
                }
            }
        });

        var va10 = $('#rb10').val();
        var va11 = $('#rb11').val();
        var va12 = $('#rb12').val();
        var va13 = $('#rb13').val();
        var va14 = $('#rb14').val();

        var va16 = $('#rb15').val();
        var ketVa16 = $('[name=rb161]:checked').val();

        var va17 = $('#rb17').val();
        var va18 = $('#rb18').val();
        var va19 = $('#rb19').val();
        var va20 = $('#rb20').val();
        var va21 = $('#rb21').val();
        var va22 = $('#rb22').val();
        var va23 = $('#rb23').val();

        var va24 = $('[name=rb241]:checked').val();
        var va25 = $('#rb24').val();
        var va26 = $('#rb25').val();
        var va27 = $('#rb26').val();

        var ttd1 = document.getElementById("ttd1_" + jenis);

        var cekTtd1 = isCanvasBlank(ttd1);

        if (!cekTtd1) {

            data.push({
                'parameter': 'Tempat Lahir',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Lahir',
                'jawaban': va2 + ', Jam ' + va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnesa',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penderita IBU, Gangguan Hamil',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Partus',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Komplikasi',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Bayi',
                'jawaban': 'BB : ' + va10 + ' gram, PB : ' + va11 + ' cm, UK : ' + va12 + 'cm, UD : ' + va13 + ' cm' + '. Nilai apgar menit ke ' + va14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            var ket = "";
            if (ketVa16 != undefined) {
                ket = ketVa16;
            }
            data.push({
                'parameter': 'Nafas buatan',
                'jawaban': va16 + ' menit. ' + ket,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'HB',
                'jawaban': va17 + ' gram %, Hot : ' + va18 + ' %',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Golongan Darah',
                'jawaban': 'Bayi : ' + va19 + ', Ibu : ' + va20 + ', Ayah : ' + va21,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var ket24 = "";
            if (va24 != undefined) {
                ket24 = va24;
            }
            data.push({
                'parameter': 'Menit ke',
                'jawaban': va22 + ', Menit ke ' + va23 + '. ' + ket24,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa',
                'jawaban': va25,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi rawat inap',
                'jawaban': va26,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban': va27,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD DPJP',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("data_laporan_pembedahan_rb" == jenis) {

        var va1 = $('#la1').val();
        var va2 = $('#la2').val();
        var va3 = $('#la3').val();
        var va4 = $('#la4').val();
        var va5 = $('#la5').val();
        var va6 = $('#la6').val();
        var va7 = $('#la7').val();
        var va8 = $('#la8').val();
        var va9 = $('#la9').val();
        var va10 = $('[name=la10]:checked').val();
        var va11 = $('#la11').val();
        var va12 = $('#la12').val();
        var va13 = $('#la13').val();
        var va14 = $('#la14').val();
        var va15 = $('[name=la15]:checked').val();
        var va16 = $('[name=la16]:checked').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 &&
            va11 && va12 && va13 && va14 != '' && va10 && va15 && va16 != undefined) {

            data.push({
                'parameter': 'Diagnosa Pra Bedah',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Pasca Bedah',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter Operator',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Asisten I',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Instrumen',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter Anestesi',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Asisten Anestesi',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan Operasi',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jaringan / Cairan yang diambil',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Operasi',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Pembiusan',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mulai Operasi',
                'jawaban': va12 + ', s/d ' + va13,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lama Operasi',
                'jawaban': va14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Macam Operasi',
                'jawaban': va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Pemeriksaan PA',
                'jawaban': va16,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("laporan_pembedahan_rb" == jenis) {

        var va1 = $('#rb1').val();
        var va2 = $('#rb2').val();
        var va3 = $('#rb3').val();
        var va4 = $('#rb4').val();
        var va5 = $('#rb5').val();
        var va6 = $('#rb6').val();
        var va7 = $('#rb7').val();
        var va8 = $('#rb8').val();
        var va9 = $('#rb9').val();
        var va10 = $('#rb10').val();
        var va11 = $('#rb11').val();
        var va12 = $('#rb12').val();

        var ttd1 = document.getElementById("ttd1_" + jenis);

        var cekTtd1 = isCanvasBlank(ttd1);

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 &&
            va11 && va12 != '' && !cekTtd1) {

            data.push({
                'parameter': 'Persiapan Operasi',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Posisi Pasien',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Desifektan',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Insisi kulit dan pembukaan lapangan operasi',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pendapatan lapangan operasi dan kulit',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Apa yang dikerjakan',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penutupan lapangan operasi',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Komplikasi Operasi',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hasil Operasi',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diskripsi jaringan yang diambil',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lain-lain yang perlu',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesimpulan',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD Dokter Operator',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("data_laporan_persalinan_rb" == jenis) {

        var va1 = $('#la1').val();
        var va2 = $('#la2').val();
        var va3 = $('#la3').val();
        var va4 = $('#la4').val();
        var va5 = $('#la5').val();
        var va6 = $('[name=la6]:checked').val();
        var va7 = $('#la7').val();
        var va8 = $('#la8').val();

        if (va1 && va2 && va3 && va4 && va5 && va7 && va8 != undefined && va6 != undefined) {

            data.push({
                'parameter': 'Diagnosa Primer',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Sekunder',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter / Bidan Penolong',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Bidang Pendamping',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'HPHT',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status Ketuban',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pecah Ketuban',
                'jawaban': 'Tanggal ' + va7 + ', Jam ' + va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("laporan_persalinan_rb" == jenis) {

        var va1 = $('#rb1').val();
        var va2 = $('#rb2').val();
        var va3 = $('#rb3').val();
        var va4 = $('#rb4').val();

        var ttd1 = document.getElementById("ttd1_" + jenis);

        var cekTtd1 = isCanvasBlank(ttd1);

        if (va1 && va2 && va3 && va4 != '' && !cekTtd1) {

            data.push({
                'parameter': 'Kala I',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kala II',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kala III',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kalan IV',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD Dokter / Bidan Penolong',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if("add_tindakan_rb" == jenis){
        var va1 = $('#rb1').val();
        var va2 = $('#rb2').val();
        var va3 = $('#rb3').val();
        var va4 = $('#rb4').val();
        var va5 = $('#rb5').val();
        var va6 = $('#rb6').val();
        var va7 = $('#rb7').val();
        var va8 = $('#rb8').val();
        var va9 = $('#rb9').val();
        var va10= $('#rb10').val();
        var va11 = $('#rb11').val();

        var tindakan = $('#tindakan_rb').val();
        var parameter = $('[name=parameter]');
        var tanda = $('[name=tanda]');

        var ttd1 = document.getElementById("ttd1");
        var ttd2 = document.getElementById("ttd2");
        var ttd3 = document.getElementById("ttd3");
        var ttd4 = document.getElementById("ttd4");
        var ttd5 = document.getElementById("ttd5");

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);
        var cekTtd4 = isCanvasBlank(ttd4);
        var cekTtd5 = isCanvasBlank(ttd5);

        if (va1 && va2 && va3 && va4 && tindakan != '' && !cekTtd1 && !cekTtd2 && !cekTtd3 && !cekTtd4 && !cekTtd5) {

            data.push({
                'parameter': 'pernyataan',
                'jawaban': 'Pemberian Informasi dan Persetujuan Tindakan Kedokteran '+tindakan,
                'keterangan': ket,
                'jenis': tindakan,
                'tipe':'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal',
                'jawaban': va1,
                'keterangan': ket,
                'jenis': tindakan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter Penanggung Jawab',
                'jawaban': va2,
                'keterangan': ket,
                'jenis': tindakan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemberi Informasi',
                'jawaban': va3,
                'keterangan': ket,
                'jenis': tindakan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penerima Informasi',
                'jawaban': va4,
                'keterangan': ket,
                'jenis': tindakan,
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Jenis Informasi',
                'informasi': 'Isi Informasi',
                'jawaban': 'Check Informasi',
                'keterangan': ket,
                'jenis': tindakan,
                'tipe':'bold',
                'id_detail_checkup': idDetailCheckup
            });

            $.each(parameter, function (i, item) {
                var informasi = $('[name=informasi'+i+']');
                var info = "";
                $.each(informasi, function (idx, itemx) {
                    console.log(itemx.type);
                    if(itemx.type == 'checkbox'){
                        if(itemx.checked){
                            if(info != ''){
                                info = info +', '+itemx.value;
                            }else{
                                info = itemx.value;
                            }
                        }
                    }
                    if(itemx.type == 'radio'){
                        if(itemx.checked){
                            if(info != ''){
                                info = info +', '+itemx.value;
                            }else{
                                info = itemx.value;
                            }
                        }
                    }
                    if(itemx.type == 'text'){
                        if(itemx.value != ''){
                            if(info != ''){
                                info = info +', '+itemx.value;
                            }else{
                                info = itemx.value;
                            }
                        }
                    }
                    if(itemx.type == 'hidden'){
                        if(itemx.value != ''){
                            if(info != ''){
                                info = info +', '+itemx.value;
                            }else{
                                info = itemx.value;
                            }
                        }
                    }
                });

                var tdn = "";
                if(tanda[i].checked){
                    tdn = tanda[i].value;
                }

                data.push({
                    'parameter': item.value,
                    'informasi': info,
                    'jawaban': tdn,
                    'keterangan': ket,
                    'jenis': tindakan,
                    'tipe':'info',
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
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerangkan hal-hal di atas secara benar dan jelas dengan memberikan kesempatakan bertanya dan atau diskusi',
                'jawaban': canv1,
                'keterangan': ket,
                'jenis': tindakan,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerima informasi sebagaimana di atas dan telah memahaminya',
                'jawaban': canv2,
                'keterangan': ket,
                'jenis': tindakan,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'pernyataan',
                'jawaban': 'Persetujuan Tindakan Medis',
                'keterangan': ket,
                'jenis': tindakan,
                'tipe':'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'penyataan',
                'jawaban': 'Yang bertanda tangan dibawah ini, Saya '+va5+' ' +
                    'tanggal lahir '+va6+', '+va7+' dengan ini menyatakan persetujuan untuk dilakukan tindakan '+va8+' ' +
                    'terhadap pasien Bernama '+va9+' tanggal lahir '+va10+', Alamat '+va11+'.' +
                    'Saya memahami perlunya dan manfaat tindakan tersebut sebagaimana telah dijelaskan seperti diatas ' +
                    'kepada saya termasuk resiko dan komplikasi yang timbul ' +
                    'Saya juga menyadari bahwa oleh karena itu ilmu kedokteran bukan ilmu pasti, maka keberhasilan tindakan ' +
                    'kedokteran bukan keniscayaan, tetapi tergantung kepada izin Tuhan Yang maha Esa. Tanggal '+converterDate(new Date)+', Jam '+converterTime(new Date()),
                'keterangan': ket,
                'jenis': tindakan,
                'tipe':'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD yang menyatakan',
                'jawaban': canv3,
                'keterangan': ket,
                'jenis': tindakan,
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saksi I',
                'jawaban': canv4,
                'keterangan': ket,
                'jenis': tindakan,
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saksi II',
                'jawaban': canv5,
                'keterangan': ket,
                'jenis': tindakan,
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        $('#save_rb_' + jenis).hide();
        $('#load_rb_' + jenis).show();
        dwr.engine.setAsync(true);
        KandunganAction.save(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_rb_' + jenis).show();
                    $('#load_rb_' + jenis).hide();
                    $('#modal-rb-' + jenis).modal('hide');
                    $('#warning_rb_' + ket).show().fadeOut(5000);
                    $('#msg_rb_' + ket).text("Berhasil menambahkan data...");
                    $('#modal-rb-' + jenis).scrollTop(0);
                } else {
                    $('#save_rb_' + jenis).show();
                    $('#load_rb_' + jenis).hide();
                    $('#warning_rb_' + jenis).show().fadeOut(5000);
                    $('#msg_rb_' + jenis).text(res.msg);
                    $('#modal-rb-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_rb_' + jenis).show().fadeOut(5000);
        $('#msg_rb_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-rb-' + jenis).scrollTop(0);
    }
}

function detailRB(jenis) {

    if (jenis != '') {
        var head = "";
        var body = "";
        var totalSkor = 0;
        var cekSkor = false;
        var first = "";
        var last = "";
        var tgl = "";
        var cekData = false;
        var rowTotal = "";
        var kesimpulan = "";
        var isKesimpulan = false;
        var forTTD = "";

        KandunganAction.getListDetail(idDetailCheckup, jenis, function (res) {

            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var jwb = "";
                    if (item.jawaban != '') {
                        jwb = item.jawaban;
                    }
                    if ("pernyataan_bayi_lahir" == jenis) {
                        if ("colspan" == item.parameter) {
                            body += '<tr>' +
                                '<td colspan="2">' + jwb + '</td>' +
                                '</tr>';
                        } else if ("TTD Bidan/Perawat" == item.parameter) {
                            body += '<tr>' +
                                '<td colspan="2">' + '<p>' + item.parameter + '</p><img src="' + jwb + '" style="height: 80px">' + '</td>' +
                                '</tr>';
                        } else if ("TTD Ibu" == item.parameter) {
                            body += '<tr>' +
                                '<td colspan="2">' + '<div class="pull-right" style="margin-top: -122px"><p>' + item.parameter + '</p><img src="' + jwb + '" style="height: 80px"></div>' + '</td>' +
                                '</tr>';
                        } else {
                            body += '<tr>' +
                                '<td width="30%">' + item.parameter + '</td>' +
                                '<td>' + jwb + '</td>' +
                                '</tr>';
                        }
                    }else if("tindakan_rb" == jenis){
                        if ("colspan" == item.tipe) {
                            body += '<tr>' +
                                '<td colspan="3">' + jwb + '</td>' +
                                '</tr>';
                        } else if ("info" == item.tipe) {
                            body += '<tr>' +
                                '<td width="25%">' + item.parameter + '</td>' +
                                '<td >' + item.informasi + '</td>' +
                                '<td width="20%" align="center">' +cekIcons(jwb)+ '</td>' +
                                '</tr>';
                        } else if ("ttd" == item.tipe) {
                            body += '<tr>' +
                                '<td colspan="2">' + item.parameter + '</td>' +
                                '<td>' + '<img src="' + item.jawaban + '" style="height: 80px">' + '</td>' +
                                '</tr>';
                        } else if("bold" == item.tipe){
                            body += '<tr style="font-weight: bold">' +
                                '<td width="25%">' + item.parameter + '</td>' +
                                '<td >' + item.informasi + '</td>' +
                                '<td width="20%" align="center">' +cekIcons(jwb)+ '</td>' +
                                '</tr>';
                        } else{
                            body += '<tr>' +
                                '<td width="30%">' + item.parameter + '</td>' +
                                '<td colspan="2">' + jwb + '</td>' +
                                '</tr>';
                        }
                    } else {
                        if ("ttd" == item.tipe) {
                            forTTD += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td colspan="2">' + '<img src="' + item.jawaban + '" style="height: 80px">' + '</td>' +
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
                    isKesimpulan = true;
                });
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }

            if (cekSkor) {
                first = '<tr style="font-weight: bold"><td>Parameter</td><td>Jawaban</td><td align="center">Skor</td></tr>';
                last = '<tr style="font-weight: bold"><td colspan="2">Total</td><td align="center">' + totalSkor + '</td></tr>'

                if ("resiko_jatuh" == jenis) {
                    var jwb = "";
                    if (totalSkor >= 0 && totalSkor <= 24) {
                        jwb = "Rendah";
                    } else if (totalSkor >= 25 && totalSkor <= 44) {
                        jwb = "Sedang";
                    } else if (totalSkor >= 45) {
                        jwb = "Tinggi";
                    }

                    if (isKesimpulan) {
                        kesimpulan = '<tr style="font-weight: bold" bgcolor="#ffebcd"><td colspan="2">Resiko Jatuh</td><td align="center">' + jwb + '</td></tr>';
                    }
                }
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + kesimpulan + forTTD + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_rb_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_rb_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_rb_' + jenis).attr('src', url);
            $('#btn_rb_' + jenis).attr('onclick', 'delRowRB(\'' + jenis + '\')');
        });
    }
}

function delRowRB(id) {
    $('#del_rb_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_rb_' + id).attr('src', url);
    $('#btn_rb_' + id).attr('onclick', 'detailRB(\'' + id + '\')');
}

function countJam() {
    var va12 = $('#la12').val();
    var va13 = $('#la13').val();
    if (va12 && va13 != '') {
        $('#la14').val(diff(va12, va13));
    }
}

function pilihTindakanRB(val) {
    if (val != '') {
        $('#form-tindakan-rb').show();
        $('#rb8').val(val);
        var body = "";
        $.each(tindakanRB(val), function (i, item) {

            var params = "";
            var informasi = "";

            if("i" == item.keterangan ){
                var info = item.informasi.split("|");
                $.each(info, function (idx, itemx) {
                    if(itemx != ''){
                        informasi += '<input class="form-control" name="informasi'+i+'" id="info'+i+'" placeholder="'+itemx+'" onchange="$(\'#info'+i+'\').val(\''+itemx+' '+'\'+this.value)">';
                    }else{
                        informasi += '<input class="form-control" name="informasi'+i+'" id="info'+i+'" placeholder="'+itemx+'">';
                    }
                });
            }
            if("r" == item.keterangan ){
                var info = item.informasi.split("|");
                $.each(info, function (idx, itemx) {
                    informasi += '<div class="row">' +
                                '<div class="custom02" style="margin-left: 15px">\n' +
                                '<input type="radio" value="'+itemx+'" id="informasi'+i+idx+'" name="informasi'+i+'"/><label for="informasi'+i+idx+'">' +itemx+'</label>\n' +
                                '</div>'+
                                '</div>';
                });
            }
            if("c" == item.keterangan ){
                var info = item.informasi.split("|");
                $.each(info, function (idx, itemx) {
                    informasi += '<div class="row">' +
                        '<div class="form-check02">\n' +
                        '<input type="checkbox" name="informasi'+i+'" id="informasi' + i + idx +'" value="' + itemx + '">\n' +
                        '<label for="informasi' + i + idx+ '"></label> ' + itemx + '\n' +
                        '</div>' +
                        '</div>';
                });
            }
            if("l" == item.keterangan ){
                informasi += item.informasi+'<input type="hidden" value="'+item.informasi+'" name="informasi'+i+'">';
            }

            var cekList = '<div class="row">' +
                '<div class="form-check02">\n' +
                '<input type="checkbox" name="tanda" id="tanda' + i +'" value="Ya">\n' +
                '<label for="tanda' + i +'"></label>'+
                '</div>' +
                '</div>'

            body += '<tr>' +
                '<td width="25%">'+item.parameter+'<input name="parameter" type="hidden" value="'+item.parameter+'"></td>' +
                '<td>'+informasi+'</td>' +
                '<td align="center" width="15%">'+cekList+'</td>' +
                '</tr>';
        });

        $('#body_tindakan_rb').html(body);

    } else {
        $('#form-tindakan-rb').hide();
        $('#rb8').val('');
        $('#body_tindakan_rb').html('');
    }
}

function tindakanRB(jenis) {

    var data = [];
    var dataCari = [];

    data.push({
        'jenis': 'Kurretage',
        'parameter': 'Diagnosa',
        'informasi': 'Blighted Ovum|Menometrorargia|Abortus Incomplet|DUB|Sisa Kehamilan',
        'keterangan': 'c'
    });
    data.push({
        'jenis': 'Kurretage',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'USG|Pendarahan',
        'keterangan': 'r'
    });
    data.push({
        'jenis': 'Kurretage',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Delatasi|Kurretage',
        'keterangan': 'r'
    });
    data.push({
        'jenis': 'Kurretage',
        'parameter': 'Indikasi Tindakan',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'Kurretage',
        'parameter': 'Tata Cara',
        'informasi': 'Mengeluarkan jaringan abnormal/ sisa kehamilan dalam rongga rahim, menggunakan instrumen kurretage',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Kurretage',
        'parameter': 'Tujuan',
        'informasi': 'Mengerluarkan isi uterus (jaringan abnormal)',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Kurretage',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan, perforasi uterus, cidera usus, cidera kandung kemih',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Kurretage',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi, infeksi dalam rongga perut',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Kurretage',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Kurretage',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'Sectio Caesaria',
        'parameter': 'Diagnosa',
        'informasi': 'G|P',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'Sectio Caesaria',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'USG|Klinis',
        'keterangan': 'r'
    });
    data.push({
        'jenis': 'Sectio Caesaria',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Low Segement Caesarean Section (LSCS)',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Sectio Caesaria',
        'parameter': 'Indikasi Tindakan',
        'informasi': 'Ibu|Janin',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'Sectio Caesaria',
        'parameter': 'Tata Cara',
        'informasi': 'Incisi laparatomy, incisi segmen bawah rahim, prosedur melahirkan janin',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Sectio Caesaria',
        'parameter': 'Tujuan',
        'informasi': 'Evakuasi / melahirkan bayi melalui insisi perut',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Sectio Caesaria',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan > 1 liter, robekan rahim, cedera usus, sedera kandung kemih, perawatan ICU, Angkat rahim, kematian ibu',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Sectio Caesaria',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi, infeksi organ dalam rongga perut',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Sectio Caesaria',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Sectio Caesaria',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'Laparatomy',
        'parameter': 'Diagnosa',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'Laparatomy',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'USG|Anamnesa',
        'keterangan': 'r'
    });
    data.push({
        'jenis': 'Laparatomy',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Laparatomy',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'Laparatomy',
        'parameter': 'Indikasi Tindakan',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'Laparatomy',
        'parameter': 'Tata Cara',
        'informasi': 'Incisi laparatomy, identifikasi, pengangkatan tumor / jaringan infeksi',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Laparatomy',
        'parameter': 'Tujuan',
        'informasi': 'Evakuasi tumor',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Laparatomy',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan, infertelisasi, pegangkatan rahim, cidera usus, kematian',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Laparatomy',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi, infeksi organ dalam rongga perut',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Laparatomy',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Laparatomy',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });

    if(jenis != ''){
        $.each(data, function (i, item) {
            if(jenis == item.jenis){
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
