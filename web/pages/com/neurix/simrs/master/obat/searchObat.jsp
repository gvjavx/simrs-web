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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#menu_obat').addClass('active');
            $('#sortMinStok').DataTable({
                "order": [[ 4, "asc" ]]
            });

            ObatAction.getListKandunganObat(function (list) {
                var str = "<option value=''>[Select One]</option>";
                $.each(list, function (i, item) {
                    str += "<option value='"+item.idKandungan+"'>"+item.kandungan+"</option>";
                });
                $("#sel_fin_kandungan_obat").html(str);
                //console.log(list);
            });

            ObatAction.getAllKategoriPersediaan(function (list) {
                var str = "<option value=''>[Select One]</option>";
                $.each(list, function (i, item) {
                    str += "<option value='"+item.idKategoriPersediaan+"'>"+item.nama+"</option>";
                });
                $("#id_kategori").html(str);
            });

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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Obat</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="obatForm" method="post" namespace="/obat" action="search_obat.action" theme="simple" cssClass="form-horizontal">
                                <s:hidden name="obat.isKp"/>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kode Produksi Obat</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pabrik" cssStyle="margin-top: 7px"
                                                     name="obat.idPabrik" required="false"
                                                     readonly="false" cssClass="form-control" placeholder="Ketik atau Scan Barcode"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Jenis Obat</label>
                                    <div class="col-sm-4">
                                        <s:action id="initJenis" namespace="/jenisobat"
                                                  name="getListJenisObat_jenisobat"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initJenis.listOfJenisObat" id="obat_jenis_obat"
                                                  listKey="idJenisObat"
                                                  listValue="namaJenisObat"
                                                  name="obat.idJenisObat"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Obat</label>
                                    <div class="col-sm-4">
                                        <s:action id="initObat" namespace="/obat"
                                                  name="getListObat_obat"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initObat.listOfObat" id="nama_obat"
                                                  listKey="idObat"
                                                  listValue="namaObat"
                                                  name="obat.idObat"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select cssClass="form-control" cssStyle="margin-top: 7px" list="#{'N':'Non Active'}" headerKey="Y" headerValue="Active" name="obat.flag"/>
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
                                <td>Nama Obat</td>
                                <td>Jenis Obat</td>
                                <td>Lembar/ Box</td>
                                <td>Biji/ Lembar</td>
                                <td>Stok Box</td>
                                <td>Stok Lembar</td>
                                <td>Stok Biji</td>
                                <td>Min Stok</td>
                                <td>Standar Margin</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfPeriksaLab" var="row">
                                <s:if test='#row.isMinStok == "Y"'>
                                    <tr bgcolor="#dd4b39">
                                </s:if>
                                <s:else>
                                    <tr>
                                </s:else>
                                        <td><s:property value="namaObat"/></td>
                                        <td><s:property escape="false" value="jenisObat"/></td>
                                        <td><s:property value="lembarPerBox"/></td>
                                        <td><s:property value="bijiPerLembar"/></td>
                                        <td><s:property value="qtyBox"/></td>
                                        <td><s:property value="qtyLembar"/></td>
                                        <td><s:property value="qtyBiji"/></td>
                                        <td><s:property value="minStok"/></td>
                                        <td><s:property value="margin"/>%</td>
                                        <td align="center" width="13%">
                                            <img onclick="detailObat('<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="flag"/>','<s:property value="lembarPerBox"/>','<s:property value="bijiPerLembar"/>','<s:property value="merk"/>','<s:property value="jenisObat"/>','<s:property value="minStok"/>', '<s:property value="flagKronis" />', '<s:property value="flagGeneric" />', '<s:property value="flagBpjs" />', '<s:property value="margin" />', '<s:property value="idKategoriPersediaan" />')" class="hvr-grow" src="<s:url value="/pages/images/icons8-search-25.png"/>" style="cursor: pointer;">
                                        <s:if test='obat.isKp == "Y"'>
                                            <img onclick="editObat('<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="qtyBox"/>','<s:property value="qtyLembar"/>','<s:property value="qtyBiji"/>','<s:property value="lembarPerBox"/>','<s:property value="bijiPerLembar"/>','<s:property value="idPabrik"/>','<s:property value="merk"/>','<s:property value="minStok"/>', '<s:property value="flagKronis" />', '<s:property value="flagGeneric" />', '<s:property value="flagBpjs" />', '<s:property value="margin" />', '<s:property value="idKategoriPersediaan" />')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                        </s:if>
                                            <s:url var="print_id_pabrik" namespace="/obat" action="printIDPabrik_obat" escapeAmp="false">
                                                <s:param name="idPabrik"><s:property value="idPabrik"/></s:param>
                                            </s:url>
                                            <s:a href="%{print_id_pabrik}" target="_blank">
                                                <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                            </s:a>
                                        </td>
                                    </tr>
                                <%--<s:else>--%>
                                <%--<tr>--%>
                                <%--<td><s:property value="namaObat"/></td>--%>
                                <%--<td><s:property escape="false" value="jenisObat"/></td>--%>
                                <%--<td><s:property value="lembarPerBox"/></td>--%>
                                <%--<td><s:property value="bijiPerLembar"/></td>--%>
                                <%--<td><s:property value="qtyBox"/></td>--%>
                                <%--<td><s:property value="qtyLembar"/></td>--%>
                                <%--<td><s:property value="qtyBiji"/></td>--%>
                                <%--<td><s:property value="minStok"/></td>--%>
                                <%--<td align="center" width="13%">--%>
                                <%--<img onclick="detailObat('<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="flag"/>','<s:property value="lembarPerBox"/>','<s:property value="bijiPerLembar"/>','<s:property value="merk"/>','<s:property value="jenisObat"/>','<s:property value="minStok"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-search-25.png"/>" style="cursor: pointer;">--%>
                                <%--<img onclick="editObat('<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="qtyBox"/>','<s:property value="qtyLembar"/>','<s:property value="qtyBiji"/>','<s:property value="lembarPerBox"/>','<s:property value="bijiPerLembar"/>','<s:property value="idPabrik"/>','<s:property value="merk"/>','<s:property value="minStok"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">--%>
                                <%--<s:url var="print_id_pabrik" namespace="/obat" action="printIDPabrik_obat" escapeAmp="false">--%>
                                <%--<s:param name="idPabrik"><s:property value="idPabrik"/></s:param>--%>
                                <%--</s:url>--%>
                                <%--<s:a href="%{print_id_pabrik}" target="_blank">--%>
                                <%--<img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>">--%>
                                <%--</s:a>--%>
                                <%--</td>--%>
                                <%--</tr>--%>
                                <%--</s:else>--%>
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
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="alert alert-warning alert-dismissible" style="display: none" id="warning_exits">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_exits"></p>
                </div>
                <div class="row" id="row-check-id-pabrik">
                    <label class="col-md-3" style="margin-top: 7px">Generate Id Obat</label>
                    <label class="switch">
                        <input type="checkbox" id="check-id-pabrik" onchange="hideIdPabrik()">
                        <span class="slider round"></span>
                    </label>
                    <%--<input type="checkbox" id="check-id-pabrik" onchange="hideIdPabrik()">--%>


                </div>

                <div class="row">

                    <div class="form-group" id="dis-id-pabrik">
                        <label class="col-md-3" style="margin-top: 7px">ID Obat</label>
                        <div class="col-md-7">
                            <s:textfield type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_pabrik"
                                         onkeypress="var warn =$('#war_pabrik').is(':visible'); if (warn){$('#cor_pabrik').show().fadeOut(3000);$('#war_pabrik').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_pabrik"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_pabrik"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <input type="hidden" id="fin_id_obat">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:textfield onkeypress="var warn =$('#war_nama').is(':visible'); if (warn){$('#cor_nama').show().fadeOut(3000);$('#war_nama').hide()}"
                                         type="text" cssClass="form-control" id="add_nama_obat" cssStyle="margin-top : 7px"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_nama"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_nama">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>
                        <div class="col-md-7">
                            <s:action id="initJenisObat" namespace="/jenisobat"
                                      name="getListJenisObat_jenisobat"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn =$('#war_jenis').is(':visible'); if (warn){$('#cor_jenis').show().fadeOut(3000);$('#war_jenis').hide()}"
                                      list="#initJenisObat.listOfJenisObat" id="add_jenis_obat"
                                      listKey="idJenisObat"
                                      listValue="namaJenisObat"
                                      cssClass="form-control select2" multiple="true"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_jenis"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_jenis"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Merek</label>
                        <div class="col-md-7">
                            <s:textfield type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_merek"
                                         onkeypress="var warn =$('#war_merek').is(':visible'); if (warn){$('#cor_merek').show().fadeOut(3000);$('#war_merek').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_merek"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_merek"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>



                    <input type="hidden" value="0" id="add_harga_box"/>
                    <input type="hidden" value="0" id="add_harga_lembar"/>
                    <input type="hidden" value="0" id="add_harga_biji"/>
                    <div id="form-edit" style="display: none">

                        <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Harga/Box</label>--%>
                        <%--<div class="col-md-7">--%>
                        <%--<s:textfield type="number" min="1" cssClass="form-control"--%>
                        <%--cssStyle="margin-top: 7px" id="add_harga_box"--%>
                        <%--onkeypress="var warn =$('#war_harga_box').is(':visible'); if (warn){$('#cor_harga_box').show().fadeOut(3000);$('#war_harga_box').hide()}"></s:textfield>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                        <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                        <%--id="war_harga_box"><i class="fa fa-times"></i> required</p>--%>
                        <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                        <%--id="cor_harga_box"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Harga/Lembar</label>--%>
                        <%--<div class="col-md-7">--%>
                        <%--<s:textfield type="number" min="1" cssClass="form-control"--%>
                        <%--cssStyle="margin-top: 7px" id="add_harga_lembar"--%>
                        <%--onkeypress="var warn =$('#war_harga_lembar').is(':visible'); if (warn){$('#cor_harga_lembar').show().fadeOut(3000);$('#war_harga_lembar').hide()}"></s:textfield>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                        <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                        <%--id="war_harga_lembar"><i class="fa fa-times"></i> required</p>--%>
                        <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                        <%--id="cor_harga_lembar"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Harga/Biji</label>--%>
                        <%--<div class="col-md-7">--%>
                        <%--<s:textfield type="number" min="1" cssClass="form-control"--%>
                        <%--cssStyle="margin-top: 7px" id="add_harga_biji"--%>
                        <%--onkeypress="var warn =$('#war_harga_biji').is(':visible'); if (warn){$('#cor_harga_biji').show().fadeOut(3000);$('#war_harga_biji').hide()}"></s:textfield>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                        <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                        <%--id="war_harga_biji"><i class="fa fa-times"></i> required</p>--%>
                        <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                        <%--id="cor_harga_biji"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Minimal Stok</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px; width: 50%" >
                                <s:textfield type="number" min="1" cssClass="form-control"
                                             id="add_min_stok"
                                             onkeypress="var warn =$('#war_min_stok').is(':visible'); if (warn){$('#cor_min_stok').show().fadeOut(3000);$('#war_min_stok').hide()}"></s:textfield>
                                <div class="input-group-addon">
                                    Box
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_min_stok"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_min_stok"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <input type="hidden" value="0" id="add_box" />
                        <div class="col-md-3">
                            <label style="margin-top: 10px">Lembar/Box</label>
                        </div>
                        <div class="col-md-3">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_lembar_box"
                                         onkeypress="$(this).css('border', '')"></s:textfield>
                        </div>
                        <div class="col-md-6"></div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <input type="hidden" value="0" id="add_lembar" />
                        <input type="hidden" value="0" id="add_biji" />
                        <div class="col-md-3">
                            <label style="margin-top: 10px">Biji/Lembar</label>
                        </div>
                        <div class="col-md-3">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_biji_lembar"
                                         onkeypress="$(this).css('border', '')"></s:textfield>
                        </div>
                        <div class="col-md-6"></div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <div class="col-md-3">
                            <label style="margin-top: 10px">Kronis</label>
                        </div>
                        <div class="col-md-3" style="margin-top: 7px;">
                            <select class="form-control" id="flag-kronis">
                                <option value="N">No</option>
                                <option value="Y">Yes</option>
                            </select>
                        </div>
                        <div class="col-md-6"></div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <div class="col-md-3">
                            <label style="margin-top: 10px">Generic</label>
                        </div>
                        <div class="col-md-3" style="margin-top: 7px;">
                            <select class="form-control" id="flag-generic">
                                <option value="N">No</option>
                                <option value="Y">Yes</option>
                            </select>
                        </div>
                        <div class="col-md-6"></div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <div class="col-md-3">
                            <label style="margin-top: 10px">BPJS</label>
                        </div>
                        <div class="col-md-3" style="margin-top: 7px;">
                            <select class="form-control" id="flag-bpjs">
                                <option value="N">No</option>
                                <option value="Y">Yes</option>
                            </select>
                        </div>
                        <div class="col-md-6"></div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <div class="col-md-3">
                            <label style="margin-top: 10px">Standar Margin</label>
                        </div>
                        <div class="col-md-3">
                            <div class="input-group" style="margin-top: 7px;">
                                <input type="number" class="form-control" id="margin"/>
                                <div class="input-group-addon">
                                    %
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6"></div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <div class="col-md-3">
                            <label style="margin-top: 10px">Kategori Persediaan</label>
                        </div>
                        <div class="col-md-3" style="margin-top: 7px;">
                            <select class="form-control" id="id_kategori">
                                <%--<option value="obat">Obat</option>--%>
                                <%--<option value="alkses">Alkes</option>--%>
                                <%--<option value="bhp">Barang Habis Pakai</option>--%>
                            </select>
                        </div>
                        <div class="col-md-6"></div>
                    </div>
                </div>


                <%--<div class="row">--%>
                <%--<div class="form-group">--%>
                <%--<div class="col-md-2">--%>
                <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                <%--id="war_biji"><i class="fa fa-times"></i> required</p>--%>
                <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                <%--id="cor_biji"><i class="fa fa-check"></i> correct</p>--%>
                <%--</div>--%>
                <%--<div class="col-md-7"></div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <hr>
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-12">

                            <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_kandungan_obat">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <p id="kandungan_obat_error"></p>
                            </div>

                            <div id="table-kandungan">
                                <label style="margin-top: 7px">Kandungan Obat</label><button class="btn btn-sm btn-primary" style="float: right"  onclick="viewEditKandunganObat('', 'add')"><i class="fa fa-plus"></i></button>
                                <table class="table table-bordered table-striped" style="width: 100%">
                                    <thead>
                                    <td>Kandungan</td>
                                    <td>Bentuk</td>
                                    <td>Sediaan</td>
                                    <%--<td>Satuan</td>--%>
                                    <td align="center">Action</td>
                                    </thead>
                                    <tbody id="body-kandungan-obat">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat" ><i class="fa fa-arrow-right"></i> Save
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
                        <div class="col-md-3" align="right">Kandungan :</div>
                        <div class="col-md-7">
                            <select id="sel_fin_kandungan_obat" class="form-control select2" style="width: 100%">
                            </select>
                        </div>
                    </div>
                    <div class="row top-7">
                        <div class="col-md-3" align="right">Bentuk</div>
                        <div class="col-md-7">
                            <select id="sel_fin_bentuk_kandungan_obat" class="form-control select2" style="width: 100%">
                                <option value="">[Select One]</option>
                                <option value="tab">Tablet</option>
                                <option value="sys">Syrup</option>
                                <option value="inj">Injeksi</option>
                                <option value="cap">Capsule</option>
                                <option value="drop">Drop</option>
                                <option value="inf">Infus</option>
                                <option value="powder">Powder</option>
                                <option value="oral">Oral</option>
                                <option value="sachet">Sachet</option>
                                <option value="supp">Suppositoria</option>
                                <option value="pulvis">Pulvis</option>
                                <%--<option value="inf">Infusion</option>--%>
                                <option value="sol">Sol</option>
                                <option value="akdr">AKDR</option>
                                <option value="penfill">Penfill</option>
                                <option value="enema">Enema</option>
                                <option value="gel">Gel</option>
                                <option value="oint">Oint</option>
                                <option value="respule">Respule</option>
                                <option value="inhaler">Inhaler</option>
                                <option value="diskus">Diskus</option>
                                <option value="nebule">Nebule</option>
                                <option value="spray">Spray</option>
                                <option value="cream">Cream</option>
                                <option value="lar">Lar</option>
                                <option value="sabun">Sabun</option>
                                <option value="xr">XR</option>
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
                                <td ><b>Jenis Obat</b></td>
                                <td style="vertical-align: middle"><span id="det_jenis_obat"></span></td>
                            </tr>
                            <tr>
                                <td width="30%"><b>Merk Obat</b></td>
                                <td><span id="det_merk_obat"></span></td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-md-6">
                        <table class="table table-striped">
                            <tr>
                                <td width="30%"><b>Min Stok</b></td>
                                <td><span id="det_min_stok_obat"></span></td>
                            </tr>
                            <tr>
                                <td><b>Lembar/Box</b></td>
                                <td><span id="det_lembar_obat"></span></td>
                            </tr>
                            <tr>
                                <td><b>Biji/Lembar</b></td>
                                <td><span id="det_biji_obat"></span></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <td>ID Barang</td>
                    <td>Expired Date</td>
                    <td>Qty Box</td>
                    <td>Qty Lembar</td>
                    <td>Qty Biji</td>
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
        $('#add_nama_obat, #add_merek, #add_pabrik, #add_lembar_box, #add_biji_lembar, #add_min_stok').val('');
        $('#add_jenis_obat').val('').trigger('change');
        $('#form-edit').show();
        $('#add_box, #add_lembar, #add_biji').removeAttr('disabled');
        $('#flag-kronis').val('');
        $('#flag-generic').val('');
        $('#flag-bpjs').val('');
        $('#margin').val('0');
        $('#id_kategori').val('');
        var id = "";
        $('#load_obat, #war_nama, #war_jenis, #war_pabrik, #war_merek, #war_biji, #war_harga_box, #war_harga_lembar, #war_harga_biji, #war_min_stok').hide();
        $('#add_box, #add_lembar_box, #add_lembar, #add_biji_lembar').css('border','');
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
        $('#modal-obat').modal('show');
        resetSessionKandunganObat();
        getFromSessionKandunganObat('');
    }

    function saveObat(id){
        console.log("save Add 1 -> KLIK");

        var nama        = $('#add_nama_obat').val();
        var jenis       = $('#add_jenis_obat').val();
        var merek       = $('#add_merek').val();
        var pabrik      = $('#add_pabrik').val();
        var box         = $('#add_box').val();
        var lembarBox   = $('#add_lembar_box').val();
        var lembar      = $('#add_lembar').val();
        var bijiLembar  = $('#add_biji_lembar').val();
        var biji        = $('#add_biji').val();
        var hargaBox    = $('#add_harga_box').val();
        var hargaLembar = $('#add_harga_lembar').val();
        var hargaBiji   = $('#add_harga_biji').val();
        var minStok     = $('#add_min_stok').val();
        var flagKronis  = $('#flag-kronis').val();
        var flagGeneric = $('#flag-generic').val();
        var flagBpjs    = $('#flag-generic').val();
        var margin      = $('#margin').val();
        var idKategori  = $('#id_kategori').val();


        if (nama != '' && jenis != null && lembarBox != '' && bijiLembar != ''
            && merek != '' && minStok != '') {

            $('#save_obat').hide();
            $('#load_obat').show();

            if (id != '') {
                console.log(" edit --> KLIK");
                dwr.engine.setAsync(true);
                ObatAction.editObat(id, nama, jenis, merek, pabrik, lembarBox, bijiLembar, minStok, flagKronis, flagGeneric, flagBpjs, margin, idKategori,  function (response) {
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

                console.log("save Add 2 -> KLIK");
                dwr.engine.setAsync(true);
                ObatAction.saveObat(nama, jenis, merek, pabrik, box, lembarBox, lembar, bijiLembar, biji, hargaBox, hargaLembar, hargaBiji, minStok, flagKronis, flagGeneric, flagBpjs, margin, idKategori, function (response) {
                    console.log(response);
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
                        $('#obat_error').text("Terjadi kesalahan ketika proses simpan ke database..!");
                    }
                })
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
            if (box == '') {
                $('#add_box').css('border','red solid 1px');
            }
            if (lembarBox == '') {
                $('#add_lembar_box').css('border','red solid 1px');
            }
            if (lembar == '') {
                $('#add_lembar').css('border','red solid 1px');
            }
            if (bijiLembar == '') {
                $('#add_biji_lembar').css('border','red solid 1px');
            }
            if (biji == '') {
                $('#war_biji').show();
            }
            if (pabrik == '') {
                $('#war_pabrik').show();
            }
            if (minStok == '') {
                $('#war_min_stok').show();
            }
        }
    }

    function editObat(idObat, nama, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerBiji, idPbarik, mrek, minStok, flagKronis, flagGeneric, flagBpjs, margin, idKategori) {
        $('#load_obat, #war_nama, #war_jenis, #war_harga, #war_stok').hide();
        $('#save_obat').attr('onclick', 'saveObat(\'' + idObat + '\')').show();
        $('#add_nama_obat').val(nama);
        $('#add_id_obat').val(idObat);
        $('#add_jenis_obat').val(listSelectObatEdit(idObat)).trigger('change');
        $('#add_merek').val(mrek);
        $('#add_pabrik').val(idPbarik);
        $('#add_box').val(qtyBox).attr('disabled','');
        $('#add_lembar_box').val(lembarPerBox);
        $('#add_lembar').val(qtyLembar).attr('disabled','');
        $('#add_biji_lembar').val(bijiPerBiji);
        $('#add_biji').val(qtyBiji).attr('disabled','');
        $('#add_min_stok').val(minStok);
        $('#form-edit').hide();
        $('#fin_id_obat').val(idObat);
        $('#flag-kronis').val(flagKronis);
        $('#flag-generic').val(flagGeneric);
        $('#flag-bpjs').val(flagBpjs);
        $('#id_kategori').val(idKategori);
        $("#margin").val(margin);
        $('#modal-obat').modal({show:true, backdrop:'static'});
        $("#row-check-id-pabrik").hide();

        console.log( "kronis" + flagKronis);
        console.log( "generic " + flagGeneric);
        showListKandunganObat(idObat);
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
            $('#det_min_stok_obat').text(minStok +" Box");

            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            today = mm + '-' + dd + '-' + yyyy;

            var table = "";
            ObatAction.getListObatDetail(idObat, function (response) {
                if(response.length > 0){
                    $.each(response, function (i, item) {

                        var dateExp = $.datepicker.formatDate('mm-dd-yy', new Date(item.expiredDate));

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
                            '<td>'+formaterDate(item.expiredDate)+'</td>'+
                            '<td>'+item.qtyBox+'</td>'+
                            '<td>'+item.qtyLembar+'</td>'+
                            '<td>'+item.qtyBiji+'</td>'+
                            '</tr>'
                    });

                    $('#body_detail').html(table);
                }else{

                }
            });
            $('#modal-detail-obat').modal({show:true, backdrop:'static'});
        }
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

        console.log("getFromSessionKandunganObat, KLIK");
        ObatAction.getListFromSessionObat(idObat, function (list) {
            var str = "";
            $.each(list, function (i, item) {
                str += "<tr>" +
                    "<td>"+item.kandungan+"</td>"+
                    "<td>"+item.namaBentuk+"</td>"+
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
            $("#sel_fin_bentuk_kandungan_obat").val("");
            $("#fin_sediaan_kandungan_obat").val("");
            $("#fin_satuan_kandungan_obat").val("");
            var strBtn = "<button class='btn btn-success' onclick=\"saveKandunganObat(\'\')\"><i class='fa fa-check'></i>Save Add</button>";
            $("#btn-save-kandungan-obat").html(strBtn);
        } else {
            ObatAction.initEditKandunganObat(idKandunganObat, function (res) {
                if (res != null){
                    $("#sel_fin_kandungan_obat").val(res.idKandungan);
                    $("#sel_fin_bentuk_kandungan_obat").val(res.bentuk);
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
        var bentuk = $('#sel_fin_bentuk_kandungan_obat').val();
        var sediaan = $("#fin_sediaan_kandungan_obat").val();
        var satuan = $("#fin_satuan_kandungan_obat").val();

        console.log(kandungan);
        console.log(bentuk);

        if (id == null || id == ""){
            ObatAction.addKandunganObat(idObat, kandungan, bentuk, sediaan, satuan, function (res) {
                if (res.status == "success"){
                    $("#modal-view-kandugan-obat").modal('hide');
                    getFromSessionKandunganObat(idObat);
                } else {
                    alert("Gagal Menyimpan");
                }
            });
        } else {
            ObatAction.editKandunganObatDetail(id, idObat, kandungan, bentuk, sediaan, satuan, function (res) {
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
        console.log(checkIdPabrik);
        if (checkIdPabrik.checked == true){
            $("#dis-id-pabrik").hide();
        } else {
            $("#dis-id-pabrik").show();
        }
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>