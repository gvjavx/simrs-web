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
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#harga_obat').addClass('active');
        });

        function formatRupiah(angka) {
            if(angka != null && angka != '' && angka > 0){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            }else{
                return 0;
            }
        }

    </script>
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
            Harga Obat
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Harga Obat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="obatForm" method="post" namespace="/hargaobat" action="searchHargaObat_hargaobat.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Obat</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_obat" cssStyle="margin-top: 7px"
                                                     name="obat.idObat" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Jenis Obat</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:action id="initJenis" namespace="/jenisobat"--%>
                                                  <%--name="getListJenisObat_jenisobat"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--list="#initJenis.listOfJenisObat" id="obat_jenis_obat"--%>
                                                  <%--listKey="idJenisObat"--%>
                                                  <%--listValue="namaJenisObat"--%>
                                                  <%--name="obat.idJenisObat"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Obat</label>
                                    <div class="col-sm-4">
                                        <%--<s:action id="initObat" namespace="/obat"--%>
                                                  <%--name="getListObat_obat"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--list="#initObat.listOfObat" id="nama_obat"--%>
                                                  <%--listKey="idObat"--%>
                                                  <%--listValue="namaObat"--%>
                                                  <%--name="obat.idObat"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                        <s:textfield id="nama_obat" name="obat.namaObat"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Flag</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:select cssClass="form-control" cssStyle="margin-top: 7px" list="#{'N':'Non Active'}" headerKey="Y" headerValue="Active" name="obat.flag"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="obatForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_hargaobat.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">

                                        <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Searching ...">
                                            Please don't close this window, server is processing your request ...
                                            <br>
                                            <center>
                                                <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                     src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                     name="image_indicator_write">
                                                <br>
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none">
                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                               closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         document.obatForm.action = 'search_obat.action';
                                                                                         document.obatForm.submit();
                                                                                     }
                                                                            }"
                                    >
                                        <s:hidden id="close_pos"></s:hidden>
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Record has been saved successfully.
                                    </sj:dialog>

                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Harga Obat</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered" style="font-size: 12px">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td>Standar Margin</td>
                                <td>Harga Jual Non BPJS(Normal)</td>
                                <td>Harga Jual Non BPJS(Khusus)</td>
                                <td>Harga Jual BPJS(Normal)</td>
                                <td>Harga Jual BPJS(Khusus)</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <%--<s:if test='#row.flagKurangMargin == "Y"'>--%>
                                    <%--<tr style="background-color: #d9534f; color: #ffffff">--%>
                                <%--</s:if>--%>
                                <%--<s:elseif test='#row.flagKurangMargin == "R"'>--%>
                                    <%--<tr style="background-color: #EBEADF; color: black">--%>
                                <%--</s:elseif>--%>
                                <%--<s:else>--%>
                                    <%--<tr>--%>
                                <%--</s:else>--%>
                                    <tr>
                                    <td><s:property value="idObat"/></td>
                                    <td><s:property value="namaObat"/></td>
                                    <td><s:property value="standarMargin"/></td>
                                    <td align="right"><script>document.write(formatRupiah('<s:property value="hargaJualUmumNonBpjs"/>'))</script></td>
                                    <td align="right"><script>document.write(formatRupiah('<s:property value="hargaJualKhususNonBpjs"/>'))</script></td>
                                    <td align="right"><script>document.write(formatRupiah('<s:property value="hargaJualUmumBpjs"/>'))</script></td>
                                    <td align="right"><script>document.write(formatRupiah('<s:property value="hargaJualKhususBpjs"/>'))</script></td>
                                    <td align="center">
                                        <img onclick="editObat('<s:property value="idObat"/>','<s:property value="idBarang"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-obat">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">

            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Edit Harga Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger" id="alert-margin" style="display: none;">Harga Item Kurang dari Standart Margin.</div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_obat">
                    <h4><i class="icon fa fa-ban"></i> Success !</h4>
                    <p id="">Berhasil menyimpan </p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="alert alert-warning alert-dismissible" style="display: none" id="warning_exits">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_exits"></p>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">ID Obat</label>
                                <div class="col-md-7">
                                    <input id="mod-id-obat" class="form-control" readonly="true">
                                    <input type="hidden" id="mod-id-harga-obat">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Nama Obat</label>
                                <div class="col-md-7">
                                    <input id="mod-nama-obat" class="form-control" readonly="true">
                                </div>
                            </div>
                        </div>
                        <%--<div class="row" style="margin-top: 7px">--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-md-5" style="margin-top: 7px">Merek</label>--%>
                                <%--<div class="col-md-7">--%>
                                    <%--<input id="mod-merk" class="form-control" readonly="true">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Standar Margin</label>
                                <div class="col-md-7">
                                    <div class="input-group">
                                        <input type="number" id="mod-standar-margin" class="form-control" readonly>
                                        <div class="input-group-addon">
                                            %
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <hr/>

                <table class="table table-striped table-bordered" style="font-size: 13px">
                    <thead>
                        <td>Konsumen</td>
                        <td align="right">Bruto</td>
                        <td align="right" width="100px">Margin</td>
                        <td align="right">Harga Jual</td>
                    </thead>
                    <tbody id="list-body-konsumen">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal" id="close_obat"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat" ><i class="fa fa-check"></i> Save
                </button>
                <button type="button" class="btn btn-success" id="ok_obat" style="display: none"><i class="fa fa-check"></i> Ok
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function showModal(){

        $('#add_nama_obat, #add_harga_box, #add_harga_lembar, #add_harga_biji, #add_merek, #add_pabrik, #add_box, #add_lembar_box, #add_lembar, #add_biji_lembar, #add_biji').val('');
        $('#add_jenis_obat').val('').trigger('change');

        var id = "";
        $('#load_obat, #war_nama, #war_jenis, #war_pabrik, #war_merek, #war_biji, #war_harga_box, #war_harga_lembar, #war_harga_biji').hide();
        $('#add_box, #add_lembar_box, #add_lembar, #add_biji_lembar').css('border','');
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
        $('#modal-obat').modal('show');

    }

    function saveObat(id, idBarang){

        var idObat      = $("#mod-id-obat").val();
        var listHarga   = [];

        console.log(jumlahList);

        for(var i = 0 ; i < jumlahList ; i++){
            var hargaBruto  = parseRibuan($("#bruto-"+i).val());
            var margin      = $("#margin-"+i).val();
            var hargaJual   = parseRibuan($("#harga-jual-"+i).val());
            var idRekanan   = $("#id-rekanan-"+i).val();
            var jenis       = $("#jenis-konsumen-"+i).val();
            listHarga.push(
                {"harga_bruto":hargaBruto, "margin_obat":margin, "harga_jual":hargaJual, "jenis_konsumen":jenis, "id_rekanan":idRekanan}
            );
        };

        console.log(listHarga);
        var stJson = JSON.stringify(listHarga);
        ObatAction.saveListHargaRekananObat(stJson, idObat, function (res) {
            if (res.status == "success"){
                $("#success_obat").show();
                $("#ok_obat").show();
                $("#ok_obat").attr("onclick", "searchForm('"+id+"')");
                $("#save_obat").hide();
                $("#close_obat").hide();
            } else {
                $("#warning_obat").show();
                $("#obat_error").text(res.msg);
            }
        });

//        if (checkMargin())
//            return false;
//
//        // khusus not bpjs
//        var net     = $("#mod-harga-net").val();
//        var margin  = $("#mod-margin").val();
//
//        // umum not bpjs
//        var netUmum = $("#mod-harga-net-umum").val();
//        var marginUmum = $("#mod-margin-umum").val();
//
//        // khusus bpjs
//        var netKhususBpjs = $("#mod-harga-net-bpjs").val() == '' ? "0" : $("#mod-harga-net-bpjs").val();
//        var marginKhususBpjs = $("#mod-margin-bpjs").val() == '' ? "0" : $("#mod-margin-bpjs").val();
//
//        // umum bpjs
//        var netUmumBpjs = $("#mod-harga-net-umum-bpjs").val() == '' ? "0" : $("#mod-harga-net-umum-bpjs").val();
//        var marginUmumBpjs = $("#mod-margin-umum-bpjs").val() == '' ? "0" : $("#mod-margin-umum-bpjs").val();
//
//        var arJson = [];
//        arJson.push({
//            "harga_net":net, "margin" : margin,
//            "harga_net_umum" : netUmum, "margin_umum" : marginUmum,
//            "net_khusus_bpjs" : netKhususBpjs, "margin_khusus_bpjs" : marginKhususBpjs,
//            "net_umum_bpjs" : netUmumBpjs, "margin_umum_bpjs" : marginUmumBpjs
//        });
//        var stJson = JSON.stringify(arJson);
//        console.log(arJson);
//        console.log(stJson);
//        ObatAction.saveHargaObat(id, idBarang, stJson, function (response) {
//           if (response.status == "success"){
//               $("#success_obat").show();
//               $("#ok_obat").show();
//               $("#ok_obat").attr("onclick", "searchForm('"+id+"')");
//               $("#save_obat").hide();
//               $("#close_obat").hide();
//           } else {
//               $("#warning_obat").show();
//               $("#obat_error").text(response.msg);
//           }
//        });
    }

    function searchForm(idObat){
        $("#id_obat").val(idObat);
        $("#obatForm").submit();
    }

    function editObat(id, idBarang) {
        $('#modal-obat').modal('show');
        showDetailHargaObat(id, idBarang);
        showListHargaKonsumen();
    }

    function showDetailHargaObat(id, idBarang){
        ObatAction.searchHargaObat(id, function (response) {
            if (response.length > 0){
                $.each(response, function(i, item){
                    $('#mod-id-obat').val(item.idObat);
                    $('#mod-id-harga-obat').val(item.idHargaObat);
                    $("#mod-nama-obat").val(item.namaObat);
                    $("#mod-standar-margin").val(item.standarMargin);
                });
                $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\',\''+idBarang+'\')');
            }
        });
    }

    function hitungMargin(id) {
        var res             = id.split("-");
        var number          = res[2];
        var hargaJual       = $("#"+id).val(); hargaJual = hargaJual.replace('.','');
        var hargaTerakhir   = $("#bruto-"+number).val();

        var selisih     = parseFloat(hargaJual) - parseFloat(hargaTerakhir);
        var margin      = parseFloat(selisih) / parseFloat(hargaTerakhir) * 100;

        $("#margin-"+number).val(Math.ceil(margin));
        $("#"+id).val(formatRupiah(Math.ceil(hargaJual)));
        //checkMargin();
    }

    function hitungHargaJual(id) {
        var res             = id.split("-");
        var number          = res[1];
        var margin          = $("#"+id).val();
        var hargaTerakhir   = $("#bruto-"+number).val();

        var selisih = parseFloat(hargaTerakhir) * parseFloat(margin) / 100;
        var harga   = parseFloat(hargaTerakhir) + selisih;

        $("#harga-jual-"+number).val(formatRupiah(Math.ceil(harga)));
        //checkMargin();
    }

    function checkMargin() {

        var hargaBeliUmum       = $("#mod-harga-beli").val();
        var hargaRataKhusus     = $("#mod-harga-rata").val();
        var hargaBeliUmumBpjs   = $("#mod-harga-beli-bpjs").val();
        var hargaRataKhususBpjs = $("#mod-harga-rata-bpjs").val();

        var marginKhusus        = $("#mod-margin").val();
        var marginUmum          = $("#mod-margin-umum").val();
        var marginKhususBpjs    = $("#mod-margin-bpjs").val();
        var marginUmumBpjs      = $("#mod-margin-umum-bpjs").val();


        var standar = $("#mod-standar-margin").val() == null ? 0 : $("#mod-standar-margin").val();
        var isVerifikasi = true;

        if (parseInt(hargaBeliUmum) > 0 && parseInt(marginUmum) >= parseInt(standar)){
            isVerifikasi = false;
        }
        if (parseInt(hargaRataKhusus) > 0 && parseInt(marginKhusus) >= parseInt(standar)){
            isVerifikasi = false;
        }
        if (parseInt(hargaBeliUmumBpjs) > 0 && parseInt(marginUmumBpjs) >= parseInt(standar)){
            isVerifikasi = false;
        }
        if (parseInt(hargaRataKhususBpjs) > 0 && parseInt(marginKhususBpjs) >= parseInt(standar)){
            isVerifikasi = false;
        }
        if (isVerifikasi == true){
            $("#alert-margin").show().fadeOut(7000);
            return true;
        }
    }

    function listSelectObatEdit(idObat){
        var data = [];
        if (idObat != '') {
            ObatAction.getJenisObatByIdObat(idObat, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        data.push(item.idJenisObat)
                    });
                }
            });
        } else {
            alert('id obat kosong');
        }
        return data;
    }

    function hitungDiskon(tipe) {
        var net = "";
        var diskon = "";

        if (tipe == "khusus"){
            net = $("#mod-harga-net").val();
            diskon = $("#mod-diskon").val();
        } else {
            // umum
            net = $("#mod-harga-net-umum").val();
            diskon = $("#mod-diskon-umum").val();
        }

        var hargajual = net - (net*(diskon/100));

        if (tipe == "khusus"){
            $("#mod-harga-jual").val(hargajual);
        } else {
            // umum
            $("#mod-harga-jual-umum").val(hargajual);
        }
    }

    var jumlahList = 0;
    function showListHargaKonsumen() {
        var idObat = $("#mod-id-obat").val();
        ObatAction.listHargaObatPerKonsumenByBranch(idObat, function (res) {

            var n = 0;
            jumlahList = 0;
            var str = "";
            $.each(res, function (i, item) {
               str += "<tr id='baris-"+n+"'>" +
                       "<td>"+item.namaKonsumen+"" +
                       "<input type='hidden' id='id-rekanan-"+n+"' value='"+item.idRekanan+"'/>" +
                       "<input type='hidden' id='jenis-konsumen-"+n+"' value='"+item.jenisKonsumen+"'/>" +
                       "</td>"+
                       "<td><input type='hidden' value='"+item.hargaTerakhir+"' id='bruto-"+n+"'/><input type='text' style='text-align: right; font-size: 13px;' class='form-control' value='"+formatRupiah(item.hargaTerakhir)+"' readonly/></td>"+
                       "<td><input type='number' style='text-align: right; font-size: 13px; width: 100px' class='form-control' id='margin-"+n+"' value='"+formatRupiah(item.margin)+"' oninput='hitungHargaJual(this.id)'/></td>"+
                       "<td><input type='number' style='text-align: right; font-size: 13px;' class='form-control' id='harga-jual-"+n+"' value='"+formatRupiah(item.hargaJual)+"' oninput='hitungMargin(this.id)' readonly/></td>"+
                   "</tr>";
               n = n + 1;
            });

            jumlahList = n;
            $("#list-body-konsumen").html(str);
        });
    }

    function parseRibuan(angka){
        if (angka != ''){

            var spangka = angka.split('.');
            var stTotal = "";
            $.each(spangka, function (i, item) {
                stTotal = stTotal + item;
            });

            return stTotal;
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>