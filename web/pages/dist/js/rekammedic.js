// rekam medic start

function initRekamMedic() {
    console.log("initRekamMedic ==> klik");
    var idPasien = $("#id_pasien").val();
    var table = "";
    var namaPasien = "";
    var thead = "";
    CheckupAction.listRekamMedic(idPasien, function (response) {
        // console.log(response);

        thead = "<tr></tr><td>No Checkup</td>"+
            "<td>Diagnosa Terakhir</td>"+
            "<td>Tanggal Masuk</td>"+
            "<td>Tanggal Keluar</td>"+
            "<td>View Details RM</td></tr>";

        $.each(response, function (i, item) {
            var noCheckup = "";
            var dignosa = "";
            var tanggal = "";
            var dateFormatMasuk = "";
            var dateFormatKeluar = "";

            if(item.noCheckup != null){
                noCheckup = item.noCheckup;
            }
            if(item.diagnosa != null){
                dignosa = item.diagnosa;
            }
            if(item.stTglMasuk != null && item.stTglKeluar != null){
                tanggalMasuk = item.stTglMasuk;
                tanggalKeluar = item.stTglKeluar;
                dateFormatMasuk = $.datepicker.formatDate('dd-mm-yy', new Date(tanggalMasuk));
                dateFormatKeluar = $.datepicker.formatDate('dd-mm-yy', new Date(tanggalKeluar));
            }
            table += "<tr>" +
                "<td>" + noCheckup + "</td>" +
                "<td>" + dignosa + "</td>" +
                "<td align='center'>" + dateFormatMasuk + "</td>" +
                "<td align='center'>" + dateFormatKeluar + "</td>" +
                "<td align='center'><button class=\"btn btn-primary\" onclick=\"viewDetailRekamMedic('"+item.noCheckup+"')\">View</button></td>" +
                "</tr>";

            namaPasien = item.namaPasien;
        });

        $("#modal-rekam-medic").modal('show');
        $('#nama_medik').html(namaPasien);
        $("#body-rekam-medic").html(thead+table);
    });
}

function getByTypeRekamMedic(type) {
    console.log("getByTypeRekamMedic ==> klik");

    var idPasien = $("#id_pasien").val();
    var table = "";
    var namaPasien = "";
    var thead = "";

    if (type == "lama"){
        CheckupAction.getListRekamMedicLama(idPasien, function (response) {
            // console.log(response);

            thead = "<tr></tr><td>ID</td>"+
                    "<td>Created Date</td>"+
                    "<td>View Detail RM</td></tr>";

            $.each(response, function (i, item) {
                table += "<tr>" +
                    "<td>" + item.id + "</td>" +
                    "<td>" + item.stDate + "</td>" +
                    "<td align='center'><button class=\"btn btn-primary\" onclick=\"viewDetailRekamMedicLama('"+item.id+"')\">View</button></td>" +
                    "</tr>";
            });

            $("#body-rekam-medic").html("");
            $("#body-rekam-medic").html(thead+table);
        });
    }
    if (type == "baru"){

        CheckupAction.listRekamMedic(idPasien, function (response) {
            // console.log(response);

            thead = "<tr></tr><td>No Checkup</td>"+
                    "<td>Diagnosa Terakhir</td>"+
                    "<td>Tanggal Masuk</td>"+
                    "<td>Tanggal Keluar</td>"+
                    "<td>View Details RM</td></tr>";

            $.each(response, function (i, item) {
                var noCheckup = "";
                var dignosa = "";
                var tanggal = "";
                var dateFormatMasuk = "";
                var dateFormatKeluar = "";

                if(item.noCheckup != null){
                    noCheckup = item.noCheckup;
                }
                if(item.diagnosa != null){
                    dignosa = item.diagnosa;
                }
                if(item.stTglMasuk != null && item.stTglKeluar != null){
                    tanggalMasuk = item.stTglMasuk;
                    tanggalKeluar = item.stTglKeluar;
                    dateFormatMasuk = $.datepicker.formatDate('dd-mm-yy', new Date(tanggalMasuk));
                    dateFormatKeluar = $.datepicker.formatDate('dd-mm-yy', new Date(tanggalKeluar));
                }
                table += "<tr>" +
                    "<td>" + noCheckup + "</td>" +
                    "<td>" + dignosa + "</td>" +
                    "<td align='center'>" + dateFormatMasuk + "</td>" +
                    "<td align='center'>" + dateFormatKeluar + "</td>" +
                    "<td align='center'><button class=\"btn btn-primary\" onclick=\"viewDetailRekamMedic('"+item.noCheckup+"')\">View</button></td>" +
                    "</tr>";

                namaPasien = item.namaPasien;
            });

            $('#nama_medik').html(namaPasien);
            $("#body-rekam-medic").html("");
            $("#body-rekam-medic").html(thead+table);
        });
    }

}

