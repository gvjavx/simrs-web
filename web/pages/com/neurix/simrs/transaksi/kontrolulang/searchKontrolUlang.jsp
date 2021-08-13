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
            $('#pendaftaran_active, #kontrol_ulang').addClass('active');
            $('#pendaftaran_open').addClass('menu-open');
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
            Pasien Kontrol Ulang
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Pasien Kontrol Ulang</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="kontrolulangForm" method="post" theme="simple" cssClass="form-horizontal" name="kontrolulang" action="searchKontrolUlang_kontrolulang.action">
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
                                        <s:textfield id="nama_pasien" name="headerDetailCheckup.namaPasien"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Pelayanan</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayananRJ_checkup"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initComboPoli.listOfPelayananRJ"
                                                  onchange="listSelectDokter(this.value)"
                                                  name="headerDetailCheckup.idPelayanan" listKey="idPelayanan"
                                                  listValue="namaPelayanan"
                                                  headerKey="" headerValue=" - "
                                                  cssClass="form-control select2" theme="simple"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Kontrol</label>
                                    <div class="col-sm-4">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield name="headerDetailCheckup.tglKontrol" id="val_kontrol" cssClass="form-control datepicker2 datemask2"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="kontrolulangForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_kontrolulang.action">
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien Kontrol Ulang</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped" style="font-size: 13px">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>No RM</td>
                                <td>Nama</td>
                                <td>Umur</td>
                                <td>Tanggal Kontrol</td>
                                <td>Pelayanan</td>
                                <td>Dokter</td>
                                <td>Status</td>
                                <td>Desa</td>
                                <td>No HP</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idPasien"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td>
                                        <s:if test='#row.umur != null && #row.umur != ""'>
                                            <s:property value="umur"/> Tahun
                                        </s:if>
                                    </td>
                                    <td>
                                        <script>
                                            var tgl = '<s:property value="tglCekup"/>';
                                            if(tgl != ''){
                                                document.write(converterDate(tgl));
                                            }
                                        </script>
                                    </td>
                                    <td><s:property value="namaPelayanan"/></td>
                                    <td><s:property value="namaDokter"/></td>
                                    <td>
                                        <s:if test='#row.status == "N"'>
                                            <span class="span-warning">Belum Kontrol</span>
                                        </s:if>
                                        <s:else>
                                            <span class="span-success">Sudah Kontrol</span>
                                        </s:else>
                                    </td>
                                    <td><s:property value="desa"/></td>
                                    <td>
                                        <script>
                                            var noHp = '<s:property value="noTelp"/>';
                                            var temp = "";
                                            if(noHp != '' && noHp.length > 0){
                                                for (var i = 0; i < noHp.length; i++) {
                                                    if(i == 3){
                                                        temp = temp+noHp[i]+'-';
                                                    }else if(i == 7){
                                                        temp = temp+noHp[i]+'-';
                                                    }else{
                                                        temp = temp+noHp[i];
                                                    }
                                                }
                                            }
                                            document.write(temp);
                                        </script>
                                    </td>
                                    <td align="center">
                                        <a href="<%= request.getContextPath() %>/checkup/add_checkup.action?tipe=KU&id=<s:property value="idPasien"/>&idp=<s:property value="idPelayanan"/>&&idd=<s:property value="idDokter"/>&jp=<s:property value="idJenisPeriksaPasien"/>&idx=<s:property value="idDetailCheckup"/>&tgl=<s:property value="tglKontrol"/>">
                                            <img class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                        </a>
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

</body>
<script>
    function listSelectDokter(idPelayanan, idDokter) {
        var option = "<option value=''>-</option>";
        CheckupAction.getComboDokterPelayanan(idPelayanan, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idDokter +"'>" + item.namaDokter + "</option>";
                });
                $('#nama_dokter').html(option);
                if(idDokter != undefined && idDokter != ''){
                    $('#nama_dokter').val(idDokter).trigger('change');
                }
            } else {
                $('#nama_dokter').html(option);
            }
        });
    }
</script>
</html>