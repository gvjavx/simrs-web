function saveRA(jenis, ket, tipe) {
    var data = [];
    var dataPasien = {
        'no_checkup' : noCheckup,
        'id_detail_checkup' : idDetailCheckup,
        'id_pasien' : idPasien,
        'id_rm' : tempidRm
    };

    var diagnosis = $('[name=diag]');
    var hasil = $('[name=hasil]');
    var inter = $('[name=inter]');
    var imple = $('[name=imple]');
    var eva = $('[name=eva]');
    var ttdPerawat = document.getElementById("ttd_perawat");
    var tgl = $('.tgl').val();
    var jam = $('.jam').val();
    var namaTerang = $('#nama_terang_perawat').val();

    var tempDiag = "";
    var tempHasil = "";
    var tempInter = "";
    var tempImple = "";
    var tempEva = "";

    $.each(diagnosis, function (i, item) {
        var val = "";
        if (item.type == 'checkbox') {
            if (item.checked) {
                val = item.value;
            }
        } else {
            if (item.value != '') {
                val = item.value;
            }
        }

        if (val != '') {
            if (tempDiag != '') {
                tempDiag = tempDiag + '|' + val;
            } else {
                tempDiag = val;
            }
        }
    });

    $.each(hasil, function (i, item) {
        var val = "";
        if (item.type == 'checkbox') {
            if (item.checked) {
                val = item.value;
            }
        } else {
            if (item.value != '') {
                val = item.value;
            }
        }

        if (val != '') {
            if (tempHasil != '') {
                tempHasil = tempHasil + '|' + val;
            } else {
                tempHasil = val;
            }
        }
    });

    $.each(inter, function (i, item) {
        var val = "";
        if (item.type == 'checkbox') {
            if (item.checked) {
                val = item.value;
            }
        } else {
            if (item.value != '') {
                val = item.value;
            }
        }

        if (val != '') {
            if (tempInter != '') {
                tempInter = tempInter + '|' + val;
            } else {
                tempInter = val;
            }
        }
    });

    $.each(imple, function (i, item) {
        var val = "";
        if (item.type == 'checkbox') {
            if (item.checked) {
                val = item.value;
            }
        } else {
            if (item.value != '') {
                val = item.value;
            }
        }

        if (val != '') {
            if (tempImple != '') {
                tempImple = tempImple + '|' + val;
            } else {
                tempImple = val;
            }
        }
    });

    $.each(eva, function (i, item) {
        var val = "";
        if (item.type == 'checkbox') {
            if (item.checked) {
                val = item.value;
            }
        } else {
            if (item.value != '') {
                val = item.value;
            }
        }

        if (val != '') {
            if (tempEva != '') {
                tempEva = tempEva + '|' + val;
            } else {
                tempEva = val;
            }
        }
    });

    var cekTtd = isCanvasBlank(ttdPerawat);

    if (tgl && jam && tempDiag && tempHasil && tempInter && tempImple && tempEva && namaTerang != '' && !cekTtd) {

        var ttd = ttdPerawat.toDataURL("image/png"),
            ttd = ttd.replace(/^data:image\/(png|jpg);base64,/, "");

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': tgl + ' ' + jam,
            'diagnosa': tempDiag,
            'hasil': tempHasil,
            'intervensi': tempInter,
            'implementasi': tempImple,
            'evaluasi': tempEva,
            'keterangan': jenis,
            'nama_terang': namaTerang,
            'ttd_perawat': ttd
        }

        var result = JSON.stringify(data);
        var pasienData = JSON.stringify(dataPasien);

        $('#save_ina_' + jenis).hide();
        $('#load_ina_' + jenis).show();
        dwr.engine.setAsync(true);
        RencanaAsuhanKeperawatanAction.save(result, pasienData, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#modal-ina-' + jenis).modal('hide');
                    $('#warning_ina_' + ket).show().fadeOut(5000);
                    $('#msg_ina_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-ina-' + jenis).scrollTop(0);
                    listAsuhanKeperawatan(ket);
                } else {
                    $('#save_ina_' + jenis).show();
                    $('#load_ina_' + jenis).hide();
                    $('#warning_ina_' + jenis).show().fadeOut(5000);
                    $('#msg_ina_' + jenis).text(res.msg);
                    $('#modal-ina-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#save_ina_' + jenis).show();
        $('#load_ina_' + jenis).hide();
        $('#warning_ina_' + jenis).show().fadeOut(5000);
        $('#msg_ina_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-ina-' + jenis).scrollTop(0);
    }
}

