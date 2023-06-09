<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <style>
        .box_shadow {
            box-shadow: 4px 8px 12px grey;
        }

        .form-check {
            display: inline-block;
            padding-left: 2px;
        }

        .form-check input {
            padding: 0;
            height: initial;
            width: initial;
            margin-bottom: 0;
            display: none;
            cursor: pointer;
        }

        .form-check label {
            position: relative;
            cursor: pointer;
        }

        .form-check label:before {
            content: '';
            -webkit-appearance: none;
            background-color: transparent;
            border: 2px solid #0079bf;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 7px;
            display: inline-block;
            position: relative;
            vertical-align: middle;
            cursor: pointer;
            margin-right: 5px;
        }

        .form-check input:checked + label:after {
            content: '';
            display: block;
            position: absolute;
            top: 0px;
            left: 7px;
            width: 6px;
            height: 14px;
            border: solid #0079bf;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/InitDashboardAction.js"/>'></script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<div class="content-wrapper">
    <section class="content-header">
        <h1>
            Dashboard
        </h1>
    </section>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-3">
                                Bulan
                                <select class="form-control select2" id="set_bulan" onchange="setAllCount()">
                                    <option value="">[Select One]</option>
                                    <option value="0">Januari</option>
                                    <option value="1">Februari</option>
                                    <option value="2">Maret</option>
                                    <option value="3">April</option>
                                    <option value="4">Mei</option>
                                    <option value="5">Juni</option>
                                    <option value="6">Juli</option>
                                    <option value="7">Agustus</option>
                                    <option value="8">September</option>
                                    <option value="9">Oktober</option>
                                    <option value="10">November</option>
                                    <option value="11">Desember</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                Tahun
                                <select class="form-control select2" id="set_tahun" onchange="setAllCount()">
                                    <option value="">[Select One]</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        Jumlah Pasien
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span style="cursor: pointer" class="info-box-icon bg-yellow cek_warna" id="count_rj" onclick="setJenis(this.id)"><img
                                            style="width: 50px; height: 50px; margin-top: 18px; cursor: pointer"
                                            src="<s:url value="/pages/images/logo-dokter.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Rawat Jalan</span>
                                        <span class="info-box-number"><span id="sum_rj"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span style="cursor: pointer" class="info-box-icon bg-green cek_warna" id="count_igd" onclick="setJenis(this.id)"><img
                                            style="width: 30px; height: 50px; margin-top: 18px; cursor: pointer"
                                            src="<s:url value="/pages/images/logo-perawat.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">IGD</span>
                                        <span class="info-box-number"><span id="sum_igd"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix visible-sm-block"></div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span style="cursor: pointer" class="info-box-icon bg-green cek_warna" id="count_ri" onclick="setJenis(this.id)"><img
                                            style="width: 50px; height: 50px; margin-top: 18px; cursor: pointer"
                                            src="<s:url value="/pages/images/logo-poli.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Rawat Inap</span>
                                        <span class="info-box-number"><span id="sum_ri"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span style="cursor: pointer" class="info-box-icon bg-green cek_warna" id="count_telemedic" onclick="setJenis(this.id)"><img
                                            style="width: 50px; height: 50px; margin-top: 18px; cursor: pointer"
                                            src="<s:url value="/pages/images/logo-pasien.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Telemedic</span>
                                        <span class="info-box-number"><span id="sum_telemedic"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        Jumlah Kunjungan Pasien <b><span id="kj_label">Rawat Jalan</span></b>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-body chart-responsive">
                                    <div class="chart" id="line-chart" style="height: 300px; width: 100%"></div>
                                </div>
                                <div class="row" id="nama_rs">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        Detail Kunjungan Pasien <b><span id="jp_label">Rawat Jalan</span></b> Berdasarkan Jenis Pasien
                    </div>
                    <div class="box-body">
                        <div class="row" id="donut_rs">
                        </div>
                        <div class="row">
                            <div class="col-md-offset-10 col-md-2">
                                <b style="font-size: 12px; margin-left: 7px">Keterangan</b>
                                <ul class="fa-ul" style="font-size: 12px" id="legend_jp">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        Detail Kunjungan Pasien <b><span id="jk_label">Rawat Jalan</span></b> Berdasarkan Jenis Kelamin
                    </div>
                    <div class="box-body">
                        <div class="row" id="donut_jk">
                        </div>
                        <div class="row">
                            <div class="col-md-offset-10 col-md-2">
                                <b style="font-size: 12px; margin-left: 7px">Keterangan</b>
                                <ul class="fa-ul" style="font-size: 12px" id="legend_jk">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        Jumlah Ketersedian Kamar
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-body chart-responsive">
                                    <div class="chart" id="line-chart-kamar" style="height: 300px; width: 100%"></div>
                                </div>
                                <div class="row" id="nama_rs_kamar">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<div class="modal" id="modal-loading">
    <div class="vertical-alignment-helper">
        <div class="modal-dialog vertical-align-center">
            <div class="modal-body">
                <div style="text-align: center; color: white;">
                    <img border="0" style="width: 130px; height: 110px;"
                         src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                         name="image_indicator_write">
                    <br>
                    <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -67px; margin-left: 45px"
                         src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                         name="image_indicator_write">
                    <p style="margin-top: -3px">Sedang mencari data...</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    $(document).ready(function () {
        $('#dashboard').addClass("active");
        setBranch();
        setTahun();
    });

    function setTahun() {
        var optiton = "";
        dwr.engine.setAsync(true);
        InitDashboardAction.getTahunPeriksa({
            callback: function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        optiton += '<option value="' + item.tahun + '">' + item.tahun + '</option>';
                    });
                }
                $('#set_tahun').html(optiton);
                var now = new Date();
                var year = now.getFullYear();
                var bulan = now.getMonth();
                $('#set_bulan').val(bulan).trigger('change');
                $('#set_tahun').val(year).trigger('change');
            }
        });
    }

    function setBranch() {
        var namaRS = "";
        var legendRS = "";
        var donutRS = "";
        var donutJK = "";
        var tempColor = [];
        dwr.engine.setAsync(true);
        InitDashboardAction.getComboBranch({
            callback: function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        var color = "#99ccff";
                        if(item.warna != null && item.warna != ''){
                            color = item.warna;
                        }

                        namaRS += '<div class="col-md-2 text-center">\n' +
                            '<div class="form-check">\n' +
                            '<input onclick="cekBranch(this.id)" checked="true" type="checkbox" name="cek_nama_rs" id="id_nama_rs_' + i + '" value="' + item.branchId + '|' + color + '">\n' +
                            '<label for="id_nama_rs_' + i + '"></label> <b style="font-size: 12px; margin-left: -6px">' + item.branchName + ' <i class="fa fa-circle" style="color: '+color+'"></i></b>\n' +
                            '</div>\n' +
                            '</div>';

                        donutRS += '<div class="col-md-2" style="margin-top: -80px">\n' +
                            '<div class="box-body chart-responsive">\n' +
                            '<div class="chart" id="donut-chart-' + item.branchId + '" style="height: 300px; position: relative;"></div>\n' +
                            '<p class="text-center" style="margin-top: -85px; font-size: 12px;"><b>' + item.branchName + '</b></p>\n' +
                            '</div>\n' +
                            '</div>';

                        donutJK += '<div class="col-md-2" style="margin-top: -80px">\n' +
                            '<div class="box-body chart-responsive">\n' +
                            '<div class="chart" id="donut-chart-jk-' + item.branchId + '" style="height: 300px; position: relative;"></div>\n' +
                            '<p class="text-center" style="margin-top: -85px; font-size: 12px;"><b>' + item.branchName + '</b></p>\n' +
                            '</div>\n' +
                            '</div>';

                        legendRS += '<div class="col-md-2 text-center">\n' +
                            '<label style="font-size: 12px;"><i class="fa fa-circle" style="color: '+color+'; margin-right: 6px"></i>' + item.branchName + '</label>' +
                            '</div>';
                    });
                    $('#nama_rs').html(namaRS);
                    $('#nama_rs_kamar').html(legendRS);
                    $('#donut_rs').html(donutRS);
                    $('#donut_jk').html(donutJK);
                }
            }
        });
    }

    function setAllCount() {
        var bulan = $('#set_bulan').val();
        var tahun = $('#set_tahun').val();
        var month = parseInt(bulan) + 1;
        if (bulan && tahun != '') {
            $('#modal-loading').modal({show:true, backdrop:'static'});
            dwr.engine.setAsync(true);
            InitDashboardAction.getCountAll(month, tahun, {
                callback: function (response) {
                    countNumber('sum_rj', response.jmlRJ);
                    countNumber('sum_igd', response.jmlIGD);
                    countNumber('sum_ri', response.jmlRI);
                    countNumber('sum_telemedic', response.jmlTelemedic);
                }
            });
            setTimeout(function () {
                setChart();
            },1000);
        }
    }

    function countNumber(id, jumlah){
        if(parseInt(jumlah) > 0){
            var i = 0;
            var interval = setInterval(function () {
                if (i <= jumlah) {
                    $('#'+id).text(i);
                    i++;
                } else {
                    clearInterval(interval);
                }
            }, 500 / jumlah);
        }else{
            $('#'+id).text(jumlah);
        }
    }

    function setChart(){
        var bulan = $('#set_bulan').val();
        var tahun = $('#set_tahun').val();
        var branchId = $('[name=cek_nama_rs]');
        var awal = "(";
        var akhir = ")";
        var isi = "";
        var branch = "";
        var colorBranch = [];
        var removeColorBranch = [];
        $.each(branchId, function (i, item) {
            if (item.checked) {
                if (isi != "") {
                    isi = isi + ", '" + item.value.split("|")[0] + "'";
                } else {
                    isi = "'" + item.value.split("|")[0] + "'";
                }
                colorBranch.push({
                    'branch_id': item.value.split("|")[0],
                    'color': item.value.split("|")[1]
                })
            }else{
                removeColorBranch.push({
                    'branch_id': item.value.split("|")[0],
                    'color': item.value.split("|")[1]
                });
            }
        });

        if (isi != "") {
            var warna = $('.cek_warna');
            var jenisKunjungan = "";
            $.each(warna, function (iw, itemw) {
                if('info-box-icon bg-yellow cek_warna' == itemw.className){
                    if(itemw.id == 'count_rj'){
                        jenisKunjungan = 'rawat_jalan';
                    }
                    if(itemw.id == 'count_igd'){
                        jenisKunjungan = 'igd';
                    }
                    if(itemw.id == 'count_ri'){
                        jenisKunjungan = 'rawat_inap';
                    }
                    if(itemw.id == 'count_telemedic'){
                        jenisKunjungan = 'telemedic';
                    }
                }
            });
            branch = awal + isi + akhir;
            var tempBranch = "";
            var tempTgl = "";
            var tempTotal = "";
            var dataBranch = [];
            var month = parseInt(bulan) + 1;
            $('#modal-loading').modal({show:true, backdrop:'static'});
            dwr.engine.setAsync(true);
            InitDashboardAction.getKunjuganRJ(month, tahun, branch, jenisKunjungan, {
                callback: function (response) {
                    var tempTotal = "";
                    var tempBranch = "";
                    var tempTgl = "";
                    var tempDate = "";
                    $.each(response, function (i, item) {
                        var tanggal = "";
                        if (item.tanggal != null && item.tanggal != '') {
                            tanggal = converterDate(item.tanggal);
                        }
                        if (tempBranch != item.branchId) {
                            tempBranch = item.branchId;
                            dataBranch.push({
                                'branch_id': item.branchId,
                                'branch_name': item.branchName,
                                'tanggal': item.tanggal,
                                'total': item.total
                            });
                        }
                        if (tanggal != "") {
                            var tgl = converterDate(item.tanggal);
                            if (tempDate != tgl) {
                                tempDate = tgl;
                                if (tempTgl != "") {
                                    tempTgl = tempTgl + '|' + tgl;
                                } else {
                                    tempTgl = tgl;
                                }
                                if (tempTotal != "") {
                                    tempTotal = tempTotal + '|' + item.branchId + '#' + item.total;
                                } else {
                                    tempTotal = item.branchId + '#' + item.total;
                                }
                            } else {
                                if (tempTotal != "") {
                                    tempTotal = tempTotal + '=' + item.branchId + '#' + item.total;
                                } else {
                                    tempTotal = item.branchId + '#' + item.total;
                                }
                            }
                        }
                    });
                    dataBranch.sort(function (a, b) {
                        var keyA = a.branch_id,
                            keyB = b.branch_id;
                        if (keyA < keyB) return -1;
                        if (keyA > keyB) return 1;
                        return 0;
                    });
                    var tt = "";
                    var tempUnit = [];
                    $.each(dataBranch, function (i, item) {
                        if (tt != item.branch_id) {
                            tt = item.branch_id;
                            tempUnit.push({
                                'branch_id': item.branch_id,
                                'branch_name': item.branch_name,
                            });
                        }
                    });
                    if (tempUnit.length > 0, tempTgl != "" && tempTotal != "") {
                        var tTgl = tempTgl.split("|");
                        var tTotal = tempTotal.split("|");
                        var temp = "";
                        var tempY = "";
                        var tempL = "";
                        var tempCo = "";

                        $.each(tTgl, function (i, item) {
                            var a = '{' + '"' + 'y' + '"' + ':' + '"' + item + '"' + ',';
                            var b = "}";
                            var tp = tTotal[i].split("=");
                            var isi = "";

                            $.each(tempUnit, function (it, itemt) {
                                var tot = 0;
                                $.each(tp, function (ix, itemx) {
                                    var id = itemx.split("#")[0];
                                    var nilai = itemx.split("#")[1];
                                    if (id == itemt.branch_id) {
                                        if(itemx.split("#")[1] != undefined){

                                        }
                                        tot = nilai;
                                    }
                                });
                                var it = it + 1;
                                if (isi != "") {
                                    isi = isi + ',' + '"' + 'item' + it + '"' + ':' + '"' + tot + '"';
                                } else {
                                    isi = '"' + 'item' + it + '"' + ':' + '"' + tot + '"';
                                }
                            });

                            if (temp != "") {
                                temp = temp + ', ' + a + isi + b;
                            } else {
                                temp = a + isi + b;
                            }
                        });
                        $.each(tempUnit, function (it, itemt) {
                            var it = it + 1;
                            if (tempY != "") {
                                tempY = tempY + ', ' + '"' + 'item' + it + '"';
                                tempL = tempL + ', ' + '"' + itemt.branch_name + '"';
                            } else {
                                tempY = '"' + 'item' + it + '"';
                                tempL = '"' + itemt.branch_name + '"';
                            }
                            $.each(colorBranch, function (ic, itemc) {
                                if (itemt.branch_id == itemc.branch_id) {
                                    if (tempCo != "") {
                                        tempCo = tempCo + ',"' + itemc.color + '"';
                                    } else {
                                        tempCo = '"' + itemc.color + '"';
                                    }
                                }
                            });
                        });

                        var dataC = "[" + temp + "]";
                        var dataY = "[" + tempY + "]";
                        var dataL = "[" + tempL + "]";
                        var dataCo = "[" + tempCo + "]";

                        var dataPar = JSON.parse(dataC);
                        var dataParY = JSON.parse(dataY);
                        var dataParL = JSON.parse(dataL);
                        var dataParCo = JSON.parse(dataCo);

                        $('#line-chart').empty();
                        var line = new Morris.Line({
                            element: 'line-chart',
                            resize: true,
                            data: dataPar,
                            xkey: 'y',
                            ykeys: dataParY,
                            labels: dataParL,
                            lineColors: dataParCo,
                            hideHover: 'auto',
                            parseTime: false,
                            lineWidth: 1,
                            smooth:true
                        });
                    } else {
                        $('#line-chart').empty();
                    }
                }
            });

            var colorJenis = [];
            colorJenis.push(
                {
                    'id': 'umum',
                    'color': '#4d4dff'
                },
                {
                    'id': 'bpjs',
                    'color': '#0F9E5E'
                },
                {
                    'id': 'asuransi',
                    'color': '#ffff00'
                },
                {
                    'id': 'rekanan',
                    'color': '#66ff33'
                },
                {
                    'id': 'paket_perusahaan',
                    'color': '#cc3399'
                },
                {
                    'id': 'paket_individu',
                    'color': '#f56954'
                });

            var tempU = "";
            var tempClr = "";
            var legendJP = "";
            dwr.engine.setAsync(true);
            InitDashboardAction.getDetailKunjuganRJ(month, tahun, branch, jenisKunjungan, {
                callback: function (response) {
                    if (response.length > 0) {
                        $.each(colorBranch, function (ic, itemc) {
                            $('#donut-chart-'+itemc.branch_id).empty();
                            var isi = "";
                            $.each(response, function (i, item) {
                                if(itemc.branch_id == item.branchId){
                                    var tot = 0;
                                    if(item.total != null){
                                        tot = item.total;
                                    }
                                    if(isi != ""){
                                        isi = isi+',{"'+'label'+'":'+'"'+item.statusPeriksaName+'"'+',"'+'value'+'":'+'"'+tot+'"}';
                                    }else{
                                        isi = '{"'+'label'+'":'+'"'+item.statusPeriksaName+'"'+',"'+'value'+'":'+'"'+tot+'"}';
                                    }
                                    if(ic == 0){
                                        $.each(colorJenis, function (ii, itemi) {
                                            if(item.idJenisPeriksaPasien == itemi.id){
                                                if(tempClr != ""){
                                                    tempClr = tempClr+',"'+itemi.color+'"';
                                                }else{
                                                    tempClr = '"'+itemi.color+'"';
                                                }
                                                legendJP += '<li><span class="fa-li"><i class="fa fa-circle" style="color: '+itemi.color+'"></i></span>'+item.statusPeriksaName+'</li>';
                                            }
                                        });
                                        $('#legend_jp').html(legendJP);
                                    }
                                }
                            });

                            var dat = "["+isi+"]";
                            var col = "["+tempClr+"]";
                            var parseData = JSON.parse(dat);
                            var parseCol = JSON.parse(col);
                            var donut = new Morris.Donut({
                                element: 'donut-chart-' + itemc.branch_id,
                                resize: true,
                                colors: parseCol,
                                data: parseData,
                                hideHover: 'auto'
                            });
                        });
                    }
                }
            });

            var colorJenisJK = [];
            colorJenisJK.push(
                {
                    'id': 'P',
                    'color': '#ff66cc'
                },
                {
                    'id': 'L',
                    'color': '#66ccff'
                });

            var tempClrJk = "";
            var legendJK = "";
            dwr.engine.setAsync(true);
            InitDashboardAction.getDetailKunjuganJK(month, tahun, branch, jenisKunjungan, {
                callback: function (response) {
                    if (response.length > 0) {
                        $.each(colorBranch, function (ic, itemc) {
                            $('#donut-chart-jk-'+itemc.branch_id).empty();
                            var isiJK = "";
                            $.each(response, function (i, item) {
                                if(itemc.branch_id == item.branchId){
                                    var tot = 0;
                                    var jenisKelamin = "Perempuan";
                                    if(item.total != null){
                                        tot = item.total;
                                    }
                                    if(item.jenisKelamin == "L"){
                                        jenisKelamin = "Laki-Laki";
                                    }
                                    if(isiJK != ""){
                                        isiJK = isiJK+',{"'+'label'+'":'+'"'+jenisKelamin+'"'+',"'+'value'+'":'+'"'+tot+'"}';
                                    }else{
                                        isiJK = '{"'+'label'+'":'+'"'+jenisKelamin+'"'+',"'+'value'+'":'+'"'+tot+'"}';
                                    }
                                    if(ic == 0){
                                        $.each(colorJenisJK, function (ii, itemjk) {
                                            if(item.jenisKelamin == itemjk.id){
                                                if(tempClrJk != ""){
                                                    tempClrJk = tempClrJk+',"'+itemjk.color+'"';
                                                }else{
                                                    tempClrJk = '"'+itemjk.color+'"';
                                                }
                                                legendJK += '<li><span class="fa-li"><i class="fa fa-circle" style="color: '+itemjk.color+'"></i></span>'+jenisKelamin+'</li>';
                                            }
                                        });
                                        $('#legend_jk').html(legendJK);
                                    }
                                }
                            });

                            var datJK = "["+isiJK+"]";
                            var colJK = "["+tempClrJk+"]";
                            var parseDataJK = JSON.parse(datJK);
                            var parseColJK = JSON.parse(colJK);
                            var donut = new Morris.Donut({
                                element: 'donut-chart-jk-' + itemc.branch_id,
                                resize: true,
                                colors: parseColJK,
                                data: parseDataJK,
                                hideHover: 'auto'
                            });
                        });
                    }
                }
            });

            var tempBranch = "";
            var tempTgl = "";
            var tempTotal = "";
            var dataBranch = [];
            var month = parseInt(bulan) + 1;
            dwr.engine.setAsync(true);
            InitDashboardAction.getKamarTerpakai(month, tahun, branch, {
                callback: function (response) {
                    var tempTotal = "";
                    var tempBranch = "";
                    var tempTgl = "";
                    var tempDate = "";
                    $.each(response, function (i, item) {
                        var tanggal = "";
                        if (item.tanggal != null && item.tanggal != '') {
                            tanggal = converterDate(item.tanggal);
                        }
                        if (tempBranch != item.branchId) {
                            tempBranch = item.branchId;
                            dataBranch.push({
                                'branch_id': item.branchId,
                                'branch_name': item.branchName,
                                'tanggal': item.tanggal,
                                'total': item.total
                            });
                        }
                        if (tanggal != "") {
                            var tgl = converterDate(item.tanggal);
                            if (tempDate != tgl) {
                                tempDate = tgl;
                                if (tempTgl != "") {
                                    tempTgl = tempTgl + '|' + tgl;
                                } else {
                                    tempTgl = tgl;
                                }
                                if (tempTotal != "") {
                                    tempTotal = tempTotal + '|' + item.branchId + '#' + item.total+ '#' + item.all;
                                } else {
                                    tempTotal = item.branchId + '#' + item.total+ '#' + item.all;
                                }
                            } else {
                                if (tempTotal != "") {
                                    tempTotal = tempTotal + '=' + item.branchId + '#' + item.total+ '#' + item.all;
                                } else {
                                    tempTotal = item.branchId + '#' + item.total+ '#' + item.all;
                                }
                            }
                        }
                    });
                    dataBranch.sort(function (a, b) {
                        var keyA = a.branch_id,
                            keyB = b.branch_id;
                        if (keyA < keyB) return -1;
                        if (keyA > keyB) return 1;
                        return 0;
                    });
                    var tt = "";
                    var tempUnit = [];
                    $.each(dataBranch, function (i, item) {
                        if (tt != item.branch_id) {
                            tt = item.branch_id;
                            tempUnit.push({
                                'branch_id': item.branch_id,
                                'branch_name': item.branch_name,
                            });
                        }
                    });
                    if (tempUnit.length > 0, tempTgl != "" && tempTotal != "") {
                        var tTgl = tempTgl.split("|");
                        var tTotal = tempTotal.split("|");
                        var temp = "";
                        var tempY = "";
                        var tempL = "";
                        var tempCo = "";

                        $.each(tTgl, function (i, item) {
                            var a = '{' + '"' + 'y' + '"' + ':' + '"' + item + '"' + ',';
                            var b = "}";
                            var tp = tTotal[i].split("=");
                            var isi = "";
                            var tempNum = 0;

                            $.each(tempUnit, function (it, itemt) {
                                var tot = 0;
                                var all = 0;
                                $.each(tp, function (ix, itemx) {
                                    var id = itemx.split("#")[0];
                                    if (id == itemt.branch_id) {
                                        if(itemx.split("#")[1] != undefined){
                                            tot = itemx.split("#")[1];
                                        }
                                        if(itemx.split("#")[2] != undefined){
                                            all = itemx.split("#")[2];
                                        }
                                    }
                                });
                                var it = it + 1;
                                var it2 = it + 1;
                                var it3 = tempNum + 1;
                                var it4 = it3 + 1;
                                if (isi != "") {
                                    isi = isi + ',' + '"' + 'item' + it3 + '"' + ':' + '"' + tot + '",'+'"' + 'item' + it4 + '"' + ':' + '"' + all + '"';
                                    tempNum = it4;
                                } else {
                                    isi = '"' + 'item' + it + '"' + ':' + '"' + tot + '",'+'"' + 'item' + it2 + '"' + ':' + '"' + all + '"';
                                    tempNum = it2;
                                }
                            });

                            if (temp != "") {
                                temp = temp + ', ' + a + isi + b;
                            } else {
                                temp = a + isi + b;
                            }
                        });

                        var tempNum = 0;
                        $.each(tempUnit, function (it, itemt) {
                            var it = it + 1;
                            var it2 = it + 1;
                            var it3 = tempNum + 1;
                            var it4 = it3 + 1;
                            if (tempY != "") {
                                tempY = tempY + ', ' + '"' + 'item' + it3 + '",'+'"' + 'item' + it4 + '"';;
                                tempL = tempL + ', ' + '"Terpakai ' + itemt.branch_name + '",'+'"Total ' + itemt.branch_name + '"';
                                tempNum = it4;
                            } else {
                                tempY = '"' + 'item' + it + '",'+'"' + 'item' + it2 + '"';;
                                tempL = '"Terpakai ' + itemt.branch_name + '",'+'"Total ' + itemt.branch_name + '"';
                                tempNum = it2;
                            }
                            $.each(colorBranch, function (ic, itemc) {
                                if (itemt.branch_id == itemc.branch_id) {
                                    if (tempCo != "") {
                                        tempCo = tempCo + ', "' + itemc.color + '",'+'"' + itemc.color + '"';
                                    } else {
                                        tempCo = '"' + itemc.color + '",'+'"' + itemc.color + '"';
                                    }
                                }
                            });
                        });

                        var dataC = "[" + temp + "]";
                        var dataY = "[" + tempY + "]";
                        var dataL = "[" + tempL + "]";
                        var dataCo = "[" + tempCo + "]";

                        var dataPar = JSON.parse(dataC);
                        var dataParY = JSON.parse(dataY);
                        var dataParL = JSON.parse(dataL);
                        var dataParCo = JSON.parse(dataCo);

                        $('#line-chart-kamar').empty();
                        var line = new Morris.Line({
                            element: 'line-chart-kamar',
                            resize: true,
                            data: dataPar,
                            xkey: 'y',
                            ykeys: dataParY,
                            labels: dataParL,
                            lineColors: dataParCo,
                            hideHover: 'auto',
                            parseTime: false,
                            lineWidth: 1,
                            smooth:true,
                            continuousLine: true
                        });
                    } else {
                        $('#line-chart-kamar').empty();
                    }
                    $('#modal-loading').modal('hide');
                }
            });
        }else{
            $('#line-chart').empty();
            $('#line-chart-kamar').empty();
            if(removeColorBranch.length > 0){
                $.each(removeColorBranch, function (i, item) {
                    $('#donut-chart-jk-'+item.branch_id).empty();
                    $('#donut-chart-'+item.branch_id).empty();
                });
            }
        }

        if(removeColorBranch.length > 0){
            $.each(removeColorBranch, function (i, item) {
                $('#donut-chart-jk-'+item.branch_id).empty();
                $('#donut-chart-'+item.branch_id).empty();
            });
        }
    }

    function cekBranch(id){
        setChart();
    }

    function setJenis(idCari){
        var warna = $('.cek_warna');
        $.each(warna, function (i, item) {
            if(item.id == idCari){
                var cek = $('#'+item.id).hasClass("info-box-icon bg-green cek_warna");
                if(cek){
                    $('#'+item.id).removeClass("info-box-icon bg-green cek_warna");
                    $('#'+item.id).addClass("info-box-icon bg-yellow cek_warna");
                }
            }else{
                var cek = $('#'+item.id).hasClass("info-box-icon bg-yellow cek_warna");
                if(cek){
                    $('#'+item.id).removeClass("info-box-icon bg-yellow cek_warna");
                    $('#'+item.id).addClass("info-box-icon bg-green cek_warna");
                }
            }
        });
        if(idCari == 'count_rj'){
            $('#kj_label, #jp_label, #jk_label').text("Rawat Jalan");
        }else if(idCari == 'count_igd'){
            $('#kj_label, #jp_label, #jk_label').text("IGD");
        }else if(idCari == 'count_ri'){
            $('#kj_label, #jp_label, #jk_label').text("Rawat Inap");
        }else if(idCari == 'count_telemedic'){
            $('#kj_label, #jp_label, #jk_label').text("Telemedic");
        }else{
            $('#kj_label, #jp_label, #jk_label').text("*[Silahkan pilih jenis kunjungan]");
        }
        setChart();
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>