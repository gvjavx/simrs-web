function saveCPPT(jenis, ket, tipe) {

    var data = "";
    var va1 = $('#cppt1').val();
    var va2 = $('#cppt2').val();
    var va3 = $('#cppt3').val();
    var va4 = $('#cppt4').val();

    var nadi = $('#cppt5_nadi').val();
    var suhu = $('#cppt5_suhu').val();
    var rr = $('#cppt5_rr').val();
    var tensi = $('#cppt5_tensi').val();
    var va5 = $('#ket_cppt5').val();

    var kesadaran = $('#tk').val();
    var spo2 = $('#spo2').val();
    var o2 = $('#o2').val();

    var va6 = $('#cppt6').val();
    var va7 = $('#cppt7').val();
    var va8 = $('#cppt8').val();
    var nP = $('#nama_petugas').val();
    var nD = $('#nama_dpjp').val();
    var sP = $('#sip_petugas').val();
    var sD = $('#sip_dpjp').val();
    var v9 = document.getElementById("cppt9");
    var v10 = document.getElementById("cppt10");
    var va9 = isCanvasBlank(v9);
    var va10 = isCanvasBlank(v10);

    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }

    if (va1 && va2 && va3 && va4 && va6 && va7 && va8 &&
        nP && sP && !va9) {

        var tipeEWS = $('#tipe_ews').val();

        var ttd1 = v9.toDataURL("image/png"),
            ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
        var ttd2 = v10.toDataURL("image/png"),
            ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

        var totalEws = 0;
        var kesimpulanEWS = "";
        var frekuensiEWS = "";
        var tempEWS = "";

        if("anak_anak" == tipeEWS){
            var merintih = $('#merintih').val();
            var perilaku = $('[name=ews3]:checked').val();
            var vaskuler = $('[name=ews4]:checked').val();
            var nebulasi = $('[name=ews6]:checked').val();

            if(merintih == "Ya"){
                totalEws = totalEws + 3;
            }else{
                var age = getAge(tglLahir);
                var tahun = age.split("|")[0];
                var bulan = age.split("|")[1];
                var tgl = age.split("|")[2];

                var tempBulan = 0;

                if(parseInt(tahun) > 0){
                    tempBulan = parseInt(tahun) * 12;
                }else if(parseInt(bulan) > 0){
                    tempBulan = bulan;
                }else {
                    tempBulan = 1;
                }

                if(parseInt(tempBulan) > 0 && parseInt(tempBulan) <= 1){
                    if(parseInt(rr) >= 40 && parseInt(rr) <= 60){
                        totalEws = totalEws + 0;
                    }else if(parseInt(rr)-60 <= 10){
                        totalEws = totalEws + 1;
                    }else if(parseInt(rr)-60 > 10){
                        totalEws = totalEws + 2;
                    }
                }else if(parseInt(tempBulan) > 1 && parseInt(tempBulan) <= 12){
                    if(parseInt(rr) >= 35 && parseInt(rr) <= 40){
                        totalEws = totalEws + 0;
                    }else if(parseInt(rr)-40 <= 10){
                        totalEws = totalEws + 1;
                    }else if(parseInt(rr)-40 > 10){
                        totalEws = totalEws + 2;
                    }
                }else if(parseInt(tempBulan) > 23 && parseInt(tempBulan) <= (3*12)){
                    if(parseInt(rr) >= 25 && parseInt(rr) <= 30){
                        totalEws = totalEws + 0;
                    }else if(parseInt(rr)-30 <= 10){
                        totalEws = totalEws + 1;
                    }else if(parseInt(rr)-30 > 10){
                        totalEws = totalEws + 2;
                    }
                }else if(parseInt(tempBulan) > (4*12) && parseInt(tempBulan) <= (6*12)){
                    if(parseInt(rr) >= 21 && parseInt(rr) <= 23){
                        totalEws = totalEws + 0;
                    }else if(parseInt(rr)-23 <= 10){
                        totalEws = totalEws + 1;
                    }else if(parseInt(rr)-23 > 10){
                        totalEws = totalEws + 2;
                    }
                }else if(parseInt(tempBulan) > (7*12) && parseInt(tempBulan) <= (12*12)){
                    if(parseInt(rr) >= 19 && parseInt(rr) <= 21){
                        totalEws = totalEws + 0;
                    }else if(parseInt(rr)-21 <= 10){
                        totalEws = totalEws + 1;
                    }else if(parseInt(rr)-21 > 10){
                        totalEws = totalEws + 2;
                    }
                }else if(parseInt(tempBulan) > (13*12) && parseInt(tempBulan) <= (19*12)){
                    if(parseInt(rr) >= 16 && parseInt(rr) <= 18){
                        totalEws = totalEws + 0;
                    }else if(parseInt(rr)-18 <= 10){
                        totalEws = totalEws + 1;
                    }else if(parseInt(rr)-18 > 10){
                        totalEws = totalEws + 2;
                    }
                }
            }

            if(perilaku != undefined){
                var isi = perilaku.split("|")[0];
                var skor = perilaku.split("|")[1];
                totalEws = totalEws + parseInt(skor);
                tempEWS = tempEWS + 'Perilaku : '+isi+', ';
            }
            if(vaskuler != undefined){
                var isi = vaskuler.split("|")[0];
                var skor = vaskuler.split("|")[1];
                totalEws = totalEws + parseInt(skor);
                tempEWS = tempEWS + 'Kardiovaskuler : '+isi+', ';
            }
            if(nebulasi != undefined){
                var isi = nebulasi.split("|")[0];
                var skor = nebulasi.split("|")[1];
                totalEws = totalEws + parseInt(skor);
                tempEWS = tempEWS + 'Nebulisasi : '+isi+', ';
            }
            if(o2 != ''){
                var ketO2 = $('#ket_o2').val();
                if("Ya" == o2){
                    totalEws = totalEws + 2;
                    tempEWS = tempEWS + 'Oksigen : '+o2+', Ket: '+ketO2;
                }else{
                    totalEws = totalEws + 0;
                    tempEWS = tempEWS + 'Oksigen : '+o2;
                }
            }

            if(totalEws >= 0 && totalEws <= 2){
                kesimpulanEWS = "Normal";
                frekuensiEWS = "4 jam"
            }else if(totalEws == 3){
                kesimpulanEWS = "Rendah";
                frekuensiEWS = "1 jam"
            }else if(totalEws == 4){
                kesimpulanEWS = "Sedang";
                frekuensiEWS = "30 menit"
            }else if(totalEws >= 5){
                kesimpulanEWS = "Tinggi";
                frekuensiEWS = "Bad set monitor/everytime"
            }

        }else if("dewasa" == tipeEWS){

            if(parseInt(umur) < 65){
                totalEws = totalEws + 0;
            }else {
                totalEws = totalEws + 3;
            }

            tempEWS = tempEWS + 'Umur : '+umur+', ';

            if(kesadaran != ''){
                if("V/P/U/Gelisah" == kesadaran){
                    totalEws = totalEws + 3;
                }else{
                    totalEws = totalEws + 0;
                }
                tempEWS = tempEWS + 'Kesadaran : '+kesadaran+', ';
            }

            if(tensi != ''){
                var sistole = tensi.split("/")[0];
                if(parseInt(sistole) > 200){
                    totalEws = totalEws + 3;
                }else if(parseInt(sistole) >= 111 && parseInt(sistole) <= 219){
                    totalEws = totalEws + 0;
                }else if(parseInt(sistole) >= 101 && parseInt(sistole) <= 110){
                    totalEws = totalEws + 1;
                }else if(parseInt(sistole) >= 91 && parseInt(sistole) <= 100){
                    totalEws = totalEws + 2;
                }else if(parseInt(sistole) < 90){
                    totalEws = totalEws + 3;
                }else {
                    totalEws = totalEws + 0;
                }
            }

            if(nadi != ''){
                if(parseInt(nadi) >= 131){
                    totalEws = totalEws + 3;
                }else if(parseInt(nadi) >= 111 && parseInt(nadi) <= 130){
                    totalEws = totalEws + 2;
                }else if(parseInt(nadi) >= 91 && parseInt(nadi) <= 110){
                    totalEws = totalEws + 1;
                }else if(parseInt(nadi) >= 51 && parseInt(nadi) <= 90){
                    totalEws = totalEws + 0;
                }else if(parseInt(nadi) >= 41 && parseInt(nadi) <= 50){
                    totalEws = totalEws + 1;
                }else if(parseInt(nadi) <= 40){
                    totalEws = totalEws + 3;
                }else {
                    totalEws = totalEws + 0;
                }
            }

            if(rr != ''){
                if(parseInt(rr) > 25){
                    totalEws = totalEws + 3;
                }else if(parseInt(rr) >= 21 && parseInt(rr) <= 24){
                    totalEws = totalEws + 2;
                }else if(parseInt(rr) >= 12 && parseInt(rr) <= 20){
                    totalEws = totalEws + 0;
                }else if(parseInt(rr) >= 9 && parseInt(rr) <= 11){
                    totalEws = totalEws + 1;
                }else if(parseInt(rr) <= 8){
                    totalEws = totalEws + 3;
                }else {
                    totalEws = totalEws + 0;
                }
            }

            if(suhu != ''){
                if(parseInt(suhu) <= 39){
                    totalEws = totalEws + 2;
                }else if(parseInt(suhu) > 38 && parseInt(suhu) <= 39){
                    totalEws = totalEws + 1;
                }else if(parseInt(suhu) > 35 && parseInt(suhu) <= 38){
                    totalEws = totalEws + 0;
                }else if(parseInt(suhu) <= 35){
                    totalEws = totalEws + 3;
                }else {
                    totalEws = totalEws + 0;
                }
            }

            if(spo2 != ''){
                if("Ya" == o2){
                    if(parseInt(spo2) >= 97){
                        totalEws = totalEws + 3;
                    }else if(parseInt(spo2) >= 95 && parseInt(spo2) <= 96){
                        totalEws = totalEws + 1;
                    }else if(parseInt(spo2) >= 93 && parseInt(spo2) <= 94){
                        totalEws = totalEws + 2;
                    }else if(parseInt(spo2) >= 93){
                        totalEws = totalEws + 0;
                    }else if(parseInt(spo2) >= 88 && parseInt(spo2) <= 92){
                        totalEws = totalEws + 0;
                    }else if(parseInt(spo2) >= 86 && parseInt(spo2) <= 87){
                        totalEws = totalEws + 1;
                    }else if(parseInt(spo2) >= 84 && parseInt(spo2) <= 85){
                        totalEws = totalEws + 2;
                    }else if(parseInt(spo2) <= 83){
                        totalEws = totalEws + 3;
                    }else {
                        totalEws = totalEws + 0;
                    }
                }else{
                    if(parseInt(spo2) >= 96){
                        totalEws = totalEws + 0;
                    }else if(parseInt(spo2) >= 94 && parseInt(spo2) <= 95){
                        totalEws = totalEws + 1;
                    }else if(parseInt(spo2) >= 92 && parseInt(spo2) <= 93){
                        totalEws = totalEws + 2;
                    }else if(parseInt(spo2) <= 91){
                        totalEws = totalEws + 3;
                    }else {
                        totalEws = totalEws + 0;
                    }
                }

                tempEWS = tempEWS + 'SpO2 : '+spo2+' % , ';
            }

            if(o2 != ''){
                var ketO2 = $('#ket_o2').val();
                if("Ya" == o2){
                    totalEws = totalEws + 2;
                    tempEWS = tempEWS + 'Oksigen : '+o2+', Ket: '+ketO2;
                }else{
                    totalEws = totalEws + 0;
                    tempEWS = tempEWS + 'Oksigen : '+o2;
                }
            }

            if(totalEws == 0){
                kesimpulanEWS = "Sangat Rendah";
                frekuensiEWS = "min 6-8 jam"
            }else if(totalEws >= 1 && totalEws <= 4){
                kesimpulanEWS = "Rendah";
                frekuensiEWS = "min 4-6 jam"
            }else if(totalEws >= 5 && totalEws <= 6){
                kesimpulanEWS = "Sedang";
                frekuensiEWS = "min 1 jam"
            }else if(totalEws >= 7){
                kesimpulanEWS = "Tinggi";
                frekuensiEWS = "Bad set monitor/everytime"
            }

        }else if("obstetri" == tipeEWS){

            if(rr != ''){
                if(parseInt(rr) > 25){
                    totalEws = totalEws + 3;
                }else if(parseInt(rr) >= 21 && parseInt(rr) <= 25){
                    totalEws = totalEws + 2;
                }else if(parseInt(rr) >= 12 && parseInt(rr) <= 20){
                    totalEws = totalEws + 0;
                }else {
                    totalEws = totalEws + 0;
                }
            }

            if(spo2 != ''){
                if(parseInt(spo2) >= 95){
                    totalEws = totalEws + 0;
                }else if(parseInt(spo2) >= 92 && parseInt(spo2) <= 95){
                    totalEws = totalEws + 2;
                }else if(parseInt(spo2) < 92){
                    totalEws = totalEws + 3;
                }else {
                    totalEws = totalEws + 0;
                }
                tempEWS = tempEWS + 'SpO2 : '+spo2+' % , ';
            }

            if(o2 != ''){
                var ketO2 = $('#ket_o2').val();
                if("Ya" == o2){
                    totalEws = totalEws + 2;
                    tempEWS = tempEWS + 'Oksigen : '+o2+', Ket: '+ketO2;
                }else{
                    totalEws = totalEws + 0;
                    tempEWS = tempEWS + 'Oksigen : '+o2;
                }
            }

            if(suhu != ''){
                if(parseFloat(suhu) > 37.7){
                    totalEws = totalEws + 3;
                }else if(parseFloat(suhu) >= 37.2 && parseFloat(suhu) <= 37.7){
                    totalEws = totalEws + 1;
                }else if(parseFloat(suhu) > 36.1 && parseFloat(suhu) <= 37.1){
                    totalEws = totalEws + 0;
                }else if(parseFloat(suhu) <= 36){
                    totalEws = totalEws + 3;
                }else {
                    totalEws = totalEws + 0;
                }
            }

            if(tensi != ''){
                var sistole = tensi.split("/")[0];
                if(parseInt(sistole) > 160){
                    totalEws = totalEws + 3;
                }else if(parseInt(sistole) >= 151 && parseInt(sistole) <= 160){
                    totalEws = totalEws + 2;
                }else if(parseInt(sistole) >= 141 && parseInt(sistole) <= 150){
                    totalEws = totalEws + 1;
                }else if(parseInt(sistole) >= 90 && parseInt(sistole) <= 140){
                    totalEws = totalEws + 0;
                }else if(parseInt(sistole) < 90){
                    totalEws = totalEws + 3;
                }else {
                    totalEws = totalEws + 0;
                }

                var diastole = tensi.split("/")[1];
                if(parseInt(diastole) > 120){
                    totalEws = totalEws + 3;
                }else if(parseInt(diastole) >= 101 && parseInt(diastole) <= 120){
                    totalEws = totalEws + 2;
                }else if(parseInt(diastole) >= 91 && parseInt(diastole) <= 100){
                    totalEws = totalEws + 1;
                }else if(parseInt(diastole) >= 60 && parseInt(diastole) <= 90){
                    totalEws = totalEws + 0;
                }else {
                    totalEws = totalEws + 0;
                }
            }

            if(nadi != ''){
                if(parseInt(nadi) > 120){
                    totalEws = totalEws + 3;
                }else if(parseInt(nadi) >= 111 && parseInt(nadi) <= 120){
                    totalEws = totalEws + 2;
                }else if(parseInt(nadi) >= 61 && parseInt(nadi) <= 100){
                    totalEws = totalEws + 0;
                }else if(parseInt(nadi) >= 50 && parseInt(nadi) <= 60){
                    totalEws = totalEws + 2;
                }else if(parseInt(nadi) < 50){
                    totalEws = totalEws + 3;
                }else {
                    totalEws = totalEws + 0;
                }
            }

            if(kesadaran != ''){
                if("V/P/U/Gelisah" == kesadaran){
                    totalEws = totalEws + 3;
                }else{
                    totalEws = totalEws + 0;
                }
                tempEWS = tempEWS + 'Kesadaran : '+kesadaran+', ';
            }

            var nyeri = $('#nyeri').val();
            var perdarahan = $('#perdarahan').val();
            var protein = $('#protein').val();

            if(nyeri != ''){
                if("Abnormal" == nyeri){
                    totalEws = totalEws + 3;
                }else{
                    totalEws = totalEws + 0;
                }
                tempEWS = tempEWS + 'Nyeri : '+nyeri+', ';
            }

            if(perdarahan != ''){
                if(parseInt(perdarahan) >= 40){
                    totalEws = totalEws + 3;
                }else if(parseInt(perdarahan) >= 31 && parseInt(perdarahan) <= 40){
                    totalEws = totalEws + 2;
                }else if(parseInt(perdarahan) >= 16 && parseInt(perdarahan) <= 30){
                    totalEws = totalEws + 1;
                }else if(parseInt(perdarahan) <= 15){
                    totalEws = totalEws + 0;
                }else {
                    totalEws = totalEws + 0;
                }
                tempEWS = tempEWS + 'Perdarahan : '+perdarahan+', ';
            }

            if(protein != ''){
                if("++>" == protein){
                    totalEws = totalEws + 3;
                }else if("+" == protein){
                    totalEws = totalEws + 2;
                }else{
                    totalEws = totalEws + 0;
                }
                tempEWS = tempEWS + 'Protein Urine : '+protein+', ';
            }

            if(totalEws == 0){
                kesimpulanEWS = "Sangat Rendah";
                frekuensiEWS = "6-8 jam"
            }else if(totalEws >= 1 && totalEws <= 4){
                kesimpulanEWS = "Rendah";
                frekuensiEWS = "4-6 jam"
            }else if(totalEws >= 5 && totalEws <= 6){
                kesimpulanEWS = "Sedang";
                frekuensiEWS = "1 jam"
            }else if(totalEws >= 7){
                kesimpulanEWS = "Tinggi";
                frekuensiEWS = "Bad set monitor/everytime"
            }
        }

        if(va10){
            ttd2 = "";
        }

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1.split("-").reverse().join("-") + ' ' + va2 + ':00',
            'ppa': va3,
            'subjective': va4,
            'nadi': nadi,
            'suhu': suhu,
            'rr': rr,
            'tensi': replaceUnderLine(tensi),
            'kesadaran': kesadaran,
            'spo2': spo2,
            'o2': o2,
            'total_ews': ''+totalEws,
            'kesimpulan': kesimpulanEWS,
            'frekuensi': frekuensiEWS,
            'data_ews': tempEWS,
            'objective': va5,
            'assesment': va6,
            'planning': va7,
            'nama_dokter': nD,
            'nama_petugas': nP,
            'sip_dokter': sD,
            'sip_petugas': sP,
            'instruksi': va8,
            'keterangan': ket,
            'ttd_petugas': ttd1,
            'ttd_dpjp': ttd2
        }

        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            $('#save_' + tipe + '_' + jenis).hide();
            $('#load_' + tipe + '_' + jenis).show();
            dwr.engine.setAsync(true);
            CatatanTerintegrasiAction.saveCatatanTerintegrasi(result, pasienData, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_' + tipe + '_' + jenis).show();
                        $('#load_' + tipe + '_' + jenis).hide();
                        $('#modal-' + tipe + '-' + jenis).modal('hide');
                        $('#warning_' + tipe + '_' + ket).show().fadeOut(5000);
                        $('#msg_' + tipe + '_' + ket).text("Berhasil menambahkan data ....");
                        $('#modal-' + tipe + '-' + jenis).scrollTop(0);
                        delRowCPPT(jenis, ket, tipe);
                        detailCPPT(jenis, ket, tipe);

                    } else {
                        $('#save_' + tipe + '_' + jenis).show();
                        $('#load_' + tipe + '_' + jenis).hide();
                        $('#warning_' + tipe + '_' + jenis).show().fadeOut(5000);
                        $('#msg_' + tipe + '_' + jenis).text(res.msg);
                        $('#modal-' + tipe + '-' + jenis).scrollTop(0);
                    }
                }
            });
        }
    } else {
        $('#save_' + tipe + '_' + jenis).show();
        $('#load_' + tipe + '_' + jenis).hide();
        $('#warning_' + tipe + '_' + jenis).show().fadeOut(5000);
        $('#msg_' + tipe + '_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-' + tipe + '-' + jenis).scrollTop(0);
    }
}

