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
            va11 && va12 && va13 && va14 && va15!= '' && !cekTtd1 && !cekTtd2 && !cekTtd3 && !cekTtd4) {

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
                'jawaban': va11+', Panjang Badan '+va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lingkar Kepala',
                'jawaban': va13+', Lingkar Lengan '+va14,
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
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Dokter/Bidan',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Kamar Bersalin',
                'jawaban': canv3,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Perawatan Ruang Bayi',
                'jawaban': canv4,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if("pernyataan_bayi_lahir" == jenis){

        var va1 = $('#p1').val();
        var ttd1 = document.getElementById("ttd1_"+jenis);
        var ttd2 = document.getElementById("ttd2_"+jenis);
        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);

        if(va1 != '' && !cekTtd1 && !cekTtd2){

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
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Ibu',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'ttd',
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

        var ttd1 = document.getElementById("ttd1_"+jenis);
        var ttd2 = document.getElementById("ttd2_"+jenis);
        var ttd3 = document.getElementById("ttd3_"+jenis);

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);

        if (!cekTtd1 && !cekTtd2 && !cekTtd3) {

            data.push({
                'parameter': 'Tanggal Jam',
                'jawaban': va1+' '+va2,
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
                'jawaban': 'GCS '+va5+', TD '+va6+', Nadi '+va7+', RR '+va8+', Suhu '+va9+', TB '+va10+', BB '+va11+', GDA '+va12+', SPO2 '+va13,
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
                'jawaban': va16+' kali, dengan suami sekarang '+va17,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kawin pertama usia',
                'jawaban': va18+' tahun',
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
                'jawaban': va21+', Siklus menstruasi '+va22,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'HPHT',
                'jawaban': va23+', HPL '+va24,
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
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Pasien dan Keluarga',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Dokter',
                'jawaban': canv3,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'ttd',
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
            if(item.type = "checkbox"){
                if(item.checked){
                    if(va5 != ''){
                        va5 = va5+', '+item.value;
                    }else{
                        va5 = item.value;
                    }
                }
            }else{
                if(item.value != ''){
                    if(item.checked){
                        if(va5 != ''){
                            va5 = va5+', '+item.value;
                        }else{
                            va5 = item.value;
                        }
                    }
                }
            }
        });

        $.each(v7, function (i, item) {
            if(item.checked){
                if(va6 != ''){
                    va6 = va6+', '+item.value;
                }else{
                    va6 = item.value;
                }
            }
        });

        $.each(v8, function (i, item) {
            if(item.checked){
                if(va7 != ''){
                    va7 = va7+', '+item.value;
                }else{
                    va7 = item.value;
                }
            }
        });

        $.each(v9, function (i, item) {
            if(item.type = "checkbox"){
                if(item.checked){
                    if(va9 != ''){
                        va9 = va9+', '+item.value;
                    }else{
                        va9 = item.value;
                    }
                }
            }else{
                if(item.value != ''){
                    if(item.checked){
                        if(va9 != ''){
                            va9 = va9+', '+item.value;
                        }else{
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

        var ttd1 = document.getElementById("ttd1_"+jenis);

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
                'jawaban': va2+', Jam '+va3,
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
                'jawaban': 'BB : '+va10+' gram, PB : '+va11+' cm, UK : '+va12+'cm, UD : '+va13+' cm'+'. Nilai apgar menit ke '+va14,
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
            if(ketVa16 != undefined){
                ket = ketVa16;
            }
            data.push({
                'parameter': 'Nafas buatan',
                'jawaban': va16+' menit. '+ket,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'HB',
                'jawaban': va17+' gram %, Hot : '+va18+' %',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Golongan Darah',
                'jawaban': 'Bayi : '+va19+', Ibu : '+va20+', Ayah : '+va21,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var ket24 = "";
            if(va24 != undefined){
                ket24 = va24;
            }
            data.push({
                'parameter': 'Menit ke',
                'jawaban': va22+', Menit ke '+va23+'. '+ket24,
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
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if("data_laporan_pembedahan_rb" == jenis){

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
                'jawaban': va12+', s/d '+va13,
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

    if("laporan_pembedahan_rb" == jenis){

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

        var ttd1 = document.getElementById("ttd1_"+jenis);

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
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if("data_laporan_persalinan_rb" == jenis){

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
                'jawaban': 'Tanggal '+va7+', Jam '+va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if("laporan_persalinan_rb" == jenis){

        var va1 = $('#rb1').val();
        var va2 = $('#rb2').val();
        var va3 = $('#rb3').val();
        var va4 = $('#rb4').val();

        var ttd1 = document.getElementById("ttd1_"+jenis);

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
                    if("pernyataan_bayi_lahir" == jenis){
                        if("colspan" == item.parameter){
                            body += '<tr>' +
                                '<td colspan="2">' + jwb + '</td>' +
                                '</tr>';
                        }else if("TTD Bidan/Perawat" == item.parameter){
                            body += '<tr>' +
                                '<td colspan="2">' + '<p>'+item.parameter+'</p><img src="' + jwb + '" style="height: 80px">' + '</td>' +
                                '</tr>';
                        }else if("TTD Ibu" == item.parameter){
                            body += '<tr>' +
                                '<td colspan="2">' + '<div class="pull-right" style="margin-top: -122px"><p>'+item.parameter+'</p><img src="' + jwb + '" style="height: 80px"></div>' + '</td>' +
                                '</tr>';
                        }else{
                            body += '<tr>' +
                                '<td width="30%">' + item.parameter + '</td>' +
                                '<td>' + jwb + '</td>' +
                                '</tr>';
                        }
                    }else{
                        if ("ttd" == item.tipe) {
                            forTTD += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td colspan="2">' + '<img src="' + item.jawaban + '" style="height: 80px">' + '</td>' +
                                '</tr>';
                        }else{
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

function countJam(){
    var va12 = $('#la12').val();
    var va13 = $('#la13').val();
    if(va12 && va13 != ''){
        $('#la14').val(diff(va12, va13));
    }
}
