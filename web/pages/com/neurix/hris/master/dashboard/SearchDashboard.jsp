<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script src="https://cdn.anychart.com/releases/8.6.0/js/anychart-bundle.min.js"></script>
    <script src="https://cdn.anychart.com/releases/8.6.0/js/anychart-base.min.js"></script>
    <script src="https://cdn.anychart.com/releases/8.6.0/js/anychart-exports.min.js"></script>
    <script src="https://cdn.anychart.com/releases/8.6.0/js/anychart-ui.min.js"></script>
    <link rel="stylesheet" href="https://cdn.anychart.com/releases/8.6.0/css/anychart-ui.min.css" />
    <script src="https://cdn.anychart.com/releases/8.6.0/js/anychart-core.min.js"></script>
    <script src="https://cdn.anychart.com/releases/8.6.0/js/anychart-sunburst.min.js"></script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/SppdAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DashboardAbsensiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AbsensiAction.js"/>'></script>

    <style>
        .anychart-credits {
            display: none;
        }

        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }

        .select2-selection__choice{
            background-color: dodgerblue;
        }

        .products-list .product-img img {
            width: 70px;
            height: 72px;
        }
        .products-list .product-info {
            margin-left: 80px;
        }

        .box-header>.fa, .box-header>.glyphicon, .box-header>.ion, .box-header .box-title {
            display: inherit;
        }
    </style>
    <script type='text/javascript'>
        $(document).ready(function() {
            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();

            today = dd + '-' + mm + '-' + yyyy;
            $('#strFilterAbsensiTanggal').val(today);
            $('#strFilterAbsensiTanggal').datepicker({
                dateFormat: 'dd-mm-yy',
                changeMonth: true,
                changeYear: true
            });

            $('#strFilterAbsensiMangkir').val(today);
            $('#strFilterAbsensiMangkir').datepicker({
                dateFormat: 'dd-mm-yy'
            });

            var d = new Date();
            $('#strFilterAbsensiTahun').val(d.getFullYear()).change;
            $('#strFilterTahun').val(d.getFullYear()).change;

            window.printPath = function(path) {
                var text = "";

                for (var i = 0; i < path.length; i++) {
                    text = path[i].get("name");
                }
                return text;
            }

            $('.cekCronJob').click(function(){
                AbsensiAction.cekCronJob(function(listdata) {
                    alert('Berhasil');
                });
            });

            window.filterTahunTopSppd = function(divisiName){
                var tahun = document.getElementById('strFilterTahun').value;
                var bulan = document.getElementById('strFilterBulan').value;
                //alert(divisiName);

                // person top 10
                SppdAction.getSppdTahunByNip(tahun, bulan, divisiName, function(listdata) {
                    var dataa = [];
                    var nama = [];
                    var warna = [];

                    $.each(listdata, function (i, item){
                        dataa.push({x: item.personName, value: item.sppdId, hari : item.branchId});
                    });

                    if(window.chartTopSppd){
                        // initiate drawing the chart
                        window.chartTopSppd.noData();
                        window.chartTopSppd.data(dataa);
                        window.chartTopSppd.removeSeriesAt(1);

                        window.chartTopSppd.animation(true);

                        window.chartTopSppd.listen("pointClick", function(e){
                            console.log(e.data);
                        });
                        // set container id for the chart
                        window.chartTopSppd.container('barHorizontal');
                        // initiate chart drawing
                        window.chartTopSppd.draw();


                    }else{
                        window.chartTopSppd = anychart.bar();
                        //chart.theme(themeSettings);

                        window.chartTopSppd.animation(true);

                        // create bar series with passed data
                        var series = window.chartTopSppd.bar(dataa);
                        // set labels position
                        labels = series.labels();
                        labels.enabled(true);

                        labels.format(function () {
                            return anychart.format.number(this.value, 3, ",", ".")
                        });

                        window.chartTopSppd.listen("pointClick", function(e){
                            console.log(e.point.get("value") + " #" + e.point.getIndex() + " " + e.point.getSeries().name());
                        });

                        hasil = labels.format();
                        //alert (hasil);
                        // set tooltip settings
                        series.tooltip()
                                .position('right')
                                .anchor('left-center')
                                .offsetX(5)
                                .offsetY(0)
                                .titleFormat('{%X}')
                                .format(
                                        "Jumlah SPPD : {%Value}{groupsSeparator:\\.}\nJumlah Hari: {%Hari}"
                                );

                        window.chartTopSppd.tooltip().positionMode('point');

                        // set container id for the chart
                        window.chartTopSppd.container('barHorizontal');
                        // initiate chart drawing
                        window.chartTopSppd.draw();
                    }

                });
            }

            window.filterTahun = function(){
                var tahun = document.getElementById('strFilterTahun').value;
                var bulan = document.getElementById('strFilterBulan').value;

                var strBulan = "Tahun ";
                if(bulan != "-"){
                    strBulan = "Bulan " + $("#strFilterBulan option:selected").text();
                }
                $('#boxTitlePie').text("Jumlah SPPD " + strBulan + " " + tahun+ " (Orang)");
                $('#boxTitleDoughnut').text(strBulan + " " + tahun + " (Bagian)");
                $('#boxTitleBarHorizontal').text("10 Orang SPPD Teratas " + strBulan + " " + tahun);

                // sunburst
                chartSunburst(tahun, bulan);
            }

            window.filterTahunTopMedical = function(divisiName){
                var tahun = document.getElementById('strFilterMedicalTahun').value;
                var bulan = document.getElementById('strFilterMedicalBulan').value;

                // person top 10
                DashboardAbsensiAction.dashboardMedicalRecordsTop(bulan, tahun, divisiName, function(listdata) {
                    var dataa = [];
                    var nama = [];
                    var warna = [];

                    $.each(listdata, function (i, item){
                        nama.push(item.nama);
                        dataa.push([item.nama, item.jumlahPengobatan]);
                        warna.push(dynamicColors()) ;
                    });


                    if(window.chartTopMedical){
                        // initiate drawing the chart
                        window.chartTopMedical.noData();
                        window.chartTopMedical.data(dataa);

                        window.chartTopMedical.animation(true);

                        // set container id for the chart
                        window.chartTopMedical.container('barHorizontalMedical');
                        // initiate chart drawing
                        window.chartTopMedical.draw();


                    }else{
                        window.chartTopMedical = anychart.bar();
                        //chart.theme(themeSettings);

                        window.chartTopMedical.animation(true);

                        // create bar series with passed data
                        var series = window.chartTopMedical.bar(dataa);
                        // set labels position
                        labels = series.labels();
                        labels.enabled(true);

                        labels.format(function () {
                            return anychart.format.number(this.value, 3, ",", ".")
                        });
                        // set tooltip settings
                        series.tooltip()
                                .position('right')
                                .anchor('left-center')
                                .offsetX(5)
                                .offsetY(0)
                                .titleFormat('{%X}')
                                .format(function () {
                                    return "Rp " + anychart.format.number(this.value, 3, ",", ".")
                                });

                        // set titles for axises
                        //chart.interactivity().hoverMode('by-x');
                        window.chartTopMedical.tooltip().positionMode('point');

                        // set container id for the chart
                        window.chartTopMedical.container('barHorizontalMedical');
                        // initiate chart drawing
                        window.chartTopMedical.draw();
                    }

                });
            }

            window.filterTahunMedical = function(){
                var tahun = document.getElementById('strFilterMedicalTahun').value;
                var bulan = document.getElementById('strFilterMedicalBulan').value;

                var strBulan = "Tahun ";
                if(bulan != "-"){
                    strBulan = "" + $("#strFilterMedicalBulan option:selected").text();
                }
                $('#boxTitleMedical').text("Biaya Pengobatan " + strBulan + " " + tahun+ " (Rupiah)");
                $('#boxTitleBarHorizontalMedical').text("10 Biaya Pengobatan Teratas " + strBulan + " " + tahun);

                // sunburst
                chartSunburstMedical(tahun, bulan);
            }

            window.filterTahunTopLembur = function(divisiName){
                var tahun = document.getElementById('strFilterLemburTahun').value;
                var bulan = document.getElementById('strFilterLemburBulan').value;

                // person top 10
                DashboardAbsensiAction.dashboardLemburTop(bulan, tahun, divisiName, function(listdata) {
                    var dataa = [];
                    var nama = [];
                    var warna = [];

                    $.each(listdata, function (i, item){
                        nama.push(item.nama);
                        dataa.push({x: item.nama, value: item.jumlahLembur, jam: item.lamaLembur});
                        warna.push(dynamicColors()) ;
                    });


                    if(window.chartTopLembur){
                        // initiate drawing the chart
                        window.chartTopLembur.noData();
                        window.chartTopLembur.data(dataa);
                        window.chartTopLembur.animation(true);
                        window.chartTopLembur.removeSeriesAt(1);

                        // set container id for the chart
                        window.chartTopLembur.container('barHorizontalLembur');
                        // initiate chart drawing
                        window.chartTopLembur.draw();

                    }else{
                        window.chartTopLembur = anychart.bar();
                        //chart.theme(themeSettings);

                        window.chartTopLembur.animation(true);

                        // create bar series with passed data
                        var series = window.chartTopLembur.bar(dataa);
                        // set labels position
                        labels = series.labels();
                        labels.enabled(true);

                        labels.format(function () {
                            return anychart.format.number(this.value, 3, ",", ".")
                        });

                        hasil = labels.format();
                        //alert (hasil);
                        // set tooltip settings
                        series.tooltip()
                                .position('right')
                                .anchor('left-center')
                                .offsetX(5)
                                .offsetY(0)
                                .titleFormat('{%X}')
                                .format(
                                        "Biaya Lembur: {%Value}{groupsSeparator:\\.}\nLama Jam Lembur : {%Jam}"
                                );

                        window.chartTopLembur.tooltip().positionMode('point');

                        // set container id for the chart
                        window.chartTopLembur.container('barHorizontalLembur');
                        // initiate chart drawing
                        window.chartTopLembur.draw();
                    }
                });
            };

            window.filterTahunLembur = function(){
                var tahun = document.getElementById('strFilterLemburTahun').value;
                var bulan = document.getElementById('strFilterLemburBulan').value;

                var strBulan = "Tahun ";
                if(bulan != "-"){
                    strBulan = "" + $("#strFilterLemburBulan option:selected").text();
                }
                $('#boxTitleLembur').text("Biaya Lembur " + strBulan + " " + tahun+ " (Rupiah)");
                $('#boxTitleBarHorizontalLembur').text("10 Biaya Lembur Teratas " + strBulan + " " + tahun);

                // sunburst
                chartSunburstLembur(tahun, bulan);
            }

            window.filterTahunAbsensi = function(){
                var tahun = document.getElementById('strFilterAbsensiTahun').value;
                var bulan = document.getElementById('strFilterAbsensiBulan').value;

                var strBulan = "Tahun ";
                if(bulan != "-"){
                    strBulan = "Bulan " + $("#strFilterAbsensiBulan option:selected").text();
                }

                $('#labelAbsensiKaryawan').text("Daftar 10 Teratas Karyawan Telat " + strBulan + " " + tahun);

                DashboardAbsensiAction.getKaryawanTelat(bulan, tahun, function(listdata) {
                    var hasil1 = "";
                    var hasil2 = "";
                    var tmpHasil = "";

                    $.each(listdata, function (i, item){
                        tmpHasil = '<li id="item" class="item"> <div class="product-img"> ' +
                                '<img src="/hris/pages/upload/image/profile/'+item.nip+'.jpg" alt="Product Image" ' +
                                'onerror="this.onerror=null; this.src=\'/hris/pages/upload/image/profile/unknown-person2.jpg\'"> ' +
                                '</div>' +
                                '<div class="product-info"> Nama : <a href="javascript:void(0)" class="product-title" onclick="getDetail(\''+item.nip+'\', \''+item.nama+'\')">' +
                                item.nama + '<span style="font-size: 13px" class="label label-warning pull-right">'+item.jumlahTelat+'</span></a>'+
                                '<span class="product-description"> Bidang : '+item.divisiName+' </span>'+
                                '<span class="product-description"> Bagian : '+item.bagianName+' </span>'+
                                '<span class="product-description"> Posisi \t\t: '+item.positionName+' </span>'+
                                '</div> </li>';

                        if(i < 5){
                            hasil1 += tmpHasil ;
                        }else{
                            hasil2 += tmpHasil ;
                        }
                    });

                    $('#kolom1').html(hasil1);
                    $('#kolom2').html(hasil2);
                });
            }

            filterTahunAbsensi();
            //filterTahun();

            window.filterTahunAbsensiTanggal = function(){
                var tanggal = document.getElementById('strFilterAbsensiTanggal').value;

                var strBulan = "Tahun ";
                if(bulan != "-"){
                    strBulan = "Bulan " + $("#strFilterAbsensiTanggalBulan option:selected").text();
                }

                $('#labelAbsensiTanggalKaryawan').text("Daftar Karyawan Paling Terlambat");
                $('#labelAbsensiTanggalRajinKaryawan').text("Daftar Karyawan Paling On Time");

                DashboardAbsensiAction.getKaryawanAbsensiTelat(tanggal, function(listdata) {
                    var hasil1 = "";
                    var tmpHasil = "";
                    var a = 1 ;

                    $.each(listdata, function (i, item){
                        a++;
                        tmpHasil = '<li id="item" class="item"> <div class="product-img"> ' +
                                '<img style="width: 105px; height: 105px" src="/hris/pages/upload/image/profile/'+item.nip+'.jpg" alt="Product Image" ' +
                                'onerror="this.onerror=null; this.src=\'/hris/pages/upload/image/profile/unknown-person2.jpg\'"> ' +
                                '</div>' +
                                '<div class="product-info" style="margin-left: 120px"> Nama : <a class="product-title">' + item.nama+' </a>'+
                                '<span class="product-description" style="padding: 2px"> Bidang      : '+item.divisiName+' </span>'+
                                '<span class="product-description" style="padding: 2px"> Bagian      : '+item.bagianName+' </span>'+
                                '<span class="product-description" style="padding: 2px"> Posisi \t\t  : '+item.positionName+' </span>'+
                                '<span class="product-description" style="padding: 2px"> Jam Masuk : '+item.jamMasuk+' </span>'+
                                '</div> </li>';

                        hasil1 += tmpHasil ;
                    });

                    for(i = a ; i <= 3; i++){
                        tmpHasil = '<li id="item" class="item"> <div class="product-img"> ' +
                                '<img style="width: 105px; height: 105px" src="/hris/pages/upload/image/profile/unknown-person2.jpg" alt="Product Image"> ' +
                        '</div>' +
                        '<div class="product-info" style="margin-left: 120px"> Nama : <a class="product-title"> - </a>'+
                        '<span class="product-description" style="padding: 2px"> Bidang      : - </span>'+
                        '<span class="product-description" style="padding: 2px"> Bagian      : - </span>'+
                        '<span class="product-description" style="padding: 2px"> Posisi   : - </span>'+
                        '<span class="product-description" style="padding: 2px"> Jam Masuk : - </span>'+
                        '</div> </li>';
                        hasil1 += tmpHasil;
                    }

                    $('#kolomAbsensi1').html(hasil1);
                });

                DashboardAbsensiAction.getKaryawanAbsensiRajin(tanggal, function(listdata) {
                    var hasil1 = "";
                    var tmpHasil = "";
                    var a = 1;

                    $.each(listdata, function (i, item){
                        a++;
                        tmpHasil = '<li id="item" class="item"> <div class="product-img"> ' +
                                '<img style="width: 105px; height: 105px" src="/hris/pages/upload/image/profile/'+item.nip+'.jpg" alt="Product Image" ' +
                                'onerror="this.onerror=null; this.src=\'/hris/pages/upload/image/profile/unknown-person2.jpg\'"> ' +
                                '</div>' +
                                '<div class="product-info" style="margin-left: 120px"> Nama : <a class="product-title">' + item.nama+' </a>'+
                                '<span class="product-description" style="padding: 2px"> Bidang : '+item.divisiName+' </span>'+
                                '<span class="product-description" style="padding: 2px"> Bagian : '+item.bagianName+' </span>'+
                                '<span class="product-description" style="padding: 2px"> Posisi \t\t: '+item.positionName+' </span>'+
                                '<span class="product-description" style="padding: 2px"> Jam Masuk : '+item.jamMasuk+' </span>'+
                                '</div> </li>';

                        hasil1 += tmpHasil ;
                    });

                    for(i = a ; i <= 3; i++){
                        tmpHasil = '<li id="item" class="item"> <div class="product-img"> ' +
                                '<img style="width: 105px; height: 105px" src="/hris/pages/upload/image/profile/unknown-person2.jpg" alt="Product Image"> ' +
                                '</div>' +
                                '<div class="product-info" style="margin-left: 120px"> Nama : <a class="product-title"> - </a>'+
                                '<span class="product-description" style="padding: 2px"> Bidang      : - </span>'+
                                '<span class="product-description" style="padding: 2px"> Bagian      : - </span>'+
                                '<span class="product-description" style="padding: 2px"> Posisi   : - </span>'+
                                '<span class="product-description" style="padding: 2px"> Jam Masuk : - </span>'+
                                '</div> </li>';
                        hasil1 += tmpHasil;
                    }

                    $('#kolomAbsensi2').html(hasil1);
                });

            }

            window.filterTahunAbsensiMangkir = function(){
                var tanggal = document.getElementById('strFilterAbsensiMangkir').value;

                var strBulan = "Tahun ";
                if(bulan != "-"){
                    strBulan = "Bulan " + $("#strFilterAbsensiTanggalBulan option:selected").text();
                }

                $('#labelAbsensiTanggalMangkir').text("Daftar 6 Karyawan tidak melakukan absensi");


                DashboardAbsensiAction.getKaryawanAbsensiMangkir(tanggal, function(listdata) {
                    var hasil2 = "";
                    var hasil1 = "";
                    var tmpHasil = "";
                    var a = 0 ;

                    $.each(listdata, function (i, item){
                        a++;
                        tmpHasil = '<li id="item" class="item"> <div class="product-img"> ' +
                                '<img style="width: 105px; height: 105px" src="/hris/pages/upload/image/profile/'+item.nip+'.jpg" alt="Product Image" ' +
                                'onerror="this.onerror=null; this.src=\'/hris/pages/upload/image/profile/unknown-person2.jpg\'"> ' +
                                '</div>' +
                                '<div class="product-info" style="margin-left: 120px"> Nama : <a class="product-title">' + item.nama+' </a>'+
                                '<span class="product-description" style="padding: 2px"> Bidang      : '+item.divisiName+' </span>'+
                                '<span class="product-description" style="padding: 2px"> Bagian      : '+item.bagianName+' </span>'+
                                '<span class="product-description" style="padding: 2px"> Posisi \t\t  : '+item.positionName+' </span>'+
                                '<span class="product-description" style="padding: 2px"> Jam Masuk - :  </span>'+
                                '</div> </li>';

                        if(a <= 3){
                            hasil1 += tmpHasil ;
                        }else{
                            hasil2 += tmpHasil ;
                        }
                    });

                    for(i = a ; i < 6; i++){
                        tmpHasil = '<li id="item" class="item"> <div class="product-img"> ' +
                                '<img style="width: 105px; height: 105px" src="/hris/pages/upload/image/profile/unknown-person2.jpg" alt="Product Image"> ' +
                        '</div>' +
                        '<div class="product-info" style="margin-left: 120px"> Nama : <a class="product-title"> - </a>'+
                        '<span class="product-description" style="padding: 2px"> Bidang      : - </span>'+
                        '<span class="product-description" style="padding: 2px"> Bagian      : - </span>'+
                        '<span class="product-description" style="padding: 2px"> Posisi   : - </span>'+
                        '<span class="product-description" style="padding: 2px"> Jam Masuk : - </span>'+
                        '</div> </li>';

                        if(i < 3){
                            hasil1 += tmpHasil ;
                        }else{
                            hasil2 += tmpHasil ;
                        }

                    }

                    $('#kolomAbsensiMangkir1').html(hasil1);
                    $('#kolomAbsensiMangkir2').html(hasil2);
                });
            }

            $(".nav-tabs a").click(function () {
                var d = new Date();
                var target = $(this).attr('href');

                if (target == "#sppd") {
                    filterTahun();
                }else if (target == "#absensi") {
                    $('#strFilterAbsensiTahun').val(d.getFullYear()).change;
                    filterTahunAbsensi();
                }else if (target == "#absensiTanggal") {
                    filterTahunAbsensiTanggal();
                }else if (target == "#absensiMangkir") {
                    filterTahunAbsensiMangkir();
                }else if (target == "#medicalRecords") {
                    $('#strFilterMedicalTahun').val(d.getFullYear()).change;
                    filterTahunMedical()
                }else if (target == "#lembur") {
                    $('#strFilterLemburTahun').val(d.getFullYear()).change;
                    filterTahunLembur()
                }
                $(this).tab('show');
            });
        });

        function link(){
            window.location.href="<s:url action='initForm_updateGolongan'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="row">
            <div class="col-md-5">
                <h1>
                    Dashboard
                    <small>e-HEALTH</small>
                </h1>
            </div>
        </div>

    </section>

    <!-- Main content -->
    <section class="content">
        <ul class="nav nav-tabs" style="font-size: 13px;">
            <li class="active"><a href="#absensi">Absensi</a></li>
            <li><a href="#absensiTanggal">Absensi(Tanggal)</a></li>
            <li><a href="#absensiMangkir">Absensi(Mangkir)</a></li>
            <li><a href="#medicalRecords">Medical Record</a></li>
            <li><a href="#lembur">Lembur</a></li>
            <li><a href="#sppd">SPPD</a></li>

        </ul>
        <div class="tab-content well">
            <div id="sppd" class="tab-pane fade">
                <div class="col-md-offset-8 col-md-4" style="margin-bottom: 18px">
                    <div class="row">
                        <div id="tahun" class="form-group col-md-6">
                            <select id="strFilterTahun" class="form-control" onchange="filterTahun()">
                                <option value="2018">2018</option>
                                <option value="2019">2019</option>
                                <option value="2020">2020</option>
                                <option value="2021">2021</option>
                            </select>
                        </div>
                        <div id="bulan" class="form-group col-md-6">
                            <select id="strFilterBulan" onchange="filterTahun()" class="form-control">
                                <option value="-">Semua Bulan</option>
                                <option value="1">Januari</option>
                                <option value="2">Februari</option>
                                <option value="3">Maret</option>
                                <option value="4">April</option>
                                <option value="5">Mei</option>
                                <option value="6">Juni</option>
                                <option value="7">Juli</option>
                                <option value="8">Agustus</option>
                                <option value="9">September</option>
                                <option value="10">Oktober</option>
                                <option value="11">Nopember</option>
                                <option value="12">Desember</option>

                            </select>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <!-- /.col (LEFT) -->
                    <div class=" col-md-6">
                        <!-- BAR CHART -->
                        <div class="box box-success">
                            <div class="box-header with-border">
                                <h3 class="box-title" id="boxTitlePie"></h3>
                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                    </button>
                                    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div id="breadcrumbs"></div>
                                <div class="chart">
                                    <%--<canvas id="chart-area" style="height:480px"></canvas>--%>
                                        <div id="chart" style="height:480px"></div>
                                    <br>
                                    <br>
                                    <label id="labelSppd"></label>
                                </div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>


                    <div class="col-md-6">
                        <!-- LINE CHART -->
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title" id="boxTitleBarHorizontal"></h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                    </button>
                                    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>

                            <div class="box-body">
                                <div id="barHorizontal" style="height:523px"></div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                    </div>
                    <!-- /.col (RIGHT) -->
                </div>
            </div>

            <div id="absensi" class="tab-pane fade in active">
                 <%--Absensi--%>
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-offset-1 col-md-5" >
                                <p id="labelAbsensiKaryawan" style="font-size: 20px; padding-top:6px; padding-left: 10px" ><b></b></p>
                            </div>
                            <div class="col-md-2">
                                <select id="strFilterAbsensiTahun" class="form-control" onchange="filterTahunAbsensi()">
                                    <option value="2018">2018</option>
                                    <option value="2019">2019</option>
                                    <option value="2020">2020</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <select id="strFilterAbsensiBulan" onchange="filterTahunAbsensi()" class="form-control">
                                    <option value="-">Semua Bulan</option>
                                    <option value="1">Januari</option>
                                    <option value="2">Februari</option>
                                    <option value="3">Maret</option>
                                    <option value="4">April</option>
                                    <option value="5">Mei</option>
                                    <option value="6">Juni</option>
                                    <option value="7">Juli</option>
                                    <option value="8">Agustus</option>
                                    <option value="9">September</option>
                                    <option value="10">Oktober</option>
                                    <option value="11">Nopember</option>
                                    <option value="12">Desember</option>
                                </select>
                            </div>
                        </div>

                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i>
                            </button>
                            <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                    class="fa fa-times"></i></button>
                        </div>
                    </div>

                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="col-sm-1"></div>

                        <div class="box-body col-md-4" >
                            <ul id="kolom1" class="products-list product-list-in-box">

                            </ul>
                        </div>

                        <div class="col-sm-1"></div>

                        <div class="box-body col-md-4" >
                            <ul id="kolom2" class="products-list product-list-in-box">

                            </ul>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer text-center">
                        <%--<a href="javascript:void(0)" class="uppercase">View All</a>--%>
                    </div>
                    <!-- /.box-footer -->
                </div>
            </div>

            <div id="absensiTanggal" class="tab-pane fade">
                 <%--Absensi--%>
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-offset-1 col-md-8" >
                                <label style="font-size:20px; display: block; text-align: right; padding-top: 4px">Tanggal :</label>
                            </div>
                            <div class="col-md-2">
                                <input type="text" id="strFilterAbsensiTanggal" class="form-control pull-right" onchange="filterTahunAbsensiTanggal()"/>
                            </div>
                        </div>

                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i>
                            </button>
                            <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                    class="fa fa-times"></i></button>
                        </div>
                    </div>

                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="col-sm-1"></div>

                        <div class="box-body col-md-5" >
                            <p id="labelAbsensiTanggalKaryawan" style="font-size: 20px; padding-top:6px;" ><b></b></p>
                            <ul id="kolomAbsensi1" class="products-list product-list-in-box">

                            </ul>
                        </div>



                        <div class="box-body col-md-5" >
                            <p id="labelAbsensiTanggalRajinKaryawan" style="font-size: 20px; padding-top:6px; " ><b></b></p>
                            <ul id="kolomAbsensi2" class="products-list product-list-in-box">

                            </ul>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer text-center">
                        <%--<a href="javascript:void(0)" class="uppercase">View All</a>--%>
                    </div>
                    <!-- /.box-footer -->
                </div>
            </div>

            <div id="absensiMangkir" class="tab-pane fade">
                 <%--Absensi--%>
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-offset-1 col-md-5" >
                                <p id="labelAbsensiTanggalMangkir" style="font-size: 20px; padding-top:6px; padding-left: 10px" ><b></b></p>
                            </div>
                            <div class=" col-md-3" >
                                <%--<a href="javascript:;" class="cekCronJob">Cek CronJob</a>--%>
                                <label style="font-size:20px; display: block; text-align: right; padding-top: 4px">Tanggal :</label>
                            </div>
                            <div class="col-md-2">
                                <input type="text" id="strFilterAbsensiMangkir" class="form-control pull-right" onchange="filterTahunAbsensiMangkir()"/>
                            </div>
                        </div>

                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i>
                            </button>
                            <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                    class="fa fa-times"></i></button>
                        </div>
                    </div>

                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="col-sm-1"></div>

                        <div class="box-body col-md-5" >
                            <p id="labelAbsensiMangkirKaryawan1" style="font-size: 20px; padding-top:6px;" ><b></b></p>
                            <ul id="kolomAbsensiMangkir1" class="products-list product-list-in-box">

                            </ul>
                        </div>



                        <div class="box-body col-md-5" >
                            <p id="labelAbsensiMangkirKaryawan2" style="font-size: 20px; padding-top:6px; " ><b></b></p>
                            <ul id="kolomAbsensiMangkir2" class="products-list product-list-in-box">

                            </ul>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer text-center">
                        <%--<a href="javascript:void(0)" class="uppercase">View All</a>--%>
                    </div>
                    <!-- /.box-footer -->
                </div>
            </div>

            <div id="medicalRecords" class="tab-pane fade">
                <div class="col-md-offset-8 col-md-4" style="margin-bottom: 18px">
                    <div class="row">
                        <div id="medicalTahun" class="form-group col-md-6">
                            <select id="strFilterMedicalTahun" class="form-control" onchange="filterTahunMedical()">
                                <option value="2018">2018</option>
                                <option value="2019">2019</option>
                                <option value="2020">2020</option>
                            </select>
                        </div>
                        <div id="medicalBulan" class="form-group col-md-6">
                            <select id="strFilterMedicalBulan" onchange="filterTahunMedical()" class="form-control">
                                <option value="-">Semua Bulan</option>
                                <option value="1">Januari</option>
                                <option value="2">Februari</option>
                                <option value="3">Maret</option>
                                <option value="4">April</option>
                                <option value="5">Mei</option>
                                <option value="6">Juni</option>
                                <option value="7">Juli</option>
                                <option value="8">Agustus</option>
                                <option value="9">September</option>
                                <option value="10">Oktober</option>
                                <option value="11">Nopember</option>
                                <option value="12">Desember</option>

                            </select>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class=" col-md-6">
                        <div class="box box-success">
                            <div class="box-header with-border">
                                <h3 class="box-title" id="boxTitleMedical"></h3>
                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                    </button>
                                    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="chart">
                                    <div id="chartMedical" style="height:523px"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <!-- LINE CHART -->
                        <div class="chart">
                        </div>

                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title" id="boxTitleBarHorizontalMedical"></h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                    </button>
                                    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>


                            <div class="box-body">
                                <div id="barHorizontalMedical" style="height:523px"></div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                    </div>
                    <!-- /.col (RIGHT) -->
                </div>
            </div>

            <div id="lembur" class="tab-pane fade">
                <div class="col-md-offset-8 col-md-4" style="margin-bottom: 18px">
                    <div class="row">
                        <div id="lemburTahun" class="form-group col-md-6">
                            <select id="strFilterLemburTahun" class="form-control" onchange="filterTahunLembur()">
                                <option value="2018">2018</option>
                                <option value="2019">2019</option>
                                <option value="2020">2020</option>
                            </select>
                        </div>
                        <div id="lemburBulan" class="form-group col-md-6">
                            <select id="strFilterLemburBulan" onchange="filterTahunLembur()" class="form-control">
                                <option value="-">Semua Bulan</option>
                                <option value="1">Januari</option>
                                <option value="2">Februari</option>
                                <option value="3">Maret</option>
                                <option value="4">April</option>
                                <option value="5">Mei</option>
                                <option value="6">Juni</option>
                                <option value="7">Juli</option>
                                <option value="8">Agustus</option>
                                <option value="9">September</option>
                                <option value="10">Oktober</option>
                                <option value="11">Nopember</option>
                                <option value="12">Desember</option>

                            </select>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class=" col-md-6">
                        <div class="box box-success">
                            <div class="box-header with-border">
                                <h3 class="box-title" id="boxTitleLembur"></h3>
                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                    </button>
                                    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="chart">
                                    <div id="chartLembur" style="height:523px"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <!-- LINE CHART -->
                        <div class="chart">
                        </div>

                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title" id="boxTitleBarHorizontalLembur"></h3>

                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                    </button>
                                    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>


                            <div class="box-body">
                                <div id="barHorizontalLembur" style="height:523px"></div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                    </div>
                    <!-- /.col (RIGHT) -->
                </div>
            </div>
        </div>

        <!-- /.row -->

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

<script>
    $(function () {
        $(".select2").select2();
    });

    var myChart;

    function barChart(tahun, divisiId, warna){

        var dataa = [];
        var label = "";
        SppdAction.getSppdTahun(tahun, divisiId, function(listdata) {
            $.each(listdata, function (i, item){
                dataa.push(item.divisiId);
                label = item.divisiName ;
            });
        });

        var areaChartData = {
            labels: ["January", "February", "March", "April", "May", "June", "July", "Agustus", "September", "Oktober", "Nopember", "Desember"],
            datasets: [
                {
                    label: label,
                    backgroundColor: warna,
                    //borderColor: "#5af2de",
                    data: dataa
                }
            ]
        };

        //-------------
        //- BAR CHART -
        //-------------
        var ctx = document.getElementById('barChart').getContext('2d');
        if (window.myBar) {
            //alert('update');
            window.myBar.data = areaChartData;
            window.myBar.update();
        }else{
            window.myBar = new Chart(ctx, {
                type: 'bar',
                data: areaChartData,
                options: {
                    responsive: true,
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Bidang ' + label
                    },
                    plugins: {
                        labels: {
                            render: 'value'
                        }
                    },
                    scales: {
                        xAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    },

                }
            });
        }

    }

    function barChartBagian(tahun, bagianId, warna){

        var dataa = [];
        var label = "";
        SppdAction.getSppdTahunByBagian(tahun, bagianId, function(listdata) {
            $.each(listdata, function (i, item){
                dataa.push(item.sppdId);
                label = item.bagianName ;
            });
        });

        var areaChartData = {
            labels: ["January", "February", "March", "April", "May", "June", "July", "Agustus", "September", "Oktober", "Nopember", "Desember"],
            datasets: [
                {
                    label: label,
                    backgroundColor: warna,
                    data: dataa
                }
            ]
        };

        //-------------
        //- BAR CHART -
        //-------------
        var ctx = document.getElementById('barChart').getContext('2d');
        if (window.myBar) {
            //alert('update');
            window.myBar.data = areaChartData;
            window.myBar.update();
        }else{
            window.myBar = new Chart(ctx, {
                type: 'bar',
                data: areaChartData,
                options: {
                    responsive: true,
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Bagian ' + label
                    },
                    plugins: {
                        labels: {
                            render: 'value'
                        }
                    },
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    },

                }
            });
        }

    }

    var dynamicColors = function() {
        var r = Math.floor(Math.random() * 255);
        var g = Math.floor(Math.random() * 255);
        var b = Math.floor(Math.random() * 255);
        return "rgb(" + r + "," + g + "," + b + ")";
    };

    function chartDoughnutBagian(tahun, bulan){
        SppdAction.getSppdTahunBagian(tahun, bulan, function(listdata) {
            var dataDoughnut = [];
            var namaBagian = [];
            var idBagian = [];
            var coloR = [];
            $.each(listdata, function (i, item){
                dataDoughnut.push(item.sppdId);
                namaBagian.push(item.bagianName);
                idBagian.push(item.bagianId);
                coloR.push(dynamicColors());
            });

            var ctx = document.getElementById('chart-doughnut').getContext('2d');
            var canvasDoughnut = document.getElementById("chart-doughnut");

            var chartData = {
                labels: namaBagian,
                datasets: [{
                    data: dataDoughnut,
                    backgroundColor: coloR,
                    label: 'Dataset 1',
                }]
            };

            var config1 = {
                type: 'doughnut',
                data: {
                    datasets: [{
                        data: dataDoughnut,
                        backgroundColor: coloR,
                        label: 'Dataset 1'
                    }],
                    labels: namaBagian,
                    tmp:idBagian,
                    warna: coloR
                },
                options: {
                    responsive: true,
                    legend: {
                        display: false
                    },
                    plugins: {
                        labels: [
                            {
                                render: 'percentage',
                                fontSize: 15,
                                fontColor: '#fff'

                            }
                        ]
                    }
                }
            };

            if (window.myDoughnut) {
                //alert('update');
                window.myDoughnut.data = chartData;
                window.myDoughnut.update();
            }else{

                window.myDoughnut = new Chart(ctx, {
                    type: 'doughnut',
                    data: chartData,
                    tmp: idBagian,
                    warna: coloR,
                    options: {
                        responsive: true,
                        legend: {
                            display: false
                        },
                        plugins: {
                            labels: [
                                {
                                    render: 'percentage',
                                    fontSize: 15,
                                    fontColor: '#fff'
                                }
                            ]
                        }
                    }
                });
            }

            canvasDoughnut.onclick = function(evt) {
                var activePoints = myDoughnut.getElementsAtEvent(evt);
                if (activePoints[0]) {
                    var chartData = activePoints[0]['_chart'].config.data;
                    var dataBagian = activePoints[0]['_chart'].config;
                    var idx = activePoints[0]['_index'];

                    var label = chartData.labels[idx];
                    var bagianId = dataBagian.tmp[idx];
                    var warna = dataBagian.warna[idx];
                    var value = chartData.datasets[0].data[idx];

                    barChartBagian(tahun, bagianId, warna);
                    $('#modal-detail-divisi').find('.modal-title').text('Detail Bulan ' + label);
                    $('#modal-detail-divisi').modal('show');


                }
            };
        });
    }

    function hexToRgb(hex) {
        // Expand shorthand form (e.g. "03F") to full form (e.g. "0033FF")
        var shorthandRegex = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
        hex = hex.replace(shorthandRegex, function (m, r, g, b) {
            return r + r + g + g + b + b;
        });

        var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result ? {
            r: parseInt(result[1], 16),
            g: parseInt(result[2], 16),
            b: parseInt(result[3], 16)
        } : null;
    }

    // chart sunburst
    function chartSunburst(tahun, bulan){
        anychart.onDocumentReady(function () {

            var children = [];
            var jumlahKandir = 0;
            SppdAction.getSppdTahunByDivisi(tahun, bulan, function(listdata) {
                var namaDivisi = [];
                var idDivisi = [];
                $.each(listdata, function (i, item) {
                    var children2 = [];
                    SppdAction.getSppdTahunBagian(tahun, bulan, item.sppdId, function(listdataBagian) {
                        $.each(listdataBagian, function (ii, item2) {
                            children2.push({name: item2.bagianName, value: item2.sppdId});
                        });
                    });
                    children.push({name: item.divisiName, value: item.divisiId, children : children2});
                    //children.push(item.divisiId);
                    namaDivisi.push(item.divisiName);
                    idDivisi.push(item.sppdId);
                    jumlahKandir += Number(item.divisiId);
                });
            });

            var data1 = [
                {
                    name: "Kandir",
                    value: jumlahKandir,
                    children: children
                }
            ];

            if(window.sunBurstChart){
                // initiate drawing the chart
                window.sunBurstChart.noData();
                window.sunBurstChart.data(data1);

                window.sunBurstChart.listen('chartDraw', function (event) {
                    filterTahunTopSppd(printPath(window.sunBurstChart.getDrilldownPath()));
                });

                // set the calculation mode
                window.sunBurstChart.calculationMode("parent-dependent");

                // set the container id
                window.sunBurstChart.container("chart");
                window.sunBurstChart.draw();


            }else{
                // create a chart and set the data
                window.sunBurstChart = anychart.sunburst(data1, "as-tree");

                // set the calculation mode
                window.sunBurstChart.calculationMode("parent-dependent");
                window.sunBurstChart.top('1%');
                // set the container id
                window.sunBurstChart.container("chart");

                window.sunBurstChart.listen("pointClick", function(event){
//                    console.log(e.point.get("value") + " #" + e.point.getIndex() );
                    // Get point chart.
//                    var pointChart = event.point.getChart();
                    //console.log('You clicked on point ' + event.point.getIndex());
                });

                window.sunBurstChart.listen('chartDraw', function (event) {
                    filterTahunTopSppd(printPath(window.sunBurstChart.getDrilldownPath()));
                });

                // initiate drawing the chart
                window.sunBurstChart.draw();
            }


        });
    }

    // sun burst medical record
    function chartSunburstMedical(tahun, bulan){
        anychart.onDocumentReady(function () {
            var children = [];
            var jumlahKandir = 0;
            DashboardAbsensiAction.getDashboardMedical(tahun, bulan, function(listdata) {
                var namaDivisi = [];
                var idDivisi = [];
                $.each(listdata, function (i, item) {
                    var children2 = [];
                    DashboardAbsensiAction.getDashboardMedicalDetailDivisi(bulan, tahun, item.divisiId, function(listdataBagian) {
                        $.each(listdataBagian, function (ii, item2) {
                            children2.push({name: item2.bagianName, value: item2.jumlahPengobatan});
                        });
                    });
                    children.push({name: item.divisiName, value: item.jumlahPengobatan, children : children2});
                    //children.push(item.divisiId);
                    namaDivisi.push(item.divisiName);
                    idDivisi.push(item.divisiId);
                    jumlahKandir += Number(item.jumlahPengobatan);
                });
            });

            var data1 = [
                {
                    name: "Kandir",
                    value: jumlahKandir,
                    children: children
                }
            ];

            if(window.sunBurstChartMedical){
                // initiate drawing the chart
                window.sunBurstChartMedical.noData();
                window.sunBurstChartMedical.data(data1);

                window.sunBurstChartMedical.listen('chartDraw', function (event) {
                    filterTahunTopMedical(printPath(window.sunBurstChartMedical.getDrilldownPath()));
                });

                // set the calculation mode
                window.sunBurstChartMedical.calculationMode("parent-dependent");

                // set the container id
                window.sunBurstChartMedical.container("chartMedical");
                window.sunBurstChartMedical.draw();


            }else{
                // create a chart and set the data
                window.sunBurstChartMedical = anychart.sunburst(data1, "as-tree");

                // set the calculation mode
                window.sunBurstChartMedical.calculationMode("parent-dependent");
                window.sunBurstChartMedical.top('1%');

                // set the container id
                window.sunBurstChartMedical.container("chartMedical");
                window.sunBurstChartMedical.tooltip().useHtml(true);
                window.sunBurstChartMedical.tooltip().titleFormat("kakakaka");

                var currentLabels = window.sunBurstChartMedical.labels();
                var currentTooltip = window.sunBurstChartMedical.tooltip();
                // format the number
                currentLabels.format(function () {
                    return this.name + "\n Rp. " + anychart.format.number(this.value, 3, ",", ".");
                });

                window.sunBurstChartMedical.listen("click", function(e){
                    //console.log(e.point.get("name") + " #" + e.point.getIndex() );
                });

                window.sunBurstChartMedical.listen('chartDraw', function (event) {
                    filterTahunTopMedical(printPath(window.sunBurstChartMedical.getDrilldownPath()));
                });

                currentTooltip.format(function () {
                    return this.name + "\n\n Rp. " + anychart.format.number(this.value, 3, ",", ".")
                });
                //.titleFormat('{%X}');

                // initiate drawing the chart
                window.sunBurstChartMedical.draw();
            }


        });
    }

    // sun burst Lembur
    function chartSunburstLembur(tahun, bulan){
        anychart.onDocumentReady(function () {
            var children = [];
            var jumlahKandir = 0;
            DashboardAbsensiAction.getDashboardLembur(bulan, tahun, function(listdata) {
                var namaDivisi = [];
                var idDivisi = [];
                $.each(listdata, function (i, item) {
                    var children2 = [];
                    DashboardAbsensiAction.getDashboardLemburDetailDivisi(bulan, tahun, item.divisiId, function(listdataBagian) {
                        $.each(listdataBagian, function (ii, item2) {
                            children2.push({name: item2.bagianName, value: item2.jumlahLembur});
                        });
                    });
                    children.push({name: item.divisiName, value: item.jumlahLembur, children : children2});
                    //children.push(item.divisiId);
                    namaDivisi.push(item.divisiName);
                    idDivisi.push(item.divisiId);
                    jumlahKandir += Number(item.jumlahLembur);
                });
            });

            var data1 = [
                {
                    name: "Kandir",
                    value: jumlahKandir,
                    children: children
                }
            ];

            if(window.sunBurstChartLembur){
                // initiate drawing the chart
                window.sunBurstChartLembur.noData();
                window.sunBurstChartLembur.data(data1);

                // set the calculation mode
                window.sunBurstChartLembur.calculationMode("parent-dependent");

                window.sunBurstChartLembur.listen('chartDraw', function (event) {
                    filterTahunTopLembur(printPath(window.sunBurstChartLembur.getDrilldownPath()));
                });

                // set the container id
                window.sunBurstChartLembur.container("chartLembur");
                window.sunBurstChartLembur.draw();


            }else{
                // create a chart and set the data
                window.sunBurstChartLembur = anychart.sunburst(data1, "as-tree");

                // set the calculation mode
                window.sunBurstChartLembur.calculationMode("parent-dependent");
                window.sunBurstChartLembur.top('1%');

                window.sunBurstChartLembur.listen('chartDraw', function (event) {
                    filterTahunTopLembur(printPath(window.sunBurstChartLembur.getDrilldownPath()));
                });

                // set the container id
                window.sunBurstChartLembur.container("chartLembur");
                window.sunBurstChartLembur.tooltip().useHtml(true);

                var currentLabels = window.sunBurstChartLembur.labels();
                var currentTooltip = window.sunBurstChartLembur.tooltip();
                // format the number
                currentLabels.format(function () {
                    return this.name + "\nRp. " + anychart.format.number(this.value, 3, ",", ".")
                });

                currentTooltip.format(function () {
                    return this.name + "\n\nRp." + anychart.format.number(this.value, 3, ",", ".")
                });
                //.titleFormat('{%X}');

                // initiate drawing the chart
                window.sunBurstChartLembur.draw();
            }


        });
    }

    // untuk Dashboard Absensi
    function getDetail(nip, nama) {
        //$('#modal-detail-absensi').html('');
        var tahun = document.getElementById('strFilterAbsensiTahun').value;
        var bulan = document.getElementById('strFilterAbsensiBulan').value;

        $('.tableDetailAbsensi').find('tbody').remove();
        $('.tableDetailAbsensi').find('thead').remove();

        var tmp_table = "";
        var tmp_table2 = "<tbody>";
        DashboardAbsensiAction.getKaryawanTelat(bulan, tahun, nip, function (listdata) {
            tmp_table = "<thead style='font-size: 13px; color: white; white-space: nowrap' ><tr class='active'>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nip</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Nama</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Divisi</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Bagian</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jam Masuk</th>" +
                    "<th style='text-align: center; background-color:  #3c8dbc''>Lama Telat</th>" +
                    "</tr></thead><tbody>";
            var i = i;
            $.each(listdata, function (i, item) {

                var myDate = new Date(item.tanggalBerangkat);
                var myDate1 = new Date(item.tanggalKembali);

                tmp_table += '<tr style="font-size: 12px; white-space: nowrap">' +
                        '<td >' + (i + 1)+ '</td>' +
                        '<td >' + item.nip + '</td>' +
                        '<td align="center">' + item.nama + '</td>' +
                        '<td align="center">' + item.divisiName + '</td>' +
                        '<td align="center">' + item.bagianName+ '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +

                        '<td align="center">' + item.strTanggalMasuk + '</td>' +
                        '<td align="center">' + item.jamMasuk + '</td>' +
                        '<td align="center">' + item.jumlahTelat + '</td>' +

                        "</tr>";

                tmp_table2 += '<tr style="font-size: 12px; white-space: nowrap">' +
                        '<td >' + (i + 1)+ '</td>' +
                        '<td >' + item.nip + '</td>' +
                        '<td align="center">' + item.nama + '</td>' +
                        '<td align="center">' + item.divisiName + '</td>' +
                        '<td align="center">' + item.bagianName+ '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +

                        '<td align="center">' + item.strTanggalMasuk + '</td>' +
                        '<td align="center">' + item.jamMasuk + '</td>' +
                        '<td align="center">' + item.jumlahTelat + '</td>' +

                        "</tr>";
            });
            tmp_table += "</tbody>";

            $('.tableDetailAbsensi').append(tmp_table);


            if(window.myDataTable){
                //window.myDataTable = $("#tableDetailAbsensi").DataTable();
                window.myDataTable.destroy();
                window.myDataTable = $("#tableDetailAbsensi").DataTable();
                //window.myDataTable = $("#tableDetailAbsensi").dataTable({ "retrieve": true }).api();

//                window.myDataTable.row.add(tmp_table2).draw();
                //window.myDataTable.clear();
                //window.myDataTable.rows.add(NewlyCreatedData); // Add new data
                //window.myDataTable.columns.adjust().draw(); // Redraw the DataTable
            }else{
                window.myDataTable = $("#tableDetailAbsensi").DataTable();
            }

            $('#modal-detail-absensi').find('.modal-title').text('Detail Absensi ' + nama);
            $('#modal-detail-absensi').modal('show');
        });
    };

</script>
</body>

<div id="modal-detail-divisi" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <canvas id="barChart" style="height:250px"></canvas>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-detail-absensi" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 1100px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <table id="tableDetailAbsensi" class="tableDetailAbsensi table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</html>

