function pengkajianFisioterapi(jenis, idRM, isSetIdRM) {
    if (isSetIdRM == "Y") {
        tempidRm = idRM;
    }
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }
    $('#modal-pengkajian-fisioterapi').modal({show: true, backdrop: 'static'});
    setDataPasien();
}

function addFisioterapi(jenis) {
    if (jenis != '') {
        if ("nyeri" == jenis) {
            setNyeri('set_' + jenis, umur);
        }
        if ("jatuh" == jenis) {
            setResikoJatuh('set_' + jenis, umur);
        }
        $('#modal-fisio-' + jenis).modal({show: true, backdrop: 'static'});
        setDataPasien();
    }
}

function saveFisio(jenis) {
    var data = [];
    var cek = false;
    var dataPasien = "";

    dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }

    if ("keadaan_umum" == jenis) {
        var darah = $('#f_darah').val();
        var nadi = $('#f_nadi').val();
        var nafas = $('#f_nafas').val();
        var suhu = $('#f_suhu').val();
        var berat = $('#f_berat').val();
        var tinggi = $('#f_tinggi').val();
        var alat = $('#f_alat').val();
        var prothesa = $('#f_prothesa').val();
        var cacat = $('#f_cacat').val();
        var checkbox = document.getElementsByName('cek_adl');
        var adl = "";
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {
                if (adl == "") {
                    adl = checkbox[i].value;
                } else {
                    adl = adl + ', ' + checkbox[i].value;
                }
            }
        }

        if (darah != '' && nadi != '' && nafas != '' && suhu != '' && berat != '' && tinggi != '' && alat != '' && prothesa != '' && cacat != '' && adl != '') {
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban': darah + ' mmHg',
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Frekuensi Nadi',
                'jawaban': nadi + ' X/menit',
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Frekuensi Nafas',
                'jawaban': nafas + ' X/menit',
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': suhu + ' ˚C',
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan',
                'jawaban': berat + ' g/kg',
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tinggi Badan',
                'jawaban': tinggi + ' cm',
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alat Bantu',
                'jawaban': alat,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Prothesa',
                'jawaban': prothesa,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Cacat Tubuh',
                'jawaban': cacat,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({'parameter': 'ADL', 'jawaban': adl, 'keterangan': jenis, 'id_detail_checkup': idDetailCheckup});
            cek = true;
        }
    }

    if ("psikologis" == jenis) {
        var checkbox = document.getElementsByName('cek_psikologis');
        var riwayat = "";
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {
                if (i == 0) {
                    riwayat = checkbox[i].value;
                } else {
                    riwayat = riwayat + ', ' + checkbox[i].value;
                }
            }
        }

        if (riwayat != '') {
            data.push({
                'parameter': 'Riwayat Psikologis',
                'jawaban': riwayat,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("nyeri" == jenis) {
        var va1 = $('[name=radio_nyeri_keluhan]:checked').val();
        var va2 = $('#y_lokasi').val();
        var va3 = $('[name=radio_nyeri_jenis]:checked').val();
        var va4 = $('#y_inten').val();
        var jen = $('#temp_jenis').val();
        var nyeri = "";
        var tipe = "";
        if (jen == "emoji") {
            nyeri = document.getElementById('choice_emoji');
            tipe = "Wong Baker Pain Scale";
        } else {
            nyeri = document.getElementById('area_nyeri');
            tipe = "Nomeric Rating Scale";
        }

        if (va1 && va3 != undefined && va2 && va4 != '') {
            data.push({
                'parameter': 'Apakah terdapat keluhan nyeri',
                'jawaban': va1,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban': va2,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis',
                'jawaban': va3,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Intensitas',
                'jawaban': va4,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });

            var canv1 = nyeri.toDataURL("image/png"),
                canv1 = canv1.replace(/^data:image\/(png|jpg);base64,/, "");
            data.push({
                'parameter': tipe,
                'jawaban': canv1,
                'keterangan': jenis,
                'tipe': 'gambar',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("jatuh" == jenis) {
        var tgl = $('#ps01').val();
        var jam = $('#ps02').val();
        data.push({
            'parameter': 'Tanggal Jam',
            'jawaban': tgl + ' ' + jam,
            'keterangan': jenis,
            'id_detail_checkup': idDetailCheckup
        });
        var resikoJatuh = $('.resiko_jatuh');
        var jenisResiko = $('#jenis_resiko').val();
        var totalSkor = "";
        if (resikoJatuh.length > 0) {
            $.each(resikoJatuh, function (i, item) {
                var label = $('#label_resiko_jatuh' + i).text();
                var resiko = $('[name=resiko_jatuh' + i + ']:checked').val();
                if (resiko != undefined) {
                    var isi = resiko.split("|")[0];
                    var skor = resiko.split("|")[1];
                    data.push({
                        'parameter': label,
                        'jawaban': isi,
                        'skor': skor,
                        'keterangan': jenis,
                        'id_detail_checkup': idDetailCheckup
                    });
                    if (totalSkor != '') {
                        totalSkor = parseInt(totalSkor) + parseInt(skor);
                    } else {
                        totalSkor = parseInt(skor);
                    }
                }
            });

            if (totalSkor != '') {
                data.push({
                    'parameter': 'Total Skor',
                    'jawaban': '' + totalSkor,
                    'keterangan': jenis,
                    'tipe': 'total',
                    'id_detail_checkup': idDetailCheckup
                });

                var kesimpulan = "";

                if (jenisResiko == "humpty_dumpty") {
                    if (parseInt(totalSkor) >= 7 && parseInt(totalSkor) <= 11) {
                        kesimpulan = "Rendah";
                    } else if (parseInt(totalSkor) >= 12) {
                        kesimpulan = "Tinggi";
                    }
                } else if (jenisResiko == "skala_morse") {
                    if (parseInt(totalSkor) >= 0 && parseInt(totalSkor) <= 24) {
                        kesimpulan = "Rendah";
                    } else if (parseInt(totalSkor) >= 25 && parseInt(totalSkor) <= 44) {
                        kesimpulan = "Sedang";
                    } else if (parseInt(totalSkor) >= 45) {
                        kesimpulan = "Tinggi";
                    }
                } else if (jenisResiko == "geriatri") {
                    if (parseInt(totalSkor) >= 0 && parseInt(totalSkor) <= 5) {
                        kesimpulan = "Rendah";
                    } else if (parseInt(totalSkor) >= 6 && parseInt(totalSkor) <= 16) {
                        kesimpulan = "Sedang";
                    } else if (parseInt(totalSkor) >= 17) {
                        kesimpulan = "Tinggi";
                    }
                }
                data.push({
                    'parameter': 'Resiko Jatuh',
                    'jawaban': kesimpulan,
                    'keterangan': jenis,
                    'tipe': 'kesimpulan',
                    'id_detail_checkup': idDetailCheckup
                });
            }
        }
        if (resikoJatuh.length == data.length - 3) {
            cek = true;
        }
    }

    if ("pengkajian" == jenis) {
        var keluhan = $('#p_keluhan').val();
        var sekarang = $('#p_penyakit_sekarang').val();
        var dahulu = $('#p_penyakit_dahulu ').val();
        var fisik = $('#p_fisik ').val();
        var penunjang = $('#p_penunjang').val();
        var assesment = $('#p_assesment').val();
        var terapi = $('#p_terapi').val();

        if (keluhan != '' && sekarang != '' && dahulu != '' && fisik != '' && penunjang != '' && assesment != '' && terapi != '') {
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': keluhan,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Sekarang',
                'jawaban': sekarang,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit Dahulu',
                'jawaban': dahulu,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Fisik',
                'jawaban': fisik,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Penunjang',
                'jawaban': penunjang,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Assesment',
                'jawaban': assesment,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Terapi/tindakan',
                'jawaban': terapi,
                'keterangan': jenis,
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        var pasienData = JSON.stringify(dataPasien);
        $('#save_' + jenis).hide();
        $('#load_' + jenis).show();
        dwr.engine.setAsync(true);
        FisioterapiAction.saveFisio(result, pasienData, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_' + jenis).show();
                    $('#load_' + jenis).hide();
                    $('#modal-fisio-' + jenis).modal('hide');
                    $('#warning_pengkajian_pengkajian').show().fadeOut(5000);
                    $('#msg_pengkajian_pengkajian').text("Berhasil menambahkan data fisioterapi...");
                    getListRekamMedis('rawat_jalan', tipePelayanan, idDetailCheckup);
                } else {
                    $('#save_' + jenis).show();
                    $('#load_' + jenis).hide();
                    $('#warning_' + jenis).show().fadeOut(5000);
                    $('#msg_' + jenis).text(res.msg);
                }
            }
        });
    } else {
        $('#warning_' + jenis).show().fadeOut(5000);
        $('#msg_' + jenis).text("Silahkan cek kembali inputan anda...!");
    }
}

function detailFisio(jenis) {
    if (jenis != '') {
        var body = "";
        var cekData = false;
        var head = "";
        FisioterapiAction.getListFisioTerapi(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {

                $.each(res, function (i, item) {

                    if ("psikologis" == item.keterangan) {
                        var jwb = "";
                        var li = "";
                        if (item.jawaban != null) {
                            jwb = item.jawaban.split(",");
                            $.each(jwb, function (i, item) {
                                li += '<li>' + item + '</li>'
                            });
                        }
                        body += '<tr>' +
                            '<td width="40%">' + item.parameter + '</td>' +
                            '<td>' + '<ul style="margin-left: 10px;">' + li + '</ul>' + '</td>' +
                            '</tr>';
                    } else if ("jatuh" == jenis) {
                        if ("total" == item.tipe) {
                            body += '<tr>' +
                                '<td width="40%" colspan="2">' + item.parameter + '</td>' +
                                '<td>' + item.jawaban + '</td>' +
                                '</tr>';
                        } else if ("kesimpulan" == item.tipe) {
                            body += '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                                '<td width="40%" colspan="2">' + item.parameter + '</td>' +
                                '<td>' + item.jawaban + '</td>' +
                                '</tr>';
                        } else {
                            body += '<tr>' +
                                '<td width="40%">' + item.parameter + '</td>' +
                                '<td>' + item.jawaban + '</td>' +
                                '<td>' + cekItemIsNull(item.skor) + '</td>' +
                                '</tr>';
                        }
                    } else {
                        body += '<tr>' +
                            '<td width="40%">' + item.parameter + '</td>' +
                            '<td>' + item.jawaban + '</td>' +
                            '</tr>';
                    }

                    cekData = true;
                });

            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }

            if(cekData){
                if("jatuh" == jenis){
                    head = '<tr style="font-weight: bold">' +
                        '<td>Parameter</td>' +
                        '<td>Jawaban</td>' +
                        '<td>Score</td>' +
                        '</tr>'
                }
            }

            var table = '<table style="font-size: 12px" class="table table-bordered"><tr bgcolor="#ffebcd">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + body + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_' + jenis).attr('src', url);
            $('#btn_' + jenis).attr('onclick', 'delRow(\'' + jenis + '\')');
        });
    }
}

function delRow(id) {
    $('#del_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_' + id).attr('src', url);
    $('#btn_' + id).attr('onclick', 'detailFisio(\'' + id + '\')');
}

function addMonitoringFisioterapi() {
    listMonitoringFisioterapi();
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();
    }

    var tgl = $('.tgl').length;
    if (tgl > 0) {
        $('.tgl').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tgl').val(formaterDate(new Date()));
        $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
    }
    $('#modal-monitoring-kunjungan').modal({show: true, backdrop: 'static'});
}

function listMonitoringFisioterapi() {
    var table = "";
    FisioterapiAction.getKunjunganFisioterapi(idPasien, function (res) {
        $.each(res, function (i, item) {
            table += '<tr>' +
                '<td>' + converterDateTime(item.createdDate) + '</td>' +
                '<td>' + item.tindakan + '</td>' +
                // '<td>' + item.keterangan + '</td>' +
                '<td>' + item.fisioterapi + '</td>' +
                '</tr>';
        });
        $('#body_monitoring_fisioterapi').html(table);
    });
}

function addMonitoringFisio() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0');
    var yyyy = today.getFullYear();
    var tglToday = dd + '-' + mm + '-' + yyyy;
    listTindakanKategoriPoli();
    $('.select2').select2({});
    $('#mon_tanggal').val(tglToday);
    $('#modal-add-monitoring').modal({show: true, backdrop: 'static'});
}

