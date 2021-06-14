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
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#penunjang_active, #periksa_lab').addClass('active');
            $('#penunjang_open').addClass('menu-open');
        });

    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>
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
            Periksa Lab Pasien
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-4">
                                <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Periksa Lab Pasien</h3>
                            </div>
                            <div class="col-md-4">
                                <div class="pull-right" style="margin-top: 7px; color: red" id="warning_text"></div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group pull-right">
                                    <input onchange="cekHasil()" class="form-control" placeholder="Scan No Order Lab" id="id_order" oninput="$(this).css('border','');">
                                    <div class="input-group-btn" onclick="cekHasil()">
                                        <button class="btn btn-success"><i class="fa fa-search"></i> Search</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="periksaLabForm" method="post" namespace="/periksalab" action="search_periksalab.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Periksa Lab</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_periksa_lab" cssStyle="margin-top: 7px"
                                                     name="periksaLab.idHeaderPemeriksaan" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Detail Checkup</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_detail_checkup" cssStyle="margin-top: 7px"
                                                     name="periksaLab.idDetailCheckup" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="periksaLab.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="periksaLab.namaPasien"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'1':'Periksa','3':'Selesai'}" cssStyle="margin-top: 7px; width: 100%"
                                                  id="status" name="periksaLab.statusPeriksa"
                                                  headerKey="0" headerValue="Antrian"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="periksaLab.stDateFrom" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="periksaLab.stDateTo" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="periksaLabForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_periksalab.action">
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Periksa Lab Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Masuk</td>
                                <td>ID Detail Checkup</td>
                                <td>Nama Pasien</td>
                                <td>Pelayanan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td>
                                        <s:property value="stCreatedDate"/>
                                        <s:if test='#row.isCito == "Y"'>
                                            <span class="span-warning">CITO</span>
                                        </s:if>
                                    </td>
                                    <td><s:property value="idDetailCheckup"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="namaPelayanan"/></td>
                                    <td align="center">
                                        <s:if test='#row.statusPeriksa == "3"'>
                                            <a target="_blank" href="printLab_periksalab.action?id=<s:property value="idDetailCheckup"/>&lab=<s:property value="idHeaderPemeriksaan"/>">
                                                <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer; ">
                                            </a>
                                        </s:if>
                                        <s:else>
                                            <s:if test='#row.idJenisPeriksa == "umum"'>
                                                <s:if test='#row.statusBayar == "Y"'>
                                                    <s:url var="add_periksa_lab" namespace="/periksalab" action="add_periksalab" escapeAmp="false">
                                                        <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                        <s:param name="lab"><s:property value="idHeaderPemeriksaan"/></s:param>
                                                        <s:param name="ket"><s:property value="isJustLab"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{add_periksa_lab}">
                                                        <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer; ">
                                                    </s:a>
                                                </s:if>
                                                <s:else>
                                                    <span class="span-warning">Uang muka belum bayar</span>
                                                </s:else>
                                            </s:if>
                                            <s:else>
                                                <s:url var="add_periksa_lab" namespace="/periksalab" action="add_periksalab" escapeAmp="false">
                                                    <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                    <s:param name="lab"><s:property value="idHeaderPemeriksaan"/></s:param>
                                                    <s:param name="ket"><s:property value="isJustLab"/></s:param>
                                                </s:url>
                                                <s:a href="%{add_periksa_lab}">
                                                    <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer; ">
                                                </s:a>
                                            </s:else>
                                        </s:else>
                                        <s:if test='#row.isUpload == "Y"'>
                                            <img border="0" class="hvr-grow" onclick="hasilUploadPM('<s:property value="idHeaderPemeriksaan"/>')" src="<s:url value="/pages/images/icons8-pictures-folder-25.png"/>" style="cursor: pointer;">
                                        </s:if>
                                        <img onclick="detail('<s:property value="idHeaderPemeriksaan"/>', '<s:property value="namaPasien"/>', '<s:property value="idPasien"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-search-25.png"/>" style="cursor: pointer; ">
                                        <a href="printLab_periksalab.action?id=<s:property value="idDetailCheckup"/>&lab=<s:property value="idHeaderPemeriksaan"/>&ket=label" target="_blank">
                                            <img class="hvr-grow" src="<s:url value="/pages/images/icons8-barcode-scanner-25.png"/>" style="cursor: pointer; ">
                                        </a>
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

