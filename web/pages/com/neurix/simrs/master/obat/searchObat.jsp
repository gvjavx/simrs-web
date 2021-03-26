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
        .form-check {
            display: inline-block;
            padding-left: 2px;
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

        .custom02 input[type="radio"] {
            display: none;
        }
        .custom02 label {
            position: relative;
            display: inline-block;
            padding: 3px 3px 3px 20px;
            cursor: pointer;
        }
        .custom02 label::before,
        .custom02 label::after {
            position: absolute;
            content: '';
            top: 50%;
            border-radius: 100%;
            -webkit-transition: all .2s;
            transition: all .2s;
        }
        .custom02 label::before {
            left: 0;
            width: 14px;
            height: 14px;
            margin-top: -8px;
            background: #f3f3f3;
            border: 1px solid #ccc;
        }
        .custom02 label:hover::before {
            background: #fff;
        }
        .custom02 label::after {
            opacity: 0;
            left: 3px;
            width: 8px;
            height: 8px;
            margin-top: -5px;
            background: #3498db;
            -webkit-transform: scale(2);
            transform: scale(2);
        }
        .custom02 input[type="radio"]:checked + label::before {
            background: #fff;
            border: 1px solid #3498db;
        }
        .custom02 input[type="radio"]:checked + label::after {
            opacity: 1;
            -webkit-transform: scale(1);
            transform: scale(1);
        }
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/JenisPersediaanObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/JenisPersediaanObatSubAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#menu_obat').addClass('active');
            $('#sortMinStok').DataTable({
                "order": [[ 2, "asc" ]]
            });

            ObatAction.getListKandunganObat(function (list) {
                var str = "<option value=''>[Select One]</option>";
                $.each(list, function (i, item) {
                    str += "<option value='"+item.idKandungan+"'>"+item.kandungan+"</option>";
                });
                $("#sel_fin_kandungan_obat").html(str);
            });

            ObatAction.getAllKategoriPersediaan(function (list) {
                var str = "<option value=''>[Select One]</option>";
                $.each(list, function (i, item) {
                    str += "<option value='"+item.idKategoriPersediaan+"'>"+item.nama+"</option>";
                });
                $("#id_kategori").html(str);
            });

            listBentukObat('');
        });

    </script>
    <style>
        .top-7{
            margin-top: 7px;
        }
    </style>
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
            Obat
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
                        <div class="form-group">
                            <s:form id="obatForm" method="post" namespace="/obat" action="search_obat.action" theme="simple" cssClass="form-horizontal">
                                <s:hidden name="obat.isKp"/>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Obat</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pabrik" cssStyle="margin-top: 7px"
                                                     name="obat.idObat" required="false"
                                                     readonly="false" cssClass="form-control" placeholder="Ketik atau Scan Barcode"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Bentuk Barang</label>
                                    <div class="col-sm-4">
                                        <select name="obat.idBentuk" class="form-control select2" id="id_bentuk_search" style="margin-top:0px !important; width: 100%">
                                        </select>
                                        <%--<s:action id="initJenis" namespace="/jenisobat"--%>
                                                  <%--name="getListJenisObat_jenisobat"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--list="#initJenis.listOfJenisObat" id="obat_jenis_obat"--%>
                                                  <%--listKey="idJenisObat"--%>
                                                  <%--listValue="namaJenisObat"--%>
                                                  <%--name="obat.idJenisObat"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Obat</label>
                                    <div class="col-sm-4">
                                        <s:textfield cssStyle="margin-top: 7px" cssClass="form-control" name="obat.namaObat"></s:textfield>
                                        <%--<s:action id="initObat" namespace="/obat"--%>
                                                  <%--name="getListObat_obat"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--list="#initObat.listOfObat" id="nama_obat"--%>
                                                  <%--listKey="idObat"--%>
                                                  <%--listValue="namaObat"--%>
                                                  <%--name="obat.idObat"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select cssClass="form-control select2" cssStyle="margin-top: 7px" list="#{'N':'Non Active'}" headerKey="Y" headerValue="Active" name="obat.flag"/>
                                    </div>
                                </div>

                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="obatForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <s:if test='obat.isKp == "Y"'>
                                            <button type="button" class="btn btn-primary" onclick="showModal()">
                                                <i class="fa fa-plus"></i> Tambah Obat
                                            </button>
                                        </s:if>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary"><i class="fa fa-print"></i> Print Report</button>
                                            <button type="button" class="btn btn-primary dropdown-toggle"
                                                    data-toggle="dropdown" style="height: 34px">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li> <a href="initPrintReportRiwayat_obat.action?type=mutasi">
                                                    <i class="fa fa-print"></i> Print Mutasi Obat
                                                </a>
                                                </li>
                                                <li><a href="initPrintReportRiwayat_obat.action?type=sumary">
                                                    <i class="fa fa-print"></i> Print Saldo Obat Opname
                                                </a>
                                                <li><a href="initReportAging_obat.action">
                                                    <i class="fa fa-print"></i> Print Aging Persediaan
                                                </a>
                                                </li>
                                            </ul>
                                        </div>

                                        <a type="button" class="btn btn-danger" href="initForm_obat.action">
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
                                    </div>
                                </div>
                                <div class="form-group" style="display: none">
                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                               closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         document.obatForm.action = 'search_obat.action';
                                                                                         document.obatForm.submit();
                                                                                     }
                                                                            }"
                                    >
                                        <s:hidden id="close_pos"></s:hidden>
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Record has been saved successfully.
                                    </sj:dialog>

                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Obat</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortMinStok" class="table table-bordered" style="font-size: 13px;">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <%--<td>Kategori</td>--%>
                                <%--<td>Lembar/ Box</td>--%>
                                <%--<td>Biji/ Lembar</td>--%>
                                <%--<td>Stok Box</td>--%>
                                <%--<td>Stok Lembar</td>--%>
                                <td>Stok Biji</td>
                                <td>Min Stok</td>
                                <td>Standar Margin</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <s:if test='#row.isMinStok == "Y"'>
                                    <tr bgcolor="#dd4b39" style="color: white">
                                </s:if>
                                <s:else>
                                    <tr>
                                </s:else>
                                        <td><s:property value="idObat"/></td>
                                        <td><s:property value="namaObat"/></td>
                                        <%--<td><s:property escape="false" value="jenisObat"/></td>--%>
                                        <%--<td><s:property value="lembarPerBox"/></td>--%>
                                        <%--<td><s:property value="bijiPerLembar"/></td>--%>
                                        <%--<td><s:property value="qtyBox"/></td>--%>
                                        <%--<td><s:property value="qtyLembar"/></td>--%>
                                        <td><s:property value="qtyBiji"/></td>
                                        <td><s:property value="minStok"/></td>
                                        <td><s:property value="margin"/>%</td>
                                        <td align="center" width="13%">
                                            <img onclick="detailObat('<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="flag"/>','<s:property value="lembarPerBox"/>','<s:property value="bijiPerLembar"/>','<s:property value="merk"/>','<s:property value="jenisObat"/>','<s:property value="minStok"/>', '<s:property value="flagKronis" />', '<s:property value="flagGeneric" />', '<s:property value="flagBpjs" />', '<s:property value="margin" />', '<s:property value="idKategoriPersediaan" />')" class="hvr-grow" src="<s:url value="/pages/images/icons8-search-25.png"/>" style="cursor: pointer;">
                                        <s:if test='obat.isKp == "Y"'>
                                            <img onclick="editObat('<s:property value="idObat"/>',
                                                    '<s:property value="namaObat"/>',
                                                    '<s:property value="qtyBox"/>',
                                                    '<s:property value="qtyLembar"/>',
                                                    '<s:property value="qtyBiji"/>',
                                                    '<s:property value="lembarPerBox"/>',
                                                    '<s:property value="bijiPerLembar"/>',
                                                    '<s:property value="idPabrik"/>',
                                                    '<s:property value="merk"/>',
                                                    '<s:property value="minStok"/>',
                                                    '<s:property value="flagKronis" />',
                                                    '<s:property value="flagGeneric" />',
                                                    '<s:property value="flagBpjs" />',
                                                    '<s:property value="margin" />',
                                                    '<s:property value="idKategoriPersediaan" />',
                                                    '<s:property value="flagParenteral" />',
                                                    '<s:property value="flagFormula" />',
                                                    '<s:property value="idJenisBentuk" />',
                                                    '<s:property value="idJenisSub" />',
                                                    '<s:property value="idBentuk" />')"
                                                 class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                        </s:if>
                                            <s:url var="print_id_pabrik" namespace="/obat" action="printIDPabrik_obat" escapeAmp="false">
                                                <s:param name="id"><s:property value="idObat"/></s:param>
                                            </s:url>
                                            <s:a href="%{print_id_pabrik}" target="_blank">
                                                <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                            </s:a>
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

