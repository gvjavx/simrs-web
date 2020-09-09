function viewTelemedic(url, tgl) {
    $('#body-video-rm').attr('src', url);
    $('#tanggal_tele').html(tgl);
    $('#modal-telemedic').modal({show: true, backdrop: 'static'});
}

function viewAllRekamMedis() {
    var sps = '<li><span class="fa-li"><i class="fa fa-list-ul"></i></span><b>Spesialis</b></li>';
    var fs = '<li><span class="fa-li"><i class="fa fa-list-ul"></i></span><b>Fisioterapi</b></li>';
    var hd = '<li><span class="fa-li"><i class="fa fa-list-ul"></i></span><b>Hemodialisa</b></li>';
    var igd = '<li><span class="fa-li"><i class="fa fa-list-ul"></i></span><b>IGD</b></li>';
    var ri = '<li><span class="fa-li"><i class="fa fa-list-ul"></i></span><b>Rawat Inap</b></li>';
    var icu = '<li><span class="fa-li"><i class="fa fa-list-ul"></i></span><b>ICU</b></li>';
    var ko = '<li><span class="fa-li"><i class="fa fa-list-ul"></i></span><b>Kamar Operasi</b></li>';
    var rb = '<li><span class="fa-li"><i class="fa fa-list-ul"></i></span><b>Ruang Bersalin</b></li>';
    var tp = '<li><span class="fa-li"><i class="fa fa-list-ul"></i></span><b>TPPRI</b></li>';
    var su = '<li><span class="fa-li"><i class="fa fa-list-ul"></i></span><b>Surat Pernyataan dan Keterangan</b></li>';
    var temp = "";
    startLoad();
    dwr.engine.setAsync(true);
    CheckupAction.getRiwayatListRekammedisPasien(idDetailCheckup, tipePelayanan, kategoriPelayanan, {
        callback: function (res) {
            if (res.length > 0) {
                stopLoad();
                $.each(res, function (i, item) {
                    var cek = "";
                    var tgl = "";
                    var icons = '<i class="fa fa-file-o"></i>';
                    var icons2 = '<i class="fa fa-print"></i>';
                    var tol = "";
                    var tolText = "";
                    var labelTerisi = "";
                    var constan = 0;
                    var terIsi = 0;
                    var labelPrint = "";
                    var terIsiPrint = "";

                    if (item.jumlahKategori != null) {
                        constan = item.jumlahKategori;
                    }
                    if (item.terisi != null && item.terisi != '') {
                        terIsi = item.terisi;
                        terIsiPrint = item.terisi;
                    }

                    if (constan == terIsi || parseInt(terIsi) > parseInt(constan)) {
                        var conver = "";
                        if (item.createdDate != null) {
                            conver = converterDate(new Date(item.createdDate));
                            tol = 'class="box-rm"';
                            tolText = '<span class="box-rmtext">Tanggal mengisi ' + conver + '</span>';
                        }
                        icons = '<i class="fa fa-check" style="color: #449d44"></i>';
                    }

                    labelPrint = '<span style="color: #367fa9; font-weight: bold">' + terIsiPrint + '</span>';

                    if ("spesialis" == item.tipeRM) {
                        sps += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><span class="hvr-grow"><span class="fa-li">' + icons + '</span>' + item.namaRm + tolText + '</span></li>';
                    }
                    if ("fisioterapi" == item.tipeRM) {
                        fs += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><span class="hvr-grow"><span class="fa-li">' + icons + '</span>' + item.namaRm + tolText + '</span></li>';
                    }
                    if ("hemodialisa" == item.tipeRM) {
                        hd += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><span class="hvr-grow"><span class="fa-li">' + icons + '</span>' + item.namaRm + tolText + '</span></li>';
                    }
                    if ("igd" == item.tipeRM) {
                        igd += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><span class="hvr-grow"><span class="fa-li">' + icons + '</span>' + item.namaRm + tolText + '</span></li>';
                    }
                    if ("tppri" == item.tipeRM) {
                        tp += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><span class="hvr-grow"><span class="fa-li">' + icons + '</span>' + item.namaRm + tolText + '</span></li>';
                    }
                    if ("rawat_inap" == item.tipeRM) {
                        ri += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><span class="hvr-grow"><span class="fa-li">' + icons + '</span>' + item.namaRm + tolText + '</span></li>';
                    }
                    if ("rawat_intensif" == item.tipeRM) {
                        icu += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><span class="hvr-grow"><span class="fa-li">' + icons + '</span>' + item.namaRm + tolText + '</span></li>';
                    }
                    if ("ruang_bersalin" == item.tipeRM) {
                        rb += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><span class="hvr-grow"><span class="fa-li">' + icons + '</span>' + item.namaRm + tolText + '</span></li>';
                    }
                    if ("kamar_operasi" == item.tipeRM) {
                        ko += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><span class="hvr-grow"><span class="fa-li">' + icons + '</span>' + item.namaRm + tolText + '</span></li>';
                    }
                    if ("surat" == item.tipeRM) {
                        su += '<li ' + tol + ' onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'N\')"><span class="hvr-grow"><span class="fa-li">' + icons + '</span>' + item.namaRm + ' ' + labelPrint + tolText + '</span></li>';
                    }

                });
                $('#asesmen_sps').html(sps);
                $('#asesmen_fs').html(fs);
                $('#asesmen_hd').html(hd);
                $('#asesmen_igd').html(igd);
                $('#asesmen_tppri').html(tp);
                $('#asesmen_ri').html(ri);
                $('#asesmen_icu').html(icu);
                $('#asesmen_ko').html(ko);
                $('#asesmen_rb').html(rb);
                $('#asesmen_surat').html(su);
            }
        }
    });
    $('#modal-all_rekam_medis').modal({show: true, backdrop: 'static'});
}

