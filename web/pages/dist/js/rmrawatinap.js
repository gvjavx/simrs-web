
function showModalResiko(noCheckup, idDetail, kat){

    $("#modal-resiko").modal("show");
    $("#kat_skor").val(kat);
    dwr.engine.setAsync(true);
    RawatInapAction.getKategoriSkorRanap(kat, function(kategori){
        $("#label-skor").html("");
        $("#label-skor").html(kategori.namaKategori);

        $("#label-add-resiko").html("");
        $("#label-add-resiko").html(kategori.namaKategori);

        RawatInapAction.getListGroupSkorRanap(noCheckup, idDetail, kat, function(response){
            if (response != null) {
                var str = "";
                $.each(response, function(i, item){
                    str += "<tr>"+
                        "<td>"+item.namaKategori+"</td>"+
                        "<td>"+item.skor+"</td>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+formateDateTime(item.stDate)+"</td>"+
                        "<td><button class='btn btn-primary' onclick=\"viewSkor('"+item.groupId+"')\">View</button></td>"+
                        "</tr>";
                });

                $("#body-list-resiko").html(str);
            }
            // console.log(response);
        });
    });
}

function addResiko(noCheckup, idDetail){
    $("#modal-add-resiko").modal("show");
    var kategori = $("#kat_skor").val();

    dwr.engine.setAsync(true);
    RawatInapAction.getListParameterByKategori(noCheckup, idDetail, kategori, function(response){

        var str = "";
        if (response != null){
            var n = 0;
            var dateupline = "<div class='form-group'>"+
                "<div class='row'>"+
                "<div class='col-md-4'><label>Created Date</label></div>"+
                "<div class='col-md-4'><input type='date' class='form-control' id='val_rsk_date' /></div>"
            "</div>"+
            "</div>";
            $.each(response, function (i, item) {
                n = i;
                var upline = "";
                if (item.namaParameter.length > 25) {
                    upline ="<div class='form-group'>" +
                        "<div class='row'>"+
                        "<div class='col-md-8'>"+
                        "<label>"+item.namaParameter+"</label>"+
                        "</div>"+
                        "<div class='col-md-4'>";
                } else {
                    upline ="<div class='form-group'>" +
                        "<div class='row'>"+
                        "<div class='col-md-4'>"+
                        "<label>"+item.namaParameter+"</label>"+
                        "</div>"+
                        "<div class='col-md-8'>";
                }

                var opt = "";
                RawatInapAction.getListSkorRanapByParam(item.idParameter, function(skors){
                    var up_select = "<select class='form-control' id='val_rsk_"+i+"'>";
                    if (skors.length > 0) {
                        $.each(skors, function(i, itemSkor){
                            if (item.skor == itemSkor.skor){
                                opt += "<option value="+itemSkor.skor+" selected> "+itemSkor.namaSkor+" </option>";
                            } else {
                                opt += "<option value="+itemSkor.skor+"> "+itemSkor.namaSkor+" </option>";
                            }
                        });
                    } else {
                        opt = "<input type='text' class='form-control' id='val_rsk_"+i+"'>";
                    }


                    // console.log(skors);

                    var down_select = "</select>";
                    var downline = "<input type='hidden' id='id_rsk_"+i+"' value='"+item.idParameter+"'>"+
                        "<input type='hidden' id='name_rsk_"+i+"' value='"+item.namaParameter+"'>"+
                        "</div>" +
                        "</div>"+
                        "<hr style='color:#b0b0b0;'/>"+
                        "</div>";
                    // "<div class='box-header with-border' style='margin-bottom: 7px;'></div>";

                    if (skors.length > 0) {
                        str += upline+up_select+opt+down_select+downline;
                    } else {
                        str += upline+opt+downline;
                    }

                    $("#ind_resiko").val(n);
                    $("#body_resiko").html(str);
                });
            });
        }
    });
}

