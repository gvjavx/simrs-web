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
        'jawaban':'Gawat Darurat|Perdarahan|RDK|Infeksi|PMTCT|Tidak Ada',
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
        'jawaban':'Ya|Tidak|Pemantauan DJJ setiap 5-10 menit selama kala II',
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

    data.push({
        'jenis':'kala3',
        'parameter': '19. Inisiasi menyusu dini',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '20. Lama kala III',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '21. Pemberian Oksitosin (2x)',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '22. Pemberian ulang oksitosin (2x)',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '23. Penegangan tali pusat terkendali',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '24. Masase fundus uteri',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '25. Plasenta lahir lengkap, Jika tidak lengkap, tindakan yang dilakukan',
        'jawaban':'',
        'class':'',
        'keterangan':'t'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '26. Plasenta tindak lahir > 30 menit',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '27. Laserai',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '29. Jika laserasi perineum, derajat 1/2/3/4 Tindakan',
        'jawaban':'Penjahitan dengan / tanpa anastesi|Tidak dijahit',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '30. Jumlah darah yang keluar / perdarahan',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'kala3',
        'parameter': '31. Masalah dan penatalaksanan masalah tersebut',
        'jawaban':'',
        'class':'',
        'keterangan':'t'
    });


    data.push({
        'jenis':'kala4',
        'parameter': '32. Kondisi ibu, KU: ',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'kala4',
        'parameter': 'Tekanan Darah',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'kala4',
        'parameter': 'Nadi',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'kala4',
        'parameter': 'Napas',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'kala4',
        'parameter': '33. Masalah dan penatalaksanaan masalah',
        'jawaban':'',
        'class':'',
        'keterangan':'t'
    });

    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': '34. Berat Badan',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': '35. Panjang Badan',
        'jawaban':'',
        'class':'',
        'keterangan':'i'
    });
    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': '36. Jenis Kelamin',
        'jawaban':'Laki-Laki|Perempuan',
        'class':'',
        'keterangan':'s'
    });
    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': '37. Penilaian bayi baru lahir',
        'jawaban':'Baik|Ada penyulit',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': '38. Bayi Lahir',
        'jawaban':'',
        'class':'',
        'keterangan':'l'
    });
    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': 'Normal, Tindakan',
        'jawaban':'Menghangatkan|Mengerikan|Rangsangan Taktil|Memastikan IMD atau naluri menyusu segera',
        'class':'',
        'keterangan':'c'
    });
    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': 'Asfiksia ringan/pucat/biru/lemas, Tindakan',
        'jawaban':'Menghangatkan|Bebas Jalan napas|Mengerikan|Rangsangan taktil|Lainnya|Pakaian/selimutbayi',
        'class':'',
        'keterangan':'c'
    });
    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': 'Cacat Bawaan Sebutkan',
        'jawaban':'',
        'class':'',
        'keterangan':'t'
    });
    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': 'Hipotermi, Tindakan',
        'jawaban':'',
        'class':'',
        'keterangan':'t'
    });
    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': '39. Pemberian ASI setelah jam pertama bayi lahir',
        'jawaban':'Ya|Tidak',
        'class':'',
        'keterangan':'r'
    });
    data.push({
        'jenis':'bayi_baru_lahir',
        'parameter': '40. Masalah lain, sebutkan',
        'jawaban':'',
        'class':'',
        'keterangan':'t'
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
        if(item.keterangan == 's'){
            var jml = item.jawaban.split("|");
            var option = "";
            $.each(jml, function (is, items) {
                option += '<option value="'+items+'">'+items+'</option>';
            });
            temp += '<div class="row '+jarak+'">\n' +
                '<div class="form-group">\n' +
                '    <label class="col-md-5 form-set" id="parameter'+i+'">'+item.parameter+'</label>\n' +
                '    <div class="col-md-7">\n' +
                '        <select class="form-control" name="jawaban'+i+'">' +
                '           <option value="">[Select One]</option> '+ option +
                '        </select>\n' +
                '    </div>\n' +
                '</div>\n' +
                '</div>';
        }
        if(item.keterangan == 'l'){
            temp += '<div class="row '+jarak+'">\n' +
                '<div class="form-group">\n' +
                '    <label class="col-md-12" id="parameter'+i+'">'+item.parameter+'</label>\n' +
                '<input type="hidden" name="jawaban'+i+'" value="">'+
                '</div>\n' +
                '</div>';
        }
    });
    if(temp != ''){
        $('#'+id).html(temp);
    }
}