function setRekamMedis(tipePelayanan, jenis, id) {
    var temp = "";
    dwr.engine.setAsync(true);
    CheckupAction.getListRekammedisPasien(tipePelayanan, jenis, idDetailCheckup, {
        callback: function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var cek = "";
                    var tgl = "";
                    var icons = '<i class="fa fa-file-o"></i>';
                    var icons2 = '<i class="fa fa-print"></i>';
                    var tol = "";
                    var tolText = "";
                    var labelTerisi = "";
                    var constan = 0;
                    var terIsi = 0;
                    var labelPrint = "";
                    var terIsiPrint = "";

                    if (item.jumlahKategori != null) {
                        constan = item.jumlahKategori;
                    }
                    if (item.terisi != null && item.terisi != '') {
                        terIsi = item.terisi;
                        terIsiPrint = item.terisi;
                    }

                    if (constan == terIsi || parseInt(terIsi) > parseInt(constan)) {
                        var conver = "";
                        if (item.createdDate != null) {
                            conver = converterDate(new Date(item.createdDate));
                            tgl = '<label class="label label-success">' + conver + '</label>';
                            tol = 'class="box-rm"';
                            tolText = '<span class="box-rmtext">Tanggal mengisi ' + conver + '</span>';
                        }
                        icons = '<i class="fa fa-check" style="color: #449d44"></i>';
                        icons2 = '<i class="fa fa-check" style="color: #449d44"></i>';
                    }

                    labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';
                    labelPrint = '<span style="color: #367fa9; font-weight: bold">' + terIsiPrint + '</span>';
                    if (item.jenis == 'ringkasan_rj') {
                        temp += '<li onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><a style="cursor: pointer"><i class="fa fa-file-o"></i>' + item.namaRm + '</a></li>';
                    } else {
                        if (item.keterangan == 'form') {
                            temp += '<li ' + tol + ' onmouseover="loadModalRM(\'' + item.jenis + '\')" onclick="' + item.function + '(\'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')"><a style="cursor: pointer">' + icons + item.namaRm + tolText + '</a></li>';
                        }
                    }
                });
                $('#' + id).html(temp);
            }
        }
    });
}

