<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>GO-MEDSYS NMU</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link rel="shortcut icon" href="<s:url value="/pages/images/logo-nmu.webp"/>"/>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">

    <!-- Vendor CSS Files -->
    <link href="../plugins/landing/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../plugins/landing/icofont/icofont.min.css" rel="stylesheet">
    <link href="../plugins/landing/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="../plugins/landing/remixicon/remixicon.css" rel="stylesheet">
    <link href="../plugins/landing/venobox/venobox.css" rel="stylesheet">
    <link href="../plugins/landing/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="../plugins/landing/aos/aos.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="../plugins/landing/css/style.css" rel="stylesheet">

    <style>

        #particles-js {
            position: absolute;
            width:  100%;
            background-color: #4f5962;
            background-repeat: no-repeat;
            background-size: 20%;
            background-position: 50% 50%;
        }

        .wrap-box01{
            margin-top: 10px;
            width:  100%;
            color: black;
            border-radius: 5px;
            text-align: left !important;
        }

        .wrap-box02{
            margin-top: 10px;
            width:  100%;
            color: black;
            border-radius: 5px;
            text-align: left !important;
        }

        .wrap-box01 ul {
            list-style: none;
        }

        .wrap-box01 .antrian .biru {
            width: 100%;
            margin-bottom: 2px;
            padding:5px;
            color: white;
            background-color: #4d4dff;
            border-radius: 5px;
        }

        .wrap-box01 .antrian .merah {
            width: 100%;
            margin-bottom: 2px;
            padding:5px;
            color: white;
            background-color: #d33724;
            border-radius: 5px;
        }

        .wrap-box02 ul {
            list-style: none;
        }

        .wrap-box02 .periksa .hijau {
            width: 100%;
            margin-bottom: 2px;
            padding:5px;
            background-color: #00a65a;
            border-radius: 5px;
            color: white;
        }

        .wrap-box02 .periksa .biru {
            width: 100%;
            margin-bottom: 2px;
            padding:5px;
            background-color: #4d4dff;
            border-radius: 5px;
            color: white;
        }

        .wrap-box02 .periksa .kuning {
            width: 100%;
            margin-bottom: 2px;
            padding:5px;
            background-color: #ffff00;
            border-radius: 5px;
            color: black;
        }

        .wrap-box02 .periksa .orange {
            width: 100%;
            margin-bottom: 2px;
            padding:5px;
            background-color: #f56954;
            border-radius: 5px;
            color: white;
        }

        .title-antrian{
            margin-top: -30px;
            color: black;
        }

    </style>
</head>

<body onload="startTime()">
<!-- ======= Header ======= -->
<header id="header" class="fixed-top">
    <div class="container">
        <div class="row justify-content-end">
            <div class="col-md-1 d-md-flex align-items-md-stretch">
                <img src="<s:url value="/pages/images/logo-nmu-copy.png"/>"
                     style="cursor: pointer; height: 40px; width: 60px; margin-top: -5px"/>
            </div>
            <div class="col-md-3 d-md-flex align-items-md-stretch">
                <div style="margin-top: 3px; margin-left: -30px; font-size: 12px">
                    <b>PT. NUSANTARA MEDIKA UTAMA</b><br>
                    <b id="nav_branch_id"></b>
                </div>
            </div>
            <div class="col-md-7 d-md-flex align-items-md-stretch">
                <div style="margin-top: 7px;">
                    <b id="nav_judul"></b>
                </div>
            </div>
            <div class="col-md-1 d-md-flex align-items-md-stretch">
                <span id="nav_logo_branch"></span>
                <i onclick="toggleFullScreen(document.body)"
                   style="cursor: pointer; margin-top: 9px; margin-left: 20px"
                   class="fa fa-expand" id="btn_full"></i>
            </div>
        </div>
    </div>
</header><!-- End Header -->