<div class="modal fade" id="modal-obat">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="title_dal"></span></h4>
            </div>
            <div class="modal-body" id="temp_resep-head">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="alert alert-warning alert-dismissible" style="display: none" id="warning_exits">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_exits"></p>
                </div>
                <input type="hidden" value="0" id="add_harga_box"/>
                <input type="hidden" value="0" id="add_harga_lembar"/>
                <input type="hidden" value="0" id="add_harga_biji"/>
                <input type="hidden" value="0" id="add_box" />
                <input type="hidden" value="0" id="add_lembar" />
                <input type="hidden" value="0" id="add_biji" />
                <div class="row" style="display: none">
                    <div id="row-check-id-pabrik">
                        <label class="col-md-3">Generate ID Obat</label>
                        <div class="col-md-9">
                            <label class="switch" style="margin-top: 4px">
                                <input type="checkbox" checked id="check-id-pabrik" onchange="hideIdPabrik()">
                                <span class="slider round"></span>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row" id="dis-id-pabrik">
                    <label class="col-md-3" style="margin-top: 7px">ID Obat</label>
                    <div class="col-md-9">
                        <s:textfield type="text" min="1" cssClass="form-control"
                                     cssStyle="margin-top: 7px" id="add_pabrik"
                                     onkeypress="var warn =$('#war_pabrik').is(':visible'); if (warn){$('#cor_pabrik').show().fadeOut(3000);$('#war_pabrik').hide()}"></s:textfield>
                        <span style="color: red; display: none;"
                              id="war_pabrik"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_pabrik"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                    <div class="col-md-9">
                        <s:textfield onkeypress="var warn =$('#war_nama').is(':visible'); if (warn){$('#cor_nama').show().fadeOut(3000);$('#war_nama').hide()}"
                                     type="text" cssClass="form-control" id="add_nama_obat" cssStyle="margin-top : 7px"></s:textfield>
                        <span style="color: red; display: none;" id="war_nama"><i
                                class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;" id="cor_nama">
                            <i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-3" style="margin-top: 7px">Kategori</label>
                    <div class="col-md-9">
                        <s:action id="initJenisObat" namespace="/jenisobat"
                                  name="getListJenisObat_jenisobat"/>
                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                  onchange="var warn =$('#war_jenis').is(':visible'); if (warn){$('#cor_jenis').show().fadeOut(3000);$('#war_jenis').hide()}"
                                  list="#initJenisObat.listOfJenisObat" id="add_jenis_obat"
                                  listKey="idJenisObat"
                                  listValue="namaJenisObat"
                                  cssClass="form-control select2" multiple="true"/>
                        <span style="color: red; display: none;"
                              id="war_jenis"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_jenis"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <%--<div class="row">--%>
                    <%--<label class="col-md-3" style="margin-top: 7px">Merek</label>--%>
                    <%--<div class="col-md-9">--%>
                        <%--<s:textfield type="text" min="1" cssClass="form-control"--%>
                                     <%--cssStyle="margin-top: 7px" id="add_merek"--%>
                                     <%--onkeypress="var warn =$('#war_merek').is(':visible'); if (warn){$('#cor_merek').show().fadeOut(3000);$('#war_merek').hide()}"></s:textfield>--%>
                        <%--<span style="color: red; display: none;"--%>
                              <%--id="war_merek"><i class="fa fa-times"></i> required</span>--%>
                        <%--<span style="color: green; display: none;"--%>
                              <%--id="cor_merek"><i class="fa fa-check"></i> correct</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="row">
                    <div class="col-md-3">
                        <label style="margin-top: 10px">Lembar/Box</label>
                    </div>
                    <div class="col-md-3">
                        <s:textfield type="number" min="1" cssClass="form-control"
                                     cssStyle="margin-top: 7px" id="add_lembar_box"
                                     onkeypress="inputWarning('war_lembar_box','cor_lembar_box')"></s:textfield>
                        <span style="color: red; display: none;"
                              id="war_lembar_box"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_lembar_box"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label style="margin-top: 10px">Biji/Lembar</label>
                    </div>
                    <div class="col-md-3">
                        <s:textfield type="number" min="1" cssClass="form-control"
                                     cssStyle="margin-top: 7px" id="add_biji_lembar"
                                     onkeypress="inputWarning('war_biji_lembar','cor_biji_lembar')"></s:textfield>
                        <span style="color: red; display: none;"
                              id="war_biji_lembar"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_biji_lembar"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-3" style="margin-top: 7px">Minimal Stok</label>
                    <div class="col-md-5">
                        <div class="input-group" style="margin-top: 7px;">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         id="add_min_stok"
                                         onkeypress="inputWarning('war_min_stok','cor_min_stok')"></s:textfield>
                            <div class="input-group-addon">
                                Biji
                            </div>
                        </div>
                        <span style="color: red; display: none;"
                              id="war_min_stok"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_min_stok"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label style="margin-top: 10px">Standar Margin</label>
                    </div>
                    <div class="col-md-3">
                        <div class="input-group" style="margin-top: 7px;">
                            <input type="number" class="form-control" id="margin"
                                   onkeypress="var warn =$('#war_margin').is(':visible'); if (warn){$('#cor_margin').show().fadeOut(3000);$('#war_margin').hide()}"
                            />
                            <div class="input-group-addon">
                                %
                            </div>
                        </div>
                        <span style="color: red; display: none;"
                              id="war_margin"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_margin"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <%--<div class="row">--%>
                    <%--<div class="col-md-3">--%>
                        <%--<label style="margin-top: 10px">BPJS</label>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2" style="margin-top: 8px;">--%>
                        <%--<div class="custom02">--%>
                            <%--<input class="radio_remove" onclick="inputWarning('war_flag_bpjs', 'cor_flag_bpjs')" type="radio" value="Y" id="bpjs1" name="radio_bpjs" /><label for="bpjs1" >Ya</label>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2" style="margin-top: 8px;">--%>
                        <%--<div class="custom02" >--%>
                            <%--<input class="radio_remove" onclick="inputWarning('war_flag_bpjs', 'cor_flag_bpjs')" type="radio" value="N" id="bpjs2" name="radio_bpjs" /><label for="bpjs2" >Tidak</label>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2" style="margin-top: 8px;">--%>
                        <%--<span style="color: red; display: none;"--%>
                              <%--id="war_flag_bpjs"><i class="fa fa-times"></i> required</span>--%>
                        <%--<span style="color: green; display: none;"--%>
                              <%--id="cor_flag_bpjs"><i class="fa fa-check"></i> correct</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="row" style="margin-top: 7px">
                    <div class="col-md-3">
                        <label>Kronis</label>
                    </div>
                    <div class="col-md-2">
                        <div class="custom02">
                            <input class="radio_remove" onclick="inputWarning('war_flag_kronis', 'cor_flag_kronis')" type="radio" value="Y" id="kronis1" name="radio_kronis" /><label for="kronis1" >Ya</label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="custom02" >
                            <input class="radio_remove" onclick="inputWarning('war_flag_kronis', 'cor_flag_kronis')" type="radio" value="N" id="kronis2" name="radio_kronis" /><label for="kronis2" >Tidak</label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <span style="color: red; display: none;"
                              id="war_flag_kronis"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_flag_kronis"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="col-md-3">
                        <label>Generic</label>
                    </div>
                    <div class="col-md-2">
                        <div class="custom02">
                            <input class="radio_remove" onclick="inputWarning('war_generic', 'cor_generic')" type="radio" value="Y" id="generic1" name="radio_generic" /><label for="generic1" >Ya</label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="custom02" >
                            <input class="radio_remove" onclick="inputWarning('war_generic', 'cor_generic')" type="radio" value="N" id="generic2" name="radio_generic" /><label for="generic2" >Tidak</label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <span style="color: red; display: none;"
                              id="war_generic"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_generic"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="col-md-3">
                        <label>Parenteral</label>
                    </div>
                    <div class="col-md-2">
                        <div class="custom02">
                            <input class="radio_remove" onclick="inputWarning('war_parenteral', 'cor_parenteral')" type="radio" value="Y" id="parenteral1" name="radio_parenteral" /><label for="parenteral1" >Ya</label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="custom02" >
                            <input class="radio_remove" onclick="inputWarning('war_parenteral', 'cor_parenteral')" type="radio" value="N" id="parenteral2" name="radio_parenteral" /><label for="parenteral2" >Tidak</label>
                        </div>
                    </div>
                    <div class="col-md-2">
                         <span style="color: red; display: none;"
                               id="war_parenteral"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_parenteral"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="col-md-3">
                        <label>Formularium</label>
                    </div>
                    <div class="col-md-2">
                        <div class="custom02">
                            <input class="radio_remove" onclick="inputWarning('war_formula', 'cor_formula')" type="radio" value="Y" id="formula1" name="radio_formula" /><label for="formula1" >Ya</label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="custom02" >
                            <input class="radio_remove" onclick="inputWarning('war_formula', 'cor_formula')" type="radio" value="N" id="formula2" name="radio_formula" /><label for="formula2" >Tidak</label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <span style="color: red; display: none;"
                              id="war_formula"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_formula"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label style="margin-top: 10px">Bentuk Barang</label>
                    </div>
                    <div class="col-md-9">
                        <select class="form-control select2" id="id_bentuk" style="margin-top:0px !important; width: 100%"
                                onchange="inputWarning('war_id_bentuk', 'cor_id_bentuk')">
                        </select>
                        <span style="color: red; display: none;"
                              id="war_id_bentuk"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_id_bentuk"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label style="margin-top: 10px">Jenis Obat</label>
                    </div>
                    <div class="col-md-9">
                        <select style="width: 100%" class="form-control select2" id="id_jenis_obat"
                                onchange="listJenisSubObat(this.value); inputWarning('war_id_jenis_obat', 'cor_id_jenis_obat');">
                        </select>
                        <span style="color: red; display: none;"
                              id="war_id_jenis_obat"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_id_jenis_obat"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label style="margin-top: 10px">Sub Jenis Obat</label>
                    </div>
                    <div class="col-md-9">
                        <select style="width: 100%" class="form-control select2" id="id_sub_jenis_obat"
                        onchange="inputWarning('war_id_sub_jenis_obat', 'cor_id_sub_jenis_obat');">
                        </select>
                        <span style="color: red; display: none;"
                              id="war_id_sub_jenis_obat"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_id_sub_jenis_obat"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label style="margin-top: 10px; font-size: 12px">Kategori Persediaan</label>
                    </div>
                    <div class="col-md-9">
                        <select class="form-control select2" id="id_kategori" style="width: 100%"
                                onchange="var warn =$('#war_id_kategori').is(':visible'); if (warn){$('#cor_id_kategori').show().fadeOut(3000);$('#war_id_kategori').hide()}">
                        </select>
                        <span style="color: red; display: none;"
                              id="war_id_kategori"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_id_kategori"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-12">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="w_kandungan">
                            <p id="p_kandungan"></p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <label class="col-md-12">Kandungan Obat</label>
                    <div class="col-md-5">
                        <select id="sel_fin_kandungan_obat" style="width: 100%" class="form-control select2"></select>
                    </div>
                    <div class="col-md-3">
                        <input style="margin-top: 7px" id="sel_fin_sediaan" class="form-control" placeholder="Sediaan"/>
                    </div>
                    <div class="col-md-3">
                        <input style="margin-top: 7px" id="sel_fin_satuan" class="form-control" placeholder="Satuan"/>
                    </div>
                    <div class="col-md-1">
                        <a onclick="addKandungan()" style="margin-top: 10px; margin-left: -20px" class="btn btn-success"><i class="fa fa-plus"></i></a>
                    </div>
                    <div class="col-md-1" style="font-size: 10px; margin-top: 13px; margin-left: -50px">
                        <span style="color: red; display: none;"
                              id="war_kandungan_obat"><i class="fa fa-times"></i> required</span>
                        <span style="color: green; display: none;"
                              id="cor_kandungan_obat"><i class="fa fa-check"></i> correct</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered" style="font-size: 14px; margin-top: 20px" id="table_kandungan">
                            <thead>
                            <tr bgcolor="#faebd7">
                                <td>Kandungan</td>
                                <td>Sediaan</td>
                                <td>Satuan</td>
                                <td align="center" width="15%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_kandungan">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <span onclick="cekScrol('fa_resep-head', 'temp_resep-head')" class="pull-left hvr-grow" style="color: black; margin-top: 11px; cursor: pointer">
                    <i id="fa_resep-head" class="fa fa-unlock"></i>
                </span>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat" ><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-kandugan-obat">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-document"></i> View Detail Kandungan</span>
                </h4>
            </div>
            <div class="modal-body">

                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin_kandungan_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_fin_error_kandungan_obat"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_fin_kandungan_obat">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_fin_kandungan_obat">Save Berhasil</p>
                </div>

                <div class="col-md-offset-2">
                    <%--<div class="row">--%>
                    <%--<div class="col-md-9">--%>
                    <%--&lt;%&ndash;<h3 id="dt-nama-asuransi"></h3>&ndash;%&gt;--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="row">
                        <div class="col-md-3" align="right">Kandungan</div>
                        <div class="col-md-7">
                            <select id="sel_fin_kandungan_obat2" class="form-control select2" style="width: 100%">
                            </select>
                        </div>
                    </div>
                    <div class="row top-7">
                        <div class="col-md-3" align="right">Sediaan</div>
                        <div class="col-md-4"><input type="number" id="fin_sediaan_kandungan_obat" class="form-control"></div>
                        <div class="col-md-3">
                            <input type="text" id="fin_satuan_kandungan_obat" class="form-control" placeholder="Satuan">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div id="btn-save-kandungan-obat"></div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-detail-obat">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail Obat</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <table class="table table-striped">
                            <tr>
                                <td width="30%"><b>ID Obat</b></td>
                                <td><span id="det_id_obat"></span></td>
                            </tr>
                            <tr>
                                <td><b>Nama Obat</b></td>
                                <td><span id="det_nama_obat"></span></td>
                            </tr>
                            <tr>
                                <td ><b>Kategori</b></td>
                                <td style="vertical-align: middle"><span id="det_jenis_obat"></span></td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-md-6">
                        <table class="table table-striped">
                            <tr>
                                <td><b>Lembar/Box</b></td>
                                <td><span id="det_lembar_obat"></span></td>
                            </tr>
                            <tr>
                                <td><b>Biji/Lembar</b></td>
                                <td><span id="det_biji_obat"></span></td>
                            </tr>
                            <tr>
                                <td width="30%"><b>Min Stok</b></td>
                                <td><span id="det_min_stok_obat" style="background-color: lightgrey; padding: 5px; border-radius: 5px;"></span></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <td>ID Barang</td>
                    <td>Pabrik</td>
                    <td>Expired Date</td>
                    <td>Stok Biji</td>
                    <td>Barcode</td>
                    </thead>
                    <tbody id="body_detail">
                    </tbody>
                </table>
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-4"><i class="fa fa-square" style="color: #eea236"></i> Expired Date Kurang dari 30 hari</div>
                        <div class="col-md-4"><i class="fa fa-square" style="color: #dd4b39"></i> Expired Date Kurang dari 10 hari</div>
                        <div class="col-md-4"><i class="fa fa-square" style="color: #ccc"></i> Expired Date Telah Habis</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>


    function showModal(){
        $('#body_kandungan').html('');
        $('.radio_remove').prop('checked', false);
        $('#title_dal').text("Tambah Obat");
        $('#add_lembar_box, #add_biji_lembar').val('1');
        $('#add_nama_obat, #add_merek, #add_pabrik, #add_min_stok').val('');
        $('#add_jenis_obat').val('').trigger('change');
        $('#form-edit').show();
        $('#add_box, #add_lembar, #add_biji').removeAttr('disabled');
        $('#flag-kronis').val('');
        $('#flag-generic').val('');
        $('#flag-bpjs').val('');
        $('#margin').val('0');
        $('#id_kategori, #id_bentuk, #id_jenis_obat, #id_sub_jenis_obat').val('').trigger('change');
        var id = "";
        $('#load_obat, #war_nama, #war_jenis, #war_pabrik, #war_merek, #war_biji, #war_harga_box, #war_harga_lembar, #war_harga_biji, #war_min_stok').hide();
        $('#add_box, #add_lembar_box, #add_lembar, #add_biji_lembar').css('border','');
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
        $('#modal-obat').modal({show:true, backdrop:'static'});
        $("#row-check-id-pabrik").show();
        $("#add_pabrik").removeAttr("readOnly");
        resetSessionKandunganObat();
        getFromSessionKandunganObat('');
        listBentukObat('');
        listJenisObat('');
        $("#dis-id-pabrik").hide();
    }

    function saveObat(id){
        var idPabrik    = $('#add_pabrik').val();
        var nama        = $('#add_nama_obat').val();
        var jenis       = $('#add_jenis_obat').val();
        var merek       = $('#add_merek').val();
        var box         = $('#add_box').val();
        var lembarBox   = $('#add_lembar_box').val();
        var lembar      = $('#add_lembar').val();
        var bijiLembar  = $('#add_biji_lembar').val();
        var biji        = $('#add_biji').val();
        var hargaBox    = $('#add_harga_box').val();
        var hargaLembar = $('#add_harga_lembar').val();
        var hargaBiji   = $('#add_harga_biji').val();
        var minStok     = $('#add_min_stok').val();
        //var flagBpjs    = $('[name=radio_bpjs]:checked').val();
        var flagKronis  = $('[name=radio_kronis]:checked').val();
        var flagGeneric = $('[name=radio_generic]:checked').val();
        var formula     = $('[name=radio_formula]:checked').val();
        var parenteral  = $('[name=radio_parenteral]:checked').val();
        var margin      = $('#margin').val();
        var idKategori  = $('#id_kategori').val();
        var idBentuk    = $('#id_bentuk').val();
        var idJenisBantuk = $('#id_jenis_obat').val();
        var idJenisSub = $('#id_sub_jenis_obat').val();
        var tableKandungan = $('#table_kandungan').tableToJSON();
        var cekGenerate = false;
        var idObat = "";
        if($('#check-id-pabrik').is(':checked')){
            cekGenerate = true;
        }else{
            if(idPabrik != ''){
                cekGenerate = true;
                idObat = idPabrik;
            }
        }

        if (nama && lembarBox && bijiLembar && margin && idKategori && idBentuk
            && minStok != '' && flagKronis && flagGeneric && idJenisBantuk && idJenisSub
            && formula && parenteral != undefined && jenis != null && cekGenerate) {
            var tempKandungan = [];
            $.each(tableKandungan, function (i, item) {
                var id = $('#id_kandungan_'+i).val();
                var sediaan = $('#sediaan_'+i).val();
                var satuan = $('#satuan_'+i).val();
                var idKan = $('#id_kan_'+i).val();
                var kan = "";
                if(idKan != undefined && idKan != '' && idKan != null){
                    kan = idKan;
                }
                if(id && sediaan && satuan != ''){
                    tempKandungan.push({
                        'id_kandungan':id,
                        'sediaan':sediaan,
                        'satuan':satuan,
                        'id':kan
                    });
                }
            });
            var tempJenis = [];
            $.each(jenis, function (i, item) {
                tempJenis.push({
                    'id_jenis':item
                })
            });
            var stringJenis = JSON.stringify(tempJenis);
            var stringKandungan = JSON.stringify(tempKandungan);

            var obj = {
                'id_obat': idPabrik,
                'nama_obat':nama,
                'kategori':stringJenis,
                'merek':"",
                'min_stok':minStok,
                'lembar_box':lembarBox,
                'biji_lembar':bijiLembar,
                'margin':margin,
                //'bpjs':flagBpjs,
                'kronis':flagKronis,
                'generic':flagGeneric,
                'parenteral':parenteral,
                'formula':formula,
                'bentuk':idBentuk,
                'jenis':idJenisBantuk,
                'sub_jenis':idJenisSub,
                'kategori_persediaan':idKategori,
                'kandungan': stringKandungan
            };

            var stringObject = JSON.stringify(obj);
            $('#save_obat').hide();
            $('#load_obat').show();
            if (id != '') {
                dwr.engine.setAsync(true);
                ObatAction.editObat(stringObject,  function (response) {
                    dwr.engine.setAsync(false);
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                    } else {
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#warning_obat').show().fadeOut(5000);
                        $('#obat_error').text(response.message);
                    }
                })
            } else {
                dwr.engine.setAsync(true);
                ObatAction.saveObat(stringObject, function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                    }else if (response.status == "warning") {
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#warning_exits').show().fadeOut(5000);
                        $('#msg_exits').text("ID Pabrik dengan bentuk fisik tersebut sudah ada pada ID obat : "+response.message);
                    } else {
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#warning_obat').show().fadeOut(5000);
                        $('#obat_error').text(response.message);
                    }
                });
            }
        } else {
            $('#warning_obat').show().fadeOut(5000);
            $('#obat_error').text("Silahkan cek kembali data inputan..!");

            if (nama == '') {
                $('#war_nama').show();
            }
            if (jenis == '' || jenis == null) {
                $('#war_jenis').show();
            }
            if (hargaBox == '') {
                $('#war_harga_box').show();
            }
            if (hargaLembar == '') {
                $('#war_harga_lembar').show();
            }
            if (hargaBiji == '') {
                $('#war_harga_biji').show();
            }
            if (merek == '') {
                $('#war_merek').show();
            }
            if (lembarBox == '') {
                $('#war_lembar_box').show();
            }
            if (bijiLembar == '') {
                $('#war_biji_lembar').show();
            }
            if (!cekGenerate) {
                $('#war_pabrik').show();
            }
            if (minStok == '') {
                $('#war_min_stok').show();
            }
            if (flagKronis == undefined) {
                $('#war_flag_kronis').show();
            }
            if (flagGeneric == undefined) {
                $('#war_generic').show();
            }
//            if (flagBpjs == undefined) {
//                $('#war_flag_bpjs').show();
//            }
            if (formula == undefined) {
                $('#war_formula').show();
            }
            if (parenteral == undefined) {
                $('#war_parenteral').show();
            }
            if (margin == '') {
                $('#war_margin').show();
            }
            if (idKategori == '') {
                $('#war_id_kategori').show();
            }
            if (idBentuk == '') {
                $('#war_id_bentuk').show();
            }
            if (idJenisBantuk == '') {
                $('#war_id_jenis_obat').show();
            }
            if (idJenisSub == '') {
                $('#war_id_sub_jenis_obat').show();
            }
            // if (tableKandungan.length == 0) {
            //     $('#war_kandungan_obat').show();
            // }
        }
    }

    function editObat(idObat, nama, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerBiji, idPbarik, mrek, minStok, flagKronis, flagGeneric, flagBpjs, margin, idKategori, flagParenteral, flagFormula, idJenisBentuk, idSubJenis, idBentuk) {
        $('#title_dal').text("Edit Obat");
        listBentukObat('');
        listJenisObat('');
        $('#load_obat, #war_nama, #war_jenis, #war_harga, #war_stok').hide();
        $('#save_obat').attr('onclick', 'saveObat(\'' + idObat + '\')').show();
        $('#add_nama_obat').val(nama);
        $('#add_id_obat').val(idObat);
        $('#add_jenis_obat').val(listSelectObatEdit(idObat)).trigger('change');
        $('#add_merek').val(mrek);
        $('#add_pabrik').val(idObat);
        $('#add_box').val(qtyBox).attr('disabled','');
        $('#add_lembar_box').val(lembarPerBox);
        $('#add_lembar').val(qtyLembar).attr('disabled','');
        $('#add_biji_lembar').val(bijiPerBiji);
        $('#add_biji').val(qtyBiji).attr('disabled','');
        $('#add_min_stok').val(minStok);
        $('#form-edit').hide();
        $('#fin_id_obat').val(idObat);
        $('[name=radio_bpjs]').filter("[value='"+flagBpjs+"']").attr('checked', true);
        $('[name=radio_kronis]').filter("[value='"+flagKronis+"']").attr('checked', true);
        $('[name=radio_generic]').filter("[value='"+flagGeneric+"']").attr('checked', true);
        $('[name=radio_formula]').filter("[value='"+flagFormula+"']").attr('checked', true);
        $('[name=radio_parenteral]').filter("[value='"+flagParenteral+"']").attr('checked', true);
        $("#margin").val(margin);
        $("#row-check-id-pabrik").hide();
        $("#add_pabrik").attr('readOnly', "true");
        $('#id_kategori').val(idKategori).trigger('change');
        $('#modal-obat').modal({show:true, backdrop:'static'});
        $('#id_jenis_obat').val(idJenisBentuk).trigger('change');
        getListKandunganObat(idObat);
        $('#id_bentuk').val(idBentuk).trigger('change');
        setTimeout(function () {
            $('#id_sub_jenis_obat').val(idSubJenis).trigger('change');
        },100);
    }

    function listSelectObatEdit(idObat){
        var data = [];
        if (idObat != '') {
            ObatAction.getJenisObatByIdObat(idObat, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        data.push(item.idJenisObat)
                    });
                }
            });
        } else {
            alert('id obat kosong');
        }
        return data;
    }

    function detailObat(idObat, namaObat, flag, lembarPerBox, bijiPerLembar, merk, jenis, minStok){
        $('#body_detail').html('');

        if(idObat != null && idObat != ''){
            $('#det_id_obat').text(idObat);
            $('#det_nama_obat').text(namaObat);
            $('#det_jenis_obat').html(jenis);
            $('#det_merk_obat').text(merk);
            $('#det_lembar_obat').text(lembarPerBox);
            $('#det_biji_obat').text(bijiPerLembar);
            $('#det_min_stok_obat').text(minStok +" Biji");

            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            today = mm + '-' + dd + '-' + yyyy;

            var table = "";
            ObatAction.getListObatDetail(idObat, function (response) {
                if(response.length > 0){
                    $.each(response, function (i, item) {

                        var dateExp = converterDate(item.expiredDate);

                        const date1 = new Date(today);
                        const date2 = new Date(dateExp);
                        const diffTime = Math.abs(date2 - date1);
                        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                        var warna = "";
                        var color = "";
                        var disabled = "";

                        if(Math.abs(date1) > Math.abs(date2)){
                            warna = '#ccc';
                            color = '#fff';
                            disabled = 'disabled';

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

                        table += '<tr bgcolor=' + warna + ' style="color: ' + color + '">' +
                            '<td>'+item.idBarang+'</td>'+
                            '<td>'+item.namaPabrikObat+'</td>'+
                            '<td>'+converterDate(item.expiredDate)+'</td>'+
                            '<td>'+item.qtyBiji+'</td>'+
                            '<td align="center">' +
                            '<a target="_blank" href="../permintaanpo/printBarcodeBarang_permintaanpo.action?id='+item.idBarang+'">' +
                            //'<button class="btn btn-sm btn-default" onclick="printBarcode(\''+item.idBarang+'\')">' +
                            //'<i class="fa fa-print"></i>' +
                            //'</button>' +
                            '<img class="hvr-grow" src="<s:url value="/pages/images/icons8-barcode-scanner-25.png"/>" style="cursor: pointer;"></a>' +
                            '</td>'+
                            '</tr>'
                    });

                    $('#body_detail').html(table);
                }else{

                }
            });
            $('#modal-detail-obat').modal({show:true, backdrop:'static'});
        }
    }

    function printBarcode(id) {
        window.location.href = "/permintaanpo/printBarcodeBarang_permintaanpo.action?id="+id;
    }

    function formaterDate(tanggal){
        var tgl = "";
        if(tanggal != null && tanggal != ''){
            tgl = $.datepicker.formatDate("dd-mm-yy", new Date(tanggal));
        }
        return tgl;
    }

    function showListKandunganObat(idObat) {
        ObatAction.createSessionKandunganObat(idObat, function (res) {
            if (res.status == "success"){
                getFromSessionKandunganObat(idObat)
            } else {
                $("#warning_kandungan_obat").show();
                $("#kandungan_obat_error").text(res.msg);
            }
        });
    }

    function getFromSessionKandunganObat(idObat){
        ObatAction.getListFromSessionObat(idObat, function (list) {
            var str = "";
            $.each(list, function (i, item) {
                str += "<tr>" +
                    "<td>"+item.kandungan+"</td>"+
                    "<td>"+item.sediaan+" "+item.satuanSediaan+"</td>"+
                    "<td align='center'><button class='btn btn-sm btn-primary' onclick=\"viewEditKandunganObat(\'"+item.id+"\')\"><i class='fa fa-edit'></i></button></td>"+
                    "</tr>";
            });
            $("#body-kandungan-obat").html(str);
        });
    }

    function viewEditKandunganObat(idKandunganObat, tipe) {
        $("#modal-view-kandugan-obat").modal('show');

        if (tipe == "add"){
            $("#sel_fin_kandungan_obat").val("");
            $("#fin_sediaan_kandungan_obat").val("");
            $("#fin_satuan_kandungan_obat").val("");
            var strBtn = "<button class='btn btn-success' onclick=\"saveKandunganObat(\'\')\"><i class='fa fa-check'></i>Save Add</button>";
            $("#btn-save-kandungan-obat").html(strBtn);
        } else {
            ObatAction.initEditKandunganObat(idKandunganObat, function (res) {
                if (res != null){
                    $("#sel_fin_kandungan_obat").val(res.idKandungan);
                    $("#fin_sediaan_kandungan_obat").val(res.sediaan);
                    $("#fin_satuan_kandungan_obat").val(res.satuanSediaan);
                    var strBtn = "<button class='btn btn-success' onclick=\"saveKandunganObat(\'" + res.id + "\')\"><i class='fa fa-check'></i> Update</button>";
                    $("#btn-save-kandungan-obat").html(strBtn);
                }
            });
        }
    }

    function saveKandunganObat(id){

        if (id == null)
            id = "";

        var idObat = $("#fin_id_obat").val();
        var kandungan = $('#sel_fin_kandungan_obat').val();
        var sediaan = $("#fin_sediaan_kandungan_obat").val();
        var satuan = $("#fin_satuan_kandungan_obat").val();

        if (id == null || id == ""){
            ObatAction.addKandunganObat(idObat, kandungan, "", sediaan, satuan, function (res) {
                if (res.status == "success"){
                    $("#modal-view-kandugan-obat").modal('hide');
                    getFromSessionKandunganObat(idObat);
                } else {
                    alert("Gagal Menyimpan");
                }
            });
        } else {
            ObatAction.editKandunganObatDetail(id, idObat, kandungan, "", sediaan, satuan, function (res) {
                if (res.status == "success"){
                    $("#modal-view-kandugan-obat").modal('hide');
                    getFromSessionKandunganObat(idObat);
                } else {
                    alert("Gagal Menyimpan");
                }
            });
        }
    }

    function resetSessionKandunganObat() {
        ObatAction.resetSessionKandunganObat();
    }

    function hideIdPabrik() {
        var checkIdPabrik = document.getElementById("check-id-pabrik");
        if (checkIdPabrik.checked == true){
            $("#dis-id-pabrik").hide();
        } else {
            $("#dis-id-pabrik").show();
        }
    }

    function listBentukObat(id) {
        if (id != null && id != ""){
            ObatAction.getHeaderObatById(id, function (obat) {

                ObatAction.listAllBentukBarang(function (res) {

                    var str = "";
                    $.each(res, function (i, item) {
                        if (obat.idBentuk == item.idBentuk)
                            str += '<option value="' + item.idBentuk + '" selected>' + item.bentuk + '</option>';
                        else
                            str += '<option value="' + item.idBentuk + '">' + item.bentuk + '</option>';
                    });

                    $("#id_bentuk").html(str);
                });
            });
        } else {
            ObatAction.listAllBentukBarang(function (res) {
                var str = "<option value=''>[Select One]</option>";
                $.each(res, function (i, item) {
                    str += '<option value="' + item.idBentuk + '">' + item.bentuk + '</option>';
                });

                $("#id_bentuk").html(str);
                $("#id_bentuk_search").html(str);
                var idBentuk = '<s:property value="obat.idBentuk"/>';
                $("#id_bentuk_search").val(idBentuk).trigger('change');
            });
        }
    }

    function listJenisObat(id) {
        if (id != null && id != "") {
            ObatAction.getHeaderObatById(id, function (obat) {
                JenisPersediaanObatAction.getJenisPersediaanAll(function (res) {
                    var str = "";
                    $.each(res, function (i, item) {
                        if (obat.idOJenisObat == item.id)
                            str += '<option value="' + item.id + '" selected>' + item.nama + '</option>';
                        else
                            str += '<option value="' + item.id + '">' + item.nama + '</option>';
                    });
                    $("#id_jenis_obat").html(str);
                });
            });

        } else {
            JenisPersediaanObatAction.getJenisPersediaanAll(function (res) {
                var str = "<option value=''>[Select One]</option>";
                $.each(res, function (i, item) {
                    str += '<option value="'+item.id+'">'+item.nama+'</option>';
                });
                $("#id_jenis_obat").html(str);
            });
        }
    }

    function listJenisSubObat(jenis) {
        JenisPersediaanObatSubAction.getListJenisObatSubByIdJenis(jenis, function (res) {
            var str = "<option value=''>[Select One]</option>";
            $.each(res, function (i, item) {
                str += '<option value="' + item.id + '">' + item.nama + '</option>';
            });
            $("#id_sub_jenis_obat").html(str);
        })
    }

    function addKandungan(){
        var body = "";
        var idKandungan = $('#sel_fin_kandungan_obat').val();
        var namaKandungan = $('#sel_fin_kandungan_obat option:selected').text();
        var sediaan = $('#sel_fin_sediaan').val();
        var satuan = $('#sel_fin_satuan').val();
        var data = $('#table_kandungan').tableToJSON();
        var count = data.length;
        var id = 'roww_'+count;
        var cek = false;
        if(idKandungan && sediaan && satuan != ''){
            if(data.length > 0){
                $.each(data, function (i, item) {
                    var kandungnId = $('#id_kandungan_'+i).val();
                    if(idKandungan == kandungnId){
                        cek = true;
                    }
                });
            }
            if(!cek){
                body = '<tr id="'+id+'">' +
                    '<td>'+namaKandungan+
                    '<input type="hidden" id="id_kandungan_'+count+'" value="'+idKandungan+'">'+
                    '<input type="hidden" id="sediaan_'+count+'" value="'+sediaan+'">'+
                    '<input type="hidden" id="satuan_'+count+'" value="'+satuan+'">'+
                    '<input type="hidden" id="id_kan_'+count+'" value="'+satuan+sediaan+'">'+
                    '</td>'+
                    '<td>'+sediaan+'</td>'+
                    '<td>'+satuan+'</td>'+
                    '<td align="center"><img border="0" onclick="delRow(\'' + id + '\')" class="hvr-grow" src="' + contextPathHeader + '/pages/images/cancel-flat-new.png" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                    '</tr>';
                $('#body_kandungan').append(body);
                inputWarning('war_kandungan_obat','cor_kandungan_obat');
            }else{
                $('#w_kandungan').show().fadeOut(5000);
                $('#p_kandungan').text("Kandungan "+namaKandungan+" sudah ada dalam list...!");
            }
        }else{
            $('#w_kandungan').show().fadeOut(5000);
            $('#p_kandungan').text("Silahkan cek inputan kandungan dan sediaan...!");
        }
    }

    function delRow(id){
        $('#'+id).remove();
    }

    function getListKandunganObat(idObat) {
        $('#body_kandungan').html('');
        ObatAction.getListKandunganObat(idObat, function (res) {
            var body = "";
            if (res.length > 0){
                $.each(res, function (i, item) {
                    body += '<tr id="'+item.id+'">' +
                        '<td>'+item.kandungan+
                        '<input type="hidden" id="id_kandungan_'+i+'" value="'+item.idKandungan+'">'+
                        '<input type="hidden" id="sediaan_'+i+'" value="'+item.sediaan+'">'+
                        '<input type="hidden" id="satuan_'+i+'" value="'+item.satuanSediaan+'">'+
                        '<input type="hidden" id="id_kan_'+i+'" value="'+item.id+'">'+
                        '</td>'+
                        '<td>'+item.sediaan+'</td>'+
                        '<td>'+item.satuanSediaan+'</td>'+
                        '<td align="center"><img border="0" onclick="delRow(\'' + item.id + '\')" class="hvr-grow" src="' + contextPathHeader + '/pages/images/cancel-flat-new.png" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                        '</tr>';
                });
                $('#body_kandungan').append(body);
            }
        });
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>