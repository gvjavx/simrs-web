<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>

    <script type='text/javascript'>
        $( document ).ready(function() {
            $('#rawat_jalan').addClass('active');
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
            Rawat Jalan Pasien
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Rawat Jalan Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="checkupDetailForm" method="post" namespace="/checkupdetail" action="search_checkupdetail.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="headerDetailCheckup.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="headerDetailCheckup.nama"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>

                                <s:if test="isEnabledPoli()">

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Poli</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initComboPoli.listOfPelayanan" id="poli"
                                                  name="headerDetailCheckup.idPelayanan" listKey="idPelayanan"
                                                  listValue="namaPelayanan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2" theme="simple"/>
                                    </div>
                                </div>
                                </s:if>

                                <s:else>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Poli</label>
                                        <div class="col-sm-4">
                                            <s:action id="initComboPoli" namespace="/checkup"
                                                      name="getComboPelayanan_checkup"/>
                                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                                      list="#initComboPoli.listOfPelayanan" id="poli"
                                                      listKey="idPelayanan"
                                                      name="headerDetailCheckup.idPelayanan"
                                                      listValue="namaPelayanan"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2" theme="simple" disabled="true"/>
                                        </div>
                                        <s:hidden name="headerDetailCheckup.idPelayanan" id=""></s:hidden>
                                    </div>
                                </s:else>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'1':'Periksa','2':'Rujuk','3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status" name="headerDetailCheckup.statusPeriksa"
                                                  headerKey="0" headerValue="Antrian"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="headerDetailCheckup.stDateFrom" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="headerDetailCheckup.stDateTo" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="checkupDetailForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <s:if test="isEnabledAddPasien()">
                                        <a type="button" class="btn btn-primary" href="addRawatJalan_checkupdetail.action"><i
                                                class="fa fa-plus"></i> Tambah Rawat Pasien</a>
                                        </s:if>
                                        <a type="button" class="btn btn-danger" href="initForm_checkupdetail.action">
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
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
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

                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Rawat Jalan Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90" style="font-size: 12px">
                                <td>ID Detail Checkup</td>
                                <td>No RM</td>
                                <td>Nama</td>
                                <td>Tanggal Masuk</td>
                                <td>Desa</td>
                                <td>Status</td>
                                <td align="center">Jenis Pasien</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idDetailCheckup"/></td>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="formatTglMasuk"/></td>
                                    <td><s:property value="desa"/></td>
                                    <td><s:property value="statusPeriksaName"/></td>
                                    <td align="center">
                                        <script>
                                            document.write(changeJenisPasien('<s:property value="idJenisPeriksaPasien"/>', '<s:property value="jenisPeriksaPasien"/>'));
                                        </script>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <s:if test='#row.statusPeriksa != "3"'>
                                            <s:if test='#row.idJenisPeriksaPasien == "umum"'>
                                                <s:if test='#row.isBayar == "Y"'>
                                                    <s:url var="add_rawat_jalan" namespace="/checkupdetail" action="add_checkupdetail" escapeAmp="false">
                                                        <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{add_rawat_jalan}">
                                                        <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                                    </s:a>
                                                    <s:if test='#row.statusPeriksa == "0"'>
                                                        <img onclick="cancelPeriksa('<s:property value="idDetailCheckup"/>')" style="cursor: pointer" class="hvr-grow" src="<s:url value="/pages/images/cancel-flat-new.png"/>">
                                                    </s:if>
                                                </s:if>
                                                <s:else>
                                                    <span class="span-warning">Uang muka belum bayar</span>
                                                </s:else>
                                            </s:if>
                                            <s:else>
                                                <s:url var="add_rawat_jalan" namespace="/checkupdetail" action="add_checkupdetail" escapeAmp="false">
                                                    <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                </s:url>
                                                <s:a href="%{add_rawat_jalan}">
                                                    <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                                </s:a>
                                                <s:if test='#row.statusPeriksa == "0"'>
                                                    <img onclick="cancelPeriksa('<s:property value="idDetailCheckup"/>')" style="cursor: pointer" class="hvr-grow" src="<s:url value="/pages/images/cancel-flat-new.png"/>">
                                                </s:if>
                                            </s:else>
                                        </s:if>
                                        <s:if test='#row.tglCekup == null'>
                                        </s:if>
                                        <s:else>
                                            <a target="_blank" href="printSuratKeterangan_checkupdetail.action?id=<s:property value="idDetailCheckup"/>">
                                                <img src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                            </a>
                                        </s:else>

                                        <s:if test='#row.keteranganSelesai == "Rujuk Rumah Sakit Lain"'>
                                            <a target="_blank" href="printFormulirPindahRS_checkupdetail.action?id=<s:property value="idDetailCheckup"/>">
                                                <img src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                            </a>
                                        </s:if>
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

<div class="modal fade" id="modal-detail">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Data Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_cancel">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <p id="msg_cancel"></p>
                            </div>
                            <table class="table table-striped" style="font-size: 13px">
                                <tr>
                                    <td width="30%">No Checkup</td>
                                    <td><span id="det_no_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td>ID Detail Checkup</td>
                                    <td><span id="det_id_detail_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td>NO RM</td>
                                    <td><span id="det_no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td>Nama Pasien</td>
                                    <td><span id="det_nama_pasien"></span></td>
                                </tr>
                                <tr>
                                    <td>Pelayanan</td>
                                    <td><span id="det_pelayanan"></span></td>
                                </tr>
                                <tr>
                                    <td>Jenis Pasien</td>
                                    <td><span id="det_jenis_pasien"></span></td>
                                </tr>
                                <tr>
                                    <td>Alamat</td>
                                    <td><span id="det_alamat"></span></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-12">
                            <textarea id="set_alasan" class="form-control" rows="2" placeholder="Alasan"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add" ><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_add"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

<script>
    function cancelPeriksa(idDetailCHeckup){
        CheckupAction.listDataPasien(idDetailCHeckup, {
            callback: function (res) {
                $('#det_no_checkup').text(res.noCheckup);
                $('#det_id_detail_checkup').text(res.idDetailCheckup);
                $('#det_no_rm').text(res.idPasien);
                $('#det_nama_pasien').text(res.nama);
                $('#det_pelayanan').text(res.namaPelayanan);
                $('#det_jenis_pasien').text(res.statusPeriksaName);
                $('#det_alamat').text(res.namaDesa+", "+res.namaKecamatan+", "+res.namaKota);
            }
        });
        $('#save_add').attr('onclick', 'saveCancel(\''+idDetailCHeckup+'\')');
        $('#modal-detail').modal({show: true, backdrop:'static'});
    }

    function saveCancel(idDetailCHeckup){
        var alsan = $('#set_alasan').val();
        if(alsan != ''){
            $('#save_add').hide();
            $('#load_add').show();
            dwr.engine.setAsync(true);
            CheckupAction.cancelPeriksa(idDetailCHeckup, alsan, {
                callback: function (res) {
                    if(res.status == "success"){
                        $('#save_add').show();
                        $('#load_add').hide();
                        $('#modal-detail').modal('hide');
                        $('#info_dialog').dialog('open');
                    }else{
                        $('#save_add').show();
                        $('#load_add').hide();
                        $('#warning_cancel').show().fadeOut(5000);
                        $('#msg_cancel').text(res.msg);
                    }
                }
            });
        }else{
            $('#warning_cancel').show().fadeOut(5000);
            $('#msg_cancel').text("Silahkan masukkan alasan pasien...!");
        }
    }

</script>

</body>
</html>