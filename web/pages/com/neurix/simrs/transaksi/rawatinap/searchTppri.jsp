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
        .garis {
            color: #ddd;
        }

        .blink_me {
            animation: blinker 3.0s linear infinite;
        }

        @keyframes blinker {
            50% {
                opacity: 0;
            }
        }
        .nav-tabs-custom>.nav-tabs>li.active{
            border-top-color: #30d196 !important;
        }
    </style>
    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#pendaftaran_active, #bayar_rawat_inap').addClass('active');
            $('#pendaftaran_open').addClass('menu-open');
            var tab = getLocalStorageAtas('tab_aktif');
            if(tab != '' && tab != null){
                if('form_1' == tab){
                    $('#form_1, #tab_1').addClass("active");
                    $('#form_2, #tab_2').removeClass("active");
                }else{
                    $('#form_1, #tab_1').removeClass("active");
                    $('#form_2, #tab_2').addClass("active");
                }
            }
        });

    </script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RawatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KeperawatanRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/spinner.js"/>'></script>

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
            Tempat Pendaftaran Pasien Rawat Inap (TPPRI)
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li onclick="setLocalStoregeAtas('tab_aktif', 'form_1')" id="form_1" style="cursor: pointer" class="active"><a href="#tab_1" data-toggle="tab" aria-expanded="true">TPPRI</a></li>
                        <li onclick="setLocalStoregeAtas('tab_aktif', 'form_2')" id="form_2" style="cursor: pointer" class=""><a href="#tab_2" data-toggle="tab" aria-expanded="false">IGD</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_1">
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Pendaftaran Rawat Inap Pasien</h3>
                            </div>
                            <div class="box-body">
                                <div class="form-group">
                                    <s:form id="rawatInapForm" method="post" namespace="/rawatinap"
                                            action="searchTppri_rawatinap.action" theme="simple" cssClass="form-horizontal">
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
                                            <label class="control-label col-sm-4">Tanggal Masuk</label>
                                            <div class="col-sm-2">
                                                <div class="input-group date" style="margin-top: 7px">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <s:textfield id="tgl_from" name="rawatInap.stTglFrom"
                                                                 cssClass="form-control"
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
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">Flag Tppri</label>
                                            <div class="col-sm-4">
                                                <s:select list="#{'Y':'Non-Active'}" id="flag" name="rawatInap.flagTppri"
                                                          headerKey="" headerValue="Active" cssClass="form-control select2"
                                                          cssStyle="width: 100%"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4"></label>
                                            <div class="col-sm-8" style="margin-top: 7px">
                                                <sj:submit type="button" cssClass="btn btn-success" formIds="rawatInapForm"
                                                           id="search" name="search"
                                                           onClickTopics="showDialogLoading"
                                                           onCompleteTopics="closeDialogLoading">
                                                    <i class="fa fa-search"></i>
                                                    Search
                                                </sj:submit>
                                                <a type="button" class="btn btn-danger" href="initTppri_rawatinap.action">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </a>
                                                <button onclick="showDaftar()" type="button" class="btn btn-primary">
                                                    <i class="fa fa-plus"></i> Pendaftaran Anak RB
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
                                                <div class="col-md-1">
                                                    <input type="color" style="margin-left: -6px; margin-top: -8px"
                                                           class="js-color-picker  color-picker pull-left">
                                                </div>
                                                <div class="col-md-9">
                                                    <input type="range" style="margin-top: -8px" class="js-line-range" min="1"
                                                           max="72" value="1">
                                                </div>
                                            </div>
                                        </div>
                                    </s:form>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien TPPRI</h3>
                            </div>
                            <div class="box-body">
                                <table id="myTable" class="table table-bordered table-striped" style="font-size: 13px; width: 100%">
                                    <thead>
                                    <tr bgcolor="#30d196">
                                        <td>No Checkup</td>
                                        <td>No RM</td>
                                        <td>Nama</td>
                                        <td>Tanggal Masuk</td>
                                        <td align="center">Jenis Pasien</td>
                                        <td>Tindak Lanjut</td>
                                        <td align="center">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="#session.listOfResultTppri" var="row">
                                        <tr>
                                            <td><s:property value="noCheckup"/></td>
                                            <td><s:property value="idPasien"/></td>
                                            <td><s:property value="namaPasien"/></td>
                                            <td><s:property value="formatTglMasuk"/></td>
                                            <td align="center">
                                                <script>
                                                    document.write(changeJenisPasien('<s:property value="idJenisPeriksa"/>', '<s:property value="jenisPeriksaPasien"/>'));
                                                </script>
                                            </td>
                                            <td><s:property value="keteranganSelesai"/></td>
                                            <td align="center">
                                                <s:if test='#row.flagTppri == "Y"'>
                                                    <img id="t_<s:property value="idDetailCheckup"/>"
                                                         onclick="printGelangPasien('<s:property value="noCheckup"/>')" class="hvr-grow"
                                                         src="<s:url value="/pages/images/icons8-print-25.png"/>"
                                                         style="cursor: pointer;">
                                                </s:if>
                                                <s:else>
                                                    <img id="t_<s:property value="idDetailCheckup"/>"
                                                         onclick="detail('<s:property value="noCheckup"/>',
                                                                 '<s:property value="idDetailCheckup"/>',
                                                                 '<s:property value="idPasien"/>',
                                                                 '<s:property value="tindakLanjut"/>',
                                                                 '<s:property value="keteranganSelesai"/>')" class="hvr-grow"
                                                         src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>"
                                                         style="cursor: pointer;">
                                                </s:else>
                                                <s:if test='#row.isTindakanRawat == "N" && #row.flagBatal == "N"'>
                                                    <img onclick="cancelPeriksa('<s:property value="idDetailCheckup"/>', 'inap')" style="cursor: pointer" class="hvr-grow" src="<s:url value="/pages/images/cancel-flat-new.png"/>">
                                                </s:if>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="tab-pane" id="tab_2">
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Rawat IGD Pasien</h3>
                            </div>
                            <div class="box-body">
                                <div class="form-group">

                                    <s:form id="igdForm" method="post" namespace="/rawatinap" action="searchIgd_rawatinap.action" theme="simple" cssClass="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">No RM</label>
                                            <div class="col-sm-4">
                                                <s:textfield cssStyle="margin-top: 7px"
                                                             name="headerDetailCheckup.idPasien" required="false"
                                                             readonly="false" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">Nama</label>
                                            <div class="col-sm-4">
                                                <s:textfield name="headerDetailCheckup.nama"
                                                             required="false" readonly="false"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">Pelayanan</label>
                                            <div class="col-sm-4">
                                                <s:textfield disabled="true" value="IGD"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">Status</label>
                                            <div class="col-sm-4">
                                                <s:select list="#{'2':'Rujuk','3':'Selesai'}" cssStyle="margin-top: 7px"
                                                          id="status" name="headerDetailCheckup.statusPeriksa"
                                                          headerKey="1" headerValue="Periksa"
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
                                                    <s:textfield name="headerDetailCheckup.stDateFrom" cssClass="form-control datepicker2"
                                                                 required="false"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="input-group date" style="margin-top: 7px">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <s:textfield name="headerDetailCheckup.stDateTo" cssClass="form-control datepicker2"
                                                                 required="false"/>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4"></label>
                                            <div class="col-sm-6" style="margin-top: 7px">
                                                <sj:submit type="button" cssClass="btn btn-success" formIds="igdForm"
                                                           id="search2" name="search2"
                                                           onClickTopics="showDialogLoading"
                                                           onCompleteTopics="closeDialogLoading">
                                                    <i class="fa fa-search"></i>
                                                    Search
                                                </sj:submit>
                                                <a href="addRawatIgd_rawatinap.action" type="button" class="btn btn-primary"><i class="fa fa-plus"></i> Pendaftaran IGD</a>
                                                <a type="button" class="btn btn-danger" href="initTppri_rawatinap.action">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </a>
                                            </div>
                                        </div>
                                    </s:form>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Rawat IGD Pasien</h3>
                            </div>
                            <div class="box-body">
                                <table id="myTable-2" class="table table-bordered table-striped" style="font-size: 13px">
                                    <thead >
                                    <tr bgcolor="#30d196">
                                        <td>ID Detail Checkup</td>
                                        <td>No RM</td>
                                        <td>Nama</td>
                                        <td>Umur</td>
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
                                            <td><s:property value="umur"/></td>
                                            <td><s:property value="formatTglMasuk"/></td>
                                            <td><s:property value="desa"/></td>
                                            <td><s:property value="statusPeriksaName"/></td>
                                            <td align="center">
                                                <script>
                                                    document.write(changeJenisPasien('<s:property value="idJenisPeriksaPasien"/>', '<s:property value="jenisPeriksaPasien"/>'));
                                                </script>
                                            </td>
                                            <td align="center">
                                                <s:if test='#row.isTindakan == "N"'>
                                                    <a href="addRawatIgd_rawatinap.action?id=<s:property value="idDetailCheckup"/>">
                                                        <img style="cursor: pointer" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-orange-25.png"/>">
                                                    </a>
                                                    <img onclick="cancelPeriksa('<s:property value="idDetailCheckup"/>', 'igd')" style="cursor: pointer" class="hvr-grow" src="<s:url value="/pages/images/cancel-flat-new.png"/>">
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
            </div>
        </div>
    </section>
