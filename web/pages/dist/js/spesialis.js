function showModalSPS(jenis) {
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }
    setDataPasien();

    if("pemeriksaan_bedah" == jenis){
        var url = "";
        if ("Laki-Laki" == jenisKelamin) {
            url = contextPath+'/pages/images/penanda-laki-laki.jpg';
        } else {
            url = contextPath+'/pages/images/penanda-perempuan.jpg';
        }
        loadImgToCanvas(url, 'area_canvas');
    }

    if("anamnesa_pemeriksaan_paru" == jenis){
        var url = contextPath+'/pages/images/paru-1.png';
        loadImgToCanvas(url, 'area_paru');
    }

    if("pemeriksaan_ortopedi" == jenis){
        var url = contextPath+'/pages/images/ortopedi.png';
        loadImgToCanvas(url, 'area_ortopedi');
    }

    if("anamnesa_pemeriksaan_ginjal" == jenis){
        var url = contextPath+'/pages/images/ginjal-1.png';
        loadImgToCanvas(url, 'area_ginjal');
    }

    if("anamnesa_pemeriksaan_jantung" == jenis){
        var url = contextPath+'/pages/images/jantung.png';
        loadImgToCanvas(url, 'area_jantung');
    }

    $('#modal-sps-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveSPS(jenis, ket) {
    var data = [];
    var cek = false;

    if ("keadaan_umum_tht" == jenis) {
        var va1 = $('#kut1').val();
        var va2 = $('#kut2').val();
        var va3 = $('#kut3').val();
        var va4 = $('#kut4').val();
        var va5 = $('#kut5').val();
        var va6 = $('#kut6').val();

        if (va1 && va3 && va4 && va5 && va6 != '') {
            data.push({
                'parameter': 'Tanggal Kunjungan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnese',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Dahulu',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Alergi Obat',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status Lokalis',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("tht_tht" == jenis) {
        var va1 = $('#tht1').val();
        var va2 = $('#tht2').val();
        var va3 = $('#tht3').val();
        var va4 = $('#tht4').val();
        var va5 = $('#tht5').val();
        var va6 = $('#tht6').val();
        var va7 = $('#tht7').val();
        var va8 = $('#tht8').val();
        var va9 = $('#tht9').val();
        var va10 = $('#tht10').val();
        var va11 = $('#tht11').val();
        var va12 = $('#tht12').val();
        var va13 = $('#tht13').val();
        var va14 = $('#tht14').val();
        var va15 = $('#tht15').val();
        var va16 = $('#tht16').val();
        var va17 = $('#tht17').val();
        var va18 = $('#tht18').val();
        var va19 = $('#tht19').val();
        var va20 = $('#tht20').val();

        if (va1 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 &&
            va11 && va12 && va13 && va14 && va15 && va16 && va17 && va18 && va19 && va20 != '') {
            data.push({
                'parameter': 'tht',
                'jawaban':
                    'Daun Telinga : ' + va1 +'|'+
                    'Kavum nasi : ' + va12 +'|'+
                    'Orofaring : ' + va18 +'='+

                    'Liang Telinga : ' + va2 +'|'+
                    'Konka Inferior : ' + va13 +'|'+
                    'Nasofaring : ' + va19 +'='+

                    'Membran timpani : ' + va3 +'|'+
                    'Konka Medis : ' + va14 +'|'+
                    'Laring : ' + va20+'='+

                    'Reflek Cahaya : ' + va4 +'|'+
                    'Meatus Media : ' + va15 +'='+

                    'Rinne : ' + va5 +'|'+
                    'Septum : ' + va16 +'='+

                    'Waber : ' + va6 +'|'+
                    'Lainnya : ' + va17 + '=' +

                    'Scabach : ' + va7 +'='+

                    'Audimetri : ' + va8 +'='+

                    'Timpanometri : ' + va9 +'='+

                    'Fungsi tuba eustachius : ' + va10 +'='+

                    'Reflek Stapedius : ' + va11,

                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pemeriksaan_tht" == jenis) {
        var va1 = $('#pt1').val();
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va6 = $('#pt6').val();
        var va7 = $('#pt7').val();
        var va8 = $('#pt8').val();
        var va9 = $('#pt9').val();

        if (va1 && va3 && va4 && va5 && va6 && va7 && va8 && va9 != '') {
            data.push({
                'parameter': 'Kepala',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Leher',
                'jawaban': va2,
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
                'parameter': 'Pemeriksaan Lainnya/ Tes Pendengaran',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi / Pengobatan',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan / Rencana Tindakan',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kontrol Kembali',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Rawat Inap',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if("edukasi_tht" == jenis || "edukasi_pd" == jenis || "edukasi_bedah" == jenis ||
       "edukasi_paru" == jenis || "edukasi_anak" == jenis || "edukasi_neurologi" == jenis ||
       "edukasi_obstetri" == jenis || "edukasi_ortopedi" == jenis || "edukasi_ginjal" == jenis ||
       "edukasi_ginjal" == jenis || "edukasi_jantung" == jenis){

        var va1 = $('[name=et]:checked').val();
        var va2 = $('#ket_nama').val();
        var va3 = $('#ket_hubungan').val();
        var va4 = $('#ket_alasan').val();
        var temp = "";
        var label = 'Edukasi awal, tentang diagnosis, rencana, tujuan terapi kepada : ';
        var ttd1 = document.getElementById("et4");
        var ttd2 = document.getElementById("et5");
        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);

        if(va1 == "Pasien"){
            temp = label+va1;
        }else if(va1 == "Keluarga"){
            if(va2 && va3 != ''){
                temp = label+"Keluarga pasien, nama : "+va2+", Hubungan dengan pasien : "+va3;
            }
        }else if(va1 == "Tidak"){
            if(va4 != ''){
                temp = label+"Tidak dapat memberi edukasi kepada pasien atau karena : "+va4;
            }
        }

        if (temp != '' && !cekTtd1 && !cekTtd2) {
            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            var canv2 = ttd2.toDataURL("image/png"),
                canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': temp,
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Dokter',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("anamnesa_pd" == jenis) {
        var va1 = $('#kut1').val();
        var va2 = $('#kut2').val();
        var va3 = $('#kut3').val();
        var va4 = $('#kut4').val();
        var va5 = $('#kut5').val();
        var va6 = $('#kut6').val();
        var va7 = $('#kut7').val();
        var va8 = $('#kut8').val();
        var va9 = $('#kut9').val();
        var va10 = $('#kut10').val();

        if (va1 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 != '') {
            data.push({
                'parameter': 'Tanggal Kunjungan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnese',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Dahulu',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Keluarga',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alergi Imunisasi',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hobi',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Olahraga',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kebiasaan Makan/Minum',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Merokok/minuman alkohol',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pemeriksaan_pd" == jenis || "pemeriksaan_anak" == jenis) {
        var va1 = $('#pt1').val();
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va6 = $('#pt6').val();
        var va7 = $('#pt7').val();
        var va8 = $('#pt8').val();
        var va9 = $('#pt9').val();
        var va10 = $('#pt10').val();
        var va11 = $('#pt11').val();
        var va12 = $('#pt12').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va11 && va12 != '') {
            data.push({
                'parameter': 'Kondisi Umum',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kepala Leher',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Thorax',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Abdomen',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ekstremitas',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Penunjang',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan / Rencana Tindakan',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kontrol Kembali',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Rawat Inap',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("anamnesa_bedah" == jenis) {
        var va1 = $('#kut1').val();
        var va2 = $('#kut2').val();
        var va3 = $('#kut3').val();
        var va4 = $('#kut4').val();
        var va5 = $('#kut5').val();
        var va6 = $('#kut6').val();

        if (va1 && va3 && va4 && va5 && va6 != '') {
            data.push({
                'parameter': 'Tanggal Kunjungan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnese',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Dahulu',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Obat-Obat yang sedang dikonsumsi',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Alergi Obat',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pemeriksaan_bedah" == jenis) {
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va6 = $('#pt6').val();
        var va7 = $('#pt7').val();
        var va8 = $('#pt8').val();
        var operesi = "Y";

        var canvasArea = document.getElementById('area_canvas');
        var canvasCek = document.getElementById('area_cek');
        if(canvasArea.toDataURL() == canvasCek.toDataURL()){
            operesi = "N";
        }
        if (va2 && va3 && va4 && va5 && va6 && va7 && va8 != '' && operesi == "Y") {

            var canv = canvasArea.toDataURL("image/png"),
                canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Pemeriksaan Fisik / Status Lokalitas',
                'jawaban': canv,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'gambar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Penunjang',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan / Rencana Tindakan',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anjuran Kontrol Kembali',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Rawat Inap',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("anamnesa_pemeriksaan_paru" == jenis) {
        var va1 = $('#pt1').val();
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va6 = $('#pt6').val();
        var va7 = $('#pt7').val();
        var va8 = $('#pt8').val();
        var paru = "Y";

        var canvasArea = document.getElementById('area_paru');
        var canvasCek = document.getElementById('area_cek');

        if(canvasArea.toDataURL() == canvasCek.toDataURL()){
            paru = "N";
        }
        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 != '' && paru == "Y") {

            data.push({
                'parameter': 'Anamnesa',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv = canvasArea.toDataURL("image/png"),
                canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Pemeriksaan Fisik / Status Lokalitas',
                'jawaban': canv,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'gambar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Penunjang',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan / Rencana Tindakan',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anjuran Kontrol Kembali',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Rawat Inap',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("anamnesa_anak" == jenis) {
        var va1 = $('#kut1').val();
        var va2 = $('#kut2').val();
        var va3 = $('#kut3').val();
        var va4 = $('#kut4').val();
        var va5 = $('#kut5').val();
        var va6 = $('#kut6').val();
        var va7 = $('#kut7').val();
        var va8 = $('#kut8').val();
        var va9 = $('#kut9').val();
        var va10 = $('#kut10').val();

        if (va1 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 != '') {
            data.push({
                'parameter': 'Tanggal Kunjungan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnese',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Dahulu',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Keluarga',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Imunisasi',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Tumbuh Kembang',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Makan',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Saudara Kandung',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Sosial Ekonomi',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("anamnesa_neurologi" == jenis) {
        var va1 = $('#kut1').val();
        var va2 = $('#kut2').val();
        var va3 = $('#kut3').val();
        var va4 = $('#kut4').val();
        var va5 = $('#kut5').val();

        if (va1 && va3 && va4 && va5 != '') {
            data.push({
                'parameter': 'Tanggal Kunjungan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnese',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Dahulu',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Keluarga',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pemeriksaan_neurologi" == jenis) {
        var va1 = $('#pt1').val();
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = "";
        var v5 = $('[name=pt5]:checked').val();
        if(v5 != undefined){
            var ket = $('#ket_pt5').val();
            if("Kelainan" == v5 || "Lainnya" == v5){
                va5 = v5+' : '+ket;
            }else{
                va5 = v5;
            }
        }
        var va6 = "";
        var v6 = $('[name=pt6]:checked').val();
        if(v6 != undefined){
            var ket = $('#ket_pt6').val();
            if("Bentuk" == v6 || "Lainnya" == v6){
                va6 = v6+' : '+ket;
            }else{
                va6 = v6;
            }
        }
        var va7 = $('#pt7').val();
        var va8 = $('#pt8').val();
        var va9 = $('#pt9').val();
        var va10 = $('#pt10').val();
        var va11 = $('#pt11').val();
        var va12 = $('#pt12').val();
        var va13 = $('#pt13').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va11 && va12 && va13 != '') {

            data.push({
                'parameter': 'Pemeriksaan Fisik',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Otonom',
                'jawaban': 'Keringat : '+va2+', BAB : '+va3+', BAK : '+va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Columna vertebralis',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Muskuloskeletal',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Neurologi',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Penunjang',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan / Rencana Tindakan',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anjuran Kontrol Kembali',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Rawat Inap',
                'jawaban': va13,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("anamnesa_obstetri" == jenis) {
        var va1 = $('#kut1').val();
        var va2 = $('#kut2').val();
        var va3 = $('#kut3').val();
        var va4 = $('#kut4').val();
        var va5 = $('#kut5').val();
        var va6 = $('#kut6').val();
        var va7 = $('#kut7').val();
        var va8 = $('#kut8').val();
        var va9 = $('#kut9').val();
        var va10 = $('#kut10').val();
        var va11 = $('#kut11').val();
        var va12 = $('#kut12').val();

        if (va1 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va11 && va12 != '') {
            data.push({
                'parameter': 'Tanggal Kunjungan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnese',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'G '+va4+' P '+va5+', Haid Terakhir',
                'jawaban': va6+', Taksiran persalinan '+va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Kehamilan Sekarang',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Persalinan',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Lain',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Keluarga',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat KB',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pemeriksaan_obstetri" == jenis) {
        var va1 = $('#pt1').val();
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va6 = $('#pt6').val();
        var va7 = $('#pt7').val();
        var va8 = $('#pt8').val();
        var va9 = $('#pt9').val();
        var va10 = $('#pt10').val();
        var va11 = $('#pt11').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va11 != '') {

            data.push({
                'parameter': 'TFU',
                'jawaban': va1 +' Djj, '+'Letak : '+va2+', His : '+va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'VT',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'USG Hamil',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penyulit',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pengobatan / Rencana Tindakan',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anjuran Kontrol Kembali',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Rawat Inap',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("anamnesa_ortopedi" == jenis) {
        var va1 = $('#kut1').val();
        var va2 = $('#kut2').val();
        var va3 = $('#kut3').val();
        var va4 = $('#kut4').val();

        if (va1 && va2 && va3 && va4 != '') {
            data.push({
                'parameter': 'Tanggal Kunjungan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Dahulu',
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
            cek = true;
        }
    }

    if ("pemeriksaan_ortopedi" == jenis) {
        var va1 = $('#pt1').val();
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va6 = $('#pt6').val();
        var va7 = $('#pt7').val();
        var va8 = $('#pt8').val();
        var va9 = $('#pt9').val();
        var va10 = $('#pt10').val();
        var va11 = $('#pt11').val();
        var va12 = $('#pt12').val();
        var va13 = $('#pt13').val();
        var va14 = $('#pt14').val();
        var va15 = $('#pt15').val();
        var va16 = $('#pt16').val();
        var va17 = $('#pt17').val();
        var va18 = $('#pt18').val();

        var cekCanvas = "Y";

        var canvasArea = document.getElementById('area_ortopedi');
        var canvasCek = document.getElementById('area_cek');

        if(canvasArea.toDataURL() == canvasCek.toDataURL()){
            cekCanvas = "N";
        }
        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 &&
            va9 && va10 && va11 && va12 && va13 && va14 && va15 &&
            va16 && va17 && va18 != '' && cekCanvas == "Y") {

            var canv = canvasArea.toDataURL("image/png"),
                canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Pemeriksaan Fisik',
                'jawaban': canv,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'gambar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kepala',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Leher',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Thorak',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Abdomen',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ekstremitas Atas',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ekstremitas Bawah',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Genetalia, os pubis',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Sacrum',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Columna vertebralis',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Muskuloskeletal',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status lokalis',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Penunjang',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban': va13,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban': va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan / Rencana Tindakan',
                'jawaban': va16,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anjuran Kontrol Kembali',
                'jawaban': va17,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Rawat Inap',
                'jawaban': va18,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("anamnesa_pemeriksaan_ginjal" == jenis) {
        var va1 = $('#pt1').val();
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va6 = $('#pt6').val();
        var va7 = $('#pt7').val();
        var va8 = $('#pt8').val();
        var va9 = $('#pt9').val();
        var ginjal = "Y";

        var canvasArea = document.getElementById('area_ginjal');
        var canvasCek = document.getElementById('area_cek');

        if(canvasArea.toDataURL() == canvasCek.toDataURL()){
            ginjal = "N";
        }
        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 != '' && ginjal == "Y") {

            data.push({
                'parameter': 'Anamnesa',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv = canvasArea.toDataURL("image/png"),
                canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Pemeriksaan Fisik / Status Lokalitas',
                'jawaban': canv,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'gambar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keterangan Status Lokalitas',
                'jawaban': va2,
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
                'parameter': 'Diagnosa Kerja',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan / Rencana Tindakan',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anjuran Kontrol Kembali',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Rawat Inap',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("anamnesa_pemeriksaan_jantung" == jenis) {
        var va1 = $('#pt1').val();
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va6 = $('#pt6').val();
        var va7 = $('#pt7').val();
        var va8 = $('#pt8').val();
        var va9 = $('#pt9').val();
        var jantung = "Y";

        var canvasArea = document.getElementById('area_jantung');
        var canvasCek = document.getElementById('area_cek');

        if(canvasArea.toDataURL() == canvasCek.toDataURL()){
            jantung = "N";
        }
        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 != '' && jantung == "Y") {

            data.push({
                'parameter': 'Anamnesa',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv = canvasArea.toDataURL("image/png"),
                canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Pemeriksaan Fisik / Status Lokalitas',
                'jawaban': canv,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'gambar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keterangan Status Lokalitas',
                'jawaban': va2,
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
                'parameter': 'Diagnosa Kerja',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan / Rencana Tindakan',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anjuran Kontrol Kembali',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Rawat Inap',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }


    if (cek) {
        var result = JSON.stringify(data);
        $('#save_sps_' + jenis).hide();
        $('#load_sps_' + jenis).show();
        dwr.engine.setAsync(true);
        AsesmenSpesialisAction.save(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_sps_' + jenis).show();
                    $('#load_sps_' + jenis).hide();
                    $('#modal-sps-' + jenis).modal('hide');
                    $('#warning_sps_' + ket).show().fadeOut(5000);
                    $('#msg_sps_' + ket).text("Berhasil menambahkan data...");
                    $('#modal-sps-' + jenis).scrollTop(0);
                } else {
                    $('#save_sps_' + jenis).show();
                    $('#load_sps_' + jenis).hide();
                    $('#warning_sps_' + jenis).show().fadeOut(5000);
                    $('#msg_sps_' + jenis).text(res.msg);
                    $('#modal-sps-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_sps_' + jenis).show().fadeOut(5000);
        $('#msg_sps_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-sps-' + jenis).scrollTop(0);
    }
}

function detailSPS(jenis) {
    if (jenis != '') {
        var head = "";
        var body = "";
        var totalSkor = 0;
        var first = "";
        var last = "";
        var tgl = "";
        var cekData = false;
        AsesmenSpesialisAction.getListDetail(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var jwb = "";
                    if (item.jawaban != null) {
                        jwb = item.jawaban;
                    }
                    if ("ttd" == item.tipe) {
                        body += '<tr>' +
                            '<td width="60%">' + item.parameter + '</td>' +
                            '<td>' + '<img src="' + jwb + '" style="width: 100px">' + '</td>' +
                            '</tr>';
                    }else if("tht_tht" == item.keterangan){
                        var tht = jwb.split("=");
                        var temp1 = "";
                        $.each(tht, function (i, item) {
                            var td = item.split("|");
                            var tr = "";
                            $.each(td, function (i, item) {
                                tr += '<td>'+item+'</td>'
                            });
                            if(td.length == 2){
                                tr = tr + '<td></td>';
                            }else if(td.length == 1){
                                tr = tr + '<td></td><td></td>';
                            }
                            temp1 += '<tr>'+tr+'</tr>';
                        });
                        var tabeleTcm = '<table class="table table-bordered table-striped" style="font-size: 12px">' +
                            '<thead>' +
                            '<td style="font-weight: bold">Telinga</td>' +
                            '<td style="font-weight: bold">Hidung</td>' +
                            '<td style="font-weight: bold">Tenggorokan</td>' +
                            '</thead>' +
                            '<tbody>' + temp1 + '</tbody>' +
                            '</table>';
                        body += '<tr>' +
                            '<td colspan="2">' + tabeleTcm + '</td>' +
                            '</tr>';
                    }else if("gambar" == item.tipe){
                        body += '<tr>' +
                            '<td width="40%">' + item.parameter + '</td>' +
                            '<td>' + '<img src="' + jwb + '" style="height: 300px">' + '</td>' +
                            '</tr>';
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

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_sps_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_sps_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_sps_' + jenis).attr('src', url);
            $('#btn_sps_' + jenis).attr('onclick', 'delRowSPS(\'' + jenis + '\')');
        });
    }
}

function delRowSPS(id) {
    $('#del_sps_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_sps_' + id).attr('src', url);
    $('#btn_sps_' + id).attr('onclick', 'detailSPS(\'' + id + '\')');
}

function showKetSPS(val, ket) {
    if (val == "Kelainan" || val == "Lainnya" || val == "Bentuk") {
        $('#sps-'+ket).show();
    } else {
        $('#sps-'+ket).hide();
    }
}

function loadImgToCanvas(url, id){
    var canvas = document.getElementById(id);
    var ctx = canvas.getContext('2d');
    var img = new Image();
    img.src = url;
    img.onload = function (ev) {
        canvas.width = img.width;
        canvas.height = img.height;
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.drawImage(img, 0, 0);
    }

    var canvasCek = document.getElementById('area_cek');
    var ctxCek = canvasCek.getContext('2d');
    var imgCek = new Image();
    imgCek.src = url;
    imgCek.onload = function (ev) {
        canvasCek.width = imgCek.width;
        canvasCek.height = imgCek.height;
        ctxCek.clearRect(0, 0, canvasCek.width, canvasCek.height);
        ctxCek.drawImage(imgCek, 0, 0);
    }
}