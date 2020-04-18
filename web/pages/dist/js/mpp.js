function showModalMpp(jenis) {
    if ("identifikasi_resiko" == jenis) {
        $('#ir1').val(formaterDate(new Date));
        $('#ir2').val(formaterTime(new Date)).timepicker();
    }
    if ("perencanaan_mpp" == jenis) {
        $('#pm1').val(formaterDate(new Date));
        $('#pm2').val(formaterTime(new Date)).timepicker();
    }
    $('#modal-mpp-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveMpp(jenis, ket) {
    var data = "";
    var cek = false;
    if ("identifikasi" == jenis) {

    }
    if (cek) {
        var result = JSON.stringify(data);
        $('#save_mpp_' + jenis).hide();
        $('#load_mpp_' + jenis).show();
        dwr.engine.setAsync(true);
        MppAction.saveMpp(result, {
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
                    body = '<tr>' +
                        '<td>'+item.parameter+'</td>' +
                        '<td>'+jwb+'</td>' +
                        '</tr>';
                });
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }
        });

        var table = '<table style="font-size: 12px" class="table table-bordered">' +
            '<thead>' + head + '</thead>' +
            '<tr bgcolor="#ffebcd">' +
            '<tbody>' + body + '</tbody>' +
            '</table>';

        var newRow = $('<tr id="del_mpp_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
        newRow.insertAfter($('table').find('#row_mpp_' + jenis));
        var url = '<%= request.getContextPath() %>/pages/images/cancel-flat-new.png';
        $('#btn_mpp_' + jenis).attr('src', url);
        $('#btn_mpp_' + jenis).attr('onclick', 'delRowMpp(\'' + jenis + '\')');
    }
}

function delRowMpp(id) {
    $('#del_mpp_' + id).remove();
    var url = '<%= request.getContextPath() %>/pages/images/icons8-plus-25.png';
    $('#btn_mpp_' + id).attr('src', url);
    $('#btn_mpp_' + id).attr('onclick', 'detailFormMpp(\'' + id + '\')');
}