</div>


<div class="modal fade" id="modal-detail">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> TPPRI</h4>
            </div>
            <div class="modal-body" style="height: 70%;overflow-y: scroll;">
                <div class="alert alert-success alert-dismissible" style="display: none" id="success">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_suc"></p>
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>No RM</b></td>
                                    <td><span id="no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No Checkup </b></td>
                                    <td><span id="no_detail_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="nama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, Tanggal Lahir</b></td>
                                    <td><span id="tgl"></span></td>
                                </tr>
                                <tr style="display: none" id="kelas_bpjs">
                                    <td><b>Kelas Kamar BPJS</b></td>
                                    <td><span id="id_kelas"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Rekam Medis</b></td>
                                    <td>
                                        <div class="btn-group dropdown">
                                            <button onclick="getListRekamMedis('tppri', '', $('#h_id_detail_pasien').val())"
                                                    type="button" class="btn btn-primary"><i class="fa fa-edit"></i>
                                                Asesmen
                                            </button>
                                            <button onclick="getListRekamMedis('tppri', '', $('#h_id_detail_pasien').val())"
                                                    type="button" class="btn btn-primary dropdown-toggle"
                                                    data-toggle="dropdown" style="height: 34px">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu" id="asesmen_rj">
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <input type="hidden" id="h_id_pasien">
                        <input type="hidden" id="h_id_detail_pasien">
                        <input type="hidden" id="h_no_checkup">
                        <input type="hidden" id="h_jenis_pasien">
                        <input type="hidden" id="h_tindak_lanjut">
                        <input type="hidden" id="h_kategori">

                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="30%"><b>Alamat</b></td>
                                    <td><span id="alamat"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Poli</b></td>
                                    <td><span id="poli"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Diagnosa</b></td>
                                    <td><span id="diagnosa"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tindak Lanjut</b></td>
                                    <td><span id="tindak_lanjut"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jenis Pasien</b></td>
                                    <td><span id="jenis_pasien"></span></td>
                                </tr>
                                <tr style="display: none" id="form-id_kelas">
                                    <td><b>Hak Kamar</b></td>
                                    <td><span
                                            style="background-color: #ec971f; color: white; border-radius: 5px; border: 1px solid black; padding: 5px"
                                            id="hak_kamar"></span></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_war"></p>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b><i class="fa fa-stethoscope"></i> Instruksi Tindak
                            Lanjut</b></label>
                    </div>
                    <div class="modal" id="top_up"></div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-2">
                                <button onclick="tambahDPJP('')" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                                    DPJP
                                </button>
                            </div>
                            <div class="col-md-6">
                                <p style="color: red; display: none" id="msg_dpjp" class="blink_me">*silahkan pilih
                                    dokter terlebih dahulu...</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Dokter DPJP</label>
                            <div class="col-md-4">
                                <select class="form-control select2 id_dpjp" id="dokter_dpjp_1" style="width: 100%"
                                        onchange="$('#msg_dpjp').hide()">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control dpjp" style="margin-top: 7px" id="jenis_dpjp_1" value="dpjp_1"
                                       disabled="disabled">
                            </div>
                        </div>
                    </div>
                    <div id="set_dpjp"></div>
                    <div class="row" style="display: none" id="form-kelas_kamar">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Kelas Kamar</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="kelas_kamar" style="width: 100%">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" style="display: none" id="form-kamar">
                            <label class="col-md-3" style="margin-top: 10px"><span id="label_kamar"></span></label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="kamar" style="width: 100%">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" style="display: none" id="form-is-uangmuka">
                            <label class="col-md-3" style="margin-top: 10px">Ada uang muka?</label>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" id="cek_uang_muka" value="Y" onclick="cekUangMuka(this.id)">
                                    <label for="cek_uang_muka"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="form-metode_pembayaran">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Metode Pembayaran</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="metode_bayar" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="tunai">Tunai</option>
                                    <option value="non_tunai">Non Tunai</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="form-uang_muka">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Uang Muka</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        Rp.
                                    </div>
                                    <input class="form-control" id="val_uang_muka"
                                           oninput="convertRp(this.id, this.value)">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Boleh Dikunjungin ?</label>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" id="cek_kunjungan" value="Y">
                                    <label for="cek_kunjungan"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-3" style="margin-top: 10px">Upload Berkas</label>
                        <div class="col-md-6">
                            <div class="input-group">
                                 <span class="input-group-btn">
                              <span class="btn btn-default btn-file">
                               Browse… <input type="file" id="berkas" accept=".jpg" onchange="setCanvasAtasWithText('img_berkas','url_berkas')">
                              </span>
                              </span>
                                <canvas id="img_berkas" style="display: none"></canvas>
                                <input type="text" id="url_berkas" class="form-control" readonly style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fin" onclick="confirm()"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_fin"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-daftar-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Daftar Pasien Baru Lahir</h4>
            </div>
            <div class="modal-body" style="height: 70%; overflow-y: scroll" id="back_top">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div id="warn-bpjs">
                </div>
                <input type="hidden" id="status_bpjs">
                <input type="hidden" id="kelas_pasien">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Jenis Pasien</label>
                                <select class="form-control" id="add_jenis"
                                        onchange="$(this).css('border',''); choiceJenisPasien(this.value)">
                                    <option value="umum">Umum</option>
                                    <option value="bpjs">BPJS</option>
                                </select>
                            </div>
                            <div class="form-group" style="display: none" id="form_no_bpjs">
                                <label style="margin-top: 7px">No BPJS</label>
                                <div class="input-group">
                                    <input type="number" class="form-control" id="add_no_bpjs" oninput="$(this).css('border','')">
                                    <div class="input-group-addon" style="cursor: pointer"
                                         onclick="cekBpjs(this.value)" id="btn-cek">
                                        <i class="fa fa-search"></i> Check
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">NIK</label>
                                <input class="form-control" id="add_nik" oninput="$(this).css('border','')" type="number">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Nama</label>
                                <input class="form-control" id="add_nama" oninput="$(this).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Jenis Kelamin</label>
                                <select class="form-control" id="add_jk" onchange="$(this).css('border','')">
                                    <option value="">[Select One]</option>
                                    <option value="L">Laki-Laki</option>
                                    <option value="P">Perempuan</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Tempat Lahir</label>
                                <input class="form-control" id="add_tempat_lahir" oninput="$(this).css('border',''); setKotaKab(this.id)">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Tanggal Lahir</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control datepicker datemask" id="add_tanggal_lahir" readonly
                                           onchange="$(this).css('border','')" style="cursor: pointer" placeholder="yyyy-mm-dd">
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Agama</label>
                                <select class="form-control" id="add_agama" onchange="$(this).css('border','')">
                                    <option value="">[Select One]</option>
                                    <option value="Islam">Islam</option>
                                    <option value="Kristen">Kristen</option>
                                    <option value="Katolik">Katolik</option>
                                    <option value="Hindu">Hindu</option>
                                    <option value="Konguchu">Konguchu</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Profesi</label>
                                <input class="form-control" id="add_profesi">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Suku</label>
                                <input class="form-control" id="add_suku">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Alamat</label>
                                <input class="form-control" id="add_alamat">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">No Telp.</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-phone"></i>
                                    </div>
                                    <input class="form-control" id="add_no_telp" oninput="$(this.id).css('border','')" onchange="$(this.id).css('border','')"
                                           data-inputmask="'mask': ['9999-9999-9999']"
                                           data-mask="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Provinsi</label>
                                <input class="form-control" id="add_provinsi" oninput="$(this).css('border',''); setProvAtas(this.id, 'add_id_provinsi')">
                                <input type="hidden" id="add_id_provinsi">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Kota</label>
                                <input class="form-control" id="add_kota" oninput="$(this).css('border',''); setKabAtas(this.id, 'add_id_kota', 'add_id_provinsi')">
                                <input type="hidden" id="add_id_kota">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Kecamatan</label>
                                <input class="form-control" id="add_kecamatan" oninput="$(this).css('border',''); setKecAtas(this.id, 'add_id_kecamatan', 'add_id_kota')">
                                <input type="hidden" id="add_id_kecamatan">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Kelurahan/Desa</label>
                                <input class="form-control" id="add_desa" oninput="$(this).css('border',''); setDesAtas(this.id, 'add_id_desa', 'add_id_kecamatan')">
                                <input type="hidden" id="add_id_desa">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Foto Identitas</label>
                                <div class="input-group" id="img_file" style="margin-top: -7px">
                              <span class="input-group-btn">
                              <span class="btn btn-default btn-file">
                               Browse… <s:file accept=".jpg" name="fileUpload" id="ktp"
                                               onchange="$('#img_file').css('border',''); setCanvas('img_ktp_canvas')"></s:file></span>
                              </span>
                                    <input type="text" class="form-control" readonly style="margin-top: 7px">
                                </div>
                                <canvas id="img_ktp_canvas"
                                        style="border: solid 1px #ccc; width: 100%; height: 200px"></canvas>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Diagnosa</label>
                                <input class="form-control" id="add_id_diagnosa"
                                       oninput="showDiagnosa(this.value, this.id); $(this.id).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Keterangan Diagnosa</label>
                                <textarea class="form-control" id="add_keterangan_diagnosa" readonly></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="form_data_rujukan" style="display: none">
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user"></i> Data Rujukan</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Perujuk/Asal</label>
                                    <select class="form-control" id="add_perujuk">
                                        <option value="1">PPK 1 - [Puskesmas]</option>
                                        <option value="2">PPK 2 - [Rumah Sakit Lain]</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">No Rujukan</label>
                                    <div class="input-group">
                                        <input class="form-control" id="add_no_rujukan">
                                        <div class="input-group-addon" style="cursor: pointer" onclick="">
                                            <i class="fa fa-search"></i> Check
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Keterangan Perujuk</label>
                                    <input class="form-control" id="add_keterangan">
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">No PPK Rujukan</label>
                                    <input class="form-control" id="add_ppk_rujukan">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Tanggal Rujukan</label>
                                    <input class="form-control datepicker datemask" id="add_tgl_rujukan">
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Foto Surat Rujukan</label>
                                    <div class="input-group" style="margin-top: -7px">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn-file">
                                                            Browse… <s:file id="foto_rujukan" accept=".jpg"
                                                                            onchange="setCanvas('add_foto_rujukan')"
                                                                            name="fileUploadDoc"></s:file>
                                                        </span>
                                                    </span>
                                        <input type="text" class="form-control" readonly style="margin-top: 7px">
                                    </div>
                                </div>
                                <canvas id="add_foto_rujukan" style="display: none"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Rawat Inap</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-2">
                                <button onclick="tambahDPJP('add')" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                                    DPJP
                                </button>
                            </div>
                            <div class="col-md-6">
                                <p style="color: red; display: none" id="msg_add_dpjp" class="blink_me">*silahkan pilih
                                    dokter terlebih dahulu...</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Dokter DPJP</label>
                            <div class="col-md-4">
                                <select class="form-control select2 add_id_dpjp" id="dokter_add_dpjp_1" style="width: 100%"
                                        onchange="$('#msg_add_dpjp').hide()">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control add_dpjp" style="margin-top: 7px" value="dpjp_1"
                                       disabled="disabled" placeholder="DPJP 1">
                            </div>
                        </div>
                    </div>
                    <div id="set_add_dpjp"></div>
                    <div class="row" id="form-add-kelas_kamar">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Kelas Kamar</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="add_kelas_kamar" style="width: 100%"
                                        onchange="getKamar(this.value, 'rawat_inap')">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" id="form-add-kamar">
                            <label class="col-md-3" style="margin-top: 10px">Kamar</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="add_kamar" style="width: 100%">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" id="form_is_uang_muka">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Ada uang muka?</label>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" id="cek_add_uang_muka" value="Y"
                                           onclick="cekUangMukaNew(this.id)">
                                    <label for="cek_add_uang_muka"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="form_uang_muka" style="display: none">
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 10px">Metode Pembayaran</label>
                                <div class="col-md-4">
                                    <select class="form-control select2" id="add_metode_bayar" style="width: 100%">
                                        <option value="">[Select One]</option>
                                        <option value="tunai">Tunai</option>
                                        <option value="non_tunai">Non Tunai</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 10px">Uang Muka</label>
                                <div class="col-md-4">
                                    <div class="input-group" style="margin-top: 7px">
                                        <div class="input-group-addon">
                                            Rp.
                                        </div>
                                        <input class="form-control" id="add_uang_muka"
                                               oninput="formatRupiahAtas(this.id, this.value, 'h_uang_muka')">
                                        <input type="hidden" id="h_uang_muka">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add" onclick="cekSaveRB()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_add"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-check"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-temp"></div>

