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
    <script type='text/javascript' src='<s:url value="/dwr/interface/MutasiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
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

<%@ include file="/pages/common/headerNav.jsp" %>
<script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Mutasi / Nonaktif
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Mutasi / Nonaktif</h3>
                    </div>
                    <div class="box-body">
                        <s:form id="mutasiForm" method="post"  theme="simple" namespace="/mutasi" action="search_mutasi.action" cssClass="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-4">ID Mutasi</label>
                                <div class="col-sm-4">
                                    <s:textfield id="mutasiId" cssStyle="margin-top: 7px"
                                                 name="mutasi.mutasiId" required="false"
                                                 readonly="false" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Unit</label>
                                <div class="col-sm-4">
                                    <s:if test='mutasi.branchIdUser == "01"'>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="mutasi.branchLamaId" cssStyle="margin-top: 7px"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </s:if>
                                    <s:else>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="mutasi.branchLamaId" cssStyle="margin-top: 7px"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        <s:hidden id="branchId" name="pembayaranUtangPiutang.branchLamaId" />
                                    </s:else>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Bidang / Divisi</label>
                                <div class="col-sm-4">
                                    <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                    <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="mutasi.divisiLamaId" cssStyle="margin-top: 7px ; width: 100%"
                                              listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control  select2" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Posisi</label>
                                <div class="col-sm-4">
                                    <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                    <s:select list="#comboPosition.listOfComboPosition" id="positionId" name="mutasi.positionLamaId" cssStyle="margin-top: 7px ; width: 100%"
                                              listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control  select2"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Nama</label>
                                <div class="col-sm-4">
                                    <s:textfield id="personName" name="mutasi.nama" cssClass="form-control" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">NIP</label>
                                <div class="col-sm-4">
                                    <s:textfield id="nip" name="mutasi.nip" cssClass="form-control" readonly="true" cssStyle="margin-top: 7px"/>
                                </div>
                            </div>
                            <script type='text/javascript'>
                            var functions, mapped;
                            $('#personName').typeahead({
                                minLength: 1,
                                source: function (query, process) {
                                    var branch = $('#branchId').val();
                                    if (branch!=""){
                                        functions = [];
                                        mapped = {};
                                        var data = [];
                                        dwr.engine.setAsync(false);
                                        MedicalRecordAction.initComboPersonil(query,branch, function (listdata) {
                                            data = listdata;
                                        });
                                        if (data.length==0){
                                            alert("Nama tidak ditemukan");
                                            $('#personName').val("");
                                        } else{
                                            $.each(data, function (i, item) {
                                                var labelItem =item.nip+ " || "+ item.namaPegawai;
                                                mapped[labelItem] = {pegawai:item.namaPegawai, id: item.nip};
                                                functions.push(labelItem);
                                            });
                                            process(functions);
                                        }
                                    } else{
                                        alert("Unit belum dipilih");
                                        $('#personName').val("");
                                    }
                                },
                                updater: function (item) {
                                    var selectedObj = mapped[item];
                                    var nama = selectedObj.pegawai;
                                    $('#nip').val(selectedObj.id);
                                    return nama;
                                }
                            });
                            </script>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Status</label>
                                <div class="col-sm-4">
                                    <s:action id="comboStatusMutasi" namespace="/statusMutasi" name="initComboStatusMutasi_statusMutasi"/>
                                    <s:select list="#comboStatusMutasi.listOfComboStatusMutasi" id="statusMutasi" listKey="statusMutasiId"
                                              listValue="statusMutasiName" headerKey="" headerValue="[Select One]" name="mutasi.status" cssStyle="margin-top: 7px"
                                              cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Tanggal Efektif</label>
                                <div class="col-sm-4">
                                    <div class="input-group date" style="margin-top: 7px">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <s:textfield id="tanggalEfektif" name="mutasi.stTanggalEfektif" cssClass="form-control pull-right"
                                                     required="false"/>
                                        <script>
                                            $('#tanggalEfektif').datepicker({
                                                dateFormat: 'dd-mm-yy',
                                                changeMonth: true,
                                                changeYear: true
                                            });
                                        </script>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-6" style="margin-top: 7px">
                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="mutasiForm" id="search" name="search"
                                               onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                        <i class="fa fa-search"></i>
                                        Search
                                    </sj:submit>
                                    <a href="add_mutasi.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Mutasi / Nonaktif</a>
                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_mutasi"/>'">
                                        <i class="fa fa-refresh"></i> Reset
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
                                            <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                 src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                 name="image_indicator_write">
                                        </center>
                                    </sj:dialog>
                                    <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                               height="650" width="900" autoOpen="false" title="View Detail">
                                        <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>" alt="Loading..."/></center>
                                    </sj:dialog>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Mutasi</h3>
                            </div>
                            <div class="box-body">
                                <table id="tableMutasi" class="table table-bordered table-striped" style="font-size: 12px">
                                    <thead >
                                    <tr bgcolor="#90ee90">
                                        <td>ID Mutasi</td>
                                        <td>NIP</td>
                                        <td>Nama Pegawai</td>
                                        <td>Status</td>
                                        <td>Unit Lama</td>
                                        <td>Posisi Lama</td>
                                        <td>Unit Baru</td>
                                        <td>Posisi Baru</td>
                                        <td>Tanggal Efektif</td>
                                        <td align="center">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="#session.listOfResult" status="listOfMutasi" id="listOfMutasi" var="row">
                                        <tr>
                                            <td><s:property value="mutasiId"/></td>
                                            <td><s:property value="nip"/></td>
                                            <td><s:property value="nama"/></td>
                                            <td><s:property value="statusName"/></td>
                                            <td><s:property value="branchLamaName"/></td>
                                            <td><s:property value="positionLamaName"/></td>
                                            <td><s:property value="branchBaruName"/></td>
                                            <td><s:property value="positionBaruName"/></td>
                                            <td><s:property value="stTanggalEfektif"/></td>
                                            <td align="center">
                                                <s:if test='#row.statusName == "Mutasi" || #row.statusName == "Rotasi"'>
                                                <a href="javascript:;" data="<s:property value="%{#row.mutasiId}"/>" class="item-print" id="<s:property value="%{#row.mutasiId}"/>">
                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                </a>
                                                </s:if>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
                            </div>
                        </s:form>
                    </div>
                </div>
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
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width: 500px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Nomor Surat</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Mutasi Id : </label>
                        <div class="col-sm-8">
                            <input readonly type="text"  class="form-control nip" id="idMutasi" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >No. Sk Mutasi : </label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="noSurat" name="nip">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="button" class="btn btn-default btn-success">Cetak Surat</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    /*function printSk(){
        $('#modal-edit').modal('show');
    }*/
    $(document).ready(function(){
        $('#tableMutasi').DataTable({
            "pageLength": 50,
            "order": [[0, "desc"]]
        });
        $('#tableMutasi').on('click', '.item-print', function(){
            var id = $(this).attr('id');

            if (confirm('Print Surat Keterangan Mutasi ? ')) {
                var addr = "printReportMutasi_mutasi.action?idMutasi="+id;
                window.open(addr,'_blank');
            }
            // $('#idMutasi').val(id);
            // $('#modal-edit').modal('show');
        });

        $('#btnSave').click(function(){
            var idMutasi = document.getElementById("idMutasi").value;
            var noSurat = document.getElementById("noSurat").value;
            var msg='Apakah Anda ingin Surat Keterangan ?';
            var msg2="Field:\n";
            dwr.engine.setAsync(false);
            if(noSurat!=''){
                if (confirm(msg)) {
                    var addr = "printReportMutasi_mutasi.action?idMutasi="+idMutasi;
                    window.open(addr,'_blank');
//                    var currentLoc = window.location.href;
//                    var newAdd = currentLoc.split('simrs/')[0] + addr;
//                    window.location.href = newAdd;
                }
            }else{
                msg2 += "-No Surat\n";
                alert(msg2+"Masih Kosong");
            }

        });



    });
</script>