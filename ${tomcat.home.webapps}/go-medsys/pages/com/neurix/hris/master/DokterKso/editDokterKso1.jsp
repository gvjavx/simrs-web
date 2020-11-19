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
                var dokterKsoId = document.getElementById("dokterKsoId2").value;
                var nip = document.getElementById("nip2").value;
                var branchId = document.getElementById("branchId2").value;
                var masterId = document.getElementById("masterId2").value;
                var jenisKso = document.getElementById("jenisKso2").value;
                var positionId = document.getElementById("positionId2").value;
                var persenKso = document.getElementById("persenKso2").value;
                var persenKs = document.getElementById("persenKs2").value;

                if (dokterKsoId != '' && nip != ''&& branchId != '' && masterId != '' && jenisKso != ''
                        && persenKso != '' && persenKs != '' && positionId != '') {
                    var status ="";
                    dwr.engine.setAsync(false);
                    DokterKsoAction.cekBeforeSave(nip,"edit",function (listData) {
                        status=listData;
                    });
                    if (status == ""){
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
                    if (dokterKsoId == '') {
                        msg += 'Field <strong>Dokter KSO ID </strong> is required.' + '<br/>';
                    }
                    if (nip == '') {
                        msg += 'Field <strong>Nip </strong> is required.' + '<br/>';
                    }
                    if (branchId == '') {
                        msg += 'Field <strong>Unit </strong> is required.' + '<br/>';
                    }
                    if (masterId == '') {
                        msg += 'Field <strong>Master ID </strong> is required.' + '<br/>';
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
            Edit Dokter Tamu
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-pencil"></i> Edit Dokter Tamu</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="editDokterKsoForm" method="post" theme="simple" namespace="/dokterkso" action="saveEdit_dokterkso" cssClass="well form-horizontal">
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
                                                    <label class="control-label"><small>Dokter Tamu ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="dokterKsoId2" name="dokterKso.dokterKsoId" required="true" disabled="false" readonly="true" cssClass="form-control"/>
                                                        <s:hidden id="dokterKsoTindakanId2" name="dokterKso.dokterKsoTindakanId" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>NIP Dokter :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="nip2" name="dokterKso.nip" cssClass="form-control" readonly="true"
                                                                     maxlength="12"
                                                        />
                                                        <script>
                                                            $(document).ready(function() {
                                                                var functions, mapped;
                                                                $('#nip2').typeahead({
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
                                                                                nama: item.namaDokter
                                                                            };
                                                                            functions.push(labelItem);
                                                                        });
                                                                        process(functions);
                                                                    },
                                                                    updater: function (item) {
                                                                        var selectedObj = mapped[item];
                                                                        $('#namaDokter2').val(selectedObj.nama);
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
                                                    <label class="control-label"><small>Nama Dokter:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaDokter2" name="dokterKso.namaDokter" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Master Id :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                            <%--<s:select list="#{'igd':'IGD', 'rawat_jalan' : 'Rawat Jalan', 'apotek' : 'Apotek',--%>
                                                            <%--'rawat_inap' : 'Rawat Inap', 'radiologi' : 'Radiologi', 'lab' : 'LAB'}" id="tipePelayanan" name="pelayanan.tipePelayanan"--%>
                                                            <%--listKey="positionId" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                                        <s:select list="#{'bpjs':'BPJS', 'umum' : 'Umum'}"
                                                                  id="masterId2" name="dokterKso.masterId"
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
                                                        <s:if test='dokterKso.branchUser == "KP"'>
                                                            <s:action id="initComboBranch" namespace="/dokterkso" name="initComboBranch_dokterkso"/>
                                                            <s:select list="#initComboBranch.listOfComboBranches" id="branchId2" name="dokterKso.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/dokterkso" name="initComboBranch_dokterkso"/>
                                                            <s:select list="#initComboBranch.listOfComboBranches" id="branchId2" name="dokterKso.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId2" name="dokterKso.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Divisi :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboPosition" namespace="/dokterkso" name="initComboPosition_dokterkso"/>
                                                        <s:select list="#initComboPosition.listOfComboPositions" id="positionId2" name="dokterKso.positionId"
                                                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Jenis KSO:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'tindakan':'Tindakan', 'obat' : 'Obat', 'kamar' : 'Kamar'}"
                                                                  id="jenisKso2" name="dokterKso.jenisKso"
                                                                  headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Persen KSO:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="persenKso2" name="dokterKso.persenKso" type="number" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Persen KS:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="persenKs2" name="dokterKso.persenKs" type="number" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tarif Ina :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'N':'Non-Active'}" id="tarifIna2" name="dokterKso.tarifIna"
                                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                                    </table>

                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <br>
                                        <h3>
                                            Add Dokter KSO Tindakan
                                            <button
                                                    id="btnAddDetail" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
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
                                                        <sj:submit targets="o" type="button" cssClass="btn btn-primary" formIds="editDokterKsoForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="fa fa-check"></i>
                                                            Save
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="search_dokterkso.action"/>'">
                                                            <i class="fa fa-arrow-left"></i> Back
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
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-offset-2 col-sm-3">
                                <label class="control-label">Riwayat Tindakan ID</label>
                            </div>
                            <div class="col-sm-4">
                                <s:textfield id="modRiwayatTindakanId" cssClass="form-control"
                                             maxlength="12"
                                />
                                <script>
                                    $(document).ready(function() {
                                        var functions, mapped;
                                        $('#modRiwayatTindakanId').typeahead({
                                            minLength: 1,
                                            source: function (query, process) {
                                                functions = [];
                                                mapped = {};
                                                var data = [];
                                                dwr.engine.setAsync(false);
                                                DokterKsoAction.initTypeaheadRiwayatTindakan(query,function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    var labelItem = item.idTindakan + " | " + item.namaTindakan;
                                                    mapped[labelItem] = {
                                                        id: item.idTindakan,
                                                        nama: item.namaTindakan
                                                    };
                                                    functions.push(labelItem);
                                                });
                                                process(functions);
                                            },
                                            updater: function (item) {
                                                var selectedObj = mapped[item];
                                                $('#modRiwayatTindakanName').val(selectedObj.nama);
                                                return selectedObj.id;
                                            }
                                        });
                                    });
                                </script>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="col-sm-offset-2 col-sm-3">
                                <label class="control-label">Nama Riwayat Tindakan</label>
                            </div>
                            <div class="col-sm-4">
                                <s:textfield id="modRiwayatTindakanName" cssClass="form-control" readonly="true"
                                />
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="col-sm-offset-2 col-sm-3">
                                <label class="control-label">Persen KSO Tindakan</label>
                            </div>
                            <div class="col-sm-4">
                                <s:textfield id="modPersenKsoTindakan" cssClass="form-control"
                                />
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
        listResult();
        $('#btnAddDetail').click(function () {
            $('#myForm')[0].reset();
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'editDokterKsoTindakan');
            $('#modal-edit').find('.modal-title').text('Edit Dokter KSO Tindakan');
        });
        $('#modBtnSave').click(function () {
            var riwayatTindakanId = $('#modRiwayatTindakanId').val();
            var riwayatTindakanName = $('#modRiwayatTindakanName').val();
            var persenKsoTindakan = $('#modPersenKsoTindakan').val();

            dwr.engine.setAsync(false);
            if(riwayatTindakanId!='' && persenKsoTindakan!=''){
                DokterKsoAction.saveRiwayatKsoTindakanSession(riwayatTindakanId,riwayatTindakanName,persenKsoTindakan,function() {
                    listResult();
                });
                $('#modal-edit').modal('hide');
            }else {
                var msg="";
                if (riwayatTindakanId==""){
                    msg+="Riwayat Tindakan Id masih kosong \n";
                }
                if (persenKsoTindakan==""){
                    msg+="Persen Kso Tindakan tidak ditemukan\n";
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
    })
</script>
