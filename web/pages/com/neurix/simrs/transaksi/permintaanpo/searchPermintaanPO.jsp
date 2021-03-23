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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanVendorAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#permintaan_po').addClass('active');
        });

        function reture() {
            $('#tipePermintaan').val('002').trigger('change');
            $('#flag').val('N').trigger('change');
            document.obatGudangForm.action = 'searchPermintaanObatGudang_obatgudang.action';
            document.obatGudangForm.submit();
        }

    </script>

</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%--<div class="pulse"></div>--%>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Purchase Order (PO)
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian PO</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="permintaanPOForm" method="post" namespace="/permintaanpo"
                                    action="search_permintaanpo.action" theme="simple"
                                    cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Permintaan Vendor</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_permintaan" cssStyle="margin-top: 7px"
                                                     name="permintaanVendor.idPermintaanVendor" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Approval</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_obat" name="permintaanVendor.idApprovalObat"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non Active'}"
                                                  cssStyle="margin-top: 7px"
                                                  id="flag" name="permintaanVendor.flag"
                                                  headerKey="Y" headerValue="Active"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-8" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="permintaanPOForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-primary" href="add_permintaanpo.action">
                                            <i class="fa fa-plus"></i> Tambah Permintaan PO
                                        </a>
                                        <a type="button" class="btn btn-danger" href="initForm_permintaanpo.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none">
                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                               resizable="false"
                                               closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         toContent();
                                                                                     }
                                                                            }"
                                    >
                                        <s:hidden id="close_pos"></s:hidden>
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Record has been saved successfully.
                                    </sj:dialog>

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
                                                <img class="spin" border="0"
                                                     style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar PO</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID PO</td>
                                <td>Nama Vendor</td>
                                <td>Tanggal Permintaan</td>
                                <td align="center">Jumlah Obat</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idPermintaanVendor"/></td>
                                    <td><s:property value="namaVendor"/></td>
                                    <td><s:property value="stCreatedDate"/></td>
                                    <td align="center"><span style="padding: 6px; background-color: #fbec88; color: black; border-radius: 20px"><s:property value="jumlahObat"/></span></td>
                                    <td><s:if test='#row.keterangan == "Telah Dikonfirmasi"'>
                                        <label class="label label-success"><s:property value="keterangan"/></label>
                                    </s:if><s:else>
                                        <label class="label label-warning"><s:property value="keterangan"/></label>
                                    </s:else></td>
                                    <td align="center">
                                        <s:if test='#row.keterangan == "Telah Dikonfirmasi"'>
                                            <%--<s:url var="print_po" namespace="/permintaanpo" action="printPermintaanPO_permintaanpo" escapeAmp="false">--%>
                                                <%--<s:param name="id"><s:property value="idPermintaanVendor"/></s:param>--%>
                                            <%--</s:url>--%>
                                            <%--<s:a href="%{print_po}" target="_blink">--%>
                                                <%--<img class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;">--%>
                                            <%--</s:a>--%>
                                            <s:if test='#row.flag == "N"'>
                                                <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-test-passed-25-orange.png"/>" onclick="showBatch('<s:property value="idPermintaanVendor"/>','<s:property value="flag"/>','<s:property value="idApprovalObat"/>')">
                                            </s:if>
                                            <s:else>
                                                <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" onclick="showBatch('<s:property value="idPermintaanVendor"/>','<s:property value="flag"/>','<s:property value="idApprovalObat"/>')">
                                            </s:else>
                                        </s:if>
                                        <s:else>
                                            <s:url var="verify_po" namespace="/permintaanpo" action="edit_permintaanpo" escapeAmp="false">
                                                <s:param name="id"><s:property value="idPermintaanVendor"/></s:param>
                                            </s:url>
                                            <s:a href="%{verify_po}">
                                                <img class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                            </s:a>
                                        </s:else>
                                        <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>" onclick="printPo('<s:property value="idPermintaanVendor"/>','<s:property value="idApprovalObat"/>')">

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
</div>

