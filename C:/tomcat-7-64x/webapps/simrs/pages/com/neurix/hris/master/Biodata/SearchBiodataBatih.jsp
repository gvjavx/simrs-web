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
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/StudyAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SppdAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AbsensiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>

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
    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_biodata'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Daftar Karyawan Dan Batih
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="biodataForm" method="post"  theme="simple" namespace="/biodata" action="searchKaryawanDanBatih_biodata.action" cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>


                        <table>
                            <tr>
                                <td width="10%" align="center">
                                    <%@ include file="/pages/common/message.jsp" %>
                                </td>
                            </tr>
                        </table>

                        <table>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="biodata.branch"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                        </table>



                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="biodataForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initFormKaryawanDanBatih_biodata.action"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="70%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="400" width="600" autoOpen="false"
                                                   title="Biodata">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfResultBiodataBatih" value="#session.listOfResultBiodataBatih" scope="request" />
                                        <display:table name="listOfResultBiodataBatih" class="table table-condensed table-striped table-hover listOfbiodata"
                                                       requestURI="paging_displaytag_biodataDanBatih_biodata.action" export="true" id="row" pagesize="60" style="font-size:10">

                                            <display:column property="noAnggota" sortable="true" title="No" />
                                            <display:column property="nip" sortable="true" title="NIP" />
                                            <display:column property="namaPegawai" sortable="true" title="Nama Pegawai" />
                                            <display:column property="batih" sortable="true" title="Batih" />
                                            <display:column property="statusKeluarga" sortable="true" title="Status Keluarga"/>
                                            <display:column property="stTanggalLahir" sortable="true" title="Tanggal Lahir"/>
                                            <display:column property="umur" sortable="true" title="Umur"/>
                                            <%--<display:column property="noKtp" sortable="true" title="No KTP" />
                                            <display:column property="stTanggalAktif" sortable="true" title="Tanggal Aktif"/>
                                            <display:column property="stTanggalPensiun" sortable="true" title="Tanggal Pensiun"/>--%>


                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </s:form>
                </td>
            </tr>
        </table>

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

