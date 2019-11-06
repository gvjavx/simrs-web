<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<script type='text/javascript' src='<s:url value="/dwr/interface/NotifikasiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/NotifAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
<script type="text/javascript">
    function readNotif(id){
        var notifId = id;
        if(notifId != null){
            dwr.engine.setAsync(false);
            NotifikasiAction.readNotification(notifId, function(message){
                if (message == "00"){
                    loadNotif();
                } else {
                    alert("error");
                }
            });
        } else {
            alert("data id tidak ditemukan");
        }
    }

    function loadNotif(){
        var tmp_data_approve = "";
        var tmp_data_pemberitahuan = "";
        var tmp_jml_pemberitahuan = "";
        var tmp_jml_approve = "";
        var data = [];
        dwr.engine.setAsync(false);
        NotifikasiAction.searchNotif(function(listData){
            data = listData;
            $.each(data, function(i, item){
                if(tmp_jml_pemberitahuan ==""){
                    if (item.jmlPemberitahuan!=null){
                        tmp_jml_pemberitahuan = item.jmlPemberitahuan;
                    }
                }
                if(tmp_jml_approve == ""){
                    tmp_jml_approve = item.jmlApproval;
                }
                if (item.tipeNotifId == "TN23"){
                    tmp_data_approve += "<li>"+
                            "<a href='/hris/notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Training</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                } else if (item.typeNotif == "TN22"){
                    tmp_data_approve += "<li>"+
                            "<a href='/hris/notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-warning'>Medical Record</span> "+item.note+""+
                            "</a>"+
                            "</li>";

                } else if (item.tipeNotifId == "TI"){
                    tmp_data_approve += "<li>"+
                            "<a href='/hris/notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>SPPD</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }else if (item.tipeNotifId == "TN33"){
                    tmp_data_approve += "<li>"+
                        "<a href='/hris/notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                        "<span class='label label-success'>Absensi</span> "+item.note+""+
                        "</a>"+
                        "</li>";
                }else if (item.tipeNotifId == "TN44"){
                    tmp_data_approve += "<li>"+
                        "<a href='/hris/notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                        "<span class='label label-success'>Indisipliner</span> "+item.note+""+
                        "</a>"+
                        "</li>";
                }else if (item.tipeNotifId == "TN55"){
                    tmp_data_approve += "<li>"+
                            "<a href='/hris/notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Dispensasi</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }
                else if (item.tipeNotifId == "TN66"){
                    tmp_data_approve += "<li>"+
                            "<a href='/hris/notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Cuti Pegawai</span> "+item.note+""+
                            "</a>"+
                            "</a>"+
                            "</a>"+
                            "</li>";
                }else if (item.tipeNotifId == "TN77"){
                    tmp_data_approve += "<li>"+
                            "<a href='/hris/notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Lembur</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }else if (item.tipeNotifId == "TN88"){
                    tmp_data_approve += "<li>"+
                            "<a href='/hris/notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Ijin Keluar Kantor</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }else if (item.tipeNotifId == "TN99"){
                    tmp_data_approve += "<li>"+
                            "<a href='/hris/notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Rekruitmen Pabrik</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }
                else {
                    tmp_data_pemberitahuan += "<li class='pemberitahuan' data-id='"+item.notifId+"~"+item.note+"'>"+
                            "<a>"+
                            "<span class='label label-default'>Pemberitahuan</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }
            })
        });
        $("#inner").html(tmp_data_approve);
        $("#inner2").html(tmp_data_pemberitahuan);
        $("#count1").html(tmp_jml_approve);
        $("#count2").html(tmp_jml_pemberitahuan);
        $("#count").html(tmp_jml_approve);
        $("#count3").html(tmp_jml_pemberitahuan);
    }
    function loadUser () {
        var data="";
        NotifikasiAction.searchUser(function(data){
            if (data=="ADMIN"){
                $(".orangPensiun").show();
            }else{
                $(".orangPensiun").hide();
            }
        });
    }
    function loadPegawaiCuti(){
        var tmp_data_pensiun= "";
        var tmp_data_tahunan= "";
        var tmp_data_panjang= "";
        var tmp_jml_pensiun= "";
        var tmp_jml_tahunan= "";
        var tmp_jml_panjang= "";
        var data = [];
        dwr.engine.setAsync(false);
        NotifikasiAction.searchCutiPensiun(function(listData){
            data = listData;
            $.each(data, function(i, item){
                if(tmp_jml_pensiun == ""){
                    tmp_jml_pensiun = item.jmlApproval;
                }
                tmp_data_pensiun += "<li class='pensiun' data-id='"+item.nip+"'>"+
                    "<a>"+
                    "<span class='label label-info'>"+item.stTanggalPensiun+"</span> "+item.namaPegawai+" "+item.stSelisih+
                    "</a>"+
                    "</li>";
            })
        });
        NotifikasiAction.searchCutiTahunan(function(listData){
            data = listData;
            $.each(data, function(i, item){
                if(tmp_jml_tahunan == ""){
                    tmp_jml_tahunan = item.jmlApproval;
                }
                tmp_data_tahunan += "<li class='tahunan' data-id='"+item.nip+"'>"+
                    "<a>"+
                    "<span class='label label-warning'>"+item.stTanggalAktif+"</span> "+item.namaPegawai+
                    "</a>"+
                    "</li>";
            })
        });
        NotifikasiAction.searchCutiPanjang(function(listData){
            data = listData;
            $.each(data, function(i, item){
                if(tmp_jml_panjang == ""){
                    tmp_jml_panjang = item.jmlApproval;
                }
                tmp_data_panjang += "<li class='panjang' data-id='"+item.nip+"'>"+
                    "<a>"+
                    "<span class='label label-warning'>"+item.stTanggalAktif+"</span> "+item.namaPegawai+
                    "</a>"+
                    "</li>";
            })
        });
        if (tmp_jml_tahunan==""){
            tmp_jml_tahunan=0;
        }
        if (tmp_jml_panjang==""){
            tmp_jml_panjang=0;
        }
        if (tmp_jml_pensiun==""){
            tmp_jml_pensiun=0;
        }
        var total = parseInt(tmp_jml_pensiun)+parseInt(tmp_jml_tahunan)+parseInt(tmp_jml_panjang);
        $("#count4").html(total);
        $("#inner5").html(tmp_data_pensiun);
        $("#count5").html(tmp_jml_pensiun);
        $("#inner6").html(tmp_data_tahunan);
        $("#count6").html(tmp_jml_tahunan);
        $("#inner7").html(tmp_data_panjang);
        $("#count7").html(tmp_jml_panjang);
    }
    $(document).ready(function() {
        loadUser();
        loadNotif();
        loadPegawaiCuti();
        $('.pemberitahuan').on('click', function() {
            var my = $(this).data('id');
            var theArray = my.split("~");
            var id = theArray[0];
            var isi = theArray[1];
            $('#modal-view-notif').find('.modal-title').text('Pemberitahuan');
            $('#ctext').text(isi);
            $('#modal-view-notif').modal('show');
            readNotif(id);
        });
        $('#modal-view-notif').on('hidden.bs.modal', function () {
            window.location.reload();
        });
        $('#modal-check-notif').on('hidden.bs.modal', function () {
            window.location.reload();
        });
        $('.pensiun').on('click', function() {
            var nip = $(this).data('id');
            var tmp_table = "";
            $('.cutiTable').find('tbody').remove();
            $('.cutiTable').find('thead').remove();
            $('#modal-check-pensiun').find('.modal-title').text('Jumlah Cuti');
            CutiPegawaiAction.searchViewCuti(nip,function(listdata) {
                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Pegawai</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Tahunan</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Sisa Cuti Panjang</th>"+
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.nip + '</td>' +
                        '<td align="center">' + item.namaPegawai + '</td>' +
                        '<td align="center">' + item.sisaCutiTahunan + '</td>' +
                        '<td align="center">' + item.sisaCutiPanjang + '</td>' +
                        "</tr>";
                });
                $('.cutiTable').append(tmp_table);
            });
            $('#modal-check-pensiun').modal('show');
        });
        /*$('.check').on('click', function() {
            dwr.engine.setAsync(false);
            if (confirm("apakah anda ingin melihat daftar yang belum terapprove ? ")){
                NotifikasiAction.searchApproval(function(listdata){
                    if(listdata!=""){
                        tmp_table = "<thead style='font-size: 12px' ><tr class='active'>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No.</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Keterangan</th>"+
                            "</tr></thead>";
                        var i = i ;
                        $.each(listdata, function (i, item) {
                            tmp_table += '<tr style="font-size: 11px;" ">' +
                                '<td align="center" class="ceknull">' + item.notifId + '</td>' +
                                '<td align="center" class="ceknull">' + item.note + '</td>' +
                                "</tr>";
                        });
                    }
                    $('.approvalTable').append(tmp_table);
                    $('#modal-check-notif').find('.modal-title').text('Check Approve');
                    $('#modal-check-notif').modal('show');
                });
            }
        });*/
    });
</script>
<style>
    .sidebar-menu li i{
        color: #50d4a3 !important;
    }

    .skin-blue .sidebar-menu>li:hover>a, .skin-blue .sidebar-menu>li.active>a {
        color: #fff;
        background: #1e282c;
        border-left-color: #50d4a3;
    }
    .box.box-primary {
        border-top-color: #50d4a3;
    }
    .skin-blue .main-header .navbar .sidebar-toggle:hover {
        background-color: #3bb387;
    }

    .ui-widget-header {
        border: 1px solid #50d4a3;
        background: #50d4a3;
    }

    .pagelinks a{
        color: #FFF;
        padding: 5px;
        background-color: #50d4a3;
        border-radius: 3px;
    }

    .exportlinks a{
        color: #FFF;
        padding: 5px;
        background-color: #50d4a3;
        border-radius: 3px;
    }

    /*.ui-button .ui-icon {*/
        /*background-image: url(/simrs/pages/images/icon-close-popup.png);*/
    /*}*/

    /*.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default, .ui-button, html .ui-button.ui-state-disabled:hover, html .ui-button.ui-state-disabled:active {*/
        /*border: 1px solid #c5dbec;*/
        /*background: #dfeffc url(/simrs/pages/images/icon-close-popup.png) 50% 50% repeat-x;*/
        /*font-weight: bold;*/
        /*color: #2e6e9e;*/
    /*}*/


</style>
<div class="wrapper">
<header class="main-header">
    <!-- Logo -->
    <a href="/simrs" class="logo" style="background-color: #3bb387 !important;">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>e-H</b></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>e-HEALTH</b></span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation" style="background-color: #30d196 !important;">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- /.messages-menu -->
                <!-- Notifications Menu -->
                <%--<li>
                    <!-- Menu toggle button -->
                    <a href="#" class="check">
                        <i class="fa fa-refresh"></i>
                        <span class="label label-warning "></span>
                    </a>
                </li>--%>
                <li class="dropdown notifications-menu orangPensiun">
                    <!-- Menu toggle button -->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-calendar"></i>
                        <span class="label label-info" id="count4"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header"><span id="count5"></span> orang akan pensiun</li>
                        <li>
                            <!-- Inner Menu: contains the notifications -->
                            <ul class="menu" id="inner5">
                                <!-- end notification -->
                            </ul>
                        </li>
                        <li class="header"><span id="count6"></span> orang lahir Cuti Tahunan Tahun Ini</li>
                        <li>
                            <!-- Inner Menu: contains the notifications -->
                            <ul class="menu" id="inner6">
                                <!-- end notification -->
                            </ul>
                        </li>
                        <li class="header"><span id="count7"></span> orang lahir Cuti Panjang Tahun Ini</li>
                        <li>
                            <!-- Inner Menu: contains the notifications -->
                            <ul class="menu" id="inner7">
                                <!-- end notification -->
                            </ul>
                        </li>
                        <%--<li class="footer"><a href="#">View all</a></li>--%>
                    </ul>
                </li>
                <li class="dropdown notifications-menu">
                    <!-- Menu toggle button -->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-envelope-o"></i>
                        <span class="label label-success" id="count"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have <span id="count1"></span> approval</li>
                        <li>
                            <!-- Inner Menu: contains the notifications -->
                            <ul class="menu" id="inner">
                                <!-- end notification -->
                            </ul>
                        </li>
                        <%--<li class="footer"><a href="#">View all</a></li>--%>
                    </ul>
                </li>
                <li class="dropdown notifications-menu">
                    <!-- Menu toggle button -->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-warning" id="count2"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have <span id="count3"></span> notifications</li>
                        <li>
                            <!-- Inner Menu: contains the notifications -->
                            <ul class="menu" id="inner2">
                                <!-- end notification -->
                            </ul>
                        </li>
                        <%--<li class="footer"><a href="#">View all</a></li>--%>
                    </ul>
                </li>
                <!-- Tasks Menu -->
            </ul>
        </div>
    </nav>
</header>
    <!-- Modal -->
    <div class="modal fade" id="modal-view-notif" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Modal Header</h4>
                </div>
                <div class="modal-body">
                    <p id="ctext"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="modal-check-notif" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Modal Header</h4>
                </div>
                <div class="modal-body">
                    <center>
                        <table id="showdata2" width="80%">
                            <tr>
                                <td align="center">
                                    <table style="width: 100%;" class="approvalTable table table-bordered" id="approvalTable">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="modal-check-pensiun" role="dialog">
        <div class="modal-dialog modal-lg">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Modal Header</h4>
                </div>
                <div class="modal-body">
                    <center>
                        <table id="showdata3" width="80%">
                            <tr>
                                <td align="center">
                                    <table style="width: 100%;" class="cutiTable table table-bordered" id="cutiTable">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Navbar -->