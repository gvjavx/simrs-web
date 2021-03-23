function showModalRJ(jenis, idRM, isSetIdRM) {
    if(isSetIdRM == "Y"){
        tempidRm = idRM;
    }
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }

    if("resiko_jatuh" == jenis){
        setResikoJatuh('set_'+jenis, umur);
    }
    if("nyeri" == jenis){
        setNyeri('set_'+jenis, umur);
    }
    $('#modal-rj-' + jenis).modal({show: true, backdrop: 'static'});
    setDataPasien();
}

function saveRJ(jenis, ket) {
    var data = [];
    var cek = false;
    var dataPasien = {
        'no_checkup' : noCheckup,
        'id_detail_checkup' : idDetailCheckup,
        'id_pasien' : idPasien,
        'id_rm' : tempidRm
    }

    if ("anamnesa_pemeriksaan_fisik" == jenis) {
        var va1 = $('#af1').val();
        var va2 = $('#af2').val();
        var va3 = $('#af3').val();
        var va4 = "";
        var va5 = $('#af5').val();
        var va6 = $('#af6').val();
        var va7 = $('#af7').val();
        var va8 = $('#af8').val();
        var va9 = $('#af9').val();
        var va10 = $('#af10').val();
        var va11 = $('#af11').val();
        var va12 = "";
        var va4d = $('[name=af4]:checked').val();
        if(va4d != undefined){
            if(va4d == "Ada"){
                var ket = $('#ket_af4').val();
                if(ket != '') {
                    va4 = va4d;
                }
            }else{
                va4 = va4d;
            }
        }
        var va12d = $('[name=af12]:checked').val();
        if(va12d != undefined){
            if(va12d == "Lain-Lain"){
                var ket = $('#ket_af12').val();
                if(ket != ''){
                    va12 = ket;
                }
            }else{
                va12 = va12d;
            }
        }
        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 && va11 && va12 != '') {
            data.push({
                'parameter': 'Tanggal & Jam',
                'jawaban': va1+' '+va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Asal Pasien',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Anamnese',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'pernyataan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penggunaan Obat',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Alergi',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Fisik',
                'jawaban': '',
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'pernyataan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keadaan Umum',
                'jawaban': 'Tinggi Badan '+va6+' cm, Berat Badan '+va7+' kg',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanda Vital',
                'jawaban': 'Tensi '+replaceUnderLine(va8)+' mmHg, Suhu '+va9+' ˚C, RR '+va10+' x/menit, Nadi '+va11+' x/menit',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hambatan',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("psiko_sosial" == jenis) {
        var va1 = $('#ps1').val();
        var va2 = $('#ps2').val();
        var va3 = $('#ps3').val();
        var va4 = $('#ps4').val();
        var va5 = $('#ps5').val();
        var va6 = $('#ps6').val();
        if (va1 && va2 && va3 && va4 && va5 && va6 != '') {
            data.push({
                'parameter': 'Persepsi Klien terhadap penyakitnya',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Asal Pasien',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ekspresi klien terhadap penyakitnya',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Gangguan konsep diri',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Reaksi anak interaksi (khusus pasien anak)',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pendidikan',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'pernyataan',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pekerjaan',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }
    if ("skrining_gizi" == jenis) {
        var va1 = $('[name=gz1]:checked').val();
        var va2 = $('[name=gz2]:checked').val();
        var va3 = $('[name=gz3]:checked').val();
        var va4 = undefined;
        var va4d = $('[name=gz4]:checked').val();
        if(va4d != undefined){
            var a = $('#ket_gz4').val();
            if(va4d == "Lain-Lain"){
                if(a != ''){
                    va4 = a;
                }
            }else{
                va4 = va4d;
            }
        }

        if (va1 && va2 && va3 && va4 != undefined) {
            var isi1 = va1.split("|")[0];
            var isi2 = va2.split("|")[0];
            var isi3 = va3.split("|")[0];

            var skor1 = va1.split("|")[1];
            var skor2 = va2.split("|")[1];
            var skor3 = va3.split("|")[1];

            data.push({
                'parameter': '1. Apakah pasien mengalami penuruanan / peningkatan BB yang tidak di inginkan dalam 6 bulan terakhir ?',
                'jawaban': isi1,
                'skor': skor1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Apakah asupan makan berkurang karena tidak nafsu makan ?',
                'jawaban': isi2,
                'skor': skor2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Pasien dengan diagnosa khusus / kondisi khusus ? Penyakit ',
                'jawaban': isi3,
                'skor': skor3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            var total = (parseInt(skor1) + parseInt(skor2) + parseInt(skor3));
            console.log(total);
            data.push({
                'parameter': 'Total',
                'jawaban': '',
                'skor': total.toString(),
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'total',
                'id_detail_checkup': idDetailCheckup
            });

            var kesimpulan = "Beresiko sedang, ulangi skrining setiap 7 hari";
            if (total >= 2) {
                kesimpulan = "Beresiko tinggi, lakukan asuhan gizi terstandart";
            }
            data.push({
                'parameter': 'Kesimpulan',
                'jawaban': kesimpulan,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'kesimpulan',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            console.log(data);
        }
    }

    if("resiko_jatuh" == jenis){
        var tgl  = $('#ps01').val();
        var jam  = $('#ps02').val();
        data.push({
            'parameter': 'Tanggal Jam',
            'jawaban': tgl+' '+jam,
            'keterangan': jenis,
            'jenis': ket,
            'id_detail_checkup': idDetailCheckup
        });
        var resikoJatuh = $('.resiko_jatuh');
        var jenisResiko = $('#jenis_resiko').val();
        var totalSkor = "";
        if(resikoJatuh.length > 0){
            $.each(resikoJatuh, function (i, item) {
                var label = $('#label_resiko_jatuh'+i).text();
                var resiko = $('[name=resiko_jatuh'+i+']:checked').val();
                if(resiko != undefined){
                    var isi = resiko.split("|")[0];
                    var skor = resiko.split("|")[1];
                    data.push({
                        'parameter': label,
                        'jawaban': isi,
                        'skor': skor,
                        'keterangan': jenis,
                        'jenis': ket,
                        'id_detail_checkup': idDetailCheckup
                    });
                    if(totalSkor != ''){
                        totalSkor = parseInt(totalSkor) + parseInt(skor);
                    }else{
                        totalSkor = parseInt(skor);
                    }
                }
            });

            if(totalSkor != ''){
                data.push({
                    'parameter': 'Total Skor',
                    'jawaban': ''+totalSkor,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'total',
                    'id_detail_checkup': idDetailCheckup
                });

                var kesimpulan = "";

                if(jenisResiko == "humpty_dumpty"){
                    if(parseInt(totalSkor) >= 7 && parseInt(totalSkor) <= 11){
                        kesimpulan = "Rendah";
                    }else if (parseInt(totalSkor) >= 12) {
                        kesimpulan = "Tinggi";
                    }
                }else if(jenisResiko == "skala_morse"){
                    if(parseInt(totalSkor) >= 0 && parseInt(totalSkor) <= 24){
                        kesimpulan = "Rendah";
                    }else if (parseInt(totalSkor) >= 25 && parseInt(totalSkor) <= 44) {
                        kesimpulan = "Sedang";
                    }else if (parseInt(totalSkor) >= 45) {
                        kesimpulan = "Tinggi";
                    }
                }else if(jenisResiko == "geriatri"){
                    if(parseInt(totalSkor) >= 0 && parseInt(totalSkor) <= 5){
                        kesimpulan = "Rendah";
                    }else if (parseInt(totalSkor) >= 6 && parseInt(totalSkor) <= 16) {
                        kesimpulan = "Sedang";
                    }else if (parseInt(totalSkor) >= 17) {
                        kesimpulan = "Tinggi";
                    }
                }
                data.push({
                    'parameter': 'Resiko Jatuh',
                    'jawaban': kesimpulan,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'kesimpulan',
                    'id_detail_checkup': idDetailCheckup
                });
            }
        }
        if(resikoJatuh.length == data.length - 3){
            cek = true;
        }
    }

    if("nyeri" == jenis){
        var va1 = $('[name=radio_nyeri_keluhan]:checked').val();
        var va2 = $('#y_lokasi').val();
        var va3 = $('[name=radio_nyeri_jenis]:checked').val();
        var va4 = $('#y_inten').val();
        var jen = $('#temp_jenis').val();
        var nyeri = "";
        var tipe = "";
        var namaTerang = $('#nama_terang').val();
        var nip = $('#nip_perawat').val();
        var ttd = document.getElementById('ttdPerawat');
        if(jen == "emoji"){
            nyeri = document.getElementById('choice_emoji');
            tipe = "Wong Baker Pain Scale";
        }else{
            nyeri = document.getElementById('area_nyeri');
            tipe = "Nomeric Rating Scale";
        }
        var cvs1 = isCanvasBlank(nyeri);
        var cvs2 = isCanvasBlank(ttd);

        if(va1 && va3 != undefined && va2 && va4 && namaTerang && nip != '' && !cvs2){
            data.push({
                'parameter': 'Apakah terdapat keluhan nyeri',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Intensitas',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });

            if("Ya" == va1){
                var canv1 = nyeri.toDataURL("image/png"),
                    canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
                data.push({
                    'parameter': tipe,
                    'jawaban': canv1,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'gambar',
                    'id_detail_checkup': idDetailCheckup
                });
            }

            var canv2 = ttd.toDataURL("image/png"),
                canv2 = canv2.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': 'TTD Perawat',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': namaTerang,
                'sip': nip,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            $('#save_rj_' + jenis).hide();
            $('#load_rj_' + jenis).show();
            dwr.engine.setAsync(true);
            KeperawatanRawatJalanAction.saveAsesmenRawat(result, pasienData, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_rj_' + jenis).show();
                        $('#load_rj_' + jenis).hide();
                        $('#modal-rj-' + jenis).modal('hide');
                        $('#warning_rj_' + ket).show().fadeOut(5000);
                        $('#msg_rj_' + ket).text("Berhasil menambahkan data...");
                        $('#modal-rj-' + jenis).scrollTop(0);
                        getListRekamMedis('rawat_jalan', tipePelayanan, idDetailCheckup);
                        delRowRJ(jenis);
                        detailRJ(jenis);
                    } else {
                        $('#save_rj_' + jenis).show();
                        $('#load_rj_' + jenis).hide();
                        $('#warning_rj_' + jenis).show().fadeOut(5000);
                        $('#msg_rj_' + jenis).text(res.msg);
                        $('#modal-rj-' + jenis).scrollTop(0);
                    }
                }
            });
        }
    } else {
        $('#warning_rj_' + jenis).show().fadeOut(5000);
        $('#msg_rj_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-rj-' + jenis).scrollTop(0);
    }
}

function detailRJ(jenis) {
    if(!cekSession()){
        if (jenis != '') {
            var head = "";
            var body = "";
            var totalSkor = 0;
            var first = "";
            var last = "";
            var tgl = "";
            var cekData = false;
            KeperawatanRawatJalanAction.getListAsesmenRawat(idDetailCheckup, jenis, function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        var jwb = "";
                        if (item.jawaban != null) {
                            jwb = item.jawaban;
                        }
                        if ("ttd" == item.tipe) {
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + '<img src="' + jwb + '" style="width: 100px">' +
                                '<p>'+item.namaTerang+'</p>' +
                                '<p>'+item.sip+'</p>' +
                                '</td>' +
                                '</tr>';
                        }else if("gambar" == item.tipe){
                            if("Wong Baker Pain Scale" == item.parameter){
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + jwb + '" style="width: 20%; height: 100px">' + '</td>' +
                                    '</tr>';
                            }else{
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + '<img src="' + jwb + '" style="width: 100%">' + '</td>' +
                                    '</tr>';
                            }
                        }else if("resiko_jatuh" == jenis || "skrining_gizi" == jenis){
                            if("total" == item.tipe){
                                body += '<tr>' +
                                    '<td width="40%" colspan="2">' + item.parameter + '</td>' +
                                    '<td>' + cekItemIsNull(item.score) + '</td>' +
                                    '</tr>';
                            }else if("kesimpulan" == item.tipe){
                                body += '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                                    '<td width="40%" colspan="2">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '</td>' +
                                    '</tr>';
                            }else{
                                body += '<tr>' +
                                    '<td width="40%">' + item.parameter + '</td>' +
                                    '<td>' + jwb + '</td>' +
                                    '<td>' + cekItemIsNull(item.score) + '</td>' +
                                    '</tr>';
                            }
                        }else {
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + jwb + '</td>' +
                                '</tr>';
                        }
                        cekData = true;
                        tgl = item.createdDate;
                    });
                } else {
                    body = '<tr>' +
                        '<td>Data belum ada</td>' +
                        '</tr>';
                }

                if(cekData){
                    if("skrining_gizi" == jenis || "resiko_jatuh" == jenis){
                        head = '<tr style="font-weight: bold">' +
                            '<td>Parameter</td>' +
                            '<td>Jawaban</td>' +
                            '<td>Score</td>' +
                            '</tr>'
                    }
                }

                var table = '<table style="font-size: 12px" class="table table-bordered">' +
                    '<thead>' + head + '</thead>' +
                    '<tbody>' + first + body + last + '</tbody>' +
                    '</table>';

                var newRow = $('<tr id="del_rj_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                newRow.insertAfter($('table').find('#row_rj_' + jenis));
                var url = contextPath + '/pages/images/minus-allnew.png';
                $('#btn_rj_' + jenis).attr('src', url);
                $('#btn_rj_' + jenis).attr('onclick', 'delRowRJ(\'' + jenis + '\')');
            });
        }
    }
}

function delRowRJ(id) {
    $('#del_rj_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_rj_' + id).attr('src', url);
    $('#btn_rj_' + id).attr('onclick', 'detailRJ(\'' + id + '\')');
}

function showKetRJ(val, ket) {
    if (val == "Ada" || val == "Lain-Lain") {
        $('#form_'+ket).show();
    } else {
        $('#form_'+ket).hide();
    }
}

function conKepRJ(jenis, ket){
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show:true, backdrop:'static'});
    $('#save_con_rm').attr('onclick', 'delKepRJ(\''+jenis+'\', \''+ket+'\')');
}

function delKepRJ(jenis, ket) {
    $('#modal-confirm-rm').modal('hide');
    if(!cekSession()){
        var dataPasien = {
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'id_pasien': idPasien,
            'id_rm': tempidRm
        }
        var result = JSON.stringify(dataPasien);
        startSpin('del_'+jenis);
        dwr.engine.setAsync(true);
        KeperawatanRawatJalanAction.saveDelete(idDetailCheckup, jenis, result, {
            callback: function (res) {
                if (res.status == "success") {
                    stopSpin('del_'+jenis);
                    $('#warning_rj_' + ket).show().fadeOut(5000);
                    $('#msg_rj_' + ket).text("Berhasil menghapus data...");
                } else {
                    stopSpin('del_'+jenis);
                    $('#modal_warning').show().fadeOut(5000);
                    $('#msg_warning').text(res.msg);
                }
                delRowRJ(jenis);
                detailRJ(jenis);
            }
        });
    }
}