function detailCPPT(jenis, ket, tipe, gizi) {
    if(!cekSession()){
        var head = "";
        var body = "";
        var first = "";
        var last = "";
        var cekData = false;

        CatatanTerintegrasiAction.getListCatatanTerintegrasi(idDetailCheckup, ket, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var setTtd = '';
                    if(item.ttdDpjp != '' && item.ttdDpjp != null){
                        setTtd = '<img style="width: 100%; height: 50px" src="' + item.ttdDpjp + '">';
                    }else{
                        setTtd = '<i onclick="setTtdDpjp(\''+item.idCatatanTerintegrasi+'\', \''+jenis+'\', \''+ket+'\', \''+tipe+'\',\'dokter\')" class="fa fa-pencil hvr-grow" style="cursor: pointer; color: #1ab7ea; margin-bottom: 10px"></i>';
                    }

                    var del = '<i id="delete_' + item.idCatatanTerintegrasi + '" onclick="conCPT(\'' + jenis + '\', \'' + ket + '\', \'' + tipe + '\', \'' + item.idCatatanTerintegrasi + '\')" style="color: red" class="fa fa-trash fa-1x hvr-grow"></i>';
                    var tempDel = "";
                    if("gizi" == gizi){
                        if("Gizi" == item.ppa){
                            tempDel = del;
                        }else{
                            tempDel = "";
                        }
                    }else{
                        if("Gizi" == item.ppa){
                            tempDel = "";
                        }else{
                            tempDel = del;
                        }
                    }

                    if("hand_over" == item.tipe){
                        if(item.ttdPenerima != '' && item.ttdPenerima != null){
                            setTtd = '<img style="width: 50%; height: 50px" src="' + item.ttdPenerima + '">';
                        }else{
                            setTtd = '<i onclick="setTtdDpjp(\''+item.idCatatanTerintegrasi+'\', \''+jenis+'\', \''+ket+'\', \''+tipe+'\')" class="fa fa-pencil hvr-grow" style="cursor: pointer; color: #1ab7ea; margin-bottom: 10px"></i>';
                        }
                        body += '<tr>' +
                            '<td width="10%">' + converterDateTime(item.waktu) + '</td>' +
                            '<td colspan="4" align="center">' +
                            '<img style="width: 50%; height: 50px" src="' + item.ttdPemberi + '">' +
                            '<p style="margin-top: -5px">' + cekNullCppt(item.namaPemberi) + '</p>' +
                            '<p style="margin-top: -10px">' + cekNullCppt(item.sipPemberi) + '</p>' +
                            '</td>' +
                            '<td colspan="4" align="center">' + setTtd +
                            '<p style="margin-top: -5px">' + cekNullCppt(item.namaPenerima) + '</p>' +
                            '<p style="margin-top: -10px">' + cekNullCppt(item.sipPenerima) + '</p>' +
                            '</td>' +
                            '<td align="center">' + tempDel + '</td></tr>';
                    }else{
                        var object =
                            '<p>Tensi : ' + item.tensi + ' mmHg</p>' +
                            '<p>Nadi : ' + item.nadi + ' x/menit</p>'+
                            '<p>Suhu : ' + item.suhu + ' ˚C</p>' +
                            '<p>RR : ' + item.rr + ' x/menit' +'</p>'+
                            '<p>Keterangan : ' + cekNullCppt(item.objective)+'</p>'+
                            '<p>-----------------------</p>' +
                            '<p>Total Skor EWS : '+cekNullCppt(item.ews)+'</p>' +
                            '<p>Kesimpulan : '+cekNullCppt(item.kesimpulan)+'</p>' +
                            '<p>Monitoring : '+cekNullCppt(item.monitoring)+'</p>';
                        body += '<tr>' +
                            '<td width="10%">' + converterDateTime(item.waktu) + '</td>' +
                            '<td>' + cekNullCppt(item.ppa) + '</td>' +
                            '<td>' + cekNullCppt(item.subjective) + '</td>' +
                            '<td width="17%">' + object + '</td>' +
                            '<td>' + cekNullCppt(item.assesment) + '</td>' +
                            '<td>' + cekNullCppt(item.planning) + '</td>' +
                            '<td>' + cekNullCppt(item.intruksi) + '</td>' +
                            '<td>' + '<img style="width: 100%; height: 50px" src="' + item.ttdPetugas + '">' +
                            '<p style="margin-top: -5px">' + cekNullCppt(item.namaPetugas) + '</p>' +
                            '<p style="margin-top: -10px">' + cekNullCppt(item.sipPetugas) + '</p>' +
                            '</td>' +
                            '<td>' + setTtd +
                            '<p style="margin-top: -5px">' + cekNullCppt(item.namaDokter) + '</p>' +
                            '<p style="margin-top: -10px">' + cekNullCppt(item.sipDokter) + '</p>' +
                            '</td>' +
                            '<td align="center">' + tempDel + '</td></tr>';
                        cekData = true;
                    }
                });
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }
            if (cekData) {
                head = '<tr style="font-weight: bold">' +
                    '<td width="13%">Waktu</td>' +
                    '<td>PPA</td>' +
                    '<td>Subjective</td>' +
                    '<td>Objective</td>' +
                    '<td>Assesment</td>' +
                    '<td>Planning</td>' +
                    '<td>Intruksi</td>' +
                    '<td>TTD Petugas</td>' +
                    '<td>TTD DPJP</td>' +
                    '<td align="center">Action</td>' +
                    '</tr>'
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_' + tipe + '_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_' + tipe + '_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_' + tipe + '_' + jenis).attr('src', url);
            $('#btn_' + tipe + '_' + jenis).attr('onclick', 'delRowCPPT(\'' + jenis + '\',\'' + ket + '\', \'' + tipe + '\', \''+gizi+'\')');
        });
    }
}

