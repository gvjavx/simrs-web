<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var tipeJurnalId = document.getElementById("tipeJurnalIdEdit").value;
            var tipeJurnalName    = document.getElementById("tipeJurnalNameEdit").value;
            var statusSave="";
            dwr.engine.setAsync(false);
            KodeRekeningAction.cekStatusBeforeSave( function(status) {
                statusSave=status;
            });
            if (tipeJurnalId != '' && tipeJurnalName != ''&&statusSave=="") {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if (tipeJurnalId == '') {
                    msg += 'Field <strong>Tipe Jurnal ID</strong> not found.' + '<br/>';
                }

                if (tipeJurnalName == '') {
                    msg += 'Field <strong>Nama Tipe Jurnal</strong> is required.' + '<br/>';
                }
                if (statusSave != '') {
                    msg += statusSave + '<br/>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');
            }
        });
        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };
    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/tipeJurnal" action="saveEdit_tipeJurnal" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Edit Tipe Jurnal</legend>
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
                            <label class="control-label"><small>Tipe Jurnal Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="tipeJurnalIdEdit" name="tipeJurnal.tipeJurnalId" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Tipe Jurnal :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tipeJurnalNameEdit" name="tipeJurnal.tipeJurnalName" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <div id="kodeRekeningList">
                    <br>
                    <h4>
                        <button
                                id="btnAddKodeRekening" type="button" class="btn btn-default btn-info"><i class="fa fa-plus"></i>
                        </button>
                        Tambah Kode Rekening
                    </h4>
                    <center>
                        <table id="showdata" width="80%">
                            <tr>
                                <td align="center">
                                    <table style="width: 100%;"
                                           class="kodeRekeningTable table table-bordered">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                    <br>
                </div>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyRolefuncForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-danger" onclick="cancelBtn();">
                            <i class="fa fa-refresh"/> Cancel
                        </button>
                    </div>
                </div>
                <div id="actions" class="form-actions">
                    <table>
                        <tr>
                            <div id="crud">
                                <td>
                                    <table>
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
                                                                    //$(this).dialog('close');
                                                                      callSearch();
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
</body>
</html>
<div id="modal-tambah-kode-rekening" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Tambah Kode Rekening</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-4">COA :</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="kodeRekening" onkeydown="formatKodeRekening(this)"
                                   onkeyup="formatKodeRekening(this)" onchange="cekAvailableCoa(this)" >
                            <script>
                                $(document).ready(function() {
                                    var functions, mapped;
                                    $('#kodeRekening').typeahead({
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
                                                var labelItem = item.kodeRekening + " | " + item.namaKodeRekening;
                                                mapped[labelItem] = {
                                                    id: item.kodeRekening,
                                                    nama: item.namaKodeRekening
                                                };
                                                functions.push(labelItem);
                                            });
                                            process(functions);
                                        },
                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            $('#namaKodeRekening').val(selectedObj.nama);
                                            return selectedObj.id;
                                        }
                                    });
                                });
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">Nama Kode Rekening :</label>
                        <div class="col-sm-6">
                            <table>
                                <input type="text" class="form-control" id="namaKodeRekening" readonly>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">Posisi Rekening :</label>
                        <div class="col-sm-6">
                            <table>
                                <s:select list="#{'D':'Debet', 'K' : 'Kredit'}" id="posisiKodeRekening"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                <input type="text" class="form-control" id="posisiKodeRekeningView" style="display: none" readonly>

                            </table>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveKodeRekening" type="button" class="">Tambah</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<script>
    window.loadKodeRekening = function () {
        $('.kodeRekeningTable').find('tbody').remove();
        $('.kodeRekeningTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table_kode_rekening = "";
        KodeRekeningAction.searchKodeRekening("",function (listdata) {
            tmp_table_kode_rekening = "<thead style='font-size: 14px' ><tr class='active'>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196'>No</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>COA</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Nama Kode Rekening</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Posisi Rekening</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196'>Delete</th>" +
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table_kode_rekening += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="center">' + item.kodeRekening + '</td>' +
                    '<td align="center">' + item.namaKodeRekening + '</td>' +
                    '<td align="center">' + item.posisiName + '</td>' +
                    '<td align="center">' +
                    "<a href='javascript:;' class ='item-delete-kode-rekening' data ='" + item.kodeRekening + "' >" +
                    "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_delete'>" +
                    '</a>' +
                    '</td>' +
                    "</tr>";
            });
            $('.kodeRekeningTable').append(tmp_table_kode_rekening);
        });
    };
    $('#btnAddKodeRekening').click(function () {
        $('#kodeRekening').val("");
        $('#namaKodeRekening').val("");
        $('#kodeRekening').removeAttr("readonly");
        $('#posisiKodeRekening').show();
        $('#posisiKodeRekeningView').hide();
        $("#btnSaveKodeRekening").attr('class', 'btn btn-default btn-success');
        $("#btnSaveKodeRekening").text("tambah");
        $('#modal-tambah-kode-rekening').modal('show');
        $('#myForm').attr('action', 'addKodeRekening');
        $('#modal-tambah-kode-rekening').find('.modal-title').text('Tambah Kode Rekening');
    });
    $('#btnSaveKodeRekening').click(function () {
        var url = $('#myForm').attr('action');
        var kodeRekening = $('#kodeRekening').val();
        var namaKodeRekening = $('#namaKodeRekening').val();
        var posisiKodeRekening = $('#posisiKodeRekening').val();
        if (url == 'addKodeRekening') {
            if (kodeRekening != ''&&posisiKodeRekening!='') {
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    KodeRekeningAction.saveAddKodeRekening(kodeRekening,namaKodeRekening,posisiKodeRekening, function () {
                        alert('Data Successfully Added');
                        $('#modal-tambah-kode-rekening').modal('hide');
                        $('#myForm')[0].reset();
                        loadKodeRekening();
                    });
                }
            } else {
                var msg="";
                if(kodeRekening==""){
                    msg+='COA harus diisi \n';
                }
                if(posisiKodeRekening==""){
                    msg+='Posisi kode rekening harus diisi \n';
                }
                alert(msg);
            }
        }else {
            if (confirm('Are you sure you want to delete this Record?')) {
                KodeRekeningAction.saveDeleteKodeRekening(kodeRekening, function () {
                    $('#modal-tambah-kode-rekening').modal('hide');
                    $('#myForm')[0].reset();
                    alert('Record has been Deleted successfully.');
                    loadKodeRekening();
                });
            }
        }
    });
    $('.kodeRekeningTable').on('click', '.item-delete-kode-rekening', function () {
        var id = $(this).attr('data');
        dwr.engine.setAsync(false);
        KodeRekeningAction.searchKodeRekening(id, function (listdata) {
            $.each(listdata, function (i, item) {
                $('#kodeRekening').val(item.kodeRekening);
                $('#namaKodeRekening').val(item.namaKodeRekening);
                $('#posisiKodeRekeningView').val(item.posisiName);
            });
        });
        $('#kodeRekening').attr("readonly","true");
        $('#posisiKodeRekening').hide();
        $('#posisiKodeRekeningView').show();
        $("#btnSaveKodeRekening").attr('class', 'btn btn-default btn-danger');
        $("#btnSaveKodeRekening").text("hapus");
        $('#modal-tambah-kode-rekening').find('.modal-title').text('Hapus Kode Rekening');
        $('#modal-tambah-kode-rekening').modal('show');
        $('#myForm').attr('action', 'delete');
    });
    $(document).ready(function () {
        loadKodeRekening();
    })
</script>