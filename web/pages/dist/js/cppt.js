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

    var dataPasien = "";

    dataPasien = {
        'no_checkup' : noCheckup,
        'id_detail_checkup' : idDetailCheckup,
        'id_pasien' : idPasien,
        'id_rm' : tempidRm
    }

    if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 &&
        nP && nD && sP && nP && nadi && suhu && rr && tensi != '' &&
        !va9 && !va10) {

        var ttd1 = v9.toDataURL("image/png"),
            ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
        var ttd2 = v10.toDataURL("image/png"),
            ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1.split("-").reverse().join("-") + ' ' + va2 + ':00',
            'ppa': va3,
            'subjective': va4,
            'nadi': nadi,
            'suhu': suhu,
            'rr': rr,
            'tensi': tensi,
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

        var result = JSON.stringify(data);
        var pasienData = JSON.stringify(dataPasien);

        $('#save_'+tipe+'_' + jenis).hide();
        $('#load_'+tipe+'_' + jenis).show();
        dwr.engine.setAsync(true);
        CatatanTerintegrasiAction.saveCatatanTerintegrasi(result, pasienData, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_'+tipe+'_' + jenis).show();
                    $('#load_'+tipe+'_' + jenis).hide();
                    $('#modal-'+tipe+'-' + jenis).modal('hide');
                    $('#warning_'+tipe+'_' + ket).show().fadeOut(5000);
                    $('#msg_'+tipe+'_' + ket).text("Berhasil menambahkan data ....");
                    $('#modal-'+tipe+'-' + jenis).scrollTop(0);

                } else {
                    $('#save_'+tipe+'_' + jenis).show();
                    $('#load_'+tipe+'_' + jenis).hide();
                    $('#warning_'+tipe+'_' + jenis).show().fadeOut(5000);
                    $('#msg_'+tipe+'_' + jenis).text(res.msg);
                    $('#modal-'+tipe+'-' + jenis).scrollTop(0);
                }
            }
        });
    } else {
        $('#save_'+tipe+'_' + jenis).show();
        $('#load_'+tipe+'_' + jenis).hide();
        $('#warning_'+tipe+'_' + jenis).show().fadeOut(5000);
        $('#msg_'+tipe+'_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-'+tipe+'-' + jenis).scrollTop(0);
    }
}

function detailCPPT(jenis, ket, tipe) {
    var head = "";
    var body = "";
    var first = "";
    var last = "";
    var cekData = false;

    CatatanTerintegrasiAction.getListCatatanTerintegrasi(idDetailCheckup, ket, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                var object = subject = 'Tensi : '+item.tensi+' mmHg, Nadi : '+item.nadi+
                    'x/menit, Suhu : '+item.suhu+' ˚C, RR : '+item.rr+' x/menit, Keterangan : '+cekNullCppt(item.objective);
                body += '<tr>' +
                    '<td>' + converterDateTime(item.waktu) + '</td>' +
                    '<td>' + cekNullCppt(item.ppa) + '</td>' +
                    '<td>' + cekNullCppt(item.subjective) + '</td>' +
                    '<td>' + object + '</td>' +
                    '<td>' + cekNullCppt(item.assesment) + '</td>' +
                    '<td>' + cekNullCppt(item.planning) + '</td>' +
                    '<td>' + cekNullCppt(item.intruksi) + '</td>' +
                    '<td>' + '<img style="width: 100%; height: 50px" src="' + item.ttdPetugas + '">' +
                    '<p style="margin-top: -5px">'+cekNullCppt(item.namaDokter)+'</p>'+
                    '<p style="margin-top: -10px">'+cekNullCppt(item.sipDokter)+'</p>'+
                    '</td>' +
                    '<td>' + '<img style="width: 100%; height: 50px" src="' + item.ttdDpjp + '">' +
                    '<p style="margin-top: -5px">'+cekNullCppt(item.namaPetugas)+'</p>'+
                    '<p style="margin-top: -10px">'+cekNullCppt(item.sipPetugas)+'</p>'+
                    '</td>' +
                    '</tr>';
                cekData = true;
            });
        }else{
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
                '<td>TTD DPJP</td>' +
                '<td>TTD Petugas</td>' +
                '</tr>'
        }

        var table = '<table style="font-size: 12px" class="table table-bordered">' +
            '<thead>' + head + '</thead>' +
            '<tbody>' + first + body + last + '</tbody>' +
            '</table>';

        var newRow = $('<tr id="del_'+tipe+'_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
        newRow.insertAfter($('table').find('#row_'+tipe+'_' + jenis));
        var url = contextPath + '/pages/images/minus-allnew.png';
        $('#btn_'+tipe+'_' + jenis).attr('src', url);
        $('#btn_'+tipe+'_' + jenis).attr('onclick', 'delRowCPPT(\'' + jenis + '\',\''+ket+'\', \''+tipe+'\')');
    });
}

function delRowCPPT(jenis, ket, tipe) {
    $('#del_'+tipe+'_' + jenis).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_'+tipe+'_' + jenis).attr('src', url);
    $('#btn_'+tipe+'_' + jenis).attr('onclick', 'detailCPPT(\'' + jenis + '\',\''+ket+'\',\''+tipe+'\')');
}

function cekNullCppt(item) {
    var res = "";
    if (item != null) {
        res = item;
    }
    return res;
}