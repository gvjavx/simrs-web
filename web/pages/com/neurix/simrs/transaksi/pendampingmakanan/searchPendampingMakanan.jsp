<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanGiziAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KeperawatanRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenGiziAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenGiziAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenRawatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CatatanTerintegrasiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PendampingMakananAction.js"/>'></script>

    <script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenrawatjalan.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/datapasien.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/nyeri.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/resikojatuh.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/gizi.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenrawatinap.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/cppt.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            $('#exampleGizi').dataTable({
                "columnDefs": [
                    {"orderable": false, "targets": 6}
                ]
            });
            $('#makanan_pendamping').addClass('active');
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
            Makanan Pendamping
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Makanan Pendamping</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="giziForm" method="post" namespace="/pendampingmakanan" action="search_pendampingmakanan.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Detail Checkup</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_detail_checkup" cssStyle="margin-top: 7px"
                                                     name="headerPendampingMakanan.idDetailCheckup" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="headerPendampingMakanan.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="headerPendampingMakanan.nama"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kelas Ruangan</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboKelas" namespace="/checkupdetail"
                                                  name="getListComboKelasRuangan_checkupdetail"/>
                                        <s:select cssStyle="margin-top: 7px"
                                                  onchange="$(this).css('border',''); listSelectRuangan(this.value)"
                                                  list="#initComboKelas.listOfKelasRuangan" id="kelas_kamar"
                                                  name="headerPendampingMakanan.idKelas"
                                                  listKey="idKelasRuangan"
                                                  listValue="namaKelasRuangan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                    <div class="col-sm-3" style="display: none;" id="load_ruang">
                                        <img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                             style="cursor: pointer; width: 45px; height: 45px"><b
                                            style="color: #00a157;">Sedang diproses...</b></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Ruangan</label>
                                    <div class="col-sm-4">
                                        <select id="ruangan_ruang" style="margin-top: 7px" class="form-control select2"
                                                id="nama_ruangan" name="headerPendampingMakanan.idRuangan">
                                            <option value=''>[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status" name="headerPendampingMakanan.status"
                                                  headerKey="0" headerValue="Antrian"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="giziForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_pendampingmakanan.action">
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
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="close_pos"></s:hidden>
                                            <s:hidden id="data_pos"></s:hidden>
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Order Makanan Pendamping</h3>
                    </div>
                    <div class="box-body">
                        <table id="exampleGizi" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>No Pesanan</td>
                                <td>ID Detail Checkup</td>
                                <td>No RM</td>
                                <td>Nama</td>
                                <td>Ruangan</td>
                                <td>Status</td>
                                <td width="5%" align="center" class="noSort">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idHeaderPendampingMakanan"/></td>
                                    <td><s:property value="idDetailCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td>
                                        <s:if test='#row.noRuangan != "" && #row.noRuangan != null'>
                                            [<s:property value="noRuangan"/>] <s:property value="namaRuangan"/>
                                        </s:if>
                                        <s:else>
                                            <s:property value="namaRuangan"/>
                                        </s:else>
                                    </td>
                                    <td>
                                        <s:if test='#row.status == "0"'>
                                            <span class="span-warning">proses</span>
                                        </s:if>
                                        <s:else>
                                            <span class="span-success">selesai</span>
                                        </s:else>
                                    </td>
                                    <td align="center">
                                        <s:if test='#row.status == "0"'>
                                            <img onclick="detailMakananPendamping('<s:property value="idHeaderPendampingMakanan"/>', '<s:property value="nama"/>', '<s:property value="noRuangan"/>', '<s:property value="namaRuangan"/>', '<s:property value="status"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                        </s:if>
                                        <s:else>
                                            <img onclick="detailMakananPendamping('<s:property value="idHeaderPendampingMakanan"/>', '<s:property value="nama"/>', '<s:property value="noRuangan"/>', '<s:property value="namaRuangan"/>', '<s:property value="status"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-search-25.png"/>" style="cursor: pointer;">
                                        </s:else>
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

<div class="modal fade" id="modal-detail_makanan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Makanan Pendamping</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="alert alert-success alert-dismissible" style="display: none" id="warning_suc_detail_makanan">
                                <h4><i class="icon fa fa-info"></i> Info!</h4>
                                <p id="msg_suc_detail_makanan"></p>
                            </div>
                            <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_detail_makanan">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <p id="msg_detail_makanan"></p>
                            </div>
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="30%">No. Pesanan</td>
                                    <td><span id="det_no_pesanan"></span></td>
                                </tr>
                                <tr>
                                    <td>Nama</td>
                                    <td><span id="det_nama"></span></td>
                                </tr>
                                <tr>
                                    <td >Ruangan</td>
                                    <td><span id="det_ruangan"></span></td>
                                </tr>
                            </table>
                            <br>
                            <table id="table_catering" class="table table-bordered" style="font-size: 12px">
                                <thead>
                                <tr>
                                    <td width="5%">No</td>
                                    <td>Nama</td>
                                    <td align="center">Qty</td>
                                    <td>Keterangan</td>
                                    <td align="center">Tarif(Rp.)</td>
                                    <td align="center">Print</td>
                                </tr>
                                </thead>
                                <tbody id="body_pendamping_makanan"></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" onclick="cekSave()" class="btn btn-success" id="save_pendamping_makanan"><i class="fa fa-check"></i> Selesai
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_pendamping_makanan"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-temp"></div>

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
                <h4 class="text-center">Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-check"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    var contextPath = '<%= request.getContextPath() %>';

    function formatRupiah(angka) {
        var reverse = angka.toString().split('').reverse().join(''),
            ribuan = reverse.match(/\d{1,3}/g);
        ribuan = ribuan.join('.').split('').reverse().join('');
        return ribuan;
    }

    function detailMakananPendamping(id, nama, noRuangan, namaRuangan, status){
        $('#det_no_pesanan').text(id);
        $('#det_nama').text(nama);
        $('#det_ruangan').text("["+noRuangan+"] "+namaRuangan);
        var table = "";
        PendampingMakananAction.listDetailPendampingMakanan(id, function (json) {
            $.each(json, function (i, item) {
                var input = '<input class="form-control tarif_makanan" id="tarif_'+item.idDetailPendampingMakanan+'" placeholder="Tarif" oninput="convertRpAtas(\'tarif_'+item.idDetailPendampingMakanan+'\', this.value, \'tarif_val_'+item.idDetailPendampingMakanan+'\')">';
                var ling = "";
                if(status != "0"){
                    input = formatRupiah(item.tarif);
                    ling = 'align="right"';
                }

                var nomor = i+1;
                table += '<tr id="'+item.idDetailPendampingMakanan+'">' +
                    '<td>'+nomor+'</td>'+
                    '<td><span id="l_nama_'+item.idDetailPendampingMakanan+'">'+item.nama+'</span>'+
                    '<input type="hidden" value="'+item.nama+'" class="form-control" id="nama_makanan_'+item.idDetailPendampingMakanan+'">'+
                    '<input type="hidden" value="'+item.idHeaderPendampingMakanan+'" class="form-control id_header_makanan" id="id_header_makanan_'+item.idHeaderPendampingMakanan+'">'+
                    '<input type="hidden" value="'+item.idDetailPendampingMakanan+'" class="form-control id_detail_makanan" id="id_detail_makanan_'+item.idDetailPendampingMakanan+'">'+
                    '</td>'+
                    '<td align="center"><span id="l_qty_'+item.idDetailPendampingMakanan+'">'+item.qty+'</span>'+
                    '<input type="hidden" value="'+item.qty+'" class="form-control" id="qty_makanan_'+item.idDetailPendampingMakanan+'">'+
                    '</td>'+
                    '<td><span id="l_keterangan_'+item.idDetailPendampingMakanan+'">'+item.keterangan+'</span>'+
                    '<input type="hidden" value="'+item.keterangan+'" class="form-control" id="keterangan_makanan_'+item.idDetailPendampingMakanan+'">'+
                    '</td>'+
                    '<td '+ling+'>' + input +
                    '<input type="hidden" class="val_tarif_makanan" id="tarif_val_'+item.idDetailPendampingMakanan+'">'+
                    '</td>' +
                    '<td align="center">'+'<a target="_blank" href="printBarcodeMakanan_pendampingmakanan.action?id='+id+'&nama='+nama+'&keterangan='+item.nama+'"><img class="hvr-grow" src="<s:url value="/pages/images/icons8-barcode-scanner-25.png"/>"></a>'+'</td>'+
                    '</tr>';
            });
            $('#body_pendamping_makanan').html(table);
        });
        $('#modal-detail_makanan').modal({show: true, backdrop: 'static'});
    }

    function cekSave(){
        var cek = false;
        var temp = $('.val_tarif_makanan');
        $.each(temp, function (i, item) {
            if(item.value == ''){
                cek = true;
            }
        });

        if(!cek){
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick', 'saveDetail()');
        }else{
            $('#warning_detail_makanan').show().fadeOut(5000);
            $('#msg_detail_makanan').text("Silahkan cek kembali inputan tarif anda..!");
            $('#modal-detail_makanan').scrollTop(0);
        }
    }

    function saveDetail(){
        $('#modal-confirm-dialog').modal('hide');
        var data = [];
        var header = $('.id_header_makanan');
        $.each(header, function (i ,item) {
            if(item.value != ''){
                data.push({
                    'id_header_makanan_pendamping': item.value,
                    'id_detail_makanan_pendamping': $('.id_detail_makanan')[i].value,
                    'tarif': $('.val_tarif_makanan')[i].value,
                });
            }
        });
        var result = JSON.stringify(data);
        $('#save_makanan_pendamping').hide();
        $('#load_makanan_pendamping').show();
        dwr.engine.setAsync(true);
        PendampingMakananAction.verifDetailPendampingMakanan(result, {
            callback: function (res) {
                if(res.status == "success"){
                    $('#modal-detail_makanan').modal('hide');
                    $('#save_makanan_pendamping').show();
                    $('#load_makanan_pendamping').hide();
                    $('#info_dialog').dialog('open');
                }else{
                    $('#save_makanan_pendamping').show();
                    $('#load_makanan_pendamping').hide();
                    $('#warning_detail_makanan').show().fadeOut(5000);
                    $('#msg_detail_makanan').text(res.msg);
                }
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>