function delRowCPPT(jenis, ket, tipe, gizi) {
    $('#del_' + tipe + '_' + jenis).remove();
    var url = "";
    if ("monitoring_hd" == ket || "asesmen-ugd" == ket || "catatan_terintegrasi_ina" == ket) {
        url = contextPath + '/pages/images/icons8-add-list-25.png';
    } else {
        url = contextPath + '/pages/images/icons8-plus-25.png';
    }
    $('#btn_' + tipe + '_' + jenis).attr('src', url);
    $('#btn_' + tipe + '_' + jenis).attr('onclick', 'detailCPPT(\'' + jenis + '\',\'' + ket + '\',\'' + tipe + '\', \''+gizi+'\')');
}

function cekNullCppt(item) {
    var res = "";
    if (item != null) {
        res = item;
    }
    return res;
}

function conCPT(jenis, ket, tipe, id) {
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show: true, backdrop: 'static'});
    $('#save_con_rm').attr('onclick', 'delCPT(\'' + jenis + '\', \'' + ket + '\',\'' + tipe + '\', \'' + id + '\')');
}

function delCPT(jenis, ket, tipe, id) {
    $('#modal-confirm-rm').modal('hide');
    if(!cekSession()){
        var dataPasien = {
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'id_pasien': idPasien,
            'id_rm': tempidRm
        }
        var result = JSON.stringify(dataPasien);
        startIconSpin('delete_' + id);
        dwr.engine.setAsync(true);
        CatatanTerintegrasiAction.saveDelete(id, result, {
            callback: function (res) {
                if (res.status == "success") {
                    stopIconSpin('delete_' + id);
                    $('#warning_' + tipe + '_' + ket).show().fadeOut(5000);
                    $('#msg_' + tipe + '_' + ket).text("Berhasil menghapus data...");
                    delRowCPPT(jenis, ket, tipe);
                    detailCPPT(jenis, ket, tipe);
                } else {
                    stopSpin('delete_' + id);
                    $('#warn_' + ket).show().fadeOut(5000);
                    $('#msg_' + ket).text(res.msg);
                }
            }
        });
    }
}