function saveMonitoringFisio() {
    var tanggal = $('#mon_tanggal').val();
    var tindakan = $('#mon_tindakan').val();
    var keterangan = $('#mon_keterangan').val();
    var fisioterapi = $('#mon_fisio').val();
    var data = "";
    if (tanggal != '' && tindakan != '' && keterangan != '' && fisioterapi != '') {
        data = {
            'tanggal': tanggal.split("-").reverse().join("-"),
            'tindakan': tindakan,
            'keterangan': keterangan,
            'fisioterapi': fisioterapi,
            'id_detail_checkup': idDetailCheckup
        }
        var result = JSON.stringify(data);
        $('#save_mon_pengkajian').hide();
        $('#load_mon_pengkajian').show();
        dwr.engine.setAsync(true);
        FisioterapiAction.saveMonFisio(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_mon_pengkajian').show();
                    $('#load_mon_pengkajian').hide();
                    listMonitoringFisioterapi();
                    $('#modal-add-monitoring').modal('hide');
                    $('#warning_monitoring_pengkajian').show().fadeOut(5000);
                    $('#msg_monitoring_pengkajian').text("Berhasil menambahakan data monitoring...");
                } else {
                    $('#save_mon_pengkajian').show();
                    $('#load_mon_pengkajian').hide();
                    $('#warning_mon_fisio').show().fadeOut(5000);
                    $('#msg_mon_fisio').text(res.msg);
                }
            }
        });
    } else {
        $('#warning_mon_fisio').show().fadeOut(5000);
        $('#msg_mon_fisio').text("Silahkan cek kembali data inputan anda...!");
    }
}

