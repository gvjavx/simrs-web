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

    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanVendorAction.js"/>'></script>

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
            Tambah Permintaan PO
            <small>e-HEALTH</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-plus-square"></i> Data Input Permintaan PO</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_po">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_po"></p>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Nama Vendor</label>
                                    <div class="col-md-8">
                                        <s:select list="#{'V001':'Vendor A','V002':'Vendor B'}"
                                                  cssStyle="margin-top: 7px" onchange="$(this).css('border','')"
                                                  id="nama_vendor"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Nama Obat</label>
                                    <div class="col-md-8">
                                        <s:action id="initObat" namespace="/obat"
                                                  name="getListObat_obat"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initObat.listOfObat" id="nama_obat"
                                                  listKey="idObat + '|' + namaObat + '|' + qty"
                                                  onchange="var warn =$('#war_req_obat').is(':visible'); if (warn){$('#cor_req_obat').show().fadeOut(3000);$('#war_req_obat').hide()};"
                                                  listValue="namaObat"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
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
                            </div>
                            <%--<div class="col-md-6">--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<s:select list="#{'L':'Laki-Laki','P':'Perempuan'}"--%>
                                                  <%--cssStyle="margin-top: 7px" onchange="$(this).css('border','')"--%>
                                                  <%--id="jenis_kelamin" name="headerCheckup.jenisKelamin"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<s:select list="#{'L':'Laki-Laki','P':'Perempuan'}"--%>
                                                  <%--cssStyle="margin-top: 7px" onchange="$(this).css('border','')"--%>
                                                  <%--id="jenis_kelamin" name="headerCheckup.jenisKelamin"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="col-md-8 col-md-offset-4">
                                        <a type="button" class="btn btn-success" onclick="addToListPo()"><i class="fa fa-plus"></i> Tambah</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-file-text-o"></i> Daftar Permintaan PO</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped" id="tabel_po">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID</td>
                                <td>Obat</td>
                                <td>Qty</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_po">
                            </tbody>
                        </table>
                        <br>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="input-group" id="img_file">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn-file">
                                                            Browse… <s:file id="imgInp" accept=".jpg" name="fileUpload" onchange="$('#img_file').css('border','')"></s:file>
                                                        </span>
                                                    </span>
                                    <input type="text" class="form-control" readonly placeholder="Dokumen PO">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <a type="button" class="btn btn-success" onclick="savePermintaanPO()"><i class="fa fa-arrow-right"></i> Save</a>
                                <a type="button" class="btn btn-danger" onclick="toContent()"><i class="fa fa-refresh"></i> Reset</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function toContent() {
        window.location.reload(true);
    }

    function addToListPo() {

        var vendor    = $('#nama_vendor').val();
        var obat      = $('#nama_obat').val();
        var data      = $('#tabel_po').tableToJSON();

        var idObat   = "";
        var namaObat = "";
        var qtyObat  = "";

        var cek = false;

        if (obat != '' && vendor != '') {
            idObat   = obat.split('|')[0];
            namaObat = obat.split('|')[1];
            qtyObat  = obat.split('|')[2];

//            if (parseInt(qty) <= parseInt(stok)) {
                $.each(data, function (i, item) {
                    if (item.ID == idObat) {
                        cek = true;
                    }
                });

                if (cek) {
                    $('#warning_po').show().fadeOut(5000);
                    $('#msg_po').text('Data sudah tersedia dalam list...!');
                } else {
                    var row = '<tr id=' + idObat + '>' +
                            '<td>' + idObat + '</td>' +
                            '<td>' + namaObat + '</td>' +
                            '<td>' + qtyObat + '</td>' +
                            '<td align="center"><img border="0" onclick="delRowObat(\'' + idObat + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                            '</tr>';

                    $('#body_po').append(row);
                    $('#nama_vendor').attr('disabled', true);
                }
//            } else {
//                $('#warning_request').show().fadeOut(5000);
//                $('#msg_request').text('Jumlah Request tidak boleh melebihi stok obat...!');
//            }
        } else {
            if (obat == '') {
//                $('#war_req_obat').show();
            }
            if (vendor == '') {
//                $('#war_req_qty').show();
            }
            $('#warning_po').show().fadeOut(5000);
            $('#msg_po').text('Silahkan cek kembali data inputan...!');
        }
    }

    function delRowObat(id) {
        $('#' + id).remove();
    }

    function savePermintaanPO() {
//        var data = $('#tabel_reture_detail').tableToJSON();
//        var stringData = JSON.stringify(data);
        var vendor = $('#nama_vendor').val();

        if (vendor != '') {

//            $('#save_req_detail').hide();
//            $('#load_ret_detail').show();
//
//            dwr.engine.setAsync(true);
            PermintaanVendorAction.savePermintaanPO(vendor,{
                callback: function (response) {
                    if (response == "success") {
//                        dwr.engine.setAsync(false);
//                        $('#modal-reture-detail').modal('hide');
//                        $('#info_dialog').dialog('open');
//                        $('#save_req_detail').show();
//                        $('#load_ret_detail').hide();
                    } else {
//                        $('#warning_reture_detail').show().fadeOut(5000);
//                        $('#msg_reture_detail').text('Terjadi kesalahan saat menyimpan data...!');
//                        $('#save_req_detail').show();
//                        $('#load_ret_detail').hide();
                    }
                }
            });

        } else {
//            $('#warning_reture_detail').show().fadeOut(5000);
//            $('#msg_reture_detail').text('Silahkan cek kembali data inputan berikut...!');
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>