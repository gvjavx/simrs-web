function showModalHD(jenis) {
    if ("pemeriksaan" == jenis) {
        $('#pem2').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    }
    $('#modal-hd-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveMonHD(jenis, ket) {
    console.log(jenis);
    var data = [];
    var cek = false;
    if ("pengkajian" == jenis) {
        var va1 = $('[name=pkj1]:checked').val();
        var tVa1 = "";
        if ("Lain-Lain" == va1) {
            var a = $('#ket_pkj1').val();
            if (a != '') {
                tVa1 = a;
            }
        } else {
            tVa1 = va1;
        }
        var va2 = $('[name=pkj2]:checked').val();
        var va3 = $('#pkj3').val();
        var va4 = $('#pkj4').val();
        var va5 = $('#pkj5').val();
        var va6 = $('[name=pkj6]:checked').val();
        var va7 = $('[name=pkj7]:checked').val();
        var tVa7 = "";
        if ("Lain-Lain" == va7) {
            var b = $('#ket_pkj7').val();
            if (b != '') {
                tVa7 = b;
            }
        } else {
            tVa7 = va7;
        }
        var va8 = $('#pkj8').val();
        var va9 = $('#pkj9').val();
        var va10 = $('#pkj10').val();
        var va11 = $('[name=pkj11]:checked').val();
        var va12 = $('#pkj012').val();
        var va13 = $('[name=pkj13]:checked').val();
        var va14 = $('#pkj014').val();
        var va15 = $('[name=pkj15]:checked').val();
        var tVa15 = "";
        if ("Lain-Lain" == va15) {
            var a = $('#ket_pkj15').val();
            if (a != '') {
                tVa15 = a;
            }
        } else {
            tVa15 = va15;
        }
        var va16 = $('[name=pkj16]:checked').val();
        var va17 = $('#pkj17').val();
        var va18 = $('#pkj18').val();
        var va19 = $('#pkj19').val();
        var va20 = $('[name=pkj20]:checked').val();
        var va21 = $('[name=pkj21]:checked').val();
        var va22 = $('[name=pkj22]:checked').val();

        if (tVa1 && tVa7 && tVa15 && va3 && va4 && va5 && va8 && va9 && va10 && va12 && va14 && va17 && va18 && va19 != '' &&
            va6 && va11 && va13 && va16 && va20 && va21 && va22 && va2 != undefined) {
            data.push({
                'parameter': 'Keluhan Utama',
                'jawaban': tVa1,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skrening Nyeri',
                'jawaban': va2,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skala',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Durasi',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Keadaan Umum',
                'jawaban': tVa7,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban': va8 + ' mmHg',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'MAP',
                'jawaban': va9,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban': va10 + ' C',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban': va11,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Freq Nadi',
                'jawaban': va12 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Respirasi',
                'jawaban': va13,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Freq Respirasi',
                'jawaban': va14 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konjungilva',
                'jawaban': tVa15,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Ekrimitas',
                'jawaban': va16,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'BB Kering',
                'jawaban': va17 + ' Kg',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'BB Post HD',
                'jawaban': va18 + ' Kg',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'BB Pre HD',
                'jawaban': va19 + ' Kg',
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Abdomen',
                'jawaban': va20,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Askes Vaskuler',
                'jawaban': va21,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Lokasi',
                'jawaban': va22,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }
    if ("resiko_jatuh" == jenis) {
        var va1 = $('[name=res1]:checked').val();
        var va2 = $('[name=res2]:checked').val();
        var va3 = $('[name=res3]:checked').val();
        var va4 = $('[name=res4]:checked').val();
        var va5 = $('[name=res5]:checked').val();
        var va6 = $('[name=res6]:checked').val();
        if (va1 && va2 && va3 && va4 && va5 && va6 != undefined) {
            var isi1 = va1.split("|")[0];
            var isi2 = va2.split("|")[0];
            var isi3 = va3.split("|")[0];
            var isi4 = va4.split("|")[0];
            var isi5 = va5.split("|")[0];
            var isi6 = va6.split("|")[0];

            var skor1 = va1.split("|")[1];
            var skor2 = va2.split("|")[1];
            var skor3 = va3.split("|")[1];
            var skor4 = va4.split("|")[1];
            var skor5 = va5.split("|")[1];
            var skor6 = va6.split("|")[1];

            data.push({
                'parameter': 'Riwayat jatuh',
                'jawaban': isi1,
                'skor': skor1,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dalgnosa Medis Sekunder > 1',
                'jawaban': isi2,
                'skor': skor2,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alat bantu jalan',
                'jawaban': isi3,
                'skor': skor3,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Memakai terapi heparin look',
                'jawaban': isi4,
                'skor': skor4,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Cara berjalan/berpindah',
                'jawaban': isi5,
                'skor': skor5,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Status mental',
                'jawaban': isi6,
                'skor': skor6,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }
    if ("pemeriksaan" == jenis) {
        var va1 = $('#pem1').val();
        var va2 = $('#pem2').val();
        var va3 = $('#pem3').val();
        var va4 = $('#pem4').val();
        var va5 = $('[name=pem5]:checked').val();
        var va6 = $('[name=pem6]:checked').val();
        var va7 = $('[name=pem7]:checked').val();
        var va8 = $('[name=pem8]:checked').val();
        if (va1 && va2 && va3 && va3 != '' && va5 && va6 && va7 && va8 != undefined) {
            data.push({
                'parameter': 'Pemeriksaan penunjang (Lab, RX, lain-lain)',
                'jawaban': va1,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal',
                'jawaban': va2.split("-").reverse().join("-"),
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Score',
                'jawaban': va3,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Rekomendasi',
                'jawaban': va4,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Adakah keyakinan / tradisi budaya yang berkaitan dengan pelayanan kesehatan yang akan diberikan',
                'jawaban': va5,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kendala Komunikasi',
                'jawaban': va6,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Yang merawat dirumah',
                'jawaban': va7,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kondisi saat ini',
                'jawaban': va8,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("diagnosa" == jenis) {
        var va1 = $('[name=diag1]:checked');
        var tVa1 = "";
        if (va1 != undefined) {
            $.each(va1, function (i, item) {
                if (va1[i].checked) {
                    if (tVa1 != '') {
                        tVa1 = tVa1 + '|' + va1[i].value;
                    } else {
                        tVa1 = va1[i].value;
                    }
                }
            });
            data.push({
                'parameter': 'Diagnosa Keperawatan',
                'jawaban': tVa1,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("intervensi" == jenis) {
        var va1 = $('[name=inter1]:checked');
        var tVa1 = "";
        if (va1 != undefined) {
            $.each(va1, function (i, item) {
                if (va1[i].checked) {
                    if (tVa1 != '') {
                        tVa1 = tVa1 + '|' + va1[i].value;
                    } else {
                        tVa1 = va1[i].value;
                    }
                }
            });
            data.push({
                'parameter': 'Intervensi Keperawatan',
                'jawaban': tVa1,
                'keterangan': jenis,
                'jenis': 'monitoring_hd',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        $('#save_hd_' + jenis).hide();
        $('#load_hd_' + jenis).show();
        dwr.engine.setAsync(true);
        HemodialisaAction.saveHemodialisa(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_hd_' + jenis).show();
                    $('#load_hd_' + jenis).hide();
                    $('#modal-hd-' + jenis).modal('hide');
                    $('#warning_hd_' + ket).show().fadeOut(5000);
                    $('#msg_hd_' + ket).text("Berhasil menambahkan data hd...");
                    $('#modal-hd-' + jenis).scrollTop(0);
                } else {
                    $('#save_hd_' + jenis).show();
                    $('#load_hd_' + jenis).hide();
                    $('#warning_hd_' + jenis).show().fadeOut(5000);
                    $('#msg_hd_' + jenis).text(res.msg);
                    $('#modal-hd-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_hd_' + jenis).show().fadeOut(5000);
        $('#msg_hd_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-hd-' + jenis).scrollTop(0);
    }
}

function detailMonHD(jenis) {
    if (jenis != '') {
        var head = "";
        var body = "";
        var totalSkor = 0;
        var first = "";
        var last = "";
        HemodialisaAction.getListHemodialisa(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var jwb = "";
                    if (item.jawaban != null) {
                        jwb = item.jawaban;
                    }
                    if ("intervensi" == item.keterangan || "diagnosa" == item.keterangan) {
                        var li = "";
                        var isi = jwb.split("|");
                        $.each(isi, function (i, item) {
                            li += '<li>'+item+'</li>';
                        });
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + '<ul style="margin-left: 10px">' + li + '</ul>' + '</td>' +
                            '</tr>';
                    } else if ("resiko_jatuh" == item.keterangan) {
                        if(item.skor != null){
                            totalSkor = parseInt(totalSkor) + parseInt(item.skor);
                        }
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb +'</td>' +
                            '<td align="center">' + item.skor +'</td>' +
                            '</tr>';
                    } else {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb + '</td>' +
                            '</tr>';
                    }
                });
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }

            if("resiko_jatuh" == jenis){
                last = '<tr style="font-weight: bold"><td colspan="2">Total Skor</td><td align="center">'+totalSkor+'</td></tr>';
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_hd_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_hd_' + jenis));
            var url = contextPath + '/pages/images/cancel-flat-new.png';
            $('#btn_hd_' + jenis).attr('src', url);
            $('#btn_hd_' + jenis).attr('onclick', 'delRowHemodialisa(\'' + jenis + '\')');
        });
    }
}

function delRowHemodialisa(id) {
    $('#del_hd_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_hd_' + id).attr('src', url);
    $('#btn_hd_' + id).attr('onclick', 'detailMonHD(\'' + id + '\')');
}

function showKetPkj1(val) {
    if (val == "Lain-Lain") {
        $('#ket_pkj1').show();
    } else {
        $('#ket_pkj1').hide();
    }
}

function showKetPkj7(val) {
    if (val == "Lain-Lain") {
        $('#ket_pkj7').show();
    } else {
        $('#ket_pkj7').hide();
    }
}

function showKetPkj15(val) {
    if (val == "Lain-Lain") {
        $('#ket_pkj15').show();
    } else {
        $('#ket_pkj15').hide();
    }
}