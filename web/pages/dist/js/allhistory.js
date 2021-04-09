function viewTelemedic(url, tgl) {
    $('#body-video-rm').attr('src', url);
    $('#tanggal_tele').html(tgl);
    $('#modal-telemedic').modal({show: true, backdrop: 'static'});
}

function viewHistory() {
    if(!cekSession()){
        $('#modal-history').modal({show: true, backdrop: 'static', keyboard: false});
        var table = "";
        CheckupAction.getListHistoryPasien(idPasien, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var btn = "";
                    var icon = "";
                    var tele = "";
                    var keteranganTindakan = item.keterangan;
                    if("lab" == item.keterangan){
                        keteranganTindakan = "laboratorium";
                    }

                    var json = "";
                    var btn2 = "";
                    if(item.uploadHasil.length > 0){
                        json = JSON.stringify(item.uploadHasil);
                    }

                    if ("resep" == keteranganTindakan || "laboratorium" == keteranganTindakan || "radiologi" == keteranganTindakan) {
                        if ("laboratorium" == keteranganTindakan || "radiologi" == keteranganTindakan) {
                            if ("Y" == item.isPeriksaLuar) {
                                btn = '<img onclick="showHasil(\'' + item.idRiwayatTindakan + '\', \'' + item.namaTindakan + '\')" border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                            } else {
                                btn = '<img class="hvr-grow" id="btn_' + item.idRiwayatTindakan + '" \n' +
                                    'onclick="detailTindakan(\'' + item.idRiwayatTindakan + '\',\'' + item.idTindakan + '\',\'' + keteranganTindakan + '\')"\n' +
                                    'src="' + contextPath + '/pages/images/icons8-plus-25.png">';
                                if(json != ''){
                                    btn2 = '<img onclick="showHasil(\'' + item.idRiwayatTindakan + '\', \'' + item.namaTindakan + '\')" border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                                }
                            }
                        } else {
                            btn = '<img class="hvr-grow" id="btn_' + item.idRiwayatTindakan + '" \n' +
                                'onclick="detailTindakan(\'' + item.idRiwayatTindakan + '\',\'' + item.idTindakan + '\',\'' + keteranganTindakan + '\')"\n' +
                                'src="' + contextPath + '/pages/images/icons8-plus-25.png">';
                        }
                    }
                    if (item.idDetailCheckup != null && item.idDetailCheckup != '') {
                        icon = '<a href="' + contextPath + '/rekammedis/detail_rekammedis.action?idPasien=' + idPasien + '&id=' + item.idDetailCheckup + '&idx='+idDetailCheckup+'&url='+urlPage+'"><i class="fa fa-search hvr-grow"></i></a>';
                        if (item.videoRm != null) {
                            tele = '<img style="cursor: pointer" src=' + contextPath + '"/pages/images/icons8-movie-beginning-30.png" onclick="viewTelemedic(\'' + item.videoRm + '\', \'' + item.tglTindakan + '\')">';
                        }
                    }

                    table += '<tr id="row_' + item.idRiwayatTindakan + '">' +
                        '<td><b>' + icon + ' ' + cekDataNull(item.idDetailCheckup) +
                        '<p style="margin-left: 15px">' + cekDataNull(item.namaPelayanan) + '</p></b>' +
                        '<p style="margin-left: 15px">' + cekDataNull(item.diagnosa) + cekDataNull(item.namaDiagnosa) + '</p>' +
                        '<textarea style="display: none" id="id_id' + item.idRiwayatTindakan + '">' + json + '</textarea>' +
                        '</td>' +
                        '<td>' + cekDataNull(item.tglTindakan) + '</td>' +
                        '<td>' + cekDataNull(item.namaTindakan) + ' <div class="pull-right">' + btn + btn2 + '</div></td>' +
                        '<td>' + cekDataNull(item.keteranganKeluar) + '</td>' +
                        '<td align="center">' + tele + '</td>' +
                        '<tr>';
                });
                $('#body_history').html(table);
            }
        });
    }
}