<div class="modal fade" id="modal-detail_lab">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail Pemeriksaan Lab <span id="nama_text"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <table class="table table-striped table-bordered" style="font-size: 13px">
                        <thead>
                        <td>Jenis Pemeriksaan</td>
                        <td>Parameter</td>
                        <tbody id="body_detail_lab">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hasil_lab">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-image"></i> <span
                        id="title_hasil_lab"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div id="carousel-hasil_lab" class="carousel slide">
                                <ol class="carousel-indicators" id="li_hasil_lab">

                                </ol>
                                <div class="carousel-inner" id="item_hasil_lab">

                                </div>
                                <a class="left carousel-control" href="#carousel-hasil_lab" data-slide="prev">
                                    <span class="fa fa-angle-left"></span>
                                </a>
                                <a class="right carousel-control" href="#carousel-hasil_lab" data-slide="next">
                                    <span class="fa fa-angle-right"></span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>
    function detail(id, nama, idPasien){
        $('#nama_text').text(nama);
        var tempPemeriksaan = "";
        var tempPemeriksa = "";
        var tempDetail = "";
        var tempIdPemeriksa = "";
        var tempIdDetail = "";
        PeriksaLabAction.listParameterPemeriksaan(id, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    var namaPemeriksaan = "";
                    var idPemeriksaan = "";
                    var namaDetailPemeriksaan = "";
                    var idDetailPemeriksaan = "";

                    if(item.namaPemeriksaan != null){
                        namaPemeriksaan = item.namaPemeriksaan;
                    }
                    if(item.idPemeriksaan != null){
                        idPemeriksaan = item.idPemeriksaan;
                    }
                    if(item.namaDetailPemeriksaan != null){
                        namaDetailPemeriksaan = item.namaDetailPemeriksaan;
                    }
                    if(item.idDetailPemeriksaan != null){
                        idDetailPemeriksaan = item.idDetailPemeriksaan;
                    }

                    if(namaPemeriksaan.toLowerCase() != tempPemeriksaan){
                        tempPemeriksaan = item.namaPemeriksaan.toLowerCase();
                        if(tempPemeriksa != ''){
                            tempPemeriksa = tempPemeriksa+'='+namaPemeriksaan;
                            tempIdPemeriksa = tempIdPemeriksa+'='+idPemeriksaan;
                        }else{
                            tempPemeriksa = namaPemeriksaan;
                            tempIdPemeriksa = idPemeriksaan;
                        }
                    }

                    if(i == 0){
                        tempDetail = namaDetailPemeriksaan;
                        tempIdDetail = idDetailPemeriksaan;
                    }else{
                        if(response[i - 1]["namaPemeriksaan"].toLowerCase() == tempPemeriksaan){
                            tempDetail = tempDetail+'#'+namaDetailPemeriksaan;
                            tempIdDetail = tempIdDetail+'#'+idDetailPemeriksaan;
                        }else{
                            tempDetail = tempDetail+'='+namaDetailPemeriksaan;
                            tempIdDetail = tempIdDetail+'='+idDetailPemeriksaan;
                        }
                    }
                });
                if(tempPemeriksa != '' && tempDetail != '') {
                    var templ = tempPemeriksa.split("=");
                    var temp2 = tempDetail.split("=");
                    var temp3 = tempIdPemeriksa.split("=");
                    var temp4 = tempIdDetail.split("=");
                    var row = "";
                    $.each(templ, function (i, item) {
                        var tempParameter = temp2[i].split("#");
                        var tempParameterLi = "";
                        $.each(tempParameter, function (i, item) {
                            tempParameterLi += '<li>' + item + '</li>';
                        });
                        row += '<tr id="row_' + i + '">' +
                            '<td>' + item + '</td>' +
                            '<td><ul style="margin-left: 20px">' + tempParameterLi + '</ul></td>' +
                            '</tr>';
                    });
                    if (row != '') {
                        $('#body_detail_lab').html(row);
                    }
                }

            }else{
                $('#body_detail_lab').html('');
            }
        });
        $('#modal-detail_lab').modal({show:true, backdrop:'static'});
    }

    function cekHasil(){
        var id = $('#id_order').val();
        if(id != ''){
            PeriksaLabAction.getEntityHeaderpemeriksaan(id, function (res) {
                if(res != null){
                    if(res.idHeaderPemeriksaan != '' && res.idHeaderPemeriksaan != null){
                        if("3" != res.statusPeriksa){
                            window.location.href = 'add_periksalab.action?id='+res.idDetailCheckup+'&lab='+res.idHeaderPemeriksaan+'&ket='+res.isJustLab;
                        }else{
                            $('#id_order').css('border','red solid 1px');
                            $('#warning_text').html('<i class="fa fa-warning blink_me_atas"></i> Pasien sudah selesai...!');
                        }
                    }
                }else{
                    $('#id_order').css('border','red solid 1px');
                    $('#warning_text').html('<i class="fa fa-warning blink_me_atas"></i> Data tidak ada...!');
                }
            });
        }else{
            $('#id_order').css('border','red solid 1px');
            $('#id_order').focus();
        }
    }

    function hasilUploadPM(id){
        $('#item_hasil_lab').html('');
        $('#li_hasil_lab').html('');
        $('#title_hasil_lab').html("Hasil Pemeriksaan Laboratorium");
        PeriksaLabAction.getUploadHasilPemeriksaan(id, function (res) {
            if (res.length > 0) {
                var set = '';
                var li = '';
                $.each(res, function (i, item) {
                    var cla = 'class="item"';
                    var claLi = '';
                    if (i == 0) {
                        cla = 'class="item active"';
                        claLi = 'class="active"';
                    }
                    var x = item.urlImg;
                    var tipe = x.split('.').pop();
                    if("pdf" == tipe){
                        set += '<div ' + cla + '>\n' +
                            '<embed src="'+item.urlImg+'" style="width: 100%; height: 70%"/>'+
                            '</div>';
                    }else{
                        set += '<div ' + cla + '>\n' +
                            '<img src="' + item.urlImg + '" style="width: 100%">\n' +
                            '</div>';
                    }
                    li += '<li data-target="#carousel-hasil_lab" data-slide-to="' + i + '" ' + claLi + '></li>';
                });
                $('#item_hasil_lab').html(set);
                $('#li_hasil_lab').html(li);
            }

        });
        $('#modal-hasil_lab').modal({show: true, backdrop: 'static'});
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>