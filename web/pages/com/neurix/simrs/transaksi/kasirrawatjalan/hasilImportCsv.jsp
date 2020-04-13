<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type="text/javascript">
        function callSearch2() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }
        function link(){
            window.location.href="<s:url action='initForm_createfpk'/>";
        }

        $(document).ready(function () {
            $('#sortTable2').DataTable({
                "order": [[1, "desc"]],
                "columnDefs": [
                    { "orderable": false, "targets": 0 }
                ]
            });
            window.close = function () {
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href = "<s:url action='search_absensi.action'/>";
            };
            window.close2 = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
            };
            $.subscribe('beforeProcessSave', function (event, data) {
                var noFpk = $('#noFpk').val();
                var tanggal = $('#tanggal_fpk').val();
                var jumlahbiayabpjskurangrs=parseInt($('#jumlah_bpjs_kd_rs').val());
                var jumlahbiayabpjslebihrs=parseInt($('#jumlah_bpjs_ld_rs').val());
                var jumlahbiayabpjssdrs=parseInt($('#jumlah_bpjs_sd_rs').val());
                if (noFpk!=''&&tanggal!='') {
                    if (jumlahbiayabpjskurangrs==0&&jumlahbiayabpjslebihrs==0&&jumlahbiayabpjssdrs==0){
                        event.originalEvent.options.submit = false;
                        var msg='<strong>Tidak ada data yang valid</strong>' + '<br/>';
                        document.getElementById('errorValidationMessage').innerHTML = msg;
                        $.publish('showErrorValidationDialog');
                    } else{
                        if (confirm('Do you want to proses this record?')) {
                            event.originalEvent.options.submit = true;
                            $.publish('showDialog');
                        } else {
                            event.originalEvent.options.submit = false;
                        }
                    }
                }else{
                    event.originalEvent.options.submit = false;
                    var msg="";
                    if ( noFpk == '') {
                        msg += 'Field <strong>No. FPK</strong> is required.' + '<br/>';
                    }
                    if ( tanggal == '') {
                        msg += 'Field <strong>Tanggal</strong> is required.' + '<br/>';
                    }
                    document.getElementById('errorValidationMessage').innerHTML = msg;
                    $.publish('showErrorValidationDialog');
                }
            });
            $.subscribe('successDialog', function (event, data) {
                if (event.originalEvent.request.status == 200) {
                    jQuery(".ui-dialog-titlebar-close").hide();
                    $.publish('showInfoDialog');
                }
            });
            $.subscribe('errorDialog', function (event, data) {
                document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
                $.publish('showErrorDialog');
            });

            function cancelBtn() {
                $('#view_dialog_menu').dialog('close');
            }
        });
    </script>

</head>

