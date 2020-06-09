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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanVendorAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#menu_obat').addClass('active');
            $('#sortMinStok').DataTable({
                "order": [[ 4, "asc" ]]
            });
        });

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
            Obat
            <%--<small>e-HEALTH</small>--%>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Print Report Mutasi Obat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="obatForm" method="post" namespace="/obat" action="printReportRiwayat_obat.action" theme="simple" cssClass="form-horizontal" target="_blank">

                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-md-2 col-md-offset-3" style="margin-top: 7px">Pelayanan</label>
                                            <div class="col-md-4">
                                                <s:action id="initComboFarmasi" namespace="/pelayanan" name="initComboPelayananFarmasi_pelayanan"/>
                                                <s:select list="#initComboFarmasi.listOfComboFarmasi" id="idPelayanan" name="obat.idPelayanan"
                                                          listKey="idPelayanan" listValue="namaPelayanan" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-md-2 col-md-offset-3" style="margin-top: 7px">Tahun</label>
                                            <div class="col-md-4">
                                                <s:select list="#{'2020':'2020', '2021' : '2021', '2022':'2022', '2023':'2023', '2024':'2024'}"
                                                          id="periodeTahun" name="obat.tahun"
                                                          headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                        <label class="col-md-2 col-md-offset-3" style="margin-top: 7px">Bulan</label>
                                        <div class="col-md-4">
                                            <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                      id="periodeBulan" name="obat.bulan"
                                                      headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                        </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-md-2 col-md-offset-3" style="margin-top: 7px">Nama Obat</label>
                                            <div class="col-md-4">
                                                <input type="text" class="form-control" style="margin-top: 7px" id="nama_obat" name="obat.namaObat" autocomplete="off">
                                                <input type="hidden" id="idObat" name="obat.idObat">
                                            </div>
                                        </div>
                                    </div>


                                    <%--<label class="control-label col-sm-4">Tanggal</label>--%>
                                    <%--<div class="col-sm-2">--%>
                                        <%--<div class="input-group date" style="margin-top: 7px">--%>
                                            <%--<div class="input-group-addon">--%>
                                                <%--<i class="fa fa-calendar"></i>--%>
                                            <%--</div>--%>
                                            <%--<input type="date" name="obat.stTglFrom"/>--%>
                                            <%--&lt;%&ndash;&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<s:textfield id="tgl_from" name="obat.stTglFrom" cssClass="form-control"&ndash;%&gt;--%>
                                                         <%--&lt;%&ndash;required="false"/>&ndash;%&gt;--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-sm-3">--%>
                                        <%--<div class="input-group date" style="margin-top: 7px">--%>
                                            <%--<div class="input-group-addon">--%>
                                                <%--<i class="fa fa-calendar"></i>--%>
                                            <%--</div>--%>
                                            <%--<input type="date" name="obat.stTglTo"/>--%>
                                            <%--&lt;%&ndash;<s:textfield id="tgl_to" name="obat.stTglTo" cssClass="form-control"&ndash;%&gt;--%>
                                                         <%--&lt;%&ndash;required="false"/>&ndash;%&gt;--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <s:submit type="button" cssClass="btn btn-success" formIds="obatForm" id="search" name="search">
                                            <i class="fa fa-print"></i>
                                            Print
                                        </s:submit>
                                        <a type="button" class="btn btn-danger" href="initPrintReportRiwayat_obat.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a type="button" class="btn btn-danger" href="initForm_obat.action">
                                            <i class="fa fa-arrow-left"></i> Back
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
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>


