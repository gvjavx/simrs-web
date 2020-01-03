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

            $(document).on('change', '.btn-file :file', function () {
                var input = $(this),
                        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
                input.trigger('fileselect', [label]);
            });

            $('.btn-file :file').on('fileselect', function (event, label) {

                var input = $(this).parents('.input-group').find(':text'),
                        log = label;

                if (input.length) {
                    input.val(log);
                } else {
                    if (log) alert(log);
                }

            });
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#img-upload').attr('src', e.target.result);
                    }

                    reader.readAsDataURL(input.files[0]);
                }
            }

            $("#imgInp").change(function () {
                readURL(this);
            });
        });

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
                                                  onchange="var warn =$('#war_req_obat').is(':visible'); if (warn){$('#cor_req_obat').show().fadeOut(3000);$('#war_req_obat').hide()}; resetField();"
                                                  listValue="namaObat"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Jml Box</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="box" type="number" style="margin-top: 7px" min="0" oninput="jmlLembar()"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Jml Lembar/Box</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="lembar_box" type="number" style="margin-top: 7px" min="0" oninput="jmlLembar();">
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
                                                                                         window.location.href = 'initForm_permintaanpo.action';
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

                                        <sj:dialog id="confirm_dialog" modal="true" resizable="false" closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Confirmation Dialog">
                                            <center><img border="0" style="height: 40px; width: 40px"
                                                         src="<s:url value="/pages/images/icon_warning.ico"/>"
                                                         name="icon_success">
                                                Do you want to save this record?
                                            </center>
                                            <br>
                                            <div class="modal-footer">
                                                <a type="button" class="btn btn-warning" style="color: white;" onclick="$('#confirm_dialog').dialog('close')">
                                                    <i class="fa fa-times"></i> No
                                                </a>
                                                <a type="button" class="btn btn-success" style="color: white;" onclick="savePermintaanPO()">
                                                    <i class="fa fa-arrow-right"></i> Yes
                                                </a>
                                            </div>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Jml Biji/Lembar</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="biji_lembar" type="number" style="margin-top: 7px" oninput="jmlBiji()"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Jml Lembar</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="lembar" type="number" style="margin-top: 7px" readonly="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Jml Biji</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="biji" type="number" style="margin-top: 7px" readonly="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Harga</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="harga" type="number" style="margin-top: 7px"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="col-md-8 col-md-offset-4">
                                        <a type="button" class="btn btn-success" onclick="addToListPo()"><i
                                                class="fa fa-plus"></i> Tambah</a>
                                        <a type="button" class="btn btn-danger" onclick="reset()"><i class="fa fa-refresh"></i>
                                            Reset</a>
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
                                <td>Box</td>
                                <td>Lembar/Box</td>
                                <td>Lembar</td>
                                <td>Biji/Lembar</td>
                                <td>Biji</td>
                                <td align="right">Harga</td>
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
                                                            Browse… <s:file id="imgInp" accept=".jpg" name="fileUpload"
                                                                            onchange="$('#img_file').css('border','')"></s:file>
                                                        </span>
                                                    </span>
                                    <input type="text" class="form-control" readonly placeholder="Upload Dokumen PO">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <a type="button" class="btn btn-success" onclick="confirm()"><i
                                        class="fa fa-arrow-right"></i> Save</a>
                                <a type="button" class="btn btn-warning" href="initForm_permintaanpo.action"><i class="fa fa-arrow-left"></i>
                                    Back</a>
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

    function reset() {
        window.location.reload(true);
    }

    function jmlLembar(){
        var box         = $('#box').val();
        var lembarBox   = $('#lembar_box').val();

        if(lembarBox != '' && parseInt(lembarBox) > 0 && box != '' && parseInt(box) > 0){
            var lembarBox = parseInt(lembarBox) * parseInt(box);
            $('#lembar').val(lembarBox);
        }else{
            $('#lembar').val('');
        }
    }

    function jmlBiji(){

        var lembar      = $('#lembar').val();
        var bijiLembar  = $('#biji_lembar').val();

        if (lembar != '' && parseInt(lembar) > 0 && bijiLembar != '' && parseInt(bijiLembar) > 0){
            var jmlBiji = parseInt(lembar) * parseInt(bijiLembar);
            $('#biji').val(jmlBiji);
        }else{
            $('#biji').val('');
        }
    }

    function confirm(){
        var data        = $('#tabel_po').tableToJSON();
        var stringData  = JSON.stringify(data);
        var vendor      = $('#nama_vendor').val();
        if(stringData != '[]' && vendor != ''){
            $('#confirm_dialog').dialog('open');
        }else{
            $('#warning_po').show().fadeOut(5000);
            $('#msg_po').text('Silahkan cek kembali data inputan...!');
        }
    }

    function resetField(){
        $('#box, #lembar_box, #lembar, #biji_lembar, #biji, #harga').val('');
    }

    function addToListPo() {

        var vendor = $('#nama_vendor').val();
        var obat = $('#nama_obat').val();
        var box = $('#box').val();
        var lembarBox = $('#lembar_box').val();
        var lembar = $('#lembar').val();
        var bijiLembar = $('#biji_lembar').val();
        var biji = $('#biji').val();
        var harga = $('#harga').val();
        var data = $('#tabel_po').tableToJSON();

        var idObat = "";
        var namaObat = "";
        var qtyObat = "";

        var cek = false;

        if (obat != '' && vendor != '') {
            idObat = obat.split('|')[0];
            namaObat = obat.split('|')[1];
            qtyObat = obat.split('|')[2];

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
                        '<td>' + box + '</td>' +
                        '<td>' + lembarBox + '</td>' +
                        '<td>' + lembar + '</td>' +
                        '<td>' + bijiLembar + '</td>' +
                        '<td>' + biji + '</td>' +
                        '<td align="right">' + harga + '</td>' +
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
        $('#confirm_dialog').dialog('close');
        var data        = $('#tabel_po').tableToJSON();
        var stringData  = JSON.stringify(data);
        var vendor      = $('#nama_vendor').val();
        $('#waiting_dialog').dialog('open');
            dwr.engine.setAsync(true);
            PermintaanVendorAction.savePermintaanPO(vendor, stringData, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#waiting_dialog').dialog('close');
                        $('#info_dialog').dialog('open');
                    } else {
                        $('#warning_po').show().fadeOut(5000);
                        $('#msg_po').text('Terjadi kesalahan saat penyimpanan data...!');
                    }
                }
            });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>