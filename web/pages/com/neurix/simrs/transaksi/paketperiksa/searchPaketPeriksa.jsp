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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PaketPeriksaAction.js"/>'></script>

    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#paket_periksa').addClass('active');
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
            Paket Periksa
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Paket Periksa</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="paketperiksaForm" method="post" namespace="/paketperiksa" action="search_paketperiksa.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Paket</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_paket" cssStyle="margin-top: 7px"
                                                     name="paketPeriksa.idPaket" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Paket</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_paket" name="paketPeriksa.namaPaket"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>

                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="paketperiksaForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_paketperiksa.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a type="button" class="btn btn-primary" href="add_paketperiksa.action">
                                            <i class="fa fa-plus"></i> Tambah Paket
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
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Paket Periksa</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>ID Paket</td>
                                <td>Nama Paket</td>
                                <td align="center" width="15%">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult">
                                <tr>
                                    <td><s:property value="idPaket"/></td>
                                    <td><s:property value="namaPaket"/></td>
                                    <td align="center">
                                        <img onclick="detailPaket('<s:property value="idPaket"/>','<s:property value="namaPaket"/>', '<s:property value="tarif"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-search-25.png"/>" style="cursor: pointer; ">
                                        <%--<img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer; ">--%>
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

<div class="modal fade" id="modal-detail">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Detail Paket</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <table class="table table-striped">
                    <tr>
                        <td width="20%">ID paket</td>
                        <td><p id="det_id_paket"></p></td>
                    </tr>
                    <tr>
                        <td>Nama Paket</td>
                        <td><p id="det_nama_paket"></p></td>
                    </tr>
                    <tr>
                        <td>Tarif</td>
                        <td><p id="det_tarif_paket"></p></td>
                    </tr>
                </table>
                <div class="row">
                    <div class="col-md-6">
                       <p>Daftar Tindakan</p>
                        <table class="table table-striped table-bordered">
                            <thead>
                            <td>ID Tindakan</td>
                            <td>Nama Tindakan</td>
                            </thead>
                            <tbody id="body_tindakan">
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6">
                        <p>Daftar Penunjang Medis</p>
                        <table class="table table-striped table-bordered" id="tabel_medis">
                            <thead>
                            <td>Nama Lab</td>
                            <td>Jenis Lab</td>
                            <td align="center">Action</td>
                            </thead>
                            <tbody id="body_lab">
                            </tbody>
                        </table>
                    </div>
                    <div id="temp_pemeriksaan"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function convertSentenceCase(myString){
        if(myString != null && myString != ''){
            var rg = /(^\w{1}|\ \s*\w{1})/gi;
            myString = myString.replace(rg, function(toReplace) {
                return toReplace.toUpperCase();
            });
            return myString;
        }else{
            return "";
        }
    }

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

    function detailPaket(idpaket, namaPaket, tarif){
        $('body_tindakan').html('');
        $('body_lab').html('');
        $('#modal-detail').modal({show:true, backdrop:'static'});
        $('#det_id_paket').text(idpaket);
        $('#det_nama_paket').text(namaPaket);
        $('#det_tarif_paket').text("Rp. "+formatRupiah(tarif));
        PaketPeriksaAction.detailPaket(idpaket, function (response) {
            if(response.length > 0){
                var table1 = "";
                var table2 = "";
                var idLab1 = "";
                var idLab2 = "";
                var detailLab1 = [];
                var detailLab2 = [];
                var cekek = false;

                $.each(response, function (i, item) {

                   if(item.jenisItem == "tindakan"){
                           table1 += '<tr>' +
                               '<td>'+item.idItem+'</td>' +
                               '<td>'+item.keterangan+'</td>' +
                               '</tr>';

                   }if(item.jenisItem == "laboratorium"){

                        if(idLab1 != item.idLab){
                            idLab1 = item.idLab;
                            table2 += '<tr id="row'+item.idLab+'">' +
                                '<td>'+item.namaLab+'</td>' +
                                '<td>'+convertSentenceCase(item.jenisItem)+'</td>' +
                                '<td align="center">'+'<img id="btn'+item.idLab+'" class="hvr-grow" onclick="detailLab(\''+item.idLab+'\',\''+item.idPaket+'\')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">'+'</td>' +
                                '</tr>';
                        }

                   }if(item.jenisItem == "radiologi"){

                        if(idLab2 != item.idLab){
                            idLab2 = item.idLab;
                            table2 += '<tr id="row'+item.idLab+'">' +
                                '<td>'+item.namaLab+'</td>' +
                                '<td>'+convertSentenceCase(item.jenisItem)+'<input type="hidden" id="det_radiologi'+item.idLab+'"></td>' +
                                '<td align="center">'+'<img id="btn'+item.idLab+'" class="hvr-grow" onclick="detailLab(\''+item.idLab+'\',\''+item.idPaket+'\')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">'+'</td>' +
                                '</tr>';
                        }
                    }
                });
                $('#body_tindakan').html(table1);
                $('#body_lab').html(table2);
           }
        });
    }

    function detailLab(idLab, idPaket){
        var table = "";
        PaketPeriksaAction.detailItem(idLab, idPaket, function (response) {
            if(response.length > 0){
                $.each(response, function (i, item) {
                    table += '<tr>' +
                        '<td>'+item.keterangan+'</td>';
                    '</tr>';
                });
            };
        });
        var rowIndex = document.getElementById("row"+idLab).rowIndex;
        var table2 = '<table class="table table-bordered"><tr bgcolor="#ffebcd">' +
            '<td>Nama Pemeriksaan</td>' +
            '<tbody>'+table+'</tbody>'+
            '</table>';
        var newRow = $('<tr id="del'+idLab+'"><td colspan="3">'+table2+'</td></tr>');
        var cancel = '<s:url value="/pages/images/icons8-cancel-25.png"/>';
        $('#btn'+idLab).attr('src',cancel);
        $('#btn'+idLab).attr('onclick', 'deleteRow(\''+idLab+'\',\''+idPaket+'\')');
        // newRow.insertAfter($('#tabel_medis tr:nth('+rowIndex+')'));
        newRow.insertAfter($('table').find('#row' + idLab));
    }

    function deleteRow(idLab, idPaket){
        $('#del'+idLab).remove();
        var plus = '<s:url value="/pages/images/icons8-plus-25.png"/>';
        $('#btn'+idLab).attr('src',plus);
        $('#btn'+idLab).attr('onclick', 'detailLab(\''+idLab+'\',\''+idPaket+'\')');
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>