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
    <script type='text/javascript' src='<s:url value="/dwr/interface/DashboardAbsensiAction.js"/>'></script>

    <style>
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

            var d = new Date();
            $('#strFilterTahun').val(d.getFullYear()).change;

            window.filterTahunAbsensi = function(){
                var tahun = document.getElementById('strFilterTahun').value;
                var bulan = document.getElementById('strFilterBulan').value;

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
        <h1>
            Dashboard Absensi
            <small>e-HEALTH</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Charts</a></li>
            <li class="active">ChartJS</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">

            <!-- /.col (LEFT) -->

            <div class="col-md-12">

                <div class="tab-content well">
                    <div id="absensi" class="tab-pane fade in active">
                        <!-- PRODUCT LIST -->
                        <div class="box box-primary">
                            <div class="box-header with-border">
                                <h3 align="center" class="box-title">Daftar karyawan telat pada bulan Mei</h3>
                                <div class="row">
                                    <br>
                                    <br>
                                    <div class="col-md-offset-6 col-md-2">
                                        <select id="strFilterTahun" class="form-control" onchange="filterTahunAbsensi()">
                                            <option value="-">Tahun</option>
                                            <option value="2018">2018</option>
                                            <option value="2019">2019</option>
                                            <option value="2020">2020</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <select id="strFilterBulan" onchange="filterTahunAbsensi()" class="form-control">
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
                                <a href="javascript:void(0)" class="uppercase">View All</a>
                            </div>
                            <!-- /.box-footer -->
                        </div>
                        <!-- /.box -->
                    </div>
                    <div id="sppd" class="tab-pane fade ">
                        <label>
                            <label>SPPD</label>
                        </label>
                    </div>
                    <div id="medicalRecords" class="tab-pane fade ">
                        <label>Medical </label>
                    </div>
                </div>

            </div>
            <!-- /.col (RIGHT) -->
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
    function getDetail(nip, nama) {
        //$('#modal-detail-absensi').html('');
        var tahun = document.getElementById('strFilterTahun').value;
        var bulan = document.getElementById('strFilterBulan').value;

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

<div id="modal-detail-absensi" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 1000px">

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

