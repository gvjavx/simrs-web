function dataResikoJatuh(jenis){
    var data = [];
    var dataCari = [];

    data.push({
        'jenis': 'humpty_dumpty',
        'parameter': '1. Usia',
        'jawaban': '0-3 tahun|4=3-7 tahun|3=8-13 tahun|2=>3 tahun/14-18 tahun|1'
    });
    data.push({
        'jenis': 'humpty_dumpty',
        'parameter': '2. Jenis Kelamin',
        'jawaban': 'Laki-laki|2=Perempuan|1'
    });
    data.push({
        'jenis': 'humpty_dumpty',
        'parameter': '3. Diagnosis',
        'jawaban': 'Kelainan Neurologi|4=Gangguan Oks (Dypsnoe, Anamia, Sinkop, Dehidrasi, Anoreksia, Sakit Kepala)|3=Kelemahan Fisik|2=Diagnosis lain|1'
    });
    data.push({
        'jenis': 'humpty_dumpty',
        'parameter': '4. Gangguan Kognitif',
        'jawaban': 'Tidak memahami keterbatasan|3=Lupa keterbatasan|2=Orientasi terhadap kelemahan|1'
    });
    data.push({
        'jenis': 'humpty_dumpty',
        'parameter': '5. Lingkungan',
        'jawaban': 'Riwayat jatuh dari tempat tidur saat bayi/anak|4=Memakai alat bantu|3=Berada ditempat tidur|2=Berada diluar ruangan|1'
    });
    data.push({
        'jenis': 'humpty_dumpty',
        'parameter': '6. Respon Operasi/Sedasi/Anastesi',
        'jawaban': '< 24|3=< 48|2=> 48|1'
    });
    data.push({
        'jenis': 'humpty_dumpty',
        'parameter': '7. Penggunaan Obat',
        'jawaban': 'Obat sedative (Kecuali Pasien ICU yang tersedasu dan paralyzed, Hipnotik, Phenotiazin, Barbiturat, Anti Depresan, Deuretik, Narkotik)|3=Salah satu obat di atas|2=Pengobatan lain|1'
    });
    data.push({
        'jenis': 'skala_morse',
        'parameter': '1. Riwayat Jatuh',
        'jawaban': 'Ya|25=Tidak|0'
    });
    data.push({
        'jenis': 'skala_morse',
        'parameter': '2. Diagnosa Sekunder',
        'jawaban': 'Ya|15=Tidak|0'
    });
    data.push({
        'jenis': 'skala_morse',
        'parameter': '3. Alat Bantu',
        'jawaban': 'Kursi/perabot|30=Kruk/tongkat|15=Tidak ada/bed rest/Kursi roda|0'
    });
    data.push({
        'jenis': 'skala_morse',
        'parameter': '4. Terapi Intravena',
        'jawaban': 'Ya|20=Tidak|0'
    });
    data.push({
        'jenis': 'skala_morse',
        'parameter': '5. Gaya Berjalan',
        'jawaban': 'Terganggu|20=Lemah|10=Normal/bed rest/Kursi roda|0'
    });
    data.push({
        'jenis': 'skala_morse',
        'parameter': '6. Status Mental',
        'jawaban': 'Lupa akan keterbatasan/pelupa|15=Menyadari kemampuannya|0'
    });
    data.push({
        'jenis': 'geriatri',
        'parameter': '1. Riwayat Jatuh',
        'jawaban': 'Ya|6=Tidak|0'
    });
    data.push({
        'jenis': 'geriatri',
        'parameter': '2. Status Mental',
        'jawaban': 'Dilirium|14=Tidak|0'
    });
    data.push({
        'jenis': 'geriatri',
        'parameter': '3. Penglihatan',
        'jawaban': 'Kacamata, Katarak, penglihatan buram, Glucoma|1=Tidak|0'
    });
    data.push({
        'jenis': 'geriatri',
        'parameter': '4. Kebiasaan Kemih',
        'jawaban': 'Terdapat perubahan perilaku kemih|20=Tidak|0'
    });
    data.push({
        'jenis': 'geriatri',
        'parameter': '5. Tranfer/Gerakan',
        'jawaban': 'Mandiri|0=Perlu Bantuan|1=Terganggu|2'
    });
    data.push({
        'jenis': 'geriatri',
        'parameter': '6. Mobilitas',
        'jawaban': 'Mandiri|0=Berjalan dengan bantuan 1 orang|1=Kursi Roda|2'
    });
    if(jenis != ''){
        $.each(data, function (i, item) {
            if(jenis == item.jenis){
                dataCari.push({
                    'jenis': item.jenis,
                    'parameter': item.parameter,
                    'jawaban': item.jawaban
                });
            }
        });
    }
    return dataCari;
}

function setResikoJatuh(id, tahun) {
    var jenis  = "";
    var resikoJatuh = "";

    if(parseInt(tahun) >= 0 && parseInt(tahun) <= 18){
        jenis = "humpty_dumpty";
    }else if(parseInt(tahun) > 18 && parseInt(tahun) <= 65){
        jenis = "skala_morse";
    }else if(parseInt(tahun) > 65){
        jenis = "geriatri";
    }

    if(jenis != ''){
        $.each(dataResikoJatuh(jenis), function (i, item) {
            if(item.parameter != ''){
                var jarak = "";
                if(i > 0){
                    jarak = 'jarak';
                }
                var resiko = '<div class="row '+jarak+' resiko_jatuh">\n' +
                    '<div class="form-group">\n' +
                    '<label class="col-md-12" id="label_resiko_jatuh'+i+'">'+item.parameter+'</label>\n' +
                    '</div>\n' +
                    '</div>';
                var dump  = item.jawaban.split("=");
                var tempA = "<div class=\"row\">\n" +
                            "<div class=\"form-group\">\n";
                var tempB = "</div>\n" +
                            "</div>";
                var temp  = "";
                var rsk   = "";
                $.each(dump, function (idx, itemx) {
                    var tempLabel = itemx.split("|");
                    var label = tempLabel[0];
                    temp += '<div class="col-md-12">\n' +
                        '<div class="custom02" style="margin-top: -7px">\n' +
                        '    <input type="radio" value="'+itemx+'" id="resiko_jatuh'+i+idx+'" name="resiko_jatuh'+i+'" /><label for="resiko_jatuh'+i+idx+'">'+label+'</label>\n' +
                        '</div>\n' +
                        '</div>'
                });
                if(temp != ''){
                    rsk = resiko + tempA + temp + tempB;
                }

                if(rsk != ''){
                    resikoJatuh += rsk;
                }
            }
        });
        $('#'+id).html(resikoJatuh);
        $('#jenis_resiko').val(jenis);
    }
}