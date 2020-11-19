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

    <link rel="stylesheet" href="../bootstraplte/css/select2.css">
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

        .btn-trans {
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
            color: #fff;
        }

        .btn-green {
            background-color: green;
        }

        .btn-red {
            background-color: red;
        }

        .btn-yellow {
            background-color: yellow;
        }

        .btn-orange {
            background-color: orange;
        }

        .btn-default {
            background-color: green;
            color: #fff;
        }

        .btn-transparent {
            background-color: transparent;
            color: #fff;
        }

        .btn-white:hover {
            color: #fff;
        }

        .btn-trans:active {
            background-color: #2caaea;
        }

        .btn-trans:visited {
            background-color: #2caaea;
        }

        .form-control {
            margin-top: 7px;
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
                        <img border="0" class="hvr-grow" src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                             style="cursor: pointer; height: 48px; width: 55px; margin-top: -15px">
                        <img border="0" class="hvr-grow" src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                             style="cursor: pointer; height: 23px; width: 23px; margin-top: -30px; margin-left: 28px">
                    </a>
                </div>
                <div class="collapse navbar-collapse pull-left">
                    <ul class="nav navbar-nav">
                        <li style="color: white; margin-top: 20px; margin-left: -10px; padding-bottom: -2px">
                            <span> PT. NUSANTARA MEDIKA UTAMA</span>
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
                            <div class="col-md-12">
                                <div class="box-header with-border">
                                    <h5 class="box-title"><i class="fa fa-home"></i> Pilih Unit Rumah Sakit</h5>
                                </div>
                                <div class="box-header with-border"></div>
                                <div class="row" style="padding-bottom: 100px">
                                    <div class="col-md-offset-3 col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-3" style="margin-top: 10px">Area</label>
                                            <div class="col-md-9">
                                                <select class="form-control select2" id="area" onchange="optionBranch(this.value)"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3" style="margin-top: 10px">Unit RS</label>
                                            <div class="col-md-9">
                                                <select class="form-control select2" id="branch" onchange="optionPoli(this.value)"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3" style="margin-top: 10px">Pelayanan</label>
                                            <div class="col-md-9">
                                                <select class="form-control select2" multiple id="poli"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button onclick="startAntrian()" class="btn btn-success" style="margin-top: 7px"><i class="fa fa-arrow-right"></i> Start</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
        <strong>Copyright &copy; 2019 <a href="#" style="color: #50d4a3">PT. Nusantara Medika Utama</a>.</strong> All
        rights reserved.
    </footer>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>

<script src="../plugins/select2/select2.full.js"></script>
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

    $(function () {
        $('.select2').select2({});
    });

    $(document).ready(function () {
        optionArea();
    });

    function optionArea(){
        var option = '<option value="">[Select One]</option>';
        CheckupAction.getListComboArea(function (response) {
           if(response.length > 0){
               $.each(response, function (i, item) {
                   option += '<option value="'+item.areaId+'">'+item.areaName+'</option>';
               });

               $('#area').html(option);
           }else{
               $('#area').html(option);
           }
        });
    }

    function optionBranch(areaId){
        var option = '<option value="">[Select One]</option>';
        CheckupAction.getListComboBranch(areaId, function (response) {
            if(response.length > 0){
                $.each(response, function (i, item) {
                    option += '<option value="'+item.branchId+'">'+item.branchName+'</option>';
                });

                $('#branch').html(option);
            }else{
                $('#branch').html(option);
            }
        });
    }

    function optionPoli(branchId){
        var option = '';
        CheckupAction.getListComboPelayanan(branchId, function (response) {
            if(response.length > 0){
                $.each(response, function (i, item) {
                    option += '<option value="'+item.idPelayanan+'">'+item.namaPelayanan+'</option>';
                });

                $('#poli').html(option);
            }else{
                $('#poli').html(option);
            }
        });
    }

    function startAntrian(){
        var branchId = $('#branch').val();
        var poli = $('#poli').val();

        if(branchId != '' && branchId != null){
            setLocalStorege("branchId", branchId);
            setLocalStorege("poli", poli);
            // window.location.href = 'viewAntrian.jsp?branch='+branchId+'&poli='+poli;
            window.location.href = 'viewAntrian.jsp';
        }else{
            alert('Silahkan pilih RS unit dan pelayanan terlebih dahulu...!');
        }
    }

    function setLocalStorege(key, value){
        localStorage.setItem(key, value);
    }

    function getLocalStorage(key){
        return localStorage.getItem(key);
    }
</script>
</body>
</html>
