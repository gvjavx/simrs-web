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

        *, *:before, *:after {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        .new {
            padding: 50px;
        }

        .form-check {
            display: block;
        }

        .form-check input {
            padding: 0;
            height: initial;
            width: initial;
            margin-bottom: 0;
            display: none;
            cursor: pointer;
        }

        .form-check label {
            position: relative;
            cursor: pointer;
        }

        .form-check label:before {
            content:'';
            -webkit-appearance: none;
            background-color: transparent;
            border: 2px solid #0079bf;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 10px;
            display: inline-block;
            position: relative;
            vertical-align: middle;
            cursor: pointer;
            margin-right: 5px;
        }

        .form-check input:checked + label:after {
            content: '';
            display: block;
            position: absolute;
            top: 2px;
            left: 9px;
            width: 6px;
            height: 14px;
            border: solid #0079bf;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#fpk, #pembayaran_active').addClass('active');
            $('#pembayaran_open').addClass('menu-open');

            $('#sortTable2').DataTable({
                "order": [[1, "desc"]],
                "columnDefs": [
                    { "orderable": false, "targets": 0 }
                ]
            })

            $('#selectall').click(function () {
                $('.selectedId').prop('checked', this.checked);

                var checkbox = document.getElementsByName('selectedId');
                var fpk = $('#no_fpk_search').val();

                var ln = 0;
                for(var i=0; i< checkbox.length; i++) {
                    if(checkbox[i].checked)
                        ln++
                }
                if(ln > 0){
                    $('#btn_create').show();
                }else{
                    $('#btn_create').hide();
                }

                if(fpk != '' && ln > 0){
                    $('#btn_create').hide();
                    $('#form-bayar').show();
                }else{
                    $('#form-bayar').hide();
                }

                console.log(fpk);
            });

            $('.selectedId').change(function () {
                var check = ($('.selectedId').filter(":checked").length == $('.selectedId').length);
                $('#selectall').prop("checked", check);

                var checkbox = document.getElementsByName('selectedId');
                var fpk = $('#no_fpk_search').val();

                var ln = 0;
                for(var i=0; i< checkbox.length; i++) {
                    if(checkbox[i].checked)
                        ln++
                }
                if(ln > 0){
                    $('#btn_create').show();
                }else{
                    $('#btn_create').hide();
                }

                if(fpk != '' && ln > 0){
                    $('#btn_create').hide();
                    $('#form-bayar').show();
                }else{
                    $('#form-bayar').hide();
                }
                console.log(fpk);
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
            FPK (Form Penganjuan Klaim) Pasien BPJS
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian FPK Pasien Bpjs</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="createfpkForm" method="post" namespace="/createfpk"
                                    action="searchFPK_createfpk.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">NO FPK</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_fpk_search" cssStyle="margin-top: 7px"
                                                     name="headerDetailCheckup.noFpk" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Poli</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initComboPoli.listOfPelayanan"
                                                  name="headerDetailCheckup.idPelayanan" listKey="idPelayanan"
                                                  listValue="namaPelayanan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2" theme="simple"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'2':'Rujuk','3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status"
                                                  headerKey="3" headerValue="Selesai"
                                                  cssClass="form-control select2" disabled="true"/>
                                        <s:hidden name="headerDetailCheckup.statusPeriksa" value="3"></s:hidden>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status Bayar</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'Y':'Sudah Dibayar'}" cssStyle="margin-top: 7px"
                                                  id="statusBayar"
                                                  headerKey="" headerValue="Belum Dibayar"
                                                  name="headerDetailCheckup.statusBayar"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="headerDetailCheckup.stDateFrom"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="headerDetailCheckup.stDateTo"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="createfpkForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_createfpk.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a class="btn btn-primary" id="btn_create" style="display: none" onclick="createFPK()"><i class="fa fa-plus"></i>
                                            Create FPK</a>
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
                                                <img class="spin" border="0"
                                                     style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>
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
                                            <s:hidden id="close_val"></s:hidden>
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
                                        </sj:dialog>
                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close'); }
                                                                            }"
                                        >
                                            <div class="alert alert-danger alert-dismissible">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                         name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable2" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td align="center" width="7%">
                                    <div class="form-check">
                                        <input type="checkbox" id="selectall">
                                        <label for="selectall"></label>
                                    </div>
                                </td>
                                <td>ID Pasien</td>
                                <td>ID Detail Checkup</td>
                                <td>No Sep</td>
                                <td>Nama</td>
                                <%--<td>Nama Pelayanan</td>--%>
                                <td>Status</td>
                                <td>No FPK</td>
                                <%--<td align="center">Action</td>--%>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td align="center">
                                        <s:if test='#row.statusFPK == "Y"'>

                                        </s:if>
                                        <s:else>
                                            <div class="form-check">
                                                <input type="checkbox" class="selectedId" name="selectedId" id="check_<s:property value="NoSep"/>">
                                                <label for="check_<s:property value="NoSep"/>"></label>
                                            </div>
                                        </s:else>
                                    </td>
                                    <td>
                                        <input type="hidden" value="<s:property value="idFpk"/>" id="id_fpk<s:property value="NoSep"/>">
                                        <s:property value="idPasien"/>
                                    </td>
                                    <td><s:property value="idDetailCheckup"/></td>
                                    <td><s:property value="NoSep"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.noFpk != null && #row.noFpk != ""'>
                                            <label class="label label-primary"> create fpk</label>
                                        </s:if>

                                        <s:if test='#row.statusFPK == "Y"'>
                                            <label class="label label-success"> sudah bayar</label>
                                        </s:if>
                                        <s:else>
                                            <label class="label label-warning"> belum bayar</label>
                                        </s:else>
                                    </td>
                                    <td><s:property value="noFpk"/></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                        <div class="box-header with-border"></div>
                        <div id="form-bayar" style="display: none">
                        <div class="row" style="margin-top: 10px">
                            <div class="col-md-offset-3 col-md-6">
                                <div class="form-group">
                                    <label class="col-md-2" style="margin-top: 7px">Bank</label>
                                    <div class="col-md-10">
                                        <select class="form-control" id="bank">
                                            <option value="" >[Select One]</option>
                                            <option value="bri">BRI</option>
                                            <option value="bni">BNI</option>
                                            <option value="bca">BCA</option>
                                            <option value="mandiri">Mandiri</option>
                                        </select>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-2" style="margin-top: 7px">No Kartu</label>--%>
                                    <%--<div class="col-md-10">--%>
                                        <%--<input type="number" id="no_rekening" style="margin-top: 7px" class="form-control">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="col-md-2" style="margin-top: 7px">No Slip</label>
                                    <div class="col-md-10">
                                        <input type="number" id="no_slip" style="margin-top: 7px" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2" style="margin-top: 7px">&nbsp;</label>
                                    <div class="col-md-10">
                                        <button class="btn btn-success" onclick="confirmPembayaran()"  style="margin-top: 7px"><i class="fa fa-check"></i> Save</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-create-fpk">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Created FPK</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fpk">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">NO FPK</label>
                        <div class="col-md-7">
                            <input class="form-control" id="no_fpk"
                                   oninput="var warn =$('#war_no_fpk').is(':visible'); if (warn){$('#cor_no_fpk').show().fadeOut(3000);$('#war_no_fpk').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_no_fpk">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_no_fpk"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <input id="data_fpk" type="hidden">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                        <div class="col-md-7">
                            <div class="input-group date" style="margin-top: 7px" id="st_tgl_lahir">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control datepicker" id="tanggal_fpk"
                                       onchange="var warn =$('#war_tanggal').is(':visible'); if (warn){$('#cor_tanggal').show().fadeOut(3000);$('#war_tanggal').hide()}">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_tanggal">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_tanggal"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fin" onclick="confirmsSaveFpk()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_fin"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function toContent(){
        var cos = $('#close_pos').val();
        var data = $('#close_val').val();

        if(cos == 1){
            $('#no_fpk_search').val(data);
            document.createfpkForm.action = 'searchFPK_createfpk.action';
            document.createfpkForm.submit();
        }else{
            window.location.reload(true);
        }
    }

    function createFPK() {
        var data = $('#sortTable2').tableToJSON();
        var result = [];
        $.each(data, function (i, item) {
            var sep = data[i]["No Sep"];
            var idDetail = data[i]["ID Detail Checkup"];
            if ($('#check_' + sep).prop("checked") == true) {
                result.push({'no_sep': sep, 'id_detail_checkup': idDetail});
            }
        });
        var jsonString = JSON.stringify(result);
        $('#data_fpk').val(jsonString);
        $('#modal-create-fpk').modal('show');
        console.log(result);
    }

    function confirmsSaveFpk(){
        var data = $('#data_fpk').val();
        var noFpk = $('#no_fpk').val();
        var tanggal = $('#tanggal_fpk').val();
        if (data != '[]' && noFpk != '' && tanggal != '') {
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick','saveFPK()');
        }else{
            $('#warning_fpk').show().fadeOut(5000);
            $('#msg').text("Silahakan periksa kembali inputan anda..!");
            if(noFpk == ''){
                $('#war_no_fpk').show();
            }
            if(tanggal == ''){
                $('#war_tanggal').show();
            }
        }
    }

    function saveFPK() {
        $('#modal-confirm-dialog').modal('hide');
        var data = $('#data_fpk').val();
        var noFpk = $('#no_fpk').val();
        var tanggal = $('#tanggal_fpk').val();
            $('#save_fin').hide();
            $('#load_fin').show();
            dwr.engine.setAsync(true);
            KasirRawatJalanAction.saveNoFPK(data, noFpk, tanggal, {callback: function (response) {
                if (response.status == "success") {
                    $('#modal-create-fpk').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(1);
                    $('#close_val').val(noFpk);
                    $('#save_fin').show();
                    $('#load_fin').hide();
                } else {
                    $('#save_fin').show();
                    $('#load_fin').hide();
                    $('#warning_fpk').show().fadeOut(5000);
                    $('#msg').text(response.msg);
                }
                }
            });
    }

    function confirmPembayaran(){
        $('#modal-confirm-dialog').modal('show');
        $('#save_con').attr('onclick','savePembayaranFPK()');
    }

    function savePembayaranFPK() {
        var data = $('#sortTable2').tableToJSON();
        var result = [];
        $.each(data, function (i, item) {
            var sep = data[i]["No Sep"];
            var idDetail = data[i]["ID Detail Checkup"];
            var idPasien = data[i]["ID Pasien"];
            var noFpk = data[i]["No FPK"];
            var idFpk = $('#id_fpk'+sep).val();
            if ($('#check_' + sep).prop("checked") == true) {
                result.push({'id_fpk': idFpk, 'no_sep': sep, 'id_detail_checkup': idDetail, "id_pasien":idPasien, "no_fpk":noFpk});
            }
        });
        var jsonString = JSON.stringify(result);
        var noSlip = $('#no_slip').val();
        var bank = $('#bank').val();
        var noRekening = "";

        if (data != '[]' && noSlip != '' && bank != '') {
            $('#modal-confirm-dialog').modal('hide');
            $('#waiting_dialog').dialog('open');
            dwr.engine.setAsync(true)
            KasirRawatJalanAction.savePembayaranFPK(jsonString, noSlip, bank, noRekening, {callback: function (response) {
                if (response.status == "success") {
                    $('#waiting_dialog').dialog('close');
                    $('#info_dialog').dialog('open');
                } else {
                    $('#waiting_dialog').dialog('close');
                    $('#errorMessage').text(response.msg);
                    $('#error_dialog').dialog('open');
                }
                }
            });
        }else{
            $('#modal-confirm-dialog').modal('hide');
            $('#error_dialog').dialog('open');
            $('#errorMessage').text("Silahkan Periksa inputan di atas..!");
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>