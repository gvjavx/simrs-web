
function viewTelemedic(url, tgl) {
    $('#body-video-rm').attr('src', url);
    $('#tanggal_tele').html(tgl);
    $('#modal-telemedic').modal({show: true, backdrop: 'static'});
    // var video = "";
    // var id = 0;
    // CheckupAction.getListVideoRm(idPasien, function (res) {
    //     if (res.length > 0) {
    //         $.each(res, function (i, item) {
    //             if(item.videoRm != null){
    //                 var count = id++;
    //                 if(count == 0){
    //                     video += '<video id="carousel-'+count+'" class="carousel-img carousel-img-displayed" controls src="'+item.videoRm+'" width="100%" height="420px"></video>';
    //                 }else {
    //                     video += '<video id="carousel-'+count+'" class="carousel-img carousel-img-noDisplay" controls src="'+item.videoRm+'" width="100%" height="420px"></video>';
    //                 }
    //             }
    //         });
    //         $('#body-video-rm').html(video);
    //     }
    // });
}

function viewRiwayat() {
    $('#modal-riwayat').modal({show: true, backdrop: 'static'});
}

function viewHistory() {
    $('#modal-history').modal({show: true, backdrop: 'static', keyboard: false});
    var table = "";
    CheckupAction.getListHistoryPasien(idPasien, function (res) {
        if(res.length > 0){
            $.each(res, function (i, item) {
                var btn = "";
                var icon = "";
                var tele = "";

                if("resep" == item.keterangan || "laboratorium" == item.keterangan || "radiologi" == item.keterangan){
                    btn = '<img class="hvr-grow" id="btn_'+item.idRiwayatTindakan+'" \n' +
                        'onclick="detailTindakan(\''+item.idRiwayatTindakan+'\',\''+item.idTindakan+'\',\''+item.keterangan+'\')"\n' +
                        'src="'+contextPath+'/pages/images/icons8-plus-25.png">';
                }
                if(item.idDetailCheckup != null && item.idDetailCheckup != ''){
                    icon = '<a href="detail_rekammedis.action?idPasien='+idPasien+'&id='+item.idDetailCheckup+'"><i class="fa fa-search"></i></a>';
                    if(item.videoRm != null){
                        tele = '<img style="cursor: pointer" src="'+contextPath+'/pages/images/icons8-movie-beginning-30.png" onclick="viewTelemedic(\''+item.videoRm+'\', \''+item.tglTindakan+'\')">';
                    }
                }
                table += '<tr id="row_'+item.idRiwayatTindakan+'">' +
                    '<td>'+icon+' '+cekDataNull(item.namaPelayanan)+'</td>' +
                    '<td>'+cekDataNull(item.idDetailCheckup)+'</td>' +
                    '<td>'+cekDataNull(item.tglTindakan)+'</td>' +
                    '<td>'+cekDataNull(item.namaTindakan)+' <div class="pull-right">'+btn+'</div></td>' +
                    '<td>'+cekDataNull(item.keteranganKeluar)+'</td>' +
                    // '<td align="center">'+btn+'</td>' +
                    '<td align="center">'+tele+'</td>' +
                    '<tr>';
            });
            $('#body_history').html(table);
        }
    });
}

function detailTindakan(id, idTindakan, keterangan){
    if (id && idTindakan && keterangan != '') {
        var head = "";
        var body = "";
        CheckupAction.getListDetailHistoryPasien(idTindakan, keterangan, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    if(keterangan == "radiologi"){
                        body += '<tr>' +
                            '<td>'+cekDataNull(item.namaDetailLab)+'</td>' +
                            '<td>'+cekDataNull(item.satuan)+'</td>' +
                            '<td>'+cekDataNull(item.acuan)+'</td>' +
                            '<td>'+cekDataNull(item.kesimpulan)+'</td>' +
                            '</tr>';
                    }
                    if(keterangan == "laboratorium"){
                        body += '<tr>' +
                            '<td>'+cekDataNull(item.namaDetailLab)+'</td>' +
                            '<td>'+cekDataNull(item.satuan)+'</td>' +
                            '<td>'+cekDataNull(item.acuan)+'</td>' +
                            '<td>'+cekDataNull(item.kesimpulan)+'</td>' +
                            '<td>'+cekDataNull(item.keterangan)+'</td>' +
                            '</tr>';
                    }
                    if(keterangan == "resep"){
                        body += '<tr>' +
                            '<td>'+cekDataNull(item.namaObat)+'</td>' +
                            '<td>'+cekDataNull(item.qty)+' '+cekDataNull(item.satuan)+'</td>' +
                            '<td>'+cekDataNull(item.keterangan)+'</td>' +
                            '</tr>';
                    }
                });
            }

            if(keterangan == "radiologi"){
                head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                    '<td>Pemeriksaan</td>' +
                    '<td>Satuan</td>' +
                    '<td>Keterangan Acuan</td>' +
                    '<td>Hasil</td>' +
                    '</tr>';
            }
            if(keterangan == "laboratorium"){
                head = '<tr bgcolor="#ffebcd" style="font-weight: bold">' +
                    '<td>Pemeriksaan</td>' +
                    '<td>Satuan</td>' +
                    '<td>Keterangan Acuan</td>' +
                    '<td>Hasil</td>' +
                    '<td>Keterangan</td>' +
                    '</tr>';
            }
            if(keterangan == "resep"){
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
            var url = contextPath+'/pages/images/minus-allnew.png';
            $('#btn_' + id).attr('src', url);
            $('#btn_' + id).attr('onclick', 'delDetail(\'' + id + '\',\''+idTindakan+'\', \''+keterangan+'\')');
        });
    }
}

function delDetail(id, idTindakan, keterangan){
    $('#del_' + id).remove();
    var url = contextPath+'/pages/images/icons8-plus-25.png';
    $('#btn_' + id).attr('src', url);
    $('#btn_' + id).attr('onclick', 'detailTindakan(\'' + id + '\', \''+idTindakan+'\', \''+keterangan+'\')');
}

function cekDataNull(item){
    var data = "";
    if(item != null && item != ''){
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
        if (newImg<0) {
            newImg = document.getElementsByClassName('carousel-img').length-1;
        }
    }

    document.getElementById('carousel-'+currentImg).className = 'carousel-img carousel-img-hidden';
    var displayedCarousel = document.getElementById('carousel-'+newImg);
    displayedCarousel.className = 'carousel-img carousel-img-hidden';

    setTimeout(function() {
        displayedCarousel.className = 'carousel-img carousel-img-displayed';
    },20);

    setTimeout(function() {
        document.getElementById('carousel-'+currentImg).className = 'carousel-img carousel-img-noDisplay';
    },520);

}
