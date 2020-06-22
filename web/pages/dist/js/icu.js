function showModalICU(jenis) {
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }
    setDataPasien();
    if("hemodinamika" == jenis){
        listHemodinamika(jenis);
    }
    if("respirasi" == jenis){
        listRespirasi(jenis);
    }

    if("respirasi_icu" == jenis){
        setHeadRespirasi();
    }

    if( "keseimbangan_icu" == jenis ||
        "injeksi_icu" == jenis ||
        "oral_icu" == jenis ||
        "lain_icu" == jenis ||
        "intakea_icu" == jenis ||
        "output_icu" == jenis){

        setInputan(jenis);
    }
    if("keseimbangan" == jenis){
        listInputan(jenis);
    }

    if("checklist_kriteria" == jenis){
        setInputan(jenis);
    }
    $('#modal-icu-' + jenis).modal({show: true, backdrop: 'static'});
}

function setHeadRespirasi(){
    RespirasiAction.getListDetail(idDetailCheckup, "respirasi", function (res) {
        if (res.length > 0) {
            var valueRes = res[0];
            $('#id13').val(valueRes.jenisNkRmNrm).trigger('change').attr('disabled','disabled');
            $('#id14').val(valueRes.jenisTPieceJRise).trigger('change').attr('disabled','disabled');
            $('#id17').val(valueRes.jenisPeepCpapEt).trigger('change').attr('disabled','disabled');
            $('#id22').val(valueRes.jenisPSupportPAsb).trigger('change').attr('disabled','disabled');
            $('#id24').val(valueRes.jenisPInsPCon).trigger('change').attr('disabled','disabled');
            $('#id29').val(valueRes.jenisFioKon).trigger('change').attr('disabled','disabled');
            $('#id31').val(valueRes.jenisUkuranKedalamaanEtt).trigger('change').attr('disabled','disabled');
        }
    });
}

