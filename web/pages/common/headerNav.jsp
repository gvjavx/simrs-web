<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<script type='text/javascript' src='<s:url value="/dwr/interface/NotifikasiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/NotifAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/TransaksiObatAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/NotifikasiAdminAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/KonsultasiGiziAction.js"/>'></script>
<script type="text/javascript">

    function loadDataLogin() {
        dwr.engine.setAsync(false);
        UserAction.getUserData(function(item){
           $("#user_name").html(item.username);
           $("#user_name_head").html(item.username);
           $("#user_branch").html(item.branchName);
           $("#user_area").html(item.areaName);
           $("#logo_branch").html('<img src="'+item.logoBranch+'" alt="Logo RS" style="height: 35px; width: 110px; margin-top: 8px">');
        });
    }

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
                            "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Training</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                } else if (item.typeNotif == "TN22"){
                    tmp_data_approve += "<li>"+
                            "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-warning'>Medical Record</span> "+item.note+""+
                            "</a>"+
                            "</li>";

                } else if (item.tipeNotifId == "TI"){
                    tmp_data_approve += "<li>"+
                            "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>SPPD</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }else if (item.tipeNotifId == "TN33"){
                    tmp_data_approve += "<li>"+
                        "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                        "<span class='label label-success'>Absensi</span> "+item.note+""+
                        "</a>"+
                        "</li>";
                }else if (item.tipeNotifId == "TN44"){
                    tmp_data_approve += "<li>"+
                        "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                        "<span class='label label-success'>Indisipliner</span> "+item.note+""+
                        "</a>"+
                        "</li>";
                }else if (item.tipeNotifId == "TN55"){
                    tmp_data_approve += "<li>"+
                            "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Dispensasi</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }
                else if (item.tipeNotifId == "TN66"){
                    tmp_data_approve += "<li>"+
                            "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Cuti Pegawai</span> "+item.note+""+
                            "</a>"+
                            "</a>"+
                            "</a>"+
                            "</li>";
                }else if (item.tipeNotifId == "TN77"){
                    tmp_data_approve += "<li>"+
                            "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Lembur</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }else if (item.tipeNotifId == "TN88"){
                    tmp_data_approve += "<li>"+
                            "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Ijin Keluar Kantor</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }else if (item.tipeNotifId == "TN99"){
                    tmp_data_approve += "<li>"+
                            "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                            "<span class='label label-success'>Rekruitmen Pabrik</span> "+item.note+""+
                            "</a>"+
                            "</li>";
                }else if (item.tipeNotifId == "TN01"){
                    tmp_data_approve += "<li>"+
                        "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                        "<span class='label label-success'>Keuangan</span> "+item.note+""+
                        "</a>"+
                        "</li>";
                }else if (item.tipeNotifId == "TN04"){
                    tmp_data_approve += "<li>"+
                        "<a href='<s:property value="appname" />notifikasi/viewNotifikasi_notifikasi.action?id="+item.fromPerson+"&request="+item.noRequest+"&tipeNotif="+item.tipeNotifId+"&notif="+item.notifId+"' onclick='readNotif("+item.notifId+");'>"+
                        "<span class='label label-success'>Pengajuan Biaya</span> "+item.note+""+
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
        NotifikasiAction.searchUser(function(data){
            if (data=="admin_hcm"){
                $(".orangPensiun").show();
            }
            if (data=="admin_keu"){
                $(".pengajuanBiaya").show();
                $(".terimaRk").show();
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

    function loadPengajuanBiaya(){
        var tmp_data_pengajuan= "";
        var tmp_jml_pengajuan="";
        var data = [];
        dwr.engine.setAsync(false);
        NotifikasiAction.searchPengajuanBiayaMenggantung(function(listData){
            data = listData;
            $.each(data, function(i, item){
                if(tmp_jml_pengajuan == ""){
                    tmp_jml_pengajuan = item.jmlApproval;
                }
                tmp_data_pengajuan += "<li class='pengajuanBiaya' data-id='"+item.pengajuanBiayaDetailId+"'>"+
                    "<a>"+
                    "<span class='label label-info'>"+item.stTanggalRealisasi+"</span> "+item.keperluan+
                    "</a>"+
                    "</li>";
            })
        });
        if (tmp_jml_pengajuan==""){
            tmp_jml_pengajuan=0;
        }
        var total = parseInt(tmp_jml_pengajuan);
        $("#count8").html(total);
        $("#inner8").html(tmp_data_pengajuan);
        $("#count9").html(tmp_jml_pengajuan);
    }

    function loadRk(){
        var tmp_data_rk= "";
        var data = [];
        var total=0;
        dwr.engine.setAsync(false);
        NotifikasiAction.searchTerimaRkPengajuan(function(listData){
            data = listData;
            $.each(data, function(i, item){
                total = item.jmlApproval;

                tmp_data_rk += "<li>"+
                    "<a href='<s:property value="appname" />pengajuanBiaya/terimaRk_pengajuanBiaya.action?rkId="+item.rkId+"' >"+
                    "<span class='label label-primary'>Terima RK</span> "+item.rkId+""+
                    "</a>"+
                    "</li>";
            })
        });
        $(".count_rk").html(total);
        $("#inner_rk").html(tmp_data_rk);
    }

    function pushNotifResep(){
        cekNotifResep();
        setInterval(function () {
            cekNotifResep();
        }, 30000);
    }

    function pushNotifTele(){
        cekNotifTele();
        setInterval(function () {
            cekNotifTele();
        }, 30000);
    }

    function pushNotifLab(kategori){
        cekNotifLab(kategori);
        setInterval(function () {
            cekNotifLab(kategori);
        }, 30000);
    }

    function pushNotifGizi(){
        cekNotifKonsultasiGizi();
        setInterval(function () {
            cekNotifKonsultasiGizi();
        }, 30000);
    }

    function cekNotifResep(){
        TransaksiObatAction.pushNotifResep(function (res) {
            var listResep = "";
            var cekCount = $('#count2').text();
            if(cekCount == ''){
                cekCount = 0;
            }
            if(res.length > 0){
                $.each(res, function (i, item) {
                    listResep += '<li>' +
                        '<a href="<%= request.getContextPath() %>/reseppoli/searchResep_reseppoli.action?id='+item.idDetailCheckup+'&idPermintaan='+item.idPermintaanResep+'">' +
                        '<i class="fa fa-info-circle text-green"></i> <small style="margin-left: -8px">'+' ['+item.idPermintaanResep +']'+' '+item.namaPasien+'</small><br>' +
                        '</a>' +
                        '</li>';
                });
                $('#inner2').html(listResep);
                $('#count2').html(res.length);
                $('#count3').html(res.length);
                if(res.length > parseInt(cekCount)){
                    $('#notif_sound').get(0).autoplay = true;
                    $('#notif_sound').get(0).load();
                }
            }else{
                $('#inner2').html(listResep);
                $('#count2').html('');
                $('#count3').html('');
            }
        });
    }

    function cekNotifTele(){
        NotifikasiAdminAction.getUnReadNotifAdminEntity("tele", function (res) {
            var listResep = "";
            var cekCount = $('#count2').text();
            if(cekCount == ''){
                cekCount = 0;
            }
            if(res.length > 0){
                $.each(res, function (i, item) {
                    listResep += '<li>' +
                        '<a href="<%= request.getContextPath() %>/onlinepaymentverif/search_onlinepaymentverif.action?id='+item.idItem+'&tipe=notif&notifid='+item.id+'">' +
                        '<i class="fa fa-info-circle text-green"></i> <small style="margin-left: -8px">'+' ['+item.createdWho +']'+' '+item.keterangan+'</small><br>' +
                        '</a>' +
                        '</li>';
                });
                $('#inner2').html(listResep);
                $('#count2').html(res.length);
                $('#count3').html(res.length);
                if(res.length > parseInt(cekCount)){
                    $('#notif_sound').get(0).autoplay = true;
                    $('#notif_sound').get(0).load();
                }
            }else{
                $('#inner2').html(listResep);
                $('#count2').html('');
                $('#count3').html('');
            }
        });
    }

    function cekNotifLab(kategori){
        PeriksaLabAction.pushNotifLab(kategori, function (res) {
            var listLab = "";
            var cekCount = $('#count2').text();
            if(cekCount == ''){
                cekCount = 0;
            }
            if(res.length > 0){
                $.each(res, function (i, item) {
                    var href = "";
                    if(item.kategori == "lab"){
                        href = '/periksalab/add_periksalab.action?id='+item.idDetailCheckup+'&lab='+item.idPeriksaLab+'&ket=';
                    }
                    if(item.kategori == "radiologi"){
                        href = '/radiologi/add_radiologi.action?id='+item.idDetailCheckup+'&lab='+item.idPeriksaLab+'&ket=';
                    }
                    if(item.kategori != null && item.kategori != '') {
                        listLab += '<li>' +
                            '<a href="<%= request.getContextPath() %>'+href+'">' +
                            '<i class="fa fa-info-circle text-green"></i> <small style="margin-left: -8px">'+' ['+item.idPeriksaLab +']'+' '+item.namaPasien+'</small><br>' +
                            '</a>' +
                            '</li>';
                    }
                });
                $('#inner2').html(listLab);
                $('#count2').html(res.length);
                $('#count3').html(res.length);
                if(res.length > parseInt(cekCount)){
                    $('#notif_sound').get(0).autoplay = true;
                    $('#notif_sound').get(0).load();
                }
            }else{
                $('#inner2').html(listLab);
                $('#count2').html('');
                $('#count3').html('');
            }
        });
    }

    function cekNotifKonsultasiGizi(){
        KonsultasiGiziAction.pushNotif(function (res) {
            var listKonsul = "";
            var cekCount = $('#count2').text();
            if(cekCount == ''){
                cekCount = 0;
            }
            if(res.length > 0){
                $.each(res, function (i, item) {
                    listKonsul += '<li>' +
                        '<a style="cursor: pointer" onclick="choiceGizi(\''+item.idKonsultasiGizi+'\', \''+item.noCheckup+'\')">' +
                        '<i class="fa fa-info-circle text-green"></i> <small style="margin-left: -8px">'+' ['+item.idPasien +']'+' '+item.nama+'</small><br>' +
                        '</a>' +
                        '</li>';
                });
                $('#inner2').html(listKonsul);
                $('#count2').html(res.length);
                $('#count3').html(res.length);
                if(res.length > parseInt(cekCount)){
                    $('#notif_sound').get(0).autoplay = true;
                    $('#notif_sound').get(0).load();
                }
            }else{
                $('#inner2').html(listKonsul);
                $('#count2').html('');
                $('#count3').html('');
            }
        });
    }

    function choiceGizi(idKonsulGizi, noCheckup) {
        var form = { "konsultasiGizi.idKonsultasiGizi":idKonsulGizi, "konsultasiGizi.noCheckup":noCheckup };
        var host = "<%= request.getContextPath() %>/konsultasigizi/search_konsultasigizi.action";
        postAtas(host, form);
    }

    function cekRole(){
        TransaksiObatAction.cekRole(function (res) {
            if(res == "ADMIN APOTEK"){
                pushNotifResep();
            }
            if(res == "VERIFIKATOR PEMBAYARAN ONLINE"){
                pushNotifTele();
            }
            if(res == "Kasir Telemedic"){
                pushNotifTele();
            }
            if(res == "ADMIN LAB"){
                pushNotifLab('lab');
            }
            if(res == "ADMIN RADIOLOGI"){
                pushNotifLab('radiologi');
            }
            if(res == "ADMIN GIZI"){
                cekNotifKonsultasiGizi();
            }
        })
    }

    $(document).ready(function() {
        $(".orangPensiun").hide();
        $(".pengajuanBiaya").hide();
        $(".terimaRk").hide();
        loadDataLogin();
        loadUser();
        loadNotif();
        loadPegawaiCuti();
        loadPengajuanBiaya();
        loadRk();
        cekRole();

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

        getStringUrlPhotoProfile();
    });

    function getStringUrlPhotoProfile() {

        var usname = $('#user_name_head').text;

        if (usname != ""){
            UserAction.getStringUrlPhotoProfile(function (str) {
                // $("#img-profile-sm-sm").attr('src', str);
                $("#img-profile-sm-sm").html('<img src="'+str+'" class="user-image" alt="User Image" onerror="this.onerror=null;this.src=\'<s:url value="/pages/images/unknown-person.png"></s:url>\'">');
                $("#img-profile").attr('src', str);
            });
        }
    }
</script>
<style>
    .sidebar-menu li i{
        color: #50d4a3 !important;
    }

        /*color: #fff;*/
        /*background: #1e282c;*/
        /*border-left-color: #50d4a3;*/
    /*}*/

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
        padding: 5px;
        border-radius: 3px;
    }

    .exportlinks a{
        /*color: #FFF;*/
        padding: 5px;
        /*background-color: #50d4a3;*/
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
    <a href="<%= request.getContextPath() %>" class="logo" style="background-color: #3bb387 !important; text-decoration: none;">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>NMU</b></span>
        <!-- logo for regular state and mobile devices -->
        <%--<img border="0" class="hvr-grow" src="<s:url value="/pages/images/RS01.png"/>" style="cursor: pointer; height: 25px; width: 35px;">--%>
        <span class="logo-lg" > <b>GO-MEDSYS NMU</b></span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation" style="background-color: #30d196 !important;">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <span id="logo_branch"></span>
        <%--<img src="<s:url value="/pages/images/RS03.png"/> " alt="Logo RS" style="height: 35px; width: 110px; margin-top: 8px">--%>
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
                <li class="dropdown notifications-menu pengajuanBiaya">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-money"></i>
                        <span class="label label-success" id="count8"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">Ada <span id="count9"></span> pengajuan biaya</li>
                        <li>
                            <ul class="menu" id="inner8">
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="dropdown notifications-menu terimaRk">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-tasks"></i>
                        <span class="label label-primary count_rk"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">Ada <span class="count_rk"></span> Penerimaan RK</li>
                        <li>
                            <ul class="menu" id="inner_rk">
                            </ul>
                        </li>
                    </ul>
                </li>
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
                <li class="dropdown user user-menu">
                    <a style="cursor: pointer;" class="dropdown-toggle" data-toggle="dropdown">
                        <%--<div id="img-profile-small">--%>

                        <%--</div>--%>
                            <span id="img-profile-sm-sm"></span>
                        <%--<img id="img-profile-sm-sm" class="user-image" alt="User Image" onerror="this.onerror=null;this.src='<s:url value="/pages/images/unknown-person.png"></s:url>;'">--%>
                        <span class="hidden-xs" id="user_name_head"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header" style="background-color: #30d196; height: 200px">
                            <img id="img-profile" class="img-circle" alt="User Image" onerror="this.onerror=null;this.src='<s:url value="/pages/images/unknown-person.png"></s:url>;'">
                            <p>
                                <span id="user_name"></span>
                                <small id="user_branch"></small>
                                <small id="user_area"></small>
                            </p>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <%--<div class="pull-left">--%>
                                <%--<a href="#" class="btn btn-default btn-flat">Profile</a>--%>
                            <%--</div>--%>
                            <div style="cursor: pointer" class="pull-right">
                                <a href="<s:property value="appname" />j_spring_security_logout" class="btn btn-danger"><i class="fa fa-sign-out"></i> Sign out</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <%--<li class="dropdown notifications-menu">--%>
                    <%--<!-- Menu toggle button -->--%>
                    <%--<a onclick="logout()" style="cursor: pointer">--%>
                        <%--<i class="fa fa-sign-out"></i>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <!-- Tasks Menu -->
            </ul>
        </div>
    </nav>
    <audio id="notif_sound" src='<s:url value="/pages/images/notif.mp3"/>'></audio>
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