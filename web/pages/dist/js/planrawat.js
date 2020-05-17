// JS FOR PLAN

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
}

function showModalPlan(idDetail, tgl, jenis){
    $("#modal-view-plan-detail").modal('show');
    $("#id-detail").val(idDetail);
    $("#jenis-plan").val(jenis);
    if (tgl == ''){
        viewPlanDetail(idDetail, formatDate(Date.now()), jenis);
    } else {
        viewPlanDetail(idDetail, tgl, jenis);
    }
}

function viewPlanDetail(idDetail, tglMasuk, jenis){

    $("#tgl-plan").val(tglMasuk);

    var listPagi = [];
    var listSiang = [];
    var listMalam = [];

    PlanKegiatanRawatAction.getListPlanKegiatanRawatByTglMulai(idDetail, tglMasuk, function (response) {


        if (response.length > 0){

            $.each(response, function(i,item){

                var ket = "";
                if (item.keterangan != null){
                    ket = item.keterangan;
                }

                if (item.waktu.toLowerCase() == "pagi"){
                    listPagi.push({
                        "ket": ket,
                        "createdwho":item.createdWho,
                        "kegiatan":setLabelKegiatan(item.jenisKegiatan),
                        "id":item.idKategori, "jenis":item.jenisKegiatan,
                        "flag":item.flagDikerjakan,
                        "createddate":item.stCreatedDate,
                        "lastupdatewho":item.lastUpdateWho,
                        "lastupdate":item.stLastUpdate
                    });
                }
                if (item.waktu.toLowerCase() == "siang"){
                    listSiang.push({
                        "ket": ket,
                        "createdwho":item.createdWho,
                        "kegiatan":setLabelKegiatan(item.jenisKegiatan),
                        "id":item.idKategori, "jenis":item.jenisKegiatan,
                        "flag":item.flagDikerjakan,
                        "createddate":item.stCreatedDate,
                        "lastupdatewho":item.lastUpdateWho,
                        "lastupdate":item.stLastUpdate
                    });
                }
                if (item.waktu.toLowerCase() == "malam"){
                    listSiang.push({
                        "ket": ket,
                        "createdwho":item.createdWho,
                        "kegiatan":setLabelKegiatan(item.jenisKegiatan),
                        "id":item.idKategori, "jenis":item.jenisKegiatan,
                        "flag":item.flagDikerjakan,
                        "createddate":item.stCreatedDate,
                        "lastupdatewho":item.lastUpdateWho,
                        "lastupdate":item.stLastUpdate
                    });
                }
            });

            var strPagi = "";
            $.each(listPagi, function (i, item){
                strPagi += "<tr>" +
                    "<td style='width:200px'>" + item.kegiatan + "</td>" +
                    "<td align='left'><strong>" + item.createdwho + "</strong></td>" +
                    "<td align='left'><strong>" + converterDateTime(item.createddate) + "</strong></td>" +
                    "<td style='width:15%'>" + item.ket + "</td>" +
                    "<td align='center'>" + setIconDikerjakan(item.flag) + "</td>" +
                    "<td align='left'><strong>" + setWaktuDikerjakan(item.flag, item.lastupdatewho) + "</strong></td>" +
                    "<td align='left'><strong>" + setWaktuDikerjakan(item.flag, converterDateTime(item.lastupdate)) + "</strong></td>";

                if (jenis == 'suster'){
                    if (item.flag == "Y"){
                        strPagi += "<td align='right'>" +
                            "<button class='btn btn-sm btn-primary' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','view');\"><i class='fa fa-search'></i></button>" +
                            "</td>" +
                            "</tr>";
                    } else {
                        strPagi += "<td align='right'>" +
                            "<button class='btn btn-sm btn-primary' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','view');\"><i class='fa fa-search'></i></button>" +
                            "<button class='btn btn-sm btn-info' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','edit');\"><i class='fa fa-edit'></i></button>" +
                            "</td>" +
                            "</tr>";
                    }
                } else {
                    strPagi += "<td align='right'>" +
                        "<button class='btn btn-sm btn-primary' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','view');\"><i class='fa fa-search'></i></button>" +
                        "<button class='btn btn-sm btn-info' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','edit');\"><i class='fa fa-edit'></i></button>" +
                        "</td>" +
                        "</tr>";
                }
            });

            var strSiang = "";
            $.each(listSiang, function (i, item){
                strSiang += "<tr>" +
                    "<td style='width:200px'>" + item.kegiatan + "</td>" +
                    "<td align='left'><strong>" + item.createdwho + "</strong></td>" +
                    "<td align='left'><strong>" + converterDateTime(item.createddate) + "</strong></td>" +
                    "<td style='width:15%'>" + item.ket + "</td>" +
                    "<td align='center'>" + setIconDikerjakan(item.flag) + "</td>" +
                    "<td align='left'><strong>" + setWaktuDikerjakan(item.flag, item.lastupdatewho) + "</strong></td>" +
                    "<td align='left'><strong>" + setWaktuDikerjakan(item.flag, converterDateTime(item.lastupdate)) + "</strong></td>";

                if (jenis == "suster"){
                    if (item.flag == "Y"){
                        strSiang += "<td align='right'>" +
                            "<button class='btn btn-sm btn-primary' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','view');\"><i class='fa fa-search'></i></button>" +
                            "</td>" +
                            "</tr>";
                    } else {
                        strSiang += "<td align='right'>" +
                            "<button class='btn btn-sm btn-primary' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','view');\"><i class='fa fa-search'></i></button>" +
                            "<button class='btn btn-sm btn-info' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','edit');\"><i class='fa fa-edit'></i></button>" +
                            "</td>" +
                            "</tr>";
                    }

                } else {
                    strSiang += "<td align='right'>" +
                        "<button class='btn btn-sm btn-primary' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','view');\"><i class='fa fa-search'></i></button>" +
                        "<button class='btn btn-sm btn-info' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','edit');\"><i class='fa fa-edit'></i></button>" +
                        "</td>" +
                        "</tr>";
                }
            });

            var strMalam = "";
            $.each(listMalam, function (i, item){
                strMalam += "<tr>" +
                    "<td style='width:200px'>" + item.kegiatan + "</td>" +
                    "<td align='left'><strong>" + item.createdwho + "</strong></td>" +
                    "<td align='left'><strong>" + converterDateTime(item.createddate) + "</strong></td>" +
                    "<td style='width:15%'>" + item.ket + "</td>" +
                    "<td align='center'>" + setIconDikerjakan(item.flag) + "</td>" +
                    "<td align='left'><strong>" + setWaktuDikerjakan(item.flag, item.lastupdatewho) + "</strong></td>" +
                    "<td align='left'><strong>" + setWaktuDikerjakan(item.flag, converterDateTime(item.lastupdate)) + "</strong></td>";

                if (jenis == 'suster'){
                    if (item.flag == "Y"){
                        strMalam += "<td align='right'>" +
                            "<button class='btn btn-sm btn-primary' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','view');\"><i class='fa fa-search'></i></button>" +
                            "</td>" +
                            "</tr>";
                    } else {
                        strMalam += "<td align='right'>" +
                            "<button class='btn btn-sm btn-primary' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','view');\"><i class='fa fa-search'></i></button>" +
                            "<button class='btn btn-sm btn-info' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','edit');\"><i class='fa fa-edit'></i></button>" +
                            "</td>" +
                            "</tr>";
                    }
                } else {
                    strMalam += "<td align='right'>" +
                        "<button class='btn btn-sm btn-primary' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','view');\"><i class='fa fa-search'></i></button>" +
                        "<button class='btn btn-sm btn-info' onclick=\"viewKegiatan('"+item.id+"','"+item.jenis+"','edit');\"><i class='fa fa-edit'></i></button>" +
                        "</td>" +
                        "</tr>";
                }
            });

            var header = "<thead style='color: white;background-color: grey'>" +
                "<tr>" +
                "<td>Kegiatan</td>" +
                "<td>Created Who</td>" +
                "<td>Created Date</td>" +
                "<td>Catatan Kegiatan</td>" +
                "<td>Status</td>" +
                "<td>Last Update Who</td>" +
                "<td>Last Update</td>" +
                "<td style='width: 100px;' align='center'>Action</td>" +
                "</tr>" +
                "</thead>";

            $("#body-list-plan-pagi").html(setLabelWaktu("pagi")+"<table class='table' style='font-size: 11px'>"+header+"<tbody>"+strPagi+"</tbody></table>");
            $("#body-list-plan-siang").html(setLabelWaktu("siang")+"<table class='table' style='font-size: 11px'>"+header+"<tbody>"+strSiang+"</tbody></table>");
            $("#body-list-plan-malam").html(setLabelWaktu("malam")+"<table class='table' style='font-size: 11px'>"+header+"<tbody>"+strMalam+"</tbody></table>");
        } else {
            $("#body-list-plan-pagi").html("");
            $("#body-list-plan-siang").html("");
            $("#body-list-plan-malam").html("");
        }
    });
}