function setTtdDpjp(id, jenis, ket, tipe, jen){
    setDataPasien();
    if("dokter" == jen){
        $('#modal-ina-ttd_dpjp').modal({show: true, backdrop: 'static'});
        $('#save_ttd_dpjp').attr('onclick','saveTtdDpjp(\''+id+'\', \''+jenis+'\', \''+ket+'\', \''+tipe+'\')');
    }else{
        $('#modal-ina-ttd_penerima').modal({show: true, backdrop: 'static'});
        $('#save_ttd_penerima').attr('onclick','saveTtdPenerima(\''+id+'\', \''+jenis+'\', \''+ket+'\', \''+tipe+'\')');
    }
}

function saveTtdDpjp(id, jenis, ket, tipe){
    var nama = $('#nama_dpjp_edit').val();
    var sip = $('#sip_dpjp_edit').val();
    var ttd = document.getElementById("ttd_edit");
    var cek = isCanvasBlank(ttd);
    if(nama && sip != '' && !cek){
        if(!cekSession()){
            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            var dataPasien = {
                'id_catatan_integrasi': id,
                'nama_dokter': nama,
                'sip_dokter': sip,
                'ttd_dpjp': ttd1
            }
            delRowCPPT(jenis, ket, tipe);
            var result = JSON.stringify(dataPasien);
            $('#save_ttd_dpjp').hide();
            $('#load_ttd_dpjp').show();
            dwr.engine.setAsync(true);
            CatatanTerintegrasiAction.updateTtdDpjp(result, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#modal-ina-ttd_dpjp').modal('hide');
                        $('#save_ttd_dpjp').show();
                        $('#load_ttd_dpjp').hide();
                        $('#warning_ina_catatan_terintegrasi_ina').show().fadeOut(5000);
                        $('#msg_ina_catatan_terintegrasi_ina').text("Berhasil mengupdate data...");
                        detailCPPT(jenis, ket, tipe);
                    } else {
                        $('#warning_ina_ttd_dpjp').show().fadeOut(5000);
                        $('#msg_ina_ttd_dpjp').text(res.msg);
                        $('#save_ttd_dpjp').show();
                        $('#load_ttd_dpjp').hide();
                    }
                }
            });
        }
    }else {
        $('#warning_ina_ttd_dpjp').show().fadeOut(5000);
        $('#msg_ina_ttd_dpjp').text("Silahkan cek kembali inputan anda...!");
    }
}