function saveResiko(noCheckup, idDetail){

    var jsonrq = [];
    var ind = $("#ind_resiko").val();

    for (i = 0; i <= ind; i++){

        var id_rsk = $("#id_rsk_"+i+"").val();
        var nilai = $("#val_rsk_"+i+"").val();
        var name_rsk = $("#name_rsk_"+i+"").val();
        var ket_rsk = "";
        var val_rsk = "0";

        var intNilai = parseInt(nilai);
        if (isNaN(intNilai)) {
            ket_rsk = nilai;
        } else {
            val_rsk = nilai;
        }

        jsonrq.push({'id':id_rsk, 'val':val_rsk, 'name':name_rsk, 'ket':ket_rsk});
    }

    var kategori = $("#kat_skor").val();

    var jsonstr = JSON.stringify(jsonrq);
    dwr.engine.setAsync(true);
    RawatInapAction.saveSkorRanapByKategori(noCheckup, idDetail, kategori, jsonstr, function(response){
        if (response.status == "success") {
            alert("sukses");

            RawatInapAction.getListGroupSkorRanap(noCheckup, idDetail, kategori, function(response){
                if (response != null) {
                    var str = "";
                    $.each(response, function(i, item){
                        str += "<tr>"+
                            "<td>"+item.namaKategori+"</td>"+
                            "<td>"+item.skor+"</td>"+
                            "<td>"+item.createdWho+"</td>"+
                            "<td>"+formateDate(item.stDate)+"</td>"+
                            "<td><button class='btn btn-primary' onclick=\"viewSkor('"+item.groupId+"')\">View</button></td>"+
                            "</tr>";
                    });

                    $("#modal-add-resiko").modal("hide");
                    $("#body-list-resiko").html("");
                    $("#body-list-resiko").html(str);
                }
                // console.log(response);
            });

        } else {
            alert(response.msg);
        }
    });
}

function showModalAsesmen(noCheckup, idDetail, kat){

    $("#modal-asesmen").modal("show");
    $("#kat_asesmen").val(kat);
    dwr.engine.setAsync(true);
    RawatInapAction.getKategoriSkorRanap(kat, function(kategori){
        $("#label-asesmen").html("");
        $("#label-asesmen").html(kategori.namaKategori);

        $("#label-add-asesmen").html("");
        $("#label-add-asesmen").html(kategori.namaKategori);

        RawatInapAction.getListGroupSkorRanap(noCheckup, idDetail, kat, function(response){
            if (response != null) {
                // console.log(response);
                var str = "";
                $.each(response, function(i, item){
                    // console.log(response);
                    str += "<tr>"+
                        "<td>"+item.namaKategori+"</td>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+formateDateTime(item.stDate)+"</td>"+
                        "<td align='center'><button class='btn btn-primary' onclick=\"viewAsesmen('"+item.groupId+"')\"><i class='fa fa-search'></i></button></td>"+
                        "</tr>";
                });

                $("#body-list-asesmen").html(str);
            }
        });
    });
}