<body class="hold-transition skin-blue sidebar-mini">
<%@ include file="/pages/common/headerNav.jsp" %>
<ivelincloud:mainMenu/>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            FPK (Form Pengajuan Klaim) Pasien BPJS
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-save"></i> FPK</h3>
                    </div>
                    <s:form id="saveImportCsv" enctype="multipart/form-data" method="post" namespace="/createfpk"
                            action="saveImportCsv_createfpk.action" theme="simple">
                        <div class="box-body">
                            <div class="row">
                                <div class="form-group">
                                    <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">No. FPK</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="noFpk" cssStyle="margin-top: 7px"
                                                     name="klaimFpkDTO.noFpk" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-sm-offset-2 col-sm-3" style="margin-top: 7px">Tanggal</label>
                                    <div class="col-md-4">
                                        <div class="input-group date" style="margin-top: 7px" id="stTanggalFpk">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input class="form-control datepicker2" id="tanggal_fpk" name="klaimFpkDTO.stTanggalFpk">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-header with-border"></div>
                                    <div class="box-header with-border">
                                        <h3 class="box-title"><i class="fa fa-th-list"></i> Data Summary</h3>
                                    </div>
                                    <div class="box-body">
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">Jumlah SEP Sudah Di Klam </label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="klaimFpkDTO.jumlahSudahDiKlaim" id="sep_sudah_diklaim"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">Jumlah Data Tidak Ada</label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="klaimFpkDTO.jumlahSepTidakAda" cssStyle="margin-top: 7px" id="data_sudah_ada"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">Jumlah Biaya BPJS < Biaya RS </label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="klaimFpkDTO.jumlahBiayaBpjsKurangDariRs" cssStyle="margin-top: 7px" id="jumlah_bpjs_kd_rs"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">Jumlah Biaya BPJS > Biaya RS </label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="klaimFpkDTO.jumlahBiayaBpjsLebihDariRs" cssStyle="margin-top: 7px" id="jumlah_bpjs_ld_rs"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">Jumlah Biaya BPJS = Biaya RS </label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="klaimFpkDTO.jumlahBiayaBpjsSamaDenganRs" cssStyle="margin-top: 7px" id="jumlah_bpjs_sd_rs"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">Jumlah Data Salah </label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="klaimFpkDTO.jumlahSalah" cssStyle="margin-top: 7px" id="jumlah_data_salah"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">Jumlah Biaya Dari BPJS </label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="klaimFpkDTO.stJumlahSeluruhnyaBpjs" id="jumlah_biaya_dari_bpjs"
                                                                 readonly="true" cssClass="form-control" cssStyle="text-align: right" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">Jumlah Biaya Dari RS </label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="klaimFpkDTO.stJumlahSeluruhnya" cssStyle="margin-top: 7px;text-align: right" id="jumlah_biaya_dari_rs"
                                                                 readonly="true" cssClass="form-control"  />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-offset-5">
                                <br>
                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary"
                                           formIds="saveImportCsv" id="save" name="save"
                                           onBeforeTopics="beforeProcessSave"
                                           onCompleteTopics="closeDialog,successDialog"
                                           onSuccessTopics="successDialog"
                                           onErrorTopics="errorDialog">
                                    <i class="fa fa-save"></i>
                                    Save
                                </sj:submit>
                                <a type="button" class="btn btn-danger" href="initForm_createfpk.action">
                                    <i class="fa fa-arrow-left"></i> Go Back
                                </a>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-header with-border"></div>
                                    <div class="box-header with-border">
                                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar SEP Klaim</h3>
                                    </div>
                                    <div class="box-body">
                                        <table id="sortTable2" class="table table-bordered table-striped">
                                            <thead>
                                            <tr bgcolor="#90ee90">
                                                <td>ID</td>
                                                <td>No. SEP</td>
                                                <td>ID Pasien</td>
                                                <td>Nama Pasien</td>
                                                <td>Total Biaya ( dari RS )</td>
                                                <td>Total Biaya ( dari BPJS )</td>
                                                <td>Keterangan</td>
                                                <td align="center">Action</td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <s:iterator value="#session.listOfImportCsv" var="row">
                                                <tr>
                                                    <td><s:property value="idDetailCheckup"/></td>
                                                    <td><s:property value="noSep"/></td>
                                                    <td><s:property value="idPasien"/></td>
                                                    <td><s:property value="namaPasien"/></td>
                                                    <td style="text-align: right"><s:property value="stTotalBiaya" /></td>
                                                    <td style="text-align: right"><s:property value="stTotalBiayaBpjs"/></td>
                                                    <td>
                                                        <s:if test='#row.statusBayar == "SB"'>
                                                            <label class="label label-danger"> Data SEP sudah di klaim</label>
                                                        </s:if>
                                                        <s:elseif test='#row.statusBayar == "N"'>
                                                            <label class="label label-danger"> Data SEP tidak ada</label>
                                                        </s:elseif>
                                                        <s:elseif test='#row.statusBayar == "KB"'>
                                                            <label class="label label-warning"> Biaya BPJS < Biaya RS</label>
                                                        </s:elseif>
                                                        <s:elseif test='#row.statusBayar == "LB"'>
                                                            <label class="label label-success"> Biaya BPJS > Biaya RS</label>
                                                        </s:elseif>
                                                        <s:elseif test='#row.statusBayar == "P"'>
                                                            <label class="label label-success"> Biaya BPJS = Biaya RS</label>
                                                        </s:elseif>
                                                        <s:else>
                                                            <label class="label label-default"> Kesalahan data</label>
                                                        </s:else>
                                                    </td>
                                                    <td  align="center">
                                                        <s:if test='#row.statusBayar != "N"'>
                                                            <img onclick="detailCheckup('<s:property value="idDetailCheckup"/>','<s:property value="noSep"/>','<s:property value="idPasien"/>','<s:property value="namaPasien"/>','<s:property value="stTotalBiaya"/>','<s:property value="stTotalBiayaBpjs"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-search-25.png"/>" style="cursor: pointer;">
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                            </tbody>
                                        </table>
                                        <div class="box-header with-border"></div>
                                    </div>
                                    <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog"
                                               modal="true"
                                               resizable="false"
                                               height="250" width="600" autoOpen="false" title="Importing ...">
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

                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                              'OK':function() {
                                                                        callSearch2();
                                                                      link();
                                                                   }
                                                            }"
                                    >
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                        FPK has been successfully created.
                                    </sj:dialog>

                                    <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                               height="250" width="600" autoOpen="false" title="Error Dialog"
                                               buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); }
                                                                    }"
                                    >
                                        <div class="alert alert-error fade in">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                            </label>
                                        </div>
                                    </sj:dialog>

                                    <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                               height="280" width="500" autoOpen="false" title="Warning"
                                               buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                    >
                                        <div class="alert alert-error fade in">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                <br/>
                                                <center><div id="errorValidationMessage"></div></center>
                                            </label>
                                        </div>
                                    </sj:dialog>
                                </div>
                            </div>
                        </div>
                    </s:form>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>