function setIconDikerjakan(param) {
    if(param == "Y"){
        return "<i class='fa fa-check'></i>";
    } else {
        return " - ";
    }
}

function setWaktuDikerjakan(param, nilai) {
    if(param == "Y"){
        return nilai;
    } else {
        return " - ";
    }
}

function setLabelKegiatan(param){
    if (param == "vitalsign")
        return "<span class='label label-primary'>Monitoring Vital Sign</span>";
    else if (param == "cairan")
        return "<span class='label label-success'>Monitoring Cairan</span>";
    else if (param == "parenteral")
        return "<span class='label label-info'>Pemberian Obat Parenteral</span>";
    else if (param == "nonparenteral")
        return "<span class='label label-warning'>Pemberian Obat Non Parenteral</span>";
    else
        return "<span class='label label-default'>"+param+"</span>";
}

function setLabelWaktu(param){
    if (param == "pagi")
        return "<h4><img src='"+pathImages+"/pages/images/ic_pagi.png' style='width: 30px'> <span class='label label-info'>Pagi</span></h4>";
    else if (param == "siang")
        return "<h4><img src='"+pathImages+"/pages/images/ic_siang.png' style='width: 30px'> <span class='label label-info'>Siang</span></h4>";
    else if (param == "malam")
        return "<h4><img src='"+pathImages+"/pages/images/ic_malam.png' style='width: 30px'> <span class='label label-info'>Malam</span></h4>";
    else
        return "<h4><span class='label label-default'>"+param+"</span></h4>";
}

