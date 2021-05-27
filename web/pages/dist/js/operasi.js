function showModalOperasi(jenis, idRM, isSetIdRM, flagHide, flagCheck) {
    if (isSetIdRM == "Y") {
        tempidRm = idRM;
    }
    if (isReadRM || flagHide == "Y") {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }
    if ("tindakan_medis_op" == jenis) {
        selectOptionTM('op', jenis);
        $('#form-'+jenis).hide();
    }
    if("ceklist_operasi" == jenis){
        $('#h_cek').val(flagCheck);
    }else{
        var cekForm = $('#h_cek').val();
        if("pre_operasi" == jenis || "kondisi_pasien" == jenis){
            if("Y" == cekForm){
                $('.btn-check').hide();
            }else{
                $('.btn-check').show();
            }
        }else{
            $('#h_cek').val("N");
        }
    }
    setDataPasien();
    $('#modal-op-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveDataOperasi(jenis, ket) {
    var data = [];
    var cek = false;
    var mlt = false;
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }

    if ("pre_operasi" == jenis) {
        var cekList1 = $('[name=cek_list1]:checked').val();
        var cekList11 = $('[name=cek_list11]:checked').val();
        var cekList2 = $('[name=cek_list2]:checked').val();
        var cekList22 = $('[name=cek_list22]:checked').val();
        var cekList3 = $('[name=cek_list3]:checked').val();
        var cekList33 = $('[name=cek_list33]:checked').val();
        var cekList4 = $('[name=cek_list4]:checked').val();
        var cekList44 = $('[name=cek_list44]:checked').val();
        var cekList5 = $('[name=cek_list5]:checked').val();
        var cekList55 = $('[name=cek_list55]:checked').val();
        var cekList6 = $('[name=cek_list6]:checked').val();
        var cekList66 = $('[name=cek_list66]:checked').val();
        var cekList7 = $('[name=cek_list7]:checked').val();
        var cekList77 = $('[name=cek_list77]:checked').val();
        var cekList8 = $('[name=cek_list8]:checked').val();
        var cekList88 = $('[name=cek_list88]:checked').val();
        var cekList9 = $('[name=cek_list9]:checked').val();
        var cekList99 = $('[name=cek_list99]:checked').val();
        var cekList10 = $('[name=cek_list10]:checked').val();
        var cekList1010 = $('[name=cek_list1010]:checked').val();
        var cekList011 = $('[name=cek_list11]:checked').val();
        var cekList1111 = $('[name=cek_list1111]:checked').val();
        var cekList012 = $('[name=cek_list12]:checked').val();
        var cekList1212 = $('[name=cek_list1212]:checked').val();
        var cekList013 = $('[name=cek_list13]:checked').val();
        var cekList1313 = $('[name=cek_list1313]:checked').val();
        var cekList014 = $('[name=cek_list14]:checked').val();
        var tCekList14 = "";
        if (cekList014 == "Ya") {
            var checbox = $('[name=cek_ket_list14]');
            $.each(checbox, function (i, item) {
                if (item.checked) {
                    if (tCekList14 != '') {
                        tCekList14 = tCekList14 + ', ' + item.value;
                    } else {
                        tCekList14 = item.value;
                    }
                }
            });
        } else {
            tCekList14 = cekList014;
        }

        var cekList15 = $('#cek_list15').val();
        var cekList16 = $('#cek_list16').val();

        var va1 = $('#tgl1').val();
        var va2 = $('#jam1').val();
        var va3 = $('#ruangan1').val();
        var va4 = $('#diagnosa1').val();
        var va5 = $('#tindakan1').val();
        var va6 = $('#operator1').val();

        if (tCekList14 && cekList15 && cekList16 != '') {
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban1': va1 + ' ' + va2,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ruangan',
                'jawaban1': va3,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa',
                'jawaban1': va4,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan Operasi',
                'jawaban1': va5,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter Operasi',
                'jawaban1': va6,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Persiapan Pasien Pre Operasi',
                'jawaban1': 'Perawat Pengirim',
                'jawaban2': 'Perawat OK',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'bold',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Pembatasan Nutrisi Per Oral(Puasa)',
                'jawaban1': cekList1,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Laboratorium',
                'jawaban1': cekList2,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Radiologi (Thorax Foto, USG, Scan)',
                'jawaban1': cekList3,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan ECG',
                'jawaban1': cekList4,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Edukasi dan Informed Consent Bedah',
                'jawaban1': cekList5,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penandaan Area Operasi',
                'jawaban1': cekList6,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi Dokter Spesialis Anestesi',
                'jawaban1': cekList7,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Edukasi dan Informed Consent Anestesi',
                'jawaban1': cekList8,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi Dokter Kardiologi ACC Operasi dengan Resiko: Ringan/Sedang/berat Cardiac Risk Index Grade I/II/III/IV',
                'jawaban1': cekList9,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi Dokter Spesialis Penyakit Dalam',
                'jawaban1': cekList10,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Schiren / Cukur',
                'jawaban1': cekList011,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Melepas Perhiasan, Soft Lens, Gigi Palsu, DLL',
                'jawaban1': cekList012,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Persiapan Prosuk Darah',
                'jawaban1': cekList013,
                'jawaban2': "N",
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit',
                'jawaban1': tCekList14,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan',
                'jawaban1': cekList15 + ' Kg',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tinggi Badan',
                'jawaban1': cekList16 + ' cm',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("kondisi_pasien" == jenis) {
        var cekList171 = $('#cek_list171').val();
        var cekList172 = $('#cek_list172').val();
        var cekList181 = $('#cek_list181').val();
        var cekList182 = $('#cek_list182').val();
        var cekList191 = $('#cek_list191').val();
        var cekList192 = $('#cek_list192').val();
        var cekList201 = $('#cek_list201').val();
        var cekList202 = $('#cek_list202').val();
        var cekList211 = $('#cek_list211').val();
        var cekList212 = $('#cek_list212').val();
        var cekList221 = $('#cek_list221').val();
        var cekList222 = $('#cek_list222').val();
        var cekList231 = $('#cek_list231').val();
        var cekList232 = $('#cek_list232').val();
        var cekList241 = $('#cek_list241').val();
        var cekList242 = $('#cek_list242').val();

        var canvasTtdPengirim = document.getElementById("ttd_pengirim");
        var canvasTtdPerawat = document.getElementById("ttd_perawat");

        var tes1 = isBlank(canvasTtdPengirim);
        var tes2 = isBlank(canvasTtdPerawat);

        var pengirim = $('#nama_terang_pengirim').val();
        var perawat = $('#nama_terang_perawat').val();
        var sip1 = $('#sip_terang_pengirim').val();
        var sip2 = $('#sip_terang_perawat').val();

        if (cekList171 && cekList172 && cekList181 && cekList182 && cekList191 && cekList192 && cekList201 && cekList202 &&
            cekList211 && cekList212 && cekList221 && cekList222 && cekList231 && cekList232 && cekList241 && cekList242 && pengirim && !tes1) {
            data.push({
                'parameter': 'Kesadaran Umum',
                'jawaban1': cekList171,
                'jawaban2': cekList172,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesadaran / GCS',
                'jawaban1': cekList181,
                'jawaban2': cekList182,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban1': replaceUnderLine(cekList191) + ' mmHg',
                'jawaban2': replaceUnderLine(cekList191) + ' mmHg',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban1': cekList201 + ' ˚C',
                'jawaban2': cekList202 + ' ˚C',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban1': cekList211 + ' x/menit',
                'jawaban2': cekList212 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Respirator',
                'jawaban1': cekList221 + ' x/menit',
                'jawaban2': cekList222 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'DJJ',
                'jawaban1': cekList231 + ' x/menit',
                'jawaban2': cekList232 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skala Nyeri',
                'jawaban1': cekList241 + ' x/menit',
                'jawaban2': cekList242 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });

            var ttd1 = canvasTtdPengirim.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");

            var ttd2 = canvasTtdPerawat.toDataURL("image/png"),
                ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

            if(tes1){
                ttd1 = "";
            }
            data.push({
                'parameter': 'TTD Perawat Pengirim',
                'jawaban1': ttd1,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'ttd',
                'nama_terang': pengirim,
                'sip': sip1,
                'id_detail_checkup': idDetailCheckup
            });
            if(tes2){
                ttd2 = "";
            }
            data.push({
                'parameter': 'TTD Perawat Kamar Operasi',
                'jawaban1': ttd2,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'tipe': 'ttd',
                'nama_terang': perawat,
                'sip': sip2,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("ttd-penandaan" == jenis) {
        var canvasTtdPasien = document.getElementById("op_ttd_pasien");
        var canvasTtdDokter = document.getElementById("op_ttd_dokter");
        var canvasArea = document.getElementById('area_canvas');
        var tes1 = isBlank(canvasTtdPasien);
        var tes2 = isBlank(canvasTtdDokter);
        var nama1 = $('#nama_terang_pasien').val();
        var nama2 = $('#nama_terang_dokter').val();
        var sip2 = $('#sip_dokter').val();
        if (!tes1 && !tes2 && nama1 && nama2 && sip2 != '') {

            var areaPenanda = canvasArea.toDataURL("image/png"),
                areaPenanda = areaPenanda.replace(/^data:image\/(png|jpg);base64,/, "");

            var ttdPasien = canvasTtdPasien.toDataURL("image/png"),
                ttdPasien = ttdPasien.replace(/^data:image\/(png|jpg);base64,/, "");

            var ttdDokter = canvasTtdDokter.toDataURL("image/png"),
                ttdDokter = ttdDokter.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Pendanaad Area Operasi',
                'jawaban1': areaPenanda,
                'keterangan': 'penandaan_area',
                'jenis': 'penandaan',
                'tipe': 'penanda',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Tanda Tangan Pasien',
                'jawaban1': ttdPasien,
                'keterangan': 'penandaan_area',
                'jenis': 'penandaan',
                'nama_terang': nama1,
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Tanda Tangan Dokter',
                'jawaban1': ttdDokter,
                'keterangan': 'penandaan_area',
                'jenis': 'penandaan',
                'tipe': 'ttd',
                'nama_terang': nama2,
                'sip': sip2,
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
            mlt = true;
        }
    }

    if ("anamnesa" == jenis) {
        var anme1 = $('#cek_anamnesa1').val();
        var anme2 = $('[name=cek_anamnesa2]:checked').val();
        var anme3 = $('[name=cek_anamnesa3]:checked').val();
        var anme4 = $('[name=cek_anamnesa4]:checked').val();
        var anme5 = $('#cek_anamnesa5').val();
        if (anme2 && anme3 && anme4 != undefined && anme5 && anme1 != '') {
            data.push({
                'parameter': 'Alergi',
                'jawaban1': anme1,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Asthma',
                'jawaban1': anme2,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat DM',
                'jawaban1': anme3,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Hipertensi',
                'jawaban1': anme4,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Lain',
                'jawaban1': anme5,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pemeriksaan_fisik" == jenis) {
        var pf1 = $('[name=cek_pf1]:checked').val();
        var pf2 = $('[name=cek_pf2]:checked').val();
        var pf3 = $('[name=cek_pf3]:checked').val();
        var pf4 = $('[name=cek_pf4]:checked').val();
        var pf5 = $('[name=cek_pf5]:checked').val();
        var pf6 = $('[name=cek_pf6]:checked').val();
        var pf7 = $('[name=cek_pf7]:checked').val();
        var pf8 = $('[name=cek_pf8]:checked').val();
        var pf9 = $('#cek_pf9').val();
        var pf10 = $('#cek_pf10').val();
        var pf11 = $('#cek_pf111').val();
        var pf12 = $('#cek_pf122').val();
        var pf13 = $('#cek_pf133').val();
        var pf14 = $('#cek_pf144').val();
        var pf15 = $('#cek_pf155').val();
        var pf16 = $('#cek_pf166').val();
        var pf17 = $('[name=cek_pf177]:checked').val();
        if (pf1 && pf2 && pf3 && pf4 && pf5 && pf6 && pf7 && pf8 && pf17 != undefined
            && pf9 && pf10 && pf11 && pf12 && pf13 && pf14 && pf15 && pf16 != '') {

            data.push({
                'parameter': 'Keadaan Umum',
                'jawaban1': pf1,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nafas',
                'jawaban1': pf2,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Gigi Palsu',
                'jawaban1': pf3,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jalan Nafas',
                'jawaban1': pf4,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suara Nafas',
                'jawaban1': pf5,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Capillary Refill Time',
                'jawaban1': pf6,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perfusi',
                'jawaban1': pf7,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesadaran',
                'jawaban1': pf8,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban1': replaceUnderLine(pf9) + ' mmHg',
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban1': pf10 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban1': pf11 + ' C',
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Respirator Rate',
                'jawaban1': pf12 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan',
                'jawaban1': pf13 + ' kg',
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tinggi Badan',
                'jawaban1': pf14 + ' cm',
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kemampuan Buka Mulut',
                'jawaban1': pf15,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skala Malampahty',
                'jawaban1': pf16,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Gerak Leher',
                'jawaban1': pf17,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("pemeriksaan_penunjang" == jenis) {
        var pp1 = $('#pp1').val();
        if (pp1 != '') {
            data.push({
                'parameter': 'Pemeriksaan Penunjang',
                'jawaban1': pp1,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("status_fisik" == jenis) {
        var st1 = $('[name=cek_st1]:checked').val();
        var st2 = $('[name=cek_st2]:checked').val();
        var st3 = $('[name=cek_st3]');
        var tSt3 = "";
        $.each(st3, function (i, item) {
            if (st3[i].checked) {
                if (tSt3 != '') {
                    tSt3 = tSt3 + ', ' + st3[i].value;
                } else {
                    tSt3 = st3[i].value;
                }
            }
        });

        if (st1 && st2 != undefined && tSt3 != '') {
            data.push({
                'parameter': 'Status Fisik Anestesi',
                'jawaban1': st1,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'ASA',
                'jawaban1': st2,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Teknik Anestesi',
                'jawaban1': tSt3,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("persiapan_anestesi" == jenis) {
        var pa1 = $('#pa1').val();
        var pa2 = $('#pa2').val();
        var pa3 = $('#pa3').val();
        var nama = $('#nama_terang_spesialis').val();
        var sip = $('#sip_spesialis').val();

        var ttd1 = document.getElementById("ttd_spesialis");
        var cekTtd1 = isBlank(ttd1);

        if (nama && sip && pa1 && pa2 && pa3 != '' && !cekTtd1) {
            data.push({
                'parameter': 'Makan minum terakhir',
                'jawaban1': pa1,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Infus',
                'jawaban1': pa2,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Obat pre medikasi',
                'jawaban1': pa3,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': 'TTD Dokter Spesialis Anestesi',
                'jawaban1': canv1,
                'keterangan': jenis,
                'jenis': 'pra_anestesi',
                'tipe': 'ttd',
                'nama_terang': nama,
                'sip': sip,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("tindakan_medis_op" == jenis) {

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
        var sip5 = $('#sip_ttd5').val();

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);
        var cekTtd4 = isCanvasBlank(ttd4);
        var cekTtd5 = isCanvasBlank(ttd5);

        if (nama1 && nama2 && nama3 && nama4 && nama5 && sip1 && va1 && va2 && va3 && va4 && persetujuan != '' && !cekTtd1 && !cekTtd2 && !cekTtd3 && !cekTtd4 && !cekTtd5) {

            data.push({
                'parameter': 'pernyataan',
                'jawaban1': 'Pemberian Informasi dan Persetujuan ' + persetujuan,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal',
                'jawaban1': va1,
                'keterangan': jenis,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter Penanggung Jawab Anestesi',
                'jawaban1': va2,
                'keterangan': jenis,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemberi Informasi',
                'jawaban1': va3,
                'keterangan': jenis,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penerima Informasi',
                'jawaban1': va4,
                'keterangan': jenis,
                'jenis': persetujuan,
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Jenis Informasi',
                'jawaban1': 'Isi Informasi',
                'jawaban2': 'Check Informasi',
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
                    'jawaban2': tdn,
                    'jawaban1': info,
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
                'jawaban1': canv1,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama1,
                'sip':sip1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerima informasi sebagaimana di atas dan telah memahaminya',
                'jawaban1': canv2,
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
                'jawaban1': 'Persetujuan Tindakan Medis',
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'pernyataan',
                'jawaban1': 'Yang bertanda tangan dibawah ini, Saya ' + va5 + ' ' +
                    'tanggal lahir ' + va6 + ', ' + va7 + ' dengan ini menyatakan persetujuan untuk dilakukan tindakan ' + persetujuan + ' ' +
                    'terhadap pasien Bernama ' + va9 + ' tanggal lahir ' + va10 + ', Alamat ' + va11 + '.' +
                    'Saya memahami perlunya dan manfaat tindakan tersebut sebagaimana telah dijelaskan seperti diatas ' +
                    'kepada saya termasuk resiko dan komplikasi yang timbul ' +
                    'Saya juga menyadari bahwa oleh karena itu ilmu kedokteran bukan ilmu pasti, maka keberhasilan tindakan ' +
                    'kedokteran bukan keniscayaan, tetapi tergantung kepada izin Tuhan Yang maha Esa. Tanggal ' + converterDate(new Date) + ', Jam ' + converterTime(new Date())+' WIB',
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD yang menyatakan',
                'jawaban1': canv3,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama3,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Saksi Keluarga',
                'jawaban1': canv4,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama4,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perawat Pendamping',
                'jawaban1': canv5,
                'keterangan': jenis,
                'jenis': persetujuan,
                'tipe': 'ttd',
                'nama_terang':nama5,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("rr_dewasa" == jenis) {

        var pe1 = $('[name=rd1]:checked').val();
        var pe2 = $('[name=rd2]:checked').val();
        var pe3 = $('[name=rd3]:checked').val();
        var pe4 = $('[name=rd4]:checked').val();
        var pe5 = $('[name=rd5]:checked').val();

        if (pe1 && pe2 && pe3 && pe4 && pe5 != undefined) {

            var isi1 = pe1.split("|")[0];
            var isi2 = pe2.split("|")[0];
            var isi3 = pe3.split("|")[0];
            var isi4 = pe4.split("|")[0];
            var isi5 = pe5.split("|")[0];

            var skor1 = pe1.split("|")[1];
            var skor2 = pe2.split("|")[1];
            var skor3 = pe3.split("|")[1];
            var skor4 = pe4.split("|")[1];
            var skor5 = pe5.split("|")[1];

            data.push({
                'parameter': 'Kesadaran',
                'jawaban1': isi1,
                'skor': skor1,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Warna Kulit',
                'jawaban1': isi2,
                'skor': skor2,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Aktifitas',
                'jawaban1': isi3,
                'skor': skor3,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Respirasi',
                'jawaban1': isi4,
                'skor': skor4,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kardio Vaskuler',
                'jawaban1': isi5,
                'skor': skor5,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("rr_anak_anak" == jenis) {

        var pe1 = $('[name=raa1]:checked').val();
        var pe2 = $('[name=raa2]:checked').val();
        var pe3 = $('[name=raa3]:checked').val();

        if (pe1 && pe2 && pe3 != undefined) {

            var isi1 = pe1.split("|")[0];
            var isi2 = pe2.split("|")[0];
            var isi3 = pe3.split("|")[0];

            var skor1 = pe1.split("|")[1];
            var skor2 = pe2.split("|")[1];
            var skor3 = pe3.split("|")[1];

            data.push({
                'parameter': 'Kesadaran',
                'jawaban1': isi1,
                'skor': skor1,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Respirasi',
                'jawaban1': isi2,
                'skor': skor2,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Aktifitas',
                'jawaban1': isi3,
                'skor': skor3,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("rr_sab" == jenis) {

        var pe1 = $('[name=sab1]:checked').val();

        if (pe1 != undefined) {

            var isi1 = pe1.split("|")[0];

            var skor1 = pe1.split("|")[1];

            data.push({
                'parameter': 'Penilaian',
                'jawaban1': isi1,
                'skor': skor1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("add_laporan_operasi" == jenis) {
        var va1 = $('#lap1').val();
        var va2 = $('#lap2').val();
        var va3 = $('#lap3').val();
        var va4 = $('#lap4').val();
        var va5 = $('#lap5').val();
        var va6 = $('#lap6').val();
        var va7 = $('#lap7').val();
        var va8 = $('#lap8').val();
        var va9 = $('#lap9').val();
        var va10 = $('[name=lap10]:checked').val();
        var va11 = $('#lap11').val();
        var va12 = $('#lap12').val();
        var va13 = $('[name=lap13]:checked').val();
        var va14 = $('[name=lap14]:checked').val();
        var va15 = $('[name=lap15]:checked').val();
        var va16 = $('[name=lap16]:checked').val();
        // var va17 = $('#lap17').val();
        var va18 = $('#lap18').val();
        var va19 = $('#lap19').val();
        var nama = $('#nama_terang_sps').val();
        var sip = $('#sip_sps').val();
        var dataTemp = [];

        var isi = $('.urutan_op');

        $.each(isi, function (i, item) {
            if (item.value != '') {
                dataTemp.push({
                    'cek_data': item.value
                });
            }
        });

        var ttd1 = document.getElementById("ttd_lap_dokter");

        var cekTtd1 = isCanvasBlank(ttd1);

        if (!cekTtd1 && isi.length == dataTemp.length && nama && sip != '') {

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Tanggal',
                'jawaban1': va1,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Ahli Bedah',
                'jawaban1': va2,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Asisten Bedah',
                'jawaban1': va3,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Perawat Instrumen',
                'jawaban1': va4,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kelas',
                'jawaban1': va5,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Medis Pre Operasi',
                'jawaban1': va6,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Medis Post Operasi',
                'jawaban1': va7,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan Operasi',
                'jawaban1': va8,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jaringan yang di incisi/excisi',
                'jawaban1': va9,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dikirim untuk pemeriksaan',
                'jawaban1': va10,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pembedahan mulai jam',
                'jawaban1': va11 + ', Selesai Jam ' + va12,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Pembiusan',
                'jawaban1': va13,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kategori Operasi',
                'jawaban1': va14,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Urgensi Operasi',
                'jawaban1': va15,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Operasi',
                'jawaban1': va16,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jumlah Cairan Masuk',
                'jawaban1': va18,
                'keterangan': ket,
                'jenis': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Urutan Operasi',
                'jawaban1': '',
                'keterangan': ket,
                'jenis': jenis,
                'tipe': 'penyataan',
                'id_detail_checkup': idDetailCheckup
            });
            $.each(isi, function (i, item) {
                var count = i + 1;
                var label = $('#label_op_' + count).text();
                if (item.value != '') {
                    data.push({
                        'parameter': label,
                        'jawaban1': item.value,
                        'keterangan': ket,
                        'jenis': jenis,
                        'id_detail_checkup': idDetailCheckup
                    });
                }
            });
            data.push({
                'parameter': 'TTD Dokter Operator',
                'jawaban1': canv1,
                'keterangan': ket,
                'jenis': jenis,
                'tipe': 'ttd',
                'nama_terang': nama,
                'sip': sip,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pra_induksi" == jenis) {

        var va1 = $('#pra1').val();
        var va2 = $('#pra2').val();
        var va3 = $('#pra3').val();
        var va4 = $('#pra4').val();
        var va5 = $('#pra5').val();
        var va6 = $('#pra6').val();
        var va7 = $('#pra7').val();
        var va9 = $('#pra9').val();

        var va11 = $('[name=pra11]:checked').val();
        var va12 = $('[name=pra12]:checked').val();
        var va13 = $('[name=pra13]:checked').val();

        var va14 = $('#pra14').val();
        var va15 = $('#pra15').val();
        var va16 = $('#pra16').val();
        var va17 = $('#pra17').val();
        var va18 = $('#pra18').val();
        var va19 = $('#pra19').val();

        var va20 = $('[name=pra20]:checked').val();

        var va21 = $('#pra21').val();
        var va22 = $('#pra22').val();

        var va23 = $('[name=pra23]:checked').val();
        var va24 = $('[name=pra24]:checked').val();
        var va25 = $('[name=pra25]:checked').val();
        var va26 = $('[name=pra26]:checked').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va9 &&
            va14 && va15 && va16 && va17 && va18 && va19 && va21 && va22 != '' &&
            va11 && va12 && va13 && va20 && va23 && va24 && va25 && va26 != undefined) {

            data.push({
                'parameter': 'Tanggal',
                'jawaban1': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosa Medis',
                'jawaban1': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rencana Tindakan',
                'jawaban1': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter Spesialis Anestesi',
                'jawaban1': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perawat Anestesi',
                'jawaban1': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dokter Bedah',
                'jawaban1': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Asisten dan Instrumen',
                'jawaban1': va7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnesa',
                'jawaban1': "",
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Makan minum terakhir jam',
                'jawaban1': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Fisik',
                'jawaban1': "",
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keadaan Umum',
                'jawaban1': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pernafasan',
                'jawaban1': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kemungkinan sulit manajemen airway',
                'jawaban1': va13,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tensi',
                'jawaban1': va14 + ' mmHg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban1': va15 + ' x/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'RR',
                'jawaban1': va16 + ' x/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban1': va17 + ' C',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan',
                'jawaban1': va18 + ' Kg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tinggi Badan',
                'jawaban1': va19 + ' Cm',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perfusi',
                'jawaban1': va20,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Capillary refil time',
                'jawaban1': va21 + ' detik',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terpasang infus : IV cath no. ',
                'jawaban1': va22 + ', ' + va23,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Psikologis',
                'jawaban1': va24,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'ASA',
                'jawaban1': va25 + ', ' + va26,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("perencanaan_anestesi" == jenis) {

        var va1 = $('[name=per1]');
        var va2 = $('[name=per2]');
        var va3 = $('[name=per3]:checked').val();
        var va4 = $('[name=per4]:checked').val();
        var va5 = $('[name=per5]:checked').val();
        var va6 = $('[name=per6]');
        var va7 = $('[name=per7]:checked').val();
        var va8 = $('[name=per8]:checked').val();
        var va9 = $('#per9').val();
        var va10 = $('[name=per10]:checked').val();
        var va11 = $('[name=per11]:checked').val();
        var va12 = $('[name=per12]:checked').val();

        var v1 = "";
        var v2 = "";
        var v3 = "";

        var ttd1 = document.getElementById("ttd_dokter_spesialis");
        var cekTtd1 = isCanvasBlank(ttd1);
        var nama = $('#nama_terang_sps').val();
        var sip = $('#sip_sps').val();

        $.each(va1, function (i, item) {
            if (item.checked) {
                if (v1 != '') {
                    v1 = v1 + '|' + item.value;
                } else {
                    v1 = item.value;
                }
            }
        });
        $.each(va2, function (i, item) {
            if (item.checked) {
                if (v2 != '') {
                    v2 = v2 + '|' + item.value;
                } else {
                    v2 = item.value;
                }
            }
        });
        $.each(va6, function (i, item) {
            if (item.checked) {
                if (v3 != '') {
                    v3 = v3 + '|' + item.value;
                } else {
                    v3 = item.value;
                }
            }
        });

        if (!cekTtd1 && nama && sip != '') {

            v1 == "" ? v1 = "|" : v1 = v1;
            v2 == "" ? v2 = "-" : v2 = v2;
            v3 == "" ? v3 = "-" : v3 = v3;
            va9 == "" ? va9 = "-" : va9 = va9;

            va3 == undefined ? va3 = "-" : va3 = va3;
            va4 == undefined ? va4 = "-" : va4 = va4;
            va5 == undefined ? va5 = "-" : va5 = va5;
            va7 == undefined ? va7 = "-" : va7 = va7;
            va8 == undefined ? va8 = "-" : va8 = va8;
            va10 == undefined ? va10 = "-" : va10 = va10;
            va11 == undefined ? va11 = "-" : va11 = va11;
            va12 == undefined ? va12 = "-" : va12 = va12;

            data.push({
                'parameter': 'Pre medikasi',
                'jawaban1': v1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'General Anestesi',
                'jawaban1': v2 + '|Induksi dengan : ' + va3 + '|Muscle relaksan : ' + va4 + '|Maintenance : ' + va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Regional Anestesi',
                'jawaban1': v3 + '|Obat-obatan: ' + va7 + '|Adjuvan : ' + va8 + '|Jarum Spinal No. ' + va9 + '|Oksigenasi : ' + va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Sedasi Dalam',
                'jawaban1': 'Obat-obatan : ' + va11 + '|Oksigenasi : ' + va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD Dokter Spesialis',
                'jawaban1': canv1,
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

    if ("add_checklist_keselamatan" == jenis) {

        var va1 = $('#kes1').val();
        var va2 = $('#kes2').val();
        var va3 = $('#kes3').val();
        var va4 = $('#kes4').val();
        var va5 = $('#kes5').val();
        var va6 = $('#kes6').val();
        var va7 = $('#kes7').val();
        var va8 = $('#kes8').val();

        var va9 = $('[name=kes9]:checked').val();
        var va10 = $('[name=kes10]:checked').val();
        var va11 = $('[name=kes11]:checked').val();
        var va12 = $('[name=kes12]:checked').val();
        var va13 = $('[name=kes13]:checked').val();
        var va14 = $('[name=kes14]:checked').val();
        var va15 = $('[name=kes15]:checked').val();
        var va16 = $('[name=kes16]:checked').val();
        var va17 = $('[name=kes17]:checked').val();
        var va18 = $('[name=kes18]:checked').val();
        var va19 = $('[name=kes19]:checked').val();//kolom 1

        var va20 = $('#kes20').val();
        var va21 = $('#kes21').val();
        var va22 = $('#kes22').val();
        var va23 = $('#kes23').val();
        var va24 = $('#kes24').val();
        var va25 = $('[name=kes25]:checked').val();
        var va26 = $('#kes26').val();
        var va27 = $('[name=kes27]');
        var va28 = $('[name=kes28]:checked').val();//kolom 2

        var va29 = $('#kes29').val();
        var va30 = $('[name=kes30]');
        var va31 = $('[name=kes31]:checked').val();
        var va32 = $('[name=kes32]');
        var va33 = $('#kes33').val();

        var nama1 = $('#nama_terang_anestesi').val();
        var nama2 = $('#nama_terang_aperator').val();
        var nama3 = $('#nama_terang_sirkuler').val();
        var sip = $('#sip_aperator').val();

        var v27 = "";
        var v30 = "";
        var v32 = "";

        var ttd1 = document.getElementById("ttd_anestesi");
        var ttd2 = document.getElementById("ttd_sirkuler");
        var ttd3 = document.getElementById("ttd_aperator");

        var cekTtd1 = isCanvasBlank(ttd1);
        var cekTtd2 = isCanvasBlank(ttd2);
        var cekTtd3 = isCanvasBlank(ttd3);

        $.each(va27, function (i, item) {
            if (item.checked) {
                if (v27 != '') {
                    v27 = v27 + ', ' + item.value;
                } else {
                    v27 = item.value;
                }
            }
        });
        $.each(va30, function (i, item) {
            if (item.checked) {
                if (v30 != '') {
                    v30 = v30 + ', ' + item.value;
                } else {
                    v30 = item.value;
                }
            }
        });
        $.each(va32, function (i, item) {
            if (item.checked) {
                if (v32 != '') {
                    v32 = v32 + ', ' + item.value;
                } else {
                    v32 = item.value;
                }
            }
        });

        if (!cekTtd1 && !cekTtd2 && !cekTtd3) {

            data.push({
                'parameter': 'Tanggal',
                'jawaban1': va1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Diagnosa',
                'jawaban1': va2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Operator',
                'jawaban1': va3,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anestesi',
                'jawaban1': va4,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Incisi Jam',
                'jawaban1': va5 + ', Selesai Jam' + va6,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perawat Bedah',
                'jawaban1': va7,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perawat Anastesi',
                'jawaban1': va8,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'colspan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Sebelum Induksi Anestesi (Fase Sign In)',
                'jawaban1': 'Sebelum Incisi (Fase Time Out)',
                'jawaban2': 'Sebelum Check Out Kamar Operasi (Fase Sign Out)',
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'bold',
                'id_detail_checkup': idDetailCheckup
            });

            var k1, t1, ka1 = "";
            var k2, t2, ka2 = "";
            var k3, t3, ka3 = "";
            var k4, t4, ka4 = "";
            var k5, t5, ka5 = "";
            var k6, t6, ka6 = "";
            var k7, t7, ka7 = "";
            var k8, t8, ka8 = "";
            var k9, t9, ka9 = "";
            var k10, t10, ka10 = "";
            var k11, t11, ka11 = "";
            var k12, t12, ka12 = "";
            var k13, t13, ka13 = "";

            if (va9 != undefined) {
                k1 = va9 + ', Ya';
            }
            if (va10 != undefined) {
                k2 = va10 + ', Ya';
            }
            if (va11 != undefined) {
                k3 = '3. Penandaan area operasi, ' + va11;
            }
            if (va12 != undefined) {
                k4 = 'a. Riwayat alergi, ' + va12;
            }
            if (va13 != undefined) {
                k5 = 'b. Kesulitan manajemen jalan nafas, ' + va13;
            }
            if (va14 != undefined) {
                k6 = 'c. Resiko kehilangan darah > 500ml atau > (70ml/kg BB) pada pasien anak, ' + va14;
            }
            if (va15 != undefined) {
                k7 = 'a. Mesin anestesi, ' + va15;
            }
            if (va16 != undefined) {
                k8 = 'b. Lampu operasi, ' + va16;
            }
            if (va17 != undefined) {
                k9 = 'c. Elektro surgical unit, ' + va17;
            }
            if (va18 != undefined) {
                k10 = 'd. Monitor hemodinamik, ' + va18;
            }
            if (va19 != undefined) {
                k11 = 'e. Suction sentral, ' + va19;
            }

            if (va20 != '') {
                t1 = "1. Prosedur yang akan dilakukan ? " + va20;
            }
            if (va21 != '') {
                t2 = "2. Perkiraan lama operasi ? " + va21;
            }
            if (va22 != '') {
                t3 = "3. Antisipasi kehilangan darah ? " + va22;
            }
            if (va23 != '') {
                t4 = "4. Antibiotik profilaksis ? " + va23;
            }
            if (va24 != '') {
                t5 = "Adakah perhatian khusus dalam pembiusan ? " + va24;
            }
            if (va25 != undefined) {
                t6 = "Adakah instrumen telah steril ? " + va25;
            }
            if (va26 != '') {
                t7 = "Lokasi pasien plate ? " + va26 + ' Tourniquet';
            }
            if (v27 != '') {
                t8 = v27;
            }
            if (va28 != undefined) {
                t9 = "Adakah implan telah tersedia sesuai kebutuhan ? " + va28;
            }

            if (va29 != '') {
                ka1 = "1. Tindakan/ prosedur yang telah dilakukan ? " + va29;
            }
            if (v30 != '') {
                ka2 = '2. Apakah instrumen, kasa, jarum telah dihitung dan lengkap ? ' + v30;
            }
            if (va31 != undefined) {
                ka3 = "3. Apakah spesimen pa diberi label nama ? Ya";
            }
            if (v32 != undefined) {
                ka4 = '4. ' + v32;
            }
            if (va33 != undefined) {
                ka5 = "5. Perhatian khusus di masa pemulihan ? " + va33;
            }

            data.push({
                'parameter': k1,
                'jawaban1': t1,
                'jawaban2': ka1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': k2,
                'jawaban1': t2,
                'jawaban2': ka2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': k3,
                'jawaban1': t3,
                'jawaban2': ka3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': k4,
                'jawaban1': t4,
                'jawaban2': ka4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': k5,
                'jawaban1': t5,
                'jawaban2': ka5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': k6,
                'jawaban1': t6,
                'jawaban2': ka6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': k7,
                'jawaban1': t7,
                'jawaban2': ka7,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': k8,
                'jawaban1': t8,
                'jawaban2': ka8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': k9,
                'jawaban1': t9,
                'jawaban2': ka9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': k10,
                'jawaban1': t10,
                'jawaban2': ka10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': k11,
                'jawaban1': t11,
                'jawaban2': ka11,
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
                'parameter': 'TTD Dokter Anestesi',
                'jawaban1': canv1,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Perawat Sikuler',
                'jawaban1': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama2,
                'sip': sip,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TTD Operator',
                'jawaban1': canv3,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama3,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("data_intra_anestesi" == jenis) {

        var va1 = $('[name=dt1]');
        var va2 = $('[name=dt2]');
        var va3 = $('[name=dt3]');
        var va4 = $('[name=dt4]');
        var va5 = $('#dt5').val();
        var va6 = $('#dt6').val();
        var va7 = $('#dt7').val();
        var va8 = $('#dt8').val();

        var va9 = $('[name=dt09]');

        var va10 = $('[name=dt13]:checked').val();
        var va11 = $('#dt014').val();

        var va12 = $('[name=dt015]');
        var va13 = $('[name=dt016]');

        var va14 = $('#dt017').val();
        var va15 = $('#dt018').val();
        var va16 = $('#dt019').val();
        var va17 = $('#dt020').val();
        var va18 = $('#dt021').val();
        var va19 = $('#dt022').val();
        var va20 = $('#dt023').val();
        var va21 = $('#dt024').val();

        var v1 = "";
        var v2 = "";
        var v3 = "";
        var v4 = "";
        var v9 = "";
        var v12 = "";
        var v13 = "";
        var nama = $('#nama_terang_sps').val();
        var sip = $('#sip_sps').val();

        $.each(va1, function (i, item) {
            if (item.checked) {
                if (v1 != '') {
                    v1 = v1 + ',' + item.value;
                } else {
                    v1 = item.value;
                }
            }
        });
        $.each(va2, function (i, item) {
            if (item.checked) {
                if (v2 != '') {
                    v2 = v2 + ',' + item.value;
                } else {
                    v2 = item.value;
                }
            }
        });
        $.each(va3, function (i, item) {
            if (item.checked) {
                if (v3 != '') {
                    v3 = v3 + ',' + item.value;
                } else {
                    v3 = item.value;
                }
            }
        });
        $.each(va4, function (i, item) {
            if (item.checked) {
                if (v4 != '') {
                    v4 = v4 + ',' + item.value;
                } else {
                    v4 = item.value;
                }
            }
        });
        $.each(va9, function (i, item) {
            if (item.value != '') {
                if (v9 != '') {
                    v9 = v9 + ',' + item.value;
                } else {
                    v9 = item.value;
                }
            }
        });
        $.each(va12, function (i, item) {
            if (item.value != '') {
                if (v12 != '') {
                    v12 = v12 + '|' + i + 1 + '. ' + item.value;
                } else {
                    v12 = i + 1 + '. ' + item.value;
                }
            }
        });
        $.each(va13, function (i, item) {
            if (item.value != '') {
                if (v13 != '') {
                    v13 = v13 + '|' + i + 1 + '. ' + item.value;
                } else {
                    v13 = i + 1 + '. ' + item.value;
                }
            }
        });

        var ttd1 = document.getElementById("ttd1");

        var cekTtd1 = isCanvasBlank(ttd1);

        if (!cekTtd1 && nama && sip != '') {

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Teknik Anestesi',
                'jawaban1': v1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pernafasan',
                'jawaban1': v2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Posisi',
                'jawaban1': v3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Endotracheal',
                'jawaban1': v4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Midazolam',
                'jawaban1': va5 + ' mg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pethidin',
                'jawaban1': va6 + ' mg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Sulfas Atropin',
                'jawaban1': va7 + ' mg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Fentanyl',
                'jawaban1': va8 + ' ug',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lain-Lain',
                'jawaban1': v9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemberian',
                'jawaban1': va10 + ', Jam ' + va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Induksi',
                'jawaban1': v12,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'li',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Medikasi Lain',
                'jawaban1': v13,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'li',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jumlah Intakea Cairan',
                'jawaban1': va14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perdarahan',
                'jawaban1': va15 + ' ml',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Urine',
                'jawaban1': va16 + ' ml',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Bayi Lahir Jam',
                'jawaban1': va17,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kel',
                'jawaban1': va18,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'AS',
                'jawaban1': va19,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'BB',
                'jawaban1': va20,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'PB',
                'jawaban1': va21,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': 'TTD',
                'jawaban1': canv1,
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

    if ("add_instruksi_pasca_anestesi" == jenis) {

        var va1 = $('#in1').val();
        var va2 = $('[name=in2]:checked').val();
        var va3 = $('[name=in3]:checked').val();
        var va4 = $('[name=in4]:checked').val();
        var va5 = $('#in5').val();
        var va6 = $('#in6').val();
        var va7 = $('#in7').val();
        var va8 = $('#in8').val();
        var va9 = $('#in9').val();
        var va10 = $('#in10').val();
        var va11 = $('#in11').val();
        var va12 = $('#in12').val();
        var va13 = $('#in13').val();
        var va14 = $('#in14').val();
        var va15 = $('#in15').val();
        var nama = $('#nama_terang_sps').val();
        var sip = $('#sip_sps').val();

        var ttd1 = document.getElementById("ttd1");
        var cekTtd1 = isCanvasBlank(ttd1);

        if (va2 && va3 && va4 != undefined && va1 && va5 && va6 && nama && sip &&
            va7 && va8 && va9 && va10 && va11 && va12 && va13 && va14 && va15 != '' && !cekTtd1) {

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Keadaaan di akhir pembedahan jam',
                'jawaban1': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keadaan Umum',
                'jawaban1': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pernafasan',
                'jawaban1': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perfusi',
                'jawaban1': va4 + ', Capilarry refill time ' + va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tensi',
                'jawaban1': va6 + ' mmHg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban1': va7 + ' x/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'RR',
                'jawaban1': va8 + ' x/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban1': va9 + ' C',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Awasi',
                'jawaban1': 'Keadaan Umum Tensi Nadi Pernafasan Suhu Perdarahan setiap ' + va10 + ' menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Posisi',
                'jawaban1': va11,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Makan/Minum',
                'jawaban1': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '4. Infus/Transfusi',
                'jawaban1': va13,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '5. Obat-Obatan',
                'jawaban1': va14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '6. Lain-Lain',
                'jawaban1': va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': 'TTD Dokter Spesialis Anestesi',
                'jawaban1': canv1,
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

    if ("data_pasca_anestesi" == jenis) {

        var va1 = $('#in1').val();
        var va2 = $('[name=in2]:checked').val();
        var va3 = $('[name=in3]:checked').val();
        var va4 = $('[name=in4]:checked').val();
        var va5 = $('#in5').val();

        var va6 = $('#in6').val();
        var va7 = $('#in7').val();
        var va8 = $('#in8').val();
        var va9 = $('#in9').val();
        var va10 = $('#in10').val();
        var va11 = $('#in11').val();
        var va12 = $('#in12').val();
        var va13 = $('#in13').val();
        var va14 = $('#in14').val();
        var va15 = $('#in15').val();
        var va16 = $('#in16').val();
        var va17 = $('#in17').val();
        var va18 = $('#in18').val();
        var nama = $('#nama_terang_sps').val();
        var sip = $('#sip_sps').val();

        var ttd1 = document.getElementById("ttd1");
        var cekTtd1 = isCanvasBlank(ttd1);

        if (va2 && va3 && va4 != undefined && va1 && va5 && va6 &&
            va7 && va8 && va9 && va10 && va11 && va12 && va13 && va14 && va15 &&
            va16 && va17 && va18 && nama && sip != '' && !cekTtd1) {

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Pasien masuk Ruang Recovery jam',
                'jawaban1': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keadaan Umum',
                'jawaban1': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pernafasan',
                'jawaban1': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perfusi',
                'jawaban1': va4 + ', Capilarry refill time ' + va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pindah ruangan jam',
                'jawaban1': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keadaan saat pindah ruangan',
                'jawaban1': 'KU ' + va7 + ', GCS ' + va8,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tensi',
                'jawaban1': va9 + ' mmHg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban1': va10 + ' x/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'RR',
                'jawaban1': va11 + ' x/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban1': va12 + ' C',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jumlah Perdarahan paska op',
                'jawaban1': va13 + 'CC',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'TFU',
                'jawaban1': va14 + ', Hb : ' + va15,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penilaian pasca anestesi dengan',
                'jawaban1': '',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Aldrette score',
                'jawaban1': va16,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Bromage score',
                'jawaban1': va17,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Steward score',
                'jawaban1': va18,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': 'TTD Perawat Ruang Recovery',
                'jawaban1': canv1,
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

    if ("add_pra_bedah" == jenis) {
        var tgl = $('#tgl1').val();
        var jam = $('#jam1').val();
        var pa1 = $('#pra1').val();
        var pa2 = $('#pra2').val();
        var pa3 = $('#pra3').val();
        var pa4 = $('#pra4').val();
        var pa5 = $('#pra5').val();
        var pa6 = $('#pra6').val();
        var pa7 = $('#pra7').val();
        var pa8 = $('#pra8').val();
        var pa9 = $('#pra9').val();
        var pa10 = $('#pra10').val();
        var pa11 = $('#pra11').val();
        var pa12 = $('[name=pra12]:checked').val();
        var pa13 = $('#pra13').val();
        var pa14 = $('#pra14').val();

        var nama = $('#nama_dpjp_edit').val();
        var sip = $('#sip_dpjp_edit').val();

        var ttd1 = document.getElementById("ttd_edit");
        var cekTtd1 = isBlank(ttd1);

        if (nama && sip && pa1 && pa2 && pa3 && pa4 && pa5 && pa6 &&
            pa7 && pa8 && pa9 && pa10 && pa11 && pa13 && pa14 != ''
            && !cekTtd1 && pa12 != undefined) {

            data.push({
                'parameter': 'Tanggal',
                'jawaban1': tgl+' '+jam,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Keluhan',
                'jawaban1': pa1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Riwayat penyakit pasien dan keluarga',
                'jawaban1': pa2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Riwayat Alergi',
                'jawaban1': 'Obat-obatan : '+pa3+', Makanan : '+pa4+', Suhu/cuaca : '+pa5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '4. Pemeriksaan Fisik',
                'jawaban1': pa6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '5. Keadaan Pra Bedah',
                'jawaban1': 'Tinggi Badan : '+pa7+' Cm, Berat Badan : '+pa9+' Kg, Tekanan Darah : '+replaceUnderLine(pa11)+' mmHg, Nadi : '+pa8+' x/menit, Suhu : '+pa10+' ˚C, Status Gizi : '+pa12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '6. Pemeriksaan Penunjang',
                'jawaban1': pa13,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '7. Analisa Data dan Rencana Tindakan',
                'jawaban1': pa14,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = ttd1.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'TTD DPJP',
                'jawaban1': canv1,
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

    if (cek) {
        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            delRowOperasi(jenis);
            $('#save_op_' + jenis).hide();
            $('#load_op_' + jenis).show();
            dwr.engine.setAsync(true);
            AsesmenOperasiAction.saveAsesmenOperasi(result, pasienData, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_op_' + jenis).show();
                        $('#load_op_' + jenis).hide();
                        $('#modal-op-' + jenis).modal('hide');
                        if (mlt) {
                            $('#modal-op-' + jenis + "-mlt").modal('hide');
                        }
                        $('#warning_op_' + ket).show().fadeOut(5000);
                        $('#msg_op_' + ket).text("Berhasil menambahkan data operasi...");
                        $('#modal-op-' + jenis).scrollTop(0);
                        detailOperasi(jenis);
                    } else {
                        $('#save_op_' + jenis).show();
                        $('#load_op_' + jenis).hide();
                        $('#warning_op_' + jenis).show().fadeOut(5000);
                        $('#msg_op_' + jenis).text(res.msg);
                        $('#modal-op-' + jenis).scrollTop(0);
                    }
                }
            });
        }
    } else {
        $('#warning_op_' + jenis).show().fadeOut(5000);
        $('#msg_op_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-op-' + jenis).scrollTop(0);
    }
}

function detailOperasi(jenis) {
    if(!cekSession()){
        if (jenis != '') {
            var body = "";
            var head = "";
            var firt = "";
            var last = "";
            var tgl = "";
            var totalSkor = 0;
            var cekData = false;
            var data = [];

            AsesmenOperasiAction.getListAsesmenOperasi(noCheckup, jenis, function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        if ("pre_operasi" == item.keterangan) {
                            var jwb1 = "";
                            var jwb2 = "";
                            var temp = "";

                            if (item.jawaban1 != null) {
                                jwb1 = item.jawaban1;
                            }
                            if (item.jawaban2 != null) {
                                jwb2 = item.jawaban2;
                            }
                            if(jwb2 == "N"){
                                temp =
                                    '<div id="set_'+jenis+i+'"><div class="form-check">\n' +
                                    '<input type="checkbox" name="cek_'+i+'" id="'+jenis+i+'" value="Ya">\n' +
                                    '<label for="'+jenis+i+'"></label>\n' + '</div>' +
                                    '<button style="margin-top: -2px" class="btn btn-success btn-sm" onclick="saveSerahTerima(\''+item.idAsesmenOperasi+'\', \''+i+'\',\''+item.parameter+'\', \''+jenis+'\')"><i class="fa fa-check"></i></button></div>';
                            }else{
                                temp = checkIcon(jwb2);
                            }
                            if ("colspan" == item.tipe) {
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td colspan="2">' + jwb1 + '</td>' +
                                    '</tr>';
                            } else if ("bold" == item.tipe) {
                                body += '<tr style="font-weight: bold">' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td align="center">' + jwb1 + '</td>' +
                                    '<td align="center" style="vertical-align: middle">' + jwb2 + '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td align="center">' + checkIcon(jwb1) + '</td>' +
                                    '<td align="center">' + temp + '</td>' +
                                    '</tr>';
                            }
                        } else if ("kondisi_pasien" == item.keterangan) {
                            var jwb1 = "";
                            var jwb2 = "";

                            var diterima = "";
                            var tempDiterima = "";

                            if (item.jawaban1 != null) {
                                jwb1 = item.jawaban1;
                            }
                            if (item.jawaban2 != null) {
                                jwb2 = item.jawaban2.split("|")[0];
                                diterima = item.jawaban2.split("|")[1];
                            }

                            if(diterima != undefined){
                                tempDiterima = diterima;
                            }else{
                                if ("Kesadaran Umum" == item.parameter) {
                                    tempDiterima = '<div id="set_'+jenis+i+'">' +
                                        '<div class="input-group" style="width: 100%">' +
                                        '<select id="'+jenis+i+'" class="form-control">\n' +
                                        '<option value="">[Select One]</option>\n' +
                                        '<option value="Baik">Baik</option>\n' +
                                        '<option value="Cukup">Cukup</option>\n' +
                                        '<option value="Lemah">Lemah</option>\n' +
                                        '<option value="Jelek">Jelek</option>\n' +
                                        '</select>' +
                                        '<div onclick="saveSerahTerima(\''+item.idAsesmenOperasi+'\', \''+i+'\',\''+item.parameter+'\', \''+jenis+'\')" class="input-group-addon" style="cursor: pointer; background-color: #449d44">' +
                                        '<i class="fa fa-check" style="color: white"></i>' +
                                        '</div>' +
                                        '</div></div>';
                                } else if ("Tekanan Darah" == item.parameter) {
                                    tempDiterima = '<div id="set_'+jenis+i+'">' +
                                        '<div class="input-group">' +
                                        '<input id="'+jenis+i+'" class="form-control" data-inputmask="\'mask\': [\'999/999\']" data-mask="">' +
                                        '<div onclick="saveSerahTerima(\''+item.idAsesmenOperasi+'\', \''+i+'\', \''+item.parameter+'\', \''+jenis+'\')" class="input-group-addon" style="cursor: pointer; background-color: #449d44">' +
                                        '<i class="fa fa-check" style="color: white"></i>' +
                                        '</div>' +
                                        '</div></div>';
                                } else {
                                    var tip = 'type="number"';
                                    if("Lain-Lain" == item.parameter){
                                        tip = "";
                                    }
                                    tempDiterima = '<div id="set_'+jenis+i+'">' +
                                        '<div class="input-group">' +
                                        '<input id="'+jenis+i+'" class="form-control" '+tip+'>' +
                                        '<div onclick="saveSerahTerima(\''+item.idAsesmenOperasi+'\', \''+i+'\', \''+item.parameter+'\', \''+jenis+'\')" class="input-group-addon" style="cursor: pointer; background-color: #449d44">' +
                                        '<i class="fa fa-check" style="color: white"></i>' +
                                        '</div>' +
                                        '</div></div>';
                                }
                            }

                            if ("ttd" == item.tipe) {
                                var tempTtd = '<i onclick="setTtdPerawat(\''+item.idAsesmenOperasi+'\', \''+jenis+'\', \''+cekItemIsNull(item.namaterang)+'\', \''+cekItemIsNull(item.sip)+'\')" class="fa fa-pencil" style="font-size: 20px; cursor: pointer; color: #00c0ef"></i><br>';
                                if(item.jawaban1 != null){
                                    tempTtd = '<img src="' + item.jawaban1 + '" style="width: 100px; height: 70px">';
                                }
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td colspan="3">' + tempTtd +
                                    '<p style="margin-top: -3px">' + cekItemIsNull(item.namaterang) + '</p>' +
                                    '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                    '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + jwb1 + '</td>' +
                                    '<td>' + jwb2 + '</td>' +
                                    '<td>' + tempDiterima + '</td>' +
                                    '</tr>';
                            }
                        } else if ("penandaan_area" == item.keterangan) {
                            if ("penanda" == item.tipe) {
                                body += '<tr>' +
                                    '<td align="center">' +
                                    '<img src="' + item.jawaban1 + '">' +
                                    '</td>' +
                                    '</tr>';
                            }
                            if ("Tanda Tangan Pasien" == item.parameter) {
                                body += '<tr>' +
                                    '<td align="left">' +
                                    '<p>TTD Pasien</p>' +
                                    '<img src="' + item.jawaban1 + '" style="width: 100px; height: 70px">' +
                                    '<p style="margin-top: -3px; padding-bottom: 10px">' + cekItemIsNull(item.namaterang) + '</p>' +
                                    '</td>' +
                                    '</tr>';
                            }
                            if ("Tanda Tangan Dokter" == item.parameter) {
                                body += '<tr>' +
                                    '<td align="right">' +
                                    '<p style="margin-top: -147px">TTD Dokter</p>' +
                                    '<img src="' + item.jawaban1 + '" style="width: 100px; height: 70px;">' +
                                    '<p style="margin-top: -3px">' + cekItemIsNull(item.namaterang) + '</p>' +
                                    '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                    '</td>' +
                                    '</tr>';
                            }
                        } else if ("informasi_dan_persetujuan" == jenis) {
                            if ("colspan" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="3">' + item.jawaban1 + '</td>' +
                                    '</tr>';
                            } else if ("info" == item.tipe) {
                                body += '<tr>' +
                                    '<td width="25%">' + item.parameter + '</td>' +
                                    '<td >' + item.jawaban1 + '</td>' +
                                    '<td width="20%" align="center">' + checkIcon(item.jawaban2) + '</td>' +
                                    '</tr>';
                            } else if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="2">' + item.parameter + '</td>' +
                                    '<td>' +
                                    '<img src="' + item.jawaban1 + '" style="height: 80px">' +
                                    '<p style="margin-top: -3px">' + cekItemIsNull(item.namaterang) + '</p>' +
                                    '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                    '</td>' +
                                    '</tr>';
                            } else if ("bold" == item.tipe) {
                                body += '<tr style="font-weight: bold">' +
                                    '<td width="25%">' + item.parameter + '</td>' +
                                    '<td >' + item.jawaban1 + '</td>' +
                                    '<td width="20%" align="center">' + item.jawaban2 + '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="30%">' + item.parameter + '</td>' +
                                    '<td colspan="2">' + item.jawaban1 + '</td>' +
                                    '</tr>';
                            }
                        } else if ("perencanaan_anestesi" == jenis) {
                            var temp = item.jawaban1.split("|");
                            var li = "";
                            $.each(temp, function (i, item) {
                                li += '<li>' + item + '</li>';
                            });
                            if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td>' +
                                    '<img src="' + item.jawaban1 + '" style="height: 80px">' +
                                    '<p style="margin-top: -3px">' + cekItemIsNull(item.namaterang) + '</p>' +
                                    '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                    '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td colspan="2">' + '<ul style="margin-left: 15px">' + li + '</ul>' + '</td>' +
                                    '</tr>';
                            }
                        } else if ("rr_dewasa" == item.keterangan || "rr_anak_anak" == item.keterangan || "rr_sab" == item.keterangan) {
                            var jwb1 = "";
                            var skor = "";
                            if (item.jawaban1 != null) {
                                jwb1 = item.jawaban1;
                            }
                            if (item.skor != null) {
                                skor = item.skor;
                                totalSkor = parseInt(totalSkor) + parseInt(item.skor);
                            }
                            body += '<tr>' +
                                '<td width="30%">' + item.parameter + '</td>' +
                                '<td>' + jwb1 + '</td>' +
                                '<td align="center" width="10%">' + skor + '</td>' +
                                '</tr>';
                            cekData = true;
                        } else if ("add_checklist_keselamatan" == jenis) {
                            var p = "";
                            var j1 = "";
                            var j2 = "";
                            if (item.parameter != null && item.parameter != '') {
                                p = item.parameter;
                            }
                            if (item.jawaban1 != null && item.jawaban1 != '') {
                                j1 = item.jawaban1;
                            }
                            if (item.jawaban2 != null && item.jawaban2 != '') {
                                j2 = item.jawaban2;
                            }
                            if ("colspan" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + p + '</td>' +
                                    '<td colspan="2">' + j1 + '</td>' +
                                    '</tr>';
                            } else if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + p + '</td>' +
                                    '<td colspan="2">' +
                                    '<img src="' + j1 + '" style="height: 80px">' +
                                    '<p style="margin-top: -3px">' + cekItemIsNull(item.namaterang) + '</p>' +
                                    '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                    '</td>' +
                                    '</tr>';
                            } else if ("bold" == item.tipe) {
                                body += '<tr style="font-weight: bold">' +
                                    '<td>' + p + '</td>' +
                                    '<td>' + j1 + '</td>' +
                                    '<td>' + j2 + '</td>' +
                                    '</tr>';
                            } else {
                                body += '<tr>' +
                                    '<td width="35%">' + p + '</td>' +
                                    '<td width="30%">' + j1 + '</td>' +
                                    '<td width="35%">' + j2 + '</td>' +
                                    '</tr>';
                            }
                        }else if("tindakan_medis_op" == item.keterangan){
                            var del = '';
                            if("Tanggal" == item.parameter){
                                del = '<span style="margin-right: 60px" onclick="conOP(\'' + jenis + '\',\'tindakan_medis\', \'\', \'' + converterDateTimeComplex(item.createdDate) + '\')" class="pull-right"><i id="delete_' + item.idAsesmenKeperawatanRawatInap + '" class="fa fa-trash hvr-grow" style="color: red; font-size: 20px"></i></span>' +
                                    '<a style="margin-right: 7px" target="_blank" href="'+contextPath+'/rekammedik/printSuratPernyataan_rekammedik?id='+idDetailCheckup+'&tipe=OK&keterangan='+item.keterangan+'&createdDate='+converterDateTimeComplex(item.createdDate)+'" class="pull-right"><i class="fa fa-print hvr-grow" style="color: deepskyblue; font-size: 20px"></i></a>';
                            }
                            if ("colspan" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="3">' + cekItemIsNull(item.jawaban1) + '</td>' +
                                    '</tr>';
                            } else if ("info" == item.tipe) {
                                body += '<tr>' +
                                    '<td width="25%">' + item.parameter + '</td>' +
                                    '<td >' + cekItemIsNull(item.jawaban1) + '</td>' +
                                    '<td width="20%" align="center">' + cekIconsIsNotNull(item.jawaban2) + '</td>' +
                                    '</tr>';
                            } else if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td colspan="2">' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + item.jawaban1 + '" style="height: 80px">' +
                                    '<p style="margin-top: -3px">'+cekItemIsNull(item.namaterang)+'</p>' +
                                    '<p style="margin-top: -10px">'+cekItemIsNull(item.sip)+'</p>' +
                                    '</td>';
                            } else if ("bold" == item.tipe) {
                                body += '<tr style="font-weight: bold">' +
                                    '<td width="25%">' + item.parameter + '</td>' +
                                    '<td >' + item.jawaban1 + '</td>' +
                                    '<td width="20%" align="center">' + item.jawaban2 + '</td>' +
                                    '</tr>';
                            }else {
                                body += '<tr>' +
                                    '<td width="30%">' + item.parameter + '</td>' +
                                    '<td colspan="2">' + item.jawaban1 + del + '</td>' +
                                    '</tr>';
                            }
                        } else {
                            var jwb1 = "";
                            if (item.jawaban1 != null) {
                                jwb1 = item.jawaban1;
                            }
                            if ("ttd" == item.tipe) {
                                body += '<tr>' +
                                    '<td>' + item.parameter + '</td>' +
                                    '<td colspan="2">' +
                                    '<img src="' + jwb1 + '" style="width: 100px; height: 70px">' +
                                    '<p style="margin-top: -3px">' + cekItemIsNull(item.namaterang) + '</p>' +
                                    '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p>' +
                                    '</td>' +
                                    '</tr>';
                            } else if ("li" == item.tipe) {
                                var val = jwb1.split("|");
                                var li = "";
                                $.each(val, function (i, item) {
                                    li += '<li>' + item + '</li>'
                                });
                                if (li != '') {
                                    body += '<tr>' +
                                        '<td>' + item.parameter + '</td>' +
                                        '<td><ul style="margin-left: 15px">' + li + '</ul></td>' +
                                        '</tr>';
                                }
                            } else {
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + jwb1 + '</td>' +
                                    '</tr>';
                            }
                            tgl = item.createdDate;
                        }

                    });

                    if ("kondisi_pasien" == jenis) {
                        head = '<tr>' +
                            '<td><b>Pemeriksaan</b></td>' +
                            '<td><b>Sebelum Ditransfer</b></td>' +
                            '<td><b>Saat Perjalanan</b></td>' +
                            '<td><b>Saat Diterima</b></td>' +
                            '</tr>';
                    }
                    if ("general_informasi" == jenis || "regional_informasi" == jenis) {
                        head = '<tr>' +
                            '<td><b>Jenis Informasi</b></td>' +
                            '<td><b>Isi Informasi</b></td>' +
                            '<td><b>Check (<i class="fa fa-check"></i>)</b></td>' +
                            '</tr>';
                    }
                    if ("general_persetujuan" == jenis || "regional_persetujuan" == jenis) {
                        firt = '<tr>' +
                            '<td colspan="2">Yang bertanda tangan dibawah ini, saya :</td>' +
                            '</tr>';
                        last = '<tr>' +
                            '<td colspan="2">Saya memahami perlunya dan manfaat tindakan tersebut sebagaimana telah dijelaskan seperti di atas kepada saya termasuk resiko dan komplikasi yang timbul. Saya juga menyadari bahwa oleh karena itu ilmu kedokteran bukan ilmu pasti. maka keberhasilan tindakan kedokteran bukan keniscayaan, tetapi bergantung kepada izin Tuhan Yang Maha Esa. Tanggal ' + formaterDate(tgl) + ', Jam ' + formaterTime(tgl) + '</td>' +
                            '</tr>';
                    }
                    if ("rr_dewasa" == jenis || "rr_anak_anak" == jenis || "rr_sab" == jenis) {
                        var con = "";
                        head = '<tr>' +
                            '<td><b>Variabel</b></td>' +
                            '<td><b>Penilaian</b></td>' +
                            '<td align="center"><b>Skor</b></td>' +
                            '</tr>';

                        if ("rr_dewasa" == jenis) {
                            if (totalSkor >= 8 && totalSkor <= 10) {
                                con = "Ya";
                            } else {
                                con = "Tidak"
                            }
                        }
                        if ("rr_anak_anak" == jenis) {
                            if (totalSkor >= 5 && totalSkor <= 6) {
                                con = "Ya";
                            } else {
                                con = "Tidak"
                            }
                        }
                        if ("rr_sab" == jenis) {
                            if (totalSkor <= 2) {
                                con = "Ya";
                            } else {
                                con = "Tidak"
                            }
                        }

                        if (cekData) {
                            last = '<tr style="font-weight: bold"><td colspan="2">Total</td><td align="center">' + totalSkor + '</td></tr>' +
                                '<tr style="font-weight: bold" bgcolor="#ffebcd"><td colspan="2">Kriteria Pindah Ruang</td><td align="center">' + con + '</td></tr>';
                        }
                    }

                } else {
                    body = '<tr>' +
                        '<td>Data belum ada</td>' +
                        '</tr>';
                }

                var table = '<table style="font-size: 12px" class="table table-bordered">' +
                    '<thead>' + head + '</thead>' +
                    '<tr bgcolor="#ffebcd">' +
                    '<tbody>' + firt + body + last + '</tbody>' +
                    '</table>';

                var newRow = $('<tr id="del_op_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                newRow.insertAfter($('table').find('#row_op_' + jenis));
                var url = contextPath + '/pages/images/minus-allnew.png';
                $('#btn_op_' + jenis).attr('src', url);
                $('#btn_op_' + jenis).attr('onclick', 'delRowOperasi(\'' + jenis + '\')');
                $('[data-mask]').inputmask();
            });
        }
    }
}

function delRowOperasi(id) {
    $('#del_op_' + id).remove();
    var url = "";
    if(id == "laporan_operasi"){
        url = contextPath + '/pages/images/icons8-add-list-25.png';
    }else{
        url = contextPath + '/pages/images/icons8-plus-25.png';
    }
    $('#btn_op_' + id).attr('src', url);
    $('#btn_op_' + id).attr('onclick', 'detailOperasi(\'' + id + '\')');
}

function checkIcon(val) {
    var fa = "";
    if (val == "Ya") {
        fa = '<i class="fa fa-check"></i>'
    }
    return fa;
}

function penandaAreaOperasi() {
    var url = "";
    if ("Laki-Laki" == jenisKelamin) {
        url = contextPath + '/pages/images/penanda-laki-laki.jpg';
    } else {
        url = contextPath + '/pages/images/penanda-perempuan.jpg';
    }
    var canvas = document.getElementById('area_canvas');
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
    $('#modal-op-ttd-penandaan-mlt').modal({show: true, backdrop: 'static'});
}

function clearConvas(jenis) {
    if ("area_canvas" == jenis) {
        var canvas = document.getElementById('area_canvas');
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        var url = "";
        if ("Laki-Laki" == jenisKelamin) {
            url = contextPath + '/pages/images/penanda-laki-laki.jpg';
        } else {
            url = contextPath + '/pages/images/penanda-perempuan.jpg';
        }
        var canvas = document.getElementById('area_canvas');
        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    } else {
        var canvas = document.getElementById(jenis);
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
    }

}

function confirmSavePenanda() {
    var canvasArea = document.getElementById('area_canvas');
    var canvasCek = document.getElementById('area_cek');
    if (canvasArea.toDataURL() == canvasCek.toDataURL()) {
        $('#warning_op_penanda_area').show().fadeOut(5000);
        $('#msg_op_penanda_area').text("Silahkan lakukan penandaan area operasi...!");
        $('#modal-penanda-area').scrollTop(0);
    } else {
        $('#modal-op-ttd-penandaan').modal({show: true, backdrop: 'static'});
    }
}

function isBlank(canvas) {
    const blank = document.createElement("canvas");
    blank.width = canvas.width;
    blank.height = canvas.height;
    return canvas.toDataURL() === blank.toDataURL();
}

function formaterDateTime(dateTime) {

    var today = "";
    if (dateTime != '' && dateTime != null) {

        today = new Date(dateTime);
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
        var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
        var sec = today.getSeconds();
        today = dd + '-' + mm + '-' + yyyy + ' ' + hh + ':' + min;
    }
    return today;
}

function formaterDate(dateTime) {

    var today = "";
    if (dateTime != '' && dateTime != null) {

        today = new Date(dateTime);
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
        var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
        var sec = today.getSeconds();
        today = dd + '-' + mm + '-' + yyyy;
    }
    return today;
}

function formaterTime(dateTime) {

    var today = "";
    if (dateTime != '' && dateTime != null) {

        today = new Date(dateTime);
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
        var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
        var sec = today.getSeconds();
        today = hh + ':' + min;
    }
    return today;
}

function showKete(val, id) {
    if (val == "Ya") {
        $('#form-ket-' + id).show();
    } else {
        $('#form-ket-' + id).hide();
    }
}

function pilihPersetujuanOK(val) {
    if (val != '') {
        $('#form-persetujuan-op').show();
        // $('#op8').val(val);
        var body = "";
        $.each(persetujuanOK(val), function (i, item) {

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

        $('#body_persetujuan-op').html(body);

    } else {
        $('#form-persetujuan-op').hide();
        // $('#op8').val('');
        $('#body_persetujuan-op').html('');
    }
}

function persetujuanOK(jenis) {

    var data = [];
    var dataCari = [];

    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Teknik Anestesi',
        'informasi': 'General Anestesi|Sedasi Moderat/Dalam',
        'keterangan': 'c'
    });
    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Kondisi yang diharapkan',
        'informasi': 'Rasa Cemas berkurang, mengantuk s/d kesadaran hilang, tidak merasa nyeri',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Tata cara',
        'informasi': 'Obat yang diinjeksikan ke pembuluh darah, obat dihirupkan keseluruh nafas',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Resiko',
        'informasi': 'Aspirasi, udema pasru, cedera mulut (gigi, lidah, bibir), suara serak dan nteri tenggorokan',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Tujuan',
        'informasi': 'Mengurangi rasa cemas, menghilangkan nyeri menjaga fungsi organ selama pembedahan',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Komplikasi',
        'informasi': 'Reaksi alergi obat, stroke, serangan jantung kematian',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Tranfusi',
        'informasi': 'Tidak|Ya',
        'keterangan': 'r'
    });
    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Komplikasi',
        'informasi': 'Reaksi tranfusi, penularan penyakit lewat darah',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Rencana Pemberian obat nyeri',
        'informasi': 'Injeksi|Patch|Peridural|Injeksi kontinyu|Lainnya',
        'keterangan': 'c'
    });
    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Prognosa',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'general_anestesi',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });

    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Teknik Anestesi',
        'informasi': 'Sub Arachnoid Blok (SAB)|Epidural|CSE/Block Syarat Mayor',
        'keterangan': 'c'
    });
    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Kondisi yang diharapkan',
        'informasi': 'Akan terjadi mati rasa pada bagian tubuh tertentu',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Tata cara',
        'informasi': 'Obat diinjeksikan pada kanal tulang belakang posisi pasien tidur miring atau duduk',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Resiko',
        'informasi': 'Sakit kepala, sakit panggung, infeksi ditempat injeksi total spinal',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Tujuan',
        'informasi': 'Mengurangi rasa cemas, menghilangkan nyeri menjaga fungsi organ selama pembedahan',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Komplikasi',
        'informasi': 'Reaksi alergi obat, stroke, serangan jantung kematian',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Tranfusi',
        'informasi': 'Tidak|Ya',
        'keterangan': 'r'
    });
    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Komplikasi',
        'informasi': 'Reaksi tranfusi, penularan penyakit lewat darah',
        'keterangan': 'l'
    });
    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Rencana Pemberian obat nyeri',
        'informasi': 'Injeksi|Patch|Peridural|Injeksi kontinyu|Lainnya',
        'keterangan': 'c'
    });
    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Prognosa',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'jenis': 'regional_anestesi',
        'parameter': 'Alternatif',
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

function saveMonAnestesi(jenis, ket) {

    var data = "";
    var jam = $('#jam').val();
    var va1 = $('#mon1').val();
    var va2 = $('#mon2').val();
    var va3 = $('#mon3').val();
    var va4 = $('#mon4').val();
    var va5 = $('#mon5').val();
    var va6 = $('#mon6').val();
    var va7 = $('#mon7').val();
    var cek = false;

    if ("mon_intra_anestesi" == jenis) {
        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 != '') {
            cek = true;
        }
    }

    if ("mon_pasca_anestesi" == jenis) {
        if (va1 && va2 && va3 && va4 != '') {
            va5 = va6 = va7 = "";
            cek = true;
        }
    }

    if (cek) {
        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': jam,
            'rr': va1,
            'nadi': va2,
            'sistole': va3,
            'diastole': va4,
            'o2': va5,
            'n2o': va6,
            'inhalasi': va7,
            'keterangan': jenis,
            'jenis': ket
        };
        if(!cekSession()){
            var result = JSON.stringify(data);
            $('#save_op_' + jenis).hide();
            $('#load_op_' + jenis).show();
            dwr.engine.setAsync(true);
            AsesmenOperasiAction.saveMonAnestesi(result, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_op_' + jenis).show();
                        $('#load_op_' + jenis).hide();
                        $('#modal-op-' + jenis).modal('hide');
                        $('#warning_op_' + ket).show().fadeOut(5000);
                        $('#msg_op_' + ket).text("Berhasil menambahkan data operasi...");
                        $('#modal-op-' + jenis).scrollTop(0);
                    } else {
                        $('#save_op_' + jenis).show();
                        $('#load_op_' + jenis).hide();
                        $('#warning_op_' + jenis).show().fadeOut(5000);
                        $('#msg_op_' + jenis).text(res.msg);
                        $('#modal-op-' + jenis).scrollTop(0);
                    }
                }
            });
        }
    } else {
        $('#warning_op_' + jenis).show().fadeOut(5000);
        $('#msg_op_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-op-' + jenis).scrollTop(0);
    }
}

function detailMonAnestesi(jenis) {
    if(!cekSession()){
        var body = "";
        var head = "";
        AsesmenOperasiAction.getListMonAnestesi(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var tanggal = converterDate(new Date(item.createdDate));

                    var tempTgl = "";
                    var btn = "";

                    if (i == 0) {
                        tempTgl = tanggal;
                        btn = '<i class="fa fa-line-chart" style="cursor: pointer; color: #1ab7ea" onclick="showChartAnestesi(\'' + jenis + '\')"></i>';
                    } else {
                        var tgl = res[i - 1]["createdDate"];
                        var tglB = converterDate(new Date(tgl));
                        if (tanggal == tglB) {
                            tempTgl = "";
                        } else {
                            tempTgl = tanggal;
                            btn = '<i class="fa fa-line-chart" style="cursor: pointer; color: #1ab7ea" onclick="showChartAnestesi(\'' + jenis + '\')"></i>';
                        }
                    }

                    if ("mon_intra_anestesi" == jenis) {
                        body += '<tr>' +
                            '<td width="18%">' + btn + ' ' + tempTgl + ' ' + '<span class="pull-right">' + item.waktu + '</span>' + '</td>' +
                            '<td>' + item.rr + '</td>' +
                            '<td>' + item.nadi + '</td>' +
                            '<td>' + item.sistole + '</td>' +
                            '<td>' + item.diastole + '</td>' +
                            '<td>' + item.o2 + '</td>' +
                            '<td>' + item.n2O + '</td>' +
                            '<td>' + item.inhalasi + '</td>' +
                            '<td align="center">' + '<i onclick="conOP(\'' + jenis + '\', \'monitoring_intra_anestesi\', \'' + item.idMonitoringAnestesi + '\')" class="fa fa-trash hvr-grow" style="color: red"></i>' + '</td>' +
                            '</tr>';
                    }
                    if ("mon_pasca_anestesi" == jenis) {
                        body += '<tr>' +
                            '<td width="18%">' + btn + ' ' + tempTgl + ' ' + '<span class="pull-right">' + item.waktu + '</span>' + '</td>' +
                            '<td>' + item.rr + '</td>' +
                            '<td>' + item.nadi + '</td>' +
                            '<td>' + item.sistole + '</td>' +
                            '<td>' + item.diastole + '</td>' +
                            '<td align="center">' + '<i onclick="conOP(\'' + jenis + '\', \'monitoring_pasca_anestesi\', \'' + item.idMonitoringAnestesi + '\')" class="fa fa-trash hvr-grow" style="color: red"></i>' + '</td>' +
                            '</tr>';
                    }
                });

                if ("mon_intra_anestesi" == jenis) {
                    head = '<tr style="font-weight: bold">' +
                        '<td align="center">Waktu</td>' +
                        '<td>RR</td>' +
                        '<td>Nadi</td>' +
                        '<td>Sistole</td>' +
                        '<td>Diastole</td>' +
                        '<td>O2</td>' +
                        '<td>N2O</td>' +
                        '<td>Inhalasi</td>' +
                        '<td align="center">Action</td>' +
                        '</tr>';
                }
                if ("mon_pasca_anestesi" == jenis) {
                    head = '<tr style="font-weight: bold">' +
                        '<td align="center">Waktu</td>' +
                        '<td>RR</td>' +
                        '<td>Nadi</td>' +
                        '<td>Sistole</td>' +
                        '<td>Diastole</td>' +
                        '<td align="center">Action</td>' +
                        '</tr>';
                }
            } else {
                body = '<tr><td>Data belum ada</td></tr>';
            }
            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tr bgcolor="#ffebcd">' +
                '<tbody>' + body + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_op_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_op_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_op_' + jenis).attr('src', url);
            $('#btn_op_' + jenis).attr('onclick', 'delRowMon(\'' + jenis + '\')');
        });
    }
}

function delRowMon(id) {
    $('#del_op_' + id).remove();
    var url = contextPath + '/pages/images/icons8-add-list-25.png';
    $('#btn_op_' + id).attr('src', url);
    $('#btn_op_' + id).attr('onclick', 'detailMonAnestesi(\'' + id + '\')');
}

function showChartAnestesi(jenis) {
    if(!cekSession()){
        $('#modal-op-chart_' + jenis).modal({show: true, backdrop: 'static'});
        var tempData = [];
        AsesmenOperasiAction.getListMonAnestesi(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                var dataArray = [];
                $.each(res, function (i, item) {

                    if ("mon_intra_anestesi" == jenis) {
                        dataArray.push({
                            y: item.waktu,
                            a: item.rr,
                            b: item.nadi,
                            c: item.sistole,
                            d: item.diastole
                        });

                        tempData.push({
                            'waktu': item.waktu,
                            'o2': item.o2,
                            'n2o': item.n2O,
                            'inhalasi': item.inhalasi
                        });
                    }
                    if ("mon_pasca_anestesi" == jenis) {
                        dataArray.push({
                            y: item.waktu,
                            a: item.rr,
                            b: item.nadi,
                            c: item.sistole,
                            d: item.diastole
                        });
                    }
                });

                $('#modal-op-chart_' + jenis).on('shown.bs.modal', function (event) {
                    $('#line-chart_' + jenis).empty();
                    if ("mon_intra_anestesi" == jenis) {
                        var line = new Morris.Line({
                            element: 'line-chart_' + jenis,
                            resize: true,
                            data: dataArray,
                            xkey: 'y',
                            ykeys: ['a', 'b', 'c', 'd'],
                            labels: ['RR', 'Nadi', 'Sistole', 'Diastole'],
                            lineColors: ['#ff0000', '#0000ff', '#00cc00', '#cc6699'],
                            hideHover: 'auto',
                            parseTime: false,
                            lineWidth: 1
                        });

                        var temp = "";
                        var waktu = "<td style='font-weight: bold'>Waktu</td>";
                        var temp1 = "<td style='font-weight: bold'>O2</td>";
                        var temp2 = "<td style='font-weight: bold'>N2O</td>";
                        var temp3 = "<td style='font-weight: bold'>Inhalasi</td>";

                        $.each(tempData, function (i, item) {
                            waktu += '<td>' + item.waktu + '</td>';
                            temp1 += '<td>' + item.o2 + '</td>'
                            temp2 += '<td>' + item.n2o + '</td>';
                            temp3 += '<td>' + item.inhalasi + '</td>';
                        });
                        if (waktu != '') {
                            temp = '<tr>'+waktu+'</tr>' +
                                '<tr>'+temp1+'</tr>' +
                                '<tr>'+temp2+'</tr>' +
                                '<tr>'+temp3+'</tr>';
                        }
                        $('#temp_body').html(temp);
                    }
                    if ("mon_pasca_anestesi" == jenis) {
                        var line = new Morris.Line({
                            element: 'line-chart_' + jenis,
                            resize: true,
                            data: dataArray,
                            xkey: 'y',
                            ykeys: ['a', 'b', 'c', 'd'],
                            labels: ['RR', 'Nadi', 'Sistole', 'Diastole'],
                            lineColors: ['#ff0000', '#0000ff', '#00cc00', '#cc6699'],
                            hideHover: 'auto',
                            parseTime: false,
                            lineWidth: 1
                        });
                    }
                });
            }
        });
    }
}

function conOP(jenis, ket, idAsesmen, date) {
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show: true, backdrop: 'static'});
    if (idAsesmen != undefined && idAsesmen != '') {
        $('#save_con_rm').attr('onclick', 'delOPM(\'' + jenis + '\', \'' + ket + '\', \'' + idAsesmen + '\')');
    } else {
        $('#save_con_rm').attr('onclick', 'delOP(\'' + jenis + '\', \'' + ket + '\', \''+date+'\')');
    }
}

function delOPM(jenis, ket, idAsesmen) {
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
        AsesmenOperasiAction.deleteMonAnestesi(idAsesmen, {
            callback: function (res) {
                if (res.status == "success") {
                    stopIconSpin('delete_' + idAsesmen);
                    $('#modal-op-'+ket).scrollTop(0);
                    $('#warning_op_'+ket).show().fadeOut(5000);
                    $('#msg_op_'+ket).text("Berhasil menghapus data...");
                } else {
                    stopIconSpin('delete_' + idAsesmen);
                    $('#modal-op-'+ket).scrollTop(0);
                    $('#warn_'+ket).show().fadeOut(5000);
                    $('#msg_'+ket).text(res.msg);
                }
            }
        });
    }
}

function delOP(jenis, ket, date) {
    $('#modal-confirm-rm').modal('hide');
    if(!cekSession()){
        var dataPasien = {
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'id_pasien': idPasien,
            'id_rm': tempidRm
        }
        var createdDate = null;
        if(date != '' && date != null && date != undefined){
            createdDate = date;
        }
        delRowOperasi(jenis);
        var result = JSON.stringify(dataPasien);
        startSpin('delete_' + jenis);
        dwr.engine.setAsync(true);
        AsesmenOperasiAction.saveDelete(idDetailCheckup, jenis, result, createdDate, {
            callback: function (res) {
                if (res.status == "success") {
                    stopSpin('delete_' + jenis);
                    $('#modal-op-' + ket).scrollTop(0);
                    $('#warning_op_' + ket).show().fadeOut(5000);
                    $('#msg_op_' + ket).text("Berhasil menghapus data...");
                    detailOperasi(jenis);
                } else {
                    stopSpin('delete_' + jenis);
                    $('#modal-op-' + ket).scrollTop(0);
                    $('#warn_' + ket).show().fadeOut(5000);
                    $('#msg_' + ket).text(res.msg);
                }
            }
        });
    }
}

function setSideValue(id, value) {
    if (value == '' || value == '___/___') {
        $('#' + id).val('');
    } else {
        $('#' + id).val(value);
    }
}

function saveSerahTerima(id, i, parameter, jenis) {
    var nilai = "";
    if(jenis == "pre_operasi"){
        if($('#'+jenis+i).is(':checked')){
            nilai = "Ya";
        }else{
            nilai = "Tidak";
        }
    }else{
        nilai = $('#'+jenis+i).val();
    }
    if(id != '' && nilai != ''){
        var jawaban = "";
        if("Tekanan Darah" == parameter){
            jawaban = replaceUnderLine(nilai)+" mmHg";
        }else if("Suhu" == parameter){
            jawaban = nilai+" ˚C";
        }else if("Nadi" == parameter || "DJJ" == parameter){
            jawaban = nilai+" x/mnt";
        }else if("RR" == parameter || "Respirator" == parameter){
            jawaban = nilai+" x/mnt";
        }else if("Skala Nyeri" == parameter){
            jawaban = nilai+" x/menit";
        }else{
            jawaban = nilai;
        }
        dwr.engine.setAsync(true);
        AsesmenOperasiAction.saveEditAsesmenOP(id, jawaban, jenis, {
            callback: function (res) {
                if (res.status == "success") {
                    if(jenis == "pre_operasi"){
                        $('#set_'+jenis+i).html(cekIconsIsNotNull(jawaban));
                    }else{
                        $('#set_'+jenis+i).html('<span>'+jawaban+'</span>');
                    }
                    $('#success_'+jenis).show().fadeOut(5000);
                    $('#msg_'+jenis).text("Data berhasil disimpan...!");
                } else {
                    $('#warn_ceklist_operasi').show().fadeOut(5000);
                    $('#msg_ceklist_operasi').text(res.msg);
                }
            }
        });
    }
}

function setTtdPerawat(id, jenis, nama, sip){
    $('#nama_ttd_perawat_edit').val(nama);
    $('#sip_ttd_perawat_edit').val(sip);
    $('#modal-op-ttd_perawat').modal({show: true, backdrop: 'static'});
    $('#save_ttd_perawat').attr('onclick','saveTtdPerawat(\''+id+'\', \''+jenis+'\')');
}

function saveTtdPerawat(id, jenis){
    var nama = $('#nama_ttd_perawat_edit').val();
    var sip = $('#sip_ttd_perawat_edit').val();
    var ttd = document.getElementById("ttd_edit");
    var cek = isCanvasBlank(ttd);
    if(nama && sip != '' && !cek){
        if(!cekSession()){
            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            $('#save_ttd_perawat').hide();
            $('#load_ttd_perawat').show();
            dwr.engine.setAsync(true);
            AsesmenOperasiAction.updateTtdPerawat(id, ttd1, sip, nama, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#modal-op-ttd_perawat').modal('hide');
                        $('#save_ttd_perawat').show();
                        $('#load_ttd_perawat').hide();
                        $('#warning_op_ceklist_operasi').show().fadeOut(5000);
                        $('#msg_op_ceklist_operasi').text("Berhasil mengupdate data...");
                        delRowOperasi(jenis);
                        detailOperasi(jenis);
                    } else {
                        $('#warning_op_ttd_perawat').show().fadeOut(5000);
                        $('#msg_op_ttd_perawat').text(res.msg);
                        $('#save_ttd_perawat').show();
                        $('#load_ttd_perawat').hide();
                    }
                }
            });
        }
    }else {
        $('#warning_op_ttd_perawat').show().fadeOut(5000);
        $('#msg_op_ttd_perawat').text("Silahkan cek kembali inputan anda...!");
    }
}
