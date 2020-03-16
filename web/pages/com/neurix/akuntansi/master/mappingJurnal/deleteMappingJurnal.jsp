<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MappingJurnalAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>

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
            window.location.href="<s:url action='initForm_mappingJurnal'/>";
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
                window.location.href="<s:url action='search_mappingJurnal.action'/>";
            };

            $.subscribe('beforeProcessSave', function (event, data) {
                var tipeJurnalId = document.getElementById("tipeJurnalId").value;
                var transId = document.getElementById("transId").value;
                if (tipeJurnalId!=""&&transId!="") {
                    var status ="";
                    dwr.engine.setAsync(false);
                    MappingJurnalAction.cekBeforeAdd(tipeJurnalId,transId,function (listData) {
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
                    if ( tipeJurnalId== '') {
                        msg += 'Field <strong>Tipe Jurnal</strong> is required.' + '<br/>';
                    }
                    if ( transId== '') {
                        msg += 'Field <strong>Transaksi Billing</strong> is required.' + '<br/>';
                    }
                    document.getElementById('errorValidationMessage').innerHTML = msg;
                    $.publish('showErrorValidationDialog');
                }
            });
            $.subscribe('beforeProcessDelete', function (event, data) {
                if (confirm('Do you want to delete this record?')) {
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
            Delete Mapping Jurnal
            <small>e-HEALTH</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-trash"></i> Delete Mapping Jurnal</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="mappingJurnalForm" method="post"  theme="simple" namespace="/mappingJurnal" action="saveDelete_mappingJurnal.action" cssClass="form-horizontal">
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
                                                    <label class="control-label"><small>Tipe Jurnal :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="initComboTipeJurnal" namespace="/tipeJurnal" name="initComboTipeJurnal_tipeJurnal"/>
                                                        <s:select list="#initComboTipeJurnal.listOfComboTipeJurnal" id="tipeJurnalId" name="mappingJurnal.tipeJurnalId" disabled="true"
                                                                  listKey="tipeJurnalId" listValue="tipeJurnalName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        <s:hidden name="mappingJurnal.tipeJurnalId"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Transaksi Billing :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboTrans" namespace="/trans" name="initComboTrans_trans"/>
                                                        <s:select list="#comboTrans.listOfComboTrans" id="transId" name="mappingJurnal.transId"
                                                                  onchange="$(this).css('border','')" disabled="true"
                                                                  listKey="transId" listValue="transName" headerKey="" headerValue="[ Select One ]" cssClass="form-control" />
                                                        <s:hidden name="mappingJurnal.transId"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <br>
                                        <center>
                                            <table id="showdata" width="100%">
                                                <tr>
                                                    <td align="center">
                                                        <table style="width: 100%;" class="kodeRekeningTable table table-bordered" id="kodeRekeningTable">
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
                                                        <sj:submit targets="o" type="button" cssClass="btn btn-primary" formIds="mappingJurnalForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessDelete" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="fa fa-trash"></i>
                                                            Delete
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="search_mappingJurnal.action"/>'">
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
                                            </br>
                                            </br>
                                            <center>
                                                <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
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
<script>
    $(document).ready(function() {
        window.listResult= function () {
            $('.kodeRekeningTable').empty();
            dwr.engine.setAsync(false);
            MappingJurnalAction.searchKodeRekeningSession(function(listdata) {
                tmp_table = "<thead><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #90ee90'>No</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Kode Rekening</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Nama Kode Rekening</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Posisi</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Master</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Bukti</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Kode Barang</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>List Kirim</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Parameter</th>"+
                    "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.kodeRekening + '</td>' +
                        '<td align="center">' + item.kodeRekeningName + '</td>' +
                        '<td align="center">' + item.posisi + '</td>' +
                        '<td align="center">' + item.masterId+ '</td>' +
                        '<td align="center">' + item.bukti + '</td>' +
                        '<td align="center">' + item.kodeBarang + '</td>' +
                        '<td align="center">' + item.kirimList + '</td>' +
                        '<td align="center">' + item.keterangan + '</td>' +
                        "</tr>";
                });
                $('.kodeRekeningTable').append(tmp_table);
                $('#kodeRekeningTable').DataTable({
                    "pageLength": 20,
                    "bDestroy":true
                });
            });
        };
        listResult();
    })
</script>