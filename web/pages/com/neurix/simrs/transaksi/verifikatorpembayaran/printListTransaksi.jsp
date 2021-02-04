<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <style>
        .modal { overflow-y: auto}
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/VerifikatorPembayaranAction.js"/>'></script>
    <script type='text/javascript'>


        function formatRupiah(angka) {
            if(angka != null && angka != ''){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                if (angka < 0){
                    return "-"+ribuan;
                } else {
                    return ribuan;
                }
            }else{
                return 0;
            }
        }

    </script>
    <style>
        .top-7{
            margin-top: 7px;
        }
    </style>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Verifikator Pembayaran
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Print List Transaksi</h3>
                    </div>
                    <div class="box-body">

                        <%--alert bukan kasir / belum ada jadwal shift kerja--%>
                        <div id="alert-shift" class="alert alert-danger" style="text-align: center; display: none">
                        </div>

                        <div class="row">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Tanggal </label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control datepicker" id="tanggal">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Shift</label>
                                            <div class="col-sm-4">
                                                <select id="sel-shift" class="form-control">

                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Jenis Pasien</label>
                                            <div class="col-sm-4">
                                                <select class="form-control" id="sel-jenis-pasien">
                                                    <option value="umum">UMUM</option>
                                                    <option value="asuransi">ASURANSI</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <input type="hidden" id="status-transaksi" value="finish"/>
                                    <br>
                                    <div class="form-group col-md-offset-2">
                                        <button class="btn btn-success" id="btn-search"><i class="fa fa-search"></i> Search </button>
                                        <button class="btn btn-success" id="btn-print"><i class="fa fa-print"></i> Print Sumary</button>
                                        <a type="button" class="btn btn-danger" href="initForm_onlinepaymentverif.action">
                                            <i class="fa fa-refresh"></i> Back
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hamburger"></i> List Transaksi</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-12">
                                <table class="table table-bordered">
                                    <thead style="font-size: 13px; background-color: #90ee90;">
                                    <td>No. Trans</td>
                                    <td>Jenis Trans</td>
                                    <td>Keterangan</td>
                                    <td>Bank</td>
                                    <td>No. RM</td>
                                    <td>Pasien</td>
                                    <td>Pelayanan</td>
                                    <td>Dokter</td>
                                    <td align="center">Nominal</td>
                                    <td>Last Update</td>
                                    <td>Last Update Who</td>
                                    <td>Action</td>
                                    </thead>
                                    <tbody id="body-search" style="font-size: 11px;">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-loading-dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Saving ...
                </h4>
            </div>
            <div class="modal-body">
                <div id="waiting-content" style="text-align: center">
                    <h4>Please don't close this window, server is processing your request ...</h4>
                    <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                         src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                         name="image_indicator_write">
                    <br>
                    <img class="spin" border="0"
                         style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                         src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                         name="image_indicator_write">
                </div>

                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin_waiting">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_fin_error_waiting"></p>
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <%--<button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-success-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Success
                </h4>
            </div>
            <div class="modal-body" style="text-align: center">
                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                     name="icon_success">
                Record has been saved successfully.
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <button type="button" class="btn btn-sm btn-success" id="ok_con"><i class="fa fa-check"></i> Ok
                </button>
            </div>
        </div>
    </div>
</div>



