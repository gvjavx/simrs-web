<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>GO-MEDSYS NMU</title>
    <link rel="shortcut icon" href="<s:url value="/pages/images/logo-nmu.webp"/>"/>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="../bootstraplte/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../dist/css/skins/skin-blue.min.css">

    <script type='text/javascript' src='<s:url value="/dwr/engine.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        .spin {
            -webkit-animation: rotation 1s infinite linear;
        }

        @-webkit-keyframes rotation {
            from {
                -webkit-transform: rotate(0deg);
            }
            to {
                -webkit-transform: rotate(359deg);
            }
        }
        .span-success{
            font-size: 13px;
            padding: 5px;
            color: white;
            background-color: #0F9E5E;
            border-radius: 5px;
            box-shadow: 1px 3px 8px grey
        }
        .span-warning{
            font-size: 13px;
            padding: 5px;
            color: white;
            background-color: darkorange;
            border-radius: 5px;
            box-shadow: 1px 3px 8px grey
        }
        .span-danger{
            font-size: 13px;
            padding: 5px;
            color: white;
            background-color: #d33724;
            border-radius: 5px;
            box-shadow: 1px 3px 8px grey
        }
        .span-primary{
            font-size: 13px;
            padding: 5px;
            color: white;
            background-color: #367fa9;
            border-radius: 5px;
            box-shadow: 1px 3px 8px grey
        }
        .span-biru{
            font-size: 13px;
            padding: 5px;
            color: white;
            background-color: #4d4dff;
            border-radius: 5px;
            box-shadow: 1px 3px 8px grey
        }
        .span-hijau-muda{
            font-size: 13px;
            padding: 5px;
            color: black;
            background-color: #66ff33;
            border-radius: 5px;
            box-shadow: 1px 3px 8px grey
        }
        .span-ungu{
            font-size: 13px;
            padding: 5px;
            color: white;
            background-color: #cc3399;
            border-radius: 5px;
            box-shadow: 1px 3px 8px grey
        }
        .span-kuning{
            font-size: 13px;
            padding: 5px;
            color: black;
            background-color: #ffff00;
            border-radius: 5px;
            box-shadow: 1px 3px 8px grey
        }
        .span-orange{
            font-size: 13px;
            padding: 5px;
            color: white;
            background-color: #f56954;
            border-radius: 5px;
            box-shadow: 1px 3px 8px grey
        }
        html, body {
            padding: 0;
            margin: 0;
        }

        *:fullscreen
        *:-ms-fullscreen,
        *:-webkit-full-screen,
        *:-moz-full-screen {
            overflow: auto !important;
        }
    </style>

</head>

<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-blue layout-top-nav fixed" onload="startTime()">
<div class="wrapper">

    <header class="main-header">
        <nav class="navbar navbar-static-top" style="background-color: #30d196">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand">
                        <img border="0" class="hvr-grow" src="<s:url value="/pages/images/sayap-logo-nmu.png"/>" style="cursor: pointer; height: 48px; width: 55px; margin-top: -15px">
                        <img border="0" class="hvr-grow" src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>" style="cursor: pointer; height: 23px; width: 23px; margin-top: -30px; margin-left: 28px">
                    </a>
                </div>
                <div class="collapse navbar-collapse pull-left">
                    <ul class="nav navbar-nav">
                        <li style="color: white; margin-top: 6px; margin-left: -10px; padding-bottom: -2px">
                            <span> PT. NUSANTARA MEDIKA UTAMA</span>
                            <br>
                            <span id="nav_branch_id"></span>
                        </li>
                        <li style="margin-left: 50px; color: white;">
                            <h3 id="nav_judul"></h3>
                        </li>
                    </ul>
                </div>
                <div class="collapse navbar-collapse pull-right">
                    <ul class="nav navbar-nav">
                        <li>
                            <span id="nav_logo_branch"></span>
                        </li>
                        <li>
                            <i onclick="toggleFullScreen(document.body)" style="cursor: pointer; margin-top: 20px; margin-left: 20px" class="fa fa-expand" id="btn_full"></i>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- /.container-fluid -->
        </nav>
    </header>
    <!-- Full Width Column -->
    <div class="content-wrapper">
        <div class="container">
            <!-- Main content -->
            <section class="content">
                <div class="box box-success" style="border-top-color: #50d4a3">
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-5">
                                <div class="box-header with-border">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <h5 class="box-title"><i class="fa fa-user-md"></i> PASIEN PERIKSA</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="box-header with-border"></div>
                                <table class="table">
                                    <tbody id="body_periksa">
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-7">
                                <div class="box-header with-border">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h5 class="box-title"><i class="fa fa-user"></i> ANTRIAN PASIEN</h5>
                                        </div>
                                        <div class="col-md-6">
                                            <span class="pull-right"><i class="fa fa-clock-o"></i> <span id="txt"></span></span>
                                            <span class="pull-right" style="margin-right: 10px"><i class="fa fa-calendar-check-o"></i> <span id="tgl"></span></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="box-header with-border"></div>
                                <table class="table">
                                    <tbody id="body_antrian">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-7">
                                <div class="box-header with-border">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <h5 class="box-title"><i class="fa fa-user"></i> ANTRIAN APOTEK</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="box-header"></div>
                                <table class="table">
                                    <tbody id="body_antrian_apotek">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </section>
            <!-- /.content -->
        </div>
        <!-- /.container -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            GO-MEDSYS NMU
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2019 <a href="#" style="color: #50d4a3">PT. Nusantara Medika Utama</a>.</strong> All rights reserved.
    </footer>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="../bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../dist/js/demo.js"></script>