function addAsesmen(noCheckup, idDetail){
    $("#modal-add-asesmen").modal("show");
    var kategori = $("#kat_asesmen").val();

    dwr.engine.setAsync(true);
    RawatInapAction.getListParameterByKategori(noCheckup, idDetail, kategori, function(response){

        var str = "";
        if (response != null){
            var n = 0;
            $.each(response, function (i, item) {
                n = i;
                var upline = "";
                if (item.namaParameter.length > 25) {
                    upline ="<div class='form-group'>" +
                        "<div class='row'>"+
                        "<div class='col-md-8'>"+
                        "<label>"+item.namaParameter+"</label>"+
                        "</div>"+
                        "<div class='col-md-4'>";
                } else {
                    upline ="<div class='form-group'>" +
                        "<div class='row'>"+
                        "<div class='col-md-4'>"+
                        "<label>"+item.namaParameter+"</label>"+
                        "</div>"+
                        "<div class='col-md-8'>";
                }

                var opt = "";
                RawatInapAction.getListSkorRanapByParam(item.idParameter, function(skors){
                    var up_select = "<select class='form-control' id='val_rsk_"+i+"' onchange=\"showOtherInput(this.id)\">";
                    // var other_text = "<input type='text' class='form-control' id='ot_val_rsk_"+i+"' style='display:none'/>";
                    if (skors.length > 0) {
                        $.each(skors, function(i, itemSkor){
                            opt += "<option value="+itemSkor.ketSkor+">"+itemSkor.namaSkor+"</option>";
                        });
                    } else {
                        if (item.type == "date") {
                            opt = "<input type='date' class='form-control' id='val_rsk_"+i+"'>";
                        } else if (item.type == "number"){
                            opt = "<input type='number' class='form-control' id='val_rsk_"+i+"'>";
                        } else {
                            opt = "<input type='text' class='form-control' id='val_rsk_"+i+"'>";
                        }
                    }


                    // console.log(skors);

                    var down_select = "</select>";
                    var downline = "<input type='hidden' id='id_rsk_"+i+"' value='"+item.idParameter+"'>"+
                        "<input type='hidden' id='name_rsk_"+i+"' value='"+item.namaParameter+"'>"+
                        "<input type='text' class='form-control' id='ot_val_rsk_"+i+"' style='display:none' placeholder='sebutkan ...'/>"+
                        "</div>" +
                        "</div>"+
                        "<hr style='color:#b0b0b0;'/>"+
                        "</div>";
                    // "<div class='box-header with-border' style='margin-bottom: 7px;'></div>";

                    if (skors.length > 0) {
                        str += upline+up_select+opt+down_select+downline;
                    } else {
                        str += upline+opt+downline;
                    }

                    $("#ind_asesmen").val(n);
                    $("#body_asesmen").html(str);
                });
            });
        }
    });
}

function saveAsesmen(noCheckup, idDetail){

    var jsonrq = [];
    var ind = $("#ind_asesmen").val();

    for (i = 0; i <= ind; i++){

        var id_rsk = $("#id_rsk_"+i+"").val();

        var nilai = "";
        if ($("#val_rsk_"+i+"").is("select")) {
            nilai = $("#val_rsk_"+i+" option:selected").text();
        } else {
            nilai = $("#val_rsk_"+i+"").val();
        }

        // var nilai = $("#val_rsk_"+i+"").val();
        // var nilai = $("#val_rsk_"+i+" option:selected").text();
        var name_rsk = $("#name_rsk_"+i+"").val();
        var ket_rsk = "";
        var val_rsk = "0";

        if (nilai.toLowerCase() == "lain") {
            ket_rsk = $("#ot_val_rsk_"+i+"").val();
        } else {
            ket_rsk = nilai;
        }
        jsonrq.push({'id':id_rsk, 'val':val_rsk, 'name':name_rsk, 'ket':ket_rsk});
    }

    var kategori = $("#kat_asesmen").val();

    var jsonstr = JSON.stringify(jsonrq);
    dwr.engine.setAsync(true);
    RawatInapAction.saveSkorRanapByKategori(noCheckup, idDetail, kategori, jsonstr, function(response){
        if (response.status == "success") {
            alert("sukses");

            RawatInapAction.getListGroupSkorRanap(noCheckup, idDetail, kategori, function(response){
                if (response != null) {
                    var str = "";
                    $.each(response, function(i, item){
                        str += "<tr>"+
                            "<td>"+item.namaKategori+"</td>"+
                            "<td>"+item.createdWho+"</td>"+
                            "<td>"+formateDate(item.stDate)+"</td>"+
                            "<td><button class='btn btn-primary' onclick=\"viewAsesmen('"+item.groupId+"')\">View</button></td>"+
                            "</tr>";
                    });

                    $("#modal-add-asesmen").modal("hide");
                    $("#body-list-asesmen").html("");
                    $("#body-list-asesmen").html(str);
                }
                // console.log(response);
            });

        } else {
            alert(response.msg);
        }
    });
}