<div class="modal fade" id="modal-detail_cancel">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Batal Rawat Inap</h4>
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
                                    <td><span id="det_no_checkup_cancel"></span></td>
                                </tr>
                                <tr>
                                    <td>ID Detail Checkup</td>
                                    <td><span id="det_id_detail_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td>NO RM</td>
                                    <td><span id="det_no_rm_cancel"></span></td>
                                </tr>
                                <tr>
                                    <td>Nama Pasien</td>
                                    <td><span id="det_nama_pasien"></span></td>
                                </tr>
                                <%--<tr>--%>
                                    <%--<td>Ruangan</td>--%>
                                    <%--<td><span id="det_ruangan"></span></td>--%>
                                <%--</tr>--%>
                                <tr>
                                    <td>Jenis Pasien</td>
                                    <td><span id="det_jenis_pasien"></span></td>
                                </tr>
                                <tr>
                                    <td>Alamat</td>
                                    <td><span id="det_alamat_batal"></span></td>
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
                <button type="button" class="btn btn-success" id="save_cancel" ><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_cancel"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ttd_concent">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Persetujuan Umum/General Consent</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ttd_concent">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_ttd_concent"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <p>Pasien dan atau wali diminta membaca, memahami, dan mengerti format berikutnya Yang bertanda tangan di bawah ini:</p>
                            <p>Nama : <span id="nama_gc"></span></p>
                            <p>Tanggal Lahir : <span id="tanggal_gc"></span></p>
                            <p>Nomor RM : <span id="rm_gc"></span></p>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-12">
                            <ol>
                                <ol type="I">
                                    <li>Persetujuan Untuk Perawatan dan Pengobatan</li>
                                    <ol type="a" style="margin-left: 17px">
                                        <li>Saya mengetahui bahwa Saya memiliki kondisi yang membutuhkan perawatan medis, Saya memberi izin kepada dokter dan profesi kesehatan lainnya untuk melakukan prosedur diagnostik dan untuk memberi pengobatan medis seperti yang diperlukan untuk penilaian secara profesional. Prosedur diagnostik dan perawatan medis termasuk tetapi tidak terbatas pada ECG, X Ray, Tes Darah, terapi fisik dan pemberiaan obat.</li>
                                        <li>Saya sadar bahwa praktek kedokteran dan ilmu bedah bukanlah ilmu pasti dan Saya mengakui bahwa tidak ada jaminan atas hal apapun, terhadap perawatan prosedur atau pemeriksaan apapun yang dilakukan kepada saya</li>
                                        <li>Saya mengerti dan memahami bahwa:</li>
                                        <ol type="b" style="margin-left: 17px">
                                            <li>Saya memiliki hak untuk menanyakan tentang pengobatan yang diusulkan termasuk identitas setiap orang yang memberikan atau mengamati pengobatan setiap saat</li>
                                            <li>Saya memiliki hak untuk persetujuan, atau menolak persetujuan untuk setiap prosedur atau terapi (injeksi, rawat luka, pemasangan gips, infus, pemeriksaan penunjang lain)</li>
                                        </ol>
                                        <li>Privasi: Saya memberi kuasa kepada " + branchName + " untuk menjaga privasi dan kerahasiaan penyakit saya selama dalam perawatan</li>
                                        <li>Rahasia Kedokteran: Saya setuju kepada " + branchName + " wajib menjamin rahasia kedokteran Saya baik untuk kepentingan perawatan atau pengobatan, pendidikan maupun penelitian, kecuali saya mengucapkan sendiri atau orang lain yang saya beri kuasa sebagai penjamin, Saya setuju untuk membuka rahasia kedokteran terkait dengan kondisi kesehatan, asuhan dan pengobatan yang saya terima kepada</li>
                                        <ol type="a" style="margin-left: 17px">
                                            <li>Dokter atau tenaga kesehatan yang memberikan asuhan kesehatan kepada saya</li>
                                            <li>Perusahaan asuransi kesehatan BPJS atau perusahaan lainnya atau pihak lain yang menjamin pembiayaan saya</li>
                                            <li>Pihak lain yang saya kehendaki</li>
                                        </ol>
                                    </ol>
                                    <li>Barang-Barang Milik Pasien</li>
                                    <ol type="a" style="margin-left: 17px">
                                        <li>Saya telah mengerti bahwa rumah sakit tidak bertanggung jawab atas semua kehilangan barang-barang milik saya, dan saya secara pribadi bertanggung jawab terhadap barang berharga yang saya miliki diantaranya uang, perhiasan, buku, cek, handphone, kartu kredit serta barang-barang berharga lainnya. dan apabila saya membutuhkan maka saya dapat menitipkan barang-barang saya kepada rumah sakit</li>
                                    </ol>
                                </ol>
                            </ol>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>Keluarga</label>
                                <canvas class="paint-canvas-ttd" id="ttd_keluarga" width="220"
                                        onmouseover="paintTtd('ttd_keluarga')"></canvas>
                                <input class="form-control" id="nama_pasien_1" placeholder="Nama Pasien/Keluarga">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_keluarga')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6 text-center">
                                <label>Pemberi Informasi</label>
                                <canvas class="paint-canvas-ttd" id="ttd_pemberi" width="220"
                                        onmouseover="paintTtd('ttd_pemberi')"></canvas>
                                <input class="form-control" id="nama_pemberi_1" placeholder="Nama Pemberi">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_pemberi')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_ttd_concent"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_ttd_concent"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

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
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i>
                    Tidak
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con_rm"><i class="fa fa-check"></i> Ya
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript' src='<s:url value="/dwr/interface/RingkasanPasienAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenRawatInapAction.js"/>'></script>