<div class="modal fade" id="modal-detail-checkup">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail Checkup</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped">
                            <tr>
                                <td><b>ID Checkup</b></td>
                                <td><span id="det_id_detail_checkup"></span></td>
                            </tr>
                            <tr>
                                <td><b>ID Pasien</b></td>
                                <td><span id="det_id_pasien"></span></td>
                            </tr>
                            <tr>
                                <td><b>Nama Pasien</b></td>
                                <td><span id="det_nama_pasien"></span></td>
                            </tr>
                            <tr>
                                <td><b>No. SEP</b></td>
                                <td><span id="det_no_sep"></span></td>
                            </tr>
                            <tr>
                                <td><b>Total Biaya RS</b></td>
                                <td><span id="det_total_biaya_rs"></span></td>
                            </tr>
                            <tr>
                                <td><b>Total Biaya BPJS</b></td>
                                <td><span id="det_total_biaya_bpjs"></span></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <td>Poli</td>
                    <td>Nama Dokter</td>
                    <td>Nama Tindakan</td>
                    <td>Biaya</td>
                    </thead>
                    <tbody id="body_detail">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    function detailCheckup(idDetailCheckup,noSep,idPasien,namaPasien,totalBiayaRs,totalBiayaBpjs){
        $('#det_id_detail_checkup').text(idDetailCheckup);
        $('#det_id_pasien').text(idPasien);
        $('#det_nama_pasien').text(namaPasien);
        $('#det_no_sep').text(noSep);
        $('#det_total_biaya_rs').text(totalBiayaRs);
        $('#det_total_biaya_bpjs').text(totalBiayaBpjs);
        $('#body_detail').html('');

        var table = "";
        KasirRawatJalanAction.getRiwayatTindakanDanDokter(idDetailCheckup, function (response) {
            if(response.length > 0){
                $.each(response, function (i, item) {
                    table += '<tr>' +
                        '<td>'+item.namaPoli+'</td>'+
                        '<td>'+item.namaDokter+'</td>'+
                        '<td>'+item.namaTindakan+'</td>'+
                        '<td>'+item.stTotalTarif+'</td>'+
                        '</tr>'
                });

                $('#body_detail').html(table);
            }else{

            }
        });

        $('#modal-detail-checkup').modal({show:true, backdrop:'static'});
    }
    function selectPembayaran(){
        var option = '<option value="">[Select One]</option>';
        KasirRawatJalanAction.getListPembayaran(function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.coa+'">'+item.pembayaranName+'</option>';
                });
                $('#bank').html(option);
            }else{
                $('#bank').html(option);
            }
        });
    }

    $('document').ready(function () {
        selectPembayaran();
    })
</script>