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
    <script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenRawatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CatatanTerintegrasiAction.js"/>'></script>

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
            $('#exampleGizi').dataTable({});
            $('#konsultasi_gizi').addClass('active');
            $('#exampleGizi').css('width','100%');
        });
    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<div class="content-wrapper">
    <section class="content-header">
        <h1>
            Konsultasi Gizi
        </h1>
    </section>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Konsultasi Gizi</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="giziForm" method="post" namespace="/konsultasigizi" action="search_konsultasigizi.action"
                                    theme="simple" cssClass="form-horizontal">
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Tipe Pelayanan</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:select list="#{'rawat_inap':'Rawat Inap'}"--%>
                                                  <%--id="tipe_pelayanan" name="konsultasiGizi.tipePelayanan"--%>
                                                  <%--headerKey="rawat_jalan" headerValue="Rawat Jalan"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <s:hidden name="konsultasiGizi.tipePelayanan" value="rawat_jalan"></s:hidden>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No Checkup</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="no_checkup" cssStyle="margin-top: 7px"
                                                     name="konsultasiGizi.noCheckup" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="konsultasiGizi.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="konsultasiGizi.nama"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'1':'Periksa','3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status" name="konsultasiGizi.status"
                                                  headerKey="0" headerValue="Antrian"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Tanggal Order</label>--%>
                                    <%--<div class="col-sm-2">--%>
                                        <%--<div class="input-group date" style="margin-top: 7px">--%>
                                            <%--<div class="input-group-addon">--%>
                                                <%--<i class="fa fa-calendar"></i>--%>
                                            <%--</div>--%>
                                            <%--<s:textfield id="tgl_from" name="konsultasiGizi.stTglFrom"--%>
                                                         <%--cssClass="form-control"--%>
                                                         <%--required="false"/>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-sm-2">--%>
                                        <%--<div class="input-group date" style="margin-top: 7px">--%>
                                            <%--<div class="input-group-addon">--%>
                                                <%--<i class="fa fa-calendar"></i>--%>
                                            <%--</div>--%>
                                            <%--<s:textfield id="tgl_to" name="rawatInap.stTglTo" cssClass="form-control"--%>
                                                         <%--required="false"/>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
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
                                        <a type="button" class="btn btn-danger" href="initForm_konsultasigizi.action">
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
                                                                                         closePoz();
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien Konsultasi Gizi</h3>
                    </div>
                    <div class="box-body">
                        <table id="exampleGizi" class="table table-bordered table-striped" style="font-size: 14px">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>No Checkup</td>
                                <td>No RM</td>
                                <td>Nama</td>
                                <td>Nama Pelayanan</td>
                                <td>Jenis Kelamin</td>
                                <td align="center" width="10%">
                                    Action
                                </td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="noCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="nama"/></td>
                                    <td><s:property value="namaPelayanan"/></td>
                                    <td><s:property value="jenisKelamin"/></td>
                                    <td align="center">
                                        <img border="0" class="hvr-grow" id="v_<s:property value="noCheckup"/>"
                                             src="<s:url value="/pages/images/icons8-search-25.png"/>"
                                             style="cursor: pointer;"
                                             onclick="viewDetail(
                                                     '<s:property value="idPasien"/>',
                                                     '<s:property value="nama"/>',
                                                     '<s:property value="namaPelayanan"/>',
                                                     '<s:property value="jenisKelamin"/>',
                                                     '<s:property value="umur"/>',
                                                     '<s:property value="alergi"/>',
                                                     '<s:property value="diagnosa"/>',
                                                     '<s:property value="tglLahir"/>',
                                                     '<s:property value="noCheckup"/>',
                                                     '<s:property value="idDetailCheckup"/>',
                                                     '<s:property value="idKonsultasiGizi"/>',
                                                     '<s:property value="status"/>'
                                                     )">
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

<div class="modal fade" id="modal-history">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Konsultasi Gizi dan Asesmen Gizi</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissable" style="display: none" id="warning_konsul">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_konsul"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="40%">NO RM</td>
                                    <td><span id="det_no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td>Nama</td>
                                    <td><span id="det_nama"></span></td>
                                </tr>
                                <tr>
                                    <td>Jenis Kelamin</td>
                                    <td><span id="det_jk"></span></td>
                                </tr>
                                <tr>
                                    <td>Umur</td>
                                    <td><span id="det_umur"></span></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="40%">Pelayanan</td>
                                    <td><span id="det_ruangan"></span></td>
                                </tr>
                                <tr>
                                    <td>Alergi</td>
                                    <td><span id="det_alergi"></span></td>
                                </tr>
                                <tr>
                                    <td>Diagnosa</td>
                                    <td><span id="det_diagnosa"></span></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="btn-group dropdown" style="margin-top: -20px">
                                <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Asesmen Gizi
                                </button>
                                <button id="btn_ases" type="button" class="btn btn-primary dropdown-toggle"
                                        data-toggle="dropdown" style="height: 34px">
                                    <span class="caret"></span>
                                    <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu" role="menu" id="asesmen_rj">
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button id="close_periksa" type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_periksa"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_periksa"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-temp"></div>

