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
        'id': '01',
        'parameter': 'Diagnosa',
        'informasi': 'Hernia Inguinalis Lateralis|Hernia Inguinalis Medialis|Hernia',
        'keterangan': 'c'
    });
    data.push({
        'id': '01',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Benjolan di daerah inguinal hilang timbul',
        'keterangan': 'l'
    });
    data.push({
        'id': '01',
        'parameter': 'Tindakan kedokteran',
        'informasi': 'Herniotomy & Hernioraphy|Ligasi tinggi',
        'keterangan': 'c'
    });
    data.push({
        'id': '01',
        'parameter': 'Indikasi Tindakan',
        'informasi': 'Mencegah terjadinya cedera organ dalam dinding perut karena masuk dan terjepit oleh kantong/ cincin hernia',
        'keterangan': 'l'
    });
    data.push({
        'id': '01',
        'parameter': 'Tujuan',
        'informasi': 'Menutup kantong hernia agar organ dalam rongga perut tidak masuk ke kantong dan mengalami cidera',
        'keterangan': 'l'
    });
    data.push({
        'id': '01',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan, cidera usus, cidera kandung kemih',
        'keterangan': 'l'
    });
    data.push({
        'id': '01',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi, infeksi dalam rongga perut',
        'keterangan': 'l'
    });
    data.push({
        'id': '01',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'id': '01',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });

    data.push({
        'id': '02',
        'parameter': 'Diagnosa',
        'informasi': 'G|P',
        'keterangan': 'i'
    });
    data.push({
        'id': '02',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'USG|Klinis',
        'keterangan': 'c'
    });
    data.push({
        'id': '02',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Low Segment Caesarean Section (LSCS)',
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
        'parameter': 'Tata cara',
        'informasi': 'Incisi laparatomy, incisi segmen bawah rahim, prosedur melahirkan janin.',
        'keterangan': 'l'
    });
    data.push({
        'id': '02',
        'parameter': 'Tujuan',
        'informasi': 'Evakuasi/ melahirkan bayi melalui insisi perut',
        'keterangan': 'l'
    });
    data.push({
        'id': '02',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan > 1 liter, robekan rahim, cedera usus, cedera kandung kemih, perawatan ICU, Angkat rahim, kematian ibu',
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
        'id': '03',
        'parameter': 'Diagnosa',
        'informasi': 'Appendicitis Acut',
        'keterangan': 'l'
    });
    data.push({
        'id': '03',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Nyeri area McBurney|Rovsing`s Sign|Blumberg Sign|Demam|Leucositosis',
        'keterangan': 'c'
    });
    data.push({
        'id': '03',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Appendictomy|Explorasi Laparatomy',
        'keterangan': 'c'
    });
    data.push({
        'id': '03',
        'parameter': 'Indikasi Tindakan',
        'informasi': 'Appendicitis Acut|Peri Appendiculair Infiltrat|Appendix Perforasi',
        'keterangan': 'c'
    });
    data.push({
        'id': '03',
        'parameter': 'Tata cara',
        'informasi': 'Incisi daerah abdomen, identifikasi, memotong dan menutup appendiks, merawat perdarahan',
        'keterangan': 'l'
    });
    data.push({
        'id': '03',
        'parameter': 'Tujuan',
        'informasi': 'Membuang organ yang terinfeksi agar infeksi tidak menyebar ke organ lain',
        'keterangan': 'l'
    });
    data.push({
        'id': '03',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan, cidera usus, cidera kandung kemih',
        'keterangan': 'l'
    });
    data.push({
        'id': '03',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi, infeksi dalam rongga perut',
        'keterangan': 'l'
    });
    data.push({
        'id': '03',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'id': '03',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });

    data.push({
        'id': '04',
        'parameter': 'Diagnosa',
        'informasi': 'Soft Tissue Tumor',
        'keterangan': 'l'
    });
    data.push({
        'id': '04',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Benjolan abnormal, pembesaran lambat',
        'keterangan': 'l'
    });
    data.push({
        'id': '04',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Ekstirpasi|Eksisi',
        'keterangan': 'c'
    });
    data.push({
        'id': '04',
        'parameter': 'Indikasi Tindakan',
        'informasi': 'Benjolan mengganggu rasa nyaman, menimbulkan nyeri, menekan jaringan/ organ di sekitarnya misalnya syaraf, pembuluh darah dll.',
        'keterangan': 'l'
    });
    data.push({
        'id': '04',
        'parameter': 'Tata cara',
        'informasi': 'Incisi di permukaan kulit pada benjolan, identifikasi dan mengangkat tumor, jahit kembali',
        'keterangan': 'l'
    });
    data.push({
        'id': '04',
        'parameter': 'Tujuan',
        'informasi': 'Membuang benjolan abnormal, menghilangkan penekanan pada jaringan sekitar',
        'keterangan': 'l'
    });
    data.push({
        'id': '04',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan, syaraf perifer dan tendon putus',
        'keterangan': 'l'
    });
    data.push({
        'id': '04',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi',
        'keterangan': 'l'
    });
    data.push({
        'id': '04',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'id': '04',
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
        'informasi': 'USG|Pendarahan',
        'keterangan': 'c'
    });
    data.push({
        'id': '05',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Delatasi|Kurretage',
        'keterangan': 'c'
    });
    data.push({
        'id': '05',
        'parameter': 'Indikasi Tindakan',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '05',
        'parameter': 'Tata cara',
        'informasi': 'Mengeluarkan jaringan abnormal/ sisa kehamilan  dalam rongga rahim, menggunakan instrumen kurretage',
        'keterangan': 'l'
    });
    data.push({
        'id': '05',
        'parameter': 'Tujuan',
        'informasi': 'Mengeluarkan isi uterus (jaringan abnormal)',
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
        'id': '06',
        'parameter': 'Diagnosa',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '06',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Klinis : Deformitas, krepitasi|X Foto',
        'keterangan': 'c'
    });
    data.push({
        'id': '06',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'DEBRIDEMENT|ORIF|CRIF',
        'keterangan': 'c'
    });
    data.push({
        'id': '06',
        'parameter': 'Indikasi Tindakan',
        'informasi': 'Nyeri',
        'keterangan': 'l'
    });
    data.push({
        'id': '06',
        'parameter': 'Tata cara',
        'informasi': 'Incisi di permukaan kulit, identifikasi tulang, reposisi, pasang fixasi, jahit kembali',
        'keterangan': 'l'
    });
    data.push({
        'id': '06',
        'parameter': 'Tujuan',
        'informasi': 'Menyambungkan tulang yang patah',
        'keterangan': 'l'
    });
    data.push({
        'id': '06',
        'parameter': 'Resiko',
        'informasi': 'Perdarahan, syaraf dan tendon putus',
        'keterangan': 'l'
    });
    data.push({
        'id': '06',
        'parameter': 'Komplikasi',
        'informasi': 'Infeksi luka operasi, osteomyelitis, reaksi alergi implant',
        'keterangan': 'l'
    });
    data.push({
        'id': '06',
        'parameter': 'Prognosa',
        'informasi': 'Ad Bonam',
        'keterangan': 'l'
    });
    data.push({
        'id': '06',
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
        'informasi': 'USG|Anamnese',
        'keterangan': 'c'
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
        'parameter': 'Tata cara',
        'informasi': 'Incisi laparatomy, identifikasi, pengangkatan tumor/ jaringan infeksi',
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
        'informasi': 'Perdarahan, infertilisasi, Pengangkatan rahim, cidera usus, kematian',
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

    data.push({
        'id': '09',
        'parameter': 'Diagnosis (WD dan DD)',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '09',
        'parameter': 'Dasar Diagnosis',
        'informasi': 'Wawancara riwayat penyakit, pemeriksaan fisik, pemeriksaan laboratorium',
        'keterangan': 'l'
    });
    data.push({
        'id': '09',
        'parameter': 'Tindakan Kedokteran',
        'informasi': 'Tranfusi Darah',
        'keterangan': 'l'
    });
    data.push({
        'id': '09',
        'parameter': 'Indikasi Tindakan',
        'informasi': 'Untuk perbaiakan keadaan umum pada kondisi anemia',
        'keterangan': 'l'
    });
    data.push({
        'id': '09',
        'parameter': 'Tata Cara',
        'informasi': 'Memasukkan darah/produk darah melalui jalur intravena sesua prosedur yang dijelaskan secara lisan',
        'keterangan': 'l'
    });
    data.push({
        'id': '09',
        'parameter': 'Tujuan',
        'informasi': 'Meningkatkan kadar produk sarah yang dibutuhkan sesuai target',
        'keterangan': 'l'
    });
    data.push({
        'id': '09',
        'parameter': 'Resiko',
        'informasi': 'Reaksi tranfusi ringan sampai syok (termasuk berbagai kemungkinan yang tidak diprediksi sebelumnya)',
        'keterangan': 'l'
    });
    data.push({
        'id': '09',
        'parameter': 'Komplikasi',
        'informasi': 'Reaksi tranfusi ringan sampai syok (termasuk berbagai kemungkinan yang tidak diprediksi sebelumnya)',
        'keterangan': 'l'
    });
    data.push({
        'id': '09',
        'parameter': 'Prognosis',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '09',
        'parameter': 'Alternattif',
        'informasi': 'CAPD, Cangkok ginjal',
        'keterangan': 'l'
    });
    data.push({
        'id': '09',
        'parameter': 'Perkiraan Biaya',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '10',
        'parameter': 'Teknik Anestesi',
        'informasi': 'General Anestesi|Sedasi Moderat/Dalam',
        'keterangan': 'c'
    });
    data.push({
        'id': '10',
        'parameter': 'Kondisi yang diharapkan',
        'informasi': 'Rasa Cemas berkurang, mengantuk s/d kesadaran hilang, tidak merasa nyeri',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Tata cara',
        'informasi': 'Obat yang diinjeksikan ke pembuluh darah, obat dihirupkan keseluruh nafas',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Resiko',
        'informasi': 'Aspirasi, udema pasru, cedera mulut (gigi, lidah, bibir), suara serak dan nteri tenggorokan',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Tujuan',
        'informasi': 'Mengurangi rasa cemas, menghilangkan nyeri menjaga fungsi organ selama pembedahan',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Komplikasi',
        'informasi': 'Reaksi alergi obat, stroke, serangan jantung kematian',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Tranfusi',
        'informasi': 'Tidak|Ya',
        'keterangan': 'r'
    });
    data.push({
        'id': '10',
        'parameter': 'Komplikasi',
        'informasi': 'Reaksi tranfusi, penularan penyakit lewat darah',
        'keterangan': 'l'
    });
    data.push({
        'id': '10',
        'parameter': 'Rencana Pemberian obat nyeri',
        'informasi': 'Injeksi|Patch|Peridural|Injeksi kontinyu|Lainnya',
        'keterangan': 'c'
    });
    data.push({
        'id': '10',
        'parameter': 'Prognosa',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '10',
        'parameter': 'Alternatif',
        'informasi': '',
        'keterangan': 'i'
    });

    data.push({
        'id': '11',
        'parameter': 'Teknik Anestesi',
        'informasi': 'Sub Arachnoid Blok (SAB)|Epidural|CSE/Block Syarat Mayor',
        'keterangan': 'c'
    });
    data.push({
        'id': '11',
        'parameter': 'Kondisi yang diharapkan',
        'informasi': 'Akan terjadi mati rasa pada bagian tubuh tertentu',
        'keterangan': 'l'
    });
    data.push({
        'id': '11',
        'parameter': 'Tata cara',
        'informasi': 'Obat diinjeksikan pada kanal tulang belakang posisi pasien tidur miring atau duduk',
        'keterangan': 'l'
    });
    data.push({
        'id': '11',
        'parameter': 'Resiko',
        'informasi': 'Sakit kepala, sakit panggung, infeksi ditempat injeksi total spinal',
        'keterangan': 'l'
    });
    data.push({
        'id': '11',
        'parameter': 'Tujuan',
        'informasi': 'Mengurangi rasa cemas, menghilangkan nyeri menjaga fungsi organ selama pembedahan',
        'keterangan': 'l'
    });
    data.push({
        'id': '11',
        'parameter': 'Komplikasi',
        'informasi': 'Reaksi alergi obat, stroke, serangan jantung kematian',
        'keterangan': 'l'
    });
    data.push({
        'id': '11',
        'parameter': 'Tranfusi',
        'informasi': 'Tidak|Ya',
        'keterangan': 'r'
    });
    data.push({
        'id': '11',
        'parameter': 'Komplikasi',
        'informasi': 'Reaksi tranfusi, penularan penyakit lewat darah',
        'keterangan': 'l'
    });
    data.push({
        'id': '11',
        'parameter': 'Rencana Pemberian obat nyeri',
        'informasi': 'Injeksi|Patch|Peridural|Injeksi kontinyu|Lainnya',
        'keterangan': 'c'
    });
    data.push({
        'id': '11',
        'parameter': 'Prognosa',
        'informasi': '',
        'keterangan': 'i'
    });
    data.push({
        'id': '11',
        'parameter': 'Alternatif',
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
    data.push({
        'tipe': 'op',
        'id_tindakan': '11',
        'nama_tindakan': 'General Anestesi'
    });
    data.push({
        'tipe': 'op',
        'id_tindakan': '12',
        'nama_tindakan': 'Regional Anestesi'
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

function setTindakanMedisValue(id, tipe, idHidden){
    $.each(kategoriTindakanMedis(tipe), function (i, item) {
        if(id == item.id_tindakan){
            $('#'+idHidden).val(item.nama_tindakan);
        }
    });
}
