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
        'no_checkup': noCheckup,
        'id_detail_checkup': idDetailCheckup,
        'id_pasien': idPasien,
        'id_rm': tempidRm
    }

    if (va1 && va2 && va3 && va4 && va5 && va6 && va7 && va8 &&
        nP && sP && !va9) {

        var ttd1 = v9.toDataURL("image/png"),
            ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
        var ttd2 = v10.toDataURL("image/png"),
            ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");

        if(va10){
            ttd2 = "";
        }

        data = {
            'id_detail_checkup': idDetailCheckup,
            'waktu': va1.split("-").reverse().join("-") + ' ' + va2 + ':00',
            'ppa': va3,
            'subjective': va4,
            'nadi': nadi,
            'suhu': suhu,
            'rr': rr,
            'tensi': replaceUnderLine(tensi),
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

        if(!cekSession()){
            var result = JSON.stringify(data);
            var pasienData = JSON.stringify(dataPasien);
            $('#save_' + tipe + '_' + jenis).hide();
            $('#load_' + tipe + '_' + jenis).show();
            dwr.engine.setAsync(true);
            CatatanTerintegrasiAction.saveCatatanTerintegrasi(result, pasienData, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#save_' + tipe + '_' + jenis).show();
                        $('#load_' + tipe + '_' + jenis).hide();
                        $('#modal-' + tipe + '-' + jenis).modal('hide');
                        $('#warning_' + tipe + '_' + ket).show().fadeOut(5000);
                        $('#msg_' + tipe + '_' + ket).text("Berhasil menambahkan data ....");
                        $('#modal-' + tipe + '-' + jenis).scrollTop(0);

                    } else {
                        $('#save_' + tipe + '_' + jenis).show();
                        $('#load_' + tipe + '_' + jenis).hide();
                        $('#warning_' + tipe + '_' + jenis).show().fadeOut(5000);
                        $('#msg_' + tipe + '_' + jenis).text(res.msg);
                        $('#modal-' + tipe + '-' + jenis).scrollTop(0);
                    }
                }
            });
        }
    } else {
        $('#save_' + tipe + '_' + jenis).show();
        $('#load_' + tipe + '_' + jenis).hide();
        $('#warning_' + tipe + '_' + jenis).show().fadeOut(5000);
        $('#msg_' + tipe + '_' + jenis).text("Silahkan cek kembali data inputan anda...");
        $('#modal-' + tipe + '-' + jenis).scrollTop(0);
    }
}

function detailCPPT(jenis, ket, tipe, gizi) {
    if(!cekSession()){
        var head = "";
        var body = "";
        var first = "";
        var last = "";
        var cekData = false;

        CatatanTerintegrasiAction.getListCatatanTerintegrasi(idDetailCheckup, ket, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var setTtd = '';
                    if(item.ttdDpjp != '' && item.ttdDpjp != null){
                        setTtd = '<img style="width: 100%; height: 50px" src="' + item.ttdDpjp + '">';
                    }else{
                        setTtd = '<i onclick="setTtdDpjp(\''+item.idCatatanTerintegrasi+'\', \''+jenis+'\', \''+ket+'\', \''+tipe+'\')" class="fa fa-pencil hvr-grow" style="cursor: pointer; color: #1ab7ea; margin-bottom: 10px"></i>';
                    }

                    var del = '<i id="delete_' + item.idCatatanTerintegrasi + '" onclick="conCPT(\'' + jenis + '\', \'' + ket + '\', \'' + tipe + '\', \'' + item.idCatatanTerintegrasi + '\')" style="color: red" class="fa fa-trash fa-1x hvr-grow"></i>';
                    var tempDel = "";
                    if("gizi" == gizi){
                        if("Gizi" == item.ppa){
                            tempDel = del;
                        }else{
                            tempDel = "";
                        }
                    }else{
                        if("Gizi" == item.ppa){
                            tempDel = "";
                        }else{
                            tempDel = del;
                        }
                    }

                    var object = subject = 'Tensi : ' + item.tensi + ' mmHg, Nadi : ' + item.nadi +
                        'x/menit, Suhu : ' + item.suhu + ' ˚C, RR : ' + item.rr + ' x/menit, Keterangan : ' + cekNullCppt(item.objective);
                    body += '<tr>' +
                        '<td>' + converterDateTime(item.waktu) + '</td>' +
                        '<td>' + cekNullCppt(item.ppa) + '</td>' +
                        '<td>' + cekNullCppt(item.subjective) + '</td>' +
                        '<td>' + object + '</td>' +
                        '<td>' + cekNullCppt(item.assesment) + '</td>' +
                        '<td>' + cekNullCppt(item.planning) + '</td>' +
                        '<td>' + cekNullCppt(item.intruksi) + '</td>' +
                        '<td>' + '<img style="width: 100%; height: 50px" src="' + item.ttdPetugas + '">' +
                        '<p style="margin-top: -5px">' + cekNullCppt(item.namaPetugas) + '</p>' +
                        '<p style="margin-top: -10px">' + cekNullCppt(item.sipPetugas) + '</p>' +
                        '</td>' +
                        '<td>' + setTtd +
                        '<p style="margin-top: -5px">' + cekNullCppt(item.namaDokter) + '</p>' +
                        '<p style="margin-top: -10px">' + cekNullCppt(item.sipDokter) + '</p>' +
                        '</td>' +
                        '<td align="center">' + tempDel
                        '</td>'
                    '</tr>';
                    cekData = true;
                });
            } else {
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
                    '<td>TTD Petugas</td>' +
                    '<td>TTD DPJP</td>' +
                    '<td align="center">Action</td>' +
                    '</tr>'
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_' + tipe + '_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_' + tipe + '_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_' + tipe + '_' + jenis).attr('src', url);
            $('#btn_' + tipe + '_' + jenis).attr('onclick', 'delRowCPPT(\'' + jenis + '\',\'' + ket + '\', \'' + tipe + '\', \''+gizi+'\')');
        });
    }
}

