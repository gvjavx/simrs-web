<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanGiziAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#permintaan_gizi').addClass('active');
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
            Permintaan Gizi
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Permintaan Gizi</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="giziForm" method="post" namespace="/ordergizi" action="search_ordergizi.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Pasien</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="rawatInap.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="rawatInap.namaPasien"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Status</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:select list="#{'0':'Antrian','1':'Periksa','2':'Rujuk','3':'Selesai'}" cssStyle="margin-top: 7px"--%>
                                                  <%--id="status" name="rawatInap.statusPeriksa"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kelas Ruangan</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboKelas" namespace="/checkupdetail"
                                                  name="getListComboKelasRuangan_checkupdetail"/>
                                        <s:select cssStyle="margin-top: 7px" onchange="$(this).css('border',''); listSelectRuangan(this)"
                                                  list="#initComboKelas.listOfKelasRuangan" id="kelas_kamar"
                                                  name="rawatInap.idKelas"
                                                  listKey="idKelasRuangan"
                                                  listValue="namaKelasRuangan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                    <div class="col-sm-3" style="display: none;" id="load_ruang">
                                        <img border="0" src="<s:url value="/pages/images/spinner.gif"/>" style="cursor: pointer; width: 45px; height: 45px"><b style="color: #00a157;">Sedang diproses...</b></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Ruangan</label>
                                    <div class="col-sm-4">
                                        <select style="margin-top: 7px" class="form-control select2" id="nama_ruangan" name="rawatInap.idRuang">
                                            <option value=''>[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="rawatInap.stTglFrom" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="rawatInap.stTglTo" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="giziForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_rawatinap.action">
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
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Permintaan Gizi</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>ID Rawat Inap</td>
                                <td>ID Pasien</td>
                                <td>Nama</td>
                                <td>Ruangan</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idRawatInap"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="namaRangan"/> [<s:property value="noRuangan"/>]</td>
                                    <td>
                                        <s:if test='#row.approveFlag == "Y"'>
                                            <label class="label label-success"> telah dikonfirmasi</label>
                                        </s:if>
                                        <s:else>
                                            <label class="label label-warning"> order gizi baru</label>
                                        </s:else>
                                    </td>
                                    <td align="center">
                                        <s:if test='#row.approveFlag == "Y"'>
                                        </s:if>
                                        <s:else>
                                            <img onclick="listOrderGizi('<s:property value="idRawatInap"/>', '<s:property value="noCheckup"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
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
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-detail-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Tindakan Rawat Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_tin">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_tin"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_tin">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_tin2"></p>
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-medkit"></i> Daftar Order Gizi</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_gizi">
                        <thead>
                        <tr >
                            <td rowspan="2">Tanggal</td>
                            <td colspan="2" align="center">Pagi</td>
                            <td colspan="2" align="center">Siang</td>
                            <td colspan="2" align="center">Malam</td>
                            <td align="center" rowspan="2">Status</td>
                            <td align="center" rowspan="2">Action</td>
                        </tr>
                        <tr >
                            <td>Jenis</td>
                            <td>Bentuk</td>
                            <td>Jenis</td>
                            <td>Bentuk</td>
                            <td>Jenisi</td>
                            <td>Bentuk</td>
                        </tr>
                        </thead>
                        <tbody id="body_gizi">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_gizi" onclick="saveVerif()"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_gizi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function listOrderGizi(idRawatInap, noCheckup){
        $('#modal-detail-pasien').modal({show:true, backdrop:'static'});
        var table = "";
        dwr.engine.setAsync(true);
        PermintaanGiziAction.getListOrderGizi(idRawatInap, {callback:function (response) {
                $.each(response, function (i, item) {

                    var tanggal = $.datepicker.formatDate("dd-mm-yy",new Date(item.createdDate));
                    var jenisPagi = "";
                    var bentukPagi = "";
                    var jenisSiang = "";
                    var bentukSiang = "";
                    var jenisMalam = "";
                    var bentukMalam = "";
                    var label = "";
                    var btn = "";

                    if(item.bentukMakanPagi != null){
                        bentukPagi = item.bentukMakanPagi;
                    }
                    if(item.dietPagi != null){
                        jenisPagi = item.dietPagi;
                    }
                    if(item.bentukMakanSiang != null){
                        bentukSiang = item.bentukMakanSiang;
                    }
                    if(item.dietSiang != null){
                        jenisSiang = item.dietSiang;
                    }
                    if(item.dietMalam != null){
                        bentukMalam = item.bentukMakanMalam;
                    }
                    if(item.bentukMakanMalam != null){
                        jenisMalam = item.dietMalam;
                    }

                    if(item.approveFlag == "Y"){
                        label = '<label class="label label-info"> dikirim</label>';
                        btn = '<img onclick="printBarcodeGizi(\''+noCheckup+'\',\''+item.idOrderGizi+'\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;">';
                    }else{
                        label = '<label class="label label-warning"> menunggu</label>';
                        btn = '<img onclick="saveApprove(\''+item.idOrderGizi+'\',\''+idRawatInap+'\',\''+noCheckup+'\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">';
                    }

                    if(item.diterimaFlag == "Y"){
                        label = '<label class="label label-success"> selesai</label>';
                        btn = '';
                    }
                    table +=    '<tr>' +
                                '<td>'+tanggal+'</td>'+
                                '<td>'+jenisPagi+'</td>'+
                                '<td>'+bentukPagi+'</td>'+
                                '<td>'+jenisSiang+'</td>'+
                                '<td>'+bentukSiang+'</td>'+
                                '<td>'+jenisMalam+'</td>'+
                                '<td>'+bentukMalam+'</td>'+
                                '<td>'+label+'</td>'+
                                '<td align="center">'+btn+'</td>'+
                                '</tr>'
                });
                $('#body_gizi').html(table);
            }});
    }

    function saveApprove(idOrder, idRawatInap, noCheckup){
        PermintaanGiziAction.updateApproveFLag(idOrder, function (response) {
            listOrderGizi(idRawatInap, noCheckup);
        })
    }

    function printBarcodeGizi(noCheckup, idorderGizi) {
        window.open('printBarcodeGizi_ordergizi.action?id=' + noCheckup+'&order='+idorderGizi, '_blank');
    }

    function saveVerif(){
        $('#save_gizi').hide();
        $('#load_gizi').show();
        setTimeout(function () {
            $('#save_gizi').show();
            $('#load_gizi').hide();
            $('#modal-detail-pasien').modal('hide');
            $('#info_dialog').dialog('open');
        },500);
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>