function saveTtdPenerima(id, jenis, ket, tipe){
    var nama = $('#nama_ttd_penerima').val();
    var sip = $('#sip_ttd_penerima').val();
    var ttd = document.getElementById("ttd_penerima");
    var cek = isCanvasBlank(ttd);
    console.log(nama)
    console.log(sip)
    console.log(cek)
    if(nama && sip != '' && !cek){
        if(!cekSession()){
            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            var dataPasien = {
                'id_catatan_integrasi': id,
                'nama_penerima': nama,
                'sip_penerima': sip,
                'ttd_penerima': ttd1
            }
            delRowCPPT(jenis, ket, tipe);
            var result = JSON.stringify(dataPasien);
            $('#save_ttd_penerima').hide();
            $('#load_ttd_penerima').show();
            dwr.engine.setAsync(true);
            CatatanTerintegrasiAction.updateTtdDpjp(result, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#modal-ina-ttd_penerima').modal('hide');
                        $('#save_ttd_penerima').show();
                        $('#load_ttd_penerima').hide();
                        $('#warning_ina_catatan_terintegrasi_ina').show().fadeOut(5000);
                        $('#msg_ina_catatan_terintegrasi_ina').text("Berhasil mengupdate data...");
                        detailCPPT(jenis, ket, tipe);
                    } else {
                        $('#warning_ina_ttd_penerima').show().fadeOut(5000);
                        $('#msg_ina_ttd_penerima').text(res.msg);
                        $('#save_ttd_penerima').show();
                        $('#load_ttd_penerima').hide();
                    }
                }
            });
        }
    }else {
        $('#warning_ina_ttd_penerima').show().fadeOut(5000);
        $('#msg_ina_ttd_penerima').text("Silahkan cek kembali inputan anda...!");
    }
}

