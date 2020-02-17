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
    <script type='text/javascript' src='<s:url value="/dwr/interface/TransaksiObatAction.js"/>'></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-blue layout-top-nav fixed">
<div class="wrapper">

    <header class="main-header">
        <nav class="navbar navbar-static-top" style="background-color: #30d196">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand"><b><i class="fa fa-bars"></i> DAFTAR ANTRIAN</b></a>
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
                    <div class="box-header with-border">
                        <h3 class="box-title">DAFTAR ANTRIAN PASIEN</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-4">

                            </div>
                            <div class="col-md-8">
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td>
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <img src="<s:url value="/pages/images/unknown-person.png"/>" class="user-image" alt="User Image">
                                                </div>
                                                <div class="col-md-9">
                                                    <p>MUHAMMAD SODIQ</p>
                                                    <p>PROBOLINGGO</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td></td>
                                        <td>PROBOLINGGO</td>
                                    </tr>
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

    });

    // function cekListObat() {
    //     setInterval(function () {
    //         TransaksiObatAction.getListResepPasien("RSP00000040", function (response) {
    //             console.log(response);
    //         });
    //     },100);
    // }
</script>
</body>
</html>