function viewAsesmen(noGroup){
    $("#modal-view-asesmen").modal("show");
    var kat = $("#kat_asesmen").val();

    dwr.engine.setAsync(true);
    RawatInapAction.getKategoriSkorRanap(kat, function(kategori){
        $("#label-view-asesmen").html("");
        $("#label-view-asesmen").html(kategori.namaKategori);

        RawatInapAction.getListViewSkorRanapByGrupId(noGroup, function(response){
            if (response != null) {
                var str = "";
                var person = "";
                $.each(response, function(i,item){
                    str += "<tr>"+
                        "<td>"+item.namaParameter+"</td>"+
                        "<td>"+item.keterangan+"</td>"+
                        "</tr>";

                    person = "<p>Diinput oleh : "+item.createdWho+"</p>"+
                        "<p>Diinput pada : "+formateDate(item.stDate)+"</p>";
                });

                $("#head-view-asesmen").html(person);
                $("#body-view-asesmen").html(str);
            }
        });
    });
}

function viewSkor(noGroup){
    $("#modal-view-skor").modal("show");
    var kat = $("#kat_skor").val();

    dwr.engine.setAsync(true);
    RawatInapAction.getKategoriSkorRanap(kat, function(kategori){
        $("#label-view-skor").html("");
        $("#label-view-skor").html(kategori.namaKategori);

        RawatInapAction.getListViewSkorRanapByGrupId(noGroup, function(response){
            if (response != null) {
                var str = "";
                var person = "";
                $.each(response, function(i,item){
                    str += "<tr>"+
                        "<td>"+item.namaParameter+"</td>"+
                        "<td>"+item.skor+"</td>"+
                        "<td>"+item.keterangan+"</td>"+
                        "</tr>";

                    person = "<p>Diinput oleh : "+item.createdWho+"</p>"+
                        "<p>Diinput pada : "+formateDate(item.stDate)+"</p>";
                });

                $("#head-view-skor").html(person);
                $("#body-view-skor").html(str);
            }
        });
    });
}

function showModalMonVitalSign(idDetail){
    $("#modal-vital-sign").modal({show:true, backdrop:'static'});
    var tempData = [];
    CatatanTerintegrasiAction.getListCatatanTerintegrasi(idDetail, 'catatan_terintegrasi_ina', function (res) {
        if (res.length > 0) {
            var dataArray = [];
            $.each(res, function (i, item) {
                var tgl = ""
                var jam = "";
                if(item.waktu != null && item.waktu != ''){
                    var waktu = converterDateTime(item.waktu);
                    if(waktu.split(" ")[0] != undefined){
                        tgl = waktu.split(" ")[0];
                    }
                    if(waktu.split(" ")[1] != undefined){
                        jam = waktu.split(" ")[1];
                    }
                }
                var sistole = 0;
                var diastole = 0;
                if(item.tensi != null && item.tensi != ''){
                    if(item.tensi.split("/")[0] != undefined){
                        sistole = item.tensi.split("/")[0];
                    }
                    if(item.tensi.split("/")[1] != undefined){
                        diastole = item.tensi.split("/")[1];
                    }
                }
                dataArray.push({
                    y: jam,
                    a: item.rr,
                    b: item.nadi,
                    c: sistole,
                    d: diastole
                });
            });
            $('#modal-vital-sign').on('shown.bs.modal', function (event) {
                $('#line-chart_vital_sign').empty();
                var line = new Morris.Line({
                    element: 'line-chart_vital_sign',
                    resize: true,
                    data: dataArray,
                    xkey: 'y',
                    ykeys: ['a', 'b', 'c', 'd'],
                    labels: ['RR', 'Nadi', 'Sistole', 'Diastole'],
                    lineColors: ['#ff0000', '#0000ff', '#00cc00', '#cc6699'],
                    hideHover: 'auto',
                    parseTime: false,
                    lineWidth: 1
                });
            });
        }
    });
}