function viewHistory() {
    $('#modal-history').modal({show: true, backdrop: 'static', keyboard: false});
    var table = "";
    CheckupAction.getListHistoryPasien(idPasien, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                var btn = "";
                var icon = "";
                var tele = "";

                if ("resep" == item.keterangan || "laboratorium" == item.keterangan || "radiologi" == item.keterangan) {
                    if ("laboratorium" == item.keterangan || "radiologi" == item.keterangan) {
                        if (item.urlLab != null && item.urlLab != '') {
                            btn = '<img onclick="labLuar(\'' + item.namaTindakan + '\', \'' + item.urlLab + '\')" border="0" class="hvr-grow" src="' + contextPath + '/pages/images/icons8-pictures-folder-25.png" style="cursor: pointer;">';
                        } else {
                            btn = '<img class="hvr-grow" id="btn_' + item.idRiwayatTindakan + '" \n' +
                                'onclick="detailTindakan(\'' + item.idRiwayatTindakan + '\',\'' + item.idTindakan + '\',\'' + item.keterangan + '\')"\n' +
                                'src="' + contextPath + '/pages/images/icons8-plus-25.png">';
                        }
                    } else {
                        btn = '<img class="hvr-grow" id="btn_' + item.idRiwayatTindakan + '" \n' +
                            'onclick="detailTindakan(\'' + item.idRiwayatTindakan + '\',\'' + item.idTindakan + '\',\'' + item.keterangan + '\')"\n' +
                            'src="' + contextPath + '/pages/images/icons8-plus-25.png">';
                    }
                }
                if (item.idDetailCheckup != null && item.idDetailCheckup != '') {
                    icon = '<a href="detail_rekammedis.action?idPasien=' + idPasien + '&id=' + item.idDetailCheckup + '"><i class="fa fa-search hvr-grow"></i></a>';
                    if (item.videoRm != null) {
                        tele = '<img style="cursor: pointer" src="' + contextPath + '/pages/images/icons8-movie-beginning-30.png" onclick="viewTelemedic(\'' + item.videoRm + '\', \'' + item.tglTindakan + '\')">';
                    }
                }
                table += '<tr id="row_' + item.idRiwayatTindakan + '">' +
                    '<td><b>' + icon + ' ' + cekDataNull(item.idDetailCheckup) +
                    '<p style="margin-left: 15px">' + cekDataNull(item.namaPelayanan) + '</p></b>' +
                    '<p style="margin-left: 15px">' + cekDataNull(item.diagnosa) + cekDataNull(item.namaDiagnosa) + '</p></td>' +
                    // '<td>' + cekDataNull(item.idDetailCheckup) + '</td>' +
                    '<td>' + cekDataNull(item.tglTindakan) + '</td>' +
                    '<td>' + cekDataNull(item.namaTindakan) + ' <div class="pull-right">' + btn + '</div></td>' +
                    '<td>' + cekDataNull(item.keteranganKeluar) + '</td>' +
                    // '<td align="center">'+btn+'</td>' +
                    '<td align="center">' + tele + '</td>' +
                    '<tr>';
            });
            $('#body_history').html(table);
        }
    });
}

function detailTindakan(id, idTindakan, keterangan) {
    if (id && idTindakan && keterangan != '') {
        var head = "";
        var body = "";
        CheckupAction.getListDetailHistoryPasien(idTindakan, keterangan, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    if (keterangan == "radiologi") {
                        body += '<tr>' +
                            '<td>' + cekDataNull(item.namaDetailLab) + '</td>' +
                            '<td>' + cekDataNull(item.satuan) + '</td>' +
                            '<td>' + cekDataNull(item.acuan) + '</td>' +
                            '<td>' + cekDataNull(item.kesimpulan) + '</td>' +
                            '</tr>';
                    }
                    if (keterangan == "laboratorium") {
                        body += '<tr>' +
                            '<td>' + cekDataNull(item.namaDetailLab) + '</td>' +
                            '<td>' + cekDataNull(item.satuan) + '</td>' +
                            '<td>' + cekDataNull(item.acuan) + '</td>' +
                            '<td>' + cekDataNull(item.kesimpulan) + '</td>' +
                            '<td>' + cekDataNull(item.keterangan) + '</td>' +
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
                    '<td>Pemeriksaan</td>' +
                    '<td>Satuan</td>' +
                    '<td>Keterangan Acuan</td>' +
                    '<td>Hasil</td>' +
                    '</tr>';
            }
            if (keterangan == "laboratorium") {
                head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                    '<td>Pemeriksaan</td>' +
                    '<td>Satuan</td>' +
                    '<td>Keterangan Acuan</td>' +
                    '<td>Hasil</td>' +
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

function carouselSwipe(id) {

    var currentImg = document.getElementsByClassName('carousel-img-displayed')[0].id.substring(9);
    var newImg = parseInt(currentImg);

    if (id == 'carousel-arrow-next') {
        newImg++;
        if (newImg >= document.getElementsByClassName('carousel-img').length) {
            newImg = 0;
        }
    } else if (id == 'carousel-arrow-prev') {
        newImg--;
        if (newImg < 0) {
            newImg = document.getElementsByClassName('carousel-img').length - 1;
        }
    }

    document.getElementById('carousel-' + currentImg).className = 'carousel-img carousel-img-hidden';
    var displayedCarousel = document.getElementById('carousel-' + newImg);
    displayedCarousel.className = 'carousel-img carousel-img-hidden';

    setTimeout(function () {
        displayedCarousel.className = 'carousel-img carousel-img-displayed';
    }, 20);

    setTimeout(function () {
        document.getElementById('carousel-' + currentImg).className = 'carousel-img carousel-img-noDisplay';
    }, 520);

}

function labLuar(kategori, url){
    $('#title_lab_luar').text("Detail Hasil "+kategori+" Luar");
    $('#img_lab_luar').attr('src',url);
    $('#modal-lab_luar').modal({show:true, backdrop:'static'});
}


