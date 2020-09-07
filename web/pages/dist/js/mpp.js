function showModalMpp(jenis, idRM, isSetIdRM) {
    if(isReadRM){
        $('.btn-hide').hide();
    }else{
        $('.btn-hide').show();
    }
    if(isSetIdRM == "Y"){
        tempidRm = idRM;
    }
    $('#modal-mpp-' + jenis).modal({show: true, backdrop: 'static'});
    setDataPasien();
}

function saveMpp(jenis, ket) {
    var data = [];
    var cek = false;
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    if ("identifikasi" == jenis) {
        var va1 = $('#idn0').val();
        var va2 = $('#idn00').val();
        var va3 = $('[name=idn1]:checked').val();
        var va4 = $('[name=idn2]:checked').val();
        var va5 = $('[name=idn3]:checked').val();
        var va6 = $('[name=idn4]:checked').val();
        var va7 = $('[name=idn5]:checked').val();
        var va8 = $('[name=idn6]:checked').val();
        var va9 = $('[name=idn7]:checked').val();
        var va10 = $('[name=idn8]:checked').val();
        if(va1 && va2 != '' && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 != undefined){
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1+ ' ' + va2,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Usia',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pasien dengan kognitif rendah (tidak sekolah, buta huruf, SD/sederajat)',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berisiko tinggi',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kasus komplek (penyakit kronis, penyakit yang memakan biaya tinggi)',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status fungsional rendah',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat gangguan mental',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat perawatan pasien',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Membantu kontinuitas pelayanan sesuai rencana pemulangan pasien (Discharge planning)',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("asesmen" == jenis) {
        var va1 = $('#ases0').val();
        var va2 = $('#ases00').val();
        var va3 = $('[name=ases1]:checked').val();
        var va4 = $('[name=ases2]:checked').val();
        var va5 = $('[name=ases3]:checked').val();
        var va6 = $('[name=ases4]:checked').val();
        var va7 = $('[name=ases5]:checked').val();
        var va8 = $('[name=ases6]:checked').val();
        var va9 = $('[name=ases7]:checked').val();
        var va10 = $('[name=ases8]:checked').val();
        var va11 = $('[name=ases9]:checked').val();
        var va12 = $('[name=ases10]:checked').val();
        var va13 = $('[name=ases11]:checked').val();
        var va14 = $('[name=ases12]:checked').val();
        if(va1 && va2 != '' && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va11 && va12 && va13 && va14 != undefined){
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1+ ' ' + va2,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Fisik, fungsional, kognitif, kemandirian',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Kesehatan',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perilaku psiko, spiritual, sosial, kultural',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesehatan mental dan kognitif',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tersedianya dukungan keluarga, kemampuan merawat dari pemberiasuhan',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Finalsial / sumber keuangan',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Asuransi / penjaminan',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat penggunaan obat NAPSA',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat trauma / kekerasan',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemahaman tentang kesehatan',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kemampuan menerima perubahan',
                'jawaban': va13,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Aspek Legal',
                'jawaban': va14,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("identifikasi_resiko" == jenis) {
        var va1 = $('#ir1').val();
        var va2 = $('#ir2').val();
        var va3 = $('#ir3').val();
        if(va1 && va2 && va3 != ''){
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1+ ' ' + va2,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Identifikasi Masalah Resiko',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("perencanaan_mpp" == jenis) {
        var va1 = $('#pm1').val();
        var va2 = $('#pm2').val();
        var va3 = $('#pm3').val();
        if(va1 && va2 && va3 != ''){
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1+ ' ' + va2,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perencanaan MPP',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'evaluasi_awal',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if("rencana_mpp" == jenis){
        var va1 = $('#rc1').val();
        var va2 = $('#rc2').val();
        var va3 = $('#rc3').val();
        if(va1 && va2 && va3 != ''){
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1+ ' ' + va2,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pelaksanaan Rencana MPP',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }
    if("monitoring_mpp" == jenis){
        var va1 = $('#mm1').val();
        var va2 = $('#mm2').val();
        var va3 = $('#mm3').val();
        if(va1 && va2 && va3 != ''){
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1+ ' ' + va2,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Monitoring',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }
    if("fasilitas_pelayanan" == jenis){
        var va1 = $('#fp1').val();
        var va2 = $('#fp2').val();
        var va3 = $('#fp3').val();
        var va4 = $('#fp1').val();
        var va5 = $('#fp2').val();
        var va6 = $('#fp3').val();
        var va7 = $('#fp2').val();
        var va8 = $('#fp3').val();
        if(va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 != ''){
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1+ ' ' + va2,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi / kolaborasi',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Second opinion',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rawat bersama / alih rawat',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Komunikasi / Edukasi',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rujukan',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lain-Lain',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }
    if("advokasi" == jenis){
        var va1 = $('#ap1').val();
        var va2 = $('#ap2').val();
        var va3 = $('[name=ap3]');
        var tVa3 = "";
        $.each(va3, function (i, item) {
            if(va3[i].checked){
                if(tVa3 != ''){
                    tVa3 = tVa3 + '|' + va3[i].value;
                }else{
                    tVa3 = va3[i].value;
                }
            }
        });

        if(va1 && va2 && tVa3 != ''){
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1+ ' ' + va2,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Advokasi pelayanan pasien',
                'jawaban': tVa3,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }
    if("hasil_pelayanan" == jenis){
        var va1 = $('#hp1').val();
        var va2 = $('#hp2').val();
        var va3 = $('#hp3').val();

        if(va1 && va2 && va3 != ''){
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1+ ' ' + va2,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hasil Pelayanan',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }
    if("terminasi" == jenis){
        var va1 = $('#tp1').val();
        var va2 = $('#tp2').val();
        var va3 = $('[name=tp3]:checked').val();
        var tVa3 = "";
        if(va3 == "Perencanaan Pulang"){
            var a = $('#tp_ket31').val();
            if(a != ''){
                tVa3 = va3 +', '+a;
            }
        }else if(va3 == "Lain-Lain"){
            var b = $('#tp_ket32').val();
            if(a != ''){
                tVa3 = va3 +', '+b;
            }
        }else{
            tVa3 = va3;
        }
        console.log(tVa3);
        if(va1 && va2 && tVa3 != ''){
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1+ ' ' + va2,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terminasi MPP',
                'jawaban': tVa3,
                'keterangan': jenis,
                'jenis': 'impementasi_mpp',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        var pasienData = JSON.stringify(dataPasien);
        $('#save_mpp_' + jenis).hide();
        $('#load_mpp_' + jenis).show();
        dwr.engine.setAsync(true);
        MppAction.saveMpp(result,pasienData, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_mpp_' + jenis).show();
                    $('#load_mpp_' + jenis).hide();
                    $('#modal-mpp-' + jenis).modal('hide');
                    $('#warning_mpp_' + ket).show().fadeOut(5000);
                    $('#msg_mpp_' + ket).text("Berhasil menambahkan data mpp...");
                    $('#modal-mpp-' + jenis).scrollTop(0);
                } else {
                    $('#save_mpp_' + jenis).show();
                    $('#load_mpp_' + jenis).hide();
                    $('#warning_mpp_' + jenis).show().fadeOut(5000);
                    $('#msg_mpp_' + jenis).text(res.msg);
                    $('#modal-mpp-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_mpp_' + jenis).show().fadeOut(5000);
        $('#msg_mpp_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-mpp-' + jenis).scrollTop(0);
    }
}

function detailFormMpp(jenis) {
    if (jenis != '') {
        var head = "";
        var body = "";
        MppAction.getListMpp(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var jwb = "";
                    if (item.jawaban != null) {
                        jwb = item.jawaban;
                    }
                    if("advokasi" == item.keterangan){
                        var li = "";
                        var isi = jwb.split("|");
                        if("Advokasi pelayanan pasien" == item.parameter){
                            $.each(isi, function (i, item) {
                                li += '<li>'+item+'</li>';
                            });
                            body += '<tr>' +
                                '<td >'+item.parameter+'</td>' +
                                '<td>'+'<ul style="margin-left: 10px">'+li+'</ul>'+'</td>' +
                                '</tr>';
                        }else{
                            body += '<tr>' +
                                '<td width="40%">'+item.parameter+'</td>' +
                                '<td>'+jwb+'</td>' +
                                '</tr>';
                        }

                    }else{
                        body += '<tr>' +
                            '<td width="40%">'+item.parameter+'</td>' +
                            '<td>'+jwb+'</td>' +
                            '</tr>';
                    }
                });
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + body + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_mpp_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_mpp_' + jenis));
            var url = contextPath+'/pages/images/minus-allnew.png';
            $('#btn_mpp_' + jenis).attr('src', url);
            $('#btn_mpp_' + jenis).attr('onclick', 'delRowMpp(\'' + jenis + '\')');
        });
    }
}

function delRowMpp(id) {
    $('#del_mpp_' + id).remove();
    var url = contextPath+'/pages/images/icons8-plus-25.png';
    $('#btn_mpp_' + id).attr('src', url);
    $('#btn_mpp_' + id).attr('onclick', 'detailFormMpp(\'' + id + '\')');
}

function showKetTp3(val){
    if(val == "Perencanaan Pulang"){
        $('#tp_ket31').show();
    }else if(val == "Lain-Lain"){
        $('#tp_ket32').show();
    }else{
        $('#tp_ket31').hide();
        $('#tp_ket32').hide();
    }
}

function conMPP(jenis, ket){
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show:true, backdrop:'static'});
    $('#save_con_rm').attr('onclick', 'delMPP(\''+jenis+'\', \''+ket+'\')');
}

function delMPP(jenis, ket) {
    $('#modal-confirm-rm').modal('hide');
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var result = JSON.stringify(dataPasien);
    startSpin('delete_'+jenis);
    dwr.engine.setAsync(true);
    MppAction.saveDelete(idDetailCheckup, jenis, result, {
        callback: function (res) {
            if (res.status == "success") {
                stopSpin('delete_'+jenis);
                $('#warning_mpp_' + ket).show().fadeOut(5000);
                $('#msg_mpp_' + ket).text("Berhasil menghapus data...");
            } else {
                stopSpin('delete_'+jenis);
                $('#warn_'+ket).show().fadeOut(5000);
                $('#msg_'+ket).text(res.msg);
            }
        }
    });
}