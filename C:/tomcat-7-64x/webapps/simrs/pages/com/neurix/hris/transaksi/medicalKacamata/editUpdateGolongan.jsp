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
    <script type='text/javascript' src='<s:url value="/dwr/interface/UpdateGolonganAction.js"/>'></script>
    <style>
        .checkApprove {width: 20px; height: 20px;}
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .modal { overflow: auto !important; }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
        .ui-dialog{
            z-index: 6000;
        }
    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_alat'/>";
        }

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {
            window.clos = function() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='initForm_payroll.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var branch = document.getElementById("branchId").value;
                var bulan = document.getElementById("bulanPayroll").value;
                var tahun = document.getElementById("tahunPayroll").value;

                if (branch != '' && bulan != '' && tahun != '') {
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        event.originalEvent.options.submit = false;
                    }
                } else {

                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (branch == '') {
                        msg += 'Field <strong>Branch</strong> is required.' + '<br/>';
                    }

                    if (bulan == '') {
                        msg += 'Field <strong>Bulan</strong> is required.' + '<br/>';
                    }

                    if (tahun == '') {
                        msg += 'Field <strong>Tahun</strong> is required.' + '<br/>';
                    }

                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');

                }
            });
        });


    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<div id="modal-promosi" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-4" >NIP</label>
                        <div class="col-sm-6">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiNip" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Nama</label>
                        <div class="col-sm-6">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiNama">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bagian</label>
                        <div class="col-sm-6">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiBagian">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jabatan</label>
                        <div class="col-sm-6">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiJabatan">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-4" >Golongan / Poin</label>
                        <div class="col-sm-4">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiGolongan">
                        </div>
                        <div class="col-sm-2">
                            <input style="text-align: left" readonly type="text" class="form-control nip" id="promosiPoin">
                        </div>
                    </div>

                    <hr>

                    <table style="width: 100%;" id="tabelDetailPromosi" class="tabelDetailPromosi table table-bordered"></table>
                </form>
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
            Edit Golongan
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="sppdForm" method="post"  theme="simple" namespace="/updateGolongan" action="saveEditData_updateGolongan.action" cssClass="well form-horizontal">

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
                                    <label class="control-label"><small>Unit :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="unitId" name="updateGolongan.branchId" disabled="true"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Periode :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}" id="flag"
                                                  name="updateGolongan.periode" disabled="true" headerKey="" headerValue="" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                        </table>



                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <button type="button" class="btn btn-success" id="btnSaveData">
                                            <i class="fa fa-save"></i> Save
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="menujuLink('initForm_updateGolongan.action', 'UpdateGolongan');">
                                            <i class="fa fa-close"></i> Cancel
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>

                        <center>
                            <table id="showdata" width="70%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="500" width="500" autoOpen="false"
                                                   title="Payroll ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listDataPayroll" value="#session.listDataGolongan" scope="request" />
                                        <display:table name="listDataPayroll" class="tablePayroll table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_edit_payroll.action" export="true" id="row" pagesize="1000" style="font-size:10">

                                            <display:column media="html" title="Edit">
                                                <a href="javascript:;"
                                                   nip="<s:property value="%{#attr.row.nip}"/>"
                                                   nama="<s:property value="%{#attr.row.namaPegawai}"/>"
                                                   strGolonganLama="<s:property value="%{#attr.row.strGolonganLama}"/>"
                                                   golonganLamaId="<s:property value="%{#attr.row.golonganIdBefore}"/>"
                                                   poinLama="<s:property value="%{#attr.row.poinBefore}"/>"
                                                   poinLebihLama="<s:property value="%{#attr.row.poinLebihBefore}"/>"

                                                   golonganId="<s:property value="%{#attr.row.golonganId}"/>"
                                                   poin="<s:property value="%{#attr.row.poin}"/>"
                                                   poinLebih="<s:property value="%{#attr.row.poinLebih}"/>"
                                                   class="item-edit">
                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                </a>
                                            </display:column>

                                            <display:column property="nip" sortable="true" title="NIP"  />
                                            <display:column property="namaPegawai" sortable="true" title="Nama" />
                                            <display:column property="bagianName" sortable="true" title="Bagian" />
                                            <display:column property="strGolonganLama" sortable="true" title="Golongan Lama" />
                                            <display:column property="nilaiAngka" sortable="true" title="Nilai Angka SMK" />
                                            <display:column property="nilaiHuruf" sortable="true" title="Nilai Huruf SMK" />
                                            <display:column property="strGolongan" sortable="true" title="Golongan Baru" />
                                            <display:column property="status" sortable="true" title="Status" />

                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>

                        <div id="actions" class="form-actions">
                            <table>
                                <tr>
                                    <div id="crud">
                                        <td>
                                            <table>
                                                <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="350" width="600" autoOpen="false" title="Searching ...">
                                                    Please don't close this window, server is processing your request ...
                                                    </br>
                                                    </br>
                                                    </br>
                                                    <center>
                                                        <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_write">
                                                    </center>
                                                </sj:dialog>

                                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                           buttons="{
                                                              'OK':function() {
                                                                      clos();
                                                                   }
                                                            }"
                                                >
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                    Record has been saved successfully.
                                                </sj:dialog>

                                                <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                           height="250" width="600" autoOpen="false" title="Error Dialog"
                                                           buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); }
                                                                    }"
                                                >
                                                    <div class="alert alert-error fade in">
                                                        <label class="control-label" align="left">
                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                                        </label>
                                                    </div>
                                                </sj:dialog>

                                                <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                                           height="280" width="500" autoOpen="false" title="Warning"
                                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                                >
                                                    <div class="alert alert-error fade in">
                                                        <label class="control-label" align="left">
                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                            <br/>
                                                            <center><div id="errorValidationMessage"></div></center>
                                                        </label>
                                                    </div>
                                                </sj:dialog>
                                            </table>
                                        </td>
                                    </div>
                                </tr>
                            </table>
                        </div>
                    </s:form>
                </td>
            </tr>
        </table>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Golongan</h4>
            </div>
            <div class="modal-body" align="left">
                <form class="form-horizontal" id="formEdit">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nip </label>

                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="editNip" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama</label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="editNama" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Golongan Lama</label>
                        <div class="col-sm-9">
                            <input readonly type="text" class="form-control nip" id="editStrGolonganLama" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Golongan Baru</label>
                        <div class="col-sm-9">
                            <s:action id="initComboTipe" namespace="/golongan" name="initComboGolongan_golongan"/>
                            <s:select list="#initComboTipe.listComboGolongan" name="editGolonganId" id="editGolonganId"
                                      listKey="golonganId" listValue="golonganName" headerKey=""
                                      headerValue="" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Poin Baru</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control nip" id="editPoin" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Poin Lebih</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control nip" id="editPoinLebih" name="nip">
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <a id="btnSave" type="btn btn-success" class="btn btn-default btn-success"><i class="fa fa-save"></i> Save</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i> Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modal-biayaLainLain" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width: 700px">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myFormLainLain">
                    <div class="form-group">
                        <label class="control-label col-sm-2" style="padding-right:0px;">Keterangan</label>
                        <div class="col-sm-7">
                            <input style="text-align: left; padding-left: 5px; padding-right: 5px;" type="text" class="form-control nip" id="lainLainKet1" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >Biaya</label>

                        <div class="col-sm-2">
                            <input style="text-align: left; padding-left: 2px" type="number" onchange="hitungLainLain()" class="form-control nip" id="lainLainBiaya1" name="nip" value="0">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" style="padding-right:0px;">Keterangan</label>
                        <div class="col-sm-7">
                            <input style="text-align: left; padding-left: 5px; padding-right: 5px" type="text" class="form-control nip" id="lainLainKet2" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >Biaya</label>

                        <div class="col-sm-2">
                            <input style="text-align: left; padding-left: 2px" type="number" onchange="hitungLainLain()" class="form-control nip" id="lainLainBiaya2" name="nip" value="0">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" style="padding-right:0px;">Keterangan</label>
                        <div class="col-sm-7">
                            <input style="text-align: left; padding-left: 5px; padding-right: 5px; " type="text" class="form-control nip" id="lainLainKet3" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >Biaya</label>

                        <div class="col-sm-2">
                            <input style="text-align: left; padding-left: 2px" type="number" onchange="hitungLainLain()" class="form-control nip" id="lainLainBiaya3" name="nip" value="0">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" style="padding-right:0px;">Keterangan</label>
                        <div class="col-sm-7">
                            <input style="text-align: left; padding-left: 5px; padding-right: 5px;" type="text" class="form-control nip" id="lainLainKet4" name="nip" >
                        </div>

                        <label class="control-label col-sm-1" >Biaya</label>

                        <div class="col-sm-2">
                            <input style="text-align: left; padding-left: 2px" type="number" onchange="hitungLainLain()" class="form-control nip" id="lainLainBiaya4" name="nip" value="0">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" style="padding-right:0px;">Keterangan</label>
                        <div class="col-sm-7">
                            <input style="text-align: left; padding-left: 5px; padding-right: 5px;" type="text" class="form-control nip" id="lainLainKet5" name="nip">
                        </div>

                        <label class="control-label col-sm-1" >Biaya</label>

                        <div class="col-sm-2">
                            <input style="text-align: left; padding-left: 2px" type="number" onchange="hitungLainLain()" class="form-control nip" id="lainLainBiaya5" name="nip" value="0">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="control-label col-sm-10" style="padding-right:0px;">Total</label>
                        <div class="col-sm-2">
                            <input style="text-align: left; padding-left: 2px" type="number" readonly class="form-control nip" id="jumlahDetailLainLain" name="nip" value="0">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-success" id="btnDetailLain" data-dismiss="modal"><i class="fa fa-save"></i> Save</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-lembur" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:800px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <%--<font size="1" face="Courier New" >--%>
                <center>
                    <table style="width: 100%;" id="tableLembur" class="tableLembur table table-bordered">
                    </table>
                </center>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-success" id="btnRefreshLembur" >Refresh</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

</body>

</html>

<script>
    $('#btnSave').click(function(){
        var nip = document.getElementById("editNip").value;
        var golonganId = document.getElementById("editGolonganId").value;
        var poin = document.getElementById("editPoin").value;
        var poinLebih = document.getElementById("editPoinLebih").value;
        var golonganName = $("[name='editGolonganId'] option:selected").text();

        if (confirm('Apakah Anda ingin merubah Data?')) {
            UpdateGolonganAction.updateGolonganBySession(nip, golonganId, golonganName, poin, poinLebih, function(listdata){
                //alert(nip + ' - ' + golonganId + ' - ' + poin + ' - ' + poinLebih);
                alert("Berhasil");
                location.reload();
            });
        }
    });

    $('#btnSaveData').click(function(){
        if (confirm('Apakah Anda ingin menyimpan Data?')) {
            UpdateGolonganAction.saveData(function(listdata){
                //alert(nip + ' - ' + golonganId + ' - ' + poin + ' - ' + poinLebih);
                alert("Berhasil");
                menujuLink('initForm_updateGolongan.action', 'UpdateGolongan');
            });
        }
    });

    $(document).ready(function() {
        var url_string = window.location.href ;
        var url = new URL(url_string);
        var bulan = url.searchParams.get("bulan");
        var tahun = url.searchParams.get("tahun");
        var branchId = url.searchParams.get("branchId");
        var tipe = url.searchParams.get("tipe");

        $('#bulanPayroll').val(bulan).change();
        $('#tahunPayroll').val(tahun).change();
        $('#branchId').val(branchId).change();
        $('#tipe').val(tipe).change();


        if(bulan != null){
            $('#txtBulanPayroll').val(bulan);
            $('#txtTahunPayroll').val(tahun);
            $('#txtBranchId').val(branchId);
            $('#txtTipeId').val(tipe);
        }

        $('.tablePayroll').on('click', '.item-edit', function(){
            var nip = $(this).attr('nip');
            var nama = $(this).attr('nama');

            var strGolonganLama = $(this).attr('strGolonganLama');
            var golonganLamaId = $(this).attr('golonganLamaId');
            var poinLama = $(this).attr('poinLama');
            var poinLebihLama = $(this).attr('poinLebihLama');

            var golonganId = $(this).attr('golonganId');
            var poin = $(this).attr('poin');
            var poinLebih = $(this).attr('poinLebih');

            $('#editNip').val(nip);
            $('#editNama').val(nama);
            $('#editStrGolonganLama').val(strGolonganLama);

            $('#editGolonganId').val(golonganId).change();
            $('#editPoin').val(poin);
            $('#editPoinLebih').val(poinLebih);
            //alert(nip + "-" + nama + "-" + golonganLamaId + "-" + poinLama + "-" + poinLebihLama + "-" + golonganId + "-" + poin + "-" + poinLebih);
            $('#modal-edit').find('.modal-title').text('Edit Golongan');
            $('#modal-edit').modal('show');
        });

        window.menujuLink = function(param, param2){
            window.location.href=param;
        }

    });

</script>