function detailTindakan(id, idTindakan, keterangan) {
    if(!cekSession()){
        if (id && idTindakan && keterangan != '') {
            var head = "";
            var body = "";
            CheckupAction.getListDetailHistoryPasien(idTindakan, keterangan, function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        if(keterangan == "radiologi" || keterangan == "laboratorium"){
                            var namaPemeriksaan = "";
                            if(i == 0){
                                namaPemeriksaan = '<b>'+item.namaPemeriksaan+'</b>';
                            }else{
                                if(res[i - 1]["namaPemeriksaan"].toLowerCase() == item.namaPemeriksaan.toLowerCase()){
                                    namaPemeriksaan = "";
                                }else{
                                    namaPemeriksaan = '<b>'+item.namaPemeriksaan+'</b>';
                                }
                            }
                        }

                        if (keterangan == "radiologi") {
                            body += '<tr>' +
                                '<td>'+namaPemeriksaan+'<br>'+
                                '<div style="margin-left: 10px">'+cekDataNull(item.namaDetailLab)+'</div>'+
                                '</td>' +
                                '<td>'+'<div style="margin-left: 15px">'+cekDataNull(item.kesimpulan)+'</div>'+'</td>' +
                                '</tr>';
                        }
                        if (keterangan == "laboratorium") {
                            var acuan = cekDataNull(item.ketAcuanL);
                            if(jenisKelamin != undefined){
                                if(jenisKelamin == "P"){
                                    acuan = cekDataNull(item.ketAcuanP);
                                }
                            }
                            body += '<tr>' +
                                '<td>'+namaPemeriksaan+'<br>'+
                                '<div style="margin-left: 10px">'+cekDataNull(item.namaDetailLab)+'</div>'+
                                '</td>' +
                                '<td>'+cekDataNull(item.kesimpulan)+'</td>' +
                                '<td>'+cekDataNull(acuan)+'</td>' +
                                '<td>'+cekDataNull(item.satuan)+'</td>' +
                                '<td>'+cekDataNull(item.keterangan)+'</td>' +
                                '</tr>';
                        }
                        if (keterangan == "resep") {
                            body += '<tr>' +
                                '<td>' + cekDataNull(item.namaObat) + '</td>' +
                                '<td>' + cekDataNull(item.qty) + ' ' + cekDataNull(item.satuan) + '</td>' +
                                '<td>' + cekDataNull(item.keterangan) + '</td>' +
                                '</tr>';
                        }
                    });
                }

                if (keterangan == "radiologi") {
                    head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                        '<td width="40%">Pemeriksaan</td>' +
                        // '<td>Satuan</td>' +
                        // '<td>Keterangan Acuan</td>' +
                        '<td>Hasil</td>' +
                        '</tr>';
                }
                if (keterangan == "laboratorium") {
                    head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                        '<td>Pemeriksaan</td>' +
                        '<td>Hasil</td>' +
                        '<td>Nilai Normal</td>' +
                        '<td>Satuan</td>' +
                        '<td>Keterangan</td>' +
                        '</tr>';
                }
                if (keterangan == "resep") {
                    head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                        '<td>Nama Obat</td>' +
                        '<td>Qty</td>' +
                        '<td>Keterangan</td>' +
                        '</tr>';
                }

                var table = '<table style="font-size: 12px" class="table table-bordered">' +
                    '<thead>' + head + '</thead>' +
                    '<tbody>' + body + '</tbody>' +
                    '</table>';

                var newRow = $('<tr id="del_' + id + '"><td colspan="6">' + table + '</td></tr>');
                newRow.insertAfter($('table').find('#row_' + id));
                var url = contextPath + '/pages/images/minus-allnew.png';
                $('#btn_' + id).attr('src', url);
                $('#btn_' + id).attr('onclick', 'delDetail(\'' + id + '\',\'' + idTindakan + '\', \'' + keterangan + '\')');
            });
        }
    }
}

function delDetail(id, idTindakan, keterangan) {
    $('#del_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_' + id).attr('src', url);
    $('#btn_' + id).attr('onclick', 'detailTindakan(\'' + id + '\', \'' + idTindakan + '\', \'' + keterangan + '\')');
}

function cekDataNull(item) {
    var data = "";
    if (item != null && item != '') {
        data = item;
    }
    return data;
}

function showHasil(id, nama) {
    var data = $('#id_id' +id).val();
    $('#item_hasil_lab').html('');
    $('#li_hasil_lab').html('');
    if (data != null && data != '') {
        var result = JSON.parse(data);
        $('#title_hasil_lab').html(nama);
        if (result.length > 0) {
            var set = '';
            var li = '';
            $.each(result, function (i, item) {
                var cla = 'class="item"';
                var claLi = '';
                if (i == 0) {
                    cla = 'class="item active"';
                    claLi = 'class="active"';
                }
                var x = item.urlImg;
                var tipe = x.split('.').pop();
                if("pdf" == tipe){
                    set += '<div ' + cla + '>\n' +
                        '<embed src="'+item.urlImg+'" style="width: 100%; height: 70%"/>'+
                        '</div>';
                }else{
                    set += '<div ' + cla + '>\n' +
                        '<img src="' + item.urlImg + '" style="width: 100%">\n' +
                        '</div>';
                }
                li += '<li data-target="#carousel-hasil_lab" data-slide-to="' + i + '" ' + claLi + '></li>';
            });
            $('#item_hasil_lab').html(set);
            $('#li_hasil_lab').html(li);
        }
        $('#modal-hasil_lab').modal({show: true, backdrop: 'static'});
    }
}


