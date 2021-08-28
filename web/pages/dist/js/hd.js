function showModalHD(jenis, idRM, isSetIdRM) {
    if(isSetIdRM == "Y"){
        tempidRm = idRM;
    }
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
    if("tindakan_medis_hd" == jenis){
        selectOptionTM('hd', jenis);
    }

    if("observasi_tindakan" == jenis){
        $('#obs1, #obs2, #obs3, #obs4, #obs5, #obs6, #obs7, #obs8, #obs9, #obs10, #obs11, #obs12, #obs13').val('');
    }
    setDataPasien();
    $('#modal-hd-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveMonHD(jenis, ket) {
    var data = [];
    var cek = false;
    var dataPasien = "";

    dataPasien = {
        'no_checkup' : noCheckup,
        'id_detail_checkup' : idDetailCheckup,
        'id_pasien' : idPasien,
        'id_rm' : tempidRm
    }

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
                'tipe': 'gambar',
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
            data.push({
                'parameter': 'Respirasi',
                'jawaban1': va13 + ', Freq: ' + va14 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
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
            data.push({
                'parameter': 'Akses Vaskuler',
                'jawaban1': va21,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            // data.push({
            //     'parameter': 'Punctie Arteri',
            //     'jawaban1': 'HD Kateter: ' + va23,
            //     'keterangan': jenis,
            //     'jenis': 'monitoring_hd',
            //     'id_detail_checkup': idDetailCheckup
            // });
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
                    if(item.value != 'on' && item.value != ''){
                        if (tVa1 != '') {
                            tVa1 = tVa1 + '|' + va1[i].value;
                        } else {
                            tVa1 = va1[i].value;
                        }
                        cek = true;
                    }

                }
            });
            if(tVa1 != ''){
                data.push({
                    'parameter': 'Diagnosa Keperawatan',
                    'jawaban1': tVa1,
                    'keterangan': jenis,
                    'jenis': 'monitoring_hd',
                    'id_detail_checkup': idDetailCheckup
                });
            }

        }
    }

    if ("intervensi" == jenis) {
        var va1 = $('[name=inter1]:checked');
        var va2 = $('[name=inter2]:checked');
        var tVa1 = "";
        var tVa2 = "";
        if (va1 && va2 != undefined) {
            $.each(va1, function (i, item) {
                if (va1[i].checked) {
                    if(item.value != 'on' && item.value != ''){
                        if (tVa1 != '') {
                            tVa1 = tVa1 + '|' + va1[i].value;
                        } else {
                            tVa1 = va1[i].value;
                        }
                        cek = true;
                    }
                }
            });
            $.each(va2, function (i, item) {
                if (va2[i].checked) {
                    if(item.value != 'on' && item.value != ''){
                        if (tVa2 != '') {
                            tVa2 = tVa2 + '|' + va2[i].value;
                        } else {
                            tVa2 = va2[i].value;
                        }
                        cek = true;
                    }
                }
            });
            if(tVa1 != ''){
                data.push({
                    'parameter': 'Intervensi Keperawatan',
                    'jawaban1': 'Mandiri',
                    'jawaban2': tVa1,
                    'keterangan': jenis,
                    'jenis': 'monitoring_hd',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if(tVa2 != ''){
                data.push({
                    'parameter': '',
                    'jawaban1': 'Kolaborasi',
                    'jawaban2': tVa2,
                    'keterangan': jenis,
                    'jenis': 'monitoring_hd',
                    'id_detail_checkup': idDetailCheckup
                });
            }
        }
    }

    if ("instruksi_medik" == jenis) {
        var va1 = "";
        var va2 = $('#im2').val();
        var va3 = $('#im3').val();
        var va4 = $('#im4').val();
        var va5 = $('#im5').val();
        var va6 = "";
        var va7 = "";
        var va8 = $('#im8').val();
        var va9 = $('#im9').val();
        var va10 = "";

        var va15 = $('#im015').val();

        var ket611 = $('#ket_im611').val();
        var ket612 = $('#ket_im612').val();
        var ket621 = $('#ket_im621').val();

        var ttd = document.getElementById('im016');
        var namaTerang = $('#im_nama_petugas').val();
        var sip = $('#im_sip_petugas').val();

        var cekTtd = isCanvasBlank(ttd);

        var tVa1 = $('[name=im1]:checked').val();;
        if (tVa1 != undefined) {
            var kete = $('#ket_im1').val();
            if(tVa1 == "Lain-Lain"){
                if(kete != ''){
                    va1 = tVa1+', '+ket;
                }
            }else{
                va1 = tVa1;
            }
        }

        var tVa6 = $('[name=im6]');
        $.each(tVa6, function (i, item) {
            if(item.checked){
                var isi = "";
                if(item.value == 'Na'){
                    isi += item.value+ket611+', '+ket621;
                }else{
                    isi += item.value+', '+ket621;
                }
                if(va6 != ''){
                    va6 = va6 +', '+isi;
                }else{
                    va6 = isi;
                }
            }
        });

        var tVa7 = $('[name=im7]');
        $.each(tVa7, function (i, item) {
            if(item.checked){
                if(va7 != ''){
                    va7 = va7 +', '+item.value;
                }else{
                    va7 = item.value;
                }
            }
        });

        var tV10 = $('[name=im10]');
        $.each(tV10, function (i, item) {
            if(item.checked){
                if(va10 != ''){
                    va10 = va10 +'|'+item.value;
                }else{
                    va10 = item.value;
                }
            }
        });

        if(va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9
            && va10 && va15 && namaTerang != '' && !cekTtd){
            data.push({
                'parameter': 'Resep HD',
                'jawaban1': va1+', HD : '+va2+' jam, QB : '+replaceUnderLine(va3)+' ml/menit, QD : '+replaceUnderLine(va4)+' ml/menit, UF Goal: '+va5+' ml',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Prog Profilling',
                'jawaban1': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dialisat',
                'jawaban1': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dosis Sirkulasi',
                'jawaban1': va8+' lu',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dosis Awal',
                'jawaban1': va9+ ' lu',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dosis Maintenance',
                'jawaban1': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Catatan lain / Resep Obat',
                'jawaban1': va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': 'TTD Dokter',
                'jawaban1': ttd1,
                'keterangan': jenis,
                'jenis': ket,
                'nama_terang': namaTerang,
                'sip': sip,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("penyulit_hd" == jenis) {
        var va1 = "";
        var va2 = $('#phd2').val();
        var tensi = $('#phd2_tensi').val();
        var suhu = $('#phd2_suhu').val();
        var rr = $('#phd2_rr').val();
        var nadi = $('#phd2_nadi').val();
        var object = $('#ket_phd2').val();
        var va3 = $('#phd3').val();
        var va4 = $('#phd4').val();
        var va5 = $('#phd5').val();
        var va6 = $('#phd6').val();
        var va7 = $('#phd7').val();
        var va8 = $('#phd8').val();
        var va9 = document.getElementById('phd9');
        var nama1 = $('#nama_terang_phd9').val();
        var va10 = document.getElementById('phd10');
        var nama2 = $('#nama_terang_phd10').val();
        var sip2 = $('#sip_phd10').val();
        var va11 = document.getElementById('phd011');;
        var nama3 = $('#nama_terang_phd011').val();
        var sip3 = $('#sip_phd011').val();

        var tV1 = $('[name=phd1]');
        $.each(tV1, function (i, item) {
            if(item.checked){
                if(va1 != ''){
                    va1 = va1 +'|'+item.value;
                }else{
                    va1 = item.value;
                }
            }
        });

        var cekTtd1 = isCanvasBlank(va9);
        var cekTtd2 = isCanvasBlank(va10);
        var cekTtd3 = isCanvasBlank(va11);

        if(va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && nama1 && nama2 &&
            nama3 && sip2 && sip3 != '' && !cekTtd1 && !cekTtd2 && !cekTtd3){
            data.push({
                'parameter': 'Penyulit Selama HD',
                'jawaban1': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            var s = 'Subjective : '+va2;
            var o = 'Objective : Tensi :'+replaceUnderLine(tensi)+' mmHg, Nadi : '+nadi+' x/menit, RR : '+rr+' x/menit, Suhu : '+suhu+' C, Keterangan' +object+'. ';
            var a = 'Assement : '+va3+'. ';
            var p = 'Planning : '+va4+'. ';
            data.push({
                'parameter': 'Evaluasi Keperawatan',
                'jawaban1': s+o+a+p,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Edukasi',
                'jawaban1': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Discharge Planning',
                'jawaban1': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Catatan Yang Akan Datang',
                'jawaban1': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Askes Vaskuler Oleh',
                'jawaban1': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            var ttd1 = convertToDataURL(va9);
            var ttd2 = convertToDataURL(va10);
            var ttd3 = convertToDataURL(va11);

            data.push({
                'parameter': 'TTD Pasien',
                'jawaban1': ttd1,
                'keterangan': jenis,
                'jenis': ket,
                'nama_terang': nama1,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Petugas',
                'jawaban1': ttd2,
                'keterangan': jenis,
                'jenis': ket,
                'nama_terang': nama2,
                'sip': sip2,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Nama Perawat Yang Bertugas',
                'jawaban1': ttd3,
                'keterangan': jenis,
                'jenis': ket,
                'nama_terang': nama3,
                'sip': sip3,
                'tipe': 'ttd',
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
        var nama = $('#nama_terang_asse22').val();
        var sip = $('#sip_asse22').val();
        var ttd = document.getElementById("asse22");
        var cekTtd = isCanvasBlank(ttd);
        if (va1 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && nama && sip &&
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
                'jawaban1': replaceUnderLine(va3) +' mmHg',
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
                'tipe': 'ttd',
                'keterangan': jenis,
                'jenis': 'asesmen_hd',
                'nama_terang': nama,
                'sip': sip,
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
                'tipe': 'ttd',
                'keterangan': jenis,
                'jenis': 'tranfusi_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya ' + nm2 + ' telah menerima informasi sebagimana di atas yang saya beri tanda/ paraf di kolom kanannya serta telah diberi kesempatan untuk bertanya/berdiskusi, dan telah memahaminya',
                'jawaban1': ttd2,
                'tipe': 'ttd',
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
                    'tipe': 'ttd',
                    'keterangan': jenis,
                    'jenis': 'tranfusi_hd',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'TTD Saksi',
                    'jawaban1': ttdB,
                    'tipe': 'ttd',
                    'keterangan': jenis,
                    'jenis': 'tranfusi_hd',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'TTD Perawat',
                    'jawaban1': ttdC,
                    'tipe': 'ttd',
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
                'tipe': 'ttd',
                'keterangan': jenis,
                'jenis': 'persetujuan_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya ' + nm2 + ' telah menerima informasi sebagimana di atas yang saya beri tanda/ paraf di kolom kanannya serta telah diberi kesempatan untuk bertanya/berdiskusi, dan telah memahaminya',
                'jawaban1': ttd2,
                'tipe': 'ttd',
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
                    'tipe': 'ttd',
                    'keterangan': jenis,
                    'jenis': 'persetujuan_hd',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'TTD Saksi',
                    'jawaban1': ttdB,
                    'tipe': 'ttd',
                    'keterangan': jenis,
                    'jenis': 'persetujuan_hd',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'TTD Perawat',
                    'jawaban1': ttdC,
                    'tipe': 'ttd',
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
        var nama = $('#nama_terang_hd_ttd_dokter').val();
        var sip = $('#sip_hd_ttd_dokter').val();
        var cekTtd = isCanvasBlank(ttd);

        if (pe1 && pe2 && pe3 && pe4 && pe5 && pe6 && pe7 && pe8 && pe9 && pe10 && pe11 &&
            pe12 && pe13 && pe14 && pe15 && pe16 && pe17 && pe18 && pe19 && nama != '' && !cekTtd) {

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
                'tipe': 'ttd',
                'keterangan': jenis,
                'jenis': 'hd_ttd_dokter',
                'nama_terang': nama,
                'sip':sip,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("perencanaan_hemodialisa_pasien") {
        var va1 = $('#ph1').val();
        var va2 = $('#ph2').val();
        var va3 = $('#ph3').val();
        var va41 = $('#ph4_1').val();
        var va42 = $('#ph4_2').val();
        var va5 = $('#ph5').val();
        var va6 = $('#ph6').val();
        var va7 = $('#ph7').val();
        var va8 = $('#ph8').val();
        var va9 = $('#ph9').val();
        var va10 = $('#ph10').val();
        var va11 = $('#ph11').val();
        var va12 = $('#ph12').val();
        var va13 = $('#ph13').val();
        var nama = $('#nama_terang_ph14').val();
        var sip = $('#sip_ph14').val();
        var ttd = document.getElementById("ph14");
        var cekTtd = isCanvasBlank(ttd);

        if (va1 && va2 && va3 && va5 && va6 && va7 && va8 && va9 && va10 && va11 && va12 != '' && !cekTtd) {
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
            if(va41 != ''){
                data.push({
                    'parameter': 'No Asuransi',
                    'jawaban1': va41,
                    'keterangan': jenis,
                    'jenis': 'perencanaan_hemodialisa',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if(va42 != ''){
                data.push({
                    'parameter': 'No BPJS',
                    'jawaban1': va42,
                    'keterangan': jenis,
                    'jenis': 'perencanaan_hemodialisa',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            data.push({
                'parameter': 'Perencanaan Hemodialisa',
                'jawaban1': 'Sebanyak ' + va5 + ' kali/minggu|' + 'Lama Hemodialisa ' + va6 + ' Jam ' + va7 + ' Menit|' + 'UF ' + va8 + ' Liter, Heparin ' + va9 + '|QB ' + replaceUnderLine(va10) + ', BD ' + va11,
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
                'tipe': 'ttd',
                'keterangan': jenis,
                'jenis': 'perencanaan_hemodialisa',
                'nama_terang': nama,
                'sip':sip,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("tindakan_medis" == jenis) {

        var va1 = $('#hd1').val();
        var va2 = $('#hd2').val();
        var va3 = $('#hd3').val();
        var va4 = $('#hd4').val();
        var va5 = $('#hd5').val();
        var va6 = $('#hd6').val();
        var va7 = $('#hd7').val();
        var va8 = $('#hd8').val();
        var va9 = $('#hd9').val();
        var va10 = $('#hd10').val();
        var va11 = $('#hd11').val();

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
        var sip3 = "";
        var nama4 = $('#nama_terang_ttd4').val();
        var nama5 = $('#nama_terang_ttd5').val();

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);
        var cekTtd4 = isCanvasBlank(ttd4);
        var cekTtd5 = isCanvasBlank(ttd5);

        if (va1 && va2 && va3 && va4 && persetujuan != '' && !cekTtd1 && !cekTtd2 && !cekTtd3 && !cekTtd4 && !cekTtd5) {

            // var resplacePerse = persetujuan.replace("_", " ");
            // var perse = convertSentenceCaseUp(resplacePerse);
            data.push({
                'parameter': 'pernyataan',
                'jawaban1': 'Pemberian Informasi dan Persetujuan ' + persetujuan,
                'keterangan': ket,
                'jenis': persetujuan,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal',
                'jawaban1': va1,
                'keterangan': ket,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter Penanggung Jawab Anestesi',
                'jawaban1': va2,
                'keterangan': ket,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemberi Informasi',
                'jawaban1': va3,
                'keterangan': ket,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penerima Informasi',
                'jawaban1': va4,
                'keterangan': ket,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Jenis Informasi',
                'jawaban1': 'Isi Informasi',
                'jawaban2': 'Check Informasi',
                'keterangan': ket,
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
                    'jawaban2': tdn,
                    'jawaban1': info,
                    'keterangan': ket,
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
                'jawaban1': canv1,
                'keterangan': ket,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama1,
                'sip':sip1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerima informasi sebagaimana di atas dan telah memahaminya',
                'jawaban1': canv2,
                'keterangan': ket,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama2,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'pernyataan',
                'jawaban1': 'Persetujuan Tindakan Medis',
                'keterangan': ket,
                'jenis': persetujuan,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'penyataan',
                'jawaban1': 'Yang bertanda tangan dibawah ini, Saya ' + va5 + ' ' +
                    'tanggal lahir ' + va6 + ', ' + va7 + ' dengan ini menyatakan persetujuan untuk dilakukan tindakan ' + persetujuan + ' ' +
                    'terhadap pasien Bernama ' + va9 + ' tanggal lahir ' + va10 + ', Alamat ' + va11 + '.' +
                    'Saya memahami perlunya dan manfaat tindakan tersebut sebagaimana telah dijelaskan seperti diatas ' +
                    'kepada saya termasuk resiko dan komplikasi yang timbul ' +
                    'Saya juga menyadari bahwa oleh karena itu ilmu kedokteran bukan ilmu pasti, maka keberhasilan tindakan ' +
                    'kedokteran bukan keniscayaan, tetapi tergantung kepada izin Tuhan Yang maha Esa. Tanggal ' + converterDate(new Date) + ', Jam ' + converterTime(new Date()) +' WIB',
                'keterangan': ket,
                'jenis': persetujuan,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD yang menyatakan',
                'jawaban1': canv3,
                'keterangan': ket,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang': nama3,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saksi I',
                'jawaban1': canv4,
                'keterangan': ket,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama4,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saksi II',
                'jawaban1': canv5,
                'keterangan': ket,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama5,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            $('#save_hd_' + jenis).hide();
            $('#load_hd_' + jenis).show();
            dwr.engine.setAsync(true);
            HemodialisaAction.saveHemodialisa(result, pasienData, {
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
            });
        }
    } else {
        $('#warning_hd_' + jenis).show().fadeOut(5000);
        $('#msg_hd_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-hd-' + jenis).scrollTop(0);
    }
}

function detailMonHD(jenis) {
    if(!cekSession()){
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
                        if ("ttd" == item.tipe) {
                            if("tindakan_medis_hd" == item.keterangan){
                                body += '<tr>' +
                                    '<td colspan="2">' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban1 + '" style="height: 100px">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerang)+'</p>' +
                                    '<p style="margin-top: -10px">'+cekItemIsNull(item.sip)+'</p>' +
                                    '</td>' +
                                    '</tr>';
                            }else{
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + jwb + '" style="width: 100px">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerang)+'</p>' +
                                    '<p style="margin-top: -10px">'+cekItemIsNull(item.sip)+'</p>' +
                                    '</td>' +
                                    '</tr>';
                            }
                        } else if ("intervensi" == item.keterangan || "diagnosa" == item.keterangan || "Dosis Maintenance" == item.parameter) {
                            var li = "";
                            var isi = jwb.split("|");
                            if("intervensi" == item.keterangan){
                                var jaw2 = jwb2.split("|");
                                $.each(jaw2, function (i, item) {
                                    li += '<li>' + item + '</li>';
                                });
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '</td>' +
                                    '<td>' + '<ul style="margin-left: 10px">' + li + '</ul>' + '</td>' +
                                    '</tr>';
                            }else{
                                $.each(isi, function (i, item) {
                                    li += '<li>' + item + '</li>';
                                });
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + '<ul style="margin-left: 10px">' + li + '</ul>' + '</td>' +
                                    '</tr>';
                            }
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
                        } else if ("gambar" == item.tipe) {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + '<img src="' + jwb + '" style="width: 100px">' + '</td>' +
                                '</tr>';
                        } else if("tindakan_medis_hd" == item.keterangan){
                            if ("colspan" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="3">' + item.jawaban1 + '</td>' +
                                    '</tr>';
                            } else if ("info" == item.tipe) {
                                body += '<tr>' +
                                    '<td width="25%">' + item.parameter + '</td>' +
                                    '<td >' + item.jawaban1 + '</td>' +
                                    '<td width="20%" align="center">' + cekIcons(item.jawaban2) + '</td>' +
                                    '</tr>';
                            } else if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="2">' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban1 + '" style="height: 80px">' + '</td>' +
                                    '</tr>';
                            } else if ("bold" == item.tipe) {
                                body += '<tr style="font-weight: bold">' +
                                    '<td width="25%">' + item.parameter + '</td>' +
                                    '<td >' + item.jawaban1 + '</td>' +
                                    '<td width="20%" align="center">' + item.jawaban2 + '</td>' +
                                    '</tr>';
                            }else {
                                body += '<tr>' +
                                    '<td width="30%">' + item.parameter + '</td>' +
                                    '<td colspan="2">' + item.jawaban1 + '</td>' +
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

function showKetHD(val, id) {
    if (val == "Lain-Lain") {
        $('#form-'+id).show();
    } else {
        $('#form-'+id).hide();
    }
}

function getListMonTransfusiDarah() {
    if(!cekSession()){
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
                        '<td align="center">' + set(item.menggigilMengancam) + '</td>' +
                        '<td align="center">' + set(item.gelisahMengancam) + '</td>' +
                        '<td align="center">' + set(item.peningkatanDetakJantungMengancam) + '</td>' +
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
    if(!cekSession()){
        var data = "";
        var dataPasien = "";
        dataPasien = {
            'no_checkup' : noCheckup,
            'id_detail_checkup' : idDetailCheckup,
            'id_pasien' : idPasien,
            'id_rm' : tempidRm
        }
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
        var reaksi = $('#ct_rekasi').val();

        if (va1 && va2 && va3 && va4 && va5 && reaksi != '') {

            data = {
                'id_detail_checkup': idDetailCheckup,
                'waktu': va1,
                'tekanan_darah': replaceUnderLine(va2),
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
            var pasienData = JSON.stringify(dataPasien);
            $('#save_hd_catatan_tranfusi').hide();
            $('#load_hd_catatan_tranfusi').show();
            dwr.engine.setAsync(true);
            MonitoringTransfusiDarahAction.saveMonitoringTransfusiDarah(result, pasienData, {
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
}

function saveObservasi(jenis, ket){
    var cek = "";
    var data = [];
    var dataPasien = "";
    dataPasien = {
        'no_checkup' : noCheckup,
        'id_detail_checkup' : idDetailCheckup,
        'id_pasien' : idPasien,
        'id_rm' : tempidRm
    }
    if("observasi_tindakan" == jenis){
        var va1 = $('#obs1').val();
        var va2 = $('#obs2').val();
        var va3 = $('#obs3').val();
        var va4 = $('#obs4').val();
        var va5 = $('#obs5').val();
        var va6 = $('#obs6').val();
        var va7 = $('#obs7').val();
        var va8 = $('#obs8').val();
        var va9 = $('#obs9').val();
        var va10 = $('#obs10').val();
        var va11 = $('#obs11').val();
        var va12 = $('#obs12').val();
        var va13 = $('#obs13').val();
        var va14 = document.getElementById('obs14');
        var nama = $('#nama_terang_obs14').val();
        var sip = $('#sip_obs14').val();
        if(va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 &&
            va11 && va12 && va13 && va14 && nama && sip != ''){
            var ttd1 = convertToDataURL(va14);
            data.push({
                'waktu': va1+' '+va2,
                'observasi': va3,
                'qb': replaceUnderLine(va4),
                'tensi': replaceUnderLine(va5),
                'nadi': va6,
                'suhu': va7,
                'rr': va8,
                'cairan_masuk': va9,
                'makan_minum': va10,
                'muntah': va11,
                'uf': va12,
                'keterangan': va13,
                'jenis': jenis,
                'ttd': ttd1,
                'nama_terang':nama,
                'sip':sip,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
        if (cek) {
            if(!cekSession()){
                var result = JSON.stringify(data);
                var pasienData = JSON.stringify(dataPasien);
                $('#save_hd_' + jenis).hide();
                $('#load_hd_' + jenis).show();
                dwr.engine.setAsync(true);
                HemodialisaAction.saveObservasi(result, pasienData, {
                    callback: function (res) {
                        if (res.status == "success") {
                            $('#save_hd_' + jenis).show();
                            $('#load_hd_' + jenis).hide();
                            $('#modal-hd-' + jenis).modal('hide');
                            $('#warning_hd_' + ket).show().fadeOut(5000);
                            $('#msg_hd_' + ket).text("Berhasil menambahkan data hd...");
                            $('#modal-hd-' + jenis).scrollTop(0);
                            getListRekamMedis('rawat_jalan', tipePelayanan, idDetailCheckup);
                        } else {
                            $('#save_hd_' + jenis).show();
                            $('#load_hd_' + jenis).hide();
                            $('#warning_hd_' + jenis).show().fadeOut(5000);
                            $('#msg_hd_' + jenis).text(res.msg);
                            $('#modal-hd-' + jenis).scrollTop(0);
                        }
                    }
                });
            }
        } else {
            $('#warning_hd_' + jenis).show().fadeOut(5000);
            $('#msg_hd_' + jenis).text("Silahkan cek kembali data inputan anda...!");
            $('#modal-hd-' + jenis).scrollTop(0);
        }

    }
}

function detailObservasi(jenis){
    if(!cekSession()){
        if (jenis != '') {
            var head = "";
            var body = "";
            var totalSkor = 0;
            var first = "";
            var last = "";
            var tgl = "";
            var cekData = false;
            HemodialisaAction.getListObservasi(idDetailCheckup, jenis, function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        body += '<tr>' +
                            '<td>' + item.waktu + '</td>' +
                            '<td>' + item.observasi + '</td>' +
                            '<td>' + item.qb + '</td>' +
                            '<td>' + item.tensi + '</td>' +
                            '<td>' + item.nadi + '</td>' +
                            '<td>' + item.suhu + '</td>' +
                            '<td>' + item.rr + '</td>' +
                            '<td>' + item.cairanMasuk + '</td>' +
                            '<td>' + item.makanMinum + '</td>' +
                            '<td>' + item.muntah + '</td>' +
                            '<td>' + item.uf + '</td>' +
                            '<td>' + item.keterangan + '</td>' +
                            '<td>' +'<img src="'+item.ttd+'" style="height: 60px;">' +
                            '<p>'+cekItemIsNull(item.namaTerang)+'</p>'+
                            '<p>'+cekItemIsNull(item.sip)+'</p>'+
                            '</td>' +
                            '<td align="center">'+'<i onclick="conObs(\''+jenis+'\', \''+item.idObservasiTindakanHd+'\')" class="fa fa-trash hvr-grow" style="color: red; font-size: 18px"></i>'+'</td>'+
                            '</tr>';
                        cekData = true;
                        tgl = item.createdDate;
                    });
                } else {
                    body = '<tr>' +
                        '<td>Data belum ada</td>' +
                        '</tr>';
                }
                if (cekData) {
                    head = '<tr style="font-weight: bold">' +
                        '<td>Waktu</td>' +
                        '<td>Obs</td>' +
                        '<td>QB</td>' +
                        '<td>Tensi</td>' +
                        '<td>Nadi</td>' +
                        '<td>Suhu</td>' +
                        '<td>RR</td>' +
                        '<td>CM</td>' +
                        '<td>MM</td>' +
                        '<td>M</td>' +
                        '<td>UF</td>' +
                        '<td>Keterangan</td>' +
                        '<td width="15%">Paraf</td>' +
                        '<td align="center">Action</td>' +
                        '</tr>';
                }
                var table = '<table style="font-size: 12px" class="table table-bordered">' +
                    '<thead>' + head + '</thead>' +
                    '<tbody>' + first + body + last + '</tbody>' +
                    '</table>';

                var newRow = $('<tr id="del_hd_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                newRow.insertAfter($('table').find('#row_hd_' + jenis));
                var url = contextPath + '/pages/images/minus-allnew.png';
                $('#btn_hd_' + jenis).attr('src', url);
                $('#btn_hd_' + jenis).attr('onclick', 'delRowObservasi(\'' + jenis + '\')');
            });
        }
    }
}

function delRowObservasi(id) {
    $('#del_hd_' + id).remove();
    var url = contextPath + '/pages/images/icons8-add-list-25.png';
    $('#btn_hd_' + id).attr('src', url);
    $('#btn_hd_' + id).attr('onclick', 'detailObservasi(\'' + id + '\')');
}

function showCheck(id){
    var cek = $('#'+id).is(':checked');
    if(cek){
        $('#form-hd_'+id).show();
    }else{
        $('#form-hd_'+id).hide();
    }
}

function selectReaksi(val){
    $('[name=ct7], [name=ct8], [name=ct9], [name=ct10], [name=ct11], [name=ct12], [name=ct13], [name=ct14], [name=ct15], [name=ct16], [name=ct17],[name=ct18],[name=ct19],[name=ct20], [name=ct21]').prop('checked', false);
    if(val == "Tidak Ada Reaksi"){
        $('#form_tidak_ada').show();
        $('#form_ringan').hide();
        $('#form_berat').hide();
        $('#form_mengancam').hide();
    }else if(val == "Reaksi Ringan"){
        $('#form_tidak_ada').hide();
        $('#form_ringan').show();
        $('#form_berat').hide();
        $('#form_mengancam').hide();
    }else if(val == "Reaksi Sedang Berat"){
        $('#form_tidak_ada').hide();
        $('#form_ringan').hide();
        $('#form_berat').show();
        $('#form_mengancam').hide();
    }else if (val == "Reaksi Yang Mengancam Jiwa") {
        $('#form_tidak_ada').hide();
        $('#form_ringan').hide();
        $('#form_berat').hide();
        $('#form_mengancam').show();
    }else{
        $('#form_tidak_ada').hide();
        $('#form_ringan').hide();
        $('#form_berat').hide();
        $('#form_mengancam').hide();
    }
}

function conHD(jenis, ket){
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show:true, backdrop:'static'});
    $('#save_con_rm').attr('onclick', 'delHD(\''+jenis+'\', \''+ket+'\')');
}

function conObs(jenis, id){
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show:true, backdrop:'static'});
    $('#save_con_rm').attr('onclick', 'delObs(\''+jenis+'\', \''+id+'\')');
}

function delObs(jenis, id) {
    $('#modal-confirm-rm').modal('hide');
    if(!cekSession()){
        startSpin('del_'+jenis);
        dwr.engine.setAsync(true);
        HemodialisaAction.deleteObservasi(id, {
            callback: function (res) {
                if (res.status == "success") {
                    stopSpin('del_'+jenis);
                    $('#warning_hd_monitoring_hd').show().fadeOut(5000);
                    $('#msg_hd_monitoring_hd').text("Berhasil menghapus data...");
                } else {
                    stopSpin('del_'+jenis);
                    $('#modal-hd-monitoring_hd').scrollTop(0);
                    $('#modal_warning').show().fadeOut(5000);
                    $('#msg_warning').text(res.msg);
                }
            }
        });
    }
}

function delHD(jenis, ket) {
    $('#modal-confirm-rm').modal('hide');
    if(!cekSession()){
        var dataPasien = {
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'id_pasien': idPasien,
            'id_rm': tempidRm
        }
        var result = JSON.stringify(dataPasien);
        startSpin('delete_'+jenis);
        dwr.engine.setAsync(true);
        HemodialisaAction.saveDelete(idDetailCheckup, jenis, result, {
            callback: function (res) {
                if (res.status == "success") {
                    stopSpin('delete_'+jenis);
                    $('#modal-hd-'+ket).scrollTop(0);
                    $('#warning_hd_' + ket).show().fadeOut(5000);
                    $('#msg_hd_' + ket).text("Berhasil menghapus data...");
                } else {
                    stopSpin('delete_'+jenis);
                    $('#modal-hd-'+ket).scrollTop(0);
                    $('#warn_'+ket).show().fadeOut(5000);
                    $('#msg_'+ket).text(res.msg);
                }
            }
        });
    }
}