function viewKegiatan(idKategori, jenis, tipe){
    if (jenis == "vitalsign"){
        viewVitalSign(idKategori, tipe);
    }
    if (jenis == "cairan"){
        viewCairan(idKategori, tipe);
    }
    if (jenis == "parenteral"){
        viewPemberianObat(idKategori, tipe, jenis)
    }
    if (jenis == "nonparenteral"){
        viewPemberianObat(idKategori, tipe, jenis);
    }
}

function viewVitalSign(id, type){
    $("#success_save_vitalsign").hide();
    $("#error_save_vitalsign").hide();
    $("#load_vitalsign").hide();

    $("#modal-edit-vital-sign").modal('show');

    RawatInapAction.getDataMonVitalSign(id, function (item) {

        $("#edit_mvs_jam").val(item.jam);
        $("#edit_mvs_nafas").val(item.nafas);
        $("#edit_mvs_nadi").val(item.nadi);
        $("#edit_mvs_suhu").val(item.suhu);
        $("#edit_mvs_tensi").val(item.tensi);
        $("#edit_mvs_tb").val(item.tb);
        $("#edit_mvs_bb").val(item.bb);
        $("#edit_mvs_id").val(id);

        if (type == "view"){
            $("#edit_mvs_jam").attr('disabled','disabled');
            $("#edit_mvs_nafas").attr('disabled','disabled');
            $("#edit_mvs_nadi").attr('disabled','disabled');
            $("#edit_mvs_suhu").attr('disabled','disabled');
            $("#edit_mvs_tensi").attr('disabled','disabled');
            $("#edit_mvs_tb").attr('disabled','disabled');
            $("#edit_mvs_bb").attr('disabled','disabled');
            $("#save_vitalsign").hide();
        } else {
            $("#edit_mvs_jam").removeAttr('disabled');
            $("#edit_mvs_nafas").removeAttr('disabled');
            $("#edit_mvs_nadi").removeAttr('disabled');
            $("#edit_mvs_suhu").removeAttr('disabled');
            $("#edit_mvs_tensi").removeAttr('disabled');
            $("#edit_mvs_tb").removeAttr('disabled');
            $("#edit_mvs_bb").removeAttr('disabled');
            $("#save_vitalsign").show();
        }
    })
}

