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
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TransaksiObatAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#permintaan_obat').addClass('active');
        });

    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Permintaan Obat Poli
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Form Transaksi Obat Apotek</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="permintaanForm" method="post" namespace="/transaksi" action="search_transaksi.action"
                                    theme="simple" cssClass="form-horizontal">

                                <div class="form-group">
                                    <label class="control-label col-sm-4">No Resep</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_resep" cssStyle="margin-top: 7px"
                                                     name="transaksiObatDetail.idPermintaanResep" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="permintaanForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search Resep
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_transaksi.action">
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
                                                <img border="0" style="width: 150px; height: 150px"
                                                     src="<s:url value="/pages/images/spinner.gif"/>"
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Obat untuk Resep</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Atas Nama</td>
                                <td>Obat</td>
                                <td>Qty</td>
                                <td>Harga Satuan</td>
                                <td>Harga Total</td>
                                <%--<td align="center">Action</td>--%>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResultResep" id="listOfResultResep">
                                <tr>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="namaObat"/></td>
                                    <td><s:property value="qty"/></td>
                                    <td><s:property value="harga"/></td>
                                    <td><s:property value="totalHarga"/></td>
                                    <%--<td align="center">--%>
                                        <%--<s:if test="#listOfResultResep.approvalFlag == null">--%>
                                            <%--<s:if test="#listOfResultResep.request == true">--%>
                                                <%--<button class="btn btn btn-primary" onclick="showRequest('<s:property value="idPermintaanObatPoli"/>', '<s:property value="namaObat"/>', '<s:property value="qty"/>', '<s:property value="qtyGudang"/>', '<s:property value="namaPelayanan"/>')">Konfirmasi Request</button>--%>
                                            <%--</s:if>--%>
                                            <%--<s:else>--%>
                                                <%--<s:a href="%{edit}" cssClass="btn btn-primary">--%>
                                                    <%--Konfirmasi Reture--%>
                                                <%--</s:a>--%>
                                            <%--</s:else>--%>
                                        <%--</s:if>--%>
                                    <%--</td>--%>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Obat</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(5)"><i class="fa fa-plus"></i> Tambah Obat
                        </button>
                        <button class="btn btn-danger btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="resetobat()">
                            <i class="fa fa-refresh"></i> Reset
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Obat</td>
                                <td>Qty</td>
                                <td>Harga Satuan</td>
                                <td>Harga Total</td>
                                <%--<td align="center">Action</td>--%>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResultObat" id="listOfResultObat">
                                <tr>
                                    <td><s:property value="namaObat"/></td>
                                    <td><s:property value="qty"/></td>
                                    <td><s:property value="harga"/></td>
                                    <td><s:property value="totalHarga"/></td>
                                    <%--<td align="center">--%>
                                            <%--<s:if test="#listOfResultResep.approvalFlag == null">--%>
                                            <%--<s:if test="#listOfResultResep.request == true">--%>
                                            <%--<button class="btn btn btn-primary" onclick="showRequest('<s:property value="idPermintaanObatPoli"/>', '<s:property value="namaObat"/>', '<s:property value="qty"/>', '<s:property value="qtyGudang"/>', '<s:property value="namaPelayanan"/>')">Konfirmasi Request</button>--%>
                                            <%--</s:if>--%>
                                            <%--<s:else>--%>
                                            <%--<s:a href="%{edit}" cssClass="btn btn-primary">--%>
                                            <%--Konfirmasi Reture--%>
                                            <%--</s:a>--%>
                                            <%--</s:else>--%>
                                            <%--</s:if>--%>
                                    <%--</td>--%>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="pembayaranForm" method="post" namespace="/transaksi" action="pembayaran_transaksi.action"
                                    theme="simple" cssClass="form-horizontal">

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Total</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="total_bayar" cssStyle="margin-top: 7px"
                                                     name="transaksiObatDetail.totalBayar" required="false"
                                                     readonly="false" cssClass="form-control" readOnly="true"/>
                                        <s:hidden name="transaksiObatDetail.idPermintaanResep"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Total yang dibayar</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="total_dibayar" cssStyle="margin-top: 7px"
                                                     name="transaksiObatDetail.nominal" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kembalian</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="kembalian" cssStyle="margin-top: 7px"
                                                     name="transaksiObatDetail.kembalian" required="false"
                                                     readonly="false" cssClass="form-control" readOnly="true"/>
                                    </div>
                                </div>

                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="pembayaranForm"
                                                   id="save" name="save"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-check"></i>
                                            Bayar
                                        </sj:submit>
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
                                                <img border="0" style="width: 150px; height: 150px"
                                                     src="<s:url value="/pages/images/spinner.gif"/>"
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
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-request">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="judul"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_request-2">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="error_request-2"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="req-2_nama">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">QTY Obat Request</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="req-2_qty">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">QTY Obat Stock</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="req-2_qty_gudang">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Poli</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="req-2_poli">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_req-2"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_req-2"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-obat">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambahkan Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="row">
                    <div class="form-group" id="jenis_form">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>
                        <div class="col-md-7">
                            <s:action id="initJenis" namespace="/jenisobat"
                                      name="getListJenisObat_jenisobat"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initJenis.listOfJenisObat" id="obat_jenis_obat"
                                      listKey="idJenisObat"
                                      listValue="namaJenisObat"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_jenis_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_jenis_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group" id="nama_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="ob_id_obat"
                                    onchange="var warn =$('#war_obat').is(':visible'); if (warn){$('#cor_obat').show().fadeOut(3000);$('#war_obat').hide()}; setStokObat(this);">
                                <option value="">[select one]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_obat"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_obat">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group" id="nama_obat_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="nama_obat"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat</label>
                        <div class="col-md-7">
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="ob_stok"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <s:textfield value="1" type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="ob_qty"
                                         onkeypress="var warn =$('#war_qty_obat').is(':visible'); if (warn){$('#cor_qty_obat').show().fadeOut(3000);$('#war_qty_obat').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_qty_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_qty_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="set_id_obat">
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<script type='text/javascript'>

    function showModal(select) {
        var id = "";
        if (select == 5) {
            $('#obat_jenis_obat').attr("onchange", "var warn =$('#war_jenis_obat').is(':visible'); if (warn){$('#cor_jenis_obat').show().fadeOut(3000);$('#war_jenis_obat').hide()}; listSelectObat(this);");
            $('#obat_jenis_obat, #ob_id_obat').val('').trigger('change');
            $('#jenis_form').show();
            $('#nama_form').show();
            $('#nama_obat_form').hide();
            $('#ob_stok').val('');
            $('#ob_qty').val('1');
            $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
            $('#load_obat, #warning_obat, #war_jenis_obat, #war_obat, #war_qty_obat').hide();
            $('#modal-obat').modal('show');
        }
    }

    function listSelectObat(select) {
        var idx = select.selectedIndex;
        var idJenis = select.options[idx].value;
        var option = "<option value=''>[Select One]</option>";
        if (idJenis != '') {
            ObatAction.listObat(idJenis, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idObat + "'>" + item.namaObat + "</option>";
                    });
                } else {
                    option = option;
                }
            });
        } else {
            option = option;
        }

        $('#ob_id_obat').html(option);
    }

    function saveObat(id) {

        var idJenis     = $('#obat_jenis_obat').val();
        var idObat      = $('#ob_id_obat').val();
        var qty         = $('#ob_qty').val();
        var stok        = $('#ob_stok').val();

        if (idObat != '' && qty > 0) {

            if (qty <= stok) {
                $('#save_obat').hide();
                $('#load_obat').show();

                dwr.engine.setAsync(true);
                TransaksiObatAction.saveAddObat(idObat, qty, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
//                        listObat();
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
//                        window.location.href = "/simrs/transaksi/init_transaksi.action";
                        $('#permintaanForm').submit();
                    } else {

                    }
                })
            }else{
                $('#warning_obat').show().fadeOut(5000);
                $('#obat_error').text("Jumlah obat tidak boleh melebihi stok..!");
            }
        } else {
            $('#warning_obat').show().fadeOut(5000);
            $('#obat_error').text("Silahkan cek kembali data inputan..!");
            if (idObat == '') {
                $('#war_obat').show();
            }
            if (qty == '' || qty < 1) {
                $('#war_qty_obat').show();
            }
        }
    }

    function resetobat() {
        window.location.href = "/simrs/transaksi/resetobat_transaksi.action";
    }

    function setStokObat(select){

        var idx     = select.selectedIndex;
        var idObat  = select.options[idx].value;
        var stok    = "";

        if (idObat != '') {
            ObatAction.getStokObat(idObat, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        if(item.idObat == idObat){
                            if(item.qty != null){
                                stok = item.qty;
                            }
                        }
                    });
                }
            });
        }
        $('#ob_stok').val(stok);
    }

    function showRequest(id, obat, qty, qtygudang, namapoli){
        $('#modal-request').modal('show');
        $('#req-2_nama').val(obat);
        $('#req-2_qty_gudang').val(qtygudang);
        $('#req-2_qty').val(qty);
        $('#req-2_poli').val(namapoli);
        $('#save_req-2').attr('onclick', 'saveRequest(\'' + id + '\')').show();
    }

    function saveRequest(id) {
        $('#save_req-2').hide();
        $('#load_req-2').show();

        dwr.engine.setAsync(true);
        ObatPoliAction.saveKonfirmasiRequest(id,{
            callback: function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    $('#modal-request').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#save_req-2').show();
                    $('#load_req-2').hide();
                } else {
                    $('#warning_request-2').show().fadeOut(5000);
                    $('#error_request-2').text(response);
                    $('#save_req-2').show();
                    $('#load_req-2').hide();
                }
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>