<script type='text/javascript'>
    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }

    // exemple : post('/contact/', {name: 'Johnny Bravo'});
    function post(path, params) {

        var method='post';
        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        const form = document.createElement('form');
        form.method = method;
        form.action = path;

        for (const key in params) {
            if (params.hasOwnProperty(key)) {
                const hiddenField = document.createElement('input');
                hiddenField.type = 'hidden';
                hiddenField.name = key;
                hiddenField.value = params[key];

                form.appendChild(hiddenField);
            }
        }

        document.body.appendChild(form);
        form.submit();
    }

    $("#tanggal").change(function () {
        var tanggal = $("#tanggal").val();
        VerifikatorPembayaranAction.getListShiftByTanggalShift(tanggal, function (res) {

            var str = '';
            $.each(res, function (i, item) {
                str += '<option value="'+item.shiftId+'"> '+item.shiftName+' </option>';
            });

            $("#sel-shift").html(str);
        });
    });

    $("#btn-print").click(function () {
        var host = firstpath()+"/onlinepaymentverif/print_onlinepaymentverif.action";
        var tanggal         = $("#tanggal").val();
        var shifId          = $("#sel-shift option:selected").val();
        var jenisPasien     = $("#sel-jenis-pasien option:selected").val();
        var statusPasien    = $("#status-transaksi").val();

        var allowPrint = true;
        var strError = "";
        if (tanggal == ""){
            strError += "<div>Tanggal Harus Diisi !</div>";
            allowPrint = false;
        }
        if (shifId == "" || shifId == null){
            strError += "<div>Shift Harus Dipilih !</div>";
            allowPrint = false;
        }
        if (jenisPasien == "" || jenisPasien == null){
            strError += "<div>Jenis Harus Dipilih !</div>";
            allowPrint = false;
        }

        if (allowPrint){
            showDialog("loading");
            var params = { "antrianTelemedic.stDateFrom" : tanggal, "antrianTelemedic.shiftId" : shifId, "antrianTelemedic.idJenisPeriksaPasien" : jenisPasien, "antrianTelemedic.statusTransaksi" : statusPasien };
            post(host, params);
            $("#modal-loading-dialog").modal('hide');
        } else {
            $("#alert-shift").html(strError);
            $("#alert-shift").show().fadeOut(5000);
        }
    });

    $("#btn-search").click(function () {
        dwr.engine.setAsync(true);
        var tanggal         = $("#tanggal").val();
        var shifId          = $("#sel-shift option:selected").val();
        var jenisPasien     = $("#sel-jenis-pasien option:selected").val();
        var statusPasien    = $("#status-transaksi").val();

        var allowPrint = true;
        var strError = "";
        if (tanggal == ""){
            strError += "<div>Tanggal Harus Diisi !</div>";
            allowPrint = false;
        }
        if (shifId == "" || shifId == null){
            strError += "<div>Shift Harus Dipilih !</div>";
            allowPrint = false;
        }
        if (jenisPasien == "" || jenisPasien == null){
            strError += "<div>Jenis Harus Dipilih !</div>";
            allowPrint = false;
        }

        if (allowPrint){
            //alert("onclick");
            $("#modal-loading-dialog").modal('show');
            var objData = {"date" : tanggal, "shift" : shifId, "jenis" : jenisPasien, "status" : statusPasien};
            var stJson  = JSON.stringify(objData);

            dwr.engine.setAsync(false);
            VerifikatorPembayaranAction.getSearchListTransByShift(stJson, function (res) {
                $("#modal-loading-dialog").modal('hide');
                var str = '';
                $.each(res, function(i, item){
                    str += '<tr>'+
                            '<td>'+item.id+'</td>'+
                            '<td style="font-weight: bold">'+item.idJenisPeriksaPasien.toUpperCase()+'</td>'+
                            '<td>'+item.keterangan+'</td>'+
                            '<td>'+item.namaBank+'</td>'+
                            '<td>'+item.idPasien+'</td>'+
                            '<td>'+item.namaPasien+'</td>'+
                            '<td>'+item.namaPelayanan+'</td>'+
                            '<td>'+item.namaDokter+'</td>'+
                            '<td style="text-align: right;">'+formatRupiah(item.nominal)+'</td>'+
                            '<td>'+item.stLastUpdate+'</td>'+
                            '<td>'+item.lastUpdateWho+'</td>'+
                            '<td><button class="btn btn-primary" onclick="printStruk(\''+item.id+'\');"><i class="fa fa-print"></i></button></td>'+
                        '</tr>';
                });

                $("#body-search").html(str);
            });
        } else {
            $("#alert-shift").html(strError);
            $("#alert-shift").show().fadeOut(5000);
        }
    });

    function formatRupiah(angka) {
        if(angka != "" && angka != null && parseInt(angka) > 0){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        } else {
            return "0";
        }
    }

    function showDialog(tipe) {
        if (tipe == "loading"){
            $("#modal-loading-dialog").modal('show');
        }
        if (tipe == "error"){
            $("#modal-loading-dialog").modal('show');
            $("#waiting-content").hide();
            $("#warning_fin_waiting").show();
        }
        if (tipe == "success"){
            $("#modal-loading-dialog").modal('hide');
            $("#modal-success-dialog").modal('show');
        }
    }


    function iconFlag(var1) {
        if (var1 == "Y")
            return "<img src='<s:url value="/pages/images/icon_success.ico" />'>";
        else if (var1 == "N")
            return "<img src='<s:url value="/pages/images/icon_failure.ico" />'>";
        else
            return "";
    }

    function nullEscape(var1) {
        if (var1 == null)
            return "";
        else
            return var1;
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>