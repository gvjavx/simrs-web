function dataForm(jenis){
    var data = [];
    var dataCari = [];
    data.push({
        'jenis':'catatan',
        'parameter': '1. Tanggal',
        'jawaban':'',
        'class':'tgl',
        'keterangan':'i'
    });
    data.push({
        'jenis':'catatan',
        'parameter': '2. Nama Bidan',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'catatan',
        'parameter': '3. Tempat Persalinan',
        'jawaban':'Rumah Ibu|Puskesmas|Polindes|Rumah Sakit|Klinik Swasta|Lainnya',
        'class':'',
        'keterangan':'c'
    });
    data.push({
        'jenis':'catatan',
        'parameter': '4. Alamat Tempat Persalinan',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'catatan',
        'parameter': '5. Catatan',
        'jawaban':'Rujuk|Kala I|Kala II|Kala III|Kala IV',
        'class':'',
        'keterangan':'c'
    });
    data.push({
        'jenis':'catatan',
        'parameter': '6. Alasan Merujuk',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'catatan',
        'parameter': '7. Tempat Merujuk',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'catatan',
        'parameter': '8. Pendamping Saat Masuk',
        'jawaban':'Bidan|Teman|Suami|Dukun|Keluarga|Tidak ada',
        'class':'',
        'keterangan':'c'
    });
    data.push({
        'jenis':'catatan',
        'parameter': '9. Masalah dalam kehamilan /persalinan ini',
        'jawaban':'Gawat Darurat|Perdarahan|RDK|Infeksi|PMTCT',
        'class':'',
        'keterangan':'c'
    });

    data.push({
        'jenis':'kala1',
        'parameter': '10. Partoraf melewati garis waspada',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala1',
        'parameter': '11. Masalah lain, sebutkan',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'kala1',
        'parameter': '12. Penatalaksanaan masalah tersebut',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'kala1',
        'parameter': '13. Hasilnya',
        'jawaban':'',
        'class':'',
        'keterangan':'t'
    });

    data.push({
        'jenis':'kala2',
        'parameter': '14. Episiotomi',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala2',
        'parameter': '15. Pendamping pada saat persalinan',
        'jawaban': 'Suami|Teman|Keluarga|Dukun|Tidak Ada',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala2',
        'parameter': '16. Gawat Janin',
        'jawaban':'Ya|i|Tidak|Pemantauan DJJ setiap 5-10 menit selama kala II',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala2',
        'parameter': '17. Distosia bahu',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala2',
        'parameter': '18. Masalah lain, penatalaksanakan masalah tab dan hasil',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    if(jenis != ''){
        $.each(data, function (i, item) {
            if(jenis == item.jenis){
                dataCari.push({
                    'jenis':item.jenis,
                    'parameter': item.parameter,
                    'jawaban':item.jawaban,
                    'class':item.class,
                    'keterangan':item.keterangan
                });
            }
        });
    }
    return dataCari;
}

function setForm(id, jenis){
    var temp = "";
    $.each(dataForm(jenis), function (i, item) {
        var jarak = "jarak";
        if(i == 0){
            jarak = "";
        }
        if(item.keterangan == 'i'){
            temp += '<div class="row '+jarak+'">\n' +
                '<div class="form-group">\n' +
                '    <label class="col-md-5 form-set" id="parameter'+i+'">'+item.parameter+'</label>\n' +
                '    <div class="col-md-7">\n' +
                '        <input class="form-control '+item.class+'" name="jawaban'+i+'">\n' +
                '    </div>\n' +
                '</div>\n' +
                '</div>';
        }
        if(item.keterangan == 'c'){
            var jml = item.jawaban.split("|");
            var check = "";
            var atas = '<div class="row '+jarak+'">\n' +
                '<div class="form-group">\n' +
                '<label class="col-md-12 form-set" id="parameter'+i+'">'+item.parameter+'</label>';
            var bawah = '</div>' +
                        '</div>';
            $.each(jml, function (ic, itemc) {
                check += '<div class="col-md-4">'+
                    '<div class="form-check">\n' +
                    '<input type="checkbox" name="jawaban'+i+'" id="jawaban'+i+ic+'" value="'+itemc+'">\n' +
                    '<label for="jawaban'+i+ic+'"></label> '+itemc+'\n' +
                    '</div>' +
                    '</div>';
            });
            if(check != ''){
                temp += atas+check+bawah;
            }
        }
        if(item.keterangan == 'r'){
            var jml = item.jawaban.split("|");
            var radio = "";
            var atas = '<div class="row '+jarak+'">\n' +
                '<div class="form-group">\n' +
                '<label class="col-md-12 form-set" id="parameter'+i+'">'+item.parameter+'</label>';
            var bawah = '</div>' +
                '</div>';
            $.each(jml, function (ir, itemr) {
                radio += '<div class="col-md-12">' +
                    '<div class="custom02">\n' +
                    '<input type="radio" name="jawaban'+i+'" value="'+itemr+'" id="jawaban'+i+ir+'" />' +
                    '<label for="jawaban'+i+ir+'">'+itemr+'</label>\n' +
                    '</div></div>';
            });
            if(radio != ''){
                temp += atas+radio+bawah;
            }
        }
        if(item.keterangan == 't'){
            temp += '<div class="row '+jarak+'">\n' +
                '<div class="form-group">\n' +
                '    <label class="col-md-5 form-set" id="parameter'+i+'">'+item.parameter+'</label>\n' +
                '    <div class="col-md-7">\n' +
                '        <textarea class="form-control" name="jawaban'+i+'"></textarea>\n' +
                '    </div>\n' +
                '</div>\n' +
                '</div>';
        }
    });
    if(temp != ''){
        $('#'+id).html(temp);
        setDataPasien();
    }
}