function setRR(val, tujuan){
    if("Ya" == val){
        $('#'+tujuan).attr('disabled', true);
        $('#'+tujuan).val('0');
    }else{
        $('#'+tujuan).attr('disabled', false);
        $('#'+tujuan).val('');
    }
}

function setHideShow(val, form){
    if("Ya" == val){
        $('#'+form).show();
    }else{
        $('#'+form).hide();
    }
}

function setEWS(jenis, umur, id){
    var set = '';
    if("obstetri" == jenis){
        set = '<div class="alert alert-info alert-dismissible">\n' +
            '                        <p id="msg_ews">Early Warning System (EWS) Obstetri</p>\n' +
            '</div>\n' +
            '<input id="tipe_ews" value="obstetri" type="hidden">\n' +
            '<div class="row jarak">\n' +
            '    <div class="form-group">\n' +
            '        <label class="col-md-3" ><b>O</b>bjective</label>\n' +
            '        <div class="col-md-4">\n' +
            '            <span>Tensi </span> <small>(mmHg)</small>\n' +
            '            <input class="form-control tensi-pasien" id="cppt5_tensi" data-inputmask="\'mask\': [\'999/999\']" data-mask="">\n' +
            '        </div>\n' +
            '        <div class="col-md-4">\n' +
            '            <span>Suhu </span> <small>(&#8451)</small>\n' +
            '            <input class="form-control suhu-pasien" id="cppt5_suhu" type="number">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<div class="row jarak">\n' +
            '    <div class="form-group">\n' +
            '        <div class="col-md-offset-3 col-md-4">\n' +
            '            <span>Nadi </span> <small>(x/menit)</small>\n' +
            '            <input class="form-control nadi-pasien" id="cppt5_nadi" type="number">\n' +
            '        </div>\n' +
            '        <div class="col-md-4">\n' +
            '            <span>RR </span> <small>(x/menit)</small>\n' +
            '            <input class="form-control rr-pasien" id="cppt5_rr" type="number">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<div class="row jarak">\n' +
            '    <div class="form-group">\n' +
            '        <div class="col-md-offset-3 col-md-4">\n' +
            '            <span>Tingkat Kesadaran</span>\n' +
            '            <select class="form-control" id="tk">\n' +
            '                <option value="Alert">Alert</option>\n' +
            '                <option value="V/P/U">V/P/U/Gelisah</option>\n' +
            '            </select>\n' +
            '        </div>\n' +
            '        <div class="col-md-4">\n' +
            '            <span>SpO2 </span> <small>(%)</small>\n' +
            '            <input class="form-control" id="spo2" type="number">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<div class="row jarak">\n' +
            '    <div class="form-group">\n' +
            '        <div class="col-md-offset-3 col-md-4">\n' +
            '            <span>Nyeri</span>\n' +
            '            <select class="form-control" id="nyeri">\n' +
            '                <option value="Normal">Normal</option>\n' +
            '                <option value="Abnormal">Abnormal</option>\n' +
            '            </select>\n' +
            '        </div>\n' +
            '        <div class="col-md-4">\n' +
            '            <span>Perdarahan </span> <small>(%)</small>\n' +
            '            <input class="form-control" id="perdarahan" type="number">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<div class="row jarak">\n' +
            '    <div class="form-group">\n' +
            '        <div class="col-md-offset-3 col-md-4">\n' +
            '            <span>Protein Urin</span>\n' +
            '            <select class="form-control" id="protein">\n' +
            '                <option value="+">+</option>\n' +
            '                <option value="++>">++></option>\n' +
            '            </select>\n' +
            '        </div>\n' +
            '        <div class="col-md-4">\n' +
            '            <span>Alat Bantu O2</span>\n' +
            '            <select class="form-control" id="o2" onchange="setHideShow(this.value, \'form_al\')">\n' +
            '                <option value="Tidak">Tidak</option>\n' +
            '                <option value="Ya">Ya</option>\n' +
            '            </select>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<div class="row jarak">\n' +
            '    <div class="form-group">\n' +
            '        <div class="col-md-offset-3 col-md-8" id="form_al" style="display: none">\n' +
            '            <span>Keterangan O2</span>\n' +
            '            <input class="form-control" id="ket_o2">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<div class="row jarak">\n' +
            '    <div class="form-group">\n' +
            '        <div class="col-md-offset-3 col-md-8">\n' +
            '            <textarea class="form-control" id="ket_cppt5" placeholder="Keterangan Objective"></textarea>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>';
    }else{
        if(parseInt(umur) >= 0 && parseInt(umur) <= 16){
            set = '<div class="alert alert-info alert-dismissible">\n' +
                '    <p id="msg_ews">Early Warning System (EWS) Anak-Anak</p>\n' +
                '</div>\n' +
                '<input id="tipe_ews" value="anak_anak" type="hidden">\n' +
                '<div class="row jarak">\n' +
                '    <div class="form-group">\n' +
                '        <label class="col-md-3" ><b>O</b>bjective</label>\n' +
                '        <div class="col-md-4">\n' +
                '            <span>Tensi </span> <small>(mmHg)</small>\n' +
                '            <input class="form-control tensi-pasien" id="cppt5_tensi" data-inputmask="\'mask\': [\'999/999\']" data-mask="">\n' +
                '        </div>\n' +
                '        <div class="col-md-4">\n' +
                '            <span>Suhu </span> <small>(&#8451)</small>\n' +
                '            <input class="form-control suhu-pasien" id="cppt5_suhu" type="number">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>\n' +
                '<div class="row jarak">\n' +
                '    <div class="form-group">\n' +
                '        <div class="col-md-offset-3 col-md-4">\n' +
                '            <span>Apakah Merintih?</span>\n' +
                '            <select class="form-control" id="merintih" onchange="setRR(this.value, \'cppt5_rr\')">\n' +
                '                <option value="Tidak">Tidak</option>\n' +
                '                <option value="Ya">Ya</option>\n' +
                '            </select>\n' +
                '        </div>\n' +
                '        <div class="col-md-4">\n' +
                '            <span>RR </span> <small>(x/menit)</small>\n' +
                '            <input class="form-control rr-pasien" id="cppt5_rr" type="number">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>\n' +
                '<div class="row jarak">\n' +
                '    <div class="form-group">\n' +
                '        <div class="col-md-offset-3 col-md-4">\n' +
                '            <span>Nadi </span> <small>(x/menit)</small>\n' +
                '            <input class="form-control nadi-pasien" id="cppt5_nadi" type="number">\n' +
                '        </div>\n' +
                '        <div class="col-md-4">\n' +
                '            <span>SpO2 </span> <small>(x/menit)</small>\n' +
                '            <input class="form-control" id="spo2" type="number">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>\n' +
                '<div class="row jarak">\n' +
                '    <div class="form-group">\n' +
                '        <div class="col-md-offset-3 col-md-4">\n' +
                '            <span>Alat Bantu O2</span>\n' +
                '            <select class="form-control" id="o2" onchange="setHideShow(this.value, \'form_al\')">\n' +
                '                <option value="Tidak">Tidak</option>\n' +
                '                <option value="Ya">Ya</option>\n' +
                '            </select>\n' +
                '        </div>\n' +
                '        <div class="col-md-4" style="display: none" id="form_al">\n' +
                '            <span>Keterangan Alat Bantu</span>\n' +
                '            <input class="form-control" id="ket_o2">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>\n' +
                '<div class="row jarak">\n' +
                '    <div class="form-group">\n' +
                '        <div class="col-md-offset-3 col-md-8">\n' +
                '            <textarea class="form-control" id="ket_cppt5" placeholder="Keterangan Objective"></textarea>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>\n' +
                '<hr>\n' +
                '<div class="row">\n' +
                '    <div class="col-md-5">\n' +
                '        <div class="row">\n' +
                '            <div class="form-group">\n' +
                '                <label class="col-md-4">Perilaku</label>\n' +
                '                <div class="col-md-8">\n' +
                '<div class="custom02">\n' +
                '    <input type="radio" value="Bermain|0" id="ews31" name="ews3" /><label for="ews31">Bermain</label>\n' +
                '</div>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '        <div class="row">\n' +
                '            <div class="form-group">\n' +
                '                <div class="col-md-offset-4 col-md-8">\n' +
                '<div class="custom02">\n' +
                '    <input type="radio" value="Tidur|1" id="ews32" name="ews3" /><label for="ews32">Tidur</label>\n' +
                '</div>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '        <div class="row">\n' +
                '            <div class="form-group">\n' +
                '                <div class="col-md-offset-4 col-md-8">\n' +
                '<div class="custom02">\n' +
                '    <input type="radio" value="Iritabel|2" id="ews33" name="ews3" /><label for="ews33">Iritabel</label>\n' +
                '</div>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '        <div class="row">\n' +
                '            <div class="form-group">\n' +
                '                <div class="col-md-offset-4 col-md-8">\n' +
                '<div class="custom02">\n' +
                '    <input type="radio" value="Letargi|3" id="ews34" name="ews3" /><label for="ews34">Letargi</label>\n' +
                '                                        </div>\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="col-md-7">\n' +
                '                            <div class="row">\n' +
                '                                <div class="form-group">\n' +
                '                                    <label class="col-md-4">Kardiovaskuler</label>\n' +
                '                                    <div class="col-md-8">\n' +
                '                                        <div class="custom02">\n' +
                '                                            <input type="radio" value="Merah Jambu|0" id="ews41" name="ews4" /><label for="ews41">Merah Jambu</label>\n' +
                '                                        </div>\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                            <div class="row">\n' +
                '                                <div class="form-group">\n' +
                '                                    <div class="col-md-offset-4 col-md-8">\n' +
                '                                        <div class="custom02">\n' +
                '                                            <input type="radio" value="Pucat|1" id="ews42" name="ews4" /><label for="ews42">Pucat</label>\n' +
                '                                        </div>\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                            <div class="row">\n' +
                '                                <div class="form-group">\n' +
                '                                    <div class="col-md-offset-4 col-md-8">\n' +
                '                                        <div class="custom02">\n' +
                '                                            <input type="radio" value="Abu Abu, CRT 4|2" id="ews43" name="ews4" /><label for="ews43">Abu Abu, CRT 4</label>\n' +
                '                                        </div>\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                            <div class="row">\n' +
                '                                <div class="form-group">\n' +
                '                                    <div class="col-md-offset-4 col-md-8">\n' +
                '                                        <div class="custom02">\n' +
                '                                            <input type="radio" value="Abu Abu, CRT >= 5|3" id="ews44" name="ews4" /><label for="ews44">Abu Abu, CRT >= 5</label>\n' +
                '                                        </div>\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <hr>\n' +
                '                    <div class="row jarak">\n' +
                '                        <div class="form-group">\n' +
                '                            <label class="col-md-6">Nebulisasi 15 mt / muntah persisten</label>\n' +
                '                            <div class="col-md-2">\n' +
                '                                <div class="custom02">\n' +
                '                                    <input type="radio" value="Tidak|0" id="ews61" name="ews6" /><label for="ews61">Tidak</label>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                            <div class="col-md-2">\n' +
                '                                <div class="custom02">\n' +
                '                                    <input type="radio" value="Ya|2" id="ews62" name="ews6" /><label for="ews62">Ya</label>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                    </div>';
        }else{
            set = '              <div class="alert alert-info alert-dismissible">\n' +
                '                        <p id="msg_ews">Early Warning System (EWS) Dewasa</p>\n' +
                '                    </div>\n' +
                '                    <input id="tipe_ews" value="dewasa" type="hidden">\n' +
                '                    <div class="row jarak">\n' +
                '                        <div class="form-group">\n' +
                '                            <label class="col-md-3" ><b>O</b>bjective</label>\n' +
                '                            <div class="col-md-4">\n' +
                '                                <span>Tensi </span> <small>(mmHg)</small>\n' +
                '                                <input class="form-control tensi-pasien" id="cppt5_tensi" data-inputmask="\'mask\': [\'999/999\']" data-mask="">\n' +
                '                            </div>\n' +
                '                            <div class="col-md-4">\n' +
                '                                <span>Suhu </span> <small>(&#8451)</small>\n' +
                '                                <input class="form-control suhu-pasien" id="cppt5_suhu" type="number">\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="row jarak">\n' +
                '                        <div class="form-group">\n' +
                '                            <div class="col-md-offset-3 col-md-4">\n' +
                '                                <span>Nadi </span> <small>(x/menit)</small>\n' +
                '                                <input class="form-control nadi-pasien" id="cppt5_nadi" type="number">\n' +
                '                            </div>\n' +
                '                            <div class="col-md-4">\n' +
                '                                <span>RR </span> <small>(x/menit)</small>\n' +
                '                                <input class="form-control rr-pasien" id="cppt5_rr" type="number">\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="row jarak">\n' +
                '                        <div class="form-group">\n' +
                '                            <div class="col-md-offset-3 col-md-4">\n' +
                '                                <span>Tingkat Kesadaran</span>\n' +
                '                                <select class="form-control" id="tk">\n' +
                '                                    <option value="Alert">Alert</option>\n' +
                '                                    <option value="V/P/U">V/P/U/Gelisah</option>\n' +
                '                                </select>\n' +
                '                            </div>\n' +
                '                            <div class="col-md-4">\n' +
                '                                <span>SpO2 </span> <small>(%)</small>\n' +
                '                                <input class="form-control" id="spo2" type="number">\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="row jarak">\n' +
                '                        <div class="form-group">\n' +
                '                            <div class="col-md-offset-3 col-md-4">\n' +
                '                                <span>Oksigen</span>\n' +
                '                                <select class="form-control" id="o2" onchange="setHideShow(this.value, \'form_al\')">\n' +
                '                                    <option value="Tidak">Tidak</option>\n' +
                '                                    <option value="Ya">Ya</option>\n' +
                '                                </select>\n' +
                '                            </div>\n' +
                '                            <div class="col-md-4" id="form_al" style="display: none">\n' +
                '                                <span>Keterangan Oksigen</span>\n' +
                '                                <input class="form-control" id="ket_o2">\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="row jarak">\n' +
                '                        <div class="form-group">\n' +
                '                            <div class="col-md-offset-3 col-md-8">\n' +
                '                                <textarea class="form-control" id="ket_cppt5" placeholder="Keterangan Objective"></textarea>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                    </div>';
        }
    }
    if(set != ''){
        $('#'+id).html(set);
    }
}