function viewCairan(id, type){
    $("#success_save_cairan").hide();
    $("#error_save_cairan").hide();
    $("#load_cairan").hide();

    $("#modal-edit-cairan").modal('show');

    RawatInapAction.getDataMonCairan(id, function (item) {

        $("#edit_mcr_macam").val(item.macamCairan);
        $("#edit_mcr_melalui").val(item.melalui);
        $("#edit_mcr_jumlah").val(item.jumlah);
        $("#edit_mcr_mulai").val(item.jamMulai);
        $("#edit_mcr_selesai").val(item.jamSelesai);
        $("#edit_mcr_cek").val(item.cekTambahanObat);
        $("#edit_mcr_sisa").val(item.sisa);
        $("#edit_mcr_buang").val(item.jamUkurBuang);
        $("#edit_mcr_dari").val(item.dari);
        $("#edit_mcr_balance").val(item.balanceCairan);
        $("#edit_mcr_ket").val(item.keterangan);
        $("#edit_mcr_id").val(id);

        if (type == "view"){

            $("#edit_mcr_macam").attr('disabled','disabled');
            $("#edit_mcr_melalui").attr('disabled','disabled');
            $("#edit_mcr_jumlah").attr('disabled','disabled');
            $("#edit_mcr_mulai").attr('disabled','disabled');
            $("#edit_mcr_selesai").attr('disabled','disabled');
            $("#edit_mcr_cek").attr('disabled','disabled');
            $("#edit_mcr_sisa").attr('disabled','disabled');
            $("#edit_mcr_buang").attr('disabled','disabled');
            $("#edit_mcr_dari").attr('disabled','disabled');
            $("#edit_mcr_balance").attr('disabled','disabled');
            $("#edit_mcr_ket").attr('disabled','disabled');
            $("#save_cairan").hide();

        } else {
            $("#edit_mcr_macam").removeAttr('disabled');
            $("#edit_mcr_melalui").removeAttr('disabled');
            $("#edit_mcr_jumlah").removeAttr('disabled');
            $("#edit_mcr_mulai").removeAttr('disabled');
            $("#edit_mcr_selesai").removeAttr('disabled');
            $("#edit_mcr_cek").removeAttr('disabled');
            $("#edit_mcr_sisa").removeAttr('disabled');
            $("#edit_mcr_buang").removeAttr('disabled');
            $("#edit_mcr_dari").removeAttr('disabled');
            $("#edit_mcr_balance").removeAttr('disabled');
            $("#edit_mcr_ket").removeAttr('disabled');
            $("#save_cairan").show();
        }
    })
}

function viewPemberianObat(id, type, kategori){
    $("#success_save_nonpar").hide();
    $("#error_save_nonpar").hide();
    $("#load_nonpar").hide();

    $("#modal-edit-pemberian-non-parenteral").modal('show');

    RawatInapAction.getMonPemberianObat(id, function (item) {

        if (kategori == "nonparenteral"){
            $("#label-pemberian").text("Observasi Obat Non Parenteral");
            $("#input_edit_par_cara").hide();
            $("#input_edit_par_skintes").hide();
            $("#save_nonpar").attr('onclick','saveUpdatePlan(\'nonparenteral\')');

            $("#select_obat_edit_nonpar").val(item.namaObat);
            $("#edit_nonpar_dosis").val(item.dosis);
            $("#select_waktu_edit_nonpar").val(item.waktu.toLowerCase());
            $("#edit_nonpar_keterangan").val(item.keterangan);
            $("#edit_nonpar_id").val(item.id);


        } else {
            $("#label-pemberian").text("Observasi Obat Parenteral");
            $("#edit_par_cara").show();
            $("#edit_par_skintes").show();
            $("#save_nonpar").attr('onclick','saveUpdatePlan(\'parenteral\')');

            $("#edit_par_cara").val(item.caraPemberian);
            $("#edit_par_skintes").val(item.skinTes);
            $("#select_obat_edit_nonpar").val(item.namaObat);
            $("#edit_nonpar_dosis").val(item.dosis);
            $("#select_waktu_edit_nonpar").val(item.waktu.toLowerCase());
            $("#edit_nonpar_keterangan").val(item.keterangan);
            $("#edit_nonpar_id").val(item.id);
        }

        if (type == "view"){

            $("#edit_nonpar_dosis").attr('disabled','disabled');
            $("#select_waktu_edit_nonpar").attr('disabled','disabled');
            $("#edit_nonpar_keterangan").attr('disabled','disabled');
            $("#edit_par_cara").attr('disabled','disabled');
            $("#edit_par_skintes").attr('disabled','disabled');
            $("#save_nonpar").hide();

        } else {
            $("#edit_nonpar_dosis").removeAttr('disabled');
            $("#select_waktu_edit_nonpar").removeAttr('disabled');
            $("#edit_nonpar_keterangan").removeAttr('disabled');
            $("#edit_par_cara").removeAttr('disabled');
            $("#edit_par_skintes").removeAttr('disabled');
            $("#save_nonpar").show();
        }
    })
}

function saveUpdatePlan(tipe){
    if (tipe == "vitalsign"){
        saveUpdateVitalSign();
    }
    if (tipe == "cairan"){
        saveUpdateCairan();
    }
    if (tipe == "parenteral"){
        saveUpdatePemberianObat();
    }
    if (tipe == "nonparenteral"){
        saveUpdatePemberianObat();
    }
}