function formaterDate(date) {
    var tgl = "";
    if (date != '') {
        var tanggal = new Date(date);
        var dd = String(tanggal.getDate()).padStart(2, '0');
        var mm = String(tanggal.getMonth() + 1).padStart(2, '0');
        var yyyy = tanggal.getFullYear();
        tgl = dd + '-' + mm + '-' + yyyy;
    }
    return tgl;
}

function listTindakanKategoriPoli() {
    var option = "<option value=''>[Select One]</option>";
    CheckupDetailAction.getListComboTindakanKategori(idPoli, function (response) {
        if (response != null) {
            $.each(response, function (i, item) {
                option += "<option value='" + item.idKategoriTindakan + "'>" + item.kategoriTindakan + "</option>";
            });
            $('#fis_ketgori_tindakan').html(option);
        } else {
            $('#fis_ketgori_tindakan').html('');
        }
    });
}

function listTindakanPoli(idKategori) {
    var option = "<option value=''>[Select One]</option>";
    if (idKategori != '') {
        CheckupDetailAction.getListComboTindakan(idKategori, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.tindakan + "'>" + item.tindakan + "</option>";
                });
                $('#mon_tindakan').html(option);
            } else {
                $('#mon_tindakan').html('');
            }
        });
    } else {
        $('#mon_tindakan').html('');
    }
}

function conFS(jenis, ket) {
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show: true, backdrop: 'static'});
    $('#save_con_rm').attr('onclick', 'delFS(\'' + jenis + '\', \'' + ket + '\')');
}

function delFS(jenis, ket) {
    $('#modal-confirm-rm').modal('hide');
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }
    var result = JSON.stringify(dataPasien);
    startSpin('delete_' + jenis);
    dwr.engine.setAsync(true);
    FisioterapiAction.saveDelete(idDetailCheckup, jenis, result, {
        callback: function (res) {
            if (res.status == "success") {
                stopSpin('delete_' + jenis);
                $('#warning_' + ket).show().fadeOut(5000);
                $('#msg_' + ket).text("Berhasil menghapus data...");
            } else {
                stopSpin('delete_' + jenis);
                $('#modal_warning').show().fadeOut(5000);
                $('#msg_warning').text(res.msg);
            }
        }
    });
}