function saveHandOver(jenis, ket, tipe) {
    var data = "";
    var va1 = $('#tgl_ttd_hand_over').val();
    var va2 = $('#jam_ttd_hand_over').val();
    var nama1 = $('#nama_hand_over_pemberi').val();
    var nama2 = $('#nama_ttd_hand_over_penerima').val();
    var sip1 = $('#sip_hand_over_pemberi').val();
    var sip2 = $('#sip_ttd_hand_over_penerima').val();

    var v9 = document.getElementById("ttd_hand_over_pemberi");
    var v10 = document.getElementById("ttd_hand_over_penerima");

    var va9 = isCanvasBlank(v9);
    var va10 = isCanvasBlank(v10);

    if (nama1 && sip1 != '' && !va9) {
        var ttd1 = v9.toDataURL("image/png"),
            ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
        var ttd2 = v10.toDataURL("image/png"),
            ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

        if(v10){
            ttd2 = "";
        }

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1.split("-").reverse().join("-") + ' ' + va2 + ':00',
            'nama_pemberi': nama1,
            'sip_pemberi': sip1,
            'nama_penerima': nama2,
            'sip_penerima': sip2,
            'ttd_pemberi': ttd1,
            'ttd_penerima': ttd2,
            'keterangan': ket
        }

        if(!cekSession()){
            var result = JSON.stringify(data);
            $('#save_' + tipe + '_' + jenis).hide();
            $('#load_' + tipe + '_' + jenis).show();
            dwr.engine.setAsync(true);
            CatatanTerintegrasiAction.saveHandOver(result, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_' + tipe + '_' + jenis).show();
                        $('#load_' + tipe + '_' + jenis).hide();
                        $('#modal-' + tipe + '-' + jenis).modal('hide');
                        $('#warning_' + tipe + '_' + ket).show().fadeOut(5000);
                        $('#msg_' + tipe + '_' + ket).text("Berhasil menambahkan data ....");
                        $('#modal-' + tipe + '-' + jenis).scrollTop(0);
                        delRowCPPT(jenis, ket, tipe);
                        detailCPPT(jenis, ket, tipe);

                    } else {
                        $('#save_' + tipe + '_' + jenis).show();
                        $('#load_' + tipe + '_' + jenis).hide();
                        $('#warning_' + tipe + '_' + jenis).show().fadeOut(5000);
                        $('#msg_' + tipe + '_' + jenis).text(res.msg);
                        $('#modal-' + tipe + '-' + jenis).scrollTop(0);
                    }
                }
            });
        }
    } else {
        $('#save_' + tipe + '_' + jenis).show();
        $('#load_' + tipe + '_' + jenis).hide();
        $('#warning_' + tipe + '_' + jenis).show().fadeOut(5000);
        $('#msg_' + tipe + '_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-' + tipe + '-' + jenis).scrollTop(0);
    }
}