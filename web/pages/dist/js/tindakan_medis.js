function pilihTindakanMedis(val, id) {
    var namaTindakan = "";
    if (val != '') {
        $.each(kategoriTindakanMedis(''), function (idx, itemx) {
            if (itemx.id_tindakan == val) {
                namaTindakan = itemx.nama_tindakan;
            }
        });
        $('#form-' + id).show();
        $('#tindakan_medis_' + id).val(namaTindakan);
        var body = "";
        $.each(tindakanMedis(val), function (i, item) {

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

        $('#body_' + id).html(body);

    } else {
        $('#form-' + id).hide();
        $('#tindakan_medis_' + id).val('');
        $('#body_' + id).html('');
    }
}

function tindakanMedis(id) {

    var data = [];
    var dataCari = [];

    data.push({
        'id': '02',
        'parameter': 'Diagnosa',
        'informasi': 'G|P',
        'keterangan': 'i'
    });
    data.push({
        'id': '02',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Pemeriksaan Fisik|Anamnesa|Pemeriksaan Penunjang',
        'keterangan': 'r'
    });
    data.push({
        'id': '02',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Low Segement Caesarean Section (LSCS)',
        'keterangan': 'l'
    });
    data.push({
        'id': '02',
        'parameter': 'Indikasi Tindakan',
        'informasi': 'Ibu|Janin',
        'keterangan': 'i'
    });
    data.push({
        'id': '02',
        'parameter': 'Tata Cara',
        'informasi': 'Incisi laparatomy, incisi segmen bawah rahim, prosedur melahirkan janin',
        'keterangan': 'l'
    });
    data.push({
        'id': '02',
        'parameter': 'Tujuan',
        'informasi': 'Evakuasi / melahirkan bayi melalui insisi perut',
        'keterangan': 'l'
    });
    data.push({
        'id': '02',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan > 1 liter, robekan rahim, cedera usus, sedera kandung kemih, perawatan ICU, Angkat rahim, kematian ibu',
        'keterangan': 'l'
    });
    data.push({
        'id': '02',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi, infeksi organ dalam rongga perut',
        'keterangan': 'l'
    });
    data.push({
        'id': '02',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'id': '02',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '05',
        'parameter': 'Diagnosa',
        'informasi': 'Blighted Ovum|Menometrorargia|Abortus Incomplet|DUB|Sisa Kehamilan',
        'keterangan': 'c'
    });
    data.push({
        'id': '05',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Pemeriksaan Fisik|Anamnesa|Pemeriksaan Penunjang',
        'keterangan': 'r'
    });
    data.push({
        'id': '05',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Delatasi|Kurretage',
        'keterangan': 'r'
    });
    data.push({
        'id': '05',
        'parameter': 'Indikasi Tindakan',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '05',
        'parameter': 'Tata Cara',
        'informasi': 'Mengeluarkan jaringan abnormal/ sisa kehamilan dalam rongga rahim, menggunakan instrumen kurretage',
        'keterangan': 'l'
    });
    data.push({
        'id': '05',
        'parameter': 'Tujuan',
        'informasi': 'Mengerluarkan isi uterus (jaringan abnormal)',
        'keterangan': 'l'
    });
    data.push({
        'id': '05',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan, perforasi uterus, cidera usus, cidera kandung kemih',
        'keterangan': 'l'
    });
    data.push({
        'id': '05',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi, infeksi dalam rongga perut',
        'keterangan': 'l'
    });
    data.push({
        'id': '05',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'id': '05',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '07',
        'parameter': 'Diagnosa',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '07',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'USG|Anamnesa',
        'keterangan': 'r'
    });
    data.push({
        'id': '07',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Laparatomy',
        'keterangan': 'i'
    });
    data.push({
        'id': '07',
        'parameter': 'Indikasi Tindakan',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '07',
        'parameter': 'Tata Cara',
        'informasi': 'Incisi laparatomy, identifikasi, pengangkatan tumor / jaringan infeksi',
        'keterangan': 'l'
    });
    data.push({
        'id': '07',
        'parameter': 'Tujuan',
        'informasi': 'Evakuasi tumor',
        'keterangan': 'l'
    });
    data.push({
        'id': '07',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan, infertelisasi, pegangkatan rahim, cidera usus, kematian',
        'keterangan': 'l'
    });
    data.push({
        'id': '07',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi, infeksi organ dalam rongga perut',
        'keterangan': 'l'
    });
    data.push({
        'id': '07',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'id': '07',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '08',
        'parameter': 'Pengertian',
        'informasi': 'Pemberian alat bantu nafas (mekanik) yang memberikan bantuan nafas dengan cara mebantu sebagian atau mengambil alih semua fungsi ventilasi',
        'keterangan': 'l'
    });
    data.push({
        'id': '08',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Cedera otak berat, Post operasi trepanasi, Potensial terjadi gangguan airway dan breathing, Hypoxia (Sesak, RR > 35, Nafas Cuping hidung, Adanya gerak nafas tambahan retraksi kalau berat tampak cyanosis)',
        'keterangan': 'l'
    });
    data.push({
        'id': '08',
        'parameter': 'Tindakan Medis',
        'informasi': 'Intubasi, Ventilator(Setting mode)',
        'keterangan': 'l'
    });
    data.push({
        'id': '08',
        'parameter': 'Tata Cara',
        'informasi': 'Setting mode ventilator, koneksikan tubing ventilator ke ETT',
        'keterangan': 'l'
    });
    data.push({
        'id': '08',
        'parameter': 'Tujuan',
        'informasi': 'Pemenuhan kebutuhan Oksigen (oksigenasi)',
        'keterangan': 'l'
    });
    data.push({
        'id': '08',
        'parameter': 'Resiko',
        'informasi': 'Pneumo thoraks, Hypotensi',
        'keterangan': 'l'
    });
    data.push({
        'id': '08',
        'parameter': 'Komplikasi',
        'informasi': 'VAP, pneumothorax, barrotrauma',
        'keterangan': 'l'
    });
    data.push({
        'id': '08',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'id': '08',
        'parameter': 'Tindakan Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '08',
        'parameter': 'Biaya*',
        'informasi': '',
        'keterangan': 'i'
    });

    data.push({
        'id': '10',
        'parameter': 'Diagnosis (WD dan DD)',
        'informasi': 'CKD V on HD',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Riwayat penyakit, pemeriksaan fisik, pemeriksaan penunjang',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Hemodialisa',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Indikasi Tindakan',
        'informasi': 'CKD V, gangguan eletronik, produk sampah ginjal dalam kadar toksik, sindroma kelebihan cairan.' +
            'Kegawatan dibidang nefrologi(hipertensi, oedema paru, encepalopati reumia,anuria / oliguria)',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Tata Cara',
        'informasi': 'Pembuluh darah arteri dan vena dihubungkan dengan mesin hemodialisa yang menganalisa ' +
            'darah, lalu sampah dan cairan dipindahkan dari tubuh dan darah kembali ke tubuh',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Tujuan',
        'informasi': 'Mengatur keseimbangan eletronik, keseimbangan cairan dan membersihkan tubuh dari sampah ginjal',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Resiko',
        'informasi': 'Pendarahan, pembengkakan dan infeksi di tempat penusukan, mual-muntah, kontaminasi air' +
            'yang digunakan hemodialisa, kram otot, penurunan tekanan darah, gejala' +
            'ketidakseimbangan, irama jantung tidak teratur, reaksi cairan dialisat, kematian',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Prognosis',
        'informasi': 'Dubia ad malam',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Alternattif',
        'informasi': 'CAPD, Cangkok ginjal',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Perkiraan Biaya',
        'informasi': '',
        'keterangan': 'i'
    });


    if (id != '') {
        $.each(data, function (i, item) {
            if (id == item.id) {
                dataCari.push({
                    'id': item.id,
                    'parameter': item.parameter,
                    'informasi': item.informasi,
                    'keterangan': item.keterangan
                });
            }
        });
    }
    return dataCari;
}

function kategoriTindakanMedis(tipe) {
    var data = [];
    var dataCari = [];
    data.push({
        'tipe': 'ina',
        'id_tindakan': '01',
        'nama_tindakan': 'Hernia'
    });
    data.push({
        'tipe': 'ina',
        'id_tindakan': '02',
        'nama_tindakan': 'Sectio Caesaria'
    });
    data.push({
        'tipe': 'ina',
        'id_tindakan': '03',
        'nama_tindakan': 'Appendictomy'
    });
    data.push({
        'tipe': 'ina',
        'id_tindakan': '04',
        'nama_tindakan': 'Soft Tissue Tumor'
    });
    data.push({
        'tipe': 'ina',
        'id_tindakan': '05',
        'nama_tindakan': 'Kurretage'
    });
    data.push({
        'tipe': 'ina',
        'id_tindakan': '06',
        'nama_tindakan': 'Fraktur'
    });
    data.push({
        'tipe': 'ina',
        'id_tindakan': '07',
        'nama_tindakan': 'Laparatomy'
    });
    data.push({
        'tipe': 'ina',
        'id_tindakan': '08',
        'nama_tindakan': 'Ventilator'
    });
    data.push({
        'tipe': 'ina',
        'id_tindakan': '09',
        'nama_tindakan': 'Transfusi Darah'
    });
    data.push({
        'tipe': 'hd',
        'id_tindakan': '09',
        'nama_tindakan': 'Transfusi Darah'
    });
    data.push({
        'tipe': 'hd',
        'id_tindakan': '10',
        'nama_tindakan': 'Hemodialisa'
    });
    if (tipe != '') {
        $.each(data, function (i, item) {
            if (tipe == item.tipe) {
                dataCari.push({
                    'tipe': item.tipe,
                    'id_tindakan': item.id_tindakan,
                    'nama_tindakan': item.nama_tindakan
                });
            }
        });
    } else {
        return data;
    }
    return dataCari;
}

function selectOptionTM(tipe, id) {
    var option = '<option value="">[Select One]</option>';
    $.each(kategoriTindakanMedis(tipe), function (i, item) {
        option += '<option value="' + item.id_tindakan + '">' + item.nama_tindakan + '</option>';
    });
    $('#' + id).html(option);
}
