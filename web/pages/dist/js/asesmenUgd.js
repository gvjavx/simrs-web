function showKetUgd(val, id){
    if(val == "Lain-Lain" || val == "Ya" || val == "Gangguan" || val == "lainnya"){
        $('#ugd-'+id).show();
    }else{
        $('#ugd-'+id).hide();
    }
}

function showKetUgd2(val, id){
    if(val == "lainnya" || val == "Ya" || val == "Lainnya" || val == "Tidak dapat dilayanani"){
        $('#ugd-2-'+id).show();
    }else{
        $('#ugd-2-'+id).hide();
    }
}

function showKetIntruksi(val){
    if(val == "rawat_inap" || val == "rawat_isolasi" || val == "rawat_intensif"){
        $('#int-ket1').show();
        $('#int-ket2').hide();
        $('#int-ket3').hide();
        $('#int-ket4').hide();
        $('#set_kontrol').html('');
    }else if ("selesai" == val) {
        $('#int-ket1').hide();
        $('#int-ket2').hide();
        $('#int-ket3').hide();
        $('#int-ket4').show();
        $('#set_kontrol').html('');
        listKeteranganKeluar('int_ket_selesai');
    }else if ("rujuk_rs_lain" == val) {
        $('#int-ket1').hide();
        $('#int-ket2').hide();
        $('#int-ket3').show();
        $('#int-ket4').hide();
        $('#set_kontrol').html('');
    }else if ("kontrol_ulang" == val) {
        $('#int-ket1').hide();
        $('#int-ket2').show();
        $('#int-ket3').hide();
        $('#int-ket4').hide();
        setPelayanan('int_pelayanan_0');
        $('#set_kontrol').html('');
    }else{
        $('#int-ket1').hide();
        $('#int-ket2').hide();
        $('#int-ket3').hide();
        $('#int-ket4').hide();
    }
}

function addKontrolUlang(tipe){
    var count = $('.'+tipe+'_tanggal_kontrol').length;
    var idRow = tipe+'_kon_'+count;
    var atas = '<div class="row jarak" id="'+idRow+'">\n';
    var bawah = '</div>\n';
    if('close' == tipe){
        atas = '<div class="form-group" id="'+idRow+'">\n';
        bawah = '<\div>';
    }
    var set = atas +
        '<div class="col-md-3">\n' +
        '    <input class="form-control ptr-tgl '+tipe+'_tanggal_kontrol" placeholder="Tanggal" style="margin-top: 7px" id="'+tipe+'_tgl_kontrol_'+count+'">\n' +
        '</div>\n' +
        '<div class="col-md-4">\n' +
        '    <select class="form-control select2 '+tipe+'_pelayanan_kontrol" id="'+tipe+'_pelayanan_'+count+'" onchange="setIntDokter(this.value, \''+tipe+'_dokter_'+count+'\')"></select>\n' +
        '</div>\n' +
        '<div class="col-md-4">\n' +
        '    <select class="form-control select2 '+tipe+'_dokter_kontrol" id="'+tipe+'_dokter_'+count+'"></select>\n' +
        '</div>\n' +
        '<div class="col-md-1">\n' +
        '    <button onclick="removeKontrol(\''+idRow+'\')" style="margin-left: -20px; margin-top: 7px" class="btn btn-danger"><i class="fa fa-trash"></i></button>\n' +
        '</div>\n' +bawah;

    $('#set_kontrol_'+tipe).append(set);
    setPelayanan(tipe+'_pelayanan_'+count);
    $(function () {
        $('.select2').select2({});
        $('.ptr-tgl').datepicker({
            dateFormat : 'dd-mm-yy'
        });
        $('.ptr-tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
        $('[data-mask]').inputmask();
    });
}

function removeKontrol(id){
    $('#'+id).remove();
}

function listKeteranganKeluar(id){
    var option = '';
    CheckupDetailAction.listOfKeteranganKeluar(function (res) {
        if(res.length > 0){
            $.each(res, function (i, item) {
                option += '<option value="'+item.idKeterangan+'">'+item.keterangan+'</option>';
            });
        }
        $('#'+id).html(option);
    });
}

function setPelayanan(id) {
    var option = '<option value=""> - </option>';
    CheckupAction.getComboPelayananWithLabCtx("", function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                option += '<option value="' + item.idPelayanan + '">' + item.namaPelayanan + '</option>';
            });
            $('#'+id).html(option);
        }
    });
}

function setIntDokter(value, id){
    var option = '<option value=""> - </option>';
    CheckupAction.getListDokterByPelayanan(value, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                option += '<option value="' + item.idDokter + '">' + item.namaDokter + '</option>';
            });
        }
        $('#'+id).html(option);
    });
}

function showAsesmenUgd(jenis, idRM, isSetIdRM) {
    if(isSetIdRM == "Y"){
        tempidRm = idRM;
    }
    if(isReadRM){
        $('.btn-hide').hide();
    }else{
        $('.btn-hide').show();
    }
    $('#modal-aud_asesmen-ugd').modal({show: true, backdrop: 'static'});
}

