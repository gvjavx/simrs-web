<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalBiayaKacamataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalKacamataAction.js"/>'></script>

    <style>
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <script type='text/javascript'>
        $(document).ready(function() {
            window.checkDec = function (el){
                var ex = /^[0-9]+\.?[0-9]*$/;
                if(ex.test(el.value)==false){
                    el.value = el.value.substring(0,el.value.length - 1);
                }
            }

            function numberWithCommas(x) {
                return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            }

            $('#tanggalBerobat').datepicker({
                dateFormat: 'dd-mm-yy',
                changeMonth: true,
                changeYear: true
            });
            $('#editTanggalBerobat').datepicker({
                dateFormat: 'dd-mm-yy',
                changeMonth: true,
                changeYear: true
            });

            $('#btnSave').click(function(){
                var tanggal = document.getElementById("tanggalBerobat").value;
                var nip = document.getElementById("nip1").value;
                var divisiId = document.getElementById("divisiId1").value;
                var positionId = document.getElementById("positionId1").value;
                var bagianId = document.getElementById("bagianId1").value;
                var golonganId = document.getElementById("golonganId1").value;
                var branchId = document.getElementById("branchId1").value;

                var maxBiayaNilai = document.getElementById("maxBiayaNilai").value;
                var jumlahBiaya = document.getElementById("jumlahBiaya").value;
                var tipe = document.getElementById("tipeKacamata").value;

                if(tanggal != '' && nip != '' && jumlahBiaya != '' && parseFloat(jumlahBiaya) > 0){
                    if(parseFloat(maxBiayaNilai) >= parseFloat(jumlahBiaya)){
                        if(tipe == "Lensa"){
                            if (confirm('Apakah data isian sudah benar?')) {
                                MedicalKacamataAction.saveAdd(nip, branchId, divisiId, bagianId, golonganId, positionId, tipe, tanggal, jumlahBiaya, function(listdata) {
                                    alert('Data berhasil disimpan');
                                    $('#modal-edit').modal('hide');
                                });
                            }
                        }else{
                            var hasilCek = cekKacamata();
                            if(hasilCek == ""){
                                if (confirm('Apakah data isian sudah benar?')) {
                                    MedicalKacamataAction.saveAdd(nip, branchId, divisiId, bagianId, golonganId, positionId, tipe, tanggal, jumlahBiaya, function(listdata) {
                                        alert('Data berhasil disimpan');
                                        $('#modal-edit').modal('hide');
                                    });
                                }
                            }else{
                                alert(hasilCek);
                            }
                        }
                    }else{
                        alert('Jumlah biaya tidak boleh melebihi dari max biaya');
                    }
                }else{
                    var strError = ""
                    if(tanggal == ''){
                        strError += "Tanggal, ";
                    }

                    if(nip == ''){
                        strError += "Nama (Autocomplete), ";
                    }

                    if(jumlahBiaya == ''){
                        strError += "Jumlah Biaya, ";
                    }

                    strError += " Tidak boleh kosong";

                    if(parseFloat(jumlahBiaya) < 0){
                        strError += "Jumlah Biaya harus diisi";
                    }

                    alert(strError);
                }
            });

            $('#btnDelete').click(function(){
                var id = document.getElementById("deleteKacamataId").value;
                MedicalKacamataAction.saveDeleteKacamata(id, function(listdata) {
                    alert('Data berhasil dihapus');
                    $('#modal-delete').modal('hide');
                    location.reload();
                });
            });

            $('#btnEdit').click(function(){
                var id = document.getElementById("editKacamataId").value;
                var tanggal = document.getElementById("editTanggalBerobat").value;
                var nip = document.getElementById("editNip1").value;
                var divisiId = document.getElementById("editDivisiId1").value;
                var positionId = document.getElementById("editPositionId1").value;
                var bagianId = document.getElementById("editBagianId1").value;
                var golonganId = document.getElementById("editGolonganId1").value;
                var branchId = document.getElementById("editBranchId1").value;

                var maxBiayaNilai = document.getElementById("editMaxBiayaNilai").value;
                var jumlahBiaya = document.getElementById("editJumlahBiaya").value;
                var tipe = document.getElementById("editTipeKacamata").value;

                if(tanggal != '' && nip != '' && jumlahBiaya != '' && parseFloat(jumlahBiaya) > 0){
                    if(parseFloat(maxBiayaNilai) >= parseFloat(jumlahBiaya)){
                        if(tipe == "Lensa"){
                            if (confirm('Apakah data isian sudah benar?')) {
                                MedicalKacamataAction.saveEdit(id, nip, branchId, divisiId, bagianId, golonganId, positionId, tipe, tanggal, jumlahBiaya, function(listdata) {
                                    alert('Data berhasil disimpan');
                                    $('#modal-edit').modal('hide');
                                });
                            }
                        }else{
                            var hasilCek = cekKacamataById(id);
                            if(hasilCek == ""){
                                if (confirm('Apakah data isian sudah benar?')) {
                                    MedicalKacamataAction.saveEdit(id, nip, branchId, divisiId, bagianId, golonganId, positionId, tipe, tanggal, jumlahBiaya, function(listdata) {
                                        alert('Data berhasil disimpan');
                                        $('#modal-edit').modal('hide');
                                    });
                                }
                            }else{
                                alert(hasilCek);
                            }
                        }
                    }else{
                        alert('Jumlah biaya tidak boleh melebihi dari max biaya');
                    }
                }else{
                    var strError = ""
                    if(tanggal == ''){
                        strError += "Tanggal, ";
                    }

                    if(nip == ''){
                        strError += "Nama (Autocomplete), ";
                    }

                    if(jumlahBiaya == ''){
                        strError += "Jumlah Biaya, ";
                    }

                    strError += " Tidak boleh kosong";

                    if(parseFloat(jumlahBiaya) < 0){
                        strError += "Jumlah Biaya harus diisi";
                    }

                    alert(strError);
                }
            });

            window.cekBiayaKacamata = function(){
                var branchId = document.getElementById("branchId1").value;
                var golonganId = document.getElementById("golonganId1").value;
                var tipe = document.getElementById("tipeKacamata").value;

                if(golonganId != ''){
                    MedicalBiayaKacamataAction.getBiaya(branchId, golonganId, tipe, function(listdata) {
                        $('#maxBiaya').val(numberWithCommas(parseFloat(listdata).toFixed(0)));
                        $('#maxBiayaNilai').val(listdata);
                    });
                }else{
                    alert('Isi Nama pegawai terlebih dahulu');
                }
            }

            window.cekBiayaKacamataEdit = function(){
                var branchId = document.getElementById("editBranchId1").value;
                var golonganId = document.getElementById("editGolonganId1").value;
                var tipe = document.getElementById("editTipeKacamata").value;

                if(golonganId != ''){
                    MedicalBiayaKacamataAction.getBiaya(branchId, golonganId, tipe, function(listdata) {
                        $('#editMaxBiaya').val(numberWithCommas(parseFloat(listdata).toFixed(0)));
                        $('#editMaxBiayaNilai').val(listdata);
                    });
                }else{
                    alert('Isi Nama pegawai terlebih dahulu');
                }
            }

            window.cekKacamata = function(){
                var nip = document.getElementById("nip1").value;
                var hasil = "";
                MedicalKacamataAction.cekTersediaMedicalKacamata(nip, function(listdata) {
                    hasil = listdata;
                });
                return hasil;
            }

            window.cekKacamataById = function(id){
                var nip = document.getElementById("editNip1").value;
                var hasil = "";
                MedicalKacamataAction.cekTersediaMedicalKacamata(id, nip, function(listdata) {
                    hasil = listdata;
                });
                return hasil;
            }
        });

        function link(){
            window.location.href="<s:url action='initForm_medicalKacamata'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Medical Record (Kacamata)
            <small>e-HEALTH</small>
        </h1>
        <%--<ol class="breadcrumb">--%>
        <%--<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>--%>
        <%--<li class="active">Here</li>--%>
        <%--</ol>--%>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="golonganForm" method="post"  theme="simple" namespace="/medicalKacamata" action="search_medicalKacamata.action" cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>



                        <table>
                            <tr>
                                <td width="10%" align="center">
                                    <%@ include file="/pages/common/message.jsp" %>
                                </td>
                            </tr>
                        </table>

                        <table >
                            <tr>
                                <td>
                                    <label class="control-label"><small>Medical Kacamata Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="golonganId" name="medicalKacamata.medicalKacamataId" required="false" readonly="false" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="unitId" name="medicalKacamata.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Bidang :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="medicalKacamata.divisiId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Bagian :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                        <s:select list="#comboBagian.comboListOfPositionBagian" id="bagianId" name="medicalKacamata.bagianId"
                                                  listKey="bagianId" listValue="bagianName" headerKey="" headerValue=""
                                                  cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Flag :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="medicalKacamata.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                        </table>



                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="golonganForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <td>
                                        <%--<button
                                                id="addMedicalKacamata" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
                                        </button>--%>
                                        <sj:a id="addMedicalKacamata" cssClass="btn btn-success" >
                                            <i class="fa fa-plus"></i>
                                            Add Medical Kacamata
                                        </sj:a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_medicalKacamata"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="70%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="600" width="500" autoOpen="false"
                                                   title="Medical Kacamata">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfGolongan" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfGolongan" class="tableMedicalKacamata table tableData table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_medicalKacamata.action" id="row" pagesize="14" style="font-size:10">
                                            <display:column media="html" title="Edit">
                                                <a href="javascript:;"  data="<s:property value="%{#attr.row.medicalKacamataId}"/>" href="javascript:;"
                                                   id="medicalEdit" class="medicalEdit">
                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                </a>
                                            </display:column>

                                            <display:column media="html" title="Delete">
                                                <a href="javascript:;"  data="<s:property value="%{#attr.row.medicalKacamataId}"/>" href="javascript:;"
                                                   id="medicalDelete" class="medicalDelete">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_edit">
                                                </a>
                                            </display:column>

                                            <display:column media="html" title="Print">
                                                <a href="javascript:;"  data="<s:property value="%{#attr.row.medicalKacamataId}"/>" href="javascript:;"
                                                   id="medicalPrint" class="medicalPrint">
                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                </a>
                                            </display:column>

                                            <display:column property="medicalKacamataId" sortable="true" title="ID" />
                                            <display:column property="branchName" sortable="true" title="Unit"  />
                                            <display:column property="bidangName" sortable="true" title="Bidang"  />
                                            <display:column property="bagianName" sortable="true" title="Bagian"  />
                                            <display:column property="positionName" sortable="true" title="Posisi"  />
                                            <display:column property="tipePembayaran" sortable="true" title="Pembayaran"  />
                                            <display:column property="strTanggalPembayaran" sortable="true" title="Tanggal Pembayaran"  />
                                            <display:column property="strTanggalBerakhir" sortable="true" title="Tanggal Akhir"  />
                                            <display:column property="strBiaya" sortable="true" title="Jumlah"  />
                                            <display:setProperty name="paging.banner.item_name">MedicalKacamata</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">MedicalKacamata</display:setProperty>
                                            <display:setProperty name="export.excel.filename">MedicalKacamata.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">MedicalKacamata.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">MedicalKacamata.pdf</display:setProperty>
                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </s:form>
                </td>
            </tr>
        </table>

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

<script>
    $('#addMedicalKacamata').click(function(){
        $('#myForm')[0].reset();
        $('#modal-add').find('.modal-title').text('Add Medical Kacamata');
        $('#modal-add').modal('show');
    });

    $('.tableMedicalKacamata').on('click', '.medicalDelete', function(){
        var kacamataId = $(this).attr('data');
        $('#deleteKacamataId').val(kacamataId);
        $('#modal-delete').find('.modal-title').text('Delete Medical Kacamata');
        $('#modal-delete').modal('show');
    });

    $('.tableMedicalKacamata').on('click', '.medicalEdit', function(){
        var kacamataId = $(this).attr('data');
        $('#myFormEdit')[0].reset();

        MedicalKacamataAction.getDataKacamataById(kacamataId, function(listdata) {
            $("#editTanggalBerobat").val(listdata.strTanggalPembayaran);
            $("#editNip").val(listdata.namaPegawai);
            $("#editNip1").val(listdata.nip);
            $("#editKacamataId").val(listdata.medicalKacamataId);
            $("#editDivisiId1").val(listdata.bidangId).change();
            $("#editPositionId1").val(listdata.positionId).change();
            $("#editBagianId1").val(listdata.bagianId).change();
            $("#editGolonganId1").val(listdata.golonganId).change();
            $("#editBranchId1").val(listdata.branchId).change();

            $("#editJumlahBiaya").val(listdata.biaya);
            $("#editTipeKacamata").val(listdata.tipePembayaran).change();
            //cekBiayaKacamataEdit();
        });

        $('#modal-edit').find('.modal-title').text('Edit Medical Kacamata');
        $('#modal-edit').modal('show');
    });

    $('.tableMedicalKacamata').on('click', '.medicalPrint', function(){
        var kacamataId = $(this).attr('data');
        if (confirm('Apakah Anda ingin mencetak medical kacamata?')) {
            window.location.href = 'searchReport_medicalKacamata?id='+ kacamataId ;
        }
    });
</script>
<div id="modal-add" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width:450px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="nip" name="nip">
                            <input style="display: none" type="text" class="form-control nip" id="nipNama" name="nip">
                            <input style="display: none" type="text" class="form-control" id="nip1" name="nip1">
                            <input style="display: none" type="text" class="form-control" id="nipOld" name="nip1">
                        </div>
                        <script type='text/javascript'>
                            var functions, mapped;
                            // var prov = document.getElementById("provinsi1").value;
                            $('#nip').typeahead({
                                minLength: 1,
                                source: function (query, process) {
                                    functions = [];
                                    mapped = {};

                                    var data = [];
                                    dwr.engine.setAsync(false);

                                    MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
                                        data = listdata;
                                    });

                                    $.each(data, function (i, item) {
                                        var labelItem =item.nip+ " || "+ item.namaPegawai;
                                        var labelNip = item.nip;
                                        mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem, branchId : item.branch,
                                            divisiId: item.divisi, positionId : item.positionId, bagianId : item.bagianId, golonganId : item.golonganId };
                                        functions.push(labelItem);
                                    });

                                    process(functions);
                                },

                                updater: function (item) {
                                    var selectedObj = mapped[item];
                                    var namaAlat = selectedObj.label;
                                    document.getElementById("nip1").value = selectedObj.id;
                                    document.getElementById("nipNama").value = selectedObj.pegawai;

                                    $('#positionId1').val(selectedObj.positionId).change();
                                    $('#branchId1').val(selectedObj.branchId).change();
                                    $('#divisiId1').val(selectedObj.divisiId).change();
                                    $('#bagianId1').val(selectedObj.bagianId).change();
                                    $('#golonganId1').val(selectedObj.golonganId).change();
                                    cekBiayaKacamata();
                                    return namaAlat;
                                }
                            });

                        </script>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" for="divisiId1">Unit :</label>
                        <div class="col-sm-8">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId1" name="sppd.branchId" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bidang :</label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="divisiId1"  disabled="true"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bagian :</label>
                        <div class="col-sm-8">
                            <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                            <s:select list="#comboBagian.comboListOfPositionBagian" id="bagianId1" name="position.bagianId" disabled="true"
                                      listKey="bagianId" listValue="bagianName" headerKey="" headerValue=""
                                      cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Posisi :</label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="positionId1" disabled="true"
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Golongan :</label>
                        <div class="col-sm-8">
                            <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                            <s:select list="#initComboTipe.listComboGolongan" id="golonganId1" name="biodata.golongan"
                                      listKey="golonganId" listValue="golonganName" headerKey="" headerValue="" disabled="true" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal :</label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" id="tanggalBerobat" class="form-control pull-right"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tipe : </label>
                        <div class="col-sm-8">
                            <s:select list="#{'Lensa':'Lensa' , 'Gagang' : 'Gagang', 'Set' : '1 Set (Gagang & Lensa)'}" cssClass="form-control"
                                      id="tipeKacamata" name="sppdPerson.kembaliVia" onchange="cekBiayaKacamata();"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Max Biaya: </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="maxBiaya" name="nip" readonly>
                            <input type="text" class="form-control" style="display: none" id="maxBiayaNilai" name="nip" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jumlah Biaya: </label>
                        <div class="col-sm-8">
                            <input onkeyup="checkDec(this);" type="text" class="form-control nip" id="jumlahBiaya">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">

                <button id="btnSave" type="button" class="btn btn-default btn-success">Save Add Kacamata</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-delete" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width:450px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <input type="text" style="display: none" class="form-control nip" id="deleteKacamataId">
                <h4>Apakah anda akan menghapus data medical kacamata?</h4>
            </div>
            <div class="modal-footer">

                <button id="btnDelete" type="button" class="btn btn-default btn-danger">Delete Kacamata</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width:450px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormEdit">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="editNip" name="nip" readonly>
                            <input style="display: none" type="text" class="form-control nip" id="editNipNama" name="nip">
                            <input style="display: none" type="text" class="form-control" id="editKacamataId" name="nip1">
                            <input style="display: none" type="text" class="form-control" id="editNip1" name="nip1">
                            <input style="display: none" type="text" class="form-control" id="editNipOld" name="nip1">
                        </div>
                        <script type='text/javascript'>
                            var functions, mapped;
                            // var prov = document.getElementById("provinsi1").value;
                            $('#editNip').typeahead({
                                minLength: 1,
                                source: function (query, process) {
                                    functions = [];
                                    mapped = {};

                                    var data = [];
                                    dwr.engine.setAsync(false);

                                    MedicalRecordAction.initComboPersonil(query,'', function (listdata) {
                                        data = listdata;
                                    });

                                    $.each(data, function (i, item) {
                                        var labelItem =item.nip+ " || "+ item.namaPegawai;
                                        var labelNip = item.nip;
                                        mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip, label: labelItem, branchId : item.branch,
                                            divisiId: item.divisi, positionId : item.positionId, bagianId : item.bagianId, golonganId : item.golonganId };
                                        functions.push(labelItem);
                                    });

                                    process(functions);
                                },

                                updater: function (item) {
                                    var selectedObj = mapped[item];
                                    var namaAlat = selectedObj.label;
                                    document.getElementById("editNip1").value = selectedObj.id;
                                    document.getElementById("editNipNama").value = selectedObj.pegawai;

                                    $('#editPositionId1').val(selectedObj.positionId).change();
                                    $('#editBranchId1').val(selectedObj.branchId).change();
                                    $('#editDivisiId1').val(selectedObj.divisiId).change();
                                    $('#editBagianId1').val(selectedObj.bagianId).change();
                                    $('#editGolonganId1').val(selectedObj.golonganId).change();
                                    cekBiayaKacamataEdit();
                                    return namaAlat;
                                }
                            });

                        </script>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Unit :</label>
                        <div class="col-sm-8">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="editBranchId1" name="sppd.branchId" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bidang :</label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="editDivisiId1" name="sppd.divisiId" disabled="true"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">Bagian :</label>
                        <div class="col-sm-8">
                            <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                            <s:select list="#comboBagian.comboListOfPositionBagian" id="editBagianId1" name="position.bagianId" disabled="true"
                                      listKey="bagianId" listValue="bagianName" headerKey="" headerValue=""
                                      cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">Posisi :</label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                            <s:select list="#comboPosition.listOfComboPosition" id="editPositionId1" disabled="true"
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4">Golongan :</label>
                        <div class="col-sm-8">
                            <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                            <s:select list="#initComboTipe.listComboGolongan" id="editGolonganId1" name="biodata.golongan"
                                      listKey="golonganId" listValue="golonganName" headerKey="" headerValue="" disabled="true" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal :</label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" id="editTanggalBerobat" class="form-control pull-right"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tipe : </label>
                        <div class="col-sm-8">
                            <s:select list="#{'Lensa':'Lensa' , 'Gagang' : 'Gagang', 'Set' : '1 Set (Gagang & Lensa)'}" cssClass="form-control"
                                      id="editTipeKacamata" onchange="cekBiayaKacamataEdit();"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Max Biaya: </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="editMaxBiaya" name="nip" readonly>
                            <input type="text" class="form-control" style="display: none" id="editMaxBiayaNilai" name="nip" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jumlah Biaya: </label>
                        <div class="col-sm-8">
                            <input onkeyup="checkDec(this);" type="text" class="form-control nip" id="editJumlahBiaya">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">

                <button id="btnEdit" type="button" class="btn btn-default btn-success">Save Edit Kacamata</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>

