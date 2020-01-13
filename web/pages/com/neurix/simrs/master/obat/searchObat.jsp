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
            $('#menu_obat').addClass('active');
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Obat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="obatForm" method="post" namespace="/obat" action="search_obat.action" theme="simple" cssClass="form-horizontal">
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">ID Obat</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:textfield id="id_obat" cssStyle="margin-top: 7px"--%>
                                                     <%--name="obat.idObat" required="false"--%>
                                                     <%--readonly="false" cssClass="form-control"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Jenis Obat</label>
                                    <div class="col-sm-4">
                                        <s:action id="initJenis" namespace="/jenisobat"
                                                  name="getListJenisObat_jenisobat"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initJenis.listOfJenisObat" id="obat_jenis_obat"
                                                  listKey="idJenisObat"
                                                  listValue="namaJenisObat"
                                                  name="obat.idJenisObat"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Obat</label>
                                    <div class="col-sm-4">
                                        <s:action id="initObat" namespace="/obat"
                                                  name="getListObat_obat"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initObat.listOfObat" id="nama_obat"
                                                  listKey="idObat"
                                                  listValue="namaObat"
                                                  name="obat.idObat"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                        <%--<s:textfield id="nama_pasien" name="obat.namaObat"--%>
                                                     <%--required="false" readonly="false"--%>
                                                     <%--cssClass="form-control" cssStyle="margin-top: 7px"/>--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select cssClass="form-control" cssStyle="margin-top: 7px" list="#{'N':'Non Active'}" headerKey="Y" headerValue="Active" name="obat.flag"/>
                                    </div>
                                </div>

                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="obatForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <button type="button" class="btn btn-primary" onclick="showModal()">
                                            <i class="fa fa-plus"></i> Tambah Obat
                                        </button>
                                        <a type="button" class="btn btn-danger" href="initForm_obat.action">
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Obat</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td>Jenis Obat</td>
                                <td>Lembar/Box</td>
                                <td>Biji/Lembar</td>
                                <td>Stok Box</td>
                                <td>Stok Lembar</td>
                                <td>Stok Biji</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfPeriksaLab">
                                <tr>
                                    <td><s:property value="idObat"/></td>
                                    <td><s:property value="namaObat"/></td>
                                    <td><s:property escape="false" value="jenisObat"/></td>
                                    <td><s:property value="lembarPerBox"/></td>
                                    <td><s:property value="bijiPerLembar"/></td>
                                    <td><s:property value="qtyBox"/></td>
                                    <td><s:property value="qtyLembar"/></td>
                                    <td><s:property value="qtyBiji"/></td>
                                    <td align="center">
                                        <img border="0" onclick="editObat('<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="flag"/>','<s:property value="qtyBox"/>','<s:property value="qtyLembar"/>','<s:property value="qtyBiji"/>','<s:property value="lembarPerBox"/>','<s:property value="bijiPerLembar"/>','<s:property value="averageHargaBox"/>','<s:property value="averageHargaLembar"/>','<s:property value="averageHargaBiji"/>','<s:property value="idPabrik"/>','<s:property value="merk"/>')" class="hvr-grow" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">
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
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:textfield onkeypress="var warn =$('#war_nama').is(':visible'); if (warn){$('#cor_nama').show().fadeOut(3000);$('#war_nama').hide()}"
                                    type="text" cssClass="form-control" id="add_nama_obat"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_nama"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_nama">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>
                        <div class="col-md-7">
                            <s:action id="initJenisObat" namespace="/jenisobat"
                                      name="getListJenisObat_jenisobat"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn =$('#war_jenis').is(':visible'); if (warn){$('#cor_jenis').show().fadeOut(3000);$('#war_jenis').hide()}"
                                      list="#initJenisObat.listOfJenisObat" id="add_jenis_obat"
                                      listKey="idJenisObat"
                                      listValue="namaJenisObat"
                                      cssClass="form-control select2" multiple="true"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_jenis"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_jenis"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">ID Pabrik</label>
                        <div class="col-md-7">
                            <s:textfield type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_pabrik"
                                         onkeypress="var warn =$('#war_pabrik').is(':visible'); if (warn){$('#cor_pabrik').show().fadeOut(3000);$('#war_pabrik').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_pabrik"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_pabrik"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Merek</label>
                        <div class="col-md-7">
                            <s:textfield type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_merek"
                                         onkeypress="var warn =$('#war_merek').is(':visible'); if (warn){$('#cor_merek').show().fadeOut(3000);$('#war_merek').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_merek"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_merek"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Box</label>
                        <div class="col-md-3">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_box"
                                         onkeypress="$(this).css('border', '')"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="margin-top: 10px">Lembar/Box</p>
                        </div>
                        <div class="col-md-3">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_lembar_box"
                                         onkeypress="$(this).css('border', '')"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Lembar</label>
                        <div class="col-md-3">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_lembar"
                                         onkeypress="$(this).css('border', '')"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="margin-top: 10px">Biji/Lembar</p>
                        </div>
                        <div class="col-md-3">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_biji_lembar"
                                         onkeypress="$(this).css('border', '')"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jml Biji</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_biji"
                                         onkeypress="var warn =$('#war_biji').is(':visible'); if (warn){$('#cor_biji').show().fadeOut(3000);$('#war_biji').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_biji"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_biji"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Harga/Box</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_harga_box"
                                         onkeypress="var warn =$('#war_harga_box').is(':visible'); if (warn){$('#cor_harga_box').show().fadeOut(3000);$('#war_harga_box').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_harga_box"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_harga_box"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Harga/Lembar</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_harga_lembar"
                                         onkeypress="var warn =$('#war_harga_lembar').is(':visible'); if (warn){$('#cor_harga_lembar').show().fadeOut(3000);$('#war_harga_lembar').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_harga_lembar"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_harga_lembar"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Harga/Biji</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_harga_biji"
                                         onkeypress="var warn =$('#war_harga_biji').is(':visible'); if (warn){$('#cor_harga_biji').show().fadeOut(3000);$('#war_harga_biji').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_harga_biji"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_harga_biji"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat" ><i class="fa fa-arrow-right"></i> Save
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
        var hargalembar = $('#add_harga_lembar').val();
        var hargaBiji   = $('#add_harga_biji').val();
        var flag        = $('#add_flag').val();

        if (nama != '' && jenis != null && harga != '' && parseInt(biji) > 0 && box != ''
            && lembarBox != '' && lembar != '' && bijiLembar != '' && biji != '' && pabrik != ''
            && merek != '' && pabrik != '') {

            $('#save_obat').hide();
            $('#load_obat').show();

            if (id != '') {
                dwr.engine.setAsync(true);
                ObatAction.editObat(id, nama, jenis, harga, biji, flag, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                    } else {
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#warning_obat').show().fadeOut(5000);
                        $('#obat_error').text("Terjadi kesalahan ketika proses simpan ke database..!");
                    }
                })
            } else {
                dwr.engine.setAsync(true);
                ObatAction.saveObat(nama, jenis, merek, pabrik, box, lembarBox, lembar, bijiLembar, biji, harga, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
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
        }
    }

    function editObat(id, nama, flag, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerBiji, hargaBox, hargaLembar, hargaBiji, idPbarik, mrek) {
        $('#load_obat, #war_nama, #war_jenis, #war_harga, #war_stok').hide();
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
        $('#add_nama_obat').val(nama);
        $('#add_jenis_obat').val(listSelectObatEdit(id)).trigger('change');
        $('#add_merek').val(mrek);
        $('#add_pabrik').val(idPbarik);
        $('#add_box').val(qtyBox);
        $('#add_lembar_box').val(lembarPerBox);
        $('#add_lembar').val(qtyLembar);
        $('#add_biji_lembar').val(bijiPerBiji);
        $('#add_biji').val(qtyBiji);
        $('#add_harga_box').val(hargaBox);
        $('#add_harga_lembar').val(hargaLembar);
        $('#add_harga_biji').val(hargaBiji);
//        $('#add_stok').val(stok);
        $('#add_flag').val(flag);
        $('#modal-obat').modal('show');
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
        console.log(data);
        return data;
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>