<div class="modal fade" id="modal-confirm-rm">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Konfirmasi
                </h4>
            </div>
            <div class="modal-body">
                <h4 class="text-center" id="tanya"></h4>
                <h4 class="text-center" id="print_form"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Tidak
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con_rm"><i class="fa fa-check"></i> Ya            </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>
    var idDetailCheckup = "";
    var contextPath = '<%= request.getContextPath() %>';
    var isReadRM = false;
    var noCheckup = "";
    var umur = "";
    var namaRuanganPasien = "";
    var tempBerat = "";
    var tempTinggi = "";
    var tempTensi = "";
    var tempSuhu = "";
    var tempNadi = "";
    var tempRr = "";
    var tempBerat = "";
    var tempTinggi = "";
    var tempAnmnesa = "";
    var idPasien = "";

    function viewDetail(idPas, nama, namaPelayanan, jenisKelamin, um, alergi, diagnosa, tglLahir, nocheckup, iddetail, idGizi, status) {
        if(!cekSession()){
            idDetailCheckup = iddetail;
            noCheckup = nocheckup;
            umur = um;
            idPasien = idPas;
            $('#det_no_rm').html(idPas);
            $('#det_nama').html(nama);
            $('#det_jk').html(jenisKelamin);
            $('#det_umur').html(um);
            $('#det_ruangan').html(namaPelayanan);
            $('#det_alergi').html(alergi);
            $('#det_diagnosa').html(diagnosa);
            if("3" != status){
                $('#close_periksa').hide();
                $('#save_periksa').attr('onclick', 'setStatus(\'3\',\''+idGizi+'\',\'dering\')');
                setStatus('1', idGizi, 'silent');
            }else{
                $('#close_periksa').show();
                $('#save_periksa').hide();
            }
            $('#btn_ases').attr('onclick', 'getListRekamMedis(\'gizi\',\'\',\''+iddetail+'\')');
            $('#modal-history').modal({show: true, backdrop: 'static', keyboard: false});
        }
    }

    function cekDataNull(item) {
        var data = "";
        if (item != null && item != '') {
            data = item;
        }
        return data;
    }

    function loadModalRM(jenis, method, parameter, idRM, flag) {
        var context = contextPath + '/pages/modal/modal-default.jsp';
        if (jenis != "") {
            context = contextPath + '/pages/modal/modal-'+jenis+'.jsp';
        }
        $('#modal-temp').load(context, function (res, status, xhr) {
            if(status == "success"){
                var func = new Function(method+'(\''+parameter+'\', \''+idRM+'\', \''+flag+'\')');
                func();
            }
        });
    }

    function getListRekamMedis(tipePelayanan, jenis, id) {
        var li = "";
        CheckupAction.getListRekammedisPasien(tipePelayanan, jenis, id, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var cek = "";
                    var tgl = "";
                    var icons = '<i class="fa fa-file-o"></i>';
                    var icons2 = '<i class="fa fa-print"></i>';
                    var tol = "";
                    var tolText = "";
                    var labelTerisi = "";
                    var constan = 0;
                    var terIsi = 0;
                    var labelPrint = "";
                    var terIsiPrint = "";
                    var enter = '';

                    if (item.jumlahKategori != null) {
                        constan = item.jumlahKategori;
                    }
                    if (item.terisi != null && item.terisi != '') {
                        terIsi = item.terisi;
                        terIsiPrint = item.terisi;
                    }

                    if (constan == terIsi || parseInt(terIsi) > parseInt(constan)) {
                        var conver = "";
                        if (item.createdDate != null) {
                            conver = converterDate(new Date(item.createdDate));
                            tgl = '<label class="label label-success">' + conver + '</label>';
                            tol = 'class="box-rm"';
                            tolText = '<span class="box-rmtext">Tanggal mengisi ' + conver + '</span>';
                        }
                        icons = '<i class="fa fa-check" style="color: #449d44"></i>';
                        icons2 = '<i class="fa fa-check" style="color: #449d44"></i>';
                        enter = '<br>';
                    }

                    labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';
                    labelPrint = '<span style="color: #367fa9; font-weight: bold">' + terIsiPrint + '</span>';

                    if (item.jenis == 'ringkasan_rj') {
                        li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-television"></i>' + item.namaRm + '</a></li>'
                    } else {
                        if (item.function == 'addMonitoringFisioterapi') {
                            li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-television"></i>' + item.namaRm + '</a></li>'
                        } else {
                            if (item.keterangan == 'form') {
                                li += '<li ' + tol + '><a style="cursor: pointer" onclick="loadModalRM(\'' + item.jenis + '\', \''+item.function +'\', \''+item.parameter+'\', \''+item.idRekamMedisPasien+'\', \'Y\')">' + icons + item.namaRm + ' ' + labelTerisi + tolText + '</a></li>'+enter;
                            } else if (item.keterangan == "surat") {
                                li += '<li ' + tol + '><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\',\'' + item.namaRm + '\')' + '">' + icons2 + item.namaRm + ' ' + labelPrint + tolText + '</a></li>'+enter;
                            }
                        }
                    }
                });
                $('#asesmen_rj').html(li);
            }
        });
    }

    function setStatus(status, id, tipe) {
        dwr.engine.setAsync(true);
        KonsultasiGiziAction.saveEdit(id, status, function (res) {
            if(res.status == "success"){
                if("dering" == tipe){
                    window.location.reload(true);
                    $('#modal-history').modal("hide");
                }
            }else{
                $('#warning_konsul').show().fadeOut(5000);
                $('#msg_konsul').text(res.msg);
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>