function showModalICU(jenis) {
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }

    setDataPasien();

    if ("hemodinamika" == jenis) {
        listHemodinamika(jenis);
    }
    if ("respirasi" == jenis) {
        listRespirasi(jenis);
    }

    if ("respirasi_icu" == jenis) {
        setHeadRespirasi();
    }

    if ("keseimbangan_icu" == jenis ||
        "injeksi_icu" == jenis ||
        "oral_icu" == jenis ||
        "lain_icu" == jenis ||
        "intakea_icu" == jenis ||
        "output_icu" == jenis) {

        setInputan(jenis);
    }
    if ("keseimbangan" == jenis) {
        listInputan(jenis);
    }

    if ("checklist_kriteria" == jenis) {
        setInputan(jenis);
    }

    if("asuhan_keperawatan" == jenis){
        listAsuhanKeperawatanICU(jenis);
    }
    $('#modal-icu-' + jenis).modal({show: true, backdrop: 'static'});
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
    var tgl = $('#tgl_'+jenis).val();
    var jam = $('#jam_'+jenis).val();

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
        if (v4.length > 0) {
            $.each(v4, function (i, item) {
                if (va4 != '') {
                    va4 = va4 + '|' + item;
                } else {
                    va4 = item;
                }
            });
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

        if(data.length > 0 && !cekTtd1 && !cekTtd2){

            var canv1 = dpjp.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            var canv2 = pasien.toDataURL("image/png"),
                canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD DPJP',
                'jawaban': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Pasien/Keluarga',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if("pengkajian_medik" == jenis){
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

        var cekTtd1 = isCanvasBlank(dokter);

        $.each(v5, function (i, item) {
            if(item.checked){
                if(va5 != ''){
                    va5 = va5 + ', '+item.value;
                }else{
                    va5 = item.value;
                }
            }
        });

        $.each(v6, function (i, item) {
            if(item.checked){
                if(va6 != ''){
                    va6 = va6 + ', '+item.value;
                }else{
                    va6 = item.value;
                }
            }
        });

        $.each(v8, function (i, item) {
            if(item.checked){
                if(va8 != ''){
                    va8 = va8 + ', '+item.value;
                }else{
                    va8 = item.value;
                }
            }
        });

        $.each(v9, function (i, item) {
            if(item.checked){
                if(va9 != ''){
                    va9 = va9 + ', '+item.value;
                }else{
                    va9 = item.value;
                }
            }
        });

        $.each(v10, function (i, item) {
            if(item.checked){
                if(va10 != ''){
                    va10 = va10 + ', '+item.value;
                }else{
                    va10 = item.value;
                }
            }
        });

        if(va1 && va2 && va3 && va5 && va6 && va8 && va9 && va10 && va11 && va12 != '' && !cekTtd1 && va7 != undefined){
            data.push({
                'parameter': 'Tanggal Jam',
                'jawaban': tgl+' '+jam,
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
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;

        }

    }

    if("pengkajian_keperawatan" == jenis){

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

        var cekTtd1 = isCanvasBlank(dokter);

        if(v1 != undefined){
            var ket = $('#ket_pk11').val();
            if("Ya" == v1){
                va1 = v1+', '+ket;
            }else{
                va1 = v1;
            }
        }

        var isiVa31 = "";
        var isiVa32 = "";
        var isiVa33 = "";

        if(v02 != undefined){
            var ket = $('#ket_pk22').val();
            if("Ya" == v1){
                isiVa31 = v02+', '+ket;
            }else{
                isiVa31 = v02;
            }
        }

        if(v03 != undefined){
            var ket = $('#ket_pk32').val();
            if("Ya" == v03){
                isiVa31 = v03+', '+ket;
            }else{
                isiVa31 = v03;
            }
        }

        if(v04 != undefined){
            var ket = $('#ket_pk42').val();
            if("Ya" == v1){
                isiVa33 = v04+', '+ket;
            }else{
                isiVa33 = v04;
            }
        }

        if(v2 != undefined){
            va3 = v2+', '+isiVa31+'. ';
        }
        if(v3 != undefined){
            va3 = va3 + v3+', '+isiVa32+'. ';
        }
        if(v4 != undefined){
            va3 = va3 + v3+', '+isiVa33+'. ';
        }

        if(v5 != undefined){

            var ket1 = $('#ket_pk51').val();
            var ket2 = $('#ket_pk52').val();
            var ket3 = $('#ket_pk53').val();
            var ket4 = $('#ket_pk54').val();
            var ket5 = $('#ket_pk55').val();

            if("Ya" == v1){
                va5 = v5+', Nama '+ket1+', Dimana '+ket2+', Hubungan dengan pasien '+ket3+', No. Telepon/HP '+ket4;
            }else{
                va5 = v5;
            }
        }

        $.each(v10, function (i, item) {
            if(item.checked){
                if(va10 != ''){
                    va10 = va10 + ', '+item.value;
                }else{
                    va10 = item.value;
                }
            }
        });

        $.each(v11, function (i, item) {
            if(item.checked){
                if(va11 != ''){
                    va11 = va11 + ', '+item.value;
                }else{
                    va11 = item.value;
                }
            }
        });



        if(jam && tgl && va1 && va3 && va5 != '' && !cekTtd1){

            data.push({
                'parameter': 'Tanggal Jam',
                'jawaban': tgl+' '+jam,
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

            if(v7 != undefined){
                data.push({
                    'parameter': 'Apakah lingkungan rumah sudah disiapkan',
                    'jawaban': v7,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if(v8 != undefined){
                data.push({
                    'parameter': 'Jika ya, apakah anda mampu merawat pasien dirumah',
                    'jawaban': v8,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if(v9 != undefined){
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
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }

    }

    if("terminal" == jenis){

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

        var dokter = document.getElementById("ter21");

        var cekTtd1 = isCanvasBlank(dokter);

        $.each(temp24, function (i, item) {
            if(item.checked){
                if(va24 != ''){
                    va24 = va24 + ', '+item.value;
                }else{
                    va24 = item.value;
                }
            }
        });

        if(va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 &&va10 != '' && !cekTtd1){

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
                'jawaban': va8+', Nadi '+va9+', Suhu '+va10+', RR '+va11+', BB '+va12+', TB '+va13+', GCS '+va14,
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

            if(va20 != undefined){
                data.push({
                    'parameter': 'Relaksasi Otot',
                    'jawaban': va20,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if(va21 != undefined){
                data.push({
                    'parameter': 'Mata / Refrek cahaya',
                    'jawaban': va21,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if(va22 != undefined){
                data.push({
                    'parameter': 'Berbicara',
                    'jawaban': va22,
                    'keterangan': jenis,
                    'jenis': ket,
                    'id_detail_checkup': idDetailCheckup
                });
            }
            if(va23 != undefined){
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
            }); data.push({
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
                'tipe':'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if("add_tindakan_icu" == jenis){
        var va1 = $('#icu1').val();
        var va2 = $('#icu2').val();
        var va3 = $('#icu3').val();
        var va4 = $('#icu4').val();
        var va5 = $('#icu5').val();
        var va6 = $('#icu6').val();
        var va7 = $('#icu7').val();
        var va8 = $('#icu8').val();
        var va9 = $('#icu9').val();
        var va10= $('#icu10').val();
        var va11 = $('#icu11').val();

        var tindakan = $('#tindakan_icu').val();
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
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerangkan hal-hal di atas secara benar dan jelas dengan memberikan kesempatakan bertanya dan atau diskusi kepada pasien dan/atau keluarganya sedemikian rupa sehingga telah memahaminya',
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
                'jawaban': 'Biaya adalah perkiraan biaya yang harus dibayarkan oleh pihak pasien erdasarkan perkiraan dalam kasus-kasus sewajarnya dan tidak mengikat kedua belah pihak apabila ada perluasan',
                'keterangan': ket,
                'jenis': tindakan,
                'tipe':'colspan',
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
        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);
        AsesmenIcuAction.save(result, {
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
        })
    } else {
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-icu-' + jenis).scrollTop(0);
    }
}

function detailICU(jenis) {
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

                    }else if("tindakan_icu" == jenis){
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
                        if("checklist_kriteria" == jenis){
                            if ("ttd" == item.tipe) {
                                forTTD += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td colspan="2">' + '<img src="' + item.jawaban + '" style="height: 80px">' + '</td>' +
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

function delRowICU(id) {
    $('#del_icu_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_icu_' + id).attr('src', url);
    $('#btn_icu_' + id).attr('onclick', 'detailICU(\'' + id + '\')');
}

function saveHemodinamika(jenis, ket) {

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
    var data = "";

    if (va1 != '') {
        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1,
            'tensi': va2,
            'bp': va3,
            'hi': va4,
            'rr': va5,
            'ekg': va6,
            'icp': va7,
            'ibp': va8,
            'cvp': va9,
            'map': va10,
            'keterangan': ket
        };
        var result = JSON.stringify(data);
        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);
        HemodinamikaAction.save(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#modal-icu-' + jenis).modal('hide');
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                    $('#modal-icu-' + jenis).scrollTop(0);
                    listHemodinamika(ket);
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

function showChartHemodinamika(jenis, tgl) {
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
                    ekg = item.ekg;
                }

                dataArray.push({
                    y: item.waktu,
                    a: item.tensi,
                    b: item.bp,
                    c: item.hi,
                    d: item.rr,
                    e: item.icp,
                    f: item.ibp,
                    g: item.cvp,
                    h: item.map
                });

                var jam = item.waktu.split(":")[0];

                if (temJam != jam) {
                    temJam = jam;
                    thEkg.push({'jam': jam});
                }

                var tempTgl = "";
                var btn = "";

                if (i == 0) {
                    tempEkg = parseInt(tempEkg) + parseInt(ekg);
                } else {
                    var jm = res[i - 1]["waktu"];
                    var jamB = jm.split(":")[0];

                    if (jam == jamB) {
                        tempEkg = parseInt(tempEkg) + parseInt(ekg);
                    } else {
                        tdEkg.push({'ekg': tempEkg});
                        tempEkg = ekg;
                    }
                }

                if (i == res.length - 1) {
                    tdEkg.push({'ekg': tempEkg});
                }

            });

            $('#modal-icu-chart_' + jenis).on('shown.bs.modal', function (event) {
                $('#line-chart_hemodinamika').empty();
                var line = new Morris.Line({
                    element: 'line-chart_hemodinamika',
                    resize: true,
                    data: dataArray,
                    xkey: 'y',
                    ykeys: ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'],
                    labels: ['Tensi', 'BP', 'HI', 'RR', 'ICP', 'IBP', 'CVP', 'MAP'],
                    lineColors: ['#ff0000', '#0000ff', '#00cc00', '#ff9933', '#cc6600', '#ffff66', '#cc6699', '#666633'],
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

function listHemodinamika(jenis) {
    HemodinamikaAction.getListDetail(idDetailCheckup, jenis, null, function (res) {
        if (res.length > 0) {
            var body = "";
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
                    '<td>' + cekItemIsNull(item.bp) + '</td>' +
                    '<td>' + cekItemIsNull(item.hi) + '</td>' +
                    '<td>' + cekItemIsNull(item.rr) + '</td>' +
                    '<td>' + cekItemIsNull(item.ekg) + '</td>' +
                    '<td>' + cekItemIsNull(item.icp) + '</td>' +
                    '<td>' + cekItemIsNull(item.ibp) + '</td>' +
                    '<td>' + cekItemIsNull(item.cvp) + '</td>' +
                    '<td>' + cekItemIsNull(item.map) + '</td>' +
                    '</tr>';
            });
            $('#body_hemodinamika').html(body);
        }
    });
}

function saveRespirasi(jenis, ket) {

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
    var va11 = $('#id11').val();
    var va12 = $('#id12').val();
    var va13 = $('#id13').val();
    var va14 = $('#id14').val();
    var va014 = $('#id014').val();
    var va15 = $('#id15').val();
    var va16 = $('#id16').val();
    var va17 = $('#id17').val();
    var va18 = $('#id18').val();
    var va19 = $('#id19').val();
    var va20 = $('#id20').val();
    var va21 = $('#id21').val();
    var va22 = $('#id22').val();
    var va23 = $('#id23').val();
    var va24 = $('#id24').val();
    var va25 = $('#id25').val();
    var va26 = $('#id26').val();
    var va27 = $('#id27').val();
    var va28 = $('#id28').val();
    var va29 = $('#id29').val();
    var va30 = $('#id30').val();
    var va31 = $('#id31').val();
    var va32 = $('#id32').val();
    var va33 = $('#id33').val();
    var va34 = $('#id34').val();

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
            'jenis_nk_rm_nrm': va13,
            'nk_rm_nrm': va014,
            'jenis_t_piece_j_rise': va14,
            't_piece_j_rise': va15,
            'tipe_ventilasi': va16,
            'jenis_peep_cpap_et': va17,
            'peep_cpap_et': va18,
            'frekwensi': va19,
            'tv': va20,
            'mv': va21,
            'jenis_p_support_p_asb': va22,
            'p_support_p_asb': va23,
            'jenis_p_ins_p_con': va24,
            'p_ins_p_con': va25,
            'triger': va26,
            'ins_time': va27,
            'flow': va28,
            'jenis_fio_kon': va29,
            'fio_kon': va30,
            'jenis_ukuran_kedalamaan_ett': va31,
            'ukuran_kedalamaan_ett': va32,
            'spo': va33,
            'secret': va34,
            'keterangan': ket
        };
        var result = JSON.stringify(data);
        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);
        RespirasiAction.save(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#modal-icu-' + jenis).modal('hide');
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                    $('#modal-icu-' + jenis).scrollTop(0);
                    listRespirasi(ket);
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

function listRespirasi(jenis) {
    RespirasiAction.getListDetail(idDetailCheckup, jenis, function (res) {
        if (res.length > 0) {
            var body = "";
            var valueRes = res[0];
            $('#head1').text(valueRes.jenisNkRmNrm);
            $('#head2').text(valueRes.jenisTPieceJRise.substring(0, 2));
            $('#head3').text(valueRes.jenisPeepCpapEt.substring(0, 2));
            $('#head4').text(valueRes.jenisPSupportPAsb.substring(0, 2));
            $('#head5').text(valueRes.jenisPInsPCon.substring(0, 2));
            $('#head6').text(valueRes.jenisFioKon.substring(0, 2).toUpperCase());
            $('#head7').text(valueRes.jenisUkuranKedalamaanEtt.substring(0, 2).toUpperCase());

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
                    '<td>' + cekItemIsNull(item.nkRmNrm) + '</td>' +
                    '<td>' + cekItemIsNull(item.tPieceJRise) + '</td>' +
                    '<td>' + cekItemIsNull(item.tipeVentilasi) + '</td>' +
                    '<td>' + cekItemIsNull(item.peepCpapEt) + '</td>' +
                    '<td>' + cekItemIsNull(item.frekwensi) + '</td>' +
                    '<td>' + cekItemIsNull(item.tv) + '</td>' +
                    '<td>' + cekItemIsNull(item.mv) + '</td>' +
                    '<td>' + cekItemIsNull(item.pSupportPAsb) + '</td>' +
                    '<td>' + cekItemIsNull(item.pInsPCon) + '</td>' +
                    '<td>' + cekItemIsNull(item.triger) + '</td>' +
                    '<td>' + cekItemIsNull(item.insTime) + '</td>' +
                    '<td>' + cekItemIsNull(item.flow) + '</td>' +
                    '<td>' + cekItemIsNull(item.fioKon) + '</td>' +
                    '<td>' + cekItemIsNull(item.ukuranKedalamaanEtt) + '</td>' +
                    '<td>' + cekItemIsNull(item.spo) + '</td>' +
                    '<td>' + cekItemIsNull(item.secret) + '</td>' +
                    '</tr>';
            });
            $('#body_respirasi').html(body);
        }
    });
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
        dataBody.push({'param': 'e. ', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '2'});
        dataBody.push({'param': 'f. Aritmia Jantung', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '2'});
        dataBody.push({'param': 'g. Astma Jantung', 'ket1': 'Ya', 'ket2': 'Tidak', 'skor': '2'});
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
        IcuAction.getListDetailParameter(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {

                $.each(res, function (i, item) {
                    inputan += '<div class="row">' +
                        '<div class="form-group">\n' +
                        '    <label class="col-md-3 jarak">' + item.jenis + '</label>\n' +
                        '<input value="' + item.idHeaderIcu + '" type="hidden" class="data-label' + jenis + '">' +
                        '    <div class="col-md-9">\n' +
                        '       <input class="form-control jarak nilai' + jenis + '" type="number">\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '</div>';
                });

                $('#inpt_' + jenis).html(inputan);
                $('#resus').html('');
                $('#darah').html('');
                $('#infus').html('');
                $('#' + jenis).html('');
                $('#select_isi').html('');
                $('#is_new').val(false);

            } else {

                if ("keseimbangan_icu") {
                    var isi = '<div class="row">\n' +
                        '<div class="form-group">\n' +
                        '    <label class="col-md-3" style="margin-top: 7px">Pengisian</label>\n' +
                        '    <div class="col-md-9">\n' +
                        '        <select class="form-control select2 " id="jensis_pengisian" style="width: 100%" onchange="pilIsi(\'' + jenis + '\', this.value)">\n' +
                        '            <option value="">[Select One]</option>\n' +
                        '            <option value="rc">Resusitasi Cairan</option>\n' +
                        '            <option value="in">Infus</option>\n' +
                        '        </select>\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '</div>';
                    $('#select_isi').html(isi);
                }

                var label = "";
                if ("injeksi_icu" == jenis) {
                    label = "Injeksi";
                }
                if ("oral_icu" == jenis) {
                    label = "Oral";
                }
                if ("lain_icu" == jenis) {
                    label = "Lain-Lain";
                }

                if ("intakea_icu" == jenis) {
                    parameter.push({'params': 'NGT/ Oral'});
                    parameter.push({'params': 'Minum'});
                }
                if ("output_icu" == jenis) {
                    parameter.push({'params': 'Drain 1'});
                    parameter.push({'params': 'Drain 2'});
                    parameter.push({'params': 'Urin'});
                    parameter.push({'params': 'Muntah'});
                    parameter.push({'params': 'BAB'});
                    parameter.push({'params': 'IWL'});
                    parameter.push({'params': 'Stoma'});
                }

                obat = '<hr class="garis">' +
                    '<div class="row">\n' +
                    '<div class="form-group">\n' +
                    '    <div class="col-md-offset-3 col-md-1">\n' +
                    '        <button class="btn btn-success" onclick="addInputan(\'' + jenis + '\', \'obat\')"><i class="fa fa-plus"></i> Tambah</button>\n' +
                    '    </div>\n' +
                    '</div>\n' +
                    '</div>\n';

                if (parameter.length > 0) {

                    $.each(parameter, function (i, item) {
                        obat +=
                            '<div class="row jarak" id="res' + i + '">\n' +
                            '<div class="form-group">\n' +
                            '    <label class="col-md-3 jarak">' + item.params + '</label>\n' +
                            '<input type="hidden" value="' + item.params + '" class="data-label' + jenis + ' obat' + jenis + '">\n' +
                            '    <div class="col-md-9">\n' +
                            '       <input class="form-control jarak nilai' + jenis + '" type="number">\n' +
                            '    </div>\n' +
                            '</div>\n' +
                            '</div>'
                    });

                } else {
                    obat = obat +
                        '<div class="row jarak" id="res1">\n' +
                        '<div class="form-group">\n' +
                        '    <label class="col-md-3 jarak">' + label + '</label>\n' +
                        '    <div class="col-md-4">\n' +
                        '<input class="form-control jarak data-label' + jenis + ' obat' + jenis + '" placeholder="Keterangan">\n' +
                        '    </div>\n' +
                        '    <label class="col-md-1 jarak">Nilai</label>\n' +
                        '    <div class="col-md-3">\n' +
                        '       <input class="form-control jarak nilai' + jenis + '" type="number">\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '</div>';
                }

                if ("keseimbangan_icu" == jenis) {
                    $('#resus').html(resus);
                    $('#darah').html(darah);
                    $('#infus').html(infus);
                } else {
                    $('#' + jenis).html(obat);
                }

                $('#inpt_' + jenis).html('');
                $('#is_new').val(true);
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

    var isNew = $('#is_new').val();
    var data = [];
    var label = $('.data-label' + jenis);
    var nilai = $('.nilai' + jenis);
    var waktu = $('#waktu_' + jenis).val();

    if (waktu != '') {
        $.each(label, function (i, item) {
            if (isNew == "true") {
                if (label.value != "" && nilai[i].value != "") {
                    data.push({
                        'id_detail_checkup': idDetailCheckup,
                        'waktu': waktu,
                        'jenis': item.value,
                        'nilai': nilai[i].value,
                        'kategori': jenis
                    });
                }
            } else {
                if (label.value != "" && nilai[i].value != "") {
                    data.push({
                        'id_detail_checkup': idDetailCheckup,
                        'id_header_icu': item.value,
                        'waktu': waktu,
                        'nilai': nilai[i].value,
                        'kategori': jenis
                    });
                }
            }
        });

        var result = JSON.stringify(data);
        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);

        IcuAction.save(result, isNew, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#modal-icu-' + jenis).modal('hide');
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                    $('#modal-icu-' + jenis).scrollTop(0);
                    if ("keseimbangan_icu" == jenis) {
                        listInputan(ket);
                    }
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
    IcuAction.getListDetail(idDetailCheckup, jenis + '_icu', function (res) {
        if (res.length > 0) {

            var tempHead = [];
            var tempBody = [];
            var tempJam = "";
            var head = "<td width='14%'>Tanggal Jam</td>";
            var body = "";

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

                if (tempJam == "") {
                    tempJam = item.waktu + tanggal;
                    tempHead.push({'jenis': item.jenis})
                } else {
                    if (tempJam == item.waktu + tanggal) {
                        tempHead.push({'jenis': item.jenis});
                    }
                }

                tempBody.push({'tanggal': tempTgl, 'jam': item.waktu, 'nilai': item.nilai});
            });

            if (tempHead.length > 0) {
                $.each(tempHead, function (i, item) {
                    head += '<td>' + item.jenis + '</td>'
                });
            }

            if (tempBody.length > 0) {
                var tempJm = "";
                var tempJml = 0;
                var tempTotal = 0;
                $.each(tempBody, function (i, item) {

                    var jam = item.jam;
                    var nilai = item.nilai;
                    var tgl = item.tanggal;

                    var temTR = "";
                    var temJM = "";
                    var jml = "";
                    var last = "";

                    tempJml = parseInt(tempJml) + parseInt(item.nilai);

                    if (tempJm != jam) {
                        tempJm = jam;
                        temTR = "|";
                        temJM = '<td>' + tgl + '<span class="pull-right">' + jam + '</span>' + '</td>';
                        if (i > 0) {
                            var total = parseInt(tempJml) - parseInt(item.nilai);
                            tempTotal = parseInt(tempTotal) + parseInt(total);
                            jml = '<td align="center"><div class="number-circle" style="background-color: #b3ffb3">' + total + '</div>&nbsp;<div class="number-circle" style="background-color: #ffff66">' + tempTotal + '</div></td>'
                            tempJml = parseInt(item.nilai);
                        }
                    }

                    if (i == tempBody.length - 1) {
                        tempTotal = parseInt(tempTotal) + parseInt(tempJml);
                        last = '<td align="center"><div class="number-circle" style="background-color: #b3ffb3">' + tempJml + '</div>&nbsp;<div class="number-circle" style="background-color: #ffff66">' + tempTotal + '</div></td>'
                        tempJml = parseInt(item.nilai);
                    }

                    if (i == 0) {
                        body += '<td>' + tgl + '<span class="pull-right">' + jam + '</span>' + '</td><td>' + nilai + '</td>';
                    } else {
                        body += jml + temTR + temJM + '<td>' + nilai + '</td>' + last;
                    }
                });
            }

            var bod = body.split("|");
            var bb = "";
            $.each(bod, function (i, item) {
                bb += '<tr>' + item + '</tr>';
            });

            head = head + '<td width="16%">Jumlah/ Komulatif</td>';

            if ("keseimbangan" == jenis) {
                $('#head_' + jenis + '_icu').html('<tr>' + head + '</tr>');
                $('#body_' + jenis + '_icu').html(bb);
            } else {
                var table = '<table style="font-size: 12px" class="table table-bordered">' +
                    '<thead><tr>' + head + '</tr></thead>' +
                    '<tbody>' + bb + '</tbody>' +
                    '</table>';

                var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                newRow.insertAfter($('table').find('#row_icu_' + jenis));
                var url = contextPath + '/pages/images/minus-allnew.png';
                $('#btn_icu_' + jenis).attr('src', url);
                $('#btn_icu_' + jenis).attr('onclick', 'delRowICCU(\'' + jenis + '\')');
            }
        } else {
            if ("keseimbangan" != jenis) {
                var table = '<table style="font-size: 12px" class="table table-bordered">' +
                    '<tbody>' + '<tr><td>Data belum ada</td></tr>' + '</tbody>' +
                    '</table>';

                var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                newRow.insertAfter($('table').find('#row_icu_' + jenis));
                var url = contextPath + '/pages/images/minus-allnew.png';
                $('#btn_icu_' + jenis).attr('src', url);
                $('#btn_icu_' + jenis).attr('onclick', 'delRowICCU(\'' + jenis + '\')');
            }
        }
    });
}

function delRowICCU(id) {
    $('#del_icu_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_icu_' + id).attr('src', url);
    $('#btn_icu_' + id).attr('onclick', 'listInputan(\'' + id + '\')');
}

function checkSelect(id, pid, val){

    if($('#'+pid).is(':checked')){
        $('#'+id).prop('checked', true);
        $('#'+pid).prop('checked', false);
    }else{
        $('#'+id).prop('checked', true);
    }

    if(val == "Ya" || val == "Dirawat dirumah"){
        $('#icu_'+id).show();
    }else {
        $('#icu_'+pid).hide();
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
        }else{
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

    var data =[];

    var diagnosis = $('[name=diag]');
    var inter = $('[name=inter]');
    var imple = $('#tin').val();
    var param = $('[name=params]');

    // var ttdPerawat = document.getElementById("ttd_perawat");

    var tgl = $('.tgl').val();
    var jam = $('.jam').val();

    var tempDiag    = "";
    var tempInter   = "";
    var tempImple   = "";

    $.each(diagnosis, function (i, item) {
        var val = "";
        if(item.type == 'checkbox' || item.type == 'radio'){
            if(item.checked){
                val = item.value;
            }
        }else{
            if(item.value != ''){
                val = item.value;
            }
        }

        if(val != ''){
            if(tempDiag != ''){
                tempDiag = tempDiag +'|'+val;
            }else{
                tempDiag = val;
            }
        }
    });

    var subjektif = "";

    $.each(param, function (i, item) {

        var subjek = $('[name=subjek'+i+']');
        var val = "";
        var sbj = "";

        $.each(subjek, function (idx, itm) {
            if(itm.type == 'checkbox'){
                if(itm.checked){
                    if(val != ''){
                        val = val+', '+itm.value;
                    }else{
                        val = itm.value;
                    }
                }
            }else{
                if(itm.value != ''){
                    if(val != ''){
                        val = val+', '+itm.value;
                    }else{
                        val = itm.value;
                    }
                }
            }
        });

        if(val != ''){
            sbj = item.value +' : '+val;
            if(subjektif != ''){
                subjektif = subjektif +'|'+sbj;
            }else{
                if(diagnosis != ''){
                    subjektif = '|'+sbj;
                }else{
                    subjektif = sbj;
                }
            }
        }
    });

    $.each(inter, function (i, item) {
        var val = "";
        if(item.type == 'checkbox'){
            if(item.checked){
                val = item.value;
            }
        }else{
            if(item.value != ''){
                val = item.value;
            }
        }

        if(val != ''){
            if(tempInter != ''){
                tempInter = tempInter +'|'+val;
            }else{
                tempInter = val;
            }
        }
    });

    $.each(imple, function (i, item) {
        if(tempImple != ''){
            tempImple = tempImple +'|'+item;
        }else{
            tempImple = item;
        }
    });

    // var cekTtd = isCanvasBlank(ttdPerawat);

    if (tgl && jam && tempDiag && tempInter && tempImple && subjektif != '') {

        // var ttd = ttdPerawat.toDataURL("image/png"),
        //     ttd = ttd.replace(/^data:image\/(png|jpg);base64,/, "");

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': tgl+' '+jam,
            'diagnosa': tempDiag+subjektif,
            'hasil': '',
            'intervensi': tempInter,
            'implementasi': tempImple,
            'evaluasi': '',
            'keterangan': jenis,
            'ttd_perawat': ''
        }

        var result = JSON.stringify(data);

        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);
        RencanaAsuhanKeperawatanAction.save(result, {
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
    RencanaAsuhanKeperawatanAction.getListDetail(idDetailCheckup, jenis+'_icu', function (res) {
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

                table += '<tr>' +
                    '<td>' + item.waktu + '</td>' +
                    '<td>' + ul1 + '</td>' +
                    '<td>' + ul2 + '</td>' +
                    '<td>' + ul3 + '</td>' +
                    '</tr>';
            });

            $('#body_asuhan_keperawatan').html(table);
        }
    });
}

function pilihTindakanICU(val) {
    if (val != '') {
        $('#form-tindakan-icu').show();
        $('#icu8').val(val);
        var body = "";
        $.each(tindakanICU(val), function (i, item) {

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