function addAsesmenUgd(jenis, idRM, isSetIdRM) {
    if (jenis != '') {
        if(isSetIdRM == "Y"){
            tempidRm = idRM;
        }
        if("anamnesa" == jenis){
            var url = "";
            if ("Laki-Laki" == jenisKelamin) {
                url = contextPath+'/pages/images/male.jpg';
            } else {
                url = contextPath+'/pages/images/female.jpg';
            }
            loadImgToCanvas(url, 'area_ugd');
        }
        if("asuhan_medis" == jenis){
            listTindakanKategoriPoli();
        }
        if("triase" == jenis){
            setNyeri('set_nyeri', umur);
        }
        if("keluar_igd" == jenis){
            setKeteranganPeriksa('ki9');
        }
        $('#modal-aud-' + jenis).modal({show: true, backdrop: 'static'});
        setDataPasien();
    }
}

function saveAsesmenUgd(jenis, keterangan) {
    if (jenis != '') {

        var data = [];
        var cek = false;
        var dataPasien = "";
        var sendTppri = [];

        dataPasien = {
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'id_pasien': idPasien,
            'id_rm': tempidRm
        }

        if ("keluhan_utama" == jenis) {
            var triase = $('.triase_ugd');
            if (triase.length > 0) {
                var warna = $('[name=radio_triase]:checked').val();
                data.push({
                    'parameter': 'Triase',
                    'jawaban': warna,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'tipe': 'triase',
                    'id_detail_checkup': idDetailCheckup
                });
                $.each(triase, function (idx, itemx) {
                    var label = $('#label_trias_'+idx).text();
                    var isi = $('[name=trias_'+idx+']:checked').val();
                    if(isi != undefined && isi != ''){
                        data.push({
                            'parameter': label,
                            'jawaban': isi,
                            'keterangan': jenis,
                            'jenis': keterangan,
                            'id_detail_checkup': idDetailCheckup
                        });
                    }
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
            var cr = $('[name=radio_ct]:checked').val();

            var cara = "";
            if(cr != undefined){
                var ket = $('#ket_ct').val();
                if(cr == "Lain-Lain"){
                    cara = cr+" : "+ket;
                }else{
                    cara = cr;
                }
            }

            var rjk = $('#rujukan').val();
            var tindakan = $('#tindakan_yang_sudah').val();

            if (tkn && nadi1 && rr1 && suhu && cara != ''
                && airway && warnaKulit1 && polaNafas && kerjaNafas && suaraNafas && jejas && trakea && dada && distensi != undefined
                && otot && rr2 && spo2 && nadi2 != ''
                && kwalNadi && irama && crt && warnaKulit2 && akral != undefined) {

                data.push({
                    'parameter': 'Cara tiba ke RS',
                    'jawaban': cara,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Rujukan',
                    'jawaban': rjk,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Tindakan',
                    'jawaban': tindakan,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Tekanan Darah',
                    'jawaban': replaceUnderLine(tkn) + ' mmHg',
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Nadi',
                    'jawaban': nadi1 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'RR',
                    'jawaban': rr1 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Suhu',
                    'jawaban': suhu + ' ˚C',
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Airway',
                    'jawaban': airway,
                    'keterangan': jenis,
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
                    'parameter': 'Otot bantu nafas',
                    'jawaban': otot,
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
                    'parameter': 'SpO2',
                    'jawaban': spo2 + ' %',
                    'keterangan': jenis,
                    'jenis': 'breathing',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Nadi',
                    'jawaban': nadi2 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': 'circulation',
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
            var tRiwayat = $('#kep_ket_alergi').val();

            if (tkn && nadi1 && rr1 && suhu && bb && tTempat && emosi && tPerilaku && status && hubungan && agama
                && dibantu && pekerjaan && tPenanggung && sekarang && dahulu && keluarga && tRiwayat != '') {
                data.push({
                    'parameter': 'Tekanan Darah',
                    'jawaban': replaceUnderLine(tkn) + ' mmHg',
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Nadi',
                    'jawaban': nadi1 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'RR',
                    'jawaban': rr1 + ' x/menit',
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Suhu',
                    'jawaban': suhu + ' ˚C',
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Berat Badan',
                    'jawaban': bb + ' Kg',
                    'keterangan': jenis,
                    'jenis': keterangan,
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
                    'id_detail_checkup': idDetailCheckup,
                    'tipe': 'penyakit_dahulu'
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
            var skal = $('#skala_nyeri').val();
            if (nyeri != undefined) {
                var cecek = false;
                if(nyeri == "Ya"){
                    if(skala != undefined && lokasi != '' && skal != ''){
                        cecek = true;
                    }
                }else{
                    cecek = true;
                }
                if(cecek){
                    data.push({
                        'parameter': 'Apakah terdapat keluhan nyeri',
                        'jawaban': nyeri,
                        'keterangan': jenis,
                        'jenis': keterangan,
                        'id_detail_checkup': idDetailCheckup
                    });
                    data.push({
                        'parameter': 'Lokasi',
                        'jawaban': lokasi != '' ? lokasi : '-',
                        'keterangan': jenis,
                        'jenis': keterangan,
                        'id_detail_checkup': idDetailCheckup
                    });
                    data.push({
                        'parameter': 'Jenis',
                        'jawaban': skala != undefined ? skala : '-',
                        'keterangan': jenis,
                        'jenis': keterangan,
                        'id_detail_checkup': idDetailCheckup
                    });
                    data.push({
                        'parameter': 'Skala',
                        'jawaban': skal != '' ? skal : '-',
                        'keterangan': jenis,
                        'jenis': keterangan,
                        'id_detail_checkup': idDetailCheckup
                    });
                    cek = true;
                }
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

                var total = parseInt(skor1)+parseInt(skor2);
                var kesimpulan = "";
                if(total < 2){
                    kesimpulan = "Tidak";
                }else{
                    kesimpulan = "Ya";
                }

                data.push({
                    'parameter': 'Total Skor Nutrisional',
                    'jawaban': ''+total,
                    'keterangan': jenis,
                    'tipe': 'total',
                    'id_detail_checkup': idDetailCheckup
                });

                data.push({
                    'parameter': 'Resiko Nutrisional',
                    'jawaban': kesimpulan,
                    'keterangan': jenis,
                    'tipe': 'total',
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

                var totalSkor = parseInt(skor1)+parseInt(skor2)+parseInt(skor3)+parseInt(skor4)+parseInt(skor5)+parseInt(skor6);
                var kesimpulan = "";
                if(totalSkor >= 0 && totalSkor <= 24){
                    kesimpulan = "Rendah";
                }else if (totalSkor >= 25 && totalSkor <= 44) {
                    kesimpulan = "Sedang";
                }else if (totalSkor >= 45) {
                    kesimpulan = "Tinggi";
                }
                data.push({
                    'parameter': 'Total Skor Resiko Jatuh',
                    'jawaban': ''+totalSkor,
                    'keterangan': jenis,
                    'tipe': 'total',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Resiko Jatuh',
                    'jawaban': kesimpulan,
                    'keterangan': jenis,
                    'tipe': 'kesimpulan',
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
                var total = parseInt(skor1)+parseInt(skor2)+parseInt(skor3)+parseInt(skor4)+parseInt(skor5)+parseInt(skor6)+parseInt(skor7)+parseInt(skor8)+parseInt(skor9)+parseInt(skor10);
                var kesimpulan = "";
                if(total >= 0 && total <= 20){
                    kesimpulan = "Keteragantungan Total";
                }else if(total >= 25 && total <= 40){
                    kesimpulan = "Keteragantungan Berat";
                }else if(total >= 45 && total <= 55){
                    kesimpulan = "Keteragantungan Sebagian";
                }else if(total >= 60 && total <= 95){
                    kesimpulan = "Ketergantungan Ringan";
                }else if(total == 100){
                    kesimpulan = "Mandiri";
                }
                data.push({
                    'parameter': 'Total Status Fungsional',
                    'jawaban': ''+total,
                    'keterangan': jenis,
                    'tipe': 'total',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Status Fungsional',
                    'jawaban': kesimpulan,
                    'keterangan': jenis,
                    'tipe': 'kesimpulan',
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if ("kebutuhan" == jenis) {
            var kebutuhan1 = $('[name=radio_aud_kebutuhan1]:checked').val();
            var kebutuhan2 = $('[name=radio_aud_kebutuhan2]:checked').val();
            var kebutuhan3 = $('[name=radio_aud_kebutuhan3]:checked').val();
            var kebutuhan4 = document.getElementsByName('kp');
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

            $.each(kebutuhan4, function (i, item) {
                if(item.checked){
                    if(tKebutuhan4 != ''){
                        tKebutuhan4 = tKebutuhan4 +', '+item.value;
                    }else{
                        tKebutuhan4 = item.value;
                    }
                }
            });

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
                    'parameter': 'Hambatan Belajar',
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

        if ("anamnesa" == jenis) {
            var va1 = $('#an1').val();
            var va2 = "";
            var v2 = $('#an2').val();
            var allow = $('#ket_allow').val();
            if(v2 != ''){
                var ket = $('#ket_an2').val();
                if(v2 == "Lainnya"){
                    va2 = ket;
                }else{
                    va2 = v2;
                }
            }

            var va3 = $('#an3').val();
            var va4 = $('#an4').val();
            var va5 = $('#an5').val();
            var ugd = "Y";

            var canvasArea = document.getElementById('area_ugd');
            var canvasCek = document.getElementById('area_cek');
            if(canvasArea.toDataURL() == canvasCek.toDataURL()){
                ugd = "N";
            }

            var va6 = $('#an6').val();
            var va7 = $('#an7').val();
            var va8 = $('#an8').val();
            var va9 = $('#an9').val();
            var va10 = $('#an10').val();
            var va11 = $('#an11').val();
            var va12 = $('#an12').val();
            var va13 = $('#an13').val();
            var va14 = $('#an14').val();
            var va15 = $('#an15').val();

            if (va1 && va2 && va3 && va4 && va5 && va6 && va7 &&
                va8 && va9 && va10 && va11 && va12 && va13 && va14 && va15 != '') {
                data.push({
                    'parameter': 'Autoanamnesis',
                    'jawaban': va1,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                var low = "";
                if(allow != ''){
                    low = ', '+allow;
                }
                data.push({
                    'parameter': 'Alloanamnesis',
                    'jawaban': 'oleh '+va2+low,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });

                if(ugd == "Y"){
                    var canv = canvasArea.toDataURL("image/png"),
                        canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");
                    data.push({
                        'parameter': 'Pemeriksaan Fisik',
                        'jawaban': canv,
                        'tipe': 'gambar',
                        'keterangan': jenis,
                        'id_detail_checkup': idDetailCheckup
                    });
                }

                data.push({
                    'parameter': 'GCS',
                    'jawaban': 'E : '+va3+', V : '+va4+', M : '+va5,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Kepala',
                    'jawaban': va6,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Leher',
                    'jawaban': va7,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Thorax',
                    'jawaban': va8,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Abdomen dan Pelvis',
                    'jawaban': va9,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Punggung dan Pinggang',
                    'jawaban': va10,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Ekstrimitas',
                    'jawaban': va11,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Penunjang Medis',
                    'jawaban': va12,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Diagnosa Kerja',
                    'jawaban': va13,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Diagnosa Banding',
                    'jawaban': va14,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Sasaran',
                    'jawaban': va15,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if ("asuhan_medis" == jenis) {
            var va1 = "";
            var v1 = $('[name=ra1]:checked').val();
            if(v1 != undefined){
                var ket = $('#ket_ra1').val();
                if("Tidak dapat dilayanani" == va1){
                    va1 = v1+', '+ket;
                }else{
                    va1 = v1;
                }
            }

            var va2 = $('#ra2').val();
            var va4 = $('#ra4').val();
            var va5 = $('#ra5').val();

            if (va1 && va2 && va4 && va5 != '') {
                data.push({
                    'parameter': 'Hasil Skrining',
                    'jawaban': va1,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Terapi',
                    'jawaban': va2,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Tindakan',
                    'jawaban': va4,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Diet',
                    'jawaban': va5,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if ("keluar_igd" == jenis) {

            var va1 = $('#ki1').val();
            var va2 = $('#ki2').val();
            var va3 = $('#ki3').val();
            var va4 = $('#ki4').val();
            var va5 = $('#ki5').val();
            var va6 = $('#ki6').val();
            var va7 = $('#ki7').val();
            var va8 = $('#ki8').val();
            var kasus = $('#kasus').val();
            var va9 = "";
            var v9 = $('#ki9').val();
            var v9Text = $('#ki9 option:selected').text();
            if(v9 != "") {
                var ket1 = $('#ket_ki91').val();
                var ket3 = $('#int_ket_rs_lain').val();
                var ketSelesai = $('#int_ket_selesai option:selected').text();

                if ("rawat_inap" == v9 || "rawat_isolasi" == v9 || "rawat_intensif" == v9) {
                    va9 = v9Text + ', ' + ket1;
                } else if ("selesai" == v9) {
                    va9 = ketSelesai;
                } else if ("rujuk_rs_lain" == v9){
                    va9 = v9Text + ','+ ket3;
                } else {
                    va9 = v9Text;
                }

                sendTppri = {
                    'id_detail_checkup': idDetailCheckup,
                    'tindak_lanjut': v9,
                    'indikasi': ket1,
                    'keterangan': va9,
                    'rujuk_rs': ket3
                }
            }

            var ttd1 = document.getElementById("ki10");
            var ttd2 = document.getElementById("ki11");
            var cekTtd1 = isCanvasBlank(ttd1);
            var cekTtd2 = isCanvasBlank(ttd2);
            var nama1 = $('#nama_terang_ki10').val();
            var sip1 = $('#sip_ki10').val();
            var nama2 = $('#nama_terang_ki11').val();
            var sip2 = $('#sip_ki11').val();

            if (va1 && va2 && va3 && va4 && va5 && va6 &&
                va7 && va8 && va9 != '' && !cekTtd1 && !cekTtd2) {
                data.push({
                    'parameter': 'Keadaan Umum',
                    'jawaban': va1,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Kesadaran',
                    'jawaban': va2,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Tekanan Darah',
                    'jawaban': replaceUnderLine(va3) +' mmHg',
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Nadi',
                    'jawaban': va4 +' x/menit',
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'RR',
                    'jawaban': va5 +' x/menit',
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Suhu',
                    'jawaban': va6 +' ˚C',
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Tanggal dan Jam Keluar',
                    'jawaban': va7 +', '+va8,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Jenis Kasus',
                    'jawaban': kasus,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Instruksi Tindak Lanjut',
                    'jawaban': va9,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });

                var ttdA = ttd1.toDataURL("image/png"),
                    ttdA = ttdA.replace(/^data:image\/(png|jpg);base64,/, "");

                var ttdB = ttd2.toDataURL("image/png"),
                    ttdB = ttdB.replace(/^data:image\/(png|jpg);base64,/, "");

                data.push({
                    'parameter': 'TTD Perawat',
                    'jawaban': ttdA,
                    'tipe': 'ttd',
                    'keterangan': jenis,
                    'nama_terang': nama1,
                    'sip':sip1,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'TTD Dokter',
                    'jawaban': ttdB,
                    'tipe': 'ttd',
                    'keterangan': jenis,
                    'nama_terang': nama2,
                    'sip':sip2,
                    'id_detail_checkup': idDetailCheckup
                });

                cek = true;
            }
        }

        if("nyeri_anak" == jenis){
            var va1 = $('[name=ya1]:checked').val();
            var va2 = $('[name=ya2]:checked').val();
            var va3 = $('[name=ya3]:checked').val();
            var va4 = $('[name=ya4]:checked').val();
            var va5 = $('[name=ya5]:checked').val();
            var va6 = $('[name=ya6]:checked').val();

            if (va1 && va2 && va3 && va4 && va5 && va6 != undefined) {

                var isi2 = va2.split("|")[0];
                var isi3 = va3.split("|")[0];
                var isi4 = va4.split("|")[0];
                var isi5 = va5.split("|")[0];
                var isi6 = va6.split("|")[0];

                var skor2 = va2.split("|")[1];
                var skor3 = va3.split("|")[1];
                var skor4 = va4.split("|")[1];
                var skor5 = va5.split("|")[1];
                var skor6 = va6.split("|")[1];

                data.push({
                    'parameter': 'Apakah terdapat keluhan nyeri',
                    'jawaban': va1,
                    'keterangan': jenis,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Face',
                    'jawaban': isi2,
                    'keterangan': jenis,
                    'skor': skor2,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Leg',
                    'jawaban': isi3,
                    'keterangan': jenis,
                    'skor': skor3,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Activity',
                    'jawaban': isi4,
                    'keterangan': jenis,
                    'skor': skor4,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Cry',
                    'jawaban': isi5,
                    'keterangan': jenis,
                    'skor': skor5,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Consolability',
                    'jawaban': isi6,
                    'keterangan': jenis,
                    'skor': skor6,
                    'id_detail_checkup': idDetailCheckup
                });
                var kesimpulan = "";
                var total = parseInt(skor2)+parseInt(skor3)+parseInt(skor4)+parseInt(skor5)+parseInt(skor6);
                if(total >= 1 && total <= 3){
                    kesimpulan = "Nyeri Ringan";
                }else if (total >= 4 && total <= 6) {
                    kesimpulan = "Nyeri Sedang";
                }else if (total >= 7 && total <= 10) {
                    kesimpulan = "Nyeri Berat";
                }else if(total == 0){
                    kesimpulan = "Tidak Nyeri";
                }

                data.push({
                    'parameter': 'Total Skor Nyeri',
                    'jawaban': ''+total,
                    'keterangan': jenis,
                    'tipe': 'total',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Nyeri',
                    'jawaban': kesimpulan,
                    'keterangan': jenis,
                    'tipe': 'kesimpulan',
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if("jatuh_anak" == jenis){
            var skor1 = "";
            var skor2 = "";
            var parUmur = parseInt(umur);

            if(parUmur < 3){
                skor1 = "4";
            }else if(parUmur >= 3 && parUmur <= 7){
                skor1 = "3";
            }else if (parUmur >= 8 && parUmur <= 13){
                skor1 = "2";
            }else if (parUmur >= 14 && parUmur <= 18) {
                skor1 = "1";
            }

            if(jenisKelamin == "Laki-Laki"){
                skor2 = "2";
            }else{
                skor2 = "1";
            }

            var va3 = $('[name=rja3]:checked').val();
            var va4 = $('[name=rja4]:checked').val();
            var va5 = $('[name=rja5]:checked').val();
            var va6 = $('[name=rja6]:checked').val();

            if (va3 && va4 && va5 && va6 != undefined) {

                var isi3 = va3.split("|")[0];
                var isi4 = va4.split("|")[0];
                var isi5 = va5.split("|")[0];
                var isi6 = va6.split("|")[0];

                var skor3 = va3.split("|")[1];
                var skor4 = va4.split("|")[1];
                var skor5 = va5.split("|")[1];
                var skor6 = va6.split("|")[1];

                data.push({
                    'parameter': 'Umur',
                    'jawaban': umur,
                    'keterangan': jenis,
                    'skor': skor1,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Jenis Kelamin',
                    'jawaban': jenisKelamin,
                    'keterangan': jenis,
                    'skor': skor2,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Diagnosis',
                    'jawaban': isi3,
                    'keterangan': jenis,
                    'skor': skor3,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Lingkungan',
                    'jawaban': isi4,
                    'keterangan': jenis,
                    'skor': skor4,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Respon Terhadap Pembedahan/Sedasi/Anastesi',
                    'jawaban': isi5,
                    'keterangan': jenis,
                    'skor': skor5,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Penggunaan Obat',
                    'jawaban': isi6,
                    'keterangan': jenis,
                    'skor': skor6,
                    'id_detail_checkup': idDetailCheckup
                });

                var kesimpulan = "";
                var total = parseInt(skor1)+parseInt(skor2)+parseInt(skor3)+parseInt(skor4)+parseInt(skor5)+parseInt(skor6);
                if(total >= 7 && total <= 11){
                    kesimpulan = "Rendah";
                }else if (total >= 12) {
                    kesimpulan = "Tinggi";
                }

                data.push({
                    'parameter': 'Total Resiko Jatuh',
                    'jawaban': ''+total,
                    'keterangan': jenis,
                    'tipe': 'total',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Resiko Jatuh',
                    'jawaban': kesimpulan,
                    'keterangan': jenis,
                    'tipe': 'kesimpulan',
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if("nutrisional_anak" == jenis){

            var va1 = $('[name=na1]:checked').val();
            var va2 = $('[name=na2]:checked').val();
            var va3 = $('[name=na3]:checked').val();
            var va4 = $('[name=na4]:checked').val();
            var va5 = $('[name=na5]:checked').val();
            var va6 = $('[name=na6]:checked').val();

            if (va1 && va2 && va3 && va4 != undefined) {

                var isi1 = va1.split("|")[0];
                var isi2 = va2.split("|")[0];
                var isi3 = va3.split("|")[0];
                var isi4 = va4.split("|")[0];

                var skor1 = va1.split("|")[1];
                var skor2 = va2.split("|")[1];
                var skor3 = va3.split("|")[1];
                var skor4 = va4.split("|")[1];

                data.push({
                    'parameter': '1. Apakah pasien tampak kurus?',
                    'jawaban': isi1,
                    'keterangan': jenis,
                    'skor': skor1,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': '2. Apakah terdapat penurunan berat badan dalam 1 bulan terakhir? (berdasarkan penilaian objektif data berat badan bila ada atau penilaian subjektif orang tua pasien atau untuk bayi < 1 bulan berat badan tidak naik selama 3 bulan berturut-turut)',
                    'jawaban': isi2,
                    'keterangan': jenis,
                    'skor': skor2,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': '3. Apakah terdapat salag satu kondisi tersebut? (diare > 5x/hari dan muntah > 3x/hari dalam seminggu terakhir atau asupan makanan berkurang selama 1 minggu terakhir)',
                    'jawaban': isi3,
                    'keterangan': jenis,
                    'skor': skor3,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': '4. Apakah terdapat penyakit atau keadaan yang mengakibatkan pasien berisiko mengalami malnutrisi? (lihat tabel)',
                    'jawaban': isi4,
                    'keterangan': jenis,
                    'skor': skor4,
                    'id_detail_checkup': idDetailCheckup
                });
                var kesimpulan = "";
                var total = parseInt(skor1)+parseInt(skor2)+parseInt(skor3)+parseInt(skor4);
                if(total < 2){
                    kesimpulan = "Tidak";
                }else{
                    kesimpulan = "Ya";
                }

                data.push({
                    'parameter': 'Total Skor Nutrisional',
                    'jawaban': ''+total,
                    'keterangan': jenis,
                    'tipe': 'total',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Resiko Nutrisional',
                    'jawaban': kesimpulan,
                    'keterangan': jenis,
                    'tipe': 'kesimpulan',
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if ("triase" == jenis) {
            var va1 = $('[name=tr1]:checked').val();
            var va2 = $('#tr2').val();
            var va3 = $('#tr3').val();
            var va4 = $('#tr4').val();
            var va5 = $('#tr5').val();
            var va6 = $('#tr6').val();
            var va7 = $('#tr7').val();
            var va8 = $('#tr8').val();
            var va9 = $('#tr9').val();
            var va10 = $('#tr10').val();

            var ny1 = $('[name=radio_nyeri_keluhan]:checked').val();
            var ny2 = $('#y_lokasi').val();
            var ny3 = $('[name=radio_nyeri_jenis]:checked').val();
            var ny4 = $('#y_inten').val();
            var jen = $('#temp_jenis').val();
            var nyeri = "";
            var tipe = "";

            var nama = $('#nama_terang_tr12').val();
            var sip = $('#sip_tr12').val();
            var ttd = document.getElementById('tr12');

            if(jen == "emoji"){
                nyeri = document.getElementById('choice_emoji');
                tipe = "Wong Baker Pain Scale";
            }else{
                nyeri = document.getElementById('area_nyeri');
                tipe = "Nomeric Rating Scale";
            }

            var cekTtd = isCanvasBlank(ttd);

            if(nama && sip != '' && !cekTtd){

                var canv1 = convertToDataURL(nyeri);
                var canv2 = convertToDataURL(ttd);

                data.push({
                    'parameter': 'Triase',
                    'jawaban': va1,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Keluhan',
                    'jawaban': va2,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Vital Sign',
                    'jawaban': 'Tensi '+va3+' mmHg, Nadi '+va4+' x/menit, Suhu '+va5+' ˚C, RR '+va6+' x/menit',
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'GCS (EVM)',
                    'jawaban': 'E '+va7+', V '+va8+', M '+va9,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Apakah terdapat keluhan nyeri',
                    'jawaban': ny1,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Lokasi',
                    'jawaban': ny2,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Jenis',
                    'jawaban': ny3,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'Intensitas',
                    'jawaban': ny4,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': tipe,
                    'jawaban': canv1,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'tipe': 'gambar',
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'SPO2',
                    'jawaban': va10,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': 'TTD Perawat',
                    'jawaban': canv2,
                    'keterangan': jenis,
                    'jenis': keterangan,
                    'tipe': 'ttd',
                    'nama_terang': nama,
                    'sip': sip,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }

        if (cek) {
            if(!cekSession()){
                var result = JSON.stringify(data);
                var pasienData = JSON.stringify(dataPasien);
                $('#save_aud_'+jenis).hide();
                $('#load_aud_'+jenis).show();
                dwr.engine.setAsync(true);
                AsesmenUgdAction.saveAsesmenUgd(result, pasienData, {
                    callback: function (res) {
                        if(res.status == "success"){
                            if("keluar_igd" == jenis){
                                dwr.engine.setAsync(true);
                                CheckupDetailAction.sendToTppti(JSON.stringify(sendTppri), {
                                    callback:function (res) {
                                        if(res.status == "success"){
                                            $('#save_aud_'+jenis).show();
                                            $('#load_aud_'+jenis).hide();
                                            $('#modal-aud-' + jenis).modal('hide');
                                            $('#warning_aud_'+keterangan).show().fadeOut(5000);
                                            $('#msg_aud_'+keterangan).text("Berhasil menambahkan data asesmen UGD...");
                                            $("#modal-aud_"+keterangan).scrollTop(0);
                                            delRowAud(jenis);
                                            detailAud(jenis);
                                            kesimpulanAsesmen();
                                            setTindakLanjut();
                                        }else{
                                            $('#save_aud_'+jenis).show();
                                            $('#load_aud_'+jenis).hide();
                                            $("#warning_aud_" + jenis).show().fadeOut(5000);
                                            $('#msg_aud_' + jenis).text(res.msg);
                                            $("#modal-aud-"+jenis).scrollTop(0);
                                            delUGD(jenis, keterangan);
                                        }
                                    }
                                });
                            }else{
                                $('#save_aud_'+jenis).show();
                                $('#load_aud_'+jenis).hide();
                                $('#modal-aud-' + jenis).modal('hide');
                                $('#warning_aud_'+keterangan).show().fadeOut(5000);
                                $('#msg_aud_'+keterangan).text("Berhasil menambahkan data asesmen UGD...");
                                $("#modal-aud_"+keterangan).scrollTop(0);
                                delRowAud(jenis);
                                detailAud(jenis);
                                kesimpulanAsesmen();
                                $('#keterangan').attr('disabled', false);
                                $('#ket_selesai').attr('disabled', false);
                                $('#keterangan_rw').attr('disabled', false);
                                $('#rs_rujukan').attr('disabled', false);
                            }
                        }else{
                            $('#save_aud_'+jenis).show();
                            $('#load_aud_'+jenis).hide();
                            $("#warning_aud_" + jenis).show().fadeOut(5000);
                            $('#msg_aud_' + jenis).text(res.msg);
                            $("#modal-aud-"+jenis).scrollTop(0);
                        }
                    }});
            }
        } else {
            $("#warning_aud_" + jenis).show().fadeOut(5000);
            $('#msg_aud_' + jenis).text("Silahkan cek kembali data inputan anda...!");
            $("#modal-aud-"+jenis).scrollTop(0);
        }
    }

}

function detailAud(jenis) {
    if(!cekSession()){
        if (jenis != '') {
            var body = "";
            var totalSkor = 0;
            var cekSkor = false;
            var rowTotal = "";
            var kesimpulan = "";
            var isKesimpulan = false;
            var list = "";
            var first = "";
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
                        }else if ("ttd" == item.tipe) {
                            body += '<tr>' +
                                '<td width="60%">' + item.parameter + '</td>' +
                                '<td>' + '<img src="' + item.jawaban + '" style="width: 100px">' +
                                '<p style="margin-top: -3px">'+cekItemIsNull(item.namaTerang)+'</p>'+
                                '<p style="margin-top: -7px">'+cekItemIsNull(item.sip)+'</p>'+
                                '</td>' +
                                '</tr>';
                        }else if("gambar" == item.tipe){
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + '<img src="' + item.jawaban + '" style="width: 100%">' + '</td>' +
                                '</tr>';
                        }else if("triase" == item.tipe){
                            var warna = item.jawaban.split("|")[1];
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + '<i class="fa fa-square fa-2x" style="color: '+warna+'"></i>' + '</td>' +
                                '</tr>';
                        }else{
                            if(item.skor != null){
                                body += '<tr>' +
                                    '<td width="60%">' + item.parameter + '</td>' +
                                    '<td>' + item.jawaban + '</td>' +
                                    '<td align="center" width="10%">' + item.skor + '</td>' +
                                    '</tr>';
                                cekSkor = true;
                            }else{
                                if(item.tipe == "total"){
                                    body += '<tr style="font-weight: bold"><td colspan="2">'+item.parameter+'</td><td align="center">'+item.jawaban+'</td></tr>';
                                }else if(item.tipe == "kesimpulan"){
                                    body += '<tr style="font-weight: bold" bgcolor="#ffebcd"><td colspan="2">'+item.parameter+'</td><td align="center">'+item.jawaban+'</td></tr>';
                                }else{
                                    body += '<tr>' +
                                        '<td width="40%">' + item.parameter + '</td>' +
                                        '<td>' + item.jawaban + '</td>' +
                                        '</tr>';
                                }
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
                    first = '<tr style="font-weight: bold"><td>Parameter</td><td>Jawaban</td><td align="center">Skor</td></tr>';
                }

                var table = '<table style="font-size: 12px" class="table table-bordered"><tr bgcolor="#ffebcd">' +
                    '<tbody>' + first + body + rowTotal + kesimpulan + '</tbody>' +
                    '</table>';

                var newRow = $('<tr id="del_aud_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                newRow.insertAfter($('table').find('#row_aud_' + jenis));
                var url = contextPath+'/pages/images/minus-allnew.png';
                $('#btn_aud_' + jenis).attr('src', url);
                $('#btn_aud_' + jenis).attr('onclick', 'delRowAud(\'' + jenis + '\')');
            });
        }
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

function listTindakanKategoriPoli() {
    if(!cekSession()){
        var option = "<option value=''>[Select One]</option>";
        CheckupDetailAction.getListComboTindakanKategori(idPoli, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idKategoriTindakan + "'>" + item.kategoriTindakan + "</option>";
                });
                $('#ra3').html(option);
            } else {
                $('#ra3').html('');
            }
        });
    }
}

function listTindakanPoli(idKategori){
    var option = "<option value=''>[Select One]</option>";
    if (idKategori != '') {
        CheckupDetailAction.getListComboTindakan(idKategori, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.tindakan + "'>" + item.tindakan + "</option>";
                });
                $('#ra4').html(option);
            } else {
                $('#ra4').html('');
            }
        });
    } else {
        $('#ra4').html('');
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

function dataTriase(jenis){
    var data = [];
    var dataCari = [];

    data.push({
        'jenis': 'Merah',
        'isi': 'gelisah/koma',
        'label': 'Keadaan Umum'
    });
    data.push({
        'jenis': 'Merah',
        'isi': '< 12 / > 30x/m',
        'label': 'Pernafasan'
    });
    data.push({
        'jenis': 'Merah',
        'isi': '< 50 / > 150x/m',
        'label': 'Nadi'
    });
    data.push({
        'jenis': 'Merah',
        'isi': 'Dingin',
        'label': 'Akral'
    });
    data.push({
        'jenis': 'Merah',
        'isi': '> 2 detik',
        'label': 'CRT'
    });

    data.push({
        'jenis': 'Kuning',
        'isi': 'Lemah/kesakitan',
        'label': 'Keadaan Umum'
    });
    data.push({
        'jenis': 'Kuning',
        'isi': '21-30x/m',
        'label': 'Pernafasan'
    });
    data.push({
        'jenis': 'Kuning',
        'isi': '101-150x/m',
        'label': 'Nadi'
    });
    data.push({
        'jenis': 'Kuning',
        'isi': 'Dingin',
        'label': 'Akral'
    });
    data.push({
        'jenis': 'Kuning',
        'isi': '< 2 detik',
        'label': 'CRT'
    });

    data.push({
        'jenis': 'Hijau',
        'isi': 'Cukup',
        'label': 'Keadaan Umum'
    });
    data.push({
        'jenis': 'Hijau',
        'isi': '12-20x/m',
        'label': 'Pernafasan'
    });
    data.push({
        'jenis': 'Hijau',
        'isi': '50-100x/m',
        'label': 'Nadi'
    });
    data.push({
        'jenis': 'Hijau',
        'isi': 'Hangat',
        'label': 'Akral'
    });
    data.push({
        'jenis': 'Hijau',
        'isi': '< 2 detik',
        'label': 'CRT'
    });

    data.push({
        'jenis': 'Hitam',
        'isi': 'Meninggal',
        'label': 'Keadaan Umum'
    });
    data.push({
        'jenis': 'Hitam',
        'isi': 'Kaku Mayat',
        'label': 'Pernafasan'
    });
    data.push({
        'jenis': 'Hitam',
        'isi': 'Lebam Mayat',
        'label': 'Nadi'
    });
    data.push({
        'jenis': 'Hitam',
        'isi': 'Refleks Kornea (-)',
        'label': 'Akral'
    });

    if(jenis != ''){
        $.each(data, function (i, item) {
            if(jenis == item.jenis){
                dataCari.push({
                    'jenis': item.jenis,
                    'isi': item.isi,
                    'label': item.label
                });
            }
        });
    }
    return dataCari;
}

function setDataTriase(val){
    if(val != ''){
        var traise = "";
        var warna = val.split("|")[0];
        $.each(dataTriase(warna), function (i, item) {
            traise += '<hr class="garis">\n' +
                '<div class="row">\n' +
                '    <div class="form-group">\n' +
                '        <label class="col-md-offset-3 col-md-3 triase_ugd" id="label_trias_'+i+'">'+item.label+'</label>\n' +
                '        <div class="col-md-6">\n' +
                '            <div class="form-check">\n' +
                '                <input type="checkbox" name="trias_'+i+'" id="tria_'+i+'" value="'+item.isi+'">\n' +
                '                <label for="tria_'+i+'"></label>'+item.isi+' \n' +
                '            </div>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>';
        });
        $('#set_triase').html(traise);
    }else{
       $('#set_triase').html('');
    }
}

function conUGD(jenis, ket){
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show:true, backdrop:'static'});
    $('#save_con_rm').attr('onclick', 'delUGD(\''+jenis+'\', \''+ket+'\')');
}

function delUGD(jenis, ket) {
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
        AsesmenUgdAction.saveDelete(idDetailCheckup, jenis, result, {
            callback: function (res) {
                if (res.status == "success") {
                    stopSpin('delete_'+jenis);
                    $('#warning_aud_' + ket).show().fadeOut(5000);
                    $('#msg_aud_' + ket).text("Berhasil menghapus data...");
                    delRowAud(jenis);
                    detailAud(jenis);
                    kesimpulanAsesmen();
                } else {
                    stopSpin('delete_'+jenis);
                    $('#modal_warning').show().fadeOut(5000);
                    $('#msg_warning').text(res.msg);
                }
            }
        });
    }
}

function kesimpulanAsesmen(){
    var temp = "";
    AsesmenUgdAction.getKesimpulanAsesmen(idDetailCheckup, function (res) {
        if(res.length > 0){
            $.each(res, function (i, item) {
                if(item.tipe == 'kesimpulan'){
                    temp += '<tr bgcolor="#ffebcd"><td>'+item.parameter+'</td><td>'+item.jawaban+'</td></tr>';
                }else{
                    temp += '<tr><td>'+item.parameter+'</td><td>'+item.jawaban+'</td></tr>';
                }
            });
        }
        $('#temp_kesimpulan').html(temp);
    });
}

function cekBox(id, tujuan) {
    if($('#'+id).is(':checked')){
        $('#'+tujuan).show();
    }else{
        $('#'+tujuan).hide();
    }
}