<main id="main">
    <div id="particles-js"></div>
    <section id="pricing" class="pricing">
        <div class="container">
            <div class="row">
                <div class="col-md-4" data-aos="zoom-im" data-aos-delay="100">
                    <div class="box">
                        <div class="title-antrian"><i class="fa fa-user-md"></i> PASIEN PERIKSA</div>
                        <div class="wrap-box02">
                            <div class="periksa">
                                <ul id="pasien_periksa">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="box" style="margin-top: 10px">
                        <div class="title-antrian"><i class="fa fa-user-md"></i> ANTRIAN APOTEK</div>
                        <div class="wrap-box02">
                            <div class="periksa">
                                <ul id="pasien_apotek">
                                </ul>
                            </div>
                            <div class="fixed-bottom" style="font-size: 12px !important; margin-left: 35px; margin-bottom: 10px">
                                <i class="fa fa-square" style="color: #4d4dff"></i> Antrian &nbsp;&nbsp;&nbsp;
                                <i class="fa fa-square" style="color: #00a65a"></i> Proses &nbsp;&nbsp;&nbsp;
                                <i class="fa fa-square" style="color: #f56954"></i> Antrian Racik
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8" data-aos="zoom-im" data-aos-delay="100">
                    <div class="box">
                        <div class="title-antrian">
                            <i class="fa fa-user"></i> ANTRIAN PASIEN
                            <i onclick="stopAntrian()"
                               style="cursor: pointer; margin-left: 7px"
                               class="fa fa-power-off"></i>
                            <span class="pull-right" style="margin-left: 7px"><i class="fa fa-clock-o"></i> <span id="txt"></span></span>
                            <span class="pull-right"><i class="fa fa-calendar-check-o"></i> <span id="tgl"></span></span>
                        </div>
                        <div class="wrap-box01">
                            <div class="antrian">
                                <ul id="antrian_pasien">
                                </ul>
                            </div>
                            <div class="fixed-bottom" style="font-size: 12px !important; margin-left: 35px; margin-bottom: 10px">
                                <i class="fa fa-square" style="color: #d33724"></i> Belum Bayar Uang Muka &nbsp;&nbsp;&nbsp;
                                <i class="fa fa-square" style="color: #4d4dff"></i> Antrian
                            </div>
                            <div class="tes-text-to-speech">Nomer Antrian 1</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>
</main>

<!-- ======= Footer ======= -->
<footer id="footer" class="fixed-bottom">
    <div class="mr-md-auto text-center text-md-left ">
        <div class="copyright" style="color: black">
            &copy; Copyright <strong><span>PT. Nusantara Medika Utama</span></strong>. All Rights Reserved
        </div>
    </div>
</footer><!-- End Footer -->
<div id="preloader"></div>

<!-- Vendor JS Files -->
<script src="../plugins/landing/jquery/jquery.min.js"></script>
<script src="../plugins/landing/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../plugins/landing/jquery.easing/jquery.easing.min.js"></script>
<script src="../plugins/landing/php-email-form/validate.js"></script>
<script src="../plugins/landing/waypoints/jquery.waypoints.min.js"></script>
<script src="../plugins/landing/counterup/counterup.min.js"></script>
<script src="../plugins/landing/venobox/venobox.min.js"></script>
<script src="../plugins/landing/owl.carousel/owl.carousel.min.js"></script>
<script src="../plugins/landing/isotope-layout/isotope.pkgd.min.js"></script>
<script src="../plugins/landing/aos/aos.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="../plugins/landing/particles/particles.min.js"></script>
<script src="../plugins/landing/particles/stats.min.js"></script>
<%--
<script src="../plugins/landing/jquery/jquery.min.js"></script>
<script src="../plugins/landing/texttospeech/articulate.min.js"></script>
<script src="../plugins/landing/texttospeech/jquery.webSpeaker.js"></script>
--%>
<script src="../plugins/landing/texttospeech/articulate.min.js"></script>
<script src="../plugins/landing/texttospeech/jquery.webSpeaker.js"></script>

<!-- Template Main JS File -->
<script src="../plugins/landing/js/main.js"></script>
<script type='text/javascript' src='<s:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>

