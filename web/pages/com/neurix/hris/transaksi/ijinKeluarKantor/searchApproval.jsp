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
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinKeluarAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/IjinAction.js"/>'></script>
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
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };

        function cancelBtn2() {
            $('#view_dialog_function2').dialog('close');
        };

        $('body').on('hidden.bs.modal', '.modal', function () {
            $(this).removeData('bs.modal');
        });

        function showLoadingDialog(){
            $('#myModalLoading').modal('show');
        }

        function showAlert(){
            var verif = document.getElementById('verif').value;
            var erVerif = document.getElementById('erVerif').value;
            if(verif !=  ""){
                document.getElementById('succesAlert').style.display = 'block';
                var sc = document.getElementById('succesAlert').value;
                if ( sc != ""){
                    sc = "";
                }
                $("#succesAlert").fadeTo(1000, 500).slideUp(500, function(){
                    $("succesAlert").slideUp(500);
                });
            }else if(erVerif != "") {
                document.getElementById('errorAlert').style.display = 'block';
                erVerif = null;
                $("#errorAlert").fadeTo(1000, 500).slideUp(500, function(){
                    $("errorAlert").slideUp(500);
                });
            }
        }

        function link(){
            window.location.href="<s:url action='initForm_alat'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" onload="showAlert()">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Ijin Keluar Kantor
            <small>HRIS</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Approval Ijin Keluar Kantor</h3>
                    </div>

                    <form role="form" method="post" id="searchForm" action="viewNotifikasi_notifikasi.action?tipeNotif=TN55">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>

                            <div class="form-group">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Ijin Keluar kantor Id </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="ijinKeluarId" name="ijinKeluar.ijinKeluarId" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>NIP </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="personId" name="ijinKeluar.nip" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                        </td>
                                    </tr>
                                </table>
                                <br>

                            </div>
                            <div class="box-footer">
                                <table align="center">
                                    <tr>
                                        <td>
                                            <sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"
                                                       onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                                <i class="fa fa-search"></i>
                                                Search
                                            </sj:submit>
                                        </td>
                                        <td>

                                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="http://localhost:8080/hris/notifikasi/viewNotifikasi_notifikasi.action?tipeNotif=TN88"/>'">
                                                <i class="fa fa-repeat"></i> Reset
                                            </button>
                                        </td>

                                    </tr>
                                </table>
                            </div>
                            <center>
                                <table id="showdata" width="50%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu_ijin_keluar" openTopics="showDialogMenu" modal="true"
                                                       height="600" width="600" autoOpen="false"
                                                       title="Ijin Keluar Kantor ">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResultIKK" value="#session.listOfResultIjinKeluarKantor" scope="request" />
                                            <display:table name="listOfResultIKK" class="tableIjinKeluarKantor table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_ijinKeluarKantor.action" id="row" pagesize="20" style="font-size:10">
                                                <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDelete" namespace="/ijinKeluar" action="deleteKantor_ijinKeluar" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.ijinKeluarId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                    </sj:a>
                                                </display:column>
                                                <display:column property="ijinKeluarId" sortable="true" title="Ijin Keluar Kantor Id"/>
                                                <display:column property="nip" sortable="true" title="NIP"/>
                                                <display:column property="namaPegawai" sortable="true" title="Nama"/>
                                                <display:column property="positionName" sortable="true" title="Jabatan"/>
                                                <display:column property="divisiName" sortable="true" title="Bidang"/>
                                                <display:column property="stTanggalAwal" sortable="true" title="Tanggal"/>
                                                <display:column property="jamKeluar" sortable="true" title="Jam Keluar"/>
                                                <display:column property="jamKembali" sortable="true" title="Jam Kembali"/>
                                                <display:column media="html" title="Approve">
                                                    <s:if test="#attr.row.ijinKeluarApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:elseif test="#attr.row.notApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                    </s:elseif>
                                                    <s:else>
                                                        <a href="javascript:;"  data="<s:property value="%{#attr.row.ijinKeluarId}"/>" nama="<s:property value="%{#attr.row.namaPegawai}"/>" ijin="<s:property value="%{#attr.row.ijinId}"/>" href="javascript:;" class="item-edit">
                                                            <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                        </a>
                                                    </s:else>
                                                </display:column>
                                                <display:setProperty name="paging.banner.item_name">Approval</display:setProperty>
                                                <display:setProperty name="paging.banner.items_name">Approval</display:setProperty>
                                                <display:setProperty name="export.excel.filename">Approval.xls</display:setProperty>
                                                <display:setProperty name="export.csv.filename">Approval.csv</display:setProperty>
                                                <display:setProperty name="export.pdf.filename">Approval.pdf</display:setProperty>
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                        </div>

                    </form>

                </div>
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
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1000px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve Cuti Pegawai</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-3" >Ijin Keluar Kantor ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="IjinKeluarId1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="Nip1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="Name321" name="nip">

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="Unit1" name="ijinKeluar.unitId" readonly="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                         </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Bidang : </label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="divisiId1" name="ijinKeluar.divisiId" disabled="true"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid1" disabled="true" name="ijinKeluar.positionId" required="false" readonly="true"
                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jam Keluar : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="jamAwal1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jam Kembali  : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="JamKembali1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="TanggalAwal1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea class="form-control nip" rows="3" id="Keterangan1" readonly="true" name="nip"></textarea>
                        </div>
                    </div>
                </form>
                <center>
                    <table id="showdata1" width="100%">
                        <tr>
                            <td align="center">
                                <table style="width: 50%;" class="searchAnggotaTable table table-bordered">
                                </table>
                            </td>
                        </tr>
                    </table>

                </center>
            </div>
            <div class="modal-footer">
                <a id="btnApprove" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
                <a id="btnNotApprove" data="not" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-edit-not-approve" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Keterangan Not Approve Ijin Keluar Kantor</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormKeterangan">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" class="form-control" id="keterangan" name="nip"></textarea>
                        </div>
                    </div>
                </form>
                <table style="width: 100%;" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a id="btnNotApprove1" type="button" class="btn btn-default btn-danger"><i class="fa fa-check"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

<script type="application/javascript">

    window.loadAnggota =  function(IjinKeluarId){
        //alert(nip);
        $('.searchAnggotaTable').find('tbody').remove();
        $('.searchAnggotaTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
//        alert(IjinKeluarId);
//        var ijinId = document.getElementById("IjinKeluarId1").value;
        IjinKeluarAction.searchAnggota(IjinKeluarId,function(listdata) {
            console.log(listdata);
            tmp_table = "<thead style='font-size: 14px; color: white' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>No</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc''>Nip</th>"+
                    "<th style='text-align: center; background-color:  #3c8dbc'>Nama Pegawai</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + i+1 + '</td>' +
                        '<td align="center">' + item.nip + '</td>' +
                        '<td align="center">' + item.namaPegawai + '</td>' +
                        "</tr>";
            });
            $('.searchAnggotaTable').append(tmp_table);
        });
    }





    var tmp_data = new Array();
    var namaMt = [];

    function cek(id, flag){
        alert(id + " " + flag);
    }

    function ok(id, flag){
        //alert(id + " " + flag);

        dwr.engine.setAsync(false);
        AlatAction.init(id, flag, function (response) {
            namaMt = response;
        });

        //alert('idnya : ' + namaMt.kodeAlat + ' flag : ' + namaMt.flag);
        $('input[id=kodeAlat]').val(namaMt.kodeAlat);
        $('input[id=namaAlat]').val(namaMt.namaAlat);
        $('input[id=flag]').val(namaMt.flag);


        $('#modal-edit').modal('show');
    }

    $(document).ready(function() {
        $('#tanggalBerobat').datepicker({
            dateFormat: 'dd-mm-yy'
        });
//        $('#errorTimestampFrom').datepicker({
//            dateFormat: 'dd-mm-yy'
//        });
    });
    $('#btnApprove').click(function(){
        var who = $('#myForm').attr('action');
        var ijinId = document.getElementById("IjinKeluarId1").value;
        var nip=document.getElementById("Nip1").value;
        if (confirm('Are you sure you want to save this Record?')) {
            dwr.engine.setAsync(false);
            IjinKeluarAction.saveApprove(ijinId, "Y",who,nip, function(listdata) {
                alert('Data Successfully Updated');
                $('#modal-edit').modal('hide');
                $('#myForm')[0].reset();
                location.reload();
            });
        }
    });

    $('.tableIjinKeluarKantor').on('click', '.item-edit', function(){
        $('#modal-edit').find('.modal-title').text('Approve Ijin');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
    });
    $('.tableIjinKeluarKantor').on('click', '.item-edit', function() {
        var IjinKeluarId = $(this).val().replace(/\n|\r/g, "");
        var IjinKeluarId = $(this).attr('data');
        var nama = $(this).attr('nama');
        var ijinid = $(this).attr('ijin');
        $('#IjinKeluarId1').val(IjinKeluarId);
        IjinKeluarAction.approveAtasan(IjinKeluarId, function (listdata) {
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalAwal);
                $('#Nip1').val(item.nip);
                $('#Name321').val(item.namaPegawai);
                $('#jamAwal1').val(item.jamKeluar);
                $('#JamKembali1').val(item.jamKembali);
                $('#positionid1').val(item.positionId);
                $('#divisiId1').val(item.divisiId);
                $('#TanggalAwal1').val((myDate.getDate()) + ' - ' + ("0" + (myDate.getMonth() + 1)).slice(-2) + ' - ' + myDate.getFullYear());
                $('#Unit1').val(item.unitId).change();
                $('#Keterangan1').val(item.keterangan).change();
                $('#keterangan').val(item.noteApproval).change();
//                $('#PejabatSementara1').val(item.pegawaiPenggantiSementara);
                if (item.ijinKeluarApproveStatus == true) {
                    $('#btnNotApprove').hide();
                    $('#btnApprove').hide();
                } else {
                    $('#btnNotApprove').show();
                    $('#btnApprove').show();
                }

                loadAnggota(IjinKeluarId);


            });
        });
    });
    $('#btnNotApprove').on('click', function() {
        $('#modal-edit-not-approve').find('.modal-title').text('Keterangan Not Approve Cuti Pegawai');
        $('#modal-edit-not-approve').modal('show');
    });
    $('#btnNotApprove1').click(function(){
        var who = "atasan";
        var IjinKeluarId = document.getElementById("IjinKeluarId1").value;
        var note = document.getElementById("keterangan").value;
        var nip=document.getElementById("Nip1").value;
        if(note != ''){
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);

                IjinKeluarAction.saveApprove(IjinKeluarId, note, who,nip, function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    location.reload();
                });
            }
        }else{
            alert('Silahkan isi keterangan Not Approve !');
        }

    });
</script>