function saveICU(jenis, ket) {
    var data = [];
    var cek = false;

    if ("identitas" == jenis) {

        var va1 = $('#id1').val();
        var va2 = $('#id2').val();
        var va3 = $('#id3').val();
        var va4 = $('#id4').val();
        var va5 = $('#id5').val();
        var va6 = $('#id6').val();
        var va7 = $('#id7').val();
        var va8 = $('#id8').val();
        var va9 = $('#id9').val();
        var va10 = $('#id10').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 && va9 && va10 != '') {

            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1 + ' ' + va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jaminann',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Asal Masuk',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tgl Masuk',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Hari Perawatan',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan/ Tinggi Badan',
                'jawaban': va7 + 'kg / ' + va8 + ' cm',
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'DPJP ICU',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perawat Jaga',
                'jawaban': va10,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("alat_infasive" == jenis) {

        var va1 = $('#ai1').val();
        var va2 = $('#ai2').val();
        var va3 = $('#ai3').val();
        var va4 = "";
        var v4 = $('#ai4').val();
        if (v4.length > 0) {
            $.each(v4, function (i, item) {
                if (va4 != '') {
                    va4 = va4 + '|' + item;
                } else {
                    va4 = item;
                }
            });
        }
        if (va1 && va2 && va3 && va4 != '') {

            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va1 + ' ' + va2,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alat',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("resiko_jatuh" == jenis) {

        var jatuh1 = $('[name=radio_aud_jatuh1]:checked').val();
        var jatuh2 = $('[name=radio_aud_jatuh2]:checked').val();
        var jatuh3 = $('[name=radio_aud_jatuh3]:checked').val();
        var jatuh4 = $('[name=radio_aud_jatuh4]:checked').val();
        var jatuh5 = $('[name=radio_aud_jatuh5]:checked').val();
        var jatuh6 = $('[name=radio_aud_jatuh6]:checked').val();

        if (jatuh1 && jatuh2 && jatuh3 && jatuh4 && jatuh5 != undefined) {

            var isi1 = jatuh1.split("|")[0];
            var isi2 = jatuh2.split("|")[0];
            var isi3 = jatuh3.split("|")[0];
            var isi4 = jatuh4.split("|")[0];
            var isi5 = jatuh5.split("|")[0];
            var isi6 = jatuh6.split("|")[0];

            var skor1 = jatuh1.split("|")[1];
            var skor2 = jatuh2.split("|")[1];
            var skor3 = jatuh3.split("|")[1];
            var skor4 = jatuh4.split("|")[1];
            var skor5 = jatuh5.split("|")[1];
            var skor6 = jatuh6.split("|")[1];

            data.push({
                'parameter': 'Riwayat Jatuh',
                'jawaban': isi1,
                'keterangan': jenis,
                'skor': skor1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Diagnosis Sekunder',
                'jawaban': isi2,
                'keterangan': jenis,
                'skor': skor2,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alat Bantu',
                'jawaban': isi3,
                'keterangan': jenis,
                'skor': skor3,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi Intravena',
                'jawaban': isi4,
                'keterangan': jenis,
                'skor': skor4,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Gaya Berjalan',
                'jawaban': isi5,
                'keterangan': jenis,
                'skor': skor5,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status Normal',
                'jawaban': isi6,
                'keterangan': jenis,
                'skor': skor6,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("decobitus" == jenis) {

        var va = $('[name=db]');
        var va1 = "";
        $.each(va, function (i, item) {
            if (item.checked) {
                if (va1 != '') {
                    va1 = va1 + '|' + item.value;
                } else {
                    va1 = item.value;
                }
            }
        });

        if (va1 != '') {

            data.push({
                'parameter': 'Derajat Decubitus',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("nyeri" == jenis) {

        var nyeri = $('[name=radio_aud_nyeri]:checked').val();
        var skala = $('[name=radio_aud_skala]:checked').val();
        var lokasi = $('#yer_lokasi').val();
        var skal = $('#skala_nyeri').val();
        var canvasArea = document.getElementById('choice_emoji');
        var cvs = isCanvasBlank(canvasArea);

        if (nyeri && skala != undefined && lokasi && skal != '') {

            data.push({
                'parameter': 'Apakah terdapat keluhan nyeri',
                'jawaban': nyeri,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban': lokasi,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis',
                'jawaban': skala,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skala',
                'jawaban': skal,
                'keterangan': jenis,
                'jenis': ket,
                'id_detail_checkup': idDetailCheckup
            });
            if (!cvs) {
                var canv = canvasArea.toDataURL("image/png"),
                    canv = canv.replace(/^data:image\/(png|jpg);base64,/, "");
                data.push({
                    'parameter': 'Scala Paint Nyeri',
                    'jawaban': canv,
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'gambar',
                    'id_detail_checkup': idDetailCheckup
                });
            }
            cek = true;
        }
    }

    if ("gcs" == jenis) {

        var gcs1 = $('#gc1').val();
        var gcs2 = $('#gc2').val();
        var gcs3 = $('#gc3').val();

        if (gcs1 && gcs2 && gcs3 != '') {

            var isi1 = gcs1.split("|")[0];
            var isi2 = gcs2.split("|")[0];
            var isi3 = gcs3.split("|")[0];

            var skor1 = gcs1.split("|")[1];
            var skor2 = gcs2.split("|")[1];
            var skor3 = gcs3.split("|")[1];

            data.push({
                'parameter': 'E = Eye (membuka mata)',
                'jawaban': isi1,
                'keterangan': jenis,
                'jenis': ket,
                'skor': skor1,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'V = Verbal (komunikasi)',
                'jawaban': isi2,
                'keterangan': jenis,
                'jenis': ket,
                'skor': skor2,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'M = Motorik (gerakan ekstimitas atas)',
                'jawaban': isi3,
                'keterangan': jenis,
                'jenis': ket,
                'skor': skor3,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);
        AsesmenIcuAction.save(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#modal-icu-' + jenis).modal('hide');
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                    $('#modal-icu-' + jenis).scrollTop(0);
                } else {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#warning_icu_' + jenis).show().fadeOut(5000);
                    $('#msg_icu_' + jenis).text(res.msg);
                    $('#modal-icu-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-icu-' + jenis).scrollTop(0);
    }
}

function detailICU(jenis) {
    if (jenis != '') {
        var head = "";
        var body = "";
        var totalSkor = 0;
        var cekSkor = false;
        var first = "";
        var last = "";
        var tgl = "";
        var cekData = false;
        var rowTotal = "";
        var kesimpulan = "";
        var isKesimpulan = false;

        AsesmenIcuAction.getListDetail(idDetailCheckup, jenis, function (res) {

            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var jwb = "";
                    if (item.jawaban != '') {
                        jwb = item.jawaban;
                    }

                    if ("Alat" == item.parameter) {
                        var val = jwb.split("|");
                        var li = "";
                        $.each(val, function (i, item) {
                            li += '<li>' + item + '</li>'
                        });
                        body += '<tr>' +
                            '<td width="40%">' + item.parameter + '</td>' +
                            '<td>' + '<ul style="margin-left: 15px">' + li + '</ul>' + '</td>' +
                            '</tr>';

                    } else if (item.score != null) {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + item.jawaban + '</td>' +
                            '<td width="10%" align="center">' + item.score + '</td>' +
                            '</tr>';
                        totalSkor = parseInt(totalSkor) + parseInt(item.score);
                        cekSkor = true;
                    } else if ("gambar" == item.tipe) {
                        body += '<tr>' +
                            '<td width="40%">' + item.parameter + '</td>' +
                            '<td>' + '<img src="' + item.jawaban + '" style="height: 80px">' + '</td>' +
                            '</tr>';
                    } else {
                        body += '<tr>' +
                            '<td width="40%">' + item.parameter + '</td>' +
                            '<td>' + jwb + '</td>' +
                            '</tr>';
                    }
                    cekData = true;
                    tgl = item.createdDate;
                    isKesimpulan = true;
                });
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }

            if (cekSkor) {
                first = '<tr style="font-weight: bold"><td>Parameter</td><td>Jawaban</td><td align="center">Skor</td></tr>';
                last = '<tr style="font-weight: bold"><td colspan="2">Total</td><td align="center">' + totalSkor + '</td></tr>'

                if ("resiko_jatuh" == jenis) {
                    var jwb = "";
                    if (totalSkor >= 0 && totalSkor <= 24) {
                        jwb = "Rendah";
                    } else if (totalSkor >= 25 && totalSkor <= 44) {
                        jwb = "Sedang";
                    } else if (totalSkor >= 45) {
                        jwb = "Tinggi";
                    }

                    if (isKesimpulan) {
                        kesimpulan = '<tr style="font-weight: bold" bgcolor="#ffebcd"><td colspan="2">Resiko Jatuh</td><td align="center">' + jwb + '</td></tr>';
                    }
                }
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + kesimpulan + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_icu_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_icu_' + jenis).attr('src', url);
            $('#btn_icu_' + jenis).attr('onclick', 'delRowICU(\'' + jenis + '\')');
        });
    }
}

function delRowICU(id) {
    $('#del_icu_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_icu_' + id).attr('src', url);
    $('#btn_icu_' + id).attr('onclick', 'detailICU(\'' + id + '\')');
}

function saveHemodinamika(jenis, ket) {

    var va1 = $('#id1').val();
    var va2 = $('#id2').val();
    var va3 = $('#id3').val();
    var va4 = $('#id4').val();
    var va5 = $('#id5').val();
    var va6 = $('#id6').val();
    var va7 = $('#id7').val();
    var va8 = $('#id8').val();
    var va9 = $('#id9').val();
    var va10 = $('#id10').val();
    var data = "";

    if (va1 != '') {
        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1,
            'tensi': va2,
            'bp': va3,
            'hi': va4,
            'rr': va5,
            'ekg': va6,
            'icp': va7,
            'ibp': va8,
            'cvp': va9,
            'map': va10,
            'keterangan': ket
        };
        var result = JSON.stringify(data);
        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);
        HemodinamikaAction.save(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#modal-icu-' + jenis).modal('hide');
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                    $('#modal-icu-' + jenis).scrollTop(0);
                    listHemodinamika(ket);
                } else {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#warning_icu_' + jenis).show().fadeOut(5000);
                    $('#msg_icu_' + jenis).text(res.msg);
                    $('#modal-icu-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-icu-' + jenis).scrollTop(0);
    }

}

function showChartHemodinamika(jenis, tgl) {
    $('#tanggal_data').html(converterDate(new Date(tgl)));
    $('#modal-icu-chart_' + jenis).modal({show: true, backdrop: 'static'});
    HemodinamikaAction.getListDetail(idDetailCheckup, jenis, tgl, function (res) {
        if (res.length > 0) {
            var dataArray   = [];
            var thEkg       = [];
            var tdEkg       = [];
            var temJam      = "";
            var tempEkg     = 0;

            $.each(res, function (i, item) {
                var ekg = 0;
                if(item.ekg != null){
                    ekg = item.ekg;
                }

                dataArray.push({
                    y: item.waktu,
                    a: item.tensi,
                    b: item.bp,
                    c: item.hi,
                    d: item.rr,
                    e: item.icp,
                    f: item.ibp,
                    g: item.cvp,
                    h: item.map
                });

                var jam = item.waktu.split(":")[0];

                if(temJam != jam){
                    temJam = jam;
                    thEkg.push({'jam':jam});
                }

                var tempTgl = "";
                var btn = "";

                if(i == 0){
                    tempEkg = parseInt(tempEkg)+parseInt(ekg);
                }else{
                    var jm = res[i - 1]["waktu"];
                    var jamB = jm.split(":")[0];

                    if(jam == jamB){
                        tempEkg = parseInt(tempEkg)+parseInt(ekg);
                    }else{
                        tdEkg.push({'ekg':tempEkg});
                        tempEkg = ekg;
                    }
                }

                if(i == res.length - 1){
                    tdEkg.push({'ekg':tempEkg});
                }

            });

            $('#modal-icu-chart_' + jenis).on('shown.bs.modal', function (event) {
                $('#line-chart_hemodinamika').empty();
                var line = new Morris.Line({
                    element: 'line-chart_hemodinamika',
                    resize: true,
                    data: dataArray,
                    xkey: 'y',
                    ykeys: ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'],
                    labels: ['Tensi', 'BP', 'HI', 'RR', 'ICP', 'IBP', 'CVP', 'MAP'],
                    lineColors: ['#ff0000', '#0000ff', '#00cc00', '#ff9933', '#cc6600', '#ffff66', '#cc6699', '#666633'],
                    hideHover: 'auto',
                    parseTime: false,
                    lineWidth: 1
                });
            });

            var jamEkg = "<td>Jam</td>";
            var valEkg = "<td>EKG</td>";
            if(thEkg.length > 0){
                $.each(thEkg, function (i, item) {
                    jamEkg += '<td>'+item.jam+'</td>';
                });
            }

            if(tdEkg.length > 0){
                $.each(tdEkg, function (i, item) {
                    valEkg += '<td>'+item.ekg+'</td>';
                });
            }
            $('#body_ekg').html('<tr style="font-weight: bold">'+jamEkg+'</tr><tr>'+valEkg+'</tr>');
        }
    });
}

function listHemodinamika(jenis) {
    HemodinamikaAction.getListDetail(idDetailCheckup, jenis, null, function (res) {
        if (res.length > 0) {
            var body = "";
            $.each(res, function (i, item) {
                var tanggal = converterDate(new Date(item.createdDate));
                var ketTgl = converterDateYmd(new Date(item.tanggal));

                var tempTgl = "";
                var btn = "";

                if(i == 0){
                    tempTgl = tanggal;
                    btn = '<i class="fa fa-line-chart" style="cursor: pointer; color: #1ab7ea" onclick="showChartHemodinamika(\''+jenis+'\', \''+ketTgl+'\')"></i>';
                }else{
                    var tgl = res[i - 1]["createdDate"];
                    var tglB = converterDate(new Date(tgl));
                    if(tanggal == tglB){
                        tempTgl = "";
                    }else{
                        tempTgl = tanggal;
                        btn = '<i class="fa fa-line-chart" style="cursor: pointer; color: #1ab7ea" onclick="showChartHemodinamika(\''+jenis+'\', \''+ketTgl+'\')"></i>';
                    }
                }

                body += '<tr>' +
                    '<td>'+btn+"&nbsp;&nbsp;"+tempTgl+'</td>'+
                    '<td>'+cekItemIsNull(item.waktu)+'</td>'+
                    '<td>'+cekItemIsNull(item.tensi)+'</td>'+
                    '<td>'+cekItemIsNull(item.bp)+'</td>'+
                    '<td>'+cekItemIsNull(item.hi)+'</td>'+
                    '<td>'+cekItemIsNull(item.rr)+'</td>'+
                    '<td>'+cekItemIsNull(item.ekg)+'</td>'+
                    '<td>'+cekItemIsNull(item.icp)+'</td>'+
                    '<td>'+cekItemIsNull(item.ibp)+'</td>'+
                    '<td>'+cekItemIsNull(item.cvp)+'</td>'+
                    '<td>'+cekItemIsNull(item.map)+'</td>'+
                    '</tr>';
            });
            $('#body_hemodinamika').html(body);
        }
    });
}

function saveRespirasi(jenis, ket) {

    var va1 = $('#id1').val();
    var va2 = $('#id2').val();
    var va3 = $('#id3').val();
    var va4 = $('#id4').val();
    var va5 = $('#id5').val();
    var va6 = $('#id6').val();
    var va7 = $('#id7').val();
    var va8 = $('#id8').val();
    var va9 = $('#id9').val();
    var va10 = $('#id10').val();
    var va11 = $('#id11').val();
    var va12 = $('#id12').val();
    var va13 = $('#id13').val();
    var va14 = $('#id14').val();
    var va014 = $('#id014').val();
    var va15 = $('#id15').val();
    var va16 = $('#id16').val();
    var va17 = $('#id17').val();
    var va18 = $('#id18').val();
    var va19 = $('#id19').val();
    var va20 = $('#id20').val();
    var va21 = $('#id21').val();
    var va22 = $('#id22').val();
    var va23 = $('#id23').val();
    var va24 = $('#id24').val();
    var va25 = $('#id25').val();
    var va26 = $('#id26').val();
    var va27 = $('#id27').val();
    var va28 = $('#id28').val();
    var va29 = $('#id29').val();
    var va30 = $('#id30').val();
    var va31 = $('#id31').val();
    var va32 = $('#id32').val();
    var va33 = $('#id33').val();
    var va34 = $('#id34').val();

    var data = "";

    if (va1 != '') {
        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1,
            'gcs': va2+'|'+va3+'|'+va4,
            'diameter_pupil': va5+'|'+va6,
            'reflek_cahaya': va7+'|'+va8,
            'tk': va9+'|'+va10,
            'kk': va11+'|'+va12,
            'jenis_nk_rm_nrm': va13,
            'nk_rm_nrm': va014,
            'jenis_t_piece_j_rise': va14,
            't_piece_j_rise': va15,
            'tipe_ventilasi': va16,
            'jenis_peep_cpap_et': va17,
            'peep_cpap_et': va18,
            'frekwensi': va19,
            'tv': va20,
            'mv': va21,
            'jenis_p_support_p_asb': va22,
            'p_support_p_asb': va23,
            'jenis_p_ins_p_con': va24,
            'p_ins_p_con': va25,
            'triger': va26,
            'ins_time': va27,
            'flow': va28,
            'jenis_fio_kon': va29,
            'fio_kon': va30,
            'jenis_ukuran_kedalamaan_ett': va31,
            'ukuran_kedalamaan_ett': va32,
            'spo': va33,
            'secret': va34,
            'keterangan': ket
        };
        var result = JSON.stringify(data);
        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);
        RespirasiAction.save(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#modal-icu-' + jenis).modal('hide');
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                    $('#modal-icu-' + jenis).scrollTop(0);
                    listRespirasi(ket);
                } else {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#warning_icu_' + jenis).show().fadeOut(5000);
                    $('#msg_icu_' + jenis).text(res.msg);
                    $('#modal-icu-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-icu-' + jenis).scrollTop(0);
    }
}

function listRespirasi(jenis) {
    RespirasiAction.getListDetail(idDetailCheckup, jenis, function (res) {
        if (res.length > 0) {
            var body = "";
            var valueRes = res[0];
            $('#head1').text(valueRes.jenisNkRmNrm);
            $('#head2').text(valueRes.jenisTPieceJRise.substring(0,2));
            $('#head3').text(valueRes.jenisPeepCpapEt.substring(0,2));
            $('#head4').text(valueRes.jenisPSupportPAsb.substring(0,2));
            $('#head5').text(valueRes.jenisPInsPCon.substring(0,2));
            $('#head6').text(valueRes.jenisFioKon.substring(0,2).toUpperCase());
            $('#head7').text(valueRes.jenisUkuranKedalamaanEtt.substring(0,2).toUpperCase());

            $.each(res, function (i, item) {
                var tanggal = converterDate(new Date(item.createdDate));
                var tempTgl = "";

                if(i == 0){
                    tempTgl = tanggal;
                }else{
                    var tgl = res[i - 1]["createdDate"];
                    var tglB = converterDate(new Date(tgl));
                    if(tanggal == tglB){
                        tempTgl = "";
                    }else{
                        tempTgl = tanggal;
                    }
                }

                var gcs = item.gcs.split("|");
                var dp = item.diameterPupil.split("|");
                var rc = item.reflekCahaya.split("|");
                var tk = item.tk.split("|");
                var kk = item.kk.split("|");

                body += '<tr>' +
                    '<td>'+tempTgl+'<span class="pull-right">'+cekItemIsNull(item.waktu)+'</span>'+'</td>'+
                    '<td>'+gcs[0]+'</td>'+
                    '<td>'+gcs[1]+'</td>'+
                    '<td>'+gcs[2]+'</td>'+
                    '<td>'+dp[0]+'</td>'+
                    '<td>'+dp[1]+'</td>'+
                    '<td>'+rc[0]+'</td>'+
                    '<td>'+rc[1]+'</td>'+
                    '<td>'+tk[0]+'</td>'+
                    '<td>'+tk[1]+'</td>'+
                    '<td>'+kk[0]+'</td>'+
                    '<td>'+kk[1]+'</td>'+
                    '<td>'+cekItemIsNull(item.nkRmNrm)+'</td>'+
                    '<td>'+cekItemIsNull(item.tPieceJRise)+'</td>'+
                    '<td>'+cekItemIsNull(item.tipeVentilasi)+'</td>'+
                    '<td>'+cekItemIsNull(item.peepCpapEt)+'</td>'+
                    '<td>'+cekItemIsNull(item.frekwensi)+'</td>'+
                    '<td>'+cekItemIsNull(item.tv)+'</td>'+
                    '<td>'+cekItemIsNull(item.mv)+'</td>'+
                    '<td>'+cekItemIsNull(item.pSupportPAsb)+'</td>'+
                    '<td>'+cekItemIsNull(item.pInsPCon)+'</td>'+
                    '<td>'+cekItemIsNull(item.triger)+'</td>'+
                    '<td>'+cekItemIsNull(item.insTime)+'</td>'+
                    '<td>'+cekItemIsNull(item.flow)+'</td>'+
                    '<td>'+cekItemIsNull(item.fioKon)+'</td>'+
                    '<td>'+cekItemIsNull(item.ukuranKedalamaanEtt)+'</td>'+
                    '<td>'+cekItemIsNull(item.spo)+'</td>'+
                    '<td>'+cekItemIsNull(item.secret)+'</td>'+
                    '</tr>';
            });
            $('#body_respirasi').html(body);
        }
    });
}

function setInputan(jenis){

    var resus = "";
    var darah = "";
    var infus = "";
    var obat = "";
    var inputan = "";
    var parameter = [];

    if("checklist_kriteria" == jenis){

    }else{
        IcuAction.getListDetailParameter(idDetailCheckup, jenis, function (res) {
            if(res.length > 0 ){

                $.each(res, function (i, item) {
                    inputan += '<div class="row">' +
                        '<div class="form-group">\n' +
                        '    <label class="col-md-3 jarak">'+item.jenis+'</label>\n' +
                        '<input value="'+item.idHeaderIcu+'" type="hidden" class="data-label'+jenis+'">'+
                        '    <div class="col-md-9">\n' +
                        '       <input class="form-control jarak nilai'+jenis+'" type="number">\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '</div>';
                });

                $('#inpt_'+jenis).html(inputan);
                $('#resus').html('');
                $('#darah').html('');
                $('#infus').html('');
                $('#'+jenis).html('');
                $('#select_isi').html('');
                $('#is_new').val(false);

            }else{

                if("keseimbangan_icu"){
                    var isi = '<div class="row">\n' +
                        '<div class="form-group">\n' +
                        '    <label class="col-md-3" style="margin-top: 7px">Pengisian</label>\n' +
                        '    <div class="col-md-9">\n' +
                        '        <select class="form-control select2 " id="jensis_pengisian" style="width: 100%" onchange="pilIsi(\''+jenis+'\', this.value)">\n' +
                        '            <option value="">[Select One]</option>\n' +
                        '            <option value="rc">Resusitasi Cairan</option>\n' +
                        '            <option value="in">Infus</option>\n' +
                        '        </select>\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '</div>';
                    $('#select_isi').html(isi);
                }

                var label = "";
                if("injeksi_icu" == jenis){
                    label = "Injeksi";
                }
                if("oral_icu" == jenis){
                    label = "Oral";
                }
                if("lain_icu" == jenis){
                    label = "Lain-Lain";
                }

                if("intakea_icu" == jenis){
                    parameter.push({'params': 'NGT/ Oral'});
                    parameter.push({'params': 'Minum'});
                }
                if("output_icu" == jenis){
                    parameter.push({'params': 'Drain 1'});
                    parameter.push({'params': 'Drain 2'});
                    parameter.push({'params': 'Urin'});
                    parameter.push({'params': 'Muntah'});
                    parameter.push({'params': 'BAB'});
                    parameter.push({'params': 'IWL'});
                    parameter.push({'params': 'Stoma'});
                }

                obat = '<hr class="garis">' +
                    '<div class="row">\n' +
                    '<div class="form-group">\n' +
                    '    <div class="col-md-offset-3 col-md-1">\n' +
                    '        <button class="btn btn-success" onclick="addInputan(\''+jenis+'\', \'obat\')"><i class="fa fa-plus"></i> Tambah</button>\n' +
                    '    </div>\n' +
                    '</div>\n' +
                    '</div>\n';

                if(parameter.length > 0){

                    $.each(parameter, function (i, item) {
                        obat +=
                            '<div class="row jarak" id="res'+i+'">\n' +
                            '<div class="form-group">\n' +
                            '    <label class="col-md-3 jarak">'+item.params+'</label>\n' +
                            '<input type="hidden" value="'+item.params+'" class="data-label'+jenis+' obat'+jenis+'">\n' +
                            '    <div class="col-md-9">\n' +
                            '       <input class="form-control jarak nilai'+jenis+'" type="number">\n' +
                            '    </div>\n' +
                            '</div>\n' +
                            '</div>'
                    });

                }else{
                    obat = obat +
                        '<div class="row jarak" id="res1">\n' +
                        '<div class="form-group">\n' +
                        '    <label class="col-md-3 jarak">'+label+'</label>\n' +
                        '    <div class="col-md-4">\n' +
                        '<input class="form-control jarak data-label'+jenis+' obat'+jenis+'" placeholder="Keterangan">\n' +
                        '    </div>\n' +
                        '    <label class="col-md-1 jarak">Nilai</label>\n' +
                        '    <div class="col-md-3">\n' +
                        '       <input class="form-control jarak nilai'+jenis+'" type="number">\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '</div>';
                }

                if("keseimbangan_icu" == jenis){
                    $('#resus').html(resus);
                    $('#darah').html(darah);
                    $('#infus').html(infus);
                }else{
                    $('#'+jenis).html(obat);
                }

                $('#inpt_'+jenis).html('');
                $('#is_new').val(true);
            }
        });
    }

    var sel = $('.select2').length;
    if(sel > 0){
        $('.select2').select2();
    }
}

function pilIsi(jenis, val){

    if("rc" == val){
        var resus = '<hr class="garis"><div class="row">\n' +
            '<div class="form-group">\n' +
            '    <div class="col-md-offset-3 col-md-1">\n' +
            '        <button class="btn btn-success" onclick="addInputan(\''+jenis+'\',\'resus\')"><i class="fa fa-plus"></i> Tambah</button>\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>\n' +
            '<div class="row jarak" id="res1">\n' +
            '<div class="form-group">\n' +
            '    <label class="col-md-3 jarak">Resusitasi Cairan</label>\n' +
            '    <div class="col-md-4">\n' +
            '       <input class="form-control jarak data-label'+jenis+'">\n' +
            '    </div>\n' +
            '    <label class="col-md-1 jarak">Nilai</label>\n' +
            '    <div class="col-md-3">\n' +
            '       <input class="form-control jarak nilai'+jenis+'" type="number">\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>';

        var darah = '<hr class="garis"><div class="row">\n' +
            '<div class="form-group">\n' +
            '    <div class="col-md-offset-3 col-md-1">\n' +
            '        <button class="btn btn-success" onclick="addInputan(\''+jenis+'\', \'darah\')"><i class="fa fa-plus"></i> Tambah</button>\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>\n' +
            '<div class="row jarak" id="res1">\n' +
            '<div class="form-group">\n' +
            '    <label class="col-md-3 jarak">Darah</label>\n' +
            '    <div class="col-md-4">\n' +
            '       <input class="form-control jarak data-label'+jenis+'">\n' +
            '    </div>\n' +
            '    <label class="col-md-1 jarak">Nilai</label>\n' +
            '    <div class="col-md-3">\n' +
            '       <input class="form-control jarak nilai'+jenis+'" type="number">\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>';

        $('#resus').html(resus);
        $('#darah').html(darah);
        $('#infus').html('');

    }else if("in" == val){
        var infus = '<hr class="garis"><div class="row">\n' +
            '<div class="form-group">\n' +
            '    <div class="col-md-offset-3 col-md-1">\n' +
            '        <button class="btn btn-success" onclick="addInputan(\''+jenis+'\', \'infus\')"><i class="fa fa-plus"></i> Tambah</button>\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>\n' +
            '<div class="row jarak" id="res1">\n' +
            '<div class="form-group">\n' +
            '    <label class="col-md-3 jarak">Infus/ TY Drip</label>\n' +
            '    <div class="col-md-4">\n' +
            '       <input class="form-control jarak data-label'+jenis+'">\n' +
            '    </div>\n' +
            '    <label class="col-md-1 jarak">Nilai</label>\n' +
            '    <div class="col-md-3">\n' +
            '       <input class="form-control jarak nilai'+jenis+'" type="number">\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>';

        $('#infus').html(infus);
        $('#resus').html('');
        $('#darah').html('');
    }
}

function addInputan(jenis, tipe){

    var id = $('.'+tipe).length;
    var count = id + 1;

    var label = "";
    var option = "";

    if("resus" == tipe){
        label = "Resusitasi Cairan";
    }
    if("darah" == tipe){
        label = "Darah";
    }
    if("infus" == tipe){
        label = "Infus/ TY Drip";
    }

    var inp ='<div class="row jarak" id="'+jenis+count+'">\n' +
        '<div class="form-group">\n' +
        '    <label class="col-md-3 jarak">'+label+'</label>\n' +
        '    <div class="col-md-4">\n' +
        '       <input class="form-control jarak data-label'+jenis+' '+jenis+'" placeholder="Keterangan Jenis">\n' +
        '    </div>\n' +
        '    <label class="col-md-1 jarak">Nilai</label>\n' +
        '    <div class="col-md-3">\n' +
        '       <input class="form-control jarak nilai'+jenis+'" type="number">\n' +
        '    </div>\n' +
        '    <div class="col-md-1">\n' +
        '        <button class="btn btn-danger" style="margin-left: -20px; margin-top: 10px" onclick="delInputan(\''+jenis+count+'\')"><i class="fa fa-trash"></i></button>\n' +
        '    </div>\n' +
        '</div>\n' +
        '</div>';

    if("keseimbangan_icu" == jenis){
        $('#'+tipe).append(inp);
    }else{
        $('#'+jenis).append(inp);
    }

    var sel = $('.select2').length;
    if(sel > 0){
        $('.select2').select2();
    }

}

function delInputan(id){
    $('#'+id).remove();
}

function saveInputan(jenis, ket){

    var isNew = $('#is_new').val();
    var data = [];
    var label = $('.data-label'+jenis);
    var nilai = $('.nilai'+jenis);
    var waktu = $('#waktu_'+jenis).val();

    if (waktu != '') {
        $.each(label, function (i, item) {
            if(isNew == "true"){
                if(label.value != "" && nilai[i].value != ""){
                    data.push({
                        'id_detail_checkup': idDetailCheckup,
                        'waktu': waktu,
                        'jenis': item.value,
                        'nilai': nilai[i].value,
                        'kategori': jenis});
                }
            }else{
                if(label.value != "" && nilai[i].value != ""){
                    data.push({
                        'id_detail_checkup': idDetailCheckup,
                        'id_header_icu': item.value,
                        'waktu': waktu,
                        'nilai': nilai[i].value,
                        'kategori': jenis});
                }
            }
        });

        var result = JSON.stringify(data);
        $('#save_icu_' + jenis).hide();
        $('#load_icu_' + jenis).show();
        dwr.engine.setAsync(true);

        IcuAction.save(result, isNew, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#modal-icu-' + jenis).modal('hide');
                    $('#warning_icu_' + ket).show().fadeOut(5000);
                    $('#msg_icu_' + ket).text("Berhasil menambahkan data ICU...");
                    $('#modal-icu-' + jenis).scrollTop(0);
                    if("keseimbangan_icu" == jenis){
                        listInputan(ket);
                    }
                } else {
                    $('#save_icu_' + jenis).show();
                    $('#load_icu_' + jenis).hide();
                    $('#warning_icu_' + jenis).show().fadeOut(5000);
                    $('#msg_icu_' + jenis).text(res.msg);
                    $('#modal-icu-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#warning_icu_' + jenis).show().fadeOut(5000);
        $('#msg_icu_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-icu-' + jenis).scrollTop(0);
    }
}

function listInputan(jenis) {
    IcuAction.getListDetail(idDetailCheckup, jenis+'_icu', function (res) {
        if (res.length > 0) {

            var tempHead = [];
            var tempBody = [];
            var tempJam = "";
            var head = "<td width='14%'>Tanggal Jam</td>";
            var body = "";

            $.each(res, function (i, item) {

                var tanggal = converterDate(new Date(item.createdDate));
                var tempTgl = "";

                if(i == 0){
                    tempTgl = tanggal;
                }else{
                    var tgl = res[i - 1]["createdDate"];
                    var tglB = converterDate(new Date(tgl));
                    if(tanggal == tglB){
                        tempTgl = "";
                    }else{
                        tempTgl = tanggal;
                    }
                }

                if(tempJam == ""){
                    tempJam = item.waktu+tanggal;
                    tempHead.push({'jenis':item.jenis})
                }else{
                    if(tempJam == item.waktu+tanggal){
                        tempHead.push({'jenis':item.jenis});
                    }
                }

                tempBody.push({'tanggal':tempTgl, 'jam': item.waktu, 'nilai': item.nilai});
            });

            if(tempHead.length > 0){
                $.each(tempHead, function (i, item) {
                    head += '<td>'+item.jenis+'</td>'
                });
            }

            if(tempBody.length > 0){
                var tempJm = "";
                var tempJml = 0;
                var tempTotal = 0;
                $.each(tempBody, function (i, item) {

                    var jam = item.jam;
                    var nilai = item.nilai;
                    var tgl = item.tanggal;

                    var temTR = "";
                    var temJM = "";
                    var jml   = "";
                    var last   = "";

                    tempJml   = parseInt(tempJml) + parseInt(item.nilai);

                    if(tempJm != jam){
                        tempJm = jam;
                        temTR = "|";
                        temJM = '<td>'+tgl+'<span class="pull-right">'+jam+'</span>'+'</td>';
                        if(i > 0){
                            var total = parseInt(tempJml) - parseInt(item.nilai);
                            tempTotal = parseInt(tempTotal) + parseInt(total);
                            jml = '<td align="center"><div class="number-circle" style="background-color: #b3ffb3">'+total+'</div>&nbsp;<div class="number-circle" style="background-color: #ffff66">'+tempTotal+'</div></td>'
                            tempJml = parseInt(item.nilai);
                        }
                    }

                    if(i == tempBody.length - 1){
                        tempTotal = parseInt(tempTotal) + parseInt(tempJml);
                        last = '<td align="center"><div class="number-circle" style="background-color: #b3ffb3">'+tempJml+'</div>&nbsp;<div class="number-circle" style="background-color: #ffff66">'+tempTotal+'</div></td>'
                        tempJml = parseInt(item.nilai);
                    }

                    if(i == 0){
                        body += '<td>'+tgl+'<span class="pull-right">'+jam+'</span>'+'</td><td>'+nilai+'</td>';
                    }else{
                        body += jml+temTR+temJM+'<td>'+nilai+'</td>'+last;
                    }
                });
            }

            var bod = body.split("|");
            var bb = "";
            $.each(bod, function (i, item) {
                bb += '<tr>'+item+'</tr>';
            });

            head = head + '<td width="16%">Jumlah/ Komulatif</td>';

            if("keseimbangan" == jenis){
                $('#head_'+jenis+'_icu').html('<tr>'+head+'</tr>');
                $('#body_'+jenis+'_icu').html(bb);
            }else{
                var table = '<table style="font-size: 12px" class="table table-bordered">' +
                    '<thead><tr>' + head + '</tr></thead>' +
                    '<tbody>' + bb + '</tbody>' +
                    '</table>';

                var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                newRow.insertAfter($('table').find('#row_icu_' + jenis));
                var url = contextPath + '/pages/images/minus-allnew.png';
                $('#btn_icu_' + jenis).attr('src', url);
                $('#btn_icu_' + jenis).attr('onclick', 'delRowICCU(\'' + jenis + '\')');
            }
        }else{
            if("keseimbangan" != jenis){
                var table = '<table style="font-size: 12px" class="table table-bordered">' +
                    '<tbody>' + '<tr><td>Data belum ada</td></tr>' + '</tbody>' +
                    '</table>';

                var newRow = $('<tr id="del_icu_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                newRow.insertAfter($('table').find('#row_icu_' + jenis));
                var url = contextPath + '/pages/images/minus-allnew.png';
                $('#btn_icu_' + jenis).attr('src', url);
                $('#btn_icu_' + jenis).attr('onclick', 'delRowICCU(\'' + jenis + '\')');
            }
        }
    });
}

function delRowICCU(id) {
    $('#del_icu_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_icu_' + id).attr('src', url);
    $('#btn_icu_' + id).attr('onclick', 'listInputan(\'' + id + '\')');
}




