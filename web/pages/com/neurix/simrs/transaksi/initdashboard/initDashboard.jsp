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
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 50px; height: 50px; margin-top: 18px"
                                            src="<s:url value="/pages/images/logo-dokter.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Rawat Jalan</span>
                                        <span class="info-box-number"><span id="sum_rj"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 30px; height: 50px; margin-top: 18px"
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
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 50px; height: 50px; margin-top: 18px"
                                            src="<s:url value="/pages/images/logo-poli.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Rawat Inap</span>
                                        <span class="info-box-number"><span id="sum_ri"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 50px; height: 50px; margin-top: 18px"
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
                        Jumlah Kunjungan Pasien Rawat Jalan
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
                        Detail Kunjungan Pasien Rawat Jalan
                    </div>
                    <div class="box-body">
                        <div class="row" id="donut_rs">
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
        var donutRS = "";
        var tempColor = [];
        dwr.engine.setAsync(true);
        InitDashboardAction.getComboBranch({
            callback: function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        var color = "#99ccff";
                        // var colorRandom = getRandomColor();
                        // if(tempColor.length > 0){
                        //     $.each(tempColor, function (ic, itemc) {
                        //         if(colorRandom == itemc.color || "#3D3418" == colorRandom){
                        //             colorRandom = getRandomColor();
                        //         }else{
                        //             color = colorRandom;
                        //             tempColor.push({
                        //                 'color':color
                        //             });
                        //         }
                        //     });
                        // }else{
                        //     color = colorRandom;
                        //     tempColor.push({
                        //         'color':color
                        //     });
                        // }
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
                    });
                    $('#nama_rs').html(namaRS);
                    $('#donut_rs').html(donutRS);
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
                    if (parseInt(response.jmlRJ) > 0) {
                        var i = 0;
                        var interval = setInterval(function () {
                            if (i <= response.jmlRJ) {
                                $('#sum_rj').text(i);
                                i++;
                            } else {
                                clearInterval(interval);
                            }
                        }, 5000 / response.jmlRJ);
                    } else {
                        $('#sum_rj').text(response.jmlRJ);
                    }

                    if (parseInt(response.jmlRI) > 0) {
                        var i = 0;
                        var interval = setInterval(function () {
                            if (i <= response.jmlRJ) {
                                $('#sum_ri').text(i);
                                i++;
                            } else {
                                clearInterval(interval);
                            }
                        }, 5000 / response.jmlRJ);
                    } else {
                        $('#sum_ri').text(response.jmlRI);
                    }

                    if (parseInt(response.jmlIGD) > 0) {
                        var i = 0;
                        var interval = setInterval(function () {
                            if (i <= response.jmlRJ) {
                                $('#sum_igd').text(i);
                                i++;
                            } else {
                                clearInterval(interval);
                            }
                        }, 5000 / response.jmlRJ);
                    } else {
                        $('#sum_igd').text(response.jmlIGD);
                    }

                    if (parseInt(response.jmlTelemedic) > 0) {
                        var i = 0;
                        var interval = setInterval(function () {
                            if (i <= response.jmlRJ) {
                                $('#sum_telemedic').text(i);
                                i++;
                            } else {
                                clearInterval(interval);
                            }
                        }, 5000 / response.jmlRJ);
                    } else {
                        $('#sum_telemedic').text(response.jmlTelemedic);
                    }
                }
            });
            setTimeout(function () {
                setChart();
            },1000);
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
            }
        });
        if (isi != "") {
            branch = awal + isi + akhir;
            var tempBranch = "";
            var tempTgl = "";
            var tempTotal = "";
            var dataBranch = [];
            var month = parseInt(bulan) + 1;
            $('#modal-loading').modal({show:true, backdrop:'static'});
            dwr.engine.setAsync(true);
            InitDashboardAction.getKunjuganRJ(month, tahun, branch, {
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
                                        tempCo = tempCo + ', "' + itemc.color + '"';
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
            dwr.engine.setAsync(true);
            InitDashboardAction.getDetailKunjuganRJ(month, tahun, branch, {
                callback: function (response) {
                    if (response.length > 0) {
                        $.each(colorBranch, function (ic, itemc) {
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
                                            }
                                        });
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
                                        tempCo = tempCo + ', "' + itemc.color + '"';
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
                            smooth:true
                        });
                    } else {
                        $('#line-chart-kamar').empty();
                    }
                    $('#modal-loading').modal('hide');
                }
            });
        }
    }

    function cekBranch(id){
        setChart();
    }

    function getRandomColor() {
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>