<script type="text/javascript" src="<s:url value="/pages/dist/js/datapasien.js"/>"></script>
<script type="text/javascript" src="<s:url value="/pages/dist/js/ringkasanpasien.js"/>"></script>
<script type="text/javascript" src="<s:url value="/pages/dist/js/paintTtd.js"/>"></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenrawatinap.js"/>'></script>

<script type='text/javascript'>

    var isReadRM = false;
    var contextPath = '<%= request.getContextPath() %>';
    var tempidRm = "";
    var noCheckup = "";
    var tempTensi = "";
    var tempSuhu = "";
    var tempNadi = "";
    var tempRr = "";
    var tempBerat = "";
    var tempTinggi = "";
    var tempAnmnesa = "";
    var idDetailCheckup = "";
    var namaPasien = "";
    var alamatLengkap = "";
    var noBpjs = "";
    var umur = "";
    var jenisKelamin = "";
    var diagnosa = "";
    var idPasien = "";
    var tglLahir = "";
    var tanggalMasuk = "";

    function printGelangPasien(noCheckup) {
        window.open('printGelangPasien_rawatinap.action?id=' + noCheckup, '_blank');
    }

    function convertRp(id, val) {
        $('#' + id).val(formatRupiahAtas2(val));
    }

    function detail(noCKP, idDCP, idPsn, tindakLanjut, keteranganSelesai) {
        idDetailCheckup = idDCP;
        noCheckup = noCKP;
        idPasien = idPsn;
        startSpinner('t_', idDCP);
        dwr.engine.setAsync(true);
        CheckupAction.listDataPasien(idDCP, {
                callback: function (res) {
                    if (res.idPasien != null) {
                        stopSpinner('t_', idDCP);
                        $('#kelas_kamar').html('');
                        $('#kamar').html('');
                        getDokterDpjp('dokter_dpjp_1', null);
                        if (res.idJenisPeriksaPasien == "umum") {
                            $('#form-is-uangmuka').show();
                        } else {
                            $('#form-metode_pembayaran').hide();
                            $('#form-uang_muka').hide();
                        }

                        if (tindakLanjut == "rawat_inap") {
                            getKelasKamar('rawat_inap');
                            $('#kelas_kamar').attr('onchange', 'getKamar(this.value, \'rawat_inap\')');
                            $('#label_kamar').text('Kamar');
                            $('#form-kelas_kamar').show();
                            $('#form-kamar').show();
                            $('#h_kategori').val('rawat_inap');
                        } else if (tindakLanjut == "rawat_intensif") {
                            getKamar(null, 'rawat_intensif');
                            getKelasKamar('rawat_intensif');
                            $('#label_kamar').text('Kamar Intensif');
                            $('#form-kamar').show();
                            $('#form-kelas_kamar').show();
                            $('#h_kategori').val('rawat_intensif');
                        } else if (tindakLanjut == "rawat_isolasi") {
                            getKamar(null, 'rawat_isolasi');
                            getKelasKamar('rawat_isolasi');
                            $('#label_kamar').text('Kamar Isolasi');
                            $('#form-kamar').show();
                            $('#form-kelas_kamar').show();
                            $('#h_kategori').val('rawat_isolasi');
                        } else if (tindakLanjut == "kamar_operasi") {
                            // getKamar(null, 'kamar_operasi');
                            $('#kelas_kamar').attr('onchange', 'getKamar(this.value, \'kamar_operasi\')');
                            getKelasKamar('kamar_operasi');
                            $('#label_kamar').text('Kamar Operasi');
                            $('#form-kamar').show();
                            $('#form-kelas_kamar').show();
                            $('#h_kategori').val('kamar_operasi');
                        } else if (tindakLanjut == "ruang_bersalin") {
                            // getKamar(null, 'ruang_bersalin');
                            $('#kelas_kamar').attr('onchange', 'getKamar(this.value, \'ruang_bersalin\')');
                            getKelasKamar('ruang_bersalin');
                            $('#label_kamar').text('Kamar Bersalin');
                            $('#form-kamar').show();
                            $('#form-kelas_kamar').show();
                            $('#h_kategori').val('ruang_bersalin');
                        } else {
                            $('#form-kamar').hide();
                            $('#form-kelas_kamar').hide();
                        }

                        var jk = "";
                        var alamat = res.namaDesa + ", " + res.namaKecamatan + ", " + res.namaKota;
                        var diagnosa = res.diagnosa + "-" + res.namaDiagnosa;
                        if (res.jenisKelamin == "L") {
                            jk = "Laki-Laki";
                        } else {
                            jk = "Perempuan";
                        }

                        if (res.idJenisPeriksaPasien == "ptpn" || res.idJenisPeriksaPasien == "bpjs") {
                            $('#form-id_kelas').show();
                            $('#hak_kamar').text(res.kelasPasien);
                        } else {
                            $('#form-id_kelas').hide();
                        }

                        $('#no_rm').html(res.idPasien);
                        $('#no_detail_checkup').html(noCKP);
                        $('#nik').html(res.noKtp);
                        $('#nama').html(res.nama);
                        $('#jenis_kelamin').html(jk);
                        $('#tgl').html(res.tempatLahir + ", " + converterDate(new Date(res.tglLahir)));
                        $('#alamat').html(alamat);
                        $('#poli').html(res.namaPelayanan);
                        $('#jenis_pasien').html(changeJenisPasien(res.idJenisPeriksaPasien ,res.statusPeriksaName));
                        $('#diagnosa').html(diagnosa);
                        $('#h_id_pasien').val(res.idPasien);
                        $('#h_id_detail_pasien').val(res.idDetailCheckup);
                        $('#h_id_pelayanan').val(res.idPelayanan);
                        $('#h_jenis_pasien').val(res.idJenisPeriksaPasien);
                        $('#h_no_checkup').val(res.noCheckup);
                        $('#h_tindak_lanjut').val(tindakLanjut);
                        $('#tindak_lanjut').html(keteranganSelesai);
                        $('#save_fin').show();
                        $('#load_fin').hide();
                        $('#dokter_dpjp_1').removeAttr('disabled');
                        $('#kelas_kamar').removeAttr('disabled');
                        $('#msg_dpjp').hide();
                        $('#modal-detail').modal({show: true, backdrop: 'static'});
                        namaPasien = res.nama;

                        $('#nama_gc').text(namaPasien);
                        $('#tanggal_gc').text(converterDate(new Date(res.tglLahir)));
                        $('#rm_gc').text(idPsn);
                    }
                }
            });
    }

    function tambahDPJP(tipe) {
        if(tipe == 'add'){
            var cekDpjp = $('.add_dpjp').length;
            var cekValue = $('#dokter_add_dpjp_' + cekDpjp).val();
            var cekJenis = $('#jenis_add_dpjp_' + cekDpjp).val();
            if (cekValue != '' && cekJenis != '') {
                var idDpjp = $('.add_id_dpjp');
                var idIsi = "";
                var aw = "(";
                var ak = ")";
                var valId = null;
                $.each(idDpjp, function (i, item) {
                    if (item.value != '' && item.value) {
                        var sp = item.value.split("|");
                        if (idIsi != '') {
                            idIsi = idIsi + ", " + "'" + sp[0] + "'";
                        } else {
                            idIsi = "'" + sp[0] + "'";
                        }
                    }
                });
                if (idIsi != '') {
                    valId = aw + idIsi + ak;
                }
                var count = cekDpjp + 1;
                $('#dokter_add_dpjp_' + cekDpjp).attr('disabled', 'disabled');
                $('#jenis_add_dpjp_' + cekDpjp).attr('disabled', 'disabled');
                var html = '<div class="row" id="set_dpjp_' + count + '">\n' +
                    '<div class="form-group">\n' +
                    '    <div class="col-md-offset-3 col-md-4">\n' +
                    '        <select class="form-control select2 add_id_dpjp" id="dokter_add_dpjp_' + count + '" style="width: 100%"\n' +
                    '            <option value=\'\'>[Select One]</option>\n' +
                    '        </select>\n' +
                    '    </div>\n' +
                    '    <div class="col-md-4">\n' +
                    '<select class="form-control select2 add_dpjp" id="jenis_add_dpjp_' + count + '">' +
                    '<option value="">[Select One]</option>' +
                    '<option value="konsultasi">Konsultasi</option>' +
                    '<option value="rawat_bersama">Rawat Bersama</option>' +
                    '</select>' +
                    '    </div>\n' +
                    '    <div class="col-md-1">\n' +
                    '        <button onclick="delDpjp(\'set_dpjp_' + count + '\')" style="margin-top: 8px; margin-left: -20px" class="btn btn-danger"><i class="fa fa-trash"></i></button>\n' +
                    '    </div>\n' +
                    '</div>\n' +
                    '</div>';
                $('#set_add_dpjp').append(html);
                $('.select2').select2();
                getDokterDpjp('dokter_add_dpjp_' + count, valId);
            } else {
                $('#msg_add_dpjp').show();
            }
        }else{
            var cekDpjp = $('.dpjp').length;
            var cekValue = $('#dokter_dpjp_' + cekDpjp).val();
            var cekJenis = $('#jenis_dpjp_' + cekDpjp).val();
            if (cekValue != '' && cekJenis != '') {
                var idDpjp = $('.id_dpjp');
                var idIsi = "";
                var aw = "(";
                var ak = ")";
                var valId = null;
                $.each(idDpjp, function (i, item) {
                    if (item.value != '' && item.value) {
                        var sp = item.value.split("|");
                        if (idIsi != '') {
                            idIsi = idIsi + ", " + "'" + sp[0] + "'";
                        } else {
                            idIsi = "'" + sp[0] + "'";
                        }
                    }
                });
                if (idIsi != '') {
                    valId = aw + idIsi + ak;
                }
                var count = cekDpjp + 1;
                $('#dokter_dpjp_' + cekDpjp).attr('disabled', 'disabled');
                $('#jenis_dpjp_' + cekDpjp).attr('disabled', 'disabled');
                var html = '<div class="row" id="set_dpjp_' + count + '">\n' +
                    '<div class="form-group">\n' +
                    '    <div class="col-md-offset-3 col-md-4">\n' +
                    '        <select class="form-control select2 id_dpjp" id="dokter_dpjp_' + count + '" style="width: 100%"\n' +
                    '            <option value=\'\'>[Select One]</option>\n' +
                    '        </select>\n' +
                    '    </div>\n' +
                    '    <div class="col-md-4">\n' +
                    '<select class="form-control select2 dpjp" id="jenis_dpjp_' + count + '">' +
                    '<option value="">[Select One]</option>' +
                    '<option value="konsultasi">Konsultasi</option>' +
                    '<option value="rawat_bersama">Rawat Bersama</option>' +
                    '</select>' +
                    '    </div>\n' +
                    '    <div class="col-md-1">\n' +
                    '        <button onclick="delDpjp(\'set_dpjp_' + count + '\')" style="margin-top: 8px; margin-left: -20px" class="btn btn-danger"><i class="fa fa-trash"></i></button>\n' +
                    '    </div>\n' +
                    '</div>\n' +
                    '</div>';
                $('#set_dpjp').append(html);
                $('.select2').select2();
                getDokterDpjp('dokter_dpjp_' + count, valId);
            } else {
                $('#msg_dpjp').show();
            }
        }
    }

    function delDpjp(id) {
        $('#' + id).remove();
    }

    function getDokterDpjp(id, idDpjp) {
        var option = '<option value="">[Select One]</option>';
        CheckupAction.getListDokterByBranchId(idDpjp, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idDokter + '|' + item.idPelayanan + '|' + item.sip + '|' + item.namaDokter + '">' + item.namaDokter + ' - ' + item.namaPelayanan + '</option>';
                });
                $('#' + id).html(option);
            } else {
                $('#' + id).html(option);
            }
        });
    }

    function getKelasKamar(kategori) {
        var option = '';
        var cekDis = false;
        if (kategori == "rawat_inap" || kategori == 'ruang_bersalin' || kategori == 'kamar_operasi') {
            option = '<option value="">[Select One]</option>';
            cekDis = true;
        }
        dwr.engine.setAsync(true);
        CheckupDetailAction.getListKelasKamar(kategori, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idKelasRuangan + '">' + item.namaKelasRuangan + '</option>';
                });
                if (cekDis) {
                    $('#kelas_kamar').html(option);
                    $('#add_kelas_kamar').html(option);
                } else {
                    $('#kelas_kamar').html(option);
                    $('#kelas_kamar').attr('disabled', 'disabled');
                }
            } else {
                $('#kelas_kamar').html(option);
            }
        });
    }

    function getKamar(idKelas, kategori) {
        var option = '<option value="">[Select One]</option>';
        dwr.engine.setAsync(true);
        CheckupDetailAction.listRuangan(idKelas, true, kategori, {
            callback: function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idTempatTidur + "'>" + '[' + item.noRuangan + "]-" + item.namaRuangan + "-[" + item.namaTempatTidur + "]</option>";
                    });
                    $('#kamar').html(option);
                    $('#add_kamar').html(option);
                } else {
                    $('#kamar').html(option);
                }
            }
        });
    }

    function confirm() {
        var cek = false;
        var jenisPasien = $('#h_jenis_pasien').val();
        var tindakLanjut = $('#h_tindak_lanjut').val();
        var kelasKamar = $('#kelas_kamar').val();
        var kamar = $('#kamar').val();
        var metodeBayar = $('#metode_bayar').val();
        var uangMuka = $('#val_uang_muka').val();
        var dataDpjp = [];
        var dataDokter = $('.id_dpjp');
        var dataPrio = $('.dpjp');
        var isUangMuka = $('#cek_uang_muka').is(':checked');
        $.each(dataDokter, function (i, item) {
            if (item.value != '') {
                var data = item.value.split("|");
                var idDokter = data[0];
                var idPelayanan = data[1];
                dataDpjp.push({
                    'id_dpjp': idDokter,
                    'id_pelayanan': idPelayanan,
                    'prioritas': dataPrio[i].value
                });
            }
        });

        if (tindakLanjut == "rawat_inap") {
            if (dataDpjp.length > 0 && kelasKamar && kamar != '') {
                if (jenisPasien == "umum") {
                    if (isUangMuka) {
                        if (metodeBayar && uangMuka != '') {
                            cek = true;
                        }
                    } else {
                        cek = true;
                    }
                } else {
                    cek = true;
                }
            }
        } else {
            if (dataDpjp.length > 0 && kamar != '') {
                if (jenisPasien == "umum") {
                    if (isUangMuka) {
                        if (metodeBayar && uangMuka != '') {
                            cek = true;
                        }
                    } else {
                        cek = true;
                    }
                } else {
                    cek = true;
                }
            }
        }

        if (cek) {
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'saveTppri()');
        } else {
            // $('#').scrollTop(0);
            $('#warning').show().fadeOut(5000);
            $('#msg_war').text("Silahkan cek kembali data inputan anda...!");
        }

    }

    function saveTppri() {
        $('#modal-confirm-dialog').modal('hide');
        if(!cekSession()){
            var data = "";
            var dataDpjp = [];
            var dataDokter = $('.id_dpjp');
            var dataPrio = $('.dpjp');
            var idPasien = $('#h_id_pasien').val();
            var noCheckup = $('#h_no_checkup').val();
            var idDetailCheckup = $('#h_id_detail_pasien').val();
            var jenisPasien = $('#h_jenis_pasien').val();
            var tindakLanjut = $('#h_tindak_lanjut').val();
            var kategori = $('#h_kategori').val();
            var metodeBayar = $('#metode_bayar').val();
            var valUangMuka = $('#val_uang_muka').val();
            var uangMuka = "";
            if (valUangMuka != undefined) {
                uangMuka = valUangMuka.replace(/[.]/g, '');
            }
            var kelasKamar = $('#kelas_kamar').val();
            var kamar = $('#kamar').val();
            var cekKunjungan = $('#cek_kunjungan').is(':checked');
            var cekBerkas = $('#berkas').val();
            var finalBerkas = "";
            if(cekBerkas != ''){
                finalBerkas = convertToDataURL(document.getElementById('img_berkas'));
            }

            var bolehKunjungan = "N";
            if(cekKunjungan){
                bolehKunjungan = "Y"
            }
            $.each(dataDokter, function (i, item) {
                if (item.value != '') {
                    var data = item.value.split("|");
                    var idDokter = data[0];
                    var idPelayanan = data[1];
                    dataDpjp.push({
                        'id_dpjp': idDokter,
                        'id_pelayanan': idPelayanan,
                        'prioritas': dataPrio[i].value
                    });
                }
            });

            data = {
                'id_pasien': idPasien,
                'no_checkup': noCheckup,
                'id_detail_checkup': idDetailCheckup,
                'jenis_pasien': jenisPasien,
                'tindak_lanjut': tindakLanjut,
                'data_dpjp': dataDpjp,
                'kelas_kamar': kelasKamar,
                'kamar': kamar,
                'metode_pembayaran': metodeBayar,
                'uang_muka': uangMuka,
                'kunjungan': bolehKunjungan,
                'img_berkas': finalBerkas
            }

            var result = JSON.stringify(data);
            $('#save_fin').hide();
            $('#load_fin').show();
            dwr.engine.setAsync(true);
            RawatInapAction.saveTppri(result,
                {
                    callback: function (response) {
                        if (response.status == "success") {
                            $('#save_fin').show();
                            $('#load_fin').hide();
                            $('#info_dialog').dialog('open');
                            $('#modal-detail').modal('hide');
                            $('body').scrollTop(0);
                            console.log(response);
                        } else {
                            $('#save_fin').show();
                            $('#load_fin').hide();
                            $('#warning').show().fadeOut(5000);
                            $('#msg_war').text(response.msg);
                        }
                    }
                });
        }
    }

    function printPernyataan(kode, idRm, flag, namaRm) {
        if(kode == "CK01"){
            KeperawatanRawatJalanAction.getListAsesmenRawat(noCheckup, "general_concent", function (res) {
                console.log(res);
                console.log(noCheckup);
                if(res.length > 0){
                    $('#tanya').text("Apakah anda yakin print ?");
                    $('#print_form').text(namaRm);
                    $('#save_con_rm').attr('onclick', 'printPernyataanRM(\'' + kode + '\', \'' + idRm + '\')');
                    $('#modal-confirm-rm').modal('show');
                }else{
                    $('#save_ttd_concent').attr('onclick', 'saveResume(\'' + kode + '\', \'' + idRm + '\')');
                    $('#modal-ttd_concent').modal('show');
                }
            });
        }else{
            $('#tanya').text("Apakah anda yakin print ?");
            $('#print_form').text(namaRm);
            $('#save_con_rm').attr('onclick', 'printPernyataanRM(\'' + kode + '\', \'' + idRm + '\')');
            $('#modal-confirm-rm').modal('show');
        }
    }

    function printPernyataanRM(kode, idRM) {
        var idDetailCheckup = $('#h_id_detail_pasien').val();
        window.open(contextPathHeader + '/rekammedik/printSuratPernyataan_rekammedik?id=' + idDetailCheckup + '&tipe=' + kode + '&ids=' + idRM, '_blank');
        $('#modal-confirm-rm').modal('hide');
    }

    function saveResume(kode, idRm){
        var data = [];
        var ttd1 = document.getElementById('ttd_keluarga');
        var ttd2 = document.getElementById('ttd_pemberi');
        var cek1 = isCanvasBlank(ttd1);
        var cek2 = isCanvasBlank(ttd2);
        var nama1 = $('#nama_pasien_1').val();
        var nama2 = $('#nama_pemberi_1').val();

        if(nama1 && nama2 != '' && !cek1 && !cek2){
            var can1 = convertToDataURLAtas(ttd1);
            var can2 = convertToDataURLAtas(ttd2);
            data.push({
                'parameter': 'nama_pasien',
                'jawaban': nama1,
                'keterangan': 'general_concent',
                'jenis': 'general_concent',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'nama_dokter',
                'jawaban': nama2,
                'keterangan': 'general_concent',
                'jenis': 'general_concent',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'pasien',
                'jawaban': can1,
                'keterangan': 'general_concent',
                'jenis': 'general_concent',
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'dokter',
                'jawaban': can2,
                'keterangan': 'general_concent',
                'jenis': 'general_concent',
                'tipe': 'ttd',
                'id_detail_checkup': idDetailCheckup
            });
            var result = JSON.stringify(data);
            $('#save_ttd_concent').hide();
            $('#load_ttd_concent').show();
            dwr.engine.setAsync(true);
            KeperawatanRawatJalanAction.saveTtdResumenMeis(result, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#modal-ttd_concent').modal('hide');
                        $('#save_ttd_concent').show();
                        $('#load_ttd_concent').hide();
                        printPernyataanRM(kode, idRm);
                    } else {
                        $('#save_ttd_concent').show();
                        $('#load_ttd_concent').hide();
                    }
                }
            });
        }else{
            $('#modal-ttd_concent').scrollTop(0);
            $('#warning_ttd_concent').show().fadeOut(5000);
            $('#msg_ttd_concent').text("Silahkan cek kembali inputan dan TTD berikut...!");
        }
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
                        li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-file-o"></i>' + item.namaRm + '</a></li>'
                    } else {
                        if (item.keterangan == 'form') {
                            li += '<li ' + tol + '><a style="cursor: pointer" onclick="loadModalRM(\'' + item.jenis + '\', \''+item.function +'\', \''+item.parameter+'\', \''+item.idRekamMedisPasien+'\', \'Y\')">' + icons + item.namaRm + ' ' + labelTerisi + tolText + '</a></li>'+enter;
                        } else if (item.keterangan == "surat") {
                            li += '<li ' + tol + '><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\',\'' + item.namaRm + '\')' + '">' + icons2 + item.namaRm + ' ' + labelPrint + tolText + '</a></li>'+enter;
                        }
                    }
                });
                $('#asesmen_rj').html(li);
            }
        });
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

    function cekUangMuka(id) {
        var cek = $('#' + id).is(':checked');
        if (cek) {
            $('#form-uang_muka').show();
            $('#metode_bayar').val('tunai');
        } else {
            $('#form-uang_muka').hide();
            $('#metode_bayar').val('');
        }
    }

    function cekUangMukaNew(id) {
        var cek = $('#' + id).is(':checked');
        if (cek) {
            $('#form_uang_muka').show();
        } else {
            $('#form_uang_muka').hide();
        }
    }
