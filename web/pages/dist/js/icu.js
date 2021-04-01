function showModalICU(jenis, idRM, isSetIdRM) {
    if (isSetIdRM == "Y") {
        tempidRm = idRM;
    }
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }

    if ("keseimbangan_icu" == jenis) {
        setInputan(jenis);
    }

    if ("checklist_kriteria" == jenis) {
        setInputan(jenis);
    }

    if("tindakan_icu" == jenis){
        selectOptionTM('icu', jenis);
        $('#form-'+jenis).hide();
    }
    $('#modal-icu-' + jenis).modal({show: true, backdrop: 'static'});
    setDataPasien();
}

function setHeadRespirasi() {
    RespirasiAction.getListDetail(idDetailCheckup, "respirasi", function (res) {
        if (res.length > 0) {
            var valueRes = res[0];
            $('#id13').val(valueRes.jenisNkRmNrm).trigger('change').attr('disabled', 'disabled');
            $('#id14').val(valueRes.jenisTPieceJRise).trigger('change').attr('disabled', 'disabled');
            $('#id17').val(valueRes.jenisPeepCpapEt).trigger('change').attr('disabled', 'disabled');
            $('#id22').val(valueRes.jenisPSupportPAsb).trigger('change').attr('disabled', 'disabled');
            $('#id24').val(valueRes.jenisPInsPCon).trigger('change').attr('disabled', 'disabled');
            $('#id29').val(valueRes.jenisFioKon).trigger('change').attr('disabled', 'disabled');
            $('#id31').val(valueRes.jenisUkuranKedalamaanEtt).trigger('change').attr('disabled', 'disabled');
        }
    });
}

