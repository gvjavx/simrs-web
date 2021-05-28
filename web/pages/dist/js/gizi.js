function showModalGizi(jenis, idRM, isSetIdRM) {
    if (isSetIdRM == "Y") {
        tempidRm = idRM;
    }
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }

    if ("skrining_gizi" == jenis) {
        setSkriningGizi(umur);
    }
    if ("pengkajian_gizi" == jenis || "pengkajian_gizi_ulang" == jenis) {
        setPengkajianGizi(umur);
    }

    $('#modal-gizi-' + jenis).modal({show: true, backdrop: 'static'});
    setDataPasien();
}

function saveGizi(jenis, ket) {
    var data = [];
    var cek = false;
    var dataTemp = "";
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }

    if ("add_skrining_gizi" == jenis) {
        var tipe = $('#tipe_gizi').val();
        if ("dewasa" == tipe) {
            var va1 = $('[name=gz1]:checked').val();
            var va2 = $('[name=gz2]:checked').val();
            var va3 = $('[name=gz3]:checked').val();
            var va4 = "";
            var va4d = $('[name=gz4]:checked').val();

            if (va4d != undefined) {
                var a = $('#ket_gz4').val();
                if (va4d == "Lain-Lain") {
                    if (a != '') {
                        va4 = a;
                    }
                } else {
                    va4 = va4d;
                }
            }

            var cek = "";
            var ketVa1 = $('[name=penurunan]:checked').val();
            if(va1 != undefined){
                if(va1 == "Ya|2"){
                    if(ketVa1 != undefined){
                        cek = ketVa1;
                    }
                }else{
                    cek = va1;
                }
            }

            var cek2 = "";
            if(va3 != undefined){
                if(va3 == "Ya|3"){
                    if(va4d != undefined){
                        cek2 = va4d;
                    }
                }else{
                    cek2 = va4d;
                }
            }

            if (cek != '' && cek2 != '' && va2 != undefined) {
                var isi1 = va1.split("|")[0];
                var isi2 = va2.split("|")[0];
                var isi3 = va3.split("|")[0];

                var skor1 = va1.split("|")[1];
                var skor2 = va2.split("|")[1];
                var skor3 = va3.split("|")[1];

                var berat = "";
                var penyakit = "";

                if(ketVa1 != undefined){
                    berat = ketVa1;
                }

                data.push({
                    'parameter': '1. Apakah pasien mengalami penuruanan / peningkatan BB yang tidak di inginkan dalam 6 bulan terakhir ? '+berat,
                    'jawaban': isi1,
                    'skor': skor1,
                    'keterangan': jenis,
                    'jenis': ket,
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': '2. Apakah asupan makan berkurang karena tidak nafsu makan ?',
                    'jawaban': isi2,
                    'skor': skor2,
                    'keterangan': jenis,
                    'jenis': ket,
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });

                if (isi3 == "Ya") {
                    va4 = "Penyakit, " + va4;
                } else {
                    va4 = "";
                }

                data.push({
                    'parameter': '3. Pasien dengan diagnosa khusus / kondisi khusus ? ' + va4,
                    'jawaban': isi3,
                    'skor': skor3,
                    'keterangan': jenis,
                    'jenis': ket,
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });

                var total = (parseInt(skor1) + parseInt(skor2) + parseInt(skor3));
                data.push({
                    'parameter': 'Total',
                    'jawaban': '',
                    'skor': total.toString(),
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'total',
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });

                var kesimpulan = "Beresiko sedang, ulangi skrining setiap 7 hari";
                if (total >= 2) {
                    kesimpulan = "Beresiko tinggi, lakukan asuhan gizi terstandart";
                }
                data.push({
                    'parameter': 'Kesimpulan',
                    'jawaban': kesimpulan,
                    'skor': "",
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'kesimpulan',
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        } else {
            var va1 = $('[name=gz1]:checked').val();
            var va2 = $('[name=gz2]:checked').val();
            var va3 = $('[name=gz3]:checked').val();
            var va4 = $('[name=gz4]:checked').val();

            var temp = "";
            var va5 = $('[name=gizi5]');
            $.each(va5, function (i, item) {
                if (item.checked && item.value != 'on' && item.value != '') {
                    if (temp != '') {
                        temp = temp + '|' + item.value;
                    } else {
                        temp = item.value;
                    }
                }
            });

            if (va1 && va2 && va3 && va4 != undefined) {
                var isi1 = va1.split("|")[0];
                var isi2 = va2.split("|")[0];
                var isi3 = va3.split("|")[0];
                var isi4 = va4.split("|")[0];

                var skor1 = va1.split("|")[1];
                var skor2 = va2.split("|")[1];
                var skor3 = va3.split("|")[1];
                var skor4 = va4.split("|")[1];

                data.push({
                    'parameter': '1. Apakah pasien tampak kurus ?',
                    'jawaban': isi1,
                    'skor': skor1,
                    'keterangan': jenis,
                    'jenis': ket,
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });
                data.push({
                    'parameter': '2. Apakah terdapat penurunan BB selama 1 bulan terakhir ?',
                    'jawaban': isi2,
                    'skor': skor2,
                    'keterangan': jenis,
                    'jenis': ket,
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });

                data.push({
                    'parameter': '3. Apakah terdapat SALAH SATU dari kondisi tersebut ?',
                    'jawaban': isi3,
                    'skor': skor3,
                    'keterangan': jenis,
                    'jenis': ket,
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });

                data.push({
                    'parameter': '4. Apakah terdapat penyakit atau keadaan yang mengakibatkan pasien beresiko mengalami malnutrisi ?',
                    'jawaban': isi3,
                    'skor': skor3,
                    'keterangan': jenis,
                    'jenis': ket,
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });

                if (temp != '') {
                    data.push({
                        'parameter': 'Penyakit',
                        'jawaban': temp,
                        'skor': "",
                        'keterangan': jenis,
                        'jenis': ket,
                        'tipe': 'li',
                        'no_checkup': noCheckup,
                        'id_detail_checkup': idDetailCheckup
                    });
                }

                var total = (parseInt(skor1) + parseInt(skor2) + parseInt(skor3) + parseInt(skor4));
                data.push({
                    'parameter': 'Total',
                    'jawaban': '',
                    'skor': total.toString(),
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'total',
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });

                var kesimpulan = "Beresiko sedang, ulangi skrining setiap 7 hari";
                if (total >= 2) {
                    kesimpulan = "Beresiko tinggi, lakukan asuhan gizi terstandart";
                }
                data.push({
                    'parameter': 'Kesimpulan',
                    'jawaban': kesimpulan,
                    'skor': "",
                    'keterangan': jenis,
                    'jenis': ket,
                    'tipe': 'kesimpulan',
                    'no_checkup': noCheckup,
                    'id_detail_checkup': idDetailCheckup
                });
                cek = true;
            }
        }
    }

    if ("add_pengkajian_gizi" == jenis || "add_pengkajian_gizi_ulang" == jenis) {

        var va1 = $('#gizi1').val();
        var va2 = $('#gizi2').val();
        var va3 = $('#gizi3').val();
        var va4 = $('#gizi4').val();
        var va5 = $('#gizi5').val();
        var va6 = $('#gizi6').val();
        var va7 = $('#gizi7').val();
        var va8 = $('#gizi8').val();
        var temp = "";

        var va10 = $('#add_gizi8').val();
        if (parseInt(umur) >= 18) {
            va10 = "cek";
        }

        var va11 = $('[name=gizi9]:checked').val();

        var va12 = $('[name=gizi10]:checked').val();
        var va13 = $('[name=gizi11]:checked').val();

        var va14 = $('[name=gizi13]');
        var temp14 = "";
        $.each(va14, function (i, item) {
            if (item.checked && item.value != '' && item.value != 'no') {
                if (temp14 != '') {
                    temp14 = temp14 + ', ' + item.value;
                } else {
                    temp14 = item.value;
                }
            }
        });

        var va15 = $('[name=gizi14]:checked').val();
        var va16 = $('[name=gizi15]');
        var tempVa15 = "";
        if (va15 != undefined) {
            if ("Kurang" == va15) {
                var tmp = "";
                $.each(va16, function (i, item) {
                    if (item.checked && item.value != '' && item.value != 'on') {
                        if (tmp != '') {
                            tmp = tmp + ',' + item.value;
                        } else {
                            tmp = item.value;
                        }
                    }
                });
                if (tmp != '') {
                    tempVa15 = "Nafsu Makan : " + va15 + ", " + tmp;
                }
            } else {
                tempVa15 = "Nafsu Makan : " + va15;
            }
        }

        var va17 = $('[name=gizi16]');
        var va18 = $('[name=gizi17]:checked').val();
        var temp17 = "";
        $.each(va17, function (i, item) {
            if (item.checked && item.value != '' && item.value != 'on') {
                var cek = item.value.split(",")[0];
                var ttmp = "";
                if ("Enteral" == cek) {
                    ttmp = '. ' + va18;
                }
                if (temp17 != '') {
                    temp17 = temp17 + ', ' + item.value + ttmp;
                } else {
                    temp17 = item.value + ttmp;
                }
            }
        });

        var tempRiwayat = "Dahulu : " + temp14 + '|' + "Sekarang : " + tempVa15 + ', Diet : ' + temp17;

        var va19 = $('[name=gizi18]');
        var va20 = $('#gizi19').val();

        var tempPersonel = "";
        var temp19 = "";
        $.each(va19, function (i, item) {
            if (item.checked && item.value != '' && item.value != 'on') {
                if (temp19 != '') {
                    temp19 = temp19 + ', ' + item.value;
                } else {
                    temp19 = item.value;
                }
            }
        });

        tempPersonel = 'Riwayat penyakit dahulu : ' + temp19 + '|Riwayat penyakit sekarang : ' + va20;

        var ttd2 = document.getElementById("gizi21");
        var cekTtd2 = isCanvasBlank(ttd2);

        var nama2 = $('#nama_gizi21').val();
        var sip2 = $('#sip_dokter').val();

        if(va1 != ''){
            temp = 'BB : ' + va1+ ' Kg';
        }
        if(va2 != ''){
            temp = temp+'|TB : ' + va2 + ' Cm';
        }
        if(va3 != ''){
            temp = temp+'|LLA : ' + va3 + ' Kg'
        }
        if(va4 != ''){
            temp = temp+'|BBI ' + va4;
        }
        if(va5 != ''){
            temp = temp+'|%BBI ' + va5;
        }
        if(va6 != ''){
            temp = temp+'|BB/U' + va6;
        }
        if(va7 != ''){
            temp = temp+'|TB/U ' + va7;
        }
        if(va8 != ''){
            temp = temp+'|BB/TB ' + va8;
        }
        if(va10 != ''){
            temp = temp+'|LLA/U ' + va10;
        }
        if(va11 != undefined){
            temp = temp+'|Status Gizi : ' + va11;
        }

        if (parseInt(umur) >= 16) {
            if(va1 != ''){
                temp = 'BB : ' + va1+ ' Kg';
            }
            if(va2 != ''){
                temp = temp+'|TB : ' + va2 + ' Cm';
            }
            if(va3 != ''){
                temp = temp+'|BBI : ' + va3;
            }
            if(va4 != ''){
                temp = temp+'|IMT ' + va4+' Kg/m2';
            }
            if(va5 != ''){
                temp = temp+'|TL ' + va5 + ' Cm';
            }
            if(va6 != ''){
                temp = temp+'|TB Est' + va6 + ' Cm';
            }
            if(va7 != ''){
                temp = temp+'|LLA ' + va7 + ' Cm';
            }
            if(va8 != ''){
                temp = temp+'|%LLA ' + va8 + ' %';
            }
            if(va11 != undefined){
                temp = temp+'|Status Gizi : ' + va11;
            }
        }

        if (va1 && tempVa15 && nama2 && sip2 != '' && va12 && va13 != undefined && !cekTtd2) {
            data.push({
                'parameter': 'A. Antropmentri',
                'jawaban': temp,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'li',
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'B. Biokimia',
                'jawaban': va12,
                'keterangan': jenis,
                'jenis': ket,
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'C. Fisik - Klinik',
                'jawaban': va13,
                'keterangan': jenis,
                'jenis': ket,
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'D. Riwayat Gizi',
                'jawaban': tempRiwayat,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'li',
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'E. Riwayat Personal',
                'jawaban': tempPersonel,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'li',
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup
            });
            var canv2 = convertToDataURL(ttd2);
            data.push({
                'parameter': 'TTD Ahli Gizi',
                'jawaban': canv2,
                'keterangan': jenis,
                'jenis': ket,
                'tipe': 'ttd',
                'nama_terang': nama2,
                'sip': sip2,
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("add_asuhan_gizi" == jenis) {
        var va1 = $('[name=gizi1]');
        var temp1 = "";
        $.each(va1, function (i, item) {
            if (item.checked && item.value != '' && item.value != 'on') {
                if (temp1 != '') {
                    temp1 = temp1 + '|' + item.value;
                } else {
                    temp1 = item.value;
                }
            }
        });
        var va2 = $('#gizi2').val();
        var va3 = $('#gizi3').val();
        var va4 = $('#gizi4').val();
        var va5 = $('#gizi5').val();
        var va6 = $('#gizi6').val();
        var va7 = $('#gizi7').val();
        var va8 = $('[name=gizi8]');
        var temp8 = "";
        $.each(va8, function (i, item) {
            if (item.checked && item.value != '' && item.value != 'on') {
                if (temp8 != '') {
                    temp8 = temp8 + '|' + item.value;
                } else {
                    temp8 = item.value;
                }
            }
        });

        var va9 = $('[name=gizi9]');
        var temp9 = "";
        $.each(va9, function (i, item) {
            if (item.checked && item.value != '' && item.value != 'on') {
                if (temp9 != '') {
                    temp9 = temp9 + '|' + item.value;
                } else {
                    temp9 = item.value;
                }
            }
        });

        var ttd2 = document.getElementById("gizi_dokter");
        var cekTtd2 = isCanvasBlank(ttd2);

        var nama2 = $('#nama_gizi21').val();
        var sip2 = $('#sip_dokter').val();

        if (temp1 && va2 && va3 && va4 && va5 && va6 && va7 && temp8 && temp9 && nama2 && sip2 != '' && !cekTtd2) {
            dataTemp = {
                'asesmen': temp1,
                'diagnosa': va2,
                'intervensi': 'Diberikan diet ' + va3 + ', Energi : ' + va4 + ' kkal' + ', Protein : ' + va5 + ' gram, Lemak : ' + va6 + ' gram, KH : ' + va7 + ' gram',
                'rencana': temp8,
                'edukasi': temp9,
                'nama_dokter': nama2,
                'sip_dokter': sip2,
                'ttd_dokter': convertToDataURL(ttd2),
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup
            }
            cek = true;
        }
    }

    if("add_evaluasi_gizi" == jenis){
        var va1 = $('#gizi1').val();
        var va2 = $('#gizi2').val();
        var va3 = $('#gizi3').val();
        var va4 = $('#gizi4').val();
        var va5 = $('#gizi5').val();
        var va6 = $('#gizi6').val();
        var va7 = $('#gizi7').val();
        var va8 = $('#gizi8').val();
        var ttd2 = document.getElementById("gizi_dokter");
        var cekTtd2 = isCanvasBlank(ttd2);
        var nama2 = $('#nama_gizi21').val();
        var sip2 = $('#sip_dokter').val();
        if(!cekTtd2 && va1 && nama2 && sip2 != ''){
            dataTemp = {
                'tanggal': va1,
                'berat_badan': va2,
                'status_gizi': va3,
                'intake': va4,
                'fisik': va5,
                'biokimia': va6,
                'intervensi': va7,
                'lain_lain': va8,
                'nama_dokter': nama2,
                'sip_dokter': sip2,
                'ttd_dokter': convertToDataURL(ttd2),
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup
            }
            cek = true;
        }
    }

    if (cek) {
        if (!cekSession()) {
            delRowGizi(jenis);
            $('#save_gizi_' + jenis).hide();
            $('#load_gizi_' + jenis).show();
            var pasienData = JSON.stringify(dataPasien);
            if ("add_asuhan_gizi" == jenis) {
                dwr.engine.setAsync(true);
                var result = JSON.stringify(dataTemp);
                AsesmenGiziAction.saveAsuhan(result, pasienData, {
                    callback: function (res) {
                        if (res.status == "success") {
                            $('#save_gizi_' + jenis).show();
                            $('#load_gizi_' + jenis).hide();
                            $('#modal-gizi-' + jenis).modal('hide');
                            $('#warning_gizi_' + ket).show().fadeOut(5000);
                            $('#msg_gizi_' + ket).text("Berhasil menambahkan data...");
                            $('#modal-gizi-' + jenis).scrollTop(0);
                            delRowGizi(jenis);
                            detailGizi(jenis);
                        } else {
                            $('#save_gizi_' + jenis).show();
                            $('#load_gizi_' + jenis).hide();
                            $('#warning_gizi_' + jenis).show().fadeOut(5000);
                            $('#msg_gizi_' + jenis).text(res.msg);
                            $('#modal-gizi-' + jenis).scrollTop(0);
                        }
                    }
                });
            } else if ("add_evaluasi_gizi" == jenis) {
                dwr.engine.setAsync(true);
                var result = JSON.stringify(dataTemp);
                AsesmenGiziAction.saveMonitoring(result, pasienData, {
                    callback: function (res) {
                        if (res.status == "success") {
                            $('#save_gizi_' + jenis).show();
                            $('#load_gizi_' + jenis).hide();
                            $('#modal-gizi-' + jenis).modal('hide');
                            $('#warning_gizi_' + ket).show().fadeOut(5000);
                            $('#msg_gizi_' + ket).text("Berhasil menambahkan data...");
                            $('#modal-gizi-' + jenis).scrollTop(0);
                            delRowGizi(jenis);
                            detailGizi(jenis);
                        } else {
                            $('#save_gizi_' + jenis).show();
                            $('#load_gizi_' + jenis).hide();
                            $('#warning_gizi_' + jenis).show().fadeOut(5000);
                            $('#msg_gizi_' + jenis).text(res.msg);
                            $('#modal-gizi-' + jenis).scrollTop(0);
                        }
                    }
                });
            } else {
                dwr.engine.setAsync(true);
                var result = JSON.stringify(data);
                AsesmenGiziAction.saveAsesmen(result, pasienData, {
                    callback: function (res) {
                        if (res.status == "success") {
                            $('#save_gizi_' + jenis).show();
                            $('#load_gizi_' + jenis).hide();
                            $('#modal-gizi-' + jenis).modal('hide');
                            $('#warning_gizi_' + ket).show().fadeOut(5000);
                            $('#msg_gizi_' + ket).text("Berhasil menambahkan data...");
                            $('#modal-gizi-' + jenis).scrollTop(0);
                            delRowGizi(jenis);
                            detailGizi(jenis);
                        } else {
                            $('#save_gizi_' + jenis).show();
                            $('#load_gizi_' + jenis).hide();
                            $('#warning_gizi_' + jenis).show().fadeOut(5000);
                            $('#msg_gizi_' + jenis).text(res.msg);
                            $('#modal-gizi-' + jenis).scrollTop(0);
                        }
                    }
                });
            }
        }
    } else {
        $('#warning_gizi_' + jenis).show().fadeOut(5000);
        $('#msg_gizi_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-gizi-' + jenis).scrollTop(0);
    }
}

function detailGizi(jenis) {
    if (!cekSession()) {
        if (jenis != '') {
            var head = "";
            var body = "";
            var first = "";
            var cekData = false;

            if ("add_evaluasi_gizi" == jenis) {
                AsesmenGiziAction.getListMonitoring(noCheckup, function (list) {
                    if (list.length > 0) {
                        $.each(list, function (i, item) {
                            body += '<tr>' +
                                '<td>' + cekItemIsNull(item.tanggal) + '</td>' +
                                '<td>' + cekItemIsNull(item.beratBadan) + '</td>' +
                                '<td>' + cekItemIsNull(item.statusGizi) + '</td>' +
                                '<td>' + cekItemIsNull(item.intake) + '</td>' +
                                '<td>' + cekItemIsNull(item.fisik) + '</td>' +
                                '<td>' + cekItemIsNull(item.biokimia) + '</td>' +
                                '<td>' + cekItemIsNull(item.intervensi) + '</td>' +
                                '<td>' + cekItemIsNull(item.lainLain) + '</td>' +
                                '<td>' +
                                '<p class="text-center">TTD Pasien</p>' +
                                '<img class="text-center" src="' + item.ttdPasien + '" style="height: 80px">' +
                                '<p class="text-center" style="margin-top: -3px">' + cekItemIsNull(item.namaPasien) + '</p>' +
                                '<br>' +
                                '<p class="text-center">TTD Ahli Gizi</p>' +
                                '<img class="text-center" src="' + item.ttdDokter + '" style="height: 80px">' +
                                '<p class="text-center" style="margin-top: -3px">' + cekItemIsNull(item.namaDokter) + '</p>' +
                                '<p class="text-center" style="margin-top: -7px">' + cekItemIsNull(item.sipDokter) + '</p>' +
                                '</td>' +
                                '<td align="center"><i id="delete_' + item.idMonitoringGizi + '" onclick="conGizi(\'' + jenis + '\', \'add_evaluasi_gizi\', \'' + item.idMonitoringGizi + '\')" class="fa fa-trash hvr-grow" style="color: red"></i></td>' +
                                '</tr>';
                        });
                        cekData = true;
                    } else {
                        body = '<tr>' +
                            '<td>Data belum ada</td>' +
                            '</tr>';
                    }

                    if (cekData) {
                        head = '<tr style="font-weight: bold">' +
                            '<td style="vertical-align: center">Tanggal</td>' +
                            '<td style="vertical-align: center">BB (Kg)</td>' +
                            '<td style="vertical-align: center">Status Gizi</td>' +
                            '<td style="vertical-align: center">Intakea</td>' +
                            '<td style="vertical-align: center">Fisik/Klinis</td>' +
                            '<td style="vertical-align: center">Biokimia</td>' +
                            '<td style="vertical-align: center">Intervensi</td>' +
                            '<td style="vertical-align: center">Lain-Lain</td>' +
                            '<td style="vertical-align: center" align="center">TTD</td>' +
                            '<td style="vertical-align: center" align="center">Action</td>' +
                            '</tr>'
                    }

                    var table = '<table style="font-size: 12px" class="table table-bordered">' +
                        '<thead>' + head + '</thead>' +
                        '<tbody>' + body + '</tbody>' +
                        '</table>';

                    var newRow = $('<tr id="del_gizi_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                    newRow.insertAfter($('table').find('#row_gizi_' + jenis));
                    var url = contextPath + '/pages/images/minus-allnew.png';
                    $('#btn_gizi_' + jenis).attr('src', url);
                    $('#btn_gizi_' + jenis).attr('onclick', 'delRowGizi(\'' + jenis + '\')');
                });
            } else if ("add_asuhan_gizi" == jenis) {
                AsesmenGiziAction.getListAsuhan(noCheckup, function (list) {
                    if (list.length > 0) {
                        $.each(list, function (i, item) {
                            var tempAs = "";
                            if (item.asesmen != null && item.asesmen != '') {
                                var ases = item.asesmen.split("|");
                                $.each(ases, function (i, item) {
                                    tempAs += '<li>' + item + '</li>';
                                });
                            }

                            var tempReb = "";
                            if (item.rencana != null && item.rencana != '') {
                                var renca = item.rencana.split("|");
                                $.each(renca, function (i, item) {
                                    tempReb += '<li>' + item + '</li>';
                                });
                            }

                            var tempEdu = "";
                            if (item.edukasi != null && item.edukasi != '') {
                                var edu = item.edukasi.split("|");
                                $.each(edu, function (i, item) {
                                    tempEdu += '<li>' + item + '</li>';
                                });
                            }

                            body += '<tr>' +
                                '<td><ul style="margin-left: 10px">' + tempAs + '</ul></td>' +
                                '<td>' + cekItemIsNull(item.diagnosa) + '</td>' +
                                '<td>' + cekItemIsNull(item.intervensi) + '</td>' +
                                '<td><ul style="margin-left: 10px">' + tempReb + '</ul></td>' +
                                '<td><ul style="margin-left: 10px">' + tempEdu + '</ul></td>' +
                                '<td>' +
                                '<p class="text-center">TTD Pasien</p>' +
                                '<img class="text-center" src="' + item.ttdPasien + '" style="height: 80px">' +
                                '<p class="text-center" style="margin-top: -3px">' + cekItemIsNull(item.namaPasien) + '</p>' +
                                '<br>' +
                                '<p class="text-center">TTD Ahli Gizi</p>' +
                                '<img class="text-center" src="' + item.ttdDokter + '" style="height: 80px">' +
                                '<p class="text-center" style="margin-top: -3px">' + cekItemIsNull(item.namaDokter) + '</p>' +
                                '<p class="text-center" style="margin-top: -7px">' + cekItemIsNull(item.sipDokter) + '</p>' +
                                '</td>' +
                                '<td align="center"><i id="delete_' + item.idAsuhanGizi + '" onclick="conGizi(\'' + jenis + '\', \'asuhan_gizi\', \'' + item.idAsuhanGizi + '\')" class="fa fa-trash hvr-grow" style="color: red"></i></td>' +
                                '</tr>';
                        });
                        cekData = true;
                    } else {
                        body = '<tr>' +
                            '<td>Data belum ada</td>' +
                            '</tr>';
                    }

                    if (cekData) {
                        head = '<tr style="font-weight: bold">' +
                            '<td style="vertical-align: center">Asesmen</td>' +
                            '<td style="vertical-align: center">Diagnosa</td>' +
                            '<td style="vertical-align: center">Intervensi</td>' +
                            '<td style="vertical-align: center">Rencana Monev</td>' +
                            '<td style="vertical-align: center">Edukasi</td>' +
                            '<td style="vertical-align: center" align="center">TTD</td>' +
                            '<td style="vertical-align: center" align="center">Action</td>' +
                            '</tr>'
                    }

                    var table = '<table style="font-size: 12px" class="table table-bordered">' +
                        '<thead>' + head + '</thead>' +
                        '<tbody>' + body + '</tbody>' +
                        '</table>';

                    var newRow = $('<tr id="del_gizi_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                    newRow.insertAfter($('table').find('#row_gizi_' + jenis));
                    var url = contextPath + '/pages/images/minus-allnew.png';
                    $('#btn_gizi_' + jenis).attr('src', url);
                    $('#btn_gizi_' + jenis).attr('onclick', 'delRowGizi(\'' + jenis + '\')');
                });
            } else {
                AsesmenGiziAction.getListAsesmen(idDetailCheckup, jenis, function (list) {
                    if (list.length > 0) {
                        $.each(list, function (i, item) {
                            var jwb = cekItemIsNull(item.jawaban);
                            if ("add_skrining_gizi" == jenis) {
                                if ("total" == item.tipe) {
                                    body += '<tr>' +
                                        '<td width="60%" colspan="2">' + item.parameter + '</td>' +
                                        '<td>' + cekItemIsNull(item.skor) + '</td>' +
                                        '</tr>';
                                } else if ("kesimpulan" == item.tipe) {
                                    body += '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                                        '<td width="60%">' + item.parameter + '</td>' +
                                        '<td colspan="2">' + jwb + '</td>' +
                                        '</tr>';
                                } else if ("li" == item.tipe) {
                                    if (jwb != '') {
                                        var temp = jwb.split("|");
                                        var liTemp = "";
                                        $.each(temp, function (i, item) {
                                            liTemp += '<li>' + item + '</li>';
                                        });
                                        body += '<tr>' +
                                            '<td width="60%">' + item.parameter + '</td>' +
                                            '<td colspan="2"><ul style="margin-left: 10px">' + liTemp + '</ul></td>' +
                                            '</tr>';
                                    }
                                } else {
                                    body += '<tr>' +
                                        '<td width="60%">' + item.parameter + '</td>' +
                                        '<td>' + jwb + '</td>' +
                                        '<td>' + cekItemIsNull(item.skor) + '</td>' +
                                        '</tr>';
                                }
                            } else {
                                if ("li" == item.tipe) {
                                    var btn = "";
                                    if("A. Antropmentri" == item.parameter){
                                        btn = '<i onclick="conGizi(\''+jenis+'\', \''+item.jenis+'\', \''+item.idAsesmenGizi+'\')" class="fa fa-trash hvr-grow" style="font-size: 20px; color: red"></i>';
                                    }
                                    if (jwb != '') {
                                        var temp = jwb.split("|");
                                        var liTemp = "";
                                        $.each(temp, function (i, item) {
                                            liTemp += '<li>' + item + '</li>';
                                        });
                                        body += '<tr>' +
                                            '<td width="40%">' + item.parameter + '</td>' +
                                            '<td>' +
                                            '<div class="pull-right">'+btn+'</div>'+
                                            '<ul style="margin-left: 10px">' + liTemp + '</ul>' +
                                            '</td>' +
                                            '</tr>';
                                    }
                                } else if ("ttd" == item.tipe) {
                                    body += '<tr>' +
                                        '<td width="40%">' + item.parameter + '</td>' +
                                        '<td colspan="2">' + '<img src="' + jwb + '" style="height: 80px">' +
                                        '<p style="margin-top: -3px">' + cekItemIsNull(item.namaTerang) + '</p>' +
                                        '<p style="margin-top: -7px">' + cekItemIsNull(item.sip) + '</p></td>' +
                                        '</tr>';
                                } else {
                                    body += '<tr>' +
                                        '<td width="40%">' + item.parameter + '</td>' +
                                        '<td>' + jwb + '</td>' +
                                        '</tr>';
                                }
                            }
                        });
                        cekData = true;
                    } else {
                        body = '<tr>' +
                            '<td>Data belum ada</td>' +
                            '</tr>';
                    }

                    if (cekData) {
                        if ("add_skrining_gizi" == jenis) {
                            head = '<tr style="font-weight: bold">' +
                                '<td>Parameter</td>' +
                                '<td>Jawaban</td>' +
                                '<td>Skor</td>' +
                                '</tr>'
                        }
                    }

                    var table = '<table style="font-size: 12px" class="table table-bordered">' +
                        '<thead>' + head + '</thead>' +
                        '<tbody>' + body + '</tbody>' +
                        '</table>';

                    var newRow = $('<tr id="del_gizi_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
                    newRow.insertAfter($('table').find('#row_gizi_' + jenis));
                    var url = contextPath + '/pages/images/minus-allnew.png';
                    $('#btn_gizi_' + jenis).attr('src', url);
                    $('#btn_gizi_' + jenis).attr('onclick', 'delRowGizi(\'' + jenis + '\')');
                });
            }
        }
    }
}

function delRowGizi(id) {
    $('#del_gizi_' + id).remove();
    var url = "";
    if (id == "add_asuhan_gizi" || id == "add_evaluasi_gizi" || id == "add_pengkajian_gizi") {
        url = contextPath + '/pages/images/icons8-add-list-25.png';
    } else {
        url = contextPath + '/pages/images/icons8-plus-25.png';
    }
    $('#btn_gizi_' + id).attr('src', url);
    $('#btn_gizi_' + id).attr('onclick', 'detailGizi(\'' + id + '\')');
}

function conGizi(jenis, ket, idAsesmen) {
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show: true, backdrop: 'static'});
    if("add_pengkajian_gizi" == jenis){
        $('#save_con_rm').attr('onclick', 'delGizi(\'' + jenis + '\', \'' + ket + '\', \''+idAsesmen+'\')');
    }else{
        if (idAsesmen != undefined && idAsesmen != '' && idAsesmen != null) {
            $('#save_con_rm').attr('onclick', 'delAsuhanMon(\'' + jenis + '\', \'' + ket + '\', \'' + idAsesmen + '\')');
        } else {
            $('#save_con_rm').attr('onclick', 'delGizi(\'' + jenis + '\', \'' + ket + '\')');
        }
    }

}

function delAsuhanMon(jenis, ket, idAsesmen) {
    $('#modal-confirm-rm').modal('hide');
    if (!cekSession()) {
        var dataPasien = {
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'id_pasien': idPasien,
            'id_rm': tempidRm
        }
        delRowGizi(jenis);
        var result = JSON.stringify(dataPasien);
        startIconSpin('delete_' + idAsesmen);
        if ("add_asuhan_gizi" == jenis) {
            dwr.engine.setAsync(true);
            AsesmenGiziAction.saveDeleteAsuhan(idAsesmen, result, {
                callback: function (res) {
                    if (res.status == "success") {
                        stopIconSpin('delete_' + idAsesmen);
                        $('#modal-gizi-' + ket).scrollTop(0);
                        $('#warning_gizi_' + ket).show().fadeOut(5000);
                        $('#msg_gizi_' + ket).text("Berhasil menghapus data...");
                    } else {
                        stopIconSpin('delete_' + idAsesmen);
                        $('#modal-gizi-' + ket).scrollTop(0);
                        $('#warn_' + ket).show().fadeOut(5000);
                        $('#msg_' + ket).text(res.msg);
                    }
                    detailGizi(jenis);
                }
            });
        } else {
            dwr.engine.setAsync(true);
            AsesmenGiziAction.saveDeleteMonitoring(idAsesmen, result, {
                callback: function (res) {
                    if (res.status == "success") {
                        stopIconSpin('delete_' + idAsesmen);
                        $('#modal-gizi-' + ket).scrollTop(0);
                        $('#warning_gizi_' + ket).show().fadeOut(5000);
                        $('#msg_gizi_' + ket).text("Berhasil menghapus data...");
                    } else {
                        stopIconSpin('delete_' + idAsesmen);
                        $('#modal-gizi-' + ket).scrollTop(0);
                        $('#warn_' + ket).show().fadeOut(5000);
                        $('#msg_' + ket).text(res.msg);
                    }
                    detailGizi(jenis);
                }
            });
        }
    }
}

function delGizi(jenis, ket, idAsesmen) {
    $('#modal-confirm-rm').modal('hide');
    if (!cekSession()) {
        var dataPasien = {
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'id_pasien': idPasien,
            'id_rm': tempidRm
        }
        delRowGizi(jenis);
        var result = JSON.stringify(dataPasien);
        startSpin('delete_' + jenis);
        dwr.engine.setAsync(true);
        AsesmenGiziAction.saveDeleteAsesmen(idDetailCheckup, jenis, result, idAsesmen, {
            callback: function (res) {
                if (res.status == "success") {
                    stopSpin('delete_' + jenis);
                    $('#modal-gizi-' + ket).scrollTop(0);
                    $('#warning_gizi_' + ket).show().fadeOut(5000);
                    $('#msg_gizi_' + ket).text("Berhasil menghapus data...");
                } else {
                    stopSpin('delete_' + jenis);
                    $('#modal-gizi-' + ket).scrollTop(0);
                    $('#warn_' + ket).show().fadeOut(5000);
                    $('#msg_' + ket).text(res.msg);
                }
                detailGizi(jenis);
            }
        });
    }
}

function showKetGizi(val, ket) {
    if (val == "Ada" || val == "Lain-Lain" || "Bermasalah" == val || "Kurang" == val) {
        $('#form_' + ket).show();
    } else {
        $('#form_' + ket).hide();
    }
}

function isCek(id, form) {
    if ($('#' + id).is(':checked')) {
        $('#' + form).show();
    } else {
        $('#' + form).hide();
    }
}

function isCekMulti(id, form, x) {
    if ($('#' + id).is(':checked')) {
        $('#form_gizi161').show();
        $('#form_gizi163').show();
    } else {
        $('#form_gizi161').hide();
        $('#form_gizi163').hide();
    }
}

function setSkriningGizi(umur) {
    var res = '<div class="row">\n' +
        '    <div class="form-group">\n' +
        '        <label class="col-md-8">1. Apakah pasien tampak kurus ?</label>\n' +
        '        <div class="col-md-2">\n' +
        '            <div class="custom02">\n' +
        '                <input type="radio" value="Ya|1" id="gz11" name="gz1" /><label for="gz11">Ya</label>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="col-md-2">\n' +
        '            <div class="custom02">\n' +
        '                <input type="radio" value="Tidak|0" id="gz12" name="gz1" /><label for="gz12">Tidak</label>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<input type="hidden" id="tipe_gizi" value="anak_anak">'+
        '<hr class="garis">\n' +
        '<div class="row">\n' +
        '    <div class="form-group">\n' +
        '        <label class="col-md-8">2. Apakah terdapat penurunan BB selama 1 bulan terakhir ?</label>\n' +
        '        <div class="col-md-2">\n' +
        '            <div class="custom02">\n' +
        '                <input type="radio" value="Ya|2" id="gz21" name="gz2" /><label for="gz21">Ya</label>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="col-md-2">\n' +
        '            <div class="custom02">\n' +
        '                <input type="radio" value="Tidak|0" id="gz22" name="gz2" /><label for="gz22">Tidak</label>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="row" style="font-size: 12px; margin-left: 5px">\n' +
        '    <div class="form-group">\n' +
        '        <label class="col-md-8">(-Berdasarkan penilaian obyektif data BB bila ada atau penilaian subyektif orang tua pasien</label>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="row" style="font-size: 12px; margin-left: 5px">\n' +
        '    <div class="form-group">\n' +
        '        <label class="col-md-8">-Untuk bayi < 1 th berat badan tidak naik selama 3 bulan terakhir)</label>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<hr class="garis">\n' +
        '<div class="row">\n' +
        '    <div class="form-group">\n' +
        '        <label class="col-md-8">3. Apakah terdapat SALAH SATU dari kondisi tersebut ?</label>\n' +
        '        <div class="col-md-2">\n' +
        '            <div class="custom02">\n' +
        '                <input type="radio" value="Ya|1" id="gz31" name="gz3" /><label for="gz31">Ya</label>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="col-md-2">\n' +
        '            <div class="custom02">\n' +
        '                <input type="radio" value="Tidak|0" id="gz32" name="gz3" /><label for="gz32">Tidak</label>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="row" style="font-size: 12px; margin-left: 5px">\n' +
        '    <div class="form-group">\n' +
        '        <label class="col-md-8">(-Diare ≥ 5 kali/hari dan/atau muntah > 3 kali/hari dalam seminggu terakhir</label>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="row" style="font-size: 12px; margin-left: 5px">\n' +
        '    <div class="form-group">\n' +
        '        <label class="col-md-8">−Asupan makanan berkurang selama 1 minggu terakhir)</label>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<hr class="garis">\n' +
        '<div class="row">\n' +
        '    <div class="form-group">\n' +
        '        <label class="col-md-8">4. Apakah terdapat penyakit atau keadaan yang mengakibatkan pasien beresiko mengalami malnutrisi ?</label>\n' +
        '        <div class="col-md-2">\n' +
        '            <div class="custom02">\n' +
        '                <input type="radio" value="Ya|2" id="gz41" name="gz4" /><label for="gz41">Ya</label>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="col-md-2">\n' +
        '            <div class="custom02">\n' +
        '                <input type="radio" value="Tidak|0" id="gz42" name="gz4" /><label for="gz42">Tidak</label>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<hr class="garis">\n' +
        '<div class="row">\n' +
        '    <div class="form-group">\n' +
        '        <label class="col-md-8">Penyakit : *optional</label>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="row">\n' +
        '    <div class="form-group">\n' +
        '        <div class="col-md-4">\n' +
        '            <div class="form-check">\n' +
        '                <input type="checkbox" name="gizi5" id="gizi51" value="Diare Kronis">\n' +
        '                <label for="gizi51"></label> Diare Kronis\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="col-md-4">\n' +
        '            <div class="form-check">\n' +
        '                <input type="checkbox" name="gizi5" id="gizi52" value="HIV">\n' +
        '                <label for="gizi52"></label> HIV\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="col-md-4">\n' +
        '            <div class="form-check">\n' +
        '                <input type="checkbox" name="gizi5" id="gizi53" value="PJB">\n' +
        '                <label for="gizi53"></label> PJB\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="row">\n' +
        '    <div class="form-group">\n' +
        '        <div class="col-md-4">\n' +
        '            <div class="form-check">\n' +
        '                <input type="checkbox" name="gizi5" id="gizi54" value="Hepato">\n' +
        '                <label for="gizi54"></label> Hepato\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="col-md-4">\n' +
        '            <div class="form-check">\n' +
        '                <input type="checkbox" name="gizi5" id="gizi55" value="Ginjal">\n' +
        '                <label for="gizi55"></label> Ginjal\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="col-md-4">\n' +
        '            <div class="form-check">\n' +
        '                <input type="checkbox" name="gizi5" id="gizi56" value="Stoma">\n' +
        '                <label for="gizi56"></label> Stoma\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="row">\n' +
        '    <div class="form-group">\n' +
        '        <div class="col-md-4">\n' +
        '            <div class="form-check">\n' +
        '                <input onclick="isCek(this.id, \'form_gizi5\')" type="checkbox" name="gizi5" id="gizi57">\n' +
        '                <label for="gizi57"></label> Lain-Lain\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="col-md-8" style="display: none" id="form_gizi5">\n' +
        '            <input class="form-control" placeholder="Sebutkan" oninput="$(\'#gizi57\').val(\'\'); $(\'#gizi57\').val(this.value);">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>';

    if (parseInt(umur) >= 16) {
        res = '<div class="row">\n' +
            '    <div class="form-group">\n' +
            '        <label class="col-md-9">1. Apakah pasien mengalami penuruanan / peningkatan BB yang tidak di inginkan dalam 6 bulan terakhir ?</label>\n' +
            '        <div class="col-md-1">\n' +
            '            <div class="custom02">\n' +
            '                <input onclick="cekGizi(\'Ya\', \'form-penurunan\')" type="radio" value="Ya|2" id="gz11" name="gz1" /><label for="gz11">Ya</label>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '        <div class="col-md-2">\n' +
            '            <div class="custom02">\n' +
            '                <input onclick="cekGizi(\'Tidak\', \'form-penurunan\')" type="radio" value="Tidak|0" id="gz12" name="gz1" /><label for="gz12">Tidak</label>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<input id="tipe_gizi" type="hidden" value="dewasa">'+
            '<div class="row" style="display: none;" id="form-penurunan">\n' +
        '                            <div class="form-group">\n' +
        '                                <div class="col-md-3">\n' +
        '                                    <div class="custom02" style="margin-top: 7px">\n' +
        '                                        <input type="radio" value="1 - 5 kg" id="aud_penurunan1" name="penurunan" /><label for="aud_penurunan1">1 - 5 kg</label>\n' +
        '                                    </div>\n' +
        '                                </div>\n' +
        '                                <div class="col-md-3">\n' +
        '                                    <div class="custom02" style="margin-top: 7px">\n' +
        '                                        <input type="radio" value="6 - 10 kg" id="aud_penurunan2" name="penurunan" /><label for="aud_penurunan2">6 - 10 kg</label>\n' +
        '                                    </div>\n' +
        '                                </div>\n' +
        '                                <div class="col-md-3">\n' +
        '                                    <div class="custom02" style="margin-top: 7px">\n' +
        '                                        <input type="radio" value="11 - 15 kg" id="aud_penurunan3" name="penurunan" /><label for="aud_penurunan3">11 - 15 kg</label>\n' +
        '                                    </div>\n' +
        '                                </div>\n' +
        '                                <div class="col-md-3">\n' +
        '                                    <div class="custom02" style="margin-top: 7px">\n' +
        '                                        <input type="radio" value="> 15 kg" id="aud_penurunan4" name="penurunan" /><label for="aud_penurunan4">> 15 kg</label>\n' +
        '                                    </div>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </div>'+
            '<hr class="garis">\n' +
            '<div class="row">\n' +
            '    <div class="form-group">\n' +
            '        <label class="col-md-9">2. Apakah asupan makan berkurang karena tidak nafsu makan ?</label>\n' +
            '        <div class="col-md-1">\n' +
            '            <div class="custom02">\n' +
            '                <input type="radio" value="Ya|1" id="gz21" name="gz2" /><label for="gz21">Ya</label>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '        <div class="col-md-2">\n' +
            '            <div class="custom02">\n' +
            '                <input type="radio" value="Tidak|0" id="gz22" name="gz2" /><label for="gz22">Tidak</label>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<hr class="garis">\n' +
            '<div class="row">\n' +
            '    <div class="form-group">\n' +
            '        <label class="col-md-9">3. Pasien dengan diagnosa khusus / kondisi khusus ?</label>\n' +
            '        <div class="col-md-1">\n' +
            '            <div class="custom02">\n' +
            '                <input onclick="cekGizi(\'Ya\', \'form-penyakit\')" type="radio" value="Ya|3" id="gz31" name="gz3" /><label for="gz31">Ya</label>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '        <div class="col-md-2">\n' +
            '            <div class="custom02">\n' +
            '                <input onclick="cekGizi(\'Tidak\', \'form-penyakit\')" type="radio" value="Tidak|0" id="gz32" name="gz3" /><label for="gz32">Tidak</label>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<div class="row" id="form-penyakit" style="display: none">\n' +
            '    <div class="form-group">\n' +
            '        <label class="col-md-3">Penyakit : </label>\n' +
            '        <div class="col-md-9">\n' +
            '            <div class="custom02">\n' +
            '                <input type="radio" onclick="showKetGizi(this.value, \'gz4\')" value="DM/Kemoterapi" id="gz41" name="gz4" /><label for="gz41">DM/Kemoterapi</label>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '        <div class="col-md-offset-3 col-md-9">\n' +
            '            <div class="custom02">\n' +
            '                <input type="radio" onclick="showKetGizi(this.value, \'gz4\')" value="Hemodialisa/Imunitas Menurun" id="gz42" name="gz4" /><label for="gz42">Hemodialisa/Imunitas Menurun</label>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '        <div class="col-md-offset-3 col-md-9">\n' +
            '            <div class="custom02">\n' +
            '                <input type="radio" onclick="showKetGizi(this.value, \'gz4\')" value="Lain-Lain" id="gz43" name="gz4" /><label for="gz43">Lain-Lain</label>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<div class="row" style="display: none" id="form_gz4">\n' +
            '    <div class="form-group">\n' +
            '        <div class="col-md-offset-3 col-md-9">\n' +
            '            <input class="form-control" id="ket_gz4" placeholder="Keterangan">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>';
    }
    $('#set_skrining_gizi').html(res);
}

function setPengkajianGizi(umur) {
    var res = '<div class="row jarak">\n' +
        '    <div class="col-md-3">\n' +
        '        <span>BB<small><b>(Kg)</b></small></span>\n' +
        '        <input class="form-control" id="gizi1" type="number">\n' +
        '    </div>\n' +
        '    <div class="col-md-3">\n' +
        '        <span>TB<small><b>(Cm)</b></small></span>\n' +
        '        <input class="form-control" id="gizi2" type="number">\n' +
        '    </div>\n' +
        '    <div class="col-md-3">\n' +
        '        <span>LLA<small><b>(Cm)</b></small></span>\n' +
        '        <input class="form-control" id="gizi3" type="number">\n' +
        '    </div>\n' +
        '    <div class="col-md-3">\n' +
        '        <span>BBI</span>\n' +
        '        <input class="form-control" id="gizi4" type="number">\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="row jarak">\n' +
        '    <div class="col-md-3">\n' +
        '        <span>%BBI</span>\n' +
        '        <input class="form-control" id="gizi5" type="number">\n' +
        '    </div>\n' +
        '    <div class="col-md-3">\n' +
        '        <span>BB/U</span>\n' +
        '        <input class="form-control" id="gizi6" type="number">\n' +
        '    </div>\n' +
        '    <div class="col-md-3">\n' +
        '        <span>TB/U</span>\n' +
        '        <input class="form-control" id="gizi7" type="number">\n' +
        '    </div>\n' +
        '    <div class="col-md-3">\n' +
        '        <span>BB/TB</span>\n' +
        '        <input class="form-control" id="gizi8" type="number">\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="row jarak">\n' +
        '    <div class="col-md-3">\n' +
        '        <span>LLA/U</span>\n' +
        '        <input class="form-control" id="add_gizi8" type="number">\n' +
        '    </div>\n' +
        '</div>';
    if (parseInt(umur) >= 5) {
        res = '<div class="row jarak">\n' +
            '    <div class="col-md-3">\n' +
            '        <span>BB<small><b>(Kg)</b></small></span>\n' +
            '        <input class="form-control" id="gizi1" type="number">\n' +
            '    </div>\n' +
            '    <div class="col-md-3">\n' +
            '        <span>TB<small><b>(Cm)</b></small></span>\n' +
            '        <input class="form-control" id="gizi2" type="number">\n' +
            '    </div>\n' +
            '    <div class="col-md-3">\n' +
            '        <span>BBI<small><b>(Kg)</b></small></span>\n' +
            '        <input class="form-control" id="gizi3" type="number">\n' +
            '    </div>\n' +
            '    <div class="col-md-3">\n' +
            '        <span>IMT<small><b>(Kg/m<sup>2</sup>)</b></small></span>\n' +
            '        <input class="form-control" id="gizi4" type="number">\n' +
            '    </div>\n' +
            '</div>\n' +
            '<div class="row jarak">\n' +
            '    <div class="col-md-3">\n' +
            '        <span>TL<small><b>(Cm)</b></small></span>\n' +
            '        <input class="form-control" id="gizi5" type="number">\n' +
            '    </div>\n' +
            '    <div class="col-md-3">\n' +
            '        <span>TB Est<small><b>(Cm)</b></small></span>\n' +
            '        <input class="form-control" id="gizi6" type="number">\n' +
            '    </div>\n' +
            '    <div class="col-md-3">\n' +
            '        <span>LLA<small><b>(Cm)</b></small></span>\n' +
            '        <input class="form-control" id="gizi7" type="number">\n' +
            '    </div>\n' +
            '    <div class="col-md-3">\n' +
            '        <span>%LLA<small><b>(%)</b></small></span>\n' +
            '        <input class="form-control" id="gizi8" type="number">\n' +
            '    </div>\n' +
            '</div>';
    }
    $('#set_pengakajian').html(res);
}


function cekGizi(val, form){
    if(val == "Ya"){
        $('#'+form).show();
    }else{
        $('#'+form).hide();
    }
}