function viewDetailRekamMedicLama(headId) {
    console.log("getByTypeRekamMedic ==> "+ headId);
    var str = "";
    var indicator = "";
    CheckupAction.getListUploadRekamMedic(headId, function (response) {
        if (response.length > 0){
            $.each(response, function (i, item) {
                if (i == 0){
                    indicator += '<li data-target="#carouselExampleIndicators" data-slide-to="'+i+'" class="active"></li>';
                    str += '<div class="carousel-item active">'+
                           '<img class="d-block w-100" src="'+item.urlImg+'" alt="'+i+' slide">'+
                           '</div>';
                } else {
                    indicator += '<li data-target="#carouselExampleIndicators" data-slide-to="'+i+'"></li>';
                    str += '<div class="carousel-item">'+
                        '<img class="d-block w-100" src="'+item.urlImg+'" alt="'+i+' slide">'+
                        '</div>';
                }
            });
            $("#modal-detail-rekam-medic-lama").modal("show");
            $("#indicator-img").html(indicator);
            $("#body-img-rm").html(str);
        }
    })
}

function viewDetailRekamMedic(noCheckup){
    console.log("viewDetailRekamMedic ==> klik");
    var urlImg = $("#img_ktp").val();
// <%--var surl = "<s:url value='/pages/images/unknown-person.png'>";--%>
    var str = "";
    CheckupAction.getDataCheckupPasien(noCheckup, function(response){
        var item = response;
        str = "<p style='font-weight:bold'>"+item.namaPasien+"</p><div class=\"row\">"+
            "<div class='col-md-3'><img src='/simrs/pages/images/nobody_m.original.jpg' style='width:100%;max-height:150px'/></div>"+
            "<div class='col-md-6'>"+
            "<table class='table table-bordered table-striped' style='font-size:12px'><tbody>"+
            "<tr><td>Diagnosa terakhir :</td><td>"+item.diagnosa+"</td></tr>"+
            "<tr><td>Tanggal Masuk :</td><td>"+item.stTglMasuk+"</td></tr>"+
            "<tr><td>Tiagnosa Keluar :</td><td>"+item.stTglKeluar+"</td></tr>"+
            "</tbody></table>"+
            "</div>"+
            "</div>";
        $("#list-body-rekam-medic").html("");
//        $("#tab_1").attr("class","active");
        $("#head-detail-rm").html("");
        $("#head-detail-rm").html(str);
    });

    $("#rm-no-checkup").val(noCheckup);
    console.log(noCheckup);
    $("#modal-detail-rekam-medic").modal("show");
}

