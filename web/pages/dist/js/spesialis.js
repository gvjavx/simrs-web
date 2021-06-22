function showModalSPS(jenis, idRM, isSetIdRM) {
    if (isSetIdRM == "Y") {
        tempidRm = idRM;
    }
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }

    if ("spesialis_bedah" == tipePelayanan || "spesialis_onkologi" == tipePelayanan) {
        var url = "";
        if ("Laki-Laki" == jenisKelamin) {
            url = contextPath + '/pages/images/penanda-laki-laki.jpg';
        } else {
            url = contextPath + '/pages/images/penanda-perempuan.jpg';
        }
        $('.canvas-cek').attr('id', 'area_canvas');
        $('.canvas-cek').attr('onmouseover', 'paintTtd(\'area_canvas\', true)');
        $('.canvas-btn').attr('onclick', 'removePaint(\'area_canvas\')');
        $('#form-gambar').show();
        loadImgToCanvas(url, 'area_canvas');
    }

    if ("spesialis_paru" == jenis) {
        var url = contextPath + '/pages/images/paru-1.png';
        $('.canvas-cek').attr('id', 'area_paru');
        $('.canvas-cek').attr('onmouseover', 'paintTtd(\'area_paru\', true)');
        $('.canvas-btn').attr('onclick', 'removePaint(\'area_paru\')');
        $('#form-gambar').show();
        loadImgToCanvas(url, 'area_paru');
    }

    if ("spesialis_ortopedi" == jenis) {
        var url = contextPath + '/pages/images/ortopedi.png';
        $('.canvas-cek').attr('id', 'area_ortopedi');
        $('.canvas-cek').attr('onmouseover', 'paintTtd(\'area_ortopedi\', true)');
        $('.canvas-btn').attr('onclick', 'removePaint(\'area_ortopedi\')');
        $('#form-gambar').show();
        loadImgToCanvas(url, 'area_ortopedi');
    }

    if ("anamnesa_pemeriksaan_ginjal" == jenis) {
        var url = contextPath + '/pages/images/ginjal-1.png';
        $('.canvas-cek').attr('id', 'area_paru');
        $('.canvas-cek').attr('onmouseover', 'paintTtd(\'area_ginjal\', true)');
        $('.canvas-btn').attr('onclick', 'removePaint(\'area_ginjal\')');
        $('#form-gambar').show();
        loadImgToCanvas(url, 'area_ginjal');
    }

    if ("spesialis_ortopedi" == jenis) {
        var url = contextPath + '/pages/images/jantung.png';
        $('.canvas-cek').attr('id', 'area_jantung');
        $('.canvas-cek').attr('onmouseover', 'paintTtd(\'area_jantung\', true)');
        $('.canvas-btn').attr('onclick', 'removePaint(\'area_jantung\')');
        $('#form-gambar').show();
        loadImgToCanvas(url, 'area_jantung');
    }
    if ("spesialis_gigi" == jenis) {
        var url = contextPath + '/pages/images/scala-gigi-new.png';
        loadImgToCanvas(url, 'area_gigi1');
    }

    if("spesialis_kulit_kelamin" == jenis){
        var url = "";
        if(jenisKelamin == "Perempuan"){
            url = contextPath + '/pages/images/kk_girl.png';
        }else{
            url = contextPath + '/pages/images/kk_man.png';
        }
        $('.canvas-cek').attr('id', 'area_kulit_kelamin');
        $('.canvas-cek').attr('onmouseover', 'paintTtd(\'area_kulit_kelamin\', true)');
        $('.canvas-btn').attr('onclick', 'removePaint(\'area_kulit_kelamin\')');
        $('#form-gambar').show();
        loadImgToCanvas(url, 'area_kulit_kelamin');
    }

    if("ophtal" == jenis){
        var url = contextPath + '/pages/images/mata.png';
        loadImgToCanvas(url, 'area_mata');
    }

    if("visus" == jenis){
        setSph("sph-val");
    }

    setKeteranganPeriksa('intruksi_'+jenis);
    if("spesialis_anak" != jenis ||
        "spesialis_mata" != jenis ||
        "rehab_medik" != jenis ||
        "spesialis_tht" != jenis ||
        "spesialis_obstetri" != jenis){
        $('#judul_asesmen').text("Asesmen "+namaPelayanan);
    }

    setDataPasien();
    $('#modal-sps-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveSPS(jenis, ket) {
    var data = [];
    var cek = false;
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }

    var sendTppri = [];
    var tindakLanjut = false;

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
                    'Daun Telinga : ' + va1 + '|' +
                    'Kavum nasi : ' + va12 + '|' +
                    'Orofaring : ' + va18 + '=' +

                    'Liang Telinga : ' + va2 + '|' +
                    'Konka Inferior : ' + va13 + '|' +
                    'Nasofaring : ' + va19 + '=' +

                    'Membran timpani : ' + va3 + '|' +
                    'Konka Medis : ' + va14 + '|' +
                    'Laring : ' + va20 + '=' +

                    'Reflek Cahaya : ' + va4 + '|' +
                    'Meatus Media : ' + va15 + '=' +

                    'Rinne : ' + va5 + '|' +
                    'Septum : ' + va16 + '=' +

                    'Waber : ' + va6 + '|' +
                    'Lainnya : ' + va17 + '=' +

                    'Scabach : ' + va7 + '=' +

                    'Audimetri : ' + va8 + '=' +

                    'Timpanometri : ' + va9 + '=' +

                    'Fungsi tuba eustachius : ' + va10 + '=' +

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
        var va9 = $('#intruksi_'+jenis+' option:selected').text();

        if (va1 && va3 && va4 && va5 && va6 && va7 != '') {
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
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
        }
    }

    if ("edukasi_tht" == jenis || "edukasi_pd" == jenis || "edukasi_bedah" == jenis ||
        "edukasi_paru" == jenis || "edukasi_anak" == jenis || "edukasi_neurologi" == jenis ||
        "edukasi_obstetri" == jenis || "edukasi_ortopedi" == jenis || "edukasi_ginjal" == jenis ||
        "edukasi_ginjal" == jenis || "edukasi_jantung" == jenis || "edukasi_umum" == jenis ||
        "edukasi_onkologi" == jenis || "edukasi_ginekologi" == jenis || "edukasi_kk" == jenis || "edukasi_spesialis" == jenis) {

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
        var nama1 = $('#nama_terang_pasien').val();
        var nama2 = $('#nama_terang_dokter').val();
        var sip2 = $('#sip_dokter').val();
        var sip2 = $('#sip_dokter').val();
        var isiEdukasi = $('#isi_edukasi').val();

        if (va1 == "Pasien") {
            temp = label + va1;
        } else if (va1 == "Keluarga") {
            if (va2 && va3 != '') {
                temp = label + "Keluarga pasien, nama : " + va2 + ", Hubungan dengan pasien : " + va3;
            }
        } else if (va1 == "Tidak") {
            if (va4 != '') {
                temp = label + "Tidak dapat memberi edukasi kepada pasien atau karena : " + va4;
            }
        }

        if (temp && isiEdukasi != '' && !cekTtd1 && !cekTtd2) {
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
                'nama_terang': nama1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Isi Edukasi',
                'jawaban': isiEdukasi,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Dokter',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama2,
                'sip': sip2,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("anamnesa_pd" == jenis || "anamnesa_umum" == jenis || "anamnesa_kk" == jenis) {
        var va1 = $('#kut1').val();
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
                'parameter': 'Anamnesa',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
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
                'parameter': 'Riwayat Pribadi',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
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

    if ("pemeriksaan_pd" == jenis || "pemeriksaan_anak" == jenis || "pemeriksaan_umum" == jenis || "pemeriksaan_kk" == jenis) {
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
        var va12 = $('#intruksi_'+jenis+' option:selected').text();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 != '') {
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

            var kulit = "Y";
            if("pemeriksaan_kk" == jenis){
                var ketGambar = $('#ket_gambar').val();
                var canvasArea = document.getElementById('area_kulit_kelamin');
                var canvasCek = document.getElementById('area_cek');
                if (canvasArea.toDataURL() == canvasCek.toDataURL()) {
                    kulit = "N";
                }
                if(kulit == "Y"){
                    data.push({
                        'parameter': 'Pemeriksaan Kulit Kelamin',
                        'jawaban': convertToDataURL(canvasArea),
                        'keterangan': jenis,
                        'jenis': ket,
                        'tipe': 'gambar',
                        'id_detail_checkup': idDetailCheckup
                    });
                }
                if(ketGambar != ''){
                    data.push({
                        'parameter': 'Keterangan Gambar',
                        'jawaban': ketGambar,
                        'keterangan': jenis,
                        'jenis': ket,
                        'id_detail_checkup': idDetailCheckup
                    });
                }
            }

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
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            if("pemeriksaan_kk" == jenis){
                if(kulit == "Y"){
                    cek = true;
                }
            }else{
                cek = true;
            }
            tindakLanjut = true;
        }
    }

    if ("anamnesa_bedah" == jenis || "anamnesa_onkologi" == jenis) {
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

    if ("pemeriksaan_bedah" == jenis || "pemeriksaan_onkologi" == jenis) {
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va6 = $('#pt6').val();
        var va8 = $('#intruksi_'+jenis+' option:selected').text();
        var operesi = "Y";
        var keteranganGambar = $('#keterangan_gambar').val();

        var canvasArea = document.getElementById('area_canvas');
        var canvasCek = document.getElementById('area_cek');
        if (canvasArea.toDataURL() == canvasCek.toDataURL()) {
            operesi = "N";
        }
        if (va2 && va3 && va4 && va5 && va6 != '' && operesi == "Y") {

            var canv = canvasArea.toDataURL("image/png"),
                canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Pemeriksaan Fisik / Status Lokalitas',
                'jawaban': canv,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'gambar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keterangan Gambar',
                'jawaban': keteranganGambar,
                'keterangan': jenis,
                'jenis': ket,
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
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
        }
    }

    if ("anamnesa_pemeriksaan_paru" == jenis) {
        var va1 = $('#pt1').val();
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va6 = $('#pt6').val();
        var va8 = $('#intruksi_'+jenis+' option:selected').text();
        var keteranganGambar = $('#keterangan_gambar').val();
        var paru = "Y";

        var canvasArea = document.getElementById('area_paru');
        var canvasCek = document.getElementById('area_cek');

        if (canvasArea.toDataURL() == canvasCek.toDataURL()) {
            paru = "N";
        }

        if (va1 && va2 && va3 && va4 && va5 && va6 != '' && paru == "Y") {

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
                'tipe': 'gambar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keterangan Gambar',
                'jawaban': keteranganGambar,
                'keterangan': jenis,
                'jenis': ket,
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
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
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
        if (v5 != undefined) {
            var ket = $('#ket_pt5').val();
            if ("Kelainan" == v5 || "Lainnya" == v5) {
                va5 = v5 + ' : ' + ket;
            } else {
                va5 = v5;
            }
        }
        var va6 = "";
        var v6 = $('[name=pt6]:checked').val();
        if (v6 != undefined) {
            var ket = $('#ket_pt6').val();
            if ("Bentuk" == v6 || "Lainnya" == v6) {
                va6 = v6 + ' : ' + ket;
            } else {
                va6 = v6;
            }
        }
        var va7 = $('#pt7').val();
        var va8 = $('#pt8').val();
        var va9 = $('#pt9').val();
        var va10 = $('#pt10').val();
        var va11 = $('#pt11').val();
        var va13 = $('#intruksi_'+jenis+' option:selected').text();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va11 != '') {

            data.push({
                'parameter': 'Pemeriksaan Fisik',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Otonom',
                'jawaban': 'Keringat : ' + va2 + ', BAB : ' + va3 + ', BAK : ' + va4,
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
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': va13,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
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
                'parameter': 'G ' + va4 + ' P ' + va5 + ', Haid Terakhir',
                'jawaban': va6 + ', Taksiran persalinan ' + va7,
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
        var va11 = $('#intruksi_'+jenis+' option:selected').text();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 != '') {

            data.push({
                'parameter': 'TFU',
                'jawaban': va1 + ' Djj, ' + 'Letak : ' + va2 + ', His : ' + va3,
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
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
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
        var va18 = $('#intruksi_'+jenis+' option:selected').text();
        var keteranganGambar = $('#keterangan_gambar').val();

        var cekCanvas = "Y";

        var canvasArea = document.getElementById('area_ortopedi');
        var canvasCek = document.getElementById('area_cek');

        if (canvasArea.toDataURL() == canvasCek.toDataURL()) {
            cekCanvas = "N";
        }
        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 &&
            va9 && va10 && va11 && va12 && va13 && va14 && va15 &&
            va16 != '' && cekCanvas == "Y") {

            var canv = canvasArea.toDataURL("image/png"),
                canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Pemeriksaan Fisik',
                'jawaban': canv,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'gambar',
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
                'parameter': 'Keterangan Gambar',
                'jawaban': keteranganGambar,
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
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': va18,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
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
        var va9 = $('#intruksi_'+jenis+' option:selected').text();
        var ginjal = "Y";

        var canvasArea = document.getElementById('area_ginjal');
        var canvasCek = document.getElementById('area_cek');

        if (canvasArea.toDataURL() == canvasCek.toDataURL()) {
            ginjal = "N";
        }
        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 != '' && ginjal == "Y") {

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
                'tipe': 'gambar',
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
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
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
        var va9 = $('#intruksi_'+jenis+' option:selected').text();
        var jantung = "Y";

        var canvasArea = document.getElementById('area_jantung');
        var canvasCek = document.getElementById('area_cek');
        var keteranganGambar = $('#keterangan_gambar').val();

        if (canvasArea.toDataURL() == canvasCek.toDataURL()) {
            jantung = "N";
        }
        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 != '' && jantung == "Y") {

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
                'tipe': 'gambar',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keterangan Gambar',
                'jawaban': keteranganGambar,
                'keterangan': jenis,
                'jenis': ket,
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
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
        }
    }

    if ("anamnesa_ginekologi" == jenis) {
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

        if (va1 && va2 && va3 && va4 && va5 && va6 &&
            va7 && va8 && va9 && va10 && va11 && va12 != '') {
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
                'parameter': 'Riwayat Kehamilan',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Menstruasi',
                'jawaban': 'Menarche : ' + va4 + ', Siklus haid : ' + va5 + ', Lama haid : ' + va6 + ', Nyeri haid : ' + va7 + ', Haid terakhir : ' + va8,
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

    if ("pemeriksaan_ginekologi" == jenis) {
        var va1 = $('#pt1').val();
        var va2 = $('#pt2').val();
        var va3 = $('#pt3').val();
        var va4 = $('#pt4').val();
        var va5 = $('#pt5').val();
        var va7 = $('#intruksi_'+jenis+' option:selected').text();

        if (va1 && va2 && va3 && va4 && va5 != '') {
            data.push({
                'parameter': 'Pemeriksaan Fisik/Lokalis',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi / Pengobatan',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan / Rencana Tindakan',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
        }
    }

    if ("anamnesa_rehab_medik" == jenis || "anamnesa_spesialis_urologi" == jenis) {
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
        var va13 = $('#kut13').val();
        var va14 = $('#kut14').val();
        var va15 = $('#kut15').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 &&
            va7 && va8 && va9 && va10 && va11 && va12 && va13 && va14 && va15 != '') {
            data.push({
                'parameter': 'Tanggal Kunjungan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Alergi',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban': va3 + ' mmHg , Nadi : ' + va4 + ' x/menit , Suhu : ' + va5 + ' ˚C , RR : ' + va6 + ' x/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan',
                'jawaban': va7 + ' Kg, Tinggi Badan : ' + va8 + ' cm, Skala Nyeri : ' + va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kondisi Nutrisi',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnesis',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Sekarang',
                'jawaban': va13,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Dahulu',
                'jawaban': va14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Obat obat yang sedang dikonsumsi',
                'jawaban': va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pemeriksaan_rehab_medik" == jenis || "pemeriksaan_spesialis_urologi" == jenis) {
        var va1 = $('[name=pt1]:checked').val();
        var va2 = $('#pt2').val();
        var va3 = $('[name=pt3]:checked').val();
        var va4 = $('[name=pt4]:checked').val();
        var va5 = $('[name=pt5]:checked').val();
        var va6 = $('[name=pt6]:checked').val();
        var va7 = $('#pt7').val();//lainnya
        var va8 = $('#pt8').val();
        var va9 = "";
        var v9 = $('[name=pt9]:checked').val();
        if (v9 != undefined) {
            var ket = $('#ket_pt9').val();
            if ("Kelainan" == v9) {
                va9 = v9 + " : " + ket;
            } else {
                va9 = v9;
            }
        }

        var va10 = "";
        var v10 = $('#pt10').val();
        if (v10 != '') {
            var ket = $('#sps-pt10').val();
            if ("Retraksi" == v10) {
                va10 = v10 + " : " + ket;
            } else {
                va10 = v10;
            }
        }

        var va11 = $('#pt011').val();

        var va12 = "";
        var v12 = $('#pt012').val();
        if (v12 != '') {
            var ket = $('#sps-pt012').val();
            if ("Lainnya" == v12) {
                va12 = v12 + " : " + ket;
            } else {
                va12 = v12;
            }
        }

        var va13 = $('#pt013').val();
        var v13 = $('#pt013').val();
        if (v13 != '') {
            var ket = $('#sps-pt013').val();
            if ("Lainnya" == v13) {
                va13 = v13 + " : " + ket;
            } else {
                va13 = v13;
            }
        }

        var va14 = $('#pt014').val();
        var va15 = $('#pt015').val();

        var va16 = $('#pt016').val();

        var va17 = $('#pt017').val();
        var va18 = $('#pt018').val();

        var va19 = "";
        var v19 = $('#pt019').val();
        if (v19 != '') {
            var ket = $('#sps-pt019').val();
            if ("Teraba" == v19) {
                va19 = v19 + " : " + ket;
            } else {
                va19 = v19;
            }
        }

        var va20 = "";
        var v20 = $('#pt020').val();
        if (v20 != '') {
            var ket = $('#sps-pt020').val();
            if ("Teraba" == v20) {
                va20 = v20 + " : " + ket;
            } else {
                va20 = v20;
            }
        }

        var va21 = $('#pt021').val();
        var va22 = $('#pt022').val();

        var va23 = $('[name=pt023]:checked').val();
        var va24 = $('[name=pt024]:checked').val();

        var va25 = $('#pt025').val();
        var va26 = $('#pt026').val();
        var va27 = $('#pt027').val();
        var va28 = $('#pt028').val();

        var va29 = $('#pt029').val();
        var va30 = $('#pt030').val();
        var va31 = $('#pt031').val();
        var va32 = $('#pt032').val();

        var va33 = $('#pt033').val();
        var va34 = $('#pt034').val();
        var va35 = $('#pt035').val();
        var va36 = $('#pt036').val();

        if (va1 && va3 && va4 && va5 && va6 != undefined && va2 && va8 && va9 && va10 &&
            va11 && va12 && va13 && va14 && va15 && va16 && va17 && va18 && va19 && va20 &&
            va21 && va23 && va24 && va25 && va26 && va27 && va28 != '') {

            data.push({
                'parameter': 'Kondisi Umum',
                'jawaban': va1 + ', GCS ' + va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            var lain = "";
            if (va7 != '') {
                lain = ', Lainnya : ' + va7;
            }
            data.push({
                'parameter': 'Kepala',
                'jawaban': 'Kunjungtivita : ' + va3 + ', Sklera : ' + va4 + ', Sianosis : ' + va5 + ', JVS : ' + va6 + lain,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'KGB',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Thorak',
                'jawaban':
                    'Jantung : ' + va9 + '. ' +
                    'Paru : Inspetik : ' + va10 + ', Palpasi : ' + va11 + ', ' +
                    'Perkusi : ' + va12 + ', Auskultasi : ' + va13 + ', ' +
                    'Ronchi : ' + va14 + ', Wheezing : ' + va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            var lainnya2 = "";
            if (va22 != "") {
                lainnya2 = ", Lainnya : " + va22;
            }
            data.push({
                'parameter': 'Abdomen',
                'jawaban':
                    va16 + ', Nyeri telan : ' + va17 + ', Bising usus : ' + va18 + ', ' +
                    'Heper : ' + va19 + ', Lien : ' + va20 + ', Massa : ' + va21 + lainnya2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ekstremitas Atas',
                'jawaban': 'Edema : ' + va23 + ', Akral : ' + va24,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'FVC',
                'jawaban': 'Fred : ' + va25 + ', Actl : ' + va26 + ', % Fred : ' + va27 + ', Keterangan : ' + va28,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'FEV 1/FVC',
                'jawaban': 'Fred : ' + va29 + ', Actl : ' + va30 + ', % Fred : ' + va31 + ', Keterangan : ' + va32,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'FVC',
                'jawaban': 'Fred : ' + va33 + ', Actl : ' + va34 + ', % Fred : ' + va35 + ', Keterangan : ' + va36,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("asesmen_gigi" == jenis) {

        var va1 = $('[name=ag1]:checked').val();
        var va2 = $('[name=ag2]:checked').val();
        var va3 = $('[name=ag3]:checked').val();
        var va4 = $('[name=ag4]:checked').val();
        var va5 = $('[name=ag5]:checked').val();
        var va6 = $('[name=ag6]:checked').val();
        var lain = $('#ag_lain_lain').val();
        var D = $('#ag_d').val();
        var M = $('#ag_m').val();
        var F = $('#ag_f').val();
        var p1 = $('#ag_p1').val();
        var p2 = $('#ag_p2').val();

        var gigiImg = document.getElementById("area_gigi1");
        var canvasCek = document.getElementById('area_cek');

        var ttd = document.getElementById("ttd_ag9");
        var cekTtd = isBlank(ttd);
        var nama = $('#ttd_nama_terang_petugas').val();
        var nip = $('#ttd_sip_petugas').val();
        var keteranganGambar = $('#keterangan_gambar').val();

        var gigi = "Y";
        if (gigiImg.toDataURL() == canvasCek.toDataURL()) {
            gigi = "N";
        }

        if (va1 && va2 && va3 != undefined && !cekTtd) {

            var label = $('.label_gigi');
            $.each(label, function (i, item) {
                var index = i+1;
                var labelKiri = $('#label_kiri_'+index).text();
                var inputKiri = $('#input_kiri_'+index).val();
                var inputKanan = $('#input_kanan_'+index).val();
                var labelKanan = $('#label_kanan_'+index).text();
                if(index <= 8){
                    data.push({
                        'parameter': 'Label Gigi',
                        'jawaban': labelKiri+'|'+inputKiri+'|'+inputKanan+'|'+labelKanan,
                        'tipe': 'tabel',
                        'keterangan': jenis,
                        'jenis': ket,
                        'id_detail_checkup': idDetailCheckup
                    });
                }
            });

            var img = gigiImg.toDataURL("image/png"),
                img = img.replace(/^data:image\/(png|jpg);base64,/, "");

            if (gigi == "Y") {
                data.push({
                    'parameter': 'Pemeriksaan Gigi',
                    'jawaban': img,
                    'tipe': 'gambar',
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Keterangan Gambar',
                    'jawaban': keteranganGambar,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }

            $.each(label, function (i, item) {
                var index = i+1;
                var labelKiri = $('#label_kiri_'+index).text();
                var inputKiri = $('#input_kiri_'+index).val();
                var inputKanan = $('#input_kanan_'+index).val();
                var labelKanan = $('#label_kanan_'+index).text();
                if(index > 8){
                    data.push({
                        'parameter': 'Label Gigi',
                        'jawaban': labelKiri+'|'+inputKiri+'|'+inputKanan+'|'+labelKanan,
                        'tipe': 'tabel',
                        'keterangan': jenis,
                        'jenis': ket,
                        'id_detail_checkup': idDetailCheckup
                    });
                }
            });

            data.push({
                'parameter': 'Occlusi',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Torus Palatinus',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Torus Mandibularis',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Palatum',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diastema',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Gigi Anomali',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lain-Lain',
                'jawaban': lain,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '',
                'jawaban': 'D :'+D+', M : '+M+', F : '+F,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jumlah photo yang diambil',
                'jawaban': p1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jumlah rontgen photo yang diambil',
                'jawaban': p2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': 'TTD Pemeriksa',
                'jawaban': ttd1,
                'tipe': 'ttd',
                'keterangan': jenis,
                'jenis': ket,
                'nama_terang': nama,
                'sip': nip,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("asesmen_gigi_lanjut" == jenis) {
        var va4 = $('#ag4').val();
        var va5 = $('#ag5').val();
        var va6 = $('#ag6').val();
        var va7 = $('#ag7').val();
        var va8 = $('#intruksi_'+jenis+' option:selected').text();

        var ttd = document.getElementById("ag9");
        var gigiImg = document.getElementById("area_gigi1");
        var canvasCek = document.getElementById('area_cek');

        var gigi = "Y";
        if (gigiImg.toDataURL() == canvasCek.toDataURL()) {
            gigi = "N";
        }
        var cekTtd = isBlank(ttd);
        var nama = $('#nama_terang_petugas').val();
        var nip = $('#sip_petugas').val();

        if (va4 && va5 && va6 && va7 && va8 && nama && nip != '' && !cekTtd) {

            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");

            var img = gigiImg.toDataURL("image/png"),
                img = img.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': 'Diastema',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lain-Lain',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rencana Tindak Lanjut',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Pemeriksa',
                'jawaban': ttd1,
                'tipe': 'ttd',
                'keterangan': jenis,
                'jenis': ket,
                'nama_terang': nama,
                'sip': nip,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
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
        var gigiImg = document.getElementById("area_gigi2");
        var canvasCek = document.getElementById('area_cek');
        var namaTerang = $('#nama_terang_gg013').val();
        var sip = $('#sip_gg013').val();

        var gigi = "Y";
        if (gigiImg.toDataURL() == canvasCek.toDataURL()) {
            gigi = "N";
        }
        var cekTtd = isBlank(ttd);

        if (va1 && va2 && temp1 && temp2 && va10 && va11 && va12 != '' &&
            va3 && va4 && va5 && va6 && va7 != undefined && !cekTtd) {

            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");

            var img = gigiImg.toDataURL("image/png"),
                img = img.replace(/^data:image\/(png|jpg);base64,/, "");

            if (gigi == "Y") {
                data.push({
                    'parameter': 'Pemeriksaan Gigi',
                    'jawaban': img,
                    'tipe': 'gambar',
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            data.push({
                'parameter': 'Golongan Darah',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penyakit Jantung',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diabetes',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Haemopilia',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hepatitis',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Gastritis',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penyakit Lainnya',
                'jawaban': temp1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alergi Obat',
                'jawaban': temp2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alergi Makanan',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Obat yang sedang dikonsumsi',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal pencatatan data',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanda Tangan',
                'jawaban': ttd1,
                'tipe': 'ttd',
                'keterangan': jenis,
                'jenis': ket,
                'nama_terang': namaTerang,
                'sip': sip,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pengkajian_mata" == jenis) {
        var va1 = $('#p1').val();
        var va2 = $('#p2').val();
        var va3 = $('#p3').val();
        var va4 = $('#p4').val();

        if (va1 && va2 && va3 && va4 != '') {
            data.push({
                'parameter': 'Tanggal Jam',
                'jawaban': va1+" "+va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnese',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("visus" == jenis) {
        var va1 = $('#v1').val();
        var va2 = $('#v2').val();
        var va3 = $('#v3').val();
        var va4 = $('#v4').val();
        var va5 = $('#v5').val();
        var va6 = $('#v6').val();
        var va7 = $('#v7').val();
        var va8 = $('#v8').val();
        var va9 = $('#v9').val();
        var va10 = $('#v10').val();
        var va11 = $('#v11').val();
        var va12 = $('#v12').val();
        var va13 = $('#v13').val();
        var va14 = $('#v14').val();
        var va15 = $('#v15').val();
        var va16 = $('#v16').val();
        var va17 = $('#v17').val();
        var va18 = $('#v18').val();
        var va19 = $('#v19').val();
        var va20 = $('#v20').val();
        var va21 = $('#v21').val();
        var va22 = $('#v22').val();
        var va23 = $('#v23').val();
        var va24 = $('#v24').val();
        var va25 = $('#v25').val();
        var va26 = $('#v26').val();
        var va27 = $('#v27').val();
        var va28 = $('#v28').val();

        va1 == "" ? va1 = "-" : va1 = va1;
        va2 == "" ? va2 = "-" : va2 = va2;
        va3 == "" ? va3 = "-" : va3 = va3;
        va4 == "" ? va4 = "-" : va4 = va4;
        va5 == "" ? va5 = "-" : va5 = va5;
        va6 == "" ? va6 = "-" : va6 = va6;
        va7 == "" ? va7 = "-" : va7 = va7;
        va8 == "" ? va8 = "-" : va8 = va8;
        va9 == "" ? va9 = "-" : va9 = va9;
        va10 == "" ? va10 = "-" : va10 = va10;
        va11 == "" ? va11 = "-" : va11 = va11;
        va12 == "" ? va12 = "-" : va12 = va12;
        va13 == "" ? va13 = "-" : va13 = va13;
        va14 == "" ? va14 = "-" : va14 = va14;
        va15 == "" ? va15 = "-" : va15 = va15;
        va16 == "" ? va16 = "-" : va16 = va16;
        va17 == "" ? va17 = "-" : va17 = va17;
        va18 == "" ? va18 = "-" : va18 = va18;
        va19 == "" ? va19 = "-" : va19 = va19;
        va20 == "" ? va20 = "-" : va20 = va20;
        va21 == "" ? va21 = "-" : va21 = va21;
        va22 == "" ? va22 = "-" : va22 = va22;
        va23 == "" ? va23 = "-" : va23 = va23;
        va24 == "" ? va24 = "-" : va24 = va24;
        va25 == "" ? va25 = "-" : va25 = va25;
        va26 == "" ? va26 = "-" : va26 = va26;
        va27 == "" ? va27 = "-" : va27 = va27;
        va28 == "" ? va28 = "-" : va28 = va28;

        data.push({
            'parameter': 'Jenis Optalmologist',
            'jawaban': 'OD|OS',
            'keterangan': jenis,
            'jenis': ket,
            'tipe':'colspan',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Visual Aquality',
            'jawaban': va1 +', Sph: '+va2+', Cyl: '+va3+', X: '+va4+', Visual Akhir: '+va5+'|'+va15 +', Sph: '+va16+', Cyl: '+va17+', X: '+va18+', Visual Akhir: '+va19,
            'keterangan': jenis,
            'jenis': ket,
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'KML',
            'jawaban': 'Sph: '+va6+', Cyl: '+va7+', X: '+va8+', Visual Akhir: '+va9+'|'+'Sph: '+va20+', Cyl: '+va21+', X: '+va22+', Visual Akhir: '+va23,
            'keterangan': jenis,
            'jenis': ket,
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Addisi',
            'jawaban': va10+'|'+va24,
            'keterangan': jenis,
            'jenis': ket,
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Keratometri',
            'jawaban': 'K1: '+va11+', K2: '+va12+'|'+'K1: '+va25+', K2: '+va26,
            'keterangan': jenis,
            'jenis': ket,
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Tonometri',
            'jawaban': 'Non-Contact: '+va13+' mmHg, Schiotz: '+va14+' mmHg|'+'Non-Contact: '+va13+' mmHg, Schiotz: '+va14+' mmHg',
            'keterangan': jenis,
            'jenis': ket,
            'id_detail_checkup': idDetailCheckup
        });
        cek = true;
    }

    if ("ophtal" == jenis) {
        var va1 = $('.o-label');
        var va2 = $('.od-isi');
        var va3 = $('.os-isi');
        var kete = $('#o1').val();
        var nama = $('#nama_terang_ttd1').val();
        var sip = $('#sip_ttd1').val();
        var c3 = "";

        var cekCanvas = "Y";
        var canvasArea = document.getElementById('area_mata');
        var canvasCek = document.getElementById('area_cek');
        var tts = document.getElementById('ttd1');
        var cektts = isCanvasBlank(tts);

        if (canvasArea.toDataURL() == canvasCek.toDataURL()) {
            cekCanvas = "N";
        }
        if(cekCanvas == "Y"){
            var canv = canvasArea.toDataURL("image/png"),
                canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': 'Pemeriksaan Optalmologist',
                'jawaban': canv,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'gambar',
                'id_detail_checkup': idDetailCheckup
            });

            var keteranganGambar = $('#o1').val();
            if(keteranganGambar != ''){
                data.push({
                    'parameter': 'Keterangan Gambar',
                    'jawaban': keteranganGambar,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
        }

        data.push({
            'parameter': '',
            'jawaban': 'OD|OS',
            'keterangan': jenis,
            'jenis': ket,
            'id_detail_checkup': idDetailCheckup
        });

        $.each(va2, function (i, item) {
            var jawaban = '-|-';
            if(item.value != '' && va3[i].value != ''){
                jawaban = item.value+'|'+va3[i].value;
                c3 = "Y";
            }else if(item.value != ''){
                jawaban = item.value+'|-';
            }else if(va3[i].value != ''){
                jawaban = '-|'+va3[i].value;
            }
            data.push({
                'parameter': va1[i].innerHTML,
                'jawaban': jawaban,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
        });

        if (!cektts) {
            var tad = convertToDataURL(tts);
            data.push({
                'parameter': 'TTD Spesialis Mata',
                'jawaban': tad,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'ttd',
                'sip':sip,
                'nama_terang': nama,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pengkajian_mata_op" == jenis) {
        var va1 = $('#pk1').val();
        var va2 = $('#pk2').val();
        var va3 = $('#pk3').val();
        var va4 = $('#pk4').val();

        if (va1 && va2 && va3 && va4 != '') {
            data.push({
                'parameter': 'Tanggal Jam',
                'jawaban': va1+" "+va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnese',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("biometri" == jenis) {
        var va1 = $('.bi-label');
        var va2 = $('.bi-od');
        var va3 = $('.bi-os');

        var va4 = $('.bi-label-2');
        var va5 = $('.bi-od-im');
        var va6 = $('.bi-od-co');
        var va7 = $('.bi-os-im');
        var va8 = $('.bi-os-co');

        var c1 = "";
        var c2 = "";

        $.each(va2, function (i, item) {
            if(item.value != '' && va3[i].value != ''){
                c1 = "Y";
            }
        });

        $.each(va5, function (i, item) {
            if(item.value != '' && va6[i].value != '' && va7[i].value != '' && va8[i].value != ''){
                c2 = "Y";
            }
        });

        if(c1 == "Y"){
            data.push({
                'parameter': 'Pemeriksaan',
                'jawaban': 'OD|OS',
                'keterangan': jenis,
                'jenis': ket,
                'tipe': '2',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keratometri',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            $.each(va2, function (i, item) {
                if(item.value != '' && va3[i].value != ''){
                    data.push({
                        'parameter': va1[i].innerHTML,
                        'jawaban': item.value+'|'+va3[i].value,
                        'keterangan': jenis,
                        'jenis': ket,
                        'tipe': '2',
                        'id_detail_checkup': idDetailCheckup
                    });
                }
            });
        }
        if(c2 == "Y"){
            data.push({
                'parameter': 'Kalkukasi "IOT POWER"',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '',
                'jawaban': 'Immersion|Contact|Immersion|Contact|',
                'keterangan': jenis,
                'jenis': ket,
                'tipe': '4',
                'id_detail_checkup': idDetailCheckup
            });
            $.each(va5, function (i, item) {
                if(item.value != '' && va6[i].value != '' && va7[i].value != '' && va8[i].value != ''){
                    data.push({
                        'parameter': va4[i].innerHTML,
                        'jawaban': item.value+'|'+va6[i].value+'|'+va7[i].value+'|'+va8[i].value,
                        'keterangan': jenis,
                        'jenis': ket,
                        'tipe': '4',
                        'id_detail_checkup': idDetailCheckup
                    });
                }
            });
        }

        if (c1 && c2 == "Y") {
            cek = true;
        }
    }

    if("anamnesis_pemeriksaan" == jenis){
        var va1 = $('#ps1').val();
        var va2 = $('#ps2').val();
        var va3 = $('#ps3').val();
        var va4 = $('#ps4').val();
        var va5 = $('#ps5').val();
        var va6 = $('#ps6').val();
        var va7 = $('#ps7').val();
        var va8 = $('#ps8').val();
        var va9 = $('#ps9').val();
        var va10 = $('#ps10').val();
        var va11 = $('#ps11').val();
        var va12 = $('#ps12').val();
        var va13 = $('#ps13').val();
        var va14 = $('#ps14').val();
        var intruksi = $('#intruksi_'+jenis+' option:selected').text();

        var cekCanvas = "Y";
        var canvasId = ('.canvas-cek')[0].id;
        if(canvasId != undefined){
            var canvasArea = document.getElementById(canvasId);
            var canvasCek = document.getElementById('area_cek');
            if (canvasArea.toDataURL() == canvasCek.toDataURL()) {
                cekCanvas = "N";
            }
        }else {
            cekCanvas = "N";
        }

        var ttdDpjp = document.getElementById('dpjp');
        var nama = $('#nama_terang_dpjp').val();
        var sip = $('#sip_dpjp').val();
        var cekDpjp = isCanvasBlank(ttdDpjp);

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 &&
            va10 && va11 && va12 && va13 && va14 && sip && nama != '' && !cekDpjp) {
            data.push({
                'parameter': 'Tanggal Kunjungan',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnesis',
                'jawaban': "",
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
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
                'tipe': 'penyakit_dahulu',
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
                'parameter': 'Pemeriksaan',
                'jawaban': "",
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kondisi Umum',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kepala Leher',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Thorax',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Abdomen',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ekstremitas',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            if(cekCanvas == "Y"){
                var canv = canvasArea.toDataURL("image/png"),
                    canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");
                data.push({
                    'parameter': 'Gambar Pemeriksaan',
                    'jawaban': canv,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'gambar',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            data.push({
                'parameter': 'Pemeriksaan Penunjang',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban': va13,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan / Rencana Tindakan',
                'jawaban': va14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Intruksi Tindak Lanjut',
                'jawaban': intruksi,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            var tad = convertToDataURL(ttdDpjp);
            data.push({
                'parameter': 'TTD Spesialis Mata',
                'jawaban': tad,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'ttd',
                'sip':sip,
                'nama_terang': nama,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            tindakLanjut = true;
        }
    }

    if (cek) {
        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            $('#save_sps_' + jenis).hide();
            $('#load_sps_' + jenis).show();
            delRowSPS(jenis);
            dwr.engine.setAsync(true);
            AsesmenSpesialisAction.save(result, pasienData, {
                callback: function (res) {
                    if (res.status == "success") {
                        if(tindakLanjut){
                            var va9 = "";
                            var v9 = $('#intruksi_'+jenis).val();
                            var v9Text = $('#intruksi_'+jenis+' option:selected').text();

                            if(v9 != '' && v9Text != ' - ') {
                                var ket1 = $('#ket_'+jenis).val();
                                var ket3 = $('#int_ket_rs_lain').val();
                                var ketSelesai = $('#int_ket_selesai option:selected').text();
                                var dataKontrol = "";

                                if ("rawat_inap" == v9 || "rawat_isolasi" == v9 || "rawat_intensif" == v9) {
                                    va9 = v9Text + ', ' + ket1;
                                } else if ("selesai" == v9) {
                                    va9 = ketSelesai;
                                } else if ("rujuk_rs_lain" == v9){
                                    va9 = v9 + ','+ ket3;
                                } else if("kontrol_ulang" == v9){
                                    va9 = v9Text
                                    var kontrol = [];
                                    var tgl = $('.int_tanggal_kontrol');
                                    var pelayanan = $('.int_pelayanan_kontrol');
                                    var dokter = $('.int_dokter_kontrol');
                                    $.each(tgl, function (i, item) {
                                        if(item.value != '' && pelayanan[i].value != '' && dokter[i].value != ''){
                                            kontrol.push({
                                                'tgl_kontrol': item.value.split("-").reverse().join("-"),
                                                'pelayanan': pelayanan[i].value,
                                                'dokter': dokter[i].value,
                                            });
                                        }
                                    });
                                    if(kontrol.length > 0){
                                        dataKontrol = JSON.stringify(kontrol);
                                    }
                                } else {
                                    va9 = v9Text;
                                }

                                sendTppri = {
                                    'id_detail_checkup': idDetailCheckup,
                                    'tindak_lanjut': v9,
                                    'indikasi': ket1,
                                    'keterangan': va9,
                                    'rujuk_rs': ket3,
                                    'data_kontrol': dataKontrol
                                }

                                if(sendTppri != null){
                                    dwr.engine.setAsync(true);
                                    CheckupDetailAction.sendToTppti(JSON.stringify(sendTppri), {
                                        callback:function (res) {
                                            if(res.status == "success"){
                                                setTindakLanjut();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                        $('#save_sps_' + jenis).show();
                        $('#load_sps_' + jenis).hide();
                        $('#modal-sps-' + jenis).modal('hide');
                        $('#warning_sps_' + ket).show().fadeOut(5000);
                        $('#msg_sps_' + ket).text("Berhasil menambahkan data...");
                        $('#modal-sps-' + jenis).scrollTop(0);
                        getListRekamMedis('rawat_jalan', tipePelayanan, idDetailCheckup);
                        detailSPS(jenis);
                    } else {
                        $('#save_sps_' + jenis).show();
                        $('#load_sps_' + jenis).hide();
                        $('#warning_sps_' + jenis).show().fadeOut(5000);
                        $('#msg_sps_' + jenis).text(res.msg);
                        $('#modal-sps-' + jenis).scrollTop(0);
                    }
                }
            });
        }
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
                    if("asesmen_gigi" == item.keterangan){
                        if("tabel" == item.tipe){
                            body += '<tr>' +
                                '<td align="center" width="25%">' + item.jawaban.split("|")[0] + '</td>' +
                                '<td align="center" width="25%">' + item.jawaban.split("|")[1] + '</td>' +
                                '<td align="center" width="25%">' + item.jawaban.split("|")[2] + '</td>' +
                                '<td align="center" width="25%">' + item.jawaban.split("|")[3] + '</td>' +
                                '</tr>';
                        }else if ("ttd" == item.tipe){
                            body += '<tr>' +
                                '<td colspan="2">' + item.parameter + '</td>' +
                                '<td colspan="2">' + '<img src="' + jwb + '" style="width: 100px">' +
                                '<p style="margin-top: -3px">' + cekItemIsNull(item.namaTerang) + '</p>' +
                                '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                '</td>' +
                                '</tr>';
                        }else if ("gambar" == item.tipe) {
                            body += '<tr align="center">' +
                                '<td colspan="4">' + '<img src="' + jwb + '" style="width: 60%">' + '</td>' +
                                '</tr>';
                        }else{
                            body += '<tr>' +
                                '<td colspan="2">' + item.parameter + '</td>' +
                                '<td colspan="2">' + jwb + '</td>' +
                                '</tr>';
                        }
                    } else if ("ttd" == item.tipe) {
                        if("ophtal" == item.keterangan){
                            body += '<tr>' +
                                '<td width="60%">' + item.parameter + '</td>' +
                                '<td colspan="2">' + '<img src="' + jwb + '" style="width: 100px">' +
                                '<p style="margin-top: -3px">' + cekItemIsNull(item.namaTerang) + '</p>' +
                                '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                '</td>' +
                                '</tr>';
                        }else{
                            body += '<tr>' +
                                '<td width="60%">' + item.parameter + '</td>' +
                                '<td>' + '<img src="' + jwb + '" style="width: 100px">' +
                                '<p style="margin-top: -3px">' + cekItemIsNull(item.namaTerang) + '</p>' +
                                '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                '</td>' +
                                '</tr>';
                        }
                    } else if ("tht_tht" == item.keterangan) {
                        var tht = jwb.split("=");
                        var temp1 = "";
                        $.each(tht, function (i, item) {
                            var td = item.split("|");
                            var tr = "";
                            $.each(td, function (i, item) {
                                tr += '<td>' + item + '</td>'
                            });
                            if (td.length == 2) {
                                tr = tr + '<td></td>';
                            } else if (td.length == 1) {
                                tr = tr + '<td></td><td></td>';
                            }
                            temp1 += '<tr>' + tr + '</tr>';
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
                    } else if ("gambar" == item.tipe) {
                        if("ophtal" == item.keterangan){
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td colspan="2">' + '<img src="' + jwb + '" style="height: 200px">' + '</td>' +
                                '</tr>';
                        }else if("pemeriksaan_kk" == item.keterangan){
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + '<img src="' + jwb + '" style="height: 200px">' + '</td>' +
                                '</tr>';
                        }else{
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + '<img src="' + jwb + '" style="width: 100%">' + '</td>' +
                                '</tr>';
                        }
                    } else if("visus" == item.keterangan){
                        body += '<tr>' +
                            '<td width="20%">' + item.parameter + '</td>' +
                            '<td>' + item.jawaban.split("|")[0] + '</td>' +
                            '<td>' + item.jawaban.split("|")[1] + '</td>' +
                            '</tr>';
                    }else if("ophtal" == item.keterangan){
                        if("colspan" == item.tipe){
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td colspan="2">' + jwb + '</td>' +
                                '</tr>';
                        }else{
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + item.jawaban.split("|")[0] + '</td>' +
                                '<td>' + item.jawaban.split("|")[1] + '</td>' +
                                '</tr>';
                        }
                    }else if("biometri" == item.keterangan){
                        if("colspan" == item.tipe) {
                            body += '<tr>' +
                                '<td colspan="5">' + item.parameter + '</td>' +
                                '</tr>';
                        }else if("2" == item.tipe){
                            if(item.jawaban != null){
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td colspan="2">' + item.jawaban.split("|")[0] + '</td>' +
                                    '<td colspan="2">' + item.jawaban.split("|")[1] + '</td>' +
                                    '</tr>';
                            }
                        }else if("4" == item.tipe){
                            if(item.jawaban != null){
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + item.jawaban.split("|")[0] + '</td>' +
                                    '<td>' + item.jawaban.split("|")[1] + '</td>' +
                                    '<td>' + item.jawaban.split("|")[2] + '</td>' +
                                    '<td>' + item.jawaban.split("|")[3] + '</td>' +
                                    '</tr>';
                            }
                        }
                    }else if("colspan" == item.tipe){
                        body += '<tr>' +
                            '<td colspan="2">' + item.parameter + '</td>' +
                            '</tr>';
                    }else {
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
    if (val == "Kelainan" || val == "Lainnya" || val == "Bentuk" || "Retraksi" == val ||
        "Teraba" == val || val == "Ada") {
        $('#sps-' + ket).show();
    } else {
        $('#sps-' + ket).hide();
    }
}

function loadImgToCanvas(url, id) {
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

function conSPS(jenis, ket){
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show:true, backdrop:'static'});
    $('#save_con_rm').attr('onclick', 'delSPS(\''+jenis+'\', \''+ket+'\')');
}

function delSPS(jenis, ket) {
    $('#modal-confirm-rm').modal('hide');
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    delRowSPS(jenis);
    var result = JSON.stringify(dataPasien);
    startSpin('delete_'+jenis);
    dwr.engine.setAsync(true);
    AsesmenSpesialisAction.saveDelete(idDetailCheckup, jenis, result, {
        callback: function (res) {
            if (res.status == "success") {
                stopSpin('delete_'+jenis);
                $('#warning_sps_' + ket).show().fadeOut(5000);
                $('#msg_sps_' + ket).text("Berhasil menghapus data...");
                detailSPS(jenis);
            } else {
                stopSpin('delete_'+jenis);
                $('#modal_warning').show().fadeOut(5000);
                $('#msg_warning').text(res.msg);
            }
        }
    });
}

function setSph(kelas){
    var temp = 0;
    var temp2 = "";
    var setOption = '<option value="">Select</option>';
    for (var j = 0; j < 2; j++){
        for (var i = 0; i < 90; i++){
            var option = "";
            if(i == 0){
                temp2 = temp + (0.25);
            }else{
                temp2 = temp2 + (0.25);
            }
            var sp = ""+temp2;
            var repl = sp.split('.');
            if(repl.length == 1){
                option = temp2+".00";
            }else{
                if(repl[1].length == 1){
                    option = ""+temp2+"0";
                }else{
                    option = ""+temp2;
                }
            }
            if(j == 0){
                setOption += '<option value="-'+option+'">-'+option+'</option>';
            }else{
                setOption += '<option value="+'+option+'">+'+option+'</option>';
            }
        }
    }
    if(setOption != ''){
        $('.'+kelas).html(setOption);
    }
}