function saveUpdateVitalSign() {

    var idDetail = $("#id-detail").val();
    var tglPlan = $("#tgl-plan").val();
    var jenisPlan = $("#jenis-plan").val();

    var id = $("#edit_mvs_id").val();
    var jam = $("#edit_mvs_jam").val();
    var nafas = $("#edit_mvs_nafas").val();
    var nadi = $("#edit_mvs_nadi").val();
    var suhu = $("#edit_mvs_suhu").val();
    var tensi = $("#edit_mvs_tensi").val();
    var tb = $("#edit_mvs_tb").val();
    var bb = $("#edit_mvs_bb").val();

    var arrData = [];
    arrData.push({
        "jam":jam,
        "nadi":nadi,
        "nafas":nafas,
        "suhu":suhu,
        "tensi":tensi,
        "tb":tb,
        "bb":bb
    });

    var stJson = JSON.stringify(arrData);
    $("#load_vitalsign").show();
    $("#save_vitalsign").hide();

    dwr.engine.setAsync(true);
    RawatInapAction.saveUpdateMonVitalSign(id, stJson, function(response){
        if (response.status == "success"){
            $("#success_save_vitalsign").show().fadeOut(5000);
            $("#load_vitalsign").hide();

            viewPlanDetail(idDetail, tglPlan, jenisPlan);
        } else {
            $("#success_save_vitalsign").hide();
            $("#error_save_vitalsign").show().fadeOut(5000);
            $("#error_ket_vitalsign").text(response.msg);
        }
    });
}

function saveUpdateCairan() {

    var idDetail = $("#id-detail").val();
    var tglPlan = $("#tgl-plan").val();
    var jenisPlan = $("#jenis-plan").val();

    var id = $("#edit_mcr_id").val();
    var macam = $("#edit_mcr_macam").val();
    var melalui = $("#edit_mcr_melalui").val();
    var jumlah = $("#edit_mcr_jumlah").val();
    var mulai = $("#edit_mcr_mulai").val();
    var selesai = $("#edit_mcr_selesai").val();
    var cek = $("#edit_mcr_cek").val();
    var sisa = $("#edit_mcr_sisa").val();
    var buang = $("#edit_mcr_buang").val();
    var dari = $("#edit_mcr_dari").val();
    var balance = $("#edit_mcr_balance").val();
    var ket = $("#edit_mcr_ket").val();

    var arrData = [];
    arrData.push({
        "macam":macam,
        "melalui":melalui,
        "jumlah":jumlah,
        "mulai":mulai,
        "selesai":selesai,
        "cek":cek,
        "jam_ukur_buang":buang,
        "dari":dari,
        "balance":balance,
        "ket":ket,
        "sisa":sisa
    });

    var stJson = JSON.stringify(arrData);
    $("#load_cairan").show();
    $("#save_cairan").hide();

    dwr.engine.setAsync(true);
    RawatInapAction.saveUpdateMonCairan(id, stJson, function(response){
        if (response.status == "success"){
            $("#success_save_cairan").show().fadeOut(5000);
            $("#load_cairan").hide();

            viewPlanDetail(idDetail, tglPlan, jenisPlan);
        } else {
            $("#success_save_cairan").hide();
            $("#error_save_cairan").show().fadeOut(5000);
            $("#error_ket_cairan").text(response.msg);
        }
    });
}

function saveUpdatePemberianObat() {

    var idDetail = $("#id-detail").val();
    var tglPlan = $("#tgl-plan").val();
    var jenisPlan = $("#jenis-plan").val();

    var id = $("#edit_nonpar_id").val();
    var obat = $("#select_obat_edit_nonpar").val();
    var cara = $("#edit_par_cara").val();
    var skintes = $("#edit_par_skintes").val();
    var dosis = $("#edit_nonpar_dosis").val();
    var waktu = $("#select_waktu_edit_nonpar").val();
    var ket = $("#edit_nonpar_keterangan").val();

    var arrData = [];
    arrData.push({
        "name":obat,
        "cara":cara,
        "dosis":dosis,
        "tes":skintes,
        "waktu":waktu,
        "ket":ket
    });

    var stJson = JSON.stringify(arrData);
    $("#load_nonpar").show();
    $("#save_nonpar").hide();

    dwr.engine.setAsync(true);
    RawatInapAction.saveUpdateMonPemberianObat(id, stJson, function(response){
        if (response.status == "success"){
            $("#success_save_nonpar").show().fadeOut(5000);
            $("#load_nonpar").hide();

            viewPlanDetail(idDetail, tglPlan, jenisPlan);
        } else {
            $("#success_save_nonpar").hide();
            $("#error_save_nonpar").show().fadeOut(5000);
            $("#error_ket_nonpar").text(response.msg);
        }
    });
}