function viewDetailRekamMedicByKategori(kategori){
    var noCheckup =   $("#rm-no-checkup").val();
    if (kategori == "ri") {
        CheckupAction.getListKategoriSkorRanapByHead(kategori, function (response) {
            // console.log(response);
            var top = "";
            var str = "";
            var btn = "";
            var bottom = "";
            var par = [];
            $.each(response, function(i, item) {
                var type = "";
                if (item.type == "skor"){
                    type = "skor";
                } else {
                    type = "asesmen";
                }
                par.push({"namaKategori":item.namaKategori, "idKategori": item.idKategori, "type":type});
            });
            par.push({"namaKategori":"Tindakan Rawat Inap", "idKategori": "ri", "type":"tindakan"});

            $.each(par, function(i, item) {
                top = "<div class='row' style='margin-top:10px'>"+
                      "<div class='col-md-8'>"+item.namaKategori+"</div>";
                btn = "<div class='col-md-4 pull-right'><button class=\"btn btn-primary\" onclick=\"showSkorRanapRm(this.id, '"+noCheckup+"','"+item.idKategori+"','"+item.type+"')\" id=\"mon-rm-"+i+"\"> View</button></div>";

                // if (item.type == "skor") {
                //     btn = "<div class='col-md-4 pull-right'><button class=\"btn btn-primary\" onclick=\"showSkorRanapRm(this.id, '"+noCheckup+"','"+item.idKategori+"','skor')\" id=\"mon-rm-"+i+"\"> View</button></div>";
                // } else {
                //     btn = "<div class='col-md-4 pull-right'><button class=\"btn btn-primary\" onclick=\"showSkorRanapRm(this.id, '"+noCheckup+"','"+item.idKategori+"','asesmen')\" id=\"mon-rm-"+i+"\"> View</button></div>";
                // }

                bottom = "</div>"+
                    "<div id=\"body-mon-rm-"+i+"\"></div>";
                str += top+btn+bottom;
            });
            $("#list-body-rekam-medic").html("");
            $("#list-body-rekam-medic").html(str);
        });
    }
    else {
        $("#list-body-rekam-medic").html("");
        var par = [];
        var str = "";
        if (kategori == "mon") {

            par.push({'label': "Observasi Cairan", 'kat': "cairan"},
                {'label': "Observasi Vital Sign", 'kat':"vitalsign"},
                {'label': "Observasi pemberian obat parenteral", 'kat':"parenteral"},
                {'label': "Observasi pemberian obat nonparenteral", 'kat':"nonparenteral"}
            );
            $.each(par, function(i, item) {
                str += "<div class='row' style='margin-top:10px'>"+
                    "<div class='col-md-8'>"+item.label+"</div>"+
                    "<div class='col-md-4 pull-right'><button class=\"btn btn-primary\" onclick=\"showDetailMonitoringRm(this.id, '"+noCheckup+"','"+item.kat+"')\" id=\"mon-rm-"+i+"\"> View</button></div>"+
                    "</div>"+
                    "<div id=\"graf-mon-rm-"+i+"\"></div>"+
                    "<div id=\"body-mon-rm-"+i+"\"></div>";
            });
            $("#list-body-rekam-medic").html(str);
        }
        else if (kategori == "igd") {

            par.push({'label': "Pemeriksaan Fisik", 'kat': "fisik"},
                {'label': "Psikosial", 'kat':"psikososial"},
                {'label': "Rencana Keperawatan", 'kat':"rencana"},
                {'label': "Resiko Jatuh", 'kat':"resikojatuh"},
                {'label': "Rekonsiliasi Obat", 'kat':"rekonsiliasi"},
                {'label': "Tindakan IGD", 'kat':"tindakan"}
            );

            $.each(par, function(i, item) {
                str += "<div class='row' style='margin-top:10px'>"+
                    "<div class='col-md-8'>"+item.label+"</div>"+
                    "<div class='col-md-4 pull-right'><button class=\"btn btn-primary\" onclick=\"showDetailIgdRm(this.id, '"+noCheckup+"','"+item.kat+"')\" id=\"mon-rm-"+i+"\"> View</button></div>"+
                    "</div>"+
                    "<div id=\"body-mon-rm-"+i+"\"></div>";
            });
            $("#list-body-rekam-medic").html(str);
        }
        else {

            par.push({'label': "Masuk Rumah Sakit", 'kat': "masuk"},
                {'label': "Data Tranfusi", 'kat':"tranfusi"},
                {'label': "Data Patrus", 'kat':"patrus"},
                {'label': "Keluar Rumah Sakit", 'kat':"keluar"}
            );

            $.each(par, function(i, item) {
                str += "<div class='row' style='margin-top:10px'>"+
                    "<div class='col-md-8'>"+item.label+"</div>"+
                    "<div class='col-md-4 pull-right'><button class=\"btn btn-primary\" onclick=\"showDetailTppriRm(this.id, '"+noCheckup+"','"+item.kat+"')\" id=\"mon-rm-"+i+"\"> View</button></div>"+
                    "</div>"+
                    "<div id=\"body-mon-rm-"+i+"\"></div>";
            });
            $("#list-body-rekam-medic").html(str);

        }
    }
}

