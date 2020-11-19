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
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiAction.js"/>'></script>
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
            Cuti Pegawai

        </h1>
    </section>
    <!-- Main content -->
    <section class="content">

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Approval Cuti Pegawai</h3>
                    </div>

                    <form role="form" method="post" id="searchForm" action="viewNotifikasi_notifikasi.action?tipeNotif=TN66">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>
                            <table width="100%" align="center">
                                <tr>
                                    <td align="center">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label>Cuti Pegawai Id </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:textfield id="cutiPegawaiId" name="cutiPegawai.cutiPegawaiId" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label>NIP </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:textfield id="personId" name="cutiPegawai.nip" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                                </td>
                                            </tr>
                                        </table>
                                        <br><br>
                                        <table>
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"
                                                               onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>

                                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_cutiPegawai"/>'">
                                                        <i class="fa fa-repeat"></i> Reset
                                                    </button>
                                                </td>

                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <center>
                                <table id="showdata" width="90%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="cutiPegawai">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResultIK" value="#session.listOfResultCutiPegawai" scope="request" />
                                            <display:table name="listOfResultIK" class="tableCutiPegawai table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_Notifikasi.action" id="row" pagesize="20" style="font-size:10">

                                                <display:column media="html" title="Approve">
                                                    <s:if test="#attr.row.cutiPegawaiApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:elseif test="#attr.row.notApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">
                                                    </s:elseif>
                                                    <s:else>
                                                        <a href="javascript:;"  data="<s:property value="%{#attr.row.cutiPegawaiId}"/>" nama="<s:property value="%{#attr.row.namaPegawai}"/>" ijin="<s:property value="%{#attr.row.ijinId}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                    </a>
                                                    </s:else>
                                                </display:column>
                                                <display:column property="cutiPegawaiId" sortable="true" title="Id" />
                                               <display:column property="nip" sortable="true" title="NIP"  />
                                               <display:column property="posisiName" sortable="true" title="Jabatan"  />
                                               <display:column property="divisiName" sortable="true" title="Bidang"  />
                                               <display:column property="unitName" sortable="true" title="Unit"  />
                                               <display:column property="namaPegawai" sortable="true" title="Nama Pegawai"  />
                                               <display:column property="cutiName" sortable="true" title="Cuti"  />
                                               <display:column property="lamaHariCuti" sortable="true" title="Lama Cuti"  />
                                               <display:column property="stTanggalDari" sortable="true" title="Tanggal Dari"  />
                                               <display:column property="stTanggalSelesai" sortable="true" title="Tanggal Selesai"  />
                                               <display:column property="pegawaiPenggantiSementara" sortable="true" title="Pegawai Pengganti Sementara"  />
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
                        <label class="control-label col-sm-3" >Cuti Pegawai ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control nip" id="CutiPegawaiId1" name="nip">
                        </div>
                    </div>
                    <div style="display: none;" class="form-group">
                        <label class="control-label col-sm-3" >Jenis Cuti : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="JenisCuti1" name="nip">
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
                            <input type="text" readonly class="form-control" id="Nama321" name="nip">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="Unit321" name="cutiPegawai.unitId" required="true"
                                      listKey="branchId" listValue="branchName" readonly="true" headerKey="" disabled="true" headerValue="[Select one]" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Bidang : </label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="Divisi1" name="cutiPegawai.divisiId"
                                      listKey="departmentId" listValue="departmentName" disabled="true" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="Posisi1" name="cutiPegawai.posisiId" required="false" readonly="true"
                                      listKey="stPositionId" disabled="true" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama Cuti : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly="true" class="form-control nip" id="CutiName321" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jumlah Hari : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly="true" class="form-control nip" id="LamaHariCuti321" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Awal : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="TanggalAwal1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Akhir : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="TanggalAkhir1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Pegawai Pengganti Sementara : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="PejabatSementara1" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama Pegawai Pengganti Sementara : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="PejabatSementara12" readonly name="nip">
                        </div>
                    </div>
                    <script>
                        $('#PejabatSementara1').typeahead({
                            minLength: 1,
                            source: function (query, process) {
                                functions = [];
                                mapped = {};
                                var data = [];
                                var unit = $('#Unit321').val();
                                if (unit=="") {
                                    alert("Unit is empty");
                                    $('#PejabatSementara12').val("");
                                    $('#PejabatSementara1').val("");
                                }else{
                                    dwr.engine.setAsync(false);
                                    CutiPegawaiAction.initComboPersonil(query, unit, function (listdata) {
                                        data = listdata;
                                    });
                                    $.each(data, function (i, item) {
                                        var labelItem = item.nip+" "+item.namaPegawai;
                                        mapped[labelItem] = { id: item.nip,nama:item.namaPegawai, label: labelItem,divisi: item.divisi,branch:item.branch,positionId:item.positionId,golonganId:item.golonganId , tanggalaktif:item.stTanggalAktif};
                                        functions.push(labelItem);
                                    });
                                    process(functions);
                                }
                            },
                            updater: function (item) {
                                var selectedObj = mapped[item];
                                $('#PejabatSementara12').val(selectedObj.nama);
                                return selectedObj.id;
                            }
                        });
                    </script>

                </form>
                <table style="width: 100%;" class="sppdPersonTable table table-bordered">
                </table>
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
                <h4 class="modal-title">Keterangan Not Approve Cuti Pegawai</h4>
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
        var nip = document.getElementById("CutiPegawaiId1").value;
        var nipid=document.getElementById("Nip1").value;
        var pejabatanSementara1=document.getElementById("PejabatSementara1").value;
        var pejabatanSementara12=document.getElementById("PejabatSementara12").value;
        if (confirm('Are you sure you want to save this Record?')) {
            dwr.engine.setAsync(false);
            CutiPegawaiAction.saveApprove(nip, "Y",who,nipid,pejabatanSementara1,pejabatanSementara12, function(listdata) {
                alert('Data Successfully Updated');
                $('#modal-edit').modal('hide');
                $('#myForm')[0].reset();
                location.reload();
            });
        }
    });

    $('.tableCutiPegawai').on('click', '.item-edit', function() {
        var CutiPegawaiId = $(this).val().replace(/\n|\r/g, "");
        var CutiPegawaiId = $(this).attr('data');
        var nip = $(this).attr('nip');
/*        alert(nip);*/
//        $('#Nip1').val(nip);
        var namapegawai;
        $('#CutiPegawaiId1').val(CutiPegawaiId);
        //alert(CutiPegawaiId);
        CutiPegawaiAction.approveAtasan(CutiPegawaiId, function(listdata) {
            $.each(listdata, function (i, item) {
                var myDate = new Date(item.tanggalDari);
                var myDate1 = new Date(item.tanggalSelesai);
                namapegawai = item.namaPegawai;
                //alert(item.namaPegawai);
                $('#Nama321').val(namapegawai);
                $('#Nip1').val(item.nip);
                $('#TanggalAwal1').val((myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear());
                $('#TanggalAkhir1').val((myDate1.getDate()) +' - '+ ("0" + (myDate1.getMonth() + 1)).slice(-2) +' - '+myDate1.getFullYear());
                $('#Divisi1').val(item.divisiId).change();
                $('#Posisi1').val(item.posisiId).change();
                $('#Unit321').val(item.unitId).change();
                $('#LamaHariCuti321').val(item.lamaHariCuti).change();
                $('#CutiName321').val(item.cutiName).change();
                $('#keterangan').val(item.noteApproval).change();
                $('#PejabatSementara1').val(item.pegawaiPenggantiSementara);
                if(item.cutiPegawaiApproveStatus == true){
                    $('#btnNotApprove').hide();
                    $('#btnApprove').hide();
                }else{
                    $('#btnNotApprove').show();
                    $('#btnApprove').show();
                }
            });
        });
        $('#modal-edit').find('.modal-title').text('Approve Cuti Pegawai');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
    });
    $('#btnNotApprove').on('click', function() {
        $('#modal-edit-not-approve').find('.modal-title').text('Keterangan Not Approve Cuti Pegawai');
        $('#modal-edit-not-approve').modal('show');
    });
    $('#btnNotApprove1').click(function(){
        var who = "atasan";
        var CutiPegawaiId = document.getElementById("CutiPegawaiId1").value;
        var note = document.getElementById("keterangan").value;
        var nipid=document.getElementById("Nip1").value;
        //alert(note);
        if(note != ''){
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);

                CutiPegawaiAction.saveApprove(CutiPegawaiId, note, who,nipid, function(listdata) {
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


