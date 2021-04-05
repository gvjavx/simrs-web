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
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#pel_ri_active, #rawat_intensif').addClass('active');
            $('#pel_ri_open').addClass('menu-open');
            selectKamar();
        });

    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
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
            Rawat Intensif Pasien
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Rawat Intensif Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="rawatIntensifForm" method="post" namespace="/rawatintensif" action="searchIntensif_rawatintensif.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No RM</label>
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
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status" name="rawatInap.status"
                                                  headerKey="1" headerValue="Periksa"
                                                  cssClass="form-control select2"/>
                                    </div>
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
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="rawatIntensifForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_rawatintensif.action">
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
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Rawat Intensif Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped" style="font-size: 13px">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>ID Detail Checkup</td>
                                <td>No RM</td>
                                <td>Nama</td>
                                <td>Tanggal Masuk</td>
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
                                    <td><s:property value="statusPeriksaName"/></td>
                                    <td align="center">
                                        <script>
                                            document.write(changeJenisPasien('<s:property value="idJenisPeriksa"/>', '<s:property value="jenisPeriksaPasien"/>'));
                                        </script>
                                    </td>
                                    <td align="center">
                                        <s:if test='#row.statusPeriksa != "3"'>
                                            <s:if test='#row.idJenisPeriksa == "umum"'>
                                                <s:if test='#row.isBayar == "Y"'>
                                                    <s:url var="add_rawat_inap" namespace="/rawatintensif" action="add_rawatintensif" escapeAmp="false">
                                                        <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                        <s:param name="idx"><s:property value="idRawatInap"/></s:param>
                                                    </s:url>
                                                    <s:a href="%{add_rawat_inap}">
                                                        <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                                    </s:a>
                                                </s:if>
                                                <s:else>
                                                    <span class="span-warning">Uang muka belum bayar</span>
                                                </s:else>
                                            </s:if>
                                            <s:else>
                                                <s:url var="add_rawat_inap" namespace="/rawatintensif" action="add_rawatintensif" escapeAmp="false">
                                                    <s:param name="id"><s:property value="idDetailCheckup"/></s:param>
                                                    <s:param name="idx"><s:property value="idRawatInap"/></s:param>
                                                </s:url>
                                                <s:a href="%{add_rawat_inap}">
                                                    <img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                                </s:a>
                                            </s:else>
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
<!-- /.content-wrapper -->
<script type='text/javascript'>

    function printGelangPasien(noCheckup) {
        window.open('printGelangPasien_rawatinap.action?id=' + noCheckup, '_blank');
    }

    function listSelectRuangan(id){
        var idx     = id.selectedIndex;
        var idKelas = id.options[idx].value;
        var option  = "";
        var flag    = false;

        $('#load_ruang').show();
        setTimeout(function () {

        },100);
        if(idKelas != ''){
            CheckupDetailAction.listRuangan(idKelas, flag, { callback: function (response) {
                option = "<option value=''>[Select One]</option>";
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idRuangan + "'>" + item.noRuangan + "-" + item.namaRuangan + "</option>";
                    });
                } else {
                    option = option;
                }
                $('#load_ruang').hide();
            }
            });
        }else{
            option = "<option value=''>[Select One]</option>";
        }

        $('#nama_ruangan').html(option);
    }

    function selectKamar(){
        var option = "<option value=''>[Select One]</option>";
        CheckupDetailAction.listRuangan(null, false, 'rawat_intensif',
            { callback: function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idRuangan + "'>" + item.noRuangan + "-" + item.namaRuangan + "</option>";
                    });
                    $('#nama_ruangan').html(option);
                } else {
                    $('#nama_ruangan').html(option);
                }
            }
        });
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>