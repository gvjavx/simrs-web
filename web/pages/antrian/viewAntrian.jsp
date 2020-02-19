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
    <title>GO-HEALTH</title>
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
        .btn-trans{
            background-color: #404040;
            width: 100px;
            height: 130px;
            border-radius: 10px;
            opacity: 0.9;
            /*padding-right: 20px;
            padding-left: 20px;*/
            padding: 6px;
            float: left;
            margin: 5px;
            border: 1px solid #f7f7f7;
            font-size: 12px;
            text-align: center;
            color : #fff;
        }

        .btn-green{
            background-color: green;
        }
        .btn-red{
            background-color: red;
        }
        .btn-yellow{
            background-color: yellow;
        }
        .btn-orange{
            background-color: orange;
        }
        .btn-default{
            background-color: green;
            color:#fff;
        }
        .btn-transparent{
            background-color: transparent;
            color:#fff;
        }

        .btn-white:hover{
            color:#fff;
        }

        .btn-trans:active{
            background-color: #2caaea;
        }

        .btn-trans:visited{
            background-color: #2caaea;
        }
    </style>

</head>

<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-blue layout-top-nav fixed">
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
                            <span id="nav_branch_id"></span><br>
                            <span> PT. NUSANTARA MEDIKA UTAMA</span>
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
                                    <h5 class="box-title"><i class="fa fa-user-md"></i> PASIEN PERIKSA</h5>
                                </div>
                                <div class="box-header with-border"></div>
                                <table class="table table-striped">
                                    <tbody id="body_periksa">
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-7">
                                <div class="box-header with-border">
                                    <h5 class="box-title"><i class="fa fa-user"></i> ANTRIAN PASIEN</h5>
                                </div>
                                <div class="box-header with-border"></div>
                                <table class="table table-striped">
                                    <tbody id="body_antrian">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </div>
                <%--<div class="box box-default">--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-12" style="display:inline;">--%>
                            <%--<div class="btn-wrapper">--%>
                                <%--<div id="id__box" class="btn-trans btn-white" data-target="#detailalat" data-toggle="modal"   >--%>
                                    <%--<b>Mawar</b>--%>
                                    <%--<i id="icon_" class="icon-volume fa" >&nbsp;</i>--%>
                                    <%--<div title="Klik untuk info detail" onClick="cek_event('')" style="text-align:left; cursor:pointer; font-size:11px; border-top:solid 2px #fff;">--%>
                                        <%--<table align="center" id="id_"--%>
                                               <%--style="width:80px; border-radius:5px; margin-top:2px;">--%>
                                            <%--<td id="id__dc" colspan="2" align="center" style="border:solid 0px red; " >--%>
                                                <%--<img style="background-color:transparent; height:65px;" class="" src="<s:url value="/pages/images/logo-poli.png"/>">--%>
                                            <%--</td>--%>
                                            <%--<tr class="hiddenx">--%>
                                                <%--<td colspan="2" style="border:solid 0px #fff;">--%>
                                                    <%--<center>&nbsp;<b id="id__s"></b></center>--%>
                                                <%--</td>--%>
                                            <%--</tr>--%>
                                            <%--<tr class="hidden" style="display: none;">--%>
                                                <%--<td>&nbsp;</td>--%>
                                                <%--<td> : <b id="id__i">0</b></td>--%>
                                            <%--</tr>--%>
                                            <%--<tr class="hidden" style="display: none;">--%>
                                                <%--<td>&nbsp;</td>--%>
                                                <%--<td> : <b id="id__v">0</b></td>--%>
                                            <%--</tr>--%>
                                        <%--</table>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
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
            GO-HEALTH
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

    function cekListAntrian() {
        var urlString = window.location;
        var url = new URL(urlString);
        var branch = url.searchParams.get("branch");
        var poli = url.searchParams.get("poli");


        var branchId = null;
        var poliId = null;

        if(branch != ''){
            branchId = branch;
        }

        if(poli != ''){
            poliId = poli;
        }

        detailBranch(branchId);

        setInterval(function () {
            var tableAntrian = "";
            CheckupAction.getListAntriaPasien(branchId, poliId, function (response) {
                if(response.length > 0){

                    $.each(response, function (i, item) {
                        tableAntrian += '<tr>' +
                            '<td>'+item.namaPelayanan.toUpperCase()+'</td>'+
                            '<td><i class="fa fa-user"></i> '+item.nama.toUpperCase()+'</td>'+
                            '<td>'+item.namaDesa.toUpperCase()+'</td>'+
                            '<td style="vertical-align: middle"><label class="label label-warning"> Selanjutnya</label></td>' +
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
                            '<td style="vertical-align: middle"><label class="label label-success"> Periksa</label></td>' +
                            '</tr>';
                    });
                    $('#body_periksa').html(tablePeriksa);
                }else{
                    $('#body_periksa').html("");
                }
            });
        },1000);
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

</script>
</body>
</html>
