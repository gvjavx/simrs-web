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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {

            $('#retur_obat').addClass('active');

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
            });

            $('.selectedId').change(function () {
                var check = ($('.selectedId').filter(":checked").length == $('.selectedId').length);
                $('#selectall').prop("checked", check);

                var checkbox = document.getElementsByName('selectedId');

                var ln = 0;
                for(var i=0; i< checkbox.length; i++) {
                    if(checkbox[i].checked)
                        ln++
                }
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
            Retur Obat
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
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_retur">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_retur"></p>
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <label class="col-md-2" style="margin-top: 7px">Nama Vendor</label>
                                <div class="col-md-3">
                                    <s:action id="initVendor" namespace="/permintaanpo"
                                              name="getComboVendor_permintaanpo"/>
                                    <s:select cssStyle="margin-top: 7px; width: 100%"
                                              list="#initVendor.listOfVendor" id="nama_vendor"
                                              name="headerCheckup.idPelayanan" listKey="idVendor"
                                              listValue="namaVendor"
                                              onchange="var warn =$('#war_po_vendor').is(':visible'); if (warn){$('#cor_po_vendor').show().fadeOut(3000);$('#war_po_vendor').hide()};"
                                              headerKey="" headerValue="[Select one]"
                                              cssClass="form-control select2"/>
                                    <p style="color: red; display: none;"
                                       id="war_po_vendor"><i class="fa fa-times"></i> required</p>
                                    <p style="color: green; display: none;"
                                       id="cor_po_vendor"><i class="fa fa-check"></i> correct</p>
                                </div>
                                <div class="col-md-4">
                                    <button type="button" style="margin-top: 7px" class="btn btn-success" onclick="searchObat($('#nama_vendor').val())">
                                        <i class="fa fa-search"></i> Search
                                    </button>
                                    <button type="button" style="margin-top: 7px" class="btn btn-danger" onclick="window.location.reload(true)">
                                        <i class="fa fa-refresh"></i> Reset
                                    </button>
                                </div>
                            </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">

                                        <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Saving ...">
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
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Obat</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered" id="tabel_retur">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td align="center" width="7%">
                                    <div class="form-check">
                                        <input type="checkbox" id="selectall">
                                        <label for="selectall"></label>
                                    </div>
                                </td>
                                <td>ID Barang</td>
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td>Qty Box</td>
                                <td>Expired Date</td>
                                <td>Min Stok</td>
                            </tr>
                            </thead>
                            <tbody id="body_retur">
                            </tbody>
                        </table>
                        <div class="box-header with-border"></div>
                        <div class="row">
                            <div class="col-md-4"><i class="fa fa-square" style="color: #eea236"></i> Expired Date Kurang dari 30 hari</div>
                            <div class="col-md-4"><i class="fa fa-square" style="color: #dd4b39"></i> Expired Date Kurang dari 10 hari</div>
                            <div class="col-md-4"><i class="fa fa-square" style="color: #ccc"></i> Expired Date Telah Habis</div>
                        </div>
                        <div class="box-header with-border"></div>
                        <div class="row" style="margin-top: 20px">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-md-2">Tanggal Retur</label>
                                    <div class="col-md-3">
                                        <input class="form-control datepicker2 datemask2" id="tgl_retur">
                                    </div>
                                    <div class="col-md-4">
                                        <a href="initForm_returobat.action" class="btn btn-warning"><i class="fa fa-times"></i> Back</a>
                                        <button class="btn btn-success" onclick="confirmRetur()"><i class="fa fa-check"></i> Save</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="box-header with-border"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
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
        window.location.href = 'initForm_returobat.action';
    }

    function confirmRetur(){
        var data = $('#tabel_retur').tableToJSON();
        var tgl = $('#tgl_retur').val();
        if(data.length > 0 && tgl != ''){
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick','saveRetur()');
        }else{
            $('#warning_retur').show().fadeOut(5000);
            $('#msg_retur').text("Silahkan cek kembali inputan anda...!");
        }
    }

    function saveRetur() {
        $('#modal-confirm-dialog').modal('hide');
        var data = $('#tabel_retur').tableToJSON();
        var idVendor = $('#nama_vendor').val();
        var tglRetur = $('#tgl_retur').val();
        var result = [];
        var totalQty = 0;
        var obj = "";
        var tanggal = tglRetur.split("-").reverse().join("-");
        var today = new Date();
        var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();

        $.each(data, function (i, item) {
            var idBarang = data[i]["ID Barang"];
            var idObat = data[i]["ID Obat"];
            var qty = data[i]["Qty Box"];
            if ($('#check_' + i).prop("checked") == true) {
                result.push({'id_barang': idBarang, 'id_obat': idObat, 'qty': qty});
                totalQty = parseInt(totalQty) + parseInt(qty);
            }
        });

        obj = {
            'id_vendor':idVendor,
            'tgl_retur':tanggal +" "+time,
            'qty_total':""+totalQty
        };

        var jsonString = JSON.stringify(result);
        var jsonObject = JSON.stringify(obj);

        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        ObatAction.saveReturObat(jsonString, jsonObject, {callback :function (response) {
            if(response.status == "success"){
                $('#waiting_dialog').dialog('close');
                $('#info_dialog').dialog('open');
            }else{
                $('#waiting_dialog').dialog('close');
                $('#error_dialog').dialog('open');
                $('#errorMessage').text(response.message);
            }
        }});
    }

    function searchObat(idVendor){

        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        today = mm + '-' + dd + '-' + yyyy;

        ObatAction.returObatByVendor(idVendor, function (response) {
            var table = "";
            if(response.length > 0){
                $.each(response, function (i, item) {
                    var dateExp = $.datepicker.formatDate('mm-dd-yy', new Date(item.expiredDate));

                    const date1 = new Date(today);
                    const date2 = new Date(dateExp);
                    const diffTime = Math.abs(date2 - date1);
                    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                    var warna = "";
                    var color = "";

                    if(Math.abs(date1) > Math.abs(date2)){
                        warna = '#ccc';
                        color = '#fff';

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

                    var dateExp2 = $.datepicker.formatDate('mm-dd-yy', new Date(item.expiredDate));
                    table += '<tr bgcolor="' + warna +'" style="color:' + color +'">' +
                        '<td align="center">' +
                        '<div class="form-check">'+
                            '<input type="checkbox" class="selectedId" name="selectedId" id="check_'+i+'">'+
                                '<label for="check_'+i+'"></label>'+
                        '</div>'+
                        '</td>' +
                        '<td>'+item.idBarang+'</td>' +
                        '<td>'+item.idObat+'</td>' +
                        '<td>'+item.namaObat+'</td>' +
                        '<td>'+item.qtyBox+'</td>' +
                        '<td>'+dateExp2+'</td>' +
                        '<td>'+item.minStok+'</td>' +
                        '</tr>';
                });
                $('#body_retur').html(table);
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>