function delRowCPPT(jenis, ket, tipe, gizi) {
    $('#del_' + tipe + '_' + jenis).remove();
    var url = "";
    if ("monitoring_hd" == ket || "asesmen-ugd" == ket || "catatan_terintegrasi_ina" == ket) {
        url = contextPath + '/pages/images/icons8-add-list-25.png';
    } else {
        url = contextPath + '/pages/images/icons8-plus-25.png';
    }
    $('#btn_' + tipe + '_' + jenis).attr('src', url);
    $('#btn_' + tipe + '_' + jenis).attr('onclick', 'detailCPPT(\'' + jenis + '\',\'' + ket + '\',\'' + tipe + '\', \''+gizi+'\')');
}

function cekNullCppt(item) {
    var res = "";
    if (item != null) {
        res = item;
    }
    return res;
}

function conCPT(jenis, ket, tipe, id) {
    $('#tanya').text("Yakin mengahapus data ini ?");
    $('#modal-confirm-rm').modal({show: true, backdrop: 'static'});
    $('#save_con_rm').attr('onclick', 'delCPT(\'' + jenis + '\', \'' + ket + '\',\'' + tipe + '\', \'' + id + '\')');
}

function delCPT(jenis, ket, tipe, id) {
    $('#modal-confirm-rm').modal('hide');
    if(!cekSession()){
        var dataPasien = {
            'no_checkup': noCheckup,
            'id_detail_checkup': idDetailCheckup,
            'id_pasien': idPasien,
            'id_rm': tempidRm
        }
        var result = JSON.stringify(dataPasien);
        startIconSpin('delete_' + id);
        dwr.engine.setAsync(true);
        CatatanTerintegrasiAction.saveDelete(id, result, {
            callback: function (res) {
                if (res.status == "success") {
                    stopIconSpin('delete_' + id);
                    $('#warning_' + tipe + '_' + ket).show().fadeOut(5000);
                    $('#msg_' + tipe + '_' + ket).text("Berhasil menghapus data...");
                } else {
                    stopSpin('delete_' + id);
                    $('#warn_' + ket).show().fadeOut(5000);
                    $('#msg_' + ket).text(res.msg);
                }
            }
        });
    }
}

function setTtdDpjp(id, jenis, ket, tipe){
    setDataPasien();
    $('#modal-ina-ttd_dpjp').modal({show: true, backdrop: 'static'});
    $('#save_ttd_dpjp').attr('onclick','saveTtdDpjp(\''+id+'\', \''+jenis+'\', \''+ket+'\', \''+tipe+'\')');
}

function saveTtdDpjp(id, jenis, ket, tipe){
    var nama = $('#nama_dpjp_edit').val();
    var sip = $('#sip_dpjp_edit').val();
    var ttd = document.getElementById("ttd_edit");
    var cek = isCanvasBlank(ttd);
    if(nama && sip != '' && !cek){
        if(!cekSession()){
            var ttd1 = ttd.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            var dataPasien = {
                'id_catatan_integrasi': id,
                'nama_dokter': nama,
                'sip_dokter': sip,
                'ttd_dpjp': ttd1
            }
            delRowCPPT(jenis, ket, tipe);
            var result = JSON.stringify(dataPasien);
            $('#save_ttd_dpjp').hide();
            $('#load_ttd_dpjp').show();
            dwr.engine.setAsync(true);
            CatatanTerintegrasiAction.updateTtdDpjp(result, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#modal-ina-ttd_dpjp').modal('hide');
                        $('#save_ttd_dpjp').show();
                        $('#load_ttd_dpjp').hide();
                        $('#warning_ina_catatan_terintegrasi_ina').show().fadeOut(5000);
                        $('#msg_ina_catatan_terintegrasi_ina').text("Berhasil mengupdate data...");
                        detailCPPT(jenis, ket, tipe);
                    } else {
                        $('#warning_ina_ttd_dpjp').show().fadeOut(5000);
                        $('#msg_ina_ttd_dpjp').text(res.msg);
                        $('#save_ttd_dpjp').show();
                        $('#load_ttd_dpjp').hide();
                    }
                }
            });
        }
    }else {
        $('#warning_ina_ttd_dpjp').show().fadeOut(5000);
        $('#msg_ina_ttd_dpjp').text("Silahkan cek kembali inputan anda...!");
    }
}