<!-- /.content-wrapper -->
<script type='text/javascript'>

    var functions, mapped;
    $('#nama_obat').typeahead({
        minLength: 3,
        source: function (query, process) {
            functions = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);

            PermintaanVendorAction.searchObat(query, function (listdata) {
                data = listdata;
            });

            if (data.length > 0) {
                console.log(data);
                $.each(data, function (i, item) {
                    var labelItem =  item.idObat+"-"+item.idPabrik +"-" + item.namaObat;
                    mapped[labelItem] = {
                        id: item.idObat,
                        nama: item.namaObat,
                        idPabrik: item.idPabrik,
                        lb: item.lembarPerBox,
                        bj: item.bijiPerLembar,
                        isBpjs: item.flagBpjs
                    };
                    functions.push(labelItem);
                });
                process(functions);
            }
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            $('#idObat').val(selectedObj.id);
            return selectedObj.nama;
        }
    });

    function saveObat(id){

        var nama        = $('#add_nama_obat').val();
        var jenis       = $('#add_jenis_obat').val();
        var merek       = $('#add_merek').val();
        var pabrik      = $('#add_pabrik').val();
        var box         = $('#add_box').val();
        var lembarBox   = $('#add_lembar_box').val();
        var lembar      = $('#add_lembar').val();
        var bijiLembar  = $('#add_biji_lembar').val();
        var biji        = $('#add_biji').val();
        var hargaBox    = $('#add_harga_box').val();
        var hargaLembar = $('#add_harga_lembar').val();
        var hargaBiji   = $('#add_harga_biji').val();
        var minStok     = $('#add_min_stok').val();


        if (nama != '' && jenis != null && biji != '' && box != ''
            && lembarBox != '' && lembar != '' && bijiLembar != '' && biji != '' && pabrik != ''
            && merek != '' && pabrik != '' && minStok != '') {

            $('#save_obat').hide();
            $('#load_obat').show();

            if (id != '') {
                dwr.engine.setAsync(true);
                ObatAction.editObat(id, nama, jenis, merek, pabrik, lembarBox, bijiLembar, minStok, function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                    } else {
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#warning_obat').show().fadeOut(5000);
                        $('#obat_error').text(response.message);
                    }
                })
            } else {
                dwr.engine.setAsync(true);
                ObatAction.saveObat(nama, jenis, merek, pabrik, box, lembarBox, lembar, bijiLembar, biji, hargaBox, hargaLembar, hargaBiji, minStok, function (response) {
                    console.log(response);
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                    }else if (response.status == "warning") {
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#warning_exits').show().fadeOut(5000);
                        $('#msg_exits').text("ID Pabrik dengan bentuk fisik tersebut sudah ada pada ID obat : "+response.message);
                    } else {
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#warning_obat').show().fadeOut(5000);
                        $('#obat_error').text("Terjadi kesalahan ketika proses simpan ke database..!");
                    }
                })
            }
        } else {
            $('#warning_obat').show().fadeOut(5000);
            $('#obat_error').text("Silahkan cek kembali data inputan..!");

            if (nama == '') {
                $('#war_nama').show();
            }
            if (jenis == '' || jenis == null) {
                $('#war_jenis').show();
            }
            if (hargaBox == '') {
                $('#war_harga_box').show();
            }
            if (hargaLembar == '') {
                $('#war_harga_lembar').show();
            }
            if (hargaBiji == '') {
                $('#war_harga_biji').show();
            }
            if (merek == '') {
                $('#war_merek').show();
            }
            if (box == '') {
                $('#add_box').css('border','red solid 1px');
            }
            if (lembarBox == '') {
                $('#add_lembar_box').css('border','red solid 1px');
            }
            if (lembar == '') {
                $('#add_lembar').css('border','red solid 1px');
            }
            if (bijiLembar == '') {
                $('#add_biji_lembar').css('border','red solid 1px');
            }
            if (biji == '') {
                $('#war_biji').show();
            }
            if (pabrik == '') {
                $('#war_pabrik').show();
            }
            if (minStok == '') {
                $('#war_min_stok').show();
            }
        }
    }

