$('[name=cek_list14]').click(function () {
    var cek = $('[name=cek_list14]:checked').val();
    if (cek == "Ya") {
        $('#form-ket-checklist').show();
    } else {
        $('#form-ket-checklist').hide();
    }
});

function showModalOperasi(jenis) {
    $('#modal-op-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveDataOperasi(jenis, ket) {
    var data = [];
    var cek = false;
    var mlt = false;

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
        var cekList11 = $('[name=cek_list11]:checked').val();
        var cekList1111 = $('[name=cek_list1111]:checked').val();
        var cekList12 = $('[name=cek_list12]:checked').val();
        var cekList1212 = $('[name=cek_list1212]:checked').val();
        var cekList13 = $('[name=cek_list13]:checked').val();
        var cekList1313 = $('[name=cek_list1313]:checked').val();
        var cekList14 = $('[name=cek_list14]:checked').val();
        var tCekList14 = "";
        if (cekList14 == "Ya") {
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
            tCekList14 = cekList14;
        }

        var cekList15 = $('#cek_list15').val();
        var cekList16 = $('#cek_list16').val();

        if (tCekList14 && cekList15 && cekList16 != '') {
            data.push({
                'parameter': 'Pembatasan Nutrisi Per Oral(Puasa)',
                'jawaban1': cekList1,
                'jawaban2': cekList11,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Laboratorium',
                'jawaban1': cekList2,
                'jawaban2': cekList22,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Radiologi (Thorax Foto, USG, Scan)',
                'jawaban1': cekList3,
                'jawaban2': cekList33,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan ECG',
                'jawaban1': cekList4,
                'jawaban2': cekList44,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Edukasi dan Informed Consent Bedah',
                'jawaban1': cekList5,
                'jawaban2': cekList55,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penandaan Area Operasi',
                'jawaban1': cekList6,
                'jawaban2': cekList66,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi Dokter Spesialis Anestesi',
                'jawaban1': cekList7,
                'jawaban2': cekList77,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Edukasi dan Informed Consent Anestesi',
                'jawaban1': cekList8,
                'jawaban2': cekList88,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi Dokter Kardiologi ACC Operasi dengan Resiko: Ringan/Sedang/berat Cardiac Risk Index Grade I/II/III/IV',
                'jawaban1': cekList9,
                'jawaban2': cekList99,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi Dokter Spesialis Penyakit Dalam',
                'jawaban1': cekList10,
                'jawaban2': cekList1010,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Schiren / Cukur',
                'jawaban1': cekList11,
                'jawaban2': cekList1111,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Melepas Perhiasan, Soft Lens, Gigi Palsu, DLL',
                'jawaban1': cekList12,
                'jawaban2': cekList1212,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Persiapan Prosuk Darah',
                'jawaban1': cekList13,
                'jawaban2': cekList1313,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit',
                'jawaban1': tCekList14,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan',
                'jawaban1': cekList15 + ' Kg',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tinggi Badan',
                'jawaban1': cekList16 + ' cm',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("kondisi_pasien" == jenis) {
        console.log('tes');
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

        if (cekList171 && cekList172 && cekList181 && cekList182 && cekList191 && cekList192 && cekList201 && cekList202 &&
            cekList211 && cekList212 && cekList221 && cekList222 && cekList231 && cekList232 && cekList241 && cekList242 != '') {
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
                'jawaban1': cekList191 + ' mmHg',
                'jawaban2': cekList191 + ' mmHg',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban1': cekList201 + ' x/menit',
                'jawaban2': cekList202 + ' x/menit',
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
            cek = true;
            console.log(data);
        }
    }

    if ("ttd-penandaan" == jenis) {
        var canvasTtdPasien = document.getElementById("op_ttd_pasien");
        var canvasTtdDokter = document.getElementById("op_ttd_dokter");
        var canvasArea = document.getElementById('area_canvas');
        var tes1 = isBlank(canvasTtdPasien);
        var tes2 = isBlank(canvasTtdDokter);
        if (!tes1 && !tes2) {

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
                'jenis': 'area_penanda',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Tanda Tangan Pasien',
                'jawaban1': ttdPasien,
                'keterangan': 'penandaan_area',
                'jenis': 'ttd_pasien',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Tanda Tangan Dokter',
                'jawaban1': ttdDokter,
                'keterangan': 'penandaan_area',
                'jenis': 'ttd_dokter',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
            mlt = true;
        }
    }

    if ("anamnesa" == jenis) {
        var anme1 = $('[name=cek_anamnesa1]:checked').val();
        var anme2 = $('[name=cek_anamnesa2]:checked').val();
        var anme3 = $('[name=cek_anamnesa3]:checked').val();
        var anme4 = $('[name=cek_anamnesa4]:checked').val();
        var anme5 = $('#cek_anamnesa5').val();
        var tAnme1 = "";
        if (anme1 == "Ya") {
            var t = $('#cek_ket_anamnesa2').val();
            if (t != '') {
                tAnme1 = "Ya, " + t;
            }
        } else {
            tAnme1 = t;
        }
        if (anme1 && anme2 && anme3 && anme4 != undefined && anme5 != '') {
            data.push({
                'parameter': 'Alergi',
                'jawaban1': tAnme1,
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
                'jawaban1': pf9 + ' mmHg',
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

        if (pa1 && pa2 && pa3 != '') {
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
            cek = true;
        }
    }

    if ("general_informasi" == jenis) {
        var pa1 = $('[name=ga1]');
        var tsPa1 = "";
        $.each(pa1, function (i, item) {
            if (pa1[i].checked) {
                if (tsPa1 != '') {
                    tsPa1 = tsPa1 + ', ' + pa1[i].value;
                } else {
                    tsPa1 = pa1[i].value;
                }
            }
        });

        var pa2 = $('[name=ga2]:checked').val();
        var pa3 = $('[name=ga3]:checked').val();
        var pa4 = $('[name=ga4]:checked').val();
        var pa5 = $('[name=ga5]:checked').val();
        var pa6 = $('[name=ga6]:checked').val();
        var pa7 = $('[name=ga7]:checked').val();
        var pa8 = $('[name=ga8]:checked').val();
        var pa9 = $('[name=ga9]:checked').val();
        var pa10 = $('[name=ga10]:checked').val();
        var pa11 = $('[name=ga11]:checked').val();
        var pa12 = $('[name=ga12]:checked').val();
        var pa1010 = $('#ga10').val();
        var pa122 = $('#ga122').val();

        if (pa2 && pa3 && pa4 && pa5 && pa6 && pa7 && pa8 && pa9 && pa10 && pa12 && pa11 != undefined &&
            tsPa1 != '') {
            data.push({
                'parameter': 'Teknik Anestesi',
                'jawaban1': tsPa1,
                'jawaban2': pa2,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kondisi yang diharapkan',
                'jawaban1': 'Rasa Cemas berkurang, mengantuk s/d kesadaran hilang, tidak merasa nyeri',
                'jawaban2': pa3,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tata cara',
                'jawaban1': 'Obat yang diinjeksikan ke pembuluh darah, obat dihirupkan keseluruh nafas',
                'jawaban2': pa4,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Resiko',
                'jawaban1': 'Aspirasi, udema pasru, cedera mulut (gigi, lidah, bibir), suara serak dan nteri tenggorokan',
                'jawaban2': pa5,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tujuan',
                'jawaban1': 'Mengurangi rasa cemas, menghilangkan nyeri menjaga fungsi organ selama pembedahan',
                'jawaban2': pa6,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Komplikasi',
                'jawaban1': 'Reaksi alergi obat, stroke, serangan jantung kematian',
                'jawaban2': pa7,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tranfusi',
                'jawaban1': pa8,
                'jawaban2': pa9,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Komplikasi',
                'jawaban1': 'Reaksi tranfusi, penularan penyakit lewat darah',
                'jawaban2': pa10,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Prognosa',
                'jawaban1': pa1010,
                'jawaban2': pa11,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alternatif',
                'jawaban1': pa122,
                'jawaban2': pa12,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("general_penyataan" == jenis) {
        var gep1 = document.getElementById("general_penyataan1");
        var gep2 = document.getElementById("general_penyataan2");
        var gp1 = isBlank(gep1);
        var gp2 = isBlank(gep2);

        if (!gp1 && !gp2) {

            var ttd1 = gep1.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");

            var ttd2 = gep2.toDataURL("image/png"),
                ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerangkan hal-hal di atas secara benar dan jelas dengan memberikan kesempatan bertanya dan atau berdiskusi',
                'jawaban1': ttd1,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerima informasi sebagaimana di atas dan telah memahaminya',
                'jawaban1': ttd2,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("general_persetujuan" == jenis) {
        var pe1 = $('#perse1').val();
        var pe2 = $('#perse2').val();
        var pe3 = $('#perse3').val();
        var pe4 = $('#perse4').val();
        var pe5 = $('#perse5').val();
        var pe6 = $('#perse6').val();
        var pe7 = $('#perse7').val();
        var pe8 = $('#perse8').val();

        if (pe1 && pe2 && pe3 && pe4 && pe5 && pe6 && pe7 && pe8 != '') {
            data.push({
                'parameter': 'Nama',
                'jawaban1': pe1,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Lahir',
                'jawaban1': pe2.split("-").reverse().join("-"),
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': pe3,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban1': pe4,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Pasien',
                'jawaban1': pe5,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Lahir',
                'jawaban1': pe6.split("-").reverse().join("-"),
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': pe7,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban1': pe8,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("regional_informasi" == jenis) {
        var pa1 = $('[name=reg1]');
        var tsPa1 = "";
        $.each(pa1, function (i, item) {
            if (pa1[i].checked) {
                if (tsPa1 != '') {
                    tsPa1 = tsPa1 + ', ' + pa1[i].value;
                } else {
                    tsPa1 = pa1[i].value;
                }
            }
        });

        var pa2 = $('[name=reg2]:checked').val();
        var pa3 = $('[name=reg3]:checked').val();
        var pa4 = $('[name=reg4]:checked').val();
        var pa5 = $('[name=reg5]:checked').val();
        var pa6 = $('[name=reg6]:checked').val();
        var pa7 = $('[name=reg7]:checked').val();
        var pa8 = $('[name=reg8]:checked').val();
        var pa9 = $('[name=reg9]:checked').val();
        var pa10 = $('[name=reg10]:checked').val();
        var pa11 = $('[name=reg11]:checked').val();
        var pa12 = $('[name=reg12]:checked').val();
        var pa1010 = $('#reg10').val();
        var pa122 = $('#reg122').val();

        if (pa2 && pa3 && pa4 && pa5 && pa6 && pa7 && pa8 && pa9 && pa10 && pa12 && pa11 != undefined &&
            tsPa1 != '') {
            data.push({
                'parameter': 'Teknik Anestesi',
                'jawaban1': tsPa1,
                'jawaban2': pa2,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kondisi yang diharapkan',
                'jawaban1': 'Akan terjadi mati rasa pada bagian tubuh tertentu',
                'jawaban2': pa3,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tata cara',
                'jawaban1': 'Obat diinjeksikan pada kanal tulang belakang posisi pasien tidur miring atau duduk',
                'jawaban2': pa4,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Resiko',
                'jawaban1': 'Sakit kepala, sakit panggung, infeksi ditempat injeksi total spinal',
                'jawaban2': pa5,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tujuan',
                'jawaban1': 'Mengurangi rasa cemas, menghilangkan nyeri menjaga fungsi organ selama pembedahan',
                'jawaban2': pa6,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Komplikasi',
                'jawaban1': 'Reaksi alergi obat, stroke, serangan jantung kematian',
                'jawaban2': pa7,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tranfusi',
                'jawaban1': pa8,
                'jawaban2': pa9,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Komplikasi',
                'jawaban1': 'Reaksi tranfusi, penularan penyakit lewat darah',
                'jawaban2': pa10,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Prognosa',
                'jawaban1': pa1010,
                'jawaban2': pa11,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alternatif',
                'jawaban1': pa122,
                'jawaban2': pa12,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("regional_penyataan" == jenis) {
        var gep1 = document.getElementById("regional_penyataan1");
        var gep2 = document.getElementById("regional_penyataan2");
        var gp1 = isBlank(gep1);
        var gp2 = isBlank(gep2);

        if (!gp1 && !gp2) {

            var ttd1 = gep1.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");

            var ttd2 = gep2.toDataURL("image/png"),
                ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerangkan hal-hal di atas secara benar dan jelas dengan memberikan kesempatan bertanya dan atau berdiskusi',
                'jawaban1': ttd1,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });

            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya telah menerima informasi sebagaimana di atas dan telah memahaminya',
                'jawaban1': ttd2,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("regional_persetujuan" == jenis) {
        var pe1 = $('#reg_perse1').val();
        var pe2 = $('#reg_perse2').val();
        var pe3 = $('#reg_perse3').val();
        var pe4 = $('#reg_perse4').val();
        var pe5 = $('#reg_perse5').val();
        var pe6 = $('#reg_perse6').val();
        var pe7 = $('#reg_perse7').val();
        var pe8 = $('#reg_perse8').val();

        if (pe1 && pe2 && pe3 && pe4 && pe5 && pe6 && pe7 && pe8 != '') {
            data.push({
                'parameter': 'Nama',
                'jawaban1': pe1,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Lahir',
                'jawaban1': pe2.split("-").reverse().join("-"),
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': pe3,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban1': pe4,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Pasien',
                'jawaban1': pe5,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Lahir',
                'jawaban1': pe6.split("-").reverse().join("-"),
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': pe7,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban1': pe8,
                'keterangan': jenis,
                'jenis': 'general_anestesi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if("rr_dewasa" == jenis){

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
                'skor':skor1,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Warna Kulit',
                'jawaban1': isi2,
                'skor':skor2,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Aktifitas',
                'jawaban1': isi3,
                'skor':skor3,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Respirasi',
                'jawaban1': isi4,
                'skor':skor4,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kardio Vaskuler',
                'jawaban1': isi5,
                'skor':skor5,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if("rr_anak_anak" == jenis){

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
                'skor':skor1,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Respirasi',
                'jawaban1': isi2,
                'skor':skor2,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Aktifitas',
                'jawaban1': isi3,
                'skor':skor3,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if("rr_sab" == jenis){

        var pe1 = $('[name=sab1]:checked').val();

        if (pe1 != undefined) {

            var isi1 = pe1.split("|")[0];

            var skor1 = pe1.split("|")[1];

            data.push({
                'parameter': 'Penilaian',
                'jawaban1': isi1,
                'skor':skor1,
                'keterangan': jenis,
                'jenis': 'pindah_rr',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        $('#save_op_' + jenis).hide();
        $('#load_op_' + jenis).show();
        dwr.engine.setAsync(true);
        AsesmenOperasiAction.saveAsesmenOperasi(result, {
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
                } else {
                    $('#save_op_' + jenis).show();
                    $('#load_op_' + jenis).hide();
                    $('#warning_op_' + jenis).show().fadeOut(5000);
                    $('#msg_op_' + jenis).text(res.msg);
                    $('#modal-op-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_op_' + jenis).show().fadeOut(5000);
        $('#msg_op_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-op-' + jenis).scrollTop(0);
    }
}

function detailOperasi(jenis) {
    if (jenis != '') {
        var body = "";
        var head = "";
        var firt = "";
        var last = "";
        var tgl = "";
        var totalSkor = 0;
        var cekData = false;
        AsesmenOperasiAction.getListAsesmenOperasi(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    if ("pre_operasi" == item.keterangan) {
                        var jwb1 = "";
                        var jwb2 = "";
                        if (item.jawaban1 != null) {
                            jwb1 = item.jawaban1;
                        }
                        if (item.jawaban2 != null) {
                            jwb2 = item.jawaban2;
                        }
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td align="center">' + checkIcon(jwb1) + '</td>' +
                            '<td align="center">' + checkIcon(jwb2) + '</td>' +
                            '</tr>';
                    } else if ("kondisi_pasien" == item.keterangan) {
                        var jwb1 = "";
                        var jwb2 = "";
                        if (item.jawaban1 != null) {
                            jwb1 = item.jawaban1;
                        }
                        if (item.jawaban2 != null) {
                            jwb2 = item.jawaban2;
                        }
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb1 + '</td>' +
                            '<td>' + jwb2 + '</td>' +
                            '</tr>';
                    } else if ("penandaan_area" == item.keterangan) {
                        if ("area_penanda" == item.jenis) {
                            body += '<tr>' +
                                '<td align="center">' +
                                '<img src="' + item.jawaban1 + '">' +
                                '</td>' +
                                '</tr>';
                        }
                        if ("ttd_pasien" == item.jenis) {
                            body += '<tr>' +
                                '<td align="left">' +
                                '<p>TTD Pasien</p>' +
                                '<img src="' + item.jawaban1 + '" style="width: 100px; height: 70px">' +
                                '</td>' +
                                '</tr>';
                        }
                        if ("ttd_dokter" == item.jenis) {
                            body += '<tr>' +
                                '<td align="right">' +
                                '<p style="margin-top: -113px">TTD Dokter</p>' +
                                '<img src="' + item.jawaban1 + '" style="width: 100px; height: 70px;">' +
                                '</td>' +
                                '</tr>';
                        }
                    } else if ("general_informasi" == item.keterangan || "regional_informasi" == item.keterangan) {
                        var jwb1 = "";
                        var jwb2 = "";
                        if (item.jawaban1 != null) {
                            jwb1 = item.jawaban1;
                        }
                        if (item.jawaban2 != null) {
                            jwb2 = item.jawaban2;
                        }
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb1 + '</td>' +
                            '<td align="center">' + checkIcon(jwb2) + '</td>' +
                            '</tr>';
                    } else if ("general_penyataan" == item.keterangan || "regional_penyataan" == item.keterangan) {
                        var jwb1 = "";
                        if (item.jawaban1 != null) {
                            jwb1 = item.jawaban1;
                        }
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td align="center">' + '<img src="' + jwb1 + '" style="width: 150px">' + '</td>' +
                            '</tr>';
                    } else if("rr_dewasa" == item.keterangan || "rr_anak_anak" == item.keterangan || "rr_sab" == item.keterangan){
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
                    }else {
                        var jwb1 = "";
                        if (item.jawaban1 != null) {
                            jwb1 = item.jawaban1;
                        }
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb1 + '</td>' +
                            '</tr>';
                        tgl = item.createdDate;
                    }

                });

                if ("pre_operasi" == jenis) {
                    head = '<tr>' +
                        '<td><b>Persiapan Pasien Pre Operasi</b></td>' +
                        '<td><b>Perawat Pengirim</b></td>' +
                        '<td><b>Perawat OK</b></td>' +
                        '</tr>';
                }
                if ("kondisi_pasien" == jenis) {
                    head = '<tr>' +
                        '<td><b>Pemeriksaan</b></td>' +
                        '<td><b>Sebelum Ditransfer</b></td>' +
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

                    if("rr_dewasa" == jenis){
                        if(totalSkor >= 8 && totalSkor <=10){
                            con = "Ya";
                        }else{
                            con = "Tidak"
                        }
                    }
                    if("rr_anak_anak" == jenis){
                        if(totalSkor >= 5 && totalSkor <=6){
                            con = "Ya";
                        }else{
                            con = "Tidak"
                        }
                    }
                    if("rr_sab" == jenis){
                        if(totalSkor <= 2){
                            con = "Ya";
                        }else{
                            con = "Tidak"
                        }
                    }

                    if(cekData){
                        last =  '<tr style="font-weight: bold"><td colspan="2">Total</td><td align="center">'+totalSkor+'</td></tr>' +
                                '<tr style="font-weight: bold" bgcolor="#ffebcd"><td colspan="2">Kriteria Pindah Ruang</td><td align="center">'+con+'</td></tr>';
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
            var url = '<%= request.getContextPath() %>/pages/images/cancel-flat-new.png';
            $('#btn_op_' + jenis).attr('src', url);
            $('#btn_op_' + jenis).attr('onclick', 'delRowOperasi(\'' + jenis + '\')');
        });
    }
}

function delRowOperasi(id) {
    $('#del_op_' + id).remove();
    var url = '<%= request.getContextPath() %>/pages/images/icons8-plus-25.png';
    $('#btn_op_' + id).attr('src', url);
    $('#btn_op_' + id).attr('onclick', 'detailOperasi(\'' + id + '\')');
}

function checkIcon(val) {
    var fa = val;
    if (val == "Ya") {
        fa = '<i class="fa fa-check"></i>'
    }
    return fa;
}

function penandaAreaOperasi() {
    var jenisKelamin = $('#jenis_kelamin').val();
    $('#jk_pasien').html(jenisKelamin);
    var url = "";
    if ("Laki-Laki" == jenisKelamin) {
        url = '<%= request.getContextPath() %>/pages/images/penanda-laki-laki.jpg';
    } else {
        url = '<%= request.getContextPath() %>/pages/images/penanda-perempuan.jpg';
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
        var jenisKelamin = $('#jenis_kelamin').val();
        var url = "";
        if ("Laki-Laki" == jenisKelamin) {
            url = '<%= request.getContextPath() %>/pages/images/penanda-laki-laki.jpg';
        } else {
            url = '<%= request.getContextPath() %>/pages/images/penanda-perempuan.jpg';
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
        var hh = today.getHours();
        var min = today.getMinutes();
        var sec = today.getSeconds();
        today = dd + '-' + mm + '-' + yyyy + ' ' + hh + ':' + min + ':' + sec;
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
        var hh = today.getHours();
        var min = today.getMinutes();
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
        var hh = today.getHours();
        var min = today.getMinutes();
        var sec = today.getSeconds();
        today = hh + ':' + min;
    }
    return today;
}


$('[name=cek_anamnesa1]').click(function () {
    var val = $('[name=cek_anamnesa1]:checked').val();
    if (val == "Ya") {
        $('#form-ket-alergi').show();
    } else {
        $('#form-ket-alergi').hide();
    }
});
