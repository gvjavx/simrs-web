function showModalPengkajianKep(jenis, idRM, isSetIdRM) {
    if(isSetIdRM == "Y"){
        tempidRm = idRM;
    }
    if(isReadRM){
        $('.btn-hide').hide();
    }else{
        $('.btn-hide').show();
    }
    if("resiko_jatuh" == jenis){
        setResikoJatuh('set_'+jenis, umur);
    }
    $('#modal-puk-' + jenis).modal({show: true, backdrop: 'static'});
    setDataPasien();
}

function savePengkajianKep(jenis, ket) {
    var data = [];
    var cek = false;
    var dataPasien = "";

    dataPasien = {
        'no_checkup' : noCheckup,
        'id_detail_checkup' : idDetailCheckup,
        'id_pasien' : idPasien,
        'id_rm' : tempidRm
    }

    if("resiko_jatuh" == jenis){
        var va1 = $('#rj1').val();
        var va2 = $('#rj2').val();
        var va3 = $('#rj3').val();
        var dataCek = [];

        var tanggal = va2.split("-").reverse().join("-");
        data.push({
            'parameter': 'Tanggal dan Jam',
            'jawaban': va2 + ' ' + va3,
            'kode': '1',
            'waktu': va1,
            'tanggal': tanggal,
            'keterangan': jenis,
            'jenis': 'pengkajian',
            'id_detail_checkup': idDetailCheckup
        });
        var resikoJatuh = $('.resiko_jatuh');
        var jenisResiko = $('#jenis_resiko').val();
        var totalSkor = "";
        if(resikoJatuh.length > 0){
            $.each(resikoJatuh, function (i, item) {
                var label = $('#label_resiko_jatuh'+i).text();
                var resiko = $('[name=resiko_jatuh'+i+']:checked').val();
                if(resiko != undefined){
                    var isi = resiko.split("|")[0];
                    var skor = resiko.split("|")[1];
                    dataCek.push({
                        'parameter': label,
                        'jawaban': isi,
                        'skor': skor,
                        'keterangan': jenis,
                        'jenis': ket,
                        'id_detail_checkup': idDetailCheckup
                    });
                    if(totalSkor != ''){
                        totalSkor = parseInt(totalSkor) + parseInt(skor);
                    }else{
                        totalSkor = parseInt(skor);
                    }
                }
            });

            if(totalSkor != ''){
                data.push({
                    'parameter': 'Score',
                    'jawaban': ''+totalSkor,
                    'kode': '2',
                    'waktu': va1,
                    'tanggal': tanggal,
                    'keterangan': jenis,
                    'jenis': 'pengkajian',
                    'id_detail_checkup': idDetailCheckup
                });

                var kesimpulan = "";

                if(jenisResiko == "humpty_dumpty"){
                    if(parseInt(totalSkor) >= 7 && parseInt(totalSkor) <= 11){
                        kesimpulan = "Rendah";
                    }else if (parseInt(totalSkor) >= 12) {
                        kesimpulan = "Tinggi";
                    }
                }else if(jenisResiko == "skala_morse"){
                    if(parseInt(totalSkor) >= 0 && parseInt(totalSkor) <= 24){
                        kesimpulan = "Rendah";
                    }else if (parseInt(totalSkor) >= 25 && parseInt(totalSkor) <= 44) {
                        kesimpulan = "Sedang";
                    }else if (parseInt(totalSkor) >= 45) {
                        kesimpulan = "Tinggi";
                    }
                }else if(jenisResiko == "geriatri"){
                    if(parseInt(totalSkor) >= 0 && parseInt(totalSkor) <= 5){
                        kesimpulan = "Rendah";
                    }else if (parseInt(totalSkor) >= 6 && parseInt(totalSkor) <= 16) {
                        kesimpulan = "Sedang";
                    }else if (parseInt(totalSkor) >= 17) {
                        kesimpulan = "Tinggi";
                    }
                }
                data.push({
                    'parameter': 'Tingkat Resiko',
                    'jawaban': kesimpulan,
                    'kode': '3',
                    'waktu': va1,
                    'tanggal': tanggal,
                    'keterangan': jenis,
                    'jenis': 'pengkajian',
                    'id_detail_checkup': idDetailCheckup
                });
            }
        }
        if(resikoJatuh.length == dataCek.length){
            cek = true;
        }
    }

    if ("pencegahan_umum" == jenis) {
        var va1 = $('#pu1').val();
        var va2 = $('#pu2').val();
        var va3 = $('#pu3').val();
        var va4 = $('[name=pu4]:checked').val();
        var va5 = $('[name=pu5]:checked').val();

        if (va1 && va2 && va3 != '' && va4 && va5 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Melakukan orientasi ruangan dan tindakan pencengahan umum',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Berikan edukasi pencegahan jatuh pada pasien dan keluarga',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("resiko_tinggi" == jenis) {
        var va1 = $('#rt1').val();
        var va2 = $('#rt2').val();
        var va3 = $('#rt3').val();
        var va4 = $('[name=rt4]:checked').val();
        var va5 = $('[name=rt5]:checked').val();
        var va6 = $('[name=rt6]:checked').val();

        if (va1 && va2 && va3 != '' && va4 && va5 && va6 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Beri penanda berupa stiker warna kuning di gelang pasien',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Beri pertanda pada bed berbentuk segitiga warna kuning',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Observasi secara teratur setiap pergantian shift',
                'jawaban': va6,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("nyeri" == jenis) {
        var va1 = $('#yer1').val();
        var va2 = $('#yer2').val();
        var va3 = $('#yer3').val();
        var va4 = $('#yer4').val();
        var va5 = $('#yer5').val();
        var va6 = $('#yer6').val();
        var va7 = $('#yer7').val();
        var va9 = $('#yer9').val();
        var va10 = $('#yer10').val();
        var va11 = $('#yer11').val();
        var va12 = $('#yer12').val();
        var va13 = $('#yer13').val();
        var va14 = $('[name=yer14]:checked').val();
        var va15 = $('[name=yer15]:checked').val();
        var va16 = $('[name=yer16]:checked').val();
        var va17 = $('[name=yer17]:checked').val();
        var va18 = $('[name=yer18]:checked').val();
        var va19 = $('[name=yer19]:checked').val();

        if (va1 != '' && va14 && va15 && va16 && va17 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Scala Nyeri',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perjalanan',
                'jawaban': va5,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kwantitas',
                'jawaban': va6,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kwalitas',
                'jawaban': va7,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Faktor Pemberat',
                'jawaban': va9,
                'kode': '5',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Faktor Peringan',
                'jawaban': va10,
                'kode': '6',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Time Bound/Jangka waktu',
                'jawaban': va11,
                'kode': '7',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban': va12,
                'kode': '8',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jam',
                'jawaban': va13,
                'kode': '9',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Reposisi',
                'jawaban': va14,
                'kode': '10',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Kompres hangat',
                'jawaban': va15,
                'kode': '11',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Kompres dingin',
                'jawaban': va16,
                'kode': '12',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '4. Kurangi rangsangan',
                'jawaban': va17,
                'kode': '13',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '5. Alihkan perhatian',
                'jawaban': va18,
                'kode': '14',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '6. Obat obatan',
                'jawaban': va19,
                'kode': '15',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("resiko_kulit" == jenis) {
        var va1 = $('#klt1').val();
        var va2 = $('#klt2').val();
        var va3 = $('#klt3').val();
        var va4 = $('#klt4').val();
        var va5 = $('[name=klt5]:checked').val();
        var va6 = $('[name=klt6]:checked').val();
        var va7 = $('[name=klt7]:checked').val();
        var va8 = $('[name=klt8]:checked').val();

        if (va1 && va2 && va3 && va4 != '' && va5 && va6 && va7 && va8 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kriteria',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Ubah Posisi',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Jaga kebersihan kulit',
                'jawaban': va6,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Anjurkan intake adekuat',
                'jawaban': va7,
                'kode': '5',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '4. Pasang kasur anti dekubitus',
                'jawaban': va8,
                'kode': '6',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("restrain" == jenis) {
        var va1 = $('#res1').val();
        var va2 = $('#res2').val();
        var va3 = $('#res3').val();
        var va4 = $('#res4').val();
        var va5 = $('#res5').val();
        var va6 = $('#res6').val();
        var va7 = $('[name=res7]:checked').val();
        var va8 = $('[name=res8]:checked').val();
        var va9 = $('[name=res9]:checked').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 != '' && va7 && va8 && va9 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Restrain',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Restrain',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban': va6,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Dekatkan kebutuhan pasien',
                'jawaban': va7,
                'kode': '5',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Edukasi pada penunggu',
                'jawaban': va8,
                'kode': '6',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Bantu Keb dasar pasien',
                'jawaban': va9,
                'kode': '7',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pemulangan_pasien" == jenis) {
        var va1 = $('#pep1').val();
        var va2 = $('#pep2').val();
        var va3 = $('#pep3').val();
        var va4 = $('#pep4').val();
        var va5 = $('#pep5').val();
        var va6 = $('[name=pep6]:checked').val();
        var va7 = $('[name=pep7]:checked').val();

        if (va1 && va2 && va3 && va4 && va5 != '' && va6 && va7 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kriteria pemulangan',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Edukasi',
                'jawaban': va6,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Identifikasi kebutuhan pasien',
                'jawaban': va7,
                'kode': '5',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pasien_akhir" == jenis) {
        var va1 = $('#pak1').val();
        var va2 = $('#pak2').val();
        var va3 = $('#pak3').val();
        var va4 = $('#pak4').val();
        var va5 = $('#pak5').val();
        var va6 = $('[name=pak6]:checked').val();
        var va7 = $('[name=pak7]:checked').val();
        var va8 = $('[name=pak8]:checked').val();
        var ttd1 = document.getElementById('gen2');
        var ttd2 = document.getElementById('gen3');
        var nama1 = $('#nama_terang_gen2').val();
        var nama2 = $('#nama_terang_gen3').val();
        var sip1 = $('#sip_gen2').val();
        var sip2 = $('#sip_gen3').val();
        var cek1 = isCanvasBlank(ttd1);
        var cek2 = isCanvasBlank(ttd2);

        if (!cek1 && !cek2 && va1 && va2 && va3 && va4 && va5 && nama1 && nama2 && sip1 && sip2 != '' && va6 && va7 && va8 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            var cvs1 = convertToDataURL(ttd1);
            var cvs2 = convertToDataURL(ttd2);
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kebutuhan Khusus',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Hub layanan khusus',
                'jawaban': va6,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Pendamping keluarga',
                'jawaban': va7,
                'kode': '5',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Mendoakan',
                'jawaban': va7,
                'kode': '6',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Perawat',
                'jawaban': cvs1,
                'kode': '7',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'tipe':'ttd',
                'nama_terang':nama1,
                'sip':sip1,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD DPJP',
                'jawaban': cvs2,
                'kode': '8',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'tipe':'ttd',
                'nama_terang':nama2,
                'sip':sip2,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        var pasienData = JSON.stringify(dataPasien);
        $('#save_puk_' + jenis).hide();
        $('#load_puk_' + jenis).show();
        dwr.engine.setAsync(true);
        PengkajianUlangKeperawatanAction.savePengkajianKeperawatan(result, pasienData, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_puk_' + jenis).show();
                    $('#load_puk_' + jenis).hide();
                    $('#modal-puk-' + jenis).modal('hide');
                    $('#warning_puk_' + ket).show().fadeOut(5000);
                    $('#msg_puk_' + ket).text("Berhasil menambahkan data data keperawatan...");
                    $('#modal-puk-' + jenis).scrollTop(0);
                } else {
                    $('#save_puk_' + jenis).show();
                    $('#load_puk_' + jenis).hide();
                    $('#warning_puk_' + jenis).show().fadeOut(5000);
                    $('#msg_puk_' + jenis).text(res.msg);
                    $('#modal-puk-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_puk_' + jenis).show().fadeOut(5000);
        $('#msg_puk_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-puk-' + jenis).scrollTop(0);
    }
}

function detailPengkajianKep(jenis) {
    if (jenis != '') {
        var head = "";
        var body = "";
        var totalSkor = 0;
        var first = "";
        var last = "";
        var tgl = "";
        var cekData = false;

        PengkajianUlangKeperawatanAction.getListPengkajianKeperawatan(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var pagi = "";
                    var siang = "";
                    var malam = "";
                    if (item.pagi != null) {
                        pagi = item.pagi;
                    }
                    if (item.siang != null) {
                        siang = item.siang;
                    }
                    if (item.malam != null) {
                        malam = item.malam;
                    }

                    if("Tanggal dan Jam" == item.parameter){
                        body += '<tr>' +
                            '<td>' + item.parameter +
                            '<i id="delete_'+item.idPengkajianUlangKeperawatan+'_pagi" onclick="conPK(\'pagi\', \''+item.idPengkajianUlangKeperawatan+'\')" class="fa fa-trash hvr-grow" style="color: #00e765; margin-left: 10px"></i>' +
                            '<i id="delete_'+item.idPengkajianUlangKeperawatan+'_siang" onclick="conPK(\'siang\', \''+item.idPengkajianUlangKeperawatan+'\')" class="fa fa-trash hvr-grow" style="color: #ff7700; margin-left: 10px"></i>' +
                            '<i id="delete_'+item.idPengkajianUlangKeperawatan+'_malam" onclick="conPK(\'malam\', \''+item.idPengkajianUlangKeperawatan+'\')" class="fa fa-trash hvr-grow" style="color: #0d6aad; margin-left: 10px"></i>' +
                            '</td>' +
                            '<td>' + setItm(pagi) + '</td>' +
                            '<td>' + setItm(siang) + '</td>' +
                            '<td>' + setItm(malam) + '</td>' +
                            '</tr>';
                    }else{
                        if(item.tipe == 'ttd'){
                            var p1 = "";
                            var p2 = "";
                            var p3 = "";
                            var s1 = "";
                            var s2 = "";
                            var s3 = "";
                            var m1 = "";
                            var m2 = "";
                            var m3 = "";
                            if(item.pagi != null && item.pagi != ''){
                                p1 = '<img src="' + item.pagi.split("|")[0] + '" style="height: 100px">';
                                p2 = '<p style="margin-top: -3px">'+item.pagi.split("|")[1]+'</p>';
                                p3 = '<p style="margin-top: -10px">'+item.pagi.split("|")[2]+'</p>';
                            }
                            if(item.siang != null && item.siang != ''){
                                s1 = '<img src="' + item.siang.split("|")[0] + '" style="height: 100px">';
                                s2 = '<p style="margin-top: -3px">'+item.siang.split("|")[1]+'</p>';
                                s3 = '<p style="margin-top: -10px">'+item.siang.split("|")[2]+'</p>';
                            }
                            if(item.malam != null && item.malam != ''){
                                m1 = '<img src="' + item.malam.split("|")[0] + '" style="height: 100px">';
                                m2 = '<p style="margin-top: -3px">'+item.malam.split("|")[1]+'</p>';
                                m3 = '<p style="margin-top: -10px">'+item.malam.split("|")[2]+'</p>';
                            }
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + p1+p2+p3 + '</td>' +
                                '<td>' + s1+s2+s3 + '</td>' +
                                '<td>' + m1+m2+m3 + '</td>' +
                                '</tr>';
                        }else{
                            body += '<tr>' +
                                '<td>' + item.parameter + '</td>' +
                                '<td>' + setItm(pagi) + '</td>' +
                                '<td>' + setItm(siang) + '</td>' +
                                '<td>' + setItm(malam) + '</td>' +
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

            if (cekData) {
                head = '<tr>' +
                    '<td><b>Pengkajian</b></td>' +
                    '<td width="17%"><b><i class="fa fa-circle" style="color: #00e765"></i> Pagi</b></td>' +
                    '<td width="17%"><b><i class="fa fa-circle" style="color: #ff7700"></i> Siang</b></td>' +
                    '<td width="17%"><b><i class="fa fa-circle" style="color: #0d6aad"></i> Malam</b></td>' +
                    '</tr>';
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_puk_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_puk_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_puk_' + jenis).attr('src', url);
            $('#btn_puk_' + jenis).attr('onclick', 'delRowPengkajianKep(\'' + jenis + '\')');
        });
    }
}

function delRowPengkajianKep(id) {
    $('#del_puk_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_puk_' + id).attr('src', url);
    $('#btn_puk_' + id).attr('onclick', 'detailPengkajianKep(\'' + id + '\')');
}

function setItm(item) {
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

function conPK(jenis, idAsesmen){
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show:true, backdrop:'static'});
    $('#save_con_rm').attr('onclick', 'delPK(\''+jenis+'\', \''+idAsesmen+'\')');
}

function delPK(jenis, idAsesmen) {
    $('#modal-confirm-rm').modal('hide');
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var result = JSON.stringify(dataPasien);
    startIconSpin('delete_'+idAsesmen+'_'+jenis);
    dwr.engine.setAsync(true);
    PengkajianUlangKeperawatanAction.saveDelete(jenis, idAsesmen, result, {
        callback: function (res) {
            if (res.status == "success") {
                stopIconSpin('delete_'+idAsesmen+'_'+jenis);
                $('#pengkajian').scrollTop(0);
                $('#warning_puk_pengkajian').show().fadeOut(5000);
                $('#msg_puk_pengkajian').text("Berhasil menghapus data...");
            } else {
                stopIconSpin('delete_'+idAsesmen+'_'+jenis);
                $('#pengkajian').scrollTop(0);
                $('#modal_warning').show().fadeOut(5000);
                $('#msg_warning').text(res.msg);
            }
        }
    });
}