//     function editObat(id, nama, flag, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerBiji, hargaBox, hargaLembar, hargaBiji, idPbarik, mrek) {
//         $('#load_obat, #war_nama, #war_jenis, #war_harga, #war_stok').hide();
//         $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
//         $('#add_nama_obat').val(nama);
//         $('#add_jenis_obat').val(listSelectObatEdit(id)).trigger('change');
//         $('#add_merek').val(mrek);
//         $('#add_pabrik').val(idPbarik);
//         $('#add_box').val(qtyBox);
//         $('#add_lembar_box').val(lembarPerBox);
//         $('#add_lembar').val(qtyLembar);
//         $('#add_biji_lembar').val(bijiPerBiji);
//         $('#add_biji').val(qtyBiji);
//         $('#add_harga_box').val(hargaBox);
//         $('#add_harga_lembar').val(hargaLembar);
//         $('#add_harga_biji').val(hargaBiji);
// //        $('#add_stok').val(stok);
//         $('#add_flag').val(flag);
//         $('#modal-obat').modal('show');
//     }

    function editObat(idObat, nama, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerBiji,idPbarik, mrek, minStok) {
        $('#load_obat, #war_nama, #war_jenis, #war_harga, #war_stok').hide();
        $('#save_obat').attr('onclick', 'saveObat(\'' + idObat + '\')').show();
        $('#add_nama_obat').val(nama);
        $('#add_id_obat').val(idObat);
        $('#add_jenis_obat').val(listSelectObatEdit(idObat)).trigger('change');
        $('#add_merek').val(mrek);
        $('#add_pabrik').val(idPbarik);
        $('#add_box').val(qtyBox).attr('disabled','');
        $('#add_lembar_box').val(lembarPerBox);
        $('#add_lembar').val(qtyLembar).attr('disabled','');
        $('#add_biji_lembar').val(bijiPerBiji);
        $('#add_biji').val(qtyBiji).attr('disabled','');
        $('#add_min_stok').val(minStok);
        $('#form-edit').hide();
        $('#modal-obat').modal({show:true, backdrop:'static'});
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

    function detailObat(idObat, namaObat, flag, lembarPerBox, bijiPerLembar, merk, jenis, minStok){
        $('#body_detail').html('');

        if(idObat != null && idObat != ''){
            $('#det_id_obat').text(idObat);
            $('#det_nama_obat').text(namaObat);
            $('#det_jenis_obat').html(jenis);
            $('#det_merk_obat').text(merk);
            $('#det_lembar_obat').text(lembarPerBox);
            $('#det_biji_obat').text(bijiPerLembar);
            $('#det_min_stok_obat').text(minStok +" Box");

            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            today = mm + '-' + dd + '-' + yyyy;

            var table = "";
            ObatAction.getListObatDetail(idObat, function (response) {
                if(response.length > 0){
                    $.each(response, function (i, item) {

                        var dateExp = $.datepicker.formatDate('mm-dd-yy', new Date(item.expiredDate));

                        const date1 = new Date(today);
                        const date2 = new Date(dateExp);
                        const diffTime = Math.abs(date2 - date1);
                        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                        var warna = "";
                        var color = "";
                        var disabled = "";

                        if(Math.abs(date1) > Math.abs(date2)){
                            warna = '#ccc';
                            color = '#fff';
                            disabled = 'disabled';

                        } else if (diffDays < 10) {
                            warna = '#dd4b39';
                            color = '#fff';

                        } else if (diffDays < 30) {
                            warna = '#eea236';
                            color = '#fff';
                        } else {
                            warna = '#fff';
                            color = '#333';
                        }

                        table += '<tr bgcolor=' + warna + ' style="color: ' + color + '">' +
                                '<td>'+item.idBarang+'</td>'+
                                '<td>'+formaterDate(item.expiredDate)+'</td>'+
                                '<td>'+item.qtyBox+'</td>'+
                                '<td>'+item.qtyLembar+'</td>'+
                                '<td>'+item.qtyBiji+'</td>'+
                                '</tr>'
                    });

                    $('#body_detail').html(table);
                }else{

                }
            });
            $('#modal-detail-obat').modal({show:true, backdrop:'static'});
        }
    }

    function formaterDate(tanggal){
        var tgl = "";

        if(tanggal != null && tanggal != ''){
            tgl = $.datepicker.formatDate("dd-mm-yy", new Date(tanggal));
        }

        return tgl;
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>