<script>

    var intervalAntrian = "";
    var intervalPeriksa = "";
    var intervalApotek = "";

    $(document).ready(function () {
        cekListAntrian();
        $('.wrap-box01').css('height', window.screen.height * window.devicePixelRatio-240);
        $('.wrap-box02').css('height', ((window.screen.height * window.devicePixelRatio)/2)-170);
        $('#particles-js').css('height', window.screen.height * window.devicePixelRatio);

        // Sets the rate of the speaking voice
        // default = 1.1; range = [0.1 - 10]
        //$().articulate('rate', 1);

        // Sets the pitch of the speaking voice;
        // default = 1.0; range = [0 - 2]
        //$().articulate('pitch', 0);

        // Sets the volume of the speaking voice
        // default = 1.0;
        //$().articulate('volume', *number*);
        //$('.tes-text-to-speech').articulate('speak');

       responsiveVoice.speak("Nomor Antrian 1");
    });

    $(function () {
        particlesJS("particles-js", {
            particles: {
                number: { value: 160, density: { enable: true, value_area: 800 } },
                color: { value: "#ffffff" },
                shape: {
                    type: "circle",
                    stroke: { width: 0, color: "#000000" },
                    polygon: { nb_sides: 5 },
                    image: { src: "img/github.svg", width: 100, height: 100 }
                },
                opacity: {
                    value: 1,
                    random: true,
                    anim: { enable: true, speed: 1, opacity_min: 0, sync: false }
                },
                size: {
                    value: 3,
                    random: true,
                    anim: { enable: false, speed: 4, size_min: 0.3, sync: false }
                },
                line_linked: {
                    enable: false,
                    distance: 150,
                    color: "#ffffff",
                    opacity: 0.4,
                    width: 1
                },
                move: {
                    enable: true,
                    speed: 1,
                    direction: "none",
                    random: true,
                    straight: false,
                    out_mode: "out",
                    bounce: false,
                    attract: { enable: false, rotateX: 600, rotateY: 600 }
                }
            },
            retina_detect: true
        });
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

        setDaftar(branchId, poliId);
        setInterval(function () {
            setDaftar(branchId, poliId);
        },60000);
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
                $('#nav_logo_branch').html('<img border="0" class="hvr-grow" src="'+response.logoBranch+'"  style="cursor: pointer; height: 35px; width: 110px;" >');
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

    function setDaftar(branchId, poliId){
        var tableAntrian = "";
        var tablePeriksa = "";
        var tableApotek  = "";

        var poli = "";
        var lim = 15;
        var limit = 15;
        var count = 1;

        CheckupAction.getListAntriaPasien(branchId, poliId, function (response) {
            clearInterval(intervalAntrian);
            $('#antrian_pasien').html('');
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

                    var color = 'biru';
                    var classLbl = 'class="span-biru"';
                    if(item.belumBayarUangMuka == "Y"){
                        color = 'merah';
                        classLbl = 'class="span-danger"';
                    }

                    var cocolor = "";
                    if(item.noCheckupOnline != null){
                        cocolor = "#0F9E5E";
                    }

                    var divA = "";
                    var divB = "";
                    var divEnd = "";

                    if(i < limit){
                        if(i == 0){
                            divA = '<div id="slide_antrian_'+count+'" class="slide_show_antrian">';
                        }
                    }else{
                        count = count+1;
                        divA = '<div id="slide_antrian_'+count+'" style="display: none" class="slide_show_antrian">';
                        divB = '</div>';
                        limit = limit+lim;
                    }

                    if(i == response.length -1){
                        divEnd = '</div>';
                    }

                    var cutPol = pol;
                    if(pol.length > 25){
                        if(pol.split(' ')[0] != undefined){
                            cutPol = pol.substr((pol.split(' ')[0].length + 1), (pol.length - 1));
                        }
                    }
                    tableAntrian += divB+divA+'<li class="'+color+'">\n' +
                        '<div class="row">\n' +
                        '    <div class="col-md-4">\n' +
                        '<span>'+cutPol.toUpperCase()+'</span>' +
                        '    </div>\n' +
                        '    <div class="col-md-4">\n' +
                        '<span>'+item.nama.toUpperCase()+'</span>' +
                        '    </div>\n' +
                        '    <div class="col-md-3">\n' +
                        '<span>'+item.namaDesa.toUpperCase()+'</span>' +
                        '    </div>\n' +
                        '    <div class="col-md-1">\n' +
                        '        <span class="box-antrian" style="font-size: 15px; font-weight: bold; float: right;">'+item.stNoAntrian+'</span>\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '</li>'+divEnd;
                });
                $('#antrian_pasien').html(tableAntrian);
                startSlide();
            }else{
                $('#antrian_pasien').html("");
            }
        });

        var limitPeriksa = 5;
        var limitPer     = 5;
        var countPeriksa = 1;
        CheckupAction.getListPeriksaPasien(branchId, poliId, function (response) {
            clearInterval(intervalPeriksa);
            $('#pasien_periksa').html('');
            if(response.length > 0){
                $.each(response, function (i, item) {
                    var divA = "";
                    var divB = "";
                    var divEnd = "";

                    if(i < limitPeriksa){
                        if(i == 0){
                            divA = '<div id="slide_periksa_'+countPeriksa+'" class="slide_show_periksa">';
                        }
                    }else{
                        countPeriksa = countPeriksa+1;
                        divA = '<div id="slide_periksa_'+countPeriksa+'" style="display: none" class="slide_show_periksa">';
                        divB = '</div>';
                        limitPeriksa = limitPeriksa+limitPer;
                    }

                    if(i == response.length -1){
                        divEnd = '</div>';
                    }

                    tablePeriksa += divB+divA+'<li class="hijau">\n' +
                        '<div class="row">\n' +
                        '    <div class="col-md-6">\n' +
                        '<span>'+item.namaPelayanan.toUpperCase()+'</span>' +
                        '    </div>\n' +
                        '    <div class="col-md-6">\n' +
                        '<span>'+item.nama.toUpperCase()+'</span>' +
                        '    </div>\n' +
                        '</div>\n' +
                        '</li>'+divEnd;
                });
                $('#pasien_periksa').html(tablePeriksa);
                startSlide();
            }else{
                $('#pasien_periksa').html("");
            }
        });

        var limitApotek = 5;
        var limitAp     = 5;
        var countApotek = 1;
        CheckupAction.getListAntrianApotikPeriksa(branchId, poliId, function (response) {
            clearInterval(intervalApotek);
            $('#pasien_apotek').html('');
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

                    var classLabel = 'biru';
                    if(item.statusPeriksaName == "Proses"){
                        classLabel = 'hijau';
                    }else{
                        if(item.ketRacik != null){
                            classLabel = 'orange';
                        }
                    }

                    var divA = "";
                    var divB = "";
                    var divEnd = "";

                    if(i < limitApotek){
                        if(i == 0){
                            divA = '<div id="slide_apotek_'+countApotek+'" class="slide_show_apotek">';
                        }
                    }else{
                        countApotek = countApotek+1;
                        divA = '<div id="slide_apotek_'+countApotek+'" style="display: none" class="slide_show_apotek">';
                        divB = '</div>';
                        limitApotek = limitApotek+limitAp;
                    }

                    if(i == response.length -1){
                        divEnd = '</div>';
                    }

                    tableApotek += divB+divA+'<li class="'+classLabel+'">\n' +
                        '<div class="row">\n' +
                        '    <div class="col-md-5">\n' +
                        '<span>'+pol.toUpperCase()+'</span>' +
                        '    </div>\n' +
                        '    <div class="col-md-7">\n' +
                        '<span>'+item.nama.toUpperCase()+'</span>' +
                        '    </div>\n' +
                        '</div>\n' +
                        '</li>'+divEnd;
                });
                $('#pasien_apotek').html(tableApotek);
                startSlide();
            }else{
                $('#pasien_apotek').html("");
            }
        });
    }

    function startSlide(){
        var slideI = $('.slide_show_antrian');
        var slideJ = $('.slide_show_periksa');
        var slideK = $('.slide_show_apotek');
        var i = 1;//antrian
        var j = 1;//periksa
        var k = 1;//apotek

        if(slideI.length > 0){
            if(slideI.length == 1){
                clearInterval(intervalAntrian);
            }else{
                intervalPeriksa = setInterval(function () {
                    $('#slide_antrian_'+i).hide("slide", { direction: "left" }, 300);
                    i++;
                    setTimeout(function () {
                        $('#slide_antrian_'+i).show("slide", { direction: "right" }, 300);
                    },300);
                    if(i == slideK.length){
                        i = 1;
                    }
                },25000);
            }
        }else{
            clearInterval(intervalAntrian);
        }

        if(slideJ.length > 0){
            if(slideJ.length == 1){
                clearInterval(intervalPeriksa);
            }else{
                intervalPeriksa = setInterval(function () {
                    $('#slide_periksa_'+j).hide("slide", { direction: "left" }, 300);
                    j++;
                    setTimeout(function () {
                        $('#slide_periksa_'+j).show("slide", { direction: "right" }, 300);
                    },300);
                    if(j == slideK.length){
                        j = 1;
                    }
                },25000);
            }
        }else{
            clearInterval(intervalPeriksa);
        }

        if(slideK.length > 0){
            if(slideK.length == 1){
                clearInterval(intervalApotek);
            }else{
                intervalPeriksa = setInterval(function () {
                    $('#slide_apotek_'+k).hide("slide", { direction: "left" }, 300);
                    k++;
                    setTimeout(function () {
                        $('#slide_apotek_'+k).show("slide", { direction: "right" }, 300);
                    },300);
                    if(k == slideK.length){
                        k = 1;
                    }
                },25000);
            }
        }else{
            clearInterval(intervalApotek);
        }
    }

    function stopAntrian(){
        localStorage.clear();
        window.location.href = 'choseeAntrian.jsp';
    }

</script>

</body>

</html>