<div class="modal fade" id="modal-batch">
    <div class="modal-dialog modal-flat" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Purchase Order Obat <span id="detail_batch"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none;" id="warning_bat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_bat"></p>
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_bat">
                        <thead>
                        <td align="center">No Batch</td>
                        <td>Last Update</td>
                        <td align="center">Action</td>
                        </thead>
                        <tbody id="body_bat">
                        </tbody>
                    </table>
                </div>
                <input type="hidden" id="bat_id_permintaan_vendor">
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <%--<button type="button" class="btn btn-success" id="save_bat" onclick="confirmSaveTutup()"><i--%>
                        <%--class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success"--%>
                        <%--id="load_bat"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-detail">
    <div class="modal-dialog modal-flat" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Purchase Order Obat <span id="detail_batch"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none;" id="warning_detail">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_detail"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%">Nomor Faktur</td>
                                    <td><p id="det_no_faktur"></p></td>
                                </tr>
                                <tr>
                                    <td>Tanggal Faktur</td>
                                    <td><p id="det_tlg_faktur"></p></td>
                                </tr>
                                <tr>
                                    <td>Foto Doc</td>
                                    <td><button id="det_img" class="btn btn-primary"><i class="fa fa-image"></i></button></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%">Nomor Invoice</td>
                                    <td><p id="det_no_invoice"></p></td>
                                </tr>
                                <tr>
                                    <td>No DO</td>
                                    <td><p id="det_no_do"></p></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_detail">
                        <thead>
                        <td>ID Barang</td>
                        <td>Nama Obat</td>
                        <td align="center">Qty Approve</td>
                        <td align="center">Jenis Satuan</td>
                        <td align="center">Action</td>
                        </thead>
                        <tbody id="body_detail">
                        </tbody>
                    </table>
                    <p id="loading_detail" style="color: #00a65a; display: none"><img src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang mengambil data...</p>
                </div>
                <input type="hidden" id="id_permintaan_vendor">
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <%--<button type="button" class="btn btn-success" id="save_detail" onclick="confirmSaveTutup()"><i--%>
                        <%--class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success"--%>
                        <%--id="load_detail"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-doc">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-file-archive-o"></i> Surat PO</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <img id="img_surat_po" style="height: 500px; width: 100%">
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-img">
    <div class="modal-dialog modal-fade modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-info"></i> View Uplaoded Documment
                </h4>
            </div>
            <div class="modal-body">
                <div id="body-img">
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4>Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>