</script>
<script>
    function showDaftar() {
        getDokterDpjp('dokter_add_dpjp_1', null);
        getKelasKamar('rawat_inap');
        $('#modal-daftar-pasien').modal({show: true, backdrop: 'static'});
    }

    function setCanvas(id) {
        var canvas = document.getElementById(id);
        var ctx = canvas.getContext('2d');
        var reader = new FileReader();
        reader.onload = function (event) {
            var img = new Image();
            img.onload = function () {
                canvas.width = img.width;
                canvas.height = img.height;
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.drawImage(img, 0, 0);
            }
            img.src = event.target.result;
        }
        reader.readAsDataURL(event.target.files[0]);
    }

    function choiceJenisPasien(jen) {
        if (jen == 'umum') {
            $('#form_no_bpjs').hide();
            $('#form_data_rujukan').hide();
            $('#form_is_uang_muka').show();
        }
        if (jen == 'bpjs') {
            $('#form_no_bpjs').show();
            $('#form_data_rujukan').show();
            $('#form_is_uang_muka').hide();
        }
    }

    function cekSaveRB() {
        var data = "";
        var jenisPasien = $('#add_jenis').val();
        var noBpjs = $('#add_no_bpjs').val();
        var nik = $('#add_nik').val();
        var nama = $('#add_nama').val();
        var jk = $('#add_jk').val();
        var tempatLahir = $('#add_tempat_lahir').val();
        var tanggalLahir = $('#add_tanggal_lahir').val();
        var noTelp = $('#add_no_telp').val();
        var agama = $('#add_agama').val();
        var profesi = $('#add_profesi').val();
        var suku = $('#add_suku').val();
        var alamat = $('#add_alamat').val();
        var provinsi = $('#add_id_provinsi').val();
        var kota = $('#add_id_kota').val();
        var kecamatan = $('#add_id_kecamatan').val();
        var desa = $('#add_id_desa').val();

        var perujuk = $('#add_perujuk').val();
        var noRujukan = $('#add_no_rujukan').val();
        var ketRujukan = $('#add_keterangan').val();
        var ppkRujukan = $('#add_ppk_rujukan').val();
        var tglRujukan = $('#add_tgl_rujukan').val();
        var noHP = noTelp.replace("-", "").replace("_", "");
        var idDignosa = $('#add_id_diagnosa').val();
        var ketDiagnosa = $('#add_keterangan_diagnosa').val();

        var cekUangMuka = $('#cek_add_uang_muka').is(':checked');
        var uangMuka = $('#h_uang_muka').val();
        var metode = $('#add_metode_bayar').val();
        var kamar = $('#add_kamar').val();
        var kelasKamar = $('#add_kelas_kamar').val();
        var dokter = $('#dokter_add_dpjp_1').val();
        var statusBpjs = $('#status_bpjs').val();

        var ktp = document.getElementById('img_ktp_canvas');
        var foto = document.getElementById('add_foto_rujukan');
        var cek = false;

        if (nik != '' && nama != '' && jk != '' && tempatLahir != '' && tanggalLahir != '' &&
            agama != '' && provinsi != '' && kota != '' && kecamatan != '' && desa != '' && noTelp != '' && kamar && kelasKamar && dokter != '') {

            if (jenisPasien == 'bpjs') {
                if (noBpjs && idDignosa && ketDiagnosa != '') {
                    if('aktif' == statusBpjs){
                        cek = true;
                    }else{
                        $('#warning_add').show().fadeOut(5000);
                        $('#msg_add').text("Silahkan cek status BPJS...!");
                        $('#back_top').scrollTop(0);
                    }
                } else {
                    $('#warning_add').show().fadeOut(5000);
                    $('#msg_add').text("Silahkan cek kembali data id diagnosa dan no bpjs...!");
                    $('#back_top').scrollTop(0);
                    if (noBpjs == '') {
                        $('#add_no_bpjs').css('border', 'solid ipx red');
                    }
                    if (idDignosa == '') {
                        $('#add_id_diagnosa').css('border', 'solid ipx red');
                    }
                    if (ketDiagnosa == '') {
                        $('#add_keterangan_diagnosa').css('border', 'solid ipx red');
                    }
                }
            }
            if (jenisPasien == 'umum') {
                if (cekUangMuka) {
                    if (uangMuka && metode != '') {
                        cek = true;
                    } else {
                        $('#warning_add').show().fadeOut(5000);
                        $('#msg_add').text("Silahkan cek kembali data uang muka dan metode bayar...!");
                        $('#back_top').scrollTop(0);
                    }
                } else {
                    cek = true;
                }
            }

        } else {
            $('#warning_add').show().fadeOut(5000);
            $('#msg_add').text("Silahkan cek kembali data inputan anda...!");
            $('#back_top').scrollTop(0);
            if (nik == '') {
                $('#add_nik').css('border', 'solid 1px red');
            }
            if (nama == '') {
                $('#add_nama').css('border', 'solid 1px red');
            }
            if (jk == '') {
                $('#add_jk').css('border', 'solid 1px red');
            }
            if (tempatLahir == '') {
                $('#add_tempat_lahir').css('border', 'solid 1px red');
            }
            if (tanggalLahir == '') {
                $('#add_tanggal_lahir').css('border', 'solid 1px red');
            }
            if (agama == '') {
                $('#add_agama').css('border', 'solid 1px red');
            }
            if (provinsi == '') {
                $('#add_provinsi').css('border', 'solid 1px red');
            }
            if (kota == '') {
                $('#add_kota').css('border', 'solid 1px red');
            }
            if (kecamatan == '') {
                $('#add_kecamatan').css('border', 'solid 1px red');
            }
            if (desa == '') {
                $('#add_desa').css('border', 'solid 1px red');
            }
            if (noTelp == '') {
                $('#add_no_telp').css('border', 'solid 1px red');
            }
        }

        if (cek) {
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'saveNewRB()');
        }
    }

    function saveNewRB() {
        if(!cekSession()){
            var data = "";
            $('#modal-confirm-dialog').modal('hide');
            var jenisPasien = $('#add_jenis').val();
            var noBpjs = $('#add_no_bpjs').val();
            var nik = $('#add_nik').val();
            var nama = $('#add_nama').val();
            var jk = $('#add_jk').val();
            var tempatLahir = $('#add_tempat_lahir').val();
            var tanggalLahir = $('#add_tanggal_lahir').val();
            var noTelp = $('#add_no_telp').val();
            var agama = $('#add_agama').val();
            var profesi = $('#add_profesi').val();
            var suku = $('#add_suku').val();
            var alamat = $('#add_alamat').val();
            var provinsi = $('#add_id_provinsi').val();
            var kota = $('#add_id_kota').val();
            var kecamatan = $('#add_id_kecamatan').val();
            var desa = $('#add_id_desa').val();

            var perujuk = $('#add_perujuk').val();
            var noRujukan = $('#add_no_rujukan').val();
            var ketRujukan = $('#add_keterangan').val();
            var ppkRujukan = $('#add_ppk_rujukan').val();
            var tglRujukan = $('#add_tgl_rujukan').val();
            var noHP = noTelp.replace(/[-]/g, '');
            var hp = noHP.replace(/[_]/g, '');
            var idDignosa = $('#add_id_diagnosa').val();
            var ketDiagnosa = $('#add_keterangan_diagnosa').val();

            var cekUangMuka = $('#cek_add_uang_muka').is(':checked');
            var uangMuka = $('#h_uang_muka').val();
            var metode = $('#add_metode_bayar').val();
            var kamar = $('#add_kamar').val();
            var kelasKamar = $('#add_kelas_kamar').val();
            var dokter = $('#dokter_add_dpjp_1').val();
            var kelasPasien = $('#kelas_pasien').val();

            var ktp = document.getElementById('img_ktp_canvas');
            var foto = document.getElementById('add_foto_rujukan');
            var ktpFinal = "";
            var fotoFinal = "";
            if ($('#ktp').val() != '') {
                ktpFinal = convertToDataURL(ktp);
            }
            if ($('#foto_rujukan').val() != '') {
                ktpFinal = convertToDataURL(ktp);
            }
            var dataDpjp = [];
            var dataDokter = $('.add_id_dpjp');
            var dataPrio = $('.add_dpjp');
            $.each(dataDokter, function (i, item) {
                if (item.value != '') {
                    var data = item.value.split("|");
                    var idDokter = data[0];
                    var idPelayanan = data[1];
                    dataDpjp.push({
                        'id_dpjp': idDokter,
                        'id_pelayanan': idPelayanan,
                        'prioritas': dataPrio[i].value
                    });
                }
            });
            data = {
                'no_bpjs': noBpjs,
                'nik': nik,
                'nama': nama,
                'jk': jk,
                'tempat_lahir': tempatLahir,
                'tanggal_lahir': tanggalLahir,
                'agama': agama,
                'no_telp': hp,
                'profesi': profesi,
                'suku': suku,
                'alamat': alamat,
                'desa_id': desa,
                'img_ktp': ktpFinal,
                'jenis_pasien': jenisPasien,
                'kelas_kamar': kelasKamar,
                'kamar': kamar,
                'diagnosa': idDignosa,
                'ket_diagnosa': ketDiagnosa,
                'perujuk': perujuk,
                'no_rujukan': noRujukan,
                'ket_perujuk': ketRujukan,
                'no_ppk': ppkRujukan,
                'tgl_ppk': tglRujukan,
                'img_rujukan': fotoFinal,
                'uang_muka': uangMuka,
                'metode_pembayaran': metode,
                'data_dpjp': dataDpjp,
                'id_kelas':kelasPasien
            };
            var objectString = JSON.stringify(data);
            $('#save_add').hide();
            $('#load_add').show();
            dwr.engine.setAsync(true);
            RawatInapAction.saveNewTppri(objectString, {
                callback: function (response) {
                    if (response.status == "success") {
                        $('#save_add').show();
                        $('#load_add').hide();
                        $('#info_dialog').dialog('open');
                        $('#modal-daftar-pasien').modal('hide');
                        $('#back_top').scrollTop(0);
                    } else {
                        $('#save_add').show();
                        $('#load_add').hide();
                        $('#warning_add').show().fadeOut(5000);
                        $('#msg_add').text(response.msg);
                        $('#back_top').scrollTop(0);
                    }
                }
            });
        }
    }

    function showDiagnosa(value, id) {
        if(value != ''){
            var menus, mapped;
            $('#' + id).typeahead({
                minLength: 3,
                source: function (query, process) {
                    menus = [];
                    mapped = {};

                    var data = [];
                    dwr.engine.setAsync(false);
                    CheckupAction.getICD10(query, function (listdata) {
                        data = listdata;
                    });

                    $.each(data, function (i, item) {
                        var labelItem = item.idDiagnosa + '-' + item.descOfDiagnosa;
                        mapped[labelItem] = {
                            id: item.idDiagnosa,
                            label: labelItem,
                            name: item.descOfDiagnosa
                        };
                        menus.push(labelItem);
                    });

                    process(menus);
                },
                updater: function (item) {
                    var selectedObj = mapped[item];
                    // insert to textarea diagnosa_ket
                    $("#add_keterangan_diagnosa").val(selectedObj.name);
                    return selectedObj.id;
                }
            });
        }else{
            $('#add_keterangan_diagnosa').val('');
        }
    }

    function cekBpjs() {
        var noBpjs = $('#add_no_bpjs').val();
        $('#btn-cek').html('<i class="fa fa-circle-o-notch fa-spin"></i> Loading...');
        dwr.engine.setAsync(true);
        CheckupAction.checkStatusBpjs(noBpjs, {
            callback: function (response) {
                var warnClass = "";
                var title = "";
                var msg = "";
                var icon = "";
                var val = "";
                if (response.keteranganStatusPeserta == "AKTIF") {
                    $('#kelas_pasien').val(response.kodeKelas);
                    $('#no_mr').val(response.noMr);
                    val = "aktif";
                    icon = "fa-info";
                    title = "Info!";
                    warnClass = "alert-success";
                    msg = "No BPJS berhasil diverifikasi dengan status AKTIF!";
                } else if (response.keteranganStatusPeserta == "TIDAK AKTIF") {
                    val = "tidak aktif";
                    icon = "fa-warning";
                    title = "Warning!";
                    warnClass = "alert-warning";
                    msg = "No BPJS berhasil diverifikasi dengan status TIDAK AKTIF!";
                } else {
                    val = "tidak ditemukan";
                    icon = "fa-warning";
                    title = "Warning!";
                    warnClass = "alert-danger";
                    msg = "No BPJS tidak ditemukan atau periksa kembali koneksi internet anda...!";
                }

                var warning = '<div class="alert ' + warnClass + ' alert-dismissible">' +
                    '<h4><i class="icon fa ' + icon + '"></i>' + title + '</h4>' + msg +
                    '</div>';

                $('#status_bpjs').val(val);
                $('#warn-bpjs').html(warning);
                $('#warn-bpjs').fadeOut(5000);
                $('#btn-cek').html('<i class="fa fa-search"></i> Check');

            }
        });

    }

    function cancelPeriksa(idDetailCHeckup, tipe){
        CheckupAction.listDataPasien(idDetailCHeckup, {
            callback: function (res) {
                $('#det_no_checkup_cancel').text(res.noCheckup);
                $('#det_id_detail_checkup').text(res.idDetailCheckup);
                $('#det_no_rm_cancel').text(res.idPasien);
                $('#det_nama_pasien').text(res.nama);
                $('#det_pelayanan').text(res.namaPelayanan);
                $('#det_jenis_pasien').text(res.statusPeriksaName);
                $('#det_alamat_batal').text(res.namaDesa+", "+res.namaKecamatan+", "+res.namaKota);
                $('#save_cancel').attr('onclick', 'saveCancel(\''+res.noCheckup+'\', \''+res.idDetailCheckup+'\', \''+tipe+'\')');
            }
        });
        $('#modal-detail_cancel').modal({show: true, backdrop:'static'});
    }

    function saveCancel(noCheckup, idDetailCHeckup, tipe){
        var alsan = $('#set_alasan').val();
        if(alsan != ''){
            $('#save_cancel').hide();
            $('#load_cancel').show();
            dwr.engine.setAsync(true);
            if('inap' == tipe){
                CheckupAction.cancelPeriksaInap(noCheckup, alsan, {
                    callback: function (res) {
                        if(res.status == "success"){
                            $('#save_cancel').show();
                            $('#load_cancel').hide();
                            $('#modal-detail_cancel').modal('hide');
                            $('#info_dialog').dialog('open');
                        }else{
                            $('#save_cancel').show();
                            $('#load_cancel').hide();
                            $('#warning_cancel').show().fadeOut(5000);
                            $('#msg_cancel').text(res.msg);
                        }
                    }
                });
            }else{
                CheckupAction.cancelPeriksa(idDetailCHeckup, alsan, {
                    callback: function (res) {
                        if(res.status == "success"){
                            $('#save_cancel').show();
                            $('#load_cancel').hide();
                            $('#modal-detail_cancel').modal('hide');
                            $('#info_dialog').dialog('open');
                        }else{
                            $('#save_cancel').show();
                            $('#load_cancel').hide();
                            $('#warning_cancel').show().fadeOut(5000);
                            $('#msg_cancel').text(res.msg);
                        }
                    }
                });
            }
        }else{
            $('#warning_cancel').show().fadeOut(5000);
            $('#msg_cancel').text("Silahkan masukkan alasan pasien...!");
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>