<script>
    $(document).ready(function() {
        var d = new Date();
        var n = d.getFullYear();

        $('#tahunAbsensi').empty();
        for(a = n-5 ; a <= (n + 5) ; a++){
            $('#tahunAbsensi').append($("<option></option>")
                    .attr("value", a)
                    .text(a));
        }

        $('#btnCariAbsensi').click(function() {
            var bulan = document.getElementById("bulanAbsensi").value;
            var tahun = document.getElementById("tahunAbsensi").value;
            var nip = document.getElementById("nipAbsensi").value;

            $('.tableAbsensi').find('tbody').remove();
            $('.tableAbsensi').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";

            var blnThn = tahun +"-"+bulan;
            AbsensiAction.cariAbseni(nip, blnThn, function(listdata) {
                tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
             "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>"+
             "<th style='text-align: center; background-color:  #3c8dbc''>In</th>"+
             "<th style='text-align: center; background-color:  #3c8dbc''>Out</th>"+
             "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
             "</tr></thead>";
             var i = i ;
                $.each(listdata, function (i, item) {
                    var jamMasuk = "-";
                    var jamKeluar = "-";
                    var statusName = "-";

                    if(item.jamMasuk != null){
                        jamMasuk = item.jamMasuk;
                    }
                    if(item.jamKeluar != null){
                        jamKeluar = item.jamKeluar;
                    }
                    if(item.statusName != null){
                        statusName = item.statusName;
                    }

                    tmp_table += '<tr  style="font-size: 12px;" ">' +
                                    '<td >' + item.stTanggal+ '</td>' +
                                    '<td align="center">' + jamMasuk+ '</td>' +
                                    '<td align="center">' + jamKeluar  + '</td>' +
                                    '<td align="center">' + statusName + '</td>' +
                                    "</tr>";
                });
                $('.tableAbsensi').append(tmp_table);
             });
        });
    });

    $('.listOfbiodata').on('click', '.item-jabatan', function(){
        var nip = $(this).attr('data');
        
        $('.tabelDetailJabatan').find('tbody').remove();
        $('.tabelDetailJabatan').find('thead').remove();
        $('.tabelDetailJabatan').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        BiodataAction.historyJabatan(nip, function(listdata) {
            tmp_table = "<thead style='color: white; font-size: 15px'><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bidang</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Unit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>PJS</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                    "</tr></thead>";
            var i = i ;
            var totalPoin = 0;
            $.each(listdata, function (i, item) {
                var bidang = "-";
                var branchName = "-";
                var positionName = "-";
                var status = "-";
                var pjs = "-";
                var tahun = "-";

                if(item.bidang!= null){
                    bidang = item.bidang;
                }
                if(item.tahun != null){
                    tahun = item.tahun;
                }
                if(item.branchName!= null){
                    branchName = item.branchName;
                }
                if(item.positionName!= null){
                    positionName = item.positionName;
                }
                if(item.status!= null){
                    status = item.status;
                }
                if(item.pjsFlag!= null){
                    if(item.pjsFlag == "Y"){
                        pjs = "Iya";
                    }else{
                        pjs = 'Tidak';
                    }
                }
                tmp_table += '<tr  style="font-size: 12px">' +
                        '<td >' + bidang + '</td>' +
                        '<td >' + branchName+ '</td>' +
                        '<td >' + positionName+ '</td>' +
                        '<td >' + status+ '</td>' +
                        '<td >' + pjs+ '</td>' +
                        '<td >' + tahun+ '</td>' +
                        "</tr>";
            });
            $('.tabelDetailJabatan').append(tmp_table);
        });

        $('#modal-jabatan').find('.modal-title').text('Jenjang Karir');
        $('#modal-jabatan').modal('show');
    });

    $('.listOfbiodata').on('click', '.item-payroll', function(){
        var nip = $(this).attr('data');

        $('.tablePayroll').find('tbody').remove();
        $('.tablePayroll').find('thead').remove();
        $('.tablePayroll').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        BiodataAction.searchPayroll(nip, function(listdata) {
            tmp_table = "<thead style='color: white; font-size: 15px'><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Download</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bulan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Gaji Kotor</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Potongan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Pph</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Gaji Bersih</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Rapel</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Thr</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Pendidikan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jasprod</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Pensiun</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jubilium</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var link = "/hris/payroll/printReportPayroll_payroll.action?id=" + item.payrollId + "&flag=Y";
                tmp_table += '<tr  style="font-size: 12px">' +
                        '<td ><a href="'+link+'" >Download</a></td>' +
                        '<td >' + item.bulan+ '</td>' +
                        '<td >' + item.tahun+ '</td>' +
                        '<td >' + item.totalA+ '</td>' +
                        '<td >' + item.totalB+ '</td>' +
                        '<td >' + item.pphGaji+ '</td>' +
                        '<td >' + item.totalGajiBersih+ '</td>' +
                        '<td >' + item.totalRapel+ '</td>' +
                        '<td >' + item.totalThr+ '</td>' +
                        '<td >' + item.totalPendidikan+ '</td>' +
                        '<td >' + item.totalJasProd+ '</td>' +
                        '<td >' + item.totalPensiun+ '</td>' +
                        '<td >' + item.totalJubileum+ '</td>' +
                        "</tr>";
            });
            $('.tabelPayroll').append(tmp_table);
        });

        $('#modal-payroll').find('.modal-title').text('Payroll');
        $('#modal-payroll').modal('show');
    });


    $('.listOfbiodata').on('click', '.item-pendidikan', function(){
        var nip = $(this).attr('data');

        $('.tablePendidikan').find('tbody').remove();
        $('.tablePendidikan').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        StudyAction.searchData(nip, function(listdata) {

            tmp_table = "<thead style='font-size: 15px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Akhir</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var typeStudy = "-";
                var studyName = "-";
                var tahunAwal = "-";
                var tahunAkhir = "-";

                if(item.typeStudy != null){
                    typeStudy = item.typeStudy;
                }
                if(item.studyName != null){
                    studyName = item.studyName;
                }
                if(item.tahunAwal != null){
                    tahunAwal = item.tahunAwal;
                }
                if(item.tahunAkhir != null){
                    tahunAkhir = item.tahunAkhir;
                }

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td >' + typeStudy + '</td>' +
                        '<td >' + studyName + '</td>' +
                        '<td align="center">' + tahunAwal + '</td>' +
                        '<td align="center">' + tahunAkhir + '</td>' +
                        "</tr>";
            });
            $('.tablePendidikan').append(tmp_table);
        });
        
        $('#modal-pendidikan').find('.modal-title').text('Riwayat Pendidikan');
        $('#modal-pendidikan').modal('show');
    });

    $('.listOfbiodata').on('click', '.item-detail', function(){
        var nip = $(this).attr('data');

        BiodataAction.detailBiodata(nip, function(listdata) {
            $('#detailImg').attr('src', listdata.pathFoto);
            $('#detailImg').attr('alt', listdata.namaPegawai);

            $('#detailNip').val(listdata.nip);
            $('#detailNama').val(listdata.namaPegawai);
            $('#detailTempatLahir').val(listdata.tempatLahir);
            $('#detailTanggalLahir').val(listdata.stTanggalLahir);
            $('#detailGender').val(listdata.genderName);
            $('#detailAgama').val(listdata.agama);
            $('#detailStatusKeluarga').val(listdata.statusKeluargaName);
            $('#detailJumlahAnak').val(listdata.jumlahAnak);

            $('#detailTanggalAktif').val(listdata.stTanggalAktif);
            $('#detailTanggalPensiun').val(listdata.stTanggalPensiun);
            $('#detailMasaKerja').val(listdata.masaKerja);

            $('#detailUnit').val(listdata.branchName);
            $('#detailBagian').val(listdata.divisiName);
            $('#detailJabatan').val(listdata.positionName);
            $('#detailNPWP').val(listdata.npwp);
            $('#detailKtp').val(listdata.noKtp);

            $('#detailAlamat').val(listdata.alamat );
            $('#detailTipePegawai').val(listdata.tipePegawaiName);

            $('#detailStatusGiling').val(listdata.statusGilingName);
            $('#detailGolongan').val(listdata.golonganName);
            $('#detailPoin').val(listdata.point);

            $('#detailStatusPegawai').val(listdata.statusPegawaiName);
            $('#detailDanaPensiun').val(listdata.danaPensiun);
            $('#detailZakat').val(listdata.zakatName);

            $('#detailNamaBank').val(listdata.namaBank);
            $('#detailCabangBank').val(listdata.cabangBank);
            $('#detailNoRekBank').val(listdata.noRekBank);
        });

        $('#modal-detail').find('.modal-title').text('Biodata Detail');
        $('#modal-detail').modal('show');
    });

    $('.listOfbiodata').on('click', '.item-cuti', function(){
        var nip = $(this).attr('data');
        $('.tableCuti').find('tbody').remove();
        $('.tableCuti').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        CutiPegawaiAction.sisaCuti(nip, function(listdata) {
            tmp_table = "<thead style='font-size: 15px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Cuti</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Sisa Cuti(Hari)</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td>' + item.cutiName + '</td>' +
                        '<td align="center">' + item.sisaCutiHari + '</td>' +
                        "</tr>";
            });
            $('.tableCuti').append(tmp_table);
        });

        $('#modal-cuti').find('.modal-title').text('Cuti');
        $('#modal-cuti').modal('show');
    });

    $('.listOfbiodata').on('click', '.item-absensi', function(){
        var nip = $(this).attr('data');
        $('#nipAbsensi').val(nip);
        $('.tableAbsensi').find('tbody').remove();
        $('.tableAbsensi').find('thead').remove();


        $('#modal-absensi').find('.modal-title').text('Absensi');
        $('#modal-absensi').modal('show');
    });

    $('.listOfbiodata').on('click', '.item-sppd', function(){
        var nip = $(this).attr('data');
        $('.sppdTable').find('tbody').remove();
        $('.sppdTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        SppdAction.searchSppdPerson(nip, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Sppd ID</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Dinas</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Berangkat Dari</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tujuan Ke</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Berangkat</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Kembali</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Detail</th>"+
                    "</tr></thead>";

            $.each(listdata, function (i, item) {
                var atasan = '';
                var sdm = '';
                var closed = '';
                var tiket = '';

                if(item.sppdClosed){
                    var myDate = new Date(item.tanggalBerangkat);
                    var myDateKembali = new Date(item.tanggalKembali);
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i +1) + '</td>' +
                            '<td >' + item.sppdId + '</td>' +
                            '<td >' + item.dinas + '</td>' +
                            '<td align="center">' + item.berangkatDari+ '</td>' +
                            '<td align="center">' + item.tujuanKe+ '</td>' +
                            '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                            '<td align="center">' + (myDateKembali.getDate()) +' - '+ ("0" + (myDateKembali.getMonth() + 1)).slice(-2) +' - '+myDateKembali.getFullYear() + '</td>' +
                            '<td align="center"> <a class="item-sppd-detail" href="javascript:;"  data="'+item.sppdId+'"><i class="fa fa-book" style="font-size:19px"></i></a> </td>' +
                            "</tr>";
                }


            });
            $('.sppdTable').append(tmp_table);
        });

        $('#modal-sppd').find('.modal-title').text('SPPD');
        $('#modal-sppd').modal('show');
    });
    $('.sppdTable').on('click', '.item-sppd-detail', function(){
        var sppdId = $(this).attr('data');
        $('#sppdId1').val(sppdId);
        //alert(sppdId);
        SppdAction.searchSppd(sppdId, function(listdata) {
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalSppdBerangkat);
                var myDate1 = new Date(item.tanggalSppdKembali);
                $('#tanggalBerangkat1').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                $('#tanggalKembali1').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());

                $('#nama1').val(item.personName);
                $('#branchId1').val(item.branchId).change();
                $('#positionId1').val(item.positionId).change();
                $('#dinas1').val(item.dinas).change();
                $('#divisiId1').val(item.divisiId).change();
                $('#keperluan1').val(item.tugasSppd);
                $('#berangkatDari1').val(item.berangkatDari);
                $('#tujuan1').val(item.tujuanKe);
                $('#berangkatVia1').val(item.berangkatVia);
                $('#kembaliVia1').val(item.pulangVia);
                $('#notApprove').val(item.notApprovalNote);
                $('#approveAtasan').val(item.approvalName);
                $('#approveAtasanId').val(item.approvalId);
                loadPerson(sppdId, 'N');
                loadReroute(sppdId);
                loadDocSppd(sppdId);
            });
        });
        //alert( $('#branchId1').text);
        $('#modal-sppd-detail').find('.modal-title').text('Detail SPPD Person');
        $('#modal-sppd-detail').modal('show');
        $('#myForm').attr('action', 'atasan');
    });


    window.loadPerson =  function(id, status){
        //alert(nip);
        var branch         = $('select[name=branchText] option:selected').text();
        var divisi         = $('select[name=divisiText] option:selected').text();
        //alert(branch);
        $('.sppdPersonTable').find('tbody').remove();
        $('.sppdPersonTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchAnggota(id, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Branch</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Divisi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Position</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note For Not Approval</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval SDM Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Approval SDM</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Note For Not Approval SDM</th>"+
                        /*"<th style='text-align: center; background-color:  #3c8dbc''>Nip Pengganti</th>"+*/
                    "<th style='text-align: center; background-color:  #3c8dbc''>Nama Pengganti</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var atasan = '';
                var sdm = '';

                if(item.sppdApproveSdm == true){
                    sdm = item.linkSdm ;
                }
                if(item.sppdApprove == true){
                    atasan = item.linkAtasan ;
                }

                tmp_table += '<tr  style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.personName + '</td>' +
                        '<td align="center">' + item.branchName + '</td>' +
                        '<td align="center">' + item.divisiName  + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + item.approvalName + '</td>' +
                        '<td align="center"><img src="' + atasan + '"</td>' +
                        '<td align="center">' + item.notApprovalNote + '</td>' +
                        '<td align="center">' + item.approvalSdmName+ '</td>' +
                        '<td align="center"><img src="' + sdm + '"</td>' +
                        '<td align="center">' + item.notApprovalSdmNote + '</td>' +
                        '<td align="center">' + item.pejabatSementaraNama + '</td>' +

                        "</tr>";
            });
            $('.sppdPersonTable').append(tmp_table);

        });
    }
    window.loadReroute =  function(sppdId){
        //alert(branch);
        $('.sppdRerouteTable').find('tbody').remove();
        $('.sppdRerouteTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchReroute(sppdId, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Reroute Dari</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Reroute Ke</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Dari Tanggal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Sampai Tanggal</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalRerouteDari);
                var myDate1 = new Date(item.tanggalRerouteKe);

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td >' + item.sppdPersonName + '</td>' +
                        '<td align="center">' + item.rerouteDari + '</td>' +
                        '<td align="center">' + item.rerouteKe  + '</td>' +
                        '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                        '<td align="center">' + (myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear() + '</td>' +
                        "</tr>";
            });
            $('.sppdRerouteTable').append(tmp_table);

        });
    }
    window.loadDocSppd =  function(sppdId){
        //alert(branch);
        $('.sppdDocTable').find('tbody').remove();
        $('.sppdDocTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        SppdAction.searchDoc(sppdId, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>View</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Keterangan</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center"> <a class="item-download" href="javascript:;"  data="'+item.sppdDocId+'"><i class="fa fa-download" style="font-size:19px"></i></a> </td>' +
                        '<td align="center">' + item.note + '</td>' +
                        "</tr>";
            });
            $('.sppdDocTable').append(tmp_table);
        });
    }
</script>