<!-- /.content-wrapper -->
<script type='text/javascript'>

    function showBatch(idPermintaan, flag, idApproval){
        $('#body_bat').html('');
        $('#modal-batch').modal({show:true, backdrop:'static'});
        PermintaanVendorAction.listBatch(idApproval, function (response) {
            console.log(response);
            var table = "";
            if(response.length > 0){
                $.each(response, function (i, item) {
                    var tgl = $.datepicker.formatDate('dd-mm-yy', new Date(item.tanggalFaktur));
                    table += '<tr>' +
                        '<td align="center">'+item.noBatch+'</td>' +
                        '<td>'+item.stLastUpdateWho+'</td>' +
                        '<td align="center">' +
                        '<img style="cursor: pointer" onclick="showDetailListObat(\''+idPermintaan+'\',\''+item.noBatch+'\',\''+item.noFaktur+'\',\''+tgl+'\',\''+item.noInvoice+'\',\''+item.noDo+'\',\''+item.urlDoc+'\')" src="<s:url value="/pages/images/icons8-search-25.png"/>">'+
                        '<a target="_blank" href="printPermintaanPO_permintaanpo?id='+idPermintaan+'&noBatch='+item.noBatch+'">' +
                        '<img src="<s:url value="/pages/images/icons8-print-25.png"/>">'+
                        '</a>'+
                        '</td>' +
                        '</tr>';
                });
                $('#body_bat').html(table);
            }
        })
    }

    function showDetailListObat(idpermintaanPo, noBatch, noFaktur, tglFaktur, noInvoice, noDo, img){
        $('#modal-detail').modal({show:true, backdrop:'static'});
        $('#det_no_faktur').text(noFaktur);
        $('#det_tlg_faktur').text(tglFaktur);
        $('#det_no_invoice').text(noInvoice);
        $('#det_no_do').text(noDo);
        $('#det_img').attr('onclick', 'viewUpload(\''+idpermintaanPo+'\', \''+noBatch+'\')')
        $('#loading_detail').show();
        $('#save_detail').show();
        $('#id_permintaan_vendor').val(idpermintaanPo);
        dwr.engine.setAsync(true);
        var table = "";
        PermintaanVendorAction.getListDetailObatApproved(idpermintaanPo, noBatch, {
            callback: function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        table += "<tr>" +
                            "<td>" + item.idBarang + "</td>" +
                            "<td>" + item.namaObat + "</td>" +
                            "<td align='center'>" + item.qtyApprove + "</td>" +
                            "<td align='center'>" + item.jenisSatuan + "</td>" +
                            "<td align='center'>" + '<a target="_blank" href="printBarcodeBarang_permintaanpo.action?id='+item.idBarang+'">' +
                            '<img class="hvr-grow" src="<s:url value="/pages/images/icons8-barcode-scanner-25.png"/>" style="cursor: pointer;"></a>'+
                            "</td>" +
                            "</tr>";
                    });
                    $('#loading_detail').hide();
                } else {
                    $('#loading_detail').hide();
                }
                $('#body_detail').html(table);
            }
        });
    }

    function confirmSaveTutup(){
        $('#modal-confirm-dialog').modal('show');
        $('#save_con').attr('onclick','saveTutup()');
    }

    function saveTutup() {
        $('#modal-confirm-dialog').modal('hide');
        var idPermintaan = $('#id_permintaan_vendor').val();
        $('#save_detail').hide();
        $('#load_detail').show();
        dwr.engine.setAsync(true);
        PermintaanVendorAction.tutupPurchaseOrder(idPermintaan, {callback: function (response) {
                if (response.status == "success") {
                    $('#modal-detail').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#save_detail').show();
                    $('#load_detail').hide();
                    $('body').scrollTop(0);
                } else {
                    $('#save_detail').show();
                    $('#load_detail').hide();
                    $('#warning_detail').show().fadeOut(5000);
                    $('#msg_detail').text(response.msg);
                }
            }
        });
    }

    function toContent() {
        window.location.reload(true);
    }

    function showDoc(img){
        $('#img_surat_po').attr('src',img);
        $('#modal-doc').modal('show');
    }

    function post(path, params) {

        var method='post';
        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        const form = document.createElement('form');
        form.method = method;
        form.action = path;
        form.target = "_blank";

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

    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }


    function printPo(idPermintaan, idApproval) {
        var form = { "permintaanVendor.idPermintaanVendor":idPermintaan, "permintaanVendor.idApprovalObat":idApproval };
        var host = firstpath()+"/permintaanpo/printPo_permintaanpo.action";
        post(host, form);
    }

    function viewUpload(idpermintaanPo, batch) {
        $("#modal-view-img").modal('show');
        $("#body-img").html("");
        PermintaanVendorAction.getListItemDoc(idpermintaanPo, batch, function (list) {
            var str = '';
            if(list.length > 0){
                $.each(list, function (i, item) {
                    var id = 'carousel-example-generic_'+item.idItem;
                    str += '<h5>'+item.jenisNomor.toUpperCase()+' - '+item.idItem+'</h5><div id="'+id+'" class="carousel slide" data-ride="carousel">\n' +
                        '<ol class="carousel-indicators" id="li_'+item.idItem+item.jenisNomor+'">\n' +
                        '</ol>\n' +
                        '<div class="carousel-inner" id="item_'+item.idItem+item.jenisNomor+'">\n' +
                        '</div>\n' +
                        '<a class="left carousel-control" href="#'+id+'" data-slide="prev">\n' +
                        '    <span class="fa fa-angle-left"></span>\n' +
                        '</a>\n' +
                        '<a class="right carousel-control" href="#'+id+'" data-slide="next">\n' +
                        '    <span class="fa fa-angle-right"></span>\n' +
                        '</a>\n' +
                        '</div><hr>';
                    showImg(item.idItem, item.jenisNomor);
                });
            }else{
                str = '<b style="text-align: center">Foto tidak ada..!</b>'
            }
            $("#body-img").html(str);
        });
    }

    function showImg(idItem, jenis){
        PermintaanVendorAction.getListImg(idItem, function (listimg) {
            var str = '';
            var li = '';
            $.each(listimg, function (n, img) {
                var aktive = '';
                var liAcktive = '';
                if(n == 0){
                    aktive = 'active';
                    liAcktive = 'class="active"';
                }
                str += '<div class="item '+aktive+'">\n' +
                    '<img style="height: 300px; width: 100%" src="'+contextPathHeader+'/images/upload/surat_po/'+img.urlImg+'" alt="Slide'+img.urlImg+'">\n' +
                    '<div class="carousel-caption">\n' +img.urlImg +
                    '</div>\n' +
                    '</div>';
                li += '<li data-target="#carousel-example-generic_'+idItem+'" data-slide-to="'+n+'" '+liAcktive+'></li>';
            });
            $("#item_"+idItem+jenis).html(str);
            $("#li_"+idItem+jenis).html(li);
        });
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>