function detailRA(jenis, ket, tipe) {
    var head = "";
    var body = "";
    var first = "";
    var last = "";
    var cekData = false;
    RencanaAsuhanKeperawatanAction.getListDetail(idDetailCheckup, jenis,  function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                var diagnosa = "";
                var hasil = "";
                var intervensi = "";
                var implementasi = "";
                var evaluasi = "";

                var ul1 = "";
                var ul2 = "";
                var ul3 = "";
                var ul4 = "";
                var ul5 = "";

                if (item.diagnosa != null) {
                    var v = item.diagnosa.split("|");
                    $.each(v, function (i, item) {
                        diagnosa += '<li>' + item + '</li>';
                    });
                }

                if (item.hasil != null) {
                    var v = item.hasil.split("|");
                    $.each(v, function (i, item) {
                        hasil += '<li>' + item + '</li>';
                    });
                }

                if (item.intervensi != null) {
                    var v = item.intervensi.split("|");
                    $.each(v, function (i, item) {
                        intervensi += '<li>' + item + '</li>';
                    });
                }

                if (item.implementasi != null) {
                    var v = item.implementasi.split("|");
                    $.each(v, function (i, item) {
                        implementasi += '<li>' + item + '</li>';
                    });
                }

                if (item.evaluasi != null) {
                    var v = item.evaluasi.split("|");
                    $.each(v, function (i, item) {
                        evaluasi += '<li>' + item + '</li>';
                    });
                }

                if (item.ttdPerawat != null) {
                    evaluasi += '<li style="list-style-type: none; margin-top: 10px">' + '<label>Perawat</label><img style="width: 50px; height: 20px" src="' + item.ttdPerawat + '">' + '</li>';
                }

                var idAsuhan = item.idRencanaAsuhanKeperawatan;

                if (diagnosa != '') {
                    ul1 = '<ul style="margin-left: 12px">' + diagnosa + '</ul>'
                }
                if (hasil != '') {
                    ul2 = '<ul style="margin-left: 12px">' + hasil + '</ul>'
                }
                if (intervensi != '') {
                    ul3 = '<ul style="margin-left: 12px">' + intervensi + '</ul>'
                }
                if (implementasi != '') {
                    ul4 = '<ul style="margin-left: 12px">' + implementasi + '</ul>'
                }
                if (evaluasi != '') {
                    ul5 = '<ul style="margin-left: 12px">' + evaluasi + '</ul>'
                }

                body += '<tr>' +
                    '<td>' + item.waktu + '</td>' +
                    '<td>' + ul1 + '</td>' +
                    '<td>' + ul2 + '</td>' +
                    '<td>' + ul3 + '</td>' +
                    '<td>' + ul4 + '</td>' +
                    '<td>' + ul5 + '</td>' +
                    '<td align="center">' + '<i id="delete_'+idAsuhan+'" onclick="conRA(\''+jenis+'\', \''+ket+'\', \''+idAsuhan+'\')" class="fa fa-trash hvr-grow" style="color: red"></i>' + '</td>' +
                    '</tr>';
            });
            cekData = true;
        }else{
            body = '<tr>' +
                '<td>Data belum ada</td>' +
                '</tr>';
        }

        if (cekData) {
            head = '<tr style="font-weight: bold">' +
                '<td width="13%">Tanggal Jam</td>' +
                '<td>Diagnosa Keperawatan</td>' +
                '<td>Hasil Luaran Keperawatan</td>' +
                '<td>Planning/ Rencana Tindakan</td>' +
                '<td>Implementasi</td>' +
                '<td>Evaluasi</td>' +
                '<td align="center">Action</td>' +
                '</tr>'
        }

        var table = '<table style="font-size: 12px" class="table table-bordered">' +
            '<thead>' + head + '</thead>' +
            '<tbody>' + body + '</tbody>' +
            '</table>';

        var newRow = $('<tr id="del_'+tipe+'_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
        newRow.insertAfter($('table').find('#row_'+tipe+'_' + jenis));
        var url = contextPath + '/pages/images/minus-allnew.png';
        $('#btn_'+tipe+'_' + jenis).attr('src', url);
        $('#btn_'+tipe+'_' + jenis).attr('onclick', 'delRowRA(\'' + jenis + '\',\''+ket+'\', \''+tipe+'\')');

    });
}

function delRowRA(jenis, ket, tipe) {
    $('#del_'+tipe+'_' + jenis).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_'+tipe+'_' + jenis).attr('src', url);
    $('#btn_'+tipe+'_' + jenis).attr('onclick', 'detailRA(\'' + jenis + '\',\''+ket+'\',\''+tipe+'\')');
}

function cekNullRA(item) {
    var res = "";
    if (item != null) {
        res = item;
    }
    return res;
}

function conRA(jenis, ket, tipe, id){
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show:true, backdrop:'static'});
    $('#save_con_rm').attr('onclick', 'delRA(\''+jenis+'\', \''+ket+'\',\''+tipe+'\', \''+id+'\')');
}

function delRA(jenis, ket, tipe, id) {
    $('#modal-confirm-rm').modal('hide');
    var dataPasien = {
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    };

    var result = JSON.stringify(dataPasien);
    startIconSpin('delete_'+id);
    dwr.engine.setAsync(true);
    RencanaAsuhanKeperawatanAction.saveDelete(id, result, {
        callback: function (res) {
            if (res.status == "success") {
                stopIconSpin('delete_'+id);
                $('#warning_'+tipe+'_' + ket).show().fadeOut(5000);
                $('#msg_'+tipe+'_' + ket).text("Berhasil menghapus data...");
            } else {
                stopSpin('delete_'+id);
                $('#warn_'+ket).show().fadeOut(5000);
                $('#msg_'+ket).text(res.msg);
            }
        }
    });
}