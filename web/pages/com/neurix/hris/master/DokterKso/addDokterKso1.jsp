<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DokterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DokterKsoAction.js"/>'></script>
    <%--<script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>--%>

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
            window.location.href="<s:url action='initForm_dokterkso'/>";
        }

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $(document).ready(function() {
            window.close = function() {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href="<s:url action='search_dokterkso.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var nip = document.getElementById("nip1").value;
                var branchId = document.getElementById("branchId1").value;
                var masterId = document.getElementById("masterId1").value;
                var jenisKso = document.getElementById("jenisKso1").value;
                // var positionId = document.getElementById("positionId1").value;
                var persenKso = document.getElementById("persenKso1").value;
                var persenKs = document.getElementById("persenKs1").value;

                console.log(nip);
                console.log(branchId);
                console.log(masterId);
                console.log(jenisKso);
                // console.log(positionId);
                console.log(persenKso);
                console.log(persenKs);

                if (nip != ''&& branchId != '' && masterId != '' && jenisKso != ''
                        && persenKso != '' && persenKs != '') {
                    var status ="";
                    dwr.engine.setAsync(false);
                    DokterKsoAction.cekBeforeSave(nip, jenisKso, masterId,"add",function (listData) {
                        status=listData;
                    });
                    if (status==""){
                        if (confirm('Do you want to save this record?')) {
                            event.originalEvent.options.submit = true;
                            $.publish('showDialog');
                        } else {
                            // Cancel Submit comes with 1.8.0
                            event.originalEvent.options.submit = false;
                        }
                    }else {
                        event.originalEvent.options.submit = false;
                        document.getElementById('errorValidationMessage').innerHTML = status;
                        $.publish('showErrorValidationDialog');
                    }
                } else {
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (nip == '') {
                        msg += 'Field <strong>ID Dokter </strong> is required.' + '<br/>';
                    }
                    if (branchId == '') {
                        msg += 'Field <strong>Unit </strong> is required.' + '<br/>';
                    }
                    if (masterId == '') {
                        msg += 'Field <strong>Jenis Pasien </strong> is required.' + '<br/>';
                    }
                    if (jenisKso == '') {
                        msg += 'Field <strong>Jenis KSO </strong> is required.' + '<br/>';
                    }
                    if (positionId == '') {
                        msg += 'Field <strong>Position ID </strong> is required.' + '<br/>';
                    }
                    if (persenKso == '') {
                        msg += 'Field <strong>Persen KSO </strong> is required.' + '<br/>';
                    }
                    if (persenKs == '') {
                        msg += 'Field <strong>Persen KS </strong> is required.' + '<br/>';
                    }

                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');
                }
            });
            $.subscribe('beforeProcessAddPerson', function (event, data) {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            });
        });


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
            Add Dokter KSO
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-plus"></i> Add Dokter KSO</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="addDokterKsoForm" method="post" theme="simple" namespace="/dokterkso" action="saveAdd_dokterkso" cssClass="well form-horizontal">
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
                                                    <label class="control-label"><small>ID Dokter :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="nip1" name="dokterKso.nip" cssClass="form-control"
                                                                     maxlength="12"
                                                        />
                                                        <script>
                                                            $(document).ready(function() {
                                                                var functions, mapped;
                                                                $('#nip1').typeahead({
                                                                    minLength: 1,
                                                                    source: function (query, process) {
                                                                        functions = [];
                                                                        mapped = {};
                                                                        var data = [];
                                                                        dwr.engine.setAsync(false);
                                                                        DokterAction.initTypeaheadDokter(query,function (listdata) {
                                                                            data = listdata;
                                                                        });
                                                                        $.each(data, function (i, item) {
                                                                            var labelItem = item.idDokter + " | " + item.namaDokter;
                                                                            mapped[labelItem] = {
                                                                                id: item.idDokter,
                                                                                nama: item.namaDokter,
                                                                                branch: item.branchId
                                                                            };
                                                                            functions.push(labelItem);
                                                                        });
                                                                        process(functions);
                                                                    },
                                                                    updater: function (item) {
                                                                        var selectedObj = mapped[item];
                                                                        $('#namaDokter1').val(selectedObj.nama);
                                                                        $('#branchId1').val(selectedObj.branch);
                                                                        console.log(selectedObj.branch);
                                                                        return selectedObj.id;
                                                                    }
                                                                });
                                                            });
                                                        </script>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama Dokter :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaDokter1" name="dokterKso.namaDokter" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Jenis Pasien :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                            <%--<s:select list="#{'igd':'IGD', 'rawat_jalan' : 'Rawat Jalan', 'apotek' : 'Apotek',--%>
                                                            <%--'rawat_inap' : 'Rawat Inap', 'radiologi' : 'Radiologi', 'lab' : 'LAB'}" id="tipePelayanan" name="pelayanan.tipePelayanan"--%>
                                                            <%--listKey="positionId" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                                        <s:select list="#{'bpjs':'BPJS', 'umum' : 'Umum'}"
                                                                  id="masterId1" name="dokterKso.masterId"
                                                                  headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                            <%--<s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>--%>
                                                            <%--<s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pendapatanDokter.branchId"--%>
                                                            <%--listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                                        <s:if test='dokterKso.branchUser == "01"'>
                                                            <s:action id="initComboBranch" namespace="/dokterkso" name="initComboBranch_dokterkso"/>
                                                            <s:select list="#initComboBranch.listOfComboBranches" id="branchId1" name="dokterKso.branchId" readonly="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/dokterkso" name="initComboBranch_dokterkso"/>
                                                            <s:select list="#initComboBranch.listOfComboBranches" id="branchId1" name="dokterKso.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId1" name="dokterKso.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <%--RAKA-31MAR2021--%>
                                            <tr style="display: none">
                                                <td>
                                                    <label class="control-label"><small>Divisi :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboPosition" namespace="/dokterkso" name="initComboPosition_dokterkso"/>
                                                        <s:select list="#initComboPosition.listOfComboPositions" id="positionId1" name="dokterKso.positionId"
                                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <%--RAKA-end--%>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Jenis KSO:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'tindakan':'Tindakan', 'resep' : 'Resep', 'kamar' : 'Ruangan'}"
                                                                  id="jenisKso1" name="dokterKso.jenisKso"
                                                                  headerKey="" headerValue="[Select one]" cssClass="form-control" onchange="jenisKso()"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Persen KSO (%):</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="persenKso1" name="dokterKso.persenKso" type="number" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Persen KS (%):</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="persenKs1" name="dokterKso.persenKs" type="number" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tarif Ina :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'N':'Non-Active'}" id="tarifIna1" name="dokterKso.tarifIna"
                                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                                    </table>

                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <br>
                                        <h3 id="addTindakan">
                                            Add Tindakan
                                            <button
                                                    id="btnAddTindakan" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah" onclick="listPelayanan()"><i class="fa fa-plus"></i>
                                            </button>
                                        </h3>
                                        <br>
                                        <center>
                                            <table id="showdata" width="100%">
                                                <tr>
                                                    <td align="center">
                                                        <table style="width: 100%;" class="tindakanDetailTable table table-bordered" id="tindakanDetailTable">
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit targets="o" type="button" cssClass="btn btn-primary" formIds="addDokterKsoForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="fa fa-check"></i>
                                                            Save
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="add_dokterkso.action"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="350" width="600" autoOpen="false" title="Saving ...">
                                            Please don't close this window, server is processing your request ...
                                            </br>
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

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
                                                                      close();
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
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Your Page Content Here -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Tindakan</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <div class="row" style="margin-top: 7px">
                            <div class="col-sm-offset-2 col-sm-2">
                                <label class="control-label">Pelayanan</label>
                            </div>
                            <div class="col-sm-6">
                                <select id="modPelayanan" style="width: 100%" class="form-control select2" onchange="listTindakan()">
                                    <option value="">[Select One]</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-offset-2 col-sm-2">
                                <label class="control-label">Tindakan</label>
                            </div>
                            <div class="col-sm-6">
                                <select id="modTindakan" style="width: 100%" class="form-control select2">
                                    <option value="">[Select One]</option>
                                </select>
                                <%--<script>--%>
                                    <%--$(document).ready(function() {--%>
                                        <%--var functions, mapped;--%>
                                        <%--$('#modRiwayatTindakanId').typeahead({--%>
                                            <%--minLength: 1,--%>
                                            <%--source: function (query, process) {--%>
                                                <%--functions = [];--%>
                                                <%--mapped = {};--%>
                                                <%--var data = [];--%>
                                                <%--dwr.engine.setAsync(false);--%>
                                                <%--DokterKsoAction.initTypeaheadRiwayatTindakan(query,function (listdata) {--%>
                                                    <%--data = listdata;--%>
                                                <%--});--%>
                                                <%--$.each(data, function (i, item) {--%>
                                                    <%--var labelItem = item.idTindakan + " | " + item.namaTindakan;--%>
                                                    <%--mapped[labelItem] = {--%>
                                                        <%--id: item.idTindakan,--%>
                                                        <%--nama: item.namaTindakan--%>
                                                    <%--};--%>
                                                    <%--functions.push(labelItem);--%>
                                                <%--});--%>
                                                <%--process(functions);--%>
                                            <%--},--%>
                                            <%--updater: function (item) {--%>
                                                <%--var selectedObj = mapped[item];--%>
                                                <%--$('#modRiwayatTindakanName').val(selectedObj.nama);--%>
                                                <%--return selectedObj.id;--%>
                                            <%--}--%>
                                        <%--});--%>
                                    <%--});--%>
                                <%--</script>--%>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="col-sm-offset-2 col-sm-2">
                                <label class="control-label">Persen KSO Tindakan</label>
                            </div>
                            <div class="col-sm-6">
                                <s:textfield type="number" id="modPersenKsoTindakan" cssClass="form-control" />
                            </div>
                        </div>
                        <br>
                    </div>
                    <br>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button id="modBtnSave" type="button" class="btn btn-default btn-success">Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        window.listResult= function () {
            $('.tindakanDetailTable').empty();
            dwr.engine.setAsync(false);
            DokterKsoAction.searchTindakanDetailSession(function(listdata) {
                tmp_table = "<thead><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #90ee90'>No</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Tindakan ID</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Nama Tindakan</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Persen KSO</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Delete</th>"+
                        "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.tindakanId + '</td>' +
                            '<td align="center">' + item.tindakanName + '</td>' +
                            '<td align="center">' + item.persenKso + '</td>' +
                            '<td align="center">' +
                            "<a href='javascript:;' class ='item-delete-data' data ='" + item.tindakanId + "'>" +
                            "<img border='0' src='<s:url value='/pages/images/delete_task.png'/>' name='icon_delete'>" +
                            '</a>' +
                            '</td>' +
                            "</tr>";
                });
                $('.tindakanDetailTable').append(tmp_table);
                $('#tindakanDetailTable').DataTable({
                    "pageLength": 20,
                    "bDestroy":true
                });
            });
        };
        $('#btnAddTindakan').click(function () {
            $('#myForm')[0].reset();
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'addDokterKsoTindakan');
            $('#modal-edit').find('.modal-title').text('Add Dokter KSO Tindakan');
        });
        $('#modBtnSave').click(function () {
            var pelayanan = $('#modPelayanan').val();
            var tindakanId = $('#modTindakan').val();
            var tindakanName = $('#modTindakan :selected').text();
            var persenKsoTindakan = $('#modPersenKsoTindakan').val();

            dwr.engine.setAsync(false);
            if(tindakanId!='' && persenKsoTindakan!='' && pelayanan!=''){
                DokterKsoAction.saveRiwayatKsoTindakanSession(tindakanId,tindakanName,persenKsoTindakan,function() {
                    listResult();
                });
                $('#modal-edit').modal('hide');
            }else {
                var msg="";
                if (tindakanId==""){
                    msg+="Tindakan masih kosong \n";
                }
                if (persenKsoTindakan==""){
                    msg+="Persen Kso Tindakan tidak ditemukan\n";
                }
                if (pelayanan==""){
                    msg+="Pelayanan masih kosong\n";
                }
                alert(msg);
            }
        })
        $('.tindakanDetailTable').on('click', '.item-delete-data', function () {
            var id = $(this).attr('data');
            if (id!=''){
                DokterKsoAction.deleteSessionTindakanDetail(id,function (result) {
                    alert("data berhasil dihapus");
                    listResult();
                });
            } else{
                var msg="";
                if (id==""){
                    msg+="Tindakan ID tidak ditemukan \n";
                }
                alert(msg);
            }
        });

        $('#addTindakan').hide();
    })

    function jenisKso() {
        var jenis = $('#jenisKso1').val();

        if(jenis != 'tindakan'){
            $('#addTindakan').hide();
            $('#showdata').hide();
        } else{
            $('#addTindakan').show();
            $('#showdata').show();
        }

    }

    function listPelayanan(){
        var idDokter = $('#nip1').val();
        console.log(idDokter);
        var option = '<option value="">[Select One]</option>';
        DokterKsoAction.initComboPelayananDokter(idDokter, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idPelayanan + "'>" + item.namaPelayanan + "</option>";
                });
                $('#modPelayanan').html(option);
            } else {
                $('#modPelayanan').html(option);
            }
        });
    }

    function listTindakan(){
        var idPelayanan = $('#modPelayanan').val();
        var idDokter = $('#nip1').val();
        console.log(idPelayanan);
        var option = '<option value="">[Select One]</option>';
        DokterKsoAction.initComboTindakanDokter(idPelayanan, idDokter, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idTindakan + "'>" + item.idTindakan + " | " + item.tindakan + "</option>";
                });
                $('#modTindakan').html(option);
            } else {
                $('#modTindakan').html(option);
            }
        });
    }

</script>