<script>

    $(document).ready(function () {
        cekListAntrian();
    });

    function setLocalStorege(key, value){
        localStorage.setItem(key, value);
    }

    function getLocalStorage(key){
        return localStorage.getItem(key);
    }

    function cekListAntrian() {
        var urlString = window.location;
        var url = new URL(urlString);
        var branch = getLocalStorage("branchId");
        var poli = getLocalStorage("poli");

        var branchId = "";
        var poliId = "";
        var pol = [];

        if(branch != 'null'){
            branchId = branch;
        }

        if(poli != 'null'){
            var inPoli = "";
            pol = poli.split(",");

            $.each(pol, function (i, item) {
                if(i == 0){
                    inPoli = "'"+item+"'";
                }else{
                    inPoli = inPoli + ","+ "'"+item+"'";
                }
            });

            poliId = inPoli;
        }

        detailBranch(branchId);

        setInterval(function () {
            var tableAntrian = "";
            var poli = "";

            CheckupAction.getListAntriaPasien(branchId, poliId, function (response) {
                if(response.length > 0){
                    var pol = "";
                    $.each(response, function (i, item) {

                        if(i == 0){

                            pol = item.namaPelayanan;

                        }else{
                            var tes = item.namaPelayanan;
                            var tes2 = response[i - 1]["namaPelayanan"];

                            if(tes == tes2){
                                pol = "";
                            }else{
                                pol = item.namaPelayanan;
                            }
                        }

                        var color = "";
                        var classLbl = 'class="span-biru"';
                        if(item.belumBayarUangMuka == "Y"){
                            color = 'style="color:red"';
                            classLbl = 'class="span-danger"';
                        }

                        var cocolor = "";
                        if(item.noCheckupOnline != null){
                            cocolor = "#0F9E5E";
                        }

                        tableAntrian += '<tr '+color+'>' +
                            '<td>'+pol.toUpperCase()+'</td>'+
                            '<td><i class="fa fa-user" style="color: '+cocolor+'"></i> '+item.nama.toUpperCase()+'</td>'+
                            '<td>'+item.namaDesa.toUpperCase()+'</td>'+
                            '<td style="vertical-align: middle"><span '+classLbl+'> '+item.stNoAntrian+'</span></td>' +
                            '</tr>';
                    });


                    $('#body_antrian').html(tableAntrian);
                }else{
                    $('#body_antrian').html("");
                }
            });

            var tablePeriksa = "";
            CheckupAction.getListPeriksaPasien(branchId, poliId, function (response) {
                if(response.length > 0){
                    $.each(response, function (i, item) {
                        tablePeriksa += '<tr>' +
                            '<td>'+item.namaPelayanan.toUpperCase()+'</td>'+
                            '<td><i class="fa fa-user"></i> '+item.nama.toUpperCase()+'</td>'+
                            '<td style="vertical-align: middle"><span class="span-success"> Periksa</span></td>' +
                            '</tr>';
                    });
                    $('#body_periksa').html(tablePeriksa);
                }else{
                    $('#body_periksa').html("");
                }
            });
            var tableApotek = "";
            CheckupAction.getListAntrianApotikPeriksa(branchId, poliId, function (response) {
                if(response.length > 0){
                    $.each(response, function (i, item) {

                        var pol = "";

                        if(i == 0){

                            pol = item.namaPelayanan;

                        }else{
                            var tes = item.namaPelayanan;
                            var tes2 = response[i - 1]["namaPelayanan"];

                            if(tes == tes2){
                                pol = "";
                            }else{
                                pol = item.namaPelayanan;
                            }
                        }
                        var classLabel = 'class="span-warning"';
                        if(item.statusPeriksaName == "Proses"){
                            classLabel = 'class="span-success"';
                        }
                        tableApotek += '<tr>' +
                            '<td>'+pol.toUpperCase()+'</td>'+
                            '<td><i class="fa fa-user"></i> '+item.nama.toUpperCase()+'<br>'+'<small style="margin-left: 15px">'+item.idPermintaanResep+'</small></td>'+
                            '<td style="vertical-align: middle"><span '+classLabel+' style="margin: 10px"> '+item.statusPeriksaName+ '</span>'+ isRacik(item.ketRacik) +'</td>' +
                            '</tr>';
                    });
                    $('#body_antrian_apotek').html(tableApotek);
                }else{
                    $('#body_antrian_apotek').html("");
                }
            });
        },5000);
    }

    function isRacik(racik) {
        if(racik != null){
            return '<span class="span-warning" style="margin: 10px">'+racik+'</span>';
        } else {
            return '';
        }
    }

    function detailBranch(branch) {
        CheckupAction.getDataBranch(branch, function (response) {
            if(response != null){
                $('#nav_branch_id').text(response.branchName.toUpperCase());
                $('#nav_judul').text("DAFTAR ANTRIAN PASIEN " +response.branchName.toUpperCase()+ " "+response.branchAddress.toUpperCase())
                $('#nav_logo_branch').html('<img border="0" class="hvr-grow" src="'+response.logoBranch+'" style="cursor: pointer; height: 35px; width: 110px; margin-top: 9px">');
            }
        });
    }

    function startTime() {
        var today = new Date();
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();
        m = checkTime(m);
        s = checkTime(s);
        document.getElementById('txt').innerHTML =
            h + ":" + m + ":" + s;
        var t = setTimeout(startTime, 500);

        initDate();
    }

    function checkTime(i) {
        if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
        return i;
    }

    function initDate() {
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        today = dd + '-' + mm + '-' + yyyy;
        $('#tgl').html(today);
        return today;
    }

    function toggleFullScreen(elem) {
        if ((document.fullScreenElement !== undefined && document.fullScreenElement === null) || (document.msFullscreenElement !== undefined && document.msFullscreenElement === null) || (document.mozFullScreen !== undefined && !document.mozFullScreen) || (document.webkitIsFullScreen !== undefined && !document.webkitIsFullScreen)) {
            if (elem.requestFullScreen) {
                elem.requestFullScreen();
            } else if (elem.mozRequestFullScreen) {
                elem.mozRequestFullScreen();
            } else if (elem.webkitRequestFullScreen) {
                elem.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
            } else if (elem.msRequestFullscreen) {
                elem.msRequestFullscreen();
            }
            $('#btn_full').removeClass("fa fa-expand").addClass("fa fa-compress");
        } else {
            if (document.cancelFullScreen) {
                document.cancelFullScreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if (document.webkitCancelFullScreen) {
                document.webkitCancelFullScreen();
            } else if (document.msExitFullscreen) {
                document.msExitFullscreen();
            }
            $('#btn_full').removeClass("fa fa-compress").addClass("fa fa-expand");
        }
    }

</script>
</body>
</html>
