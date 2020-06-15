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
        .modal { overflow-y: auto}
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/VerifikatorPembayaranAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#bayar_rawat_jalan, #pembayaran_active').addClass('active');
            $('#pembayaran_open').addClass('menu-open');
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
            Verifikator Pembayaran
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Pembayaran</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="searchForm" method="post" namespace="/onlinepaymentverif" action="search_onlinepaymentverif.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Pasien</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="headerDetailCheckup.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Nama</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:textfield id="nama_pasien" name="headerDetailCheckup.nama"--%>
                                                     <%--required="false" readonly="false"--%>
                                                     <%--cssClass="form-control" cssStyle="margin-top: 7px"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="control-label col-sm-4">Poli</label>--%>
                                        <%--<div class="col-sm-4">--%>
                                            <%--<s:action id="initComboPoli" namespace="/checkup"--%>
                                                      <%--name="getComboPelayanan_checkup"/>--%>
                                            <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                      <%--list="#initComboPoli.listOfPelayanan"--%>
                                                      <%--name="headerDetailCheckup.idPelayanan" listKey="idPelayanan"--%>
                                                      <%--listValue="namaPelayanan"--%>
                                                      <%--headerKey="" headerValue="[Select one]"--%>
                                                      <%--cssClass="form-control select2" theme="simple"/>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Status</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:select list="#{'':'Rujuk','':'Selesai'}" cssStyle="margin-top: 7px"--%>
                                                  <%--id="status"--%>
                                                  <%--headerKey="3" headerValue="Selesai"--%>
                                                  <%--cssClass="form-control select2" disabled="true"/>--%>
                                        <%--<s:hidden name="headerDetailCheckup.statusPeriksa" value="3"></s:hidden>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Status Bayar</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:select list="#{'Y':'Sudah Dibayar'}" cssStyle="margin-top: 7px"--%>
                                                  <%--id="statusBayar"--%>
                                                  <%--headerKey="" headerValue="Belum Dibayar" name="headerDetailCheckup.statusBayar"--%>
                                                  <%--cssClass="form-control"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Jenis Pasien</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'asuransi':'ASURANSI', 'bpjs':'BPJS'}" cssStyle="margin-top: 7px"
                                                  headerKey="umum" headerValue="UMUM" name="headerDetailCheckup.idJenisPeriksaPasien"
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Tanggal Masuk</label>--%>
                                    <%--<div class="col-sm-2">--%>
                                        <%--<div class="input-group date" style="margin-top: 7px">--%>
                                            <%--<div class="input-group-addon">--%>
                                                <%--<i class="fa fa-calendar"></i>--%>
                                            <%--</div>--%>
                                            <%--<s:textfield id="tgl_from" name="headerDetailCheckup.stDateFrom" cssClass="form-control"--%>
                                                         <%--required="false"/>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-sm-2">--%>
                                        <%--<div class="input-group date" style="margin-top: 7px">--%>
                                            <%--<div class="input-group-addon">--%>
                                                <%--<i class="fa fa-calendar"></i>--%>
                                            <%--</div>--%>
                                            <%--<s:textfield id="tgl_to" name="headerDetailCheckup.stDateTo" cssClass="form-control"--%>
                                                         <%--required="false"/>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="searchForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_kasirjalan.action">
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped" style="font-size: 13px">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Id</td>
                                <td>Id Pasien</td>
                                <td>Nama</td>
                                <td>Status</td>
                                <td>Status Bayar konsultasi</td>
                                <td>Approve Bayar konsultasi</td>
                                <td>Status Bayar Resep</td>
                                <td>Approve Bayar Resep</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResults" var="row">
                                <tr>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="ketStatus"/></td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.flagBayarKonsultasi == "Y"'>
                                            <label class="label label-success"> sudah bayar</label>
                                        </s:if>
                                        <s:else>
                                            <label class="label label-warning"> belum bayar</label>
                                        </s:else>
                                    </td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.approveKonsultasi == "Y"'>
                                            <label class="label label-success"> <i class="fa fa-check"></i></label>
                                        </s:if>
                                    </td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.flagResep == "Y"'>
                                            <s:if test='#row.flagBayarResep == "Y"'>
                                                <label class="label label-success"> sudah bayar</label>
                                            </s:if>
                                            <s:else>
                                                <label class="label label-warning"> belum bayar</label>
                                            </s:else>
                                        </s:if>

                                    </td>
                                    <td style="vertical-align: middle" align="center">
                                        <s:if test='#row.approveResep == "Y"'>
                                            <label class="label label-success"> <i class="fa fa-check"></i></label>
                                        </s:if>
                                    </td>
                                    <td align="center">
                                        <button class="btn btn-sm btn-primary" onclick="showDetail('<s:property value="id"/>')"><i class="fa fa-edit"></i></button>
                                        <%--<s:if test='#row.statusBayar == "Y"'>--%>
                                            <%--<s:url var="print_invo" namespace="/kasirjalan" action="printInvoice_kasirjalan" escapeAmp="false">--%>
                                                <%--<s:param name="id"><s:property value="idDetailCheckup"/></s:param>--%>
                                                <%--<s:param name="jenis"><s:property value="idJenisPeriksaPasien"/></s:param>--%>
                                            <%--</s:url>--%>
                                            <%--<s:a href="%{print_invo}" target="_blank">--%>
                                            <%--<img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>">--%>
                                            <%--</s:a>--%>
                                        <%--</s:if>--%>
                                        <%--<s:else>--%>
                                            <%--<img id="t_<s:property value="idDetailCheckup"/>" onclick="showInvoice('<s:property value="noCheckup"/>','<s:property value="idDetailCheckup"/>')" class="hvr-grow" src="<s:url value="/pages/images/icon_payment.ico"/>" style="cursor: pointer;">--%>
                                        <%--</s:else>--%>
                                        <%--<s:if test='#row.idJenisPeriksaPasien == "bpjs"'>--%>
                                            <%--<s:url var="print_invo" namespace="/kasirjalan" action="printInvoice_kasirjalan" escapeAmp="false">--%>
                                                <%--<s:param name="id"><s:property value="idDetailCheckup"/></s:param>--%>
                                                <%--<s:param name="jenis"><s:property value="idJenisPeriksaPasien"/></s:param>--%>
                                            <%--</s:url>--%>
                                            <%--<s:a href="%{print_invo}" target="_blank">--%>
                                                <%--<img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>">--%>
                                            <%--</s:a>--%>
                                        <%--</s:if>--%>
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