function showDetailMonitoringRm(id, noCheckup, kategori){
    console.log("showDetailMonitoringRm ==> klik");
    console.log(id+"-"+noCheckup+"-"+kategori);

    $("#"+id+"").hide();

    var str = "";
    var headstr = "";
    var tablehead = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>";
    var tabledown = "</table><hr/>";
    var upthead = "<thead style='font-weight: bold;'>";
    var downthead = "</thead>";
    var uptbody = "<tbody>";
    var downtbody = "</tbody>";
    if (kategori == "cairan") {
        RawatInapAction.getListMonCairan(noCheckup, "", "", function(response){
            console.log(response);
            if (response.length > 0) {

                headstr = upthead +
                    "<tr>"+
                    "<td>Created Who</td>"+
                    "<td>tgl</td>"+
                    "<td>Macam Cairan</td>"+
                    "<td>Melalui</td>"+
                    "<td>jumlah</td>"+
                    "<td>Jam Mulai</td>"+
                    "<td>Jam Selesai</td>"+
                    "<td>Cek Tambahan Obat</td>"+
                    "<td>Sisa</td>"+
                    "<td>Jam Ukur Buang</td>"+
                    "<td>Dari</td>"+
                    "<td>Balance Cairan</td>"+
                    "<td>Keterangan</td>"+
                    "</tr>" + downthead;

                $.each(response, function(i, item) {
                    str += "<tr>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+item.stDate+"</td>"+
                        "<td>"+item.macamCairan+"</td>"+
                        "<td>"+item.melalui+"</td>"+
                        "<td>"+item.jumlah+"</td>"+
                        "<td>"+item.jamMulai+"</td>"+
                        "<td>"+item.jamSelesai+"</td>"+
                        "<td>"+item.cekTambahanObat+"</td>"+
                        "<td>"+item.sisa+"</td>"+
                        "<td>"+item.jamUkurBuang+"</td>"+
                        "<td>"+item.dari+"</td>"+
                        "<td>"+item.balanceCairan+"</td>"+
                        "<td>"+item.keterangan+"</td>"+
                        "</tr>";
                });
                $("#body-"+id+"").html(tablehead+headstr+uptbody+str+downtbody+tabledown);
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }

    if (kategori == "vitalsign") {
        RawatInapAction.getListMonVitalSign(noCheckup, "", "", function(response){
            console.log(response);
            if (response.length > 0) {
                headstr = upthead +
                    "<tr>"+
                    "<td>Created Who</td>"+
                    "<td>Created Date</td>"+
                    "<td>Jam</td>"+
                    "<td>Nafas</td>"+
                    "<td>Nadi</td>"+
                    "<td>Suhu</td>"+
                    "<td>Tensi</td>"+
                    "<td>Berat Badan (Kg)</td>"+
                    "<td>Tinggi Badan (cm)</td>"+
                    "</tr>" + downthead;

                $.each(response, function(i, item) {
                    str += "<tr>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+item.stDate+"</td>"+
                        "<td>"+item.jam+"</td>"+
                        "<td>"+item.nafas+"</td>"+
                        "<td>"+item.nadi+"</td>"+
                        "<td>"+item.suhu+"</td>"+
                        "<td>"+item.tensi+"</td>"+
                        "<td>"+item.bb+"</td>"+
                        "<td>"+item.tb+"</td>"+
                        "</tr>";
                });

                $("#body-"+id+"").html(tablehead+headstr+uptbody+str+downtbody+tabledown);
                $("#graf-"+id+"").html('<div id="line-chart-rm" style="height:300px;"></div>'+
                    '<p style="margin-top:50px;"><i class="fa fa-circle" style="color:#3a4dc9"></i> Suhu  '+
                    '<i class="fa fa-circle" style="color:#eb4034"></i> Nadi  '+
                    '<i class="fa fa-circle" style="color:#6b6b6b"></i> Nafas</p>');

                var suhu = [], nadi = [], nafas = [], label = [];
                RawatInapAction.getListGraf("", noCheckup, function(grafs){
                    console.log(grafs)
                    $.each(grafs, function(i, item){
                        suhu.push([i,item.suhu]);
                        nadi.push([i,item.nadi]);
                        nafas.push([i,item.nafas]);
                        label.push([i,item.stDate]);
                    });

                    /*
                   * LINE CHART
                   * ----------
                   */
                    var line_data1 = { data : suhu, color: '#3a4dc9' }
                    var line_data2 = { data : nadi, color: '#eb4034' }
                    var line_data3 = { data : nafas, color: '#6b6b6b' }

                    $.plot("#line-chart-rm", [line_data1, line_data2, line_data3], {
                        grid  : { hoverable  : true, borderColor: '#f3f3f3', borderWidth: 1, tickColor  : '#f3f3f3'},
                        series: { shadowSize: 0, lines : { show: true }, points : { show: true } },
                        lines : { fill : false, color: ['#3c8dbc', '#f56954'] },
                        yaxis : { show: true },
                        xaxis : { show: true, ticks: label }
                    })
                    //Initialize tooltip on hover
                    $('<div class="tooltip-inner" id="line-chart-tooltip"></div>').css({
                        position: 'absolute',
                        display : 'none',
                        opacity : 0.8
                    }).appendTo('body')
                    $("#line-chart-rm").bind('plothover', function (event, pos, item) {

                        if (item) {
                            var x = item.datapoint[0].toFixed(2),
                                y = item.datapoint[1].toFixed(2)

                            $('#line-chart-tooltip').html(parseInt(y))
                                .css({ top: item.pageY + 5, left: item.pageX + 5})
                                .fadeIn(200)
                        } else {
                            $('#line-chart-tooltip').hide()
                        }
                    })
                    /* END LINE CHART */
                });
            } else {
                setTidakAdaRekamMedic(id)

            }
        });
    } if (kategori == "parenteral") {
        RawatInapAction.getListMonPemberianObat(noCheckup, "", kategori, "",  function(response){
            console.log(response);
            if (response.length > 0) {
                headstr = upthead +
                    "<tr>"+
                    "<td>Created Who</td>"+
                    "<td>Created Date</td>"+
                    "<td>Nama Obat</td>"+
                    "<td>Cara Pemberian</td>"+
                    "<td>Dosis</td>"+
                    "<td>Skin Tes</td>"+
                    "<td>Waktu</td>"+
                    "<td>Keterangan</td>"+
                    "</tr>" + downthead;

                $.each(response, function(i, item){
                    str += "<tr>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+item.stDate+"</td>"+
                        "<td>"+item.namaObat+"</td>"+
                        "<td>"+item.caraPemberian+"</td>"+
                        "<td>"+item.dosis+"</td>"+
                        "<td>"+item.skinTes+"</td>"+
                        "<td>"+item.waktu+"</td>"+
                        "<td>"+item.keterangan+"</td>"+
                        "</tr>";
                });
                $("#body-"+id+"").html(tablehead+headstr+uptbody+str+downtbody+tabledown);
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
    if (kategori == "nonparenteral") {
        RawatInapAction.getListMonPemberianObat(noCheckup, "", kategori, "",  function(response){
            console.log(response);
            if (response.length > 0) {
                headstr = upthead +
                    "<tr>"+
                    "<td>Created Who</td>"+
                    "<td>Created Date</td>"+
                    "<td>Nama Obat</td>"+
                    "<td>Dosis</td>"+
                    "<td>Waktu</td>"+
                    "<td>Keterangan</td>"+
                    "</tr>" + downthead;

                $.each(response, function(i, item){
                    str += "<tr>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+item.stDate+"</td>"+
                        "<td>"+item.namaObat+"</td>"+
                        "<td>"+item.dosis+"</td>"+
                        "<td>"+item.waktu+"</td>"+
                        "<td>"+item.keterangan+"</td>"+
                        "</tr>";
                });
                $("#body-"+id+"").html(tablehead+headstr+uptbody+str+downtbody+tabledown);
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
}

function showSkorRanapRm(id, noCheckup, idKategori, type){
    console.log("showDetailMonitoringRm ==> "+id+"-"+noCheckup+"-"+idKategori+"-"+type);
    $("#"+id+"").hide();

    var str = "";
    var table = "";
    var headstr = "";
    var tablehead = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>";
    var tabledown = "</table>";
    var upthead = "<thead style='font-weight: bold;'>";
    var downthead = "</thead>";
    var uptbody = "<tbody>";
    var downtbody = "</tbody>";
    RawatInapAction.getListGroupSkorRanap(noCheckup, "", idKategori, function(response){

        if (response.length > 0) {
            if (type == "skor"){
                headstr = upthead +
                    "<tr>"+
                    "<td>Created Who</td>"+
                    "<td>Created Date</td>"+
                    "<td>Asesmen</td>"+
                    "<td>Total Skor</td>"+
                    "<td>View Detail</td>"+
                    "</tr>" + downthead;

                $.each(response, function(i, item){
                    str = "<tr>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+item.stDate+"</td>"+
                        "<td>"+item.namaKategori+"</td>"+
                        "<td>"+item.skor+"</td>"+
                        "<td align=\"center\"><button id=\""+idKategori+"-"+i+"\" class='btn btn-primary' onclick=\"viewDetailSkorRm('"+item.groupId+"', '"+type+"', this.id)\"><i class=\"fa fa-search\"></i></button></td>"+
                        "</tr>";

                    table += tablehead+headstr+uptbody+str+downtbody+tabledown+"<div id=\"body-"+idKategori+"-"+i+"\"></div>";
                });
            }
            if (type == "asesmen"){
                headstr = upthead +
                    "<tr>"+
                    "<td>Created Who</td>"+
                    "<td>Created Date</td>"+
                    "<td>Asesmen</td>"+
                    "<td>View Detail</td>"+
                    "</tr>" + downthead;

                $.each(response, function(i, item){
                    str = "<tr>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+item.stDate+"</td>"+
                        "<td>"+item.namaKategori+"</td>"+
                        "<td align=\"center\"><button id=\""+idKategori+"-"+i+"\" class='btn btn-primary' onclick=\"viewDetailSkorRm('"+item.groupId+"', '"+type+"', this.id)\"><i class=\"fa fa-search\"></i></button></td>"+
                        "</tr>";

                    table += tablehead+headstr+uptbody+str+downtbody+tabledown+"<div id=\"body-"+idKategori+"-"+i+"\"></div>";
                });
            }
            $("#body-"+id+"").html(table+"<hr/>");
        } else if (type == "tindakan") {
            CheckupAction.getListTindakanRawat(noCheckup, "ri", function (response) {
                if (response.length > 0) {
                    headstr = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>" +
                        "<thead>" +
                        "<tr>" +
                        "<td>Created Who</td>" +
                        "<td>Created Date</td>" +
                        "<td>Nama Tindakan</td>" +
                        "</tr></thead>";

                    str += "<tbody>";
                    $.each(response, function (i, item) {
                        str += "<tr>" +
                            "<td>" + item.createdWho + "</td>" +
                            "<td>" + item.stDate + "</td>" +
                            "<td>" + item.namaTindakan + "</td>" +
                            "</tr>";
                    });
                    str += "</tbody></table>";
                    table = headstr + str;
                    $("#body-"+id+"").html(table+"<hr/>");
                } else {
                    setTidakAdaRekamMedic(id)
                }
            });
        } else {
            setTidakAdaRekamMedic(id)
        }

        // console.log(response);
    });
}

function viewDetailSkorRm(groupId, type, id){
    console.log("viewDetailSkorRm ==> "+groupId+"-"+type+"-"+id);
    $("#"+id+"").removeAttr("class");
    $("#"+id+"").removeAttr("onclick");
    $("#"+id+"").attr("class","btn btn-secondary");

    var tablehead = "<p>Detail :</p><table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>";
    var tabledown = "</table>";
    var upthead = "<thead style='font-weight: bold;'>";
    var downthead = "</thead>";
    var uptbody = "<tbody>";
    var downtbody = "</tbody>";
    RawatInapAction.getListViewSkorRanapByGrupId(groupId, function(response){
        console.log(response);
        var str = "";
        if (response.length > 0) {
            if (type == "skor") {
                $.each(response, function(i,item){
                    str += "<tr>"+
                        "<td>"+item.namaParameter+"</td>"+
                        "<td>"+item.skor+"</td>"+
                        "<td>"+item.keterangan+"</td>"+
                        "</tr>";
                });
            } else {
                $.each(response, function(i,item){
                    str += "<tr>"+
                        "<td>"+item.namaParameter+"</td>"+
                        "<td>"+item.keterangan+"</td>"+
                        "</tr>";
                });
            }
            $("#body-"+id+"").html(tablehead+uptbody+str+downtbody+tabledown);
        }
    });
}

function showDetailIgdRm(id, noCheckup, kategori){
    console.log("showDetailIgdRm ==> "+id+" | "+noCheckup+" | "+kategori);
    $("#"+id+"").hide();
    var head = "";
    var isi = [];
    var str = "";
    var tbodyhead = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'><tbody>";
    var tbodybottom = "</tbody></table>";
    if (kategori == "fisik"){
        CheckupAction.getPemeriksaanFisikByNoCheckup(noCheckup, function(response){
            // console.log(response);
            if(response.id != null){

                head = "<table class='table table-bordered table-striped' width='60%' style='font-size:11px;margin-top:10px;'><tbody>"+
                    "<tr><td>Created Who</td><td>"+response.createdWho+"</td></tr>"+
                    "<tr><td>Created Date</td><td>"+response.createdDate+"</td></tr>"+
                    "</tbody></table>";

                isi.push(
                    {"label": "Tinggi Badan (cm)", "nilai":response.tinggiBadan },
                    {"label": "Berat Badan (Kg)", "nilai":response.beratBadan },
                    {"label": "Nadi", "nilai":response.nadi },
                    {"label": "Respiration Rate", "nilai":response.respirationRate },
                    {"label": "Tekanan Darah", "nilai":response.tekananDarah },
                    {"label": "Suhu ", "nilai":response.suhu },
                    {"label": "Kepala", "nilai":response.kepala },
                    {"label": "Mata", "nilai":response.mata },
                    {"label": "Leher", "nilai":response.leher },
                    {"label": "Thorak", "nilai":response.thorak },
                    {"label": "Thorak Chor", "nilai":response.thorakChor },
                    {"label": "Thorak Pulmo", "nilai":response.thorakPulmo },
                    {"label": "Abdoman", "nilai":response.abdoman },
                    {"label": "Extremitas", "nilai":response.extrimitas }
                );


                $.each(isi,function(i, item) {
                    str += "<tr><td>"+item.label+"</td><td>"+item.nilai+"</td></tr>";
                });
                $("#body-"+id+"").html(head+tbodyhead+str+tbodybottom+"<hr/>");
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
    if (kategori == "psikososial"){
        CheckupAction.getPsikososial(noCheckup, function (response) {
            if(response.id != null){
                head = "<table class='table table-bordered table-striped' width='60%' style='font-size:11px;margin-top:10px;'><tbody>"+
                    "<tr><td>Created Who</td><td>"+response.createdWho+"</td></tr>"+
                    "<tr><td>Created Date</td><td>"+response.createdDate+"</td></tr>"+
                    "</tbody></table>";

                isi.push(
                    {"label": "Komunikasi", "nilai":response.komunikasi },
                    {"label": "Kemampuan Bicara", "nilai":response.kemampuanBicara },
                    {"label": "Konsep Diri", "nilai":response.konsepDiri },
                    {"label": "Pernah Dirawat", "nilai":response.pernahDirawat },
                    {"label": "Tahu Tentang SakitNya", "nilai":response.tahuTentangSakitNya },
                    {"label": "Obat Dari Rumah ", "nilai":response.obatDariRumah },
                    {"label": "Nyeri", "nilai":response.nyeri },
                    {"label": "Intensitas Nyeri", "nilai":response.intensitasNyeri },
                    {"label": "Jenis Intensitas Nyeri", "nilai":response.jenisIntensitasNyeri },
                    {"label": "Numeric Rating Scale", "nilai":response.numericRatingScale },
                    {"label": "Wong Baker Pain Scale", "nilai":response.wongBakerPainScale }
                );

                $.each(isi,function(i, item) {
                    str += "<tr><td>"+item.label+"</td><td>"+item.nilai+"</td></tr>";
                });
                $("#body-"+id+"").html(head+tbodyhead+str+tbodybottom+"<hr/>");
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
    if (kategori == "resikojatuh"){
        CheckupAction.getListResikoJatuh(noCheckup, "", function(response){
            console.log(response);
            if (response.status == "success"){
                if (response.resikoJatuhEntityList[0].id != null){
                    var createdDate = "";
                    var createdWho = "";
                    var sumSkor = 0;

                    $.each(response.resikoJatuhEntityList, function (i, item) {
                        str += "<tr><td>"+item.namaParameter+"</td><td>"+item.skor+"</td></tr>";
                        sumSkor += item.skor;
                        createdWho  = item.createdWho;
                        createdDate = item.createdDate;
                    });

                    head = "<table class='table table-bordered table-striped' width='60%' style='font-size:11px;margin-top:10px;'><tbody>"+
                        "<tr><td>Created Who</td><td>"+createdWho+"</td></tr>"+
                        "<tr><td>Created Date</td><td>"+createdDate+"</td></tr>"+
                        "</tbody></table>";

                    str += "<tr style='font-weight:bold;'><td>Total Skor</td><td>"+sumSkor+"</td></tr>";
                    $("#body-"+id+"").html(head+tbodyhead+str+tbodybottom+"<hr/>");
                } else {
                    setTidakAdaRekamMedic(id)
                }
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
    if (kategori == "rencana") {
        CheckupAction.getListRencanaRawat(noCheckup, "", "rigd", function(response){
            console.log(response);
            if (response[0].idRencana != null) {

                $.each(response, function (i, item) {
                    var upline = "<tr><td>"+item.namaParameter+"</td><td align='center'>";
                    var opt = "";
                    if (item.check == "Y") {
                        opt = "<i class='fa fa-check'></i>"
                    }
                    var downline = "</td></tr>";
                    str += upline+opt+downline;
                    createdWho  = item.createdWho;
                    createdDate = item.createdDate;
                });

                head = "<table class='table table-bordered table-striped' width='60%' style='font-size:11px;margin-top:10px;'><tbody>"+
                    "<tr><td>Created Who</td><td>"+createdWho+"</td></tr>"+
                    "<tr><td>Created Date</td><td>"+createdDate+"</td></tr>"+
                    "</tbody></table>";

                $("#body-"+id+"").html(head+tbodyhead+str+tbodybottom+"<hr/>");
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
    if (kategori == "rekonsiliasi") {
        CheckupAction.getListRekonsiliasiObat(noCheckup, function (response) {
            if (response.length > 0) {
                head = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>"+
                    "<thead>"+
                    "<tr>"+
                    "<td>Nama Obat</td>"+
                    "<td>Bentuk</td>"+
                    "<td>Dosis</td>"+
                    "<td>Frekuensi</td>"+
                    "<td>Rute</td>"+
                    "<td>Permintaan Obat di Berikan Saat Masuk</td>"+
                    "<td>Obat Dari Rumah Dilanjutkan Saat Pulang</td>"+
                    "</tr></thead>";

                str += "<tbody>";
                $.each(response, function(i,item){
                    str += "<tr>"+
                        "<td>"+item.namaObat+"</td>"+
                        "<td>"+item.bentuk+"</td>"+
                        "<td>"+item.dosis+" "+item.satuanDosis+"</td>"+
                        "<td>"+item.frekuensi+"</td>"+
                        "<td>"+item.rute+"</td>"+
                        "<td align='center'>"+convertToCheck(item.obatMasukFlag)+"</td>"+
                        "<td align='center'>"+convertToCheck(item.obatDariRumahFlag)+"</td>"+
                        "</tr>";
                });
                str += "</tbody></table>";
                $("#body-"+id+"").html(head+str+"<hr/>");
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }

    if (kategori == "tindakan"){

        CheckupAction.getListTindakanRawat(noCheckup, "igd", function (response) {
            if (response.length > 0) {
                head = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>"+
                    "<thead>"+
                    "<tr>"+
                    "<td>Created Who</td>"+
                    "<td>Created Date</td>"+
                    "<td>Nama Tindakan</td>"+
                    "</tr></thead>";

                str += "<tbody>";
                $.each(response, function(i,item){
                    str += "<tr>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+item.stDate+"</td>"+
                        "<td>"+item.namaTindakan+"</td>"+
                        "</tr>";
                });
                str += "</tbody></table>";
                $("#body-"+id+"").html(head+str+"<hr/>");
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
}

function showDetailTppriRm(id, noCheckup, kategori){
    console.log("showDetailTppriRm ==> "+id+" | "+noCheckup+" | "+kategori);
    $("#"+id+"").hide();

    var str = "";
    var headstr = "";
    var tablehead = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>";
    var tabledown = "</table>";
    var upthead = "<thead style='font-weight: bold;'>";
    var downthead = "</thead>";
    var uptbody = "<tbody>";
    var downtbody = "</tbody>";
    if (kategori == "tranfusi") {
        CheckupAction.getListTranfusi(noCheckup, function (response) {
            if (response.length > 0) {
                headstr = upthead +
                    "<tr>"+
                    "<td>Created Who</td>"+
                    "<td>Created Date</td>"+
                    "<td>Keterangan Tranfusi</td>"+
                    "<td>CC</td>"+
                    "</tr>" + downthead;

                $.each(response, function(i, item){
                    str += "<tr>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+item.createdDate+"</td>"+
                        "<td>"+item.ketTransfusi+"</td>"+
                        "<td>"+item.cc+"</td>"+
                        "</tr>";
                });
                $("#body-"+id+"").html(tablehead+headstr+uptbody+str+downtbody+tabledown+"<hr/>");
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
    if (kategori == "patrus"){
        CheckupAction.getListPatrus(noCheckup, function (response) {
            if (response.length > 0) {
                headstr = upthead +
                    "<tr>"+
                    "<td>Created Who</td>"+
                    "<td>Created Date</td>"+
                    "<td>Keterangan Patrus</td>"+
                    "</tr>" + downthead;

                $.each(response, function(i, item){
                    str += "<tr>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+item.createdDate+"</td>"+
                        "<td>"+item.ketPatrus+"</td>"+
                        "</tr>";
                });
                $("#body-"+id+"").html(tablehead+headstr+uptbody+str+downtbody+tabledown);
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
    if (kategori == "masuk"){
        CheckupAction.getRingkasanKeluarMasuk(noCheckup, kategori, function (response) {
            if (response.masukMelalui != null) {
                headstr = upthead +
                    "<tr>"+
                    "<td>MRS Melalui</td>"+
                    "<td>Rujukan Dari</td>"+
                    "<td>Waktu Datang</td>"+
                    "<td>Waktu Pindah Ruangan</td>"+
                    "<td>Ruangan</td>"+
                    "</tr>" + downthead;

                str = "<tr>"+
                    "<td>"+response.masukMelalui+"</td>"+
                    "<td>"+checkNull(response.rujukan)+" - "+checkNull(response.ketRujukan)+"</td>"+
                    "<td>"+response.tglMasukRs+"</td>"+
                    "<td>"+response.tglPindahRuang+"</td>"+
                    "<td>"+response.ruang+"</td>"+
                    "</tr>";

                $("#body-"+id+"").html(tablehead+headstr+uptbody+str+downtbody+tabledown+"<hr/>");
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
    if (kategori == "keluar"){
        CheckupAction.getRingkasanKeluarMasuk(noCheckup, kategori, function (response) {
            if (response.idDetailCheckup != null) {
                headstr = upthead +
                    "<tr>"+
                    "<td>Waktu Keluar</td>"+
                    "<td>Keaadan Keluar</td>"+
                    "<td>Cara Pulang</td>"+
                    "</tr>" + downthead;

                str = "<tr>"+
                    "<td>"+response.tglKeluarRs+"</td>"+
                    "<td>"+response.keadaanKeluar+"</td>"+
                    "<td>"+response.caraKeluar+"</td>"+
                    "</tr>";

                $("#body-"+id+"").html(tablehead+headstr+uptbody+str+downtbody+tabledown+"<div id='dokter-"+id+"'></div>");
                if (response.dokterList.length > 0){
                    headstr = "";
                    headstr = upthead +
                        "<tr>"+
                        "<td>Nama Dokter</td>"+
                        "</tr>" + downthead;

                    str = "";
                    $.each(response.dokterList, function (i, item) {
                        str += "<tr>"+
                            "<td>"+item.namaDokter+"</td>"+
                            "</tr>";
                    });
                    $("#dokter-"+id+"").html("<p>Dokter yang Merawat : </p>"+tablehead+headstr+uptbody+str+downtbody+tabledown+"<hr/>");
                }
            } else {
                setTidakAdaRekamMedic(id)
            }
        });
    }
}

function setTidakAdaRekamMedic(id){
    $("#"+id+"").show();
    $("#"+id+"").removeAttr("class");
    $("#"+id+"").removeAttr("onclick");
    $("#"+id+"").attr("class", "btn btn-secondary");
    $("#"+id+"").html("Tidak Ada Rekam Medic");
}

function convertToCheck(item){
    if (item == "Y") {
        return "<i class='fa fa-check'></i>";
    }
}

function checkNull(item){
    if (item == null){
        return "";
    } else {
        return item;
    }
}

// rekam medic end