function showModalRj(jenis) {
    if (isReadRM) {
        $('.btn-hide').hide();
    } else {
        $('.btn-hide').show();

        if ("privasi" == jenis) {
            $('.nama-pasien').val(namaPasien);
            $('.no-rm').val(idPasien);
            $('.tgl').val(tglLahir);
            $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
        }

        var jam = $('.jam').length;
        var tgl = $('.tgl').length;

        if (jam > 0) {
            $('.jam').timepicker();
            $('.jam').val(converterDateTime(new Date()));
        }
        if (tgl > 0) {
            $('.tgl').datepicker({
                dateFormat: 'dd-mm-yy'
            });
            $('.tgl').val(converterDate(new Date()));
            $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
        }
    }

    if ("ringkasan_rj" == jenis) {
        listRekamMedisRJ();
    }

    $('#modal-rj-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveRekamMedisRJ(jenis, ket) {
    var data = "";
    var va1 = $('#rj1').val();
    var va2 = $('#rj2').val();
    var va3 = $('#rj3').val();
    var va4 = $('#rj4').val();
    var va5 = $('#rj5').val();
    var va6 = $('#rj6').val();

    if (va1 && va2 && va3 && va4 && va5 && va6 != '') {
        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1.split("-").reverse().join("-") + ' ' + va2 + ':00',
            'anamnese': va3,
            'pemeriksaan_fisik': va4,
            'diagnosa': va5,
            'planing': va6,
            'keterangan': jenis
        }

        var result = JSON.stringify(data);

        $('#save_rj_' + jenis).hide();
        $('#load_rj_' + jenis).show();
        dwr.engine.setAsync(true);
        RekamMedisRawatJalanAction.saveRekamMedisRJ(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_rj_' + jenis).show();
                    $('#load_rj_' + jenis).hide();
                    $('#modal-rj-' + jenis).modal('hide');
                    $('#warning_rj_' + ket).show().fadeOut(5000);
                    $('#msg_rj_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-rj-' + jenis).scrollTop(0);
                    listRekamMedisRJ();
                } else {
                    $('#save_rj_' + jenis).show();
                    $('#load_rj_' + jenis).hide();
                    $('#warning_rj_' + jenis).show().fadeOut(5000);
                    $('#msg_rj_' + jenis).text(res.msg);
                    $('#modal-rj-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#save_rj_' + jenis).show();
        $('#load_rj_' + jenis).hide();
        $('#warning_rj_' + jenis).show().fadeOut(5000);
        $('#msg_rj_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-rj-' + jenis).scrollTop(0);
    }
}

function listRekamMedisRJ() {
    var table = "";
    RekamMedisRawatJalanAction.getListRekamMedisRJ(idDetailCheckup, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                table += '<tr>' +
                    '<td>' + converterDateTime(item.waktu) + '</td>' +
                    '<td>' + cekNull(item.anamnese) + '</td>' +
                    '<td>' + cekNull(item.pemeriksaanFisik) + '</td>' +
                    '<td>' + cekNull(item.diagnosa) + '</td>' +
                    '<td>' + cekNull(item.planing) + '</td>' +
                    '</tr>';
            });
            $('#body_ringkasan_rj').html(table);
        }
    });
}

function cekNull(item) {
    var res = "";
    if (item != null) {
        res = item;
    }
    return res;
}