<div class="modal fade" id="modal-invoice">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Pembayaran</h4>
            </div>
            <div class="modal-body">
                <%--<div class="box-header with-border">--%>
                    <%--<h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>--%>
                <%--</div>--%>
                <div class="box-body">
                    <div class="row">
                        <!-- /.col -->
                    </div>
                </div>

                <%--<div class="box-header with-border">--%>
                    <%--<h3 class="box-title"><i class="fa fa-medkit"></i> Daftar Tindakan Rawat</h3>--%>
                <%--</div>--%>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_tindakan_fin">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td width="20%">Keterangan</td>
                            <td>Approve Flag</td>
                            <td>Approve Who</td>
                            <td align="center" width="20%">Total Tarif (Rp.)</td>
                            <td align="center" width="20%">View Bukti</td>
                            <td width="10%" align="center">Action</td>
                        </tr>
                        </thead>
                        <tbody id="body_tindakan_fin">
                        </tbody>
                    </table>

                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_fin_error"></p>
                    </div>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_fin">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_fin"></p>
                    </div>
                </div>
                <div class="box-header with-border"></div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <%--<button type="button" class="btn btn-success" id="save_fin" onclick="confirmSavePembayaranTagihan()"><i class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success"--%>
                        <%--id="load_fin"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-bukti">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> View bukti
                </h4>
            </div>
            <div class="modal-body">
                <div id="body-view-bukti">

                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function formatRupiah(angka) {
        if(angka != "" && angka != null && parseInt(angka) > 0){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        } else {
            return "0";
        }
    }

    function showDetail(var1){
        $("#modal-invoice").modal('show');

        VerifikatorPembayaranAction.listDetailPembayaran(var1, function (response) {

            var str = "";
            $.each(response, function (i, item) {
                str += "<tr>" +
                    "<td>"+item.keterangan+"</td>"+
                    "<td>"+ nullEscape(item.approvedFlag) +"</td>"+
                    "<td>"+ nullEscape(item.approvedWho) +"</td>"+
                    "<td>"+ formatRupiah( item.nominal  )+"</td>";

                    if (item.flagBayar == "Y"){
                        str += "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewBukti(\'"+item.urlFotoBukti+"\')\"><i class='fa fa-search'></i></button></td>"+
                            "<td align='center'><button class='btn btn-sm btn-success' onclick=\"saveApprove(\'"+item.id+"\')\"><i class='fa fa-check'></i> Approve</button></td>";
                    } else {
                        str += "<td></td>" +
                            "<td></td>";
                    }
                    str += "</tr>";
            });

            $("#body_tindakan_fin").html(str);
        })
    }

    function nullEscape(var1) {
        if (var1 == null)
            return "";
        else
            return var1;
    }

    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }

    function viewBukti(var1){
        $("#modal-view-bukti").modal('show');

        var urlImg = firstpath()+"/images/upload/bukti_transfer/"+var1;

        $("#body-view-bukti").html("<img src='"+urlImg+"'></img>");
    }

    function saveApprove(var1) {

        $("#msg_fin").text("Loading . . .");
        $("#success_fin").show();

        dwr.engine.setAsync(true);
        VerifikatorPembayaranAction.approveTransaksi(var1, function (response) {
            dwr.engine.setAsync(false);
            if (response.status == "error"){
                $("#warning_fin").show();
                $("#success_fin").hide();
                $("#msg_fin_error").text(response.message);
            } else {
                $("#success_fin").show().fadeOut(5000);
                $("#msg_fin").text("Success Approve");
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>