function addMonVitalSign(){
    $("#modal-add-vital-sign").modal("show");
}
function showGrafVitalSign(idDetail){
    $("#modal-graf-vital-sign").modal("show");

    var suhu = [], nadi = [], nafas = [], label = [];
    dwr.engine.setAsync(true);
    RawatInapAction.getListGraf(idDetailCheckup, "", function(response){
        console.log(response)
        $.each(response, function(i, item){
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

        $.plot('#line-chart', [line_data1, line_data2, line_data3], {
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
        $('#line-chart').bind('plothover', function (event, pos, item) {

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
}

function saveVitalSign(noCheckup, idDetail){
    // alert("klik");
    var jsonrq = [];
    jsonrq.push({
        'jam': $("#mvs_jam").val(),
        'nafas': $("#mvs_nafas").val(),
        'nadi': $("#mvs_nadi").val(),
        'suhu': $("#mvs_suhu").val(),
        'tensi': $("#mvs_tensi").val(),
        'tb': $("#mvs_tb").val(),
        'bb': $("#mvs_bb").val()
    });

    var jsonstr = JSON.stringify(jsonrq);
    console.log(jsonstr);
    dwr.engine.setAsync(true);
    RawatInapAction.saveMonVitalSign(noCheckup, idDetail, jsonstr, function(response){
        if (response.status == "success") {
            alert("success");
            $("#modal-add-vital-sign").modal("hide");

            RawatInapAction.getListMonVitalSign("", idDetail, "", function(response){
                console.log(response);
                var str = "";
                $.each(response, function(i, item) {
                    str += "<tr>"+
                        "<td>"+item.jam+"</td>"+
                        "<td>"+item.nafas+"</td>"+
                        "<td>"+item.nadi+"</td>"+
                        "<td>"+item.suhu+"</td>"+
                        "<td>"+item.tensi+"</td>"+
                        "<td>"+item.bb+"</td>"+
                        "<td>"+item.tb+"</td>"+
                        "<td>"+item.createdWho+"</td>"+
                        "<td>"+formateDate(item.stDate)+"</td>"+
                        "</tr>";
                });

                $("#body-list-vital-sign").html("");
                $("#body-list-vital-sign").html(str);
            });
        } else {
            alert(response.msg);
        }
    });
}

function showModalCairan(idDetail){

    $("#modal-cairan").modal("show");

    dwr.engine.setAsync(true);
    RawatInapAction.getListMonCairan("", idDetail, "", function(response){
        // console.log(response);
        var str = "";
        $.each(response, function(i, item) {
            str += "<tr>"+
                "<td>"+formateDate(item.stDate)+"</td>"+
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
                "<td>"+item.createdWho+"</td>"+
                "</tr>";
        });

        $("#body-list-cairan").html(str);
    });
}

function addObCairan(){
    $("#modal-add-cairan").modal("show");
}

function saveObCairan(noCheckup, idDetail){
    var jsonrq = [];
    jsonrq.push({
        'macam': $("#mcr_macam").val(),
        'melalui': $("#mcr_melalui").val(),
        'jumlah': $("#mcr_jumlah").val(),
        'mulai': $("#mcr_mulai").val(),
        'selesai': $("#mcr_selesai").val(),
        'cek': $("#mcr_cek").val(),
        'sisa': $("#mcr_sisa").val(),
        'jam_ukur_buang': $("#mcr_buang").val(),
        'dari': $("#mcr_dari").val(),
        'balance': $("#mcr_balance").val(),
        'ket': $("#mcr_ket").val(),
    });

    var jsonstr = JSON.stringify(jsonrq);
    dwr.engine.setAsync(true);
    RawatInapAction.saveMonCairan(noCheckup, idDetail, jsonstr, function(response){
        if (response.status == "success") {
            alert("success");
            $("#modal-add-cairan").modal("hide");

            RawatInapAction.getListMonCairan("", idDetail, "", function(response){
                // console.log(response);
                var str = "";
                $.each(response, function(i, item) {
                    str += "<tr>"+
                        "<td>"+formateDate(item.stDate)+"</td>"+
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
                        "<td>"+item.createdWho+"</td>"+
                        "</tr>";
                });

                $("#body-list-cairan").html("");
                $("#body-list-cairan").html(str);
            });
        } else {
            alert(response.msg);
        }
    });
}

function showModalPemberianObat(idDetail, kategori){

    // alert(kategori);

    $("#modal-pemberian").modal("show");
    $("#kat_pemberian").val(kategori);
    $("#label_kat_pemberian").html(kategori);
    dwr.engine.setAsync(true);
    RawatInapAction.getListMonPemberianObat("", idDetail, kategori, "",  function(response){
        // console.log(response);
        var strhead = "";
        var str = "";
        if (kategori == "parenteral") {

            $.each(response, function(i, item){
                str += "<tr>"+
                    "<td>"+item.namaObat+"</td>"+
                    "<td>"+item.caraPemberian+"</td>"+
                    "<td>"+item.dosis+"</td>"+
                    "<td>"+item.skinTes+"</td>"+
                    "<td>"+item.waktu+"</td>"+
                    "<td>"+item.keterangan+"</td>"+
                    "<td>"+item.createdWho+"</td>"+
                    "<td>"+formateDate(item.stDate)+"</td>"+
                    "</tr>";
            });

            strhead = "<tr>"+
                "<td>Nama Obat</td>"+
                "<td>Cara Pemberian</td>"+
                "<td>Dosis</td>"+
                "<td>Skin Tes</td>"+
                "<td>Waktu</td>"+
                "<td>Keterangan</td>"+
                "<td>Created Who</td>"+
                "<td>Created Date</td>"+
                "</tr>";
        } else {

            $.each(response, function(i, item) {
                str += "<tr>"+
                    "<td>"+item.namaObat+"</td>"+
                    "<td>"+item.dosis+"</td>"+
                    "<td>"+item.waktu+"</td>"+
                    "<td>"+item.keterangan+"</td>"+
                    "<td>"+item.createdWho+"</td>"+
                    "<td>"+formateDate(item.stDate)+"</td>"+
                    "</tr>";
            });

            strhead = "<tr>"+
                "<td>Nama Obat</td>"+
                "<td>Dosis</td>"+
                "<td>Waktu</td>"+
                "<td>Keterangan</td>"+
                "<td>Created Who</td>"+
                "<td>Created Date</td>"+
                "</tr>";;
        }

        $("#thead_pemberian").html(strhead);
        $("#body-list-pemberian").html("");
        $("#body-list-pemberian").html(str);
    });
}


function addPemberianObat(){
    var kat = $("#kat_pemberian").val();

    var str="";
    dwr.engine.setAsync(true);
    if (kat == "parenteral") {
        RawatInapAction.getListObatParenteral(idPoli, function(response){
            console.log(response);
            $.each(response, function(i, item) {
                str += "<option val=\'"+item.namaObat+"\'>"+item.namaObat+"</option>";
            });
            $("#select_obat_par").html(str);
        });
        $("#modal-add-pemberian-parenteral").modal("show");
    } else {
        RawatInapAction.getListObatNonParenteral(idDetailCheckup, "%",  function(response){
            console.log(response);
            $.each(response, function(i, item) {
                str += "<option val=\'"+item.namaObat+"\'>"+item.namaObat+"</option>";
            });
            $("#select_obat_nonpar").html(str);
        });
        $("#modal-add-pemberian-non-parenteral").modal("show");
    }
}

function savePemberianObat(noCheckup, idDetail){
    var kat = $("#kat_pemberian").val();

    var jsonrq = [];
    var ispar = false;
    if (kat == "parenteral") {
        ispar = true;
        jsonrq.push({
            'name': $("#select_obat_par").val(),
            'cara': $("#par_cara").val(),
            'dosis': $("#par_dosis").val(),
            'tes': $("#par_skintes").val(),
            'waktu': $("#select_waktu_par").val(),
            'ket': $("#par_keterangan").val(),
            'kat': kat
        });
    } else {
        jsonrq.push({
            'name': $("#select_obat_nonpar").val(),
            'cara': "",
            'dosis': $("#nonpar_dosis").val(),
            'tes': "",
            'waktu': $("#select_waktu_nonpar").val(),
            'ket': $("#nonpar_keterangan").val(),
            'kat': kat
        });
    }
    var jsonstr = JSON.stringify(jsonrq);
    dwr.engine.setAsync(true);
    RawatInapAction.saveMonPemberianObat(noCheckup, idDetail, jsonstr, function(response){
        if (response.status == "success") {
            alert(response.status);
            dwr.engine.setAsync(true);
            RawatInapAction.getListMonPemberianObat("", idDetail, kat, "",  function(response){
                var strhead = "";
                var str = "";
                if (ispar) {
                    $("#modal-add-pemberian-parenteral").modal("hide");
                    $.each(response, function(i, item){
                        str += "<tr>"+
                            "<td>"+item.namaObat+"</td>"+
                            "<td>"+item.caraPemberian+"</td>"+
                            "<td>"+item.dosis+"</td>"+
                            "<td>"+item.skinTes+"</td>"+
                            "<td>"+item.waktu+"</td>"+
                            "<td>"+item.keterangan+"</td>"+
                            "<td>"+item.createdWho+"</td>"+
                            "<td>"+formateDate(item.stDate)+"</td>"+
                            "</tr>";
                    });
                } else {
                    $("#modal-add-pemberian-non-parenteral").modal("hide");
                    $.each(response, function(i, item) {
                        str += "<tr>"+
                            "<td>"+item.namaObat+"</td>"+
                            "<td>"+item.dosis+"</td>"+
                            "<td>"+item.waktu+"</td>"+
                            "<td>"+item.keterangan+"</td>"+
                            "<td>"+item.createdWho+"</td>"+
                            "<td>"+formateDate(item.stDate)+"</td>"+
                            "</tr>";
                    });
                }
                $("#body-list-pemberian").html("");
                $("#body-list-pemberian").html(str);
            });
        } else {
            alert(response.msg);
        }
    });
};


function showFormEdukasi(){
    $("#modal-edukasi").modal("show");

    dwr.engine.setAsync(true);
    RawatInapAction.getListGroupSkorRanap(noCheckup, idDetailCheckup, "inap14", function(response){
        if(response.length > 0){
            var strhead = "";
            $.each(response, function(i, itemHeader){
                strhead += "<tr>"+
                    "<td>"+itemHeader.namaKategori+"</td>"+
                    "<td>"+itemHeader.createdWho+"</td>"+
                    "<td>"+itemHeader.stDate+"</td>"+
                    "<td><button class='btn btn-primary' onclick=\"viewAsesmen('"+itemHeader.groupId+"')\">View</button></td>"+
                    "</tr>";
            });
        }

        RawatInapAction.getListGroupSkorRanap(noCheckup, idDetailCheckup, "inap15", function(bodyresponse){
            if(response.length > 0){
                var strbody = "";
                $.each(bodyresponse, function(i, itemBody){
                    strbody += "<tr>"+
                        "<td>"+itemBody.namaKategori+"</td>"+
                        "<td>"+itemBody.createdWho+"</td>"+
                        "<td>"+itemBody.stDate+"</td>"+
                        "<td><button class='btn btn-primary' onclick=\"viewAsesmen('"+itemBody.groupId+"')\">View</button></td>"+
                        "</tr>";
                });
            }

            $("#list-body-header-edukasi").html(strhead);
            $("#list-body-edukasi").html(strbody);
        });
    });
};

function addFormEdukasi(kategori){

    $("#btn-save-edukasi").html("");
    $("#btn-save-edukasi").html("<button class='btn btn-success' onclick=\"saveFormEdukasi('"+kategori+"')\"></button>");
    dwr.engine.setAsync(true);
    RawatInapAction.getListParameterByKategori(noCheckup, idDetailCheckup, kategori, function(response){

        var str = "";
        if (response != null){
            var n = 0;
            $.each(response, function (i, item) {
                n = i;
                var upline = "";
                if (item.namaParameter.length > 25) {
                    upline ="<div class='form-group'>" +
                        "<div class='row'>"+
                        "<div class='col-md-8'>"+
                        "<label>"+item.namaParameter+"</label>"+
                        "</div>"+
                        "<div class='col-md-4'>";
                } else {
                    upline ="<div class='form-group'>" +
                        "<div class='row'>"+
                        "<div class='col-md-4'>"+
                        "<label>"+item.namaParameter+"</label>"+
                        "</div>"+
                        "<div class='col-md-8'>";
                }

                var opt = "";
                RawatInapAction.getListSkorRanapByParam(item.idParameter, function(skors){

                    var up_select = "<select class='form-control' id='val_rsk_"+i+"' onchange=\"showOtherInput(this.id)\">";
                    // var other_text = "<input type='text' class='form-control' id='ot_val_rsk_"+i+"' style='display:none'/>";
                    if (skors.length > 0) {
                        $.each(skors, function(i, itemSkor){
                            opt += "<option value="+itemSkor.ketSkor+">"+itemSkor.namaSkor+"</option>";
                        });
                    } else {
                        if (item.type == "date") {
                            opt = "<input type='date' class='form-control' id='val_rsk_"+i+"'>";
                        } else if (item.type == "number"){
                            opt = "<input type='number' class='form-control' id='val_rsk_"+i+"'>";
                        } else {
                            opt = "<input type='text' class='form-control' id='val_rsk_"+i+"'>";
                        }
                    }


                    // console.log(skors);

                    var down_select = "</select>";
                    var downline = "<input type='hidden' id='id_rsk_"+i+"' value='"+item.idParameter+"'>"+
                        "<input type='hidden' id='name_rsk_"+i+"' value='"+item.namaParameter+"'>"+
                        "<input type='text' class='form-control' id='ot_val_rsk_"+i+"' style='display:none' placeholder='sebutkan ...'/>"+
                        "</div>" +
                        "</div>"+
                        "<hr style='color:#b0b0b0;'/>"+
                        "</div>";
                    // "<div class='box-header with-border' style='margin-bottom: 7px;'></div>";

                    if (skors.length > 0) {
                        str += upline+up_select+opt+down_select+downline;
                    } else {
                        str += upline+opt+downline;
                    }

                    $("#ind_edukasi").val(n);
                    $("#body_edukasi").html(str);
                });
            });
        };
    });
}

function saveFormEdukasi(kategori){

    var jsonrq = [];
    var ind = $("#ind_edukasi").val();

    for (i = 0; i <= ind; i++){

        var id_rsk = $("#id_rsk_"+i+"").val();

        var nilai = "";
        if ($("#val_rsk_"+i+"").is("select")) {
            nilai = $("#val_rsk_"+i+" option:selected").text();
        } else {
            nilai = $("#val_rsk_"+i+"").val();
        }

        var name_rsk = $("#name_rsk_"+i+"").val();
        var ket_rsk = "";
        var val_rsk = "0";

        if (nilai.toLowerCase() == "lain") {
            ket_rsk = $("#ot_val_rsk_"+i+"").val();
        } else {
            ket_rsk = nilai;
        }

        jsonrq.push({'id':id_rsk, 'val':val_rsk, 'name':name_rsk, 'ket':ket_rsk});
    }
    var jsonstr = JSON.stringify(jsonrq);
    dwr.engine.setAsync(true);
    RawatInapAction.saveSkorRanapByKategori(noCheckup, idDetailCheckup, kategori, jsonstr, function(response){
        if (response.status == "success") {
            alert("sukses");

            RawatInapAction.getListGroupSkorRanap(noCheckup, idDetailCheckup, kategori, function(response){
                if (response != null) {
                    var str = "";
                    $.each(response, function(i, item){
                        str += "<tr>"+
                            "<td>"+item.namaKategori+"</td>"+
                            "<td>"+item.createdWho+"</td>"+
                            "<td>"+formateDate(item.stDate)+"</td>"+
                            "<td><button class='btn btn-primary' onclick=\"viewAsesmen('"+item.groupId+"')\">View</button></td>"+
                            "</tr>";
                    });

                    if(kategori == "inap14"){
                        $("#list-body-header-edukasi").html("");
                        $("#list-body-header-edukasi").html(str);
                    } else {
                        $("#list-body-edukasi").html("");
                        $("#list-body-edukasi").html(str);
                    }

                    $("#modal-add-edukasi").modal("hide");
                }
                // console.log(response);
            });

        } else {
            alert(response.msg);
        }
    });
}