function saveICU(jenis, ket) {
    var data = [];
    var cek = false;
    var tgl = $('#tgl_' + jenis).val();
    var jam = $('#jam_' + jenis).val();
    var dataPasien = "";

    dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }

    if ("identitas" == jenis) {

        var va1 = $('#id1').val();
        var va2 = $('#id2').val();
        var va3 = $('#id3').val();
        var va4 = $('#id4').val();
        var va5 = $('#id5').val();
        var va6 = $('#id6').val();
        var va7 = $('#id7').val();
        var va8 = $('#id8').val();
        var va9 = $('#id9').val();
        var va10 = $('#id10').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 != '') {

            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1 + ' ' + va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jaminann',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Asal Masuk',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tgl Masuk',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hari Perawatan',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan/ Tinggi Badan',
                'jawaban': va7 + 'kg / ' + va8 + ' cm',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'DPJP ICU',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perawat Jaga',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("alat_infasive" == jenis) {

        var va1 = $('#ai1').val();
        var va2 = $('#ai2').val();
        var va3 = $('#ai3').val();
        var va4 = "";
        var v4 = $('#ai4').val();
        if(v4 != null){
            if (v4.length > 0) {
                $.each(v4, function (i, item) {
                    if (va4 != '') {
                        va4 = va4 + '|' + item;
                    } else {
                        va4 = item;
                    }
                });
            }
        }
        if (va1 && va2 && va3 && va4 != '') {

            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1 + ' ' + va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alat',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("resiko_jatuh" == jenis) {

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

    if ("decobitus" == jenis) {

        var va = $('[name=db]');
        var va1 = "";
        $.each(va, function (i, item) {
            if (item.checked) {
                if (va1 != '') {
                    va1 = va1 + '|' + item.value;
                } else {
                    va1 = item.value;
                }
            }
        });

        if (va1 != '') {

            data.push({
                'parameter': 'Derajat Decubitus',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("nyeri" == jenis) {

        var nyeri = $('[name=radio_aud_nyeri]:checked').val();
        var skala = $('[name=radio_aud_skala]:checked').val();
        var lokasi = $('#yer_lokasi').val();
        var skal = $('#skala_nyeri').val();
        var canvasArea = document.getElementById('choice_emoji');
        var cvs = isCanvasBlank(canvasArea);

        if (nyeri && skala != undefined && lokasi && skal != '') {

            data.push({
                'parameter': 'Apakah terdapat keluhan nyeri',
                'jawaban': nyeri,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban': lokasi,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis',
                'jawaban': skala,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skala',
                'jawaban': skal,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            if (!cvs) {
                var canv = canvasArea.toDataURL("image/png"),
                    canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");
                data.push({
                    'parameter': 'Scala Paint Nyeri',
                    'jawaban': canv,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'gambar',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            cek = true;
        }
    }

    if ("gcs" == jenis) {

        var gcs1 = $('#gc1').val();
        var gcs2 = $('#gc2').val();
        var gcs3 = $('#gc3').val();

        if (gcs1 && gcs2 && gcs3 != '') {

            var isi1 = gcs1.split("|")[0];
            var isi2 = gcs2.split("|")[0];
            var isi3 = gcs3.split("|")[0];

            var skor1 = gcs1.split("|")[1];
            var skor2 = gcs2.split("|")[1];
            var skor3 = gcs3.split("|")[1];

            data.push({
                'parameter': 'E = Eye (membuka mata)',
                'jawaban': isi1,
                'keterangan': jenis,
                'jenis': ket,
                'skor': skor1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'V = Verbal (komunikasi)',
                'jawaban': isi2,
                'keterangan': jenis,
                'jenis': ket,
                'skor': skor2,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'M = Motorik (gerakan ekstimitas atas)',
                'jawaban': isi3,
                'keterangan': jenis,
                'jenis': ket,
                'skor': skor3,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("checklist_kriteria" == jenis) {

        var kriteria1 = $('[name=kriteria1]');
        var kriteria2 = $('[name=kriteria2]');
        var parameter = $('[name=parameter]');
        var dpjp = document.getElementById("ttd_dpjp");
        var pasien = document.getElementById("ttd_pasien");

        var cekTtd1 = isCanvasBlank(dpjp);
        var cekTtd2 = isCanvasBlank(pasien);
        var nama1 = $('#nama_terang_dokter').val();
        var sip1 = $('#sip_dokter').val();
        var nama2 = $('#nama_terang_pasien').val();

        $.each(parameter, function (i, item) {

            if (item.value != '') {

                var isi = "";
                var skor = "0";

                var jwb1 = kriteria1[i];
                var jwb2 = kriteria2[i];

                if (jwb1.checked) {
                    var spl = jwb1.value.split("|");
                    isi = spl[0];
                    skor = spl[1];
                } else if (jwb2.checked) {
                    var spl = jwb2.value.split("|");
                    isi = spl[0];
                    skor = spl[1];
                }

                data.push({
                    'parameter': item.value,
                    'jawaban': isi,
                    'keterangan': jenis,
                    'jenis': ket,
                    'skor': skor,
                    'id_detail_checkup': idDetailCheckup
                });
            }
        });

        if (data.length > 0 && !cekTtd1 && !cekTtd2 && nama1 && nama2 && sip1 != '') {

            var canv1 = dpjp.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv2 = pasien.toDataURL("image/png"),
                canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD DPJP',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama1,
                'sip': sip1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Pasien/Keluarga',
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

    if ("pengkajian_medik" == jenis) {
        var va1 = $('#01pm').val();
        var va2 = $('#02pm').val();
        var va3 = $('#03pm').val();

        var va5 = "";
        var va6 = "";
        var va7 = $('[name=pm3]:checked').val();
        var va8 = "";
        var va9 = "";
        var va10 = "";
        var va11 = $('#pm7').val();
        var va12 = $('#pm8').val();

        var v5 = $('[name=pm1]');
        var v6 = $('[name=pm2]');
        var v8 = $('[name=pm4]');
        var v9 = $('[name=pm5]');
        var v10 = $('[name=pm6]');

        var dokter = document.getElementById("ttd_dokter");
        var nama = $('#nama_terang_dokter').val();
        var sip = $('#sip_dokter').val();

        var cekTtd1 = isCanvasBlank(dokter);

        $.each(v5, function (i, item) {
            if (item.checked) {
                if (va5 != '') {
                    va5 = va5 + ', ' + item.value;
                } else {
                    va5 = item.value;
                }
            }
        });

        $.each(v6, function (i, item) {
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
                if (va8 != '') {
                    va8 = va8 + ', ' + item.value;
                } else {
                    va8 = item.value;
                }
            }
        });

        $.each(v9, function (i, item) {
            if (item.checked) {
                if (va9 != '') {
                    va9 = va9 + ', ' + item.value;
                } else {
                    va9 = item.value;
                }
            }
        });

        $.each(v10, function (i, item) {
            if (item.checked) {
                if (va10 != '') {
                    va10 = va10 + ', ' + item.value;
                } else {
                    va10 = item.value;
                }
            }
        });

        if (va1 && va2 && va3 && va5 && va6 && va8 && va9 && va10 && va11 && va12 && nama && sip != '' && !cekTtd1 && va7 != undefined) {
            data.push({
                'parameter': 'Tanggal Jam',
                'jawaban': tgl + ' ' + jam,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kelas',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Medik',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'DPJP',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Gejala - Gejala',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'a. Kegawatan Pernafasan',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'b. Kehilangan otot tonus',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'c. Nyeri',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'd. Pelambatan sirkulasi',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'e. Pencernaan',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Faktor-faktor yang ,meningkatkan gejala fisik',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Asesment',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '4. Perencanaan',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = dokter.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD Dokter',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama,
                'sip': sip,
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;

        }

    }

    if ("pengkajian_keperawatan" == jenis) {

        var va1 = "";
        var va3 = "";
        var va4 = "";
        var va5 = "";
        var va6 = "";
        var va7 = "";
        var va8 = "";
        var va9 = "";
        var va10 = "";
        var va11 = "";

        var v1 = $('[name=pk1]:checked').val();

        var v2 = $('[name=pk02]:checked').val();
        var v3 = $('[name=pk03]:checked').val();
        var v4 = $('[name=pk04]:checked').val();

        var v02 = $('[name=pk2]:checked').val();
        var v03 = $('[name=pk3]:checked').val();
        var v04 = $('[name=pk4]:checked').val();

        var v5 = $('[name=pk5]:checked').val();
        var v6 = $('[name=pk6]:checked').val();

        var v7 = $('[name=pk7]:checked').val();
        var v8 = $('[name=pk8]:checked').val();
        var v9 = $('[name=pk9]:checked').val();

        var v10 = $('[name=pk10]');
        var v11 = $('[name=pk11]');


        var dokter = document.getElementById("ttd_perawat");
        var nama = $('#nama_terang_perawat').val();

        var cekTtd1 = isCanvasBlank(dokter);

        if (v1 != undefined) {
            var ket = $('#ket_pk11').val();
            if ("Ya" == v1) {
                va1 = v1 + ', ' + ket;
            } else {
                va1 = v1;
            }
        }

        var isiVa31 = "";
        var isiVa32 = "";
        var isiVa33 = "";

        if (v02 != undefined) {
            var ket = $('#ket_pk22').val();
            if ("Ya" == v1) {
                isiVa31 = v02 + ', ' + ket;
            } else {
                isiVa31 = v02;
            }
        }

        if (v03 != undefined) {
            var ket = $('#ket_pk32').val();
            if ("Ya" == v03) {
                isiVa31 = v03 + ', ' + ket;
            } else {
                isiVa31 = v03;
            }
        }

        if (v04 != undefined) {
            var ket = $('#ket_pk42').val();
            if ("Ya" == v1) {
                isiVa33 = v04 + ', ' + ket;
            } else {
                isiVa33 = v04;
            }
        }

        if (v2 != undefined) {
            va3 = v2 + ', ' + isiVa31 + '. ';
        }
        if (v3 != undefined) {
            va3 = va3 + v3 + ', ' + isiVa32 + '. ';
        }
        if (v4 != undefined) {
            va3 = va3 + v3 + ', ' + isiVa33 + '. ';
        }

        if (v5 != undefined) {

            var ket1 = $('#ket_pk51').val();
            var ket2 = $('#ket_pk52').val();
            var ket3 = $('#ket_pk53').val();
            var ket4 = $('#ket_pk54').val();
            var ket5 = $('#ket_pk55').val();

            if ("Ya" == v1) {
                va5 = v5 + ', Nama ' + ket1 + ', Dimana ' + ket2 + ', Hubungan dengan pasien ' + ket3 + ', No. Telepon/HP ' + ket4;
            } else {
                va5 = v5;
            }
        }

        $.each(v10, function (i, item) {
            if (item.checked) {
                if (va10 != '') {
                    va10 = va10 + ', ' + item.value;
                } else {
                    va10 = item.value;
                }
            }
        });

        $.each(v11, function (i, item) {
            if (item.checked) {
                if (va11 != '') {
                    va11 = va11 + ', ' + item.value;
                } else {
                    va11 = item.value;
                }
            }
        });


        if (jam && tgl && va1 && va3 && va5 && nama != '' && !cekTtd1) {

            data.push({
                'parameter': 'Tanggal Jam',
                'jawaban': tgl + ' ' + jam,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Orental spiritual pasien dan keluarga',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Apakah perlu pelayanan spiritual',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Urusan dan kebutuhan spiritual dan keluarga seperti putus asa, penderitaan, rasa bersalah atau pengampunan',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Status psikososial pasien dan keluarga',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'a. Apakah ada orang yang ingin dihubungi saat ini?',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'b. Bagaimana rencana keperawatan selajutnya?',
                'jawaban': v6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            if (v7 != undefined) {
                data.push({
                    'parameter': 'Apakah lingkungan rumah sudah disiapkan',
                    'jawaban': v7,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if (v8 != undefined) {
                data.push({
                    'parameter': 'Jika ya, apakah anda mampu merawat pasien dirumah',
                    'jawaban': v8,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if (v9 != undefined) {
                data.push({
                    'parameter': 'Jika tidak, apakah perlu difasilitasi oleh rumah sakit',
                    'jawaban': v9,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }

            data.push({
                'parameter': 'c. Reaksi pasien atas penyakitnya?',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'd. Reaksi keluarga atas penyakitnya?',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = dokter.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama,
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }

    }

    if ("terminal" == jenis) {

        var va1 = $('#ter1').val();
        var va2 = $('#ter2').val();
        var va3 = $('#ter3').val();
        var va4 = $('[name=ter4]:checked').val();
        var va5 = $('[name=ter5]:checked').val();
        var va6 = $('#ter06').val();
        var va7 = $('#ter07').val();
        var va8 = $('#ter08').val();
        var va9 = $('#ter09').val();
        var va10 = $('#ter010').val();
        var va11 = $('#ter011').val();
        var va12 = $('#ter012').val();
        var va13 = $('#ter013').val();
        var va14 = $('#ter014').val();
        var va15 = $('#ter015').val();
        var va16 = $('[name=ter6]:checked').val();
        var va17 = $('[name=ter7]:checked').val();
        var va18 = $('[name=ter8]:checked').val();
        var va19 = $('[name=ter9]:checked').val();
        var va20 = $('[name=ter10]:checked').val();
        var va21 = $('[name=ter11]:checked').val();
        var va22 = $('[name=ter12]:checked').val();
        var va23 = $('[name=ter13]:checked').val();
        var va24 = "";
        var temp24 = $('[name=ter14]');
        var va25 = $('[name=ter014]:checked').val();
        var va26 = $('[name=ter15]:checked').val();
        var va27 = $('[name=ter16]:checked').val();
        var va28 = $('[name=ter17]:checked').val();
        var va29 = $('[name=ter18]:checked').val();
        var va30 = $('#ter19').val();
        var va31 = $('#ter20').val();
        var petugas = $('#nama_terang_ter21').val();

        var dokter = document.getElementById("ter21");

        var cekTtd1 = isCanvasBlank(dokter);

        $.each(temp24, function (i, item) {
            if (item.checked) {
                if (va24 != '') {
                    va24 = va24 + ', ' + item.value;
                } else {
                    va24 = item.value;
                }
            }
        });

        if (petugas && va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 != '' && !cekTtd1) {

            data.push({
                'parameter': 'Keluarga Terdekat',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hubungan',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'No Telp',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Gelang Identifikasi',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Persetujuan Umum',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Pasien',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alergi Pasien',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tensi',
                'jawaban': va8 + ', Nadi ' + va9 + ', Suhu ' + va10 + ', RR ' + va11 + ', BB ' + va12 + ', TB ' + va13 + ', GCS ' + va14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnesa',
                'jawaban': va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alat Bantu yang digunakan',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Oksigen',
                'jawaban': va16,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ventilator',
                'jawaban': va17,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Monitor Pasien',
                'jawaban': va18,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alat bantu yang lain',
                'jawaban': va19,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Asesmen Neurologi',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            if (va20 != undefined) {
                data.push({
                    'parameter': 'Relaksasi Otot',
                    'jawaban': va20,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if (va21 != undefined) {
                data.push({
                    'parameter': 'Mata / Refrek cahaya',
                    'jawaban': va21,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if (va22 != undefined) {
                data.push({
                    'parameter': 'Berbicara',
                    'jawaban': va22,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if (va23 != undefined) {
                data.push({
                    'parameter': 'Kenjang',
                    'jawaban': va23,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }

            data.push({
                'parameter': 'Gastrointestinal',
                'jawaban': va24,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kardio Vaskuler',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': va25,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ritme',
                'jawaban': va26,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Respirasi',
                'jawaban': va27,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Akral',
                'jawaban': va28,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saluran Kencing',
                'jawaban': va29,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tipe Kateter',
                'jawaban': va30,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Kateter',
                'jawaban': va31,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = dokter.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': petugas,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("add_tindakan_icu" == jenis) {
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

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);
        var cekTtd4 = isCanvasBlank(ttd4);
        var cekTtd5 = isCanvasBlank(ttd5);

        if (nama1 && nama2 && nama3 && nama4 && nama5 && sip1 && sip3 && va1 && va2 && va3 && va4 && persetujuan != '' && !cekTtd1 && !cekTtd2 && !cekTtd3 && !cekTtd4 && !cekTtd5) {

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
                'parameter': 'Dokter Penanggung Jawab Anestesi',
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
                'parameter': 'penyataan',
                'jawaban': 'Yang bertanda tangan dibawah ini, Saya ' + va5 + ' ' +
                    'tanggal lahir ' + va6 + ', ' + va7 + ' dengan ini menyatakan persetujuan untuk dilakukan tindakan ' + persetujuan + ' ' +
                    'terhadap pasien Bernama ' + va9 + ' tanggal lahir ' + va10 + ', Alamat ' + va11 + '.' +
                    'Saya memahami perlunya dan manfaat tindakan tersebut sebagaimana telah dijelaskan seperti diatas ' +
                    'kepada saya termasuk resiko dan komplikasi yang timbul ' +
                    'Saya juga menyadari bahwa oleh karena itu ilmu kedokteran bukan ilmu pasti, maka keberhasilan tindakan ' +
                    'kedokteran bukan keniscayaan, tetapi tergantung kepada izin Tuhan Yang maha Esa. Tanggal ' + converterDate(new Date) + ', Jam ' + converterTime(new Date()),
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
                'sip':sip3,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saksi I',
                'jawaban': canv4,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama4,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saksi II',
                'jawaban': canv5,
                'keterangan': jenis,
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
            $('#save_icu_' + jenis).hide();
            $('#load_icu_' + jenis).show();
            dwr.engine.setAsync(true);
            AsesmenIcuAction.save(result, pasienData, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_icu_' + jenis).show();
                        $('#load_icu_' + jenis).hide();
                        $('#modal-icu-' + jenis).modal('hide');
                        $('#warning_icu_' + ket).show().fadeOut(5000);
                        $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                        $('#modal-icu-' + jenis).scrollTop(0);
                    } else {
                        $('#save_icu_' + jenis).show();
                        $('#load_icu_' + jenis).hide();
                        $('#warning_icu_' + jenis).show().fadeOut(5000);
                        $('#msg_icu_' + jenis).text(res.msg);
                        $('#modal-icu-' + jenis).scrollTop(0);
                    }
                }
            });
        }
    } else {
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-icu-' + jenis).scrollTop(0);
    }
}

function detailICU(jenis) {
    if(!cekSession()){
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

            AsesmenIcuAction.getListDetail(idDetailCheckup, jenis, function (res) {

                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        var jwb = "";
                        if (item.jawaban != '') {
                            jwb = item.jawaban;
                        }

                        if ("Alat" == item.parameter) {
                            var val = jwb.split("|");
                            var li = "";
                            $.each(val, function (i, item) {
                                li += '<li>' + item + '</li>'
                            });
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + '<ul style="margin-left: 15px">' + li + '</ul>' + '</td>' +
                                '</tr>';

                        } else if ("tindakan_icu" == jenis) {
                            if ("colspan" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="3">' + jwb + '</td>' +
                                    '</tr>';
                            } else if ("info" == item.tipe) {
                                body += '<tr>' +
                                    '<td width="25%">' + item.parameter + '</td>' +
                                    '<td >' + item.informasi + '</td>' +
                                    '<td width="20%" align="center">' + cekIconsIsNotNull(jwb) + '</td>' +
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
                                    '<td >' + item.informasi + '</td>' +
                                    '<td width="20%" align="center">' + cekItemIsNull(jwb) + '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="30%">' + item.parameter + '</td>' +
                                    '<td colspan="2">' + jwb + '</td>' +
                                    '</tr>';
                            }
                        }else if("add_tindakan_icu" == jenis){
                            if ("colspan" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="3">' + cekItemIsNull(jwb) + '</td>' +
                                    '</tr>';
                            } else if ("info" == item.tipe) {
                                body += '<tr>' +
                                    '<td width="30%">' + cekItemIsNull(item.parameter) + '</td>' +
                                    '<td >' + cekItemIsNull(jwb) + '</td>' +
                                    '<td width="20%" align="center">' + cekIcons(item.informasi) + '</td>' +
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
                                    '<td >' + cekItemIsNull(jwb) + '</td>' +
                                    '<td width="20%" align="center">' + cekItemIsNull(item.informasi) + '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="30%">' + item.parameter + '</td>' +
                                    '<td colspan="2">' + jwb + '</td>' +
                                    '</tr>';
                            }
                        } else if (item.score != null) {
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + item.jawaban + '</td>' +
                                '<td width="10%" align="center">' + item.score + '</td>' +
                                '</tr>';
                            totalSkor = parseInt(totalSkor) + parseInt(item.score);
                            cekSkor = true;
                        } else if ("gambar" == item.tipe) {
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + '<img src="' + item.jawaban + '" style="height: 80px">' + '</td>' +
                                '</tr>';
                        } else {
                            if ("checklist_kriteria" == jenis) {
                                if ("ttd" == item.tipe) {
                                    forTTD += '<tr>' +
                                        '<td width="40%">' + item.parameter + '</td>' +
                                        '<td colspan="2">' + '<img src="' + item.jawaban + '" style="height: 80px">' +
                                        '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerang)+'</p>' +
                                        '<p style="margin-top: -10px">'+cekItemIsNull(item.sip)+'</p>' +
                                        '</td>' +
                                        '</tr>';
                                }
                            } else {
                                if ("ttd" == item.tipe) {
                                    forTTD += '<tr>' +
                                        '<td width="40%">' + item.parameter + '</td>' +
                                        '<td colspan="2">' + '<img src="' + item.jawaban + '" style="height: 80px">' +
                                        '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerang)+'</p>' +
                                        '<p style="margin-top: -10px">'+cekItemIsNull(item.sip)+'</p>' +
                                        '</td>' +
                                        '</tr>';
                                } else {
                                    body += '<tr>' +
                                        '<td width="40%">' + item.parameter + '</td>' +
                                        '<td>' + jwb + '</td>' +
                                        '</tr>';
                                }
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

                var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                newRow.insertAfter($('table').find('#row_icu_' + jenis));
                var url = contextPath + '/pages/images/minus-allnew.png';
                $('#btn_icu_' + jenis).attr('src', url);
                $('#btn_icu_' + jenis).attr('onclick', 'delRowICU(\'' + jenis + '\')');
            });
        }
    }
}

function delRowICU(id) {
    $('#del_icu_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_icu_' + id).attr('src', url);
    $('#btn_icu_' + id).attr('onclick', 'detailICU(\'' + id + '\')');
}

function saveHemodinamika(jenis, ket) {
    var dataPasien = "";
    dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var va1 = $('#hemo1').val();
    var va2 = $('#hemo2').val();
    var va3 = $('#hemo3').val();
    var va4 = $('#hemo4').val();
    var va5 = $('#hemo5').val();
    var va6 = $('#hemo6').val();
    var va7 = $('#hemo7').val();
    var va8 = $('#hemo8').val();
    var va9 = $('#hemo9').val();
    var va10 = $('#hemo10').val();
    var va11 = $('#hemo11').val();
    var data = "";

    if (va1 != '') {
        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1,
            'tensi': va2,
            'st': va3,
            'dt': va4,
            'hi': va5,
            'rr': va6,
            'ekg': va7,
            'icp': va8,
            'ibp': va9,
            'cvp': va10,
            'map': va11,
            'keterangan': jenis
        };
        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            $('#save_icu_' + jenis).hide();
            $('#load_icu_' + jenis).show();
            dwr.engine.setAsync(true);
            HemodinamikaAction.save(result, pasienData, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_icu_' + jenis).show();
                        $('#load_icu_' + jenis).hide();
                        $('#modal-icu-' + jenis).modal('hide');
                        $('#warning_icu_' + ket).show().fadeOut(5000);
                        $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                        $('#modal-icu-' + jenis).scrollTop(0);
                    } else {
                        $('#save_icu_' + jenis).show();
                        $('#load_icu_' + jenis).hide();
                        $('#warning_icu_' + jenis).show().fadeOut(5000);
                        $('#msg_icu_' + jenis).text(res.msg);
                        $('#modal-icu-' + jenis).scrollTop(0);
                    }
                }
            });
        }
    } else {
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-icu-' + jenis).scrollTop(0);
    }

}

function showChartHemodinamika(jenis, tgl) {
    if(!cekSession()){
        $('#tanggal_data').html(converterDate(new Date(tgl)));
        $('#modal-icu-chart_' + jenis).modal({show: true, backdrop: 'static'});
        HemodinamikaAction.getListDetail(idDetailCheckup, jenis, tgl, function (res) {
            if (res.length > 0) {
                var dataArray = [];
                var thEkg = [];
                var tdEkg = [];
                var temJam = "";
                var tempEkg = 0;

                $.each(res, function (i, item) {
                    var ekg = 0;
                    if (item.ekg != null) {
                        // ekg = item.ekg;
                        thEkg.push({'jam': item.waktu});
                        tdEkg.push({'ekg': item.ekg});
                    }

                    dataArray.push({
                        y: item.waktu,
                        a: item.tensi,
                        b: item.sistole,
                        c: item.diastole,
                        d: item.hi,
                        e: item.rr,
                        f: item.icp,
                        g: item.ibp,
                        h: item.cvp,
                        i: item.map
                    });
                });

                $('#modal-icu-chart_' + jenis).on('shown.bs.modal', function (event) {
                    $('#line-chart_hemodinamika').empty();
                    var line = new Morris.Line({
                        element: 'line-chart_hemodinamika',
                        resize: true,
                        data: dataArray,
                        xkey: 'y',
                        ykeys: ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'],
                        labels: ['Temperature', 'Sistole', 'Diastole', 'HR', 'RR', 'ICP', 'IBP', 'CVP', 'MAP'],
                        lineColors: ['#ff0000', '#0000ff', '#00cc00', '#ff9933', '#cc6600', '#ffff66', '#cc6699', '#666633', '#000066'],
                        hideHover: 'auto',
                        parseTime: false,
                        lineWidth: 1
                    });
                });

                var jamEkg = "<td>Jam</td>";
                var valEkg = "<td>EKG</td>";
                if (thEkg.length > 0) {
                    $.each(thEkg, function (i, item) {
                        jamEkg += '<td>' + item.jam + '</td>';
                    });
                }

                if (tdEkg.length > 0) {
                    $.each(tdEkg, function (i, item) {
                        valEkg += '<td>' + item.ekg + '</td>';
                    });
                }
                $('#body_ekg').html('<tr style="font-weight: bold">' + jamEkg + '</tr><tr>' + valEkg + '</tr>');
            }
        });
    }
}

function listHemodinamika(jenis) {
    if(!cekSession()){
        var cekData = false;
        var body = "";
        var head = "";
        HemodinamikaAction.getListDetail(idDetailCheckup, jenis, null, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var tanggal = converterDate(new Date(item.createdDate));
                    var ketTgl = converterDateYmd(new Date(item.tanggal));

                    var tempTgl = "";
                    var btn = "";

                    if (i == 0) {
                        tempTgl = tanggal;
                        btn = '<i class="fa fa-line-chart" style="cursor: pointer; color: #1ab7ea" onclick="showChartHemodinamika(\'' + jenis + '\', \'' + ketTgl + '\')"></i>';
                    } else {
                        var tgl = res[i - 1]["createdDate"];
                        var tglB = converterDate(new Date(tgl));
                        if (tanggal == tglB) {
                            tempTgl = "";
                        } else {
                            tempTgl = tanggal;
                            btn = '<i class="fa fa-line-chart" style="cursor: pointer; color: #1ab7ea" onclick="showChartHemodinamika(\'' + jenis + '\', \'' + ketTgl + '\')"></i>';
                        }
                    }

                    body += '<tr>' +
                        '<td>' + btn + "&nbsp;&nbsp;" + tempTgl + '</td>' +
                        '<td>' + cekItemIsNull(item.waktu) + '</td>' +
                        '<td>' + cekItemIsNull(item.tensi) + '</td>' +
                        '<td>' + cekItemIsNull(item.sistole) + '</td>' +
                        '<td>' + cekItemIsNull(item.diastole) + '</td>' +
                        '<td>' + cekItemIsNull(item.hi) + '</td>' +
                        '<td>' + cekItemIsNull(item.rr) + '</td>' +
                        '<td>' + cekItemIsNull(item.ekg) + '</td>' +
                        '<td>' + cekItemIsNull(item.icp) + '</td>' +
                        '<td>' + cekItemIsNull(item.ibp) + '</td>' +
                        '<td>' + cekItemIsNull(item.cvp) + '</td>' +
                        '<td>' + cekItemIsNull(item.map) + '</td>' +
                        '<td align="center">' + '<i id="delete_' + item.idHemodinamika + '" onclick="conICU(\'' + jenis + '\', \'asesmen_icu\', \'' + item.idHemodinamika + '\', \'hemodinamika\')" class="fa fa-trash hvr-grow" style="color: red;">' + '</td>' +
                        '</tr>';
                    cekData = true;
                });
            } else {
                body = '<tr><td>Data belum ada</td></tr>';
            }

            if (cekData) {
                head = '<tr style="font-weight: bold">\n' +
                    '<td>Tanggal</td>\n' +
                    '<td>Waktu</td>\n' +
                    '<td>Temperatur</td>\n' +
                    '<td>Sistole</td>\n' +
                    '<td>Diastole</td>\n' +
                    '<td>HR</td>\n' +
                    '<td>RR</td>\n' +
                    '<td>EKG</td>\n' +
                    '<td>ICP</td>\n' +
                    '<td>IBP</td>\n' +
                    '<td>CVP</td>\n' +
                    '<td>MAP</td>\n' +
                    '<td width="10%" align="center">Action</td>\n' +
                    '</tr>';
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + body + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_icu_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_icu_' + jenis).attr('src', url);
            $('#btn_icu_' + jenis).attr('onclick', 'delRowHemo(\'' + jenis + '\')');
        });
    }
}

function delRowHemo(id) {
    $('#del_icu_' + id).remove();
    var url = contextPath + '/pages/images/icons8-add-list-25.png';
    $('#btn_icu_' + id).attr('src', url);
    $('#btn_icu_' + id).attr('onclick', 'listHemodinamika(\'' + id + '\')');
}

function saveRespirasi(jenis, ket) {
    var dataPasien = "";
    dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var va1 = $('#res1').val();
    var va2 = $('#res2').val();
    var va3 = $('#res3').val();
    var va4 = $('#res4').val();
    var va5 = $('#res5').val();
    var va6 = $('#res6').val();
    var va7 = $('#res7').val();
    var va8 = $('#res8').val();
    var va9 = $('#res9').val();
    var va10 = $('#res10').val();
    var va11 = $('#res11').val();
    var va12 = $('#res12').val();
    var va13 = $('#res13').val();
    var va14 = $('#res14').val();
    var va014 = $('#res014').val();
    var va15 = $('#res15').val();
    var va16 = $('#res16').val();
    var va17 = $('#res17').val();
    var va18 = $('#res18').val();
    var va19 = $('#res19').val();
    var va20 = $('#res20').val();
    var va21 = $('#res21').val();
    var va22 = $('#res22').val();
    var va23 = $('#res23').val();
    var va24 = $('#res24').val();
    var va25 = $('#res25').val();
    var va26 = $('#res26').val();
    var va27 = $('#res27').val();
    var va28 = $('#res28').val();

    var data = "";

    if (va1 != '') {
        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1,
            'gcs': va2 + '|' + va3 + '|' + va4,
            'diameter_pupil': va5 + '|' + va6,
            'reflek_cahaya': va7 + '|' + va8,
            'tk': va9 + '|' + va10,
            'kk': va11 + '|' + va12,
            'o2': va13,
            'tipe_ventilasi': va14,
            'peep': va15,
            'frekwensi': va16,
            'tv': va17,
            'mv': va18,
            'p_support_p_asb': va19,
            'p_ins_p_con': va20,
            'triger': va21,
            'ins_time': va22,
            'flow': va23,
            'fio_kon': va24,
            'ukuran_ett': va25,
            'kedalaman_ett': va26,
            'spo2': va27,
            'secret': va28,
            'keterangan': jenis
        };
        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            $('#save_icu_' + jenis).hide();
            $('#load_icu_' + jenis).show();
            dwr.engine.setAsync(true);
            RespirasiAction.save(result, pasienData, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_icu_' + jenis).show();
                        $('#load_icu_' + jenis).hide();
                        $('#modal-icu-' + jenis).modal('hide');
                        $('#warning_icu_' + ket).show().fadeOut(5000);
                        $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                        $('#modal-icu-' + jenis).scrollTop(0);
                    } else {
                        $('#save_icu_' + jenis).show();
                        $('#load_icu_' + jenis).hide();
                        $('#warning_icu_' + jenis).show().fadeOut(5000);
                        $('#msg_icu_' + jenis).text(res.msg);
                        $('#modal-icu-' + jenis).scrollTop(0);
                    }
                }
            });
        }
    } else {
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-icu-' + jenis).scrollTop(0);
    }
}

function listRespirasi(jenis) {
    if(!cekSession()){
        var body = "";
        var head = '';
        var cekData = false;
        RespirasiAction.getListDetail(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var tanggal = converterDate(new Date(item.createdDate));
                    var tempTgl = "";

                    if (i == 0) {
                        tempTgl = tanggal;
                    } else {
                        var tgl = res[i - 1]["createdDate"];
                        var tglB = converterDate(new Date(tgl));
                        if (tanggal == tglB) {
                            tempTgl = "";
                        } else {
                            tempTgl = tanggal;
                        }
                    }

                    var gcs = item.gcs.split("|");
                    var dp = item.diameterPupil.split("|");
                    var rc = item.reflekCahaya.split("|");
                    var tk = item.tk.split("|");
                    var kk = item.kk.split("|");

                    body += '<tr>' +
                        '<td>' + tempTgl + '<span class="pull-right">' + cekItemIsNull(item.waktu) + '</span>' + '</td>' +
                        '<td>' + gcs[0] + '</td>' +
                        '<td>' + gcs[1] + '</td>' +
                        '<td>' + gcs[2] + '</td>' +
                        '<td>' + dp[0] + '</td>' +
                        '<td>' + dp[1] + '</td>' +
                        '<td>' + rc[0] + '</td>' +
                        '<td>' + rc[1] + '</td>' +
                        '<td>' + tk[0] + '</td>' +
                        '<td>' + tk[1] + '</td>' +
                        '<td>' + kk[0] + '</td>' +
                        '<td>' + kk[1] + '</td>' +
                        '<td>' + cekItemIsNull(item.o2) + '</td>' +
                        '<td>' + cekItemIsNull(item.peep) + '</td>' +
                        '<td>' + cekItemIsNull(item.frekwensi) + '</td>' +
                        '<td>' + cekItemIsNull(item.tv) + '</td>' +
                        '<td>' + cekItemIsNull(item.mv) + '</td>' +
                        '<td>' + cekItemIsNull(item.pSupportPAsb) + '</td>' +
                        '<td>' + cekItemIsNull(item.pInsPCon) + '</td>' +
                        '<td>' + cekItemIsNull(item.triger) + '</td>' +
                        '<td>' + cekItemIsNull(item.insTime) + '</td>' +
                        '<td>' + cekItemIsNull(item.flow) + '</td>' +
                        '<td>' + cekItemIsNull(item.fioKon) + '</td>' +
                        '<td>' + cekItemIsNull(item.ukuranEtt) + '</td>' +
                        '<td>' + cekItemIsNull(item.kedalamanEtt) + '</td>' +
                        '<td>' + cekItemIsNull(item.spo2) + '</td>' +
                        '<td>' + cekItemIsNull(item.secret) + '</td>' +
                        '<td align="center">' + '<i id="delete_' + item.idRespirasi + '" onclick="conICU(\'' + jenis + '\', \'asesmen_icu\', \'' + item.idRespirasi + '\', \'respirasi\')" class="fa fa-trash hvr-grow" style="color: red;">' + '</td>' +
                        '</tr>';
                    cekData = true;
                });
            } else {
                body = '<tr><td>Data belum ada</td></tr>';
            }

            if (cekData) {
                head = '<tr>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">Tanggal Jam</td>\n' +
                    '<td colspan="3" style="vertical-align: middle" align="center">GCS</td>\n' +
                    '<td colspan="2" style="vertical-align: middle" align="center">DP</td>\n' +
                    '<td colspan="2" style="vertical-align: middle" align="center">RC</td>\n' +
                    '<td colspan="4" style="vertical-align: middle" align="center">EXTR</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">O2</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">PE</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">FR</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">TV</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">MV</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">PS</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">PI</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">TR</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">IN</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">FL</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">FI</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">UE</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">DE</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">SP</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">SE</td>\n' +
                    '<td rowspan="2" style="vertical-align: middle" align="center">Action</td>\n' +
                    '                        </tr>\n' +
                    '                        <tr>\n' +
                    '<td align="center">E</td>\n' +
                    '<td align="center">V</td>\n' +
                    '<td align="center">M</td>\n' +
                    '<td align="center">R</td>\n' +
                    '<td align="center">L</td>\n' +
                    '<td align="center">R</td>\n' +
                    '<td align="center">L</td>\n' +
                    '<td align="center">TR</td>\n' +
                    '<td align="center">TL</td>\n' +
                    '<td align="center">KR</td>\n' +
                    '<td align="center">KL</td>\n' +
                    '                        </tr>';
            }
            var table = '<table style="font-size: 10px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + body + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_icu_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_icu_' + jenis).attr('src', url);
            $('#btn_icu_' + jenis).attr('onclick', 'delRowRes(\'' + jenis + '\')');
        });
    }
}

function delRowRes(id) {
    $('#del_icu_' + id).remove();
    var url = contextPath + '/pages/images/icons8-add-list-25.png';
    $('#btn_icu_' + id).attr('src', url);
    $('#btn_icu_' + id).attr('onclick', 'listRespirasi(\'' + id + '\')');
}

function setInputan(jenis) {

    var resus = "";
    var darah = "";
    var infus = "";
    var obat = "";
    var inputan = "";
    var parameter = [];

    if ("checklist_kriteria" == jenis) {

        var head = "";
        var body = "";
        var dataHead = [];
        var dataBody = [];

        head = '<tr><td rowspan="2" style="vertical-align: middle" align="center">Kriteria</td><td colspan="2" style="vertical-align: middle" align="center">Keterangan</td></tr>' +
            '<tr><td style="vertical-align: middle" align="center">Ya</td><td style="vertical-align: middle" align="center">Tidak</td></tr>';

        dataBody.push({'param': 'Prioritas 1 (Skor 3)', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': ''});
        dataBody.push({
            'param': 'a. Gagal jantung dengan tanda bendungan paru',
            'ket1': 'Ya',
            'ket2': 'Tidak',
            'skor': '3'
        });
        dataBody.push({'param': 'b. Gagal nafas', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({'param': 'c. Gangguan asam basa / elektrolit', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({
            'param': 'd. Gagal ginjal dengan tanda bendungan paru',
            'ket1': 'Ya',
            'ket2': 'Tidak',
            'skor': '3'
        });
        dataBody.push({
            'param': 'e. Syok karena perdarahan atau anafilakis',
            'ket1': 'Ya',
            'ket2': 'Tidak',
            'skor': '3'
        });
        dataBody.push({
            'param': 'f. Pasca operasi dengan gangguan nafas atau hipotensi',
            'ket1': 'Ya',
            'ket2': 'Tidak',
            'skor': '3'
        });
        dataBody.push({'param': 'Prioritas 2 (Skor 2)', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': ''});
        dataBody.push({'param': 'a. Pasca operasi besar', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '2'});
        dataBody.push({'param': 'b. Kejang berulang', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '2'});
        dataBody.push({'param': 'c. Gangguan kesadaran', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '2'});
        dataBody.push({'param': 'd. Dehidrasi berat', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '2'});
        // dataBody.push({'param': 'e. ', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '2'});
        dataBody.push({'param': 'e. Aritmia Jantung', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '2'});
        dataBody.push({'param': 'f. Astma Akut Berat', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '2'});
        dataBody.push({
            'param': 'h. Diabetis yang memerlukan terapi insulin-kontinyu',
            'ket1': 'Ya',
            'ket2': 'Tidak',
            'skor': '2'
        });
        dataBody.push({'param': 'Prioritas 3 (Skor 1)', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': ''});
        dataBody.push({'param': 'a. Penyakit keganasan dengan metastatis', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '1'});
        dataBody.push({
            'param': 'b. Pasien geriatri dengan fungsi hidup sebelumnya minimal',
            'ket1': 'Ya',
            'ket2': 'Tidak',
            'skor': '1'
        });
        dataBody.push({'param': 'c. Pasien dengan GCS 3', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '1'});
        dataBody.push({
            'param': 'd. Pasien penyakit jantung, paru terminal disertai komplikasi penyakit akut berat',
            'ket1': 'Ya',
            'ket2': 'Tidak',
            'skor': '1'
        });
        dataBody.push({
            'param': 'Kriteria Fisiologis Tanda-Tanda Vital (Skor 3)',
            'ket1': 'Ya',
            'ket2': 'Tidak',
            'skor': ''
        });
        dataBody.push({'param': 'a. Nadi < 40 atau > 150 (x/menit)', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({
            'param': 'b. SBP < 80 mmHg atau 20 mmHg dibawah SBP pasien',
            'ket1': 'Ya',
            'ket2': 'Tidak',
            'skor': '3'
        });
        dataBody.push({'param': 'c. MAP < 60 mmHg', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({'param': 'd. DBP > 120 mmHg', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({'param': 'e. RR > 35 x/menit', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({
            'param': 'Kriteria Fisiologis Laboratorium (Skor 3)',
            'ket1': 'Ya',
            'ket2': 'Tidak',
            'skor': ''
        });
        dataBody.push({'param': 'a. Na < 110 meq/L atau > 170 meq/L', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({'param': 'b. K < 2.0 meq/L atau 7 meq/L', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({'param': 'c. PaO2 < 50 mmHg', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({'param': 'd. PH < 7,1 atau 7,7', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({'param': 'e. GDS > 800 mg/dl', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});
        dataBody.push({'param': 'f. Ca > 15 mg/dl', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '3'});

        if (dataBody.length > 0) {
            $.each(dataBody, function (i, item) {
                var skor = item.skor;
                var kriteria = item.param;
                if (skor != '') {
                    body += '<tr><td>' + item.param + '<input value="' + item.param + '" type="hidden" name="parameter"></td>' +
                        '<td>' +
                        '<div class="form-check02">' +
                        '<input onclick="setChoice(\'ya\', \'' + i + '\')" type="checkbox" name="kriteria1" id="ya' + i + '" value="' + item.ket1 + '|' + skor + '">' +
                        '<label for="ya' + i + '"></label>' +
                        '</div>' +
                        '</td>' +
                        '<td>' +
                        '<div class="form-check02">' +
                        '<input onclick="setChoice(\'tidak\', \'' + i + '\')" type="checkbox" name="kriteria2" id="tidak' + i + '" value="' + item.ket2 + '|0">' +
                        '<label for="tidak' + i + '"></label>' +
                        '</div>' +
                        '</td></tr>';
                } else {
                    body += '<tr style="font-weight: bold"><td colspan="3">' + item.param + '' +
                        '<input value="' + item.param + '" type="hidden" name="parameter">' +
                        '<input type="hidden" name="kriteria1"><input type="hidden" name="kriteria2">' +
                        '</td></tr>';
                }
            });
        }

        $('#th_' + jenis).html(head);
        $('#td_' + jenis).html(body);


    } else {
        dwr.engine.setAsync(false);
        IcuAction.getListHead(idDetailCheckup, "parenteral", null, function (res) {
            if (res.length > 0) {
                var temp = "";
                $('#form-parenteral').hide();
                $('#is_new_parenteral').val(false);
                $.each(res, function (i, item) {
                    temp += '<div class="row jarak">\n' +
                        '<label class="col-md-3">'+item.jenis+'</label>\n' +
                        '<div class="col-md-5">\n' +
                        '    <input type="hidden" class="id_header_parenteral" value="'+item.idHeaderIcu+'" readonly>' +
                        '    <input class="form-control nilai_parenteral" placeholder="Nilai" type="number">\n' +
                        '</div>\n' +
                        '</div>';
                });
                $('#h_parenteral').html(temp);
            }else{
                $('#is_new_parenteral').val(true);
                $('#form-parenteral').show();
            }
        });
        dwr.engine.setAsync(false);
        IcuAction.getListDetail(idDetailCheckup, "obat", function (res) {
            if (res.length > 0) {
                var temp = "";
                $('#is_new_obat').val(false);
                $('#form-obat').hide();
                $.each(res, function (i, item) {
                    if(item.nilai == null || item.nilai == '' || item.nilai == '#'){
                        temp += '<div class="row jarak">\n' +
                            '<label class="col-md-3">'+item.jenis+'</label>\n' +
                            '<div class="col-md-3">' +
                            '<div class="input-group">\n' +
                            '<div class="input-group-addon">\n' +
                            '<i class="fa fa-clock-o"></i>\n' +
                            '</div>\n' +
                            '<input type="hidden" class="id_detail_obat" value="'+item.idDetailIcu+'">' +
                            '<input class="form-control" value="'+item.waktu+'" readonly>\n' +
                            '</div>'+
                            '</div>'+
                            '<div class="col-md-3">\n' +
                            '    <input type="hidden" class="id_header_obat" value="'+item.idHeaderIcu+'">' +
                            '    <input class="form-control nilai_obat" placeholder="Nilai" type="number">\n' +
                            '</div>\n' +
                            '</div>';
                    }
                });
                $('#h_obat').html(temp);
            }else{
                $('#is_new_obat').val(true);
                $('#form-obat').show();
            }
        });
        dwr.engine.setAsync(false);
        IcuAction.getListHead(idDetailCheckup, "enteral", null, function (res) {
            if (res.length > 0) {
                var temp = "";
                $('#is_new_enteral').val(false);
                $('#form-enteral').hide();
                $.each(res, function (i, item) {
                    temp += '<div class="row jarak">\n' +
                        '<label class="col-md-3">'+item.jenis+'</label>\n' +
                        '<div class="col-md-5">\n' +
                        '    <input type="hidden" class="id_header_enteral" value="'+item.idHeaderIcu+'" readonly>' +
                        '    <input class="form-control nilai_enteral" placeholder="Nilai" type="number">\n' +
                        '</div>\n' +
                        '</div>';
                });
                $('#h_enteral').html(temp);
            }else{
                $('#is_new_enteral').val(true);
                $('#form-enteral').show();
            }
        });
        dwr.engine.setAsync(false);
        IcuAction.getListHead(idDetailCheckup, "output", null, function (res) {
            if (res.length > 0) {
                var temp = "";
                $('#is_new_output').val(false);
                $('#form-output').hide();
                $.each(res, function (i, item) {
                    temp += '<div class="row jarak">\n' +
                        '<label class="col-md-3">'+item.jenis+'</label>\n' +
                        '<div class="col-md-5">\n' +
                        '    <input type="hidden" class="id_header_output" value="'+item.idHeaderIcu+'" readonly>' +
                        '    <input class="form-control nilai_output" placeholder="Nilai" type="number">\n' +
                        '</div>\n' +
                        '</div>';
                });
                $('#h_output').html(temp);
            }else{
                $('#is_new_output').val(true);
                $('#form-output').show();
            }
        });
    }

    var sel = $('.select2').length;
    if (sel > 0) {
        $('.select2').select2();
    }
}

function setChoice(ket, index) {

    if ("ya" == ket) {
        $('#ya' + index).prop('checked', true);
        $('#tidak' + index).prop('checked', false);
    }

    if ("tidak" == ket) {
        $('#ya' + index).prop('checked', false);
        $('#tidak' + index).prop('checked', true);
    }
}

function pilIsi(jenis, val) {

    if ("rc" == val) {
        var resus = '<hr class="garis"><div class="row">\n' +
            '<div class="form-group">\n' +
            '    <div class="col-md-offset-3 col-md-1">\n' +
            '        <button class="btn btn-success" onclick="addInputan(\'' + jenis + '\',\'resus\')"><i class="fa fa-plus"></i> Tambah</button>\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>\n' +
            '<div class="row jarak" id="res1">\n' +
            '<div class="form-group">\n' +
            '    <label class="col-md-3 jarak">Resusitasi Cairan</label>\n' +
            '    <div class="col-md-4">\n' +
            '       <input class="form-control jarak data-label' + jenis + '">\n' +
            '    </div>\n' +
            '    <label class="col-md-1 jarak">Nilai</label>\n' +
            '    <div class="col-md-3">\n' +
            '       <input class="form-control jarak nilai' + jenis + '" type="number">\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>';

        var darah = '<hr class="garis"><div class="row">\n' +
            '<div class="form-group">\n' +
            '    <div class="col-md-offset-3 col-md-1">\n' +
            '        <button class="btn btn-success" onclick="addInputan(\'' + jenis + '\', \'darah\')"><i class="fa fa-plus"></i> Tambah</button>\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>\n' +
            '<div class="row jarak" id="res1">\n' +
            '<div class="form-group">\n' +
            '    <label class="col-md-3 jarak">Darah</label>\n' +
            '    <div class="col-md-4">\n' +
            '       <input class="form-control jarak data-label' + jenis + '">\n' +
            '    </div>\n' +
            '    <label class="col-md-1 jarak">Nilai</label>\n' +
            '    <div class="col-md-3">\n' +
            '       <input class="form-control jarak nilai' + jenis + '" type="number">\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>';

        $('#resus').html(resus);
        $('#darah').html(darah);
        $('#infus').html('');

    } else if ("in" == val) {
        var infus = '<hr class="garis"><div class="row">\n' +
            '<div class="form-group">\n' +
            '    <div class="col-md-offset-3 col-md-1">\n' +
            '        <button class="btn btn-success" onclick="addInputan(\'' + jenis + '\', \'infus\')"><i class="fa fa-plus"></i> Tambah</button>\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>\n' +
            '<div class="row jarak" id="res1">\n' +
            '<div class="form-group">\n' +
            '    <label class="col-md-3 jarak">Infus/ TY Drip</label>\n' +
            '    <div class="col-md-4">\n' +
            '       <input class="form-control jarak data-label' + jenis + '">\n' +
            '    </div>\n' +
            '    <label class="col-md-1 jarak">Nilai</label>\n' +
            '    <div class="col-md-3">\n' +
            '       <input class="form-control jarak nilai' + jenis + '" type="number">\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>';

        $('#infus').html(infus);
        $('#resus').html('');
        $('#darah').html('');
    }
}

function addInputan(jenis, tipe) {

    var id = $('.' + tipe).length;
    var count = id + 1;

    var label = "";
    var option = "";

    if ("resus" == tipe) {
        label = "Resusitasi Cairan";
    }
    if ("darah" == tipe) {
        label = "Darah";
    }
    if ("infus" == tipe) {
        label = "Infus/ TY Drip";
    }

    var inp = '<div class="row jarak" id="' + jenis + count + '">\n' +
        '<div class="form-group">\n' +
        '    <label class="col-md-3 jarak">' + label + '</label>\n' +
        '    <div class="col-md-4">\n' +
        '       <input class="form-control jarak data-label' + jenis + ' ' + jenis + '" placeholder="Keterangan Jenis">\n' +
        '    </div>\n' +
        '    <label class="col-md-1 jarak">Nilai</label>\n' +
        '    <div class="col-md-3">\n' +
        '       <input class="form-control jarak nilai' + jenis + '" type="number">\n' +
        '    </div>\n' +
        '    <div class="col-md-1">\n' +
        '        <button class="btn btn-danger" style="margin-left: -20px; margin-top: 10px" onclick="delInputan(\'' + jenis + count + '\')"><i class="fa fa-trash"></i></button>\n' +
        '    </div>\n' +
        '</div>\n' +
        '</div>';

    if ("keseimbangan_icu" == jenis) {
        $('#' + tipe).append(inp);
    } else {
        $('#' + jenis).append(inp);
    }

    var sel = $('.select2').length;
    if (sel > 0) {
        $('.select2').select2();
    }

}

function delInputan(id) {
    $('#' + id).remove();
}

function saveInputan(jenis, ket) {
    var dataPasien = "";
    dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }

    var isNew1 = $('#is_new_parenteral').val();
    var isNew2 = $('#is_new_obat').val();
    var isNew3 = $('#is_new_enteral').val();
    var isNew4 = $('#is_new_output').val();

    var data = [];
    var waktu = $('#waktu_' + jenis).val();
    var waktu = $('#waktu_keseimbangan_icu').val();
    var p1 = $('.jenis_loading');
    var p11 = $('.nilai_loading');
    var p2 = $('.jenis_darah');
    var p22 = $('.nilai_darah');
    var p3 = $('.jenis_cairan');
    var p33 = $('.nilai_cairan');

    if(isNew1 == "false"){
        var idHeader = $('.id_header_parenteral');
        var nilai = $('.nilai_parenteral');
        $.each(idHeader, function (i, item) {
            if(item.value != '' && nilai[i].value != ''){
                data.push({
                    'id_detail_checkup': idDetailCheckup,
                    'waktu': waktu,
                    'id_header_icu': item.value,
                    'nilai': nilai[i].value,
                    'kategori': 'parenteral',
                    'is_new': isNew1
                })
            }
        });
    }else{
        $.each(p1, function (i, item) {
            console.log(p11);
            if (item.value != '' && p11[i].value != '') {
                data.push({
                    'id_detail_checkup': idDetailCheckup,
                    'waktu': waktu,
                    'jenis': item.value,
                    'nilai': p11[i].value,
                    'kategori': 'parenteral',
                    'is_new': isNew1
                })
            }
        });

        $.each(p2, function (i, item) {
            if (item.value != '' && p22[i].value != '') {
                data.push({
                    'id_detail_checkup': idDetailCheckup,
                    'waktu': waktu,
                    'jenis': item.value,
                    'nilai': p22[i].value,
                    'kategori': 'parenteral',
                    'is_new': isNew1
                })
            }
        });

        $.each(p3, function (i, item) {
            if (item.value != '' && p33[i].value != '') {
                data.push({
                    'id_detail_checkup': idDetailCheckup,
                    'waktu': waktu,
                    'jenis': item.value,
                    'nilai': p33[i].value,
                    'kategori': 'parenteral',
                    'is_new': isNew1
                })
            }
        });
    }

    var o1 = $('.jenis_injeksi');
    var o11 = $('.kali_injeksi');
    var o111 = $('.nilai_injeksi');
    var o2 = $('.jenis_oral');
    var o22 = $('.kali_oral');
    var o222 = $('.nilai_oral');
    var o3 = $('.jenis_lainnya');
    var o33 = $('.kali_lainnya');
    var o333 = $('.nilai_lainnya');

    if(isNew2 == "false"){
        var idHeader = $('.id_header_obat');
        var idDetail = $('.id_detail_obat');
        var nilai = $('.nilai_obat');
        $.each(idHeader, function (i, item) {
            if(item.value != '' && idDetail[i].value != '' && nilai[i].value != ''){
                data.push({
                    'id_detail_checkup': idDetailCheckup,
                    'waktu': waktu,
                    'id_header_icu': item.value,
                    'id_detail_icu': idDetail[i].value,
                    'nilai': nilai[i].value,
                    'kategori': 'obat',
                    'is_new': isNew2
                });
            }
        });
    }else{
        $.each(o1, function (i, item) {
            if (item.value != '' && o11[i].value != '' && o111[i].value != '') {
                var kali = o11[i].value.split('x');
                var atas = "";
                var bawah = "";
                if (kali[0] != null && kali[0] != '') {
                    atas = kali[0];
                    var time = new Date;
                    var curentTime = time.getHours();
                    var sisaJam = 24 - curentTime;
                    var perHari = sisaJam / parseInt(atas);
                    var perMenit = perHari * 60;
                    for (var j = 0; j < atas; j++) {
                        if (j == 0) {
                            data.push({
                                'id_detail_checkup': idDetailCheckup,
                                'waktu': waktu,
                                'jenis': item.value + ' ' + o11[i].value,
                                'nilai': o111[0].value,
                                'kategori': 'obat',
                                'is_new': isNew2
                            });
                        } else {
                            time.setMinutes(perMenit);
                            var nextTime = converterTime(time);
                            data.push({
                                'id_detail_checkup': idDetailCheckup,
                                'waktu': nextTime,
                                'jenis': item.value + ' ' + o11[i].value,
                                'nilai': '',
                                'kategori': 'obat',
                                'is_new': isNew2
                            });
                        }
                    }
                }
                if (kali[1] != null && kali[1] != '') {
                    bawah = kali[1];
                }
            }
        });

        $.each(o2, function (i, item) {
            if (item.value != '' && o22[i].value != '' && o222[i].value != '') {
                var kali = o22[i].value.split('x');
                var atas = "";
                var bawah = "";
                if (kali[0] != null && kali[0] != '') {
                    atas = kali[0];
                    var time = new Date;
                    var curentTime = time.getHours();
                    var sisaJam = 24 - curentTime;
                    var perHari = sisaJam / parseInt(atas);
                    var perMenit = perHari * 60;
                    for (var j = 0; j < atas; j++) {
                        if (j == 0) {
                            data.push({
                                'id_detail_checkup': idDetailCheckup,
                                'waktu': waktu,
                                'jenis': item.value + ' ' + o22[i].value,
                                'nilai': o222[0].value,
                                'kategori': 'obat',
                                'is_new': isNew2
                            });
                        } else {
                            time.setMinutes(perMenit);
                            var nextTime = converterTime(time);
                            data.push({
                                'id_detail_checkup': idDetailCheckup,
                                'waktu': nextTime,
                                'jenis': item.value + ' ' + o22[i].value,
                                'nilai': '',
                                'kategori': 'obat',
                                'is_new': isNew2
                            });
                        }
                    }
                }
            }
        });
        $.each(o3, function (i, item) {
            if (item.value != '' && o33[i].value != '' && o333[i].value != '') {
                var kali = o33[i].value.split('x');
                var atas = "";
                var bawah = "";
                if (kali[0] != null && kali[0] != '') {
                    atas = kali[0];
                    var time = new Date;
                    var curentTime = time.getHours();
                    var sisaJam = 24 - curentTime;
                    var perHari = sisaJam / parseInt(atas);
                    var perMenit = perHari * 60;
                    for (var j = 0; j < atas; j++) {
                        if (j == 0) {
                            data.push({
                                'id_detail_checkup': idDetailCheckup,
                                'waktu': waktu,
                                'jenis': item.value + ' ' + o33[i].value,
                                'nilai': o333[0].value,
                                'kategori': 'obat',
                                'is_new': isNew2
                            });
                        } else {
                            time.setMinutes(perMenit);
                            var nextTime = converterTime(time);
                            data.push({
                                'id_detail_checkup': idDetailCheckup,
                                'waktu': nextTime,
                                'jenis': item.value + ' ' + o33[i].value,
                                'nilai': '',
                                'kategori': 'obat',
                                'is_new': isNew2
                            });
                        }
                    }
                }
            }
        });
    }

    var ngt = $('#ngt').val();
    var minum = $('#minum').val();

    if(isNew3 == "false"){
        var idHeader = $('.id_header_enteral');
        var nilai = $('.nilai_enteral');
        $.each(idHeader, function (i, item) {
            if(item.value != '' && nilai[i].value != ''){
                data.push({
                    'id_detail_checkup': idDetailCheckup,
                    'waktu': waktu,
                    'id_header_icu': item.value,
                    'nilai': nilai[i].value,
                    'kategori': 'enteral',
                    'is_new': isNew3
                });
            }
        });
    }else{
        if (ngt && minum != '') {
            data.push({
                'id_detail_checkup': idDetailCheckup,
                'waktu': waktu,
                'jenis': 'NGT/Oral',
                'nilai': ngt,
                'kategori': 'enteral',
                'is_new': isNew3
            });
            data.push({
                'id_detail_checkup': idDetailCheckup,
                'waktu': waktu,
                'jenis': 'Minum',
                'nilai': minum,
                'kategori': 'enteral',
                'is_new': isNew3
            });
        }
    }

    var drain1 = $('#drain1').val();
    var drain2 = $('#drain2').val();
    var urin = $('#urin').val();
    var muntah = $('#muntah').val();
    var bab = $('#bab').val();
    var iwl = $('#iwl').val();
    var stoma = $('#stoma').val();

    if(isNew4 == "false"){
        var idHeader = $('.id_header_output');
        var nilai = $('.nilai_output');
        $.each(idHeader, function (i, item) {
            if(item.value != '' && nilai[i].value != ''){
                data.push({
                    'id_detail_checkup': idDetailCheckup,
                    'waktu': waktu,
                    'id_header_icu': item.value,
                    'nilai': nilai[i].value,
                    'kategori': 'output',
                    'is_new': isNew4
                });
            }
        });
    }else{
        if (drain1 && drain2 && urin && muntah && bab && iwl && stoma != '') {
            data.push({
                'id_detail_checkup': idDetailCheckup,
                'waktu': waktu,
                'jenis': 'Drain 1',
                'nilai': drain1,
                'kategori': 'output',
                'is_new': isNew4
            });
            data.push({
                'id_detail_checkup': idDetailCheckup,
                'waktu': waktu,
                'jenis': 'Drain 2',
                'nilai': drain2,
                'kategori': 'output',
                'is_new': isNew4
            });
            data.push({
                'id_detail_checkup': idDetailCheckup,
                'waktu': waktu,
                'jenis': 'Urin',
                'nilai': urin,
                'kategori': 'output',
                'is_new': isNew4
            });
            data.push({
                'id_detail_checkup': idDetailCheckup,
                'waktu': waktu,
                'jenis': 'Muntah',
                'nilai': muntah,
                'kategori': 'output',
                'is_new': isNew4
            });
            data.push({
                'id_detail_checkup': idDetailCheckup,
                'waktu': waktu,
                'jenis': 'BAB',
                'nilai': bab,
                'kategori': 'output',
                'is_new': isNew4
            });
            data.push({
                'id_detail_checkup': idDetailCheckup,
                'waktu': waktu,
                'jenis': 'IWL',
                'nilai': iwl,
                'kategori': 'output',
                'is_new': isNew4
            });
            data.push({
                'id_detail_checkup': idDetailCheckup,
                'waktu': waktu,
                'jenis': 'Stoma',
                'nilai': stoma,
                'kategori': 'output',
                'is_new': isNew4
            });
        }
    }

    console.log(isNew1);
    console.log(isNew2);
    console.log(isNew3);
    console.log(isNew4);

    if (waktu != '') {
        var result = JSON.stringify(data);
        var pasienData = JSON.stringify(dataPasien);

        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);

        IcuAction.save(result, pasienData, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#modal-icu-' + jenis).modal('hide');
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                    $('#modal-icu-' + jenis).scrollTop(0);
                } else {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#warning_icu_' + jenis).show().fadeOut(5000);
                    $('#msg_icu_' + jenis).text(res.msg);
                    $('#modal-icu-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-icu-' + jenis).scrollTop(0);
    }
}

function listInputan(jenis) {
    var body1 = "";
    var body2 = "";
    var body3 = "";
    var body4 = "";
    var rbody1 = "";
    var rbody2 = "";
    var rbody3 = "";
    var rbody4 = "";
    var thead1 = "<td width='12%'>Tanggal Jam</td>";
    var thead2 = "<td width='12%'>Tanggal Jam</td>";
    var thead3 = "<td width='12%'>Tanggal Jam</td>";
    var thead4 = "<td width='12%'>Tanggal Jam</td>";
    var head1 = "";
    var head2 = "";
    var head3 = "";
    var head4 = "";
    var body = "";
    var head = "";
    var parenteral = [];
    var obat = [];
    var enteral = [];
    var output = [];
    var hparenteral = [];
    var hobat = [];
    var henteral = [];
    var houtput = [];
    var temp1 = "";
    var temp2 = "";
    var temp3 = "";
    var temp4 = "";
    dwr.engine.setAsync(false);
    IcuAction.getListDetail(idDetailCheckup, null, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                if (item.kategori == "parenteral") {
                    parenteral.push({
                        'jam': item.waktu,
                        'tanggal': converterDate(item.createdDate),
                        'nilai': item.nilai,
                        'id_detail_icu': item.idDetailIcu,
                        'id_header_icu': item.idHeaderIcu
                    });
                }
                if (item.kategori == "obat") {
                    obat.push({
                        'jam': item.waktu,
                        'tanggal': converterDate(item.createdDate),
                        'nilai': item.nilai,
                        'id_detail_icu': item.idDetailIcu,
                        'id_header_icu': item.idHeaderIcu
                    });
                }
                if (item.kategori == "enteral") {
                    enteral.push({
                        'jam': item.waktu,
                        'tanggal': converterDate(item.createdDate),
                        'nilai': item.nilai,
                        'id_detail_icu': item.idDetailIcu,
                        'id_header_icu': item.idHeaderIcu
                    });
                }
                if (item.kategori == "output") {
                    output.push({
                        'jam': item.waktu,
                        'tanggal': converterDate(item.createdDate),
                        'nilai': item.nilai,
                        'id_detail_icu': item.idDetailIcu,
                        'id_header_icu': item.idHeaderIcu
                    });
                }
            });
        }
    });
    if (parenteral.length > 0) {
        dwr.engine.setAsync(false);
        IcuAction.getListHead(idDetailCheckup, "parenteral", null, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    thead1 += '<td>' + item.jenis + '</td>';
                });
            }
        });
        var pBody = "";
        var tempJam = "";
        head1 = '<tr style="font-weight: bold"><td>Parenteral</td></tr><tr>' + thead1 + '<td width="5%" align="center">Action</td></tr>';
        $.each(parenteral, function (i, item) {
            var tanggal = item.tanggal;
            var tempTgl = "";
            if (i == 0) {
                tempTgl = tanggal;
            } else {
                var tgl = parenteral[i - 1]["tanggal"];
                var tglB = tgl;
                if (tanggal == tglB) {
                    tempTgl = "";
                } else {
                    tempTgl = tanggal;
                }
            }

            var tempTr = "";
            var jam = "";
            var btn = "";
            if (tempJam == "") {
                tempJam = item.jam;
                jam = '<td>' + tempTgl + '<span class="pull-right">' + item.jam + '</span></td>';
            } else {
                if (tempJam != item.jam) {
                    tempJam = item.jam;
                    tempTr = "|";
                    jam = '<td>' + tempTgl + '<span class="pull-right">' + item.jam + '</span></td>';
                    btn = '<td align="center"><i onclick="conICU(\''+jenis +'\',\'asesmen_icu\', \''+item.id_detail_icu +'\')" style="color: red" class="fa fa-trash hvr-grow"></i></td>';
                }
            }
            if (pBody == "") {
                pBody = jam + '<td>' + '<span style="background-color: #449d44; padding: 3px; color: white; border: solid 1px black">'+item.nilai.split("#")[0]+'</span>'+'<span style="background-color: #286090; padding: 3px; color: white; border: solid 1px black; margin-left: 1px">'+item.nilai.split("#")[1]+'</span>' + '</td>';
            } else {
                pBody = pBody + btn + tempTr + jam + '<td>' + '<span style="background-color: #449d44; padding: 3px; color: white; border: solid 1px black">'+item.nilai.split("#")[0]+'</span>'+'<span style="background-color: #286090; padding: 3px; color: white; border: solid 1px black; margin-left: 1px">'+item.nilai.split("#")[1]+'</span>' + '</td>';
            }

            if (i == parenteral.length - 1) {
                pBody = pBody + '<td align="center"><i onclick="conICU(\''+jenis +'\',\'asesmen_icu\', \''+item.id_detail_icu +'\')" style="color: red" class="fa fa-trash hvr-grow"></i></td>';
            }
        });
        if (pBody != '') {
            var tr = pBody.split("|");
            $.each(tr, function (ix, itemx) {
                body1 += '<tr>' + itemx + '</tr>';
            });
        }
        rbody1 = head1 + body1;
    }

    if (obat.length > 0) {
        dwr.engine.setAsync(false);
        IcuAction.getListHead(idDetailCheckup, "obat", null, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    thead2 += '<td>' + item.jenis + '</td>';
                });
            }
        });
        var pBody = "";
        var tempJam = "";
        head2 = '<tr style="font-weight: bold"><td>Obat-obatan</td></tr><tr>' + thead2 + '</tr>';
        $.each(obat, function (i, item) {
            var tanggal = item.tanggal;
            var tempTgl = "";
            if (i == 0) {
                tempTgl = tanggal;
            } else {
                var tgl = obat[i - 1]["tanggal"];
                var tglB = tgl;
                if (tanggal == tglB) {
                    tempTgl = "";
                } else {
                    tempTgl = tanggal;
                }
            }
            var tempTr = "";
            var jam = "";
            var btn = "";
            if (tempJam == "") {
                tempJam = item.jam;
                jam = '<td>' + tempTgl + '<span class="pull-right">' + item.jam + '</span></td>';
            } else {
                if (tempJam != item.jam) {
                    tempJam = item.jam;
                    tempTr = "|";
                    jam = '<td>' + tempTgl + '<span class="pull-right">' + item.jam + '</span></td>';
                    btn = '<td align="center"><i onclick="conICU(\''+jenis +'\',\'asesmen_icu\', \''+item.id_detail_icu +'\')" style="color: red" class="fa fa-trash hvr-grow"></i></td>';
                }
            }

            var isi = '<i onclick="showEditObat(\''+item.id_header_icu+'\',\''+item.id_detail_icu+'\')" style="color: #286090; font-size: 20px" class="fa fa-edit hvr-grow"></i>';
            if(item.nilai != '' && item.nilai != null && item.nilai != '#'){
                var isi = '<span style="background-color: #449d44; padding: 3px; color: white; border: solid 1px black">'+item.nilai.split("#")[0]+'</span>'+'<span style="background-color: #286090; padding: 3px; color: white; border: solid 1px black; margin-left: 1px">'+item.nilai.split("#")[1]+'</span>';
            }
            if (pBody == "") {
                pBody = jam + '<td>' + isi + '</td>';
            } else {
                pBody = pBody + tempTr + jam + '<td>' + isi + '</td>';
            }
        });
        if (pBody != '') {
            var tr = pBody.split("|");
            $.each(tr, function (ix, itemx) {
                body2 += '<tr>' + itemx + '</tr>';
            });
        }
        rbody2 = head2 + body2;
    }

    if (enteral.length > 0) {
        dwr.engine.setAsync(false);
        IcuAction.getListHead(idDetailCheckup, "enteral", null, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    thead3 += '<td>' + item.jenis + '</td>';
                });
            }
        });
        var pBody = "";
        var tempJam = "";
        head3 = '<tr style="font-weight: bold"><td>Enteral</td></tr><tr>' + thead3 + '<td width="5%" align="center">Action</td></tr>';
        $.each(enteral, function (i, item) {
            var tanggal = item.tanggal;
            var tempTgl = "";
            if (i == 0) {
                tempTgl = tanggal;
            } else {
                var tgl = enteral[i - 1]["tanggal"];
                var tglB = tgl;
                if (tanggal == tglB) {
                    tempTgl = "";
                } else {
                    tempTgl = tanggal;
                }
            }
            var tempTr = "";
            var jam = "";
            var btn = "";
            if (tempJam == "") {
                tempJam = item.jam;
                jam = '<td>' + tempTgl + '<span class="pull-right">' + item.jam + '</span></td>';
            } else {
                if (tempJam != item.jam) {
                    tempJam = item.jam;
                    tempTr = "|";
                    jam = '<td>' + tempTgl + '<span class="pull-right">' + item.jam + '</span></td>';
                    btn = '<td align="center"><i onclick="conICU(\''+jenis +'\',\'asesmen_icu\', \''+item.id_detail_icu +'\')" style="color: red" class="fa fa-trash hvr-grow"></i></td>';
                }
            }
            if (pBody == "") {
                pBody = jam + '<td>' + '<span style="background-color: #449d44; padding: 3px; color: white; border: solid 1px black">'+item.nilai.split("#")[0]+'</span>'+'<span style="background-color: #286090; padding: 3px; color: white; border: solid 1px black; margin-left: 1px">'+item.nilai.split("#")[1]+'</span>' + '</td>';
            } else {
                pBody = pBody + btn + tempTr + jam + '<td>' + '<span style="background-color: #449d44; padding: 3px; color: white; border: solid 1px black">'+item.nilai.split("#")[0]+'</span>'+'<span style="background-color: #286090; padding: 3px; color: white; border: solid 1px black; margin-left: 1px">'+item.nilai.split("#")[1]+'</span>' + '</td>';
            }

            if (i == enteral.length - 1) {
                pBody = pBody + '<td align="center"><i onclick="conICU(\''+jenis +'\',\'asesmen_icu\', \''+item.id_detail_icu +'\')" style="color: red" class="fa fa-trash hvr-grow"></i></td>';
            }
        });
        if (pBody != '') {
            var tr = pBody.split("|");
            $.each(tr, function (ix, itemx) {
                body3 += '<tr>' + itemx + '</tr>';
            });
        }
        rbody3 = head3 + body3;
    }

    if (output.length > 0) {
        dwr.engine.setAsync(false);
        IcuAction.getListHead(idDetailCheckup, "output", null, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    thead4 += '<td>' + item.jenis + '</td>';
                });
            }
        });
        var pBody = "";
        var tempJam = "";
        head4 = '<tr style="font-weight: bold"><td>Output</td></tr><tr>' + thead4 + '<td width="5%" align="center">Action</td></tr>';
        $.each(output, function (i, item) {
            var tanggal = item.tanggal;
            var tempTgl = "";
            if (i == 0) {
                tempTgl = tanggal;
            } else {
                var tgl = output[i - 1]["tanggal"];
                var tglB = tgl;
                if (tanggal == tglB) {
                    tempTgl = "";
                } else {
                    tempTgl = tanggal;
                }
            }
            var tempTr = "";
            var jam = "";
            var btn = "";
            if (tempJam == "") {
                tempJam = item.jam;
                jam = '<td>' + tempTgl + '<span class="pull-right">' + item.jam + '</span></td>';
            } else {
                if (tempJam != item.jam) {
                    tempJam = item.jam;
                    tempTr = "|";
                    jam = '<td>' + tempTgl + '<span class="pull-right">' + item.jam + '</span></td>';
                    btn = '<td align="center"><i onclick="conICU(\''+jenis +'\',\'asesmen_icu\', \''+item.id_detail_icu +'\')" style="color: red" class="fa fa-trash hvr-grow"></i></td>';
                }
            }
            if (pBody == "") {
                pBody = jam + '<td>' + '<span style="background-color: #449d44; padding: 3px; color: white; border: solid 1px black">'+item.nilai.split("#")[0]+'</span>'+'<span style="background-color: #286090; padding: 3px; color: white; border: solid 1px black; margin-left: 1px">'+item.nilai.split("#")[1]+'</span>' + '</td>';
            } else {
                pBody = pBody + btn + tempTr + jam + '<td>' + '<span style="background-color: #449d44; padding: 3px; color: white; border: solid 1px black">'+item.nilai.split("#")[0]+'</span>'+'<span style="background-color: #286090; padding: 3px; color: white; border: solid 1px black; margin-left: 1px">'+item.nilai.split("#")[1]+'</span>' + '</td>';
            }

            if (i == output.length - 1) {
                pBody = pBody + '<td align="center"><i onclick="conICU(\''+jenis +'\',\'asesmen_icu\', \''+item.id_detail_icu +'\')" style="color: red" class="fa fa-trash hvr-grow"></i></td>';
            }
        });
        if (pBody != '') {
            var tr = pBody.split("|");
            $.each(tr, function (ix, itemx) {
                body4 += '<tr>' + itemx + '</tr>';
            });
        }
        rbody4 = head4 + body4;
    }

    if (parenteral.length > 0 || obat.length > 0 || enteral.length > 0 || output.length > 0) {
        var table1 = "";
        var table2 = "";
        var table3 = "";
        var table4 = "";
        if (parenteral.length > 0) {
            table1 = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead><tr>' + head + '</tr></thead>' +
                '<tbody>' + rbody1 + '</tbody>' +
                '</table>';
        }
        if (obat.length > 0) {
            table2 = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead><tr>' + head + '</tr></thead>' +
                '<tbody>' + rbody2 + '</tbody>' +
                '</table>';
        }
        if (enteral.length > 0) {
            table3 = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead><tr>' + head + '</tr></thead>' +
                '<tbody>' + rbody3 + '</tbody>' +
                '</table>';
        }
        if (output.length > 0) {
            table4 = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead><tr>' + head + '</tr></thead>' +
                '<tbody>' + rbody4 + '</tbody>' +
                '</table>';
        }
        body = table1 + table2 + table3 + table4;
    } else {
        body = '<tr><td>Data belum ada</td></tr>';
    }

    var table = '<table style="font-size: 12px" class="table table-bordered">' +
        '<thead><tr>' + head + '</tr></thead>' +
        '<tbody>' + body + '</tbody>' +
        '</table>';

    var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
    newRow.insertAfter($('table').find('#row_icu_' + jenis));
    var url = contextPath + '/pages/images/minus-allnew.png';
    $('#btn_icu_' + jenis).attr('src', url);
    $('#btn_icu_' + jenis).attr('onclick', 'delRowICCU(\'' + jenis + '\')');
}

function searcHead(kategori) {
    var res = [];
    dwr.engine.setAsync(false);
    IcuAction.getListHead(idDetailCheckup, kategori, null, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                res.push({
                    'id_header_icu': item.idHeaderIcu,
                    'jenis': item.jenis
                });
            });
            return res;
        }
    });
}

function delRowICCU(id) {
    $('#del_icu_' + id).remove();
    var url = contextPath + '/pages/images/icons8-add-list-25.png';
    $('#btn_icu_' + id).attr('src', url);
    $('#btn_icu_' + id).attr('onclick', 'listInputan(\'' + id + '\')');
}

function checkSelect(id, pid, val) {

    if ($('#' + pid).is(':checked')) {
        $('#' + id).prop('checked', true);
        $('#' + pid).prop('checked', false);
    } else {
        $('#' + id).prop('checked', true);
    }

    if (val == "Ya" || val == "Dirawat dirumah") {
        $('#icu_' + id).show();
    } else {
        $('#icu_' + pid).hide();
    }
}

function saveCatatanTerintegrasiICU(jenis, ket) {
    var data = "";
    var va1 = $('#cp1').val();
    var va2 = $('#cp2').val();
    var va3 = $('#cp3').val();
    // var va4 = $('#cp4').val();
    var va5 = $('#cp5').val();
    var v6 = document.getElementById("cp6");
    var v7 = document.getElementById("cp7");
    var va6 = isBlank(v6);
    var va7 = isBlank(v7);

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

        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);
        CatatanTerintegrasiAction.saveCatatanTerintegrasi(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#modal-icu-' + jenis).modal('hide');
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-icu-' + jenis).scrollTop(0);
                    listCatatanTerintegrasiICU();
                } else {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#warning_icu_' + jenis).show().fadeOut(5000);
                    $('#msg_icu_' + jenis).text(res.msg);
                    $('#modal-icu-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#save_icu_' + jenis).show();
        $('#load_icu_' + jenis).hide();
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-icu-' + jenis).scrollTop(0);
    }
}

function listCatatanTerintegrasiICU(jenis) {
    var table = "";
    var body = "";
    var head = "";
    CatatanTerintegrasiAction.getListCatatanTerintegrasi(idDetailCheckup, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                body += '<tr>' +
                    '<td>' + converterDateTime(item.waktu) + '</td>' +
                    '<td>' + cekNull(item.ppa) + '</td>' +
                    '<td>' + cekNull(item.intruksi) + '</td>' +
                    '<td>' + '<img style="width: 100%; height: 20px" src="' + item.ttdPetugas + '">' + '</td>' +
                    '<td>' + '<img style="width: 100%; height: 20px" src="' + item.ttdDpjp + '">' + '</td>' +
                    '</tr>';
            });

            head = '<tr>\n' +
                '<td rowspan="2" style="vertical-align: middle">Tanggal dan Jam</td>\n' +
                '<td rowspan="2" style="vertical-align: middle">PPA</td>\n' +
                '<td colspan="1" style="vertical-align: middle" align="center">Hasil Pemeriksaan, Analisa, Rencana Penatalaksaan Pasien</td>\n' +
                '<td colspan="2" style="vertical-align: middle" align="center">Paraf/Nama</td>\n' +
                '</tr>\n' +
                '<tr>\n' +
                '<td align="center">Instruksi</td>\n' +
                '<td width="10%">Petugas</td>\n' +
                '<td width="10%">DPJP</td>\n' +
                '</tr>';
        } else {
            body = '<tr><td>Data belum ada</td></tr>'
        }
        var table = '<table style="font-size: 12px" class="table table-bordered">' +
            '<thead><tr>' + head + '</tr></thead>' +
            '<tbody>' + body + '</tbody>' +
            '</table>';

        var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
        newRow.insertAfter($('table').find('#row_icu_' + jenis));
        var url = contextPath + '/pages/images/minus-allnew.png';
        $('#btn_icu_' + jenis).attr('src', url);
        $('#btn_icu_' + jenis).attr('onclick', 'delRowICCU(\'' + jenis + '\')');
    });
}

function saveAsuhanKeperawatanICU(jenis, ket) {

    var data = [];
    var dataPasien = "";
    dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }

    var diagnosis = $('[name=diag]');
    var inter = $('[name=inter]');
    var imple = $('#tin').val();
    var param = $('[name=params]');

    var tgl = $('.tgl').val();
    var jam = $('.jam').val();

    var tempDiag = "";
    var tempInter = "";
    var tempImple = "";

    $.each(diagnosis, function (i, item) {
        var val = "";
        if (item.type == 'checkbox' || item.type == 'radio') {
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

    var subjektif = "";

    $.each(param, function (i, item) {

        var subjek = $('[name=subjek' + i + ']');
        var val = "";
        var sbj = "";

        $.each(subjek, function (idx, itm) {
            if (itm.type == 'checkbox') {
                if (itm.checked) {
                    if (val != '') {
                        val = val + ', ' + itm.value;
                    } else {
                        val = itm.value;
                    }
                }
            } else {
                if (itm.value != '') {
                    if (val != '') {
                        val = val + ', ' + itm.value;
                    } else {
                        val = itm.value;
                    }
                }
            }
        });

        if (val != '') {
            sbj = item.value + ' : ' + val;
            if (subjektif != '') {
                subjektif = subjektif + '|' + sbj;
            } else {
                if (diagnosis != '') {
                    subjektif = '|' + sbj;
                } else {
                    subjektif = sbj;
                }
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
        if (tempImple != '') {
            tempImple = tempImple + '|' + item;
        } else {
            tempImple = item;
        }
    });

    var ttdPerawat = document.getElementById("asuhan_perawat");
    var ttdDpjp = document.getElementById("asuhan_dpjp");
    var nama1 = $('#nama_asuhan_perawat').val();
    var nama2 = $('#nama_asuhan_dpjp').val();
    var sip2 = $('#sip_asuhan_dpjp').val();

    var cekTtd1 = isCanvasBlank(ttdPerawat);
    var cekTtd2 = isCanvasBlank(ttdDpjp);

    if (tgl && jam && tempDiag && tempInter && tempImple && subjektif && nama1 && nama2 && sip2 != '' && !cekTtd1 && !cekTtd2) {

        var cvs1 = convertToDataURL(ttdPerawat);
        var cvs2 = convertToDataURL(ttdDpjp);

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': tgl + ' ' + jam,
            'diagnosa': tempDiag + subjektif,
            'hasil': '',
            'intervensi': tempInter,
            'implementasi': tempImple,
            'evaluasi': '',
            'keterangan': jenis,
            'ttd_perawat': cvs1,
            'ttd_dpjp': cvs2,
            'nama_terang': nama1,
            'nama_dokter': nama2,
            'sip': sip2,
        }

        var result = JSON.stringify(data);
        var pasienData = JSON.stringify(dataPasien);

        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);
        RencanaAsuhanKeperawatanAction.save(result, pasienData, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#modal-icu-' + jenis).modal('hide');
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-icu-' + jenis).scrollTop(0);
                    listAsuhanKeperawatanICU(ket);
                } else {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#warning_icu_' + jenis).show().fadeOut(5000);
                    $('#msg_icu_' + jenis).text(res.msg);
                    $('#modal-icu-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#save_icu_' + jenis).show();
        $('#load_icu_' + jenis).hide();
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-icu-' + jenis).scrollTop(0);
    }
}

function listAsuhanKeperawatanICU(jenis) {
    var table = "";
    var head = '';
    var body = '';
    var cekData = false;
    RencanaAsuhanKeperawatanAction.getListDetail(idDetailCheckup, jenis, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {

                var diagnosa = "";
                var intervensi = "";
                var implementasi = "";

                var ul1 = "";
                var ul2 = "";
                var ul3 = "";

                if (item.diagnosa != null) {
                    var v = item.diagnosa.split("|");
                    $.each(v, function (i, item) {
                        diagnosa += '<li>' + item + '</li>';
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

                if (diagnosa != '') {
                    ul1 = '<ul style="margin-left: 12px">' + diagnosa + '</ul>'
                }
                if (intervensi != '') {
                    ul2 = '<ul style="margin-left: 12px">' + intervensi + '</ul>'
                }
                if (implementasi != '') {
                    ul3 = '<ul style="margin-left: 12px">' + implementasi + '</ul>'
                }

                body += '<tr>' +
                    '<td>' + item.waktu + '</td>' +
                    '<td>' + ul1 + '</td>' +
                    '<td>' + ul2 + '</td>' +
                    '<td>' + ul3 + '</td>' +
                    '</tr>';
                cekData = true;
            });
        } else {
            body = '<tr><td>Data belum ada</td></tr>';
        }

        if (cekData) {
            head = '<tr>\n' +
                '<td width="10%" style="vertical-align: middle" align="center">Tanggal Jam</td>\n' +
                '<td width="20%" style="vertical-align: middle" align="center">Diagnosa Keperawatan</td>\n' +
                '<td width="20%" style="vertical-align: middle" align="center">Planning/ Rencana Tindakan</td>\n' +
                '<td width="20%" style="vertical-align: middle" align="center">Tindakan</td>\n' +
                '</tr>';
        }

        var table = '<table style="font-size: 12px" class="table table-bordered">' +
            '<thead><tr>' + head + '</tr></thead>' +
            '<tbody>' + body + '</tbody>' +
            '</table>';

        var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
        newRow.insertAfter($('table').find('#row_icu_' + jenis));
        var url = contextPath + '/pages/images/minus-allnew.png';
        $('#btn_icu_' + jenis).attr('src', url);
        $('#btn_icu_' + jenis).attr('onclick', 'delRowAsuhan(\'' + jenis + '\')');
    });
}

function delRowAsuhan(id) {
    $('#del_icu_' + id).remove();
    var url = contextPath + '/pages/images/icons8-add-list-25.png';
    $('#btn_icu_' + id).attr('src', url);
    $('#btn_icu_' + id).attr('onclick', 'listAsuhanKeperawatanICU(\'' + id + '\')');
}

function pilihTindakanICU(val) {
    if (val != '') {
        $('#form-tindakan-icu').show();
        $('#icu8').val(val);
        var body = "";
        $.each(tindakanICU(val), function (i, item) {

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

        $('#body_tindakan_icu').html(body);

    } else {
        $('#form-tindakan-icu').hide();
        $('#icu8').val('');
        $('#body_tindakan_icu').html('');
    }
}

function tindakanICU(jenis) {

    var data = [];
    var dataCari = [];

    data.push({
        'jenis': 'Ventilator',
        'parameter': 'Pengertian',
        'informasi': 'Pemberian alat bantu nafas (mekanik) yang memberikan bantuan nafas dengan cara mebantu sebagian atau mengambil alih semua fungsi ventilasi',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Ventilator',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Cedera otak berat, Post operasi trepanasi, Potensial terjadi gangguan airway dan breathing, Hypoxia (Sesak, RR > 35, Nafas Cuping hidung, Adanya gerak nafas tambahan retraksi kalau berat tampak cyanosis)',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Ventilator',
        'parameter': 'Tindakan Medis',
        'informasi': 'Intubasi, Ventilator(Setting mode)',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Ventilator',
        'parameter': 'Tata Cara',
        'informasi': 'Setting mode ventilator, koneksikan tubing ventilator ke ETT',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Ventilator',
        'parameter': 'Tujuan',
        'informasi': 'Pemenuhan kebutuhan Oksigen (oksigenasi)',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Ventilator',
        'parameter': 'Resiko',
        'informasi': 'Pneumo thoraks, Hypotensi',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Ventilator',
        'parameter': 'Komplikasi',
        'informasi': 'VAP, pneumothorax, barrotrauma',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Ventilator',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'Ventilator',
        'parameter': 'Tindakan Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'Ventilator',
        'parameter': 'Biaya*',
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

function conICU(jenis, ket, idAsesmen, tipe) {
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show: true, backdrop: 'static'});
    if (idAsesmen != undefined && idAsesmen != '') {
        $('#save_con_rm').attr('onclick', 'delICUS(\'' + jenis + '\', \'' + ket + '\', \'' + idAsesmen + '\', \'' + tipe + '\')');
    } else {
        $('#save_con_rm').attr('onclick', 'delICU(\'' + jenis + '\', \'' + ket + '\')');
    }
}

function delICUS(jenis, ket, idAsesmen, tipe) {
    $('#modal-confirm-rm').modal('hide');
    if(!cekSession()){
        var dataPasien = {
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'id_pasien': idPasien,
            'id_rm': tempidRm
        }
        var result = JSON.stringify(dataPasien);
        startIconSpin('delete_' + idAsesmen);
        dwr.engine.setAsync(true);
        IcuAction.saveDelete(idAsesmen, tipe, {
            callback: function (res) {
                if (res.status == "success") {
                    stopIconSpin('delete_' + idAsesmen);
                    $('#modal-icu-' + ket).scrollTop(0);
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menghapus data...");
                } else {
                    stopIconSpin('delete_' + idAsesmen);
                    $('#modal-icu-' + ket).scrollTop(0);
                    $('#warn_' + ket).show().fadeOut(5000);
                    $('#msg_' + ket).text(res.msg);
                }
            }
        });
    }
}

function delICU(jenis, ket) {
    if(!cekSession()){
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
        AsesmenIcuAction.saveDelete(idDetailCheckup, jenis, result, {
            callback: function (res) {
                if (res.status == "success") {
                    stopSpin('delete_' + jenis);
                    $('#modal-icu-' + ket).scrollTop(0);
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menghapus data...");
                } else {
                    stopSpin('delete_' + jenis);
                    $('#modal-icu-' + ket).scrollTop(0);
                    $('#warn_' + ket).show().fadeOut(5000);
                    $('#msg_' + ket).text(res.msg);
                }
            }
        });
    }
}

function setCustomeJenis(idSet, jenis) {
    var set = "";
    if (jenis == 'loading' || jenis == 'darah' || jenis == 'cairan') {
        var id = "";
        var count = "";
        var pc1 = "";
        var pc2 = "";
        if (jenis == 'loading') {
            count = $('.jenis_loading').length;
            id = 'loading' + count;
            pc1 = 'jenis_loading';
            pc2 = 'Loading';
            pc3 = 'nilai_loading';
        }
        if (jenis == 'darah') {
            count = $('.jenis_darah').length;
            id = 'darah' + count;
            pc1 = 'jenis_darah';
            pc2 = 'Darah';
            pc3 = 'nilai_darah';
        }
        if (jenis == 'cairan') {
            count = $('.jenis_cairan').length;
            id = 'cairan' + count;
            pc1 = 'jenis_cairan';
            pc2 = 'Cairan';
            pc3 = 'nilai_cairan';
        }

        set = '<div class="row jarak" id="' + id + '">\n' +
            '<div class="col-md-offset-3 col-md-5">\n' +
            '    <input class="form-control ' + pc1 + '" placeholder="Jenis ' + pc2 + '">\n' +
            '</div>\n' +
            '<div class="col-md-3">\n' +
            '    <input class="form-control ' + pc3 + '" placeholder="Nilai ' + pc2 + '" type="number">\n' +
            '</div>\n' +
            '<div class="col-md-1">\n' +
            '    <button style="margin-top:2px; margin-left: -20px" onclick="delJenis(\'' + id + '\')" class="btn btn-danger"><i class="fa fa-trash"></i></button>\n' +
            '</div>\n' +
            '</div>';
    }
    if (jenis == 'injeksi' || jenis == 'oral' || jenis == 'lainnya') {
        var id = "";
        var count = "";
        var pc1 = "";
        var pc2 = "";
        var pc3 = "";
        if (jenis == 'injeksi') {
            count = $('.jenis_injeksi').length;
            id = 'injeksi' + count;
            pc1 = 'jenis_injeksi';
            pc2 = 'kali_injeksi';
            pc3 = 'nilai_injeksi';
        }
        if (jenis == 'oral') {
            count = $('.jenis_oral').length;
            id = 'oral' + count;
            pc1 = 'jenis_oral';
            pc2 = 'kali_oral';
            pc3 = 'nilai_oral';
        }
        if (jenis == 'lainnya') {
            count = $('.jenis_lainnya').length;
            id = 'lainnya' + count;
            pc1 = 'jenis_lainnya';
            pc2 = 'kali_lainnya';
            pc3 = 'nilai_lainnya';
        }

        set = '<div class="row jarak" id="' + id + '">\n' +
            '<div class="col-md-offset-3 col-md-4">\n' +
            '    <input class="form-control ' + pc1 + '" placeholder="Jenis ' + jenis + '">\n' +
            '</div>\n' +
            '<div class="col-md-2">\n' +
            '    <input class="form-control ' + pc2 + '" data-inputmask="\'mask\': [\'9x9\']"\n' +
            '           data-mask="">\n' +
            '</div>\n' +
            '<div class="col-md-2">\n' +
            '    <input class="form-control ' + pc3 + '" type="number" placeholder="Nilai">\n' +
            '</div>\n' +
            '<div class="col-md-1">\n' +
            '    <button style="margin-top:2px; margin-left: -20px" onclick="delJenis(\'' + id + '\')" class="btn btn-danger"><i class="fa fa-trash"></i></button>\n' +
            '</div>\n' +
            '</div>';
    }
    $('#' + idSet).append(set);
    $('[data-mask]').inputmask();
}

function delJenis(id) {
    $('#' + id).remove();
}

function showEditObat(idHead, idDetail){
    $('#save_icu_edit_obat').attr('onclick', 'saveEditObat(\''+idHead+'\',\''+idDetail+'\')');
    $('#modal-icu-edit-obat').modal({show:true, backdrop:'static'});
}

function saveEditObat(idHead, idDetail){
    if(!cekSession()){
        var nilai = $('#obat_nilai').val();
        if(nilai != ''){
            IcuAction.editObat(idHead, idDetail, nilai, function (res) {
                if(res.status == 'success'){
                    $('#modal-icu-edit-obat').modal('hide');
                    $('#warning_icu_asesmen_icu').show().fadeOut(5000);
                    $('#msg_icu_asesmen_icu').text("Berhasil mengupdate data obat-obatan...!");
                    $('#del_icu_keseimbangan_icu').remove();
                    var url = contextPath + '/pages/images/icons8-add-list-25.png';
                    $('#btn_icu_keseimbangan_icu').attr('src', url);
                    $('#btn_icu_keseimbangan_icu').attr('onclick', 'listInputan(\'keseimbangan_icu\')');
                }else{
                    $('#warning_icu_edit_obat').show().fadeOut(5000);
                    $('#msg_icu_edit_obat').text(res.msg);
                }
            });
        }else{
            $('#warning_icu_edit_obat').show().fadeOut(5000);
            $('#msg_icu_edit_obat').text("Silahkan cek kembali inputan nilai...!");
        }
    }
}






