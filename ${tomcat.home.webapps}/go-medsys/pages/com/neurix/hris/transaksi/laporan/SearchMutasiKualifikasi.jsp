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
    <script type='text/javascript' src='<s:url value="/dwr/interface/SppdAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/StudyAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AbsensiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BiodataAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TrainingAction.js"/>'></script>
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

        function link(){
            window.location.href="<s:url action='initForm_alat'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >
<div id="modal-study" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1000px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <table style="width: 100%;" class="tableStudy table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-training" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1000px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <table style="width: 100%;" class="tableTraining table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-jabatan" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:800px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <table style="width: 100%;" id="tabelDetailJabatan" class="tabelDetailJabatan table table-bordered"></table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-absensi" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="formGaji">

                    <div class="form-group">
                        <label class="control-label col-sm-1" >Bulan</label>
                        <input type="text" id="nipAbsensi" style="display:none;">
                        <div class="col-sm-4">
                            <select class="form-control" id="bulanAbsensi">
                                <option value="01"> Januari </option>
                                <option value="02"> Februari </option>
                                <option value="03"> Maret </option>
                                <option value="04"> April </option>
                                <option value="05"> Mei </option>
                                <option value="06"> Juni </option>
                                <option value="07"> Juli </option>
                                <option value="08"> Agustus </option>
                                <option value="09"> September </option>
                                <option value="10"> Oktober </option>
                                <option value="11"> Nopember </option>
                                <option value="12"> Desember </option>
                            </select>
                        </div>

                        <label class="control-label col-sm-1" >Tahun</label>

                        <div class="col-sm-3">
                            <select id="tahunAbsensi" class="form-control" ></select>
                        </div>

                        <div class="col-sm-3">
                            <a type="button" class="btn btn-primary" id="btnCariAbsensi" ><i class="fa fa-search"></i> Cari</a>
                        </div>
                    </div>

                </form>
                <table style="width: 100%;" class="tableAbsensi table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Kualifikasi Calon Pejabat
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="mutasiKualifikasiForm" method="post"  theme="simple" namespace="/mutasi" action="searchMutasiKualifikasi_mutasi.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Jabatan :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                        <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="mutasi.positionLamaId"
                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
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
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="mutasi.branchLamaId"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[All Unit]" cssClass="form-control"/>
                                    </table>

                                </td>
                            </tr>

                            <%--<tr>
                                <td>
                                    <label class="control-label"><small>Divisi :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="mutasi.divisiLamaId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>

                                </td>
                            </tr>--%>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Status :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'rotasi':'Rotasi' , 'promosi' : 'Promosi'}" cssClass="form-control" id="pulangVia" name="mutasi.status"/>
                                    </table>

                                </td>
                            </tr>

                        </table>


                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="mutasiKualifikasiForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>

                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initFormKualifikasi_mutasi"/>'">
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
                                                   height="800" width="1000" autoOpen="false"
                                                   title="Sppd ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfSppd" value="#session.listOfKualifikasi" scope="request" />
                                        <display:table name="listOfSppd" class=" tableKualifikasi table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_mutasi.action" id="row" pagesize="30" style="font-size:10">
                                            <display:column property="nip" sortable="true" title="NIP"  />
                                            <display:column property="nama" sortable="true" title="Nama Pegawai"  />
                                            <display:column property="divisiBaruName" sortable="true" title="Divisi"  />
                                            <display:column property="positionBaruName" sortable="true" title="Jabatan"  />
                                            <display:column property="branchBaruName" sortable="true" title="Unit"  />
                                            <display:column media="html" title="Absensi">
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-absensi">
                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                </a>
                                            </display:column>

                                            <display:column media="html" title="Pendidikan">
                                                <a href="javascript:;"  data="<s:property value="%{#attr.row.nip}"/>" href="javascript:;" class="item-pendidikan" >
                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                </a>
                                            </display:column>

                                            <display:column media="html" title="Masa Kerja">
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.nip}"/>" class="item-jabatan">
                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                </a>
                                            </display:column>

                                            <display:column media="html" title="Training">
                                                <a href="javascript:;"  data="<s:property value="%{#attr.row.nip}"/>" href="javascript:;" class="item-training" >
                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                </a>
                                            </display:column>
                                            <display:setProperty name="paging.banner.item_name">MutasiKualifikasi</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">MutasiKualifikasi</display:setProperty>
                                            <display:setProperty name="export.excel.filename">MutasiKualifikasi.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">MutasiKualifikasi.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">MutasiKualifikasi.pdf</display:setProperty>
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

</body>

</html>

<script>
    $(document).ready(function() {
        var d = new Date();
        var n = d.getFullYear();

        $('#tahunAbsensi').empty();
        for(a = n-5 ; a <= (n + 5) ; a++){
            $('#tahunAbsensi').append($("<option></option>")
                    .attr("value", a)
                    .text(a));
        }

        $('#btnApprove').click(function(){
            var who = $('#myForm').attr('action');
            var nip = document.getElementById("sppdId1").value;
            //alert(who);
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);

                SppdAction.saveApprove(nip, "Y",who, function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    location.reload();
                    loadPerson('', 'Y');
                });
            }
        });

        $('#btnNotApprove').click(function(){
            var who = $('#myForm').attr('action');
            var nip = document.getElementById("sppdId1").value;
            var note = document.getElementById("notApprove").value;
            //alert(note);
            if(note != ''){
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);

                    SppdAction.saveApprove(nip, note, who, function(listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-edit').modal('hide');
                        $('#myForm')[0].reset();
                        location.reload();
                        loadPerson('', 'Y');
                    });
                }
            }else{
                alert('Silahkan isi keterangan Not Approve !');
            }

        });

        $('#tanggalBerangkat').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });

        $('#tanggalKembali').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true
        });


        $('.tableSppd').on('click', '.item-edit', function(){
            var sppdId = $(this).val().replace(/\n|\r/g, "");
            var sppdId = $(this).attr('data');
            $('#sppdId1').val(sppdId);
            //alert(sppdId);
            SppdAction.approveAtasan(sppdId, function(listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalSppdBerangkat);
                    var myDate1 = new Date(item.tanggalSppdKembali);
                    $('#tanggalBerangkat1').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                    $('#tanggalKembali1').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());

                    $('#nama1').val(item.personName);
                    $('#branchId1').val(item.branchId).change();
                    $('#positionId1').val(item.positionId).change();
                    $('#dinas1').val(item.dinas).change();
                    $('#divisiId1').val(item.divisiId).change();
                    $('#keperluan1').val(item.tugasSppd);
                    $('#berangkatDari1').val(item.berangkatDari);
                    $('#tujuan1').val(item.tujuanKe);
                    $('#berangkatVia1').val(item.berangkatVia);
                    $('#kembaliVia1').val(item.pulangVia);
                    $('#notApprove').val(item.notApprovalNote);
                    $('#approveAtasan').val(item.approvalName);
                    $('#approveAtasanId').val(item.approvalId);
                    if(item.sppdApproveStatus == true){
                        $('#btnNotApprove').hide();
                        $('#btnApprove').hide();
                        //location.reload();
                        loadPerson(sppdId, 'Y');
                    }else{
                        $('#btnNotApprove').show();
                        $('#btnApprove').show();
                        //location.reload();
                        loadPerson(sppdId, 'N');
                    }


                });
            });
            //alert( $('#branchId1').text);
            $('#modal-edit').find('.modal-title').text('Approve SPPD');
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'atasan');
        });

        $('.tableSppd').on('click', '.item-editSdm', function(){
            var sppdId = $(this).val().replace(/\n|\r/g, "");
            var sppdId = $(this).attr('data');
            $('#sppdId1').val(sppdId);
            //alert(sppdId);
            SppdAction.approveAtasan(sppdId, function(listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalSppdBerangkat);
                    var myDate1 = new Date(item.tanggalSppdKembali);
                    $('#tanggalBerangkat1').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                    $('#tanggalKembali1').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());

                    $('#dinas1').val(item.dinas).change();
                    $('#nama1').val(item.personName);
                    $('#branchId1').val(item.branchId).change();
                    $('#positionId1').val(item.positionId).change();
                    $('#divisiId1').val(item.divisiId).change();
                    $('#keperluan1').val(item.tugasSppd);
                    $('#berangkatDari1').val(item.berangkatDari);
                    $('#tujuan1').val(item.tujuanKe);
                    $('#berangkatVia1').val(item.berangkatVia);
                    $('#kembaliVia1').val(item.pulangVia);
                    $('#notApprove').val(item.notApprovalSdmNote);
                    $('#approveAtasan').val(item.approvalName);
                    $('#approveAtasanId').val(item.approvalId);
                    if(item.sppdApproveSdm == true){
                        $('#btnNotApprove').hide();
                        $('#btnApprove').hide();
                        //location.reload();
                        loadPerson(sppdId, "Y");
                    }else{
                        $('#btnNotApprove').show();
                        $('#btnApprove').show();
                        //location.reload();
                        loadPerson(sppdId, "N");
                    }


                });
            });
            //alert( $('#branchId1').text);
            $('#modal-edit').find('.modal-title').text('Approve SPPD');
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'Sdm');
        });


    });

    window.loadPerson =  function(id, status){
        //alert(nip);
        var branch         = $('select[name=branchText] option:selected').text();
        var divisi         = $('select[name=divisiText] option:selected').text();
        //alert(branch);
        $('.sppdPersonTable').find('tbody').remove();
        $('.sppdPersonTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        SppdAction.searchAnggota(id, function(listdata) {

            tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Branch</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Divisi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Position</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Nip Pengganti</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Nama Pengganti</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.personName + '</td>' +
                        '<td align="center">' + branch + '</td>' +
                        '<td align="center">' + divisi + '</td>' +
                        '<td align="center">' + item.positionName + '</td>' +
                        '<td align="center">' + item.tipePerson + '</td>' +
                        '<td align="center">' + '<input readonly type="text" value="'+item.pejabatSementara+'" name="anamaPengganti'+i+'" id="anamaPengganti'+i+'" class="form-control">' + '</td>' +
                        '<td align="center">' + '<input  value="'+item.pejabatSementaraNama+'" type="text" onchange="updatePengganti('+'\''+item.personId+'\''+',\''+i+'\')" class="form-control namaPengganti" id="namaPengganti'+i+'" name="namaPersonPengganti">' + '</td>' +

                        "</tr>";
            });
            $('.sppdPersonTable').append(tmp_table);

        });
    }

    $('.sppdPersonTable').on('click', '.item-apply', function(){
        var nip = $(this).attr('data');
        var id = $(this).attr('datai');
        var pengganti = document.getElementById("anamaPengganti"+id).value;
        SppdAction.searchAnggotaAdd(nip, pengganti, function(listdata) {
            alert('berhasil');
        });
    });

    function updatePengganti(nip, id){
        var pengganti = document.getElementById("anamaPengganti"+id).value;
        SppdAction.searchAnggotaAdd(nip, pengganti, function(listdata) {
            //alert('berhasil');
        });
    }

    $(document).on('keydown', '.namaPengganti', function() {
        var id = this.id;
        var branch         = $('select[name=branchText] option:selected').val();
        var divisi         = $('select[name=divisiText] option:selected').val();
        //alert(id);
        $('#' + id).typeahead({
            minLength: 1,
            source: function (query, process) {
                functions = [];
                mapped = {};

                var data = [];
                dwr.engine.setAsync(false);
                UserAction.initComboUser(query,branch, divisi, function (listdata) {
                    data = listdata;
                });
                //alert(prov);
                $.each(data, function (i, item) {
                    //alert(item.userId);
                    var labelItem = item.username;
                    mapped[labelItem] = { id: item.userId, label: labelItem, branchId : item.branchId, divisiId: item.divisiId, positionId : item.positionId };
                    functions.push(labelItem);
                });

                process(functions);
            },

            updater: function (item) {
                var selectedObj = mapped[item];
                var namaAlat = selectedObj.label;
                //updatePengganti(,//selectedObj.id)
                $('#a'+id).val(selectedObj.id);
                return namaAlat;
            }
        });
    });

    $('.tableKualifikasi').on('click', '.item-pendidikan', function(){
        var id = $(this).attr('data');
        
        $('.tableStudy').find('tbody').remove();
        $('.tableStudy').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        StudyAction.searchData(id, function(listdata) {

            tmp_table = "<thead style='font-size: 15px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Type Study</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Study Name</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Awal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun Akhir</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var typeStudy = "-";
                var studyName = "-";
                var tahunAwal = "-";
                var tahunAkhir = "-";

                if(item.typeStudy != null){
                    typeStudy = item.typeStudy;
                }
                if(item.studyName != null){
                    studyName = item.studyName;
                }
                if(item.tahunAwal != null){
                    tahunAwal = item.tahunAwal;
                }
                if(item.tahunAkhir != null){
                    tahunAkhir = item.tahunAkhir;
                }

                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td >' + typeStudy + '</td>' +
                        '<td >' + studyName + '</td>' +
                        '<td align="center">' + tahunAwal + '</td>' +
                        '<td align="center">' + tahunAkhir + '</td>' +
                        "</tr>";
            });
            $('.tableStudy').append(tmp_table);
        });
        
        $('#modal-study').find('.modal-title').text('Riwayat Pendidikan');
        $('#modal-study').modal('show');
    });

    $('.tableKualifikasi').on('click', '.item-training', function(){
        var id = $(this).attr('data');
        loadTraining(id);
        $('#modal-training').find('.modal-title').text('Training');
        $('#modal-training').modal('show');
    });

    $('#btnCariAbsensi').click(function() {
        var bulan = document.getElementById("bulanAbsensi").value;
        var tahun = document.getElementById("tahunAbsensi").value;
        var nip = document.getElementById("nipAbsensi").value;

        $('.tableAbsensi').find('tbody').remove();
        $('.tableAbsensi').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";

        var blnThn = tahun +"-"+bulan;
        AbsensiAction.cariAbseni(nip, blnThn, function(listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Tanggal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>In</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Out</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var jamMasuk = "-";
                var jamKeluar = "-";
                var statusName = "-";

                if(item.jamMasuk != null){
                    jamMasuk = item.jamMasuk;
                }
                if(item.jamKeluar != null){
                    jamKeluar = item.jamKeluar;
                }
                if(item.statusName != null){
                    statusName = item.statusName;
                }

                tmp_table += '<tr  style="font-size: 12px;" ">' +
                        '<td >' + item.stTanggal+ '</td>' +
                        '<td align="center">' + jamMasuk+ '</td>' +
                        '<td align="center">' + jamKeluar  + '</td>' +
                        '<td align="center">' + statusName + '</td>' +
                        "</tr>";
            });
            $('.tableAbsensi').append(tmp_table);
        });
    });

    $('.tableKualifikasi').on('click', '.item-absensi', function(){
        var nip = $(this).attr('data');
        $('#nipAbsensi').val(nip);
        $('.tableAbsensi').find('tbody').remove();
        $('.tableAbsensi').find('thead').remove();


        $('#modal-absensi').find('.modal-title').text('Absensi');
        $('#modal-absensi').modal('show');
    });

    $('.tableKualifikasi').on('click', '.item-jabatan', function(){
        var nip = $(this).attr('data');

        $('.tabelDetailJabatan').find('tbody').remove();
        $('.tabelDetailJabatan').find('thead').remove();
        $('.tabelDetailJabatan').find('tfoot').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        BiodataAction.historyJabatan(nip, function(listdata) {
            tmp_table = "<thead style='color: white; font-size: 15px'><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Bidang</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Unit</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Jabatan</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Status</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>PJS</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tahun</th>"+
                    "</tr></thead>";
            var i = i ;
            var totalPoin = 0;
            $.each(listdata, function (i, item) {
                var bidang = "-";
                var branchName = "-";
                var positionName = "-";
                var status = "-";
                var pjs = "-";
                var tahun = "-";

                if(item.bidang!= null){
                    bidang = item.bidang;
                }
                if(item.tahun != null){
                    tahun = item.tahun;
                }
                if(item.branchName!= null){
                    branchName = item.branchName;
                }
                if(item.positionName!= null){
                    positionName = item.positionName;
                }
                if(item.status!= null){
                    status = item.status;
                }
                if(item.pjsFlag!= null){
                    if(item.pjsFlag == "Y"){
                        pjs = "Iya";
                    }else{
                        pjs = 'Tidak';
                    }
                }
                tmp_table += '<tr  style="font-size: 12px">' +
                        '<td >' + bidang + '</td>' +
                        '<td >' + branchName+ '</td>' +
                        '<td >' + positionName+ '</td>' +
                        '<td >' + status+ '</td>' +
                        '<td >' + pjs+ '</td>' +
                        '<td >' + tahun+ '</td>' +
                        "</tr>";
            });
            $('.tabelDetailJabatan').append(tmp_table);
        });

        $('#modal-jabatan').find('.modal-title').text('Jenjang Karir');
        $('#modal-jabatan').modal('show');
    });
    
    window.loadTraining = function(nip){
        //alert(nip);
        $('.tableTraining').find('tbody').remove();
        $('.tableTraining').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        TrainingAction.searchData(nip, function(listdata) {

            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tipe Training</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Awal</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Tanggal Akhir</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Instansi</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Kota</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td >' + item.tipeTraining + '</td>' +
                        '<td align="center">' + item.stTrainingStartdate+ '</td>' +
                        '<td align="center">' + item.stTrainingEndDate+ '</td>' +
                        '<td align="center">' + item.instansi+ '</td>' +
                        '<td align="center">' + item.kota+ '</td>' +
                        "</tr>";
            });
            $('.tableTraining').append(tmp_table);
        });
    }

</script>

