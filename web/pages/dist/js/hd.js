function showModalHD(jenis) {
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }

    if ("pemeriksaan" == jenis) {
        $('#pem2').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    }
    if ("catatan_tranfusi_darah" == jenis) {
        getListMonTransfusiDarah();
    }
    setDataPasien();
    $('#modal-hd-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveMonHD(jenis, ket) {
    var data = [];
    var cek = false;
    if ("pengkajian" == jenis) {
        var va1 = $('[name=pkj1]:checked').val();
        var tVa1 = "";
        if ("Lain-Lain" == va1) {
            var a = $('#ket_pkj1').val();
            if (a != '') {
                tVa1 = a;
            }
        } else {
            tVa1 = va1;
        }
        var va2 = $('[name=pkj2]:checked').val();
        var va3 = $('#pkj3').val();
        var va4 = $('#pkj4').val();
        var va5 = $('#pkj5').val();
        var va6 = $('[name=pkj6]:checked').val();
        var va7 = $('[name=pkj7]:checked').val();
        var tVa7 = "";
        if ("Lain-Lain" == va7) {
            var b = $('#ket_pkj7').val();
            if (b != '') {
                tVa7 = b;
            }
        } else {
            tVa7 = va7;
        }
        var va8 = $('#pkj8').val();
        var va9 = $('#pkj9').val();
        var va10 = $('#pkj10').val();
        var va11 = $('[name=pkj11]:checked').val();
        var va12 = $('#pkj012').val();
        var va13 = $('[name=pkj13]:checked').val();
        var va14 = $('#pkj014').val();
        var va15 = $('[name=pkj15]:checked').val();
        var tVa15 = "";
        if ("Lain-Lain" == va15) {
            var a = $('#ket_pkj15').val();
            if (a != '') {
                tVa15 = a;
            }
        } else {
            tVa15 = va15;
        }
        var va16 = $('[name=pkj16]:checked').val();
        var va17 = $('#pkj17').val();
        var va18 = $('#pkj18').val();
        var va19 = $('#pkj19').val();
        var va20 = $('[name=pkj20]:checked').val();
        var va21 = $('[name=pkj21]:checked').val();
        var va22 = $('[name=pkj22]:checked').val();
        var va23 = $('#pkj23').val();
        var canvasArea = document.getElementById('choice_emoji');
        var cvs = isCanvasBlank(canvasArea);

        if (tVa1 && tVa7 && tVa15 && va3 && va4 && va5 && va8 && va10 && va12 && va14 && va17 && va18 && va19 != '' &&
            va6 && va11 && va13 && va16 && va20 && va21 && va22 && va2 != undefined) {
            var canv = canvasArea.toDataURL("image/png"),
                canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban1': tVa1,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skrening Nyeri',
                'jawaban1': va2,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Scala Nyeri Paint',
                'jawaban1': canv,
                'is_ttd': 'Y',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skala',
                'jawaban1': va3,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban1': va4,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Durasi',
                'jawaban1': va5,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis',
                'jawaban1': va6,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keadaan Umum',
                'jawaban1': tVa7,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban1': va8 + ' mmHg',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            // data.push({
            //     'parameter': 'MAP',
            //     'jawaban1': va9,
            //     'keterangan': jenis,
            //     'jenis': 'monitoring_hd',
            //     'id_detail_checkup': idDetailCheckup
            // });
            data.push({
                'parameter': 'Suhu',
                'jawaban1': va10 + ' ˚C',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban1': va11 + ', Freq: ' + va12 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            // data.push({
            //     'parameter': 'Freq Nadi',
            //     'jawaban1': va12 + ' x/menit',
            //     'keterangan': jenis,
            //     'jenis': 'monitoring_hd',
            //     'id_detail_checkup': idDetailCheckup
            // });
            data.push({
                'parameter': 'Respirasi',
                'jawaban1': va13 + ', Freq: ' + va14 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            // data.push({
            //     'parameter': 'Freq Respirasi',
            //     'jawaban1': va14 + ' x/menit',
            //     'keterangan': jenis,
            //     'jenis': 'monitoring_hd',
            //     'id_detail_checkup': idDetailCheckup
            // });
            data.push({
                'parameter': 'Konjungilva',
                'jawaban1': tVa15,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ekstemitas',
                'jawaban1': va16,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badang',
                'jawaban1': 'Pre HD: ' + va17 + ' Kg, Post HD: ' + va18 + ' Kg, Kering: ' + va19 + ' Kg',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            // data.push({
            //     'parameter': 'BB Post HD',
            //     'jawaban1': va18 + ' Kg',
            //     'keterangan': jenis,
            //     'jenis': 'monitoring_hd',
            //     'id_detail_checkup': idDetailCheckup
            // });
            // data.push({
            //     'parameter': 'BB Pre HD',
            //     'jawaban1': va19 + ' Kg',
            //     'keterangan': jenis,
            //     'jenis': 'monitoring_hd',
            //     'id_detail_checkup': idDetailCheckup
            // });
            // data.push({
            //     'parameter': 'Abdomen',
            //     'jawaban1': va20,
            //     'keterangan': jenis,
            //     'jenis': 'monitoring_hd',
            //     'id_detail_checkup': idDetailCheckup
            // });
            data.push({
                'parameter': 'Akses Vaskuler',
                'jawaban1': va21,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Punctie Arteri',
                'jawaban1': 'HD Kateter: ' + va23,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban1': va22,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }
    if ("resiko_jatuh" == jenis) {
        var va1 = $('[name=res1]:checked').val();
        var va2 = $('[name=res2]:checked').val();
        var va3 = $('[name=res3]:checked').val();
        var va4 = $('[name=res4]:checked').val();
        var va5 = $('[name=res5]:checked').val();
        var va6 = $('[name=res6]:checked').val();
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
                'parameter': 'Riwayat jatuh',
                'jawaban1': isi1,
                'skor': skor1,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Medis Sekunder > 1',
                'jawaban1': isi2,
                'skor': skor2,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alat bantu jalan',
                'jawaban1': isi3,
                'skor': skor3,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Memakai terapi heparin look',
                'jawaban1': isi4,
                'skor': skor4,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Cara berjalan/berpindah',
                'jawaban1': isi5,
                'skor': skor5,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status mental',
                'jawaban1': isi6,
                'skor': skor6,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }
    if ("pemeriksaan" == jenis) {
        var va1 = $('#pem1').val();
        var va2 = $('#pem2').val();
        var va3 = $('#pem3').val();
        var va4 = $('#pem4').val();
        var va5 = $('[name=pem5]:checked').val();
        var va6 = $('[name=pem6]:checked').val();
        var va7 = $('[name=pem7]:checked').val();
        var va8 = $('[name=pem8]:checked').val();
        if (va1 && va2 && va3 && va3 != '' && va5 && va6 && va7 && va8 != undefined) {
            data.push({
                'parameter': 'Pemeriksaan penunjang (Lab, RX, lain-lain)',
                'jawaban1': va1,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal',
                'jawaban1': va2.split("-").reverse().join("-"),
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Score',
                'jawaban1': va3,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rekomendasi',
                'jawaban1': va4,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Adakah keyakinan / tradisi budaya yang berkaitan dengan pelayanan kesehatan yang akan diberikan',
                'jawaban1': va5,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kendala Komunikasi',
                'jawaban1': va6,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Yang merawat dirumah',
                'jawaban1': va7,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kondisi saat ini',
                'jawaban1': va8,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("diagnosa" == jenis) {
        var va1 = $('[name=diag1]:checked');
        var tVa1 = "";
        if (va1 != undefined) {
            $.each(va1, function (i, item) {
                if (va1[i].checked) {
                    if (tVa1 != '') {
                        tVa1 = tVa1 + '|' + va1[i].value;
                    } else {
                        tVa1 = va1[i].value;
                    }
                }
            });
            data.push({
                'parameter': 'Diagnosa Keperawatan',
                'jawaban1': tVa1,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("intervensi" == jenis) {
        var va1 = $('[name=inter1]:checked');
        var tVa1 = "";
        if (va1 != undefined) {
            $.each(va1, function (i, item) {
                if (va1[i].checked) {
                    if (tVa1 != '') {
                        tVa1 = tVa1 + '|' + va1[i].value;
                    } else {
                        tVa1 = va1[i].value;
                    }
                }
            });
            data.push({
                'parameter': 'Intervensi Keperawatan',
                'jawaban1': tVa1,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("asesmen_awal" == jenis) {
        var va1 = $('#asse1').val();
        var va2 = $('#asse2').val();
        var va3 = $('#asse3').val();
        var va4 = $('#asse4').val();
        var va5 = $('#asse5').val();
        var va6 = $('#asse6').val();
        var va7 = $('#asse7').val();
        var va8 = $('#asse8').val();
        var va9 = $('#asse9').val();
        var va10 = $('#asse10').val();
        var va11 = $('#asse11').val();
        var va12 = $('#asse12').val();
        var va13 = $('#asse13').val();
        var va14 = $('#asse14').val();
        var va15 = $('#asse15').val();
        var va16 = $('#asse16').val();
        var va17 = $('#asse17').val();
        var va18 = $('#asse18').val();
        var va19 = $('#asse19').val();
        var va20 = $('#asse20').val();
        var va21 = $('#asse21').val();
        var ttd = document.getElementById("asse22");
        var cekTtd = isCanvasBlank(ttd);
        if (va1 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 &&
            va11 && va12 && va13 && va14 && va15 && va16 && va17 && va18 && va19 && va20 && va21 != '' && !cekTtd) {
            data.push({
                'parameter': 'Anamnese',
                'jawaban1': va1,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Fisik',
                'jawaban1': '',
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tensi',
                'jawaban1': va3 +' mmHg',
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban1': va4 +' x/menit',
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penrnafasan',
                'jawaban1': va5 +' x/menit',
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban1': va6 +' ˚C',
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keadaan Umum',
                'jawaban1': va7,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesadaran',
                'jawaban1': va8,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status Lokalis',
                'jawaban1': va9,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kepala',
                'jawaban1': va10,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Leher',
                'jawaban1': va11,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dada',
                'jawaban1': va12,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perut',
                'jawaban1': va13,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kaki',
                'jawaban1': va14,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pinggang',
                'jawaban1': va15,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alat Kelamin',
                'jawaban1': va16,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anggota Gerak',
                'jawaban1': va17,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksan Penunjang',
                'jawaban1': va18,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Kerja',
                'jawaban1': va19,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Banding',
                'jawaban1': va20,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pengobatan / Tindakan',
                'jawaban1': va21,
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            var ttdA = ttd.toDataURL("image/png"),
                ttdA = ttdA.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': 'TTD Dokter',
                'jawaban1': ttdA,
                'is_ttd': 'Y',
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("tranfusi_informasi" == jenis) {
        var va1 = $('#ti1').val();
        var va2 = $('[name=ti2]:checked').val();
        var va3 = $('[name=ti3]:checked').val();
        var va4 = $('[name=ti4]:checked').val();
        var va5 = $('[name=ti5]:checked').val();
        var va6 = $('[name=ti6]:checked').val();
        var va7 = $('[name=ti7]:checked').val();
        var va8 = $('[name=ti8]:checked').val();
        var va9 = $('[name=ti9]:checked').val();
        var va10 = $('#ti10').val();
        var va11 = $('[name=ti11]:checked').val();
        var va12 = $('[name=ti12]:checked').val();
        var va13 = $('#ti13').val();
        var va14 = $('[name=ti14]:checked').val();

        // if (va1 && va10 && va13 != '' && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 &&
        //     va11 && va12 && va14 != undefined) {
        data.push({
            'parameter': 'Diagnosis (WD dan DD)',
            'jawaban1': va1,
            'jawaban2': va2,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Dasar diagnosis',
            'jawaban1': 'Wawancara riwayat penyakit, pemeriksaan fisik, pemeriksaan laboratorium',
            'jawaban2': va3,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Tindakan Kedokteran',
            'jawaban1': 'Tranfusi Darah',
            'jawaban2': va4,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Indikasi Tindakan',
            'jawaban1': 'Untuk perbaiakan keadaan umum pada kondisi anemia',
            'jawaban2': va5,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Tata Cara',
            'jawaban1': 'Memasukkan darah/produk darah melalui jalur intravena sesua prosedur yang dijelaskan secara lisan',
            'jawaban2': va6,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Tujuan',
            'jawaban1': 'Meningkatkan kadar produk sarah yang dibutuhkan sesuai target',
            'jawaban2': va7,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Resiko',
            'jawaban1': 'Reaksi tranfusi ringan sampai syok (termasuk berbagai kemungkinan yang tidak diprediksi sebelumnya)',
            'jawaban2': va8,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Komplikasi',
            'jawaban1': 'Reaksi tranfusi ringan sampai syok (termasuk berbagai kemungkinan yang tidak diprediksi sebelumnya)',
            'jawaban2': va9,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Prognosis',
            'jawaban1': va10,
            'jawaban2': va11,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Alternatif',
            'jawaban1': 'Pemberian zat besi per oral dengan risiko keadaan umum dapat semakin lemah dan proses penyembuhan menjadi lebih lama',
            'jawaban2': va12,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Perkiraan Biaya',
            'jawaban1': 'Sesuai ketentuan sebagai ' + va13,
            'jawaban2': va14,
            'keterangan': jenis,
            'jenis': 'tranfusi_hd',
            'id_detail_checkup': idDetailCheckup
        });
        cek = true;
        // }
    }

    if ("tranfusi_penyataan" == jenis) {
        var va1 = document.getElementById("tranfusi_penyataan1");
        var va2 = document.getElementById("tranfusi_penyataan2");
        var nm1 = $('#tpe1').val();
        var nm2 = $('#tpe2').val();
        var cekVa1 = isCanvasBlank(va1);
        var cekVa2 = isCanvasBlank(va2);
        if (!cekVa1 && !cekVa2 && nm1 && nm2) {

            var ttd1 = va1.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            var ttd2 = va2.toDataURL("image/png"),
                ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya ' + nm1 + ' telah menerangkan hal-hal di atas secara benar dan jelas dan memberikan kesempatan untuk bertanya dan/atau berdiskusi',
                'jawaban1': ttd1,
                'is_ttd': 'Y',
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya ' + nm2 + ' telah menerima informasi sebagimana di atas yang saya beri tanda/ paraf di kolom kanannya serta telah diberi kesempatan untuk bertanya/berdiskusi, dan telah memahaminya',
                'jawaban1': ttd2,
                'is_ttd': 'Y',
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("tranfusi_persetujuan" == jenis) {
        var pe1 = $('#tperse1').val();
        var pe2 = $('#tperse2').val();
        var pe3 = $('#tperse3').val();
        var pe4 = $('#tperse4').val();
        var pe5 = $('#tperse5').val();
        var pe6 = $('#tperse6').val();
        var pe7 = $('#tperse7').val();
        var pe8 = $('#tperse8').val();
        var pe9 = $('#tperse9').val();
        var pe10 = $('#tperse10').val();
        var ttd1 = document.getElementById("tperse11");
        var ttd2 = document.getElementById("tperse12");
        var ttd3 = document.getElementById("tperse13");

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);

        if (pe1 && pe2 && pe3 && pe4 && pe5 && pe6 && pe7 && pe8 && pe9 && pe10 != '' && !cekTtd1 && !cekTtd2 && !cekTtd3) {
            data.push({
                'parameter': 'pernyataan',
                'jawaban1': 'Yang bertanda tangan dibawah ini, saya :',
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama',
                'jawaban1': pe1,
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Umur',
                'jawaban1': pe2,
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': pe3,
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban1': pe4,
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan',
                'jawaban1': pe5,
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban1': pe6,
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Pasien',
                'jawaban1': pe7,
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Umur',
                'jawaban1': pe8,
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': pe9,
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban1': pe10,
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'pernyataan',
                'jawaban1': 'Saya memahami perlunya dan manfaat tindakan tersebut sebagaimana telah dijelaskan seperti di atas kepada saya termasuk resiko dan komplikasi yang timbul. Saya juga menyadari bahwa oleh karena itu ilmu kedokteran bukan ilmu pasti. maka keberhasilan tindakan kedokteran bukan keniscayaan, tetapi bergantung kepada izin Tuhan Yang Maha Esa. Tanggal '+converterDate(new Date())+', Jam '+converterTime(new Date()),
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            var ttdA = ttd1.toDataURL("image/png"),
                ttdA = ttdA.replace(/^data:image\/(png|jpg);base64,/, "");

            var ttdB = ttd2.toDataURL("image/png"),
                ttdB = ttdB.replace(/^data:image\/(png|jpg);base64,/, "");

            var ttdC = ttd3.toDataURL("image/png"),
                ttdC = ttdC.replace(/^data:image\/(png|jpg);base64,/, "");

            if (!cekTtd1 && !cekTtd2 && !cekTtd3) {
                data.push({
                    'parameter': 'TTD Yang Menyatakan',
                    'jawaban1': ttdA,
                    'is_ttd': 'Y',
                    'keterangan': jenis,
                    'jenis': 'tranfusi_hd',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'TTD Saksi',
                    'jawaban1': ttdB,
                    'is_ttd': 'Y',
                    'keterangan': jenis,
                    'jenis': 'tranfusi_hd',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'TTD Perawat',
                    'jawaban1': ttdC,
                    'is_ttd': 'Y',
                    'keterangan': jenis,
                    'jenis': 'tranfusi_hd',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            cek = true;
        }
    }

    if ("persetujuan_hd_informasi" == jenis) {

        var va1 = $('[name=tti2]:checked').val();
        var va2 = $('[name=tti2]:checked').val();
        var va3 = $('[name=tti3]:checked').val();
        var va4 = $('[name=tti4]:checked').val();
        var va5 = $('[name=tti5]:checked').val();
        var va6 = $('[name=tti6]:checked').val();
        var va7 = $('[name=tti7]:checked').val();
        var va8 = $('[name=tti8]:checked').val();
        var va9 = $('[name=tti9]:checked').val();
        var va10 = $('#tti10').val();
        var va11 = $('[name=tti11]:checked').val();

        // if (va10 != '' && va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 &&
        //     va11 != undefined) {
        data.push({
            'parameter': 'Diagnosis (WD dan DD)',
            'jawaban1': 'CKD V on HD',
            'jawaban2': va1,
            'keterangan': jenis,
            'jenis': 'persetujuan_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Dasar diagnosis',
            'jawaban1': 'Riwayat penyakit, pemeriksaan fisik, pemeriksaan penunjang',
            'jawaban2': va2,
            'keterangan': jenis,
            'jenis': 'persetujuan_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Tindakan Kedokteran',
            'jawaban1': 'Hemodialisa',
            'jawaban2': va3,
            'keterangan': jenis,
            'jenis': 'persetujuan_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Indikasi Tindakan',
            'jawaban1': 'CKD V, gangguan eletronik, produk sampah ginjal dalam kadar toksik, sindroma kelebihan cairan. Kegawatan dibidang nefrologi (hipertensi, oedema paru, encepalopati reumia, anuria/oliguria)',
            'jawaban2': va4,
            'keterangan': jenis,
            'jenis': 'persetujuan_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Tata Cara',
            'jawaban1': 'Pembuluh darah arteri dan vena dihubungkan dengan mesin hemodialisa yang menganalisa darah, lalu sampah dan cairan dipindahkan dari tubuh dan darah kembali ke tubuh',
            'jawaban2': va5,
            'keterangan': jenis,
            'jenis': 'persetujuan_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Tujuan',
            'jawaban1': 'Mengatur keseimbangan eletronik, keseimbangan cairan dan membersihkan tubuh dari sampah ginjal',
            'jawaban2': va6,
            'keterangan': jenis,
            'jenis': 'persetujuan_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Resiko',
            'jawaban1': 'Pendarahan, pembengkakan dan infeksi di tempat penusukan, mual-muntah, kontaminasi air yang digunakan hemodialisa, kram otot, penurunan tekanan darah, gejala ketidakseimbangan, irama jantung tidak teratur, reaksi cairan dialisat, kematian',
            'jawaban2': va7,
            'keterangan': jenis,
            'jenis': 'persetujuan_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Prognosis',
            'jawaban1': 'Reaksi tranfusi ringan sampai syok (termasuk berbagai kemungkinan yang tidak diprediksi sebelumnya)',
            'jawaban2': va8,
            'keterangan': jenis,
            'jenis': 'persetujuan_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Alternattif',
            'jawaban1': 'CAPD, Cangkok ginjal',
            'jawaban2': va9,
            'keterangan': jenis,
            'jenis': 'persetujuan_hd',
            'id_detail_checkup': idDetailCheckup
        });
        data.push({
            'parameter': 'Perkiraan Biaya',
            'jawaban1': va10,
            'jawaban2': va11,
            'keterangan': jenis,
            'jenis': 'persetujuan_hd',
            'id_detail_checkup': idDetailCheckup
        });

        cek = true;
        // }
    }

    if ("persetujuan_hd_penyataan" == jenis) {
        var va1 = document.getElementById("persetujuan_hd_penyataan1");
        var va2 = document.getElementById("persetujuan_hd_penyataan2");
        var nm1 = $('#ttpe1').val();
        var nm2 = $('#ttpe2').val();
        var cekVa1 = isCanvasBlank(va1);
        var cekVa2 = isCanvasBlank(va2);
        if (!cekVa1 && !cekVa2 && nm1 && nm2) {

            var ttd1 = va1.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            var ttd2 = va2.toDataURL("image/png"),
                ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya ' + nm1 + ' telah menerangkan hal-hal di atas secara benar dan jelas dan memberikan kesempatan untuk bertanya dan/atau berdiskusi',
                'jawaban1': ttd1,
                'is_ttd': 'Y',
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya ' + nm2 + ' telah menerima informasi sebagimana di atas yang saya beri tanda/ paraf di kolom kanannya serta telah diberi kesempatan untuk bertanya/berdiskusi, dan telah memahaminya',
                'jawaban1': ttd2,
                'is_ttd': 'Y',
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("persetujuan_hd_persetujuan" == jenis) {
        var pe1 = $('#pperse1').val();
        var pe2 = $('#pperse2').val();
        var pe3 = $('#pperse3').val();
        var pe4 = $('#pperse4').val();
        var pe5 = $('#pperse5').val();
        var pe6 = $('#pperse6').val();
        var pe7 = $('#pperse7').val();
        var pe8 = $('#pperse8').val();
        var pe9 = $('#pperse9').val();
        var pe10 = $('#pperse10').val();
        var ttd1 = document.getElementById("pperse11");
        var ttd2 = document.getElementById("pperse12");
        var ttd3 = document.getElementById("pperse13");

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);


        if (pe1 && pe2 && pe3 && pe4 && pe5 && pe6 && pe7 && pe8 && pe9 && pe10 != '' && !cekTtd1 && !cekTtd2 && !cekTtd3) {
            data.push({
                'parameter': 'pernyataan',
                'jawaban1': 'Yang bertanda tangan dibawah ini, saya :',
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama',
                'jawaban1': pe1,
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Umur',
                'jawaban1': pe2,
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': pe3,
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban1': pe4,
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan',
                'jawaban1': pe5,
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban1': pe6,
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Pasien',
                'jawaban1': pe7,
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Umur',
                'jawaban1': pe8,
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': pe9,
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban1': pe10,
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'pernyataan',
                'jawaban1': 'Saya memahami perlunya dan manfaat tindakan tersebut sebagaimana telah dijelaskan seperti di atas kepada saya termasuk resiko dan komplikasi yang timbul. Saya juga menyadari bahwa oleh karena itu ilmu kedokteran bukan ilmu pasti. maka keberhasilan tindakan kedokteran bukan keniscayaan, tetapi bergantung kepada izin Tuhan Yang Maha Esa. Tanggal '+converterDate(new Date())+', Jam '+converterTime(new Date()),
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });

            var ttdA = ttd1.toDataURL("image/png"),
                ttdA = ttdA.replace(/^data:image\/(png|jpg);base64,/, "");

            var ttdB = ttd2.toDataURL("image/png"),
                ttdB = ttdB.replace(/^data:image\/(png|jpg);base64,/, "");

            var ttdC = ttd3.toDataURL("image/png"),
                ttdC = ttdC.replace(/^data:image\/(png|jpg);base64,/, "");

            if (!cekTtd1 && !cekTtd2 && !cekTtd3) {
                data.push({
                    'parameter': 'TTD Yang Menyatakan',
                    'jawaban1': ttdA,
                    'is_ttd': 'Y',
                    'keterangan': jenis,
                    'jenis': 'persetujuan_hd',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'TTD Saksi',
                    'jawaban1': ttdB,
                    'is_ttd': 'Y',
                    'keterangan': jenis,
                    'jenis': 'persetujuan_hd',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'TTD Perawat',
                    'jawaban1': ttdC,
                    'is_ttd': 'Y',
                    'keterangan': jenis,
                    'jenis': 'persetujuan_hd',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            cek = true;
        }
    }

    if ("travelling_dialysis" == jenis) {
        var pe1 = $('#td1').val();
        var pe2 = $('#td2').val();
        var pe3 = $('#td3').val();
        var pe4 = $('#td4').val();
        var pe5 = $('#td5').val();
        var pe6 = $('#td6').val();
        var pe7 = $('#td7').val();
        var pe8 = $('#td8').val();
        var pe9 = $('#td9').val();
        var pe10 = $('#td10').val();
        var pe11 = $('#td11').val();
        var pe12 = $('#td12').val();
        var pe13 = $('#td13').val();
        var pe14 = $('#td14').val();
        var pe15 = $('#td15').val();
        var pe16 = $('#td16').val();
        var pe17 = $('#td17').val();
        var pe18 = $('#td18').val();
        var pe19 = $('#td19').val();
        var ttd = document.getElementById("hd_ttd_dokter");
        var cekTtd = isCanvasBlank(ttd);

        if (pe1 && pe2 && pe3 && pe4 && pe5 && pe6 && pe7 && pe8 && pe9 && pe10 && pe11 &&
            pe12 && pe13 && pe14 && pe15 && pe16 && pe17 && pe18 && pe19 != '' && !cekTtd) {

            var ttdDok = ttd.toDataURL("image/png"),
                ttdDok = ttdDok.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Nama Pasien',
                'jawaban1': pe1,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban1': pe2,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa',
                'jawaban1': pe3,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Mulai Hemodialisa',
                'jawaban1': pe4,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi',
                'jawaban1': pe5,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban1': pe6 +' mmHg',
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Komposisi Dialisat',
                'jawaban1': pe7,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tipe Dializer',
                'jawaban1': pe8,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Komplikasi Saat HD',
                'jawaban1': pe9,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pungsi',
                'jawaban1': pe10,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dosis Heparin',
                'jawaban1': pe11,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Interval Hemodialisa',
                'jawaban1': pe12,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TMP',
                'jawaban1': pe13,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'HbsAg',
                'jawaban1': pe14,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Transfusi',
                'jawaban1': pe15,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diet',
                'jawaban1': pe16,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Allergi',
                'jawaban1': pe17,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan Kering',
                'jawaban1': pe18 +' Kg',
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Golongan Darah',
                'jawaban1': pe19,
                'keterangan': jenis,
                'jenis': 'travelling',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanda Tangan Dokter',
                'jawaban1': ttdDok,
                'is_ttd': 'Y',
                'keterangan': jenis,
                'jenis': 'hd_ttd_dokter',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("perencanaan_hemodialisa_pasien") {
        var va1 = $('#ph1').val();
        var va2 = $('#ph2').val();
        var va3 = $('#ph3').val();
        var va4 = $('#ph4').val();
        var va5 = $('#ph5').val();
        var va6 = $('#ph6').val();
        var va7 = $('#ph7').val();
        var va8 = $('#ph8').val();
        var va9 = $('#ph9').val();
        var va10 = $('#ph10').val();
        var va11 = $('#ph11').val();
        var va12 = $('#ph12').val();
        var va13 = $('#ph13').val();
        var ttd = document.getElementById("ph14");
        var cekTtd = isCanvasBlank(ttd);

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va11 && va12 != '' && !cekTtd) {
            data.push({
                'parameter': 'Nama Pasien',
                'jawaban1': va1,
                'keterangan': jenis,
                'jenis': 'perencanaan_hemodialisa',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': va13,
                'keterangan': jenis,
                'jenis': 'perencanaan_hemodialisa',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Umur',
                'jawaban1': va2,
                'keterangan': jenis,
                'jenis': 'perencanaan_hemodialisa',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban1': va3,
                'keterangan': jenis,
                'jenis': 'perencanaan_hemodialisa',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'No Asuransi/ BPJS/ Swasta',
                'jawaban1': va4,
                'keterangan': jenis,
                'jenis': 'perencanaan_hemodialisa',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perencanaan Hemodialisa',
                'jawaban1': 'Sebanyak ' + va5 + ' kali/minggu|' + 'Lama Hemodialisa ' + va6 + ' Jam ' + va7 + ' Menit|' + 'UF ' + va8 + ' Liter, Heparin ' + va9 + '|QB ' + va10 + ', BD ' + va11,
                'keterangan': jenis,
                'jenis': 'perencanaan_hemodialisa',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Catatan',
                'jawaban1': va12,
                'keterangan': jenis,
                'jenis': 'perencanaan_hemodialisa',
                'id_detail_checkup': idDetailCheckup
            });

            var canv = ttd.toDataURL("image/png"),
                canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD Dokter',
                'jawaban1': canv,
                'is_ttd': 'Y',
                'keterangan': jenis,
                'jenis': 'perencanaan_hemodialisa',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        $('#save_hd_' + jenis).hide();
        $('#load_hd_' + jenis).show();
        dwr.engine.setAsync(true);
        HemodialisaAction.saveHemodialisa(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_hd_' + jenis).show();
                    $('#load_hd_' + jenis).hide();
                    $('#modal-hd-' + jenis).modal('hide');
                    $('#warning_hd_' + ket).show().fadeOut(5000);
                    $('#msg_hd_' + ket).text("Berhasil menambahkan data hd...");
                    $('#modal-hd-' + jenis).scrollTop(0);
                } else {
                    $('#save_hd_' + jenis).show();
                    $('#load_hd_' + jenis).hide();
                    $('#warning_hd_' + jenis).show().fadeOut(5000);
                    $('#msg_hd_' + jenis).text(res.msg);
                    $('#modal-hd-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_hd_' + jenis).show().fadeOut(5000);
        $('#msg_hd_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-hd-' + jenis).scrollTop(0);
    }
}

function detailMonHD(jenis) {
    if (jenis != '') {
        var head = "";
        var body = "";
        var totalSkor = 0;
        var first = "";
        var last = "";
        var tgl = "";
        var cekData = false;

        HemodialisaAction.getListHemodialisa(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var jwb = "";
                    var jwb2 = "";
                    if (item.jawaban1 != null) {
                        jwb = item.jawaban1;
                    }
                    if (item.jawaban2 != null) {
                        jwb2 = item.jawaban2;
                    }
                    if ("Y" == item.isTtd) {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + '<img src="' + jwb + '" style="width: 100px">' + '</td>' +
                            '</tr>';
                    } else if ("intervensi" == item.keterangan || "diagnosa" == item.keterangan) {
                        var li = "";
                        var isi = jwb.split("|");
                        $.each(isi, function (i, item) {
                            li += '<li>' + item + '</li>';
                        });
                        body += '<tr>' +
                            '<td width="40%">' + item.parameter + '</td>' +
                            '<td>' + '<ul style="margin-left: 10px">' + li + '</ul>' + '</td>' +
                            '</tr>';
                    } else if ("resiko_jatuh" == item.keterangan) {
                        if (item.skor != null) {
                            totalSkor = parseInt(totalSkor) + parseInt(item.skor);
                        }
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb + '</td>' +
                            '<td align="center">' + item.skor + '</td>' +
                            '</tr>';
                    } else if ("tranfusi_informasi" == item.keterangan || "persetujuan_hd_informasi" == item.keterangan) {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb + '</td>' +
                            '<td align="center">' + cekIcons(jwb2) + '</td>' +
                            '</tr>';
                    } else if ("tranfusi_penyataan" == item.keterangan || "persetujuan_hd_penyataan" == item.keterangan) {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td align="center">' + '<img src="' + jwb + '" style="width: 150px">' + '</td>' +
                            '</tr>';
                    } else if ("pernyataan" == item.parameter) {
                        body += '<tr>' +
                            '<td colspan="2">' + jwb + '</td>' +
                            '</tr>';
                    } else if ("perencanaan_hemodialisa_pasien" == item.keterangan) {
                        if ("Perencanaan Hemodialisa" == item.parameter) {
                            var isi = jwb.split("|");
                            var li = "";
                            $.each(isi, function (i, item) {
                                li += '<li style="list-style-type: none">' + item + '</li>';
                            });
                            if (li != '') {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<ul style="margin-left: 1px">' + li + '</ul>' + '</td>' +
                                    '</tr>';
                            }
                        } else {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + jwb + '</td>' +
                                '</tr>';
                        }
                    } else if ("Scala Nyeri Paint" == item.parameter) {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + '<img src="' + jwb + '" style="width: 100px">' + '</td>' +
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

            if ("resiko_jatuh" == jenis) {
                var con = "";
                if (totalSkor >= 0 && totalSkor <= 24) {
                    con = "Tidak berisiko";
                } else if (totalSkor >= 25 && totalSkor <= 50) {
                    con = "Resiko rendah";
                } else {
                    con = "Resiko tinggi";
                }

                if (cekData) {
                    last = '<tr style="font-weight: bold"><td colspan="2">Total Skor</td><td align="center">' + totalSkor + '</td></tr>' +
                        '<tr style="font-weight: bold" bgcolor="#ffebcd"><td colspan="2">Resiko Jatuh</td><td align="center">' + con + '</td></tr>';
                }
            }

            if ("tranfusi_informasi" == jenis || "persetujuan_hd_informasi" == jenis) {
                if (cekData) {
                    head = '<tr style="font-weight: bold"><td width="25%">Jenis Informasi</td><td>Isi Informasi</td><td align="center" width="15%">Check (<i class="fa fa-check"></i>)</td></tr>'
                }
            }

            // if ("tranfusi_persetujuan" == jenis || "persetujuan_hd_persetujuan" == jenis) {
            //     if (cekData) {
            //         first = '<tr>' +
            //             '<td colspan="2">Yang bertanda tangan dibawah ini, saya :</td>' +
            //             '</tr>';
            //         last = '<tr>' +
            //             '<td colspan="2">Saya memahami perlunya dan manfaat tindakan tersebut sebagaimana telah dijelaskan seperti di atas kepada saya termasuk resiko dan komplikasi yang timbul. Saya juga menyadari bahwa oleh karena itu ilmu kedokteran bukan ilmu pasti. maka keberhasilan tindakan kedokteran bukan keniscayaan, tetapi bergantung kepada izin Tuhan Yang Maha Esa. Tanggal ' + converterDate(tgl) + ', Jam ' + converterDateTime(tgl) + '</td>' +
            //             '</tr>';
            //     }
            // }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_hd_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_hd_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_hd_' + jenis).attr('src', url);
            $('#btn_hd_' + jenis).attr('onclick', 'delRowHemodialisa(\'' + jenis + '\')');
        });
    }
}

function delRowHemodialisa(id) {
    $('#del_hd_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_hd_' + id).attr('src', url);
    $('#btn_hd_' + id).attr('onclick', 'detailMonHD(\'' + id + '\')');
}

function showKetPkj1(val) {
    if (val == "Lain-Lain") {
        $('#ket_pkj1').show();
    } else {
        $('#ket_pkj1').hide();
    }
}

function showKetPkj7(val) {
    if (val == "Lain-Lain") {
        $('#ket_pkj7').show();
    } else {
        $('#ket_pkj7').hide();
    }
}

function showKetPkj15(val) {
    if (val == "Lain-Lain") {
        $('#ket_pkj15').show();
    } else {
        $('#ket_pkj15').hide();
    }
}

function getListMonTransfusiDarah() {
    var body = "";
    MonitoringTransfusiDarahAction.getListMonitoringTransfusiDarah(idDetailCheckup, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                body +=
                    '<tr>' +
                    '<td align="center">' + set(item.waktu) + '</td>' +
                    '<td align="center">' + set(item.tekananDarah) + '</td>' +
                    '<td align="center">' + set(item.nadi) + '</td>' +
                    '<td align="center">' + set(item.suhu) + '</td>' +
                    '<td align="center">' + set(item.rr) + '</td>' +
                    '<td align="center">' + set(item.tidakAdaReaksi) + '</td>' +
                    '<td align="center">' + set(item.gatal) + '</td>' +
                    '<td align="center">' + set(item.urtikariaBeratSedang) + '</td>' +
                    '<td align="center">' + set(item.kulitKemerahanSedang) + '</td>' +
                    '<td align="center">' + set(item.demamSedang) + '</td>' +
                    '<td align="center">' + set(item.menggigilSedang) + '</td>' +
                    '<td align="center">' + set(item.gelisahSedang) + '</td>' +
                    '<td align="center">' + set(item.peningkatanDetakJantungSedang) + '</td>' +
                    '<td align="center">' + set(item.demamMengancam) + '</td>' +
                    '<td align="center">' + set(item.menggigilSedang) + '</td>' +
                    '<td align="center">' + set(item.gelisahSedang) + '</td>' +
                    '<td align="center">' + set(item.peningkatanDetakJantungSedang) + '</td>' +
                    '<td align="center">' + set(item.nafasCepatMengancam) + '</td>' +
                    '<td align="center">' + set(item.urinMengancam) + '</td>' +
                    '<td align="center">' + set(item.pendarahanJantungMengancam) + '</td>' +
                    '<td align="center">' + set(item.kesadaranMengancam) + '</td>' +
                    '</tr>';
            });
            $('#body_hd_catatan_tranfusi_darah').html(body);
        }
    });
}

function set(item) {
    var res = "";
    if (item != null && item != '') {
        if (item == "Ya") {
            res = '<i class="fa fa-check" style="color: #449d44"></i>';
        } else if (item == "Tidak") {
            res = '<i class="fa fa-times" style="color: #dd4b39"></i>';
        } else {
            res = item;
        }
    }
    return res;
}

function saveMonTransfusiDarah(jenis) {
    var data = "";
    var va1 = $('#ct1').val();
    var va2 = $('#ct2').val();
    var va3 = $('#ct3').val();
    var va4 = $('#ct4').val();
    var va5 = $('#ct5').val();
    var va6 = $('#ct6').val();
    var va7 = $('[name=ct7]:checked').val();
    var va8 = $('[name=ct8]:checked').val();
    var va9 = $('[name=ct9]:checked').val();
    var va10 = $('[name=ct10]:checked').val();
    var va11 = $('[name=ct11]:checked').val();
    var va12 = $('[name=ct12]:checked').val();
    var va13 = $('[name=ct13]:checked').val();
    var va14 = $('[name=ct14]:checked').val();
    var va15 = $('[name=ct15]:checked').val();
    var va16 = $('[name=ct16]:checked').val();
    var va17 = $('[name=ct17]:checked').val();
    var va18 = $('[name=ct18]:checked').val();
    var va19 = $('[name=ct19]:checked').val();
    var va20 = $('[name=ct20]:checked').val();
    var va21 = $('[name=ct21]:checked').val();
    var va22 = jenis;

    if (va1 && va2 && va3 && va4 && va5 && va6 != '' &&
        va7 && va8 && va9 && va10 && va11 && va12 && va13 && va14 &&
        va15 && va16 && va17 && va18 && va19 && va20 && va21 != undefined) {

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1,
            'tekanan_darah': va2,
            'nadi': va3,
            'suhu': va4,
            'rr': va5,
            'tidak_ada_reaksi': va6,
            'gatal': va7,
            'urtikaria_berat_sedang': va8,
            'kulit_kemerahan_sedang': va9,
            'demam_sedang': va10,
            'menggigil_sedang': va11,
            'gelisah_sedang': va12,
            'peningkatan_detak_jantung_sedang': va13,
            'demam_mengancam': va14,
            'menggigil_mengancam': va15,
            'gelisah_mengancam': va16,
            'peningkatan_detak_jantung_mengancam': va17,
            'nafas_cepat_mengancam': va18,
            'urin_mengancam': va19,
            'pendarahan_jantung_mengancam': va20,
            'kesadaran_mengancam': va21,
            'keterangan': va22
        };

        var result = JSON.stringify(data);
        $('#save_hd_catatan_tranfusi').hide();
        $('#load_hd_catatan_tranfusi').show();
        dwr.engine.setAsync(true);
        MonitoringTransfusiDarahAction.saveMonitoringTransfusiDarah(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_hd_catatan_tranfusi').show();
                    $('#load_hd_catatan_tranfusi').hide();
                    $('#modal-hd-catatan_tranfusi').modal('hide');
                    $('#warning_hd_catatan_tranfusi_darah').show().fadeOut(5000);
                    $('#msg_hd_catatan_tranfusi_darah').text("Berhasil menambahkan data monitoring...");
                    getListMonTransfusiDarah();
                } else {
                    $('#save_hd_catatan_tranfusi').show();
                    $('#load_hd_catatan_tranfusi').hide();
                    $('#warning_hd_catatan_tranfusi').show().fadeOut(5000);
                    $('#msg_hd_catatan_tranfusi').text(res.msg);
                }
            }
        });

    } else {
        $('#warning_hd_catatan_tranfusi').show().fadeOut(5000);
        $('#msg_hd_catatan_tranfusi').text("Silahkan cek kembali data inputan anda...!");
        $('#modal-hd-catatan_tranfusi').scrollTop(0);
    }
}