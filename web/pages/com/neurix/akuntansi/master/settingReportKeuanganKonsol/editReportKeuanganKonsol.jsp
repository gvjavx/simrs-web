<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SettingReportKeuanganKonsolAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_reportKeuanganKonsol'/>";
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
                window.location.href="<s:url action='search_reportKeuanganKonsol.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var kodeRekeningAlias = document.getElementById("kodeRekeningAlias1").value;
                var namaKodeRekeningAlias = document.getElementById("namaKodeRekeningAlias1").value;
                if (kodeRekeningAlias != "" && namaKodeRekeningAlias != "") {
                    var status ="";
                    dwr.engine.setAsync(false);
                    SettingReportKeuanganKonsolAction.cekBeforeSave(kodeRekeningAlias,"add",function (listData) {
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
                    }else{
                        event.originalEvent.options.submit = false;
                        document.getElementById('errorValidationMessage').innerHTML = status;
                        $.publish('showErrorValidationDialog');
                    }

                } else {
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (kodeRekeningAlias == '') {
                        msg += 'Field <strong>Kode Rekening Alias</strong> is required.' + '<br/>';
                    }
                    if (namaKodeRekeningAlias == '') {
                        msg += 'Field <strong>Nama Kode Rekening Alias</strong> is required.' + '<br/>';
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
            Edit Report Keuangan Konsol
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-pencil"></i> Edit Report Keuangan Konsol</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="editReportKeuanganKonsolForm" method="post"  theme="simple" namespace="/reportKeuanganKonsol" action="saveEdit_reportKeuanganKonsol.action" cssClass="form-horizontal">
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
                                                    <label class="control-label"><small>Kode Rekening Alias :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="kodeRekeningAlias1" name="akunSettingReportKeuanganKonsol.kodeRekeningAlias" required="true" readonly="true" disabled="false" cssClass="form-control"/>
                                                        <s:hidden name="akunSettingReportKeuanganKonsol.settingReportKonsolId"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama Kode Rekening Alias :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaKodeRekeningAlias1" name="akunSettingReportKeuanganKonsol.namaKodeRekeningAlias" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Flag Label:</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'N':'Non-Active'}" id="flagLabel1" name="akunSettingReportKeuanganKonsol.flagLabel"
                                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                                    </table>

                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <br>
                                        <h3>
                                            Add Kode Rekening
                                            <button
                                                    id="btnAddDetail" type="button" class="btn btn-default btn-info" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i>
                                            </button>
                                        </h3>
                                        <br>
                                        <center>
                                            <table id="showdata" width="100%">
                                                <tr>
                                                    <td align="center">
                                                        <table style="width: 100%;" class="konsolDetailTable table table-bordered" id="konsolDetailTable">
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
                                                        <sj:submit targets="o" type="button" cssClass="btn btn-primary" formIds="editReportKeuanganKonsolForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="fa fa-check"></i>
                                                            Save
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="search_reportKeuanganKonsol.action"/>'">
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
                                <label class="control-label">Rekening ID</label>
                            </div>
                            <div class="col-sm-4">
                                <s:textfield id="modRekeningId" cssClass="form-control"
                                             maxlength="12"
                                />
                                <script>
                                    $(document).ready(function() {
                                        var functions, mapped;
                                        $('#modRekeningId').typeahead({
                                            minLength: 1,
                                            source: function (query, process) {
                                                functions = [];
                                                mapped = {};
                                                var data = [];
                                                dwr.engine.setAsync(false);
                                                KodeRekeningAction.initTypeaheadKodeRekening(query,function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    var labelItem = item.rekeningId + " | " + item.namaKodeRekening;
                                                    mapped[labelItem] = {
                                                        id: item.rekeningId,
                                                        nama: item.namaKodeRekening
                                                    };
                                                    functions.push(labelItem);
                                                });
                                                process(functions);
                                            },
                                            updater: function (item) {
                                                var selectedObj = mapped[item];
                                                $('#modeKodeRekeningName').val(selectedObj.nama);
                                                return selectedObj.id;
                                            }
                                        });
                                    });
                                </script>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="col-sm-offset-2 col-sm-3">
                                <label class="control-label">Nama Kode Rekening</label>
                            </div>
                            <div class="col-sm-4">
                                <s:textfield id="modeKodeRekeningName" cssClass="form-control" readonly="true"
                                />
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="col-sm-offset-2 col-sm-3">
                                <label class="control-label">Operator</label>
                            </div>
                            <div class="col-sm-4">
                                <s:select list="#{'T':'Tambah'}" id="modOperator"
                                          headerKey="K" headerValue="Kurang" cssClass="form-control" />
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
            $('.konsolDetailTable').empty();
            dwr.engine.setAsync(false);
            SettingReportKeuanganKonsolAction.searchKonsolDetailSession(function(listdata) {
                tmp_table = "<thead><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #90ee90'>No</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Rekening ID</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Nama Kode Rekening</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Operator</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Delete</th>"+
                        "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.rekeningId + '</td>' +
                            '<td align="center">' + item.namaRekening + '</td>' +
                            '<td align="center">' + item.sbOperator + '</td>' +
                            '<td align="center">' +
                            "<a href='javascript:;' class ='item-delete-data' data ='" + item.rekeningId + "'>" +
                            "<img border='0' src='<s:url value='/pages/images/delete_task.png'/>' name='icon_delete'>" +
                            '</a>' +
                            '</td>' +
                            "</tr>";
                });
                $('.konsolDetailTable').append(tmp_table);
                $('#konsolDetailTable').DataTable({
                    "pageLength": 20,
                    "bDestroy":true
                });
            });
        };
        listResult();
        $('#btnAddDetail').click(function () {
            $('#myForm')[0].reset();
            $('#modal-edit').modal('show');
            $('#myForm').attr('action', 'addKodeRekening');
            $('#modal-edit').find('.modal-title').text('Add Detail Report Keuangan Konsol');
        });
        $('#modBtnSave').click(function () {
            var idRekening = $('#modRekeningId').val();
            var namaKodeRekening = $('#modeKodeRekeningName').val();
            var operator = $('#modOperator').val();

            dwr.engine.setAsync(false);
            if(idRekening!='' && operator!=''){
                SettingReportKeuanganKonsolAction.saveKonsolDetailSession(idRekening,namaKodeRekening,operator,function() {
                    listResult();
                });
                $('#modal-edit').modal('hide');
            }else {
                var msg="";
                if (idRekening==""){
                    msg+="Rekening Id masih kosong \n";
                }
                if (operator==""){
                    msg+="Operator tidak ditemukan\n";
                }
                alert(msg);
            }
        })
        $('.konsolDetailTable').on('click', '.item-delete-data', function () {
            var id = $(this).attr('data');
            if (id!=''){
                SettingReportKeuanganKonsolAction.deleteSessionKonsolDetail(id,function (result) {
                    alert("data berhasil dihapus");
                    listResult();
                });
            } else{
                var msg="";
                if (id==""){
                    msg+="Rekening ID tidak ditemukan \n";
                }
                alert(msg);
            }
        });
    })
</script>
